package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsCoinDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCoin;
import com.ccclubs.service.admin.ICsCoinService;
import com.lazy3q.web.helper.$;

/**
 * 红包的Service实现
 * @author Joel
 */
public class CsCoinService implements ICsCoinService
{
	ICsCoinDao csCoinDao;
	

	/**
	 * 获取所有红包
	 * @return
	 */
	public List<CsCoin> getCsCoinList(Map params,Integer size)
	{
		return csCoinDao.getCsCoinList(params,size);
	}
	
	/**
	 * 获取红包统计
	 * @return
	 */
	public List getCsCoinStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csCoinDao.getCsCoinStats(params,groups,sums);
	}
	
	/**
	 * 获取红包总数
	 * @return
	 */
	public Long getCsCoinCount(Map params)
	{
		return csCoinDao.getCsCoinCount(params);
	}
	
	/**
	 * 获取红包自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCoinEval(String eval,Map params)
	{
		return csCoinDao.getCsCoinEval(eval,params);
	}

	/**
	 * 获取红包分页
	 * @return
	 */
	public Page getCsCoinPage(int page,int size,Map params)
	{
		return csCoinDao.getCsCoinPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取红包
	 * @param params
	 * @return
	 */
	public CsCoin getCsCoin(Map params)
	{
		return csCoinDao.getCsCoin(params);
	}

	/**
	 * 根据ID取红包
	 * @param id
	 * @return
	 */
	public CsCoin getCsCoinById(Long id)
	{
		return csCoinDao.getCsCoinById(id);
	}

	/**
	 * 保存红包
	 * @param csCoin
	 */
	public CsCoin saveCsCoin(CsCoin csCoin)
	{
		return csCoinDao.saveCsCoin(csCoin);
	}
	
	/**
	 * 更新红包
	 * @param csCoin
	 */
	public void updateCsCoin(CsCoin csCoin)
	{
		csCoinDao.updateCsCoin(csCoin);
	}
	/**
	 * 更新红包非空字段
	 * @param csCoin
	 */
	public void updateCsCoin$NotNull(CsCoin csCoin)
	{
		csCoinDao.updateCsCoin$NotNull(csCoin);
	}
	
	/**
	 * 根据ID删除一个红包
	 * @param id
	 */
	public void deleteCsCoinById(Long id)
	{
		csCoinDao.deleteCsCoinById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个红包
	 * @param id
	 */
	public void removeCsCoinById(Long id)
	{
		csCoinDao.removeCsCoinById(id);
	}
	
	/**
	 * 根据条件更新红包
	 * @param values
	 * @param params
	 */
	public void updateCsCoinByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsCoinBy时无条件");
		csCoinDao.updateCsCoinByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除红包
	 * @param params
	 */
	public void deleteCsCoinByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsCoinBy时无条件");
		csCoinDao.deleteCsCoinByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsCoinDao getCsCoinDao()
	{
		return csCoinDao;
	}

	public void setCsCoinDao(ICsCoinDao csCoinDao)
	{
		this.csCoinDao = csCoinDao;
	}
}