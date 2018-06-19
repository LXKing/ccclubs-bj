package com.ccclubs.action.api.webservice.struct;

import java.util.List;

import com.ccclubs.util.struct.OrderNumber;
import com.ccclubs.util.struct.size;
import com.ccclubs.util.struct.Struct;
import com.ccclubs.util.struct.Ignore;

/**
 * 车辆租赁点
 * 
 * @author zhangjian
 *
 */
public class CsOutletsRecord extends Struct{
	
	@Ignore(true)
	private String outletsId;						//租赁点编号
	
	@OrderNumber(1)
	private byte[] collectDate = new byte[6];		//数据采集时间
	
	@OrderNumber(2)
	private int csoLongitude;						//租赁点经度
	
	@OrderNumber(3)
	private int csoLatitude;						//租赁点纬度
	
	@size(6)
	@OrderNumber(4)
	private byte[] company = new byte[6];			//租赁点所属公司
	
	@OrderNumber(5)
	private byte carTypeCount;						//租赁点车型数量
	
	@Ignore(true)
	private String remain;							//预留
	
	@Ignore(true)
	private List<CsCarType> carTypeList;
	
	public byte[] getCompany() {
		return company;
	}

	public void setCompany(byte[] company) {
		this.company = company;
	}

	public String getRemain() {
		return remain;
	}

	public void setRemain(String remain) {
		this.remain = remain;
	}

	public List<CsCarType> getCarTypeList() {
		return carTypeList;
	}

	public void setCarTypeList(List<CsCarType> carTypeList) {
		this.carTypeList = carTypeList;
	}

	public byte[] getCollectDate() {
		return collectDate;
	}

	public void setCollectDate(byte[] collectDate) {
		this.collectDate = collectDate;
	}

	public int getCsoLongitude() {
		return csoLongitude;
	}

	public void setCsoLongitude(int csoLongitude) {
		this.csoLongitude = csoLongitude;
	}

	public int getCsoLatitude() {
		return csoLatitude;
	}

	public void setCsoLatitude(int csoLatitude) {
		this.csoLatitude = csoLatitude;
	}

	public byte getCarTypeCount() {
		return carTypeCount;
	}

	public void setCarTypeCount(byte carTypeCount) {
		this.carTypeCount = carTypeCount;
	}

	public String getOutletsId() {
		return outletsId;
	}

	public void setOutletsId(String outletsId) {
		this.outletsId = outletsId;
	}
	
}

