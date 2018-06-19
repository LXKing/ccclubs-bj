package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsPowerPileDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsPowerPile;
import com.ccclubs.service.admin.ICsPowerPileService;
import com.lazy3q.web.helper.$;

/**
 * 充电桩的Service实现
 * @author 飞啊飘啊
 */
public class CsPowerPileService implements ICsPowerPileService
{
	ICsPowerPileDao csPowerPileDao;
	

	/**
	 * 获取所有充电桩
	 * @return
	 */
	public List<CsPowerPile> getCsPowerPileList(Map params,Integer size)
	{
		return csPowerPileDao.getCsPowerPileList(params,size);
	}
	
	/**
	 * 获取充电桩统计
	 * @return
	 */
	public List getCsPowerPileStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csPowerPileDao.getCsPowerPileStats(params,groups,sums);
	}
	
	/**
	 * 获取充电桩总数
	 * @return
	 */
	public Long getCsPowerPileCount(Map params)
	{
		return csPowerPileDao.getCsPowerPileCount(params);
	}
	
	/**
	 * 获取充电桩自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsPowerPileEval(String eval,Map params)
	{
		return csPowerPileDao.getCsPowerPileEval(eval,params);
	}

	/**
	 * 获取充电桩分页
	 * @return
	 */
	public Page getCsPowerPilePage(int page,int size,Map params)
	{
		return csPowerPileDao.getCsPowerPilePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取充电桩
	 * @param params
	 * @return
	 */
	public CsPowerPile getCsPowerPile(Map params)
	{
		return csPowerPileDao.getCsPowerPile(params);
	}

	/**
	 * 根据ID取充电桩
	 * @param id
	 * @return
	 */
	public CsPowerPile getCsPowerPileById(Long id)
	{
		return csPowerPileDao.getCsPowerPileById(id);
	}

	/**
	 * 保存充电桩
	 * @param csPowerPile
	 */
	public CsPowerPile saveCsPowerPile(CsPowerPile csPowerPile)
	{
		return csPowerPileDao.saveCsPowerPile(csPowerPile);
	}
	
	/**
	 * 更新充电桩
	 * @param csPowerPile
	 */
	public void updateCsPowerPile(CsPowerPile csPowerPile)
	{
		csPowerPileDao.updateCsPowerPile(csPowerPile);
	}
	/**
	 * 更新充电桩非空字段
	 * @param csPowerPile
	 */
	public void updateCsPowerPile$NotNull(CsPowerPile csPowerPile)
	{
		csPowerPileDao.updateCsPowerPile$NotNull(csPowerPile);
	}
	
	/**
	 * 根据ID删除一个充电桩
	 * @param id
	 */
	public void deleteCsPowerPileById(Long id)
	{
		csPowerPileDao.deleteCsPowerPileById(id);
	}
	/**
	 * 根据ID逻辑删除一个充电桩
	 * @param id
	 */
	public void removeCsPowerPileById(Long id)
	{
		csPowerPileDao.removeCsPowerPileById(id);
	}
	
	/**
	 * 根据条件更新充电桩
	 * @param values
	 * @param params
	 */
	public void updateCsPowerPileByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsPowerPileBy时无条件");
		csPowerPileDao.updateCsPowerPileByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除充电桩
	 * @param params
	 */
	public void deleteCsPowerPileByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsPowerPileBy时无条件");
		csPowerPileDao.deleteCsPowerPileByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsPowerPileDao getCsPowerPileDao()
	{
		return csPowerPileDao;
	}

	public void setCsPowerPileDao(ICsPowerPileDao csPowerPileDao)
	{
		this.csPowerPileDao = csPowerPileDao;
	}
}