package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ISrvApiLogDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvApiLog;
import com.ccclubs.service.admin.ISrvApiLogService;
import com.lazy3q.web.helper.$;

/**
 * API日志的Service实现
 * @author 飞啊飘啊
 */
public class SrvApiLogService implements ISrvApiLogService
{
	ISrvApiLogDao srvApiLogDao;
	

	/**
	 * 获取所有API日志
	 * @return
	 */
	public List<SrvApiLog> getSrvApiLogList(Map params,Integer size)
	{
		return srvApiLogDao.getSrvApiLogList(params,size);
	}
	
	/**
	 * 获取API日志统计
	 * @return
	 */
	public List getSrvApiLogStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return srvApiLogDao.getSrvApiLogStats(params,groups,sums);
	}
	
	/**
	 * 获取API日志总数
	 * @return
	 */
	public Long getSrvApiLogCount(Map params)
	{
		return srvApiLogDao.getSrvApiLogCount(params);
	}
	
	/**
	 * 获取API日志自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvApiLogEval(String eval,Map params)
	{
		return srvApiLogDao.getSrvApiLogEval(eval,params);
	}

	/**
	 * 获取API日志分页
	 * @return
	 */
	public Page getSrvApiLogPage(int page,int size,Map params)
	{
		return srvApiLogDao.getSrvApiLogPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取API日志
	 * @param params
	 * @return
	 */
	public SrvApiLog getSrvApiLog(Map params)
	{
		return srvApiLogDao.getSrvApiLog(params);
	}

	/**
	 * 根据ID取API日志
	 * @param id
	 * @return
	 */
	public SrvApiLog getSrvApiLogById(Long id)
	{
		return srvApiLogDao.getSrvApiLogById(id);
	}

	/**
	 * 保存API日志
	 * @param srvApiLog
	 */
	public SrvApiLog saveSrvApiLog(SrvApiLog srvApiLog)
	{
		return srvApiLogDao.saveSrvApiLog(srvApiLog);
	}
	
	/**
	 * 更新API日志
	 * @param srvApiLog
	 */
	public void updateSrvApiLog(SrvApiLog srvApiLog)
	{
		srvApiLogDao.updateSrvApiLog(srvApiLog);
	}
	/**
	 * 更新API日志非空字段
	 * @param srvApiLog
	 */
	public void updateSrvApiLog$NotNull(SrvApiLog srvApiLog)
	{
		srvApiLogDao.updateSrvApiLog$NotNull(srvApiLog);
	}
	
	/**
	 * 根据ID删除一个API日志
	 * @param id
	 */
	public void deleteSrvApiLogById(Long id)
	{
		srvApiLogDao.deleteSrvApiLogById(id);
	}
	/**
	 * 根据ID逻辑删除一个API日志
	 * @param id
	 */
	public void removeSrvApiLogById(Long id)
	{
		srvApiLogDao.removeSrvApiLogById(id);
	}
	
	/**
	 * 根据条件更新API日志
	 * @param values
	 * @param params
	 */
	public void updateSrvApiLogByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateSrvApiLogBy时无条件");
		srvApiLogDao.updateSrvApiLogByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除API日志
	 * @param params
	 */
	public void deleteSrvApiLogByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteSrvApiLogBy时无条件");
		srvApiLogDao.deleteSrvApiLogByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ISrvApiLogDao getSrvApiLogDao()
	{
		return srvApiLogDao;
	}

	public void setSrvApiLogDao(ISrvApiLogDao srvApiLogDao)
	{
		this.srvApiLogDao = srvApiLogDao;
	}
}