package com.ccclubs.action.logistic.vo;

public class Online {
	private String id;//上下线id
	private String oper;//操作名称
	private String oper_user;//操作人
	private String oper_time;//操作时间
	private String remark;//操作备注
	private String tag;//原因标签
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public String getOper_time() {
		return oper_time;
	}
	public void setOper_time(String oper_time) {
		this.oper_time = oper_time;
	}
	public String getOper_user() {
		return oper_user;
	}
	public void setOper_user(String oper_user) {
		this.oper_user = oper_user;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
}
