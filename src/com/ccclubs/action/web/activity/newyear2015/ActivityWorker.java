package com.ccclubs.action.web.activity.newyear2015;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.ccclubs.helper.redis.DefaultJRedisClient;
import com.ccclubs.model.CsChjSign;
import com.ccclubs.model.CsPack;
import com.ccclubs.service.admin.ICsChjSignService;
import com.ccclubs.service.admin.ICsPackService;
import com.ccclubs.util.DateUtil;
import com.lazy3q.web.helper.$;

/**
 * 
 * 抢购活动处理线程
 * 
 * @author joel
 *
 */
public class ActivityWorker extends Thread implements Activity{
	
	public void run() {
		if($.empty($.config("release.online"))){
		
			ActivityBase activityBase = new ActivityBase();
			
			ICsChjSignService csChjSignService = $.getBean("csChjSignService");
			ICsPackService csPackService = $.getBean("csPackService");
			DefaultJRedisClient jRedisClient = $.getBean("jRedisClient");
			
			List<CsChjSign> dataList = csChjSignService.getCsChjSignList($.add(CsChjSign.F.cscsYear, 2015), -1);
			List<CsPack> packs = csPackService.getCsPackList($.add("definex",""+CsPack.C.cspFlag+" like '"+activityBase.NEW_YEAR_PACK_2015_+"%'"), -1);
			
			if(!CollectionUtils.isEmpty(dataList) && !CollectionUtils.isEmpty(packs)){
				
				Map<Long, CsPack> map = new HashMap<Long, CsPack>();
				for(CsPack pack : packs){
					map.put(pack.getCspId(), pack);
				}
				
				while(true){
					try {
						for(CsChjSign sign : dataList){
							String key = "meal_book_"+sign.getCscsMember()+"";
							String userBook = jRedisClient.get(key);
							Long[] step = activityBase.getCurrentStep(new Date());
							if(!StringUtils.isEmpty(userBook)){
								String[] str = userBook.split(",");
								Date date = new DateUtil().StringtoDate(str[2], DATE_FORMAT);
								if(date!=null){
									long time_end = date.getTime() + 1000 * 60 *30;
									if(time_end <= new Date().getTime()){
										CsPack pack = map.get(Long.valueOf(str[0]));
										if(pack!=null){
											String currentPackKey = pack.getCspFlag()+"-"+step[0];
											jRedisClient.del(key);
											jRedisClient.decr(currentPackKey);
											
											try{
												CsChjSign csChjSign = csChjSignService.getCsChjSign($.add(CsChjSign.F.cscsMember, Long.valueOf(str[1])).add(CsChjSign.F.cscsYear, 2015));
												if(csChjSign != null){
													csChjSign.setCscsLocked(null);
													csChjSign.setCscsRemark(csChjSign.getCscsRemark()+"#"+new DateUtil().dateToString(new Date(), DATE_FORMAT)+"自动失效"+currentPackKey);
													csChjSign.setCscsUpdateTime(new Date());
													csChjSign.update();
												}
											}catch(Exception e){
												e.printStackTrace();
											}
											
										}
									}
								} 
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						Thread.sleep(30000);	//一分钟去拉去一次数据
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
}
