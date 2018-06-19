package com.ccclubs.action.web;

public class VehicleJsonFormat {
	/**
	 * 车辆Id
	 */
	private long cscId = 0l;
	/**
	 * 车牌号
	 */
	private String cscNumber = null;
	/**
	 * 是否是宣传车
	 */
	private int cscIsAd = 0;
	/**
	 * 车型Id
	 */
	private long cscmId = 0l;
	/**
	 * 车型描述
	 */
	private String cscmName = null;
	/**
	 * 天租价格
	 */
	private double pricesDay = 0;
	/**
	 * 小时价格
	 */
	private double pricesHour = 0;
	
	public long getCscId() {
		return cscId;
	}
	public void setCscId(long cscId) {
		this.cscId = cscId;
	}
	public String getCscNumber() {
		return cscNumber;
	}
	public void setCscNumber(String cscNumber) {
		this.cscNumber = cscNumber;
	}
	public int getCscIsAd() {
		return cscIsAd;
	}
	public void setCscIsAd(int cscIsAd) {
		this.cscIsAd = cscIsAd;
	}
	public long getCscmId() {
		return cscmId;
	}
	public void setCscmId(long cscmId) {
		this.cscmId = cscmId;
	}
	public String getCscmName() {
		return cscmName;
	}
	public void setCscmName(String cscmName) {
		this.cscmName = cscmName;
	}
	public double getPricesDay() {
		return pricesDay;
	}
	public void setPricesDay(double pricesDay) {
		this.pricesDay = pricesDay;
	}
	public double getPricesHour() {
		return pricesHour;
	}
	public void setPricesHour(double pricesHour) {
		this.pricesHour = pricesHour;
	}
}
