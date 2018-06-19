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

@namespace("control/history")
public @caption("车辆历史状态") @table("cs_history_state") class CsHistoryState implements java.io.Serializable
{
	private static final long serialVersionUID =          1l;
	private @caption("编号") @column("cshs_id")   @primary  @note("  ") Long cshsId;// 主键 非空     
	private @caption("城市") @column("cshs_host")    @relate("$cshsHost") @RelateClass(SrvHost.class)  @note("  ") Long cshsHost;// 非空     
 	private SrvHost $cshsHost;//关联对象[运营城市]
	private @caption("车牌号") @column("cshs_number")    @note("  ") String cshsNumber;// 非空    ${basePath}${proname}/object/car_numbers.do 
	private @caption("添加时间") @column("cshs_add_time")    @note("  ") Date cshsAddTime;// 非空     
	private @caption("下位机时间") @column("cshs_current_time")    @note("  ") Date cshsCurrentTime;//     
	private @caption("租赁状态") @column("cshs_rented")    @note("  ") String cshsRented;//     
	private @caption("有报警") @column("cshs_warn")    @note("  ") String cshsWarn;//     
	private @caption("RFID卡号") @column("cshs_rfid")    @note("  ") String cshsRfid;//     
	private @caption("用户RFID") @column("cshs_rfid_dte")    @note("  ") String cshsRfidDte;//     
	private @caption("OBD里程") @column("cshs_obd_mile")    @note("  ") String cshsObdMile;//     
	private @caption("发动机温度") @column("cshs_engine_t")    @note("  ") String cshsEngineT;//     
	private @caption("总里程") @column("cshs_mileage")    @note("  ") String cshsMileage;//     
	private @caption("车速") @column("cshs_speed")    @note("  ") String cshsSpeed;//     
	private @caption("转速") @column("cshs_motor")    @note("  ") String cshsMotor;//     
	private @caption("燃油量") @column("cshs_oil")    @note("  ") String cshsOil;//     
	private @caption("蓄电量") @column("cshs_power")    @note("  ") String cshsPower;//     
	private @caption("动力电池电量") @column("cshs_ev_battery")    @note("  ") String cshsEvBattery;//     
	private @caption("在充电") @column("cshs_charging")    @note("  ") String cshsCharging;//     
	private @caption("油里程") @column("cshs_fuel_mileage")    @note("  ") String cshsFuelMileage;//     
	private @caption("电量程") @column("cshs_electric_mileage")    @note("  ") String cshsElectricMileage;//     
	private @caption("续航里程") @column("cshs_endurance")    @note("  ") String cshsEndurance;//     
	private @caption("温度") @column("cshs_temperature")    @note("  ") String cshsTemperature;//     
	private @caption("信号强度") @column("cshs_csq")    @note("  ") String cshsCsq;//     
	private @caption("功耗") @column("cshs_power_consumption")    @note("  ") String cshsPowerConsumption;//     
	private @caption("经度") @column("cshs_longitude")    @note("  ") String cshsLongitude;//     
	private @caption("纬度") @column("cshs_latitude")    @note("  ") String cshsLatitude;//     
	private @caption("当前订单") @column("cshs_order")    @relate("$cshsOrder") @RelateClass(CsOrder.class)  @note("  ") Long cshsOrder;//     
 	private CsOrder $cshsOrder;//关联对象[系统订单]
	private @caption("接口数据") @column("cshs_mo_data")    @note("  ") String cshsMoData;//     
	
	//默认构造函数
	public CsHistoryState(){
	
	}
	
	//主键构造函数
	public CsHistoryState(Long id){
		this.cshsId = id;
	}
	
	/**所有字段构造函数 CsHistoryState(cshsHost,cshsNumber,cshsAddTime,cshsCurrentTime,cshsRented,cshsWarn,cshsRfid,cshsRfidDte,cshsObdMile,cshsEngineT,cshsMileage,cshsSpeed,cshsMotor,cshsOil,cshsPower,cshsEvBattery,cshsCharging,cshsFuelMileage,cshsElectricMileage,cshsEndurance,cshsTemperature,cshsCsq,cshsPowerConsumption,cshsLongitude,cshsLatitude,cshsOrder,cshsMoData)
	 CsHistoryState(
	 	$.getLong("cshsHost")//城市 [非空]
	 	,$.getString("cshsNumber")//车牌号 [非空]
	 	,$.getDate("cshsAddTime")//添加时间 [非空]
	 	,$.getDate("cshsCurrentTime")//下位机时间
	 	,$.getString("cshsRented")//租赁状态
	 	,$.getString("cshsWarn")//有报警
	 	,$.getString("cshsRfid")//RFID卡号
	 	,$.getString("cshsRfidDte")//用户RFID
	 	,$.getString("cshsObdMile")//OBD里程
	 	,$.getString("cshsEngineT")//发动机温度
	 	,$.getString("cshsMileage")//总里程
	 	,$.getString("cshsSpeed")//车速
	 	,$.getString("cshsMotor")//转速
	 	,$.getString("cshsOil")//燃油量
	 	,$.getString("cshsPower")//蓄电量
	 	,$.getString("cshsEvBattery")//动力电池电量
	 	,$.getString("cshsCharging")//在充电
	 	,$.getString("cshsFuelMileage")//油里程
	 	,$.getString("cshsElectricMileage")//电量程
	 	,$.getString("cshsEndurance")//续航里程
	 	,$.getString("cshsTemperature")//温度
	 	,$.getString("cshsCsq")//信号强度
	 	,$.getString("cshsPowerConsumption")//功耗
	 	,$.getString("cshsLongitude")//经度
	 	,$.getString("cshsLatitude")//纬度
	 	,$.getLong("cshsOrder")//当前订单
	 	,$.getString("cshsMoData")//接口数据
	 )
	**/
	public CsHistoryState(Long cshsHost,String cshsNumber,Date cshsAddTime,Date cshsCurrentTime,String cshsRented,String cshsWarn,String cshsRfid,String cshsRfidDte,String cshsObdMile,String cshsEngineT,String cshsMileage,String cshsSpeed,String cshsMotor,String cshsOil,String cshsPower,String cshsEvBattery,String cshsCharging,String cshsFuelMileage,String cshsElectricMileage,String cshsEndurance,String cshsTemperature,String cshsCsq,String cshsPowerConsumption,String cshsLongitude,String cshsLatitude,Long cshsOrder,String cshsMoData){
		this.cshsHost=cshsHost;
		this.cshsNumber=cshsNumber;
		this.cshsAddTime=cshsAddTime;
		this.cshsCurrentTime=cshsCurrentTime;
		this.cshsRented=cshsRented;
		this.cshsWarn=cshsWarn;
		this.cshsRfid=cshsRfid;
		this.cshsRfidDte=cshsRfidDte;
		this.cshsObdMile=cshsObdMile;
		this.cshsEngineT=cshsEngineT;
		this.cshsMileage=cshsMileage;
		this.cshsSpeed=cshsSpeed;
		this.cshsMotor=cshsMotor;
		this.cshsOil=cshsOil;
		this.cshsPower=cshsPower;
		this.cshsEvBattery=cshsEvBattery;
		this.cshsCharging=cshsCharging;
		this.cshsFuelMileage=cshsFuelMileage;
		this.cshsElectricMileage=cshsElectricMileage;
		this.cshsEndurance=cshsEndurance;
		this.cshsTemperature=cshsTemperature;
		this.cshsCsq=cshsCsq;
		this.cshsPowerConsumption=cshsPowerConsumption;
		this.cshsLongitude=cshsLongitude;
		this.cshsLatitude=cshsLatitude;
		this.cshsOrder=cshsOrder;
		this.cshsMoData=cshsMoData;
	}
	
	//设置非空字段
	public CsHistoryState setNotNull(Long cshsHost,String cshsNumber,Date cshsAddTime){
		this.cshsHost=cshsHost;
		this.cshsNumber=cshsNumber;
		this.cshsAddTime=cshsAddTime;
		return this;
	}
	/** 编号 [非空]       **/
	public CsHistoryState cshsId(Long cshsId){
		this.cshsId = cshsId;
		this.setSeted(F.cshsId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsHistoryState cshsHost(Long cshsHost){
		this.cshsHost = cshsHost;
		this.setSeted(F.cshsHost);
		return this;
	}
	/** 车牌号 [非空]      ${basePath}${proname}/object/car_numbers.do **/
	public CsHistoryState cshsNumber(String cshsNumber){
		this.cshsNumber = cshsNumber;
		this.setSeted(F.cshsNumber);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsHistoryState cshsAddTime(Date cshsAddTime){
		this.cshsAddTime = cshsAddTime;
		this.setSeted(F.cshsAddTime);
		return this;
	}
	/** 下位机时间        **/
	public CsHistoryState cshsCurrentTime(Date cshsCurrentTime){
		this.cshsCurrentTime = cshsCurrentTime;
		this.setSeted(F.cshsCurrentTime);
		return this;
	}
	/** 租赁状态        **/
	public CsHistoryState cshsRented(String cshsRented){
		this.cshsRented = cshsRented;
		this.setSeted(F.cshsRented);
		return this;
	}
	/** 有报警        **/
	public CsHistoryState cshsWarn(String cshsWarn){
		this.cshsWarn = cshsWarn;
		this.setSeted(F.cshsWarn);
		return this;
	}
	/** RFID卡号        **/
	public CsHistoryState cshsRfid(String cshsRfid){
		this.cshsRfid = cshsRfid;
		this.setSeted(F.cshsRfid);
		return this;
	}
	/** 用户RFID        **/
	public CsHistoryState cshsRfidDte(String cshsRfidDte){
		this.cshsRfidDte = cshsRfidDte;
		this.setSeted(F.cshsRfidDte);
		return this;
	}
	/** OBD里程        **/
	public CsHistoryState cshsObdMile(String cshsObdMile){
		this.cshsObdMile = cshsObdMile;
		this.setSeted(F.cshsObdMile);
		return this;
	}
	/** 发动机温度        **/
	public CsHistoryState cshsEngineT(String cshsEngineT){
		this.cshsEngineT = cshsEngineT;
		this.setSeted(F.cshsEngineT);
		return this;
	}
	/** 总里程        **/
	public CsHistoryState cshsMileage(String cshsMileage){
		this.cshsMileage = cshsMileage;
		this.setSeted(F.cshsMileage);
		return this;
	}
	/** 车速        **/
	public CsHistoryState cshsSpeed(String cshsSpeed){
		this.cshsSpeed = cshsSpeed;
		this.setSeted(F.cshsSpeed);
		return this;
	}
	/** 转速        **/
	public CsHistoryState cshsMotor(String cshsMotor){
		this.cshsMotor = cshsMotor;
		this.setSeted(F.cshsMotor);
		return this;
	}
	/** 燃油量        **/
	public CsHistoryState cshsOil(String cshsOil){
		this.cshsOil = cshsOil;
		this.setSeted(F.cshsOil);
		return this;
	}
	/** 蓄电量        **/
	public CsHistoryState cshsPower(String cshsPower){
		this.cshsPower = cshsPower;
		this.setSeted(F.cshsPower);
		return this;
	}
	/** 动力电池电量        **/
	public CsHistoryState cshsEvBattery(String cshsEvBattery){
		this.cshsEvBattery = cshsEvBattery;
		this.setSeted(F.cshsEvBattery);
		return this;
	}
	/** 在充电        **/
	public CsHistoryState cshsCharging(String cshsCharging){
		this.cshsCharging = cshsCharging;
		this.setSeted(F.cshsCharging);
		return this;
	}
	/** 油里程        **/
	public CsHistoryState cshsFuelMileage(String cshsFuelMileage){
		this.cshsFuelMileage = cshsFuelMileage;
		this.setSeted(F.cshsFuelMileage);
		return this;
	}
	/** 电量程        **/
	public CsHistoryState cshsElectricMileage(String cshsElectricMileage){
		this.cshsElectricMileage = cshsElectricMileage;
		this.setSeted(F.cshsElectricMileage);
		return this;
	}
	/** 续航里程        **/
	public CsHistoryState cshsEndurance(String cshsEndurance){
		this.cshsEndurance = cshsEndurance;
		this.setSeted(F.cshsEndurance);
		return this;
	}
	/** 温度        **/
	public CsHistoryState cshsTemperature(String cshsTemperature){
		this.cshsTemperature = cshsTemperature;
		this.setSeted(F.cshsTemperature);
		return this;
	}
	/** 信号强度        **/
	public CsHistoryState cshsCsq(String cshsCsq){
		this.cshsCsq = cshsCsq;
		this.setSeted(F.cshsCsq);
		return this;
	}
	/** 功耗        **/
	public CsHistoryState cshsPowerConsumption(String cshsPowerConsumption){
		this.cshsPowerConsumption = cshsPowerConsumption;
		this.setSeted(F.cshsPowerConsumption);
		return this;
	}
	/** 经度        **/
	public CsHistoryState cshsLongitude(String cshsLongitude){
		this.cshsLongitude = cshsLongitude;
		this.setSeted(F.cshsLongitude);
		return this;
	}
	/** 纬度        **/
	public CsHistoryState cshsLatitude(String cshsLatitude){
		this.cshsLatitude = cshsLatitude;
		this.setSeted(F.cshsLatitude);
		return this;
	}
	/** 当前订单  [CsOrder]      **/
	public CsHistoryState cshsOrder(Long cshsOrder){
		this.cshsOrder = cshsOrder;
		this.setSeted(F.cshsOrder);
		return this;
	}
	/** 接口数据        **/
	public CsHistoryState cshsMoData(String cshsMoData){
		this.cshsMoData = cshsMoData;
		this.setSeted(F.cshsMoData);
		return this;
	}
	
	
	//克隆对象
	public CsHistoryState clone(){
		CsHistoryState clone = new CsHistoryState();
		clone.cshsHost=this.cshsHost;
		clone.cshsNumber=this.cshsNumber;
		clone.cshsAddTime=this.cshsAddTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取车辆历史状态
	 * @param id
	 * @return
	 */
	public static @api CsHistoryState get(Long id){		
		return getCsHistoryStateById(id);
	}
	/**
	 * 获取所有车辆历史状态
	 * @return
	 */
	public static @api List<CsHistoryState> list(Map params,Integer size){
		return getCsHistoryStateList(params,size);
	}
	/**
	 * 获取车辆历史状态分页
	 * @return
	 */
	public static @api Page<CsHistoryState> page(int page,int size,Map params){
		return getCsHistoryStatePage(page, size , params);
	}
	/**
	 * 根据查询条件取车辆历史状态
	 * @param params
	 * @return
	 */
	public static @api CsHistoryState Get(Map params){
		return getCsHistoryState(params);
	}
	/**
	 * 获取车辆历史状态数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsHistoryStateCount(params);
	}
	/**
	 * 获取车辆历史状态数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsHistoryStateEval(eval,params);
	}
	
	/**
	 * 根据ID取车辆历史状态
	 * @param id
	 * @return
	 */
	public static @api CsHistoryState getCsHistoryStateById(Long id){		
		CsHistoryState csHistoryState = (CsHistoryState) $.GetRequest("CsHistoryState$"+id);
		if(csHistoryState!=null)
			return csHistoryState;
		com.ccclubs.service.admin.ICsHistoryStateService csHistoryStateService = $.GetSpringBean("csHistoryStateService");		
		return csHistoryStateService.getCsHistoryStateById(id);
	}
	
	
	/**
	 * 根据ID取车辆历史状态的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		//当前对象不支持键值缓存
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCshsNumber$();
		if(!"CshsNumber".equals("CshsNumber"))
			keyValue+="("+this.getCshsNumber$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有车辆历史状态
	 * @return
	 */
	public static @api List<CsHistoryState> getCsHistoryStateList(Map params,Integer size){
		com.ccclubs.service.admin.ICsHistoryStateService csHistoryStateService = $.GetSpringBean("csHistoryStateService");
		return csHistoryStateService.getCsHistoryStateList(params, size);
	}
	
	/**
	 * 获取车辆历史状态分页
	 * @return
	 */
	public static @api Page<CsHistoryState> getCsHistoryStatePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsHistoryStateService csHistoryStateService = $.GetSpringBean("csHistoryStateService");
		return csHistoryStateService.getCsHistoryStatePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取车辆历史状态
	 * @param params
	 * @return
	 */
	public static @api CsHistoryState getCsHistoryState(Map params){
		com.ccclubs.service.admin.ICsHistoryStateService csHistoryStateService = $.GetSpringBean("csHistoryStateService");
		return csHistoryStateService.getCsHistoryState(params);
	}
	
	/**
	 * 获取车辆历史状态数
	 * @return
	 */
	public static @api Long getCsHistoryStateCount(Map params){
		com.ccclubs.service.admin.ICsHistoryStateService csHistoryStateService = $.GetSpringBean("csHistoryStateService");
		return csHistoryStateService.getCsHistoryStateCount(params);
	}
		
		
	/**
	 * 获取车辆历史状态自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsHistoryStateEval(String eval,Map params){
		com.ccclubs.service.admin.ICsHistoryStateService csHistoryStateService = $.GetSpringBean("csHistoryStateService");
		return csHistoryStateService.getCsHistoryStateEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsHistoryState(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsHistoryStateService csHistoryStateService = $.GetSpringBean("csHistoryStateService");
		csHistoryStateService.updateCsHistoryStateByConfirm(set, where);
	}
	
	
	/**
	 * 保存车辆历史状态对象
	 * @param params
	 * @return
	 */
	public CsHistoryState save(){
		com.ccclubs.service.admin.ICsHistoryStateService csHistoryStateService = $.GetSpringBean("csHistoryStateService");
		if(this.getCshsId()!=null)
			csHistoryStateService.updateCsHistoryState(this);
		else
			return csHistoryStateService.saveCsHistoryState(this);
		return this;
	}
	
	
	/**
	 * 更新车辆历史状态对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsHistoryStateService csHistoryStateService = $.GetSpringBean("csHistoryStateService");
		csHistoryStateService.updateCsHistoryState$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsHistoryStateService csHistoryStateService = $.GetSpringBean("csHistoryStateService");
			csHistoryStateService.deleteCsHistoryStateById(this.getCshsId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsHistoryStateService csHistoryStateService = $.GetSpringBean("csHistoryStateService");
		return csHistoryStateService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCshsId(){
		return this.cshsId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCshsId$(){
		String strValue="";
		 strValue=$.str(this.getCshsId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCshsId(Long cshsId){
		this.cshsId = cshsId;
		this.setSeted(F.cshsId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCshsHost(){
		return this.cshsHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCshsHost$(){
		String strValue="";
		if(this.getCshsHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCshsHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCshsHost(Long cshsHost){
		this.cshsHost = cshsHost;
		this.setSeted(F.cshsHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cshsHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCshsHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCshsHost()!=null){
 			srvHost = SrvHost.get(this.getCshsHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCshsHost(), srvHost);
	 	return srvHost;
	}
	/*******************************车牌号**********************************/	
	/**
	* 车牌号 [非空]      ${basePath}${proname}/object/car_numbers.do
	**/
	public String getCshsNumber(){
		return this.cshsNumber;
	}
	/**
	* 获取车牌号格式化(toString)
	**/
	public String getCshsNumber$(){
		String strValue="";
		 strValue=$.str(this.getCshsNumber());	
	 	 return strValue;
	}
	/**
	* 车牌号 [非空]      ${basePath}${proname}/object/car_numbers.do
	**/
	public void setCshsNumber(String cshsNumber){
		this.cshsNumber = cshsNumber;
		this.setSeted(F.cshsNumber);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCshsAddTime(){
		return this.cshsAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCshsAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCshsAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCshsAddTime(Date cshsAddTime){
		this.cshsAddTime = cshsAddTime;
		this.setSeted(F.cshsAddTime);
	}
	/*******************************下位机时间**********************************/	
	/**
	* 下位机时间       
	**/
	public Date getCshsCurrentTime(){
		return this.cshsCurrentTime;
	}
	/**
	* 获取下位机时间格式化(toString)
	**/
	public String getCshsCurrentTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCshsCurrentTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 下位机时间       
	**/
	public void setCshsCurrentTime(Date cshsCurrentTime){
		this.cshsCurrentTime = cshsCurrentTime;
		this.setSeted(F.cshsCurrentTime);
	}
	/*******************************租赁状态**********************************/	
	/**
	* 租赁状态       
	**/
	public String getCshsRented(){
		return this.cshsRented;
	}
	/**
	* 获取租赁状态格式化(toString)
	**/
	public String getCshsRented$(){
		String strValue="";
		 strValue=$.str(this.getCshsRented());
	 	 return strValue;
	}
	/**
	* 租赁状态       
	**/
	public void setCshsRented(String cshsRented){
		this.cshsRented = cshsRented;
		this.setSeted(F.cshsRented);
	}
	/*******************************有报警**********************************/	
	/**
	* 有报警       
	**/
	public String getCshsWarn(){
		return this.cshsWarn;
	}
	/**
	* 获取有报警格式化(toString)
	**/
	public String getCshsWarn$(){
		String strValue="";
		 strValue=$.str(this.getCshsWarn());
	 	 return strValue;
	}
	/**
	* 有报警       
	**/
	public void setCshsWarn(String cshsWarn){
		this.cshsWarn = cshsWarn;
		this.setSeted(F.cshsWarn);
	}
	/*******************************RFID卡号**********************************/	
	/**
	* RFID卡号       
	**/
	public String getCshsRfid(){
		return this.cshsRfid;
	}
	/**
	* 获取RFID卡号格式化(toString)
	**/
	public String getCshsRfid$(){
		String strValue="";
		 strValue=$.str(this.getCshsRfid());
	 	 return strValue;
	}
	/**
	* RFID卡号       
	**/
	public void setCshsRfid(String cshsRfid){
		this.cshsRfid = cshsRfid;
		this.setSeted(F.cshsRfid);
	}
	/*******************************用户RFID**********************************/	
	/**
	* 用户RFID       
	**/
	public String getCshsRfidDte(){
		return this.cshsRfidDte;
	}
	/**
	* 获取用户RFID格式化(toString)
	**/
	public String getCshsRfidDte$(){
		String strValue="";
		 strValue=$.str(this.getCshsRfidDte());
	 	 return strValue;
	}
	/**
	* 用户RFID       
	**/
	public void setCshsRfidDte(String cshsRfidDte){
		this.cshsRfidDte = cshsRfidDte;
		this.setSeted(F.cshsRfidDte);
	}
	/*******************************OBD里程**********************************/	
	/**
	* OBD里程       
	**/
	public String getCshsObdMile(){
		return this.cshsObdMile;
	}
	/**
	* 获取OBD里程格式化(toString)
	**/
	public String getCshsObdMile$(){
		String strValue="";
		 strValue=$.str(this.getCshsObdMile());
	 	 return strValue;
	}
	/**
	* OBD里程       
	**/
	public void setCshsObdMile(String cshsObdMile){
		this.cshsObdMile = cshsObdMile;
		this.setSeted(F.cshsObdMile);
	}
	/*******************************发动机温度**********************************/	
	/**
	* 发动机温度       
	**/
	public String getCshsEngineT(){
		return this.cshsEngineT;
	}
	/**
	* 获取发动机温度格式化(toString)
	**/
	public String getCshsEngineT$(){
		String strValue="";
		 strValue=$.str(this.getCshsEngineT());
	 	 return strValue;
	}
	/**
	* 发动机温度       
	**/
	public void setCshsEngineT(String cshsEngineT){
		this.cshsEngineT = cshsEngineT;
		this.setSeted(F.cshsEngineT);
	}
	/*******************************总里程**********************************/	
	/**
	* 总里程       
	**/
	public String getCshsMileage(){
		return this.cshsMileage;
	}
	/**
	* 获取总里程格式化(toString)
	**/
	public String getCshsMileage$(){
		String strValue="";
		 strValue=$.str(this.getCshsMileage());
	 	 return strValue;
	}
	/**
	* 总里程       
	**/
	public void setCshsMileage(String cshsMileage){
		this.cshsMileage = cshsMileage;
		this.setSeted(F.cshsMileage);
	}
	/*******************************车速**********************************/	
	/**
	* 车速       
	**/
	public String getCshsSpeed(){
		return this.cshsSpeed;
	}
	/**
	* 获取车速格式化(toString)
	**/
	public String getCshsSpeed$(){
		String strValue="";
		 strValue=$.str(this.getCshsSpeed());
	 	 return strValue;
	}
	/**
	* 车速       
	**/
	public void setCshsSpeed(String cshsSpeed){
		this.cshsSpeed = cshsSpeed;
		this.setSeted(F.cshsSpeed);
	}
	/*******************************转速**********************************/	
	/**
	* 转速       
	**/
	public String getCshsMotor(){
		return this.cshsMotor;
	}
	/**
	* 获取转速格式化(toString)
	**/
	public String getCshsMotor$(){
		String strValue="";
		 strValue=$.str(this.getCshsMotor());
	 	 return strValue;
	}
	/**
	* 转速       
	**/
	public void setCshsMotor(String cshsMotor){
		this.cshsMotor = cshsMotor;
		this.setSeted(F.cshsMotor);
	}
	/*******************************燃油量**********************************/	
	/**
	* 燃油量       
	**/
	public String getCshsOil(){
		return this.cshsOil;
	}
	/**
	* 获取燃油量格式化(toString)
	**/
	public String getCshsOil$(){
		String strValue="";
		 strValue=$.str(this.getCshsOil());
	 	 return strValue;
	}
	/**
	* 燃油量       
	**/
	public void setCshsOil(String cshsOil){
		this.cshsOil = cshsOil;
		this.setSeted(F.cshsOil);
	}
	/*******************************蓄电量**********************************/	
	/**
	* 蓄电量       
	**/
	public String getCshsPower(){
		return this.cshsPower;
	}
	/**
	* 获取蓄电量格式化(toString)
	**/
	public String getCshsPower$(){
		String strValue="";
		 strValue=$.str(this.getCshsPower());
	 	 return strValue;
	}
	/**
	* 蓄电量       
	**/
	public void setCshsPower(String cshsPower){
		this.cshsPower = cshsPower;
		this.setSeted(F.cshsPower);
	}
	/*******************************动力电池电量**********************************/	
	/**
	* 动力电池电量       
	**/
	public String getCshsEvBattery(){
		return this.cshsEvBattery;
	}
	/**
	* 获取动力电池电量格式化(toString)
	**/
	public String getCshsEvBattery$(){
		String strValue="";
		 strValue=$.str(this.getCshsEvBattery());
	 	 return strValue;
	}
	/**
	* 动力电池电量       
	**/
	public void setCshsEvBattery(String cshsEvBattery){
		this.cshsEvBattery = cshsEvBattery;
		this.setSeted(F.cshsEvBattery);
	}
	/*******************************在充电**********************************/	
	/**
	* 在充电       
	**/
	public String getCshsCharging(){
		return this.cshsCharging;
	}
	/**
	* 获取在充电格式化(toString)
	**/
	public String getCshsCharging$(){
		String strValue="";
		 strValue=$.str(this.getCshsCharging());
	 	 return strValue;
	}
	/**
	* 在充电       
	**/
	public void setCshsCharging(String cshsCharging){
		this.cshsCharging = cshsCharging;
		this.setSeted(F.cshsCharging);
	}
	/*******************************油里程**********************************/	
	/**
	* 油里程       
	**/
	public String getCshsFuelMileage(){
		return this.cshsFuelMileage;
	}
	/**
	* 获取油里程格式化(toString)
	**/
	public String getCshsFuelMileage$(){
		String strValue="";
		 strValue=$.str(this.getCshsFuelMileage());
	 	 return strValue;
	}
	/**
	* 油里程       
	**/
	public void setCshsFuelMileage(String cshsFuelMileage){
		this.cshsFuelMileage = cshsFuelMileage;
		this.setSeted(F.cshsFuelMileage);
	}
	/*******************************电量程**********************************/	
	/**
	* 电量程       
	**/
	public String getCshsElectricMileage(){
		return this.cshsElectricMileage;
	}
	/**
	* 获取电量程格式化(toString)
	**/
	public String getCshsElectricMileage$(){
		String strValue="";
		 strValue=$.str(this.getCshsElectricMileage());
	 	 return strValue;
	}
	/**
	* 电量程       
	**/
	public void setCshsElectricMileage(String cshsElectricMileage){
		this.cshsElectricMileage = cshsElectricMileage;
		this.setSeted(F.cshsElectricMileage);
	}
	/*******************************续航里程**********************************/	
	/**
	* 续航里程       
	**/
	public String getCshsEndurance(){
		return this.cshsEndurance;
	}
	/**
	* 获取续航里程格式化(toString)
	**/
	public String getCshsEndurance$(){
		String strValue="";
		 strValue=$.str(this.getCshsEndurance());
	 	 return strValue;
	}
	/**
	* 续航里程       
	**/
	public void setCshsEndurance(String cshsEndurance){
		this.cshsEndurance = cshsEndurance;
		this.setSeted(F.cshsEndurance);
	}
	/*******************************温度**********************************/	
	/**
	* 温度       
	**/
	public String getCshsTemperature(){
		return this.cshsTemperature;
	}
	/**
	* 获取温度格式化(toString)
	**/
	public String getCshsTemperature$(){
		String strValue="";
		 strValue=$.str(this.getCshsTemperature());
	 	 return strValue;
	}
	/**
	* 温度       
	**/
	public void setCshsTemperature(String cshsTemperature){
		this.cshsTemperature = cshsTemperature;
		this.setSeted(F.cshsTemperature);
	}
	/*******************************信号强度**********************************/	
	/**
	* 信号强度       
	**/
	public String getCshsCsq(){
		return this.cshsCsq;
	}
	/**
	* 获取信号强度格式化(toString)
	**/
	public String getCshsCsq$(){
		String strValue="";
		 strValue=$.str(this.getCshsCsq());
	 	 return strValue;
	}
	/**
	* 信号强度       
	**/
	public void setCshsCsq(String cshsCsq){
		this.cshsCsq = cshsCsq;
		this.setSeted(F.cshsCsq);
	}
	/*******************************功耗**********************************/	
	/**
	* 功耗       
	**/
	public String getCshsPowerConsumption(){
		return this.cshsPowerConsumption;
	}
	/**
	* 获取功耗格式化(toString)
	**/
	public String getCshsPowerConsumption$(){
		String strValue="";
		 strValue=$.str(this.getCshsPowerConsumption());
	 	 return strValue;
	}
	/**
	* 功耗       
	**/
	public void setCshsPowerConsumption(String cshsPowerConsumption){
		this.cshsPowerConsumption = cshsPowerConsumption;
		this.setSeted(F.cshsPowerConsumption);
	}
	/*******************************经度**********************************/	
	/**
	* 经度       
	**/
	public String getCshsLongitude(){
		return this.cshsLongitude;
	}
	/**
	* 获取经度格式化(toString)
	**/
	public String getCshsLongitude$(){
		String strValue="";
		 strValue=$.str(this.getCshsLongitude());
	 	 return strValue;
	}
	/**
	* 经度       
	**/
	public void setCshsLongitude(String cshsLongitude){
		this.cshsLongitude = cshsLongitude;
		this.setSeted(F.cshsLongitude);
	}
	/*******************************纬度**********************************/	
	/**
	* 纬度       
	**/
	public String getCshsLatitude(){
		return this.cshsLatitude;
	}
	/**
	* 获取纬度格式化(toString)
	**/
	public String getCshsLatitude$(){
		String strValue="";
		 strValue=$.str(this.getCshsLatitude());
	 	 return strValue;
	}
	/**
	* 纬度       
	**/
	public void setCshsLatitude(String cshsLatitude){
		this.cshsLatitude = cshsLatitude;
		this.setSeted(F.cshsLatitude);
	}
	/*******************************当前订单**********************************/	
	/**
	* 当前订单  [CsOrder]     
	**/
	public Long getCshsOrder(){
		return this.cshsOrder;
	}
	/**
	* 获取当前订单格式化(toString)
	**/
	public String getCshsOrder$(){
		String strValue="";
		if(this.getCshsOrder()!=null){
				strValue+=$.str(CsOrder.getKeyValue(this.getCshsOrder()));
		}			
	 	 return strValue;
	}
	/**
	* 当前订单  [CsOrder]     
	**/
	public void setCshsOrder(Long cshsOrder){
		this.cshsOrder = cshsOrder;
		this.setSeted(F.cshsOrder);
	}
	/**
	* 获取关联对象[系统订单]
	**/	 			
 	public CsOrder get$cshsOrder(){
 		com.ccclubs.model.CsOrder csOrder = (com.ccclubs.model.CsOrder) $.GetRequest("CsOrder$"+this.getCshsOrder());
 		if(csOrder!=null)
			return csOrder;
		if(this.getCshsOrder()!=null){
 			csOrder = CsOrder.get(this.getCshsOrder());
 		}
 		$.SetRequest("CsOrder$"+this.getCshsOrder(), csOrder);
	 	return csOrder;
	}
	/*******************************接口数据**********************************/	
	/**
	* 接口数据       
	**/
	public String getCshsMoData(){
		return this.cshsMoData;
	}
	/**
	* 获取接口数据格式化(toString)
	**/
	public String getCshsMoData$(){
		String strValue="";
		 strValue=$.str(this.getCshsMoData());
	 	 return strValue;
	}
	/**
	* 接口数据       
	**/
	public void setCshsMoData(String cshsMoData){
		this.cshsMoData = cshsMoData;
		this.setSeted(F.cshsMoData);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsHistoryState.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsHistoryState.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsHistoryState.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsHistoryState.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsHistoryState.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsHistoryState.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsHistoryState.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsHistoryState.this);
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
		public M cshsId(Object cshsId){this.put("cshsId", cshsId);return this;};
	 	/** and cshs_id is null */
 		public M cshsIdNull(){if(this.get("cshsIdNot")==null)this.put("cshsIdNot", "");this.put("cshsId", null);return this;};
 		/** not .... */
 		public M cshsIdNot(){this.put("cshsIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M cshsHost(Object cshsHost){this.put("cshsHost", cshsHost);return this;};
	 	/** and cshs_host is null */
 		public M cshsHostNull(){if(this.get("cshsHostNot")==null)this.put("cshsHostNot", "");this.put("cshsHost", null);return this;};
 		/** not .... */
 		public M cshsHostNot(){this.put("cshsHostNot", "not");return this;};
		/** 车牌号 [非空]      ${basePath}${proname}/object/car_numbers.do **/
		public M cshsNumber(Object cshsNumber){this.put("cshsNumber", cshsNumber);return this;};
	 	/** and cshs_number is null */
 		public M cshsNumberNull(){if(this.get("cshsNumberNot")==null)this.put("cshsNumberNot", "");this.put("cshsNumber", null);return this;};
 		/** not .... */
 		public M cshsNumberNot(){this.put("cshsNumberNot", "not");return this;};
		/** 添加时间 [非空]       **/
		public M cshsAddTime(Object cshsAddTime){this.put("cshsAddTime", cshsAddTime);return this;};
	 	/** and cshs_add_time is null */
 		public M cshsAddTimeNull(){if(this.get("cshsAddTimeNot")==null)this.put("cshsAddTimeNot", "");this.put("cshsAddTime", null);return this;};
 		/** not .... */
 		public M cshsAddTimeNot(){this.put("cshsAddTimeNot", "not");return this;};
 		/** and cshs_add_time >= ? */
 		public M cshsAddTimeStart(Object start){this.put("cshsAddTimeStart", start);return this;};			
 		/** and cshs_add_time <= ? */
 		public M cshsAddTimeEnd(Object end){this.put("cshsAddTimeEnd", end);return this;};
		/** 下位机时间        **/
		public M cshsCurrentTime(Object cshsCurrentTime){this.put("cshsCurrentTime", cshsCurrentTime);return this;};
	 	/** and cshs_current_time is null */
 		public M cshsCurrentTimeNull(){if(this.get("cshsCurrentTimeNot")==null)this.put("cshsCurrentTimeNot", "");this.put("cshsCurrentTime", null);return this;};
 		/** not .... */
 		public M cshsCurrentTimeNot(){this.put("cshsCurrentTimeNot", "not");return this;};
 		/** and cshs_current_time >= ? */
 		public M cshsCurrentTimeStart(Object start){this.put("cshsCurrentTimeStart", start);return this;};			
 		/** and cshs_current_time <= ? */
 		public M cshsCurrentTimeEnd(Object end){this.put("cshsCurrentTimeEnd", end);return this;};
		/** 租赁状态        **/
		public M cshsRented(Object cshsRented){this.put("cshsRented", cshsRented);return this;};
	 	/** and cshs_rented is null */
 		public M cshsRentedNull(){if(this.get("cshsRentedNot")==null)this.put("cshsRentedNot", "");this.put("cshsRented", null);return this;};
 		/** not .... */
 		public M cshsRentedNot(){this.put("cshsRentedNot", "not");return this;};
		/** 有报警        **/
		public M cshsWarn(Object cshsWarn){this.put("cshsWarn", cshsWarn);return this;};
	 	/** and cshs_warn is null */
 		public M cshsWarnNull(){if(this.get("cshsWarnNot")==null)this.put("cshsWarnNot", "");this.put("cshsWarn", null);return this;};
 		/** not .... */
 		public M cshsWarnNot(){this.put("cshsWarnNot", "not");return this;};
		/** RFID卡号        **/
		public M cshsRfid(Object cshsRfid){this.put("cshsRfid", cshsRfid);return this;};
	 	/** and cshs_rfid is null */
 		public M cshsRfidNull(){if(this.get("cshsRfidNot")==null)this.put("cshsRfidNot", "");this.put("cshsRfid", null);return this;};
 		/** not .... */
 		public M cshsRfidNot(){this.put("cshsRfidNot", "not");return this;};
		/** 用户RFID        **/
		public M cshsRfidDte(Object cshsRfidDte){this.put("cshsRfidDte", cshsRfidDte);return this;};
	 	/** and cshs_rfid_dte is null */
 		public M cshsRfidDteNull(){if(this.get("cshsRfidDteNot")==null)this.put("cshsRfidDteNot", "");this.put("cshsRfidDte", null);return this;};
 		/** not .... */
 		public M cshsRfidDteNot(){this.put("cshsRfidDteNot", "not");return this;};
		/** OBD里程        **/
		public M cshsObdMile(Object cshsObdMile){this.put("cshsObdMile", cshsObdMile);return this;};
	 	/** and cshs_obd_mile is null */
 		public M cshsObdMileNull(){if(this.get("cshsObdMileNot")==null)this.put("cshsObdMileNot", "");this.put("cshsObdMile", null);return this;};
 		/** not .... */
 		public M cshsObdMileNot(){this.put("cshsObdMileNot", "not");return this;};
		/** 发动机温度        **/
		public M cshsEngineT(Object cshsEngineT){this.put("cshsEngineT", cshsEngineT);return this;};
	 	/** and cshs_engine_t is null */
 		public M cshsEngineTNull(){if(this.get("cshsEngineTNot")==null)this.put("cshsEngineTNot", "");this.put("cshsEngineT", null);return this;};
 		/** not .... */
 		public M cshsEngineTNot(){this.put("cshsEngineTNot", "not");return this;};
		/** 总里程        **/
		public M cshsMileage(Object cshsMileage){this.put("cshsMileage", cshsMileage);return this;};
	 	/** and cshs_mileage is null */
 		public M cshsMileageNull(){if(this.get("cshsMileageNot")==null)this.put("cshsMileageNot", "");this.put("cshsMileage", null);return this;};
 		/** not .... */
 		public M cshsMileageNot(){this.put("cshsMileageNot", "not");return this;};
		/** 车速        **/
		public M cshsSpeed(Object cshsSpeed){this.put("cshsSpeed", cshsSpeed);return this;};
	 	/** and cshs_speed is null */
 		public M cshsSpeedNull(){if(this.get("cshsSpeedNot")==null)this.put("cshsSpeedNot", "");this.put("cshsSpeed", null);return this;};
 		/** not .... */
 		public M cshsSpeedNot(){this.put("cshsSpeedNot", "not");return this;};
		/** 转速        **/
		public M cshsMotor(Object cshsMotor){this.put("cshsMotor", cshsMotor);return this;};
	 	/** and cshs_motor is null */
 		public M cshsMotorNull(){if(this.get("cshsMotorNot")==null)this.put("cshsMotorNot", "");this.put("cshsMotor", null);return this;};
 		/** not .... */
 		public M cshsMotorNot(){this.put("cshsMotorNot", "not");return this;};
		/** 燃油量        **/
		public M cshsOil(Object cshsOil){this.put("cshsOil", cshsOil);return this;};
	 	/** and cshs_oil is null */
 		public M cshsOilNull(){if(this.get("cshsOilNot")==null)this.put("cshsOilNot", "");this.put("cshsOil", null);return this;};
 		/** not .... */
 		public M cshsOilNot(){this.put("cshsOilNot", "not");return this;};
		/** 蓄电量        **/
		public M cshsPower(Object cshsPower){this.put("cshsPower", cshsPower);return this;};
	 	/** and cshs_power is null */
 		public M cshsPowerNull(){if(this.get("cshsPowerNot")==null)this.put("cshsPowerNot", "");this.put("cshsPower", null);return this;};
 		/** not .... */
 		public M cshsPowerNot(){this.put("cshsPowerNot", "not");return this;};
		/** 动力电池电量        **/
		public M cshsEvBattery(Object cshsEvBattery){this.put("cshsEvBattery", cshsEvBattery);return this;};
	 	/** and cshs_ev_battery is null */
 		public M cshsEvBatteryNull(){if(this.get("cshsEvBatteryNot")==null)this.put("cshsEvBatteryNot", "");this.put("cshsEvBattery", null);return this;};
 		/** not .... */
 		public M cshsEvBatteryNot(){this.put("cshsEvBatteryNot", "not");return this;};
		/** 在充电        **/
		public M cshsCharging(Object cshsCharging){this.put("cshsCharging", cshsCharging);return this;};
	 	/** and cshs_charging is null */
 		public M cshsChargingNull(){if(this.get("cshsChargingNot")==null)this.put("cshsChargingNot", "");this.put("cshsCharging", null);return this;};
 		/** not .... */
 		public M cshsChargingNot(){this.put("cshsChargingNot", "not");return this;};
		/** 油里程        **/
		public M cshsFuelMileage(Object cshsFuelMileage){this.put("cshsFuelMileage", cshsFuelMileage);return this;};
	 	/** and cshs_fuel_mileage is null */
 		public M cshsFuelMileageNull(){if(this.get("cshsFuelMileageNot")==null)this.put("cshsFuelMileageNot", "");this.put("cshsFuelMileage", null);return this;};
 		/** not .... */
 		public M cshsFuelMileageNot(){this.put("cshsFuelMileageNot", "not");return this;};
		/** 电量程        **/
		public M cshsElectricMileage(Object cshsElectricMileage){this.put("cshsElectricMileage", cshsElectricMileage);return this;};
	 	/** and cshs_electric_mileage is null */
 		public M cshsElectricMileageNull(){if(this.get("cshsElectricMileageNot")==null)this.put("cshsElectricMileageNot", "");this.put("cshsElectricMileage", null);return this;};
 		/** not .... */
 		public M cshsElectricMileageNot(){this.put("cshsElectricMileageNot", "not");return this;};
		/** 续航里程        **/
		public M cshsEndurance(Object cshsEndurance){this.put("cshsEndurance", cshsEndurance);return this;};
	 	/** and cshs_endurance is null */
 		public M cshsEnduranceNull(){if(this.get("cshsEnduranceNot")==null)this.put("cshsEnduranceNot", "");this.put("cshsEndurance", null);return this;};
 		/** not .... */
 		public M cshsEnduranceNot(){this.put("cshsEnduranceNot", "not");return this;};
		/** 温度        **/
		public M cshsTemperature(Object cshsTemperature){this.put("cshsTemperature", cshsTemperature);return this;};
	 	/** and cshs_temperature is null */
 		public M cshsTemperatureNull(){if(this.get("cshsTemperatureNot")==null)this.put("cshsTemperatureNot", "");this.put("cshsTemperature", null);return this;};
 		/** not .... */
 		public M cshsTemperatureNot(){this.put("cshsTemperatureNot", "not");return this;};
		/** 信号强度        **/
		public M cshsCsq(Object cshsCsq){this.put("cshsCsq", cshsCsq);return this;};
	 	/** and cshs_csq is null */
 		public M cshsCsqNull(){if(this.get("cshsCsqNot")==null)this.put("cshsCsqNot", "");this.put("cshsCsq", null);return this;};
 		/** not .... */
 		public M cshsCsqNot(){this.put("cshsCsqNot", "not");return this;};
		/** 功耗        **/
		public M cshsPowerConsumption(Object cshsPowerConsumption){this.put("cshsPowerConsumption", cshsPowerConsumption);return this;};
	 	/** and cshs_power_consumption is null */
 		public M cshsPowerConsumptionNull(){if(this.get("cshsPowerConsumptionNot")==null)this.put("cshsPowerConsumptionNot", "");this.put("cshsPowerConsumption", null);return this;};
 		/** not .... */
 		public M cshsPowerConsumptionNot(){this.put("cshsPowerConsumptionNot", "not");return this;};
		/** 经度        **/
		public M cshsLongitude(Object cshsLongitude){this.put("cshsLongitude", cshsLongitude);return this;};
	 	/** and cshs_longitude is null */
 		public M cshsLongitudeNull(){if(this.get("cshsLongitudeNot")==null)this.put("cshsLongitudeNot", "");this.put("cshsLongitude", null);return this;};
 		/** not .... */
 		public M cshsLongitudeNot(){this.put("cshsLongitudeNot", "not");return this;};
		/** 纬度        **/
		public M cshsLatitude(Object cshsLatitude){this.put("cshsLatitude", cshsLatitude);return this;};
	 	/** and cshs_latitude is null */
 		public M cshsLatitudeNull(){if(this.get("cshsLatitudeNot")==null)this.put("cshsLatitudeNot", "");this.put("cshsLatitude", null);return this;};
 		/** not .... */
 		public M cshsLatitudeNot(){this.put("cshsLatitudeNot", "not");return this;};
		/** 当前订单  [CsOrder]      **/
		public M cshsOrder(Object cshsOrder){this.put("cshsOrder", cshsOrder);return this;};
	 	/** and cshs_order is null */
 		public M cshsOrderNull(){if(this.get("cshsOrderNot")==null)this.put("cshsOrderNot", "");this.put("cshsOrder", null);return this;};
 		/** not .... */
 		public M cshsOrderNot(){this.put("cshsOrderNot", "not");return this;};
		/** and cshs_order >= ? */
		public M cshsOrderMin(Object min){this.put("cshsOrderMin", min);return this;};
		/** and cshs_order <= ? */
		public M cshsOrderMax(Object max){this.put("cshsOrderMax", max);return this;};
		/** 接口数据        **/
		public M cshsMoData(Object cshsMoData){this.put("cshsMoData", cshsMoData);return this;};
	 	/** and cshs_mo_data is null */
 		public M cshsMoDataNull(){if(this.get("cshsMoDataNot")==null)this.put("cshsMoDataNot", "");this.put("cshsMoData", null);return this;};
 		/** not .... */
 		public M cshsMoDataNot(){this.put("cshsMoDataNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有车辆历史状态 **/
		public @api List<CsHistoryState> list(Integer size){
			return getCsHistoryStateList(this,size);
		}
		/** 获取车辆历史状态分页 **/
		public @api Page<CsHistoryState> page(int page,int size){
			return getCsHistoryStatePage(page, size , this);
		}
		/** 根据查询条件取车辆历史状态 **/
		public @api CsHistoryState get(){
			return getCsHistoryState(this);
		}
		/** 获取车辆历史状态数 **/
		public @api Long count(){
			return getCsHistoryStateCount(this);
		}
		/** 获取车辆历史状态表达式 **/
		public @api <T> T eval(String strEval){
			return getCsHistoryStateEval(strEval,this);
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
			updateCsHistoryState(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cshsId="cshsId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cshsHost="cshsHost";
		/** 车牌号 [非空]      ${basePath}${proname}/object/car_numbers.do **/
		public final static @type(String.class)  String cshsNumber="cshsNumber";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cshsAddTime="cshsAddTime";
	 	/** and cshs_add_time >= ? */
 		public final static @type(Date.class) String cshsAddTimeStart="cshsAddTimeStart";
 		/** and cshs_add_time <= ? */
 		public final static @type(Date.class) String cshsAddTimeEnd="cshsAddTimeEnd";
		/** 下位机时间        **/
		public final static @type(Date.class)  String cshsCurrentTime="cshsCurrentTime";
	 	/** and cshs_current_time >= ? */
 		public final static @type(Date.class) String cshsCurrentTimeStart="cshsCurrentTimeStart";
 		/** and cshs_current_time <= ? */
 		public final static @type(Date.class) String cshsCurrentTimeEnd="cshsCurrentTimeEnd";
		/** 租赁状态        **/
		public final static @type(String.class) @like String cshsRented="cshsRented";
		/** 有报警        **/
		public final static @type(String.class) @like String cshsWarn="cshsWarn";
		/** RFID卡号        **/
		public final static @type(String.class) @like String cshsRfid="cshsRfid";
		/** 用户RFID        **/
		public final static @type(String.class) @like String cshsRfidDte="cshsRfidDte";
		/** OBD里程        **/
		public final static @type(String.class) @like String cshsObdMile="cshsObdMile";
		/** 发动机温度        **/
		public final static @type(String.class) @like String cshsEngineT="cshsEngineT";
		/** 总里程        **/
		public final static @type(String.class) @like String cshsMileage="cshsMileage";
		/** 车速        **/
		public final static @type(String.class) @like String cshsSpeed="cshsSpeed";
		/** 转速        **/
		public final static @type(String.class) @like String cshsMotor="cshsMotor";
		/** 燃油量        **/
		public final static @type(String.class) @like String cshsOil="cshsOil";
		/** 蓄电量        **/
		public final static @type(String.class) @like String cshsPower="cshsPower";
		/** 动力电池电量        **/
		public final static @type(String.class) @like String cshsEvBattery="cshsEvBattery";
		/** 在充电        **/
		public final static @type(String.class) @like String cshsCharging="cshsCharging";
		/** 油里程        **/
		public final static @type(String.class) @like String cshsFuelMileage="cshsFuelMileage";
		/** 电量程        **/
		public final static @type(String.class) @like String cshsElectricMileage="cshsElectricMileage";
		/** 续航里程        **/
		public final static @type(String.class) @like String cshsEndurance="cshsEndurance";
		/** 温度        **/
		public final static @type(String.class) @like String cshsTemperature="cshsTemperature";
		/** 信号强度        **/
		public final static @type(String.class) @like String cshsCsq="cshsCsq";
		/** 功耗        **/
		public final static @type(String.class) @like String cshsPowerConsumption="cshsPowerConsumption";
		/** 经度        **/
		public final static @type(String.class) @like String cshsLongitude="cshsLongitude";
		/** 纬度        **/
		public final static @type(String.class) @like String cshsLatitude="cshsLatitude";
		/** 当前订单  [CsOrder]      **/
		public final static @type(Long.class)  String cshsOrder="cshsOrder";
		/** and cshs_order >= ? */
		public final static @type(Long.class) String cshsOrderMin="cshsOrderMin";
		/** and cshs_order <= ? */
		public final static @type(Long.class) String cshsOrderMax="cshsOrderMax";
		/** 接口数据        **/
		public final static @type(String.class) @like String cshsMoData="cshsMoData";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cshsId="cshs_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cshsHost="cshs_host";
		/** 车牌号 [非空]      ${basePath}${proname}/object/car_numbers.do **/
		public final static String cshsNumber="cshs_number";
		/** 添加时间 [非空]       **/
		public final static String cshsAddTime="cshs_add_time";
		/** 下位机时间        **/
		public final static String cshsCurrentTime="cshs_current_time";
		/** 租赁状态        **/
		public final static String cshsRented="cshs_rented";
		/** 有报警        **/
		public final static String cshsWarn="cshs_warn";
		/** RFID卡号        **/
		public final static String cshsRfid="cshs_rfid";
		/** 用户RFID        **/
		public final static String cshsRfidDte="cshs_rfid_dte";
		/** OBD里程        **/
		public final static String cshsObdMile="cshs_obd_mile";
		/** 发动机温度        **/
		public final static String cshsEngineT="cshs_engine_t";
		/** 总里程        **/
		public final static String cshsMileage="cshs_mileage";
		/** 车速        **/
		public final static String cshsSpeed="cshs_speed";
		/** 转速        **/
		public final static String cshsMotor="cshs_motor";
		/** 燃油量        **/
		public final static String cshsOil="cshs_oil";
		/** 蓄电量        **/
		public final static String cshsPower="cshs_power";
		/** 动力电池电量        **/
		public final static String cshsEvBattery="cshs_ev_battery";
		/** 在充电        **/
		public final static String cshsCharging="cshs_charging";
		/** 油里程        **/
		public final static String cshsFuelMileage="cshs_fuel_mileage";
		/** 电量程        **/
		public final static String cshsElectricMileage="cshs_electric_mileage";
		/** 续航里程        **/
		public final static String cshsEndurance="cshs_endurance";
		/** 温度        **/
		public final static String cshsTemperature="cshs_temperature";
		/** 信号强度        **/
		public final static String cshsCsq="cshs_csq";
		/** 功耗        **/
		public final static String cshsPowerConsumption="cshs_power_consumption";
		/** 经度        **/
		public final static String cshsLongitude="cshs_longitude";
		/** 纬度        **/
		public final static String cshsLatitude="cshs_latitude";
		/** 当前订单  [CsOrder]      **/
		public final static String cshsOrder="cshs_order";
		/** 接口数据        **/
		public final static String cshsMoData="cshs_mo_data";
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
				$.Set(name,CsHistoryState.getCsHistoryState(params));
			else
				$.Set(name,CsHistoryState.getCsHistoryStateList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取车辆历史状态数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsHistoryState) $.GetRequest("CsHistoryState$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsHistoryState.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsHistoryState.getCsHistoryState(params);
			else
				value = CsHistoryState.getCsHistoryStateList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsHistoryState.Get($.add(CsHistoryState.F.cshsId,param));
		else if(!$.empty(param.toString()))
			value = CsHistoryState.get(Long.valueOf(param.toString()));
		$.SetRequest("CsHistoryState$"+param.hashCode(), value);
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
	public void mergeSet(CsHistoryState old){
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