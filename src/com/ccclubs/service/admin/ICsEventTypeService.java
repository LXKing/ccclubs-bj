package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsEventType;

/**
 * 系统事件类型的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsEventTypeService
{
	/**
	 * 获取所有系统事件类型
	 * @return
	 */
	public List<CsEventType> getCsEventTypeList(Map params,Integer size);
	
	/**
	 * 获取系统事件类型统计
	 * @return
	 */
	public List getCsEventTypeStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取系统事件类型总数
	 * @return
	 */	
	public Long getCsEventTypeCount(Map params);
	
	
	/**
	 * 获取系统事件类型自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsEventTypeEval(String eval,Map params);

	/**
	 * 获取系统事件类型分页
	 * @return
	 */	
	public Page getCsEventTypePage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取系统事件类型
	 * @param params
	 * @return
	 */
	public CsEventType getCsEventType(Map params);

	/**
	 * 根据ID取系统事件类型
	 * @param id
	 * @return
	 */
	public CsEventType getCsEventTypeById(Long id);


	/**
	 * 保存系统事件类型
	 * @param csEventType
	 */
	public CsEventType saveCsEventType(CsEventType csEventType);

	/**
	 * 更新系统事件类型
	 * @param csEventType
	 */
	public void updateCsEventType(CsEventType csEventType);
	/**
	 * 更新系统事件类型非空字段
	 * @param csEventType
	 */
	public void updateCsEventType$NotNull(CsEventType csEventType);

	/**
	 * 根据ID删除一个系统事件类型
	 * @param id
	 */
	public void deleteCsEventTypeById(Long id);
	/**
	 * 根据ID逻辑删除一个系统事件类型
	 * @param id
	 */
	public void removeCsEventTypeById(Long id);
	/**
	 * 根据条件更新系统事件类型
	 * @param values
	 * @param params
	 */
	public void updateCsEventTypeByConfirm(Map values, Map params);
	/**
	 * 根据条件删除系统事件类型
	 * @param params
	 */
	public void deleteCsEventTypeByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}