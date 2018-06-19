package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsAlipayLogDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsAlipayLog;
import com.ccclubs.service.admin.ICsAlipayLogService;
import com.lazy3q.web.helper.$;

/**
 * 接口日志的Service实现
 * @author 飞啊飘啊
 */
public class CsAlipayLogService implements ICsAlipayLogService
{
	ICsAlipayLogDao csAlipayLogDao;
	

	/**
	 * 获取所有接口日志
	 * @return
	 */
	public List<CsAlipayLog> getCsAlipayLogList(Map params,Integer size)
	{
		return csAlipayLogDao.getCsAlipayLogList(params,size);
	}
	
	/**
	 * 获取接口日志统计
	 * @return
	 */
	public List getCsAlipayLogStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csAlipayLogDao.getCsAlipayLogStats(params,groups,sums);
	}
	
	/**
	 * 获取接口日志总数
	 * @return
	 */
	public Long getCsAlipayLogCount(Map params)
	{
		return csAlipayLogDao.getCsAlipayLogCount(params);
	}
	
	/**
	 * 获取接口日志自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsAlipayLogEval(String eval,Map params)
	{
		return csAlipayLogDao.getCsAlipayLogEval(eval,params);
	}

	/**
	 * 获取接口日志分页
	 * @return
	 */
	public Page getCsAlipayLogPage(int page,int size,Map params)
	{
		return csAlipayLogDao.getCsAlipayLogPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取接口日志
	 * @param params
	 * @return
	 */
	public CsAlipayLog getCsAlipayLog(Map params)
	{
		return csAlipayLogDao.getCsAlipayLog(params);
	}

	/**
	 * 根据ID取接口日志
	 * @param id
	 * @return
	 */
	public CsAlipayLog getCsAlipayLogById(Long id)
	{
		return csAlipayLogDao.getCsAlipayLogById(id);
	}

	/**
	 * 保存接口日志
	 * @param csAlipayLog
	 */
	public CsAlipayLog saveCsAlipayLog(CsAlipayLog csAlipayLog)
	{
		return csAlipayLogDao.saveCsAlipayLog(csAlipayLog);
	}
	
	/**
	 * 更新接口日志
	 * @param csAlipayLog
	 */
	public void updateCsAlipayLog(CsAlipayLog csAlipayLog)
	{
		csAlipayLogDao.updateCsAlipayLog(csAlipayLog);
	}
	/**
	 * 更新接口日志非空字段
	 * @param csAlipayLog
	 */
	public void updateCsAlipayLog$NotNull(CsAlipayLog csAlipayLog)
	{
		csAlipayLogDao.updateCsAlipayLog$NotNull(csAlipayLog);
	}
	
	/**
	 * 根据ID删除一个接口日志
	 * @param id
	 */
	public void deleteCsAlipayLogById(Long id)
	{
		csAlipayLogDao.deleteCsAlipayLogById(id);
	}
	/**
	 * 根据ID逻辑删除一个接口日志
	 * @param id
	 */
	public void removeCsAlipayLogById(Long id)
	{
		csAlipayLogDao.removeCsAlipayLogById(id);
	}
	
	/**
	 * 根据条件更新接口日志
	 * @param values
	 * @param params
	 */
	public void updateCsAlipayLogByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsAlipayLogBy时无条件");
		csAlipayLogDao.updateCsAlipayLogByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除接口日志
	 * @param params
	 */
	public void deleteCsAlipayLogByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsAlipayLogBy时无条件");
		csAlipayLogDao.deleteCsAlipayLogByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsAlipayLogDao getCsAlipayLogDao()
	{
		return csAlipayLogDao;
	}

	public void setCsAlipayLogDao(ICsAlipayLogDao csAlipayLogDao)
	{
		this.csAlipayLogDao = csAlipayLogDao;
	}
}