package com.ccclubs.service.admin;

import java.util.List;

import com.ccclubs.model.SrvUser;

public interface ISrvUserService
{	
	/**
	 * 获取直接用户列表
	 * @param srvUser 
	 * @return
	 */
	List getSrvUserList(SrvUser srvUser);

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
	 * 根据ID删除用户
	 * @param id
	 */
	void deleteSrvUserById(Long id);

	/**
	 * 根据用户名取用户
	 * @param strUsername
	 * @return
	 */
	SrvUser getSrvUserByUsername(String strUsername);

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
	 * 获取用户子级树
	 * @param srvUser
	 * @return
	 */
	List<SrvUser> getSrvUserOnwerTree(SrvUser srvUser);

	/**
	 * 根据条件查询用户列表
	 * @return
	 */
	List<SrvUser> getSrvUserList(java.util.Map params,Integer size);
	
}