package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsArgumentDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsArgument;
import com.ccclubs.service.admin.ICsArgumentService;
import com.lazy3q.web.helper.$;

/**
 * 系统参数的Service实现
 * @author 飞啊飘啊
 */
public class CsArgumentService implements ICsArgumentService
{
	ICsArgumentDao csArgumentDao;
	

	/**
	 * 获取所有系统参数
	 * @return
	 */
	public List<CsArgument> getCsArgumentList(Map params,Integer size)
	{
		return csArgumentDao.getCsArgumentList(params,size);
	}
	
	/**
	 * 获取系统参数统计
	 * @return
	 */
	public List getCsArgumentStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csArgumentDao.getCsArgumentStats(params,groups,sums);
	}
	
	/**
	 * 获取系统参数总数
	 * @return
	 */
	public Long getCsArgumentCount(Map params)
	{
		return csArgumentDao.getCsArgumentCount(params);
	}
	
	/**
	 * 获取系统参数自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsArgumentEval(String eval,Map params)
	{
		return csArgumentDao.getCsArgumentEval(eval,params);
	}

	/**
	 * 获取系统参数分页
	 * @return
	 */
	public Page getCsArgumentPage(int page,int size,Map params)
	{
		return csArgumentDao.getCsArgumentPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取系统参数
	 * @param params
	 * @return
	 */
	public CsArgument getCsArgument(Map params)
	{
		return csArgumentDao.getCsArgument(params);
	}

	/**
	 * 根据ID取系统参数
	 * @param id
	 * @return
	 */
	public CsArgument getCsArgumentById(Long id)
	{
		return csArgumentDao.getCsArgumentById(id);
	}

	/**
	 * 保存系统参数
	 * @param csArgument
	 */
	public CsArgument saveCsArgument(CsArgument csArgument)
	{
		return csArgumentDao.saveCsArgument(csArgument);
	}
	
	/**
	 * 更新系统参数
	 * @param csArgument
	 */
	public void updateCsArgument(CsArgument csArgument)
	{
		csArgumentDao.updateCsArgument(csArgument);
	}
	/**
	 * 更新系统参数非空字段
	 * @param csArgument
	 */
	public void updateCsArgument$NotNull(CsArgument csArgument)
	{
		csArgumentDao.updateCsArgument$NotNull(csArgument);
	}
	
	/**
	 * 根据ID删除一个系统参数
	 * @param id
	 */
	public void deleteCsArgumentById(Long id)
	{
		csArgumentDao.deleteCsArgumentById(id);
	}
	
	/**
	 * 根据条件更新系统参数
	 * @param values
	 * @param params
	 */
	public void updateCsArgumentByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsArgumentBy时无条件");
		csArgumentDao.updateCsArgumentByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除系统参数
	 * @param params
	 */
	public void deleteCsArgumentByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsArgumentBy时无条件");
		csArgumentDao.deleteCsArgumentByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsArgumentDao getCsArgumentDao()
	{
		return csArgumentDao;
	}

	public void setCsArgumentDao(ICsArgumentDao csArgumentDao)
	{
		this.csArgumentDao = csArgumentDao;
	}
}