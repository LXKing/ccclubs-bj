package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsUnitAirport;

/**
 * 机场服务的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsUnitAirportService
{
	/**
	 * 获取所有机场服务
	 * @return
	 */
	public List<CsUnitAirport> getCsUnitAirportList(Map params,Integer size);
	
	/**
	 * 获取机场服务统计
	 * @return
	 */
	public List getCsUnitAirportStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取机场服务总数
	 * @return
	 */	
	public Long getCsUnitAirportCount(Map params);
	
	
	/**
	 * 获取机场服务自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUnitAirportEval(String eval,Map params);

	/**
	 * 获取机场服务分页
	 * @return
	 */	
	public Page getCsUnitAirportPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取机场服务
	 * @param params
	 * @return
	 */
	public CsUnitAirport getCsUnitAirport(Map params);

	/**
	 * 根据ID取机场服务
	 * @param id
	 * @return
	 */
	public CsUnitAirport getCsUnitAirportById(Long id);


	/**
	 * 保存机场服务
	 * @param csUnitAirport
	 */
	public CsUnitAirport saveCsUnitAirport(CsUnitAirport csUnitAirport);

	/**
	 * 更新机场服务
	 * @param csUnitAirport
	 */
	public void updateCsUnitAirport(CsUnitAirport csUnitAirport);
	/**
	 * 更新机场服务非空字段
	 * @param csUnitAirport
	 */
	public void updateCsUnitAirport$NotNull(CsUnitAirport csUnitAirport);

	/**
	 * 根据ID删除一个机场服务
	 * @param id
	 */
	public void deleteCsUnitAirportById(Long id);
	/**
	 * 根据ID逻辑删除一个机场服务
	 * @param id
	 */
	public void removeCsUnitAirportById(Long id);
	/**
	 * 根据条件更新机场服务
	 * @param values
	 * @param params
	 */
	public void updateCsUnitAirportByConfirm(Map values, Map params);
	/**
	 * 根据条件删除机场服务
	 * @param params
	 */
	public void deleteCsUnitAirportByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}