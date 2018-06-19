package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsShopOrderItemDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsShopOrderItem;
import com.ccclubs.service.admin.ICsShopOrderItemService;
import com.lazy3q.web.helper.$;

/**
 * 订单明细的Service实现
 * @author 飞啊飘啊
 */
public class CsShopOrderItemService implements ICsShopOrderItemService
{
	ICsShopOrderItemDao csShopOrderItemDao;
	

	/**
	 * 获取所有订单明细
	 * @return
	 */
	public List<CsShopOrderItem> getCsShopOrderItemList(Map params,Integer size)
	{
		return csShopOrderItemDao.getCsShopOrderItemList(params,size);
	}
	
	/**
	 * 获取订单明细统计
	 * @return
	 */
	public List getCsShopOrderItemStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csShopOrderItemDao.getCsShopOrderItemStats(params,groups,sums);
	}
	
	/**
	 * 获取订单明细总数
	 * @return
	 */
	public Long getCsShopOrderItemCount(Map params)
	{
		return csShopOrderItemDao.getCsShopOrderItemCount(params);
	}
	
	/**
	 * 获取订单明细自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsShopOrderItemEval(String eval,Map params)
	{
		return csShopOrderItemDao.getCsShopOrderItemEval(eval,params);
	}

	/**
	 * 获取订单明细分页
	 * @return
	 */
	public Page getCsShopOrderItemPage(int page,int size,Map params)
	{
		return csShopOrderItemDao.getCsShopOrderItemPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取订单明细
	 * @param params
	 * @return
	 */
	public CsShopOrderItem getCsShopOrderItem(Map params)
	{
		return csShopOrderItemDao.getCsShopOrderItem(params);
	}

	/**
	 * 根据ID取订单明细
	 * @param id
	 * @return
	 */
	public CsShopOrderItem getCsShopOrderItemById(Long id)
	{
		return csShopOrderItemDao.getCsShopOrderItemById(id);
	}

	/**
	 * 保存订单明细
	 * @param csShopOrderItem
	 */
	public CsShopOrderItem saveCsShopOrderItem(CsShopOrderItem csShopOrderItem)
	{
		return csShopOrderItemDao.saveCsShopOrderItem(csShopOrderItem);
	}
	
	/**
	 * 更新订单明细
	 * @param csShopOrderItem
	 */
	public void updateCsShopOrderItem(CsShopOrderItem csShopOrderItem)
	{
		csShopOrderItemDao.updateCsShopOrderItem(csShopOrderItem);
	}
	/**
	 * 更新订单明细非空字段
	 * @param csShopOrderItem
	 */
	public void updateCsShopOrderItem$NotNull(CsShopOrderItem csShopOrderItem)
	{
		csShopOrderItemDao.updateCsShopOrderItem$NotNull(csShopOrderItem);
	}
	
	/**
	 * 根据ID删除一个订单明细
	 * @param id
	 */
	public void deleteCsShopOrderItemById(Long id)
	{
		csShopOrderItemDao.deleteCsShopOrderItemById(id);
	}
	/**
	 * 根据ID逻辑删除一个订单明细
	 * @param id
	 */
	public void removeCsShopOrderItemById(Long id)
	{
		csShopOrderItemDao.removeCsShopOrderItemById(id);
	}
	
	/**
	 * 根据条件更新订单明细
	 * @param values
	 * @param params
	 */
	public void updateCsShopOrderItemByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsShopOrderItemBy时无条件");
		csShopOrderItemDao.updateCsShopOrderItemByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除订单明细
	 * @param params
	 */
	public void deleteCsShopOrderItemByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsShopOrderItemBy时无条件");
		csShopOrderItemDao.deleteCsShopOrderItemByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsShopOrderItemDao getCsShopOrderItemDao()
	{
		return csShopOrderItemDao;
	}

	public void setCsShopOrderItemDao(ICsShopOrderItemDao csShopOrderItemDao)
	{
		this.csShopOrderItemDao = csShopOrderItemDao;
	}
}