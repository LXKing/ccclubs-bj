package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsUnitOrder;

/**
 * 企业订单的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsUnitOrderService
{
	/**
	 * 获取所有企业订单
	 * @return
	 */
	public List<CsUnitOrder> getCsUnitOrderList(Map params,Integer size);
	
	/**
	 * 获取企业订单统计
	 * @return
	 */
	public List getCsUnitOrderStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取企业订单总数
	 * @return
	 */	
	public Long getCsUnitOrderCount(Map params);
	
	
	/**
	 * 获取企业订单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUnitOrderEval(String eval,Map params);

	/**
	 * 获取企业订单分页
	 * @return
	 */	
	public Page getCsUnitOrderPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取企业订单
	 * @param params
	 * @return
	 */
	public CsUnitOrder getCsUnitOrder(Map params);

	/**
	 * 根据ID取企业订单
	 * @param id
	 * @return
	 */
	public CsUnitOrder getCsUnitOrderById(Long id);


	/**
	 * 保存企业订单
	 * @param csUnitOrder
	 */
	public CsUnitOrder saveCsUnitOrder(CsUnitOrder csUnitOrder);

	/**
	 * 更新企业订单
	 * @param csUnitOrder
	 */
	public void updateCsUnitOrder(CsUnitOrder csUnitOrder);
	/**
	 * 更新企业订单非空字段
	 * @param csUnitOrder
	 */
	public void updateCsUnitOrder$NotNull(CsUnitOrder csUnitOrder);

	/**
	 * 根据ID删除一个企业订单
	 * @param id
	 */
	public void deleteCsUnitOrderById(Long id);
	/**
	 * 根据ID逻辑删除一个企业订单
	 * @param id
	 */
	public void removeCsUnitOrderById(Long id);
	/**
	 * 根据条件更新企业订单
	 * @param values
	 * @param params
	 */
	public void updateCsUnitOrderByConfirm(Map values, Map params);
	/**
	 * 根据条件删除企业订单
	 * @param params
	 */
	public void deleteCsUnitOrderByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}