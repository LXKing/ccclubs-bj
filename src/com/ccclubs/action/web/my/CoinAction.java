package com.ccclubs.action.web.my;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.ccclubs.config.Constant;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsRecord;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ICsCoinService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;

public class CoinAction {
	ICsMemberService csMemberService;
	ICsCoinService csCoinService;
	ICommonMoneyService commonMoneyService;

	/**
	 * 个人中心 消费记录页面 
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String execute() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();

		CsMember member = csMemberService.getCsMemberById(SystemHelper.getLoginId());
		$.SetRequest("member", member);

		try{
			$.SetRequest("page", doQuery());
		}catch (Exception e) {
			e.printStackTrace();
		}
		List<SrvHost> hosts=SrvHost.getSrvHostList($.add(SrvHost.F.shState, 1), -1);
		List list = new ArrayList();
		for(SrvHost srvHost:hosts){
			Map map = new HashMap();
			Double coin=commonMoneyService.getCoin(srvHost.getShId(), SystemHelper.getLoginId(), Calendar.getInstance().getTime());
			map.put("cityName", srvHost.getShName());
			map.put("cityId", srvHost.getShId());
			map.put("coin",coin );
			list.add(map);
		}
		$.setRequest("coins", list);
		return "coin";
	}
	
	Page doQuery(){
		// 帐户类型 type = 0：充值帐户 ，1：现金券帐户
		int type = $.getInteger("type", 0);
		$.getString("type");
		$.SetRequest("type", type);
		
		int useType = 0, addType = 0;
		if(type == 0){
			useType = 1;
			addType = 1;
		}
		if(type == 1) addType = 1;	//收入明细
		if(type == 2) useType = 1;	//支出明细
		int current = $.getInteger("page", 0);
		int size  = Constant.LOADE_COUNT * 2;
		
		//获取分页总数
		List<Map> countData = $.getDao("ccclubs_system").executeQuery(genQueryCountSQL(useType, addType, SystemHelper.getLoginId()));
		if(!CollectionUtils.isEmpty(countData)){
			Long count= (Long) countData.get(0).get("total");
			List<Map> data = $.getDao("ccclubs_system").executeQuery(genQuerySQL(useType, addType, SystemHelper.getLoginId(), size, (int)Page.test(current, size, count)));
			Page page  = new Page(current, size, count, data);
			return page;
		}
		
		return null;
	}
	
	/**
	 * 生成统计SQL
	 * @param useType
	 * @param addType
	 * @return
	 */
	String genQueryCountSQL(int useType, int addType, Object cscMember){
		StringBuffer sql = new StringBuffer("SELECT");
		
		if(addType == 1){
			sql.append("(SELECT count(0) FROM cs_coin WHERE csc_member="+cscMember+" AND csc_status = 1 AND csc_count = csc_remain)");
		}
		
		if(addType == 1 && useType == 1){
			sql.append("+");
		}
		
		if(useType == 1){
			sql.append("(SELECT count(0) FROM cs_coin cc JOIN cs_use_record cur ON cc.csc_id = cur.csur_coin WHERE cc.csc_member="+cscMember+" AND cc.csc_status = 1)");
		}
		
		sql.append("as total FROM DUAL");
		
		return sql.toString();
	}
	
	/**
	 * 生成统计SQL
	 * @param useType
	 * @param addType
	 * @return
	 */
	String genQuerySQL(int useType, int addType, Object cscMember, int limit, int offset){
		StringBuffer sql = new StringBuffer("SELECT csc_id, csc_host, csc_count, csc_remain, csc_end, csc_add_time, csc_remark, type FROM (");
		
		if(addType == 1){
			sql.append("SELECT csc_id,csc_host,csc_count,csc_remain,csc_end,csc_add_time,csc_remark,1 AS type FROM cs_coin WHERE csc_member="+cscMember+" AND csc_status = 1 AND csc_count = csc_remain");
		}
		
		if(addType == 1 && useType == 1){
			sql.append(" union all ");
		}
		
		if(useType == 1){
			sql.append("SELECT cc.csc_id,cc.csc_host,cur.csur_count AS csc_count,csc_remain,csc_end,csc_add_time,'' AS csc_remark,2 AS type FROM cs_coin cc JOIN cs_use_record cur ON cc.csc_id = cur.csur_coin WHERE cc.csc_member ="+cscMember+" AND cc.csc_status = 1");
		}
		
		sql.append(") AS coin ORDER BY csc_add_time DESC LIMIT "+limit+" OFFSET "+offset);
		
		return sql.toString();
	}

	/**
	 * 加载更多
	 * 
	 * @return
	 */
	public String expand() {
		if (!SystemHelper.isLogin())
			return SystemHelper.gotoLogin();

		Map<String, Object> params = new HashMap<String, Object>();
		params.put(CsRecord.F.csrMember, SystemHelper.getLoginId());
		// 帐户类型 type = 0：充值帐户 ，1：现金券帐户
		int type = $.getInteger("type", 0);
		$.SetRequest("type", type);
		int useType = 0, addType = 0;
		if(type == 0){
			useType = 1;
			addType = 1;
		}
		
		if(type == 1) addType = 1;	//收入明细
		if(type == 2) useType = 1;	//支出明细
		
		params.put("cscMember", SystemHelper.getLoginId());
		try{
			$.SetRequest("page", doQuery());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "expand";
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

	public ICsCoinService getCsCoinService() {
		return csCoinService;
	}

	public void setCsCoinService(ICsCoinService csCoinService) {
		this.csCoinService = csCoinService;
	}

	public ICommonMoneyService getCommonMoneyService() {
		return commonMoneyService;
	}

	public void setCommonMoneyService(ICommonMoneyService commonMoneyService) {
		this.commonMoneyService = commonMoneyService;
	}

}
