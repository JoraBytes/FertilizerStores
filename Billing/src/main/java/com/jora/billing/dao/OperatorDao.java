package com.jora.billing.dao;

import java.util.List;
import java.util.Map;

public interface OperatorDao {

	int save(Map<String, Object> saveMap) throws Exception;

	List<Map<String, Object>> view() throws Exception;

	int update(Map<String, Object> saveMap) throws Exception;

}
