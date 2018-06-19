package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMarketIndex;

/**
 * 营销指标的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsMarketIndexDao
{
	/**
	 * 获取所有营销指标
	 * @return
	 */
	public List<CsMarketIndex> getCsMarketIndexList(Map params,Integer size);
	
	/**
	 * 获取营销指标统计
	 * @return
	 */
	public List getCsMarketIndexStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取营销指标总数
	 * @return
	 */	
	public Long getCsMarketIndexCount(Map params);
	
	/**
	 * 获取营销指标自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMarketIndexEval(String eval,Map params);
	
	/**
	 * 获取营销指标分页
	 * @return
	 */	
	public Page getCsMarketIndexPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取营销指标
	 * @param params
	 * @return
	 */
	public CsMarketIndex getCsMarketIndex(Map params);	

	/**
	 * 根据ID取营销指标
	 * @param id
	 * @return
	 */
	public CsMarketIndex getCsMarketIndexById(Long id);


	
	

	/**
	 * 保存营销指标
	 * @param csMarketIndex
	 */
	public CsMarketIndex saveCsMarketIndex(CsMarketIndex csMarketIndex);

	/**
	 * 更新营销指标
	 * @param csMarketIndex
	 */
	public void updateCsMarketIndex(CsMarketIndex csMarketIndex);
	/**
	 * 更新营销指标非空字段
	 * @param csMarketIndex
	 */
	public void updateCsMarketIndex$NotNull(CsMarketIndex csMarketIndex);

	/**
	 * 根据ID删除一个营销指标
	 * @param id
	 */
	public void deleteCsMarketIndexById(Long id);
	/**
	 * 根据ID逻辑删除一个营销指标
	 * @param id
	 */
	public void removeCsMarketIndexById(Long id);
	/**
	 * 根据条件更新营销指标
	 * @param values
	 * @param params
	 */
	public void updateCsMarketIndexByConfirm(Map values, Map params);
	/**
	 * 根据条件删除营销指标
	 * @param params
	 */
	public void deleteCsMarketIndexByConfirm(Map params);

}