package com.ccclubs.action.api.webservice.jwtgps;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.ccclubs.action.api.webservice.JwtGPSReportTask;
import com.ccclubs.model.CsCar;
import com.ccclubs.model.CsState;
import com.ccclubs.util.ByteUtil;
import com.ccclubs.util.CRCUtil;
import com.ccclubs.util.struct.OrderNumber;
import com.ccclubs.util.struct.Pad;
import com.ccclubs.util.struct.PadType;
import com.ccclubs.util.struct.Struct;
import com.ccclubs.util.struct.size;

/**
 * 
 * 补报车辆定位信息
 * 
 * @author joel
 *
 */
public class RepayCarLocationReq extends Struct{
	
	@size(value=21)
	@OrderNumber(value=1)
	@Pad(type=PadType.RIRHT)
	private String vehicle_no; //车牌号
	
	@OrderNumber(value=2)
	private byte vehicle_color; //车辆颜色
	
	@OrderNumber(value=3)
	private short data_type;    //数据类型
	
	@OrderNumber(value=4)
	private int data_length;	//数据长度
	
	@OrderNumber(value=5)
	private byte gnss_cnt;		//该数据包里包含的卫星定位数据个数
	
	@OrderNumber(value=6)
	private byte[] gnss_datas;	//数据1....N的合并bytes

	public String getVehicle_no() {
		return vehicle_no;
	}

	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}

	public byte getVehicle_color() {
		return vehicle_color;
	}

	public void setVehicle_color(byte vehicle_color) {
		this.vehicle_color = vehicle_color;
	}

	public short getData_type() {
		return data_type;
	}

	public void setData_type(short data_type) {
		this.data_type = data_type;
	}

	public int getData_length() {
		return data_length;
	}

	public void setData_length(int data_length) {
		this.data_length = data_length;
	}

	public byte[] getGnss_datas() {
		return gnss_datas;
	}

	public void setGnss_datas(byte[] gnss_datas) {
		this.gnss_datas = gnss_datas;
	}
	
	public byte getGnss_cnt() {
		return gnss_cnt;
	}

	public void setGnss_cnt(byte gnss_cnt) {
		this.gnss_cnt = gnss_cnt;
	}
	
	/**
	 * 
	 * @param type 是否实时数据，or补发数据
	 * @param port
	 * @param state
	 * @return
	 */
	public byte[] getHexString(int port, List<CsState> state){
		if(CollectionUtils.isEmpty(state))return new byte[0];

		DataHead dh = new DataHead();
		dh.setMsg_length(46+22+2);
		dh.setMsg_sn(0);
		dh.setMsg_id((short)0x1200);
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
		CsCar car = JwtGPSReportTask.cars.get(state.get(0).getCssNumber());
		if(car == null) return new byte[0];
		this.setVehicle_no(car.getCscCarNo());
		if(car!=null){
			this.setVehicle_color((byte)Convert.getColor(car.getCscColor()));
		}else{
			this.setVehicle_color((byte)Convert.getColor(null));
		}
		this.setData_type((short)0x1203);
		int len = 36 * state.size();
		this.setData_length(len);								//TODO  最终确认
		this.setGnss_cnt((byte)state.size());					//该数据包里包含的卫星定位数据个数

		byte[] a = null;										//TODO
		for(CsState s : state){
			CarLocationRecord data = new CarLocationRecord();
			byte[] b = data.getHexString(s);
			if(b!=null){
				if(a == null){
					a = b;
				}else{
					a = ByteUtil.union(a, b);
				}
			}
		}
		
		this.setGnss_datas(a);
		
		byte[] bodyBytes = null;
		try {
			bodyBytes = Struct.getObjectBytesExt(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		byte[] byte_3 = ByteUtil.union(headBytes, bodyBytes);
		byte_3 = Convert.replace2(byte_3);
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
		messageBytes = Convert.replace2(messageBytes);
		String hexStr = Struct.bytesToHexString(messageBytes).toUpperCase();
		System.out.println("补报车辆GPS信息："+hexStr);
		return messageBytes;
//		return hexStr;
	}
}
