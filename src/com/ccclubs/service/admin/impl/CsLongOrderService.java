package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsLongOrderDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsLongOrder;
import com.ccclubs.service.admin.ICsLongOrderService;
import com.lazy3q.web.helper.$;

/**
 * 长租订单的Service实现
 * @author 飞啊飘啊
 */
public class CsLongOrderService implements ICsLongOrderService
{
	ICsLongOrderDao csLongOrderDao;
	

	/**
	 * 获取所有长租订单
	 * @return
	 */
	public List<CsLongOrder> getCsLongOrderList(Map params,Integer size)
	{
		return csLongOrderDao.getCsLongOrderList(params,size);
	}
	
	/**
	 * 获取长租订单统计
	 * @return
	 */
	public List getCsLongOrderStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csLongOrderDao.getCsLongOrderStats(params,groups,sums);
	}
	
	/**
	 * 获取长租订单总数
	 * @return
	 */
	public Long getCsLongOrderCount(Map params)
	{
		return csLongOrderDao.getCsLongOrderCount(params);
	}
	
	/**
	 * 获取长租订单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsLongOrderEval(String eval,Map params)
	{
		return csLongOrderDao.getCsLongOrderEval(eval,params);
	}

	/**
	 * 获取长租订单分页
	 * @return
	 */
	public Page getCsLongOrderPage(int page,int size,Map params)
	{
		return csLongOrderDao.getCsLongOrderPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取长租订单
	 * @param params
	 * @return
	 */
	public CsLongOrder getCsLongOrder(Map params)
	{
		return csLongOrderDao.getCsLongOrder(params);
	}

	/**
	 * 根据ID取长租订单
	 * @param id
	 * @return
	 */
	public CsLongOrder getCsLongOrderById(Long id)
	{
		return csLongOrderDao.getCsLongOrderById(id);
	}

	/**
	 * 保存长租订单
	 * @param csLongOrder
	 */
	public CsLongOrder saveCsLongOrder(CsLongOrder csLongOrder)
	{
		return csLongOrderDao.saveCsLongOrder(csLongOrder);
	}
	
	/**
	 * 更新长租订单
	 * @param csLongOrder
	 */
	public void updateCsLongOrder(CsLongOrder csLongOrder)
	{
		csLongOrderDao.updateCsLongOrder(csLongOrder);
	}
	/**
	 * 更新长租订单非空字段
	 * @param csLongOrder
	 */
	public void updateCsLongOrder$NotNull(CsLongOrder csLongOrder)
	{
		csLongOrderDao.updateCsLongOrder$NotNull(csLongOrder);
	}
	
	/**
	 * 根据ID删除一个长租订单
	 * @param id
	 */
	public void deleteCsLongOrderById(Long id)
	{
		csLongOrderDao.deleteCsLongOrderById(id);
	}
	/**
	 * 根据ID逻辑删除一个长租订单
	 * @param id
	 */
	public void removeCsLongOrderById(Long id)
	{
		csLongOrderDao.removeCsLongOrderById(id);
	}
	
	/**
	 * 根据条件更新长租订单
	 * @param values
	 * @param params
	 */
	public void updateCsLongOrderByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsLongOrderBy时无条件");
		csLongOrderDao.updateCsLongOrderByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除长租订单
	 * @param params
	 */
	public void deleteCsLongOrderByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsLongOrderBy时无条件");
		csLongOrderDao.deleteCsLongOrderByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsLongOrderDao getCsLongOrderDao()
	{
		return csLongOrderDao;
	}

	public void setCsLongOrderDao(ICsLongOrderDao csLongOrderDao)
	{
		this.csLongOrderDao = csLongOrderDao;
	}
}