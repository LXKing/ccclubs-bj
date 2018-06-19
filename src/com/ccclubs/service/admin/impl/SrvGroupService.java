package com.ccclubs.service.admin.impl;

import java.util.List;

import com.ccclubs.dao.ISrvGroupDao;
import com.ccclubs.dao.ISrvLimitedDao;
import com.ccclubs.model.SrvGroup;
import com.ccclubs.model.SrvUser;
import com.ccclubs.service.admin.ISrvGroupService;
import com.ccclubs.service.admin.ISrvUserService;

public class SrvGroupService implements ISrvGroupService
{
	ISrvGroupDao srvGroupDao;
	ISrvUserService srvUserService;
	ISrvLimitedDao srvLimitedDao;
	
	public List getSrvGroupList(SrvUser srvUser)
	{		
		return srvGroupDao.getSrvGroupList(srvUser);
	}
	
	public List<SrvGroup> getSrvUserTreeChild(SrvUser srvUser)
	{
		return srvGroupDao.getSrvUserTreeChild(srvUser);
	}
	
	public SrvGroup getSrvGroupById(Long id)
	{
		return srvGroupDao.getSrvGroupById(id);
	}
	
	public SrvGroup saveSrvGroup(SrvGroup srvGroup)
	{		
		srvGroupDao.saveSrvGroup(srvGroup);
		return null;
	}
	
	public void updateSrvGroup(SrvGroup srvGroup)
	{
		srvGroupDao.updateSrvGroup(srvGroup);
	}
	
	/**
	 * 删除组
	 */
	public void deleteSrvGroupById(Long id)
	{
		List<SrvUser> users=srvGroupDao.getUsersByGroupId(id);
		for(SrvUser o:users)//借用srvUserService删除用户
			srvUserService.deleteSrvUserById(o.getSuId());
		
		//删除当前组
		srvGroupDao.deleteSrvGroupById(id);
		
		srvLimitedDao.delLimitedByGroupIds(""+id);
	}
	
	/**
	 * 根据条件查询组列表
	 * @return
	 */
	public List<SrvGroup> getSrvGroupList(java.util.Map params,Integer size){
		return srvGroupDao.getSrvGroupList(params,size);
	}

	public ISrvGroupDao getSrvGroupDao()
	{
		return srvGroupDao;
	}

	public void setSrvGroupDao(ISrvGroupDao srvGroupDao)
	{
		this.srvGroupDao = srvGroupDao;
	}

	public ISrvUserService getSrvUserService()
	{
		return srvUserService;
	}

	public void setSrvUserService(ISrvUserService srvUserService)
	{
		this.srvUserService = srvUserService;
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