package com.ccclubs.action.admin.fee;

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
import com.ccclubs.model.CsPrice;
import com.ccclubs.service.admin.ICsPriceService;
/**************LAZY3Q_CODE_IMPORT**************/
import java.util.ArrayList;
import com.lazy3q.sql.Lazy3qDaoSupport;
import com.ccclubs.model.CsCarModel;
import com.ccclubs.model.CsFeeTypeSet;
import com.ccclubs.model.CsOutlets;
import com.ccclubs.model.CsUserType;
import com.ccclubs.model.SrvHost;
import com.ccclubs.config.SYSTEM;
/**************LAZY3Q_CODE_IMPORT**************/


/**
 * 价格的Action
 * @author 飞啊飘啊
 * 感谢struts的Action框架
 */
@SuppressWarnings("unchecked")
public class PriceAction
{
	ICsPriceService csPriceService;
	
	CsPrice csPrice;
	
	/**
	 * 价格管理首页
	 * @return
	 */	
	public String execute()
	{	
		try{
			java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\.([\\S\\s]+)\\.").matcher($.getServletRequest().getServletPath());
			String alias=(String) (matcher.find()?$.setRequest("alias",matcher.group(1)):null);//别名入口
						
			$.setRequest("modelClass",CsPrice.class);//把当前Action对应的模型类放入到request中,以作为后面导出、、、、等功能的反射使用
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//列表地址参数状态串，查询条件传递到转跳,就是把当前列表页的路径和参数记下来，放入request,在后面添加、编辑时把这个参数entrypoint带上，提交后再返回到当前列表
			//处理页面的视图数据，比如显示哪些列，自定义显示列等
			ActionHelper.doPageViewFields(CsPrice.class);
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> objects = ActionHelper.getQueryFormParams(CsPrice.class);
			$.setRequest("PARAMS",ActionHelper.getQueryFormParams(CsPrice.class));
									
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=CsPrice.C.get($.forMat($.getString("asc")));//升序字段
			objects.put("asc", strAsc);//放入查询条件
			String strDesc=CsPrice.C.get($.forMat($.getString("desc")));//降序字段
			objects.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				objects.put("desc","csp_id");//那么，默认以主键降序，传给Dao层
			
			//把request传过来的查询参数放回request,为了页面能够正确显示上次所选择的值
			Set<String> keys = objects.keySet();
			for(String key:keys)
				$.setRequest(key, objects.get(key));
				
			//把组装好的objects整个Map直接放入request,####原因暂时保密，等你哪天理解出这儿的意思了，你就是高手了
			$.setRequest("objects",objects);
			
			//反强制限制域
			
			//canQuery：表示当前列表是否具有查询功能，这里只是负责传个参数，具体能查不能查，在页面根据这个参数做处理
			$.setRequest("canQuery", $.getBoolean("canQuery",true));			
			/************LAZY3Q_CODE_EXECUTE************/
			Long csoHost = $.getLong("csoHost",SYSTEM.getDefaultHost());
			$.setRequest("csoHost", csoHost);
			objects.put("CsOutlets", $.add(CsOutlets.F.csoHost, csoHost));
			//objects.put("CsCarModel", $.add(CsCarModel.F.cscmHosts, "%#"+csoHost+"#%"));
			String[] queryKeys = "cspGoods,cspOutets,cspModel,cspUserType".split(",");
			int paramCount = 0;
			for(String key:queryKeys)
				paramCount+=$.empty($.getString(key))?0:1;
			if(paramCount==1||paramCount==2){
				//调用Service获取分页
				Page page = csPriceService.getCsPricePage(0,65535, objects);
				//把分页对象放入request
				$.setRequest("page", page);	
				$.setRequest("size", 65535);
			}else{
			/************LAZY3Q_CODE_EXECUTE************/

						
			//调用Service获取分页
			Page page = csPriceService.getCsPricePage($.getInteger("page", 0), $.getInteger("size",16), objects);
			//把分页对象放入request
			$.setRequest("page", page);	
			
			//重置请求参数，主要是把里面的百分号去掉一下
			ActionHelper.resetQueryFormParams(objects);
			/************LAZY3Q_AFTER_EXECUTE************/
			}
			Page page = $.getRequest("page");
			objects.put("CsOutlets",null);
			objects.put("CsCarModel",null);
			Long cspUserType = $.getLong("cspUserType");
			Long cspModel = $.getLong("cspModel");
			Long defaultOutlets = null;
			//获取默认网点的价格信息
			if(cspUserType!=null && cspModel!=null){
				CsFeeTypeSet csFeeTypeSet = CsFeeTypeSet.where().csftsModel(cspModel).csftsHost(csoHost).csftsFeeType("%"+cspUserType+"%").get();
				$.setRequest("csFeeTypeSet", csFeeTypeSet);
				if(csFeeTypeSet==null){
					$.SetTips(SrvHost.get(csoHost).getShName()+","+CsCarModel.get(cspModel).getCscmName()+","+"未配置计费策略<br/>或者其适用的计费方式未包含:"+CsUserType.get(cspUserType).getCsutName());
				}else{
					defaultOutlets = csFeeTypeSet.getCsftsOutlets();
					$.setRequest("defaultOutlets", defaultOutlets);					
					$.setRequest("defaults",CsPrice.where().cspUserType(cspUserType).cspModel(cspModel).cspOutets(defaultOutlets).list(65535));
				}
			}
			
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
	 * 价格添加/编辑页面
	 * @return
	 */
	public String edit()
	{		
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//编辑页状态串(包含进入编辑页面时的地址和参数)
			$.setRequest("entrypoint", $.getString("entrypoint"));//列表地址参数状态串，由列表页生产，这里只是携带参数，跟快递差不多
			$.setRequest("ids",$.getString("ids"));//批量编辑的数据ID，多个ID以,号分隔
			$.setSession("csPriceToken", $.uuid());//防重复提交Token
			//当前要编辑的数据ID，有这个ID表示修改数据，没这个ID表示添加数据
			Long id=$.getLong("id");
			if(id!=null){
				//把当前要修改的数据查出来,注意了，csPrice这个对象为什么不用$.setRequest,因为csPrice有get,set方法，由struts处理的
				csPrice=csPriceService.getCsPriceById(id);
			}
			if(csPrice==null)
				csPrice = new CsPrice();
			//根据自定义配置ctrl中配置的默认值信息设置默认值
			if(CTRL!=null)
				CTRL.setObjectDefaultValue(csPrice);
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
	public void on(String type,CsPrice object,CsPrice old){
		try{
			//执行后台配置的动态代码脚本，如果有在后台配置的话(*_*)
			ActionHelper.executeActionScript(CsPrice.class, type, object, old);
			/************LAZY3Q_ACTION_ON************/
			/************LAZY3Q_ACTION_ON************/

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	/**
	 * 保存价格
	 * @return
	 */
	public String save()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//主键为空，表示插入,否则表示更新
			final Boolean bIsAdd = ($.empty($.getString("ids")) && $.empty($.getString("PARAMS")) && csPrice.getCspId()==null);
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsPriceToken = $.getString("csPriceToken");
			final String sessionCsPriceToken = $.getSession("csPriceToken");
			if($.equals(requestCsPriceToken,sessionCsPriceToken)){
				$.removeSession("csPriceToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csPriceService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						/************LAZY3Q_BEFOR_SAVE************/
						/************LAZY3Q_BEFOR_SAVE************/

						if(bIsAdd){
							//批量插入处理
							String vValue = ""+(csPrice.getCspId()==null?"":csPrice.getCspId());
							String sArray[]={""+vValue};
							if(vValue.startsWith("[*]")){
								vValue=vValue.replace("[*]","");
								sArray=vValue.split("[\\s|\u3000]+");				
							}
							for(String sItem:sArray){
								if(!$.empty(sItem))
								csPrice.setCspId(Long.valueOf(sItem));
							
								//设置默认值
								if(csPrice.getCspGoods()==null)
								csPrice.setCspGoods((long)0);
								if(csPrice.getCspOutets()==null)
								csPrice.setCspOutets((long)0);
								if(csPrice.getCspModel()==null)
								csPrice.setCspModel((long)0);
								if(csPrice.getCspPrice()==null)
								csPrice.setCspPrice((double)0);
								
								//根据自定义的默认值信息设置默认值
								if(CTRL!=null)
									CTRL.setObjectDefaultValue(csPrice);
								
								csPrice=csPriceService.saveCsPrice(csPrice);			
								
								on("save",csPrice,csPrice);
							}							
							
							csPriceService.updateCsPrice$NotNull(csPrice);						
							/************LAZY3Q_CODE_ADD************/
							/************LAZY3Q_CODE_ADD************/

							
							//记录操作日志
							LoggerHelper.writeLog(CsPrice.class,"add","添加了[价格]["+csPrice.getCspId()+"]",(Long)$.getSession("ccclubs_login_id"), csPrice,csPrice.getCspId());
							$.SetTips("保存价格成功");
						}else{
							String PARAMS = $.getString("PARAMS");
							String ids=$.getString("ids");
							if(!$.empty(PARAMS)){//根据查询条件批量更新
								Map params = $.eval(PARAMS);
								params.put("confirm", 1);
								params.put("definex","1=1");
								csPriceService.updateCsPriceByConfirm(ActionHelper.getSetValuesFromModel(csPrice), params);
								LoggerHelper.writeLog(CsPrice.class, "update", 
											"批量修改了[价格]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(null,csPrice,false)
											,null);
							}else if(!$.empty(ids)){//根据ids批量更新
								String[] array = ids.split(",");
								for(String sid:array){
									csPrice.setCspId(Long.valueOf(sid));
									CsPrice oldCsPrice = CsPrice.get(Long.valueOf(sid));
									csPriceService.updateCsPrice$NotNull(csPrice);
									LoggerHelper.writeLog(CsPrice.class, "update", 
											"修改了[价格]["+oldCsPrice.getCspId()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsPrice, csPrice,false)
											,Long.valueOf(sid));
									on($.getString("method"),csPrice,oldCsPrice);
								}
							}else{
								if($.empty($.getString("edittype"))){
									//设置填充值
									
									CsPrice oldCsPrice = CsPrice.get(csPrice.getCspId());
									csPriceService.updateCsPrice$NotNull(csPrice);
									LoggerHelper.writeLog(CsPrice.class, "update", 
											"修改了[价格]["+oldCsPrice.getCspId()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsPrice, csPrice,false)
											,csPrice.getCspId());
									on($.getString("method"),csPrice,oldCsPrice);									
								}else{
									CsPrice oldCsPrice = csPriceService.getCsPriceById(csPrice.getCspId());
									//设置默认值
									csPrice.setCspGoods(oldCsPrice.getCspGoods());	
									csPrice.setCspOutets(oldCsPrice.getCspOutets());	
									csPrice.setCspModel(oldCsPrice.getCspModel());	
									
									//设置填充值
									
									//从oldCsPrice同步csPrice未set过的值
									csPrice.mergeSet(oldCsPrice);
											
									csPriceService.updateCsPrice(csPrice);
									LoggerHelper.writeLog(CsPrice.class, "update", 
											"修改了[价格]["+oldCsPrice.getCspId()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsPrice, csPrice,true)
											,csPrice.getCspId());
									on("update",csPrice,oldCsPrice);
								}
							}
							/************LAZY3Q_CODE_UPDATE************/
							/************LAZY3Q_CODE_UPDATE************/

							//记录操作日志
							$.SetTips("修改价格成功");
						}	
						/************LAZY3Q_CODE_SAVE************/
						/************LAZY3Q_CODE_SAVE************/

						
						return null;
					}
				});
				
			}else{
				$.removeSession("csPriceToken");//移除令牌	
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
				return $.SendHtml("<script>"+complete+"("+$.json(csPrice)+")"+"</script>","UTF-8");
			}
			
			//如果点保存，则回到编辑页，如果点确定，则回到列表页
			//现在知道editorpoint和entrypoint的意义了吗
			//它们由地址加参数(如:http://xxx.xx/xx.xx?xx=xx&xxx=xxx)转成16进制字串进行传递的
			if($.getString("submiter","").indexOf("保存")>-1){//则回到编辑页
				if(bIsAdd)
					return $.Redirect("price_edit.do?entrypoint="+$.getString("entrypoint")+"&id="+csPrice.getCspId());
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
	 * 删除价格
	 * @return
	 */
	public String del()
	{
		try{
			//获取当前用户的权限，看当前用户有没有删除权限
			SystemHelper.getSrvLimit("admin/fee/price.do");
					/************LAZY3Q_CAN_DEL************/
					/************LAZY3Q_CAN_DEL************/

			if($.getRequest("canDel")==null || ((Boolean)$.getRequest("canDel"))==false){
				throw new RuntimeException("您没有权限删除该数据");
			}
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//多个删除数据动作合并事务过程
			csPriceService.executeTransaction(new Function(){
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
							CsPrice csPrice = csPriceService.getCsPriceById(id);
							csPriceService.deleteCsPriceById(id);			
							//记录操作日志
							if(csPrice!=null)
								LoggerHelper.writeLog(CsPrice.class,"del","删除了[价格]["+csPrice.getCspId()+"]",(Long)$.getSession("ccclubs_login_id"), csPrice);
							on("delete",csPrice,csPrice);
					}
					/************LAZY3Q_CODE_DEL************/
					/************LAZY3Q_CODE_DEL************/

					
					return null;
				}
			});
			
			$.SetTips("删除价格成功");
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
	 * 显示价格详情
	 * @return
	 */
	public String details()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			Long id=$.getLong("id");
			Long key=$.getLong("key");
			if(id!=null)
				csPrice=csPriceService.getCsPriceById(id);
			if(key!=null && id==null)
				csPrice=CsPrice.Get($.add(CsPrice.F.cspId,key));
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
	 * 根据编号或ID查询价格
	 * 返回ajax数据
	 * @return
	 */
	public String query(){
		try{
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> params = ActionHelper.getQueryFormParams(CsPrice.class);
			
			//反强制限制域
			
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=$.getString("asc");//升序字段
			params.put("asc", strAsc);//放入查询条件
			String strDesc=$.getString("desc");//降序字段
			params.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				params.put("desc","csp_id");//那么，默认以主键降序，传给Dao层
								
			String strTerm=$.getString("value");//智能搜索时的参数，一般都是主键后面那个字段
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("cspId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or csp_id like '"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}
			/************LAZY3Q_CODE_QUERY************/
			/************LAZY3Q_CODE_QUERY************/

			
			//是否需要整个数据对象
			Boolean bObject=$.getBoolean("object",false);
			
			List<CsPrice> list = csPriceService.getCsPricePage(0,$.getInteger("size",10),params).getResult();
			
			/**
			* OK!取到数据拼成放入Map中，[{value,text,object:{...}},...]
			* value:数据ID，text:数据标识,object,整个对象
			**/
			List<Map> result = new java.util.ArrayList();
			for(CsPrice csPrice:list){
				Map map = new HashMap();
				map.put("value",csPrice.getCspId().toString());
				map.put("text", $.js(csPrice.getKeyValue()));
				if(bObject==true)
					map.put("object",csPrice);
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
	public String copy(){		
		try{
			Map<String,Object> params=new HashMap();
 			params.put("cspGoods", $.getLong("cspGoods"));
 			params.put("cspOutets", $.getLong("cspOutets"));
 			params.put("cspModel", $.getLong("cspModel"));
 			params.put("cspUserType", $.getLong("cspUserType"));
 			List<CsPrice> list = csPriceService.getCsPriceList(params, $.getInteger("size",65535));
			return $.SendAjax(list, "UTF-8");
		}catch(Exception ex){
			ex.printStackTrace();
			Logger.getRootLogger().error(ex.getMessage(),ex);
			return $.SendAjax("[]", "UTF-8");
		}
	}
	/**
	 * 保存产品
	 * @return
	 */
	public String saveall()
	{
		try{
			if(threadcount!=0)
				return $.SendAjax($.add("success", false).add("message", "之前未完成的保存正在执行，请稍候再试!"),"UTF-8");
			
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			Lazy3qDaoSupport dao = $.getDao("ccclubs_system");	
			String strPrices = $.getString("prices");
			String[] array = $.empty(strPrices) ? new String[]{} : strPrices.split(";");
			final List<List> lists = new ArrayList();
			List<String> sqls = new ArrayList();
			String insertTemplate = "insert into cs_price(csp_goods,csp_outets,csp_model,csp_user_type,csp_price) select {0},{1},{2},{3},{4} from dual where not exists(select * from cs_price where csp_goods={0} and csp_outets={1} and csp_model={2} and csp_user_type={3});";
			String updateTemplate = "update cs_price set csp_price={4} where csp_goods={0} and csp_outets={1} and csp_model={2} and csp_user_type={3};";
			for(String strPrice:array){
				String[] items = strPrice.split(":");
				String[] ids = items[0].split(",");
				sqls.add(insertTemplate.replace("{0}", ids[0]).replace("{1}", ids[1]).replace("{2}", ids[2]).replace("{3}", ids[3]).replace("{4}", items[1]));
				sqls.add(updateTemplate.replace("{0}", ids[0]).replace("{1}", ids[1]).replace("{2}", ids[2]).replace("{3}", ids[3]).replace("{4}", items[1]));
				if(sqls.size()>49){
					lists.add(sqls);
					sqls = new ArrayList();
				}
			}	
			if(sqls.size()>0){
				lists.add(sqls);
				sqls = new ArrayList();
			}
			
			threadcount=8;
			for(int i=0;i<8;i++){
				new Thread(){
					public void run(){
						while(true){
							List list = null;
							synchronized(lists){
								if(lists.size()>0)
									list = lists.remove(0);
							}
							if(list==null)break;							
							$.getDao("ccclubs_system").executeBatch(list);
							System.out.println("线程["+Thread.currentThread().getId()+"]保存"+list.size()/2+"条价格数据");
						}
						
						$.getDao("ccclubs_system").executeUpdate("delete from cs_price where csp_price=0");
						
						threadcount--;
					}
				}.start();				
			}
			
			while(threadcount!=0)
				Thread.sleep(500);
						
			com.ccclubs.dao.impl.MemCache.clearObject(CsPrice.M.class);//清除查询缓存
			com.ccclubs.dao.impl.MemCache.clearValue(CsPrice.M.class);//清除条件缓存
			com.ccclubs.dao.impl.MemCache.clearObject(CsPrice.class);//清除所有对象缓存
						
			return $.SendAjax($.add("success", true).add("message","价格配置保存成功"),"UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax($.add("success", false).add("message","系统繁忙,请稍候再试"),"UTF-8");
		}
	}	
	static int threadcount = 0;
	/***************LAZY3Q_DEFINE_CODE******************/


	public ICsPriceService getCsPriceService()
	{
		return csPriceService;
	}

	public void setCsPriceService(ICsPriceService csPriceService)
	{
		this.csPriceService = csPriceService;
	}

	public CsPrice getCsPrice()
	{
		return csPrice;
	}

	public void setCsPrice(CsPrice csPrice)
	{
		this.csPrice = csPrice;
	}
}