package com.sourceframework.extjs.domain;

import java.io.Serializable;

public class User
  implements Serializable
{
  private static final long serialVersionUID = 7414103723490439299L;
  private String userId;

  public String getUserId()
  {
    return this.userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}