/**
 * 
 */
package com.ccclubs.action.official;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.action.unit.UnitLoginHelper;
import com.ccclubs.helper.ActionHelper;
import com.ccclubs.helper.LoggerHelper;
import com.ccclubs.model.CsUnitGroup;
import com.ccclubs.model.CsUnitInfo;
import com.ccclubs.model.CsUnitPerson;
import com.ccclubs.model.CsUnitUser;
import com.ccclubs.service.admin.ICsUnitGroupService;
import com.ccclubs.service.admin.ICsUnitPersonService;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
//import com.lazy3q.web.helper.ConfigHelper;
import com.lazy3q.web.helper.ControllerHelper;
import com.lazy3q.web.util.Page;
import com.opensymphony.xwork2.ActionContext;

/**
 * 部门层级管理Action
 * @author zhuangtian
 *
 */
public class DeptAction {
	
	ICsUnitGroupService csUnitGroupService;
	public ICsUnitPersonService csUnitPersonService;
	CsUnitGroup csUnitGroup;
	
	public String execute(){
		try{
			
			$.setRequest("entrypoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));//查询条件传递到转跳
			Map<String,Object> params = ActionHelper.getQueryFormParams(CsUnitGroup.class);//把表单参数转成查询需要的参数格式
			CsUnitInfo unitInfo = UnitLoginHelper.getLogin().get$csuuUnitInfo();
			if(unitInfo.getCsuiId() != null){
				params.put(CsUnitGroup.F.csugInfo, unitInfo.getCsuiId());
			}
			params.put("definex", "(csug_status>=0)");
			Page<CsUnitGroup> page = csUnitGroupService.getCsUnitGroupPage($.getInteger("page",0), 16, params);
			$.SetRequest("csUnitGroup", page.getResult());
			$.SetRequest("page", page);
			
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙，请稍候再试");
		}
		return "index";
	}
	
	/**
	 * 编辑企业部门信息
	 * @return
	 */
	public String edit(){
		try{
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csUnitGroup);//进入之前的预处理
			$.SetRequest("controller",controller);
			Map params = ActionContext.getContext().getParameters();
			$.SetRequest("editorpoint",$.hex($.getUrlPath()+"?"+$.querys("UTF-8")));
			$.SetRequest("entrypoint", $.getString(params, "entrypoint"));
			$.SetRequest("id",$.getString(params, "id"));
			$.setSession("csUnitUserToken", $.uuid());
			Long id=$.getLong(params, "id");
			//获取部门下面的全部人员
			Map<String,Object> param=new HashMap();
			param.put("csupInfo", UnitLoginHelper.getLogin().getCsuuUnitInfo());
			param.put("definex", "(csup_status>=0)");
			param.put("csupGroup", $.getLong(params, "id"));
			List<CsUnitPerson> csUnitPersons = csUnitPersonService.getCsUnitPersonList(param, -1);
			$.setRequest("csUnitPersons", csUnitPersons);
			
			
			if(id != null){
				csUnitGroup = csUnitGroupService.getCsUnitGroupById(id);				
			}
			
			
			if(csUnitGroup == null)
				csUnitGroup = new CsUnitGroup();
			
			ControllerHelper.setObjectDefaultValue(csUnitGroup,controller);
			
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return "edit";
	}
	
	/**
	 * 保存企业部门信息
	 * @return
	 */
	public String save(){
		try{
			final CsUnitInfo unitInfo = UnitLoginHelper.getLogin().get$csuuUnitInfo();
			final Map controller = $.getJson("controller");
			ControllerHelper.executeControllerBefore(controller, csUnitGroup);//进入之前的预处理	
			$.SetRequest("controller",controller);
			//主键为空，表示插入,否则表示更新
			final Boolean bIsAdd = ($.empty($.getString("id")) && csUnitGroup.getCsugId()==null);
			
					if(bIsAdd){
							//新增信息，设置相关属性的默认值
							csUnitGroup.setCsugHost(UnitLoginHelper.getLogin().getCsuuHost());
							csUnitGroup.setCsugInfo(UnitLoginHelper.getLogin().getCsuuUnitInfo());						
							csUnitGroup.setCsugAddTime(new Date());
							csUnitGroup.setCsugUpdateTime(csUnitGroup.getCsugAddTime());
							
							
							csUnitGroup=csUnitGroupService.saveCsUnitGroup(csUnitGroup);
							ControllerHelper.setObjectDefaultValue(csUnitGroup,controller);
						//记录操作日志
						LoggerHelper.writeLog(CsUnitUser.class,"add","添加了[部门信息]["+csUnitGroup.getCsugName()+"]",(Long)$.getSession("ccclubs_login_id"), csUnitGroup);
						$.SetTips("保存部门信息成功");
					}else{
						String ids=$.getString("id");
						if(ids!=null && !ids.trim().equals("")){//批量更新
								CsUnitGroup csUnitGroup_new = csUnitGroupService.getCsUnitGroupById(Long.valueOf(ids));
								csUnitGroup_new.setCsugName(csUnitGroup.getCsugName());
								csUnitGroup_new.setCsugStatus(csUnitGroup.getCsugStatus());
								csUnitGroup_new.setCsugUpdateTime(new Date());
								csUnitGroup_new.setCsugPerson(csUnitGroup.getCsugPerson());
								csUnitGroupService.updateCsUnitGroup(csUnitGroup_new);
								$.SetTips("修改部门信息成功");
							
						}else{
								CsUnitGroup oldCsUnitGroup = csUnitGroupService.getCsUnitGroupById(csUnitGroup.getCsugId());
								//设置默认值
								csUnitGroup.setCsugHost(oldCsUnitGroup.getCsugHost());
								csUnitGroup.setCsugInfo(oldCsUnitGroup.getCsugInfo());
								
								csUnitGroup.setCsugStatus(oldCsUnitGroup.getCsugStatus());
								csUnitGroup.setCsugPerson(oldCsUnitGroup.getCsugPerson());
								csUnitGroup.setCsugUpdateTime(new Date());				
								//设置填充值
								
								csUnitGroupService.updateCsUnitGroup(oldCsUnitGroup);
							
						}
					}										
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙,请稍候再试");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	
	public String del(){
		try{
			Long pid=$.getLong("id");
			Long[] ids= new Long[]{pid};
			String strIds=$.getString( "ids");
			if(!$.empty(strIds) && !strIds.trim().equals("")){//批量删除
				String[] array = strIds.split(",");
				ids=new Long[array.length];
				for(int i=0;i<array.length;i++)
					ids[i]=Long.valueOf(array[i]);
			}		
			for(Long id:ids){
				csUnitGroupService.removeCsUnitGroupById(id);
					//记录操作日志
				if(csUnitGroup != null)
				LoggerHelper.writeLog(CsUnitGroup.class,"del","删除了[单位部门信息]["+csUnitGroup.getCsugName()+"]",(Long)$.getSession("ccclubs_login_id"), UnitLoginHelper.getLogin());
			}
			$.SetTips("部门删除成功");
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙，请稍后再试！");
		}
		return $.Redirect($.xeh($.getString("entrypoint")));
	}
	
	
	public String details(){
		return $.Redirect($.xeh($.getString("entrypoint")));
	}

	public ICsUnitGroupService getCsUnitGroupService() {
		return csUnitGroupService;
	}

	public void setCsUnitGroupService(ICsUnitGroupService csUnitGroupService) {
		this.csUnitGroupService = csUnitGroupService;
	}

	public CsUnitGroup getCsUnitGroup() {
		return csUnitGroup;
	}

	public void setCsUnitGroup(CsUnitGroup csUnitGroup) {
		this.csUnitGroup = csUnitGroup;
	}

	public ICsUnitPersonService getCsUnitPersonService() {
		return csUnitPersonService;
	}

	public void setCsUnitPersonService(ICsUnitPersonService csUnitPersonService) {
		this.csUnitPersonService = csUnitPersonService;
	}

	
}
