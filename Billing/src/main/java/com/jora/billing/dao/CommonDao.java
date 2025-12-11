package com.jora.billing.dao;

public interface CommonDao {

	boolean chkExisting(String table, String column, String value, String dataType) throws Exception;

}
