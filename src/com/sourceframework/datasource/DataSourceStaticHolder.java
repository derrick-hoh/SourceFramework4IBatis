package com.sourceframework.datasource;

import javax.sql.DataSource;

public final class DataSourceStaticHolder
{
  private static DataSource dataSource;

  public static void setDataSource(DataSource dataSource)
  {
    DataSourceStaticHolder.dataSource = dataSource;
  }

  public static DataSource getDataSource() {
    return dataSource;
  }
}