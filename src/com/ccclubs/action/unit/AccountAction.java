package com.ccclubs.action.unit;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberShip;
import com.ccclubs.model.CsUnitFee;
import com.ccclubs.model.CsUnitUser;
import com.ccclubs.service.admin.ICsMemberShipService;
import com.lazy3q.lang.api;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;
import com.opensymphony.xwork2.ActionContext;

public class AccountAction {
	
	ICsMemberShipService csMemberShipService;
	
	public String execute(){
		try{			
			Page<CsMemberShip> page = csMemberShipService.getCsMemberShipPage(
				$.getInteger("page",0), 16, $.add("definex", "csms_payer in ("+UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiMember().replace("#", "")+")")
			);
			$.SetRequest("page", page);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "index";
	}
	
	/**
	 * 添加子帐号
	 * @param mobile
	 * @param name
	 * @return
	 */
	public static @api Map add(Long pid,String mobile,String name){
		try{
			//根据手机号码和姓名获取会员
			CsMember useMember = CsMember.Get($.add(CsMember.F.csmMobile, mobile).add(CsMember.F.csmName, "%"+name+"%"));
			if(useMember==null)
				return $.add("message", "会员帐号不存在");
			//根据会员获取与当前企业的关系，如果存在，则提示不能添加
			CsUnitUser login = UnitLoginHelper.getLogin();
			if(UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiMember().indexOf("#"+pid+"#")==-1)
				return $.add("message", "无权添加到此帐号").add("success", false);
			CsMember payMember = CsMember.get(pid);
			CsMemberShip ship = CsMemberShip.getCsMemberShip($.add("csmsPayer", payMember.getCsmId()).add("csmsTargeter", useMember.getCsmId()));
			if(ship!=null)
				return $.add("message", "该会员帐号已存在，不能重复添加");
			new CsMemberShip(
					payMember.getCsmHost()//城市 [非空]
				 	,payMember.getCsmId()//付款帐号 [非空]
				 	,useMember.getCsmId()//使用帐号 [非空]
				 	,new Date()//添加时间 [非空]
				 	,""//备注
				 	,(short)1//状态 [非空]
				 ).save();		
			return $.add("message", "会员帐号添加成功").add("success", true);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.add("message", "系统繁忙,请稍候再试");
		}
	}	
	
	public static @api Map del(Long id){
		try{
			//根据手机号码和姓名获取会员
			//根据会员获取与当前企业的关系，如果存在，则提示不能删除
			CsMemberShip ship = CsMemberShip.get(id);
			if(ship==null)
				return $.add("message", "对不起，你没有权限删除该数据");
			if(UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiMember().indexOf("#"+ship.getCsmsPayer().longValue()+"#")==-1)
				return $.add("message", "对不起，你没有权限删除该数据");
			ship.delete();	
			return $.add("message", "会员帐号删除成功").add("success", true);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.add("message", "系统繁忙,请稍候再试");
		}
	}
	
	/**
	 * 根据支付人获取车辆使用人
	 * @return
	 */
	public String accounts(){
		try{			
			com.ccclubs.service.admin.ICsMemberService csMemberService = $.GetSpringBean("csMemberService");
			
			Long pid = $.getLong("pid");
			if(UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiMember().indexOf("#"+pid+"#")==-1)
				return $.SendAjax(null,"UTF-8");
			
			CsMember payMember = CsMember.get(pid);
			
			Map params = new HashMap();
			params.put("csmStatus", 1);
			
			String strDefinex = "";
			String strTerm=$.getString("value");
			if(!$.empty(strTerm)){
				strDefinex += "(";
				strDefinex += "csm_username like '%"+strTerm.replaceAll("'", "''")+"%'";
				strDefinex += " or csm_name like '%"+strTerm.replaceAll("'", "''")+"%'";
				strDefinex += " or csm_email like '%"+strTerm.replaceAll("'", "''")+"%'";
				strDefinex += " or csm_mobile like '%"+strTerm.replaceAll("'", "''")+"%'";	
				strDefinex+=")";
			}		
			strDefinex+=" and (csm_id="+payMember.getCsmId()+" or csm_id in (select csms_targeter from cs_member_ship where csms_status=1 and csms_payer="+payMember.getCsmId()+"))";
			
			params.put("definex", strDefinex);
			
			List<com.ccclubs.model.CsMember> list = csMemberService.getCsMemberList(params, -1);
			List<Map> result = new java.util.ArrayList();
			String template = "{name}({mobile})";
			for(com.ccclubs.model.CsMember csMember:list){
				Map map = new HashMap();
				map.put("value", $.zerofill(csMember.getCsmId().toString(), 6));
				String strText = template
				.replace("{name}", $.js(csMember.getCsmName()))
				.replace("{username}", $.js(csMember.getCsmUsername()))
				.replace("{mobile}", $.js(csMember.getCsmMobile()))
				.replace("{email}", $.js(csMember.getCsmEmail()));				
				map.put("text",strText);
				result.add(map);
			}
			return $.SendAjax(result, "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}		
	}

	public ICsMemberShipService getCsMemberShipService() {
		return csMemberShipService;
	}

	public void setCsMemberShipService(ICsMemberShipService csMemberShipService) {
		this.csMemberShipService = csMemberShipService;
	}
}
