package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsMemberSummaryDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMemberSummary;
import com.ccclubs.service.admin.ICsMemberSummaryService;
import com.lazy3q.web.helper.$;

/**
 * 会员汇总项的Service实现
 * @author Joel
 */
public class CsMemberSummaryService implements ICsMemberSummaryService
{
	ICsMemberSummaryDao csMemberSummaryDao;
	

	/**
	 * 获取所有会员汇总项
	 * @return
	 */
	public List<CsMemberSummary> getCsMemberSummaryList(Map params,Integer size)
	{
		return csMemberSummaryDao.getCsMemberSummaryList(params,size);
	}
	
	/**
	 * 获取会员汇总项统计
	 * @return
	 */
	public List getCsMemberSummaryStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csMemberSummaryDao.getCsMemberSummaryStats(params,groups,sums);
	}
	
	/**
	 * 获取会员汇总项总数
	 * @return
	 */
	public Long getCsMemberSummaryCount(Map params)
	{
		return csMemberSummaryDao.getCsMemberSummaryCount(params);
	}
	
	/**
	 * 获取会员汇总项自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMemberSummaryEval(String eval,Map params)
	{
		return csMemberSummaryDao.getCsMemberSummaryEval(eval,params);
	}

	/**
	 * 获取会员汇总项分页
	 * @return
	 */
	public Page getCsMemberSummaryPage(int page,int size,Map params)
	{
		return csMemberSummaryDao.getCsMemberSummaryPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取会员汇总项
	 * @param params
	 * @return
	 */
	public CsMemberSummary getCsMemberSummary(Map params)
	{
		return csMemberSummaryDao.getCsMemberSummary(params);
	}

	/**
	 * 根据ID取会员汇总项
	 * @param id
	 * @return
	 */
	public CsMemberSummary getCsMemberSummaryById(Long id)
	{
		return csMemberSummaryDao.getCsMemberSummaryById(id);
	}

	/**
	 * 保存会员汇总项
	 * @param csMemberSummary
	 */
	public CsMemberSummary saveCsMemberSummary(CsMemberSummary csMemberSummary)
	{
		return csMemberSummaryDao.saveCsMemberSummary(csMemberSummary);
	}
	
	/**
	 * 更新会员汇总项
	 * @param csMemberSummary
	 */
	public void updateCsMemberSummary(CsMemberSummary csMemberSummary)
	{
		csMemberSummaryDao.updateCsMemberSummary(csMemberSummary);
	}
	/**
	 * 更新会员汇总项非空字段
	 * @param csMemberSummary
	 */
	public void updateCsMemberSummary$NotNull(CsMemberSummary csMemberSummary)
	{
		csMemberSummaryDao.updateCsMemberSummary$NotNull(csMemberSummary);
	}
	
	/**
	 * 根据ID删除一个会员汇总项
	 * @param id
	 */
	public void deleteCsMemberSummaryById(Long id)
	{
		csMemberSummaryDao.deleteCsMemberSummaryById(id);
	}
	
	/**
	 * 根据ID逻辑删除一个会员汇总项
	 * @param id
	 */
	public void removeCsMemberSummaryById(Long id)
	{
		csMemberSummaryDao.removeCsMemberSummaryById(id);
	}
	
	/**
	 * 根据条件更新会员汇总项
	 * @param values
	 * @param params
	 */
	public void updateCsMemberSummaryByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsMemberSummaryBy时无条件");
		csMemberSummaryDao.updateCsMemberSummaryByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除会员汇总项
	 * @param params
	 */
	public void deleteCsMemberSummaryByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsMemberSummaryBy时无条件");
		csMemberSummaryDao.deleteCsMemberSummaryByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsMemberSummaryDao getCsMemberSummaryDao()
	{
		return csMemberSummaryDao;
	}

	public void setCsMemberSummaryDao(ICsMemberSummaryDao csMemberSummaryDao)
	{
		this.csMemberSummaryDao = csMemberSummaryDao;
	}
}