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
import com.ccclubs.model.CsGift;
import com.ccclubs.service.admin.ICsGiftService;

/**
 * 优惠内容的Action
 * @author 飞啊飘啊
 * 感谢struts的Action框架
 */
@SuppressWarnings("unchecked")
public class GiftAction
{
	ICsGiftService csGiftService;
	
	CsGift csGift;
	
	/**
	 * 优惠内容管理首页
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
 			objects.put(CsGift.F.csgMember, UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiMember());
 			objects.put(CsGift.F.csgSysPack, $.getLong(CsGift.F.csgSysPack));
 			objects.put(CsGift.F.csgUserPack, $.getLong(CsGift.F.csgUserPack));
 			objects.put(CsGift.F.csgGoods, $.getLong(CsGift.F.csgGoods));
 			objects.put(CsGift.F.csgOutlets, $.getLong(CsGift.F.csgOutlets));
 			objects.put(CsGift.F.csgModel, $.getLong(CsGift.F.csgModel));
 			//objects.put(CsGift.F.csgType, $.getShort(CsGift.F.csgType));
 			objects.put(CsGift.F.csgType, 0);
			objects.put(CsGift.F.csgUntilTime+"Start", $.getDate(CsGift.F.csgUntilTime+"Start"));
			objects.put(CsGift.F.csgUntilTime+"End", $.getDate(CsGift.F.csgUntilTime+"End"));
			objects.put(CsGift.F.csgUntilTime, $.getString(CsGift.F.csgUntilTime));
			objects.put(CsGift.F.csgUpdateTime+"Start", $.getDate(CsGift.F.csgUpdateTime+"Start"));
			objects.put(CsGift.F.csgUpdateTime+"End", $.getDate(CsGift.F.csgUpdateTime+"End"));
			objects.put(CsGift.F.csgUpdateTime, $.getString(CsGift.F.csgUpdateTime));
			objects.put(CsGift.F.csgAddTime+"Start", $.getDate(CsGift.F.csgAddTime+"Start"));
			objects.put(CsGift.F.csgAddTime+"End", $.getDate(CsGift.F.csgAddTime+"End"));
			objects.put(CsGift.F.csgAddTime, $.getString(CsGift.F.csgAddTime));
 			objects.put(CsGift.F.csgStatus, $.getShort(CsGift.F.csgStatus));
			
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
				objects.put("desc","csg_id");
			
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
						
			Page page = csGiftService.getCsGiftPage($.getInteger(requests, "page", 0), $.getInteger("size",16), objects);
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
			response.setHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode("优惠内容","UTF-8")+"."+pageIndex+".xls");
			
			
			out.println("<table border=1 cellspacing=0 cellpadding=0>");
			
			out.println("<tr>");
			
			{
					out.println("<td>编号</td>");
		 	}
			
			{
							out.println("<td>[所属会员]真实姓名</td>");
		 	}
			
			{
							out.println("<td>[系统套餐]套餐名称</td>");
		 	}
			if(bAll)
			{
							out.println("<td>[会员套餐]编号</td>");
		 	}
			
			{
							out.println("<td>[所属商品]名称</td>");
		 	}
			if(bAll)
			{
							out.println("<td>[所属网点]名称</td>");
		 	}
			if(bAll)
			{
							out.println("<td>[所属车型]车型名称</td>");
		 	}
			
			{
					out.println("<td>优惠类型</td>");
		 	}
			if(bAll)
			{
					out.println("<td>原始数量</td>");
		 	}
			
			{
					out.println("<td>可用数量</td>");
		 	}
			
			{
					out.println("<td>优惠价格</td>");
		 	}
			
			{
					out.println("<td>优惠折扣</td>");
		 	}
			
			{
					out.println("<td>有效期至</td>");
		 	}
			if(bAll)
			{
					out.println("<td>修改时间</td>");
		 	}
			if(bAll)
			{
					out.println("<td>添加时间</td>");
		 	}
			
			{
					out.println("<td>状态</td>");
		 	}
		 	out.println("</tr>");
		 	
		 	
		 	
			int last = -1;
			for(int i=start;i<finish;i++){
				Page page = csGiftService.getCsGiftPage(i,EXPORT_QUERY_SIZE,params);
				if(page.getIndex()==last)
					break;
				last = page.getIndex();
				List<CsGift> list = page.getResult();
				for(CsGift item:list){
					out.println("<tr>");
					
					{
						String strValue="";					
						 strValue=""+item.getCsgId();
						String strHtml = $.html(strValue);	
						if($.empty(strHtml))
							strHtml = " ";	
						out.println("<td>"+strHtml+"</td>");
				 	}
					
					{
						String strValue="";					
					 	 	com.ccclubs.model.CsMember csMember = com.ccclubs.model.CsMember.getCsMemberById(item.getCsgMember());
								if(csMember!=null)
									strValue=""+csMember.getCsmName();
								strValue = $.html(strValue);	
								if($.empty(strValue))
									strValue = " ";	
								out.println("<td>"+strValue+"</td>");									
				 	}
					
					{
						String strValue="";					
					 	 	com.ccclubs.model.CsPack csPack = com.ccclubs.model.CsPack.getCsPackById(item.getCsgSysPack());
								if(csPack!=null)
									strValue=""+csPack.getCspName();
								strValue = $.html(strValue);	
								if($.empty(strValue))
									strValue = " ";	
								out.println("<td>"+strValue+"</td>");									
				 	}
					if(bAll)
					{
						String strValue="";					
					 	 	com.ccclubs.model.CsUserPack csUserPack = com.ccclubs.model.CsUserPack.getCsUserPackById(item.getCsgUserPack());
								if(csUserPack!=null)
									strValue=""+csUserPack.getCsupId();
								strValue = $.html(strValue);	
								if($.empty(strValue))
									strValue = " ";	
								out.println("<td>"+strValue+"</td>");									
				 	}
					
					{
								out.println("<td>"+item.getCsgGoods$()+"</td>");									
				 	}
					if(bAll)
					{
						String strValue="";					
					 	 	com.ccclubs.model.CsOutlets csOutlets = com.ccclubs.model.CsOutlets.getCsOutletsById(item.getCsgOutlets());
								if(csOutlets!=null)
									strValue=""+csOutlets.getCsoName();
								strValue = $.html(strValue);	
								if($.empty(strValue))
									strValue = " ";	
								out.println("<td>"+strValue+"</td>");									
				 	}
					if(bAll)
					{
						String strValue="";			
								out.println("<td>"+item.getCsgGoods$()+"</td>");									
				 	}
					
					{
						String strValue="";					
						 if($.equals(""+item.getCsgType(),"0"))
						 	strValue=""+"数量";		 
						 if($.equals(""+item.getCsgType(),"1"))
						 	strValue=""+"价格";		 
						 if($.equals(""+item.getCsgType(),"2"))
						 	strValue=""+"折扣";		 
						String strHtml = $.html(strValue);	
						if($.empty(strHtml))
							strHtml = " ";	
						out.println("<td>"+strHtml+"</td>");
				 	}
					if(bAll)
					{
						String strValue="";					
		 				 strValue=""+item.getCsgCount();	
						String strHtml = $.html(strValue);	
						if($.empty(strHtml))
							strHtml = " ";	
						out.println("<td>"+strHtml+"</td>");
				 	}
					
					{
						String strValue="";					
		 				 strValue=""+item.getCsgRemain();	
						String strHtml = $.html(strValue);	
						if($.empty(strHtml))
							strHtml = " ";	
						out.println("<td>"+strHtml+"</td>");
				 	}
					
					{
						String strValue="";					
		 				 strValue=""+item.getCsgPrice();	
						String strHtml = $.html(strValue);	
						if($.empty(strHtml))
							strHtml = " ";	
						out.println("<td>"+strHtml+"</td>");
				 	}
					
					{
						String strValue="";					
		 				 strValue=""+item.getCsgRebate();	
						String strHtml = $.html(strValue);	
						if($.empty(strHtml))
							strHtml = " ";	
						out.println("<td>"+strHtml+"</td>");
				 	}
					
					{
						String strValue="";					
					 	 strValue=""+$.date(item.getCsgUntilTime(),"yyyy-MM-dd HH:mm:ss");
						String strHtml = $.html(strValue);	
						if($.empty(strHtml))
							strHtml = " ";	
						out.println("<td>"+strHtml+"</td>");
				 	}
					if(bAll)
					{
						String strValue="";					
					 	 strValue=""+$.date(item.getCsgUpdateTime(),"yyyy-MM-dd HH:mm:ss");
						String strHtml = $.html(strValue);	
						if($.empty(strHtml))
							strHtml = " ";	
						out.println("<td>"+strHtml+"</td>");
				 	}
					if(bAll)
					{
						String strValue="";					
					 	 strValue=""+$.date(item.getCsgAddTime(),"yyyy-MM-dd HH:mm:ss");
						String strHtml = $.html(strValue);	
						if($.empty(strHtml))
							strHtml = " ";	
						out.println("<td>"+strHtml+"</td>");
				 	}
					
					{
						String strValue="";					
						 if($.equals(""+item.getCsgStatus(),"1"))
						 	strValue=""+"有效";		 
						 if($.equals(""+item.getCsgStatus(),"0"))
						 	strValue=""+"无效";		 
						 if($.equals(""+item.getCsgStatus(),"2"))
						 	strValue=""+"过期";		 
						 if($.equals(""+item.getCsgStatus(),"3"))
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
	 * 优惠内容添加/编辑页面
	 * @return
	 */
	public String edit()
	{		
		try{
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csGift);//进入之前的预处理	
			$.SetRequest("controller",controller);
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
			$.SetRequest("ids",$.getString(params, "ids"));
			$.setSession("csGiftToken", $.uuid());
			Long id=$.getLong(params, "id");
			if(id!=null){
				csGift=csGiftService.getCsGiftById(id);
			}
			if(csGift==null)
				csGift = new CsGift();
			//根据自定义的默认值信息设置默认值
			ControllerHelper.setObjectDefaultValue(csGift,controller);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";
	}
	
	
	/**
	 * 显示优惠内容详情
	 * @return
	 */
	public String details()
	{
		try{
			$.SetRequest("controller", $.getJson("controller"));
			Map params = ActionContext.getContext().getParameters();
			Long id=$.getLong(params,"id");
			csGift=csGiftService.getCsGiftById(id);
			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";
	}


	/**
	 * 根据编号或ID查询优惠内容
	 * @return
	 */
	public String query(){
		try{
 			Map<String,Object> params=new HashMap();		
 			params.put("csgId", $.getLong("csgId"));
 			params.put("csgMember", $.getLong("csgMember"));
 			params.put("csgSysPack", $.getLong("csgSysPack"));
 			params.put("csgUserPack", $.getLong("csgUserPack"));
 			params.put("csgGoods", $.getLong("csgGoods"));
 			params.put("csgOutlets", $.getLong("csgOutlets"));
 			params.put("csgModel", $.getLong("csgModel"));
 			params.put("csgType", $.getShort("csgType"));
			params.put("csgCountMin", $.getDouble("csgCountMin"));
			params.put("csgCountMax", $.getDouble("csgCountMax"));
			params.put("csgRemainMin", $.getDouble("csgRemainMin"));
			params.put("csgRemainMax", $.getDouble("csgRemainMax"));
			params.put("csgPriceMin", $.getDouble("csgPriceMin"));
			params.put("csgPriceMax", $.getDouble("csgPriceMax"));
			params.put("csgRebateMin", $.getDouble("csgRebateMin"));
			params.put("csgRebateMax", $.getDouble("csgRebateMax"));
			params.put("csgUntilTimeStart", $.getDate("csgUntilTimeStart"));
			params.put("csgUntilTimeEnd", $.getDate("csgUntilTimeEnd"));
			params.put("csgUntilTime", $.getString("csgUntilTime"));
			params.put("csgUpdateTimeStart", $.getDate("csgUpdateTimeStart"));
			params.put("csgUpdateTimeEnd", $.getDate("csgUpdateTimeEnd"));
			params.put("csgUpdateTime", $.getString("csgUpdateTime"));
			params.put("csgAddTimeStart", $.getDate("csgAddTimeStart"));
			params.put("csgAddTimeEnd", $.getDate("csgAddTimeEnd"));
			params.put("csgAddTime", $.getString("csgAddTime"));
 			params.put("csgStatus", $.getShort("csgStatus"));
			//取排序参数
			String strAsc=$.getString("asc");
			params.put("asc", strAsc);
			String strDesc=$.getString("desc");
			params.put("desc",strDesc);
			if($.empty(strAsc) && $.empty(strDesc))
				params.put("desc","csg_id");
								
			String strTerm=$.getString("value");
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("csgId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or csg_id like '%"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}
			
			Boolean bObject=$.getBoolean("object",false);
			
			List<CsGift> list = csGiftService.getCsGiftList(params, $.getInteger("size",10));
			List<Map> result = new java.util.ArrayList();
			for(CsGift csGift:list){
				Map map = new HashMap();
				map.put("value", $.zerofill(csGift.getCsgId().toString(), 6));
				String strText = "";
				strText+=csGift.getCsgId()+",";
				map.put("text", $.js(strText));
				if(bObject==true)
					map.put("object",csGift);
				result.add(map);
			}
			return $.SendAjax(result, "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}		
	}

	public ICsGiftService getCsGiftService()
	{
		return csGiftService;
	}

	public void setCsGiftService(ICsGiftService csGiftService)
	{
		this.csGiftService = csGiftService;
	}

	public CsGift getCsGift()
	{
		return csGift;
	}

	public void setCsGift(CsGift csGift)
	{
		this.csGift = csGift;
	}
}