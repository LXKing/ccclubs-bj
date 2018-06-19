package com.ccclubs.action.admin.operate.quota;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.helper.SystemHelper;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

public class OrderAction {
	
	public String execute(){
		try{
			Section section = Section.getSection($.getInteger("year",Integer.parseInt($.date(new Date(), "yyyy"))), $.getInteger("month",new Date().getMonth()+1),$.getInteger("week"));//当月			
			$.SetRequest("month",$.getInteger("month",new Date().getMonth()+1));
			$.SetRequest("sectioner", section);
			String type=$.getString("type","ordermoney");//查询类型	
			$.SetRequest("type", type);
			String view = $.getString("view","column");//默认显示为柱状图（同比环比）
			$.SetRequest("view",view);
			$.SetRequest("all", $.getBoolean("all",false));
			Lazy3qDaoSupport dao = $.getDao("ccclubs_system");

			if(type.equals("ordermoney")){//订单金额
				if(view.equals("column")){//环比同比
					$.SetRequest("list",this.getOrderMoney(section));
					$.SetRequest("prev",this.getOrderMoney(section.getPrev()));
					$.SetRequest("last",this.getOrderMoney(section.getLast()));	
				}else{//曲线图
					List<Map> list = new ArrayList();
					for(int i=0;i<12;i++){
						list.add(0,this.getOrderMoney(section).get(0));		
						list.get(0).put("sectioner", section);
						section = section.getPrev();
					}
					$.SetRequest("list",list);
				}
			}else if(type.equals("ordertime")){//订单时间
				if(view.equals("column")){//环比同比
					$.SetRequest("list",this.getOrderTime(section));
					$.SetRequest("prev",this.getOrderTime(section.getPrev()));
					$.SetRequest("last",this.getOrderTime(section.getLast()));
				}else{//曲线图
					List<Map> list = new ArrayList();
					for(int i=0;i<12;i++){
						list.add(0,this.getOrderTime(section).get(0));
						list.get(0).put("sectioner", section);
						section = section.getPrev();
					}
					$.SetRequest("list",list);
				}
			}else if(type.equals("ordercount")){//订单数
				if(view.equals("column")){//环比同比
					List<Map> list = dao.executeQuery(this.getOrderCountSql(), section.getEnd(),section.getStart());
					List<Map> prev = dao.executeQuery(this.getOrderCountSql(), section.getPrev().getEnd(),section.getPrev().getStart());
					List<Map> last = dao.executeQuery(this.getOrderCountSql(), section.getLast().getEnd(),section.getLast().getStart());
					$.SetRequest("list",list);
					$.SetRequest("prev",prev);
					$.SetRequest("last",last);
				}else{//曲线图
					List<Map> list = new ArrayList();
					for(int i=0;i<12;i++){
						list.add(0,dao.executeQuery(this.getOrderCountSql(), section.getEnd(),section.getStart()).get(0));
						list.get(0).put("sectioner", section);
						section = section.getPrev();
					}
					$.SetRequest("list",list);
				}
			}else if(type.equals("average")){//会员平均消费
				if(view.equals("column")){//环比同比
					$.SetRequest("list",this.getAverage(section));
					$.SetRequest("prev",this.getAverage(section.getPrev()));
					$.SetRequest("last",this.getAverage(section.getLast()));
				}else{//曲线图
					List<Map> list = new ArrayList();
					for(int i=0;i<12;i++){
						list.add(0,this.getAverage(section).get(0));
						list.get(0).put("sectioner", section);
						section = section.getPrev();
					}
					$.SetRequest("list",list);
				}
			}else if(type.equals("compose")){//收入构成
				long max = section.getEnd().getTime()/1000;//最大时间转成时间戳
				long min = section.getStart().getTime()/1000;//最小时间转成时间戳
				
				String factor="(LEAST(unix_timestamp(cso_finish_time),{max})-GREATEST(unix_timestamp(cso_start_time),{min}))/(unix_timestamp(cso_finish_time)-unix_timestamp(cso_start_time))";
				factor = factor.replace("{max}", ""+max).replace("{min}", ""+min);

				String sumPay  = " sum("+moenyType()+" * {factor}) as 'pay' ".replace("{factor}", ""+factor);
				
				Map<String,String> keyValues = 
					$.add("cso_pay_kilom", "电里程费")
					.add("cso_pay_mileage", "油里程费")
					.add("cso_pay_rent", "租金费用")
					.add("cso_pay_insure", "免责费用")
					.add("cso_pay_timeout", "超时费用")
					.add("cso_pay_driver", "陪驾费用");
				String strField = "";
				List<String> fields = new ArrayList();
				for(String key:keyValues.keySet()){
					fields.add(keyValues.get(key));
					strField+=(",sum("+key+" * {factor}) as '"+keyValues.get(key)+"' ").replace("{factor}", ""+factor);
				}
				$.SetRequest("fields",fields);

				String strSql = "select {pay}{field} from cs_order " +
					"where cso_host"+equalsHost()+" and cso_start_time<? and cso_finish_time>? and cso_pay_member not in (?) and "+orderStatusSql()+"";
				strSql = strSql.replace("{pay}", sumPay).replace("{field}", strField);
				
				List<Map> list = dao.executeQuery(strSql, section.getEnd(),section.getStart());
				$.SetRequest("compose",list.get(0));
			}else{//其它
				$.SetRequest("kind", $.getString("kind", "count"));				
				if(type.equals("from")){//订单来源分析
					$.SetRequest("list",this.getOrderStats("cso_from", section));
					$.SetRequest("caption", "订单来源");
				}
				if(type.equals("model")){//订单来源分析
					$.SetRequest("list",this.getOrderStats("cso_model", section));
					$.SetRequest("caption", "订单车型");
				}
				if(type.equals("car")){//订单车辆分析
					$.SetRequest("list",this.getOrderStats("cso_car", section));
					$.SetRequest("caption", "订单车辆");
				}				
				if(type.equals("outlets")){//订单网点分析
					$.SetRequest("list",this.getOrderStats("cso_outlets", section));
					$.SetRequest("caption", "订单网点");
				}				
				if(type.equals("area")){//订单区域分析
					$.SetRequest("list",this.getOrderStats("cso_area", section));
					$.SetRequest("caption", "订单区域");
				}				
			}			
		}catch(Exception ex){
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "index";	
	}

	/**
	 * 获取平均消费SQL
	 * @return
	 */
	private List<Map> getAverage(Section section) {
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		
		long max = section.getEnd().getTime()/1000;//最大时间转成时间戳
		long min = section.getStart().getTime()/1000;//最小时间转成时间戳
		
		String factor="(LEAST(unix_timestamp(cso_finish_time),{max})-GREATEST(unix_timestamp(cso_start_time),{min}))/(unix_timestamp(cso_finish_time)-unix_timestamp(cso_start_time))";
		factor = factor.replace("{max}", ""+max).replace("{min}", ""+min);
		
		String sumMoney  = " sum("+moenyType()+" * {factor}) as 'money' ".replace("{factor}", ""+factor);//订单金额

		String strSql = "select {money},count(distinct cso_pay_member) as 'count' from cs_order " +
			"where cso_host"+equalsHost()+" and cso_start_time<? and cso_finish_time>? and cso_pay_member not in (?) and "+orderStatusSql()+"";	
		
		strSql = strSql.replace("{money}", sumMoney);
		
		List<Map> list = dao.executeQuery(strSql, section.getEnd(),section.getStart());
		
		for(Map map:list){
			Double dMoney = (Double) map.get("money");
			Long lCount = (Long) map.get("count");	
			map.put("value",lCount==null?"NAN":(dMoney/lCount));
		}
		
		
		return list;		
	}

	/**
	 * 获取订单数SQL
	 * @return
	 */
	private String getOrderCountSql() {
		return "select count(1) as 'count' from cs_order " +
				"where cso_host"+equalsHost()+" and cso_start_time<? and cso_finish_time>? and cso_income = 1 and "+orderStatusSql()+" ";
	}	
	
	
	public String orderStatusSql(){
		return $.getBoolean("all",false)==true?" (cso_status!=7 and cso_status!=3) ":"cso_status=4";
	}
	public String moenyType(){
		return $.getBoolean("all",false)==true?"cso_pay_need":"cso_pay_real";
	}
	
	
	/**
	 * 获取订单金额指标
	 * @param section
	 * @return
	 */
	private List<Map> getOrderMoney(Section section) {
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		
		long max = section.getEnd().getTime()/1000;//最大时间转成时间戳
		long min = section.getStart().getTime()/1000;//最小时间转成时间戳
		
		String factor="(LEAST(unix_timestamp(cso_finish_time),{max})-GREATEST(unix_timestamp(cso_start_time),{min}))/(unix_timestamp(cso_finish_time)-unix_timestamp(cso_start_time))";
		factor = factor.replace("{max}", ""+max).replace("{min}", ""+min);
		
		String sumMoney  = " sum("+moenyType()+" * {factor}) as 'money' ".replace("{factor}", ""+factor);//订单金额

		String strSql = "select {money} from cs_order " +
			"where cso_host"+equalsHost()+" and cso_start_time<? and cso_finish_time>? and cso_income = 1 and "+orderStatusSql()+"";	
		
		strSql = strSql.replace("{money}", sumMoney);
		
		List<Map> list = dao.executeQuery(strSql, section.getEnd(),section.getStart());
		return list;
	}
	/**
	 * 获取订单时长指标
	 * @param section
	 * @return
	 */
	private List<Map> getOrderTime(Section section) {
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		
		long max = section.getEnd().getTime()/1000;//最大时间转成时间戳
		long min = section.getStart().getTime()/1000;//最小时间转成时间戳
		
		String factor="(LEAST(unix_timestamp(cso_finish_time),{max})-GREATEST(unix_timestamp(cso_start_time),{min}))/(unix_timestamp(cso_finish_time)-unix_timestamp(cso_start_time))";
		factor = factor.replace("{max}", ""+max).replace("{min}", ""+min);
		
		String sumTime = " sum((LEAST(unix_timestamp(cso_finish_time),{max})-GREATEST(unix_timestamp(cso_start_time),{min})))*1.0 as 'time' ".replace("{max}", ""+max).replace("{min}", ""+min);//订单时长

		String strSql = "select {time} from cs_order " +
			"where cso_host"+equalsHost()+" and cso_start_time<? and cso_finish_time>? and cso_income = 1 and "+orderStatusSql()+"";	
		
		strSql = strSql.replace("{time}", sumTime);
		
		List<Map> list = dao.executeQuery(strSql, section.getEnd(),section.getStart());
		return list;
	}


	public List<Map> getOrderStats(String sGroup,Section section){
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
				
		long max = section.getEnd().getTime()/1000;//最大时间转成时间戳
		long min = section.getStart().getTime()/1000;//最小时间转成时间戳
		
		String factor="(LEAST(unix_timestamp(cso_finish_time),{max})-GREATEST(unix_timestamp(cso_start_time),{min}))/(unix_timestamp(cso_finish_time)-unix_timestamp(cso_start_time))";
		factor = factor.replace("{max}", ""+max).replace("{min}", ""+min);		
		
		
		String sumMoney  = " sum("+moenyType()+" * {factor}) as 'money' ".replace("{factor}", ""+factor);//订单金额
		String sumTime = " sum((LEAST(unix_timestamp(cso_finish_time),{max})-GREATEST(unix_timestamp(cso_start_time),{min}))) as 'time' ".replace("{max}", ""+max).replace("{min}", ""+min);//订单时长
		
		String strSql = "select count(1) as 'count',{money},{time},"+sGroup+" from cs_order " +
			"where cso_host"+equalsHost()+" and cso_start_time<? and cso_finish_time>? and cso_income = 1 and "+orderStatusSql()+" group by "+sGroup;	
		
		strSql = strSql.replace("{money}", sumMoney).replace("{time}", sumTime);
		
		List<Map> list = dao.executeQuery(strSql, section.getEnd(),section.getStart());
		return list;
	}
	
	/**
	 * 获取城域sql限制表达式
	 * @return
	 */
	public static String equalsHost(){
		String strHost = SystemHelper.testHost($.getLong("host"));
		if($.empty(strHost))
			return " !=0 ";
		else
			return " in ("+strHost+") ";
	}
	
	
}
