package com.ccclubs.action.api.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import com.ccclubs.action.api.webservice.struct.MOData;

public interface ICarStateService {

	/**
	 * 获取车辆租赁数据
	 * @return
	 */
	public String getCarRentData();
	
	/**
	 * 获取车辆租赁点数据
	 * @return
	 */
	public String getOutletsData();
	
	/**
	 * 发送车辆状态数据
	 * @param username
	 * @param password
	 * @param data
	 */
	@WebMethod
	public String uploadPacketData(@WebParam(name="userName")String userName,@WebParam(name="pwd")String pwd,@WebParam(name="packetData")String packetData);
	
	
	/**
	 * 保存状态数据
	 */
	public void saveMOData(List<MOData> datas);
	
}
