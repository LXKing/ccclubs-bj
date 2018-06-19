package com.ccclubs.service.admin;

import java.util.List;

import com.ccclubs.model.SrvProject;
import com.ccclubs.model.SrvUser;

public interface ISrvProjectService
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
	 * 根据ID删除项目
	 * @param id
	 */
	void deleteSrvProjectById(Long id);
	
	/**
	 * 根据一些ID取项目
	 * @param strIds
	 * @return
	 */
	List<SrvProject> getSrvProjectListByIds(String strIds);
	
	/**
	 * 根据条件查询项目列表
	 * @return
	 */
	List<SrvProject> getSrvProjectList(java.util.Map params,Integer size);
	
}