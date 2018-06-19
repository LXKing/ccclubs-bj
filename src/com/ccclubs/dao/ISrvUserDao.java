package com.ccclubs.dao;

import java.util.List;

import com.ccclubs.model.SrvUser;

public interface ISrvUserDao
{

	/**
	 * 获取用户列表
	 * @param srvUser 
	 * @return
	 */
	List getSrvUserList(SrvUser srvUser);
	
	/**
	 * 获取用户树
	 * @param srvUser 
	 * @return
	 */
	List getSrvUserTree(SrvUser srvUser);

	/**
	 * 根据ID取用户
	 * @param id
	 * @return
	 */
	SrvUser getSrvUserById(Long id);

	/**
	 * 保存用户
	 * @param srvUser
	 */
	SrvUser saveSrvUser(SrvUser srvUser);
	/**
	 * 更新用户
	 * @param srvUser
	 */
	void updateSrvUser(SrvUser srvUser);

	/**
	 * 根据用户名取用户
	 * @param strUsername
	 * @return
	 */
	SrvUser getSrvUserByUsername(String strUsername);

	/**
	 * 删除用户下所有用户
	 * @param srvUser
	 */
	void deleteUserTree(SrvUser srvUser);

	/**
	 * 获取没有权限的子用户
	 * @param actorId
	 * @return
	 */
	List<SrvUser> getNoLimitUsersByGroupId(Long actorId);

	/**
	 * 根据一些familys取用户
	 * @param familys
	 * @return
	 */
	List<SrvUser> getUserTreeByFamilys(List<String> familys);

	/**
	 * 取子集用户
	 * @param user
	 * @return
	 */
	List<SrvUser> getSrvUserOnwerTree(SrvUser user);

	/**
	 * 修改密码
	 * @param srvUser
	 */
	void updatePassword(SrvUser srvUser);
	
	/**
	 * 更新IP
	 * @param remoteAddr
	 */
	void updateLastIp(SrvUser srvUser);
	
	/**
	 * 根据条件查询用户列表
	 * @return
	 */
	List<SrvUser> getSrvUserList(java.util.Map params,Integer size);

}