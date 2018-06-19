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
import com.ccclubs.model.CsTrouble;
import com.ccclubs.service.admin.ICsTroubleService;
/**************LAZY3Q_CODE_IMPORT**************/
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.admin.ICsOrderService;
import com.ccclubs.service.admin.ICsRepairService;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.model.CsRepair;
/**************LAZY3Q_CODE_IMPORT**************/


/**
 * 车辆事故的Action
 * @author 飞啊飘啊
 * 感谢struts的Action框架
 */
@SuppressWarnings("unchecked")
public class TroubleAction
{
	ICsTroubleService csTroubleService;
	
	CsTrouble csTrouble;
	
	/**
	 * 车辆事故管理首页
	 * @return
	 */	
	public String execute()
	{	
		try{
			java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\.([\\S\\s]+)\\.").matcher($.getServletRequest().getServletPath());
			String alias=(String) (matcher.find()?$.setRequest("alias",matcher.group(1)):null);//别名入口
						
			$.setRequest("modelClass",CsTrouble.class);//把当前Action对应的模型类放入到request中,以作为后面导出、、、、等功能的反射使用
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//列表地址参数状态串，查询条件传递到转跳,就是把当前列表页的路径和参数记下来，放入request,在后面添加、编辑时把这个参数entrypoint带上，提交后再返回到当前列表
			//处理页面的视图数据，比如显示哪些列，自定义显示列等
			ActionHelper.doPageViewFields(CsTrouble.class);
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> objects = ActionHelper.getQueryFormParams(CsTrouble.class);
			$.setRequest("PARAMS",ActionHelper.getQueryFormParams(CsTrouble.class));
									
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=CsTrouble.C.get($.forMat($.getString("asc")));//升序字段
			objects.put("asc", strAsc);//放入查询条件
			String strDesc=CsTrouble.C.get($.forMat($.getString("desc")));//降序字段
			objects.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				objects.put("desc","cst_id");//那么，默认以主键降序，传给Dao层
			
			//把request传过来的查询参数放回request,为了页面能够正确显示上次所选择的值
			Set<String> keys = objects.keySet();
			for(String key:keys)
				$.setRequest(key, objects.get(key));
				
			//把组装好的objects整个Map直接放入request,####原因暂时保密，等你哪天理解出这儿的意思了，你就是高手了
			$.setRequest("objects",objects);
			//强制限制域
			objects.put("HOSTS",SystemHelper.testHost(null));
			
			//反强制限制域
			if(objects.get("cstOrder")!=null && objects.get("cstOrderNot")==null){
				objects.remove("HOSTS");
			}
			if(objects.get("cstMember")!=null && objects.get("cstMemberNot")==null){
				objects.remove("HOSTS");
			}
			
			//canQuery：表示当前列表是否具有查询功能，这里只是负责传个参数，具体能查不能查，在页面根据这个参数做处理
			$.setRequest("canQuery", $.getBoolean("canQuery",true));			
			if($.getBoolean("export",false)==true)//如果传入了一个数据导出的参数
			{
				//获取当前用户的权限，看当前用户有没有删除权限
				SystemHelper.getSrvLimit("admin/service/trouble.do");
				if($.getRequest("canExport")==null || ((Boolean)$.getRequest("canExport"))==false){
					throw new RuntimeException("您没有权限导出该数据");
				}
				final Map<String, Object> finalParams = objects;
				//调用lazy3q.jar中的导出功能
				//参数说明：(这次导出多少条数据，到数据库查的时候一次查多少条，当前所在第几页,处理器，对应的模型类,要导出哪些字段)
				$.export($.getInteger("size",1000), 100, $.getInteger("page",0), new Export(){//new Export这个类，他会根据参数分次查询，边查边写，目的只有一个，就是为了避免把大量的数据放在内存中
					@Override //重载实现Export的getPage方法，Export会分多次调用这个方法
					public Page getPage(Integer page, int size) {//这时的page和size是Export转化过后的当前页，每页查询数					
						return csTroubleService.getCsTroublePage(page, size, finalParams);
					}
				}, CsTrouble.class, $.getArray("keys"));
				return null;//数据已经被Export直接写入给输出流了，直接返回null,下面的逻辑就不执行了，
			}
			/************LAZY3Q_CODE_EXECUTE************/
			/************LAZY3Q_CODE_EXECUTE************/

						
			//调用Service获取分页
			Page page = csTroubleService.getCsTroublePage($.getInteger("page", 0), $.getInteger("size",16), objects);
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
	 * 车辆事故统计
	 * @return
	 */	
	public String stats()
	{	
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//列表地址参数状态串,查询条件传递到转跳,就是把当前列表页的路径和参数记下来，放入request,在后面添加、编辑时把这个参数entrypoint带上，提交后再返回到当前列表
			$.setRequest("modelClass",CsTrouble.class);//把当前Action对应的模型类放入到request中,以作为后面导出、、、、等功能的反射使用
			
			if($.getBoolean("export",false)==true){//如果传入了一个数据导出的参数
				javax.servlet.http.HttpServletResponse response = com.lazy3q.xwork.WebContext.getServletResponse();
				response.setCharacterEncoding("UTF-8"); 
				java.io.PrintWriter out = response.getWriter();			
				response.setContentType("application/x-octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode("车辆事故统计导出","UTF-8")+".xls");
				out.println($.getString("html"));
				out.println("");
				out.flush();
				return null;//写入给输出流了，直接返回null,下面的逻辑就不执行了，
			}
			
			//处理页面的视图数据，比如显示哪些列，自定义显示列等
			ActionHelper.doPageViewFields(CsTrouble.class);
					
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> objects = ActionHelper.getQueryFormParams(CsTrouble.class);
				
			//把request传过来的查询参数放回request,为了页面能够正确显示上次所选择的值
			Set<String> keys = objects.keySet();
			for(String key:keys)
				$.setRequest(key, objects.get(key));
				
			//把组装好的objects整个Map直接放入request,####原因暂时保密，等你哪天理解出这儿的意思了，你就是高手了
			$.setRequest("objects",objects);
			//强制限制域
			objects.put("HOSTS",SystemHelper.testHost(null));
			
			//反强制限制域
			if(objects.get("cstOrder")!=null && objects.get("cstOrderNot")==null){
				objects.remove("HOSTS");
			}
			if(objects.get("cstMember")!=null && objects.get("cstMemberNot")==null){
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
		 				groups.put(CsTrouble.C.get(groupName),"{key}");
		 			else{
		 				groups.put(CsTrouble.C.get(groupName),"DATE_FORMAT({key},'"+timeType.split(",")[0]+"')");
		 				$.setRequest("haveTimeType",true);
		 				timeTypes.add(groupName);
		 				if(timeType.startsWith("%Y"))
		 					objects.put("asc", CsTrouble.C.get(groupName));
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
		 				sums.put(CsTrouble.C.get(sumName),"sum({key})");
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
			/************LAZY3Q_CODE_STATS************/
			/************LAZY3Q_CODE_STATS************/

						
			//调用Service获取统计数据
			List list = csTroubleService.getCsTroubleStats(objects,groups,sums);
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
	 * 车辆事故添加/编辑页面
	 * @return
	 */
	public String edit()
	{		
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//编辑页状态串(包含进入编辑页面时的地址和参数)
			$.setRequest("entrypoint", $.getString("entrypoint"));//列表地址参数状态串，由列表页生产，这里只是携带参数，跟快递差不多
			$.setRequest("ids",$.getString("ids"));//批量编辑的数据ID，多个ID以,号分隔
			$.setSession("csTroubleToken", $.uuid());//防重复提交Token
			//当前要编辑的数据ID，有这个ID表示修改数据，没这个ID表示添加数据
			Long id=$.getLong("id");
			if(id!=null){
				//把当前要修改的数据查出来,注意了，csTrouble这个对象为什么不用$.setRequest,因为csTrouble有get,set方法，由struts处理的
				csTrouble=csTroubleService.getCsTroubleById(id);
				//确认域标识
				if(SystemHelper.testHost(csTrouble.getCstHost()).equals("0")){
					return $.SendHtml("<script>alert('对不起，你没有权限编辑该数据');</script>", "UTF-8");
				}
			}
			if(csTrouble==null)
				csTrouble = new CsTrouble();
			//根据自定义配置ctrl中配置的默认值信息设置默认值
			if(CTRL!=null)
				CTRL.setObjectDefaultValue(csTrouble);
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
	public void on(String type,CsTrouble object,CsTrouble old){
		try{
			//执行后台配置的动态代码脚本，如果有在后台配置的话(*_*)
			ActionHelper.executeActionScript(CsTrouble.class, type, object, old);
			/************LAZY3Q_ACTION_ON************/
			/************LAZY3Q_ACTION_ON************/

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	/**
	 * 保存车辆事故
	 * @return
	 */
	public String save()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//主键为空，表示插入,否则表示更新
			final Boolean bIsAdd = ($.empty($.getString("ids")) && $.empty($.getString("PARAMS")) && csTrouble.getCstId()==null);
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsTroubleToken = $.getString("csTroubleToken");
			final String sessionCsTroubleToken = $.getSession("csTroubleToken");
			if($.equals(requestCsTroubleToken,sessionCsTroubleToken)){
				$.removeSession("csTroubleToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csTroubleService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						/************LAZY3Q_BEFOR_SAVE************/
						/************LAZY3Q_BEFOR_SAVE************/

						if(bIsAdd){
							//批量插入处理
							String vValue = ""+(csTrouble.getCstId()==null?"":csTrouble.getCstId());
							String sArray[]={""+vValue};
							if(vValue.startsWith("[*]")){
								vValue=vValue.replace("[*]","");
								sArray=vValue.split("[\\s|\u3000]+");				
							}
							for(String sItem:sArray){
								if(!$.empty(sItem))
								csTrouble.setCstId(Long.valueOf(sItem));
							
								//设置默认值
								if(csTrouble.getCstTitle()==null)
									csTrouble.setCstTitle(csTrouble.get$cstCar().getCscNumber()+"["+csTrouble.get$cstOrder().getCsoId()+"]事故");
								if(csTrouble.getCstMoney()==null)
									csTrouble.setCstMoney(0d);
								if(csTrouble.getCstAgio()==null)
									csTrouble.setCstAgio(0d);
								if(csTrouble.getCstNotice()==null)
									csTrouble.setCstNotice((short)0);
								if(csTrouble.getCstAccepter()==null)
									csTrouble.setCstAccepter(LoginHelper.getLoginId());
								if(csTrouble.getCstAddTime()==null)
									csTrouble.setCstAddTime(new Date());
								if(csTrouble.getCstStatus()==null)
									csTrouble.setCstStatus((short)0);
								
								//根据自定义的默认值信息设置默认值
								if(CTRL!=null)
									CTRL.setObjectDefaultValue(csTrouble);
								//添加域标识
								if(SystemHelper.testHost(csTrouble.getCstHost()).equals("0")){
									throw new RuntimeException("非法指定域值");
								}
								
								csTrouble=csTroubleService.saveCsTrouble(csTrouble);			
								
								on("save",csTrouble,csTrouble);
							}							
							
							csTroubleService.updateCsTrouble$NotNull(csTrouble);						
							/************LAZY3Q_CODE_ADD************/
							/************LAZY3Q_CODE_ADD************/

							
							//记录操作日志
							LoggerHelper.writeLog(CsTrouble.class,"add","添加了[车辆事故]["+csTrouble.getCstId()+"]",(Long)$.getSession("ccclubs_login_id"), csTrouble,csTrouble.getCstId());
							$.SetTips("保存车辆事故成功");
						}else{
							String PARAMS = $.getString("PARAMS");
							String ids=$.getString("ids");
							if(!$.empty(PARAMS)){//根据查询条件批量更新
								Map params = $.eval(PARAMS);
								params.put("confirm", 1);
								params.put("definex","1=1");
								csTroubleService.updateCsTroubleByConfirm(ActionHelper.getSetValuesFromModel(csTrouble), params);
								LoggerHelper.writeLog(CsTrouble.class, "update", 
											"批量修改了[车辆事故]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(null,csTrouble,false)
											,null);
							}else if(!$.empty(ids)){//根据ids批量更新
								String[] array = ids.split(",");
								for(String sid:array){
									csTrouble.setCstId(Long.valueOf(sid));
									CsTrouble oldCsTrouble = CsTrouble.get(Long.valueOf(sid));
									csTroubleService.updateCsTrouble$NotNull(csTrouble);
									LoggerHelper.writeLog(CsTrouble.class, "update", 
											"修改了[车辆事故]["+oldCsTrouble.getCstId()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsTrouble, csTrouble,false)
											,Long.valueOf(sid));
									on($.getString("method"),csTrouble,oldCsTrouble);
								}
							}else{
								if($.empty($.getString("edittype"))){
									//设置填充值
									csTrouble.setCstUpdateTime(new Date());
									
									CsTrouble oldCsTrouble = CsTrouble.get(csTrouble.getCstId());
									csTroubleService.updateCsTrouble$NotNull(csTrouble);
									LoggerHelper.writeLog(CsTrouble.class, "update", 
											"修改了[车辆事故]["+oldCsTrouble.getCstId()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsTrouble, csTrouble,false)
											,csTrouble.getCstId());
									on($.getString("method"),csTrouble,oldCsTrouble);									
								}else{
									CsTrouble oldCsTrouble = csTroubleService.getCsTroubleById(csTrouble.getCstId());
									//设置默认值
									csTrouble.setCstTitle(oldCsTrouble.getCstTitle());	
									csTrouble.setCstOrder(oldCsTrouble.getCstOrder());	
									csTrouble.setCstMember(oldCsTrouble.getCstMember());	
									csTrouble.setCstCar(oldCsTrouble.getCstCar());	
									csTrouble.setCstType(oldCsTrouble.getCstType());	
									csTrouble.setCstRepairAdr(oldCsTrouble.getCstRepairAdr());	
									csTrouble.setCstRepairPart(oldCsTrouble.getCstRepairPart());	
									csTrouble.setCstMoney(oldCsTrouble.getCstMoney());	
									csTrouble.setCstHappenTime(oldCsTrouble.getCstHappenTime());	
									csTrouble.setCstAccepter(oldCsTrouble.getCstAccepter());	
									csTrouble.setCstCaser(oldCsTrouble.getCstCaser());	
									csTrouble.setCstFinishTime(oldCsTrouble.getCstFinishTime());	
									csTrouble.setCstUpdateTime(oldCsTrouble.getCstUpdateTime());	
									csTrouble.setCstAddTime(oldCsTrouble.getCstAddTime());	
									csTrouble.setCstStatus(oldCsTrouble.getCstStatus());	
									
									//设置填充值
									if(csTrouble.getCstUpdateTime()==null)
										csTrouble.setCstUpdateTime(new Date());
									//添加域标识
									if(SystemHelper.testHost(csTrouble.getCstHost()).equals("0")){
										throw new RuntimeException("非法指定域值");
									}
									
									//从oldCsTrouble同步csTrouble未set过的值
									csTrouble.mergeSet(oldCsTrouble);
											
									csTroubleService.updateCsTrouble(csTrouble);
									LoggerHelper.writeLog(CsTrouble.class, "update", 
											"修改了[车辆事故]["+oldCsTrouble.getCstId()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsTrouble, csTrouble,true)
											,csTrouble.getCstId());
									on("update",csTrouble,oldCsTrouble);
								}
							}
							/************LAZY3Q_CODE_UPDATE************/
							/************LAZY3Q_CODE_UPDATE************/

							//记录操作日志
							$.SetTips("修改车辆事故成功");
						}	
					/*************LAZY3Q_CODE_SAVE***************/
					//同步维修费用
					if(csTrouble.getCstRepairPay()!=null  && $.empty($.getString("ids")) && csTrouble.getCstRepair()!=null){
						ICsRepairService csRepairService=$.getBean("csRepairService");
						csRepairService.updateCsRepairByConfirm(
								$.add("csrMoney", csTrouble.getCstRepairPay()), 
								$.add("csrId", csTrouble.getCstRepair()).add("confirm", 1)
						);
					}
					/*************LAZY3Q_CODE_SAVE***************/

						
						return null;
					}
				});
				
			}else{
				$.removeSession("csTroubleToken");//移除令牌	
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
				return $.SendHtml("<script>"+complete+"("+$.json(csTrouble)+")"+"</script>","UTF-8");
			}
			
			//如果点保存，则回到编辑页，如果点确定，则回到列表页
			//现在知道editorpoint和entrypoint的意义了吗
			//它们由地址加参数(如:http://xxx.xx/xx.xx?xx=xx&xxx=xxx)转成16进制字串进行传递的
			if($.getString("submiter","").indexOf("保存")>-1){//则回到编辑页
				if(bIsAdd)
					return $.Redirect("trouble_edit.do?entrypoint="+$.getString("entrypoint")+"&id="+csTrouble.getCstId());
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
	 * 删除车辆事故
	 * @return
	 */
	public String del()
	{
		try{
			//获取当前用户的权限，看当前用户有没有删除权限
			SystemHelper.getSrvLimit("admin/service/trouble.do");
					/************LAZY3Q_CAN_DEL************/
					/************LAZY3Q_CAN_DEL************/

			if($.getRequest("canDel")==null || ((Boolean)$.getRequest("canDel"))==false){
				throw new RuntimeException("您没有权限删除该数据");
			}
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//多个删除数据动作合并事务过程
			csTroubleService.executeTransaction(new Function(){
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
							CsTrouble csTrouble = csTroubleService.getCsTroubleById(id);
							//确认域标识
							if(SystemHelper.testHost(csTrouble.getCstHost()).equals("0")){
								throw new RuntimeException("当前用户没有权限删除该数据");
							}
							if(ConfigHelper.GetBoolean("logic_delete")==true)
								csTroubleService.removeCsTroubleById(id);
							else
							csTroubleService.deleteCsTroubleById(id);			
							//记录操作日志
							if(csTrouble!=null)
								LoggerHelper.writeLog(CsTrouble.class,"del","删除了[车辆事故]["+csTrouble.getCstId()+"]",(Long)$.getSession("ccclubs_login_id"), csTrouble);
							on("delete",csTrouble,csTrouble);
					}
					/************LAZY3Q_CODE_DEL************/
					/************LAZY3Q_CODE_DEL************/

					
					return null;
				}
			});
			
			$.SetTips("删除车辆事故成功");
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
	 * 显示车辆事故详情
	 * @return
	 */
	public String details()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			Long id=$.getLong("id");
			Long key=$.getLong("key");
			if(id!=null)
				csTrouble=csTroubleService.getCsTroubleById(id);
			if(key!=null && id==null)
				csTrouble=CsTrouble.Get($.add(CsTrouble.F.cstId,key));
			//确认域标识
			if(SystemHelper.testHost(csTrouble.getCstHost()).equals("0")){
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
	 * 根据编号或ID查询车辆事故
	 * 返回ajax数据
	 * @return
	 */
	public String query(){
		try{
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> params = ActionHelper.getQueryFormParams(CsTrouble.class);
			//强制限制域
			params.put("HOSTS",SystemHelper.testHost(null));
			
			//反强制限制域
			if(params.get("cstOrder")!=null && params.get("cstOrderNot")==null){
				params.remove("HOSTS");
			}
			if(params.get("cstMember")!=null && params.get("cstMemberNot")==null){
				params.remove("HOSTS");
			}
			
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=$.getString("asc");//升序字段
			params.put("asc", strAsc);//放入查询条件
			String strDesc=$.getString("desc");//降序字段
			params.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				params.put("desc","cst_id");//那么，默认以主键降序，传给Dao层
								
			String strTerm=$.getString("value");//智能搜索时的参数，一般都是主键后面那个字段
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("cstId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or cst_id like '"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}
			/************LAZY3Q_CODE_QUERY************/
			/************LAZY3Q_CODE_QUERY************/

			
			//是否需要整个数据对象
			Boolean bObject=$.getBoolean("object",false);
			
			List<CsTrouble> list = csTroubleService.getCsTroublePage(0,$.getInteger("size",10),params).getResult();
			
			/**
			* OK!取到数据拼成放入Map中，[{value,text,object:{...}},...]
			* value:数据ID，text:数据标识,object,整个对象
			**/
			List<Map> result = new java.util.ArrayList();
			for(CsTrouble csTrouble:list){
				Map map = new HashMap();
				map.put("value",csTrouble.getCstId().toString());
				map.put("text", $.js(csTrouble.getKeyValue()));
				if(bObject==true)
					map.put("object",csTrouble);
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
	 * 定损
	 */
	public String pricing(){		
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等		
			csTroubleService.updateCsTrouble$NotNull(
				new CsTrouble()
				.cstId(csTrouble.getCstId())
				.cstInsureFee(csTrouble.getCstInsureFee())
				.cstRemark(csTrouble.getCstRemark())
				.cstStatus((short)1)
			);						
			//记录操作日志
			LoggerHelper.writeLog(CsTrouble.class,"update","定损[车辆事故]["+csTrouble.getCstId()+"]",(Long)$.getSession("ccclubs_login_id"), csTrouble);
			$.SetTips("事故定损成功");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	/**
	 * 协商
	 */
	public String consult(){		
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			CsTrouble updateCsTrouble = new CsTrouble()
				.cstId(csTrouble.getCstId())
				.cstMoney(csTrouble.getCstMoney())
				.cstRemark(csTrouble.getCstRemark());
			if(updateCsTrouble.getCstStatus()==null || updateCsTrouble.getCstStatus().shortValue()==1)
				updateCsTrouble.cstStatus((short)2);
			csTroubleService.updateCsTrouble$NotNull(updateCsTrouble);						
			//记录操作日志
			LoggerHelper.writeLog(CsTrouble.class,"update","协商[车辆事故]["+csTrouble.getCstId()+"]",(Long)$.getSession("ccclubs_login_id"), csTrouble);
			$.SetTips("事故协商成功");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	/**
	 * 维修处理
	 */
	public String repair(){		
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等	
			if($.getString("submiter","").indexOf("送入维修")>-1){				
				CsTrouble oldCsTrouble = csTroubleService.getCsTroubleById(csTrouble.getCstId());
				ICsRepairService csRepairService = $.GetSpringBean("csRepairService");
				CsRepair csRepair = null;
				if(oldCsTrouble.getCstRepair()==null){//如果老数据中没有维修记录，则添加一条维修记录到数据库中
					csRepair=new CsRepair();
					if(csTrouble.getCstRepairAdr()!=null){
						csRepair.setNotNull(
							oldCsTrouble.getCstHost(),
							"事故["+oldCsTrouble.getKeyValue()+"]车辆维修",
							oldCsTrouble.getCstCar(), 
							csTrouble.getCstRepairAdr(),
							LoginHelper.getLoginId(), 
							new Date(), 
							(short)0
						);
						csRepair.setCsrDescript(csTrouble.getCstRepairPart());
						csRepair.csrOrder(oldCsTrouble.getCstOrder());
						csRepair = csRepairService.saveCsRepair(csRepair);
					}
				}else
					csRepair = csRepairService.getCsRepairById(oldCsTrouble.getCstRepair());
				csTroubleService.updateCsTrouble$NotNull(
						new CsTrouble()
						.cstId(csTrouble.getCstId())
						.cstRepairAdr(csTrouble.getCstRepairAdr())
						.cstRepairPart(csTrouble.getCstRepairPart())
						.cstRemark(csTrouble.getCstRemark())
						.cstStatus((short)3)
						.cstRepair(csRepair.getCsrId())
				);
			}
			if($.getString("submiter","").indexOf("维修完成")>-1){
				csTroubleService.updateCsTrouble$NotNull(
						new CsTrouble()
						.cstId(csTrouble.getCstId())
						.cstRepairAdr(csTrouble.getCstRepairAdr())
						.cstRepairPart(csTrouble.getCstRepairPart())
						.cstRemark(csTrouble.getCstRemark())
						.cstStatus((short)4)
				);			
			}
			//记录操作日志
			LoggerHelper.writeLog(CsTrouble.class,"update","维修处理[车辆事故]["+csTrouble.getCstId()+"]",(Long)$.getSession("ccclubs_login_id"), csTrouble);
			$.SetTips("事故维修处理成功");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	/**
	 * 扣款
	 */
	public String deduct(){		
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等	
			csTroubleService.executeTransaction(new Function(){
				@Override
				public <T> T execute(Object... arg0) {
					
					CsTrouble oldCsTrouble = csTroubleService.getCsTroubleById(csTrouble.getCstId());
					
					ICommonMoneyService commonMoneyService = $.GetSpringBean("commonMoneyService");
					commonMoneyService.updateMoney(
							oldCsTrouble.getCstMember(),
							ICommonMoneyService.MoneyType.Money, 
							-(Math.abs(oldCsTrouble.getCstMoney())), 
							SYSTEM.RecordType.事故扣款, 
							"事故扣款", 
							oldCsTrouble.getCstOrder(),
							oldCsTrouble.getCstId(),
							CsTrouble.class
						);
					csTroubleService.updateCsTrouble$NotNull(
							new CsTrouble()
							.cstId(oldCsTrouble.getCstId())
							.cstRemark(csTrouble.getCstRemark())
							.cstStatus((short)5)
						);
					return null;
				}				
			});			
			//记录操作日志
			LoggerHelper.writeLog(CsTrouble.class,"update","扣款[车辆事故]["+csTrouble.getCstId()+"]",(Long)$.getSession("ccclubs_login_id"), csTrouble);
			$.SetTips("事故扣款成功");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	/**
	 * 结案
	 */
	public String settle(){		
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等		
			csTroubleService.executeTransaction(new Function(){
				@Override
				public <T> T execute(Object... arg0) {
					csTroubleService.updateCsTrouble$NotNull(
							new CsTrouble()
							.cstId(csTrouble.getCstId())
							.cstAgio(csTrouble.getCstAgio())
							.cstStatus((short)6)
							.cstCaser(LoginHelper.getLoginId())
							.cstFinishTime(new Date())
						);
					CsTrouble dbCsTrouble = CsTrouble.get(csTrouble.getCstId());
					com.ccclubs.service.common.impl.CommonMoneyService.getBean().updateGrow(
						dbCsTrouble.getCstMember(), 
						SYSTEM.GrowRecordType.交通事故, 
						csTrouble.getCstId(), 
						Math.max($.or(dbCsTrouble.getCstInsureFee(),0d),$.or(dbCsTrouble.getCstMoney(),0d))
					);
					//记录操作日志
					LoggerHelper.writeLog(CsTrouble.class,"update","结案[车辆事故]["+csTrouble.getCstId()+"]",(Long)$.getSession("ccclubs_login_id"), csTrouble);
					
					return null;
				}
			});
			$.SetTips("事故结案成功");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	/***************LAZY3Q_DEFINE_CODE******************/


	public ICsTroubleService getCsTroubleService()
	{
		return csTroubleService;
	}

	public void setCsTroubleService(ICsTroubleService csTroubleService)
	{
		this.csTroubleService = csTroubleService;
	}

	public CsTrouble getCsTrouble()
	{
		return csTrouble;
	}

	public void setCsTrouble(CsTrouble csTrouble)
	{
		this.csTrouble = csTrouble;
	}
}