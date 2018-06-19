package com.ccclubs.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DESUtil {
	
	public static String getIvStr(String ivStr){
		return (ivStr!=null && ivStr.length()>8) ? ivStr.substring(0, 8) : ivStr;
	}
	
	public static String decrypt(String decryptString, String decryptKey, String ivStr)
			throws Exception {
		byte[] iv1 = getIvStr(ivStr).getBytes("ASCII");
		IvParameterSpec iv = new IvParameterSpec(iv1);
		SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, iv);

		return new String(cipher.doFinal(Base64.decode(decryptString)), "utf-8");
	}

	public static String encrypt(String encryptString, String encryptKey, String ivStr)
			throws Exception {
		byte[] iv1 = getIvStr(ivStr).getBytes("ASCII");
		IvParameterSpec iv = new IvParameterSpec(iv1);
		DESKeySpec dks = new DESKeySpec(encryptKey.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		
		return Base64.encode(cipher.doFinal(encryptString.getBytes("utf-8")));
	}
}
