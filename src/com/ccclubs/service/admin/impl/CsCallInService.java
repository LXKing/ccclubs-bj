package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsCallInDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCallIn;
import com.ccclubs.service.admin.ICsCallInService;
import com.lazy3q.web.helper.$;

/**
 * 客户来电的Service实现
 * @author 飞啊飘啊
 */
public class CsCallInService implements ICsCallInService
{
	ICsCallInDao csCallInDao;
	

	/**
	 * 获取所有客户来电
	 * @return
	 */
	public List<CsCallIn> getCsCallInList(Map params,Integer size)
	{
		return csCallInDao.getCsCallInList(params,size);
	}
	
	/**
	 * 获取客户来电统计
	 * @return
	 */
	public List getCsCallInStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csCallInDao.getCsCallInStats(params,groups,sums);
	}
	
	/**
	 * 获取客户来电总数
	 * @return
	 */
	public Long getCsCallInCount(Map params)
	{
		return csCallInDao.getCsCallInCount(params);
	}
	
	/**
	 * 获取客户来电自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCallInEval(String eval,Map params)
	{
		return csCallInDao.getCsCallInEval(eval,params);
	}

	/**
	 * 获取客户来电分页
	 * @return
	 */
	public Page getCsCallInPage(int page,int size,Map params)
	{
		return csCallInDao.getCsCallInPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取客户来电
	 * @param params
	 * @return
	 */
	public CsCallIn getCsCallIn(Map params)
	{
		return csCallInDao.getCsCallIn(params);
	}

	/**
	 * 根据ID取客户来电
	 * @param id
	 * @return
	 */
	public CsCallIn getCsCallInById(Long id)
	{
		return csCallInDao.getCsCallInById(id);
	}

	/**
	 * 保存客户来电
	 * @param csCallIn
	 */
	public CsCallIn saveCsCallIn(CsCallIn csCallIn)
	{
		return csCallInDao.saveCsCallIn(csCallIn);
	}
	
	/**
	 * 更新客户来电
	 * @param csCallIn
	 */
	public void updateCsCallIn(CsCallIn csCallIn)
	{
		csCallInDao.updateCsCallIn(csCallIn);
	}
	/**
	 * 更新客户来电非空字段
	 * @param csCallIn
	 */
	public void updateCsCallIn$NotNull(CsCallIn csCallIn)
	{
		csCallInDao.updateCsCallIn$NotNull(csCallIn);
	}
	
	/**
	 * 根据ID删除一个客户来电
	 * @param id
	 */
	public void deleteCsCallInById(Long id)
	{
		csCallInDao.deleteCsCallInById(id);
	}
	/**
	 * 根据ID逻辑删除一个客户来电
	 * @param id
	 */
	public void removeCsCallInById(Long id)
	{
		csCallInDao.removeCsCallInById(id);
	}
	
	/**
	 * 根据条件更新客户来电
	 * @param values
	 * @param params
	 */
	public void updateCsCallInByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsCallInBy时无条件");
		csCallInDao.updateCsCallInByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除客户来电
	 * @param params
	 */
	public void deleteCsCallInByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsCallInBy时无条件");
		csCallInDao.deleteCsCallInByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsCallInDao getCsCallInDao()
	{
		return csCallInDao;
	}

	public void setCsCallInDao(ICsCallInDao csCallInDao)
	{
		this.csCallInDao = csCallInDao;
	}
}