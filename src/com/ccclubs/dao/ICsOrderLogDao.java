package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsOrderLog;

/**
 * 订单操作记录的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsOrderLogDao
{
	/**
	 * 获取所有订单操作记录
	 * @return
	 */
	public List<CsOrderLog> getCsOrderLogList(Map params,Integer size);
	
	/**
	 * 获取订单操作记录统计
	 * @return
	 */
	public List getCsOrderLogStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取订单操作记录总数
	 * @return
	 */	
	public Long getCsOrderLogCount(Map params);
	
	/**
	 * 获取订单操作记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsOrderLogEval(String eval,Map params);
	
	/**
	 * 获取订单操作记录分页
	 * @return
	 */	
	public Page getCsOrderLogPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取订单操作记录
	 * @param params
	 * @return
	 */
	public CsOrderLog getCsOrderLog(Map params);	

	/**
	 * 根据ID取订单操作记录
	 * @param id
	 * @return
	 */
	public CsOrderLog getCsOrderLogById(Long id);


	
	

	/**
	 * 保存订单操作记录
	 * @param csOrderLog
	 */
	public CsOrderLog saveCsOrderLog(CsOrderLog csOrderLog);

	/**
	 * 更新订单操作记录
	 * @param csOrderLog
	 */
	public void updateCsOrderLog(CsOrderLog csOrderLog);
	/**
	 * 更新订单操作记录非空字段
	 * @param csOrderLog
	 */
	public void updateCsOrderLog$NotNull(CsOrderLog csOrderLog);

	/**
	 * 根据ID删除一个订单操作记录
	 * @param id
	 */
	public void deleteCsOrderLogById(Long id);
	/**
	 * 根据条件更新订单操作记录
	 * @param values
	 * @param params
	 */
	public void updateCsOrderLogByConfirm(Map values, Map params);
	/**
	 * 根据条件删除订单操作记录
	 * @param params
	 */
	public void deleteCsOrderLogByConfirm(Map params);

}