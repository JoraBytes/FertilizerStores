package com.jora.billing.service;

import java.util.List;
import java.util.Map;

public interface HsnBasicsService {

	int save(Map<String, Object> saveMap) throws Exception;

	List<Map<String, Object>> viewHsnBasics() throws Exception;

}
