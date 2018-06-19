package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsStateDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsState;
import com.ccclubs.service.admin.ICsStateService;
import com.lazy3q.web.helper.$;

/**
 * 车辆当前状态的Service实现
 * @author 飞啊飘啊
 */
public class CsStateService implements ICsStateService
{
	ICsStateDao csStateDao;
	

	/**
	 * 获取所有车辆当前状态
	 * @return
	 */
	public List<CsState> getCsStateList(Map params,Integer size)
	{
		return csStateDao.getCsStateList(params,size);
	}
	
	/**
	 * 获取车辆当前状态统计
	 * @return
	 */
	public List getCsStateStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csStateDao.getCsStateStats(params,groups,sums);
	}
	
	/**
	 * 获取车辆当前状态总数
	 * @return
	 */
	public Long getCsStateCount(Map params)
	{
		return csStateDao.getCsStateCount(params);
	}
	
	/**
	 * 获取车辆当前状态自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsStateEval(String eval,Map params)
	{
		return csStateDao.getCsStateEval(eval,params);
	}

	/**
	 * 获取车辆当前状态分页
	 * @return
	 */
	public Page getCsStatePage(int page,int size,Map params)
	{
		return csStateDao.getCsStatePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取车辆当前状态
	 * @param params
	 * @return
	 */
	public CsState getCsState(Map params)
	{
		return csStateDao.getCsState(params);
	}

	/**
	 * 根据ID取车辆当前状态
	 * @param id
	 * @return
	 */
	public CsState getCsStateById(Long id)
	{
		return csStateDao.getCsStateById(id);
	}

	/**
	 * 保存车辆当前状态
	 * @param csState
	 */
	public CsState saveCsState(CsState csState)
	{
		return csStateDao.saveCsState(csState);
	}
	
	/**
	 * 更新车辆当前状态
	 * @param csState
	 */
	public void updateCsState(CsState csState)
	{
		csStateDao.updateCsState(csState);
	}
	/**
	 * 更新车辆当前状态非空字段
	 * @param csState
	 */
	public void updateCsState$NotNull(CsState csState)
	{
		csStateDao.updateCsState$NotNull(csState);
	}
	
	/**
	 * 根据ID删除一个车辆当前状态
	 * @param id
	 */
	public void deleteCsStateById(Long id)
	{
		csStateDao.deleteCsStateById(id);
	}
	
	/**
	 * 根据条件更新车辆当前状态
	 * @param values
	 * @param params
	 */
	public void updateCsStateByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsStateBy时无条件");
		csStateDao.updateCsStateByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除车辆当前状态
	 * @param params
	 */
	public void deleteCsStateByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsStateBy时无条件");
		csStateDao.deleteCsStateByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsStateDao getCsStateDao()
	{
		return csStateDao;
	}

	public void setCsStateDao(ICsStateDao csStateDao)
	{
		this.csStateDao = csStateDao;
	}
}