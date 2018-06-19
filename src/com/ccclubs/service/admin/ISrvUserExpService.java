package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.SrvUserExp;

/**
 * 用户扩展信息的Service接口
 * @author Joel
 */
@SuppressWarnings("unchecked")
public interface ISrvUserExpService
{
	/**
	 * 获取所有用户扩展信息
	 * @return
	 */
	public List<SrvUserExp> getSrvUserExpList(Map params,Integer size);
	
	/**
	 * 获取用户扩展信息统计
	 * @return
	 */
	public List getSrvUserExpStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取用户扩展信息总数
	 * @return
	 */	
	public Long getSrvUserExpCount(Map params);
	
	
	/**
	 * 获取用户扩展信息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvUserExpEval(String eval,Map params);

	/**
	 * 获取用户扩展信息分页
	 * @return
	 */	
	public Page getSrvUserExpPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取用户扩展信息
	 * @param params
	 * @return
	 */
	public SrvUserExp getSrvUserExp(Map params);

	/**
	 * 根据ID取用户扩展信息
	 * @param id
	 * @return
	 */
	public SrvUserExp getSrvUserExpById(Long id);


	/**
	 * 保存用户扩展信息
	 * @param srvUserExp
	 */
	public SrvUserExp saveSrvUserExp(SrvUserExp srvUserExp);

	/**
	 * 更新用户扩展信息
	 * @param srvUserExp
	 */
	public void updateSrvUserExp(SrvUserExp srvUserExp);
	/**
	 * 更新用户扩展信息非空字段
	 * @param srvUserExp
	 */
	public void updateSrvUserExp$NotNull(SrvUserExp srvUserExp);

	/**
	 * 根据ID删除一个用户扩展信息
	 * @param id
	 */
	public void deleteSrvUserExpById(Long id);
	/**
	 * 根据ID逻辑删除一个用户扩展信息
	 * @param id
	 */
	public void removeSrvUserExpById(Long id);
	/**
	 * 根据条件更新用户扩展信息
	 * @param values
	 * @param params
	 */
	public void updateSrvUserExpByConfirm(Map values, Map params);
	/**
	 * 根据条件删除用户扩展信息
	 * @param params
	 */
	public void deleteSrvUserExpByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}