package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsChannelLogDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsChannelLog;
import com.ccclubs.service.admin.ICsChannelLogService;
import com.lazy3q.web.helper.$;

/**
 * 渠道跟踪维护的Service实现
 * @author 飞啊飘啊
 */
public class CsChannelLogService implements ICsChannelLogService
{
	ICsChannelLogDao csChannelLogDao;
	

	/**
	 * 获取所有渠道跟踪维护
	 * @return
	 */
	public List<CsChannelLog> getCsChannelLogList(Map params,Integer size)
	{
		return csChannelLogDao.getCsChannelLogList(params,size);
	}
	
	/**
	 * 获取渠道跟踪维护统计
	 * @return
	 */
	public List getCsChannelLogStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csChannelLogDao.getCsChannelLogStats(params,groups,sums);
	}
	
	/**
	 * 获取渠道跟踪维护总数
	 * @return
	 */
	public Long getCsChannelLogCount(Map params)
	{
		return csChannelLogDao.getCsChannelLogCount(params);
	}
	
	/**
	 * 获取渠道跟踪维护自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsChannelLogEval(String eval,Map params)
	{
		return csChannelLogDao.getCsChannelLogEval(eval,params);
	}

	/**
	 * 获取渠道跟踪维护分页
	 * @return
	 */
	public Page getCsChannelLogPage(int page,int size,Map params)
	{
		return csChannelLogDao.getCsChannelLogPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取渠道跟踪维护
	 * @param params
	 * @return
	 */
	public CsChannelLog getCsChannelLog(Map params)
	{
		return csChannelLogDao.getCsChannelLog(params);
	}

	/**
	 * 根据ID取渠道跟踪维护
	 * @param id
	 * @return
	 */
	public CsChannelLog getCsChannelLogById(Long id)
	{
		return csChannelLogDao.getCsChannelLogById(id);
	}

	/**
	 * 保存渠道跟踪维护
	 * @param csChannelLog
	 */
	public CsChannelLog saveCsChannelLog(CsChannelLog csChannelLog)
	{
		return csChannelLogDao.saveCsChannelLog(csChannelLog);
	}
	
	/**
	 * 更新渠道跟踪维护
	 * @param csChannelLog
	 */
	public void updateCsChannelLog(CsChannelLog csChannelLog)
	{
		csChannelLogDao.updateCsChannelLog(csChannelLog);
	}
	/**
	 * 更新渠道跟踪维护非空字段
	 * @param csChannelLog
	 */
	public void updateCsChannelLog$NotNull(CsChannelLog csChannelLog)
	{
		csChannelLogDao.updateCsChannelLog$NotNull(csChannelLog);
	}
	
	/**
	 * 根据ID删除一个渠道跟踪维护
	 * @param id
	 */
	public void deleteCsChannelLogById(Long id)
	{
		csChannelLogDao.deleteCsChannelLogById(id);
	}
	/**
	 * 根据ID逻辑删除一个渠道跟踪维护
	 * @param id
	 */
	public void removeCsChannelLogById(Long id)
	{
		csChannelLogDao.removeCsChannelLogById(id);
	}
	
	/**
	 * 根据条件更新渠道跟踪维护
	 * @param values
	 * @param params
	 */
	public void updateCsChannelLogByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsChannelLogBy时无条件");
		csChannelLogDao.updateCsChannelLogByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除渠道跟踪维护
	 * @param params
	 */
	public void deleteCsChannelLogByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsChannelLogBy时无条件");
		csChannelLogDao.deleteCsChannelLogByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsChannelLogDao getCsChannelLogDao()
	{
		return csChannelLogDao;
	}

	public void setCsChannelLogDao(ICsChannelLogDao csChannelLogDao)
	{
		this.csChannelLogDao = csChannelLogDao;
	}
}