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

public class FinanceAction {
	
	public String execute(){
		try{
			Section section = Section.getSection($.getInteger("year",Integer.parseInt($.date(new Date(), "yyyy"))), $.getInteger("month",new Date().getMonth()+1),$.getInteger("week"));//当月
			$.SetRequest("month",$.getInteger("month",new Date().getMonth()+1));
			$.SetRequest("sectioner", section);
			String type=$.getString("type","money");//查询类型	
			$.SetRequest("type", type);
			String view = $.getString("view","column");//默认显示为柱状图（同比环比）
			$.SetRequest("view",view);
			$.SetRequest("all", $.getBoolean("all",false));
			Lazy3qDaoSupport dao = $.getDao("ccclubs_system");

			
			if(type.equals("money")){//支付宝充值
				if(view.equals("column")){//环比同比
					$.SetRequest("list",this.getMoney(section));
					$.SetRequest("prev",this.getMoney(section.getPrev()));
					$.SetRequest("last",this.getMoney(section.getLast()));	
				}else{//曲线图
					List<Map> list = new ArrayList();
					for(int i=0;i<12;i++){
						list.add(0,this.getMoney(section).get(0));		
						list.get(0).put("sectioner", section);
						section = section.getPrev();
					}
					$.SetRequest("list",list);
				}
			}else if(type.equals("coupon")){//现金券充值
				if(view.equals("column")){//环比同比
					$.SetRequest("list",this.getCouponCharge(section));
					$.SetRequest("prev",this.getCouponCharge(section.getPrev()));
					$.SetRequest("last",this.getCouponCharge(section.getLast()));	
				}else{//曲线图
					List<Map> list = new ArrayList();
					for(int i=0;i<12;i++){
						list.add(0,this.getCouponCharge(section).get(0));		
						list.get(0).put("sectioner", section);
						section = section.getPrev();
					}
					$.SetRequest("list",list);
				}
			}else if(type.equals("package")){//套餐购买
				if(view.equals("column")){//环比同比
					$.SetRequest("list",this.getPackage(section));
					$.SetRequest("prev",this.getPackage(section.getPrev()));
					$.SetRequest("last",this.getPackage(section.getLast()));	
				}else{//曲线图
					List<Map> list = new ArrayList();
					for(int i=0;i<12;i++){
						list.add(0,this.getPackage(section).get(0));		
						list.get(0).put("sectioner", section);
						section = section.getPrev();
					}
					$.SetRequest("list",list);
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
	 * 获取套餐统计分析
	 * @param section
	 * @return
	 */
	private List<Map> getPackage(Section section) {
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		return dao.executeQuery("select sum(csr_amount)*-1 as 'count' from cs_record left join cs_record_type on cs_record.csr_record_type=cs_record_type.csrt_id " +
				"where csr_host"+equalsHost()+" and csr_amount<0 and cs_record_type.csrt_type_name like '%套餐%' and csr_group!=? and csr_add_time>? and csr_add_time<?",section.getStart(),section.getEnd());
	}

	/**
	 * 获取现金券统计分析
	 * @param section
	 * @return
	 */
	private List<Map> getCouponCharge(Section section) {
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		return dao.executeQuery("select sum(csr_amount) as 'count' from cs_record left join cs_record_type on cs_record.csr_record_type=cs_record_type.csrt_id " +
				"where csr_host"+equalsHost()+" and cs_record_type.csrt_money_type=1 and csr_amount>0 and csr_add_time>? and csr_add_time<?",section.getStart(),section.getEnd());
	}

	/**
	 * 获取充值金额统计分析
	 * @param section
	 * @return
	 */
	private List<Map> getMoney(Section section) {
		Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
		return dao.executeQuery("select sum(csar_amount)-sum(csar_refund) as 'count' from cs_alipay_record " +
				"where csar_host"+equalsHost()+" and csar_status=1 and csar_add_time>? and csar_add_time<?", section.getStart(),section.getEnd());
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
