package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsBugDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsBug;
import com.ccclubs.service.admin.ICsBugService;
import com.lazy3q.web.helper.$;

/**
 * 系统BUG的Service实现
 * @author 飞啊飘啊
 */
public class CsBugService implements ICsBugService
{
	ICsBugDao csBugDao;
	

	/**
	 * 获取所有系统BUG
	 * @return
	 */
	public List<CsBug> getCsBugList(Map params,Integer size)
	{
		return csBugDao.getCsBugList(params,size);
	}
	
	/**
	 * 获取系统BUG统计
	 * @return
	 */
	public List getCsBugStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csBugDao.getCsBugStats(params,groups,sums);
	}
	
	/**
	 * 获取系统BUG总数
	 * @return
	 */
	public Long getCsBugCount(Map params)
	{
		return csBugDao.getCsBugCount(params);
	}
	
	/**
	 * 获取系统BUG自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsBugEval(String eval,Map params)
	{
		return csBugDao.getCsBugEval(eval,params);
	}

	/**
	 * 获取系统BUG分页
	 * @return
	 */
	public Page getCsBugPage(int page,int size,Map params)
	{
		return csBugDao.getCsBugPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取系统BUG
	 * @param params
	 * @return
	 */
	public CsBug getCsBug(Map params)
	{
		return csBugDao.getCsBug(params);
	}

	/**
	 * 根据ID取系统BUG
	 * @param id
	 * @return
	 */
	public CsBug getCsBugById(Long id)
	{
		return csBugDao.getCsBugById(id);
	}

	/**
	 * 保存系统BUG
	 * @param csBug
	 */
	public CsBug saveCsBug(CsBug csBug)
	{
		return csBugDao.saveCsBug(csBug);
	}
	
	/**
	 * 更新系统BUG
	 * @param csBug
	 */
	public void updateCsBug(CsBug csBug)
	{
		csBugDao.updateCsBug(csBug);
	}
	/**
	 * 更新系统BUG非空字段
	 * @param csBug
	 */
	public void updateCsBug$NotNull(CsBug csBug)
	{
		csBugDao.updateCsBug$NotNull(csBug);
	}
	
	/**
	 * 根据ID删除一个系统BUG
	 * @param id
	 */
	public void deleteCsBugById(Long id)
	{
		csBugDao.deleteCsBugById(id);
	}
	/**
	 * 根据ID逻辑删除一个系统BUG
	 * @param id
	 */
	public void removeCsBugById(Long id)
	{
		csBugDao.removeCsBugById(id);
	}
	
	/**
	 * 根据条件更新系统BUG
	 * @param values
	 * @param params
	 */
	public void updateCsBugByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsBugBy时无条件");
		csBugDao.updateCsBugByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除系统BUG
	 * @param params
	 */
	public void deleteCsBugByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsBugBy时无条件");
		csBugDao.deleteCsBugByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsBugDao getCsBugDao()
	{
		return csBugDao;
	}

	public void setCsBugDao(ICsBugDao csBugDao)
	{
		this.csBugDao = csBugDao;
	}
}