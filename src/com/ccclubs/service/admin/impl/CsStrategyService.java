package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsStrategyDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsStrategy;
import com.ccclubs.service.admin.ICsStrategyService;
import com.lazy3q.web.helper.$;

/**
 * 品牌战略的Service实现
 * @author 飞啊飘啊
 */
public class CsStrategyService implements ICsStrategyService
{
	ICsStrategyDao csStrategyDao;
	

	/**
	 * 获取所有品牌战略
	 * @return
	 */
	public List<CsStrategy> getCsStrategyList(Map params,Integer size)
	{
		return csStrategyDao.getCsStrategyList(params,size);
	}
	
	/**
	 * 获取品牌战略统计
	 * @return
	 */
	public List getCsStrategyStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csStrategyDao.getCsStrategyStats(params,groups,sums);
	}
	
	/**
	 * 获取品牌战略总数
	 * @return
	 */
	public Long getCsStrategyCount(Map params)
	{
		return csStrategyDao.getCsStrategyCount(params);
	}
	
	/**
	 * 获取品牌战略自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsStrategyEval(String eval,Map params)
	{
		return csStrategyDao.getCsStrategyEval(eval,params);
	}

	/**
	 * 获取品牌战略分页
	 * @return
	 */
	public Page getCsStrategyPage(int page,int size,Map params)
	{
		return csStrategyDao.getCsStrategyPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取品牌战略
	 * @param params
	 * @return
	 */
	public CsStrategy getCsStrategy(Map params)
	{
		return csStrategyDao.getCsStrategy(params);
	}

	/**
	 * 根据ID取品牌战略
	 * @param id
	 * @return
	 */
	public CsStrategy getCsStrategyById(Long id)
	{
		return csStrategyDao.getCsStrategyById(id);
	}

	/**
	 * 保存品牌战略
	 * @param csStrategy
	 */
	public CsStrategy saveCsStrategy(CsStrategy csStrategy)
	{
		return csStrategyDao.saveCsStrategy(csStrategy);
	}
	
	/**
	 * 更新品牌战略
	 * @param csStrategy
	 */
	public void updateCsStrategy(CsStrategy csStrategy)
	{
		csStrategyDao.updateCsStrategy(csStrategy);
	}
	/**
	 * 更新品牌战略非空字段
	 * @param csStrategy
	 */
	public void updateCsStrategy$NotNull(CsStrategy csStrategy)
	{
		csStrategyDao.updateCsStrategy$NotNull(csStrategy);
	}
	
	/**
	 * 根据ID删除一个品牌战略
	 * @param id
	 */
	public void deleteCsStrategyById(Long id)
	{
		csStrategyDao.deleteCsStrategyById(id);
	}
	/**
	 * 根据ID逻辑删除一个品牌战略
	 * @param id
	 */
	public void removeCsStrategyById(Long id)
	{
		csStrategyDao.removeCsStrategyById(id);
	}
	
	/**
	 * 根据条件更新品牌战略
	 * @param values
	 * @param params
	 */
	public void updateCsStrategyByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsStrategyBy时无条件");
		csStrategyDao.updateCsStrategyByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除品牌战略
	 * @param params
	 */
	public void deleteCsStrategyByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsStrategyBy时无条件");
		csStrategyDao.deleteCsStrategyByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsStrategyDao getCsStrategyDao()
	{
		return csStrategyDao;
	}

	public void setCsStrategyDao(ICsStrategyDao csStrategyDao)
	{
		this.csStrategyDao = csStrategyDao;
	}
}