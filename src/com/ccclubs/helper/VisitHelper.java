package com.ccclubs.helper;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ccclubs.model.CsActivity;
import com.ccclubs.model.CsChannel;
import com.ccclubs.model.CsMarketPlan;
import com.ccclubs.model.CsVisitDetail;
import com.ccclubs.model.CsVisitIp;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;

/**
 * 访问统计助手
 * @author uiu
 *
 */
public class VisitHelper {

	//访问记录平台标识
	static String visitPlatformFlag = $.config("visit.platform.flag");
	static{
		try {
			if(!$.empty(visitPlatformFlag)){
				$.on("request", new Function(){
					@Override
					public <T> T execute(Object... arg0) {
						executeVisit();
						return null;
					}
					/**
					 * 处理访问
					 */
					public void executeVisit() {
						try{
							String xRequestedWith = $.getServletRequest().getHeader("x-requested-with");
							if(xRequestedWith!=null){//ajax请求，放弃统计
								return;
							}
						}catch(Exception ex){
							ex.printStackTrace();
						}
						//获取来源标识,格式[C3,P4],其中C表示渠道，P表示营销策划,跟的数字表示对应的ID
						String strReferer = $.getString("referer");
						if($.empty(strReferer))return;
						String[] array = strReferer.toUpperCase().split(",");
						Long marketPlan = null;
						Long channelId = null;
						Long activity = null;
						for(String flag:array){
							String sid = flag.replaceAll("[^0-9]", "");
							if($.empty(sid))continue;
							if(flag.startsWith("C"))
								channelId = Long.valueOf(sid);
							if(flag.startsWith("P"))
								marketPlan = Long.valueOf(sid);
							if(flag.startsWith("A"))
								activity = Long.valueOf(sid);
						}
						Boolean uv = false;
						if($.getSession("referer.uv")==null)
							$.setSession("referer.uv", uv=true);
						onVisit(visitPlatformFlag,marketPlan,channelId,activity,$.getServletRequest().getRemoteAddr(),uv);
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存访问记录
	 * @param platform 平台标识 不能为空
	 * @param marketPlan 可为空 与channelid不能同时空
	 * @param channelid 可为空 与marketPlan不能同时空
	 * @param ip IP地址 可为空
	 * @param uv 是否UV，需调用都计算
	 */
	public static void onVisit(final String platform,final Long marketPlan,final Long channelid,final Long activity,final String ip,final Boolean uv){
		if(marketPlan==null && channelid==null && activity==null)
			return;
		new Thread(){
			@Override
			public void run() {
				VisitHelper.storeVisit(platform, marketPlan, channelid,activity, ip, uv);
			}
		}.start();
	}
	//保存访问记录,一个小时为一个时间点
	protected static void storeVisit(String platform, Long marketPlan,Long channelid,final Long activity, String ip, Boolean uv) {
		if(marketPlan==null && channelid==null && activity==null)
			return;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		
		com.ccclubs.model.CsVisitDetail.M where = CsVisitDetail.where().csvdAddTimeStart(calendar.getTime()).csvdPlatform(platform).csvdChannel(channelid).csvdPlan(marketPlan).csvdActivity(activity);
		if(marketPlan==null)
			where.add(CsVisitDetail.F.csvdPlan+"Not", "");
		if(channelid==null)
			where.add(CsVisitDetail.F.csvdChannel+"Not", "");
		if(activity==null)
			where.add(CsVisitDetail.F.csvdActivity+"Not", "");		
		CsVisitDetail csVisitDetail = where.get();
		
		
		Long hostid = 0l;
		try{
			if(marketPlan!=null)
				hostid = CsMarketPlan.get(marketPlan).getCsmpHost();
		    else if(channelid!=null)
			    hostid = CsChannel.get(channelid).getCscHost();
		    else if(activity!=null)
		    	hostid = CsActivity.get(activity).getCsaHost();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		if(csVisitDetail==null){
			csVisitDetail = new CsVisitDetail(
				hostid	
				,platform//所属平台
			 	,channelid//所属渠道
			 	,marketPlan//所属策划
			 	,activity//所属活动
			 	,0//PV [非空]
			 	,0//UV [非空]
			 	,0//IP [非空]
			 	,new Date()//访问时间 [非空]
			 	,(short)1//状态 [非空]
			 ).save();
		}
		
		csVisitDetail.setCsvdPv(csVisitDetail.getCsvdPv()+1);
		
		if(uv)
			csVisitDetail.setCsvdUv(csVisitDetail.getCsvdUv()+1);
		
		if(!$.empty(ip) && (marketPlan!=null || channelid!=null || activity!=null)){
			//查询是否同一天同一IP
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			
			CsVisitIp.M ipWhere = CsVisitIp.where().csviAddress(ip).csviPlan(marketPlan).csviChannel(channelid).csviActivity(activity);
			if(marketPlan==null)
				ipWhere.add(CsVisitIp.F.csviPlan+"Not", "");
			if(channelid==null)
				ipWhere.add(CsVisitIp.F.csviChannel+"Not", "");
			if(activity==null)
				ipWhere.add(CsVisitIp.F.csviActivity+"Not", "");				
			CsVisitIp visitIp = ipWhere.get();
			if(visitIp==null){
				new CsVisitIp(
					hostid	
					,ip//IP地址 [非空]
				 	,channelid//所属渠道
				 	,marketPlan//所属策划
				 	,activity//所属活动
				 	,null//源地址
				 	,null//目标地址
				 	,new Date()//访问时间 [非空]
				 ).save();
				csVisitDetail.setCsvdIp(csVisitDetail.getCsvdIp()+1);
			}
		}
		csVisitDetail.update();
	}
	
	
}
