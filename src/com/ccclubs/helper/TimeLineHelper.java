package com.ccclubs.helper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.Restriction;
import com.lazy3q.web.helper.$;

public class TimeLineHelper {
	/**
	 * 时间轴对象
	 * @author uiu
	 */
	public static class Timeline implements java.io.Serializable{
		Date start = null;//开始时间
		Date finish = null;//结束时间
		Long order = null;//关联订单
		public Date getStart() {
			return start;
		}
		public void setStart(Date start) {
			this.start = start;
		}
		public Date getFinish() {
			return finish;
		}
		public void setFinish(Date finish) {
			this.finish = finish;
		}
		public Long getOrder() {
			return order;
		}
		public void setOrder(Long order) {
			this.order = order;
		}
	}

	
	/**
	 * 获取车辆时间轴
	 * @param car 	车辆ID
	 * @param count	几天
	 * @return
	 */
	public static List<Timeline> getTimelines(Long carId,Integer dayCount){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DATE, dayCount);
		
		List<Timeline> timelines = new ArrayList();
		if(SYSTEM.REDIS.initialize){
			String[] keys = SYSTEM.REDIS.getKeys("OBJ.TIMELINE."+carId+".*");
			for(String key:keys){
				Timeline timeline = SYSTEM.REDIS.getObject("TIMELINE", key.replace("OBJ.TIMELINE.", ""));
				if(timeline==null)continue;
				if(timeline.getStart().after(calendar.getTime()))continue;
				timelines.add(timeline);
			}
		}else{
			Long today = new Date(new Date().getTime()/SYSTEM.DAY*SYSTEM.DAY).getTime();
			List<CsOrder> orders = CsOrder.getCsOrderList($.add(CsOrder.F.csoCar, carId).add("definex", CsOrder.C.csoStatus + " not in (3,7) and if(ISNULL(cso_ret_time), cso_finish_time  > " + today + " , cso_ret_time >"+ today + ")"), -1);
			for(CsOrder csOrder:orders){
				Timeline timeline = new Timeline();
				timeline.setOrder(csOrder.getCsoId());
				timeline.setStart(csOrder.getCsoStartTime());
				timeline.setFinish($.or(csOrder.getCsoRetTime(),csOrder.getCsoFinishTime()));
				if(timeline.getStart().after(calendar.getTime()))continue;
				timelines.add(timeline);
			}
		}
		return timelines;
	}
	
	
	/**
	 * 根据订单号更新时间轴
	 * @param order
	 */
	public static void update(final Long order){
		if(!SYSTEM.REDIS.initialize)return;
		CsOrder csOrder = CsOrder.get(order);
		update(csOrder);
	}
	/**
	 * 更新所有时间轴
	 */
	public static void undateAll(){
		if(!SYSTEM.REDIS.initialize)return;
		SYSTEM.REDIS.clearObject("TIMELINE");
		String today = $.date(new Date(), "yyyy-MM-dd 00:00:00");
		List<CsOrder> orders = CsOrder.getCsOrderList($.add("definex", CsOrder.C.csoStatus + " not in (3,7) and if(ISNULL(cso_ret_time), cso_finish_time  > '" + today + "' , cso_ret_time >'"+ today + "')"), -1);
		for(CsOrder csOrder:orders)
			update(csOrder);
	}
	private static void update(CsOrder csOrder) {
		if(csOrder.getCsoStatus().shortValue()==3 || csOrder.getCsoStatus().shortValue()==7){
			SYSTEM.REDIS.deleteObject("TIMELINE", csOrder.getCsoCar()+"."+csOrder.getCsoId());
		}else{
			Timeline timeline = new Timeline();
			timeline.setOrder(csOrder.getCsoId());
			timeline.setStart(csOrder.getCsoStartTime());
			timeline.setFinish($.or(csOrder.getCsoRetTime(),csOrder.getCsoFinishTime()));
			SYSTEM.REDIS.setObject("TIMELINE", csOrder.getCsoCar()+"."+csOrder.getCsoId(),timeline);
			//设置时间轴对象有效期至订单结束次日的凌晨
			SYSTEM.REDIS.setObjectExpireTime("TIMELINE", csOrder.getCsoCar()+"."+csOrder.getCsoId(), new Date(timeline.getFinish().getTime()+SYSTEM.DAY));
		}
	}
	private static void active(){
		if($.empty(SYSTEM.REDIS.getValue("TIMELINE", "INIT"))){
			undateAll();
			SYSTEM.REDIS.setValue("TIMELINE","INIT","TRUE");
		}
	}
	public static void start() {
		new Thread(){
			public void run(){
				while(true){
					try{
						active();
					}catch(Exception ex){
						ex.printStackTrace();
					}
					try{
						Thread.sleep(10000l);
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	
	
	
	
	
	public static BufferedImage getTimeLineImage(Date date, Integer count, List<Timeline> timelines,List<Restriction> restrictions) {		
		if(date==null)
			date=new Date();
		if(count==null)
			count = 7;
		
		BufferedImage image = new BufferedImage(273*count+1, 25, BufferedImage.TYPE_INT_ARGB);
		
		int height = 13;
		
		Graphics g = image.getGraphics();		
		g.setColor(new Color(255,255,255));
		g.fillRect(0, 0, image.getWidth(), height);//设定背景色
				
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 6);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date taday6hour = calendar.getTime();
		
		Long minute5 = 1000l*60l*5l;
		
		/**
		 * 画时间颜色,5分钟为一个像素
		 */
		g.setColor(new Color(255,0,0));
		for(Timeline timeline:timelines){
			Date start = timeline.getStart();
			Date finish = timeline.getFinish();	
			Long iStart = (start.getTime()-taday6hour.getTime())/minute5;
			Long iFinish= (finish.getTime()-taday6hour.getTime())/minute5;			
			g.fillRect(iStart.intValue(), 0, (int) (iFinish-iStart), height);//设定背景色
		}
		
		/**
		 * 画限行颜色,5分钟为一个像素
		 */
		g.setColor(new Color(0,0,255));
		for(Restriction restriction:restrictions){
			Date start = restriction.getStart();
			Date finish = restriction.getFinish();			
			Long iStart = (start.getTime()-taday6hour.getTime())/minute5;
			Long iFinish= (finish.getTime()-taday6hour.getTime())/minute5;			
			g.fillRect(iStart.intValue(), 0, (int) (iFinish-iStart), height);//设定背景色
		}	
		
		
		//每天画条分隔线
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		
		while(true){
			Integer xPos = new Long((calendar.getTime().getTime()-taday6hour.getTime())/minute5).intValue();
			
			//每三小时画条分隔线
			g.setColor(new Color(0,0,0,255));	
			g.setFont(new Font("Times New Roman",Font.PLAIN,9));
			for(int i=0;i<24/3;i++){
				//g.drawLine(xPos+i*3*12, 4, xPos+i*3*12, height-5);
				if((xPos+i*3*12-2)>0)
					g.drawString(""+i*3,xPos+i*3*12-2,height-3);
			}
			
			if(count>1){
				g.setColor(new Color(0,0,0));						
				g.drawLine(xPos.intValue(), height+2, xPos.intValue(), image.getHeight());			
				g.setFont(new Font("Times New Roman",Font.PLAIN,12));
				Integer fPos = xPos+12*12-28;//日期字体位置
				g.drawString($.date(calendar.getTime(), "yyyy-MM-dd"),fPos,image.getHeight());
			}
			
			calendar.add(Calendar.DATE, 1);
			if(xPos>image.getWidth())break;
		}
			
		
		
		g.drawRect(0, 0, image.getWidth()-1, height-1);//设定背景色
		
		g.dispose();
		
		return image;
	}

	
	
	
	
	
	public static List<Map> getTimeLineMap(Date date, Integer count, List<Timeline> timelines,List<Restriction> restrictions) {
		List<Map> list = new ArrayList();
		
		if(date==null)
			date=new Date();
		if(count==null)
			count = 7;
		
		int height = 13;
						
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 6);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date taday6hour = calendar.getTime();
		
		Long minute5 = 1000l*60l*5l;
		
		for(Timeline timeline:timelines){
			Date start = timeline.getStart();
			Date finish = timeline.getFinish();			
			Long iStart = (start.getTime()-taday6hour.getTime())/minute5;
			Long iFinish= (finish.getTime()-taday6hour.getTime())/minute5;
			Map map = new HashMap();
			map.put("coords", iStart.intValue()+","+0+","+iFinish+","+height);
			map.put("title", $.date(start, "yyyy-MM-dd HH:mm")+"-"+$.date(finish, "yyyy-MM-dd HH:mm")+"有单");
			list.add(map);
		}
		
		for(Restriction restriction:restrictions){
			Date start = restriction.getStart();
			Date finish = restriction.getFinish();			
			Long iStart = (start.getTime()-taday6hour.getTime())/minute5;
			Long iFinish= (finish.getTime()-taday6hour.getTime())/minute5;			
			Map map = new HashMap();
			map.put("coords", iStart.intValue()+","+0+","+iFinish+","+height);
			map.put("title", $.date(start, "yyyy-MM-dd HH:mm")+"-"+$.date(finish, "yyyy-MM-dd HH:mm")+"限行");
			list.add(map);
		}
		
		return list;
	}


	

}
