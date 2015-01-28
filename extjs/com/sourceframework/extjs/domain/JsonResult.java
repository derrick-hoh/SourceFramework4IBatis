package com.sourceframework.extjs.domain;

import java.io.Serializable;

public class JsonResult<E>
  implements Serializable
{
  private static final long serialVersionUID = -583313538462869967L;
  private boolean success;
  private E data;

  public JsonResult()
  {
    this.success = true;
  }

  public JsonResult(E e) {
    this();
    this.data = e;
  }

  public boolean isSuccess() {
    return this.success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public E getData() {
    return this.data;
  }

  public void setData(E data) {
    this.data = data;
  }
}