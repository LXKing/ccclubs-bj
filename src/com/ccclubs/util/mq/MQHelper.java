package com.ccclubs.util.mq;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import com.ccclubs.util.mq.entity.OpenDoor;

public class MQHelper {

	
private static MqttClient mqttClient = null;
	
	/**
	 * @param args
	 * @throws MqttException 
	 */
	public static void main(String[] args){
		try {
			mqttClient = new MqttClient("tcp://192.168.1.237:1883","d");
		
			//注册监听
			mqttClient.setCallback(new MqttCallback(){
				public void connectionLost(Throwable arg0) {
					System.out.println("connectionLost");
				}
				public void deliveryComplete(IMqttDeliveryToken arg0) {
					System.out.println("deliveryComplete - "+arg0.getMessageId());
				}
				//接收到消息
				public void messageArrived(String topic, MqttMessage arg1)throws Exception {				
					System.out.println("接收到消息 - "+topic+" - "+arg1.toString());
					
				}
			});
			
			//连接
			mqttClient.connect();		
					
			//订阅
			//mqttClient.subscribe("MQTT Examples");
					
			OpenDoor openDoor = new OpenDoor("ZJA6334E",10234l);
			openDoor.trace();
			//发送消息
			mqttClient.publish("MQTT Examples", openDoor.getBytes(),0,false);
			mqttClient.publish("MQTT Examples", openDoor.getBytes(),0,false);
			mqttClient.publish("MQTT Examples", openDoor.getBytes(),0,false);
			mqttClient.publish("MQTT Examples", openDoor.getBytes(),0,false);
			
			System.out.println(0xff00);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
		

}
