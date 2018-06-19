package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsChannelIndexDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsChannelIndex;
import com.ccclubs.service.admin.ICsChannelIndexService;
import com.lazy3q.web.helper.$;

/**
 * 渠道指标的Service实现
 * @author 飞啊飘啊
 */
public class CsChannelIndexService implements ICsChannelIndexService
{
	ICsChannelIndexDao csChannelIndexDao;
	

	/**
	 * 获取所有渠道指标
	 * @return
	 */
	public List<CsChannelIndex> getCsChannelIndexList(Map params,Integer size)
	{
		return csChannelIndexDao.getCsChannelIndexList(params,size);
	}
	
	/**
	 * 获取渠道指标统计
	 * @return
	 */
	public List getCsChannelIndexStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csChannelIndexDao.getCsChannelIndexStats(params,groups,sums);
	}
	
	/**
	 * 获取渠道指标总数
	 * @return
	 */
	public Long getCsChannelIndexCount(Map params)
	{
		return csChannelIndexDao.getCsChannelIndexCount(params);
	}
	
	/**
	 * 获取渠道指标自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsChannelIndexEval(String eval,Map params)
	{
		return csChannelIndexDao.getCsChannelIndexEval(eval,params);
	}

	/**
	 * 获取渠道指标分页
	 * @return
	 */
	public Page getCsChannelIndexPage(int page,int size,Map params)
	{
		return csChannelIndexDao.getCsChannelIndexPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取渠道指标
	 * @param params
	 * @return
	 */
	public CsChannelIndex getCsChannelIndex(Map params)
	{
		return csChannelIndexDao.getCsChannelIndex(params);
	}

	/**
	 * 根据ID取渠道指标
	 * @param id
	 * @return
	 */
	public CsChannelIndex getCsChannelIndexById(Long id)
	{
		return csChannelIndexDao.getCsChannelIndexById(id);
	}

	/**
	 * 保存渠道指标
	 * @param csChannelIndex
	 */
	public CsChannelIndex saveCsChannelIndex(CsChannelIndex csChannelIndex)
	{
		return csChannelIndexDao.saveCsChannelIndex(csChannelIndex);
	}
	
	/**
	 * 更新渠道指标
	 * @param csChannelIndex
	 */
	public void updateCsChannelIndex(CsChannelIndex csChannelIndex)
	{
		csChannelIndexDao.updateCsChannelIndex(csChannelIndex);
	}
	/**
	 * 更新渠道指标非空字段
	 * @param csChannelIndex
	 */
	public void updateCsChannelIndex$NotNull(CsChannelIndex csChannelIndex)
	{
		csChannelIndexDao.updateCsChannelIndex$NotNull(csChannelIndex);
	}
	
	/**
	 * 根据ID删除一个渠道指标
	 * @param id
	 */
	public void deleteCsChannelIndexById(Long id)
	{
		csChannelIndexDao.deleteCsChannelIndexById(id);
	}
	/**
	 * 根据ID逻辑删除一个渠道指标
	 * @param id
	 */
	public void removeCsChannelIndexById(Long id)
	{
		csChannelIndexDao.removeCsChannelIndexById(id);
	}
	
	/**
	 * 根据条件更新渠道指标
	 * @param values
	 * @param params
	 */
	public void updateCsChannelIndexByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsChannelIndexBy时无条件");
		csChannelIndexDao.updateCsChannelIndexByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除渠道指标
	 * @param params
	 */
	public void deleteCsChannelIndexByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsChannelIndexBy时无条件");
		csChannelIndexDao.deleteCsChannelIndexByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsChannelIndexDao getCsChannelIndexDao()
	{
		return csChannelIndexDao;
	}

	public void setCsChannelIndexDao(ICsChannelIndexDao csChannelIndexDao)
	{
		this.csChannelIndexDao = csChannelIndexDao;
	}
}