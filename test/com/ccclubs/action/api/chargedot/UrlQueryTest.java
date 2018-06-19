package com.ccclubs.action.api.chargedot;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ccclubs.action.api.chargedot.aishi.PowerPileState;
import com.ccclubs.util.IPAddressUtils;

public class UrlQueryTest {
	
	public static void main(String[] args) {
		UrlQueryTest qt = new UrlQueryTest();
		String result = qt.queryAllState();
		System.out.println(result);
	}
	
	public String queryAllState(){
		IPAddressUtils ipUtil = new IPAddressUtils();
		
		String params = "";
		params += "CustId=cust0001";
		params += "&Password=123456";
		params += "&Company=Com00001";
		//params += "&Dids=ASEV01A11404P0000020";
		
		String result = ipUtil.getRs("http://42.96.191.30:8080/api/GetMonCdsData", params, "UTF-8");
		
//		String result = ipUtil.getRs("http://42.96.191.30:8080/api/GetMonCdsData?CustId=cust0001&Password=123456&Company=Com00001&Dids=ASEV01A11404P0000020", "", "UTF-8");
		JSONObject jsonObj = JSONObject.fromObject(result);
		System.out.println(result);
		System.out.println(jsonObj.get("MonDataList"));
		JSONArray.toArray(JSONArray.fromObject(jsonObj.get("MonDataList")));
		JSONArray jsonArr = JSONArray.fromObject(jsonObj.get("MonDataList"));
		JSONObject o = jsonArr.getJSONObject(0);
		System.out.println(o.toBean(o, PowerPileState.class));
		
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jo = (JSONObject) jsonArr.get(i);
			System.out.println(jo.get("name"));
		}
		
		return result;
	}
}
