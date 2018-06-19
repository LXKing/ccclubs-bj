package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsLuckyItem;

/**
 * 抽奖奖项的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsLuckyItemService
{
	/**
	 * 获取所有抽奖奖项
	 * @return
	 */
	public List<CsLuckyItem> getCsLuckyItemList(Map params,Integer size);
	
	/**
	 * 获取抽奖奖项统计
	 * @return
	 */
	public List getCsLuckyItemStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取抽奖奖项总数
	 * @return
	 */	
	public Long getCsLuckyItemCount(Map params);
	
	
	/**
	 * 获取抽奖奖项自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsLuckyItemEval(String eval,Map params);

	/**
	 * 获取抽奖奖项分页
	 * @return
	 */	
	public Page getCsLuckyItemPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取抽奖奖项
	 * @param params
	 * @return
	 */
	public CsLuckyItem getCsLuckyItem(Map params);

	/**
	 * 根据ID取抽奖奖项
	 * @param id
	 * @return
	 */
	public CsLuckyItem getCsLuckyItemById(Long id);


	/**
	 * 保存抽奖奖项
	 * @param csLuckyItem
	 */
	public CsLuckyItem saveCsLuckyItem(CsLuckyItem csLuckyItem);

	/**
	 * 更新抽奖奖项
	 * @param csLuckyItem
	 */
	public void updateCsLuckyItem(CsLuckyItem csLuckyItem);
	/**
	 * 更新抽奖奖项非空字段
	 * @param csLuckyItem
	 */
	public void updateCsLuckyItem$NotNull(CsLuckyItem csLuckyItem);

	/**
	 * 根据ID删除一个抽奖奖项
	 * @param id
	 */
	public void deleteCsLuckyItemById(Long id);
	/**
	 * 根据ID逻辑删除一个抽奖奖项
	 * @param id
	 */
	public void removeCsLuckyItemById(Long id);
	/**
	 * 根据条件更新抽奖奖项
	 * @param values
	 * @param params
	 */
	public void updateCsLuckyItemByConfirm(Map values, Map params);
	/**
	 * 根据条件删除抽奖奖项
	 * @param params
	 */
	public void deleteCsLuckyItemByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}