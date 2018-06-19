package com.ccclubs.action.api.chargedot.icharge;

/**
 * 
 * 电站/电桩
 * 
 * @author Administrator
 *
 */
public class Elec {
	
	private String electricId;
	
	private String electricType;
	
	private Integer electricState;
	
	private String longitude;
	
	private String latitude;
	
	private String cityCode;

	public String getElectricId() {
		return electricId;
	}

	public void setElectricId(String electricId) {
		this.electricId = electricId;
	}

	public String getElectricType() {
		return electricType;
	}

	public void setElectricType(String electricType) {
		this.electricType = electricType;
	}

	public Integer getElectricState() {
		return electricState;
	}

	public void setElectricState(Integer electricState) {
		this.electricState = electricState;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	
	
}
