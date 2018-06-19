package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsPresentDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsPresent;
import com.ccclubs.service.admin.ICsPresentService;
import com.lazy3q.web.helper.$;

/**
 * 礼品赠品的Service实现
 * @author 飞啊飘啊
 */
public class CsPresentService implements ICsPresentService
{
	ICsPresentDao csPresentDao;
	

	/**
	 * 获取所有礼品赠品
	 * @return
	 */
	public List<CsPresent> getCsPresentList(Map params,Integer size)
	{
		return csPresentDao.getCsPresentList(params,size);
	}
	
	/**
	 * 获取礼品赠品统计
	 * @return
	 */
	public List getCsPresentStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csPresentDao.getCsPresentStats(params,groups,sums);
	}
	
	/**
	 * 获取礼品赠品总数
	 * @return
	 */
	public Long getCsPresentCount(Map params)
	{
		return csPresentDao.getCsPresentCount(params);
	}
	
	/**
	 * 获取礼品赠品自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsPresentEval(String eval,Map params)
	{
		return csPresentDao.getCsPresentEval(eval,params);
	}

	/**
	 * 获取礼品赠品分页
	 * @return
	 */
	public Page getCsPresentPage(int page,int size,Map params)
	{
		return csPresentDao.getCsPresentPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取礼品赠品
	 * @param params
	 * @return
	 */
	public CsPresent getCsPresent(Map params)
	{
		return csPresentDao.getCsPresent(params);
	}

	/**
	 * 根据ID取礼品赠品
	 * @param id
	 * @return
	 */
	public CsPresent getCsPresentById(Long id)
	{
		return csPresentDao.getCsPresentById(id);
	}

	/**
	 * 保存礼品赠品
	 * @param csPresent
	 */
	public CsPresent saveCsPresent(CsPresent csPresent)
	{
		return csPresentDao.saveCsPresent(csPresent);
	}
	
	/**
	 * 更新礼品赠品
	 * @param csPresent
	 */
	public void updateCsPresent(CsPresent csPresent)
	{
		csPresentDao.updateCsPresent(csPresent);
	}
	/**
	 * 更新礼品赠品非空字段
	 * @param csPresent
	 */
	public void updateCsPresent$NotNull(CsPresent csPresent)
	{
		csPresentDao.updateCsPresent$NotNull(csPresent);
	}
	
	/**
	 * 根据ID删除一个礼品赠品
	 * @param id
	 */
	public void deleteCsPresentById(Long id)
	{
		csPresentDao.deleteCsPresentById(id);
	}
	/**
	 * 根据ID逻辑删除一个礼品赠品
	 * @param id
	 */
	public void removeCsPresentById(Long id)
	{
		csPresentDao.removeCsPresentById(id);
	}
	
	/**
	 * 根据条件更新礼品赠品
	 * @param values
	 * @param params
	 */
	public void updateCsPresentByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsPresentBy时无条件");
		csPresentDao.updateCsPresentByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除礼品赠品
	 * @param params
	 */
	public void deleteCsPresentByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsPresentBy时无条件");
		csPresentDao.deleteCsPresentByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsPresentDao getCsPresentDao()
	{
		return csPresentDao;
	}

	public void setCsPresentDao(ICsPresentDao csPresentDao)
	{
		this.csPresentDao = csPresentDao;
	}
}