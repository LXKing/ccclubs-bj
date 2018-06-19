package com.ccclubs.action.thread;

import java.util.Date;
import java.util.List;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.service.admin.ICsCoinService;
import com.ccclubs.service.admin.ICsFreeHourService;
import com.ccclubs.service.admin.ICsGiftService;
import com.ccclubs.service.admin.ICsUserPackService;
import com.ccclubs.exception.ErrorException;
import com.ccclubs.model.CsCoin;
import com.ccclubs.model.CsFreeHour;
import com.ccclubs.model.CsGift;
import com.ccclubs.model.CsUserPack;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.Lazy;
import com.lazy3q.web.helper.$;

/**
 * 免费小时，优惠内容过期处理
 * @author uiu
 */
public class OverdueThread extends Thread {
	
	ICsGiftService csGiftService;
	ICsUserPackService csUserPackService;
	ICsFreeHourService csFreeHourService;
	ICsCoinService csCoinService;

	@Override
	public void run() {
		while(true){
			try{
				updateOverdueFreeHour();				
			}catch(Exception ex){
				ex.printStackTrace();
				new ErrorException("THREAD_ERROR","线程处理过期免费小时出错",ex);
			}
			
			try{
				updateOverdueGift();				
			}catch(Exception ex){
				ex.printStackTrace();
				new ErrorException("THREAD_ERROR","线程处理过期优惠项出错",ex);
			}
			
			try{
				updateOverdueUserPack();				
			}catch(Exception ex){
				ex.printStackTrace();
				new ErrorException("THREAD_ERROR","线程处理过期套餐出错",ex);
			}
			
			try{
				updateUseUpUserPack();				
			}catch(Exception ex){
				ex.printStackTrace();
				new ErrorException("THREAD_ERROR","线程处理已经用完的套餐出错",ex);
			}
			
			try {
				Thread.sleep(SYSTEM.HOUR);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * 更新已经用完的会员套餐
	 */
	private void updateUseUpUserPack() {		
		csUserPackService.executeTransaction(new Function(){
			@Override
			public <T> T execute(Object... arg0) {
				//取类型为数量方式的优惠项
				List<CsGift> gifts = csGiftService.getCsGiftList(
						$.add(CsGift.F.csgType, 0)//类型为数量
						.add(CsGift.F.csgStatus, 1)//状态为正常的
						.add("definex","csg_remain=0")//数量为0的
					,-1);
				for(CsGift csGift:gifts){
					//状态改为已用完
					csGiftService.updateCsGiftByConfirm(
							$.add(CsGift.F.csgStatus, 3),
							$.add(CsGift.F.csgId, csGift.getCsgId()).add("confirm", 1)
						);
					//取同一用户套餐的所有优惠项
					List<CsGift> sames = csGiftService.getCsGiftList(
							$.add(CsGift.F.csgUserPack, csGift.getCsgUserPack())
						, -1);
					Boolean bUseUp = true;
					for(CsGift gift:sames){
						if(gift.getCsgStatus().shortValue()!=1)
							continue;
						if(gift.getCsgType().shortValue()!=0){//如果优惠方式为折扣或价格方式
							bUseUp = false;
							break;
						}
						if(gift.getCsgRemain()>0){//数量方式，但没用完
							bUseUp = false;
							break;
						}
					}
					if(bUseUp.booleanValue()==true){
						csUserPackService.updateCsUserPackByConfirm(//更新为已用完
							$.add(CsUserPack.F.csupStatus, 3)
								, $.add(CsUserPack.F.csupId, csGift.getCsgUserPack()).add("confirm", 1)
							);
					}
				}				
				return null;
			}
		});
		
	}

	
	
	/**
	 * 更新已经过期的会员套餐
	 */
	private void updateOverdueUserPack() {
		csUserPackService.updateCsUserPackByConfirm(
				Lazy.add("csupStatus", 2), 
				Lazy.add("csupUntilTimeEnd", new Date()).add("confirm", 1)
			);		
	}

	/**
	 * 更新已经过期的会员优惠内容
	 */
	private void updateOverdueGift() {
		csGiftService.updateCsGiftByConfirm(
			Lazy.add("csgStatus", 2), 
			Lazy.add("csgUntilTimeEnd", new Date()).add("confirm", 1)
		);
	}

	/**
	 * 更新已经过期的或已用完免费小时
	 */
	private void updateOverdueFreeHour() {
		//更新已过期的
		csFreeHourService.updateCsFreeHourByConfirm(
			Lazy.add(CsFreeHour.F.csfhStatus, 2), 
			Lazy.add(CsFreeHour.F.csfhEndEnd, new Date()).add("confirm", 1)
		);	
		//更新已经用完的
		/*csFreeHourService.updateCsFreeHourByConfirm(
			Lazy.add(CsFreeHour.F.csfhStatus, 3), 
			Lazy.add(CsFreeHour.F.csfhStatus,1)
			.add(CsFreeHour.F.csfhRemain,0)
			.add("confirm", 1)
		);*/
	}
	
	
	/**
	 * 更新已经过期的或已用完红包
	 */
	private void updateOverdueCoin() {
		//更新已过期的
		csCoinService.updateCsCoinByConfirm(
			Lazy.add(CsCoin.F.cscStatus, 2), 
			Lazy.add(CsCoin.F.cscEndEnd, new Date()).add("confirm", 1)
		);	
		//更新已经用完的
		/*csCoinService.updateCsCoinByConfirm(
			Lazy.add(CsCoin.F.cscStatus, 3), 
			Lazy.add(CsCoin.F.cscStatus,1)
			.add(CsCoin.F.cscRemain,0)
			.add("confirm", 1)
		);*/
	}
	

	public ICsGiftService getCsGiftService() {
		return csGiftService;
	}

	public void setCsGiftService(ICsGiftService csGiftService) {
		this.csGiftService = csGiftService;
	}

	public ICsFreeHourService getCsFreeHourService() {
		return csFreeHourService;
	}

	public void setCsFreeHourService(ICsFreeHourService csFreeHourService) {
		this.csFreeHourService = csFreeHourService;
	}

	public ICsUserPackService getCsUserPackService() {
		return csUserPackService;
	}

	public void setCsUserPackService(ICsUserPackService csUserPackService) {
		this.csUserPackService = csUserPackService;
	}


	public ICsCoinService getCsCoinService() {
		return csCoinService;
	}


	public void setCsCoinService(ICsCoinService csCoinService) {
		this.csCoinService = csCoinService;
	}
	
}
