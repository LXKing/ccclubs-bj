package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ITbEmployeeDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbEmployee;
import com.ccclubs.service.admin.ITbEmployeeService;
import com.lazy3q.web.helper.$;

/**
 * 公司员工的Service实现
 * @author 飞啊飘啊
 */
public class TbEmployeeService implements ITbEmployeeService
{
	ITbEmployeeDao tbEmployeeDao;
	

	/**
	 * 获取所有公司员工
	 * @return
	 */
	public List<TbEmployee> getTbEmployeeList(Map params,Integer size)
	{
		return tbEmployeeDao.getTbEmployeeList(params,size);
	}
	
	/**
	 * 获取公司员工统计
	 * @return
	 */
	public List getTbEmployeeStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return tbEmployeeDao.getTbEmployeeStats(params,groups,sums);
	}
	
	/**
	 * 获取公司员工总数
	 * @return
	 */
	public Long getTbEmployeeCount(Map params)
	{
		return tbEmployeeDao.getTbEmployeeCount(params);
	}
	
	/**
	 * 获取公司员工自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbEmployeeEval(String eval,Map params)
	{
		return tbEmployeeDao.getTbEmployeeEval(eval,params);
	}

	/**
	 * 获取公司员工分页
	 * @return
	 */
	public Page getTbEmployeePage(int page,int size,Map params)
	{
		return tbEmployeeDao.getTbEmployeePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取公司员工
	 * @param params
	 * @return
	 */
	public TbEmployee getTbEmployee(Map params)
	{
		return tbEmployeeDao.getTbEmployee(params);
	}

	/**
	 * 根据ID取公司员工
	 * @param id
	 * @return
	 */
	public TbEmployee getTbEmployeeById(Long id)
	{
		return tbEmployeeDao.getTbEmployeeById(id);
	}

	/**
	 * 保存公司员工
	 * @param tbEmployee
	 */
	public TbEmployee saveTbEmployee(TbEmployee tbEmployee)
	{
		return tbEmployeeDao.saveTbEmployee(tbEmployee);
	}
	
	/**
	 * 更新公司员工
	 * @param tbEmployee
	 */
	public void updateTbEmployee(TbEmployee tbEmployee)
	{
		tbEmployeeDao.updateTbEmployee(tbEmployee);
	}
	/**
	 * 更新公司员工非空字段
	 * @param tbEmployee
	 */
	public void updateTbEmployee$NotNull(TbEmployee tbEmployee)
	{
		tbEmployeeDao.updateTbEmployee$NotNull(tbEmployee);
	}
	
	/**
	 * 根据ID删除一个公司员工
	 * @param id
	 */
	public void deleteTbEmployeeById(Long id)
	{
		tbEmployeeDao.deleteTbEmployeeById(id);
	}
	/**
	 * 根据ID逻辑删除一个公司员工
	 * @param id
	 */
	public void removeTbEmployeeById(Long id)
	{
		tbEmployeeDao.removeTbEmployeeById(id);
	}
	
	/**
	 * 根据条件更新公司员工
	 * @param values
	 * @param params
	 */
	public void updateTbEmployeeByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateTbEmployeeBy时无条件");
		tbEmployeeDao.updateTbEmployeeByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除公司员工
	 * @param params
	 */
	public void deleteTbEmployeeByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteTbEmployeeBy时无条件");
		tbEmployeeDao.deleteTbEmployeeByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ITbEmployeeDao getTbEmployeeDao()
	{
		return tbEmployeeDao;
	}

	public void setTbEmployeeDao(ITbEmployeeDao tbEmployeeDao)
	{
		this.tbEmployeeDao = tbEmployeeDao;
	}
}