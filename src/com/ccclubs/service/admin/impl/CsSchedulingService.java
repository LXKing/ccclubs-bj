package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsSchedulingDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsScheduling;
import com.ccclubs.service.admin.ICsSchedulingService;
import com.lazy3q.web.helper.$;

/**
 * 车辆调度的Service实现
 * @author 飞啊飘啊
 */
public class CsSchedulingService implements ICsSchedulingService
{
	ICsSchedulingDao csSchedulingDao;
	

	/**
	 * 获取所有车辆调度
	 * @return
	 */
	public List<CsScheduling> getCsSchedulingList(Map params,Integer size)
	{
		return csSchedulingDao.getCsSchedulingList(params,size);
	}
	
	/**
	 * 获取车辆调度统计
	 * @return
	 */
	public List getCsSchedulingStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csSchedulingDao.getCsSchedulingStats(params,groups,sums);
	}
	
	/**
	 * 获取车辆调度总数
	 * @return
	 */
	public Long getCsSchedulingCount(Map params)
	{
		return csSchedulingDao.getCsSchedulingCount(params);
	}
	
	/**
	 * 获取车辆调度自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsSchedulingEval(String eval,Map params)
	{
		return csSchedulingDao.getCsSchedulingEval(eval,params);
	}

	/**
	 * 获取车辆调度分页
	 * @return
	 */
	public Page getCsSchedulingPage(int page,int size,Map params)
	{
		return csSchedulingDao.getCsSchedulingPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取车辆调度
	 * @param params
	 * @return
	 */
	public CsScheduling getCsScheduling(Map params)
	{
		return csSchedulingDao.getCsScheduling(params);
	}

	/**
	 * 根据ID取车辆调度
	 * @param id
	 * @return
	 */
	public CsScheduling getCsSchedulingById(Long id)
	{
		return csSchedulingDao.getCsSchedulingById(id);
	}

	/**
	 * 保存车辆调度
	 * @param csScheduling
	 */
	public CsScheduling saveCsScheduling(CsScheduling csScheduling)
	{
		return csSchedulingDao.saveCsScheduling(csScheduling);
	}
	
	/**
	 * 更新车辆调度
	 * @param csScheduling
	 */
	public void updateCsScheduling(CsScheduling csScheduling)
	{
		csSchedulingDao.updateCsScheduling(csScheduling);
	}
	/**
	 * 更新车辆调度非空字段
	 * @param csScheduling
	 */
	public void updateCsScheduling$NotNull(CsScheduling csScheduling)
	{
		csSchedulingDao.updateCsScheduling$NotNull(csScheduling);
	}
	
	/**
	 * 根据ID删除一个车辆调度
	 * @param id
	 */
	public void deleteCsSchedulingById(Long id)
	{
		csSchedulingDao.deleteCsSchedulingById(id);
	}
	/**
	 * 根据ID逻辑删除一个车辆调度
	 * @param id
	 */
	public void removeCsSchedulingById(Long id)
	{
		csSchedulingDao.removeCsSchedulingById(id);
	}
	
	/**
	 * 根据条件更新车辆调度
	 * @param values
	 * @param params
	 */
	public void updateCsSchedulingByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsSchedulingBy时无条件");
		csSchedulingDao.updateCsSchedulingByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除车辆调度
	 * @param params
	 */
	public void deleteCsSchedulingByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsSchedulingBy时无条件");
		csSchedulingDao.deleteCsSchedulingByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsSchedulingDao getCsSchedulingDao()
	{
		return csSchedulingDao;
	}

	public void setCsSchedulingDao(ICsSchedulingDao csSchedulingDao)
	{
		this.csSchedulingDao = csSchedulingDao;
	}
}