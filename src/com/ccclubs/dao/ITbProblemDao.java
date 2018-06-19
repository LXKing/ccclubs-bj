package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbProblem;

/**
 * 问题故障报修的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ITbProblemDao
{
	/**
	 * 获取所有问题故障报修
	 * @return
	 */
	public List<TbProblem> getTbProblemList(Map params,Integer size);
	
	/**
	 * 获取问题故障报修统计
	 * @return
	 */
	public List getTbProblemStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取问题故障报修总数
	 * @return
	 */	
	public Long getTbProblemCount(Map params);
	
	/**
	 * 获取问题故障报修自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbProblemEval(String eval,Map params);
	
	/**
	 * 获取问题故障报修分页
	 * @return
	 */	
	public Page getTbProblemPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取问题故障报修
	 * @param params
	 * @return
	 */
	public TbProblem getTbProblem(Map params);	

	/**
	 * 根据ID取问题故障报修
	 * @param id
	 * @return
	 */
	public TbProblem getTbProblemById(Long id);


	
	

	/**
	 * 保存问题故障报修
	 * @param tbProblem
	 */
	public TbProblem saveTbProblem(TbProblem tbProblem);

	/**
	 * 更新问题故障报修
	 * @param tbProblem
	 */
	public void updateTbProblem(TbProblem tbProblem);
	/**
	 * 更新问题故障报修非空字段
	 * @param tbProblem
	 */
	public void updateTbProblem$NotNull(TbProblem tbProblem);

	/**
	 * 根据ID删除一个问题故障报修
	 * @param id
	 */
	public void deleteTbProblemById(Long id);
	/**
	 * 根据ID逻辑删除一个问题故障报修
	 * @param id
	 */
	public void removeTbProblemById(Long id);
	/**
	 * 根据条件更新问题故障报修
	 * @param values
	 * @param params
	 */
	public void updateTbProblemByConfirm(Map values, Map params);
	/**
	 * 根据条件删除问题故障报修
	 * @param params
	 */
	public void deleteTbProblemByConfirm(Map params);

}