package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsMarketIndexDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMarketIndex;
import com.ccclubs.service.admin.ICsMarketIndexService;
import com.lazy3q.web.helper.$;

/**
 * 营销指标的Service实现
 * @author 飞啊飘啊
 */
public class CsMarketIndexService implements ICsMarketIndexService
{
	ICsMarketIndexDao csMarketIndexDao;
	

	/**
	 * 获取所有营销指标
	 * @return
	 */
	public List<CsMarketIndex> getCsMarketIndexList(Map params,Integer size)
	{
		return csMarketIndexDao.getCsMarketIndexList(params,size);
	}
	
	/**
	 * 获取营销指标统计
	 * @return
	 */
	public List getCsMarketIndexStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csMarketIndexDao.getCsMarketIndexStats(params,groups,sums);
	}
	
	/**
	 * 获取营销指标总数
	 * @return
	 */
	public Long getCsMarketIndexCount(Map params)
	{
		return csMarketIndexDao.getCsMarketIndexCount(params);
	}
	
	/**
	 * 获取营销指标自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMarketIndexEval(String eval,Map params)
	{
		return csMarketIndexDao.getCsMarketIndexEval(eval,params);
	}

	/**
	 * 获取营销指标分页
	 * @return
	 */
	public Page getCsMarketIndexPage(int page,int size,Map params)
	{
		return csMarketIndexDao.getCsMarketIndexPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取营销指标
	 * @param params
	 * @return
	 */
	public CsMarketIndex getCsMarketIndex(Map params)
	{
		return csMarketIndexDao.getCsMarketIndex(params);
	}

	/**
	 * 根据ID取营销指标
	 * @param id
	 * @return
	 */
	public CsMarketIndex getCsMarketIndexById(Long id)
	{
		return csMarketIndexDao.getCsMarketIndexById(id);
	}

	/**
	 * 保存营销指标
	 * @param csMarketIndex
	 */
	public CsMarketIndex saveCsMarketIndex(CsMarketIndex csMarketIndex)
	{
		return csMarketIndexDao.saveCsMarketIndex(csMarketIndex);
	}
	
	/**
	 * 更新营销指标
	 * @param csMarketIndex
	 */
	public void updateCsMarketIndex(CsMarketIndex csMarketIndex)
	{
		csMarketIndexDao.updateCsMarketIndex(csMarketIndex);
	}
	/**
	 * 更新营销指标非空字段
	 * @param csMarketIndex
	 */
	public void updateCsMarketIndex$NotNull(CsMarketIndex csMarketIndex)
	{
		csMarketIndexDao.updateCsMarketIndex$NotNull(csMarketIndex);
	}
	
	/**
	 * 根据ID删除一个营销指标
	 * @param id
	 */
	public void deleteCsMarketIndexById(Long id)
	{
		csMarketIndexDao.deleteCsMarketIndexById(id);
	}
	/**
	 * 根据ID逻辑删除一个营销指标
	 * @param id
	 */
	public void removeCsMarketIndexById(Long id)
	{
		csMarketIndexDao.removeCsMarketIndexById(id);
	}
	
	/**
	 * 根据条件更新营销指标
	 * @param values
	 * @param params
	 */
	public void updateCsMarketIndexByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsMarketIndexBy时无条件");
		csMarketIndexDao.updateCsMarketIndexByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除营销指标
	 * @param params
	 */
	public void deleteCsMarketIndexByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsMarketIndexBy时无条件");
		csMarketIndexDao.deleteCsMarketIndexByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsMarketIndexDao getCsMarketIndexDao()
	{
		return csMarketIndexDao;
	}

	public void setCsMarketIndexDao(ICsMarketIndexDao csMarketIndexDao)
	{
		this.csMarketIndexDao = csMarketIndexDao;
	}
}