package com.sourceframework.datasource;

import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource
{
  protected Object determineCurrentLookupKey()
  {
    return DataSourceContextHolder.getDataSource();
  }

@Override
public Logger getParentLogger() throws SQLFeatureNotSupportedException { 
	return null;
}
}