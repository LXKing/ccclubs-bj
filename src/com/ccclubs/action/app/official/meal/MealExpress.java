package com.ccclubs.action.app.official.meal;

import java.util.Map;

public class MealExpress {
	
	private String time;
	
	/**
	 * 1:"星期一", 2:"星期二", 3:"星期三", 4:"星期四", 5:"星期五", 6:"星期六", 0:"星期日"
	 */
	private String rule;
	
	private String tag;
	
	/**
	 * 保证金
	 */
	private double margin;
	
	/**
	 * 间隔单位（小时）
	 */
	private int sep;
	
	private Map<String, Object> features;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public <T> T getFeature(String key){
		if(this.features == null)return null;
		return (T)this.features.get(key);
	}

	public Map<String, Object> getFeatures() {
		return features;
	}

	public void setFeatures(Map<String, Object> features) {
		this.features = features;
	}

	public int getSep() {
		return sep;
	}

	public void setSep(int sep) {
		this.sep = sep;
	}

	public double getMargin() {
		return margin;
	}

	public void setMargin(double margin) {
		this.margin = margin;
	}
	
}
