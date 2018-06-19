package com.ccclubs.service.common;

import java.util.Date;
import java.util.Map;

import com.ccclubs.exception.MessageException;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsUnitOrder;
import com.ccclubs.model.CsUserType;


public interface ICommonUtilService {
	
	public enum SMSType{
		代码类短信,
		通知类短信,
		提示类短信,
		广告类短信
	}
	
	/**
	 * 发送模版短信
	 * @param flag 标识 
	 * @param mobile 手机号码
	 * @param smsType 
	 * @param params 参数列表
	 */
	public Boolean sendTemplateSms(Long host,String flag,String mobile,String defaultTemplate,SMSType smsType, Map<String,String> params);

	/**
	 * 发送普通短信
	 * @param mobile 手机号码
	 * @param content 短信内容
	 * @param smsType 
	 */
	public void sendSms(Long host,String mobile,String content, SMSType smsType);

	/**
	 * 根据标识获取会员类型
	 * @param strFlag
	 * @return
	 */
	public CsUserType getUserType(String strFlag);
	
	
	
	
}
