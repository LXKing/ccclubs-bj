package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.CsUnderlineMember;

/**
 * 线下人员管理的Service接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ICsUnderlineMemberService
{
	/**
	 * 获取所有线下人员管理
	 * @return
	 */
	public List<CsUnderlineMember> getCsUnderlineMemberList(Map params,Integer size);
	
	/**
	 * 获取线下人员管理统计
	 * @return
	 */
	public List getCsUnderlineMemberStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取线下人员管理总数
	 * @return
	 */	
	public Long getCsUnderlineMemberCount(Map params);
	
	
	/**
	 * 获取线下人员管理自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getCsUnderlineMemberEval(String eval,Map params);

	/**
	 * 获取线下人员管理分页
	 * @return
	 */	
	public Page getCsUnderlineMemberPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取线下人员管理
	 * @param params
	 * @return
	 */
	public CsUnderlineMember getCsUnderlineMember(Map params);

	/**
	 * 根据ID取线下人员管理
	 * @param id
	 * @return
	 */
	public CsUnderlineMember getCsUnderlineMemberById(Long id);


	/**
	 * 保存线下人员管理
	 * @param csUnderlineMember
	 */
	public CsUnderlineMember saveCsUnderlineMember(CsUnderlineMember csUnderlineMember);

	/**
	 * 更新线下人员管理
	 * @param csUnderlineMember
	 */
	public void updateCsUnderlineMember(CsUnderlineMember csUnderlineMember);
	/**
	 * 更新线下人员管理非空字段
	 * @param csUnderlineMember
	 */
	public void updateCsUnderlineMember$NotNull(CsUnderlineMember csUnderlineMember);

	/**
	 * 根据ID删除一个线下人员管理
	 * @param id
	 */
	public void deleteCsUnderlineMemberById(Long id);
	/**
	 * 根据ID逻辑删除一个线下人员管理
	 * @param id
	 */
	public void removeCsUnderlineMemberById(Long id);
	/**
	 * 根据条件更新线下人员管理
	 * @param values
	 * @param params
	 */
	public void updateCsUnderlineMemberByConfirm(Map values, Map params);
	/**
	 * 根据条件删除线下人员管理
	 * @param params
	 */
	public void deleteCsUnderlineMemberByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}