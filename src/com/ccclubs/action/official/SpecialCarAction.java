package com.ccclubs.action.official;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.action.unit.UnitLoginHelper;
import com.ccclubs.exception.MessageException;
import com.ccclubs.model.CsSpecialCar;
import com.ccclubs.model.CsUnitOrder;
import com.ccclubs.service.admin.ICsSpecialCarService;
import com.ccclubs.service.admin.ICsUnitOrderService;
import com.ccclubs.service.common.ICommonUnitService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.ControllerHelper;
import com.lazy3q.web.util.Page;
import com.opensymphony.xwork2.ActionContext;

public class SpecialCarAction {
	
	
	public ICsSpecialCarService csSpecialCarService;
	public ICsUnitOrderService csUnitOrderService;
	public ICommonUnitService commonUnitService;
	public CsSpecialCar csSpecialCar;
	public CsUnitOrder csUnitOrder;
	
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
 			objects.put(CsUnitOrder.F.csuoRemark, $.getString(CsUnitOrder.F.csuoRemark));
 			objects.put(CsUnitOrder.F.csuoUnitGroup, $.getShort(CsUnitOrder.F.csuoUnitGroup)); 
			objects.put(CsUnitOrder.F.csuoState, $.getShort(CsUnitOrder.F.csuoState));
			objects.put(CsUnitOrder.F.csuoSettleState, $.getShort(CsUnitOrder.F.csuoSettleState));
			objects.put(CsUnitOrder.F.csuoSpecial, $.getShort(CsUnitOrder.F.csuoSpecial));
			objects.put("definex", "csuo_order = 0");
			//取不包含的条件字段
 			String[] strNots = $.getArray("Not");
 			if(strNots!=null){
	 			for(String strNot:strNots)
	 				objects.put(strNot+"Not", "not");
 			}			
			//设置查询记录的排序规则
				objects.put("desc","csuo_id");
						
			Page page = csUnitOrderService.getCsUnitOrderPage($.getInteger(requests, "page", 0), $.getInteger("size",16), objects);
			//把分页对象放入request
			$.SetRequest("page", page);	
		}catch(MessageException e){ 
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙，请稍后再试！");
		}
		return "index";
	}
	
	/**
	 * 审核、取消订单页面
	 * @return String
	 */
	public String checkorder(){
		try{
				final Map controller = $.getJson("controller");
				ControllerHelper.executeControllerBefore(controller, csUnitOrder);//进入之前的预处理	
				$.SetRequest("controller",controller);
				Map params = ActionContext.getContext().getParameters();
				$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
				$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
				$.SetRequest("id",$.getString(params, "id"));
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
				if("审核".equals(controller.get("title"))){
					$.setRequest("canVisible", 1);
				}else if("取消".equals(controller.get("title"))){
					$.setRequest("canVisible", 2);
				}
				//ControllerHelper.setObjectDefaultValue(csOrder,controller);			
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙，请稍后再试！");
		}
		return "checkorder";
	}
	
	/**
	 * 审核和取消订单业务处理
	 * @return
	 */
	public String checkandcancelorder(){
		try{
			String type = $.getRequest("canVisible");//type为1表示审核业务，type为2表示取消订单业务
			String str = $.getRequest("submiter");//该字段表示的是针对审核业务，如果str获取的是agree表示审核通过，如果为disagree表示审核不通过
			Long id = $.getRequest("id");
			csUnitOrder = csUnitOrderService.getCsUnitOrderById(id);
			csSpecialCar = csUnitOrder.get$csuoSpecial();
			if("1".equals(type)){
				if(csUnitOrder.getCsuoState() == 0){
					$.SetTips("订单无需审核！");
				}else if(csUnitOrder.getCsuoState() >= 2){
					$.SetTips("订单已审核过，无再需审核！");
				}else{
					if("agree".equals(str)){
						commonUnitService.executeCheckUnitOrder(true, csUnitOrder.getCsuoId(), csUnitOrder.getCsuoUnitInfo(), csUnitOrder.getCsuoRemark());
					}else if("disagree".equals(str)){					
						commonUnitService.executeCheckUnitOrder(false, csUnitOrder.getCsuoId(), csUnitOrder.getCsuoUnitInfo(), csUnitOrder.getCsuoRemark());
					}
					$.SetTips("审核操作成功！");
				}
			}else if("2".equals(type)){
				if(csUnitOrder.getCsuoState()!= 2){
					$.SetTips("订单使用中或已完成，无法取消！");
				}else{
					commonUnitService.executeCancelUnitOrder(csUnitOrder.getCsuoUnitInfo(), csUnitOrder.getCsuoId(), csUnitOrder.getCsuoRemark());
					$.SetTips("订单取消成功！");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙，请稍后再试！");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	

	public ICsSpecialCarService getCsSpecialCarService() {
		return csSpecialCarService;
	}

	public void setCsSpecialCarService(ICsSpecialCarService csSpecialCarService) {
		this.csSpecialCarService = csSpecialCarService;
	}

	public CsSpecialCar getCsSpecialCar() {
		return csSpecialCar;
	}

	public void setCsSpecialCar(CsSpecialCar csSpecialCar) {
		this.csSpecialCar = csSpecialCar;
	}

	public CsUnitOrder getCsUnitOrder() {
		return csUnitOrder;
	}

	public void setCsUnitOrder(CsUnitOrder csUnitOrder) {
		this.csUnitOrder = csUnitOrder;
	}

	public ICsUnitOrderService getCsUnitOrderService() {
		return csUnitOrderService;
	}

	public void setCsUnitOrderService(ICsUnitOrderService csUnitOrderService) {
		this.csUnitOrderService = csUnitOrderService;
	}

	public ICommonUnitService getCommonUnitService() {
		return commonUnitService;
	}

	public void setCommonUnitService(ICommonUnitService commonUnitService) {
		this.commonUnitService = commonUnitService;
	}
	
	

}
