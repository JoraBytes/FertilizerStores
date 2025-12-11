package com.jora.billing.service;

import java.util.List;
import java.util.Map;

public interface OperatorService {

	int save(Map<String, Object> saveMap) throws Exception;

	List<Map<String, Object>> viewOperators() throws Exception;

	int update(Map<String, Object> saveMap) throws Exception;

}
