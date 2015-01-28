package com.sourceframework.extjs.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class EntityCollection<E>
  implements Serializable
{
  private static final long serialVersionUID = 1767392352252814488L;
  private Set<E> entities;

@SuppressWarnings({ "unchecked", "rawtypes" })
public EntityCollection()
  {
    this.entities = new HashSet();
  }

  public Set<E> getEntities() {
    return this.entities;
  }

  public void setEntities(Set<E> entities) {
    this.entities = entities;
  }
}