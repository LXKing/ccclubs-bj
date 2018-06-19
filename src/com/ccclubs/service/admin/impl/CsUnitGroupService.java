package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsUnitGroupDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnitGroup;
import com.ccclubs.service.admin.ICsUnitGroupService;
import com.lazy3q.web.helper.$;

/**
 * 分组信息的Service实现
 * @author 飞啊飘啊
 */
public class CsUnitGroupService implements ICsUnitGroupService
{
	ICsUnitGroupDao csUnitGroupDao;
	

	/**
	 * 获取所有分组信息
	 * @return
	 */
	public List<CsUnitGroup> getCsUnitGroupList(Map params,Integer size)
	{
		return csUnitGroupDao.getCsUnitGroupList(params,size);
	}
	
	/**
	 * 获取分组信息统计
	 * @return
	 */
	public List getCsUnitGroupStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csUnitGroupDao.getCsUnitGroupStats(params,groups,sums);
	}
	
	/**
	 * 获取分组信息总数
	 * @return
	 */
	public Long getCsUnitGroupCount(Map params)
	{
		return csUnitGroupDao.getCsUnitGroupCount(params);
	}
	
	/**
	 * 获取分组信息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUnitGroupEval(String eval,Map params)
	{
		return csUnitGroupDao.getCsUnitGroupEval(eval,params);
	}

	/**
	 * 获取分组信息分页
	 * @return
	 */
	public Page getCsUnitGroupPage(int page,int size,Map params)
	{
		return csUnitGroupDao.getCsUnitGroupPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取分组信息
	 * @param params
	 * @return
	 */
	public CsUnitGroup getCsUnitGroup(Map params)
	{
		return csUnitGroupDao.getCsUnitGroup(params);
	}

	/**
	 * 根据ID取分组信息
	 * @param id
	 * @return
	 */
	public CsUnitGroup getCsUnitGroupById(Long id)
	{
		return csUnitGroupDao.getCsUnitGroupById(id);
	}

	/**
	 * 保存分组信息
	 * @param csUnitGroup
	 */
	public CsUnitGroup saveCsUnitGroup(CsUnitGroup csUnitGroup)
	{
		return csUnitGroupDao.saveCsUnitGroup(csUnitGroup);
	}
	
	/**
	 * 更新分组信息
	 * @param csUnitGroup
	 */
	public void updateCsUnitGroup(CsUnitGroup csUnitGroup)
	{
		csUnitGroupDao.updateCsUnitGroup(csUnitGroup);
	}
	/**
	 * 更新分组信息非空字段
	 * @param csUnitGroup
	 */
	public void updateCsUnitGroup$NotNull(CsUnitGroup csUnitGroup)
	{
		csUnitGroupDao.updateCsUnitGroup$NotNull(csUnitGroup);
	}
	
	/**
	 * 根据ID删除一个分组信息
	 * @param id
	 */
	public void deleteCsUnitGroupById(Long id)
	{
		csUnitGroupDao.deleteCsUnitGroupById(id);
	}
	/**
	 * 根据ID逻辑删除一个分组信息
	 * @param id
	 */
	public void removeCsUnitGroupById(Long id)
	{
		csUnitGroupDao.removeCsUnitGroupById(id);
	}
	
	/**
	 * 根据条件更新分组信息
	 * @param values
	 * @param params
	 */
	public void updateCsUnitGroupByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsUnitGroupBy时无条件");
		csUnitGroupDao.updateCsUnitGroupByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除分组信息
	 * @param params
	 */
	public void deleteCsUnitGroupByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsUnitGroupBy时无条件");
		csUnitGroupDao.deleteCsUnitGroupByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsUnitGroupDao getCsUnitGroupDao()
	{
		return csUnitGroupDao;
	}

	public void setCsUnitGroupDao(ICsUnitGroupDao csUnitGroupDao)
	{
		this.csUnitGroupDao = csUnitGroupDao;
	}
}