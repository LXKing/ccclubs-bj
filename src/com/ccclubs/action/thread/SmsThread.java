package com.ccclubs.action.thread;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ccclubs.action.thread.sms.BaseSMS;
import com.ccclubs.action.thread.sms.JinLouNoteV3;
import com.ccclubs.action.thread.sms.sdk_entinfo_cn;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.exception.ErrorException;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsSmsMt;
import com.ccclubs.model.SrvHost;
import com.ccclubs.model.SrvProperty;
import com.ccclubs.service.admin.ICsSmsMtService;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.Lazy;

public class SmsThread extends Thread {
	
	ICsSmsMtService csSmsMtService;	
	ISrvPropertyService srvPropertyService;
	
	@Override
	public void run() {
		while(true){
			if($.empty($.config("release.online"))){
				try {
					$.trace("非在线模式，放弃执行当前线程"+this.getClass().getName());
					Thread.sleep(5000l);
				} catch (Exception e) {
					e.printStackTrace();
				}
				continue;
			}
			int count = 0;
			try{
				count = scanSms();
			}catch(Exception ex){
				ex.printStackTrace();
				new ErrorException("THREAD_ERROR","线程处理短信出错",ex);
			}
			try {
				if(count==0)
					Thread.sleep(5000);
				else
					Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 扫描需要处理的短信
	 */
	private int scanSms() {		
		final CsSmsMt csSmsMt=csSmsMtService.getCsSmsMt(Lazy.add("cssmStatus", 1));
		if(csSmsMt!=null){	
			sendSms(csSmsMt);					
			return 1;
		}else
			return 0;
	}
	
	/**
	 * 获取短信发送类
	 * @return
	 */
	public static BaseSMS getSMS(Short type){
		SMSType smsType=null;
		SMSType[] types = SMSType.values();
		for(SMSType t:types){
			if((t.ordinal()+1)==(type.intValue())){
				smsType = t;
				break;
			}
		}
		String strSmsClass = SYSTEM.getArgumentValue(smsType.name()+"通道类", "SMS_CHANNEL_CLASS_"+smsType.ordinal(), $.or($.config("sms_channel"), "com.ccclubs.action.thread.sms.www_smsadmin_cn"));
		BaseSMS sms = null;
		try{
			Logger.getRootLogger().error("使用短信通道："+strSmsClass);
			Class smsClass = (Class<BaseSMS>) Class.forName(strSmsClass);
			sms = (BaseSMS) smsClass.newInstance();
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("无法找到短信发送类 - "+strSmsClass);			
		}
		return sms;
	}
	
	
	
	public static String remain() {
		return "当前多通道并行模式，暂时无法查询短信余额";	
	}
	
	
	/**
	 * 提交短信发送
	 * @param sign
	 * @param cssmMobile
	 * @return
	 */
	private void sendSms(CsSmsMt csSmsMt) {		
		BaseSMS sms = getSMS(csSmsMt.getCssmType());
		
//		//如果是北京，则走指定的发送通道
//		if(csSmsMt.getCssmHost()!=null && csSmsMt.getCssmHost().longValue()!=0l){
//			SrvHost srvHost = csSmsMt.get$cssmHost();
//			csSmsMt.setValues("srvHost", srvHost);
//			if(srvHost.getShOper().longValue()==1l)
//				sms = new sdk_entinfo_cn();
//		}
		
		if(sms==null)
			return;
		
		//根据类名取通道号
		SrvProperty srvProperty = srvPropertyService.getSrvProperty(
			$.add(SrvProperty.F.spValue, sms.getClass().getName())
		);	
		
		if(SystemHelper.isMobile(csSmsMt.getCssmMobile())){
			String strContent = sign(csSmsMt,csSmsMt.getCssmContent());
			String strRet = sms.send(csSmsMt.getCssmMobile(),strContent);
			
			boolean jinlouSendSuccess = false;
			if(sms instanceof JinLouNoteV3){
				jinlouSendSuccess = JinLouNoteV3.isSucceedOrFail(strRet);
				if(!jinlouSendSuccess){
					Logger.getRootLogger().error("金楼短信通道发送失败！id="+csSmsMt.getCssmId()+",content="+strContent);
				}
			}
			
			if(strRet!=null && (strRet.indexOf("成功")>-1 || jinlouSendSuccess)){
				csSmsMtService.updateCsSmsMt$NotNull(
					new CsSmsMt()
					.cssmId(csSmsMt.getCssmId())
					.cssmStatus((short)2)
					.cssmContent(strContent)
					.cssmChannel(srvProperty==null?null:srvProperty.getSpId())
					.cssmRemark(strRet)
				);
			}else{
				csSmsMtService.updateCsSmsMt$NotNull(
					new CsSmsMt()
					.cssmId(csSmsMt.getCssmId())
					.cssmStatus((short)2)
					.cssmContent(strContent)
					.cssmRemark(strRet)
					.cssmChannel(srvProperty==null?null:srvProperty.getSpId())
				);				
			}
		}else{
				csSmsMtService.updateCsSmsMt$NotNull(
					new CsSmsMt()
					.cssmId(csSmsMt.getCssmId())
					.cssmStatus((short)3)
					.cssmRemark("非手机号码，放弃发送")
					.cssmChannel(srvProperty==null?null:srvProperty.getSpId())
				);
		}
	}

	
	public static void main(String...strings){
		String content = "需要[fds]【】需要【车纷享";
		content = content.replaceAll("[\\[|【][^\\[\\]【】]*[\\]|】]$", "");
		$.trace(content);
	}
	
	public static final String smsSign = $.or($.config("sms.sign"), "") ;
	/**
	 * 短信签名
	 * @param csSmsMt 
	 * @param content
	 * @return
	 */
	public static String sign(CsSmsMt csSmsMt, String content){
		content = content.replaceAll("[\\[|【][^\\[\\]【】]*[\\]|】]$", "");
		content = content.replace("车纷享", "");
		SrvHost srvHost = (SrvHost) csSmsMt.getValues().get("srvHost");
		String sign = srvHost==null? smsSign : $.or(srvHost.get$shOper().getCsoSign(), smsSign);
		return $.trim(content)+ (StringUtils.isEmpty(sign) ? "" : "【"+sign+"】");
	}
	

	public ICsSmsMtService getCsSmsMtService() {
		return csSmsMtService;
	}

	public void setCsSmsMtService(ICsSmsMtService csSmsMtService) {
		this.csSmsMtService = csSmsMtService;
	}

	public ISrvPropertyService getSrvPropertyService() {
		return srvPropertyService;
	}

	public void setSrvPropertyService(ISrvPropertyService srvPropertyService) {
		this.srvPropertyService = srvPropertyService;
	}

}
