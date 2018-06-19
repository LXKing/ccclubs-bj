package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsSpecialCarDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsSpecialCar;
import com.ccclubs.service.admin.ICsSpecialCarService;
import com.lazy3q.web.helper.$;

/**
 * 专车订单的Service实现
 * @author Joel
 */
public class CsSpecialCarService implements ICsSpecialCarService
{
	ICsSpecialCarDao csSpecialCarDao;
	

	/**
	 * 获取所有专车订单
	 * @return
	 */
	public List<CsSpecialCar> getCsSpecialCarList(Map params,Integer size)
	{
		return csSpecialCarDao.getCsSpecialCarList(params,size);
	}
	
	/**
	 * 获取专车订单统计
	 * @return
	 */
	public List getCsSpecialCarStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csSpecialCarDao.getCsSpecialCarStats(params,groups,sums);
	}
	
	/**
	 * 获取专车订单总数
	 * @return
	 */
	public Long getCsSpecialCarCount(Map params)
	{
		return csSpecialCarDao.getCsSpecialCarCount(params);
	}
	
	/**
	 * 获取专车订单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsSpecialCarEval(String eval,Map params)
	{
		return csSpecialCarDao.getCsSpecialCarEval(eval,params);
	}

	/**
	 * 获取专车订单分页
	 * @return
	 */
	public Page getCsSpecialCarPage(int page,int size,Map params)
	{
		return csSpecialCarDao.getCsSpecialCarPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取专车订单
	 * @param params
	 * @return
	 */
	public CsSpecialCar getCsSpecialCar(Map params)
	{
		return csSpecialCarDao.getCsSpecialCar(params);
	}

	/**
	 * 根据ID取专车订单
	 * @param id
	 * @return
	 */
	public CsSpecialCar getCsSpecialCarById(Long id)
	{
		return csSpecialCarDao.getCsSpecialCarById(id);
	}

	/**
	 * 保存专车订单
	 * @param csSpecialCar
	 */
	public CsSpecialCar saveCsSpecialCar(CsSpecialCar csSpecialCar)
	{
		return csSpecialCarDao.saveCsSpecialCar(csSpecialCar);
	}
	
	/**
	 * 更新专车订单
	 * @param csSpecialCar
	 */
	public void updateCsSpecialCar(CsSpecialCar csSpecialCar)
	{
		csSpecialCarDao.updateCsSpecialCar(csSpecialCar);
	}
	/**
	 * 更新专车订单非空字段
	 * @param csSpecialCar
	 */
	public void updateCsSpecialCar$NotNull(CsSpecialCar csSpecialCar)
	{
		csSpecialCarDao.updateCsSpecialCar$NotNull(csSpecialCar);
	}
	
	/**
	 * 根据ID删除一个专车订单
	 * @param id
	 */
	public void deleteCsSpecialCarById(Long id)
	{
		csSpecialCarDao.deleteCsSpecialCarById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个专车订单
	 * @param id
	 */
	public void removeCsSpecialCarById(Long id)
	{
		csSpecialCarDao.removeCsSpecialCarById(id);
	}
	
	/**
	 * 根据条件更新专车订单
	 * @param values
	 * @param params
	 */
	public void updateCsSpecialCarByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsSpecialCarBy时无条件");
		csSpecialCarDao.updateCsSpecialCarByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除专车订单
	 * @param params
	 */
	public void deleteCsSpecialCarByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsSpecialCarBy时无条件");
		csSpecialCarDao.deleteCsSpecialCarByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsSpecialCarDao getCsSpecialCarDao()
	{
		return csSpecialCarDao;
	}

	public void setCsSpecialCarDao(ICsSpecialCarDao csSpecialCarDao)
	{
		this.csSpecialCarDao = csSpecialCarDao;
	}
}