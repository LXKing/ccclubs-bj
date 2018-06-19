package com.ccclubs.util.mq.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.util.mq.CStruct;
import com.ccclubs.util.mq.ICarStatus;
import com.ccclubs.util.mq.annotation.size;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.JsonHelper;

/**
 * 远程车辆发上来的电动车状态信息III(第三版)
 * <p>
 * 包括车牌号，订单号，RFID，经、纬度，里程，油量等
 * </p>
 * 
 * @author Administrator
 * 
 */
public class CarStatusIII extends CStruct implements ICarStatus {

	/**
	 * 车牌号
	 */
	public @size(8)
	String			mCarNum;
	/**
	 * 订单号
	 */
	public long		mOrderId;
	/**
	 * Fuc Code 标识位 0x6d
	 */
	public byte		mFucCode	= 0x6d;
	/**
	 * RFID号
	 */
	public @size(8)
	String			mRfid;
	/**
	 * 读取的RFID号
	 */
	public @size(8)
	String			mRfid_dte;
	/**
	 * 经度，整数部分
	 */
	public short	mLongitude;
	/**
	 * 经度，小数部分
	 */
	public int		mLongitudeDecimal;
	/**
	 * 纬度，整数部分
	 */
	public short	mLatitude;
	/**
	 * 纬度，整数部分
	 */
	public int		mLatitudeDecimal;
	/**
	 * 里程
	 */
	public int		mMiles;
	/**
	 * OBD里程
	 */
	public int		mOBDMiles;
	/**
	 * 当前时间
	 */
	public int		mTime;
	/**
	 * 燃油量
	 */
	public short	mOil;
	/**
	 * 蓄电池电量
	 */
	public short	mBattery;
	/**
	 * 转速
	 */
	public short	mRpm;
	/**
	 * 车速
	 */
	public byte		mSpeed;
	/**
	 * 车辆租赁状态 ,0x00 初始状态，0x01 收到订单，
	 */
	public byte		mCarStatus;
	/**
	 * 信号强度
	 */
	public byte		mCsq;
	/**
	 * 温度
	 */
	public byte		mTemperature;
	/**
	 * 发动机温度
	 */
	public byte		mEngineTemperature;
	/**
	 * 报警类型，0x00：车门开 0x01：车速过高
	 */
	public byte		mAlarmType;

	// 7*2*8=112 长度
	/**
	 * CAN包数据
	 */
	public long		mCanDataId1;
	/**
	 * CAN包数据
	 */
	public long		mCanData1;
	/**
	 * CAN包数据
	 */
	public long		mCanDataId2;
	/**
	 * CAN包数据
	 */
	public long		mCanData2;
	/**
	 * CAN包数据
	 */
	public long		mCanDataId3;
	/**
	 * CAN包数据
	 */
	public long		mCanData3;
	/**
	 * CAN包数据
	 */
	public long		mCanDataId4;
	/**
	 * CAN包数据
	 */
	public long		mCanData4;
	/**
	 * CAN包数据
	 */
	public long		mCanDataId5;
	/**
	 * CAN包数据
	 */
	public long		mCanData5;
	/**
	 * CAN包数据
	 */
	public long		mCanDataId6;
	/**
	 * CAN包数据
	 */
	public long		mCanData6;
	/**
	 * CAN包数据
	 */
	public long		mCanDataId7;
	/**
	 * CAN包数据
	 */
	public long		mCanData7;

	/**
	 * 
	 */
	public CarStatusIII() {
		// TODO Auto-generated constructor stub
	}

	public CarStatusIII(String mCarNum) {
		// TODO Auto-generated constructor stub
		this.mFucCode = 0x41;
		this.mCarNum = mCarNum;
		this.mOrderId = 10002028l;
		this.mRfid = "FAS78921";
		this.mLongitude = 123;
		this.mLongitudeDecimal = 123424;
		this.mLatitude = 30;
		this.mLatitudeDecimal = 124531;
		this.mMiles = 10022;
		this.mOil = 72;
		this.mBattery = 135;
		// this.mPowerBattery = 100;
		this.mRpm = 980;
		this.mSpeed = 90;
		this.mCarStatus = 1;

		this.mTemperature = 30;
		this.mAlarmType = 0;
		this.mCsq = 19;
		this.mTime = 0;
	}

	/**
	 * 获得insert sql语句
	 * 
	 * @param table
	 * @param carStatus
	 * @return
	 */
	public static String getInsertSql(String table, CarStatusIII carStatus, int connectType, String host) {
		StringBuilder sb = new StringBuilder();
		// String table = "cs_log";
		sb.append("INSERT INTO " + table + " (");
		switch (connectType) {
			case 0:
				// 
				sb.append("css_number,css_add_time,css_rented,css_rfid,css_speed,css_motor,css_oil,css_power,css_mileage,css_longitude,css_latitude,css_order,css_host,css_warn,css_temperature,css_csq,css_current_time,css_rfid_dte,css_obd_mile,css_engine_t)");
				break;
			case 1:
				// 
				sb.append("cshs_number,cshs_add_time,cshs_rented,cshs_rfid,cshs_speed,cshs_motor,cshs_oil,cshs_power,cshs_mileage,cshs_longitude,cshs_latitude,cshs_order,cshs_host,cshs_warn,cshs_temperature,cshs_csq,cshs_current_time,cshs_rfid_dte,cshs_obd_mile,cshs_engine_t,cshs_mo_data)");
				break;
		}
		// sb.append("cshs_number,cshs_add_time,cshs_rented,cshs_rfid,cshs_speed,cshs_motor,cshs_oil,cshs_power,css_mileage,cshs_longitude,cshs_latitude,cshs_order)");
		sb.append(" VALUES (");
		sb.append("'" + carStatus.mCarNum.toUpperCase() + "','" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "','" + carStatus.mCarStatus + "',");
		sb.append("'" + carStatus.mRfid + "','" + carStatus.mSpeed + "','" + carStatus.mRpm + "','" + carStatus.mOil + "','" + carStatus.mBattery + "','" + carStatus.mMiles + "',");
		sb.append("'" + carStatus.mLongitude + "." + $.zerofill(carStatus.mLongitudeDecimal, 6) + "','" + carStatus.mLatitude + "." + $.zerofill(carStatus.mLatitudeDecimal, 6) + "'," + carStatus.mOrderId + ",");
		// 加上地域信息
		sb.append(host + ",");
		sb.append("'" + carStatus.mAlarmType + "','" + carStatus.mTemperature + "','" + carStatus.mCsq + "','" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(carStatus.mTime * 1000l + SYSTEM.MACHINE_TIME)));
		// 第II版加上的信息，用户rfid，obd里程，发动机温度
		sb.append("','" + carStatus.mRfid_dte + "','" + carStatus.mOBDMiles + "','" + carStatus.mEngineTemperature);
		if (connectType == 1) {
			sb.append("','" + getJsonString(carStatus));
		}
		sb.append("')");
		return sb.toString();
	}

	/**
	 * 获得update sql语句
	 * 
	 * @param table
	 * @param carStatus
	 * @return
	 */
	public static String getUpdateSql(String table, CarStatusIII carStatus, String host) {
		StringBuilder sb = new StringBuilder();
		// String table = "cs_log";
		sb.append("UPDATE " + table + " SET ");
		sb.append("css_add_time ='" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "',");
		sb.append("css_rented ='" + carStatus.mCarStatus + "',");
		sb.append("css_rfid ='" + carStatus.mRfid + "',");
		sb.append("css_speed ='" + carStatus.mSpeed + "',");
		sb.append("css_motor ='" + carStatus.mRpm + "',");
		sb.append("css_oil ='" + carStatus.mOil + "',");
		sb.append("css_power ='" + carStatus.mBattery + "',");
		sb.append("css_mileage ='" + carStatus.mMiles + "',");
		sb.append("css_longitude ='" + carStatus.mLongitude + "." + $.zerofill(carStatus.mLongitudeDecimal, 6) + "',");
		sb.append("css_latitude ='" + carStatus.mLatitude + "." + $.zerofill(carStatus.mLatitudeDecimal, 6) + "',");
		sb.append("css_order =" + carStatus.mOrderId + ",");
		// css_current_time
		sb.append("css_current_time ='" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(carStatus.mTime * 1000l + SYSTEM.MACHINE_TIME)) + "',");
		sb.append("css_csq ='" + carStatus.mCsq + "',");
		sb.append("css_warn ='" + carStatus.mAlarmType + "',");
		//sb.append("css_host =" + host + ",");
		sb.append("css_temperature ='" + carStatus.mTemperature + "',");
		// css_rfid_dte,css_obd_mile,css_engine_t
		sb.append("css_rfid_dte ='" + carStatus.mRfid_dte + "',");
		sb.append("css_obd_mile ='" + carStatus.mOBDMiles + "',");
		sb.append("css_engine_t ='" + carStatus.mEngineTemperature + "'");
		sb.append(" WHERE binary UPPER(css_number) = '" + carStatus.mCarNum.toUpperCase() + "'");
		return sb.toString();
	}

	/**
	 * 获得 cshs_mo_data 的json字符串 long（长整数）：64位，8字节
	 * 
	 * @param carStatus
	 * @return
	 */
	public static String getJsonString(CarStatusIII carStatus) {
		HashMap<String, Object> mapResult = new HashMap<String, Object>();
		HashMap<Long, Long> map = new HashMap<Long, Long>();
		map.put(carStatus.mCanDataId1, carStatus.mCanData1);
		map.put(carStatus.mCanDataId2, carStatus.mCanData2);
		map.put(carStatus.mCanDataId3, carStatus.mCanData3);
		map.put(carStatus.mCanDataId4, carStatus.mCanData4);
		map.put(carStatus.mCanDataId5, carStatus.mCanData5);
		map.put(carStatus.mCanDataId6, carStatus.mCanData6);
		map.put(carStatus.mCanDataId7, carStatus.mCanData7);

		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			Object key = iterator.next();// key
			Object value = map.get(key);// value

			Long msgId = ((Long) key) >> 32;
			if (msgId == 0x30) {
				mapResult.put("SYS_Status", ((Long) value) >> 63 == 0x0 ? "系统正常" : "系统故障");
			}
			if (msgId == 0x300) {
				// 0..2 共3位
				mapResult.put("Gear_Pos", getGearPos(((Long) value) >> 61));
				// 3..3 共1位
				mapResult.put("Gear_Pos", ((((Long) value) >> 60) & 0x1) == 0x0 ? "OFF" : "ON");
				// 4..4 共1位
				mapResult.put("Active_ECO_Mode", ((((Long) value) >> 59) & 0x1) == 0x0 ? "OFF" : "ON");
				// 5..5 共1位
				mapResult.put("Passive_ECO_Mode", ((((Long) value) >> 58) & 0x1) == 0x0 ? "OFF" : "ON");
				// 6..6 共1位
				mapResult.put("DIAGNOSIS_MODE", ((((Long) value) >> 57) & 0x1) == 0x0 ? "OFF" : "ON");
				// 8..15 共8位
				mapResult.put("Speed", (((Long) value) >> 48) & 0xFF);
			}
			if (msgId == 0x301) {
				// 7..7 共1位
				mapResult.put("Motor_OverTemp", ((((Long) value) >> 56) & 0x1) == 0x0 ? "Normal" : "OverTemp");
				// 16..31 共16位
				mapResult.put("Motor_Speed", (((Long) value) >> 32) & 0xFFFF);
				// 32..47 共16位
				mapResult.put("Motor_Power", (((Long) value) >> 16) & 0xFFFF);
				// 48..63 共16位
				mapResult.put("Motor_Temp", ((Long) value) & 0xFFFF);
			}
			if (msgId == 0x302) {
				// 0..15 共16位
				mapResult.put("BMS_Main_Voltage", (((Long) value) >> 48) & 0xFFFF);
				// 16..31 共16位
				mapResult.put("BMS_Main_Current", (((Long) value) >> 32) & 0xFFFF);
				// 32..39 共8位
				mapResult.put("BMS_SOC", (((Long) value) >> 24) & 0xFF);
				// 40..55 共16位
				mapResult.put("BMS_Cell_Max_Voltage", (((Long) value) >> 8) & 0xFFFF);
				// 56..63 共8位
				mapResult.put("BMS_Cell_MaxVoltage_No", ((Long) value) & 0xFF);
			}
			if (msgId == 0x303) {
				// 0..15 共16位
				mapResult.put("BMS_Cell_Min_Voltage", (((Long) value) >> 48) & 0xFFFF);
				// 16..23 共8位
				mapResult.put("BMS_Cell_MinVoltage_No", (((Long) value) >> 40) & 0xFF);
				// 24..31 共8位
				mapResult.put("BMS_Max_Temp", (((Long) value) >> 32) & 0xFF);
				// 32..39 共8位
				mapResult.put("BMS_Cell_MaxTemp_No", (((Long) value) >> 24) & 0xFF);
				// 40..47 共8位
				mapResult.put("BMS_Min_Temp", (((Long) value) >> 16) & 0xFF);
				// 48..55 共8位
				mapResult.put("BMS_Cell_MinTemp_No", (((Long) value) >> 8) & 0xFF);
			}
			if (msgId == 0x304) {
				// 0..3 共4位
				mapResult.put("BMS_Fault", getBMS((((Long) value) >> 60) & 0xF));
				// 4..4 共1位
				mapResult.put("BMS_Battery_CutOff", ((((Long) value) >> 59) & 0x1) == 0x0 ? "正常" : "断开");
				// 5..5 共1位
				mapResult.put("Charge_Connector_indicate", ((((Long) value) >> 58) & 0x1) == 0x0 ? "未连接" : "连接");
				// 6..6 共1位
				mapResult.put("Charge_indicate", ((((Long) value) >> 57) & 0x1) == 0x0 ? "未充电" : "充电");
				// 7..7 共1位
				mapResult.put("ABS_Fault", ((((Long) value) >> 56) & 0x1) == 0x0 ? "正常" : "故障");
				// 8..8 共1位
				mapResult.put("EBD_Fault", ((((Long) value) >> 55) & 0x1) == 0x0 ? "正常" : "故障");
				// 9..9 共1位
				mapResult.put("ESC_Fault", ((((Long) value) >> 54) & 0x1) == 0x0 ? "正常" : "故障");
				// 10..10 共1位
				mapResult.put("SRS_Fault", ((((Long) value) >> 53) & 0x1) == 0x0 ? "正常" : "故障");
				// 11..11 共1位
				mapResult.put("EPS_Fault", ((((Long) value) >> 52) & 0x1) == 0x0 ? "正常" : "故障");
			}
		}
		return map(mapResult);
	}

	/**
	 * 获取档位信息
	 * 
	 * @param value
	 * @return
	 */
	private static String getGearPos(Long value) {
		if (value == 0x0) {
			return "无效";
		}
		if (value == 0x1) {
			return "P";
		}
		if (value == 0x2) {
			return "N";
		}
		if (value == 0x3) {
			return "D";
		}
		if (value == 0x4) {
			return "R";
		}
		return "";
	}

	private static String getBMS(Long value) {
		if (value == 0x0) {
			return "正常";
		}
		if (value == 0x1) {
			return "电池一级报警";
		}
		if (value == 0x2) {
			return "电池二级报警";
		}
		if (value == 0x3) {
			return "电池三级报警";
		}
		return "";
	}

	private static String map(Map map) {
		String strJson = "";
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			if (strJson != null && !strJson.trim().equals(""))
				strJson += ",";
			strJson += "\"" + JsonHelper.js(key) + "\":" + JsonHelper.json(map.get(key));
		}
		return "{" + strJson + "}";
	}

	@Override
	public String getCarNum() {
		return this.mCarNum;
	}

	@Override
	public int getTime() {
		return this.mTime;
	}

	@Override
	public int getCarStatus() {
		return this.mCarStatus;
	}

	@Override
	public long getOrderId() {
		return this.mOrderId;
	}

	@Override
	public int getCarElectric() {
		// TODO Auto-generated method stub
		return this.mMiles;
	}

	@Override
	public int getCarFuel() {
		// TODO Auto-generated method stub
		return 0;
	}
}
