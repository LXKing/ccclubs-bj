package com.ccclubs.action.web.activity.newyear2015;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsChjSign;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsPack;
import com.ccclubs.model.CsUserPack;
import com.ccclubs.service.admin.ICsCarModelService;
import com.ccclubs.service.admin.ICsChjSignService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsPackInfoService;
import com.ccclubs.service.admin.ICsPackService;
import com.ccclubs.service.admin.ICsUserPackService;
import com.ccclubs.service.admin.ISrvPropertyService;
import com.ccclubs.service.web.IWebDefaultService;
import com.lazy3q.web.helper.$;

public class ActivityAction extends ActivityBase{

	IWebDefaultService	webDefaultService;
	ISrvPropertyService	srvPropertyService;
	ICsCarModelService	csCarModelService;
	ICsUserPackService  csUserPackService;
	ICsPackService csPackService;
	ICsMemberService csMemberService;
	ICsPackInfoService csPackInfoService;

	/**
	 * 首页 index页面
	 * 
	 * @return
	 */
	public String execute() {
		getChannel();
		
		Long[] step = getCurrentStep(null);
		$.setRequest("currentStep", step[0]);
		
		//如果是报名中的状态，获取是否已报名和是否报名
		if (SystemHelper.isLogin()){
			if(isSign()){
				$.setRequest("signed", 1);
			}else{
				String autoload = $.getString("autoload");
				if(autoload!=null && autoload.equals("true")){
					$.setRequest("autoload", 1);
				}
			}
		}
		
		//如果是抢购套餐中状态，获取用户是否已经抢购了套餐
		if(step[0] > 0 && step[0] <= 5){
			if (SystemHelper.isLogin()){
				
				//判断用户是否已经购买过套餐
				List<CsPack> packs = csPackService.getCsPackList($.add("definex",""+CsPack.C.cspFlag+" like '"+NEW_YEAR_PACK_2015_+"%'"), -1);
				String sIds = "";
				for(CsPack csPack:packs){
					if(!$.empty(sIds))
						sIds+=",";
					sIds+=csPack.getCspId();
				}
				
				//取一个套餐
				CsUserPack csUserPack = csUserPackService.getCsUserPack(
						$.add(CsUserPack.F.csupMember, SystemHelper.getLoginId())
						.add("definex","csup_pack in ("+sIds+")")
						.add(CsUserPack.F.csupStatus, 1));
				if(csUserPack !=null){
					$.setRequest("packed", 1);
				}
			}
		}
		
		if(step[0] ==0){
			$.setRequest("activity_state", 1);
		}else if(step[0] >0 && step[0] <4){
			$.setRequest("activity_state", 2);
		}else if(step[0] == 5){
			$.setRequest("activity_state", 3);
		}else if(step[0] == 4){
			$.setRequest("activity_state", 4);
		}else if(step[0]>5){
			$.setRequest("activity_state", 5);
		}

		return "index";
	}

	public String sign() {
		try {
			if (!SystemHelper.isLogin())
				return $.SendHtml("101", "UTF-8");

			Calendar calendar = Calendar.getInstance();
			// 2015年1月11日10点
			calendar.set(2015, 0, 11, 10, 0, 0);
			if ((new Date().getTime() - calendar.getTime().getTime()) > 0)
				// 已经截止了
				return $.SendHtml("109", SYSTEM.UTF8);
			
			ICsMemberService csMemberService = $.getBean("csMemberService");
			CsMember csMember = csMemberService.getCsMemberById(SystemHelper.getLoginId());
			if (csMember.getCsmName() == null || csMember.getCsmName() == "") {
				// 需要完善资料
				return $.SendHtml("107", SYSTEM.UTF8);
			}

			ICsChjSignService csChjSignService = $.getBean("csChjSignService");
			CsChjSign csChjSign = csChjSignService.getCsChjSign($.add(CsChjSign.F.cscsMember, SystemHelper.getLoginId()).add(CsChjSign.F.cscsYear, 2015));
			if (csChjSign == null) {
				$.SetRequest("csChjSign", csChjSign);
				csChjSign = new CsChjSign();
				csChjSign.setCscsMember(csMember.getCsmId());
				csChjSign.setCscsMobile(csMember.getCsmMobile());
				csChjSign.setCscsName(csMember.getCsmName());
				csChjSign.setCscsHost(csMember.getCsmHost());
				csChjSign.setCscsStatus((short) 0);
				csChjSign.setCscsUpdateTime(new Date());
				csChjSign.setCscsAddTime(new Date());
				csChjSign.setCscsYear((short)2015);
				
				try{
					String channel = $.getSession("channel");
					if(channel !=null){
						try{
							csChjSign.setCscsVisitChannel(Long.valueOf(channel));
						}catch(Exception e){
							csChjSign.setCscsVisitChannel(0l);	//其它渠道
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				
				csChjSignService.saveCsChjSign(csChjSign);

				return $.SendHtml("100", "UTF-8");
			} else {
				// 已经提交过报名了
				return $.SendHtml("108", SYSTEM.UTF8);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return $.SendHtml("9999", "UTF-8");
		}
	}

	public IWebDefaultService getWebDefaultService() {
		return webDefaultService;
	}

	public void setWebDefaultService(IWebDefaultService webDefaultService) {
		this.webDefaultService = webDefaultService;
	}

	public ISrvPropertyService getSrvPropertyService() {
		return srvPropertyService;
	}

	public void setSrvPropertyService(ISrvPropertyService srvPropertyService) {
		this.srvPropertyService = srvPropertyService;
	}

	public ICsCarModelService getCsCarModelService() {
		return csCarModelService;
	}

	public void setCsCarModelService(ICsCarModelService csCarModelService) {
		this.csCarModelService = csCarModelService;
	}

	public ICsUserPackService getCsUserPackService() {
		return csUserPackService;
	}

	public void setCsUserPackService(ICsUserPackService csUserPackService) {
		this.csUserPackService = csUserPackService;
	}

	public ICsPackService getCsPackService() {
		return csPackService;
	}

	public void setCsPackService(ICsPackService csPackService) {
		this.csPackService = csPackService;
	}

	public ICsPackInfoService getCsPackInfoService() {
		return csPackInfoService;
	}

	public void setCsPackInfoService(ICsPackInfoService csPackInfoService) {
		this.csPackInfoService = csPackInfoService;
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}
	
}
