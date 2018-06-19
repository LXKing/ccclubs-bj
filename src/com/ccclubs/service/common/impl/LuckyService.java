package com.ccclubs.service.common.impl;

import java.util.Date;
import java.util.List;

import com.ccclubs.config.SYSTEM.IntegralType;
import com.ccclubs.config.SYSTEM.RecordType;
import com.ccclubs.dao.ICsMemberDao;
import com.ccclubs.model.CsFreeHour;
import com.ccclubs.model.CsLucky;
import com.ccclubs.model.CsLuckyItem;
import com.ccclubs.model.CsLuckyResult;
import com.ccclubs.model.CsMember;
import com.ccclubs.dao.ICsLuckyItemDao;
import com.ccclubs.dao.ICsLuckyResultDao;
import com.ccclubs.dao.ICsLuckyDao;
import com.ccclubs.exception.ErrorException;
import com.ccclubs.exception.MessageException;
import com.ccclubs.service.admin.ICsFreeHourService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.ILuckyService;
import com.ccclubs.service.common.ICommonMoneyService.MoneyType;
import com.lazy3q.web.helper.$;

public class LuckyService implements ILuckyService {
	
	ICsLuckyDao csLuckyDao;
	ICsLuckyItemDao csLuckyItemDao;
	ICsLuckyResultDao csLuckyResultDao;
	ICsMemberDao csMemberDao;
	ICommonMoneyService commonMoneyService;
	ICsFreeHourService csFreeHourService;

	/**
	 * 抽奖 
	 * @param member
	 * @param from 抽奖来源 0:网站 1:微信 2:app 3:其它  
	 * @param 错误代码 - 100到200之间为系统异常 201:未登录  202:活动已结束  203:积分不够  204:奖池奖品已经抽完
	 * @return
	 */
	public CsLuckyResult executeLucky(LuckyType luckyType, Long member,Short from)throws MessageException {		
		CsMember csMember = csMemberDao.getCsMemberById(member);
		if(csMember==null){
			throw new MessageException("对不起，请先登录后再抽奖",201);
		}		
				
		CsLucky csLucky = this.getCsLucky(luckyType);
		if(csLucky==null){
			new ErrorException("LUCK_ERROR","未配置抽奖活动","未配置抽奖活动["+luckyType.name()+"]");
			throw new MessageException("系统错误，请稍候再试",101);
		}
				
		if(csLucky.getCslFinish().before(new Date())){
			throw new MessageException("对不起，抽奖活动已结束",202);
		}
		
		List<CsLuckyItem> luckyItems = csLuckyItemDao.getCsLuckyItemList(
				$.add(CsLuckyItem.F.csliLuckyId, csLucky.getCslId())
				.add(CsLuckyItem.F.csliStatus, 1)
		,-1);
		if(luckyItems.isEmpty()){
			new ErrorException("LUCK_ERROR","未配置抽奖奖项","未配置抽奖活动["+luckyType.name()+"]的奖项");
			throw new MessageException("系统错误，请稍候再试",102);
		}
		
		if(csMember.getCsmIntegral()<csLucky.getCslPrice()){
			throw new MessageException("对不起，积分不够，不能参与抽奖",203);
		}
		
		//把所有的奖项的数量（）求和，取一随机数，得到的就是会员抽中的奖项
		Integer allCount = 0;
		for(CsLuckyItem csLuckyItem:luckyItems){
			//在奖项数量上乘以中奖系数
			Integer count = new Double(csLuckyItem.getCsliRemain() * csLuckyItem.getCsliRatio()).intValue();
			if(csLuckyItem.getCsliRemain().intValue()==0){
				csLuckyItem.getValues().put("MIN", -1);//奖项中奖开始序列
				csLuckyItem.getValues().put("MAX", -1);//奖项中奖结束序列
			}else{
				csLuckyItem.getValues().put("MIN", allCount+1);//奖项中奖开始序列
				allCount+=count;
				csLuckyItem.getValues().put("MAX", allCount);//奖项中奖结束序列
			}
		}
		if(allCount<1){
			throw new MessageException("奖池奖品已经抽完",204);
		}
		
		Integer random = $.rand(allCount);//取一随机数
		CsLuckyItem luckyItemed = null;//会员所抽中的奖项
		for(CsLuckyItem csLuckyItem:luckyItems){
			Integer MIN = (Integer) csLuckyItem.getValues().get("MIN");
			Integer MAX = (Integer) csLuckyItem.getValues().get("MAX");
			if(random>=MIN && random<=MAX){
				luckyItemed = csLuckyItem;
				break;
			}
		}
				
		//所中奖项余量减一
		csLuckyItemDao.updateCsLuckyItemByConfirm(
				$.add("definex", "csli_remain=csli_remain-1"),
				$.add(CsLuckyItem.F.csliId, luckyItemed.getCsliId()).add("confirm", 1)
			);
		
		//中奖结果
		CsLuckyResult csLuckyResult = new CsLuckyResult();
		csLuckyResult.setCslrAddTime(new Date());
		csLuckyResult.setCslrAmount(luckyItemed.getCsliAmount());
		csLuckyResult.setCslrFrom(from);
		csLuckyResult.setCslrItemIndex(luckyItemed.getCsliIndex());
		csLuckyResult.setCslrItemType(luckyItemed.getCsliType());
		csLuckyResult.setCslrLucky(csLucky.getCslId());
		csLuckyResult.setCslrLuckyItem(luckyItemed.getCsliId());
		csLuckyResult.setCslrMember(member);
		csLuckyResult.setCslrName(csLucky.getCslName()+" - 中奖 -"+luckyItemed.getCsliName());
		csLuckyResult.setCslrRemark("");
		csLuckyResult.setCslrUpdateTime(new Date());
		
		short type = luckyItemed.getCsliType().shortValue();
		if(type==1 || type==1 || type==2){
			csLuckyResult.setCslrStatus((short)1);	
			csLuckyResult.setCslrState((short)1);
			csLuckyResult.setCslrIssueTime(new Date());			
			if(type==1){
				commonMoneyService.updateIntegral(
						csMember.getCsmId(), 
						luckyItemed.getCsliAmount().doubleValue(), 
						IntegralType.抽奖得积分,
						"抽奖得积分"+luckyItemed.getCsliAmount(),
						csLuckyResult.getCslrId());
			}else if(type==2){
				commonMoneyService.updateMoney(
						csMember.getCsmId(), 
						MoneyType.Coupon, 
						luckyItemed.getCsliAmount().doubleValue(), 
						RecordType.赠送现金券,
						"抽奖得现金券"+luckyItemed.getCsliAmount(),
						null,
						csLuckyResult.getCslrId(),
						CsLuckyResult.class
					);
			}else if(type==3){				
				CsFreeHour csFreeHour = new CsFreeHour();
				csFreeHour.setCsfhAddTime(new Date());
				csFreeHour.setCsfhCount(luckyItemed.getCsliAmount().doubleValue());
				csFreeHour.setCsfhEditor(0l);
				csFreeHour.setCsfhValidity((short)3);
				csFreeHour.setCsfhEnd(com.ccclubs.helper.SystemHelper.getValidUntil(csFreeHour.getCsfhValidity()));
				csFreeHour.setCsfhMember(csMember.getCsmId());
				csFreeHour.setCsfhRemain(luckyItemed.getCsliAmount().doubleValue());
				csFreeHour.setCsfhRemark("抽奖得免费小时");
				csFreeHour.setCsfhStatus((short)1);
				csFreeHour.setCsfhUpdateTime(new Date());				
				csFreeHourService.saveCsFreeHour(csFreeHour);
			}			
		}else{
			csLuckyResult.setCslrStatus((short)0);	
			csLuckyResult.setCslrState((short)0);
		}
		
		//保存中奖
		csLuckyResult = csLuckyResultDao.saveCsLuckyResult(csLuckyResult);
		
		commonMoneyService.updateIntegral(
				csMember.getCsmId(), 
				csLucky.getCslPrice(), 
				IntegralType.抽奖花费积分,
				"抽奖花费积分"+csLucky.getCslPrice(),
				csLuckyResult.getCslrId());
		
		return csLuckyResult;
	}
		
	
	

	/**
	 * 获取抽奖活动对象
	 * @param luckyType
	 * @return
	 */
	public CsLucky getCsLucky(LuckyType luckyType) {		
		return csLuckyDao.getCsLucky(
			$.add(CsLucky.F.cslFlag,luckyType.name())
		);
	}
	

	public ICsLuckyDao getCsLuckyDao() {
		return csLuckyDao;
	}

	public void setCsLuckyDao(ICsLuckyDao csLuckyDao) {
		this.csLuckyDao = csLuckyDao;
	}

	public ICsLuckyItemDao getCsLuckyItemDao() {
		return csLuckyItemDao;
	}

	public void setCsLuckyItemDao(ICsLuckyItemDao csLuckyItemDao) {
		this.csLuckyItemDao = csLuckyItemDao;
	}

	public ICsLuckyResultDao getCsLuckyResultDao() {
		return csLuckyResultDao;
	}

	public void setCsLuckyResultDao(ICsLuckyResultDao csLuckyResultDao) {
		this.csLuckyResultDao = csLuckyResultDao;
	}




	public ICsMemberDao getCsMemberDao() {
		return csMemberDao;
	}




	public void setCsMemberDao(ICsMemberDao csMemberDao) {
		this.csMemberDao = csMemberDao;
	}




	public ICommonMoneyService getCommonMoneyService() {
		return commonMoneyService;
	}




	public void setCommonMoneyService(ICommonMoneyService commonMoneyService) {
		this.commonMoneyService = commonMoneyService;
	}




	public ICsFreeHourService getCsFreeHourService() {
		return csFreeHourService;
	}




	public void setCsFreeHourService(ICsFreeHourService csFreeHourService) {
		this.csFreeHourService = csFreeHourService;
	}

}
