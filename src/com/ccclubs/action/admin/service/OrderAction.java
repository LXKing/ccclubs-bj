package com.ccclubs.action.admin.service;

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
import com.lazy3q.web.util.Ctrl;
import com.lazy3q.web.helper.ConfigHelper;
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
import com.ccclubs.service.common.impl.OrderInfo;
import com.ccclubs.model.CsOrderLog;
/**************LAZY3Q_CODE_IMPORT**************/


/**
 * 系统订单的Action
 * @author 飞啊飘啊
 * 感谢struts的Action框架
 */
@SuppressWarnings("unchecked")
public class OrderAction
{
	ICsOrderService csOrderService;
	
	CsOrder csOrder;
	
	/**
	 * 系统订单管理首页
	 * @return
	 */	
	public String execute()
	{	
		try{
			java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\.([\\S\\s]+)\\.").matcher($.getServletRequest().getServletPath());
			String alias=(String) (matcher.find()?$.setRequest("alias",matcher.group(1)):null);//别名入口
						
			$.setRequest("modelClass",CsOrder.class);//把当前Action对应的模型类放入到request中,以作为后面导出、、、、等功能的反射使用
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//列表地址参数状态串，查询条件传递到转跳,就是把当前列表页的路径和参数记下来，放入request,在后面添加、编辑时把这个参数entrypoint带上，提交后再返回到当前列表
			//处理页面的视图数据，比如显示哪些列，自定义显示列等
			ActionHelper.doPageViewFields(CsOrder.class);
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> objects = ActionHelper.getQueryFormParams(CsOrder.class);
			$.setRequest("PARAMS",ActionHelper.getQueryFormParams(CsOrder.class));
									
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=CsOrder.C.get($.forMat($.getString("asc")));//升序字段
			objects.put("asc", strAsc);//放入查询条件
			String strDesc=CsOrder.C.get($.forMat($.getString("desc")));//降序字段
			objects.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				objects.put("desc","cso_id");//那么，默认以主键降序，传给Dao层
			
			//把request传过来的查询参数放回request,为了页面能够正确显示上次所选择的值
			Set<String> keys = objects.keySet();
			for(String key:keys)
				$.setRequest(key, objects.get(key));
				
			//把组装好的objects整个Map直接放入request,####原因暂时保密，等你哪天理解出这儿的意思了，你就是高手了
			$.setRequest("objects",objects);
			//强制限制域
			objects.put("HOSTS",SystemHelper.testHost(null));
			
			//反强制限制域
			if(objects.get("csoPayMember")!=null && objects.get("csoPayMemberNot")==null){
				objects.remove("HOSTS");
			}
			if(objects.get("csoUseMember")!=null && objects.get("csoUseMemberNot")==null){
				objects.remove("HOSTS");
			}
			
			if($.getString("csoIds") != null){
				objects.put("definex", "(1=1 and cso_id in ("+$.getString("csoIds").replaceFirst(",", "")+"))");
			}
			
			
			//canQuery：表示当前列表是否具有查询功能，这里只是负责传个参数，具体能查不能查，在页面根据这个参数做处理
			$.setRequest("canQuery", $.getBoolean("canQuery",true));			
			if($.getBoolean("export",false)==true)//如果传入了一个数据导出的参数
			{
				//获取当前用户的权限，看当前用户有没有删除权限
				SystemHelper.getSrvLimit("admin/service/order.do");
				if($.getRequest("canExport")==null || ((Boolean)$.getRequest("canExport"))==false){
					throw new RuntimeException("您没有权限导出该数据");
				}
				final Map<String, Object> finalParams = objects;
				//调用lazy3q.jar中的导出功能
				//参数说明：(这次导出多少条数据，到数据库查的时候一次查多少条，当前所在第几页,处理器，对应的模型类,要导出哪些字段)
				$.export($.getInteger("size",1000), 100, $.getInteger("page",0), new Export(){//new Export这个类，他会根据参数分次查询，边查边写，目的只有一个，就是为了避免把大量的数据放在内存中
					@Override //重载实现Export的getPage方法，Export会分多次调用这个方法
					public Page getPage(Integer page, int size) {//这时的page和size是Export转化过后的当前页，每页查询数					
						return csOrderService.getCsOrderPage(page, size, finalParams);
					}
				}, CsOrder.class, $.getArray("keys"));
				return null;//数据已经被Export直接写入给输出流了，直接返回null,下面的逻辑就不执行了，
			}
			/************LAZY3Q_CODE_EXECUTE************/
			/************LAZY3Q_CODE_EXECUTE************/

						
			//调用Service获取分页
			Page page = csOrderService.getCsOrderPage($.getInteger("page", 0), $.getInteger("size",16), objects);
			//把分页对象放入request
			$.setRequest("page", page);	
			
			//重置请求参数，主要是把里面的百分号去掉一下
			ActionHelper.resetQueryFormParams(objects);
			/************LAZY3Q_AFTER_EXECUTE************/
			/************LAZY3Q_AFTER_EXECUTE************/

			
			//把当前查询到的条数放入request
			objects.put("count",page.getCount());
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "index";
	}
	/**
	 * 系统订单统计
	 * @return
	 */	
	public String stats()
	{	
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//列表地址参数状态串,查询条件传递到转跳,就是把当前列表页的路径和参数记下来，放入request,在后面添加、编辑时把这个参数entrypoint带上，提交后再返回到当前列表
			$.setRequest("modelClass",CsOrder.class);//把当前Action对应的模型类放入到request中,以作为后面导出、、、、等功能的反射使用
			
			if($.getBoolean("export",false)==true){//如果传入了一个数据导出的参数
				javax.servlet.http.HttpServletResponse response = com.lazy3q.xwork.WebContext.getServletResponse();
				response.setCharacterEncoding("UTF-8"); 
				java.io.PrintWriter out = response.getWriter();			
				response.setContentType("application/x-octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode("系统订单统计导出","UTF-8")+".xls");
				out.println($.getString("html"));
				out.println("");
				out.flush();
				return null;//写入给输出流了，直接返回null,下面的逻辑就不执行了，
			}
			
			//处理页面的视图数据，比如显示哪些列，自定义显示列等
			ActionHelper.doPageViewFields(CsOrder.class);
					
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> objects = ActionHelper.getQueryFormParams(CsOrder.class);
				
			//把request传过来的查询参数放回request,为了页面能够正确显示上次所选择的值
			Set<String> keys = objects.keySet();
			for(String key:keys)
				$.setRequest(key, objects.get(key));
				
			//把组装好的objects整个Map直接放入request,####原因暂时保密，等你哪天理解出这儿的意思了，你就是高手了
			$.setRequest("objects",objects);
			//强制限制域
			objects.put("HOSTS",SystemHelper.testHost(null));
			
			//反强制限制域
			if(objects.get("csoPayMember")!=null && objects.get("csoPayMemberNot")==null){
				objects.remove("HOSTS");
			}
			if(objects.get("csoUseMember")!=null && objects.get("csoUseMemberNot")==null){
				objects.remove("HOSTS");
			}
						
			Map<String,Object> groups=new HashMap();//要分组的字段,Dao层会处理，有空去看看
			Map<String,Object> sums=new HashMap();//要统计的字段,Dao层会处理，有空去看看
			
			
			//获取要分组的字段，放入Map中
 			String[] groupNames  =  $.getArray("groupBy");
 			$.setRequest("groupBy", groupNames);
 			List<String> timeTypes = new java.util.ArrayList();
 			if(groupNames!=null){
	 			for(String groupName:groupNames){
	 				String timeType = $.getString(groupName+"TimeType");
		 			if($.empty(timeType))
		 				groups.put(CsOrder.C.get(groupName),"{key}");
		 			else{
		 				groups.put(CsOrder.C.get(groupName),"DATE_FORMAT({key},'"+timeType.split(",")[0]+"')");
		 				$.setRequest("haveTimeType",true);
		 				timeTypes.add(groupName);
		 				if(timeType.startsWith("%Y"))
		 					objects.put("asc", CsOrder.C.get(groupName));
		 			}
		 			$.setRequest(groupName+"Group",true);
	 			}
	 			$.setRequest("groupCount", groupNames.length);
 			}else{
 				$.setRequest("groupCount", 0);
 			}
 			$.setRequest("timeTypes", timeTypes);
 			 		
 			 	
 			//获取要统计的字段，放入Map中
 			String[] sumNames = $.getArray("sumBy");
 			$.setRequest("sumBy", sumNames);
 			if(sumNames!=null){
	 			for(int i=0;i<sumNames.length;i++){
	 				String sumName=sumNames[i];
	 				if(sumName.indexOf(":")==-1){
		 				sums.put(CsOrder.C.get(sumName),"sum({key})");
		 				$.setRequest(sumName+"Sum",true);
	 				}else{//如果统计字段中带有逗号，那么则认为是自定义统计
	 					sums.put(sumName.split(":")[0],sumName.split(":")[1]);
		 				$.setRequest(sumName.split(":")[0]+"Sum",true);
	 				}
	 			}
	 			$.setRequest("sumCount", sumNames.length);
 			}else{
 				$.setRequest("sumCount", 0);
 			}
 			/******************LAZY3Q_CODE_STATS*******************/
 			Map dates = com.ccclubs.helper.UtilHelper.getDateTimeAreas();
 			$.SetRequest("dates", dates);
 			
 			Boolean netincome = $.getBoolean("netincome",false);
 			Date csoStartTimeStart = (Date) objects.get(CsOrder.F.csoStartTime+"Start");
 			Date csoStartTimeEnd = (Date) objects.get(CsOrder.F.csoStartTime+"End");
 			Date csoFinishTimeStart = (Date) objects.get(CsOrder.F.csoFinishTime+"Start");
 			Date csoFinishTimeEnd = (Date) objects.get(CsOrder.F.csoFinishTime+"End");
			if(netincome==true){
				if((csoStartTimeStart==null && csoStartTimeEnd==null)
						||(csoFinishTimeStart==null && csoFinishTimeEnd==null)){
					$.SetRequest("netincome", false);
				}else{
					long max = Long.MIN_VALUE;//最大时间
					long min = Long.MAX_VALUE;//最小时间
					if(csoStartTimeStart!=null){
						max = Math.max(max, csoStartTimeStart.getTime());
						min = Math.min(min, csoStartTimeStart.getTime());
					}
					if(csoStartTimeEnd!=null){
						max = Math.max(max, csoStartTimeEnd.getTime());
						min = Math.min(min, csoStartTimeEnd.getTime());
					}
					if(csoFinishTimeStart!=null){
						max = Math.max(max, csoFinishTimeStart.getTime());
						min = Math.min(min, csoFinishTimeStart.getTime());
					}
					if(csoFinishTimeEnd!=null){
						max = Math.max(max, csoFinishTimeEnd.getTime());
						min = Math.min(min, csoFinishTimeEnd.getTime());
					}
					max = max/1000;//转成时间戳
					min = min/1000;//转成时间戳
					
					String factor="(LEAST(unix_timestamp(cso_finish_time),{max})-GREATEST(unix_timestamp(cso_start_time),{min}))/(unix_timestamp(cso_finish_time)-unix_timestamp(cso_start_time))";
					factor = factor.replace("{max}", ""+max).replace("{min}", ""+min);					
					
		 			for(Map.Entry<String, Object> item : sums.entrySet())
		 				if(item.getKey()!=null)
		 					sums.put(item.getKey(), item.getValue().toString().replace("{key}", "{key} * "+factor));
		 			
		 			$.SetRequest("netincome", true);
				}
			}
			if($.getBoolean("notrevenue",false)==true){//排除非营收会员支付的订单
				objects.put("CsMember", $.add("csmNotRevenue", 1).add("csmNotRevenueNot", "not").add("on", "cso_pay_member"));
			}
 			/******************LAZY3Q_CODE_STATS*******************/

						
			//调用Service获取统计数据
			List list = csOrderService.getCsOrderStats(objects,groups,sums);
			$.setRequest("list", list);
			
			//重置请求参数，主要是把里面的百分号去掉一下
			ActionHelper.resetQueryFormParams(objects);
					
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "stats";
	}	
	
	
	
	/**
	 * 系统订单添加/编辑页面
	 * @return
	 */
	public String edit()
	{		
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//编辑页状态串(包含进入编辑页面时的地址和参数)
			$.setRequest("entrypoint", $.getString("entrypoint"));//列表地址参数状态串，由列表页生产，这里只是携带参数，跟快递差不多
			$.setRequest("ids",$.getString("ids"));//批量编辑的数据ID，多个ID以,号分隔
			$.setSession("csOrderToken", $.uuid());//防重复提交Token
			//当前要编辑的数据ID，有这个ID表示修改数据，没这个ID表示添加数据
			Long id=$.getLong("id");
			if(id!=null){
				//把当前要修改的数据查出来,注意了，csOrder这个对象为什么不用$.setRequest,因为csOrder有get,set方法，由struts处理的
				csOrder=csOrderService.getCsOrderById(id);
				//确认域标识
				if(SystemHelper.testHost(csOrder.getCsoHost()).equals("0")){
					return $.SendHtml("<script>alert('对不起，你没有权限编辑该数据');</script>", "UTF-8");
				}
			}
			if(csOrder==null)
				csOrder = new CsOrder();
			//根据自定义配置ctrl中配置的默认值信息设置默认值
			if(CTRL!=null)
				CTRL.setObjectDefaultValue(csOrder);
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
	 * 数据事件触发
	 * @param type 动作类型
	 * @param object 操作的对象
	 * 如果发生错误，不抛出，尽量避免影响主业务
	 * @return
	 */
	public void on(String type,CsOrder object,CsOrder old){
		try{
			//执行后台配置的动态代码脚本，如果有在后台配置的话(*_*)
			ActionHelper.executeActionScript(CsOrder.class, type, object, old);
			/************LAZY3Q_ACTION_ON************/
			/************LAZY3Q_ACTION_ON************/

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	/**
	 * 保存系统订单
	 * @return
	 */
	public String save()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//主键为空，表示插入,否则表示更新
			final Boolean bIsAdd = ($.empty($.getString("ids")) && $.empty($.getString("PARAMS")) && csOrder.getCsoId()==null);
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsOrderToken = $.getString("csOrderToken");
			final String sessionCsOrderToken = $.getSession("csOrderToken");
			if($.equals(requestCsOrderToken,sessionCsOrderToken)){
				$.removeSession("csOrderToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csOrderService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						/************LAZY3Q_BEFOR_SAVE************/
						/************LAZY3Q_BEFOR_SAVE************/

						if(bIsAdd){
							//批量插入处理
							String vValue = ""+(csOrder.getCsoId()==null?"":csOrder.getCsoId());
							String sArray[]={""+vValue};
							if(vValue.startsWith("[*]")){
								vValue=vValue.replace("[*]","");
								sArray=vValue.split("[\\s|\u3000]+");				
							}
							for(String sItem:sArray){
								if(!$.empty(sItem))
								csOrder.setCsoId(Long.valueOf(sItem));
							
								//设置默认值
								if(csOrder.getCsoHost()==null)
								csOrder.setCsoHost((long)0);
								if(csOrder.getCsoArea()==null)
								csOrder.setCsoArea((long)0);
								if(csOrder.getCsoModel()==null)
								csOrder.setCsoModel((long)0);
								if(csOrder.getCsoProvid()==null)
								csOrder.setCsoProvid((long)0);
								if(csOrder.getCsoCarNumber()==null)
								csOrder.setCsoCarNumber("");
								if(csOrder.getCsoEvRfid()==null)
								csOrder.setCsoEvRfid("");
								if(csOrder.getCsoLongOrder()==null)
									csOrder.setCsoLongOrder(false);
								if(csOrder.getCsoDuration()==null)
								csOrder.setCsoDuration((double)0);
								if(csOrder.getCsoFuel()==null)
								csOrder.setCsoFuel((double)0);
								if(csOrder.getCsoElectric()==null)
								csOrder.setCsoElectric((double)0);
								if(csOrder.getCsoFrom()==null)
								csOrder.setCsoFrom((short)0);
								if(csOrder.getCsoUpdateTime()==null)
								csOrder.setCsoUpdateTime(new Date());
								if(csOrder.getCsoAddTime()==null)
								csOrder.setCsoAddTime(new Date());
								if(csOrder.getCsoCode()==null)
								csOrder.setCsoCode("");
								if(csOrder.getCsoEditor()==null)
								csOrder.setCsoEditor((Long) $.getSession("ccclubs_login_id"));
								if(csOrder.getCsoMask()==null)
									csOrder.setCsoMask(0l);
								if(csOrder.getCsoDeviceVesion()==null)
									csOrder.setCsoDeviceVesion((short)0);
								if(csOrder.getCsoState()==null)
								csOrder.setCsoState((short)0);
								if(csOrder.getCsoStatus()==null)
								csOrder.setCsoStatus((short)0);
								
								//根据自定义的默认值信息设置默认值
								if(CTRL!=null)
									CTRL.setObjectDefaultValue(csOrder);
								//添加域标识
								if(SystemHelper.testHost(csOrder.getCsoHost()).equals("0")){
									throw new RuntimeException("非法指定域值");
								}
								
								csOrder=csOrderService.saveCsOrder(csOrder);			
								
								on("save",csOrder,csOrder);
							}							
							
							csOrderService.updateCsOrder$NotNull(csOrder);						
							/************LAZY3Q_CODE_ADD************/
							/************LAZY3Q_CODE_ADD************/

							
							//记录操作日志
							LoggerHelper.writeLog(CsOrder.class,"add","添加了[系统订单]["+csOrder.getCsoId()+"]",(Long)$.getSession("ccclubs_login_id"), csOrder,csOrder.getCsoId());
							$.SetTips("保存系统订单成功");
						}else{
							String PARAMS = $.getString("PARAMS");
							String ids=$.getString("ids");
							if(!$.empty(PARAMS)){//根据查询条件批量更新
								Map params = $.eval(PARAMS);
								params.put("confirm", 1);
								params.put("definex","1=1");
								csOrderService.updateCsOrderByConfirm(ActionHelper.getSetValuesFromModel(csOrder), params);
								LoggerHelper.writeLog(CsOrder.class, "update", 
											"批量修改了[系统订单]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(null,csOrder,false)
											,null);
							}else if(!$.empty(ids)){//根据ids批量更新
								String[] array = ids.split(",");
								for(String sid:array){
									csOrder.setCsoId(Long.valueOf(sid));
									CsOrder oldCsOrder = CsOrder.get(Long.valueOf(sid));
									csOrderService.updateCsOrder$NotNull(csOrder);
									LoggerHelper.writeLog(CsOrder.class, "update", 
											"修改了[系统订单]["+oldCsOrder.getCsoId()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsOrder, csOrder,false)
											,Long.valueOf(sid));
									on($.getString("method"),csOrder,oldCsOrder);
								}
							}else{
								if($.empty($.getString("edittype"))){
									//设置填充值
									
									CsOrder oldCsOrder = CsOrder.get(csOrder.getCsoId());
									csOrderService.updateCsOrder$NotNull(csOrder);
									LoggerHelper.writeLog(CsOrder.class, "update", 
											"修改了[系统订单]["+oldCsOrder.getCsoId()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsOrder, csOrder,false)
											,csOrder.getCsoId());
									on($.getString("method"),csOrder,oldCsOrder);									
								}else{
									CsOrder oldCsOrder = csOrderService.getCsOrderById(csOrder.getCsoId());
									//设置默认值
									csOrder.setCsoHost(oldCsOrder.getCsoHost());	
									csOrder.setCsoPayMember(oldCsOrder.getCsoPayMember());	
									csOrder.setCsoUseMember(oldCsOrder.getCsoUseMember());	
									csOrder.setCsoFeeType(oldCsOrder.getCsoFeeType());	
									csOrder.setCsoArea(oldCsOrder.getCsoArea());	
									csOrder.setCsoOutlets(oldCsOrder.getCsoOutlets());	
									csOrder.setCsoOutletsRet(oldCsOrder.getCsoOutletsRet());	
									csOrder.setCsoModel(oldCsOrder.getCsoModel());	
									csOrder.setCsoCar(oldCsOrder.getCsoCar());	
									csOrder.setCsoProvid(oldCsOrder.getCsoProvid());	
									csOrder.setCsoCarNumber(oldCsOrder.getCsoCarNumber());	
									csOrder.setCsoMobile(oldCsOrder.getCsoMobile());	
									csOrder.setCsoEvRfid(oldCsOrder.getCsoEvRfid());	
									csOrder.setCsoIsAdCar(oldCsOrder.getCsoIsAdCar());	
									csOrder.setCsoStartTime(oldCsOrder.getCsoStartTime());	
									csOrder.setCsoFinishTime(oldCsOrder.getCsoFinishTime());	
									csOrder.setCsoLongOrder(oldCsOrder.getCsoLongOrder());	
									csOrder.setCsoLongPrice(oldCsOrder.getCsoLongPrice());	
									csOrder.setCsoUseType(oldCsOrder.getCsoUseType());	
									csOrder.setCsoTakeTime(oldCsOrder.getCsoTakeTime());	
									csOrder.setCsoRetTime(oldCsOrder.getCsoRetTime());	
									csOrder.setCsoDuration(oldCsOrder.getCsoDuration());	
									csOrder.setCsoFuel(oldCsOrder.getCsoFuel());	
									csOrder.setCsoElectric(oldCsOrder.getCsoElectric());	
									csOrder.setCsoCreditCard(oldCsOrder.getCsoCreditCard());	
									csOrder.setCsoCreditBill(oldCsOrder.getCsoCreditBill());	
									csOrder.setCsoInsureType(oldCsOrder.getCsoInsureType());	
									csOrder.setCsoFreehour(oldCsOrder.getCsoFreehour());	
									csOrder.setCsoRebate(oldCsOrder.getCsoRebate());	
									csOrder.setCsoMarginNeed(oldCsOrder.getCsoMarginNeed());	
									csOrder.setCsoPredict(oldCsOrder.getCsoPredict());	
									csOrder.setCsoPayNeed(oldCsOrder.getCsoPayNeed());	
									csOrder.setCsoPayReal(oldCsOrder.getCsoPayReal());	
									csOrder.setCsoPayMoney(oldCsOrder.getCsoPayMoney());	
									csOrder.setCsoPayCoupon(oldCsOrder.getCsoPayCoupon());	
									csOrder.setCsoPayCoin(oldCsOrder.getCsoPayCoin());	
									csOrder.setCsoPayKilom(oldCsOrder.getCsoPayKilom());	
									csOrder.setCsoPayMileage(oldCsOrder.getCsoPayMileage());	
									csOrder.setCsoPayRent(oldCsOrder.getCsoPayRent());	
									csOrder.setCsoPayInsure(oldCsOrder.getCsoPayInsure());	
									csOrder.setCsoPayTimeout(oldCsOrder.getCsoPayTimeout());	
									csOrder.setCsoPayDriver(oldCsOrder.getCsoPayDriver());	
									csOrder.setCsoIncome(oldCsOrder.getCsoIncome());	
									csOrder.setCsoUseIndex(oldCsOrder.getCsoUseIndex());	
									csOrder.setCsoFlag(oldCsOrder.getCsoFlag());	
									csOrder.setCsoFrom(oldCsOrder.getCsoFrom());	
									csOrder.setCsoSrc(oldCsOrder.getCsoSrc());	
									csOrder.setCsoUpdateTime(oldCsOrder.getCsoUpdateTime());	
									csOrder.setCsoAddTime(oldCsOrder.getCsoAddTime());	
									csOrder.setCsoEndTime(oldCsOrder.getCsoEndTime());	
									csOrder.setCsoSerial(oldCsOrder.getCsoSerial());	
									csOrder.setCsoCode(oldCsOrder.getCsoCode());	
									csOrder.setCsoLog(oldCsOrder.getCsoLog());	
									csOrder.setCsoEditor(oldCsOrder.getCsoEditor());	
									csOrder.setCsoMask(oldCsOrder.getCsoMask());	
									csOrder.setCsoDeviceVesion(oldCsOrder.getCsoDeviceVesion());	
									csOrder.setCsoAlarm(oldCsOrder.getCsoAlarm());	
									csOrder.setCsoState(oldCsOrder.getCsoState());	
									csOrder.setCsoStatus(oldCsOrder.getCsoStatus());	
									
									//设置填充值
									//添加域标识
									if(SystemHelper.testHost(csOrder.getCsoHost()).equals("0")){
										throw new RuntimeException("非法指定域值");
									}
									
									//从oldCsOrder同步csOrder未set过的值
									csOrder.mergeSet(oldCsOrder);
											
									csOrderService.updateCsOrder(csOrder);
									LoggerHelper.writeLog(CsOrder.class, "update", 
											"修改了[系统订单]["+oldCsOrder.getCsoId()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsOrder, csOrder,true)
											,csOrder.getCsoId());
									on("update",csOrder,oldCsOrder);
								}
							}
							/************LAZY3Q_CODE_UPDATE************/
							/************LAZY3Q_CODE_UPDATE************/

							//记录操作日志
							$.SetTips("修改系统订单成功");
						}	
						/************LAZY3Q_CODE_SAVE************/
						/************LAZY3Q_CODE_SAVE************/

						
						return null;
					}
				});
				
			}else{
				$.removeSession("csOrderToken");//移除令牌	
				$.SetTips("对不起，请勿重复提交");
			}
			/************LAZY3Q_CODE_RETURN************/
			/************LAZY3Q_CODE_RETURN************/

			
			//从ctrl中获取自定义转跳地址,是不是发现ctrl这个东东很神奇？
			String redirectUrl = CTRL==null ? null : CTRL.getRedirect();
			if(!$.empty(redirectUrl))return $.Redirect(redirectUrl);//如果有转跳地址，下面的逻辑就不执行了
			
			//complete是什么呢，jsonp回调函数，jsonp技术建议百度一下
			String complete = $.getString("complete");
			if(!$.empty(complete)){
				$.removeSession("tips");
				return $.SendHtml("<script>"+complete+"("+$.json(csOrder)+")"+"</script>","UTF-8");
			}
			
			//如果点保存，则回到编辑页，如果点确定，则回到列表页
			//现在知道editorpoint和entrypoint的意义了吗
			//它们由地址加参数(如:http://xxx.xx/xx.xx?xx=xx&xxx=xxx)转成16进制字串进行传递的
			if($.getString("submiter","").indexOf("保存")>-1){//则回到编辑页
				if(bIsAdd)
					return $.Redirect("order_edit.do?entrypoint="+$.getString("entrypoint")+"&id="+csOrder.getCsoId());
				else
					return $.Redirect($.xeh($.getString("editorpoint")));
			}
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		//回到列表页
		return $.Redirect($.empty($.getString("entrypoint"))?$.xeh($.getString("editorpoint")):$.xeh($.getString("entrypoint")));
	}


	/**
	 * 删除系统订单
	 * @return
	 */
	public String del()
	{
		try{
			//获取当前用户的权限，看当前用户有没有删除权限
			SystemHelper.getSrvLimit("admin/service/order.do");
					/************LAZY3Q_CAN_DEL************/
					/************LAZY3Q_CAN_DEL************/

			if($.getRequest("canDel")==null || ((Boolean)$.getRequest("canDel"))==false){
				throw new RuntimeException("您没有权限删除该数据");
			}
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//多个删除数据动作合并事务过程
			csOrderService.executeTransaction(new Function(){
				public <T> T execute(Object... arg0){
			
					Long pid=$.getLong("id");
					Long[] ids=new Long[]{pid};
					String strIds=$.getString( "ids");
					if(!$.empty(strIds) && !strIds.trim().equals("")){//批量删除
						String[] array = strIds.split(",");
						ids=new Long[array.length];
						for(int i=0;i<array.length;i++)
							ids[i]=Long.valueOf(array[i]);
					}		
					for(Long id:ids){
							CsOrder csOrder = csOrderService.getCsOrderById(id);
							//确认域标识
							if(SystemHelper.testHost(csOrder.getCsoHost()).equals("0")){
								throw new RuntimeException("当前用户没有权限删除该数据");
							}
							if(ConfigHelper.GetBoolean("logic_delete")==true)
								csOrderService.removeCsOrderById(id);
							else
							csOrderService.deleteCsOrderById(id);			
							//记录操作日志
							if(csOrder!=null)
								LoggerHelper.writeLog(CsOrder.class,"del","删除了[系统订单]["+csOrder.getCsoId()+"]",(Long)$.getSession("ccclubs_login_id"), csOrder);
							on("delete",csOrder,csOrder);
					}
					/************LAZY3Q_CODE_DEL************/
					/************LAZY3Q_CODE_DEL************/

					
					return null;
				}
			});
			
			$.SetTips("删除系统订单成功");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			if(e.getMessage().indexOf("没有权限删除该数据")>-1)
				return $.SendHtml("<script>alert('对不起，你没有权限删除该数据');</script>", "UTF-8");
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}	

	/**
	 * 显示系统订单详情
	 * @return
	 */
	public String details()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			Long id=$.getLong("id");
			Long key=$.getLong("key");
			if(id!=null)
				csOrder=csOrderService.getCsOrderById(id);
			if(key!=null && id==null)
				csOrder=CsOrder.Get($.add(CsOrder.F.csoId,key));
			//确认域标识
			if(SystemHelper.testHost(csOrder.getCsoHost()).equals("0")){
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
	 * 根据订单编号或ID查询系统订单
	 * 返回ajax数据
	 * @return
	 */
	public String query(){
		try{
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> params = ActionHelper.getQueryFormParams(CsOrder.class);
			//强制限制域
			params.put("HOSTS",SystemHelper.testHost(null));
			
			//反强制限制域
			if(params.get("csoPayMember")!=null && params.get("csoPayMemberNot")==null){
				params.remove("HOSTS");
			}
			if(params.get("csoUseMember")!=null && params.get("csoUseMemberNot")==null){
				params.remove("HOSTS");
			}
			
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=$.getString("asc");//升序字段
			params.put("asc", strAsc);//放入查询条件
			String strDesc=$.getString("desc");//降序字段
			params.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				params.put("desc","cso_id");//那么，默认以主键降序，传给Dao层
								
			String strTerm=$.getString("value");//智能搜索时的参数，一般都是主键后面那个字段
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("csoId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or cso_id like '"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex+=" or cso_status like '"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}
			/************LAZY3Q_CODE_QUERY************/
			/************LAZY3Q_CODE_QUERY************/

			
			//是否需要整个数据对象
			Boolean bObject=$.getBoolean("object",false);
			
			List<CsOrder> list = csOrderService.getCsOrderPage(0,$.getInteger("size",10),params).getResult();
			
			/**
			* OK!取到数据拼成放入Map中，[{value,text,object:{...}},...]
			* value:数据ID，text:数据标识,object,整个对象
			**/
			List<Map> result = new java.util.ArrayList();
			for(CsOrder csOrder:list){
				Map map = new HashMap();
				map.put("value",csOrder.getCsoId().toString());
				map.put("text", $.js(csOrder.getKeyValue()));
				if(bObject==true)
					map.put("object",csOrder);
				result.add(map);
			}
			/************LAZY3Q_AFTER_QUERY************/
			/************LAZY3Q_AFTER_QUERY************/

			
			//$.SendAjax这个函数，第一个参数不是字符串，会自动把第一个对象转成json格式的字符串
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
	 * 订单计算器
	 * @return
	 */
	public String calculator(){
		$.SetRequest("define", "order_calculator.jsp");
		return "define";
	}
	/**
	 * 添加订单
	 */
	public synchronized String add(){
		try{
			final Ctrl ctrl = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			Double defineMargin = $.getDouble("defineMargin");
			
			String requestCsOrderToken = $.getString("csOrderToken");
			String sessionCsOrderToken = $.getSession("csOrderToken");
			//if($.equals(requestCsOrderToken,sessionCsOrderToken)){
				$.removeSession("csOrderToken");//移除令牌		
				com.ccclubs.service.common.ICommonDisposeService commonDisposeService=$.GetSpringBean("commonDisposeService");
				csOrder = commonDisposeService.executeSaveOrder(
						csOrder.getCsoPayMember(), 
						csOrder.getCsoUseMember(), 
						csOrder.getCsoCar(), 
						csOrder.getCsoOutlets(),
						csOrder.getCsoOutletsRet(),
						csOrder.getCsoStartTime(), 
						csOrder.getCsoFinishTime(),	
						csOrder.getCsoFeeType(),
						csOrder.getCsoInsureType(), 
						csOrder.getCsoLongPrice(),
						csOrder.getCsoFreehour(),
						defineMargin!=null ? defineMargin : csOrder.getCsoMarginNeed(),
						LoginHelper.getLoginId(),
						com.ccclubs.service.common.From.后台,
						null
				);
				//记录操作日志
				LoggerHelper.writeLog(CsOrder.class,"update","添加了[订单]["+csOrder.getCsoId()+"]",(Long)$.getSession("ccclubs_login_id"), "添加了订单" ,csOrder.getCsoId());
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
			final Ctrl ctrl = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			com.ccclubs.service.common.ICommonDisposeService commonDisposeService=$.GetSpringBean("commonDisposeService");
			commonDisposeService.executeReOrder(csOrder.getCsoId(), csOrder.getCsoFinishTime(), csOrder.getCsoFreehour(),com.ccclubs.service.common.From.后台,null);
			//记录操作日志
			LoggerHelper.writeLog(CsOrder.class,"update","续订[订单]["+csOrder.getCsoId()+"]",(Long)$.getSession("ccclubs_login_id"), "续订了订单" , csOrder.getCsoId());
			$.SetTips("续订订单成功");
		}catch(com.ccclubs.exception.MessageException oe){
			$.SetTips(oe.getMessage());	
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.empty($.getString("entrypoint"))?$.xeh($.getString("editorpoint")):$.xeh($.getString("entrypoint")));
	}
	/**
	 * 结算订单
	 * @return
	 */
	public String settle(){
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
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
					csOrder.getCsoElectric(),com.ccclubs.service.common.From.后台,null);
			
			//记录操作日志
			LoggerHelper.writeLog(CsOrder.class,"update","结算[订单]["+csOrder.getCsoId()+"]",(Long)$.getSession("ccclubs_login_id"), "结算了订单" , csOrder.getCsoId());
			
			$.SetTips("订单结算成功");	
		}catch(com.ccclubs.exception.MessageException oe){
			$.SetTips(oe.getMessage());	
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.empty($.getString("entrypoint"))?$.xeh($.getString("editorpoint")):$.xeh($.getString("entrypoint")));
	}
	/**
	 * 取消订单
	 * @return
	 */
	public String cancel(){
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
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
			commonDisposeService.executeCancelOrder(csOrder.getCsoId(),csOrder.getCsoRemark(),com.ccclubs.service.common.From.后台,null);
			
			//记录操作日志
			LoggerHelper.writeLog(CsOrder.class,"update","取消[订单]["+csOrder.getCsoId()+"]",(Long)$.getSession("ccclubs_login_id"), "取消了订单" , csOrder.getCsoId());
			
			$.SetTips("订单取消成功");
		}catch(com.ccclubs.exception.MessageException oe){
			$.SetTips(oe.getMessage());	
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.empty($.getString("entrypoint"))?$.xeh($.getString("editorpoint")):$.xeh($.getString("entrypoint")));
	}
	/**
	 * 待处理订单
	 * @return
	 */
	public String pend(){
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
						
			CsOrder oldOrder = csOrderService.getCsOrderById(csOrder.getCsoId());
			short status = oldOrder.getCsoStatus().shortValue();
			if(status>2){
				$.SetTips("只有已预订、正在执行、已还车的订单才能进行待处理操作");
				return $.Redirect($.xeh($.getString("entrypoint")));
			}
			
			new CsOrderLog(//添加订单日志
				 	"订单修改为待处理"//操作项目 [非空]
				 	,csOrder.getCsoId()//所属订单 [非空]
				 	,LoginHelper.getLoginId()//操作人 [非空]
				 	,null
				 	,(short)com.ccclubs.service.common.From.后台.ordinal()
				 	,null
				 	,csOrder.getCsoRemark()//备注信息
				 	,new Date()//添加时间 [非空]
				 ).save();
			
			csOrderService.updateCsOrder$NotNull(
					new CsOrder().csoId(csOrder.getCsoId())
					.csoStatus((short)5)					
			);
			$.SetTips("订单待处理成功");			
						
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.empty($.getString("entrypoint"))?$.xeh($.getString("editorpoint")):$.xeh($.getString("entrypoint")));
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
					csOrder.getCsoOutlets(),
					csOrder.getCsoOutletsRet(),
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
			
			String strError = "";
			for(String sError:orderinfo.errors)
				strError+=($.empty(sError)?"":",")+sError;
			
			return $.SendAjax(
					$.add("margin", orderinfo.getMargin())
					.add("price", orderinfo.getPrice())
					.add("cancoin", orderinfo.getCancoin())
					.add("usecoin", orderinfo.getUsecoin())
					.add("feeee", orderinfo.getFeeee())
					.add("freehour", orderinfo.getFreehour())
					.add("predict", orderinfo.getFeeee())
					.add("longorder", orderinfo.getLongorder())
					.add("feetype", orderinfo.getFeetype())
					.add("conflict", orderinfo.getConflict())
					.add("error", strError)
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
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
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
				.csoStatus((short)0)
				.csoState((short)0)
			);
			
			new CsOrderLog(//添加订单日志
				 	"订单重发"//操作项目 [非空]
				 	,csOrder.getCsoId()//所属订单 [非空]
				 	,LoginHelper.getLoginId()//操作人 [非空]
				 	,null
				 	,(short)com.ccclubs.service.common.From.后台.ordinal()
				 	,null
				 	,csOrder.getCsoRemark()//备注信息
				 	,new Date()//添加时间 [非空]
				 ).save();
			
			//记录操作日志
			LoggerHelper.writeLog(CsOrder.class,"update","撤销[订单]["+csOrder.getCsoId()+"]",(Long)$.getSession("ccclubs_login_id"), "撤销了订单" , csOrder.getCsoId());
			
			$.SetTips("重发订单成功");
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.empty($.getString("entrypoint"))?$.xeh($.getString("editorpoint")):$.xeh($.getString("entrypoint")));
	}
	/**
	 * 撤销订单
	 * @return
	 */
	public String undo(){
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
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
			commonDisposeService.executeUndoOrder(csOrder.getCsoId(),csOrder.getCsoRemark(),com.ccclubs.service.common.From.后台,null);
			
			//记录操作日志
			LoggerHelper.writeLog(CsOrder.class,"update","撤销[订单]["+csOrder.getCsoId()+"]",(Long)$.getSession("ccclubs_login_id"), "撤销了订单" , csOrder.getCsoId());
			
			$.SetTips("撤销订单成功");
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.empty($.getString("entrypoint"))?$.xeh($.getString("editorpoint")):$.xeh($.getString("entrypoint")));
	}
	/**
	 * 回滚订单
	 * @return
	 */
	public String rollback(){
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
						
			CsOrder oldOrder = csOrderService.getCsOrderById(csOrder.getCsoId());
			short status = oldOrder.getCsoStatus().shortValue();
			if(status!=4){
				$.SetTips("只有已完成的订单才能撤销");
				return $.Redirect($.xeh($.getString("entrypoint")));
			}
			
			com.ccclubs.service.common.ICommonDisposeService commonDisposeService=$.GetSpringBean("commonDisposeService");
			commonDisposeService.executeRollbackOrder(csOrder.getCsoId(),csOrder.getCsoRemark(),com.ccclubs.service.common.From.后台,null);
			
			//记录操作日志
			LoggerHelper.writeLog(CsOrder.class,"update","回滚[订单]["+csOrder.getCsoId()+"]",(Long)$.getSession("ccclubs_login_id"), "撤销了订单" , csOrder.getCsoId());
			
			$.SetTips("回滚订单成功");
		}catch(com.ccclubs.exception.MessageException oe){
			$.SetTips(oe.getMessage());	
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.empty($.getString("entrypoint"))?$.xeh($.getString("editorpoint")):$.xeh($.getString("entrypoint")));
	}
	
	public String changeOutlets(){
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
						
			CsOrder oldOrder = csOrderService.getCsOrderById(csOrder.getCsoId());
			if(oldOrder.getCsoStatus() != 0 && oldOrder.getCsoStatus() != 1){
				$.SetTips("只有使用中或者已预订订单可更改还车网点");
				return $.Redirect($.xeh($.getString("entrypoint")));
			}
			
			if(oldOrder.getCsoOutlets() == oldOrder.getCsoOutletsRet()){
				$.SetTips("只有异地借还订单可以更改还车网点");
				return $.Redirect($.xeh($.getString("entrypoint")));
			}
			csOrderService.updateCsOrder$NotNull(csOrder);
			//记录操作日志
			LoggerHelper.writeLog(CsOrder.class,"update","修改["+csOrder.getCsoId()+"]",(Long)$.getSession("ccclubs_login_id"), "还车网点"+oldOrder.getCsoOutletsRet()+"更新到"+csOrder.getCsoOutletsRet() , csOrder.getCsoId());
			
			$.SetTips("还车网点修改成功");
		}catch(com.ccclubs.exception.MessageException oe){
			$.SetTips(oe.getMessage());	
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.empty($.getString("entrypoint"))?$.xeh($.getString("editorpoint")):$.xeh($.getString("entrypoint")));

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