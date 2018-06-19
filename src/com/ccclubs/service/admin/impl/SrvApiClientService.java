package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ISrvApiClientDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvApiClient;
import com.ccclubs.service.admin.ISrvApiClientService;
import com.lazy3q.web.helper.$;

/**
 * API客户端的Service实现
 * @author 飞啊飘啊
 */
public class SrvApiClientService implements ISrvApiClientService
{
	ISrvApiClientDao srvApiClientDao;
	

	/**
	 * 获取所有API客户端
	 * @return
	 */
	public List<SrvApiClient> getSrvApiClientList(Map params,Integer size)
	{
		return srvApiClientDao.getSrvApiClientList(params,size);
	}
	
	/**
	 * 获取API客户端统计
	 * @return
	 */
	public List getSrvApiClientStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return srvApiClientDao.getSrvApiClientStats(params,groups,sums);
	}
	
	/**
	 * 获取API客户端总数
	 * @return
	 */
	public Long getSrvApiClientCount(Map params)
	{
		return srvApiClientDao.getSrvApiClientCount(params);
	}
	
	/**
	 * 获取API客户端自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvApiClientEval(String eval,Map params)
	{
		return srvApiClientDao.getSrvApiClientEval(eval,params);
	}

	/**
	 * 获取API客户端分页
	 * @return
	 */
	public Page getSrvApiClientPage(int page,int size,Map params)
	{
		return srvApiClientDao.getSrvApiClientPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取API客户端
	 * @param params
	 * @return
	 */
	public SrvApiClient getSrvApiClient(Map params)
	{
		return srvApiClientDao.getSrvApiClient(params);
	}

	/**
	 * 根据ID取API客户端
	 * @param id
	 * @return
	 */
	public SrvApiClient getSrvApiClientById(Long id)
	{
		return srvApiClientDao.getSrvApiClientById(id);
	}

	/**
	 * 保存API客户端
	 * @param srvApiClient
	 */
	public SrvApiClient saveSrvApiClient(SrvApiClient srvApiClient)
	{
		return srvApiClientDao.saveSrvApiClient(srvApiClient);
	}
	
	/**
	 * 更新API客户端
	 * @param srvApiClient
	 */
	public void updateSrvApiClient(SrvApiClient srvApiClient)
	{
		srvApiClientDao.updateSrvApiClient(srvApiClient);
	}
	/**
	 * 更新API客户端非空字段
	 * @param srvApiClient
	 */
	public void updateSrvApiClient$NotNull(SrvApiClient srvApiClient)
	{
		srvApiClientDao.updateSrvApiClient$NotNull(srvApiClient);
	}
	
	/**
	 * 根据ID删除一个API客户端
	 * @param id
	 */
	public void deleteSrvApiClientById(Long id)
	{
		srvApiClientDao.deleteSrvApiClientById(id);
	}
	/**
	 * 根据ID逻辑删除一个API客户端
	 * @param id
	 */
	public void removeSrvApiClientById(Long id)
	{
		srvApiClientDao.removeSrvApiClientById(id);
	}
	
	/**
	 * 根据条件更新API客户端
	 * @param values
	 * @param params
	 */
	public void updateSrvApiClientByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateSrvApiClientBy时无条件");
		srvApiClientDao.updateSrvApiClientByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除API客户端
	 * @param params
	 */
	public void deleteSrvApiClientByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteSrvApiClientBy时无条件");
		srvApiClientDao.deleteSrvApiClientByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ISrvApiClientDao getSrvApiClientDao()
	{
		return srvApiClientDao;
	}

	public void setSrvApiClientDao(ISrvApiClientDao srvApiClientDao)
	{
		this.srvApiClientDao = srvApiClientDao;
	}
}