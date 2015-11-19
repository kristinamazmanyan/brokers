package com.brokerexam.repository;

import java.util.HashMap;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

public class DBUtils {

	public static final String id = "id";
	public static final String name = "name";
	public static final String description = "description";
	public static final String created_at = "created_at";
	public static final String changed_by = "changed_by";
	public static final String changed_at = "changed_at";


	private static final String SELECT_MAX_INDEX_PREFIX = "select max(id) from ";

    public static synchronized long getNextIndex(JdbcTemplate jdbcTemplate,
			String tableName) {
		return (jdbcTemplate.queryForObject(SELECT_MAX_INDEX_PREFIX + tableName, Integer.class) + 1);
	}

	public static synchronized long getNextId(JdbcTemplate jdbcTemplate,
			String tableName) {
		return (jdbcTemplate.queryForObject(SELECT_MAX_INDEX_PREFIX + tableName, Integer.class) + 1);
	}

	public static synchronized int getNextIndex(JdbcTemplate jdbcTemplate,
			String tableName, String column) {
		return (jdbcTemplate.queryForObject("select max(" + column + ") from "
                + tableName, Integer.class) + 1);
	}

	public static void callStoredProcedure(
			NamedParameterJdbcTemplate parameterJdbcTemplate, String name,
			Object... params) {
		StringBuffer sql = new StringBuffer();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();

		sql.append("CALL ").append(name).append("(");

		for (int i = 0; i < params.length; i++) {
			String pname = "p" + i;
			Object pvalue = params[i];

			sql.append(":").append(pname);
			if (i + 1 < params.length) {
				sql.append(", ");
			}

			paramMap.put(pname, pvalue);
		}
		sql.append(")");

		parameterJdbcTemplate.update(sql.toString(), paramMap);
	}

	public static void rollBackCurrentTransaction() {
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	}
}
