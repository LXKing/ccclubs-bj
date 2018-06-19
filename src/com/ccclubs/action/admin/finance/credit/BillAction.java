package com.ccclubs.action.admin.finance.credit;

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
import com.ccclubs.model.CsCreditBill;
import com.ccclubs.service.admin.ICsCreditBillService;
/************LAZY3Q_CODE_IMPORT************/
import com.ccclubs.model.CsOrder;
import com.ccclubs.service.common.ICommonMoneyService;
import jxl.Sheet;
import jxl.Workbook;
import java.io.File;
/************LAZY3Q_CODE_IMPORT************/


/**
 * 信用账单的Action
 * @author 飞啊飘啊
 * 感谢struts的Action框架
 */
@SuppressWarnings("unchecked")
public class BillAction
{
	ICsCreditBillService csCreditBillService;
	
	CsCreditBill csCreditBill;
	
	/**
	 * 信用账单管理首页
	 * @return
	 */	
	public String execute()
	{	
		try{
			java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\.([\\S\\s]+)\\.").matcher($.getServletRequest().getServletPath());
			String alias=(String) (matcher.find()?$.setRequest("alias",matcher.group(1)):null);//别名入口
						
			$.setRequest("modelClass",CsCreditBill.class);//把当前Action对应的模型类放入到request中,以作为后面导出、、、、等功能的反射使用
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//列表地址参数状态串，查询条件传递到转跳,就是把当前列表页的路径和参数记下来，放入request,在后面添加、编辑时把这个参数entrypoint带上，提交后再返回到当前列表
			//处理页面的视图数据，比如显示哪些列，自定义显示列等
			ActionHelper.doPageViewFields(CsCreditBill.class);
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> objects = ActionHelper.getQueryFormParams(CsCreditBill.class);
			$.setRequest("PARAMS",ActionHelper.getQueryFormParams(CsCreditBill.class));
									
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=CsCreditBill.C.get($.forMat($.getString("asc")));//升序字段
			objects.put("asc", strAsc);//放入查询条件
			String strDesc=CsCreditBill.C.get($.forMat($.getString("desc")));//降序字段
			objects.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				objects.put("desc","cscb_id");//那么，默认以主键降序，传给Dao层
			
			//把request传过来的查询参数放回request,为了页面能够正确显示上次所选择的值
			Set<String> keys = objects.keySet();
			for(String key:keys)
				$.setRequest(key, objects.get(key));
				
			//把组装好的objects整个Map直接放入request,####原因暂时保密，等你哪天理解出这儿的意思了，你就是高手了
			$.setRequest("objects",objects);
			//强制限制域
			objects.put("HOSTS",SystemHelper.testHost(null));
			
			//反强制限制域
			if(objects.get("cscbMember")!=null && objects.get("cscbMemberNot")==null){
				objects.remove("HOSTS");
			}
			if(objects.get("cscbOrder")!=null && objects.get("cscbOrderNot")==null){
				objects.remove("HOSTS");
			}
			
			//canQuery：表示当前列表是否具有查询功能，这里只是负责传个参数，具体能查不能查，在页面根据这个参数做处理
			$.setRequest("canQuery", $.getBoolean("canQuery",true));			
			if($.getBoolean("export",false)==true)//如果传入了一个数据导出的参数
			{
				//获取当前用户的权限，看当前用户有没有删除权限
				SystemHelper.getSrvLimit("admin/finance/credit/bill.do");
				if($.getRequest("canExport")==null || ((Boolean)$.getRequest("canExport"))==false){
					throw new RuntimeException("您没有权限导出该数据");
				}
				final Map<String, Object> finalParams = objects;
				//调用lazy3q.jar中的导出功能
				//参数说明：(这次导出多少条数据，到数据库查的时候一次查多少条，当前所在第几页,处理器，对应的模型类,要导出哪些字段)
				$.export($.getInteger("size",1000), 100, $.getInteger("page",0), new Export(){//new Export这个类，他会根据参数分次查询，边查边写，目的只有一个，就是为了避免把大量的数据放在内存中
					@Override //重载实现Export的getPage方法，Export会分多次调用这个方法
					public Page getPage(Integer page, int size) {//这时的page和size是Export转化过后的当前页，每页查询数					
						return csCreditBillService.getCsCreditBillPage(page, size, finalParams);
					}
				}, CsCreditBill.class, $.getArray("keys"));
				return null;//数据已经被Export直接写入给输出流了，直接返回null,下面的逻辑就不执行了，
			}
			/************LAZY3Q_CODE_EXECUTE************/
			/************LAZY3Q_CODE_EXECUTE************/

						
			//调用Service获取分页
			Page page = csCreditBillService.getCsCreditBillPage($.getInteger("page", 0), $.getInteger("size",16), objects);
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
	 * 信用账单添加/编辑页面
	 * @return
	 */
	public String edit()
	{		
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//编辑页状态串(包含进入编辑页面时的地址和参数)
			$.setRequest("entrypoint", $.getString("entrypoint"));//列表地址参数状态串，由列表页生产，这里只是携带参数，跟快递差不多
			$.setRequest("ids",$.getString("ids"));//批量编辑的数据ID，多个ID以,号分隔
			$.setSession("csCreditBillToken", $.uuid());//防重复提交Token
			//当前要编辑的数据ID，有这个ID表示修改数据，没这个ID表示添加数据
			Long id=$.getLong("id");
			if(id!=null){
				//把当前要修改的数据查出来,注意了，csCreditBill这个对象为什么不用$.setRequest,因为csCreditBill有get,set方法，由struts处理的
				csCreditBill=csCreditBillService.getCsCreditBillById(id);
				//确认域标识
				if(SystemHelper.testHost(csCreditBill.getCscbHost()).equals("0")){
					return $.SendHtml("<script>alert('对不起，你没有权限编辑该数据');</script>", "UTF-8");
				}
			}
			if(csCreditBill==null)
				csCreditBill = new CsCreditBill();
			//根据自定义配置ctrl中配置的默认值信息设置默认值
			if(CTRL!=null)
				CTRL.setObjectDefaultValue(csCreditBill);
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
	public void on(String type,CsCreditBill object,CsCreditBill old){
		try{
			//执行后台配置的动态代码脚本，如果有在后台配置的话(*_*)
			ActionHelper.executeActionScript(CsCreditBill.class, type, object, old);
			/************LAZY3Q_ACTION_ON************/
			if($.equals(type,"status")){
				if(object.getCscbStatus().shortValue()==1){
					CsCreditBill bill = CsCreditBill.get(object.getCscbId());
					CsOrder order = bill.get$cscbOrder();
					if(order.getCsoStatus().shortValue()==8){
						if(bill.getCscbTitle().indexOf("取消")>-1){
							new CsOrder(bill.getCscbOrder()).csoStatus((short)3).update();
						}else if(bill.getCscbTitle().indexOf("消费")>-1){
							new CsOrder(bill.getCscbOrder()).csoStatus((short)4).update();
						}
					}
				}
			}
			/************LAZY3Q_ACTION_ON************/

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	/**
	 * 保存信用账单
	 * @return
	 */
	public String save()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//主键为空，表示插入,否则表示更新
			final Boolean bIsAdd = ($.empty($.getString("ids")) && $.empty($.getString("PARAMS")) && csCreditBill.getCscbId()==null);
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsCreditBillToken = $.getString("csCreditBillToken");
			final String sessionCsCreditBillToken = $.getSession("csCreditBillToken");
			if($.equals(requestCsCreditBillToken,sessionCsCreditBillToken)){
				$.removeSession("csCreditBillToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csCreditBillService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						/************LAZY3Q_BEFOR_SAVE************/
						/************LAZY3Q_BEFOR_SAVE************/

						if(bIsAdd){
							//批量插入处理
							String vValue = ""+(csCreditBill.getCscbId()==null?"":csCreditBill.getCscbId());
							String sArray[]={""+vValue};
							if(vValue.startsWith("[*]")){
								vValue=vValue.replace("[*]","");
								sArray=vValue.split("[\\s|\u3000]+");				
							}
							for(String sItem:sArray){
								if(!$.empty(sItem))
								csCreditBill.setCscbId(Long.valueOf(sItem));
							
								//设置默认值
								if(csCreditBill.getCscbUpdateTime()==null)
								csCreditBill.setCscbUpdateTime(new Date());
								if(csCreditBill.getCscbAddTime()==null)
								csCreditBill.setCscbAddTime(new Date());
								if(csCreditBill.getCscbEditor()==null)
									csCreditBill.setCscbEditor(LoginHelper.getLoginId());
								if(csCreditBill.getCscbState()==null)
									csCreditBill.setCscbState((short)0);
								if(csCreditBill.getCscbStatus()==null)
									csCreditBill.setCscbStatus((short)0);
								
								//根据自定义的默认值信息设置默认值
								if(CTRL!=null)
									CTRL.setObjectDefaultValue(csCreditBill);
								//添加域标识
								if(SystemHelper.testHost(csCreditBill.getCscbHost()).equals("0")){
									throw new RuntimeException("非法指定域值");
								}
								
								csCreditBill=csCreditBillService.saveCsCreditBill(csCreditBill);			
								
								on("save",csCreditBill,csCreditBill);
							}							
							
							csCreditBillService.updateCsCreditBill$NotNull(csCreditBill);						
							/************LAZY3Q_CODE_ADD************/
							/************LAZY3Q_CODE_ADD************/

							
							//记录操作日志
							LoggerHelper.writeLog(CsCreditBill.class,"add","添加了[信用账单]["+csCreditBill.getCscbId()+"]",(Long)$.getSession("ccclubs_login_id"), csCreditBill,csCreditBill.getCscbId());
							$.SetTips("保存信用账单成功");
						}else{
							String PARAMS = $.getString("PARAMS");
							String ids=$.getString("ids");
							if(!$.empty(PARAMS)){//根据查询条件批量更新
								Map params = $.eval(PARAMS);
								params.put("confirm", 1);
								params.put("definex","1=1");
								csCreditBillService.updateCsCreditBillByConfirm(ActionHelper.getSetValuesFromModel(csCreditBill), params);
								LoggerHelper.writeLog(CsCreditBill.class, "update", 
											"批量修改了[信用账单]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(null,csCreditBill,false)
											,null);
							}else if(!$.empty(ids)){//根据ids批量更新
								String[] array = ids.split(",");
								for(String sid:array){
									csCreditBill.setCscbId(Long.valueOf(sid));
									CsCreditBill oldCsCreditBill = CsCreditBill.get(Long.valueOf(sid));
									csCreditBillService.updateCsCreditBill$NotNull(csCreditBill);
									LoggerHelper.writeLog(CsCreditBill.class, "update", 
											"修改了[信用账单]["+oldCsCreditBill.getCscbId()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsCreditBill, csCreditBill,false)
											,Long.valueOf(sid));
									on($.getString("method"),csCreditBill,oldCsCreditBill);
								}
							}else{
								if($.empty($.getString("edittype"))){
									//设置填充值
									csCreditBill.setCscbEditor(LoginHelper.getLoginId());
									
									CsCreditBill oldCsCreditBill = CsCreditBill.get(csCreditBill.getCscbId());
									csCreditBillService.updateCsCreditBill$NotNull(csCreditBill);
									LoggerHelper.writeLog(CsCreditBill.class, "update", 
											"修改了[信用账单]["+oldCsCreditBill.getCscbId()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsCreditBill, csCreditBill,false)
											,csCreditBill.getCscbId());
									on($.getString("method"),csCreditBill,oldCsCreditBill);									
								}else{
									CsCreditBill oldCsCreditBill = csCreditBillService.getCsCreditBillById(csCreditBill.getCscbId());
									//设置默认值
									csCreditBill.setCscbUpdateTime(oldCsCreditBill.getCscbUpdateTime());	
									csCreditBill.setCscbAddTime(oldCsCreditBill.getCscbAddTime());	
									csCreditBill.setCscbEditor(oldCsCreditBill.getCscbEditor());	
									csCreditBill.setCscbState(oldCsCreditBill.getCscbState());	
									csCreditBill.setCscbStatus(oldCsCreditBill.getCscbStatus());	
									
									//设置填充值
									if(csCreditBill.getCscbEditor()==null)
										csCreditBill.setCscbEditor(LoginHelper.getLoginId());
									//添加域标识
									if(SystemHelper.testHost(csCreditBill.getCscbHost()).equals("0")){
										throw new RuntimeException("非法指定域值");
									}
									
									//从oldCsCreditBill同步csCreditBill未set过的值
									csCreditBill.mergeSet(oldCsCreditBill);
											
									csCreditBillService.updateCsCreditBill(csCreditBill);
									LoggerHelper.writeLog(CsCreditBill.class, "update", 
											"修改了[信用账单]["+oldCsCreditBill.getCscbId()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsCreditBill, csCreditBill,true)
											,csCreditBill.getCscbId());
									on("update",csCreditBill,oldCsCreditBill);
								}
							}
							/************LAZY3Q_CODE_UPDATE************/
							/************LAZY3Q_CODE_UPDATE************/

							//记录操作日志
							$.SetTips("修改信用账单成功");
						}	
						/************LAZY3Q_CODE_SAVE************/
						/************LAZY3Q_CODE_SAVE************/

						
						return null;
					}
				});
				
			}else{
				$.removeSession("csCreditBillToken");//移除令牌	
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
				return $.SendHtml("<script>"+complete+"("+$.json(csCreditBill)+")"+"</script>","UTF-8");
			}
			
			//如果点保存，则回到编辑页，如果点确定，则回到列表页
			//现在知道editorpoint和entrypoint的意义了吗
			//它们由地址加参数(如:http://xxx.xx/xx.xx?xx=xx&xxx=xxx)转成16进制字串进行传递的
			if($.getString("submiter","").indexOf("保存")>-1){//则回到编辑页
				if(bIsAdd)
					return $.Redirect("bill_edit.do?entrypoint="+$.getString("entrypoint")+"&id="+csCreditBill.getCscbId());
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
	 * 删除信用账单
	 * @return
	 */
	public String del()
	{
		try{
			//获取当前用户的权限，看当前用户有没有删除权限
			SystemHelper.getSrvLimit("admin/finance/credit/bill.do");
					/************LAZY3Q_CAN_DEL************/
					/************LAZY3Q_CAN_DEL************/

			if($.getRequest("canDel")==null || ((Boolean)$.getRequest("canDel"))==false){
				throw new RuntimeException("您没有权限删除该数据");
			}
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//多个删除数据动作合并事务过程
			csCreditBillService.executeTransaction(new Function(){
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
							CsCreditBill csCreditBill = csCreditBillService.getCsCreditBillById(id);
							//确认域标识
							if(SystemHelper.testHost(csCreditBill.getCscbHost()).equals("0")){
								throw new RuntimeException("当前用户没有权限删除该数据");
							}
							if(ConfigHelper.GetBoolean("logic_delete")==true)
								csCreditBillService.removeCsCreditBillById(id);
							else
							csCreditBillService.deleteCsCreditBillById(id);			
							//记录操作日志
							if(csCreditBill!=null)
								LoggerHelper.writeLog(CsCreditBill.class,"del","删除了[信用账单]["+csCreditBill.getCscbId()+"]",(Long)$.getSession("ccclubs_login_id"), csCreditBill);
							on("delete",csCreditBill,csCreditBill);
					}
					/************LAZY3Q_CODE_DEL************/
					/************LAZY3Q_CODE_DEL************/

					
					return null;
				}
			});
			
			$.SetTips("删除信用账单成功");
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
	 * 显示信用账单详情
	 * @return
	 */
	public String details()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			Long id=$.getLong("id");
			Long key=$.getLong("key");
			if(id!=null)
				csCreditBill=csCreditBillService.getCsCreditBillById(id);
			if(key!=null && id==null)
				csCreditBill=CsCreditBill.Get($.add(CsCreditBill.F.cscbId,key));
			//确认域标识
			if(SystemHelper.testHost(csCreditBill.getCscbHost()).equals("0")){
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
	 * 根据编号或ID查询信用账单
	 * 返回ajax数据
	 * @return
	 */
	public String query(){
		try{
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> params = ActionHelper.getQueryFormParams(CsCreditBill.class);
			//强制限制域
			params.put("HOSTS",SystemHelper.testHost(null));
			
			//反强制限制域
			if(params.get("cscbMember")!=null && params.get("cscbMemberNot")==null){
				params.remove("HOSTS");
			}
			if(params.get("cscbOrder")!=null && params.get("cscbOrderNot")==null){
				params.remove("HOSTS");
			}
			
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=$.getString("asc");//升序字段
			params.put("asc", strAsc);//放入查询条件
			String strDesc=$.getString("desc");//降序字段
			params.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				params.put("desc","cscb_id");//那么，默认以主键降序，传给Dao层
								
			String strTerm=$.getString("value");//智能搜索时的参数，一般都是主键后面那个字段
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("cscbId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or cscb_id like '"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex+=" or cscb_status like '"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}
			/************LAZY3Q_CODE_QUERY************/
			/************LAZY3Q_CODE_QUERY************/

			
			//是否需要整个数据对象
			Boolean bObject=$.getBoolean("object",false);
			
			List<CsCreditBill> list = csCreditBillService.getCsCreditBillPage(0,$.getInteger("size",10),params).getResult();
			
			/**
			* OK!取到数据拼成放入Map中，[{value,text,object:{...}},...]
			* value:数据ID，text:数据标识,object,整个对象
			**/
			List<Map> result = new java.util.ArrayList();
			for(CsCreditBill csCreditBill:list){
				Map map = new HashMap();
				map.put("value",csCreditBill.getCscbId().toString());
				map.put("text", $.js(csCreditBill.getKeyValue()));
				if(bObject==true)
					map.put("object",csCreditBill);
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
	/************LAZY3Q_DEFINE_CODE************/	
	/**
	 * 帐户代扣
	 */
	public String repay(){
		try{
			//获取当前用户的权限，看当前用户有没有删除权限
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//多个删除数据动作合并事务过程
			String strError = com.ccclubs.service.common.impl.CommonMoneyService.getBean().repayCreditBillByAccount(csCreditBill.getCscbId(), csCreditBill.getCscbRemark());
			$.SetTips(strError);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	/**
	 * 帐单撤销
	 */
	public String cancel(){
		try{
			//获取当前用户的权限，看当前用户有没有删除权限
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//多个删除数据动作合并事务过程
			String strError = com.ccclubs.service.common.impl.CommonMoneyService.getBean().cancelCreditBill(csCreditBill.getCscbId(), csCreditBill.getCscbRemark());
			$.SetTips(strError);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	/**
	 * 已付处理
	 */
	public String payed(){
		try{
			//获取当前用户的权限，看当前用户有没有删除权限
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//多个删除数据动作合并事务过程
			String strError = com.ccclubs.service.common.impl.CommonMoneyService.getBean().setCreditBillPayed(csCreditBill.getCscbId(),csCreditBill.getCscbPaySerial(),csCreditBill.getCscbRemark(),true);
			$.SetTips(strError);
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	/**
	 * 坏帐处理
	 */
	public String bad(){
		try{
			//获取当前用户的权限，看当前用户有没有删除权限
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//多个删除数据动作合并事务过程
			String strError = com.ccclubs.service.common.impl.CommonMoneyService.getBean().setCreditBillBad(csCreditBill.getCscbId(),csCreditBill.getCscbRemark());
			$.SetTips(strError);
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	
	
	/**
	 * 批量设置已付
	 * //会员ID|订单ID|积分充值数|充值描述
	 * @return
	 */
	public String batch(){
		try{		
			String message = csCreditBillService.executeTransaction(new Function(){
				public <T> T execute(Object... arg0){
					Workbook book=null;
					try {
						book = Workbook.getWorkbook(batch);
					} catch (Exception e) {						
						e.printStackTrace();
						return (T) "文件类型或格式错误";
					}					
					ICommonMoneyService commonMoneyService=$.GetSpringBean("commonMoneyService");
			        Sheet sheet = book.getSheet(0);
			        List<Map> messages=new java.util.ArrayList();
			        for(int i=1;i<sheet.getRows();i++){
			        	if($.empty(sheet.getCell(1,i).getContents()))continue;
			        	if($.trim(sheet.getCell(1,i).getContents()).length()>12)continue;
			        	String serial = $.trim(sheet.getCell(0,i).getContents());
			        	Long orderId = getLong(sheet.getCell(1,i).getContents());
			        	Double money = getDouble(sheet.getCell(2,i).getContents());
			        	CsCreditBill creditBill = CsCreditBill.where().cscbOrder(orderId).get();
			        	Map map = $.add("serial", serial).add("order", orderId).add("money", money);	
			        	messages.add(map);
			        	if(creditBill==null){
			        		map.put("message", "信用帐单不存在");		
			        	}else if(money==null){
			        		map.put("message", "无交易金额");	
			        	}else if(money.doubleValue()!=creditBill.getCscbValue().doubleValue()){
			        		map.put("message", "金额有出入");
			        	}else{
			        		commonMoneyService.setCreditBillPayed(creditBill.getCscbId(), serial, csCreditBill.getCscbRemark(), true);		        		
			        	}
			        }
			        
			        book.close();
					return (T)"批量对帐成功";
				}
			});			
			$.SetTips(message);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect("bill.do");
	}
	public Double getDouble(String sDouble){
		if($.trim(sDouble).equals(""))
			return null;
		Double d = null;
		try{
			d = Double.parseDouble($.trim(sDouble));
		}catch(Exception ex){
			return null;
		}
		return d;
	}
	public Long getLong(String sLong){
		if($.trim(sLong).equals(""))
			return null;
		Long l = null;
		try{
			l = Long.parseLong($.trim(sLong));
		}catch(Exception ex){
			return null;
		}
		return l;
	}
	File batch;
	String batchFileName;
	public File getBatch() {
		return batch;
	}
	public void setBatch(File batch) {
		this.batch = batch;
	}
	public String getBatchFileName() {
		return batchFileName;
	}
	public void setBatchFileName(String batchFileName) {
		this.batchFileName = batchFileName;
	}
	
	/************LAZY3Q_DEFINE_CODE************/


	public ICsCreditBillService getCsCreditBillService()
	{
		return csCreditBillService;
	}

	public void setCsCreditBillService(ICsCreditBillService csCreditBillService)
	{
		this.csCreditBillService = csCreditBillService;
	}

	public CsCreditBill getCsCreditBill()
	{
		return csCreditBill;
	}

	public void setCsCreditBill(CsCreditBill csCreditBill)
	{
		this.csCreditBill = csCreditBill;
	}
}