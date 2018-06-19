package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsUnitMtDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsUnitMt;
import com.ccclubs.service.admin.ICsUnitMtService;
import com.lazy3q.web.helper.$;

/**
 * 推送消息的Service实现
 * @author 飞啊飘啊
 */
public class CsUnitMtService implements ICsUnitMtService
{
	ICsUnitMtDao csUnitMtDao;
	

	/**
	 * 获取所有推送消息
	 * @return
	 */
	public List<CsUnitMt> getCsUnitMtList(Map params,Integer size)
	{
		return csUnitMtDao.getCsUnitMtList(params,size);
	}
	
	/**
	 * 获取推送消息统计
	 * @return
	 */
	public List getCsUnitMtStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csUnitMtDao.getCsUnitMtStats(params,groups,sums);
	}
	
	/**
	 * 获取推送消息总数
	 * @return
	 */
	public Long getCsUnitMtCount(Map params)
	{
		return csUnitMtDao.getCsUnitMtCount(params);
	}
	
	/**
	 * 获取推送消息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUnitMtEval(String eval,Map params)
	{
		return csUnitMtDao.getCsUnitMtEval(eval,params);
	}

	/**
	 * 获取推送消息分页
	 * @return
	 */
	public Page getCsUnitMtPage(int page,int size,Map params)
	{
		return csUnitMtDao.getCsUnitMtPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取推送消息
	 * @param params
	 * @return
	 */
	public CsUnitMt getCsUnitMt(Map params)
	{
		return csUnitMtDao.getCsUnitMt(params);
	}

	/**
	 * 根据ID取推送消息
	 * @param id
	 * @return
	 */
	public CsUnitMt getCsUnitMtById(Long id)
	{
		return csUnitMtDao.getCsUnitMtById(id);
	}

	/**
	 * 保存推送消息
	 * @param csUnitMt
	 */
	public CsUnitMt saveCsUnitMt(CsUnitMt csUnitMt)
	{
		return csUnitMtDao.saveCsUnitMt(csUnitMt);
	}
	
	/**
	 * 更新推送消息
	 * @param csUnitMt
	 */
	public void updateCsUnitMt(CsUnitMt csUnitMt)
	{
		csUnitMtDao.updateCsUnitMt(csUnitMt);
	}
	/**
	 * 更新推送消息非空字段
	 * @param csUnitMt
	 */
	public void updateCsUnitMt$NotNull(CsUnitMt csUnitMt)
	{
		csUnitMtDao.updateCsUnitMt$NotNull(csUnitMt);
	}
	
	/**
	 * 根据ID删除一个推送消息
	 * @param id
	 */
	public void deleteCsUnitMtById(Long id)
	{
		csUnitMtDao.deleteCsUnitMtById(id);
	}
	/**
	 * 根据ID逻辑删除一个推送消息
	 * @param id
	 */
	public void removeCsUnitMtById(Long id)
	{
		csUnitMtDao.removeCsUnitMtById(id);
	}
	
	/**
	 * 根据条件更新推送消息
	 * @param values
	 * @param params
	 */
	public void updateCsUnitMtByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsUnitMtBy时无条件");
		csUnitMtDao.updateCsUnitMtByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除推送消息
	 * @param params
	 */
	public void deleteCsUnitMtByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsUnitMtBy时无条件");
		csUnitMtDao.deleteCsUnitMtByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsUnitMtDao getCsUnitMtDao()
	{
		return csUnitMtDao;
	}

	public void setCsUnitMtDao(ICsUnitMtDao csUnitMtDao)
	{
		this.csUnitMtDao = csUnitMtDao;
	}
}