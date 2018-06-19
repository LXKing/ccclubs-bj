package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsCarDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCar;
import com.ccclubs.service.admin.ICsCarService;
import com.lazy3q.web.helper.$;

/**
 * 车辆的Service实现
 * @author 飞啊飘啊
 */
public class CsCarService implements ICsCarService
{
	ICsCarDao csCarDao;
	

	/**
	 * 获取所有车辆
	 * @return
	 */
	public List<CsCar> getCsCarList(Map params,Integer size)
	{
		return csCarDao.getCsCarList(params,size);
	}
	
	/**
	 * 获取车辆统计
	 * @return
	 */
	public List getCsCarStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csCarDao.getCsCarStats(params,groups,sums);
	}
	
	/**
	 * 获取车辆总数
	 * @return
	 */
	public Long getCsCarCount(Map params)
	{
		return csCarDao.getCsCarCount(params);
	}
	
	/**
	 * 获取车辆自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCarEval(String eval,Map params)
	{
		return csCarDao.getCsCarEval(eval,params);
	}

	/**
	 * 获取车辆分页
	 * @return
	 */
	public Page getCsCarPage(int page,int size,Map params)
	{
		return csCarDao.getCsCarPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取车辆
	 * @param params
	 * @return
	 */
	public CsCar getCsCar(Map params)
	{
		return csCarDao.getCsCar(params);
	}

	/**
	 * 根据ID取车辆
	 * @param id
	 * @return
	 */
	public CsCar getCsCarById(Long id)
	{
		return csCarDao.getCsCarById(id);
	}

	/**
	 * 保存车辆
	 * @param csCar
	 */
	public CsCar saveCsCar(CsCar csCar)
	{
		return csCarDao.saveCsCar(csCar);
	}
	
	/**
	 * 更新车辆
	 * @param csCar
	 */
	public void updateCsCar(CsCar csCar)
	{
		csCarDao.updateCsCar(csCar);
	}
	/**
	 * 更新车辆非空字段
	 * @param csCar
	 */
	public void updateCsCar$NotNull(CsCar csCar)
	{
		csCarDao.updateCsCar$NotNull(csCar);
	}
	
	/**
	 * 根据ID删除一个车辆
	 * @param id
	 */
	public void deleteCsCarById(Long id)
	{
		csCarDao.deleteCsCarById(id);
	}
	/**
	 * 根据ID逻辑删除一个车辆
	 * @param id
	 */
	public void removeCsCarById(Long id)
	{
		csCarDao.removeCsCarById(id);
	}
	
	/**
	 * 根据条件更新车辆
	 * @param values
	 * @param params
	 */
	public void updateCsCarByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsCarBy时无条件");
		csCarDao.updateCsCarByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除车辆
	 * @param params
	 */
	public void deleteCsCarByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsCarBy时无条件");
		csCarDao.deleteCsCarByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsCarDao getCsCarDao()
	{
		return csCarDao;
	}

	public void setCsCarDao(ICsCarDao csCarDao)
	{
		this.csCarDao = csCarDao;
	}
}