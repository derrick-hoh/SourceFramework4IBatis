package com.sourceframework.ibatis.ext;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.sourceframework.datasource.DataSourceStaticHolder;
import com.sourceframework.utils.ReflectionUtils;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.core.io.Resource;
import org.springframework.orm.ibatis.SqlMapClientFactoryBean;

public class SqlMapClientDecoratorFactoryBean extends SqlMapClientFactoryBean
{
  private SqlExecutor sqlExecutor;

  public void setSqlExecutor(SqlExecutor sqlExecutor)
  {
    this.sqlExecutor = sqlExecutor;
  }

  public void setDataSource(DataSource dataSource)
  {
    super.setDataSource(dataSource);
    DataSourceStaticHolder.setDataSource(dataSource);
  }

  protected SqlMapClient buildSqlMapClient(Resource[] configLocations, Resource[] mappingLocations, Properties properties)
    throws IOException
  {
    SqlMapClient sqlMapClient = super.buildSqlMapClient(configLocations, mappingLocations, properties);

    if ((sqlMapClient instanceof ExtendedSqlMapClient)) {
      ReflectionUtils.setFieldValue(((ExtendedSqlMapClient)sqlMapClient).getDelegate(), "sqlExecutor", this.sqlExecutor);
    }

    return sqlMapClient;
  }
}