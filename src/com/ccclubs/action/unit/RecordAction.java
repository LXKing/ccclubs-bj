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
import com.ccclubs.model.CsUseRecord;
import com.ccclubs.service.admin.ICsUseRecordService;

/**
 * 免费优惠记录的Action
 * @author 飞啊飘啊
 * 感谢struts的Action框架
 */
@SuppressWarnings("unchecked")
public class RecordAction
{
	ICsUseRecordService csUseRecordService;
	
	CsUseRecord csUseRecord;
	
	/**
	 * 免费优惠记录管理首页
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
 			objects.put(CsUseRecord.F.csurMember, UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiMember());
 			objects.put(CsUseRecord.F.csurFreeHour, $.getLong(CsUseRecord.F.csurFreeHour));
 			objects.put(CsUseRecord.F.csurGift, $.getLong(CsUseRecord.F.csurGift));
 			objects.put(CsUseRecord.F.csurOrder, $.getLong(CsUseRecord.F.csurOrder));
			objects.put(CsUseRecord.F.csurAddTime+"Start", $.getDate(CsUseRecord.F.csurAddTime+"Start"));
			objects.put(CsUseRecord.F.csurAddTime+"End", $.getDate(CsUseRecord.F.csurAddTime+"End"));
			
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
				objects.put("desc","csur_id");
			
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
						
			Page page = csUseRecordService.getCsUseRecordPage($.getInteger(requests, "page", 0), $.getInteger("size",16), objects);
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
			response.setHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode("免费优惠记录","UTF-8")+"."+pageIndex+".xls");
			
			
			out.println("<table border=1 cellspacing=0 cellpadding=0>");
			
			out.println("<tr>");
			
			{
					out.println("<td>编号</td>");
		 	}
			
			{
							out.println("<td>[所属会员]真实姓名</td>");
		 	}
			
			{
							out.println("<td>[所属免费小时]编号</td>");
		 	}
			
			{
							out.println("<td>[所属优惠项]编号</td>");
		 	}
			
			{
							out.println("<td>[所属订单]订单编号</td>");
		 	}
			
			{
					out.println("<td>使用数量</td>");
		 	}
			
			{
					out.println("<td>添加时间</td>");
		 	}
		 	out.println("</tr>");
		 	
		 	
		 	
			int last = -1;
			for(int i=start;i<finish;i++){
				Page page = csUseRecordService.getCsUseRecordPage(i,EXPORT_QUERY_SIZE,params);
				if(page.getIndex()==last)
					break;
				last = page.getIndex();
				List<CsUseRecord> list = page.getResult();
				for(CsUseRecord item:list){
					out.println("<tr>");
					
					{
						String strValue="";					
						 strValue=""+item.getCsurId();
						String strHtml = $.html(strValue);	
						if($.empty(strHtml))
							strHtml = " ";	
						out.println("<td>"+strHtml+"</td>");
				 	}
					
					{
						String strValue="";					
					 	 	com.ccclubs.model.CsMember csMember = com.ccclubs.model.CsMember.getCsMemberById(item.getCsurMember());
								if(csMember!=null)
									strValue=""+csMember.getCsmName();
								strValue = $.html(strValue);	
								if($.empty(strValue))
									strValue = " ";	
								out.println("<td>"+strValue+"</td>");									
				 	}
					
					{
						String strValue="";					
					 	 	com.ccclubs.model.CsFreeHour csFreeHour = com.ccclubs.model.CsFreeHour.getCsFreeHourById(item.getCsurFreeHour());
								if(csFreeHour!=null)
									strValue=""+csFreeHour.getCsfhId();
								strValue = $.html(strValue);	
								if($.empty(strValue))
									strValue = " ";	
								out.println("<td>"+strValue+"</td>");									
				 	}
					
					{
						String strValue="";					
					 	 	com.ccclubs.model.CsGift csGift = com.ccclubs.model.CsGift.getCsGiftById(item.getCsurGift());
								if(csGift!=null)
									strValue=""+csGift.getCsgId();
								strValue = $.html(strValue);	
								if($.empty(strValue))
									strValue = " ";	
								out.println("<td>"+strValue+"</td>");									
				 	}
					
					{
						String strValue="";					
					 	 	com.ccclubs.model.CsOrder csOrder = com.ccclubs.model.CsOrder.getCsOrderById(item.getCsurOrder());
								if(csOrder!=null)
									strValue=""+csOrder.getCsoId();
								strValue = $.html(strValue);	
								if($.empty(strValue))
									strValue = " ";	
								out.println("<td>"+strValue+"</td>");									
				 	}
					
					{
						String strValue="";					
		 				 strValue=""+item.getCsurCount();	
						String strHtml = $.html(strValue);	
						if($.empty(strHtml))
							strHtml = " ";	
						out.println("<td>"+strHtml+"</td>");
				 	}
					
					{
						String strValue="";					
					 	 strValue=""+$.date(item.getCsurAddTime(),"yyyy-MM-dd HH:mm:ss");
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
	 * 免费优惠记录添加/编辑页面
	 * @return
	 */
	public String edit()
	{		
		try{
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csUseRecord);//进入之前的预处理	
			$.SetRequest("controller",controller);
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
			$.SetRequest("ids",$.getString(params, "ids"));
			$.setSession("csUseRecordToken", $.uuid());
			Long id=$.getLong(params, "id");
			if(id!=null){
				csUseRecord=csUseRecordService.getCsUseRecordById(id);
			}
			if(csUseRecord==null)
				csUseRecord = new CsUseRecord();
			//根据自定义的默认值信息设置默认值
			ControllerHelper.setObjectDefaultValue(csUseRecord,controller);
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";
	}

	/**
	 * 显示免费优惠记录详情
	 * @return
	 */
	public String details()
	{
		try{
			$.SetRequest("controller", $.getJson("controller"));
			Map params = ActionContext.getContext().getParameters();
			Long id=$.getLong(params,"id");
			csUseRecord=csUseRecordService.getCsUseRecordById(id);
			
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";
	}


	/**
	 * 根据编号或ID查询免费优惠记录
	 * @return
	 */
	public String query(){
		try{
 			Map<String,Object> params=new HashMap();		
 			params.put("csurId", $.getLong("csurId"));
 			params.put("csurMember", $.getLong("csurMember"));
 			params.put("csurFreeHour", $.getLong("csurFreeHour"));
 			params.put("csurGift", $.getLong("csurGift"));
 			params.put("csurOrder", $.getLong("csurOrder"));
			params.put("csurCountMin", $.getDouble("csurCountMin"));
			params.put("csurCountMax", $.getDouble("csurCountMax"));
			params.put("csurAddTimeStart", $.getDate("csurAddTimeStart"));
			params.put("csurAddTimeEnd", $.getDate("csurAddTimeEnd"));
			//取排序参数
			String strAsc=$.getString("asc");
			params.put("asc", strAsc);
			String strDesc=$.getString("desc");
			params.put("desc",strDesc);
			if($.empty(strAsc) && $.empty(strDesc))
				params.put("desc","csur_id");
								
			String strTerm=$.getString("value");
			if(!$.empty(strTerm)){
				if(strTerm.toLowerCase().startsWith("id:"))//如果查询条件以id:开头，则按ID查询
					params.put("csurId", strTerm.toLowerCase().replaceFirst("id:", ""));					
				else//按标识查询，模糊查询请带%
				{
					String strDefinex = "";
					strDefinex+=" or csur_id like '%"+strTerm.replaceAll("'", "''")+"%'";
					strDefinex="(2=1 "+strDefinex+")";
					params.put("definex",strDefinex);
				}					
			}
			
			Boolean bObject=$.getBoolean("object",false);
			
			List<CsUseRecord> list = csUseRecordService.getCsUseRecordList(params, $.getInteger("size",10));
			List<Map> result = new java.util.ArrayList();
			for(CsUseRecord csUseRecord:list){
				Map map = new HashMap();
				map.put("value", $.zerofill(csUseRecord.getCsurId().toString(), 6));
				String strText = "";
				strText+=csUseRecord.getCsurId()+",";
				map.put("text", $.js(strText));
				if(bObject==true)
					map.put("object",csUseRecord);
				result.add(map);
			}
			return $.SendAjax(result, "UTF-8");
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			return $.SendAjax("[]", "UTF-8");
		}		
	}

	public ICsUseRecordService getCsUseRecordService()
	{
		return csUseRecordService;
	}

	public void setCsUseRecordService(ICsUseRecordService csUseRecordService)
	{
		this.csUseRecordService = csUseRecordService;
	}

	public CsUseRecord getCsUseRecord()
	{
		return csUseRecord;
	}

	public void setCsUseRecord(CsUseRecord csUseRecord)
	{
		this.csUseRecord = csUseRecord;
	}
}