package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ISrvQueryDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvQuery;
import com.ccclubs.service.admin.ISrvQueryService;
import com.lazy3q.web.helper.$;

/**
 * 表单查询的Service实现
 * @author 飞啊飘啊
 */
public class SrvQueryService implements ISrvQueryService
{
	ISrvQueryDao srvQueryDao;
	

	/**
	 * 获取所有表单查询
	 * @return
	 */
	public List<SrvQuery> getSrvQueryList(Map params,Integer size)
	{
		return srvQueryDao.getSrvQueryList(params,size);
	}
	
	/**
	 * 获取表单查询统计
	 * @return
	 */
	public List getSrvQueryStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return srvQueryDao.getSrvQueryStats(params,groups,sums);
	}
	
	/**
	 * 获取表单查询总数
	 * @return
	 */
	public Long getSrvQueryCount(Map params)
	{
		return srvQueryDao.getSrvQueryCount(params);
	}
	
	/**
	 * 获取表单查询自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvQueryEval(String eval,Map params)
	{
		return srvQueryDao.getSrvQueryEval(eval,params);
	}

	/**
	 * 获取表单查询分页
	 * @return
	 */
	public Page getSrvQueryPage(int page,int size,Map params)
	{
		return srvQueryDao.getSrvQueryPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取表单查询
	 * @param params
	 * @return
	 */
	public SrvQuery getSrvQuery(Map params)
	{
		return srvQueryDao.getSrvQuery(params);
	}

	/**
	 * 根据ID取表单查询
	 * @param id
	 * @return
	 */
	public SrvQuery getSrvQueryById(Long id)
	{
		return srvQueryDao.getSrvQueryById(id);
	}

	/**
	 * 保存表单查询
	 * @param srvQuery
	 */
	public SrvQuery saveSrvQuery(SrvQuery srvQuery)
	{
		return srvQueryDao.saveSrvQuery(srvQuery);
	}
	
	/**
	 * 更新表单查询
	 * @param srvQuery
	 */
	public void updateSrvQuery(SrvQuery srvQuery)
	{
		srvQueryDao.updateSrvQuery(srvQuery);
	}
	/**
	 * 更新表单查询非空字段
	 * @param srvQuery
	 */
	public void updateSrvQuery$NotNull(SrvQuery srvQuery)
	{
		srvQueryDao.updateSrvQuery$NotNull(srvQuery);
	}
	
	/**
	 * 根据ID删除一个表单查询
	 * @param id
	 */
	public void deleteSrvQueryById(Long id)
	{
		srvQueryDao.deleteSrvQueryById(id);
	}
	/**
	 * 根据ID逻辑删除一个表单查询
	 * @param id
	 */
	public void removeSrvQueryById(Long id)
	{
		srvQueryDao.removeSrvQueryById(id);
	}
	
	/**
	 * 根据条件更新表单查询
	 * @param values
	 * @param params
	 */
	public void updateSrvQueryByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateSrvQueryBy时无条件");
		srvQueryDao.updateSrvQueryByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除表单查询
	 * @param params
	 */
	public void deleteSrvQueryByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteSrvQueryBy时无条件");
		srvQueryDao.deleteSrvQueryByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ISrvQueryDao getSrvQueryDao()
	{
		return srvQueryDao;
	}

	public void setSrvQueryDao(ISrvQueryDao srvQueryDao)
	{
		this.srvQueryDao = srvQueryDao;
	}
}