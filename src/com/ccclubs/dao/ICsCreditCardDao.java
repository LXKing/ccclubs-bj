package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsCreditCard;

/**
 * 信用卡的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsCreditCardDao
{
	/**
	 * 获取所有信用卡
	 * @return
	 */
	public List<CsCreditCard> getCsCreditCardList(Map params,Integer size);
	
	/**
	 * 获取信用卡统计
	 * @return
	 */
	public List getCsCreditCardStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取信用卡总数
	 * @return
	 */	
	public Long getCsCreditCardCount(Map params);
	
	/**
	 * 获取信用卡自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsCreditCardEval(String eval,Map params);
	
	/**
	 * 获取信用卡分页
	 * @return
	 */	
	public Page getCsCreditCardPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取信用卡
	 * @param params
	 * @return
	 */
	public CsCreditCard getCsCreditCard(Map params);	

	/**
	 * 根据ID取信用卡
	 * @param id
	 * @return
	 */
	public CsCreditCard getCsCreditCardById(Long id);


	
	

	/**
	 * 保存信用卡
	 * @param csCreditCard
	 */
	public CsCreditCard saveCsCreditCard(CsCreditCard csCreditCard);

	/**
	 * 更新信用卡
	 * @param csCreditCard
	 */
	public void updateCsCreditCard(CsCreditCard csCreditCard);
	/**
	 * 更新信用卡非空字段
	 * @param csCreditCard
	 */
	public void updateCsCreditCard$NotNull(CsCreditCard csCreditCard);

	/**
	 * 根据ID删除一个信用卡
	 * @param id
	 */
	public void deleteCsCreditCardById(Long id);
	/**
	 * 根据ID逻辑删除一个信用卡
	 * @param id
	 */
	public void removeCsCreditCardById(Long id);
	/**
	 * 根据条件更新信用卡
	 * @param values
	 * @param params
	 */
	public void updateCsCreditCardByConfirm(Map values, Map params);
	/**
	 * 根据条件删除信用卡
	 * @param params
	 */
	public void deleteCsCreditCardByConfirm(Map params);

}