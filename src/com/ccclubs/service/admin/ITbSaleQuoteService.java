package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.TbSaleQuote;

/**
 * 跟单报价的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ITbSaleQuoteService
{
	/**
	 * 获取所有跟单报价
	 * @return
	 */
	public List<TbSaleQuote> getTbSaleQuoteList(Map params,Integer size);
	
	/**
	 * 获取跟单报价统计
	 * @return
	 */
	public List getTbSaleQuoteStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取跟单报价总数
	 * @return
	 */	
	public Long getTbSaleQuoteCount(Map params);
	
	
	/**
	 * 获取跟单报价自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbSaleQuoteEval(String eval,Map params);

	/**
	 * 获取跟单报价分页
	 * @return
	 */	
	public Page getTbSaleQuotePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取跟单报价
	 * @param params
	 * @return
	 */
	public TbSaleQuote getTbSaleQuote(Map params);

	/**
	 * 根据ID取跟单报价
	 * @param id
	 * @return
	 */
	public TbSaleQuote getTbSaleQuoteById(Long id);


	/**
	 * 保存跟单报价
	 * @param tbSaleQuote
	 */
	public TbSaleQuote saveTbSaleQuote(TbSaleQuote tbSaleQuote);

	/**
	 * 更新跟单报价
	 * @param tbSaleQuote
	 */
	public void updateTbSaleQuote(TbSaleQuote tbSaleQuote);
	/**
	 * 更新跟单报价非空字段
	 * @param tbSaleQuote
	 */
	public void updateTbSaleQuote$NotNull(TbSaleQuote tbSaleQuote);

	/**
	 * 根据ID删除一个跟单报价
	 * @param id
	 */
	public void deleteTbSaleQuoteById(Long id);
	/**
	 * 根据ID逻辑删除一个跟单报价
	 * @param id
	 */
	public void removeTbSaleQuoteById(Long id);
	/**
	 * 根据条件更新跟单报价
	 * @param values
	 * @param params
	 */
	public void updateTbSaleQuoteByConfirm(Map values, Map params);
	/**
	 * 根据条件删除跟单报价
	 * @param params
	 */
	public void deleteTbSaleQuoteByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}