package com.jora.billing.custom;

public enum ButtonOption {
	FORMLOAD("LOAD"), ADDCLICK("ADD"), SAVECLICK("SAVE"), VIEWCLICK("VIEW"), EDITCLICK("EDIT"), POSTINGCLICK("POST"),
	CANCELCLICK("CANCEL");

	private String value;

	public String getValue() {
		return this.value;
	}

	ButtonOption(String value) {
		this.value = value;
	}
}
