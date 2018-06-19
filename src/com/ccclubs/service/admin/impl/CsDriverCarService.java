package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsDriverCarDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsDriverCar;
import com.ccclubs.service.admin.ICsDriverCarService;
import com.lazy3q.web.helper.$;

/**
 * 司机车辆的Service实现
 * @author Joel
 */
public class CsDriverCarService implements ICsDriverCarService
{
	ICsDriverCarDao csDriverCarDao;
	

	/**
	 * 获取所有司机车辆
	 * @return
	 */
	public List<CsDriverCar> getCsDriverCarList(Map params,Integer size)
	{
		return csDriverCarDao.getCsDriverCarList(params,size);
	}
	
	/**
	 * 获取司机车辆统计
	 * @return
	 */
	public List getCsDriverCarStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csDriverCarDao.getCsDriverCarStats(params,groups,sums);
	}
	
	/**
	 * 获取司机车辆总数
	 * @return
	 */
	public Long getCsDriverCarCount(Map params)
	{
		return csDriverCarDao.getCsDriverCarCount(params);
	}
	
	/**
	 * 获取司机车辆自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsDriverCarEval(String eval,Map params)
	{
		return csDriverCarDao.getCsDriverCarEval(eval,params);
	}

	/**
	 * 获取司机车辆分页
	 * @return
	 */
	public Page getCsDriverCarPage(int page,int size,Map params)
	{
		return csDriverCarDao.getCsDriverCarPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取司机车辆
	 * @param params
	 * @return
	 */
	public CsDriverCar getCsDriverCar(Map params)
	{
		return csDriverCarDao.getCsDriverCar(params);
	}

	/**
	 * 根据ID取司机车辆
	 * @param id
	 * @return
	 */
	public CsDriverCar getCsDriverCarById(Long id)
	{
		return csDriverCarDao.getCsDriverCarById(id);
	}

	/**
	 * 保存司机车辆
	 * @param csDriverCar
	 */
	public CsDriverCar saveCsDriverCar(CsDriverCar csDriverCar)
	{
		return csDriverCarDao.saveCsDriverCar(csDriverCar);
	}
	
	/**
	 * 更新司机车辆
	 * @param csDriverCar
	 */
	public void updateCsDriverCar(CsDriverCar csDriverCar)
	{
		csDriverCarDao.updateCsDriverCar(csDriverCar);
	}
	/**
	 * 更新司机车辆非空字段
	 * @param csDriverCar
	 */
	public void updateCsDriverCar$NotNull(CsDriverCar csDriverCar)
	{
		csDriverCarDao.updateCsDriverCar$NotNull(csDriverCar);
	}
	
	/**
	 * 根据ID删除一个司机车辆
	 * @param id
	 */
	public void deleteCsDriverCarById(Long id)
	{
		csDriverCarDao.deleteCsDriverCarById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个司机车辆
	 * @param id
	 */
	public void removeCsDriverCarById(Long id)
	{
		csDriverCarDao.removeCsDriverCarById(id);
	}
	
	/**
	 * 根据条件更新司机车辆
	 * @param values
	 * @param params
	 */
	public void updateCsDriverCarByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsDriverCarBy时无条件");
		csDriverCarDao.updateCsDriverCarByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除司机车辆
	 * @param params
	 */
	public void deleteCsDriverCarByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsDriverCarBy时无条件");
		csDriverCarDao.deleteCsDriverCarByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsDriverCarDao getCsDriverCarDao()
	{
		return csDriverCarDao;
	}

	public void setCsDriverCarDao(ICsDriverCarDao csDriverCarDao)
	{
		this.csDriverCarDao = csDriverCarDao;
	}
}