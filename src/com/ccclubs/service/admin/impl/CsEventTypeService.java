package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsEventTypeDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsEventType;
import com.ccclubs.service.admin.ICsEventTypeService;
import com.lazy3q.web.helper.$;

/**
 * 系统事件类型的Service实现
 * @author 飞啊飘啊
 */
public class CsEventTypeService implements ICsEventTypeService
{
	ICsEventTypeDao csEventTypeDao;
	

	/**
	 * 获取所有系统事件类型
	 * @return
	 */
	public List<CsEventType> getCsEventTypeList(Map params,Integer size)
	{
		return csEventTypeDao.getCsEventTypeList(params,size);
	}
	
	/**
	 * 获取系统事件类型统计
	 * @return
	 */
	public List getCsEventTypeStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csEventTypeDao.getCsEventTypeStats(params,groups,sums);
	}
	
	/**
	 * 获取系统事件类型总数
	 * @return
	 */
	public Long getCsEventTypeCount(Map params)
	{
		return csEventTypeDao.getCsEventTypeCount(params);
	}
	
	/**
	 * 获取系统事件类型自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsEventTypeEval(String eval,Map params)
	{
		return csEventTypeDao.getCsEventTypeEval(eval,params);
	}

	/**
	 * 获取系统事件类型分页
	 * @return
	 */
	public Page getCsEventTypePage(int page,int size,Map params)
	{
		return csEventTypeDao.getCsEventTypePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取系统事件类型
	 * @param params
	 * @return
	 */
	public CsEventType getCsEventType(Map params)
	{
		return csEventTypeDao.getCsEventType(params);
	}

	/**
	 * 根据ID取系统事件类型
	 * @param id
	 * @return
	 */
	public CsEventType getCsEventTypeById(Long id)
	{
		return csEventTypeDao.getCsEventTypeById(id);
	}

	/**
	 * 保存系统事件类型
	 * @param csEventType
	 */
	public CsEventType saveCsEventType(CsEventType csEventType)
	{
		return csEventTypeDao.saveCsEventType(csEventType);
	}
	
	/**
	 * 更新系统事件类型
	 * @param csEventType
	 */
	public void updateCsEventType(CsEventType csEventType)
	{
		csEventTypeDao.updateCsEventType(csEventType);
	}
	/**
	 * 更新系统事件类型非空字段
	 * @param csEventType
	 */
	public void updateCsEventType$NotNull(CsEventType csEventType)
	{
		csEventTypeDao.updateCsEventType$NotNull(csEventType);
	}
	
	/**
	 * 根据ID删除一个系统事件类型
	 * @param id
	 */
	public void deleteCsEventTypeById(Long id)
	{
		csEventTypeDao.deleteCsEventTypeById(id);
	}
	/**
	 * 根据ID逻辑删除一个系统事件类型
	 * @param id
	 */
	public void removeCsEventTypeById(Long id)
	{
		csEventTypeDao.removeCsEventTypeById(id);
	}
	
	/**
	 * 根据条件更新系统事件类型
	 * @param values
	 * @param params
	 */
	public void updateCsEventTypeByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsEventTypeBy时无条件");
		csEventTypeDao.updateCsEventTypeByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除系统事件类型
	 * @param params
	 */
	public void deleteCsEventTypeByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsEventTypeBy时无条件");
		csEventTypeDao.deleteCsEventTypeByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsEventTypeDao getCsEventTypeDao()
	{
		return csEventTypeDao;
	}

	public void setCsEventTypeDao(ICsEventTypeDao csEventTypeDao)
	{
		this.csEventTypeDao = csEventTypeDao;
	}
}