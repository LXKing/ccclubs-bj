package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsOrderDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsOrder;
import com.ccclubs.service.admin.ICsOrderService;
import com.lazy3q.web.helper.$;

/**
 * 系统订单的Service实现
 * @author 飞啊飘啊
 */
public class CsOrderService implements ICsOrderService
{
	ICsOrderDao csOrderDao;
	

	/**
	 * 获取所有系统订单
	 * @return
	 */
	public List<CsOrder> getCsOrderList(Map params,Integer size)
	{
		return csOrderDao.getCsOrderList(params,size);
	}
	
	/**
	 * 获取系统订单统计
	 * @return
	 */
	public List getCsOrderStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csOrderDao.getCsOrderStats(params,groups,sums);
	}
	
	/**
	 * 获取系统订单总数
	 * @return
	 */
	public Long getCsOrderCount(Map params)
	{
		return csOrderDao.getCsOrderCount(params);
	}
	
	/**
	 * 获取系统订单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsOrderEval(String eval,Map params)
	{
		return csOrderDao.getCsOrderEval(eval,params);
	}

	/**
	 * 获取系统订单分页
	 * @return
	 */
	public Page getCsOrderPage(int page,int size,Map params)
	{
		return csOrderDao.getCsOrderPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取系统订单
	 * @param params
	 * @return
	 */
	public CsOrder getCsOrder(Map params)
	{
		return csOrderDao.getCsOrder(params);
	}

	/**
	 * 根据ID取系统订单
	 * @param id
	 * @return
	 */
	public CsOrder getCsOrderById(Long id)
	{
		return csOrderDao.getCsOrderById(id);
	}

	/**
	 * 保存系统订单
	 * @param csOrder
	 */
	public CsOrder saveCsOrder(CsOrder csOrder)
	{
		return csOrderDao.saveCsOrder(csOrder);
	}
	
	/**
	 * 更新系统订单
	 * @param csOrder
	 */
	public void updateCsOrder(CsOrder csOrder)
	{
		csOrderDao.updateCsOrder(csOrder);
	}
	/**
	 * 更新系统订单非空字段
	 * @param csOrder
	 */
	public void updateCsOrder$NotNull(CsOrder csOrder)
	{
		csOrderDao.updateCsOrder$NotNull(csOrder);
	}
	
	/**
	 * 根据ID删除一个系统订单
	 * @param id
	 */
	public void deleteCsOrderById(Long id)
	{
		csOrderDao.deleteCsOrderById(id);
	}
	/**
	 * 根据ID逻辑删除一个系统订单
	 * @param id
	 */
	public void removeCsOrderById(Long id)
	{
		csOrderDao.removeCsOrderById(id);
	}
	
	/**
	 * 根据条件更新系统订单
	 * @param values
	 * @param params
	 */
	public void updateCsOrderByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsOrderBy时无条件");
		csOrderDao.updateCsOrderByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除系统订单
	 * @param params
	 */
	public void deleteCsOrderByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsOrderBy时无条件");
		csOrderDao.deleteCsOrderByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsOrderDao getCsOrderDao()
	{
		return csOrderDao;
	}

	public void setCsOrderDao(ICsOrderDao csOrderDao)
	{
		this.csOrderDao = csOrderDao;
	}
}