package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ITbDepartmentDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbDepartment;
import com.ccclubs.service.admin.ITbDepartmentService;
import com.lazy3q.web.helper.$;

/**
 * 公司部门的Service实现
 * @author 飞啊飘啊
 */
public class TbDepartmentService implements ITbDepartmentService
{
	ITbDepartmentDao tbDepartmentDao;
	

	/**
	 * 获取所有公司部门
	 * @return
	 */
	public List<TbDepartment> getTbDepartmentList(Map params,Integer size)
	{
		return tbDepartmentDao.getTbDepartmentList(params,size);
	}
	
	/**
	 * 获取公司部门统计
	 * @return
	 */
	public List getTbDepartmentStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return tbDepartmentDao.getTbDepartmentStats(params,groups,sums);
	}
	
	/**
	 * 获取公司部门总数
	 * @return
	 */
	public Long getTbDepartmentCount(Map params)
	{
		return tbDepartmentDao.getTbDepartmentCount(params);
	}
	
	/**
	 * 获取公司部门自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbDepartmentEval(String eval,Map params)
	{
		return tbDepartmentDao.getTbDepartmentEval(eval,params);
	}

	/**
	 * 获取公司部门分页
	 * @return
	 */
	public Page getTbDepartmentPage(int page,int size,Map params)
	{
		return tbDepartmentDao.getTbDepartmentPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取公司部门
	 * @param params
	 * @return
	 */
	public TbDepartment getTbDepartment(Map params)
	{
		return tbDepartmentDao.getTbDepartment(params);
	}

	/**
	 * 根据ID取公司部门
	 * @param id
	 * @return
	 */
	public TbDepartment getTbDepartmentById(Long id)
	{
		return tbDepartmentDao.getTbDepartmentById(id);
	}

	/**
	 * 保存公司部门
	 * @param tbDepartment
	 */
	public TbDepartment saveTbDepartment(TbDepartment tbDepartment)
	{
		return tbDepartmentDao.saveTbDepartment(tbDepartment);
	}
	
	/**
	 * 更新公司部门
	 * @param tbDepartment
	 */
	public void updateTbDepartment(TbDepartment tbDepartment)
	{
		tbDepartmentDao.updateTbDepartment(tbDepartment);
	}
	/**
	 * 更新公司部门非空字段
	 * @param tbDepartment
	 */
	public void updateTbDepartment$NotNull(TbDepartment tbDepartment)
	{
		tbDepartmentDao.updateTbDepartment$NotNull(tbDepartment);
	}
	
	/**
	 * 根据ID删除一个公司部门
	 * @param id
	 */
	public void deleteTbDepartmentById(Long id)
	{
		tbDepartmentDao.deleteTbDepartmentById(id);
	}
	/**
	 * 根据ID逻辑删除一个公司部门
	 * @param id
	 */
	public void removeTbDepartmentById(Long id)
	{
		tbDepartmentDao.removeTbDepartmentById(id);
	}
	
	/**
	 * 根据条件更新公司部门
	 * @param values
	 * @param params
	 */
	public void updateTbDepartmentByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateTbDepartmentBy时无条件");
		tbDepartmentDao.updateTbDepartmentByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除公司部门
	 * @param params
	 */
	public void deleteTbDepartmentByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteTbDepartmentBy时无条件");
		tbDepartmentDao.deleteTbDepartmentByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ITbDepartmentDao getTbDepartmentDao()
	{
		return tbDepartmentDao;
	}

	public void setTbDepartmentDao(ITbDepartmentDao tbDepartmentDao)
	{
		this.tbDepartmentDao = tbDepartmentDao;
	}
}