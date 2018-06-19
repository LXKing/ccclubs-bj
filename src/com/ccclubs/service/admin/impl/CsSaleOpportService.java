package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsSaleOpportDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsSaleOpport;
import com.ccclubs.service.admin.ICsSaleOpportService;
import com.lazy3q.web.helper.$;

/**
 * 销售机会的Service实现
 * @author 飞啊飘啊
 */
public class CsSaleOpportService implements ICsSaleOpportService
{
	ICsSaleOpportDao csSaleOpportDao;
	

	/**
	 * 获取所有销售机会
	 * @return
	 */
	public List<CsSaleOpport> getCsSaleOpportList(Map params,Integer size)
	{
		return csSaleOpportDao.getCsSaleOpportList(params,size);
	}
	
	/**
	 * 获取销售机会统计
	 * @return
	 */
	public List getCsSaleOpportStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csSaleOpportDao.getCsSaleOpportStats(params,groups,sums);
	}
	
	/**
	 * 获取销售机会总数
	 * @return
	 */
	public Long getCsSaleOpportCount(Map params)
	{
		return csSaleOpportDao.getCsSaleOpportCount(params);
	}
	
	/**
	 * 获取销售机会自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsSaleOpportEval(String eval,Map params)
	{
		return csSaleOpportDao.getCsSaleOpportEval(eval,params);
	}

	/**
	 * 获取销售机会分页
	 * @return
	 */
	public Page getCsSaleOpportPage(int page,int size,Map params)
	{
		return csSaleOpportDao.getCsSaleOpportPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取销售机会
	 * @param params
	 * @return
	 */
	public CsSaleOpport getCsSaleOpport(Map params)
	{
		return csSaleOpportDao.getCsSaleOpport(params);
	}

	/**
	 * 根据ID取销售机会
	 * @param id
	 * @return
	 */
	public CsSaleOpport getCsSaleOpportById(Long id)
	{
		return csSaleOpportDao.getCsSaleOpportById(id);
	}

	/**
	 * 保存销售机会
	 * @param csSaleOpport
	 */
	public CsSaleOpport saveCsSaleOpport(CsSaleOpport csSaleOpport)
	{
		return csSaleOpportDao.saveCsSaleOpport(csSaleOpport);
	}
	
	/**
	 * 更新销售机会
	 * @param csSaleOpport
	 */
	public void updateCsSaleOpport(CsSaleOpport csSaleOpport)
	{
		csSaleOpportDao.updateCsSaleOpport(csSaleOpport);
	}
	/**
	 * 更新销售机会非空字段
	 * @param csSaleOpport
	 */
	public void updateCsSaleOpport$NotNull(CsSaleOpport csSaleOpport)
	{
		csSaleOpportDao.updateCsSaleOpport$NotNull(csSaleOpport);
	}
	
	/**
	 * 根据ID删除一个销售机会
	 * @param id
	 */
	public void deleteCsSaleOpportById(Long id)
	{
		csSaleOpportDao.deleteCsSaleOpportById(id);
	}
	/**
	 * 根据ID逻辑删除一个销售机会
	 * @param id
	 */
	public void removeCsSaleOpportById(Long id)
	{
		csSaleOpportDao.removeCsSaleOpportById(id);
	}
	
	/**
	 * 根据条件更新销售机会
	 * @param values
	 * @param params
	 */
	public void updateCsSaleOpportByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsSaleOpportBy时无条件");
		csSaleOpportDao.updateCsSaleOpportByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除销售机会
	 * @param params
	 */
	public void deleteCsSaleOpportByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsSaleOpportBy时无条件");
		csSaleOpportDao.deleteCsSaleOpportByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsSaleOpportDao getCsSaleOpportDao()
	{
		return csSaleOpportDao;
	}

	public void setCsSaleOpportDao(ICsSaleOpportDao csSaleOpportDao)
	{
		this.csSaleOpportDao = csSaleOpportDao;
	}
}