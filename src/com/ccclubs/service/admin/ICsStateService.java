package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsState;

/**
 * 车辆当前状态的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsStateService
{
	/**
	 * 获取所有车辆当前状态
	 * @return
	 */
	public List<CsState> getCsStateList(Map params,Integer size);
	
	/**
	 * 获取车辆当前状态统计
	 * @return
	 */
	public List getCsStateStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取车辆当前状态总数
	 * @return
	 */	
	public Long getCsStateCount(Map params);
	
	
	/**
	 * 获取车辆当前状态自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsStateEval(String eval,Map params);

	/**
	 * 获取车辆当前状态分页
	 * @return
	 */	
	public Page getCsStatePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取车辆当前状态
	 * @param params
	 * @return
	 */
	public CsState getCsState(Map params);

	/**
	 * 根据ID取车辆当前状态
	 * @param id
	 * @return
	 */
	public CsState getCsStateById(Long id);


	/**
	 * 保存车辆当前状态
	 * @param csState
	 */
	public CsState saveCsState(CsState csState);

	/**
	 * 更新车辆当前状态
	 * @param csState
	 */
	public void updateCsState(CsState csState);
	/**
	 * 更新车辆当前状态非空字段
	 * @param csState
	 */
	public void updateCsState$NotNull(CsState csState);

	/**
	 * 根据ID删除一个车辆当前状态
	 * @param id
	 */
	public void deleteCsStateById(Long id);
	/**
	 * 根据条件更新车辆当前状态
	 * @param values
	 * @param params
	 */
	public void updateCsStateByConfirm(Map values, Map params);
	/**
	 * 根据条件删除车辆当前状态
	 * @param params
	 */
	public void deleteCsStateByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}