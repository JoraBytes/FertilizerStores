package com.jora.billing.daoimpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jora.billing.dao.HsnBasicsDao;
import com.jora.billing.query.HsnBasicsQuery;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HsnBasicsDaoImpl implements HsnBasicsDao {

	private final HsnBasicsQuery hsnBasicsQuery;

	private final HikariDataSource directoryDataSource;

	@Override
	public int save(Map<String, Object> saveMap) throws Exception {
		try {
			return new NamedParameterJdbcTemplate(directoryDataSource).update(hsnBasicsQuery.save(), saveMap);
		} catch (Exception e) {
			if (e.getCause() instanceof SQLException) {
				throw new Exception(e.getCause().getMessage());
			}
			throw e;
		}
	}

	@Override
	public List<Map<String, Object>> viewHsnBasics() throws Exception {
		try {
			return new JdbcTemplate(directoryDataSource).queryForList(hsnBasicsQuery.viewHsnBasics());
		} catch (Exception e) {
			if (e.getCause() instanceof SQLException) {
				throw new Exception(e.getCause().getMessage());
			}
			throw e;
		}
	}

}
