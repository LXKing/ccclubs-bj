package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsSaleEvent;

/**
 * 销售活动的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsSaleEventService
{
	/**
	 * 获取所有销售活动
	 * @return
	 */
	public List<CsSaleEvent> getCsSaleEventList(Map params,Integer size);
	
	/**
	 * 获取销售活动统计
	 * @return
	 */
	public List getCsSaleEventStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取销售活动总数
	 * @return
	 */	
	public Long getCsSaleEventCount(Map params);
	
	
	/**
	 * 获取销售活动自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsSaleEventEval(String eval,Map params);

	/**
	 * 获取销售活动分页
	 * @return
	 */	
	public Page getCsSaleEventPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取销售活动
	 * @param params
	 * @return
	 */
	public CsSaleEvent getCsSaleEvent(Map params);

	/**
	 * 根据ID取销售活动
	 * @param id
	 * @return
	 */
	public CsSaleEvent getCsSaleEventById(Long id);


	/**
	 * 保存销售活动
	 * @param csSaleEvent
	 */
	public CsSaleEvent saveCsSaleEvent(CsSaleEvent csSaleEvent);

	/**
	 * 更新销售活动
	 * @param csSaleEvent
	 */
	public void updateCsSaleEvent(CsSaleEvent csSaleEvent);
	/**
	 * 更新销售活动非空字段
	 * @param csSaleEvent
	 */
	public void updateCsSaleEvent$NotNull(CsSaleEvent csSaleEvent);

	/**
	 * 根据ID删除一个销售活动
	 * @param id
	 */
	public void deleteCsSaleEventById(Long id);
	/**
	 * 根据ID逻辑删除一个销售活动
	 * @param id
	 */
	public void removeCsSaleEventById(Long id);
	/**
	 * 根据条件更新销售活动
	 * @param values
	 * @param params
	 */
	public void updateCsSaleEventByConfirm(Map values, Map params);
	/**
	 * 根据条件删除销售活动
	 * @param params
	 */
	public void deleteCsSaleEventByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}