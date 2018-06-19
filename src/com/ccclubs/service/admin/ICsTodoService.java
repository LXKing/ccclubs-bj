package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsTodo;

/**
 * 待办事项的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsTodoService
{
	/**
	 * 获取所有待办事项
	 * @return
	 */
	public List<CsTodo> getCsTodoList(Map params,Integer size);
	
	/**
	 * 获取待办事项统计
	 * @return
	 */
	public List getCsTodoStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取待办事项总数
	 * @return
	 */	
	public Long getCsTodoCount(Map params);
	
	
	/**
	 * 获取待办事项自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsTodoEval(String eval,Map params);

	/**
	 * 获取待办事项分页
	 * @return
	 */	
	public Page getCsTodoPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取待办事项
	 * @param params
	 * @return
	 */
	public CsTodo getCsTodo(Map params);

	/**
	 * 根据ID取待办事项
	 * @param id
	 * @return
	 */
	public CsTodo getCsTodoById(Long id);


	/**
	 * 保存待办事项
	 * @param csTodo
	 */
	public CsTodo saveCsTodo(CsTodo csTodo);

	/**
	 * 更新待办事项
	 * @param csTodo
	 */
	public void updateCsTodo(CsTodo csTodo);
	/**
	 * 更新待办事项非空字段
	 * @param csTodo
	 */
	public void updateCsTodo$NotNull(CsTodo csTodo);

	/**
	 * 根据ID删除一个待办事项
	 * @param id
	 */
	public void deleteCsTodoById(Long id);
	/**
	 * 根据ID逻辑删除一个待办事项
	 * @param id
	 */
	public void removeCsTodoById(Long id);
	/**
	 * 根据条件更新待办事项
	 * @param values
	 * @param params
	 */
	public void updateCsTodoByConfirm(Map values, Map params);
	/**
	 * 根据条件删除待办事项
	 * @param params
	 */
	public void deleteCsTodoByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}