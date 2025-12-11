package com.jora.billing.serviceimpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jora.billing.dao.HsnBasicsDao;
import com.jora.billing.service.HsnBasicsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HsnBasicsServiceImpl implements HsnBasicsService {

	private final HsnBasicsDao hsnBasicsDao;

	@Override
	public int save(Map<String, Object> saveMap) throws Exception {

		saveMap.put("createddate", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		saveMap.put("createdtime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		return hsnBasicsDao.save(saveMap);
	}

	@Override
	public List<Map<String, Object>> viewHsnBasics() throws Exception {

		return hsnBasicsDao.viewHsnBasics();
	}

}
