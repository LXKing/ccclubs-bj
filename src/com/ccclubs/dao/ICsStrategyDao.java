package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsStrategy;

/**
 * 品牌战略的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsStrategyDao
{
	/**
	 * 获取所有品牌战略
	 * @return
	 */
	public List<CsStrategy> getCsStrategyList(Map params,Integer size);
	
	/**
	 * 获取品牌战略统计
	 * @return
	 */
	public List getCsStrategyStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取品牌战略总数
	 * @return
	 */	
	public Long getCsStrategyCount(Map params);
	
	/**
	 * 获取品牌战略自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsStrategyEval(String eval,Map params);
	
	/**
	 * 获取品牌战略分页
	 * @return
	 */	
	public Page getCsStrategyPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取品牌战略
	 * @param params
	 * @return
	 */
	public CsStrategy getCsStrategy(Map params);	

	/**
	 * 根据ID取品牌战略
	 * @param id
	 * @return
	 */
	public CsStrategy getCsStrategyById(Long id);


	
	

	/**
	 * 保存品牌战略
	 * @param csStrategy
	 */
	public CsStrategy saveCsStrategy(CsStrategy csStrategy);

	/**
	 * 更新品牌战略
	 * @param csStrategy
	 */
	public void updateCsStrategy(CsStrategy csStrategy);
	/**
	 * 更新品牌战略非空字段
	 * @param csStrategy
	 */
	public void updateCsStrategy$NotNull(CsStrategy csStrategy);

	/**
	 * 根据ID删除一个品牌战略
	 * @param id
	 */
	public void deleteCsStrategyById(Long id);
	/**
	 * 根据ID逻辑删除一个品牌战略
	 * @param id
	 */
	public void removeCsStrategyById(Long id);
	/**
	 * 根据条件更新品牌战略
	 * @param values
	 * @param params
	 */
	public void updateCsStrategyByConfirm(Map values, Map params);
	/**
	 * 根据条件删除品牌战略
	 * @param params
	 */
	public void deleteCsStrategyByConfirm(Map params);

}