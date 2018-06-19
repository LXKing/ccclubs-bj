package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ISrvOauthDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvOauth;
import com.ccclubs.service.admin.ISrvOauthService;
import com.lazy3q.web.helper.$;

/**
 * 用户授权的Service实现
 * @author 飞啊飘啊
 */
public class SrvOauthService implements ISrvOauthService
{
	ISrvOauthDao srvOauthDao;
	

	/**
	 * 获取所有用户授权
	 * @return
	 */
	public List<SrvOauth> getSrvOauthList(Map params,Integer size)
	{
		return srvOauthDao.getSrvOauthList(params,size);
	}
	
	/**
	 * 获取用户授权统计
	 * @return
	 */
	public List getSrvOauthStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return srvOauthDao.getSrvOauthStats(params,groups,sums);
	}
	
	/**
	 * 获取用户授权总数
	 * @return
	 */
	public Long getSrvOauthCount(Map params)
	{
		return srvOauthDao.getSrvOauthCount(params);
	}
	
	/**
	 * 获取用户授权自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvOauthEval(String eval,Map params)
	{
		return srvOauthDao.getSrvOauthEval(eval,params);
	}

	/**
	 * 获取用户授权分页
	 * @return
	 */
	public Page getSrvOauthPage(int page,int size,Map params)
	{
		return srvOauthDao.getSrvOauthPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取用户授权
	 * @param params
	 * @return
	 */
	public SrvOauth getSrvOauth(Map params)
	{
		return srvOauthDao.getSrvOauth(params);
	}

	/**
	 * 根据ID取用户授权
	 * @param id
	 * @return
	 */
	public SrvOauth getSrvOauthById(Long id)
	{
		return srvOauthDao.getSrvOauthById(id);
	}

	/**
	 * 保存用户授权
	 * @param srvOauth
	 */
	public SrvOauth saveSrvOauth(SrvOauth srvOauth)
	{
		return srvOauthDao.saveSrvOauth(srvOauth);
	}
	
	/**
	 * 更新用户授权
	 * @param srvOauth
	 */
	public void updateSrvOauth(SrvOauth srvOauth)
	{
		srvOauthDao.updateSrvOauth(srvOauth);
	}
	/**
	 * 更新用户授权非空字段
	 * @param srvOauth
	 */
	public void updateSrvOauth$NotNull(SrvOauth srvOauth)
	{
		srvOauthDao.updateSrvOauth$NotNull(srvOauth);
	}
	
	/**
	 * 根据ID删除一个用户授权
	 * @param id
	 */
	public void deleteSrvOauthById(Long id)
	{
		srvOauthDao.deleteSrvOauthById(id);
	}
	
	/**
	 * 根据条件更新用户授权
	 * @param values
	 * @param params
	 */
	public void updateSrvOauthByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateSrvOauthBy时无条件");
		srvOauthDao.updateSrvOauthByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除用户授权
	 * @param params
	 */
	public void deleteSrvOauthByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteSrvOauthBy时无条件");
		srvOauthDao.deleteSrvOauthByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ISrvOauthDao getSrvOauthDao()
	{
		return srvOauthDao;
	}

	public void setSrvOauthDao(ISrvOauthDao srvOauthDao)
	{
		this.srvOauthDao = srvOauthDao;
	}
}