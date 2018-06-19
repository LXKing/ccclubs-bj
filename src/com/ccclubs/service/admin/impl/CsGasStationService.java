package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsGasStationDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsGasStation;
import com.ccclubs.service.admin.ICsGasStationService;
import com.lazy3q.web.helper.$;

/**
 * 加油站的Service实现
 * @author 飞啊飘啊
 */
public class CsGasStationService implements ICsGasStationService
{
	ICsGasStationDao csGasStationDao;
	

	/**
	 * 获取所有加油站
	 * @return
	 */
	public List<CsGasStation> getCsGasStationList(Map params,Integer size)
	{
		return csGasStationDao.getCsGasStationList(params,size);
	}
	
	/**
	 * 获取加油站统计
	 * @return
	 */
	public List getCsGasStationStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csGasStationDao.getCsGasStationStats(params,groups,sums);
	}
	
	/**
	 * 获取加油站总数
	 * @return
	 */
	public Long getCsGasStationCount(Map params)
	{
		return csGasStationDao.getCsGasStationCount(params);
	}
	
	/**
	 * 获取加油站自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsGasStationEval(String eval,Map params)
	{
		return csGasStationDao.getCsGasStationEval(eval,params);
	}

	/**
	 * 获取加油站分页
	 * @return
	 */
	public Page getCsGasStationPage(int page,int size,Map params)
	{
		return csGasStationDao.getCsGasStationPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取加油站
	 * @param params
	 * @return
	 */
	public CsGasStation getCsGasStation(Map params)
	{
		return csGasStationDao.getCsGasStation(params);
	}

	/**
	 * 根据ID取加油站
	 * @param id
	 * @return
	 */
	public CsGasStation getCsGasStationById(Long id)
	{
		return csGasStationDao.getCsGasStationById(id);
	}

	/**
	 * 保存加油站
	 * @param csGasStation
	 */
	public CsGasStation saveCsGasStation(CsGasStation csGasStation)
	{
		return csGasStationDao.saveCsGasStation(csGasStation);
	}
	
	/**
	 * 更新加油站
	 * @param csGasStation
	 */
	public void updateCsGasStation(CsGasStation csGasStation)
	{
		csGasStationDao.updateCsGasStation(csGasStation);
	}
	/**
	 * 更新加油站非空字段
	 * @param csGasStation
	 */
	public void updateCsGasStation$NotNull(CsGasStation csGasStation)
	{
		csGasStationDao.updateCsGasStation$NotNull(csGasStation);
	}
	
	/**
	 * 根据ID删除一个加油站
	 * @param id
	 */
	public void deleteCsGasStationById(Long id)
	{
		csGasStationDao.deleteCsGasStationById(id);
	}
	/**
	 * 根据ID逻辑删除一个加油站
	 * @param id
	 */
	public void removeCsGasStationById(Long id)
	{
		csGasStationDao.removeCsGasStationById(id);
	}
	
	/**
	 * 根据条件更新加油站
	 * @param values
	 * @param params
	 */
	public void updateCsGasStationByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsGasStationBy时无条件");
		csGasStationDao.updateCsGasStationByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除加油站
	 * @param params
	 */
	public void deleteCsGasStationByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsGasStationBy时无条件");
		csGasStationDao.deleteCsGasStationByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsGasStationDao getCsGasStationDao()
	{
		return csGasStationDao;
	}

	public void setCsGasStationDao(ICsGasStationDao csGasStationDao)
	{
		this.csGasStationDao = csGasStationDao;
	}
}