package com.ccclubs.action.admin.operate.quota;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.helper.LoginHelper;
import com.ccclubs.helper.SystemHelper;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

public class MemberAction {
	
	
	/**
	 * 会员指标统计首页入口
	 * @return
	 */
	public String execute(){
		try{
			
			Section section = Section.getSection($.getInteger("year",Integer.parseInt($.date(new Date(), "yyyy"))), $.getInteger("month",new Date().getMonth()+1),$.getInteger("week"));//当月
			$.SetRequest("month",$.getInteger("month",new Date().getMonth()+1));
			$.SetRequest("sectioner", section);
			String type=$.getString("type","newly");//查询类型	
			$.SetRequest("type", type);
			String view = $.getString("view","column");//默认显示为柱状图（同比环比）
			$.SetRequest("view",view);
			Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
			if(type.equals("newly")){//新增会员指标
				if(view.equals("column")){//环比同比
					List<Map> list = dao.executeQuery("select count(1) as 'count' from cs_member " +
							"where csm_host"+equalsHost()+" and  csm_add_time>? and csm_add_time<?", section.getStart(),section.getEnd());
					List<Map> prev = dao.executeQuery("select count(1) as 'count' from cs_member " +
							"where csm_host"+equalsHost()+" and  csm_add_time>? and csm_add_time<?", section.getPrev().getStart(),section.getPrev().getEnd());
					List<Map> last = dao.executeQuery("select count(1) as 'count' from cs_member " +
							"where csm_host"+equalsHost()+" and  csm_add_time>? and csm_add_time<?", section.getLast().getStart(),section.getLast().getEnd());
					$.SetRequest("list",list);
					$.SetRequest("prev",prev);
					$.SetRequest("last",last);
				}else{//曲线图
					List<Map> list = new ArrayList();
					for(int i=0;i<12;i++){
						list.add(0,dao.executeQuery("select count(1) as 'count' from cs_member " +
								"where csm_host"+equalsHost()+" and  csm_add_time>? and csm_add_time<?", section.getStart(),section.getEnd()).get(0));		
						list.get(0).put("sectioner", section);
						section = section.getPrev();
					}
					$.SetRequest("list",list);
				}
			}else if(type.equals("charge")){//新增充值指标
				if(view.equals("column")){//环比同比			
					List<Map> list = dao.executeQuery("select count(1) as 'count' from cs_member " +
							"where csm_host"+equalsHost()+" and  csm_first_recharge>? and csm_first_recharge<?", section.getStart(),section.getEnd());
					List<Map> prev = dao.executeQuery("select count(1) as 'count' from cs_member " +
							"where csm_host"+equalsHost()+" and  csm_first_recharge>? and csm_first_recharge<?", section.getPrev().getStart(),section.getPrev().getEnd());
					List<Map> last = dao.executeQuery("select count(1) as 'count' from cs_member " +
							"where csm_host"+equalsHost()+" and  csm_first_recharge>? and csm_first_recharge<?", section.getLast().getStart(),section.getLast().getEnd());
					$.SetRequest("list",list);
					$.SetRequest("prev",prev);
					$.SetRequest("last",last);
				}else{//曲线图
					List<Map> list = new ArrayList();
					for(int i=0;i<12;i++){
						list.add(0,dao.executeQuery("select count(1) as 'count' from cs_member " +
								"where csm_host"+equalsHost()+" and  csm_first_recharge>? and csm_first_recharge<?", section.getStart(),section.getEnd()).get(0));		
						list.get(0).put("sectioner", section);
						section = section.getPrev();
					}
					$.SetRequest("list",list);
				}
			}else if(type.equals("refund")){//会员退款分析
				if(view.equals("column")){//环比同比
					List<Map> list = dao.executeQuery("select count(1) as 'count' from cs_refund " +
							"where csr_host"+equalsHost()+" and  csr_status=2 and csr_back_time>? and csr_back_time<?", section.getStart(),section.getEnd());
					List<Map> prev = dao.executeQuery("select count(1) as 'count' from cs_refund " +
							"where csr_host"+equalsHost()+" and   csr_status=2 and csr_back_time>? and csr_back_time<?", section.getPrev().getStart(),section.getPrev().getEnd());
					List<Map> last = dao.executeQuery("select count(1) as 'count' from cs_refund " +
							"where csr_host"+equalsHost()+" and  csr_status=2 and csr_back_time>? and csr_back_time<?", section.getLast().getStart(),section.getLast().getEnd());
					$.SetRequest("list",list);
					$.SetRequest("prev",prev);
					$.SetRequest("last",last);
				}else{//曲线图
					List<Map> list = new ArrayList();
					for(int i=0;i<12;i++){
						list.add(0,dao.executeQuery("select count(1) as 'count' from cs_refund " +
								"where csr_host"+equalsHost()+" and  csr_status=2 and csr_back_time>? and csr_back_time<?", section.getStart(),section.getEnd()).get(0));		
						list.get(0).put("sectioner", section);
						section = section.getPrev();
					}
					$.SetRequest("list",list);
				}
			}else if(type.equals("change")){//会员转化率(计算时间段内【【【【【【注册并且充值】】】】】】】】】】】的会员个数)
				if(view.equals("column")){//环比同比
					{
						List<Map> list = dao.executeQuery("select count(1) as 'count' from cs_member " +
								"where csm_host"+equalsHost()+" and  csm_first_recharge>? and csm_first_recharge<? and csm_add_time>? and csm_add_time<?", section.getStart(),section.getEnd(), section.getStart(),section.getEnd());
						List<Map> prev = dao.executeQuery("select count(1) as 'count' from cs_member " +
								"where csm_host"+equalsHost()+" and  csm_first_recharge>? and csm_first_recharge<? and csm_add_time>? and csm_add_time<?", section.getPrev().getStart(),section.getPrev().getEnd(), section.getPrev().getStart(),section.getPrev().getEnd());
						List<Map> last = dao.executeQuery("select count(1) as 'count' from cs_member " +
								"where csm_host"+equalsHost()+" and  csm_first_recharge>? and csm_first_recharge<? and csm_add_time>? and csm_add_time<?", section.getLast().getStart(),section.getLast().getEnd(), section.getLast().getStart(),section.getLast().getEnd());
						$.SetRequest("charge",$.add("list", list).add("prev", prev).add("last", last));
					}
				}else{//曲线图
					List<Map> list = new ArrayList();
					for(int i=0;i<12;i++){	
						Long chargeCount = (Long) dao.executeQuery("select count(1) as 'count' from cs_member " +
								"where csm_host"+equalsHost()+" and  csm_first_recharge>? and csm_first_recharge<? and csm_add_time>? and csm_add_time<?", section.getStart(),section.getEnd(),section.getStart(),section.getEnd()).get(0).get("count");
						list.add(0,$.add("value",chargeCount));
						list.get(0).put("sectioner", section);
						section = section.getPrev();
					}
					$.SetRequest("list",list);
				}
			}else if(type.equals("from")){//注册来源分析
				List<Map> list = dao.executeQuery("select count(1) as 'count',csm_from from cs_member " +
						"where csm_host"+equalsHost()+" and  csm_add_time>? and csm_add_time<?  group by csm_from ", section.getStart(),section.getEnd());
				Map exists = new HashMap();
				for(Map map:list)
					exists.put(map.get("csm_from")+"", true);
				for(String sFrom:new String[]{"0","1","2","3","4"}){
					if(exists.get(sFrom)==null)
						list.add($.add("csm_from", Integer.parseInt(sFrom)).add("count", 0));
				}
				$.SetRequest("list",list);
			}else if(type.equals("outlets")){//注册网点分析
				List<Map> list = dao.executeQuery("select count(1) as 'count',csm_outlets from cs_member " +
						"where csm_host"+equalsHost()+" and  csm_add_time>? and csm_add_time<?  group by csm_outlets ", section.getStart(),section.getEnd());
				$.SetRequest("list",list);
			}
			
				
		}catch(Exception ex){
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "index";	
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
