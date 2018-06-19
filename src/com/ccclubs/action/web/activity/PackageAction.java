package com.ccclubs.action.web.activity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.exception.ErrorException;
import com.ccclubs.exception.MessageException;
import com.ccclubs.helper.SystemHelper;
import com.ccclubs.model.CsChjSign;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsPack;
import com.ccclubs.model.CsUserPack;
import com.ccclubs.service.admin.ICsChjSignService;
import com.ccclubs.service.admin.ICsMemberService;
import com.ccclubs.service.admin.ICsPackInfoService;
import com.ccclubs.service.admin.ICsPackService;
import com.ccclubs.service.admin.ICsUserPackService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.IPackageService;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;

public class PackageAction {
	
	//报名截止时间
	Date signTime = $.date("2014-01-05 10:00:00", "yyyy-MM-dd HH:mm:ss");	
	
	//活动开始时间
	Date startTime = $.date("2014-01-11 11:11:11", "yyyy-MM-dd HH:mm:ss");
	Date finishTime = $.date("2014-01-18 11:11:11", "yyyy-MM-dd HH:mm:ss");
		
	//套餐投放最大数量
	static Map<String,Integer> MAXES = 
		$.add(SYSTEM.NEW_YEAR_PACK_2013_KY,89)
		.add(SYSTEM.NEW_YEAR_PACK_2013_MG,23)
		.add(SYSTEM.NEW_YEAR_PACK_2013_SMART,8);

	IPackageService packageService;
	ICsPackService csPackService;
	ICsPackInfoService csPackInfoService;
	ICsUserPackService csUserPackService;
	ICsMemberService csMemberService;
	ICsChjSignService csChjSignService;
	
	/**
	 * 套餐购买页
	 * @return
	 */
	public String index(){
		
		$.SetRequest("unitil", signTime);		
		$.SetRequest("now", new Date());
		
		
		$.SetRequest("start", startTime);
		$.SetRequest("finish", finishTime);
		
		return "index";
	}	
	
	/**
	 * 预定购买提示页
	 * @return
	 */
	public String detail(){		
		try{			
			//判断用户是否登录	
			if(!SystemHelper.isLogin()){
				$.SetTips("亲，您还没有登录，请登录后再抢购");
				return "detail";
			}			
					
			//判断用户是否已经购买过套餐
			List<CsPack> packs = csPackService.getCsPackList(
					$.add("definex",""+CsPack.C.cspFlag+" like '"+SYSTEM.NEW_YEAR_PACK_2013_+"%'")
				, -1);
			String sIds = "";
			for(CsPack csPack:packs){
				if(!$.empty(sIds))
					sIds+=",";
				sIds+=csPack.getCspId();
			}
			if(!$.empty(sIds)){
				CsUserPack csUserPack = csUserPackService.getCsUserPack(
					$.add(CsUserPack.F.csupMember, SystemHelper.getLoginId())
					.add("definex","csup_pack in ("+sIds+")")
					.add(CsUserPack.F.csupStatus, 1)
				);
				if(csUserPack!=null){
					$.SetRequest("packaged", true);
					$.SetTips("亲，您已经购买过"+csUserPack.get$csupPack().getCspName()+"啦!");
					return "detail";
				}				
			}			
			
			CsMember csMember = csMemberService.getCsMemberById(SystemHelper.getLoginId());	
			
			//判断活动是否已经结束
			if(new Date().after(finishTime)){
				$.SetTips("亲，抢购活动已经结束了哦~~");	
				return "detail";
			}
			
			//判断活动是否已经开始
			if(new Date().before(startTime)){
				$.SetTips("亲，抢购活动还没开始哦~~");	
				return "detail";
			}
						
			//判断当前用户选择的类型套餐是否已经卖完
			String flag = $.getString("flag");
			CsPack csPack = csPackService.getCsPack($.add(CsPack.F.cspFlag,flag));
			if(csPack==null)
				throw new ErrorException("NEW_YEAR_PACK_NO_FLAG","找不到相关的春节套餐","找不到标识为"+flag+"的春节套餐");
			//取已经卖出的套餐个数
			Long saled = csUserPackService.getCsUserPackCount(
				$.add(CsUserPack.F.csupPack, csPack.getCspId())
				.add(CsUserPack.F.csupStatus, 1)
			);
			Map<String,Short> mapping = $.add(SYSTEM.NEW_YEAR_PACK_2013_KY, 1)
				.add(SYSTEM.NEW_YEAR_PACK_2013_MG, 2)
				.add(SYSTEM.NEW_YEAR_PACK_2013_SMART, 3);
			if(saled>MAXES.get(flag)){
				$.SetTips("不好意思，您来晚啦，当前车型套餐已经被预定完了^_^");	
				return "detail";
			}
			$.SetRequest("csPack", csPack);
		} catch (Exception ex) {
			ex.printStackTrace();
			$.SetTips(SYSTEM.ERROR_TIPS);
		}
		return "detail";
	}
	
	/**
	 * 购买确认
	 * @return
	 */
	public String buy(){		
		try{
			//判断用户是否登录	
			if(!SystemHelper.isLogin()){
				$.SetTips("亲，您还没有登录，请登录后再抢购");
				return "buy";
			}			
			
			//判断用户是否已经购买过套餐
			List<CsPack> packs = csPackService.getCsPackList(
					$.add("definex",""+CsPack.C.cspFlag+" like '"+SYSTEM.NEW_YEAR_PACK_2013_+"%'")
				, -1);
			String sIds = "";
			for(CsPack csPack:packs){
				if(!$.empty(sIds))
					sIds+=",";
				sIds+=csPack.getCspId();
			}
			if(!$.empty(sIds)){
				CsUserPack csUserPack = csUserPackService.getCsUserPack(
					$.add(CsUserPack.F.csupMember, SystemHelper.getLoginId())
					.add("definex","csup_pack in ("+sIds+")")
					.add(CsUserPack.F.csupStatus, 1)
				);
				if(csUserPack!=null){
					$.SetRequest("packaged", true);
					$.SetTips("亲，您已经购买过"+csUserPack.get$csupPack().getCspName()+"啦!");
					return "buy";
				}				
			}
						
			//判断活动是否已经结束
			if(new Date().after(finishTime)){
				$.SetTips("亲，抢购活动已经结束了哦~~");	
				return "buy";
			}
			
			//判断活动是否已经开始
			if(new Date().before(startTime)){
				$.SetTips("亲，抢购活动还没开始哦~~");	
				return "buy";
			}
			
			
			//判断当前用户选择的类型套餐是否已经卖完
			String flag = $.getString("flag");
			CsPack csPack = csPackService.getCsPack($.add(CsPack.F.cspFlag,flag));
			if(csPack==null)
				throw new ErrorException("NEW_YEAR_PACK_NO_FLAG","找不到相关的春节套餐","找不到标识为"+flag+"的春节套餐");
			//取已经卖出的套餐个数
			Long saled = csUserPackService.getCsUserPackCount(
				$.add(CsUserPack.F.csupPack, csPack.getCspId())
				.add(CsUserPack.F.csupStatus, 1)
			);
			Map<String,Short> mapping = $.add(SYSTEM.NEW_YEAR_PACK_2013_KY, 1)
				.add(SYSTEM.NEW_YEAR_PACK_2013_MG, 2)
				.add(SYSTEM.NEW_YEAR_PACK_2013_SMART, 3);
			if(saled>MAXES.get(flag)){
				$.SetTips("不好意思，您来晚啦，当前车型套餐已经被预定完了^_^");	
				return "buy";
			}	
			
			$.SetRequest("csPack", csPack);
			
			CsMember csMember = csMemberService.getCsMemberById(SystemHelper.getLoginId());			
			$.SetRequest("csMember", csMember);			
		
		} catch (Exception ex) {
			ex.printStackTrace();
			$.SetTips(SYSTEM.ERROR_TIPS);
		}
		return "buy";
	}
	
	
	public String submit(){
		try{
			//判断用户是否登录	
			if(!SystemHelper.isLogin()){
				$.SetTips("亲，您还没有登录，请登录后再抢购");
				return "message";
			}
			
			//判断用户是否已经购买过套餐
			List<CsPack> packs = csPackService.getCsPackList(
					$.add("definex",""+CsPack.C.cspFlag+" like '"+SYSTEM.NEW_YEAR_PACK_2013_+"%'")
				, -1);
			String sIds = "";
			for(CsPack csPack:packs){
				if(!$.empty(sIds))
					sIds+=",";
				sIds+=csPack.getCspId();
			}
			if(!$.empty(sIds)){
				CsUserPack csUserPack = csUserPackService.getCsUserPack(
					$.add(CsUserPack.F.csupMember, SystemHelper.getLoginId())
					.add("definex","csup_pack in ("+sIds+")")
					.add(CsUserPack.F.csupStatus, 1)
				);
				if(csUserPack!=null){
					$.SetRequest("packaged", true);
					$.SetTips("亲，您已经购买过"+csUserPack.get$csupPack().getCspName()+"啦!");
					return "message";
				}				
			}
						
			//判断活动是否已经结束
			if(new Date().after(finishTime)){
				$.SetTips("亲，抢购活动已经结束了哦~~");	
				return "message";
			}
			
			//判断活动是否已经开始
			if(new Date().before(startTime)){
				$.SetTips("亲，抢购活动还没开始哦~~");	
				return "message";
			}
			
			//判断当前用户选择的类型套餐是否已经卖完
			String flag = $.getString("flag");
			CsPack csPack = csPackService.getCsPack($.add(CsPack.F.cspFlag,flag));
			if(csPack==null)
				throw new ErrorException("NEW_YEAR_PACK_NO_FLAG","找不到相关的春节套餐","找不到标识为"+flag+"的春节套餐");
			//取已经卖出的套餐个数
			Long saled = csUserPackService.getCsUserPackCount(
				$.add(CsUserPack.F.csupPack, csPack.getCspId())
				.add(CsUserPack.F.csupStatus, 1)
			);
			Map<String,Short> mapping = $.add(SYSTEM.NEW_YEAR_PACK_2013_KY, 1)
				.add(SYSTEM.NEW_YEAR_PACK_2013_MG, 2)
				.add(SYSTEM.NEW_YEAR_PACK_2013_SMART, 3);
			if(saled>MAXES.get(flag)){
				$.SetTips("不好意思，您来晚啦，当前车型套餐已经被预定完了^_^");	
				return "buy";
			}	
			
			CsMember csMember = csMemberService.getCsMemberById(SystemHelper.getLoginId());			
			$.SetRequest("csMember", csMember);	
			
			packageService.executeBuyPackage(SystemHelper.getLoginId(), csPack.getCspId(), "2013春节套餐二次抢购" ,false);
			
			$.SetRequest("packaged", true);
			$.SetTips("恭喜您，您已经成功购买【"+csPack.getCspName()+"】!请与2014-1-20日前选定您需要租赁的车辆");
		} catch (MessageException me){
			me.printStackTrace();
			$.SetTips(me.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			$.SetTips(SYSTEM.ERROR_TIPS);
		}
		return "message";
	}
	

	public IPackageService getPackageService() {
		return packageService;
	}

	public void setPackageService(IPackageService packageService) {
		this.packageService = packageService;
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

	public ICsUserPackService getCsUserPackService() {
		return csUserPackService;
	}

	public void setCsUserPackService(ICsUserPackService csUserPackService) {
		this.csUserPackService = csUserPackService;
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}

	public ICsChjSignService getCsChjSignService() {
		return csChjSignService;
	}

	public void setCsChjSignService(ICsChjSignService csChjSignService) {
		this.csChjSignService = csChjSignService;
	}
	
}
