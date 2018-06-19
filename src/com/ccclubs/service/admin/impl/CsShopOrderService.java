package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsShopOrderDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsShopOrder;
import com.ccclubs.service.admin.ICsShopOrderService;
import com.lazy3q.web.helper.$;

/**
 * 商城订单的Service实现
 * @author 飞啊飘啊
 */
public class CsShopOrderService implements ICsShopOrderService
{
	ICsShopOrderDao csShopOrderDao;
	

	/**
	 * 获取所有商城订单
	 * @return
	 */
	public List<CsShopOrder> getCsShopOrderList(Map params,Integer size)
	{
		return csShopOrderDao.getCsShopOrderList(params,size);
	}
	
	/**
	 * 获取商城订单统计
	 * @return
	 */
	public List getCsShopOrderStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csShopOrderDao.getCsShopOrderStats(params,groups,sums);
	}
	
	/**
	 * 获取商城订单总数
	 * @return
	 */
	public Long getCsShopOrderCount(Map params)
	{
		return csShopOrderDao.getCsShopOrderCount(params);
	}
	
	/**
	 * 获取商城订单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsShopOrderEval(String eval,Map params)
	{
		return csShopOrderDao.getCsShopOrderEval(eval,params);
	}

	/**
	 * 获取商城订单分页
	 * @return
	 */
	public Page getCsShopOrderPage(int page,int size,Map params)
	{
		return csShopOrderDao.getCsShopOrderPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取商城订单
	 * @param params
	 * @return
	 */
	public CsShopOrder getCsShopOrder(Map params)
	{
		return csShopOrderDao.getCsShopOrder(params);
	}

	/**
	 * 根据ID取商城订单
	 * @param id
	 * @return
	 */
	public CsShopOrder getCsShopOrderById(Long id)
	{
		return csShopOrderDao.getCsShopOrderById(id);
	}

	/**
	 * 保存商城订单
	 * @param csShopOrder
	 */
	public CsShopOrder saveCsShopOrder(CsShopOrder csShopOrder)
	{
		return csShopOrderDao.saveCsShopOrder(csShopOrder);
	}
	
	/**
	 * 更新商城订单
	 * @param csShopOrder
	 */
	public void updateCsShopOrder(CsShopOrder csShopOrder)
	{
		csShopOrderDao.updateCsShopOrder(csShopOrder);
	}
	/**
	 * 更新商城订单非空字段
	 * @param csShopOrder
	 */
	public void updateCsShopOrder$NotNull(CsShopOrder csShopOrder)
	{
		csShopOrderDao.updateCsShopOrder$NotNull(csShopOrder);
	}
	
	/**
	 * 根据ID删除一个商城订单
	 * @param id
	 */
	public void deleteCsShopOrderById(Long id)
	{
		csShopOrderDao.deleteCsShopOrderById(id);
	}
	/**
	 * 根据ID逻辑删除一个商城订单
	 * @param id
	 */
	public void removeCsShopOrderById(Long id)
	{
		csShopOrderDao.removeCsShopOrderById(id);
	}
	
	/**
	 * 根据条件更新商城订单
	 * @param values
	 * @param params
	 */
	public void updateCsShopOrderByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsShopOrderBy时无条件");
		csShopOrderDao.updateCsShopOrderByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除商城订单
	 * @param params
	 */
	public void deleteCsShopOrderByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsShopOrderBy时无条件");
		csShopOrderDao.deleteCsShopOrderByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsShopOrderDao getCsShopOrderDao()
	{
		return csShopOrderDao;
	}

	public void setCsShopOrderDao(ICsShopOrderDao csShopOrderDao)
	{
		this.csShopOrderDao = csShopOrderDao;
	}
}