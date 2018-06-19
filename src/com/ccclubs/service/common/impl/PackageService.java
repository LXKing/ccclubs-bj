package com.ccclubs.service.common.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ccclubs.config.SYSTEM;
import com.ccclubs.config.SYSTEM.RecordType;
import com.ccclubs.dao.ICsMemberDao;
import com.ccclubs.exception.ErrorCode;
import com.ccclubs.exception.ErrorException;
import com.ccclubs.exception.MessageException;
import com.ccclubs.helper.LoginHelper;
import com.ccclubs.model.CsGift;
import com.ccclubs.model.CsMember;
import com.ccclubs.model.CsPack;
import com.ccclubs.model.CsPackInfo;
import com.ccclubs.model.CsUserPack;
import com.ccclubs.service.admin.ICsGiftService;
import com.ccclubs.service.admin.ICsPackInfoService;
import com.ccclubs.service.admin.ICsPackService;
import com.ccclubs.service.admin.ICsUserPackService;
import com.ccclubs.service.common.ICommonMoneyService;
import com.ccclubs.service.common.IPackageService;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.helper.WebHelper.LzMap;
import com.lazy3q.web.util.Page;

public class PackageService implements IPackageService {

	ICsPackService csPackService;
	ICsPackInfoService csPackInfoService;
	ICsUserPackService csUserPackService;
	ICommonMoneyService commonMoneyService;
	ICsMemberDao csMemberDao;
	ICsGiftService csGiftService;

	/**
	 * 会员购买套餐,返回转换后的会员套餐
	 * 
	 * @param memberId
	 * @param systemPackageId
	 * @param copup 是否可以使用现金券
	 * @return
	 * @throws MessageException 
	 */
	public CsUserPack executeBuyPackage(Long memberId, Long systemPackageId, String remark,boolean bCanUseCopup) throws MessageException {
		CsMember csMember = csMemberDao.getCsMemberById(memberId);
		CsPack systemPackage = csPackService.getCsPackById(systemPackageId);
		if (systemPackage.getCspStatus().shortValue() != 1) {
			throw new MessageException(ErrorCode.PACKAGE_CANOT_BUY,"当前套餐不能购买");
		}

		Double canMoney = bCanUseCopup?(csMember.getCsmCoupon()+csMember.getCsmMoney()):csMember.getCsmMoney();
		// 判断会员的余额是否可用
		if (canMoney < systemPackage.getCspPrice())
			throw new MessageException(ErrorCode.MONEY_LESS,"会员可用余额不足");

		CsUserPack existUserPack = csUserPackService.getCsUserPack(
			$.add(CsUserPack.F.csupMember, memberId)
			.add(CsUserPack.F.csupStatus, 1)
			.add(CsUserPack.F.csupPack, systemPackageId)
		);
		if (existUserPack != null)
			throw new MessageException(ErrorCode.PACKAGE_CANOT_REPEAT,"您已经购买了该套餐");

		// 添加套餐信息到会员帐户体系
		CsUserPack csUserPack = new CsUserPack();
		csUserPack.setCsupHost(csMember.getCsmHost());
		csUserPack.setCsupAddTime(new Date());
		csUserPack.setCsupMember(memberId);
		csUserPack.setCsupPack(systemPackage.getCspId());
		csUserPack.setCsupRemark(remark);
		csUserPack.setCsupState((short) 0);// 不续订
		csUserPack.setCsupStatus((short) 1);// 有效
		csUserPack.setCsupUpdateTime(new Date());
		// 取套餐的有效期
		Integer dayCount = 0;
		switch (systemPackage.getCspValidity().shortValue()) {// 0:不限 1:一月
																// 2:一季 3:半年
																// 4:一年 5:天数
		case 0:// 不限
			dayCount = 65535;
			break;
		case 1:// 不限
			dayCount = 31;
			break;
		case 2:// 不限
			dayCount = 31 * 3;
			break;
		case 3:// 不限
			dayCount = 365 / 2;
			break;
		case 4:// 不限
			dayCount = 365;
			break;
		case 5:// 不限
			dayCount = systemPackage.getCspDay();
			break;
		}
		if (dayCount == null || dayCount < 1) {
			new ErrorException("SYSTEM_PACKAGE_ERROR", "系统套餐配置错误", "系统套餐配置错误，可能是套餐类型为天数时，而天数没配置或太小");
			throw new MessageException(ErrorCode.SYSTEM_ERROR,"系统繁忙，请稍候再试");
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, dayCount);
		csUserPack.setCsupUntilTime(calendar.getTime());
		// 保存会员套餐到数据库
		csUserPack = csUserPackService.saveCsUserPack(csUserPack);

		// 把当前系统套餐的配置克隆到会员的优惠项中
		List<CsPackInfo> infos = csPackInfoService.getCsPackInfoList(
				$.add(CsPackInfo.F.cspiPack, systemPackage.getCspId())
				.add("asc", CsPackInfo.C.cspiId)
			, -1);
		for (CsPackInfo csPackInfo : infos) {
			CsGift csGift = new CsGift();
			csGift.csgMember(memberId);
			csGift.setCsgHost(csMember.getCsmHost());
			csGift.setCsgName(csPackInfo.getCspiName());
			csGift.csgSysPack(systemPackage.getCspId());
			csGift.csgUserPack(csUserPack.getCsupId());
			csGift.csgGoods(csPackInfo.getCspiGoods());
			csGift.csgOutlets(csPackInfo.getCspiOutlets());
			csGift.csgModel(csPackInfo.getCspiModel());
			csGift.csgType(csPackInfo.getCspiType());
			if (csPackInfo.getCspiType().shortValue() == 0) {
				csGift.csgCount(csPackInfo.getCspiValue());
				csGift.csgRemain(csPackInfo.getCspiValue());
			} else if (csPackInfo.getCspiType().shortValue() == 1) {
				csGift.csgPrice(csPackInfo.getCspiValue());
			} else if (csPackInfo.getCspiType().shortValue() == 2) {
				csGift.csgRebate(csPackInfo.getCspiValue());
			}
			csGift.setCsgScript(csPackInfo.getCspiScript());
			csGift.csgUntilTime(csUserPack.getCsupUntilTime());
			csGift.csgAddTime(new Date());
			csGift.csgUpdateTime(new Date());
			csGift.csgStatus((short) 1);
			
			if($.empty(csGift.getCsgName())){
				csGift.setCsgName(csGift.get$csgGoods().getCsgName()+"优惠");
			}
			
			csGiftService.saveCsGift(csGift);
		}

		
		if(bCanUseCopup){
			Double payCoupon = $(Math.min(csMember.getCsmCoupon(), systemPackage.getCspPrice()));// 现金券支付
			if (payCoupon < 0)
				payCoupon = 0d;
			Double payMoney = $(systemPackage.getCspPrice() - payCoupon);// 余额支付数
	
			if (payCoupon > 0) {// 现金券支付
				commonMoneyService.updateMoney(csMember.getCsmId(), ICommonMoneyService.MoneyType.Coupon, -payCoupon, SYSTEM.RecordType.购买套餐, remark, null, csUserPack.getCsupId(),CsUserPack.class);
			}
			commonMoneyService.updateMoney(csMember.getCsmId(), ICommonMoneyService.MoneyType.Money, -payMoney, SYSTEM.RecordType.购买套餐, remark, null, csUserPack.getCsupId(),CsUserPack.class);
		}else{
			Double payMoney = $(systemPackage.getCspPrice());// 余额支付数
			commonMoneyService.updateMoney(csMember.getCsmId(), ICommonMoneyService.MoneyType.Money, -payMoney, SYSTEM.RecordType.购买套餐, remark, null, csUserPack.getCsupId(),CsUserPack.class);
		}

		return csUserPack;
	}
	/**
	 * 会员购买套餐,返回转换后的会员套餐
	 * 
	 * @param memberId
	 * @param systemPackageId
	 * @return
	 */
	public CsUserPack executeBuyPackage(Long memberId, Long systemPackageId, String remark)throws MessageException {
		return this.executeBuyPackage(memberId, systemPackageId, remark, true);		
	}

	/**
	 * 获取系统所有套餐
	 * 
	 * @return
	 */
	public List<CsPack> getSystemPackageList() {
		return csPackService.getCsPackList($.add("cspStatus", 1), -1);
	}

	/**
	 * 获取会员已经购买的套餐分页
	 * 
	 * @param memberId
	 * @param usable -
	 *            true:只取可用的,false:取全部
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<CsUserPack> getUserPackageList(Long memberId, Boolean usable, int page, int size) {
		LzMap params = $.add("csupMember", memberId);
		if (usable == true) {
			params.add("csupStatus", 1);
		} else {
			params.add("cspStatusNot", "not");
			params.add("csupStatus", 0);
		}
		return csUserPackService.getCsUserPackPage(page, size, params);
	}

	public Double $(Double value) {
		return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
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

	public ICommonMoneyService getCommonMoneyService() {
		return commonMoneyService;
	}

	public void setCommonMoneyService(ICommonMoneyService commonMoneyService) {
		this.commonMoneyService = commonMoneyService;
	}

	public ICsMemberDao getCsMemberDao() {
		return csMemberDao;
	}

	public void setCsMemberDao(ICsMemberDao csMemberDao) {
		this.csMemberDao = csMemberDao;
	}

	public ICsGiftService getCsGiftService() {
		return csGiftService;
	}

	public void setCsGiftService(ICsGiftService csGiftService) {
		this.csGiftService = csGiftService;
	}

	
}
