package com.sourceframework.extjs.web.binder;

import java.beans.PropertyEditorSupport;
import java.util.Collection;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class QueryConditionBindingInitializer
  implements WebBindingInitializer
{
  private Gson gson = new Gson();

  public Gson getGson() {
    return this.gson;
  }

  public void initBinder(WebDataBinder binder, WebRequest request)
  {
    binder.registerCustomEditor(Collection.class, "fields", new PropertyEditorSupport()
    {
      @SuppressWarnings("rawtypes")
	public void setAsText(String text) throws IllegalArgumentException
      {
        setValue(QueryConditionBindingInitializer.this.gson.fromJson(text, new TypeToken() {  } .getType()));
      }
    });
    binder.registerCustomEditor(Collection.class, "filter", new PropertyEditorSupport()
    {
      @SuppressWarnings("rawtypes")
	public void setAsText(String text) throws IllegalArgumentException
      {
        setValue(QueryConditionBindingInitializer.this.gson.fromJson(text, new TypeToken() {  } .getType()));
      }
    });
    binder.registerCustomEditor(Collection.class, "sort", new PropertyEditorSupport()
    {
      @SuppressWarnings("rawtypes")
	public void setAsText(String text) throws IllegalArgumentException
      {
        setValue(QueryConditionBindingInitializer.this.gson.fromJson(text, new TypeToken() {  } .getType()));
      }
    });
  }
}