package com.ccclubs.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 各种格式的日期转换通用类
 * @author wangx
 *
 */
public class DateUtil {

	/**
	 * 获取系统时间
	 * 
	 * @return
	 */
	public static Date getSysDate() {
		// 在某些系统中如果不设置系统的默认时区，有可能获取到的时间比实际时间少8小时，即（GMT时间）
		return Calendar.getInstance().getTime();
	}
	
	/**
	 * 将13位毫秒的数值转换成日期字符串
	 * @param datetime
	 * @param dateformat samples("yyyy年MM月dd日 HH:mm:ss")
	 * @return
	 */
	public String longToString(Long datetime, String dateformat) {
		String str = "";
		if (datetime != 0) {
			if(dateformat==null||dateformat.equals("")){
				dateformat = "yyyy-MM-dd HH:mm:ss";
			}
			SimpleDateFormat sdf = new SimpleDateFormat("",
					Locale.SIMPLIFIED_CHINESE);
			sdf.applyPattern(dateformat);
			Date date = new Date(datetime);
			str = sdf.format(date);
		} else {
			str = "空";
		}
		return str;
	}
	
	/**
	 * Date类转换成字符串
	 * @param date
	 * @param dateformat samples("yyyy年MM月dd日 HH:mm:ss")
	 * @return
	 */
	public String dateToString(Date date, String dateformat) {
		String str = "";
		if(date!=null){
			if(dateformat==null||dateformat.equals("")){
				dateformat = "yyyy-MM-dd HH:mm:ss";
			}
			SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
			str = sdf.format(date);
		}
		return str;
	}
	
	/**
	 * Date类型的字符串转换成13位毫秒的数值
	 * @param date
	 * @param dateformat samples("yyyy年MM月dd日 HH:mm:ss")
	 * @return
	 */
	public long stringToLong(String date, String dateformat) {
		long l = 0L;
		if(dateformat==null||dateformat.equals("")){
			dateformat = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sm = new SimpleDateFormat(dateformat);
		try {
			Date d = sm.parse(date);
			l = d.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return l;
	}
	
	/**
	 * 将13位毫秒的数值转换成Date类型
	 * @param datetime
	 * @param dateformat samples("yyyy年MM月dd日 HH:mm:ss")
	 * @return
	 */
	public Date longToDate(long datetime, String dateformat) {
		Date d = null;
		if(dateformat==null||dateformat.equals("")){
			dateformat = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("",
				Locale.SIMPLIFIED_CHINESE);
		sdf.applyPattern(dateformat);
		d = new Date(datetime);
		return d;
	}
	
	/**
	 * 符合条件的String类型转换成Date类型
	 * @param datestring
	 * @param dateformat samples("yyyy年MM月dd日 HH:mm:ss")
	 * @return
	 */
	public Date StringtoDate(String datestring, String dateformat){
		Date date = null;
		if(dateformat==null||dateformat.equals("")){
			dateformat = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat format = new SimpleDateFormat(dateformat);
		try {
			date = format.parse(datestring);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public String timestampToString(Timestamp ts, String dateformat){
		String str = "";
		if (ts != null) {
			if(dateformat==null||dateformat.equals("")){
				dateformat = "yyyy-MM-dd HH:mm:ss";
			}
			SimpleDateFormat sdf = new SimpleDateFormat("",
					Locale.SIMPLIFIED_CHINESE);
			sdf.applyPattern(dateformat);
			str = sdf.format(ts);
		} else {
			str = "空";
		}
		return str;
	}
}
