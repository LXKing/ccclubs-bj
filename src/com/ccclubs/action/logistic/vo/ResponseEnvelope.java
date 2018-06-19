package com.ccclubs.action.logistic.vo;

import java.util.ArrayList;
import java.util.List;

public class ResponseEnvelope<T> {
	private boolean success = true;
	private String message = "ok";
	private int code;
	private Object data;
	private long totalSize;
	private boolean state = true;
	private List<T> list;

	public ResponseEnvelope() {
		this.totalSize = 0;
		this.list = new ArrayList<T>();
	}
	public ResponseEnvelope(Boolean state, int code, String message) {
		this.state = state;
		this.code = code;
		this.message = message;
	}
	
	public long getTotalSize() {
		return totalSize;
	}
	public ResponseEnvelope<T> setTotalSize(long totalSize) {
		this.totalSize = totalSize;
		return this;
	}
	public List<T> getList() {
		return list;
	}
	public ResponseEnvelope<T> setList(List<T> list) {
		this.list = list;
		return this;
	}
	public boolean isSuccess() {
		return success;
	}
	public ResponseEnvelope<T> setSuccess(boolean success) {
		this.success = success;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public ResponseEnvelope<T> setMessage(String message) {
		this.message = message;
		return this;
	}
	public boolean isState() {
		return state;
	}
	public ResponseEnvelope<T> setState(boolean state) {
		this.state = state;
		return this;
	}
	public Object getData() {
		return data;
	}
	public ResponseEnvelope<T> setData(Object data) {
		this.data = data;
		return this;
	}
	public int getCode() {
		return code;
	}
	public ResponseEnvelope<T> setCode(int code) {
		this.code = code;
		return this;
	}
}
