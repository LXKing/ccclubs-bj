package com.ccclubs.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lazy3q.lang.api;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;

/**
 * 2014春节出错订单恢复
 * @author uiu
 *
 */
public class NewYearRollback {
	
	public static void main(String...string){
		System.out.println(rollback(10045639l));
	}
	
	static Lazy3qDaoSupport dao = $.getDao("ccclubs_system");
	public @api static String rollback(Long orderid){
		List<Map> orders = dao.executeQuery("select * from cs_order where cso_id=? and cso_status=4", orderid);
		if(orders.isEmpty())return "订单不存在";
		Map order = orders.get(0);
		if(!dao.executeQuery("select * from cs_order where cso_pay_member=? and cso_id>?", order.get("cso_pay_member"),order.get("cso_id")).isEmpty()){
			return "后面有订单，放弃结算"+orderid;
		}
		Map member = dao.executeQuery("select * from cs_member where csm_id = ?", order.get("cso_pay_member")).get(0);
		List<Map> records = dao.executeQuery("select * from cs_record where csr_order=?", orderid);
		$.trace(
			orderid,
			member.get("csm_name"),
			"实付金额"+order.get("csoPayReal"),
			"保证金支付"+order.get("csoPayMoney"),
			"现金券支付"+order.get("csoPayCoupon"),
			"红包支付"+order.get("csoPayCoin")
		);
		
		List<Integer> lines = new ArrayList();
		
		//把订单设置为不要发短信
		lines.add(dao.executeUpdate("update cs_order set cso_mask=2 where cso_id=?", orderid));
		
		
		
		Double csoPayMoney=(Double)order.get("csoPayMoney");
		Double csoPayCoupon=(Double)order.get("csoPayCoupon");	
		Double csoPayCoin = (Double)order.get("csoPayCoin");
		Long csmId=(Long)member.get("csmId");
		$.trace(orderid,csmId,csoPayMoney,csoPayCoupon);
		Map csIntegralRecord = dao.executeQuery("select * from cs_integral_record where csr_type='消费得积分' and csr_relate=? and csr_member=?", orderid,csmId).get(0);
		Double integral = (Double) csIntegralRecord.get("csr_amount");
		//把套餐重置回来
		lines.add(dao.executeUpdate("update cs_gift set csg_remain=csg_count,csg_status=1 where csg_member=? and (csg_sys_pack=9 or csg_sys_pack=10 or csg_sys_pack=11)", csmId));
		//把关联的红包恢复
		List<Map> userecords = dao.executeQuery("select * from cs_use_record where csur_order=?", orderid);
		for(Map map:userecords){
			if(map.get("csur_free_hour")!=null)
				lines.add(dao.executeUpdate("update cs_free_hour set csfh_remain=csfh_remain+? where csfh_id=?", map.get("csur_count"),map.get("csur_free_hour")));
			if(map.get("csur_coin")!=null)
				lines.add(dao.executeUpdate("update cs_coin set csc_remain=csc_remain+? where csc_id=?", map.get("csur_count"),map.get("csur_coin")));
		}
		//把套餐使用记录删除
		lines.add(dao.executeUpdate("delete from cs_use_record where csur_order=?", orderid));	
		//把余额、现金券、积分改回来  csmMoney;//余额 [非空]     csmCoupon;//现金券 [非空]      csmIntegral;//积分 [非空]   
		lines.add(dao.executeUpdate("update cs_member set csm_money=csm_money+?,csm_coupon=csm_coupon+?,csm_integral=csm_integral-? where csm_id=?",csoPayMoney,csoPayCoupon,integral,csmId));
		//把积分记录删掉
		lines.add(dao.executeUpdate("delete from cs_integral_record where csr_id=?", csIntegralRecord.get("csr_id")));
		//把消费记录删掉
		lines.add(dao.executeUpdate("delete from cs_record where csr_order=?", orderid));
		//把订单状态改回到已还车2
		lines.add(dao.executeUpdate("update cs_order set cso_status=2,cso_mask=2 where cso_id=?", orderid));
		return "回滚成功:"+$.json(lines);
	}
	
}
