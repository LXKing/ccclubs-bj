package com.ccclubs.action.web;

import java.util.Date;

public class BaseOrderInfo {
	/**
	 * 用车方式，默认为-1（原取原还）
	 */
	private int csoType=-1;
	/**
	 * 地域
	 */
	private Long csoHost = -1l;
	/**
	 * 车型Id
	 */
	private Long csoModel = -1l;
	/**
	 * 网点Id
	 */
	private Long csoOutlets = -1l;
	/**
	 * 网点名称
	 */
	private String csoOutletsName = "";
	/**
	 * 取车区域
	 */
	private Long csoArea = -1l;
	/**
	 * 取车区域名称
	 */
	private String csoAreaName = "";
	/**
	 * 还车网点Id
	 */
	private Long csoOutletsRet = -1l;
	/**
	 * 还车网点名称
	 */
	private String csoOutletsNameRet = "";
	/**
	 * 排序，默认或者价格
	 */
	private String csoSort = "default";
	/**
	 * 是否是宣传车 ，默认 false（全部）
	 */
	private Boolean csoIsAdCar = false;
	/**
	 * 是否启用时间作为筛选条件
	 */
	private Boolean csoIsDate = false;
	/**
	 * 是否启用为春节租车
	 */
	private Boolean csoIsSpring = false;
	/**
	 * 查看 csoAmount 天内的车辆租赁情况
	 */
	private int csoAmount = 2;
	/**
	 * 开始时间
	 */
	private Date csoStart = null;
	/**
	 * 结束时间
	 */
	private Date csoFinish = null;
	/**
	 * 车辆Id
	 */
	private Long csoCar = 0l;
	/**
	 * 使用免费小时
	 */
	private Double csoFreeHours = 0d;
	/**
	 * 保险产品类型
	 */
	private Long csoInsure = 0l;
	
	/**
	 * 一个地理位置的纬度
	 */
	private Double csoLatitude = 0d;
	
	/**
	 * 一个地理位置的经度
	 */
	private Double csoLongitude = 0d;

	/**
	 * 获取车型Id
	 * 
	 * @return
	 */
	public Long getCsoModel() {
		return csoModel;
	}

	/**
	 * 设置车型Id
	 * 
	 * @param csoModel
	 */
	public void setCsoModel(Long csoModel) {
		this.csoModel = csoModel;
	}

	/**
	 * 获取取车网点Id，默认为-1（不限）
	 * 
	 * @return
	 */
	public Long getCsoOutlets() {
		return csoOutlets;
	}

	/**
	 * 设置取车网点Id
	 * 
	 * @param csoOutlets
	 */
	public void setCsoOutlets(Long csoOutlets) {
		this.csoOutlets = csoOutlets;
	}

	/**
	 * 获取取车区域Id ，默认为-1（不限）
	 * 
	 * @return
	 */
	public Long getCsoArea() {
		return csoArea;
	}

	/**
	 * 设置取车区域Id
	 * 
	 * @param csoArea
	 */
	public void setCsoArea(Long csoArea) {
		this.csoArea = csoArea;
	}

	/**
	 * 获取所选车辆Id
	 * 
	 * @return
	 */
	public Long getCsoCar() {
		return csoCar;
	}

	/**
	 * 设置所选车辆Id
	 * 
	 * @param csoCar
	 */
	public void setCsoCar(Long csoCar) {
		this.csoCar = csoCar;
	}

	/**
	 * 获取所选车辆是否为广告车
	 * 
	 * @return
	 */
	public Boolean getCsoIsAdCar() {
		return csoIsAdCar;
	}

	/**
	 * 设置所选车辆是否为广告车
	 * 
	 * @param csoIsAdCar
	 */
	public void setCsoIsAdCar(Boolean csoIsAdCar) {
		this.csoIsAdCar = csoIsAdCar;
	}

	/**
	 * 获取定单开始时间
	 * 
	 * @return
	 */
	public Date getCsoStart() {
		return csoStart;
	}

	/**
	 * 设置定单开始时间
	 * 
	 * @param csoStart
	 */
	public void setCsoStart(Date csoStart) {
		this.csoStart = csoStart;
	}

	/**
	 * 获取定单结束时间
	 * 
	 * @return
	 */
	public Date getCsoFinish() {
		return csoFinish;
	}

	/**
	 * 设置定单结束时间
	 * 
	 * @param csoFinish
	 */
	public void setCsoFinish(Date csoFinish) {
		this.csoFinish = csoFinish;
	}
	
	public Double getCsoFreeHours() {
		return csoFreeHours;
	}

	public void setCsoFreeHours(Double csoFreeHours) {
		this.csoFreeHours = csoFreeHours;
	}

	public Long getCsoInsure() {
		return csoInsure;
	}

	public void setCsoInsure(Long csoInsure) {
		this.csoInsure = csoInsure;
	}

	public String getCsoSort() {
		return csoSort;
	}

	public void setCsoSort(String csoSort) {
		this.csoSort = csoSort;
	}

	public Boolean getCsoIsDate() {
		return csoIsDate;
	}

	public void setCsoIsDate(Boolean csoIsDate) {
		this.csoIsDate = csoIsDate;
	}

	public int getCsoAmount() {
		return csoAmount;
	}

	public void setCsoAmount(int csoAmount) {
		this.csoAmount = csoAmount;
	}

	public Boolean getCsoIsSpring() {
		return csoIsSpring;
	}

	public void setCsoIsSpring(Boolean csoIsSpring) {
		this.csoIsSpring = csoIsSpring;
	}

	public Double getCsoLatitude() {
		return csoLatitude;
	}

	public void setCsoLatitude(Double csoLatitude) {
		this.csoLatitude = csoLatitude;
	}

	public Double getCsoLongitude() {
		return csoLongitude;
	}

	public void setCsoLongitude(Double csoLongitude) {
		this.csoLongitude = csoLongitude;
	}

	public Long getCsoHost() {
		return csoHost;
	}

	public void setCsoHost(Long csoHost) {
		this.csoHost = csoHost;
	}

	public String getCsoAreaName() {
		return csoAreaName;
	}

	public void setCsoAreaName(String csoAreaName) {
		this.csoAreaName = csoAreaName;
	}
	public String getCsoOutletsName() {
		return csoOutletsName;
	}
	public void setCsoOutletsName(String csoOutletsName) {
		this.csoOutletsName = csoOutletsName;
	}

	public int getCsoType() {
		return csoType;
	}

	public void setCsoType(int csoType) {
		this.csoType = csoType;
	}

	public Long getCsoOutletsRet() {
		return csoOutletsRet;
	}

	public void setCsoOutletsRet(Long csoOutletsRet) {
		this.csoOutletsRet = csoOutletsRet;
	}

	public String getCsoOutletsNameRet() {
		return csoOutletsNameRet;
	}

	public void setCsoOutletsNameRet(String csoOutletsNameRet) {
		this.csoOutletsNameRet = csoOutletsNameRet;
	}
}
