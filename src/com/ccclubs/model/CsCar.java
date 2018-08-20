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

@namespace("object/car")
public @caption("车辆") @table("cs_car") class CsCar implements java.io.Serializable
{
	private static final long serialVersionUID =              1l;
	private @caption("编号") @column("csc_id")   @primary  @note("  ") Long cscId;// 主键 非空     
	private @caption("城市") @column("csc_host")    @relate("$cscHost") @RelateClass(SrvHost.class)  @note("  ") Long cscHost;// 非空     
 	private SrvHost $cscHost;//关联对象[运营城市]
	private @caption("车牌号") @column("csc_number")    @note("  ") String cscNumber;// 非空     
	private @caption("车牌名称") @column("csc_car_no")    @note("  ") String cscCarNo;// 非空     
	private @caption("车型") @column("csc_model")    @relate("$cscModel") @RelateClass(CsCarModel.class)  @note("  ") Long cscModel;// 非空     
 	private CsCarModel $cscModel;//关联对象[车型]
	private @caption("网点") @column("csc_outlets")    @relate("$cscOutlets") @RelateClass(CsOutlets.class)  @note(" {cscHost}:host  ") Long cscOutlets;// 非空 {cscHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} 
 	private CsOutlets $cscOutlets;//关联对象[网点]
	//private @caption("订单记录") @RelateClass(CsOrder.class) List<CsOrder> orders;//反向关联的系统订单列表
	//private @caption("违章记录") @RelateClass(CsViolat.class) List<CsViolat> violats;//反向关联的车辆违章列表
	//private @caption("事故记录") @RelateClass(CsTrouble.class) List<CsTrouble> troubles;//反向关联的车辆事故列表
	//private @caption("维修记录") @RelateClass(CsRepair.class) List<CsRepair> repairs;//反向关联的车辆维修列表
	//private @caption("保养记录") @RelateClass(CsMaintain.class) List<CsMaintain> maintains;//反向关联的车辆保养列表
	//private @caption("上下线记录") @RelateClass(CsCarOn.class) List<CsCarOn> onlines;//反向关联的车辆上线记录列表
	private @caption("供应商") @column("csc_provid")    @relate("$cscProvid") @RelateClass(CsProvid.class)  @note("  ") Long cscProvid;// 非空     
 	private CsProvid $cscProvid;//关联对象[供应商]
	private @caption("折扣") @column("csc_rebate")    @note("  0到1,比如0.75表示七五折") Double cscRebate;// 非空  0到1,比如0.75表示七五折   
	private @caption("广告车？") @column("csc_is_ad")    @note("  ") Boolean cscIsAd;// 非空     
	private @caption("经度") @column("csc_longitude")  @hidden   @note("  ") Double cscLongitude;//     
	private @caption("纬度") @column("csc_latitude")  @hidden   @note("  ") Double cscLatitude;//     
	private @caption("手机号码") @column("csc_mobile")    @note("  ") String cscMobile;//     
	private @caption("服务密码") @column("csc_password")    @note("  ") String cscPassword;//     
	private @caption("加油卡号") @column("csc_oil_card")    @note("  ") String cscOilCard;//     
	private @caption("车架号") @column("csc_vin")    @note("  ") String cscVin;//     
	private @caption("发动机号") @column("csc_engine_no")    @note("  ") String cscEngineNo;//     
	private @caption("购车日") @column("csc_buy_date")    @note("  ") Date cscBuyDate;//     
	private @caption("合同号") @column("csc_bargain_no")    @note("  ") String cscBargainNo;//     
	private @caption("购置税") @column("csc_tax_price")    @note("  ") Double cscTaxPrice;//     
	private @caption("车价") @column("csc_buy_price")    @note("  ") Double cscBuyPrice;//     
	private @caption("合格证号") @column("csc_certific")    @note("  ") String cscCertific;//     
	private @caption("厂牌型号") @column("csc_factory_no")    @note("  ") String cscFactoryNo;//     
	private @caption("颜色") @column("csc_color")    @note(" 0:黑色 1:白色 2:红色 3:绿色 4:黄色 5:橙色 6:银色 7:蓝色 8:深蓝 9:深灰 10:深红  ") Short cscColor;// 0:黑色 1:白色 2:红色 3:绿色 4:黄色 5:橙色 6:银色 7:蓝色 8:深蓝 9:深灰 10:深红     
	private @caption("车辆主图") @column("csc_preview")    @note("  图片建议尺寸500*500像素（比例）") String cscPreview;//  图片建议尺寸500*500像素（比例）   
	private @caption("照片列表") @column("csc_images")    @note("  ") String cscImages;//     
	private @caption("保养公里") @column("csc_maint_km")    @note("  ") Integer cscMaintKm;//     
	private @caption("初次登记") @column("csc_check_in")    @note("  ") Date cscCheckIn;//     
	private @caption("上次年检日期") @column("csc_annual_pre")    @note("  ") Date cscAnnualPre;//     
	private @caption("实际年检日期") @column("csc_annual")    @note("  ") Date cscAnnual;//     
	private @caption("具体年检信息") @column("csc_annual_info")    @note("  ") String cscAnnualInfo;//     
	private @caption("预计下次年检") @column("csc_annual_next")    @note("  ") Date cscAnnualNext;//     
	private @caption("交强险投保") @column("csc_ti_date")    @note("  ") Date cscTiDate;//     
	private @caption("交强险到期") @column("csc_ti_unit")    @note("  ") Date cscTiUnit;//     
	private @caption("交强险保单") @column("csc_ti_no")    @note("  ") String cscTiNo;//     
	private @caption("商业险投保") @column("csc_bi_date")    @note("  ") Date cscBiDate;//     
	private @caption("商业险到期") @column("csc_bi_unit")    @note("  ") Date cscBiUnit;//     
	private @caption("商业险保单") @column("csc_bi_no")    @note("  ") String cscBiNo;//     
	private @caption("商业险类型") @column("csc_bi_type")    @note("  ") String cscBiType;//     
	private @caption("商业险金额") @column("csc_bi_limit")    @note("  ") Double cscBiLimit;//     
	private @caption("保险公司") @column("csc_bi_company")    @note("  ") String cscBiCompany;//     
	private @caption("修改人") @column("csc_editor")    @relate("$cscEditor") @RelateClass(SrvUser.class)  @note("  ") Long cscEditor;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cscEditor;//关联对象[用户]
	private @caption("修改时间") @column("csc_update_time")    @note("  ") Date cscUpdateTime;// 非空     
	private @caption("添加时间") @column("csc_add_time")    @note("  ") Date cscAddTime;// 非空     
	private @caption("备注") @column("csc_remark")    @note("  ") String cscRemark;//     
	private @caption("备忘信息") @column("csc_memo")    @note(" textarea:content text:sign  ") String cscMemo;// textarea:content text:sign     
	private @caption("上线密码") @column("csc_on_psd")    @note("  ") String cscOnPsd;//     
	private @caption("车机安装") @column("csc_setup")    @note(" 0:未安装 1:正在安装 2:已安装  ") Short cscSetup;// 0:未安装 1:正在安装 2:已安装     
	private @caption("下位机版本") @column("csc_device_vesion")    @note(" 0:1.0版 1:2.0版  ") Short cscDeviceVesion;// 0:1.0版 1:2.0版     
	private @caption("服务器标识") @column("csc_mqtt_flag")    @note("  ") String cscMqttFlag;//     
	private @caption("程序掩码") @column("csc_mask")    @note(" 1:假定条件  ") Long cscMask;// 1:假定条件     
	private @caption("状态") @column("csc_status")    @note(" 1:上线 2:下线 3:企业可租 0:无效  ") Short cscStatus;// 非空 1:上线 2:下线 3:企业可租 0:无效     
	
	private @caption("绑定的终端序列号") @column("csc_ter_no")    @note("  ") String cscTerNo;
	private @caption("车辆挂载的平台") @column("csc_bind_platform")    @note(" 0：北京平台，1：车机中心  ") Short cscBindPlatform;// 0：自己业务平台，1：车机中心
	//默认构造函数
	public CsCar(){
	
	}
	
	//主键构造函数
	public CsCar(Long id){
		this.cscId = id;
	}
	
	/**所有字段构造函数 CsCar(cscHost,cscNumber,cscCarNo,cscModel,cscOutlets,cscProvid,cscRebate,cscIsAd,cscLongitude,cscLatitude,cscMobile,cscPassword,cscOilCard,cscVin,cscEngineNo,cscBuyDate,cscBargainNo,cscTaxPrice,cscBuyPrice,cscCertific,cscFactoryNo,cscColor,cscPreview,cscImages,cscMaintKm,cscCheckIn,cscAnnualPre,cscAnnual,cscAnnualInfo,cscAnnualNext,cscTiDate,cscTiUnit,cscTiNo,cscBiDate,cscBiUnit,cscBiNo,cscBiType,cscBiLimit,cscBiCompany,cscEditor,cscUpdateTime,cscAddTime,cscRemark,cscMemo,cscOnPsd,cscSetup,cscDeviceVesion,cscMqttFlag,cscMask,cscStatus)
	 CsCar(
	 	$.getLong("cscHost")//城市 [非空]
	 	,$.getString("cscNumber")//车牌号 [非空]
	 	,$.getString("cscCarNo")//车牌名称 [非空]
	 	,$.getLong("cscModel")//车型 [非空]
	 	,$.getLong("cscOutlets")//网点 [非空]
	 	,$.getLong("cscProvid")//供应商 [非空]
	 	,$.getDouble("cscRebate")//折扣 [非空]
	 	,$.getBoolean("cscIsAd")//广告车？ [非空]
	 	,$.getDouble("cscLongitude")//经度
	 	,$.getDouble("cscLatitude")//纬度
	 	,$.getString("cscMobile")//手机号码
	 	,$.getString("cscPassword")//服务密码
	 	,$.getString("cscOilCard")//加油卡号
	 	,$.getString("cscVin")//车架号
	 	,$.getString("cscEngineNo")//发动机号
	 	,$.getDate("cscBuyDate")//购车日
	 	,$.getString("cscBargainNo")//合同号
	 	,$.getDouble("cscTaxPrice")//购置税
	 	,$.getDouble("cscBuyPrice")//车价
	 	,$.getString("cscCertific")//合格证号
	 	,$.getString("cscFactoryNo")//厂牌型号
	 	,$.getShort("cscColor")//颜色
	 	,$.getString("cscPreview")//车辆主图
	 	,$.getString("cscImages")//照片列表
	 	,$.getInteger("cscMaintKm")//保养公里
	 	,$.getDate("cscCheckIn")//初次登记
	 	,$.getDate("cscAnnualPre")//上次年检日期
	 	,$.getDate("cscAnnual")//实际年检日期
	 	,$.getString("cscAnnualInfo")//具体年检信息
	 	,$.getDate("cscAnnualNext")//预计下次年检
	 	,$.getDate("cscTiDate")//交强险投保
	 	,$.getDate("cscTiUnit")//交强险到期
	 	,$.getString("cscTiNo")//交强险保单
	 	,$.getDate("cscBiDate")//商业险投保
	 	,$.getDate("cscBiUnit")//商业险到期
	 	,$.getString("cscBiNo")//商业险保单
	 	,$.getString("cscBiType")//商业险类型
	 	,$.getDouble("cscBiLimit")//商业险金额
	 	,$.getString("cscBiCompany")//保险公司
	 	,$.getLong("cscEditor")//修改人
	 	,$.getDate("cscUpdateTime")//修改时间 [非空]
	 	,$.getDate("cscAddTime")//添加时间 [非空]
	 	,$.getString("cscRemark")//备注
	 	,$.getString("cscMemo")//备忘信息
	 	,$.getString("cscOnPsd")//上线密码
	 	,$.getShort("cscSetup")//车机安装
	 	,$.getShort("cscDeviceVesion")//下位机版本
	 	,$.getString("cscMqttFlag")//服务器标识
	 	,$.getLong("cscMask")//程序掩码
	 	,$.getShort("cscStatus")//状态 [非空]
	 )
	**/
	public CsCar(Long cscHost,String cscNumber,String cscCarNo,Long cscModel,Long cscOutlets,Long cscProvid,Double cscRebate,Boolean cscIsAd,Double cscLongitude,Double cscLatitude,String cscMobile,String cscPassword,String cscOilCard,String cscVin,String cscEngineNo,Date cscBuyDate,String cscBargainNo,Double cscTaxPrice,Double cscBuyPrice,String cscCertific,String cscFactoryNo,Short cscColor,String cscPreview,String cscImages,Integer cscMaintKm,Date cscCheckIn,Date cscAnnualPre,Date cscAnnual,String cscAnnualInfo,Date cscAnnualNext,Date cscTiDate,Date cscTiUnit,String cscTiNo,Date cscBiDate,Date cscBiUnit,String cscBiNo,String cscBiType,Double cscBiLimit,String cscBiCompany,Long cscEditor,Date cscUpdateTime,Date cscAddTime,String cscRemark,String cscMemo,String cscOnPsd,Short cscSetup,Short cscDeviceVesion,String cscMqttFlag,Long cscMask,Short cscStatus, String cscTerNo, Short cscBindPlatform){
		this.cscHost=cscHost;
		this.cscNumber=cscNumber;
		this.cscCarNo=cscCarNo;
		this.cscModel=cscModel;
		this.cscOutlets=cscOutlets;
		this.cscProvid=cscProvid;
		this.cscRebate=cscRebate;
		this.cscIsAd=cscIsAd;
		this.cscLongitude=cscLongitude;
		this.cscLatitude=cscLatitude;
		this.cscMobile=cscMobile;
		this.cscPassword=cscPassword;
		this.cscOilCard=cscOilCard;
		this.cscVin=cscVin;
		this.cscEngineNo=cscEngineNo;
		this.cscBuyDate=cscBuyDate;
		this.cscBargainNo=cscBargainNo;
		this.cscTaxPrice=cscTaxPrice;
		this.cscBuyPrice=cscBuyPrice;
		this.cscCertific=cscCertific;
		this.cscFactoryNo=cscFactoryNo;
		this.cscColor=cscColor;
		this.cscPreview=cscPreview;
		this.cscImages=cscImages;
		this.cscMaintKm=cscMaintKm;
		this.cscCheckIn=cscCheckIn;
		this.cscAnnualPre=cscAnnualPre;
		this.cscAnnual=cscAnnual;
		this.cscAnnualInfo=cscAnnualInfo;
		this.cscAnnualNext=cscAnnualNext;
		this.cscTiDate=cscTiDate;
		this.cscTiUnit=cscTiUnit;
		this.cscTiNo=cscTiNo;
		this.cscBiDate=cscBiDate;
		this.cscBiUnit=cscBiUnit;
		this.cscBiNo=cscBiNo;
		this.cscBiType=cscBiType;
		this.cscBiLimit=cscBiLimit;
		this.cscBiCompany=cscBiCompany;
		this.cscEditor=cscEditor;
		this.cscUpdateTime=cscUpdateTime;
		this.cscAddTime=cscAddTime;
		this.cscRemark=cscRemark;
		this.cscMemo=cscMemo;
		this.cscOnPsd=cscOnPsd;
		this.cscSetup=cscSetup;
		this.cscDeviceVesion=cscDeviceVesion;
		this.cscMqttFlag=cscMqttFlag;
		this.cscMask=cscMask;
		this.cscStatus=cscStatus;
		
		this.cscTerNo=cscTerNo;
		this.cscBindPlatform=cscBindPlatform;
	}
	
	//设置非空字段
	public CsCar setNotNull(Long cscHost,String cscNumber,String cscCarNo,Long cscModel,Long cscOutlets,Long cscProvid,Double cscRebate,Boolean cscIsAd,Date cscUpdateTime,Date cscAddTime,Short cscStatus){
		this.cscHost=cscHost;
		this.cscNumber=cscNumber;
		this.cscCarNo=cscCarNo;
		this.cscModel=cscModel;
		this.cscOutlets=cscOutlets;
		this.cscProvid=cscProvid;
		this.cscRebate=cscRebate;
		this.cscIsAd=cscIsAd;
		this.cscUpdateTime=cscUpdateTime;
		this.cscAddTime=cscAddTime;
		this.cscStatus=cscStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsCar cscId(Long cscId){
		this.cscId = cscId;
		this.setSeted(F.cscId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsCar cscHost(Long cscHost){
		this.cscHost = cscHost;
		this.setSeted(F.cscHost);
		return this;
	}
	/** 车牌号 [非空]       **/
	public CsCar cscNumber(String cscNumber){
		this.cscNumber = cscNumber;
		this.setSeted(F.cscNumber);
		return this;
	}
	/** 车牌名称 [非空]       **/
	public CsCar cscCarNo(String cscCarNo){
		this.cscCarNo = cscCarNo;
		this.setSeted(F.cscCarNo);
		return this;
	}
	/** 车型 [非空] [CsCarModel]      **/
	public CsCar cscModel(Long cscModel){
		this.cscModel = cscModel;
		this.setSeted(F.cscModel);
		return this;
	}
	/** 网点 [非空] [CsOutlets]  {cscHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
	public CsCar cscOutlets(Long cscOutlets){
		this.cscOutlets = cscOutlets;
		this.setSeted(F.cscOutlets);
		return this;
	}
	/** 供应商 [非空] [CsProvid]      **/
	public CsCar cscProvid(Long cscProvid){
		this.cscProvid = cscProvid;
		this.setSeted(F.cscProvid);
		return this;
	}
	/** 折扣 [非空]    0到1,比如0.75表示七五折   **/
	public CsCar cscRebate(Double cscRebate){
		this.cscRebate = cscRebate;
		this.setSeted(F.cscRebate);
		return this;
	}
	/** 广告车？ [非空]       **/
	public CsCar cscIsAd(Boolean cscIsAd){
		this.cscIsAd = cscIsAd;
		this.setSeted(F.cscIsAd);
		return this;
	}
	/** 经度        **/
	public CsCar cscLongitude(Double cscLongitude){
		this.cscLongitude = cscLongitude;
		this.setSeted(F.cscLongitude);
		return this;
	}
	/** 纬度        **/
	public CsCar cscLatitude(Double cscLatitude){
		this.cscLatitude = cscLatitude;
		this.setSeted(F.cscLatitude);
		return this;
	}
	/** 手机号码        **/
	public CsCar cscMobile(String cscMobile){
		this.cscMobile = cscMobile;
		this.setSeted(F.cscMobile);
		return this;
	}
	/** 服务密码        **/
	public CsCar cscPassword(String cscPassword){
		this.cscPassword = cscPassword;
		this.setSeted(F.cscPassword);
		return this;
	}
	/** 加油卡号        **/
	public CsCar cscOilCard(String cscOilCard){
		this.cscOilCard = cscOilCard;
		this.setSeted(F.cscOilCard);
		return this;
	}
	/** 车架号        **/
	public CsCar cscVin(String cscVin){
		this.cscVin = cscVin;
		this.setSeted(F.cscVin);
		return this;
	}
	/** 发动机号        **/
	public CsCar cscEngineNo(String cscEngineNo){
		this.cscEngineNo = cscEngineNo;
		this.setSeted(F.cscEngineNo);
		return this;
	}
	/** 购车日        **/
	public CsCar cscBuyDate(Date cscBuyDate){
		this.cscBuyDate = cscBuyDate;
		this.setSeted(F.cscBuyDate);
		return this;
	}
	/** 合同号        **/
	public CsCar cscBargainNo(String cscBargainNo){
		this.cscBargainNo = cscBargainNo;
		this.setSeted(F.cscBargainNo);
		return this;
	}
	/** 购置税        **/
	public CsCar cscTaxPrice(Double cscTaxPrice){
		this.cscTaxPrice = cscTaxPrice;
		this.setSeted(F.cscTaxPrice);
		return this;
	}
	/** 车价        **/
	public CsCar cscBuyPrice(Double cscBuyPrice){
		this.cscBuyPrice = cscBuyPrice;
		this.setSeted(F.cscBuyPrice);
		return this;
	}
	/** 合格证号        **/
	public CsCar cscCertific(String cscCertific){
		this.cscCertific = cscCertific;
		this.setSeted(F.cscCertific);
		return this;
	}
	/** 厂牌型号        **/
	public CsCar cscFactoryNo(String cscFactoryNo){
		this.cscFactoryNo = cscFactoryNo;
		this.setSeted(F.cscFactoryNo);
		return this;
	}
	/** 颜色    0:黑色 1:白色 2:红色 3:绿色 4:黄色 5:橙色 6:银色 7:蓝色 8:深蓝 9:深灰 10:深红     **/
	public CsCar cscColor(Short cscColor){
		this.cscColor = cscColor;
		this.setSeted(F.cscColor);
		return this;
	}
	/** 车辆主图     图片建议尺寸500*500像素（比例）   **/
	public CsCar cscPreview(String cscPreview){
		this.cscPreview = cscPreview;
		this.setSeted(F.cscPreview);
		return this;
	}
	/** 照片列表        **/
	public CsCar cscImages(String cscImages){
		this.cscImages = cscImages;
		this.setSeted(F.cscImages);
		return this;
	}
	/** 保养公里        **/
	public CsCar cscMaintKm(Integer cscMaintKm){
		this.cscMaintKm = cscMaintKm;
		this.setSeted(F.cscMaintKm);
		return this;
	}
	/** 初次登记        **/
	public CsCar cscCheckIn(Date cscCheckIn){
		this.cscCheckIn = cscCheckIn;
		this.setSeted(F.cscCheckIn);
		return this;
	}
	/** 上次年检日期        **/
	public CsCar cscAnnualPre(Date cscAnnualPre){
		this.cscAnnualPre = cscAnnualPre;
		this.setSeted(F.cscAnnualPre);
		return this;
	}
	/** 实际年检日期        **/
	public CsCar cscAnnual(Date cscAnnual){
		this.cscAnnual = cscAnnual;
		this.setSeted(F.cscAnnual);
		return this;
	}
	/** 具体年检信息        **/
	public CsCar cscAnnualInfo(String cscAnnualInfo){
		this.cscAnnualInfo = cscAnnualInfo;
		this.setSeted(F.cscAnnualInfo);
		return this;
	}
	/** 预计下次年检        **/
	public CsCar cscAnnualNext(Date cscAnnualNext){
		this.cscAnnualNext = cscAnnualNext;
		this.setSeted(F.cscAnnualNext);
		return this;
	}
	/** 交强险投保        **/
	public CsCar cscTiDate(Date cscTiDate){
		this.cscTiDate = cscTiDate;
		this.setSeted(F.cscTiDate);
		return this;
	}
	/** 交强险到期        **/
	public CsCar cscTiUnit(Date cscTiUnit){
		this.cscTiUnit = cscTiUnit;
		this.setSeted(F.cscTiUnit);
		return this;
	}
	/** 交强险保单        **/
	public CsCar cscTiNo(String cscTiNo){
		this.cscTiNo = cscTiNo;
		this.setSeted(F.cscTiNo);
		return this;
	}
	/** 商业险投保        **/
	public CsCar cscBiDate(Date cscBiDate){
		this.cscBiDate = cscBiDate;
		this.setSeted(F.cscBiDate);
		return this;
	}
	/** 商业险到期        **/
	public CsCar cscBiUnit(Date cscBiUnit){
		this.cscBiUnit = cscBiUnit;
		this.setSeted(F.cscBiUnit);
		return this;
	}
	/** 商业险保单        **/
	public CsCar cscBiNo(String cscBiNo){
		this.cscBiNo = cscBiNo;
		this.setSeted(F.cscBiNo);
		return this;
	}
	/** 商业险类型        **/
	public CsCar cscBiType(String cscBiType){
		this.cscBiType = cscBiType;
		this.setSeted(F.cscBiType);
		return this;
	}
	/** 商业险金额        **/
	public CsCar cscBiLimit(Double cscBiLimit){
		this.cscBiLimit = cscBiLimit;
		this.setSeted(F.cscBiLimit);
		return this;
	}
	/** 保险公司        **/
	public CsCar cscBiCompany(String cscBiCompany){
		this.cscBiCompany = cscBiCompany;
		this.setSeted(F.cscBiCompany);
		return this;
	}
	/** 修改人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsCar cscEditor(Long cscEditor){
		this.cscEditor = cscEditor;
		this.setSeted(F.cscEditor);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsCar cscUpdateTime(Date cscUpdateTime){
		this.cscUpdateTime = cscUpdateTime;
		this.setSeted(F.cscUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsCar cscAddTime(Date cscAddTime){
		this.cscAddTime = cscAddTime;
		this.setSeted(F.cscAddTime);
		return this;
	}
	/** 备注        **/
	public CsCar cscRemark(String cscRemark){
		this.cscRemark = cscRemark;
		this.setSeted(F.cscRemark);
		return this;
	}
	/** 备忘信息    textarea:content text:sign     **/
	public CsCar cscMemo(String cscMemo){
		this.cscMemo = cscMemo;
		this.setSeted(F.cscMemo);
		return this;
	}
	/** 上线密码        **/
	public CsCar cscOnPsd(String cscOnPsd){
		this.cscOnPsd = cscOnPsd;
		this.setSeted(F.cscOnPsd);
		return this;
	}
	/** 车机安装    0:未安装 1:正在安装 2:已安装     **/
	public CsCar cscSetup(Short cscSetup){
		this.cscSetup = cscSetup;
		this.setSeted(F.cscSetup);
		return this;
	}
	/** 下位机版本    0:1.0版 1:2.0版     **/
	public CsCar cscDeviceVesion(Short cscDeviceVesion){
		this.cscDeviceVesion = cscDeviceVesion;
		this.setSeted(F.cscDeviceVesion);
		return this;
	}
	/** 服务器标识        **/
	public CsCar cscMqttFlag(String cscMqttFlag){
		this.cscMqttFlag = cscMqttFlag;
		this.setSeted(F.cscMqttFlag);
		return this;
	}
	/** 程序掩码    1:假定条件     **/
	public CsCar cscMask(Long cscMask){
		this.cscMask = cscMask;
		this.setSeted(F.cscMask);
		return this;
	}
	/** 状态 [非空]   1:上线 2:下线 3:企业可租 0:无效     **/
	public CsCar cscStatus(Short cscStatus){
		this.cscStatus = cscStatus;
		this.setSeted(F.cscStatus);
		return this;
	}
	
	public CsCar cscTerNo(String cscTerNo) {
	    this.cscTerNo = cscTerNo;
	    this.setSeted(F.cscTerNo);
	    return this;
	}
	
	public CsCar cscBindPlatform(Short cscBindPlatform) {
	    this.cscBindPlatform = cscBindPlatform;
	    this.setSeted(F.cscBindPlatform);
	    return this;
	}
	
	//克隆对象
	public CsCar clone(){
		CsCar clone = new CsCar();
		clone.cscHost=this.cscHost;
		clone.cscNumber=this.cscNumber;
		clone.cscCarNo=this.cscCarNo;
		clone.cscModel=this.cscModel;
		clone.cscOutlets=this.cscOutlets;
		clone.cscProvid=this.cscProvid;
		clone.cscRebate=this.cscRebate;
		clone.cscIsAd=this.cscIsAd;
		clone.cscUpdateTime=this.cscUpdateTime;
		clone.cscAddTime=this.cscAddTime;
		clone.cscStatus=this.cscStatus;
		
		clone.cscTerNo=this.cscTerNo;
		clone.cscBindPlatform=this.cscBindPlatform;
		return clone;
	}
	
	
	/**
	 * 根据ID取车辆
	 * @param id
	 * @return
	 */
	public static @api CsCar get(Long id){		
		return getCsCarById(id);
	}
	/**
	 * 获取所有车辆
	 * @return
	 */
	public static @api List<CsCar> list(Map params,Integer size){
		return getCsCarList(params,size);
	}
	/**
	 * 获取车辆分页
	 * @return
	 */
	public static @api Page<CsCar> page(int page,int size,Map params){
		return getCsCarPage(page, size , params);
	}
	/**
	 * 根据查询条件取车辆
	 * @param params
	 * @return
	 */
	public static @api CsCar Get(Map params){
		return getCsCar(params);
	}
	/**
	 * 获取车辆数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsCarCount(params);
	}
	/**
	 * 获取车辆数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsCarEval(eval,params);
	}
	
	/**
	 * 根据ID取车辆
	 * @param id
	 * @return
	 */
	public static @api CsCar getCsCarById(Long id){		
		CsCar csCar = (CsCar) $.GetRequest("CsCar$"+id);
		if(csCar!=null)
			return csCar;
		com.ccclubs.service.admin.ICsCarService csCarService = $.GetSpringBean("csCarService");		
		return csCarService.getCsCarById(id);
	}
	
	
	/**
	 * 根据ID取车辆的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsCar.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsCar csCar = get(id);
		if(csCar!=null){
			String strValue = csCar.getCscNumber$();
			if(!"CscNumber".equals("CscNumber"))
				strValue+="("+csCar.getCscNumber$()+")";
			if(!"CscNumber".equals("CscModel"))
				strValue+="("+csCar.getCscModel$()+")";
			MemCache.setValue(CsCar.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCscNumber$();
		if(!"CscNumber".equals("CscNumber"))
			keyValue+="("+this.getCscNumber$()+")";
		if(!"CscNumber".equals("CscModel"))
			keyValue+="("+this.getCscModel$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有车辆
	 * @return
	 */
	public static @api List<CsCar> getCsCarList(Map params,Integer size){
		com.ccclubs.service.admin.ICsCarService csCarService = $.GetSpringBean("csCarService");
		return csCarService.getCsCarList(params, size);
	}
	
	/**
	 * 获取车辆分页
	 * @return
	 */
	public static @api Page<CsCar> getCsCarPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsCarService csCarService = $.GetSpringBean("csCarService");
		return csCarService.getCsCarPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取车辆
	 * @param params
	 * @return
	 */
	public static @api CsCar getCsCar(Map params){
		com.ccclubs.service.admin.ICsCarService csCarService = $.GetSpringBean("csCarService");
		return csCarService.getCsCar(params);
	}
	
	/**
	 * 获取车辆数
	 * @return
	 */
	public static @api Long getCsCarCount(Map params){
		com.ccclubs.service.admin.ICsCarService csCarService = $.GetSpringBean("csCarService");
		return csCarService.getCsCarCount(params);
	}
		
		
	/**
	 * 获取车辆自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsCarEval(String eval,Map params){
		com.ccclubs.service.admin.ICsCarService csCarService = $.GetSpringBean("csCarService");
		return csCarService.getCsCarEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsCar(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsCarService csCarService = $.GetSpringBean("csCarService");
		csCarService.updateCsCarByConfirm(set, where);
	}
	
	
	/**
	 * 保存车辆对象
	 * @param params
	 * @return
	 */
	public CsCar save(){
		com.ccclubs.service.admin.ICsCarService csCarService = $.GetSpringBean("csCarService");
		if(this.getCscId()!=null)
			csCarService.updateCsCar(this);
		else
			return csCarService.saveCsCar(this);
		return this;
	}
	
	
	/**
	 * 更新车辆对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsCarService csCarService = $.GetSpringBean("csCarService");
		csCarService.updateCsCar$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsCarService csCarService = $.GetSpringBean("csCarService");
		if($.equals($.config("logic_delete"),"true"))
			csCarService.removeCsCarById(this.getCscId());
		else
			csCarService.deleteCsCarById(this.getCscId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsCarService csCarService = $.GetSpringBean("csCarService");
		return csCarService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCscId(){
		return this.cscId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCscId$(){
		String strValue="";
		 strValue=$.str(this.getCscId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCscId(Long cscId){
		this.cscId = cscId;
		this.setSeted(F.cscId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCscHost(){
		return this.cscHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCscHost$(){
		String strValue="";
		if(this.getCscHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCscHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCscHost(Long cscHost){
		this.cscHost = cscHost;
		this.setSeted(F.cscHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cscHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCscHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCscHost()!=null){
 			srvHost = SrvHost.get(this.getCscHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCscHost(), srvHost);
	 	return srvHost;
	}
	/*******************************车牌号**********************************/	
	/**
	* 车牌号 [非空]      
	**/
	public String getCscNumber(){
		return this.cscNumber;
	}
	/**
	* 获取车牌号格式化(toString)
	**/
	public String getCscNumber$(){
		String strValue="";
		 strValue=$.str(this.getCscNumber());
	 	 return strValue;
	}
	/**
	* 车牌号 [非空]      
	**/
	public void setCscNumber(String cscNumber){
		this.cscNumber = cscNumber;
		this.setSeted(F.cscNumber);
	}
	/*******************************车牌名称**********************************/	
	/**
	* 车牌名称 [非空]      
	**/
	public String getCscCarNo(){
		return this.cscCarNo;
	}
	/**
	* 获取车牌名称格式化(toString)
	**/
	public String getCscCarNo$(){
		String strValue="";
		 strValue=$.str(this.getCscCarNo());
	 	 return strValue;
	}
	/**
	* 车牌名称 [非空]      
	**/
	public void setCscCarNo(String cscCarNo){
		this.cscCarNo = cscCarNo;
		this.setSeted(F.cscCarNo);
	}
	/*******************************车型**********************************/	
	/**
	* 车型 [非空] [CsCarModel]     
	**/
	public Long getCscModel(){
		return this.cscModel;
	}
	/**
	* 获取车型格式化(toString)
	**/
	public String getCscModel$(){
		String strValue="";
		if(this.getCscModel()!=null){
				strValue+=$.str(CsCarModel.getKeyValue(this.getCscModel()));
		}			
	 	 return strValue;
	}
	/**
	* 车型 [非空] [CsCarModel]     
	**/
	public void setCscModel(Long cscModel){
		this.cscModel = cscModel;
		this.setSeted(F.cscModel);
	}
	/**
	* 获取关联对象[车型]
	**/	 			
 	public CsCarModel get$cscModel(){
 		com.ccclubs.model.CsCarModel csCarModel = (com.ccclubs.model.CsCarModel) $.GetRequest("CsCarModel$"+this.getCscModel());
 		if(csCarModel!=null)
			return csCarModel;
		if(this.getCscModel()!=null){
 			csCarModel = CsCarModel.get(this.getCscModel());
 		}
 		$.SetRequest("CsCarModel$"+this.getCscModel(), csCarModel);
	 	return csCarModel;
	}
	/*******************************网点**********************************/	
	/**
	* 网点 [非空] [CsOutlets]  {cscHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param}
	**/
	public Long getCscOutlets(){
		return this.cscOutlets;
	}
	/**
	* 获取网点格式化(toString)
	**/
	public String getCscOutlets$(){
		String strValue="";
		if(this.getCscOutlets()!=null){
				strValue+=$.str(CsOutlets.getKeyValue(this.getCscOutlets()));
		}			
	 	 return strValue;
	}
	/**
	* 网点 [非空] [CsOutlets]  {cscHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param}
	**/
	public void setCscOutlets(Long cscOutlets){
		this.cscOutlets = cscOutlets;
		this.setSeted(F.cscOutlets);
	}
	/**
	* 获取关联对象[网点]
	**/	 			
 	public CsOutlets get$cscOutlets(){
 		com.ccclubs.model.CsOutlets csOutlets = (com.ccclubs.model.CsOutlets) $.GetRequest("CsOutlets$"+this.getCscOutlets());
 		if(csOutlets!=null)
			return csOutlets;
		if(this.getCscOutlets()!=null){
 			csOutlets = CsOutlets.get(this.getCscOutlets());
 		}
 		$.SetRequest("CsOutlets$"+this.getCscOutlets(), csOutlets);
	 	return csOutlets;
	}
	/*******************************反向关联的系统订单列表**********************************/	
	/**
	* 获取与当前对象反向关联的系统订单列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsOrder> getOrders(){
		com.ccclubs.service.admin.ICsOrderService csOrderService = $.GetSpringBean("csOrderService");
		Map params = new HashMap();
		params.put("csoCar",this.getCscId());
		return csOrderService.getCsOrderList(params,-1);
	}
	/**
	* 获取订单记录格式化(toString)
	**/
	public String getOrders$(){
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
		params.put("csvCar",this.getCscId());
		return csViolatService.getCsViolatList(params,-1);
	}
	/**
	* 获取违章记录格式化(toString)
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
		params.put("cstCar",this.getCscId());
		return csTroubleService.getCsTroubleList(params,-1);
	}
	/**
	* 获取事故记录格式化(toString)
	**/
	public String getTroubles$(){
		return "[...]";
	}
	/*******************************反向关联的车辆维修列表**********************************/	
	/**
	* 获取与当前对象反向关联的车辆维修列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsRepair> getRepairs(){
		com.ccclubs.service.admin.ICsRepairService csRepairService = $.GetSpringBean("csRepairService");
		Map params = new HashMap();
		params.put("csrCar",this.getCscId());
		return csRepairService.getCsRepairList(params,-1);
	}
	/**
	* 获取维修记录格式化(toString)
	**/
	public String getRepairs$(){
		return "[...]";
	}
	/*******************************反向关联的车辆保养列表**********************************/	
	/**
	* 获取与当前对象反向关联的车辆保养列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsMaintain> getMaintains(){
		com.ccclubs.service.admin.ICsMaintainService csMaintainService = $.GetSpringBean("csMaintainService");
		Map params = new HashMap();
		params.put("csmNumber",this.getCscId());
		return csMaintainService.getCsMaintainList(params,-1);
	}
	/**
	* 获取保养记录格式化(toString)
	**/
	public String getMaintains$(){
		return "[...]";
	}
	/*******************************反向关联的车辆上线记录列表**********************************/	
	/**
	* 获取与当前对象反向关联的车辆上线记录列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsCarOn> getOnlines(){
		com.ccclubs.service.admin.ICsCarOnService csCarOnService = $.GetSpringBean("csCarOnService");
		Map params = new HashMap();
		params.put("cscoCar",this.getCscId());
		return csCarOnService.getCsCarOnList(params,-1);
	}
	/**
	* 获取上下线记录格式化(toString)
	**/
	public String getOnlines$(){
		return "[...]";
	}
	/*******************************供应商**********************************/	
	/**
	* 供应商 [非空] [CsProvid]     
	**/
	public Long getCscProvid(){
		return this.cscProvid;
	}
	/**
	* 获取供应商格式化(toString)
	**/
	public String getCscProvid$(){
		String strValue="";
		if(this.getCscProvid()!=null){
				strValue+=$.str(CsProvid.getKeyValue(this.getCscProvid()));
		}			
	 	 return strValue;
	}
	/**
	* 供应商 [非空] [CsProvid]     
	**/
	public void setCscProvid(Long cscProvid){
		this.cscProvid = cscProvid;
		this.setSeted(F.cscProvid);
	}
	/**
	* 获取关联对象[供应商]
	**/	 			
 	public CsProvid get$cscProvid(){
 		com.ccclubs.model.CsProvid csProvid = (com.ccclubs.model.CsProvid) $.GetRequest("CsProvid$"+this.getCscProvid());
 		if(csProvid!=null)
			return csProvid;
		if(this.getCscProvid()!=null){
 			csProvid = CsProvid.get(this.getCscProvid());
 		}
 		$.SetRequest("CsProvid$"+this.getCscProvid(), csProvid);
	 	return csProvid;
	}
	/*******************************折扣**********************************/	
	/**
	* 折扣 [非空]    0到1,比如0.75表示七五折  
	**/
	public Double getCscRebate(){
		return this.cscRebate;
	}
	/**
	* 获取折扣格式化(toString)
	**/
	public String getCscRebate$(){
		String strValue="";
		 strValue=$.str(this.getCscRebate());	
	 	 return strValue;
	}
	/**
	* 折扣 [非空]    0到1,比如0.75表示七五折  
	**/
	public void setCscRebate(Double cscRebate){
		this.cscRebate = cscRebate;
		this.setSeted(F.cscRebate);
	}
	/*******************************广告车？**********************************/	
	/**
	* 广告车？ [非空]      
	**/
	public Boolean getCscIsAd(){
		return this.cscIsAd;
	}
	/**
	* 获取广告车？格式化(toString)
	**/
	public String getCscIsAd$(){
		String strValue="";
		 strValue=$.str((this.getCscIsAd()!=null && this.getCscIsAd())?"是":"否");					 	
	 	 return strValue;
	}
	/**
	* 广告车？ [非空]      
	**/
	public void setCscIsAd(Boolean cscIsAd){
		this.cscIsAd = cscIsAd;
		this.setSeted(F.cscIsAd);
	}
	/*******************************经度**********************************/	
	/**
	* 经度       
	**/
	public Double getCscLongitude(){
		return this.cscLongitude;
	}
	/**
	* 获取经度格式化(toString)
	**/
	public String getCscLongitude$(){
		String strValue="";
		 strValue=$.str(this.getCscLongitude());	
	 	 return strValue;
	}
	/**
	* 经度       
	**/
	public void setCscLongitude(Double cscLongitude){
		this.cscLongitude = cscLongitude;
		this.setSeted(F.cscLongitude);
	}
	/*******************************纬度**********************************/	
	/**
	* 纬度       
	**/
	public Double getCscLatitude(){
		return this.cscLatitude;
	}
	/**
	* 获取纬度格式化(toString)
	**/
	public String getCscLatitude$(){
		String strValue="";
		 strValue=$.str(this.getCscLatitude());	
	 	 return strValue;
	}
	/**
	* 纬度       
	**/
	public void setCscLatitude(Double cscLatitude){
		this.cscLatitude = cscLatitude;
		this.setSeted(F.cscLatitude);
	}
	/*******************************手机号码**********************************/	
	/**
	* 手机号码       
	**/
	public String getCscMobile(){
		return this.cscMobile;
	}
	/**
	* 获取手机号码格式化(toString)
	**/
	public String getCscMobile$(){
		String strValue="";
		 strValue=$.str(this.getCscMobile());
	 	 return strValue;
	}
	/**
	* 手机号码       
	**/
	public void setCscMobile(String cscMobile){
		this.cscMobile = cscMobile;
		this.setSeted(F.cscMobile);
	}
	/*******************************服务密码**********************************/	
	/**
	* 服务密码       
	**/
	public String getCscPassword(){
		return this.cscPassword;
	}
	/**
	* 获取服务密码格式化(toString)
	**/
	public String getCscPassword$(){
		String strValue="";
		 strValue=$.str(this.getCscPassword());
	 	 return strValue;
	}
	/**
	* 服务密码       
	**/
	public void setCscPassword(String cscPassword){
		this.cscPassword = cscPassword;
		this.setSeted(F.cscPassword);
	}
	/*******************************加油卡号**********************************/	
	/**
	* 加油卡号       
	**/
	public String getCscOilCard(){
		return this.cscOilCard;
	}
	/**
	* 获取加油卡号格式化(toString)
	**/
	public String getCscOilCard$(){
		String strValue="";
		 strValue=$.str(this.getCscOilCard());
	 	 return strValue;
	}
	/**
	* 加油卡号       
	**/
	public void setCscOilCard(String cscOilCard){
		this.cscOilCard = cscOilCard;
		this.setSeted(F.cscOilCard);
	}
	/*******************************车架号**********************************/	
	/**
	* 车架号       
	**/
	public String getCscVin(){
		return this.cscVin;
	}
	/**
	* 获取车架号格式化(toString)
	**/
	public String getCscVin$(){
		String strValue="";
		 strValue=$.str(this.getCscVin());
	 	 return strValue;
	}
	/**
	* 车架号       
	**/
	public void setCscVin(String cscVin){
		this.cscVin = cscVin;
		this.setSeted(F.cscVin);
	}
	/*******************************发动机号**********************************/	
	/**
	* 发动机号       
	**/
	public String getCscEngineNo(){
		return this.cscEngineNo;
	}
	/**
	* 获取发动机号格式化(toString)
	**/
	public String getCscEngineNo$(){
		String strValue="";
		 strValue=$.str(this.getCscEngineNo());
	 	 return strValue;
	}
	/**
	* 发动机号       
	**/
	public void setCscEngineNo(String cscEngineNo){
		this.cscEngineNo = cscEngineNo;
		this.setSeted(F.cscEngineNo);
	}
	/*******************************购车日**********************************/	
	/**
	* 购车日       
	**/
	public Date getCscBuyDate(){
		return this.cscBuyDate;
	}
	/**
	* 获取购车日格式化(toString)
	**/
	public String getCscBuyDate$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscBuyDate(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 购车日       
	**/
	public void setCscBuyDate(Date cscBuyDate){
		this.cscBuyDate = cscBuyDate;
		this.setSeted(F.cscBuyDate);
	}
	/*******************************合同号**********************************/	
	/**
	* 合同号       
	**/
	public String getCscBargainNo(){
		return this.cscBargainNo;
	}
	/**
	* 获取合同号格式化(toString)
	**/
	public String getCscBargainNo$(){
		String strValue="";
		 strValue=$.str(this.getCscBargainNo());
	 	 return strValue;
	}
	/**
	* 合同号       
	**/
	public void setCscBargainNo(String cscBargainNo){
		this.cscBargainNo = cscBargainNo;
		this.setSeted(F.cscBargainNo);
	}
	/*******************************购置税**********************************/	
	/**
	* 购置税       
	**/
	public Double getCscTaxPrice(){
		return this.cscTaxPrice;
	}
	/**
	* 获取购置税格式化(toString)
	**/
	public String getCscTaxPrice$(){
		String strValue="";
		 strValue=$.str(this.getCscTaxPrice());	
	 	 return strValue;
	}
	/**
	* 购置税       
	**/
	public void setCscTaxPrice(Double cscTaxPrice){
		this.cscTaxPrice = cscTaxPrice;
		this.setSeted(F.cscTaxPrice);
	}
	/*******************************车价**********************************/	
	/**
	* 车价       
	**/
	public Double getCscBuyPrice(){
		return this.cscBuyPrice;
	}
	/**
	* 获取车价格式化(toString)
	**/
	public String getCscBuyPrice$(){
		String strValue="";
		 strValue=$.str(this.getCscBuyPrice());	
	 	 return strValue;
	}
	/**
	* 车价       
	**/
	public void setCscBuyPrice(Double cscBuyPrice){
		this.cscBuyPrice = cscBuyPrice;
		this.setSeted(F.cscBuyPrice);
	}
	/*******************************合格证号**********************************/	
	/**
	* 合格证号       
	**/
	public String getCscCertific(){
		return this.cscCertific;
	}
	/**
	* 获取合格证号格式化(toString)
	**/
	public String getCscCertific$(){
		String strValue="";
		 strValue=$.str(this.getCscCertific());
	 	 return strValue;
	}
	/**
	* 合格证号       
	**/
	public void setCscCertific(String cscCertific){
		this.cscCertific = cscCertific;
		this.setSeted(F.cscCertific);
	}
	/*******************************厂牌型号**********************************/	
	/**
	* 厂牌型号       
	**/
	public String getCscFactoryNo(){
		return this.cscFactoryNo;
	}
	/**
	* 获取厂牌型号格式化(toString)
	**/
	public String getCscFactoryNo$(){
		String strValue="";
		 strValue=$.str(this.getCscFactoryNo());
	 	 return strValue;
	}
	/**
	* 厂牌型号       
	**/
	public void setCscFactoryNo(String cscFactoryNo){
		this.cscFactoryNo = cscFactoryNo;
		this.setSeted(F.cscFactoryNo);
	}
	/*******************************颜色**********************************/	
	/**
	* 颜色    0:黑色 1:白色 2:红色 3:绿色 4:黄色 5:橙色 6:银色 7:蓝色 8:深蓝 9:深灰 10:深红    
	**/
	public Short getCscColor(){
		return this.cscColor;
	}
	/**
	* 获取颜色格式化(toString)
	**/
	public String getCscColor$(){
		String strValue="";
		 if($.equals($.str(this.getCscColor()),"0"))
			strValue=$.str("黑色");		 
		 if($.equals($.str(this.getCscColor()),"1"))
			strValue=$.str("白色");		 
		 if($.equals($.str(this.getCscColor()),"2"))
			strValue=$.str("红色");		 
		 if($.equals($.str(this.getCscColor()),"3"))
			strValue=$.str("绿色");		 
		 if($.equals($.str(this.getCscColor()),"4"))
			strValue=$.str("黄色");		 
		 if($.equals($.str(this.getCscColor()),"5"))
			strValue=$.str("橙色");		 
		 if($.equals($.str(this.getCscColor()),"6"))
			strValue=$.str("银色");		 
		 if($.equals($.str(this.getCscColor()),"7"))
			strValue=$.str("蓝色");		 
		 if($.equals($.str(this.getCscColor()),"8"))
			strValue=$.str("深蓝");		 
		 if($.equals($.str(this.getCscColor()),"9"))
			strValue=$.str("深灰");		 
		 if($.equals($.str(this.getCscColor()),"10"))
			strValue=$.str("深红");		 
	 	 return strValue;
	}
	/**
	* 颜色    0:黑色 1:白色 2:红色 3:绿色 4:黄色 5:橙色 6:银色 7:蓝色 8:深蓝 9:深灰 10:深红    
	**/
	public void setCscColor(Short cscColor){
		this.cscColor = cscColor;
		this.setSeted(F.cscColor);
	}
	/*******************************车辆主图**********************************/	
	/**
	* 车辆主图     图片建议尺寸500*500像素（比例）  
	**/
	public String getCscPreview(){
		return this.cscPreview;
	}
	/**
	* 获取车辆主图格式化(toString)
	**/
	public String getCscPreview$(){
		String strValue="";
		 strValue=$.str(this.getCscPreview());
	 	 return strValue;
	}
	/**
	* 车辆主图     图片建议尺寸500*500像素（比例）  
	**/
	public void setCscPreview(String cscPreview){
		this.cscPreview = cscPreview;
		this.setSeted(F.cscPreview);
	}
	/*******************************照片列表**********************************/	
	/**
	* 照片列表       
	**/
	public String getCscImages(){
		return this.cscImages;
	}
	/**
	* 获取照片列表格式化(toString)
	**/
	public String getCscImages$(){
		String strValue="";
		 strValue=$.str(this.getCscImages());
	 	 return strValue;
	}
	/**
	* 照片列表       
	**/
	public void setCscImages(String cscImages){
		this.cscImages = cscImages;
		this.setSeted(F.cscImages);
	}
	/*******************************保养公里**********************************/	
	/**
	* 保养公里       
	**/
	public Integer getCscMaintKm(){
		return this.cscMaintKm;
	}
	/**
	* 获取保养公里格式化(toString)
	**/
	public String getCscMaintKm$(){
		String strValue="";
		 strValue=$.str(this.getCscMaintKm());
	 	 return strValue;
	}
	/**
	* 保养公里       
	**/
	public void setCscMaintKm(Integer cscMaintKm){
		this.cscMaintKm = cscMaintKm;
		this.setSeted(F.cscMaintKm);
	}
	/*******************************初次登记**********************************/	
	/**
	* 初次登记       
	**/
	public Date getCscCheckIn(){
		return this.cscCheckIn;
	}
	/**
	* 获取初次登记格式化(toString)
	**/
	public String getCscCheckIn$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscCheckIn(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 初次登记       
	**/
	public void setCscCheckIn(Date cscCheckIn){
		this.cscCheckIn = cscCheckIn;
		this.setSeted(F.cscCheckIn);
	}
	/*******************************上次年检日期**********************************/	
	/**
	* 上次年检日期       
	**/
	public Date getCscAnnualPre(){
		return this.cscAnnualPre;
	}
	/**
	* 获取上次年检日期格式化(toString)
	**/
	public String getCscAnnualPre$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscAnnualPre(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 上次年检日期       
	**/
	public void setCscAnnualPre(Date cscAnnualPre){
		this.cscAnnualPre = cscAnnualPre;
		this.setSeted(F.cscAnnualPre);
	}
	/*******************************实际年检日期**********************************/	
	/**
	* 实际年检日期       
	**/
	public Date getCscAnnual(){
		return this.cscAnnual;
	}
	/**
	* 获取实际年检日期格式化(toString)
	**/
	public String getCscAnnual$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscAnnual(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 实际年检日期       
	**/
	public void setCscAnnual(Date cscAnnual){
		this.cscAnnual = cscAnnual;
		this.setSeted(F.cscAnnual);
	}
	/*******************************具体年检信息**********************************/	
	/**
	* 具体年检信息       
	**/
	public String getCscAnnualInfo(){
		return this.cscAnnualInfo;
	}
	/**
	* 获取具体年检信息格式化(toString)
	**/
	public String getCscAnnualInfo$(){
		String strValue="";
		 strValue=$.str(this.getCscAnnualInfo());
	 	 return strValue;
	}
	/**
	* 具体年检信息       
	**/
	public void setCscAnnualInfo(String cscAnnualInfo){
		this.cscAnnualInfo = cscAnnualInfo;
		this.setSeted(F.cscAnnualInfo);
	}
	/*******************************预计下次年检**********************************/	
	/**
	* 预计下次年检       
	**/
	public Date getCscAnnualNext(){
		return this.cscAnnualNext;
	}
	/**
	* 获取预计下次年检格式化(toString)
	**/
	public String getCscAnnualNext$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscAnnualNext(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 预计下次年检       
	**/
	public void setCscAnnualNext(Date cscAnnualNext){
		this.cscAnnualNext = cscAnnualNext;
		this.setSeted(F.cscAnnualNext);
	}
	/*******************************交强险投保**********************************/	
	/**
	* 交强险投保       
	**/
	public Date getCscTiDate(){
		return this.cscTiDate;
	}
	/**
	* 获取交强险投保格式化(toString)
	**/
	public String getCscTiDate$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscTiDate(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 交强险投保       
	**/
	public void setCscTiDate(Date cscTiDate){
		this.cscTiDate = cscTiDate;
		this.setSeted(F.cscTiDate);
	}
	/*******************************交强险到期**********************************/	
	/**
	* 交强险到期       
	**/
	public Date getCscTiUnit(){
		return this.cscTiUnit;
	}
	/**
	* 获取交强险到期格式化(toString)
	**/
	public String getCscTiUnit$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscTiUnit(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 交强险到期       
	**/
	public void setCscTiUnit(Date cscTiUnit){
		this.cscTiUnit = cscTiUnit;
		this.setSeted(F.cscTiUnit);
	}
	/*******************************交强险保单**********************************/	
	/**
	* 交强险保单       
	**/
	public String getCscTiNo(){
		return this.cscTiNo;
	}
	/**
	* 获取交强险保单格式化(toString)
	**/
	public String getCscTiNo$(){
		String strValue="";
		 strValue=$.str(this.getCscTiNo());
	 	 return strValue;
	}
	/**
	* 交强险保单       
	**/
	public void setCscTiNo(String cscTiNo){
		this.cscTiNo = cscTiNo;
		this.setSeted(F.cscTiNo);
	}
	/*******************************商业险投保**********************************/	
	/**
	* 商业险投保       
	**/
	public Date getCscBiDate(){
		return this.cscBiDate;
	}
	/**
	* 获取商业险投保格式化(toString)
	**/
	public String getCscBiDate$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscBiDate(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 商业险投保       
	**/
	public void setCscBiDate(Date cscBiDate){
		this.cscBiDate = cscBiDate;
		this.setSeted(F.cscBiDate);
	}
	/*******************************商业险到期**********************************/	
	/**
	* 商业险到期       
	**/
	public Date getCscBiUnit(){
		return this.cscBiUnit;
	}
	/**
	* 获取商业险到期格式化(toString)
	**/
	public String getCscBiUnit$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscBiUnit(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 商业险到期       
	**/
	public void setCscBiUnit(Date cscBiUnit){
		this.cscBiUnit = cscBiUnit;
		this.setSeted(F.cscBiUnit);
	}
	/*******************************商业险保单**********************************/	
	/**
	* 商业险保单       
	**/
	public String getCscBiNo(){
		return this.cscBiNo;
	}
	/**
	* 获取商业险保单格式化(toString)
	**/
	public String getCscBiNo$(){
		String strValue="";
		 strValue=$.str(this.getCscBiNo());
	 	 return strValue;
	}
	/**
	* 商业险保单       
	**/
	public void setCscBiNo(String cscBiNo){
		this.cscBiNo = cscBiNo;
		this.setSeted(F.cscBiNo);
	}
	/*******************************商业险类型**********************************/	
	/**
	* 商业险类型       
	**/
	public String getCscBiType(){
		return this.cscBiType;
	}
	/**
	* 获取商业险类型格式化(toString)
	**/
	public String getCscBiType$(){
		String strValue="";
		 strValue=$.str(this.getCscBiType());
	 	 return strValue;
	}
	/**
	* 商业险类型       
	**/
	public void setCscBiType(String cscBiType){
		this.cscBiType = cscBiType;
		this.setSeted(F.cscBiType);
	}
	/*******************************商业险金额**********************************/	
	/**
	* 商业险金额       
	**/
	public Double getCscBiLimit(){
		return this.cscBiLimit;
	}
	/**
	* 获取商业险金额格式化(toString)
	**/
	public String getCscBiLimit$(){
		String strValue="";
		 strValue=$.str(this.getCscBiLimit());	
	 	 return strValue;
	}
	/**
	* 商业险金额       
	**/
	public void setCscBiLimit(Double cscBiLimit){
		this.cscBiLimit = cscBiLimit;
		this.setSeted(F.cscBiLimit);
	}
	/*******************************保险公司**********************************/	
	/**
	* 保险公司       
	**/
	public String getCscBiCompany(){
		return this.cscBiCompany;
	}
	/**
	* 获取保险公司格式化(toString)
	**/
	public String getCscBiCompany$(){
		String strValue="";
		 strValue=$.str(this.getCscBiCompany());
	 	 return strValue;
	}
	/**
	* 保险公司       
	**/
	public void setCscBiCompany(String cscBiCompany){
		this.cscBiCompany = cscBiCompany;
		this.setSeted(F.cscBiCompany);
	}
	/*******************************修改人**********************************/	
	/**
	* 修改人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCscEditor(){
		return this.cscEditor;
	}
	/**
	* 获取修改人格式化(toString)
	**/
	public String getCscEditor$(){
		String strValue="";
		if(this.getCscEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCscEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 修改人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCscEditor(Long cscEditor){
		this.cscEditor = cscEditor;
		this.setSeted(F.cscEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cscEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCscEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCscEditor()!=null){
 			srvUser = SrvUser.get(this.getCscEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCscEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCscUpdateTime(){
		return this.cscUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCscUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCscUpdateTime(Date cscUpdateTime){
		this.cscUpdateTime = cscUpdateTime;
		this.setSeted(F.cscUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCscAddTime(){
		return this.cscAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCscAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCscAddTime(Date cscAddTime){
		this.cscAddTime = cscAddTime;
		this.setSeted(F.cscAddTime);
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getCscRemark(){
		return this.cscRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCscRemark$(){
		String strValue="";
		 strValue=$.str(this.getCscRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setCscRemark(String cscRemark){
		this.cscRemark = cscRemark;
		this.setSeted(F.cscRemark);
	}
	/*******************************备忘信息**********************************/	
	/**
	* 备忘信息    textarea:content text:sign    
	**/
	public String getCscMemo(){
		return this.cscMemo;
	}
	/**
	* 获取备忘信息格式化(toString)
	**/
	public String getCscMemo$(){
		String strValue="";
		 strValue=$.str(this.getCscMemo());
	 	 return strValue;
	}
	/**
	* 备忘信息    textarea:content text:sign    
	**/
	public void setCscMemo(String cscMemo){
		this.cscMemo = cscMemo;
		this.setSeted(F.cscMemo);
	}
	/**
	* 备忘信息对应内部类
	**/
	class CscMemo{
		String content;//备忘内容
		String sign;//签名
		public void setContent(String content){
			this.content=content;
		}
		public String getContent(){
			return this.content;
		}
		public void setSign(String sign){
			this.sign=sign;
		}
		public String getSign(){
			return this.sign;
		}
	}
	/**
	* 获取备忘信息格式化列表
	**/ 			
 	public List<CscMemo> get$cscMemo(){
 		List<CscMemo> array = new ArrayList();
 		List<Map> list = $.eval(this.cscMemo);
 		for(Map map:list){
 			CscMemo cscMemo = new CscMemo();
 			cscMemo.setContent($.parseString(map.get("content")));
 			cscMemo.setSign($.parseString(map.get("sign")));
 			array.add(cscMemo);
 		}
 		return array;
 	}
	/*******************************上线密码**********************************/	
	/**
	* 上线密码       
	**/
	public String getCscOnPsd(){
		return this.cscOnPsd;
	}
	/**
	* 获取上线密码格式化(toString)
	**/
	public String getCscOnPsd$(){
		String strValue="";
		 strValue=$.str(this.getCscOnPsd());
	 	 return strValue;
	}
	/**
	* 上线密码       
	**/
	public void setCscOnPsd(String cscOnPsd){
		this.cscOnPsd = cscOnPsd;
		this.setSeted(F.cscOnPsd);
	}
	/*******************************车机安装**********************************/	
	/**
	* 车机安装    0:未安装 1:正在安装 2:已安装    
	**/
	public Short getCscSetup(){
		return this.cscSetup;
	}
	/**
	* 获取车机安装格式化(toString)
	**/
	public String getCscSetup$(){
		String strValue="";
		 if($.equals($.str(this.getCscSetup()),"0"))
			strValue=$.str("未安装");		 
		 if($.equals($.str(this.getCscSetup()),"1"))
			strValue=$.str("正在安装");		 
		 if($.equals($.str(this.getCscSetup()),"2"))
			strValue=$.str("已安装");		 
	 	 return strValue;
	}
	/**
	* 车机安装    0:未安装 1:正在安装 2:已安装    
	**/
	public void setCscSetup(Short cscSetup){
		this.cscSetup = cscSetup;
		this.setSeted(F.cscSetup);
	}
	/*******************************下位机版本**********************************/	
	/**
	* 下位机版本    0:1.0版 1:2.0版    
	**/
	public Short getCscDeviceVesion(){
		return this.cscDeviceVesion;
	}
	/**
	* 获取下位机版本格式化(toString)
	**/
	public String getCscDeviceVesion$(){
		String strValue="";
		 if($.equals($.str(this.getCscDeviceVesion()),"0"))
			strValue=$.str("1.0版");		 
		 if($.equals($.str(this.getCscDeviceVesion()),"1"))
			strValue=$.str("2.0版");		 
	 	 return strValue;
	}
	/**
	* 下位机版本    0:1.0版 1:2.0版    
	**/
	public void setCscDeviceVesion(Short cscDeviceVesion){
		this.cscDeviceVesion = cscDeviceVesion;
		this.setSeted(F.cscDeviceVesion);
	}
	/*******************************服务器标识**********************************/	
	/**
	* 服务器标识       
	**/
	public String getCscMqttFlag(){
		return this.cscMqttFlag;
	}
	/**
	* 获取服务器标识格式化(toString)
	**/
	public String getCscMqttFlag$(){
		String strValue="";
		 strValue=$.str(this.getCscMqttFlag());
	 	 return strValue;
	}
	/**
	* 服务器标识       
	**/
	public void setCscMqttFlag(String cscMqttFlag){
		this.cscMqttFlag = cscMqttFlag;
		this.setSeted(F.cscMqttFlag);
	}
	/*******************************程序掩码**********************************/	
	/**
	* 程序掩码    1:假定条件    
	**/
	public Long getCscMask(){
		return this.cscMask;
	}
	/**
	* 获取程序掩码格式化(toString)
	**/
	public String getCscMask$(){
		String strValue="";
		if(this.getCscMask()!=null){
			if((this.getCscMask() & 1 ) == 1)
				strValue+="["+$.str("假定条件")+"]";		 
		}
	 	 return strValue;
	}
	/**
	* 程序掩码    1:假定条件    
	**/
	public void setCscMask(Long cscMask){
		this.cscMask = cscMask;
		this.setSeted(F.cscMask);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:上线 2:下线 3:企业可租 0:无效    
	**/
	public Short getCscStatus(){
		return this.cscStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCscStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCscStatus()),"1"))
			strValue=$.str("上线");		 
		 if($.equals($.str(this.getCscStatus()),"2"))
			strValue=$.str("下线");		 
		 if($.equals($.str(this.getCscStatus()),"3"))
			strValue=$.str("企业可租");		 
		 if($.equals($.str(this.getCscStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:上线 2:下线 3:企业可租 0:无效    
	**/
	public void setCscStatus(Short cscStatus){
		this.cscStatus = cscStatus;
		this.setSeted(F.cscStatus);
	}
	
	/*******************************终端序列号**********************************/  

    public String getCscTerNo(){
        return this.cscTerNo;
    }
    /**
    * 获取终端序列号格式化(toString)
    **/
    public String getCscTerNo$(){    
         return $.str(getCscTerNo());
    }
    /**
    * 终端序列号
    **/
    public void setCscTerNo(String cscTerNo){
        this.cscTerNo = cscTerNo;
        this.setSeted(F.cscTerNo);
    }
    
    /*******************************绑定平台**********************************/   
    /**
    * 所绑定平台  0: 北京平台, 1： 车机中心 
    **/
    public Short getCscBindPlatform(){
        return this.cscBindPlatform;
    }
    /**
    * 获取状态格式化(toString)
    **/
    public String getCscBindPlatform$(){
        String strValue="";
         if($.equals($.str(this.getCscBindPlatform()),"0"))
            strValue=$.str("北京平台");        
         if($.equals($.str(this.getCscBindPlatform()),"1"))
            strValue=$.str("车机中心");        
         return strValue;
    }
    /**
    * 所绑定平台  0: 北京平台, 1： 车机中心 
    **/
    public void setCscBindPlatform(Short cscBindPlatform){
        this.cscBindPlatform = cscBindPlatform;
        this.setSeted(F.cscBindPlatform);
    }
    
    
    
	/************LAZY3Q_DEFINE_CODE************/
	public class TimelineMap extends HashMap{
		@Override
		public Object get(Object key) {
			return com.ccclubs.helper.TimeLineHelper.getTimelines(CsCar.this.getCscId(), $.parseInt(key));
		}
	}
	public TimelineMap getTimelines(){
		return new TimelineMap();
	}
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsCar.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCar.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCar.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsCar.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsCar.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCar.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCar.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsCar.this);
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
		public M cscId(Object cscId){this.put("cscId", cscId);return this;};
	 	/** and csc_id is null */
 		public M cscIdNull(){if(this.get("cscIdNot")==null)this.put("cscIdNot", "");this.put("cscId", null);return this;};
 		/** not .... */
 		public M cscIdNot(){this.put("cscIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M cscHost(Object cscHost){this.put("cscHost", cscHost);return this;};
	 	/** and csc_host is null */
 		public M cscHostNull(){if(this.get("cscHostNot")==null)this.put("cscHostNot", "");this.put("cscHost", null);return this;};
 		/** not .... */
 		public M cscHostNot(){this.put("cscHostNot", "not");return this;};
		/** 车牌号 [非空]       **/
		public M cscNumber(Object cscNumber){this.put("cscNumber", cscNumber);return this;};
	 	/** and csc_number is null */
 		public M cscNumberNull(){if(this.get("cscNumberNot")==null)this.put("cscNumberNot", "");this.put("cscNumber", null);return this;};
 		/** not .... */
 		public M cscNumberNot(){this.put("cscNumberNot", "not");return this;};
		/** 车牌名称 [非空]       **/
		public M cscCarNo(Object cscCarNo){this.put("cscCarNo", cscCarNo);return this;};
	 	/** and csc_car_no is null */
 		public M cscCarNoNull(){if(this.get("cscCarNoNot")==null)this.put("cscCarNoNot", "");this.put("cscCarNo", null);return this;};
 		/** not .... */
 		public M cscCarNoNot(){this.put("cscCarNoNot", "not");return this;};
		/** 车型 [非空] [CsCarModel]      **/
		public M cscModel(Object cscModel){this.put("cscModel", cscModel);return this;};
	 	/** and csc_model is null */
 		public M cscModelNull(){if(this.get("cscModelNot")==null)this.put("cscModelNot", "");this.put("cscModel", null);return this;};
 		/** not .... */
 		public M cscModelNot(){this.put("cscModelNot", "not");return this;};
		public M cscModel$on(CsCarModel.M value){
			this.put("CsCarModel", value);
			this.put("cscModel$on", value);
			return this;
		};	
		/** 网点 [非空] [CsOutlets]  {cscHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
		public M cscOutlets(Object cscOutlets){this.put("cscOutlets", cscOutlets);return this;};
	 	/** and csc_outlets is null */
 		public M cscOutletsNull(){if(this.get("cscOutletsNot")==null)this.put("cscOutletsNot", "");this.put("cscOutlets", null);return this;};
 		/** not .... */
 		public M cscOutletsNot(){this.put("cscOutletsNot", "not");return this;};
		public M cscOutlets$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("cscOutlets$on", value);
			return this;
		};	
		/** 供应商 [非空] [CsProvid]      **/
		public M cscProvid(Object cscProvid){this.put("cscProvid", cscProvid);return this;};
	 	/** and csc_provid is null */
 		public M cscProvidNull(){if(this.get("cscProvidNot")==null)this.put("cscProvidNot", "");this.put("cscProvid", null);return this;};
 		/** not .... */
 		public M cscProvidNot(){this.put("cscProvidNot", "not");return this;};
		public M cscProvid$on(CsProvid.M value){
			this.put("CsProvid", value);
			this.put("cscProvid$on", value);
			return this;
		};	
		/** 折扣 [非空]    0到1,比如0.75表示七五折   **/
		public M cscRebate(Object cscRebate){this.put("cscRebate", cscRebate);return this;};
	 	/** and csc_rebate is null */
 		public M cscRebateNull(){if(this.get("cscRebateNot")==null)this.put("cscRebateNot", "");this.put("cscRebate", null);return this;};
 		/** not .... */
 		public M cscRebateNot(){this.put("cscRebateNot", "not");return this;};
		/** and csc_rebate >= ? */
		public M cscRebateMin(Object min){this.put("cscRebateMin", min);return this;};
		/** and csc_rebate <= ? */
		public M cscRebateMax(Object max){this.put("cscRebateMax", max);return this;};
		/** 广告车？ [非空]       **/
		public M cscIsAd(Object cscIsAd){this.put("cscIsAd", cscIsAd);return this;};
	 	/** and csc_is_ad is null */
 		public M cscIsAdNull(){if(this.get("cscIsAdNot")==null)this.put("cscIsAdNot", "");this.put("cscIsAd", null);return this;};
 		/** not .... */
 		public M cscIsAdNot(){this.put("cscIsAdNot", "not");return this;};
		/** 经度        **/
		public M cscLongitude(Object cscLongitude){this.put("cscLongitude", cscLongitude);return this;};
	 	/** and csc_longitude is null */
 		public M cscLongitudeNull(){if(this.get("cscLongitudeNot")==null)this.put("cscLongitudeNot", "");this.put("cscLongitude", null);return this;};
 		/** not .... */
 		public M cscLongitudeNot(){this.put("cscLongitudeNot", "not");return this;};
		/** and csc_longitude >= ? */
		public M cscLongitudeMin(Object min){this.put("cscLongitudeMin", min);return this;};
		/** and csc_longitude <= ? */
		public M cscLongitudeMax(Object max){this.put("cscLongitudeMax", max);return this;};
		/** 纬度        **/
		public M cscLatitude(Object cscLatitude){this.put("cscLatitude", cscLatitude);return this;};
	 	/** and csc_latitude is null */
 		public M cscLatitudeNull(){if(this.get("cscLatitudeNot")==null)this.put("cscLatitudeNot", "");this.put("cscLatitude", null);return this;};
 		/** not .... */
 		public M cscLatitudeNot(){this.put("cscLatitudeNot", "not");return this;};
		/** and csc_latitude >= ? */
		public M cscLatitudeMin(Object min){this.put("cscLatitudeMin", min);return this;};
		/** and csc_latitude <= ? */
		public M cscLatitudeMax(Object max){this.put("cscLatitudeMax", max);return this;};
		/** 手机号码        **/
		public M cscMobile(Object cscMobile){this.put("cscMobile", cscMobile);return this;};
	 	/** and csc_mobile is null */
 		public M cscMobileNull(){if(this.get("cscMobileNot")==null)this.put("cscMobileNot", "");this.put("cscMobile", null);return this;};
 		/** not .... */
 		public M cscMobileNot(){this.put("cscMobileNot", "not");return this;};
		/** 服务密码        **/
		public M cscPassword(Object cscPassword){this.put("cscPassword", cscPassword);return this;};
	 	/** and csc_password is null */
 		public M cscPasswordNull(){if(this.get("cscPasswordNot")==null)this.put("cscPasswordNot", "");this.put("cscPassword", null);return this;};
 		/** not .... */
 		public M cscPasswordNot(){this.put("cscPasswordNot", "not");return this;};
		/** 加油卡号        **/
		public M cscOilCard(Object cscOilCard){this.put("cscOilCard", cscOilCard);return this;};
	 	/** and csc_oil_card is null */
 		public M cscOilCardNull(){if(this.get("cscOilCardNot")==null)this.put("cscOilCardNot", "");this.put("cscOilCard", null);return this;};
 		/** not .... */
 		public M cscOilCardNot(){this.put("cscOilCardNot", "not");return this;};
		/** 车架号        **/
		public M cscVin(Object cscVin){this.put("cscVin", cscVin);return this;};
	 	/** and csc_vin is null */
 		public M cscVinNull(){if(this.get("cscVinNot")==null)this.put("cscVinNot", "");this.put("cscVin", null);return this;};
 		/** not .... */
 		public M cscVinNot(){this.put("cscVinNot", "not");return this;};
		/** 发动机号        **/
		public M cscEngineNo(Object cscEngineNo){this.put("cscEngineNo", cscEngineNo);return this;};
	 	/** and csc_engine_no is null */
 		public M cscEngineNoNull(){if(this.get("cscEngineNoNot")==null)this.put("cscEngineNoNot", "");this.put("cscEngineNo", null);return this;};
 		/** not .... */
 		public M cscEngineNoNot(){this.put("cscEngineNoNot", "not");return this;};
		/** 购车日        **/
		public M cscBuyDate(Object cscBuyDate){this.put("cscBuyDate", cscBuyDate);return this;};
	 	/** and csc_buy_date is null */
 		public M cscBuyDateNull(){if(this.get("cscBuyDateNot")==null)this.put("cscBuyDateNot", "");this.put("cscBuyDate", null);return this;};
 		/** not .... */
 		public M cscBuyDateNot(){this.put("cscBuyDateNot", "not");return this;};
 		/** and csc_buy_date >= ? */
 		public M cscBuyDateStart(Object start){this.put("cscBuyDateStart", start);return this;};			
 		/** and csc_buy_date <= ? */
 		public M cscBuyDateEnd(Object end){this.put("cscBuyDateEnd", end);return this;};
		/** 合同号        **/
		public M cscBargainNo(Object cscBargainNo){this.put("cscBargainNo", cscBargainNo);return this;};
	 	/** and csc_bargain_no is null */
 		public M cscBargainNoNull(){if(this.get("cscBargainNoNot")==null)this.put("cscBargainNoNot", "");this.put("cscBargainNo", null);return this;};
 		/** not .... */
 		public M cscBargainNoNot(){this.put("cscBargainNoNot", "not");return this;};
		/** 购置税        **/
		public M cscTaxPrice(Object cscTaxPrice){this.put("cscTaxPrice", cscTaxPrice);return this;};
	 	/** and csc_tax_price is null */
 		public M cscTaxPriceNull(){if(this.get("cscTaxPriceNot")==null)this.put("cscTaxPriceNot", "");this.put("cscTaxPrice", null);return this;};
 		/** not .... */
 		public M cscTaxPriceNot(){this.put("cscTaxPriceNot", "not");return this;};
		/** and csc_tax_price >= ? */
		public M cscTaxPriceMin(Object min){this.put("cscTaxPriceMin", min);return this;};
		/** and csc_tax_price <= ? */
		public M cscTaxPriceMax(Object max){this.put("cscTaxPriceMax", max);return this;};
		/** 车价        **/
		public M cscBuyPrice(Object cscBuyPrice){this.put("cscBuyPrice", cscBuyPrice);return this;};
	 	/** and csc_buy_price is null */
 		public M cscBuyPriceNull(){if(this.get("cscBuyPriceNot")==null)this.put("cscBuyPriceNot", "");this.put("cscBuyPrice", null);return this;};
 		/** not .... */
 		public M cscBuyPriceNot(){this.put("cscBuyPriceNot", "not");return this;};
		/** and csc_buy_price >= ? */
		public M cscBuyPriceMin(Object min){this.put("cscBuyPriceMin", min);return this;};
		/** and csc_buy_price <= ? */
		public M cscBuyPriceMax(Object max){this.put("cscBuyPriceMax", max);return this;};
		/** 合格证号        **/
		public M cscCertific(Object cscCertific){this.put("cscCertific", cscCertific);return this;};
	 	/** and csc_certific is null */
 		public M cscCertificNull(){if(this.get("cscCertificNot")==null)this.put("cscCertificNot", "");this.put("cscCertific", null);return this;};
 		/** not .... */
 		public M cscCertificNot(){this.put("cscCertificNot", "not");return this;};
		/** 厂牌型号        **/
		public M cscFactoryNo(Object cscFactoryNo){this.put("cscFactoryNo", cscFactoryNo);return this;};
	 	/** and csc_factory_no is null */
 		public M cscFactoryNoNull(){if(this.get("cscFactoryNoNot")==null)this.put("cscFactoryNoNot", "");this.put("cscFactoryNo", null);return this;};
 		/** not .... */
 		public M cscFactoryNoNot(){this.put("cscFactoryNoNot", "not");return this;};
		/** 颜色    0:黑色 1:白色 2:红色 3:绿色 4:黄色 5:橙色 6:银色 7:蓝色 8:深蓝 9:深灰 10:深红     **/
		public M cscColor(Object cscColor){this.put("cscColor", cscColor);return this;};
	 	/** and csc_color is null */
 		public M cscColorNull(){if(this.get("cscColorNot")==null)this.put("cscColorNot", "");this.put("cscColor", null);return this;};
 		/** not .... */
 		public M cscColorNot(){this.put("cscColorNot", "not");return this;};
		/** 车辆主图     图片建议尺寸500*500像素（比例）   **/
		public M cscPreview(Object cscPreview){this.put("cscPreview", cscPreview);return this;};
	 	/** and csc_preview is null */
 		public M cscPreviewNull(){if(this.get("cscPreviewNot")==null)this.put("cscPreviewNot", "");this.put("cscPreview", null);return this;};
 		/** not .... */
 		public M cscPreviewNot(){this.put("cscPreviewNot", "not");return this;};
		/** 照片列表        **/
		public M cscImages(Object cscImages){this.put("cscImages", cscImages);return this;};
	 	/** and csc_images is null */
 		public M cscImagesNull(){if(this.get("cscImagesNot")==null)this.put("cscImagesNot", "");this.put("cscImages", null);return this;};
 		/** not .... */
 		public M cscImagesNot(){this.put("cscImagesNot", "not");return this;};
		/** 保养公里        **/
		public M cscMaintKm(Object cscMaintKm){this.put("cscMaintKm", cscMaintKm);return this;};
	 	/** and csc_maint_km is null */
 		public M cscMaintKmNull(){if(this.get("cscMaintKmNot")==null)this.put("cscMaintKmNot", "");this.put("cscMaintKm", null);return this;};
 		/** not .... */
 		public M cscMaintKmNot(){this.put("cscMaintKmNot", "not");return this;};
		/** and csc_maint_km >= ? */
		public M cscMaintKmMin(Object min){this.put("cscMaintKmMin", min);return this;};
		/** and csc_maint_km <= ? */
		public M cscMaintKmMax(Object max){this.put("cscMaintKmMax", max);return this;};
		/** 初次登记        **/
		public M cscCheckIn(Object cscCheckIn){this.put("cscCheckIn", cscCheckIn);return this;};
	 	/** and csc_check_in is null */
 		public M cscCheckInNull(){if(this.get("cscCheckInNot")==null)this.put("cscCheckInNot", "");this.put("cscCheckIn", null);return this;};
 		/** not .... */
 		public M cscCheckInNot(){this.put("cscCheckInNot", "not");return this;};
 		/** and csc_check_in >= ? */
 		public M cscCheckInStart(Object start){this.put("cscCheckInStart", start);return this;};			
 		/** and csc_check_in <= ? */
 		public M cscCheckInEnd(Object end){this.put("cscCheckInEnd", end);return this;};
		/** 上次年检日期        **/
		public M cscAnnualPre(Object cscAnnualPre){this.put("cscAnnualPre", cscAnnualPre);return this;};
	 	/** and csc_annual_pre is null */
 		public M cscAnnualPreNull(){if(this.get("cscAnnualPreNot")==null)this.put("cscAnnualPreNot", "");this.put("cscAnnualPre", null);return this;};
 		/** not .... */
 		public M cscAnnualPreNot(){this.put("cscAnnualPreNot", "not");return this;};
 		/** and csc_annual_pre >= ? */
 		public M cscAnnualPreStart(Object start){this.put("cscAnnualPreStart", start);return this;};			
 		/** and csc_annual_pre <= ? */
 		public M cscAnnualPreEnd(Object end){this.put("cscAnnualPreEnd", end);return this;};
		/** 实际年检日期        **/
		public M cscAnnual(Object cscAnnual){this.put("cscAnnual", cscAnnual);return this;};
	 	/** and csc_annual is null */
 		public M cscAnnualNull(){if(this.get("cscAnnualNot")==null)this.put("cscAnnualNot", "");this.put("cscAnnual", null);return this;};
 		/** not .... */
 		public M cscAnnualNot(){this.put("cscAnnualNot", "not");return this;};
 		/** and csc_annual >= ? */
 		public M cscAnnualStart(Object start){this.put("cscAnnualStart", start);return this;};			
 		/** and csc_annual <= ? */
 		public M cscAnnualEnd(Object end){this.put("cscAnnualEnd", end);return this;};
		/** 具体年检信息        **/
		public M cscAnnualInfo(Object cscAnnualInfo){this.put("cscAnnualInfo", cscAnnualInfo);return this;};
	 	/** and csc_annual_info is null */
 		public M cscAnnualInfoNull(){if(this.get("cscAnnualInfoNot")==null)this.put("cscAnnualInfoNot", "");this.put("cscAnnualInfo", null);return this;};
 		/** not .... */
 		public M cscAnnualInfoNot(){this.put("cscAnnualInfoNot", "not");return this;};
		/** 预计下次年检        **/
		public M cscAnnualNext(Object cscAnnualNext){this.put("cscAnnualNext", cscAnnualNext);return this;};
	 	/** and csc_annual_next is null */
 		public M cscAnnualNextNull(){if(this.get("cscAnnualNextNot")==null)this.put("cscAnnualNextNot", "");this.put("cscAnnualNext", null);return this;};
 		/** not .... */
 		public M cscAnnualNextNot(){this.put("cscAnnualNextNot", "not");return this;};
 		/** and csc_annual_next >= ? */
 		public M cscAnnualNextStart(Object start){this.put("cscAnnualNextStart", start);return this;};			
 		/** and csc_annual_next <= ? */
 		public M cscAnnualNextEnd(Object end){this.put("cscAnnualNextEnd", end);return this;};
		/** 交强险投保        **/
		public M cscTiDate(Object cscTiDate){this.put("cscTiDate", cscTiDate);return this;};
	 	/** and csc_ti_date is null */
 		public M cscTiDateNull(){if(this.get("cscTiDateNot")==null)this.put("cscTiDateNot", "");this.put("cscTiDate", null);return this;};
 		/** not .... */
 		public M cscTiDateNot(){this.put("cscTiDateNot", "not");return this;};
 		/** and csc_ti_date >= ? */
 		public M cscTiDateStart(Object start){this.put("cscTiDateStart", start);return this;};			
 		/** and csc_ti_date <= ? */
 		public M cscTiDateEnd(Object end){this.put("cscTiDateEnd", end);return this;};
		/** 交强险到期        **/
		public M cscTiUnit(Object cscTiUnit){this.put("cscTiUnit", cscTiUnit);return this;};
	 	/** and csc_ti_unit is null */
 		public M cscTiUnitNull(){if(this.get("cscTiUnitNot")==null)this.put("cscTiUnitNot", "");this.put("cscTiUnit", null);return this;};
 		/** not .... */
 		public M cscTiUnitNot(){this.put("cscTiUnitNot", "not");return this;};
 		/** and csc_ti_unit >= ? */
 		public M cscTiUnitStart(Object start){this.put("cscTiUnitStart", start);return this;};			
 		/** and csc_ti_unit <= ? */
 		public M cscTiUnitEnd(Object end){this.put("cscTiUnitEnd", end);return this;};
		/** 交强险保单        **/
		public M cscTiNo(Object cscTiNo){this.put("cscTiNo", cscTiNo);return this;};
	 	/** and csc_ti_no is null */
 		public M cscTiNoNull(){if(this.get("cscTiNoNot")==null)this.put("cscTiNoNot", "");this.put("cscTiNo", null);return this;};
 		/** not .... */
 		public M cscTiNoNot(){this.put("cscTiNoNot", "not");return this;};
		/** 商业险投保        **/
		public M cscBiDate(Object cscBiDate){this.put("cscBiDate", cscBiDate);return this;};
	 	/** and csc_bi_date is null */
 		public M cscBiDateNull(){if(this.get("cscBiDateNot")==null)this.put("cscBiDateNot", "");this.put("cscBiDate", null);return this;};
 		/** not .... */
 		public M cscBiDateNot(){this.put("cscBiDateNot", "not");return this;};
 		/** and csc_bi_date >= ? */
 		public M cscBiDateStart(Object start){this.put("cscBiDateStart", start);return this;};			
 		/** and csc_bi_date <= ? */
 		public M cscBiDateEnd(Object end){this.put("cscBiDateEnd", end);return this;};
		/** 商业险到期        **/
		public M cscBiUnit(Object cscBiUnit){this.put("cscBiUnit", cscBiUnit);return this;};
	 	/** and csc_bi_unit is null */
 		public M cscBiUnitNull(){if(this.get("cscBiUnitNot")==null)this.put("cscBiUnitNot", "");this.put("cscBiUnit", null);return this;};
 		/** not .... */
 		public M cscBiUnitNot(){this.put("cscBiUnitNot", "not");return this;};
 		/** and csc_bi_unit >= ? */
 		public M cscBiUnitStart(Object start){this.put("cscBiUnitStart", start);return this;};			
 		/** and csc_bi_unit <= ? */
 		public M cscBiUnitEnd(Object end){this.put("cscBiUnitEnd", end);return this;};
		/** 商业险保单        **/
		public M cscBiNo(Object cscBiNo){this.put("cscBiNo", cscBiNo);return this;};
	 	/** and csc_bi_no is null */
 		public M cscBiNoNull(){if(this.get("cscBiNoNot")==null)this.put("cscBiNoNot", "");this.put("cscBiNo", null);return this;};
 		/** not .... */
 		public M cscBiNoNot(){this.put("cscBiNoNot", "not");return this;};
		/** 商业险类型        **/
		public M cscBiType(Object cscBiType){this.put("cscBiType", cscBiType);return this;};
	 	/** and csc_bi_type is null */
 		public M cscBiTypeNull(){if(this.get("cscBiTypeNot")==null)this.put("cscBiTypeNot", "");this.put("cscBiType", null);return this;};
 		/** not .... */
 		public M cscBiTypeNot(){this.put("cscBiTypeNot", "not");return this;};
		/** 商业险金额        **/
		public M cscBiLimit(Object cscBiLimit){this.put("cscBiLimit", cscBiLimit);return this;};
	 	/** and csc_bi_limit is null */
 		public M cscBiLimitNull(){if(this.get("cscBiLimitNot")==null)this.put("cscBiLimitNot", "");this.put("cscBiLimit", null);return this;};
 		/** not .... */
 		public M cscBiLimitNot(){this.put("cscBiLimitNot", "not");return this;};
		/** and csc_bi_limit >= ? */
		public M cscBiLimitMin(Object min){this.put("cscBiLimitMin", min);return this;};
		/** and csc_bi_limit <= ? */
		public M cscBiLimitMax(Object max){this.put("cscBiLimitMax", max);return this;};
		/** 保险公司        **/
		public M cscBiCompany(Object cscBiCompany){this.put("cscBiCompany", cscBiCompany);return this;};
	 	/** and csc_bi_company is null */
 		public M cscBiCompanyNull(){if(this.get("cscBiCompanyNot")==null)this.put("cscBiCompanyNot", "");this.put("cscBiCompany", null);return this;};
 		/** not .... */
 		public M cscBiCompanyNot(){this.put("cscBiCompanyNot", "not");return this;};
		/** 修改人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cscEditor(Object cscEditor){this.put("cscEditor", cscEditor);return this;};
	 	/** and csc_editor is null */
 		public M cscEditorNull(){if(this.get("cscEditorNot")==null)this.put("cscEditorNot", "");this.put("cscEditor", null);return this;};
 		/** not .... */
 		public M cscEditorNot(){this.put("cscEditorNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M cscUpdateTime(Object cscUpdateTime){this.put("cscUpdateTime", cscUpdateTime);return this;};
	 	/** and csc_update_time is null */
 		public M cscUpdateTimeNull(){if(this.get("cscUpdateTimeNot")==null)this.put("cscUpdateTimeNot", "");this.put("cscUpdateTime", null);return this;};
 		/** not .... */
 		public M cscUpdateTimeNot(){this.put("cscUpdateTimeNot", "not");return this;};
 		/** and csc_update_time >= ? */
 		public M cscUpdateTimeStart(Object start){this.put("cscUpdateTimeStart", start);return this;};			
 		/** and csc_update_time <= ? */
 		public M cscUpdateTimeEnd(Object end){this.put("cscUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cscAddTime(Object cscAddTime){this.put("cscAddTime", cscAddTime);return this;};
	 	/** and csc_add_time is null */
 		public M cscAddTimeNull(){if(this.get("cscAddTimeNot")==null)this.put("cscAddTimeNot", "");this.put("cscAddTime", null);return this;};
 		/** not .... */
 		public M cscAddTimeNot(){this.put("cscAddTimeNot", "not");return this;};
 		/** and csc_add_time >= ? */
 		public M cscAddTimeStart(Object start){this.put("cscAddTimeStart", start);return this;};			
 		/** and csc_add_time <= ? */
 		public M cscAddTimeEnd(Object end){this.put("cscAddTimeEnd", end);return this;};
		/** 备注        **/
		public M cscRemark(Object cscRemark){this.put("cscRemark", cscRemark);return this;};
	 	/** and csc_remark is null */
 		public M cscRemarkNull(){if(this.get("cscRemarkNot")==null)this.put("cscRemarkNot", "");this.put("cscRemark", null);return this;};
 		/** not .... */
 		public M cscRemarkNot(){this.put("cscRemarkNot", "not");return this;};
		/** 备忘信息    textarea:content text:sign     **/
		public M cscMemo(Object cscMemo){this.put("cscMemo", cscMemo);return this;};
	 	/** and csc_memo is null */
 		public M cscMemoNull(){if(this.get("cscMemoNot")==null)this.put("cscMemoNot", "");this.put("cscMemo", null);return this;};
 		/** not .... */
 		public M cscMemoNot(){this.put("cscMemoNot", "not");return this;};
		/** 上线密码        **/
		public M cscOnPsd(Object cscOnPsd){this.put("cscOnPsd", cscOnPsd);return this;};
	 	/** and csc_on_psd is null */
 		public M cscOnPsdNull(){if(this.get("cscOnPsdNot")==null)this.put("cscOnPsdNot", "");this.put("cscOnPsd", null);return this;};
 		/** not .... */
 		public M cscOnPsdNot(){this.put("cscOnPsdNot", "not");return this;};
		/** 车机安装    0:未安装 1:正在安装 2:已安装     **/
		public M cscSetup(Object cscSetup){this.put("cscSetup", cscSetup);return this;};
	 	/** and csc_setup is null */
 		public M cscSetupNull(){if(this.get("cscSetupNot")==null)this.put("cscSetupNot", "");this.put("cscSetup", null);return this;};
 		/** not .... */
 		public M cscSetupNot(){this.put("cscSetupNot", "not");return this;};
		/** 下位机版本    0:1.0版 1:2.0版     **/
		public M cscDeviceVesion(Object cscDeviceVesion){this.put("cscDeviceVesion", cscDeviceVesion);return this;};
	 	/** and csc_device_vesion is null */
 		public M cscDeviceVesionNull(){if(this.get("cscDeviceVesionNot")==null)this.put("cscDeviceVesionNot", "");this.put("cscDeviceVesion", null);return this;};
 		/** not .... */
 		public M cscDeviceVesionNot(){this.put("cscDeviceVesionNot", "not");return this;};
		/** 服务器标识        **/
		public M cscMqttFlag(Object cscMqttFlag){this.put("cscMqttFlag", cscMqttFlag);return this;};
	 	/** and csc_mqtt_flag is null */
 		public M cscMqttFlagNull(){if(this.get("cscMqttFlagNot")==null)this.put("cscMqttFlagNot", "");this.put("cscMqttFlag", null);return this;};
 		/** not .... */
 		public M cscMqttFlagNot(){this.put("cscMqttFlagNot", "not");return this;};
		/** 程序掩码    1:假定条件     **/
		public M cscMask(Object cscMask){this.put("cscMask", cscMask);return this;};
	 	/** and csc_mask is null */
 		public M cscMaskNull(){if(this.get("cscMaskNot")==null)this.put("cscMaskNot", "");this.put("cscMask", null);return this;};
 		/** not .... */
 		public M cscMaskNot(){this.put("cscMaskNot", "not");return this;};
		/** 状态 [非空]   1:上线 2:下线 3:企业可租 0:无效     **/
		public M cscStatus(Object cscStatus){this.put("cscStatus", cscStatus);return this;};
	 	/** and csc_status is null */
 		public M cscStatusNull(){if(this.get("cscStatusNot")==null)this.put("cscStatusNot", "");this.put("cscStatus", null);return this;};
 		/** not .... */
 		public M cscStatusNot(){this.put("cscStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	
	 	// -----------------------------------------------------------
	 	/**   终端号      **/
        public M cscTerNo(Object cscTerNo){this.put("cscTerNo", cscTerNo);return this;};
        /** and csc_ter_no is null */
        public M cscTerNoNull(){if(this.get("cscTerNoNot")==null)this.put("cscTerNoNot", "");this.put("cscTerNo", null);return this;};
        /** not .... */
        public M cscTerNoNot(){this.put("cscTerNoNot", "not");return this;};
        
        // -----------------------------------------------------------
        /**   绑定平台      **/
        public M cscBindPlatform(Object cscBindPlatform){this.put("cscBindPlatform", cscBindPlatform);return this;};
        /** and csc_bind_platform is null */
        public M cscBindPlatformNull(){if(this.get("cscBindPlatformNot")==null)this.put("cscBindPlatformNot", "");this.put("cscBindPlatform", null);return this;};
        /** not .... */
        public M cscBindPlatformNot(){this.put("cscBindPlatformNot", "not");return this;};
        
	 	/** 获取所有车辆 **/
		public @api List<CsCar> list(Integer size){
			return getCsCarList(this,size);
		}
		/** 获取车辆分页 **/
		public @api Page<CsCar> page(int page,int size){
			return getCsCarPage(page, size , this);
		}
		/** 根据查询条件取车辆 **/
		public @api CsCar get(){
			return getCsCar(this);
		}
		/** 获取车辆数 **/
		public @api Long count(){
			return getCsCarCount(this);
		}
		/** 获取车辆表达式 **/
		public @api <T> T eval(String strEval){
			return getCsCarEval(strEval,this);
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
			updateCsCar(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscId="cscId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cscHost="cscHost";
		/** 车牌号 [非空]       **/
		public final static @type(String.class) @like String cscNumber="cscNumber";
		/** 车牌名称 [非空]       **/
		public final static @type(String.class) @like String cscCarNo="cscCarNo";
		/** 车型 [非空] [CsCarModel]      **/
		public final static @type(Long.class)  String cscModel="cscModel";
		/** 网点 [非空] [CsOutlets]  {cscHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
		public final static @type(Long.class)  String cscOutlets="cscOutlets";
		/** 供应商 [非空] [CsProvid]      **/
		public final static @type(Long.class)  String cscProvid="cscProvid";
		/** 折扣 [非空]    0到1,比如0.75表示七五折   **/
		public final static @type(Double.class)  String cscRebate="cscRebate";
		/** and csc_rebate >= ? */
		public final static @type(Double.class) String cscRebateMin="cscRebateMin";
		/** and csc_rebate <= ? */
		public final static @type(Double.class) String cscRebateMax="cscRebateMax";
		/** 广告车？ [非空]       **/
		public final static @type(Boolean.class)  String cscIsAd="cscIsAd";
		/** 经度        **/
		public final static @type(Double.class)  String cscLongitude="cscLongitude";
		/** and csc_longitude >= ? */
		public final static @type(Double.class) String cscLongitudeMin="cscLongitudeMin";
		/** and csc_longitude <= ? */
		public final static @type(Double.class) String cscLongitudeMax="cscLongitudeMax";
		/** 纬度        **/
		public final static @type(Double.class)  String cscLatitude="cscLatitude";
		/** and csc_latitude >= ? */
		public final static @type(Double.class) String cscLatitudeMin="cscLatitudeMin";
		/** and csc_latitude <= ? */
		public final static @type(Double.class) String cscLatitudeMax="cscLatitudeMax";
		/** 手机号码        **/
		public final static @type(String.class) @like String cscMobile="cscMobile";
		/** 服务密码        **/
		public final static @type(String.class) @like String cscPassword="cscPassword";
		/** 加油卡号        **/
		public final static @type(String.class) @like String cscOilCard="cscOilCard";
		/** 车架号        **/
		public final static @type(String.class) @like String cscVin="cscVin";
		/** 发动机号        **/
		public final static @type(String.class) @like String cscEngineNo="cscEngineNo";
		/** 购车日        **/
		public final static @type(Date.class)  String cscBuyDate="cscBuyDate";
	 	/** and csc_buy_date >= ? */
 		public final static @type(Date.class) String cscBuyDateStart="cscBuyDateStart";
 		/** and csc_buy_date <= ? */
 		public final static @type(Date.class) String cscBuyDateEnd="cscBuyDateEnd";
		/** 合同号        **/
		public final static @type(String.class) @like String cscBargainNo="cscBargainNo";
		/** 购置税        **/
		public final static @type(Double.class)  String cscTaxPrice="cscTaxPrice";
		/** and csc_tax_price >= ? */
		public final static @type(Double.class) String cscTaxPriceMin="cscTaxPriceMin";
		/** and csc_tax_price <= ? */
		public final static @type(Double.class) String cscTaxPriceMax="cscTaxPriceMax";
		/** 车价        **/
		public final static @type(Double.class)  String cscBuyPrice="cscBuyPrice";
		/** and csc_buy_price >= ? */
		public final static @type(Double.class) String cscBuyPriceMin="cscBuyPriceMin";
		/** and csc_buy_price <= ? */
		public final static @type(Double.class) String cscBuyPriceMax="cscBuyPriceMax";
		/** 合格证号        **/
		public final static @type(String.class) @like String cscCertific="cscCertific";
		/** 厂牌型号        **/
		public final static @type(String.class) @like String cscFactoryNo="cscFactoryNo";
		/** 颜色    0:黑色 1:白色 2:红色 3:绿色 4:黄色 5:橙色 6:银色 7:蓝色 8:深蓝 9:深灰 10:深红     **/
		public final static @type(Short.class)  String cscColor="cscColor";
		/** 车辆主图     图片建议尺寸500*500像素（比例）   **/
		public final static @type(String.class)  String cscPreview="cscPreview";
		/** 照片列表        **/
		public final static @type(String.class)  String cscImages="cscImages";
		/** 保养公里        **/
		public final static @type(Integer.class)  String cscMaintKm="cscMaintKm";
		/** and csc_maint_km >= ? */
		public final static @type(Integer.class) String cscMaintKmMin="cscMaintKmMin";
		/** and csc_maint_km <= ? */
		public final static @type(Integer.class) String cscMaintKmMax="cscMaintKmMax";
		/** 初次登记        **/
		public final static @type(Date.class)  String cscCheckIn="cscCheckIn";
	 	/** and csc_check_in >= ? */
 		public final static @type(Date.class) String cscCheckInStart="cscCheckInStart";
 		/** and csc_check_in <= ? */
 		public final static @type(Date.class) String cscCheckInEnd="cscCheckInEnd";
		/** 上次年检日期        **/
		public final static @type(Date.class)  String cscAnnualPre="cscAnnualPre";
	 	/** and csc_annual_pre >= ? */
 		public final static @type(Date.class) String cscAnnualPreStart="cscAnnualPreStart";
 		/** and csc_annual_pre <= ? */
 		public final static @type(Date.class) String cscAnnualPreEnd="cscAnnualPreEnd";
		/** 实际年检日期        **/
		public final static @type(Date.class)  String cscAnnual="cscAnnual";
	 	/** and csc_annual >= ? */
 		public final static @type(Date.class) String cscAnnualStart="cscAnnualStart";
 		/** and csc_annual <= ? */
 		public final static @type(Date.class) String cscAnnualEnd="cscAnnualEnd";
		/** 具体年检信息        **/
		public final static @type(String.class) @like String cscAnnualInfo="cscAnnualInfo";
		/** 预计下次年检        **/
		public final static @type(Date.class)  String cscAnnualNext="cscAnnualNext";
	 	/** and csc_annual_next >= ? */
 		public final static @type(Date.class) String cscAnnualNextStart="cscAnnualNextStart";
 		/** and csc_annual_next <= ? */
 		public final static @type(Date.class) String cscAnnualNextEnd="cscAnnualNextEnd";
		/** 交强险投保        **/
		public final static @type(Date.class)  String cscTiDate="cscTiDate";
	 	/** and csc_ti_date >= ? */
 		public final static @type(Date.class) String cscTiDateStart="cscTiDateStart";
 		/** and csc_ti_date <= ? */
 		public final static @type(Date.class) String cscTiDateEnd="cscTiDateEnd";
		/** 交强险到期        **/
		public final static @type(Date.class)  String cscTiUnit="cscTiUnit";
	 	/** and csc_ti_unit >= ? */
 		public final static @type(Date.class) String cscTiUnitStart="cscTiUnitStart";
 		/** and csc_ti_unit <= ? */
 		public final static @type(Date.class) String cscTiUnitEnd="cscTiUnitEnd";
		/** 交强险保单        **/
		public final static @type(String.class) @like String cscTiNo="cscTiNo";
		/** 商业险投保        **/
		public final static @type(Date.class)  String cscBiDate="cscBiDate";
	 	/** and csc_bi_date >= ? */
 		public final static @type(Date.class) String cscBiDateStart="cscBiDateStart";
 		/** and csc_bi_date <= ? */
 		public final static @type(Date.class) String cscBiDateEnd="cscBiDateEnd";
		/** 商业险到期        **/
		public final static @type(Date.class)  String cscBiUnit="cscBiUnit";
	 	/** and csc_bi_unit >= ? */
 		public final static @type(Date.class) String cscBiUnitStart="cscBiUnitStart";
 		/** and csc_bi_unit <= ? */
 		public final static @type(Date.class) String cscBiUnitEnd="cscBiUnitEnd";
		/** 商业险保单        **/
		public final static @type(String.class) @like String cscBiNo="cscBiNo";
		/** 商业险类型        **/
		public final static @type(String.class) @like String cscBiType="cscBiType";
		/** 商业险金额        **/
		public final static @type(Double.class)  String cscBiLimit="cscBiLimit";
		/** and csc_bi_limit >= ? */
		public final static @type(Double.class) String cscBiLimitMin="cscBiLimitMin";
		/** and csc_bi_limit <= ? */
		public final static @type(Double.class) String cscBiLimitMax="cscBiLimitMax";
		/** 保险公司        **/
		public final static @type(String.class) @like String cscBiCompany="cscBiCompany";
		/** 修改人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String cscEditor="cscEditor";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cscUpdateTime="cscUpdateTime";
	 	/** and csc_update_time >= ? */
 		public final static @type(Date.class) String cscUpdateTimeStart="cscUpdateTimeStart";
 		/** and csc_update_time <= ? */
 		public final static @type(Date.class) String cscUpdateTimeEnd="cscUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cscAddTime="cscAddTime";
	 	/** and csc_add_time >= ? */
 		public final static @type(Date.class) String cscAddTimeStart="cscAddTimeStart";
 		/** and csc_add_time <= ? */
 		public final static @type(Date.class) String cscAddTimeEnd="cscAddTimeEnd";
		/** 备注        **/
		public final static @type(String.class) @like String cscRemark="cscRemark";
		/** 备忘信息    textarea:content text:sign     **/
		public final static @type(String.class)  String cscMemo="cscMemo";
		/** 上线密码        **/
		public final static @type(String.class) @like String cscOnPsd="cscOnPsd";
		/** 车机安装    0:未安装 1:正在安装 2:已安装     **/
		public final static @type(Short.class)  String cscSetup="cscSetup";
		/** 下位机版本    0:1.0版 1:2.0版     **/
		public final static @type(Short.class)  String cscDeviceVesion="cscDeviceVesion";
		/** 服务器标识        **/
		public final static @type(String.class) @like String cscMqttFlag="cscMqttFlag";
		/** 程序掩码    1:假定条件     **/
		public final static @type(Long.class)  String cscMask="cscMask";
		/** 状态 [非空]   1:上线 2:下线 3:企业可租 0:无效     **/
		public final static @type(Short.class)  String cscStatus="cscStatus";
		
		public final static @type(Short.class) String cscTerNo="cscTerNo";
		public final static @type(Short.class) String cscBindPlatform="cscBindPlatform";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscId="csc_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cscHost="csc_host";
		/** 车牌号 [非空]       **/
		public final static String cscNumber="csc_number";
		/** 车牌名称 [非空]       **/
		public final static String cscCarNo="csc_car_no";
		/** 车型 [非空] [CsCarModel]      **/
		public final static String cscModel="csc_model";
		/** 网点 [非空] [CsOutlets]  {cscHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
		public final static String cscOutlets="csc_outlets";
		/** 供应商 [非空] [CsProvid]      **/
		public final static String cscProvid="csc_provid";
		/** 折扣 [非空]    0到1,比如0.75表示七五折   **/
		public final static String cscRebate="csc_rebate";
		/** 广告车？ [非空]       **/
		public final static String cscIsAd="csc_is_ad";
		/** 经度        **/
		public final static String cscLongitude="csc_longitude";
		/** 纬度        **/
		public final static String cscLatitude="csc_latitude";
		/** 手机号码        **/
		public final static String cscMobile="csc_mobile";
		/** 服务密码        **/
		public final static String cscPassword="csc_password";
		/** 加油卡号        **/
		public final static String cscOilCard="csc_oil_card";
		/** 车架号        **/
		public final static String cscVin="csc_vin";
		/** 发动机号        **/
		public final static String cscEngineNo="csc_engine_no";
		/** 购车日        **/
		public final static String cscBuyDate="csc_buy_date";
		/** 合同号        **/
		public final static String cscBargainNo="csc_bargain_no";
		/** 购置税        **/
		public final static String cscTaxPrice="csc_tax_price";
		/** 车价        **/
		public final static String cscBuyPrice="csc_buy_price";
		/** 合格证号        **/
		public final static String cscCertific="csc_certific";
		/** 厂牌型号        **/
		public final static String cscFactoryNo="csc_factory_no";
		/** 颜色    0:黑色 1:白色 2:红色 3:绿色 4:黄色 5:橙色 6:银色 7:蓝色 8:深蓝 9:深灰 10:深红     **/
		public final static String cscColor="csc_color";
		/** 车辆主图     图片建议尺寸500*500像素（比例）   **/
		public final static String cscPreview="csc_preview";
		/** 照片列表        **/
		public final static String cscImages="csc_images";
		/** 保养公里        **/
		public final static String cscMaintKm="csc_maint_km";
		/** 初次登记        **/
		public final static String cscCheckIn="csc_check_in";
		/** 上次年检日期        **/
		public final static String cscAnnualPre="csc_annual_pre";
		/** 实际年检日期        **/
		public final static String cscAnnual="csc_annual";
		/** 具体年检信息        **/
		public final static String cscAnnualInfo="csc_annual_info";
		/** 预计下次年检        **/
		public final static String cscAnnualNext="csc_annual_next";
		/** 交强险投保        **/
		public final static String cscTiDate="csc_ti_date";
		/** 交强险到期        **/
		public final static String cscTiUnit="csc_ti_unit";
		/** 交强险保单        **/
		public final static String cscTiNo="csc_ti_no";
		/** 商业险投保        **/
		public final static String cscBiDate="csc_bi_date";
		/** 商业险到期        **/
		public final static String cscBiUnit="csc_bi_unit";
		/** 商业险保单        **/
		public final static String cscBiNo="csc_bi_no";
		/** 商业险类型        **/
		public final static String cscBiType="csc_bi_type";
		/** 商业险金额        **/
		public final static String cscBiLimit="csc_bi_limit";
		/** 保险公司        **/
		public final static String cscBiCompany="csc_bi_company";
		/** 修改人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cscEditor="csc_editor";
		/** 修改时间 [非空]       **/
		public final static String cscUpdateTime="csc_update_time";
		/** 添加时间 [非空]       **/
		public final static String cscAddTime="csc_add_time";
		/** 备注        **/
		public final static String cscRemark="csc_remark";
		/** 备忘信息    textarea:content text:sign     **/
		public final static String cscMemo="csc_memo";
		/** 上线密码        **/
		public final static String cscOnPsd="csc_on_psd";
		/** 车机安装    0:未安装 1:正在安装 2:已安装     **/
		public final static String cscSetup="csc_setup";
		/** 下位机版本    0:1.0版 1:2.0版     **/
		public final static String cscDeviceVesion="csc_device_vesion";
		/** 服务器标识        **/
		public final static String cscMqttFlag="csc_mqtt_flag";
		/** 程序掩码    1:假定条件     **/
		public final static String cscMask="csc_mask";
		/** 状态 [非空]   1:上线 2:下线 3:企业可租 0:无效     **/
		public final static String cscStatus="csc_status";
		
        public final static String cscTerNo="csc_ter_no";
        public final static String cscBindPlatform="csc_bind_platform";
        
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
				$.Set(name,CsCar.getCsCar(params));
			else
				$.Set(name,CsCar.getCsCarList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取车辆数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsCar) $.GetRequest("CsCar$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsCar.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsCar.getCsCar(params);
			else
				value = CsCar.getCsCarList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsCar.Get($.add(CsCar.F.cscId,param));
		else if(!$.empty(param.toString()))
			value = CsCar.get(Long.valueOf(param.toString()));
		$.SetRequest("CsCar$"+param.hashCode(), value);
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
	public void mergeSet(CsCar old){
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