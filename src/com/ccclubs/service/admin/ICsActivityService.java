package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsActivity;

/**
 * 营销活动的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsActivityService
{
	/**
	 * 获取所有营销活动
	 * @return
	 */
	public List<CsActivity> getCsActivityList(Map params,Integer size);
	
	/**
	 * 获取营销活动统计
	 * @return
	 */
	public List getCsActivityStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取营销活动总数
	 * @return
	 */	
	public Long getCsActivityCount(Map params);
	
	
	/**
	 * 获取营销活动自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsActivityEval(String eval,Map params);

	/**
	 * 获取营销活动分页
	 * @return
	 */	
	public Page getCsActivityPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取营销活动
	 * @param params
	 * @return
	 */
	public CsActivity getCsActivity(Map params);

	/**
	 * 根据ID取营销活动
	 * @param id
	 * @return
	 */
	public CsActivity getCsActivityById(Long id);


	/**
	 * 保存营销活动
	 * @param csActivity
	 */
	public CsActivity saveCsActivity(CsActivity csActivity);

	/**
	 * 更新营销活动
	 * @param csActivity
	 */
	public void updateCsActivity(CsActivity csActivity);
	/**
	 * 更新营销活动非空字段
	 * @param csActivity
	 */
	public void updateCsActivity$NotNull(CsActivity csActivity);

	/**
	 * 根据ID删除一个营销活动
	 * @param id
	 */
	public void deleteCsActivityById(Long id);
	/**
	 * 根据ID逻辑删除一个营销活动
	 * @param id
	 */
	public void removeCsActivityById(Long id);
	/**
	 * 根据条件更新营销活动
	 * @param values
	 * @param params
	 */
	public void updateCsActivityByConfirm(Map values, Map params);
	/**
	 * 根据条件删除营销活动
	 * @param params
	 */
	public void deleteCsActivityByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}