package com.ccclubs.vo;

import java.util.Date;
import java.util.Map;

public class MapState {
	
	private Long cssHost;
	private Date cssAddTime;
	private String cssEvBattery;
	private String cssLongitude;
	private String cssLatitude;
	private String cssNumber;
	private Long cssOrder;
	private String cssPower;
	private String cssCharging;
	private Map<String, Object> values;
	
	public Long getCssHost() {
		return cssHost;
	}
	public void setCssHost(Long cssHost) {
		this.cssHost = cssHost;
	}
	
	public Date getCssAddTime() {
		return cssAddTime;
	}
	public void setCssAddTime(Date cssAddTime) {
		this.cssAddTime = cssAddTime;
	}
	public String getCssEvBattery() {
		return cssEvBattery;
	}
	public void setCssEvBattery(String cssEvBattery) {
		this.cssEvBattery = cssEvBattery;
	}
	public String getCssLongitude() {
		return cssLongitude;
	}
	public void setCssLongitude(String cssLongitude) {
		this.cssLongitude = cssLongitude;
	}
	public String getCssLatitude() {
		return cssLatitude;
	}
	public void setCssLatitude(String cssLatitude) {
		this.cssLatitude = cssLatitude;
	}
	public String getCssNumber() {
		return cssNumber;
	}
	public void setCssNumber(String cssNumber) {
		this.cssNumber = cssNumber;
	}
	public Long getCssOrder() {
		return cssOrder;
	}
	public void setCssOrder(Long cssOrder) {
		this.cssOrder = cssOrder;
	}
	public String getCssPower() {
		return cssPower;
	}
	public void setCssPower(String cssPower) {
		this.cssPower = cssPower;
	}
	public String getCssCharging() {
		return cssCharging;
	}
	public void setCssCharging(String cssCharging) {
		this.cssCharging = cssCharging;
	}
	public Map<String, Object> getValues() {
		return values;
	}
	public void setValues(Map<String, Object> values) {
		this.values = values;
	}
	
}
