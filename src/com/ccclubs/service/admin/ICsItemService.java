package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsItem;

/**
 * 商品信息的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsItemService
{
	/**
	 * 获取所有商品信息
	 * @return
	 */
	public List<CsItem> getCsItemList(Map params,Integer size);
	
	/**
	 * 获取商品信息统计
	 * @return
	 */
	public List getCsItemStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取商品信息总数
	 * @return
	 */	
	public Long getCsItemCount(Map params);
	
	
	/**
	 * 获取商品信息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsItemEval(String eval,Map params);

	/**
	 * 获取商品信息分页
	 * @return
	 */	
	public Page getCsItemPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取商品信息
	 * @param params
	 * @return
	 */
	public CsItem getCsItem(Map params);

	/**
	 * 根据ID取商品信息
	 * @param id
	 * @return
	 */
	public CsItem getCsItemById(Long id);


	/**
	 * 保存商品信息
	 * @param csItem
	 */
	public CsItem saveCsItem(CsItem csItem);

	/**
	 * 更新商品信息
	 * @param csItem
	 */
	public void updateCsItem(CsItem csItem);
	/**
	 * 更新商品信息非空字段
	 * @param csItem
	 */
	public void updateCsItem$NotNull(CsItem csItem);

	/**
	 * 根据ID删除一个商品信息
	 * @param id
	 */
	public void deleteCsItemById(Long id);
	/**
	 * 根据ID逻辑删除一个商品信息
	 * @param id
	 */
	public void removeCsItemById(Long id);
	/**
	 * 根据条件更新商品信息
	 * @param values
	 * @param params
	 */
	public void updateCsItemByConfirm(Map values, Map params);
	/**
	 * 根据条件删除商品信息
	 * @param params
	 */
	public void deleteCsItemByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}