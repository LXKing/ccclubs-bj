package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsShopOrder;

/**
 * 商城订单的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsShopOrderDao
{
	/**
	 * 获取所有商城订单
	 * @return
	 */
	public List<CsShopOrder> getCsShopOrderList(Map params,Integer size);
	
	/**
	 * 获取商城订单统计
	 * @return
	 */
	public List getCsShopOrderStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取商城订单总数
	 * @return
	 */	
	public Long getCsShopOrderCount(Map params);
	
	/**
	 * 获取商城订单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsShopOrderEval(String eval,Map params);
	
	/**
	 * 获取商城订单分页
	 * @return
	 */	
	public Page getCsShopOrderPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取商城订单
	 * @param params
	 * @return
	 */
	public CsShopOrder getCsShopOrder(Map params);	

	/**
	 * 根据ID取商城订单
	 * @param id
	 * @return
	 */
	public CsShopOrder getCsShopOrderById(Long id);


	
	

	/**
	 * 保存商城订单
	 * @param csShopOrder
	 */
	public CsShopOrder saveCsShopOrder(CsShopOrder csShopOrder);

	/**
	 * 更新商城订单
	 * @param csShopOrder
	 */
	public void updateCsShopOrder(CsShopOrder csShopOrder);
	/**
	 * 更新商城订单非空字段
	 * @param csShopOrder
	 */
	public void updateCsShopOrder$NotNull(CsShopOrder csShopOrder);

	/**
	 * 根据ID删除一个商城订单
	 * @param id
	 */
	public void deleteCsShopOrderById(Long id);
	/**
	 * 根据ID逻辑删除一个商城订单
	 * @param id
	 */
	public void removeCsShopOrderById(Long id);
	/**
	 * 根据条件更新商城订单
	 * @param values
	 * @param params
	 */
	public void updateCsShopOrderByConfirm(Map values, Map params);
	/**
	 * 根据条件删除商城订单
	 * @param params
	 */
	public void deleteCsShopOrderByConfirm(Map params);

}