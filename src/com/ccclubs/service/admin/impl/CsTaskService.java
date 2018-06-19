package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsTaskDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsTask;
import com.ccclubs.service.admin.ICsTaskService;
import com.lazy3q.web.helper.$;

/**
 * 数据任务的Service实现
 * @author 飞啊飘啊
 */
public class CsTaskService implements ICsTaskService
{
	ICsTaskDao csTaskDao;
	

	/**
	 * 获取所有数据任务
	 * @return
	 */
	public List<CsTask> getCsTaskList(Map params,Integer size)
	{
		return csTaskDao.getCsTaskList(params,size);
	}
	
	/**
	 * 获取数据任务统计
	 * @return
	 */
	public List getCsTaskStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csTaskDao.getCsTaskStats(params,groups,sums);
	}
	
	/**
	 * 获取数据任务总数
	 * @return
	 */
	public Long getCsTaskCount(Map params)
	{
		return csTaskDao.getCsTaskCount(params);
	}
	
	/**
	 * 获取数据任务自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsTaskEval(String eval,Map params)
	{
		return csTaskDao.getCsTaskEval(eval,params);
	}

	/**
	 * 获取数据任务分页
	 * @return
	 */
	public Page getCsTaskPage(int page,int size,Map params)
	{
		return csTaskDao.getCsTaskPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取数据任务
	 * @param params
	 * @return
	 */
	public CsTask getCsTask(Map params)
	{
		return csTaskDao.getCsTask(params);
	}

	/**
	 * 根据ID取数据任务
	 * @param id
	 * @return
	 */
	public CsTask getCsTaskById(Long id)
	{
		return csTaskDao.getCsTaskById(id);
	}

	/**
	 * 保存数据任务
	 * @param csTask
	 */
	public CsTask saveCsTask(CsTask csTask)
	{
		return csTaskDao.saveCsTask(csTask);
	}
	
	/**
	 * 更新数据任务
	 * @param csTask
	 */
	public void updateCsTask(CsTask csTask)
	{
		csTaskDao.updateCsTask(csTask);
	}
	/**
	 * 更新数据任务非空字段
	 * @param csTask
	 */
	public void updateCsTask$NotNull(CsTask csTask)
	{
		csTaskDao.updateCsTask$NotNull(csTask);
	}
	
	/**
	 * 根据ID删除一个数据任务
	 * @param id
	 */
	public void deleteCsTaskById(Long id)
	{
		csTaskDao.deleteCsTaskById(id);
	}
	
	/**
	 * 根据条件更新数据任务
	 * @param values
	 * @param params
	 */
	public void updateCsTaskByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsTaskBy时无条件");
		csTaskDao.updateCsTaskByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除数据任务
	 * @param params
	 */
	public void deleteCsTaskByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsTaskBy时无条件");
		csTaskDao.deleteCsTaskByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsTaskDao getCsTaskDao()
	{
		return csTaskDao;
	}

	public void setCsTaskDao(ICsTaskDao csTaskDao)
	{
		this.csTaskDao = csTaskDao;
	}
}