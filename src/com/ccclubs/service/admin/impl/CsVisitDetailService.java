package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsVisitDetailDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsVisitDetail;
import com.ccclubs.service.admin.ICsVisitDetailService;
import com.lazy3q.web.helper.$;

/**
 * 访问详情的Service实现
 * @author 飞啊飘啊
 */
public class CsVisitDetailService implements ICsVisitDetailService
{
	ICsVisitDetailDao csVisitDetailDao;
	

	/**
	 * 获取所有访问详情
	 * @return
	 */
	public List<CsVisitDetail> getCsVisitDetailList(Map params,Integer size)
	{
		return csVisitDetailDao.getCsVisitDetailList(params,size);
	}
	
	/**
	 * 获取访问详情统计
	 * @return
	 */
	public List getCsVisitDetailStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csVisitDetailDao.getCsVisitDetailStats(params,groups,sums);
	}
	
	/**
	 * 获取访问详情总数
	 * @return
	 */
	public Long getCsVisitDetailCount(Map params)
	{
		return csVisitDetailDao.getCsVisitDetailCount(params);
	}
	
	/**
	 * 获取访问详情自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsVisitDetailEval(String eval,Map params)
	{
		return csVisitDetailDao.getCsVisitDetailEval(eval,params);
	}

	/**
	 * 获取访问详情分页
	 * @return
	 */
	public Page getCsVisitDetailPage(int page,int size,Map params)
	{
		return csVisitDetailDao.getCsVisitDetailPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取访问详情
	 * @param params
	 * @return
	 */
	public CsVisitDetail getCsVisitDetail(Map params)
	{
		return csVisitDetailDao.getCsVisitDetail(params);
	}

	/**
	 * 根据ID取访问详情
	 * @param id
	 * @return
	 */
	public CsVisitDetail getCsVisitDetailById(Long id)
	{
		return csVisitDetailDao.getCsVisitDetailById(id);
	}

	/**
	 * 保存访问详情
	 * @param csVisitDetail
	 */
	public CsVisitDetail saveCsVisitDetail(CsVisitDetail csVisitDetail)
	{
		return csVisitDetailDao.saveCsVisitDetail(csVisitDetail);
	}
	
	/**
	 * 更新访问详情
	 * @param csVisitDetail
	 */
	public void updateCsVisitDetail(CsVisitDetail csVisitDetail)
	{
		csVisitDetailDao.updateCsVisitDetail(csVisitDetail);
	}
	/**
	 * 更新访问详情非空字段
	 * @param csVisitDetail
	 */
	public void updateCsVisitDetail$NotNull(CsVisitDetail csVisitDetail)
	{
		csVisitDetailDao.updateCsVisitDetail$NotNull(csVisitDetail);
	}
	
	/**
	 * 根据ID删除一个访问详情
	 * @param id
	 */
	public void deleteCsVisitDetailById(Long id)
	{
		csVisitDetailDao.deleteCsVisitDetailById(id);
	}
	/**
	 * 根据ID逻辑删除一个访问详情
	 * @param id
	 */
	public void removeCsVisitDetailById(Long id)
	{
		csVisitDetailDao.removeCsVisitDetailById(id);
	}
	
	/**
	 * 根据条件更新访问详情
	 * @param values
	 * @param params
	 */
	public void updateCsVisitDetailByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsVisitDetailBy时无条件");
		csVisitDetailDao.updateCsVisitDetailByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除访问详情
	 * @param params
	 */
	public void deleteCsVisitDetailByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsVisitDetailBy时无条件");
		csVisitDetailDao.deleteCsVisitDetailByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsVisitDetailDao getCsVisitDetailDao()
	{
		return csVisitDetailDao;
	}

	public void setCsVisitDetailDao(ICsVisitDetailDao csVisitDetailDao)
	{
		this.csVisitDetailDao = csVisitDetailDao;
	}
}