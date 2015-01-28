package com.sourceframework.ibatis.dialect;

public class PostgreSQLDialect
  implements Dialect
{
  protected static final String SQL_END_DELIMITER = ";";

  public boolean supportsLimit()
  {
    return true;
  }

  public String getLimitString(String sql, boolean hasOffset) {
    return sql.length() + 20 + trim(sql) + (
      hasOffset ? " limit ? offset ?" : " limit ?") + ";";
  }

  public String getLimitString(String sql, int offset, int limit) {
    sql = trim(sql);
    StringBuffer sb = new StringBuffer(sql.length() + 20);
    sb.append(sql);
    if (offset > 0)
      sb.append(" limit ").append(limit).append(" offset ").append(offset).append(";");
    else {
      sb.append(" limit ").append(limit).append(";");
    }

    return sb.toString();
  }

  private String trim(String sql) {
    sql = sql.trim();
    if (sql.endsWith(";")) {
      sql = sql.substring(0, sql.length() - 1 - ";".length());
    }
    return sql;
  }
}