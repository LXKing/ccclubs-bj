package com.ccclubs.action.web.activity.newyear2015;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsChjSign;
import com.ccclubs.model.CsPack;
import com.ccclubs.service.admin.ICsChjSignService;
import com.ccclubs.service.admin.ICsPackService;
import com.ccclubs.util.DateUtil;
import com.lazy3q.web.helper.$;

/**
 * 活动基础
 * 
 * @author joel
 *
 */
public class ActivityBase implements Activity{
	
	public static final String	NEW_YEAR_PACK_2015_			= "NEW_YEAR_PACK_2015_";		// 2013春节套餐标识前缀
	public static final String	NEW_YEAR_PACK_2015_KY		= "NEW_YEAR_PACK_2015_KY";		// 别克2013春节套餐标识前缀
	public static final String	NEW_YEAR_PACK_2015_MG		= "NEW_YEAR_PACK_2015_MG";		// 名爵2013春节套餐标识前缀
	public static final String	NEW_YEAR_PACK_2015_SMART	= "NEW_YEAR_PACK_2015_SMART";	// SMART2013春节套餐标识前缀
	
	public final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	//预定套餐
	public static final Date pack_begin_time = new DateUtil().StringtoDate("2015-01-12 11:00:00", "yyyy-MM-dd HH:mm:ss");
	public static final Date pack_second_time = new DateUtil().StringtoDate("2015-01-14 11:00:00", "yyyy-MM-dd HH:mm:ss");
	public static final Date pack_third_time = new DateUtil().StringtoDate("2015-01-16 11:00:00", "yyyy-MM-dd HH:mm:ss");
	public static final Date pack_end_time =  new DateUtil().StringtoDate("2015-01-17 23:59:59", "yyyy-MM-dd HH:mm:ss");
	
	//预定车辆
	public static final Date car_begin_time = new DateUtil().StringtoDate("2015-02-01 11:00:00", "yyyy-MM-dd HH:mm:ss");
	public static final Date car_end_time = new DateUtil().StringtoDate("2015-02-07 23:59:59", "yyyy-MM-dd HH:mm:ss");
	
	//结束
	public static final Date all_end_time = new DateUtil().StringtoDate("2015-02-07 23:59:59", "yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 测试开关
	 */
	protected static volatile boolean isSwitch = true;
	
	Meal meal_bkky = new Meal();
	Meal meal_mg3 = new Meal();
	Meal meal_smart = new Meal();
	
	
	/**
	 * 获取/埋点渠道
	 */
	public void getChannel(){
		String channel = $.getString("spm");
		if(channel!=null && !channel.equals("")){
			$.setSession("channel", channel);
		}
	}
	
	public Date getNow(){
		Date now = $.getDate("temp");
		if(now !=null && isSwitch){
			return now;
		}
		return new Date();
	}
	
	/**
	 * 是否已报名
	 * @return
	 */
	public boolean isSign(){
		ICsChjSignService csChjSignService = $.getBean("csChjSignService");
		CsChjSign csChjSign = csChjSignService.getCsChjSign($.add(CsChjSign.F.cscsMember, SystemHelper.getLoginId())
				.add(CsChjSign.F.cscsYear, 2015));
		return csChjSign !=null && csChjSign.getCscsId()!=null;
	}
	
	/**
	 * 获得当前活动处于的阶段
	 * @return
	 */
	public Long[] getCurrentStep(Date now){
		Long step[] = new Long[2];
		//状态：等待开始(报名中0)-》第一轮1-》第二轮2-》第三轮3-》抢购套餐结束4-》抢车5-》结束6
		if(now == null){
			now = new Date();
		}
		if(now.before(pack_begin_time)){
			step[0] = 0L;	//报名阶段
			step[1] = pack_begin_time.getTime() - now.getTime();
			return step;
		}else{
			if(now.before(pack_second_time)){
				step[0] = 1L;	//第一轮进行中
				step[1] = pack_second_time.getTime() - now.getTime();
				return step;
			}
			
			if(now.before(pack_third_time)){
				step[0] = 2L;	//第二轮进行中
				step[1] = pack_third_time.getTime() - now.getTime();
				return step;
			}
			
			
			if(now.before(car_begin_time)){
				
				if(now.after(pack_end_time)){
					step[0] = 4L;	//抢套餐结束
					step[1] = car_begin_time.getTime() - now.getTime();
					return step;
				}
				
				step[0] = 3L;	//第三轮进行中
				step[1] = pack_end_time.getTime() - now.getTime();
				return step;
			}
			
			if(now.before(all_end_time)){
				step[0] = 5L;	//抢车进行中
				step[1] = all_end_time.getTime() - now.getTime();
				return step;
			}else{
				step[0] = 6L;	//活动已结束
				step[1] = all_end_time.getTime() - now.getTime();
				return step;
			}
		}
	}
	
}
