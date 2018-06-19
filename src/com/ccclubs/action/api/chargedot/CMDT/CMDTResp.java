package com.ccclubs.action.api.chargedot.CMDT;

import java.util.List;

public class CMDTResp {
	
	private boolean success;
	
	private String failureMsg;
	
	private List<CMDTBill> data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getFailureMsg() {
		return failureMsg;
	}

	public void setFailureMsg(String failureMsg) {
		this.failureMsg = failureMsg;
	}

	public List<CMDTBill> getData() {
		return data;
	}

	public void setData(List<CMDTBill> data) {
		this.data = data;
	}
	
}
