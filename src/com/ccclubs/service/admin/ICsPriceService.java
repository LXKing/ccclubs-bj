package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsPrice;

/**
 * 价格的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsPriceService
{
	/**
	 * 获取所有价格
	 * @return
	 */
	public List<CsPrice> getCsPriceList(Map params,Integer size);
	
	/**
	 * 获取价格统计
	 * @return
	 */
	public List getCsPriceStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取价格总数
	 * @return
	 */	
	public Long getCsPriceCount(Map params);
	
	
	/**
	 * 获取价格自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsPriceEval(String eval,Map params);

	/**
	 * 获取价格分页
	 * @return
	 */	
	public Page getCsPricePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取价格
	 * @param params
	 * @return
	 */
	public CsPrice getCsPrice(Map params);

	/**
	 * 根据ID取价格
	 * @param id
	 * @return
	 */
	public CsPrice getCsPriceById(Long id);


	/**
	 * 保存价格
	 * @param csPrice
	 */
	public CsPrice saveCsPrice(CsPrice csPrice);

	/**
	 * 更新价格
	 * @param csPrice
	 */
	public void updateCsPrice(CsPrice csPrice);
	/**
	 * 更新价格非空字段
	 * @param csPrice
	 */
	public void updateCsPrice$NotNull(CsPrice csPrice);

	/**
	 * 根据ID删除一个价格
	 * @param id
	 */
	public void deleteCsPriceById(Long id);
	/**
	 * 根据条件更新价格
	 * @param values
	 * @param params
	 */
	public void updateCsPriceByConfirm(Map values, Map params);
	/**
	 * 根据条件删除价格
	 * @param params
	 */
	public void deleteCsPriceByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}