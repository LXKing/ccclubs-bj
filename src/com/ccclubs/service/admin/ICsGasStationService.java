package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsGasStation;

/**
 * 加油站的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsGasStationService
{
	/**
	 * 获取所有加油站
	 * @return
	 */
	public List<CsGasStation> getCsGasStationList(Map params,Integer size);
	
	/**
	 * 获取加油站统计
	 * @return
	 */
	public List getCsGasStationStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取加油站总数
	 * @return
	 */	
	public Long getCsGasStationCount(Map params);
	
	
	/**
	 * 获取加油站自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsGasStationEval(String eval,Map params);

	/**
	 * 获取加油站分页
	 * @return
	 */	
	public Page getCsGasStationPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取加油站
	 * @param params
	 * @return
	 */
	public CsGasStation getCsGasStation(Map params);

	/**
	 * 根据ID取加油站
	 * @param id
	 * @return
	 */
	public CsGasStation getCsGasStationById(Long id);


	/**
	 * 保存加油站
	 * @param csGasStation
	 */
	public CsGasStation saveCsGasStation(CsGasStation csGasStation);

	/**
	 * 更新加油站
	 * @param csGasStation
	 */
	public void updateCsGasStation(CsGasStation csGasStation);
	/**
	 * 更新加油站非空字段
	 * @param csGasStation
	 */
	public void updateCsGasStation$NotNull(CsGasStation csGasStation);

	/**
	 * 根据ID删除一个加油站
	 * @param id
	 */
	public void deleteCsGasStationById(Long id);
	/**
	 * 根据ID逻辑删除一个加油站
	 * @param id
	 */
	public void removeCsGasStationById(Long id);
	/**
	 * 根据条件更新加油站
	 * @param values
	 * @param params
	 */
	public void updateCsGasStationByConfirm(Map values, Map params);
	/**
	 * 根据条件删除加油站
	 * @param params
	 */
	public void deleteCsGasStationByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}