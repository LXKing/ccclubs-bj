package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsShopOrderItem;

/**
 * 订单明细的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsShopOrderItemService
{
	/**
	 * 获取所有订单明细
	 * @return
	 */
	public List<CsShopOrderItem> getCsShopOrderItemList(Map params,Integer size);
	
	/**
	 * 获取订单明细统计
	 * @return
	 */
	public List getCsShopOrderItemStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取订单明细总数
	 * @return
	 */	
	public Long getCsShopOrderItemCount(Map params);
	
	
	/**
	 * 获取订单明细自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsShopOrderItemEval(String eval,Map params);

	/**
	 * 获取订单明细分页
	 * @return
	 */	
	public Page getCsShopOrderItemPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取订单明细
	 * @param params
	 * @return
	 */
	public CsShopOrderItem getCsShopOrderItem(Map params);

	/**
	 * 根据ID取订单明细
	 * @param id
	 * @return
	 */
	public CsShopOrderItem getCsShopOrderItemById(Long id);


	/**
	 * 保存订单明细
	 * @param csShopOrderItem
	 */
	public CsShopOrderItem saveCsShopOrderItem(CsShopOrderItem csShopOrderItem);

	/**
	 * 更新订单明细
	 * @param csShopOrderItem
	 */
	public void updateCsShopOrderItem(CsShopOrderItem csShopOrderItem);
	/**
	 * 更新订单明细非空字段
	 * @param csShopOrderItem
	 */
	public void updateCsShopOrderItem$NotNull(CsShopOrderItem csShopOrderItem);

	/**
	 * 根据ID删除一个订单明细
	 * @param id
	 */
	public void deleteCsShopOrderItemById(Long id);
	/**
	 * 根据ID逻辑删除一个订单明细
	 * @param id
	 */
	public void removeCsShopOrderItemById(Long id);
	/**
	 * 根据条件更新订单明细
	 * @param values
	 * @param params
	 */
	public void updateCsShopOrderItemByConfirm(Map values, Map params);
	/**
	 * 根据条件删除订单明细
	 * @param params
	 */
	public void deleteCsShopOrderItemByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}