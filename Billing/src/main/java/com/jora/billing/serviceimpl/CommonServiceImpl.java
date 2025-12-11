package com.jora.billing.serviceimpl;

import org.springframework.stereotype.Service;

import com.jora.billing.dao.CommonDao;
import com.jora.billing.service.CommonService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService {

	private final CommonDao commonDao;

	@Override
	public boolean chkExisting(String table, String column, String value, String dataType) throws Exception {

		return commonDao.chkExisting(table, column, value, dataType);
	}

}
