package com.ccclubs.service.common;

import java.util.List;

import com.ccclubs.exception.MessageException;
import com.ccclubs.model.CsPack;
import com.ccclubs.model.CsUserPack;
import com.lazy3q.web.util.Page;


/**
 * 套餐服务类
 * @author uiu
 *
 */
public interface IPackageService {
	
	
	/**
	 * 获取系统所有套餐
	 * @return
	 */
	public List<CsPack> getSystemPackageList();
	
	
	
	/**
	 * 获取会员已经购买的套餐分页
	 * @param memberId
	 * @param usable - true:只取可用的,false:取全部
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<CsUserPack> getUserPackageList(Long memberId, Boolean usable, int page, int size);
	
	
	/**
	 * 会员购买套餐,返回转换后的会员套餐
	 * @param memberId
	 * @param systemPackageId
	 * @param remark
	 * @return
	 */
	public CsUserPack executeBuyPackage(Long memberId,Long systemPackageId,String remark)throws MessageException;


	/**
	 * 购买套餐
	 * @param loginId
	 * @param cspId
	 * @param string
	 * @param bCopup 是否可以使用现金券
	 */
	public CsUserPack executeBuyPackage(Long loginId, Long cspId, String string,boolean bCanUseCopup)throws MessageException;	
	

}
