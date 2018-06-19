package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsUserTypeDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUserType;
import com.ccclubs.service.admin.ICsUserTypeService;
import com.lazy3q.web.helper.$;

/**
 * 计费方式的Service实现
 * @author 飞啊飘啊
 */
public class CsUserTypeService implements ICsUserTypeService
{
	ICsUserTypeDao csUserTypeDao;
	

	/**
	 * 获取所有计费方式
	 * @return
	 */
	public List<CsUserType> getCsUserTypeList(Map params,Integer size)
	{
		return csUserTypeDao.getCsUserTypeList(params,size);
	}
	
	/**
	 * 获取计费方式统计
	 * @return
	 */
	public List getCsUserTypeStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csUserTypeDao.getCsUserTypeStats(params,groups,sums);
	}
	
	/**
	 * 获取计费方式总数
	 * @return
	 */
	public Long getCsUserTypeCount(Map params)
	{
		return csUserTypeDao.getCsUserTypeCount(params);
	}
	
	/**
	 * 获取计费方式自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUserTypeEval(String eval,Map params)
	{
		return csUserTypeDao.getCsUserTypeEval(eval,params);
	}

	/**
	 * 获取计费方式分页
	 * @return
	 */
	public Page getCsUserTypePage(int page,int size,Map params)
	{
		return csUserTypeDao.getCsUserTypePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取计费方式
	 * @param params
	 * @return
	 */
	public CsUserType getCsUserType(Map params)
	{
		return csUserTypeDao.getCsUserType(params);
	}

	/**
	 * 根据ID取计费方式
	 * @param id
	 * @return
	 */
	public CsUserType getCsUserTypeById(Long id)
	{
		return csUserTypeDao.getCsUserTypeById(id);
	}

	/**
	 * 保存计费方式
	 * @param csUserType
	 */
	public CsUserType saveCsUserType(CsUserType csUserType)
	{
		return csUserTypeDao.saveCsUserType(csUserType);
	}
	
	/**
	 * 更新计费方式
	 * @param csUserType
	 */
	public void updateCsUserType(CsUserType csUserType)
	{
		csUserTypeDao.updateCsUserType(csUserType);
	}
	/**
	 * 更新计费方式非空字段
	 * @param csUserType
	 */
	public void updateCsUserType$NotNull(CsUserType csUserType)
	{
		csUserTypeDao.updateCsUserType$NotNull(csUserType);
	}
	
	/**
	 * 根据ID删除一个计费方式
	 * @param id
	 */
	public void deleteCsUserTypeById(Long id)
	{
		csUserTypeDao.deleteCsUserTypeById(id);
	}
	/**
	 * 根据ID逻辑删除一个计费方式
	 * @param id
	 */
	public void removeCsUserTypeById(Long id)
	{
		csUserTypeDao.removeCsUserTypeById(id);
	}
	
	/**
	 * 根据条件更新计费方式
	 * @param values
	 * @param params
	 */
	public void updateCsUserTypeByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsUserTypeBy时无条件");
		csUserTypeDao.updateCsUserTypeByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除计费方式
	 * @param params
	 */
	public void deleteCsUserTypeByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsUserTypeBy时无条件");
		csUserTypeDao.deleteCsUserTypeByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsUserTypeDao getCsUserTypeDao()
	{
		return csUserTypeDao;
	}

	public void setCsUserTypeDao(ICsUserTypeDao csUserTypeDao)
	{
		this.csUserTypeDao = csUserTypeDao;
	}
}