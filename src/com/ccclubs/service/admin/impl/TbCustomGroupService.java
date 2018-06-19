package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ITbCustomGroupDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbCustomGroup;
import com.ccclubs.service.admin.ITbCustomGroupService;
import com.lazy3q.web.helper.$;

/**
 * 客户组织的Service实现
 * @author 飞啊飘啊
 */
public class TbCustomGroupService implements ITbCustomGroupService
{
	ITbCustomGroupDao tbCustomGroupDao;
	

	/**
	 * 获取所有客户组织
	 * @return
	 */
	public List<TbCustomGroup> getTbCustomGroupList(Map params,Integer size)
	{
		return tbCustomGroupDao.getTbCustomGroupList(params,size);
	}
	
	/**
	 * 获取客户组织统计
	 * @return
	 */
	public List getTbCustomGroupStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return tbCustomGroupDao.getTbCustomGroupStats(params,groups,sums);
	}
	
	/**
	 * 获取客户组织总数
	 * @return
	 */
	public Long getTbCustomGroupCount(Map params)
	{
		return tbCustomGroupDao.getTbCustomGroupCount(params);
	}
	
	/**
	 * 获取客户组织自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbCustomGroupEval(String eval,Map params)
	{
		return tbCustomGroupDao.getTbCustomGroupEval(eval,params);
	}

	/**
	 * 获取客户组织分页
	 * @return
	 */
	public Page getTbCustomGroupPage(int page,int size,Map params)
	{
		return tbCustomGroupDao.getTbCustomGroupPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取客户组织
	 * @param params
	 * @return
	 */
	public TbCustomGroup getTbCustomGroup(Map params)
	{
		return tbCustomGroupDao.getTbCustomGroup(params);
	}

	/**
	 * 根据ID取客户组织
	 * @param id
	 * @return
	 */
	public TbCustomGroup getTbCustomGroupById(Long id)
	{
		return tbCustomGroupDao.getTbCustomGroupById(id);
	}

	/**
	 * 保存客户组织
	 * @param tbCustomGroup
	 */
	public TbCustomGroup saveTbCustomGroup(TbCustomGroup tbCustomGroup)
	{
		return tbCustomGroupDao.saveTbCustomGroup(tbCustomGroup);
	}
	
	/**
	 * 更新客户组织
	 * @param tbCustomGroup
	 */
	public void updateTbCustomGroup(TbCustomGroup tbCustomGroup)
	{
		tbCustomGroupDao.updateTbCustomGroup(tbCustomGroup);
	}
	/**
	 * 更新客户组织非空字段
	 * @param tbCustomGroup
	 */
	public void updateTbCustomGroup$NotNull(TbCustomGroup tbCustomGroup)
	{
		tbCustomGroupDao.updateTbCustomGroup$NotNull(tbCustomGroup);
	}
	
	/**
	 * 根据ID删除一个客户组织
	 * @param id
	 */
	public void deleteTbCustomGroupById(Long id)
	{
		tbCustomGroupDao.deleteTbCustomGroupById(id);
	}
	/**
	 * 根据ID逻辑删除一个客户组织
	 * @param id
	 */
	public void removeTbCustomGroupById(Long id)
	{
		tbCustomGroupDao.removeTbCustomGroupById(id);
	}
	
	/**
	 * 根据条件更新客户组织
	 * @param values
	 * @param params
	 */
	public void updateTbCustomGroupByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateTbCustomGroupBy时无条件");
		tbCustomGroupDao.updateTbCustomGroupByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除客户组织
	 * @param params
	 */
	public void deleteTbCustomGroupByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteTbCustomGroupBy时无条件");
		tbCustomGroupDao.deleteTbCustomGroupByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ITbCustomGroupDao getTbCustomGroupDao()
	{
		return tbCustomGroupDao;
	}

	public void setTbCustomGroupDao(ITbCustomGroupDao tbCustomGroupDao)
	{
		this.tbCustomGroupDao = tbCustomGroupDao;
	}
}