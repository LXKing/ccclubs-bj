package com.ccclubs.dao;

import java.util.List;

/**
 * 
 * 数据交换
 * 
 * @author zhangjian
 *
 */
public interface ICsInfoDistributeDao {
	
	/**
	 * 以车辆数据为主体，获取车辆订单状况
	 * @return
	 */
	public List getCsCarAndOrder();
	
	/**
	 * 查询网点和对应车型数据
	 * @return
	 */
	public List getCsOutletsAndCarModelData();
	
	
}
