package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsMarketPlan;

/**
 * 营销方案的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsMarketPlanService
{
	/**
	 * 获取所有营销方案
	 * @return
	 */
	public List<CsMarketPlan> getCsMarketPlanList(Map params,Integer size);
	
	/**
	 * 获取营销方案统计
	 * @return
	 */
	public List getCsMarketPlanStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取营销方案总数
	 * @return
	 */	
	public Long getCsMarketPlanCount(Map params);
	
	
	/**
	 * 获取营销方案自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMarketPlanEval(String eval,Map params);

	/**
	 * 获取营销方案分页
	 * @return
	 */	
	public Page getCsMarketPlanPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取营销方案
	 * @param params
	 * @return
	 */
	public CsMarketPlan getCsMarketPlan(Map params);

	/**
	 * 根据ID取营销方案
	 * @param id
	 * @return
	 */
	public CsMarketPlan getCsMarketPlanById(Long id);


	/**
	 * 保存营销方案
	 * @param csMarketPlan
	 */
	public CsMarketPlan saveCsMarketPlan(CsMarketPlan csMarketPlan);

	/**
	 * 更新营销方案
	 * @param csMarketPlan
	 */
	public void updateCsMarketPlan(CsMarketPlan csMarketPlan);
	/**
	 * 更新营销方案非空字段
	 * @param csMarketPlan
	 */
	public void updateCsMarketPlan$NotNull(CsMarketPlan csMarketPlan);

	/**
	 * 根据ID删除一个营销方案
	 * @param id
	 */
	public void deleteCsMarketPlanById(Long id);
	/**
	 * 根据ID逻辑删除一个营销方案
	 * @param id
	 */
	public void removeCsMarketPlanById(Long id);
	/**
	 * 根据条件更新营销方案
	 * @param values
	 * @param params
	 */
	public void updateCsMarketPlanByConfirm(Map values, Map params);
	/**
	 * 根据条件删除营销方案
	 * @param params
	 */
	public void deleteCsMarketPlanByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}