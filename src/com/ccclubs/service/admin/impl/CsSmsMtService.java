package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsSmsMtDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsSmsMt;
import com.ccclubs.service.admin.ICsSmsMtService;
import com.lazy3q.web.helper.$;

/**
 * 短信发送的Service实现
 * @author 飞啊飘啊
 */
public class CsSmsMtService implements ICsSmsMtService
{
	ICsSmsMtDao csSmsMtDao;
	

	/**
	 * 获取所有短信发送
	 * @return
	 */
	public List<CsSmsMt> getCsSmsMtList(Map params,Integer size)
	{
		return csSmsMtDao.getCsSmsMtList(params,size);
	}
	
	/**
	 * 获取短信发送统计
	 * @return
	 */
	public List getCsSmsMtStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csSmsMtDao.getCsSmsMtStats(params,groups,sums);
	}
	
	/**
	 * 获取短信发送总数
	 * @return
	 */
	public Long getCsSmsMtCount(Map params)
	{
		return csSmsMtDao.getCsSmsMtCount(params);
	}
	
	/**
	 * 获取短信发送自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsSmsMtEval(String eval,Map params)
	{
		return csSmsMtDao.getCsSmsMtEval(eval,params);
	}

	/**
	 * 获取短信发送分页
	 * @return
	 */
	public Page getCsSmsMtPage(int page,int size,Map params)
	{
		return csSmsMtDao.getCsSmsMtPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取短信发送
	 * @param params
	 * @return
	 */
	public CsSmsMt getCsSmsMt(Map params)
	{
		return csSmsMtDao.getCsSmsMt(params);
	}

	/**
	 * 根据ID取短信发送
	 * @param id
	 * @return
	 */
	public CsSmsMt getCsSmsMtById(Long id)
	{
		return csSmsMtDao.getCsSmsMtById(id);
	}

	/**
	 * 保存短信发送
	 * @param csSmsMt
	 */
	public CsSmsMt saveCsSmsMt(CsSmsMt csSmsMt)
	{
		return csSmsMtDao.saveCsSmsMt(csSmsMt);
	}
	
	/**
	 * 更新短信发送
	 * @param csSmsMt
	 */
	public void updateCsSmsMt(CsSmsMt csSmsMt)
	{
		csSmsMtDao.updateCsSmsMt(csSmsMt);
	}
	/**
	 * 更新短信发送非空字段
	 * @param csSmsMt
	 */
	public void updateCsSmsMt$NotNull(CsSmsMt csSmsMt)
	{
		csSmsMtDao.updateCsSmsMt$NotNull(csSmsMt);
	}
	
	/**
	 * 根据ID删除一个短信发送
	 * @param id
	 */
	public void deleteCsSmsMtById(Long id)
	{
		csSmsMtDao.deleteCsSmsMtById(id);
	}
	/**
	 * 根据ID逻辑删除一个短信发送
	 * @param id
	 */
	public void removeCsSmsMtById(Long id)
	{
		csSmsMtDao.removeCsSmsMtById(id);
	}
	
	/**
	 * 根据条件更新短信发送
	 * @param values
	 * @param params
	 */
	public void updateCsSmsMtByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsSmsMtBy时无条件");
		csSmsMtDao.updateCsSmsMtByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除短信发送
	 * @param params
	 */
	public void deleteCsSmsMtByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsSmsMtBy时无条件");
		csSmsMtDao.deleteCsSmsMtByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsSmsMtDao getCsSmsMtDao()
	{
		return csSmsMtDao;
	}

	public void setCsSmsMtDao(ICsSmsMtDao csSmsMtDao)
	{
		this.csSmsMtDao = csSmsMtDao;
	}
}