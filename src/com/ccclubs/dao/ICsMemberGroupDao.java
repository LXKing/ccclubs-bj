package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.ccclubs.model.CsMemberGroup;

/**
 * 会员组织的Dao接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsMemberGroupDao
{
	/**
	 * 获取所有会员组织
	 * @return
	 */
	public List<CsMemberGroup> getCsMemberGroupList(Map params,Integer size);
	
	/**
	 * 获取会员组织统计
	 * @return
	 */
	public List getCsMemberGroupStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取会员组织总数
	 * @return
	 */	
	public Long getCsMemberGroupCount(Map params);
	
	/**
	 * 获取会员组织自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMemberGroupEval(String eval,Map params);
	
	/**
	 * 获取会员组织分页
	 * @return
	 */	
	public Page getCsMemberGroupPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取会员组织
	 * @param params
	 * @return
	 */
	public CsMemberGroup getCsMemberGroup(Map params);	

	/**
	 * 根据ID取会员组织
	 * @param id
	 * @return
	 */
	public CsMemberGroup getCsMemberGroupById(Long id);


	
	

	/**
	 * 保存会员组织
	 * @param csMemberGroup
	 */
	public CsMemberGroup saveCsMemberGroup(CsMemberGroup csMemberGroup);

	/**
	 * 更新会员组织
	 * @param csMemberGroup
	 */
	public void updateCsMemberGroup(CsMemberGroup csMemberGroup);
	/**
	 * 更新会员组织非空字段
	 * @param csMemberGroup
	 */
	public void updateCsMemberGroup$NotNull(CsMemberGroup csMemberGroup);

	/**
	 * 根据ID删除一个会员组织
	 * @param id
	 */
	public void deleteCsMemberGroupById(Long id);
	/**
	 * 根据ID逻辑删除一个会员组织
	 * @param id
	 */
	public void removeCsMemberGroupById(Long id);
	/**
	 * 根据条件更新会员组织
	 * @param values
	 * @param params
	 */
	public void updateCsMemberGroupByConfirm(Map values, Map params);
	/**
	 * 根据条件删除会员组织
	 * @param params
	 */
	public void deleteCsMemberGroupByConfirm(Map params);

}