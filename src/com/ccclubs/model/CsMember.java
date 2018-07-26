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
import com.ccclubs.constants.MemberRecStatus;
import com.ccclubs.dao.impl.MemCache;

@namespace("user/member")
public @caption("会员帐号") @table("cs_member") class CsMember implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csm_id")   @primary  @note("  ") Long csmId;// 主键 非空     
	private @caption("城市") @column("csm_host")    @relate("$csmHost") @RelateClass(SrvHost.class)  @note("  ") Long csmHost;// 非空     
 	private SrvHost $csmHost;//关联对象[运营城市]
	private @caption("用户名") @column("csm_username")    @note("  如果由第三方平台注册，不同类型加前缀") String csmUsername;// 非空  如果由第三方平台注册，不同类型加前缀   
	private @caption("帐号密码") @column("csm_password")  @hidden   @note("  ") String csmPassword;// 非空     
	private @caption("所属组织") @column("csm_group")    @relate("$csmGroup") @RelateClass(CsMemberGroup.class)  @note("  ") Long csmGroup;//     
 	private CsMemberGroup $csmGroup;//关联对象[会员组织]
	private @caption("余额") @column("csm_money")    @note("  ") Double csmMoney;// 非空     
	private @caption("现金券") @column("csm_coupon")    @note("  ") Double csmCoupon;// 非空     
	private @caption("积分") @column("csm_integral")    @note("  ") Double csmIntegral;// 非空     
	private @caption("成长值") @column("csm_grow")    @note("  ") Integer csmGrow;// 非空     
	private @caption("会员等级") @column("csm_grade")    @note("  ") Integer csmGrade;// 非空     
	private @caption("折扣") @column("csm_rebate")    @note("  0到1,比如0.75表示七五折") Double csmRebate;// 非空  0到1,比如0.75表示七五折   
	private @caption("计算营收") @column("csm_not_revenue")    @note(" 0:计入营收 1:不计营收  表示该帐号支付的订单是否为营收项") Short csmNotRevenue;// 0:计入营收 1:不计营收  表示该帐号支付的订单是否为营收项   
	//private @caption("订单记录") @RelateClass(CsOrder.class) List<CsOrder> csmOrders;//反向关联的系统订单列表
	//private @caption("充值记录") @RelateClass(CsAlipayRecord.class) List<CsAlipayRecord> csmAlipays;//反向关联的支付宝充值列表
	//private @caption("使用套餐") @RelateClass(CsUserPack.class) List<CsUserPack> csmPacks;//反向关联的用户套餐列表
	//private @caption("免费小时") @RelateClass(CsFreeHour.class) List<CsFreeHour> csmFreehours;//反向关联的免费小时列表
	//private @caption("红包") @RelateClass(CsCoin.class) List<CsCoin> csmCoins;//反向关联的红包列表
	//private @caption("消费记录") @RelateClass(CsRecord.class) List<CsRecord> csmRecords;//反向关联的消费记录列表
	//private @caption("信用账单") @RelateClass(CsCreditBill.class) List<CsCreditBill> csmBills;//反向关联的信用账单列表
	//private @caption("积分记录") @RelateClass(CsIntegralRecord.class) List<CsIntegralRecord> csmIntegrals;//反向关联的积分记录列表
	//private @caption("成长记录") @RelateClass(CsGrowRecord.class) List<CsGrowRecord> csmGrows;//反向关联的成长记录列表
	//private @caption("优惠记录") @RelateClass(CsUseRecord.class) List<CsUseRecord> csmUseRecord;//反向关联的惠免记录列表
	//private @caption("退款记录") @RelateClass(CsRefund.class) List<CsRefund> csmRefounds;//反向关联的会员退款列表
	//private @caption("违章记录") @RelateClass(CsViolat.class) List<CsViolat> csmViolats;//反向关联的车辆违章列表
	//private @caption("事故记录") @RelateClass(CsTrouble.class) List<CsTrouble> csmTroubles;//反向关联的车辆事故列表
	//private @caption("发票记录") @RelateClass(CsInvoice.class) List<CsInvoice> csmInvoices;//反向关联的发票记录列表
	//private @caption("地址管理") @RelateClass(CsAddress.class) List<CsAddress> csmAddresses;//反向关联的会员地址列表
	//private @caption("备忘备注") @RelateClass(CsMemberMemo.class) List<CsMemberMemo> csmMemos;//反向关联的会员备忘列表
	private @caption("微信帐号标识") @column("csm_weixin_flag")    @note("  ") String csmWeixinFlag;//     
	private @caption("支付宝帐号标识") @column("csm_alipay_flag")    @note("  ") String csmAlipayFlag;//     
	private @caption("头像") @column("csm_header")  @hidden   @note(" 200:width 200:height  ") String csmHeader;// 200:width 200:height     
	private @caption("邮箱") @column("csm_email")    @note("  ") String csmEmail;//     
	private @caption("手机") @column("csm_mobile")    @note("  ") String csmMobile;//     
	private @caption("EV卡") @column("csm_evcard")    @relate("$csmEvcard") @RelateClass(CsEvCard.class)  @note("  ") Long csmEvcard;//     
 	private CsEvCard $csmEvcard;//关联对象[会员卡]
	private @caption("快递单号") @column("csm_express")    @note("  ") String csmExpress;//     
	private @caption("邀请码") @column("csm_temp")    @note("  ") String csmTemp;//     
	private @caption("真实姓名") @column("csm_name")    @note("  ") String csmName;//     
	private @caption("详细信息") @column("csm_info")    @relate("$csmInfo") @RelateClass(CsMemberInfo.class)  @note("  ") Long csmInfo;//     
 	private CsMemberInfo $csmInfo;//关联对象[会员信息]
	private @caption("默认网点") @column("csm_outlets")    @relate("$csmOutlets") @RelateClass(CsOutlets.class)  @note("  ") Long csmOutlets;//    ${basePath}${proname}/object/outlets_tree.do?parent={param} 
 	private CsOutlets $csmOutlets;//关联对象[网点]
	private @caption("VIP状态") @column("csm_is_vip")    @note(" 0:不是 1:是  ") Short csmIsVip;// 0:不是 1:是     
	private @caption("VIP开始时间") @column("csm_vip_start")    @note("  ") Date csmVipStart;//     
	private @caption("VIP结束时间") @column("csm_vip_end")    @note("  ") Date csmVipEnd;//     
	private @caption("更新时间") @column("csm_update_time")    @note("  ") Date csmUpdateTime;//     
	private @caption("注册时间") @column("csm_add_time")    @note("  ") Date csmAddTime;// 非空     
	private @caption("最后登录") @column("csm_last_time")    @note("  ") Date csmLastTime;//     
	private @caption("最后登录IP") @column("csm_last_ip")    @note("  ") String csmLastIp;//     
	private @caption("登录次数") @column("csm_login_s")    @note("  ") Integer csmLoginS;//     
	private @caption("首次用车") @column("csm_first_use")    @note("  ") Date csmFirstUse;//     
	private @caption("最后使用") @column("csm_last_use")    @note("  ") Date csmLastUse;//     
	private @caption("累计充值") @column("csm_all_recharge")    @note("  ") Double csmAllRecharge;//(单位：元)     
	private @caption("累计订单") @column("csm_all_order_s")    @note("  ") Long csmAllOrderS;//(单位：个)     
	private @caption("累计用车小时") @column("csm_all_use_time")    @note("  ") Double csmAllUseTime;//(单位：小时)     
	private @caption("违章次数") @column("csm_all_violat_s")    @note("  ") Long csmAllViolatS;//     
	private @caption("未处理违章") @column("csm_un_violat_s")    @note("  ") Long csmUnViolatS;//     
	private @caption("事故次数") @column("csm_all_trouble_s")    @note("  ") Long csmAllTroubleS;//     
	private @caption("首次充值") @column("csm_first_recharge")    @note("  ") Date csmFirstRecharge;//     
	private @caption("最后退款") @column("csm_refund_time")    @note("  ") Date csmRefundTime;//     
	private @caption("注册IP") @column("csm_regist_ip")    @note("  ") String csmRegistIp;//     
	private @caption("添加人员") @column("csm_adder")    @relate("$csmAdder") @RelateClass(SrvUser.class)  @note("  ") Long csmAdder;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csmAdder;//关联对象[用户]
	private @caption("跟踪人员") @column("csm_tracker")    @relate("$csmTracker") @RelateClass(SrvUser.class)  @note("  ") Long csmTracker;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csmTracker;//关联对象[用户]
	private @caption("推荐会员") @column("csm_refer")    @relate("$csmRefer") @RelateClass(CsMember.class)  @note("  ") Long csmRefer;//     
 	private CsMember $csmRefer;//关联对象[会员帐号]
	private @caption("推荐方式") @column("csm_refer_type")    @note(" 0:无 1:地址转发 2:微博分享 3:广告媒体 4:网络搜索 5:朋友推荐 6:活动推广  ") Short csmReferType;// 0:无 1:地址转发 2:微博分享 3:广告媒体 4:网络搜索 5:朋友推荐 6:活动推广     
	private @caption("注册平台") @column("csm_from")    @note(" 0:后台 1:网站 2:微信 3:IOS 4:ANROID 5:OFFICE") Short csmFrom;// 0:后台 1:网站 2:微信 3:APP 4:支付宝微生活     
	private @caption("注册源信息") @column("csm_src")    @note("  ") String csmSrc;//     存储格式:类名@ID,其中类名包括
	private Object $csmSrc;//泛关联对象，对象类名由csmSrc字段中标明
	private @caption("来源渠道") @column("csm_channel")    @relate("$csmChannel") @RelateClass(CsChannel.class)  @note("  ") Long csmChannel;//     
 	private CsChannel $csmChannel;//关联对象[访问渠道]
	private @caption("营销方案") @column("csm_market_plan")    @relate("$csmMarketPlan") @RelateClass(CsMarketPlan.class)  @note("  ") Long csmMarketPlan;//     
 	private CsMarketPlan $csmMarketPlan;//关联对象[营销方案]
	private @caption("来源活动") @column("csm_activity")    @relate("$csmActivity") @RelateClass(CsActivity.class)  @note("  ") Long csmActivity;//     
 	private CsActivity $csmActivity;//关联对象[营销活动]
	private @caption("销售人员") @column("csm_saler")    @relate("$csmSaler") @RelateClass(SrvUser.class)  @note("  ") Long csmSaler;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csmSaler;//关联对象[用户]
	private @caption("营销标记") @column("csm_market")    @relate("$csmMarket") @RelateClass(SrvProperty.class)  @note(" 营销标识:'MEMBER_MARKET'  ") String csmMarket;// 营销标识:'MEMBER_MARKET'    select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) 
 	private SrvProperty $csmMarket;//关联对象[系统属性]
	private @caption("会员标签") @column("csm_tag")    @relate("$csmTag") @RelateClass(SrvProperty.class)  @note(" 用户标签:'MEMBER_TAG'  (会员特性)标签用来对用户分类，在系统属性表中配置") String csmTag;// 用户标签:'MEMBER_TAG'  (会员特性)标签用来对用户分类，在系统属性表中配置  select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) 
 	private SrvProperty $csmTag;//关联对象[系统属性]
	private @caption("备忘标记") @column("csm_mark")    @relate("$csmMark") @RelateClass(SrvProperty.class)  @note(" 备忘标记:'MEMBER_MARK'  (管理标记)管理人员对该会员做的备忘标记，在系统属性表中配置") String csmMark;// 备忘标记:'MEMBER_MARK'  (管理标记)管理人员对该会员做的备忘标记，在系统属性表中配置  select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) 
 	private SrvProperty $csmMark;//关联对象[系统属性]
	private @caption("回访标记") @column("csm_visit_flag")    @relate("$csmVisitFlag") @RelateClass(CsQuestTemplate.class)  @note("  (回访标记)对应回访模板,勾上了表示该模板已调查或已回访") String csmVisitFlag;//  (回访标记)对应回访模板,勾上了表示该模板已调查或已回访  select * from cs_quest_template where 1=1 
 	private CsQuestTemplate $csmVisitFlag;//关联对象[问卷模板]
	private @caption("程序掩码") @column("csm_mask")    @note(" 1:已回流支付宝 2:保留2 4:保留4 8:保留8  (程序标记)技术人员使用的标记，按位运算") Long csmMask;// 1:已回流支付宝 2:保留2 4:保留4 8:保留8  (程序标记)技术人员使用的标记，按位运算   
	private @caption("备注") @column("csm_remark")    @note("  ") String csmRemark;//     
	private @caption("手机认证") @column("csm_v_mobile")    @note(" 0:未认证 1:已认证 2:等待认证 3:认证失败  ") Short csmVMobile;// 0:未认证 1:已认证 2:等待认证 3:认证失败     
	private @caption("邮箱认证") @column("csm_v_email")    @note(" 0:未认证 1:已认证 2:等待认证 3:认证失败  ") Short csmVEmail;// 0:未认证 1:已认证 2:等待认证 3:认证失败     
	private @caption("实名认证") @column("csm_v_real")    @note(" 0:未认证 1:已认证 2:等待认证 3:认证失败  ") Short csmVReal;// 0:未认证 1:已认证 2:等待认证 3:认证失败     
	private @caption("驾驶认证") @column("csm_v_drive")    @note(" 0:未认证 1:已认证 2:等待认证 3:认证失败  ") Short csmVDrive;// 0:未认证 1:已认证 2:等待认证 3:认证失败     
	private @caption("可用状态") @column("csm_status")    @note(" 1:正常 0:禁用  -1:黑名单  ") Short csmStatus;// 非空 0:禁用  -1:黑名单;1:正常;2:黑名单;     
	private @caption("工作认证") @column("csm_v_work")    @note(" 0:未认证 1:已认证 2:等待认证 3:认证失败  ") Short csmVWork;// 0:未认证 1:已认证 2:等待认证 3:认证失败
	private @caption("线下认证") @column("csm_v_offline")    @note(" 0:未认证 1:已认证 2:等待认证 3:认证失败  ") Short csmVOffline;// 0:未认证 1:已认证 2:等待认证 3:认证失败
	private @caption("禁用原因") @column("csm_lock_reason")    @note(" 禁用原因  ") String csmLockReason;// 禁用原因
	private @caption("线下认证机器编码") @column("csm_v_offline_code")    @note(" 线下认证机器编码  ") String csmVOfflineCode;// 线下认证机器编码
	private Short vstatus;

    //默认构造函数
	public CsMember(){
	
	}
	
	//主键构造函数
	public CsMember(Long id){
		this.csmId = id;
	}
	
	/**所有字段构造函数 CsMember(csmHost,csmUsername,csmPassword,csmGroup,csmMoney,csmCoupon,
csmIntegral,csmGrow,csmGrade,csmRebate,csmNotRevenue,csmWeixinFlag,csmAlipayFlag,csmHeader,
csmEmail,csmMobile,csmEvcard,csmExpress,csmTemp,csmName,csmInfo,csmOutlets,csmIsVip,csmVipStart,
csmVipEnd,csmUpdateTime,csmAddTime,csmLastTime,csmLastIp,csmLoginS,csmFirstUse,csmLastUse,
csmAllRecharge,csmAllOrderS,csmAllUseTime,csmAllViolatS,csmUnViolatS,csmAllTroubleS,
csmFirstRecharge,csmRefundTime,csmRegistIp,csmAdder,csmTracker,csmRefer,csmReferType,
csmFrom,csmSrc,csmChannel,csmMarketPlan,csmActivity,csmSaler,csmMarket,csmTag,csmMark,
csmVisitFlag,csmMask,csmRemark,csmVMobile,csmVEmail,csmVReal,csmVDrive,csmStatus)
	 CsMember(
	 	$.getLong("csmHost")//城市 [非空]
	 	,$.getString("csmUsername")//用户名 [非空]
	 	,$.getString("csmPassword")//帐号密码 [非空]
	 	,$.getLong("csmGroup")//所属组织
	 	,$.getDouble("csmMoney")//余额 [非空]
	 	,$.getDouble("csmCoupon")//现金券 [非空]
	 	,$.getDouble("csmIntegral")//积分 [非空]
	 	,$.getInteger("csmGrow")//成长值 [非空]
	 	,$.getInteger("csmGrade")//会员等级 [非空]
	 	,$.getDouble("csmRebate")//折扣 [非空]
	 	,$.getShort("csmNotRevenue")//计算营收
	 	,$.getString("csmWeixinFlag")//微信帐号标识
	 	,$.getString("csmAlipayFlag")//支付宝帐号标识
	 	,$.getString("csmHeader")//头像
	 	,$.getString("csmEmail")//邮箱
	 	,$.getString("csmMobile")//手机
	 	,$.getLong("csmEvcard")//EV卡
	 	,$.getString("csmExpress")//快递单号
	 	,$.getString("csmTemp")//邀请码
	 	,$.getString("csmName")//真实姓名
	 	,$.getLong("csmInfo")//详细信息
	 	,$.getLong("csmOutlets")//默认网点
	 	,$.getShort("csmIsVip")//VIP状态
	 	,$.getDate("csmVipStart")//VIP开始时间
	 	,$.getDate("csmVipEnd")//VIP结束时间
	 	,$.getDate("csmUpdateTime")//更新时间
	 	,$.getDate("csmAddTime")//注册时间 [非空]
	 	,$.getDate("csmLastTime")//最后登录
	 	,$.getString("csmLastIp")//最后登录IP
	 	,$.getInteger("csmLoginS")//登录次数
	 	,$.getDate("csmFirstUse")//首次用车
	 	,$.getDate("csmLastUse")//最后使用
	 	,$.getDouble("csmAllRecharge")//累计充值
	 	,$.getLong("csmAllOrderS")//累计订单
	 	,$.getDouble("csmAllUseTime")//累计用车小时
	 	,$.getLong("csmAllViolatS")//违章次数
	 	,$.getLong("csmUnViolatS")//未处理违章
	 	,$.getLong("csmAllTroubleS")//事故次数
	 	,$.getDate("csmFirstRecharge")//首次充值
	 	,$.getDate("csmRefundTime")//最后退款
	 	,$.getString("csmRegistIp")//注册IP
	 	,$.getLong("csmAdder")//添加人员
	 	,$.getLong("csmTracker")//跟踪人员
	 	,$.getLong("csmRefer")//推荐会员
	 	,$.getShort("csmReferType")//推荐方式
	 	,$.getShort("csmFrom")//注册平台
	 	,$.getString("csmSrc")//注册源信息
	 	,$.getLong("csmChannel")//来源渠道
	 	,$.getLong("csmMarketPlan")//营销方案
	 	,$.getLong("csmActivity")//来源活动
	 	,$.getLong("csmSaler")//销售人员
	 	,$.getString("csmMarket")//营销标记
	 	,$.getString("csmTag")//会员标签
	 	,$.getString("csmMark")//备忘标记
	 	,$.getString("csmVisitFlag")//回访标记
	 	,$.getLong("csmMask")//程序掩码
	 	,$.getString("csmRemark")//备注
	 	,$.getShort("csmVMobile")//手机认证
	 	,$.getShort("csmVEmail")//邮箱认证
	 	,$.getShort("csmVReal")//实名认证
	 	,$.getShort("csmVDrive")//驾驶认证
	 	,$.getShort("csmStatus")//可用状态 [非空]
	 	,$.getShort("csmVWork")//工作认证
	 	,$.getShort("csmVIdcard")//身份证认证
	 	,$.getShort("csmVOffline")//线下认证
	 )
	**/
	public CsMember(Long csmHost,String csmUsername,String csmPassword,Long csmGroup,
	        Double csmMoney,Double csmCoupon,Double csmIntegral,Integer csmGrow,
	        Integer csmGrade,Double csmRebate,Short csmNotRevenue,String csmWeixinFlag,
	        String csmAlipayFlag,String csmHeader,String csmEmail,String csmMobile,
	        Long csmEvcard,String csmExpress,String csmTemp,String csmName,Long csmInfo,
	        Long csmOutlets,Short csmIsVip,Date csmVipStart,Date csmVipEnd,Date csmUpdateTime,
	        Date csmAddTime,Date csmLastTime,String csmLastIp,Integer csmLoginS,Date csmFirstUse,
	        Date csmLastUse,Double csmAllRecharge,Long csmAllOrderS,Double csmAllUseTime,
	        Long csmAllViolatS,Long csmUnViolatS,Long csmAllTroubleS,Date csmFirstRecharge,
	        Date csmRefundTime,String csmRegistIp,Long csmAdder,Long csmTracker,Long csmRefer,
	        Short csmReferType,Short csmFrom,String csmSrc,Long csmChannel,Long csmMarketPlan,
	        Long csmActivity,Long csmSaler,String csmMarket,String csmTag,String csmMark,
	        String csmVisitFlag,Long csmMask,String csmRemark,Short csmVMobile,Short csmVEmail,
	        Short csmVReal,Short csmVDrive,Short csmStatus ,Short csmVWork,
	        Short csmVIdcard,Short csmVOffline){
		this.csmHost=csmHost;
		this.csmUsername=csmUsername;
		this.csmPassword=csmPassword;
		this.csmGroup=csmGroup;
		this.csmMoney=csmMoney;
		this.csmCoupon=csmCoupon;
		this.csmIntegral=csmIntegral;
		this.csmGrow=csmGrow;
		this.csmGrade=csmGrade;
		this.csmRebate=csmRebate;
		this.csmNotRevenue=csmNotRevenue;
		this.csmWeixinFlag=csmWeixinFlag;
		this.csmAlipayFlag=csmAlipayFlag;
		this.csmHeader=csmHeader;
		this.csmEmail=csmEmail;
		this.csmMobile=csmMobile;
		this.csmEvcard=csmEvcard;
		this.csmExpress=csmExpress;
		this.csmTemp=csmTemp;
		this.csmName=csmName;
		this.csmInfo=csmInfo;
		this.csmOutlets=csmOutlets;
		this.csmIsVip=csmIsVip;
		this.csmVipStart=csmVipStart;
		this.csmVipEnd=csmVipEnd;
		this.csmUpdateTime=csmUpdateTime;
		this.csmAddTime=csmAddTime;
		this.csmLastTime=csmLastTime;
		this.csmLastIp=csmLastIp;
		this.csmLoginS=csmLoginS;
		this.csmFirstUse=csmFirstUse;
		this.csmLastUse=csmLastUse;
		this.csmAllRecharge=csmAllRecharge;
		this.csmAllOrderS=csmAllOrderS;
		this.csmAllUseTime=csmAllUseTime;
		this.csmAllViolatS=csmAllViolatS;
		this.csmUnViolatS=csmUnViolatS;
		this.csmAllTroubleS=csmAllTroubleS;
		this.csmFirstRecharge=csmFirstRecharge;
		this.csmRefundTime=csmRefundTime;
		this.csmRegistIp=csmRegistIp;
		this.csmAdder=csmAdder;
		this.csmTracker=csmTracker;
		this.csmRefer=csmRefer;
		this.csmReferType=csmReferType;
		this.csmFrom=csmFrom;
		this.csmSrc=csmSrc;
		this.csmChannel=csmChannel;
		this.csmMarketPlan=csmMarketPlan;
		this.csmActivity=csmActivity;
		this.csmSaler=csmSaler;
		this.csmMarket=csmMarket;
		this.csmTag=csmTag;
		this.csmMark=csmMark;
		this.csmVisitFlag=csmVisitFlag;
		this.csmMask=csmMask;
		this.csmRemark=csmRemark;
		this.csmVMobile=csmVMobile;
		this.csmVEmail=csmVEmail;
		this.csmVReal=csmVReal;
		this.csmVDrive=csmVDrive;
		this.csmStatus=csmStatus;
		this.csmVOffline=csmVOffline;
		this.csmVWork=csmVWork;
	}
	
	//设置非空字段
	public CsMember setNotNull(Long csmHost,String csmUsername,String csmPassword,Double csmMoney,Double csmCoupon,Double csmIntegral,Integer csmGrow,Integer csmGrade,Double csmRebate,Date csmAddTime,Short csmStatus){
		this.csmHost=csmHost;
		this.csmUsername=csmUsername;
		this.csmPassword=csmPassword;
		this.csmMoney=csmMoney;
		this.csmCoupon=csmCoupon;
		this.csmIntegral=csmIntegral;
		this.csmGrow=csmGrow;
		this.csmGrade=csmGrade;
		this.csmRebate=csmRebate;
		this.csmAddTime=csmAddTime;
		this.csmStatus=csmStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsMember csmId(Long csmId){
		this.csmId = csmId;
		this.setSeted(F.csmId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsMember csmHost(Long csmHost){
		this.csmHost = csmHost;
		this.setSeted(F.csmHost);
		return this;
	}
	/** 用户名 [非空]    如果由第三方平台注册，不同类型加前缀   **/
	public CsMember csmUsername(String csmUsername){
		this.csmUsername = csmUsername;
		this.setSeted(F.csmUsername);
		return this;
	}
	/** 帐号密码 [非空]       **/
	public CsMember csmPassword(String csmPassword){
		this.csmPassword = csmPassword;
		this.setSeted(F.csmPassword);
		return this;
	}
	/** 所属组织  [CsMemberGroup]      **/
	public CsMember csmGroup(Long csmGroup){
		this.csmGroup = csmGroup;
		this.setSeted(F.csmGroup);
		return this;
	}
	/** 余额 [非空]       **/
	public CsMember csmMoney(Double csmMoney){
		this.csmMoney = csmMoney;
		this.setSeted(F.csmMoney);
		return this;
	}
	/** 现金券 [非空]       **/
	public CsMember csmCoupon(Double csmCoupon){
		this.csmCoupon = csmCoupon;
		this.setSeted(F.csmCoupon);
		return this;
	}
	/** 积分 [非空]       **/
	public CsMember csmIntegral(Double csmIntegral){
		this.csmIntegral = csmIntegral;
		this.setSeted(F.csmIntegral);
		return this;
	}
	/** 成长值 [非空]       **/
	public CsMember csmGrow(Integer csmGrow){
		this.csmGrow = csmGrow;
		this.setSeted(F.csmGrow);
		return this;
	}
	/** 会员等级 [非空]       **/
	public CsMember csmGrade(Integer csmGrade){
		this.csmGrade = csmGrade;
		this.setSeted(F.csmGrade);
		return this;
	}
	/** 折扣 [非空]    0到1,比如0.75表示七五折   **/
	public CsMember csmRebate(Double csmRebate){
		this.csmRebate = csmRebate;
		this.setSeted(F.csmRebate);
		return this;
	}
	/** 计算营收    0:计入营收 1:不计营收  表示该帐号支付的订单是否为营收项   **/
	public CsMember csmNotRevenue(Short csmNotRevenue){
		this.csmNotRevenue = csmNotRevenue;
		this.setSeted(F.csmNotRevenue);
		return this;
	}
	/** 微信帐号标识        **/
	public CsMember csmWeixinFlag(String csmWeixinFlag){
		this.csmWeixinFlag = csmWeixinFlag;
		this.setSeted(F.csmWeixinFlag);
		return this;
	}
	/** 支付宝帐号标识        **/
	public CsMember csmAlipayFlag(String csmAlipayFlag){
		this.csmAlipayFlag = csmAlipayFlag;
		this.setSeted(F.csmAlipayFlag);
		return this;
	}
	/** 头像    200:width 200:height     **/
	public CsMember csmHeader(String csmHeader){
		this.csmHeader = csmHeader;
		this.setSeted(F.csmHeader);
		return this;
	}
	/** 邮箱        **/
	public CsMember csmEmail(String csmEmail){
		this.csmEmail = csmEmail;
		this.setSeted(F.csmEmail);
		return this;
	}
	/** 手机        **/
	public CsMember csmMobile(String csmMobile){
		this.csmMobile = csmMobile;
		this.setSeted(F.csmMobile);
		return this;
	}
	/** EV卡  [CsEvCard]      **/
	public CsMember csmEvcard(Long csmEvcard){
		this.csmEvcard = csmEvcard;
		this.setSeted(F.csmEvcard);
		return this;
	}
	/** 快递单号        **/
	public CsMember csmExpress(String csmExpress){
		this.csmExpress = csmExpress;
		this.setSeted(F.csmExpress);
		return this;
	}
	/** 邀请码        **/
	public CsMember csmTemp(String csmTemp){
		this.csmTemp = csmTemp;
		this.setSeted(F.csmTemp);
		return this;
	}
	/** 真实姓名        **/
	public CsMember csmName(String csmName){
		this.csmName = csmName;
		this.setSeted(F.csmName);
		return this;
	}
	/** 详细信息  [CsMemberInfo]      **/
	public CsMember csmInfo(Long csmInfo){
		this.csmInfo = csmInfo;
		this.setSeted(F.csmInfo);
		return this;
	}
	/** 默认网点  [CsOutlets]     ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
	public CsMember csmOutlets(Long csmOutlets){
		this.csmOutlets = csmOutlets;
		this.setSeted(F.csmOutlets);
		return this;
	}
	/** VIP状态    0:不是 1:是     **/
	public CsMember csmIsVip(Short csmIsVip){
		this.csmIsVip = csmIsVip;
		this.setSeted(F.csmIsVip);
		return this;
	}
	/** VIP开始时间        **/
	public CsMember csmVipStart(Date csmVipStart){
		this.csmVipStart = csmVipStart;
		this.setSeted(F.csmVipStart);
		return this;
	}
	/** VIP结束时间        **/
	public CsMember csmVipEnd(Date csmVipEnd){
		this.csmVipEnd = csmVipEnd;
		this.setSeted(F.csmVipEnd);
		return this;
	}
	/** 更新时间        **/
	public CsMember csmUpdateTime(Date csmUpdateTime){
		this.csmUpdateTime = csmUpdateTime;
		this.setSeted(F.csmUpdateTime);
		return this;
	}
	/** 注册时间 [非空]       **/
	public CsMember csmAddTime(Date csmAddTime){
		this.csmAddTime = csmAddTime;
		this.setSeted(F.csmAddTime);
		return this;
	}
	/** 最后登录        **/
	public CsMember csmLastTime(Date csmLastTime){
		this.csmLastTime = csmLastTime;
		this.setSeted(F.csmLastTime);
		return this;
	}
	/** 最后登录IP        **/
	public CsMember csmLastIp(String csmLastIp){
		this.csmLastIp = csmLastIp;
		this.setSeted(F.csmLastIp);
		return this;
	}
	/** 登录次数        **/
	public CsMember csmLoginS(Integer csmLoginS){
		this.csmLoginS = csmLoginS;
		this.setSeted(F.csmLoginS);
		return this;
	}
	/** 首次用车        **/
	public CsMember csmFirstUse(Date csmFirstUse){
		this.csmFirstUse = csmFirstUse;
		this.setSeted(F.csmFirstUse);
		return this;
	}
	/** 最后使用        **/
	public CsMember csmLastUse(Date csmLastUse){
		this.csmLastUse = csmLastUse;
		this.setSeted(F.csmLastUse);
		return this;
	}
	/** 累计充值        **/
	public CsMember csmAllRecharge(Double csmAllRecharge){
		this.csmAllRecharge = csmAllRecharge;
		this.setSeted(F.csmAllRecharge);
		return this;
	}
	/** 累计订单        **/
	public CsMember csmAllOrderS(Long csmAllOrderS){
		this.csmAllOrderS = csmAllOrderS;
		this.setSeted(F.csmAllOrderS);
		return this;
	}
	/** 累计用车小时        **/
	public CsMember csmAllUseTime(Double csmAllUseTime){
		this.csmAllUseTime = csmAllUseTime;
		this.setSeted(F.csmAllUseTime);
		return this;
	}
	/** 违章次数        **/
	public CsMember csmAllViolatS(Long csmAllViolatS){
		this.csmAllViolatS = csmAllViolatS;
		this.setSeted(F.csmAllViolatS);
		return this;
	}
	/** 未处理违章        **/
	public CsMember csmUnViolatS(Long csmUnViolatS){
		this.csmUnViolatS = csmUnViolatS;
		this.setSeted(F.csmUnViolatS);
		return this;
	}
	/** 事故次数        **/
	public CsMember csmAllTroubleS(Long csmAllTroubleS){
		this.csmAllTroubleS = csmAllTroubleS;
		this.setSeted(F.csmAllTroubleS);
		return this;
	}
	/** 首次充值        **/
	public CsMember csmFirstRecharge(Date csmFirstRecharge){
		this.csmFirstRecharge = csmFirstRecharge;
		this.setSeted(F.csmFirstRecharge);
		return this;
	}
	/** 最后退款        **/
	public CsMember csmRefundTime(Date csmRefundTime){
		this.csmRefundTime = csmRefundTime;
		this.setSeted(F.csmRefundTime);
		return this;
	}
	/** 注册IP        **/
	public CsMember csmRegistIp(String csmRegistIp){
		this.csmRegistIp = csmRegistIp;
		this.setSeted(F.csmRegistIp);
		return this;
	}
	/** 添加人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsMember csmAdder(Long csmAdder){
		this.csmAdder = csmAdder;
		this.setSeted(F.csmAdder);
		return this;
	}
	/** 跟踪人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsMember csmTracker(Long csmTracker){
		this.csmTracker = csmTracker;
		this.setSeted(F.csmTracker);
		return this;
	}
	/** 推荐会员  [CsMember]      **/
	public CsMember csmRefer(Long csmRefer){
		this.csmRefer = csmRefer;
		this.setSeted(F.csmRefer);
		return this;
	}
	/** 推荐方式    0:无 1:地址转发 2:微博分享 3:广告媒体 4:网络搜索 5:朋友推荐 6:活动推广     **/
	public CsMember csmReferType(Short csmReferType){
		this.csmReferType = csmReferType;
		this.setSeted(F.csmReferType);
		return this;
	}
	/** 注册平台    0:后台 1:网站 2:微信 3:APP 4:支付宝微生活     **/
	public CsMember csmFrom(Short csmFrom){
		this.csmFrom = csmFrom;
		this.setSeted(F.csmFrom);
		return this;
	}
	/** 注册源信息        **/
	public CsMember csmSrc(String csmSrc){
		this.csmSrc = csmSrc;
		this.setSeted(F.csmSrc);
		return this;
	}
	/** 来源渠道  [CsChannel]      **/
	public CsMember csmChannel(Long csmChannel){
		this.csmChannel = csmChannel;
		this.setSeted(F.csmChannel);
		return this;
	}
	/** 营销方案  [CsMarketPlan]      **/
	public CsMember csmMarketPlan(Long csmMarketPlan){
		this.csmMarketPlan = csmMarketPlan;
		this.setSeted(F.csmMarketPlan);
		return this;
	}
	/** 来源活动  [CsActivity]      **/
	public CsMember csmActivity(Long csmActivity){
		this.csmActivity = csmActivity;
		this.setSeted(F.csmActivity);
		return this;
	}
	/** 销售人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsMember csmSaler(Long csmSaler){
		this.csmSaler = csmSaler;
		this.setSeted(F.csmSaler);
		return this;
	}
	/** 营销标记  [SrvProperty]  营销标识:'MEMBER_MARKET'    select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
	public CsMember csmMarket(String csmMarket){
		this.csmMarket = csmMarket;
		this.setSeted(F.csmMarket);
		return this;
	}
	/** 会员标签  [SrvProperty]  用户标签:'MEMBER_TAG'  (会员特性)标签用来对用户分类，在系统属性表中配置  select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
	public CsMember csmTag(String csmTag){
		this.csmTag = csmTag;
		this.setSeted(F.csmTag);
		return this;
	}
	/** 备忘标记  [SrvProperty]  备忘标记:'MEMBER_MARK'  (管理标记)管理人员对该会员做的备忘标记，在系统属性表中配置  select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
	public CsMember csmMark(String csmMark){
		this.csmMark = csmMark;
		this.setSeted(F.csmMark);
		return this;
	}
	/** 回访标记  [CsQuestTemplate]   (回访标记)对应回访模板,勾上了表示该模板已调查或已回访  select * from cs_quest_template where 1=1 **/
	public CsMember csmVisitFlag(String csmVisitFlag){
		this.csmVisitFlag = csmVisitFlag;
		this.setSeted(F.csmVisitFlag);
		return this;
	}
	/** 程序掩码    1:已回流支付宝 2:保留2 4:保留4 8:保留8  (程序标记)技术人员使用的标记，按位运算   **/
	public CsMember csmMask(Long csmMask){
		this.csmMask = csmMask;
		this.setSeted(F.csmMask);
		return this;
	}
	/** 备注        **/
	public CsMember csmRemark(String csmRemark){
		this.csmRemark = csmRemark;
		this.setSeted(F.csmRemark);
		return this;
	}
	/** 手机认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
	public CsMember csmVMobile(Short csmVMobile){
		this.csmVMobile = csmVMobile;
		this.setSeted(F.csmVMobile);
		return this;
	}
	/** 邮箱认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
	public CsMember csmVEmail(Short csmVEmail){
		this.csmVEmail = csmVEmail;
		this.setSeted(F.csmVEmail);
		return this;
	}
	/** 实名认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
	public CsMember csmVReal(Short csmVReal){
		this.csmVReal = csmVReal;
		this.setSeted(F.csmVReal);
		return this;
	}
	/** 驾驶认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
	public CsMember csmVDrive(Short csmVDrive){
		this.csmVDrive = csmVDrive;
		this.setSeted(F.csmVDrive);
		return this;
	}
	
	/** 工作认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
    public CsMember csmVWork(Short csmVWork){
        this.csmVWork = csmVWork;
        this.setSeted(F.csmVWork);
        return this;
    }
    /** 线下认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
    public CsMember csmVOffline(Short csmVOffline){
        this.csmVOffline = csmVOffline;
        this.setSeted(F.csmVOffline);
        return this;
    }
    
	
	/** 可用状态 [非空]   1:正常 0:禁用  -1:黑名单     **/
	public CsMember csmStatus(Short csmStatus){
		this.csmStatus = csmStatus;
		this.setSeted(F.csmStatus);
		return this;
	}
	
	
	//克隆对象
	public CsMember clone(){
		CsMember clone = new CsMember();
		clone.csmHost=this.csmHost;
		clone.csmUsername=this.csmUsername;
		clone.csmPassword=this.csmPassword;
		clone.csmMoney=this.csmMoney;
		clone.csmCoupon=this.csmCoupon;
		clone.csmIntegral=this.csmIntegral;
		clone.csmGrow=this.csmGrow;
		clone.csmGrade=this.csmGrade;
		clone.csmRebate=this.csmRebate;
		clone.csmAddTime=this.csmAddTime;
		clone.csmStatus=this.csmStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取会员帐号
	 * @param id
	 * @return
	 */
	public static @api CsMember get(Long id){		
		return getCsMemberById(id);
	}
	/**
	 * 获取所有会员帐号
	 * @return
	 */
	public static @api List<CsMember> list(Map params,Integer size){
		return getCsMemberList(params,size);
	}
	/**
	 * 获取会员帐号分页
	 * @return
	 */
	public static @api Page<CsMember> page(int page,int size,Map params){
		return getCsMemberPage(page, size , params);
	}
	/**
	 * 根据查询条件取会员帐号
	 * @param params
	 * @return
	 */
	public static @api CsMember Get(Map params){
		return getCsMember(params);
	}
	/**
	 * 获取会员帐号数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsMemberCount(params);
	}
	/**
	 * 获取会员帐号数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsMemberEval(eval,params);
	}
	
	/**
	 * 根据ID取会员帐号
	 * @param id
	 * @return
	 */
	public static @api CsMember getCsMemberById(Long id){		
		CsMember csMember = (CsMember) $.GetRequest("CsMember$"+id);
		if(csMember!=null)
			return csMember;
		com.ccclubs.service.admin.ICsMemberService csMemberService = $.GetSpringBean("csMemberService");		
		return csMemberService.getCsMemberById(id);
	}
	
	
	/**
	 * 根据ID取会员帐号的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsMember.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsMember csMember = get(id);
		if(csMember!=null){
			String strValue = csMember.getCsmName$();
			if(!"CsmName".equals("CsmMobile"))
				strValue+="("+csMember.getCsmMobile$()+")";
			if(!"CsmName".equals("CsmName"))
				strValue+="("+csMember.getCsmName$()+")";
			MemCache.setValue(CsMember.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsmName$();
		if(!"CsmName".equals("CsmMobile"))
			keyValue+="("+this.getCsmMobile$()+")";
		if(!"CsmName".equals("CsmName"))
			keyValue+="("+this.getCsmName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有会员帐号
	 * @return
	 */
	public static @api List<CsMember> getCsMemberList(Map params,Integer size){
		com.ccclubs.service.admin.ICsMemberService csMemberService = $.GetSpringBean("csMemberService");
		return csMemberService.getCsMemberList(params, size);
	}
	
	/**
	 * 获取会员帐号分页
	 * @return
	 */
	public static @api Page<CsMember> getCsMemberPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsMemberService csMemberService = $.GetSpringBean("csMemberService");
		return csMemberService.getCsMemberPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取会员帐号
	 * @param params
	 * @return
	 */
	public static @api CsMember getCsMember(Map params){
		com.ccclubs.service.admin.ICsMemberService csMemberService = $.GetSpringBean("csMemberService");
		return csMemberService.getCsMember(params);
	}
	
	/**
	 * 获取会员帐号数
	 * @return
	 */
	public static @api Long getCsMemberCount(Map params){
		com.ccclubs.service.admin.ICsMemberService csMemberService = $.GetSpringBean("csMemberService");
		return csMemberService.getCsMemberCount(params);
	}
		
		
	/**
	 * 获取会员帐号自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsMemberEval(String eval,Map params){
		com.ccclubs.service.admin.ICsMemberService csMemberService = $.GetSpringBean("csMemberService");
		return csMemberService.getCsMemberEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsMember(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsMemberService csMemberService = $.GetSpringBean("csMemberService");
		csMemberService.updateCsMemberByConfirm(set, where);
	}
	
	
	/**
	 * 保存会员帐号对象
	 * @param params
	 * @return
	 */
	public CsMember save(){
		com.ccclubs.service.admin.ICsMemberService csMemberService = $.GetSpringBean("csMemberService");
		if(this.getCsmId()!=null)
			csMemberService.updateCsMember(this);
		else
			return csMemberService.saveCsMember(this);
		return this;
	}
	
	
	/**
	 * 更新会员帐号对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsMemberService csMemberService = $.GetSpringBean("csMemberService");
		csMemberService.updateCsMember$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsMemberService csMemberService = $.GetSpringBean("csMemberService");
		if($.equals($.config("logic_delete"),"true"))
			csMemberService.removeCsMemberById(this.getCsmId());
		else
			csMemberService.deleteCsMemberById(this.getCsmId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsMemberService csMemberService = $.GetSpringBean("csMemberService");
		return csMemberService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsmId(){
		return this.csmId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsmId$(){
		String strValue="";
		 strValue=$.str(this.getCsmId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsmId(Long csmId){
		this.csmId = csmId;
		this.setSeted(F.csmId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsmHost(){
		return this.csmHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsmHost$(){
		String strValue="";
		if(this.getCsmHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsmHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsmHost(Long csmHost){
		this.csmHost = csmHost;
		this.setSeted(F.csmHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csmHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsmHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsmHost()!=null){
 			srvHost = SrvHost.get(this.getCsmHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsmHost(), srvHost);
	 	return srvHost;
	}
	/*******************************用户名**********************************/	
	/**
	* 用户名 [非空]    如果由第三方平台注册，不同类型加前缀  
	**/
	public String getCsmUsername(){
		return this.csmUsername;
	}
	/**
	* 获取用户名格式化(toString)
	**/
	public String getCsmUsername$(){
		String strValue="";
		 strValue=$.str(this.getCsmUsername());
	 	 return strValue;
	}
	/**
	* 用户名 [非空]    如果由第三方平台注册，不同类型加前缀  
	**/
	public void setCsmUsername(String csmUsername){
		this.csmUsername = csmUsername;
		this.setSeted(F.csmUsername);
	}
	/*******************************帐号密码**********************************/	
	/**
	* 帐号密码 [非空]      
	**/
	public String getCsmPassword(){
		return this.csmPassword;
	}
	/**
	* 获取帐号密码格式化(toString)
	**/
	public String getCsmPassword$(){
		String strValue="";
		 strValue=$.str(this.getCsmPassword());
	 	 return strValue;
	}
	/**
	* 帐号密码 [非空]      
	**/
	public void setCsmPassword(String csmPassword){
		this.csmPassword = csmPassword;
		this.setSeted(F.csmPassword);
	}
	/*******************************所属组织**********************************/	
	/**
	* 所属组织  [CsMemberGroup]     
	**/
	public Long getCsmGroup(){
		return this.csmGroup;
	}
	/**
	* 获取所属组织格式化(toString)
	**/
	public String getCsmGroup$(){
		String strValue="";
		if(this.getCsmGroup()!=null){
				strValue+=$.str(CsMemberGroup.getKeyValue(this.getCsmGroup()));
		}			
	 	 return strValue;
	}
	/**
	* 所属组织  [CsMemberGroup]     
	**/
	public void setCsmGroup(Long csmGroup){
		this.csmGroup = csmGroup;
		this.setSeted(F.csmGroup);
	}
	/**
	* 获取关联对象[会员组织]
	**/	 			
 	public CsMemberGroup get$csmGroup(){
 		com.ccclubs.model.CsMemberGroup csMemberGroup = (com.ccclubs.model.CsMemberGroup) $.GetRequest("CsMemberGroup$"+this.getCsmGroup());
 		if(csMemberGroup!=null)
			return csMemberGroup;
		if(this.getCsmGroup()!=null){
 			csMemberGroup = CsMemberGroup.get(this.getCsmGroup());
 		}
 		$.SetRequest("CsMemberGroup$"+this.getCsmGroup(), csMemberGroup);
	 	return csMemberGroup;
	}
	/*******************************余额**********************************/	
	/**
	* 余额 [非空]      
	**/
	public Double getCsmMoney(){
		return this.csmMoney;
	}
	/**
	* 获取余额格式化(toString)
	**/
	public String getCsmMoney$(){
		String strValue="";
		 strValue=$.str(this.getCsmMoney());	
	 	 return strValue;
	}
	/**
	* 余额 [非空]      
	**/
	public void setCsmMoney(Double csmMoney){
		this.csmMoney = csmMoney;
		this.setSeted(F.csmMoney);
	}
	/*******************************现金券**********************************/	
	/**
	* 现金券 [非空]      
	**/
	public Double getCsmCoupon(){
		return this.csmCoupon;
	}
	/**
	* 获取现金券格式化(toString)
	**/
	public String getCsmCoupon$(){
		String strValue="";
		 strValue=$.str(this.getCsmCoupon());	
	 	 return strValue;
	}
	/**
	* 现金券 [非空]      
	**/
	public void setCsmCoupon(Double csmCoupon){
		this.csmCoupon = csmCoupon;
		this.setSeted(F.csmCoupon);
	}
	/*******************************积分**********************************/	
	/**
	* 积分 [非空]      
	**/
	public Double getCsmIntegral(){
		return this.csmIntegral;
	}
	/**
	* 获取积分格式化(toString)
	**/
	public String getCsmIntegral$(){
		String strValue="";
		 strValue=$.str(this.getCsmIntegral());	
	 	 return strValue;
	}
	/**
	* 积分 [非空]      
	**/
	public void setCsmIntegral(Double csmIntegral){
		this.csmIntegral = csmIntegral;
		this.setSeted(F.csmIntegral);
	}
	/*******************************成长值**********************************/	
	/**
	* 成长值 [非空]      
	**/
	public Integer getCsmGrow(){
		return this.csmGrow;
	}
	/**
	* 获取成长值格式化(toString)
	**/
	public String getCsmGrow$(){
		String strValue="";
		 strValue=$.str(this.getCsmGrow());
	 	 return strValue;
	}
	/**
	* 成长值 [非空]      
	**/
	public void setCsmGrow(Integer csmGrow){
		this.csmGrow = csmGrow;
		this.setSeted(F.csmGrow);
	}
	/*******************************会员等级**********************************/	
	/**
	* 会员等级 [非空]      
	**/
	public Integer getCsmGrade(){
		return this.csmGrade;
	}
	/**
	* 获取会员等级格式化(toString)
	**/
	public String getCsmGrade$(){
		String strValue="";
		 strValue=$.str(this.getCsmGrade());
	 	 return strValue;
	}
	/**
	* 会员等级 [非空]      
	**/
	public void setCsmGrade(Integer csmGrade){
		this.csmGrade = csmGrade;
		this.setSeted(F.csmGrade);
	}
	/*******************************折扣**********************************/	
	/**
	* 折扣 [非空]    0到1,比如0.75表示七五折  
	**/
	public Double getCsmRebate(){
		return this.csmRebate;
	}
	/**
	* 获取折扣格式化(toString)
	**/
	public String getCsmRebate$(){
		String strValue="";
		 strValue=$.str(this.getCsmRebate());	
	 	 return strValue;
	}
	/**
	* 折扣 [非空]    0到1,比如0.75表示七五折  
	**/
	public void setCsmRebate(Double csmRebate){
		this.csmRebate = csmRebate;
		this.setSeted(F.csmRebate);
	}
	/*******************************计算营收**********************************/	
	/**
	* 计算营收    0:计入营收 1:不计营收  表示该帐号支付的订单是否为营收项  
	**/
	public Short getCsmNotRevenue(){
		return this.csmNotRevenue;
	}
	/**
	* 获取计算营收格式化(toString)
	**/
	public String getCsmNotRevenue$(){
		String strValue="";
		 if($.equals($.str(this.getCsmNotRevenue()),"0"))
			strValue=$.str("计入营收");		 
		 if($.equals($.str(this.getCsmNotRevenue()),"1"))
			strValue=$.str("不计营收");		 
	 	 return strValue;
	}
	/**
	* 计算营收    0:计入营收 1:不计营收  表示该帐号支付的订单是否为营收项  
	**/
	public void setCsmNotRevenue(Short csmNotRevenue){
		this.csmNotRevenue = csmNotRevenue;
		this.setSeted(F.csmNotRevenue);
	}
	/*******************************反向关联的系统订单列表**********************************/	
	/**
	* 获取与当前对象反向关联的系统订单列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsOrder> getCsmOrders(){
		com.ccclubs.service.admin.ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
		Map params = new HashMap();
		params.put("csoPayMember",this.getCsmId());
		return csOrderService.getCsOrderList(params,-1);
	}
	/**
	* 获取订单记录格式化(toString)
	**/
	public String getCsmOrders$(){
		return "[...]";
	}
	/*******************************反向关联的支付宝充值列表**********************************/	
	/**
	* 获取与当前对象反向关联的支付宝充值列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsAlipayRecord> getCsmAlipays(){
		com.ccclubs.service.admin.ICsAlipayRecordService csAlipayRecordService = $.GetSpringBean("csAlipayRecordService");
		Map params = new HashMap();
		params.put("csarMember",this.getCsmId());
		return csAlipayRecordService.getCsAlipayRecordList(params,-1);
	}
	/**
	* 获取充值记录格式化(toString)
	**/
	public String getCsmAlipays$(){
		return "[...]";
	}
	/*******************************反向关联的用户套餐列表**********************************/	
	/**
	* 获取与当前对象反向关联的用户套餐列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsUserPack> getCsmPacks(){
		com.ccclubs.service.admin.ICsUserPackService csUserPackService = $.GetSpringBean("csUserPackService");
		Map params = new HashMap();
		params.put("csupMember",this.getCsmId());
		return csUserPackService.getCsUserPackList(params,-1);
	}
	/**
	* 获取使用套餐格式化(toString)
	**/
	public String getCsmPacks$(){
		return "[...]";
	}
	/*******************************反向关联的免费小时列表**********************************/	
	/**
	* 获取与当前对象反向关联的免费小时列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsFreeHour> getCsmFreehours(){
		com.ccclubs.service.admin.ICsFreeHourService csFreeHourService = $.GetSpringBean("csFreeHourService");
		Map params = new HashMap();
		params.put("csfhMember",this.getCsmId());
		return csFreeHourService.getCsFreeHourList(params,-1);
	}
	/**
	* 获取免费小时格式化(toString)
	**/
	public String getCsmFreehours$(){
		return "[...]";
	}
	/*******************************反向关联的列表**********************************/	
	/**
	* 获取与当前对象反向关联的红包列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsCoin> getCsmCoins(){
		com.ccclubs.service.admin.ICsCoinService csCoinService = $.GetSpringBean("csCoinService");
		Map params = new HashMap();
		params.put("cscMember",this.getCsmId());
		return csCoinService.getCsCoinList(params,-1);
	}
	/**
	* 获取红包格式化(toString)
	**/
	public String getCsmCoins$(){
		return "[...]";
	}
	/*******************************反向关联的消费记录列表**********************************/	
	/**
	* 获取与当前对象反向关联的消费记录列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsRecord> getCsmRecords(){
		com.ccclubs.service.admin.ICsRecordService csRecordService = $.GetSpringBean("csRecordService");
		Map params = new HashMap();
		params.put("csrMember",this.getCsmId());
		return csRecordService.getCsRecordList(params,-1);
	}
	/**
	* 获取消费记录格式化(toString)
	**/
	public String getCsmRecords$(){
		return "[...]";
	}
	/*******************************反向关联的信用账单列表**********************************/	
	/**
	* 获取与当前对象反向关联的信用账单列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsCreditBill> getCsmBills(){
		com.ccclubs.service.admin.ICsCreditBillService csCreditBillService = $.GetSpringBean("csCreditBillService");
		Map params = new HashMap();
		params.put("cscbMember",this.getCsmId());
		return csCreditBillService.getCsCreditBillList(params,-1);
	}
	/**
	* 获取信用账单格式化(toString)
	**/
	public String getCsmBills$(){
		return "[...]";
	}
	/*******************************反向关联的积分记录列表**********************************/	
	/**
	* 获取与当前对象反向关联的积分记录列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsIntegralRecord> getCsmIntegrals(){
		com.ccclubs.service.admin.ICsIntegralRecordService csIntegralRecordService = $.GetSpringBean("csIntegralRecordService");
		Map params = new HashMap();
		params.put("csrMember",this.getCsmId());
		return csIntegralRecordService.getCsIntegralRecordList(params,-1);
	}
	/**
	* 获取积分记录格式化(toString)
	**/
	public String getCsmIntegrals$(){
		return "[...]";
	}
	/*******************************反向关联的成长记录列表**********************************/	
	/**
	* 获取与当前对象反向关联的成长记录列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsGrowRecord> getCsmGrows(){
		com.ccclubs.service.admin.ICsGrowRecordService csGrowRecordService = $.GetSpringBean("csGrowRecordService");
		Map params = new HashMap();
		params.put("csgrMember",this.getCsmId());
		return csGrowRecordService.getCsGrowRecordList(params,-1);
	}
	/**
	* 获取成长记录格式化(toString)
	**/
	public String getCsmGrows$(){
		return "[...]";
	}
	/*******************************反向关联的惠免记录列表**********************************/	
	/**
	* 获取与当前对象反向关联的惠免记录列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsUseRecord> getCsmUseRecord(){
		com.ccclubs.service.admin.ICsUseRecordService csUseRecordService = $.GetSpringBean("csUseRecordService");
		Map params = new HashMap();
		params.put("csurMember",this.getCsmId());
		return csUseRecordService.getCsUseRecordList(params,-1);
	}
	/**
	* 获取优惠记录格式化(toString)
	**/
	public String getCsmUseRecord$(){
		return "[...]";
	}
	/*******************************反向关联的会员退款列表**********************************/	
	/**
	* 获取与当前对象反向关联的会员退款列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsRefund> getCsmRefounds(){
		com.ccclubs.service.admin.ICsRefundService csRefundService = $.GetSpringBean("csRefundService");
		Map params = new HashMap();
		params.put("csrMember",this.getCsmId());
		return csRefundService.getCsRefundList(params,-1);
	}
	/**
	* 获取退款记录格式化(toString)
	**/
	public String getCsmRefounds$(){
		return "[...]";
	}
	/*******************************反向关联的车辆违章列表**********************************/	
	/**
	* 获取与当前对象反向关联的车辆违章列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsViolat> getCsmViolats(){
		com.ccclubs.service.admin.ICsViolatService csViolatService = $.GetSpringBean("csViolatService");
		Map params = new HashMap();
		params.put("csvMember",this.getCsmId());
		return csViolatService.getCsViolatList(params,-1);
	}
	/**
	* 获取违章记录格式化(toString)
	**/
	public String getCsmViolats$(){
		return "[...]";
	}
	/*******************************反向关联的车辆事故列表**********************************/	
	/**
	* 获取与当前对象反向关联的车辆事故列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsTrouble> getCsmTroubles(){
		com.ccclubs.service.admin.ICsTroubleService csTroubleService = $.GetSpringBean("csTroubleService");
		Map params = new HashMap();
		params.put("cstMember",this.getCsmId());
		return csTroubleService.getCsTroubleList(params,-1);
	}
	/**
	* 获取事故记录格式化(toString)
	**/
	public String getCsmTroubles$(){
		return "[...]";
	}
	/*******************************反向关联的发票记录列表**********************************/	
	/**
	* 获取与当前对象反向关联的发票记录列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsInvoice> getCsmInvoices(){
		com.ccclubs.service.admin.ICsInvoiceService csInvoiceService = $.GetSpringBean("csInvoiceService");
		Map params = new HashMap();
		params.put("csiMember",this.getCsmId());
		return csInvoiceService.getCsInvoiceList(params,-1);
	}
	/**
	* 获取发票记录格式化(toString)
	**/
	public String getCsmInvoices$(){
		return "[...]";
	}
	/*******************************反向关联的会员地址列表**********************************/	
	/**
	* 获取与当前对象反向关联的会员地址列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsAddress> getCsmAddresses(){
		com.ccclubs.service.admin.ICsAddressService csAddressService = $.GetSpringBean("csAddressService");
		Map params = new HashMap();
		params.put("csaMember",this.getCsmId());
		return csAddressService.getCsAddressList(params,-1);
	}
	/**
	* 获取地址管理格式化(toString)
	**/
	public String getCsmAddresses$(){
		return "[...]";
	}
	/*******************************反向关联的会员备忘列表**********************************/	
	/**
	* 获取与当前对象反向关联的会员备忘列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsMemberMemo> getCsmMemos(){
		com.ccclubs.service.admin.ICsMemberMemoService csMemberMemoService = $.GetSpringBean("csMemberMemoService");
		Map params = new HashMap();
		params.put("csmmMember",this.getCsmId());
		return csMemberMemoService.getCsMemberMemoList(params,-1);
	}
	/**
	* 获取备忘备注格式化(toString)
	**/
	public String getCsmMemos$(){
		return "[...]";
	}
	/*******************************微信帐号标识**********************************/	
	/**
	* 微信帐号标识       
	**/
	public String getCsmWeixinFlag(){
		return this.csmWeixinFlag;
	}
	/**
	* 获取微信帐号标识格式化(toString)
	**/
	public String getCsmWeixinFlag$(){
		String strValue="";
		 strValue=$.str(this.getCsmWeixinFlag());
	 	 return strValue;
	}
	/**
	* 微信帐号标识       
	**/
	public void setCsmWeixinFlag(String csmWeixinFlag){
		this.csmWeixinFlag = csmWeixinFlag;
		this.setSeted(F.csmWeixinFlag);
	}
	/*******************************支付宝帐号标识**********************************/	
	/**
	* 支付宝帐号标识       
	**/
	public String getCsmAlipayFlag(){
		return this.csmAlipayFlag;
	}
	/**
	* 获取支付宝帐号标识格式化(toString)
	**/
	public String getCsmAlipayFlag$(){
		String strValue="";
		 strValue=$.str(this.getCsmAlipayFlag());
	 	 return strValue;
	}
	/**
	* 支付宝帐号标识       
	**/
	public void setCsmAlipayFlag(String csmAlipayFlag){
		this.csmAlipayFlag = csmAlipayFlag;
		this.setSeted(F.csmAlipayFlag);
	}
	/*******************************头像**********************************/	
	/**
	* 头像    200:width 200:height    
	**/
	public String getCsmHeader(){
		return this.csmHeader;
	}
	/**
	* 获取头像格式化(toString)
	**/
	public String getCsmHeader$(){
		String strValue="";
		 strValue=$.str(this.getCsmHeader());
	 	 return strValue;
	}
	/**
	* 头像    200:width 200:height    
	**/
	public void setCsmHeader(String csmHeader){
		this.csmHeader = csmHeader;
		this.setSeted(F.csmHeader);
	}
	/*******************************邮箱**********************************/	
	/**
	* 邮箱       
	**/
	public String getCsmEmail(){
		return this.csmEmail;
	}
	/**
	* 获取邮箱格式化(toString)
	**/
	public String getCsmEmail$(){
		String strValue="";
		 strValue=$.str(this.getCsmEmail());
	 	 return strValue;
	}
	/**
	* 邮箱       
	**/
	public void setCsmEmail(String csmEmail){
		this.csmEmail = csmEmail;
		this.setSeted(F.csmEmail);
	}
	/*******************************手机**********************************/	
	/**
	* 手机       
	**/
	public String getCsmMobile(){
		return this.csmMobile;
	}
	/**
	* 获取手机格式化(toString)
	**/
	public String getCsmMobile$(){
		String strValue="";
		 strValue=$.str(this.getCsmMobile());
	 	 return strValue;
	}
	/**
	* 手机       
	**/
	public void setCsmMobile(String csmMobile){
		this.csmMobile = csmMobile;
		this.setSeted(F.csmMobile);
	}
	/*******************************EV卡**********************************/	
	/**
	* EV卡  [CsEvCard]     
	**/
	public Long getCsmEvcard(){
		return this.csmEvcard;
	}
	/**
	* 获取EV卡格式化(toString)
	**/
	public String getCsmEvcard$(){
		String strValue="";
		if(this.getCsmEvcard()!=null){
				strValue+=$.str(CsEvCard.getKeyValue(this.getCsmEvcard()));
		}			
	 	 return strValue;
	}
	/**
	* EV卡  [CsEvCard]     
	**/
	public void setCsmEvcard(Long csmEvcard){
		this.csmEvcard = csmEvcard;
		this.setSeted(F.csmEvcard);
	}
	/**
	* 获取关联对象[会员卡]
	**/	 			
 	public CsEvCard get$csmEvcard(){
 		com.ccclubs.model.CsEvCard csEvCard = (com.ccclubs.model.CsEvCard) $.GetRequest("CsEvCard$"+this.getCsmEvcard());
 		if(csEvCard!=null)
			return csEvCard;
		if(this.getCsmEvcard()!=null){
 			csEvCard = CsEvCard.get(this.getCsmEvcard());
 		}
 		$.SetRequest("CsEvCard$"+this.getCsmEvcard(), csEvCard);
	 	return csEvCard;
	}
	/*******************************快递单号**********************************/	
	/**
	* 快递单号       
	**/
	public String getCsmExpress(){
		return this.csmExpress;
	}
	/**
	* 获取快递单号格式化(toString)
	**/
	public String getCsmExpress$(){
		String strValue="";
		 strValue=$.str(this.getCsmExpress());
	 	 return strValue;
	}
	/**
	* 快递单号       
	**/
	public void setCsmExpress(String csmExpress){
		this.csmExpress = csmExpress;
		this.setSeted(F.csmExpress);
	}
	/*******************************邀请码**********************************/	
	/**
	* 邀请码       
	**/
	public String getCsmTemp(){
		return this.csmTemp;
	}
	/**
	* 获取邀请码格式化(toString)
	**/
	public String getCsmTemp$(){
		String strValue="";
		 strValue=$.str(this.getCsmTemp());
	 	 return strValue;
	}
	/**
	* 邀请码       
	**/
	public void setCsmTemp(String csmTemp){
		this.csmTemp = csmTemp;
		this.setSeted(F.csmTemp);
	}
	/*******************************真实姓名**********************************/	
	/**
	* 真实姓名       
	**/
	public String getCsmName(){
		return this.csmName;
	}
	/**
	* 获取真实姓名格式化(toString)
	**/
	public String getCsmName$(){
		String strValue="";
		 strValue=$.str(this.getCsmName());
	 	 return strValue;
	}
	/**
	* 真实姓名       
	**/
	public void setCsmName(String csmName){
		this.csmName = csmName;
		this.setSeted(F.csmName);
	}
	/*******************************详细信息**********************************/	
	/**
	* 详细信息  [CsMemberInfo]     
	**/
	public Long getCsmInfo(){
		return this.csmInfo;
	}
	/**
	* 获取详细信息格式化(toString)
	**/
	public String getCsmInfo$(){
		String strValue="";
		if(this.getCsmInfo()!=null){
				strValue+=$.str(CsMemberInfo.getKeyValue(this.getCsmInfo()));
		}			
	 	 return strValue;
	}
	/**
	* 详细信息  [CsMemberInfo]     
	**/
	public void setCsmInfo(Long csmInfo){
		this.csmInfo = csmInfo;
		this.setSeted(F.csmInfo);
	}
	/**
	* 获取关联对象[会员信息]
	**/	 			
 	public CsMemberInfo get$csmInfo(){
 		com.ccclubs.model.CsMemberInfo csMemberInfo = (com.ccclubs.model.CsMemberInfo) $.GetRequest("CsMemberInfo$"+this.getCsmInfo());
 		if(csMemberInfo!=null)
			return csMemberInfo;
		if(this.getCsmInfo()!=null){
 			csMemberInfo = CsMemberInfo.get(this.getCsmInfo());
 		}
 		$.SetRequest("CsMemberInfo$"+this.getCsmInfo(), csMemberInfo);
	 	return csMemberInfo;
	}
	/*******************************默认网点**********************************/	
	/**
	* 默认网点  [CsOutlets]     ${basePath}${proname}/object/outlets_tree.do?parent={param}
	**/
	public Long getCsmOutlets(){
		return this.csmOutlets;
	}
	/**
	* 获取默认网点格式化(toString)
	**/
	public String getCsmOutlets$(){
		String strValue="";
		if(this.getCsmOutlets()!=null){
				strValue+=$.str(CsOutlets.getKeyValue(this.getCsmOutlets()));
		}			
	 	 return strValue;
	}
	/**
	* 默认网点  [CsOutlets]     ${basePath}${proname}/object/outlets_tree.do?parent={param}
	**/
	public void setCsmOutlets(Long csmOutlets){
		this.csmOutlets = csmOutlets;
		this.setSeted(F.csmOutlets);
	}
	/**
	* 获取关联对象[网点]
	**/	 			
 	public CsOutlets get$csmOutlets(){
 		com.ccclubs.model.CsOutlets csOutlets = (com.ccclubs.model.CsOutlets) $.GetRequest("CsOutlets$"+this.getCsmOutlets());
 		if(csOutlets!=null)
			return csOutlets;
		if(this.getCsmOutlets()!=null){
 			csOutlets = CsOutlets.get(this.getCsmOutlets());
 		}
 		$.SetRequest("CsOutlets$"+this.getCsmOutlets(), csOutlets);
	 	return csOutlets;
	}
	/*******************************VIP状态**********************************/	
	/**
	* VIP状态    0:不是 1:是    
	**/
	public Short getCsmIsVip(){
		return this.csmIsVip;
	}
	/**
	* 获取VIP状态格式化(toString)
	**/
	public String getCsmIsVip$(){
		String strValue="";
		 if($.equals($.str(this.getCsmIsVip()),"0"))
			strValue=$.str("不是");		 
		 if($.equals($.str(this.getCsmIsVip()),"1"))
			strValue=$.str("是");		 
	 	 return strValue;
	}
	/**
	* VIP状态    0:不是 1:是    
	**/
	public void setCsmIsVip(Short csmIsVip){
		this.csmIsVip = csmIsVip;
		this.setSeted(F.csmIsVip);
	}
	/*******************************VIP开始时间**********************************/	
	/**
	* VIP开始时间       
	**/
	public Date getCsmVipStart(){
		return this.csmVipStart;
	}
	/**
	* 获取VIP开始时间格式化(toString)
	**/
	public String getCsmVipStart$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmVipStart(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* VIP开始时间       
	**/
	public void setCsmVipStart(Date csmVipStart){
		this.csmVipStart = csmVipStart;
		this.setSeted(F.csmVipStart);
	}
	/*******************************VIP结束时间**********************************/	
	/**
	* VIP结束时间       
	**/
	public Date getCsmVipEnd(){
		return this.csmVipEnd;
	}
	/**
	* 获取VIP结束时间格式化(toString)
	**/
	public String getCsmVipEnd$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmVipEnd(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* VIP结束时间       
	**/
	public void setCsmVipEnd(Date csmVipEnd){
		this.csmVipEnd = csmVipEnd;
		this.setSeted(F.csmVipEnd);
	}
	/*******************************更新时间**********************************/	
	/**
	* 更新时间       
	**/
	public Date getCsmUpdateTime(){
		return this.csmUpdateTime;
	}
	/**
	* 获取更新时间格式化(toString)
	**/
	public String getCsmUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 更新时间       
	**/
	public void setCsmUpdateTime(Date csmUpdateTime){
		this.csmUpdateTime = csmUpdateTime;
		this.setSeted(F.csmUpdateTime);
	}
	/*******************************注册时间**********************************/	
	/**
	* 注册时间 [非空]      
	**/
	public Date getCsmAddTime(){
		return this.csmAddTime;
	}
	/**
	* 获取注册时间格式化(toString)
	**/
	public String getCsmAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 注册时间 [非空]      
	**/
	public void setCsmAddTime(Date csmAddTime){
		this.csmAddTime = csmAddTime;
		this.setSeted(F.csmAddTime);
	}
	/*******************************最后登录**********************************/	
	/**
	* 最后登录       
	**/
	public Date getCsmLastTime(){
		return this.csmLastTime;
	}
	/**
	* 获取最后登录格式化(toString)
	**/
	public String getCsmLastTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmLastTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 最后登录       
	**/
	public void setCsmLastTime(Date csmLastTime){
		this.csmLastTime = csmLastTime;
		this.setSeted(F.csmLastTime);
	}
	/*******************************最后登录IP**********************************/	
	/**
	* 最后登录IP       
	**/
	public String getCsmLastIp(){
		return this.csmLastIp;
	}
	/**
	* 获取最后登录IP格式化(toString)
	**/
	public String getCsmLastIp$(){
		String strValue="";
		 strValue=$.str(this.getCsmLastIp());
	 	 return strValue;
	}
	/**
	* 最后登录IP       
	**/
	public void setCsmLastIp(String csmLastIp){
		this.csmLastIp = csmLastIp;
		this.setSeted(F.csmLastIp);
	}
	/*******************************登录次数**********************************/	
	/**
	* 登录次数       
	**/
	public Integer getCsmLoginS(){
		return this.csmLoginS;
	}
	/**
	* 获取登录次数格式化(toString)
	**/
	public String getCsmLoginS$(){
		String strValue="";
		 strValue=$.str(this.getCsmLoginS());
	 	 return strValue;
	}
	/**
	* 登录次数       
	**/
	public void setCsmLoginS(Integer csmLoginS){
		this.csmLoginS = csmLoginS;
		this.setSeted(F.csmLoginS);
	}
	/*******************************首次用车**********************************/	
	/**
	* 首次用车       
	**/
	public Date getCsmFirstUse(){
		return this.csmFirstUse;
	}
	/**
	* 获取首次用车格式化(toString)
	**/
	public String getCsmFirstUse$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmFirstUse(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 首次用车       
	**/
	public void setCsmFirstUse(Date csmFirstUse){
		this.csmFirstUse = csmFirstUse;
		this.setSeted(F.csmFirstUse);
	}
	/*******************************最后使用**********************************/	
	/**
	* 最后使用       
	**/
	public Date getCsmLastUse(){
		return this.csmLastUse;
	}
	/**
	* 获取最后使用格式化(toString)
	**/
	public String getCsmLastUse$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmLastUse(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 最后使用       
	**/
	public void setCsmLastUse(Date csmLastUse){
		this.csmLastUse = csmLastUse;
		this.setSeted(F.csmLastUse);
	}
	/*******************************累计充值**********************************/	
	/**
	* 累计充值       
	**/
	public Double getCsmAllRecharge(){
		return this.csmAllRecharge;
	}
	/**
	* 获取累计充值格式化(toString)
	**/
	public String getCsmAllRecharge$(){
		String strValue="";
		 strValue=$.str(this.getCsmAllRecharge());	
	 	 return strValue;
	}
	/**
	* 累计充值       
	**/
	public void setCsmAllRecharge(Double csmAllRecharge){
		this.csmAllRecharge = csmAllRecharge;
		this.setSeted(F.csmAllRecharge);
	}
	/*******************************累计订单**********************************/	
	/**
	* 累计订单       
	**/
	public Long getCsmAllOrderS(){
		return this.csmAllOrderS;
	}
	/**
	* 获取累计订单格式化(toString)
	**/
	public String getCsmAllOrderS$(){
		String strValue="";
		 strValue=$.str(this.getCsmAllOrderS());
	 	 return strValue;
	}
	/**
	* 累计订单       
	**/
	public void setCsmAllOrderS(Long csmAllOrderS){
		this.csmAllOrderS = csmAllOrderS;
		this.setSeted(F.csmAllOrderS);
	}
	/*******************************累计用车小时**********************************/	
	/**
	* 累计用车小时       
	**/
	public Double getCsmAllUseTime(){
		return this.csmAllUseTime;
	}
	/**
	* 获取累计用车小时格式化(toString)
	**/
	public String getCsmAllUseTime$(){
		String strValue="";
		 strValue=$.str(this.getCsmAllUseTime());	
	 	 return strValue;
	}
	/**
	* 累计用车小时       
	**/
	public void setCsmAllUseTime(Double csmAllUseTime){
		this.csmAllUseTime = csmAllUseTime;
		this.setSeted(F.csmAllUseTime);
	}
	/*******************************违章次数**********************************/	
	/**
	* 违章次数       
	**/
	public Long getCsmAllViolatS(){
		return this.csmAllViolatS;
	}
	/**
	* 获取违章次数格式化(toString)
	**/
	public String getCsmAllViolatS$(){
		String strValue="";
		 strValue=$.str(this.getCsmAllViolatS());
	 	 return strValue;
	}
	/**
	* 违章次数       
	**/
	public void setCsmAllViolatS(Long csmAllViolatS){
		this.csmAllViolatS = csmAllViolatS;
		this.setSeted(F.csmAllViolatS);
	}
	/*******************************未处理违章**********************************/	
	/**
	* 未处理违章       
	**/
	public Long getCsmUnViolatS(){
		return this.csmUnViolatS;
	}
	/**
	* 获取未处理违章格式化(toString)
	**/
	public String getCsmUnViolatS$(){
		String strValue="";
		 strValue=$.str(this.getCsmUnViolatS());
	 	 return strValue;
	}
	/**
	* 未处理违章       
	**/
	public void setCsmUnViolatS(Long csmUnViolatS){
		this.csmUnViolatS = csmUnViolatS;
		this.setSeted(F.csmUnViolatS);
	}
	/*******************************事故次数**********************************/	
	/**
	* 事故次数       
	**/
	public Long getCsmAllTroubleS(){
		return this.csmAllTroubleS;
	}
	/**
	* 获取事故次数格式化(toString)
	**/
	public String getCsmAllTroubleS$(){
		String strValue="";
		 strValue=$.str(this.getCsmAllTroubleS());
	 	 return strValue;
	}
	/**
	* 事故次数       
	**/
	public void setCsmAllTroubleS(Long csmAllTroubleS){
		this.csmAllTroubleS = csmAllTroubleS;
		this.setSeted(F.csmAllTroubleS);
	}
	/*******************************首次充值**********************************/	
	/**
	* 首次充值       
	**/
	public Date getCsmFirstRecharge(){
		return this.csmFirstRecharge;
	}
	/**
	* 获取首次充值格式化(toString)
	**/
	public String getCsmFirstRecharge$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmFirstRecharge(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 首次充值       
	**/
	public void setCsmFirstRecharge(Date csmFirstRecharge){
		this.csmFirstRecharge = csmFirstRecharge;
		this.setSeted(F.csmFirstRecharge);
	}
	/*******************************最后退款**********************************/	
	/**
	* 最后退款       
	**/
	public Date getCsmRefundTime(){
		return this.csmRefundTime;
	}
	/**
	* 获取最后退款格式化(toString)
	**/
	public String getCsmRefundTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmRefundTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 最后退款       
	**/
	public void setCsmRefundTime(Date csmRefundTime){
		this.csmRefundTime = csmRefundTime;
		this.setSeted(F.csmRefundTime);
	}
	/*******************************注册IP**********************************/	
	/**
	* 注册IP       
	**/
	public String getCsmRegistIp(){
		return this.csmRegistIp;
	}
	/**
	* 获取注册IP格式化(toString)
	**/
	public String getCsmRegistIp$(){
		String strValue="";
		 strValue=$.str(this.getCsmRegistIp());
	 	 return strValue;
	}
	/**
	* 注册IP       
	**/
	public void setCsmRegistIp(String csmRegistIp){
		this.csmRegistIp = csmRegistIp;
		this.setSeted(F.csmRegistIp);
	}
	/*******************************添加人员**********************************/	
	/**
	* 添加人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsmAdder(){
		return this.csmAdder;
	}
	/**
	* 获取添加人员格式化(toString)
	**/
	public String getCsmAdder$(){
		String strValue="";
		if(this.getCsmAdder()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsmAdder()));
		}			
	 	 return strValue;
	}
	/**
	* 添加人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsmAdder(Long csmAdder){
		this.csmAdder = csmAdder;
		this.setSeted(F.csmAdder);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csmAdder(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsmAdder());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsmAdder()!=null){
 			srvUser = SrvUser.get(this.getCsmAdder());
 		}
 		$.SetRequest("SrvUser$"+this.getCsmAdder(), srvUser);
	 	return srvUser;
	}
	/*******************************跟踪人员**********************************/	
	/**
	* 跟踪人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsmTracker(){
		return this.csmTracker;
	}
	/**
	* 获取跟踪人员格式化(toString)
	**/
	public String getCsmTracker$(){
		String strValue="";
		if(this.getCsmTracker()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsmTracker()));
		}			
	 	 return strValue;
	}
	/**
	* 跟踪人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsmTracker(Long csmTracker){
		this.csmTracker = csmTracker;
		this.setSeted(F.csmTracker);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csmTracker(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsmTracker());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsmTracker()!=null){
 			srvUser = SrvUser.get(this.getCsmTracker());
 		}
 		$.SetRequest("SrvUser$"+this.getCsmTracker(), srvUser);
	 	return srvUser;
	}
	/*******************************推荐会员**********************************/	
	/**
	* 推荐会员  [CsMember]     
	**/
	public Long getCsmRefer(){
		return this.csmRefer;
	}
	/**
	* 获取推荐会员格式化(toString)
	**/
	public String getCsmRefer$(){
		String strValue="";
		if(this.getCsmRefer()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsmRefer()));
		}			
	 	 return strValue;
	}
	/**
	* 推荐会员  [CsMember]     
	**/
	public void setCsmRefer(Long csmRefer){
		this.csmRefer = csmRefer;
		this.setSeted(F.csmRefer);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csmRefer(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsmRefer());
 		if(csMember!=null)
			return csMember;
		if(this.getCsmRefer()!=null){
 			csMember = CsMember.get(this.getCsmRefer());
 		}
 		$.SetRequest("CsMember$"+this.getCsmRefer(), csMember);
	 	return csMember;
	}
	/*******************************推荐方式**********************************/	
	/**
	* 推荐方式    0:无 1:地址转发 2:微博分享 3:广告媒体 4:网络搜索 5:朋友推荐 6:活动推广    
	**/
	public Short getCsmReferType(){
		return this.csmReferType;
	}
	/**
	* 获取推荐方式格式化(toString)
	**/
	public String getCsmReferType$(){
		String strValue="";
		 if($.equals($.str(this.getCsmReferType()),"0"))
			strValue=$.str("无");		 
		 if($.equals($.str(this.getCsmReferType()),"1"))
			strValue=$.str("地址转发");		 
		 if($.equals($.str(this.getCsmReferType()),"2"))
			strValue=$.str("微博分享");		 
		 if($.equals($.str(this.getCsmReferType()),"3"))
			strValue=$.str("广告媒体");		 
		 if($.equals($.str(this.getCsmReferType()),"4"))
			strValue=$.str("网络搜索");		 
		 if($.equals($.str(this.getCsmReferType()),"5"))
			strValue=$.str("朋友推荐");		 
		 if($.equals($.str(this.getCsmReferType()),"6"))
			strValue=$.str("活动推广");		 
	 	 return strValue;
	}
	/**
	* 推荐方式    0:无 1:地址转发 2:微博分享 3:广告媒体 4:网络搜索 5:朋友推荐 6:活动推广    
	**/
	public void setCsmReferType(Short csmReferType){
		this.csmReferType = csmReferType;
		this.setSeted(F.csmReferType);
	}
	/*******************************注册平台**********************************/	
	/**
	* 注册平台    0:后台 1:网站 2:微信 3:APP 4:支付宝微生活    
	**/
	public Short getCsmFrom(){
		return this.csmFrom;
	}
	/**
	* 获取注册平台格式化(toString)
	**/
	public String getCsmFrom$(){
		String strValue="";
		 if($.equals($.str(this.getCsmFrom()),"0"))
			strValue=$.str("后台");		 
		 if($.equals($.str(this.getCsmFrom()),"1"))
			strValue=$.str("网站");		 
		 if($.equals($.str(this.getCsmFrom()),"2"))
			strValue=$.str("微信");		 
		 if($.equals($.str(this.getCsmFrom()),"3"))
			strValue=$.str("IOS");		 
		 if($.equals($.str(this.getCsmFrom()),"4"))
			strValue=$.str("ANDROID");	
		 if($.equals($.str(this.getCsmFrom()),"5"))
				strValue=$.str("OFFICE");
	 	 return strValue;
	}
	/**
	* 注册平台    0:后台 1:网站 2:微信 3:APP 4:支付宝微生活    
	**/
	public void setCsmFrom(Short csmFrom){
		this.csmFrom = csmFrom;
		this.setSeted(F.csmFrom);
	}
	/*******************************注册源信息**********************************/	
	/**
	* 注册源信息       
	**/
	public String getCsmSrc(){
		return this.csmSrc;
	}
	/**
	* 获取注册源信息格式化(toString)
	**/
	public String getCsmSrc$(){
		String strValue="";
	 	 Object object = this.get$csmSrc();
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
	* 注册源信息       
	**/
	public void setCsmSrc(String csmSrc){
		this.csmSrc = csmSrc;
		this.setSeted(F.csmSrc);
	}
	/**
	* 泛关联对象，对象类名由csmSrc字段中标明
	**/	 			
 	public <T> T get$csmSrc(){
		if($.empty(this.csmSrc))
 			return null;
 		try{
	 		String ClassName = this.csmSrc.split("@")[0];
	 		Long id = Long.parseLong(this.csmSrc.split("@")[1]);
	 		Class<?> cls = Class.forName("com.ccclubs.model."+ClassName);
	 		return (T)cls.getMethod("get", Long.class).invoke(null, id);	
 		}catch(Exception ex){
 			ex.printStackTrace();
 			return null;
 		}
	}
	/*******************************来源渠道**********************************/	
	/**
	* 来源渠道  [CsChannel]     
	**/
	public Long getCsmChannel(){
		return this.csmChannel;
	}
	/**
	* 获取来源渠道格式化(toString)
	**/
	public String getCsmChannel$(){
		String strValue="";
		if(this.getCsmChannel()!=null){
				strValue+=$.str(CsChannel.getKeyValue(this.getCsmChannel()));
		}			
	 	 return strValue;
	}
	/**
	* 来源渠道  [CsChannel]     
	**/
	public void setCsmChannel(Long csmChannel){
		this.csmChannel = csmChannel;
		this.setSeted(F.csmChannel);
	}
	/**
	* 获取关联对象[访问渠道]
	**/	 			
 	public CsChannel get$csmChannel(){
 		com.ccclubs.model.CsChannel csChannel = (com.ccclubs.model.CsChannel) $.GetRequest("CsChannel$"+this.getCsmChannel());
 		if(csChannel!=null)
			return csChannel;
		if(this.getCsmChannel()!=null){
 			csChannel = CsChannel.get(this.getCsmChannel());
 		}
 		$.SetRequest("CsChannel$"+this.getCsmChannel(), csChannel);
	 	return csChannel;
	}
	/*******************************营销方案**********************************/	
	/**
	* 营销方案  [CsMarketPlan]     
	**/
	public Long getCsmMarketPlan(){
		return this.csmMarketPlan;
	}
	/**
	* 获取营销方案格式化(toString)
	**/
	public String getCsmMarketPlan$(){
		String strValue="";
		if(this.getCsmMarketPlan()!=null){
				strValue+=$.str(CsMarketPlan.getKeyValue(this.getCsmMarketPlan()));
		}			
	 	 return strValue;
	}
	/**
	* 营销方案  [CsMarketPlan]     
	**/
	public void setCsmMarketPlan(Long csmMarketPlan){
		this.csmMarketPlan = csmMarketPlan;
		this.setSeted(F.csmMarketPlan);
	}
	/**
	* 获取关联对象[营销方案]
	**/	 			
 	public CsMarketPlan get$csmMarketPlan(){
 		com.ccclubs.model.CsMarketPlan csMarketPlan = (com.ccclubs.model.CsMarketPlan) $.GetRequest("CsMarketPlan$"+this.getCsmMarketPlan());
 		if(csMarketPlan!=null)
			return csMarketPlan;
		if(this.getCsmMarketPlan()!=null){
 			csMarketPlan = CsMarketPlan.get(this.getCsmMarketPlan());
 		}
 		$.SetRequest("CsMarketPlan$"+this.getCsmMarketPlan(), csMarketPlan);
	 	return csMarketPlan;
	}
	/*******************************来源活动**********************************/	
	/**
	* 来源活动  [CsActivity]     
	**/
	public Long getCsmActivity(){
		return this.csmActivity;
	}
	/**
	* 获取来源活动格式化(toString)
	**/
	public String getCsmActivity$(){
		String strValue="";
		if(this.getCsmActivity()!=null){
				strValue+=$.str(CsActivity.getKeyValue(this.getCsmActivity()));
		}			
	 	 return strValue;
	}
	/**
	* 来源活动  [CsActivity]     
	**/
	public void setCsmActivity(Long csmActivity){
		this.csmActivity = csmActivity;
		this.setSeted(F.csmActivity);
	}
	/**
	* 获取关联对象[营销活动]
	**/	 			
 	public CsActivity get$csmActivity(){
 		com.ccclubs.model.CsActivity csActivity = (com.ccclubs.model.CsActivity) $.GetRequest("CsActivity$"+this.getCsmActivity());
 		if(csActivity!=null)
			return csActivity;
		if(this.getCsmActivity()!=null){
 			csActivity = CsActivity.get(this.getCsmActivity());
 		}
 		$.SetRequest("CsActivity$"+this.getCsmActivity(), csActivity);
	 	return csActivity;
	}
	/*******************************销售人员**********************************/	
	/**
	* 销售人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsmSaler(){
		return this.csmSaler;
	}
	/**
	* 获取销售人员格式化(toString)
	**/
	public String getCsmSaler$(){
		String strValue="";
		if(this.getCsmSaler()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsmSaler()));
		}			
	 	 return strValue;
	}
	/**
	* 销售人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsmSaler(Long csmSaler){
		this.csmSaler = csmSaler;
		this.setSeted(F.csmSaler);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csmSaler(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsmSaler());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsmSaler()!=null){
 			srvUser = SrvUser.get(this.getCsmSaler());
 		}
 		$.SetRequest("SrvUser$"+this.getCsmSaler(), srvUser);
	 	return srvUser;
	}
	/*******************************营销标记**********************************/	
	/**
	* 营销标记  [SrvProperty]  营销标识:'MEMBER_MARKET'    select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)
	**/
	public String getCsmMarket(){
		return this.csmMarket;
	}
	/**
	* 获取营销标记格式化(toString)
	**/
	public String getCsmMarket$(){
		String strValue="";
			
		if(!$.empty(this.getCsmMarket())){
			List<com.ccclubs.model.SrvProperty> items = com.ccclubs.model.SrvProperty.getSrvPropertyList($.add("definex","sp_id in ("+$.str(this.getCsmMarket()).replace("#","")+")"),-1);
			for(com.ccclubs.model.SrvProperty item1:items)
				strValue+="["+$.str(item1.getSpName())+"]";
		}
	 	 return strValue;
	}
	/**
	* 营销标记  [SrvProperty]  营销标识:'MEMBER_MARKET'    select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)
	**/
	public void setCsmMarket(String csmMarket){
		this.csmMarket = csmMarket;
		this.setSeted(F.csmMarket);
	}
			
	/**
	* 获取营销标记列表
	**/ 			
 	public List<com.ccclubs.model.SrvProperty> get$csmMarket(){
		if(!$.empty(this.getCsmMarket())){
			return com.ccclubs.model.SrvProperty.getSrvPropertyList($.add("definex","sp_id in ("+$.str(this.getCsmMarket()).replace("#","")+")"),-1);
		}else
			return new ArrayList();
	}
	/*******************************会员标签**********************************/	
	/**
	* 会员标签  [SrvProperty]  用户标签:'MEMBER_TAG'  (会员特性)标签用来对用户分类，在系统属性表中配置  select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)
	**/
	public String getCsmTag(){
		return this.csmTag;
	}
	/**
	* 获取会员标签格式化(toString)
	**/
	public String getCsmTag$(){
		String strValue="";
			
		if(!$.empty(this.getCsmTag())){
			List<com.ccclubs.model.SrvProperty> items = com.ccclubs.model.SrvProperty.getSrvPropertyList($.add("definex","sp_id in ("+$.str(this.getCsmTag()).replace("#","")+")"),-1);
			for(com.ccclubs.model.SrvProperty item1:items)
				strValue+="["+$.str(item1.getSpName())+"]";
		}
	 	 return strValue;
	}
	/**
	* 会员标签  [SrvProperty]  用户标签:'MEMBER_TAG'  (会员特性)标签用来对用户分类，在系统属性表中配置  select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)
	**/
	public void setCsmTag(String csmTag){
		this.csmTag = csmTag;
		this.setSeted(F.csmTag);
	}
			
	/**
	* 获取会员标签列表
	**/ 			
 	public List<com.ccclubs.model.SrvProperty> get$csmTag(){
		if(!$.empty(this.getCsmTag())){
			return com.ccclubs.model.SrvProperty.getSrvPropertyList($.add("definex","sp_id in ("+$.str(this.getCsmTag()).replace("#","")+")"),-1);
		}else
			return new ArrayList();
	}
	/*******************************备忘标记**********************************/	
	/**
	* 备忘标记  [SrvProperty]  备忘标记:'MEMBER_MARK'  (管理标记)管理人员对该会员做的备忘标记，在系统属性表中配置  select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)
	**/
	public String getCsmMark(){
		return this.csmMark;
	}
	/**
	* 获取备忘标记格式化(toString)
	**/
	public String getCsmMark$(){
		String strValue="";
			
		if(!$.empty(this.getCsmMark())){
			List<com.ccclubs.model.SrvProperty> items = com.ccclubs.model.SrvProperty.getSrvPropertyList($.add("definex","sp_id in ("+$.str(this.getCsmMark()).replace("#","")+")"),-1);
			for(com.ccclubs.model.SrvProperty item1:items)
				strValue+="["+$.str(item1.getSpName())+"]";
		}
	 	 return strValue;
	}
	/**
	* 备忘标记  [SrvProperty]  备忘标记:'MEMBER_MARK'  (管理标记)管理人员对该会员做的备忘标记，在系统属性表中配置  select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)
	**/
	public void setCsmMark(String csmMark){
		this.csmMark = csmMark;
		this.setSeted(F.csmMark);
	}
			
	/**
	* 获取备忘标记列表
	**/ 			
 	public List<com.ccclubs.model.SrvProperty> get$csmMark(){
		if(!$.empty(this.getCsmMark())){
			return com.ccclubs.model.SrvProperty.getSrvPropertyList($.add("definex","sp_id in ("+$.str(this.getCsmMark()).replace("#","")+")"),-1);
		}else
			return new ArrayList();
	}
	/*******************************回访标记**********************************/	
	/**
	* 回访标记  [CsQuestTemplate]   (回访标记)对应回访模板,勾上了表示该模板已调查或已回访  select * from cs_quest_template where 1=1
	**/
	public String getCsmVisitFlag(){
		return this.csmVisitFlag;
	}
	/**
	* 获取回访标记格式化(toString)
	**/
	public String getCsmVisitFlag$(){
		String strValue="";
			
		if(!$.empty(this.getCsmVisitFlag())){
			List<com.ccclubs.model.CsQuestTemplate> items = com.ccclubs.model.CsQuestTemplate.getCsQuestTemplateList($.add("definex","csqt_id in ("+$.str(this.getCsmVisitFlag()).replace("#","")+")"),-1);
			for(com.ccclubs.model.CsQuestTemplate item1:items)
				strValue+="["+$.str(item1.getCsqtName())+"]";
		}
	 	 return strValue;
	}
	/**
	* 回访标记  [CsQuestTemplate]   (回访标记)对应回访模板,勾上了表示该模板已调查或已回访  select * from cs_quest_template where 1=1
	**/
	public void setCsmVisitFlag(String csmVisitFlag){
		this.csmVisitFlag = csmVisitFlag;
		this.setSeted(F.csmVisitFlag);
	}
			
	/**
	* 获取回访标记列表
	**/ 			
 	public List<com.ccclubs.model.CsQuestTemplate> get$csmVisitFlag(){
		if(!$.empty(this.getCsmVisitFlag())){
			return com.ccclubs.model.CsQuestTemplate.getCsQuestTemplateList($.add("definex","csqt_id in ("+$.str(this.getCsmVisitFlag()).replace("#","")+")"),-1);
		}else
			return new ArrayList();
	}
	/*******************************程序掩码**********************************/	
	/**
	* 程序掩码    1:已回流支付宝 2:保留2 4:保留4 8:保留8  (程序标记)技术人员使用的标记，按位运算  
	**/
	public Long getCsmMask(){
		return this.csmMask;
	}
	/**
	* 获取程序掩码格式化(toString)
	**/
	public String getCsmMask$(){
		String strValue="";
		if(this.getCsmMask()!=null){
			if((this.getCsmMask() & 1 ) == 1)
				strValue+="["+$.str("已回流支付宝")+"]";		 
			if((this.getCsmMask() & 2 ) == 2)
				strValue+="["+$.str("保留2")+"]";		 
			if((this.getCsmMask() & 4 ) == 4)
				strValue+="["+$.str("保留4")+"]";		 
			if((this.getCsmMask() & 8 ) == 8)
				strValue+="["+$.str("保留8")+"]";		 
		}
	 	 return strValue;
	}
	/**
	* 程序掩码    1:已回流支付宝 2:保留2 4:保留4 8:保留8  (程序标记)技术人员使用的标记，按位运算  
	**/
	public void setCsmMask(Long csmMask){
		this.csmMask = csmMask;
		this.setSeted(F.csmMask);
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getCsmRemark(){
		return this.csmRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCsmRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsmRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setCsmRemark(String csmRemark){
		this.csmRemark = csmRemark;
		this.setSeted(F.csmRemark);
	}
	/*******************************手机认证**********************************/	
	/**
	* 手机认证    0:未认证 1:已认证 2:等待认证 3:认证失败    
	**/
	public Short getCsmVMobile(){
		return this.csmVMobile;
	}
	/**
	* 获取手机认证格式化(toString)
	**/
	public String getCsmVMobile$(){
		String strValue="";
		 if($.equals($.str(this.getCsmVMobile()),"0"))
			strValue=$.str("未认证");		 
		 if($.equals($.str(this.getCsmVMobile()),"1"))
			strValue=$.str("已认证");		 
		 if($.equals($.str(this.getCsmVMobile()),"2"))
			strValue=$.str("等待认证");		 
		 if($.equals($.str(this.getCsmVMobile()),"3"))
			strValue=$.str("认证失败");		 
	 	 return strValue;
	}
	/**
	* 手机认证    0:未认证 1:已认证 2:等待认证 3:认证失败    
	**/
	public void setCsmVMobile(Short csmVMobile){
		this.csmVMobile = csmVMobile;
		this.setSeted(F.csmVMobile);
	}
	/*******************************邮箱认证**********************************/	
	/**
	* 邮箱认证    0:未认证 1:已认证 2:等待认证 3:认证失败    
	**/
	public Short getCsmVEmail(){
		return this.csmVEmail;
	}
	/**
	* 获取邮箱认证格式化(toString)
	**/
	public String getCsmVEmail$(){
		String strValue="";
		 if($.equals($.str(this.getCsmVEmail()),"0"))
			strValue=$.str("未认证");		 
		 if($.equals($.str(this.getCsmVEmail()),"1"))
			strValue=$.str("已认证");		 
		 if($.equals($.str(this.getCsmVEmail()),"2"))
			strValue=$.str("等待认证");		 
		 if($.equals($.str(this.getCsmVEmail()),"3"))
			strValue=$.str("认证失败");		 
	 	 return strValue;
	}
	/**
	* 邮箱认证    0:未认证 1:已认证 2:等待认证 3:认证失败    
	**/
	public void setCsmVEmail(Short csmVEmail){
		this.csmVEmail = csmVEmail;
		this.setSeted(F.csmVEmail);
	}
	/*******************************实名认证**********************************/	
	/**
	* 实名认证    0:未认证 1:已认证 2:等待认证 3:认证失败    
	**/
	public Short getCsmVReal(){
	    if(this.csmVReal == null)
            return 0;
		return this.csmVReal;
	}
	/**
	* 获取实名认证格式化(toString)
	**/
	public String getCsmVReal$(){
		String strValue="";
		 if($.equals($.str(this.getCsmVReal()),"0"))
			strValue=$.str("未认证");		 
		 if($.equals($.str(this.getCsmVReal()),"1"))
			strValue=$.str("已认证");		 
		 if($.equals($.str(this.getCsmVReal()),"2"))
			strValue=$.str("等待认证");		 
		 if($.equals($.str(this.getCsmVReal()),"3"))
			strValue=$.str("认证失败");		 
	 	 return strValue;
	}
	/**
	* 实名认证    0:未认证 1:已认证 2:等待认证 3:认证失败    
	**/
	public void setCsmVReal(Short csmVReal){
		this.csmVReal = csmVReal;
		this.setSeted(F.csmVReal);
	}
	/*******************************驾驶认证**********************************/	
	/**
	* 驾驶认证    0:未认证 1:已认证 2:等待认证 3:认证失败    
	**/
	public Short getCsmVDrive(){
	    if(this.csmVDrive == null)
            return 0;
		return this.csmVDrive;
	}
	/**
	* 获取驾驶认证格式化(toString)
	**/
	public String getCsmVDrive$(){
		String strValue="";
		 if($.equals($.str(this.getCsmVDrive()),"0"))
			strValue=$.str("未认证");		 
		 if($.equals($.str(this.getCsmVDrive()),"1"))
			strValue=$.str("已认证");		 
		 if($.equals($.str(this.getCsmVDrive()),"2"))
			strValue=$.str("等待认证");		 
		 if($.equals($.str(this.getCsmVDrive()),"3"))
			strValue=$.str("认证失败");		 
	 	 return strValue;
	}
	/**
	* 驾驶认证    0:未认证 1:已认证 2:等待认证 3:认证失败    
	**/
	public void setCsmVDrive(Short csmVDrive){
		this.csmVDrive = csmVDrive;
		this.setSeted(F.csmVDrive);
	}
    
	/*******************************可用状态**********************************/	
	/**
	* 可用状态 [非空]   1:正常 0:禁用  -1:黑名单    
	**/
	public Short getCsmStatus(){
		return this.csmStatus;
	}
	/**
	* 获取可用状态格式化(toString)
	**/
	public String getCsmStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsmStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsmStatus()),"0"))
			strValue=$.str("禁用");		 
	 	 return strValue;
	}
	/**
	* 可用状态 [非空]   1:正常 0:禁用  -1:黑名单    
	**/
	public void setCsmStatus(Short csmStatus){
		this.csmStatus = csmStatus;
		this.setSeted(F.csmStatus);
	}
	
	/*******************************线下认证**********************************/ 
    /**
    * 线下认证    0:未认证 1:已认证 2:等待认证 3:认证失败    
    **/
    public Short getCsmVOffline(){
        if(this.csmVOffline == null)
            return 0;
        return this.csmVOffline;
    }
    /**
    * 获取线下认证格式化(toString)
    **/
    public String getCsmVOffline$(){
        String strValue="";
         if($.equals($.str(this.getCsmVOffline()),"0"))
            strValue=$.str("未认证");       
         if($.equals($.str(this.getCsmVOffline()),"1"))
            strValue=$.str("已认证");       
         if($.equals($.str(this.getCsmVOffline()),"2"))
            strValue=$.str("等待认证");      
         if($.equals($.str(this.getCsmVOffline()),"3"))
            strValue=$.str("认证失败");      
         return strValue;
    }
    /**
    * 线下认证    0:未认证 1:已认证 2:等待认证 3:认证失败    
    **/
    public void setCsmVOffline(Short csmVOffline){
        this.csmVOffline = csmVOffline;
        this.setSeted(F.csmVOffline);
    }
    
    
    /*******************************工作认证**********************************/ 
    /**
    * 工作认证    0:未认证 1:已认证 2:等待认证 3:认证失败    
    **/
    public Short getCsmVWork(){
        if(this.csmVWork == null)
            return 0;
        return this.csmVWork;
    }
    /**
    * 获取工作认证格式化(toString)
    **/
    public String getCsmVWork$(){
        String strValue="";
         if($.equals($.str(this.getCsmVWork()),"0"))
            strValue=$.str("未认证");       
         if($.equals($.str(this.getCsmVWork()),"1"))
            strValue=$.str("已认证");       
         if($.equals($.str(this.getCsmVWork()),"2"))
            strValue=$.str("等待认证");      
         if($.equals($.str(this.getCsmVWork()),"3"))
            strValue=$.str("认证失败");      
         return strValue;
    }
    /**
    * 工作认证    0:未认证 1:已认证 2:等待认证 3:认证失败    
    **/
    public void setCsmVWork(Short csmVWork){
        this.csmVWork = csmVWork;
        this.setSeted(F.csmVWork);
    }
    
    public String getCsmLockReason() {
        return csmLockReason;
    }

    public void setCsmLockReason(String csmLockReason) {
        this.csmLockReason = csmLockReason;
        this.setSeted(F.csmLockReason);
    }

    public String getCsmVOfflineCode() {
        return csmVOfflineCode;
    }

    public void setCsmVOfflineCode(String csmVOfflineCode) {
        this.csmVOfflineCode = csmVOfflineCode;
        this.setSeted(F.csmVOfflineCode);
    }
    
    public void setVstatus(Short vstatus) {
        this.vstatus = vstatus;
    }
    
    public short getVstatus() {
        if (this.getCsmVDrive() == MemberRecStatus.REC_FAIL || this.getCsmVReal() == MemberRecStatus.REC_FAIL || this.getCsmVWork() == MemberRecStatus.REC_FAIL
                || this.getCsmVOffline() == MemberRecStatus.REC_FAIL) {
            return MemberRecStatus.REC_FAIL;//四项认证存在认证失败
        }else if(this.getCsmVDrive() == MemberRecStatus.REC_PASS && this.getCsmVReal() == MemberRecStatus.REC_PASS && this.getCsmVWork() == MemberRecStatus.REC_PASS
                && this.getCsmVOffline() == MemberRecStatus.REC_PASS) {
            return MemberRecStatus.REC_PASS;//四项认证全部通过
        }else if(this.getCsmVDrive() == MemberRecStatus.NEVER_REC || this.getCsmVReal() == MemberRecStatus.NEVER_REC || this.getCsmVWork() == MemberRecStatus.NEVER_REC
                || this.getCsmVOffline() == MemberRecStatus.NEVER_REC) {
            return MemberRecStatus.NEVER_REC;//四项认证存在未认证
        }else {
            return MemberRecStatus.WAIT_CHECK;//等待认证
        }
    }
    
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

    /**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsMember.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMember.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMember.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsMember.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsMember.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMember.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMember.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsMember.this);
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
		public M csmId(Object csmId){this.put("csmId", csmId);return this;};
	 	/** and csm_id is null */
 		public M csmIdNull(){if(this.get("csmIdNot")==null)this.put("csmIdNot", "");this.put("csmId", null);return this;};
 		/** not .... */
 		public M csmIdNot(){this.put("csmIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csmHost(Object csmHost){this.put("csmHost", csmHost);return this;};
	 	/** and csm_host is null */
 		public M csmHostNull(){if(this.get("csmHostNot")==null)this.put("csmHostNot", "");this.put("csmHost", null);return this;};
 		/** not .... */
 		public M csmHostNot(){this.put("csmHostNot", "not");return this;};
		/** 用户名 [非空]    如果由第三方平台注册，不同类型加前缀   **/
		public M csmUsername(Object csmUsername){this.put("csmUsername", csmUsername);return this;};
	 	/** and csm_username is null */
 		public M csmUsernameNull(){if(this.get("csmUsernameNot")==null)this.put("csmUsernameNot", "");this.put("csmUsername", null);return this;};
 		/** not .... */
 		public M csmUsernameNot(){this.put("csmUsernameNot", "not");return this;};
		/** 帐号密码 [非空]       **/
		public M csmPassword(Object csmPassword){this.put("csmPassword", csmPassword);return this;};
	 	/** and csm_password is null */
 		public M csmPasswordNull(){if(this.get("csmPasswordNot")==null)this.put("csmPasswordNot", "");this.put("csmPassword", null);return this;};
 		/** not .... */
 		public M csmPasswordNot(){this.put("csmPasswordNot", "not");return this;};
		/** 所属组织  [CsMemberGroup]      **/
		public M csmGroup(Object csmGroup){this.put("csmGroup", csmGroup);return this;};
	 	/** and csm_group is null */
 		public M csmGroupNull(){if(this.get("csmGroupNot")==null)this.put("csmGroupNot", "");this.put("csmGroup", null);return this;};
 		/** not .... */
 		public M csmGroupNot(){this.put("csmGroupNot", "not");return this;};
		public M csmGroup$on(CsMemberGroup.M value){
			this.put("CsMemberGroup", value);
			this.put("csmGroup$on", value);
			return this;
		};	
		/** 余额 [非空]       **/
		public M csmMoney(Object csmMoney){this.put("csmMoney", csmMoney);return this;};
	 	/** and csm_money is null */
 		public M csmMoneyNull(){if(this.get("csmMoneyNot")==null)this.put("csmMoneyNot", "");this.put("csmMoney", null);return this;};
 		/** not .... */
 		public M csmMoneyNot(){this.put("csmMoneyNot", "not");return this;};
		/** and csm_money >= ? */
		public M csmMoneyMin(Object min){this.put("csmMoneyMin", min);return this;};
		/** and csm_money <= ? */
		public M csmMoneyMax(Object max){this.put("csmMoneyMax", max);return this;};
		/** 现金券 [非空]       **/
		public M csmCoupon(Object csmCoupon){this.put("csmCoupon", csmCoupon);return this;};
	 	/** and csm_coupon is null */
 		public M csmCouponNull(){if(this.get("csmCouponNot")==null)this.put("csmCouponNot", "");this.put("csmCoupon", null);return this;};
 		/** not .... */
 		public M csmCouponNot(){this.put("csmCouponNot", "not");return this;};
		/** and csm_coupon >= ? */
		public M csmCouponMin(Object min){this.put("csmCouponMin", min);return this;};
		/** and csm_coupon <= ? */
		public M csmCouponMax(Object max){this.put("csmCouponMax", max);return this;};
		/** 积分 [非空]       **/
		public M csmIntegral(Object csmIntegral){this.put("csmIntegral", csmIntegral);return this;};
	 	/** and csm_integral is null */
 		public M csmIntegralNull(){if(this.get("csmIntegralNot")==null)this.put("csmIntegralNot", "");this.put("csmIntegral", null);return this;};
 		/** not .... */
 		public M csmIntegralNot(){this.put("csmIntegralNot", "not");return this;};
		/** and csm_integral >= ? */
		public M csmIntegralMin(Object min){this.put("csmIntegralMin", min);return this;};
		/** and csm_integral <= ? */
		public M csmIntegralMax(Object max){this.put("csmIntegralMax", max);return this;};
		/** 成长值 [非空]       **/
		public M csmGrow(Object csmGrow){this.put("csmGrow", csmGrow);return this;};
	 	/** and csm_grow is null */
 		public M csmGrowNull(){if(this.get("csmGrowNot")==null)this.put("csmGrowNot", "");this.put("csmGrow", null);return this;};
 		/** not .... */
 		public M csmGrowNot(){this.put("csmGrowNot", "not");return this;};
		/** and csm_grow >= ? */
		public M csmGrowMin(Object min){this.put("csmGrowMin", min);return this;};
		/** and csm_grow <= ? */
		public M csmGrowMax(Object max){this.put("csmGrowMax", max);return this;};
		/** 会员等级 [非空]       **/
		public M csmGrade(Object csmGrade){this.put("csmGrade", csmGrade);return this;};
	 	/** and csm_grade is null */
 		public M csmGradeNull(){if(this.get("csmGradeNot")==null)this.put("csmGradeNot", "");this.put("csmGrade", null);return this;};
 		/** not .... */
 		public M csmGradeNot(){this.put("csmGradeNot", "not");return this;};
		/** and csm_grade >= ? */
		public M csmGradeMin(Object min){this.put("csmGradeMin", min);return this;};
		/** and csm_grade <= ? */
		public M csmGradeMax(Object max){this.put("csmGradeMax", max);return this;};
		/** 折扣 [非空]    0到1,比如0.75表示七五折   **/
		public M csmRebate(Object csmRebate){this.put("csmRebate", csmRebate);return this;};
	 	/** and csm_rebate is null */
 		public M csmRebateNull(){if(this.get("csmRebateNot")==null)this.put("csmRebateNot", "");this.put("csmRebate", null);return this;};
 		/** not .... */
 		public M csmRebateNot(){this.put("csmRebateNot", "not");return this;};
		/** and csm_rebate >= ? */
		public M csmRebateMin(Object min){this.put("csmRebateMin", min);return this;};
		/** and csm_rebate <= ? */
		public M csmRebateMax(Object max){this.put("csmRebateMax", max);return this;};
		/** 计算营收    0:计入营收 1:不计营收  表示该帐号支付的订单是否为营收项   **/
		public M csmNotRevenue(Object csmNotRevenue){this.put("csmNotRevenue", csmNotRevenue);return this;};
	 	/** and csm_not_revenue is null */
 		public M csmNotRevenueNull(){if(this.get("csmNotRevenueNot")==null)this.put("csmNotRevenueNot", "");this.put("csmNotRevenue", null);return this;};
 		/** not .... */
 		public M csmNotRevenueNot(){this.put("csmNotRevenueNot", "not");return this;};
		/** 微信帐号标识        **/
		public M csmWeixinFlag(Object csmWeixinFlag){this.put("csmWeixinFlag", csmWeixinFlag);return this;};
	 	/** and csm_weixin_flag is null */
 		public M csmWeixinFlagNull(){if(this.get("csmWeixinFlagNot")==null)this.put("csmWeixinFlagNot", "");this.put("csmWeixinFlag", null);return this;};
 		/** not .... */
 		public M csmWeixinFlagNot(){this.put("csmWeixinFlagNot", "not");return this;};
		/** 支付宝帐号标识        **/
		public M csmAlipayFlag(Object csmAlipayFlag){this.put("csmAlipayFlag", csmAlipayFlag);return this;};
	 	/** and csm_alipay_flag is null */
 		public M csmAlipayFlagNull(){if(this.get("csmAlipayFlagNot")==null)this.put("csmAlipayFlagNot", "");this.put("csmAlipayFlag", null);return this;};
 		/** not .... */
 		public M csmAlipayFlagNot(){this.put("csmAlipayFlagNot", "not");return this;};
		/** 头像    200:width 200:height     **/
		public M csmHeader(Object csmHeader){this.put("csmHeader", csmHeader);return this;};
	 	/** and csm_header is null */
 		public M csmHeaderNull(){if(this.get("csmHeaderNot")==null)this.put("csmHeaderNot", "");this.put("csmHeader", null);return this;};
 		/** not .... */
 		public M csmHeaderNot(){this.put("csmHeaderNot", "not");return this;};
		/** 邮箱        **/
		public M csmEmail(Object csmEmail){this.put("csmEmail", csmEmail);return this;};
	 	/** and csm_email is null */
 		public M csmEmailNull(){if(this.get("csmEmailNot")==null)this.put("csmEmailNot", "");this.put("csmEmail", null);return this;};
 		/** not .... */
 		public M csmEmailNot(){this.put("csmEmailNot", "not");return this;};
		/** 手机        **/
		public M csmMobile(Object csmMobile){this.put("csmMobile", csmMobile);return this;};
	 	/** and csm_mobile is null */
 		public M csmMobileNull(){if(this.get("csmMobileNot")==null)this.put("csmMobileNot", "");this.put("csmMobile", null);return this;};
 		/** not .... */
 		public M csmMobileNot(){this.put("csmMobileNot", "not");return this;};
		/** EV卡  [CsEvCard]      **/
		public M csmEvcard(Object csmEvcard){this.put("csmEvcard", csmEvcard);return this;};
	 	/** and csm_evcard is null */
 		public M csmEvcardNull(){if(this.get("csmEvcardNot")==null)this.put("csmEvcardNot", "");this.put("csmEvcard", null);return this;};
 		/** not .... */
 		public M csmEvcardNot(){this.put("csmEvcardNot", "not");return this;};
		public M csmEvcard$on(CsEvCard.M value){
			this.put("CsEvCard", value);
			this.put("csmEvcard$on", value);
			return this;
		};	
		/** 快递单号        **/
		public M csmExpress(Object csmExpress){this.put("csmExpress", csmExpress);return this;};
	 	/** and csm_express is null */
 		public M csmExpressNull(){if(this.get("csmExpressNot")==null)this.put("csmExpressNot", "");this.put("csmExpress", null);return this;};
 		/** not .... */
 		public M csmExpressNot(){this.put("csmExpressNot", "not");return this;};
		/** 邀请码        **/
		public M csmTemp(Object csmTemp){this.put("csmTemp", csmTemp);return this;};
	 	/** and csm_temp is null */
 		public M csmTempNull(){if(this.get("csmTempNot")==null)this.put("csmTempNot", "");this.put("csmTemp", null);return this;};
 		/** not .... */
 		public M csmTempNot(){this.put("csmTempNot", "not");return this;};
		/** 真实姓名        **/
		public M csmName(Object csmName){this.put("csmName", csmName);return this;};
	 	/** and csm_name is null */
 		public M csmNameNull(){if(this.get("csmNameNot")==null)this.put("csmNameNot", "");this.put("csmName", null);return this;};
 		/** not .... */
 		public M csmNameNot(){this.put("csmNameNot", "not");return this;};
		/** 详细信息  [CsMemberInfo]      **/
		public M csmInfo(Object csmInfo){this.put("csmInfo", csmInfo);return this;};
	 	/** and csm_info is null */
 		public M csmInfoNull(){if(this.get("csmInfoNot")==null)this.put("csmInfoNot", "");this.put("csmInfo", null);return this;};
 		/** not .... */
 		public M csmInfoNot(){this.put("csmInfoNot", "not");return this;};
		public M csmInfo$on(CsMemberInfo.M value){
			this.put("CsMemberInfo", value);
			this.put("csmInfo$on", value);
			return this;
		};	
		/** 默认网点  [CsOutlets]     ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
		public M csmOutlets(Object csmOutlets){this.put("csmOutlets", csmOutlets);return this;};
	 	/** and csm_outlets is null */
 		public M csmOutletsNull(){if(this.get("csmOutletsNot")==null)this.put("csmOutletsNot", "");this.put("csmOutlets", null);return this;};
 		/** not .... */
 		public M csmOutletsNot(){this.put("csmOutletsNot", "not");return this;};
		public M csmOutlets$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("csmOutlets$on", value);
			return this;
		};	
		/** VIP状态    0:不是 1:是     **/
		public M csmIsVip(Object csmIsVip){this.put("csmIsVip", csmIsVip);return this;};
	 	/** and csm_is_vip is null */
 		public M csmIsVipNull(){if(this.get("csmIsVipNot")==null)this.put("csmIsVipNot", "");this.put("csmIsVip", null);return this;};
 		/** not .... */
 		public M csmIsVipNot(){this.put("csmIsVipNot", "not");return this;};
		/** VIP开始时间        **/
		public M csmVipStart(Object csmVipStart){this.put("csmVipStart", csmVipStart);return this;};
	 	/** and csm_vip_start is null */
 		public M csmVipStartNull(){if(this.get("csmVipStartNot")==null)this.put("csmVipStartNot", "");this.put("csmVipStart", null);return this;};
 		/** not .... */
 		public M csmVipStartNot(){this.put("csmVipStartNot", "not");return this;};
 		/** and csm_vip_start >= ? */
 		public M csmVipStartStart(Object start){this.put("csmVipStartStart", start);return this;};			
 		/** and csm_vip_start <= ? */
 		public M csmVipStartEnd(Object end){this.put("csmVipStartEnd", end);return this;};
		/** VIP结束时间        **/
		public M csmVipEnd(Object csmVipEnd){this.put("csmVipEnd", csmVipEnd);return this;};
	 	/** and csm_vip_end is null */
 		public M csmVipEndNull(){if(this.get("csmVipEndNot")==null)this.put("csmVipEndNot", "");this.put("csmVipEnd", null);return this;};
 		/** not .... */
 		public M csmVipEndNot(){this.put("csmVipEndNot", "not");return this;};
 		/** and csm_vip_end >= ? */
 		public M csmVipEndStart(Object start){this.put("csmVipEndStart", start);return this;};			
 		/** and csm_vip_end <= ? */
 		public M csmVipEndEnd(Object end){this.put("csmVipEndEnd", end);return this;};
		/** 更新时间        **/
		public M csmUpdateTime(Object csmUpdateTime){this.put("csmUpdateTime", csmUpdateTime);return this;};
	 	/** and csm_update_time is null */
 		public M csmUpdateTimeNull(){if(this.get("csmUpdateTimeNot")==null)this.put("csmUpdateTimeNot", "");this.put("csmUpdateTime", null);return this;};
 		/** not .... */
 		public M csmUpdateTimeNot(){this.put("csmUpdateTimeNot", "not");return this;};
 		/** and csm_update_time >= ? */
 		public M csmUpdateTimeStart(Object start){this.put("csmUpdateTimeStart", start);return this;};			
 		/** and csm_update_time <= ? */
 		public M csmUpdateTimeEnd(Object end){this.put("csmUpdateTimeEnd", end);return this;};
		/** 注册时间 [非空]       **/
		public M csmAddTime(Object csmAddTime){this.put("csmAddTime", csmAddTime);return this;};
	 	/** and csm_add_time is null */
 		public M csmAddTimeNull(){if(this.get("csmAddTimeNot")==null)this.put("csmAddTimeNot", "");this.put("csmAddTime", null);return this;};
 		/** not .... */
 		public M csmAddTimeNot(){this.put("csmAddTimeNot", "not");return this;};
 		/** and csm_add_time >= ? */
 		public M csmAddTimeStart(Object start){this.put("csmAddTimeStart", start);return this;};			
 		/** and csm_add_time <= ? */
 		public M csmAddTimeEnd(Object end){this.put("csmAddTimeEnd", end);return this;};
		/** 最后登录        **/
		public M csmLastTime(Object csmLastTime){this.put("csmLastTime", csmLastTime);return this;};
	 	/** and csm_last_time is null */
 		public M csmLastTimeNull(){if(this.get("csmLastTimeNot")==null)this.put("csmLastTimeNot", "");this.put("csmLastTime", null);return this;};
 		/** not .... */
 		public M csmLastTimeNot(){this.put("csmLastTimeNot", "not");return this;};
 		/** and csm_last_time >= ? */
 		public M csmLastTimeStart(Object start){this.put("csmLastTimeStart", start);return this;};			
 		/** and csm_last_time <= ? */
 		public M csmLastTimeEnd(Object end){this.put("csmLastTimeEnd", end);return this;};
		/** 最后登录IP        **/
		public M csmLastIp(Object csmLastIp){this.put("csmLastIp", csmLastIp);return this;};
	 	/** and csm_last_ip is null */
 		public M csmLastIpNull(){if(this.get("csmLastIpNot")==null)this.put("csmLastIpNot", "");this.put("csmLastIp", null);return this;};
 		/** not .... */
 		public M csmLastIpNot(){this.put("csmLastIpNot", "not");return this;};
		/** 登录次数        **/
		public M csmLoginS(Object csmLoginS){this.put("csmLoginS", csmLoginS);return this;};
	 	/** and csm_login_s is null */
 		public M csmLoginSNull(){if(this.get("csmLoginSNot")==null)this.put("csmLoginSNot", "");this.put("csmLoginS", null);return this;};
 		/** not .... */
 		public M csmLoginSNot(){this.put("csmLoginSNot", "not");return this;};
		/** and csm_login_s >= ? */
		public M csmLoginSMin(Object min){this.put("csmLoginSMin", min);return this;};
		/** and csm_login_s <= ? */
		public M csmLoginSMax(Object max){this.put("csmLoginSMax", max);return this;};
		/** 首次用车        **/
		public M csmFirstUse(Object csmFirstUse){this.put("csmFirstUse", csmFirstUse);return this;};
	 	/** and csm_first_use is null */
 		public M csmFirstUseNull(){if(this.get("csmFirstUseNot")==null)this.put("csmFirstUseNot", "");this.put("csmFirstUse", null);return this;};
 		/** not .... */
 		public M csmFirstUseNot(){this.put("csmFirstUseNot", "not");return this;};
 		/** and csm_first_use >= ? */
 		public M csmFirstUseStart(Object start){this.put("csmFirstUseStart", start);return this;};			
 		/** and csm_first_use <= ? */
 		public M csmFirstUseEnd(Object end){this.put("csmFirstUseEnd", end);return this;};
		/** 最后使用        **/
		public M csmLastUse(Object csmLastUse){this.put("csmLastUse", csmLastUse);return this;};
	 	/** and csm_last_use is null */
 		public M csmLastUseNull(){if(this.get("csmLastUseNot")==null)this.put("csmLastUseNot", "");this.put("csmLastUse", null);return this;};
 		/** not .... */
 		public M csmLastUseNot(){this.put("csmLastUseNot", "not");return this;};
 		/** and csm_last_use >= ? */
 		public M csmLastUseStart(Object start){this.put("csmLastUseStart", start);return this;};			
 		/** and csm_last_use <= ? */
 		public M csmLastUseEnd(Object end){this.put("csmLastUseEnd", end);return this;};
		/** 累计充值        **/
		public M csmAllRecharge(Object csmAllRecharge){this.put("csmAllRecharge", csmAllRecharge);return this;};
	 	/** and csm_all_recharge is null */
 		public M csmAllRechargeNull(){if(this.get("csmAllRechargeNot")==null)this.put("csmAllRechargeNot", "");this.put("csmAllRecharge", null);return this;};
 		/** not .... */
 		public M csmAllRechargeNot(){this.put("csmAllRechargeNot", "not");return this;};
		/** and csm_all_recharge >= ? */
		public M csmAllRechargeMin(Object min){this.put("csmAllRechargeMin", min);return this;};
		/** and csm_all_recharge <= ? */
		public M csmAllRechargeMax(Object max){this.put("csmAllRechargeMax", max);return this;};
		/** 累计订单        **/
		public M csmAllOrderS(Object csmAllOrderS){this.put("csmAllOrderS", csmAllOrderS);return this;};
	 	/** and csm_all_order_s is null */
 		public M csmAllOrderSNull(){if(this.get("csmAllOrderSNot")==null)this.put("csmAllOrderSNot", "");this.put("csmAllOrderS", null);return this;};
 		/** not .... */
 		public M csmAllOrderSNot(){this.put("csmAllOrderSNot", "not");return this;};
		/** and csm_all_order_s >= ? */
		public M csmAllOrderSMin(Object min){this.put("csmAllOrderSMin", min);return this;};
		/** and csm_all_order_s <= ? */
		public M csmAllOrderSMax(Object max){this.put("csmAllOrderSMax", max);return this;};
		/** 累计用车小时        **/
		public M csmAllUseTime(Object csmAllUseTime){this.put("csmAllUseTime", csmAllUseTime);return this;};
	 	/** and csm_all_use_time is null */
 		public M csmAllUseTimeNull(){if(this.get("csmAllUseTimeNot")==null)this.put("csmAllUseTimeNot", "");this.put("csmAllUseTime", null);return this;};
 		/** not .... */
 		public M csmAllUseTimeNot(){this.put("csmAllUseTimeNot", "not");return this;};
		/** and csm_all_use_time >= ? */
		public M csmAllUseTimeMin(Object min){this.put("csmAllUseTimeMin", min);return this;};
		/** and csm_all_use_time <= ? */
		public M csmAllUseTimeMax(Object max){this.put("csmAllUseTimeMax", max);return this;};
		/** 违章次数        **/
		public M csmAllViolatS(Object csmAllViolatS){this.put("csmAllViolatS", csmAllViolatS);return this;};
	 	/** and csm_all_violat_s is null */
 		public M csmAllViolatSNull(){if(this.get("csmAllViolatSNot")==null)this.put("csmAllViolatSNot", "");this.put("csmAllViolatS", null);return this;};
 		/** not .... */
 		public M csmAllViolatSNot(){this.put("csmAllViolatSNot", "not");return this;};
		/** and csm_all_violat_s >= ? */
		public M csmAllViolatSMin(Object min){this.put("csmAllViolatSMin", min);return this;};
		/** and csm_all_violat_s <= ? */
		public M csmAllViolatSMax(Object max){this.put("csmAllViolatSMax", max);return this;};
		/** 未处理违章        **/
		public M csmUnViolatS(Object csmUnViolatS){this.put("csmUnViolatS", csmUnViolatS);return this;};
	 	/** and csm_un_violat_s is null */
 		public M csmUnViolatSNull(){if(this.get("csmUnViolatSNot")==null)this.put("csmUnViolatSNot", "");this.put("csmUnViolatS", null);return this;};
 		/** not .... */
 		public M csmUnViolatSNot(){this.put("csmUnViolatSNot", "not");return this;};
		/** and csm_un_violat_s >= ? */
		public M csmUnViolatSMin(Object min){this.put("csmUnViolatSMin", min);return this;};
		/** and csm_un_violat_s <= ? */
		public M csmUnViolatSMax(Object max){this.put("csmUnViolatSMax", max);return this;};
		/** 事故次数        **/
		public M csmAllTroubleS(Object csmAllTroubleS){this.put("csmAllTroubleS", csmAllTroubleS);return this;};
	 	/** and csm_all_trouble_s is null */
 		public M csmAllTroubleSNull(){if(this.get("csmAllTroubleSNot")==null)this.put("csmAllTroubleSNot", "");this.put("csmAllTroubleS", null);return this;};
 		/** not .... */
 		public M csmAllTroubleSNot(){this.put("csmAllTroubleSNot", "not");return this;};
		/** and csm_all_trouble_s >= ? */
		public M csmAllTroubleSMin(Object min){this.put("csmAllTroubleSMin", min);return this;};
		/** and csm_all_trouble_s <= ? */
		public M csmAllTroubleSMax(Object max){this.put("csmAllTroubleSMax", max);return this;};
		/** 首次充值        **/
		public M csmFirstRecharge(Object csmFirstRecharge){this.put("csmFirstRecharge", csmFirstRecharge);return this;};
	 	/** and csm_first_recharge is null */
 		public M csmFirstRechargeNull(){if(this.get("csmFirstRechargeNot")==null)this.put("csmFirstRechargeNot", "");this.put("csmFirstRecharge", null);return this;};
 		/** not .... */
 		public M csmFirstRechargeNot(){this.put("csmFirstRechargeNot", "not");return this;};
 		/** and csm_first_recharge >= ? */
 		public M csmFirstRechargeStart(Object start){this.put("csmFirstRechargeStart", start);return this;};			
 		/** and csm_first_recharge <= ? */
 		public M csmFirstRechargeEnd(Object end){this.put("csmFirstRechargeEnd", end);return this;};
		/** 最后退款        **/
		public M csmRefundTime(Object csmRefundTime){this.put("csmRefundTime", csmRefundTime);return this;};
	 	/** and csm_refund_time is null */
 		public M csmRefundTimeNull(){if(this.get("csmRefundTimeNot")==null)this.put("csmRefundTimeNot", "");this.put("csmRefundTime", null);return this;};
 		/** not .... */
 		public M csmRefundTimeNot(){this.put("csmRefundTimeNot", "not");return this;};
 		/** and csm_refund_time >= ? */
 		public M csmRefundTimeStart(Object start){this.put("csmRefundTimeStart", start);return this;};			
 		/** and csm_refund_time <= ? */
 		public M csmRefundTimeEnd(Object end){this.put("csmRefundTimeEnd", end);return this;};
		/** 注册IP        **/
		public M csmRegistIp(Object csmRegistIp){this.put("csmRegistIp", csmRegistIp);return this;};
	 	/** and csm_regist_ip is null */
 		public M csmRegistIpNull(){if(this.get("csmRegistIpNot")==null)this.put("csmRegistIpNot", "");this.put("csmRegistIp", null);return this;};
 		/** not .... */
 		public M csmRegistIpNot(){this.put("csmRegistIpNot", "not");return this;};
		/** 添加人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csmAdder(Object csmAdder){this.put("csmAdder", csmAdder);return this;};
	 	/** and csm_adder is null */
 		public M csmAdderNull(){if(this.get("csmAdderNot")==null)this.put("csmAdderNot", "");this.put("csmAdder", null);return this;};
 		/** not .... */
 		public M csmAdderNot(){this.put("csmAdderNot", "not");return this;};
		/** 跟踪人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csmTracker(Object csmTracker){this.put("csmTracker", csmTracker);return this;};
	 	/** and csm_tracker is null */
 		public M csmTrackerNull(){if(this.get("csmTrackerNot")==null)this.put("csmTrackerNot", "");this.put("csmTracker", null);return this;};
 		/** not .... */
 		public M csmTrackerNot(){this.put("csmTrackerNot", "not");return this;};
		/** 推荐会员  [CsMember]      **/
		public M csmRefer(Object csmRefer){this.put("csmRefer", csmRefer);return this;};
	 	/** and csm_refer is null */
 		public M csmReferNull(){if(this.get("csmReferNot")==null)this.put("csmReferNot", "");this.put("csmRefer", null);return this;};
 		/** not .... */
 		public M csmReferNot(){this.put("csmReferNot", "not");return this;};
		public M csmRefer$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csmRefer$on", value);
			return this;
		};	
		/** 推荐方式    0:无 1:地址转发 2:微博分享 3:广告媒体 4:网络搜索 5:朋友推荐 6:活动推广     **/
		public M csmReferType(Object csmReferType){this.put("csmReferType", csmReferType);return this;};
	 	/** and csm_refer_type is null */
 		public M csmReferTypeNull(){if(this.get("csmReferTypeNot")==null)this.put("csmReferTypeNot", "");this.put("csmReferType", null);return this;};
 		/** not .... */
 		public M csmReferTypeNot(){this.put("csmReferTypeNot", "not");return this;};
		/** 注册平台    0:后台 1:网站 2:微信 3:APP 4:支付宝微生活     **/
		public M csmFrom(Object csmFrom){this.put("csmFrom", csmFrom);return this;};
	 	/** and csm_from is null */
 		public M csmFromNull(){if(this.get("csmFromNot")==null)this.put("csmFromNot", "");this.put("csmFrom", null);return this;};
 		/** not .... */
 		public M csmFromNot(){this.put("csmFromNot", "not");return this;};
		/** 注册源信息        **/
		public M csmSrc(Object csmSrc){this.put("csmSrc", csmSrc);return this;};
	 	/** and csm_src is null */
 		public M csmSrcNull(){if(this.get("csmSrcNot")==null)this.put("csmSrcNot", "");this.put("csmSrc", null);return this;};
 		/** not .... */
 		public M csmSrcNot(){this.put("csmSrcNot", "not");return this;};
		/** 来源渠道  [CsChannel]      **/
		public M csmChannel(Object csmChannel){this.put("csmChannel", csmChannel);return this;};
	 	/** and csm_channel is null */
 		public M csmChannelNull(){if(this.get("csmChannelNot")==null)this.put("csmChannelNot", "");this.put("csmChannel", null);return this;};
 		/** not .... */
 		public M csmChannelNot(){this.put("csmChannelNot", "not");return this;};
		public M csmChannel$on(CsChannel.M value){
			this.put("CsChannel", value);
			this.put("csmChannel$on", value);
			return this;
		};	
		/** 营销方案  [CsMarketPlan]      **/
		public M csmMarketPlan(Object csmMarketPlan){this.put("csmMarketPlan", csmMarketPlan);return this;};
	 	/** and csm_market_plan is null */
 		public M csmMarketPlanNull(){if(this.get("csmMarketPlanNot")==null)this.put("csmMarketPlanNot", "");this.put("csmMarketPlan", null);return this;};
 		/** not .... */
 		public M csmMarketPlanNot(){this.put("csmMarketPlanNot", "not");return this;};
		public M csmMarketPlan$on(CsMarketPlan.M value){
			this.put("CsMarketPlan", value);
			this.put("csmMarketPlan$on", value);
			return this;
		};	
		/** 来源活动  [CsActivity]      **/
		public M csmActivity(Object csmActivity){this.put("csmActivity", csmActivity);return this;};
	 	/** and csm_activity is null */
 		public M csmActivityNull(){if(this.get("csmActivityNot")==null)this.put("csmActivityNot", "");this.put("csmActivity", null);return this;};
 		/** not .... */
 		public M csmActivityNot(){this.put("csmActivityNot", "not");return this;};
		public M csmActivity$on(CsActivity.M value){
			this.put("CsActivity", value);
			this.put("csmActivity$on", value);
			return this;
		};	
		/** 销售人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csmSaler(Object csmSaler){this.put("csmSaler", csmSaler);return this;};
	 	/** and csm_saler is null */
 		public M csmSalerNull(){if(this.get("csmSalerNot")==null)this.put("csmSalerNot", "");this.put("csmSaler", null);return this;};
 		/** not .... */
 		public M csmSalerNot(){this.put("csmSalerNot", "not");return this;};
		/** 营销标记  [SrvProperty]  营销标识:'MEMBER_MARKET'    select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
		public M csmMarket(Object csmMarket){this.put("csmMarket", csmMarket);return this;};
	 	/** and csm_market is null */
 		public M csmMarketNull(){if(this.get("csmMarketNot")==null)this.put("csmMarketNot", "");this.put("csmMarket", null);return this;};
 		/** not .... */
 		public M csmMarketNot(){this.put("csmMarketNot", "not");return this;};
		/** 会员标签  [SrvProperty]  用户标签:'MEMBER_TAG'  (会员特性)标签用来对用户分类，在系统属性表中配置  select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
		public M csmTag(Object csmTag){this.put("csmTag", csmTag);return this;};
	 	/** and csm_tag is null */
 		public M csmTagNull(){if(this.get("csmTagNot")==null)this.put("csmTagNot", "");this.put("csmTag", null);return this;};
 		/** not .... */
 		public M csmTagNot(){this.put("csmTagNot", "not");return this;};
		/** 备忘标记  [SrvProperty]  备忘标记:'MEMBER_MARK'  (管理标记)管理人员对该会员做的备忘标记，在系统属性表中配置  select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
		public M csmMark(Object csmMark){this.put("csmMark", csmMark);return this;};
	 	/** and csm_mark is null */
 		public M csmMarkNull(){if(this.get("csmMarkNot")==null)this.put("csmMarkNot", "");this.put("csmMark", null);return this;};
 		/** not .... */
 		public M csmMarkNot(){this.put("csmMarkNot", "not");return this;};
		/** 回访标记  [CsQuestTemplate]   (回访标记)对应回访模板,勾上了表示该模板已调查或已回访  select * from cs_quest_template where 1=1 **/
		public M csmVisitFlag(Object csmVisitFlag){this.put("csmVisitFlag", csmVisitFlag);return this;};
	 	/** and csm_visit_flag is null */
 		public M csmVisitFlagNull(){if(this.get("csmVisitFlagNot")==null)this.put("csmVisitFlagNot", "");this.put("csmVisitFlag", null);return this;};
 		/** not .... */
 		public M csmVisitFlagNot(){this.put("csmVisitFlagNot", "not");return this;};
		/** 程序掩码    1:已回流支付宝 2:保留2 4:保留4 8:保留8  (程序标记)技术人员使用的标记，按位运算   **/
		public M csmMask(Object csmMask){this.put("csmMask", csmMask);return this;};
	 	/** and csm_mask is null */
 		public M csmMaskNull(){if(this.get("csmMaskNot")==null)this.put("csmMaskNot", "");this.put("csmMask", null);return this;};
 		/** not .... */
 		public M csmMaskNot(){this.put("csmMaskNot", "not");return this;};
		/** 备注        **/
		public M csmRemark(Object csmRemark){this.put("csmRemark", csmRemark);return this;};
	 	/** and csm_remark is null */
 		public M csmRemarkNull(){if(this.get("csmRemarkNot")==null)this.put("csmRemarkNot", "");this.put("csmRemark", null);return this;};
 		/** not .... */
 		public M csmRemarkNot(){this.put("csmRemarkNot", "not");return this;};
		/** 手机认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
		public M csmVMobile(Object csmVMobile){this.put("csmVMobile", csmVMobile);return this;};
	 	/** and csm_v_mobile is null */
 		public M csmVMobileNull(){if(this.get("csmVMobileNot")==null)this.put("csmVMobileNot", "");this.put("csmVMobile", null);return this;};
 		/** not .... */
 		public M csmVMobileNot(){this.put("csmVMobileNot", "not");return this;};
		/** 邮箱认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
		public M csmVEmail(Object csmVEmail){this.put("csmVEmail", csmVEmail);return this;};
	 	/** and csm_v_email is null */
 		public M csmVEmailNull(){if(this.get("csmVEmailNot")==null)this.put("csmVEmailNot", "");this.put("csmVEmail", null);return this;};
 		/** not .... */
 		public M csmVEmailNot(){this.put("csmVEmailNot", "not");return this;};
		/** 实名认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
		public M csmVReal(Object csmVReal){this.put("csmVReal", csmVReal);return this;};
	 	/** and csm_v_real is null */
 		public M csmVRealNull(){if(this.get("csmVRealNot")==null)this.put("csmVRealNot", "");this.put("csmVReal", null);return this;};
 		/** not .... */
 		public M csmVRealNot(){this.put("csmVRealNot", "not");return this;};
		/** 驾驶认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
		public M csmVDrive(Object csmVDrive){this.put("csmVDrive", csmVDrive);return this;};
	 	/** and csm_v_drive is null */
 		public M csmVDriveNull(){if(this.get("csmVDriveNot")==null)this.put("csmVDriveNot", "");this.put("csmVDrive", null);return this;};
 		/** not .... */
 		public M csmVDriveNot(){this.put("csmVDriveNot", "not");return this;};
 		
 		
 		/** 工作认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
        public M csmVWork(Object csmVWork){this.put("csmVWork", csmVWork);return this;};
        /** and csm_v_work is null */
        public M csmVWorkNull(){
            if(this.get("csmVWorkNot")==null)this.put("csmVWorkNot", "");
            this.put("csmVWork", null);return this;};
        /** not .... */
        public M csmVWorkNot(){this.put("csmVWorkNot", "not");return this;};
        
        /** 线下认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
        public M csmVOffline(Object csmVOffline){this.put("csmVOffline", csmVOffline);return this;};
        /** and csm_v_Offline is null */
        public M csmVOfflineNull(){
            if(this.get("csmVOfflineNot")==null)this.put("csmVOfflineNot", "");
            this.put("csmVOffline", null);return this;};
        /** not .... */
        public M csmVOfflineNot(){this.put("csmVOfflineNot", "not");return this;};
        /** 身份证认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
        public M csmVIdcard(Object csmVIdcard){
            this.put("csmVIdcard", csmVIdcard);return this;};
        /** and csm_v_Idcard is null */
        public M csmVIdcardNull(){
            if(this.get("csmVIdcardNot")==null)this.put("csmVIdcardNot", "");
            this.put("csmVIdcard", null);return this;};
        /** not .... */
        public M csmVIdcardNot(){this.put("csmVIdcardNot", "not");return this;};
        /** 禁用原因 **/
        public M csmLockReason(Object csmLockReason){this.put("csmLockReason", csmLockReason);return this;};
        /** and csm_lock_reason is null */
        public M csmLockReasonNull(){if(this.get("csmLockReasonNot")==null)this.put("csmLockReasonNot", "");this.put("csmLockReason", null);return this;};
        /** not .... */
        public M csmLockReasonNot(){this.put("csmLockReasonNot", "not");return this;};
        /** 线下认证机器编码 **/
        public M csmVOfflineCode(Object csmVOfflineCode){this.put("csmVOfflineCode", csmVOfflineCode);return this;};
        /** and csm_v_offline_code is null */
        public M csmVOfflineCodeNull(){if(this.get("csmVOfflineCodeNot")==null)this.put("csmVOfflineCodeNot", "");this.put("csmVOfflineCode", null);return this;};
        /** not .... */
        public M csmVOfflineCodeNot(){this.put("csmVOfflineCodeNot", "not");return this;};
        
		/** 可用状态 [非空]   1:正常 0:禁用  -1:黑名单     **/
		public M csmStatus(Object csmStatus){this.put("csmStatus", csmStatus);return this;};
	 	/** and csm_status is null */
 		public M csmStatusNull(){if(this.get("csmStatusNot")==null)this.put("csmStatusNot", "");this.put("csmStatus", null);return this;};
 		/** not .... */
 		public M csmStatusNot(){this.put("csmStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有会员帐号 **/
		public @api List<CsMember> list(Integer size){
			return getCsMemberList(this,size);
		}
		/** 获取会员帐号分页 **/
		public @api Page<CsMember> page(int page,int size){
			return getCsMemberPage(page, size , this);
		}
		/** 根据查询条件取会员帐号 **/
		public @api CsMember get(){
			return getCsMember(this);
		}
		/** 获取会员帐号数 **/
		public @api Long count(){
			return getCsMemberCount(this);
		}
		/** 获取会员帐号表达式 **/
		public @api <T> T eval(String strEval){
			return getCsMemberEval(strEval,this);
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
			updateCsMember(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csmId="csmId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csmHost="csmHost";
		/** 用户名 [非空]    如果由第三方平台注册，不同类型加前缀   **/
		public final static @type(String.class) @like String csmUsername="csmUsername";
		/** 帐号密码 [非空]       **/
		public final static @type(String.class) @like String csmPassword="csmPassword";
		/** 所属组织  [CsMemberGroup]      **/
		public final static @type(Long.class)  String csmGroup="csmGroup";
		/** 余额 [非空]       **/
		public final static @type(Double.class)  String csmMoney="csmMoney";
		/** and csm_money >= ? */
		public final static @type(Double.class) String csmMoneyMin="csmMoneyMin";
		/** and csm_money <= ? */
		public final static @type(Double.class) String csmMoneyMax="csmMoneyMax";
		/** 现金券 [非空]       **/
		public final static @type(Double.class)  String csmCoupon="csmCoupon";
		/** and csm_coupon >= ? */
		public final static @type(Double.class) String csmCouponMin="csmCouponMin";
		/** and csm_coupon <= ? */
		public final static @type(Double.class) String csmCouponMax="csmCouponMax";
		/** 积分 [非空]       **/
		public final static @type(Double.class)  String csmIntegral="csmIntegral";
		/** and csm_integral >= ? */
		public final static @type(Double.class) String csmIntegralMin="csmIntegralMin";
		/** and csm_integral <= ? */
		public final static @type(Double.class) String csmIntegralMax="csmIntegralMax";
		/** 成长值 [非空]       **/
		public final static @type(Integer.class)  String csmGrow="csmGrow";
		/** and csm_grow >= ? */
		public final static @type(Integer.class) String csmGrowMin="csmGrowMin";
		/** and csm_grow <= ? */
		public final static @type(Integer.class) String csmGrowMax="csmGrowMax";
		/** 会员等级 [非空]       **/
		public final static @type(Integer.class)  String csmGrade="csmGrade";
		/** and csm_grade >= ? */
		public final static @type(Integer.class) String csmGradeMin="csmGradeMin";
		/** and csm_grade <= ? */
		public final static @type(Integer.class) String csmGradeMax="csmGradeMax";
		/** 折扣 [非空]    0到1,比如0.75表示七五折   **/
		public final static @type(Double.class)  String csmRebate="csmRebate";
		/** and csm_rebate >= ? */
		public final static @type(Double.class) String csmRebateMin="csmRebateMin";
		/** and csm_rebate <= ? */
		public final static @type(Double.class) String csmRebateMax="csmRebateMax";
		/** 计算营收    0:计入营收 1:不计营收  表示该帐号支付的订单是否为营收项   **/
		public final static @type(Short.class)  String csmNotRevenue="csmNotRevenue";
		/** 微信帐号标识        **/
		public final static @type(String.class) @like String csmWeixinFlag="csmWeixinFlag";
		/** 支付宝帐号标识        **/
		public final static @type(String.class) @like String csmAlipayFlag="csmAlipayFlag";
		/** 头像    200:width 200:height     **/
		public final static @type(String.class)  String csmHeader="csmHeader";
		/** 邮箱        **/
		public final static @type(String.class) @like String csmEmail="csmEmail";
		/** 手机        **/
		public final static @type(String.class) @like String csmMobile="csmMobile";
		/** EV卡  [CsEvCard]      **/
		public final static @type(Long.class)  String csmEvcard="csmEvcard";
		/** 快递单号        **/
		public final static @type(String.class) @like String csmExpress="csmExpress";
		/** 邀请码        **/
		public final static @type(String.class) @like String csmTemp="csmTemp";
		/** 真实姓名        **/
		public final static @type(String.class) @like String csmName="csmName";
		/** 详细信息  [CsMemberInfo]      **/
		public final static @type(Long.class)  String csmInfo="csmInfo";
		/** 默认网点  [CsOutlets]     ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
		public final static @type(Long.class)  String csmOutlets="csmOutlets";
		/** VIP状态    0:不是 1:是     **/
		public final static @type(Short.class)  String csmIsVip="csmIsVip";
		/** VIP开始时间        **/
		public final static @type(Date.class)  String csmVipStart="csmVipStart";
	 	/** and csm_vip_start >= ? */
 		public final static @type(Date.class) String csmVipStartStart="csmVipStartStart";
 		/** and csm_vip_start <= ? */
 		public final static @type(Date.class) String csmVipStartEnd="csmVipStartEnd";
		/** VIP结束时间        **/
		public final static @type(Date.class)  String csmVipEnd="csmVipEnd";
	 	/** and csm_vip_end >= ? */
 		public final static @type(Date.class) String csmVipEndStart="csmVipEndStart";
 		/** and csm_vip_end <= ? */
 		public final static @type(Date.class) String csmVipEndEnd="csmVipEndEnd";
		/** 更新时间        **/
		public final static @type(Date.class)  String csmUpdateTime="csmUpdateTime";
	 	/** and csm_update_time >= ? */
 		public final static @type(Date.class) String csmUpdateTimeStart="csmUpdateTimeStart";
 		/** and csm_update_time <= ? */
 		public final static @type(Date.class) String csmUpdateTimeEnd="csmUpdateTimeEnd";
		/** 注册时间 [非空]       **/
		public final static @type(Date.class)  String csmAddTime="csmAddTime";
	 	/** and csm_add_time >= ? */
 		public final static @type(Date.class) String csmAddTimeStart="csmAddTimeStart";
 		/** and csm_add_time <= ? */
 		public final static @type(Date.class) String csmAddTimeEnd="csmAddTimeEnd";
		/** 最后登录        **/
		public final static @type(Date.class)  String csmLastTime="csmLastTime";
	 	/** and csm_last_time >= ? */
 		public final static @type(Date.class) String csmLastTimeStart="csmLastTimeStart";
 		/** and csm_last_time <= ? */
 		public final static @type(Date.class) String csmLastTimeEnd="csmLastTimeEnd";
		/** 最后登录IP        **/
		public final static @type(String.class) @like String csmLastIp="csmLastIp";
		/** 登录次数        **/
		public final static @type(Integer.class)  String csmLoginS="csmLoginS";
		/** and csm_login_s >= ? */
		public final static @type(Integer.class) String csmLoginSMin="csmLoginSMin";
		/** and csm_login_s <= ? */
		public final static @type(Integer.class) String csmLoginSMax="csmLoginSMax";
		/** 首次用车        **/
		public final static @type(Date.class)  String csmFirstUse="csmFirstUse";
	 	/** and csm_first_use >= ? */
 		public final static @type(Date.class) String csmFirstUseStart="csmFirstUseStart";
 		/** and csm_first_use <= ? */
 		public final static @type(Date.class) String csmFirstUseEnd="csmFirstUseEnd";
		/** 最后使用        **/
		public final static @type(Date.class)  String csmLastUse="csmLastUse";
	 	/** and csm_last_use >= ? */
 		public final static @type(Date.class) String csmLastUseStart="csmLastUseStart";
 		/** and csm_last_use <= ? */
 		public final static @type(Date.class) String csmLastUseEnd="csmLastUseEnd";
		/** 累计充值        **/
		public final static @type(Double.class)  String csmAllRecharge="csmAllRecharge";
		/** and csm_all_recharge >= ? */
		public final static @type(Double.class) String csmAllRechargeMin="csmAllRechargeMin";
		/** and csm_all_recharge <= ? */
		public final static @type(Double.class) String csmAllRechargeMax="csmAllRechargeMax";
		/** 累计订单        **/
		public final static @type(Long.class)  String csmAllOrderS="csmAllOrderS";
		/** and csm_all_order_s >= ? */
		public final static @type(Long.class) String csmAllOrderSMin="csmAllOrderSMin";
		/** and csm_all_order_s <= ? */
		public final static @type(Long.class) String csmAllOrderSMax="csmAllOrderSMax";
		/** 累计用车小时        **/
		public final static @type(Double.class)  String csmAllUseTime="csmAllUseTime";
		/** and csm_all_use_time >= ? */
		public final static @type(Double.class) String csmAllUseTimeMin="csmAllUseTimeMin";
		/** and csm_all_use_time <= ? */
		public final static @type(Double.class) String csmAllUseTimeMax="csmAllUseTimeMax";
		/** 违章次数        **/
		public final static @type(Long.class)  String csmAllViolatS="csmAllViolatS";
		/** and csm_all_violat_s >= ? */
		public final static @type(Long.class) String csmAllViolatSMin="csmAllViolatSMin";
		/** and csm_all_violat_s <= ? */
		public final static @type(Long.class) String csmAllViolatSMax="csmAllViolatSMax";
		/** 未处理违章        **/
		public final static @type(Long.class)  String csmUnViolatS="csmUnViolatS";
		/** and csm_un_violat_s >= ? */
		public final static @type(Long.class) String csmUnViolatSMin="csmUnViolatSMin";
		/** and csm_un_violat_s <= ? */
		public final static @type(Long.class) String csmUnViolatSMax="csmUnViolatSMax";
		/** 事故次数        **/
		public final static @type(Long.class)  String csmAllTroubleS="csmAllTroubleS";
		/** and csm_all_trouble_s >= ? */
		public final static @type(Long.class) String csmAllTroubleSMin="csmAllTroubleSMin";
		/** and csm_all_trouble_s <= ? */
		public final static @type(Long.class) String csmAllTroubleSMax="csmAllTroubleSMax";
		/** 首次充值        **/
		public final static @type(Date.class)  String csmFirstRecharge="csmFirstRecharge";
	 	/** and csm_first_recharge >= ? */
 		public final static @type(Date.class) String csmFirstRechargeStart="csmFirstRechargeStart";
 		/** and csm_first_recharge <= ? */
 		public final static @type(Date.class) String csmFirstRechargeEnd="csmFirstRechargeEnd";
		/** 最后退款        **/
		public final static @type(Date.class)  String csmRefundTime="csmRefundTime";
	 	/** and csm_refund_time >= ? */
 		public final static @type(Date.class) String csmRefundTimeStart="csmRefundTimeStart";
 		/** and csm_refund_time <= ? */
 		public final static @type(Date.class) String csmRefundTimeEnd="csmRefundTimeEnd";
		/** 注册IP        **/
		public final static @type(String.class) @like String csmRegistIp="csmRegistIp";
		/** 添加人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csmAdder="csmAdder";
		/** 跟踪人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csmTracker="csmTracker";
		/** 推荐会员  [CsMember]      **/
		public final static @type(Long.class)  String csmRefer="csmRefer";
		/** 推荐方式    0:无 1:地址转发 2:微博分享 3:广告媒体 4:网络搜索 5:朋友推荐 6:活动推广     **/
		public final static @type(Short.class)  String csmReferType="csmReferType";
		/** 注册平台    0:后台 1:网站 2:微信 3:APP 4:支付宝微生活     **/
		public final static @type(Short.class)  String csmFrom="csmFrom";
		/** 注册源信息        **/
		public final static @type(String.class)  String csmSrc="csmSrc";
		/** 来源渠道  [CsChannel]      **/
		public final static @type(Long.class)  String csmChannel="csmChannel";
		/** 营销方案  [CsMarketPlan]      **/
		public final static @type(Long.class)  String csmMarketPlan="csmMarketPlan";
		/** 来源活动  [CsActivity]      **/
		public final static @type(Long.class)  String csmActivity="csmActivity";
		/** 销售人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csmSaler="csmSaler";
		/** 营销标记  [SrvProperty]  营销标识:'MEMBER_MARKET'    select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
		public final static @type(String.class) @like String csmMarket="csmMarket";
		/** 会员标签  [SrvProperty]  用户标签:'MEMBER_TAG'  (会员特性)标签用来对用户分类，在系统属性表中配置  select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
		public final static @type(String.class) @like String csmTag="csmTag";
		/** 备忘标记  [SrvProperty]  备忘标记:'MEMBER_MARK'  (管理标记)管理人员对该会员做的备忘标记，在系统属性表中配置  select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
		public final static @type(String.class) @like String csmMark="csmMark";
		/** 回访标记  [CsQuestTemplate]   (回访标记)对应回访模板,勾上了表示该模板已调查或已回访  select * from cs_quest_template where 1=1 **/
		public final static @type(String.class) @like String csmVisitFlag="csmVisitFlag";
		/** 程序掩码    1:已回流支付宝 2:保留2 4:保留4 8:保留8  (程序标记)技术人员使用的标记，按位运算   **/
		public final static @type(Long.class)  String csmMask="csmMask";
		/** 备注        **/
		public final static @type(String.class) @like String csmRemark="csmRemark";
		/** 手机认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
		public final static @type(Short.class)  String csmVMobile="csmVMobile";
		/** 邮箱认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
		public final static @type(Short.class)  String csmVEmail="csmVEmail";
		/** 实名认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
		public final static @type(Short.class)  String csmVReal="csmVReal";
		/** 驾驶认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
		public final static @type(Short.class)  String csmVDrive="csmVDrive";
		
		/** 工作认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
        public final static @type(Short.class)  String csmVWork="csmVWork";
        /** 身份证认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
        public final static @type(Short.class)  String csmVIdcard="csmVIdcard";
        /** 线下认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
        public final static @type(Short.class)  String csmVOffline="csmVOffline";
        /** 禁用原因 **/
        public final static @type(String.class)  String csmLockReason="csmLockReason";
        /** 线下认证机器码 **/
        public final static @type(String.class)  String csmVOfflineCode="csmVOfflineCode";        
		
		/** 可用状态 [非空]   1:正常 0:禁用  -1:黑名单     **/
		public final static @type(Short.class)  String csmStatus="csmStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csmId="csm_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csmHost="csm_host";
		/** 用户名 [非空]    如果由第三方平台注册，不同类型加前缀   **/
		public final static String csmUsername="csm_username";
		/** 帐号密码 [非空]       **/
		public final static String csmPassword="csm_password";
		/** 所属组织  [CsMemberGroup]      **/
		public final static String csmGroup="csm_group";
		/** 余额 [非空]       **/
		public final static String csmMoney="csm_money";
		/** 现金券 [非空]       **/
		public final static String csmCoupon="csm_coupon";
		/** 积分 [非空]       **/
		public final static String csmIntegral="csm_integral";
		/** 成长值 [非空]       **/
		public final static String csmGrow="csm_grow";
		/** 会员等级 [非空]       **/
		public final static String csmGrade="csm_grade";
		/** 折扣 [非空]    0到1,比如0.75表示七五折   **/
		public final static String csmRebate="csm_rebate";
		/** 计算营收    0:计入营收 1:不计营收  表示该帐号支付的订单是否为营收项   **/
		public final static String csmNotRevenue="csm_not_revenue";
		/** 微信帐号标识        **/
		public final static String csmWeixinFlag="csm_weixin_flag";
		/** 支付宝帐号标识        **/
		public final static String csmAlipayFlag="csm_alipay_flag";
		/** 头像    200:width 200:height     **/
		public final static String csmHeader="csm_header";
		/** 邮箱        **/
		public final static String csmEmail="csm_email";
		/** 手机        **/
		public final static String csmMobile="csm_mobile";
		/** EV卡  [CsEvCard]      **/
		public final static String csmEvcard="csm_evcard";
		/** 快递单号        **/
		public final static String csmExpress="csm_express";
		/** 邀请码        **/
		public final static String csmTemp="csm_temp";
		/** 真实姓名        **/
		public final static String csmName="csm_name";
		/** 详细信息  [CsMemberInfo]      **/
		public final static String csmInfo="csm_info";
		/** 默认网点  [CsOutlets]     ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
		public final static String csmOutlets="csm_outlets";
		/** VIP状态    0:不是 1:是     **/
		public final static String csmIsVip="csm_is_vip";
		/** VIP开始时间        **/
		public final static String csmVipStart="csm_vip_start";
		/** VIP结束时间        **/
		public final static String csmVipEnd="csm_vip_end";
		/** 更新时间        **/
		public final static String csmUpdateTime="csm_update_time";
		/** 注册时间 [非空]       **/
		public final static String csmAddTime="csm_add_time";
		/** 最后登录        **/
		public final static String csmLastTime="csm_last_time";
		/** 最后登录IP        **/
		public final static String csmLastIp="csm_last_ip";
		/** 登录次数        **/
		public final static String csmLoginS="csm_login_s";
		/** 首次用车        **/
		public final static String csmFirstUse="csm_first_use";
		/** 最后使用        **/
		public final static String csmLastUse="csm_last_use";
		/** 累计充值        **/
		public final static String csmAllRecharge="csm_all_recharge";
		/** 累计订单        **/
		public final static String csmAllOrderS="csm_all_order_s";
		/** 累计用车小时        **/
		public final static String csmAllUseTime="csm_all_use_time";
		/** 违章次数        **/
		public final static String csmAllViolatS="csm_all_violat_s";
		/** 未处理违章        **/
		public final static String csmUnViolatS="csm_un_violat_s";
		/** 事故次数        **/
		public final static String csmAllTroubleS="csm_all_trouble_s";
		/** 首次充值        **/
		public final static String csmFirstRecharge="csm_first_recharge";
		/** 最后退款        **/
		public final static String csmRefundTime="csm_refund_time";
		/** 注册IP        **/
		public final static String csmRegistIp="csm_regist_ip";
		/** 添加人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csmAdder="csm_adder";
		/** 跟踪人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csmTracker="csm_tracker";
		/** 推荐会员  [CsMember]      **/
		public final static String csmRefer="csm_refer";
		/** 推荐方式    0:无 1:地址转发 2:微博分享 3:广告媒体 4:网络搜索 5:朋友推荐 6:活动推广     **/
		public final static String csmReferType="csm_refer_type";
		/** 注册平台    0:后台 1:网站 2:微信 3:APP 4:支付宝微生活     **/
		public final static String csmFrom="csm_from";
		/** 注册源信息        **/
		public final static String csmSrc="csm_src";
		/** 来源渠道  [CsChannel]      **/
		public final static String csmChannel="csm_channel";
		/** 营销方案  [CsMarketPlan]      **/
		public final static String csmMarketPlan="csm_market_plan";
		/** 来源活动  [CsActivity]      **/
		public final static String csmActivity="csm_activity";
		/** 销售人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csmSaler="csm_saler";
		/** 营销标记  [SrvProperty]  营销标识:'MEMBER_MARKET'    select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
		public final static String csmMarket="csm_market";
		/** 会员标签  [SrvProperty]  用户标签:'MEMBER_TAG'  (会员特性)标签用来对用户分类，在系统属性表中配置  select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
		public final static String csmTag="csm_tag";
		/** 备忘标记  [SrvProperty]  备忘标记:'MEMBER_MARK'  (管理标记)管理人员对该会员做的备忘标记，在系统属性表中配置  select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
		public final static String csmMark="csm_mark";
		/** 回访标记  [CsQuestTemplate]   (回访标记)对应回访模板,勾上了表示该模板已调查或已回访  select * from cs_quest_template where 1=1 **/
		public final static String csmVisitFlag="csm_visit_flag";
		/** 程序掩码    1:已回流支付宝 2:保留2 4:保留4 8:保留8  (程序标记)技术人员使用的标记，按位运算   **/
		public final static String csmMask="csm_mask";
		/** 备注        **/
		public final static String csmRemark="csm_remark";
		/** 手机认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
		public final static String csmVMobile="csm_v_mobile";
		/** 邮箱认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
		public final static String csmVEmail="csm_v_email";
		/** 实名认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
		public final static String csmVReal="csm_v_real";
		/** 驾驶认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
		public final static String csmVDrive="csm_v_drive";
		
		/** 工作认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
        public final static String csmVWork="csm_v_work";
        /** 线下认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
        public final static String csmVOffline="csm_v_offline";
        /** 工作认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
        public final static String csmLockReason="csm_lock_reason";
        /** 线下认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
        public final static String csmVOfflineCode="csm_v_offline_code";
        /** 身份证认证    0:未认证 1:已认证 2:等待认证 3:认证失败     **/
        public final static String csmVIdcard="csm_v_idcard";
        
		/** 可用状态 [非空]   1:正常 0:禁用  -1:黑名单     **/
		public final static String csmStatus="csm_status";
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
				$.Set(name,CsMember.getCsMember(params));
			else
				$.Set(name,CsMember.getCsMemberList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取会员帐号数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsMember.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsMember.getCsMember(params);
			else
				value = CsMember.getCsMemberList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsMember.Get($.add(CsMember.F.csmId,param));
		else if(!$.empty(param.toString()))
			value = CsMember.get(Long.valueOf(param.toString()));
		$.SetRequest("CsMember$"+param.hashCode(), value);
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
	public void mergeSet(CsMember old){
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