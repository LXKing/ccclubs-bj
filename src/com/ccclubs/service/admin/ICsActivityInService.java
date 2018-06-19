package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsActivityIn;

/**
 * 活动参与的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsActivityInService
{
	/**
	 * 获取所有活动参与
	 * @return
	 */
	public List<CsActivityIn> getCsActivityInList(Map params,Integer size);
	
	/**
	 * 获取活动参与统计
	 * @return
	 */
	public List getCsActivityInStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取活动参与总数
	 * @return
	 */	
	public Long getCsActivityInCount(Map params);
	
	
	/**
	 * 获取活动参与自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsActivityInEval(String eval,Map params);

	/**
	 * 获取活动参与分页
	 * @return
	 */	
	public Page getCsActivityInPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取活动参与
	 * @param params
	 * @return
	 */
	public CsActivityIn getCsActivityIn(Map params);

	/**
	 * 根据ID取活动参与
	 * @param id
	 * @return
	 */
	public CsActivityIn getCsActivityInById(Long id);


	/**
	 * 保存活动参与
	 * @param csActivityIn
	 */
	public CsActivityIn saveCsActivityIn(CsActivityIn csActivityIn);

	/**
	 * 更新活动参与
	 * @param csActivityIn
	 */
	public void updateCsActivityIn(CsActivityIn csActivityIn);
	/**
	 * 更新活动参与非空字段
	 * @param csActivityIn
	 */
	public void updateCsActivityIn$NotNull(CsActivityIn csActivityIn);

	/**
	 * 根据ID删除一个活动参与
	 * @param id
	 */
	public void deleteCsActivityInById(Long id);
	/**
	 * 根据ID逻辑删除一个活动参与
	 * @param id
	 */
	public void removeCsActivityInById(Long id);
	/**
	 * 根据条件更新活动参与
	 * @param values
	 * @param params
	 */
	public void updateCsActivityInByConfirm(Map values, Map params);
	/**
	 * 根据条件删除活动参与
	 * @param params
	 */
	public void deleteCsActivityInByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}