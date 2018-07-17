package com.ccclubs.helper;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ccclubs.model.CsEmail;
import com.ccclubs.model.CsUserType;
import com.ccclubs.service.common.ICommonUtilService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.lazy3q.web.helper.LZ;
import com.lazy3q.web.helper.Lazy;
import com.lazy3q.web.helper.$;

public class UtilHelper {	
	/**
	 * 发送模版短信
	 * @param flag 标识 
	 * @param mobile 手机号码
	 * @param params 参数列表
	 */
	public static Boolean sendTemplateSMS(Long host,String flag, String mobile,String defaultTemplate,SMSType smsType,Map<String,String> params) {

		ICommonUtilService commonUtilService = Lazy.GetSpringBean("commonUtilService");
		
		return commonUtilService.sendTemplateSms(host,flag, mobile,defaultTemplate,smsType, params);
	}
	/**
     * 发送模版短信
     * @param flag 标识 
     * @param mobile 手机号码
     * @param params 参数列表
     * @param append 附加信息
     */
    public static Boolean sendTemplateSMS(Long host,String flag, String mobile,String defaultTemplate,SMSType smsType,Map<String,String> params, String append) {

        ICommonUtilService commonUtilService = Lazy.GetSpringBean("commonUtilService");
        
        return commonUtilService.sendTemplateSms(host,flag, mobile,defaultTemplate,smsType, params, append);
    }
	/**
	 * 发送普通短信
	 * @param mobile 手机号码
	 * @param content 短信内容
	 */
	public static void sendSms(Long host,String mobile, String content,SMSType smsType) {
		
		ICommonUtilService commonUtilService = Lazy.GetSpringBean("commonUtilService");
		
		commonUtilService.sendSms(host,mobile, content,smsType);
	}
	
	/**
	 * 获取系统会员类型标识
	 * @param userType
	 * @return
	 */
	public static CsUserType getUserType(String strFlag) {
		ICommonUtilService commonUtilService = Lazy.GetSpringBean("commonUtilService");		
		return commonUtilService.getUserType(strFlag);
	}
	
	
	/**
	 * 获取常用时间区间
	 * @return
	 */
	public static Map getDateTimeAreas(){
		Map map = new HashMap();		
		
		Calendar calendar = Calendar.getInstance();
		//今天
		Map today = new HashMap();
		today.put("end", ToDayEnd(calendar));
		today.put("start", ToDayStart(calendar));
		map.put("today", today);
		
		//本周		
		Map week = new HashMap();
		calendar.set(Calendar.DAY_OF_WEEK, 7);
		week.put("end", ToDayEnd(calendar));
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		week.put("start", ToDayStart(calendar));
		map.put("week", week);		
		
		//本月
		Map month = new HashMap();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, 1);//下个月
		calendar.set(Calendar.DAY_OF_MONTH, 1);//1号
		calendar.add(Calendar.DATE, -1);//减一天
		month.put("end", ToDayEnd(calendar));
		calendar.set(Calendar.DAY_OF_MONTH, 1);//本月1号
		month.put("start", ToDayStart(calendar));
		map.put("month", month);		
		
		//上月
		Map premonth = new HashMap();
		calendar.add(Calendar.DATE, -1);//减一天
		premonth.put("end", ToDayEnd(calendar));
		calendar.set(Calendar.DAY_OF_MONTH, 1);//本月1号
		premonth.put("start", ToDayStart(calendar));
		map.put("premonth", premonth);
		
		
		//今年
		Map year = new HashMap();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, 1);//下一年
		calendar.set(Calendar.MONTH, 0);//1月
		calendar.set(Calendar.DAY_OF_MONTH, 1);//1号
		year.put("end", new Date(ToDayStart(calendar).getTime()-1000));
		calendar.add(Calendar.YEAR, -1);//减一年
		year.put("start", ToDayStart(calendar));
		map.put("year", year);
		
		//去年
		Map preyear = new HashMap();
		preyear.put("end", new Date(ToDayStart(calendar).getTime()-1000));
		calendar.add(Calendar.YEAR, -1);//减一年
		preyear.put("start", ToDayStart(calendar));
		map.put("preyear", preyear);
		
		return map;
	}
	public static Date ToDayEnd(Calendar calendar){
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
	public static Date ToDayStart(Calendar calendar){
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	public static Date getBirthdayFromIdCard(String idcard){
		try{
			if($.empty(idcard)||idcard.length()<15)
				return null;
			int y = Integer.parseInt(idcard.substring(6, 10));
			int m = Integer.parseInt(idcard.substring(10, 12));
			int d = Integer.parseInt(idcard.substring(12, 14));
			if(y<1900 || y>3000)
				return null;
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, y);
			calendar.set(Calendar.MONTH, m-1);
			calendar.set(Calendar.DATE, d);
			return calendar.getTime();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
}
