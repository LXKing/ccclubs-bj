package com.ccclubs.action.receiver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.DateHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.util.mq.CStruct;
import com.ccclubs.util.mq.ICarStatus;
import com.ccclubs.util.mq.entity.CarAlarm;
import com.ccclubs.util.mq.entity.CarStatus;
import com.ccclubs.util.mq.entity.CarStatusII;
import com.ccclubs.util.mq.entity.CarStatusIII;
import com.ccclubs.util.mq.entity.CarStatusIV;
import com.ccclubs.util.mq.entity.RemoteOption;
import com.ccclubs.util.mq.entity.TimeSynchronization;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

/**
 * 车辆状态信息接收器
 */
public class CarStatusReceiver {

	private static Lazy3qDaoSupport		dao								= $.getDao("ccclubs_system");
	// private static Lazy3qDaoSupport historyDao =
	// $.getDao("ccclubs_system_history");
	private static Lazy3qDaoSupport		historyDao						= $.getDao("ccclubs_system");
	private final static String			ORDER_DOWN_STREAM_TEMPLATE		= "car/ser/{0}/rt_0/{1}/pri_0";
	private final static int			TIME_SYNCHRONIZATION_INTERVAL	= 60;
	private final static int			TIME_GET_HOST_INTERVAL			= 30 * 60;

	private final static int			CONNECT_FAILE_MAX_INTERVAL		= 5;								// 重连失败最大次数
	private static int					CONNECT_CURRENT_FAILE_INTERVAL	= 0;								// 当前重连失败次数

	private static Map<String, String>	cars							= new HashMap<String, String>();
	private static Date					lastDate						= null;
	private static MqttClient			mqttClientReceiver				= null;
	private static MqttClient			mqttClientPublish				= null;

	private final static String			MQTT_CLIENT_RECEIVER			= $.config("applicationCarStatusReceiver");
	private final static String			MQTT_CLIENT_PUBLISH				= $.config("applicationCarStatusPublish");

	private final static String			CAR_TABLE						= "cs_car";
	private final static String			CAR_STATE_TABLE					= "cs_state";
	private final static String			HISTORY_STATE_TABLE_PREFIX		= "cs_history_state";
	private final static String			ALARM_STATE_TABLE				= "cs_alarm";

	private final static byte			CAR_STATUS_FUC_CODE				= 0x41;
	private final static byte			CAR_STATUS_III_FUC_CODE			= 0x6D;
	private final static byte			CAR_STATUS_IV_FUC_CODE			= 0x66;
	private final static byte			CAR_ALARM_FUC_CODE				= 0x43;
	private final static byte			CAR_NO_ALARM_CODE				= 0x00;							// 没报警

	private final static String			MQTT_HOST						= $.config("mqtt.address");

	private final static int			CAR_STATUS_LENGTH				= 56;								// CStruct.sizeof(CarStatus.class);
	private final static int			CAR_ALARM_LENGTH				= 18;
	private final static int			CAR_STATUS_LENGTH_II			= 69;								// 7个CAN包数据
	private final static int			CAR_STATUS_LENGTH_III			= 181;								// 7个CAN包数据
	private final static int			CAR_STATUS_LENGTH_IV			= 72;

	private final static String			USER_NAME						= "qsxiaogang";
	private final static String			PASSWORD						= "513387369";
	private static MqttConnectOptions	conOpt							= new MqttConnectOptions();

	private static Timer				connectTimer					= new Timer();
	private static String				logPath							= null;

	static {
		initCarStatusTable();
		initCarAlarmTable();
	}

	/**
	 * 创建车牌号对应的车辆状态信息表
	 */
	public static void initCarStatusTable() {
		cars.clear();
		List<Map> list = dao.executeQuery(" select csc_number,csc_host from " + CAR_TABLE + " where csc_status <> -1");
		// 设置获取数据时间
		lastDate = new Date();
		for (Map map : list) {
			createTable(map.get("csc_number").toString(), map.get("csc_host").toString());
		}
	}

	public static void createTable(String number, String host) {
		// historyDao.execute("CREATE table if not exists " +
		// HISTORY_STATE_TABLE_PREFIX + "_" + number.toLowerCase() + " LIKE " +
		// HISTORY_STATE_TABLE_PREFIX);
		cars.put(number.toUpperCase(), host);
	}

	/**
	 * 创建车辆报警表
	 */
	public static void initCarAlarmTable() {
		String strSql = "CREATE TABLE IF NOT EXISTS `" + ALARM_STATE_TABLE + "` " + "(`csa_id` bigint(20) NOT NULL auto_increment," + "`csa_number` varchar(32) NOT NULL," + "`csa_add_time` datetime NOT NULL," + "`csa_info` varchar(32) NOT NULL," + "`csa_order` bigint(20) NOT NULL,"
				+ "PRIMARY KEY  (`csa_id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		dao.execute(strSql);
	}

	/**
	 * The main entry point of the sample.
	 * 
	 * This method handles parsing the arguments specified on the command-line
	 * before performing the specified action.
	 */
	public static void main(String[] args) {
		try {
			ConnectTimerTask task = new ConnectTimerTask();
			connectTimer.schedule(task, 5 * 1000, 60 * 1000);
			// 连接
			// mqttClientReceiver.connect(conOpt);
			// mqttClientPublish.connect(conOpt);
			// 订阅状态信息
			// subscribeTopic(mqttClientReceiver);
		} catch (Exception e) {
			e.printStackTrace();
			writeLog(e.getMessage() + " main 出错");
		}
	}

	/**
	 * 回调事件
	 * 
	 * @author qsxiaogang
	 * 
	 */
	private static MqttCallback	carStatusCallback	= new MqttCallback() {

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
														public void messageArrived(String topic, MqttMessage message) throws Exception {
															// 消息结果
															final byte[] mMessage = message.getPayload();
															// CStruct.trace(mMessage);
															// 只接收：状态信息，报警信息
															switch (mMessage.length) {
																case CAR_STATUS_LENGTH:
																	CarStatus carStatus = CarStatus.readObject(mMessage, CarStatus.class);
																	if (carStatus != null && carStatus.mFucCode == CAR_STATUS_FUC_CODE) {
																		CStruct.trace(mMessage);
																		$.trace("收到来自 " + carStatus.mCarNum + "的状态数据");
																		// TODO:是否需要判断车牌号的正确性
																		writtenToDatabase(carStatus, 0);
																		writtenToDatabase(carStatus, 1);
																		if (carStatus.mAlarmType != CAR_NO_ALARM_CODE) {
																			writtenToDatabase(new CarAlarm(carStatus.mCarNum, carStatus.mOrderId, carStatus.mAlarmType));
																		}
																		Timer timer = new Timer();
																		TimerTask task = new CarTimerTask(carStatus);
																		// 10秒后开始执行任务
																		timer.schedule(task, 10000);
																	}
																	break;
																case CAR_STATUS_LENGTH_II:
																	CarStatusII carStatusII = CarStatusII.readObject(mMessage, CarStatusII.class);
																	if (carStatusII != null && carStatusII.mFucCode == CAR_STATUS_FUC_CODE) {
																		CStruct.trace(mMessage);
																		$.trace("收到来自 " + carStatusII.mCarNum + "的状态数据");
																		// TODO:是否需要判断车牌号的正确性
																		writtenToDatabase(carStatusII, 0);
																		writtenToDatabase(carStatusII, 1);
																		if (carStatusII.mAlarmType != CAR_NO_ALARM_CODE) {
																			writtenToDatabase(new CarAlarm(carStatusII.mCarNum, carStatusII.mOrderId, carStatusII.mAlarmType));
																		}
																		Timer timerII = new Timer();
																		TimerTask taskII = new CarTimerTask(carStatusII);
																		// 10秒后开始执行任务
																		timerII.schedule(taskII, 10000);
																	}
																	break;
																case CAR_STATUS_LENGTH_III:
																	CarStatusIII carStatusIII = CarStatusIII.readObject(mMessage, CarStatusIII.class);
																	if (carStatusIII != null && carStatusIII.mFucCode == CAR_STATUS_III_FUC_CODE) {
																		CStruct.trace(mMessage);
																		$.trace("收到来自 " + carStatusIII.mCarNum + "的状态数据");
																		// TODO:是否需要判断车牌号的正确性
																		writtenToDatabase(carStatusIII, 0);
																		writtenToDatabase(carStatusIII, 1);
																		if (carStatusIII.mAlarmType != CAR_NO_ALARM_CODE) {
																			writtenToDatabase(new CarAlarm(carStatusIII.mCarNum, carStatusIII.mOrderId, carStatusIII.mAlarmType));
																		}
																		Timer timerIII = new Timer();
																		TimerTask taskIII = new CarTimerTask(carStatusIII);
																		// 10秒后开始执行任务
																		timerIII.schedule(taskIII, 10000);
																	}
																	break;
																case CAR_STATUS_LENGTH_IV:
																	CarStatusIV carStatusIV = CarStatusIV.readObject(mMessage, CarStatusIV.class);
																	if (carStatusIV != null && carStatusIV.mFucCode == CAR_STATUS_IV_FUC_CODE) {
																		CStruct.trace(mMessage);
																		$.trace("收到来自 " + carStatusIV.mCarNum + "的状态数据");
																		// TODO:是否需要判断车牌号的正确性
																		writtenToDatabase(carStatusIV, 0);
																		writtenToDatabase(carStatusIV, 1);
																		if (carStatusIV.mAlarmType != CAR_NO_ALARM_CODE) {
																			writtenToDatabase(new CarAlarm(carStatusIV.mCarNum, carStatusIV.mOrderId, carStatusIV.mAlarmType));
																		}
																		Timer timerIV = new Timer();
																		TimerTask taskIV = new CarTimerTask(carStatusIV);
																		// 10秒后开始执行任务
																		timerIV.schedule(taskIV, 10000);
																	}
																	break;
																case CAR_ALARM_LENGTH:
																	CarAlarm carAlarm = CarAlarm.readObject(mMessage, CarAlarm.class);
																	if (carAlarm != null && carAlarm.mFucCode == CAR_ALARM_FUC_CODE) {
																		// TODO:是否需要判断车牌号的正确性
																		CStruct.trace(mMessage);
																		$.trace("收到来自 " + carAlarm.mCarNum + "的状态数据");
																		if (carAlarm.mAlarmType != CAR_NO_ALARM_CODE) {
																			writtenToDatabase(carAlarm);
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
			// 杭州

			// 远程操作的回复
			mqttClient.subscribe("ser/car/hzc/rt_0/+/pri_0");
			// 订单的回复
			mqttClient.subscribe("ser/car/hzc/rt_0/+/pri_4");
			// 北京
			// 远程操作的回复
			mqttClient.subscribe("ser/car/bjp/rt_0/+/pri_0");
			// 订单的回复
			mqttClient.subscribe("ser/car/bjp/rt_0/+/pri_4");
		} catch (Exception e) {
			e.printStackTrace();
			writeLog(e.getMessage() + " subscribeTopic 出错");
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

		String strSql = "SELECT * FROM cs_car WHERE csc_number = '" + carNum + "'  AND  csc_status <>-1  LIMIT 1 ";
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
	 * 同步时间任务
	 * 
	 * @author qsxiaogang
	 * 
	 */
	public static class CarTimerTask extends TimerTask {
		private ICarStatus	carStatus;

		public CarTimerTask(ICarStatus carStatus) {
			this.carStatus = carStatus;
		}

		/**
		 * 此计时器任务要执行的操作。
		 */
		public void run() {
			// Date executeTime = new Date(this.scheduledExecutionTime());
			try {
				correctionCarTime(this.carStatus);
				correctionOrderStatus(this.carStatus);
			} catch (Throwable e) {
				e.printStackTrace();
				writeLog(e.getMessage() + " 校正车辆时间出错");
			}
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
				CONNECT_CURRENT_FAILE_INTERVAL++;
				writeLog(e.getMessage() + " 第 " + CONNECT_CURRENT_FAILE_INTERVAL + "次 ConnectTimerTask 重新连接 出错");
				// 超过最大重连次数
				if (CONNECT_CURRENT_FAILE_INTERVAL >= CONNECT_FAILE_MAX_INTERVAL) {
					// TODO:
				}
			}
		}
	}

	/**
	 * 将车辆状态信息写入数据库
	 * 
	 * @param carStatus
	 */
	private static void writtenToDatabase(CarStatus carStatus, int connectType) {
		try {
			// 每30分钟重新获取车辆地域信息
			if (cars.get(carStatus.mCarNum) == null || lastDate == null || new Date().getTime() - lastDate.getTime() >= TIME_GET_HOST_INTERVAL * 1000l) {
				initCarStatusTable();
			}
			switch (connectType) {
				case 1:
					String sNumber = carStatus.mCarNum;
					sNumber = sNumber.toUpperCase();
					// historyDao.execute(CarStatus.getInsertSql(HISTORY_STATE_TABLE_PREFIX
					// + "_" + sNumber, carStatus, connectType,
					// cars.get(sNumber)));
					historyDao.execute(CarStatus.getInsertSql(HISTORY_STATE_TABLE_PREFIX, carStatus, connectType, cars.get(sNumber)));

					break;
				case 0:
					// 先尝试更新，如果更新没成功，执行插入操作
					int result = dao.executeUpdate(CarStatus.getUpdateSql(CAR_STATE_TABLE, carStatus, cars.get(carStatus.mCarNum)));
					if (!(result > 0)) {
						dao.executeUpdate(CarStatus.getInsertSql(CAR_STATE_TABLE, carStatus, connectType, cars.get(carStatus.mCarNum)));
					}
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			writeLog(e.getMessage() + " writtenToDatabase 出错 ");
		}
	}

	/**
	 * 将车辆状态信息写入数据库(第二版)
	 * 
	 * @param carStatus
	 */
	private static void writtenToDatabase(CarStatusII carStatus, int connectType) {
		try {
			// 每30分钟重新获取车辆地域信息
			if (cars.get(carStatus.mCarNum) == null || lastDate == null || new Date().getTime() - lastDate.getTime() >= TIME_GET_HOST_INTERVAL * 1000l) {
				initCarStatusTable();
			}
			switch (connectType) {
				case 1:
					String sNumber = carStatus.mCarNum;
					sNumber = sNumber.toUpperCase();
					// historyDao.execute(CarStatusII.getInsertSql(HISTORY_STATE_TABLE_PREFIX
					// + "_" + sNumber, carStatus, connectType,
					// cars.get(sNumber)));
					historyDao.execute(CarStatusII.getInsertSql(HISTORY_STATE_TABLE_PREFIX, carStatus, connectType, cars.get(sNumber)));

					break;
				case 0:
					// 先尝试更新，如果更新没成功，执行插入操作
					int result = dao.executeUpdate(CarStatusII.getUpdateSql(CAR_STATE_TABLE, carStatus, cars.get(carStatus.mCarNum)));
					if (!(result > 0)) {
						dao.executeUpdate(CarStatusII.getInsertSql(CAR_STATE_TABLE, carStatus, connectType, cars.get(carStatus.mCarNum)));
					}
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			writeLog(e.getMessage() + " writtenToDatabase 出错 ");
		}
	}

	/**
	 * 将车辆状态信息写入数据库
	 * 
	 * @param carStatus
	 */
	private static void writtenToDatabase(CarStatusIII carStatus, int connectType) {
		try {
			// 每30分钟重新获取车辆地域信息
			if (cars.get(carStatus.mCarNum) == null || lastDate == null || new Date().getTime() - lastDate.getTime() >= TIME_GET_HOST_INTERVAL * 1000l) {
				initCarStatusTable();
			}
			switch (connectType) {
				case 1:
					String sNumber = carStatus.mCarNum;
					sNumber = sNumber.toUpperCase();
					// historyDao.execute(CarStatusIII.getInsertSql(HISTORY_STATE_TABLE_PREFIX
					// + "_" + sNumber, carStatus, connectType,
					// cars.get(sNumber)));
					historyDao.execute(CarStatusIII.getInsertSql(HISTORY_STATE_TABLE_PREFIX, carStatus, connectType, cars.get(sNumber)));

					break;
				case 0:
					// 先尝试更新，如果更新没成功，执行插入操作
					int result = dao.executeUpdate(CarStatusIII.getUpdateSql(CAR_STATE_TABLE, carStatus, cars.get(carStatus.mCarNum)));
					if (!(result > 0)) {
						dao.executeUpdate(CarStatusIII.getInsertSql(CAR_STATE_TABLE, carStatus, connectType, cars.get(carStatus.mCarNum)));
					}
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			writeLog(e.getMessage() + " writtenToDatabase 出错 ");
		}
	}

	/**
	 * 将车辆状态信息写入数据库
	 * 
	 * @param carStatus
	 */
	private static void writtenToDatabase(CarStatusIV carStatus, int connectType) {
		try {
			// 每30分钟重新获取车辆地域信息
			if (cars.get(carStatus.mCarNum) == null || lastDate == null || new Date().getTime() - lastDate.getTime() >= TIME_GET_HOST_INTERVAL * 1000l) {
				initCarStatusTable();
			}
			switch (connectType) {
				case 1:
					String sNumber = carStatus.mCarNum;
					sNumber = sNumber.toUpperCase();
					// historyDao.execute(CarStatusIV.getInsertSql(HISTORY_STATE_TABLE_PREFIX
					// + "_" + sNumber, carStatus, connectType,
					// cars.get(sNumber)));
					historyDao.execute(CarStatusIV.getInsertSql(HISTORY_STATE_TABLE_PREFIX, carStatus, connectType, cars.get(sNumber)));

					break;
				case 0:
					// 先尝试更新，如果更新没成功，执行插入操作
					int result = dao.executeUpdate(CarStatusIV.getUpdateSql(CAR_STATE_TABLE, carStatus, cars.get(carStatus.mCarNum)));
					if (!(result > 0)) {
						dao.executeUpdate(CarStatusIV.getInsertSql(CAR_STATE_TABLE, carStatus, connectType, cars.get(carStatus.mCarNum)));
					}
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			writeLog(e.getMessage() + " writtenToDatabase 出错 ");
		}
	}

	/**
	 * 校正下位机时间，发送远程时钟同步命令
	 * <p>
	 * 如果时间相差大于设置的阈值，则进行时间校正，发送时间同步
	 * </p>
	 * 
	 * @param takeCar
	 */
	public static void correctionCarTime(ICarStatus carStatus) {
		try {
			long now = new Date().getTime();
			// 如果时间相差大于设置的阈值，则进行时间校正，发送时间同步
			if (Math.abs(now - SYSTEM.MACHINE_TIME - carStatus.getTime() * 1000l) > TIME_SYNCHRONIZATION_INTERVAL * 1000l) {
				// String topic = ORDER_DOWN_STREAM_TEMPLATE.replace("{0}",
				// carStatus.getCarNum().toUpperCase());
				// TODO:还未实现Transaction ID的写入数据库操作
				TimeSynchronization timeSynchronization = new TimeSynchronization(carStatus.getCarNum().toUpperCase(), 0l, (int) ((new Date().getTime() - SYSTEM.MACHINE_TIME) / 1000));

				List<Map> list = dao.executeQuery("select csc_host from " + CAR_TABLE + " where csc_number = '" + carStatus.getCarNum() + "'");
				// 设置获取数据时间
				lastDate = new Date();
				for (Map map : list) {
					if (map != null && mqttClientPublish != null && mqttClientPublish.isConnected()) {
						mqttClientPublish.publish(getTopic(carStatus.getCarNum().toUpperCase()), timeSynchronization.getBytes(), 0, false);
					}
				}
				writeLog("给 " + carStatus.getCarNum() + "发送同步时间");
			}
		} catch (Throwable e) {
			e.printStackTrace();
			writeLog(e.getMessage() + " correctionCarTime 发送同步时间出错");
		}
	}

	/**
	 * // TODO:1、车辆状态 >= 3 时，如果订单状态是 0 ，则将订单状态变更为 1
	 * 2、如果车辆上有订单，但是该订单状态是已取消或者已作废，发送初始化命令
	 * 
	 * @param carStatus
	 */
	public static void correctionOrderStatus(ICarStatus carStatus) {
		try {
			if (carStatus.getOrderId() == 0) {
				return;
			}

			List<Map> list = dao.executeQuery("select cso_status,cso_host,cso_car from cs_order where  cso_id = " + carStatus.getOrderId());

			for (Map map : list) {
				if (map != null) {
					int status = (Integer) map.get("cso_status");
					// 该订单状态是已取消或者已作废，发送初始化命令
					if (status == 3 || status == 7 || status == 4) {
						// CsRemote csRemote = new CsRemote();
						// csRemote.setCsrNumber(carStatus.getCarNum());
						// csRemote.setCsrCar((Long) map.get("cso_car"));
						// csRemote.setCsrHost((Long) map.get("cso_host"));
						// csRemote.setCsrEditor(0l);
						// csRemote.setCsrAddTime(new Date());
						// csRemote.setCsrState((short) 2);
						// csRemote.setCsrStatus((short) 0);
						// csRemote.setCsrType((short) 4);
						// csRemote.setCsrRemark("车机上的订单状态为已取消或者已作废，发送初始化！");
						RemoteOption remoteOption = new RemoteOption();
						remoteOption.id = 0l;
						remoteOption.number = carStatus.getCarNum();
						// 初始化命令对应的值
						remoteOption.function = 0x46;
						remoteOption.address = 0x0000;
						remoteOption.value = 0x0000;
						// 发送初始化命令
						mqttClientPublish.publish(getTopic(carStatus.getCarNum().toUpperCase()), remoteOption.getBytes(), 0, false);

						writeLog(CarStatusReceiver.class.getResource("/").toURI().getPath() + "RemoteTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + ".txt", carStatus.getCarNum() + "  " + carStatus.getOrderId() + " 车机上的订单状态为已取消或者已作废，发送初始化！");
						return;
					}

					// 车辆状态 >= 3 时，如果订单状态是 0 ，则将订单状态变更为 1
					if (carStatus.getCarStatus() >= 3 && status == 0) {
						String strSql = "UPDATE cs_order SET cso_status = 1 , cso_state = 2 WHERE cso_id = " + carStatus.getOrderId();
						dao.executeUpdate(strSql);
						writeLog(CarStatusReceiver.class.getResource("/").toURI().getPath() + "RemoteTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + ".txt", carStatus.getCarNum() + " 车机上的状态已经是在使用中，更新已预订状态为使用中");
						return;
					}
					// mqttClientPublish.publish(getTopic(carStatus.getCarNum().toUpperCase()),
					// timeSynchronization.getBytes(), 0, false);
				}
			}

		} catch (Throwable e) {
			e.printStackTrace();
			writeLog(e.getMessage() + " correctionOrderStatus 更正订单状态出错");
		}
	}

	/**
	 * 将车辆报警信息写入数据库
	 * 
	 * @param carAlarm
	 */
	private static void writtenToDatabase(CarAlarm carAlarm) {
		try {
			dao.execute(CarAlarm.getInsertSql(ALARM_STATE_TABLE, carAlarm));
		} catch (Exception e) {
			e.printStackTrace();
			writeLog(e.getMessage() + " writtenToDatabase 出错");
		}
	}

	/**
	 * 获取日志路径
	 */
	private static void getLogPath() {
		try {
			logPath = CarStatusReceiver.class.getResource("/").toURI().getPath() + "CarStatusReceiverTemp/" + DateHelper.formatDate(new Date(), "yyyy-MM-dd") + ".txt";
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
		String timeString = DateHelper.formatDate(new Date(), "HH:mm:ss");
		$.trace(timeString + "   -   " + content);
		// if (logPath == null) {
		getLogPath();
		// }
		if (logPath != null) {
			writeAppendToFile(logPath, timeString + "   -   " + content, "UTF-8");
		}
	}

	/**
	 * 写日志，指定目录
	 * 
	 * @param content
	 */
	private static void writeLog(String path, String content) {
		String timeString = DateHelper.formatDate(new Date(), "HH:mm:ss");
		$.trace(timeString + "   -   " + content);
		// if (logPath == null) {
		// getLogPath();
		// }
		// if (logPath != null) {
		writeAppendToFile(path, timeString + "   -   " + content, "UTF-8");
		// }
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
