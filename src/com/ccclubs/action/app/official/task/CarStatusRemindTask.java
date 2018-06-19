package com.ccclubs.action.app.official.task;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import com.ccclubs.model.CsOrder;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsStateService;
import com.ccclubs.util.DateUtil;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

public class CarStatusRemindTask {
	
	public static AtomicBoolean init = new AtomicBoolean(false);				//是否已启动实例
	
	private static Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
	
	final ICsStateService csStateService = $.GetSpringBean("csStateService");		
	
	final ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
	
	public void init(){
		if(init.get()){
			return;
		}
		
		Timer timer = new Timer();
		long delay = 10000; 								//从接收到指令延迟十秒后开始执行
		LoadTask task = new LoadTask(this);
		timer.schedule(task, delay, 1000 * 60 * 5);			//执行频率：5分钟
		
		init.set(true);
	}
	
	public void execute(){
		try {
			String time = new DateUtil().dateToString(Calendar.getInstance().getTime(), "yyyy-MM-dd HH:mm:ss");
			List<Map> list = dao.executeQuery("select state.* from cs_state state inner join cs_car car on state.css_car = car.csc_id and car.csc_status = 1 and css_add_time > '"+time+"'and (state.css_ev_battery is not null and state.css_ev_battery< 30)");
			if(list!=null){
				for(Map map : list){
					CsOrder order = csOrderService.getCsOrder($.add("definex", " cso_status in (0,1) and cso_car = '"+map.get("css_car")+"'"));
					if(order !=null){
						//给使用中的用户发短信提醒
						Long member = order.getCsoPayMember();
					}else{
						//给管理员推送消息提醒
						
					}
				}
			}
		} catch (Exception e) {
			
		}
	}
	
}

/**
 * 更新定时任务
 * 
 * @author
 * 
 */
class LoadTask extends TimerTask {
	
	CarStatusRemindTask task;
	
	public LoadTask(CarStatusRemindTask task){
		this.task = task;
	}
	
	/**
	 * 此计时器任务要执行的操作。
	 */
	public void run() {
		try {
			task.execute();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}