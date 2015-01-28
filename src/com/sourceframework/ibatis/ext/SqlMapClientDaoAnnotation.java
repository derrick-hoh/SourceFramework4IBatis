package com.sourceframework.ibatis.ext;

import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class SqlMapClientDaoAnnotation extends SqlMapClientDaoSupport
{
  @Autowired
  public void setSqlMapClientAnnotation(SqlMapClient sqlMapClient)
  {
    super.setSqlMapClient(sqlMapClient);
  }
}