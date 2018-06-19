package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsSaleEventDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsSaleEvent;
import com.ccclubs.service.admin.ICsSaleEventService;
import com.lazy3q.web.helper.$;

/**
 * 销售活动的Service实现
 * @author 飞啊飘啊
 */
public class CsSaleEventService implements ICsSaleEventService
{
	ICsSaleEventDao csSaleEventDao;
	

	/**
	 * 获取所有销售活动
	 * @return
	 */
	public List<CsSaleEvent> getCsSaleEventList(Map params,Integer size)
	{
		return csSaleEventDao.getCsSaleEventList(params,size);
	}
	
	/**
	 * 获取销售活动统计
	 * @return
	 */
	public List getCsSaleEventStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csSaleEventDao.getCsSaleEventStats(params,groups,sums);
	}
	
	/**
	 * 获取销售活动总数
	 * @return
	 */
	public Long getCsSaleEventCount(Map params)
	{
		return csSaleEventDao.getCsSaleEventCount(params);
	}
	
	/**
	 * 获取销售活动自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsSaleEventEval(String eval,Map params)
	{
		return csSaleEventDao.getCsSaleEventEval(eval,params);
	}

	/**
	 * 获取销售活动分页
	 * @return
	 */
	public Page getCsSaleEventPage(int page,int size,Map params)
	{
		return csSaleEventDao.getCsSaleEventPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取销售活动
	 * @param params
	 * @return
	 */
	public CsSaleEvent getCsSaleEvent(Map params)
	{
		return csSaleEventDao.getCsSaleEvent(params);
	}

	/**
	 * 根据ID取销售活动
	 * @param id
	 * @return
	 */
	public CsSaleEvent getCsSaleEventById(Long id)
	{
		return csSaleEventDao.getCsSaleEventById(id);
	}

	/**
	 * 保存销售活动
	 * @param csSaleEvent
	 */
	public CsSaleEvent saveCsSaleEvent(CsSaleEvent csSaleEvent)
	{
		return csSaleEventDao.saveCsSaleEvent(csSaleEvent);
	}
	
	/**
	 * 更新销售活动
	 * @param csSaleEvent
	 */
	public void updateCsSaleEvent(CsSaleEvent csSaleEvent)
	{
		csSaleEventDao.updateCsSaleEvent(csSaleEvent);
	}
	/**
	 * 更新销售活动非空字段
	 * @param csSaleEvent
	 */
	public void updateCsSaleEvent$NotNull(CsSaleEvent csSaleEvent)
	{
		csSaleEventDao.updateCsSaleEvent$NotNull(csSaleEvent);
	}
	
	/**
	 * 根据ID删除一个销售活动
	 * @param id
	 */
	public void deleteCsSaleEventById(Long id)
	{
		csSaleEventDao.deleteCsSaleEventById(id);
	}
	/**
	 * 根据ID逻辑删除一个销售活动
	 * @param id
	 */
	public void removeCsSaleEventById(Long id)
	{
		csSaleEventDao.removeCsSaleEventById(id);
	}
	
	/**
	 * 根据条件更新销售活动
	 * @param values
	 * @param params
	 */
	public void updateCsSaleEventByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsSaleEventBy时无条件");
		csSaleEventDao.updateCsSaleEventByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除销售活动
	 * @param params
	 */
	public void deleteCsSaleEventByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsSaleEventBy时无条件");
		csSaleEventDao.deleteCsSaleEventByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsSaleEventDao getCsSaleEventDao()
	{
		return csSaleEventDao;
	}

	public void setCsSaleEventDao(ICsSaleEventDao csSaleEventDao)
	{
		this.csSaleEventDao = csSaleEventDao;
	}
}