package com.ccclubs.service.admin.impl;

import java.util.List;

import com.ccclubs.dao.ISrvGroupDao;
import com.ccclubs.dao.ISrvLimitedDao;
import com.ccclubs.dao.ISrvUserDao;
import com.ccclubs.model.SrvGroup;
import com.ccclubs.model.SrvUser;
import com.ccclubs.service.admin.ISrvUserService;

public class SrvUserService implements ISrvUserService
{
	ISrvUserDao srvUserDao;	
	ISrvGroupDao srvGroupDao;
	ISrvLimitedDao srvLimitedDao;
	
	public List getSrvUserList(SrvUser srvUser)
	{		
		return srvUserDao.getSrvUserList(srvUser);
	}
	
	public SrvUser getSrvUserById(Long id)
	{
		return srvUserDao.getSrvUserById(id);
	}
	
	public SrvUser getSrvUserByUsername(String strUsername)
	{
		return srvUserDao.getSrvUserByUsername(strUsername);
	}
	
	public SrvUser saveSrvUser(SrvUser srvUser)
	{
		SrvUser parentSrvUser = srvUserDao.getSrvUserById(srvUser.getSuParentId());
		srvUser=srvUserDao.saveSrvUser(srvUser);
		if(parentSrvUser!=null)
			srvUser.setSuFamily(parentSrvUser.getSuFamily()+",@"+srvUser.getSuGroupId()+"@"+",#"+srvUser.getSuId()+"#");
		else
			srvUser.setSuFamily("#0#"+",@"+srvUser.getSuGroupId()+"@"+",#"+srvUser.getSuId()+"#");
		srvUserDao.updateSrvUser(srvUser);
		return srvUser;
	}
	
	public void updateSrvUser(SrvUser srvUser)
	{
		srvUserDao.updateSrvUser(srvUser);
	}
	
	/**
	 * 删除用户,级连删除下面所有组和用户
	 */
	public void deleteSrvUserById(Long id)
	{		
		SrvUser srvUser=srvUserDao.getSrvUserById(id);
		
		List<SrvUser> list=srvUserDao.getSrvUserTree(srvUser);
		String ids="";
		for(SrvUser u:list)
		{
			if(!ids.equals(""))
				ids+=",";
			ids+=u.getSuId();
		}
		
		//删除该用户下所有组
		srvGroupDao.deleteGroupsByUserFamily(srvUser);
		srvUserDao.deleteUserTree(srvUser);
		
		srvLimitedDao.delLimitedByUserIds(ids);
	}
	
	public List<SrvUser> getSrvUserOnwerTree(SrvUser srvUser)
	{		
		return srvUserDao.getSrvUserOnwerTree(srvUser);
	}

	public void updatePassword(SrvUser srvUser)
	{
		srvUserDao.updatePassword(srvUser);
	}
	
	public void updateLastIp(SrvUser srvUser)
	{
		srvUserDao.updateLastIp(srvUser);
	}
	
	/**
	 * 根据条件查询用户列表
	 * @return
	 */
	public List<SrvUser> getSrvUserList(java.util.Map params,Integer size){
		return srvUserDao.getSrvUserList(params,size);
	}
	
	public ISrvUserDao getSrvUserDao()
	{
		return srvUserDao;
	}

	public void setSrvUserDao(ISrvUserDao srvUserDao)
	{
		this.srvUserDao = srvUserDao;
	}

	public ISrvGroupDao getSrvGroupDao()
	{
		return srvGroupDao;
	}

	public void setSrvGroupDao(ISrvGroupDao srvGroupDao)
	{
		this.srvGroupDao = srvGroupDao;
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