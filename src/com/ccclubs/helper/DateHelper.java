package com.ccclubs.helper;

import java.util.Calendar;
import java.util.Date;
import com.lazy3q.web.helper.LZ;

/**
 * 日期工具
 * 
 * @author Administrator
 * 
 */
public class DateHelper {
	/**
	 * 时间类型枚举
	 * 
	 * @author Administrator
	 * 
	 */
	public static enum TIME_UNIT {
		MILLIS, SECS, MINS, HOURS, DAYS
	};

	private static String		INVALID_DATE_ERR	= "日期为空，或者不是有效的日期";
	private static String		INVALID_CAL_ERR		= "日历为空";

	/**
	 * 1 秒 = 1000 毫秒
	 */
	private static final long	MILLIS_IN_SEC		= 1000;
	/**
	 * 1 分钟 = 60 秒
	 */
	private static final long	MILLIS_IN_MIN		= MILLIS_IN_SEC * 60;
	/**
	 * 1 小时 = 60 分钟
	 */
	private static final long	MILLIS_IN_HOUR		= MILLIS_IN_MIN * 60;
	/**
	 * 1 天 = 24 小时
	 */
	private static final long	MILLIS_IN_DAY		= MILLIS_IN_HOUR * 24;

	/**
	 * 按照 format 格式化一个 date
	 * 
	 * @param date -
	 *            等待被格式化的日期类型
	 * @param format -
	 *            格式化参数，如："yyyy-MM-dd HH:mm"
	 * @return 被格式化的字符串
	 */
	public static String formatDate(Date date, String format) {
		return LZ.date(date, format);
	}

	/**
	 * 按照 format 格式化一个 date
	 * 
	 * @param date -
	 *            等待被格式化的日期字符串
	 * @param format -
	 *            格式化参数，如："yyyy-MM-dd HH:mm"
	 * @return 被格式化后的日期类型
	 */
	public static Date formatDate(String date, String format) {
		return LZ.date(date, format);
	}

	/**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date format2Date(Date date, String format) {
		return formatDate(formatDate(date, format), format);
	}

	/**
	 * 获取 cal1 与 cal2 的时间差，返回毫秒
	 * 
	 * @param cal1
	 * @param cal2
	 * @param timeUnit -
	 *            时间单位，毫秒，秒，分钟，小时，天
	 * @return 如果 cal2 比 cal1 大则返回正数
	 */
	public static long getDiff(Calendar cal1, Calendar cal2, TIME_UNIT timeUnit) {
		if (cal1 == null || cal2 == null || timeUnit == null) {
			throw new NullPointerException(INVALID_DATE_ERR);
		}

		long timeDiffInMillis = cal2.getTimeInMillis() - cal1.getTimeInMillis();

		switch (timeUnit) {
			case MILLIS:
				return timeDiffInMillis;
			case SECS:
				return timeDiffInMillis / MILLIS_IN_SEC;
			case MINS:
				return timeDiffInMillis / MILLIS_IN_MIN;
			case HOURS:
				return timeDiffInMillis / MILLIS_IN_HOUR;
			case DAYS:
				return timeDiffInMillis / MILLIS_IN_DAY;
			default:
				return timeDiffInMillis;
		}

	}

	/**
	 * 获取 date1 与 date2 的时间差，返回毫秒
	 * 
	 * @param date1 -
	 * @param date2 -
	 * @param timeUnit -
	 *            时间单位，毫秒，秒，分钟，小时，天
	 * @return 如果 date2 比 date1 大则返回正数
	 */
	public static long getDiff(Date date1, Date date2, TIME_UNIT timeUnit) {
		if (date1 == null || date2 == null || timeUnit == null) {
			throw new NullPointerException();
		}

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);

		return getDiff(cal1, cal2, timeUnit);

	}

	/**
	 * 返回 cal 指定时间单位 timeUnit 加上 amount 后的 Calendar
	 * 
	 * @param cal -
	 * @param amount -
	 *            数量 ，可为负数
	 * @param timeUnit -
	 *            时间单位，毫秒，秒，分钟，小时，天
	 * @return 返回改变后的 Calendar
	 */
	public static Calendar addTime(Calendar cal, long amount, TIME_UNIT timeUnit) {
		if (cal == null) {
			throw new NullPointerException(INVALID_CAL_ERR);
		}
		switch (timeUnit) {
			case MILLIS:
				cal.setTimeInMillis(cal.getTimeInMillis() + amount);
				return cal;
			case SECS:
				long secondsToAdd = cal.getTimeInMillis() + amount * MILLIS_IN_SEC;
				cal.setTimeInMillis(secondsToAdd);
				return cal;
			case MINS:
				long minsToAdd = cal.getTimeInMillis() + amount * MILLIS_IN_MIN;
				cal.setTimeInMillis(minsToAdd);
				return cal;
			case HOURS:
				long hoursToAdd = cal.getTimeInMillis() + amount * MILLIS_IN_HOUR;
				cal.setTimeInMillis(hoursToAdd);
				return cal;
			case DAYS:
				long daysToAdd = cal.getTimeInMillis() + amount * MILLIS_IN_DAY;
				cal.setTimeInMillis(daysToAdd);
				return cal;
			default:
				return null;
		}
	}

	/**
	 * 返回 date 指定时间单位 timeUnit 加上 amount 后的 Date
	 * 
	 * @param date
	 * @param amount -
	 *            数量 ，可为负数
	 * @param timeUnit -
	 *            时间单位，毫秒，秒，分钟，小时，天
	 * @return 返回改变后的 Date
	 */
	public static Date addTime(Date date, long amount, TIME_UNIT timeUnit) {
		if (date == null) {
			throw new NullPointerException(INVALID_DATE_ERR);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		addTime(cal, amount, timeUnit);
		return cal.getTime();
	}

	/**
	 * 根据年，月，日，时，分，秒获得一个日期类型
	 * 
	 * @param y -
	 *            年份，如：2013
	 * @param m -
	 *            月份，从 1 开始
	 * @param d -
	 *            日，从 1 开始
	 * @param hour -
	 *            小时
	 * @param min -
	 *            分钟
	 * @param second
	 *            -秒
	 * @return 获得一个指定年，月，日，时，分，秒的日期对象
	 */
	public static Date getDate(int y, int m, int d, int hour, int min, int second) {
		return getCalendar(y, m, d, hour, min, second).getTime();
	}

	/**
	 * 根据年，月，日，时，分，秒获得一个日期类型
	 * 
	 * @param y -
	 *            年份，如：2013
	 * @param m -
	 *            月份，从 1 开始
	 * @param d -
	 *            日，从 1 开始
	 * @param hour -
	 *            小时
	 * @param min -
	 *            分钟
	 * @param second
	 *            -秒
	 * @return 获得一个指定年，月，日，时，分，秒的 Calendar 对象
	 */
	public static Calendar getCalendar(int y, int m, int d, int hour, int min, int second) {
		Calendar cal = Calendar.getInstance();
		cal.set(y, m - 1, d, hour, min, second);
		return cal;
	}

	/**
	 * 根据 date 日期，获得与当前时间的间隔描述
	 * 
	 * @param date -
	 *            日期
	 * @return 返回字符串，如：3分钟之前
	 */
	public static String getTimeDesc(Date date) {
		if (date == null)
			return null;

		long second = (new Date().getTime() - date.getTime()) / MILLIS_IN_SEC;

		if (second < 60)
			return "几秒之前";
		if (second < 60 * 60)
			return "" + second / 60 + "分钟之前";

		if (second < 60 * 60 * 24)
			return "" + second / 60 / 60 + "小时之前";

		if (second < 60 * 60 * 24 * 30)
			return "" + second / 60 / 60 / 24 + "天之前";

		if (second < 60 * 60 * 24 * 365)
			return "" + second / 60 / 60 / 24 / 30 + "个月之前";

		return "" + second / 60 / 60 / 24 / 365 + "年之前";
	}

	/**
	 * 根据当前时间返回问候语，如：当前上午9点，返回 “上午好”
	 * 
	 * @return
	 */
	public static String getGreetings() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		if (hour < 6)
			return "凌晨好";
		if (hour < 12)
			return "上午好";
		if (hour < 14)
			return "中午好";
		if (hour < 17)
			return "下午好";
		if (hour < 19)
			return "傍晚好";
		if (hour < 22)
			return "晚上好";
		return "夜深了";
	}

	/**
	 * 根据两个时间，返回间隔差，如：3小时20分钟
	 * 
	 * @param start -
	 *            开始时间
	 * @param finish -
	 *            结束时间
	 * @return 返回如：3小时20分钟
	 */
	public static String getTimeDesc(Date start, Date finish) {
		if (start == null)
			return null;
		if (start.after(finish)) {
			Date temp = start;
			start = finish;
			finish = temp;
		}

		long min = (finish.getTime() - start.getTime()) / MILLIS_IN_MIN;

		if (min < 60)
			return "" + min + "分钟";
		if (min < 60 * 24) {
			if (min % 60 == 0) {
				return "" + min / 60 + "小时";
			} else {
				return "" + min / 60 + "小时" + min % 60 + "分钟";
			}
		}
		// 整天
		if (min % (60 * 24) == 0) {
			return "" + min / (60 * 24) + "天";
		}
		// 有小时，没分钟
		if (min / 60 > 0 && min % 60 == 0) {
			return "" + min / (60 * 24) + "天" + min % (60 * 24) / 60 + "小时";
		}
		// 有小时，有分钟
		if (min / 60 > 0 && min % 60 != 0) {
			return "" + min / (60 * 24) + "天" + min % (60 * 24) / 60 + "小时" + min % 60 + "分钟";
		}
		// 没小时，有分钟
		return "" + min / (60 * 24) + "天" + min % 60 + "分钟";
	}
}
