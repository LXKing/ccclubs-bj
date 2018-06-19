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
import com.ccclubs.model.CsUnitFee;
import com.ccclubs.service.admin.ICsUnitFeeService;

/**
 * 价格配置的Action
 * @author 飞啊飘啊
 * 感谢struts的Action框架
 */
@SuppressWarnings("unchecked")
public class FeeAction
{
	ICsUnitFeeService csUnitFeeService;
	
	CsUnitFee csUnitFee;
	
	/**
	 * 价格配置管理首页
	 * @return
	 */	
	public String execute()
	{	
		try{			
			Map<String,Object> requests = ActionContext.getContext().getParameters();
			Map<String,Object> objects=new HashMap();
			//把request传过来的(String)参数转换成java对象
 			objects.put(CsUnitFee.F.csufUnitInfo, UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiId());
			Page page = csUnitFeeService.getCsUnitFeePage($.getInteger(requests, "page", 0), $.getInteger("size",16), objects);
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
	 * 价格配置添加/编辑页面
	 * @return
	 */
	public String edit()
	{		
		try{
			Map params = ActionContext.getContext().getParameters();			
			$.setSession("csUnitFeeToken", $.uuid());
			Long id=$.getLong(params, "id");
			if(id!=null)
				csUnitFee=csUnitFeeService.getCsUnitFeeById(id);			
			if(csUnitFee==null)
				csUnitFee = new CsUnitFee();
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";
	}
	
	
	/**
	 * 保存价格配置
	 * @return
	 */
	public String save()
	{
		try{			
			//主键为空，表示插入,否则表示更新
			final Boolean bIsAdd = csUnitFee.getCsufId()==null;
			String requestCsUnitFeeToken = $.getString("csUnitFeeToken");
			String sessionCsUnitFeeToken = $.getSession("csUnitFeeToken");
			if($.equals(requestCsUnitFeeToken,sessionCsUnitFeeToken)){
				$.removeSession("csUnitFeeToken");//移除令牌				
				//多个写数据动作合并事务过程
				csUnitFeeService.executeTransaction(new Function(){
					public <T> T execute(Object... arg0){
						if(bIsAdd){
							csUnitFee.setCsufHost(UnitLoginHelper.getLogin().getCsuuHost());
							csUnitFee.setCsufUnitInfo(UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiId());
							csUnitFee=csUnitFeeService.saveCsUnitFee(csUnitFee);							
							$.SetTips("保存价格配置成功");
						}else{
							csUnitFee.setCsufHost(UnitLoginHelper.getLogin().getCsuuHost());
							csUnitFee.setCsufUnitInfo(UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiId());
							csUnitFeeService.updateCsUnitFee$NotNull(csUnitFee);							
							$.SetTips("修改价格配置成功");
						}
						return null;
					}
				});
			}else{
				$.SetTips("对不起，请勿重复提交");
			}
			
			if($.getString("submiter","").indexOf("保存")>-1){
				if(bIsAdd)
					return $.Redirect("fee.edit.htl?id="+csUnitFee.getCsufId());
				else
					return $.Redirect("fee.do");
			}
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect("fee.do");
	}

	/**
	 * 删除价格配置
	 * @return
	 */
	public String del()
	{
		try{
			$.SetRequest("controller", $.getJson("controller"));
			Long id=$.getLong("id");
			csUnitFee = csUnitFeeService.getCsUnitFeeById(id);
			if(csUnitFee.getCsufUnitInfo().longValue()==UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiId().longValue()){
				csUnitFeeService.deleteCsUnitFeeById(id);
				$.SetTips("删除价格配置成功");
			}else{
				$.SetTips("您没有权限删除价格配置");
			}
		}catch(Exception e){			
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect("fee.do");
	}
	
	public String query(){		
		Long csufModel = $.getLong("csufModel");
		csUnitFee = csUnitFeeService.getCsUnitFee(
			$.add(CsUnitFee.F.csufModel, csufModel)
			.add(CsUnitFee.F.csufUnitInfo, UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiId())
		);
		if(csUnitFee!=null)
			return $.SendAjax($.add("exists", true).add("id", csUnitFee.getCsufId()), $.UTF8);
		else
			return $.SendAjax($.add("exists", false), $.UTF8);
	}

	public ICsUnitFeeService getCsUnitFeeService()
	{
		return csUnitFeeService;
	}

	public void setCsUnitFeeService(ICsUnitFeeService csUnitFeeService)
	{
		this.csUnitFeeService = csUnitFeeService;
	}

	public CsUnitFee getCsUnitFee()
	{
		return csUnitFee;
	}

	public void setCsUnitFee(CsUnitFee csUnitFee)
	{
		this.csUnitFee = csUnitFee;
	}
}