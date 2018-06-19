package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsIntegralRule;

/**
 * 积分规则的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsIntegralRuleService
{
	/**
	 * 获取所有积分规则
	 * @return
	 */
	public List<CsIntegralRule> getCsIntegralRuleList(Map params,Integer size);
	
	/**
	 * 获取积分规则统计
	 * @return
	 */
	public List getCsIntegralRuleStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取积分规则总数
	 * @return
	 */	
	public Long getCsIntegralRuleCount(Map params);
	
	
	/**
	 * 获取积分规则自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsIntegralRuleEval(String eval,Map params);

	/**
	 * 获取积分规则分页
	 * @return
	 */	
	public Page getCsIntegralRulePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取积分规则
	 * @param params
	 * @return
	 */
	public CsIntegralRule getCsIntegralRule(Map params);

	/**
	 * 根据ID取积分规则
	 * @param id
	 * @return
	 */
	public CsIntegralRule getCsIntegralRuleById(Long id);


	/**
	 * 保存积分规则
	 * @param csIntegralRule
	 */
	public CsIntegralRule saveCsIntegralRule(CsIntegralRule csIntegralRule);

	/**
	 * 更新积分规则
	 * @param csIntegralRule
	 */
	public void updateCsIntegralRule(CsIntegralRule csIntegralRule);
	/**
	 * 更新积分规则非空字段
	 * @param csIntegralRule
	 */
	public void updateCsIntegralRule$NotNull(CsIntegralRule csIntegralRule);

	/**
	 * 根据ID删除一个积分规则
	 * @param id
	 */
	public void deleteCsIntegralRuleById(Long id);
	/**
	 * 根据ID逻辑删除一个积分规则
	 * @param id
	 */
	public void removeCsIntegralRuleById(Long id);
	/**
	 * 根据条件更新积分规则
	 * @param values
	 * @param params
	 */
	public void updateCsIntegralRuleByConfirm(Map values, Map params);
	/**
	 * 根据条件删除积分规则
	 * @param params
	 */
	public void deleteCsIntegralRuleByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}