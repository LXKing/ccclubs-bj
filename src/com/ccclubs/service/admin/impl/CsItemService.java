package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsItemDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsItem;
import com.ccclubs.service.admin.ICsItemService;
import com.lazy3q.web.helper.$;

/**
 * 商品信息的Service实现
 * @author 飞啊飘啊
 */
public class CsItemService implements ICsItemService
{
	ICsItemDao csItemDao;
	

	/**
	 * 获取所有商品信息
	 * @return
	 */
	public List<CsItem> getCsItemList(Map params,Integer size)
	{
		return csItemDao.getCsItemList(params,size);
	}
	
	/**
	 * 获取商品信息统计
	 * @return
	 */
	public List getCsItemStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csItemDao.getCsItemStats(params,groups,sums);
	}
	
	/**
	 * 获取商品信息总数
	 * @return
	 */
	public Long getCsItemCount(Map params)
	{
		return csItemDao.getCsItemCount(params);
	}
	
	/**
	 * 获取商品信息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsItemEval(String eval,Map params)
	{
		return csItemDao.getCsItemEval(eval,params);
	}

	/**
	 * 获取商品信息分页
	 * @return
	 */
	public Page getCsItemPage(int page,int size,Map params)
	{
		return csItemDao.getCsItemPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取商品信息
	 * @param params
	 * @return
	 */
	public CsItem getCsItem(Map params)
	{
		return csItemDao.getCsItem(params);
	}

	/**
	 * 根据ID取商品信息
	 * @param id
	 * @return
	 */
	public CsItem getCsItemById(Long id)
	{
		return csItemDao.getCsItemById(id);
	}

	/**
	 * 保存商品信息
	 * @param csItem
	 */
	public CsItem saveCsItem(CsItem csItem)
	{
		return csItemDao.saveCsItem(csItem);
	}
	
	/**
	 * 更新商品信息
	 * @param csItem
	 */
	public void updateCsItem(CsItem csItem)
	{
		csItemDao.updateCsItem(csItem);
	}
	/**
	 * 更新商品信息非空字段
	 * @param csItem
	 */
	public void updateCsItem$NotNull(CsItem csItem)
	{
		csItemDao.updateCsItem$NotNull(csItem);
	}
	
	/**
	 * 根据ID删除一个商品信息
	 * @param id
	 */
	public void deleteCsItemById(Long id)
	{
		csItemDao.deleteCsItemById(id);
	}
	/**
	 * 根据ID逻辑删除一个商品信息
	 * @param id
	 */
	public void removeCsItemById(Long id)
	{
		csItemDao.removeCsItemById(id);
	}
	
	/**
	 * 根据条件更新商品信息
	 * @param values
	 * @param params
	 */
	public void updateCsItemByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsItemBy时无条件");
		csItemDao.updateCsItemByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除商品信息
	 * @param params
	 */
	public void deleteCsItemByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsItemBy时无条件");
		csItemDao.deleteCsItemByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsItemDao getCsItemDao()
	{
		return csItemDao;
	}

	public void setCsItemDao(ICsItemDao csItemDao)
	{
		this.csItemDao = csItemDao;
	}
}