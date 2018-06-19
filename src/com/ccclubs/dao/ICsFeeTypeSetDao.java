package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsFeeTypeSet;

/**
 * 计费策略的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsFeeTypeSetDao
{
	/**
	 * 获取所有计费策略
	 * @return
	 */
	public List<CsFeeTypeSet> getCsFeeTypeSetList(Map params,Integer size);
	
	/**
	 * 获取计费策略统计
	 * @return
	 */
	public List getCsFeeTypeSetStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取计费策略总数
	 * @return
	 */	
	public Long getCsFeeTypeSetCount(Map params);
	
	/**
	 * 获取计费策略自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsFeeTypeSetEval(String eval,Map params);
	
	/**
	 * 获取计费策略分页
	 * @return
	 */	
	public Page getCsFeeTypeSetPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取计费策略
	 * @param params
	 * @return
	 */
	public CsFeeTypeSet getCsFeeTypeSet(Map params);	

	/**
	 * 根据ID取计费策略
	 * @param id
	 * @return
	 */
	public CsFeeTypeSet getCsFeeTypeSetById(Long id);


	
	

	/**
	 * 保存计费策略
	 * @param csFeeTypeSet
	 */
	public CsFeeTypeSet saveCsFeeTypeSet(CsFeeTypeSet csFeeTypeSet);

	/**
	 * 更新计费策略
	 * @param csFeeTypeSet
	 */
	public void updateCsFeeTypeSet(CsFeeTypeSet csFeeTypeSet);
	/**
	 * 更新计费策略非空字段
	 * @param csFeeTypeSet
	 */
	public void updateCsFeeTypeSet$NotNull(CsFeeTypeSet csFeeTypeSet);

	/**
	 * 根据ID删除一个计费策略
	 * @param id
	 */
	public void deleteCsFeeTypeSetById(Long id);
	/**
	 * 根据ID逻辑删除一个计费策略
	 * @param id
	 */
	public void removeCsFeeTypeSetById(Long id);
	/**
	 * 根据条件更新计费策略
	 * @param values
	 * @param params
	 */
	public void updateCsFeeTypeSetByConfirm(Map values, Map params);
	/**
	 * 根据条件删除计费策略
	 * @param params
	 */
	public void deleteCsFeeTypeSetByConfirm(Map params);

}