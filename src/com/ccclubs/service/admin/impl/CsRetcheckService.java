package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;

import com.ccclubs.dao.ICsRetcheckDao;
import com.ccclubs.model.CsRetcheck;
import com.ccclubs.service.admin.ICsRetcheckService;
import com.lazy3q.util.Function;
import com.lazy3q.web.util.Page;

/**
 * 还车检查的Service实现
 * @author Joel
 */
public class CsRetcheckService implements ICsRetcheckService
{
	ICsRetcheckDao csRetcheckDao;
	

	/**
	 * 获取所有还车检查
	 * @return
	 */
	public List<CsRetcheck> getCsRetcheckList(Map params,Integer size)
	{
		return csRetcheckDao.getCsRetcheckList(params,size);
	}
	
	/**
	 * 获取还车检查统计
	 * @return
	 */
	public List getCsRetcheckStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csRetcheckDao.getCsRetcheckStats(params,groups,sums);
	}
	
	/**
	 * 获取还车检查总数
	 * @return
	 */
	public Long getCsRetcheckCount(Map params)
	{
		return csRetcheckDao.getCsRetcheckCount(params);
	}
	
	/**
	 * 获取还车检查自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsRetcheckEval(String eval,Map params)
	{
		return csRetcheckDao.getCsRetcheckEval(eval,params);
	}

	/**
	 * 获取还车检查分页
	 * @return
	 */
	public Page getCsRetcheckPage(int page,int size,Map params)
	{
		return csRetcheckDao.getCsRetcheckPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取还车检查
	 * @param params
	 * @return
	 */
	public CsRetcheck getCsRetcheck(Map params)
	{
		return csRetcheckDao.getCsRetcheck(params);
	}

	/**
	 * 根据ID取还车检查
	 * @param id
	 * @return
	 */
	public CsRetcheck getCsRetcheckById(Long id)
	{
		return csRetcheckDao.getCsRetcheckById(id);
	}

	/**
	 * 保存还车检查
	 * @param csRetcheck
	 */
	public CsRetcheck saveCsRetcheck(CsRetcheck csRetcheck)
	{
		return csRetcheckDao.saveCsRetcheck(csRetcheck);
	}
	
	/**
	 * 更新还车检查
	 * @param csRetcheck
	 */
	public void updateCsRetcheck(CsRetcheck csRetcheck)
	{
		csRetcheckDao.updateCsRetcheck(csRetcheck);
	}
	/**
	 * 更新还车检查非空字段
	 * @param csRetcheck
	 */
	public void updateCsRetcheck$NotNull(CsRetcheck csRetcheck)
	{
		csRetcheckDao.updateCsRetcheck$NotNull(csRetcheck);
	}
	
	/**
	 * 根据ID删除一个还车检查
	 * @param id
	 */
	public void deleteCsRetcheckById(Long id)
	{
		csRetcheckDao.deleteCsRetcheckById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个还车检查
	 * @param id
	 */
	public void removeCsRetcheckById(Long id)
	{
		csRetcheckDao.removeCsRetcheckById(id);
	}
	
	/**
	 * 根据条件更新还车检查
	 * @param values
	 * @param params
	 */
	public void updateCsRetcheckByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsRetcheckBy时无条件");
		csRetcheckDao.updateCsRetcheckByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除还车检查
	 * @param params
	 */
	public void deleteCsRetcheckByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsRetcheckBy时无条件");
		csRetcheckDao.deleteCsRetcheckByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsRetcheckDao getCsRetcheckDao()
	{
		return csRetcheckDao;
	}

	public void setCsRetcheckDao(ICsRetcheckDao csRetcheckDao)
	{
		this.csRetcheckDao = csRetcheckDao;
	}
}