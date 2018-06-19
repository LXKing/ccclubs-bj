package com.ccclubs.action.api.webservice.struct;

import java.util.List;

import com.ccclubs.util.struct.OrderNumber;
import com.ccclubs.util.struct.size;
import com.ccclubs.util.struct.Struct;
import com.ccclubs.util.struct.Ignore;

/**
 * 车型编号数据
 * @author zhangjian
 *
 */
public class CsCarType extends Struct{
	
	@size(10)
	@OrderNumber(1)
	private byte[] typeId = new byte[10];		//车型编号
	
	@OrderNumber(2)
	private byte[] typeCount;						//车型编号数目
	
	@Ignore(true)
	private List<String> typeNumber;			//车型车牌号
	
	public byte[] getTypeCount() {
		return typeCount;
	}

	public void setTypeCount(byte[] typeCount) {
		this.typeCount = typeCount;
	}

	public List<String> getTypeNumber() {
		return typeNumber;
	}

	public void setTypeNumber(List<String> typeNumber) {
		this.typeNumber = typeNumber;
	}

	public byte[] getTypeId() {
		return typeId;
	}

	public void setTypeId(byte[] typeId) {
		this.typeId = typeId;
	}
	
}
