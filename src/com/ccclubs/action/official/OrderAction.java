package com.ccclubs.action.official;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.ccclubs.action.unit.UnitLoginHelper;
import com.ccclubs.exception.MessageException;
import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.helper.UnitHelper;
import com.ccclubs.helper.UnitHelper.UnitOrderInfo;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsUnitInfo;
import com.ccclubs.model.CsUnitOrder;
import com.ccclubs.model.CsUnitPerson;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsUnitGroupService;
import com.ccclubs.service.admin.ICsUnitOrderService;
import com.ccclubs.service.common.ICommonUnitService;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.ControllerHelper;
import com.lazy3q.web.util.Page;
import com.opensymphony.xwork2.ActionContext;

public class OrderAction {
	
	public ICsUnitOrderService csUnitOrderService;
	public ICsUnitGroupService csUnitGroupService;
	public ICsMemberService csMemberService;
	public ICsOrderService csOrderService;
	public ICommonUnitService commonUnitService;
	public CsUnitOrder csUnitOrder;
	public CsOrder csOrder;
	public CsUnitPerson csUnitPerson;
	
	public String execute(){
		try{
			$.SetRequest("controller", $.getJson("controller"));
			//查询条件传递到转跳
			$.SetRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			Map<String,Object> requests = ActionContext.getContext().getParameters();
			Map<String,Object> objects=new HashMap();
			//把request传过来的(String)参数转换成java对象			
			objects.put(CsUnitOrder.F.csuoOrder, $.getLong(CsUnitOrder.F.csuoOrder));
 			objects.put(CsUnitOrder.F.csuoUnitInfo, UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiId());
 			objects.put(CsUnitOrder.F.csuoUnitChild, $.getLong(CsUnitOrder.F.csuoUnitChild));
 			objects.put(CsUnitOrder.F.csuoCar, $.getLong(CsUnitOrder.F.csuoCar));
 			objects.put(CsUnitOrder.F.csuoUsername, $.getString(CsUnitOrder.F.csuoUsername));
 			objects.put(CsUnitOrder.F.csuoMobile, $.getString(CsUnitOrder.F.csuoMobile));
 			objects.put(CsUnitOrder.F.csuoAlias, $.getString(CsUnitOrder.F.csuoAlias));
			objects.put(CsUnitOrder.F.csuoStartTime+"Start", $.getDate(CsUnitOrder.F.csuoStartTime+"Start"));
			objects.put(CsUnitOrder.F.csuoStartTime+"End", $.getDate(CsUnitOrder.F.csuoStartTime+"End"));
			objects.put(CsUnitOrder.F.csuoStartTime, $.getString(CsUnitOrder.F.csuoStartTime));
			objects.put(CsUnitOrder.F.csuoFinishTime+"Start", $.getDate(CsUnitOrder.F.csuoFinishTime+"Start"));
			objects.put(CsUnitOrder.F.csuoFinishTime+"End", $.getDate(CsUnitOrder.F.csuoFinishTime+"End"));
			objects.put(CsUnitOrder.F.csuoFinishTime, $.getString(CsUnitOrder.F.csuoFinishTime));
			objects.put(CsUnitOrder.F.csuoRetTime+"Start", $.getDate(CsUnitOrder.F.csuoRetTime+"Start"));
			objects.put(CsUnitOrder.F.csuoRetTime+"End", $.getDate(CsUnitOrder.F.csuoRetTime+"End"));
			objects.put(CsUnitOrder.F.csuoRetTime, $.getString(CsUnitOrder.F.csuoRetTime));
 			objects.put(CsUnitOrder.F.csuoRemark, $.getString(CsUnitOrder.F.csuoRemark));
 			objects.put(CsUnitOrder.F.csuoUnitGroup, $.getShort(CsUnitOrder.F.csuoUnitGroup)); 
			objects.put(CsUnitOrder.F.csuoState, $.getShort(CsUnitOrder.F.csuoState));
			objects.put(CsUnitOrder.F.csuoSettleState, $.getShort(CsUnitOrder.F.csuoSettleState));
			objects.put(CsUnitOrder.F.csuoSpecial, $.getLong(CsUnitOrder.F.csuoSpecial));
			objects.put("definex", "csuo_special = 0");
			//取不包含的条件字段
 			String[] strNots = $.getArray("Not");
 			if(strNots!=null){
	 			for(String strNot:strNots)
	 				objects.put(strNot+"Not", "not");
 			}			
			//设置查询记录的排序规则
				objects.put("desc","csuo_id");
						
			Page page = csUnitOrderService.getCsUnitOrderPage($.getInteger(requests, "page", 0), $.getInteger("size",10), objects);
			//把分页对象放入request
			$.SetRequest("page", page);	
		}catch(Exception ex){
			ex.printStackTrace();			
		}
		
		return "index";
	}
	
	public String edit(){
		try{
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csUnitOrder);//进入之前的预处理	
			$.SetRequest("controller",controller);
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
			$.SetRequest("ids",$.getString(params, "ids"));
			$.setSession("csUnitOrderToken", $.uuid());
			Long id=$.getLong(params, "id");
			if(id!=null){
				csUnitOrder=csUnitOrderService.getCsUnitOrderById(id);
				csOrder = csOrderService.getCsOrderById(csUnitOrder.getCsuoOrder());
			}
			if(csUnitOrder==null)
				csUnitOrder = new CsUnitOrder();
			//根据自定义的默认值信息设置默认值
			ControllerHelper.setObjectDefaultValue(csUnitOrder,controller);
			ControllerHelper.setObjectDefaultValue(csOrder,controller);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";
	}
	
	public String add(){
		try{
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csUnitOrder);//进入之前的预处理	
			$.SetRequest("controller",controller);
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
			$.SetRequest("ids",$.getString(params, "ids"));
			$.setSession("csUnitOrderToken", $.uuid());
			Long id=$.getLong(params, "id");
			
			if(id!=null){
				csUnitOrder=csUnitOrderService.getCsUnitOrderById(id);
			}
			if(csUnitOrder==null)
				csUnitOrder = new CsUnitOrder();
			//根据自定义的默认值信息设置默认值
			ControllerHelper.setObjectDefaultValue(csUnitOrder,controller);
			$.setRequest("canVisible", true);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "add";
	}
	
	public String save(){
		try{
			final CsUnitInfo unitInfo = UnitLoginHelper.getLogin().get$csuuUnitInfo();
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csUnitOrder);//进入之前的预处理	
			$.SetRequest("controller",controller);
			String id = $.getString("id");
			if(($.empty(id) && csUnitOrder.getCsuoId()==null)){
				//获取所有的部门信息
				Map<String,Object> param = new HashMap();
				param.put(CsMember.F.csmMobile, csUnitOrder.getCsuoMobile());
				param.put("definex", "(csm_status=1)");
				CsMember csMember = csMemberService.getCsMember(param);
				commonUnitService.executeAddOfficialOrder(csUnitPerson.getCsupId(), csUnitOrder.getCsuoProfile(), csUnitOrder.getCsuoCar(), csUnitOrder.getCsuoStartTime(), csUnitOrder.getCsuoFinishTime(), csUnitOrder.getCsuoType());
				$.SetTips("保存订单信息成功");
				}else{
					
					$.SetTips("系统繁忙，请稍后再试！");
				}	
		}catch(MessageException e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips(e.getMessage()); 
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙，请稍后再试！");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	
	/**
	 * 审核订单、续订订单和取消订单业务处理 
	 */
	public String checkOrReorderOrCancel(){
		try{
			
			String type = $.getRequest("canVisible");//type为1表示续订业务，type为2表示取消订单业务，type为3表示审核业务
			String str = $.getRequest("submiter");//该字段表示的是针对审核业务，如果str获取的是agree表示审核通过，如果为disagree表示审核不通过
			Long id = $.getLong("id");
			csUnitOrder = csUnitOrderService.getCsUnitOrderById(id);
			//csOrder = csUnitOrder.get$csuoOrder();
			if("1".equals(type)){
				if(csUnitOrder.getCsuoState() == 2 && csUnitOrder.getCsuoState()!= 2 ){
					$.SetTips("无法续订订单！");
				}else{
					if(commonUnitService.executeReUnitOrder(UnitLoginHelper.getLogin().getCsuuId(), csUnitOrder.getCsuoId(), csUnitOrder.getCsuoFinishTime()) != null)
						$.SetTips("订单续订成功！");
				}				
			}else if("2".equals(type)){
				if(csUnitOrder.getCsuoState()!= 2){
					$.SetTips("订单使用中或已完成，无法取消！");
				}else{
					commonUnitService.executeCancelUnitOrder(csUnitOrder.getCsuoUnitInfo(), csUnitOrder.getCsuoId(), csUnitOrder.getCsuoRemark());
					$.SetTips("订单取消成功！");
				}
				
			}else if("3".equals(type)){
				if(csUnitOrder.getCsuoState() >= 2){
					$.SetTips("订单已审核过，无再需审核！");
				}else{
					if("agree".equals(str)){
						commonUnitService.executeCheckUnitOrder(true, csUnitOrder.getCsuoId(), csUnitOrder.getCsuoUnitInfo(), csUnitOrder.getCsuoRemark());
					}else if("disagree".equals(str)){					
						commonUnitService.executeCheckUnitOrder(false, csUnitOrder.getCsuoId(), csUnitOrder.getCsuoUnitInfo(), csUnitOrder.getCsuoRemark());
					}
					$.SetTips("审核操作成功！");
				}
			}else{
				$.SetTips("系统繁忙，请稍后再试！");
			}
		}catch(MessageException e){
			$.SetTips(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙，请稍后再试！");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	/**
	 * 续订订单业务处理
	 * @return String
	 */
	public String reorder(){
		try{
				final Map controller = $.getJson("controller");
				ControllerHelper.executeControllerBefore(controller, csUnitOrder);//进入之前的预处理	
				$.SetRequest("controller",controller);
				Map params = ActionContext.getContext().getParameters();
				$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
				$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
				$.SetRequest("ids",$.getString(params, "ids"));
				$.setSession("csUnitOrderToken", $.uuid());
				String operate = $.getString(params,"operate");
				Long id=$.getLong(params, "id");
				if(id!=null){
					csUnitOrder=csUnitOrderService.getCsUnitOrderById(id);
					//csOrder = csOrderService.getCsOrderById(csUnitOrder.getCsuoOrder());
				}
				if(csUnitOrder==null)
					csUnitOrder = new CsUnitOrder();
				//根据自定义的默认值信息设置默认值
				ControllerHelper.setObjectDefaultValue(csUnitOrder,controller);
				if("续订".equals(controller.get("title"))){
					$.setRequest("canVisible", 1);
				}else if("取消".equals(controller.get("title"))){
					$.setRequest("canVisible", 2);
				}else if("审核".equals(controller.get("title"))){
					$.setRequest("canVisible", 3);
				}
				//ControllerHelper.setObjectDefaultValue(csOrder,controller);			
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙，请稍后再试！");
		}
		return "reorder";
	}
	

	/**
	 * 查询订单业务处理
	 * @return String
	 */
	
	public String query(){
		try{
			
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙，请稍后再试");
		}
		return "";
	}
	
	public String details(){
		try{
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csUnitOrder);//进入之前的预处理	
			$.SetRequest("controller",controller);
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
			$.SetRequest("ids",$.getString(params, "ids"));
			$.setSession("csUnitOrderToken", $.uuid());
			Long id = $.getLong(params,"id");
			if(id!=null){
				csUnitOrder=csUnitOrderService.getCsUnitOrderById(id);
			}
			if(csUnitOrder==null)
				csUnitOrder = new CsUnitOrder();
			if(csUnitOrder.getCsuoMileage() == null)
				csUnitOrder.setCsuoMileage(0d);
			UnitOrderInfo info = UnitHelper.getUnitOrderInfo(
					csUnitOrder.getCsuoUnitInfo(),
					csUnitOrder.getCsuoCar(), 
					csUnitOrder.getCsuoStartTime(), 
					csUnitOrder.getCsuoFinishTime(), 
					csUnitOrder.getCsuoRetTime(),
					csUnitOrder.getCsuoFreeHour(),
					csUnitOrder.getCsuoMileage(),
					csUnitOrder.getCsuoFreeKm()
				);
			if("结算".equals(controller.get("title"))){
				$.setRequest("canVisible", 1);
			}else if("详情".equals(controller.get("title"))){
				$.setRequest("canVisible", 2);
			}
			$.setRequest("day", info.getDay());
			$.setRequest("hour", info.getHour());
			$.setRequest("total", info.getTotal());
			ControllerHelper.setObjectDefaultValue(csUnitOrder,controller);
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙，请稍后再试！");
		}
		return "detail";
	}
	
	public String settlement(){
		try{
			final Map controller = $.getJson("controller");
			$.SetRequest("controller",controller);
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
						
			final CsUnitOrder oldOrder = csUnitOrderService.getCsUnitOrderById(csUnitOrder.getCsuoId());
			if(oldOrder.getCsuoState().shortValue()!=2){
				$.SetTips("只有在状态为审核通过的订单才能结算");
				return $.Redirect($.xeh($.getString("entrypoint")));
			}
			csUnitOrderService.executeTransaction(new Function(){
				public <T> T execute(Object... arg0){
					csUnitOrder = OrderAction.this.merge(csUnitOrder);
					UnitOrderInfo info = UnitHelper.getUnitOrderInfo(
							csUnitOrder.getCsuoUnitInfo(),
							csUnitOrder.getCsuoCar(), 
							csUnitOrder.getCsuoStartTime(), 
							csUnitOrder.getCsuoFinishTime(), 
							csUnitOrder.getCsuoRetTime(),
							csUnitOrder.getCsuoFreeHour(),
							csUnitOrder.getCsuoMileage(),
							csUnitOrder.getCsuoFreeKm()
						);
															
					csUnitOrderService.updateCsUnitOrderByConfirm(
							$.add(CsUnitOrder.F.csuoRetTime, csUnitOrder.getCsuoRetTime())
							.add(CsUnitOrder.F.csuoPayNeed, info.getTotal())
							.add(CsUnitOrder.F.csuoFreeHour, csUnitOrder.getCsuoFreeHour())
							.add(CsUnitOrder.F.csuoMileage, csUnitOrder.getCsuoMileage())
							.add(CsUnitOrder.F.csuoFreeKm, csUnitOrder.getCsuoFreeKm())
							.add(CsUnitOrder.F.csuoPayReal, csUnitOrder.getCsuoPayReal())
							.add(CsUnitOrder.F.csuoRemark, csUnitOrder.getCsuoRemark()),
							$.add(CsUnitOrder.F.csuoId, csUnitOrder.getCsuoId()).add("confirm", 1)
						);
					//记录操作日志
					LoggerHelper.writeLog(CsUnitOrder.class,"update",UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiName()+"结算订单[公务订单][订单]["+csUnitOrder.getCsuoOrder()+"]",0l, null);
					return null;
				}
			});
			
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙，请稍后再试！");
		}
		
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	
	/**
	 * 根据临时订单到数据库取实际订单，并合并两对象
	 * @param csOrder
	 * @return
	 */
	public CsUnitOrder merge(CsUnitOrder csUnitOrder) {
		if(csUnitOrder.getCsuoId()==null)return csUnitOrder;
		CsUnitOrder dbOrder = csUnitOrderService.getCsUnitOrderById(csUnitOrder.getCsuoId());
		if(dbOrder!=null){//与持久对象合并
			Field[] fields = CsUnitOrder.class.getDeclaredFields();
			for(Field field:fields){
				field.setAccessible(true);
				try{
					if(field.get(csUnitOrder)!=null)
						field.set(dbOrder, field.get(csUnitOrder));	
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}else
			dbOrder = csUnitOrder;
		return dbOrder;
	}

	public ICsUnitOrderService getCsUnitOrderService() {
		return csUnitOrderService;
	}

	public void setCsUnitOrderService(ICsUnitOrderService csUnitOrderService) {
		this.csUnitOrderService = csUnitOrderService;
	}

	public ICsOrderService getCsOrderService() {
		return csOrderService;
	}

	public void setCsOrderService(ICsOrderService csOrderService) {
		this.csOrderService = csOrderService;
	}

	public CsUnitOrder getCsUnitOrder() {
		return csUnitOrder;
	}

	public void setCsUnitOrder(CsUnitOrder csUnitOrder) {
		this.csUnitOrder = csUnitOrder;
	}

	public ICsUnitGroupService getCsUnitGroupService() {
		return csUnitGroupService;
	}

	public void setCsUnitGroupService(ICsUnitGroupService csUnitGroupService) {
		this.csUnitGroupService = csUnitGroupService;
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

	public CsOrder getCsOrder() {
		return csOrder;
	}

	public void setCsOrder(CsOrder csOrder) {
		this.csOrder = csOrder;
	}

	public ICommonUnitService getCommonUnitService() {
		return commonUnitService;
	}

	public void setCommonUnitService(ICommonUnitService commonUnitService) {
		this.commonUnitService = commonUnitService;
	}

	public CsUnitPerson getCsUnitPerson() {
		return csUnitPerson;
	}

	public void setCsUnitPerson(CsUnitPerson csUnitPerson) {
		this.csUnitPerson = csUnitPerson;
	}
	
	
}
