package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsLuckyItemDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsLuckyItem;
import com.ccclubs.service.admin.ICsLuckyItemService;
import com.lazy3q.web.helper.$;

/**
 * 抽奖奖项的Service实现
 * @author 飞啊飘啊
 */
public class CsLuckyItemService implements ICsLuckyItemService
{
	ICsLuckyItemDao csLuckyItemDao;
	

	/**
	 * 获取所有抽奖奖项
	 * @return
	 */
	public List<CsLuckyItem> getCsLuckyItemList(Map params,Integer size)
	{
		return csLuckyItemDao.getCsLuckyItemList(params,size);
	}
	
	/**
	 * 获取抽奖奖项统计
	 * @return
	 */
	public List getCsLuckyItemStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csLuckyItemDao.getCsLuckyItemStats(params,groups,sums);
	}
	
	/**
	 * 获取抽奖奖项总数
	 * @return
	 */
	public Long getCsLuckyItemCount(Map params)
	{
		return csLuckyItemDao.getCsLuckyItemCount(params);
	}
	
	/**
	 * 获取抽奖奖项自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsLuckyItemEval(String eval,Map params)
	{
		return csLuckyItemDao.getCsLuckyItemEval(eval,params);
	}

	/**
	 * 获取抽奖奖项分页
	 * @return
	 */
	public Page getCsLuckyItemPage(int page,int size,Map params)
	{
		return csLuckyItemDao.getCsLuckyItemPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取抽奖奖项
	 * @param params
	 * @return
	 */
	public CsLuckyItem getCsLuckyItem(Map params)
	{
		return csLuckyItemDao.getCsLuckyItem(params);
	}

	/**
	 * 根据ID取抽奖奖项
	 * @param id
	 * @return
	 */
	public CsLuckyItem getCsLuckyItemById(Long id)
	{
		return csLuckyItemDao.getCsLuckyItemById(id);
	}

	/**
	 * 保存抽奖奖项
	 * @param csLuckyItem
	 */
	public CsLuckyItem saveCsLuckyItem(CsLuckyItem csLuckyItem)
	{
		return csLuckyItemDao.saveCsLuckyItem(csLuckyItem);
	}
	
	/**
	 * 更新抽奖奖项
	 * @param csLuckyItem
	 */
	public void updateCsLuckyItem(CsLuckyItem csLuckyItem)
	{
		csLuckyItemDao.updateCsLuckyItem(csLuckyItem);
	}
	/**
	 * 更新抽奖奖项非空字段
	 * @param csLuckyItem
	 */
	public void updateCsLuckyItem$NotNull(CsLuckyItem csLuckyItem)
	{
		csLuckyItemDao.updateCsLuckyItem$NotNull(csLuckyItem);
	}
	
	/**
	 * 根据ID删除一个抽奖奖项
	 * @param id
	 */
	public void deleteCsLuckyItemById(Long id)
	{
		csLuckyItemDao.deleteCsLuckyItemById(id);
	}
	/**
	 * 根据ID逻辑删除一个抽奖奖项
	 * @param id
	 */
	public void removeCsLuckyItemById(Long id)
	{
		csLuckyItemDao.removeCsLuckyItemById(id);
	}
	
	/**
	 * 根据条件更新抽奖奖项
	 * @param values
	 * @param params
	 */
	public void updateCsLuckyItemByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsLuckyItemBy时无条件");
		csLuckyItemDao.updateCsLuckyItemByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除抽奖奖项
	 * @param params
	 */
	public void deleteCsLuckyItemByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsLuckyItemBy时无条件");
		csLuckyItemDao.deleteCsLuckyItemByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsLuckyItemDao getCsLuckyItemDao()
	{
		return csLuckyItemDao;
	}

	public void setCsLuckyItemDao(ICsLuckyItemDao csLuckyItemDao)
	{
		this.csLuckyItemDao = csLuckyItemDao;
	}
}