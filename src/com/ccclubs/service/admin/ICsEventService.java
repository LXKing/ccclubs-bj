package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsEvent;

/**
 * 系统事件的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsEventService
{
	/**
	 * 获取所有系统事件
	 * @return
	 */
	public List<CsEvent> getCsEventList(Map params,Integer size);
	
	/**
	 * 获取系统事件统计
	 * @return
	 */
	public List getCsEventStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取系统事件总数
	 * @return
	 */	
	public Long getCsEventCount(Map params);
	
	
	/**
	 * 获取系统事件自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsEventEval(String eval,Map params);

	/**
	 * 获取系统事件分页
	 * @return
	 */	
	public Page getCsEventPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取系统事件
	 * @param params
	 * @return
	 */
	public CsEvent getCsEvent(Map params);

	/**
	 * 根据ID取系统事件
	 * @param id
	 * @return
	 */
	public CsEvent getCsEventById(Long id);


	/**
	 * 保存系统事件
	 * @param csEvent
	 */
	public CsEvent saveCsEvent(CsEvent csEvent);

	/**
	 * 更新系统事件
	 * @param csEvent
	 */
	public void updateCsEvent(CsEvent csEvent);
	/**
	 * 更新系统事件非空字段
	 * @param csEvent
	 */
	public void updateCsEvent$NotNull(CsEvent csEvent);

	/**
	 * 根据ID删除一个系统事件
	 * @param id
	 */
	public void deleteCsEventById(Long id);
	/**
	 * 根据ID逻辑删除一个系统事件
	 * @param id
	 */
	public void removeCsEventById(Long id);
	/**
	 * 根据条件更新系统事件
	 * @param values
	 * @param params
	 */
	public void updateCsEventByConfirm(Map values, Map params);
	/**
	 * 根据条件删除系统事件
	 * @param params
	 */
	public void deleteCsEventByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}