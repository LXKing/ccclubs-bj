package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsRepair;

/**
 * 车辆维修的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsRepairService
{
	/**
	 * 获取所有车辆维修
	 * @return
	 */
	public List<CsRepair> getCsRepairList(Map params,Integer size);
	
	/**
	 * 获取车辆维修统计
	 * @return
	 */
	public List getCsRepairStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取车辆维修总数
	 * @return
	 */	
	public Long getCsRepairCount(Map params);
	
	
	/**
	 * 获取车辆维修自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRepairEval(String eval,Map params);

	/**
	 * 获取车辆维修分页
	 * @return
	 */	
	public Page getCsRepairPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取车辆维修
	 * @param params
	 * @return
	 */
	public CsRepair getCsRepair(Map params);

	/**
	 * 根据ID取车辆维修
	 * @param id
	 * @return
	 */
	public CsRepair getCsRepairById(Long id);


	/**
	 * 保存车辆维修
	 * @param csRepair
	 */
	public CsRepair saveCsRepair(CsRepair csRepair);

	/**
	 * 更新车辆维修
	 * @param csRepair
	 */
	public void updateCsRepair(CsRepair csRepair);
	/**
	 * 更新车辆维修非空字段
	 * @param csRepair
	 */
	public void updateCsRepair$NotNull(CsRepair csRepair);

	/**
	 * 根据ID删除一个车辆维修
	 * @param id
	 */
	public void deleteCsRepairById(Long id);
	/**
	 * 根据ID逻辑删除一个车辆维修
	 * @param id
	 */
	public void removeCsRepairById(Long id);
	/**
	 * 根据条件更新车辆维修
	 * @param values
	 * @param params
	 */
	public void updateCsRepairByConfirm(Map values, Map params);
	/**
	 * 根据条件删除车辆维修
	 * @param params
	 */
	public void deleteCsRepairByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}