package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsIntegralRuleDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsIntegralRule;
import com.ccclubs.service.admin.ICsIntegralRuleService;
import com.lazy3q.web.helper.$;

/**
 * 积分规则的Service实现
 * @author 飞啊飘啊
 */
public class CsIntegralRuleService implements ICsIntegralRuleService
{
	ICsIntegralRuleDao csIntegralRuleDao;
	

	/**
	 * 获取所有积分规则
	 * @return
	 */
	public List<CsIntegralRule> getCsIntegralRuleList(Map params,Integer size)
	{
		return csIntegralRuleDao.getCsIntegralRuleList(params,size);
	}
	
	/**
	 * 获取积分规则统计
	 * @return
	 */
	public List getCsIntegralRuleStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csIntegralRuleDao.getCsIntegralRuleStats(params,groups,sums);
	}
	
	/**
	 * 获取积分规则总数
	 * @return
	 */
	public Long getCsIntegralRuleCount(Map params)
	{
		return csIntegralRuleDao.getCsIntegralRuleCount(params);
	}
	
	/**
	 * 获取积分规则自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsIntegralRuleEval(String eval,Map params)
	{
		return csIntegralRuleDao.getCsIntegralRuleEval(eval,params);
	}

	/**
	 * 获取积分规则分页
	 * @return
	 */
	public Page getCsIntegralRulePage(int page,int size,Map params)
	{
		return csIntegralRuleDao.getCsIntegralRulePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取积分规则
	 * @param params
	 * @return
	 */
	public CsIntegralRule getCsIntegralRule(Map params)
	{
		return csIntegralRuleDao.getCsIntegralRule(params);
	}

	/**
	 * 根据ID取积分规则
	 * @param id
	 * @return
	 */
	public CsIntegralRule getCsIntegralRuleById(Long id)
	{
		return csIntegralRuleDao.getCsIntegralRuleById(id);
	}

	/**
	 * 保存积分规则
	 * @param csIntegralRule
	 */
	public CsIntegralRule saveCsIntegralRule(CsIntegralRule csIntegralRule)
	{
		return csIntegralRuleDao.saveCsIntegralRule(csIntegralRule);
	}
	
	/**
	 * 更新积分规则
	 * @param csIntegralRule
	 */
	public void updateCsIntegralRule(CsIntegralRule csIntegralRule)
	{
		csIntegralRuleDao.updateCsIntegralRule(csIntegralRule);
	}
	/**
	 * 更新积分规则非空字段
	 * @param csIntegralRule
	 */
	public void updateCsIntegralRule$NotNull(CsIntegralRule csIntegralRule)
	{
		csIntegralRuleDao.updateCsIntegralRule$NotNull(csIntegralRule);
	}
	
	/**
	 * 根据ID删除一个积分规则
	 * @param id
	 */
	public void deleteCsIntegralRuleById(Long id)
	{
		csIntegralRuleDao.deleteCsIntegralRuleById(id);
	}
	/**
	 * 根据ID逻辑删除一个积分规则
	 * @param id
	 */
	public void removeCsIntegralRuleById(Long id)
	{
		csIntegralRuleDao.removeCsIntegralRuleById(id);
	}
	
	/**
	 * 根据条件更新积分规则
	 * @param values
	 * @param params
	 */
	public void updateCsIntegralRuleByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsIntegralRuleBy时无条件");
		csIntegralRuleDao.updateCsIntegralRuleByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除积分规则
	 * @param params
	 */
	public void deleteCsIntegralRuleByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsIntegralRuleBy时无条件");
		csIntegralRuleDao.deleteCsIntegralRuleByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsIntegralRuleDao getCsIntegralRuleDao()
	{
		return csIntegralRuleDao;
	}

	public void setCsIntegralRuleDao(ICsIntegralRuleDao csIntegralRuleDao)
	{
		this.csIntegralRuleDao = csIntegralRuleDao;
	}
}