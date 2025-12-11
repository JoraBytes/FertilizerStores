package com.jora.billing.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jora.billing.common.CommonValues;
import com.jora.billing.components.Table;
import com.jora.billing.service.OperatorService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OperatorLogic {

	private final OperatorService operatorService;

	public int save(Map<String, Object> saveMap) throws Exception {
		return operatorService.save(saveMap);
	}

	public int viewOperators(Table tblView) throws Exception {
		List<Map<String, Object>> lstData = operatorService.viewOperators();
		if (lstData.isEmpty())
			throw new Exception("No data found...");
		tblView.clear();
		for (Map<String, Object> data : lstData) {
			List<Object> lstRow = new ArrayList<>();
			lstRow.add(data.get("operatorcode"));
			lstRow.add(data.get("operatorname"));
			lstRow.add(CommonValues.selectedYesOrNo("Y".equals(data.get("entrymode").toString())));
			lstRow.add(CommonValues.selectedYesOrNo("Y".equals(data.get("editmode").toString())));
			lstRow.add(CommonValues.selectedYesOrNo("Y".equals(data.get("viewmode").toString())));
			lstRow.add(CommonValues.selectedYesOrNo("Y".equals(data.get("deletemode").toString())));
			lstRow.add(CommonValues.selectedYesOrNo("Y".equals(data.get("cancelaccess").toString())));
			lstRow.add(CommonValues.selectedYesOrNo("Y".equals(data.get("rateentry").toString())));
			lstRow.add(CommonValues.selectedYesOrNo("Y".equals(data.get("prouser").toString())));
			lstRow.add(CommonValues.selectedYesOrNo("Y".equals(data.get("previousdateentry").toString())));
			lstRow.add(data.get("companytag"));
			lstRow.add(data.get("companyflag"));
			lstRow.add(CommonValues.selectedYesOrNo("Y".equals(data.get("active").toString())));
			lstRow.add(data);
			tblView.addRow(lstRow);
		}
		return lstData.size();
	}

	public int update(Map<String, Object> saveMap) throws Exception {
		return operatorService.update(saveMap);
	}
}
