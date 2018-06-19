package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsCartItemDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCartItem;
import com.ccclubs.service.admin.ICsCartItemService;
import com.lazy3q.web.helper.$;

/**
 * 购物车项的Service实现
 * @author 飞啊飘啊
 */
public class CsCartItemService implements ICsCartItemService
{
	ICsCartItemDao csCartItemDao;
	

	/**
	 * 获取所有购物车项
	 * @return
	 */
	public List<CsCartItem> getCsCartItemList(Map params,Integer size)
	{
		return csCartItemDao.getCsCartItemList(params,size);
	}
	
	/**
	 * 获取购物车项统计
	 * @return
	 */
	public List getCsCartItemStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csCartItemDao.getCsCartItemStats(params,groups,sums);
	}
	
	/**
	 * 获取购物车项总数
	 * @return
	 */
	public Long getCsCartItemCount(Map params)
	{
		return csCartItemDao.getCsCartItemCount(params);
	}
	
	/**
	 * 获取购物车项自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCartItemEval(String eval,Map params)
	{
		return csCartItemDao.getCsCartItemEval(eval,params);
	}

	/**
	 * 获取购物车项分页
	 * @return
	 */
	public Page getCsCartItemPage(int page,int size,Map params)
	{
		return csCartItemDao.getCsCartItemPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取购物车项
	 * @param params
	 * @return
	 */
	public CsCartItem getCsCartItem(Map params)
	{
		return csCartItemDao.getCsCartItem(params);
	}

	/**
	 * 根据ID取购物车项
	 * @param id
	 * @return
	 */
	public CsCartItem getCsCartItemById(Long id)
	{
		return csCartItemDao.getCsCartItemById(id);
	}

	/**
	 * 保存购物车项
	 * @param csCartItem
	 */
	public CsCartItem saveCsCartItem(CsCartItem csCartItem)
	{
		return csCartItemDao.saveCsCartItem(csCartItem);
	}
	
	/**
	 * 更新购物车项
	 * @param csCartItem
	 */
	public void updateCsCartItem(CsCartItem csCartItem)
	{
		csCartItemDao.updateCsCartItem(csCartItem);
	}
	/**
	 * 更新购物车项非空字段
	 * @param csCartItem
	 */
	public void updateCsCartItem$NotNull(CsCartItem csCartItem)
	{
		csCartItemDao.updateCsCartItem$NotNull(csCartItem);
	}
	
	/**
	 * 根据ID删除一个购物车项
	 * @param id
	 */
	public void deleteCsCartItemById(Long id)
	{
		csCartItemDao.deleteCsCartItemById(id);
	}
	
	/**
	 * 根据条件更新购物车项
	 * @param values
	 * @param params
	 */
	public void updateCsCartItemByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsCartItemBy时无条件");
		csCartItemDao.updateCsCartItemByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除购物车项
	 * @param params
	 */
	public void deleteCsCartItemByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsCartItemBy时无条件");
		csCartItemDao.deleteCsCartItemByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsCartItemDao getCsCartItemDao()
	{
		return csCartItemDao;
	}

	public void setCsCartItemDao(ICsCartItemDao csCartItemDao)
	{
		this.csCartItemDao = csCartItemDao;
	}
}