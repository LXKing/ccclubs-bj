package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRechargeCard;

/**
 * 充值卡的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsRechargeCardDao
{
	/**
	 * 获取所有充值卡
	 * @return
	 */
	public List<CsRechargeCard> getCsRechargeCardList(Map params,Integer size);
	
	/**
	 * 获取充值卡统计
	 * @return
	 */
	public List getCsRechargeCardStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取充值卡总数
	 * @return
	 */	
	public Long getCsRechargeCardCount(Map params);
	
	/**
	 * 获取充值卡自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRechargeCardEval(String eval,Map params);
	
	/**
	 * 获取充值卡分页
	 * @return
	 */	
	public Page getCsRechargeCardPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取充值卡
	 * @param params
	 * @return
	 */
	public CsRechargeCard getCsRechargeCard(Map params);	

	/**
	 * 根据ID取充值卡
	 * @param id
	 * @return
	 */
	public CsRechargeCard getCsRechargeCardById(Long id);


	
	

	/**
	 * 保存充值卡
	 * @param csRechargeCard
	 */
	public CsRechargeCard saveCsRechargeCard(CsRechargeCard csRechargeCard);

	/**
	 * 更新充值卡
	 * @param csRechargeCard
	 */
	public void updateCsRechargeCard(CsRechargeCard csRechargeCard);
	/**
	 * 更新充值卡非空字段
	 * @param csRechargeCard
	 */
	public void updateCsRechargeCard$NotNull(CsRechargeCard csRechargeCard);

	/**
	 * 根据ID删除一个充值卡
	 * @param id
	 */
	public void deleteCsRechargeCardById(Long id);
	/**
	 * 根据ID逻辑删除一个充值卡
	 * @param id
	 */
	public void removeCsRechargeCardById(Long id);
	/**
	 * 根据条件更新充值卡
	 * @param values
	 * @param params
	 */
	public void updateCsRechargeCardByConfirm(Map values, Map params);
	/**
	 * 根据条件删除充值卡
	 * @param params
	 */
	public void deleteCsRechargeCardByConfirm(Map params);

}