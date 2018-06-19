package com.ccclubs.action.weixin;

import java.util.List;

import com.ccclubs.model.CsWxContent;

/**
 * 微信接口脚本处理
 * @author uiu
 *
 */
public interface IWXScript {

	public enum ReturnType{TEXT,NEWS,PIC}
	
	ReturnType getReturnType();
	
	List<CsWxContent> getContents(String openId,String content);
	
	String getContent(String openId,String content);	
	
}
