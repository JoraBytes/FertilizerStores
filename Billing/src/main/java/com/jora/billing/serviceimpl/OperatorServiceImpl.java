package com.jora.billing.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jora.billing.dao.OperatorDao;
import com.jora.billing.service.OperatorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OperatorServiceImpl implements OperatorService {

	private final OperatorDao operatorDao;

	@Override
	public int save(Map<String, Object> saveMap) throws Exception {
		return operatorDao.save(saveMap);
	}

	@Override
	public List<Map<String, Object>> viewOperators() throws Exception {
		return operatorDao.view();
	}

	@Override
	public int update(Map<String, Object> saveMap) throws Exception {
		return operatorDao.update(saveMap);
	}

}
