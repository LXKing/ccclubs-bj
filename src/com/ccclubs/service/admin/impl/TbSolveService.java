package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ITbSolveDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbSolve;
import com.ccclubs.service.admin.ITbSolveService;
import com.lazy3q.web.helper.$;

/**
 * 问题处理记录的Service实现
 * @author 飞啊飘啊
 */
public class TbSolveService implements ITbSolveService
{
	ITbSolveDao tbSolveDao;
	

	/**
	 * 获取所有问题处理记录
	 * @return
	 */
	public List<TbSolve> getTbSolveList(Map params,Integer size)
	{
		return tbSolveDao.getTbSolveList(params,size);
	}
	
	/**
	 * 获取问题处理记录统计
	 * @return
	 */
	public List getTbSolveStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return tbSolveDao.getTbSolveStats(params,groups,sums);
	}
	
	/**
	 * 获取问题处理记录总数
	 * @return
	 */
	public Long getTbSolveCount(Map params)
	{
		return tbSolveDao.getTbSolveCount(params);
	}
	
	/**
	 * 获取问题处理记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbSolveEval(String eval,Map params)
	{
		return tbSolveDao.getTbSolveEval(eval,params);
	}

	/**
	 * 获取问题处理记录分页
	 * @return
	 */
	public Page getTbSolvePage(int page,int size,Map params)
	{
		return tbSolveDao.getTbSolvePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取问题处理记录
	 * @param params
	 * @return
	 */
	public TbSolve getTbSolve(Map params)
	{
		return tbSolveDao.getTbSolve(params);
	}

	/**
	 * 根据ID取问题处理记录
	 * @param id
	 * @return
	 */
	public TbSolve getTbSolveById(Long id)
	{
		return tbSolveDao.getTbSolveById(id);
	}

	/**
	 * 保存问题处理记录
	 * @param tbSolve
	 */
	public TbSolve saveTbSolve(TbSolve tbSolve)
	{
		return tbSolveDao.saveTbSolve(tbSolve);
	}
	
	/**
	 * 更新问题处理记录
	 * @param tbSolve
	 */
	public void updateTbSolve(TbSolve tbSolve)
	{
		tbSolveDao.updateTbSolve(tbSolve);
	}
	/**
	 * 更新问题处理记录非空字段
	 * @param tbSolve
	 */
	public void updateTbSolve$NotNull(TbSolve tbSolve)
	{
		tbSolveDao.updateTbSolve$NotNull(tbSolve);
	}
	
	/**
	 * 根据ID删除一个问题处理记录
	 * @param id
	 */
	public void deleteTbSolveById(Long id)
	{
		tbSolveDao.deleteTbSolveById(id);
	}
	/**
	 * 根据ID逻辑删除一个问题处理记录
	 * @param id
	 */
	public void removeTbSolveById(Long id)
	{
		tbSolveDao.removeTbSolveById(id);
	}
	
	/**
	 * 根据条件更新问题处理记录
	 * @param values
	 * @param params
	 */
	public void updateTbSolveByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateTbSolveBy时无条件");
		tbSolveDao.updateTbSolveByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除问题处理记录
	 * @param params
	 */
	public void deleteTbSolveByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteTbSolveBy时无条件");
		tbSolveDao.deleteTbSolveByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ITbSolveDao getTbSolveDao()
	{
		return tbSolveDao;
	}

	public void setTbSolveDao(ITbSolveDao tbSolveDao)
	{
		this.tbSolveDao = tbSolveDao;
	}
}