package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsLimitTimeDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsLimitTime;
import com.ccclubs.service.admin.ICsLimitTimeService;
import com.lazy3q.web.helper.$;

/**
 * 交通限行的Service实现
 * @author 飞啊飘啊
 */
public class CsLimitTimeService implements ICsLimitTimeService
{
	ICsLimitTimeDao csLimitTimeDao;
	

	/**
	 * 获取所有交通限行
	 * @return
	 */
	public List<CsLimitTime> getCsLimitTimeList(Map params,Integer size)
	{
		return csLimitTimeDao.getCsLimitTimeList(params,size);
	}
	
	/**
	 * 获取交通限行统计
	 * @return
	 */
	public List getCsLimitTimeStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csLimitTimeDao.getCsLimitTimeStats(params,groups,sums);
	}
	
	/**
	 * 获取交通限行总数
	 * @return
	 */
	public Long getCsLimitTimeCount(Map params)
	{
		return csLimitTimeDao.getCsLimitTimeCount(params);
	}
	
	/**
	 * 获取交通限行自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsLimitTimeEval(String eval,Map params)
	{
		return csLimitTimeDao.getCsLimitTimeEval(eval,params);
	}

	/**
	 * 获取交通限行分页
	 * @return
	 */
	public Page getCsLimitTimePage(int page,int size,Map params)
	{
		return csLimitTimeDao.getCsLimitTimePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取交通限行
	 * @param params
	 * @return
	 */
	public CsLimitTime getCsLimitTime(Map params)
	{
		return csLimitTimeDao.getCsLimitTime(params);
	}

	/**
	 * 根据ID取交通限行
	 * @param id
	 * @return
	 */
	public CsLimitTime getCsLimitTimeById(Long id)
	{
		return csLimitTimeDao.getCsLimitTimeById(id);
	}

	/**
	 * 保存交通限行
	 * @param csLimitTime
	 */
	public CsLimitTime saveCsLimitTime(CsLimitTime csLimitTime)
	{
		return csLimitTimeDao.saveCsLimitTime(csLimitTime);
	}
	
	/**
	 * 更新交通限行
	 * @param csLimitTime
	 */
	public void updateCsLimitTime(CsLimitTime csLimitTime)
	{
		csLimitTimeDao.updateCsLimitTime(csLimitTime);
	}
	/**
	 * 更新交通限行非空字段
	 * @param csLimitTime
	 */
	public void updateCsLimitTime$NotNull(CsLimitTime csLimitTime)
	{
		csLimitTimeDao.updateCsLimitTime$NotNull(csLimitTime);
	}
	
	/**
	 * 根据ID删除一个交通限行
	 * @param id
	 */
	public void deleteCsLimitTimeById(Long id)
	{
		csLimitTimeDao.deleteCsLimitTimeById(id);
	}
	
	/**
	 * 根据条件更新交通限行
	 * @param values
	 * @param params
	 */
	public void updateCsLimitTimeByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsLimitTimeBy时无条件");
		csLimitTimeDao.updateCsLimitTimeByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除交通限行
	 * @param params
	 */
	public void deleteCsLimitTimeByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsLimitTimeBy时无条件");
		csLimitTimeDao.deleteCsLimitTimeByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsLimitTimeDao getCsLimitTimeDao()
	{
		return csLimitTimeDao;
	}

	public void setCsLimitTimeDao(ICsLimitTimeDao csLimitTimeDao)
	{
		this.csLimitTimeDao = csLimitTimeDao;
	}
}