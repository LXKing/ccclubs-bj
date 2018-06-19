package com.ccclubs.action.receiver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.DateHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsOrder;
import com.ccclubs.util.mq.CStruct;
import com.ccclubs.util.mq.entity.FurtherCar;
import com.ccclubs.util.mq.entity.FurtherCarReply;
import com.ccclubs.util.mq.entity.OrderDownStream;
import com.ccclubs.util.mq.entity.OrderUpStream;
import com.ccclubs.util.mq.entity.RemoteOption;
import com.ccclubs.util.mq.entity.TakeCar;
import com.ccclubs.util.mq.entity.TakeCarReply;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

public class OrderInfoReceiver {

	private final static int			CONSTANT_SCAN_INTERVAL		= 20;																																									// 订单扫描时间，单位：秒
	private final static int			ORDER_INTERVAL				= 20 * 60;																																								// 订单提前发送时间，单位：秒
	private final static int			ORDER_LIMIT					= 60;																																									// 单次订单扫描数

	private static String				logPath						= null;
	private static Lazy3qDaoSupport		dao							= $.getDao("ccclubs_system");
	private static MqttClient			mqttClientReceiver			= null;
	private static MqttClient			mqttClientPublish			= null;
	private final static String			MQTT_HOST					= $.config("mqtt.address");
	private final static String			MQTT_CLIENT_RECEIVER		= $.config("applicationOrderInfoReceiver");
	private final static String			MQTT_CLIENT_PUBLISH			= $.config("applicationOrderInfoPublish");

	private final static String			ORDER_DOWN_STREAM_TEMPLATE	= "car/ser/{0}/rt_0/{1}/pri_0";

	private final static String			ORDER_SQL_PARAMS			= CsOrder.C.csoCarNumber + "," + CsOrder.C.csoStartTime + "," + CsOrder.C.csoFinishTime + "," + CsOrder.C.csoId + "," + CsOrder.C.csoEvRfid + "," + CsOrder.C.csoCode;

	private final static String			ORDER_TABLE					= "cs_order";
	private final static String			REMOTE_TABLE				= "cs_remote";
	private final static byte			ORDER_STREAM_FUC_CODE		= 0x44;
	private final static byte			ORDER_FURTHER_FUC_CODE_O	= 0x42;																																								// 汽油车
	private final static byte			ORDER_FURTHER_FUC_CODE_E	= 0x64;																																								// 电动车
	private final static byte			ORDER_TAKE_FUC_CODE			= 0x45;
	// ------------------------固定长度的接收内容 ↓↓↓
	private final static int			ORDER_UP_STREAM_LENGTH		= 29;
	private final static int			TAKE_CAR_LENGTH				= 33;																																									// CStruct.sizeof(CarStatus.class);
	private final static int			FURTHER_CAR_LENGTH			= 37;																																									// CStruct.sizeof(CarAlarm.class);
	private final static int			REMOTE_LENGTH				= 21;

	private final static long			ORDER_STATUS_RETURN			= 2l;
	private final static long			ORDER_STATUS_PEND			= 5l;

	private final static String			USER_NAME					= "qsxiaogang";
	private final static String			PASSWORD					= "513387369";

	private static MqttConnectOptions	conOpt						= new MqttConnectOptions();

	private static Map<Long, Date>		orderList					= new HashMap<Long, Date>();
	private static Timer				connectTimer				= new Timer();

	/**
	 * @param args
	 * @throws MqttException
	 */
	public static void main(String[] args) {
		try {

			ConnectTimerTask task = new ConnectTimerTask();
			connectTimer.schedule(task, 5 * 1000, 60 * 1000);

			Timer timer = new Timer();
			TimerTask orderTask = new OrderTimerTask();
			// 5秒后开始执行任务，并且每隔15秒执行一次
			timer.schedule(orderTask, 5000, CONSTANT_SCAN_INTERVAL * 1000);
		} catch (Exception e) {
			e.printStackTrace();
			writeLog(e.getMessage() + " - main exception");
		}
	}

	/**
	 * 重连任务
	 * 
	 * @author qsxiaogang
	 * 
	 */
	public static class ConnectTimerTask extends TimerTask {
		/**
		 * 此计时器任务要执行的操作。
		 */
		public void run() {
			try {
				// 如果连接失败次数大于等于最大失败次数
				if (mqttClientReceiver == null || mqttClientPublish == null || !mqttClientReceiver.isConnected() || !mqttClientPublish.isConnected()) {
					// 重新连接，重新订阅
					writeLog("开始连接");
					// add at 2015-03-07 13:27
					conOpt.setUserName(USER_NAME);
					conOpt.setPassword(PASSWORD.toCharArray());
					if (mqttClientReceiver == null) {
						mqttClientReceiver = new MqttClient(MQTT_HOST, MQTT_CLIENT_RECEIVER, null);
					}
					if (mqttClientPublish == null) {
						mqttClientPublish = new MqttClient(MQTT_HOST, MQTT_CLIENT_PUBLISH, null);
					}
					if (!mqttClientReceiver.isConnected()) {
						mqttClientReceiver.connect(conOpt);
					}
					if (!mqttClientPublish.isConnected()) {
						mqttClientPublish.connect(conOpt);
					}

					// 注册监听
					mqttClientReceiver.setCallback(orderReceiverCallback);

					if (mqttClientReceiver.isConnected()) {
						// 订阅状态信息
						subscribeTopic(mqttClientReceiver);
						writeLog("连接成功，取消重连");
					} else {
						mqttClientPublish = null;
						mqttClientReceiver = null;
						writeLog("连接失败，等待1分钟后重连");
					}
				}
			} catch (Throwable e) {
				e.printStackTrace();
				mqttClientPublish = null;
				mqttClientReceiver = null;
				writeLog(e.getMessage() + " ConnectTimerTask 重新连接 出错");
			}
		}
	}

	/**
	 * 回调事件
	 * 
	 * @author qsxiaogang
	 * 
	 */
	private static MqttCallback	orderReceiverCallback	= new MqttCallback() {
															public void connectionLost(Throwable arg0) {
																arg0.printStackTrace();
																mqttClientPublish = null;
																mqttClientReceiver = null;
																writeLog(arg0.getMessage() + " mqtt connectionLost 断开连接");
																// 重新连接
															}

															public void deliveryComplete(IMqttDeliveryToken arg0) {
																System.out.println("deliveryComplete - " + arg0.getMessageId());
															}

															// 接收到消息
															public void messageArrived(String topic, MqttMessage arg1) throws Exception {

																// 消息结果
																final byte[] mMessage = arg1.getPayload();
																$.trace("topic：" + topic);
																CStruct.trace(mMessage);
																// 只接收：订单上行信息，取车信息，还车信息，时间校正信息
																switch (mMessage.length) {
																	case REMOTE_LENGTH:
																		RemoteOption remoteOption = RemoteOption.readObject(mMessage, RemoteOption.class);
																		if (remoteOption != null && remoteOption.id != 0) {
																			updateRemote(remoteOption);
																		}
																		break;
																	case ORDER_UP_STREAM_LENGTH:
																		// 接收订单二次确认信息
																		OrderUpStream orderUpStream = OrderUpStream.readObject(mMessage, OrderUpStream.class);
																		if (orderUpStream != null && orderUpStream.mFucCode == ORDER_STREAM_FUC_CODE) {
																			writeLog("收到来自 车牌号：" + orderUpStream.mCarNum + "，订单号：" + orderUpStream.mOrderId + " 订单回复");
																			updateOrderState(orderUpStream.mOrderId, 2);
																			if (orderList.get(orderUpStream.mOrderId) != null) {
																				orderList.remove(orderUpStream.mOrderId);
																			}
																		}
																		break;
																	case TAKE_CAR_LENGTH:
																		// 接收取车信息
																		TakeCar takeCar = TakeCar.readObject(mMessage, TakeCar.class);
																		if (takeCar != null && takeCar.mFucCode == ORDER_TAKE_FUC_CODE) {
																			updateTakeCar(takeCar);
																			Timer timer = new Timer();
																			TimerTask task = new TakeCarRplyTimerTask(new TakeCarReply(takeCar.mCarNum, takeCar.mOrderId));
																			// 5秒后开始执行任务
																			timer.schedule(task, 5 * 1000);
																		}
																		break;
																	case FURTHER_CAR_LENGTH:
																		// 接收还车信息
																		FurtherCar furtherCar = FurtherCar.readObject(mMessage, FurtherCar.class);
																		if (furtherCar != null && (furtherCar.mFucCode == ORDER_FURTHER_FUC_CODE_O || furtherCar.mFucCode == ORDER_FURTHER_FUC_CODE_E)) {
																			updateFurtherCar(furtherCar);
																			Timer timer = new Timer();
																			TimerTask task = new FurtherCarRplyTimerTask(new FurtherCarReply(furtherCar.mCarNum, furtherCar.mOrderId));
																			// 5秒后开始执行任务
																			timer.schedule(task, 5 * 1000);
																		}
																		break;
																}
															}
														};

	/**
	 * 订单扫描任务
	 * 
	 * @author Administrator
	 * 
	 */
	public static class OrderTimerTask extends TimerTask {
		/**
		 * 此计时器任务要执行的操作。
		 */
		public void run() {
			try {
				Date contrastTime = new Date(new Date().getTime() + (ORDER_INTERVAL * 1000));// 20分钟后的时间
				
				/***************************2015-09-04刘代进加向前限制为7天前开始的订单，避免全表搜索**********************************/
				Date forwardTime =  new Date(new Date().getTime() - (SYSTEM.DAY*7));

				String strSql = "SELECT " + ORDER_SQL_PARAMS + " FROM " + ORDER_TABLE + " WHERE cso_start_time>'"+$.date(forwardTime, "yyyy-MM-dd HH:mm:ss")+"' and cso_status = 0 AND (cso_state = 0 OR cso_state = 1) AND  cso_device_vesion = 1  AND cso_start_time < '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(contrastTime) + "' LIMIT " + ORDER_LIMIT;
				List<Map> list = dao.executeQuery(strSql);

				writeLog("获得等待发送订单，共 " + list.size() + " 个");
				// $.trace(strSql);
				for (Map map : list) { 
					if (map != null) {
						// String topic =
						// ORDER_DOWN_STREAM_TEMPLATE.replace("{0}",
						// String.valueOf(map.get(CsOrder.C.csoCarNumber)).toUpperCase());
						OrderDownStream orderDownStream = new OrderDownStream(String.valueOf(map.get(CsOrder.C.csoCarNumber)).toUpperCase(), 0l, (int) ((((Timestamp) map.get(CsOrder.C.csoStartTime)).getTime() - SYSTEM.MACHINE_TIME) / 1000), (int) ((((Timestamp) (map.get(CsOrder.C.csoFinishTime)))
								.getTime() - SYSTEM.MACHINE_TIME) / 1000), (Long) (map.get(CsOrder.C.csoId)), String.valueOf(map.get(CsOrder.C.csoEvRfid)).toUpperCase(), Integer.parseInt(String.valueOf(map.get(CsOrder.C.csoCode))));
						// $.trace("开始发送 订单数据 ");
						CStruct.trace(orderDownStream.getBytes());
						if (orderList.get(orderDownStream.mOrderId) == null) {
							// 如果订单开始时间与当前时间不超过一天就发
							if (((Timestamp) map.get(CsOrder.C.csoStartTime)).getTime() + 10 * SYSTEM.DAY >= System.currentTimeMillis()) {
								orderList.put(orderDownStream.mOrderId, new Date());
								mqttClientPublish.publish(getTopic(map.get(CsOrder.C.csoCarNumber)), orderDownStream.getBytes(), 0, false);
								writeLog("发送订单，订单号： " + orderDownStream.mOrderId + "，车牌号：" + orderDownStream.mCarNum + "，topic：" + getTopic(map.get(CsOrder.C.csoCarNumber)));
								updateOrderState(map.get(CsOrder.C.csoId), 1);
							}
						} else {
							// 发过一次的订单，后面每隔 1分钟 再发
							if (new Date().getTime() - orderList.get(orderDownStream.mOrderId).getTime() >= 60 * 1000l) {
								if (((Timestamp) map.get(CsOrder.C.csoStartTime)).getTime() + 10 * SYSTEM.DAY >= System.currentTimeMillis()) {
									orderList.put(orderDownStream.mOrderId, new Date());
									mqttClientPublish.publish(getTopic(map.get(CsOrder.C.csoCarNumber)), orderDownStream.getBytes(), 0, false);
									writeLog("发送订单，订单号： " + orderDownStream.mOrderId + "，车牌号：" + orderDownStream.mCarNum + "，topic：" + getTopic(map.get(CsOrder.C.csoCarNumber)));
									updateOrderState(map.get(CsOrder.C.csoId), 1);
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				writeLog(e.getMessage() + " - OrderTimerTask exception");
			}
		}
	}

	/**
	 * 订阅指定主题，远程，订单，区分城市
	 * 
	 * @param mqttClient
	 */
	public static void subscribeTopic(MqttClient mqttClient) {
		try {
			// 杭州
			// 远程操作的回复
			mqttClient.subscribe("ser/car/hzc/rt_0/+/pri_2");
			// 订单的回复
			mqttClient.subscribe("ser/car/hzc/rt_0/+/pri_3");
			// 北京
			// 远程操作的回复
			mqttClient.subscribe("ser/car/bjh/rt_0/+/pri_2");
			// 订单的回复
			mqttClient.subscribe("ser/car/bjh/rt_0/+/pri_3");
			
			// 北京
			// 远程操作的回复
			//mqttClient.subscribe("ser/car/bjp/rt_0/+/pri_2");
			// 订单的回复
			//mqttClient.subscribe("ser/car/bjp/rt_0/+/pri_3");
		} catch (Exception e) {
			e.printStackTrace();
			writeLog(e.getMessage() + " - subscribeTopic exception");
		}
	}

	/**
	 * 根据车牌号，组织发送话题
	 * 
	 * @param carNum
	 * @param host
	 * @return
	 */
	private static String getTopic(Object carNum) {

		String topic = ORDER_DOWN_STREAM_TEMPLATE;
		if (carNum == null) {
			return topic;
		}

		topic = ORDER_DOWN_STREAM_TEMPLATE.replace("{1}", String.valueOf(carNum).toUpperCase()).replace("{0}", "hzc");

		String strSql = "SELECT * FROM cs_car WHERE csc_number = '" + carNum + "' AND csc_status > 0  LIMIT 1 ";
		List<Map> list = dao.executeQuery(strSql);
		// $.trace(strSql);
		for (Map map : list) {
			if (map != null) {
				if (map.get("csc_mqtt_flag") != null && !$.empty(map.get(CsCar.C.cscMqttFlag).toString())) {
					topic = map.get("csc_mqtt_flag").toString();
				}
			}
		}
		return topic;
	}

	/**
	 * 取车回复确认任务
	 * 
	 * @author qsxiaogang
	 * @date add at 2015-07-20 10:00
	 * 
	 */
	private static class TakeCarRplyTimerTask extends TimerTask {
		private TakeCarReply	takeCarReply;

		public TakeCarRplyTimerTask(TakeCarReply takeCarReply) {
			this.takeCarReply = takeCarReply;
		}

		/**
		 * 此计时器任务要执行的操作。
		 */
		public void run() {
			// Date executeTime = new Date(this.scheduledExecutionTime());
			try {
				String strSql = "SELECT * FROM " + ORDER_TABLE + " WHERE cso_id = " + takeCarReply.mOrderId;
				List<Map> list = dao.executeQuery(strSql);
				for (Map map : list) {
					if (map != null) {
						mqttClientPublish.publish(getTopic(map.get(CsOrder.C.csoCarNumber)), this.takeCarReply.getBytes(), 0, false);
					}
				}
			} catch (Throwable e) {
				e.printStackTrace();
				writeLog(e.getMessage() + " - TakeCarRplyTimerTask  exception ");
			}
		}
	}

	/**
	 * 还车回复确认任务
	 * 
	 * @author qsxiaogang
	 * 
	 */
	private static class FurtherCarRplyTimerTask extends TimerTask {
		private FurtherCarReply	furtherCarReply;

		public FurtherCarRplyTimerTask(FurtherCarReply furtherCarReply) {
			this.furtherCarReply = furtherCarReply;
		}

		/**
		 * 此计时器任务要执行的操作。
		 */
		public void run() {
			// Date executeTime = new Date(this.scheduledExecutionTime());
			try {
				String strSql = "SELECT * FROM " + ORDER_TABLE + " WHERE cso_id = " + furtherCarReply.mOrderId;
				List<Map> list = dao.executeQuery(strSql);
				for (Map map : list) {
					if (map != null) {
						mqttClientPublish.publish(getTopic(map.get(CsOrder.C.csoCarNumber)), this.furtherCarReply.getBytes(), 0, false);
					}
				}
			} catch (Throwable e) {
				e.printStackTrace();
				writeLog(e.getMessage() + " - FurtherCarRplyTimerTask  exception ");
			}
		}
	}

	/**
	 * 更新订单数据发送状态
	 * <p>
	 * 根据订单号，更新对应的订单信息发送状态
	 * </p>
	 * <p>
	 * 数据库操作
	 * </p>
	 * 
	 * @param csOrder
	 */
	private static void updateOrderState(Object order, Object value) {
		try {
			writeLog("更改订单状态，订单号：" + order + "  值：" + value);
			String strSql = "UPDATE " + ORDER_TABLE + " SET cso_state = " + value + " WHERE cso_id = " + order;
			dao.executeUpdate(strSql);
			// $.trace(strSql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			writeLog(e.getMessage() + " - updateOrderState  exception");
		}
	}

	/**
	 * 更新取车信息
	 * <p>
	 * 根据取车订单号和车牌号，更新实际取车时间
	 * </p>
	 * <p>
	 * 数据库操作
	 * </p>
	 * 
	 * @param takeCar
	 */
	private static void updateTakeCar(TakeCar takeCar) {
		try {
			String takeTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(takeCar.mTakeTime * 1000l + SYSTEM.MACHINE_TIME));
			String strSql = "UPDATE " + ORDER_TABLE + " SET cso_status = 1, cso_take_time = '" + takeTime + "' WHERE cso_id = " + takeCar.mOrderId + " AND cso_car_number = '" + takeCar.mCarNum + "'";
			dao.executeUpdate(strSql);
			writeLog("收到来自 车牌号：" + takeCar.mCarNum + "，订单号：" + takeCar.mOrderId + " 取车回复，取车内容，实际取车时间：" + takeTime);
			// $.trace(strSql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			writeLog(e.getMessage() + " - updateTakeCar exception");
		}
	}

	/**
	 * 更新还车信息
	 * <p>
	 * 根据还车订单号和车牌号，更新还车油里程，电里程为0，实际还车时间
	 * </p>
	 * <p>
	 * 数据库操作
	 * </p>
	 * 
	 * @param takeCar
	 */
	private static void updateFurtherCar(FurtherCar furtherCar) {
		try {
			String retTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(furtherCar.mFurtherTime * 1000l + SYSTEM.MACHINE_TIME));
			String memo = "";
			// 实际还车时间
			writeLog("收到来自 车牌号：" + furtherCar.mCarNum + "，订单号：" + furtherCar.mOrderId + " 还车回复，还车内容，订单里程：" + furtherCar.mMiles + "，实际还车时间：" + retTime);
			// 取得当前定单信息
			String strSql = "SELECT * FROM " + ORDER_TABLE + " WHERE cso_id =" + furtherCar.mOrderId; // + "
			// AND
			// (cso_status
			// = 0
			// OR
			// cso_status
			// =
			// 1)";
			List<Map> list = dao.executeQuery(strSql);
			for (Map map : list) {
				// 更新还车油里程，电里程为0，实际还车时间
				if (map != null) {
					// 首次还车
					if (Integer.parseInt(map.get(CsOrder.C.csoStatus).toString()) == 0 || Integer.parseInt(map.get(CsOrder.C.csoStatus).toString()) == 1) {
						// add at 2015-01-28 09:58 by xiaogang 针对下位机还车信息发送延迟
						memo = "车牌号：" + furtherCar.mCarNum + "，订单号：" + furtherCar.mOrderId + " 首次还车，订单里程：" + furtherCar.mMiles + "，实际还车时间：" + retTime;
						if ((furtherCar.mFurtherTime * 1000l + SYSTEM.MACHINE_TIME - ((Timestamp) map.get(CsOrder.C.csoFinishTime)).getTime()) >= 1 * 60 * 60 * 1000l) {
							// 修改为待处理
							dao.executeUpdate(getFurtherCarSql(furtherCar, retTime, ORDER_STATUS_PEND, "超时还车，系统修改订单状态为待处理"));
						} else {
							// 修改为已还车
							dao.executeUpdate(getFurtherCarSql(furtherCar, retTime, ORDER_STATUS_RETURN, "正常还车"));
						}
						writeLog(memo);
						return;
					} else {
						// 里程不一样，油里程
						if (furtherCar.mFucCode == ORDER_FURTHER_FUC_CODE_O) {
							if (furtherCar.mMiles != SystemHelper.toInt(Double.parseDouble(map.get(CsOrder.C.csoFuel).toString()))) {
								memo = "车牌号：" + furtherCar.mCarNum + "，订单号：" + furtherCar.mOrderId + " 多次还车，订单油里程：" + furtherCar.mMiles + "，实际还车时间：" + retTime + "  多次还车描述：前后油里程不一致，前：" + map.get(CsOrder.C.csoFuel) + "  后：" + furtherCar.mMiles;
								// dao.executeUpdate(getFurtherCarSql(furtherCar,
								// retTime, ORDER_STATUS_PEND,
								// "多次还车，前后油里程不一致，系统修改订单状态为待处理"));
								dao.execute(getOrderLogSql(furtherCar.mOrderId, memo));
								writeLog(memo);
								return;
							}
						} else {// 里程不一样，电里程
							if (furtherCar.mMiles != SystemHelper.toInt(Double.parseDouble(map.get(CsOrder.C.csoElectric).toString()))) {
								memo = "车牌号：" + furtherCar.mCarNum + "，订单号：" + furtherCar.mOrderId + " 多次还车，订单电里程：" + furtherCar.mMiles + "，实际还车时间：" + retTime + "  多次还车描述：前后里程不一致，前：" + map.get(CsOrder.C.csoElectric) + "  后：" + furtherCar.mMiles;
								// dao.executeUpdate(getFurtherCarSql(furtherCar,
								// retTime, ORDER_STATUS_PEND,
								// "多次还车，前后电里程不一致，系统修改订单状态为待处理"));
								dao.execute(getOrderLogSql(furtherCar.mOrderId, memo));

								writeLog(memo);
								return;
							}
						}
						// 还车时间在之前的5分钟左右
						if (map.get(CsOrder.C.csoRetTime) != null) {
							Long time = ((Timestamp) map.get(CsOrder.C.csoRetTime)).getTime();
							if ((furtherCar.mFurtherTime * 1000l + SYSTEM.MACHINE_TIME) >= (time + 5 * 60 * 1000l)) {
								memo = "车牌号：" + furtherCar.mCarNum + "，订单号：" + furtherCar.mOrderId + " 多次还车，订单里程：" + furtherCar.mMiles + "，实际还车时间：" + retTime + "  多次还车描述：前后还车时间不一致，前：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time)) + "  后："
										+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(furtherCar.mFurtherTime * 1000l + SYSTEM.MACHINE_TIME));
								// dao.executeUpdate(getFurtherCarSql(furtherCar,
								// retTime, ORDER_STATUS_PEND,
								// "多次还车，前后还车时间不一致，系统修改订单状态为待处理"));
								writeLog(memo);
								dao.execute(getOrderLogSql(furtherCar.mOrderId, memo));
								return;
							}
						}

						memo = "车牌号：" + furtherCar.mCarNum + "，订单号：" + furtherCar.mOrderId + " 多次还车，订单里程：" + furtherCar.mMiles + "，实际还车时间：" + retTime;
						// dao.executeUpdate(getFurtherCarSql(furtherCar,
						// retTime, ORDER_STATUS_PEND,
						// "多次还车，前后还车时间不一致，系统修改订单状态为待处理"));
						writeLog(getOrderLogSql(furtherCar.mOrderId, memo));
						dao.execute(getOrderLogSql(furtherCar.mOrderId, memo));
						return;
					}
				}
			}

			// $.trace(strSql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			writeLog(e.getMessage());
		}
	}

	private static String getOrderLogSql(Long order, String memo) {
		String sqlString = "INSERT INTO cs_order_log (csol_title,csol_order,csol_editor,csol_remark,csol_add_time) VALUES ('收到多次还车信息',{order},0,'{remark}','{addtime}')";
		sqlString = sqlString.replace("{order}", order.toString()).replace("{remark}", memo).replace("{addtime}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

		return sqlString;
	}

	/**
	 * 获得更新还车信息Sql语句
	 * 
	 * @param remoteOption
	 */
	private static String getFurtherCarSql(FurtherCar furtherCar, String retTime, Long orderStatus, String memo) {
		// " + furtherCar.mMiles + "
		String strRetSql = "UPDATE " + ORDER_TABLE + " SET cso_status = {3}, cso_fuel = {0}, cso_electric = {1}, cso_ret_time = '" + retTime + "' , cso_remark = concat_ws('',cso_remark,'{4}') WHERE cso_id = " + furtherCar.mOrderId;
		strRetSql = strRetSql.replace("{3}", orderStatus.toString()).replace("{4}", memo);
		switch (furtherCar.mFucCode) {
			case ORDER_FURTHER_FUC_CODE_O:
				strRetSql = strRetSql.replace("{0}", String.valueOf(furtherCar.mMiles)).replace("{1}", "0");
				break;
			case ORDER_FURTHER_FUC_CODE_E:
				strRetSql = strRetSql.replace("{1}", String.valueOf(furtherCar.mMiles)).replace("{0}", "0");
				break;
			default:
				strRetSql = strRetSql.replace("{0}", String.valueOf(furtherCar.mMiles)).replace("{1}", "0");
				break;
		}

		return strRetSql;
	}

	/**
	 * 远程操作回复
	 * 
	 * @param remoteOption
	 */
	private static void updateRemote(RemoteOption remoteOption) {
		try {
			String timeString = DateHelper.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
			String strSql = "UPDATE " + REMOTE_TABLE + " SET csr_status = 1,csr_update_time = '" + timeString + "' WHERE csr_id = " + remoteOption.id;
			dao.executeUpdate(strSql);
			writeLog("收到来自 车牌号：" + remoteOption.number + "，远程操作回复");
		} catch (Exception e) {
			e.printStackTrace();
			writeLog(e.getMessage());
		}
	}

	/**
	 * 获取日志路径
	 */
	private static void getLogPath() {
		try {
			logPath = OrderInfoReceiver.class.getResource("/").toURI().getPath() + "OrderInfoReceiverTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + ".txt";
		} catch (Exception ex) {
			System.out.println("获取文件路径时出错:" + ex.getMessage());
		}
	}

	/**
	 * 写日志
	 * 
	 * @param content
	 */
	private static void writeLog(String content) {
		// if (logPath == null) {
		getLogPath();
		// }
		if (logPath != null) {
			String timeString = DateHelper.formatDate(new Date(), "HH:mm:ss");
			writeAppendToFile(logPath, timeString + "   -   " + content, "UTF-8");
			$.trace(timeString + "   -   " + content);
		}
	}

	/**
	 * 追加到文件末尾，并且换行
	 * 
	 * @param fileName
	 * @param content
	 * @param encode
	 */
	private static void writeAppendToFile(String fileName, String content, String encode) {
		if (encode == null)
			encode = "UTF-8";
		FileWriter writer = null;
		try {
			fileName = fileName.replaceAll("\\\\", "/");
			fileName = fileName.replaceAll("//", "/");

			String filePath = fileName.substring(0, fileName.lastIndexOf("/"));

			if (!new File(filePath).exists())
				new File(filePath).mkdirs();

			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			writer = new FileWriter(fileName, true);
			writer.write(content + "\r\n");
		} catch (IOException ex) {
			System.out.println("写文件[" + fileName + "]到[" + fileName + "]时出错:" + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}