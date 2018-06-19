package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsUnitPersonDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnitPerson;
import com.ccclubs.service.admin.ICsUnitPersonService;
import com.lazy3q.web.helper.$;

/**
 * 用车人员的Service实现
 * @author Joel
 */
public class CsUnitPersonService implements ICsUnitPersonService
{
	ICsUnitPersonDao csUnitPersonDao;
	

	/**
	 * 获取所有用车人员
	 * @return
	 */
	public List<CsUnitPerson> getCsUnitPersonList(Map params,Integer size)
	{
		return csUnitPersonDao.getCsUnitPersonList(params,size);
	}
	
	/**
	 * 获取用车人员统计
	 * @return
	 */
	public List getCsUnitPersonStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csUnitPersonDao.getCsUnitPersonStats(params,groups,sums);
	}
	
	/**
	 * 获取用车人员总数
	 * @return
	 */
	public Long getCsUnitPersonCount(Map params)
	{
		return csUnitPersonDao.getCsUnitPersonCount(params);
	}
	
	/**
	 * 获取用车人员自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUnitPersonEval(String eval,Map params)
	{
		return csUnitPersonDao.getCsUnitPersonEval(eval,params);
	}

	/**
	 * 获取用车人员分页
	 * @return
	 */
	public Page getCsUnitPersonPage(int page,int size,Map params)
	{
		return csUnitPersonDao.getCsUnitPersonPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取用车人员
	 * @param params
	 * @return
	 */
	public CsUnitPerson getCsUnitPerson(Map params)
	{
		return csUnitPersonDao.getCsUnitPerson(params);
	}

	/**
	 * 根据ID取用车人员
	 * @param id
	 * @return
	 */
	public CsUnitPerson getCsUnitPersonById(Long id)
	{
		return csUnitPersonDao.getCsUnitPersonById(id);
	}

	/**
	 * 保存用车人员
	 * @param csUnitPerson
	 */
	public CsUnitPerson saveCsUnitPerson(CsUnitPerson csUnitPerson)
	{
		return csUnitPersonDao.saveCsUnitPerson(csUnitPerson);
	}
	
	/**
	 * 更新用车人员
	 * @param csUnitPerson
	 */
	public void updateCsUnitPerson(CsUnitPerson csUnitPerson)
	{
		csUnitPersonDao.updateCsUnitPerson(csUnitPerson);
	}
	/**
	 * 更新用车人员非空字段
	 * @param csUnitPerson
	 */
	public void updateCsUnitPerson$NotNull(CsUnitPerson csUnitPerson)
	{
		csUnitPersonDao.updateCsUnitPerson$NotNull(csUnitPerson);
	}
	
	/**
	 * 根据ID删除一个用车人员
	 * @param id
	 */
	public void deleteCsUnitPersonById(Long id)
	{
		csUnitPersonDao.deleteCsUnitPersonById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个用车人员
	 * @param id
	 */
	public void removeCsUnitPersonById(Long id)
	{
		csUnitPersonDao.removeCsUnitPersonById(id);
	}
	
	/**
	 * 根据条件更新用车人员
	 * @param values
	 * @param params
	 */
	public void updateCsUnitPersonByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsUnitPersonBy时无条件");
		csUnitPersonDao.updateCsUnitPersonByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除用车人员
	 * @param params
	 */
	public void deleteCsUnitPersonByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsUnitPersonBy时无条件");
		csUnitPersonDao.deleteCsUnitPersonByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsUnitPersonDao getCsUnitPersonDao()
	{
		return csUnitPersonDao;
	}

	public void setCsUnitPersonDao(ICsUnitPersonDao csUnitPersonDao)
	{
		this.csUnitPersonDao = csUnitPersonDao;
	}
}