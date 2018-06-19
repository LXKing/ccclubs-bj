package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.TbCustomGroup;

/**
 * 客户组织的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ITbCustomGroupService
{
	/**
	 * 获取所有客户组织
	 * @return
	 */
	public List<TbCustomGroup> getTbCustomGroupList(Map params,Integer size);
	
	/**
	 * 获取客户组织统计
	 * @return
	 */
	public List getTbCustomGroupStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取客户组织总数
	 * @return
	 */	
	public Long getTbCustomGroupCount(Map params);
	
	
	/**
	 * 获取客户组织自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getTbCustomGroupEval(String eval,Map params);

	/**
	 * 获取客户组织分页
	 * @return
	 */	
	public Page getTbCustomGroupPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取客户组织
	 * @param params
	 * @return
	 */
	public TbCustomGroup getTbCustomGroup(Map params);

	/**
	 * 根据ID取客户组织
	 * @param id
	 * @return
	 */
	public TbCustomGroup getTbCustomGroupById(Long id);


	/**
	 * 保存客户组织
	 * @param tbCustomGroup
	 */
	public TbCustomGroup saveTbCustomGroup(TbCustomGroup tbCustomGroup);

	/**
	 * 更新客户组织
	 * @param tbCustomGroup
	 */
	public void updateTbCustomGroup(TbCustomGroup tbCustomGroup);
	/**
	 * 更新客户组织非空字段
	 * @param tbCustomGroup
	 */
	public void updateTbCustomGroup$NotNull(TbCustomGroup tbCustomGroup);

	/**
	 * 根据ID删除一个客户组织
	 * @param id
	 */
	public void deleteTbCustomGroupById(Long id);
	/**
	 * 根据ID逻辑删除一个客户组织
	 * @param id
	 */
	public void removeTbCustomGroupById(Long id);
	/**
	 * 根据条件更新客户组织
	 * @param values
	 * @param params
	 */
	public void updateTbCustomGroupByConfirm(Map values, Map params);
	/**
	 * 根据条件删除客户组织
	 * @param params
	 */
	public void deleteTbCustomGroupByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}