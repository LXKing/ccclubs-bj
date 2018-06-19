package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbSolve;

/**
 * 问题处理记录的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ITbSolveDao
{
	/**
	 * 获取所有问题处理记录
	 * @return
	 */
	public List<TbSolve> getTbSolveList(Map params,Integer size);
	
	/**
	 * 获取问题处理记录统计
	 * @return
	 */
	public List getTbSolveStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取问题处理记录总数
	 * @return
	 */	
	public Long getTbSolveCount(Map params);
	
	/**
	 * 获取问题处理记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbSolveEval(String eval,Map params);
	
	/**
	 * 获取问题处理记录分页
	 * @return
	 */	
	public Page getTbSolvePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取问题处理记录
	 * @param params
	 * @return
	 */
	public TbSolve getTbSolve(Map params);	

	/**
	 * 根据ID取问题处理记录
	 * @param id
	 * @return
	 */
	public TbSolve getTbSolveById(Long id);


	
	

	/**
	 * 保存问题处理记录
	 * @param tbSolve
	 */
	public TbSolve saveTbSolve(TbSolve tbSolve);

	/**
	 * 更新问题处理记录
	 * @param tbSolve
	 */
	public void updateTbSolve(TbSolve tbSolve);
	/**
	 * 更新问题处理记录非空字段
	 * @param tbSolve
	 */
	public void updateTbSolve$NotNull(TbSolve tbSolve);

	/**
	 * 根据ID删除一个问题处理记录
	 * @param id
	 */
	public void deleteTbSolveById(Long id);
	/**
	 * 根据ID逻辑删除一个问题处理记录
	 * @param id
	 */
	public void removeTbSolveById(Long id);
	/**
	 * 根据条件更新问题处理记录
	 * @param values
	 * @param params
	 */
	public void updateTbSolveByConfirm(Map values, Map params);
	/**
	 * 根据条件删除问题处理记录
	 * @param params
	 */
	public void deleteTbSolveByConfirm(Map params);

}