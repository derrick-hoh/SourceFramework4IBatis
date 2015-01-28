package com.sourceframework.extjs.domain;

import java.io.Serializable;

public class FilterCondition
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = -980382968958561270L;
  private String field;
  private String type;
  private Object value;
  private String comparison;

  public String getField()
  {
    return this.field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Object getValue() {
    return this.value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  public String getComparison() {
    return this.comparison;
  }

  public void setComparison(String comparison) {
    this.comparison = comparison;
  }

  public Object clone()
  {
    try {
      return super.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }

    return null;
  }
}