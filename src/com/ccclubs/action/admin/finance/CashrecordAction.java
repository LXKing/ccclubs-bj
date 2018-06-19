package com.ccclubs.action.admin.finance;

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
import com.ccclubs.model.CsCashRecord;
import com.ccclubs.service.admin.ICsCashRecordService;
/************LAZY3Q_CODE_IMPORT************/
import com.ccclubs.model.*;
/************LAZY3Q_CODE_IMPORT************/


/**
 * 提现记录的Action
 * @author 飞啊飘啊
 * 感谢struts的Action框架
 */
@SuppressWarnings("unchecked")
public class CashrecordAction
{
	ICsCashRecordService csCashRecordService;
	
	CsCashRecord csCashRecord;
	
	/**
	 * 提现记录管理首页
	 * @return
	 */	
	public String execute()
	{	
		try{
			java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\.([\\S\\s]+)\\.").matcher($.getServletRequest().getServletPath());
			String alias=(String) (matcher.find()?$.setRequest("alias",matcher.group(1)):null);//别名入口
						
			$.setRequest("modelClass",CsCashRecord.class);//把当前Action对应的模型类放入到request中,以作为后面导出、、、、等功能的反射使用
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//列表地址参数状态串，查询条件传递到转跳,就是把当前列表页的路径和参数记下来，放入request,在后面添加、编辑时把这个参数entrypoint带上，提交后再返回到当前列表
			//处理页面的视图数据，比如显示哪些列，自定义显示列等
			ActionHelper.doPageViewFields(CsCashRecord.class);
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> objects = ActionHelper.getQueryFormParams(CsCashRecord.class);
			$.setRequest("PARAMS",ActionHelper.getQueryFormParams(CsCashRecord.class));
									
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=CsCashRecord.C.get($.forMat($.getString("asc")));//升序字段
			objects.put("asc", strAsc);//放入查询条件
			String strDesc=CsCashRecord.C.get($.forMat($.getString("desc")));//降序字段
			objects.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				objects.put("desc","cscr_id");//那么，默认以主键降序，传给Dao层
			
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
				SystemHelper.getSrvLimit("admin/finance/cashrecord.do");
				if($.getRequest("canExport")==null || ((Boolean)$.getRequest("canExport"))==false){
					throw new RuntimeException("您没有权限导出该数据");
				}
				final Map<String, Object> finalParams = objects;
				//调用lazy3q.jar中的导出功能
				//参数说明：(这次导出多少条数据，到数据库查的时候一次查多少条，当前所在第几页,处理器，对应的模型类,要导出哪些字段)
				$.export($.getInteger("size",1000), 100, $.getInteger("page",0), new Export(){//new Export这个类，他会根据参数分次查询，边查边写，目的只有一个，就是为了避免把大量的数据放在内存中
					@Override //重载实现Export的getPage方法，Export会分多次调用这个方法
					public Page getPage(Integer page, int size) {//这时的page和size是Export转化过后的当前页，每页查询数					
						return csCashRecordService.getCsCashRecordPage(page, size, finalParams);
					}
				}, CsCashRecord.class, $.getArray("keys"));
				return null;//数据已经被Export直接写入给输出流了，直接返回null,下面的逻辑就不执行了，
			}
			/************LAZY3Q_CODE_EXECUTE************/
			/************LAZY3Q_CODE_EXECUTE************/

						
			//调用Service获取分页
			Page page = csCashRecordService.getCsCashRecordPage($.getInteger("page", 0), $.getInteger("size",16), objects);
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
	 * 提现记录添加/编辑页面
	 * @return
	 */
	public String edit()
	{		
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//编辑页状态串(包含进入编辑页面时的地址和参数)
			$.setRequest("entrypoint", $.getString("entrypoint"));//列表地址参数状态串，由列表页生产，这里只是携带参数，跟快递差不多
			$.setRequest("ids",$.getString("ids"));//批量编辑的数据ID，多个ID以,号分隔
			$.setSession("csCashRecordToken", $.uuid());//防重复提交Token
			//当前要编辑的数据ID，有这个ID表示修改数据，没这个ID表示添加数据
			Long id=$.getLong("id");
			if(id!=null){
				//把当前要修改的数据查出来,注意了，csCashRecord这个对象为什么不用$.setRequest,因为csCashRecord有get,set方法，由struts处理的
				csCashRecord=csCashRecordService.getCsCashRecordById(id);
				//确认域标识
				if(SystemHelper.testHost(csCashRecord.getCscrHost()).equals("0")){
					return $.SendHtml("<script>alert('对不起，你没有权限编辑该数据');</script>", "UTF-8");
				}
			}
			if(csCashRecord==null)
				csCashRecord = new CsCashRecord();
			//根据自定义配置ctrl中配置的默认值信息设置默认值
			if(CTRL!=null)
				CTRL.setObjectDefaultValue(csCashRecord);
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
	public void on(String type,CsCashRecord object,CsCashRecord old){
		try{
			//执行后台配置的动态代码脚本，如果有在后台配置的话(*_*)
			ActionHelper.executeActionScript(CsCashRecord.class, type, object, old);
			/************LAZY3Q_ACTION_ON************/
			/************LAZY3Q_ACTION_ON************/

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	/**
	 * 保存提现记录
	 * @return
	 */
	public String save()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//主键为空，表示插入,否则表示更新
			final Boolean bIsAdd = ($.empty($.getString("ids")) && $.empty($.getString("PARAMS")) && csCashRecord.getCscrId()==null);
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsCashRecordToken = $.getString("csCashRecordToken");
			final String sessionCsCashRecordToken = $.getSession("csCashRecordToken");
			if($.equals(requestCsCashRecordToken,sessionCsCashRecordToken)){
				$.removeSession("csCashRecordToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csCashRecordService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						/************LAZY3Q_BEFOR_SAVE************/
						/************LAZY3Q_BEFOR_SAVE************/

						if(bIsAdd){
							//批量插入处理
							String vValue = ""+(csCashRecord.getCscrTradeNo()==null?"":csCashRecord.getCscrTradeNo());
							String sArray[]={""+vValue};
							if(vValue.startsWith("[*]")){
								vValue=vValue.replace("[*]","");
								sArray=vValue.split("[\\s|\u3000]+");				
							}
							for(String sItem:sArray){
								if(!$.empty(sItem))
								csCashRecord.setCscrTradeNo(String.valueOf(sItem));
							
								//设置默认值
								if(csCashRecord.getCscrAdder()==null)
									csCashRecord.setCscrAdder(LoginHelper.getLoginId());
								if(csCashRecord.getCscrUpdateTime()==null)
									csCashRecord.setCscrUpdateTime(new Date());
								if(csCashRecord.getCscrAddTime()==null)
									csCashRecord.setCscrAddTime(new Date());
								if(csCashRecord.getCscrStatus()==null)
								csCashRecord.setCscrStatus((short)0);
								
								//根据自定义的默认值信息设置默认值
								if(CTRL!=null)
									CTRL.setObjectDefaultValue(csCashRecord);
								//添加域标识
								if(SystemHelper.testHost(csCashRecord.getCscrHost()).equals("0")){
									throw new RuntimeException("非法指定域值");
								}
								
								csCashRecord=csCashRecordService.saveCsCashRecord(csCashRecord);			
								
								on("save",csCashRecord,csCashRecord);
							}							
							
							csCashRecordService.updateCsCashRecord$NotNull(csCashRecord);						
							/************LAZY3Q_CODE_ADD************/
							/************LAZY3Q_CODE_ADD************/

							
							//记录操作日志
							LoggerHelper.writeLog(CsCashRecord.class,"add","添加了[提现记录]["+csCashRecord.getCscrTradeNo()+"]",(Long)$.getSession("ccclubs_login_id"), csCashRecord,csCashRecord.getCscrId());
							$.SetTips("保存提现记录成功");
						}else{
							String PARAMS = $.getString("PARAMS");
							String ids=$.getString("ids");
							if(!$.empty(PARAMS)){//根据查询条件批量更新
								Map params = $.eval(PARAMS);
								params.put("confirm", 1);
								params.put("definex","1=1");
								csCashRecordService.updateCsCashRecordByConfirm(ActionHelper.getSetValuesFromModel(csCashRecord), params);
								LoggerHelper.writeLog(CsCashRecord.class, "update", 
											"批量修改了[提现记录]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(null,csCashRecord,false)
											,null);
							}else if(!$.empty(ids)){//根据ids批量更新
								String[] array = ids.split(",");
								for(String sid:array){
									csCashRecord.setCscrId(Long.valueOf(sid));
									CsCashRecord oldCsCashRecord = CsCashRecord.get(Long.valueOf(sid));
									csCashRecordService.updateCsCashRecord$NotNull(csCashRecord);
									LoggerHelper.writeLog(CsCashRecord.class, "update", 
											"修改了[提现记录]["+oldCsCashRecord.getCscrTradeNo()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsCashRecord, csCashRecord,false)
											,Long.valueOf(sid));
									on($.getString("method"),csCashRecord,oldCsCashRecord);
								}
							}else{
								if($.empty($.getString("edittype"))){
									//设置填充值
									csCashRecord.setCscrUpdateTime(new Date());
									
									CsCashRecord oldCsCashRecord = CsCashRecord.get(csCashRecord.getCscrId());
									csCashRecordService.updateCsCashRecord$NotNull(csCashRecord);
									LoggerHelper.writeLog(CsCashRecord.class, "update", 
											"修改了[提现记录]["+oldCsCashRecord.getCscrTradeNo()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsCashRecord, csCashRecord,false)
											,csCashRecord.getCscrId());
									on($.getString("method"),csCashRecord,oldCsCashRecord);									
								}else{
									CsCashRecord oldCsCashRecord = csCashRecordService.getCsCashRecordById(csCashRecord.getCscrId());
									//设置默认值
									csCashRecord.setCscrVoucher(oldCsCashRecord.getCscrVoucher());	
									csCashRecord.setCscrAdder(oldCsCashRecord.getCscrAdder());	
									csCashRecord.setCscrChecker(oldCsCashRecord.getCscrChecker());	
									csCashRecord.setCscrRemark(oldCsCashRecord.getCscrRemark());	
									csCashRecord.setCscrUpdateTime(oldCsCashRecord.getCscrUpdateTime());	
									csCashRecord.setCscrAddTime(oldCsCashRecord.getCscrAddTime());	
									csCashRecord.setCscrStatus(oldCsCashRecord.getCscrStatus());	
									
									//设置填充值
									if(csCashRecord.getCscrUpdateTime()==null)
										csCashRecord.setCscrUpdateTime(new Date());
									//添加域标识
									if(SystemHelper.testHost(csCashRecord.getCscrHost()).equals("0")){
										throw new RuntimeException("非法指定域值");
									}
									
									//从oldCsCashRecord同步csCashRecord未set过的值
									csCashRecord.mergeSet(oldCsCashRecord);
											
									csCashRecordService.updateCsCashRecord(csCashRecord);
									LoggerHelper.writeLog(CsCashRecord.class, "update", 
											"修改了[提现记录]["+oldCsCashRecord.getCscrTradeNo()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsCashRecord, csCashRecord,true)
											,csCashRecord.getCscrId());
									on("update",csCashRecord,oldCsCashRecord);
								}
							}
							/************LAZY3Q_CODE_UPDATE************/
							/************LAZY3Q_CODE_UPDATE************/

							//记录操作日志
							$.SetTips("修改提现记录成功");
						}	
						/************LAZY3Q_CODE_SAVE************/
						/************LAZY3Q_CODE_SAVE************/

						
						return null;
					}
				});
				
			}else{
				$.removeSession("csCashRecordToken");//移除令牌	
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
				return $.SendHtml("<script>"+complete+"("+$.json(csCashRecord)+")"+"</script>","UTF-8");
			}
			
			//如果点保存，则回到编辑页，如果点确定，则回到列表页
			//现在知道editorpoint和entrypoint的意义了吗
			//它们由地址加参数(如:http://xxx.xx/xx.xx?xx=xx&xxx=xxx)转成16进制字串进行传递的
			if($.getString("submiter","").indexOf("保存")>-1){//则回到编辑页
				if(bIsAdd)
					return $.Redirect("cashrecord_edit.do?entrypoint="+$.getString("entrypoint")+"&id="+csCashRecord.getCscrId());
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
	 * 删除提现记录
	 * @return
	 */
	public String del()
	{
		try{
			//获取当前用户的权限，看当前用户有没有删除权限
			SystemHelper.getSrvLimit("admin/finance/cashrecord.do");
					/************LAZY3Q_CAN_DEL************/
					/************LAZY3Q_CAN_DEL************/

			if($.getRequest("canDel")==null || ((Boolean)$.getRequest("canDel"))==false){
				throw new RuntimeException("您没有权限删除该数据");
			}
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//多个删除数据动作合并事务过程
			csCashRecordService.executeTransaction(new Function(){
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
							CsCashRecord csCashRecord = csCashRecordService.getCsCashRecordById(id);
							//确认域标识
							if(SystemHelper.testHost(csCashRecord.getCscrHost()).equals("0")){
								throw new RuntimeException("当前用户没有权限删除该数据");
							}
							if(ConfigHelper.GetBoolean("logic_delete")==true)
								csCashRecordService.removeCsCashRecordById(id);
							else
							csCashRecordService.deleteCsCashRecordById(id);			
							//记录操作日志
							if(csCashRecord!=null)
								LoggerHelper.writeLog(CsCashRecord.class,"del","删除了[提现记录]["+csCashRecord.getCscrTradeNo()+"]",(Long)$.getSession("ccclubs_login_id"), csCashRecord);
							on("delete",csCashRecord,csCashRecord);
					}
					/************LAZY3Q_CODE_DEL************/
					/************LAZY3Q_CODE_DEL************/

					
					return null;
				}
			});
			
			$.SetTips("删除提现记录成功");
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
	 * 显示提现记录详情
	 * @return
	 */
	public String details()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			Long id=$.getLong("id");
			Long key=$.getLong("key");
			if(id!=null)
				csCashRecord=csCashRecordService.getCsCashRecordById(id);
			if(key!=null && id==null)
				csCashRecord=CsCashRecord.Get($.add(CsCashRecord.F.cscrId,key));
			//确认域标识
			if(SystemHelper.testHost(csCashRecord.getCscrHost()).equals("0")){
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
	 * 根据流水号或ID查询提现记录
	 * 返回ajax数据
	 * @return
	 */
	public String query(){
		try{
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> params = ActionHelper.getQueryFormParams(CsCashRecord.class);
			//强制限制域
			params.put("HOSTS",SystemHelper.testHost(null));
			
			//反强制限制域
			
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=$.getString("asc");//升序字段
			params.put("asc", strAsc);//放入查询条件
			String strDesc=$.getString("desc");//降序字段
			params.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				params.put("desc","cscr_id");//那么，默认以主键降序，传给Dao层
								
			String strTerm=$.getString("value");//智能搜索时的参数，一般都是主键后面那个字段
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("cscrId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or cscr_trade_no like '"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}
			/************LAZY3Q_CODE_QUERY************/
			/************LAZY3Q_CODE_QUERY************/

			
			//是否需要整个数据对象
			Boolean bObject=$.getBoolean("object",false);
			
			List<CsCashRecord> list = csCashRecordService.getCsCashRecordPage(0,$.getInteger("size",10),params).getResult();
			
			/**
			* OK!取到数据拼成放入Map中，[{value,text,object:{...}},...]
			* value:数据ID，text:数据标识,object,整个对象
			**/
			List<Map> result = new java.util.ArrayList();
			for(CsCashRecord csCashRecord:list){
				Map map = new HashMap();
				map.put("value",csCashRecord.getCscrId().toString());
				map.put("text", $.js(csCashRecord.getKeyValue()));
				if(bObject==true)
					map.put("object",csCashRecord);
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
	 * 定义表单提款提交
	 * @return
	 */
	public String withdraw()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsCashRecordToken = $.getString("csCashRecordToken");
			final String sessionCsCashRecordToken = $.getSession("csCashRecordToken");
			if($.equals(requestCsCashRecordToken,sessionCsCashRecordToken)){
				$.removeSession("csCashRecordToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csCashRecordService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						/************LAZY3Q_FORM_CODE_withdraw************/
						String strStart = $.getString("start");
						String strFinish = $.getString("finish");
						Long host = csCashRecord.getCscrHost();
						if($.empty(strStart)){
							$.SetTips("开始日期不能为空");
							return null;
						}
						if($.empty(strFinish)){
							$.SetTips("结束日期不能为空");
							return null;
						}
						if(host==null){
							$.SetTips("城市不能为空");
							return null;
						}
						Date start = $.date(strStart+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
						Date finish = $.date(strFinish+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
						if(finish.before(start)){
							$.SetTips("开始时间不能比结束时间迟");
							return null;
						}
						//从收支记录中获取当前城市时间段内的收入与支出
						Double in = com.ccclubs.model.CsOperRecord.where().csorHost(host).add("definex", "csor_cash_no is null").csorDateTimeStart(start).csorDateTimeEnd(finish).eval("sum(csor_in)");
						Double out = com.ccclubs.model.CsOperRecord.where().csorHost(host).add("definex", "csor_cash_no is null").csorDateTimeStart(start).csorDateTimeEnd(finish).eval("sum(csor_out)");
						Double amount = $.or(in,0d) - $.or(out,0d);
						if(amount<=0){
							$.SetTips("当前时间段无可提现资金");
							return null;
						}
						csCashRecord = new CsCashRecord(
								csCashRecord.getCscrHost()//城市 [非空]
							 	,$.zerofill(host, 6)+$.date(start,"yyyyMMdd")+$.date(finish,"yyyyMMdd")//流水号 [非空]
							 	,amount//提取额 [非空]
							 	,$.date(start,"yyyy年MM月dd日")+"至"+$.date(start,"yyyy年MM月dd日")+SrvHost.getKeyValue(host)+"提现记录"//摘要 [非空]
							 	,null//付款信息
							 	,null//付款截图
							 	,LoginHelper.getLoginId()//添加人 [非空]
							 	,null//审核人
							 	,null//备注
							 	,new Date()//修改时间 [非空]
							 	,new Date()//添加时间 [非空]
							 	,(short)1//状态 [非空]
							 ).save();
						com.ccclubs.service.admin.ICsOperRecordService csOperRecordService = $.GetSpringBean("csOperRecordService");
						csOperRecordService.updateCsOperRecordByConfirm(
							$.add(CsOperRecord.F.csorCashNo, csCashRecord.getCscrId()),
							com.ccclubs.model.CsOperRecord.where().csorHost(host).add("confirm", "1").add("definex", "csor_cash_no is null").csorDateTimeStart(start).csorDateTimeEnd(finish)
						);
						$.SetTips("提款申请已提交，等待管理员审核");
						/************LAZY3Q_FORM_CODE_withdraw************/

						return null;
					}
				});
				
			}else{
				$.SetTips("对不起，请勿重复提交");
			}
			/************LAZY3Q_FORM_RETURN_withdraw************/
			/************LAZY3Q_FORM_RETURN_withdraw************/

			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		
		//回到列表页
		return $.Redirect($.empty($.getString("entrypoint"))?$.xeh($.getString("editorpoint")):$.xeh($.getString("entrypoint")));
	}
	/**
	 * 定义表单审核提交
	 * @return
	 */
	public String verify()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsCashRecordToken = $.getString("csCashRecordToken");
			final String sessionCsCashRecordToken = $.getSession("csCashRecordToken");
			if($.equals(requestCsCashRecordToken,sessionCsCashRecordToken)){
				$.removeSession("csCashRecordToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csCashRecordService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						/************LAZY3Q_FORM_CODE_verify************/
						int cscrStatus = $.equals($.getString("submiter"), "审核通过")?2:0;
						new CsCashRecord(csCashRecord.getCscrId())
							.cscrStatus((short)cscrStatus)
							.cscrRemark(csCashRecord.getCscrRemark())
							.update();
						
						//审核未通过：释放对应的收支记录
						if(cscrStatus==0){
							com.ccclubs.service.admin.ICsOperRecordService csOperRecordService = $.GetSpringBean("csOperRecordService");
							csOperRecordService.updateCsOperRecordByConfirm(
								$.add("definex", "csor_cash_no=null")
								,com.ccclubs.model.CsOperRecord.where().csorCashNo(csCashRecord.getCscrId()).add("confirm", 1)
							);
						}
						$.SetTips("操作成功！");
						/************LAZY3Q_FORM_CODE_verify************/

						return null;
					}
				});
				
			}else{
				$.SetTips("对不起，请勿重复提交");
			}
			/************LAZY3Q_FORM_RETURN_verify************/
			/************LAZY3Q_FORM_RETURN_verify************/

			
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
	public String pay()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsCashRecordToken = $.getString("csCashRecordToken");
			final String sessionCsCashRecordToken = $.getSession("csCashRecordToken");
			if($.equals(requestCsCashRecordToken,sessionCsCashRecordToken)){
				$.removeSession("csCashRecordToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csCashRecordService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						/************LAZY3Q_FORM_CODE_pay************/
						new CsCashRecord(csCashRecord.getCscrId())
							.cscrStatus((short)3)
							.cscrVoucher(csCashRecord.getCscrVoucher())
							.update();
						$.SetTips("操作成功！");
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
	/************LAZY3Q_DEFINE_CODE************/
	/**
	 * 根据城市，开始时间，结束时间获取可提取的资金
	 */
	public String info(){
		try{
			String strStart = $.getString("start");
			String strFinish = $.getString("finish");
			Long host = $.getLong("host");
			if($.empty(strStart))
				return $.SendAjax($.add("success", false).add("message", "开始日期不能为空"), $.UTF8);
			if($.empty(strFinish))
				return $.SendAjax($.add("success", false).add("message", "结束日期不能为空"), $.UTF8);
			if(host==null)
				return $.SendAjax($.add("success", false).add("message", "城市不能为空"), $.UTF8);
			Date start = $.date(strStart+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
			Date finish = $.date(strFinish+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
			if(finish.before(start))
				return $.SendAjax($.add("success", false).add("message", "开始时间不能比结束时间迟"), $.UTF8);
			//从收支记录中获取当前城市时间段内的收入与支出
			Double in = com.ccclubs.model.CsOperRecord.where().csorHost(host).add("definex", "csor_cash_no is null").csorDateTimeStart(start).csorDateTimeEnd(finish).eval("sum(csor_in)");
			Double out = com.ccclubs.model.CsOperRecord.where().csorHost(host).add("definex", "csor_cash_no is null").csorDateTimeStart(start).csorDateTimeEnd(finish).eval("sum(csor_out)");
			Double amount = $.or(in,0d) - $.or(out,0d);
			if(amount<=0)
				return $.SendAjax($.add("success", false).add("message", "当前时间段无可提现资金"), $.UTF8);
			return $.SendAjax($.add("success", true).add("value",$.digit(amount, 2)), $.UTF8);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax($.add("success", false).add("message", "系统繁忙，请稍候再试"), $.UTF8);
		}
	}
	/**
	 * 下载帐单
	 * @return
	 */
	public String download(){
		
		
		return null;
	}
	/************LAZY3Q_DEFINE_CODE************/


	public ICsCashRecordService getCsCashRecordService()
	{
		return csCashRecordService;
	}

	public void setCsCashRecordService(ICsCashRecordService csCashRecordService)
	{
		this.csCashRecordService = csCashRecordService;
	}

	public CsCashRecord getCsCashRecord()
	{
		return csCashRecord;
	}

	public void setCsCashRecord(CsCashRecord csCashRecord)
	{
		this.csCashRecord = csCashRecord;
	}
}