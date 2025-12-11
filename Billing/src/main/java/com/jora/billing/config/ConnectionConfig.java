package com.jora.billing.config;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jora.billing.common.CommonValues;
import com.jora.billing.common.ErrorHandler;
import com.jora.billing.common.Operator;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ConnectionConfig {

	private final BuildProperties buildProperties;

	private final Logger log = LogManager.getLogger(getClass());

	@Bean("directoryDataSource")
	@Primary
	public HikariDataSource getHoMasterConn() throws Exception {
		try {
//			HikariDataSource dataSource = getDataSource(ApplicationConfig.serverName, ApplicationConfig.portNo,
//					ApplicationConfig.password, ApplicationConfig.userName, ApplicationConfig.companyTag + "Directory");
			HikariDataSource dataSource = getDataSource(ApplicationConfig.serverName, ApplicationConfig.portNo,
					ApplicationConfig.password, ApplicationConfig.userName, "abcmaindb");
			try {
				ApplicationConfig.companyFlag = new JdbcTemplate(dataSource).queryForObject(
						"select CompanyFlag from HoDetails where CompanyTag=?", String.class,
						ApplicationConfig.companyTag);
			} catch (EmptyResultDataAccessException e) {
				throw new Exception("CompanyFlag Details not Found...!\nPlease Contact Jora ");
			} catch (IncorrectResultSizeDataAccessException e) {
				throw new Exception("Multiple CompanyFlag Details not Found...!\nPlease Contact Jora ");
			}
			if (ApplicationConfig.companyFlag.equalsIgnoreCase("")) {
				throw new Exception("CompanyFlag Details not Found...!\nPlease Contact Jora ");
			}
			CommonValues.setApplicationName(buildProperties.getName());
			CommonValues.setVersion(buildProperties.getVersion());
			CommonValues.setOperator(Operator.builder().operatorCode(1).operatorName("SUPERVISOR").build());
			return dataSource;
		} catch (Exception e) {
			log.error(ErrorHandler.errorTraceForLogger(e));
			throw e;
		}
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	public HikariDataSource getDataSource(String serverName, String portNo, String password, String userName,
			String dbName) throws Exception {
		HikariConfig config = new HikariConfig();
		StringBuilder url = new StringBuilder();
		url.append("jdbc:mysql://").append(serverName).append(":").append(portNo).append("/").append(dbName);
		url.append("?useSSL=false&allowPublicKeyRetrieval=true&autoReconnect=true&");
		url.append("applicationName=").append(buildProperties.getName());
		config.setJdbcUrl(url.toString());
		config.setUsername(userName);
		config.setPassword(password);
		config.setMaximumPoolSize(5);
		config.setMinimumIdle(1);
		config.setMaxLifetime(TimeUnit.MINUTES.toMillis(30));
		config.setConnectionTimeout(TimeUnit.SECONDS.toMillis(30));
		config.setIdleTimeout(TimeUnit.MINUTES.toMillis(2));
		return new HikariDataSource(config);
	}

}
