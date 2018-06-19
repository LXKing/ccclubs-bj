package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsChannelIndex;

/**
 * 渠道指标的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsChannelIndexService
{
	/**
	 * 获取所有渠道指标
	 * @return
	 */
	public List<CsChannelIndex> getCsChannelIndexList(Map params,Integer size);
	
	/**
	 * 获取渠道指标统计
	 * @return
	 */
	public List getCsChannelIndexStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取渠道指标总数
	 * @return
	 */	
	public Long getCsChannelIndexCount(Map params);
	
	
	/**
	 * 获取渠道指标自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsChannelIndexEval(String eval,Map params);

	/**
	 * 获取渠道指标分页
	 * @return
	 */	
	public Page getCsChannelIndexPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取渠道指标
	 * @param params
	 * @return
	 */
	public CsChannelIndex getCsChannelIndex(Map params);

	/**
	 * 根据ID取渠道指标
	 * @param id
	 * @return
	 */
	public CsChannelIndex getCsChannelIndexById(Long id);


	/**
	 * 保存渠道指标
	 * @param csChannelIndex
	 */
	public CsChannelIndex saveCsChannelIndex(CsChannelIndex csChannelIndex);

	/**
	 * 更新渠道指标
	 * @param csChannelIndex
	 */
	public void updateCsChannelIndex(CsChannelIndex csChannelIndex);
	/**
	 * 更新渠道指标非空字段
	 * @param csChannelIndex
	 */
	public void updateCsChannelIndex$NotNull(CsChannelIndex csChannelIndex);

	/**
	 * 根据ID删除一个渠道指标
	 * @param id
	 */
	public void deleteCsChannelIndexById(Long id);
	/**
	 * 根据ID逻辑删除一个渠道指标
	 * @param id
	 */
	public void removeCsChannelIndexById(Long id);
	/**
	 * 根据条件更新渠道指标
	 * @param values
	 * @param params
	 */
	public void updateCsChannelIndexByConfirm(Map values, Map params);
	/**
	 * 根据条件删除渠道指标
	 * @param params
	 */
	public void deleteCsChannelIndexByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}