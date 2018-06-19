package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsOrder;

/**
 * 系统订单的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsOrderService
{
	/**
	 * 获取所有系统订单
	 * @return
	 */
	public List<CsOrder> getCsOrderList(Map params,Integer size);
	
	/**
	 * 获取系统订单统计
	 * @return
	 */
	public List getCsOrderStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取系统订单总数
	 * @return
	 */	
	public Long getCsOrderCount(Map params);
	
	
	/**
	 * 获取系统订单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsOrderEval(String eval,Map params);

	/**
	 * 获取系统订单分页
	 * @return
	 */	
	public Page getCsOrderPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取系统订单
	 * @param params
	 * @return
	 */
	public CsOrder getCsOrder(Map params);

	/**
	 * 根据ID取系统订单
	 * @param id
	 * @return
	 */
	public CsOrder getCsOrderById(Long id);


	/**
	 * 保存系统订单
	 * @param csOrder
	 */
	public CsOrder saveCsOrder(CsOrder csOrder);

	/**
	 * 更新系统订单
	 * @param csOrder
	 */
	public void updateCsOrder(CsOrder csOrder);
	/**
	 * 更新系统订单非空字段
	 * @param csOrder
	 */
	public void updateCsOrder$NotNull(CsOrder csOrder);

	/**
	 * 根据ID删除一个系统订单
	 * @param id
	 */
	public void deleteCsOrderById(Long id);
	/**
	 * 根据ID逻辑删除一个系统订单
	 * @param id
	 */
	public void removeCsOrderById(Long id);
	/**
	 * 根据条件更新系统订单
	 * @param values
	 * @param params
	 */
	public void updateCsOrderByConfirm(Map values, Map params);
	/**
	 * 根据条件删除系统订单
	 * @param params
	 */
	public void deleteCsOrderByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}