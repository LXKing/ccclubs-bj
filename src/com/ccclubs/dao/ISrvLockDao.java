package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvLock;

/**
 * 事务锁的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ISrvLockDao
{
	/**
	 * 获取所有事务锁
	 * @return
	 */
	public List<SrvLock> getSrvLockList(Map params,Integer size);
	
	/**
	 * 获取事务锁统计
	 * @return
	 */
	public List getSrvLockStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取事务锁总数
	 * @return
	 */	
	public Long getSrvLockCount(Map params);
	
	/**
	 * 获取事务锁自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvLockEval(String eval,Map params);
	
	/**
	 * 获取事务锁分页
	 * @return
	 */	
	public Page getSrvLockPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取事务锁
	 * @param params
	 * @return
	 */
	public SrvLock getSrvLock(Map params);	

	/**
	 * 根据ID取事务锁
	 * @param id
	 * @return
	 */
	public SrvLock getSrvLockById(Long id);


	
	

	/**
	 * 保存事务锁
	 * @param srvLock
	 */
	public SrvLock saveSrvLock(SrvLock srvLock);

	/**
	 * 更新事务锁
	 * @param srvLock
	 */
	public void updateSrvLock(SrvLock srvLock);
	/**
	 * 更新事务锁非空字段
	 * @param srvLock
	 */
	public void updateSrvLock$NotNull(SrvLock srvLock);

	/**
	 * 根据ID删除一个事务锁
	 * @param id
	 */
	public void deleteSrvLockById(Long id);
	/**
	 * 根据条件更新事务锁
	 * @param values
	 * @param params
	 */
	public void updateSrvLockByConfirm(Map values, Map params);
	/**
	 * 根据条件删除事务锁
	 * @param params
	 */
	public void deleteSrvLockByConfirm(Map params);

}