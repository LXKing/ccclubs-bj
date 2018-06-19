package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsAlarmDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsAlarm;
import com.ccclubs.service.admin.ICsAlarmService;
import com.lazy3q.web.helper.$;

/**
 * 车辆警报的Service实现
 * @author 飞啊飘啊
 */
public class CsAlarmService implements ICsAlarmService
{
	ICsAlarmDao csAlarmDao;
	

	/**
	 * 获取所有车辆警报
	 * @return
	 */
	public List<CsAlarm> getCsAlarmList(Map params,Integer size)
	{
		return csAlarmDao.getCsAlarmList(params,size);
	}
	
	/**
	 * 获取车辆警报统计
	 * @return
	 */
	public List getCsAlarmStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csAlarmDao.getCsAlarmStats(params,groups,sums);
	}
	
	/**
	 * 获取车辆警报总数
	 * @return
	 */
	public Long getCsAlarmCount(Map params)
	{
		return csAlarmDao.getCsAlarmCount(params);
	}
	
	/**
	 * 获取车辆警报自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsAlarmEval(String eval,Map params)
	{
		return csAlarmDao.getCsAlarmEval(eval,params);
	}

	/**
	 * 获取车辆警报分页
	 * @return
	 */
	public Page getCsAlarmPage(int page,int size,Map params)
	{
		return csAlarmDao.getCsAlarmPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取车辆警报
	 * @param params
	 * @return
	 */
	public CsAlarm getCsAlarm(Map params)
	{
		return csAlarmDao.getCsAlarm(params);
	}

	/**
	 * 根据ID取车辆警报
	 * @param id
	 * @return
	 */
	public CsAlarm getCsAlarmById(Long id)
	{
		return csAlarmDao.getCsAlarmById(id);
	}

	/**
	 * 保存车辆警报
	 * @param csAlarm
	 */
	public CsAlarm saveCsAlarm(CsAlarm csAlarm)
	{
		return csAlarmDao.saveCsAlarm(csAlarm);
	}
	
	/**
	 * 更新车辆警报
	 * @param csAlarm
	 */
	public void updateCsAlarm(CsAlarm csAlarm)
	{
		csAlarmDao.updateCsAlarm(csAlarm);
	}
	/**
	 * 更新车辆警报非空字段
	 * @param csAlarm
	 */
	public void updateCsAlarm$NotNull(CsAlarm csAlarm)
	{
		csAlarmDao.updateCsAlarm$NotNull(csAlarm);
	}
	
	/**
	 * 根据ID删除一个车辆警报
	 * @param id
	 */
	public void deleteCsAlarmById(Long id)
	{
		csAlarmDao.deleteCsAlarmById(id);
	}
	
	/**
	 * 根据条件更新车辆警报
	 * @param values
	 * @param params
	 */
	public void updateCsAlarmByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsAlarmBy时无条件");
		csAlarmDao.updateCsAlarmByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除车辆警报
	 * @param params
	 */
	public void deleteCsAlarmByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsAlarmBy时无条件");
		csAlarmDao.deleteCsAlarmByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsAlarmDao getCsAlarmDao()
	{
		return csAlarmDao;
	}

	public void setCsAlarmDao(ICsAlarmDao csAlarmDao)
	{
		this.csAlarmDao = csAlarmDao;
	}
}