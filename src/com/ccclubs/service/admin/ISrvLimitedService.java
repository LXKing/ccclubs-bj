package com.ccclubs.service.admin;

import java.util.List;
import java.util.Map;

import com.ccclubs.model.SrvGroup;
import com.ccclubs.model.SrvLimited;
import com.ccclubs.model.SrvUser;

public interface ISrvLimitedService{
	
	/**
	 * 获取所有权限
	 * @return
	 */
	public List<SrvLimited> getSrvLimitedList(Map params,Integer size);

	List<SrvLimited> getUserLimits(SrvUser login, boolean b);
	
	List<SrvLimited> getGroupLimits(SrvGroup srvGroup);

	void updateLimited(Long actorid, Integer actortype, List<SrvLimited> limits);

	public SrvLimited getUserLimited(SrvUser login, String strPath);

}