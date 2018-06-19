package com.ccclubs.action.api.webservice.jwtgps;

import com.ccclubs.util.struct.OrderNumber;
import com.ccclubs.util.struct.Struct;

/**
 * 数据体部分
 * 
 * @author joel
 *
 */
public class Data extends Struct{
	
	@OrderNumber(value=1)
	private byte headFlag;
	
	@OrderNumber(value=2)
	private DataHead messageHeader;
	
	@OrderNumber(value=3)
	private Object messageBody;
	
	@OrderNumber(value=4)
	private short crcCode;
	
	@OrderNumber(value=5)
	private byte endFlag;

	public byte getHeadFlag() {
		return headFlag;
	}

	public void setHeadFlag(byte headFlag) {
		this.headFlag = headFlag;
	}

	public byte getEndFlag() {
		return endFlag;
	}

	public void setEndFlag(byte endFlag) {
		this.endFlag = endFlag;
	}

	public DataHead getMessageHeader() {
		return messageHeader;
	}

	public void setMessageHeader(DataHead messageHeader) {
		this.messageHeader = messageHeader;
	}

	public Object getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(Object messageBody) {
		this.messageBody = messageBody;
	}

	public short getCrcCode() {
		return crcCode;
	}

	public void setCrcCode(short crcCode) {
		this.crcCode = crcCode;
	}
	
	
}
