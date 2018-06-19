package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsUpgradeLogDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUpgradeLog;
import com.ccclubs.service.admin.ICsUpgradeLogService;
import com.lazy3q.web.helper.$;

/**
 * 升级日志的Service实现
 * @author 飞啊飘啊
 */
public class CsUpgradeLogService implements ICsUpgradeLogService
{
	ICsUpgradeLogDao csUpgradeLogDao;
	

	/**
	 * 获取所有升级日志
	 * @return
	 */
	public List<CsUpgradeLog> getCsUpgradeLogList(Map params,Integer size)
	{
		return csUpgradeLogDao.getCsUpgradeLogList(params,size);
	}
	
	/**
	 * 获取升级日志统计
	 * @return
	 */
	public List getCsUpgradeLogStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csUpgradeLogDao.getCsUpgradeLogStats(params,groups,sums);
	}
	
	/**
	 * 获取升级日志总数
	 * @return
	 */
	public Long getCsUpgradeLogCount(Map params)
	{
		return csUpgradeLogDao.getCsUpgradeLogCount(params);
	}
	
	/**
	 * 获取升级日志自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUpgradeLogEval(String eval,Map params)
	{
		return csUpgradeLogDao.getCsUpgradeLogEval(eval,params);
	}

	/**
	 * 获取升级日志分页
	 * @return
	 */
	public Page getCsUpgradeLogPage(int page,int size,Map params)
	{
		return csUpgradeLogDao.getCsUpgradeLogPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取升级日志
	 * @param params
	 * @return
	 */
	public CsUpgradeLog getCsUpgradeLog(Map params)
	{
		return csUpgradeLogDao.getCsUpgradeLog(params);
	}

	/**
	 * 根据ID取升级日志
	 * @param id
	 * @return
	 */
	public CsUpgradeLog getCsUpgradeLogById(Long id)
	{
		return csUpgradeLogDao.getCsUpgradeLogById(id);
	}

	/**
	 * 保存升级日志
	 * @param csUpgradeLog
	 */
	public CsUpgradeLog saveCsUpgradeLog(CsUpgradeLog csUpgradeLog)
	{
		return csUpgradeLogDao.saveCsUpgradeLog(csUpgradeLog);
	}
	
	/**
	 * 更新升级日志
	 * @param csUpgradeLog
	 */
	public void updateCsUpgradeLog(CsUpgradeLog csUpgradeLog)
	{
		csUpgradeLogDao.updateCsUpgradeLog(csUpgradeLog);
	}
	/**
	 * 更新升级日志非空字段
	 * @param csUpgradeLog
	 */
	public void updateCsUpgradeLog$NotNull(CsUpgradeLog csUpgradeLog)
	{
		csUpgradeLogDao.updateCsUpgradeLog$NotNull(csUpgradeLog);
	}
	
	/**
	 * 根据ID删除一个升级日志
	 * @param id
	 */
	public void deleteCsUpgradeLogById(Long id)
	{
		csUpgradeLogDao.deleteCsUpgradeLogById(id);
	}
	/**
	 * 根据ID逻辑删除一个升级日志
	 * @param id
	 */
	public void removeCsUpgradeLogById(Long id)
	{
		csUpgradeLogDao.removeCsUpgradeLogById(id);
	}
	
	/**
	 * 根据条件更新升级日志
	 * @param values
	 * @param params
	 */
	public void updateCsUpgradeLogByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsUpgradeLogBy时无条件");
		csUpgradeLogDao.updateCsUpgradeLogByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除升级日志
	 * @param params
	 */
	public void deleteCsUpgradeLogByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsUpgradeLogBy时无条件");
		csUpgradeLogDao.deleteCsUpgradeLogByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsUpgradeLogDao getCsUpgradeLogDao()
	{
		return csUpgradeLogDao;
	}

	public void setCsUpgradeLogDao(ICsUpgradeLogDao csUpgradeLogDao)
	{
		this.csUpgradeLogDao = csUpgradeLogDao;
	}
}