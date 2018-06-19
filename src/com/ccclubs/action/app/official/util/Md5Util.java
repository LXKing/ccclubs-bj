package com.ccclubs.action.app.official.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
	
	/**
	 * 获取MD5加密后的字符串
	 * @param str 加密前的字符串
	 * @return
	 */
	public static String MD5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(str.getBytes("UTF-8"));
			byte[] byteArray = md.digest();
			StringBuffer md5StrBuff = new StringBuffer();
			for (int i = 0; i < byteArray.length; i++) {
				if (Integer.toHexString(0xFF & byteArray[i]).length() == 1){
					md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
				} else {
					md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
				}
			}
			return md5StrBuff.toString();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取加密字符串
	 * @param param
	 * @return
	 */
	public static String MD5ForInteger(Integer param){
		return MD5(param.toString());
	}
	
	public static String MD52(String str) {
		try {
			String key = str.substring(6, 15);
			key = MD5(key);
			key = MD5(key);
			String p = str.substring(0,5);
			String e = str.substring(15, str.length() - 1);
			str = p + e + key;
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(str.getBytes("UTF-8"));
			byte[] byteArray = md.digest();
			StringBuffer md5StrBuff = new StringBuffer();
			for (int i = 0; i < byteArray.length; i++) {
				if (Integer.toHexString(0xFF & byteArray[i]).length() == 1){
					md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
				} else {
					md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
				}
			}
			return md5StrBuff.toString();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
     * Get the MD5 32 and 16 coding.
     * @param str String MD5 Type
     * @return String or Null
     * @author He Daoyuan
     * @version	2016-04-01
     */
	public static String MD5(String str, int type) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            if (32 == type) {
            	return buf.toString();
            }
            if (16 == type) {
            	return buf.toString().substring(8, 24);
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        
        return null;
    }
	
//	public static void main(String[] args) {
//		System.out.println(MD52("410101=10149511;2535=985053545;565298"));
//	}
	
}
