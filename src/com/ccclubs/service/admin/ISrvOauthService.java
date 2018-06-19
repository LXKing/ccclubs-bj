package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.lazy3q.web.util.Page;
import com.lazy3q.util.Function;
import com.ccclubs.model.SrvOauth;

/**
 * 用户授权的Service接口
 * @author 飞啊飘啊
 */
@SuppressWarnings("unchecked")
public interface ISrvOauthService
{
	/**
	 * 获取所有用户授权
	 * @return
	 */
	public List<SrvOauth> getSrvOauthList(Map params,Integer size);
	
	/**
	 * 获取用户授权统计
	 * @return
	 */
	public List getSrvOauthStats(Map params,Map<String,Object> groups,Map<String,Object> sums);

	/**
	 * 获取用户授权总数
	 * @return
	 */	
	public Long getSrvOauthCount(Map params);
	
	
	/**
	 * 获取用户授权自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public <T> T getSrvOauthEval(String eval,Map params);

	/**
	 * 获取用户授权分页
	 * @return
	 */	
	public Page getSrvOauthPage(int page,int size,Map params);
	
	/**
	 * 根据查询条件取用户授权
	 * @param params
	 * @return
	 */
	public SrvOauth getSrvOauth(Map params);

	/**
	 * 根据ID取用户授权
	 * @param id
	 * @return
	 */
	public SrvOauth getSrvOauthById(Long id);


	/**
	 * 保存用户授权
	 * @param srvOauth
	 */
	public SrvOauth saveSrvOauth(SrvOauth srvOauth);

	/**
	 * 更新用户授权
	 * @param srvOauth
	 */
	public void updateSrvOauth(SrvOauth srvOauth);
	/**
	 * 更新用户授权非空字段
	 * @param srvOauth
	 */
	public void updateSrvOauth$NotNull(SrvOauth srvOauth);

	/**
	 * 根据ID删除一个用户授权
	 * @param id
	 */
	public void deleteSrvOauthById(Long id);
	/**
	 * 根据条件更新用户授权
	 * @param values
	 * @param params
	 */
	public void updateSrvOauthByConfirm(Map values, Map params);
	/**
	 * 根据条件删除用户授权
	 * @param params
	 */
	public void deleteSrvOauthByConfirm(Map params);

	/**
	 * 执行过程，以便事务回滚
	 * @param function
	 */
	public <T> T executeTransaction(Function function);

}