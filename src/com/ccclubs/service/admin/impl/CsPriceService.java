package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsPriceDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsPrice;
import com.ccclubs.service.admin.ICsPriceService;
import com.lazy3q.web.helper.$;

/**
 * 价格的Service实现
 * @author 飞啊飘啊
 */
public class CsPriceService implements ICsPriceService
{
	ICsPriceDao csPriceDao;
	

	/**
	 * 获取所有价格
	 * @return
	 */
	public List<CsPrice> getCsPriceList(Map params,Integer size)
	{
		return csPriceDao.getCsPriceList(params,size);
	}
	
	/**
	 * 获取价格统计
	 * @return
	 */
	public List getCsPriceStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csPriceDao.getCsPriceStats(params,groups,sums);
	}
	
	/**
	 * 获取价格总数
	 * @return
	 */
	public Long getCsPriceCount(Map params)
	{
		return csPriceDao.getCsPriceCount(params);
	}
	
	/**
	 * 获取价格自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsPriceEval(String eval,Map params)
	{
		return csPriceDao.getCsPriceEval(eval,params);
	}

	/**
	 * 获取价格分页
	 * @return
	 */
	public Page getCsPricePage(int page,int size,Map params)
	{
		return csPriceDao.getCsPricePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取价格
	 * @param params
	 * @return
	 */
	public CsPrice getCsPrice(Map params)
	{
		return csPriceDao.getCsPrice(params);
	}

	/**
	 * 根据ID取价格
	 * @param id
	 * @return
	 */
	public CsPrice getCsPriceById(Long id)
	{
		return csPriceDao.getCsPriceById(id);
	}

	/**
	 * 保存价格
	 * @param csPrice
	 */
	public CsPrice saveCsPrice(CsPrice csPrice)
	{
		return csPriceDao.saveCsPrice(csPrice);
	}
	
	/**
	 * 更新价格
	 * @param csPrice
	 */
	public void updateCsPrice(CsPrice csPrice)
	{
		csPriceDao.updateCsPrice(csPrice);
	}
	/**
	 * 更新价格非空字段
	 * @param csPrice
	 */
	public void updateCsPrice$NotNull(CsPrice csPrice)
	{
		csPriceDao.updateCsPrice$NotNull(csPrice);
	}
	
	/**
	 * 根据ID删除一个价格
	 * @param id
	 */
	public void deleteCsPriceById(Long id)
	{
		csPriceDao.deleteCsPriceById(id);
	}
	
	/**
	 * 根据条件更新价格
	 * @param values
	 * @param params
	 */
	public void updateCsPriceByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsPriceBy时无条件");
		csPriceDao.updateCsPriceByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除价格
	 * @param params
	 */
	public void deleteCsPriceByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsPriceBy时无条件");
		csPriceDao.deleteCsPriceByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsPriceDao getCsPriceDao()
	{
		return csPriceDao;
	}

	public void setCsPriceDao(ICsPriceDao csPriceDao)
	{
		this.csPriceDao = csPriceDao;
	}
}