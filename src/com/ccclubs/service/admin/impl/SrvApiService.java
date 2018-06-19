package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ISrvApiDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvApi;
import com.ccclubs.service.admin.ISrvApiService;
import com.lazy3q.web.helper.$;

/**
 * API接口的Service实现
 * @author 飞啊飘啊
 */
public class SrvApiService implements ISrvApiService
{
	ISrvApiDao srvApiDao;
	

	/**
	 * 获取所有API接口
	 * @return
	 */
	public List<SrvApi> getSrvApiList(Map params,Integer size)
	{
		return srvApiDao.getSrvApiList(params,size);
	}
	
	/**
	 * 获取API接口统计
	 * @return
	 */
	public List getSrvApiStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return srvApiDao.getSrvApiStats(params,groups,sums);
	}
	
	/**
	 * 获取API接口总数
	 * @return
	 */
	public Long getSrvApiCount(Map params)
	{
		return srvApiDao.getSrvApiCount(params);
	}
	
	/**
	 * 获取API接口自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvApiEval(String eval,Map params)
	{
		return srvApiDao.getSrvApiEval(eval,params);
	}

	/**
	 * 获取API接口分页
	 * @return
	 */
	public Page getSrvApiPage(int page,int size,Map params)
	{
		return srvApiDao.getSrvApiPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取API接口
	 * @param params
	 * @return
	 */
	public SrvApi getSrvApi(Map params)
	{
		return srvApiDao.getSrvApi(params);
	}

	/**
	 * 根据ID取API接口
	 * @param id
	 * @return
	 */
	public SrvApi getSrvApiById(Long id)
	{
		return srvApiDao.getSrvApiById(id);
	}

	/**
	 * 保存API接口
	 * @param srvApi
	 */
	public SrvApi saveSrvApi(SrvApi srvApi)
	{
		return srvApiDao.saveSrvApi(srvApi);
	}
	
	/**
	 * 更新API接口
	 * @param srvApi
	 */
	public void updateSrvApi(SrvApi srvApi)
	{
		srvApiDao.updateSrvApi(srvApi);
	}
	/**
	 * 更新API接口非空字段
	 * @param srvApi
	 */
	public void updateSrvApi$NotNull(SrvApi srvApi)
	{
		srvApiDao.updateSrvApi$NotNull(srvApi);
	}
	
	/**
	 * 根据ID删除一个API接口
	 * @param id
	 */
	public void deleteSrvApiById(Long id)
	{
		srvApiDao.deleteSrvApiById(id);
	}
	/**
	 * 根据ID逻辑删除一个API接口
	 * @param id
	 */
	public void removeSrvApiById(Long id)
	{
		srvApiDao.removeSrvApiById(id);
	}
	
	/**
	 * 根据条件更新API接口
	 * @param values
	 * @param params
	 */
	public void updateSrvApiByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateSrvApiBy时无条件");
		srvApiDao.updateSrvApiByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除API接口
	 * @param params
	 */
	public void deleteSrvApiByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteSrvApiBy时无条件");
		srvApiDao.deleteSrvApiByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ISrvApiDao getSrvApiDao()
	{
		return srvApiDao;
	}

	public void setSrvApiDao(ISrvApiDao srvApiDao)
	{
		this.srvApiDao = srvApiDao;
	}
}