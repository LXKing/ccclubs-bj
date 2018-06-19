package com.ccclubs.action.admin.object;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ccclubs.helper.ActionHelper;
import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsEvCard;
import com.ccclubs.service.admin.ICsEvCardService;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.ConfigHelper;
import com.lazy3q.web.node.Export;
import com.lazy3q.web.util.Ctrl;
import com.lazy3q.web.util.Page;


/**
 * 会员卡的Action
 * @author 飞啊飘啊
 * 感谢struts的Action框架
 */
@SuppressWarnings("unchecked")
public class EvcardAction
{
	ICsEvCardService csEvCardService;
	
	CsEvCard csEvCard;
	
	/**
	 * 会员卡管理首页
	 * @return
	 */	
	public String execute()
	{	
		try{
			java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\.([\\S\\s]+)\\.").matcher($.getServletRequest().getServletPath());
			String alias=(String) (matcher.find()?$.setRequest("alias",matcher.group(1)):null);//别名入口
						
			$.setRequest("modelClass",CsEvCard.class);//把当前Action对应的模型类放入到request中,以作为后面导出、、、、等功能的反射使用
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//列表地址参数状态串，查询条件传递到转跳,就是把当前列表页的路径和参数记下来，放入request,在后面添加、编辑时把这个参数entrypoint带上，提交后再返回到当前列表
			//处理页面的视图数据，比如显示哪些列，自定义显示列等
			ActionHelper.doPageViewFields(CsEvCard.class);
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> objects = ActionHelper.getQueryFormParams(CsEvCard.class);
			$.setRequest("PARAMS",ActionHelper.getQueryFormParams(CsEvCard.class));
									
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=CsEvCard.C.get($.forMat($.getString("asc")));//升序字段
			objects.put("asc", strAsc);//放入查询条件
			String strDesc=CsEvCard.C.get($.forMat($.getString("desc")));//降序字段
			objects.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				objects.put("desc","csec_id");//那么，默认以主键降序，传给Dao层
			
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
				SystemHelper.getSrvLimit("admin/object/evcard.do");
				if($.getRequest("canExport")==null || ((Boolean)$.getRequest("canExport"))==false){
					throw new RuntimeException("您没有权限导出该数据");
				}
				final Map<String, Object> finalParams = objects;
				//调用lazy3q.jar中的导出功能
				//参数说明：(这次导出多少条数据，到数据库查的时候一次查多少条，当前所在第几页,处理器，对应的模型类,要导出哪些字段)
				$.export($.getInteger("size",1000), 100, $.getInteger("page",0), new Export(){//new Export这个类，他会根据参数分次查询，边查边写，目的只有一个，就是为了避免把大量的数据放在内存中
					@Override //重载实现Export的getPage方法，Export会分多次调用这个方法
					public Page getPage(Integer page, int size) {//这时的page和size是Export转化过后的当前页，每页查询数					
						return csEvCardService.getCsEvCardPage(page, size, finalParams);
					}
				}, CsEvCard.class, $.getArray("keys"));
				return null;//数据已经被Export直接写入给输出流了，直接返回null,下面的逻辑就不执行了，
			}
			/************LAZY3Q_CODE_EXECUTE************/
			/************LAZY3Q_CODE_EXECUTE************/

						
			//调用Service获取分页
			Page page = csEvCardService.getCsEvCardPage($.getInteger("page", 0), $.getInteger("size",16), objects);
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
	 * 会员卡添加/编辑页面
	 * @return
	 */
	public String edit()
	{		
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//编辑页状态串(包含进入编辑页面时的地址和参数)
			$.setRequest("entrypoint", $.getString("entrypoint"));//列表地址参数状态串，由列表页生产，这里只是携带参数，跟快递差不多
			$.setRequest("ids",$.getString("ids"));//批量编辑的数据ID，多个ID以,号分隔
			$.setSession("csEvCardToken", $.uuid());//防重复提交Token
			//当前要编辑的数据ID，有这个ID表示修改数据，没这个ID表示添加数据
			Long id=$.getLong("id");
			if(id!=null){
				//把当前要修改的数据查出来,注意了，csEvCard这个对象为什么不用$.setRequest,因为csEvCard有get,set方法，由struts处理的
				csEvCard=csEvCardService.getCsEvCardById(id);
				//确认域标识
				if(SystemHelper.testHost(csEvCard.getCsecHost()).equals("0")){
					return $.SendHtml("<script>alert('对不起，你没有权限编辑该数据');</script>", "UTF-8");
				}
			}
			if(csEvCard==null)
				csEvCard = new CsEvCard();
			//根据自定义配置ctrl中配置的默认值信息设置默认值
			if(CTRL!=null)
				CTRL.setObjectDefaultValue(csEvCard);
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
	public void on(String type,CsEvCard object,CsEvCard old){
		try{
			//执行后台配置的动态代码脚本，如果有在后台配置的话(*_*)
			ActionHelper.executeActionScript(CsEvCard.class, type, object, old);
			/************LAZY3Q_ACTION_ON************/
			/************LAZY3Q_ACTION_ON************/

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	/**
	 * 保存会员卡
	 * 批量添加
	 * 卡号设置[*]
	 * 机器号[*]
	 * 备注[*] 卡号 机器号\n 卡号 机器号
	 * @return
	 */
	public String save()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//主键为空，表示插入,否则表示更新
			final Boolean bIsAdd = ($.empty($.getString("ids")) && $.empty($.getString("PARAMS")) && csEvCard.getCsecId()==null);
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsEvCardToken = $.getString("csEvCardToken");
			final String sessionCsEvCardToken = $.getSession("csEvCardToken");
			if($.equals(requestCsEvCardToken,sessionCsEvCardToken)){
				$.removeSession("csEvCardToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csEvCardService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						/************LAZY3Q_BEFOR_SAVE************/
						/************LAZY3Q_BEFOR_SAVE************/

						if(bIsAdd){
							//批量插入处理
							String vValue = ""+(csEvCard.getCsecNumber()==null?"":csEvCard.getCsecNumber());
							String rValue = ""+(csEvCard.getCsecRfid()==null ? "" :csEvCard.getCsecRfid());
							String remark = csEvCard.getCsecRemark();
							String remarkArray[] = {""+remark}; 
							if(remark.startsWith("[*]")){
								remark = remark.replace("[*]","");
								remarkArray = remark.split("\\n");
								csEvCard.setCsecRemark("");
							}
							
							boolean bl = vValue.startsWith("[*]") && rValue.startsWith("[*]") ;
							
							for (int i = 0; i < remarkArray.length; i++) {
								if(bl){
									String sItem = remarkArray[i];
									String array[] = sItem.split("[\\s|\u3000]+");
									if(!$.empty(array[0]))
										csEvCard.setCsecNumber(String.valueOf(array[0]));
									CsEvCard c = csEvCardService.getCsEvCard($.add(CsEvCard.F.csecNumber, String.valueOf(array[0])));
									if(c != null)
										continue;
									
									if(!$.empty(array[1]))
										csEvCard.setCsecRfid(String.valueOf(array[1]));
									
									c = csEvCardService.getCsEvCard($.add(CsEvCard.F.csecRfid, String.valueOf(array[1])));
									if(c != null)
										continue;
								}	
								//设置默认值
								if(csEvCard.getCsecAddTime()==null)
								csEvCard.setCsecAddTime(new Date());
								if(csEvCard.getCsecFlag()==null)
									csEvCard.setCsecFlag((short)1);
								
								//根据自定义的默认值信息设置默认值
								if(CTRL!=null)
									CTRL.setObjectDefaultValue(csEvCard);
								//添加域标识
								if(SystemHelper.testHost(csEvCard.getCsecHost()).equals("0")){
									throw new RuntimeException("非法指定域值");
								}
								
								csEvCard=csEvCardService.saveCsEvCard(csEvCard);			
								
								on("save",csEvCard,csEvCard);
								LoggerHelper.writeLog(CsEvCard.class,"add","添加了[会员卡]["+csEvCard.getCsecNumber()+"]",(Long)$.getSession("ccclubs_login_id"), csEvCard,csEvCard.getCsecId());
							}							
							
							csEvCardService.updateCsEvCard$NotNull(csEvCard);						
							/************LAZY3Q_CODE_ADD************/
							/************LAZY3Q_CODE_ADD************/

							
							//记录操作日志
							$.SetTips("保存会员卡成功");
						}else{
							String PARAMS = $.getString("PARAMS");
							String ids=$.getString("ids");
							if(!$.empty(PARAMS)){//根据查询条件批量更新
								Map params = $.eval(PARAMS);
								params.put("confirm", 1);
								params.put("definex","1=1");
								csEvCardService.updateCsEvCardByConfirm(ActionHelper.getSetValuesFromModel(csEvCard), params);
								LoggerHelper.writeLog(CsEvCard.class, "update", 
											"批量修改了[会员卡]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(null,csEvCard,false)
											,null);
							}else if(!$.empty(ids)){//根据ids批量更新
								String[] array = ids.split(",");
								for(String sid:array){
									csEvCard.setCsecId(Long.valueOf(sid));
									CsEvCard oldCsEvCard = CsEvCard.get(Long.valueOf(sid));
									csEvCardService.updateCsEvCard$NotNull(csEvCard);
									LoggerHelper.writeLog(CsEvCard.class, "update", 
											"修改了[会员卡]["+oldCsEvCard.getCsecNumber()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsEvCard, csEvCard,false)
											,Long.valueOf(sid));
									on($.getString("method"),csEvCard,oldCsEvCard);
								}
							}else{
								if($.empty($.getString("edittype"))){
									//设置填充值
									
									CsEvCard oldCsEvCard = CsEvCard.get(csEvCard.getCsecId());
									csEvCardService.updateCsEvCard$NotNull(csEvCard);
									LoggerHelper.writeLog(CsEvCard.class, "update", 
											"修改了[会员卡]["+oldCsEvCard.getCsecNumber()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsEvCard, csEvCard,false)
											,csEvCard.getCsecId());
									on($.getString("method"),csEvCard,oldCsEvCard);									
								}else{
									CsEvCard oldCsEvCard = csEvCardService.getCsEvCardById(csEvCard.getCsecId());
									//设置默认值
									csEvCard.setCsecAddTime(oldCsEvCard.getCsecAddTime());	
									csEvCard.setCsecFlag(oldCsEvCard.getCsecFlag());	
									
									//设置填充值
									//添加域标识
									if(SystemHelper.testHost(csEvCard.getCsecHost()).equals("0")){
										throw new RuntimeException("非法指定域值");
									}
									
									//从oldCsEvCard同步csEvCard未set过的值
									csEvCard.mergeSet(oldCsEvCard);
											
									csEvCardService.updateCsEvCard(csEvCard);
									LoggerHelper.writeLog(CsEvCard.class, "update", 
											"修改了[会员卡]["+oldCsEvCard.getCsecNumber()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsEvCard, csEvCard,true)
											,csEvCard.getCsecId());
									on("update",csEvCard,oldCsEvCard);
								}
							}
							/************LAZY3Q_CODE_UPDATE************/
							/************LAZY3Q_CODE_UPDATE************/

							//记录操作日志
							$.SetTips("修改会员卡成功");
						}	
						/************LAZY3Q_CODE_SAVE************/
						/************LAZY3Q_CODE_SAVE************/

						
						return null;
					}
				});
				
			}else{
				$.removeSession("csEvCardToken");//移除令牌	
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
				return $.SendHtml("<script>"+complete+"("+$.json(csEvCard)+")"+"</script>","UTF-8");
			}
			
			//如果点保存，则回到编辑页，如果点确定，则回到列表页
			//现在知道editorpoint和entrypoint的意义了吗
			//它们由地址加参数(如:http://xxx.xx/xx.xx?xx=xx&xxx=xxx)转成16进制字串进行传递的
			if($.getString("submiter","").indexOf("保存")>-1){//则回到编辑页
				if(bIsAdd)
					return $.Redirect("evcard_edit.do?entrypoint="+$.getString("entrypoint")+"&id="+csEvCard.getCsecId());
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
	 * 删除会员卡
	 * @return
	 */
	public String del()
	{
		try{
			//获取当前用户的权限，看当前用户有没有删除权限
			SystemHelper.getSrvLimit("admin/object/evcard.do");
					/************LAZY3Q_CAN_DEL************/
					/************LAZY3Q_CAN_DEL************/

			if($.getRequest("canDel")==null || ((Boolean)$.getRequest("canDel"))==false){
				throw new RuntimeException("您没有权限删除该数据");
			}
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//多个删除数据动作合并事务过程
			csEvCardService.executeTransaction(new Function(){
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
							CsEvCard csEvCard = csEvCardService.getCsEvCardById(id);
							//确认域标识
							if(SystemHelper.testHost(csEvCard.getCsecHost()).equals("0")){
								throw new RuntimeException("当前用户没有权限删除该数据");
							}
							if(ConfigHelper.GetBoolean("logic_delete")==true)
								csEvCardService.removeCsEvCardById(id);
							else
							csEvCardService.deleteCsEvCardById(id);			
							//记录操作日志
							if(csEvCard!=null)
								LoggerHelper.writeLog(CsEvCard.class,"del","删除了[会员卡]["+csEvCard.getCsecNumber()+"]",(Long)$.getSession("ccclubs_login_id"), csEvCard);
							on("delete",csEvCard,csEvCard);
					}
					/************LAZY3Q_CODE_DEL************/
					/************LAZY3Q_CODE_DEL************/

					
					return null;
				}
			});
			
			$.SetTips("删除会员卡成功");
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
	 * 显示会员卡详情
	 * @return
	 */
	public String details()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			Long id=$.getLong("id");
			Long key=$.getLong("key");
			if(id!=null)
				csEvCard=csEvCardService.getCsEvCardById(id);
			if(key!=null && id==null)
				csEvCard=CsEvCard.Get($.add(CsEvCard.F.csecId,key));
			//确认域标识
			if(SystemHelper.testHost(csEvCard.getCsecHost()).equals("0")){
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
	 * 根据卡号或ID查询会员卡
	 * 返回ajax数据
	 * @return
	 */
	public String query(){
		try{
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> params = ActionHelper.getQueryFormParams(CsEvCard.class);
			//强制限制域
			params.put("HOSTS",SystemHelper.testHost(null));
			
			//反强制限制域
			
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=$.getString("asc");//升序字段
			params.put("asc", strAsc);//放入查询条件
			String strDesc=$.getString("desc");//降序字段
			params.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				params.put("desc","csec_id");//那么，默认以主键降序，传给Dao层
								
			String strTerm=$.getString("value");//智能搜索时的参数，一般都是主键后面那个字段
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("csecId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or csec_number like '"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}
			/************LAZY3Q_CODE_QUERY************/
			params.remove("HOSTS");
			params.remove("csecHost");
			/************LAZY3Q_CODE_QUERY************/

			
			//是否需要整个数据对象
			Boolean bObject=$.getBoolean("object",false);
			
			List<CsEvCard> list = csEvCardService.getCsEvCardPage(0,$.getInteger("size",10),params).getResult();
			
			/**
			* OK!取到数据拼成放入Map中，[{value,text,object:{...}},...]
			* value:数据ID，text:数据标识,object,整个对象
			**/
			List<Map> result = new java.util.ArrayList();
			for(CsEvCard csEvCard:list){
				Map map = new HashMap();
				map.put("value",csEvCard.getCsecId().toString());
				map.put("text", $.js(csEvCard.getKeyValue()));
				if(bObject==true)
					map.put("object",csEvCard);
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
	/************LAZY3Q_DEFINE_CODE************/


	public ICsEvCardService getCsEvCardService()
	{
		return csEvCardService;
	}

	public void setCsEvCardService(ICsEvCardService csEvCardService)
	{
		this.csEvCardService = csEvCardService;
	}

	public CsEvCard getCsEvCard()
	{
		return csEvCard;
	}

	public void setCsEvCard(CsEvCard csEvCard)
	{
		this.csEvCard = csEvCard;
	}
}