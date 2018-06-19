package com.ccclubs.action.admin.object;

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
import com.ccclubs.model.CsCar;
import com.ccclubs.service.admin.ICsCarService;
/**************LAZY3Q_CODE_IMPORT**************/
import com.ccclubs.model.CsOrder;
import com.ccclubs.service.common.ICommonOrderService;
import com.ccclubs.model.CsCarOn;
import com.ccclubs.model.CsGoods;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsPrice;
import com.ccclubs.model.CsUserType;
import com.ccclubs.model.Restriction;
import com.ccclubs.model.SrvHost;
import com.ccclubs.service.admin.ICsOutletsService;
import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.RuleName;
import com.ccclubs.dao.ICsGoodsDao;
import com.ccclubs.dao.ICsPriceDao;
import com.ccclubs.dao.ICsUserTypeDao;
import com.ccclubs.dao.impl.Trigger.Type;
import com.ccclubs.exception.ErrorException;
import com.lazy3q.sql.Lazy3qDaoSupport;
/**************LAZY3Q_CODE_IMPORT**************/


/**
 * 车辆的Action
 * @author 飞啊飘啊
 * 感谢struts的Action框架
 */
@SuppressWarnings("unchecked")
public class CarAction
{
	ICsCarService csCarService;
	
	CsCar csCar;
	//下线标签
	private Long property;
	
	/**
	 * 车辆管理首页
	 * @return
	 */	
	public String execute()
	{	
		try{
			java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\.([\\S\\s]+)\\.").matcher($.getServletRequest().getServletPath());
			String alias=(String) (matcher.find()?$.setRequest("alias",matcher.group(1)):null);//别名入口
						
			$.setRequest("modelClass",CsCar.class);//把当前Action对应的模型类放入到request中,以作为后面导出、、、、等功能的反射使用
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//列表地址参数状态串，查询条件传递到转跳,就是把当前列表页的路径和参数记下来，放入request,在后面添加、编辑时把这个参数entrypoint带上，提交后再返回到当前列表
			//处理页面的视图数据，比如显示哪些列，自定义显示列等
			ActionHelper.doPageViewFields(CsCar.class);
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> objects = ActionHelper.getQueryFormParams(CsCar.class);
			$.setRequest("PARAMS",ActionHelper.getQueryFormParams(CsCar.class));
									
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=CsCar.C.get($.forMat($.getString("asc")));//升序字段
			objects.put("asc", strAsc);//放入查询条件
			String strDesc=CsCar.C.get($.forMat($.getString("desc")));//降序字段
			objects.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				objects.put("desc","csc_id");//那么，默认以主键降序，传给Dao层
			
			//把request传过来的查询参数放回request,为了页面能够正确显示上次所选择的值
			Set<String> keys = objects.keySet();
			for(String key:keys)
				$.setRequest(key, objects.get(key));
				
			//把组装好的objects整个Map直接放入request,####原因暂时保密，等你哪天理解出这儿的意思了，你就是高手了
			$.setRequest("objects",objects);
			//强制限制域
			objects.put("HOSTS",SystemHelper.testHost(null));
			
			//反强制限制域
			
			//canQuery：表示当前列表是否具有查询功能，这里只是负责传个参数，具体能查不能查，在页面根据这个参数做处理
			$.setRequest("canQuery", $.getBoolean("canQuery",true));			
			if($.getBoolean("export",false)==true)//如果传入了一个数据导出的参数
			{
				//获取当前用户的权限，看当前用户有没有删除权限
				SystemHelper.getSrvLimit("admin/object/car.do");
				if($.getRequest("canExport")==null || ((Boolean)$.getRequest("canExport"))==false){
					throw new RuntimeException("您没有权限导出该数据");
				}
				final Map<String, Object> finalParams = objects;
				//调用lazy3q.jar中的导出功能
				//参数说明：(这次导出多少条数据，到数据库查的时候一次查多少条，当前所在第几页,处理器，对应的模型类,要导出哪些字段)
				$.export($.getInteger("size",1000), 100, $.getInteger("page",0), new Export(){//new Export这个类，他会根据参数分次查询，边查边写，目的只有一个，就是为了避免把大量的数据放在内存中
					@Override //重载实现Export的getPage方法，Export会分多次调用这个方法
					public Page getPage(Integer page, int size) {//这时的page和size是Export转化过后的当前页，每页查询数					
						return csCarService.getCsCarPage(page, size, finalParams);
					}
				}, CsCar.class, $.getArray("keys"));
				return null;//数据已经被Export直接写入给输出流了，直接返回null,下面的逻辑就不执行了，
			}
			/************LAZY3Q_CODE_EXECUTE************/
			String servletPath = $.getServletRequest().getServletPath();
			/*if(servletPath.indexOf("car_select.do")>-1)
				objects.put(CsCar.F.cscHost, SYSTEM.getDefaultHost());*/
			if(servletPath.indexOf("car_select.do")>-1)
				objects.put("desc", "csc_status=1");
			/************LAZY3Q_CODE_EXECUTE************/

						
			//调用Service获取分页
			Page page = csCarService.getCsCarPage($.getInteger("page", 0), $.getInteger("size",16), objects);
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
	 * 车辆添加/编辑页面
	 * @return
	 */
	public String edit()
	{		
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//编辑页状态串(包含进入编辑页面时的地址和参数)
			$.setRequest("entrypoint", $.getString("entrypoint"));//列表地址参数状态串，由列表页生产，这里只是携带参数，跟快递差不多
			$.setRequest("ids",$.getString("ids"));//批量编辑的数据ID，多个ID以,号分隔
			$.setSession("csCarToken", $.uuid());//防重复提交Token
			//当前要编辑的数据ID，有这个ID表示修改数据，没这个ID表示添加数据
			Long id=$.getLong("id");
			if(id!=null){
				//把当前要修改的数据查出来,注意了，csCar这个对象为什么不用$.setRequest,因为csCar有get,set方法，由struts处理的
				csCar=csCarService.getCsCarById(id);
				//确认域标识
				if(SystemHelper.testHost(csCar.getCscHost()).equals("0")){
					return $.SendHtml("<script>alert('对不起，你没有权限编辑该数据');</script>", "UTF-8");
				}
			}
			if(csCar==null)
				csCar = new CsCar();
			//根据自定义配置ctrl中配置的默认值信息设置默认值
			if(CTRL!=null)
				CTRL.setObjectDefaultValue(csCar);
			/************LAZY3Q_CODE_EDIT************/
			readTimeLineData();
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
	public void on(String type,CsCar object,CsCar old){
		try{
			//执行后台配置的动态代码脚本，如果有在后台配置的话(*_*)
			ActionHelper.executeActionScript(CsCar.class, type, object, old);
			/************LAZY3Q_ACTION_ON************/
			
			/************LAZY3Q_ACTION_ON************/

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	/**
	 * 保存车辆
	 * @return
	 */
	public String save()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//主键为空，表示插入,否则表示更新
			final Boolean bIsAdd = ($.empty($.getString("ids")) && $.empty($.getString("PARAMS")) && csCar.getCscId()==null);
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsCarToken = $.getString("csCarToken");
			final String sessionCsCarToken = $.getSession("csCarToken");
			if($.equals(requestCsCarToken,sessionCsCarToken)){
				$.removeSession("csCarToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csCarService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						/************LAZY3Q_BEFOR_SAVE************/
						/************LAZY3Q_BEFOR_SAVE************/

						if(bIsAdd){
							//批量插入处理
							String vValue = ""+(csCar.getCscNumber()==null?"":csCar.getCscNumber());
							String sArray[]={""+vValue};
							if(vValue.startsWith("[*]")){
								vValue=vValue.replace("[*]","");
								sArray=vValue.split("[\\s|\u3000]+");				
							}
							String cscCarNo = csCar.getCscCarNo();
							String cscMqttFlag = csCar.getCscMqttFlag();
							for(String sItem:sArray){
								if(!$.empty(sItem))
								csCar.setCscNumber(String.valueOf(sItem));
							
								//设置默认值
								if(csCar.getCscRebate()==null)
									csCar.setCscRebate(1.0d);
								if(csCar.getCscLongitude()==null)
									csCar.setCscLongitude(csCar.get$cscOutlets().getCsoLongitude());
								if(csCar.getCscLatitude()==null)
									csCar.setCscLatitude(csCar.get$cscOutlets().getCsoLatitude());
								if(csCar.getCscEditor()==null)
									csCar.setCscEditor(LoginHelper.getLoginId());
								if(csCar.getCscUpdateTime()==null)
								csCar.setCscUpdateTime(new Date());
								if(csCar.getCscAddTime()==null)
								csCar.setCscAddTime(new Date());
								if(csCar.getCscDeviceVesion()==null)
									csCar.setCscDeviceVesion((short)0);
								if(csCar.getCscMask()==null)
									csCar.setCscMask(0l);
								if(csCar.getCscStatus()==null)
									csCar.setCscStatus((short)2);
								csCar.setCscCarNo(sItem.length()>2?cscCarNo.replace("*", sItem.substring(2, sItem.length())):cscCarNo.replace("*",sItem));
								csCar.setCscMqttFlag(cscMqttFlag.replace("*",sItem));
								//根据自定义的默认值信息设置默认值
								if(CTRL!=null)
									CTRL.setObjectDefaultValue(csCar);
								//添加域标识
								if(SystemHelper.testHost(csCar.getCscHost()).equals("0")){
									throw new RuntimeException("非法指定域值");
								}
								
								csCar=csCarService.saveCsCar(csCar);			
								
								on("save",csCar,csCar);
							}							
							
							csCarService.updateCsCar$NotNull(csCar);						
							/************LAZY3Q_CODE_ADD************/
							/************LAZY3Q_CODE_ADD************/

							
							//记录操作日志
							LoggerHelper.writeLog(CsCar.class,"add","添加了[车辆]["+csCar.getCscNumber()+"]",(Long)$.getSession("ccclubs_login_id"), csCar,csCar.getCscId());
							$.SetTips("保存车辆成功");
						}else{
							String PARAMS = $.getString("PARAMS");
							String ids=$.getString("ids");
							if(!$.empty(PARAMS)){//根据查询条件批量更新
								Map params = $.eval(PARAMS);
								params.put("confirm", 1);
								params.put("definex","1=1");
								csCarService.updateCsCarByConfirm(ActionHelper.getSetValuesFromModel(csCar), params);
								LoggerHelper.writeLog(CsCar.class, "update", 
											"批量修改了[车辆]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(null,csCar,false)
											,null);
							}else if(!$.empty(ids)){//根据ids批量更新
								String[] array = ids.split(",");
								for(String sid:array){
									csCar.setCscId(Long.valueOf(sid));
									CsCar oldCsCar = CsCar.get(Long.valueOf(sid));
									csCarService.updateCsCar$NotNull(csCar);
									LoggerHelper.writeLog(CsCar.class, "update", 
											"修改了[车辆]["+oldCsCar.getCscNumber()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsCar, csCar,false)
											,Long.valueOf(sid));
									on($.getString("method"),csCar,oldCsCar);
								}
							}else{
								if($.empty($.getString("edittype"))){
									//设置填充值
									csCar.setCscLongitude(csCar.get$cscOutlets()==null?null:csCar.get$cscOutlets().getCsoLongitude());
									csCar.setCscLatitude(csCar.get$cscOutlets()==null?null:csCar.get$cscOutlets().getCsoLatitude());
									csCar.setCscEditor(LoginHelper.getLoginId());
									
									CsCar oldCsCar = CsCar.get(csCar.getCscId());
									csCarService.updateCsCar$NotNull(csCar);
									LoggerHelper.writeLog(CsCar.class, "update", 
											"修改了[车辆]["+oldCsCar.getCscNumber()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsCar, csCar,false)
											,csCar.getCscId());
									on($.getString("method"),csCar,oldCsCar);									
								}else{
									CsCar oldCsCar = csCarService.getCsCarById(csCar.getCscId());
									//设置默认值
									csCar.setCscOutlets(oldCsCar.getCscOutlets());	
									csCar.setCscLongitude(oldCsCar.getCscLongitude());	
									csCar.setCscLatitude(oldCsCar.getCscLatitude());	
									csCar.setCscEditor(oldCsCar.getCscEditor());	
									csCar.setCscUpdateTime(oldCsCar.getCscUpdateTime());	
									csCar.setCscAddTime(oldCsCar.getCscAddTime());	
									csCar.setCscRemark(oldCsCar.getCscRemark());	
									csCar.setCscOnPsd(oldCsCar.getCscOnPsd());	
									csCar.setCscMask(oldCsCar.getCscMask());	
									csCar.setCscStatus(oldCsCar.getCscStatus());	
									
									//设置填充值
									if(csCar.getCscLongitude()==null)
										csCar.setCscLongitude(csCar.get$cscOutlets()==null?null:csCar.get$cscOutlets().getCsoLongitude());
									if(csCar.getCscLatitude()==null)
										csCar.setCscLatitude(csCar.get$cscOutlets()==null?null:csCar.get$cscOutlets().getCsoLatitude());
									if(csCar.getCscEditor()==null)
										csCar.setCscEditor(LoginHelper.getLoginId());
									//添加域标识
									if(SystemHelper.testHost(csCar.getCscHost()).equals("0")){
										throw new RuntimeException("非法指定域值");
									}
									
									//从oldCsCar同步csCar未set过的值
									csCar.mergeSet(oldCsCar);
											
									csCarService.updateCsCar(csCar);
									LoggerHelper.writeLog(CsCar.class, "update", 
											"修改了[车辆]["+oldCsCar.getCscNumber()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsCar, csCar,true)
											,csCar.getCscId());
									on("update",csCar,oldCsCar);
								}
							}
							/************LAZY3Q_CODE_UPDATE************/						
							/************LAZY3Q_CODE_UPDATE************/

							//记录操作日志
							$.SetTips("修改车辆成功");
						}	
						/************LAZY3Q_CODE_SAVE************/
						/************LAZY3Q_CODE_SAVE************/

						
						return null;
					}
				});
				
			}else{
				$.removeSession("csCarToken");//移除令牌	
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
				return $.SendHtml("<script>"+complete+"("+$.json(csCar)+")"+"</script>","UTF-8");
			}
			
			//如果点保存，则回到编辑页，如果点确定，则回到列表页
			//现在知道editorpoint和entrypoint的意义了吗
			//它们由地址加参数(如:http://xxx.xx/xx.xx?xx=xx&xxx=xxx)转成16进制字串进行传递的
			if($.getString("submiter","").indexOf("保存")>-1){//则回到编辑页
				if(bIsAdd)
					return $.Redirect("car_edit.do?entrypoint="+$.getString("entrypoint")+"&id="+csCar.getCscId());
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
	 * 删除车辆
	 * @return
	 */
	public String del()
	{
		try{
			//获取当前用户的权限，看当前用户有没有删除权限
			SystemHelper.getSrvLimit("admin/object/car.do");
					/************LAZY3Q_CAN_DEL************/
					/************LAZY3Q_CAN_DEL************/

			if($.getRequest("canDel")==null || ((Boolean)$.getRequest("canDel"))==false){
				throw new RuntimeException("您没有权限删除该数据");
			}
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//多个删除数据动作合并事务过程
			csCarService.executeTransaction(new Function(){
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
							CsCar csCar = csCarService.getCsCarById(id);
							//确认域标识
							if(SystemHelper.testHost(csCar.getCscHost()).equals("0")){
								throw new RuntimeException("当前用户没有权限删除该数据");
							}
							if(ConfigHelper.GetBoolean("logic_delete")==true)
								csCarService.removeCsCarById(id);
							else
							csCarService.deleteCsCarById(id);			
							//记录操作日志
							if(csCar!=null)
								LoggerHelper.writeLog(CsCar.class,"del","删除了[车辆]["+csCar.getCscNumber()+"]",(Long)$.getSession("ccclubs_login_id"), csCar);
							on("delete",csCar,csCar);
					}
					/************LAZY3Q_CODE_DEL************/
					/************LAZY3Q_CODE_DEL************/

					
					return null;
				}
			});
			
			$.SetTips("删除车辆成功");
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
	 * 显示车辆详情
	 * @return
	 */
	public String details()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			Long id=$.getLong("id");
			Long key=$.getLong("key");
			if(id!=null)
				csCar=csCarService.getCsCarById(id);
			if(key!=null && id==null)
				csCar=CsCar.Get($.add(CsCar.F.cscId,key));
			//确认域标识
			if(SystemHelper.testHost(csCar.getCscHost()).equals("0")){
				return $.SendHtml("<script>alert('对不起，你没有权限查看该数据');</script>", "UTF-8");
			}
			/************LAZY3Q_CODE_DETAIL************/
			readTimeLineData();
			/************LAZY3Q_CODE_DETAIL************/

			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";
	}


	/**
	 * 根据车牌号或ID查询车辆
	 * 返回ajax数据
	 * @return
	 */
	public String query(){
		try{
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> params = ActionHelper.getQueryFormParams(CsCar.class);
			//强制限制域
			params.put("HOSTS",SystemHelper.testHost(null));
			
			//反强制限制域
			
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=$.getString("asc");//升序字段
			params.put("asc", strAsc);//放入查询条件
			String strDesc=$.getString("desc");//降序字段
			params.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				params.put("desc","csc_id");//那么，默认以主键降序，传给Dao层
								
			String strTerm=$.getString("value");//智能搜索时的参数，一般都是主键后面那个字段
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("cscId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or csc_number like '"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex+=" or csc_model like '"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}
			/************LAZY3Q_CODE_QUERY************/
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("cscId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or csc_number like '%"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}
			/************LAZY3Q_CODE_QUERY************/

			
			//是否需要整个数据对象
			Boolean bObject=$.getBoolean("object",false);
			
			List<CsCar> list = csCarService.getCsCarPage(0,$.getInteger("size",10),params).getResult();
			
			/**
			* OK!取到数据拼成放入Map中，[{value,text,object:{...}},...]
			* value:数据ID，text:数据标识,object,整个对象
			**/
			List<Map> result = new java.util.ArrayList();
			for(CsCar csCar:list){
				Map map = new HashMap();
				map.put("value",csCar.getCscId().toString());
				map.put("text", $.js(csCar.getKeyValue()));
				if(bObject==true)
					map.put("object",csCar);
				result.add(map);
			}
			/************LAZY3Q_AFTER_QUERY************/
			result = new java.util.ArrayList();
			for(CsCar csCar:list){
				Map map = new HashMap();
				map.put("value",csCar.getCscId().toString());
				map.put("text", "["+csCar.getCscHost$()+"]"+$.js(csCar.getKeyValue()));
				if(bObject==true)
					map.put("object",csCar);
				result.add(map);
			}
			/************LAZY3Q_AFTER_QUERY************/

			
			//$.SendAjax这个函数，第一个参数不是字符串，会自动把第一个对象转成json格式的字符串
			return $.SendAjax(result, "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}		
	}
	/**
	 * 定义表单车辆年检提交
	 * @return
	 */
	public String annual()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsCarToken = $.getString("csCarToken");
			final String sessionCsCarToken = $.getSession("csCarToken");
			if($.equals(requestCsCarToken,sessionCsCarToken)){
				$.removeSession("csCarToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csCarService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						/************LAZY3Q_FORM_CODE_annual************/
						csCar.update();
						$.SetTips("年检信息维护成功!");
						/************LAZY3Q_FORM_CODE_annual************/

						return null;
					}
				});
				
			}else{
				$.SetTips("对不起，请勿重复提交");
			}
			/************LAZY3Q_FORM_RETURN_annual************/
			/************LAZY3Q_FORM_RETURN_annual************/

			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		
		//回到列表页
		return $.Redirect($.empty($.getString("entrypoint"))?$.xeh($.getString("editorpoint")):$.xeh($.getString("entrypoint")));
	}
	/***************LAZY3Q_DEFINE_CODE******************/
	
	/**
	 * 读取当前车辆时间轴信息
	 */
	private void readTimeLineData() {
		java.util.Calendar calender = java.util.Calendar.getInstance();
		calender.set(java.util.Calendar.HOUR_OF_DAY, 0);
		calender.set(java.util.Calendar.MINUTE, 0);
		calender.set(java.util.Calendar.SECOND, 0);
		Date begin = $.getDate("begin",calender.getTime());
		$.setRequest("begin", begin);
		Date end = $.getDate("end");
		{
			calender.setTime(begin);
			calender.add(java.util.Calendar.DATE, 5);
			end = calender.getTime();
		}
		$.setRequest("end", end);
		int day = (int)((end.getTime()-begin.getTime())/SYSTEM.DAY)+1;
		$.setRequest("day",day);
		List<CsOrder> orders = CsOrder.where().csoFinishTimeStart(begin).csoCar(csCar.getCscId()).add("definex", "cso_status<3 and cso_status>-1").list(-1);
		ICommonOrderService commonOrderService = $.GetSpringBean("commonOrderService");
		List<Restriction> restrs = commonOrderService.getRestrictions(csCar.getCscId(),day);
		csCar.setValues("orders", orders);
		csCar.setValues("restrs", restrs);
		List<Map> dates=new java.util.ArrayList();
		for(int i=0;i<day;i++){
			dates.add(
				$.add("start", new Date(begin.getTime()+(i*SYSTEM.DAY))).add("finish", new Date(begin.getTime()+(i*SYSTEM.DAY)+SYSTEM.DAY))
			);
		}
		$.setRequest("dates", dates);
		$.setRequest("howlong",end.getTime()-begin.getTime()+SYSTEM.DAY);
	}
	
	/**
	 * 动态车辆选择
	 * @return
	 */
	public String select(){
		execute();
		
		java.util.Calendar calender = java.util.Calendar.getInstance();
		calender.set(java.util.Calendar.HOUR_OF_DAY, 0);
		calender.set(java.util.Calendar.MINUTE, 0);
		calender.set(java.util.Calendar.SECOND, 0);
		Date begin = $.getDate("begin",calender.getTime());
		$.setRequest("begin", begin);
		Date end = $.getDate("end");
		if(end==null || end.before(begin)){
			if(end!=null)
				$.SetTips("结束时间不能在开始时间之前,自动设置为开始时间向后5天");
			calender.setTime(begin);
			calender.add(java.util.Calendar.DATE, 5);
			end = calender.getTime();
		}
		if((end.getTime()-begin.getTime())>SYSTEM.MONTH){
			calender.setTime(begin);
			calender.add(java.util.Calendar.DATE, 30);
			end = calender.getTime();
			$.SetTips("时间跨度不能超过一个月,已经自动退化为一个月");
		}
		$.setRequest("end", end);
		int day = (int)((end.getTime()-begin.getTime())/SYSTEM.DAY)+1;
		$.setRequest("day",day);
		
		Page<CsCar> page = $.getRequest("page");
		List<CsCar> result = page.getResult();
		
		/*
		String ids = "0";
		for(CsCar csCar:result){
			if(!$.empty(ids))
				ids+=",";
			ids+=csCar.getCscId();
		}
		List<CsOrder> allorders = CsOrder.list($.add("csoFinishTimeStart",begin).add("definex", "cso_car in ("+ids+") and cso_status<3 and cso_status>-1"), -1);
		Map<Long,List> mapping = new HashMap();
		for(CsOrder csOrder:allorders){
			Long carid = csOrder.getCsoCar();
			List list = mapping.get(carid);
			if(list==null){
				list = new java.util.ArrayList();
				mapping.put(carid, list);
			}
			list.add(csOrder);
		}*/
		ICommonOrderService commonOrderService = $.GetSpringBean("commonOrderService");
		for(CsCar csCar:result){
			//List<CsOrder> orders = mapping.get(csCar.getCscId());
			List<Restriction> restrs = commonOrderService.getRestrictions(csCar.getCscId(),day);
			//csCar.setValues("orders", orders);
			csCar.setValues("restrs", restrs);
		}
		
		List<Map> dates=new java.util.ArrayList();
		for(int i=0;i<day;i++){
			dates.add(
				$.add("start", new Date(begin.getTime()+(i*SYSTEM.DAY))).add("finish", new Date(begin.getTime()+(i*SYSTEM.DAY)+SYSTEM.DAY))
			);
		}
		$.setRequest("dates", dates);
		
		$.setRequest("howlong",end.getTime()-begin.getTime()+SYSTEM.DAY);
		
		$.SetRequest("define", "car_select.jsp");
		return "define";
	}
	/**
	 * 获取车辆可租用图片
	 * @return
	 */
	public String timeline(){
		try{
			Long id = $.getLong("id");			
			com.ccclubs.service.admin.ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
			ICommonOrderService commonOrderService = $.GetSpringBean("commonOrderService");
			java.util.Calendar calender = java.util.Calendar.getInstance();
			calender.set(java.util.Calendar.HOUR_OF_DAY, 0);
			calender.set(java.util.Calendar.MINUTE, 0);
			calender.set(java.util.Calendar.SECOND, 0);
			$.SetRequest("image", com.ccclubs.helper.TimeLineHelper.getTimeLineImage(new Date(),7,com.ccclubs.helper.TimeLineHelper.getTimelines(id, 7),commonOrderService.getRestrictions(id, 10)));	
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}
		$.SetRequest("define", "car_timeline.jsp");
		return "define";
	}
	/**
	 * 获取车辆可租用图片
	 * @return
	 */
	public String timemap(){
		try{
			Long id = $.getLong("id");			
			com.ccclubs.service.admin.ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
			ICommonOrderService commonOrderService = $.GetSpringBean("commonOrderService");
			java.util.Calendar calender = java.util.Calendar.getInstance();
			calender.set(java.util.Calendar.HOUR_OF_DAY, 0);
			calender.set(java.util.Calendar.MINUTE, 0);
			calender.set(java.util.Calendar.SECOND, 0);
			List<Map> timemaps = com.ccclubs.helper.TimeLineHelper.getTimeLineMap(new Date(),7,com.ccclubs.helper.TimeLineHelper.getTimelines(id, 7),commonOrderService.getRestrictions(id, 10));
			return $.SendAjax(timemaps, "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}
	}
	/**
	 * 动态获取所有车牌号码
	 * @return
	 */
	public String numbers(){
		try{
			Map<String,Object> params=new HashMap();			
			
			//强制限制域
			params.put("HOSTS",SystemHelper.testHost(null));
			
			String strTerm=$.getString("value");
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("cscId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or csc_number like '%"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}
			
			Boolean bObject=$.getBoolean("object",false);
			
			List<CsCar> list = csCarService.getCsCarList(params, $.getInteger("size",10));
			List<Map> result = new java.util.ArrayList();
			for(CsCar csCar:list){
				Map map = new HashMap();
				map.put("value",csCar.getCscNumber());				
				map.put("text", csCar.getCscNumber());
				if(bObject==true)
					map.put("object",csCar);
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
	 * 上下线操作
	 * @return
	 */
	public String status(){
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsCarToken = $.getString("csCarToken");
			final String sessionCsCarToken = $.getSession("csCarToken");
			if($.equals(requestCsCarToken,sessionCsCarToken)){
				$.removeSession("csCarToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csCarService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
							String remark = csCar.getCscRemark();
							String password = csCar.getCscOnPsd();
							Short status = csCar.getCscStatus();
							if(!$.empty(password))//密码用md5加密
								password = $.md5(password);
							
							List<Long> ids=new java.util.ArrayList();
							if($.empty($.getString("ids"))){
								ids.add(csCar.getCscId());
							}else{
								for(String sid:$.getString("ids").split(","))
									ids.add(Long.valueOf(sid));
							}
							
							List<String> results = new java.util.ArrayList();
							for(Long id:ids){
								String sResult = com.ccclubs.service.common.impl.SchedulingService.onlineCar(id, status, remark, password,property,LoginHelper.getLoginId());
								results.add(sResult);
							}
							String strTips = "处理成功：";
							for(String tip:results){
								strTips+=tip;
							}
							$.SetTips(strTips);
						return null;
					}
				});
			}else{
				$.SetTips("对不起，请勿重复提交");
			}
			
			return $.Redirect($.xeh($.getString("entrypoint")));
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		//回到列表页
		return $.Redirect($.empty($.getString("entrypoint"))?$.xeh($.getString("editorpoint")):$.xeh($.getString("entrypoint")));
	}
	
	/**
	 * 车辆调度管理
	 * @return
	 */
	public String dispatch(){
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsCarToken = $.getString("csCarToken");
			final String sessionCsCarToken = $.getSession("csCarToken");
			if($.equals(requestCsCarToken,sessionCsCarToken)){
				$.removeSession("csCarToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csCarService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						String remark = csCar.getCscRemark();//备注信息
						Long targetOutlets  = csCar.getCscOutlets();//目标网点
						CsCar oldCsCar = CsCar.get(csCar.getCscId());
						//插入调度记录数据
						if(com.ccclubs.model.CsScheduling.where().cssStatus((short)0).cssCar(csCar.getCscId()).cssTimeStart(new Date()).count()>0){
							$.SetTips("当前车辆后面可能有定单，不能调度！");
						}else{
							com.ccclubs.service.common.impl.SchedulingService.scheduling(
								oldCsCar.getCscId(),oldCsCar.getCscOutlets(), targetOutlets, new Date(), new Date(), 
								com.ccclubs.service.common.impl.SchedulingService.DispatchType.干预调度
								, null, remark 
							);
							//更新车辆网点
							new CsCar(oldCsCar.getCscId()).cscOutlets(targetOutlets).update();
							$.SetTips("车辆调度成功！");
						}
						
						return null;
					}
				});
			}else{
				$.SetTips("对不起，请勿重复提交");
			}
			
			return $.Redirect($.xeh($.getString("entrypoint")));
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		//回到列表页
		return $.Redirect($.empty($.getString("entrypoint"))?$.xeh($.getString("editorpoint")):$.xeh($.getString("entrypoint")));
	}
	/***************LAZY3Q_DEFINE_CODE******************/


	public ICsCarService getCsCarService()
	{
		return csCarService;
	}

	public void setCsCarService(ICsCarService csCarService)
	{
		this.csCarService = csCarService;
	}

	public CsCar getCsCar()
	{
		return csCar;
	}

	public void setCsCar(CsCar csCar)
	{
		this.csCar = csCar;
	}



	public Long getProperty() {
		return property;
	}



	public void setProperty(Long property) {
		this.property = property;
	}
	
	
}