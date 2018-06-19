package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsWxMtDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsWxMt;
import com.ccclubs.service.admin.ICsWxMtService;
import com.lazy3q.web.helper.$;

/**
 * 微信下行的Service实现
 * @author 飞啊飘啊
 */
public class CsWxMtService implements ICsWxMtService
{
	ICsWxMtDao csWxMtDao;
	

	/**
	 * 获取所有微信下行
	 * @return
	 */
	public List<CsWxMt> getCsWxMtList(Map params,Integer size)
	{
		return csWxMtDao.getCsWxMtList(params,size);
	}
	
	/**
	 * 获取微信下行统计
	 * @return
	 */
	public List getCsWxMtStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csWxMtDao.getCsWxMtStats(params,groups,sums);
	}
	
	/**
	 * 获取微信下行总数
	 * @return
	 */
	public Long getCsWxMtCount(Map params)
	{
		return csWxMtDao.getCsWxMtCount(params);
	}
	
	/**
	 * 获取微信下行自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsWxMtEval(String eval,Map params)
	{
		return csWxMtDao.getCsWxMtEval(eval,params);
	}

	/**
	 * 获取微信下行分页
	 * @return
	 */
	public Page getCsWxMtPage(int page,int size,Map params)
	{
		return csWxMtDao.getCsWxMtPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取微信下行
	 * @param params
	 * @return
	 */
	public CsWxMt getCsWxMt(Map params)
	{
		return csWxMtDao.getCsWxMt(params);
	}

	/**
	 * 根据ID取微信下行
	 * @param id
	 * @return
	 */
	public CsWxMt getCsWxMtById(Long id)
	{
		return csWxMtDao.getCsWxMtById(id);
	}

	/**
	 * 保存微信下行
	 * @param csWxMt
	 */
	public CsWxMt saveCsWxMt(CsWxMt csWxMt)
	{
		return csWxMtDao.saveCsWxMt(csWxMt);
	}
	
	/**
	 * 更新微信下行
	 * @param csWxMt
	 */
	public void updateCsWxMt(CsWxMt csWxMt)
	{
		csWxMtDao.updateCsWxMt(csWxMt);
	}
	/**
	 * 更新微信下行非空字段
	 * @param csWxMt
	 */
	public void updateCsWxMt$NotNull(CsWxMt csWxMt)
	{
		csWxMtDao.updateCsWxMt$NotNull(csWxMt);
	}
	
	/**
	 * 根据ID删除一个微信下行
	 * @param id
	 */
	public void deleteCsWxMtById(Long id)
	{
		csWxMtDao.deleteCsWxMtById(id);
	}
	/**
	 * 根据ID逻辑删除一个微信下行
	 * @param id
	 */
	public void removeCsWxMtById(Long id)
	{
		csWxMtDao.removeCsWxMtById(id);
	}
	
	/**
	 * 根据条件更新微信下行
	 * @param values
	 * @param params
	 */
	public void updateCsWxMtByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsWxMtBy时无条件");
		csWxMtDao.updateCsWxMtByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除微信下行
	 * @param params
	 */
	public void deleteCsWxMtByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsWxMtBy时无条件");
		csWxMtDao.deleteCsWxMtByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsWxMtDao getCsWxMtDao()
	{
		return csWxMtDao;
	}

	public void setCsWxMtDao(ICsWxMtDao csWxMtDao)
	{
		this.csWxMtDao = csWxMtDao;
	}
}