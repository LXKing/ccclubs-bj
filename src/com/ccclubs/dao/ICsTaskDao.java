package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsTask;

/**
 * 数据任务的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsTaskDao
{
	/**
	 * 获取所有数据任务
	 * @return
	 */
	public List<CsTask> getCsTaskList(Map params,Integer size);
	
	/**
	 * 获取数据任务统计
	 * @return
	 */
	public List getCsTaskStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取数据任务总数
	 * @return
	 */	
	public Long getCsTaskCount(Map params);
	
	/**
	 * 获取数据任务自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsTaskEval(String eval,Map params);
	
	/**
	 * 获取数据任务分页
	 * @return
	 */	
	public Page getCsTaskPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取数据任务
	 * @param params
	 * @return
	 */
	public CsTask getCsTask(Map params);	

	/**
	 * 根据ID取数据任务
	 * @param id
	 * @return
	 */
	public CsTask getCsTaskById(Long id);


	
	

	/**
	 * 保存数据任务
	 * @param csTask
	 */
	public CsTask saveCsTask(CsTask csTask);

	/**
	 * 更新数据任务
	 * @param csTask
	 */
	public void updateCsTask(CsTask csTask);
	/**
	 * 更新数据任务非空字段
	 * @param csTask
	 */
	public void updateCsTask$NotNull(CsTask csTask);

	/**
	 * 根据ID删除一个数据任务
	 * @param id
	 */
	public void deleteCsTaskById(Long id);
	/**
	 * 根据条件更新数据任务
	 * @param values
	 * @param params
	 */
	public void updateCsTaskByConfirm(Map values, Map params);
	/**
	 * 根据条件删除数据任务
	 * @param params
	 */
	public void deleteCsTaskByConfirm(Map params);

}