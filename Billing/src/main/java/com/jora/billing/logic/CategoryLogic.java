package com.jora.billing.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jora.billing.components.ComboBox;
import com.jora.billing.components.Item;

@Component
public class CategoryLogic {

	
	public void loadHsnBasics(ComboBox<Item> cmbHsnCode) throws Exception {
		cmbHsnCode.removeAllItems();
		List<Map<String, Object>> lstHsnCode = new ArrayList<Map<String,Object>>();

		if (lstHsnCode.isEmpty()) {
			throw new Exception("No Active HsnBasics found!..");
		}

		for (Map<String, Object> map : lstHsnCode) {
			cmbHsnCode.addListItem(new Item(map.get("hsnname").toString() + " ~ " + map.get("hsncode").toString(),
					map.get("hsncode").toString()));
		}

	}
}
