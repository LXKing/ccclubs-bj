package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsGift;

/**
 * 优惠内容的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsGiftService
{
	/**
	 * 获取所有优惠内容
	 * @return
	 */
	public List<CsGift> getCsGiftList(Map params,Integer size);
	
	/**
	 * 获取优惠内容统计
	 * @return
	 */
	public List getCsGiftStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取优惠内容总数
	 * @return
	 */	
	public Long getCsGiftCount(Map params);
	
	
	/**
	 * 获取优惠内容自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsGiftEval(String eval,Map params);

	/**
	 * 获取优惠内容分页
	 * @return
	 */	
	public Page getCsGiftPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取优惠内容
	 * @param params
	 * @return
	 */
	public CsGift getCsGift(Map params);

	/**
	 * 根据ID取优惠内容
	 * @param id
	 * @return
	 */
	public CsGift getCsGiftById(Long id);


	/**
	 * 保存优惠内容
	 * @param csGift
	 */
	public CsGift saveCsGift(CsGift csGift);

	/**
	 * 更新优惠内容
	 * @param csGift
	 */
	public void updateCsGift(CsGift csGift);
	/**
	 * 更新优惠内容非空字段
	 * @param csGift
	 */
	public void updateCsGift$NotNull(CsGift csGift);

	/**
	 * 根据ID删除一个优惠内容
	 * @param id
	 */
	public void deleteCsGiftById(Long id);
	/**
	 * 根据ID逻辑删除一个优惠内容
	 * @param id
	 */
	public void removeCsGiftById(Long id);
	/**
	 * 根据条件更新优惠内容
	 * @param values
	 * @param params
	 */
	public void updateCsGiftByConfirm(Map values, Map params);
	/**
	 * 根据条件删除优惠内容
	 * @param params
	 */
	public void deleteCsGiftByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}