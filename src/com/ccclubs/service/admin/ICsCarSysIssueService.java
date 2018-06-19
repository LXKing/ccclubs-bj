package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsCarSysIssue;

/**
 * 系统车况报告的Service接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsCarSysIssueService
{
	/**
	 * 获取所有系统车况报告
	 * @return
	 */
	public List<CsCarSysIssue> getCsCarSysIssueList(Map params,Integer size);
	
	/**
	 * 获取系统车况报告统计
	 * @return
	 */
	public List getCsCarSysIssueStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取系统车况报告总数
	 * @return
	 */	
	public Long getCsCarSysIssueCount(Map params);
	
	
	/**
	 * 获取系统车况报告自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCarSysIssueEval(String eval,Map params);

	/**
	 * 获取系统车况报告分页
	 * @return
	 */	
	public Page getCsCarSysIssuePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取系统车况报告
	 * @param params
	 * @return
	 */
	public CsCarSysIssue getCsCarSysIssue(Map params);

	/**
	 * 根据ID取系统车况报告
	 * @param id
	 * @return
	 */
	public CsCarSysIssue getCsCarSysIssueById(Long id);


	/**
	 * 保存系统车况报告
	 * @param csCarSysIssue
	 */
	public CsCarSysIssue saveCsCarSysIssue(CsCarSysIssue csCarSysIssue);

	/**
	 * 更新系统车况报告
	 * @param csCarSysIssue
	 */
	public void updateCsCarSysIssue(CsCarSysIssue csCarSysIssue);
	/**
	 * 更新系统车况报告非空字段
	 * @param csCarSysIssue
	 */
	public void updateCsCarSysIssue$NotNull(CsCarSysIssue csCarSysIssue);

	/**
	 * 根据ID删除一个系统车况报告
	 * @param id
	 */
	public void deleteCsCarSysIssueById(Long id);
	/**
	 * 根据ID逻辑删除一个系统车况报告
	 * @param id
	 */
	public void removeCsCarSysIssueById(Long id);
	/**
	 * 根据条件更新系统车况报告
	 * @param values
	 * @param params
	 */
	public void updateCsCarSysIssueByConfirm(Map values, Map params);
	/**
	 * 根据条件删除系统车况报告
	 * @param params
	 */
	public void deleteCsCarSysIssueByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}