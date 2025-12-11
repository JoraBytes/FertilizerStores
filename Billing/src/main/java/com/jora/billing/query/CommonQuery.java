package com.jora.billing.query;

import org.springframework.stereotype.Component;

@Component
public class CommonQuery {

	public String chkExisting(String table, String column) {
		StringBuilder builder = new StringBuilder();
		builder.append("select ").append(column).append(" from ").append(table).append(" where ").append(column)
				.append(" =?");
		return builder.toString();
	}

}
