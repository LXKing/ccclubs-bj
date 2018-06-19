package com.ccclubs.exception;

public enum ErrorCode {
	
	 SYSTEM_ERROR(100,"系统繁忙"),
	
	ORDER_CONFLICT(101,"订单冲突"), 
	ORDER_NO_REFUND(102,"退款会员不能下单"), 
	ORDER_A_B_OUTLETS_ERROR(103,"A借B还条件不通过"), 
	ORDER_MEMBERINFO_ERROR(104,"资料未通过不能下单"), 
	ORDER_MEMBER_DISABLE(105,"帐号禁用不能下单"), 
	ORDER_NO_EV_CARD(106,"未绑定EV卡不能下单"), 
	ORDER_CREDIT_UN_REPAY(107,"信用卡未结算"), 
	ORDER_MONEY_LESS(108,"会员余额不足"), 
	ORDER_STATUS_ERROR(109,"订单状态错误"),
	ORDER_TIME_ERROR(110,"订单时间错误"), 
	ORDER_COUNT_LIMIT(111,"订单次数限制"), 
	ORDER_TIME_LIMIT(112,"订单时间限制"), 
	ORDER_CREDIT_BAD_PAY(113,"信用卡坏帐"),
	ORDER_REPEAT_SETTLE(114,"订单时间错误"), 
	ORDER_FLAG_ERROR(115,"订单类型标识错误"),
	
	MONEY_RECORD_TYPE_ERROR(121,"消费类型错误"), 
	MONEY_LESS(122,"用户余额不足"), 
	
	PACKAGE_CANOT_BUY(131,"套餐不能购买"), 
	PACKAGE_CANOT_REPEAT(132,"套餐不能重复购买"), 
	
	ORDER_CANOT_ROLLBACK(132,"订单不能回滚"),
	
	ORDER_CANOT_CANCEL(133,"只有已预订未使用的订单才能取消"),
	;
	public int code;
	public String message;
	ErrorCode(int code,String message){
		this.code = code;
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
