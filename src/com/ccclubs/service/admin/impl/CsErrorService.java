package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsErrorDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsError;
import com.ccclubs.service.admin.ICsErrorService;
import com.lazy3q.web.helper.$;

/**
 * 错误信息的Service实现
 * @author 飞啊飘啊
 */
public class CsErrorService implements ICsErrorService
{
	ICsErrorDao csErrorDao;
	

	/**
	 * 获取所有错误信息
	 * @return
	 */
	public List<CsError> getCsErrorList(Map params,Integer size)
	{
		return csErrorDao.getCsErrorList(params,size);
	}
	
	/**
	 * 获取错误信息统计
	 * @return
	 */
	public List getCsErrorStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csErrorDao.getCsErrorStats(params,groups,sums);
	}
	
	/**
	 * 获取错误信息总数
	 * @return
	 */
	public Long getCsErrorCount(Map params)
	{
		return csErrorDao.getCsErrorCount(params);
	}
	
	/**
	 * 获取错误信息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsErrorEval(String eval,Map params)
	{
		return csErrorDao.getCsErrorEval(eval,params);
	}

	/**
	 * 获取错误信息分页
	 * @return
	 */
	public Page getCsErrorPage(int page,int size,Map params)
	{
		return csErrorDao.getCsErrorPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取错误信息
	 * @param params
	 * @return
	 */
	public CsError getCsError(Map params)
	{
		return csErrorDao.getCsError(params);
	}

	/**
	 * 根据ID取错误信息
	 * @param id
	 * @return
	 */
	public CsError getCsErrorById(Long id)
	{
		return csErrorDao.getCsErrorById(id);
	}

	/**
	 * 保存错误信息
	 * @param csError
	 */
	public CsError saveCsError(CsError csError)
	{
		return csErrorDao.saveCsError(csError);
	}
	
	/**
	 * 更新错误信息
	 * @param csError
	 */
	public void updateCsError(CsError csError)
	{
		csErrorDao.updateCsError(csError);
	}
	/**
	 * 更新错误信息非空字段
	 * @param csError
	 */
	public void updateCsError$NotNull(CsError csError)
	{
		csErrorDao.updateCsError$NotNull(csError);
	}
	
	/**
	 * 根据ID删除一个错误信息
	 * @param id
	 */
	public void deleteCsErrorById(Long id)
	{
		csErrorDao.deleteCsErrorById(id);
	}
	
	/**
	 * 根据条件更新错误信息
	 * @param values
	 * @param params
	 */
	public void updateCsErrorByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsErrorBy时无条件");
		csErrorDao.updateCsErrorByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除错误信息
	 * @param params
	 */
	public void deleteCsErrorByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsErrorBy时无条件");
		csErrorDao.deleteCsErrorByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsErrorDao getCsErrorDao()
	{
		return csErrorDao;
	}

	public void setCsErrorDao(ICsErrorDao csErrorDao)
	{
		this.csErrorDao = csErrorDao;
	}
}