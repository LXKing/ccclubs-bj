package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsMemberGroupDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMemberGroup;
import com.ccclubs.service.admin.ICsMemberGroupService;
import com.lazy3q.web.helper.$;

/**
 * 会员组织的Service实现
 * @author 飞啊飘啊
 */
public class CsMemberGroupService implements ICsMemberGroupService
{
	ICsMemberGroupDao csMemberGroupDao;
	

	/**
	 * 获取所有会员组织
	 * @return
	 */
	public List<CsMemberGroup> getCsMemberGroupList(Map params,Integer size)
	{
		return csMemberGroupDao.getCsMemberGroupList(params,size);
	}
	
	/**
	 * 获取会员组织统计
	 * @return
	 */
	public List getCsMemberGroupStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csMemberGroupDao.getCsMemberGroupStats(params,groups,sums);
	}
	
	/**
	 * 获取会员组织总数
	 * @return
	 */
	public Long getCsMemberGroupCount(Map params)
	{
		return csMemberGroupDao.getCsMemberGroupCount(params);
	}
	
	/**
	 * 获取会员组织自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMemberGroupEval(String eval,Map params)
	{
		return csMemberGroupDao.getCsMemberGroupEval(eval,params);
	}

	/**
	 * 获取会员组织分页
	 * @return
	 */
	public Page getCsMemberGroupPage(int page,int size,Map params)
	{
		return csMemberGroupDao.getCsMemberGroupPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取会员组织
	 * @param params
	 * @return
	 */
	public CsMemberGroup getCsMemberGroup(Map params)
	{
		return csMemberGroupDao.getCsMemberGroup(params);
	}

	/**
	 * 根据ID取会员组织
	 * @param id
	 * @return
	 */
	public CsMemberGroup getCsMemberGroupById(Long id)
	{
		return csMemberGroupDao.getCsMemberGroupById(id);
	}

	/**
	 * 保存会员组织
	 * @param csMemberGroup
	 */
	public CsMemberGroup saveCsMemberGroup(CsMemberGroup csMemberGroup)
	{
		return csMemberGroupDao.saveCsMemberGroup(csMemberGroup);
	}
	
	/**
	 * 更新会员组织
	 * @param csMemberGroup
	 */
	public void updateCsMemberGroup(CsMemberGroup csMemberGroup)
	{
		csMemberGroupDao.updateCsMemberGroup(csMemberGroup);
	}
	/**
	 * 更新会员组织非空字段
	 * @param csMemberGroup
	 */
	public void updateCsMemberGroup$NotNull(CsMemberGroup csMemberGroup)
	{
		csMemberGroupDao.updateCsMemberGroup$NotNull(csMemberGroup);
	}
	
	/**
	 * 根据ID删除一个会员组织
	 * @param id
	 */
	public void deleteCsMemberGroupById(Long id)
	{
		csMemberGroupDao.deleteCsMemberGroupById(id);
	}
	/**
	 * 根据ID逻辑删除一个会员组织
	 * @param id
	 */
	public void removeCsMemberGroupById(Long id)
	{
		csMemberGroupDao.removeCsMemberGroupById(id);
	}
	
	/**
	 * 根据条件更新会员组织
	 * @param values
	 * @param params
	 */
	public void updateCsMemberGroupByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsMemberGroupBy时无条件");
		csMemberGroupDao.updateCsMemberGroupByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除会员组织
	 * @param params
	 */
	public void deleteCsMemberGroupByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsMemberGroupBy时无条件");
		csMemberGroupDao.deleteCsMemberGroupByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsMemberGroupDao getCsMemberGroupDao()
	{
		return csMemberGroupDao;
	}

	public void setCsMemberGroupDao(ICsMemberGroupDao csMemberGroupDao)
	{
		this.csMemberGroupDao = csMemberGroupDao;
	}
}