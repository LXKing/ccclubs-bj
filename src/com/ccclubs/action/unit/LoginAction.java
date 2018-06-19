package com.ccclubs.action.unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsUnitUser;
import com.ccclubs.model.CsMemberShip;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsUnitInfoService;
import com.ccclubs.service.admin.ICsUnitUserService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsMemberShipService;
import com.lazy3q.web.helper.$;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction {
	
	ICsMemberService csMemberService;
	ICsUnitInfoService csUnitInfoService;
	ICsCarService csCarService;
	ICsMemberShipService csMemberShipService;
	ICsUnitUserService csUnitUserService;	
	

	public String execute(){
		
		return "index";
	}

	
	/**
	 * 登录
	 * @return
	 */
	public String login(){
		try{
			$.setSession("super_user", null);
			Map params = ActionContext.getContext().getParameters();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			String strRequestSyscode=$.getString(params,"syscode");
		    String strSessionSyscode = (String) session.getAttribute("syscode");
			session.removeAttribute("syscode");
			if (strRequestSyscode == null || !strRequestSyscode.equals(strSessionSyscode)){
				$.SetTips("对不起，验证码输入错误！");
				return "failure";
			}
			String strUsername=$.getString(params, "username");
			String strPassword=$.getString(params, "password");		
			if(strUsername==null || strUsername.trim().equals("")){
				$.SetTips("对不起，帐号输入为空！");
				return "failure";
			}
			if(strPassword==null || strPassword.trim().equals("")){
				$.SetTips("对不起，密码输入为空！");
				return "failure";
			}
			
			
			CsUnitUser csUnitUser = csUnitUserService.getCsUnitUser($.add(CsUnitUser.F.csuuUsername, strUsername));
			if(csUnitUser==null){
				$.SetTips("对不起，帐号不存在！");
				return "failure";
			}
			if(csUnitUser.getCsuuStatus().shortValue()!=1){
				$.SetTips("对不起，帐号已经失效！");
				return "failure";
			}
						
			if (!$.md5(strPassword).equals(csUnitUser.getCsuuPassword().toUpperCase())) {
				$.SetTips("对不起，密码输入错误！");
				return "failure";
			}
			
			List<CsCar> carlist = csCarService.getCsCarList($.add(CsCar.F.cscOutlets, csUnitUser.get$csuuUnitInfo().getCsuiOutlets()), -1);
			List<CsMemberShip> ships = csMemberShipService.getCsMemberShipList($.add(CsMemberShip.F.csmsPayer, csUnitUser.get$csuuUnitInfo().getCsuiMember()), -1);
			
			List<Map> cars = new ArrayList();
			for(CsCar csCar:carlist){
				cars.add(
					$.add("id", csCar.getCscId()).add("name", csCar.getCscNumber())
				);
			}
			
			List<Map> rooms = new ArrayList();
			for(CsMemberShip csMemberShip:ships){
				if(csMemberShip.get$csmsTargeter()!=null)
					rooms.add(
						$.add("id", csMemberShip.getCsmsTargeter()).add("name", csMemberShip.get$csmsTargeter().getCsmName())
					);
			}
			
			csUnitUser.setValues("cars", cars);
			csUnitUser.setValues("rooms", rooms);
			
			UnitLoginHelper.setLogin(csUnitUser);	
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误,请稍候再试");
			return "failure";
		}
		return "success";
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	public String logout (){
		try{
			UnitLoginHelper.setLogin(null);
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误["+this.getClass().getName().hashCode()+"],请稍候再试");
			return "failure";
		}
		return "failure";
	}
	
	
	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}


	public ICsUnitInfoService getCsUnitInfoService() {
		return csUnitInfoService;
	}


	public void setCsUnitInfoService(ICsUnitInfoService csUnitInfoService) {
		this.csUnitInfoService = csUnitInfoService;
	}


	public ICsCarService getCsCarService() {
		return csCarService;
	}


	public void setCsCarService(ICsCarService csCarService) {
		this.csCarService = csCarService;
	}


	public ICsMemberShipService getCsMemberShipService() {
		return csMemberShipService;
	}


	public void setCsMemberShipService(ICsMemberShipService csMemberShipService) {
		this.csMemberShipService = csMemberShipService;
	}


	public ICsUnitUserService getCsUnitUserService() {
		return csUnitUserService;
	}


	public void setCsUnitUserService(ICsUnitUserService csUnitUserService) {
		this.csUnitUserService = csUnitUserService;
	}
	
}
