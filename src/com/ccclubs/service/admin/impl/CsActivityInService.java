package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsActivityInDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsActivityIn;
import com.ccclubs.service.admin.ICsActivityInService;
import com.lazy3q.web.helper.$;

/**
 * 活动参与的Service实现
 * @author 飞啊飘啊
 */
public class CsActivityInService implements ICsActivityInService
{
	ICsActivityInDao csActivityInDao;
	

	/**
	 * 获取所有活动参与
	 * @return
	 */
	public List<CsActivityIn> getCsActivityInList(Map params,Integer size)
	{
		return csActivityInDao.getCsActivityInList(params,size);
	}
	
	/**
	 * 获取活动参与统计
	 * @return
	 */
	public List getCsActivityInStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csActivityInDao.getCsActivityInStats(params,groups,sums);
	}
	
	/**
	 * 获取活动参与总数
	 * @return
	 */
	public Long getCsActivityInCount(Map params)
	{
		return csActivityInDao.getCsActivityInCount(params);
	}
	
	/**
	 * 获取活动参与自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsActivityInEval(String eval,Map params)
	{
		return csActivityInDao.getCsActivityInEval(eval,params);
	}

	/**
	 * 获取活动参与分页
	 * @return
	 */
	public Page getCsActivityInPage(int page,int size,Map params)
	{
		return csActivityInDao.getCsActivityInPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取活动参与
	 * @param params
	 * @return
	 */
	public CsActivityIn getCsActivityIn(Map params)
	{
		return csActivityInDao.getCsActivityIn(params);
	}

	/**
	 * 根据ID取活动参与
	 * @param id
	 * @return
	 */
	public CsActivityIn getCsActivityInById(Long id)
	{
		return csActivityInDao.getCsActivityInById(id);
	}

	/**
	 * 保存活动参与
	 * @param csActivityIn
	 */
	public CsActivityIn saveCsActivityIn(CsActivityIn csActivityIn)
	{
		return csActivityInDao.saveCsActivityIn(csActivityIn);
	}
	
	/**
	 * 更新活动参与
	 * @param csActivityIn
	 */
	public void updateCsActivityIn(CsActivityIn csActivityIn)
	{
		csActivityInDao.updateCsActivityIn(csActivityIn);
	}
	/**
	 * 更新活动参与非空字段
	 * @param csActivityIn
	 */
	public void updateCsActivityIn$NotNull(CsActivityIn csActivityIn)
	{
		csActivityInDao.updateCsActivityIn$NotNull(csActivityIn);
	}
	
	/**
	 * 根据ID删除一个活动参与
	 * @param id
	 */
	public void deleteCsActivityInById(Long id)
	{
		csActivityInDao.deleteCsActivityInById(id);
	}
	/**
	 * 根据ID逻辑删除一个活动参与
	 * @param id
	 */
	public void removeCsActivityInById(Long id)
	{
		csActivityInDao.removeCsActivityInById(id);
	}
	
	/**
	 * 根据条件更新活动参与
	 * @param values
	 * @param params
	 */
	public void updateCsActivityInByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsActivityInBy时无条件");
		csActivityInDao.updateCsActivityInByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除活动参与
	 * @param params
	 */
	public void deleteCsActivityInByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsActivityInBy时无条件");
		csActivityInDao.deleteCsActivityInByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsActivityInDao getCsActivityInDao()
	{
		return csActivityInDao;
	}

	public void setCsActivityInDao(ICsActivityInDao csActivityInDao)
	{
		this.csActivityInDao = csActivityInDao;
	}
}