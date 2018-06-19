package com.ccclubs.action.app.official.util;

public enum Reason {
	
	距离订单开始时间小于半个小时的仍未审核的发送短信(""),
	
	取消叫车订单发送短信(""),
	
	单用户远程操作间隔十秒钟(""),
	
	距离订单开始时间小于2小时的给司机推送消息(""),
	
	购买现金券产品(""),
	
	支付宝充值回调(""),
	
	APP端发起的支付宝充值回调("")
	;
	
	private String eventKey;
	
	 private Reason(String eventKey){
		this.eventKey = eventKey;
	}
	 
	public Reason set(Object eventKey){
		this.eventKey = eventKey+"";
		return this;
	}
	 
	public String get(){
		if(eventKey == null || eventKey.equals("")){
			throw new RuntimeException("eventKey can't null!");
		}
		return this.name()+this.eventKey; 
	}
	
}
