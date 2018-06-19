package com.ccclubs.helper;

import java.math.BigDecimal;

import com.lazy3q.web.helper.$;

/**
 * 数字相关的操作
 * 
 * @author Administrator
 * 
 */
public class DigitHelper {

	public static String digit(Object number, int num) {
		return $.digit(number, num);
	}

	/**
	 * 最多保留2位小数
	 * 
	 * @param number
	 * @return
	 */
	public static String digit(Object value) {
		if (value == null)
			return "";
		try {
			BigDecimal bd = new BigDecimal(value.toString());
			// bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
			double result = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			if (result * 100 % 10 > 0) {
				return $.digit(result, 2);
			} else if (result * 10 % 10 > 0) {
				return $.digit(result, 1);
			} else {
				return String.valueOf(SystemHelper.toInt(result));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
}
