package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsLogDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsLog;
import com.ccclubs.service.admin.ICsLogService;
import com.lazy3q.web.helper.$;

/**
 * 系统日志的Service实现
 * @author 飞啊飘啊
 */
public class CsLogService implements ICsLogService
{
	ICsLogDao csLogDao;
	

	/**
	 * 获取所有系统日志
	 * @return
	 */
	public List<CsLog> getCsLogList(Map params,Integer size)
	{
		return csLogDao.getCsLogList(params,size);
	}
	
	/**
	 * 获取系统日志统计
	 * @return
	 */
	public List getCsLogStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csLogDao.getCsLogStats(params,groups,sums);
	}
	
	/**
	 * 获取系统日志总数
	 * @return
	 */
	public Long getCsLogCount(Map params)
	{
		return csLogDao.getCsLogCount(params);
	}
	
	/**
	 * 获取系统日志自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsLogEval(String eval,Map params)
	{
		return csLogDao.getCsLogEval(eval,params);
	}

	/**
	 * 获取系统日志分页
	 * @return
	 */
	public Page getCsLogPage(int page,int size,Map params)
	{
		return csLogDao.getCsLogPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取系统日志
	 * @param params
	 * @return
	 */
	public CsLog getCsLog(Map params)
	{
		return csLogDao.getCsLog(params);
	}

	/**
	 * 根据ID取系统日志
	 * @param id
	 * @return
	 */
	public CsLog getCsLogById(Long id)
	{
		return csLogDao.getCsLogById(id);
	}

	/**
	 * 保存系统日志
	 * @param csLog
	 */
	public CsLog saveCsLog(CsLog csLog)
	{
		return csLogDao.saveCsLog(csLog);
	}
	
	/**
	 * 更新系统日志
	 * @param csLog
	 */
	public void updateCsLog(CsLog csLog)
	{
		csLogDao.updateCsLog(csLog);
	}
	/**
	 * 更新系统日志非空字段
	 * @param csLog
	 */
	public void updateCsLog$NotNull(CsLog csLog)
	{
		csLogDao.updateCsLog$NotNull(csLog);
	}
	
	/**
	 * 根据ID删除一个系统日志
	 * @param id
	 */
	public void deleteCsLogById(Long id)
	{
		csLogDao.deleteCsLogById(id);
	}
	
	/**
	 * 根据条件更新系统日志
	 * @param values
	 * @param params
	 */
	public void updateCsLogByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsLogBy时无条件");
		csLogDao.updateCsLogByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除系统日志
	 * @param params
	 */
	public void deleteCsLogByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsLogBy时无条件");
		csLogDao.deleteCsLogByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsLogDao getCsLogDao()
	{
		return csLogDao;
	}

	public void setCsLogDao(ICsLogDao csLogDao)
	{
		this.csLogDao = csLogDao;
	}
}