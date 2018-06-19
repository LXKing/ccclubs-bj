package com.ccclubs.action.unit;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ccclubs.action.app.official.util.ThirdPartyApiHelper;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.exception.MessageException;
import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.helper.UnitHelper;
import com.ccclubs.helper.UtilHelper;
import com.ccclubs.helper.UnitHelper.UnitOrderInfo;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsRemote;
import com.ccclubs.model.CsSpecialCar;
import com.ccclubs.model.CsUnitFee;
import com.ccclubs.model.CsUnitInfo;
import com.ccclubs.model.CsUnitOrder;
import com.ccclubs.model.CsOrder;
import com.ccclubs.model.CsUnitPerson;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsRemoteService;
import com.ccclubs.service.admin.ICsUnitFeeService;
import com.ccclubs.service.admin.ICsUnitOrderService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.common.From;
import com.ccclubs.service.common.ICommonDisposeService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ICommonOrderService;
import com.ccclubs.service.common.ICommonUnitService;
import com.ccclubs.service.common.ICommonUtilService.SMSType;
import com.ccclubs.service.common.impl.CommonDisposeService;
import com.ccclubs.service.common.impl.CommonUnitService;
import com.ccclubs.service.common.impl.OrderInfo;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.ControllerHelper;
import com.lazy3q.web.helper.WebHelper.LzMap;
import com.lazy3q.web.util.Page;
import com.opensymphony.xwork2.ActionContext;

public class OrderAction {
	
	ICommonOrderService commonOrderService;
	ICommonMoneyService commonMoneyService;
	ICommonDisposeService commonDisposeService;
	ICsUnitOrderService csUnitOrderService;
	ICommonUnitService commonUnitService;
	ICsOrderService csOrderService;
	CsUnitOrder csUnitOrder;	
	
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
 			objects.put(CsUnitOrder.F.csuoNumber, $.getString(CsUnitOrder.F.csuoNumber));
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
 			objects.put(CsUnitOrder.F.csuoType,$.getString(CsUnitOrder.F.csuoType));
 			objects.put(CsUnitOrder.F.csuoState, $.getShort(CsUnitOrder.F.csuoState));
 			
 			
			
			//取不包含的条件字段
 			String[] strNots = $.getArray("Not");
 			if(strNots!=null){
	 			for(String strNot:strNots)
	 				objects.put(strNot+"Not", "not");
 			}
			
			//取排序参数
			String strAsc=$.getString(requests, "asc");
			objects.put("asc", strAsc);
			String strDesc=$.getString(requests, "desc");
			objects.put("desc",strDesc);
			if($.empty(strAsc) && $.empty(strDesc))
				objects.put("desc","csuo_id");
			
			//把request传过来的查询参数放回request
			Set<String> keys = objects.keySet();
			for(String key:keys)
				$.SetRequest(key, objects.get(key));
			
			$.SetRequest("all", $.getBoolean(requests, "all"));		
			
			$.SetRequest("canQuery", $.getBoolean("canQuery",true));
						
			Page page = csUnitOrderService.getCsUnitOrderPage($.getInteger(requests, "page", 0), $.getInteger("size",16), objects);
			//把分页对象放入request
			$.SetRequest("page", page);	
		}catch(Exception ex){
			ex.printStackTrace();			
		}
		
		return "index";
	}
	

	
	/**
	 * 酒店订单添加/编辑页面
	 * @return
	 */
	public String edit()
	{		
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
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";
	}
	
	
	
	/**
	 * 添加订单
	 * @return
	 */
	public String add(){
		try{
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csUnitOrder);//进入之前的预处理	
			$.SetRequest("controller",controller);
			
			final Boolean bNotSendSms = $.getBoolean("checkbox",false);
			
			String requestCsOrderToken = $.getString("csUnitOrderToken");
			String sessionCsOrderToken = $.getSession("csUnitOrderToken");
			if($.equals(requestCsOrderToken,sessionCsOrderToken)){
				$.removeSession("csUnitOrderToken");//移除令牌
				//多个写数据动作合并事务过程
				csUnitOrderService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						
						CsUnitInfo unitInfo = UnitLoginHelper.getLogin().get$csuuUnitInfo();
						Long csuoUnitMember = UnitHelper.getPayIdByUseId(unitInfo.getCsuiId(),csUnitOrder.getCsuoUnitChild());
						CsUnitPerson csUnitPerson = CsUnitPerson.where().csupInfo(UnitLoginHelper.getLogin().getCsuuUnitInfo()).csupMember(csUnitOrder.getCsuoUnitChild()).get();
						
						$.setLocal(ICommonDisposeService.DONOTSENDSMS, true);
						csUnitOrder = commonUnitService.executeAddUnitOrder(
							unitInfo.getCsuiId(),csUnitPerson==null?null:csUnitPerson.getCsupId(), csUnitOrder.getCsuoUnitChild(),
							csUnitOrder.getCsuoUsername(),csUnitOrder.getCsuoMobile(), 
							csUnitOrder.getCsuoProfile(), csUnitOrder.getCsuoIdcard(),
							csUnitOrder.getCsuoCar(), csUnitOrder.getCsuoStartTime(), csUnitOrder.getCsuoFinishTime(), 
							csUnitOrder.getCsuoType(), 
							csUnitOrder.getCsuoDayPrice(), csUnitOrder.getCsuoHourPrice(), csUnitOrder.getCsuoKmPrice()
							,null,null
						);
						
						return null;
					}
				});				
				$.SetTips("保存订单成功");
			}else{
				$.SetTips("对不起，请勿重复提交");
			}
		}catch(com.ccclubs.exception.MessageException oe){
			$.SetTips(oe.getMessage());	
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.empty($.getString("entrypoint"))?"/unit/order.do":$.xeh($.getString("entrypoint")));
	}
	
	/**
	 * 续订订单
	 * @return
	 */
	public String reorder(){
		try{
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csUnitOrder);//进入之前的预处理	
			$.SetRequest("controller",controller);
			
			String requestCsOrderToken = $.getString("csUnitOrderToken");
			String sessionCsOrderToken = $.getSession("csUnitOrderToken");
			if($.equals(requestCsOrderToken,sessionCsOrderToken)){
				$.removeSession("csUnitOrderToken");//移除令牌
				//多个写数据动作合并事务过程
				csUnitOrderService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){			
						csUnitOrder = OrderAction.this.merge(csUnitOrder);
						
						$.setLocal(ICommonDisposeService.DONOTSENDSMS, true);
						commonDisposeService.executeReOrder(csUnitOrder.getCsuoOrder(), csUnitOrder.getCsuoFinishTime(), null,
								From.企业,
								UnitLoginHelper.getLoginAt());

						UnitOrderInfo info = UnitHelper.getUnitOrderInfo(csUnitOrder.getCsuoUnitInfo(),csUnitOrder.getCsuoCar(), csUnitOrder.getCsuoStartTime(), csUnitOrder.getCsuoFinishTime(), csUnitOrder.getCsuoRetTime(),csUnitOrder.getCsuoFreeHour(),null,null);
						csUnitOrder.setCsuoPayNeed(info.getTotal());						
						
						csUnitOrderService.updateCsUnitOrderByConfirm(
								$.add(CsUnitOrder.F.csuoFinishTime, csUnitOrder.getCsuoFinishTime())
								.add(CsUnitOrder.F.csuoFreeHour, csUnitOrder.getCsuoFreeHour())
								.add(CsUnitOrder.F.csuoPayNeed, info.getTotal()),
								$.add(CsUnitOrder.F.csuoId, csUnitOrder.getCsuoId()).add("confirm", 1)
							);
						
						//记录操作日志
						LoggerHelper.writeLog(CsUnitOrder.class,"update",UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiName()+"续订了[酒店订单][订单]["+csUnitOrder.getCsuoOrder()+"]",0l, null);
						return null;
					}
				});				
				$.SetTips("订单续订成功");
			}else{
				$.SetTips("对不起，请勿重复提交");
			}
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
	 * 撤销订单
	 * @return
	 */
	public String cancel(){
		try{
			final Map controller = $.getJson("controller");
			$.SetRequest("controller",controller);
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
						
			final CsUnitOrder oldOrder = csUnitOrderService.getCsUnitOrderById(csUnitOrder.getCsuoId());			
			CommonUnitService.getBean().executeCancelUnitOrder(oldOrder.getCsuoUnitInfo(), oldOrder.getCsuoId(), csUnitOrder.getCsuoRemark());
			
			$.SetTips("撤销订单成功");
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}	
	
	
	
	/**
	 * 远程还车
	 * @return
	 */
	public String ret(){
		try{
			final Map controller = $.getJson("controller");
			$.SetRequest("controller",controller);
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
						
			final CsUnitOrder oldOrder = csUnitOrderService.getCsUnitOrderById(csUnitOrder.getCsuoId());
			if(oldOrder.get$csuoOrder().getCsoStatus().shortValue()==1){//系统订单未开始执行的可以取消
				//多个写数据动作合并事务过程
				csUnitOrderService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						
						ICsRemoteService csRemoteService = $.getBean("csRemoteService");
						
						CsRemote csRemote = new CsRemote();
						csRemote.setCsrHost(oldOrder.getCsuoHost());
						csRemote.setCsrNumber(oldOrder.get$csuoCar().getCscNumber());
						csRemote.setCsrCar(oldOrder.getCsuoCar());
						csRemote.setCsrEditor(0l);
						csRemote.setCsrAddTime(new Date());
						csRemote.setCsrState((short)2);
						csRemote.setCsrStatus((short)0);			
						csRemote.setCsrType((short)7);
						csRemote.setCsrRemark("");	
						csRemote=csRemoteService.saveCsRemote(csRemote);
						com.ccclubs.service.common.impl.RemoteService.dealRemoteCommend(csRemote);
						
						csOrderService.updateCsOrder$NotNull(
								new CsOrder().csoId(oldOrder.getCsuoOrder())
								.csoRetTime(csUnitOrder.getCsuoRetTime())
								.csoRemark(csUnitOrder.getCsuoRemark())
								.csoStatus((short)2)				
						);			
						
						new CsUnitOrder(csUnitOrder.getCsuoId())
						.csuoRetTime(csUnitOrder.getCsuoRetTime())
						.update();
						
						$.setLocal(ICommonDisposeService.DONOTSENDSMS, true);
						//记录操作日志
						LoggerHelper.writeLog(CsUnitOrder.class,"update",UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiName()+"取消订单[酒店订单][订单]["+csUnitOrder.getCsuoOrder()+"]",0l, null);
						return null;
					}
				});	
			}else{
				$.SetTips("只有在系统订单状态为使用中的订单才能远程还车");
				return $.Redirect($.xeh($.getString("entrypoint")));
			}
			
			$.SetTips("远程还车成功");
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
						
			final CsUnitOrder oldOrder = csUnitOrderService.getCsUnitOrderById(csUnitOrder.getCsuoId());
			
			final Boolean bNotSendSms = $.getBoolean("checkbox",false);
						
			//多个写数据动作合并事务过程
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
					
					//发送结算短信
					if(bNotSendSms==false && !$.empty(csUnitOrder.getCsuoMobile())){
						UtilHelper.sendTemplateSMS(SYSTEM.getDefaultHost(),"SETTLE_UNIT_ORDER",
							csUnitOrder.getCsuoMobile(), 
							"您在{time}还车成功，定单[{order}]时长为[{day}]天[{hour}]小时,里程数[{km}],结算费用为{money}元。[车纷享]", 
							SMSType.通知类短信,
							$.add("time", $.date(csUnitOrder.getCsuoRetTime(), "MM月dd日HH时mm分"))
							.add("order", csUnitOrder.getCsuoOrder().toString())
							.add("money", UnitHelper.$(csUnitOrder.getCsuoPayReal()).toString())
							.add("km", UnitHelper.$(csUnitOrder.getCsuoMileage()).toString())
							.add("day", UnitHelper.$(info.getDay()).toString())
							.add("hour", UnitHelper.$(info.getHour()).toString())
						);
					}
					
					return null;
				}
			});			
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
	 * 审核订单
	 * @return
	 */
	public String check(){
		try{
			final Map controller = $.getJson("controller");
			$.SetRequest("controller",controller);
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
						
			final CsUnitOrder oldOrder = csUnitOrderService.getCsUnitOrderById(csUnitOrder.getCsuoId());
			if(oldOrder.getCsuoState().shortValue()!=1){
				$.SetTips("只有在状态为待审核的订单才能结算");
				return $.Redirect($.xeh($.getString("entrypoint")));
			}
			
			CommonUnitService.getBean().executeCheckUnitOrder($.getString("submiter","").indexOf("审核通过")>-1,csUnitOrder.getCsuoId(), oldOrder.getCsuoUnitInfo(), csUnitOrder.getCsuoRemark());
			
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
	 * 根据车型获取价格
	 * @return
	 */
	public String price(){		
		try{
			ICsUnitFeeService csUnitFeeService = $.getBean("csUnitFeeService");
			ICsCarService csCarService = $.getBean("csCarService");
			CsCar csCar = csCarService.getCsCarById($.getLong("car"));
			CsUnitFee csUnitFee = csUnitFeeService.getCsUnitFee(
				$.add(CsUnitFee.F.csufModel, csCar.getCscModel())
				.add(CsUnitFee.F.csufUnitInfo, UnitLoginHelper.getLogin().getCsuuUnitInfo())
			);
			if(csUnitFee!=null)
				return $.SendAjax(
						$.add("success", true).add("dayPrice", csUnitFee.getCsufDayPrice()).add("hourPrice", csUnitFee.getCsufHourPrice())
					, $.UTF8);
			else
				return $.SendAjax($.add("success", false).add("message", "未配置["+csCar.get$cscModel().getCscmName()+"]价格"), $.UTF8);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax($.add("success", false).add("message", "系统繁忙，请稍候再试"), $.UTF8);
		}
	}
	
	
	
	
	
	/**
	 * 验证订单
	 * @return
	 */
	public String verify(){
		try{
			csUnitOrder = this.merge(csUnitOrder);
			
			CsOrder csOrder = new CsOrder();
			csOrder.setCsoCar(csUnitOrder.getCsuoCar());
			csOrder.setCsoPayMember(UnitHelper.getPayIdByUseId(UnitLoginHelper.getLogin().getCsuuUnitInfo(),csUnitOrder.getCsuoUnitChild()));
			csOrder.setCsoUseMember(csUnitOrder.getCsuoUnitChild());
			csOrder.setCsoStartTime(csUnitOrder.getCsuoStartTime());
			csOrder.setCsoFinishTime(csUnitOrder.getCsuoFinishTime());
			csOrder.setCsoOutlets(csUnitOrder.get$csuoCar().getCscOutlets());	
			csOrder.setCsoId(csUnitOrder.getCsuoOrder());			
			
			csOrder = commonOrderService.merge(csOrder);
			
			if(csUnitOrder.get$csuoUnitChild().get$csmEvcard()==null){
				return $.SendAjax($.add("success", false).add("message", "该子帐号没有绑定会员卡"), $.UTF8);
			}
			
			if(commonOrderService.isExistOrderByTime(csOrder.getCsoCar(), csOrder.getCsoStartTime(), csOrder.getCsoFinishTime(), csOrder.getCsoId())){
				return $.SendAjax($.add("success", false).add("message", "当前订单时间已经被其它订单占用"), $.UTF8);
			}
						
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
					commonOrderService.getProductByFlag(SYSTEM.INSURE).getCspId(),//默认按里程免责
					csOrder.getCsoLongPrice(),
					csOrder.getCsoId()
				);
			
			//检查保证金是否可用
			Double canMoney = commonMoneyService.getUsableAmount(csOrder.getCsoPayMember());			
			//如果当前会员余额不足并且订单不是后台管理员下单，则不允许下单
			if(canMoney<(orderinfo.getMargin()+orderinfo.getPrice())){
				return $.SendAjax($.add("success", false).add("message", "当前帐户可用余额不足"), $.UTF8);
			}
			
			String strError = "";
			for(String sError:orderinfo.errors)
				strError+=($.empty(sError)?"":",")+sError;
						
			Map  result= new HashMap();
			result.put("success", true);
			result.put("canMoney", canMoney);
			
			result.put("data", $.add("margin", orderinfo.getMargin())
					.add("price", orderinfo.getPrice())
					.add("feeee", orderinfo.getFeeee())
					.add("freehour", orderinfo.getFreehour())
					.add("predict", orderinfo.getFeeee())
					.add("longorder", orderinfo.getLongorder())
					.add("feetype", orderinfo.getFeetype())
					.add("conflict", orderinfo.getConflict())
					.add("error", strError));
			
			if(csUnitOrder.getCsuoType().shortValue()==0){
				UnitOrderInfo info = UnitHelper.getUnitOrderInfo(csUnitOrder.getCsuoUnitInfo(),csUnitOrder.getCsuoCar(), csUnitOrder.getCsuoStartTime(), csUnitOrder.getCsuoFinishTime(), csUnitOrder.getCsuoRetTime(),csUnitOrder.getCsuoFreeHour(),csUnitOrder.getCsuoMileage(),csUnitOrder.getCsuoFreeKm());
				result.put("info", info);
			}
			
			return $.SendAjax(result, $.UTF8);		
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax($.add("success", false).add("message", "系统繁忙，请稍候再试"), $.UTF8);
		}
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


	public CsUnitOrder getCsUnitOrder() {
		return csUnitOrder;
	}


	public void setCsUnitOrder(CsUnitOrder csUnitOrder) {
		this.csUnitOrder = csUnitOrder;
	}


	public ICommonOrderService getCommonOrderService() {
		return commonOrderService;
	}


	public void setCommonOrderService(ICommonOrderService commonOrderService) {
		this.commonOrderService = commonOrderService;
	}


	public ICommonMoneyService getCommonMoneyService() {
		return commonMoneyService;
	}


	public void setCommonMoneyService(ICommonMoneyService commonMoneyService) {
		this.commonMoneyService = commonMoneyService;
	}

	public ICommonDisposeService getCommonDisposeService() {
		return commonDisposeService;
	}

	public void setCommonDisposeService(ICommonDisposeService commonDisposeService) {
		this.commonDisposeService = commonDisposeService;
	}



	public ICsOrderService getCsOrderService() {
		return csOrderService;
	}



	public void setCsOrderService(ICsOrderService csOrderService) {
		this.csOrderService = csOrderService;
	}



	public ICommonUnitService getCommonUnitService() {
		return commonUnitService;
	}



	public void setCommonUnitService(ICommonUnitService commonUnitService) {
		this.commonUnitService = commonUnitService;
	}

}
