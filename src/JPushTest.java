import java.util.HashMap;
import java.util.Map;

import cn.jpush.api.common.DeviceType;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;


public class JPushTest {
	
	static String MASTER_SECRET = "f30033c895bcef7ff6457f88";
	static String APP_KEY = "a74766f3c67e7d6dddf483e4";
	
	public static void main(String[] args) {
//	        PushClient pushClient = new PushClient(MASTER_SECRET, APP_KEY);
			PushClient  pushClient = new PushClient(MASTER_SECRET, APP_KEY);
	        try {
//	        	pushClient.setBaseUrl("https://api.jpush.cn/v3/push/");
//	        	pushClient.setDefaults(false, 2000);
//	        	pushClient.setBaseUrl("https://www.jpush.cn/push/apps/a74766f3c67e7d6dddf483e4/push/");
//	            pushClient.sendPush("{aaa:'a'}");
	            PushResult result = pushClient.sendPushValidate(PushPayload.alertAll("alert"));
//	            PushResult result = pushClient.sendPushValidate(buildPushObject_android_tag_alertWithTitle());
//	            PushResult result = pushClient.sendPushValidate(buildPushObject_all_all_alert());
	            System.out.println(result.getOriginalContent());
	        } catch (APIConnectionException e) {
	            e.printStackTrace();
	        } catch (APIRequestException e) {
	            e.printStackTrace();
	        } catch(Exception e){
	        	e.printStackTrace();
	        }
	}
	
	public static PushPayload buildPushObject_all_all_alert() {
        return PushPayload.alertAll("是对的山东省");
    }
	
	public static PushPayload buildPushObject_android_tag_alertWithTitle() {
		Map<String, String> extParam = new HashMap<String, String>(); 
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.tag("111"))
                .setNotification(Notification.android("alert", "wolaile", null))
                .build();
    }
	
}
