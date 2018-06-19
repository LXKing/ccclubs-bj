package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsActivityDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsActivity;
import com.ccclubs.service.admin.ICsActivityService;
import com.lazy3q.web.helper.$;

/**
 * 营销活动的Service实现
 * @author 飞啊飘啊
 */
public class CsActivityService implements ICsActivityService
{
	ICsActivityDao csActivityDao;
	

	/**
	 * 获取所有营销活动
	 * @return
	 */
	public List<CsActivity> getCsActivityList(Map params,Integer size)
	{
		return csActivityDao.getCsActivityList(params,size);
	}
	
	/**
	 * 获取营销活动统计
	 * @return
	 */
	public List getCsActivityStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csActivityDao.getCsActivityStats(params,groups,sums);
	}
	
	/**
	 * 获取营销活动总数
	 * @return
	 */
	public Long getCsActivityCount(Map params)
	{
		return csActivityDao.getCsActivityCount(params);
	}
	
	/**
	 * 获取营销活动自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsActivityEval(String eval,Map params)
	{
		return csActivityDao.getCsActivityEval(eval,params);
	}

	/**
	 * 获取营销活动分页
	 * @return
	 */
	public Page getCsActivityPage(int page,int size,Map params)
	{
		return csActivityDao.getCsActivityPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取营销活动
	 * @param params
	 * @return
	 */
	public CsActivity getCsActivity(Map params)
	{
		return csActivityDao.getCsActivity(params);
	}

	/**
	 * 根据ID取营销活动
	 * @param id
	 * @return
	 */
	public CsActivity getCsActivityById(Long id)
	{
		return csActivityDao.getCsActivityById(id);
	}

	/**
	 * 保存营销活动
	 * @param csActivity
	 */
	public CsActivity saveCsActivity(CsActivity csActivity)
	{
		return csActivityDao.saveCsActivity(csActivity);
	}
	
	/**
	 * 更新营销活动
	 * @param csActivity
	 */
	public void updateCsActivity(CsActivity csActivity)
	{
		csActivityDao.updateCsActivity(csActivity);
	}
	/**
	 * 更新营销活动非空字段
	 * @param csActivity
	 */
	public void updateCsActivity$NotNull(CsActivity csActivity)
	{
		csActivityDao.updateCsActivity$NotNull(csActivity);
	}
	
	/**
	 * 根据ID删除一个营销活动
	 * @param id
	 */
	public void deleteCsActivityById(Long id)
	{
		csActivityDao.deleteCsActivityById(id);
	}
	/**
	 * 根据ID逻辑删除一个营销活动
	 * @param id
	 */
	public void removeCsActivityById(Long id)
	{
		csActivityDao.removeCsActivityById(id);
	}
	
	/**
	 * 根据条件更新营销活动
	 * @param values
	 * @param params
	 */
	public void updateCsActivityByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsActivityBy时无条件");
		csActivityDao.updateCsActivityByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除营销活动
	 * @param params
	 */
	public void deleteCsActivityByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsActivityBy时无条件");
		csActivityDao.deleteCsActivityByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsActivityDao getCsActivityDao()
	{
		return csActivityDao;
	}

	public void setCsActivityDao(ICsActivityDao csActivityDao)
	{
		this.csActivityDao = csActivityDao;
	}
}