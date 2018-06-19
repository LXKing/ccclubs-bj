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
import com.ccclubs.model.CsLongOrder;
import com.ccclubs.service.admin.ICsLongOrderService;
/************LAZY3Q_CODE_IMPORT************/
import com.ccclubs.model.CsLongOrderDetail;
import com.ccclubs.model.CsLongOrderFee;
/************LAZY3Q_CODE_IMPORT************/


/**
 * 长租订单的Action
 * @author 飞啊飘啊
 * 感谢struts的Action框架
 */
@SuppressWarnings("unchecked")
public class LongorderAction
{
	ICsLongOrderService csLongOrderService;
	
	CsLongOrder csLongOrder;
	
	/**
	 * 长租订单管理首页
	 * @return
	 */	
	public String execute()
	{	
		try{
			java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\.([\\S\\s]+)\\.").matcher($.getServletRequest().getServletPath());
			String alias=(String) (matcher.find()?$.setRequest("alias",matcher.group(1)):null);//别名入口
						
			$.setRequest("modelClass",CsLongOrder.class);//把当前Action对应的模型类放入到request中,以作为后面导出、、、、等功能的反射使用
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//列表地址参数状态串，查询条件传递到转跳,就是把当前列表页的路径和参数记下来，放入request,在后面添加、编辑时把这个参数entrypoint带上，提交后再返回到当前列表
			//处理页面的视图数据，比如显示哪些列，自定义显示列等
			ActionHelper.doPageViewFields(CsLongOrder.class);
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> objects = ActionHelper.getQueryFormParams(CsLongOrder.class);
			$.setRequest("PARAMS",ActionHelper.getQueryFormParams(CsLongOrder.class));
									
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=CsLongOrder.C.get($.forMat($.getString("asc")));//升序字段
			objects.put("asc", strAsc);//放入查询条件
			String strDesc=CsLongOrder.C.get($.forMat($.getString("desc")));//降序字段
			objects.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				objects.put("desc","cslo_id");//那么，默认以主键降序，传给Dao层
			
			//把request传过来的查询参数放回request,为了页面能够正确显示上次所选择的值
			Set<String> keys = objects.keySet();
			for(String key:keys)
				$.setRequest(key, objects.get(key));
				
			//把组装好的objects整个Map直接放入request,####原因暂时保密，等你哪天理解出这儿的意思了，你就是高手了
			$.setRequest("objects",objects);
			//强制限制域
			objects.put("HOSTS",SystemHelper.testHost(null));
			
			//反强制限制域
			if(objects.get("csloMember")!=null && objects.get("csloMemberNot")==null){
				objects.remove("HOSTS");
			}
			
			//canQuery：表示当前列表是否具有查询功能，这里只是负责传个参数，具体能查不能查，在页面根据这个参数做处理
			$.setRequest("canQuery", $.getBoolean("canQuery",true));			
			if($.getBoolean("export",false)==true)//如果传入了一个数据导出的参数
			{
				//获取当前用户的权限，看当前用户有没有删除权限
				SystemHelper.getSrvLimit("admin/service/longorder.do");
				if($.getRequest("canExport")==null || ((Boolean)$.getRequest("canExport"))==false){
					throw new RuntimeException("您没有权限导出该数据");
				}
				final Map<String, Object> finalParams = objects;
				//调用lazy3q.jar中的导出功能
				//参数说明：(这次导出多少条数据，到数据库查的时候一次查多少条，当前所在第几页,处理器，对应的模型类,要导出哪些字段)
				$.export($.getInteger("size",1000), 100, $.getInteger("page",0), new Export(){//new Export这个类，他会根据参数分次查询，边查边写，目的只有一个，就是为了避免把大量的数据放在内存中
					@Override //重载实现Export的getPage方法，Export会分多次调用这个方法
					public Page getPage(Integer page, int size) {//这时的page和size是Export转化过后的当前页，每页查询数					
						return csLongOrderService.getCsLongOrderPage(page, size, finalParams);
					}
				}, CsLongOrder.class, $.getArray("keys"));
				return null;//数据已经被Export直接写入给输出流了，直接返回null,下面的逻辑就不执行了，
			}
			/************LAZY3Q_CODE_EXECUTE************/
			/************LAZY3Q_CODE_EXECUTE************/

						
			//调用Service获取分页
			Page page = csLongOrderService.getCsLongOrderPage($.getInteger("page", 0), $.getInteger("size",16), objects);
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
	 * 长租订单添加/编辑页面
	 * @return
	 */
	public String edit()
	{		
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//编辑页状态串(包含进入编辑页面时的地址和参数)
			$.setRequest("entrypoint", $.getString("entrypoint"));//列表地址参数状态串，由列表页生产，这里只是携带参数，跟快递差不多
			$.setRequest("ids",$.getString("ids"));//批量编辑的数据ID，多个ID以,号分隔
			$.setSession("csLongOrderToken", $.uuid());//防重复提交Token
			//当前要编辑的数据ID，有这个ID表示修改数据，没这个ID表示添加数据
			Long id=$.getLong("id");
			if(id!=null){
				//把当前要修改的数据查出来,注意了，csLongOrder这个对象为什么不用$.setRequest,因为csLongOrder有get,set方法，由struts处理的
				csLongOrder=csLongOrderService.getCsLongOrderById(id);
				//确认域标识
				if(SystemHelper.testHost(csLongOrder.getCsloHost()).equals("0")){
					return $.SendHtml("<script>alert('对不起，你没有权限编辑该数据');</script>", "UTF-8");
				}
			}
			if(csLongOrder==null)
				csLongOrder = new CsLongOrder();
			//根据自定义配置ctrl中配置的默认值信息设置默认值
			if(CTRL!=null)
				CTRL.setObjectDefaultValue(csLongOrder);
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
	public void on(String type,CsLongOrder object,CsLongOrder old){
		try{
			//执行后台配置的动态代码脚本，如果有在后台配置的话(*_*)
			ActionHelper.executeActionScript(CsLongOrder.class, type, object, old);
			/************LAZY3Q_ACTION_ON************/
			/************LAZY3Q_ACTION_ON************/

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	/**
	 * 保存长租订单
	 * @return
	 */
	public String save()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//主键为空，表示插入,否则表示更新
			final Boolean bIsAdd = ($.empty($.getString("ids")) && $.empty($.getString("PARAMS")) && csLongOrder.getCsloId()==null);
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsLongOrderToken = $.getString("csLongOrderToken");
			final String sessionCsLongOrderToken = $.getSession("csLongOrderToken");
			if($.equals(requestCsLongOrderToken,sessionCsLongOrderToken)){
				$.removeSession("csLongOrderToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csLongOrderService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						/************LAZY3Q_BEFOR_SAVE************/
						/************LAZY3Q_BEFOR_SAVE************/

						if(bIsAdd){
							//批量插入处理
							String vValue = ""+(csLongOrder.getCsloId()==null?"":csLongOrder.getCsloId());
							String sArray[]={""+vValue};
							if(vValue.startsWith("[*]")){
								vValue=vValue.replace("[*]","");
								sArray=vValue.split("[\\s|\u3000]+");				
							}
							for(String sItem:sArray){
								if(!$.empty(sItem))
								csLongOrder.setCsloId(Long.valueOf(sItem));
							
								//设置默认值
								if(csLongOrder.getCsloCount()==null)
								csLongOrder.setCsloCount(0);
								if(csLongOrder.getCsloLeaseTerm()==null)
								csLongOrder.setCsloLeaseTerm((double)0);
								if(csLongOrder.getCsloNeedPay()==null)
								csLongOrder.setCsloNeedPay((double)0);
								if(csLongOrder.getCsloRealPay()==null)
								csLongOrder.setCsloRealPay((double)0);
								if(csLongOrder.getCsloUpdateTime()==null)
								csLongOrder.setCsloUpdateTime(new Date());
								if(csLongOrder.getCsloAddTime()==null)
								csLongOrder.setCsloAddTime(new Date());
								if(csLongOrder.getCsloEditor()==null)
								csLongOrder.setCsloEditor((Long) $.getSession("ccclubs_login_id"));
								if(csLongOrder.getCsloStatus()==null)
								csLongOrder.setCsloStatus((short)0);
								
								//根据自定义的默认值信息设置默认值
								if(CTRL!=null)
									CTRL.setObjectDefaultValue(csLongOrder);
								//添加域标识
								if(SystemHelper.testHost(csLongOrder.getCsloHost()).equals("0")){
									throw new RuntimeException("非法指定域值");
								}
								
								csLongOrder=csLongOrderService.saveCsLongOrder(csLongOrder);			
								
								on("save",csLongOrder,csLongOrder);
							}							
							
							csLongOrderService.updateCsLongOrder$NotNull(csLongOrder);						
							/************LAZY3Q_CODE_ADD************/
							/************LAZY3Q_CODE_ADD************/

							
							//记录操作日志
							LoggerHelper.writeLog(CsLongOrder.class,"add","添加了[长租订单]["+csLongOrder.getCsloId()+"]",(Long)$.getSession("ccclubs_login_id"), csLongOrder,csLongOrder.getCsloId());
							$.SetTips("保存长租订单成功");
						}else{
							String PARAMS = $.getString("PARAMS");
							String ids=$.getString("ids");
							if(!$.empty(PARAMS)){//根据查询条件批量更新
								Map params = $.eval(PARAMS);
								params.put("confirm", 1);
								params.put("definex","1=1");
								csLongOrderService.updateCsLongOrderByConfirm(ActionHelper.getSetValuesFromModel(csLongOrder), params);
								LoggerHelper.writeLog(CsLongOrder.class, "update", 
											"批量修改了[长租订单]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(null,csLongOrder,false)
											,null);
							}else if(!$.empty(ids)){//根据ids批量更新
								String[] array = ids.split(",");
								for(String sid:array){
									csLongOrder.setCsloId(Long.valueOf(sid));
									CsLongOrder oldCsLongOrder = CsLongOrder.get(Long.valueOf(sid));
									csLongOrderService.updateCsLongOrder$NotNull(csLongOrder);
									LoggerHelper.writeLog(CsLongOrder.class, "update", 
											"修改了[长租订单]["+oldCsLongOrder.getCsloId()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsLongOrder, csLongOrder,false)
											,Long.valueOf(sid));
									on($.getString("method"),csLongOrder,oldCsLongOrder);
								}
							}else{
								if($.empty($.getString("edittype"))){
									//设置填充值
									
									CsLongOrder oldCsLongOrder = CsLongOrder.get(csLongOrder.getCsloId());
									csLongOrderService.updateCsLongOrder$NotNull(csLongOrder);
									LoggerHelper.writeLog(CsLongOrder.class, "update", 
											"修改了[长租订单]["+oldCsLongOrder.getCsloId()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsLongOrder, csLongOrder,false)
											,csLongOrder.getCsloId());
									on($.getString("method"),csLongOrder,oldCsLongOrder);									
								}else{
									CsLongOrder oldCsLongOrder = csLongOrderService.getCsLongOrderById(csLongOrder.getCsloId());
									//设置默认值
									csLongOrder.setCsloHost(oldCsLongOrder.getCsloHost());	
									csLongOrder.setCsloMember(oldCsLongOrder.getCsloMember());	
									csLongOrder.setCsloCount(oldCsLongOrder.getCsloCount());	
									csLongOrder.setCsloLeaseTerm(oldCsLongOrder.getCsloLeaseTerm());	
									csLongOrder.setCsloDeposit(oldCsLongOrder.getCsloDeposit());	
									csLongOrder.setCsloNeedPay(oldCsLongOrder.getCsloNeedPay());	
									csLongOrder.setCsloRealPay(oldCsLongOrder.getCsloRealPay());	
									csLongOrder.setCsloPactTime(oldCsLongOrder.getCsloPactTime());	
									csLongOrder.setCsloStartTime(oldCsLongOrder.getCsloStartTime());	
									csLongOrder.setCsloFinishTime(oldCsLongOrder.getCsloFinishTime());	
									csLongOrder.setCsloUpdateTime(oldCsLongOrder.getCsloUpdateTime());	
									csLongOrder.setCsloAddTime(oldCsLongOrder.getCsloAddTime());	
									csLongOrder.setCsloSerial(oldCsLongOrder.getCsloSerial());	
									csLongOrder.setCsloEditor(oldCsLongOrder.getCsloEditor());	
									csLongOrder.setCsloError(oldCsLongOrder.getCsloError());	
									csLongOrder.setCsloStatus(oldCsLongOrder.getCsloStatus());	
									
									//设置填充值
									//添加域标识
									if(SystemHelper.testHost(csLongOrder.getCsloHost()).equals("0")){
										throw new RuntimeException("非法指定域值");
									}
									
									//从oldCsLongOrder同步csLongOrder未set过的值
									csLongOrder.mergeSet(oldCsLongOrder);
											
									csLongOrderService.updateCsLongOrder(csLongOrder);
									LoggerHelper.writeLog(CsLongOrder.class, "update", 
											"修改了[长租订单]["+oldCsLongOrder.getCsloId()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsLongOrder, csLongOrder,true)
											,csLongOrder.getCsloId());
									on("update",csLongOrder,oldCsLongOrder);
								}
							}
							/************LAZY3Q_CODE_UPDATE************/
							/************LAZY3Q_CODE_UPDATE************/

							//记录操作日志
							$.SetTips("修改长租订单成功");
						}	
						/************LAZY3Q_CODE_SAVE************/
						/************LAZY3Q_CODE_SAVE************/

						
						return null;
					}
				});
				
			}else{
				$.removeSession("csLongOrderToken");//移除令牌	
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
				return $.SendHtml("<script>"+complete+"("+$.json(csLongOrder)+")"+"</script>","UTF-8");
			}
			
			//如果点保存，则回到编辑页，如果点确定，则回到列表页
			//现在知道editorpoint和entrypoint的意义了吗
			//它们由地址加参数(如:http://xxx.xx/xx.xx?xx=xx&xxx=xxx)转成16进制字串进行传递的
			if($.getString("submiter","").indexOf("保存")>-1){//则回到编辑页
				if(bIsAdd)
					return $.Redirect("longorder_edit.do?entrypoint="+$.getString("entrypoint")+"&id="+csLongOrder.getCsloId());
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
	 * 删除长租订单
	 * @return
	 */
	public String del()
	{
		try{
			//获取当前用户的权限，看当前用户有没有删除权限
			SystemHelper.getSrvLimit("admin/service/longorder.do");
					/************LAZY3Q_CAN_DEL************/
					/************LAZY3Q_CAN_DEL************/

			if($.getRequest("canDel")==null || ((Boolean)$.getRequest("canDel"))==false){
				throw new RuntimeException("您没有权限删除该数据");
			}
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//多个删除数据动作合并事务过程
			csLongOrderService.executeTransaction(new Function(){
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
							CsLongOrder csLongOrder = csLongOrderService.getCsLongOrderById(id);
							//确认域标识
							if(SystemHelper.testHost(csLongOrder.getCsloHost()).equals("0")){
								throw new RuntimeException("当前用户没有权限删除该数据");
							}
							if(ConfigHelper.GetBoolean("logic_delete")==true)
								csLongOrderService.removeCsLongOrderById(id);
							else
							csLongOrderService.deleteCsLongOrderById(id);			
							//记录操作日志
							if(csLongOrder!=null)
								LoggerHelper.writeLog(CsLongOrder.class,"del","删除了[长租订单]["+csLongOrder.getCsloId()+"]",(Long)$.getSession("ccclubs_login_id"), csLongOrder);
							on("delete",csLongOrder,csLongOrder);
					}
					/************LAZY3Q_CODE_DEL************/
					/************LAZY3Q_CODE_DEL************/

					
					return null;
				}
			});
			
			$.SetTips("删除长租订单成功");
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
	 * 显示长租订单详情
	 * @return
	 */
	public String details()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			Long id=$.getLong("id");
			Long key=$.getLong("key");
			if(id!=null)
				csLongOrder=csLongOrderService.getCsLongOrderById(id);
			if(key!=null && id==null)
				csLongOrder=CsLongOrder.Get($.add(CsLongOrder.F.csloId,key));
			//确认域标识
			if(SystemHelper.testHost(csLongOrder.getCsloHost()).equals("0")){
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
	 * 根据订单编号或ID查询长租订单
	 * 返回ajax数据
	 * @return
	 */
	public String query(){
		try{
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> params = ActionHelper.getQueryFormParams(CsLongOrder.class);
			//强制限制域
			params.put("HOSTS",SystemHelper.testHost(null));
			
			//反强制限制域
			if(params.get("csloMember")!=null && params.get("csloMemberNot")==null){
				params.remove("HOSTS");
			}
			
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=$.getString("asc");//升序字段
			params.put("asc", strAsc);//放入查询条件
			String strDesc=$.getString("desc");//降序字段
			params.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				params.put("desc","cslo_id");//那么，默认以主键降序，传给Dao层
								
			String strTerm=$.getString("value");//智能搜索时的参数，一般都是主键后面那个字段
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("csloId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or cslo_id like '"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex+=" or cslo_status like '"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}
			/************LAZY3Q_CODE_QUERY************/
			/************LAZY3Q_CODE_QUERY************/

			
			//是否需要整个数据对象
			Boolean bObject=$.getBoolean("object",false);
			
			List<CsLongOrder> list = csLongOrderService.getCsLongOrderPage(0,$.getInteger("size",10),params).getResult();
			
			/**
			* OK!取到数据拼成放入Map中，[{value,text,object:{...}},...]
			* value:数据ID，text:数据标识,object,整个对象
			**/
			List<Map> result = new java.util.ArrayList();
			for(CsLongOrder csLongOrder:list){
				Map map = new HashMap();
				map.put("value",csLongOrder.getCsloId().toString());
				map.put("text", $.js(csLongOrder.getKeyValue()));
				if(bObject==true)
					map.put("object",csLongOrder);
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
	/**
	 * 定义表单下单提交
	 * @return
	 */
	public String add()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsLongOrderToken = $.getString("csLongOrderToken");
			final String sessionCsLongOrderToken = $.getSession("csLongOrderToken");
			if($.equals(requestCsLongOrderToken,sessionCsLongOrderToken)){
				$.removeSession("csLongOrderToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csLongOrderService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						/************LAZY3Q_FORM_CODE_add************/
						String cids = $.getString("cids");
						if(cids==null || $.empty(cids)){
							$.SetTips("未添加订单车辆");
							return null;
						}
						List<Map> array = $.eval(cids);
						if(csLongOrder.getCsloStartTime().after(csLongOrder.getCsloFinishTime())){
							$.SetTips("合同开始时间不能迟于合同结束时间");
							return null;
						}
						csLongOrder.setCsloUpdateTime(new Date());
						csLongOrder.setCsloAddTime(new Date());
						csLongOrder.setCsloEditor(LoginHelper.getLoginId());
						csLongOrder.setCsloStatus((short)0);	
						csLongOrder.setCsloLeaseTerm(0d);
						csLongOrder.setCsloNeedPay(0d);
						csLongOrder.setCsloRealPay(0d);
						csLongOrder.setCsloError("押金未缴");
						csLongOrder.setCsloCount(0);
						csLongOrder = csLongOrder.save();
						//添加订单车辆明细
						for(Map map:array){
							new com.ccclubs.model.CsLongOrderDetail(
								csLongOrder.getCsloHost()//城市 [非空]
							 	,csLongOrder.getCsloId()//所属订单 [非空]
							 	,$.parseLong(map.get("car"))//预订车辆 [非空]
							 	,$.parseDouble(map.get("rent"))//租金
							 	,null//交付时里程
							 	,null//还车时里程
							 	,null//车辆交付时间
							 	,null//车辆归还时间
							 	,new Date()//修改时间 [非空]
							 	,new Date()//添加时间 [非空]
							 	,LoginHelper.getLoginId()//操作人 [非空]
							 	,""+map.get("remark")
							 	,(short)0//状态 [非空]
							 ).save();
						}
						CsLongOrder.compute(csLongOrder.getCsloId());
						$.SetTips("订单添加成功!");
						/************LAZY3Q_FORM_CODE_add************/

						return null;
					}
				});
				
			}else{
				$.SetTips("对不起，请勿重复提交");
			}
			/************LAZY3Q_FORM_RETURN_add************/
			/************LAZY3Q_FORM_RETURN_add************/

			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		
		//回到列表页
		return $.Redirect($.empty($.getString("entrypoint"))?$.xeh($.getString("editorpoint")):$.xeh($.getString("entrypoint")));
	}
	/**
	 * 定义表单交付提交
	 * @return
	 */
	public String deliver()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsLongOrderToken = $.getString("csLongOrderToken");
			final String sessionCsLongOrderToken = $.getSession("csLongOrderToken");
			if($.equals(requestCsLongOrderToken,sessionCsLongOrderToken)){
				$.removeSession("csLongOrderToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csLongOrderService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						/************LAZY3Q_FORM_CODE_deliver************/
						String infos = $.getString("infos");
						if(infos==null || $.empty(infos)){
							$.SetTips("未发现要交付的车辆");
							return null;
						}
						List<Map> array = $.eval(infos);
						//添加订单车辆明细
						for(Map map:array){
							CsLongOrderDetail.where().cslodId(map.get("id"))
							.set().cslodStatus(1).cslodSetKilo(map.get("setKilo")).cslodSetTime(map.get("setDate")).update();
						}
						CsLongOrder.where().csloId(csLongOrder.getCsloId()).set().csloRemark(csLongOrder.getCsloRemark()).csloStatus((short)1).update();
						$.SetTips("操作成功");
						/************LAZY3Q_FORM_CODE_deliver************/

						return null;
					}
				});
				
			}else{
				$.SetTips("对不起，请勿重复提交");
			}
			/************LAZY3Q_FORM_RETURN_deliver************/
			/************LAZY3Q_FORM_RETURN_deliver************/

			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		
		//回到列表页
		return $.Redirect($.empty($.getString("entrypoint"))?$.xeh($.getString("editorpoint")):$.xeh($.getString("entrypoint")));
	}
	/**
	 * 定义表单续租提交
	 * @return
	 */
	public String reorder()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsLongOrderToken = $.getString("csLongOrderToken");
			final String sessionCsLongOrderToken = $.getSession("csLongOrderToken");
			if($.equals(requestCsLongOrderToken,sessionCsLongOrderToken)){
				$.removeSession("csLongOrderToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csLongOrderService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						/************LAZY3Q_FORM_CODE_reorder************/
						CsLongOrder.where().csloId(csLongOrder.getCsloId())
						.set().csloFinishTime($.getDate("to")).csloRemark(csLongOrder.getCsloRemark()).update();
						CsLongOrder.compute(csLongOrder.getCsloId());
						$.SetTips("订单续租成功!");
						/************LAZY3Q_FORM_CODE_reorder************/

						return null;
					}
				});
				
			}else{
				$.SetTips("对不起，请勿重复提交");
			}
			/************LAZY3Q_FORM_RETURN_reorder************/
			/************LAZY3Q_FORM_RETURN_reorder************/

			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		
		//回到列表页
		return $.Redirect($.empty($.getString("entrypoint"))?$.xeh($.getString("editorpoint")):$.xeh($.getString("entrypoint")));
	}
	/**
	 * 定义表单缴费提交
	 * @return
	 */
	public String pay()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsLongOrderToken = $.getString("csLongOrderToken");
			final String sessionCsLongOrderToken = $.getSession("csLongOrderToken");
			if($.equals(requestCsLongOrderToken,sessionCsLongOrderToken)){
				$.removeSession("csLongOrderToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csLongOrderService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						/************LAZY3Q_FORM_CODE_pay************/
						CsLongOrder dbLongOrder = CsLongOrder.get(csLongOrder.getCsloId());
						new com.ccclubs.model.CsLongOrderFee(
							dbLongOrder.getCsloHost()//城市 [非空]
						 	,csLongOrder.getCsloId()//所属长租 [非空]
						 	,$.getString("feeTitle")//项目名称 [非空]
						 	,$.getShort("moneyType")//费用类型 [非空]
						 	,$.getShort("type")//交费方式 [非空]
						 	,$.getDouble("ammount")//费用金额 [非空]
						 	,$.getString("remark")//备注信息 [非空]
						 	,new Date()//修改时间 [非空]
						 	,new Date()//添加时间 [非空]
						 	,null//入帐信息
						 	,(short)0//财务确认 [非空]
						 	,(short)0//状态 [非空]
						 ).save();
						$.SetTips("订单缴费操作成功!");
						/************LAZY3Q_FORM_CODE_pay************/

						return null;
					}
				});
				
			}else{
				$.SetTips("对不起，请勿重复提交");
			}
			/************LAZY3Q_FORM_RETURN_pay************/
			/************LAZY3Q_FORM_RETURN_pay************/

			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		
		//回到列表页
		return $.Redirect($.empty($.getString("entrypoint"))?$.xeh($.getString("editorpoint")):$.xeh($.getString("entrypoint")));
	}
	/**
	 * 定义表单作废提交
	 * @return
	 */
	public String undo()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsLongOrderToken = $.getString("csLongOrderToken");
			final String sessionCsLongOrderToken = $.getSession("csLongOrderToken");
			if($.equals(requestCsLongOrderToken,sessionCsLongOrderToken)){
				$.removeSession("csLongOrderToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csLongOrderService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						/************LAZY3Q_FORM_CODE_undo************/
						CsLongOrder.where().csloId(csLongOrder.getCsloId()).set().csloRemark(csLongOrder.getCsloRemark()).csloStatus((short)7).update();
						CsLongOrderFee.where().cslofLongorder(csLongOrder.getCsloId()).set().cslofState(0).cslofStatus((short)1).update();
						CsLongOrderDetail.where().cslodLongOrder(csLongOrder.getCsloId()).set().cslodStatus(3).update();
						$.SetTips("订单作废操作成功!");
						/************LAZY3Q_FORM_CODE_undo************/

						return null;
					}
				});
				
			}else{
				$.SetTips("对不起，请勿重复提交");
			}
			/************LAZY3Q_FORM_RETURN_undo************/
			/************LAZY3Q_FORM_RETURN_undo************/

			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		
		//回到列表页
		return $.Redirect($.empty($.getString("entrypoint"))?$.xeh($.getString("editorpoint")):$.xeh($.getString("entrypoint")));
	}
	/**
	 * 定义表单完成提交
	 * @return
	 */
	public String complete()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsLongOrderToken = $.getString("csLongOrderToken");
			final String sessionCsLongOrderToken = $.getSession("csLongOrderToken");
			if($.equals(requestCsLongOrderToken,sessionCsLongOrderToken)){
				$.removeSession("csLongOrderToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csLongOrderService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						/************LAZY3Q_FORM_CODE_complete************/
						String infos = $.getString("infos");
						if(infos==null || $.empty(infos)){
							$.SetTips("未发现要还车的车辆");
							return null;
						}
						List<Map> array = $.eval(infos);
						//添加订单车辆明细
						for(Map map:array){
							CsLongOrderDetail.where().cslodId(map.get("id"))
							.set().cslodStatus(2).cslodRetKilo(map.get("retKilo")).cslodRetTime(map.get("retDate")).update();
						}
						CsLongOrder.where().csloId(csLongOrder.getCsloId()).set().csloRemark(csLongOrder.getCsloRemark()).csloStatus((short)4).update();
						$.SetTips("操作成功");
						/************LAZY3Q_FORM_CODE_complete************/

						return null;
					}
				});
				
			}else{
				$.SetTips("对不起，请勿重复提交");
			}
			/************LAZY3Q_FORM_RETURN_complete************/
			/************LAZY3Q_FORM_RETURN_complete************/

			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		
		//回到列表页
		return $.Redirect($.empty($.getString("entrypoint"))?$.xeh($.getString("editorpoint")):$.xeh($.getString("entrypoint")));
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/


	public ICsLongOrderService getCsLongOrderService()
	{
		return csLongOrderService;
	}

	public void setCsLongOrderService(ICsLongOrderService csLongOrderService)
	{
		this.csLongOrderService = csLongOrderService;
	}

	public CsLongOrder getCsLongOrder()
	{
		return csLongOrder;
	}

	public void setCsLongOrder(CsLongOrder csLongOrder)
	{
		this.csLongOrder = csLongOrder;
	}
}