package com.ccclubs.action.web.activity.newyear2015;

/**
 * 活动车型
 * 
 * @author joel
 *
 */
public enum ActivityCarType {
	BKKY(1, ActivityBase.NEW_YEAR_PACK_2015_KY), MG3(2, ActivityBase.NEW_YEAR_PACK_2015_MG), SMART(3, ActivityBase.NEW_YEAR_PACK_2015_SMART);
	
	private int id;
	private String packFlag;
	
	public int getId() {
		return id;
	}
	
	public String getPackFlag(){
		return packFlag;
	}

	ActivityCarType(int id, String packFlag){
		this.id = id;
		this.packFlag = packFlag;
	}
}
