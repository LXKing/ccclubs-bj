package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.SrvWorkflow;

/**
 * 工作流的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ISrvWorkflowService
{
	/**
	 * 获取所有工作流
	 * @return
	 */
	public List<SrvWorkflow> getSrvWorkflowList(Map params,Integer size);
	
	/**
	 * 获取工作流统计
	 * @return
	 */
	public List getSrvWorkflowStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取工作流总数
	 * @return
	 */	
	public Long getSrvWorkflowCount(Map params);
	
	
	/**
	 * 获取工作流自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvWorkflowEval(String eval,Map params);

	/**
	 * 获取工作流分页
	 * @return
	 */	
	public Page getSrvWorkflowPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取工作流
	 * @param params
	 * @return
	 */
	public SrvWorkflow getSrvWorkflow(Map params);

	/**
	 * 根据ID取工作流
	 * @param id
	 * @return
	 */
	public SrvWorkflow getSrvWorkflowById(Long id);


	/**
	 * 保存工作流
	 * @param srvWorkflow
	 */
	public SrvWorkflow saveSrvWorkflow(SrvWorkflow srvWorkflow);

	/**
	 * 更新工作流
	 * @param srvWorkflow
	 */
	public void updateSrvWorkflow(SrvWorkflow srvWorkflow);
	/**
	 * 更新工作流非空字段
	 * @param srvWorkflow
	 */
	public void updateSrvWorkflow$NotNull(SrvWorkflow srvWorkflow);

	/**
	 * 根据ID删除一个工作流
	 * @param id
	 */
	public void deleteSrvWorkflowById(Long id);
	/**
	 * 根据ID逻辑删除一个工作流
	 * @param id
	 */
	public void removeSrvWorkflowById(Long id);
	/**
	 * 根据条件更新工作流
	 * @param values
	 * @param params
	 */
	public void updateSrvWorkflowByConfirm(Map values, Map params);
	/**
	 * 根据条件删除工作流
	 * @param params
	 */
	public void deleteSrvWorkflowByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}