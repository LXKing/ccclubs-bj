package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ITbProblemDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbProblem;
import com.ccclubs.service.admin.ITbProblemService;
import com.lazy3q.web.helper.$;

/**
 * 问题故障报修的Service实现
 * @author 飞啊飘啊
 */
public class TbProblemService implements ITbProblemService
{
	ITbProblemDao tbProblemDao;
	

	/**
	 * 获取所有问题故障报修
	 * @return
	 */
	public List<TbProblem> getTbProblemList(Map params,Integer size)
	{
		return tbProblemDao.getTbProblemList(params,size);
	}
	
	/**
	 * 获取问题故障报修统计
	 * @return
	 */
	public List getTbProblemStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return tbProblemDao.getTbProblemStats(params,groups,sums);
	}
	
	/**
	 * 获取问题故障报修总数
	 * @return
	 */
	public Long getTbProblemCount(Map params)
	{
		return tbProblemDao.getTbProblemCount(params);
	}
	
	/**
	 * 获取问题故障报修自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbProblemEval(String eval,Map params)
	{
		return tbProblemDao.getTbProblemEval(eval,params);
	}

	/**
	 * 获取问题故障报修分页
	 * @return
	 */
	public Page getTbProblemPage(int page,int size,Map params)
	{
		return tbProblemDao.getTbProblemPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取问题故障报修
	 * @param params
	 * @return
	 */
	public TbProblem getTbProblem(Map params)
	{
		return tbProblemDao.getTbProblem(params);
	}

	/**
	 * 根据ID取问题故障报修
	 * @param id
	 * @return
	 */
	public TbProblem getTbProblemById(Long id)
	{
		return tbProblemDao.getTbProblemById(id);
	}

	/**
	 * 保存问题故障报修
	 * @param tbProblem
	 */
	public TbProblem saveTbProblem(TbProblem tbProblem)
	{
		return tbProblemDao.saveTbProblem(tbProblem);
	}
	
	/**
	 * 更新问题故障报修
	 * @param tbProblem
	 */
	public void updateTbProblem(TbProblem tbProblem)
	{
		tbProblemDao.updateTbProblem(tbProblem);
	}
	/**
	 * 更新问题故障报修非空字段
	 * @param tbProblem
	 */
	public void updateTbProblem$NotNull(TbProblem tbProblem)
	{
		tbProblemDao.updateTbProblem$NotNull(tbProblem);
	}
	
	/**
	 * 根据ID删除一个问题故障报修
	 * @param id
	 */
	public void deleteTbProblemById(Long id)
	{
		tbProblemDao.deleteTbProblemById(id);
	}
	/**
	 * 根据ID逻辑删除一个问题故障报修
	 * @param id
	 */
	public void removeTbProblemById(Long id)
	{
		tbProblemDao.removeTbProblemById(id);
	}
	
	/**
	 * 根据条件更新问题故障报修
	 * @param values
	 * @param params
	 */
	public void updateTbProblemByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateTbProblemBy时无条件");
		tbProblemDao.updateTbProblemByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除问题故障报修
	 * @param params
	 */
	public void deleteTbProblemByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteTbProblemBy时无条件");
		tbProblemDao.deleteTbProblemByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ITbProblemDao getTbProblemDao()
	{
		return tbProblemDao;
	}

	public void setTbProblemDao(ITbProblemDao tbProblemDao)
	{
		this.tbProblemDao = tbProblemDao;
	}
}