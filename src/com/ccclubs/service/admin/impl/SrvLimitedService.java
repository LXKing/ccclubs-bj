package com.ccclubs.service.admin.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ccclubs.model.SrvGroup;
import com.ccclubs.model.SrvLimited;
import com.ccclubs.model.SrvUser;

import com.ccclubs.dao.ISrvGroupDao;
import com.ccclubs.dao.ISrvLimitedDao;
import com.ccclubs.dao.ISrvUserDao;

import com.ccclubs.service.admin.ISrvLimitedService;
import com.ccclubs.service.admin.ISrvUserService;

public class SrvLimitedService implements ISrvLimitedService{

	ISrvLimitedDao srvLimitedDao;
	ISrvGroupDao srvGroupDao;
	ISrvUserDao srvUserDao;
	
	/**
	 * 获取所有权限
	 * @return
	 */
	public List<SrvLimited> getSrvLimitedList(Map params,Integer size)
	{
		return srvLimitedDao.getSrvLimitedList(params,size);
	}
	
	/**
	 * 获取组权限
	 * @param get_login_user
	 * @return
	 */
	public List<SrvLimited> getGroupLimits(SrvGroup srvGroup)
	{
		return srvLimitedDao.getLimitsByActorId(srvGroup.getSgId(), 0);
	}

	/**
	 * 获取用户权限，当用户没有权限时，获取该用户所属组的权限
	 * @param get_login_user
	 * @return
	 */
	public List<SrvLimited> getUserLimits(SrvUser srvUser, boolean bUserGroupLimits)
	{
		List<SrvLimited> list=srvLimitedDao.getLimitsByActorId(srvUser.getSuId(), 1);
		if(list.isEmpty() && bUserGroupLimits)
			return srvLimitedDao.getLimitsByActorId(srvUser.getSuGroupId(), 0);
		else
			return list;
	}
	
	/**
	 * 根据路径获取当前用户的权限
	 */
	@Override
	public SrvLimited getUserLimited(SrvUser srvUser, String strPath) {
		return srvLimitedDao.getUserLimited(srvUser,strPath);
	}

	/**
	 * 修改权限
	 * @param actorid
	 * @param actortype
	 * @param limits
	 */
	public void updateLimited(Long actorId, Integer actorType,List<SrvLimited> limits)
	{
		for(SrvLimited l:limits)
		{
			if(l.getSlId()==null)
				srvLimitedDao.saveSrvLimited(l);
			else
				srvLimitedDao.updateSrvLimited(l);
		}		
		
		List<SrvUser> childUsers;
		List<SrvGroup> childGroups;
		String strUserIds="";
		String strGroupIds="";
		
		//获取所有子节点用户
		if(actorType==1)
		{
			SrvUser user = srvUserDao.getSrvUserById(actorId);
			childUsers=srvUserDao.getSrvUserOnwerTree(user);
		}
		else
		{
			List<SrvUser> tempUsers=srvUserDao.getNoLimitUsersByGroupId(actorId);//获取没有权限的子用户
			List<String> familys=new ArrayList();
			for(SrvUser user:tempUsers)
			{
				familys.add(user.getSuFamily());
			}
			
			childUsers=srvUserDao.getUserTreeByFamilys(familys);
		}		
		for(SrvUser user:childUsers)
		{
			if(!strUserIds.equals(""))
				strUserIds+=",";
			strUserIds+=user.getSuId();
		}
		
		//获取相关的组		
		childGroups=srvGroupDao.getGroupsByUserIds(strUserIds);
		for(SrvGroup group:childGroups)
		{
			if(!strGroupIds.equals(""))
				strGroupIds+=",";
			strGroupIds+=group.getSgId();
		}		
		
		srvLimitedDao.updateLimitByParent(limits,strUserIds,strGroupIds);//根据父节点用户权限按位与权限，通过SQL语句执行
		
		srvLimitedDao.delAllNullLimits();//删除权限为0的权限
	}	


	public ISrvLimitedDao getSrvLimitedDao()
	{
		return srvLimitedDao;
	}

	public void setSrvLimitedDao(ISrvLimitedDao srvLimitedDao)
	{
		this.srvLimitedDao = srvLimitedDao;
	}

	public ISrvGroupDao getSrvGroupDao()
	{
		return srvGroupDao;
	}

	public void setSrvGroupDao(ISrvGroupDao srvGroupDao)
	{
		this.srvGroupDao = srvGroupDao;
	}

	public ISrvUserDao getSrvUserDao()
	{
		return srvUserDao;
	}

	public void setSrvUserDao(ISrvUserDao srvUserDao)
	{
		this.srvUserDao = srvUserDao;
	}
	

}