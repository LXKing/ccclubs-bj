package com.ccclubs.helper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMessage;
import com.lazy3q.web.helper.$;

/**
 * web在线客户聊天系统助手
 * 通信中间件：mqtt
 * 数据协议：matt通信协议
 * @author uiu
 */
public class ChatHelper {
	/**
	 * 监听器
	 */
	private static Map<String,ChatSendListener> listeners = new HashMap();
	
	public static abstract class ChatSendListener{
		public abstract void send(String flag,String content);
	}
	
	public enum Platform{
		WEB,//网页
		WEIXIN,//微信
		ALIPAY,//微生活
		EXTMAN//后台
	}
	
	//mqtt主题协议:原平台标识/城市ID/(mo|mt|on|off)/flag(标识或openid)/service(客服ID)
	public static String CHAT_MQ_FORMAT = "{platform}/{host}/{type}/{flag}/{service}";
	
	/**
	 * 添加平台消息发送监听器
	 * @param platform
	 * @param chatListener
	 */
	public static void addChatListener(Platform platform,ChatSendListener chatListener){
		listeners.put(platform.name(), chatListener);
	}
	/**
	 * 订阅所有mqtt消息，以保存或转发消息
	 */
	static{
		if($.equals("extman", $.config("chat.mqtt.client"))){
			MqttClient mqttClient = getMessageMqttClient();
			mqttClient.setCallback(new MqttCallback(){
				public void connectionLost(Throwable arg0) {
					// TODO Auto-generated method stub
				}
				public void deliveryComplete(IMqttDeliveryToken arg0) {
					// TODO Auto-generated method stub
				}
				public void messageArrived(String arg0, MqttMessage arg1)throws Exception {
					
				}
			});
			try {
				mqttClient.subscribe("+/+/+/+/+");
			} catch (MqttException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 获取IM之MQTT消息中间件客户端
	 * @return
	 */
	private static MqttClient getMessageMqttClient(){
		if(messageMqttClient==null){
			try {
				String address = $.config("chat.mqtt.address");
				if($.empty(address))
					throw new Exception("未配置消息MQTT服务器,请在配置文件config.properties文件中配置chat.mqtt.address");
				String username = $.config("chat.mqtt.username");
				String password = $.config("chat.mqtt.password");
				if(!$.empty(username))
					conOpt.setUserName(username);
				if(!$.empty(password))
					conOpt.setPassword($.or(password,"").toCharArray());
				conOpt.setCleanSession(true);
				String clientid = $.config("chat.mqtt.client");
				if($.empty(clientid))
					throw new Exception("未配置消息MQTT客户端标识,请在配置文件config.properties文件中配置chat.mqtt.client");
				messageMqttClient = new MqttClient(address,clientid);
				messageMqttClient.connect(conOpt);
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}
		if (!messageMqttClient.isConnected()) {
			$.trace("消息MQTT开始重连");
			try {
				messageMqttClient.connect(conOpt);
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}
		}
		return messageMqttClient;
	}
	private static MqttClient			messageMqttClient		= null;	
	private static MqttConnectOptions	conOpt				= new MqttConnectOptions();

}
