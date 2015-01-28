package com.sourceframework.extjs.domain;

import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.Collection;

public class QueryCondition
  implements Serializable
{
  private static final long serialVersionUID = 2981117250951871303L;
  private int page;
  private int start;
  private int limit;
  private String query;
  private Collection<String> fields;
  private Collection<FilterCondition> filter;
  private Collection<SortCondition> sort;
  private User user;

  public QueryCondition()
  {
    this.filter = Lists.newArrayList();
  }

  public int getPage() {
    return this.page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getStart() {
    return this.start;
  }

  public void setStart(int start) {
    this.start = start;
  }

  public int getLimit() {
    return this.limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public String getQuery() {
    return this.query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public Collection<String> getFields() {
    return this.fields;
  }

  public void setFields(Collection<String> fields) {
    this.fields = fields;
  }

  public Collection<FilterCondition> getFilter() {
    return this.filter;
  }

  public void setFilter(Collection<FilterCondition> filter) {
    this.filter = filter;
  }

  public Collection<SortCondition> getSort() {
    return this.sort;
  }

  public void setSort(Collection<SortCondition> sort) {
    this.sort = sort;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}