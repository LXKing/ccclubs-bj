package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ITbSaleQuoteDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.TbSaleQuote;
import com.ccclubs.service.admin.ITbSaleQuoteService;
import com.lazy3q.web.helper.$;

/**
 * 跟单报价的Service实现
 * @author 飞啊飘啊
 */
public class TbSaleQuoteService implements ITbSaleQuoteService
{
	ITbSaleQuoteDao tbSaleQuoteDao;
	

	/**
	 * 获取所有跟单报价
	 * @return
	 */
	public List<TbSaleQuote> getTbSaleQuoteList(Map params,Integer size)
	{
		return tbSaleQuoteDao.getTbSaleQuoteList(params,size);
	}
	
	/**
	 * 获取跟单报价统计
	 * @return
	 */
	public List getTbSaleQuoteStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return tbSaleQuoteDao.getTbSaleQuoteStats(params,groups,sums);
	}
	
	/**
	 * 获取跟单报价总数
	 * @return
	 */
	public Long getTbSaleQuoteCount(Map params)
	{
		return tbSaleQuoteDao.getTbSaleQuoteCount(params);
	}
	
	/**
	 * 获取跟单报价自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbSaleQuoteEval(String eval,Map params)
	{
		return tbSaleQuoteDao.getTbSaleQuoteEval(eval,params);
	}

	/**
	 * 获取跟单报价分页
	 * @return
	 */
	public Page getTbSaleQuotePage(int page,int size,Map params)
	{
		return tbSaleQuoteDao.getTbSaleQuotePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取跟单报价
	 * @param params
	 * @return
	 */
	public TbSaleQuote getTbSaleQuote(Map params)
	{
		return tbSaleQuoteDao.getTbSaleQuote(params);
	}

	/**
	 * 根据ID取跟单报价
	 * @param id
	 * @return
	 */
	public TbSaleQuote getTbSaleQuoteById(Long id)
	{
		return tbSaleQuoteDao.getTbSaleQuoteById(id);
	}

	/**
	 * 保存跟单报价
	 * @param tbSaleQuote
	 */
	public TbSaleQuote saveTbSaleQuote(TbSaleQuote tbSaleQuote)
	{
		return tbSaleQuoteDao.saveTbSaleQuote(tbSaleQuote);
	}
	
	/**
	 * 更新跟单报价
	 * @param tbSaleQuote
	 */
	public void updateTbSaleQuote(TbSaleQuote tbSaleQuote)
	{
		tbSaleQuoteDao.updateTbSaleQuote(tbSaleQuote);
	}
	/**
	 * 更新跟单报价非空字段
	 * @param tbSaleQuote
	 */
	public void updateTbSaleQuote$NotNull(TbSaleQuote tbSaleQuote)
	{
		tbSaleQuoteDao.updateTbSaleQuote$NotNull(tbSaleQuote);
	}
	
	/**
	 * 根据ID删除一个跟单报价
	 * @param id
	 */
	public void deleteTbSaleQuoteById(Long id)
	{
		tbSaleQuoteDao.deleteTbSaleQuoteById(id);
	}
	/**
	 * 根据ID逻辑删除一个跟单报价
	 * @param id
	 */
	public void removeTbSaleQuoteById(Long id)
	{
		tbSaleQuoteDao.removeTbSaleQuoteById(id);
	}
	
	/**
	 * 根据条件更新跟单报价
	 * @param values
	 * @param params
	 */
	public void updateTbSaleQuoteByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateTbSaleQuoteBy时无条件");
		tbSaleQuoteDao.updateTbSaleQuoteByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除跟单报价
	 * @param params
	 */
	public void deleteTbSaleQuoteByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteTbSaleQuoteBy时无条件");
		tbSaleQuoteDao.deleteTbSaleQuoteByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ITbSaleQuoteDao getTbSaleQuoteDao()
	{
		return tbSaleQuoteDao;
	}

	public void setTbSaleQuoteDao(ITbSaleQuoteDao tbSaleQuoteDao)
	{
		this.tbSaleQuoteDao = tbSaleQuoteDao;
	}
}