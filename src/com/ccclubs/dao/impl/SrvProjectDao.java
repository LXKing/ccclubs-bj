package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ISrvProjectDao;
import com.ccclubs.model.SrvProject;

@SuppressWarnings("unchecked")
public class SrvProjectDao extends SqlMapClientDaoSupport implements ISrvProjectDao
{
	public List getSrvProjectList()
	{
		return  this.getSqlMapClientTemplate().queryForList("getSrvProjectList");
	}

	public SrvProject getSrvProjectById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		return  (SrvProject) this.getSqlMapClientTemplate().queryForObject("getSrvProjectById",params);
	}
	public SrvProject saveSrvProject(SrvProject srvProject)
	{
		Long id=(Long) this.getSqlMapClientTemplate().insert("saveSrvProject", srvProject);
		return this.getSrvProjectById(id);
	}
	public void updateSrvProject(SrvProject srvProject)
	{
		this.getSqlMapClientTemplate().update("updateSrvProject", srvProject);
	}

	public List<SrvProject> getSrvProjectListByIds(String strIds)
	{
		if(strIds==null || strIds.equals(""))
			return new ArrayList();
		Map params=new HashMap();
		params.put("ids", strIds);
		return this.getSqlMapClientTemplate().queryForList("getSrvProjectListByIds", params);
	}

	
	public void delSrvProjectTree(SrvProject project){
		this.getSqlMapClientTemplate().delete("delSrvProjectTree", project);
	}

	public List<SrvProject> getSrvProjectTree(SrvProject project)
	{		
		return this.getSqlMapClientTemplate().queryForList("getSrvProjectTree", project);
	}
	
	/**
	 * 根据条件查询项目列表
	 * @return
	 */
	public List<SrvProject> getSrvProjectList(java.util.Map params,Integer size){
		if(size!=null && size!=-1)
			params.put("limit", size);
		return this.getSqlMapClientTemplate().queryForList("getSrvProjectListBy", params);
	}
	
}