package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsPropagate;

/**
 * 传播内容的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsPropagateService
{
	/**
	 * 获取所有传播内容
	 * @return
	 */
	public List<CsPropagate> getCsPropagateList(Map params,Integer size);
	
	/**
	 * 获取传播内容统计
	 * @return
	 */
	public List getCsPropagateStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取传播内容总数
	 * @return
	 */	
	public Long getCsPropagateCount(Map params);
	
	
	/**
	 * 获取传播内容自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsPropagateEval(String eval,Map params);

	/**
	 * 获取传播内容分页
	 * @return
	 */	
	public Page getCsPropagatePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取传播内容
	 * @param params
	 * @return
	 */
	public CsPropagate getCsPropagate(Map params);

	/**
	 * 根据ID取传播内容
	 * @param id
	 * @return
	 */
	public CsPropagate getCsPropagateById(Long id);


	/**
	 * 保存传播内容
	 * @param csPropagate
	 */
	public CsPropagate saveCsPropagate(CsPropagate csPropagate);

	/**
	 * 更新传播内容
	 * @param csPropagate
	 */
	public void updateCsPropagate(CsPropagate csPropagate);
	/**
	 * 更新传播内容非空字段
	 * @param csPropagate
	 */
	public void updateCsPropagate$NotNull(CsPropagate csPropagate);

	/**
	 * 根据ID删除一个传播内容
	 * @param id
	 */
	public void deleteCsPropagateById(Long id);
	/**
	 * 根据ID逻辑删除一个传播内容
	 * @param id
	 */
	public void removeCsPropagateById(Long id);
	/**
	 * 根据条件更新传播内容
	 * @param values
	 * @param params
	 */
	public void updateCsPropagateByConfirm(Map values, Map params);
	/**
	 * 根据条件删除传播内容
	 * @param params
	 */
	public void deleteCsPropagateByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}