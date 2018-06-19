package com.ccclubs.util.mq.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ccclubs.util.mq.CStruct;
import com.ccclubs.util.mq.annotation.size;

/**
 * 远程车辆发上来的报警信息
 * <p>
 * 包括车牌号，订单号，报警类型
 * </p>
 * 
 * @author Administrator
 * 
 */
public class CarAlarm extends CStruct {
	/**
	 * 车牌号
	 */
	public @size(8)
	String		mCarNum;
	/**
	 * 订单号
	 */
	public long	mOrderId;
	/**
	 * Fuc Code 标识位 0x41
	 */
	public byte	mFucCode	= 0x43;
	/**
	 * 报警类型，0x00：车门开 0x01：车速过高
	 */
	public byte	mAlarmType;

	/**
	 * 
	 */
	public CarAlarm() {
		// TODO Auto-generated constructor stub
	}

	public CarAlarm(String mCarNum, long mOrderId, byte mAlarmType) {
		// TODO Auto-generated constructor stub
		this.mCarNum = mCarNum;
		this.mOrderId = mOrderId;
		this.mFucCode = 0X43;
		this.mAlarmType = mAlarmType;
	}

	public static String getInsertSql(String table, CarAlarm carAlarm) {
		StringBuilder sb = new StringBuilder();
		// String table = "cs_log";
		sb.append("INSERT INTO " + table + " (");
		sb.append("csa_number,csa_add_time,csa_info,csa_order)");
		sb.append(" VALUES (");
		// sb.append("'++',0,'test','" + new SimpleDateFormat("yyyy-MM-dd
		// HH:mm").format(new Date()) + "')");
		sb.append("'" + carAlarm.mCarNum.toUpperCase() + "','" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "',");
		// sb.append("'" + getAlarmType(carAlarm.mAlarmType) + "'," +
		// carAlarm.mOrderId + ")");
		sb.append("'" + carAlarm.mAlarmType + "'," + carAlarm.mOrderId + ")");
		return sb.toString();
	}

	/**
	 * 获取报警类型
	 * 
	 * @param mAlarmType
	 * @return
	 */
	public static String getAlarmType(byte mAlarmType) {
		String resultString = "" + mAlarmType;
		switch (mAlarmType) {
			case 0x01:
				resultString = "车门开";
				break;
			case 0x02:
				resultString = "车速过高";
				break;
			case 0x03:
				resultString = "转速过高";
				break;
			case 0x04:
				resultString = "水温过高";
				break;
		}
		return resultString;
	}
}
