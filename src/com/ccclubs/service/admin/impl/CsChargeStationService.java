package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsChargeStationDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsChargeStation;
import com.ccclubs.service.admin.ICsChargeStationService;
import com.lazy3q.web.helper.$;

/**
 * 充电站的Service实现
 * @author Joel
 */
public class CsChargeStationService implements ICsChargeStationService
{
	ICsChargeStationDao csChargeStationDao;
	

	/**
	 * 获取所有充电站
	 * @return
	 */
	public List<CsChargeStation> getCsChargeStationList(Map params,Integer size)
	{
		return csChargeStationDao.getCsChargeStationList(params,size);
	}
	
	/**
	 * 获取充电站统计
	 * @return
	 */
	public List getCsChargeStationStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csChargeStationDao.getCsChargeStationStats(params,groups,sums);
	}
	
	/**
	 * 获取充电站总数
	 * @return
	 */
	public Long getCsChargeStationCount(Map params)
	{
		return csChargeStationDao.getCsChargeStationCount(params);
	}
	
	/**
	 * 获取充电站自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsChargeStationEval(String eval,Map params)
	{
		return csChargeStationDao.getCsChargeStationEval(eval,params);
	}

	/**
	 * 获取充电站分页
	 * @return
	 */
	public Page getCsChargeStationPage(int page,int size,Map params)
	{
		return csChargeStationDao.getCsChargeStationPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取充电站
	 * @param params
	 * @return
	 */
	public CsChargeStation getCsChargeStation(Map params)
	{
		return csChargeStationDao.getCsChargeStation(params);
	}

	/**
	 * 根据ID取充电站
	 * @param id
	 * @return
	 */
	public CsChargeStation getCsChargeStationById(Long id)
	{
		return csChargeStationDao.getCsChargeStationById(id);
	}

	/**
	 * 保存充电站
	 * @param csChargeStation
	 */
	public CsChargeStation saveCsChargeStation(CsChargeStation csChargeStation)
	{
		return csChargeStationDao.saveCsChargeStation(csChargeStation);
	}
	
	/**
	 * 更新充电站
	 * @param csChargeStation
	 */
	public void updateCsChargeStation(CsChargeStation csChargeStation)
	{
		csChargeStationDao.updateCsChargeStation(csChargeStation);
	}
	/**
	 * 更新充电站非空字段
	 * @param csChargeStation
	 */
	public void updateCsChargeStation$NotNull(CsChargeStation csChargeStation)
	{
		csChargeStationDao.updateCsChargeStation$NotNull(csChargeStation);
	}
	
	/**
	 * 根据ID删除一个充电站
	 * @param id
	 */
	public void deleteCsChargeStationById(Long id)
	{
		csChargeStationDao.deleteCsChargeStationById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个充电站
	 * @param id
	 */
	public void removeCsChargeStationById(Long id)
	{
		csChargeStationDao.removeCsChargeStationById(id);
	}
	
	/**
	 * 根据条件更新充电站
	 * @param values
	 * @param params
	 */
	public void updateCsChargeStationByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsChargeStationBy时无条件");
		csChargeStationDao.updateCsChargeStationByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除充电站
	 * @param params
	 */
	public void deleteCsChargeStationByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsChargeStationBy时无条件");
		csChargeStationDao.deleteCsChargeStationByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsChargeStationDao getCsChargeStationDao()
	{
		return csChargeStationDao;
	}

	public void setCsChargeStationDao(ICsChargeStationDao csChargeStationDao)
	{
		this.csChargeStationDao = csChargeStationDao;
	}
}