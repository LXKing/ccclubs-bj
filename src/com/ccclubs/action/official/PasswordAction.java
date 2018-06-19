/**
 * 
 */
package com.ccclubs.action.official;

import java.util.Map;

import org.apache.log4j.Logger;

import com.ccclubs.action.unit.UnitLoginHelper;
import com.ccclubs.model.CsUnitUser;
import com.ccclubs.service.admin.ICsUnitUserService;
import com.lazy3q.web.helper.$;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author zhuangtian
 */
public class PasswordAction {
	
	public ICsUnitUserService csUnitUserService;
	
	public String execute(){
		return "index";
	}

	public String editPassword(){
		try{
			Map params = ActionContext.getContext().getParameters();
			String strOld=$.getString(params, "old");
			String strNew=$.getString(params, "new");
			CsUnitUser user = csUnitUserService.getCsUnitUserById(UnitLoginHelper.getLogin().getCsuuId());
			if(!user.getCsuuPassword().toUpperCase().equals($.md5(strOld).toUpperCase())){
				$.SetTips("旧密码输入错误");
				return "entry";
			}
			user.setCsuuPassword($.md5(strOld).toUpperCase());
			csUnitUserService.updateCsUnitUserByConfirm(
				$.add(CsUnitUser.F.csuuPassword, $.md5(strNew).toUpperCase()),
				$.add(CsUnitUser.F.csuuId, UnitLoginHelper.getLogin().getCsuuId()).add("confirm", 1));
			$.SetTips("密码修改成功");
		}catch(Exception e){
			e.printStackTrace();
			Logger.getRootLogger().error(e.getMessage(),e);
			$.SetTips("系统繁忙，请稍后再试");
		}
		return "entry";
	}
	
	public ICsUnitUserService getCsUnitUserService() {
		return csUnitUserService;
	}

	public void setCsUnitUserService(ICsUnitUserService csUnitUserService) {
		this.csUnitUserService = csUnitUserService;
	}

}
