package com.ccclubs.action.api.chargedot.teld;

/**
 * 
 * 特来电充电订单查询
 * 
 * @author Joel
 *
 */
public class TeldChargeRecordQuery {
	
	/**
	 * 开始时间
	 */
	private String beginTime;
	
	/**
	 * 结束时间
	 */
	private String endTime;
	
	/**
	 * 电站编号(只能为数字)
	 */
	private String staCode;
	
	/**
	 * 终端编号(只能为数字)
	 */
	private String pileCode;
	
	/**
	 * 订单号
	 */
	private String chargeCode;
	
	/**
	 * 外部订单号
	 */
	private String openChargeCode;
	
	/**
	 * 车牌号
	 */
	private String carLicense;

	/**
	 * 卡号
	 */
	private String cardCode;
	
	/**
	 * 当前页码：(可以不传默认为1,最大为500)
	 */
	private String pageNo;
	
	/**
	 * 每页条数：(可以不传默认为100,最大为500)
	 */
	private String pageSize;

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStaCode() {
		return staCode;
	}

	public void setStaCode(String staCode) {
		this.staCode = staCode;
	}

	public String getPileCode() {
		return pileCode;
	}

	public void setPileCode(String pileCode) {
		this.pileCode = pileCode;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public String getOpenChargeCode() {
		return openChargeCode;
	}

	public void setOpenChargeCode(String openChargeCode) {
		this.openChargeCode = openChargeCode;
	}

	public String getCarLicense() {
		return carLicense;
	}

	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
}
