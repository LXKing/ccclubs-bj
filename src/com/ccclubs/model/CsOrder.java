package com.ccclubs.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.lazy3q.web.helper.$;

import com.lazy3q.web.tags.BodyTag;

import com.lazy3q.web.util.Page;
import com.lazy3q.lang.*;
import com.lazy3q.util.Function;
import com.ccclubs.dao.impl.MemCache;

@namespace("service/order")
public @caption("系统订单") @table("cs_order") class CsOrder implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("订单编号") @column("cso_id")   @primary  @note("  ") Long csoId;// 主键 非空     
	private @caption("城市") @column("cso_host")    @relate("$csoHost") @RelateClass(SrvHost.class)  @note("  ") Long csoHost;// 非空     
 	private SrvHost $csoHost;//关联对象[运营城市]
	private @caption("支付会员") @column("cso_pay_member")    @relate("$csoPayMember") @RelateClass(CsMember.class)  @note("  ") Long csoPayMember;// 非空    ${basePath}${proname}/user/member_inquire.do 
 	private CsMember $csoPayMember;//关联对象[会员帐号]
	private @caption("使用会员") @column("cso_use_member")    @relate("$csoUseMember") @RelateClass(CsMember.class)  @note("  ") Long csoUseMember;// 非空    ${basePath}${proname}/user/member_inquire.do 
 	private CsMember $csoUseMember;//关联对象[会员帐号]
	//private @caption("操作记录项") @RelateClass(CsOrderLog.class) List<CsOrderLog> logs;//反向关联的订单操作记录列表
	private @caption("计费类型") @column("cso_fee_type")    @relate("$csoFeeType") @RelateClass(CsUserType.class)  @note("  ") Long csoFeeType;// 非空     
 	private CsUserType $csoFeeType;//关联对象[计费方式]
	private @caption("网点区域") @column("cso_area")    @relate("$csoArea") @RelateClass(CsArea.class)  @note("  ") Long csoArea;// 非空     
 	private CsArea $csoArea;//关联对象[网点区域]
	private @caption("取车网点") @column("cso_outlets")    @relate("$csoOutlets") @RelateClass(CsOutlets.class)  @note(" {csoHost}:host  ") Long csoOutlets;// 非空 {csoHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} 
 	private CsOutlets $csoOutlets;//关联对象[网点]
	private @caption("还车网点") @column("cso_outlets_ret")    @relate("$csoOutletsRet") @RelateClass(CsOutlets.class)  @note(" {csoHost}:host  ") Long csoOutletsRet;// 非空 {csoHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} 
 	private CsOutlets $csoOutletsRet;//关联对象[网点]
	private @caption("预订车型") @column("cso_model")    @relate("$csoModel") @RelateClass(CsCarModel.class)  @note("  ") Long csoModel;// 非空     
 	private CsCarModel $csoModel;//关联对象[车型]
	private @caption("预订车辆") @column("cso_car")    @relate("$csoCar") @RelateClass(CsCar.class)  @note(" {csoOutlets}:cscOutlets {csoModel}:cscModel  ") Long csoCar;// 非空 {csoOutlets}:cscOutlets {csoModel}:cscModel     
 	private CsCar $csoCar;//关联对象[车辆]
	private @caption("车辆商家") @column("cso_provid")    @relate("$csoProvid") @RelateClass(CsProvid.class)  @note("  ") Long csoProvid;// 非空     
 	private CsProvid $csoProvid;//关联对象[供应商]
	private @caption("车牌号码") @column("cso_car_number")    @note("  ") String csoCarNumber;// 非空     
	private @caption("手机号码") @column("cso_mobile")    @note("  ") String csoMobile;//     
	private @caption("EV序列号") @column("cso_ev_rfid")    @note("  ") String csoEvRfid;// 非空     
	private @caption("宣传车？") @column("cso_is_ad_car")    @note("  ") Boolean csoIsAdCar;//     
	private @caption("订单开始时间") @column("cso_start_time")    @note("  ") Date csoStartTime;// 非空     
	private @caption("订单结束时间") @column("cso_finish_time")    @note("  ") Date csoFinishTime;// 非空     
	private @caption("是否长单") @column("cso_long_order")    @note("  ") Boolean csoLongOrder;//     
	private @caption("长单价格") @column("cso_long_price")    @note("  元/月") Double csoLongPrice;//(单位：元)  元/月   
	private @caption("用车方式") @column("cso_use_type")    @note(" 0:原借原还 1:A借B还  ") Short csoUseType;// 0:原借原还 1:A借B还     
	private @caption("实际取车时间") @column("cso_take_time")    @note("  ") Date csoTakeTime;//     
	private @caption("实际还车时间") @column("cso_ret_time")    @note("  ") Date csoRetTime;//     
	private @caption("订单时长") @column("cso_duration")    @note("  ") Double csoDuration;//(单位：小时) 非空     
	private @caption("燃油里程") @column("cso_fuel")    @note("  ") Double csoFuel;// 非空     
	private @caption("电里程") @column("cso_electric")    @note("  ") Double csoElectric;// 非空     
	private @caption("使用信用卡") @column("cso_credit_card")    @relate("$csoCreditCard") @RelateClass(CsCreditCard.class)  @note(" {csoPayMember}:csccMember  ") Long csoCreditCard;// {csoPayMember}:csccMember     
 	private CsCreditCard $csoCreditCard;//关联对象[信用卡]
	private @caption("信用卡帐单") @column("cso_credit_bill")    @relate("$csoCreditBill") @RelateClass(CsCreditBill.class)  @note(" {csoPayMember}:cscbMember  ") Long csoCreditBill;// {csoPayMember}:cscbMember     
 	private CsCreditBill $csoCreditBill;//关联对象[信用账单]
	private @caption("保险类型") @column("cso_insure_type")    @relate("$csoInsureType") @RelateClass(CsProduct.class)  @note("  ") Long csoInsureType;//    select * from cs_product where csp_flag='INSURE' or csp_flag='SECURE' 
 	private CsProduct $csoInsureType;//关联对象[产品]
	private @caption("免费小时") @column("cso_freehour")    @note("  ") Double csoFreehour;//     
	//private @caption("订单明细") @RelateClass(CsOrderDetail.class) List<CsOrderDetail> csoCostDetails;//反向关联的订单明细列表
	//private @caption("免费优惠项") @RelateClass(CsUseRecord.class) List<CsUseRecord> gifts;//反向关联的惠免记录列表
	//private @caption("消费记录项") @RelateClass(CsRecord.class) List<CsRecord> records;//反向关联的消费记录列表
	//private @caption("相关违章") @RelateClass(CsViolat.class) List<CsViolat> violats;//反向关联的车辆违章列表
	//private @caption("相关事故") @RelateClass(CsTrouble.class) List<CsTrouble> troubles;//反向关联的车辆事故列表
	//private @caption("收入记录") @RelateClass(CsOperRecord.class) List<CsOperRecord> operrecords;//反向关联的收支记录列表
	private @caption("折扣") @column("cso_rebate")    @note("  0到1,比如0.75表示七五折") Double csoRebate;//  0到1,比如0.75表示七五折   
	private @caption("押金/保证金") @column("cso_margin_need")    @note("  ") Double csoMarginNeed;//(单位：元)     
	private @caption("预计/里程费") @column("cso_predict")    @note("  ") Double csoPredict;//(单位：元)     
	private @caption("应付金额") @column("cso_pay_need")    @note("  应付为当前状态下的预计理论金额") Double csoPayNeed;//(单位：元)  应付为当前状态下的预计理论金额   
	private @caption("实付金额") @column("cso_pay_real")    @note("  ") Double csoPayReal;//(单位：元)     
	private @caption("保证金支付") @column("cso_pay_money")    @note("  ") Double csoPayMoney;//(单位：元)     
	private @caption("现金券支付") @column("cso_pay_coupon")    @note("  ") Double csoPayCoupon;//(单位：元)     
	private @caption("红包支付") @column("cso_pay_coin")    @note("  ") Double csoPayCoin;//(单位：元)     
	private @caption("电里程费") @column("cso_pay_kilom")    @note("  ") Double csoPayKilom;//(单位：元)     
	private @caption("油里程费") @column("cso_pay_mileage")    @note("  ") Double csoPayMileage;//(单位：元)     
	private @caption("租金费用") @column("cso_pay_rent")    @note("  ") Double csoPayRent;//(单位：元)     
	private @caption("免责费用") @column("cso_pay_insure")    @note("  ") Double csoPayInsure;//(单位：元)     
	private @caption("超时费用") @column("cso_pay_timeout")    @note("  ") Double csoPayTimeout;//(单位：元)     
	private @caption("陪驾费用") @column("cso_pay_driver")    @note("  ") Double csoPayDriver;//     
	private @caption("计入营收") @column("cso_income")    @note(" 0:不计营收 1:计入营收  ") Short csoIncome;// 0:不计营收 1:计入营收     
	private @caption("订单次数") @column("cso_use_index")    @note("  标明该订单是会员第几次用车") Integer csoUseIndex;//  标明该订单是会员第几次用车   
	private @caption("类型标识") @column("cso_flag")    @note(" 0:默认 1:2014春节订单 2:2015春节订单 3:订单簇  ") Short csoFlag;// 0:默认 1:2014春节订单 2:2015春节订单 3:套餐     
	private @caption("订单来源") @column("cso_from")    @note(" 0:网站预订 1:后台预订 2:微信预订 3:App预订 4:企业预订 5:支付宝预订  ") Short csoFrom;// 非空 0:网站预订 1:后台预订 2:微信预订 3:App预订 4:企业预订 5:支付宝预订     
	private @caption("来源信息") @column("cso_src")    @note("  ") String csoSrc;//     存储格式:类名@ID,其中类名包括
	private Object $csoSrc;//泛关联对象，对象类名由csoSrc字段中标明
	private @caption("修改时间") @column("cso_update_time")    @note("  ") Date csoUpdateTime;// 非空     
	private @caption("添加时间") @column("cso_add_time")    @note("  ") Date csoAddTime;// 非空     
	private @caption("结束时间") @column("cso_end_time")    @note("  ") Date csoEndTime;//     
	private @caption("序列号") @column("cso_serial")    @note("  ") String csoSerial;//     
	private @caption("授权码") @column("cso_code")    @note("  ") String csoCode;// 非空     
	private @caption("订单备注") @column("cso_remark")    @note("  ") String csoRemark;//     
	private @caption("历史日志") @column("cso_log")  @hidden   @note("  ") String csoLog;//     
	private @caption("操作人") @column("cso_editor")    @relate("$csoEditor") @RelateClass(SrvUser.class)  @note("  ") Long csoEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csoEditor;//关联对象[用户]
	private @caption("程序掩码") @column("cso_mask")    @note(" 1:催还车短信已发 2:设置不发还车短信  ") Long csoMask;// 非空 1:催还车短信已发 2:设置不发还车短信     
	private @caption("下位机版本") @column("cso_device_vesion")    @note(" 0:默认 1:测试  ") Short csoDeviceVesion;// 0:默认 1:测试     
	private @caption("报警") @column("cso_alarm")    @note(" 0:默认 1:超距 2:超时 3:断线  ") Short csoAlarm;// 0:默认 1:超距 2:超时 3:断线     
	private @caption("数据状态") @column("cso_state")    @note(" 0:未发送 1:已发送 2:已收到 3:不发送 4:待审批  ") Short csoState;// 非空 0:未发送 1:已发送 2:已收到 3:不发送     
	private @caption("状态") @column("cso_status")    @note(" 0:已预定 1:使用中 2:已还车 3:已取消 4:已完成 5:待处理 6:已退款 7:已作废  ") Short csoStatus;// 非空 0:已预定 1:使用中 2:已还车 3:已取消 4:已完成 5:待处理 6:已退款 7:已作废     
	private @caption("取消来源 ") @column("cso_cancel_from")      @note("1:后台取消 3:app取消") Short csoCancelFrom;

	//默认构造函数
	public CsOrder(){
	
	}
	
	//主键构造函数
	public CsOrder(Long id){
		this.csoId = id;
	}
	
	/**所有字段构造函数 CsOrder(csoHost,csoPayMember,csoUseMember,csoFeeType,csoArea,csoOutlets,csoOutletsRet,csoModel,csoCar,csoProvid,csoCarNumber,csoMobile,csoEvRfid,csoIsAdCar,csoStartTime,csoFinishTime,csoLongOrder,csoLongPrice,csoUseType,csoTakeTime,csoRetTime,csoDuration,csoFuel,csoElectric,csoCreditCard,csoCreditBill,csoInsureType,csoFreehour,csoRebate,csoMarginNeed,csoPredict,csoPayNeed,csoPayReal,csoPayMoney,csoPayCoupon,csoPayCoin,csoPayKilom,csoPayMileage,csoPayRent,csoPayInsure,csoPayTimeout,csoPayDriver,csoIncome,csoUseIndex,csoFlag,csoFrom,csoSrc,csoUpdateTime,csoAddTime,csoEndTime,csoSerial,csoCode,csoRemark,csoLog,csoEditor,csoMask,csoDeviceVesion,csoAlarm,csoState,csoStatus)
	 CsOrder(
	 	$.getLong("csoHost")//城市 [非空]
	 	,$.getLong("csoPayMember")//支付会员 [非空]
	 	,$.getLong("csoUseMember")//使用会员 [非空]
	 	,$.getLong("csoFeeType")//计费类型 [非空]
	 	,$.getLong("csoArea")//网点区域 [非空]
	 	,$.getLong("csoOutlets")//取车网点 [非空]
	 	,$.getLong("csoOutletsRet")//还车网点 [非空]
	 	,$.getLong("csoModel")//预订车型 [非空]
	 	,$.getLong("csoCar")//预订车辆 [非空]
	 	,$.getLong("csoProvid")//车辆商家 [非空]
	 	,$.getString("csoCarNumber")//车牌号码 [非空]
	 	,$.getString("csoMobile")//手机号码
	 	,$.getString("csoEvRfid")//EV序列号 [非空]
	 	,$.getBoolean("csoIsAdCar")//宣传车？
	 	,$.getDate("csoStartTime")//订单开始时间 [非空]
	 	,$.getDate("csoFinishTime")//订单结束时间 [非空]
	 	,$.getBoolean("csoLongOrder")//是否长单
	 	,$.getDouble("csoLongPrice")//长单价格
	 	,$.getShort("csoUseType")//用车方式
	 	,$.getDate("csoTakeTime")//实际取车时间
	 	,$.getDate("csoRetTime")//实际还车时间
	 	,$.getDouble("csoDuration")//订单时长 [非空]
	 	,$.getDouble("csoFuel")//燃油里程 [非空]
	 	,$.getDouble("csoElectric")//电里程 [非空]
	 	,$.getLong("csoCreditCard")//使用信用卡
	 	,$.getLong("csoCreditBill")//信用卡帐单
	 	,$.getLong("csoInsureType")//保险类型
	 	,$.getDouble("csoFreehour")//免费小时
	 	,$.getDouble("csoRebate")//折扣
	 	,$.getDouble("csoMarginNeed")//押金/保证金
	 	,$.getDouble("csoPredict")//预计/里程费
	 	,$.getDouble("csoPayNeed")//应付金额
	 	,$.getDouble("csoPayReal")//实付金额
	 	,$.getDouble("csoPayMoney")//保证金支付
	 	,$.getDouble("csoPayCoupon")//现金券支付
	 	,$.getDouble("csoPayCoin")//红包支付
	 	,$.getDouble("csoPayKilom")//电里程费
	 	,$.getDouble("csoPayMileage")//油里程费
	 	,$.getDouble("csoPayRent")//租金费用
	 	,$.getDouble("csoPayInsure")//免责费用
	 	,$.getDouble("csoPayTimeout")//超时费用
	 	,$.getDouble("csoPayDriver")//陪驾费用
	 	,$.getShort("csoIncome")//计入营收
	 	,$.getInteger("csoUseIndex")//订单次数
	 	,$.getShort("csoFlag")//类型标识
	 	,$.getShort("csoFrom")//订单来源 [非空]
	 	,$.getString("csoSrc")//来源信息
	 	,$.getDate("csoUpdateTime")//修改时间 [非空]
	 	,$.getDate("csoAddTime")//添加时间 [非空]
	 	,$.getDate("csoEndTime")//结束时间
	 	,$.getString("csoSerial")//序列号
	 	,$.getString("csoCode")//授权码 [非空]
	 	,$.getString("csoRemark")//订单备注
	 	,$.getString("csoLog")//历史日志
	 	,$.getLong("csoEditor")//操作人 [非空]
	 	,$.getLong("csoMask")//程序掩码 [非空]
	 	,$.getShort("csoDeviceVesion")//下位机版本
	 	,$.getShort("csoAlarm")//报警
	 	,$.getShort("csoState")//数据状态 [非空]
	 	,$.getShort("csoStatus")//状态 [非空]
	 )
	**/
	public CsOrder(Long csoHost,Long csoPayMember,Long csoUseMember,Long csoFeeType,Long csoArea,Long csoOutlets,Long csoOutletsRet,Long csoModel,Long csoCar,Long csoProvid,String csoCarNumber,String csoMobile,String csoEvRfid,Boolean csoIsAdCar,Date csoStartTime,Date csoFinishTime,Boolean csoLongOrder,Double csoLongPrice,Short csoUseType,Date csoTakeTime,Date csoRetTime,Double csoDuration,Double csoFuel,Double csoElectric,Long csoCreditCard,Long csoCreditBill,Long csoInsureType,Double csoFreehour,Double csoRebate,Double csoMarginNeed,Double csoPredict,Double csoPayNeed,Double csoPayReal,Double csoPayMoney,Double csoPayCoupon,Double csoPayCoin,Double csoPayKilom,Double csoPayMileage,Double csoPayRent,Double csoPayInsure,Double csoPayTimeout,Double csoPayDriver,Short csoIncome,Integer csoUseIndex,Short csoFlag,Short csoFrom,String csoSrc,Date csoUpdateTime,Date csoAddTime,Date csoEndTime,String csoSerial,String csoCode,String csoRemark,String csoLog,Long csoEditor,Long csoMask,Short csoDeviceVesion,Short csoAlarm,Short csoState,Short csoStatus){
		this.csoHost=csoHost;
		this.csoPayMember=csoPayMember;
		this.csoUseMember=csoUseMember;
		this.csoFeeType=csoFeeType;
		this.csoArea=csoArea;
		this.csoOutlets=csoOutlets;
		this.csoOutletsRet=csoOutletsRet;
		this.csoModel=csoModel;
		this.csoCar=csoCar;
		this.csoProvid=csoProvid;
		this.csoCarNumber=csoCarNumber;
		this.csoMobile=csoMobile;
		this.csoEvRfid=csoEvRfid;
		this.csoIsAdCar=csoIsAdCar;
		this.csoStartTime=csoStartTime;
		this.csoFinishTime=csoFinishTime;
		this.csoLongOrder=csoLongOrder;
		this.csoLongPrice=csoLongPrice;
		this.csoUseType=csoUseType;
		this.csoTakeTime=csoTakeTime;
		this.csoRetTime=csoRetTime;
		this.csoDuration=csoDuration;
		this.csoFuel=csoFuel;
		this.csoElectric=csoElectric;
		this.csoCreditCard=csoCreditCard;
		this.csoCreditBill=csoCreditBill;
		this.csoInsureType=csoInsureType;
		this.csoFreehour=csoFreehour;
		this.csoRebate=csoRebate;
		this.csoMarginNeed=csoMarginNeed;
		this.csoPredict=csoPredict;
		this.csoPayNeed=csoPayNeed;
		this.csoPayReal=csoPayReal;
		this.csoPayMoney=csoPayMoney;
		this.csoPayCoupon=csoPayCoupon;
		this.csoPayCoin=csoPayCoin;
		this.csoPayKilom=csoPayKilom;
		this.csoPayMileage=csoPayMileage;
		this.csoPayRent=csoPayRent;
		this.csoPayInsure=csoPayInsure;
		this.csoPayTimeout=csoPayTimeout;
		this.csoPayDriver=csoPayDriver;
		this.csoIncome=csoIncome;
		this.csoUseIndex=csoUseIndex;
		this.csoFlag=csoFlag;
		this.csoFrom=csoFrom;
		this.csoSrc=csoSrc;
		this.csoUpdateTime=csoUpdateTime;
		this.csoAddTime=csoAddTime;
		this.csoEndTime=csoEndTime;
		this.csoSerial=csoSerial;
		this.csoCode=csoCode;
		this.csoRemark=csoRemark;
		this.csoLog=csoLog;
		this.csoEditor=csoEditor;
		this.csoMask=csoMask;
		this.csoDeviceVesion=csoDeviceVesion;
		this.csoAlarm=csoAlarm;
		this.csoState=csoState;
		this.csoStatus=csoStatus;
	}
	
	//设置非空字段
	public CsOrder setNotNull(Long csoHost,Long csoPayMember,Long csoUseMember,Long csoFeeType,Long csoArea,Long csoOutlets,Long csoOutletsRet,Long csoModel,Long csoCar,Long csoProvid,String csoCarNumber,String csoEvRfid,Date csoStartTime,Date csoFinishTime,Double csoDuration,Double csoFuel,Double csoElectric,Short csoFrom,Date csoUpdateTime,Date csoAddTime,String csoCode,Long csoEditor,Long csoMask,Short csoState,Short csoStatus){
		this.csoHost=csoHost;
		this.csoPayMember=csoPayMember;
		this.csoUseMember=csoUseMember;
		this.csoFeeType=csoFeeType;
		this.csoArea=csoArea;
		this.csoOutlets=csoOutlets;
		this.csoOutletsRet=csoOutletsRet;
		this.csoModel=csoModel;
		this.csoCar=csoCar;
		this.csoProvid=csoProvid;
		this.csoCarNumber=csoCarNumber;
		this.csoEvRfid=csoEvRfid;
		this.csoStartTime=csoStartTime;
		this.csoFinishTime=csoFinishTime;
		this.csoDuration=csoDuration;
		this.csoFuel=csoFuel;
		this.csoElectric=csoElectric;
		this.csoFrom=csoFrom;
		this.csoUpdateTime=csoUpdateTime;
		this.csoAddTime=csoAddTime;
		this.csoCode=csoCode;
		this.csoEditor=csoEditor;
		this.csoMask=csoMask;
		this.csoState=csoState;
		this.csoStatus=csoStatus;
		return this;
	}
	/** 订单编号 [非空]       **/
	public CsOrder csoId(Long csoId){
		this.csoId = csoId;
		this.setSeted(F.csoId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsOrder csoHost(Long csoHost){
		this.csoHost = csoHost;
		this.setSeted(F.csoHost);
		return this;
	}
	/** 支付会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
	public CsOrder csoPayMember(Long csoPayMember){
		this.csoPayMember = csoPayMember;
		this.setSeted(F.csoPayMember);
		return this;
	}
	/** 使用会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
	public CsOrder csoUseMember(Long csoUseMember){
		this.csoUseMember = csoUseMember;
		this.setSeted(F.csoUseMember);
		return this;
	}
	/** 计费类型 [非空] [CsUserType]      **/
	public CsOrder csoFeeType(Long csoFeeType){
		this.csoFeeType = csoFeeType;
		this.setSeted(F.csoFeeType);
		return this;
	}
	/** 网点区域 [非空] [CsArea]      **/
	public CsOrder csoArea(Long csoArea){
		this.csoArea = csoArea;
		this.setSeted(F.csoArea);
		return this;
	}
	/** 取车网点 [非空] [CsOutlets]  {csoHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
	public CsOrder csoOutlets(Long csoOutlets){
		this.csoOutlets = csoOutlets;
		this.setSeted(F.csoOutlets);
		return this;
	}
	/** 还车网点 [非空] [CsOutlets]  {csoHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
	public CsOrder csoOutletsRet(Long csoOutletsRet){
		this.csoOutletsRet = csoOutletsRet;
		this.setSeted(F.csoOutletsRet);
		return this;
	}
	/** 预订车型 [非空] [CsCarModel]      **/
	public CsOrder csoModel(Long csoModel){
		this.csoModel = csoModel;
		this.setSeted(F.csoModel);
		return this;
	}
	/** 预订车辆 [非空] [CsCar]  {csoOutlets}:cscOutlets {csoModel}:cscModel     **/
	public CsOrder csoCar(Long csoCar){
		this.csoCar = csoCar;
		this.setSeted(F.csoCar);
		return this;
	}
	/** 车辆商家 [非空] [CsProvid]      **/
	public CsOrder csoProvid(Long csoProvid){
		this.csoProvid = csoProvid;
		this.setSeted(F.csoProvid);
		return this;
	}
	/** 车牌号码 [非空]       **/
	public CsOrder csoCarNumber(String csoCarNumber){
		this.csoCarNumber = csoCarNumber;
		this.setSeted(F.csoCarNumber);
		return this;
	}
	/** 手机号码        **/
	public CsOrder csoMobile(String csoMobile){
		this.csoMobile = csoMobile;
		this.setSeted(F.csoMobile);
		return this;
	}
	/** EV序列号 [非空]       **/
	public CsOrder csoEvRfid(String csoEvRfid){
		this.csoEvRfid = csoEvRfid;
		this.setSeted(F.csoEvRfid);
		return this;
	}
	/** 宣传车？        **/
	public CsOrder csoIsAdCar(Boolean csoIsAdCar){
		this.csoIsAdCar = csoIsAdCar;
		this.setSeted(F.csoIsAdCar);
		return this;
	}
	/** 订单开始时间 [非空]       **/
	public CsOrder csoStartTime(Date csoStartTime){
		this.csoStartTime = csoStartTime;
		this.setSeted(F.csoStartTime);
		return this;
	}
	/** 订单结束时间 [非空]       **/
	public CsOrder csoFinishTime(Date csoFinishTime){
		this.csoFinishTime = csoFinishTime;
		this.setSeted(F.csoFinishTime);
		return this;
	}
	/** 是否长单        **/
	public CsOrder csoLongOrder(Boolean csoLongOrder){
		this.csoLongOrder = csoLongOrder;
		this.setSeted(F.csoLongOrder);
		return this;
	}
	/** 长单价格     元/月   **/
	public CsOrder csoLongPrice(Double csoLongPrice){
		this.csoLongPrice = csoLongPrice;
		this.setSeted(F.csoLongPrice);
		return this;
	}
	/** 用车方式    0:原借原还 1:A借B还     **/
	public CsOrder csoUseType(Short csoUseType){
		this.csoUseType = csoUseType;
		this.setSeted(F.csoUseType);
		return this;
	}
	/** 实际取车时间        **/
	public CsOrder csoTakeTime(Date csoTakeTime){
		this.csoTakeTime = csoTakeTime;
		this.setSeted(F.csoTakeTime);
		return this;
	}
	/** 实际还车时间        **/
	public CsOrder csoRetTime(Date csoRetTime){
		this.csoRetTime = csoRetTime;
		this.setSeted(F.csoRetTime);
		return this;
	}
	/** 订单时长 [非空]       **/
	public CsOrder csoDuration(Double csoDuration){
		this.csoDuration = csoDuration;
		this.setSeted(F.csoDuration);
		return this;
	}
	/** 燃油里程 [非空]       **/
	public CsOrder csoFuel(Double csoFuel){
		this.csoFuel = csoFuel;
		this.setSeted(F.csoFuel);
		return this;
	}
	/** 电里程 [非空]       **/
	public CsOrder csoElectric(Double csoElectric){
		this.csoElectric = csoElectric;
		this.setSeted(F.csoElectric);
		return this;
	}
	/** 使用信用卡  [CsCreditCard]  {csoPayMember}:csccMember     **/
	public CsOrder csoCreditCard(Long csoCreditCard){
		this.csoCreditCard = csoCreditCard;
		this.setSeted(F.csoCreditCard);
		return this;
	}
	/** 信用卡帐单  [CsCreditBill]  {csoPayMember}:cscbMember     **/
	public CsOrder csoCreditBill(Long csoCreditBill){
		this.csoCreditBill = csoCreditBill;
		this.setSeted(F.csoCreditBill);
		return this;
	}
	/** 保险类型  [CsProduct]     select * from cs_product where csp_flag='INSURE' or csp_flag='SECURE' **/
	public CsOrder csoInsureType(Long csoInsureType){
		this.csoInsureType = csoInsureType;
		this.setSeted(F.csoInsureType);
		return this;
	}
	/** 免费小时        **/
	public CsOrder csoFreehour(Double csoFreehour){
		this.csoFreehour = csoFreehour;
		this.setSeted(F.csoFreehour);
		return this;
	}
	/** 折扣     0到1,比如0.75表示七五折   **/
	public CsOrder csoRebate(Double csoRebate){
		this.csoRebate = csoRebate;
		this.setSeted(F.csoRebate);
		return this;
	}
	/** 押金/保证金        **/
	public CsOrder csoMarginNeed(Double csoMarginNeed){
		this.csoMarginNeed = csoMarginNeed;
		this.setSeted(F.csoMarginNeed);
		return this;
	}
	/** 预计/里程费        **/
	public CsOrder csoPredict(Double csoPredict){
		this.csoPredict = csoPredict;
		this.setSeted(F.csoPredict);
		return this;
	}
	/** 应付金额     应付为当前状态下的预计理论金额   **/
	public CsOrder csoPayNeed(Double csoPayNeed){
		this.csoPayNeed = csoPayNeed;
		this.setSeted(F.csoPayNeed);
		return this;
	}
	/** 实付金额        **/
	public CsOrder csoPayReal(Double csoPayReal){
		this.csoPayReal = csoPayReal;
		this.setSeted(F.csoPayReal);
		return this;
	}
	/** 保证金支付        **/
	public CsOrder csoPayMoney(Double csoPayMoney){
		this.csoPayMoney = csoPayMoney;
		this.setSeted(F.csoPayMoney);
		return this;
	}
	/** 现金券支付        **/
	public CsOrder csoPayCoupon(Double csoPayCoupon){
		this.csoPayCoupon = csoPayCoupon;
		this.setSeted(F.csoPayCoupon);
		return this;
	}
	/** 红包支付        **/
	public CsOrder csoPayCoin(Double csoPayCoin){
		this.csoPayCoin = csoPayCoin;
		this.setSeted(F.csoPayCoin);
		return this;
	}
	/** 电里程费        **/
	public CsOrder csoPayKilom(Double csoPayKilom){
		this.csoPayKilom = csoPayKilom;
		this.setSeted(F.csoPayKilom);
		return this;
	}
	/** 油里程费        **/
	public CsOrder csoPayMileage(Double csoPayMileage){
		this.csoPayMileage = csoPayMileage;
		this.setSeted(F.csoPayMileage);
		return this;
	}
	/** 租金费用        **/
	public CsOrder csoPayRent(Double csoPayRent){
		this.csoPayRent = csoPayRent;
		this.setSeted(F.csoPayRent);
		return this;
	}
	/** 免责费用        **/
	public CsOrder csoPayInsure(Double csoPayInsure){
		this.csoPayInsure = csoPayInsure;
		this.setSeted(F.csoPayInsure);
		return this;
	}
	/** 超时费用        **/
	public CsOrder csoPayTimeout(Double csoPayTimeout){
		this.csoPayTimeout = csoPayTimeout;
		this.setSeted(F.csoPayTimeout);
		return this;
	}
	/** 陪驾费用        **/
	public CsOrder csoPayDriver(Double csoPayDriver){
		this.csoPayDriver = csoPayDriver;
		this.setSeted(F.csoPayDriver);
		return this;
	}
	/** 计入营收    0:不计营收 1:计入营收     **/
	public CsOrder csoIncome(Short csoIncome){
		this.csoIncome = csoIncome;
		this.setSeted(F.csoIncome);
		return this;
	}
	/** 订单次数     标明该订单是会员第几次用车   **/
	public CsOrder csoUseIndex(Integer csoUseIndex){
		this.csoUseIndex = csoUseIndex;
		this.setSeted(F.csoUseIndex);
		return this;
	}
	/** 类型标识    0:默认 1:2014春节订单 2:2015春节订单     **/
	public CsOrder csoFlag(Short csoFlag){
		this.csoFlag = csoFlag;
		this.setSeted(F.csoFlag);
		return this;
	}
	/** 订单来源 [非空]   0:网站预订 1:后台预订 2:微信预订 3:App预订 4:企业预订 5:支付宝预订     **/
	public CsOrder csoFrom(Short csoFrom){
		this.csoFrom = csoFrom;
		this.setSeted(F.csoFrom);
		return this;
	}
	/** 来源信息        **/
	public CsOrder csoSrc(String csoSrc){
		this.csoSrc = csoSrc;
		this.setSeted(F.csoSrc);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsOrder csoUpdateTime(Date csoUpdateTime){
		this.csoUpdateTime = csoUpdateTime;
		this.setSeted(F.csoUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsOrder csoAddTime(Date csoAddTime){
		this.csoAddTime = csoAddTime;
		this.setSeted(F.csoAddTime);
		return this;
	}
	/** 结束时间        **/
	public CsOrder csoEndTime(Date csoEndTime){
		this.csoEndTime = csoEndTime;
		this.setSeted(F.csoEndTime);
		return this;
	}
	/** 序列号        **/
	public CsOrder csoSerial(String csoSerial){
		this.csoSerial = csoSerial;
		this.setSeted(F.csoSerial);
		return this;
	}
	/** 授权码 [非空]       **/
	public CsOrder csoCode(String csoCode){
		this.csoCode = csoCode;
		this.setSeted(F.csoCode);
		return this;
	}
	/** 订单备注        **/
	public CsOrder csoRemark(String csoRemark){
		this.csoRemark = csoRemark;
		this.setSeted(F.csoRemark);
		return this;
	}
	/** 历史日志        **/
	public CsOrder csoLog(String csoLog){
		this.csoLog = csoLog;
		this.setSeted(F.csoLog);
		return this;
	}
	/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsOrder csoEditor(Long csoEditor){
		this.csoEditor = csoEditor;
		this.setSeted(F.csoEditor);
		return this;
	}
	/** 程序掩码 [非空]   1:催还车短信已发 2:设置不发还车短信     **/
	public CsOrder csoMask(Long csoMask){
		this.csoMask = csoMask;
		this.setSeted(F.csoMask);
		return this;
	}
	/** 下位机版本    0:默认 1:测试     **/
	public CsOrder csoDeviceVesion(Short csoDeviceVesion){
		this.csoDeviceVesion = csoDeviceVesion;
		this.setSeted(F.csoDeviceVesion);
		return this;
	}
	/** 报警    0:默认 1:超距 2:超时 3:断线     **/
	public CsOrder csoAlarm(Short csoAlarm){
		this.csoAlarm = csoAlarm;
		this.setSeted(F.csoAlarm);
		return this;
	}
	/** 数据状态 [非空]   0:未发送 1:已发送 2:已收到 3:不发送     **/
	public CsOrder csoState(Short csoState){
		this.csoState = csoState;
		this.setSeted(F.csoState);
		return this;
	}
	/** 状态 [非空]   0:已预定 1:使用中 2:已还车 3:已取消 4:已完成 5:待处理 6:已退款 7:已作废     **/
	public CsOrder csoStatus(Short csoStatus){
		this.csoStatus = csoStatus;
		this.setSeted(F.csoStatus);
		return this;
	}
	
	/** 取消来源 1：后台 3:app [可空]       **/
	public CsOrder csoCancelFrom(Short csoCancelFrom){
		this.csoCancelFrom = csoCancelFrom;
		this.setSeted(F.csoCancelFrom);
		return this;
	}
	//克隆对象
	public CsOrder clone(){
		CsOrder clone = new CsOrder();
		clone.csoHost=this.csoHost;
		clone.csoPayMember=this.csoPayMember;
		clone.csoUseMember=this.csoUseMember;
		clone.csoFeeType=this.csoFeeType;
		clone.csoArea=this.csoArea;
		clone.csoOutlets=this.csoOutlets;
		clone.csoOutletsRet=this.csoOutletsRet;
		clone.csoModel=this.csoModel;
		clone.csoCar=this.csoCar;
		clone.csoProvid=this.csoProvid;
		clone.csoCarNumber=this.csoCarNumber;
		clone.csoEvRfid=this.csoEvRfid;
		clone.csoStartTime=this.csoStartTime;
		clone.csoFinishTime=this.csoFinishTime;
		clone.csoDuration=this.csoDuration;
		clone.csoFuel=this.csoFuel;
		clone.csoElectric=this.csoElectric;
		clone.csoFrom=this.csoFrom;
		clone.csoUpdateTime=this.csoUpdateTime;
		clone.csoAddTime=this.csoAddTime;
		clone.csoCode=this.csoCode;
		clone.csoEditor=this.csoEditor;
		clone.csoMask=this.csoMask;
		clone.csoState=this.csoState;
		clone.csoStatus=this.csoStatus;
		clone.csoCancelFrom=this.csoCancelFrom;
		return clone;
	}
	
	
	/**
	 * 根据ID取系统订单
	 * @param id
	 * @return
	 */
	public static @api CsOrder get(Long id){		
		return getCsOrderById(id);
	}
	/**
	 * 获取所有系统订单
	 * @return
	 */
	public static @api List<CsOrder> list(Map params,Integer size){
		return getCsOrderList(params,size);
	}
	/**
	 * 获取系统订单分页
	 * @return
	 */
	public static @api Page<CsOrder> page(int page,int size,Map params){
		return getCsOrderPage(page, size , params);
	}
	/**
	 * 根据查询条件取系统订单
	 * @param params
	 * @return
	 */
	public static @api CsOrder Get(Map params){
		return getCsOrder(params);
	}
	/**
	 * 获取系统订单数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsOrderCount(params);
	}
	/**
	 * 获取系统订单数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsOrderEval(eval,params);
	}
	
	/**
	 * 根据ID取系统订单
	 * @param id
	 * @return
	 */
	public static @api CsOrder getCsOrderById(Long id){		
		CsOrder csOrder = (CsOrder) $.GetRequest("CsOrder$"+id);
		if(csOrder!=null)
			return csOrder;
		com.ccclubs.service.admin.ICsOrderService csOrderService = $.GetSpringBean("csOrderService");		
		return csOrderService.getCsOrderById(id);
	}
	
	
	/**
	 * 根据ID取系统订单的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsOrder.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsOrder csOrder = get(id);
		if(csOrder!=null){
			String strValue = csOrder.getCsoId$();
			if(!"CsoId".equals("CsoId"))
				strValue+="("+csOrder.getCsoId$()+")";
			if(!"CsoId".equals("CsoStatus"))
				strValue+="("+csOrder.getCsoStatus$()+")";
			MemCache.setValue(CsOrder.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsoId$();
		if(!"CsoId".equals("CsoId"))
			keyValue+="("+this.getCsoId$()+")";
		if(!"CsoId".equals("CsoStatus"))
			keyValue+="("+this.getCsoStatus$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有系统订单
	 * @return
	 */
	public static @api List<CsOrder> getCsOrderList(Map params,Integer size){
		com.ccclubs.service.admin.ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
		return csOrderService.getCsOrderList(params, size);
	}
	
	/**
	 * 获取系统订单分页
	 * @return
	 */
	public static @api Page<CsOrder> getCsOrderPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
		return csOrderService.getCsOrderPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取系统订单
	 * @param params
	 * @return
	 */
	public static @api CsOrder getCsOrder(Map params){
		com.ccclubs.service.admin.ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
		return csOrderService.getCsOrder(params);
	}
	
	/**
	 * 获取系统订单数
	 * @return
	 */
	public static @api Long getCsOrderCount(Map params){
		com.ccclubs.service.admin.ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
		return csOrderService.getCsOrderCount(params);
	}
		
		
	/**
	 * 获取系统订单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsOrderEval(String eval,Map params){
		com.ccclubs.service.admin.ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
		return csOrderService.getCsOrderEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsOrder(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
		csOrderService.updateCsOrderByConfirm(set, where);
	}
	
	
	/**
	 * 保存系统订单对象
	 * @param params
	 * @return
	 */
	public CsOrder save(){
		com.ccclubs.service.admin.ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
		if(this.getCsoId()!=null)
			csOrderService.updateCsOrder(this);
		else
			return csOrderService.saveCsOrder(this);
		return this;
	}
	
	
	/**
	 * 更新系统订单对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
		csOrderService.updateCsOrder$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
		if($.equals($.config("logic_delete"),"true"))
			csOrderService.removeCsOrderById(this.getCsoId());
		else
			csOrderService.deleteCsOrderById(this.getCsoId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
		return csOrderService.executeTransaction(function);
	}
	/*******************************订单编号**********************************/	
	/**
	* 订单编号 [非空]      
	**/
	public Long getCsoId(){
		return this.csoId;
	}
	/**
	* 获取订单编号格式化(toString)
	**/
	public String getCsoId$(){
		String strValue="";
		 strValue=$.str(this.getCsoId());
	 	 return strValue;
	}
	/**
	* 订单编号 [非空]      
	**/
	public void setCsoId(Long csoId){
		this.csoId = csoId;
		this.setSeted(F.csoId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsoHost(){
		return this.csoHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsoHost$(){
		String strValue="";
		if(this.getCsoHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsoHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsoHost(Long csoHost){
		this.csoHost = csoHost;
		this.setSeted(F.csoHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csoHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsoHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsoHost()!=null){
 			srvHost = SrvHost.get(this.getCsoHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsoHost(), srvHost);
	 	return srvHost;
	}
	/*******************************支付会员**********************************/	
	/**
	* 支付会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public Long getCsoPayMember(){
		return this.csoPayMember;
	}
	/**
	* 获取支付会员格式化(toString)
	**/
	public String getCsoPayMember$(){
		String strValue="";
		if(this.getCsoPayMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsoPayMember()));
		}			
	 	 return strValue;
	}
	/**
	* 支付会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public void setCsoPayMember(Long csoPayMember){
		this.csoPayMember = csoPayMember;
		this.setSeted(F.csoPayMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csoPayMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsoPayMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsoPayMember()!=null){
 			csMember = CsMember.get(this.getCsoPayMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsoPayMember(), csMember);
	 	return csMember;
	}
	/*******************************使用会员**********************************/	
	/**
	* 使用会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public Long getCsoUseMember(){
		return this.csoUseMember;
	}
	/**
	* 获取使用会员格式化(toString)
	**/
	public String getCsoUseMember$(){
		String strValue="";
		if(this.getCsoUseMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsoUseMember()));
		}			
	 	 return strValue;
	}
	/**
	* 使用会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public void setCsoUseMember(Long csoUseMember){
		this.csoUseMember = csoUseMember;
		this.setSeted(F.csoUseMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csoUseMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsoUseMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsoUseMember()!=null){
 			csMember = CsMember.get(this.getCsoUseMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsoUseMember(), csMember);
	 	return csMember;
	}
	/*******************************反向关联的订单操作记录列表**********************************/	
	/**
	* 获取与当前对象反向关联的订单操作记录列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsOrderLog> getLogs(){
		com.ccclubs.service.admin.ICsOrderLogService csOrderLogService = $.GetSpringBean("csOrderLogService");
		Map params = new HashMap();
		params.put("csolOrder",this.getCsoId());
		return csOrderLogService.getCsOrderLogList(params,-1);
	}
	/**
	* 获取操作记录项格式化(toString)
	**/
	public String getLogs$(){
		return "[...]";
	}
	/*******************************计费类型**********************************/	
	/**
	* 计费类型 [非空] [CsUserType]     
	**/
	public Long getCsoFeeType(){
		return this.csoFeeType;
	}
	/**
	* 获取计费类型格式化(toString)
	**/
	public String getCsoFeeType$(){
		String strValue="";
		if(this.getCsoFeeType()!=null){
				strValue+=$.str(CsUserType.getKeyValue(this.getCsoFeeType()));
		}			
	 	 return strValue;
	}
	/**
	* 计费类型 [非空] [CsUserType]     
	**/
	public void setCsoFeeType(Long csoFeeType){
		this.csoFeeType = csoFeeType;
		this.setSeted(F.csoFeeType);
	}
	/**
	* 获取关联对象[计费方式]
	**/	 			
 	public CsUserType get$csoFeeType(){
 		com.ccclubs.model.CsUserType csUserType = (com.ccclubs.model.CsUserType) $.GetRequest("CsUserType$"+this.getCsoFeeType());
 		if(csUserType!=null)
			return csUserType;
		if(this.getCsoFeeType()!=null){
 			csUserType = CsUserType.get(this.getCsoFeeType());
 		}
 		$.SetRequest("CsUserType$"+this.getCsoFeeType(), csUserType);
	 	return csUserType;
	}
	/*******************************网点区域**********************************/	
	/**
	* 网点区域 [非空] [CsArea]     
	**/
	public Long getCsoArea(){
		return this.csoArea;
	}
	/**
	* 获取网点区域格式化(toString)
	**/
	public String getCsoArea$(){
		String strValue="";
		if(this.getCsoArea()!=null){
				strValue+=$.str(CsArea.getKeyValue(this.getCsoArea()));
		}			
	 	 return strValue;
	}
	/**
	* 网点区域 [非空] [CsArea]     
	**/
	public void setCsoArea(Long csoArea){
		this.csoArea = csoArea;
		this.setSeted(F.csoArea);
	}
	/**
	* 获取关联对象[网点区域]
	**/	 			
 	public CsArea get$csoArea(){
 		com.ccclubs.model.CsArea csArea = (com.ccclubs.model.CsArea) $.GetRequest("CsArea$"+this.getCsoArea());
 		if(csArea!=null)
			return csArea;
		if(this.getCsoArea()!=null){
 			csArea = CsArea.get(this.getCsoArea());
 		}
 		$.SetRequest("CsArea$"+this.getCsoArea(), csArea);
	 	return csArea;
	}
	/*******************************取车网点**********************************/	
	/**
	* 取车网点 [非空] [CsOutlets]  {csoHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param}
	**/
	public Long getCsoOutlets(){
		return this.csoOutlets;
	}
	/**
	* 获取取车网点格式化(toString)
	**/
	public String getCsoOutlets$(){
		String strValue="";
		if(this.getCsoOutlets()!=null){
				strValue+=$.str(CsOutlets.getKeyValue(this.getCsoOutlets()));
		}			
	 	 return strValue;
	}
	/**
	* 取车网点 [非空] [CsOutlets]  {csoHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param}
	**/
	public void setCsoOutlets(Long csoOutlets){
		this.csoOutlets = csoOutlets;
		this.setSeted(F.csoOutlets);
	}
	/**
	* 获取关联对象[网点]
	**/	 			
 	public CsOutlets get$csoOutlets(){
 		com.ccclubs.model.CsOutlets csOutlets = (com.ccclubs.model.CsOutlets) $.GetRequest("CsOutlets$"+this.getCsoOutlets());
 		if(csOutlets!=null)
			return csOutlets;
		if(this.getCsoOutlets()!=null){
 			csOutlets = CsOutlets.get(this.getCsoOutlets());
 		}
 		$.SetRequest("CsOutlets$"+this.getCsoOutlets(), csOutlets);
	 	return csOutlets;
	}
	/*******************************还车网点**********************************/	
	/**
	* 还车网点 [非空] [CsOutlets]  {csoHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param}
	**/
	public Long getCsoOutletsRet(){
		return this.csoOutletsRet;
	}
	/**
	* 获取还车网点格式化(toString)
	**/
	public String getCsoOutletsRet$(){
		String strValue="";
		if(this.getCsoOutletsRet()!=null){
				strValue+=$.str(CsOutlets.getKeyValue(this.getCsoOutletsRet()));
		}			
	 	 return strValue;
	}
	/**
	* 还车网点 [非空] [CsOutlets]  {csoHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param}
	**/
	public void setCsoOutletsRet(Long csoOutletsRet){
		this.csoOutletsRet = csoOutletsRet;
		this.setSeted(F.csoOutletsRet);
	}
	/**
	* 获取关联对象[网点]
	**/	 			
 	public CsOutlets get$csoOutletsRet(){
 		com.ccclubs.model.CsOutlets csOutlets = (com.ccclubs.model.CsOutlets) $.GetRequest("CsOutlets$"+this.getCsoOutletsRet());
 		if(csOutlets!=null)
			return csOutlets;
		if(this.getCsoOutletsRet()!=null){
 			csOutlets = CsOutlets.get(this.getCsoOutletsRet());
 		}
 		$.SetRequest("CsOutlets$"+this.getCsoOutletsRet(), csOutlets);
	 	return csOutlets;
	}
	/*******************************预订车型**********************************/	
	/**
	* 预订车型 [非空] [CsCarModel]     
	**/
	public Long getCsoModel(){
		return this.csoModel;
	}
	/**
	* 获取预订车型格式化(toString)
	**/
	public String getCsoModel$(){
		String strValue="";
		if(this.getCsoModel()!=null){
				strValue+=$.str(CsCarModel.getKeyValue(this.getCsoModel()));
		}			
	 	 return strValue;
	}
	/**
	* 预订车型 [非空] [CsCarModel]     
	**/
	public void setCsoModel(Long csoModel){
		this.csoModel = csoModel;
		this.setSeted(F.csoModel);
	}
	/**
	* 获取关联对象[车型]
	**/	 			
 	public CsCarModel get$csoModel(){
 		com.ccclubs.model.CsCarModel csCarModel = (com.ccclubs.model.CsCarModel) $.GetRequest("CsCarModel$"+this.getCsoModel());
 		if(csCarModel!=null)
			return csCarModel;
		if(this.getCsoModel()!=null){
 			csCarModel = CsCarModel.get(this.getCsoModel());
 		}
 		$.SetRequest("CsCarModel$"+this.getCsoModel(), csCarModel);
	 	return csCarModel;
	}
	/*******************************预订车辆**********************************/	
	/**
	* 预订车辆 [非空] [CsCar]  {csoOutlets}:cscOutlets {csoModel}:cscModel    
	**/
	public Long getCsoCar(){
		return this.csoCar;
	}
	/**
	* 获取预订车辆格式化(toString)
	**/
	public String getCsoCar$(){
		String strValue="";
		if(this.getCsoCar()!=null){
				strValue+=$.str(CsCar.getKeyValue(this.getCsoCar()));
		}			
	 	 return strValue;
	}
	/**
	* 预订车辆 [非空] [CsCar]  {csoOutlets}:cscOutlets {csoModel}:cscModel    
	**/
	public void setCsoCar(Long csoCar){
		this.csoCar = csoCar;
		this.setSeted(F.csoCar);
	}
	/**
	* 获取关联对象[车辆]
	**/	 			
 	public CsCar get$csoCar(){
 		com.ccclubs.model.CsCar csCar = (com.ccclubs.model.CsCar) $.GetRequest("CsCar$"+this.getCsoCar());
 		if(csCar!=null)
			return csCar;
		if(this.getCsoCar()!=null){
 			csCar = CsCar.get(this.getCsoCar());
 		}
 		$.SetRequest("CsCar$"+this.getCsoCar(), csCar);
	 	return csCar;
	}
	/*******************************车辆商家**********************************/	
	/**
	* 车辆商家 [非空] [CsProvid]     
	**/
	public Long getCsoProvid(){
		return this.csoProvid;
	}
	/**
	* 获取车辆商家格式化(toString)
	**/
	public String getCsoProvid$(){
		String strValue="";
		if(this.getCsoProvid()!=null){
				strValue+=$.str(CsProvid.getKeyValue(this.getCsoProvid()));
		}			
	 	 return strValue;
	}
	/**
	* 车辆商家 [非空] [CsProvid]     
	**/
	public void setCsoProvid(Long csoProvid){
		this.csoProvid = csoProvid;
		this.setSeted(F.csoProvid);
	}
	/**
	* 获取关联对象[供应商]
	**/	 			
 	public CsProvid get$csoProvid(){
 		com.ccclubs.model.CsProvid csProvid = (com.ccclubs.model.CsProvid) $.GetRequest("CsProvid$"+this.getCsoProvid());
 		if(csProvid!=null)
			return csProvid;
		if(this.getCsoProvid()!=null){
 			csProvid = CsProvid.get(this.getCsoProvid());
 		}
 		$.SetRequest("CsProvid$"+this.getCsoProvid(), csProvid);
	 	return csProvid;
	}
	/*******************************车牌号码**********************************/	
	/**
	* 车牌号码 [非空]      
	**/
	public String getCsoCarNumber(){
		return this.csoCarNumber;
	}
	/**
	* 获取车牌号码格式化(toString)
	**/
	public String getCsoCarNumber$(){
		String strValue="";
		 strValue=$.str(this.getCsoCarNumber());
	 	 return strValue;
	}
	/**
	* 车牌号码 [非空]      
	**/
	public void setCsoCarNumber(String csoCarNumber){
		this.csoCarNumber = csoCarNumber;
		this.setSeted(F.csoCarNumber);
	}
	/*******************************手机号码**********************************/	
	/**
	* 手机号码       
	**/
	public String getCsoMobile(){
		return this.csoMobile;
	}
	/**
	* 获取手机号码格式化(toString)
	**/
	public String getCsoMobile$(){
		String strValue="";
		 strValue=$.str(this.getCsoMobile());
	 	 return strValue;
	}
	/**
	* 手机号码       
	**/
	public void setCsoMobile(String csoMobile){
		this.csoMobile = csoMobile;
		this.setSeted(F.csoMobile);
	}
	/*******************************EV序列号**********************************/	
	/**
	* EV序列号 [非空]      
	**/
	public String getCsoEvRfid(){
		return this.csoEvRfid;
	}
	/**
	* 获取EV序列号格式化(toString)
	**/
	public String getCsoEvRfid$(){
		String strValue="";
		 strValue=$.str(this.getCsoEvRfid());
	 	 return strValue;
	}
	/**
	* EV序列号 [非空]      
	**/
	public void setCsoEvRfid(String csoEvRfid){
		this.csoEvRfid = csoEvRfid;
		this.setSeted(F.csoEvRfid);
	}
	/*******************************宣传车？**********************************/	
	/**
	* 宣传车？       
	**/
	public Boolean getCsoIsAdCar(){
		return this.csoIsAdCar;
	}
	/**
	* 获取宣传车？格式化(toString)
	**/
	public String getCsoIsAdCar$(){
		String strValue="";
		 strValue=$.str((this.getCsoIsAdCar()!=null && this.getCsoIsAdCar())?"是":"否");					 	
	 	 return strValue;
	}
	/**
	* 宣传车？       
	**/
	public void setCsoIsAdCar(Boolean csoIsAdCar){
		this.csoIsAdCar = csoIsAdCar;
		this.setSeted(F.csoIsAdCar);
	}
	/*******************************订单开始时间**********************************/	
	/**
	* 订单开始时间 [非空]      
	**/
	public Date getCsoStartTime(){
		return this.csoStartTime;
	}
	/**
	* 获取订单开始时间格式化(toString)
	**/
	public String getCsoStartTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsoStartTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 订单开始时间 [非空]      
	**/
	public void setCsoStartTime(Date csoStartTime){
		this.csoStartTime = csoStartTime;
		this.setSeted(F.csoStartTime);
	}
	/*******************************订单结束时间**********************************/	
	/**
	* 订单结束时间 [非空]      
	**/
	public Date getCsoFinishTime(){
		return this.csoFinishTime;
	}
	/**
	* 获取订单结束时间格式化(toString)
	**/
	public String getCsoFinishTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsoFinishTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 订单结束时间 [非空]      
	**/
	public void setCsoFinishTime(Date csoFinishTime){
		this.csoFinishTime = csoFinishTime;
		this.setSeted(F.csoFinishTime);
	}
	/*******************************是否长单**********************************/	
	/**
	* 是否长单       
	**/
	public Boolean getCsoLongOrder(){
		return this.csoLongOrder;
	}
	/**
	* 获取是否长单格式化(toString)
	**/
	public String getCsoLongOrder$(){
		String strValue="";
		 strValue=$.str((this.getCsoLongOrder()!=null && this.getCsoLongOrder())?"是":"否");					 	
	 	 return strValue;
	}
	/**
	* 是否长单       
	**/
	public void setCsoLongOrder(Boolean csoLongOrder){
		this.csoLongOrder = csoLongOrder;
		this.setSeted(F.csoLongOrder);
	}
	/*******************************长单价格**********************************/	
	/**
	* 长单价格     元/月  
	**/
	public Double getCsoLongPrice(){
		return this.csoLongPrice;
	}
	/**
	* 获取长单价格格式化(toString)
	**/
	public String getCsoLongPrice$(){
		String strValue="";
		 strValue=$.str(this.getCsoLongPrice());	
	 	 return strValue;
	}
	/**
	* 长单价格     元/月  
	**/
	public void setCsoLongPrice(Double csoLongPrice){
		this.csoLongPrice = csoLongPrice;
		this.setSeted(F.csoLongPrice);
	}
	/*******************************用车方式**********************************/	
	/**
	* 用车方式    0:原借原还 1:A借B还    
	**/
	public Short getCsoUseType(){
		return this.csoUseType;
	}
	/**
	* 获取用车方式格式化(toString)
	**/
	public String getCsoUseType$(){
		String strValue="";
		 if($.equals($.str(this.getCsoUseType()),"0"))
			strValue=$.str("原借原还");		 
		 if($.equals($.str(this.getCsoUseType()),"1"))
			strValue=$.str("A借B还");		 
	 	 return strValue;
	}
	/**
	* 用车方式    0:原借原还 1:A借B还    
	**/
	public void setCsoUseType(Short csoUseType){
		this.csoUseType = csoUseType;
		this.setSeted(F.csoUseType);
	}
	/*******************************实际取车时间**********************************/	
	/**
	* 实际取车时间       
	**/
	public Date getCsoTakeTime(){
		return this.csoTakeTime;
	}
	/**
	* 获取实际取车时间格式化(toString)
	**/
	public String getCsoTakeTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsoTakeTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 实际取车时间       
	**/
	public void setCsoTakeTime(Date csoTakeTime){
		this.csoTakeTime = csoTakeTime;
		this.setSeted(F.csoTakeTime);
	}
	/*******************************实际还车时间**********************************/	
	/**
	* 实际还车时间       
	**/
	public Date getCsoRetTime(){
		return this.csoRetTime;
	}
	/**
	* 获取实际还车时间格式化(toString)
	**/
	public String getCsoRetTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsoRetTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 实际还车时间       
	**/
	public void setCsoRetTime(Date csoRetTime){
		this.csoRetTime = csoRetTime;
		this.setSeted(F.csoRetTime);
	}
	/*******************************订单时长**********************************/	
	/**
	* 订单时长 [非空]      
	**/
	public Double getCsoDuration(){
		return this.csoDuration;
	}
	/**
	* 获取订单时长格式化(toString)
	**/
	public String getCsoDuration$(){
		String strValue="";
		 strValue=$.str(this.getCsoDuration());	
	 	 return strValue;
	}
	/**
	* 订单时长 [非空]      
	**/
	public void setCsoDuration(Double csoDuration){
		this.csoDuration = csoDuration;
		this.setSeted(F.csoDuration);
	}
	/*******************************燃油里程**********************************/	
	/**
	* 燃油里程 [非空]      
	**/
	public Double getCsoFuel(){
		return this.csoFuel;
	}
	/**
	* 获取燃油里程格式化(toString)
	**/
	public String getCsoFuel$(){
		String strValue="";
		 strValue=$.str(this.getCsoFuel());	
	 	 return strValue;
	}
	/**
	* 燃油里程 [非空]      
	**/
	public void setCsoFuel(Double csoFuel){
		this.csoFuel = csoFuel;
		this.setSeted(F.csoFuel);
	}
	/*******************************电里程**********************************/	
	/**
	* 电里程 [非空]      
	**/
	public Double getCsoElectric(){
		return this.csoElectric;
	}
	/**
	* 获取电里程格式化(toString)
	**/
	public String getCsoElectric$(){
		String strValue="";
		 strValue=$.str(this.getCsoElectric());	
	 	 return strValue;
	}
	/**
	* 电里程 [非空]      
	**/
	public void setCsoElectric(Double csoElectric){
		this.csoElectric = csoElectric;
		this.setSeted(F.csoElectric);
	}
	/*******************************使用信用卡**********************************/	
	/**
	* 使用信用卡  [CsCreditCard]  {csoPayMember}:csccMember    
	**/
	public Long getCsoCreditCard(){
		return this.csoCreditCard;
	}
	/**
	* 获取使用信用卡格式化(toString)
	**/
	public String getCsoCreditCard$(){
		String strValue="";
		if(this.getCsoCreditCard()!=null){
				strValue+=$.str(CsCreditCard.getKeyValue(this.getCsoCreditCard()));
		}			
	 	 return strValue;
	}
	/**
	* 使用信用卡  [CsCreditCard]  {csoPayMember}:csccMember    
	**/
	public void setCsoCreditCard(Long csoCreditCard){
		this.csoCreditCard = csoCreditCard;
		this.setSeted(F.csoCreditCard);
	}
	/**
	* 获取关联对象[信用卡]
	**/	 			
 	public CsCreditCard get$csoCreditCard(){
 		com.ccclubs.model.CsCreditCard csCreditCard = (com.ccclubs.model.CsCreditCard) $.GetRequest("CsCreditCard$"+this.getCsoCreditCard());
 		if(csCreditCard!=null)
			return csCreditCard;
		if(this.getCsoCreditCard()!=null){
 			csCreditCard = CsCreditCard.get(this.getCsoCreditCard());
 		}
 		$.SetRequest("CsCreditCard$"+this.getCsoCreditCard(), csCreditCard);
	 	return csCreditCard;
	}
	/*******************************信用卡帐单**********************************/	
	/**
	* 信用卡帐单  [CsCreditBill]  {csoPayMember}:cscbMember    
	**/
	public Long getCsoCreditBill(){
		return this.csoCreditBill;
	}
	/**
	* 获取信用卡帐单格式化(toString)
	**/
	public String getCsoCreditBill$(){
		String strValue="";
		if(this.getCsoCreditBill()!=null){
				strValue+=$.str(CsCreditBill.getKeyValue(this.getCsoCreditBill()));
		}			
	 	 return strValue;
	}
	/**
	* 信用卡帐单  [CsCreditBill]  {csoPayMember}:cscbMember    
	**/
	public void setCsoCreditBill(Long csoCreditBill){
		this.csoCreditBill = csoCreditBill;
		this.setSeted(F.csoCreditBill);
	}
	/**
	* 获取关联对象[信用账单]
	**/	 			
 	public CsCreditBill get$csoCreditBill(){
 		com.ccclubs.model.CsCreditBill csCreditBill = (com.ccclubs.model.CsCreditBill) $.GetRequest("CsCreditBill$"+this.getCsoCreditBill());
 		if(csCreditBill!=null)
			return csCreditBill;
		if(this.getCsoCreditBill()!=null){
 			csCreditBill = CsCreditBill.get(this.getCsoCreditBill());
 		}
 		$.SetRequest("CsCreditBill$"+this.getCsoCreditBill(), csCreditBill);
	 	return csCreditBill;
	}
	/*******************************保险类型**********************************/	
	/**
	* 保险类型  [CsProduct]     select * from cs_product where csp_flag='INSURE' or csp_flag='SECURE'
	**/
	public Long getCsoInsureType(){
		return this.csoInsureType;
	}
	/**
	* 获取保险类型格式化(toString)
	**/
	public String getCsoInsureType$(){
		String strValue="";
		if(this.getCsoInsureType()!=null){
				strValue+=$.str(CsProduct.getKeyValue(this.getCsoInsureType()));
		}			
	 	 return strValue;
	}
	/**
	* 保险类型  [CsProduct]     select * from cs_product where csp_flag='INSURE' or csp_flag='SECURE'
	**/
	public void setCsoInsureType(Long csoInsureType){
		this.csoInsureType = csoInsureType;
		this.setSeted(F.csoInsureType);
	}
	/**
	* 获取关联对象[产品]
	**/	 			
 	public CsProduct get$csoInsureType(){
 		com.ccclubs.model.CsProduct csProduct = (com.ccclubs.model.CsProduct) $.GetRequest("CsProduct$"+this.getCsoInsureType());
 		if(csProduct!=null)
			return csProduct;
		if(this.getCsoInsureType()!=null){
 			csProduct = CsProduct.get(this.getCsoInsureType());
 		}
 		$.SetRequest("CsProduct$"+this.getCsoInsureType(), csProduct);
	 	return csProduct;
	}
	/*******************************免费小时**********************************/	
	/**
	* 免费小时       
	**/
	public Double getCsoFreehour(){
		return this.csoFreehour;
	}
	/**
	* 获取免费小时格式化(toString)
	**/
	public String getCsoFreehour$(){
		String strValue="";
		 strValue=$.str(this.getCsoFreehour());	
	 	 return strValue;
	}
	/**
	* 免费小时       
	**/
	public void setCsoFreehour(Double csoFreehour){
		this.csoFreehour = csoFreehour;
		this.setSeted(F.csoFreehour);
	}
	/*******************************反向关联的订单明细列表**********************************/	
	/**
	* 获取与当前对象反向关联的订单明细列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsOrderDetail> getCsoCostDetails(){
		com.ccclubs.service.admin.ICsOrderDetailService csOrderDetailService = $.GetSpringBean("csOrderDetailService");
		Map params = new HashMap();
		params.put("csodOrder",this.getCsoId());
		return csOrderDetailService.getCsOrderDetailList(params,-1);
	}
	/**
	* 获取订单明细格式化(toString)
	**/
	public String getCsoCostDetails$(){
		return "[...]";
	}
	/*******************************反向关联的惠免记录列表**********************************/	
	/**
	* 获取与当前对象反向关联的惠免记录列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsUseRecord> getGifts(){
		com.ccclubs.service.admin.ICsUseRecordService csUseRecordService = $.GetSpringBean("csUseRecordService");
		Map params = new HashMap();
		params.put("csurOrder",this.getCsoId());
		return csUseRecordService.getCsUseRecordList(params,-1);
	}
	/**
	* 获取免费优惠项格式化(toString)
	**/
	public String getGifts$(){
		return "[...]";
	}
	/*******************************反向关联的消费记录列表**********************************/	
	/**
	* 获取与当前对象反向关联的消费记录列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsRecord> getRecords(){
		com.ccclubs.service.admin.ICsRecordService csRecordService = $.GetSpringBean("csRecordService");
		Map params = new HashMap();
		params.put("csrOrder",this.getCsoId());
		return csRecordService.getCsRecordList(params,-1);
	}
	/**
	* 获取消费记录项格式化(toString)
	**/
	public String getRecords$(){
		return "[...]";
	}
	/*******************************反向关联的车辆违章列表**********************************/	
	/**
	* 获取与当前对象反向关联的车辆违章列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsViolat> getViolats(){
		com.ccclubs.service.admin.ICsViolatService csViolatService = $.GetSpringBean("csViolatService");
		Map params = new HashMap();
		params.put("csvOrder",this.getCsoId());
		return csViolatService.getCsViolatList(params,-1);
	}
	/**
	* 获取相关违章格式化(toString)
	**/
	public String getViolats$(){
		return "[...]";
	}
	/*******************************反向关联的车辆事故列表**********************************/	
	/**
	* 获取与当前对象反向关联的车辆事故列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsTrouble> getTroubles(){
		com.ccclubs.service.admin.ICsTroubleService csTroubleService = $.GetSpringBean("csTroubleService");
		Map params = new HashMap();
		params.put("cstOrder",this.getCsoId());
		return csTroubleService.getCsTroubleList(params,-1);
	}
	/**
	* 获取相关事故格式化(toString)
	**/
	public String getTroubles$(){
		return "[...]";
	}
	/*******************************反向关联的收支记录列表**********************************/	
	/**
	* 获取与当前对象反向关联的收支记录列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsOperRecord> getOperrecords(){
		com.ccclubs.service.admin.ICsOperRecordService csOperRecordService = $.GetSpringBean("csOperRecordService");
		Map params = new HashMap();
		params.put("csorOrder",this.getCsoId());
		return csOperRecordService.getCsOperRecordList(params,-1);
	}
	/**
	* 获取收入记录格式化(toString)
	**/
	public String getOperrecords$(){
		return "[...]";
	}
	/*******************************折扣**********************************/	
	/**
	* 折扣     0到1,比如0.75表示七五折  
	**/
	public Double getCsoRebate(){
		return this.csoRebate;
	}
	/**
	* 获取折扣格式化(toString)
	**/
	public String getCsoRebate$(){
		String strValue="";
		 strValue=$.str(this.getCsoRebate());	
	 	 return strValue;
	}
	/**
	* 折扣     0到1,比如0.75表示七五折  
	**/
	public void setCsoRebate(Double csoRebate){
		this.csoRebate = csoRebate;
		this.setSeted(F.csoRebate);
	}
	/*******************************押金/保证金**********************************/	
	/**
	* 押金/保证金       
	**/
	public Double getCsoMarginNeed(){
		return this.csoMarginNeed;
	}
	/**
	* 获取押金/保证金格式化(toString)
	**/
	public String getCsoMarginNeed$(){
		String strValue="";
		 strValue=$.str(this.getCsoMarginNeed());	
	 	 return strValue;
	}
	/**
	* 押金/保证金       
	**/
	public void setCsoMarginNeed(Double csoMarginNeed){
		this.csoMarginNeed = csoMarginNeed;
		this.setSeted(F.csoMarginNeed);
	}
	/*******************************预计/里程费**********************************/	
	/**
	* 预计/里程费       
	**/
	public Double getCsoPredict(){
		return this.csoPredict;
	}
	/**
	* 获取预计/里程费格式化(toString)
	**/
	public String getCsoPredict$(){
		String strValue="";
		 strValue=$.str(this.getCsoPredict());	
	 	 return strValue;
	}
	/**
	* 预计/里程费       
	**/
	public void setCsoPredict(Double csoPredict){
		this.csoPredict = csoPredict;
		this.setSeted(F.csoPredict);
	}
	/*******************************应付金额**********************************/	
	/**
	* 应付金额     应付为当前状态下的预计理论金额  
	**/
	public Double getCsoPayNeed(){
		return this.csoPayNeed;
	}
	/**
	* 获取应付金额格式化(toString)
	**/
	public String getCsoPayNeed$(){
		String strValue="";
		 strValue=$.str(this.getCsoPayNeed());	
	 	 return strValue;
	}
	/**
	* 应付金额     应付为当前状态下的预计理论金额  
	**/
	public void setCsoPayNeed(Double csoPayNeed){
		this.csoPayNeed = csoPayNeed;
		this.setSeted(F.csoPayNeed);
	}
	/*******************************实付金额**********************************/	
	/**
	* 实付金额       
	**/
	public Double getCsoPayReal(){
		return this.csoPayReal;
	}
	/**
	* 获取实付金额格式化(toString)
	**/
	public String getCsoPayReal$(){
		String strValue="";
		 strValue=$.str(this.getCsoPayReal());	
	 	 return strValue;
	}
	/**
	* 实付金额       
	**/
	public void setCsoPayReal(Double csoPayReal){
		this.csoPayReal = csoPayReal;
		this.setSeted(F.csoPayReal);
	}
	/*******************************保证金支付**********************************/	
	/**
	* 保证金支付       
	**/
	public Double getCsoPayMoney(){
		return this.csoPayMoney;
	}
	/**
	* 获取保证金支付格式化(toString)
	**/
	public String getCsoPayMoney$(){
		String strValue="";
		 strValue=$.str(this.getCsoPayMoney());	
	 	 return strValue;
	}
	/**
	* 保证金支付       
	**/
	public void setCsoPayMoney(Double csoPayMoney){
		this.csoPayMoney = csoPayMoney;
		this.setSeted(F.csoPayMoney);
	}
	/*******************************现金券支付**********************************/	
	/**
	* 现金券支付       
	**/
	public Double getCsoPayCoupon(){
		return this.csoPayCoupon;
	}
	/**
	* 获取现金券支付格式化(toString)
	**/
	public String getCsoPayCoupon$(){
		String strValue="";
		 strValue=$.str(this.getCsoPayCoupon());	
	 	 return strValue;
	}
	/**
	* 现金券支付       
	**/
	public void setCsoPayCoupon(Double csoPayCoupon){
		this.csoPayCoupon = csoPayCoupon;
		this.setSeted(F.csoPayCoupon);
	}
	/*******************************红包支付**********************************/	
	/**
	* 红包支付       
	**/
	public Double getCsoPayCoin(){
		return this.csoPayCoin;
	}
	/**
	* 获取红包支付格式化(toString)
	**/
	public String getCsoPayCoin$(){
		String strValue="";
		 strValue=$.str(this.getCsoPayCoin());	
	 	 return strValue;
	}
	/**
	* 红包支付       
	**/
	public void setCsoPayCoin(Double csoPayCoin){
		this.csoPayCoin = csoPayCoin;
		this.setSeted(F.csoPayCoin);
	}
	/*******************************电里程费**********************************/	
	/**
	* 电里程费       
	**/
	public Double getCsoPayKilom(){
		return this.csoPayKilom;
	}
	/**
	* 获取电里程费格式化(toString)
	**/
	public String getCsoPayKilom$(){
		String strValue="";
		 strValue=$.str(this.getCsoPayKilom());	
	 	 return strValue;
	}
	/**
	* 电里程费       
	**/
	public void setCsoPayKilom(Double csoPayKilom){
		this.csoPayKilom = csoPayKilom;
		this.setSeted(F.csoPayKilom);
	}
	/*******************************油里程费**********************************/	
	/**
	* 油里程费       
	**/
	public Double getCsoPayMileage(){
		return this.csoPayMileage;
	}
	/**
	* 获取油里程费格式化(toString)
	**/
	public String getCsoPayMileage$(){
		String strValue="";
		 strValue=$.str(this.getCsoPayMileage());	
	 	 return strValue;
	}
	/**
	* 油里程费       
	**/
	public void setCsoPayMileage(Double csoPayMileage){
		this.csoPayMileage = csoPayMileage;
		this.setSeted(F.csoPayMileage);
	}
	/*******************************租金费用**********************************/	
	/**
	* 租金费用       
	**/
	public Double getCsoPayRent(){
		return this.csoPayRent;
	}
	/**
	* 获取租金费用格式化(toString)
	**/
	public String getCsoPayRent$(){
		String strValue="";
		 strValue=$.str(this.getCsoPayRent());	
	 	 return strValue;
	}
	/**
	* 租金费用       
	**/
	public void setCsoPayRent(Double csoPayRent){
		this.csoPayRent = csoPayRent;
		this.setSeted(F.csoPayRent);
	}
	/*******************************免责费用**********************************/	
	/**
	* 免责费用       
	**/
	public Double getCsoPayInsure(){
		return this.csoPayInsure;
	}
	/**
	* 获取免责费用格式化(toString)
	**/
	public String getCsoPayInsure$(){
		String strValue="";
		 strValue=$.str(this.getCsoPayInsure());	
	 	 return strValue;
	}
	/**
	* 免责费用       
	**/
	public void setCsoPayInsure(Double csoPayInsure){
		this.csoPayInsure = csoPayInsure;
		this.setSeted(F.csoPayInsure);
	}
	/*******************************超时费用**********************************/	
	/**
	* 超时费用       
	**/
	public Double getCsoPayTimeout(){
		return this.csoPayTimeout;
	}
	/**
	* 获取超时费用格式化(toString)
	**/
	public String getCsoPayTimeout$(){
		String strValue="";
		 strValue=$.str(this.getCsoPayTimeout());	
	 	 return strValue;
	}
	/**
	* 超时费用       
	**/
	public void setCsoPayTimeout(Double csoPayTimeout){
		this.csoPayTimeout = csoPayTimeout;
		this.setSeted(F.csoPayTimeout);
	}
	/*******************************陪驾费用**********************************/	
	/**
	* 陪驾费用       
	**/
	public Double getCsoPayDriver(){
		return this.csoPayDriver;
	}
	/**
	* 获取陪驾费用格式化(toString)
	**/
	public String getCsoPayDriver$(){
		String strValue="";
		 strValue=$.str(this.getCsoPayDriver());	
	 	 return strValue;
	}
	/**
	* 陪驾费用       
	**/
	public void setCsoPayDriver(Double csoPayDriver){
		this.csoPayDriver = csoPayDriver;
		this.setSeted(F.csoPayDriver);
	}
	/*******************************计入营收**********************************/	
	/**
	* 计入营收    0:不计营收 1:计入营收    
	**/
	public Short getCsoIncome(){
		return this.csoIncome;
	}
	/**
	* 获取计入营收格式化(toString)
	**/
	public String getCsoIncome$(){
		String strValue="";
		 if($.equals($.str(this.getCsoIncome()),"0"))
			strValue=$.str("不计营收");		 
		 if($.equals($.str(this.getCsoIncome()),"1"))
			strValue=$.str("计入营收");		 
	 	 return strValue;
	}
	/**
	* 计入营收    0:不计营收 1:计入营收    
	**/
	public void setCsoIncome(Short csoIncome){
		this.csoIncome = csoIncome;
		this.setSeted(F.csoIncome);
	}
	/*******************************订单次数**********************************/	
	/**
	* 订单次数     标明该订单是会员第几次用车  
	**/
	public Integer getCsoUseIndex(){
		return this.csoUseIndex;
	}
	/**
	* 获取订单次数格式化(toString)
	**/
	public String getCsoUseIndex$(){
		String strValue="";
		 strValue=$.str(this.getCsoUseIndex());
	 	 return strValue;
	}
	/**
	* 订单次数     标明该订单是会员第几次用车  
	**/
	public void setCsoUseIndex(Integer csoUseIndex){
		this.csoUseIndex = csoUseIndex;
		this.setSeted(F.csoUseIndex);
	}
	/*******************************类型标识**********************************/	
	/**
	* 类型标识    0:默认 1:2014春节订单 2:2015春节订单    
	**/
	public Short getCsoFlag(){
		return this.csoFlag;
	}
	/**
	* 获取类型标识格式化(toString)
	**/
	public String getCsoFlag$(){
		String strValue="";
		 if($.equals($.str(this.getCsoFlag()),"0"))
			strValue=$.str("默认");		 
		 if($.equals($.str(this.getCsoFlag()),"1"))
			strValue=$.str("2014春节订单");		 
		 if($.equals($.str(this.getCsoFlag()),"2"))
			strValue=$.str("2015春节订单");		 
	 	 return strValue;
	}
	/**
	* 类型标识    0:默认 1:2014春节订单 2:2015春节订单    
	**/
	public void setCsoFlag(Short csoFlag){
		this.csoFlag = csoFlag;
		this.setSeted(F.csoFlag);
	}
	/*******************************订单来源**********************************/	
	/**
	* 订单来源 [非空]   0:网站预订 1:后台预订 2:微信预订 3:App预订 4:企业预订 5:支付宝预订    
	**/
	public Short getCsoFrom(){
		return this.csoFrom;
	}
	/**
	* 获取订单来源格式化(toString)
	**/
	public String getCsoFrom$(){
		String strValue="";
		 if($.equals($.str(this.getCsoFrom()),"0"))
			strValue=$.str("网站预订");		 
		 if($.equals($.str(this.getCsoFrom()),"1"))
			strValue=$.str("后台预订");		 
		 if($.equals($.str(this.getCsoFrom()),"2"))
			strValue=$.str("微信预订");		 
		 if($.equals($.str(this.getCsoFrom()),"3"))
			strValue=$.str("App预订");		 
		 if($.equals($.str(this.getCsoFrom()),"4"))
			strValue=$.str("企业预订");		 
		 if($.equals($.str(this.getCsoFrom()),"5"))
			strValue=$.str("支付宝预订");		 
	 	 return strValue;
	}
	/**
	* 订单来源 [非空]   0:网站预订 1:后台预订 2:微信预订 3:App预订 4:企业预订 5:支付宝预订    
	**/
	public void setCsoFrom(Short csoFrom){
		this.csoFrom = csoFrom;
		this.setSeted(F.csoFrom);
	}
	/*******************************来源信息**********************************/	
	/**
	* 来源信息       
	**/
	public String getCsoSrc(){
		return this.csoSrc;
	}
	/**
	* 获取来源信息格式化(toString)
	**/
	public String getCsoSrc$(){
		String strValue="";
	 	 Object object = this.get$csoSrc();
	 	 if(object!=null){
	 		caption caption = object.getClass().getAnnotation(caption.class);
	 		if(caption!=null)strValue+="["+caption.value()+"]";
	 		try {
				strValue+=object.getClass().getMethod("getKeyValue").invoke(object);
			} catch (Exception e) {
				e.printStackTrace();
			}
	 	 }
	 	 return strValue;
	}
	/**
	* 来源信息       
	**/
	public void setCsoSrc(String csoSrc){
		this.csoSrc = csoSrc;
		this.setSeted(F.csoSrc);
	}
	/**
	* 泛关联对象，对象类名由csoSrc字段中标明
	**/	 			
 	public <T> T get$csoSrc(){
		if($.empty(this.csoSrc))
 			return null;
 		try{
	 		String ClassName = this.csoSrc.split("@")[0];
	 		Long id = Long.parseLong(this.csoSrc.split("@")[1]);
	 		Class<?> cls = Class.forName("com.ccclubs.model."+ClassName);
	 		return (T)cls.getMethod("get", Long.class).invoke(null, id);	
 		}catch(Exception ex){
 			ex.printStackTrace();
 			return null;
 		}
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsoUpdateTime(){
		return this.csoUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsoUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsoUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsoUpdateTime(Date csoUpdateTime){
		this.csoUpdateTime = csoUpdateTime;
		this.setSeted(F.csoUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsoAddTime(){
		return this.csoAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsoAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsoAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsoAddTime(Date csoAddTime){
		this.csoAddTime = csoAddTime;
		this.setSeted(F.csoAddTime);
	}
	/*******************************结束时间**********************************/	
	/**
	* 结束时间       
	**/
	public Date getCsoEndTime(){
		return this.csoEndTime;
	}
	/**
	* 获取结束时间格式化(toString)
	**/
	public String getCsoEndTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsoEndTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 结束时间       
	**/
	public void setCsoEndTime(Date csoEndTime){
		this.csoEndTime = csoEndTime;
		this.setSeted(F.csoEndTime);
	}
	/*******************************序列号**********************************/	
	/**
	* 序列号       
	**/
	public String getCsoSerial(){
		return this.csoSerial;
	}
	/**
	* 获取序列号格式化(toString)
	**/
	public String getCsoSerial$(){
		String strValue="";
		 strValue=$.str(this.getCsoSerial());
	 	 return strValue;
	}
	/**
	* 序列号       
	**/
	public void setCsoSerial(String csoSerial){
		this.csoSerial = csoSerial;
		this.setSeted(F.csoSerial);
	}
	/*******************************授权码**********************************/	
	/**
	* 授权码 [非空]      
	**/
	public String getCsoCode(){
		return this.csoCode;
	}
	/**
	* 获取授权码格式化(toString)
	**/
	public String getCsoCode$(){
		String strValue="";
		 strValue=$.str(this.getCsoCode());
	 	 return strValue;
	}
	/**
	* 授权码 [非空]      
	**/
	public void setCsoCode(String csoCode){
		this.csoCode = csoCode;
		this.setSeted(F.csoCode);
	}
	/*******************************订单备注**********************************/	
	/**
	* 订单备注       
	**/
	public String getCsoRemark(){
		return this.csoRemark;
	}
	/**
	* 获取订单备注格式化(toString)
	**/
	public String getCsoRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsoRemark());
	 	 return strValue;
	}
	/**
	* 订单备注       
	**/
	public void setCsoRemark(String csoRemark){
		this.csoRemark = csoRemark;
		this.setSeted(F.csoRemark);
	}
	/*******************************历史日志**********************************/	
	/**
	* 历史日志       
	**/
	public String getCsoLog(){
		return this.csoLog;
	}
	/**
	* 获取历史日志格式化(toString)
	**/
	public String getCsoLog$(){
		String strValue="";
		 strValue=$.str(this.getCsoLog());
	 	 return strValue;
	}
	/**
	* 历史日志       
	**/
	public void setCsoLog(String csoLog){
		this.csoLog = csoLog;
		this.setSeted(F.csoLog);
	}
	/*******************************操作人**********************************/	
	/**
	* 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsoEditor(){
		return this.csoEditor;
	}
	/**
	* 获取操作人格式化(toString)
	**/
	public String getCsoEditor$(){
		String strValue="";
		if(this.getCsoEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsoEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsoEditor(Long csoEditor){
		this.csoEditor = csoEditor;
		this.setSeted(F.csoEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csoEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsoEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsoEditor()!=null){
 			srvUser = SrvUser.get(this.getCsoEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCsoEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************程序掩码**********************************/	
	/**
	* 程序掩码 [非空]   1:催还车短信已发 2:设置不发还车短信    
	**/
	public Long getCsoMask(){
		return this.csoMask;
	}
	/**
	* 获取程序掩码格式化(toString)
	**/
	public String getCsoMask$(){
		String strValue="";
		if(this.getCsoMask()!=null){
			if((this.getCsoMask() & 1 ) == 1)
				strValue+="["+$.str("催还车短信已发")+"]";		 
			if((this.getCsoMask() & 2 ) == 2)
				strValue+="["+$.str("设置不发还车短信")+"]";		 
		}
	 	 return strValue;
	}
	/**
	* 程序掩码 [非空]   1:催还车短信已发 2:设置不发还车短信    
	**/
	public void setCsoMask(Long csoMask){
		this.csoMask = csoMask;
		this.setSeted(F.csoMask);
	}
	/*******************************下位机版本**********************************/	
	/**
	* 下位机版本    0:默认 1:测试    
	**/
	public Short getCsoDeviceVesion(){
		return this.csoDeviceVesion;
	}
	/**
	* 获取下位机版本格式化(toString)
	**/
	public String getCsoDeviceVesion$(){
		String strValue="";
		 if($.equals($.str(this.getCsoDeviceVesion()),"0"))
			strValue=$.str("默认");		 
		 if($.equals($.str(this.getCsoDeviceVesion()),"1"))
			strValue=$.str("测试");		 
	 	 return strValue;
	}
	/**
	* 下位机版本    0:默认 1:测试    
	**/
	public void setCsoDeviceVesion(Short csoDeviceVesion){
		this.csoDeviceVesion = csoDeviceVesion;
		this.setSeted(F.csoDeviceVesion);
	}
	/*******************************报警**********************************/	
	/**
	* 报警    0:默认 1:超距 2:超时 3:断线    
	**/
	public Short getCsoAlarm(){
		return this.csoAlarm;
	}
	/**
	* 获取报警格式化(toString)
	**/
	public String getCsoAlarm$(){
		String strValue="";
		 if($.equals($.str(this.getCsoAlarm()),"0"))
			strValue=$.str("默认");		 
		 if($.equals($.str(this.getCsoAlarm()),"1"))
			strValue=$.str("超距");		 
		 if($.equals($.str(this.getCsoAlarm()),"2"))
			strValue=$.str("超时");		 
		 if($.equals($.str(this.getCsoAlarm()),"3"))
			strValue=$.str("断线");		 
	 	 return strValue;
	}
	/**
	* 报警    0:默认 1:超距 2:超时 3:断线    
	**/
	public void setCsoAlarm(Short csoAlarm){
		this.csoAlarm = csoAlarm;
		this.setSeted(F.csoAlarm);
	}
	/*******************************数据状态**********************************/	
	/**
	* 数据状态 [非空]   0:未发送 1:已发送 2:已收到 3:不发送    
	**/
	public Short getCsoState(){
		return this.csoState;
	}
	/**
	* 获取数据状态格式化(toString)
	**/
	public String getCsoState$(){
		String strValue="";
		 if($.equals($.str(this.getCsoState()),"0"))
			strValue=$.str("未发送");		 
		 if($.equals($.str(this.getCsoState()),"1"))
			strValue=$.str("已发送");		 
		 if($.equals($.str(this.getCsoState()),"2"))
			strValue=$.str("已收到");		 
		 if($.equals($.str(this.getCsoState()),"3"))
			strValue=$.str("不发送");		 
	 	 return strValue;
	}
	/**
	* 数据状态 [非空]   0:未发送 1:已发送 2:已收到 3:不发送    
	**/
	public void setCsoState(Short csoState){
		this.csoState = csoState;
		this.setSeted(F.csoState);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:已预定 1:使用中 2:已还车 3:已取消 4:已完成 5:待处理 6:已退款 7:已作废    
	**/
	public Short getCsoStatus(){
		return this.csoStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsoStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsoStatus()),"0"))
			strValue=$.str("已预定");		 
		 if($.equals($.str(this.getCsoStatus()),"1"))
			strValue=$.str("使用中");		 
		 if($.equals($.str(this.getCsoStatus()),"2"))
			strValue=$.str("已还车");		 
		 if($.equals($.str(this.getCsoStatus()),"3"))
			strValue=$.str("已取消");		 
		 if($.equals($.str(this.getCsoStatus()),"4"))
			strValue=$.str("已完成");		 
		 if($.equals($.str(this.getCsoStatus()),"5"))
			strValue=$.str("待处理");		 
		 if($.equals($.str(this.getCsoStatus()),"6"))
			strValue=$.str("已退款");		 
		 if($.equals($.str(this.getCsoStatus()),"7"))
			strValue=$.str("已作废");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:已预定 1:使用中 2:已还车 3:已取消 4:已完成 5:待处理 6:已退款 7:已作废    
	**/
	public void setCsoStatus(Short csoStatus){
		this.csoStatus = csoStatus;
		this.setSeted(F.csoStatus);
	}
	
	/*******************************取消来源 1：后台 3:app**********************************/	
	/**
	* 取消来源 1：后台 3:app [可空]      
	**/
	public Short getCsoCancelFrom(){
		return this.csoCancelFrom;
	}
	/**
	* 获取取消来源 1：后台 3:app格式化(toString)
	**/
	public String getCsoCancelFrom$(){
		String strValue="";
		 strValue=$.str(this.getCsoCancelFrom());
	 	 return strValue;
	}
	/**
	* 取消来源 1：后台 3:app [可空]     
	**/
	public void setCsoCancelFrom(Short csoCancelFrom){
		this.csoCancelFrom = csoCancelFrom;
		this.setSeted(F.csoCancelFrom);
	}
	
	
	
	
	
	
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsOrder.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsOrder.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsOrder.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsOrder.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsOrder.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsOrder.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsOrder.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsOrder.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 获取查询参数链式串
	 * @return
	 */
	public static M where(){ return new M(0); }
	public static M m(){ return new M(0); }
	public static M set(){ return new M(1); }
	public static class M extends HashMap{
		int type = 0;
		M linked = null;
		public M(int type){this.type = type;};
		public M where(){
			this.linked=new M(0);
			this.linked.linked=this;
			return this.linked;
		};
		public M set(){
			this.linked=new M(1);
			this.linked.linked=this;
			return this.linked;
		};
		/** 订单编号 [非空]       **/
		public M csoId(Object csoId){this.put("csoId", csoId);return this;};
	 	/** and cso_id is null */
 		public M csoIdNull(){if(this.get("csoIdNot")==null)this.put("csoIdNot", "");this.put("csoId", null);return this;};
 		/** not .... */
 		public M csoIdNot(){this.put("csoIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csoHost(Object csoHost){this.put("csoHost", csoHost);return this;};
	 	/** and cso_host is null */
 		public M csoHostNull(){if(this.get("csoHostNot")==null)this.put("csoHostNot", "");this.put("csoHost", null);return this;};
 		/** not .... */
 		public M csoHostNot(){this.put("csoHostNot", "not");return this;};
		/** 支付会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public M csoPayMember(Object csoPayMember){this.put("csoPayMember", csoPayMember);return this;};
	 	/** and cso_pay_member is null */
 		public M csoPayMemberNull(){if(this.get("csoPayMemberNot")==null)this.put("csoPayMemberNot", "");this.put("csoPayMember", null);return this;};
 		/** not .... */
 		public M csoPayMemberNot(){this.put("csoPayMemberNot", "not");return this;};
		public M csoPayMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csoPayMember$on", value);
			return this;
		};	
		/** 使用会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public M csoUseMember(Object csoUseMember){this.put("csoUseMember", csoUseMember);return this;};
	 	/** and cso_use_member is null */
 		public M csoUseMemberNull(){if(this.get("csoUseMemberNot")==null)this.put("csoUseMemberNot", "");this.put("csoUseMember", null);return this;};
 		/** not .... */
 		public M csoUseMemberNot(){this.put("csoUseMemberNot", "not");return this;};
		public M csoUseMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csoUseMember$on", value);
			return this;
		};	
		/** 计费类型 [非空] [CsUserType]      **/
		public M csoFeeType(Object csoFeeType){this.put("csoFeeType", csoFeeType);return this;};
	 	/** and cso_fee_type is null */
 		public M csoFeeTypeNull(){if(this.get("csoFeeTypeNot")==null)this.put("csoFeeTypeNot", "");this.put("csoFeeType", null);return this;};
 		/** not .... */
 		public M csoFeeTypeNot(){this.put("csoFeeTypeNot", "not");return this;};
		public M csoFeeType$on(CsUserType.M value){
			this.put("CsUserType", value);
			this.put("csoFeeType$on", value);
			return this;
		};	
		/** 网点区域 [非空] [CsArea]      **/
		public M csoArea(Object csoArea){this.put("csoArea", csoArea);return this;};
	 	/** and cso_area is null */
 		public M csoAreaNull(){if(this.get("csoAreaNot")==null)this.put("csoAreaNot", "");this.put("csoArea", null);return this;};
 		/** not .... */
 		public M csoAreaNot(){this.put("csoAreaNot", "not");return this;};
		public M csoArea$on(CsArea.M value){
			this.put("CsArea", value);
			this.put("csoArea$on", value);
			return this;
		};	
		/** 取车网点 [非空] [CsOutlets]  {csoHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
		public M csoOutlets(Object csoOutlets){this.put("csoOutlets", csoOutlets);return this;};
	 	/** and cso_outlets is null */
 		public M csoOutletsNull(){if(this.get("csoOutletsNot")==null)this.put("csoOutletsNot", "");this.put("csoOutlets", null);return this;};
 		/** not .... */
 		public M csoOutletsNot(){this.put("csoOutletsNot", "not");return this;};
		public M csoOutlets$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("csoOutlets$on", value);
			return this;
		};	
		/** 还车网点 [非空] [CsOutlets]  {csoHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
		public M csoOutletsRet(Object csoOutletsRet){this.put("csoOutletsRet", csoOutletsRet);return this;};
	 	/** and cso_outlets_ret is null */
 		public M csoOutletsRetNull(){if(this.get("csoOutletsRetNot")==null)this.put("csoOutletsRetNot", "");this.put("csoOutletsRet", null);return this;};
 		/** not .... */
 		public M csoOutletsRetNot(){this.put("csoOutletsRetNot", "not");return this;};
		public M csoOutletsRet$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("csoOutletsRet$on", value);
			return this;
		};	
		/** 预订车型 [非空] [CsCarModel]      **/
		public M csoModel(Object csoModel){this.put("csoModel", csoModel);return this;};
	 	/** and cso_model is null */
 		public M csoModelNull(){if(this.get("csoModelNot")==null)this.put("csoModelNot", "");this.put("csoModel", null);return this;};
 		/** not .... */
 		public M csoModelNot(){this.put("csoModelNot", "not");return this;};
		public M csoModel$on(CsCarModel.M value){
			this.put("CsCarModel", value);
			this.put("csoModel$on", value);
			return this;
		};	
		/** 预订车辆 [非空] [CsCar]  {csoOutlets}:cscOutlets {csoModel}:cscModel     **/
		public M csoCar(Object csoCar){this.put("csoCar", csoCar);return this;};
	 	/** and cso_car is null */
 		public M csoCarNull(){if(this.get("csoCarNot")==null)this.put("csoCarNot", "");this.put("csoCar", null);return this;};
 		/** not .... */
 		public M csoCarNot(){this.put("csoCarNot", "not");return this;};
		public M csoCar$on(CsCar.M value){
			this.put("CsCar", value);
			this.put("csoCar$on", value);
			return this;
		};	
		/** 车辆商家 [非空] [CsProvid]      **/
		public M csoProvid(Object csoProvid){this.put("csoProvid", csoProvid);return this;};
	 	/** and cso_provid is null */
 		public M csoProvidNull(){if(this.get("csoProvidNot")==null)this.put("csoProvidNot", "");this.put("csoProvid", null);return this;};
 		/** not .... */
 		public M csoProvidNot(){this.put("csoProvidNot", "not");return this;};
		public M csoProvid$on(CsProvid.M value){
			this.put("CsProvid", value);
			this.put("csoProvid$on", value);
			return this;
		};	
		/** 车牌号码 [非空]       **/
		public M csoCarNumber(Object csoCarNumber){this.put("csoCarNumber", csoCarNumber);return this;};
	 	/** and cso_car_number is null */
 		public M csoCarNumberNull(){if(this.get("csoCarNumberNot")==null)this.put("csoCarNumberNot", "");this.put("csoCarNumber", null);return this;};
 		/** not .... */
 		public M csoCarNumberNot(){this.put("csoCarNumberNot", "not");return this;};
		/** 手机号码        **/
		public M csoMobile(Object csoMobile){this.put("csoMobile", csoMobile);return this;};
	 	/** and cso_mobile is null */
 		public M csoMobileNull(){if(this.get("csoMobileNot")==null)this.put("csoMobileNot", "");this.put("csoMobile", null);return this;};
 		/** not .... */
 		public M csoMobileNot(){this.put("csoMobileNot", "not");return this;};
		/** EV序列号 [非空]       **/
		public M csoEvRfid(Object csoEvRfid){this.put("csoEvRfid", csoEvRfid);return this;};
	 	/** and cso_ev_rfid is null */
 		public M csoEvRfidNull(){if(this.get("csoEvRfidNot")==null)this.put("csoEvRfidNot", "");this.put("csoEvRfid", null);return this;};
 		/** not .... */
 		public M csoEvRfidNot(){this.put("csoEvRfidNot", "not");return this;};
		/** 宣传车？        **/
		public M csoIsAdCar(Object csoIsAdCar){this.put("csoIsAdCar", csoIsAdCar);return this;};
	 	/** and cso_is_ad_car is null */
 		public M csoIsAdCarNull(){if(this.get("csoIsAdCarNot")==null)this.put("csoIsAdCarNot", "");this.put("csoIsAdCar", null);return this;};
 		/** not .... */
 		public M csoIsAdCarNot(){this.put("csoIsAdCarNot", "not");return this;};
		/** 订单开始时间 [非空]       **/
		public M csoStartTime(Object csoStartTime){this.put("csoStartTime", csoStartTime);return this;};
	 	/** and cso_start_time is null */
 		public M csoStartTimeNull(){if(this.get("csoStartTimeNot")==null)this.put("csoStartTimeNot", "");this.put("csoStartTime", null);return this;};
 		/** not .... */
 		public M csoStartTimeNot(){this.put("csoStartTimeNot", "not");return this;};
 		/** and cso_start_time >= ? */
 		public M csoStartTimeStart(Object start){this.put("csoStartTimeStart", start);return this;};			
 		/** and cso_start_time <= ? */
 		public M csoStartTimeEnd(Object end){this.put("csoStartTimeEnd", end);return this;};
		/** 订单结束时间 [非空]       **/
		public M csoFinishTime(Object csoFinishTime){this.put("csoFinishTime", csoFinishTime);return this;};
	 	/** and cso_finish_time is null */
 		public M csoFinishTimeNull(){if(this.get("csoFinishTimeNot")==null)this.put("csoFinishTimeNot", "");this.put("csoFinishTime", null);return this;};
 		/** not .... */
 		public M csoFinishTimeNot(){this.put("csoFinishTimeNot", "not");return this;};
 		/** and cso_finish_time >= ? */
 		public M csoFinishTimeStart(Object start){this.put("csoFinishTimeStart", start);return this;};			
 		/** and cso_finish_time <= ? */
 		public M csoFinishTimeEnd(Object end){this.put("csoFinishTimeEnd", end);return this;};
		/** 是否长单        **/
		public M csoLongOrder(Object csoLongOrder){this.put("csoLongOrder", csoLongOrder);return this;};
	 	/** and cso_long_order is null */
 		public M csoLongOrderNull(){if(this.get("csoLongOrderNot")==null)this.put("csoLongOrderNot", "");this.put("csoLongOrder", null);return this;};
 		/** not .... */
 		public M csoLongOrderNot(){this.put("csoLongOrderNot", "not");return this;};
		/** 长单价格     元/月   **/
		public M csoLongPrice(Object csoLongPrice){this.put("csoLongPrice", csoLongPrice);return this;};
	 	/** and cso_long_price is null */
 		public M csoLongPriceNull(){if(this.get("csoLongPriceNot")==null)this.put("csoLongPriceNot", "");this.put("csoLongPrice", null);return this;};
 		/** not .... */
 		public M csoLongPriceNot(){this.put("csoLongPriceNot", "not");return this;};
		/** and cso_long_price >= ? */
		public M csoLongPriceMin(Object min){this.put("csoLongPriceMin", min);return this;};
		/** and cso_long_price <= ? */
		public M csoLongPriceMax(Object max){this.put("csoLongPriceMax", max);return this;};
		/** 用车方式    0:原借原还 1:A借B还     **/
		public M csoUseType(Object csoUseType){this.put("csoUseType", csoUseType);return this;};
	 	/** and cso_use_type is null */
 		public M csoUseTypeNull(){if(this.get("csoUseTypeNot")==null)this.put("csoUseTypeNot", "");this.put("csoUseType", null);return this;};
 		/** not .... */
 		public M csoUseTypeNot(){this.put("csoUseTypeNot", "not");return this;};
		/** 实际取车时间        **/
		public M csoTakeTime(Object csoTakeTime){this.put("csoTakeTime", csoTakeTime);return this;};
	 	/** and cso_take_time is null */
 		public M csoTakeTimeNull(){if(this.get("csoTakeTimeNot")==null)this.put("csoTakeTimeNot", "");this.put("csoTakeTime", null);return this;};
 		/** not .... */
 		public M csoTakeTimeNot(){this.put("csoTakeTimeNot", "not");return this;};
 		/** and cso_take_time >= ? */
 		public M csoTakeTimeStart(Object start){this.put("csoTakeTimeStart", start);return this;};			
 		/** and cso_take_time <= ? */
 		public M csoTakeTimeEnd(Object end){this.put("csoTakeTimeEnd", end);return this;};
		/** 实际还车时间        **/
		public M csoRetTime(Object csoRetTime){this.put("csoRetTime", csoRetTime);return this;};
	 	/** and cso_ret_time is null */
 		public M csoRetTimeNull(){if(this.get("csoRetTimeNot")==null)this.put("csoRetTimeNot", "");this.put("csoRetTime", null);return this;};
 		/** not .... */
 		public M csoRetTimeNot(){this.put("csoRetTimeNot", "not");return this;};
 		/** and cso_ret_time >= ? */
 		public M csoRetTimeStart(Object start){this.put("csoRetTimeStart", start);return this;};			
 		/** and cso_ret_time <= ? */
 		public M csoRetTimeEnd(Object end){this.put("csoRetTimeEnd", end);return this;};
		/** 订单时长 [非空]       **/
		public M csoDuration(Object csoDuration){this.put("csoDuration", csoDuration);return this;};
	 	/** and cso_duration is null */
 		public M csoDurationNull(){if(this.get("csoDurationNot")==null)this.put("csoDurationNot", "");this.put("csoDuration", null);return this;};
 		/** not .... */
 		public M csoDurationNot(){this.put("csoDurationNot", "not");return this;};
		/** and cso_duration >= ? */
		public M csoDurationMin(Object min){this.put("csoDurationMin", min);return this;};
		/** and cso_duration <= ? */
		public M csoDurationMax(Object max){this.put("csoDurationMax", max);return this;};
		/** 燃油里程 [非空]       **/
		public M csoFuel(Object csoFuel){this.put("csoFuel", csoFuel);return this;};
	 	/** and cso_fuel is null */
 		public M csoFuelNull(){if(this.get("csoFuelNot")==null)this.put("csoFuelNot", "");this.put("csoFuel", null);return this;};
 		/** not .... */
 		public M csoFuelNot(){this.put("csoFuelNot", "not");return this;};
		/** and cso_fuel >= ? */
		public M csoFuelMin(Object min){this.put("csoFuelMin", min);return this;};
		/** and cso_fuel <= ? */
		public M csoFuelMax(Object max){this.put("csoFuelMax", max);return this;};
		/** 电里程 [非空]       **/
		public M csoElectric(Object csoElectric){this.put("csoElectric", csoElectric);return this;};
	 	/** and cso_electric is null */
 		public M csoElectricNull(){if(this.get("csoElectricNot")==null)this.put("csoElectricNot", "");this.put("csoElectric", null);return this;};
 		/** not .... */
 		public M csoElectricNot(){this.put("csoElectricNot", "not");return this;};
		/** and cso_electric >= ? */
		public M csoElectricMin(Object min){this.put("csoElectricMin", min);return this;};
		/** and cso_electric <= ? */
		public M csoElectricMax(Object max){this.put("csoElectricMax", max);return this;};
		/** 使用信用卡  [CsCreditCard]  {csoPayMember}:csccMember     **/
		public M csoCreditCard(Object csoCreditCard){this.put("csoCreditCard", csoCreditCard);return this;};
	 	/** and cso_credit_card is null */
 		public M csoCreditCardNull(){if(this.get("csoCreditCardNot")==null)this.put("csoCreditCardNot", "");this.put("csoCreditCard", null);return this;};
 		/** not .... */
 		public M csoCreditCardNot(){this.put("csoCreditCardNot", "not");return this;};
		public M csoCreditCard$on(CsCreditCard.M value){
			this.put("CsCreditCard", value);
			this.put("csoCreditCard$on", value);
			return this;
		};	
		/** 信用卡帐单  [CsCreditBill]  {csoPayMember}:cscbMember     **/
		public M csoCreditBill(Object csoCreditBill){this.put("csoCreditBill", csoCreditBill);return this;};
	 	/** and cso_credit_bill is null */
 		public M csoCreditBillNull(){if(this.get("csoCreditBillNot")==null)this.put("csoCreditBillNot", "");this.put("csoCreditBill", null);return this;};
 		/** not .... */
 		public M csoCreditBillNot(){this.put("csoCreditBillNot", "not");return this;};
		public M csoCreditBill$on(CsCreditBill.M value){
			this.put("CsCreditBill", value);
			this.put("csoCreditBill$on", value);
			return this;
		};	
		/** 保险类型  [CsProduct]     select * from cs_product where csp_flag='INSURE' or csp_flag='SECURE' **/
		public M csoInsureType(Object csoInsureType){this.put("csoInsureType", csoInsureType);return this;};
	 	/** and cso_insure_type is null */
 		public M csoInsureTypeNull(){if(this.get("csoInsureTypeNot")==null)this.put("csoInsureTypeNot", "");this.put("csoInsureType", null);return this;};
 		/** not .... */
 		public M csoInsureTypeNot(){this.put("csoInsureTypeNot", "not");return this;};
		public M csoInsureType$on(CsProduct.M value){
			this.put("CsProduct", value);
			this.put("csoInsureType$on", value);
			return this;
		};	
		/** 免费小时        **/
		public M csoFreehour(Object csoFreehour){this.put("csoFreehour", csoFreehour);return this;};
	 	/** and cso_freehour is null */
 		public M csoFreehourNull(){if(this.get("csoFreehourNot")==null)this.put("csoFreehourNot", "");this.put("csoFreehour", null);return this;};
 		/** not .... */
 		public M csoFreehourNot(){this.put("csoFreehourNot", "not");return this;};
		/** and cso_freehour >= ? */
		public M csoFreehourMin(Object min){this.put("csoFreehourMin", min);return this;};
		/** and cso_freehour <= ? */
		public M csoFreehourMax(Object max){this.put("csoFreehourMax", max);return this;};
		/** 折扣     0到1,比如0.75表示七五折   **/
		public M csoRebate(Object csoRebate){this.put("csoRebate", csoRebate);return this;};
	 	/** and cso_rebate is null */
 		public M csoRebateNull(){if(this.get("csoRebateNot")==null)this.put("csoRebateNot", "");this.put("csoRebate", null);return this;};
 		/** not .... */
 		public M csoRebateNot(){this.put("csoRebateNot", "not");return this;};
		/** and cso_rebate >= ? */
		public M csoRebateMin(Object min){this.put("csoRebateMin", min);return this;};
		/** and cso_rebate <= ? */
		public M csoRebateMax(Object max){this.put("csoRebateMax", max);return this;};
		/** 押金/保证金        **/
		public M csoMarginNeed(Object csoMarginNeed){this.put("csoMarginNeed", csoMarginNeed);return this;};
	 	/** and cso_margin_need is null */
 		public M csoMarginNeedNull(){if(this.get("csoMarginNeedNot")==null)this.put("csoMarginNeedNot", "");this.put("csoMarginNeed", null);return this;};
 		/** not .... */
 		public M csoMarginNeedNot(){this.put("csoMarginNeedNot", "not");return this;};
		/** and cso_margin_need >= ? */
		public M csoMarginNeedMin(Object min){this.put("csoMarginNeedMin", min);return this;};
		/** and cso_margin_need <= ? */
		public M csoMarginNeedMax(Object max){this.put("csoMarginNeedMax", max);return this;};
		/** 预计/里程费        **/
		public M csoPredict(Object csoPredict){this.put("csoPredict", csoPredict);return this;};
	 	/** and cso_predict is null */
 		public M csoPredictNull(){if(this.get("csoPredictNot")==null)this.put("csoPredictNot", "");this.put("csoPredict", null);return this;};
 		/** not .... */
 		public M csoPredictNot(){this.put("csoPredictNot", "not");return this;};
		/** and cso_predict >= ? */
		public M csoPredictMin(Object min){this.put("csoPredictMin", min);return this;};
		/** and cso_predict <= ? */
		public M csoPredictMax(Object max){this.put("csoPredictMax", max);return this;};
		/** 应付金额     应付为当前状态下的预计理论金额   **/
		public M csoPayNeed(Object csoPayNeed){this.put("csoPayNeed", csoPayNeed);return this;};
	 	/** and cso_pay_need is null */
 		public M csoPayNeedNull(){if(this.get("csoPayNeedNot")==null)this.put("csoPayNeedNot", "");this.put("csoPayNeed", null);return this;};
 		/** not .... */
 		public M csoPayNeedNot(){this.put("csoPayNeedNot", "not");return this;};
		/** and cso_pay_need >= ? */
		public M csoPayNeedMin(Object min){this.put("csoPayNeedMin", min);return this;};
		/** and cso_pay_need <= ? */
		public M csoPayNeedMax(Object max){this.put("csoPayNeedMax", max);return this;};
		/** 实付金额        **/
		public M csoPayReal(Object csoPayReal){this.put("csoPayReal", csoPayReal);return this;};
	 	/** and cso_pay_real is null */
 		public M csoPayRealNull(){if(this.get("csoPayRealNot")==null)this.put("csoPayRealNot", "");this.put("csoPayReal", null);return this;};
 		/** not .... */
 		public M csoPayRealNot(){this.put("csoPayRealNot", "not");return this;};
		/** and cso_pay_real >= ? */
		public M csoPayRealMin(Object min){this.put("csoPayRealMin", min);return this;};
		/** and cso_pay_real <= ? */
		public M csoPayRealMax(Object max){this.put("csoPayRealMax", max);return this;};
		/** 保证金支付        **/
		public M csoPayMoney(Object csoPayMoney){this.put("csoPayMoney", csoPayMoney);return this;};
	 	/** and cso_pay_money is null */
 		public M csoPayMoneyNull(){if(this.get("csoPayMoneyNot")==null)this.put("csoPayMoneyNot", "");this.put("csoPayMoney", null);return this;};
 		/** not .... */
 		public M csoPayMoneyNot(){this.put("csoPayMoneyNot", "not");return this;};
		/** and cso_pay_money >= ? */
		public M csoPayMoneyMin(Object min){this.put("csoPayMoneyMin", min);return this;};
		/** and cso_pay_money <= ? */
		public M csoPayMoneyMax(Object max){this.put("csoPayMoneyMax", max);return this;};
		/** 现金券支付        **/
		public M csoPayCoupon(Object csoPayCoupon){this.put("csoPayCoupon", csoPayCoupon);return this;};
	 	/** and cso_pay_coupon is null */
 		public M csoPayCouponNull(){if(this.get("csoPayCouponNot")==null)this.put("csoPayCouponNot", "");this.put("csoPayCoupon", null);return this;};
 		/** not .... */
 		public M csoPayCouponNot(){this.put("csoPayCouponNot", "not");return this;};
		/** and cso_pay_coupon >= ? */
		public M csoPayCouponMin(Object min){this.put("csoPayCouponMin", min);return this;};
		/** and cso_pay_coupon <= ? */
		public M csoPayCouponMax(Object max){this.put("csoPayCouponMax", max);return this;};
		/** 红包支付        **/
		public M csoPayCoin(Object csoPayCoin){this.put("csoPayCoin", csoPayCoin);return this;};
	 	/** and cso_pay_coin is null */
 		public M csoPayCoinNull(){if(this.get("csoPayCoinNot")==null)this.put("csoPayCoinNot", "");this.put("csoPayCoin", null);return this;};
 		/** not .... */
 		public M csoPayCoinNot(){this.put("csoPayCoinNot", "not");return this;};
		/** and cso_pay_coin >= ? */
		public M csoPayCoinMin(Object min){this.put("csoPayCoinMin", min);return this;};
		/** and cso_pay_coin <= ? */
		public M csoPayCoinMax(Object max){this.put("csoPayCoinMax", max);return this;};
		/** 电里程费        **/
		public M csoPayKilom(Object csoPayKilom){this.put("csoPayKilom", csoPayKilom);return this;};
	 	/** and cso_pay_kilom is null */
 		public M csoPayKilomNull(){if(this.get("csoPayKilomNot")==null)this.put("csoPayKilomNot", "");this.put("csoPayKilom", null);return this;};
 		/** not .... */
 		public M csoPayKilomNot(){this.put("csoPayKilomNot", "not");return this;};
		/** and cso_pay_kilom >= ? */
		public M csoPayKilomMin(Object min){this.put("csoPayKilomMin", min);return this;};
		/** and cso_pay_kilom <= ? */
		public M csoPayKilomMax(Object max){this.put("csoPayKilomMax", max);return this;};
		/** 油里程费        **/
		public M csoPayMileage(Object csoPayMileage){this.put("csoPayMileage", csoPayMileage);return this;};
	 	/** and cso_pay_mileage is null */
 		public M csoPayMileageNull(){if(this.get("csoPayMileageNot")==null)this.put("csoPayMileageNot", "");this.put("csoPayMileage", null);return this;};
 		/** not .... */
 		public M csoPayMileageNot(){this.put("csoPayMileageNot", "not");return this;};
		/** and cso_pay_mileage >= ? */
		public M csoPayMileageMin(Object min){this.put("csoPayMileageMin", min);return this;};
		/** and cso_pay_mileage <= ? */
		public M csoPayMileageMax(Object max){this.put("csoPayMileageMax", max);return this;};
		/** 租金费用        **/
		public M csoPayRent(Object csoPayRent){this.put("csoPayRent", csoPayRent);return this;};
	 	/** and cso_pay_rent is null */
 		public M csoPayRentNull(){if(this.get("csoPayRentNot")==null)this.put("csoPayRentNot", "");this.put("csoPayRent", null);return this;};
 		/** not .... */
 		public M csoPayRentNot(){this.put("csoPayRentNot", "not");return this;};
		/** and cso_pay_rent >= ? */
		public M csoPayRentMin(Object min){this.put("csoPayRentMin", min);return this;};
		/** and cso_pay_rent <= ? */
		public M csoPayRentMax(Object max){this.put("csoPayRentMax", max);return this;};
		/** 免责费用        **/
		public M csoPayInsure(Object csoPayInsure){this.put("csoPayInsure", csoPayInsure);return this;};
	 	/** and cso_pay_insure is null */
 		public M csoPayInsureNull(){if(this.get("csoPayInsureNot")==null)this.put("csoPayInsureNot", "");this.put("csoPayInsure", null);return this;};
 		/** not .... */
 		public M csoPayInsureNot(){this.put("csoPayInsureNot", "not");return this;};
		/** and cso_pay_insure >= ? */
		public M csoPayInsureMin(Object min){this.put("csoPayInsureMin", min);return this;};
		/** and cso_pay_insure <= ? */
		public M csoPayInsureMax(Object max){this.put("csoPayInsureMax", max);return this;};
		/** 超时费用        **/
		public M csoPayTimeout(Object csoPayTimeout){this.put("csoPayTimeout", csoPayTimeout);return this;};
	 	/** and cso_pay_timeout is null */
 		public M csoPayTimeoutNull(){if(this.get("csoPayTimeoutNot")==null)this.put("csoPayTimeoutNot", "");this.put("csoPayTimeout", null);return this;};
 		/** not .... */
 		public M csoPayTimeoutNot(){this.put("csoPayTimeoutNot", "not");return this;};
		/** and cso_pay_timeout >= ? */
		public M csoPayTimeoutMin(Object min){this.put("csoPayTimeoutMin", min);return this;};
		/** and cso_pay_timeout <= ? */
		public M csoPayTimeoutMax(Object max){this.put("csoPayTimeoutMax", max);return this;};
		/** 陪驾费用        **/
		public M csoPayDriver(Object csoPayDriver){this.put("csoPayDriver", csoPayDriver);return this;};
	 	/** and cso_pay_driver is null */
 		public M csoPayDriverNull(){if(this.get("csoPayDriverNot")==null)this.put("csoPayDriverNot", "");this.put("csoPayDriver", null);return this;};
 		/** not .... */
 		public M csoPayDriverNot(){this.put("csoPayDriverNot", "not");return this;};
		/** and cso_pay_driver >= ? */
		public M csoPayDriverMin(Object min){this.put("csoPayDriverMin", min);return this;};
		/** and cso_pay_driver <= ? */
		public M csoPayDriverMax(Object max){this.put("csoPayDriverMax", max);return this;};
		/** 计入营收    0:不计营收 1:计入营收     **/
		public M csoIncome(Object csoIncome){this.put("csoIncome", csoIncome);return this;};
	 	/** and cso_income is null */
 		public M csoIncomeNull(){if(this.get("csoIncomeNot")==null)this.put("csoIncomeNot", "");this.put("csoIncome", null);return this;};
 		/** not .... */
 		public M csoIncomeNot(){this.put("csoIncomeNot", "not");return this;};
		/** 订单次数     标明该订单是会员第几次用车   **/
		public M csoUseIndex(Object csoUseIndex){this.put("csoUseIndex", csoUseIndex);return this;};
	 	/** and cso_use_index is null */
 		public M csoUseIndexNull(){if(this.get("csoUseIndexNot")==null)this.put("csoUseIndexNot", "");this.put("csoUseIndex", null);return this;};
 		/** not .... */
 		public M csoUseIndexNot(){this.put("csoUseIndexNot", "not");return this;};
		/** and cso_use_index >= ? */
		public M csoUseIndexMin(Object min){this.put("csoUseIndexMin", min);return this;};
		/** and cso_use_index <= ? */
		public M csoUseIndexMax(Object max){this.put("csoUseIndexMax", max);return this;};
		/** 类型标识    0:默认 1:2014春节订单 2:2015春节订单     **/
		public M csoFlag(Object csoFlag){this.put("csoFlag", csoFlag);return this;};
	 	/** and cso_flag is null */
 		public M csoFlagNull(){if(this.get("csoFlagNot")==null)this.put("csoFlagNot", "");this.put("csoFlag", null);return this;};
 		/** not .... */
 		public M csoFlagNot(){this.put("csoFlagNot", "not");return this;};
		/** 订单来源 [非空]   0:网站预订 1:后台预订 2:微信预订 3:App预订 4:企业预订 5:支付宝预订     **/
		public M csoFrom(Object csoFrom){this.put("csoFrom", csoFrom);return this;};
	 	/** and cso_from is null */
 		public M csoFromNull(){if(this.get("csoFromNot")==null)this.put("csoFromNot", "");this.put("csoFrom", null);return this;};
 		/** not .... */
 		public M csoFromNot(){this.put("csoFromNot", "not");return this;};
		/** 来源信息        **/
		public M csoSrc(Object csoSrc){this.put("csoSrc", csoSrc);return this;};
	 	/** and cso_src is null */
 		public M csoSrcNull(){if(this.get("csoSrcNot")==null)this.put("csoSrcNot", "");this.put("csoSrc", null);return this;};
 		/** not .... */
 		public M csoSrcNot(){this.put("csoSrcNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csoUpdateTime(Object csoUpdateTime){this.put("csoUpdateTime", csoUpdateTime);return this;};
	 	/** and cso_update_time is null */
 		public M csoUpdateTimeNull(){if(this.get("csoUpdateTimeNot")==null)this.put("csoUpdateTimeNot", "");this.put("csoUpdateTime", null);return this;};
 		/** not .... */
 		public M csoUpdateTimeNot(){this.put("csoUpdateTimeNot", "not");return this;};
 		/** and cso_update_time >= ? */
 		public M csoUpdateTimeStart(Object start){this.put("csoUpdateTimeStart", start);return this;};			
 		/** and cso_update_time <= ? */
 		public M csoUpdateTimeEnd(Object end){this.put("csoUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csoAddTime(Object csoAddTime){this.put("csoAddTime", csoAddTime);return this;};
	 	/** and cso_add_time is null */
 		public M csoAddTimeNull(){if(this.get("csoAddTimeNot")==null)this.put("csoAddTimeNot", "");this.put("csoAddTime", null);return this;};
 		/** not .... */
 		public M csoAddTimeNot(){this.put("csoAddTimeNot", "not");return this;};
 		/** and cso_add_time >= ? */
 		public M csoAddTimeStart(Object start){this.put("csoAddTimeStart", start);return this;};			
 		/** and cso_add_time <= ? */
 		public M csoAddTimeEnd(Object end){this.put("csoAddTimeEnd", end);return this;};
		/** 结束时间        **/
		public M csoEndTime(Object csoEndTime){this.put("csoEndTime", csoEndTime);return this;};
	 	/** and cso_end_time is null */
 		public M csoEndTimeNull(){if(this.get("csoEndTimeNot")==null)this.put("csoEndTimeNot", "");this.put("csoEndTime", null);return this;};
 		/** not .... */
 		public M csoEndTimeNot(){this.put("csoEndTimeNot", "not");return this;};
 		/** and cso_end_time >= ? */
 		public M csoEndTimeStart(Object start){this.put("csoEndTimeStart", start);return this;};			
 		/** and cso_end_time <= ? */
 		public M csoEndTimeEnd(Object end){this.put("csoEndTimeEnd", end);return this;};
		/** 序列号        **/
		public M csoSerial(Object csoSerial){this.put("csoSerial", csoSerial);return this;};
	 	/** and cso_serial is null */
 		public M csoSerialNull(){if(this.get("csoSerialNot")==null)this.put("csoSerialNot", "");this.put("csoSerial", null);return this;};
 		/** not .... */
 		public M csoSerialNot(){this.put("csoSerialNot", "not");return this;};
		/** 授权码 [非空]       **/
		public M csoCode(Object csoCode){this.put("csoCode", csoCode);return this;};
	 	/** and cso_code is null */
 		public M csoCodeNull(){if(this.get("csoCodeNot")==null)this.put("csoCodeNot", "");this.put("csoCode", null);return this;};
 		/** not .... */
 		public M csoCodeNot(){this.put("csoCodeNot", "not");return this;};
		/** 订单备注        **/
		public M csoRemark(Object csoRemark){this.put("csoRemark", csoRemark);return this;};
	 	/** and cso_remark is null */
 		public M csoRemarkNull(){if(this.get("csoRemarkNot")==null)this.put("csoRemarkNot", "");this.put("csoRemark", null);return this;};
 		/** not .... */
 		public M csoRemarkNot(){this.put("csoRemarkNot", "not");return this;};
		/** 历史日志        **/
		public M csoLog(Object csoLog){this.put("csoLog", csoLog);return this;};
	 	/** and cso_log is null */
 		public M csoLogNull(){if(this.get("csoLogNot")==null)this.put("csoLogNot", "");this.put("csoLog", null);return this;};
 		/** not .... */
 		public M csoLogNot(){this.put("csoLogNot", "not");return this;};
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csoEditor(Object csoEditor){this.put("csoEditor", csoEditor);return this;};
	 	/** and cso_editor is null */
 		public M csoEditorNull(){if(this.get("csoEditorNot")==null)this.put("csoEditorNot", "");this.put("csoEditor", null);return this;};
 		/** not .... */
 		public M csoEditorNot(){this.put("csoEditorNot", "not");return this;};
		/** 程序掩码 [非空]   1:催还车短信已发 2:设置不发还车短信     **/
		public M csoMask(Object csoMask){this.put("csoMask", csoMask);return this;};
	 	/** and cso_mask is null */
 		public M csoMaskNull(){if(this.get("csoMaskNot")==null)this.put("csoMaskNot", "");this.put("csoMask", null);return this;};
 		/** not .... */
 		public M csoMaskNot(){this.put("csoMaskNot", "not");return this;};
		/** 下位机版本    0:默认 1:测试     **/
		public M csoDeviceVesion(Object csoDeviceVesion){this.put("csoDeviceVesion", csoDeviceVesion);return this;};
	 	/** and cso_device_vesion is null */
 		public M csoDeviceVesionNull(){if(this.get("csoDeviceVesionNot")==null)this.put("csoDeviceVesionNot", "");this.put("csoDeviceVesion", null);return this;};
 		/** not .... */
 		public M csoDeviceVesionNot(){this.put("csoDeviceVesionNot", "not");return this;};
		/** 报警    0:默认 1:超距 2:超时 3:断线     **/
		public M csoAlarm(Object csoAlarm){this.put("csoAlarm", csoAlarm);return this;};
	 	/** and cso_alarm is null */
 		public M csoAlarmNull(){if(this.get("csoAlarmNot")==null)this.put("csoAlarmNot", "");this.put("csoAlarm", null);return this;};
 		/** not .... */
 		public M csoAlarmNot(){this.put("csoAlarmNot", "not");return this;};
		/** 数据状态 [非空]   0:未发送 1:已发送 2:已收到 3:不发送     **/
		public M csoState(Object csoState){this.put("csoState", csoState);return this;};
	 	/** and cso_state is null */
 		public M csoStateNull(){if(this.get("csoStateNot")==null)this.put("csoStateNot", "");this.put("csoState", null);return this;};
 		/** not .... */
 		public M csoStateNot(){this.put("csoStateNot", "not");return this;};
		/** 状态 [非空]   0:已预定 1:使用中 2:已还车 3:已取消 4:已完成 5:待处理 6:已退款 7:已作废     **/
		public M csoStatus(Object csoStatus){this.put("csoStatus", csoStatus);return this;};
	 	/** and cso_status is null */
 		public M csoStatusNull(){if(this.get("csoStatusNot")==null)this.put("csoStatusNot", "");this.put("csoStatus", null);return this;};
 		/** not .... */
 		public M csoStatusNot(){this.put("csoStatusNot", "not");return this;};
 		
 		
 		/** 取消来源 1：后台 3:app [可空]       **/
		public M csoCancelFrom(Object csoCancelFrom){this.put("csoCancelFrom", csoCancelFrom);return this;};
		/** and cso_cancel_from is null */
		public M csoCancelFromNull(){if(this.get("csoCancelFromNot")==null)this.put("csoCancelFromNot", "");this.put("csoCancelFrom", null);return this;};
		/** not .... */
		public M csoCancelFromNot(){this.put("csoCancelFromNot", "not");return this;};
		/** and cso_cancel_from >= ? */
		public M csoCancelFromMin(Object min){this.put("csoCancelFromMin", min);return this;};
		/** and cso_cancel_from <= ? */
		public M csoCancelFromMax(Object max){this.put("csoCancelFromMax", max);return this;};
 		
 		
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有系统订单 **/
		public @api List<CsOrder> list(Integer size){
			return getCsOrderList(this,size);
		}
		/** 获取系统订单分页 **/
		public @api Page<CsOrder> page(int page,int size){
			return getCsOrderPage(page, size , this);
		}
		/** 根据查询条件取系统订单 **/
		public @api CsOrder get(){
			return getCsOrder(this);
		}
		/** 获取系统订单数 **/
		public @api Long count(){
			return getCsOrderCount(this);
		}
		/** 获取系统订单表达式 **/
		public @api <T> T eval(String strEval){
			return getCsOrderEval(strEval,this);
		}
		/** 根据条件更新 **/
		public @api void update(){
			M where = null;
			M set = null;
			if(this.type==0){
				if(this.linked==null)throw new RuntimeException("未发set");
				where = this;
				set = this.linked;
			}else{
				if(this.linked==null)throw new RuntimeException("未发where条件");
				set = this;
				where = this.linked;
			}
			updateCsOrder(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 订单编号 [非空]       **/
		public final static @type(Long.class)  String csoId="csoId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csoHost="csoHost";
		/** 支付会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static @type(Long.class)  String csoPayMember="csoPayMember";
		/** 使用会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static @type(Long.class)  String csoUseMember="csoUseMember";
		/** 计费类型 [非空] [CsUserType]      **/
		public final static @type(Long.class)  String csoFeeType="csoFeeType";
		/** 网点区域 [非空] [CsArea]      **/
		public final static @type(Long.class)  String csoArea="csoArea";
		/** 取车网点 [非空] [CsOutlets]  {csoHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
		public final static @type(Long.class)  String csoOutlets="csoOutlets";
		/** 还车网点 [非空] [CsOutlets]  {csoHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
		public final static @type(Long.class)  String csoOutletsRet="csoOutletsRet";
		/** 预订车型 [非空] [CsCarModel]      **/
		public final static @type(Long.class)  String csoModel="csoModel";
		/** 预订车辆 [非空] [CsCar]  {csoOutlets}:cscOutlets {csoModel}:cscModel     **/
		public final static @type(Long.class)  String csoCar="csoCar";
		/** 车辆商家 [非空] [CsProvid]      **/
		public final static @type(Long.class)  String csoProvid="csoProvid";
		/** 车牌号码 [非空]       **/
		public final static @type(String.class) @like String csoCarNumber="csoCarNumber";
		/** 手机号码        **/
		public final static @type(String.class) @like String csoMobile="csoMobile";
		/** EV序列号 [非空]       **/
		public final static @type(String.class) @like String csoEvRfid="csoEvRfid";
		/** 宣传车？        **/
		public final static @type(Boolean.class)  String csoIsAdCar="csoIsAdCar";
		/** 订单开始时间 [非空]       **/
		public final static @type(Date.class)  String csoStartTime="csoStartTime";
	 	/** and cso_start_time >= ? */
 		public final static @type(Date.class) String csoStartTimeStart="csoStartTimeStart";
 		/** and cso_start_time <= ? */
 		public final static @type(Date.class) String csoStartTimeEnd="csoStartTimeEnd";
		/** 订单结束时间 [非空]       **/
		public final static @type(Date.class)  String csoFinishTime="csoFinishTime";
	 	/** and cso_finish_time >= ? */
 		public final static @type(Date.class) String csoFinishTimeStart="csoFinishTimeStart";
 		/** and cso_finish_time <= ? */
 		public final static @type(Date.class) String csoFinishTimeEnd="csoFinishTimeEnd";
		/** 是否长单        **/
		public final static @type(Boolean.class)  String csoLongOrder="csoLongOrder";
		/** 长单价格     元/月   **/
		public final static @type(Double.class)  String csoLongPrice="csoLongPrice";
		/** and cso_long_price >= ? */
		public final static @type(Double.class) String csoLongPriceMin="csoLongPriceMin";
		/** and cso_long_price <= ? */
		public final static @type(Double.class) String csoLongPriceMax="csoLongPriceMax";
		/** 用车方式    0:原借原还 1:A借B还     **/
		public final static @type(Short.class)  String csoUseType="csoUseType";
		/** 实际取车时间        **/
		public final static @type(Date.class)  String csoTakeTime="csoTakeTime";
	 	/** and cso_take_time >= ? */
 		public final static @type(Date.class) String csoTakeTimeStart="csoTakeTimeStart";
 		/** and cso_take_time <= ? */
 		public final static @type(Date.class) String csoTakeTimeEnd="csoTakeTimeEnd";
		/** 实际还车时间        **/
		public final static @type(Date.class)  String csoRetTime="csoRetTime";
	 	/** and cso_ret_time >= ? */
 		public final static @type(Date.class) String csoRetTimeStart="csoRetTimeStart";
 		/** and cso_ret_time <= ? */
 		public final static @type(Date.class) String csoRetTimeEnd="csoRetTimeEnd";
		/** 订单时长 [非空]       **/
		public final static @type(Double.class)  String csoDuration="csoDuration";
		/** and cso_duration >= ? */
		public final static @type(Double.class) String csoDurationMin="csoDurationMin";
		/** and cso_duration <= ? */
		public final static @type(Double.class) String csoDurationMax="csoDurationMax";
		/** 燃油里程 [非空]       **/
		public final static @type(Double.class)  String csoFuel="csoFuel";
		/** and cso_fuel >= ? */
		public final static @type(Double.class) String csoFuelMin="csoFuelMin";
		/** and cso_fuel <= ? */
		public final static @type(Double.class) String csoFuelMax="csoFuelMax";
		/** 电里程 [非空]       **/
		public final static @type(Double.class)  String csoElectric="csoElectric";
		/** and cso_electric >= ? */
		public final static @type(Double.class) String csoElectricMin="csoElectricMin";
		/** and cso_electric <= ? */
		public final static @type(Double.class) String csoElectricMax="csoElectricMax";
		/** 使用信用卡  [CsCreditCard]  {csoPayMember}:csccMember     **/
		public final static @type(Long.class)  String csoCreditCard="csoCreditCard";
		/** 信用卡帐单  [CsCreditBill]  {csoPayMember}:cscbMember     **/
		public final static @type(Long.class)  String csoCreditBill="csoCreditBill";
		/** 保险类型  [CsProduct]     select * from cs_product where csp_flag='INSURE' or csp_flag='SECURE' **/
		public final static @type(Long.class)  String csoInsureType="csoInsureType";
		/** 免费小时        **/
		public final static @type(Double.class)  String csoFreehour="csoFreehour";
		/** and cso_freehour >= ? */
		public final static @type(Double.class) String csoFreehourMin="csoFreehourMin";
		/** and cso_freehour <= ? */
		public final static @type(Double.class) String csoFreehourMax="csoFreehourMax";
		/** 折扣     0到1,比如0.75表示七五折   **/
		public final static @type(Double.class)  String csoRebate="csoRebate";
		/** and cso_rebate >= ? */
		public final static @type(Double.class) String csoRebateMin="csoRebateMin";
		/** and cso_rebate <= ? */
		public final static @type(Double.class) String csoRebateMax="csoRebateMax";
		/** 押金/保证金        **/
		public final static @type(Double.class)  String csoMarginNeed="csoMarginNeed";
		/** and cso_margin_need >= ? */
		public final static @type(Double.class) String csoMarginNeedMin="csoMarginNeedMin";
		/** and cso_margin_need <= ? */
		public final static @type(Double.class) String csoMarginNeedMax="csoMarginNeedMax";
		/** 预计/里程费        **/
		public final static @type(Double.class)  String csoPredict="csoPredict";
		/** and cso_predict >= ? */
		public final static @type(Double.class) String csoPredictMin="csoPredictMin";
		/** and cso_predict <= ? */
		public final static @type(Double.class) String csoPredictMax="csoPredictMax";
		/** 应付金额     应付为当前状态下的预计理论金额   **/
		public final static @type(Double.class)  String csoPayNeed="csoPayNeed";
		/** and cso_pay_need >= ? */
		public final static @type(Double.class) String csoPayNeedMin="csoPayNeedMin";
		/** and cso_pay_need <= ? */
		public final static @type(Double.class) String csoPayNeedMax="csoPayNeedMax";
		/** 实付金额        **/
		public final static @type(Double.class)  String csoPayReal="csoPayReal";
		/** and cso_pay_real >= ? */
		public final static @type(Double.class) String csoPayRealMin="csoPayRealMin";
		/** and cso_pay_real <= ? */
		public final static @type(Double.class) String csoPayRealMax="csoPayRealMax";
		/** 保证金支付        **/
		public final static @type(Double.class)  String csoPayMoney="csoPayMoney";
		/** and cso_pay_money >= ? */
		public final static @type(Double.class) String csoPayMoneyMin="csoPayMoneyMin";
		/** and cso_pay_money <= ? */
		public final static @type(Double.class) String csoPayMoneyMax="csoPayMoneyMax";
		/** 现金券支付        **/
		public final static @type(Double.class)  String csoPayCoupon="csoPayCoupon";
		/** and cso_pay_coupon >= ? */
		public final static @type(Double.class) String csoPayCouponMin="csoPayCouponMin";
		/** and cso_pay_coupon <= ? */
		public final static @type(Double.class) String csoPayCouponMax="csoPayCouponMax";
		/** 红包支付        **/
		public final static @type(Double.class)  String csoPayCoin="csoPayCoin";
		/** and cso_pay_coin >= ? */
		public final static @type(Double.class) String csoPayCoinMin="csoPayCoinMin";
		/** and cso_pay_coin <= ? */
		public final static @type(Double.class) String csoPayCoinMax="csoPayCoinMax";
		/** 电里程费        **/
		public final static @type(Double.class)  String csoPayKilom="csoPayKilom";
		/** and cso_pay_kilom >= ? */
		public final static @type(Double.class) String csoPayKilomMin="csoPayKilomMin";
		/** and cso_pay_kilom <= ? */
		public final static @type(Double.class) String csoPayKilomMax="csoPayKilomMax";
		/** 油里程费        **/
		public final static @type(Double.class)  String csoPayMileage="csoPayMileage";
		/** and cso_pay_mileage >= ? */
		public final static @type(Double.class) String csoPayMileageMin="csoPayMileageMin";
		/** and cso_pay_mileage <= ? */
		public final static @type(Double.class) String csoPayMileageMax="csoPayMileageMax";
		/** 租金费用        **/
		public final static @type(Double.class)  String csoPayRent="csoPayRent";
		/** and cso_pay_rent >= ? */
		public final static @type(Double.class) String csoPayRentMin="csoPayRentMin";
		/** and cso_pay_rent <= ? */
		public final static @type(Double.class) String csoPayRentMax="csoPayRentMax";
		/** 免责费用        **/
		public final static @type(Double.class)  String csoPayInsure="csoPayInsure";
		/** and cso_pay_insure >= ? */
		public final static @type(Double.class) String csoPayInsureMin="csoPayInsureMin";
		/** and cso_pay_insure <= ? */
		public final static @type(Double.class) String csoPayInsureMax="csoPayInsureMax";
		/** 超时费用        **/
		public final static @type(Double.class)  String csoPayTimeout="csoPayTimeout";
		/** and cso_pay_timeout >= ? */
		public final static @type(Double.class) String csoPayTimeoutMin="csoPayTimeoutMin";
		/** and cso_pay_timeout <= ? */
		public final static @type(Double.class) String csoPayTimeoutMax="csoPayTimeoutMax";
		/** 陪驾费用        **/
		public final static @type(Double.class)  String csoPayDriver="csoPayDriver";
		/** and cso_pay_driver >= ? */
		public final static @type(Double.class) String csoPayDriverMin="csoPayDriverMin";
		/** and cso_pay_driver <= ? */
		public final static @type(Double.class) String csoPayDriverMax="csoPayDriverMax";
		/** 计入营收    0:不计营收 1:计入营收     **/
		public final static @type(Short.class)  String csoIncome="csoIncome";
		/** 订单次数     标明该订单是会员第几次用车   **/
		public final static @type(Integer.class)  String csoUseIndex="csoUseIndex";
		/** and cso_use_index >= ? */
		public final static @type(Integer.class) String csoUseIndexMin="csoUseIndexMin";
		/** and cso_use_index <= ? */
		public final static @type(Integer.class) String csoUseIndexMax="csoUseIndexMax";
		/** 类型标识    0:默认 1:2014春节订单 2:2015春节订单     **/
		public final static @type(Short.class)  String csoFlag="csoFlag";
		/** 订单来源 [非空]   0:网站预订 1:后台预订 2:微信预订 3:App预订 4:企业预订 5:支付宝预订     **/
		public final static @type(Short.class)  String csoFrom="csoFrom";
		/** 来源信息        **/
		public final static @type(String.class)  String csoSrc="csoSrc";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csoUpdateTime="csoUpdateTime";
	 	/** and cso_update_time >= ? */
 		public final static @type(Date.class) String csoUpdateTimeStart="csoUpdateTimeStart";
 		/** and cso_update_time <= ? */
 		public final static @type(Date.class) String csoUpdateTimeEnd="csoUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csoAddTime="csoAddTime";
	 	/** and cso_add_time >= ? */
 		public final static @type(Date.class) String csoAddTimeStart="csoAddTimeStart";
 		/** and cso_add_time <= ? */
 		public final static @type(Date.class) String csoAddTimeEnd="csoAddTimeEnd";
		/** 结束时间        **/
		public final static @type(Date.class)  String csoEndTime="csoEndTime";
	 	/** and cso_end_time >= ? */
 		public final static @type(Date.class) String csoEndTimeStart="csoEndTimeStart";
 		/** and cso_end_time <= ? */
 		public final static @type(Date.class) String csoEndTimeEnd="csoEndTimeEnd";
		/** 序列号        **/
		public final static @type(String.class) @like String csoSerial="csoSerial";
		/** 授权码 [非空]       **/
		public final static @type(String.class) @like String csoCode="csoCode";
		/** 订单备注        **/
		public final static @type(String.class) @like String csoRemark="csoRemark";
		/** 历史日志        **/
		public final static @type(String.class) @like String csoLog="csoLog";
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csoEditor="csoEditor";
		/** 程序掩码 [非空]   1:催还车短信已发 2:设置不发还车短信     **/
		public final static @type(Long.class)  String csoMask="csoMask";
		/** 下位机版本    0:默认 1:测试     **/
		public final static @type(Short.class)  String csoDeviceVesion="csoDeviceVesion";
		/** 报警    0:默认 1:超距 2:超时 3:断线     **/
		public final static @type(Short.class)  String csoAlarm="csoAlarm";
		/** 数据状态 [非空]   0:未发送 1:已发送 2:已收到 3:不发送     **/
		public final static @type(Short.class)  String csoState="csoState";
		/** 状态 [非空]   0:已预定 1:使用中 2:已还车 3:已取消 4:已完成 5:待处理 6:已退款 7:已作废     **/
		public final static @type(Short.class)  String csoStatus="csoStatus";
		
		/** 取消来源 1：后台 3:app [可空]       **/
		public final static @type(Short.class)  String csoCancelFrom="csoCancelFrom";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 订单编号 [非空]       **/
		public final static String csoId="cso_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csoHost="cso_host";
		/** 支付会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static String csoPayMember="cso_pay_member";
		/** 使用会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static String csoUseMember="cso_use_member";
		/** 计费类型 [非空] [CsUserType]      **/
		public final static String csoFeeType="cso_fee_type";
		/** 网点区域 [非空] [CsArea]      **/
		public final static String csoArea="cso_area";
		/** 取车网点 [非空] [CsOutlets]  {csoHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
		public final static String csoOutlets="cso_outlets";
		/** 还车网点 [非空] [CsOutlets]  {csoHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
		public final static String csoOutletsRet="cso_outlets_ret";
		/** 预订车型 [非空] [CsCarModel]      **/
		public final static String csoModel="cso_model";
		/** 预订车辆 [非空] [CsCar]  {csoOutlets}:cscOutlets {csoModel}:cscModel     **/
		public final static String csoCar="cso_car";
		/** 车辆商家 [非空] [CsProvid]      **/
		public final static String csoProvid="cso_provid";
		/** 车牌号码 [非空]       **/
		public final static String csoCarNumber="cso_car_number";
		/** 手机号码        **/
		public final static String csoMobile="cso_mobile";
		/** EV序列号 [非空]       **/
		public final static String csoEvRfid="cso_ev_rfid";
		/** 宣传车？        **/
		public final static String csoIsAdCar="cso_is_ad_car";
		/** 订单开始时间 [非空]       **/
		public final static String csoStartTime="cso_start_time";
		/** 订单结束时间 [非空]       **/
		public final static String csoFinishTime="cso_finish_time";
		/** 是否长单        **/
		public final static String csoLongOrder="cso_long_order";
		/** 长单价格     元/月   **/
		public final static String csoLongPrice="cso_long_price";
		/** 用车方式    0:原借原还 1:A借B还     **/
		public final static String csoUseType="cso_use_type";
		/** 实际取车时间        **/
		public final static String csoTakeTime="cso_take_time";
		/** 实际还车时间        **/
		public final static String csoRetTime="cso_ret_time";
		/** 订单时长 [非空]       **/
		public final static String csoDuration="cso_duration";
		/** 燃油里程 [非空]       **/
		public final static String csoFuel="cso_fuel";
		/** 电里程 [非空]       **/
		public final static String csoElectric="cso_electric";
		/** 使用信用卡  [CsCreditCard]  {csoPayMember}:csccMember     **/
		public final static String csoCreditCard="cso_credit_card";
		/** 信用卡帐单  [CsCreditBill]  {csoPayMember}:cscbMember     **/
		public final static String csoCreditBill="cso_credit_bill";
		/** 保险类型  [CsProduct]     select * from cs_product where csp_flag='INSURE' or csp_flag='SECURE' **/
		public final static String csoInsureType="cso_insure_type";
		/** 免费小时        **/
		public final static String csoFreehour="cso_freehour";
		/** 折扣     0到1,比如0.75表示七五折   **/
		public final static String csoRebate="cso_rebate";
		/** 押金/保证金        **/
		public final static String csoMarginNeed="cso_margin_need";
		/** 预计/里程费        **/
		public final static String csoPredict="cso_predict";
		/** 应付金额     应付为当前状态下的预计理论金额   **/
		public final static String csoPayNeed="cso_pay_need";
		/** 实付金额        **/
		public final static String csoPayReal="cso_pay_real";
		/** 保证金支付        **/
		public final static String csoPayMoney="cso_pay_money";
		/** 现金券支付        **/
		public final static String csoPayCoupon="cso_pay_coupon";
		/** 红包支付        **/
		public final static String csoPayCoin="cso_pay_coin";
		/** 电里程费        **/
		public final static String csoPayKilom="cso_pay_kilom";
		/** 油里程费        **/
		public final static String csoPayMileage="cso_pay_mileage";
		/** 租金费用        **/
		public final static String csoPayRent="cso_pay_rent";
		/** 免责费用        **/
		public final static String csoPayInsure="cso_pay_insure";
		/** 超时费用        **/
		public final static String csoPayTimeout="cso_pay_timeout";
		/** 陪驾费用        **/
		public final static String csoPayDriver="cso_pay_driver";
		/** 计入营收    0:不计营收 1:计入营收     **/
		public final static String csoIncome="cso_income";
		/** 订单次数     标明该订单是会员第几次用车   **/
		public final static String csoUseIndex="cso_use_index";
		/** 类型标识    0:默认 1:2014春节订单 2:2015春节订单     **/
		public final static String csoFlag="cso_flag";
		/** 订单来源 [非空]   0:网站预订 1:后台预订 2:微信预订 3:App预订 4:企业预订 5:支付宝预订     **/
		public final static String csoFrom="cso_from";
		/** 来源信息        **/
		public final static String csoSrc="cso_src";
		/** 修改时间 [非空]       **/
		public final static String csoUpdateTime="cso_update_time";
		/** 添加时间 [非空]       **/
		public final static String csoAddTime="cso_add_time";
		/** 结束时间        **/
		public final static String csoEndTime="cso_end_time";
		/** 序列号        **/
		public final static String csoSerial="cso_serial";
		/** 授权码 [非空]       **/
		public final static String csoCode="cso_code";
		/** 订单备注        **/
		public final static String csoRemark="cso_remark";
		/** 历史日志        **/
		public final static String csoLog="cso_log";
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csoEditor="cso_editor";
		/** 程序掩码 [非空]   1:催还车短信已发 2:设置不发还车短信     **/
		public final static String csoMask="cso_mask";
		/** 下位机版本    0:默认 1:测试     **/
		public final static String csoDeviceVesion="cso_device_vesion";
		/** 报警    0:默认 1:超距 2:超时 3:断线     **/
		public final static String csoAlarm="cso_alarm";
		/** 数据状态 [非空]   0:未发送 1:已发送 2:已收到 3:不发送     **/
		public final static String csoState="cso_state";
		/** 状态 [非空]   0:已预定 1:使用中 2:已还车 3:已取消 4:已完成 5:待处理 6:已退款 7:已作废     **/
		public final static String csoStatus="cso_status";
		
		/** 取消来源 1：后台 3:app [可空]       **/
		public final static String csoCancelFrom="cso_cancel_from";
	 	public static String get(String name){
			try {
				Field field = C.class.getField(name);
				if(field!=null)
					return (String)field.get(null);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			return null;
		}
	}
	
	
	
	/**
	 * 通过页面标签获取对象
	 * @author uiu
	 */
	public static class Tag extends BodyTag{
		String name;
		Integer size;
		@Override
		public void execute(String strContent) {
			Map params = $.eval(strContent);
			if(size==null)
				$.Set(name,CsOrder.getCsOrder(params));
			else
				$.Set(name,CsOrder.getCsOrderList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取系统订单数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsOrder) $.GetRequest("CsOrder$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsOrder.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsOrder.getCsOrder(params);
			else
				value = CsOrder.getCsOrderList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsOrder.Get($.add(CsOrder.F.csoId,param));
		else if(!$.empty(param.toString()))
			value = CsOrder.get(Long.valueOf(param.toString()));
		$.SetRequest("CsOrder$"+param.hashCode(), value);
		return value;
	}
	
	
	
	
	/**字段设置标记队列**/
	private Map<String,Boolean> seted=new HashMap();
	public Boolean getSeted(String key){
		return seted.get(key);
	}
	public void setSeted(String key){
		seted.put(key,true);
	}
	/**同步未set的字段**/
	public void mergeSet(CsOrder old){
		Field[] fields = this.getClass().getDeclaredFields();
		try {
			for(Field field:fields){
				String key = field.getName();
				if(seted.get(key)==null && field.getAnnotation(column.class)!=null){
					field.setAccessible(true);
					field.set(this, field.get(old));					
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
		
	/**自定义变量存储**/
	private Map values=new HashMap();
	public Map getValues(){
		return values;
	}
	public void setValues(String name,Object value){
		values.put(name,value);
	}
	
	/**分表时的表名后缀**/
	private String suffix;
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
}