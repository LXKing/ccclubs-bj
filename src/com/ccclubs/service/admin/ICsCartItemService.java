package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsCartItem;

/**
 * 购物车项的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsCartItemService
{
	/**
	 * 获取所有购物车项
	 * @return
	 */
	public List<CsCartItem> getCsCartItemList(Map params,Integer size);
	
	/**
	 * 获取购物车项统计
	 * @return
	 */
	public List getCsCartItemStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取购物车项总数
	 * @return
	 */	
	public Long getCsCartItemCount(Map params);
	
	
	/**
	 * 获取购物车项自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCartItemEval(String eval,Map params);

	/**
	 * 获取购物车项分页
	 * @return
	 */	
	public Page getCsCartItemPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取购物车项
	 * @param params
	 * @return
	 */
	public CsCartItem getCsCartItem(Map params);

	/**
	 * 根据ID取购物车项
	 * @param id
	 * @return
	 */
	public CsCartItem getCsCartItemById(Long id);


	/**
	 * 保存购物车项
	 * @param csCartItem
	 */
	public CsCartItem saveCsCartItem(CsCartItem csCartItem);

	/**
	 * 更新购物车项
	 * @param csCartItem
	 */
	public void updateCsCartItem(CsCartItem csCartItem);
	/**
	 * 更新购物车项非空字段
	 * @param csCartItem
	 */
	public void updateCsCartItem$NotNull(CsCartItem csCartItem);

	/**
	 * 根据ID删除一个购物车项
	 * @param id
	 */
	public void deleteCsCartItemById(Long id);
	/**
	 * 根据条件更新购物车项
	 * @param values
	 * @param params
	 */
	public void updateCsCartItemByConfirm(Map values, Map params);
	/**
	 * 根据条件删除购物车项
	 * @param params
	 */
	public void deleteCsCartItemByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}