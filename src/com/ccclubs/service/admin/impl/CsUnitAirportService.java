package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsUnitAirportDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnitAirport;
import com.ccclubs.service.admin.ICsUnitAirportService;
import com.lazy3q.web.helper.$;

/**
 * 机场服务的Service实现
 * @author 飞啊飘啊
 */
public class CsUnitAirportService implements ICsUnitAirportService
{
	ICsUnitAirportDao csUnitAirportDao;
	

	/**
	 * 获取所有机场服务
	 * @return
	 */
	public List<CsUnitAirport> getCsUnitAirportList(Map params,Integer size)
	{
		return csUnitAirportDao.getCsUnitAirportList(params,size);
	}
	
	/**
	 * 获取机场服务统计
	 * @return
	 */
	public List getCsUnitAirportStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csUnitAirportDao.getCsUnitAirportStats(params,groups,sums);
	}
	
	/**
	 * 获取机场服务总数
	 * @return
	 */
	public Long getCsUnitAirportCount(Map params)
	{
		return csUnitAirportDao.getCsUnitAirportCount(params);
	}
	
	/**
	 * 获取机场服务自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUnitAirportEval(String eval,Map params)
	{
		return csUnitAirportDao.getCsUnitAirportEval(eval,params);
	}

	/**
	 * 获取机场服务分页
	 * @return
	 */
	public Page getCsUnitAirportPage(int page,int size,Map params)
	{
		return csUnitAirportDao.getCsUnitAirportPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取机场服务
	 * @param params
	 * @return
	 */
	public CsUnitAirport getCsUnitAirport(Map params)
	{
		return csUnitAirportDao.getCsUnitAirport(params);
	}

	/**
	 * 根据ID取机场服务
	 * @param id
	 * @return
	 */
	public CsUnitAirport getCsUnitAirportById(Long id)
	{
		return csUnitAirportDao.getCsUnitAirportById(id);
	}

	/**
	 * 保存机场服务
	 * @param csUnitAirport
	 */
	public CsUnitAirport saveCsUnitAirport(CsUnitAirport csUnitAirport)
	{
		return csUnitAirportDao.saveCsUnitAirport(csUnitAirport);
	}
	
	/**
	 * 更新机场服务
	 * @param csUnitAirport
	 */
	public void updateCsUnitAirport(CsUnitAirport csUnitAirport)
	{
		csUnitAirportDao.updateCsUnitAirport(csUnitAirport);
	}
	/**
	 * 更新机场服务非空字段
	 * @param csUnitAirport
	 */
	public void updateCsUnitAirport$NotNull(CsUnitAirport csUnitAirport)
	{
		csUnitAirportDao.updateCsUnitAirport$NotNull(csUnitAirport);
	}
	
	/**
	 * 根据ID删除一个机场服务
	 * @param id
	 */
	public void deleteCsUnitAirportById(Long id)
	{
		csUnitAirportDao.deleteCsUnitAirportById(id);
	}
	/**
	 * 根据ID逻辑删除一个机场服务
	 * @param id
	 */
	public void removeCsUnitAirportById(Long id)
	{
		csUnitAirportDao.removeCsUnitAirportById(id);
	}
	
	/**
	 * 根据条件更新机场服务
	 * @param values
	 * @param params
	 */
	public void updateCsUnitAirportByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsUnitAirportBy时无条件");
		csUnitAirportDao.updateCsUnitAirportByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除机场服务
	 * @param params
	 */
	public void deleteCsUnitAirportByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsUnitAirportBy时无条件");
		csUnitAirportDao.deleteCsUnitAirportByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsUnitAirportDao getCsUnitAirportDao()
	{
		return csUnitAirportDao;
	}

	public void setCsUnitAirportDao(ICsUnitAirportDao csUnitAirportDao)
	{
		this.csUnitAirportDao = csUnitAirportDao;
	}
}