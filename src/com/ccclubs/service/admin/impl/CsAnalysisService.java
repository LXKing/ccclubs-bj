package com.ccclubs.service.admin.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.ccclubs.entity.MemberConsuMe;
import com.ccclubs.entity.RemoteData;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsUnitPerson;
import com.ccclubs.model.SrvProperty;
import com.ccclubs.service.admin.ICsAnalysisService;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;

public class CsAnalysisService implements ICsAnalysisService{

	Lazy3qDaoSupport dao = $.getDao("ccclubs_system");

	
	@Override
	public Page<RemoteData> getReMoteDataPage(int page, int size, Map params) {
		// TODO Auto-generated method stub
		
		String addTimeStart = $.getString("addTimeStart");
		if($.empty(addTimeStart)){
			addTimeStart = getMinMonthDate(new Date());
		}
		String addTimeEnd = $.getString("addTimeEnd");
		if($.empty(addTimeEnd)){
			addTimeEnd = getMaxMonthDate(new Date());
		}
		
		String definex = " and ( csr_add_time > '"+addTimeStart +"' and csr_add_time < '"+ addTimeEnd+"' or csr_add_time is null)";
		
		if(params != null && params.get("suMobile") != null){
			definex +=" and su_mobile like '%"+params.get("suMobile").toString()+"%'";
		}
		
		SrvProperty property = SrvProperty.getSrvProperty($.add(SrvProperty.F.spFlag, "REMOTE_CASE").add(SrvProperty.F.spParent, 0));		
		List<SrvProperty> propertyList = SrvProperty.getSrvPropertyList($.add(SrvProperty.F.spParent, property.getSpId()), -1);
		StringBuilder builder = new StringBuilder();
		
		for (SrvProperty p : propertyList) {
			builder.append("SUM(case csr_case WHEN "+p.getSpId()+" then 1 ELSE 0  END) as "+p.getSpFlag$()+" , ");
		}
		
		
		//调用Service获取分页
		String sql = "SELECT su_id as suId ,su_real_name as suRealName, su_mobile as suMobile, " +
				builder.toString()+
				"SUM(case csr_type WHEN 0 then 1 ELSE 0  END) as rmOptenDoorCount ," +
				"SUM(case csr_type WHEN 1 then 1 ELSE 0  END) as rmCloseDoorCount  ," +
				"SUM(case csr_type WHEN 2 then 1 ELSE 0  END) as rmStartCount  ," +
				"SUM(case csr_type WHEN 17 then 1 ELSE 0  END) as rmBlowCount  " +
				"from srv_user LEFT JOIN cs_remote on csr_editor = su_id " +
				"where 1=1 " +
				" and  EXISTS (SELECT sue_user from srv_user_exp where sue_user = su_id and sue_is_qr_op = TRUE)  " + 
				definex +
				" GROUP BY  su_id ";
		
		return dao.executeQuery(sql, page, size, new String[] {});
	}
	
	/**
	* 获取月份起始日期
	* @param date
	* @return
	* @throws ParseException
	*/
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	public  String getMinMonthDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
	}
	
	/**
	* 获取月份最后日期
	* @param date
	* @return
	* @throws ParseException
	*/
	public  String getMaxMonthDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
	}

	@Override
	public Page<MemberConsuMe> getMemberConsuMePage(int page, int size,Map objects) {
		
		// TODO Auto-generated method stub
		objects.put("join", " join cs_unit_person p on p.csup_member = csm_id");
		StringBuffer definex = new StringBuffer();
		definex.append(" csup_status > -1 and   p.csup_member is not null");
		if($.getString("csmId") != null && !$.empty($.getString("csmId")) ){
			definex.append(" and csm_id = "+$.getString("csmId"));
		}
		
		if($.getString("csuiId") != null && !$.empty($.getString("csuiId"))){
			definex.append(" and csup_info = "+$.getString("csuiId"));
		}
		
		if($.getString("csugId") != null && !$.empty($.getString("csugId"))){
			definex.append(" and csup_group = "+$.getString("csugId"));
		}
		
		Date addTimeStart =$.getDate("addTimeStart");
		Date addTimeEnd = $.getDate("addTimeEnd");
		
		objects.put("definex", "(   "+definex.toString()+" )");	
		Page memberPage = CsMember.getCsMemberPage(page,size, objects);
		
		return this.getPage(memberPage, addTimeStart, addTimeEnd);
	}
	
	
	public Page<MemberConsuMe> getPage(Page page,Date addTimeStart,Date addTimeEnd){
		List<CsMember> memberList =page.getResult();
		List<MemberConsuMe> consumeList = new ArrayList<MemberConsuMe>();
		Map<Long , MemberConsuMe> consumeMap = new HashMap<Long, MemberConsuMe>();
		String memberIds ="";
		MemberConsuMe consuMe;
		if(memberList.isEmpty()){
			return 	new Page<MemberConsuMe>(page.getIndex(), page.getSize(), page.getCount(), consumeList); 

		}
		for (int i = 0; i < memberList.size(); i++) {
			CsMember member = memberList.get(i);
			CsUnitPerson unitPerson = CsUnitPerson.getCsUnitPerson($.add(CsUnitPerson.F.csupMember, member.getCsmId()));
			consuMe = new MemberConsuMe(member,unitPerson);
			if(i+1 == memberList.size())
				memberIds+=member.getCsmId$();
			else
				memberIds += member.getCsmId$()+",";
			consumeMap.put(member.getCsmId(), consuMe);
			consumeList.add(consuMe);
		}
		
		
		String sql = "SELECT count(*) AS orderCount, " +//订单总数
				"SUM( IF ( cso_status = 4, cso_duration, 0 ) ) AS timeCount, " +//订单时长
				"SUM( IF ( (cso_status = 3 || cso_status = 4) && (cso_pay_member = cso_use_member), cso_pay_money, 0 ) ) AS payMoney, " +//余额消费
				"sum( IF ( (cso_status = 3 || cso_status = 4) && (cso_pay_member = cso_use_member), cso_pay_coupon, 0 )) AS coupon ," +//现金券消费
				" m.csm_id" +//会员ID
				" FROM cs_order o,cs_member m,cs_unit_person p , cs_unit_info i, cs_unit_group g " +
				"WHERE " +
				"o.cso_use_member = m.csm_id " +
				"and m.csm_id = p.csup_member " +
				"and i.csui_id = p.csup_info " +
				"and g.csug_id = p.csup_group " +
				"AND cso_status > - 1 " +
				" and o.cso_use_member in  ("+memberIds+") " + 
				(addTimeStart != null ? " and o.cso_add_time > '"+$.date(addTimeStart, "yyyy-MM-dd HH:mm:ss")+"'" : " ")	+
				(addTimeEnd != null ? " and o.cso_add_time < '"+$.date(addTimeEnd, "yyyy-MM-dd HH:mm:ss")+"'" : "")+
				" group by o.cso_use_member";
		
		List<Map> resultMap = dao.executeQuery(sql);
		for (Map map : resultMap) {
			Long csmId = (Long) map.get("csm_id");
			MemberConsuMe memberConsuMe = consumeMap.get(csmId);
			Long orderCount = (Long) map.get("orderCount");//订单总数
			memberConsuMe.setOrderCount(orderCount == null ? 0 : orderCount.intValue());
			Double timeCount = (Double) map.get("timeCount");//订单时长
			memberConsuMe.setOrderTime($(timeCount));
			Double balanceConsuMeCount = (Double) map.get("payMoney");//余额消费
			memberConsuMe.setBalanceConsuMeCount($(balanceConsuMeCount));
			Double couponConsumeCount = (Double) map.get("coupon");//现金券消费
			memberConsuMe.setCouponConsuMeCount($(couponConsumeCount));
		}
		
		
		String recordSql = "SELECT " +
				" sum(if(r.csr_record_subject = 4 || r.csr_record_subject = 37,csr_amount,0)) as rechargeOnline," +
				" sum(if(r.csr_record_subject = 13,csr_amount,0)) as couponCount," +
				" sum(if(r.csr_record_subject = 16,csr_amount,0)) as backConsuMeCouponCount," +
				" sum(if(r.csr_record_subject = 15,csr_amount,0)) as backRechargeCoupon," +
				" r.csr_member " +
				" from cs_record r ,cs_record_subject s " +
				" where " +
				" r.csr_record_subject = s.csrs_id and " +
				" csr_member in ("+memberIds+") " +
				(addTimeStart != null ? " and r.csr_add_time > '"+$.date(addTimeStart, "yyyy-MM-dd HH:mm:ss")+"'" : " ")	+
				(addTimeEnd != null ? " and r.csr_add_time < '"+$.date(addTimeEnd, "yyyy-MM-dd HH:mm:ss")+"'" : "")+
				" GROUP BY r.csr_member";
		
		List<Map> recordResult = dao.executeQuery(recordSql);
		for (Map map : recordResult) {
			Long csmId = (Long) map.get("csr_member");
			MemberConsuMe memberConsuMe = consumeMap.get(csmId);
			
			Double rechargeOnline = (Double) map.get("rechargeOnline"); //用户在线充值
			memberConsuMe.setBalanceRechargeCount(rechargeOnline);
			
			Double couponCount = (Double) map.get("couponCount");//购买现金券
			memberConsuMe.setCouponCount(Math.abs($(couponCount)));
			
			Double backConsuMeCouponCount = (Double)map.get("backConsuMeCouponCount");//现金券后台扣款
			memberConsuMe.setBackConsuMeCouponCount(backConsuMeCouponCount);
			
			Double backRechargeCoupon = (Double)map.get("backRechargeCoupon");//现金券后台充值
			memberConsuMe.setBackRechargeCoupon(backRechargeCoupon);
			
		}
		
		
		String artifReqcordSQL = "SELECT " +
				" SUM(IF (  s.csrs_is_user_in = 1 , r.csar_count, 	0 )) AS backRecharge," +//后台充值
				" SUM( IF ( r.csar_money_type = 0 && s.csrs_is_user_in = 1, r.csar_count, 	0 ) ) AS balanceRecharge, " +//余额后台充值
				"sum( IF ( r.csar_money_type = 0 && s.csrs_is_user_out = 1, r.csar_count, 0 ) ) AS backConsuMeCount, " +//余额后台扣款
				" sum( IF ( r.csar_money_type = 1 && s.csrs_is_user_in = 1, r.csar_count, 0 ) ) AS backRechargeCoupon, " +//现在券后台充值
				" SUM(	IF ( r.csar_money_type = 1 && s.csrs_is_user_out = 1, r.csar_count, 0 ) ) AS backConsuMeCouponCount, " +//后台现金券消费
				" r.csar_member " +
				" FROM " +
				" cs_artif_record r, cs_record_subject s " +
				" WHERE " +
				" r.csar_record_subject = s.csrs_id AND " +
				" r.csar_status = 1 and " +
				" r.csar_member in ("+memberIds+")" +
				(addTimeStart != null ? " and r.csar_add_time > '"+$.date(addTimeStart, "yyyy-MM-dd HH:mm:ss")+"'" : " ")	+
				(addTimeEnd != null ? " and r.csar_add_time < '"+$.date(addTimeEnd, "yyyy-MM-dd HH:mm:ss")+"'" : "")+
				" GROUP BY r.csar_member ;";
		
		
		List<Map> artifReqcordResult = dao.executeQuery(artifReqcordSQL);
		for (Map map : artifReqcordResult) {
			Long csmId = (Long) map.get("csar_member");
			MemberConsuMe memberConsuMe = consumeMap.get(csmId);
			
			Double rechargeOnline = (Double) map.get("backRecharge"); //用户后台充值(现金券+余额)
			memberConsuMe.setRechargeCount($(rechargeOnline)+$(memberConsuMe.getBalanceRechargeCount()));
			
			//余额后台充值
			Double balanceRecharge = (Double) map.get("balanceRecharge");
			memberConsuMe.setBackgroundCount($(balanceRecharge));
			
			//余额后台扣款
			Double backConsuMeCount = (Double) map.get("backConsuMeCount");
			memberConsuMe.setBackConsuMeCount($(backConsuMeCount));
			
			//现在券后台充值
			Double backRechargeCoupon = (Double) map.get("backRechargeCoupon");
			memberConsuMe.setBackRechargeCoupon($(backRechargeCoupon+memberConsuMe.getBackRechargeCoupon()));
			
			//后台现金券消费backConsuMeCouponCount
			Double backConsuMeCouponCount = (Double) map.get("backConsuMeCouponCount");
			memberConsuMe.setBackConsuMeCouponCount($(backConsuMeCouponCount+memberConsuMe.getBackConsuMeCouponCount()));
		}
		String coinSQL = "SELECT " +
				" COUNT(*) as coin_count," +
				" SUM(if(csc_remain>0 && csc_status = 1 && csc_end > NOW() ,csc_remain,0)) as remain," +
				" csc_member " +
				" from " +
				" cs_coin " +
				" WHERE " +
				" csc_member IN ("+memberIds+")" +
				(addTimeStart != null ? " and  csc_add_time > '"+$.date(addTimeStart, "yyyy-MM-dd HH:mm:ss")+"'" : " ")	+
				(addTimeEnd != null ? " and  csc_add_time < '"+$.date(addTimeEnd, "yyyy-MM-dd HH:mm:ss")+"'" : "")+
				" GROUP BY csc_member ;";
		
		List<Map> coinResult = dao.executeQuery(coinSQL);
		for (Map map : coinResult) {
			Long csmId = Long.valueOf(map.get("csc_member").toString());
			MemberConsuMe memberConsuMe = consumeMap.get(csmId);
			//赠送红包总数
			Long giveCount = (Long) map.get("coin_count");
			memberConsuMe.setGiveCoinCount(giveCount == null ? 0 :giveCount.intValue());
			
			//红包余额
			Double remain = (Double) map.get("remain");
			memberConsuMe.setCoinCount($(remain));
			
		}
		
		//红包消费总计
		String useSQL ="SELECT SUM(csur_count) AS coinConsuMeCount ," +
				" cso_use_member FROM " +
				" cs_use_record LEFT JOIN cs_order ON cso_id = csur_order WHERE cso_status IN (3,4) AND " +
				" cso_use_member IN ("+memberIds+")" +
				(addTimeStart != null ? " and  csur_add_time > '"+$.date(addTimeStart, "yyyy-MM-dd HH:mm:ss")+"'" : " ")	+
				(addTimeEnd != null ? " and  csur_add_time < '"+$.date(addTimeEnd, "yyyy-MM-dd HH:mm:ss")+"'" : "")+
				" GROUP BY cso_use_member;";
		List<Map> coinUseResult = dao.executeQuery(useSQL);
		for (Map map : coinUseResult) {
			Long csmId = Long.valueOf(map.get("cso_use_member").toString());
			MemberConsuMe memberConsuMe = consumeMap.get(csmId);
			//红包消费总计
			Double coinConsuMeCount = (Double) map.get("coinConsuMeCount"); 
			memberConsuMe.setCoinConsuMeCount($(coinConsuMeCount));
		}
		
		for (int i = 0; i < memberList.size(); i++) {
			CsMember member = memberList.get(i);
			MemberConsuMe memberConsuMe = consumeMap.get(member.getCsmId());
			memberConsuMe.setConsumeCount($(memberConsuMe.getBalanceConsuMeCount()+memberConsuMe.getBackConsuMeCount() + memberConsuMe.getCouponConsuMeCount() + memberConsuMe.getBackConsuMeCouponCount() + memberConsuMe.getCoinConsuMeCount()));
			memberConsuMe.setOrderConsume($(memberConsuMe.getBalanceConsuMeCount() + memberConsuMe.getCouponConsuMeCount() + memberConsuMe.getCoinConsuMeCount()));
		}
		
		Page<MemberConsuMe> consumePage = new Page<MemberConsuMe>(page.getIndex(), page.getSize(), page.getCount(), consumeList); 
		return consumePage;
	}
	public Double $(Double value){
		if(value==null)return 0d;
		return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}
