package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ISrvLockDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvLock;
import com.ccclubs.service.admin.ISrvLockService;
import com.lazy3q.web.helper.$;

/**
 * 事务锁的Service实现
 * @author 飞啊飘啊
 */
public class SrvLockService implements ISrvLockService
{
	ISrvLockDao srvLockDao;
	

	/**
	 * 获取所有事务锁
	 * @return
	 */
	public List<SrvLock> getSrvLockList(Map params,Integer size)
	{
		return srvLockDao.getSrvLockList(params,size);
	}
	
	/**
	 * 获取事务锁统计
	 * @return
	 */
	public List getSrvLockStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return srvLockDao.getSrvLockStats(params,groups,sums);
	}
	
	/**
	 * 获取事务锁总数
	 * @return
	 */
	public Long getSrvLockCount(Map params)
	{
		return srvLockDao.getSrvLockCount(params);
	}
	
	/**
	 * 获取事务锁自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvLockEval(String eval,Map params)
	{
		return srvLockDao.getSrvLockEval(eval,params);
	}

	/**
	 * 获取事务锁分页
	 * @return
	 */
	public Page getSrvLockPage(int page,int size,Map params)
	{
		return srvLockDao.getSrvLockPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取事务锁
	 * @param params
	 * @return
	 */
	public SrvLock getSrvLock(Map params)
	{
		return srvLockDao.getSrvLock(params);
	}

	/**
	 * 根据ID取事务锁
	 * @param id
	 * @return
	 */
	public SrvLock getSrvLockById(Long id)
	{
		return srvLockDao.getSrvLockById(id);
	}

	/**
	 * 保存事务锁
	 * @param srvLock
	 */
	public SrvLock saveSrvLock(SrvLock srvLock)
	{
		return srvLockDao.saveSrvLock(srvLock);
	}
	
	/**
	 * 更新事务锁
	 * @param srvLock
	 */
	public void updateSrvLock(SrvLock srvLock)
	{
		srvLockDao.updateSrvLock(srvLock);
	}
	/**
	 * 更新事务锁非空字段
	 * @param srvLock
	 */
	public void updateSrvLock$NotNull(SrvLock srvLock)
	{
		srvLockDao.updateSrvLock$NotNull(srvLock);
	}
	
	/**
	 * 根据ID删除一个事务锁
	 * @param id
	 */
	public void deleteSrvLockById(Long id)
	{
		srvLockDao.deleteSrvLockById(id);
	}
	
	/**
	 * 根据条件更新事务锁
	 * @param values
	 * @param params
	 */
	public void updateSrvLockByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateSrvLockBy时无条件");
		srvLockDao.updateSrvLockByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除事务锁
	 * @param params
	 */
	public void deleteSrvLockByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteSrvLockBy时无条件");
		srvLockDao.deleteSrvLockByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ISrvLockDao getSrvLockDao()
	{
		return srvLockDao;
	}

	public void setSrvLockDao(ISrvLockDao srvLockDao)
	{
		this.srvLockDao = srvLockDao;
	}
}