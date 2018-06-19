package com.ccclubs.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ccclubs.helper.UtilHelper;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Lazy3qLogger;

public class DataAnalyse {

	/**
	 * 注册网点分析
	 */
	private synchronized static boolean synOutlets() {
		if(synOutlets==true)
			return false;
		synOutlets = true;
		try{
			Lazy3qDaoSupport dao = $.getDao("ccclubs_system");		
			List<Map> ids = dao.executeQuery("select csm_id from cs_member where csm_outlets is null");
			for(Map mid:ids){
				Long id = (Long) mid.get("csm_id");
				//查找第一次订单
				List<Map> list = dao.executeQuery("select cso_outlets from cs_order where cso_use_member=? order by cso_add_time asc limit 1", id);
				if(list.isEmpty())
					continue;
				dao.executeUpdate("update cs_member set csm_outlets=? where csm_id=?", list.get(0).get("cso_outlets"),id);
			}	
		}catch(Exception ex){
			ex.printStackTrace();
		}
		synOutlets = false;
		return true;
	}
	static boolean synOutlets = false;
	

	/**
	 * 检查新增会员充值
	 * 2014-01-20
	 */
	public synchronized static boolean synNewCharge(){
		if(synNewCharge==true)
			return false;
		synNewCharge = true;
		try{
			Lazy3qDaoSupport dao = $.getDao("ccclubs_system");		
			List<Map> ids = dao.executeQuery("select csm_id from cs_member where csm_first_recharge is null");
			for(Map mid:ids){
				Long id = (Long) mid.get("csm_id");
				//查找第一次充值记录
				List<Map> list = dao.executeQuery("select csr_add_time from cs_record where csr_member=? and csr_money_type=0 and csr_type like '%充值%' order by csr_add_time asc limit 1", id);
				if(list.isEmpty())
					continue;
				dao.executeUpdate("update cs_member set csm_first_recharge=? where csm_id=?", list.get(0).get("csr_add_time"),id);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		synNewCharge = false;
		return true;
	}
	static boolean synNewCharge = false;
	
	
	/**
	 * 检查新增会员退款
	 */
	public synchronized static boolean synNewRefund(){
		if(synNewRefund==true)
			return false;
		synNewRefund = true;
		try{
			try{
				Lazy3qDaoSupport dao = $.getDao("ccclubs_system");		
				List<Map> ids = dao.executeQuery("select csm_id from cs_member");
				for(Map mid:ids){
					Long id = (Long) mid.get("csm_id");
					//查找第一次充值记录
					List<Map> list = dao.executeQuery("select csr_add_time from cs_refund where csr_status<3 and csr_member=?", id);
					if(list.isEmpty())
						continue;
					dao.executeUpdate("update cs_member set csm_refund_time=? where csm_id=?", list.get(0).get("cso_start_time"),id);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		synNewRefund = false;
		return true;
	}	
	static boolean synNewRefund=false;
	
	
	/**
	 * 检查新增会员退款
	 */
	public synchronized static boolean synLastUse(){
		if(synLastUse==true)
			return false;
		synLastUse = true;
		try{
			Lazy3qDaoSupport dao = $.getDao("ccclubs_system");		
			List<Map> ids = dao.executeQuery("select csm_id from cs_member");
			for(Map mid:ids){
				Long id = (Long) mid.get("csm_id");
				//查找第一次订单
				List<Map> list = dao.executeQuery("select cso_start_time from cs_order where cso_pay_member=? order by cso_id desc limit 1", id);
				if(list.isEmpty())
					continue;
				dao.executeUpdate("update cs_member set csm_last_use=? where csm_id=?", list.get(0).get("cso_start_time"),id);
			}	
		}catch(Exception ex){
			ex.printStackTrace();
		}
		synLastUse = false;
		return true;
	}
	static boolean synLastUse = false;
	
	
	/**
	 * 分析会员生日
	 */
	public static void synBirthday(){
		try{
			Lazy3qDaoSupport dao = $.getDao("ccclubs_system");		
			List<Map> ids = dao.executeQuery("select csmi_id from cs_member_info where length(csmi_certify_num)>0 or length(csmi_driver_num)>0");
			for(Map mid:ids){
				Long id = (Long) mid.get("csmi_id");
				//查找第一次订单
				List<Map> list = dao.executeQuery("select csmi_certify_num,csmi_driver_num from cs_member_info where csmi_id=?", id);
				if(list.isEmpty())
					continue;
				String csmi_certify_num = (String) list.get(0).get("csmi_certify_num");
				String csmi_driver_num = (String) list.get(0).get("csmi_driver_num");
				String idcard = $.or(csmi_driver_num, csmi_certify_num);
				if(idcard.length()<15)
					continue;
				dao.executeUpdate("update cs_member_info set csmi_birthday=? where csmi_id=?", UtilHelper.getBirthdayFromIdCard(idcard),id);
			}	
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	
	public static void updateMemberByJoinSql(String resSql){
		try{
			Lazy3qDaoSupport dao = $.getDao("ccclubs_system");		
			Lazy3qLogger.bShowSql=true;
			List<Map> ids = dao.executeQuery(resSql);
			for(Map<String,Object> map:ids){			
				Set<String> keyset = map.keySet();
				Object[]params=new Object[keyset.size()+1];
				String strUpdate = "";
				int index = 0;
				for(String key:keyset){
					if(!$.empty(strUpdate))
						strUpdate+=",";
					strUpdate+=key+"=?";
					params[index++]=map.get(key);
				}
				params[index++]=map.get("csm_id");
				if(map.get("csm_id")==null)
					continue;
				try{
					dao.executeUpdate("update cs_member set "+strUpdate+" where csm_id=?", params);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			$.trace(ids.size());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	/**
	 * [累计订单][首次用车][最后使用][累计用车]
	 */
	public static void synMemberByOrder(){
		updateMemberByJoinSql("select a.csm_id,count(b.cso_id) as csm_all_order_s,min(b.cso_add_time) as csm_first_use,max(b.cso_add_time) as csm_last_use,sum(unix_timestamp(b.cso_finish_time)-unix_timestamp(b.cso_start_time))/3600 as csm_all_use_time from cs_member a inner join cs_order b on b.cso_use_member=a.csm_id group by b.cso_use_member");
	}
		
	
	/**
	 * [首次充值][累计充值]
	 */
	public static void synMemberByRecord(){
		updateMemberByJoinSql("select a.csm_id,sum(b.csr_amount) as csm_all_recharge,min(b.csr_add_time) as csm_first_recharge from cs_member a right join cs_record b on b.csr_member=a.csm_id where b.csr_type like '%充值%' group by b.csr_member");
	}
	
	/**
	 * [最后退款]
	 */
	public static void synMemberRefoundByRecord(){
		updateMemberByJoinSql("select a.csm_id,max(b.csr_add_time) as csm_refund_time from cs_member a right join cs_record b on b.csr_member=a.csm_id where b.csr_type like '%退款%' group by b.csr_member");
	}
	
	
	/**
	 * [事故次数]
	 */
	public static void synMemberTrouble(){
		updateMemberByJoinSql("select a.csm_id,count(b.cst_id) as csm_all_trouble_s from cs_member a right join cs_trouble b on b.cst_member=a.csm_id group by b.cst_member");
	}
	
	
	/**
	 * [违章次数]
	 */
	public static void synMemberViolat(){
		updateMemberByJoinSql("select a.csm_id,count(b.csv_id) as csm_all_violat_s from cs_member a right join cs_violat b on b.csv_member=a.csm_id group by b.csv_member");
	}
	
	
	/**
	 * [未处理违章次数]
	 */
	public static void synMemberUnViolat(){
		updateMemberByJoinSql("select a.csm_id,count(b.csv_id) as csm_un_violat_s from cs_member a right join cs_violat b on b.csv_member=a.csm_id where b.csv_status<>2 group by b.csv_member");
	}
			
	
	public static void synMemberAll(){
		synMemberByOrder();
		synMemberByRecord();
		synMemberRefoundByRecord();
		synMemberTrouble();
		synMemberViolat();
		synMemberUnViolat();
	}
	
	
	public static void main(String...strings){
		synMemberAll();
	}
	
	
	
}
