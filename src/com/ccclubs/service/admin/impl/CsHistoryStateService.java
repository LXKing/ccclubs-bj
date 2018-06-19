package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsHistoryStateDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsHistoryState;
import com.ccclubs.service.admin.ICsHistoryStateService;
import com.lazy3q.web.helper.$;

/**
 * 车辆历史状态的Service实现
 * @author 飞啊飘啊
 */
public class CsHistoryStateService implements ICsHistoryStateService
{
	ICsHistoryStateDao csHistoryStateDao;
	

	/**
	 * 获取所有车辆历史状态
	 * @return
	 */
	public List<CsHistoryState> getCsHistoryStateList(Map params,Integer size)
	{
		return csHistoryStateDao.getCsHistoryStateList(params,size);
	}
	
	/**
	 * 获取车辆历史状态统计
	 * @return
	 */
	public List getCsHistoryStateStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csHistoryStateDao.getCsHistoryStateStats(params,groups,sums);
	}
	
	/**
	 * 获取车辆历史状态总数
	 * @return
	 */
	public Long getCsHistoryStateCount(Map params)
	{
		return csHistoryStateDao.getCsHistoryStateCount(params);
	}
	
	/**
	 * 获取车辆历史状态自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsHistoryStateEval(String eval,Map params)
	{
		return csHistoryStateDao.getCsHistoryStateEval(eval,params);
	}

	/**
	 * 获取车辆历史状态分页
	 * @return
	 */
	public Page getCsHistoryStatePage(int page,int size,Map params)
	{
		return csHistoryStateDao.getCsHistoryStatePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取车辆历史状态
	 * @param params
	 * @return
	 */
	public CsHistoryState getCsHistoryState(Map params)
	{
		return csHistoryStateDao.getCsHistoryState(params);
	}

	/**
	 * 根据ID取车辆历史状态
	 * @param id
	 * @return
	 */
	public CsHistoryState getCsHistoryStateById(Long id)
	{
		return csHistoryStateDao.getCsHistoryStateById(id);
	}

	/**
	 * 保存车辆历史状态
	 * @param csHistoryState
	 */
	public CsHistoryState saveCsHistoryState(CsHistoryState csHistoryState)
	{
		return csHistoryStateDao.saveCsHistoryState(csHistoryState);
	}
	
	/**
	 * 更新车辆历史状态
	 * @param csHistoryState
	 */
	public void updateCsHistoryState(CsHistoryState csHistoryState)
	{
		csHistoryStateDao.updateCsHistoryState(csHistoryState);
	}
	/**
	 * 更新车辆历史状态非空字段
	 * @param csHistoryState
	 */
	public void updateCsHistoryState$NotNull(CsHistoryState csHistoryState)
	{
		csHistoryStateDao.updateCsHistoryState$NotNull(csHistoryState);
	}
	
	/**
	 * 根据ID删除一个车辆历史状态
	 * @param id
	 */
	public void deleteCsHistoryStateById(Long id)
	{
		csHistoryStateDao.deleteCsHistoryStateById(id);
	}
	
	/**
	 * 根据条件更新车辆历史状态
	 * @param values
	 * @param params
	 */
	public void updateCsHistoryStateByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsHistoryStateBy时无条件");
		csHistoryStateDao.updateCsHistoryStateByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除车辆历史状态
	 * @param params
	 */
	public void deleteCsHistoryStateByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsHistoryStateBy时无条件");
		csHistoryStateDao.deleteCsHistoryStateByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsHistoryStateDao getCsHistoryStateDao()
	{
		return csHistoryStateDao;
	}

	public void setCsHistoryStateDao(ICsHistoryStateDao csHistoryStateDao)
	{
		this.csHistoryStateDao = csHistoryStateDao;
	}
}