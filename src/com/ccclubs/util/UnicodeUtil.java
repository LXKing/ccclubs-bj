package com.ccclubs.util;

import lazy3q.code.template.SSIbatis.limit.shortcut_jsp;

public class UnicodeUtil {

	public static String string2Unicode(final String gbString) {
		char[] utfBytes = gbString.toCharArray();
		String unicodeBytes = "";
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
			String hexB = Integer.toHexString(utfBytes[byteIndex]);
			if (hexB.length() <= 2) {
				hexB = "00" + hexB;
			}
			unicodeBytes = unicodeBytes + "\\\\u" + hexB;
		}
		return unicodeBytes;
	}

	public static String unicode2String(final String unicodeStr) {
		StringBuffer sb = new StringBuffer();
		String str[] = unicodeStr.toUpperCase().split("\\\\U");
		for (int i = 0; i < str.length; i++) {
			if (str[i].equals(""))
				continue;
			char c = (char) Integer.parseInt(str[i].trim(), 16);
			sb.append(c);
		}
		return sb.toString();
	}

	public static short string2UnicodeHex(final String gbString) {
		char[] utfBytes = gbString.toCharArray();
		short result = 0;
		for (int byteIndex = 0; byteIndex < 1; byteIndex++) {
			result = (short) ((char) (utfBytes[byteIndex]));
		}
		return result;
	}

	public static String unicodeHex2String(final short target) {
		StringBuffer sb = new StringBuffer();

		char c = (char) Integer.parseInt(target + "", 10);
		sb.append(c);

		return sb.toString();
	}
}
