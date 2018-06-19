package com.ccclubs.action.unit;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.ActionHelper;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.model.CsArea;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsMemberShip;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsOrderDetail;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsUnitInfo;
import com.ccclubs.model.Restriction;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonOrderService;
import com.ccclubs.service.common.impl.OrderInfo;
import com.lazy3q.lang.type;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.WebHelper.LzMap;
import com.lazy3q.web.node.Export;
import com.lazy3q.web.util.Page;
import com.opensymphony.xwork2.ActionContext;

public class AdapterAction {
	
	public String member_account() {
		try{
			Long pid = $.getLong("pid");
			if(!$.idin(UnitLoginHelper.getUseIds(),pid))
				return $.SendAjax(null,"UTF-8");
			ICommonMoneyService commonMoneyService=$.GetSpringBean("commonMoneyService");
			CsMember member = CsMember.get(pid);
			Double canUseMoney = commonMoneyService.getUsableAmount(pid);
			Double canFreeHour = commonMoneyService.getCanUseFreeHour(pid, new Date());
			return $.SendAjax($.add("money",canUseMoney).add("freehour", canFreeHour).add("integral", member.getCsmIntegral()),"UTF-8");	
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
		}
		return $.SendAjax(null,"UTF-8");	
	}
	public String member_info() {
		try{
			Long pid = $.getLong("pid");
			if(!$.idin(UnitLoginHelper.getUseIds(),pid))
				return $.SendAjax(null,"UTF-8");
			//查询是否有查询权限
			CsMemberShip memberShip = CsMemberShip.Get($.add("csmsPayer",pid));
			if(memberShip==null)
				return $.SendAjax(null,"UTF-8");
			ICommonMoneyService commonMoneyService=$.GetSpringBean("commonMoneyService");
			CsMember csMember = CsMember.get(pid);
			
			Boolean bObject=$.getBoolean("object",false);
			
			List<Map> result = new java.util.ArrayList();
			if(csMember!=null){
				Map map = new HashMap();
				map.put("value",csMember.getCsmId().toString());
				String strText = "";
				strText+=csMember.getCsmName()+",";
				map.put("text", $.js(strText));
				if(bObject==true)
					map.put("object",csMember);
				result.add(map);
			}
			
			return $.SendAjax(result, "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}	
	}

	public String member_inquire() {
		try{
 			Map<String,Object> memparams=new HashMap();
			LzMap params = $.Map();
			String strTerm=$.getString("value");
			String strDefinex = " csm_id in ("+UnitLoginHelper.getUseIds()+") ";
			if(!$.empty(strTerm)){
				strDefinex += " and (";
				strDefinex += "csm_username like '%"+strTerm.replaceAll("'", "''")+"%'";
				strDefinex += " or csm_name like '%"+strTerm.replaceAll("'", "''")+"%'";
				strDefinex += " or csm_email like '%"+strTerm.replaceAll("'", "''")+"%'";
				strDefinex += " or csm_mobile like '%"+strTerm.replaceAll("'", "''")+"%'";
				strDefinex+=")";				
			}
			params.put("definex",strDefinex);
			
			List<CsMember> list = CsMember.list(params, 10);
			List<Map> result = new java.util.ArrayList();
			//String template = "{name}({mobile})[{username}]<{email}>";
			for(CsMember csMember:list){
				Map map = new HashMap();
				map.put("value", $.zerofill(csMember.getCsmId(), 6));
				/*
				String strText = template
				.replace("{name}", $.js(csMember.getCsmName()))
				.replace("{username}", $.js(csMember.getCsmUsername()))
				.replace("{mobile}", $.js(csMember.getCsmMobile()))
				.replace("{email}", $.js(csMember.getCsmEmail()));	
				*/			
				map.put("text",csMember.getKeyValue());
				result.add(map);
			}
			return $.SendAjax(result, "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}
	}

	public String car_query() {
		try{
			LzMap params = $.Map();
			CsUnitInfo unitInfo = UnitLoginHelper.getLogin().get$csuuUnitInfo();
			if(unitInfo.getCsuiOutlets()!=null){
				params.put(CsCar.F.cscOutlets, unitInfo.getCsuiOutlets());
			}else{
				params.put(CsCar.F.cscHost, unitInfo.getCsuiHost());
			}
			params.add(CsCar.F.cscStatus, 1);
			String strTerm=$.getString("value");
			if(!$.empty(strTerm)){
				String strDefinex = "(";
				strDefinex += "csc_number like '%"+strTerm.replaceAll("'", "''")+"%'";
				strDefinex+=")";
				params.put("definex",strDefinex);
			}
			List<CsCar> list = CsCar.list(params, 10);
			List<Map> result = new java.util.ArrayList();
			String template = "{name}({mobile})[{username}]<{email}>";
			for(CsCar csCar:list){
				Map map = new HashMap();
				map.put("value", $.zerofill(csCar.getCscId().toString(), 6));			
				map.put("text",csCar.getCscCarNo());
				result.add(map);
			}
			return $.SendAjax(result, "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}
	}
	
	
	
	

	public String carmodel_query() {
		try{
 			Map<String,Object> params=new HashMap();		
			//把request传过来的(String)参数转换成java对象			
			Field[] fields = CsCarModel.F.class.getFields();
			for(Field field:fields){
				type type = field.getAnnotation(type.class);
				params.put(field.getName(), $.getObject(field.getName(), type.value()));
			}
			
			//取排序参数
			String strAsc=$.getString("asc");
			params.put("asc", strAsc);
			String strDesc=$.getString("desc");
			params.put("desc",strDesc);
			if($.empty(strAsc) && $.empty(strDesc))
				params.put("desc","cscm_id");
								
			String strTerm=$.getString("value");
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("cscmId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or cscm_name like '%"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}

			
			Boolean bObject=$.getBoolean("object",false);
			
			List<CsCarModel> list = CsCarModel.list(params, $.getInteger("size",10));
			List<Map> result = new java.util.ArrayList();
			for(CsCarModel csCarModel:list){
				Map map = new HashMap();
				map.put("value",csCarModel.getCscmId().toString());
				String strText = "";
				strText+=csCarModel.getCscmName()+",";
				map.put("text", $.js(strText));
				if(bObject==true)
					map.put("object",csCarModel);
				result.add(map);
			}
			return $.SendAjax(result, "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}	
	}

	public String outlets_tree() {
		try{
			CsUnitInfo unitInfo = UnitLoginHelper.getLogin().get$csuuUnitInfo();
			if(unitInfo.getCsuiOutlets()==null){
				Long parent = $.getLong("parent",0l);
				if(parent.longValue()==0){
					LzMap params = $.add(CsArea.F.csaHost,unitInfo.getCsuiHost());
					List<CsArea> list = CsArea.list(params, -1);
					String strAjax="";
					for(CsArea o:list){
						if(!strAjax.equals(""))
							strAjax+=",";
						strAjax+="{";
						strAjax+="\"value\":\""+o.getCsaId()+"\"";
						strAjax+=",";
						strAjax+="\"text\":\""+$.js(o.getCsaName())+"\"";
						strAjax+=",";
						strAjax+="\"more\":true";
						strAjax+=",";
						strAjax+="\"invalid\":true";					
						strAjax+="}";
					}
					return $.SendAjax("["+strAjax+"]", "UTF-8");
				}else{
					List<CsOutlets> list = CsOutlets.list($.add("csoArea", parent), -1);
					List<Map> result = new java.util.ArrayList();
					for(CsOutlets csOutlets:list){
						Map map = new HashMap();
						map.put("value", $.zerofill(csOutlets.getCsoId().toString(), 6));
						map.put("text", $.js(""+csOutlets.getCsoName()));
						result.add(map);
					}
					return $.SendAjax(result, "UTF-8");
				}
			}else{
				List<Map> result = new java.util.ArrayList();
				{
					Map map = new HashMap();
					List<CsOutlets> csOutletsList = unitInfo.get$csuiOutlets();
					if(csOutletsList!=null)
					for(CsOutlets csOutlets: csOutletsList){
						map.put("value", $.zerofill(csOutlets.getCsoId().toString(), 6));
						map.put("text", $.js(""+csOutlets.getCsoName()));
						result.add(map);
					}
				}
				return $.SendAjax(result, "UTF-8");
			}
						
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}
	}
	
	
	
	/**
	 * 订单明细管理首页
	 * @return
	 */	
	public String orderdetail()
	{	
		try{
			java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\.([\\S\\s]+)\\.").matcher($.getServletRequest().getServletPath());
			String alias=(String) (matcher.find()?$.setRequest("alias",matcher.group(1)):null);//别名入口
			$.setRequest("CsOrderDetailClass",CsOrderDetail.class);
			$.setRequest("controller", $.getJson("controller"));//可配置控制器				
			$.setRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//查询条件传递到转跳
			Boolean all = $.setRequest("all", $.getBoolean("all")) ;
			if(all!=null && all==false && !$.empty($.getString("fields"))){//自定义显示字段
				String[] fields = $.getString("fields").split(",");
				Map<String,Object> defines = new HashMap();
				for(String key:fields)
					defines.put(key, true);
				defines.put("fields", fields);
				$.setRequest("defines", defines);			
			}
			Map<String,Object> requests = ActionContext.getContext().getParameters();
			Map<String,Object> objects = ActionHelper.getQueryFormParams(CsOrderDetail.class);//把表单参数转成查询需要的参数格式

			//限制为当前支付会员
			objects.put("CsOrder", $.add("csoPayMember", UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiMember()));
						
			//取排序参数
			String strAsc=$.getString("asc");
			objects.put("asc", strAsc);
			String strDesc=$.getString("desc");
			objects.put("desc",strDesc);
			if($.empty(strAsc) && $.empty(strDesc))
				objects.put("desc","csod_id");
			
			//把request传过来的查询参数放回request
			Set<String> keys = objects.keySet();
			for(String key:keys)
				$.setRequest(key, objects.get(key));
			
			//把组装好的objects直接放入request
			$.setRequest("objects",objects);
			
			$.setRequest("canQuery",false);
			
						
			Page page = CsOrderDetail.page($.getInteger(requests, "page", 0), $.getInteger("size",16), objects);
			//把分页对象放入request
			$.setRequest("page", page);	
			
			objects.put("count",page.getCount());
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "index";
	}
	
	public String detaildynamic(){
		try{
			$.SetRequest("controller", $.getJson("controller"));
			//查询条件传递到转跳
			$.SetRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			
			ICommonOrderService commonOrderService=$.GetSpringBean("commonOrderService");	
			List<CsOrderDetail> details = new java.util.ArrayList();
			csOrder = commonOrderService.merge(csOrder);
			//拆分订单			
			OrderInfo orderinfo = commonOrderService.splitOrderDetails(
					csOrder.getCsoPayMember(), 
					csOrder.getCsoCar(), 
					csOrder.getCsoStartTime(), 
					csOrder.getCsoFinishTime(), 
					csOrder.getCsoRetTime(), 
					csOrder.getCsoFreehour(), 
					csOrder.getCsoElectric(), 
					csOrder.getCsoFuel(), 
					csOrder.getCsoFeeType(),
					csOrder.getCsoInsureType(),
					csOrder.getCsoLongPrice(),
					csOrder.getCsoId());
			details=orderinfo.getDetails();
			Page page  = new Page(0, 1024, details.size(), details);
			$.SetRequest("page", page);			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "index";
	}
	CsOrder csOrder;
	public CsOrder getCsOrder() {
		return csOrder;
	}

	public void setCsOrder(CsOrder csOrder) {
		this.csOrder = csOrder;
	}

}
