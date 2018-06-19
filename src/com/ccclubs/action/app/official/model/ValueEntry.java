package com.ccclubs.action.app.official.model;

public class ValueEntry {
	
	private Object val;
	
	private String encode;
	
	public ValueEntry(Object val){
		this.val = val;
	}
	
	public ValueEntry(Object val, String encode){
		this.val = val;
		this.encode = encode;
	}

	public Object getVal() {
		return val;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public void setVal(Object val) {
		this.val = val;
	}

}
