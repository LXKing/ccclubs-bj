package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsElecHistoryDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsElecHistory;
import com.ccclubs.service.admin.ICsElecHistoryService;
import com.lazy3q.web.helper.$;

/**
 * 电桩历史数据的Service实现
 * @author 飞啊飘啊
 */
public class CsElecHistoryService implements ICsElecHistoryService
{
	ICsElecHistoryDao csElecHistoryDao;
	

	/**
	 * 获取所有电桩历史数据
	 * @return
	 */
	public List<CsElecHistory> getCsElecHistoryList(Map params,Integer size)
	{
		return csElecHistoryDao.getCsElecHistoryList(params,size);
	}
	
	/**
	 * 获取电桩历史数据统计
	 * @return
	 */
	public List getCsElecHistoryStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csElecHistoryDao.getCsElecHistoryStats(params,groups,sums);
	}
	
	/**
	 * 获取电桩历史数据总数
	 * @return
	 */
	public Long getCsElecHistoryCount(Map params)
	{
		return csElecHistoryDao.getCsElecHistoryCount(params);
	}
	
	/**
	 * 获取电桩历史数据自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsElecHistoryEval(String eval,Map params)
	{
		return csElecHistoryDao.getCsElecHistoryEval(eval,params);
	}

	/**
	 * 获取电桩历史数据分页
	 * @return
	 */
	public Page getCsElecHistoryPage(int page,int size,Map params)
	{
		return csElecHistoryDao.getCsElecHistoryPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取电桩历史数据
	 * @param params
	 * @return
	 */
	public CsElecHistory getCsElecHistory(Map params)
	{
		return csElecHistoryDao.getCsElecHistory(params);
	}

	/**
	 * 根据ID取电桩历史数据
	 * @param id
	 * @return
	 */
	public CsElecHistory getCsElecHistoryById(Long id)
	{
		return csElecHistoryDao.getCsElecHistoryById(id);
	}

	/**
	 * 保存电桩历史数据
	 * @param csElecHistory
	 */
	public CsElecHistory saveCsElecHistory(CsElecHistory csElecHistory)
	{
		return csElecHistoryDao.saveCsElecHistory(csElecHistory);
	}
	
	/**
	 * 更新电桩历史数据
	 * @param csElecHistory
	 */
	public void updateCsElecHistory(CsElecHistory csElecHistory)
	{
		csElecHistoryDao.updateCsElecHistory(csElecHistory);
	}
	/**
	 * 更新电桩历史数据非空字段
	 * @param csElecHistory
	 */
	public void updateCsElecHistory$NotNull(CsElecHistory csElecHistory)
	{
		csElecHistoryDao.updateCsElecHistory$NotNull(csElecHistory);
	}
	
	/**
	 * 根据ID删除一个电桩历史数据
	 * @param id
	 */
	public void deleteCsElecHistoryById(Long id)
	{
		csElecHistoryDao.deleteCsElecHistoryById(id);
	}
	
	/**
	 * 根据条件更新电桩历史数据
	 * @param values
	 * @param params
	 */
	public void updateCsElecHistoryByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsElecHistoryBy时无条件");
		csElecHistoryDao.updateCsElecHistoryByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除电桩历史数据
	 * @param params
	 */
	public void deleteCsElecHistoryByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsElecHistoryBy时无条件");
		csElecHistoryDao.deleteCsElecHistoryByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsElecHistoryDao getCsElecHistoryDao()
	{
		return csElecHistoryDao;
	}

	public void setCsElecHistoryDao(ICsElecHistoryDao csElecHistoryDao)
	{
		this.csElecHistoryDao = csElecHistoryDao;
	}
}