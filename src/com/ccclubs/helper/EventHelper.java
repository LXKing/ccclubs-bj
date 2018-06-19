package com.ccclubs.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.ccclubs.exception.ErrorException;
import com.ccclubs.model.CsEmail;
import com.ccclubs.model.CsEvent;
import com.ccclubs.model.CsEventHeed;
import com.ccclubs.model.CsEventType;
import com.ccclubs.model.CsNotice;
import com.ccclubs.model.SrvUser;
import com.ccclubs.model.SrvUserExp;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.WebHelper.LzMap;

/**
 * 消息中心助手
 * @author uiu
 */
public class EventHelper {
	public static enum EventType{
		测试事件,
		车辆交强险到期预警, 
		车辆商业险到期预警, 
		车辆年检到期预警, 
		网点缴费预警提醒, 
		网点合约到期预警提醒, 
		车辆保养预警提醒,
		新会员注册提醒,
		会员充值提醒,
		会员下单提醒, 
		车辆掉线提醒,
		会员上传资料图片, 
		会员退款提醒, 
		会员退款审核到期预警, 
		会员退款打款到期预警, 
		会员还车提醒,
		会员异地还车提醒, 
		车辆低电量提醒, 
		首次用车掉线提醒,
		首次用车还车超时,
		首次用车超距报警
	}
	/**
	 * 发送系统事件
	 * @param sender 发送人
	 * @param receiver
	 * @param eventType
	 * @param flag
	 * @param content
	 * @param url
	 */
	public static void postEvent(final Long host,final EventType eventType,final String content,final Long dataid,final Class dataClass,final Long ...defaultNoticeUserIds){
		if($.empty($.config("release.online"))){
			System.out.print("事件发送未执行");
			return;
		}
		try{
			if(dataid==null)
				throw new Exception("发送系统事件时未dataid不能为空");
			new Thread(){
				public void run(){
					EventHelper.sendEvent(host,eventType, content, dataid ,dataClass,defaultNoticeUserIds);		
				}
			}.start();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void sendEvent(final Long host,EventType eventType,String content,Long dataid,Class dataClass,Long ...defaultNoticeUserIds){
		//根据EventType获取数据库CsEventType
		CsEventType csEventType = GetCsEventType(eventType);
		if(csEventType.getCsetStatus().shortValue()!=1)
			return;
		//时间限制
		Date limit = new Date(new Date().getTime()-(csEventType.getCsetInterval()*1000));
		String sDataId = dataClass.getClass().getSimpleName()+"@"+dataid;
		CsEvent existEvent = CsEvent.where().cseAddTimeStart(limit).cseDataId(sDataId).cseType(csEventType.getCsetId()).get();
		if(existEvent!=null)
			return;
		CsEvent csEvent = new CsEvent(
			content//事件内容 [非空]
		 	,csEventType.getCsetId()//事件类型 [非空]
		 	,dataClass==null?null:(dataClass.getSimpleName()+"@"+dataid)//关联ID [非空]
		 	,new Date()//修改时间 [非空]
		 	,new Date()//添加时间 [非空]
		 	,(short)1//状态 [非空]
		 ).save();
		
		String action = "";
		if(!$.empty(csEventType.getCsetAction()))
			action = csEventType.getCsetAction().replace("{id}", dataid==null?"":String.valueOf(dataid));
		
		//发送默认的通道消息
		 for(Long useid:defaultNoticeUserIds){
			 EventHelper.sendNotice(host,0l, useid, eventType.name(), content,action);
		 }
		 if(csEventType.getCsetLevel().shortValue()==1){
			 EventHelper.publish(MessageType.MESSAGE,host,0l,null, eventType.name(), content, dataid);
			 $.trace("############发送全员消息############");
		 }
		 //根据事件关注人推送相关事件
		 List<CsEventHeed> heeds = CsEventHeed.list(
			$.add(CsEventHeed.F.csehEventType, csEventType.getCsetId()).add(CsEventHeed.F.csehStatus, 1)
			,-1
		 );
		 for(CsEventHeed csEventHeed:heeds){
			 SrvUser user = csEventHeed.get$csehHeedUser();
			 if(user==null || !$.idin(user.getSuHost(), host))
				 continue;
			 if(csEventHeed.getCsehHeedType().indexOf("#0#")>-1){
				 //向浏览器端推荐消息
				 EventHelper.publish(MessageType.MESSAGE,host,0l,csEventHeed.getCsehHeedUser(), eventType.name(), content, dataid);
			 }
			 if(csEventHeed.getCsehHeedType().indexOf("#1#")>-1){
				 CsNotice csNotice = EventHelper.sendNotice(host,0l, csEventHeed.getCsehHeedUser(), eventType.name(), content, action);
				//向浏览器端推荐消息
				 EventHelper.publish(MessageType.NOTICE,host,0l,csEventHeed.getCsehHeedUser(), eventType.name(), content, csNotice.getCsnId());
			 }
			 if(csEventHeed.getCsehHeedType().indexOf("#3#")>-1){
				 //向用户发送邮件
				 if(!$.empty(user.getSuEmail())){
					 new CsEmail(
						 user.getSuEmail()//接收人 [非空]
					 	,content//邮件内容 [非空]
					 	,null//发送人
					 	,null//发送时间
					 	,new Date()//添加时间 [非空]
					 	,(short)0//状态 [非空]
					 ).save();
				 }
			 }
			 if(csEventHeed.getCsehHeedType().indexOf("#4#")>-1){	 
				 //向用户发微信
				 SrvUserExp srvUserExp = SrvUserExp.where().sueUser(user.getSuId()).get();
				 if(srvUserExp!=null && !$.empty(srvUserExp.getSueWxOpenid())){
					 WeixinHelper.sendWxTemplateMessage(
					 host, (short)1, srvUserExp.getSueWxOpenid(),
					 WeixinHelper.WxTemplateMsgType.系统报警,
					 CsEvent.class, csEvent.getCseId());
				 }
			 }
			 if(csEventHeed.getCsehHeedType().indexOf("#2#")>-1){
				 if(!$.empty(user.getSuMobile())){
					 Long lHost = 1l;
					 if(!$.empty(user.getSuHost()))
						 lHost = Long.parseLong(user.getSuHost().split(",")[0]);
					 UtilHelper.sendSms(lHost, user.getSuMobile(), content, SMSType.通知类短信);
				 }
			 }
		 }
	}
	
	/**
	 * 获取系统事件类型
	 * @param eventType
	 */
	public static synchronized CsEventType GetCsEventType(EventType eventType){
		CsEventType csEventType = CsEventType.Get($.add(CsEventType.F.csetName, eventType.name()));
		if(csEventType==null){
			csEventType = new CsEventType(
				eventType.name()//事件名称 [非空]
				,(short)0//事件级别
				,null
			 	,null//处理地址
			 	,60l//时间间隔，默认一分钟 
			 	,new Date()//修改时间 [非空]
			 	,new Date()//添加时间 [非空]
			 	,(short)0//状态 [非空]
			 ).save();
		}
		return csEventType;
	}
	
	
	
	/**
     * 发送html邮件
     * @param sender 邮件发送人实体
     * @param to 接收人的邮件地址
     * @param title 发送邮件的标题
     * @param content	发送邮件的内容
     * @throws Exception
     */
    public static void sendHTMLMail(String to,String title,String content) throws Exception {
    	JavaMailSenderImpl sender = $.getBean("mailSender");
    	MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
		helper.setTo(to);
		String nick = javax.mail.internet.MimeUtility.encodeText("车纷享");
		helper.setFrom(new InternetAddress(sender.getUsername(), nick));
		helper.setSubject(title);
		helper.setSentDate(new Date());
		helper.setText(content, true);
		sender.send(msg);
		System.out.println("成功发送邮件 - "+sender.getUsername() + " >> " + to);
    }
	
	
	
	
	/**
	 * 发送待办事项
	 * @param sender
	 * @param receiver
	 * @param flag
	 * @param title
	 * @param content
	 * @param url
	 * @return 
	 */
	public static CsNotice sendNotice(Long host,Long sender,Long receiver,String flag,String content,String url){
		try{
			CsNotice csNotice = CsNotice.getCsNotice($.add("csnFlag", flag).add("csnReceiver", receiver));
			if(csNotice==null){	
				return new CsNotice(
					content//内容 [非空]
				 	,new Date()//提醒时间
				 	,url//处理地址
				 	,flag//标识
				 	,sender//发送人 [非空]
				 	,receiver//接收人 [非空]
				 	,null//处理描述
				 	,null//处理日志
				 	,new Date()//添加时间 [非空]
				 	,new Date()//处理时间
				 	,(short)0//读取状态 [非空]
				 ).save();
			}
			return csNotice;
		}catch(Exception ex){
			ex.printStackTrace();
			new ErrorException("NOTICE_ERROR","发送待办事项出错",ex);
			return null;
		}
	}
	
	
	/**
	 * 向浏览器端推荐消息
	 * @param messageType
	 * @param sender
	 * @param receiver
	 * @param flag
	 * @param content
	 * @param action
	 * @param dataid
	 */
	public static void publish(MessageType messageType,Long host,Long sender,Long receiver, String flag,String content,Long dataid) {
		try {
			List<Object> datas = new ArrayList();
			datas.add(flag);
			//datas.add($.left(content, 32));
			//datas.add(content+content+content+content+content+content);
			datas.add(content);
			datas.add(dataid);
			String strJson = $.json(datas);
			String topic = MQTT_PUSH_FORMAT.replace("{from}", ""+sender).replace("{host}", ""+host)
			.replace("{to}", ""+receiver).replace("{type}", messageType.name());
			$.publish(topic,strJson.getBytes("UTF-8"),0, false);
			$.trace(topic.getBytes().length,strJson.getBytes().length,topic,strJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static String MQTT_PUSH_FORMAT	= "event/{type}/{host}/{from}/{to}";
	public static enum MessageType{
		DATA,//数据消息
		MESSAGE,//纯内容消息
		NOTICE,//系统通知
		TODO, //待办事项
	}
	public static void main(String...strings){		
		while(true){			
			EventHelper.publish(EventHelper.MessageType.DATA, 0l, 0l, null, "test", $.rand(99)+"条最准的心理测试条最准的心理测试条最准的心理测试条最准的心理测试条最准的心理测试条最准的心理测试条条最准的心理测试条最准的心理测试条最准的心理测试条最准的心理测试条最准的心理测试条最准的心理测试条条最准的心理测试条最准的心理测试条最准的心理测试条最准的心理测试条最准的心理测试条最准的心理测试条最准的心理测试", 0l);
			try {
				Thread.sleep(1000l);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
