package com.sourceframework.ibatis.dialect;

public abstract interface Dialect {
	public abstract boolean supportsLimit();

	public abstract String getLimitString(String paramString, boolean paramBoolean);

	public abstract String getLimitString(String paramString, int paramInt1, int paramInt2);
}
