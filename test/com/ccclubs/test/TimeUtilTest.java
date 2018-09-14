package com.ccclubs.test;

import java.util.Date;
import com.ccclubs.param.TimeUtil;

public class TimeUtilTest {

    public static void main(String[] args) {
        
        Date start = TimeUtil.stringToDate("17:00");
        start = TimeUtil.addMinute(start, 12*60);
        String time = TimeUtil.format(start, TimeUtil.DATE.FORMAT_DEFAULT);
        System.out.println(time);
        
        time = TimeUtil.format(TimeUtil.stringToDate("17:00"), TimeUtil.DATE.FORMAT_DEFAULT);
        System.out.println(time);
        
        String str1 = "2018-09-01 11:00:00";
        String str2 = "2018-09-02 10:00:00";
        Date t1 = TimeUtil.stringToDate(str1);
        Date t2 = TimeUtil.stringToDate(str2);
        int days = TimeUtil.getDaysBetween(t1, t1, null);
        System.out.println(days);
        
        str1 = "2018-09-01 11:00:00";
        str2 = "2018-09-01 11:00:00";
        t1 = TimeUtil.stringToDate(str1);
        t2 = TimeUtil.stringToDate(str2);
        System.out.println(t1.after(t2));
        
        Date d = new Date(1536823800000l);
        str2 = TimeUtil.format(d, TimeUtil.DATE.FORMAT_DEFAULT);
        System.out.println(str2);
    }

}
