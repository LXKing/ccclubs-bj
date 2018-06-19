package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.SrvTransaction;

/**
 * 数据事务的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ISrvTransactionService
{
	/**
	 * 获取所有数据事务
	 * @return
	 */
	public List<SrvTransaction> getSrvTransactionList(Map params,Integer size);
	
	/**
	 * 获取数据事务统计
	 * @return
	 */
	public List getSrvTransactionStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取数据事务总数
	 * @return
	 */	
	public Long getSrvTransactionCount(Map params);
	
	
	/**
	 * 获取数据事务自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvTransactionEval(String eval,Map params);

	/**
	 * 获取数据事务分页
	 * @return
	 */	
	public Page getSrvTransactionPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取数据事务
	 * @param params
	 * @return
	 */
	public SrvTransaction getSrvTransaction(Map params);

	/**
	 * 根据ID取数据事务
	 * @param id
	 * @return
	 */
	public SrvTransaction getSrvTransactionById(Long id);


	/**
	 * 保存数据事务
	 * @param srvTransaction
	 */
	public SrvTransaction saveSrvTransaction(SrvTransaction srvTransaction);

	/**
	 * 更新数据事务
	 * @param srvTransaction
	 */
	public void updateSrvTransaction(SrvTransaction srvTransaction);
	/**
	 * 更新数据事务非空字段
	 * @param srvTransaction
	 */
	public void updateSrvTransaction$NotNull(SrvTransaction srvTransaction);

	/**
	 * 根据ID删除一个数据事务
	 * @param id
	 */
	public void deleteSrvTransactionById(Long id);
	/**
	 * 根据条件更新数据事务
	 * @param values
	 * @param params
	 */
	public void updateSrvTransactionByConfirm(Map values, Map params);
	/**
	 * 根据条件删除数据事务
	 * @param params
	 */
	public void deleteSrvTransactionByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}