package com.sourceframework.extjs.web.controller;

import com.sourceframework.extjs.domain.EntityCollection;
import com.sourceframework.extjs.domain.GridResult;
import com.sourceframework.extjs.domain.JsonResult;
import com.sourceframework.extjs.domain.QueryCondition;
import com.sourceframework.service.SourceServiceSupport;
import com.sourceframework.utils.ReflectionUtils;

import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public class ExtJSControllerSupport<E> {
	protected static Logger logger = LoggerFactory.getLogger(ExtJSControllerSupport.class);
	@SuppressWarnings("unused")
	private static final String GRID_NAME = "get%sGrid";
	@SuppressWarnings("unused")
	private static final String GRID_TOTAL = "get%sTotal";
	@SuppressWarnings("unused")
	private static final String INSERT_NAME = "insert%s";
	@SuppressWarnings("unused")
	private static final String UPDATE_NAME = "update%s";
	@SuppressWarnings("unused")
	private static final String DELETE_NAME = "delete%s";
	@SuppressWarnings("unused")
	private static final String CONTROLLER_SUFFIX = "Controller";
	protected Class<E> entityClass;
	protected SourceServiceSupport service;

	@Autowired
	@Qualifier("service")
	public void setService(SourceServiceSupport service) {
		this.service = service;
	}

	public ExtJSControllerSupport() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	private String getSimpleName() {
		return StringUtils.substringBefore(getClass().getSimpleName(),
				"Controller");
	}

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Set.class, new PropertyEditorSupport() {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void setAsText(String text) throws IllegalArgumentException {
				if ((JSONUtils.mayBeJSON(text))
						&& (binder.getTarget().getClass() == EntityCollection.class)) {
					Set entities = new HashSet();
					for (Iterator localIterator = JSONArray.fromObject(text)
							.iterator(); localIterator.hasNext();) {
						Object object = localIterator.next();
						if ((object instanceof JSONObject)) {
							entities.add(JSONObject.toBean((JSONObject) object, ExtJSControllerSupport.this.entityClass));
						}
					}

					if (entities.size() > 0) {
						setValue(entities);
					}
					return;
				}
			}
		});
	}

	protected boolean isNew(E entity) {
		return true;
	}

	protected void preHandle(E entity) {
	}

	protected void postHandle(E entity) {
	}

	protected void upreHandle(E entity) {
	}

	protected void upstHandle(E entity) {
	}

	protected void proSort(QueryCondition queryCondition) {
	}

	protected boolean isExist(String id) {
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = { "/grid" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public GridResult<E> grid(QueryCondition queryCondition, ModelMap model, HttpServletRequest request) {
		proSort(queryCondition);
		Collection list = this.service.getAll(String.format("get%sGrid", new Object[] { getSimpleName() }),queryCondition, queryCondition.getStart(),queryCondition.getLimit());
		int total = ((Integer) this.service.get(String.format("get%sTotal", new Object[] { getSimpleName() }),queryCondition)).intValue();
		return new GridResult(list, total);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public JsonResult<Void> add(E entity, HttpServletRequest request) {
		boolean isNew = isNew(entity);
		if (isNew) {
			preHandle(entity);
			this.service.insert(String.format("insert%s",new Object[] { getSimpleName() }), entity);
			postHandle(entity);
		} else {
			upreHandle(entity);
			this.service.update(String.format("update%s",new Object[] { getSimpleName() }), entity);
			upstHandle(entity);
		}

		return new JsonResult();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(params = { "entities" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public JsonResult<Void> add(EntityCollection<E> entitys) {
		for (Object entity : entitys.getEntities()) {
			preHandle((E) entity);
		}

		this.service.insert(String.format("insert%s", new Object[] { getSimpleName() }), entitys.getEntities());

		for (Object entity : entitys.getEntities()) {
			postHandle((E) entity);
		}

		return new JsonResult();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = { org.springframework.web.bind.annotation.RequestMethod.PUT })
	@ResponseBody
	public JsonResult<Void> modify(E entity) {
		preHandle(entity);
		this.service.update(String.format("update%s", new Object[] { getSimpleName() }), entity);
		postHandle(entity);
		return new JsonResult();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = { "/{id}" }, method = { org.springframework.web.bind.annotation.RequestMethod.DELETE })
	@ResponseBody
	public JsonResult<String> delete(@PathVariable String id) {
		JsonResult result = new JsonResult();
		boolean isExist = isExist(id);
		if (isExist) {
			this.service.delete(String.format("delete%s", new Object[] { getSimpleName() }), id);
			result.setSuccess(true);
		} else {
			result.setData("删除失败!");
			result.setSuccess(false);
		}

		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = { org.springframework.web.bind.annotation.RequestMethod.DELETE })
	@ResponseBody
	public JsonResult<Void> delete(@RequestParam("id") String[] id) {
		if ((id != null) && (id.length > 0)) {
			this.service.delete(String.format("delete%s",new Object[] { getSimpleName() }), Arrays.asList(id));
		}

		return new JsonResult();
	}
}