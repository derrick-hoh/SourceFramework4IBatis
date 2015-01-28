package com.sourceframework.ibatis.ext;

import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
import com.ibatis.sqlmap.engine.scope.RequestScope;
import com.sourceframework.ibatis.dialect.Dialect;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class LimitSqlExecutor extends SqlExecutor
{
  private static final Logger logger = Logger.getLogger(LimitSqlExecutor.class);
  private Dialect dialect;
  private boolean enableLimit = true;

  public Dialect getDialect() {
    return this.dialect;
  }

  @Autowired
  public void setDialect(Dialect dialect) {
    this.dialect = dialect;
  }

  public boolean isEnableLimit() {
    return this.enableLimit;
  }

  public void setEnableLimit(boolean enableLimit) {
    this.enableLimit = enableLimit;
  }

  public void executeQuery(RequestScope request, Connection conn, String sql, Object[] parameters, int skipResults, int maxResults, RowHandlerCallback callback)
    throws SQLException
  {
    if (((skipResults != 0) || (maxResults != -999999)) && (supportsLimit())) {
      sql = this.dialect.getLimitString(sql, skipResults, maxResults);

      if (logger.isDebugEnabled()) {
        logger.debug(sql);
      }

      skipResults = 0;
      maxResults = -999999;
    }

    super.executeQuery(request, conn, sql, parameters, skipResults, maxResults, 
      callback);
  }

  public boolean supportsLimit() {
    if ((this.enableLimit) && (this.dialect != null)) {
      return this.dialect.supportsLimit();
    }

    return false;
  }
}