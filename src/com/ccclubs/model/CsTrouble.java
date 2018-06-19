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

@namespace("service/trouble")
public @caption("车辆事故") @table("cs_trouble") class CsTrouble implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cst_id")   @primary  @note("  ") Long cstId;// 主键 非空     
	private @caption("城市") @column("cst_host")    @relate("$cstHost") @RelateClass(SrvHost.class)  @note("  ") Long cstHost;// 非空     
 	private SrvHost $cstHost;//关联对象[运营城市]
	private @caption("事故名称") @column("cst_title")  @hidden   @note("  ") String cstTitle;// 非空     
	private @caption("事故订单") @column("cst_order")    @relate("$cstOrder") @RelateClass(CsOrder.class)  @note("  ") Long cstOrder;// 非空     
 	private CsOrder $cstOrder;//关联对象[系统订单]
	private @caption("事故会员") @column("cst_member")    @relate("$cstMember") @RelateClass(CsMember.class)  @note("  ") Long cstMember;// 非空    ${basePath}${proname}/user/member_inquire.do 
 	private CsMember $cstMember;//关联对象[会员帐号]
	private @caption("事故车辆") @column("cst_car")    @relate("$cstCar") @RelateClass(CsCar.class)  @note("  ") Long cstCar;// 非空     
 	private CsCar $cstCar;//关联对象[车辆]
	private @caption("事故类型") @column("cst_type")    @note(" 0:简易事故 1:单方事故 2:我方全责 3:对方全责 4:同等责任  ") Short cstType;// 非空 0:简易事故 1:单方事故 2:我方全责 3:对方全责 4:同等责任     
	private @caption("车损情况") @column("cst_damage")    @note("  ") String cstDamage;//     
	private @caption("送修地点") @column("cst_repair_adr")    @relate("$cstRepairAdr") @RelateClass(CsRepairAdr.class)  @note("  ") Long cstRepairAdr;//     
 	private CsRepairAdr $cstRepairAdr;//关联对象[维修地点]
	private @caption("维修部位") @column("cst_repair_part")    @note("  ") String cstRepairPart;//     
	private @caption("维修详细信息") @column("cst_repair")    @relate("$cstRepair") @RelateClass(CsRepair.class)  @note("  ") Long cstRepair;//     
 	private CsRepair $cstRepair;//关联对象[车辆维修]
	private @caption("开票金额") @column("cst_invoice")    @note("  ") Double cstInvoice;//(单位：元)     
	private @caption("保险定损") @column("cst_insure_fee")    @note("  ") Double cstInsureFee;//(单位：元)     
	private @caption("协商扣款") @column("cst_money")    @note("  将从会员保证金里扣除的钱") Double cstMoney;//(单位：元) 非空  将从会员保证金里扣除的钱   
	private @caption("三者损失") @column("cst_third_loss")    @note("  ") Double cstThirdLoss;//(单位：元)     
	private @caption("拖车费用") @column("cst_trail_fee")    @note("  ") Double cstTrailFee;//     
	private @caption("返还垫付时间") @column("cst_repay_date")    @note("  返还给垫付人垫付款的时间") Date cstRepayDate;//  返还给垫付人垫付款的时间   
	private @caption("返还垫付方式") @column("cst_repay_type")    @note(" 0:银行转帐 1:返还保证金 2:返还现金  ") Short cstRepayType;// 0:银行转帐 1:返还保证金 2:返还现金     
	private @caption("三者垫付备注") @column("cst_third_desc")    @note("  三者损失、垫付等其它备注信息") String cstThirdDesc;//  三者损失、垫付等其它备注信息   
	private @caption("赔付到账") @column("cst_compensate")    @note("  ") Double cstCompensate;//(单位：元)     
	private @caption("赔付到账时间") @column("cst_comp_date")    @note("  ") Date cstCompDate;//     
	private @caption("维修费用") @column("cst_repair_pay")    @note("  ") Double cstRepairPay;//(单位：元)     
	private @caption("合计差价") @column("cst_agio")    @note("  ") Double cstAgio;// 非空     
	private @caption("事故描述") @column("cst_decipt")    @note("  ") String cstDecipt;//     
	private @caption("图片列表") @column("cst_images")  @hidden   @note("  ") String cstImages;//     
	private @caption("发生时间") @column("cst_happen_time")    @note("  ") Date cstHappenTime;// 非空     
	private @caption("客服通知") @column("cst_notice")    @note(" 0:未通知 1:已通知  ") Short cstNotice;// 非空 0:未通知 1:已通知     
	private @caption("受理标记") @column("cst_deal_flag")    @note(" 0:默认 1:配合 2:不配合  ") Short cstDealFlag;// 0:默认 1:配合 2:不配合     
	private @caption("受理人员") @column("cst_accepter")    @relate("$cstAccepter") @RelateClass(SrvUser.class)  @note("  ") Long cstAccepter;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cstAccepter;//关联对象[用户]
	private @caption("结案人员") @column("cst_caser")    @relate("$cstCaser") @RelateClass(SrvUser.class)  @note("  ") Long cstCaser;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cstCaser;//关联对象[用户]
	private @caption("结案时间") @column("cst_finish_time")    @note("  ") Date cstFinishTime;//     
	private @caption("备注信息") @column("cst_remark")    @note("  ") String cstRemark;//     
	private @caption("更新时间") @column("cst_update_time")    @note("  ") Date cstUpdateTime;//     
	private @caption("添加时间") @column("cst_add_time")    @note("  ") Date cstAddTime;// 非空     
	private @caption("处理状态") @column("cst_status")    @note(" 0:已录入待定损 1:已定损待协商 2:已协商待维修 3:正在维修 4:已维修待扣款 5:已扣款待结案 6:已结案  ") Short cstStatus;// 非空 0:已录入待定损 1:已定损待协商 2:已协商待维修 3:正在维修 4:已维修待扣款 5:已扣款待结案 6:已结案     
	
	//默认构造函数
	public CsTrouble(){
	
	}
	
	//主键构造函数
	public CsTrouble(Long id){
		this.cstId = id;
	}
	
	/**所有字段构造函数 CsTrouble(cstHost,cstTitle,cstOrder,cstMember,cstCar,cstType,cstDamage,cstRepairAdr,cstRepairPart,cstRepair,cstInvoice,cstInsureFee,cstMoney,cstThirdLoss,cstTrailFee,cstRepayDate,cstRepayType,cstThirdDesc,cstCompensate,cstCompDate,cstRepairPay,cstAgio,cstDecipt,cstImages,cstHappenTime,cstNotice,cstDealFlag,cstAccepter,cstCaser,cstFinishTime,cstRemark,cstUpdateTime,cstAddTime,cstStatus)
	 CsTrouble(
	 	$.getLong("cstHost")//城市 [非空]
	 	,$.getString("cstTitle")//事故名称 [非空]
	 	,$.getLong("cstOrder")//事故订单 [非空]
	 	,$.getLong("cstMember")//事故会员 [非空]
	 	,$.getLong("cstCar")//事故车辆 [非空]
	 	,$.getShort("cstType")//事故类型 [非空]
	 	,$.getString("cstDamage")//车损情况
	 	,$.getLong("cstRepairAdr")//送修地点
	 	,$.getString("cstRepairPart")//维修部位
	 	,$.getLong("cstRepair")//维修详细信息
	 	,$.getDouble("cstInvoice")//开票金额
	 	,$.getDouble("cstInsureFee")//保险定损
	 	,$.getDouble("cstMoney")//协商扣款 [非空]
	 	,$.getDouble("cstThirdLoss")//三者损失
	 	,$.getDouble("cstTrailFee")//拖车费用
	 	,$.getDate("cstRepayDate")//返还垫付时间
	 	,$.getShort("cstRepayType")//返还垫付方式
	 	,$.getString("cstThirdDesc")//三者垫付备注
	 	,$.getDouble("cstCompensate")//赔付到账
	 	,$.getDate("cstCompDate")//赔付到账时间
	 	,$.getDouble("cstRepairPay")//维修费用
	 	,$.getDouble("cstAgio")//合计差价 [非空]
	 	,$.getString("cstDecipt")//事故描述
	 	,$.getString("cstImages")//图片列表
	 	,$.getDate("cstHappenTime")//发生时间 [非空]
	 	,$.getShort("cstNotice")//客服通知 [非空]
	 	,$.getShort("cstDealFlag")//受理标记
	 	,$.getLong("cstAccepter")//受理人员 [非空]
	 	,$.getLong("cstCaser")//结案人员
	 	,$.getDate("cstFinishTime")//结案时间
	 	,$.getString("cstRemark")//备注信息
	 	,$.getDate("cstUpdateTime")//更新时间
	 	,$.getDate("cstAddTime")//添加时间 [非空]
	 	,$.getShort("cstStatus")//处理状态 [非空]
	 )
	**/
	public CsTrouble(Long cstHost,String cstTitle,Long cstOrder,Long cstMember,Long cstCar,Short cstType,String cstDamage,Long cstRepairAdr,String cstRepairPart,Long cstRepair,Double cstInvoice,Double cstInsureFee,Double cstMoney,Double cstThirdLoss,Double cstTrailFee,Date cstRepayDate,Short cstRepayType,String cstThirdDesc,Double cstCompensate,Date cstCompDate,Double cstRepairPay,Double cstAgio,String cstDecipt,String cstImages,Date cstHappenTime,Short cstNotice,Short cstDealFlag,Long cstAccepter,Long cstCaser,Date cstFinishTime,String cstRemark,Date cstUpdateTime,Date cstAddTime,Short cstStatus){
		this.cstHost=cstHost;
		this.cstTitle=cstTitle;
		this.cstOrder=cstOrder;
		this.cstMember=cstMember;
		this.cstCar=cstCar;
		this.cstType=cstType;
		this.cstDamage=cstDamage;
		this.cstRepairAdr=cstRepairAdr;
		this.cstRepairPart=cstRepairPart;
		this.cstRepair=cstRepair;
		this.cstInvoice=cstInvoice;
		this.cstInsureFee=cstInsureFee;
		this.cstMoney=cstMoney;
		this.cstThirdLoss=cstThirdLoss;
		this.cstTrailFee=cstTrailFee;
		this.cstRepayDate=cstRepayDate;
		this.cstRepayType=cstRepayType;
		this.cstThirdDesc=cstThirdDesc;
		this.cstCompensate=cstCompensate;
		this.cstCompDate=cstCompDate;
		this.cstRepairPay=cstRepairPay;
		this.cstAgio=cstAgio;
		this.cstDecipt=cstDecipt;
		this.cstImages=cstImages;
		this.cstHappenTime=cstHappenTime;
		this.cstNotice=cstNotice;
		this.cstDealFlag=cstDealFlag;
		this.cstAccepter=cstAccepter;
		this.cstCaser=cstCaser;
		this.cstFinishTime=cstFinishTime;
		this.cstRemark=cstRemark;
		this.cstUpdateTime=cstUpdateTime;
		this.cstAddTime=cstAddTime;
		this.cstStatus=cstStatus;
	}
	
	//设置非空字段
	public CsTrouble setNotNull(Long cstHost,String cstTitle,Long cstOrder,Long cstMember,Long cstCar,Short cstType,Double cstMoney,Double cstAgio,Date cstHappenTime,Short cstNotice,Long cstAccepter,Date cstAddTime,Short cstStatus){
		this.cstHost=cstHost;
		this.cstTitle=cstTitle;
		this.cstOrder=cstOrder;
		this.cstMember=cstMember;
		this.cstCar=cstCar;
		this.cstType=cstType;
		this.cstMoney=cstMoney;
		this.cstAgio=cstAgio;
		this.cstHappenTime=cstHappenTime;
		this.cstNotice=cstNotice;
		this.cstAccepter=cstAccepter;
		this.cstAddTime=cstAddTime;
		this.cstStatus=cstStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsTrouble cstId(Long cstId){
		this.cstId = cstId;
		this.setSeted(F.cstId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsTrouble cstHost(Long cstHost){
		this.cstHost = cstHost;
		this.setSeted(F.cstHost);
		return this;
	}
	/** 事故名称 [非空]       **/
	public CsTrouble cstTitle(String cstTitle){
		this.cstTitle = cstTitle;
		this.setSeted(F.cstTitle);
		return this;
	}
	/** 事故订单 [非空] [CsOrder]      **/
	public CsTrouble cstOrder(Long cstOrder){
		this.cstOrder = cstOrder;
		this.setSeted(F.cstOrder);
		return this;
	}
	/** 事故会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
	public CsTrouble cstMember(Long cstMember){
		this.cstMember = cstMember;
		this.setSeted(F.cstMember);
		return this;
	}
	/** 事故车辆 [非空] [CsCar]      **/
	public CsTrouble cstCar(Long cstCar){
		this.cstCar = cstCar;
		this.setSeted(F.cstCar);
		return this;
	}
	/** 事故类型 [非空]   0:简易事故 1:单方事故 2:我方全责 3:对方全责 4:同等责任     **/
	public CsTrouble cstType(Short cstType){
		this.cstType = cstType;
		this.setSeted(F.cstType);
		return this;
	}
	/** 车损情况        **/
	public CsTrouble cstDamage(String cstDamage){
		this.cstDamage = cstDamage;
		this.setSeted(F.cstDamage);
		return this;
	}
	/** 送修地点  [CsRepairAdr]      **/
	public CsTrouble cstRepairAdr(Long cstRepairAdr){
		this.cstRepairAdr = cstRepairAdr;
		this.setSeted(F.cstRepairAdr);
		return this;
	}
	/** 维修部位        **/
	public CsTrouble cstRepairPart(String cstRepairPart){
		this.cstRepairPart = cstRepairPart;
		this.setSeted(F.cstRepairPart);
		return this;
	}
	/** 维修详细信息  [CsRepair]      **/
	public CsTrouble cstRepair(Long cstRepair){
		this.cstRepair = cstRepair;
		this.setSeted(F.cstRepair);
		return this;
	}
	/** 开票金额        **/
	public CsTrouble cstInvoice(Double cstInvoice){
		this.cstInvoice = cstInvoice;
		this.setSeted(F.cstInvoice);
		return this;
	}
	/** 保险定损        **/
	public CsTrouble cstInsureFee(Double cstInsureFee){
		this.cstInsureFee = cstInsureFee;
		this.setSeted(F.cstInsureFee);
		return this;
	}
	/** 协商扣款 [非空]    将从会员保证金里扣除的钱   **/
	public CsTrouble cstMoney(Double cstMoney){
		this.cstMoney = cstMoney;
		this.setSeted(F.cstMoney);
		return this;
	}
	/** 三者损失        **/
	public CsTrouble cstThirdLoss(Double cstThirdLoss){
		this.cstThirdLoss = cstThirdLoss;
		this.setSeted(F.cstThirdLoss);
		return this;
	}
	/** 拖车费用        **/
	public CsTrouble cstTrailFee(Double cstTrailFee){
		this.cstTrailFee = cstTrailFee;
		this.setSeted(F.cstTrailFee);
		return this;
	}
	/** 返还垫付时间     返还给垫付人垫付款的时间   **/
	public CsTrouble cstRepayDate(Date cstRepayDate){
		this.cstRepayDate = cstRepayDate;
		this.setSeted(F.cstRepayDate);
		return this;
	}
	/** 返还垫付方式    0:银行转帐 1:返还保证金 2:返还现金     **/
	public CsTrouble cstRepayType(Short cstRepayType){
		this.cstRepayType = cstRepayType;
		this.setSeted(F.cstRepayType);
		return this;
	}
	/** 三者垫付备注     三者损失、垫付等其它备注信息   **/
	public CsTrouble cstThirdDesc(String cstThirdDesc){
		this.cstThirdDesc = cstThirdDesc;
		this.setSeted(F.cstThirdDesc);
		return this;
	}
	/** 赔付到账        **/
	public CsTrouble cstCompensate(Double cstCompensate){
		this.cstCompensate = cstCompensate;
		this.setSeted(F.cstCompensate);
		return this;
	}
	/** 赔付到账时间        **/
	public CsTrouble cstCompDate(Date cstCompDate){
		this.cstCompDate = cstCompDate;
		this.setSeted(F.cstCompDate);
		return this;
	}
	/** 维修费用        **/
	public CsTrouble cstRepairPay(Double cstRepairPay){
		this.cstRepairPay = cstRepairPay;
		this.setSeted(F.cstRepairPay);
		return this;
	}
	/** 合计差价 [非空]       **/
	public CsTrouble cstAgio(Double cstAgio){
		this.cstAgio = cstAgio;
		this.setSeted(F.cstAgio);
		return this;
	}
	/** 事故描述        **/
	public CsTrouble cstDecipt(String cstDecipt){
		this.cstDecipt = cstDecipt;
		this.setSeted(F.cstDecipt);
		return this;
	}
	/** 图片列表        **/
	public CsTrouble cstImages(String cstImages){
		this.cstImages = cstImages;
		this.setSeted(F.cstImages);
		return this;
	}
	/** 发生时间 [非空]       **/
	public CsTrouble cstHappenTime(Date cstHappenTime){
		this.cstHappenTime = cstHappenTime;
		this.setSeted(F.cstHappenTime);
		return this;
	}
	/** 客服通知 [非空]   0:未通知 1:已通知     **/
	public CsTrouble cstNotice(Short cstNotice){
		this.cstNotice = cstNotice;
		this.setSeted(F.cstNotice);
		return this;
	}
	/** 受理标记    0:默认 1:配合 2:不配合     **/
	public CsTrouble cstDealFlag(Short cstDealFlag){
		this.cstDealFlag = cstDealFlag;
		this.setSeted(F.cstDealFlag);
		return this;
	}
	/** 受理人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsTrouble cstAccepter(Long cstAccepter){
		this.cstAccepter = cstAccepter;
		this.setSeted(F.cstAccepter);
		return this;
	}
	/** 结案人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsTrouble cstCaser(Long cstCaser){
		this.cstCaser = cstCaser;
		this.setSeted(F.cstCaser);
		return this;
	}
	/** 结案时间        **/
	public CsTrouble cstFinishTime(Date cstFinishTime){
		this.cstFinishTime = cstFinishTime;
		this.setSeted(F.cstFinishTime);
		return this;
	}
	/** 备注信息        **/
	public CsTrouble cstRemark(String cstRemark){
		this.cstRemark = cstRemark;
		this.setSeted(F.cstRemark);
		return this;
	}
	/** 更新时间        **/
	public CsTrouble cstUpdateTime(Date cstUpdateTime){
		this.cstUpdateTime = cstUpdateTime;
		this.setSeted(F.cstUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsTrouble cstAddTime(Date cstAddTime){
		this.cstAddTime = cstAddTime;
		this.setSeted(F.cstAddTime);
		return this;
	}
	/** 处理状态 [非空]   0:已录入待定损 1:已定损待协商 2:已协商待维修 3:正在维修 4:已维修待扣款 5:已扣款待结案 6:已结案     **/
	public CsTrouble cstStatus(Short cstStatus){
		this.cstStatus = cstStatus;
		this.setSeted(F.cstStatus);
		return this;
	}
	
	
	//克隆对象
	public CsTrouble clone(){
		CsTrouble clone = new CsTrouble();
		clone.cstHost=this.cstHost;
		clone.cstTitle=this.cstTitle;
		clone.cstOrder=this.cstOrder;
		clone.cstMember=this.cstMember;
		clone.cstCar=this.cstCar;
		clone.cstType=this.cstType;
		clone.cstMoney=this.cstMoney;
		clone.cstAgio=this.cstAgio;
		clone.cstHappenTime=this.cstHappenTime;
		clone.cstNotice=this.cstNotice;
		clone.cstAccepter=this.cstAccepter;
		clone.cstAddTime=this.cstAddTime;
		clone.cstStatus=this.cstStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取车辆事故
	 * @param id
	 * @return
	 */
	public static @api CsTrouble get(Long id){		
		return getCsTroubleById(id);
	}
	/**
	 * 获取所有车辆事故
	 * @return
	 */
	public static @api List<CsTrouble> list(Map params,Integer size){
		return getCsTroubleList(params,size);
	}
	/**
	 * 获取车辆事故分页
	 * @return
	 */
	public static @api Page<CsTrouble> page(int page,int size,Map params){
		return getCsTroublePage(page, size , params);
	}
	/**
	 * 根据查询条件取车辆事故
	 * @param params
	 * @return
	 */
	public static @api CsTrouble Get(Map params){
		return getCsTrouble(params);
	}
	/**
	 * 获取车辆事故数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsTroubleCount(params);
	}
	/**
	 * 获取车辆事故数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsTroubleEval(eval,params);
	}
	
	/**
	 * 根据ID取车辆事故
	 * @param id
	 * @return
	 */
	public static @api CsTrouble getCsTroubleById(Long id){		
		CsTrouble csTrouble = (CsTrouble) $.GetRequest("CsTrouble$"+id);
		if(csTrouble!=null)
			return csTrouble;
		com.ccclubs.service.admin.ICsTroubleService csTroubleService = $.GetSpringBean("csTroubleService");		
		return csTroubleService.getCsTroubleById(id);
	}
	
	
	/**
	 * 根据ID取车辆事故的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsTrouble.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsTrouble csTrouble = get(id);
		if(csTrouble!=null){
			String strValue = csTrouble.getCstId$();
			if(!"CstId".equals("CstId"))
				strValue+="("+csTrouble.getCstId$()+")";
			MemCache.setValue(CsTrouble.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCstId$();
		if(!"CstId".equals("CstId"))
			keyValue+="("+this.getCstId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有车辆事故
	 * @return
	 */
	public static @api List<CsTrouble> getCsTroubleList(Map params,Integer size){
		com.ccclubs.service.admin.ICsTroubleService csTroubleService = $.GetSpringBean("csTroubleService");
		return csTroubleService.getCsTroubleList(params, size);
	}
	
	/**
	 * 获取车辆事故分页
	 * @return
	 */
	public static @api Page<CsTrouble> getCsTroublePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsTroubleService csTroubleService = $.GetSpringBean("csTroubleService");
		return csTroubleService.getCsTroublePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取车辆事故
	 * @param params
	 * @return
	 */
	public static @api CsTrouble getCsTrouble(Map params){
		com.ccclubs.service.admin.ICsTroubleService csTroubleService = $.GetSpringBean("csTroubleService");
		return csTroubleService.getCsTrouble(params);
	}
	
	/**
	 * 获取车辆事故数
	 * @return
	 */
	public static @api Long getCsTroubleCount(Map params){
		com.ccclubs.service.admin.ICsTroubleService csTroubleService = $.GetSpringBean("csTroubleService");
		return csTroubleService.getCsTroubleCount(params);
	}
		
		
	/**
	 * 获取车辆事故自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsTroubleEval(String eval,Map params){
		com.ccclubs.service.admin.ICsTroubleService csTroubleService = $.GetSpringBean("csTroubleService");
		return csTroubleService.getCsTroubleEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsTrouble(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsTroubleService csTroubleService = $.GetSpringBean("csTroubleService");
		csTroubleService.updateCsTroubleByConfirm(set, where);
	}
	
	
	/**
	 * 保存车辆事故对象
	 * @param params
	 * @return
	 */
	public CsTrouble save(){
		com.ccclubs.service.admin.ICsTroubleService csTroubleService = $.GetSpringBean("csTroubleService");
		if(this.getCstId()!=null)
			csTroubleService.updateCsTrouble(this);
		else
			return csTroubleService.saveCsTrouble(this);
		return this;
	}
	
	
	/**
	 * 更新车辆事故对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsTroubleService csTroubleService = $.GetSpringBean("csTroubleService");
		csTroubleService.updateCsTrouble$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsTroubleService csTroubleService = $.GetSpringBean("csTroubleService");
		if($.equals($.config("logic_delete"),"true"))
			csTroubleService.removeCsTroubleById(this.getCstId());
		else
			csTroubleService.deleteCsTroubleById(this.getCstId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsTroubleService csTroubleService = $.GetSpringBean("csTroubleService");
		return csTroubleService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCstId(){
		return this.cstId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCstId$(){
		String strValue="";
		 strValue=$.str(this.getCstId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCstId(Long cstId){
		this.cstId = cstId;
		this.setSeted(F.cstId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCstHost(){
		return this.cstHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCstHost$(){
		String strValue="";
		if(this.getCstHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCstHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCstHost(Long cstHost){
		this.cstHost = cstHost;
		this.setSeted(F.cstHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cstHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCstHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCstHost()!=null){
 			srvHost = SrvHost.get(this.getCstHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCstHost(), srvHost);
	 	return srvHost;
	}
	/*******************************事故名称**********************************/	
	/**
	* 事故名称 [非空]      
	**/
	public String getCstTitle(){
		return this.cstTitle;
	}
	/**
	* 获取事故名称格式化(toString)
	**/
	public String getCstTitle$(){
		String strValue="";
		 strValue=$.str(this.getCstTitle());
	 	 return strValue;
	}
	/**
	* 事故名称 [非空]      
	**/
	public void setCstTitle(String cstTitle){
		this.cstTitle = cstTitle;
		this.setSeted(F.cstTitle);
	}
	/*******************************事故订单**********************************/	
	/**
	* 事故订单 [非空] [CsOrder]     
	**/
	public Long getCstOrder(){
		return this.cstOrder;
	}
	/**
	* 获取事故订单格式化(toString)
	**/
	public String getCstOrder$(){
		String strValue="";
		if(this.getCstOrder()!=null){
				strValue+=$.str(CsOrder.getKeyValue(this.getCstOrder()));
		}			
	 	 return strValue;
	}
	/**
	* 事故订单 [非空] [CsOrder]     
	**/
	public void setCstOrder(Long cstOrder){
		this.cstOrder = cstOrder;
		this.setSeted(F.cstOrder);
	}
	/**
	* 获取关联对象[系统订单]
	**/	 			
 	public CsOrder get$cstOrder(){
 		com.ccclubs.model.CsOrder csOrder = (com.ccclubs.model.CsOrder) $.GetRequest("CsOrder$"+this.getCstOrder());
 		if(csOrder!=null)
			return csOrder;
		if(this.getCstOrder()!=null){
 			csOrder = CsOrder.get(this.getCstOrder());
 		}
 		$.SetRequest("CsOrder$"+this.getCstOrder(), csOrder);
	 	return csOrder;
	}
	/*******************************事故会员**********************************/	
	/**
	* 事故会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public Long getCstMember(){
		return this.cstMember;
	}
	/**
	* 获取事故会员格式化(toString)
	**/
	public String getCstMember$(){
		String strValue="";
		if(this.getCstMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCstMember()));
		}			
	 	 return strValue;
	}
	/**
	* 事故会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public void setCstMember(Long cstMember){
		this.cstMember = cstMember;
		this.setSeted(F.cstMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$cstMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCstMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCstMember()!=null){
 			csMember = CsMember.get(this.getCstMember());
 		}
 		$.SetRequest("CsMember$"+this.getCstMember(), csMember);
	 	return csMember;
	}
	/*******************************事故车辆**********************************/	
	/**
	* 事故车辆 [非空] [CsCar]     
	**/
	public Long getCstCar(){
		return this.cstCar;
	}
	/**
	* 获取事故车辆格式化(toString)
	**/
	public String getCstCar$(){
		String strValue="";
		if(this.getCstCar()!=null){
				strValue+=$.str(CsCar.getKeyValue(this.getCstCar()));
		}			
	 	 return strValue;
	}
	/**
	* 事故车辆 [非空] [CsCar]     
	**/
	public void setCstCar(Long cstCar){
		this.cstCar = cstCar;
		this.setSeted(F.cstCar);
	}
	/**
	* 获取关联对象[车辆]
	**/	 			
 	public CsCar get$cstCar(){
 		com.ccclubs.model.CsCar csCar = (com.ccclubs.model.CsCar) $.GetRequest("CsCar$"+this.getCstCar());
 		if(csCar!=null)
			return csCar;
		if(this.getCstCar()!=null){
 			csCar = CsCar.get(this.getCstCar());
 		}
 		$.SetRequest("CsCar$"+this.getCstCar(), csCar);
	 	return csCar;
	}
	/*******************************事故类型**********************************/	
	/**
	* 事故类型 [非空]   0:简易事故 1:单方事故 2:我方全责 3:对方全责 4:同等责任    
	**/
	public Short getCstType(){
		return this.cstType;
	}
	/**
	* 获取事故类型格式化(toString)
	**/
	public String getCstType$(){
		String strValue="";
		 if($.equals($.str(this.getCstType()),"0"))
			strValue=$.str("简易事故");		 
		 if($.equals($.str(this.getCstType()),"1"))
			strValue=$.str("单方事故");		 
		 if($.equals($.str(this.getCstType()),"2"))
			strValue=$.str("我方全责");		 
		 if($.equals($.str(this.getCstType()),"3"))
			strValue=$.str("对方全责");		 
		 if($.equals($.str(this.getCstType()),"4"))
			strValue=$.str("同等责任");		 
	 	 return strValue;
	}
	/**
	* 事故类型 [非空]   0:简易事故 1:单方事故 2:我方全责 3:对方全责 4:同等责任    
	**/
	public void setCstType(Short cstType){
		this.cstType = cstType;
		this.setSeted(F.cstType);
	}
	/*******************************车损情况**********************************/	
	/**
	* 车损情况       
	**/
	public String getCstDamage(){
		return this.cstDamage;
	}
	/**
	* 获取车损情况格式化(toString)
	**/
	public String getCstDamage$(){
		String strValue="";
		 strValue=$.str(this.getCstDamage());
	 	 return strValue;
	}
	/**
	* 车损情况       
	**/
	public void setCstDamage(String cstDamage){
		this.cstDamage = cstDamage;
		this.setSeted(F.cstDamage);
	}
	/*******************************送修地点**********************************/	
	/**
	* 送修地点  [CsRepairAdr]     
	**/
	public Long getCstRepairAdr(){
		return this.cstRepairAdr;
	}
	/**
	* 获取送修地点格式化(toString)
	**/
	public String getCstRepairAdr$(){
		String strValue="";
		if(this.getCstRepairAdr()!=null){
				strValue+=$.str(CsRepairAdr.getKeyValue(this.getCstRepairAdr()));
		}			
	 	 return strValue;
	}
	/**
	* 送修地点  [CsRepairAdr]     
	**/
	public void setCstRepairAdr(Long cstRepairAdr){
		this.cstRepairAdr = cstRepairAdr;
		this.setSeted(F.cstRepairAdr);
	}
	/**
	* 获取关联对象[维修地点]
	**/	 			
 	public CsRepairAdr get$cstRepairAdr(){
 		com.ccclubs.model.CsRepairAdr csRepairAdr = (com.ccclubs.model.CsRepairAdr) $.GetRequest("CsRepairAdr$"+this.getCstRepairAdr());
 		if(csRepairAdr!=null)
			return csRepairAdr;
		if(this.getCstRepairAdr()!=null){
 			csRepairAdr = CsRepairAdr.get(this.getCstRepairAdr());
 		}
 		$.SetRequest("CsRepairAdr$"+this.getCstRepairAdr(), csRepairAdr);
	 	return csRepairAdr;
	}
	/*******************************维修部位**********************************/	
	/**
	* 维修部位       
	**/
	public String getCstRepairPart(){
		return this.cstRepairPart;
	}
	/**
	* 获取维修部位格式化(toString)
	**/
	public String getCstRepairPart$(){
		String strValue="";
		 strValue=$.str(this.getCstRepairPart());
	 	 return strValue;
	}
	/**
	* 维修部位       
	**/
	public void setCstRepairPart(String cstRepairPart){
		this.cstRepairPart = cstRepairPart;
		this.setSeted(F.cstRepairPart);
	}
	/*******************************维修详细信息**********************************/	
	/**
	* 维修详细信息  [CsRepair]     
	**/
	public Long getCstRepair(){
		return this.cstRepair;
	}
	/**
	* 获取维修详细信息格式化(toString)
	**/
	public String getCstRepair$(){
		String strValue="";
		if(this.getCstRepair()!=null){
				strValue+=$.str(CsRepair.getKeyValue(this.getCstRepair()));
		}			
	 	 return strValue;
	}
	/**
	* 维修详细信息  [CsRepair]     
	**/
	public void setCstRepair(Long cstRepair){
		this.cstRepair = cstRepair;
		this.setSeted(F.cstRepair);
	}
	/**
	* 获取关联对象[车辆维修]
	**/	 			
 	public CsRepair get$cstRepair(){
 		com.ccclubs.model.CsRepair csRepair = (com.ccclubs.model.CsRepair) $.GetRequest("CsRepair$"+this.getCstRepair());
 		if(csRepair!=null)
			return csRepair;
		if(this.getCstRepair()!=null){
 			csRepair = CsRepair.get(this.getCstRepair());
 		}
 		$.SetRequest("CsRepair$"+this.getCstRepair(), csRepair);
	 	return csRepair;
	}
	/*******************************开票金额**********************************/	
	/**
	* 开票金额       
	**/
	public Double getCstInvoice(){
		return this.cstInvoice;
	}
	/**
	* 获取开票金额格式化(toString)
	**/
	public String getCstInvoice$(){
		String strValue="";
		 strValue=$.str(this.getCstInvoice());	
	 	 return strValue;
	}
	/**
	* 开票金额       
	**/
	public void setCstInvoice(Double cstInvoice){
		this.cstInvoice = cstInvoice;
		this.setSeted(F.cstInvoice);
	}
	/*******************************保险定损**********************************/	
	/**
	* 保险定损       
	**/
	public Double getCstInsureFee(){
		return this.cstInsureFee;
	}
	/**
	* 获取保险定损格式化(toString)
	**/
	public String getCstInsureFee$(){
		String strValue="";
		 strValue=$.str(this.getCstInsureFee());	
	 	 return strValue;
	}
	/**
	* 保险定损       
	**/
	public void setCstInsureFee(Double cstInsureFee){
		this.cstInsureFee = cstInsureFee;
		this.setSeted(F.cstInsureFee);
	}
	/*******************************协商扣款**********************************/	
	/**
	* 协商扣款 [非空]    将从会员保证金里扣除的钱  
	**/
	public Double getCstMoney(){
		return this.cstMoney;
	}
	/**
	* 获取协商扣款格式化(toString)
	**/
	public String getCstMoney$(){
		String strValue="";
		 strValue=$.str(this.getCstMoney());	
	 	 return strValue;
	}
	/**
	* 协商扣款 [非空]    将从会员保证金里扣除的钱  
	**/
	public void setCstMoney(Double cstMoney){
		this.cstMoney = cstMoney;
		this.setSeted(F.cstMoney);
	}
	/*******************************三者损失**********************************/	
	/**
	* 三者损失       
	**/
	public Double getCstThirdLoss(){
		return this.cstThirdLoss;
	}
	/**
	* 获取三者损失格式化(toString)
	**/
	public String getCstThirdLoss$(){
		String strValue="";
		 strValue=$.str(this.getCstThirdLoss());	
	 	 return strValue;
	}
	/**
	* 三者损失       
	**/
	public void setCstThirdLoss(Double cstThirdLoss){
		this.cstThirdLoss = cstThirdLoss;
		this.setSeted(F.cstThirdLoss);
	}
	/*******************************拖车费用**********************************/	
	/**
	* 拖车费用       
	**/
	public Double getCstTrailFee(){
		return this.cstTrailFee;
	}
	/**
	* 获取拖车费用格式化(toString)
	**/
	public String getCstTrailFee$(){
		String strValue="";
		 strValue=$.str(this.getCstTrailFee());	
	 	 return strValue;
	}
	/**
	* 拖车费用       
	**/
	public void setCstTrailFee(Double cstTrailFee){
		this.cstTrailFee = cstTrailFee;
		this.setSeted(F.cstTrailFee);
	}
	/*******************************返还垫付时间**********************************/	
	/**
	* 返还垫付时间     返还给垫付人垫付款的时间  
	**/
	public Date getCstRepayDate(){
		return this.cstRepayDate;
	}
	/**
	* 获取返还垫付时间格式化(toString)
	**/
	public String getCstRepayDate$(){
		String strValue="";
		 strValue=$.str($.date(this.getCstRepayDate(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 返还垫付时间     返还给垫付人垫付款的时间  
	**/
	public void setCstRepayDate(Date cstRepayDate){
		this.cstRepayDate = cstRepayDate;
		this.setSeted(F.cstRepayDate);
	}
	/*******************************返还垫付方式**********************************/	
	/**
	* 返还垫付方式    0:银行转帐 1:返还保证金 2:返还现金    
	**/
	public Short getCstRepayType(){
		return this.cstRepayType;
	}
	/**
	* 获取返还垫付方式格式化(toString)
	**/
	public String getCstRepayType$(){
		String strValue="";
		 if($.equals($.str(this.getCstRepayType()),"0"))
			strValue=$.str("银行转帐");		 
		 if($.equals($.str(this.getCstRepayType()),"1"))
			strValue=$.str("返还保证金");		 
		 if($.equals($.str(this.getCstRepayType()),"2"))
			strValue=$.str("返还现金");		 
	 	 return strValue;
	}
	/**
	* 返还垫付方式    0:银行转帐 1:返还保证金 2:返还现金    
	**/
	public void setCstRepayType(Short cstRepayType){
		this.cstRepayType = cstRepayType;
		this.setSeted(F.cstRepayType);
	}
	/*******************************三者垫付备注**********************************/	
	/**
	* 三者垫付备注     三者损失、垫付等其它备注信息  
	**/
	public String getCstThirdDesc(){
		return this.cstThirdDesc;
	}
	/**
	* 获取三者垫付备注格式化(toString)
	**/
	public String getCstThirdDesc$(){
		String strValue="";
		 strValue=$.str(this.getCstThirdDesc());
	 	 return strValue;
	}
	/**
	* 三者垫付备注     三者损失、垫付等其它备注信息  
	**/
	public void setCstThirdDesc(String cstThirdDesc){
		this.cstThirdDesc = cstThirdDesc;
		this.setSeted(F.cstThirdDesc);
	}
	/*******************************赔付到账**********************************/	
	/**
	* 赔付到账       
	**/
	public Double getCstCompensate(){
		return this.cstCompensate;
	}
	/**
	* 获取赔付到账格式化(toString)
	**/
	public String getCstCompensate$(){
		String strValue="";
		 strValue=$.str(this.getCstCompensate());	
	 	 return strValue;
	}
	/**
	* 赔付到账       
	**/
	public void setCstCompensate(Double cstCompensate){
		this.cstCompensate = cstCompensate;
		this.setSeted(F.cstCompensate);
	}
	/*******************************赔付到账时间**********************************/	
	/**
	* 赔付到账时间       
	**/
	public Date getCstCompDate(){
		return this.cstCompDate;
	}
	/**
	* 获取赔付到账时间格式化(toString)
	**/
	public String getCstCompDate$(){
		String strValue="";
		 strValue=$.str($.date(this.getCstCompDate(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 赔付到账时间       
	**/
	public void setCstCompDate(Date cstCompDate){
		this.cstCompDate = cstCompDate;
		this.setSeted(F.cstCompDate);
	}
	/*******************************维修费用**********************************/	
	/**
	* 维修费用       
	**/
	public Double getCstRepairPay(){
		return this.cstRepairPay;
	}
	/**
	* 获取维修费用格式化(toString)
	**/
	public String getCstRepairPay$(){
		String strValue="";
		 strValue=$.str(this.getCstRepairPay());	
	 	 return strValue;
	}
	/**
	* 维修费用       
	**/
	public void setCstRepairPay(Double cstRepairPay){
		this.cstRepairPay = cstRepairPay;
		this.setSeted(F.cstRepairPay);
	}
	/*******************************合计差价**********************************/	
	/**
	* 合计差价 [非空]      
	**/
	public Double getCstAgio(){
		return this.cstAgio;
	}
	/**
	* 获取合计差价格式化(toString)
	**/
	public String getCstAgio$(){
		String strValue="";
		 strValue=$.str(this.getCstAgio());	
	 	 return strValue;
	}
	/**
	* 合计差价 [非空]      
	**/
	public void setCstAgio(Double cstAgio){
		this.cstAgio = cstAgio;
		this.setSeted(F.cstAgio);
	}
	/*******************************事故描述**********************************/	
	/**
	* 事故描述       
	**/
	public String getCstDecipt(){
		return this.cstDecipt;
	}
	/**
	* 获取事故描述格式化(toString)
	**/
	public String getCstDecipt$(){
		String strValue="";
		 strValue=$.str(this.getCstDecipt());
	 	 return strValue;
	}
	/**
	* 事故描述       
	**/
	public void setCstDecipt(String cstDecipt){
		this.cstDecipt = cstDecipt;
		this.setSeted(F.cstDecipt);
	}
	/*******************************图片列表**********************************/	
	/**
	* 图片列表       
	**/
	public String getCstImages(){
		return this.cstImages;
	}
	/**
	* 获取图片列表格式化(toString)
	**/
	public String getCstImages$(){
		String strValue="";
		 strValue=$.str(this.getCstImages());
	 	 return strValue;
	}
	/**
	* 图片列表       
	**/
	public void setCstImages(String cstImages){
		this.cstImages = cstImages;
		this.setSeted(F.cstImages);
	}
	/*******************************发生时间**********************************/	
	/**
	* 发生时间 [非空]      
	**/
	public Date getCstHappenTime(){
		return this.cstHappenTime;
	}
	/**
	* 获取发生时间格式化(toString)
	**/
	public String getCstHappenTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCstHappenTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 发生时间 [非空]      
	**/
	public void setCstHappenTime(Date cstHappenTime){
		this.cstHappenTime = cstHappenTime;
		this.setSeted(F.cstHappenTime);
	}
	/*******************************客服通知**********************************/	
	/**
	* 客服通知 [非空]   0:未通知 1:已通知    
	**/
	public Short getCstNotice(){
		return this.cstNotice;
	}
	/**
	* 获取客服通知格式化(toString)
	**/
	public String getCstNotice$(){
		String strValue="";
		 if($.equals($.str(this.getCstNotice()),"0"))
			strValue=$.str("未通知");		 
		 if($.equals($.str(this.getCstNotice()),"1"))
			strValue=$.str("已通知");		 
	 	 return strValue;
	}
	/**
	* 客服通知 [非空]   0:未通知 1:已通知    
	**/
	public void setCstNotice(Short cstNotice){
		this.cstNotice = cstNotice;
		this.setSeted(F.cstNotice);
	}
	/*******************************受理标记**********************************/	
	/**
	* 受理标记    0:默认 1:配合 2:不配合    
	**/
	public Short getCstDealFlag(){
		return this.cstDealFlag;
	}
	/**
	* 获取受理标记格式化(toString)
	**/
	public String getCstDealFlag$(){
		String strValue="";
		 if($.equals($.str(this.getCstDealFlag()),"0"))
			strValue=$.str("默认");		 
		 if($.equals($.str(this.getCstDealFlag()),"1"))
			strValue=$.str("配合");		 
		 if($.equals($.str(this.getCstDealFlag()),"2"))
			strValue=$.str("不配合");		 
	 	 return strValue;
	}
	/**
	* 受理标记    0:默认 1:配合 2:不配合    
	**/
	public void setCstDealFlag(Short cstDealFlag){
		this.cstDealFlag = cstDealFlag;
		this.setSeted(F.cstDealFlag);
	}
	/*******************************受理人员**********************************/	
	/**
	* 受理人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCstAccepter(){
		return this.cstAccepter;
	}
	/**
	* 获取受理人员格式化(toString)
	**/
	public String getCstAccepter$(){
		String strValue="";
		if(this.getCstAccepter()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCstAccepter()));
		}			
	 	 return strValue;
	}
	/**
	* 受理人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCstAccepter(Long cstAccepter){
		this.cstAccepter = cstAccepter;
		this.setSeted(F.cstAccepter);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cstAccepter(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCstAccepter());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCstAccepter()!=null){
 			srvUser = SrvUser.get(this.getCstAccepter());
 		}
 		$.SetRequest("SrvUser$"+this.getCstAccepter(), srvUser);
	 	return srvUser;
	}
	/*******************************结案人员**********************************/	
	/**
	* 结案人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCstCaser(){
		return this.cstCaser;
	}
	/**
	* 获取结案人员格式化(toString)
	**/
	public String getCstCaser$(){
		String strValue="";
		if(this.getCstCaser()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCstCaser()));
		}			
	 	 return strValue;
	}
	/**
	* 结案人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCstCaser(Long cstCaser){
		this.cstCaser = cstCaser;
		this.setSeted(F.cstCaser);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cstCaser(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCstCaser());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCstCaser()!=null){
 			srvUser = SrvUser.get(this.getCstCaser());
 		}
 		$.SetRequest("SrvUser$"+this.getCstCaser(), srvUser);
	 	return srvUser;
	}
	/*******************************结案时间**********************************/	
	/**
	* 结案时间       
	**/
	public Date getCstFinishTime(){
		return this.cstFinishTime;
	}
	/**
	* 获取结案时间格式化(toString)
	**/
	public String getCstFinishTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCstFinishTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 结案时间       
	**/
	public void setCstFinishTime(Date cstFinishTime){
		this.cstFinishTime = cstFinishTime;
		this.setSeted(F.cstFinishTime);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息       
	**/
	public String getCstRemark(){
		return this.cstRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCstRemark$(){
		String strValue="";
		 strValue=$.str(this.getCstRemark());
	 	 return strValue;
	}
	/**
	* 备注信息       
	**/
	public void setCstRemark(String cstRemark){
		this.cstRemark = cstRemark;
		this.setSeted(F.cstRemark);
	}
	/*******************************更新时间**********************************/	
	/**
	* 更新时间       
	**/
	public Date getCstUpdateTime(){
		return this.cstUpdateTime;
	}
	/**
	* 获取更新时间格式化(toString)
	**/
	public String getCstUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCstUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 更新时间       
	**/
	public void setCstUpdateTime(Date cstUpdateTime){
		this.cstUpdateTime = cstUpdateTime;
		this.setSeted(F.cstUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCstAddTime(){
		return this.cstAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCstAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCstAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCstAddTime(Date cstAddTime){
		this.cstAddTime = cstAddTime;
		this.setSeted(F.cstAddTime);
	}
	/*******************************处理状态**********************************/	
	/**
	* 处理状态 [非空]   0:已录入待定损 1:已定损待协商 2:已协商待维修 3:正在维修 4:已维修待扣款 5:已扣款待结案 6:已结案    
	**/
	public Short getCstStatus(){
		return this.cstStatus;
	}
	/**
	* 获取处理状态格式化(toString)
	**/
	public String getCstStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCstStatus()),"0"))
			strValue=$.str("已录入待定损");		 
		 if($.equals($.str(this.getCstStatus()),"1"))
			strValue=$.str("已定损待协商");		 
		 if($.equals($.str(this.getCstStatus()),"2"))
			strValue=$.str("已协商待维修");		 
		 if($.equals($.str(this.getCstStatus()),"3"))
			strValue=$.str("正在维修");		 
		 if($.equals($.str(this.getCstStatus()),"4"))
			strValue=$.str("已维修待扣款");		 
		 if($.equals($.str(this.getCstStatus()),"5"))
			strValue=$.str("已扣款待结案");		 
		 if($.equals($.str(this.getCstStatus()),"6"))
			strValue=$.str("已结案");		 
	 	 return strValue;
	}
	/**
	* 处理状态 [非空]   0:已录入待定损 1:已定损待协商 2:已协商待维修 3:正在维修 4:已维修待扣款 5:已扣款待结案 6:已结案    
	**/
	public void setCstStatus(Short cstStatus){
		this.cstStatus = cstStatus;
		this.setSeted(F.cstStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsTrouble.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsTrouble.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsTrouble.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsTrouble.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsTrouble.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsTrouble.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsTrouble.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsTrouble.this);
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
		public M cstId(Object cstId){this.put("cstId", cstId);return this;};
	 	/** and cst_id is null */
 		public M cstIdNull(){if(this.get("cstIdNot")==null)this.put("cstIdNot", "");this.put("cstId", null);return this;};
 		/** not .... */
 		public M cstIdNot(){this.put("cstIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M cstHost(Object cstHost){this.put("cstHost", cstHost);return this;};
	 	/** and cst_host is null */
 		public M cstHostNull(){if(this.get("cstHostNot")==null)this.put("cstHostNot", "");this.put("cstHost", null);return this;};
 		/** not .... */
 		public M cstHostNot(){this.put("cstHostNot", "not");return this;};
		/** 事故名称 [非空]       **/
		public M cstTitle(Object cstTitle){this.put("cstTitle", cstTitle);return this;};
	 	/** and cst_title is null */
 		public M cstTitleNull(){if(this.get("cstTitleNot")==null)this.put("cstTitleNot", "");this.put("cstTitle", null);return this;};
 		/** not .... */
 		public M cstTitleNot(){this.put("cstTitleNot", "not");return this;};
		/** 事故订单 [非空] [CsOrder]      **/
		public M cstOrder(Object cstOrder){this.put("cstOrder", cstOrder);return this;};
	 	/** and cst_order is null */
 		public M cstOrderNull(){if(this.get("cstOrderNot")==null)this.put("cstOrderNot", "");this.put("cstOrder", null);return this;};
 		/** not .... */
 		public M cstOrderNot(){this.put("cstOrderNot", "not");return this;};
		public M cstOrder$on(CsOrder.M value){
			this.put("CsOrder", value);
			this.put("cstOrder$on", value);
			return this;
		};	
		/** 事故会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public M cstMember(Object cstMember){this.put("cstMember", cstMember);return this;};
	 	/** and cst_member is null */
 		public M cstMemberNull(){if(this.get("cstMemberNot")==null)this.put("cstMemberNot", "");this.put("cstMember", null);return this;};
 		/** not .... */
 		public M cstMemberNot(){this.put("cstMemberNot", "not");return this;};
		public M cstMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("cstMember$on", value);
			return this;
		};	
		/** 事故车辆 [非空] [CsCar]      **/
		public M cstCar(Object cstCar){this.put("cstCar", cstCar);return this;};
	 	/** and cst_car is null */
 		public M cstCarNull(){if(this.get("cstCarNot")==null)this.put("cstCarNot", "");this.put("cstCar", null);return this;};
 		/** not .... */
 		public M cstCarNot(){this.put("cstCarNot", "not");return this;};
		public M cstCar$on(CsCar.M value){
			this.put("CsCar", value);
			this.put("cstCar$on", value);
			return this;
		};	
		/** 事故类型 [非空]   0:简易事故 1:单方事故 2:我方全责 3:对方全责 4:同等责任     **/
		public M cstType(Object cstType){this.put("cstType", cstType);return this;};
	 	/** and cst_type is null */
 		public M cstTypeNull(){if(this.get("cstTypeNot")==null)this.put("cstTypeNot", "");this.put("cstType", null);return this;};
 		/** not .... */
 		public M cstTypeNot(){this.put("cstTypeNot", "not");return this;};
		/** 车损情况        **/
		public M cstDamage(Object cstDamage){this.put("cstDamage", cstDamage);return this;};
	 	/** and cst_damage is null */
 		public M cstDamageNull(){if(this.get("cstDamageNot")==null)this.put("cstDamageNot", "");this.put("cstDamage", null);return this;};
 		/** not .... */
 		public M cstDamageNot(){this.put("cstDamageNot", "not");return this;};
		/** 送修地点  [CsRepairAdr]      **/
		public M cstRepairAdr(Object cstRepairAdr){this.put("cstRepairAdr", cstRepairAdr);return this;};
	 	/** and cst_repair_adr is null */
 		public M cstRepairAdrNull(){if(this.get("cstRepairAdrNot")==null)this.put("cstRepairAdrNot", "");this.put("cstRepairAdr", null);return this;};
 		/** not .... */
 		public M cstRepairAdrNot(){this.put("cstRepairAdrNot", "not");return this;};
		public M cstRepairAdr$on(CsRepairAdr.M value){
			this.put("CsRepairAdr", value);
			this.put("cstRepairAdr$on", value);
			return this;
		};	
		/** 维修部位        **/
		public M cstRepairPart(Object cstRepairPart){this.put("cstRepairPart", cstRepairPart);return this;};
	 	/** and cst_repair_part is null */
 		public M cstRepairPartNull(){if(this.get("cstRepairPartNot")==null)this.put("cstRepairPartNot", "");this.put("cstRepairPart", null);return this;};
 		/** not .... */
 		public M cstRepairPartNot(){this.put("cstRepairPartNot", "not");return this;};
		/** 维修详细信息  [CsRepair]      **/
		public M cstRepair(Object cstRepair){this.put("cstRepair", cstRepair);return this;};
	 	/** and cst_repair is null */
 		public M cstRepairNull(){if(this.get("cstRepairNot")==null)this.put("cstRepairNot", "");this.put("cstRepair", null);return this;};
 		/** not .... */
 		public M cstRepairNot(){this.put("cstRepairNot", "not");return this;};
		public M cstRepair$on(CsRepair.M value){
			this.put("CsRepair", value);
			this.put("cstRepair$on", value);
			return this;
		};	
		/** 开票金额        **/
		public M cstInvoice(Object cstInvoice){this.put("cstInvoice", cstInvoice);return this;};
	 	/** and cst_invoice is null */
 		public M cstInvoiceNull(){if(this.get("cstInvoiceNot")==null)this.put("cstInvoiceNot", "");this.put("cstInvoice", null);return this;};
 		/** not .... */
 		public M cstInvoiceNot(){this.put("cstInvoiceNot", "not");return this;};
		/** and cst_invoice >= ? */
		public M cstInvoiceMin(Object min){this.put("cstInvoiceMin", min);return this;};
		/** and cst_invoice <= ? */
		public M cstInvoiceMax(Object max){this.put("cstInvoiceMax", max);return this;};
		/** 保险定损        **/
		public M cstInsureFee(Object cstInsureFee){this.put("cstInsureFee", cstInsureFee);return this;};
	 	/** and cst_insure_fee is null */
 		public M cstInsureFeeNull(){if(this.get("cstInsureFeeNot")==null)this.put("cstInsureFeeNot", "");this.put("cstInsureFee", null);return this;};
 		/** not .... */
 		public M cstInsureFeeNot(){this.put("cstInsureFeeNot", "not");return this;};
		/** and cst_insure_fee >= ? */
		public M cstInsureFeeMin(Object min){this.put("cstInsureFeeMin", min);return this;};
		/** and cst_insure_fee <= ? */
		public M cstInsureFeeMax(Object max){this.put("cstInsureFeeMax", max);return this;};
		/** 协商扣款 [非空]    将从会员保证金里扣除的钱   **/
		public M cstMoney(Object cstMoney){this.put("cstMoney", cstMoney);return this;};
	 	/** and cst_money is null */
 		public M cstMoneyNull(){if(this.get("cstMoneyNot")==null)this.put("cstMoneyNot", "");this.put("cstMoney", null);return this;};
 		/** not .... */
 		public M cstMoneyNot(){this.put("cstMoneyNot", "not");return this;};
		/** and cst_money >= ? */
		public M cstMoneyMin(Object min){this.put("cstMoneyMin", min);return this;};
		/** and cst_money <= ? */
		public M cstMoneyMax(Object max){this.put("cstMoneyMax", max);return this;};
		/** 三者损失        **/
		public M cstThirdLoss(Object cstThirdLoss){this.put("cstThirdLoss", cstThirdLoss);return this;};
	 	/** and cst_third_loss is null */
 		public M cstThirdLossNull(){if(this.get("cstThirdLossNot")==null)this.put("cstThirdLossNot", "");this.put("cstThirdLoss", null);return this;};
 		/** not .... */
 		public M cstThirdLossNot(){this.put("cstThirdLossNot", "not");return this;};
		/** and cst_third_loss >= ? */
		public M cstThirdLossMin(Object min){this.put("cstThirdLossMin", min);return this;};
		/** and cst_third_loss <= ? */
		public M cstThirdLossMax(Object max){this.put("cstThirdLossMax", max);return this;};
		/** 拖车费用        **/
		public M cstTrailFee(Object cstTrailFee){this.put("cstTrailFee", cstTrailFee);return this;};
	 	/** and cst_trail_fee is null */
 		public M cstTrailFeeNull(){if(this.get("cstTrailFeeNot")==null)this.put("cstTrailFeeNot", "");this.put("cstTrailFee", null);return this;};
 		/** not .... */
 		public M cstTrailFeeNot(){this.put("cstTrailFeeNot", "not");return this;};
		/** and cst_trail_fee >= ? */
		public M cstTrailFeeMin(Object min){this.put("cstTrailFeeMin", min);return this;};
		/** and cst_trail_fee <= ? */
		public M cstTrailFeeMax(Object max){this.put("cstTrailFeeMax", max);return this;};
		/** 返还垫付时间     返还给垫付人垫付款的时间   **/
		public M cstRepayDate(Object cstRepayDate){this.put("cstRepayDate", cstRepayDate);return this;};
	 	/** and cst_repay_date is null */
 		public M cstRepayDateNull(){if(this.get("cstRepayDateNot")==null)this.put("cstRepayDateNot", "");this.put("cstRepayDate", null);return this;};
 		/** not .... */
 		public M cstRepayDateNot(){this.put("cstRepayDateNot", "not");return this;};
 		/** and cst_repay_date >= ? */
 		public M cstRepayDateStart(Object start){this.put("cstRepayDateStart", start);return this;};			
 		/** and cst_repay_date <= ? */
 		public M cstRepayDateEnd(Object end){this.put("cstRepayDateEnd", end);return this;};
		/** 返还垫付方式    0:银行转帐 1:返还保证金 2:返还现金     **/
		public M cstRepayType(Object cstRepayType){this.put("cstRepayType", cstRepayType);return this;};
	 	/** and cst_repay_type is null */
 		public M cstRepayTypeNull(){if(this.get("cstRepayTypeNot")==null)this.put("cstRepayTypeNot", "");this.put("cstRepayType", null);return this;};
 		/** not .... */
 		public M cstRepayTypeNot(){this.put("cstRepayTypeNot", "not");return this;};
		/** 三者垫付备注     三者损失、垫付等其它备注信息   **/
		public M cstThirdDesc(Object cstThirdDesc){this.put("cstThirdDesc", cstThirdDesc);return this;};
	 	/** and cst_third_desc is null */
 		public M cstThirdDescNull(){if(this.get("cstThirdDescNot")==null)this.put("cstThirdDescNot", "");this.put("cstThirdDesc", null);return this;};
 		/** not .... */
 		public M cstThirdDescNot(){this.put("cstThirdDescNot", "not");return this;};
		/** 赔付到账        **/
		public M cstCompensate(Object cstCompensate){this.put("cstCompensate", cstCompensate);return this;};
	 	/** and cst_compensate is null */
 		public M cstCompensateNull(){if(this.get("cstCompensateNot")==null)this.put("cstCompensateNot", "");this.put("cstCompensate", null);return this;};
 		/** not .... */
 		public M cstCompensateNot(){this.put("cstCompensateNot", "not");return this;};
		/** and cst_compensate >= ? */
		public M cstCompensateMin(Object min){this.put("cstCompensateMin", min);return this;};
		/** and cst_compensate <= ? */
		public M cstCompensateMax(Object max){this.put("cstCompensateMax", max);return this;};
		/** 赔付到账时间        **/
		public M cstCompDate(Object cstCompDate){this.put("cstCompDate", cstCompDate);return this;};
	 	/** and cst_comp_date is null */
 		public M cstCompDateNull(){if(this.get("cstCompDateNot")==null)this.put("cstCompDateNot", "");this.put("cstCompDate", null);return this;};
 		/** not .... */
 		public M cstCompDateNot(){this.put("cstCompDateNot", "not");return this;};
 		/** and cst_comp_date >= ? */
 		public M cstCompDateStart(Object start){this.put("cstCompDateStart", start);return this;};			
 		/** and cst_comp_date <= ? */
 		public M cstCompDateEnd(Object end){this.put("cstCompDateEnd", end);return this;};
		/** 维修费用        **/
		public M cstRepairPay(Object cstRepairPay){this.put("cstRepairPay", cstRepairPay);return this;};
	 	/** and cst_repair_pay is null */
 		public M cstRepairPayNull(){if(this.get("cstRepairPayNot")==null)this.put("cstRepairPayNot", "");this.put("cstRepairPay", null);return this;};
 		/** not .... */
 		public M cstRepairPayNot(){this.put("cstRepairPayNot", "not");return this;};
		/** and cst_repair_pay >= ? */
		public M cstRepairPayMin(Object min){this.put("cstRepairPayMin", min);return this;};
		/** and cst_repair_pay <= ? */
		public M cstRepairPayMax(Object max){this.put("cstRepairPayMax", max);return this;};
		/** 合计差价 [非空]       **/
		public M cstAgio(Object cstAgio){this.put("cstAgio", cstAgio);return this;};
	 	/** and cst_agio is null */
 		public M cstAgioNull(){if(this.get("cstAgioNot")==null)this.put("cstAgioNot", "");this.put("cstAgio", null);return this;};
 		/** not .... */
 		public M cstAgioNot(){this.put("cstAgioNot", "not");return this;};
		/** and cst_agio >= ? */
		public M cstAgioMin(Object min){this.put("cstAgioMin", min);return this;};
		/** and cst_agio <= ? */
		public M cstAgioMax(Object max){this.put("cstAgioMax", max);return this;};
		/** 事故描述        **/
		public M cstDecipt(Object cstDecipt){this.put("cstDecipt", cstDecipt);return this;};
	 	/** and cst_decipt is null */
 		public M cstDeciptNull(){if(this.get("cstDeciptNot")==null)this.put("cstDeciptNot", "");this.put("cstDecipt", null);return this;};
 		/** not .... */
 		public M cstDeciptNot(){this.put("cstDeciptNot", "not");return this;};
		/** 图片列表        **/
		public M cstImages(Object cstImages){this.put("cstImages", cstImages);return this;};
	 	/** and cst_images is null */
 		public M cstImagesNull(){if(this.get("cstImagesNot")==null)this.put("cstImagesNot", "");this.put("cstImages", null);return this;};
 		/** not .... */
 		public M cstImagesNot(){this.put("cstImagesNot", "not");return this;};
		/** 发生时间 [非空]       **/
		public M cstHappenTime(Object cstHappenTime){this.put("cstHappenTime", cstHappenTime);return this;};
	 	/** and cst_happen_time is null */
 		public M cstHappenTimeNull(){if(this.get("cstHappenTimeNot")==null)this.put("cstHappenTimeNot", "");this.put("cstHappenTime", null);return this;};
 		/** not .... */
 		public M cstHappenTimeNot(){this.put("cstHappenTimeNot", "not");return this;};
 		/** and cst_happen_time >= ? */
 		public M cstHappenTimeStart(Object start){this.put("cstHappenTimeStart", start);return this;};			
 		/** and cst_happen_time <= ? */
 		public M cstHappenTimeEnd(Object end){this.put("cstHappenTimeEnd", end);return this;};
		/** 客服通知 [非空]   0:未通知 1:已通知     **/
		public M cstNotice(Object cstNotice){this.put("cstNotice", cstNotice);return this;};
	 	/** and cst_notice is null */
 		public M cstNoticeNull(){if(this.get("cstNoticeNot")==null)this.put("cstNoticeNot", "");this.put("cstNotice", null);return this;};
 		/** not .... */
 		public M cstNoticeNot(){this.put("cstNoticeNot", "not");return this;};
		/** 受理标记    0:默认 1:配合 2:不配合     **/
		public M cstDealFlag(Object cstDealFlag){this.put("cstDealFlag", cstDealFlag);return this;};
	 	/** and cst_deal_flag is null */
 		public M cstDealFlagNull(){if(this.get("cstDealFlagNot")==null)this.put("cstDealFlagNot", "");this.put("cstDealFlag", null);return this;};
 		/** not .... */
 		public M cstDealFlagNot(){this.put("cstDealFlagNot", "not");return this;};
		/** 受理人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cstAccepter(Object cstAccepter){this.put("cstAccepter", cstAccepter);return this;};
	 	/** and cst_accepter is null */
 		public M cstAccepterNull(){if(this.get("cstAccepterNot")==null)this.put("cstAccepterNot", "");this.put("cstAccepter", null);return this;};
 		/** not .... */
 		public M cstAccepterNot(){this.put("cstAccepterNot", "not");return this;};
		/** 结案人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cstCaser(Object cstCaser){this.put("cstCaser", cstCaser);return this;};
	 	/** and cst_caser is null */
 		public M cstCaserNull(){if(this.get("cstCaserNot")==null)this.put("cstCaserNot", "");this.put("cstCaser", null);return this;};
 		/** not .... */
 		public M cstCaserNot(){this.put("cstCaserNot", "not");return this;};
		/** 结案时间        **/
		public M cstFinishTime(Object cstFinishTime){this.put("cstFinishTime", cstFinishTime);return this;};
	 	/** and cst_finish_time is null */
 		public M cstFinishTimeNull(){if(this.get("cstFinishTimeNot")==null)this.put("cstFinishTimeNot", "");this.put("cstFinishTime", null);return this;};
 		/** not .... */
 		public M cstFinishTimeNot(){this.put("cstFinishTimeNot", "not");return this;};
 		/** and cst_finish_time >= ? */
 		public M cstFinishTimeStart(Object start){this.put("cstFinishTimeStart", start);return this;};			
 		/** and cst_finish_time <= ? */
 		public M cstFinishTimeEnd(Object end){this.put("cstFinishTimeEnd", end);return this;};
		/** 备注信息        **/
		public M cstRemark(Object cstRemark){this.put("cstRemark", cstRemark);return this;};
	 	/** and cst_remark is null */
 		public M cstRemarkNull(){if(this.get("cstRemarkNot")==null)this.put("cstRemarkNot", "");this.put("cstRemark", null);return this;};
 		/** not .... */
 		public M cstRemarkNot(){this.put("cstRemarkNot", "not");return this;};
		/** 更新时间        **/
		public M cstUpdateTime(Object cstUpdateTime){this.put("cstUpdateTime", cstUpdateTime);return this;};
	 	/** and cst_update_time is null */
 		public M cstUpdateTimeNull(){if(this.get("cstUpdateTimeNot")==null)this.put("cstUpdateTimeNot", "");this.put("cstUpdateTime", null);return this;};
 		/** not .... */
 		public M cstUpdateTimeNot(){this.put("cstUpdateTimeNot", "not");return this;};
 		/** and cst_update_time >= ? */
 		public M cstUpdateTimeStart(Object start){this.put("cstUpdateTimeStart", start);return this;};			
 		/** and cst_update_time <= ? */
 		public M cstUpdateTimeEnd(Object end){this.put("cstUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cstAddTime(Object cstAddTime){this.put("cstAddTime", cstAddTime);return this;};
	 	/** and cst_add_time is null */
 		public M cstAddTimeNull(){if(this.get("cstAddTimeNot")==null)this.put("cstAddTimeNot", "");this.put("cstAddTime", null);return this;};
 		/** not .... */
 		public M cstAddTimeNot(){this.put("cstAddTimeNot", "not");return this;};
 		/** and cst_add_time >= ? */
 		public M cstAddTimeStart(Object start){this.put("cstAddTimeStart", start);return this;};			
 		/** and cst_add_time <= ? */
 		public M cstAddTimeEnd(Object end){this.put("cstAddTimeEnd", end);return this;};
		/** 处理状态 [非空]   0:已录入待定损 1:已定损待协商 2:已协商待维修 3:正在维修 4:已维修待扣款 5:已扣款待结案 6:已结案     **/
		public M cstStatus(Object cstStatus){this.put("cstStatus", cstStatus);return this;};
	 	/** and cst_status is null */
 		public M cstStatusNull(){if(this.get("cstStatusNot")==null)this.put("cstStatusNot", "");this.put("cstStatus", null);return this;};
 		/** not .... */
 		public M cstStatusNot(){this.put("cstStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有车辆事故 **/
		public @api List<CsTrouble> list(Integer size){
			return getCsTroubleList(this,size);
		}
		/** 获取车辆事故分页 **/
		public @api Page<CsTrouble> page(int page,int size){
			return getCsTroublePage(page, size , this);
		}
		/** 根据查询条件取车辆事故 **/
		public @api CsTrouble get(){
			return getCsTrouble(this);
		}
		/** 获取车辆事故数 **/
		public @api Long count(){
			return getCsTroubleCount(this);
		}
		/** 获取车辆事故表达式 **/
		public @api <T> T eval(String strEval){
			return getCsTroubleEval(strEval,this);
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
			updateCsTrouble(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cstId="cstId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cstHost="cstHost";
		/** 事故名称 [非空]       **/
		public final static @type(String.class) @like String cstTitle="cstTitle";
		/** 事故订单 [非空] [CsOrder]      **/
		public final static @type(Long.class)  String cstOrder="cstOrder";
		/** 事故会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static @type(Long.class)  String cstMember="cstMember";
		/** 事故车辆 [非空] [CsCar]      **/
		public final static @type(Long.class)  String cstCar="cstCar";
		/** 事故类型 [非空]   0:简易事故 1:单方事故 2:我方全责 3:对方全责 4:同等责任     **/
		public final static @type(Short.class)  String cstType="cstType";
		/** 车损情况        **/
		public final static @type(String.class) @like String cstDamage="cstDamage";
		/** 送修地点  [CsRepairAdr]      **/
		public final static @type(Long.class)  String cstRepairAdr="cstRepairAdr";
		/** 维修部位        **/
		public final static @type(String.class) @like String cstRepairPart="cstRepairPart";
		/** 维修详细信息  [CsRepair]      **/
		public final static @type(Long.class)  String cstRepair="cstRepair";
		/** 开票金额        **/
		public final static @type(Double.class)  String cstInvoice="cstInvoice";
		/** and cst_invoice >= ? */
		public final static @type(Double.class) String cstInvoiceMin="cstInvoiceMin";
		/** and cst_invoice <= ? */
		public final static @type(Double.class) String cstInvoiceMax="cstInvoiceMax";
		/** 保险定损        **/
		public final static @type(Double.class)  String cstInsureFee="cstInsureFee";
		/** and cst_insure_fee >= ? */
		public final static @type(Double.class) String cstInsureFeeMin="cstInsureFeeMin";
		/** and cst_insure_fee <= ? */
		public final static @type(Double.class) String cstInsureFeeMax="cstInsureFeeMax";
		/** 协商扣款 [非空]    将从会员保证金里扣除的钱   **/
		public final static @type(Double.class)  String cstMoney="cstMoney";
		/** and cst_money >= ? */
		public final static @type(Double.class) String cstMoneyMin="cstMoneyMin";
		/** and cst_money <= ? */
		public final static @type(Double.class) String cstMoneyMax="cstMoneyMax";
		/** 三者损失        **/
		public final static @type(Double.class)  String cstThirdLoss="cstThirdLoss";
		/** and cst_third_loss >= ? */
		public final static @type(Double.class) String cstThirdLossMin="cstThirdLossMin";
		/** and cst_third_loss <= ? */
		public final static @type(Double.class) String cstThirdLossMax="cstThirdLossMax";
		/** 拖车费用        **/
		public final static @type(Double.class)  String cstTrailFee="cstTrailFee";
		/** and cst_trail_fee >= ? */
		public final static @type(Double.class) String cstTrailFeeMin="cstTrailFeeMin";
		/** and cst_trail_fee <= ? */
		public final static @type(Double.class) String cstTrailFeeMax="cstTrailFeeMax";
		/** 返还垫付时间     返还给垫付人垫付款的时间   **/
		public final static @type(Date.class)  String cstRepayDate="cstRepayDate";
	 	/** and cst_repay_date >= ? */
 		public final static @type(Date.class) String cstRepayDateStart="cstRepayDateStart";
 		/** and cst_repay_date <= ? */
 		public final static @type(Date.class) String cstRepayDateEnd="cstRepayDateEnd";
		/** 返还垫付方式    0:银行转帐 1:返还保证金 2:返还现金     **/
		public final static @type(Short.class)  String cstRepayType="cstRepayType";
		/** 三者垫付备注     三者损失、垫付等其它备注信息   **/
		public final static @type(String.class) @like String cstThirdDesc="cstThirdDesc";
		/** 赔付到账        **/
		public final static @type(Double.class)  String cstCompensate="cstCompensate";
		/** and cst_compensate >= ? */
		public final static @type(Double.class) String cstCompensateMin="cstCompensateMin";
		/** and cst_compensate <= ? */
		public final static @type(Double.class) String cstCompensateMax="cstCompensateMax";
		/** 赔付到账时间        **/
		public final static @type(Date.class)  String cstCompDate="cstCompDate";
	 	/** and cst_comp_date >= ? */
 		public final static @type(Date.class) String cstCompDateStart="cstCompDateStart";
 		/** and cst_comp_date <= ? */
 		public final static @type(Date.class) String cstCompDateEnd="cstCompDateEnd";
		/** 维修费用        **/
		public final static @type(Double.class)  String cstRepairPay="cstRepairPay";
		/** and cst_repair_pay >= ? */
		public final static @type(Double.class) String cstRepairPayMin="cstRepairPayMin";
		/** and cst_repair_pay <= ? */
		public final static @type(Double.class) String cstRepairPayMax="cstRepairPayMax";
		/** 合计差价 [非空]       **/
		public final static @type(Double.class)  String cstAgio="cstAgio";
		/** and cst_agio >= ? */
		public final static @type(Double.class) String cstAgioMin="cstAgioMin";
		/** and cst_agio <= ? */
		public final static @type(Double.class) String cstAgioMax="cstAgioMax";
		/** 事故描述        **/
		public final static @type(String.class) @like String cstDecipt="cstDecipt";
		/** 图片列表        **/
		public final static @type(String.class)  String cstImages="cstImages";
		/** 发生时间 [非空]       **/
		public final static @type(Date.class)  String cstHappenTime="cstHappenTime";
	 	/** and cst_happen_time >= ? */
 		public final static @type(Date.class) String cstHappenTimeStart="cstHappenTimeStart";
 		/** and cst_happen_time <= ? */
 		public final static @type(Date.class) String cstHappenTimeEnd="cstHappenTimeEnd";
		/** 客服通知 [非空]   0:未通知 1:已通知     **/
		public final static @type(Short.class)  String cstNotice="cstNotice";
		/** 受理标记    0:默认 1:配合 2:不配合     **/
		public final static @type(Short.class)  String cstDealFlag="cstDealFlag";
		/** 受理人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String cstAccepter="cstAccepter";
		/** 结案人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String cstCaser="cstCaser";
		/** 结案时间        **/
		public final static @type(Date.class)  String cstFinishTime="cstFinishTime";
	 	/** and cst_finish_time >= ? */
 		public final static @type(Date.class) String cstFinishTimeStart="cstFinishTimeStart";
 		/** and cst_finish_time <= ? */
 		public final static @type(Date.class) String cstFinishTimeEnd="cstFinishTimeEnd";
		/** 备注信息        **/
		public final static @type(String.class) @like String cstRemark="cstRemark";
		/** 更新时间        **/
		public final static @type(Date.class)  String cstUpdateTime="cstUpdateTime";
	 	/** and cst_update_time >= ? */
 		public final static @type(Date.class) String cstUpdateTimeStart="cstUpdateTimeStart";
 		/** and cst_update_time <= ? */
 		public final static @type(Date.class) String cstUpdateTimeEnd="cstUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cstAddTime="cstAddTime";
	 	/** and cst_add_time >= ? */
 		public final static @type(Date.class) String cstAddTimeStart="cstAddTimeStart";
 		/** and cst_add_time <= ? */
 		public final static @type(Date.class) String cstAddTimeEnd="cstAddTimeEnd";
		/** 处理状态 [非空]   0:已录入待定损 1:已定损待协商 2:已协商待维修 3:正在维修 4:已维修待扣款 5:已扣款待结案 6:已结案     **/
		public final static @type(Short.class)  String cstStatus="cstStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cstId="cst_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cstHost="cst_host";
		/** 事故名称 [非空]       **/
		public final static String cstTitle="cst_title";
		/** 事故订单 [非空] [CsOrder]      **/
		public final static String cstOrder="cst_order";
		/** 事故会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static String cstMember="cst_member";
		/** 事故车辆 [非空] [CsCar]      **/
		public final static String cstCar="cst_car";
		/** 事故类型 [非空]   0:简易事故 1:单方事故 2:我方全责 3:对方全责 4:同等责任     **/
		public final static String cstType="cst_type";
		/** 车损情况        **/
		public final static String cstDamage="cst_damage";
		/** 送修地点  [CsRepairAdr]      **/
		public final static String cstRepairAdr="cst_repair_adr";
		/** 维修部位        **/
		public final static String cstRepairPart="cst_repair_part";
		/** 维修详细信息  [CsRepair]      **/
		public final static String cstRepair="cst_repair";
		/** 开票金额        **/
		public final static String cstInvoice="cst_invoice";
		/** 保险定损        **/
		public final static String cstInsureFee="cst_insure_fee";
		/** 协商扣款 [非空]    将从会员保证金里扣除的钱   **/
		public final static String cstMoney="cst_money";
		/** 三者损失        **/
		public final static String cstThirdLoss="cst_third_loss";
		/** 拖车费用        **/
		public final static String cstTrailFee="cst_trail_fee";
		/** 返还垫付时间     返还给垫付人垫付款的时间   **/
		public final static String cstRepayDate="cst_repay_date";
		/** 返还垫付方式    0:银行转帐 1:返还保证金 2:返还现金     **/
		public final static String cstRepayType="cst_repay_type";
		/** 三者垫付备注     三者损失、垫付等其它备注信息   **/
		public final static String cstThirdDesc="cst_third_desc";
		/** 赔付到账        **/
		public final static String cstCompensate="cst_compensate";
		/** 赔付到账时间        **/
		public final static String cstCompDate="cst_comp_date";
		/** 维修费用        **/
		public final static String cstRepairPay="cst_repair_pay";
		/** 合计差价 [非空]       **/
		public final static String cstAgio="cst_agio";
		/** 事故描述        **/
		public final static String cstDecipt="cst_decipt";
		/** 图片列表        **/
		public final static String cstImages="cst_images";
		/** 发生时间 [非空]       **/
		public final static String cstHappenTime="cst_happen_time";
		/** 客服通知 [非空]   0:未通知 1:已通知     **/
		public final static String cstNotice="cst_notice";
		/** 受理标记    0:默认 1:配合 2:不配合     **/
		public final static String cstDealFlag="cst_deal_flag";
		/** 受理人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cstAccepter="cst_accepter";
		/** 结案人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cstCaser="cst_caser";
		/** 结案时间        **/
		public final static String cstFinishTime="cst_finish_time";
		/** 备注信息        **/
		public final static String cstRemark="cst_remark";
		/** 更新时间        **/
		public final static String cstUpdateTime="cst_update_time";
		/** 添加时间 [非空]       **/
		public final static String cstAddTime="cst_add_time";
		/** 处理状态 [非空]   0:已录入待定损 1:已定损待协商 2:已协商待维修 3:正在维修 4:已维修待扣款 5:已扣款待结案 6:已结案     **/
		public final static String cstStatus="cst_status";
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
				$.Set(name,CsTrouble.getCsTrouble(params));
			else
				$.Set(name,CsTrouble.getCsTroubleList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取车辆事故数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsTrouble) $.GetRequest("CsTrouble$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsTrouble.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsTrouble.getCsTrouble(params);
			else
				value = CsTrouble.getCsTroubleList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsTrouble.Get($.add(CsTrouble.F.cstId,param));
		else if(!$.empty(param.toString()))
			value = CsTrouble.get(Long.valueOf(param.toString()));
		$.SetRequest("CsTrouble$"+param.hashCode(), value);
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
	public void mergeSet(CsTrouble old){
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