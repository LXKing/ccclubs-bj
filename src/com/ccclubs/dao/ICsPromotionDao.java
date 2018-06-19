package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsPromotion;

/**
 * 促销配置的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsPromotionDao
{
	/**
	 * 获取所有促销配置
	 * @return
	 */
	public List<CsPromotion> getCsPromotionList(Map params,Integer size);
	
	/**
	 * 获取促销配置统计
	 * @return
	 */
	public List getCsPromotionStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取促销配置总数
	 * @return
	 */	
	public Long getCsPromotionCount(Map params);
	
	/**
	 * 获取促销配置自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsPromotionEval(String eval,Map params);
	
	/**
	 * 获取促销配置分页
	 * @return
	 */	
	public Page getCsPromotionPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取促销配置
	 * @param params
	 * @return
	 */
	public CsPromotion getCsPromotion(Map params);	

	/**
	 * 根据ID取促销配置
	 * @param id
	 * @return
	 */
	public CsPromotion getCsPromotionById(Long id);


	
	

	/**
	 * 保存促销配置
	 * @param csPromotion
	 */
	public CsPromotion saveCsPromotion(CsPromotion csPromotion);

	/**
	 * 更新促销配置
	 * @param csPromotion
	 */
	public void updateCsPromotion(CsPromotion csPromotion);
	/**
	 * 更新促销配置非空字段
	 * @param csPromotion
	 */
	public void updateCsPromotion$NotNull(CsPromotion csPromotion);

	/**
	 * 根据ID删除一个促销配置
	 * @param id
	 */
	public void deleteCsPromotionById(Long id);
	/**
	 * 根据ID逻辑删除一个促销配置
	 * @param id
	 */
	public void removeCsPromotionById(Long id);
	/**
	 * 根据条件更新促销配置
	 * @param values
	 * @param params
	 */
	public void updateCsPromotionByConfirm(Map values, Map params);
	/**
	 * 根据条件删除促销配置
	 * @param params
	 */
	public void deleteCsPromotionByConfirm(Map params);

}