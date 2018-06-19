package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsTodoDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsTodo;
import com.ccclubs.service.admin.ICsTodoService;
import com.lazy3q.web.helper.$;

/**
 * 待办事项的Service实现
 * @author 飞啊飘啊
 */
public class CsTodoService implements ICsTodoService
{
	ICsTodoDao csTodoDao;
	

	/**
	 * 获取所有待办事项
	 * @return
	 */
	public List<CsTodo> getCsTodoList(Map params,Integer size)
	{
		return csTodoDao.getCsTodoList(params,size);
	}
	
	/**
	 * 获取待办事项统计
	 * @return
	 */
	public List getCsTodoStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csTodoDao.getCsTodoStats(params,groups,sums);
	}
	
	/**
	 * 获取待办事项总数
	 * @return
	 */
	public Long getCsTodoCount(Map params)
	{
		return csTodoDao.getCsTodoCount(params);
	}
	
	/**
	 * 获取待办事项自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsTodoEval(String eval,Map params)
	{
		return csTodoDao.getCsTodoEval(eval,params);
	}

	/**
	 * 获取待办事项分页
	 * @return
	 */
	public Page getCsTodoPage(int page,int size,Map params)
	{
		return csTodoDao.getCsTodoPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取待办事项
	 * @param params
	 * @return
	 */
	public CsTodo getCsTodo(Map params)
	{
		return csTodoDao.getCsTodo(params);
	}

	/**
	 * 根据ID取待办事项
	 * @param id
	 * @return
	 */
	public CsTodo getCsTodoById(Long id)
	{
		return csTodoDao.getCsTodoById(id);
	}

	/**
	 * 保存待办事项
	 * @param csTodo
	 */
	public CsTodo saveCsTodo(CsTodo csTodo)
	{
		return csTodoDao.saveCsTodo(csTodo);
	}
	
	/**
	 * 更新待办事项
	 * @param csTodo
	 */
	public void updateCsTodo(CsTodo csTodo)
	{
		csTodoDao.updateCsTodo(csTodo);
	}
	/**
	 * 更新待办事项非空字段
	 * @param csTodo
	 */
	public void updateCsTodo$NotNull(CsTodo csTodo)
	{
		csTodoDao.updateCsTodo$NotNull(csTodo);
	}
	
	/**
	 * 根据ID删除一个待办事项
	 * @param id
	 */
	public void deleteCsTodoById(Long id)
	{
		csTodoDao.deleteCsTodoById(id);
	}
	/**
	 * 根据ID逻辑删除一个待办事项
	 * @param id
	 */
	public void removeCsTodoById(Long id)
	{
		csTodoDao.removeCsTodoById(id);
	}
	
	/**
	 * 根据条件更新待办事项
	 * @param values
	 * @param params
	 */
	public void updateCsTodoByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsTodoBy时无条件");
		csTodoDao.updateCsTodoByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除待办事项
	 * @param params
	 */
	public void deleteCsTodoByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsTodoBy时无条件");
		csTodoDao.deleteCsTodoByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsTodoDao getCsTodoDao()
	{
		return csTodoDao;
	}

	public void setCsTodoDao(ICsTodoDao csTodoDao)
	{
		this.csTodoDao = csTodoDao;
	}
}