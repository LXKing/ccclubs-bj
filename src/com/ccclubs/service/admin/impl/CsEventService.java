package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsEventDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsEvent;
import com.ccclubs.service.admin.ICsEventService;
import com.lazy3q.web.helper.$;

/**
 * 系统事件的Service实现
 * @author 飞啊飘啊
 */
public class CsEventService implements ICsEventService
{
	ICsEventDao csEventDao;
	

	/**
	 * 获取所有系统事件
	 * @return
	 */
	public List<CsEvent> getCsEventList(Map params,Integer size)
	{
		return csEventDao.getCsEventList(params,size);
	}
	
	/**
	 * 获取系统事件统计
	 * @return
	 */
	public List getCsEventStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csEventDao.getCsEventStats(params,groups,sums);
	}
	
	/**
	 * 获取系统事件总数
	 * @return
	 */
	public Long getCsEventCount(Map params)
	{
		return csEventDao.getCsEventCount(params);
	}
	
	/**
	 * 获取系统事件自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsEventEval(String eval,Map params)
	{
		return csEventDao.getCsEventEval(eval,params);
	}

	/**
	 * 获取系统事件分页
	 * @return
	 */
	public Page getCsEventPage(int page,int size,Map params)
	{
		return csEventDao.getCsEventPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取系统事件
	 * @param params
	 * @return
	 */
	public CsEvent getCsEvent(Map params)
	{
		return csEventDao.getCsEvent(params);
	}

	/**
	 * 根据ID取系统事件
	 * @param id
	 * @return
	 */
	public CsEvent getCsEventById(Long id)
	{
		return csEventDao.getCsEventById(id);
	}

	/**
	 * 保存系统事件
	 * @param csEvent
	 */
	public CsEvent saveCsEvent(CsEvent csEvent)
	{
		return csEventDao.saveCsEvent(csEvent);
	}
	
	/**
	 * 更新系统事件
	 * @param csEvent
	 */
	public void updateCsEvent(CsEvent csEvent)
	{
		csEventDao.updateCsEvent(csEvent);
	}
	/**
	 * 更新系统事件非空字段
	 * @param csEvent
	 */
	public void updateCsEvent$NotNull(CsEvent csEvent)
	{
		csEventDao.updateCsEvent$NotNull(csEvent);
	}
	
	/**
	 * 根据ID删除一个系统事件
	 * @param id
	 */
	public void deleteCsEventById(Long id)
	{
		csEventDao.deleteCsEventById(id);
	}
	/**
	 * 根据ID逻辑删除一个系统事件
	 * @param id
	 */
	public void removeCsEventById(Long id)
	{
		csEventDao.removeCsEventById(id);
	}
	
	/**
	 * 根据条件更新系统事件
	 * @param values
	 * @param params
	 */
	public void updateCsEventByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsEventBy时无条件");
		csEventDao.updateCsEventByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除系统事件
	 * @param params
	 */
	public void deleteCsEventByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsEventBy时无条件");
		csEventDao.deleteCsEventByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsEventDao getCsEventDao()
	{
		return csEventDao;
	}

	public void setCsEventDao(ICsEventDao csEventDao)
	{
		this.csEventDao = csEventDao;
	}
}