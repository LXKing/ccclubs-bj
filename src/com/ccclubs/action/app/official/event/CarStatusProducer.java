package com.ccclubs.action.app.official.event;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.ccclubs.util.mq.ICarStatus;
import com.ccclubs.util.mq.entity.CarAlarm;
import com.ccclubs.util.mq.entity.CarStatusII;
import com.ccclubs.util.mq.entity.CarStatusIV;
import com.lazy3q.web.helper.$;

/**
 * 车辆状态信息接收器
 */
public class CarStatusProducer{

	private final static int			CONNECT_FAILE_MAX_INTERVAL		= 5;								// 重连失败最大次数
	private static int					CONNECT_CURRENT_FAILE_INTERVAL	= 0;								// 当前重连失败次数
	private static MqttClient			mqttClientReceiver				= null;
	private static MqttClient			mqttClientPublish				= null;
	private final static String			MQTT_CLIENT_RECEIVER			= $.config("applicationCarStatusReceiver");
	private final static String			MQTT_CLIENT_PUBLISH				= $.config("applicationCarStatusPublish");
	private final static byte			CAR_STATUS_IV_FUC_CODE			= 0x66;
	private final static byte			CAR_ALARM_FUC_CODE				= 0x43;
	private final static String			MQTT_HOST						= $.config("mqtt.address");
	private final static int			CAR_ALARM_LENGTH				= 18;								//车辆报警
	private final static int			CAR_STATUS_LENGTH_II			= 69;								// 7个CAN包数据
	private final static int			CAR_STATUS_LENGTH_IV			= 72;								//(北汽鹏龙)下位机消息长度
	private final static String			USER_NAME						= "qsxiaogang";
	private final static String			PASSWORD						= "513387369";
	private final static byte			CAR_NO_ALARM_CODE				= 0x00;								// 没报警
	private final static byte			CAR_STATUS_FUC_CODE				= 0x41;
	private static MqttConnectOptions	conOpt							= new MqttConnectOptions();
	private static Timer				connectTimer					= new Timer();
	
	/**
	 * 车辆状态消息队列
	 */
    public BlockingQueue<ICarStatus> carStatusQueue;  
    
    public static AtomicBoolean init = new AtomicBoolean(false);				//是否已启动实例
	
	final static Logger log = Logger.getRootLogger();
	
	public CarStatusProducer(BlockingQueue<ICarStatus> carStatusQueue){
		this.carStatusQueue = carStatusQueue;
	}
	
	/**
	 * 初始化
	 */
	public synchronized void init() {
		
		if(init.get()){
			return;
		}
		
		try {
			ConnectTimerTask task = new ConnectTimerTask();
			connectTimer.schedule(task, 5 * 1000, 60 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		init.set(true);
	}

	/**
	 * 回调事件
	 * 
	 * @author qsxiaogang
	 * 
	 */
	private MqttCallback	carStatusCallback	= new MqttCallback() {

														public void connectionLost(Throwable arg0) {
															arg0.printStackTrace();
															mqttClientPublish = null;
															mqttClientReceiver = null;
															log.info(arg0.getMessage() + " mqtt connectionLost 断开连接");
															// 重新连接
														}

														public void deliveryComplete(IMqttDeliveryToken arg0) {
															System.out.println("deliveryComplete - " + arg0.getMessageId());
														}

														// 接收到消息
														public void messageArrived(String topic, MqttMessage message) throws Exception {
															// 消息结果
															final byte[] mMessage = message.getPayload();
															// CStruct.trace(mMessage);
															// 只接收：状态信息，报警信息
															switch (mMessage.length) {
																case CAR_STATUS_LENGTH_II:
																	CarStatusII carStatusII = CarStatusII.readObject(mMessage, CarStatusII.class);
																	if (carStatusII != null && carStatusII.mFucCode == CAR_STATUS_FUC_CODE) {
																		$.trace("收到来自 " + carStatusII.mCarNum + "的状态数据");
																		
																		carStatusQueue.put(carStatusII);
																		if (carStatusII.mAlarmType != CAR_NO_ALARM_CODE) {
//																			//ignore
																		}
																	}
																	break;
																case CAR_STATUS_LENGTH_IV:
																	CarStatusIV carStatusIV = CarStatusIV.readObject(mMessage, CarStatusIV.class);
																	if (carStatusIV != null && carStatusIV.mFucCode == CAR_STATUS_IV_FUC_CODE) {
																		
																		carStatusQueue.put(carStatusIV);
																		if (carStatusIV.mAlarmType != CAR_NO_ALARM_CODE) {
																			//ignore
																		}
																	}
																	break;
																case CAR_ALARM_LENGTH:
																	CarAlarm carAlarm = CarAlarm.readObject(mMessage, CarAlarm.class);
																	if (carAlarm != null && carAlarm.mFucCode == CAR_ALARM_FUC_CODE) {
																		// TODO:是否需要判断车牌号的正确性
																		if (carAlarm.mAlarmType != CAR_NO_ALARM_CODE) {
																			//ignore
																		}
																	}
																	break;
															}
														}
													};

	/**
	 * 订阅指定主题，远程，订单，区分城市
	 * 
	 * @param mqttClient
	 */
	public static void subscribeTopic(MqttClient mqttClient) {
		try {
			// 远程操作的回复
			mqttClient.subscribe("ser/car/bjp/rt_0/+/pri_0");
			// 订单的回复
			mqttClient.subscribe("ser/car/bjp/rt_0/+/pri_4");
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e.getMessage() + " subscribeTopic 出错");
		}
	}

	/**
	 * 重连任务
	 * 
	 * @author qsxiaogang
	 * 
	 */
	public class ConnectTimerTask extends TimerTask {
		/**
		 * 此计时器任务要执行的操作。
		 */
		public void run() {
			try {
				// 如果连接失败次数大于等于最大失败次数
				if (mqttClientReceiver == null || mqttClientPublish == null || !mqttClientReceiver.isConnected() || !mqttClientPublish.isConnected()) {
					// 重新连接，重新订阅
					log.info("开始连接");
					// add at 2015-03-07 13:27
					conOpt = new MqttConnectOptions();
					conOpt.setUserName(USER_NAME);
					conOpt.setPassword(PASSWORD.toCharArray());
					mqttClientReceiver = new MqttClient(MQTT_HOST, MQTT_CLIENT_RECEIVER, null);
					mqttClientPublish = new MqttClient(MQTT_HOST, MQTT_CLIENT_PUBLISH, null);

					mqttClientPublish.connect(conOpt);
					mqttClientReceiver.connect(conOpt);
					// 注册监听
					mqttClientReceiver.setCallback(carStatusCallback);

					if (mqttClientReceiver.isConnected() && mqttClientPublish.isConnected()) {
						// 订阅状态信息
						subscribeTopic(mqttClientReceiver);
						log.info("连接成功，取消重连");
					} else {
						mqttClientPublish = null;
						mqttClientReceiver = null;
						log.info("连接失败，等待1分钟后重连");
					}
				}
			} catch (Throwable e) {
				e.printStackTrace();
				mqttClientPublish = null;
				mqttClientReceiver = null;
				CONNECT_CURRENT_FAILE_INTERVAL++;
				log.info(e.getMessage() + " 第 " + CONNECT_CURRENT_FAILE_INTERVAL + "次 ConnectTimerTask 重新连接 出错");
				// 超过最大重连次数
				if (CONNECT_CURRENT_FAILE_INTERVAL >= CONNECT_FAILE_MAX_INTERVAL) {
					// TODO:
				}
			}
		}
	}

}
