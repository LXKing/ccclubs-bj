package com.ccclubs.dao.impl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.dao.ISrvGroupDao;
import com.ccclubs.model.SrvGroup;
import com.ccclubs.model.SrvUser;

@SuppressWarnings("unchecked")
public class SrvGroupDao extends SqlMapClientDaoSupport implements ISrvGroupDao
{
	public List getSrvGroupList(SrvUser srvUser)
	{
		return  this.getSqlMapClientTemplate().queryForList("getSrvGroupList",srvUser);
	}
	
	public List<SrvGroup> getSrvUserTreeChild(SrvUser srvUser)
	{
		return  this.getSqlMapClientTemplate().queryForList("getSrvUserTreeChild",srvUser);
	}

	public SrvGroup getSrvGroupById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		return  (SrvGroup) this.getSqlMapClientTemplate().queryForObject("getSrvGroupById",params);
	}
	public SrvGroup saveSrvGroup(SrvGroup srvGroup)
	{
		Long id=(Long) this.getSqlMapClientTemplate().insert("saveSrvGroup", srvGroup);
		return this.getSrvGroupById(id);
	}
	public void updateSrvGroup(SrvGroup srvGroup)
	{
		this.getSqlMapClientTemplate().update("updateSrvGroup", srvGroup);
	}

	public void deleteGroupsByUserFamily(SrvUser srvUser)
	{
		this.getSqlMapClientTemplate().delete("deleteGroupsByUserFamily", srvUser);
	}

	public List<SrvUser> getUsersByGroupId(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		return this.getSqlMapClientTemplate().queryForList("getUsersByGroupId", params);
	}

	public void deleteSrvGroupById(Long id)
	{
		Map params=new HashMap();
		params.put("id", id);
		this.getSqlMapClientTemplate().delete("deleteSrvGroupById", params);
	}

	public List<SrvGroup> getGroupsByUserIds(String strUserIds)
	{
		if(strUserIds==null || strUserIds.equals(""))
			return new ArrayList();
		Map params=new HashMap();
		params.put("ids", strUserIds);
		return  this.getSqlMapClientTemplate().queryForList("getGroupsByUserIds",params);
	}

	/**
	 * 根据条件查询项目列表
	 * @return
	 */
	public List<SrvGroup> getSrvGroupList(java.util.Map params,Integer size){
		if(size!=null && size!=-1)
			params.put("limit", size);
		return this.getSqlMapClientTemplate().queryForList("getSrvGroupListBy", params);
	}
	
}