import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.ccclubs.action.app.official.util.Md5Util;
import com.ccclubs.helper.SystemHelper;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.LZ;


public class Md5 {
	public static void main(String[] args) {
		System.out.println($.md5("123.57.172.114administrator3c88b61Fa27fd031").toLowerCase());
		System.out.println($.md5("120.55.191.193administrator90da8764AG360ccb").toLowerCase());
		System.out.println($.md5("123456"));
		
		System.out.println("==============================================");
		try {
			System.out.println($.md5(new String("ids8f73sxf3sf43fdxq1appidx3dandroid29gmethodcom.ccclubs.member.saveContactparams{\"relation\":\"家人\",\"people\":\"肖冲\",\"peoplePhoneNumber\":\"18758033172\",\"company\":\"\",\"postAddress\":\"肖冲\"}timestamp2015-11-17 14:29:10token79ae60613fe84919ba8fcb6895a8db6fids8f73sxf3sf43fdxq1".getBytes("UTF-8"),"UTF-8")));
		
			System.out.println(LZ.md5("ids8f73sxf3sf43fdxq1appidx3dandroid29gmethodcom.ccclubs.member.saveContactparams{\"relation\":\"家人\",\"people\":\"肖冲\",\"peoplePhoneNumber\":\"18758033172\",\"company\":\"\",\"postAddress\":\"肖冲\"}timestamp2015-11-17 14:29:10token79ae60613fe84919ba8fcb6895a8db6fids8f73sxf3sf43fdxq1"));
			
			System.out.println("==============================================");
			System.out.println(LZ.md5("ids8f73sxf3sf43fdxq1appidx3dandroid29gmethodcom.ccclubs.member.saveContactparams{\"relation\":\"家人\",\"people\":\"肖冲\",\"peoplePhoneNumber\":\"18758033172\",\"company\":\"\",\"postAddress\":\"肖冲\"}timestamp2015-11-17 14:45:20token79ae60613fe84919ba8fcb6895a8db6fids8f73sxf3sf43fdxq1"));
			
			System.out.println(new Md5Util().MD5("ids8f73sxf3sf43fdxq1appidx3dandroid29gmethodcom.ccclubs.member.saveContactparams{\"relation\":\"家人\",\"people\":\"肖冲\",\"peoplePhoneNumber\":\"18758033172\",\"company\":\"\",\"postAddress\":\"肖冲\"}timestamp2015-11-17 14:50:06token79ae60613fe84919ba8fcb6895a8db6fids8f73sxf3sf43fdxq1"));
			System.out.println(LZ.md5(new String("ids8f73sxf3sf43fdxq1appidx3dandroid29gmethodcom.ccclubs.member.saveContactparams{\"relation\":\"家人\",\"people\":\"肖冲\",\"peoplePhoneNumber\":\"18758033172\",\"company\":\"\",\"postAddress\":\"肖冲\"}timestamp2015-11-17 14:50:06token79ae60613fe84919ba8fcb6895a8db6fids8f73sxf3sf43fdxq1".getBytes("ISO-8859-1"), "UTF-8")));
		
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
