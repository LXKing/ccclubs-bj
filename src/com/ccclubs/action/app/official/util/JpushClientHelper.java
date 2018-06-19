package com.ccclubs.action.app.official.util;

import java.util.Map;

import org.apache.log4j.Logger;
import cn.jpush.api.JPushClient;
import cn.jpush.api.common.ClientConfig;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 消息推送助手(仅限于penglongApp)
 * 通过https://www.jpush.cn应用管理设置
 * @author joel
 *
 */
public class JpushClientHelper {
	
	private static final String jPushHostName = "https://api.jpush.cn";

	static JPushClient jPushClient = null;
	
	final static Logger LOG = Logger.getRootLogger();
	
	static {
			ClientConfig config = ClientConfig.getInstance();
			
			config.setPushHostName(jPushHostName);
			
			//dcl
/*			synchronized(JpushClientHelper.class){
				if(jPushClient == null){
					synchronized(JpushClientHelper.class){
						jPushClient = new JPushClient(masterSecret, appKey, 3, null, config);
					}
				}
			}*/
	}
	
	JpushClientHelper(){
		
	}

	/**
	 * 构建系统通知类消息
	 * @param title
	 * @param alert
	 * @return
	 */
	static PushPayload buildAlert(String title, String alert) {
		return PushPayload.newBuilder()
				.setPlatform(Platform.android_ios())		//推送至android ios平台
				.setAudience(Audience.all())
				.setNotification(Notification.android(alert, title, null))
				.build();
	}
	
	/**
	 * 构建系统通知类消息
	 * @param tag
	 * @param title
	 * @param alert
	 * @return
	 */
	static PushPayload buildAlert(String tag, String title, String alert) {
		 return buildAlert(tag, title, alert, null);
    }
	
	/**
	 * 构建系统通知类消息
	 * @param tag
	 * @param title
	 * @param alert
	 * @return
	 */
	static PushPayload buildAlert(String tag, String title, String alert, Map<String, String> extra) {
		return PushPayload.newBuilder()
				.setPlatform(Platform.android_ios())		//推送至android ios平台
				.setAudience(Audience.tag(tag))
				.setNotification(Notification.newBuilder()
						.addPlatformNotification(AndroidNotification.newBuilder().setTitle(title).addExtras(extra).setAlert(alert).build())
						.addPlatformNotification(IosNotification.newBuilder().setAlert(alert).addExtras(extra).build()).build())
				.setOptions(Options.newBuilder().setApnsProduction(Env.isOnline()?true:false).build())
				.build();
	}
	
	/**
	 * 构建自定义消息
	 * @param tag	  tag
	 * @param message 消息体
	 * @param extras  扩展参数，可选
	 * @return
	 */
	static PushPayload buildMessage(String tag, String message, Map<String, String> extras) {
		  return PushPayload.newBuilder()
	                .setPlatform(Platform.android_ios())
	                .setAudience(Audience.newBuilder()
	                        .addAudienceTarget(AudienceTarget.tag(tag))
	                        .build())
	                .setMessage(Message.newBuilder()
	                        .setMsgContent(message)
	                        .addExtras(extras)
	                        .build())
	                .build();
    }

	/**
	 * （对匹配tag）发送通知
	 * @param title
	 * @param alert
	 */
	public static void sendAlertWithTag(String tag, String title, String alert , JpushConfig jcf) {
        ClientConfig config = ClientConfig.getInstance();
        config.setPushHostName(jPushHostName);
        PushPayload payload = buildAlert(tag, title, alert);
        jPushClient = new JPushClient(jcf.getAppSecret(), jcf.getAppKey(), 3, null, config);
        
        try {
            PushResult result = jPushClient.sendPush(payload);
            LOG.info("Got result - " + result);
        } catch (APIConnectionException e) {
        	LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
    }
	
	/**
	 * （对匹配tag）发送通知
	 * @param title
	 * @param alert
	 */
	public static void sendAlertWithTag(String tag, String title, String alert, Map<String, String> extra, JpushConfig jcf) {

        PushPayload payload = buildAlert(tag, title, alert, extra);
        
        ClientConfig config = ClientConfig.getInstance();
		
		config.setPushHostName(jPushHostName);
        jPushClient = new JPushClient(jcf.getAppSecret(), jcf.getAppKey(), 3, null, config);

        try {
            PushResult result = jPushClient.sendPush(payload);
            LOG.info("Got result - " + result);
        } catch (APIConnectionException e) {
        	LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        }
    }
	
	/**
	 * （对所有人）发送通知
	 * @param title
	 * @param alert
	 */
	public static void sendAlertWithAll(String title, String alert, JpushConfig jcf) {
		
		PushPayload payload = buildAlert(title, alert);
		
		ClientConfig config = ClientConfig.getInstance();
		
		config.setPushHostName(jPushHostName);
        jPushClient = new JPushClient(jcf.getAppSecret(), jcf.getAppKey(), 3, null, config);
		
		try {
			PushResult result = jPushClient.sendPush(payload);
			LOG.info("Got result - " + result);
		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later. ", e);
		} catch (APIRequestException e) {
			LOG.error("Error response from JPush server. Should review and fix it. ", e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
			LOG.info("Msg ID: " + e.getMsgId());
		}
		
	}
	
	/**
	 * 发送自定义消息
	 * @param tag
	 * @param message
	 * @param extras
	 */
	public static void sendMessageWithTag(String tag , String message, Map<String, String> extras, JpushConfig jcf) {
		
		PushPayload payload = buildMessage(tag, message, extras);
		
		ClientConfig config = ClientConfig.getInstance();
		
		config.setPushHostName(jPushHostName);
        jPushClient = new JPushClient(jcf.getAppSecret(), jcf.getAppKey(), 3, null, config);
		
		try {
			PushResult result = jPushClient.sendPush(payload);
			LOG.info("Got result - " + result);
		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later. ", e);
		} catch (APIRequestException e) {
			LOG.error("Error response from JPush server. Should review and fix it. ", e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
			LOG.info("Msg ID: " + e.getMsgId());
		}
	}
	
//	/**
//	 * @param Id
//	 */
//	@Deprecated
//	public static void getAppKeyAndMasterSecret1(int Id){
//		
//		if(Env.isTest()){
//			/*
//			 * 鹏龙测试的
//			 */
//			appKey = "88dd78af73dcb9dd27e028f6";masterSecret = "872f0e9c262b7ed155cdb1f7";
//			return;
//		}
//		
//		String _appKey = $.config("jpush.appKey");
//		String _masterSecret = $.config("jpush.masterSecret");
//		if(StringUtils.isNotEmpty(_appKey) && StringUtils.isNotEmpty(_masterSecret)){
//			appKey = _appKey;
//			masterSecret = _masterSecret;
//			return;
//		}
//		
//		switch(Id){
//			case 1: appKey = "3aa095f7180ea39c6d8f49c4";masterSecret = "d1d3dfea7b64de009f471c38";break; 	//公务出行正式
//			case 2: appKey = "b5fd06453cad3814ed009639";masterSecret = "fe19d1a37a8095c9ac1e0da4";break; 	//南京国政
//			default: appKey = "88dd78af73dcb9dd27e028f6";masterSecret = "872f0e9c262b7ed155cdb1f7";break;
//		}
//	}
	
}
