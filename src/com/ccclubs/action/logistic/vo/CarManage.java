package com.ccclubs.action.logistic.vo;

import java.util.Map;

public class CarManage {
	private String csc_id;//车辆id
	private String csc_host;//车辆城市
	private String csc_car_no;//车牌
	private String csc_model;//车型
	private String csc_outlets;//网点
	private String csc_longitude;//经度
	private String csc_latitude;//纬度
	private String csc_status;//上下线状态
	private String csc_images;//车辆图片
	private String css_endurance;//续航
	private String css_charging;//充电状态
	private String csc_use;//使用状态
	private String css_ev_battery;	//电池电量
	private String css_power;//蓄电量
	
	private String model_name;//车型名称
	private String outlet_name;//网点名称
	
	private String order_outlet_name;
	private String order_retoutlet_name;
	
	private Object timeline;
	
	public String getCsc_id() {
		return csc_id;
	}
	public void setCsc_id(String csc_id) {
		this.csc_id = csc_id;
	}
	public String getCsc_host() {
		return csc_host;
	}
	public void setCsc_host(String csc_host) {
		this.csc_host = csc_host;
	}
	public String getCsc_car_no() {
		return csc_car_no;
	}
	public void setCsc_car_no(String csc_car_no) {
		this.csc_car_no = csc_car_no;
	}
	public String getCsc_model() {
		return csc_model;
	}
	public void setCsc_model(String csc_model) {
		this.csc_model = csc_model;
	}
	public String getCsc_outlets() {
		return csc_outlets;
	}
	public void setCsc_outlets(String csc_outlets) {
		this.csc_outlets = csc_outlets;
	}
	public String getCsc_longitude() {
		return csc_longitude;
	}
	public void setCsc_longitude(String csc_longitude) {
		this.csc_longitude = csc_longitude;
	}
	public String getCsc_latitude() {
		return csc_latitude;
	}
	public void setCsc_latitude(String csc_latitude) {
		this.csc_latitude = csc_latitude;
	}
	public String getCsc_status() {
		return csc_status;
	}
	public void setCsc_status(String csc_status) {
		this.csc_status = csc_status;
	}
	public String getCsc_images() {
		return csc_images;
	}
	public void setCsc_images(String csc_images) {
		this.csc_images = csc_images;
	}
	public String getCss_endurance() {
		return css_endurance;
	}
	public void setCss_endurance(String css_endurance) {
		this.css_endurance = css_endurance;
	}
	public String getCss_charging() {
		return css_charging;
	}
	public void setCss_charging(String css_charging) {
		this.css_charging = css_charging;
	}
	public String getCsc_use() {
		return csc_use;
	}
	public void setCsc_use(String csc_use) {
		this.csc_use = csc_use;
	}
	public String getModel_name() {
		return model_name;
	}
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	public String getOutlet_name() {
		return outlet_name;
	}
	public void setOutlet_name(String outlet_name) {
		this.outlet_name = outlet_name;
	}
	public String getOrder_outlet_name() {
		return order_outlet_name;
	}
	public void setOrder_outlet_name(String order_outlet_name) {
		this.order_outlet_name = order_outlet_name;
	}
	public String getOrder_retoutlet_name() {
		return order_retoutlet_name;
	}
	public void setOrder_retoutlet_name(String order_retoutlet_name) {
		this.order_retoutlet_name = order_retoutlet_name;
	}
	public Object getTimeline() {
		return timeline;
	}
	public void setTimeline(Object timeline) {
		this.timeline = timeline;
	}
	public String getCss_ev_battery() {
		return css_ev_battery;
	}
	public void setCss_ev_battery(String css_ev_battery) {
		this.css_ev_battery = css_ev_battery;
	}
	public String getCss_power() {
		return css_power;
	}
	public void setCss_power(String css_power) {
		this.css_power = css_power;
	}
}
