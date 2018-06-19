package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsChannel;

/**
 * 访问渠道的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsChannelService
{
	/**
	 * 获取所有访问渠道
	 * @return
	 */
	public List<CsChannel> getCsChannelList(Map params,Integer size);
	
	/**
	 * 获取访问渠道统计
	 * @return
	 */
	public List getCsChannelStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取访问渠道总数
	 * @return
	 */	
	public Long getCsChannelCount(Map params);
	
	
	/**
	 * 获取访问渠道自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsChannelEval(String eval,Map params);

	/**
	 * 获取访问渠道分页
	 * @return
	 */	
	public Page getCsChannelPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取访问渠道
	 * @param params
	 * @return
	 */
	public CsChannel getCsChannel(Map params);

	/**
	 * 根据ID取访问渠道
	 * @param id
	 * @return
	 */
	public CsChannel getCsChannelById(Long id);


	/**
	 * 保存访问渠道
	 * @param csChannel
	 */
	public CsChannel saveCsChannel(CsChannel csChannel);

	/**
	 * 更新访问渠道
	 * @param csChannel
	 */
	public void updateCsChannel(CsChannel csChannel);
	/**
	 * 更新访问渠道非空字段
	 * @param csChannel
	 */
	public void updateCsChannel$NotNull(CsChannel csChannel);

	/**
	 * 根据ID删除一个访问渠道
	 * @param id
	 */
	public void deleteCsChannelById(Long id);
	/**
	 * 根据ID逻辑删除一个访问渠道
	 * @param id
	 */
	public void removeCsChannelById(Long id);
	/**
	 * 根据条件更新访问渠道
	 * @param values
	 * @param params
	 */
	public void updateCsChannelByConfirm(Map values, Map params);
	/**
	 * 根据条件删除访问渠道
	 * @param params
	 */
	public void deleteCsChannelByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}