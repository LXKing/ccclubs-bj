package com.ccclubs.helper;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * RSA加密解密工具 
 * @author hc
 *
 */
public class RSAUtil {


	/**
	 * 公密钥
	 */
	private static String publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdFJFsd12k6rYpyoTTPNc5kfU3vHG39w4mmwbSMp/uQRK7oMf4iE+DvjC1cMNiTi0hV/AAzdmXlZYoNhlmWoxzy6o03/u9dZnzHp9rlTxQUdz63yiebyUwj3ikpfdXNYTvMpH/0L0Z6E4yTML8yFVuai/FRaN0m6NkEpIFX3F5GQIDAQAB";
	
	/**
	 * 私密钥
	 */
	private static String privateKeyString = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJ0UkWx3XaTqtinKhNM81zmR9Te8cbf3DiabBtIyn+5BErugx/iIT4O+MLVww2JOLSFX8ADN2ZeVlig2GWZajHPLqjTf+711mfMen2uVPFBR3PrfKJ5vJTCPeKSl91c1hO8ykf/QvRnoTjJMwvzIVW5qL8VFo3Sbo2QSkgVfcXkZAgMBAAECgYEAi56YlByVM90cBZFo9omBlD5UBDKPscD65v2s1nEBDrjwea2zkQk4BBWtHdkZEewC2Txcq5pM6huf2CYd9YhOhcip8rB8DGaQElaTHmI4+MzNpVmCs2mA1Pp0p1YdyrxRBnG7FPnuGc8e8dG5ZEmaWVyWNnI000gsY9WX0xm3EcECQQDaBYfatVrO1R6QuqXtBVfHZ6yVCXFk6baMtt86iRZq/R8L0xRMcpOAtXlHoWiNhmLG6I88C4g7SZwB+8LR7TMtAkEAuHFrP2VfdUzQ3mFrS9LLKYSbhFMwAoggd68TuJQ8tB5dBAVdKMMrYFVNzdTtN88MzXyaJZmEN7XA9EleBY2BHQJAAmbhMkWhNHoglhOYVF4ks2uu+Q1blHMGsdIp72knOBKFzwmKRbVVbquRJHrgvf5nBC33tf+ihpSvBU6SvOFSGQJBAJygTAfA6SguPCysI7Z24el2362mCt4LhS69cQzMbSQzqJ9ygqLL4fjjV7fT+kHrRJ73EdueGwZIBPwtlXc3rA0CQQDSjbwmKOIhoV+ZQzMsibzOM2F6hSUeLw66V8GMGZ6mWqpYVuEFOKsFGnzF4KNU5/1q19ORxh7PILttz4r+xJEg";
	
	/**
	 * 获取 keyPair
	 * @param keyLength
	 * @return
	 */
	public static KeyPair generateKeypair() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            return kpg.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("RSA algorithm not supported",e);
        }
    }
	
	 /**
      * 根据publicKey获取PublicKey对象
      */
    public static PublicKey getPublicKey(String key) throws Exception {   
          byte[] keyBytes;   
          keyBytes = (new BASE64Decoder()).decodeBuffer(key);   
          X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);   
          KeyFactory keyFactory = KeyFactory.getInstance("RSA");   
          PublicKey publicKey = keyFactory.generatePublic(keySpec);   
          return publicKey;   
    }
    
    
    /**
     * 根据privateKey获取PrivateKey对象
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {   
          byte[] keyBytes;   
          keyBytes = (new BASE64Decoder()).decodeBuffer(key);   
          PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);   
          KeyFactory keyFactory = KeyFactory.getInstance("RSA");   
          PrivateKey privateKey = keyFactory.generatePrivate(keySpec);   
          return privateKey;   
    }
	
    /**
     *  解密
     * @param encrypted
     * @param keys
     * @return
     */
    public static String decrypt(String encrypted) {
        Cipher dec;
        try {
          dec = Cipher.getInstance("RSA");
       	  PrivateKey privateKey = getPrivateKey(privateKeyString);
       	  dec.init(Cipher.DECRYPT_MODE, privateKey);  
        } catch (Exception e) {
            throw new RuntimeException("RSA algorithm not supported",e);
        }
        byte[] data = hexStringToByteArray(encrypted);
        byte[] decryptedBlock = null;
        byte[]  deBytes = null;
		try {
			decryptedBlock = dec.doFinal(data);
			String s = new String(decryptedBlock,"US-ASCII");
			BASE64Decoder base64Decoder = new BASE64Decoder();
			deBytes = base64Decoder.decodeBuffer(s);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return new String(deBytes);
    }
    
    /**
     * 数据转换
     * @param data
     * @return
     */
    public static byte[] hexStringToByteArray(String data) {
        int k = 0;
        byte[] results = new byte[data.length() / 2];
        for (int i = 0; i < data.length();) {
            results[k] = (byte) (Character.digit(data.charAt(i++), 16) << 4);
            results[k] += (byte) (Character.digit(data.charAt(i++), 16));
            k++;
        }
        return results;
    }
	
	/**
	 * 获取 modulus
	 * @param keyPair
	 * @return
	 */
	 public static String getPublicKeyModulus(RSAPublicKey publicKey) {
        return publicKey.getModulus().toString(16);
	 }
	 
	 /**
	  * 获取 Exponent
	  * @param keyPair
	  * @return
	  */
	 public static String getPublicKeyExponent(RSAPublicKey publicKey) {
        return publicKey.getPublicExponent().toString(16);
     }
	 
	 /**
	  * 获取 MD
	  * @param keyLength
	  * @return
	  */
	 public static int getMaxDigits(int keyLength)   {
        return ((keyLength *2)/16)+3;
     }
	
	/**
     * 从Key对象中取得Key的String值
     */
    public static String getKeyString(Key key) throws Exception {   
          byte[] keyBytes = key.getEncoded();   
          String s = (new BASE64Encoder()).encode(keyBytes);
          return s;   
    }
    
    
    public static String getJSKey(PublicKey publicKey,int keyLength){
    	StringBuffer buff = new StringBuffer();
    	buff.append("{");
    	buff.append("\"e\":");
    	buff.append("\"");
    	buff.append(getPublicKeyExponent((RSAPublicKey) publicKey));
    	buff.append("\",");
    	buff.append("\"n\":");
    	buff.append("\"");
    	buff.append(getPublicKeyModulus((RSAPublicKey) publicKey));
    	buff.append("\",");
    	buff.append("\"maxdigits\":");
    	buff.append("\"");
    	buff.append(getMaxDigits(keyLength));
    	buff.append("\"");
    	buff.append("}");
    	return buff.toString();
    }
    
    /*
     * 使用publicKey来加密  
     */
    public static String enCrypt(String publicKeyString,String content) throws Exception{
  	  Cipher cipher = Cipher.getInstance("RSA");
  	  PublicKey publicKey = getPublicKey(publicKeyString);
  	  cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
        byte[] enBytes = cipher.doFinal(content.getBytes());  
        return (new BASE64Encoder()).encode(enBytes);
    }
    
    /*
     * 使用privateKey来解密  
     */
    public static String deCrypt(String privateKeyString,String content) throws Exception{
  	  Cipher cipher = Cipher.getInstance("RSA");
  	  PrivateKey privateKey = getPrivateKey(privateKeyString);
  	  cipher.init(Cipher.DECRYPT_MODE, privateKey);  
  	  byte[] deBytes = (new BASE64Decoder()).decodeBuffer(content);
        byte[] orgBytes = cipher.doFinal(deBytes);  
        return new String(orgBytes);
    }
    
    
    public static void main(String[] args){
    	
    	///生成 公有key 和私有key
    	KeyPair keyPair = RSAUtil.generateKeypair();
    	try {
    		String publicKey = getKeyString(keyPair.getPublic());
    		System.out.println("公有key="+publicKey);
    		String privateKey = getKeyString(keyPair.getPrivate());
    		System.out.println("私有key="+privateKey);
    		//生产js段的key
    		String key = getJSKey(getPublicKey(publicKeyString), 1024);
			System.out.println(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	
}
