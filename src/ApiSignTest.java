/**
 * This class is used for ...
 * 
 * @date 2015年10月16日
 * @author qsxiaogang
 *             
 */


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.security.MessageDigest;
/**
 * @date 2015年10月16日
 */
public class ApiSignTest
{
	
	
	public static void main(String[] args)
	{
		short a=0;

		System.out.println(urlNearbyCar(0, 11, 0, "", 0l, 0l, a, 0l, 7));
		
		
		
	}
	
	private static String appId="3dbcd8";
	private static String appsecret="8ecfe53a79c";
	
	
	private static String DateFromat="yyyy-mm-dd HH:mm:ss";
	
	private static final String URL_OFFICIAL = "http://api.ccclubs.com/invoke.do?appid="+appId;
	
	
	
	private static String urlNearbyCar(int  pageNo,int pageSize,long host,String CarNo,long Cartype,long Outlets,short State,long area,int day){
		
		String sign="";
		Map< String,Object>map=new HashMap<String, Object>();
		map.put("appid", appId);
		map.put("timestamp", getDate());
		map.put("method", "com.ccclubs.car.page");
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		map.put("host", host);
		map.put("number", CarNo);
		map.put("model", Cartype);
		map.put("outlets", Outlets);
		map.put("state", State);
		map.put("area", area);
		map.put("day", day);
		
		System.out.println(sign);
	sign=	getSign(appsecret, map);
//		return  URL_OFFICIAL+"&timestamp="+getDate()+"&method=com.ccclubs.car.page&sign="+sign;
		return sign;
	}
	
	
	
	
	
	/**获取到时间戳*/
	public static String  getDate(){
		 
		SimpleDateFormat sdf=new SimpleDateFormat(DateFromat);
		return sdf.format(new Date());
		
	}
	
	
	
	
	/**获取到 签名*/
	public static String getSign(String secret,Map<String, Object> map){
		
		List<String> names=new ArrayList();
		
		Iterator it = map.entrySet().iterator();
		while(it.hasNext()){
		Entry entry = (Entry)it.next();
		names.add(entry.getKey().toString());
		}
		
		java.util.Collections.sort(names);
		String strSign = secret;
		 it = map.entrySet().iterator();
		while(it.hasNext()){
		Entry entry = (Entry)it.next();
			strSign+=entry.getKey().toString()+entry.getValue().toString() ;
			}
		
		strSign+=secret;
		String md5 = getMD5(strSign).toUpperCase();
		return md5;
	}
	
	/**MD5 加密*/
    public static String getMD5(String val){  
    	// 返回字符串
    			String md5Str = null;
    			try {
    			// 操作字符串
    			StringBuffer buf = new StringBuffer();

    			MessageDigest md = MessageDigest.getInstance("MD5");

    			// 添加要进行计算摘要的信息,使用 plainText 的 byte 数组更新摘要。
    			md.update(val.getBytes());
    			// 计算出摘要,完成哈希计算。
    			byte b[] = md.digest();
    			int i;
    			for (int offset = 0; offset < b.length; offset++) {
    			i = b[offset];
    			if (i < 0) {
    			i += 256;
    			}
    			if (i < 16) {
    			buf.append("0");
    			}
    			// 将整型 十进制 i 转换为16位，用十六进制参数表示的无符号整数值的字符串表示形式。
    			buf.append(Integer.toHexString(i));
    			}
    			// 32位的加密
    			md5Str = buf.toString();
    			// 16位的加密
    			// md5Str = buf.toString().md5Strstring(8,24);
    			} catch (Exception e) {
    			e.printStackTrace();
    			}
    			return md5Str;
} 
}
