package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsRuleDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRule;
import com.ccclubs.service.admin.ICsRuleService;
import com.lazy3q.web.helper.$;

/**
 * 规则的Service实现
 * @author 飞啊飘啊
 */
public class CsRuleService implements ICsRuleService
{
	ICsRuleDao csRuleDao;
	

	/**
	 * 获取所有规则
	 * @return
	 */
	public List<CsRule> getCsRuleList(Map params,Integer size)
	{
		return csRuleDao.getCsRuleList(params,size);
	}
	
	/**
	 * 获取规则统计
	 * @return
	 */
	public List getCsRuleStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csRuleDao.getCsRuleStats(params,groups,sums);
	}
	
	/**
	 * 获取规则总数
	 * @return
	 */
	public Long getCsRuleCount(Map params)
	{
		return csRuleDao.getCsRuleCount(params);
	}
	
	/**
	 * 获取规则自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRuleEval(String eval,Map params)
	{
		return csRuleDao.getCsRuleEval(eval,params);
	}

	/**
	 * 获取规则分页
	 * @return
	 */
	public Page getCsRulePage(int page,int size,Map params)
	{
		return csRuleDao.getCsRulePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取规则
	 * @param params
	 * @return
	 */
	public CsRule getCsRule(Map params)
	{
		return csRuleDao.getCsRule(params);
	}

	/**
	 * 根据ID取规则
	 * @param id
	 * @return
	 */
	public CsRule getCsRuleById(Long id)
	{
		return csRuleDao.getCsRuleById(id);
	}

	/**
	 * 保存规则
	 * @param csRule
	 */
	public CsRule saveCsRule(CsRule csRule)
	{
		return csRuleDao.saveCsRule(csRule);
	}
	
	/**
	 * 更新规则
	 * @param csRule
	 */
	public void updateCsRule(CsRule csRule)
	{
		csRuleDao.updateCsRule(csRule);
	}
	/**
	 * 更新规则非空字段
	 * @param csRule
	 */
	public void updateCsRule$NotNull(CsRule csRule)
	{
		csRuleDao.updateCsRule$NotNull(csRule);
	}
	
	/**
	 * 根据ID删除一个规则
	 * @param id
	 */
	public void deleteCsRuleById(Long id)
	{
		csRuleDao.deleteCsRuleById(id);
	}
	/**
	 * 根据ID逻辑删除一个规则
	 * @param id
	 */
	public void removeCsRuleById(Long id)
	{
		csRuleDao.removeCsRuleById(id);
	}
	
	/**
	 * 根据条件更新规则
	 * @param values
	 * @param params
	 */
	public void updateCsRuleByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsRuleBy时无条件");
		csRuleDao.updateCsRuleByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除规则
	 * @param params
	 */
	public void deleteCsRuleByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsRuleBy时无条件");
		csRuleDao.deleteCsRuleByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsRuleDao getCsRuleDao()
	{
		return csRuleDao;
	}

	public void setCsRuleDao(ICsRuleDao csRuleDao)
	{
		this.csRuleDao = csRuleDao;
	}
}