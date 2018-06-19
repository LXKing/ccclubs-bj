package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ISrvUserExpDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvUserExp;
import com.ccclubs.service.admin.ISrvUserExpService;
import com.lazy3q.web.helper.$;

/**
 * 用户扩展信息的Service实现
 * @author Joel
 */
public class SrvUserExpService implements ISrvUserExpService
{
	ISrvUserExpDao srvUserExpDao;
	

	/**
	 * 获取所有用户扩展信息
	 * @return
	 */
	public List<SrvUserExp> getSrvUserExpList(Map params,Integer size)
	{
		return srvUserExpDao.getSrvUserExpList(params,size);
	}
	
	/**
	 * 获取用户扩展信息统计
	 * @return
	 */
	public List getSrvUserExpStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return srvUserExpDao.getSrvUserExpStats(params,groups,sums);
	}
	
	/**
	 * 获取用户扩展信息总数
	 * @return
	 */
	public Long getSrvUserExpCount(Map params)
	{
		return srvUserExpDao.getSrvUserExpCount(params);
	}
	
	/**
	 * 获取用户扩展信息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvUserExpEval(String eval,Map params)
	{
		return srvUserExpDao.getSrvUserExpEval(eval,params);
	}

	/**
	 * 获取用户扩展信息分页
	 * @return
	 */
	public Page getSrvUserExpPage(int page,int size,Map params)
	{
		return srvUserExpDao.getSrvUserExpPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取用户扩展信息
	 * @param params
	 * @return
	 */
	public SrvUserExp getSrvUserExp(Map params)
	{
		return srvUserExpDao.getSrvUserExp(params);
	}

	/**
	 * 根据ID取用户扩展信息
	 * @param id
	 * @return
	 */
	public SrvUserExp getSrvUserExpById(Long id)
	{
		return srvUserExpDao.getSrvUserExpById(id);
	}

	/**
	 * 保存用户扩展信息
	 * @param srvUserExp
	 */
	public SrvUserExp saveSrvUserExp(SrvUserExp srvUserExp)
	{
		return srvUserExpDao.saveSrvUserExp(srvUserExp);
	}
	
	/**
	 * 更新用户扩展信息
	 * @param srvUserExp
	 */
	public void updateSrvUserExp(SrvUserExp srvUserExp)
	{
		srvUserExpDao.updateSrvUserExp(srvUserExp);
	}
	/**
	 * 更新用户扩展信息非空字段
	 * @param srvUserExp
	 */
	public void updateSrvUserExp$NotNull(SrvUserExp srvUserExp)
	{
		srvUserExpDao.updateSrvUserExp$NotNull(srvUserExp);
	}
	
	/**
	 * 根据ID删除一个用户扩展信息
	 * @param id
	 */
	public void deleteSrvUserExpById(Long id)
	{
		srvUserExpDao.deleteSrvUserExpById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个用户扩展信息
	 * @param id
	 */
	public void removeSrvUserExpById(Long id)
	{
		srvUserExpDao.removeSrvUserExpById(id);
	}
	
	/**
	 * 根据条件更新用户扩展信息
	 * @param values
	 * @param params
	 */
	public void updateSrvUserExpByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateSrvUserExpBy时无条件");
		srvUserExpDao.updateSrvUserExpByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除用户扩展信息
	 * @param params
	 */
	public void deleteSrvUserExpByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteSrvUserExpBy时无条件");
		srvUserExpDao.deleteSrvUserExpByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ISrvUserExpDao getSrvUserExpDao()
	{
		return srvUserExpDao;
	}

	public void setSrvUserExpDao(ISrvUserExpDao srvUserExpDao)
	{
		this.srvUserExpDao = srvUserExpDao;
	}
}