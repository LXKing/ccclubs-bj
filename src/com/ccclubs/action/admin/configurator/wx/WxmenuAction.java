package com.ccclubs.action.admin.configurator.wx;

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
import com.ccclubs.model.CsWxMenu;
import com.ccclubs.service.admin.ICsWxMenuService;
/************LAZY3Q_CODE_IMPORT************/
/************LAZY3Q_CODE_IMPORT************/


/**
 * 微信菜单的Action
 * @author 飞啊飘啊
 * 感谢struts的Action框架
 */
@SuppressWarnings("unchecked")
public class WxmenuAction
{
	ICsWxMenuService csWxMenuService;
	
	CsWxMenu csWxMenu;
	
	/**
	 * 微信菜单管理首页
	 * @return
	 */	
	public String execute()
	{	
		try{
			java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("\\.([\\S\\s]+)\\.").matcher($.getServletRequest().getServletPath());
			String alias=(String) (matcher.find()?$.setRequest("alias",matcher.group(1)):null);//别名入口
						
			$.setRequest("modelClass",CsWxMenu.class);//把当前Action对应的模型类放入到request中,以作为后面导出、、、、等功能的反射使用
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//列表地址参数状态串，查询条件传递到转跳,就是把当前列表页的路径和参数记下来，放入request,在后面添加、编辑时把这个参数entrypoint带上，提交后再返回到当前列表
			//处理页面的视图数据，比如显示哪些列，自定义显示列等
			ActionHelper.doPageViewFields(CsWxMenu.class);
			Map<String,Object> objects=new HashMap();
			
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=$.getString("asc");//升序字段
			objects.put("asc", strAsc);//放入查询条件
			String strDesc=$.getString("desc");//降序字段
			objects.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))
				objects.put("desc","cswm_id");
			//把request传过来的查询参数放回request,为了页面能够正确显示上次所选择的值
			Set<String> keys = objects.keySet();
			for(String key:keys)
				$.setRequest(key, objects.get(key));	
			
			//反强制限制域
			/************LAZY3Q_CODE_TREE_EXECUTE************/
			/************LAZY3Q_CODE_TREE_EXECUTE************/

				
			//调用Service取列表
			List<CsWxMenu> list = csWxMenuService.getCsWxMenuList(objects,-1);
			//把取回的list排成树形[ORDER_TREE]或层形[ORDER_COAT]
			list=Tree.OrderList(list,"cswmId", "cswmParent", Tree.ORDER_TREE);
			//把列表放入request
			$.setRequest("list", list);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "index";
	}
	/**
	 * ajax获取微信菜单树形数据
	 * @return
	 */
	public String tree(){
		try{
			//获取要查询的数据的父节点，没有默认为0（顶层）
			Long parent = $.getLong("parent",0l);
			//根据父节点取数据列表
			List<CsWxMenu> list = csWxMenuService.getCsWxMenuList($.add("cswmParent",parent),-1);
			
			/**
			* OK!取到数据拼成json串，[{value,text,more},...]
			* value:数据ID，text:数据标识,more是否还有子节点
			**/
			String strAjax="";
			for(CsWxMenu o:list){
				if(!strAjax.equals(""))
					strAjax+=",";
				strAjax+="{";
				strAjax+="\"value\":\""+o.getCswmId()+"\"";
				strAjax+=",";
				strAjax+="\"text\":\""+$.js(o.getCswmName())+"\"";
				strAjax+=",";
				strAjax+="\"more\":true";
				strAjax+="}";
			}
			//完了，$.SendAjax直接写json串到输出流
			return $.SendAjax("["+strAjax+"]", "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}
	}
	
	
	
	/**
	 * 微信菜单添加/编辑页面
	 * @return
	 */
	public String edit()
	{		
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			$.setRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//编辑页状态串(包含进入编辑页面时的地址和参数)
			$.setRequest("entrypoint", $.getString("entrypoint"));//列表地址参数状态串，由列表页生产，这里只是携带参数，跟快递差不多
			$.setRequest("ids",$.getString("ids"));//批量编辑的数据ID，多个ID以,号分隔
			$.setSession("csWxMenuToken", $.uuid());//防重复提交Token
			{
			//父节点数据ID
			Long parent=$.getLong("parent");
			if(parent!=null)//如果父节点数据ID不为空，把父对象查出来
				$.setRequest("parentCsWxMenu", csWxMenuService.getCsWxMenuById(parent));
			}
			//当前要编辑的数据ID，有这个ID表示修改数据，没这个ID表示添加数据
			Long id=$.getLong("id");
			if(id!=null){
				//把当前要修改的数据查出来,注意了，csWxMenu这个对象为什么不用$.setRequest,因为csWxMenu有get,set方法，由struts处理的
				csWxMenu=csWxMenuService.getCsWxMenuById(id);
				//把当前修改的数据的父级数据也查出来，放到request中
				$.setRequest("parentCsWxMenu", csWxMenuService.getCsWxMenuById(csWxMenu.getCswmParent()));
			}
			if(csWxMenu==null)
				csWxMenu = new CsWxMenu();
			//根据自定义配置ctrl中配置的默认值信息设置默认值
			if(CTRL!=null)
				CTRL.setObjectDefaultValue(csWxMenu);
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
	public void on(String type,CsWxMenu object,CsWxMenu old){
		try{
			//执行后台配置的动态代码脚本，如果有在后台配置的话(*_*)
			ActionHelper.executeActionScript(CsWxMenu.class, type, object, old);
			/************LAZY3Q_ACTION_ON************/
			/************LAZY3Q_ACTION_ON************/

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	/**
	 * 保存微信菜单
	 * @return
	 */
	public String save()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//主键为空，表示插入,否则表示更新
			final Boolean bIsAdd = ($.empty($.getString("ids")) && $.empty($.getString("PARAMS")) && csWxMenu.getCswmId()==null);
			//Token技术建议百度下，就是为了防止重复提交
			final String requestCsWxMenuToken = $.getString("csWxMenuToken");
			final String sessionCsWxMenuToken = $.getSession("csWxMenuToken");
			if($.equals(requestCsWxMenuToken,sessionCsWxMenuToken)){
				$.removeSession("csWxMenuToken");//移除令牌				
				//多个写数据动作合并事务过程（当这个过程中发生任务错误时，由spring控制处理数据回滚，把所有修改的，添加的，删除的....统统恢复）
				csWxMenuService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						/************LAZY3Q_BEFOR_SAVE************/
						/************LAZY3Q_BEFOR_SAVE************/

						if(bIsAdd){
							//批量插入处理
							String vValue = ""+(csWxMenu.getCswmName()==null?"":csWxMenu.getCswmName());
							String sArray[]={""+vValue};
							if(vValue.startsWith("[*]")){
								vValue=vValue.replace("[*]","");
								sArray=vValue.split("[\\s|\u3000]+");				
							}
							for(String sItem:sArray){
								if(!$.empty(sItem))
								csWxMenu.setCswmName(String.valueOf(sItem));
							
								//设置默认值
								if(csWxMenu.getCswmTime()==null)
									csWxMenu.setCswmTime(new Date());
								
								//根据自定义的默认值信息设置默认值
								if(CTRL!=null)
									CTRL.setObjectDefaultValue(csWxMenu);
								
								csWxMenu=csWxMenuService.saveCsWxMenu(csWxMenu);			
								
								on("save",csWxMenu,csWxMenu);
							}							
							
							csWxMenuService.updateCsWxMenu$NotNull(csWxMenu);						
							/************LAZY3Q_CODE_ADD************/
							/************LAZY3Q_CODE_ADD************/

							
							//记录操作日志
							LoggerHelper.writeLog(CsWxMenu.class,"add","添加了[微信菜单]["+csWxMenu.getCswmName()+"]",(Long)$.getSession("ccclubs_login_id"), csWxMenu,csWxMenu.getCswmId());
							$.SetTips("保存微信菜单成功");
						}else{
							String PARAMS = $.getString("PARAMS");
							String ids=$.getString("ids");
							if(!$.empty(PARAMS)){//根据查询条件批量更新
								Map params = $.eval(PARAMS);
								params.put("confirm", 1);
								params.put("definex","1=1");
								csWxMenuService.updateCsWxMenuByConfirm(ActionHelper.getSetValuesFromModel(csWxMenu), params);
								LoggerHelper.writeLog(CsWxMenu.class, "update", 
											"批量修改了[微信菜单]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(null,csWxMenu,false)
											,null);
							}else if(!$.empty(ids)){//根据ids批量更新
								String[] array = ids.split(",");
								for(String sid:array){
									csWxMenu.setCswmId(Long.valueOf(sid));
									CsWxMenu oldCsWxMenu = CsWxMenu.get(Long.valueOf(sid));
									csWxMenuService.updateCsWxMenu$NotNull(csWxMenu);
									LoggerHelper.writeLog(CsWxMenu.class, "update", 
											"修改了[微信菜单]["+oldCsWxMenu.getCswmName()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsWxMenu, csWxMenu,false)
											,Long.valueOf(sid));
									on($.getString("method"),csWxMenu,oldCsWxMenu);
								}
							}else{
								if($.empty($.getString("edittype"))){
									//设置填充值
									csWxMenu.setCswmTime(new Date());
									
									CsWxMenu oldCsWxMenu = CsWxMenu.get(csWxMenu.getCswmId());
									csWxMenuService.updateCsWxMenu$NotNull(csWxMenu);
									LoggerHelper.writeLog(CsWxMenu.class, "update", 
											"修改了[微信菜单]["+oldCsWxMenu.getCswmName()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsWxMenu, csWxMenu,false)
											,csWxMenu.getCswmId());
									on($.getString("method"),csWxMenu,oldCsWxMenu);									
								}else{
									CsWxMenu oldCsWxMenu = csWxMenuService.getCsWxMenuById(csWxMenu.getCswmId());
									//设置默认值
									csWxMenu.setCswmTime(oldCsWxMenu.getCswmTime());	
									
									//设置填充值
									if(csWxMenu.getCswmTime()==null)
										csWxMenu.setCswmTime(new Date());
									
									//从oldCsWxMenu同步csWxMenu未set过的值
									csWxMenu.mergeSet(oldCsWxMenu);
											
									csWxMenuService.updateCsWxMenu(csWxMenu);
									LoggerHelper.writeLog(CsWxMenu.class, "update", 
											"修改了[微信菜单]["+oldCsWxMenu.getCswmName()+"]",
											(Long)$.getSession("ccclubs_login_id"),LoggerHelper.getUpdateDescription(oldCsWxMenu, csWxMenu,true)
											,csWxMenu.getCswmId());
									on("update",csWxMenu,oldCsWxMenu);
								}
							}
							/************LAZY3Q_CODE_UPDATE************/
							/************LAZY3Q_CODE_UPDATE************/

							//记录操作日志
							$.SetTips("修改微信菜单成功");
						}	
						/************LAZY3Q_CODE_SAVE************/
						/************LAZY3Q_CODE_SAVE************/

						
						return null;
					}
				});
				
			}else{
				$.removeSession("csWxMenuToken");//移除令牌	
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
				return $.SendHtml("<script>"+complete+"("+$.json(csWxMenu)+")"+"</script>","UTF-8");
			}
			
			//如果点保存，则回到编辑页，如果点确定，则回到列表页
			//现在知道editorpoint和entrypoint的意义了吗
			//它们由地址加参数(如:http://xxx.xx/xx.xx?xx=xx&xxx=xxx)转成16进制字串进行传递的
			if($.getString("submiter","").indexOf("保存")>-1){//则回到编辑页
				if(bIsAdd)
					return $.Redirect("wxmenu_edit.do?entrypoint="+$.getString("entrypoint")+"&id="+csWxMenu.getCswmId());
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
	 * 删除微信菜单
	 * @return
	 */
	public String del()
	{
		try{
			//获取当前用户的权限，看当前用户有没有删除权限
			SystemHelper.getSrvLimit("admin/configurator/wx/wxmenu.do");
					/************LAZY3Q_CAN_DEL************/
					/************LAZY3Q_CAN_DEL************/

			if($.getRequest("canDel")==null || ((Boolean)$.getRequest("canDel"))==false){
				throw new RuntimeException("您没有权限删除该数据");
			}
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			//多个删除数据动作合并事务过程
			csWxMenuService.executeTransaction(new Function(){
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
							CsWxMenu csWxMenu = csWxMenuService.getCsWxMenuById(id);
							if(ConfigHelper.GetBoolean("logic_delete")==true)
								csWxMenuService.removeCsWxMenuById(id);
							else
							csWxMenuService.deleteCsWxMenuById(id);			
							//记录操作日志
							if(csWxMenu!=null)
								LoggerHelper.writeLog(CsWxMenu.class,"del","删除了[微信菜单]["+csWxMenu.getCswmName()+"]",(Long)$.getSession("ccclubs_login_id"), csWxMenu);
							on("delete",csWxMenu,csWxMenu);
					}
					/************LAZY3Q_CODE_DEL************/
					/************LAZY3Q_CODE_DEL************/

					
					return null;
				}
			});
			
			$.SetTips("删除微信菜单成功");
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
	 * 显示微信菜单详情
	 * @return
	 */
	public String details()
	{
		try{
			final Ctrl CTRL = $.setRequest("CTRL",$.CTRL($.getString("ctrl")));//可自定义配置控制器,定义哪些字段可显示、可编辑、是否可查询等
			Long id=$.getLong("id");
			Long key=$.getLong("key");
			if(id!=null)
				csWxMenu=csWxMenuService.getCsWxMenuById(id);
			if(key!=null && id==null)
				csWxMenu=CsWxMenu.Get($.add(CsWxMenu.F.cswmId,key));
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
	 * 根据菜单名称或ID查询微信菜单
	 * 返回ajax数据
	 * @return
	 */
	public String query(){
		try{
			//根据当前的类，把表单参数转成Dao查询需要的Map参数格式,ActionHelper.getQueryFormParams这个东东在Lazy3q.jar中
			Map<String,Object> params = ActionHelper.getQueryFormParams(CsWxMenu.class);
			
			//反强制限制域
			
			//取排序参数,放入查询条件中，取不到放入查询条件中也没关系，因为Dao层会判断的
			String strAsc=$.getString("asc");//升序字段
			params.put("asc", strAsc);//放入查询条件
			String strDesc=$.getString("desc");//降序字段
			params.put("desc",strDesc);//放入查询条件
			if($.empty(strAsc) && $.empty(strDesc))//如果未传入排序字段
				params.put("desc","cswm_id");//那么，默认以主键降序，传给Dao层
								
			String strTerm=$.getString("value");//智能搜索时的参数，一般都是主键后面那个字段
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("cswmId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or cswm_name like '"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}
			/************LAZY3Q_CODE_QUERY************/
			/************LAZY3Q_CODE_QUERY************/

			
			//是否需要整个数据对象
			Boolean bObject=$.getBoolean("object",false);
			
			List<CsWxMenu> list = csWxMenuService.getCsWxMenuPage(0,$.getInteger("size",10),params).getResult();
			
			/**
			* OK!取到数据拼成放入Map中，[{value,text,object:{...}},...]
			* value:数据ID，text:数据标识,object,整个对象
			**/
			List<Map> result = new java.util.ArrayList();
			for(CsWxMenu csWxMenu:list){
				Map map = new HashMap();
				map.put("value",csWxMenu.getCswmId().toString());
				map.put("text", $.js(csWxMenu.getKeyValue()));
				if(bObject==true)
					map.put("object",csWxMenu);
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


	public ICsWxMenuService getCsWxMenuService()
	{
		return csWxMenuService;
	}

	public void setCsWxMenuService(ICsWxMenuService csWxMenuService)
	{
		this.csWxMenuService = csWxMenuService;
	}

	public CsWxMenu getCsWxMenu()
	{
		return csWxMenu;
	}

	public void setCsWxMenu(CsWxMenu csWxMenu)
	{
		this.csWxMenu = csWxMenu;
	}
}