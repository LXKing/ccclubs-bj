package com.ccclubs.action.api.webservice.struct;

import java.util.Calendar;
import java.util.Date;

import com.ccclubs.util.struct.Struct;

public class EVTime extends Struct {
	public byte y;
	public byte M;
	public byte d;
	public byte H;
	public byte m;
	public byte s;
	
	public Date getTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2000+y);		
		calendar.set(Calendar.MONTH,M-1);
		calendar.set(Calendar.DAY_OF_MONTH,d);
		calendar.set(Calendar.HOUR_OF_DAY,H);
		calendar.set(Calendar.MINUTE,m);
		calendar.set(Calendar.SECOND,s);
		return calendar.getTime();
	}
	
	/**
	 * 转换日期类型成byte数组
	 * @param date
	 * @return
	 */
	public static byte[] convertTime2Byte(Date date){
		byte[] btArr = new byte[6];
		if(date!=null){
			Calendar cal = Calendar.getInstance();  
			cal.setTime(date);
			
			btArr[0] = (byte) (cal.get(Calendar.YEAR) - 2000);
			btArr[1] = (byte) (cal.get(Calendar.MONTH)+1);
			btArr[2] = (byte) (cal.get(Calendar.DAY_OF_MONTH));
			btArr[3] = (byte) (cal.get(Calendar.HOUR_OF_DAY));
			btArr[4] = (byte) (cal.get(Calendar.MINUTE));
			btArr[5] = (byte) (cal.get(Calendar.SECOND));
			
		}
		return btArr;
	}
	
}
