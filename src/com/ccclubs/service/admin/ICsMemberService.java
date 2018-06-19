package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsMember;

/**
 * 会员帐号的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ICsMemberService
{
	/**
	 * 获取所有会员帐号
	 * @return
	 */
	public List<CsMember> getCsMemberList(Map params,Integer size);
	
	/**
	 * 获取会员帐号统计
	 * @return
	 */
	public List getCsMemberStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取会员帐号总数
	 * @return
	 */	
	public Long getCsMemberCount(Map params);
	
	
	/**
	 * 获取会员帐号自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsMemberEval(String eval,Map params);

	/**
	 * 获取会员帐号分页
	 * @return
	 */	
	public Page getCsMemberPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取会员帐号
	 * @param params
	 * @return
	 */
	public CsMember getCsMember(Map params);

	/**
	 * 根据ID取会员帐号
	 * @param id
	 * @return
	 */
	public CsMember getCsMemberById(Long id);


	/**
	 * 保存会员帐号
	 * @param csMember
	 */
	public CsMember saveCsMember(CsMember csMember);

	/**
	 * 更新会员帐号
	 * @param csMember
	 */
	public void updateCsMember(CsMember csMember);
	/**
	 * 更新会员帐号非空字段
	 * @param csMember
	 */
	public void updateCsMember$NotNull(CsMember csMember);

	/**
	 * 根据ID删除一个会员帐号
	 * @param id
	 */
	public void deleteCsMemberById(Long id);
	/**
	 * 根据ID逻辑删除一个会员帐号
	 * @param id
	 */
	public void removeCsMemberById(Long id);
	/**
	 * 根据条件更新会员帐号
	 * @param values
	 * @param params
	 */
	public void updateCsMemberByConfirm(Map values, Map params);
	/**
	 * 根据条件删除会员帐号
	 * @param params
	 */
	public void deleteCsMemberByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}