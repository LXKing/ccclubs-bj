package com.ccclubs.param;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @function
 * @author cjb
 * @createTime 2018年9月6日
 */
public class TimeUtil {

    public interface DATE {
        // 常用日期格式
        public static final String FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
        public static final String FORMAT_yyyyMMddHHmmsss = "yyyyMMddHHmmsss";
        public static final String FORMAT_yyyyMMddHHmmss = "yyyyMMddHHmmss";
        public static final String FORMAT_yyMMddHHmmss = "yyMMddHHmmss";
        public static final String FORMAT_yyyyMMdd = "yyyyMMdd";
        public static final String FORMAT_yyyyMM = "yyyyMM";
        public static final String FORMAT_HHmmss = "HHmmss";
        public static final String FORMAT_HHmm = "HHmm";
        public static final String FORMAT_yyyy_MM_dd_HH_mm_ss_S = "yyyy-MM-dd HH:mm:ss.S";
        public static final String FORMAT_yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
        public static final String FORMAT_yyyy_MM_dd = "yyyy-MM-dd";
        public static final String FORMAT_yyyy_ZH_MM_ZH_dd = "yyyy年MM月dd日";
        public static final String FORMAT_MMddyyyy = "MM/dd/yyyy";
        public static final String FORMAT_HH_mm_ss = "HH:mm:ss";
        public static final String FORMAT_HH_mm = "HH:mm";
        // 日期格式数组
        public static final String[] PARSE_FORMAT_ARR =
                new String[] {FORMAT_yyyyMMddHHmmsss, FORMAT_yyyyMMddHHmmss, FORMAT_yyMMddHHmmss,
                        FORMAT_yyyyMMdd, FORMAT_yyyyMM, FORMAT_DEFAULT,
                        FORMAT_yyyy_MM_dd_HH_mm_ss_S, FORMAT_yyyy_MM_dd_HH_mm, FORMAT_yyyy_MM_dd,
                        FORMAT_yyyy_ZH_MM_ZH_dd, FORMAT_MMddyyyy, FORMAT_HH_mm_ss, FORMAT_HH_mm};
        // 星期数组
        public static final String[] weekDaysName =
                {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    }

    public interface DF {
        public static final SimpleDateFormat DEFAULT = new SimpleDateFormat(DATE.FORMAT_DEFAULT);

        public static final SimpleDateFormat yyyyMMddHHmmsss =
                new SimpleDateFormat(DATE.FORMAT_yyyyMMddHHmmsss);

        public static final SimpleDateFormat yyyyMMddHHmmss =
                new SimpleDateFormat(DATE.FORMAT_yyyyMMddHHmmss);

        public static final SimpleDateFormat yyMMddHHmmss =
                new SimpleDateFormat(DATE.FORMAT_yyMMddHHmmss);

        public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat(DATE.FORMAT_yyyyMMdd);

        public static final SimpleDateFormat yyyyMM = new SimpleDateFormat(DATE.FORMAT_yyyyMM);

        public static final SimpleDateFormat yyyy_MM_dd_HH_mm_ss_S =
                new SimpleDateFormat(DATE.FORMAT_yyyy_MM_dd_HH_mm_ss_S);

        public static final SimpleDateFormat yyyy_MM_dd_HH_mm =
                new SimpleDateFormat(DATE.FORMAT_yyyy_MM_dd_HH_mm);

        public static final SimpleDateFormat yyyy_MM_dd =
                new SimpleDateFormat(DATE.FORMAT_yyyy_MM_dd);

        public static final SimpleDateFormat yyyy_ZH_MM_ZH_dd =
                new SimpleDateFormat(DATE.FORMAT_yyyy_ZH_MM_ZH_dd);

        public static final SimpleDateFormat MMddyyyy = new SimpleDateFormat(DATE.FORMAT_MMddyyyy);

        public static final SimpleDateFormat HH_mm_ss = new SimpleDateFormat(DATE.FORMAT_HH_mm_ss);

        public static final SimpleDateFormat HH_mm = new SimpleDateFormat(DATE.FORMAT_HH_mm);
    }

    public enum RoundMode {
        Ceiling, Floor
    }

    /**
     * 格式化当前时间
     *
     * @param partten
     * @return
     */
    public static String formatCurrentTime(String partten) {
        return DateFormatUtils.format(new Date(), partten);
    }

    /**
     * 获取指定格式化时间字符窜
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        if(StringUtils.isEmpty(pattern)) {
            pattern = DATE.FORMAT_DEFAULT;
        }
        try {
            return DateFormatUtils.format(date, pattern);
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }

    public static Integer getIntValueOfTime(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        try {
            String str = DateFormatUtils.format(date, pattern);
            str = str.replaceAll("^0+", "");
            return Integer.valueOf(str);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 当前日期添加指定年数
     * 
     * @param date
     * @param count
     * @return
     */
    public static Date addYear(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, count);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 当前日期添加指定月数
     * 
     * @param date
     * @param count
     * @return
     */
    public static Date addMonth(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, count);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 当前日期添加指定天数
     * 
     * @param date
     * @param count
     * @return
     */
    public static Date addDay(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, count);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 当前日期添加指定小时数
     * 
     * @param date
     * @param count
     * @return
     */
    public static Date addHour(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, count);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 当前日期添加指定分钟数
     * 
     * @param date
     * @param count
     * @return
     */
    public static Date addMinute(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, count);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 当前日期添加指定秒数
     * 
     * @param date
     * @param count
     * @return
     */
    public static Date addSecond(Date date, int count) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, count);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 字符窜转日期
     * 
     * @param obj
     * @return
     */
    public static Date stringToDate(Object obj) {
        if (obj == null)
            return null;
        String str = obj.toString();
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            return DateUtils.parseDate(str.trim(), DATE.PARSE_FORMAT_ARR);
        } catch (ParseException e) {
            throw new RuntimeException("wrong date format");
        }
    }

    /**
     * 获得当前年
     *
     * @return
     */
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    /**
     * 获得程序运行当前月
     *
     * @return
     */
    public static int getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int month = calendar.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * 获得某个日期所属年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    /**
     * 获得某个时间所属月
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * 获得某个日期所属天
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DATE);
        return day;
    }

    /**
     * 判断2个日期是否是同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        if ((getYear(date1) != getYear(date2)) || (getMonth(date1) != getMonth(date2))
                || (getDay(date1) != getDay(date2))) {
            return false;
        }
        return true;
    }

    /**
     * 获得当前时间的早晨 00:00:00分
     *
     * @param date
     * @return
     */
    public static Date getDayFirstTime(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return DateUtils.parseDate(DateFormatUtils.format(date, DATE.FORMAT_yyyyMMdd),
                    DATE.FORMAT_yyyyMMdd);
        } catch (ParseException e) {
            throw new RuntimeException();
        }
    }

    /**
     * 获得某一年的第一天
     *
     * @return
     */
    public static Date getYearFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        String time = year + "-1-1";
        try {
            return DateUtils.parseDate(time, DATE.FORMAT_yyyy_MM_dd);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 判断某个时间是否大于当前时间
     *
     * @param date
     * @return
     */
    public static boolean afterNow(Date date) {
        if (date == null)
            return false;
        return date.after(new Date());
    }

    /**
     * 判断某个时间是否小于当前时间
     *
     * @param date
     * @return
     */
    public static boolean beforeNow(Date date) {
        if (date == null)
            return false;
        return date.before(new Date());
    }

    /**
     * 获得汉字星期时间
     *
     * @param date
     * @return
     */
    public static String getWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return DATE.weekDaysName[intWeek];
    }

    /**********************************************************************************************************/
    /**
     * 获取yyyy-MM-dd格式日期
     *
     * @return Date
     */
    public static Date getDate() {
        Calendar oneCalendar = Calendar.getInstance();
        return getDate(oneCalendar.get(Calendar.YEAR), oneCalendar.get(Calendar.MONTH) + 1,
                oneCalendar.get(Calendar.DATE));
    }

    /**
     * 根据所给年、月、日，得到日期(Date类型)，注意：只有年月日，没有时分秒。 年、月、日不合法会抛IllegalArgumentException
     *
     * @param year 年
     * @param month 月
     * @param day 日
     * @return 日期
     */
    public static Date getDate(int year, int month, int day) {
        return getDate(year, month, day, 0, 0, 0);
    }

    /**
     * 根据所给年、月、日、时、分、秒，得到日期(Date类型)。 年、月、日不合法会抛IllegalArgumentException
     *
     * @param yyyy 4位年
     * @param MM 月
     * @param dd 日
     * @param HH 时
     * @param mm 分
     * @param ss 秒
     * @return
     */
    public static Date getDate(int yyyy, int MM, int dd, int HH, int mm, int ss) {
        if (!verityDate(yyyy, MM, dd))
            throw new IllegalArgumentException("不是有效的时间");
        Calendar oneCalendar = Calendar.getInstance();
        oneCalendar.clear();
        oneCalendar.set(yyyy, MM - 1, dd, HH, mm, ss);
        return new Date(oneCalendar.getTime().getTime());
    }

    /**
     * 根据所给年、月、日，检验是否为合法日期。
     *
     * @param yyyy 4位年
     * @param MM 月
     * @param dd 日
     * @return
     */
    public static boolean verityDate(int yyyy, int MM, int dd) {
        boolean flag = false;

        if (MM >= 1 && MM <= 12 && dd >= 1 && dd <= 31) {
            if (MM == 4 || MM == 6 || MM == 9 || MM == 11) {
                if (dd <= 30) {
                    flag = true;
                }
            } else if (MM == 2) {
                if (yyyy % 100 != 0 && yyyy % 4 == 0 || yyyy % 400 == 0) {
                    if (dd <= 29) {
                        flag = true;
                    }
                } else if (dd <= 28) {
                    flag = true;
                }

            } else {
                flag = true;
            }

        }
        return flag;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param start 较小的时间
     * @param end 较大的时间
     * @return 相差天数
     */
    public static Integer getDaysBetween(Date start, Date end, RoundMode mode) {
        if (start == null || end == null)
            return null;
        long startTime = start.getTime();
        long endTime = end.getTime();
        long interval = endTime - startTime;
        Long days = interval / (24 * 3600 * 1000);
        if (mode != null && mode == RoundMode.Ceiling) {
            long rest = interval % (24 * 3600 * 1000);
            if (rest > 0) {
                days += 1;
            }
        }

        return days.intValue();
    }

    /**
     * 两个时间相差的分数
     *
     * @param start
     * @param end
     * @return
     */
    public static Integer getHoursBetween(Date start, Date end, RoundMode mode) {
        if (start == null || end == null) {
            return null;
        }
        long startTime = start.getTime();
        long endTime = end.getTime();
        long interval = endTime - startTime;
        Long hours = interval / (3600 * 1000);
        if (mode != null && mode == RoundMode.Ceiling) {
            long rest = interval % (3600 * 1000);
            if (rest > 0) {
                hours += 1;
            }
        }

        return hours.intValue();
    }

    /**
     * 两个时间相差的分数
     *
     * @param start
     * @param end
     * @return
     */
    public static Integer getMinutesBetween(Date start, Date end, RoundMode mode) {
        if (start == null || end == null) {
            return null;
        }
        long startTime = start.getTime();
        long endTime = end.getTime();
        long interval = endTime - startTime;
        Long minutes = interval / 60000;
        if (mode != null && mode == RoundMode.Ceiling) {
            long rest = interval % 60000;
            if (rest > 0) {
                minutes += 1;
            }
        }

        return minutes.intValue();
    }
}
