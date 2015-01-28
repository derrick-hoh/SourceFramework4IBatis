package com.sourceframework.extjs.domain;

import java.io.Serializable;

public class SortCondition
  implements Serializable
{
  private static final long serialVersionUID = -6791202240877219224L;
  private String property;
  private String direction;

  public String getProperty()
  {
    return this.property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public String getDirection() {
    return this.direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }
}