package com.ccclubs.service.admin.impl;

import java.util.List;

import com.ccclubs.dao.ISrvLimitedDao;
import com.ccclubs.dao.ISrvProjectDao;
import com.ccclubs.model.SrvProject;
import com.ccclubs.model.SrvUser;
import com.ccclubs.service.admin.ISrvProjectService;

public class SrvProjectService implements ISrvProjectService
{
	ISrvProjectDao srvProjectDao;
	ISrvLimitedDao srvLimitedDao;	
	
	public List getSrvProjectList()
	{		
		return srvProjectDao.getSrvProjectList();
	}
	
	public SrvProject getSrvProjectById(Long id)
	{
		return srvProjectDao.getSrvProjectById(id);
	}
	
	public SrvProject saveSrvProject(SrvProject srvProject)
	{
		SrvProject parentSrvProject = srvProjectDao.getSrvProjectById(srvProject.getSpParentId());
		srvProject=srvProjectDao.saveSrvProject(srvProject);
		if(parentSrvProject!=null)
			srvProject.setSpFamily(parentSrvProject.getSpFamily()+","+srvProject.getSpId());
		else
			srvProject.setSpFamily(""+srvProject.getSpId());
		
		srvProjectDao.updateSrvProject(srvProject);
		
		return srvProject;
	}
	
	public List<SrvProject> getSrvProjectListByIds(String strIds)
	{
		return srvProjectDao.getSrvProjectListByIds(strIds);
	}
	
	public void updateSrvProject(SrvProject srvProject)
	{
		SrvProject parentSrvProject = srvProjectDao.getSrvProjectById(srvProject.getSpParentId());
		srvProjectDao.updateSrvProject(srvProject);
		if(parentSrvProject!=null)
			srvProject.setSpFamily(parentSrvProject.getSpFamily()+","+srvProject.getSpId());
		else
			srvProject.setSpFamily(""+srvProject.getSpId());
	}
	
	public void deleteSrvProjectById(Long id)
	{
		SrvProject project=srvProjectDao.getSrvProjectById(id);		
		List<SrvProject> list=srvProjectDao.getSrvProjectTree(project);
		
		String strIds="";
		for(SrvProject o:list)
		{
			if(!strIds.equals(""))
				strIds+=",";
			strIds+=o.getSpId();
		}
		
		srvLimitedDao.delLimitedByProIds(strIds);//同时删除关联的权限	
		srvProjectDao.delSrvProjectTree(project);//删除项目
	}
	
	/**
	 * 根据条件查询项目列表
	 * @return
	 */
	public List<SrvProject> getSrvProjectList(java.util.Map params,Integer size){
		return srvProjectDao.getSrvProjectList(params,size);
	}

	public ISrvProjectDao getSrvProjectDao()
	{
		return srvProjectDao;
	}

	public void setSrvProjectDao(ISrvProjectDao srvProjectDao)
	{
		this.srvProjectDao = srvProjectDao;
	}

	public ISrvLimitedDao getSrvLimitedDao()
	{
		return srvLimitedDao;
	}

	public void setSrvLimitedDao(ISrvLimitedDao srvLimitedDao)
	{
		this.srvLimitedDao = srvLimitedDao;
	}
	
}