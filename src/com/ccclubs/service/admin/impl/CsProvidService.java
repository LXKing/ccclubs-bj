package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsProvidDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsProvid;
import com.ccclubs.service.admin.ICsProvidService;
import com.lazy3q.web.helper.$;

/**
 * 供应商的Service实现
 * @author 飞啊飘啊
 */
public class CsProvidService implements ICsProvidService
{
	ICsProvidDao csProvidDao;
	

	/**
	 * 获取所有供应商
	 * @return
	 */
	public List<CsProvid> getCsProvidList(Map params,Integer size)
	{
		return csProvidDao.getCsProvidList(params,size);
	}
	
	/**
	 * 获取供应商统计
	 * @return
	 */
	public List getCsProvidStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csProvidDao.getCsProvidStats(params,groups,sums);
	}
	
	/**
	 * 获取供应商总数
	 * @return
	 */
	public Long getCsProvidCount(Map params)
	{
		return csProvidDao.getCsProvidCount(params);
	}
	
	/**
	 * 获取供应商自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsProvidEval(String eval,Map params)
	{
		return csProvidDao.getCsProvidEval(eval,params);
	}

	/**
	 * 获取供应商分页
	 * @return
	 */
	public Page getCsProvidPage(int page,int size,Map params)
	{
		return csProvidDao.getCsProvidPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取供应商
	 * @param params
	 * @return
	 */
	public CsProvid getCsProvid(Map params)
	{
		return csProvidDao.getCsProvid(params);
	}

	/**
	 * 根据ID取供应商
	 * @param id
	 * @return
	 */
	public CsProvid getCsProvidById(Long id)
	{
		return csProvidDao.getCsProvidById(id);
	}

	/**
	 * 保存供应商
	 * @param csProvid
	 */
	public CsProvid saveCsProvid(CsProvid csProvid)
	{
		return csProvidDao.saveCsProvid(csProvid);
	}
	
	/**
	 * 更新供应商
	 * @param csProvid
	 */
	public void updateCsProvid(CsProvid csProvid)
	{
		csProvidDao.updateCsProvid(csProvid);
	}
	/**
	 * 更新供应商非空字段
	 * @param csProvid
	 */
	public void updateCsProvid$NotNull(CsProvid csProvid)
	{
		csProvidDao.updateCsProvid$NotNull(csProvid);
	}
	
	/**
	 * 根据ID删除一个供应商
	 * @param id
	 */
	public void deleteCsProvidById(Long id)
	{
		csProvidDao.deleteCsProvidById(id);
	}
	/**
	 * 根据ID逻辑删除一个供应商
	 * @param id
	 */
	public void removeCsProvidById(Long id)
	{
		csProvidDao.removeCsProvidById(id);
	}
	
	/**
	 * 根据条件更新供应商
	 * @param values
	 * @param params
	 */
	public void updateCsProvidByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsProvidBy时无条件");
		csProvidDao.updateCsProvidByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除供应商
	 * @param params
	 */
	public void deleteCsProvidByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsProvidBy时无条件");
		csProvidDao.deleteCsProvidByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsProvidDao getCsProvidDao()
	{
		return csProvidDao;
	}

	public void setCsProvidDao(ICsProvidDao csProvidDao)
	{
		this.csProvidDao = csProvidDao;
	}
}