package com.ccclubs.util.mq.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.util.mq.CStruct;
import com.ccclubs.util.mq.ICarStatus;
import com.ccclubs.util.mq.annotation.size;
import com.lazy3q.web.helper.$;

/**
 * 远程车辆发上来的电动车状态信息IV，长度72
 * <p>
 * 包括车牌号，订单号，RFID，经、纬度，里程，油量等
 * </p>
 * 
 * @author Administrator
 * 
 */
public class CarStatusIV extends CStruct implements ICarStatus {

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
	 * Fuc Code 标识位 0x41
	 */
	public byte		mFucCode	= 0x66;
	/**
	 * Subfuc code 标识位 0x00
	 */
	public byte		mSubFucCode	= 0x00;
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
	 * 续航里程
	 */
	public short	mEndurance;

	/**
	 * 动力电池电量
	 */
	public byte		mSOC;
	/**
	 * 是否在充电
	 */
	public byte		mCharging;

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
	 * 车内温度
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

	/**
	 * 
	 */
	public CarStatusIV() {
		// TODO Auto-generated constructor stub
	}

	public CarStatusIV(String mCarNum) {
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
	public static String getInsertSql(String table, CarStatusIV carStatus, int connectType, String host) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " + table + " (");
		switch (connectType) {
			case 0:
				// 
				sb.append("css_number,css_add_time,css_rented,css_rfid,css_speed,css_motor,css_power,css_mileage,css_longitude,css_latitude,css_order,css_host,css_warn,css_temperature,css_csq,css_current_time,css_rfid_dte,css_obd_mile,css_engine_t,css_endurance,css_ev_battery,css_charging)");
				break;
			case 1:
				// 
				sb
						.append("cshs_number,cshs_add_time,cshs_rented,cshs_rfid,cshs_speed,cshs_motor,cshs_power,cshs_mileage,cshs_longitude,cshs_latitude,cshs_order,cshs_host,cshs_warn,cshs_temperature,cshs_csq,cshs_current_time,cshs_rfid_dte,cshs_obd_mile,cshs_engine_t,cshs_endurance,cshs_ev_battery,cshs_charging)");
				break;
		}
		// sb.append("cshs_number,cshs_add_time,cshs_rented,cshs_rfid,cshs_speed,cshs_motor,cshs_oil,cshs_power,css_mileage,cshs_longitude,cshs_latitude,cshs_order)");
		sb.append(" VALUES (");
		sb.append("'" + carStatus.mCarNum.toUpperCase() + "','" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "','" + carStatus.mCarStatus + "',");
		sb.append("'" + carStatus.mRfid + "','" + carStatus.mSpeed + "','" + carStatus.mRpm + "','" + carStatus.mBattery + "','" + carStatus.mMiles + "',");
		sb.append("'" + carStatus.mLongitude + "." + $.zerofill(carStatus.mLongitudeDecimal, 6) + "','" + carStatus.mLatitude + "." + $.zerofill(carStatus.mLatitudeDecimal, 6) + "'," + carStatus.mOrderId + ",");
		// 加上地域信息
		sb.append(host + ",");
		sb.append("'" + carStatus.mAlarmType + "','" + carStatus.mTemperature + "','" + carStatus.mCsq + "','" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(carStatus.mTime * 1000l + SYSTEM.MACHINE_TIME)));
		// 第II版加上的信息，用户rfid，obd里程，发动机温度
		sb.append("','" + carStatus.mRfid_dte + "','" + carStatus.mOBDMiles + "','" + carStatus.mEngineTemperature);
		// 续航里程，动力电池电量，是否在充电
		sb.append("','" + carStatus.mEndurance + "','" + carStatus.mSOC + "','" + carStatus.mCharging);
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
	public static String getUpdateSql(String table, CarStatusIV carStatus, String host) {
		StringBuilder sb = new StringBuilder();
		// String table = "cs_log";
		sb.append("UPDATE " + table + " SET ");
		sb.append("css_add_time ='" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "',");
		sb.append("css_rented ='" + carStatus.mCarStatus + "',");
		sb.append("css_rfid ='" + carStatus.mRfid + "',");
		sb.append("css_speed ='" + carStatus.mSpeed + "',");
		sb.append("css_motor ='" + carStatus.mRpm + "',");
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
		sb.append("css_engine_t ='" + carStatus.mEngineTemperature + "',");
		// 续航里程，动力电池电量，是否在充电 css_endurance,css_ev_battery,css_charging
		sb.append("css_endurance ='" + carStatus.mEndurance + "',");
		sb.append("css_ev_battery ='" + carStatus.mSOC + "',");
		sb.append("css_charging ='" + carStatus.mCharging + "'");
		sb.append(" WHERE binary UPPER(css_number) = '" + carStatus.mCarNum.toUpperCase() + "'");
		return sb.toString();
	}

	@Override
	public String getCarNum() {
		return this.mCarNum;
	}

	@Override
	public int getTime() {
		return this.mTime;
	}

	//	
	// /**
	// * 获取报警类型
	// *
	// * @param mAlarmType
	// * @return
	// */
	// public static String getAlarmType(byte mAlarmType) {
	// String resultString = "" + mAlarmType;
	// switch (mAlarmType) {
	// case 0x01:
	// resultString = "车门开";
	// break;
	// case 0x02:
	// resultString = "车速过高";
	// break;
	// case 0x03:
	// resultString = "转速过高";
	// break;
	// case 0x04:
	// resultString = "水温过高";
	// break;
	// }
	// return resultString;
	// }

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
