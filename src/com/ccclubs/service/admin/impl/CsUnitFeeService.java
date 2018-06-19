package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsUnitFeeDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnitFee;
import com.ccclubs.service.admin.ICsUnitFeeService;
import com.lazy3q.web.helper.$;

/**
 * 价格配置的Service实现
 * @author 飞啊飘啊
 */
public class CsUnitFeeService implements ICsUnitFeeService
{
	ICsUnitFeeDao csUnitFeeDao;
	

	/**
	 * 获取所有价格配置
	 * @return
	 */
	public List<CsUnitFee> getCsUnitFeeList(Map params,Integer size)
	{
		return csUnitFeeDao.getCsUnitFeeList(params,size);
	}
	
	/**
	 * 获取价格配置统计
	 * @return
	 */
	public List getCsUnitFeeStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csUnitFeeDao.getCsUnitFeeStats(params,groups,sums);
	}
	
	/**
	 * 获取价格配置总数
	 * @return
	 */
	public Long getCsUnitFeeCount(Map params)
	{
		return csUnitFeeDao.getCsUnitFeeCount(params);
	}
	
	/**
	 * 获取价格配置自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUnitFeeEval(String eval,Map params)
	{
		return csUnitFeeDao.getCsUnitFeeEval(eval,params);
	}

	/**
	 * 获取价格配置分页
	 * @return
	 */
	public Page getCsUnitFeePage(int page,int size,Map params)
	{
		return csUnitFeeDao.getCsUnitFeePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取价格配置
	 * @param params
	 * @return
	 */
	public CsUnitFee getCsUnitFee(Map params)
	{
		return csUnitFeeDao.getCsUnitFee(params);
	}

	/**
	 * 根据ID取价格配置
	 * @param id
	 * @return
	 */
	public CsUnitFee getCsUnitFeeById(Long id)
	{
		return csUnitFeeDao.getCsUnitFeeById(id);
	}

	/**
	 * 保存价格配置
	 * @param csUnitFee
	 */
	public CsUnitFee saveCsUnitFee(CsUnitFee csUnitFee)
	{
		return csUnitFeeDao.saveCsUnitFee(csUnitFee);
	}
	
	/**
	 * 更新价格配置
	 * @param csUnitFee
	 */
	public void updateCsUnitFee(CsUnitFee csUnitFee)
	{
		csUnitFeeDao.updateCsUnitFee(csUnitFee);
	}
	/**
	 * 更新价格配置非空字段
	 * @param csUnitFee
	 */
	public void updateCsUnitFee$NotNull(CsUnitFee csUnitFee)
	{
		csUnitFeeDao.updateCsUnitFee$NotNull(csUnitFee);
	}
	
	/**
	 * 根据ID删除一个价格配置
	 * @param id
	 */
	public void deleteCsUnitFeeById(Long id)
	{
		csUnitFeeDao.deleteCsUnitFeeById(id);
	}
	
	/**
	 * 根据条件更新价格配置
	 * @param values
	 * @param params
	 */
	public void updateCsUnitFeeByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsUnitFeeBy时无条件");
		csUnitFeeDao.updateCsUnitFeeByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除价格配置
	 * @param params
	 */
	public void deleteCsUnitFeeByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsUnitFeeBy时无条件");
		csUnitFeeDao.deleteCsUnitFeeByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsUnitFeeDao getCsUnitFeeDao()
	{
		return csUnitFeeDao;
	}

	public void setCsUnitFeeDao(ICsUnitFeeDao csUnitFeeDao)
	{
		this.csUnitFeeDao = csUnitFeeDao;
	}
}