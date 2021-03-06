
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import com.lazy3q.web.helper.$;

public class JpushTest2 {
	
    protected static final Logger LOG = LoggerFactory.getLogger(JpushTest2.class);

    // demo App defined in resources/jpush-api.conf 
//	private static final String appKey ="5f6fa0c0cab45dcbafe616c3";
//	private static final String masterSecret = "6e8d5c406c985f510f91462f";
	private static final String appKey ="3aa095f7180ea39c6d8f49c4";
	private static final String masterSecret = "d1d3dfea7b64de009f471c38";
	
	public static final String TITLE = "Test from API example";
    public static final String ALERT = "Test from API Example - alert";
    public static final String MSG_CONTENT = "Test from API Example - msgContent";
    public static final String REGISTRATION_ID = "0900e8d85ef";
    public static final String TAG = "tag_api";

	public static void main(String[] args) {
        testSendPushWithCustomConfig();
	}
	
	
	public static void testSendPush() {
	    // HttpProxy proxy = new HttpProxy("localhost", 3128);
	    // Can use this https proxy: https://github.com/Exa-Networks/exaproxy
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
        
        // For push, all you need do is to build PushPayload object.
        PushPayload payload = buildPushObject_all_all_alert();
        
        try {
            PushResult result = jpushClient.sendPush(payload);
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
	
	public static PushPayload buildPushObject_all_all_alert() {
	    return PushPayload.alertAll(ALERT);
	}
	
    public static PushPayload buildPushObject_all_alias_alert() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias("alias1"))
                .setNotification(Notification.alert(ALERT))
                .build();
    }
    
    public static PushPayload buildPushObject_android_tag_alertWithTitle() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag("tag1"))
                .setNotification(Notification.android(ALERT, TITLE, null))
                .build();
    }
    
    public static PushPayload buildPushObject_android_and_ios() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.tag("tag1"))
                .setNotification(Notification.newBuilder()
                		.setAlert("alert content")
                		.addPlatformNotification(AndroidNotification.newBuilder()
                				.setTitle("Android Title").build())
                		.addPlatformNotification(IosNotification.newBuilder()
                				.incrBadge(1)
                				.addExtra("extra_key", "extra_value").build())
                		.build())
                .build();
    }
    
    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and("tag1", "tag_all"))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(ALERT)
                                .setBadge(5)
                                .setSound("happy")
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                 .setMessage(Message.content(MSG_CONTENT))
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(true)
                         .build())
                 .build();
    }
    
    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
//        return PushPayload.newBuilder()
//                .setPlatform(Platform.android_ios())
////                .setAudience(Audience.newBuilder()
////                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
////                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
////                        .build())
//                .setAudience(Audience.tag("10000032"))
//                .setMessage(Message.newBuilder()
//                        .setMsgContent(MSG_CONTENT)
//                        .addExtra("from", "JPush")
//                        .build())
//                .build();
    	
    	return PushPayload.newBuilder()
                .setPlatform(Platform.android())
//                .setAudience(Audience.newBuilder()
//                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
//                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
//                        .build())
                .setAudience(Audience.tag("10000032"))
                .setMessage(Message.content("卧室内容"))
                .build();
    }
    
    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras_online() {
    	long id = 34;
    	Map<String, Object> msgmap = new HashMap<String, Object>();
		msgmap.put("specialId", id);
		msgmap.put("status", 3);
		Map<String, String> extras = new HashMap<String, String>();
		extras.put("specialId", id+"");
		
		
		String message =$.json(msgmap); 
		 return PushPayload.newBuilder()
	                .setPlatform(Platform.android_ios())
	                .setAudience(Audience.newBuilder()
	                        .addAudienceTarget(AudienceTarget.tag("10000032"))
	                        .build())
	                .setMessage(Message.newBuilder()
	                        .setMsgContent(message)
	                        .addExtras(extras)
	                        .build())
	                .build();
		
//    	return PushPayload.newBuilder()
//    			.setPlatform(Platform.android())
//    			.setAudience(Audience.tag("10000032"))
//    			.setMessage(Message.content($.json(msgmap)))
//    			.build();
    }
    
    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras2() {
    	 return PushPayload.newBuilder()
                 .setPlatform(Platform.android())
//                 .setAudience(Audience.all())
                 .setAudience(Audience.tag("10000032"))
                 .setNotification(Notification.android(ALERT, TITLE, null))
                 .build();
    }

    public static void testSendPushWithCustomConfig() {
        ClientConfig config = ClientConfig.getInstance();
        // Setup the custom hostname
        config.setPushHostName("https://api.jpush.cn");

        JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3, null, config);

        // For push, all you need do is to build PushPayload object.
//        PushPayload payload = buildPushObject_all_all_alert();
//        PushPayload payload = buildPushObject_ios_audienceMore_messageWithExtras();
//        PushPayload payload = buildPushObject_ios_audienceMore_messageWithExtras2();
        PushPayload payload = buildPushObject_ios_audienceMore_messageWithExtras_online();

        try {
            PushResult result = jpushClient.sendPush(payload);
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

}

