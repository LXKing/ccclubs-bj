package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsRevisitDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsRevisit;
import com.ccclubs.service.admin.ICsRevisitService;
import com.lazy3q.web.helper.$;

/**
 * 用户回访的Service实现
 * @author 飞啊飘啊
 */
public class CsRevisitService implements ICsRevisitService
{
	ICsRevisitDao csRevisitDao;
	

	/**
	 * 获取所有用户回访
	 * @return
	 */
	public List<CsRevisit> getCsRevisitList(Map params,Integer size)
	{
		return csRevisitDao.getCsRevisitList(params,size);
	}
	
	/**
	 * 获取用户回访统计
	 * @return
	 */
	public List getCsRevisitStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csRevisitDao.getCsRevisitStats(params,groups,sums);
	}
	
	/**
	 * 获取用户回访总数
	 * @return
	 */
	public Long getCsRevisitCount(Map params)
	{
		return csRevisitDao.getCsRevisitCount(params);
	}
	
	/**
	 * 获取用户回访自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRevisitEval(String eval,Map params)
	{
		return csRevisitDao.getCsRevisitEval(eval,params);
	}

	/**
	 * 获取用户回访分页
	 * @return
	 */
	public Page getCsRevisitPage(int page,int size,Map params)
	{
		return csRevisitDao.getCsRevisitPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取用户回访
	 * @param params
	 * @return
	 */
	public CsRevisit getCsRevisit(Map params)
	{
		return csRevisitDao.getCsRevisit(params);
	}

	/**
	 * 根据ID取用户回访
	 * @param id
	 * @return
	 */
	public CsRevisit getCsRevisitById(Long id)
	{
		return csRevisitDao.getCsRevisitById(id);
	}

	/**
	 * 保存用户回访
	 * @param csRevisit
	 */
	public CsRevisit saveCsRevisit(CsRevisit csRevisit)
	{
		return csRevisitDao.saveCsRevisit(csRevisit);
	}
	
	/**
	 * 更新用户回访
	 * @param csRevisit
	 */
	public void updateCsRevisit(CsRevisit csRevisit)
	{
		csRevisitDao.updateCsRevisit(csRevisit);
	}
	/**
	 * 更新用户回访非空字段
	 * @param csRevisit
	 */
	public void updateCsRevisit$NotNull(CsRevisit csRevisit)
	{
		csRevisitDao.updateCsRevisit$NotNull(csRevisit);
	}
	
	/**
	 * 根据ID删除一个用户回访
	 * @param id
	 */
	public void deleteCsRevisitById(Long id)
	{
		csRevisitDao.deleteCsRevisitById(id);
	}
	/**
	 * 根据ID逻辑删除一个用户回访
	 * @param id
	 */
	public void removeCsRevisitById(Long id)
	{
		csRevisitDao.removeCsRevisitById(id);
	}
	
	/**
	 * 根据条件更新用户回访
	 * @param values
	 * @param params
	 */
	public void updateCsRevisitByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsRevisitBy时无条件");
		csRevisitDao.updateCsRevisitByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除用户回访
	 * @param params
	 */
	public void deleteCsRevisitByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsRevisitBy时无条件");
		csRevisitDao.deleteCsRevisitByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsRevisitDao getCsRevisitDao()
	{
		return csRevisitDao;
	}

	public void setCsRevisitDao(ICsRevisitDao csRevisitDao)
	{
		this.csRevisitDao = csRevisitDao;
	}
}