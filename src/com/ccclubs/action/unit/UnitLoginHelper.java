package com.ccclubs.action.unit;

import java.util.List;

import com.ccclubs.model.CsMemberShip;
import com.ccclubs.model.CsUnitInfo;
import com.ccclubs.model.CsUnitUser;
import com.lazy3q.web.helper.$;

public class UnitLoginHelper{

	private static String unit_login="unit_login";

	public static void setLogin(CsUnitUser csUnitUser) {
		$.setSession(unit_login, csUnitUser);
	}
	
	public static CsUnitUser getLogin(){ 
		return $.getSession(unit_login);
	}

	public static String getLoginAt(){ 
		CsUnitUser user = getLogin();
		return user==null?null:("CsUnitUser@"+user.getCsuuId());
	}
	
	public static String getUseIds(){
		if(UnitLoginHelper.getLogin()==null)
			return null;
		if($.empty(UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiMember()))
			return null;
		List<CsMemberShip> list = CsMemberShip.list($.add("definex", "csms_payer in ("+UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiMember()+")"), 1024);
		String strIds = UnitLoginHelper.getLogin().get$csuuUnitInfo().getCsuiMember();
		//String strIds="0";
		for(CsMemberShip csMemberShip:list)
			strIds+=","+csMemberShip.getCsmsTargeter();
		return strIds;
	}
	
}
