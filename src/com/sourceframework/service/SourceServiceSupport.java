package com.sourceframework.service;

import com.sourceframework.dao.SourceIBatisDao;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class SourceServiceSupport
{
  protected SourceIBatisDao ibatisDao;

  @Autowired(required=false)
  @Qualifier("ibatisDao")
  public void setIbatisDao(SourceIBatisDao ibatisDao)
  {
    this.ibatisDao = ibatisDao;
  }

  public <V> V get(String queryName) {
    return get(queryName, null);
  }

  public <V, P> V get(String queryName, P p) {
    return this.ibatisDao.get(queryName, p);
  }

  public <V> Collection<V> getAll(String queryName) {
    return getAll(queryName, null);
  }

  public <V, P> Collection<V> getAll(String queryName, P queryCondition) {
    return this.ibatisDao.getAll(queryName, queryCondition);
  }

  public <V, P> Collection<V> getAll(String queryName, P queryCondition, int start, int limit) {
    return this.ibatisDao.getAll(queryName, queryCondition, start, limit);
  }

  public <V> int delete(String deleteName, V v) {
    return this.ibatisDao.delete(deleteName, v);
  }

  public <V> int delete(String deleteName, Collection<V> vs) {
    return this.ibatisDao.delete(deleteName, vs);
  }

  public int delete(String deleteName) {
    return this.ibatisDao.delete(deleteName);
  }

  public <V> int update(String updateName, V v) {
    return this.ibatisDao.update(updateName, v);
  }

  public <V> int update(String updateName, Collection<V> vs) {
    return this.ibatisDao.update(updateName, vs);
  }

  public <V> void insert(String insertName, V v) {
    this.ibatisDao.insert(insertName, v);
  }

  public <V> void insert(String insertName, Collection<V> vs) {
    this.ibatisDao.insert(insertName, vs);
  }
}