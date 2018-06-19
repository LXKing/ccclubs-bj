package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsFeeTypeSetDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsFeeTypeSet;
import com.ccclubs.service.admin.ICsFeeTypeSetService;
import com.lazy3q.web.helper.$;

/**
 * 计费策略的Service实现
 * @author 飞啊飘啊
 */
public class CsFeeTypeSetService implements ICsFeeTypeSetService
{
	ICsFeeTypeSetDao csFeeTypeSetDao;
	

	/**
	 * 获取所有计费策略
	 * @return
	 */
	public List<CsFeeTypeSet> getCsFeeTypeSetList(Map params,Integer size)
	{
		return csFeeTypeSetDao.getCsFeeTypeSetList(params,size);
	}
	
	/**
	 * 获取计费策略统计
	 * @return
	 */
	public List getCsFeeTypeSetStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csFeeTypeSetDao.getCsFeeTypeSetStats(params,groups,sums);
	}
	
	/**
	 * 获取计费策略总数
	 * @return
	 */
	public Long getCsFeeTypeSetCount(Map params)
	{
		return csFeeTypeSetDao.getCsFeeTypeSetCount(params);
	}
	
	/**
	 * 获取计费策略自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsFeeTypeSetEval(String eval,Map params)
	{
		return csFeeTypeSetDao.getCsFeeTypeSetEval(eval,params);
	}

	/**
	 * 获取计费策略分页
	 * @return
	 */
	public Page getCsFeeTypeSetPage(int page,int size,Map params)
	{
		return csFeeTypeSetDao.getCsFeeTypeSetPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取计费策略
	 * @param params
	 * @return
	 */
	public CsFeeTypeSet getCsFeeTypeSet(Map params)
	{
		return csFeeTypeSetDao.getCsFeeTypeSet(params);
	}

	/**
	 * 根据ID取计费策略
	 * @param id
	 * @return
	 */
	public CsFeeTypeSet getCsFeeTypeSetById(Long id)
	{
		return csFeeTypeSetDao.getCsFeeTypeSetById(id);
	}

	/**
	 * 保存计费策略
	 * @param csFeeTypeSet
	 */
	public CsFeeTypeSet saveCsFeeTypeSet(CsFeeTypeSet csFeeTypeSet)
	{
		return csFeeTypeSetDao.saveCsFeeTypeSet(csFeeTypeSet);
	}
	
	/**
	 * 更新计费策略
	 * @param csFeeTypeSet
	 */
	public void updateCsFeeTypeSet(CsFeeTypeSet csFeeTypeSet)
	{
		csFeeTypeSetDao.updateCsFeeTypeSet(csFeeTypeSet);
	}
	/**
	 * 更新计费策略非空字段
	 * @param csFeeTypeSet
	 */
	public void updateCsFeeTypeSet$NotNull(CsFeeTypeSet csFeeTypeSet)
	{
		csFeeTypeSetDao.updateCsFeeTypeSet$NotNull(csFeeTypeSet);
	}
	
	/**
	 * 根据ID删除一个计费策略
	 * @param id
	 */
	public void deleteCsFeeTypeSetById(Long id)
	{
		csFeeTypeSetDao.deleteCsFeeTypeSetById(id);
	}
	/**
	 * 根据ID逻辑删除一个计费策略
	 * @param id
	 */
	public void removeCsFeeTypeSetById(Long id)
	{
		csFeeTypeSetDao.removeCsFeeTypeSetById(id);
	}
	
	/**
	 * 根据条件更新计费策略
	 * @param values
	 * @param params
	 */
	public void updateCsFeeTypeSetByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsFeeTypeSetBy时无条件");
		csFeeTypeSetDao.updateCsFeeTypeSetByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除计费策略
	 * @param params
	 */
	public void deleteCsFeeTypeSetByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsFeeTypeSetBy时无条件");
		csFeeTypeSetDao.deleteCsFeeTypeSetByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsFeeTypeSetDao getCsFeeTypeSetDao()
	{
		return csFeeTypeSetDao;
	}

	public void setCsFeeTypeSetDao(ICsFeeTypeSetDao csFeeTypeSetDao)
	{
		this.csFeeTypeSetDao = csFeeTypeSetDao;
	}
}