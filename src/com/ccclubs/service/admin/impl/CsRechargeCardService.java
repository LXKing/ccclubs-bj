package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsRechargeCardDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRechargeCard;
import com.ccclubs.service.admin.ICsRechargeCardService;
import com.lazy3q.web.helper.$;

/**
 * 充值卡的Service实现
 * @author 飞啊飘啊
 */
public class CsRechargeCardService implements ICsRechargeCardService
{
	ICsRechargeCardDao csRechargeCardDao;
	

	/**
	 * 获取所有充值卡
	 * @return
	 */
	public List<CsRechargeCard> getCsRechargeCardList(Map params,Integer size)
	{
		return csRechargeCardDao.getCsRechargeCardList(params,size);
	}
	
	/**
	 * 获取充值卡统计
	 * @return
	 */
	public List getCsRechargeCardStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csRechargeCardDao.getCsRechargeCardStats(params,groups,sums);
	}
	
	/**
	 * 获取充值卡总数
	 * @return
	 */
	public Long getCsRechargeCardCount(Map params)
	{
		return csRechargeCardDao.getCsRechargeCardCount(params);
	}
	
	/**
	 * 获取充值卡自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRechargeCardEval(String eval,Map params)
	{
		return csRechargeCardDao.getCsRechargeCardEval(eval,params);
	}

	/**
	 * 获取充值卡分页
	 * @return
	 */
	public Page getCsRechargeCardPage(int page,int size,Map params)
	{
		return csRechargeCardDao.getCsRechargeCardPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取充值卡
	 * @param params
	 * @return
	 */
	public CsRechargeCard getCsRechargeCard(Map params)
	{
		return csRechargeCardDao.getCsRechargeCard(params);
	}

	/**
	 * 根据ID取充值卡
	 * @param id
	 * @return
	 */
	public CsRechargeCard getCsRechargeCardById(Long id)
	{
		return csRechargeCardDao.getCsRechargeCardById(id);
	}

	/**
	 * 保存充值卡
	 * @param csRechargeCard
	 */
	public CsRechargeCard saveCsRechargeCard(CsRechargeCard csRechargeCard)
	{
		return csRechargeCardDao.saveCsRechargeCard(csRechargeCard);
	}
	
	/**
	 * 更新充值卡
	 * @param csRechargeCard
	 */
	public void updateCsRechargeCard(CsRechargeCard csRechargeCard)
	{
		csRechargeCardDao.updateCsRechargeCard(csRechargeCard);
	}
	/**
	 * 更新充值卡非空字段
	 * @param csRechargeCard
	 */
	public void updateCsRechargeCard$NotNull(CsRechargeCard csRechargeCard)
	{
		csRechargeCardDao.updateCsRechargeCard$NotNull(csRechargeCard);
	}
	
	/**
	 * 根据ID删除一个充值卡
	 * @param id
	 */
	public void deleteCsRechargeCardById(Long id)
	{
		csRechargeCardDao.deleteCsRechargeCardById(id);
	}
	/**
	 * 根据ID逻辑删除一个充值卡
	 * @param id
	 */
	public void removeCsRechargeCardById(Long id)
	{
		csRechargeCardDao.removeCsRechargeCardById(id);
	}
	
	/**
	 * 根据条件更新充值卡
	 * @param values
	 * @param params
	 */
	public void updateCsRechargeCardByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsRechargeCardBy时无条件");
		csRechargeCardDao.updateCsRechargeCardByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除充值卡
	 * @param params
	 */
	public void deleteCsRechargeCardByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsRechargeCardBy时无条件");
		csRechargeCardDao.deleteCsRechargeCardByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsRechargeCardDao getCsRechargeCardDao()
	{
		return csRechargeCardDao;
	}

	public void setCsRechargeCardDao(ICsRechargeCardDao csRechargeCardDao)
	{
		this.csRechargeCardDao = csRechargeCardDao;
	}
}