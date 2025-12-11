package com.jora.billing.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jora.billing.common.CommonValues;
import com.jora.billing.components.Table;
import com.jora.billing.service.HsnBasicsService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HsnBasicsLogic {

	private final HsnBasicsService hsnBasicsService;

	public int save(Map<String, Object> saveMap) throws Exception {

		return hsnBasicsService.save(saveMap);
	}

	public int viewHsnBasics(Table tblView) throws Exception {
		List<Map<String, Object>> lstData = hsnBasicsService.viewHsnBasics();
		if (lstData.isEmpty())
			throw new Exception("No data found...");
		tblView.clear();
		for (Map<String, Object> data : lstData) {
			List<Object> lstRow = new ArrayList<>();
			lstRow.add(data.get("hsncode"));
			lstRow.add(data.get("hsnname"));

			lstRow.add(data.get("sgst"));
			lstRow.add(data.get("cgst"));
			lstRow.add(data.get("igst"));
			lstRow.add(CommonValues.selectedYesOrNo("Y".equals(data.get("active").toString())));
			lstRow.add(data.get("createdby"));
			lstRow.add(data.get("createddate"));
			lstRow.add(data.get("operatorname"));
			lstRow.add(data.get("modifieddate"));
			lstRow.add(data.get("companytag"));
			lstRow.add(data.get("companyflag"));

			lstRow.add(data);
			tblView.addRow(lstRow);
		}
		return lstData.size();
	}

}
