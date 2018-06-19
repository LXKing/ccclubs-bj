package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsHolidayDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsHoliday;
import com.ccclubs.service.admin.ICsHolidayService;
import com.lazy3q.web.helper.$;

/**
 * 节假日的Service实现
 * @author 飞啊飘啊
 */
public class CsHolidayService implements ICsHolidayService
{
	ICsHolidayDao csHolidayDao;
	

	/**
	 * 获取所有节假日
	 * @return
	 */
	public List<CsHoliday> getCsHolidayList(Map params,Integer size)
	{
		return csHolidayDao.getCsHolidayList(params,size);
	}
	
	/**
	 * 获取节假日统计
	 * @return
	 */
	public List getCsHolidayStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csHolidayDao.getCsHolidayStats(params,groups,sums);
	}
	
	/**
	 * 获取节假日总数
	 * @return
	 */
	public Long getCsHolidayCount(Map params)
	{
		return csHolidayDao.getCsHolidayCount(params);
	}
	
	/**
	 * 获取节假日自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsHolidayEval(String eval,Map params)
	{
		return csHolidayDao.getCsHolidayEval(eval,params);
	}

	/**
	 * 获取节假日分页
	 * @return
	 */
	public Page getCsHolidayPage(int page,int size,Map params)
	{
		return csHolidayDao.getCsHolidayPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取节假日
	 * @param params
	 * @return
	 */
	public CsHoliday getCsHoliday(Map params)
	{
		return csHolidayDao.getCsHoliday(params);
	}

	/**
	 * 根据ID取节假日
	 * @param id
	 * @return
	 */
	public CsHoliday getCsHolidayById(Long id)
	{
		return csHolidayDao.getCsHolidayById(id);
	}

	/**
	 * 保存节假日
	 * @param csHoliday
	 */
	public CsHoliday saveCsHoliday(CsHoliday csHoliday)
	{
		return csHolidayDao.saveCsHoliday(csHoliday);
	}
	
	/**
	 * 更新节假日
	 * @param csHoliday
	 */
	public void updateCsHoliday(CsHoliday csHoliday)
	{
		csHolidayDao.updateCsHoliday(csHoliday);
	}
	/**
	 * 更新节假日非空字段
	 * @param csHoliday
	 */
	public void updateCsHoliday$NotNull(CsHoliday csHoliday)
	{
		csHolidayDao.updateCsHoliday$NotNull(csHoliday);
	}
	
	/**
	 * 根据ID删除一个节假日
	 * @param id
	 */
	public void deleteCsHolidayById(Long id)
	{
		csHolidayDao.deleteCsHolidayById(id);
	}
	
	/**
	 * 根据条件更新节假日
	 * @param values
	 * @param params
	 */
	public void updateCsHolidayByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsHolidayBy时无条件");
		csHolidayDao.updateCsHolidayByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除节假日
	 * @param params
	 */
	public void deleteCsHolidayByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsHolidayBy时无条件");
		csHolidayDao.deleteCsHolidayByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsHolidayDao getCsHolidayDao()
	{
		return csHolidayDao;
	}

	public void setCsHolidayDao(ICsHolidayDao csHolidayDao)
	{
		this.csHolidayDao = csHolidayDao;
	}
}