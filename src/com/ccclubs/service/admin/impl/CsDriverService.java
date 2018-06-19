package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsDriverDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsDriver;
import com.ccclubs.service.admin.ICsDriverService;
import com.lazy3q.web.helper.$;

/**
 * 专车司机的Service实现
 * @author Joel
 */
public class CsDriverService implements ICsDriverService
{
	ICsDriverDao csDriverDao;
	

	/**
	 * 获取所有专车司机
	 * @return
	 */
	public List<CsDriver> getCsDriverList(Map params,Integer size)
	{
		return csDriverDao.getCsDriverList(params,size);
	}
	
	/**
	 * 获取专车司机统计
	 * @return
	 */
	public List getCsDriverStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csDriverDao.getCsDriverStats(params,groups,sums);
	}
	
	/**
	 * 获取专车司机总数
	 * @return
	 */
	public Long getCsDriverCount(Map params)
	{
		return csDriverDao.getCsDriverCount(params);
	}
	
	/**
	 * 获取专车司机自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsDriverEval(String eval,Map params)
	{
		return csDriverDao.getCsDriverEval(eval,params);
	}

	/**
	 * 获取专车司机分页
	 * @return
	 */
	public Page getCsDriverPage(int page,int size,Map params)
	{
		return csDriverDao.getCsDriverPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取专车司机
	 * @param params
	 * @return
	 */
	public CsDriver getCsDriver(Map params)
	{
		return csDriverDao.getCsDriver(params);
	}

	/**
	 * 根据ID取专车司机
	 * @param id
	 * @return
	 */
	public CsDriver getCsDriverById(Long id)
	{
		return csDriverDao.getCsDriverById(id);
	}

	/**
	 * 保存专车司机
	 * @param csDriver
	 */
	public CsDriver saveCsDriver(CsDriver csDriver)
	{
		return csDriverDao.saveCsDriver(csDriver);
	}
	
	/**
	 * 更新专车司机
	 * @param csDriver
	 */
	public void updateCsDriver(CsDriver csDriver)
	{
		csDriverDao.updateCsDriver(csDriver);
	}
	/**
	 * 更新专车司机非空字段
	 * @param csDriver
	 */
	public void updateCsDriver$NotNull(CsDriver csDriver)
	{
		csDriverDao.updateCsDriver$NotNull(csDriver);
	}
	
	/**
	 * 根据ID删除一个专车司机
	 * @param id
	 */
	public void deleteCsDriverById(Long id)
	{
		csDriverDao.deleteCsDriverById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个专车司机
	 * @param id
	 */
	public void removeCsDriverById(Long id)
	{
		csDriverDao.removeCsDriverById(id);
	}
	
	/**
	 * 根据条件更新专车司机
	 * @param values
	 * @param params
	 */
	public void updateCsDriverByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsDriverBy时无条件");
		csDriverDao.updateCsDriverByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除专车司机
	 * @param params
	 */
	public void deleteCsDriverByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsDriverBy时无条件");
		csDriverDao.deleteCsDriverByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsDriverDao getCsDriverDao()
	{
		return csDriverDao;
	}

	public void setCsDriverDao(ICsDriverDao csDriverDao)
	{
		this.csDriverDao = csDriverDao;
	}
}