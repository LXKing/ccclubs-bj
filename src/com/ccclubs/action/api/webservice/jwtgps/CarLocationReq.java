package com.ccclubs.action.api.webservice.jwtgps;

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
 * 车辆定位GPS数据请求
 * 
 * @author joel
 *
 */
public class CarLocationReq extends Struct{
	
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
	private byte[] gnss_data;	//车辆定位数据

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

	public byte[] getGnss_data() {
		return gnss_data;
	}

	public void setGnss_data(byte[] gnss_data) {
		this.gnss_data = gnss_data;
	}
	
	/**
	 * 
	 * @param type 是否实时数据，or补发数据
	 * @param port
	 * @param state
	 * @return
	 */
	public byte[] getHexString(int port, CsState state){

		DataHead dh = new DataHead();
		dh.setMsg_length(36+22+2);
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
		
		CsCar car = JwtGPSReportTask.cars.get(state.getCssNumber());
		if(car == null) return new byte[0];
		this.setVehicle_no(car.getCscNumber());
		if(car!=null){
			this.setVehicle_color((byte)Convert.getColor(car.getCscColor()));
		}else{
			this.setVehicle_color((byte)Convert.getColor(null));
		}
		this.setData_type((short)0x1202);
		this.setData_length(36);			//TODO  最终确认
		CarLocationRecord data = new CarLocationRecord();
		this.setGnss_data(data.getHexString(state));
		
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
		System.out.println("车辆GPS位置："+hexStr);
		return messageBytes;
//		return hexStr;
	}
	
	public static void main(String[] args) {
		
	}
	
}
