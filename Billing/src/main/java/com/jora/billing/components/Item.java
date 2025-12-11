package com.jora.billing.components;

public class Item {
	public Object key;
	public Object value;
	public boolean checked;

	public Item(Object key, Object value) {
		this.key = key;
		this.value = value;
	}

	public String toString() {
		return key + " : " + value;
	}

	public Object getKey() {
		return this.key;
	}

	public Object getValue() {
		return this.value;
	}
}