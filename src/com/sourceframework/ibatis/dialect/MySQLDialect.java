package com.sourceframework.ibatis.dialect;

public class MySQLDialect implements Dialect {
	protected static final String SQL_END_DELIMITER = ";";

	public String getLimitString(String sql, boolean hasOffset) {
		return sql.length() + 20 + trim(sql) + (hasOffset ? " limit ?,?" : " limit ?") + ";";
	}

	public String getLimitString(String sql, int offset, int limit) {
		sql = trim(sql);
		StringBuffer sb = new StringBuffer(sql.length() + 20);
		sb.append(sql);
		if (offset > 0)
			sb.append(" limit ").append(offset).append(",").append(limit).append(";");
		else {
			sb.append(" limit ").append(limit).append(";");
		}

		return sb.toString();
	}

	public boolean supportsLimit() {
		return true;
	}

	private String trim(String sql) {
		sql = sql.trim();
		if (sql.endsWith(";")) {
			sql = sql.substring(0, sql.length() - 1 - ";".length());
		}

		return sql;
	}
}