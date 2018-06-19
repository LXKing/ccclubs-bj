package com.ccclubs.service.common.impl;

import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.ccclubs.action.receiver.CarStatusReceiver.ConnectTimerTask;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsRemote;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.ccclubs.util.mq.entity.RemoteOption;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.WebHelper;

public class RemoteService implements MqttCallback {

	private static MqttClient pushMqttClient = null; // MqttClient建议单例模式
	private static String MQTT_PUSH_FORMAT = "car/ser/{0}/rt_0/{1}/pri_2";
	private static Timer connectTimer = new Timer();
	private final static String USER_NAME = "qsxiaogang";
	private final static String PASSWORD = "513387369";

	private static MqttConnectOptions conOpt = null;

	static {
		try {
			ConnectTimerTask task = new ConnectTimerTask();
			connectTimer.schedule(task, 1000, 15 * 1000);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 返回MQTT方式的远程操作命令内容，同时下发远程命令到设备
	 * 
	 * @param remote
	 * @return
	 */
	private static String getMQTTRemoteCommend(CsRemote remote) {
		RemoteOption remoteOption = new RemoteOption();
		remoteOption.id = remote.getCsrId();
		remoteOption.number = remote.getCsrNumber();
		switch (remote.getCsrType()) {// 0:远程开门 1:远程关门 2:允许启动 3:断油电 4:初始化
		// 5:同步时钟 6:远程取车 7:远程还车
		case 0:// 远程开门
			remoteOption.function = 0x05;
			remoteOption.address = 0x1000;
			remoteOption.value = ~0x00ff;
			break;
		case 1:// 远程关门
			remoteOption.function = 0x05;
			remoteOption.address = 0x1000;
			remoteOption.value = 0x0000;
			break;
		case 2:// 允许启动
			remoteOption.function = 0x05;
			remoteOption.address = 0x1004;
			remoteOption.value = ~0x00ff;
			break;
		case 3:// 禁止启动(断油电)
			remoteOption.function = 0x05;
			remoteOption.address = 0x1004;
			remoteOption.value = 0x0000;
			break;
		case 4:// 初始化
			remoteOption.function = 0x46;
			remoteOption.address = 0x0000;
			remoteOption.value = 0x0000;
			break;
		case 6:// 远程取车
			remoteOption.function = 0x47;
			remoteOption.address = 0x1000;
			remoteOption.value = 0x0006;
			break;
		case 7:// 远程还车
			remoteOption.function = 0x48;
			remoteOption.address = ~0x6fff;// 0x9000
			remoteOption.value = 0x0000;
			break;
		case 8:// 远程升级
			remoteOption.function = 0x49;
			remoteOption.address = ~0x3ffd;// 0xC002
			remoteOption.value = 0x0000;
			break;
		case 9:// 远程重启
			remoteOption.function = 0x50;
			remoteOption.address = ~0x3FFC;
			remoteOption.value = 0x0000;
			break;
		case 10:// GPS远程开
			remoteOption.function = 0x05;
			remoteOption.address = 0x1010;
			remoteOption.value = ~0x00ff;
			break;
		case 11:// GPS远程关
			remoteOption.function = 0x05;
			remoteOption.address = 0x1010;
			remoteOption.value = 0x0000;
			break;
		case 12:// 读取RFID序列号
			remoteOption.function = 0x03;
			remoteOption.address = ~0x6FFB;// 0x9004
			remoteOption.value = 0x0004;
			break;
		case 13:// 设置GPS坐标
			remoteOption.function = 0x10;
			remoteOption.address = ~0x6FED;// 0x9012
			remoteOption.value = 0x0004;
			break;
		case 14:// 查询当前设备的手机号
			remoteOption.function = 0x03;
			remoteOption.address = ~0x3FF9;// 0xC006
			remoteOption.value = 0x0006;
			break;
		case 15:// 刷卡器开
			remoteOption.function = 0x05;
			remoteOption.address = 0x1011;// 0x9012
			remoteOption.value = ~0x00ff;
			break;
		case 16:// 刷卡器关
			remoteOption.function = 0x05;
			remoteOption.address = 0x1011;// 0xC006
			remoteOption.value = 0x0000;
			break;
		case 17:// 远程鸣笛
			remoteOption.function = 0x05;
			remoteOption.address = 0x1012;// 0xC006
			remoteOption.value = ~0x00ff;
			break;
		}
		String strBytes = "";
		try {
			pushMqttClient.publish(getTopic(remote), remoteOption.getBytes(), 0, false);
			for (Byte b : remoteOption.getBytes()) {
				strBytes += (WebHelper.zerofill(Integer.toHexString(b & 0xFF), 2));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "mqtt连接失败";
		}
		return strBytes;
	}

	public static class ConnectTimerTask extends TimerTask {
		/**
		 * 此计时器任务要执行的操作。
		 */
		public void run() {
			try {
				if (pushMqttClient == null || !pushMqttClient.isConnected()) {
					// 重新连接，重新订阅
					// add at 2015-03-07 13:27
					if (conOpt == null) {
						conOpt = new MqttConnectOptions();
						conOpt.setUserName(USER_NAME);
						conOpt.setPassword(PASSWORD.toCharArray());
					}

					if (pushMqttClient == null) {
						pushMqttClient = new MqttClient($.config("mqtt.address"), $.config("application"), null);
					}

					if (!pushMqttClient.isConnected()) {
						pushMqttClient.connect(conOpt);
					}
				}
			} catch (MqttException ex) {
				ex.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取话题
	 * 
	 * @param remote
	 * @return
	 */
	private static String getTopic(CsRemote remote) {
		String topic = MQTT_PUSH_FORMAT.replace("{1}", remote.get$csrCar().getCscNumber().toUpperCase()).replace("{0}", "hzc");
		Object mqttFlag = remote.get$csrCar().getCscMqttFlag();
		if (mqttFlag != null && !$.empty(mqttFlag.toString())) {
			topic = mqttFlag.toString();
		}
		return topic;
	}

	static void customDealRemoteCommend(CsRemote remote) {
		if (remote.getCsrId() == null) {
			remote.setCsrState((short) 2);
			remote = remote.save();
		}
		if (remote.getCsrWay() == null)
			remote.setCsrWay((short) 0);
		if (remote.get$csrCar().getCscDeviceVesion().shortValue() == 0) {
			remote.setCsrCode(getDefaultRemoteCommend(remote));
			remote.setCsrState((short) 0);
			remote.update();
		} else {
			if (remote.getCsrWay().shortValue() == 0 || $.empty(remote.get$csrCar().getCscMobile())) {
				remote.setCsrCode(getMQTTRemoteCommend(remote));
				remote.setCsrState((short) 1);
				remote.update();
			} else if (remote.getCsrWay().shortValue() == 1) {
				remote.setCsrCode(getRemoteSMSContent(remote));
				remote.setCsrState((short) 1);
				remote.update();
			}
		}
	}
	
	/**
	 * 车辆远程控制，转到远程调用统一服务
	 * @param remote
	 */
	public static void dealRemoteCommend(CsRemote remote) {
		customDealRemoteCommend(remote);  //临时方案，新版的远程控制专用应用未上线
		
		//redirectRemoteCommend(remote.getCsrId());
	}
	/**
	 * 车辆远程控制转向
	 * @param id
	 */
	private static void redirectRemoteCommend(Long id) {
		String serviceUrl = $.config("service.domain");
		if($.empty(serviceUrl)){
			throw new RuntimeException("配置文件中未配置service.domain属性");
		}
		String strRet = $.post(serviceUrl+"/service/remote.invoke.do", $.add("id", id).add("sign", $.md5(id+"-"+SYSTEM.PRIVATE_KEY)), $.UTF8);	
		if($.empty(strRet)){
			throw new RuntimeException("远程操作调用失败");
		}
		Map result = $.eval(strRet);
		if(result==null){
			throw new RuntimeException("远程操作调用失败");
		}
		if(result.get("success")==null || ((Boolean)result.get("success"))==false){
			throw new RuntimeException("远程操作调用失败 - "+result.get("message"));
		}
	}
	public static void dealRemoteCommendFromService(CsRemote remote) {
		if (remote.getCsrId() == null) {
			remote.setCsrState((short) 2);
			remote = remote.save();
		}
		if (remote.getCsrWay() == null)
			remote.setCsrWay((short) 0);
		if (remote.get$csrCar().getCscDeviceVesion().shortValue() == 0) {
			remote.setCsrCode(getDefaultRemoteCommend(remote));
			remote.setCsrState((short) 0);
			remote.update();
		} else {
			if (remote.getCsrWay().shortValue() == 0 || $.empty(remote.get$csrCar().getCscMobile())) {
				remote.setCsrCode(getMQTTRemoteCommend(remote));
				remote.setCsrState((short) 1);
				remote.update();
			} else if (remote.getCsrWay().shortValue() == 1) {
				remote.setCsrCode(getRemoteSMSContent(remote));
				remote.setCsrState((short) 1);
				remote.update();
			}
		}
	}

	/**
	 * 获取默认网关的指令串
	 * 
	 * @param remote
	 * @return
	 */
	private static String getDefaultRemoteCommend(CsRemote remote) {
		String commend = "";
		char cType = "ABCDEF".charAt(remote.getCsrType());
		switch (remote.getCsrType()) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
			commend = "$,R,{0},{1},{2},#".replace("{0}", remote.getCsrNumber()).replace("{1}", "" + cType).replace("{2}", $.zerofill(remote.getCsrId(), 8));
			break;
		case 5:
			commend = "$,R,{0},{1},{2},{3},#".replace("{0}", remote.getCsrNumber()).replace("{1}", "" + cType).replace("{2}", $.zerofill(remote.getCsrId(), 8))
					.replace("{3}", $.date(new Date(new Date().getTime() + 60000), "yyyy:MM:dd:HH:mm:ss"));
			break;
		}
		return commend;
	}

	/**
	 * 获取远程短信控制的短信内容,同时把短信发下去
	 * 
	 * @param remote
	 * @param orderCode
	 * @return
	 */
	private static String getRemoteSMSContent(CsRemote remote) {
		// 获取当前远程车辆的订单验证码
		CsOrder order = CsOrder.Get($.add(CsOrder.F.csoCar, remote.getCsrCar()).add(CsOrder.F.csoStartTimeEnd, new Date())
				.add(CsOrder.F.csoFinishTimeStart, new Date()));
		if (order == null)
			return null;
		String orderCode = order.getCsoCode();
		String smsContent = "";
		switch (remote.getCsrType()) {
		case 0:
			smsContent = "$,{0},SMS,UNLOCK,$,#".replace("{0}", orderCode);
			break;
		case 1:
			smsContent = "$,{0},SMS,LOCK,$,#".replace("{0}", orderCode);
			break;
		case 2:
			smsContent = "$,{0},SMS,IMMOB,$,#".replace("{0}", orderCode);
			break;
		}
		UtilHelper.sendSms(order.getCsoHost(), remote.get$csrCar().getCscMobile(), smsContent, SMSType.提示类短信);
		return smsContent;
	}

	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub
		cause.printStackTrace();
		pushMqttClient = null;
	}

	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub

	}

	public void messageArrived(String topic, MqttMessage message) throws Exception {
		// TODO Auto-generated method stub

	}
}
