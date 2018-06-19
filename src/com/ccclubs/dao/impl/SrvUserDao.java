package com.ccclubs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ISrvUserDao;
import com.ccclubs.model.SrvUser;
import com.ccclubs.model.SrvUserExp;

@SuppressWarnings("unchecked")
public class SrvUserDao extends SqlMapClientDaoSupport implements ISrvUserDao
{
	public List getSrvUserList(SrvUser srvUser)
	{		
		return  this.getSqlMapClientTemplate().queryForList("getSrvUserList",srvUser);
	}

	public SrvUser getSrvUserById(Long id)
	{
		if(id==null || id.longValue()==0)
			return null;
	
		SrvUser srvUser = MemCache.getObject(SrvUser.class, id);
		if(srvUser!=null)
			return srvUser;
			
		Map params=new HashMap();
		params.put("id", id);
		srvUser = (SrvUser) this.getSqlMapClientTemplate().queryForObject("getSrvUserById",params);
		if(srvUser!=null)
			MemCache.setValue(SrvUser.class,id, srvUser.getKeyValue());
		if(srvUser!=null)
			MemCache.setObject(SrvUser.class,id, srvUser);
		return srvUser;
	}
	
	public SrvUser getSrvUserByUsername(String strUsername)
	{
		Map params=new HashMap();
		params.put("suUsername", strUsername);
		return  (SrvUser) this.getSqlMapClientTemplate().queryForObject("getSrvUserByUsername",params);
	}
	
	public SrvUser saveSrvUser(SrvUser srvUser)
	{
		Long id=(Long) this.getSqlMapClientTemplate().insert("saveSrvUser", srvUser);
		srvUser = this.getSrvUserById(id);
		MemCache.setValue(SrvUser.class,id, srvUser.getKeyValue());
		MemCache.setObject(SrvUser.class,id,srvUser);
		MemCache.clearObject(SrvUser.M.class);
		MemCache.clearValue(SrvUser.M.class);
		return srvUser;
	}
	public void updateSrvUser(SrvUser srvUser)
	{
		this.getSqlMapClientTemplate().update("updateSrvUser", srvUser);
		srvUser = this.getSrvUserById(srvUser.getSuId());
		if(srvUser!=null){
			MemCache.setValue(SrvUser.class,srvUser.getSuId(), srvUser.getKeyValue());
			MemCache.setObject(SrvUser.class,srvUser.getSuId(),srvUser);
		}
		MemCache.clearObject(SrvUser.M.class);
		MemCache.clearValue(SrvUser.M.class);
	}

	public void deleteUserTree(SrvUser srvUser)
	{
		this.getSqlMapClientTemplate().delete("deleteUserTree", srvUser);
		MemCache.clearObject(SrvUser.M.class);
		MemCache.clearValue(SrvUser.M.class);
		MemCache.clearObject(SrvUser.class);
		MemCache.clearValue(SrvUser.class);
	}
	
	public List getSrvUserTree(SrvUser srvUser)
	{		
		return  this.getSqlMapClientTemplate().queryForList("getSrvUserTree",srvUser);
	}
	
	public List<SrvUser> getSrvUserOnwerTree(SrvUser srvUser)
	{
		return  this.getSqlMapClientTemplate().queryForList("getSrvUserOnwerTree",srvUser);
	}

	public List<SrvUser> getNoLimitUsersByGroupId(Long actorId)
	{
		Map params=new HashMap();
		params.put("groupId", actorId);
		return  this.getSqlMapClientTemplate().queryForList("getNoLimitUsersByGroupId",params);
	}

	public List<SrvUser> getUserTreeByFamilys(List<String> familys)
	{
		if(familys.isEmpty())
			return new ArrayList();
		
		Map params=new HashMap();
		params.put("familys", familys);
		
		return  this.getSqlMapClientTemplate().queryForList("getUserTreeByFamilys",params);
	}

	public void updatePassword(SrvUser srvUser)
	{
		this.getSqlMapClientTemplate().update("updatePassword", srvUser);
		srvUser = this.getSrvUserById(srvUser.getSuId());
		if(srvUser!=null){
			MemCache.setValue(SrvUser.class,srvUser.getSuId(), srvUser.getKeyValue());
			MemCache.setObject(SrvUser.class,srvUser.getSuId(),srvUser);
		}
		MemCache.clearObject(SrvUser.M.class);
		MemCache.clearValue(SrvUser.M.class);
	}
	
	public void updateLastIp(SrvUser srvUser)
	{
		this.getSqlMapClientTemplate().update("updateLastIp", srvUser);
		srvUser = this.getSrvUserById(srvUser.getSuId());
		if(srvUser!=null){
			MemCache.setValue(SrvUser.class,srvUser.getSuId(), srvUser.getKeyValue());
			MemCache.setObject(SrvUser.class,srvUser.getSuId(),srvUser);
		}
		MemCache.clearObject(SrvUser.M.class);
		MemCache.clearValue(SrvUser.M.class);
	}
	
	/**
	 * 根据条件查询用户列表
	 * @return
	 */
	public List<SrvUser> getSrvUserList(java.util.Map params,Integer size){
		if(size!=null && size!=-1)
			params.put("limit", size);
		return this.getSqlMapClientTemplate().queryForList("getSrvUserListBy", params);
	}
}