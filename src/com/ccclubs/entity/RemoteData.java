package com.ccclubs.entity;

import java.util.Map;

import com.ccclubs.model.SrvUser;
import com.lazy3q.lang.RelateClass;
import com.lazy3q.lang.api;
import com.lazy3q.lang.caption;
import com.lazy3q.lang.hidden;
import com.lazy3q.lang.type;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;


/**
 * 外勤人员远程操作统计
 * @author byz
 *
 */
public @caption("远程控制统计") class RemoteData {

	
	private @caption("管理员") @hidden @RelateClass(SrvUser.class) SrvUser srvUser;
	
	private @caption("ID") Long suId;
	
	private @caption("姓名") String suRealName;
	
	private @caption("手机号") String suMobile;
	
	private @caption("远程开门统计") Integer rmOptenDoorCount;
	
	private @caption("远程关门统计") Integer rmCloseDoorCount;
	
	private @caption("允许启动次数统计") Integer rmStartCount;
	
	private @caption("远程鸣笛次数统计") Integer rmBlowCount;//鸣笛
	
	private @caption("大洗次数统计") Integer bigCleanCount;//大洗
	
	private @caption("小洗次数统计") Integer smallCleanCount;
	
	private @caption("充电次数统计") Integer rechargedCount;//
	
	private @caption("挪车次数统计") Integer moveCarCount;//
	
	private @caption("拿物品次数统计") Integer takeThingsCount;

	
	
	public SrvUser getSrvUser() {
		return srvUser;
	}

	public void setSrvUser(SrvUser srvUser) {
		this.srvUser = srvUser;
	}

	public Long getSuId() {
		return suId;
	}

	public void setSuId(Long suId) {
		this.suId = suId;
	}

	public String getSuRealName() {
		return suRealName;
	}

	public void setSuRealName(String suRealName) {
		this.suRealName = suRealName;
	}

	public String getSuMobile() {
		return suMobile;
	}

	public void setSuMobile(String suMobile) {
		this.suMobile = suMobile;
	}


	public Integer getRmOptenDoorCount() {
		return rmOptenDoorCount;
	}

	public void setRmOptenDoorCount(Integer rmOptenDoorCount) {
		this.rmOptenDoorCount = rmOptenDoorCount;
	}

	public Integer getRmCloseDoorCount() {
		return rmCloseDoorCount;
	}

	public void setRmCloseDoorCount(Integer rmCloseDoorCount) {
		this.rmCloseDoorCount = rmCloseDoorCount;
	}

	public Integer getRmStartCount() {
		return rmStartCount;
	}

	public void setRmStartCount(Integer rmStartCount) {
		this.rmStartCount = rmStartCount;
	}

	public Integer getRmBlowCount() {
		return rmBlowCount;
	}

	public void setRmBlowCount(Integer rmBlowCount) {
		this.rmBlowCount = rmBlowCount;
	}

	public Integer getBigCleanCount() {
		return bigCleanCount;
	}

	public void setBigCleanCount(Integer bigCleanCount) {
		this.bigCleanCount = bigCleanCount;
	}

	public Integer getSmallCleanCount() {
		return smallCleanCount;
	}

	public void setSmallCleanCount(Integer smallCleanCount) {
		this.smallCleanCount = smallCleanCount;
	}

	public Integer getRechargedCount() {
		return rechargedCount;
	}

	public void setRechargedCount(Integer rechargedCount) {
		this.rechargedCount = rechargedCount;
	}

	public Integer getMoveCarCount() {
		return moveCarCount;
	}

	public void setMoveCarCount(Integer moveCarCount) {
		this.moveCarCount = moveCarCount;
	}

	public Integer getTakeThingsCount() {
		return takeThingsCount;
	}

	public void setTakeThingsCount(Integer takeThingsCount) {
		this.takeThingsCount = takeThingsCount;
	}
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscId="cscId";
		
		public final static @type(Long.class) String suId="suId";
		
		public final static @type(String.class) String suRealName="suRealName";
		
		public final static @type(String.class) String suMobile="suMobile";
		
		public final static @type(Integer.class) String rmOptenDoorCount="rmOptenDoorCount";
		
		public final static @type(Integer.class) String rmCloseDoorCount="rmCloseDoorCount";
		
		public final static @type(Integer.class) String rmStartCount="rmStartCount";
		
		public final static @type(Integer.class) String rmBlowCount="rmBlowCount";//鸣笛
		
		public final static @type(Integer.class) String bigCleanCount="bigCleanCount";//大洗
		
		public final static @type(Integer.class) String smallCleanCount="smallCleanCount";
		
		public final static @type(Integer.class) String rechargedCount="rechargedCount";//
		
		public final static @type(Integer.class) String moveCarCount="moveCarCount";//
		
		public final static @type(Integer.class) String takeThingsCount="takeThingsCount";
		
	}
	
	public static @api Page<RemoteData> page(int page,int size,Map params){
		return getRemoteDataPage(page, size , params);
	}
	
	public static @api Page<RemoteData> getRemoteDataPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsAnalysisService csAnalysisService = $.GetSpringBean("csAnalysisService");
		return csAnalysisService.getReMoteDataPage(page, size , params);
	}
}
