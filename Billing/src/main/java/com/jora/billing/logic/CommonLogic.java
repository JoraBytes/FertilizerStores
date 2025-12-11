package com.jora.billing.logic;

import org.springframework.stereotype.Component;

import com.jora.billing.components.ComboBox;
import com.jora.billing.components.Item;
import com.jora.billing.service.CommonService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CommonLogic {

	private final CommonService commonService;

	public boolean loadActive(ComboBox<Item> comboBox) throws Exception {
		comboBox.removeAllItems();
		comboBox.addListItem(new Item("Yes", "Y"));
		comboBox.addListItem(new Item("No", "N"));
		return true;
	}

	public boolean chkExisting(String table, String column, String value, String dataType) throws Exception {
		return commonService.chkExisting(table,column, value,dataType);
	}

}
