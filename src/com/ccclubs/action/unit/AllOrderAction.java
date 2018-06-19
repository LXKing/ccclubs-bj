package com.ccclubs.action.unit;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.UUID;
import java.lang.reflect.Field;

import org.apache.log4j.Logger;

import com.lazy3q.web.helper.$;
import com.lazy3q.web.node.Export;
import com.lazy3q.lang.*;
import com.lazy3q.web.helper.ConfigHelper;
import com.lazy3q.web.helper.ControllerHelper;
import com.lazy3q.web.helper.WebHelper.LzMap;
import com.lazy3q.web.util.Page;
import com.lazy3q.web.util.Tree;
import com.lazy3q.util.Function;
import com.lazy3q.util.Meet;

import com.opensymphony.xwork2.ActionContext;
		   
import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.helper.ActionHelper;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsOrder;
import com.ccclubs.service.admin.ICsOrderService;
/**************LAZY3Q_CODE_IMPORT**************/
import com.ccclubs.service.common.From;
import com.ccclubs.service.common.impl.OrderInfo;
/**************LAZY3Q_CODE_IMPORT**************/


/**
 * 企业所有订单的Action
 * @author 飞啊飘啊
 * 感谢struts的Action框架
 */
@SuppressWarnings("unchecked")
public class AllOrderAction
{
	ICsOrderService csOrderService;
	
	CsOrder csOrder;
	
	/**
	 * 企业所有订单管理首页
	 * @return
	 */	
	public String execute()
	{	
		try{
			java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\.([\\S\\s]+)\\.").matcher($.getServletRequest().getServletPath());
			String alias=(String) (matcher.find()?$.setRequest("alias",matcher.group(1)):null);//别名入口
			$.setRequest("CsOrderClass",CsOrder.class);
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
			Map<String,Object> objects = ActionHelper.getQueryFormParams(CsOrder.class);//把表单参数转成查询需要的参数格式
			
			//添加域标识
			objects.put("definex", "csms_payer in ("+UnitLoginHelper.getUseIds()+")");
			
			//取排序参数
			String strAsc=$.getString("asc");
			objects.put("asc", strAsc);
			String strDesc=$.getString("desc");
			objects.put("desc",strDesc);
			if($.empty(strAsc) && $.empty(strDesc))
				objects.put("desc","cso_id");
			
			//把request传过来的查询参数放回request
			Set<String> keys = objects.keySet();
			for(String key:keys)
				$.setRequest(key, objects.get(key));
			
			//把组装好的objects直接放入request
			$.setRequest("objects",objects);
			
			$.setRequest("canQuery", $.getBoolean("canQuery",true));
			/************LAZY3Q_CODE_EXECUTE************/
			$.setRequest("canView",true);
			$.setRequest("canAdd",true);
			$.setRequest("canEdit",false);
			$.setRequest("canDel",false);
			List<Boolean> canExp=new ArrayList();
			for(int i=0;i<13;i++){
				canExp.add(true);
			}
			$.setRequest("canExp",canExp);
			/************LAZY3Q_CODE_EXECUTE************/

						
			Page page = csOrderService.getCsOrderPage($.getInteger(requests, "page", 0), $.getInteger("size",16), objects);
			//把分页对象放入request
			$.setRequest("page", page);	
			/************LAZY3Q_AFTER_EXECUTE************/
			/************LAZY3Q_AFTER_EXECUTE************/

			
			objects.put("count",page.getCount());
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "index";
	}
	
	
	/**
	 * 订单添加/编辑页面
	 * @return
	 */
	public String edit()
	{		
		try{
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csOrder);//进入之前的预处理	
			$.setRequest("controller",controller);
			Map params = ActionContext.getContext().getParameters();
			$.setRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.setRequest("entrypoint", $.getString(params, "entrypoint"));
			$.setRequest("ids",$.getString(params, "ids"));
			$.setSession("csOrderToken", $.uuid());
			Long id=$.getLong(params, "id");
			if(id!=null){
				csOrder=csOrderService.getCsOrderById(id);
				//确认域标识一个
				if(!$.idin(UnitLoginHelper.getUseIds(), csOrder.getCsoPayMember())){
					return $.SendHtml("<script>alert('对不起，你没有权限编辑该数据');</script>", "UTF-8");
				}
			}
			if(csOrder==null)
				csOrder = new CsOrder();
			//根据自定义的默认值信息设置默认值
			ControllerHelper.setObjectDefaultValue(csOrder,controller);
			/************LAZY3Q_CODE_EDIT************/
			/************LAZY3Q_CODE_EDIT************/

		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";
	}
	
	/**
	 * 显示订单详情
	 * @return
	 */
	public String details()
	{
		try{
			$.setRequest("controller", $.getJson("controller"));
			Map params = ActionContext.getContext().getParameters();
			Long id=$.getLong(params,"id");
			csOrder=csOrderService.getCsOrderById(id);
			//确认域标识
			if(!$.idin(UnitLoginHelper.getUseIds(), csOrder.getCsoPayMember())){
				return $.SendHtml("<script>alert('对不起，你没有权限查看该数据');</script>", "UTF-8");
			}
			/************LAZY3Q_CODE_DETAIL************/
			/************LAZY3Q_CODE_DETAIL************/
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";
	}


	/**
	 * 根据订单编号或ID查询订单
	 * @return
	 */
	public String query(){
		try{
 			Map<String,Object> params=new HashMap();		
			//把request传过来的(String)参数转换成java对象			
			Field[] fields = CsOrder.F.class.getFields();
			for(Field field:fields){
				type type = field.getAnnotation(type.class);
				params.put(field.getName(), $.getObject(field.getName(), type.value()));
			}
			//添加域标识
			params.put(CsOrder.F.csoHost, LoginHelper.getLogin().getSuHost());
			params.put("definex", "csms_payer in ("+UnitLoginHelper.getUseIds()+")");
			
			
			//取排序参数
			String strAsc=$.getString("asc");
			params.put("asc", strAsc);
			String strDesc=$.getString("desc");
			params.put("desc",strDesc);
			if($.empty(strAsc) && $.empty(strDesc))
				params.put("desc","cso_id");
								
			String strTerm=$.getString("value");
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("csoId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or cso_id like '%"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}
			/************LAZY3Q_CODE_QUERY************/
			/************LAZY3Q_CODE_QUERY************/

			
			Boolean bObject=$.getBoolean("object",false);
			
			List<CsOrder> list = csOrderService.getCsOrderList(params, $.getInteger("size",10));
			List<Map> result = new java.util.ArrayList();
			for(CsOrder csOrder:list){
				Map map = new HashMap();
				map.put("value",csOrder.getCsoId().toString());
				String strText = "";
				strText+=csOrder.getCsoId()+",";
				map.put("text", $.js(strText));
				if(bObject==true)
					map.put("object",csOrder);
				result.add(map);
			}
			return $.SendAjax(result, "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}		
	}
	/***************LAZY3Q_DEFINE_CODE******************/
	/**
	 * 获取订单信息
	 */
	public String info(){
		try{
			csOrder = csOrderService.getCsOrderById($.getLong("id"));		
			return $.SendAjax(
					$.add("member",$.add("id", csOrder.getCsoUseMember()).add("name", csOrder.get$csoUseMember().getCsmName()))
					.add("car",$.add("id", csOrder.getCsoCar()).add("name", csOrder.get$csoCar().getCscNumber()))
				,"UTF-8");
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	/**
	 * 添加订单
	 */
	public synchronized String add(){
		try{
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csOrder);//进入之前的预处理	
			$.SetRequest("controller",controller);
			Double defineMargin = $.getDouble("defineMargin");
			
			String requestCsOrderToken = $.getString("csOrderToken");
			String sessionCsOrderToken = $.getSession("csOrderToken");
			//if($.equals(requestCsOrderToken,sessionCsOrderToken)){
				$.removeSession("csOrderToken");//移除令牌		
				com.ccclubs.service.common.ICommonDisposeService commonDisposeService=$.GetSpringBean("commonDisposeService");
				commonDisposeService.executeSaveOrder(
						csOrder.getCsoPayMember(), 
						csOrder.getCsoUseMember(), 
						csOrder.getCsoCar(), 
						csOrder.getCsoStartTime(), 
						csOrder.getCsoFinishTime(),	
						csOrder.getCsoFeeType(),
						csOrder.getCsoInsureType(), 
						csOrder.getCsoLongPrice(),
						csOrder.getCsoFreehour(),
						defineMargin!=null ? defineMargin : csOrder.getCsoMarginNeed(),
						0l,
						From.企业,
						UnitLoginHelper.getLoginAt()
				);
				//记录操作日志
				LoggerHelper.writeLog(CsOrder.class,"add","添加了[订单]["+csOrder.getCsoId()+"]",(Long)$.getSession("ccclubs_login_id"), csOrder);
				$.SetTips("保存订单成功");
			/*}else{
				$.SetTips("对不起，请勿重复提交");
			}*/
		}catch(com.ccclubs.exception.MessageException oe){
			$.SetTips(oe.getMessage());	
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	/**
	 * 续订
	 */
	public String reorder(){
		try{
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csOrder);//进入之前的预处理	
			$.SetRequest("controller",controller);
			com.ccclubs.service.common.ICommonDisposeService commonDisposeService=$.GetSpringBean("commonDisposeService");
			commonDisposeService.executeReOrder(csOrder.getCsoId(), csOrder.getCsoFinishTime(), csOrder.getCsoFreehour(),
					From.企业,
					UnitLoginHelper.getLoginAt());
			//记录操作日志
			LoggerHelper.writeLog(CsOrder.class,"update","续订[订单]["+csOrder.getCsoId()+"]",(Long)$.getSession("ccclubs_login_id"), csOrder);
			$.SetTips("续订订单成功");
		}catch(com.ccclubs.exception.MessageException oe){
			$.SetTips(oe.getMessage());	
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	/**
	 * 结算订单
	 * @return
	 */
	public String settle(){
		try{
			final Map controller = $.getJson("controller");
			$.SetRequest("controller",controller);
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
						
			CsOrder oldOrder = csOrderService.getCsOrderById(csOrder.getCsoId());
			short status = oldOrder.getCsoStatus().shortValue();
			if(status!=5){
				$.SetTips("只有待处理的订单才能进行结算操作");
				return $.Redirect($.xeh($.getString("entrypoint")));
			}			
			
			com.ccclubs.service.common.ICommonDisposeService commonDisposeService=$.GetSpringBean("commonDisposeService");
			commonDisposeService.executeSettleOrder(
					csOrder.getCsoId(), 
					csOrder.getCsoRetTime(), 
					csOrder.getCsoFuel(), 
					csOrder.getCsoElectric(),
					From.企业,
					UnitLoginHelper.getLoginAt());
			
			$.SetTips("订单结算成功");	
		}catch(com.ccclubs.exception.MessageException oe){
			$.SetTips(oe.getMessage());	
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	/**
	 * 取消订单
	 * @return
	 */
	public String cancel(){
		try{
			final Map controller = $.getJson("controller");
			$.SetRequest("controller",controller);
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
			
						
			CsOrder oldOrder = csOrderService.getCsOrderById(csOrder.getCsoId());
			short status = oldOrder.getCsoStatus().shortValue();
			if(status!=0){
				$.SetTips("只有已预订的订单才能取消");
				return $.Redirect($.xeh($.getString("entrypoint")));
			}
			
			com.ccclubs.service.common.ICommonDisposeService commonDisposeService=$.GetSpringBean("commonDisposeService");
			commonDisposeService.executeCancelOrder(csOrder.getCsoId(),csOrder.getCsoRemark(),
					From.企业,
					UnitLoginHelper.getLoginAt());
			
			$.SetTips("订单取消成功");
		}catch(com.ccclubs.exception.MessageException oe){
			$.SetTips(oe.getMessage());	
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	/**
	 * 待处理订单
	 * @return
	 */
	public String pend(){
		try{
			final Map controller = $.getJson("controller");
			$.SetRequest("controller",controller);
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
						
			CsOrder oldOrder = csOrderService.getCsOrderById(csOrder.getCsoId());
			short status = oldOrder.getCsoStatus().shortValue();
			if(status>2){
				$.SetTips("只有已预订、正在执行、已还车的订单才能进行待处理操作");
				return $.Redirect($.xeh($.getString("entrypoint")));
			}
			csOrderService.updateCsOrder$NotNull(
					new CsOrder().csoId(csOrder.getCsoId())
					.csoRemark(csOrder.getCsoRemark())
					.csoStatus((short)5)					
			);
			$.SetTips("订单待处理成功");			
						
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	/**
	 * 根据支付人获取车辆使用人
	 * @return
	 */
	public String user(){
		try{			
			com.ccclubs.service.admin.ICsMemberService csMemberService = $.GetSpringBean("csMemberService");

			Long payer = $.getLong("payer");
			if(payer==null)
				return $.SendAjax("[]", "UTF-8");
			
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
			strDefinex+=" and (csm_id="+payer+" or csm_id in (select csms_targeter from cs_member_ship where csms_status=1 and csms_payer="+payer+"))";
			
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
	

	/**
	 * 拆分订单获取订单相关信息
	 */
	public String split(){
		try{
			com.ccclubs.service.common.ICommonOrderService commonOrderService=$.GetSpringBean("commonOrderService");
			
			csOrder = commonOrderService.merge(csOrder);
			
			OrderInfo orderinfo = commonOrderService.splitOrderDetails(
					csOrder.getCsoPayMember(), 
					csOrder.getCsoCar(), 
					csOrder.getCsoStartTime(),
					csOrder.getCsoFinishTime(), 
					null, 
					csOrder.getCsoFreehour(), 
					null, 
					null, 
					csOrder.getCsoFeeType(),
					csOrder.getCsoInsureType(), 
					csOrder.getCsoLongPrice(),
					csOrder.getCsoId()
				);
			
			Double defineMargin = $.getDouble("defineMargin");
			//自定义保证金
			if(defineMargin!=null)
				orderinfo.setMargin(defineMargin);
			
			return $.SendAjax(
					$.add("margin", orderinfo.getMargin())
					.add("price", orderinfo.getPrice())
					.add("feeee", orderinfo.getFeeee())
					.add("freehour", orderinfo.getFreehour())
					.add("predict", orderinfo.getFeeee())
					.add("longorder", orderinfo.getLongorder())
					.add("feetype", orderinfo.getFeetype())
					.add("conflict", orderinfo.getConflict())
					,"UTF-8");			
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
		}
		return $.SendAjax(null,"UTF-8");
	}
	/**
	 * 重发订单
	 * @return
	 */
	public String reset(){
		try{
			final Map controller = $.getJson("controller");
			$.SetRequest("controller",controller);
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
						
			/*CsOrder oldOrder = csOrderService.getCsOrderById(csOrder.getCsoId());
			short status = oldOrder.getCsoStatus().shortValue();
			if(status>2){
				$.SetTips("只有已预订、正在执行、已还车的订单才能进行重发");
				return $.Redirect($.xeh($.getString("entrypoint")));
			}*/
			csOrderService.updateCsOrder$NotNull(
				new CsOrder().csoId(csOrder.getCsoId())
				.csoRemark(csOrder.getCsoRemark())
				.csoStatus((short)0)
				.csoState((short)0)
			);
			$.SetTips("重发订单成功");
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	/**
	 * 撤销订单
	 * @return
	 */
	public String undo(){
		try{
			final Map controller = $.getJson("controller");
			$.SetRequest("controller",controller);
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
						
			CsOrder oldOrder = csOrderService.getCsOrderById(csOrder.getCsoId());
			short status = oldOrder.getCsoStatus().shortValue();
			if(status!=5){
				$.SetTips("只有待处理的订单才能撤销");
				return $.Redirect($.xeh($.getString("entrypoint")));
			}
			
			com.ccclubs.service.common.ICommonDisposeService commonDisposeService=$.GetSpringBean("commonDisposeService");
			commonDisposeService.executeUndoOrder(csOrder.getCsoId(),csOrder.getCsoRemark(),
					From.企业,
					UnitLoginHelper.getLoginAt());
			
			$.SetTips("撤销订单成功");
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}	
	/***************LAZY3Q_DEFINE_CODE******************/


	public ICsOrderService getCsOrderService()
	{
		return csOrderService;
	}

	public void setCsOrderService(ICsOrderService csOrderService)
	{
		this.csOrderService = csOrderService;
	}

	public CsOrder getCsOrder()
	{
		return csOrder;
	}

	public void setCsOrder(CsOrder csOrder)
	{
		this.csOrder = csOrder;
	}
}