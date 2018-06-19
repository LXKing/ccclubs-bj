package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsWxOrderDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsWxOrder;
import com.ccclubs.service.admin.ICsWxOrderService;
import com.lazy3q.web.helper.$;

/**
 * 微信指令的Service实现
 * @author 飞啊飘啊
 */
public class CsWxOrderService implements ICsWxOrderService
{
	ICsWxOrderDao csWxOrderDao;
	

	/**
	 * 获取所有微信指令
	 * @return
	 */
	public List<CsWxOrder> getCsWxOrderList(Map params,Integer size)
	{
		return csWxOrderDao.getCsWxOrderList(params,size);
	}
	
	/**
	 * 获取微信指令统计
	 * @return
	 */
	public List getCsWxOrderStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csWxOrderDao.getCsWxOrderStats(params,groups,sums);
	}
	
	/**
	 * 获取微信指令总数
	 * @return
	 */
	public Long getCsWxOrderCount(Map params)
	{
		return csWxOrderDao.getCsWxOrderCount(params);
	}
	
	/**
	 * 获取微信指令自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsWxOrderEval(String eval,Map params)
	{
		return csWxOrderDao.getCsWxOrderEval(eval,params);
	}

	/**
	 * 获取微信指令分页
	 * @return
	 */
	public Page getCsWxOrderPage(int page,int size,Map params)
	{
		return csWxOrderDao.getCsWxOrderPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取微信指令
	 * @param params
	 * @return
	 */
	public CsWxOrder getCsWxOrder(Map params)
	{
		return csWxOrderDao.getCsWxOrder(params);
	}

	/**
	 * 根据ID取微信指令
	 * @param id
	 * @return
	 */
	public CsWxOrder getCsWxOrderById(Long id)
	{
		return csWxOrderDao.getCsWxOrderById(id);
	}

	/**
	 * 保存微信指令
	 * @param csWxOrder
	 */
	public CsWxOrder saveCsWxOrder(CsWxOrder csWxOrder)
	{
		return csWxOrderDao.saveCsWxOrder(csWxOrder);
	}
	
	/**
	 * 更新微信指令
	 * @param csWxOrder
	 */
	public void updateCsWxOrder(CsWxOrder csWxOrder)
	{
		csWxOrderDao.updateCsWxOrder(csWxOrder);
	}
	/**
	 * 更新微信指令非空字段
	 * @param csWxOrder
	 */
	public void updateCsWxOrder$NotNull(CsWxOrder csWxOrder)
	{
		csWxOrderDao.updateCsWxOrder$NotNull(csWxOrder);
	}
	
	/**
	 * 根据ID删除一个微信指令
	 * @param id
	 */
	public void deleteCsWxOrderById(Long id)
	{
		csWxOrderDao.deleteCsWxOrderById(id);
	}
	/**
	 * 根据ID逻辑删除一个微信指令
	 * @param id
	 */
	public void removeCsWxOrderById(Long id)
	{
		csWxOrderDao.removeCsWxOrderById(id);
	}
	
	/**
	 * 根据条件更新微信指令
	 * @param values
	 * @param params
	 */
	public void updateCsWxOrderByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsWxOrderBy时无条件");
		csWxOrderDao.updateCsWxOrderByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除微信指令
	 * @param params
	 */
	public void deleteCsWxOrderByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsWxOrderBy时无条件");
		csWxOrderDao.deleteCsWxOrderByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsWxOrderDao getCsWxOrderDao()
	{
		return csWxOrderDao;
	}

	public void setCsWxOrderDao(ICsWxOrderDao csWxOrderDao)
	{
		this.csWxOrderDao = csWxOrderDao;
	}
}