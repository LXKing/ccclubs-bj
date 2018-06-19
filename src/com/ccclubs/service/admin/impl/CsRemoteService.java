package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsRemoteDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRemote;
import com.ccclubs.service.admin.ICsRemoteService;
import com.lazy3q.web.helper.$;

/**
 * 远程控制的Service实现
 * @author 飞啊飘啊
 */
public class CsRemoteService implements ICsRemoteService
{
	ICsRemoteDao csRemoteDao;
	

	/**
	 * 获取所有远程控制
	 * @return
	 */
	public List<CsRemote> getCsRemoteList(Map params,Integer size)
	{
		return csRemoteDao.getCsRemoteList(params,size);
	}
	
	/**
	 * 获取远程控制统计
	 * @return
	 */
	public List getCsRemoteStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csRemoteDao.getCsRemoteStats(params,groups,sums);
	}
	
	/**
	 * 获取远程控制总数
	 * @return
	 */
	public Long getCsRemoteCount(Map params)
	{
		return csRemoteDao.getCsRemoteCount(params);
	}
	
	/**
	 * 获取远程控制自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRemoteEval(String eval,Map params)
	{
		return csRemoteDao.getCsRemoteEval(eval,params);
	}

	/**
	 * 获取远程控制分页
	 * @return
	 */
	public Page getCsRemotePage(int page,int size,Map params)
	{
		return csRemoteDao.getCsRemotePage(page,size,params);
	}
	
	/**
	 * 根据查询条件取远程控制
	 * @param params
	 * @return
	 */
	public CsRemote getCsRemote(Map params)
	{
		return csRemoteDao.getCsRemote(params);
	}

	/**
	 * 根据ID取远程控制
	 * @param id
	 * @return
	 */
	public CsRemote getCsRemoteById(Long id)
	{
		return csRemoteDao.getCsRemoteById(id);
	}

	/**
	 * 保存远程控制
	 * @param csRemote
	 */
	public CsRemote saveCsRemote(CsRemote csRemote)
	{
		return csRemoteDao.saveCsRemote(csRemote);
	}
	
	/**
	 * 更新远程控制
	 * @param csRemote
	 */
	public void updateCsRemote(CsRemote csRemote)
	{
		csRemoteDao.updateCsRemote(csRemote);
	}
	/**
	 * 更新远程控制非空字段
	 * @param csRemote
	 */
	public void updateCsRemote$NotNull(CsRemote csRemote)
	{
		csRemoteDao.updateCsRemote$NotNull(csRemote);
	}
	
	/**
	 * 根据ID删除一个远程控制
	 * @param id
	 */
	public void deleteCsRemoteById(Long id)
	{
		csRemoteDao.deleteCsRemoteById(id);
	}
	
	/**
	 * 根据条件更新远程控制
	 * @param values
	 * @param params
	 */
	public void updateCsRemoteByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsRemoteBy时无条件");
		csRemoteDao.updateCsRemoteByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除远程控制
	 * @param params
	 */
	public void deleteCsRemoteByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsRemoteBy时无条件");
		csRemoteDao.deleteCsRemoteByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsRemoteDao getCsRemoteDao()
	{
		return csRemoteDao;
	}

	public void setCsRemoteDao(ICsRemoteDao csRemoteDao)
	{
		this.csRemoteDao = csRemoteDao;
	}
}