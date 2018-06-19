package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsCarSysIssueDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCarSysIssue;
import com.ccclubs.service.admin.ICsCarSysIssueService;
import com.lazy3q.web.helper.$;

/**
 * 系统车况报告的Service实现
 * @author Joel
 */
public class CsCarSysIssueService implements ICsCarSysIssueService
{
	ICsCarSysIssueDao csCarSysIssueDao;
	

	/**
	 * 获取所有系统车况报告
	 * @return
	 */
	public List<CsCarSysIssue> getCsCarSysIssueList(Map params,Integer size)
	{
		return csCarSysIssueDao.getCsCarSysIssueList(params,size);
	}
	
	/**
	 * 获取系统车况报告统计
	 * @return
	 */
	public List getCsCarSysIssueStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csCarSysIssueDao.getCsCarSysIssueStats(params,groups,sums);
	}
	
	/**
	 * 获取系统车况报告总数
	 * @return
	 */
	public Long getCsCarSysIssueCount(Map params)
	{
		return csCarSysIssueDao.getCsCarSysIssueCount(params);
	}
	
	/**
	 * 获取系统车况报告自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCarSysIssueEval(String eval,Map params)
	{
		return csCarSysIssueDao.getCsCarSysIssueEval(eval,params);
	}

	/**
	 * 获取系统车况报告分页
	 * @return
	 */
	public Page getCsCarSysIssuePage(int page,int size,Map params)
	{
		return csCarSysIssueDao.getCsCarSysIssuePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取系统车况报告
	 * @param params
	 * @return
	 */
	public CsCarSysIssue getCsCarSysIssue(Map params)
	{
		return csCarSysIssueDao.getCsCarSysIssue(params);
	}

	/**
	 * 根据ID取系统车况报告
	 * @param id
	 * @return
	 */
	public CsCarSysIssue getCsCarSysIssueById(Long id)
	{
		return csCarSysIssueDao.getCsCarSysIssueById(id);
	}

	/**
	 * 保存系统车况报告
	 * @param csCarSysIssue
	 */
	public CsCarSysIssue saveCsCarSysIssue(CsCarSysIssue csCarSysIssue)
	{
		return csCarSysIssueDao.saveCsCarSysIssue(csCarSysIssue);
	}
	
	/**
	 * 更新系统车况报告
	 * @param csCarSysIssue
	 */
	public void updateCsCarSysIssue(CsCarSysIssue csCarSysIssue)
	{
		csCarSysIssueDao.updateCsCarSysIssue(csCarSysIssue);
	}
	/**
	 * 更新系统车况报告非空字段
	 * @param csCarSysIssue
	 */
	public void updateCsCarSysIssue$NotNull(CsCarSysIssue csCarSysIssue)
	{
		csCarSysIssueDao.updateCsCarSysIssue$NotNull(csCarSysIssue);
	}
	
	/**
	 * 根据ID删除一个系统车况报告
	 * @param id
	 */
	public void deleteCsCarSysIssueById(Long id)
	{
		csCarSysIssueDao.deleteCsCarSysIssueById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个系统车况报告
	 * @param id
	 */
	public void removeCsCarSysIssueById(Long id)
	{
		csCarSysIssueDao.removeCsCarSysIssueById(id);
	}
	
	/**
	 * 根据条件更新系统车况报告
	 * @param values
	 * @param params
	 */
	public void updateCsCarSysIssueByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsCarSysIssueBy时无条件");
		csCarSysIssueDao.updateCsCarSysIssueByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除系统车况报告
	 * @param params
	 */
	public void deleteCsCarSysIssueByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsCarSysIssueBy时无条件");
		csCarSysIssueDao.deleteCsCarSysIssueByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsCarSysIssueDao getCsCarSysIssueDao()
	{
		return csCarSysIssueDao;
	}

	public void setCsCarSysIssueDao(ICsCarSysIssueDao csCarSysIssueDao)
	{
		this.csCarSysIssueDao = csCarSysIssueDao;
	}
}