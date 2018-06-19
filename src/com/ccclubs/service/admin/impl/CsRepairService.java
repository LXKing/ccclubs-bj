package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsRepairDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRepair;
import com.ccclubs.service.admin.ICsRepairService;
import com.lazy3q.web.helper.$;

/**
 * 车辆维修的Service实现
 * @author 飞啊飘啊
 */
public class CsRepairService implements ICsRepairService
{
	ICsRepairDao csRepairDao;
	

	/**
	 * 获取所有车辆维修
	 * @return
	 */
	public List<CsRepair> getCsRepairList(Map params,Integer size)
	{
		return csRepairDao.getCsRepairList(params,size);
	}
	
	/**
	 * 获取车辆维修统计
	 * @return
	 */
	public List getCsRepairStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csRepairDao.getCsRepairStats(params,groups,sums);
	}
	
	/**
	 * 获取车辆维修总数
	 * @return
	 */
	public Long getCsRepairCount(Map params)
	{
		return csRepairDao.getCsRepairCount(params);
	}
	
	/**
	 * 获取车辆维修自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRepairEval(String eval,Map params)
	{
		return csRepairDao.getCsRepairEval(eval,params);
	}

	/**
	 * 获取车辆维修分页
	 * @return
	 */
	public Page getCsRepairPage(int page,int size,Map params)
	{
		return csRepairDao.getCsRepairPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取车辆维修
	 * @param params
	 * @return
	 */
	public CsRepair getCsRepair(Map params)
	{
		return csRepairDao.getCsRepair(params);
	}

	/**
	 * 根据ID取车辆维修
	 * @param id
	 * @return
	 */
	public CsRepair getCsRepairById(Long id)
	{
		return csRepairDao.getCsRepairById(id);
	}

	/**
	 * 保存车辆维修
	 * @param csRepair
	 */
	public CsRepair saveCsRepair(CsRepair csRepair)
	{
		return csRepairDao.saveCsRepair(csRepair);
	}
	
	/**
	 * 更新车辆维修
	 * @param csRepair
	 */
	public void updateCsRepair(CsRepair csRepair)
	{
		csRepairDao.updateCsRepair(csRepair);
	}
	/**
	 * 更新车辆维修非空字段
	 * @param csRepair
	 */
	public void updateCsRepair$NotNull(CsRepair csRepair)
	{
		csRepairDao.updateCsRepair$NotNull(csRepair);
	}
	
	/**
	 * 根据ID删除一个车辆维修
	 * @param id
	 */
	public void deleteCsRepairById(Long id)
	{
		csRepairDao.deleteCsRepairById(id);
	}
	/**
	 * 根据ID逻辑删除一个车辆维修
	 * @param id
	 */
	public void removeCsRepairById(Long id)
	{
		csRepairDao.removeCsRepairById(id);
	}
	
	/**
	 * 根据条件更新车辆维修
	 * @param values
	 * @param params
	 */
	public void updateCsRepairByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsRepairBy时无条件");
		csRepairDao.updateCsRepairByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除车辆维修
	 * @param params
	 */
	public void deleteCsRepairByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsRepairBy时无条件");
		csRepairDao.deleteCsRepairByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsRepairDao getCsRepairDao()
	{
		return csRepairDao;
	}

	public void setCsRepairDao(ICsRepairDao csRepairDao)
	{
		this.csRepairDao = csRepairDao;
	}
}