package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsGrowTypeDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsGrowType;
import com.ccclubs.service.admin.ICsGrowTypeService;
import com.lazy3q.web.helper.$;

/**
 * 成长分类型的Service实现
 * @author 飞啊飘啊
 */
public class CsGrowTypeService implements ICsGrowTypeService
{
	ICsGrowTypeDao csGrowTypeDao;
	

	/**
	 * 获取所有成长分类型
	 * @return
	 */
	public List<CsGrowType> getCsGrowTypeList(Map params,Integer size)
	{
		return csGrowTypeDao.getCsGrowTypeList(params,size);
	}
	
	/**
	 * 获取成长分类型统计
	 * @return
	 */
	public List getCsGrowTypeStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csGrowTypeDao.getCsGrowTypeStats(params,groups,sums);
	}
	
	/**
	 * 获取成长分类型总数
	 * @return
	 */
	public Long getCsGrowTypeCount(Map params)
	{
		return csGrowTypeDao.getCsGrowTypeCount(params);
	}
	
	/**
	 * 获取成长分类型自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsGrowTypeEval(String eval,Map params)
	{
		return csGrowTypeDao.getCsGrowTypeEval(eval,params);
	}

	/**
	 * 获取成长分类型分页
	 * @return
	 */
	public Page getCsGrowTypePage(int page,int size,Map params)
	{
		return csGrowTypeDao.getCsGrowTypePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取成长分类型
	 * @param params
	 * @return
	 */
	public CsGrowType getCsGrowType(Map params)
	{
		return csGrowTypeDao.getCsGrowType(params);
	}

	/**
	 * 根据ID取成长分类型
	 * @param id
	 * @return
	 */
	public CsGrowType getCsGrowTypeById(Long id)
	{
		return csGrowTypeDao.getCsGrowTypeById(id);
	}

	/**
	 * 保存成长分类型
	 * @param csGrowType
	 */
	public CsGrowType saveCsGrowType(CsGrowType csGrowType)
	{
		return csGrowTypeDao.saveCsGrowType(csGrowType);
	}
	
	/**
	 * 更新成长分类型
	 * @param csGrowType
	 */
	public void updateCsGrowType(CsGrowType csGrowType)
	{
		csGrowTypeDao.updateCsGrowType(csGrowType);
	}
	/**
	 * 更新成长分类型非空字段
	 * @param csGrowType
	 */
	public void updateCsGrowType$NotNull(CsGrowType csGrowType)
	{
		csGrowTypeDao.updateCsGrowType$NotNull(csGrowType);
	}
	
	/**
	 * 根据ID删除一个成长分类型
	 * @param id
	 */
	public void deleteCsGrowTypeById(Long id)
	{
		csGrowTypeDao.deleteCsGrowTypeById(id);
	}
	/**
	 * 根据ID逻辑删除一个成长分类型
	 * @param id
	 */
	public void removeCsGrowTypeById(Long id)
	{
		csGrowTypeDao.removeCsGrowTypeById(id);
	}
	
	/**
	 * 根据条件更新成长分类型
	 * @param values
	 * @param params
	 */
	public void updateCsGrowTypeByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsGrowTypeBy时无条件");
		csGrowTypeDao.updateCsGrowTypeByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除成长分类型
	 * @param params
	 */
	public void deleteCsGrowTypeByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsGrowTypeBy时无条件");
		csGrowTypeDao.deleteCsGrowTypeByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsGrowTypeDao getCsGrowTypeDao()
	{
		return csGrowTypeDao;
	}

	public void setCsGrowTypeDao(ICsGrowTypeDao csGrowTypeDao)
	{
		this.csGrowTypeDao = csGrowTypeDao;
	}
}