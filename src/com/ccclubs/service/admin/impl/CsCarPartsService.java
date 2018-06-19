package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsCarPartsDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCarParts;
import com.ccclubs.service.admin.ICsCarPartsService;
import com.lazy3q.web.helper.$;

/**
 * 车辆部件的Service实现
 * @author Joel
 */
public class CsCarPartsService implements ICsCarPartsService
{
	ICsCarPartsDao csCarPartsDao;
	

	/**
	 * 获取所有车辆部件
	 * @return
	 */
	public List<CsCarParts> getCsCarPartsList(Map params,Integer size)
	{
		return csCarPartsDao.getCsCarPartsList(params,size);
	}
	
	/**
	 * 获取车辆部件统计
	 * @return
	 */
	public List getCsCarPartsStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csCarPartsDao.getCsCarPartsStats(params,groups,sums);
	}
	
	/**
	 * 获取车辆部件总数
	 * @return
	 */
	public Long getCsCarPartsCount(Map params)
	{
		return csCarPartsDao.getCsCarPartsCount(params);
	}
	
	/**
	 * 获取车辆部件自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCarPartsEval(String eval,Map params)
	{
		return csCarPartsDao.getCsCarPartsEval(eval,params);
	}

	/**
	 * 获取车辆部件分页
	 * @return
	 */
	public Page getCsCarPartsPage(int page,int size,Map params)
	{
		return csCarPartsDao.getCsCarPartsPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取车辆部件
	 * @param params
	 * @return
	 */
	public CsCarParts getCsCarParts(Map params)
	{
		return csCarPartsDao.getCsCarParts(params);
	}

	/**
	 * 根据ID取车辆部件
	 * @param id
	 * @return
	 */
	public CsCarParts getCsCarPartsById(Long id)
	{
		return csCarPartsDao.getCsCarPartsById(id);
	}

	/**
	 * 保存车辆部件
	 * @param csCarParts
	 */
	public CsCarParts saveCsCarParts(CsCarParts csCarParts)
	{
		return csCarPartsDao.saveCsCarParts(csCarParts);
	}
	
	/**
	 * 更新车辆部件
	 * @param csCarParts
	 */
	public void updateCsCarParts(CsCarParts csCarParts)
	{
		csCarPartsDao.updateCsCarParts(csCarParts);
	}
	/**
	 * 更新车辆部件非空字段
	 * @param csCarParts
	 */
	public void updateCsCarParts$NotNull(CsCarParts csCarParts)
	{
		csCarPartsDao.updateCsCarParts$NotNull(csCarParts);
	}
	
	/**
	 * 根据ID删除一个车辆部件
	 * @param id
	 */
	public void deleteCsCarPartsById(Long id)
	{
		csCarPartsDao.deleteCsCarPartsById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个车辆部件
	 * @param id
	 */
	public void removeCsCarPartsById(Long id)
	{
		csCarPartsDao.removeCsCarPartsById(id);
	}
	
	/**
	 * 根据条件更新车辆部件
	 * @param values
	 * @param params
	 */
	public void updateCsCarPartsByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsCarPartsBy时无条件");
		csCarPartsDao.updateCsCarPartsByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除车辆部件
	 * @param params
	 */
	public void deleteCsCarPartsByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsCarPartsBy时无条件");
		csCarPartsDao.deleteCsCarPartsByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsCarPartsDao getCsCarPartsDao()
	{
		return csCarPartsDao;
	}

	public void setCsCarPartsDao(ICsCarPartsDao csCarPartsDao)
	{
		this.csCarPartsDao = csCarPartsDao;
	}
}