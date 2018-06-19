package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ISrvTransactionDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvTransaction;
import com.ccclubs.service.admin.ISrvTransactionService;
import com.lazy3q.web.helper.$;

/**
 * 数据事务的Service实现
 * @author 飞啊飘啊
 */
public class SrvTransactionService implements ISrvTransactionService
{
	ISrvTransactionDao srvTransactionDao;
	

	/**
	 * 获取所有数据事务
	 * @return
	 */
	public List<SrvTransaction> getSrvTransactionList(Map params,Integer size)
	{
		return srvTransactionDao.getSrvTransactionList(params,size);
	}
	
	/**
	 * 获取数据事务统计
	 * @return
	 */
	public List getSrvTransactionStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return srvTransactionDao.getSrvTransactionStats(params,groups,sums);
	}
	
	/**
	 * 获取数据事务总数
	 * @return
	 */
	public Long getSrvTransactionCount(Map params)
	{
		return srvTransactionDao.getSrvTransactionCount(params);
	}
	
	/**
	 * 获取数据事务自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvTransactionEval(String eval,Map params)
	{
		return srvTransactionDao.getSrvTransactionEval(eval,params);
	}

	/**
	 * 获取数据事务分页
	 * @return
	 */
	public Page getSrvTransactionPage(int page,int size,Map params)
	{
		return srvTransactionDao.getSrvTransactionPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取数据事务
	 * @param params
	 * @return
	 */
	public SrvTransaction getSrvTransaction(Map params)
	{
		return srvTransactionDao.getSrvTransaction(params);
	}

	/**
	 * 根据ID取数据事务
	 * @param id
	 * @return
	 */
	public SrvTransaction getSrvTransactionById(Long id)
	{
		return srvTransactionDao.getSrvTransactionById(id);
	}

	/**
	 * 保存数据事务
	 * @param srvTransaction
	 */
	public SrvTransaction saveSrvTransaction(SrvTransaction srvTransaction)
	{
		return srvTransactionDao.saveSrvTransaction(srvTransaction);
	}
	
	/**
	 * 更新数据事务
	 * @param srvTransaction
	 */
	public void updateSrvTransaction(SrvTransaction srvTransaction)
	{
		srvTransactionDao.updateSrvTransaction(srvTransaction);
	}
	/**
	 * 更新数据事务非空字段
	 * @param srvTransaction
	 */
	public void updateSrvTransaction$NotNull(SrvTransaction srvTransaction)
	{
		srvTransactionDao.updateSrvTransaction$NotNull(srvTransaction);
	}
	
	/**
	 * 根据ID删除一个数据事务
	 * @param id
	 */
	public void deleteSrvTransactionById(Long id)
	{
		srvTransactionDao.deleteSrvTransactionById(id);
	}
	
	/**
	 * 根据条件更新数据事务
	 * @param values
	 * @param params
	 */
	public void updateSrvTransactionByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateSrvTransactionBy时无条件");
		srvTransactionDao.updateSrvTransactionByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除数据事务
	 * @param params
	 */
	public void deleteSrvTransactionByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteSrvTransactionBy时无条件");
		srvTransactionDao.deleteSrvTransactionByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ISrvTransactionDao getSrvTransactionDao()
	{
		return srvTransactionDao;
	}

	public void setSrvTransactionDao(ISrvTransactionDao srvTransactionDao)
	{
		this.srvTransactionDao = srvTransactionDao;
	}
}