package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsScheduling;

/**
 * 车辆调度的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsSchedulingDao
{
	/**
	 * 获取所有车辆调度
	 * @return
	 */
	public List<CsScheduling> getCsSchedulingList(Map params,Integer size);
	
	/**
	 * 获取车辆调度统计
	 * @return
	 */
	public List getCsSchedulingStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取车辆调度总数
	 * @return
	 */	
	public Long getCsSchedulingCount(Map params);
	
	/**
	 * 获取车辆调度自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsSchedulingEval(String eval,Map params);
	
	/**
	 * 获取车辆调度分页
	 * @return
	 */	
	public Page getCsSchedulingPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取车辆调度
	 * @param params
	 * @return
	 */
	public CsScheduling getCsScheduling(Map params);	

	/**
	 * 根据ID取车辆调度
	 * @param id
	 * @return
	 */
	public CsScheduling getCsSchedulingById(Long id);


	
	

	/**
	 * 保存车辆调度
	 * @param csScheduling
	 */
	public CsScheduling saveCsScheduling(CsScheduling csScheduling);

	/**
	 * 更新车辆调度
	 * @param csScheduling
	 */
	public void updateCsScheduling(CsScheduling csScheduling);
	/**
	 * 更新车辆调度非空字段
	 * @param csScheduling
	 */
	public void updateCsScheduling$NotNull(CsScheduling csScheduling);

	/**
	 * 根据ID删除一个车辆调度
	 * @param id
	 */
	public void deleteCsSchedulingById(Long id);
	/**
	 * 根据ID逻辑删除一个车辆调度
	 * @param id
	 */
	public void removeCsSchedulingById(Long id);
	/**
	 * 根据条件更新车辆调度
	 * @param values
	 * @param params
	 */
	public void updateCsSchedulingByConfirm(Map values, Map params);
	/**
	 * 根据条件删除车辆调度
	 * @param params
	 */
	public void deleteCsSchedulingByConfirm(Map params);

}