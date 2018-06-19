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
import com.ccclubs.model.CsUnitAirport;
import com.ccclubs.service.admin.ICsUnitAirportService;

/**
 * 酒店机场的Action
 * @author 飞啊飘啊
 * 感谢struts的Action框架
 */
@SuppressWarnings("unchecked")
public class AirportAction
{
	ICsUnitAirportService csUnitAirportService;
	
	CsUnitAirport csUnitAirport;
	
	/**
	 * 酒店机场管理首页
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
 			objects.put(CsUnitAirport.F.csuaUnitInfo, UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiId());
 			objects.put(CsUnitAirport.F.csuaType, $.getShort(CsUnitAirport.F.csuaType));
 			objects.put(CsUnitAirport.F.csuaFlight, $.getString(CsUnitAirport.F.csuaFlight));
			objects.put(CsUnitAirport.F.csuaTime+"Start", $.getDate(CsUnitAirport.F.csuaTime+"Start"));
			objects.put(CsUnitAirport.F.csuaTime+"End", $.getDate(CsUnitAirport.F.csuaTime+"End"));
			objects.put(CsUnitAirport.F.csuaTime, $.getString(CsUnitAirport.F.csuaTime));
 			objects.put(CsUnitAirport.F.csuaContact, $.getString(CsUnitAirport.F.csuaContact));
 			objects.put(CsUnitAirport.F.csuaMobile, $.getString(CsUnitAirport.F.csuaMobile));
			objects.put(CsUnitAirport.F.csuaUpdateTime+"Start", $.getDate(CsUnitAirport.F.csuaUpdateTime+"Start"));
			objects.put(CsUnitAirport.F.csuaUpdateTime+"End", $.getDate(CsUnitAirport.F.csuaUpdateTime+"End"));
			objects.put(CsUnitAirport.F.csuaUpdateTime, $.getString(CsUnitAirport.F.csuaUpdateTime));
			objects.put(CsUnitAirport.F.csuaAddTime+"Start", $.getDate(CsUnitAirport.F.csuaAddTime+"Start"));
			objects.put(CsUnitAirport.F.csuaAddTime+"End", $.getDate(CsUnitAirport.F.csuaAddTime+"End"));
			objects.put(CsUnitAirport.F.csuaAddTime, $.getString(CsUnitAirport.F.csuaAddTime));
 			objects.put(CsUnitAirport.F.csuaStatus, $.getShort(CsUnitAirport.F.csuaStatus));
			
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
				objects.put("desc","csua_id");
			
			//把request传过来的查询参数放回request
			Set<String> keys = objects.keySet();
			for(String key:keys)
				$.SetRequest(key, objects.get(key));
			
			$.SetRequest("all", $.getBoolean(requests, "all"));		
			
			$.SetRequest("canQuery", $.getBoolean("canQuery",true));	
									
			Page page = csUnitAirportService.getCsUnitAirportPage($.getInteger(requests, "page", 0), $.getInteger("size",16), objects);
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
	 * 酒店机场添加/编辑页面
	 * @return
	 */
	public String edit()
	{		
		try{
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csUnitAirport);//进入之前的预处理	
			$.SetRequest("controller",controller);
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
			$.SetRequest("ids",$.getString(params, "ids"));
			$.setSession("csUnitAirportToken", $.uuid());
			Long id=$.getLong(params, "id");
			if(id!=null){
				csUnitAirport=csUnitAirportService.getCsUnitAirportById(id);
			}
			if(csUnitAirport==null)
				csUnitAirport = new CsUnitAirport();
			//根据自定义的默认值信息设置默认值
			ControllerHelper.setObjectDefaultValue(csUnitAirport,controller);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";
	}
	
	
	/**
	 * 保存酒店机场
	 * @return
	 */
	public String save()
	{
		try{
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csUnitAirport);//进入之前的预处理	
			$.SetRequest("controller",controller);
			//主键为空，表示插入,否则表示更新
			final Boolean bIsAdd = ($.empty($.getString("ids")) && csUnitAirport.getCsuaId()==null);
			String requestCsUnitAirportToken = $.getString("csUnitAirportToken");
			String sessionCsUnitAirportToken = $.getSession("csUnitAirportToken");
			if($.equals(requestCsUnitAirportToken,sessionCsUnitAirportToken)){
				$.removeSession("csUnitAirportToken");//移除令牌				
				//多个写数据动作合并事务过程
				csUnitAirportService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						if(bIsAdd){
							//设置默认值
							if(csUnitAirport.getCsuaUpdateTime()==null)
								csUnitAirport.setCsuaUpdateTime(new Date());
							if(csUnitAirport.getCsuaAddTime()==null)
								csUnitAirport.setCsuaAddTime(new Date());
							
							//根据自定义的默认值信息设置默认值
							ControllerHelper.setObjectDefaultValue(csUnitAirport,controller);
							
							csUnitAirport.setCsuaStatus((short)0);
							csUnitAirport.setCsuaUnitInfo(UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiId());
							
							csUnitAirport=csUnitAirportService.saveCsUnitAirport(csUnitAirport);
							
							//记录操作日志
							LoggerHelper.writeLog(CsUnitAirport.class,"add","添加了[酒店机场]["+csUnitAirport.getCsuaId()+"]",0l, csUnitAirport);
							$.SetTips("保存酒店机场成功");
						}else{
							String ids=$.getString("ids");
							if(ids!=null && !ids.trim().equals("")){//批量更新
								String[] array = ids.split(",");
								for(String sid:array){
									csUnitAirport.setCsuaId(Long.valueOf(sid));
									csUnitAirportService.updateCsUnitAirport$NotNull(csUnitAirport);
								}
							}else{
								if(controller!=null && controller.get("fields")!=null && !((Map)controller.get("fields")).isEmpty()){
									
									//设置填充值
									csUnitAirport.setCsuaUpdateTime(new Date());
								
									csUnitAirportService.updateCsUnitAirport$NotNull(csUnitAirport);
								}else{
									CsUnitAirport oldCsUnitAirport = csUnitAirportService.getCsUnitAirportById(csUnitAirport.getCsuaId());
									//设置默认值
									csUnitAirport.setCsuaUpdateTime(oldCsUnitAirport.getCsuaUpdateTime());	
									csUnitAirport.setCsuaAddTime(oldCsUnitAirport.getCsuaAddTime());	
									
									//设置填充值
									if(csUnitAirport.getCsuaUpdateTime()==null)
										csUnitAirport.setCsuaUpdateTime(new Date());
									
									csUnitAirportService.updateCsUnitAirport(csUnitAirport);
								}					
							}						
							
							//记录操作日志
							LoggerHelper.writeLog(CsUnitAirport.class,"update","修改了[酒店机场]["+csUnitAirport.getCsuaId()+"]",0l, csUnitAirport);	
							$.SetTips("修改酒店机场成功");
						}					
						
						ControllerHelper.executeControllerAfter(controller, csUnitAirport);//数据保存后的自定义逻辑处理		
						
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
					return $.Redirect("airport_edit.do?entrypoint="+$.getString("entrypoint")+"&id="+csUnitAirport.getCsuaId());
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
	 * 删除酒店机场
	 * @return
	 */
	public String del()
	{
		try{
			$.SetRequest("controller", $.getJson("controller"));
			//多个删除数据动作合并事务过程
			csUnitAirportService.executeTransaction(new Function(){
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
							CsUnitAirport csUnitAirport = csUnitAirportService.getCsUnitAirportById(id);
							if(ConfigHelper.GetBoolean("logic_delete")==true)
								csUnitAirportService.removeCsUnitAirportById(id);
							else
							csUnitAirportService.deleteCsUnitAirportById(id);			
							//记录操作日志
							if(csUnitAirport!=null)
								LoggerHelper.writeLog(CsUnitAirport.class,"del","删除了[酒店机场]["+csUnitAirport.getCsuaId()+"]",0l, csUnitAirport);
					}
					
					return null;
				}
			});
			
			$.SetTips("删除酒店机场成功");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}	

	/**
	 * 显示酒店机场详情
	 * @return
	 */
	public String details()
	{
		try{
			$.SetRequest("controller", $.getJson("controller"));
			Map params = ActionContext.getContext().getParameters();
			Long id=$.getLong(params,"id");
			csUnitAirport=csUnitAirportService.getCsUnitAirportById(id);
			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";
	}


	/**
	 * 根据编号或ID查询酒店机场
	 * @return
	 */
	public String query(){
		try{
 			Map<String,Object> params=new HashMap();		
 			params.put("csuaId", $.getLong("csuaId"));
 			params.put("csuaUnitInfo", $.getLong("csuaUnitInfo"));
 			params.put("csuaType", $.getShort("csuaType"));
 			params.put("csuaFlight", $.getString("csuaFlight"));
			params.put("csuaTimeStart", $.getDate("csuaTimeStart"));
			params.put("csuaTimeEnd", $.getDate("csuaTimeEnd"));
			params.put("csuaTime", $.getString("csuaTime"));
 			params.put("csuaContact", $.getString("csuaContact"));
 			params.put("csuaMobile", $.getString("csuaMobile"));
			params.put("csuaCountMin", $.getInteger("csuaCountMin"));
			params.put("csuaCountMax", $.getInteger("csuaCountMax"));
			params.put("csuaUpdateTimeStart", $.getDate("csuaUpdateTimeStart"));
			params.put("csuaUpdateTimeEnd", $.getDate("csuaUpdateTimeEnd"));
			params.put("csuaUpdateTime", $.getString("csuaUpdateTime"));
			params.put("csuaAddTimeStart", $.getDate("csuaAddTimeStart"));
			params.put("csuaAddTimeEnd", $.getDate("csuaAddTimeEnd"));
			params.put("csuaAddTime", $.getString("csuaAddTime"));
 			params.put("csuaRemark", $.getString("csuaRemark"));
 			params.put("csuaStatus", $.getShort("csuaStatus"));
			//取排序参数
			String strAsc=$.getString("asc");
			params.put("asc", strAsc);
			String strDesc=$.getString("desc");
			params.put("desc",strDesc);
			if($.empty(strAsc) && $.empty(strDesc))
				params.put("desc","csua_id");
								
			String strTerm=$.getString("value");
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("csuaId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or csua_id like '%"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}
			
			Boolean bObject=$.getBoolean("object",false);
			
			List<CsUnitAirport> list = csUnitAirportService.getCsUnitAirportList(params, $.getInteger("size",10));
			List<Map> result = new java.util.ArrayList();
			for(CsUnitAirport csUnitAirport:list){
				Map map = new HashMap();
				map.put("value", $.zerofill(csUnitAirport.getCsuaId().toString(), 6));
				String strText = "";
				strText+=csUnitAirport.getCsuaId()+",";
				map.put("text", $.js(strText));
				if(bObject==true)
					map.put("object",csUnitAirport);
				result.add(map);
			}
			return $.SendAjax(result, "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}		
	}

	public ICsUnitAirportService getCsUnitAirportService()
	{
		return csUnitAirportService;
	}

	public void setCsUnitAirportService(ICsUnitAirportService csUnitAirportService)
	{
		this.csUnitAirportService = csUnitAirportService;
	}

	public CsUnitAirport getCsUnitAirport()
	{
		return csUnitAirport;
	}

	public void setCsUnitAirport(CsUnitAirport csUnitAirport)
	{
		this.csUnitAirport = csUnitAirport;
	}
}