package com.sourceframework.extjs.domain;

import java.io.Serializable;
import java.util.Collection;

public class GridResult<E>
  implements Serializable
{
  private static final long serialVersionUID = 2539990099370837658L;
  private boolean success;
  private int totalCount;
  private Collection<E> results;

  public GridResult()
  {
    this.success = true;
  }

  public GridResult(Collection<E> results, int totalCount) {
    this();
    this.results = results;
    this.totalCount = totalCount;
  }

  public boolean isSuccess() {
    return this.success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public int getTotalCount() {
    return this.totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public Collection<E> getResults() {
    return this.results;
  }

  public void setResults(Collection<E> results) {
    this.results = results;
  }
}