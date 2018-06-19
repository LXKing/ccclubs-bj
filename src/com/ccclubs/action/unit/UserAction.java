package com.ccclubs.action.unit;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.ConfigHelper;
import com.lazy3q.web.helper.ControllerHelper;
import com.lazy3q.web.util.Page;
import com.lazy3q.web.util.Tree;
import com.lazy3q.util.Function;

import com.opensymphony.xwork2.ActionContext;
		   
import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.model.CsUnitUser;
import com.ccclubs.service.admin.ICsUnitUserService;

/**
 * 酒店管理员的Action
 * @author 飞啊飘啊
 * 感谢struts的Action框架
 */
@SuppressWarnings("unchecked")
public class UserAction
{
	ICsUnitUserService csUnitUserService;
	
	CsUnitUser csUnitUser;
	
	/**
	 * 酒店管理员管理首页
	 * @return
	 */	
	public String execute()
	{	
		try{
			$.SetRequest("controller", $.getJson("controller"));
			//查询条件传递到转跳
			$.SetRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			Map<String,Object> requests = ActionContext.getContext().getParameters();
			Map<String,Object> objects=new HashMap();
			//把request传过来的(String)参数转换成java对象			
 			objects.put(CsUnitUser.F.csuuUsername, $.getString(CsUnitUser.F.csuuUsername));
 			objects.put(CsUnitUser.F.csuuUnitInfo, UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiId());
 			objects.put(CsUnitUser.F.csuuIsRoot, $.getBoolean(CsUnitUser.F.csuuIsRoot));
 			objects.put(CsUnitUser.F.csuuRealname, $.getString(CsUnitUser.F.csuuRealname));
 			objects.put(CsUnitUser.F.csuuMobile, $.getString(CsUnitUser.F.csuuMobile));
			objects.put(CsUnitUser.F.csuuAddTime+"Start", $.getDate(CsUnitUser.F.csuuAddTime+"Start"));
			objects.put(CsUnitUser.F.csuuAddTime+"End", $.getDate(CsUnitUser.F.csuuAddTime+"End"));
			objects.put(CsUnitUser.F.csuuAddTime, $.getString(CsUnitUser.F.csuuAddTime));
 			objects.put(CsUnitUser.F.csuuStatus, $.getShort(CsUnitUser.F.csuuStatus));
			
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
				objects.put("desc","csuu_id");
			
			//把request传过来的查询参数放回request
			Set<String> keys = objects.keySet();
			for(String key:keys)
				$.SetRequest(key, objects.get(key));
			
			$.SetRequest("all", $.getBoolean(requests, "all"));		
			
						
			Page page = csUnitUserService.getCsUnitUserPage($.getInteger(requests, "page", 0), $.getInteger("size",16), objects);
			//把分页对象放入request
			$.SetRequest("page", page);		
			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "index";
	}
	/**
	 * 酒店管理员添加/编辑页面
	 * @return
	 */
	public String edit()
	{		
		try{
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csUnitUser);//进入之前的预处理	
			$.SetRequest("controller",controller);
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
			$.SetRequest("ids",$.getString(params, "ids"));
			$.setSession("csUnitUserToken", $.uuid());
			Long id=$.getLong(params, "id");
			if(id!=null){
				csUnitUser=csUnitUserService.getCsUnitUserById(id);
			}
			if(csUnitUser==null)
				csUnitUser = new CsUnitUser();
			//根据自定义的默认值信息设置默认值
			ControllerHelper.setObjectDefaultValue(csUnitUser,controller);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";
	}
	
	
	/**
	 * 保存酒店管理员
	 * @return
	 */
	public String save()
	{
		try{
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csUnitUser);//进入之前的预处理	
			$.SetRequest("controller",controller);
			//主键为空，表示插入,否则表示更新
			final Boolean bIsAdd = ($.empty($.getString("ids")) && csUnitUser.getCsuuId()==null);
			String requestCsUnitUserToken = $.getString("csUnitUserToken");
			String sessionCsUnitUserToken = $.getSession("csUnitUserToken");
			if($.equals(requestCsUnitUserToken,sessionCsUnitUserToken)){
				$.removeSession("csUnitUserToken");//移除令牌				
				//多个写数据动作合并事务过程
				csUnitUserService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						if(bIsAdd){
							//批量插入处理
							String vValue = ""+(csUnitUser.getCsuuUsername()==null?"":csUnitUser.getCsuuUsername());
							String sArray[]={""+vValue};
							if(vValue.startsWith("[*]")){
								vValue=vValue.replace("[*]","");
								sArray=vValue.split("[\\s|\u3000]+");				
							}
							for(String sItem:sArray){
								if(!$.empty(sItem))
								csUnitUser.setCsuuUsername(String.valueOf(sItem));
								//设置默认值
								
								//设置默认值
								csUnitUser.setCsuuPassword($.md5(csUnitUser.getCsuuPassword()));
								if(csUnitUser.getCsuuAddTime()==null)
								csUnitUser.setCsuuAddTime(new Date());
								
								csUnitUser.setCsuuHost(UnitLoginHelper.getLogin().getCsuuHost());
								csUnitUser.setCsuuUnitInfo(UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiId());
								csUnitUser.setCsuuIsRoot(false);
								
								//根据自定义的默认值信息设置默认值
								ControllerHelper.setObjectDefaultValue(csUnitUser,controller);
								
								csUnitUser=csUnitUserService.saveCsUnitUser(csUnitUser);			
											
							}
							
							//记录操作日志
							LoggerHelper.writeLog(CsUnitUser.class,"add","添加了[酒店管理员]["+csUnitUser.getCsuuUsername()+"]",(Long)$.getSession("ccclubs_login_id"), csUnitUser);
							$.SetTips("保存酒店管理员成功");
						}else{
							String ids=$.getString("ids");
							if(ids!=null && !ids.trim().equals("")){//批量更新
								String[] array = ids.split(",");
								for(String sid:array){
									csUnitUser.setCsuuId(Long.valueOf(sid));
									csUnitUser.setCsuuUnitInfo(UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiId());
									csUnitUserService.updateCsUnitUser$NotNull(csUnitUser);
								}
							}else{
								if(controller!=null && controller.get("fields")!=null && !((Map)controller.get("fields")).isEmpty()){
									
									//设置填充值								
									csUnitUserService.updateCsUnitUser$NotNull(csUnitUser);
								}else{
									CsUnitUser oldCsUnitUser = csUnitUserService.getCsUnitUserById(csUnitUser.getCsuuId());
									//设置默认值
									csUnitUser.setCsuuUsername(oldCsUnitUser.getCsuuUsername());	
									csUnitUser.setCsuuPassword(oldCsUnitUser.getCsuuPassword());	
									csUnitUser.setCsuuAddTime(oldCsUnitUser.getCsuuAddTime());	
									csUnitUser.setCsuuLastIp(oldCsUnitUser.getCsuuLastIp());	
									csUnitUser.setCsuuLastTime(oldCsUnitUser.getCsuuLastTime());	
									csUnitUser.setCsuuIsRoot(oldCsUnitUser.getCsuuIsRoot());
									csUnitUser.setCsuuUnitInfo(oldCsUnitUser.getCsuuUnitInfo());
									csUnitUser.setCsuuHost(oldCsUnitUser.getCsuuHost());
									
									csUnitUser.setCsuuUnitInfo(UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiId());
									
									//设置填充值
									
									csUnitUserService.updateCsUnitUser(csUnitUser);
								}					
							}						
							
							//记录操作日志
							LoggerHelper.writeLog(CsUnitUser.class,"update","修改了[酒店管理员]["+csUnitUser.getCsuuUsername()+"]",(Long)$.getSession("ccclubs_login_id"), csUnitUser);	
							$.SetTips("修改酒店管理员成功");
						}					
						
						ControllerHelper.executeControllerAfter(controller, csUnitUser);//数据保存后的自定义逻辑处理		
						
						return null;
					}
				});
				
			}else{
				$.SetTips("对不起，请勿重复提交");
			}
			
			String redirectUrl = ControllerHelper.getValue(controller, "redirect", null);//自定义转跳地址
			if(!$.empty(redirectUrl))return $.Redirect(redirectUrl);
			
			if($.getString("submiter","").indexOf("保存")>-1){
				if(bIsAdd)
					return $.Redirect("user_edit.do?entrypoint="+$.getString("entrypoint")+"&id="+csUnitUser.getCsuuId());
				else
					return $.Redirect($.xeh($.getString("editorpoint")));
			}
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}

	/**
	 * 删除酒店管理员
	 * @return
	 */
	public String del()
	{
		try{
			$.SetRequest("controller", $.getJson("controller"));
			//多个删除数据动作合并事务过程
			csUnitUserService.executeTransaction(new Function(){
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
						CsUnitUser csUnitUser = csUnitUserService.getCsUnitUserById(id);
						if(csUnitUser.getCsuuUnitInfo().longValue()!=UnitLoginHelper.getLogin().getCsuuUnitInfo().longValue())
							continue;
						if(id.longValue()==UnitLoginHelper.getLogin().getCsuuId().longValue())
							continue;
						{
							if(ConfigHelper.GetBoolean("logic_delete")==true)
								csUnitUserService.removeCsUnitUserById(id);
							else
								csUnitUserService.deleteCsUnitUserById(id);		
							//记录操作日志
							if(csUnitUser!=null)
								LoggerHelper.writeLog(CsUnitUser.class,"del","删除了[酒店管理员]["+csUnitUser.getCsuuUsername()+"]",(Long)$.getSession("ccclubs_login_id"), csUnitUser);
						}
					}
					
					return null;
				}
			});
			
			$.SetTips("删除酒店管理员成功");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}	

	/**
	 * 显示酒店管理员详情
	 * @return
	 */
	public String details()
	{
		try{
			$.SetRequest("controller", $.getJson("controller"));
			Map params = ActionContext.getContext().getParameters();
			Long id=$.getLong(params,"id");
			csUnitUser=csUnitUserService.getCsUnitUserById(id);
			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";
	}


	/**
	 * 根据登录帐号或ID查询酒店管理员
	 * @return
	 */
	public String query(){
		try{
 			Map<String,Object> params=new HashMap();		
 			params.put("csuuId", $.getLong("csuuId"));
 			params.put("csuuUsername", $.getString("csuuUsername"));
 			params.put("csuuPassword", $.getString("csuuPassword"));
 			params.put("csuuUnitInfo", $.getLong("csuuUnitInfo"));
 			params.put("csuuIsRoot", $.getBoolean("csuuIsRoot"));
 			params.put("csuuLimit", $.getString("csuuLimit"));
 			params.put("csuuRealname", $.getString("csuuRealname"));
 			params.put("csuuMobile", $.getString("csuuMobile"));
			params.put("csuuAddTimeStart", $.getDate("csuuAddTimeStart"));
			params.put("csuuAddTimeEnd", $.getDate("csuuAddTimeEnd"));
			params.put("csuuAddTime", $.getString("csuuAddTime"));
 			params.put("csuuLastIp", $.getString("csuuLastIp"));
 			params.put("csuuLastTime", $.getString("csuuLastTime"));
 			params.put("csuuStatus", $.getShort("csuuStatus"));
			//取排序参数
			String strAsc=$.getString("asc");
			params.put("asc", strAsc);
			String strDesc=$.getString("desc");
			params.put("desc",strDesc);
			if($.empty(strAsc) && $.empty(strDesc))
				params.put("desc","csuu_id");
								
			String strTerm=$.getString("value");
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("csuuId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or csuu_username like '%"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}
			
			Boolean bObject=$.getBoolean("object",false);
			
			List<CsUnitUser> list = csUnitUserService.getCsUnitUserList(params, $.getInteger("size",10));
			List<Map> result = new java.util.ArrayList();
			for(CsUnitUser csUnitUser:list){
				Map map = new HashMap();
				map.put("value", $.zerofill(csUnitUser.getCsuuId().toString(), 6));
				String strText = "";
				strText+=csUnitUser.getCsuuUsername()+",";
				map.put("text", $.js(strText));
				if(bObject==true)
					map.put("object",csUnitUser);
				result.add(map);
			}
			return $.SendAjax(result, "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}		
	}

	public ICsUnitUserService getCsUnitUserService()
	{
		return csUnitUserService;
	}

	public void setCsUnitUserService(ICsUnitUserService csUnitUserService)
	{
		this.csUnitUserService = csUnitUserService;
	}

	public CsUnitUser getCsUnitUser()
	{
		return csUnitUser;
	}

	public void setCsUnitUser(CsUnitUser csUnitUser)
	{
		this.csUnitUser = csUnitUser;
	}
}