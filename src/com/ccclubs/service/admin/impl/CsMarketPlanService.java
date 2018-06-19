package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsMarketPlanDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMarketPlan;
import com.ccclubs.service.admin.ICsMarketPlanService;
import com.lazy3q.web.helper.$;

/**
 * 营销方案的Service实现
 * @author 飞啊飘啊
 */
public class CsMarketPlanService implements ICsMarketPlanService
{
	ICsMarketPlanDao csMarketPlanDao;
	

	/**
	 * 获取所有营销方案
	 * @return
	 */
	public List<CsMarketPlan> getCsMarketPlanList(Map params,Integer size)
	{
		return csMarketPlanDao.getCsMarketPlanList(params,size);
	}
	
	/**
	 * 获取营销方案统计
	 * @return
	 */
	public List getCsMarketPlanStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csMarketPlanDao.getCsMarketPlanStats(params,groups,sums);
	}
	
	/**
	 * 获取营销方案总数
	 * @return
	 */
	public Long getCsMarketPlanCount(Map params)
	{
		return csMarketPlanDao.getCsMarketPlanCount(params);
	}
	
	/**
	 * 获取营销方案自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMarketPlanEval(String eval,Map params)
	{
		return csMarketPlanDao.getCsMarketPlanEval(eval,params);
	}

	/**
	 * 获取营销方案分页
	 * @return
	 */
	public Page getCsMarketPlanPage(int page,int size,Map params)
	{
		return csMarketPlanDao.getCsMarketPlanPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取营销方案
	 * @param params
	 * @return
	 */
	public CsMarketPlan getCsMarketPlan(Map params)
	{
		return csMarketPlanDao.getCsMarketPlan(params);
	}

	/**
	 * 根据ID取营销方案
	 * @param id
	 * @return
	 */
	public CsMarketPlan getCsMarketPlanById(Long id)
	{
		return csMarketPlanDao.getCsMarketPlanById(id);
	}

	/**
	 * 保存营销方案
	 * @param csMarketPlan
	 */
	public CsMarketPlan saveCsMarketPlan(CsMarketPlan csMarketPlan)
	{
		return csMarketPlanDao.saveCsMarketPlan(csMarketPlan);
	}
	
	/**
	 * 更新营销方案
	 * @param csMarketPlan
	 */
	public void updateCsMarketPlan(CsMarketPlan csMarketPlan)
	{
		csMarketPlanDao.updateCsMarketPlan(csMarketPlan);
	}
	/**
	 * 更新营销方案非空字段
	 * @param csMarketPlan
	 */
	public void updateCsMarketPlan$NotNull(CsMarketPlan csMarketPlan)
	{
		csMarketPlanDao.updateCsMarketPlan$NotNull(csMarketPlan);
	}
	
	/**
	 * 根据ID删除一个营销方案
	 * @param id
	 */
	public void deleteCsMarketPlanById(Long id)
	{
		csMarketPlanDao.deleteCsMarketPlanById(id);
	}
	/**
	 * 根据ID逻辑删除一个营销方案
	 * @param id
	 */
	public void removeCsMarketPlanById(Long id)
	{
		csMarketPlanDao.removeCsMarketPlanById(id);
	}
	
	/**
	 * 根据条件更新营销方案
	 * @param values
	 * @param params
	 */
	public void updateCsMarketPlanByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsMarketPlanBy时无条件");
		csMarketPlanDao.updateCsMarketPlanByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除营销方案
	 * @param params
	 */
	public void deleteCsMarketPlanByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsMarketPlanBy时无条件");
		csMarketPlanDao.deleteCsMarketPlanByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsMarketPlanDao getCsMarketPlanDao()
	{
		return csMarketPlanDao;
	}

	public void setCsMarketPlanDao(ICsMarketPlanDao csMarketPlanDao)
	{
		this.csMarketPlanDao = csMarketPlanDao;
	}
}