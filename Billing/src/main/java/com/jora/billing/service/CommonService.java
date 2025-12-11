package com.jora.billing.service;

public interface CommonService {

	boolean chkExisting(String table, String column, String value, String dataType) throws Exception;

}
