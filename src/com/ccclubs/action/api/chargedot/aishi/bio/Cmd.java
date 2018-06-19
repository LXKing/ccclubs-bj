package com.ccclubs.action.api.chargedot.aishi.bio;

/**
 * 指令
 * 
 * @author zhangjian
 *
 */
public interface Cmd {
	
	/**
	 * 设置指令类型
	 * @param cmdType
	 */
	public void setCmdType(RespType cmdType);
	
	/**
	 * 获取指令类型
	 * @return
	 */
	public RespType getCmdType();
	
	/**
	 * 设置指令
	 * @param cmd
	 */
	public void setCmd(String cmd);
	
}
