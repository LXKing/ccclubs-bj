package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsUnderlineMemberDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnderlineMember;
import com.ccclubs.service.admin.ICsUnderlineMemberService;
import com.lazy3q.web.helper.$;

/**
 * 线下人员管理的Service实现
 * @author Joel
 */
public class CsUnderlineMemberService implements ICsUnderlineMemberService
{
	ICsUnderlineMemberDao csUnderlineMemberDao;
	

	/**
	 * 获取所有线下人员管理
	 * @return
	 */
	public List<CsUnderlineMember> getCsUnderlineMemberList(Map params,Integer size)
	{
		return csUnderlineMemberDao.getCsUnderlineMemberList(params,size);
	}
	
	/**
	 * 获取线下人员管理统计
	 * @return
	 */
	public List getCsUnderlineMemberStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csUnderlineMemberDao.getCsUnderlineMemberStats(params,groups,sums);
	}
	
	/**
	 * 获取线下人员管理总数
	 * @return
	 */
	public Long getCsUnderlineMemberCount(Map params)
	{
		return csUnderlineMemberDao.getCsUnderlineMemberCount(params);
	}
	
	/**
	 * 获取线下人员管理自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUnderlineMemberEval(String eval,Map params)
	{
		return csUnderlineMemberDao.getCsUnderlineMemberEval(eval,params);
	}

	/**
	 * 获取线下人员管理分页
	 * @return
	 */
	public Page getCsUnderlineMemberPage(int page,int size,Map params)
	{
		return csUnderlineMemberDao.getCsUnderlineMemberPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取线下人员管理
	 * @param params
	 * @return
	 */
	public CsUnderlineMember getCsUnderlineMember(Map params)
	{
		return csUnderlineMemberDao.getCsUnderlineMember(params);
	}

	/**
	 * 根据ID取线下人员管理
	 * @param id
	 * @return
	 */
	public CsUnderlineMember getCsUnderlineMemberById(Long id)
	{
		return csUnderlineMemberDao.getCsUnderlineMemberById(id);
	}

	/**
	 * 保存线下人员管理
	 * @param csUnderlineMember
	 */
	public CsUnderlineMember saveCsUnderlineMember(CsUnderlineMember csUnderlineMember)
	{
		return csUnderlineMemberDao.saveCsUnderlineMember(csUnderlineMember);
	}
	
	/**
	 * 更新线下人员管理
	 * @param csUnderlineMember
	 */
	public void updateCsUnderlineMember(CsUnderlineMember csUnderlineMember)
	{
		csUnderlineMemberDao.updateCsUnderlineMember(csUnderlineMember);
	}
	/**
	 * 更新线下人员管理非空字段
	 * @param csUnderlineMember
	 */
	public void updateCsUnderlineMember$NotNull(CsUnderlineMember csUnderlineMember)
	{
		csUnderlineMemberDao.updateCsUnderlineMember$NotNull(csUnderlineMember);
	}
	
	/**
	 * 根据ID删除一个线下人员管理
	 * @param id
	 */
	public void deleteCsUnderlineMemberById(Long id)
	{
		csUnderlineMemberDao.deleteCsUnderlineMemberById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个线下人员管理
	 * @param id
	 */
	public void removeCsUnderlineMemberById(Long id)
	{
		csUnderlineMemberDao.removeCsUnderlineMemberById(id);
	}
	
	/**
	 * 根据条件更新线下人员管理
	 * @param values
	 * @param params
	 */
	public void updateCsUnderlineMemberByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsUnderlineMemberBy时无条件");
		csUnderlineMemberDao.updateCsUnderlineMemberByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除线下人员管理
	 * @param params
	 */
	public void deleteCsUnderlineMemberByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsUnderlineMemberBy时无条件");
		csUnderlineMemberDao.deleteCsUnderlineMemberByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsUnderlineMemberDao getCsUnderlineMemberDao()
	{
		return csUnderlineMemberDao;
	}

	public void setCsUnderlineMemberDao(ICsUnderlineMemberDao csUnderlineMemberDao)
	{
		this.csUnderlineMemberDao = csUnderlineMemberDao;
	}
}