package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsUnitOrderDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnitOrder;
import com.ccclubs.service.admin.ICsUnitOrderService;
import com.lazy3q.web.helper.$;

/**
 * 企业订单的Service实现
 * @author 飞啊飘啊
 */
public class CsUnitOrderService implements ICsUnitOrderService
{
	ICsUnitOrderDao csUnitOrderDao;
	

	/**
	 * 获取所有企业订单
	 * @return
	 */
	public List<CsUnitOrder> getCsUnitOrderList(Map params,Integer size)
	{
		return csUnitOrderDao.getCsUnitOrderList(params,size);
	}
	
	/**
	 * 获取企业订单统计
	 * @return
	 */
	public List getCsUnitOrderStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csUnitOrderDao.getCsUnitOrderStats(params,groups,sums);
	}
	
	/**
	 * 获取企业订单总数
	 * @return
	 */
	public Long getCsUnitOrderCount(Map params)
	{
		return csUnitOrderDao.getCsUnitOrderCount(params);
	}
	
	/**
	 * 获取企业订单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUnitOrderEval(String eval,Map params)
	{
		return csUnitOrderDao.getCsUnitOrderEval(eval,params);
	}

	/**
	 * 获取企业订单分页
	 * @return
	 */
	public Page getCsUnitOrderPage(int page,int size,Map params)
	{
		return csUnitOrderDao.getCsUnitOrderPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取企业订单
	 * @param params
	 * @return
	 */
	public CsUnitOrder getCsUnitOrder(Map params)
	{
		return csUnitOrderDao.getCsUnitOrder(params);
	}

	/**
	 * 根据ID取企业订单
	 * @param id
	 * @return
	 */
	public CsUnitOrder getCsUnitOrderById(Long id)
	{
		return csUnitOrderDao.getCsUnitOrderById(id);
	}

	/**
	 * 保存企业订单
	 * @param csUnitOrder
	 */
	public CsUnitOrder saveCsUnitOrder(CsUnitOrder csUnitOrder)
	{
		return csUnitOrderDao.saveCsUnitOrder(csUnitOrder);
	}
	
	/**
	 * 更新企业订单
	 * @param csUnitOrder
	 */
	public void updateCsUnitOrder(CsUnitOrder csUnitOrder)
	{
		csUnitOrderDao.updateCsUnitOrder(csUnitOrder);
	}
	/**
	 * 更新企业订单非空字段
	 * @param csUnitOrder
	 */
	public void updateCsUnitOrder$NotNull(CsUnitOrder csUnitOrder)
	{
		csUnitOrderDao.updateCsUnitOrder$NotNull(csUnitOrder);
	}
	
	/**
	 * 根据ID删除一个企业订单
	 * @param id
	 */
	public void deleteCsUnitOrderById(Long id)
	{
		csUnitOrderDao.deleteCsUnitOrderById(id);
	}
	/**
	 * 根据ID逻辑删除一个企业订单
	 * @param id
	 */
	public void removeCsUnitOrderById(Long id)
	{
		csUnitOrderDao.removeCsUnitOrderById(id);
	}
	
	/**
	 * 根据条件更新企业订单
	 * @param values
	 * @param params
	 */
	public void updateCsUnitOrderByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsUnitOrderBy时无条件");
		csUnitOrderDao.updateCsUnitOrderByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除企业订单
	 * @param params
	 */
	public void deleteCsUnitOrderByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsUnitOrderBy时无条件");
		csUnitOrderDao.deleteCsUnitOrderByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsUnitOrderDao getCsUnitOrderDao()
	{
		return csUnitOrderDao;
	}

	public void setCsUnitOrderDao(ICsUnitOrderDao csUnitOrderDao)
	{
		this.csUnitOrderDao = csUnitOrderDao;
	}
}