package com.ccclubs.action.api.webservice.jwtgps;

import com.ccclubs.util.ByteUtil;
import com.ccclubs.util.CRCUtil;
import com.ccclubs.util.struct.OrderNumber;
import com.ccclubs.util.struct.Pad;
import com.ccclubs.util.struct.PadType;
import com.ccclubs.util.struct.Struct;
import com.ccclubs.util.struct.size;

/**
 * 消息长度 46byte
 * 
 * @author joel
 *
 */
public class LoginReq extends Struct{
	
	@OrderNumber(value=1)
	private int userId;
	
	@size(value=8)
	@OrderNumber(value=2)
	@Pad(type=PadType.RIRHT)
	private String password;
	
	@size(value=32)
	@OrderNumber(value=3)
	@Pad(type=PadType.RIRHT)
	private String downlinkip;
	
	@OrderNumber(value=4)
	private short downlinkport;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDownlinkip() {
		return downlinkip;
	}

	public void setDownlinkip(String downlinkip) {
		this.downlinkip = downlinkip;
	}

	public short getDownlinkport() {
		return downlinkport;
	}

	public void setDownlinkport(short downlinkport) {
		this.downlinkport = downlinkport;
	}
	
	public byte[] getHexString(int port){

		DataHead dh = new DataHead();
		dh.setMsg_length(46+22+2);
		dh.setMsg_sn(0);
		dh.setMsg_id((short)0x1001);
		dh.setMsg_gnsscenterid(1144);
		dh.setVersion_flag(new byte[]{0x01, 0x02, 0x0F});
		dh.setEncrypt_flag((byte)0x00);
		dh.setEncrypt_key(0);
		byte[] headBytes = null;
		try {
			headBytes = Struct.getObjectBytesExt(dh);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.setUserId(1011);
		this.setPassword("jwtgps");
		this.setDownlinkip("123.57.172.114");
//		this.setDownlinkport((short)8899);
		this.setDownlinkport((short)port);
		byte[] bodyBytes = null;
		try {
			bodyBytes = Struct.getObjectBytesExt(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		byte[] byte_3 = ByteUtil.union(headBytes, bodyBytes); 
		int crc_code = CRCUtil.do_crc(byte_3);
		
		Data dt = new Data();
		dt.setHeadFlag((byte)0x5b);
		dt.setMessageHeader(dh);
		dt.setMessageBody(this);
		dt.setCrcCode((short)crc_code);
		dt.setEndFlag((byte)0x5d);
		
		byte[] messageBytes = null;
		try {
			messageBytes = Struct.getObjectBytesExt(dt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String hexStr = Struct.bytesToHexString(messageBytes).toUpperCase();
		System.out.println(hexStr);
		return messageBytes;
//		return hexStr;
	}
	
}
