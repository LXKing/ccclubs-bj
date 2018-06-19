package com.ccclubs.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ccclubs.dao.ICsInfoDistributeDao;

@SuppressWarnings("rawtypes")
public class CsInfoDistributeDao extends SqlMapClientDaoSupport implements ICsInfoDistributeDao {

	/**
	 * 以车辆数据为主体，获取车辆订单状况
	 */
	@Override
	public List getCsCarAndOrder() {
		return (List)this.getSqlMapClientTemplate().queryForList("getCsCarAndOrder");
	}
	
	/**
	 * 获取网点和对应车型数据
	 */
	@Override
	public List getCsOutletsAndCarModelData() {
		List dataList = (List) this.getSqlMapClientTemplate().queryForList("getCsOutletsAndCarModelData");
		return dataList;
	}

}
