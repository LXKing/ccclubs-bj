package com.ccclubs.dao;

import java.util.List;
import java.util.Map;

import com.ccclubs.model.SrvLimited;
import com.ccclubs.model.SrvUser;

public interface ISrvLimitedDao{
	
	/**
	 * 获取所有权限
	 * @return
	 */
	public List<SrvLimited> getSrvLimitedList(Map params,Integer size);

	void delLimitedByGroupIds(String string);

	void delLimitedByProIds(String strIds);

	void delLimitedByUserIds(String ids);

	List<SrvLimited> getLimitsByActorId(Long sgId, int i);

	void saveSrvLimited(SrvLimited l);

	void updateSrvLimited(SrvLimited l);

	void updateLimitByParent(List<SrvLimited> limits, String strUserIds,String strGroupIds);

	void delAllNullLimits();

	public SrvLimited getUserLimited(SrvUser srvUser, String strPath);

}