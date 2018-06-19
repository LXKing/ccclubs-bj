package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsEventHeedDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsEventHeed;
import com.ccclubs.service.admin.ICsEventHeedService;
import com.lazy3q.web.helper.$;

/**
 * 事件关注的Service实现
 * @author 飞啊飘啊
 */
public class CsEventHeedService implements ICsEventHeedService
{
	ICsEventHeedDao csEventHeedDao;
	

	/**
	 * 获取所有事件关注
	 * @return
	 */
	public List<CsEventHeed> getCsEventHeedList(Map params,Integer size)
	{
		return csEventHeedDao.getCsEventHeedList(params,size);
	}
	
	/**
	 * 获取事件关注统计
	 * @return
	 */
	public List getCsEventHeedStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csEventHeedDao.getCsEventHeedStats(params,groups,sums);
	}
	
	/**
	 * 获取事件关注总数
	 * @return
	 */
	public Long getCsEventHeedCount(Map params)
	{
		return csEventHeedDao.getCsEventHeedCount(params);
	}
	
	/**
	 * 获取事件关注自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsEventHeedEval(String eval,Map params)
	{
		return csEventHeedDao.getCsEventHeedEval(eval,params);
	}

	/**
	 * 获取事件关注分页
	 * @return
	 */
	public Page getCsEventHeedPage(int page,int size,Map params)
	{
		return csEventHeedDao.getCsEventHeedPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取事件关注
	 * @param params
	 * @return
	 */
	public CsEventHeed getCsEventHeed(Map params)
	{
		return csEventHeedDao.getCsEventHeed(params);
	}

	/**
	 * 根据ID取事件关注
	 * @param id
	 * @return
	 */
	public CsEventHeed getCsEventHeedById(Long id)
	{
		return csEventHeedDao.getCsEventHeedById(id);
	}

	/**
	 * 保存事件关注
	 * @param csEventHeed
	 */
	public CsEventHeed saveCsEventHeed(CsEventHeed csEventHeed)
	{
		return csEventHeedDao.saveCsEventHeed(csEventHeed);
	}
	
	/**
	 * 更新事件关注
	 * @param csEventHeed
	 */
	public void updateCsEventHeed(CsEventHeed csEventHeed)
	{
		csEventHeedDao.updateCsEventHeed(csEventHeed);
	}
	/**
	 * 更新事件关注非空字段
	 * @param csEventHeed
	 */
	public void updateCsEventHeed$NotNull(CsEventHeed csEventHeed)
	{
		csEventHeedDao.updateCsEventHeed$NotNull(csEventHeed);
	}
	
	/**
	 * 根据ID删除一个事件关注
	 * @param id
	 */
	public void deleteCsEventHeedById(Long id)
	{
		csEventHeedDao.deleteCsEventHeedById(id);
	}
	/**
	 * 根据ID逻辑删除一个事件关注
	 * @param id
	 */
	public void removeCsEventHeedById(Long id)
	{
		csEventHeedDao.removeCsEventHeedById(id);
	}
	
	/**
	 * 根据条件更新事件关注
	 * @param values
	 * @param params
	 */
	public void updateCsEventHeedByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsEventHeedBy时无条件");
		csEventHeedDao.updateCsEventHeedByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除事件关注
	 * @param params
	 */
	public void deleteCsEventHeedByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsEventHeedBy时无条件");
		csEventHeedDao.deleteCsEventHeedByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsEventHeedDao getCsEventHeedDao()
	{
		return csEventHeedDao;
	}

	public void setCsEventHeedDao(ICsEventHeedDao csEventHeedDao)
	{
		this.csEventHeedDao = csEventHeedDao;
	}
}