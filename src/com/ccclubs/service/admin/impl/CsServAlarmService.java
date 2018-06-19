package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsServAlarmDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsServAlarm;
import com.ccclubs.service.admin.ICsServAlarmService;
import com.lazy3q.web.helper.$;

/**
 * 服务监控报警的Service实现
 * @author Joel
 */
public class CsServAlarmService implements ICsServAlarmService
{
	ICsServAlarmDao csServAlarmDao;
	

	/**
	 * 获取所有服务监控报警
	 * @return
	 */
	public List<CsServAlarm> getCsServAlarmList(Map params,Integer size)
	{
		return csServAlarmDao.getCsServAlarmList(params,size);
	}
	
	/**
	 * 获取服务监控报警统计
	 * @return
	 */
	public List getCsServAlarmStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csServAlarmDao.getCsServAlarmStats(params,groups,sums);
	}
	
	/**
	 * 获取服务监控报警总数
	 * @return
	 */
	public Long getCsServAlarmCount(Map params)
	{
		return csServAlarmDao.getCsServAlarmCount(params);
	}
	
	/**
	 * 获取服务监控报警自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsServAlarmEval(String eval,Map params)
	{
		return csServAlarmDao.getCsServAlarmEval(eval,params);
	}

	/**
	 * 获取服务监控报警分页
	 * @return
	 */
	public Page getCsServAlarmPage(int page,int size,Map params)
	{
		return csServAlarmDao.getCsServAlarmPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取服务监控报警
	 * @param params
	 * @return
	 */
	public CsServAlarm getCsServAlarm(Map params)
	{
		return csServAlarmDao.getCsServAlarm(params);
	}

	/**
	 * 根据ID取服务监控报警
	 * @param id
	 * @return
	 */
	public CsServAlarm getCsServAlarmById(Long id)
	{
		return csServAlarmDao.getCsServAlarmById(id);
	}

	/**
	 * 保存服务监控报警
	 * @param csServAlarm
	 */
	public CsServAlarm saveCsServAlarm(CsServAlarm csServAlarm)
	{
		return csServAlarmDao.saveCsServAlarm(csServAlarm);
	}
	
	/**
	 * 更新服务监控报警
	 * @param csServAlarm
	 */
	public void updateCsServAlarm(CsServAlarm csServAlarm)
	{
		csServAlarmDao.updateCsServAlarm(csServAlarm);
	}
	/**
	 * 更新服务监控报警非空字段
	 * @param csServAlarm
	 */
	public void updateCsServAlarm$NotNull(CsServAlarm csServAlarm)
	{
		csServAlarmDao.updateCsServAlarm$NotNull(csServAlarm);
	}
	
	/**
	 * 根据ID删除一个服务监控报警
	 * @param id
	 */
	public void deleteCsServAlarmById(Long id)
	{
		csServAlarmDao.deleteCsServAlarmById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个服务监控报警
	 * @param id
	 */
	public void removeCsServAlarmById(Long id)
	{
		csServAlarmDao.removeCsServAlarmById(id);
	}
	
	/**
	 * 根据条件更新服务监控报警
	 * @param values
	 * @param params
	 */
	public void updateCsServAlarmByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsServAlarmBy时无条件");
		csServAlarmDao.updateCsServAlarmByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除服务监控报警
	 * @param params
	 */
	public void deleteCsServAlarmByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsServAlarmBy时无条件");
		csServAlarmDao.deleteCsServAlarmByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsServAlarmDao getCsServAlarmDao()
	{
		return csServAlarmDao;
	}

	public void setCsServAlarmDao(ICsServAlarmDao csServAlarmDao)
	{
		this.csServAlarmDao = csServAlarmDao;
	}
}