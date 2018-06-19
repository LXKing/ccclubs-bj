package com.ccclubs.dao;

import java.util.List;

import com.ccclubs.model.SrvGroup;
import com.ccclubs.model.SrvUser;

@SuppressWarnings("unchecked")
public interface ISrvGroupDao
{

	/**
	 * 获取组树
	 * @param srvUser 
	 * @return
	 */
	List getSrvGroupList(SrvUser srvUser);
	
	/**
	 * 获取用户子级所有组
	 * @param login
	 * @return
	 */
	List<SrvGroup> getSrvUserTreeChild(SrvUser srvUser);

	/**
	 * 根据ID取组
	 * @param id
	 * @return
	 */
	SrvGroup getSrvGroupById(Long id);

	/**
	 * 保存组
	 * @param srvGroup
	 */
	SrvGroup saveSrvGroup(SrvGroup srvGroup);
	/**
	 * 更新组
	 * @param srvGroup
	 */
	void updateSrvGroup(SrvGroup srvGroup);

	/**
	 * 删除用户下所有组
	 * @param srvUser
	 */
	void deleteGroupsByUserFamily(SrvUser srvUser);

	/**
	 * 根据组ID取用户
	 * @param id
	 * @return
	 */
	List<SrvUser> getUsersByGroupId(Long id);

	/**
	 * 根据ID删除组
	 * @param id
	 */
	void deleteSrvGroupById(Long id);

	/**
	 * 根据用户IDS取组列表
	 * @param strUserIds
	 * @return
	 */
	List<SrvGroup> getGroupsByUserIds(String strUserIds);

	/**
	 * 根据条件查询组列表
	 * @return
	 */
	List<SrvGroup> getSrvGroupList(java.util.Map params,Integer size);
}