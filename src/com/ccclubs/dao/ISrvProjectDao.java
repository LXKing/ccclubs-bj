package com.ccclubs.dao;

import java.util.List;

import com.ccclubs.model.SrvProject;

public interface ISrvProjectDao
{

	/**
	 * 获取项目树
	 * @return
	 */
	List getSrvProjectList();

	/**
	 * 根据ID取项目
	 * @param id
	 * @return
	 */
	SrvProject getSrvProjectById(Long id);

	/**
	 * 保存项目
	 * @param srvProject
	 */
	SrvProject saveSrvProject(SrvProject srvProject);
	/**
	 * 更新项目
	 * @param srvProject
	 */
	void updateSrvProject(SrvProject srvProject);


	/**
	 * 根据一些ID取项目
	 * @param strIds
	 * @return
	 */
	List<SrvProject> getSrvProjectListByIds(String strIds);

	/**
	 * 取项目树
	 * @param project
	 * @return
	 */
	List<SrvProject> getSrvProjectTree(SrvProject project);
	
	/**
	 * 删除项目树
	 * @param project
	 */
	void delSrvProjectTree(SrvProject project);
	
	/**
	 * 根据条件查询项目列表
	 * @return
	 */
	List<SrvProject> getSrvProjectList(java.util.Map params,Integer size);

}