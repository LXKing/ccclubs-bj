package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsChargeStation;

/**
 * 充电站的Service接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsChargeStationService
{
	/**
	 * 获取所有充电站
	 * @return
	 */
	public List<CsChargeStation> getCsChargeStationList(Map params,Integer size);
	
	/**
	 * 获取充电站统计
	 * @return
	 */
	public List getCsChargeStationStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取充电站总数
	 * @return
	 */	
	public Long getCsChargeStationCount(Map params);
	
	
	/**
	 * 获取充电站自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsChargeStationEval(String eval,Map params);

	/**
	 * 获取充电站分页
	 * @return
	 */	
	public Page getCsChargeStationPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取充电站
	 * @param params
	 * @return
	 */
	public CsChargeStation getCsChargeStation(Map params);

	/**
	 * 根据ID取充电站
	 * @param id
	 * @return
	 */
	public CsChargeStation getCsChargeStationById(Long id);


	/**
	 * 保存充电站
	 * @param csChargeStation
	 */
	public CsChargeStation saveCsChargeStation(CsChargeStation csChargeStation);

	/**
	 * 更新充电站
	 * @param csChargeStation
	 */
	public void updateCsChargeStation(CsChargeStation csChargeStation);
	/**
	 * 更新充电站非空字段
	 * @param csChargeStation
	 */
	public void updateCsChargeStation$NotNull(CsChargeStation csChargeStation);

	/**
	 * 根据ID删除一个充电站
	 * @param id
	 */
	public void deleteCsChargeStationById(Long id);
	/**
	 * 根据ID逻辑删除一个充电站
	 * @param id
	 */
	public void removeCsChargeStationById(Long id);
	/**
	 * 根据条件更新充电站
	 * @param values
	 * @param params
	 */
	public void updateCsChargeStationByConfirm(Map values, Map params);
	/**
	 * 根据条件删除充电站
	 * @param params
	 */
	public void deleteCsChargeStationByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}