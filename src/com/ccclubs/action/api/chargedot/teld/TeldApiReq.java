package com.ccclubs.action.api.chargedot.teld;

import java.util.LinkedHashMap;
import java.util.Map;

import com.ccclubs.action.app.official.util.Md5Util;
import com.ccclubs.util.DESUtil;
import com.google.gson.Gson;
import com.lazy3q.web.helper.$;

/**
 * 
 * 特来电电桩API请求参数
 * 
 * @author Joel
 *
 */
public class TeldApiReq {
	
	private Object requestMsg;
	
	private String encrypt;
	
	private String sign;
	
	public Object getRequestMsg() {
		return requestMsg;
	}

	public void setRequestMsg(Object requestMsg) {
		this.requestMsg = requestMsg;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getEncrypt() {
		return encrypt;
	}

	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}

	/**
	 * 特来电接口
	 * 1、加密：	{"requestMsg":"1Dbn7AhINvhdEdpo0PZ0YdqmG7HX7QzevdA7BSA6q4s=","sign":"94e292b22164820a158468bc0b432ee0"}
	 * 2、不加密 {"requestMsg":{"pileCode":"1101150019206"},"encrypt":"encrypt","sign":"94e292b22164820a158468bc0b432ee0"}
	 * 
	 * @param encrypt
	 * @return
	 */
	public String getReqData(boolean doEncrypt){
		this.encrypt = doEncrypt ? "encrypt" : "noencrypt";
		Gson mgson = new Gson();
		String paramVal = mgson.toJson(getRequestMsg());
		String desParam = "";
		if(doEncrypt){
			try {
				desParam = DESUtil.encrypt(paramVal, TeldApi.DesKey, TeldApi.DesIv);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			desParam = paramVal;
		}
		
		String s = doEncrypt ? "" : ("encrypt="+getEncrypt()+"&");
		s = s + "requestMsg="+paramVal;
		
		this.sign = Md5Util.MD5(s+TeldApi.Key);
		
		Map<String, Object> res = new LinkedHashMap<String, Object>();
		if(!doEncrypt){
			res.put("encrypt", encrypt);
			res.put("requestMsg", getRequestMsg());
		}else{
			res.put("requestMsg", desParam);
		}
		res.put("sign", this.sign);
		return $.json(res);
	}
}
