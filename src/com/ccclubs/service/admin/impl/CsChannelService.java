package com.ccclubs.service.admin.impl;

import java.util.List;
import java.util.Map;
import com.lazy3q.util.Function;

import com.ccclubs.dao.ICsChannelDao;
import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsChannel;
import com.ccclubs.service.admin.ICsChannelService;
import com.lazy3q.web.helper.$;

/**
 * 访问渠道的Service实现
 * @author 飞啊飘啊
 */
public class CsChannelService implements ICsChannelService
{
	ICsChannelDao csChannelDao;
	

	/**
	 * 获取所有访问渠道
	 * @return
	 */
	public List<CsChannel> getCsChannelList(Map params,Integer size)
	{
		return csChannelDao.getCsChannelList(params,size);
	}
	
	/**
	 * 获取访问渠道统计
	 * @return
	 */
	public List getCsChannelStats(Map params,Map<String,Object> groups,Map<String,Object> sums)
	{
		return csChannelDao.getCsChannelStats(params,groups,sums);
	}
	
	/**
	 * 获取访问渠道总数
	 * @return
	 */
	public Long getCsChannelCount(Map params)
	{
		return csChannelDao.getCsChannelCount(params);
	}
	
	/**
	 * 获取访问渠道自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsChannelEval(String eval,Map params)
	{
		return csChannelDao.getCsChannelEval(eval,params);
	}

	/**
	 * 获取访问渠道分页
	 * @return
	 */
	public Page getCsChannelPage(int page,int size,Map params)
	{
		return csChannelDao.getCsChannelPage(page,size,params);
	}
	
	/**
	 * 根据查询条件取访问渠道
	 * @param params
	 * @return
	 */
	public CsChannel getCsChannel(Map params)
	{
		return csChannelDao.getCsChannel(params);
	}

	/**
	 * 根据ID取访问渠道
	 * @param id
	 * @return
	 */
	public CsChannel getCsChannelById(Long id)
	{
		return csChannelDao.getCsChannelById(id);
	}

	/**
	 * 保存访问渠道
	 * @param csChannel
	 */
	public CsChannel saveCsChannel(CsChannel csChannel)
	{
		return csChannelDao.saveCsChannel(csChannel);
	}
	
	/**
	 * 更新访问渠道
	 * @param csChannel
	 */
	public void updateCsChannel(CsChannel csChannel)
	{
		csChannelDao.updateCsChannel(csChannel);
	}
	/**
	 * 更新访问渠道非空字段
	 * @param csChannel
	 */
	public void updateCsChannel$NotNull(CsChannel csChannel)
	{
		csChannelDao.updateCsChannel$NotNull(csChannel);
	}
	
	/**
	 * 根据ID删除一个访问渠道
	 * @param id
	 */
	public void deleteCsChannelById(Long id)
	{
		csChannelDao.deleteCsChannelById(id);
	}
	/**
	 * 根据ID逻辑删除一个访问渠道
	 * @param id
	 */
	public void removeCsChannelById(Long id)
	{
		csChannelDao.removeCsChannelById(id);
	}
	
	/**
	 * 根据条件更新访问渠道
	 * @param values
	 * @param params
	 */
	public void updateCsChannelByConfirm(Map values, Map params){
		if(params.isEmpty())
			throw new RuntimeException("updateCsChannelBy时无条件");
		csChannelDao.updateCsChannelByConfirm(values,params);
	}
	
	/**
	 * 根据条件删除访问渠道
	 * @param params
	 */
	public void deleteCsChannelByConfirm(Map params){
		if(params.isEmpty())
			throw new RuntimeException("deleteCsChannelBy时无条件");
		csChannelDao.deleteCsChannelByConfirm(params);
	}

	/**
	 * 事务处理
	 */
	public <T> T executeTransaction(Function function) {
		return function.execute();	
	}

	public ICsChannelDao getCsChannelDao()
	{
		return csChannelDao;
	}

	public void setCsChannelDao(ICsChannelDao csChannelDao)
	{
		this.csChannelDao = csChannelDao;
	}
}