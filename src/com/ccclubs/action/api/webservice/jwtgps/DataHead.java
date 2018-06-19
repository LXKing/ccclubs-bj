package com.ccclubs.action.api.webservice.jwtgps;

import com.ccclubs.util.struct.OrderNumber;
import com.ccclubs.util.struct.Struct;

/**
 * 数据头    长度22个字节
 * 
 * @author joel
 *
 */
public class DataHead extends Struct{
	
	/**
	 * 数据长度
	 */
	@OrderNumber(value=1)
	private int msg_length;
	
	/**
	 * 序列号
	 */
	@OrderNumber(value=2)
	private int msg_sn;
	
	/**
	 * 数据类型 
	 */
	@OrderNumber(value=3)
	private short msg_id;
	
	/**
	 * 下级平台接入码
	 */
	@OrderNumber(value=4)
	private int msg_gnsscenterid;
	
	/**
	 * 协议版本号标识
	 */
	@OrderNumber(value=5)
	private byte[] version_flag  = new byte[3];
	
	/**
	 * 报文加密标识位 b: 0表示报文不加密，1表示报文加密
	 */
	@OrderNumber(value=6)
	private byte encrypt_flag;
	
	/**
	 * 数据加密的密匙，长度为4个字节
	 */
	@OrderNumber(value=7)
	private int encrypt_key;

	public int getMsg_length() {
		return msg_length;
	}

	public void setMsg_length(int msg_length) {
		this.msg_length = msg_length;
	}

	public int getMsg_sn() {
		return msg_sn;
	}

	public void setMsg_sn(int msg_sn) {
		this.msg_sn = msg_sn;
	}

	public short getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(short msg_id) {
		this.msg_id = msg_id;
	}

	public int getMsg_gnsscenterid() {
		return msg_gnsscenterid;
	}

	public void setMsg_gnsscenterid(int msg_gnsscenterid) {
		this.msg_gnsscenterid = msg_gnsscenterid;
	}

	public byte[] getVersion_flag() {
		return version_flag;
	}

	public void setVersion_flag(byte[] version_flag) {
		this.version_flag = version_flag;
	}

	public byte getEncrypt_flag() {
		return encrypt_flag;
	}

	public void setEncrypt_flag(byte encrypt_flag) {
		this.encrypt_flag = encrypt_flag;
	}

	public int getEncrypt_key() {
		return encrypt_key;
	}

	public void setEncrypt_key(int encrypt_key) {
		this.encrypt_key = encrypt_key;
	}
	
}
