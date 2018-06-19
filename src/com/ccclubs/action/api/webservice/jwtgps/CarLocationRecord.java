package com.ccclubs.action.api.webservice.jwtgps;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.ccclubs.model.CsState;
import com.ccclubs.util.ByteUtil;
import com.ccclubs.util.struct.OrderNumber;
import com.ccclubs.util.struct.Struct;

/**
 * 
 * 车辆定位数据
 * 
 * @author joel
 *
 */
public class CarLocationRecord extends Struct{
	
	@OrderNumber(value=1)
	private byte excrypt; 	//该字段标识传输的定位信息是否使用国家测绘局批准的地图保密插件进行加密：加密标识：1-已加密，0-未加密

	@OrderNumber(value=2)
	private byte day;
	
	@OrderNumber(value=3)
	private byte month;
	
	@OrderNumber(value=4)
	private short year;
	
//	@OrderNumber(value=5)
//	private byte[] date = new byte[4];  	//日月年（dmyy）
	
	@OrderNumber(value=5)
	private byte[] time = new byte[3];  	//时分秒（hms）
	
	@OrderNumber(value=6)
	private int lon;	  	//经度，单位为 1*10^-6度
	
	@OrderNumber(value=7)
	private int lat; 	  	//纬度，单位为 1*10^-6度
	
	@OrderNumber(value=8)
	private short vec1;		//速度，指卫星定位车载终端设备上传的行车速度信息
	
	@OrderNumber(value=9)
	private short vec2;		//行驶记录速度，指车辆行驶记录设备上传的行车速度
	
	@OrderNumber(value=10)
	private  int  vec3;     //车辆当前总里程数，指车辆上传的行车里程数
	
	@OrderNumber(value=11)
	private short direction; //方向，0-359，单位为度，正北为0，顺时针
	
	@OrderNumber(value=12)
	private short altitude;  //海拔高度，单位为米（m）
	
	@OrderNumber(value=13)
	private int  state;		 //车辆状态
	
	@OrderNumber(value=14)
	private int alarm;       //报警状态

	public byte getExcrypt() {
		return excrypt;
	}

	public void setExcrypt(byte excrypt) {
		this.excrypt = excrypt;
	}

//	public byte[] getDate() {
//		return date;
//	}
//
//	public void setDate(byte[] date) {
//		this.date = date;
//	}

	public byte[] getTime() {
		return time;
	}

	public void setTime(byte[] time) {
		this.time = time;
	}

	public int getLon() {
		return lon;
	}

	public void setLon(int lon) {
		this.lon = lon;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public short getVec1() {
		return vec1;
	}

	public void setVec1(short vec1) {
		this.vec1 = vec1;
	}

	public short getVec2() {
		return vec2;
	}

	public void setVec2(short vec2) {
		this.vec2 = vec2;
	}

	public int getVec3() {
		return vec3;
	}

	public void setVec3(int vec3) {
		this.vec3 = vec3;
	}

	public short getDirection() {
		return direction;
	}

	public void setDirection(short direction) {
		this.direction = direction;
	}

	public short getAltitude() {
		return altitude;
	}

	public void setAltitude(short altitude) {
		this.altitude = altitude;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getAlarm() {
		return alarm;
	}

	public void setAlarm(int alarm) {
		this.alarm = alarm;
	}
	
	public byte getDay() {
		return day;
	}

	public void setDay(byte day) {
		this.day = day;
	}

	public byte getMonth() {
		return month;
	}

	public void setMonth(byte month) {
		this.month = month;
	}

	public short getYear() {
		return year;
	}

	public void setYear(short year) {
		this.year = year;
	}

//	public byte[] getYmd(Date date){
//		Calendar cal = Calendar.getInstance();  
//		cal.setTime(date);
//		short year = Short.reverseBytes((short)cal.get(Calendar.YEAR));
//		byte[] year_b = ByteUtil.shortToBytes((short)2015);
//		System.out.println(Struct.bytesToHexString(year_b));
//		byte[] data = new byte[4];
//		data[0]=(byte) (cal.get(Calendar.DAY_OF_MONTH));			//日
//		data[1]=(byte) (cal.get(Calendar.MONTH)+1);					//月
//		data[2]=year_b[0];											//年
//		data[3]=year_b[1];											//年
//		return data;
//	}
//	
	public byte[] getHms(Date date){
		Calendar cal = Calendar.getInstance();  
		cal.setTime(date);
		byte[] data = new byte[3];
		data[0] = (byte) (cal.get(Calendar.HOUR_OF_DAY));
		data[1] = (byte) (cal.get(Calendar.MINUTE));
		data[2] = (byte) (cal.get(Calendar.SECOND));
		return data;
	}
	
	/**
	 * 
	 * @param type 是否实时数据，or补发数据
	 * @param port
	 * @param state
	 * @return
	 */
	public byte[] getHexString(CsState state){
		this.setExcrypt((byte)0x00);		//未加密
		Date date = state.getCssAddTime();
		Calendar cal = Calendar.getInstance();  
		cal.setTime(date);
		//this.setDate(getYmd(date));
		this.setDay((byte)cal.get(Calendar.DAY_OF_MONTH));
		this.setMonth((byte)(cal.get(Calendar.MONTH)+1));
		this.setYear((short)cal.get(Calendar.YEAR));
		this.setTime(getHms(date));
		int lon_ = 0;
		int lat_ = 0;
		try{
			lon_ = new BigDecimal(Double.valueOf(state.getCssLongitude()) * 1000000).intValue();
			lat_ = new BigDecimal(Double.valueOf(state.getCssLatitude()) * 1000000).intValue();
		}catch(Exception e){}
		this.setLon(lon_);
		this.setLat(lat_);
		int speed = 0;
		int css_motor = 0;
		try{
			speed = Integer.valueOf(state.getCssSpeed()).intValue();
			css_motor = Integer.valueOf(state.getCssMotor()).intValue();
		}catch(Exception e){}
		this.setVec1((short)speed);
		this.setVec2((short)0);
		this.setVec3(css_motor);
		this.setDirection((short)0);
		this.setAltitude((short)0);
		this.setState(0);
		this.setAlarm(StringUtils.isEmpty(state.getCssWarn()) ? 0 : 1);
		
		try {
			return Struct.getObjectBytesExt(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String readInteger4(String loc){
		
		return loc;
	}
	
	
	public static void main(String[] args) {
	}
}
