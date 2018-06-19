package com.ccclubs.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ISrvLimitedDao;
import com.ccclubs.model.CsUseRecord;
import com.ccclubs.model.SrvLimited;
import com.ccclubs.model.SrvUser;

@SuppressWarnings("unchecked")
public class SrvLimitedDao extends SqlMapClientDaoSupport implements ISrvLimitedDao{

	
	/**
	 * 获取所有权限
	 * @return
	 */	
	public List<SrvLimited> getSrvLimitedList(Map params,Integer size)
	{	
		if(size!=null && size!=-1)
			params.put("limit", size);
		return this.getSqlMapClientTemplate().queryForList("getSrvLimitedList", params);
	}
	
	
	public void delLimitedByGroupIds(String string)
	{		
		Map params=new HashMap();
		params.put("ids", string);
		this.getSqlMapClientTemplate().delete("delLimitedByGroupIds", params);
	}

	public void delLimitedByProIds(String strIds)
	{
		Map params=new HashMap();
		params.put("ids", strIds);
		this.getSqlMapClientTemplate().delete("delLimitedByProIds", params);
	}

	public void delLimitedByUserIds(String ids)
	{
		Map params=new HashMap();
		params.put("ids", ids);
		this.getSqlMapClientTemplate().delete("delLimitedByUserIds", params);
	}

	public void delAllNullLimits()
	{
		this.getSqlMapClientTemplate().delete("delAllNullLimits");
	}

	public List<SrvLimited> getLimitsByActorId(Long sgId, int type)
	{
		Map params=new HashMap();
		params.put("actorId", sgId);
		params.put("actorType", type);
		return  this.getSqlMapClientTemplate().queryForList("getLimitsByActorId", params);
	}

	public void updateLimitByParent(List<SrvLimited> limits, String strUserIds,String strGroupIds)
	{
		if((strUserIds==null || strUserIds.equals("")) && (strGroupIds==null || strGroupIds.equals("")))
			return;		
			
		for(SrvLimited limit:limits)
		{
			String strPartSql="";		
			if(!strUserIds.equals(""))
			{
				strPartSql+="(sl_Actor_Id in ("+strUserIds+") and sl_Actor_Type=1)";
			}				
			if(!strGroupIds.equals(""))
			{
				if(!strPartSql.equals(""))
					strPartSql+=" or ";
				strPartSql+="(sl_Actor_Id in ("+strGroupIds+") and sl_Actor_Type=0)";
			}				
			if(!strPartSql.equals(""))
				strPartSql=" and ("+strPartSql+") ";				
			String strSql="update srv_limited set sl_Limit = sl_Limit & "+limit.getSlLimit()+" where sl_Project_Id="+limit.getSlProjectId()+strPartSql;
			
			Map params=new HashMap();
			params.put("sql", strSql);
			this.getSqlMapClientTemplate().update("updateLimitByParent", params);
		}
		
	}
	
	@Override
	public SrvLimited getUserLimited(SrvUser srvUser, String strPath) {
		Long count = (Long) this.getSqlMapClientTemplate().queryForObject("getUserLimitedCount", srvUser);
		Map params=new HashMap();
		params.put("actorId", count>0?srvUser.getSuId():srvUser.getSuGroupId());
		params.put("actorType", count>0?1:0);
		params.put("path","%" + strPath + "%");
		return (SrvLimited) this.getSqlMapClientTemplate().queryForObject("getUserLimited",params);
	}

	public void updateSrvLimited(SrvLimited l)
	{
		this.getSqlMapClientTemplate().update("updateSrvLimited", l);
	}
	
	public void saveSrvLimited(SrvLimited l)
	{
		this.getSqlMapClientTemplate().insert("saveSrvLimited", l);
	}


	

}