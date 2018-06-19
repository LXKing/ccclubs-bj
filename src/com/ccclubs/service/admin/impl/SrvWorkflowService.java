package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ISrvWorkflowDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvWorkflow;
import com.ccclubs.service.admin.ISrvWorkflowService;
import com.lazy3q.web.helper.$;

/**
 * 工作流的Service实现
 * @author 飞啊飘啊
 */
public class SrvWorkflowService implements ISrvWorkflowService
{
	ISrvWorkflowDao srvWorkflowDao;
	

	/**
	 * 获取所有工作流
	 * @return
	 */
	public List<SrvWorkflow> getSrvWorkflowList(Map params,Integer size)
	{
		return srvWorkflowDao.getSrvWorkflowList(params,size);
	}
	
	/**
	 * 获取工作流统计
	 * @return
	 */
	public List getSrvWorkflowStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return srvWorkflowDao.getSrvWorkflowStats(params,groups,sums);
	}
	
	/**
	 * 获取工作流总数
	 * @return
	 */
	public Long getSrvWorkflowCount(Map params)
	{
		return srvWorkflowDao.getSrvWorkflowCount(params);
	}
	
	/**
	 * 获取工作流自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvWorkflowEval(String eval,Map params)
	{
		return srvWorkflowDao.getSrvWorkflowEval(eval,params);
	}

	/**
	 * 获取工作流分页
	 * @return
	 */
	public Page getSrvWorkflowPage(int page,int size,Map params)
	{
		return srvWorkflowDao.getSrvWorkflowPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取工作流
	 * @param params
	 * @return
	 */
	public SrvWorkflow getSrvWorkflow(Map params)
	{
		return srvWorkflowDao.getSrvWorkflow(params);
	}

	/**
	 * 根据ID取工作流
	 * @param id
	 * @return
	 */
	public SrvWorkflow getSrvWorkflowById(Long id)
	{
		return srvWorkflowDao.getSrvWorkflowById(id);
	}

	/**
	 * 保存工作流
	 * @param srvWorkflow
	 */
	public SrvWorkflow saveSrvWorkflow(SrvWorkflow srvWorkflow)
	{
		return srvWorkflowDao.saveSrvWorkflow(srvWorkflow);
	}
	
	/**
	 * 更新工作流
	 * @param srvWorkflow
	 */
	public void updateSrvWorkflow(SrvWorkflow srvWorkflow)
	{
		srvWorkflowDao.updateSrvWorkflow(srvWorkflow);
	}
	/**
	 * 更新工作流非空字段
	 * @param srvWorkflow
	 */
	public void updateSrvWorkflow$NotNull(SrvWorkflow srvWorkflow)
	{
		srvWorkflowDao.updateSrvWorkflow$NotNull(srvWorkflow);
	}
	
	/**
	 * 根据ID删除一个工作流
	 * @param id
	 */
	public void deleteSrvWorkflowById(Long id)
	{
		srvWorkflowDao.deleteSrvWorkflowById(id);
	}
	/**
	 * 根据ID逻辑删除一个工作流
	 * @param id
	 */
	public void removeSrvWorkflowById(Long id)
	{
		srvWorkflowDao.removeSrvWorkflowById(id);
	}
	
	/**
	 * 根据条件更新工作流
	 * @param values
	 * @param params
	 */
	public void updateSrvWorkflowByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateSrvWorkflowBy时无条件");
		srvWorkflowDao.updateSrvWorkflowByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除工作流
	 * @param params
	 */
	public void deleteSrvWorkflowByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteSrvWorkflowBy时无条件");
		srvWorkflowDao.deleteSrvWorkflowByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ISrvWorkflowDao getSrvWorkflowDao()
	{
		return srvWorkflowDao;
	}

	public void setSrvWorkflowDao(ISrvWorkflowDao srvWorkflowDao)
	{
		this.srvWorkflowDao = srvWorkflowDao;
	}
}