package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsUnitGroup;

/**
 * 分组信息的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsUnitGroupService
{
	/**
	 * 获取所有分组信息
	 * @return
	 */
	public List<CsUnitGroup> getCsUnitGroupList(Map params,Integer size);
	
	/**
	 * 获取分组信息统计
	 * @return
	 */
	public List getCsUnitGroupStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取分组信息总数
	 * @return
	 */	
	public Long getCsUnitGroupCount(Map params);
	
	
	/**
	 * 获取分组信息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUnitGroupEval(String eval,Map params);

	/**
	 * 获取分组信息分页
	 * @return
	 */	
	public Page getCsUnitGroupPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取分组信息
	 * @param params
	 * @return
	 */
	public CsUnitGroup getCsUnitGroup(Map params);

	/**
	 * 根据ID取分组信息
	 * @param id
	 * @return
	 */
	public CsUnitGroup getCsUnitGroupById(Long id);


	/**
	 * 保存分组信息
	 * @param csUnitGroup
	 */
	public CsUnitGroup saveCsUnitGroup(CsUnitGroup csUnitGroup);

	/**
	 * 更新分组信息
	 * @param csUnitGroup
	 */
	public void updateCsUnitGroup(CsUnitGroup csUnitGroup);
	/**
	 * 更新分组信息非空字段
	 * @param csUnitGroup
	 */
	public void updateCsUnitGroup$NotNull(CsUnitGroup csUnitGroup);

	/**
	 * 根据ID删除一个分组信息
	 * @param id
	 */
	public void deleteCsUnitGroupById(Long id);
	/**
	 * 根据ID逻辑删除一个分组信息
	 * @param id
	 */
	public void removeCsUnitGroupById(Long id);
	/**
	 * 根据条件更新分组信息
	 * @param values
	 * @param params
	 */
	public void updateCsUnitGroupByConfirm(Map values, Map params);
	/**
	 * 根据条件删除分组信息
	 * @param params
	 */
	public void deleteCsUnitGroupByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}