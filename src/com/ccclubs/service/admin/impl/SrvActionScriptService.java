package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ISrvActionScriptDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.SrvActionScript;
import com.ccclubs.service.admin.ISrvActionScriptService;
import com.lazy3q.web.helper.$;

/**
 * 后台事务脚本的Service实现
 * @author 飞啊飘啊
 */
public class SrvActionScriptService implements ISrvActionScriptService
{
	ISrvActionScriptDao srvActionScriptDao;
	

	/**
	 * 获取所有后台事务脚本
	 * @return
	 */
	public List<SrvActionScript> getSrvActionScriptList(Map params,Integer size)
	{
		return srvActionScriptDao.getSrvActionScriptList(params,size);
	}
	
	/**
	 * 获取后台事务脚本统计
	 * @return
	 */
	public List getSrvActionScriptStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return srvActionScriptDao.getSrvActionScriptStats(params,groups,sums);
	}
	
	/**
	 * 获取后台事务脚本总数
	 * @return
	 */
	public Long getSrvActionScriptCount(Map params)
	{
		return srvActionScriptDao.getSrvActionScriptCount(params);
	}
	
	/**
	 * 获取后台事务脚本自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvActionScriptEval(String eval,Map params)
	{
		return srvActionScriptDao.getSrvActionScriptEval(eval,params);
	}

	/**
	 * 获取后台事务脚本分页
	 * @return
	 */
	public Page getSrvActionScriptPage(int page,int size,Map params)
	{
		return srvActionScriptDao.getSrvActionScriptPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取后台事务脚本
	 * @param params
	 * @return
	 */
	public SrvActionScript getSrvActionScript(Map params)
	{
		return srvActionScriptDao.getSrvActionScript(params);
	}

	/**
	 * 根据ID取后台事务脚本
	 * @param id
	 * @return
	 */
	public SrvActionScript getSrvActionScriptById(Long id)
	{
		return srvActionScriptDao.getSrvActionScriptById(id);
	}

	/**
	 * 保存后台事务脚本
	 * @param srvActionScript
	 */
	public SrvActionScript saveSrvActionScript(SrvActionScript srvActionScript)
	{
		return srvActionScriptDao.saveSrvActionScript(srvActionScript);
	}
	
	/**
	 * 更新后台事务脚本
	 * @param srvActionScript
	 */
	public void updateSrvActionScript(SrvActionScript srvActionScript)
	{
		srvActionScriptDao.updateSrvActionScript(srvActionScript);
	}
	/**
	 * 更新后台事务脚本非空字段
	 * @param srvActionScript
	 */
	public void updateSrvActionScript$NotNull(SrvActionScript srvActionScript)
	{
		srvActionScriptDao.updateSrvActionScript$NotNull(srvActionScript);
	}
	
	/**
	 * 根据ID删除一个后台事务脚本
	 * @param id
	 */
	public void deleteSrvActionScriptById(Long id)
	{
		srvActionScriptDao.deleteSrvActionScriptById(id);
	}
	/**
	 * 根据ID逻辑删除一个后台事务脚本
	 * @param id
	 */
	public void removeSrvActionScriptById(Long id)
	{
		srvActionScriptDao.removeSrvActionScriptById(id);
	}
	
	/**
	 * 根据条件更新后台事务脚本
	 * @param values
	 * @param params
	 */
	public void updateSrvActionScriptByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateSrvActionScriptBy时无条件");
		srvActionScriptDao.updateSrvActionScriptByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除后台事务脚本
	 * @param params
	 */
	public void deleteSrvActionScriptByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteSrvActionScriptBy时无条件");
		srvActionScriptDao.deleteSrvActionScriptByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ISrvActionScriptDao getSrvActionScriptDao()
	{
		return srvActionScriptDao;
	}

	public void setSrvActionScriptDao(ISrvActionScriptDao srvActionScriptDao)
	{
		this.srvActionScriptDao = srvActionScriptDao;
	}
}