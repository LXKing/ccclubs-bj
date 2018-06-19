package com.ccclubs.action.logistic.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ccclubs.util.DateUtil;

public class JobView {
	
	private String datestr;
	
	private List<Map<String, Object>> datas;
	
	DateUtil du = new DateUtil();

	public Date getDate(){
		return du.StringtoDate(datestr, "yyyy-MM-dd");
	}


	public String getDatestr() {
		return datestr;
	}

	public void setDatestr(String datestr) {
		this.datestr = datestr;
	}

	public List<Map<String, Object>> getDatas() {
		return datas;
	}

	public void setDatas(List<Map<String, Object>> datas) {
		this.datas = datas;
	}

}
