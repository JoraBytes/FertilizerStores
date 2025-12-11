package com.jora.billing.daoimpl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jora.billing.dao.OperatorDao;
import com.jora.billing.query.OperatorQuery;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OperatorDaoImpl implements OperatorDao {

	private final HikariDataSource directoryDataSource;

	private final OperatorQuery operatorQuery;

	@Override
	public int save(Map<String, Object> saveMap) throws Exception {
		return new NamedParameterJdbcTemplate(directoryDataSource).update(operatorQuery.save(), saveMap);
	}

	@Override
	public List<Map<String, Object>> view() throws Exception {
		return new JdbcTemplate(directoryDataSource).queryForList(operatorQuery.view());
	}

	@Override
	public int update(Map<String, Object> saveMap) throws Exception {
		return new NamedParameterJdbcTemplate(directoryDataSource).update(operatorQuery.update(), saveMap);
	}
}
