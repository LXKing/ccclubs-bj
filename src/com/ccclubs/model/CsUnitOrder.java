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

@namespace("unit/order")
public @caption("企业订单") @table("cs_unit_order") class CsUnitOrder implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csuo_id")   @primary  @note("  ") Long csuoId;// 主键 非空     
	private @caption("城市") @column("csuo_host")    @relate("$csuoHost") @RelateClass(SrvHost.class)  @note("  ") Long csuoHost;// 非空     
 	private SrvHost $csuoHost;//关联对象[运营城市]
	private @caption("系统订单") @column("csuo_order")    @relate("$csuoOrder") @RelateClass(CsOrder.class)  @note("  ") Long csuoOrder;// 非空     
 	private CsOrder $csuoOrder;//关联对象[系统订单]
	private @caption("专车订单") @column("csuo_special")    @relate("$csuoSpecial") @RelateClass(CsSpecialCar.class)  @note("  ") Long csuoSpecial;// 非空     
 	private CsSpecialCar $csuoSpecial;//关联对象[专车订单]
	private @caption("所属单位") @column("csuo_unit_info")    @relate("$csuoUnitInfo") @RelateClass(CsUnitInfo.class)  @note("  ") Long csuoUnitInfo;// 非空     
 	private CsUnitInfo $csuoUnitInfo;//关联对象[企业信息]
	private @caption("所属分组") @column("csuo_unit_group")    @relate("$csuoUnitGroup") @RelateClass(CsUnitGroup.class)  @note("  ") Long csuoUnitGroup;//     
 	private CsUnitGroup $csuoUnitGroup;//关联对象[分组信息]
	private @caption("支付帐号") @column("csuo_unit_member")    @relate("$csuoUnitMember") @RelateClass(CsMember.class)  @note("  ") Long csuoUnitMember;// 非空     
 	private CsMember $csuoUnitMember;//关联对象[会员帐号]
	private @caption("使用帐号") @column("csuo_unit_child")    @relate("$csuoUnitChild") @RelateClass(CsMember.class)  @note(" {csuoUnitInfo}:unit  ") Long csuoUnitChild;// 非空 {csuoUnitInfo}:unit    ${basePath}${proname}/unit/info_room.do 
 	private CsMember $csuoUnitChild;//关联对象[会员帐号]
	private @caption("预订车辆") @column("csuo_car")    @relate("$csuoCar") @RelateClass(CsCar.class)  @note("  ") Long csuoCar;//     
 	private CsCar $csuoCar;//关联对象[车辆]
	private @caption("车牌号码") @column("csuo_number")    @note("  ") String csuoNumber;//     
	private @caption("订单类型") @column("csuo_type")    @note(" 0:代理订单 1:公务用车 2:紧急公务  ") Short csuoType;// 非空 0:代理订单 1:公务用车 2:紧急公务     
	private @caption("用车人员") @column("csuo_person")    @relate("$csuoPerson") @RelateClass(CsUnitPerson.class)  @note("  ") Long csuoPerson;//     
 	private CsUnitPerson $csuoPerson;//关联对象[用车人员]
	private @caption("使用人姓名") @column("csuo_username")    @note("  ") String csuoUsername;//     
	private @caption("别名或标记") @column("csuo_alias")    @note("  ") String csuoAlias;//     
	private @caption("使用人手机") @column("csuo_mobile")    @note("  ") String csuoMobile;//     
	private @caption("订单摘要") @column("csuo_profile")    @note("  ") String csuoProfile;//     
	private @caption("身份证号") @column("csuo_idcard")    @note("  ") String csuoIdcard;//     
	private @caption("订单开始时间") @column("csuo_start_time")    @note("  ") Date csuoStartTime;// 非空     
	private @caption("订单结束时间") @column("csuo_finish_time")    @note("  ") Date csuoFinishTime;// 非空     
	private @caption("实际还车时间") @column("csuo_ret_time")    @note("  ") Date csuoRetTime;//     
	private @caption("免费小时(代理)") @column("csuo_free_hour")    @note("  ") Integer csuoFreeHour;//     
	private @caption("免费里程(代理)") @column("csuo_free_km")    @note("  ") Double csuoFreeKm;//     
	private @caption("天租价格(代理)") @column("csuo_day_price")    @note("  ") Double csuoDayPrice;//(单位：元)     
	private @caption("小时价格(代理)") @column("csuo_hour_price")    @note("  ") Double csuoHourPrice;//(单位：元)     
	private @caption("里程价格(代理)") @column("csuo_km_price")    @note("  ") Double csuoKmPrice;//     
	private @caption("应付金额(代理)") @column("csuo_pay_need")    @note("  ") Double csuoPayNeed;//(单位：元)     
	private @caption("实付金额(代理)") @column("csuo_pay_real")    @note("  ") Double csuoPayReal;//(单位：元)     
	private @caption("订单备注") @column("csuo_remark")    @note("  ") String csuoRemark;//     
	private @caption("添加时间") @column("csuo_add_time")    @note("  ") Date csuoAddTime;//     
	private @caption("修改时间") @column("csuo_update_time")    @note("  ") Date csuoUpdateTime;//     
	private @caption("里程数") @column("csuo_mileage")    @note("  ") Double csuoMileage;//(单位：公里)     
	private @caption("结算费用") @column("csuo_settlement")    @note("  ") Double csuoSettlement;//     
	private @caption("结算状态") @column("csuo_settle_state")    @note(" 0:未结算 1:已结算  ") Short csuoSettleState;// 0:未结算 1:已结算     
	private @caption("结算时间") @column("csuo_settle_time")    @note("  ") Date csuoSettleTime;//     
	private @caption("结账单") @column("csuo_statement")    @relate("$csuoStatement") @RelateClass(CsUnitBill.class)  @note("  ") Long csuoStatement;//     
 	private CsUnitBill $csuoStatement;//关联对象[企业对账]
	private @caption("订单来源") @column("csuo_from")    @note("  ") String csuoFrom;//     
	private @caption("审核时间") @column("csuo_check_time")    @note("  ") Date csuoCheckTime;//     
	private @caption("审核人") @column("csuo_checker")    @note("  ") String csuoChecker;//    SrvUser,CsUnitUser,CsUnitPerson 存储格式:类名@ID,其中类名包括SrvUser或CsUnitUser或CsUnitPerson
	private Object $csuoChecker;//泛关联对象，对象类名由csuoChecker字段中标明
	private @caption("状态") @column("csuo_state")    @note(" 1:等待审核 2:审核通过 3:审核未过 4:订单结束  ") Short csuoState;// 1:等待审核 2:审核通过 3:审核未过 4:订单结束     
	
	//默认构造函数
	public CsUnitOrder(){
	
	}
	
	//主键构造函数
	public CsUnitOrder(Long id){
		this.csuoId = id;
	}
	
	/**所有字段构造函数 CsUnitOrder(csuoHost,csuoOrder,csuoSpecial,csuoUnitInfo,csuoUnitGroup,csuoUnitMember,csuoUnitChild,csuoCar,csuoNumber,csuoType,csuoPerson,csuoUsername,csuoAlias,csuoMobile,csuoProfile,csuoIdcard,csuoStartTime,csuoFinishTime,csuoRetTime,csuoFreeHour,csuoFreeKm,csuoDayPrice,csuoHourPrice,csuoKmPrice,csuoPayNeed,csuoPayReal,csuoRemark,csuoAddTime,csuoUpdateTime,csuoMileage,csuoSettlement,csuoSettleState,csuoSettleTime,csuoStatement,csuoFrom,csuoCheckTime,csuoChecker,csuoState)
	 CsUnitOrder(
	 	$.getLong("csuoHost")//城市 [非空]
	 	,$.getLong("csuoOrder")//系统订单 [非空]
	 	,$.getLong("csuoSpecial")//专车订单 [非空]
	 	,$.getLong("csuoUnitInfo")//所属单位 [非空]
	 	,$.getLong("csuoUnitGroup")//所属分组
	 	,$.getLong("csuoUnitMember")//支付帐号 [非空]
	 	,$.getLong("csuoUnitChild")//使用帐号 [非空]
	 	,$.getLong("csuoCar")//预订车辆
	 	,$.getString("csuoNumber")//车牌号码
	 	,$.getShort("csuoType")//订单类型 [非空]
	 	,$.getLong("csuoPerson")//用车人员
	 	,$.getString("csuoUsername")//使用人姓名
	 	,$.getString("csuoAlias")//别名或标记
	 	,$.getString("csuoMobile")//使用人手机
	 	,$.getString("csuoProfile")//订单摘要
	 	,$.getString("csuoIdcard")//身份证号
	 	,$.getDate("csuoStartTime")//订单开始时间 [非空]
	 	,$.getDate("csuoFinishTime")//订单结束时间 [非空]
	 	,$.getDate("csuoRetTime")//实际还车时间
	 	,$.getInteger("csuoFreeHour")//免费小时(代理)
	 	,$.getDouble("csuoFreeKm")//免费里程(代理)
	 	,$.getDouble("csuoDayPrice")//天租价格(代理)
	 	,$.getDouble("csuoHourPrice")//小时价格(代理)
	 	,$.getDouble("csuoKmPrice")//里程价格(代理)
	 	,$.getDouble("csuoPayNeed")//应付金额(代理)
	 	,$.getDouble("csuoPayReal")//实付金额(代理)
	 	,$.getString("csuoRemark")//订单备注
	 	,$.getDate("csuoAddTime")//添加时间
	 	,$.getDate("csuoUpdateTime")//修改时间
	 	,$.getDouble("csuoMileage")//里程数
	 	,$.getDouble("csuoSettlement")//结算费用
	 	,$.getShort("csuoSettleState")//结算状态
	 	,$.getDate("csuoSettleTime")//结算时间
	 	,$.getLong("csuoStatement")//结账单
	 	,$.getString("csuoFrom")//订单来源
	 	,$.getDate("csuoCheckTime")//审核时间
	 	,$.getString("csuoChecker")//审核人
	 	,$.getShort("csuoState")//状态
	 )
	**/
	public CsUnitOrder(Long csuoHost,Long csuoOrder,Long csuoSpecial,Long csuoUnitInfo,Long csuoUnitGroup,Long csuoUnitMember,Long csuoUnitChild,Long csuoCar,String csuoNumber,Short csuoType,Long csuoPerson,String csuoUsername,String csuoAlias,String csuoMobile,String csuoProfile,String csuoIdcard,Date csuoStartTime,Date csuoFinishTime,Date csuoRetTime,Integer csuoFreeHour,Double csuoFreeKm,Double csuoDayPrice,Double csuoHourPrice,Double csuoKmPrice,Double csuoPayNeed,Double csuoPayReal,String csuoRemark,Date csuoAddTime,Date csuoUpdateTime,Double csuoMileage,Double csuoSettlement,Short csuoSettleState,Date csuoSettleTime,Long csuoStatement,String csuoFrom,Date csuoCheckTime,String csuoChecker,Short csuoState){
		this.csuoHost=csuoHost;
		this.csuoOrder=csuoOrder;
		this.csuoSpecial=csuoSpecial;
		this.csuoUnitInfo=csuoUnitInfo;
		this.csuoUnitGroup=csuoUnitGroup;
		this.csuoUnitMember=csuoUnitMember;
		this.csuoUnitChild=csuoUnitChild;
		this.csuoCar=csuoCar;
		this.csuoNumber=csuoNumber;
		this.csuoType=csuoType;
		this.csuoPerson=csuoPerson;
		this.csuoUsername=csuoUsername;
		this.csuoAlias=csuoAlias;
		this.csuoMobile=csuoMobile;
		this.csuoProfile=csuoProfile;
		this.csuoIdcard=csuoIdcard;
		this.csuoStartTime=csuoStartTime;
		this.csuoFinishTime=csuoFinishTime;
		this.csuoRetTime=csuoRetTime;
		this.csuoFreeHour=csuoFreeHour;
		this.csuoFreeKm=csuoFreeKm;
		this.csuoDayPrice=csuoDayPrice;
		this.csuoHourPrice=csuoHourPrice;
		this.csuoKmPrice=csuoKmPrice;
		this.csuoPayNeed=csuoPayNeed;
		this.csuoPayReal=csuoPayReal;
		this.csuoRemark=csuoRemark;
		this.csuoAddTime=csuoAddTime;
		this.csuoUpdateTime=csuoUpdateTime;
		this.csuoMileage=csuoMileage;
		this.csuoSettlement=csuoSettlement;
		this.csuoSettleState=csuoSettleState;
		this.csuoSettleTime=csuoSettleTime;
		this.csuoStatement=csuoStatement;
		this.csuoFrom=csuoFrom;
		this.csuoCheckTime=csuoCheckTime;
		this.csuoChecker=csuoChecker;
		this.csuoState=csuoState;
	}
	
	//设置非空字段
	public CsUnitOrder setNotNull(Long csuoHost,Long csuoOrder,Long csuoSpecial,Long csuoUnitInfo,Long csuoUnitMember,Long csuoUnitChild,Short csuoType,Date csuoStartTime,Date csuoFinishTime){
		this.csuoHost=csuoHost;
		this.csuoOrder=csuoOrder;
		this.csuoSpecial=csuoSpecial;
		this.csuoUnitInfo=csuoUnitInfo;
		this.csuoUnitMember=csuoUnitMember;
		this.csuoUnitChild=csuoUnitChild;
		this.csuoType=csuoType;
		this.csuoStartTime=csuoStartTime;
		this.csuoFinishTime=csuoFinishTime;
		return this;
	}
	/** 编号 [非空]       **/
	public CsUnitOrder csuoId(Long csuoId){
		this.csuoId = csuoId;
		this.setSeted(F.csuoId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsUnitOrder csuoHost(Long csuoHost){
		this.csuoHost = csuoHost;
		this.setSeted(F.csuoHost);
		return this;
	}
	/** 系统订单 [非空] [CsOrder]      **/
	public CsUnitOrder csuoOrder(Long csuoOrder){
		this.csuoOrder = csuoOrder;
		this.setSeted(F.csuoOrder);
		return this;
	}
	/** 专车订单 [非空] [CsSpecialCar]      **/
	public CsUnitOrder csuoSpecial(Long csuoSpecial){
		this.csuoSpecial = csuoSpecial;
		this.setSeted(F.csuoSpecial);
		return this;
	}
	/** 所属单位 [非空] [CsUnitInfo]      **/
	public CsUnitOrder csuoUnitInfo(Long csuoUnitInfo){
		this.csuoUnitInfo = csuoUnitInfo;
		this.setSeted(F.csuoUnitInfo);
		return this;
	}
	/** 所属分组  [CsUnitGroup]      **/
	public CsUnitOrder csuoUnitGroup(Long csuoUnitGroup){
		this.csuoUnitGroup = csuoUnitGroup;
		this.setSeted(F.csuoUnitGroup);
		return this;
	}
	/** 支付帐号 [非空] [CsMember]      **/
	public CsUnitOrder csuoUnitMember(Long csuoUnitMember){
		this.csuoUnitMember = csuoUnitMember;
		this.setSeted(F.csuoUnitMember);
		return this;
	}
	/** 使用帐号 [非空] [CsMember]  {csuoUnitInfo}:unit    ${basePath}${proname}/unit/info_room.do **/
	public CsUnitOrder csuoUnitChild(Long csuoUnitChild){
		this.csuoUnitChild = csuoUnitChild;
		this.setSeted(F.csuoUnitChild);
		return this;
	}
	/** 预订车辆  [CsCar]      **/
	public CsUnitOrder csuoCar(Long csuoCar){
		this.csuoCar = csuoCar;
		this.setSeted(F.csuoCar);
		return this;
	}
	/** 车牌号码        **/
	public CsUnitOrder csuoNumber(String csuoNumber){
		this.csuoNumber = csuoNumber;
		this.setSeted(F.csuoNumber);
		return this;
	}
	/** 订单类型 [非空]   0:代理订单 1:公务用车 2:紧急公务     **/
	public CsUnitOrder csuoType(Short csuoType){
		this.csuoType = csuoType;
		this.setSeted(F.csuoType);
		return this;
	}
	/** 用车人员  [CsUnitPerson]      **/
	public CsUnitOrder csuoPerson(Long csuoPerson){
		this.csuoPerson = csuoPerson;
		this.setSeted(F.csuoPerson);
		return this;
	}
	/** 使用人姓名        **/
	public CsUnitOrder csuoUsername(String csuoUsername){
		this.csuoUsername = csuoUsername;
		this.setSeted(F.csuoUsername);
		return this;
	}
	/** 别名或标记        **/
	public CsUnitOrder csuoAlias(String csuoAlias){
		this.csuoAlias = csuoAlias;
		this.setSeted(F.csuoAlias);
		return this;
	}
	/** 使用人手机        **/
	public CsUnitOrder csuoMobile(String csuoMobile){
		this.csuoMobile = csuoMobile;
		this.setSeted(F.csuoMobile);
		return this;
	}
	/** 订单摘要        **/
	public CsUnitOrder csuoProfile(String csuoProfile){
		this.csuoProfile = csuoProfile;
		this.setSeted(F.csuoProfile);
		return this;
	}
	/** 身份证号        **/
	public CsUnitOrder csuoIdcard(String csuoIdcard){
		this.csuoIdcard = csuoIdcard;
		this.setSeted(F.csuoIdcard);
		return this;
	}
	/** 订单开始时间 [非空]       **/
	public CsUnitOrder csuoStartTime(Date csuoStartTime){
		this.csuoStartTime = csuoStartTime;
		this.setSeted(F.csuoStartTime);
		return this;
	}
	/** 订单结束时间 [非空]       **/
	public CsUnitOrder csuoFinishTime(Date csuoFinishTime){
		this.csuoFinishTime = csuoFinishTime;
		this.setSeted(F.csuoFinishTime);
		return this;
	}
	/** 实际还车时间        **/
	public CsUnitOrder csuoRetTime(Date csuoRetTime){
		this.csuoRetTime = csuoRetTime;
		this.setSeted(F.csuoRetTime);
		return this;
	}
	/** 免费小时(代理)        **/
	public CsUnitOrder csuoFreeHour(Integer csuoFreeHour){
		this.csuoFreeHour = csuoFreeHour;
		this.setSeted(F.csuoFreeHour);
		return this;
	}
	/** 免费里程(代理)        **/
	public CsUnitOrder csuoFreeKm(Double csuoFreeKm){
		this.csuoFreeKm = csuoFreeKm;
		this.setSeted(F.csuoFreeKm);
		return this;
	}
	/** 天租价格(代理)        **/
	public CsUnitOrder csuoDayPrice(Double csuoDayPrice){
		this.csuoDayPrice = csuoDayPrice;
		this.setSeted(F.csuoDayPrice);
		return this;
	}
	/** 小时价格(代理)        **/
	public CsUnitOrder csuoHourPrice(Double csuoHourPrice){
		this.csuoHourPrice = csuoHourPrice;
		this.setSeted(F.csuoHourPrice);
		return this;
	}
	/** 里程价格(代理)        **/
	public CsUnitOrder csuoKmPrice(Double csuoKmPrice){
		this.csuoKmPrice = csuoKmPrice;
		this.setSeted(F.csuoKmPrice);
		return this;
	}
	/** 应付金额(代理)        **/
	public CsUnitOrder csuoPayNeed(Double csuoPayNeed){
		this.csuoPayNeed = csuoPayNeed;
		this.setSeted(F.csuoPayNeed);
		return this;
	}
	/** 实付金额(代理)        **/
	public CsUnitOrder csuoPayReal(Double csuoPayReal){
		this.csuoPayReal = csuoPayReal;
		this.setSeted(F.csuoPayReal);
		return this;
	}
	/** 订单备注        **/
	public CsUnitOrder csuoRemark(String csuoRemark){
		this.csuoRemark = csuoRemark;
		this.setSeted(F.csuoRemark);
		return this;
	}
	/** 添加时间        **/
	public CsUnitOrder csuoAddTime(Date csuoAddTime){
		this.csuoAddTime = csuoAddTime;
		this.setSeted(F.csuoAddTime);
		return this;
	}
	/** 修改时间        **/
	public CsUnitOrder csuoUpdateTime(Date csuoUpdateTime){
		this.csuoUpdateTime = csuoUpdateTime;
		this.setSeted(F.csuoUpdateTime);
		return this;
	}
	/** 里程数        **/
	public CsUnitOrder csuoMileage(Double csuoMileage){
		this.csuoMileage = csuoMileage;
		this.setSeted(F.csuoMileage);
		return this;
	}
	/** 结算费用        **/
	public CsUnitOrder csuoSettlement(Double csuoSettlement){
		this.csuoSettlement = csuoSettlement;
		this.setSeted(F.csuoSettlement);
		return this;
	}
	/** 结算状态    0:未结算 1:已结算     **/
	public CsUnitOrder csuoSettleState(Short csuoSettleState){
		this.csuoSettleState = csuoSettleState;
		this.setSeted(F.csuoSettleState);
		return this;
	}
	/** 结算时间        **/
	public CsUnitOrder csuoSettleTime(Date csuoSettleTime){
		this.csuoSettleTime = csuoSettleTime;
		this.setSeted(F.csuoSettleTime);
		return this;
	}
	/** 结账单  [CsUnitBill]      **/
	public CsUnitOrder csuoStatement(Long csuoStatement){
		this.csuoStatement = csuoStatement;
		this.setSeted(F.csuoStatement);
		return this;
	}
	/** 订单来源        **/
	public CsUnitOrder csuoFrom(String csuoFrom){
		this.csuoFrom = csuoFrom;
		this.setSeted(F.csuoFrom);
		return this;
	}
	/** 审核时间        **/
	public CsUnitOrder csuoCheckTime(Date csuoCheckTime){
		this.csuoCheckTime = csuoCheckTime;
		this.setSeted(F.csuoCheckTime);
		return this;
	}
	/** 审核人       SrvUser,CsUnitUser,CsUnitPerson **/
	public CsUnitOrder csuoChecker(String csuoChecker){
		this.csuoChecker = csuoChecker;
		this.setSeted(F.csuoChecker);
		return this;
	}
	/** 状态    1:等待审核 2:审核通过 3:审核未过 4:订单结束     **/
	public CsUnitOrder csuoState(Short csuoState){
		this.csuoState = csuoState;
		this.setSeted(F.csuoState);
		return this;
	}
	
	
	//克隆对象
	public CsUnitOrder clone(){
		CsUnitOrder clone = new CsUnitOrder();
		clone.csuoHost=this.csuoHost;
		clone.csuoOrder=this.csuoOrder;
		clone.csuoSpecial=this.csuoSpecial;
		clone.csuoUnitInfo=this.csuoUnitInfo;
		clone.csuoUnitMember=this.csuoUnitMember;
		clone.csuoUnitChild=this.csuoUnitChild;
		clone.csuoType=this.csuoType;
		clone.csuoStartTime=this.csuoStartTime;
		clone.csuoFinishTime=this.csuoFinishTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取企业订单
	 * @param id
	 * @return
	 */
	public static @api CsUnitOrder get(Long id){		
		return getCsUnitOrderById(id);
	}
	/**
	 * 获取所有企业订单
	 * @return
	 */
	public static @api List<CsUnitOrder> list(Map params,Integer size){
		return getCsUnitOrderList(params,size);
	}
	/**
	 * 获取企业订单分页
	 * @return
	 */
	public static @api Page<CsUnitOrder> page(int page,int size,Map params){
		return getCsUnitOrderPage(page, size , params);
	}
	/**
	 * 根据查询条件取企业订单
	 * @param params
	 * @return
	 */
	public static @api CsUnitOrder Get(Map params){
		return getCsUnitOrder(params);
	}
	/**
	 * 获取企业订单数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsUnitOrderCount(params);
	}
	/**
	 * 获取企业订单数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsUnitOrderEval(eval,params);
	}
	
	/**
	 * 根据ID取企业订单
	 * @param id
	 * @return
	 */
	public static @api CsUnitOrder getCsUnitOrderById(Long id){		
		CsUnitOrder csUnitOrder = (CsUnitOrder) $.GetRequest("CsUnitOrder$"+id);
		if(csUnitOrder!=null)
			return csUnitOrder;
		com.ccclubs.service.admin.ICsUnitOrderService csUnitOrderService = $.GetSpringBean("csUnitOrderService");		
		return csUnitOrderService.getCsUnitOrderById(id);
	}
	
	
	/**
	 * 根据ID取企业订单的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsUnitOrder.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsUnitOrder csUnitOrder = get(id);
		if(csUnitOrder!=null){
			String strValue = csUnitOrder.getCsuoOrder$();
			if(!"CsuoOrder".equals("CsuoOrder"))
				strValue+="("+csUnitOrder.getCsuoOrder$()+")";
			if(!"CsuoOrder".equals("CsuoSpecial"))
				strValue+="("+csUnitOrder.getCsuoSpecial$()+")";
			MemCache.setValue(CsUnitOrder.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsuoOrder$();
		if(!"CsuoOrder".equals("CsuoOrder"))
			keyValue+="("+this.getCsuoOrder$()+")";
		if(!"CsuoOrder".equals("CsuoSpecial"))
			keyValue+="("+this.getCsuoSpecial$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有企业订单
	 * @return
	 */
	public static @api List<CsUnitOrder> getCsUnitOrderList(Map params,Integer size){
		com.ccclubs.service.admin.ICsUnitOrderService csUnitOrderService = $.GetSpringBean("csUnitOrderService");
		return csUnitOrderService.getCsUnitOrderList(params, size);
	}
	
	/**
	 * 获取企业订单分页
	 * @return
	 */
	public static @api Page<CsUnitOrder> getCsUnitOrderPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsUnitOrderService csUnitOrderService = $.GetSpringBean("csUnitOrderService");
		return csUnitOrderService.getCsUnitOrderPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取企业订单
	 * @param params
	 * @return
	 */
	public static @api CsUnitOrder getCsUnitOrder(Map params){
		com.ccclubs.service.admin.ICsUnitOrderService csUnitOrderService = $.GetSpringBean("csUnitOrderService");
		return csUnitOrderService.getCsUnitOrder(params);
	}
	
	/**
	 * 获取企业订单数
	 * @return
	 */
	public static @api Long getCsUnitOrderCount(Map params){
		com.ccclubs.service.admin.ICsUnitOrderService csUnitOrderService = $.GetSpringBean("csUnitOrderService");
		return csUnitOrderService.getCsUnitOrderCount(params);
	}
		
		
	/**
	 * 获取企业订单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsUnitOrderEval(String eval,Map params){
		com.ccclubs.service.admin.ICsUnitOrderService csUnitOrderService = $.GetSpringBean("csUnitOrderService");
		return csUnitOrderService.getCsUnitOrderEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsUnitOrder(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsUnitOrderService csUnitOrderService = $.GetSpringBean("csUnitOrderService");
		csUnitOrderService.updateCsUnitOrderByConfirm(set, where);
	}
	
	
	/**
	 * 保存企业订单对象
	 * @param params
	 * @return
	 */
	public CsUnitOrder save(){
		com.ccclubs.service.admin.ICsUnitOrderService csUnitOrderService = $.GetSpringBean("csUnitOrderService");
		if(this.getCsuoId()!=null)
			csUnitOrderService.updateCsUnitOrder(this);
		else
			return csUnitOrderService.saveCsUnitOrder(this);
		return this;
	}
	
	
	/**
	 * 更新企业订单对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsUnitOrderService csUnitOrderService = $.GetSpringBean("csUnitOrderService");
		csUnitOrderService.updateCsUnitOrder$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsUnitOrderService csUnitOrderService = $.GetSpringBean("csUnitOrderService");
		if($.equals($.config("logic_delete"),"true"))
			csUnitOrderService.removeCsUnitOrderById(this.getCsuoId());
		else
			csUnitOrderService.deleteCsUnitOrderById(this.getCsuoId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsUnitOrderService csUnitOrderService = $.GetSpringBean("csUnitOrderService");
		return csUnitOrderService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsuoId(){
		return this.csuoId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsuoId$(){
		String strValue="";
		 strValue=$.str(this.getCsuoId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsuoId(Long csuoId){
		this.csuoId = csuoId;
		this.setSeted(F.csuoId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsuoHost(){
		return this.csuoHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsuoHost$(){
		String strValue="";
		if(this.getCsuoHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsuoHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsuoHost(Long csuoHost){
		this.csuoHost = csuoHost;
		this.setSeted(F.csuoHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csuoHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsuoHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsuoHost()!=null){
 			srvHost = SrvHost.get(this.getCsuoHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsuoHost(), srvHost);
	 	return srvHost;
	}
	/*******************************系统订单**********************************/	
	/**
	* 系统订单 [非空] [CsOrder]     
	**/
	public Long getCsuoOrder(){
		return this.csuoOrder;
	}
	/**
	* 获取系统订单格式化(toString)
	**/
	public String getCsuoOrder$(){
		String strValue="";
		if(this.getCsuoOrder()!=null){
				strValue+=$.str(CsOrder.getKeyValue(this.getCsuoOrder()));
		}			
	 	 return strValue;
	}
	/**
	* 系统订单 [非空] [CsOrder]     
	**/
	public void setCsuoOrder(Long csuoOrder){
		this.csuoOrder = csuoOrder;
		this.setSeted(F.csuoOrder);
	}
	/**
	* 获取关联对象[系统订单]
	**/	 			
 	public CsOrder get$csuoOrder(){
 		com.ccclubs.model.CsOrder csOrder = (com.ccclubs.model.CsOrder) $.GetRequest("CsOrder$"+this.getCsuoOrder());
 		if(csOrder!=null)
			return csOrder;
		if(this.getCsuoOrder()!=null){
 			csOrder = CsOrder.get(this.getCsuoOrder());
 		}
 		$.SetRequest("CsOrder$"+this.getCsuoOrder(), csOrder);
	 	return csOrder;
	}
	/*******************************专车订单**********************************/	
	/**
	* 专车订单 [非空] [CsSpecialCar]     
	**/
	public Long getCsuoSpecial(){
		return this.csuoSpecial;
	}
	/**
	* 获取专车订单格式化(toString)
	**/
	public String getCsuoSpecial$(){
		String strValue="";
		if(this.getCsuoSpecial()!=null){
				strValue+=$.str(CsSpecialCar.getKeyValue(this.getCsuoSpecial()));
		}			
	 	 return strValue;
	}
	/**
	* 专车订单 [非空] [CsSpecialCar]     
	**/
	public void setCsuoSpecial(Long csuoSpecial){
		this.csuoSpecial = csuoSpecial;
		this.setSeted(F.csuoSpecial);
	}
	/**
	* 获取关联对象[专车订单]
	**/	 			
 	public CsSpecialCar get$csuoSpecial(){
 		com.ccclubs.model.CsSpecialCar csSpecialCar = (com.ccclubs.model.CsSpecialCar) $.GetRequest("CsSpecialCar$"+this.getCsuoSpecial());
 		if(csSpecialCar!=null)
			return csSpecialCar;
		if(this.getCsuoSpecial()!=null){
 			csSpecialCar = CsSpecialCar.get(this.getCsuoSpecial());
 		}
 		$.SetRequest("CsSpecialCar$"+this.getCsuoSpecial(), csSpecialCar);
	 	return csSpecialCar;
	}
	/*******************************所属单位**********************************/	
	/**
	* 所属单位 [非空] [CsUnitInfo]     
	**/
	public Long getCsuoUnitInfo(){
		return this.csuoUnitInfo;
	}
	/**
	* 获取所属单位格式化(toString)
	**/
	public String getCsuoUnitInfo$(){
		String strValue="";
		if(this.getCsuoUnitInfo()!=null){
				strValue+=$.str(CsUnitInfo.getKeyValue(this.getCsuoUnitInfo()));
		}			
	 	 return strValue;
	}
	/**
	* 所属单位 [非空] [CsUnitInfo]     
	**/
	public void setCsuoUnitInfo(Long csuoUnitInfo){
		this.csuoUnitInfo = csuoUnitInfo;
		this.setSeted(F.csuoUnitInfo);
	}
	/**
	* 获取关联对象[企业信息]
	**/	 			
 	public CsUnitInfo get$csuoUnitInfo(){
 		com.ccclubs.model.CsUnitInfo csUnitInfo = (com.ccclubs.model.CsUnitInfo) $.GetRequest("CsUnitInfo$"+this.getCsuoUnitInfo());
 		if(csUnitInfo!=null)
			return csUnitInfo;
		if(this.getCsuoUnitInfo()!=null){
 			csUnitInfo = CsUnitInfo.get(this.getCsuoUnitInfo());
 		}
 		$.SetRequest("CsUnitInfo$"+this.getCsuoUnitInfo(), csUnitInfo);
	 	return csUnitInfo;
	}
	/*******************************所属分组**********************************/	
	/**
	* 所属分组  [CsUnitGroup]     
	**/
	public Long getCsuoUnitGroup(){
		return this.csuoUnitGroup;
	}
	/**
	* 获取所属分组格式化(toString)
	**/
	public String getCsuoUnitGroup$(){
		String strValue="";
		if(this.getCsuoUnitGroup()!=null){
				strValue+=$.str(CsUnitGroup.getKeyValue(this.getCsuoUnitGroup()));
		}			
	 	 return strValue;
	}
	/**
	* 所属分组  [CsUnitGroup]     
	**/
	public void setCsuoUnitGroup(Long csuoUnitGroup){
		this.csuoUnitGroup = csuoUnitGroup;
		this.setSeted(F.csuoUnitGroup);
	}
	/**
	* 获取关联对象[分组信息]
	**/	 			
 	public CsUnitGroup get$csuoUnitGroup(){
 		com.ccclubs.model.CsUnitGroup csUnitGroup = (com.ccclubs.model.CsUnitGroup) $.GetRequest("CsUnitGroup$"+this.getCsuoUnitGroup());
 		if(csUnitGroup!=null)
			return csUnitGroup;
		if(this.getCsuoUnitGroup()!=null){
 			csUnitGroup = CsUnitGroup.get(this.getCsuoUnitGroup());
 		}
 		$.SetRequest("CsUnitGroup$"+this.getCsuoUnitGroup(), csUnitGroup);
	 	return csUnitGroup;
	}
	/*******************************支付帐号**********************************/	
	/**
	* 支付帐号 [非空] [CsMember]     
	**/
	public Long getCsuoUnitMember(){
		return this.csuoUnitMember;
	}
	/**
	* 获取支付帐号格式化(toString)
	**/
	public String getCsuoUnitMember$(){
		String strValue="";
		if(this.getCsuoUnitMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsuoUnitMember()));
		}			
	 	 return strValue;
	}
	/**
	* 支付帐号 [非空] [CsMember]     
	**/
	public void setCsuoUnitMember(Long csuoUnitMember){
		this.csuoUnitMember = csuoUnitMember;
		this.setSeted(F.csuoUnitMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csuoUnitMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsuoUnitMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsuoUnitMember()!=null){
 			csMember = CsMember.get(this.getCsuoUnitMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsuoUnitMember(), csMember);
	 	return csMember;
	}
	/*******************************使用帐号**********************************/	
	/**
	* 使用帐号 [非空] [CsMember]  {csuoUnitInfo}:unit    ${basePath}${proname}/unit/info_room.do
	**/
	public Long getCsuoUnitChild(){
		return this.csuoUnitChild;
	}
	/**
	* 获取使用帐号格式化(toString)
	**/
	public String getCsuoUnitChild$(){
		String strValue="";
		if(this.getCsuoUnitChild()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsuoUnitChild()));
		}			
	 	 return strValue;
	}
	/**
	* 使用帐号 [非空] [CsMember]  {csuoUnitInfo}:unit    ${basePath}${proname}/unit/info_room.do
	**/
	public void setCsuoUnitChild(Long csuoUnitChild){
		this.csuoUnitChild = csuoUnitChild;
		this.setSeted(F.csuoUnitChild);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csuoUnitChild(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsuoUnitChild());
 		if(csMember!=null)
			return csMember;
		if(this.getCsuoUnitChild()!=null){
 			csMember = CsMember.get(this.getCsuoUnitChild());
 		}
 		$.SetRequest("CsMember$"+this.getCsuoUnitChild(), csMember);
	 	return csMember;
	}
	/*******************************预订车辆**********************************/	
	/**
	* 预订车辆  [CsCar]     
	**/
	public Long getCsuoCar(){
		return this.csuoCar;
	}
	/**
	* 获取预订车辆格式化(toString)
	**/
	public String getCsuoCar$(){
		String strValue="";
		if(this.getCsuoCar()!=null){
				strValue+=$.str(CsCar.getKeyValue(this.getCsuoCar()));
		}			
	 	 return strValue;
	}
	/**
	* 预订车辆  [CsCar]     
	**/
	public void setCsuoCar(Long csuoCar){
		this.csuoCar = csuoCar;
		this.setSeted(F.csuoCar);
	}
	/**
	* 获取关联对象[车辆]
	**/	 			
 	public CsCar get$csuoCar(){
 		com.ccclubs.model.CsCar csCar = (com.ccclubs.model.CsCar) $.GetRequest("CsCar$"+this.getCsuoCar());
 		if(csCar!=null)
			return csCar;
		if(this.getCsuoCar()!=null){
 			csCar = CsCar.get(this.getCsuoCar());
 		}
 		$.SetRequest("CsCar$"+this.getCsuoCar(), csCar);
	 	return csCar;
	}
	/*******************************车牌号码**********************************/	
	/**
	* 车牌号码       
	**/
	public String getCsuoNumber(){
		return this.csuoNumber;
	}
	/**
	* 获取车牌号码格式化(toString)
	**/
	public String getCsuoNumber$(){
		String strValue="";
		 strValue=$.str(this.getCsuoNumber());
	 	 return strValue;
	}
	/**
	* 车牌号码       
	**/
	public void setCsuoNumber(String csuoNumber){
		this.csuoNumber = csuoNumber;
		this.setSeted(F.csuoNumber);
	}
	/*******************************订单类型**********************************/	
	/**
	* 订单类型 [非空]   0:代理订单 1:公务用车 2:紧急公务    
	**/
	public Short getCsuoType(){
		return this.csuoType;
	}
	/**
	* 获取订单类型格式化(toString)
	**/
	public String getCsuoType$(){
		String strValue="";
		 if($.equals($.str(this.getCsuoType()),"0"))
			strValue=$.str("代理订单");		 
		 if($.equals($.str(this.getCsuoType()),"1"))
			strValue=$.str("公务用车");		 
		 if($.equals($.str(this.getCsuoType()),"2"))
			strValue=$.str("紧急公务");		 
	 	 return strValue;
	}
	/**
	* 订单类型 [非空]   0:代理订单 1:公务用车 2:紧急公务    
	**/
	public void setCsuoType(Short csuoType){
		this.csuoType = csuoType;
		this.setSeted(F.csuoType);
	}
	/*******************************用车人员**********************************/	
	/**
	* 用车人员  [CsUnitPerson]     
	**/
	public Long getCsuoPerson(){
		return this.csuoPerson;
	}
	/**
	* 获取用车人员格式化(toString)
	**/
	public String getCsuoPerson$(){
		String strValue="";
		if(this.getCsuoPerson()!=null){
				strValue+=$.str(CsUnitPerson.getKeyValue(this.getCsuoPerson()));
		}			
	 	 return strValue;
	}
	/**
	* 用车人员  [CsUnitPerson]     
	**/
	public void setCsuoPerson(Long csuoPerson){
		this.csuoPerson = csuoPerson;
		this.setSeted(F.csuoPerson);
	}
	/**
	* 获取关联对象[用车人员]
	**/	 			
 	public CsUnitPerson get$csuoPerson(){
 		com.ccclubs.model.CsUnitPerson csUnitPerson = (com.ccclubs.model.CsUnitPerson) $.GetRequest("CsUnitPerson$"+this.getCsuoPerson());
 		if(csUnitPerson!=null)
			return csUnitPerson;
		if(this.getCsuoPerson()!=null){
 			csUnitPerson = CsUnitPerson.get(this.getCsuoPerson());
 		}
 		$.SetRequest("CsUnitPerson$"+this.getCsuoPerson(), csUnitPerson);
	 	return csUnitPerson;
	}
	/*******************************使用人姓名**********************************/	
	/**
	* 使用人姓名       
	**/
	public String getCsuoUsername(){
		return this.csuoUsername;
	}
	/**
	* 获取使用人姓名格式化(toString)
	**/
	public String getCsuoUsername$(){
		String strValue="";
		 strValue=$.str(this.getCsuoUsername());
	 	 return strValue;
	}
	/**
	* 使用人姓名       
	**/
	public void setCsuoUsername(String csuoUsername){
		this.csuoUsername = csuoUsername;
		this.setSeted(F.csuoUsername);
	}
	/*******************************别名或标记**********************************/	
	/**
	* 别名或标记       
	**/
	public String getCsuoAlias(){
		return this.csuoAlias;
	}
	/**
	* 获取别名或标记格式化(toString)
	**/
	public String getCsuoAlias$(){
		String strValue="";
		 strValue=$.str(this.getCsuoAlias());
	 	 return strValue;
	}
	/**
	* 别名或标记       
	**/
	public void setCsuoAlias(String csuoAlias){
		this.csuoAlias = csuoAlias;
		this.setSeted(F.csuoAlias);
	}
	/*******************************使用人手机**********************************/	
	/**
	* 使用人手机       
	**/
	public String getCsuoMobile(){
		return this.csuoMobile;
	}
	/**
	* 获取使用人手机格式化(toString)
	**/
	public String getCsuoMobile$(){
		String strValue="";
		 strValue=$.str(this.getCsuoMobile());
	 	 return strValue;
	}
	/**
	* 使用人手机       
	**/
	public void setCsuoMobile(String csuoMobile){
		this.csuoMobile = csuoMobile;
		this.setSeted(F.csuoMobile);
	}
	/*******************************订单摘要**********************************/	
	/**
	* 订单摘要       
	**/
	public String getCsuoProfile(){
		return this.csuoProfile;
	}
	/**
	* 获取订单摘要格式化(toString)
	**/
	public String getCsuoProfile$(){
		String strValue="";
		 strValue=$.str(this.getCsuoProfile());
	 	 return strValue;
	}
	/**
	* 订单摘要       
	**/
	public void setCsuoProfile(String csuoProfile){
		this.csuoProfile = csuoProfile;
		this.setSeted(F.csuoProfile);
	}
	/*******************************身份证号**********************************/	
	/**
	* 身份证号       
	**/
	public String getCsuoIdcard(){
		return this.csuoIdcard;
	}
	/**
	* 获取身份证号格式化(toString)
	**/
	public String getCsuoIdcard$(){
		String strValue="";
		 strValue=$.str(this.getCsuoIdcard());
	 	 return strValue;
	}
	/**
	* 身份证号       
	**/
	public void setCsuoIdcard(String csuoIdcard){
		this.csuoIdcard = csuoIdcard;
		this.setSeted(F.csuoIdcard);
	}
	/*******************************订单开始时间**********************************/	
	/**
	* 订单开始时间 [非空]      
	**/
	public Date getCsuoStartTime(){
		return this.csuoStartTime;
	}
	/**
	* 获取订单开始时间格式化(toString)
	**/
	public String getCsuoStartTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsuoStartTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 订单开始时间 [非空]      
	**/
	public void setCsuoStartTime(Date csuoStartTime){
		this.csuoStartTime = csuoStartTime;
		this.setSeted(F.csuoStartTime);
	}
	/*******************************订单结束时间**********************************/	
	/**
	* 订单结束时间 [非空]      
	**/
	public Date getCsuoFinishTime(){
		return this.csuoFinishTime;
	}
	/**
	* 获取订单结束时间格式化(toString)
	**/
	public String getCsuoFinishTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsuoFinishTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 订单结束时间 [非空]      
	**/
	public void setCsuoFinishTime(Date csuoFinishTime){
		this.csuoFinishTime = csuoFinishTime;
		this.setSeted(F.csuoFinishTime);
	}
	/*******************************实际还车时间**********************************/	
	/**
	* 实际还车时间       
	**/
	public Date getCsuoRetTime(){
		return this.csuoRetTime;
	}
	/**
	* 获取实际还车时间格式化(toString)
	**/
	public String getCsuoRetTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsuoRetTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 实际还车时间       
	**/
	public void setCsuoRetTime(Date csuoRetTime){
		this.csuoRetTime = csuoRetTime;
		this.setSeted(F.csuoRetTime);
	}
	/*******************************免费小时(代理)**********************************/	
	/**
	* 免费小时(代理)       
	**/
	public Integer getCsuoFreeHour(){
		return this.csuoFreeHour;
	}
	/**
	* 获取免费小时(代理)格式化(toString)
	**/
	public String getCsuoFreeHour$(){
		String strValue="";
		 strValue=$.str(this.getCsuoFreeHour());
	 	 return strValue;
	}
	/**
	* 免费小时(代理)       
	**/
	public void setCsuoFreeHour(Integer csuoFreeHour){
		this.csuoFreeHour = csuoFreeHour;
		this.setSeted(F.csuoFreeHour);
	}
	/*******************************免费里程(代理)**********************************/	
	/**
	* 免费里程(代理)       
	**/
	public Double getCsuoFreeKm(){
		return this.csuoFreeKm;
	}
	/**
	* 获取免费里程(代理)格式化(toString)
	**/
	public String getCsuoFreeKm$(){
		String strValue="";
		 strValue=$.str(this.getCsuoFreeKm());	
	 	 return strValue;
	}
	/**
	* 免费里程(代理)       
	**/
	public void setCsuoFreeKm(Double csuoFreeKm){
		this.csuoFreeKm = csuoFreeKm;
		this.setSeted(F.csuoFreeKm);
	}
	/*******************************天租价格(代理)**********************************/	
	/**
	* 天租价格(代理)       
	**/
	public Double getCsuoDayPrice(){
		return this.csuoDayPrice;
	}
	/**
	* 获取天租价格(代理)格式化(toString)
	**/
	public String getCsuoDayPrice$(){
		String strValue="";
		 strValue=$.str(this.getCsuoDayPrice());	
	 	 return strValue;
	}
	/**
	* 天租价格(代理)       
	**/
	public void setCsuoDayPrice(Double csuoDayPrice){
		this.csuoDayPrice = csuoDayPrice;
		this.setSeted(F.csuoDayPrice);
	}
	/*******************************小时价格(代理)**********************************/	
	/**
	* 小时价格(代理)       
	**/
	public Double getCsuoHourPrice(){
		return this.csuoHourPrice;
	}
	/**
	* 获取小时价格(代理)格式化(toString)
	**/
	public String getCsuoHourPrice$(){
		String strValue="";
		 strValue=$.str(this.getCsuoHourPrice());	
	 	 return strValue;
	}
	/**
	* 小时价格(代理)       
	**/
	public void setCsuoHourPrice(Double csuoHourPrice){
		this.csuoHourPrice = csuoHourPrice;
		this.setSeted(F.csuoHourPrice);
	}
	/*******************************里程价格(代理)**********************************/	
	/**
	* 里程价格(代理)       
	**/
	public Double getCsuoKmPrice(){
		return this.csuoKmPrice;
	}
	/**
	* 获取里程价格(代理)格式化(toString)
	**/
	public String getCsuoKmPrice$(){
		String strValue="";
		 strValue=$.str(this.getCsuoKmPrice());	
	 	 return strValue;
	}
	/**
	* 里程价格(代理)       
	**/
	public void setCsuoKmPrice(Double csuoKmPrice){
		this.csuoKmPrice = csuoKmPrice;
		this.setSeted(F.csuoKmPrice);
	}
	/*******************************应付金额(代理)**********************************/	
	/**
	* 应付金额(代理)       
	**/
	public Double getCsuoPayNeed(){
		return this.csuoPayNeed;
	}
	/**
	* 获取应付金额(代理)格式化(toString)
	**/
	public String getCsuoPayNeed$(){
		String strValue="";
		 strValue=$.str(this.getCsuoPayNeed());	
	 	 return strValue;
	}
	/**
	* 应付金额(代理)       
	**/
	public void setCsuoPayNeed(Double csuoPayNeed){
		this.csuoPayNeed = csuoPayNeed;
		this.setSeted(F.csuoPayNeed);
	}
	/*******************************实付金额(代理)**********************************/	
	/**
	* 实付金额(代理)       
	**/
	public Double getCsuoPayReal(){
		return this.csuoPayReal;
	}
	/**
	* 获取实付金额(代理)格式化(toString)
	**/
	public String getCsuoPayReal$(){
		String strValue="";
		 strValue=$.str(this.getCsuoPayReal());	
	 	 return strValue;
	}
	/**
	* 实付金额(代理)       
	**/
	public void setCsuoPayReal(Double csuoPayReal){
		this.csuoPayReal = csuoPayReal;
		this.setSeted(F.csuoPayReal);
	}
	/*******************************订单备注**********************************/	
	/**
	* 订单备注       
	**/
	public String getCsuoRemark(){
		return this.csuoRemark;
	}
	/**
	* 获取订单备注格式化(toString)
	**/
	public String getCsuoRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsuoRemark());
	 	 return strValue;
	}
	/**
	* 订单备注       
	**/
	public void setCsuoRemark(String csuoRemark){
		this.csuoRemark = csuoRemark;
		this.setSeted(F.csuoRemark);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间       
	**/
	public Date getCsuoAddTime(){
		return this.csuoAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsuoAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsuoAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间       
	**/
	public void setCsuoAddTime(Date csuoAddTime){
		this.csuoAddTime = csuoAddTime;
		this.setSeted(F.csuoAddTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间       
	**/
	public Date getCsuoUpdateTime(){
		return this.csuoUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsuoUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsuoUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间       
	**/
	public void setCsuoUpdateTime(Date csuoUpdateTime){
		this.csuoUpdateTime = csuoUpdateTime;
		this.setSeted(F.csuoUpdateTime);
	}
	/*******************************里程数**********************************/	
	/**
	* 里程数       
	**/
	public Double getCsuoMileage(){
		return this.csuoMileage;
	}
	/**
	* 获取里程数格式化(toString)
	**/
	public String getCsuoMileage$(){
		String strValue="";
		 strValue=$.str(this.getCsuoMileage());	
	 	 return strValue;
	}
	/**
	* 里程数       
	**/
	public void setCsuoMileage(Double csuoMileage){
		this.csuoMileage = csuoMileage;
		this.setSeted(F.csuoMileage);
	}
	/*******************************结算费用**********************************/	
	/**
	* 结算费用       
	**/
	public Double getCsuoSettlement(){
		return this.csuoSettlement;
	}
	/**
	* 获取结算费用格式化(toString)
	**/
	public String getCsuoSettlement$(){
		String strValue="";
		 strValue=$.str(this.getCsuoSettlement());	
	 	 return strValue;
	}
	/**
	* 结算费用       
	**/
	public void setCsuoSettlement(Double csuoSettlement){
		this.csuoSettlement = csuoSettlement;
		this.setSeted(F.csuoSettlement);
	}
	/*******************************结算状态**********************************/	
	/**
	* 结算状态    0:未结算 1:已结算    
	**/
	public Short getCsuoSettleState(){
		return this.csuoSettleState;
	}
	/**
	* 获取结算状态格式化(toString)
	**/
	public String getCsuoSettleState$(){
		String strValue="";
		 if($.equals($.str(this.getCsuoSettleState()),"0"))
			strValue=$.str("未结算");		 
		 if($.equals($.str(this.getCsuoSettleState()),"1"))
			strValue=$.str("已结算");		 
	 	 return strValue;
	}
	/**
	* 结算状态    0:未结算 1:已结算    
	**/
	public void setCsuoSettleState(Short csuoSettleState){
		this.csuoSettleState = csuoSettleState;
		this.setSeted(F.csuoSettleState);
	}
	/*******************************结算时间**********************************/	
	/**
	* 结算时间       
	**/
	public Date getCsuoSettleTime(){
		return this.csuoSettleTime;
	}
	/**
	* 获取结算时间格式化(toString)
	**/
	public String getCsuoSettleTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsuoSettleTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 结算时间       
	**/
	public void setCsuoSettleTime(Date csuoSettleTime){
		this.csuoSettleTime = csuoSettleTime;
		this.setSeted(F.csuoSettleTime);
	}
	/*******************************结账单**********************************/	
	/**
	* 结账单  [CsUnitBill]     
	**/
	public Long getCsuoStatement(){
		return this.csuoStatement;
	}
	/**
	* 获取结账单格式化(toString)
	**/
	public String getCsuoStatement$(){
		String strValue="";
		if(this.getCsuoStatement()!=null){
				strValue+=$.str(CsUnitBill.getKeyValue(this.getCsuoStatement()));
		}			
	 	 return strValue;
	}
	/**
	* 结账单  [CsUnitBill]     
	**/
	public void setCsuoStatement(Long csuoStatement){
		this.csuoStatement = csuoStatement;
		this.setSeted(F.csuoStatement);
	}
	/**
	* 获取关联对象[企业对账]
	**/	 			
 	public CsUnitBill get$csuoStatement(){
 		com.ccclubs.model.CsUnitBill csUnitBill = (com.ccclubs.model.CsUnitBill) $.GetRequest("CsUnitBill$"+this.getCsuoStatement());
 		if(csUnitBill!=null)
			return csUnitBill;
		if(this.getCsuoStatement()!=null){
 			csUnitBill = CsUnitBill.get(this.getCsuoStatement());
 		}
 		$.SetRequest("CsUnitBill$"+this.getCsuoStatement(), csUnitBill);
	 	return csUnitBill;
	}
	/*******************************订单来源**********************************/	
	/**
	* 订单来源       
	**/
	public String getCsuoFrom(){
		return this.csuoFrom;
	}
	/**
	* 获取订单来源格式化(toString)
	**/
	public String getCsuoFrom$(){
		String strValue="";
		 strValue=$.str(this.getCsuoFrom());
	 	 return strValue;
	}
	/**
	* 订单来源       
	**/
	public void setCsuoFrom(String csuoFrom){
		this.csuoFrom = csuoFrom;
		this.setSeted(F.csuoFrom);
	}
	/*******************************审核时间**********************************/	
	/**
	* 审核时间       
	**/
	public Date getCsuoCheckTime(){
		return this.csuoCheckTime;
	}
	/**
	* 获取审核时间格式化(toString)
	**/
	public String getCsuoCheckTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsuoCheckTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 审核时间       
	**/
	public void setCsuoCheckTime(Date csuoCheckTime){
		this.csuoCheckTime = csuoCheckTime;
		this.setSeted(F.csuoCheckTime);
	}
	/*******************************审核人**********************************/	
	/**
	* 审核人       SrvUser,CsUnitUser,CsUnitPerson
	**/
	public String getCsuoChecker(){
		return this.csuoChecker;
	}
	/**
	* 获取审核人格式化(toString)
	**/
	public String getCsuoChecker$(){
		String strValue="";
	 	 Object object = this.get$csuoChecker();
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
	* 审核人       SrvUser,CsUnitUser,CsUnitPerson
	**/
	public void setCsuoChecker(String csuoChecker){
		this.csuoChecker = csuoChecker;
		this.setSeted(F.csuoChecker);
	}
	/**
	* 泛关联对象，对象类名由csuoChecker字段中标明
	**/	 			
 	public <T> T get$csuoChecker(){
		if($.empty(this.csuoChecker))
 			return null;
 		try{
	 		String ClassName = this.csuoChecker.split("@")[0];
	 		Long id = Long.parseLong(this.csuoChecker.split("@")[1]);
	 		Class<?> cls = Class.forName("com.ccclubs.model."+ClassName);
	 		return (T)cls.getMethod("get", Long.class).invoke(null, id);	
 		}catch(Exception ex){
 			ex.printStackTrace();
 			return null;
 		}
	}
	/*******************************状态**********************************/	
	/**
	* 状态    1:等待审核 2:审核通过 3:审核未过 4:订单结束    
	**/
	public Short getCsuoState(){
		return this.csuoState;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsuoState$(){
		String strValue="";
		 if($.equals($.str(this.getCsuoState()),"1"))
			strValue=$.str("等待审核");		 
		 if($.equals($.str(this.getCsuoState()),"2"))
			strValue=$.str("审核通过");		 
		 if($.equals($.str(this.getCsuoState()),"3"))
			strValue=$.str("审核未过");		 
		 if($.equals($.str(this.getCsuoState()),"4"))
			strValue=$.str("订单结束");		 
	 	 return strValue;
	}
	/**
	* 状态    1:等待审核 2:审核通过 3:审核未过 4:订单结束    
	**/
	public void setCsuoState(Short csuoState){
		this.csuoState = csuoState;
		this.setSeted(F.csuoState);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsUnitOrder.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUnitOrder.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUnitOrder.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsUnitOrder.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsUnitOrder.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUnitOrder.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUnitOrder.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsUnitOrder.this);
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
		/** 编号 [非空]       **/
		public M csuoId(Object csuoId){this.put("csuoId", csuoId);return this;};
	 	/** and csuo_id is null */
 		public M csuoIdNull(){if(this.get("csuoIdNot")==null)this.put("csuoIdNot", "");this.put("csuoId", null);return this;};
 		/** not .... */
 		public M csuoIdNot(){this.put("csuoIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csuoHost(Object csuoHost){this.put("csuoHost", csuoHost);return this;};
	 	/** and csuo_host is null */
 		public M csuoHostNull(){if(this.get("csuoHostNot")==null)this.put("csuoHostNot", "");this.put("csuoHost", null);return this;};
 		/** not .... */
 		public M csuoHostNot(){this.put("csuoHostNot", "not");return this;};
		/** 系统订单 [非空] [CsOrder]      **/
		public M csuoOrder(Object csuoOrder){this.put("csuoOrder", csuoOrder);return this;};
	 	/** and csuo_order is null */
 		public M csuoOrderNull(){if(this.get("csuoOrderNot")==null)this.put("csuoOrderNot", "");this.put("csuoOrder", null);return this;};
 		/** not .... */
 		public M csuoOrderNot(){this.put("csuoOrderNot", "not");return this;};
		public M csuoOrder$on(CsOrder.M value){
			this.put("CsOrder", value);
			this.put("csuoOrder$on", value);
			return this;
		};	
		/** 专车订单 [非空] [CsSpecialCar]      **/
		public M csuoSpecial(Object csuoSpecial){this.put("csuoSpecial", csuoSpecial);return this;};
	 	/** and csuo_special is null */
 		public M csuoSpecialNull(){if(this.get("csuoSpecialNot")==null)this.put("csuoSpecialNot", "");this.put("csuoSpecial", null);return this;};
 		/** not .... */
 		public M csuoSpecialNot(){this.put("csuoSpecialNot", "not");return this;};
		public M csuoSpecial$on(CsSpecialCar.M value){
			this.put("CsSpecialCar", value);
			this.put("csuoSpecial$on", value);
			return this;
		};	
		/** 所属单位 [非空] [CsUnitInfo]      **/
		public M csuoUnitInfo(Object csuoUnitInfo){this.put("csuoUnitInfo", csuoUnitInfo);return this;};
	 	/** and csuo_unit_info is null */
 		public M csuoUnitInfoNull(){if(this.get("csuoUnitInfoNot")==null)this.put("csuoUnitInfoNot", "");this.put("csuoUnitInfo", null);return this;};
 		/** not .... */
 		public M csuoUnitInfoNot(){this.put("csuoUnitInfoNot", "not");return this;};
		public M csuoUnitInfo$on(CsUnitInfo.M value){
			this.put("CsUnitInfo", value);
			this.put("csuoUnitInfo$on", value);
			return this;
		};	
		/** 所属分组  [CsUnitGroup]      **/
		public M csuoUnitGroup(Object csuoUnitGroup){this.put("csuoUnitGroup", csuoUnitGroup);return this;};
	 	/** and csuo_unit_group is null */
 		public M csuoUnitGroupNull(){if(this.get("csuoUnitGroupNot")==null)this.put("csuoUnitGroupNot", "");this.put("csuoUnitGroup", null);return this;};
 		/** not .... */
 		public M csuoUnitGroupNot(){this.put("csuoUnitGroupNot", "not");return this;};
		public M csuoUnitGroup$on(CsUnitGroup.M value){
			this.put("CsUnitGroup", value);
			this.put("csuoUnitGroup$on", value);
			return this;
		};	
		/** 支付帐号 [非空] [CsMember]      **/
		public M csuoUnitMember(Object csuoUnitMember){this.put("csuoUnitMember", csuoUnitMember);return this;};
	 	/** and csuo_unit_member is null */
 		public M csuoUnitMemberNull(){if(this.get("csuoUnitMemberNot")==null)this.put("csuoUnitMemberNot", "");this.put("csuoUnitMember", null);return this;};
 		/** not .... */
 		public M csuoUnitMemberNot(){this.put("csuoUnitMemberNot", "not");return this;};
		public M csuoUnitMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csuoUnitMember$on", value);
			return this;
		};	
		/** 使用帐号 [非空] [CsMember]  {csuoUnitInfo}:unit    ${basePath}${proname}/unit/info_room.do **/
		public M csuoUnitChild(Object csuoUnitChild){this.put("csuoUnitChild", csuoUnitChild);return this;};
	 	/** and csuo_unit_child is null */
 		public M csuoUnitChildNull(){if(this.get("csuoUnitChildNot")==null)this.put("csuoUnitChildNot", "");this.put("csuoUnitChild", null);return this;};
 		/** not .... */
 		public M csuoUnitChildNot(){this.put("csuoUnitChildNot", "not");return this;};
		public M csuoUnitChild$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csuoUnitChild$on", value);
			return this;
		};	
		/** 预订车辆  [CsCar]      **/
		public M csuoCar(Object csuoCar){this.put("csuoCar", csuoCar);return this;};
	 	/** and csuo_car is null */
 		public M csuoCarNull(){if(this.get("csuoCarNot")==null)this.put("csuoCarNot", "");this.put("csuoCar", null);return this;};
 		/** not .... */
 		public M csuoCarNot(){this.put("csuoCarNot", "not");return this;};
		public M csuoCar$on(CsCar.M value){
			this.put("CsCar", value);
			this.put("csuoCar$on", value);
			return this;
		};	
		/** 车牌号码        **/
		public M csuoNumber(Object csuoNumber){this.put("csuoNumber", csuoNumber);return this;};
	 	/** and csuo_number is null */
 		public M csuoNumberNull(){if(this.get("csuoNumberNot")==null)this.put("csuoNumberNot", "");this.put("csuoNumber", null);return this;};
 		/** not .... */
 		public M csuoNumberNot(){this.put("csuoNumberNot", "not");return this;};
		/** 订单类型 [非空]   0:代理订单 1:公务用车 2:紧急公务     **/
		public M csuoType(Object csuoType){this.put("csuoType", csuoType);return this;};
	 	/** and csuo_type is null */
 		public M csuoTypeNull(){if(this.get("csuoTypeNot")==null)this.put("csuoTypeNot", "");this.put("csuoType", null);return this;};
 		/** not .... */
 		public M csuoTypeNot(){this.put("csuoTypeNot", "not");return this;};
		/** 用车人员  [CsUnitPerson]      **/
		public M csuoPerson(Object csuoPerson){this.put("csuoPerson", csuoPerson);return this;};
	 	/** and csuo_person is null */
 		public M csuoPersonNull(){if(this.get("csuoPersonNot")==null)this.put("csuoPersonNot", "");this.put("csuoPerson", null);return this;};
 		/** not .... */
 		public M csuoPersonNot(){this.put("csuoPersonNot", "not");return this;};
		public M csuoPerson$on(CsUnitPerson.M value){
			this.put("CsUnitPerson", value);
			this.put("csuoPerson$on", value);
			return this;
		};	
		/** 使用人姓名        **/
		public M csuoUsername(Object csuoUsername){this.put("csuoUsername", csuoUsername);return this;};
	 	/** and csuo_username is null */
 		public M csuoUsernameNull(){if(this.get("csuoUsernameNot")==null)this.put("csuoUsernameNot", "");this.put("csuoUsername", null);return this;};
 		/** not .... */
 		public M csuoUsernameNot(){this.put("csuoUsernameNot", "not");return this;};
		/** 别名或标记        **/
		public M csuoAlias(Object csuoAlias){this.put("csuoAlias", csuoAlias);return this;};
	 	/** and csuo_alias is null */
 		public M csuoAliasNull(){if(this.get("csuoAliasNot")==null)this.put("csuoAliasNot", "");this.put("csuoAlias", null);return this;};
 		/** not .... */
 		public M csuoAliasNot(){this.put("csuoAliasNot", "not");return this;};
		/** 使用人手机        **/
		public M csuoMobile(Object csuoMobile){this.put("csuoMobile", csuoMobile);return this;};
	 	/** and csuo_mobile is null */
 		public M csuoMobileNull(){if(this.get("csuoMobileNot")==null)this.put("csuoMobileNot", "");this.put("csuoMobile", null);return this;};
 		/** not .... */
 		public M csuoMobileNot(){this.put("csuoMobileNot", "not");return this;};
		/** 订单摘要        **/
		public M csuoProfile(Object csuoProfile){this.put("csuoProfile", csuoProfile);return this;};
	 	/** and csuo_profile is null */
 		public M csuoProfileNull(){if(this.get("csuoProfileNot")==null)this.put("csuoProfileNot", "");this.put("csuoProfile", null);return this;};
 		/** not .... */
 		public M csuoProfileNot(){this.put("csuoProfileNot", "not");return this;};
		/** 身份证号        **/
		public M csuoIdcard(Object csuoIdcard){this.put("csuoIdcard", csuoIdcard);return this;};
	 	/** and csuo_idcard is null */
 		public M csuoIdcardNull(){if(this.get("csuoIdcardNot")==null)this.put("csuoIdcardNot", "");this.put("csuoIdcard", null);return this;};
 		/** not .... */
 		public M csuoIdcardNot(){this.put("csuoIdcardNot", "not");return this;};
		/** 订单开始时间 [非空]       **/
		public M csuoStartTime(Object csuoStartTime){this.put("csuoStartTime", csuoStartTime);return this;};
	 	/** and csuo_start_time is null */
 		public M csuoStartTimeNull(){if(this.get("csuoStartTimeNot")==null)this.put("csuoStartTimeNot", "");this.put("csuoStartTime", null);return this;};
 		/** not .... */
 		public M csuoStartTimeNot(){this.put("csuoStartTimeNot", "not");return this;};
 		/** and csuo_start_time >= ? */
 		public M csuoStartTimeStart(Object start){this.put("csuoStartTimeStart", start);return this;};			
 		/** and csuo_start_time <= ? */
 		public M csuoStartTimeEnd(Object end){this.put("csuoStartTimeEnd", end);return this;};
		/** 订单结束时间 [非空]       **/
		public M csuoFinishTime(Object csuoFinishTime){this.put("csuoFinishTime", csuoFinishTime);return this;};
	 	/** and csuo_finish_time is null */
 		public M csuoFinishTimeNull(){if(this.get("csuoFinishTimeNot")==null)this.put("csuoFinishTimeNot", "");this.put("csuoFinishTime", null);return this;};
 		/** not .... */
 		public M csuoFinishTimeNot(){this.put("csuoFinishTimeNot", "not");return this;};
 		/** and csuo_finish_time >= ? */
 		public M csuoFinishTimeStart(Object start){this.put("csuoFinishTimeStart", start);return this;};			
 		/** and csuo_finish_time <= ? */
 		public M csuoFinishTimeEnd(Object end){this.put("csuoFinishTimeEnd", end);return this;};
		/** 实际还车时间        **/
		public M csuoRetTime(Object csuoRetTime){this.put("csuoRetTime", csuoRetTime);return this;};
	 	/** and csuo_ret_time is null */
 		public M csuoRetTimeNull(){if(this.get("csuoRetTimeNot")==null)this.put("csuoRetTimeNot", "");this.put("csuoRetTime", null);return this;};
 		/** not .... */
 		public M csuoRetTimeNot(){this.put("csuoRetTimeNot", "not");return this;};
 		/** and csuo_ret_time >= ? */
 		public M csuoRetTimeStart(Object start){this.put("csuoRetTimeStart", start);return this;};			
 		/** and csuo_ret_time <= ? */
 		public M csuoRetTimeEnd(Object end){this.put("csuoRetTimeEnd", end);return this;};
		/** 免费小时(代理)        **/
		public M csuoFreeHour(Object csuoFreeHour){this.put("csuoFreeHour", csuoFreeHour);return this;};
	 	/** and csuo_free_hour is null */
 		public M csuoFreeHourNull(){if(this.get("csuoFreeHourNot")==null)this.put("csuoFreeHourNot", "");this.put("csuoFreeHour", null);return this;};
 		/** not .... */
 		public M csuoFreeHourNot(){this.put("csuoFreeHourNot", "not");return this;};
		/** and csuo_free_hour >= ? */
		public M csuoFreeHourMin(Object min){this.put("csuoFreeHourMin", min);return this;};
		/** and csuo_free_hour <= ? */
		public M csuoFreeHourMax(Object max){this.put("csuoFreeHourMax", max);return this;};
		/** 免费里程(代理)        **/
		public M csuoFreeKm(Object csuoFreeKm){this.put("csuoFreeKm", csuoFreeKm);return this;};
	 	/** and csuo_free_km is null */
 		public M csuoFreeKmNull(){if(this.get("csuoFreeKmNot")==null)this.put("csuoFreeKmNot", "");this.put("csuoFreeKm", null);return this;};
 		/** not .... */
 		public M csuoFreeKmNot(){this.put("csuoFreeKmNot", "not");return this;};
		/** and csuo_free_km >= ? */
		public M csuoFreeKmMin(Object min){this.put("csuoFreeKmMin", min);return this;};
		/** and csuo_free_km <= ? */
		public M csuoFreeKmMax(Object max){this.put("csuoFreeKmMax", max);return this;};
		/** 天租价格(代理)        **/
		public M csuoDayPrice(Object csuoDayPrice){this.put("csuoDayPrice", csuoDayPrice);return this;};
	 	/** and csuo_day_price is null */
 		public M csuoDayPriceNull(){if(this.get("csuoDayPriceNot")==null)this.put("csuoDayPriceNot", "");this.put("csuoDayPrice", null);return this;};
 		/** not .... */
 		public M csuoDayPriceNot(){this.put("csuoDayPriceNot", "not");return this;};
		/** and csuo_day_price >= ? */
		public M csuoDayPriceMin(Object min){this.put("csuoDayPriceMin", min);return this;};
		/** and csuo_day_price <= ? */
		public M csuoDayPriceMax(Object max){this.put("csuoDayPriceMax", max);return this;};
		/** 小时价格(代理)        **/
		public M csuoHourPrice(Object csuoHourPrice){this.put("csuoHourPrice", csuoHourPrice);return this;};
	 	/** and csuo_hour_price is null */
 		public M csuoHourPriceNull(){if(this.get("csuoHourPriceNot")==null)this.put("csuoHourPriceNot", "");this.put("csuoHourPrice", null);return this;};
 		/** not .... */
 		public M csuoHourPriceNot(){this.put("csuoHourPriceNot", "not");return this;};
		/** and csuo_hour_price >= ? */
		public M csuoHourPriceMin(Object min){this.put("csuoHourPriceMin", min);return this;};
		/** and csuo_hour_price <= ? */
		public M csuoHourPriceMax(Object max){this.put("csuoHourPriceMax", max);return this;};
		/** 里程价格(代理)        **/
		public M csuoKmPrice(Object csuoKmPrice){this.put("csuoKmPrice", csuoKmPrice);return this;};
	 	/** and csuo_km_price is null */
 		public M csuoKmPriceNull(){if(this.get("csuoKmPriceNot")==null)this.put("csuoKmPriceNot", "");this.put("csuoKmPrice", null);return this;};
 		/** not .... */
 		public M csuoKmPriceNot(){this.put("csuoKmPriceNot", "not");return this;};
		/** and csuo_km_price >= ? */
		public M csuoKmPriceMin(Object min){this.put("csuoKmPriceMin", min);return this;};
		/** and csuo_km_price <= ? */
		public M csuoKmPriceMax(Object max){this.put("csuoKmPriceMax", max);return this;};
		/** 应付金额(代理)        **/
		public M csuoPayNeed(Object csuoPayNeed){this.put("csuoPayNeed", csuoPayNeed);return this;};
	 	/** and csuo_pay_need is null */
 		public M csuoPayNeedNull(){if(this.get("csuoPayNeedNot")==null)this.put("csuoPayNeedNot", "");this.put("csuoPayNeed", null);return this;};
 		/** not .... */
 		public M csuoPayNeedNot(){this.put("csuoPayNeedNot", "not");return this;};
		/** and csuo_pay_need >= ? */
		public M csuoPayNeedMin(Object min){this.put("csuoPayNeedMin", min);return this;};
		/** and csuo_pay_need <= ? */
		public M csuoPayNeedMax(Object max){this.put("csuoPayNeedMax", max);return this;};
		/** 实付金额(代理)        **/
		public M csuoPayReal(Object csuoPayReal){this.put("csuoPayReal", csuoPayReal);return this;};
	 	/** and csuo_pay_real is null */
 		public M csuoPayRealNull(){if(this.get("csuoPayRealNot")==null)this.put("csuoPayRealNot", "");this.put("csuoPayReal", null);return this;};
 		/** not .... */
 		public M csuoPayRealNot(){this.put("csuoPayRealNot", "not");return this;};
		/** and csuo_pay_real >= ? */
		public M csuoPayRealMin(Object min){this.put("csuoPayRealMin", min);return this;};
		/** and csuo_pay_real <= ? */
		public M csuoPayRealMax(Object max){this.put("csuoPayRealMax", max);return this;};
		/** 订单备注        **/
		public M csuoRemark(Object csuoRemark){this.put("csuoRemark", csuoRemark);return this;};
	 	/** and csuo_remark is null */
 		public M csuoRemarkNull(){if(this.get("csuoRemarkNot")==null)this.put("csuoRemarkNot", "");this.put("csuoRemark", null);return this;};
 		/** not .... */
 		public M csuoRemarkNot(){this.put("csuoRemarkNot", "not");return this;};
		/** 添加时间        **/
		public M csuoAddTime(Object csuoAddTime){this.put("csuoAddTime", csuoAddTime);return this;};
	 	/** and csuo_add_time is null */
 		public M csuoAddTimeNull(){if(this.get("csuoAddTimeNot")==null)this.put("csuoAddTimeNot", "");this.put("csuoAddTime", null);return this;};
 		/** not .... */
 		public M csuoAddTimeNot(){this.put("csuoAddTimeNot", "not");return this;};
 		/** and csuo_add_time >= ? */
 		public M csuoAddTimeStart(Object start){this.put("csuoAddTimeStart", start);return this;};			
 		/** and csuo_add_time <= ? */
 		public M csuoAddTimeEnd(Object end){this.put("csuoAddTimeEnd", end);return this;};
		/** 修改时间        **/
		public M csuoUpdateTime(Object csuoUpdateTime){this.put("csuoUpdateTime", csuoUpdateTime);return this;};
	 	/** and csuo_update_time is null */
 		public M csuoUpdateTimeNull(){if(this.get("csuoUpdateTimeNot")==null)this.put("csuoUpdateTimeNot", "");this.put("csuoUpdateTime", null);return this;};
 		/** not .... */
 		public M csuoUpdateTimeNot(){this.put("csuoUpdateTimeNot", "not");return this;};
 		/** and csuo_update_time >= ? */
 		public M csuoUpdateTimeStart(Object start){this.put("csuoUpdateTimeStart", start);return this;};			
 		/** and csuo_update_time <= ? */
 		public M csuoUpdateTimeEnd(Object end){this.put("csuoUpdateTimeEnd", end);return this;};
		/** 里程数        **/
		public M csuoMileage(Object csuoMileage){this.put("csuoMileage", csuoMileage);return this;};
	 	/** and csuo_mileage is null */
 		public M csuoMileageNull(){if(this.get("csuoMileageNot")==null)this.put("csuoMileageNot", "");this.put("csuoMileage", null);return this;};
 		/** not .... */
 		public M csuoMileageNot(){this.put("csuoMileageNot", "not");return this;};
		/** and csuo_mileage >= ? */
		public M csuoMileageMin(Object min){this.put("csuoMileageMin", min);return this;};
		/** and csuo_mileage <= ? */
		public M csuoMileageMax(Object max){this.put("csuoMileageMax", max);return this;};
		/** 结算费用        **/
		public M csuoSettlement(Object csuoSettlement){this.put("csuoSettlement", csuoSettlement);return this;};
	 	/** and csuo_settlement is null */
 		public M csuoSettlementNull(){if(this.get("csuoSettlementNot")==null)this.put("csuoSettlementNot", "");this.put("csuoSettlement", null);return this;};
 		/** not .... */
 		public M csuoSettlementNot(){this.put("csuoSettlementNot", "not");return this;};
		/** and csuo_settlement >= ? */
		public M csuoSettlementMin(Object min){this.put("csuoSettlementMin", min);return this;};
		/** and csuo_settlement <= ? */
		public M csuoSettlementMax(Object max){this.put("csuoSettlementMax", max);return this;};
		/** 结算状态    0:未结算 1:已结算     **/
		public M csuoSettleState(Object csuoSettleState){this.put("csuoSettleState", csuoSettleState);return this;};
	 	/** and csuo_settle_state is null */
 		public M csuoSettleStateNull(){if(this.get("csuoSettleStateNot")==null)this.put("csuoSettleStateNot", "");this.put("csuoSettleState", null);return this;};
 		/** not .... */
 		public M csuoSettleStateNot(){this.put("csuoSettleStateNot", "not");return this;};
		/** 结算时间        **/
		public M csuoSettleTime(Object csuoSettleTime){this.put("csuoSettleTime", csuoSettleTime);return this;};
	 	/** and csuo_settle_time is null */
 		public M csuoSettleTimeNull(){if(this.get("csuoSettleTimeNot")==null)this.put("csuoSettleTimeNot", "");this.put("csuoSettleTime", null);return this;};
 		/** not .... */
 		public M csuoSettleTimeNot(){this.put("csuoSettleTimeNot", "not");return this;};
 		/** and csuo_settle_time >= ? */
 		public M csuoSettleTimeStart(Object start){this.put("csuoSettleTimeStart", start);return this;};			
 		/** and csuo_settle_time <= ? */
 		public M csuoSettleTimeEnd(Object end){this.put("csuoSettleTimeEnd", end);return this;};
		/** 结账单  [CsUnitBill]      **/
		public M csuoStatement(Object csuoStatement){this.put("csuoStatement", csuoStatement);return this;};
	 	/** and csuo_statement is null */
 		public M csuoStatementNull(){if(this.get("csuoStatementNot")==null)this.put("csuoStatementNot", "");this.put("csuoStatement", null);return this;};
 		/** not .... */
 		public M csuoStatementNot(){this.put("csuoStatementNot", "not");return this;};
		public M csuoStatement$on(CsUnitBill.M value){
			this.put("CsUnitBill", value);
			this.put("csuoStatement$on", value);
			return this;
		};	
		/** 订单来源        **/
		public M csuoFrom(Object csuoFrom){this.put("csuoFrom", csuoFrom);return this;};
	 	/** and csuo_from is null */
 		public M csuoFromNull(){if(this.get("csuoFromNot")==null)this.put("csuoFromNot", "");this.put("csuoFrom", null);return this;};
 		/** not .... */
 		public M csuoFromNot(){this.put("csuoFromNot", "not");return this;};
		/** 审核时间        **/
		public M csuoCheckTime(Object csuoCheckTime){this.put("csuoCheckTime", csuoCheckTime);return this;};
	 	/** and csuo_check_time is null */
 		public M csuoCheckTimeNull(){if(this.get("csuoCheckTimeNot")==null)this.put("csuoCheckTimeNot", "");this.put("csuoCheckTime", null);return this;};
 		/** not .... */
 		public M csuoCheckTimeNot(){this.put("csuoCheckTimeNot", "not");return this;};
 		/** and csuo_check_time >= ? */
 		public M csuoCheckTimeStart(Object start){this.put("csuoCheckTimeStart", start);return this;};			
 		/** and csuo_check_time <= ? */
 		public M csuoCheckTimeEnd(Object end){this.put("csuoCheckTimeEnd", end);return this;};
		/** 审核人       SrvUser,CsUnitUser,CsUnitPerson **/
		public M csuoChecker(Object csuoChecker){this.put("csuoChecker", csuoChecker);return this;};
	 	/** and csuo_checker is null */
 		public M csuoCheckerNull(){if(this.get("csuoCheckerNot")==null)this.put("csuoCheckerNot", "");this.put("csuoChecker", null);return this;};
 		/** not .... */
 		public M csuoCheckerNot(){this.put("csuoCheckerNot", "not");return this;};
		/** 状态    1:等待审核 2:审核通过 3:审核未过 4:订单结束     **/
		public M csuoState(Object csuoState){this.put("csuoState", csuoState);return this;};
	 	/** and csuo_state is null */
 		public M csuoStateNull(){if(this.get("csuoStateNot")==null)this.put("csuoStateNot", "");this.put("csuoState", null);return this;};
 		/** not .... */
 		public M csuoStateNot(){this.put("csuoStateNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有企业订单 **/
		public @api List<CsUnitOrder> list(Integer size){
			return getCsUnitOrderList(this,size);
		}
		/** 获取企业订单分页 **/
		public @api Page<CsUnitOrder> page(int page,int size){
			return getCsUnitOrderPage(page, size , this);
		}
		/** 根据查询条件取企业订单 **/
		public @api CsUnitOrder get(){
			return getCsUnitOrder(this);
		}
		/** 获取企业订单数 **/
		public @api Long count(){
			return getCsUnitOrderCount(this);
		}
		/** 获取企业订单表达式 **/
		public @api <T> T eval(String strEval){
			return getCsUnitOrderEval(strEval,this);
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
			updateCsUnitOrder(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csuoId="csuoId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csuoHost="csuoHost";
		/** 系统订单 [非空] [CsOrder]      **/
		public final static @type(Long.class)  String csuoOrder="csuoOrder";
		/** 专车订单 [非空] [CsSpecialCar]      **/
		public final static @type(Long.class)  String csuoSpecial="csuoSpecial";
		/** 所属单位 [非空] [CsUnitInfo]      **/
		public final static @type(Long.class)  String csuoUnitInfo="csuoUnitInfo";
		/** 所属分组  [CsUnitGroup]      **/
		public final static @type(Long.class)  String csuoUnitGroup="csuoUnitGroup";
		/** 支付帐号 [非空] [CsMember]      **/
		public final static @type(Long.class)  String csuoUnitMember="csuoUnitMember";
		/** 使用帐号 [非空] [CsMember]  {csuoUnitInfo}:unit    ${basePath}${proname}/unit/info_room.do **/
		public final static @type(Long.class)  String csuoUnitChild="csuoUnitChild";
		/** 预订车辆  [CsCar]      **/
		public final static @type(Long.class)  String csuoCar="csuoCar";
		/** 车牌号码        **/
		public final static @type(String.class) @like String csuoNumber="csuoNumber";
		/** 订单类型 [非空]   0:代理订单 1:公务用车 2:紧急公务     **/
		public final static @type(Short.class)  String csuoType="csuoType";
		/** 用车人员  [CsUnitPerson]      **/
		public final static @type(Long.class)  String csuoPerson="csuoPerson";
		/** 使用人姓名        **/
		public final static @type(String.class) @like String csuoUsername="csuoUsername";
		/** 别名或标记        **/
		public final static @type(String.class) @like String csuoAlias="csuoAlias";
		/** 使用人手机        **/
		public final static @type(String.class) @like String csuoMobile="csuoMobile";
		/** 订单摘要        **/
		public final static @type(String.class) @like String csuoProfile="csuoProfile";
		/** 身份证号        **/
		public final static @type(String.class) @like String csuoIdcard="csuoIdcard";
		/** 订单开始时间 [非空]       **/
		public final static @type(Date.class)  String csuoStartTime="csuoStartTime";
	 	/** and csuo_start_time >= ? */
 		public final static @type(Date.class) String csuoStartTimeStart="csuoStartTimeStart";
 		/** and csuo_start_time <= ? */
 		public final static @type(Date.class) String csuoStartTimeEnd="csuoStartTimeEnd";
		/** 订单结束时间 [非空]       **/
		public final static @type(Date.class)  String csuoFinishTime="csuoFinishTime";
	 	/** and csuo_finish_time >= ? */
 		public final static @type(Date.class) String csuoFinishTimeStart="csuoFinishTimeStart";
 		/** and csuo_finish_time <= ? */
 		public final static @type(Date.class) String csuoFinishTimeEnd="csuoFinishTimeEnd";
		/** 实际还车时间        **/
		public final static @type(Date.class)  String csuoRetTime="csuoRetTime";
	 	/** and csuo_ret_time >= ? */
 		public final static @type(Date.class) String csuoRetTimeStart="csuoRetTimeStart";
 		/** and csuo_ret_time <= ? */
 		public final static @type(Date.class) String csuoRetTimeEnd="csuoRetTimeEnd";
		/** 免费小时(代理)        **/
		public final static @type(Integer.class)  String csuoFreeHour="csuoFreeHour";
		/** and csuo_free_hour >= ? */
		public final static @type(Integer.class) String csuoFreeHourMin="csuoFreeHourMin";
		/** and csuo_free_hour <= ? */
		public final static @type(Integer.class) String csuoFreeHourMax="csuoFreeHourMax";
		/** 免费里程(代理)        **/
		public final static @type(Double.class)  String csuoFreeKm="csuoFreeKm";
		/** and csuo_free_km >= ? */
		public final static @type(Double.class) String csuoFreeKmMin="csuoFreeKmMin";
		/** and csuo_free_km <= ? */
		public final static @type(Double.class) String csuoFreeKmMax="csuoFreeKmMax";
		/** 天租价格(代理)        **/
		public final static @type(Double.class)  String csuoDayPrice="csuoDayPrice";
		/** and csuo_day_price >= ? */
		public final static @type(Double.class) String csuoDayPriceMin="csuoDayPriceMin";
		/** and csuo_day_price <= ? */
		public final static @type(Double.class) String csuoDayPriceMax="csuoDayPriceMax";
		/** 小时价格(代理)        **/
		public final static @type(Double.class)  String csuoHourPrice="csuoHourPrice";
		/** and csuo_hour_price >= ? */
		public final static @type(Double.class) String csuoHourPriceMin="csuoHourPriceMin";
		/** and csuo_hour_price <= ? */
		public final static @type(Double.class) String csuoHourPriceMax="csuoHourPriceMax";
		/** 里程价格(代理)        **/
		public final static @type(Double.class)  String csuoKmPrice="csuoKmPrice";
		/** and csuo_km_price >= ? */
		public final static @type(Double.class) String csuoKmPriceMin="csuoKmPriceMin";
		/** and csuo_km_price <= ? */
		public final static @type(Double.class) String csuoKmPriceMax="csuoKmPriceMax";
		/** 应付金额(代理)        **/
		public final static @type(Double.class)  String csuoPayNeed="csuoPayNeed";
		/** and csuo_pay_need >= ? */
		public final static @type(Double.class) String csuoPayNeedMin="csuoPayNeedMin";
		/** and csuo_pay_need <= ? */
		public final static @type(Double.class) String csuoPayNeedMax="csuoPayNeedMax";
		/** 实付金额(代理)        **/
		public final static @type(Double.class)  String csuoPayReal="csuoPayReal";
		/** and csuo_pay_real >= ? */
		public final static @type(Double.class) String csuoPayRealMin="csuoPayRealMin";
		/** and csuo_pay_real <= ? */
		public final static @type(Double.class) String csuoPayRealMax="csuoPayRealMax";
		/** 订单备注        **/
		public final static @type(String.class) @like String csuoRemark="csuoRemark";
		/** 添加时间        **/
		public final static @type(Date.class)  String csuoAddTime="csuoAddTime";
	 	/** and csuo_add_time >= ? */
 		public final static @type(Date.class) String csuoAddTimeStart="csuoAddTimeStart";
 		/** and csuo_add_time <= ? */
 		public final static @type(Date.class) String csuoAddTimeEnd="csuoAddTimeEnd";
		/** 修改时间        **/
		public final static @type(Date.class)  String csuoUpdateTime="csuoUpdateTime";
	 	/** and csuo_update_time >= ? */
 		public final static @type(Date.class) String csuoUpdateTimeStart="csuoUpdateTimeStart";
 		/** and csuo_update_time <= ? */
 		public final static @type(Date.class) String csuoUpdateTimeEnd="csuoUpdateTimeEnd";
		/** 里程数        **/
		public final static @type(Double.class)  String csuoMileage="csuoMileage";
		/** and csuo_mileage >= ? */
		public final static @type(Double.class) String csuoMileageMin="csuoMileageMin";
		/** and csuo_mileage <= ? */
		public final static @type(Double.class) String csuoMileageMax="csuoMileageMax";
		/** 结算费用        **/
		public final static @type(Double.class)  String csuoSettlement="csuoSettlement";
		/** and csuo_settlement >= ? */
		public final static @type(Double.class) String csuoSettlementMin="csuoSettlementMin";
		/** and csuo_settlement <= ? */
		public final static @type(Double.class) String csuoSettlementMax="csuoSettlementMax";
		/** 结算状态    0:未结算 1:已结算     **/
		public final static @type(Short.class)  String csuoSettleState="csuoSettleState";
		/** 结算时间        **/
		public final static @type(Date.class)  String csuoSettleTime="csuoSettleTime";
	 	/** and csuo_settle_time >= ? */
 		public final static @type(Date.class) String csuoSettleTimeStart="csuoSettleTimeStart";
 		/** and csuo_settle_time <= ? */
 		public final static @type(Date.class) String csuoSettleTimeEnd="csuoSettleTimeEnd";
		/** 结账单  [CsUnitBill]      **/
		public final static @type(Long.class)  String csuoStatement="csuoStatement";
		/** 订单来源        **/
		public final static @type(String.class) @like String csuoFrom="csuoFrom";
		/** 审核时间        **/
		public final static @type(Date.class)  String csuoCheckTime="csuoCheckTime";
	 	/** and csuo_check_time >= ? */
 		public final static @type(Date.class) String csuoCheckTimeStart="csuoCheckTimeStart";
 		/** and csuo_check_time <= ? */
 		public final static @type(Date.class) String csuoCheckTimeEnd="csuoCheckTimeEnd";
		/** 审核人       SrvUser,CsUnitUser,CsUnitPerson **/
		public final static @type(String.class)  String csuoChecker="csuoChecker";
		/** 状态    1:等待审核 2:审核通过 3:审核未过 4:订单结束     **/
		public final static @type(Short.class)  String csuoState="csuoState";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csuoId="csuo_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csuoHost="csuo_host";
		/** 系统订单 [非空] [CsOrder]      **/
		public final static String csuoOrder="csuo_order";
		/** 专车订单 [非空] [CsSpecialCar]      **/
		public final static String csuoSpecial="csuo_special";
		/** 所属单位 [非空] [CsUnitInfo]      **/
		public final static String csuoUnitInfo="csuo_unit_info";
		/** 所属分组  [CsUnitGroup]      **/
		public final static String csuoUnitGroup="csuo_unit_group";
		/** 支付帐号 [非空] [CsMember]      **/
		public final static String csuoUnitMember="csuo_unit_member";
		/** 使用帐号 [非空] [CsMember]  {csuoUnitInfo}:unit    ${basePath}${proname}/unit/info_room.do **/
		public final static String csuoUnitChild="csuo_unit_child";
		/** 预订车辆  [CsCar]      **/
		public final static String csuoCar="csuo_car";
		/** 车牌号码        **/
		public final static String csuoNumber="csuo_number";
		/** 订单类型 [非空]   0:代理订单 1:公务用车 2:紧急公务     **/
		public final static String csuoType="csuo_type";
		/** 用车人员  [CsUnitPerson]      **/
		public final static String csuoPerson="csuo_person";
		/** 使用人姓名        **/
		public final static String csuoUsername="csuo_username";
		/** 别名或标记        **/
		public final static String csuoAlias="csuo_alias";
		/** 使用人手机        **/
		public final static String csuoMobile="csuo_mobile";
		/** 订单摘要        **/
		public final static String csuoProfile="csuo_profile";
		/** 身份证号        **/
		public final static String csuoIdcard="csuo_idcard";
		/** 订单开始时间 [非空]       **/
		public final static String csuoStartTime="csuo_start_time";
		/** 订单结束时间 [非空]       **/
		public final static String csuoFinishTime="csuo_finish_time";
		/** 实际还车时间        **/
		public final static String csuoRetTime="csuo_ret_time";
		/** 免费小时(代理)        **/
		public final static String csuoFreeHour="csuo_free_hour";
		/** 免费里程(代理)        **/
		public final static String csuoFreeKm="csuo_free_km";
		/** 天租价格(代理)        **/
		public final static String csuoDayPrice="csuo_day_price";
		/** 小时价格(代理)        **/
		public final static String csuoHourPrice="csuo_hour_price";
		/** 里程价格(代理)        **/
		public final static String csuoKmPrice="csuo_km_price";
		/** 应付金额(代理)        **/
		public final static String csuoPayNeed="csuo_pay_need";
		/** 实付金额(代理)        **/
		public final static String csuoPayReal="csuo_pay_real";
		/** 订单备注        **/
		public final static String csuoRemark="csuo_remark";
		/** 添加时间        **/
		public final static String csuoAddTime="csuo_add_time";
		/** 修改时间        **/
		public final static String csuoUpdateTime="csuo_update_time";
		/** 里程数        **/
		public final static String csuoMileage="csuo_mileage";
		/** 结算费用        **/
		public final static String csuoSettlement="csuo_settlement";
		/** 结算状态    0:未结算 1:已结算     **/
		public final static String csuoSettleState="csuo_settle_state";
		/** 结算时间        **/
		public final static String csuoSettleTime="csuo_settle_time";
		/** 结账单  [CsUnitBill]      **/
		public final static String csuoStatement="csuo_statement";
		/** 订单来源        **/
		public final static String csuoFrom="csuo_from";
		/** 审核时间        **/
		public final static String csuoCheckTime="csuo_check_time";
		/** 审核人       SrvUser,CsUnitUser,CsUnitPerson **/
		public final static String csuoChecker="csuo_checker";
		/** 状态    1:等待审核 2:审核通过 3:审核未过 4:订单结束     **/
		public final static String csuoState="csuo_state";
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
				$.Set(name,CsUnitOrder.getCsUnitOrder(params));
			else
				$.Set(name,CsUnitOrder.getCsUnitOrderList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取企业订单数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsUnitOrder) $.GetRequest("CsUnitOrder$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsUnitOrder.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsUnitOrder.getCsUnitOrder(params);
			else
				value = CsUnitOrder.getCsUnitOrderList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsUnitOrder.Get($.add(CsUnitOrder.F.csuoId,param));
		else if(!$.empty(param.toString()))
			value = CsUnitOrder.get(Long.valueOf(param.toString()));
		$.SetRequest("CsUnitOrder$"+param.hashCode(), value);
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
	public void mergeSet(CsUnitOrder old){
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