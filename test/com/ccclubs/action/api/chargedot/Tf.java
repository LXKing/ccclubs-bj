package com.ccclubs.action.api.chargedot;

import com.ccclubs.util.DateUtil;

public class Tf {
	public static void main(String[] args) {
		final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		System.out.println("6, 4 "+ new DateUtil().StringtoDate("2015-01-15 17:38:00", DATE_FORMAT).getTime());
		System.out.println("6, 3 "+ new DateUtil().StringtoDate("2015-01-15 18:04:00", DATE_FORMAT).getTime());
		System.out.println("4, 2 "+ new DateUtil().StringtoDate("2015-01-15 19:14:00", DATE_FORMAT).getTime());
		System.out.println("3, 2 "+ new DateUtil().StringtoDate("2015-01-15 19:37:00", DATE_FORMAT).getTime());
		System.out.println("3, 1 "+ new DateUtil().StringtoDate("2015-01-15 19:41:00", DATE_FORMAT).getTime());
		System.out.println("2, 1 "+ new DateUtil().StringtoDate("2015-01-15 19:45:00", DATE_FORMAT).getTime());
		System.out.println("1, 1 "+ new DateUtil().StringtoDate("2015-01-15 20:35:00", DATE_FORMAT).getTime());
		System.out.println("0, 0 "+ new DateUtil().StringtoDate("2015-01-15 23:50:00", DATE_FORMAT).getTime());
		System.out.println("SMART "+ new DateUtil().StringtoDate("2015-01-15 23:59:59", DATE_FORMAT).getTime());
		System.out.println(" ================================================================================ ");
		System.out.println("18, 5, 7 "+ new DateUtil().StringtoDate("2015-01-16 15:23:00", DATE_FORMAT).getTime());
		System.out.println("17, 5, 7 "+ new DateUtil().StringtoDate("2015-01-16 17:14:00", DATE_FORMAT).getTime());
		System.out.println("17, 4, 7 "+ new DateUtil().StringtoDate("2015-01-16 16:20:00", DATE_FORMAT).getTime());
		System.out.println("15, 3, 6 "+ new DateUtil().StringtoDate("2015-01-16 16:50:00", DATE_FORMAT).getTime());
		System.out.println("14, 3, 6 "+ new DateUtil().StringtoDate("2015-01-16 20:03:00", DATE_FORMAT).getTime());
		System.out.println("14, 2, 6 "+ new DateUtil().StringtoDate("2015-01-16 21:22:00", DATE_FORMAT).getTime());
		System.out.println(" ================================================================================ ");
		System.out.println("12, 2, 5 "+ new DateUtil().StringtoDate("2015-01-17 09:28:00", DATE_FORMAT).getTime());
		System.out.println("11, 2, 5 "+ new DateUtil().StringtoDate("2015-01-17 11:13:00", DATE_FORMAT).getTime());
		System.out.println("11, 1, 5 "+ new DateUtil().StringtoDate("2015-01-17 11:42:00", DATE_FORMAT).getTime());
		System.out.println("9, 1, 5 "+ new DateUtil().StringtoDate("2015-01-17 13:10:00", DATE_FORMAT).getTime());
		System.out.println("7, 1, 5 "+ new DateUtil().StringtoDate("2015-01-17 17:52:00", DATE_FORMAT).getTime());
		System.out.println("6, 1, 5 "+ new DateUtil().StringtoDate("2015-01-17 20:11:00", DATE_FORMAT).getTime());
		System.out.println("6, 0, 5 "+ new DateUtil().StringtoDate("2015-01-17 21:18:00", DATE_FORMAT).getTime());
		
		System.out.println("end "+ new DateUtil().StringtoDate("2015-01-17 23:59:59", DATE_FORMAT).getTime());
	}
}
