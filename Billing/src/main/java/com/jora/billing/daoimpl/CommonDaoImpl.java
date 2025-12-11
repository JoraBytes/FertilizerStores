package com.jora.billing.daoimpl;

import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jora.billing.dao.CommonDao;
import com.jora.billing.query.CommonQuery;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommonDaoImpl implements CommonDao {

	private final CommonQuery commonQuery;

	private final HikariDataSource directoryDataSource;

	@Override
	public boolean chkExisting(String table, String column, String value, String dataType) throws Exception {
		try {
			if (dataType.equalsIgnoreCase("String")) {
				new JdbcTemplate(directoryDataSource).queryForObject(commonQuery.chkExisting(table, column),
						String.class, value);
			} else if (dataType.equalsIgnoreCase("int")) {
				new JdbcTemplate(directoryDataSource).queryForObject(commonQuery.chkExisting(table, column),
						Integer.class, Integer.parseInt(value));
			} else if (dataType.equalsIgnoreCase("Double")) {
				new JdbcTemplate(directoryDataSource).queryForObject(commonQuery.chkExisting(table, column),
						Double.class, Double.parseDouble(value));
			}
			return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		} catch (Exception e) {
			if (e.getCause() instanceof SQLException) {
				throw new Exception(e.getCause().getMessage());
			}
			throw e;
		}
	}

}
