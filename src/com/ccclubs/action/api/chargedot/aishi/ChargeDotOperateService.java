package com.ccclubs.action.api.chargedot.aishi;

import com.ccclubs.action.api.chargedot.aishi.bio.Result;

/**
 * 
 * 充电桩操作接口
 * 
 * @author zhangjian
 *
 */
public interface ChargeDotOperateService {
	
	/**
	 * 登陆
	 * @param cid
	 * @param password
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Result login(String cid, String password, String user)throws Exception;
	
	/**
	 * 预约充电
	 * 分别执行(登陆-》绑定-》预约充电)
	 * 
	 * @param did 电桩设备编号
	 * @return
	 */
	public Result timing(String cid, String password, String user, String did);
	
	
	/**
	 * 取消预约
	 * 分别执行(登陆-》绑定-》取消预约充电)
	 * 
	 * @param did 电桩设备编号
	 * @return
	 */
	public Result cancelTiming(String cid, String password, String user, String did);
}
