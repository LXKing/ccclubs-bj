package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.TbEmployee;

/**
 * 公司员工的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ITbEmployeeService
{
	/**
	 * 获取所有公司员工
	 * @return
	 */
	public List<TbEmployee> getTbEmployeeList(Map params,Integer size);
	
	/**
	 * 获取公司员工统计
	 * @return
	 */
	public List getTbEmployeeStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取公司员工总数
	 * @return
	 */	
	public Long getTbEmployeeCount(Map params);
	
	
	/**
	 * 获取公司员工自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbEmployeeEval(String eval,Map params);

	/**
	 * 获取公司员工分页
	 * @return
	 */	
	public Page getTbEmployeePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取公司员工
	 * @param params
	 * @return
	 */
	public TbEmployee getTbEmployee(Map params);

	/**
	 * 根据ID取公司员工
	 * @param id
	 * @return
	 */
	public TbEmployee getTbEmployeeById(Long id);


	/**
	 * 保存公司员工
	 * @param tbEmployee
	 */
	public TbEmployee saveTbEmployee(TbEmployee tbEmployee);

	/**
	 * 更新公司员工
	 * @param tbEmployee
	 */
	public void updateTbEmployee(TbEmployee tbEmployee);
	/**
	 * 更新公司员工非空字段
	 * @param tbEmployee
	 */
	public void updateTbEmployee$NotNull(TbEmployee tbEmployee);

	/**
	 * 根据ID删除一个公司员工
	 * @param id
	 */
	public void deleteTbEmployeeById(Long id);
	/**
	 * 根据ID逻辑删除一个公司员工
	 * @param id
	 */
	public void removeTbEmployeeById(Long id);
	/**
	 * 根据条件更新公司员工
	 * @param values
	 * @param params
	 */
	public void updateTbEmployeeByConfirm(Map values, Map params);
	/**
	 * 根据条件删除公司员工
	 * @param params
	 */
	public void deleteTbEmployeeByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}