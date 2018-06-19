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
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsUserPack;
import com.ccclubs.service.admin.ICsUserPackService;
/**************LAZY3Q_CODE_IMPORT**************/
import java.util.Calendar;
import com.ccclubs.model.CsGift;
import com.ccclubs.model.CsPack;
import com.ccclubs.model.CsPackInfo;
import com.ccclubs.service.admin.ICsGiftService;
import com.ccclubs.service.admin.ICsPackInfoService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.config.SYSTEM;
/**************LAZY3Q_CODE_IMPORT**************/


/**
 * 用户套餐的Action
 * @author 飞啊飘啊
 * 感谢struts的Action框架
 */
@SuppressWarnings("unchecked")
public class PackAction
{
	ICsUserPackService csUserPackService;
	
	CsUserPack csUserPack;
	
	/**
	 * 用户套餐管理首页
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
 			objects.put(CsUserPack.F.csupPack, $.getLong(CsUserPack.F.csupPack));
 			objects.put(CsUserPack.F.csupMember, UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiMember());
			objects.put(CsUserPack.F.csupUntilTime+"Start", $.getDate(CsUserPack.F.csupUntilTime+"Start"));
			objects.put(CsUserPack.F.csupUntilTime+"End", $.getDate(CsUserPack.F.csupUntilTime+"End"));
			objects.put(CsUserPack.F.csupUntilTime, $.getString(CsUserPack.F.csupUntilTime));
			objects.put(CsUserPack.F.csupUpdateTime+"Start", $.getDate(CsUserPack.F.csupUpdateTime+"Start"));
			objects.put(CsUserPack.F.csupUpdateTime+"End", $.getDate(CsUserPack.F.csupUpdateTime+"End"));
			objects.put(CsUserPack.F.csupUpdateTime, $.getString(CsUserPack.F.csupUpdateTime));
			objects.put(CsUserPack.F.csupAddTime+"Start", $.getDate(CsUserPack.F.csupAddTime+"Start"));
			objects.put(CsUserPack.F.csupAddTime+"End", $.getDate(CsUserPack.F.csupAddTime+"End"));
			objects.put(CsUserPack.F.csupAddTime, $.getString(CsUserPack.F.csupAddTime));
 			objects.put(CsUserPack.F.csupState, $.getShort(CsUserPack.F.csupState));
 			objects.put(CsUserPack.F.csupStatus, $.getShort(CsUserPack.F.csupStatus));
			
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
				objects.put("desc","csup_id");
			
			//把request传过来的查询参数放回request
			Set<String> keys = objects.keySet();
			for(String key:keys)
				$.SetRequest(key, objects.get(key));
			
			$.SetRequest("all", $.getBoolean(requests, "all"));		
			
			$.SetRequest("canQuery", $.getBoolean("canQuery",true));	
			if($.getBoolean("export",false)==true)//如果是数据导出
			{
				final Map<String, Object> finalParams = objects;
				return (String) $.queue(new Function(){
					@Override
					public <T> T execute(Object... arg0) {
						return (T) export(finalParams,$.getBoolean("all",false));
					}
				});
			}
						
			Page page = csUserPackService.getCsUserPackPage($.getInteger(requests, "page", 0), $.getInteger("size",16), objects);
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
	 * 数据导出
	 * @return
	 */
	public String export(Map params,Boolean bAll){		
		try{
			
			final int EXPORT_MAX = 1000;//一次最大导出1000
		 	final int EXPORT_QUERY_SIZE = 100;//一次查询的数量			
			int pageIndex = $.getInteger("page",0);//导出的页码			
			//导出的页码转成（一次查询的数量）的页码范围
			int start = EXPORT_MAX/EXPORT_QUERY_SIZE*pageIndex;
			int finish = EXPORT_MAX/EXPORT_QUERY_SIZE*(pageIndex+1);
		
		
			javax.servlet.http.HttpServletResponse response = com.lazy3q.xwork.WebContext.getServletResponse();
			
			response.setCharacterEncoding("UTF-8"); 
			java.io.PrintWriter out = response.getWriter();			
			response.setContentType("application/x-octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode("用户套餐","UTF-8")+"."+pageIndex+".xls");
			
			
			out.println("<table border=1 cellspacing=0 cellpadding=0>");
			
			out.println("<tr>");
			
			{
					out.println("<td>编号</td>");
		 	}
			
			{
							out.println("<td>[所属系统套餐]套餐名称</td>");
		 	}
			
			{
							out.println("<td>[所属会员]真实姓名</td>");
		 	}
			if(bAll)
			{
					out.println("<td>备注</td>");
		 	}
			
			{
					out.println("<td>有效期至</td>");
		 	}
			
			{
					out.println("<td>修改时间</td>");
		 	}
			
			{
					out.println("<td>添加时间</td>");
		 	}
			
			{
					out.println("<td>套餐状态</td>");
		 	}
		 	out.println("</tr>");
		 	
		 	
		 	
			int last = -1;
			for(int i=start;i<finish;i++){
				Page page = csUserPackService.getCsUserPackPage(i,EXPORT_QUERY_SIZE,params);
				if(page.getIndex()==last)
					break;
				last = page.getIndex();
				List<CsUserPack> list = page.getResult();
				for(CsUserPack item:list){
					out.println("<tr>");
					
					{
						String strValue="";					
						 strValue=""+item.getCsupId();
						String strHtml = $.html(strValue);	
						if($.empty(strHtml))
							strHtml = " ";	
						out.println("<td>"+strHtml+"</td>");
				 	}
					
					{
						String strValue="";					
					 	 	com.ccclubs.model.CsPack csPack = com.ccclubs.model.CsPack.getCsPackById(item.getCsupPack());
								if(csPack!=null)
									strValue=""+csPack.getCspName();
								strValue = $.html(strValue);	
								if($.empty(strValue))
									strValue = " ";	
								out.println("<td>"+strValue+"</td>");									
				 	}
					
					{
						String strValue="";					
					 	 	com.ccclubs.model.CsMember csMember = com.ccclubs.model.CsMember.getCsMemberById(item.getCsupMember());
								if(csMember!=null)
									strValue=""+csMember.getCsmName();
								strValue = $.html(strValue);	
								if($.empty(strValue))
									strValue = " ";	
								out.println("<td>"+strValue+"</td>");									
				 	}
					if(bAll)
					{
						String strValue="";					
					 	 strValue=""+(item.getCsupRemark());
						String strHtml = $.html(strValue);	
						if($.empty(strHtml))
							strHtml = " ";	
						out.println("<td>"+strHtml+"</td>");
				 	}
					
					{
						String strValue="";					
					 	 strValue=""+$.date(item.getCsupUntilTime(),"yyyy-MM-dd HH:mm:ss");
						String strHtml = $.html(strValue);	
						if($.empty(strHtml))
							strHtml = " ";	
						out.println("<td>"+strHtml+"</td>");
				 	}
					
					{
						String strValue="";					
					 	 strValue=""+$.date(item.getCsupUpdateTime(),"yyyy-MM-dd HH:mm:ss");
						String strHtml = $.html(strValue);	
						if($.empty(strHtml))
							strHtml = " ";	
						out.println("<td>"+strHtml+"</td>");
				 	}
					
					{
						String strValue="";					
					 	 strValue=""+$.date(item.getCsupAddTime(),"yyyy-MM-dd HH:mm:ss");
						String strHtml = $.html(strValue);	
						if($.empty(strHtml))
							strHtml = " ";	
						out.println("<td>"+strHtml+"</td>");
				 	}
					
					{
						String strValue="";					
						 if($.equals(""+item.getCsupStatus(),"1"))
						 	strValue=""+"有效";		 
						 if($.equals(""+item.getCsupStatus(),"0"))
						 	strValue=""+"无效";		 
						 if($.equals(""+item.getCsupStatus(),"2"))
						 	strValue=""+"过期";		 
						 if($.equals(""+item.getCsupStatus(),"3"))
						 	strValue=""+"用完";		 
						String strHtml = $.html(strValue);	
						if($.empty(strHtml))
							strHtml = " ";	
						out.println("<td>"+strHtml+"</td>");
				 	}
					out.println("</tr>");
				}
			}
			
			out.println("</table>");
			
			out.flush();
			
			return null;
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendHtml("<script>alert(\"系统繁忙,请稍候再试\")</script>", "UTF-8");
		}
	}
	
	
	
	/**
	 * 用户套餐添加/编辑页面
	 * @return
	 */
	public String edit()
	{		
		try{
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csUserPack);//进入之前的预处理	
			$.SetRequest("controller",controller);
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
			$.SetRequest("ids",$.getString(params, "ids"));
			$.setSession("csUserPackToken", $.uuid());
			Long id=$.getLong(params, "id");
			if(id!=null){
				csUserPack=csUserPackService.getCsUserPackById(id);
			}
			if(csUserPack==null)
				csUserPack = new CsUserPack();
			//根据自定义的默认值信息设置默认值
			ControllerHelper.setObjectDefaultValue(csUserPack,controller);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";
	}
	
	/**
	 * 显示用户套餐详情
	 * @return
	 */
	public String details()
	{
		try{
			$.SetRequest("controller", $.getJson("controller"));
			Map params = ActionContext.getContext().getParameters();
			Long id=$.getLong(params,"id");
			csUserPack=csUserPackService.getCsUserPackById(id);
			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";
	}


	/**
	 * 根据编号或ID查询用户套餐
	 * @return
	 */
	public String query(){
		try{
 			Map<String,Object> params=new HashMap();		
 			params.put("csupId", $.getLong("csupId"));
 			params.put("csupPack", $.getLong("csupPack"));
 			params.put("csupMember", $.getLong("csupMember"));
 			params.put("csupRemark", $.getString("csupRemark"));
			params.put("csupUntilTimeStart", $.getDate("csupUntilTimeStart"));
			params.put("csupUntilTimeEnd", $.getDate("csupUntilTimeEnd"));
			params.put("csupUntilTime", $.getString("csupUntilTime"));
			params.put("csupUpdateTimeStart", $.getDate("csupUpdateTimeStart"));
			params.put("csupUpdateTimeEnd", $.getDate("csupUpdateTimeEnd"));
			params.put("csupUpdateTime", $.getString("csupUpdateTime"));
			params.put("csupAddTimeStart", $.getDate("csupAddTimeStart"));
			params.put("csupAddTimeEnd", $.getDate("csupAddTimeEnd"));
			params.put("csupAddTime", $.getString("csupAddTime"));
 			params.put("csupState", $.getShort("csupState"));
 			params.put("csupStatus", $.getShort("csupStatus"));
			//取排序参数
			String strAsc=$.getString("asc");
			params.put("asc", strAsc);
			String strDesc=$.getString("desc");
			params.put("desc",strDesc);
			if($.empty(strAsc) && $.empty(strDesc))
				params.put("desc","csup_id");
								
			String strTerm=$.getString("value");
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("csupId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or csup_id like '%"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}
			
			Boolean bObject=$.getBoolean("object",false);
			
			List<CsUserPack> list = csUserPackService.getCsUserPackList(params, $.getInteger("size",10));
			List<Map> result = new java.util.ArrayList();
			for(CsUserPack csUserPack:list){
				Map map = new HashMap();
				map.put("value", $.zerofill(csUserPack.getCsupId().toString(), 6));
				String strText = "";
				strText+=csUserPack.getCsupId()+",";
				map.put("text", $.js(strText));
				if(bObject==true)
					map.put("object",csUserPack);
				result.add(map);
			}
			return $.SendAjax(result, "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}		
	}

	public ICsUserPackService getCsUserPackService()
	{
		return csUserPackService;
	}

	public void setCsUserPackService(ICsUserPackService csUserPackService)
	{
		this.csUserPackService = csUserPackService;
	}

	public CsUserPack getCsUserPack()
	{
		return csUserPack;
	}

	public void setCsUserPack(CsUserPack csUserPack)
	{
		this.csUserPack = csUserPack;
	}
}