package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsHistoryState;

/**
 * 车辆历史状态的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsHistoryStateDao
{
	/**
	 * 获取所有车辆历史状态
	 * @return
	 */
	public List<CsHistoryState> getCsHistoryStateList(Map params,Integer size);
	
	/**
	 * 获取车辆历史状态统计
	 * @return
	 */
	public List getCsHistoryStateStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取车辆历史状态总数
	 * @return
	 */	
	public Long getCsHistoryStateCount(Map params);
	
	/**
	 * 获取车辆历史状态自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsHistoryStateEval(String eval,Map params);
	
	/**
	 * 获取车辆历史状态分页
	 * @return
	 */	
	public Page getCsHistoryStatePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取车辆历史状态
	 * @param params
	 * @return
	 */
	public CsHistoryState getCsHistoryState(Map params);	

	/**
	 * 根据ID取车辆历史状态
	 * @param id
	 * @return
	 */
	public CsHistoryState getCsHistoryStateById(Long id);


	
	

	/**
	 * 保存车辆历史状态
	 * @param csHistoryState
	 */
	public CsHistoryState saveCsHistoryState(CsHistoryState csHistoryState);

	/**
	 * 更新车辆历史状态
	 * @param csHistoryState
	 */
	public void updateCsHistoryState(CsHistoryState csHistoryState);
	/**
	 * 更新车辆历史状态非空字段
	 * @param csHistoryState
	 */
	public void updateCsHistoryState$NotNull(CsHistoryState csHistoryState);

	/**
	 * 根据ID删除一个车辆历史状态
	 * @param id
	 */
	public void deleteCsHistoryStateById(Long id);
	/**
	 * 根据条件更新车辆历史状态
	 * @param values
	 * @param params
	 */
	public void updateCsHistoryStateByConfirm(Map values, Map params);
	/**
	 * 根据条件删除车辆历史状态
	 * @param params
	 */
	public void deleteCsHistoryStateByConfirm(Map params);

}