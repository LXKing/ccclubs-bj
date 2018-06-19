package com.ccclubs.action.api.chargedot.aishi.bio;


/**
 * 指令
 * 
 * 
 * @author zhangjian
 *
 */
public class Command implements Cmd{
	
	/**
	 * 命令类型
	 */
	private RespType cmdType;
	
	/**
	 * 文本
	 */
	private String cmd;
	
	/**
	 * 上一条命令
	 */
	private Command prev;
	
	/**
	 * 下一条命令
	 */
	private	Command next;
	
	public Command(){
		
	}
	
	public Command(RespType cmdType, String cmd){
		this.cmdType = cmdType;
		this.cmd = cmd;
	}

	public RespType getCmdType() {
		return cmdType;
	}

	public void setCmdType(RespType cmdType) {
		this.cmdType = cmdType;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public Command getPrev() {
		return prev;
	}

	public void setPrev(Command prev) {
		this.prev = prev;
	}

	public Command getNext() {
		return next;
	}

	public void setNext(Command next) {
		this.next = next;
	}
	
}
