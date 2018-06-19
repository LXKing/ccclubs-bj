package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsLongOrder;

/**
 * 长租订单的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsLongOrderDao
{
	/**
	 * 获取所有长租订单
	 * @return
	 */
	public List<CsLongOrder> getCsLongOrderList(Map params,Integer size);
	
	/**
	 * 获取长租订单统计
	 * @return
	 */
	public List getCsLongOrderStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取长租订单总数
	 * @return
	 */	
	public Long getCsLongOrderCount(Map params);
	
	/**
	 * 获取长租订单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsLongOrderEval(String eval,Map params);
	
	/**
	 * 获取长租订单分页
	 * @return
	 */	
	public Page getCsLongOrderPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取长租订单
	 * @param params
	 * @return
	 */
	public CsLongOrder getCsLongOrder(Map params);	

	/**
	 * 根据ID取长租订单
	 * @param id
	 * @return
	 */
	public CsLongOrder getCsLongOrderById(Long id);


	
	

	/**
	 * 保存长租订单
	 * @param csLongOrder
	 */
	public CsLongOrder saveCsLongOrder(CsLongOrder csLongOrder);

	/**
	 * 更新长租订单
	 * @param csLongOrder
	 */
	public void updateCsLongOrder(CsLongOrder csLongOrder);
	/**
	 * 更新长租订单非空字段
	 * @param csLongOrder
	 */
	public void updateCsLongOrder$NotNull(CsLongOrder csLongOrder);

	/**
	 * 根据ID删除一个长租订单
	 * @param id
	 */
	public void deleteCsLongOrderById(Long id);
	/**
	 * 根据ID逻辑删除一个长租订单
	 * @param id
	 */
	public void removeCsLongOrderById(Long id);
	/**
	 * 根据条件更新长租订单
	 * @param values
	 * @param params
	 */
	public void updateCsLongOrderByConfirm(Map values, Map params);
	/**
	 * 根据条件删除长租订单
	 * @param params
	 */
	public void deleteCsLongOrderByConfirm(Map params);

}