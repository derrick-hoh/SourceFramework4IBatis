package com.sourceframework.dao;

import java.sql.SQLException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ibatis.SqlMapClientCallback;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.sourceframework.ibatis.ext.SqlMapClientDaoAnnotation;

public class SourceIBatisDao extends SqlMapClientDaoAnnotation {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public <V> V get(String queryName) {
		return get(queryName, null);
	}

	@SuppressWarnings("unchecked")
	public <V, P> V get(String queryName, P p) {
		return (V) getSqlMapClientTemplate().queryForObject(queryName, p);
	}

	public <V> Collection<V> getAll(String queryName) {
		return getAll(queryName, null);
	}

	@SuppressWarnings("unchecked")
	public <V, P> Collection<V> getAll(String queryName, P queryCondition) {
		return getSqlMapClientTemplate().queryForList(queryName, queryCondition);
	}

	@SuppressWarnings("unchecked")
	public <V, P> Collection<V> getAll(String queryName, P queryCondition,
			int start, int limit) {
		return getSqlMapClientTemplate().queryForList(queryName,queryCondition, start, limit);
	}

	public int delete(String deleteName) {
		return delete(deleteName, null);
	}

	public <V> int delete(String deleteName, V v) {
		if (v == null) {
			return getSqlMapClientTemplate().delete(deleteName);
		}
		return getSqlMapClientTemplate().delete(deleteName, v);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <V> int delete(final String deleteName, final Collection<V> vs) {
		return ((Integer) getSqlMapClientTemplate().execute(
				new SqlMapClientCallback() {
					public Integer doInSqlMapClient(SqlMapExecutor executor)
							throws SQLException {
						executor.startBatch();
						for (Object v : vs) {
							executor.delete(deleteName, v);
						}
						return Integer.valueOf(executor.executeBatch());
					}
				})).intValue();
	}

	public void insert(String insertName) {
		insert(insertName, null);
	}

	public <V> void insert(String insertName, V v) {
		if (v == null)
			getSqlMapClientTemplate().insert(insertName);
		else
			getSqlMapClientTemplate().insert(insertName, v);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <V> void insert(final String insertName, final Collection<V> vs) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Integer doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Object v : vs) {
					executor.insert(insertName, v);
				}
				return Integer.valueOf(executor.executeBatch());
			}
		});
	}

	public int update(String updateName) {
		return update(updateName, null);
	}

	public <V> int update(String updateName, V v) {
		if (v == null) {
			return getSqlMapClientTemplate().update(updateName);
		}
		return getSqlMapClientTemplate().update(updateName, v);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <V> int update(final String updateName, final Collection<V> vs) {
		return ((Integer) getSqlMapClientTemplate().execute(
				new SqlMapClientCallback() {
					public Integer doInSqlMapClient(SqlMapExecutor executor)
							throws SQLException {
						executor.startBatch();
						for (Object v : vs) {
							executor.update(updateName, v);
						}
						return Integer.valueOf(executor.executeBatch());
					}
				})).intValue();
	}
}