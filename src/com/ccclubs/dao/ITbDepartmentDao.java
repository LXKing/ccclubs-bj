package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbDepartment;

/**
 * 公司部门的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ITbDepartmentDao
{
	/**
	 * 获取所有公司部门
	 * @return
	 */
	public List<TbDepartment> getTbDepartmentList(Map params,Integer size);
	
	/**
	 * 获取公司部门统计
	 * @return
	 */
	public List getTbDepartmentStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取公司部门总数
	 * @return
	 */	
	public Long getTbDepartmentCount(Map params);
	
	/**
	 * 获取公司部门自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbDepartmentEval(String eval,Map params);
	
	/**
	 * 获取公司部门分页
	 * @return
	 */	
	public Page getTbDepartmentPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取公司部门
	 * @param params
	 * @return
	 */
	public TbDepartment getTbDepartment(Map params);	

	/**
	 * 根据ID取公司部门
	 * @param id
	 * @return
	 */
	public TbDepartment getTbDepartmentById(Long id);


	
	

	/**
	 * 保存公司部门
	 * @param tbDepartment
	 */
	public TbDepartment saveTbDepartment(TbDepartment tbDepartment);

	/**
	 * 更新公司部门
	 * @param tbDepartment
	 */
	public void updateTbDepartment(TbDepartment tbDepartment);
	/**
	 * 更新公司部门非空字段
	 * @param tbDepartment
	 */
	public void updateTbDepartment$NotNull(TbDepartment tbDepartment);

	/**
	 * 根据ID删除一个公司部门
	 * @param id
	 */
	public void deleteTbDepartmentById(Long id);
	/**
	 * 根据ID逻辑删除一个公司部门
	 * @param id
	 */
	public void removeTbDepartmentById(Long id);
	/**
	 * 根据条件更新公司部门
	 * @param values
	 * @param params
	 */
	public void updateTbDepartmentByConfirm(Map values, Map params);
	/**
	 * 根据条件删除公司部门
	 * @param params
	 */
	public void deleteTbDepartmentByConfirm(Map params);

}