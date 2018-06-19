package com.ccclubs.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.lazy3q.web.helper.$;

import com.lazy3q.web.tags.BodyTag;

import com.lazy3q.web.util.Page;
import com.lazy3q.lang.*;
import com.lazy3q.util.Function;
import com.ccclubs.dao.impl.MemCache;

@namespace("unit/specialcar")
public @caption("专车订单") @table("cs_special_car") class CsSpecialCar implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cssc_id")   @primary   @note("  ") Long csscId;
	private @caption("运营地区") @column("cssc_host")    @relate("$csscHost") @RelateClass(SrvHost.class)  @note("  ") Long csscHost;
	private SrvHost $csscHost;//关联对象[运营地区]
	private @caption("企业信息") @column("cssc_info")    @relate("$csscInfo") @RelateClass(CsUnitInfo.class)  @note("  ") Long csscInfo;
	private CsUnitInfo $csscInfo;//关联对象[企业信息]
	private @caption("用车人员") @column("cssc_person")    @relate("$csscPerson") @RelateClass(CsUnitPerson.class)  @note("  ") Long csscPerson;
	private CsUnitPerson $csscPerson;//关联对象[用车人员]
	private @caption("企业订单") @column("cssc_unit_order")    @relate("$csscUnitOrder") @RelateClass(CsUnitOrder.class)  @note("  ") Long csscUnitOrder;
	private CsUnitOrder $csscUnitOrder;//关联对象[企业订单]
	private @caption("乘客姓名") @column("cssc_name")      @note("  ") String csscName;
	private @caption("乘客电话") @column("cssc_mobile")      @note("  ") String csscMobile;
	private @caption("乘车人数") @column("cssc_count")      @note("  ") Integer csscCount;
	private @caption("预定车型") @column("cssc_car_model")    @relate("$csscCarModel") @RelateClass(CsSpecialModel.class)  @note("  ") Long csscCarModel;
	private CsSpecialModel $csscCarModel;//关联对象[预定车型]
	private @caption("车牌号码") @column("cssc_car_number")      @note("  ") String csscCarNumber;
	private @caption("司机姓名") @column("cssc_driver_name")      @note("  ") String csscDriverName;
	private @caption("司机电话") @column("cssc_driver_phone")      @note("  ") String csscDriverPhone;
	private @caption("司机照片") @column("cssc_driver_image")      @note("  ") String csscDriverImage;
	private @caption("车辆颜色") @column("cssc_driver_color")      @note("  ") String csscDriverColor;
	private @caption("车型信息") @column("cssc_driver_car_info")      @note("  ") String csscDriverCarInfo;
	private @caption("客户留言") @column("cssc_msg")      @note("  ") String csscMsg;
	private @caption("起点位置") @column("cssc_start_point")      @note("  ") String csscStartPoint;
	private @caption("起点地址") @column("cssc_start_addr")      @note("  ") String csscStartAddr;
	private @caption("开始时间") @column("cssc_start_time")      @note("  ") Date csscStartTime;
	private @caption("终点位置") @column("cssc_finish_point")      @note("  ") String csscFinishPoint;
	private @caption("终点地址") @column("cssc_finish_addr")      @note("  ") String csscFinishAddr;
	private @caption("结束时间") @column("cssc_finish_time")      @note("  ") Date csscFinishTime;
	private @caption("订单时长") @column("cssc_duration")      @note("  ") Long csscDuration;
	private @caption("实际时长") @column("cssc_real_dur")      @note("  ") Long csscRealDur;
	private @caption("公里数") @column("cssc_kilo")      @note("  ") Integer csscKilo;
	private @caption("用车评分") @column("cssc_scope")      @note(" 0:未评分 1:一星 2:二星 3:三星 4:四星 5:五星  ") Short csscScope;
	private @caption("用车评价") @column("cssc_assess")      @note("  ") String csscAssess;
	private @caption("三方名称") @column("cssc_third")      @note(" 1:滴滴专车 2:易道专车  ") Long csscThird;
	private @caption("三方订单号") @column("cssc_third_no")      @note("  ") String csscThirdNo;
	private @caption("三方费用") @column("cssc_third_fee")      @note("  ") Double csscThirdFee;
	private @caption("三方费用信息") @column("cssc_third_fee_data")     @hidden @note("  ") String csscThirdFeeData;
	private @caption("三方其它费用") @column("cssc_third_other_fee")      @note("  ") Double csscThirdOtherFee;
	private @caption("三方其它费用描述") @column("cssc_third_other_fee_des")      @note("  ") String csscThirdOtherFeeDes;
	private @caption("修改时间") @column("cssc_update_time")      @note("  ") Date csscUpdateTime;
	private @caption("添加时间") @column("cssc_add_time")      @note("  ") Date csscAddTime;
	private @caption("响应状态") @column("cssc_response")      @note("  ") String csscResponse;
	private @caption("状态") @column("cssc_status")      @note(" 0:待审核 1:已审核 2:已下单 3:已接单 4:已完成 5:已取消 6:已作废 7:待处理 8:司机到达 9:行程中  ") Short csscStatus;   //2待接单，3待接驾/已接单，（8司机到达，9行程中） ，4已完成，5已取消，6已作废，7待处理
	private @caption("预估里程(单位:公里)") @column("cssc_predit_mileage")      @note("") Double csscPreditMileage;
	private @caption("预估费用") @column("cssc_estimate_fee")      @note("") Double csscEstimateFee;
	private @caption("车车需求") @column("cssc_demand")      @note("") String csscDemand;
	private @caption("是否拼车") @column("cssc_is_carpool")      @note("0:不拼车 1:拼车") Short csscIsCarpool;
	private @caption("司机到达时间") @column("cssc_driver_arrivaltime")      @note("") Date csscDriverArrivaltime;
	private @caption("专车司机") @column("cssc_drivercar")    @relate("$csscDrivercar") @RelateClass(CsDriverCar.class)  @note("") Long csscDrivercar;
	private CsDriverCar $csscDrivercar;//关联对象[专车司机]
	
	//默认构造函数
	public CsSpecialCar(){
	
	}
	
	//主键构造函数
	public CsSpecialCar(Long id){
		this.csscId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsSpecialCar(Long csscHost,Long csscInfo,Long csscPerson,Long csscUnitOrder,String csscName,String csscMobile,Integer csscCount,Long csscCarModel,String csscCarNumber,String csscDriverName,String csscDriverPhone,String csscDriverImage,String csscDriverColor,String csscDriverCarInfo,String csscMsg,String csscStartPoint,String csscStartAddr,Date csscStartTime,String csscFinishPoint,String csscFinishAddr,Date csscFinishTime,Long csscDuration,Long csscRealDur,Integer csscKilo,Short csscScope,String csscAssess,Long csscThird,String csscThirdNo,Double csscThirdFee,String csscThirdFeeData,Double csscThirdOtherFee,String csscThirdOtherFeeDes,Date csscUpdateTime,Date csscAddTime,String csscResponse,Short csscStatus,Short csscIsCarpool,Long csscDrivercar){
		this.csscHost=csscHost;
		this.csscInfo=csscInfo;
		this.csscPerson=csscPerson;
		this.csscUnitOrder=csscUnitOrder;
		this.csscName=csscName;
		this.csscMobile=csscMobile;
		this.csscCount=csscCount;
		this.csscCarModel=csscCarModel;
		this.csscCarNumber=csscCarNumber;
		this.csscDriverName=csscDriverName;
		this.csscDriverPhone=csscDriverPhone;
		this.csscDriverImage=csscDriverImage;
		this.csscDriverColor=csscDriverColor;
		this.csscDriverCarInfo=csscDriverCarInfo;
		this.csscMsg=csscMsg;
		this.csscStartPoint=csscStartPoint;
		this.csscStartAddr=csscStartAddr;
		this.csscStartTime=csscStartTime;
		this.csscFinishPoint=csscFinishPoint;
		this.csscFinishAddr=csscFinishAddr;
		this.csscFinishTime=csscFinishTime;
		this.csscDuration=csscDuration;
		this.csscRealDur=csscRealDur;
		this.csscKilo=csscKilo;
		this.csscScope=csscScope;
		this.csscAssess=csscAssess;
		this.csscThird=csscThird;
		this.csscThirdNo=csscThirdNo;
		this.csscThirdFee=csscThirdFee;
		this.csscThirdFeeData=csscThirdFeeData;
		this.csscThirdOtherFee=csscThirdOtherFee;
		this.csscThirdOtherFeeDes=csscThirdOtherFeeDes;
		this.csscUpdateTime=csscUpdateTime;
		this.csscAddTime=csscAddTime;
		this.csscResponse=csscResponse;
		this.csscStatus=csscStatus;
		this.csscIsCarpool=csscIsCarpool;
		this.csscDrivercar=csscDrivercar;
	}
	
	//设置非空字段
	public CsSpecialCar setNotNull(Long csscHost,Long csscInfo,Long csscPerson,Long csscUnitOrder,Integer csscCount,String csscStartPoint,Date csscStartTime,String csscFinishPoint,Short csscScope,Date csscUpdateTime,Date csscAddTime,Short csscStatus,Short csscIsCarpool,Date csscDriverArrivaltime){
		this.csscHost=csscHost;
		this.csscInfo=csscInfo;
		this.csscPerson=csscPerson;
		this.csscUnitOrder=csscUnitOrder;
		this.csscCount=csscCount;
		this.csscStartPoint=csscStartPoint;
		this.csscStartTime=csscStartTime;
		this.csscFinishPoint=csscFinishPoint;
		this.csscScope=csscScope;
		this.csscUpdateTime=csscUpdateTime;
		this.csscAddTime=csscAddTime;
		this.csscStatus=csscStatus;
		this.csscIsCarpool=csscIsCarpool;
		this.csscDriverArrivaltime=csscDriverArrivaltime;
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsSpecialCar csscId(Long csscId){
		this.csscId = csscId;
		this.setSeted(F.csscId);
		return this;
	}
	/** 运营地区 [非空]       **/
	public CsSpecialCar csscHost(Long csscHost){
		this.csscHost = csscHost;
		this.setSeted(F.csscHost);
		return this;
	}
	/** 企业信息 [非空]       **/
	public CsSpecialCar csscInfo(Long csscInfo){
		this.csscInfo = csscInfo;
		this.setSeted(F.csscInfo);
		return this;
	}
	/** 用车人员 [非空]       **/
	public CsSpecialCar csscPerson(Long csscPerson){
		this.csscPerson = csscPerson;
		this.setSeted(F.csscPerson);
		return this;
	}
	/** 企业订单 [非空]       **/
	public CsSpecialCar csscUnitOrder(Long csscUnitOrder){
		this.csscUnitOrder = csscUnitOrder;
		this.setSeted(F.csscUnitOrder);
		return this;
	}
	/** 乘客姓名 [非空]       **/
	public CsSpecialCar csscName(String csscName){
		this.csscName = csscName;
		this.setSeted(F.csscName);
		return this;
	}
	/** 乘客电话 [非空]       **/
	public CsSpecialCar csscMobile(String csscMobile){
		this.csscMobile = csscMobile;
		this.setSeted(F.csscMobile);
		return this;
	}
	/** 乘车人数 [非空]       **/
	public CsSpecialCar csscCount(Integer csscCount){
		this.csscCount = csscCount;
		this.setSeted(F.csscCount);
		return this;
	}
	/** 预定车型 [非空]       **/
	public CsSpecialCar csscCarModel(Long csscCarModel){
		this.csscCarModel = csscCarModel;
		this.setSeted(F.csscCarModel);
		return this;
	}
	/** 车牌号码 [非空]       **/
	public CsSpecialCar csscCarNumber(String csscCarNumber){
		this.csscCarNumber = csscCarNumber;
		this.setSeted(F.csscCarNumber);
		return this;
	}
	/** 司机姓名 [非空]       **/
	public CsSpecialCar csscDriverName(String csscDriverName){
		this.csscDriverName = csscDriverName;
		this.setSeted(F.csscDriverName);
		return this;
	}
	/** 司机电话 [非空]       **/
	public CsSpecialCar csscDriverPhone(String csscDriverPhone){
		this.csscDriverPhone = csscDriverPhone;
		this.setSeted(F.csscDriverPhone);
		return this;
	}
	/** 司机照片 [非空]       **/
	public CsSpecialCar csscDriverImage(String csscDriverImage){
		this.csscDriverImage = csscDriverImage;
		this.setSeted(F.csscDriverImage);
		return this;
	}
	/** 车辆颜色 [非空]       **/
	public CsSpecialCar csscDriverColor(String csscDriverColor){
		this.csscDriverColor = csscDriverColor;
		this.setSeted(F.csscDriverColor);
		return this;
	}
	/** 车型信息 [非空]       **/
	public CsSpecialCar csscDriverCarInfo(String csscDriverCarInfo){
		this.csscDriverCarInfo = csscDriverCarInfo;
		this.setSeted(F.csscDriverCarInfo);
		return this;
	}
	/** 客户留言 [非空]       **/
	public CsSpecialCar csscMsg(String csscMsg){
		this.csscMsg = csscMsg;
		this.setSeted(F.csscMsg);
		return this;
	}
	/** 起点位置 [非空]       **/
	public CsSpecialCar csscStartPoint(String csscStartPoint){
		this.csscStartPoint = csscStartPoint;
		this.setSeted(F.csscStartPoint);
		return this;
	}
	/** 起点地址 [非空]       **/
	public CsSpecialCar csscStartAddr(String csscStartAddr){
		this.csscStartAddr = csscStartAddr;
		this.setSeted(F.csscStartAddr);
		return this;
	}
	/** 开始时间 [非空]       **/
	public CsSpecialCar csscStartTime(Date csscStartTime){
		this.csscStartTime = csscStartTime;
		this.setSeted(F.csscStartTime);
		return this;
	}
	/** 终点位置 [非空]       **/
	public CsSpecialCar csscFinishPoint(String csscFinishPoint){
		this.csscFinishPoint = csscFinishPoint;
		this.setSeted(F.csscFinishPoint);
		return this;
	}
	/** 终点地址 [非空]       **/
	public CsSpecialCar csscFinishAddr(String csscFinishAddr){
		this.csscFinishAddr = csscFinishAddr;
		this.setSeted(F.csscFinishAddr);
		return this;
	}
	/** 结束时间 [非空]       **/
	public CsSpecialCar csscFinishTime(Date csscFinishTime){
		this.csscFinishTime = csscFinishTime;
		this.setSeted(F.csscFinishTime);
		return this;
	}
	/** 订单时长 [非空]       **/
	public CsSpecialCar csscDuration(Long csscDuration){
		this.csscDuration = csscDuration;
		this.setSeted(F.csscDuration);
		return this;
	}
	/** 实际时长 [非空]       **/
	public CsSpecialCar csscRealDur(Long csscRealDur){
		this.csscRealDur = csscRealDur;
		this.setSeted(F.csscRealDur);
		return this;
	}
	/** 公里数 [非空]       **/
	public CsSpecialCar csscKilo(Integer csscKilo){
		this.csscKilo = csscKilo;
		this.setSeted(F.csscKilo);
		return this;
	}
	/** 用车评分 [非空]       **/
	public CsSpecialCar csscScope(Short csscScope){
		this.csscScope = csscScope;
		this.setSeted(F.csscScope);
		return this;
	}
	/** 用车评价 [非空]       **/
	public CsSpecialCar csscAssess(String csscAssess){
		this.csscAssess = csscAssess;
		this.setSeted(F.csscAssess);
		return this;
	}
	/** 三方名称 [非空]       **/
	public CsSpecialCar csscThird(Long csscThird){
		this.csscThird = csscThird;
		this.setSeted(F.csscThird);
		return this;
	}
	/** 三方订单号 [非空]       **/
	public CsSpecialCar csscThirdNo(String csscThirdNo){
		this.csscThirdNo = csscThirdNo;
		this.setSeted(F.csscThirdNo);
		return this;
	}
	/** 三方费用 [非空]       **/
	public CsSpecialCar csscThirdFee(Double csscThirdFee){
		this.csscThirdFee = csscThirdFee;
		this.setSeted(F.csscThirdFee);
		return this;
	}
	/** 三方费用信息 [非空]       **/
	public CsSpecialCar csscThirdFeeData(String csscThirdFeeData){
		this.csscThirdFeeData = csscThirdFeeData;
		this.setSeted(F.csscThirdFeeData);
		return this;
	}
	/** 三方其它费用 [非空]       **/
	public CsSpecialCar csscThirdOtherFee(Double csscThirdOtherFee){
		this.csscThirdOtherFee = csscThirdOtherFee;
		this.setSeted(F.csscThirdOtherFee);
		return this;
	}
	/** 三方其它费用描述 [非空]       **/
	public CsSpecialCar csscThirdOtherFeeDes(String csscThirdOtherFeeDes){
		this.csscThirdOtherFeeDes = csscThirdOtherFeeDes;
		this.setSeted(F.csscThirdOtherFeeDes);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsSpecialCar csscUpdateTime(Date csscUpdateTime){
		this.csscUpdateTime = csscUpdateTime;
		this.setSeted(F.csscUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsSpecialCar csscAddTime(Date csscAddTime){
		this.csscAddTime = csscAddTime;
		this.setSeted(F.csscAddTime);
		return this;
	}
	/** 响应状态 [非空]       **/
	public CsSpecialCar csscResponse(String csscResponse){
		this.csscResponse = csscResponse;
		this.setSeted(F.csscResponse);
		return this;
	}
	/** 状态 [非空]       **/
	public CsSpecialCar csscStatus(Short csscStatus){
		this.csscStatus = csscStatus;
		this.setSeted(F.csscStatus);
		return this;
	}
	/** 预估里程(单位:公里) [非空]       **/
	public CsSpecialCar csscPreditMileage(Double csscPreditMileage){
		this.csscPreditMileage = csscPreditMileage;
		this.setSeted(F.csscPreditMileage);
		return this;
	}
	/** 预估费用 [非空]       **/
	public CsSpecialCar csscEstimateFee(Double csscEstimateFee){
		this.csscEstimateFee = csscEstimateFee;
		this.setSeted(F.csscEstimateFee);
		return this;
	}
	/** 车车需求 [非空]       **/
	public CsSpecialCar csscDemand(String csscDemand){
		this.csscDemand = csscDemand;
		this.setSeted(F.csscDemand);
		return this;
	}
	/** 是否拼车 [非空]       **/
	public CsSpecialCar csscIsCarpool(Short csscIsCarpool){
		this.csscIsCarpool = csscIsCarpool;
		this.setSeted(F.csscIsCarpool);
		return this;
	}
	/** 司机到达时间 [非空]       **/
	public CsSpecialCar csscDriverArrivaltime(Date csscDriverArrivaltime){
		this.csscDriverArrivaltime = csscDriverArrivaltime;
		this.setSeted(F.csscDriverArrivaltime);
		return this;
	}
	/** 专车司机 [可空]       **/
	public CsSpecialCar csscDrivercar(Long csscDrivercar){
		this.csscDrivercar = csscDrivercar;
		this.setSeted(F.csscDrivercar);
		return this;
	}
	
	//克隆对象
	public CsSpecialCar clone(){
		CsSpecialCar clone = new CsSpecialCar();
		clone.csscId=this.csscId;
		clone.csscHost=this.csscHost;
		clone.csscInfo=this.csscInfo;
		clone.csscPerson=this.csscPerson;
		clone.csscUnitOrder=this.csscUnitOrder;
		clone.csscName=this.csscName;
		clone.csscMobile=this.csscMobile;
		clone.csscCount=this.csscCount;
		clone.csscCarModel=this.csscCarModel;
		clone.csscCarNumber=this.csscCarNumber;
		clone.csscDriverName=this.csscDriverName;
		clone.csscDriverPhone=this.csscDriverPhone;
		clone.csscDriverImage=this.csscDriverImage;
		clone.csscDriverColor=this.csscDriverColor;
		clone.csscDriverCarInfo=this.csscDriverCarInfo;
		clone.csscMsg=this.csscMsg;
		clone.csscStartPoint=this.csscStartPoint;
		clone.csscStartAddr=this.csscStartAddr;
		clone.csscStartTime=this.csscStartTime;
		clone.csscFinishPoint=this.csscFinishPoint;
		clone.csscFinishAddr=this.csscFinishAddr;
		clone.csscFinishTime=this.csscFinishTime;
		clone.csscDuration=this.csscDuration;
		clone.csscRealDur=this.csscRealDur;
		clone.csscKilo=this.csscKilo;
		clone.csscScope=this.csscScope;
		clone.csscAssess=this.csscAssess;
		clone.csscThird=this.csscThird;
		clone.csscThirdNo=this.csscThirdNo;
		clone.csscThirdFee=this.csscThirdFee;
		clone.csscThirdFeeData=this.csscThirdFeeData;
		clone.csscThirdOtherFee=this.csscThirdOtherFee;
		clone.csscThirdOtherFeeDes=this.csscThirdOtherFeeDes;
		clone.csscUpdateTime=this.csscUpdateTime;
		clone.csscAddTime=this.csscAddTime;
		clone.csscResponse=this.csscResponse;
		clone.csscStatus=this.csscStatus;
		clone.csscPreditMileage=this.csscPreditMileage;
		clone.csscEstimateFee=this.csscEstimateFee;
		clone.csscDemand=this.csscDemand;
		clone.csscIsCarpool=this.csscIsCarpool;
		clone.csscDriverArrivaltime=this.csscDriverArrivaltime;
		clone.csscDrivercar=this.csscDrivercar;
		return clone;
	}
	
	
	/**
	 * 根据ID取专车订单
	 * @param id
	 * @return
	 */
	public static @api CsSpecialCar get(Long id){		
		return getCsSpecialCarById(id);
	}
	/**
	 * 获取所有专车订单
	 * @return
	 */
	public static @api List<CsSpecialCar> list(Map params,Integer size){
		return getCsSpecialCarList(params,size);
	}
	/**
	 * 获取专车订单分页
	 * @return
	 */
	public static @api Page<CsSpecialCar> page(int page,int size,Map params){
		return getCsSpecialCarPage(page, size , params);
	}
	/**
	 * 根据查询条件取专车订单
	 * @param params
	 * @return
	 */
	public static @api CsSpecialCar Get(Map params){
		return getCsSpecialCar(params);
	}
	/**
	 * 获取专车订单数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsSpecialCarCount(params);
	}
	/**
	 * 获取专车订单数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsSpecialCarEval(eval,params);
	}
	
	/**
	 * 根据ID取专车订单
	 * @param id
	 * @return
	 */
	public static @api CsSpecialCar getCsSpecialCarById(Long id){		
		CsSpecialCar csSpecialCar = (CsSpecialCar) $.GetRequest("CsSpecialCar$"+id);
		if(csSpecialCar!=null)
			return csSpecialCar;
		com.ccclubs.service.admin.ICsSpecialCarService csSpecialCarService = $.GetSpringBean("csSpecialCarService");		
		return csSpecialCarService.getCsSpecialCarById(id);
	}
	
	
	/**
	 * 根据ID取专车订单的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsSpecialCar.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsSpecialCar csSpecialCar = get(id);
		if(csSpecialCar!=null){
			String strValue = csSpecialCar.getCsscStatus$();
			if(!"CsscStatus".equals("CsscStatus"))
				strValue+="("+csSpecialCar.getCsscStatus$()+")";
			MemCache.setValue(CsSpecialCar.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsscId$();
		if(!"CsscId".equals("CsscId"))
			keyValue+="("+this.getCsscId$()+")";
		if(!"CsscId".equals("CsscStatus"))
			keyValue+="("+this.getCsscStatus$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有专车订单
	 * @return
	 */
	public static @api List<CsSpecialCar> getCsSpecialCarList(Map params,Integer size){
		com.ccclubs.service.admin.ICsSpecialCarService csSpecialCarService = $.GetSpringBean("csSpecialCarService");
		return csSpecialCarService.getCsSpecialCarList(params, size);
	}
	
	/**
	 * 获取专车订单分页
	 * @return
	 */
	public static @api Page<CsSpecialCar> getCsSpecialCarPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsSpecialCarService csSpecialCarService = $.GetSpringBean("csSpecialCarService");
		return csSpecialCarService.getCsSpecialCarPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取专车订单
	 * @param params
	 * @return
	 */
	public static @api CsSpecialCar getCsSpecialCar(Map params){
		com.ccclubs.service.admin.ICsSpecialCarService csSpecialCarService = $.GetSpringBean("csSpecialCarService");
		return csSpecialCarService.getCsSpecialCar(params);
	}
	
	/**
	 * 获取专车订单数
	 * @return
	 */
	public static @api Long getCsSpecialCarCount(Map params){
		com.ccclubs.service.admin.ICsSpecialCarService csSpecialCarService = $.GetSpringBean("csSpecialCarService");
		return csSpecialCarService.getCsSpecialCarCount(params);
	}
		
		
	/**
	 * 获取专车订单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsSpecialCarEval(String eval,Map params){
		com.ccclubs.service.admin.ICsSpecialCarService csSpecialCarService = $.GetSpringBean("csSpecialCarService");
		return csSpecialCarService.getCsSpecialCarEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsSpecialCar(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsSpecialCarService csSpecialCarService = $.GetSpringBean("csSpecialCarService");
		csSpecialCarService.updateCsSpecialCarByConfirm(set, where);
	}
	
	
	/**
	 * 保存专车订单对象
	 * @param params
	 * @return
	 */
	public CsSpecialCar save(){
		com.ccclubs.service.admin.ICsSpecialCarService csSpecialCarService = $.GetSpringBean("csSpecialCarService");
		if(this.getCsscId()!=null)
			csSpecialCarService.updateCsSpecialCar(this);
		else
			return csSpecialCarService.saveCsSpecialCar(this);
		return this;
	}
	
	
	/**
	 * 更新专车订单对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsSpecialCarService csSpecialCarService = $.GetSpringBean("csSpecialCarService");
		csSpecialCarService.updateCsSpecialCar$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsSpecialCarService csSpecialCarService = $.GetSpringBean("csSpecialCarService");
		if($.equals($.config("logic_delete"),"true"))
			csSpecialCarService.removeCsSpecialCarById(this.getCsscId());
		else
			csSpecialCarService.deleteCsSpecialCarById(this.getCsscId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsSpecialCarService csSpecialCarService = $.GetSpringBean("csSpecialCarService");
		return csSpecialCarService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsscId(){
		return this.csscId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsscId$(){
		String strValue="";
		 strValue=$.str(this.getCsscId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCsscId(Long csscId){
		this.csscId = csscId;
		this.setSeted(F.csscId);
	}
	/*******************************运营地区**********************************/	
	/**
	* 运营地区 [非空]      
	**/
	public Long getCsscHost(){
		return this.csscHost;
	}
	/**
	* 获取运营地区格式化(toString)
	**/
	public String getCsscHost$(){
		String strValue="";
		if(this.getCsscHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsscHost()));
		}			
	 	 return strValue;
	}
	/**
	* 运营地区 [非空]     
	**/
	public void setCsscHost(Long csscHost){
		this.csscHost = csscHost;
		this.setSeted(F.csscHost);
	}
	/**
	* 获取关联对象[运营地区]
	**/	 			
 	public SrvHost get$csscHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsscHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsscHost()!=null){
			srvHost = SrvHost.get(this.getCsscHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsscHost(), srvHost);
	 	return srvHost;
	}
	/*******************************企业信息**********************************/	
	/**
	* 企业信息 [非空]      
	**/
	public Long getCsscInfo(){
		return this.csscInfo;
	}
	/**
	* 获取企业信息格式化(toString)
	**/
	public String getCsscInfo$(){
		String strValue="";
		if(this.getCsscInfo()!=null){
				strValue+=$.str(CsUnitInfo.getKeyValue(this.getCsscInfo()));
		}			
	 	 return strValue;
	}
	/**
	* 企业信息 [非空]     
	**/
	public void setCsscInfo(Long csscInfo){
		this.csscInfo = csscInfo;
		this.setSeted(F.csscInfo);
	}
	/**
	* 获取关联对象[企业信息]
	**/	 			
 	public CsUnitInfo get$csscInfo(){
 		com.ccclubs.model.CsUnitInfo csUnitInfo = (com.ccclubs.model.CsUnitInfo) $.GetRequest("CsUnitInfo$"+this.getCsscInfo());
 		if(csUnitInfo!=null)
			return csUnitInfo;
		if(this.getCsscInfo()!=null){
			csUnitInfo = CsUnitInfo.get(this.getCsscInfo());
 		}
 		$.SetRequest("CsUnitInfo$"+this.getCsscInfo(), csUnitInfo);
	 	return csUnitInfo;
	}
	/*******************************用车人员**********************************/	
	/**
	* 用车人员 [非空]      
	**/
	public Long getCsscPerson(){
		return this.csscPerson;
	}
	/**
	* 获取用车人员格式化(toString)
	**/
	public String getCsscPerson$(){
		String strValue="";
		if(this.getCsscPerson()!=null){
				strValue+=$.str(CsUnitPerson.getKeyValue(this.getCsscPerson()));
		}			
	 	 return strValue;
	}
	/**
	* 用车人员 [非空]     
	**/
	public void setCsscPerson(Long csscPerson){
		this.csscPerson = csscPerson;
		this.setSeted(F.csscPerson);
	}
	/**
	* 获取关联对象[用车人员]
	**/	 			
 	public CsUnitPerson get$csscPerson(){
 		com.ccclubs.model.CsUnitPerson csUnitPerson = (com.ccclubs.model.CsUnitPerson) $.GetRequest("CsUnitPerson$"+this.getCsscPerson());
 		if(csUnitPerson!=null)
			return csUnitPerson;
		if(this.getCsscPerson()!=null){
			csUnitPerson = CsUnitPerson.get(this.getCsscPerson());
 		}
 		$.SetRequest("CsUnitPerson$"+this.getCsscPerson(), csUnitPerson);
	 	return csUnitPerson;
	}
	/*******************************企业订单**********************************/	
	/**
	* 企业订单 [非空]      
	**/
	public Long getCsscUnitOrder(){
		return this.csscUnitOrder;
	}
	/**
	* 获取企业订单格式化(toString)
	**/
	public String getCsscUnitOrder$(){
		String strValue="";
		if(this.getCsscUnitOrder()!=null){
				strValue+=$.str(CsUnitOrder.getKeyValue(this.getCsscUnitOrder()));
		}			
	 	 return strValue;
	}
	/**
	* 企业订单 [非空]     
	**/
	public void setCsscUnitOrder(Long csscUnitOrder){
		this.csscUnitOrder = csscUnitOrder;
		this.setSeted(F.csscUnitOrder);
	}
	/**
	* 获取关联对象[企业订单]
	**/	 			
 	public CsUnitOrder get$csscUnitOrder(){
 		com.ccclubs.model.CsUnitOrder csUnitOrder = (com.ccclubs.model.CsUnitOrder) $.GetRequest("CsUnitOrder$"+this.getCsscUnitOrder());
 		if(csUnitOrder!=null)
			return csUnitOrder;
		if(this.getCsscUnitOrder()!=null){
			csUnitOrder = CsUnitOrder.get(this.getCsscUnitOrder());
 		}
 		$.SetRequest("CsUnitOrder$"+this.getCsscUnitOrder(), csUnitOrder);
	 	return csUnitOrder;
	}
	/*******************************乘客姓名**********************************/	
	/**
	* 乘客姓名 [非空]      
	**/
	public String getCsscName(){
		return this.csscName;
	}
	/**
	* 获取乘客姓名格式化(toString)
	**/
	public String getCsscName$(){
		String strValue="";
		 strValue=$.str(this.getCsscName());
	 	 return strValue;
	}
	/**
	* 乘客姓名 [非空]     
	**/
	public void setCsscName(String csscName){
		this.csscName = csscName;
		this.setSeted(F.csscName);
	}
	/*******************************乘客电话**********************************/	
	/**
	* 乘客电话 [非空]      
	**/
	public String getCsscMobile(){
		return this.csscMobile;
	}
	/**
	* 获取乘客电话格式化(toString)
	**/
	public String getCsscMobile$(){
		String strValue="";
		 strValue=$.str(this.getCsscMobile());
	 	 return strValue;
	}
	/**
	* 乘客电话 [非空]     
	**/
	public void setCsscMobile(String csscMobile){
		this.csscMobile = csscMobile;
		this.setSeted(F.csscMobile);
	}
	/*******************************乘车人数**********************************/	
	/**
	* 乘车人数 [非空]      
	**/
	public Integer getCsscCount(){
		return this.csscCount;
	}
	/**
	* 获取乘车人数格式化(toString)
	**/
	public String getCsscCount$(){
		String strValue="";
		 strValue=$.str(this.getCsscCount());
	 	 return strValue;
	}
	/**
	* 乘车人数 [非空]     
	**/
	public void setCsscCount(Integer csscCount){
		this.csscCount = csscCount;
		this.setSeted(F.csscCount);
	}
	/*******************************预定车型**********************************/	
	/**
	* 预定车型 [非空]      
	**/
	public Long getCsscCarModel(){
		return this.csscCarModel;
	}
	/**
	* 获取预定车型格式化(toString)
	**/
	public String getCsscCarModel$(){
		String strValue="";
		if(this.getCsscCarModel()!=null){
				strValue+=$.str(CsSpecialModel.getKeyValue(this.getCsscCarModel()));
		}			
	 	 return strValue;
	}
	/**
	* 预定车型 [非空]     
	**/
	public void setCsscCarModel(Long csscCarModel){
		this.csscCarModel = csscCarModel;
		this.setSeted(F.csscCarModel);
	}
	/**
	* 获取关联对象[预定车型]
	**/	 			
 	public CsSpecialModel get$csscCarModel(){
 		com.ccclubs.model.CsSpecialModel csSpecialModel = (com.ccclubs.model.CsSpecialModel) $.GetRequest("CsSpecialModel$"+this.getCsscCarModel());
 		if(csSpecialModel!=null)
			return csSpecialModel;
		if(this.getCsscCarModel()!=null){
			csSpecialModel = CsSpecialModel.get(this.getCsscCarModel());
 		}
 		$.SetRequest("CsSpecialModel$"+this.getCsscCarModel(), csSpecialModel);
	 	return csSpecialModel;
	}
	/*******************************车牌号码**********************************/	
	/**
	* 车牌号码 [非空]      
	**/
	public String getCsscCarNumber(){
		return this.csscCarNumber;
	}
	/**
	* 获取车牌号码格式化(toString)
	**/
	public String getCsscCarNumber$(){
		String strValue="";
		 strValue=$.str(this.getCsscCarNumber());
	 	 return strValue;
	}
	/**
	* 车牌号码 [非空]     
	**/
	public void setCsscCarNumber(String csscCarNumber){
		this.csscCarNumber = csscCarNumber;
		this.setSeted(F.csscCarNumber);
	}
	/*******************************司机姓名**********************************/	
	/**
	* 司机姓名 [非空]      
	**/
	public String getCsscDriverName(){
		return this.csscDriverName;
	}
	/**
	* 获取司机姓名格式化(toString)
	**/
	public String getCsscDriverName$(){
		String strValue="";
		 strValue=$.str(this.getCsscDriverName());
	 	 return strValue;
	}
	/**
	* 司机姓名 [非空]     
	**/
	public void setCsscDriverName(String csscDriverName){
		this.csscDriverName = csscDriverName;
		this.setSeted(F.csscDriverName);
	}
	/*******************************司机电话**********************************/	
	/**
	* 司机电话 [非空]      
	**/
	public String getCsscDriverPhone(){
		return this.csscDriverPhone;
	}
	/**
	* 获取司机电话格式化(toString)
	**/
	public String getCsscDriverPhone$(){
		String strValue="";
		 strValue=$.str(this.getCsscDriverPhone());
	 	 return strValue;
	}
	/**
	* 司机电话 [非空]     
	**/
	public void setCsscDriverPhone(String csscDriverPhone){
		this.csscDriverPhone = csscDriverPhone;
		this.setSeted(F.csscDriverPhone);
	}
	/*******************************司机照片**********************************/	
	/**
	* 司机照片 [非空]      
	**/
	public String getCsscDriverImage(){
		return this.csscDriverImage;
	}
	/**
	* 获取司机照片格式化(toString)
	**/
	public String getCsscDriverImage$(){
		String strValue="";
		 strValue=$.str(this.getCsscDriverImage());
	 	 return strValue;
	}
	/**
	* 司机照片 [非空]     
	**/
	public void setCsscDriverImage(String csscDriverImage){
		this.csscDriverImage = csscDriverImage;
		this.setSeted(F.csscDriverImage);
	}
	/*******************************车辆颜色**********************************/	
	/**
	* 车辆颜色 [非空]      
	**/
	public String getCsscDriverColor(){
		return this.csscDriverColor;
	}
	/**
	* 获取车辆颜色格式化(toString)
	**/
	public String getCsscDriverColor$(){
		String strValue="";
		 strValue=$.str(this.getCsscDriverColor());
	 	 return strValue;
	}
	/**
	* 车辆颜色 [非空]     
	**/
	public void setCsscDriverColor(String csscDriverColor){
		this.csscDriverColor = csscDriverColor;
		this.setSeted(F.csscDriverColor);
	}
	/*******************************车型信息**********************************/	
	/**
	* 车型信息 [非空]      
	**/
	public String getCsscDriverCarInfo(){
		return this.csscDriverCarInfo;
	}
	/**
	* 获取车型信息格式化(toString)
	**/
	public String getCsscDriverCarInfo$(){
		String strValue="";
		 strValue=$.str(this.getCsscDriverCarInfo());
	 	 return strValue;
	}
	/**
	* 车型信息 [非空]     
	**/
	public void setCsscDriverCarInfo(String csscDriverCarInfo){
		this.csscDriverCarInfo = csscDriverCarInfo;
		this.setSeted(F.csscDriverCarInfo);
	}
	/*******************************客户留言**********************************/	
	/**
	* 客户留言 [非空]      
	**/
	public String getCsscMsg(){
		return this.csscMsg;
	}
	/**
	* 获取客户留言格式化(toString)
	**/
	public String getCsscMsg$(){
		String strValue="";
		 strValue=$.str(this.getCsscMsg());
	 	 return strValue;
	}
	/**
	* 客户留言 [非空]     
	**/
	public void setCsscMsg(String csscMsg){
		this.csscMsg = csscMsg;
		this.setSeted(F.csscMsg);
	}
	/*******************************起点位置**********************************/	
	/**
	* 起点位置 [非空]      
	**/
	public String getCsscStartPoint(){
		return this.csscStartPoint;
	}
	/**
	* 获取起点位置格式化(toString)
	**/
	public String getCsscStartPoint$(){
		String strValue="";
		 strValue=$.str(this.getCsscStartPoint());
	 	 return strValue;
	}
	/**
	* 起点位置 [非空]     
	**/
	public void setCsscStartPoint(String csscStartPoint){
		this.csscStartPoint = csscStartPoint;
		this.setSeted(F.csscStartPoint);
	}
	/*******************************起点地址**********************************/	
	/**
	* 起点地址 [非空]      
	**/
	public String getCsscStartAddr(){
		return this.csscStartAddr;
	}
	/**
	* 获取起点地址格式化(toString)
	**/
	public String getCsscStartAddr$(){
		String strValue="";
		 strValue=$.str(this.getCsscStartAddr());
	 	 return strValue;
	}
	/**
	* 起点地址 [非空]     
	**/
	public void setCsscStartAddr(String csscStartAddr){
		this.csscStartAddr = csscStartAddr;
		this.setSeted(F.csscStartAddr);
	}
	/*******************************开始时间**********************************/	
	/**
	* 开始时间 [非空]      
	**/
	public Date getCsscStartTime(){
		return this.csscStartTime;
	}
	/**
	* 获取开始时间格式化(toString)
	**/
	public String getCsscStartTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsscStartTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 开始时间 [非空]     
	**/
	public void setCsscStartTime(Date csscStartTime){
		this.csscStartTime = csscStartTime;
		this.setSeted(F.csscStartTime);
	}
	/*******************************终点位置**********************************/	
	/**
	* 终点位置 [非空]      
	**/
	public String getCsscFinishPoint(){
		return this.csscFinishPoint;
	}
	/**
	* 获取终点位置格式化(toString)
	**/
	public String getCsscFinishPoint$(){
		String strValue="";
		 strValue=$.str(this.getCsscFinishPoint());
	 	 return strValue;
	}
	/**
	* 终点位置 [非空]     
	**/
	public void setCsscFinishPoint(String csscFinishPoint){
		this.csscFinishPoint = csscFinishPoint;
		this.setSeted(F.csscFinishPoint);
	}
	/*******************************终点地址**********************************/	
	/**
	* 终点地址 [非空]      
	**/
	public String getCsscFinishAddr(){
		return this.csscFinishAddr;
	}
	/**
	* 获取终点地址格式化(toString)
	**/
	public String getCsscFinishAddr$(){
		String strValue="";
		 strValue=$.str(this.getCsscFinishAddr());
	 	 return strValue;
	}
	/**
	* 终点地址 [非空]     
	**/
	public void setCsscFinishAddr(String csscFinishAddr){
		this.csscFinishAddr = csscFinishAddr;
		this.setSeted(F.csscFinishAddr);
	}
	/*******************************结束时间**********************************/	
	/**
	* 结束时间 [非空]      
	**/
	public Date getCsscFinishTime(){
		return this.csscFinishTime;
	}
	/**
	* 获取结束时间格式化(toString)
	**/
	public String getCsscFinishTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsscFinishTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 结束时间 [非空]     
	**/
	public void setCsscFinishTime(Date csscFinishTime){
		this.csscFinishTime = csscFinishTime;
		this.setSeted(F.csscFinishTime);
	}
	/*******************************订单时长**********************************/	
	/**
	* 订单时长 [非空]      
	**/
	public Long getCsscDuration(){
		return this.csscDuration;
	}
	/**
	* 获取订单时长格式化(toString)
	**/
	public String getCsscDuration$(){
		String strValue="";
		 strValue=$.str(this.getCsscDuration());
	 	 return strValue;
	}
	/**
	* 订单时长 [非空]     
	**/
	public void setCsscDuration(Long csscDuration){
		this.csscDuration = csscDuration;
		this.setSeted(F.csscDuration);
	}
	/*******************************实际时长**********************************/	
	/**
	* 实际时长 [非空]      
	**/
	public Long getCsscRealDur(){
		return this.csscRealDur;
	}
	/**
	* 获取实际时长格式化(toString)
	**/
	public String getCsscRealDur$(){
		String strValue="";
		 strValue=$.str(this.getCsscRealDur());
	 	 return strValue;
	}
	/**
	* 实际时长 [非空]     
	**/
	public void setCsscRealDur(Long csscRealDur){
		this.csscRealDur = csscRealDur;
		this.setSeted(F.csscRealDur);
	}
	/*******************************公里数**********************************/	
	/**
	* 公里数 [非空]      
	**/
	public Integer getCsscKilo(){
		return this.csscKilo;
	}
	/**
	* 获取公里数格式化(toString)
	**/
	public String getCsscKilo$(){
		String strValue="";
		 strValue=$.str(this.getCsscKilo());
	 	 return strValue;
	}
	/**
	* 公里数 [非空]     
	**/
	public void setCsscKilo(Integer csscKilo){
		this.csscKilo = csscKilo;
		this.setSeted(F.csscKilo);
	}
	/*******************************用车评分**********************************/	
	/**
	* 用车评分 [非空]      
	**/
	public Short getCsscScope(){
		return this.csscScope;
	}
	/**
	* 获取用车评分格式化(toString)
	**/
	public String getCsscScope$(){
		String strValue="";
		 if($.equals($.str(this.getCsscScope()),"3"))
			 strValue=$.str("三星");
		 if($.equals($.str(this.getCsscScope()),"2"))
			 strValue=$.str("二星");
		 if($.equals($.str(this.getCsscScope()),"1"))
			 strValue=$.str("一星");
		 if($.equals($.str(this.getCsscScope()),"0"))
			 strValue=$.str("未评分");
		 if($.equals($.str(this.getCsscScope()),"5"))
			 strValue=$.str("五星");
		 if($.equals($.str(this.getCsscScope()),"4"))
			 strValue=$.str("四星");
	 	 return strValue;
	}
	/**
	* 用车评分 [非空]     
	**/
	public void setCsscScope(Short csscScope){
		this.csscScope = csscScope;
		this.setSeted(F.csscScope);
	}
	/*******************************用车评价**********************************/	
	/**
	* 用车评价 [非空]      
	**/
	public String getCsscAssess(){
		return this.csscAssess;
	}
	/**
	* 获取用车评价格式化(toString)
	**/
	public String getCsscAssess$(){
		String strValue="";
		 strValue=$.str(this.getCsscAssess());
	 	 return strValue;
	}
	/**
	* 用车评价 [非空]     
	**/
	public void setCsscAssess(String csscAssess){
		this.csscAssess = csscAssess;
		this.setSeted(F.csscAssess);
	}
	/*******************************三方名称**********************************/	
	/**
	* 三方名称 [非空]      
	**/
	public Long getCsscThird(){
		return this.csscThird;
	}
	/**
	* 获取三方名称格式化(toString)
	**/
	public String getCsscThird$(){
		String strValue="";
		 if($.equals($.str(this.getCsscThird()),"1"))
			strValue=$.str("滴滴专车");		 
		 if($.equals($.str(this.getCsscThird()),"2"))
			strValue=$.str("易道专车");		 
	 	 return strValue;
	}
	/**
	* 三方名称 [非空]     
	**/
	public void setCsscThird(Long csscThird){
		this.csscThird = csscThird;
		this.setSeted(F.csscThird);
	}
	/*******************************三方订单号**********************************/	
	/**
	* 三方订单号 [非空]      
	**/
	public String getCsscThirdNo(){
		return this.csscThirdNo;
	}
	/**
	* 获取三方订单号格式化(toString)
	**/
	public String getCsscThirdNo$(){
		String strValue="";
		 strValue=$.str(this.getCsscThirdNo());
	 	 return strValue;
	}
	/**
	* 三方订单号 [非空]     
	**/
	public void setCsscThirdNo(String csscThirdNo){
		this.csscThirdNo = csscThirdNo;
		this.setSeted(F.csscThirdNo);
	}
	/*******************************三方费用**********************************/	
	/**
	* 三方费用 [非空]      
	**/
	public Double getCsscThirdFee(){
		return this.csscThirdFee;
	}
	/**
	* 获取三方费用格式化(toString)
	**/
	public String getCsscThirdFee$(){
		String strValue="";
		 strValue=$.str(this.getCsscThirdFee());
	 	 return strValue;
	}
	/**
	* 三方费用 [非空]     
	**/
	public void setCsscThirdFee(Double csscThirdFee){
		this.csscThirdFee = csscThirdFee;
		this.setSeted(F.csscThirdFee);
	}
	/*******************************三方费用信息**********************************/	
	/**
	* 三方费用信息 [非空]      
	**/
	public String getCsscThirdFeeData(){
		return this.csscThirdFeeData;
	}
	/**
	* 获取三方费用信息格式化(toString)
	**/
	public String getCsscThirdFeeData$(){
		String strValue="";
		 strValue=$.str(this.getCsscThirdFeeData());
	 	 return strValue;
	}
	/**
	* 三方费用信息 [非空]     
	**/
	public void setCsscThirdFeeData(String csscThirdFeeData){
		this.csscThirdFeeData = csscThirdFeeData;
		this.setSeted(F.csscThirdFeeData);
	}
	/*******************************三方其它费用**********************************/	
	/**
	* 三方其它费用 [非空]      
	**/
	public Double getCsscThirdOtherFee(){
		return this.csscThirdOtherFee;
	}
	/**
	* 获取三方其它费用格式化(toString)
	**/
	public String getCsscThirdOtherFee$(){
		String strValue="";
		 strValue=$.str(this.getCsscThirdOtherFee());
	 	 return strValue;
	}
	/**
	* 三方其它费用 [非空]     
	**/
	public void setCsscThirdOtherFee(Double csscThirdOtherFee){
		this.csscThirdOtherFee = csscThirdOtherFee;
		this.setSeted(F.csscThirdOtherFee);
	}
	/*******************************三方其它费用描述**********************************/	
	/**
	* 三方其它费用描述 [非空]      
	**/
	public String getCsscThirdOtherFeeDes(){
		return this.csscThirdOtherFeeDes;
	}
	/**
	* 获取三方其它费用描述格式化(toString)
	**/
	public String getCsscThirdOtherFeeDes$(){
		String strValue="";
		 strValue=$.str(this.getCsscThirdOtherFeeDes());
	 	 return strValue;
	}
	/**
	* 三方其它费用描述 [非空]     
	**/
	public void setCsscThirdOtherFeeDes(String csscThirdOtherFeeDes){
		this.csscThirdOtherFeeDes = csscThirdOtherFeeDes;
		this.setSeted(F.csscThirdOtherFeeDes);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsscUpdateTime(){
		return this.csscUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsscUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsscUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCsscUpdateTime(Date csscUpdateTime){
		this.csscUpdateTime = csscUpdateTime;
		this.setSeted(F.csscUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsscAddTime(){
		return this.csscAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsscAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsscAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]     
	**/
	public void setCsscAddTime(Date csscAddTime){
		this.csscAddTime = csscAddTime;
		this.setSeted(F.csscAddTime);
	}
	/*******************************响应状态**********************************/	
	/**
	* 响应状态 [非空]      
	**/
	public String getCsscResponse(){
		return this.csscResponse;
	}
	/**
	* 获取响应状态格式化(toString)
	**/
	public String getCsscResponse$(){
		String strValue="";
		 strValue=$.str(this.getCsscResponse());
	 	 return strValue;
	}
	/**
	* 响应状态 [非空]     
	**/
	public void setCsscResponse(String csscResponse){
		this.csscResponse = csscResponse;
		this.setSeted(F.csscResponse);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]      
	**/
	public Short getCsscStatus(){
		return this.csscStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsscStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsscStatus()),"3"))
			 strValue=$.str("已接单");
		 if($.equals($.str(this.getCsscStatus()),"2"))
			 strValue=$.str("已下单");
		 if($.equals($.str(this.getCsscStatus()),"1"))
			 strValue=$.str("已审核");
		 if($.equals($.str(this.getCsscStatus()),"0"))
			 strValue=$.str("待审核");
		 if($.equals($.str(this.getCsscStatus()),"6"))
			 strValue=$.str("已作废");
		 if($.equals($.str(this.getCsscStatus()),"5"))
			 strValue=$.str("已取消");
		 if($.equals($.str(this.getCsscStatus()),"4"))
			 strValue=$.str("已完成");
		 if($.equals($.str(this.getCsscStatus()),"7"))
			 strValue=$.str("待处理");
		 if($.equals($.str(this.getCsscStatus()),"8"))
			 strValue=$.str("司机到达");
		 if($.equals($.str(this.getCsscStatus()),"9"))
			 strValue=$.str("行程中");
	 	 return strValue;
	}
	/**
	* 状态 [非空]     
	**/
	public void setCsscStatus(Short csscStatus){
		this.csscStatus = csscStatus;
		this.setSeted(F.csscStatus);
	}
	/*******************************预估里程(单位:公里)**********************************/	
	/**
	* 预估里程(单位:公里) [非空]      
	**/
	public Double getCsscPreditMileage(){
		return this.csscPreditMileage;
	}
	/**
	* 获取预估里程(单位:公里)格式化(toString)
	**/
	public String getCsscPreditMileage$(){
		String strValue="";
		 strValue=$.str(this.getCsscPreditMileage());
	 	 return strValue;
	}
	/**
	* 预估里程(单位:公里) [非空]     
	**/
	public void setCsscPreditMileage(Double csscPreditMileage){
		this.csscPreditMileage = csscPreditMileage;
		this.setSeted(F.csscPreditMileage);
	}
	/*******************************预估费用**********************************/	
	/**
	* 预估费用 [非空]      
	**/
	public Double getCsscEstimateFee(){
		return this.csscEstimateFee;
	}
	/**
	* 获取预估费用格式化(toString)
	**/
	public String getCsscEstimateFee$(){
		String strValue="";
		 strValue=$.str(this.getCsscEstimateFee());
	 	 return strValue;
	}
	/**
	* 预估费用 [非空]     
	**/
	public void setCsscEstimateFee(Double csscEstimateFee){
		this.csscEstimateFee = csscEstimateFee;
		this.setSeted(F.csscEstimateFee);
	}
	/*******************************车车需求**********************************/	
	/**
	* 车车需求 [非空]      
	**/
	public String getCsscDemand(){
		return this.csscDemand;
	}
	/**
	* 获取车车需求格式化(toString)
	**/
	public String getCsscDemand$(){
		String strValue="";
		 strValue=$.str(this.getCsscDemand());
	 	 return strValue;
	}
	/**
	* 车车需求 [非空]     
	**/
	public void setCsscDemand(String csscDemand){
		this.csscDemand = csscDemand;
		this.setSeted(F.csscDemand);
	}
	/*******************************是否拼车**********************************/	
	/**
	* 是否拼车 [非空]      
	**/
	public Short getCsscIsCarpool(){
		return this.csscIsCarpool;
	}
	/**
	* 获取是否拼车格式化(toString)
	**/
	public String getCsscIsCarpool$(){
		String strValue="";
		 if($.equals($.str(this.getCsscIsCarpool()),"1"))
			 strValue=$.str("拼车");
		 if($.equals($.str(this.getCsscIsCarpool()),"0"))
			 strValue=$.str("不拼车");
	 	 return strValue;
	}
	/**
	* 是否拼车 [非空]     
	**/
	public void setCsscIsCarpool(Short csscIsCarpool){
		this.csscIsCarpool = csscIsCarpool;
		this.setSeted(F.csscIsCarpool);
	}
	/*******************************司机到达时间**********************************/	
	/**
	* 司机到达时间 [非空]      
	**/
	public Date getCsscDriverArrivaltime(){
		return this.csscDriverArrivaltime;
	}
	/**
	* 获取司机到达时间格式化(toString)
	**/
	public String getCsscDriverArrivaltime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsscDriverArrivaltime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 司机到达时间 [非空]     
	**/
	public void setCsscDriverArrivaltime(Date csscDriverArrivaltime){
		this.csscDriverArrivaltime = csscDriverArrivaltime;
		this.setSeted(F.csscDriverArrivaltime);
	}
	/*******************************专车司机**********************************/	
	/**
	* 专车司机 [可空]      
	**/
	public Long getCsscDrivercar(){
		return this.csscDrivercar;
	}
	/**
	* 获取专车司机格式化(toString)
	**/
	public String getCsscDrivercar$(){
		String strValue="";
		if(this.getCsscDrivercar()!=null){
				strValue+=$.str(CsDriverCar.getKeyValue(this.getCsscDrivercar()));
		}			
	 	 return strValue;
	}
	/**
	* 专车司机 [可空]     
	**/
	public void setCsscDrivercar(Long csscDrivercar){
		this.csscDrivercar = csscDrivercar;
		this.setSeted(F.csscDrivercar);
	}
	/**
	* 获取关联对象[专车司机]
	**/	 			
 	public CsDriverCar get$csscDrivercar(){
 		com.ccclubs.model.CsDriverCar csDriverCar = (com.ccclubs.model.CsDriverCar) $.GetRequest("CsDriverCar$"+this.getCsscDrivercar());
 		if(csDriverCar!=null)
			return csDriverCar;
		if(this.getCsscDrivercar()!=null){
			csDriverCar = CsDriverCar.get(this.getCsscDrivercar());
 		}
 		$.SetRequest("CsDriverCar$"+this.getCsscDrivercar(), csDriverCar);
	 	return csDriverCar;
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsSpecialCar.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsSpecialCar.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsSpecialCar.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsSpecialCar.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsSpecialCar.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsSpecialCar.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsSpecialCar.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsSpecialCar.this);
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
		public M csscId(Object csscId){this.put("csscId", csscId);return this;};
		/** and cssc_id is null */
		public M csscIdNull(){if(this.get("csscIdNot")==null)this.put("csscIdNot", "");this.put("csscId", null);return this;};
		/** not .... */
		public M csscIdNot(){this.put("csscIdNot", "not");return this;};
		/** 运营地区 [非空]       **/
		public M csscHost(Object csscHost){this.put("csscHost", csscHost);return this;};
		/** and cssc_host is null */
		public M csscHostNull(){if(this.get("csscHostNot")==null)this.put("csscHostNot", "");this.put("csscHost", null);return this;};
		/** not .... */
		public M csscHostNot(){this.put("csscHostNot", "not");return this;};
		/** 企业信息 [非空]       **/
		public M csscInfo(Object csscInfo){this.put("csscInfo", csscInfo);return this;};
		/** and cssc_info is null */
		public M csscInfoNull(){if(this.get("csscInfoNot")==null)this.put("csscInfoNot", "");this.put("csscInfo", null);return this;};
		/** not .... */
		public M csscInfoNot(){this.put("csscInfoNot", "not");return this;};
		public M csscInfo$on(CsUnitInfo.M value){
			this.put("CsUnitInfo", value);
			this.put("csscInfo$on", value);
			return this;
		};	
		/** 用车人员 [非空]       **/
		public M csscPerson(Object csscPerson){this.put("csscPerson", csscPerson);return this;};
		/** and cssc_person is null */
		public M csscPersonNull(){if(this.get("csscPersonNot")==null)this.put("csscPersonNot", "");this.put("csscPerson", null);return this;};
		/** not .... */
		public M csscPersonNot(){this.put("csscPersonNot", "not");return this;};
		public M csscPerson$on(CsUnitPerson.M value){
			this.put("CsUnitPerson", value);
			this.put("csscPerson$on", value);
			return this;
		};	
		/** 企业订单 [非空]       **/
		public M csscUnitOrder(Object csscUnitOrder){this.put("csscUnitOrder", csscUnitOrder);return this;};
		/** and cssc_unit_order is null */
		public M csscUnitOrderNull(){if(this.get("csscUnitOrderNot")==null)this.put("csscUnitOrderNot", "");this.put("csscUnitOrder", null);return this;};
		/** not .... */
		public M csscUnitOrderNot(){this.put("csscUnitOrderNot", "not");return this;};
		public M csscUnitOrder$on(CsUnitOrder.M value){
			this.put("CsUnitOrder", value);
			this.put("csscUnitOrder$on", value);
			return this;
		};	
		/** 乘客姓名 [非空]       **/
		public M csscName(Object csscName){this.put("csscName", csscName);return this;};
		/** and cssc_name is null */
		public M csscNameNull(){if(this.get("csscNameNot")==null)this.put("csscNameNot", "");this.put("csscName", null);return this;};
		/** not .... */
		public M csscNameNot(){this.put("csscNameNot", "not");return this;};
		/** 乘客电话 [非空]       **/
		public M csscMobile(Object csscMobile){this.put("csscMobile", csscMobile);return this;};
		/** and cssc_mobile is null */
		public M csscMobileNull(){if(this.get("csscMobileNot")==null)this.put("csscMobileNot", "");this.put("csscMobile", null);return this;};
		/** not .... */
		public M csscMobileNot(){this.put("csscMobileNot", "not");return this;};
		/** 乘车人数 [非空]       **/
		public M csscCount(Object csscCount){this.put("csscCount", csscCount);return this;};
		/** and cssc_count is null */
		public M csscCountNull(){if(this.get("csscCountNot")==null)this.put("csscCountNot", "");this.put("csscCount", null);return this;};
		/** not .... */
		public M csscCountNot(){this.put("csscCountNot", "not");return this;};
		/** 预定车型 [非空]       **/
		public M csscCarModel(Object csscCarModel){this.put("csscCarModel", csscCarModel);return this;};
		/** and cssc_car_model is null */
		public M csscCarModelNull(){if(this.get("csscCarModelNot")==null)this.put("csscCarModelNot", "");this.put("csscCarModel", null);return this;};
		/** not .... */
		public M csscCarModelNot(){this.put("csscCarModelNot", "not");return this;};
		public M csscCarModel$on(CsSpecialModel.M value){
			this.put("CsSpecialModel", value);
			this.put("csscCarModel$on", value);
			return this;
		};	
		/** 车牌号码 [非空]       **/
		public M csscCarNumber(Object csscCarNumber){this.put("csscCarNumber", csscCarNumber);return this;};
		/** and cssc_car_number is null */
		public M csscCarNumberNull(){if(this.get("csscCarNumberNot")==null)this.put("csscCarNumberNot", "");this.put("csscCarNumber", null);return this;};
		/** not .... */
		public M csscCarNumberNot(){this.put("csscCarNumberNot", "not");return this;};
		/** 司机姓名 [非空]       **/
		public M csscDriverName(Object csscDriverName){this.put("csscDriverName", csscDriverName);return this;};
		/** and cssc_driver_name is null */
		public M csscDriverNameNull(){if(this.get("csscDriverNameNot")==null)this.put("csscDriverNameNot", "");this.put("csscDriverName", null);return this;};
		/** not .... */
		public M csscDriverNameNot(){this.put("csscDriverNameNot", "not");return this;};
		/** 司机电话 [非空]       **/
		public M csscDriverPhone(Object csscDriverPhone){this.put("csscDriverPhone", csscDriverPhone);return this;};
		/** and cssc_driver_phone is null */
		public M csscDriverPhoneNull(){if(this.get("csscDriverPhoneNot")==null)this.put("csscDriverPhoneNot", "");this.put("csscDriverPhone", null);return this;};
		/** not .... */
		public M csscDriverPhoneNot(){this.put("csscDriverPhoneNot", "not");return this;};
		/** 司机照片 [非空]       **/
		public M csscDriverImage(Object csscDriverImage){this.put("csscDriverImage", csscDriverImage);return this;};
		/** and cssc_driver_image is null */
		public M csscDriverImageNull(){if(this.get("csscDriverImageNot")==null)this.put("csscDriverImageNot", "");this.put("csscDriverImage", null);return this;};
		/** not .... */
		public M csscDriverImageNot(){this.put("csscDriverImageNot", "not");return this;};
		/** 车辆颜色 [非空]       **/
		public M csscDriverColor(Object csscDriverColor){this.put("csscDriverColor", csscDriverColor);return this;};
		/** and cssc_driver_color is null */
		public M csscDriverColorNull(){if(this.get("csscDriverColorNot")==null)this.put("csscDriverColorNot", "");this.put("csscDriverColor", null);return this;};
		/** not .... */
		public M csscDriverColorNot(){this.put("csscDriverColorNot", "not");return this;};
		/** 车型信息 [非空]       **/
		public M csscDriverCarInfo(Object csscDriverCarInfo){this.put("csscDriverCarInfo", csscDriverCarInfo);return this;};
		/** and cssc_driver_car_info is null */
		public M csscDriverCarInfoNull(){if(this.get("csscDriverCarInfoNot")==null)this.put("csscDriverCarInfoNot", "");this.put("csscDriverCarInfo", null);return this;};
		/** not .... */
		public M csscDriverCarInfoNot(){this.put("csscDriverCarInfoNot", "not");return this;};
		/** 客户留言 [非空]       **/
		public M csscMsg(Object csscMsg){this.put("csscMsg", csscMsg);return this;};
		/** and cssc_msg is null */
		public M csscMsgNull(){if(this.get("csscMsgNot")==null)this.put("csscMsgNot", "");this.put("csscMsg", null);return this;};
		/** not .... */
		public M csscMsgNot(){this.put("csscMsgNot", "not");return this;};
		/** 起点位置 [非空]       **/
		public M csscStartPoint(Object csscStartPoint){this.put("csscStartPoint", csscStartPoint);return this;};
		/** and cssc_start_point is null */
		public M csscStartPointNull(){if(this.get("csscStartPointNot")==null)this.put("csscStartPointNot", "");this.put("csscStartPoint", null);return this;};
		/** not .... */
		public M csscStartPointNot(){this.put("csscStartPointNot", "not");return this;};
		/** 起点地址 [非空]       **/
		public M csscStartAddr(Object csscStartAddr){this.put("csscStartAddr", csscStartAddr);return this;};
		/** and cssc_start_addr is null */
		public M csscStartAddrNull(){if(this.get("csscStartAddrNot")==null)this.put("csscStartAddrNot", "");this.put("csscStartAddr", null);return this;};
		/** not .... */
		public M csscStartAddrNot(){this.put("csscStartAddrNot", "not");return this;};
		/** 开始时间 [非空]       **/
		public M csscStartTime(Object csscStartTime){this.put("csscStartTime", csscStartTime);return this;};
		/** and cssc_start_time is null */
		public M csscStartTimeNull(){if(this.get("csscStartTimeNot")==null)this.put("csscStartTimeNot", "");this.put("csscStartTime", null);return this;};
		/** not .... */
		public M csscStartTimeNot(){this.put("csscStartTimeNot", "not");return this;};
		/** and cssc_start_time >= ? */
 		public M csscStartTimeStart(Object start){this.put("csscStartTimeStart", start);return this;};			
 		/** and cssc_start_time <= ? */
 		public M csscStartTimeEnd(Object end){this.put("csscStartTimeEnd", end);return this;};
		/** 终点位置 [非空]       **/
		public M csscFinishPoint(Object csscFinishPoint){this.put("csscFinishPoint", csscFinishPoint);return this;};
		/** and cssc_finish_point is null */
		public M csscFinishPointNull(){if(this.get("csscFinishPointNot")==null)this.put("csscFinishPointNot", "");this.put("csscFinishPoint", null);return this;};
		/** not .... */
		public M csscFinishPointNot(){this.put("csscFinishPointNot", "not");return this;};
		/** 终点地址 [非空]       **/
		public M csscFinishAddr(Object csscFinishAddr){this.put("csscFinishAddr", csscFinishAddr);return this;};
		/** and cssc_finish_addr is null */
		public M csscFinishAddrNull(){if(this.get("csscFinishAddrNot")==null)this.put("csscFinishAddrNot", "");this.put("csscFinishAddr", null);return this;};
		/** not .... */
		public M csscFinishAddrNot(){this.put("csscFinishAddrNot", "not");return this;};
		/** 结束时间 [非空]       **/
		public M csscFinishTime(Object csscFinishTime){this.put("csscFinishTime", csscFinishTime);return this;};
		/** and cssc_finish_time is null */
		public M csscFinishTimeNull(){if(this.get("csscFinishTimeNot")==null)this.put("csscFinishTimeNot", "");this.put("csscFinishTime", null);return this;};
		/** not .... */
		public M csscFinishTimeNot(){this.put("csscFinishTimeNot", "not");return this;};
		/** and cssc_finish_time >= ? */
 		public M csscFinishTimeStart(Object start){this.put("csscFinishTimeStart", start);return this;};			
 		/** and cssc_finish_time <= ? */
 		public M csscFinishTimeEnd(Object end){this.put("csscFinishTimeEnd", end);return this;};
		/** 订单时长 [非空]       **/
		public M csscDuration(Object csscDuration){this.put("csscDuration", csscDuration);return this;};
		/** and cssc_duration is null */
		public M csscDurationNull(){if(this.get("csscDurationNot")==null)this.put("csscDurationNot", "");this.put("csscDuration", null);return this;};
		/** not .... */
		public M csscDurationNot(){this.put("csscDurationNot", "not");return this;};
		/** 实际时长 [非空]       **/
		public M csscRealDur(Object csscRealDur){this.put("csscRealDur", csscRealDur);return this;};
		/** and cssc_real_dur is null */
		public M csscRealDurNull(){if(this.get("csscRealDurNot")==null)this.put("csscRealDurNot", "");this.put("csscRealDur", null);return this;};
		/** not .... */
		public M csscRealDurNot(){this.put("csscRealDurNot", "not");return this;};
		/** 公里数 [非空]       **/
		public M csscKilo(Object csscKilo){this.put("csscKilo", csscKilo);return this;};
		/** and cssc_kilo is null */
		public M csscKiloNull(){if(this.get("csscKiloNot")==null)this.put("csscKiloNot", "");this.put("csscKilo", null);return this;};
		/** not .... */
		public M csscKiloNot(){this.put("csscKiloNot", "not");return this;};
		/** 用车评分 [非空]       **/
		public M csscScope(Object csscScope){this.put("csscScope", csscScope);return this;};
		/** and cssc_scope is null */
		public M csscScopeNull(){if(this.get("csscScopeNot")==null)this.put("csscScopeNot", "");this.put("csscScope", null);return this;};
		/** not .... */
		public M csscScopeNot(){this.put("csscScopeNot", "not");return this;};
		/** 用车评价 [非空]       **/
		public M csscAssess(Object csscAssess){this.put("csscAssess", csscAssess);return this;};
		/** and cssc_assess is null */
		public M csscAssessNull(){if(this.get("csscAssessNot")==null)this.put("csscAssessNot", "");this.put("csscAssess", null);return this;};
		/** not .... */
		public M csscAssessNot(){this.put("csscAssessNot", "not");return this;};
		/** 三方名称 [非空]       **/
		public M csscThird(Object csscThird){this.put("csscThird", csscThird);return this;};
		/** and cssc_third is null */
		public M csscThirdNull(){if(this.get("csscThirdNot")==null)this.put("csscThirdNot", "");this.put("csscThird", null);return this;};
		/** not .... */
		public M csscThirdNot(){this.put("csscThirdNot", "not");return this;};
		/** 三方订单号 [非空]       **/
		public M csscThirdNo(Object csscThirdNo){this.put("csscThirdNo", csscThirdNo);return this;};
		/** and cssc_third_no is null */
		public M csscThirdNoNull(){if(this.get("csscThirdNoNot")==null)this.put("csscThirdNoNot", "");this.put("csscThirdNo", null);return this;};
		/** not .... */
		public M csscThirdNoNot(){this.put("csscThirdNoNot", "not");return this;};
		/** 三方费用 [非空]       **/
		public M csscThirdFee(Object csscThirdFee){this.put("csscThirdFee", csscThirdFee);return this;};
		/** and cssc_third_fee is null */
		public M csscThirdFeeNull(){if(this.get("csscThirdFeeNot")==null)this.put("csscThirdFeeNot", "");this.put("csscThirdFee", null);return this;};
		/** not .... */
		public M csscThirdFeeNot(){this.put("csscThirdFeeNot", "not");return this;};
		/** 三方费用信息 [非空]       **/
		public M csscThirdFeeData(Object csscThirdFeeData){this.put("csscThirdFeeData", csscThirdFeeData);return this;};
		/** and cssc_third_fee_data is null */
		public M csscThirdFeeDataNull(){if(this.get("csscThirdFeeDataNot")==null)this.put("csscThirdFeeDataNot", "");this.put("csscThirdFeeData", null);return this;};
		/** not .... */
		public M csscThirdFeeDataNot(){this.put("csscThirdFeeDataNot", "not");return this;};
		/** 三方其它费用 [非空]       **/
		public M csscThirdOtherFee(Object csscThirdOtherFee){this.put("csscThirdOtherFee", csscThirdOtherFee);return this;};
		/** and cssc_third_other_fee is null */
		public M csscThirdOtherFeeNull(){if(this.get("csscThirdOtherFeeNot")==null)this.put("csscThirdOtherFeeNot", "");this.put("csscThirdOtherFee", null);return this;};
		/** not .... */
		public M csscThirdOtherFeeNot(){this.put("csscThirdOtherFeeNot", "not");return this;};
		/** 三方其它费用描述 [非空]       **/
		public M csscThirdOtherFeeDes(Object csscThirdOtherFeeDes){this.put("csscThirdOtherFeeDes", csscThirdOtherFeeDes);return this;};
		/** and cssc_third_other_fee_des is null */
		public M csscThirdOtherFeeDesNull(){if(this.get("csscThirdOtherFeeDesNot")==null)this.put("csscThirdOtherFeeDesNot", "");this.put("csscThirdOtherFeeDes", null);return this;};
		/** not .... */
		public M csscThirdOtherFeeDesNot(){this.put("csscThirdOtherFeeDesNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csscUpdateTime(Object csscUpdateTime){this.put("csscUpdateTime", csscUpdateTime);return this;};
		/** and cssc_update_time is null */
		public M csscUpdateTimeNull(){if(this.get("csscUpdateTimeNot")==null)this.put("csscUpdateTimeNot", "");this.put("csscUpdateTime", null);return this;};
		/** not .... */
		public M csscUpdateTimeNot(){this.put("csscUpdateTimeNot", "not");return this;};
		/** and cssc_update_time >= ? */
 		public M csscUpdateTimeStart(Object start){this.put("csscUpdateTimeStart", start);return this;};			
 		/** and cssc_update_time <= ? */
 		public M csscUpdateTimeEnd(Object end){this.put("csscUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csscAddTime(Object csscAddTime){this.put("csscAddTime", csscAddTime);return this;};
		/** and cssc_add_time is null */
		public M csscAddTimeNull(){if(this.get("csscAddTimeNot")==null)this.put("csscAddTimeNot", "");this.put("csscAddTime", null);return this;};
		/** not .... */
		public M csscAddTimeNot(){this.put("csscAddTimeNot", "not");return this;};
		/** and cssc_add_time >= ? */
 		public M csscAddTimeStart(Object start){this.put("csscAddTimeStart", start);return this;};			
 		/** and cssc_add_time <= ? */
 		public M csscAddTimeEnd(Object end){this.put("csscAddTimeEnd", end);return this;};
		/** 响应状态 [非空]       **/
		public M csscResponse(Object csscResponse){this.put("csscResponse", csscResponse);return this;};
		/** and cssc_response is null */
		public M csscResponseNull(){if(this.get("csscResponseNot")==null)this.put("csscResponseNot", "");this.put("csscResponse", null);return this;};
		/** not .... */
		public M csscResponseNot(){this.put("csscResponseNot", "not");return this;};
		/** 状态 [非空]       **/
		public M csscStatus(Object csscStatus){this.put("csscStatus", csscStatus);return this;};
		/** and cssc_status is null */
		public M csscStatusNull(){if(this.get("csscStatusNot")==null)this.put("csscStatusNot", "");this.put("csscStatus", null);return this;};
		/** not .... */
		public M csscStatusNot(){this.put("csscStatusNot", "not");return this;};
		/** 预估里程(单位:公里) [非空]       **/
		public M csscPreditMileage(Object csscPreditMileage){this.put("csscPreditMileage", csscPreditMileage);return this;};
		/** and cssc_predit_mileage is null */
		public M csscPreditMileageNull(){if(this.get("csscPreditMileageNot")==null)this.put("csscPreditMileageNot", "");this.put("csscPreditMileage", null);return this;};
		/** not .... */
		public M csscPreditMileageNot(){this.put("csscPreditMileageNot", "not");return this;};
		/** 预估费用 [非空]       **/
		public M csscEstimateFee(Object csscEstimateFee){this.put("csscEstimateFee", csscEstimateFee);return this;};
		/** and cssc_estimate_fee is null */
		public M csscEstimateFeeNull(){if(this.get("csscEstimateFeeNot")==null)this.put("csscEstimateFeeNot", "");this.put("csscEstimateFee", null);return this;};
		/** not .... */
		public M csscEstimateFeeNot(){this.put("csscEstimateFeeNot", "not");return this;};
		/** 车车需求 [非空]       **/
		public M csscDemand(Object csscDemand){this.put("csscDemand", csscDemand);return this;};
		/** and cssc_demand is null */
		public M csscDemandNull(){if(this.get("csscDemandNot")==null)this.put("csscDemandNot", "");this.put("csscDemand", null);return this;};
		/** not .... */
		public M csscDemandNot(){this.put("csscDemandNot", "not");return this;};
		/** 是否拼车 [非空]       **/
		public M csscIsCarpool(Object csscIsCarpool){this.put("csscIsCarpool", csscIsCarpool);return this;};
		/** and cssc_is_carpool is null */
		public M csscIsCarpoolNull(){if(this.get("csscIsCarpoolNot")==null)this.put("csscIsCarpoolNot", "");this.put("csscIsCarpool", null);return this;};
		/** not .... */
		public M csscIsCarpoolNot(){this.put("csscIsCarpoolNot", "not");return this;};
		/** 司机到达时间 [非空]       **/
		public M csscDriverArrivaltime(Object csscDriverArrivaltime){this.put("csscDriverArrivaltime", csscDriverArrivaltime);return this;};
		/** and cssc_driver_arrivaltime is null */
		public M csscDriverArrivaltimeNull(){if(this.get("csscDriverArrivaltimeNot")==null)this.put("csscDriverArrivaltimeNot", "");this.put("csscDriverArrivaltime", null);return this;};
		/** not .... */
		public M csscDriverArrivaltimeNot(){this.put("csscDriverArrivaltimeNot", "not");return this;};
		/** and cssc_driver_arrivaltime >= ? */
 		public M csscDriverArrivaltimeStart(Object start){this.put("csscDriverArrivaltimeStart", start);return this;};			
 		/** and cssc_driver_arrivaltime <= ? */
 		public M csscDriverArrivaltimeEnd(Object end){this.put("csscDriverArrivaltimeEnd", end);return this;};
		/** 专车司机 [可空]       **/
		public M csscDrivercar(Object csscDrivercar){this.put("csscDrivercar", csscDrivercar);return this;};
		/** and cssc_drivercar is null */
		public M csscDrivercarNull(){if(this.get("csscDrivercarNot")==null)this.put("csscDrivercarNot", "");this.put("csscDrivercar", null);return this;};
		/** not .... */
		public M csscDrivercarNot(){this.put("csscDrivercarNot", "not");return this;};
		public M csscDrivercar$on(CsDriverCar.M value){
			this.put("CsDriverCar", value);
			this.put("csscDrivercar$on", value);
			return this;
		};	
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有专车订单 **/
		public @api List<CsSpecialCar> list(Integer size){
			return getCsSpecialCarList(this,size);
		}
		/** 获取专车订单分页 **/
		public @api Page<CsSpecialCar> page(int page,int size){
			return getCsSpecialCarPage(page, size , this);
		}
		/** 根据查询条件取专车订单 **/
		public @api CsSpecialCar get(){
			return getCsSpecialCar(this);
		}
		/** 获取专车订单数 **/
		public @api Long count(){
			return getCsSpecialCarCount(this);
		}
		/** 获取专车订单表达式 **/
		public @api <T> T eval(String strEval){
			return getCsSpecialCarEval(strEval,this);
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
			updateCsSpecialCar(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csscId="csscId";
		/** 运营地区 [非空]       **/
		public final static @type(Long.class)  String csscHost="csscHost";
		/** 企业信息 [非空]       **/
		public final static @type(Long.class)  String csscInfo="csscInfo";
		/** 用车人员 [非空]       **/
		public final static @type(Long.class)  String csscPerson="csscPerson";
		/** 企业订单 [非空]       **/
		public final static @type(Long.class)  String csscUnitOrder="csscUnitOrder";
		/** 乘客姓名 [非空]       **/
		public final static @type(String.class)  String csscName="csscName";
		/** 乘客电话 [非空]       **/
		public final static @type(String.class)  String csscMobile="csscMobile";
		/** 乘车人数 [非空]       **/
		public final static @type(Integer.class)  String csscCount="csscCount";
		/** 预定车型 [非空]       **/
		public final static @type(Long.class)  String csscCarModel="csscCarModel";
		/** 车牌号码 [非空]       **/
		public final static @type(String.class)@like  String csscCarNumber="csscCarNumber";
		/** 司机姓名 [非空]       **/
		public final static @type(String.class)  String csscDriverName="csscDriverName";
		/** 司机电话 [非空]       **/
		public final static @type(String.class)  String csscDriverPhone="csscDriverPhone";
		/** 司机照片 [非空]       **/
		public final static @type(String.class)  String csscDriverImage="csscDriverImage";
		/** 车辆颜色 [非空]       **/
		public final static @type(String.class)  String csscDriverColor="csscDriverColor";
		/** 车型信息 [非空]       **/
		public final static @type(String.class)  String csscDriverCarInfo="csscDriverCarInfo";
		/** 客户留言 [非空]       **/
		public final static @type(String.class)@like  String csscMsg="csscMsg";
		/** 起点位置 [非空]       **/
		public final static @type(String.class)  String csscStartPoint="csscStartPoint";
		/** 起点地址 [非空]       **/
		public final static @type(String.class)@like  String csscStartAddr="csscStartAddr";
		/** 开始时间 [非空]       **/
		public final static @type(Date.class)  String csscStartTime="csscStartTime";
		/** and cssc_start_time >= ? */
 		public final static @type(Date.class) String csscStartTimeStart="csscStartTimeStart";
 		/** and cssc_start_time <= ? */
 		public final static @type(Date.class) String csscStartTimeEnd="csscStartTimeEnd";
		/** 终点位置 [非空]       **/
		public final static @type(String.class)  String csscFinishPoint="csscFinishPoint";
		/** 终点地址 [非空]       **/
		public final static @type(String.class)@like  String csscFinishAddr="csscFinishAddr";
		/** 结束时间 [非空]       **/
		public final static @type(Date.class)  String csscFinishTime="csscFinishTime";
		/** and cssc_finish_time >= ? */
 		public final static @type(Date.class) String csscFinishTimeStart="csscFinishTimeStart";
 		/** and cssc_finish_time <= ? */
 		public final static @type(Date.class) String csscFinishTimeEnd="csscFinishTimeEnd";
		/** 订单时长 [非空]       **/
		public final static @type(Long.class)  String csscDuration="csscDuration";
		/** 实际时长 [非空]       **/
		public final static @type(Long.class)  String csscRealDur="csscRealDur";
		/** 公里数 [非空]       **/
		public final static @type(Integer.class)  String csscKilo="csscKilo";
		/** 用车评分 [非空]       **/
		public final static @type(Short.class)  String csscScope="csscScope";
		/** 用车评价 [非空]       **/
		public final static @type(String.class)@like  String csscAssess="csscAssess";
		/** 三方名称 [非空]       **/
		public final static @type(Long.class)  String csscThird="csscThird";
		/** 三方订单号 [非空]       **/
		public final static @type(String.class)  String csscThirdNo="csscThirdNo";
		/** 三方费用 [非空]       **/
		public final static @type(Double.class)  String csscThirdFee="csscThirdFee";
		/** 三方费用信息 [非空]       **/
		public final static @type(String.class)@like  String csscThirdFeeData="csscThirdFeeData";
		/** 三方其它费用 [非空]       **/
		public final static @type(Double.class)  String csscThirdOtherFee="csscThirdOtherFee";
		/** 三方其它费用描述 [非空]       **/
		public final static @type(String.class)@like  String csscThirdOtherFeeDes="csscThirdOtherFeeDes";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csscUpdateTime="csscUpdateTime";
		/** and cssc_update_time >= ? */
 		public final static @type(Date.class) String csscUpdateTimeStart="csscUpdateTimeStart";
 		/** and cssc_update_time <= ? */
 		public final static @type(Date.class) String csscUpdateTimeEnd="csscUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csscAddTime="csscAddTime";
		/** and cssc_add_time >= ? */
 		public final static @type(Date.class) String csscAddTimeStart="csscAddTimeStart";
 		/** and cssc_add_time <= ? */
 		public final static @type(Date.class) String csscAddTimeEnd="csscAddTimeEnd";
		/** 响应状态 [非空]       **/
		public final static @type(String.class)@like  String csscResponse="csscResponse";
		/** 状态 [非空]       **/
		public final static @type(Short.class)  String csscStatus="csscStatus";
		/** 预估里程(单位:公里) [非空]       **/
		public final static @type(Double.class)  String csscPreditMileage="csscPreditMileage";
		/** 预估费用 [非空]       **/
		public final static @type(Double.class)  String csscEstimateFee="csscEstimateFee";
		/** 车车需求 [非空]       **/
		public final static @type(String.class)  String csscDemand="csscDemand";
		/** 是否拼车 [非空]       **/
		public final static @type(Short.class)  String csscIsCarpool="csscIsCarpool";
		/** 司机到达时间 [非空]       **/
		public final static @type(Date.class)  String csscDriverArrivaltime="csscDriverArrivaltime";
		/** and cssc_driver_arrivaltime >= ? */
 		public final static @type(Date.class) String csscDriverArrivaltimeStart="csscDriverArrivaltimeStart";
 		/** and cssc_driver_arrivaltime <= ? */
 		public final static @type(Date.class) String csscDriverArrivaltimeEnd="csscDriverArrivaltimeEnd";
		/** 专车司机 [可空]       **/
		public final static @type(Long.class)  String csscDrivercar="csscDrivercar";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csscId="cssc_id";
		/** 运营地区 [非空]       **/
		public final static String csscHost="cssc_host";
		/** 企业信息 [非空]       **/
		public final static String csscInfo="cssc_info";
		/** 用车人员 [非空]       **/
		public final static String csscPerson="cssc_person";
		/** 企业订单 [非空]       **/
		public final static String csscUnitOrder="cssc_unit_order";
		/** 乘客姓名 [非空]       **/
		public final static String csscName="cssc_name";
		/** 乘客电话 [非空]       **/
		public final static String csscMobile="cssc_mobile";
		/** 乘车人数 [非空]       **/
		public final static String csscCount="cssc_count";
		/** 预定车型 [非空]       **/
		public final static String csscCarModel="cssc_car_model";
		/** 车牌号码 [非空]       **/
		public final static String csscCarNumber="cssc_car_number";
		/** 司机姓名 [非空]       **/
		public final static String csscDriverName="cssc_driver_name";
		/** 司机电话 [非空]       **/
		public final static String csscDriverPhone="cssc_driver_phone";
		/** 司机照片 [非空]       **/
		public final static String csscDriverImage="cssc_driver_image";
		/** 车辆颜色 [非空]       **/
		public final static String csscDriverColor="cssc_driver_color";
		/** 车型信息 [非空]       **/
		public final static String csscDriverCarInfo="cssc_driver_car_info";
		/** 客户留言 [非空]       **/
		public final static String csscMsg="cssc_msg";
		/** 起点位置 [非空]       **/
		public final static String csscStartPoint="cssc_start_point";
		/** 起点地址 [非空]       **/
		public final static String csscStartAddr="cssc_start_addr";
		/** 开始时间 [非空]       **/
		public final static String csscStartTime="cssc_start_time";
		/** 终点位置 [非空]       **/
		public final static String csscFinishPoint="cssc_finish_point";
		/** 终点地址 [非空]       **/
		public final static String csscFinishAddr="cssc_finish_addr";
		/** 结束时间 [非空]       **/
		public final static String csscFinishTime="cssc_finish_time";
		/** 订单时长 [非空]       **/
		public final static String csscDuration="cssc_duration";
		/** 实际时长 [非空]       **/
		public final static String csscRealDur="cssc_real_dur";
		/** 公里数 [非空]       **/
		public final static String csscKilo="cssc_kilo";
		/** 用车评分 [非空]       **/
		public final static String csscScope="cssc_scope";
		/** 用车评价 [非空]       **/
		public final static String csscAssess="cssc_assess";
		/** 三方名称 [非空]       **/
		public final static String csscThird="cssc_third";
		/** 三方订单号 [非空]       **/
		public final static String csscThirdNo="cssc_third_no";
		/** 三方费用 [非空]       **/
		public final static String csscThirdFee="cssc_third_fee";
		/** 三方费用信息 [非空]       **/
		public final static String csscThirdFeeData="cssc_third_fee_data";
		/** 三方其它费用 [非空]       **/
		public final static String csscThirdOtherFee="cssc_third_other_fee";
		/** 三方其它费用描述 [非空]       **/
		public final static String csscThirdOtherFeeDes="cssc_third_other_fee_des";
		/** 修改时间 [非空]       **/
		public final static String csscUpdateTime="cssc_update_time";
		/** 添加时间 [非空]       **/
		public final static String csscAddTime="cssc_add_time";
		/** 响应状态 [非空]       **/
		public final static String csscResponse="cssc_response";
		/** 状态 [非空]       **/
		public final static String csscStatus="cssc_status";
		/** 预估里程(单位:公里) [非空]       **/
		public final static String csscPreditMileage="cssc_predit_mileage";
		/** 预估费用 [非空]       **/
		public final static String csscEstimateFee="cssc_estimate_fee";
		/** 车车需求 [非空]       **/
		public final static String csscDemand="cssc_demand";
		/** 是否拼车 [非空]       **/
		public final static String csscIsCarpool="cssc_is_carpool";
		/** 司机到达时间 [非空]       **/
		public final static String csscDriverArrivaltime="cssc_driver_arrivaltime";
		/** 专车司机 [可空]       **/
		public final static String csscDrivercar="cssc_drivercar";
		
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
				$.Set(name,CsSpecialCar.getCsSpecialCar(params));
			else
				$.Set(name,CsSpecialCar.getCsSpecialCarList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取专车订单数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsSpecialCar) $.GetRequest("CsSpecialCar$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsSpecialCar.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsSpecialCar.getCsSpecialCar(params);
			else
				value = CsSpecialCar.getCsSpecialCarList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsSpecialCar.Get($.add(CsSpecialCar.F.csscId,param));
		else if(!$.empty(param.toString()))
			value = CsSpecialCar.get(Long.valueOf(param.toString()));
		$.SetRequest("CsSpecialCar$"+param.hashCode(), value);
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
	public void mergeSet(CsSpecialCar old){
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