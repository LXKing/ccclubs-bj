package com.ccclubs.entity;

public class Message {
		
	String type;//类型
	Object value;//数据
	
	/**
	 * @param type
	 * @param data
	 */
	public Message(String type, Object value) {
		super();
		this.type = type;
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
}
