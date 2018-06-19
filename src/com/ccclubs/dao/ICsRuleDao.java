package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRule;

/**
 * 规则的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsRuleDao
{
	/**
	 * 获取所有规则
	 * @return
	 */
	public List<CsRule> getCsRuleList(Map params,Integer size);
	
	/**
	 * 获取规则统计
	 * @return
	 */
	public List getCsRuleStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取规则总数
	 * @return
	 */	
	public Long getCsRuleCount(Map params);
	
	/**
	 * 获取规则自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRuleEval(String eval,Map params);
	
	/**
	 * 获取规则分页
	 * @return
	 */	
	public Page getCsRulePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取规则
	 * @param params
	 * @return
	 */
	public CsRule getCsRule(Map params);	

	/**
	 * 根据ID取规则
	 * @param id
	 * @return
	 */
	public CsRule getCsRuleById(Long id);


	
	

	/**
	 * 保存规则
	 * @param csRule
	 */
	public CsRule saveCsRule(CsRule csRule);

	/**
	 * 更新规则
	 * @param csRule
	 */
	public void updateCsRule(CsRule csRule);
	/**
	 * 更新规则非空字段
	 * @param csRule
	 */
	public void updateCsRule$NotNull(CsRule csRule);

	/**
	 * 根据ID删除一个规则
	 * @param id
	 */
	public void deleteCsRuleById(Long id);
	/**
	 * 根据ID逻辑删除一个规则
	 * @param id
	 */
	public void removeCsRuleById(Long id);
	/**
	 * 根据条件更新规则
	 * @param values
	 * @param params
	 */
	public void updateCsRuleByConfirm(Map values, Map params);
	/**
	 * 根据条件删除规则
	 * @param params
	 */
	public void deleteCsRuleByConfirm(Map params);

}