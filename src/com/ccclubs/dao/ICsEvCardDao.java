package com.ccclubs.dao;

import java.util.List;
import java.util.Map;
import com.ccclubs.model.CsEvCard;
import com.lazy3q.web.util.Page;

/**
 * 会员卡的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsEvCardDao
{
	/**
	 * 获取所有会员卡
	 * @return
	 */
	public List<CsEvCard> getCsEvCardList(Map params,Integer size);
	
	/**
	 * 获取会员卡统计
	 * @return
	 */
	public List getCsEvCardStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取会员卡总数
	 * @return
	 */	
	public Long getCsEvCardCount(Map params);
	
	/**
     * 获取会员卡总数
     * @return
     */
	public Long getCount(Map params);
	
	/**
	 * 获取会员卡自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsEvCardEval(String eval,Map params);
	
	/**
	 * 获取会员卡分页
	 * @return
	 */	
	public Page getCsEvCardPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取会员卡
	 * @param params
	 * @return
	 */
	public CsEvCard getCsEvCard(Map params);	

	/**
	 * 根据ID取会员卡
	 * @param id
	 * @return
	 */
	public CsEvCard getCsEvCardById(Long id);


	
	

	/**
	 * 保存会员卡
	 * @param csEvCard
	 */
	public CsEvCard saveCsEvCard(CsEvCard csEvCard);

	/**
	 * 更新会员卡
	 * @param csEvCard
	 */
	public void updateCsEvCard(CsEvCard csEvCard);
	/**
	 * 更新会员卡非空字段
	 * @param csEvCard
	 */
	public void updateCsEvCard$NotNull(CsEvCard csEvCard);

	/**
	 * 根据ID删除一个会员卡
	 * @param id
	 */
	public void deleteCsEvCardById(Long id);
	/**
	 * 根据ID逻辑删除一个会员卡
	 * @param id
	 */
	public void removeCsEvCardById(Long id);
	/**
	 * 根据条件更新会员卡
	 * @param values
	 * @param params
	 */
	public void updateCsEvCardByConfirm(Map values, Map params);
	/**
	 * 根据条件删除会员卡
	 * @param params
	 */
	public void deleteCsEvCardByConfirm(Map params);

}