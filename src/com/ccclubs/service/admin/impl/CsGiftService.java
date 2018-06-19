package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsGiftDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsGift;
import com.ccclubs.service.admin.ICsGiftService;
import com.lazy3q.web.helper.$;

/**
 * 优惠内容的Service实现
 * @author 飞啊飘啊
 */
public class CsGiftService implements ICsGiftService
{
	ICsGiftDao csGiftDao;
	

	/**
	 * 获取所有优惠内容
	 * @return
	 */
	public List<CsGift> getCsGiftList(Map params,Integer size)
	{
		return csGiftDao.getCsGiftList(params,size);
	}
	
	/**
	 * 获取优惠内容统计
	 * @return
	 */
	public List getCsGiftStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csGiftDao.getCsGiftStats(params,groups,sums);
	}
	
	/**
	 * 获取优惠内容总数
	 * @return
	 */
	public Long getCsGiftCount(Map params)
	{
		return csGiftDao.getCsGiftCount(params);
	}
	
	/**
	 * 获取优惠内容自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsGiftEval(String eval,Map params)
	{
		return csGiftDao.getCsGiftEval(eval,params);
	}

	/**
	 * 获取优惠内容分页
	 * @return
	 */
	public Page getCsGiftPage(int page,int size,Map params)
	{
		return csGiftDao.getCsGiftPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取优惠内容
	 * @param params
	 * @return
	 */
	public CsGift getCsGift(Map params)
	{
		return csGiftDao.getCsGift(params);
	}

	/**
	 * 根据ID取优惠内容
	 * @param id
	 * @return
	 */
	public CsGift getCsGiftById(Long id)
	{
		return csGiftDao.getCsGiftById(id);
	}

	/**
	 * 保存优惠内容
	 * @param csGift
	 */
	public CsGift saveCsGift(CsGift csGift)
	{
		return csGiftDao.saveCsGift(csGift);
	}
	
	/**
	 * 更新优惠内容
	 * @param csGift
	 */
	public void updateCsGift(CsGift csGift)
	{
		csGiftDao.updateCsGift(csGift);
	}
	/**
	 * 更新优惠内容非空字段
	 * @param csGift
	 */
	public void updateCsGift$NotNull(CsGift csGift)
	{
		csGiftDao.updateCsGift$NotNull(csGift);
	}
	
	/**
	 * 根据ID删除一个优惠内容
	 * @param id
	 */
	public void deleteCsGiftById(Long id)
	{
		csGiftDao.deleteCsGiftById(id);
	}
	/**
	 * 根据ID逻辑删除一个优惠内容
	 * @param id
	 */
	public void removeCsGiftById(Long id)
	{
		csGiftDao.removeCsGiftById(id);
	}
	
	/**
	 * 根据条件更新优惠内容
	 * @param values
	 * @param params
	 */
	public void updateCsGiftByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsGiftBy时无条件");
		csGiftDao.updateCsGiftByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除优惠内容
	 * @param params
	 */
	public void deleteCsGiftByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsGiftBy时无条件");
		csGiftDao.deleteCsGiftByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsGiftDao getCsGiftDao()
	{
		return csGiftDao;
	}

	public void setCsGiftDao(ICsGiftDao csGiftDao)
	{
		this.csGiftDao = csGiftDao;
	}
}