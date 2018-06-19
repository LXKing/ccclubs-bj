package com.ccclubs.action.official;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ccclubs.action.unit.UnitLoginHelper;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsMemberShip;
import com.ccclubs.model.CsUnitUser;
import com.ccclubs.service.admin.ICsCarService;
import com.ccclubs.service.admin.ICsMemberShipService;
import com.ccclubs.service.admin.ICsUnitUserService;
import com.ccclubs.service.admin.impl.CsCarService;
import com.ccclubs.service.admin.impl.CsMemberShipService;
import com.lazy3q.web.helper.$;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction {
	
	public ICsUnitUserService csUnitUserService;
	public ICsCarService csCarService;
	public ICsMemberShipService csMemberShipService;

	public String execute(){
		return "index";
	}
	
	public String login(){
		try{
			Map params = ActionContext.getContext().getParameters();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			String strRequestCode = $.getString(params, "syscode");
			String strSessionCode = (String) session.getAttribute("syscode");
			if(strRequestCode == null || !strRequestCode.equals(strSessionCode)){
				$.SetTips("验证码输入错误，请重新输入");
				return "failure";
			}
			String userName = $.getString(params, "username");
			String passWord = $.getString(params, "password");
			if(userName == null || userName.trim().equals("")){
				$.SetTips("账号不能为空，请重新输入！");
				return "failure";
			}
			
			if(passWord == null || passWord.trim().equals("")){
				$.SetTips("账号不能为空，请重新输入！");
				return "failure";
			}
			
			CsUnitUser csUnitUser = csUnitUserService.getCsUnitUser($.add(CsUnitUser.F.csuuUsername, userName));
			
			if(csUnitUser == null){
				$.SetTips("对不起，账号不存在！");
				return "failure";
			}
			
			if(csUnitUser.getCsuuStatus().shortValue() != 1){
				$.SetTips("对不起，账号已失效！");
				return "failure";
			}
			
			if(!$.md5(passWord).equals(csUnitUser.getCsuuPassword().toUpperCase())){
				$.SetTips("对不起,密码错误！");
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
		}catch(Exception e){
			e.printStackTrace();
			$.SetTips("系统错误，请稍后再试");
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
			$.setSession("ccclubs_login", null);
			$.setSession("ccclubs_login_id",null);
			$.setSession("session_like_host", null);
			$.setSession("unit_login", null);
			$.setCookie("/", "UID", null);
		}catch(Exception ex){
			ex.printStackTrace();
			$.SetTips("系统错误["+this.getClass().getName().hashCode()+"],请稍候再试");
			return "failure";
		}
		return "failure";
	}

	public ICsUnitUserService getCsUnitUserService() {
		return csUnitUserService;
	}

	public void setCsUnitUserService(ICsUnitUserService csUnitUserService) {
		this.csUnitUserService = csUnitUserService;
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


	
	
}
