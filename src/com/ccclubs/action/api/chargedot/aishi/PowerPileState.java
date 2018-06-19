package com.ccclubs.action.api.chargedot.aishi;

/**
 * 
 * 电桩状态
 * 
 * @author zhangjian
 *
 */
public class PowerPileState {
	
	/**
	 * 	
	 * 充电电量
	 */
	private String Did;
	
	/**
	 * 电桩状态
	 */
	private Integer Status;
	
	/**
	 * 	
	 * 充电电量
	 */
	private String CompanyId;
	
	/**
	 * 	
	 * 充电电量
	 */
	private String EleAmount;
	
	/**
	 * 	
	 * 充电电量
	 */
	private String TimeLong;
	/**
	 * 	
	 * 充电电量
	 */
	private String ErrorCode;
	/**
	 * 	
	 * 充电电量
	 */
	private String LastCreateTime;

	public String getDid() {
		return Did;
	}

	public void setDid(String did) {
		Did = did;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public String getCompanyId() {
		return CompanyId;
	}

	public void setCompanyId(String companyId) {
		CompanyId = companyId;
	}

	public String getEleAmount() {
		return EleAmount;
	}

	public void setEleAmount(String eleAmount) {
		EleAmount = eleAmount;
	}

	public String getTimeLong() {
		return TimeLong;
	}

	public void setTimeLong(String timeLong) {
		TimeLong = timeLong;
	}

	public String getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}

	public String getLastCreateTime() {
		return LastCreateTime;
	}

	public void setLastCreateTime(String lastCreateTime) {
		LastCreateTime = lastCreateTime;
	}
	
}
