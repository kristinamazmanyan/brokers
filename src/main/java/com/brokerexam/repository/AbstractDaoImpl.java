package com.brokerexam.repository;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class AbstractDaoImpl extends JdbcDaoSupport {

	protected NamedParameterJdbcTemplate parameterJdbcTemplate;

	public AbstractDaoImpl(DataSource dataSource) {
		super();
		setDataSource(dataSource);
		parameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
}
