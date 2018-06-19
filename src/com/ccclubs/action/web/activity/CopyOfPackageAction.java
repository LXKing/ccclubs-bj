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

public class CopyOfPackageAction {
	
	//报名截止时间
	Date signTime = $.date("2014-01-05 10:00:00", "yyyy-MM-dd HH:mm:ss");	
	
	//活动开始时间
	Date startTime = $.date("2014-01-10 11:00:00", "yyyy-MM-dd HH:mm:ss");
	Date finishTime = $.date("2014-01-20 11:00:00", "yyyy-MM-dd HH:mm:ss");
		
	//套餐投放最大数量
	static Map<String,Integer> MAXES = 
		$.add(SYSTEM.NEW_YEAR_PACK_2013_KY,80)
		.add(SYSTEM.NEW_YEAR_PACK_2013_MG,18)
		.add(SYSTEM.NEW_YEAR_PACK_2013_SMART,7);

	IPackageService packageService;
	ICsPackService csPackService;
	ICsPackInfoService csPackInfoService;
	ICsUserPackService csUserPackService;
	ICsChjSignService csChjSignService;
	ICsMemberService csMemberService;
	
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
			
			//判断用户是否已经报名
			CsChjSign csChjSign = csChjSignService.getCsChjSign(
				$.add(CsChjSign.F.cscsMember, SystemHelper.getLoginId())
			);
			if(csChjSign == null){
				$.SetTips("对不起，您没有报名，当前不能抢购套餐哦");
				return "detail";
			}
			
			CsMember csMember = csMemberService.getCsMemberById(SystemHelper.getLoginId());	
			if(csMember.getCsmMoney()<100){
				$.SetTips("抱歉，您当前充值账户余额不足100元，请先充值再参加抢购！<a style='color:blue;font-size:14px;' href='/my/alipay.html'>立即充值</a>");
				return "detail";
			}
			
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
			
			$.SetRequest("csChjSign", csChjSign);
			if(csChjSign.getCscsLocked()!=null && csChjSign.getCscsLocked()>0){
				if((new Date().getTime()-csChjSign.getCscsLockTime().getTime())<SYSTEM.HOUR*2){
					CsPack lockedPack = csPackService.getCsPack(
						$.add(CsPack.F.cspFlag,csChjSign.getCscsLocked())
					);
					$.SetRequest("lockedPack", lockedPack);
				}
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
			//取已经预定的套餐个数
			Long signed = csChjSignService.getCsChjSignCount(
				$.add(CsChjSign.F.cscsStatus, 1)
				.add(CsChjSign.F.cscsLocked, mapping.get(flag))
				.add(CsChjSign.F.cscsLockTime+"Start", new Date(new Date().getTime() - SYSTEM.HOUR*2))
			);
			if((saled+signed)>MAXES.get(flag)){
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
	 * 取消
	 * @return
	 */
	public String cancel(){
		try{			
			//判断用户是否登录	
			if(!SystemHelper.isLogin()){
				$.SetTips("亲，您还没有登录，请登录后再抢购");
				return "message";
			}	
			CsChjSign csChjSign = csChjSignService.getCsChjSign(
				$.add(CsChjSign.F.cscsMember, SystemHelper.getLoginId())
			);
			if(csChjSign == null){
				$.SetTips("对不起，您没有报名，操作失败");
				return "message";
			}
			csChjSign.setCscsLocked(0l);
			csChjSign.setCscsLockTime(null);
			csChjSignService.updateCsChjSign(csChjSign);
			$.SetTips("取消抢定成功，您可以重新选择其它套餐进行抢定");
		} catch (Exception ex) {
			ex.printStackTrace();
			$.SetTips(SYSTEM.ERROR_TIPS);
		}
		return "message";
	}
	
	
	/**
	 * 预定
	 * @return
	 */
	public String reserve(){		
		try{			
			//判断用户是否登录	
			if(!SystemHelper.isLogin()){
				$.SetTips("亲，您还没有登录，请登录后再抢购");
				return "reserve";
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
					return "reserve";
				}				
			}			
			
			
			//判断用户是否已经报名
			CsChjSign csChjSign = csChjSignService.getCsChjSign(
				$.add(CsChjSign.F.cscsMember, SystemHelper.getLoginId())
			);
			if(csChjSign == null){
				$.SetTips("对不起，您没有报名，当前不能预定套餐哦");
				return "reserve";
			}
			
			
			
			//判断活动是否已经结束
			if(new Date().after(finishTime)){
				$.SetTips("亲，抢购活动已经结束了哦~~");	
				return "reserve";
			}
			
			//判断活动是否已经开始
			if(new Date().before(startTime)){
				$.SetTips("亲，抢购活动还没开始哦~~");	
				return "reserve";
			}
			
			$.SetRequest("csChjSign", csChjSign);
			
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
			//取已经预定的套餐个数
			Long signed = csChjSignService.getCsChjSignCount(
				$.add(CsChjSign.F.cscsStatus, 1)
				.add(CsChjSign.F.cscsLocked, csPack.getCspId())
				.add(CsChjSign.F.cscsLockTime+"Start", new Date(new Date().getTime() - SYSTEM.HOUR*2))
			);
			if((saled+signed)>MAXES.get(flag)){
				$.SetTips("不好意思，您来晚啦，当前车型套餐已经被预定完了^_^");	
				return "reserve";
			}

			csChjSign.setCscsLocked(csPack.getCspId());
			csChjSign.setCscsLockTime(new Date());
			csChjSign.setCscsStatus((short)1);
			csChjSignService.updateCsChjSign(csChjSign);
			
			$.SetRequest("csPack", csPack);
			
			$.SetTips("恭喜您，抢定成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			$.SetTips(SYSTEM.ERROR_TIPS);
		}
		return "reserve";
	}
	
	
	
	/**
	 * 购买成功
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
			
			
			//判断用户是否已经报名
			CsChjSign csChjSign = csChjSignService.getCsChjSign(
				$.add(CsChjSign.F.cscsMember, SystemHelper.getLoginId())
			);
			if(csChjSign == null){
				$.SetTips("对不起，您没有报名，当前不能预定套餐哦");
				return "buy";
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
			//检查用户所购套餐是否是之前已经选定的,如果不是，或者不一样，则为用户预定该套餐
			if(csChjSign.getCscsLocked()==null || csPack.getCspId().longValue()!=csChjSign.getCscsLocked().longValue()){
				//取已经卖出的套餐个数
				Long saled = csUserPackService.getCsUserPackCount(
					$.add(CsUserPack.F.csupPack, csPack.getCspId())
					.add(CsUserPack.F.csupStatus, 1)
				);
				//取已经预定的套餐个数
				Long signed = csChjSignService.getCsChjSignCount(
					$.add(CsChjSign.F.cscsStatus, 1)
					.add(CsChjSign.F.cscsLocked, csPack.getCspId())
					.add(CsChjSign.F.cscsLockTime+"Start", new Date(new Date().getTime() - SYSTEM.HOUR*2))
				);
				if((saled+signed)>MAXES.get(flag)){
					$.SetTips("不好意思，您来晚啦，当前车型套餐已经被预定完了^_^");	
					return "buy";
				}	
				csChjSign.setCscsLocked(csPack.getCspId());
				csChjSign.setCscsLockTime(new Date());
				csChjSign.setCscsStatus((short)1);
				csChjSignService.updateCsChjSign(csChjSign);
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
			
			//判断用户是否已经报名
			CsChjSign csChjSign = csChjSignService.getCsChjSign(
				$.add(CsChjSign.F.cscsMember, SystemHelper.getLoginId())
			);
			if(csChjSign == null){
				$.SetTips("对不起，您没有报名，当前不能预定套餐哦");
				return "message";
			}
			if(csChjSign.getCscsLocked()==null || csChjSign.getCscsLocked().shortValue()==0){
				$.SetTips("对不起，您还没有预定，不能支付该套餐哦");
				return "message";
			}
			if((new Date().getTime()-csChjSign.getCscsLockTime().getTime())>SYSTEM.HOUR*2){
				$.SetTips("对不起，您预定的套餐已经超过两个小时支付时间，请重新预定后再购买");
				return "message";
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
			
			final CsPack csPack = CsPack.get(csChjSign.getCscsLocked());
			CsMember csMember = csMemberService.getCsMemberById(SystemHelper.getLoginId());			
			$.SetRequest("csMember", csMember);	
			
			final CsChjSign finalCsChjSign = csChjSign;
			csChjSignService.executeTransaction(new Function(){
				@Override
				public <T> T execute(Object... arg0) {
					packageService.executeBuyPackage(SystemHelper.getLoginId(), csPack.getCspId(), "2013春节套餐抢购" ,false);
					csChjSignService.updateCsChjSignByConfirm(
						$.add(CsChjSign.F.cscsStatus, 2),
						$.add(CsChjSign.F.cscsId, finalCsChjSign.getCscsId()).add("confirm", 1));
					return null;
				}
			});
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

	public ICsChjSignService getCsChjSignService() {
		return csChjSignService;
	}

	public void setCsChjSignService(ICsChjSignService csChjSignService) {
		this.csChjSignService = csChjSignService;
	}

	public ICsMemberService getCsMemberService() {
		return csMemberService;
	}

	public void setCsMemberService(ICsMemberService csMemberService) {
		this.csMemberService = csMemberService;
	}
	
}
