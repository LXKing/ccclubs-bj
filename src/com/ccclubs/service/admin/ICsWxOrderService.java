package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsWxOrder;

/**
 * 微信指令的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsWxOrderService
{
	/**
	 * 获取所有微信指令
	 * @return
	 */
	public List<CsWxOrder> getCsWxOrderList(Map params,Integer size);
	
	/**
	 * 获取微信指令统计
	 * @return
	 */
	public List getCsWxOrderStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取微信指令总数
	 * @return
	 */	
	public Long getCsWxOrderCount(Map params);
	
	
	/**
	 * 获取微信指令自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsWxOrderEval(String eval,Map params);

	/**
	 * 获取微信指令分页
	 * @return
	 */	
	public Page getCsWxOrderPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取微信指令
	 * @param params
	 * @return
	 */
	public CsWxOrder getCsWxOrder(Map params);

	/**
	 * 根据ID取微信指令
	 * @param id
	 * @return
	 */
	public CsWxOrder getCsWxOrderById(Long id);


	/**
	 * 保存微信指令
	 * @param csWxOrder
	 */
	public CsWxOrder saveCsWxOrder(CsWxOrder csWxOrder);

	/**
	 * 更新微信指令
	 * @param csWxOrder
	 */
	public void updateCsWxOrder(CsWxOrder csWxOrder);
	/**
	 * 更新微信指令非空字段
	 * @param csWxOrder
	 */
	public void updateCsWxOrder$NotNull(CsWxOrder csWxOrder);

	/**
	 * 根据ID删除一个微信指令
	 * @param id
	 */
	public void deleteCsWxOrderById(Long id);
	/**
	 * 根据ID逻辑删除一个微信指令
	 * @param id
	 */
	public void removeCsWxOrderById(Long id);
	/**
	 * 根据条件更新微信指令
	 * @param values
	 * @param params
	 */
	public void updateCsWxOrderByConfirm(Map values, Map params);
	/**
	 * 根据条件删除微信指令
	 * @param params
	 */
	public void deleteCsWxOrderByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}