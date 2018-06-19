package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsMemberDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMember;
import com.ccclubs.service.admin.ICsMemberService;
import com.lazy3q.web.helper.$;

/**
 * 会员帐号的Service实现
 * @author 飞啊飘啊
 */
public class CsMemberService implements ICsMemberService
{
	ICsMemberDao csMemberDao;
	

	/**
	 * 获取所有会员帐号
	 * @return
	 */
	public List<CsMember> getCsMemberList(Map params,Integer size)
	{
		return csMemberDao.getCsMemberList(params,size);
	}
	
	/**
	 * 获取会员帐号统计
	 * @return
	 */
	public List getCsMemberStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csMemberDao.getCsMemberStats(params,groups,sums);
	}
	
	/**
	 * 获取会员帐号总数
	 * @return
	 */
	public Long getCsMemberCount(Map params)
	{
		return csMemberDao.getCsMemberCount(params);
	}
	
	/**
	 * 获取会员帐号自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMemberEval(String eval,Map params)
	{
		return csMemberDao.getCsMemberEval(eval,params);
	}

	/**
	 * 获取会员帐号分页
	 * @return
	 */
	public Page getCsMemberPage(int page,int size,Map params)
	{
		return csMemberDao.getCsMemberPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取会员帐号
	 * @param params
	 * @return
	 */
	public CsMember getCsMember(Map params)
	{
		return csMemberDao.getCsMember(params);
	}

	/**
	 * 根据ID取会员帐号
	 * @param id
	 * @return
	 */
	public CsMember getCsMemberById(Long id)
	{
		return csMemberDao.getCsMemberById(id);
	}

	/**
	 * 保存会员帐号
	 * @param csMember
	 */
	public CsMember saveCsMember(CsMember csMember)
	{
		return csMemberDao.saveCsMember(csMember);
	}
	
	/**
	 * 更新会员帐号
	 * @param csMember
	 */
	public void updateCsMember(CsMember csMember)
	{
		csMemberDao.updateCsMember(csMember);
	}
	/**
	 * 更新会员帐号非空字段
	 * @param csMember
	 */
	public void updateCsMember$NotNull(CsMember csMember)
	{
		csMemberDao.updateCsMember$NotNull(csMember);
	}
	
	/**
	 * 根据ID删除一个会员帐号
	 * @param id
	 */
	public void deleteCsMemberById(Long id)
	{
		csMemberDao.deleteCsMemberById(id);
	}
	/**
	 * 根据ID逻辑删除一个会员帐号
	 * @param id
	 */
	public void removeCsMemberById(Long id)
	{
		csMemberDao.removeCsMemberById(id);
	}
	
	/**
	 * 根据条件更新会员帐号
	 * @param values
	 * @param params
	 */
	public void updateCsMemberByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsMemberBy时无条件");
		csMemberDao.updateCsMemberByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除会员帐号
	 * @param params
	 */
	public void deleteCsMemberByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsMemberBy时无条件");
		csMemberDao.deleteCsMemberByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsMemberDao getCsMemberDao()
	{
		return csMemberDao;
	}

	public void setCsMemberDao(ICsMemberDao csMemberDao)
	{
		this.csMemberDao = csMemberDao;
	}
}