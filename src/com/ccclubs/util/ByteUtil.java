package com.ccclubs.util;

import com.ccclubs.util.struct.Struct;


public class ByteUtil {

	/**
	   * 通过ASCII码将十进制的字节数组格式化为十六进制字符串
	   * @see 该方法会将字节数组中的所有字节均格式化为字符串
	   * @see 使用说明详见<code>formatToHexStringWithASCII(byte[], int, int)</code>方法
	   */
	  private static String formatToHexStringWithASCII(byte[] data){
	    return formatToHexStringWithASCII(data, 0, data.length);
	  }
	  
	  
	  /**
	   * 通过ASCII码将十进制的字节数组格式化为十六进制字符串
	   * @see 该方法常用于字符串的十六进制打印,打印时左侧为十六进制数值,右侧为对应的字符串原文
	   * @see 在构造右侧的字符串原文时,该方法内部使用的是平台的默认字符集,来解码byte[]数组
	   * @see 该方法在将字节转为十六进制时,默认使用的是<code>java.util.Locale.getDefault()</code>
	   * @see 详见String.format(String, Object...)方法和new String(byte[], int, int)构造方法
	   * @param data   十进制的字节数组
	   * @param offset 数组下标,标记从数组的第几个字节开始格式化输出
	   * @param length 格式长度,其不得大于数组长度,否则抛出java.lang.ArrayIndexOutOfBoundsException
	   * @return 格式化后的十六进制字符串
	   */
	  private static String formatToHexStringWithASCII(byte[] data, int offset, int length){
	    int end = offset + length;
	    StringBuilder sb = new StringBuilder();
	    StringBuilder sb2 = new StringBuilder();
	    sb.append("\r\n------------------------------------------------------------------------");
	    boolean chineseCutFlag = false;
	    for(int i=offset; i<end; i+=16){
	      sb.append(String.format("\r\n%04X: ", i-offset)); //X或x表示将结果格式化为十六进制整数
	      sb2.setLength(0);
	      for(int j=i; j<i+16; j++){
	        if(j < end){
	          byte b = data[j];
	          if(b >= 0){ //ENG ASCII
	            sb.append(String.format("%02X ", b));
	            if(b<32 || b>126){ //不可见字符
	              sb2.append(" ");
	            }else{
	              sb2.append((char)b);
	            }
	          }else{ //CHA ASCII
	            if(j == i+15){ //汉字前半个字节
	              sb.append(String.format("%02X ", data[j]));
	              chineseCutFlag = true;
	              String s = new String(data, j, 2);
	              sb2.append(s);
	            }else if(j == i&&chineseCutFlag){ //后半个字节
	              sb.append(String.format("%02X ", data[j]));
	              chineseCutFlag = false;
	              String s = new String(data, j, 1);
	              sb2.append(s);
	            }else{
	              sb.append(String.format("%02X %02X ", data[j], data[j + 1]));
	              String s = new String(data, j, 2);
	              sb2.append(s);
	              j++;
	            }
	          }
	        }else{
	          sb.append("   ");
	        }
	      }
	      sb.append("| ");
	      sb.append(sb2.toString());
	    }
	    sb.append("\r\n------------------------------------------------------------------------");
	    return sb.toString();
	}
	  
	public static byte[] union(byte[] byteArr1, byte[] byteArr2){
		  	byte[] byte_3 = new byte[byteArr1.length+byteArr2.length];  
		    System.arraycopy(byteArr1, 0, byte_3, 0, byteArr1.length);  
		    System.arraycopy(byteArr2, 0, byte_3, byteArr1.length, byteArr2.length);  
			return byte_3;
	}
	
//    /* 字符串转byte[] 
//        这个方法转换后的结果是会多一些 48字符进来的就是代表的是0不知道为什么，但是可以只是取出指定的字符串就行了 
//    */  
//		 public static byte[] hexStringToBytes(String hexString) 
//		  {  
//		    if (hexString == null || hexString.equals(""))
//		     {         
//		              return null;  
//			}  
//			 hexString = hexString.toUpperCase();  
//			 int length = hexString.length() / 2;  
//			 char[] hexChars = hexString.toCharArray();  
//			 byte[] d = new byte[length];  
//			 for (int i = 0; i < length; i++) {  
//			     int pos = i * 2;  
//			     d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));  
//			 }  
//			 return d;  
//			}  	
      
    /* byte转short */  
    public final static short getShort(byte[] buf, boolean asc, int len) {  
        short r = 0;  
        if (asc)  
          for (int i = len - 1; i >= 0; i--) {  
            r <<= 8;  
            r |= (buf[i] & 0x00ff);  
          }  
        else  
          for (int i = 0; i < len; i++) {  
            r <<= 8;  
            r |= (buf[i] & 0x00ff);  
          }  
          
      return r;  
    }  
      
    /* B2 -> 0xB2 */  
    public static int stringToByte(String in, byte[] b) throws Exception {   
        if (b.length < in.length() / 2) {   
            throw new Exception("byte array too small");   
        }  
          
        int j=0;  
        StringBuffer buf = new StringBuffer(2);   
        for (int i=0; i<in.length(); i++, j++) {   
             buf.insert(0, in.charAt(i));   
             buf.insert(1, in.charAt(i+1));   
           int t = Integer.parseInt(buf.toString(),16);   
            System.out.println("byte hex value:" + t);   
          b[j] = (byte)t;  
            i++;   
             buf.delete(0,2);   
         }   
          
        return j;  
     }  
      
     /* byte to  int */  
    public final static int getInt(byte[] buf, boolean asc, int len) {  
        if (buf == null) {  
          throw new IllegalArgumentException("byte array is null!");  
       }  
        if (len > 4) {  
           throw new IllegalArgumentException("byte array size > 4 !");  
         }  
        int r = 0;  
         if (asc)  
          for (int i = len - 1; i >= 0; i--) {  
          r <<= 8;  
             r |= (buf[i] & 0x000000ff);  
           }  
        else  
          for (int i = 0; i < len; i++) {  
           r <<= 8;  
            r |= (buf[i] & 0x000000ff);  
           }  
         return r;  
     }  
      
     /* int -> byte[] */  
    public static byte[] intToBytes(int num) {  
        byte[] b = new byte[4];  
        for (int i = 0; i < 4; i++) {  
         b[i] = (byte) (num >>> (24 - i * 8));  
       }  
         
        return b;  
     }  
       
     /* short to byte[] */  
     public static byte[] shortToBytes(short num) {  
       byte[] b = new byte[2];  
          
        for (int i = 0; i < 2; i++) {  
            b[i] = (byte) (num >>> (i * 8));  
        }  
          
       return b;  
    }  
       
     /* byte to String */  
     private static char findHex(byte b) {   
        int t = new Byte(b).intValue();   
         t = t < 0 ? t + 16 : t;   
  
        if ((0 <= t) &&(t <= 9)) {   
        return (char)(t + '0');   
         }   
   
         return (char)(t-10+'A');   
     }   
     public static String byteToString(byte b) {   
        byte high, low;   
         byte maskHigh = (byte)0xf0;   
         byte maskLow = 0x0f;   
   
         high = (byte)((b & maskHigh) >> 4);   
        low = (byte)(b & maskLow);   
   
         StringBuffer buf = new StringBuffer();   
        buf.append(findHex(high));   
        buf.append(findHex(low));   
 
         return buf.toString();   
    }  
       
    /* short -> byte */  
     public final static byte[] getBytes(short s, boolean asc) {  
         byte[] buf = new byte[2];  
        if (asc)      for (int i = buf.length - 1; i >= 0; i--) {        buf[i] = (byte) (s & 0x00ff);  
            s >>= 8;  
           }  
         else  
          for (int i = 0; i < buf.length; i++) {  
            buf[i] = (byte) (s & 0x00ff);  
             s >>= 8;  
          }  
         return buf;  
       }  
     
     /* int -> byte[] */  
      public final static byte[] getBytes(int s, boolean asc) {  
        byte[] buf = new byte[4];  
         if (asc)  
           for (int i = buf.length - 1; i >= 0; i--) {  
            buf[i] = (byte) (s & 0x000000ff);  
           s >>= 8;  
           }  
        else  
           for (int i = 0; i < buf.length; i++) {  
             buf[i] = (byte) (s & 0x000000ff);  
             s >>= 8;  
           }  
        return buf;  
       }  
         
       /* long -> byte[] */  
      public final static byte[] getBytes(long s, boolean asc) {  
         byte[] buf = new byte[8];  
        if (asc)  
          for (int i = buf.length - 1; i >= 0; i--) {  
             buf[i] = (byte) (s & 0x00000000000000ff);  
             s >>= 8;  
          }  
         else  
          for (int i = 0; i < buf.length; i++) {  
            buf[i] = (byte) (s & 0x00000000000000ff);  
            s >>= 8;  
           }  
        return buf;  
      }  
         
       /* byte[]->int */  
      public final static int getInt(byte[] buf, boolean asc) {  
         if (buf == null) {  
           throw new IllegalArgumentException("byte array is null!");  
        }  
        if (buf.length > 4) {  
           throw new IllegalArgumentException("byte array size > 4 !");  
        }  
         int r = 0;  
         if (asc)  
          for (int i = buf.length - 1; i >= 0; i--) {  
            r <<= 8;  
             r |= (buf[i] & 0x000000ff);  
          }  
         else  
           for (int i = 0; i < buf.length; i++) {  
             r <<= 8;  
            r |= (buf[i] & 0x000000ff);  
          }  
         return r;  
       }  
      /* byte[] -> long */  
      public final static long getLong(byte[] buf, boolean asc) {  
        if (buf == null) {  
           throw new IllegalArgumentException("byte array is null!");  
         }  
        if (buf.length > 8) {  
           throw new IllegalArgumentException("byte array size > 8 !");  
         }  
         long r = 0;  
        if (asc)  
           for (int i = buf.length - 1; i >= 0; i--) {  
            r <<= 8;  
             r |= (buf[i] & 0x00000000000000ff);  
          }  
         else  
           for (int i = 0; i < buf.length; i++) {  
            r <<= 8;  
            r |= (buf[i] & 0x00000000000000ff);  
          }  
        return r;  
      }  
	
	public static void main(String[] args) {
		String vald = "5B 00 00 00 1B 00 00 00 00 10 02 00 00 04 61 01 00 00 00 00 00 00 00 00 68 17 5D 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 5B 00 00 00 1A 00 00 00 00 10 06 00 00 04 61 01 00 00 00 00 00 00 00 23 B4 5D 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00";
		byte[] b = Struct.getBytesFromString(vald.replace(" ", ""));
		System.out.println(formatToHexStringWithASCII(b));
	}
}
