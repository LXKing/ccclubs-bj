package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsOrderLogDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsOrderLog;
import com.ccclubs.service.admin.ICsOrderLogService;
import com.lazy3q.web.helper.$;

/**
 * 订单操作记录的Service实现
 * @author 飞啊飘啊
 */
public class CsOrderLogService implements ICsOrderLogService
{
	ICsOrderLogDao csOrderLogDao;
	

	/**
	 * 获取所有订单操作记录
	 * @return
	 */
	public List<CsOrderLog> getCsOrderLogList(Map params,Integer size)
	{
		return csOrderLogDao.getCsOrderLogList(params,size);
	}
	
	/**
	 * 获取订单操作记录统计
	 * @return
	 */
	public List getCsOrderLogStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csOrderLogDao.getCsOrderLogStats(params,groups,sums);
	}
	
	/**
	 * 获取订单操作记录总数
	 * @return
	 */
	public Long getCsOrderLogCount(Map params)
	{
		return csOrderLogDao.getCsOrderLogCount(params);
	}
	
	/**
	 * 获取订单操作记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsOrderLogEval(String eval,Map params)
	{
		return csOrderLogDao.getCsOrderLogEval(eval,params);
	}

	/**
	 * 获取订单操作记录分页
	 * @return
	 */
	public Page getCsOrderLogPage(int page,int size,Map params)
	{
		return csOrderLogDao.getCsOrderLogPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取订单操作记录
	 * @param params
	 * @return
	 */
	public CsOrderLog getCsOrderLog(Map params)
	{
		return csOrderLogDao.getCsOrderLog(params);
	}

	/**
	 * 根据ID取订单操作记录
	 * @param id
	 * @return
	 */
	public CsOrderLog getCsOrderLogById(Long id)
	{
		return csOrderLogDao.getCsOrderLogById(id);
	}

	/**
	 * 保存订单操作记录
	 * @param csOrderLog
	 */
	public CsOrderLog saveCsOrderLog(CsOrderLog csOrderLog)
	{
		return csOrderLogDao.saveCsOrderLog(csOrderLog);
	}
	
	/**
	 * 更新订单操作记录
	 * @param csOrderLog
	 */
	public void updateCsOrderLog(CsOrderLog csOrderLog)
	{
		csOrderLogDao.updateCsOrderLog(csOrderLog);
	}
	/**
	 * 更新订单操作记录非空字段
	 * @param csOrderLog
	 */
	public void updateCsOrderLog$NotNull(CsOrderLog csOrderLog)
	{
		csOrderLogDao.updateCsOrderLog$NotNull(csOrderLog);
	}
	
	/**
	 * 根据ID删除一个订单操作记录
	 * @param id
	 */
	public void deleteCsOrderLogById(Long id)
	{
		csOrderLogDao.deleteCsOrderLogById(id);
	}
	
	/**
	 * 根据条件更新订单操作记录
	 * @param values
	 * @param params
	 */
	public void updateCsOrderLogByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsOrderLogBy时无条件");
		csOrderLogDao.updateCsOrderLogByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除订单操作记录
	 * @param params
	 */
	public void deleteCsOrderLogByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsOrderLogBy时无条件");
		csOrderLogDao.deleteCsOrderLogByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsOrderLogDao getCsOrderLogDao()
	{
		return csOrderLogDao;
	}

	public void setCsOrderLogDao(ICsOrderLogDao csOrderLogDao)
	{
		this.csOrderLogDao = csOrderLogDao;
	}
}