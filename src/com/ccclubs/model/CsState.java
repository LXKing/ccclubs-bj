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

@namespace("control/state")
public @caption("车辆当前状态") @table("cs_state") class CsState implements java.io.Serializable
{
	private static final long serialVersionUID =          1l;
	private @caption("编号") @column("css_id")   @primary  @note("  ") Long cssId;// 主键 非空     
	private @caption("城市") @column("css_host")    @relate("$cssHost") @RelateClass(SrvHost.class)  @note("  ") Long cssHost;// 非空     
 	private SrvHost $cssHost;//关联对象[运营城市]
	private @caption("车牌号") @column("css_number")    @note("  ") String cssNumber;// 非空     
	private @caption("关联车辆") @column("css_car")    @relate("$cssCar") @RelateClass(CsCar.class)  @note("  ") Long cssCar;//     
 	private CsCar $cssCar;//关联对象[车辆]
	private @caption("添加时间") @column("css_add_time")    @note("  ") Date cssAddTime;// 非空     
	private @caption("下位机时间") @column("css_current_time")    @note("  ") Date cssCurrentTime;//     
	private @caption("租赁状态") @column("css_rented")    @note("  ") String cssRented;//     
	private @caption("有报警") @column("css_warn")    @note("  ") String cssWarn;//     
	private @caption("RFID卡号") @column("css_rfid")    @note("  ") String cssRfid;//     
	private @caption("用户RFID") @column("css_rfid_dte")    @note("  ") String cssRfidDte;//     
	private @caption("OBD里程") @column("css_obd_mile")    @note("  ") String cssObdMile;//     
	private @caption("发动机温度") @column("css_engine_t")    @note("  ") String cssEngineT;//     
	private @caption("总里程") @column("css_mileage")    @note("  ") String cssMileage;//     
	private @caption("车速") @column("css_speed")    @note("  ") String cssSpeed;//     
	private @caption("转速") @column("css_motor")    @note("  ") String cssMotor;//     
	private @caption("燃油量") @column("css_oil")    @note("  ") String cssOil;//     
	private @caption("蓄电量") @column("css_power")    @note("  ") String cssPower;//     
	private @caption("动力电池电量") @column("css_ev_battery")    @note("  ") String cssEvBattery;//     
	private @caption("在充电") @column("css_charging")    @note("  ") String cssCharging;//     
	private @caption("油里程") @column("css_fuel_mileage")    @note("  ") String cssFuelMileage;//     
	private @caption("电量程") @column("css_electric_mileage")    @note("  ") String cssElectricMileage;//     
	private @caption("续航里程") @column("css_endurance")    @note("  ") String cssEndurance;//     
	private @caption("温度") @column("css_temperature")    @note("  ") String cssTemperature;//     
	private @caption("信号强度") @column("css_csq")    @note("  ") String cssCsq;//     
	private @caption("功耗") @column("css_power_consumption")    @note("  ") String cssPowerConsumption;//     
	private @caption("经度") @column("css_longitude")    @note("  ") String cssLongitude;//     
	private @caption("纬度") @column("css_latitude")    @note("  ") String cssLatitude;//     
	private @caption("当前订单") @column("css_order")    @relate("$cssOrder") @RelateClass(CsOrder.class)  @note("  ") Long cssOrder;//     
 	private CsOrder $cssOrder;//关联对象[系统订单]
	
	//默认构造函数
	public CsState(){
	
	}
	
	//主键构造函数
	public CsState(Long id){
		this.cssId = id;
	}
	
	/**所有字段构造函数 CsState(cssHost,cssNumber,cssCar,cssAddTime,cssCurrentTime,cssRented,cssWarn,cssRfid,cssRfidDte,cssObdMile,cssEngineT,cssMileage,cssSpeed,cssMotor,cssOil,cssPower,cssEvBattery,cssCharging,cssFuelMileage,cssElectricMileage,cssEndurance,cssTemperature,cssCsq,cssPowerConsumption,cssLongitude,cssLatitude,cssOrder)
	 CsState(
	 	$.getLong("cssHost")//城市 [非空]
	 	,$.getString("cssNumber")//车牌号 [非空]
	 	,$.getLong("cssCar")//关联车辆
	 	,$.getDate("cssAddTime")//添加时间 [非空]
	 	,$.getDate("cssCurrentTime")//下位机时间
	 	,$.getString("cssRented")//租赁状态
	 	,$.getString("cssWarn")//有报警
	 	,$.getString("cssRfid")//RFID卡号
	 	,$.getString("cssRfidDte")//用户RFID
	 	,$.getString("cssObdMile")//OBD里程
	 	,$.getString("cssEngineT")//发动机温度
	 	,$.getString("cssMileage")//总里程
	 	,$.getString("cssSpeed")//车速
	 	,$.getString("cssMotor")//转速
	 	,$.getString("cssOil")//燃油量
	 	,$.getString("cssPower")//蓄电量
	 	,$.getString("cssEvBattery")//动力电池电量
	 	,$.getString("cssCharging")//在充电
	 	,$.getString("cssFuelMileage")//油里程
	 	,$.getString("cssElectricMileage")//电量程
	 	,$.getString("cssEndurance")//续航里程
	 	,$.getString("cssTemperature")//温度
	 	,$.getString("cssCsq")//信号强度
	 	,$.getString("cssPowerConsumption")//功耗
	 	,$.getString("cssLongitude")//经度
	 	,$.getString("cssLatitude")//纬度
	 	,$.getLong("cssOrder")//当前订单
	 )
	**/
	public CsState(Long cssHost,String cssNumber,Long cssCar,Date cssAddTime,Date cssCurrentTime,String cssRented,String cssWarn,String cssRfid,String cssRfidDte,String cssObdMile,String cssEngineT,String cssMileage,String cssSpeed,String cssMotor,String cssOil,String cssPower,String cssEvBattery,String cssCharging,String cssFuelMileage,String cssElectricMileage,String cssEndurance,String cssTemperature,String cssCsq,String cssPowerConsumption,String cssLongitude,String cssLatitude,Long cssOrder){
		this.cssHost=cssHost;
		this.cssNumber=cssNumber;
		this.cssCar=cssCar;
		this.cssAddTime=cssAddTime;
		this.cssCurrentTime=cssCurrentTime;
		this.cssRented=cssRented;
		this.cssWarn=cssWarn;
		this.cssRfid=cssRfid;
		this.cssRfidDte=cssRfidDte;
		this.cssObdMile=cssObdMile;
		this.cssEngineT=cssEngineT;
		this.cssMileage=cssMileage;
		this.cssSpeed=cssSpeed;
		this.cssMotor=cssMotor;
		this.cssOil=cssOil;
		this.cssPower=cssPower;
		this.cssEvBattery=cssEvBattery;
		this.cssCharging=cssCharging;
		this.cssFuelMileage=cssFuelMileage;
		this.cssElectricMileage=cssElectricMileage;
		this.cssEndurance=cssEndurance;
		this.cssTemperature=cssTemperature;
		this.cssCsq=cssCsq;
		this.cssPowerConsumption=cssPowerConsumption;
		this.cssLongitude=cssLongitude;
		this.cssLatitude=cssLatitude;
		this.cssOrder=cssOrder;
	}
	
	//设置非空字段
	public CsState setNotNull(Long cssHost,String cssNumber,Date cssAddTime){
		this.cssHost=cssHost;
		this.cssNumber=cssNumber;
		this.cssAddTime=cssAddTime;
		return this;
	}
	/** 编号 [非空]       **/
	public CsState cssId(Long cssId){
		this.cssId = cssId;
		this.setSeted(F.cssId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsState cssHost(Long cssHost){
		this.cssHost = cssHost;
		this.setSeted(F.cssHost);
		return this;
	}
	/** 车牌号 [非空]       **/
	public CsState cssNumber(String cssNumber){
		this.cssNumber = cssNumber;
		this.setSeted(F.cssNumber);
		return this;
	}
	/** 关联车辆  [CsCar]      **/
	public CsState cssCar(Long cssCar){
		this.cssCar = cssCar;
		this.setSeted(F.cssCar);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsState cssAddTime(Date cssAddTime){
		this.cssAddTime = cssAddTime;
		this.setSeted(F.cssAddTime);
		return this;
	}
	/** 下位机时间        **/
	public CsState cssCurrentTime(Date cssCurrentTime){
		this.cssCurrentTime = cssCurrentTime;
		this.setSeted(F.cssCurrentTime);
		return this;
	}
	/** 租赁状态        **/
	public CsState cssRented(String cssRented){
		this.cssRented = cssRented;
		this.setSeted(F.cssRented);
		return this;
	}
	/** 有报警        **/
	public CsState cssWarn(String cssWarn){
		this.cssWarn = cssWarn;
		this.setSeted(F.cssWarn);
		return this;
	}
	/** RFID卡号        **/
	public CsState cssRfid(String cssRfid){
		this.cssRfid = cssRfid;
		this.setSeted(F.cssRfid);
		return this;
	}
	/** 用户RFID        **/
	public CsState cssRfidDte(String cssRfidDte){
		this.cssRfidDte = cssRfidDte;
		this.setSeted(F.cssRfidDte);
		return this;
	}
	/** OBD里程        **/
	public CsState cssObdMile(String cssObdMile){
		this.cssObdMile = cssObdMile;
		this.setSeted(F.cssObdMile);
		return this;
	}
	/** 发动机温度        **/
	public CsState cssEngineT(String cssEngineT){
		this.cssEngineT = cssEngineT;
		this.setSeted(F.cssEngineT);
		return this;
	}
	/** 总里程        **/
	public CsState cssMileage(String cssMileage){
		this.cssMileage = cssMileage;
		this.setSeted(F.cssMileage);
		return this;
	}
	/** 车速        **/
	public CsState cssSpeed(String cssSpeed){
		this.cssSpeed = cssSpeed;
		this.setSeted(F.cssSpeed);
		return this;
	}
	/** 转速        **/
	public CsState cssMotor(String cssMotor){
		this.cssMotor = cssMotor;
		this.setSeted(F.cssMotor);
		return this;
	}
	/** 燃油量        **/
	public CsState cssOil(String cssOil){
		this.cssOil = cssOil;
		this.setSeted(F.cssOil);
		return this;
	}
	/** 蓄电量        **/
	public CsState cssPower(String cssPower){
		this.cssPower = cssPower;
		this.setSeted(F.cssPower);
		return this;
	}
	/** 动力电池电量        **/
	public CsState cssEvBattery(String cssEvBattery){
		this.cssEvBattery = cssEvBattery;
		this.setSeted(F.cssEvBattery);
		return this;
	}
	/** 在充电        **/
	public CsState cssCharging(String cssCharging){
		this.cssCharging = cssCharging;
		this.setSeted(F.cssCharging);
		return this;
	}
	/** 油里程        **/
	public CsState cssFuelMileage(String cssFuelMileage){
		this.cssFuelMileage = cssFuelMileage;
		this.setSeted(F.cssFuelMileage);
		return this;
	}
	/** 电量程        **/
	public CsState cssElectricMileage(String cssElectricMileage){
		this.cssElectricMileage = cssElectricMileage;
		this.setSeted(F.cssElectricMileage);
		return this;
	}
	/** 续航里程        **/
	public CsState cssEndurance(String cssEndurance){
		this.cssEndurance = cssEndurance;
		this.setSeted(F.cssEndurance);
		return this;
	}
	/** 温度        **/
	public CsState cssTemperature(String cssTemperature){
		this.cssTemperature = cssTemperature;
		this.setSeted(F.cssTemperature);
		return this;
	}
	/** 信号强度        **/
	public CsState cssCsq(String cssCsq){
		this.cssCsq = cssCsq;
		this.setSeted(F.cssCsq);
		return this;
	}
	/** 功耗        **/
	public CsState cssPowerConsumption(String cssPowerConsumption){
		this.cssPowerConsumption = cssPowerConsumption;
		this.setSeted(F.cssPowerConsumption);
		return this;
	}
	/** 经度        **/
	public CsState cssLongitude(String cssLongitude){
		this.cssLongitude = cssLongitude;
		this.setSeted(F.cssLongitude);
		return this;
	}
	/** 纬度        **/
	public CsState cssLatitude(String cssLatitude){
		this.cssLatitude = cssLatitude;
		this.setSeted(F.cssLatitude);
		return this;
	}
	/** 当前订单  [CsOrder]      **/
	public CsState cssOrder(Long cssOrder){
		this.cssOrder = cssOrder;
		this.setSeted(F.cssOrder);
		return this;
	}
	
	
	//克隆对象
	public CsState clone(){
		CsState clone = new CsState();
		clone.cssHost=this.cssHost;
		clone.cssNumber=this.cssNumber;
		clone.cssAddTime=this.cssAddTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取车辆当前状态
	 * @param id
	 * @return
	 */
	public static @api CsState get(Long id){		
		return getCsStateById(id);
	}
	/**
	 * 获取所有车辆当前状态
	 * @return
	 */
	public static @api List<CsState> list(Map params,Integer size){
		return getCsStateList(params,size);
	}
	/**
	 * 获取车辆当前状态分页
	 * @return
	 */
	public static @api Page<CsState> page(int page,int size,Map params){
		return getCsStatePage(page, size , params);
	}
	/**
	 * 根据查询条件取车辆当前状态
	 * @param params
	 * @return
	 */
	public static @api CsState Get(Map params){
		return getCsState(params);
	}
	/**
	 * 获取车辆当前状态数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsStateCount(params);
	}
	/**
	 * 获取车辆当前状态数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsStateEval(eval,params);
	}
	
	/**
	 * 根据ID取车辆当前状态
	 * @param id
	 * @return
	 */
	public static @api CsState getCsStateById(Long id){		
		CsState csState = (CsState) $.GetRequest("CsState$"+id);
		if(csState!=null)
			return csState;
		com.ccclubs.service.admin.ICsStateService csStateService = $.GetSpringBean("csStateService");		
		return csStateService.getCsStateById(id);
	}
	
	
	/**
	 * 根据ID取车辆当前状态的标识键值
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
		String keyValue = this.getCssNumber$();
		if(!"CssNumber".equals("CssNumber"))
			keyValue+="("+this.getCssNumber$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有车辆当前状态
	 * @return
	 */
	public static @api List<CsState> getCsStateList(Map params,Integer size){
		com.ccclubs.service.admin.ICsStateService csStateService = $.GetSpringBean("csStateService");
		return csStateService.getCsStateList(params, size);
	}
	
	/**
	 * 获取车辆当前状态分页
	 * @return
	 */
	public static @api Page<CsState> getCsStatePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsStateService csStateService = $.GetSpringBean("csStateService");
		return csStateService.getCsStatePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取车辆当前状态
	 * @param params
	 * @return
	 */
	public static @api CsState getCsState(Map params){
		com.ccclubs.service.admin.ICsStateService csStateService = $.GetSpringBean("csStateService");
		return csStateService.getCsState(params);
	}
	
	/**
	 * 获取车辆当前状态数
	 * @return
	 */
	public static @api Long getCsStateCount(Map params){
		com.ccclubs.service.admin.ICsStateService csStateService = $.GetSpringBean("csStateService");
		return csStateService.getCsStateCount(params);
	}
		
		
	/**
	 * 获取车辆当前状态自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsStateEval(String eval,Map params){
		com.ccclubs.service.admin.ICsStateService csStateService = $.GetSpringBean("csStateService");
		return csStateService.getCsStateEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsState(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsStateService csStateService = $.GetSpringBean("csStateService");
		csStateService.updateCsStateByConfirm(set, where);
	}
	
	
	/**
	 * 保存车辆当前状态对象
	 * @param params
	 * @return
	 */
	public CsState save(){
		com.ccclubs.service.admin.ICsStateService csStateService = $.GetSpringBean("csStateService");
		if(this.getCssId()!=null)
			csStateService.updateCsState(this);
		else
			return csStateService.saveCsState(this);
		return this;
	}
	
	
	/**
	 * 更新车辆当前状态对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsStateService csStateService = $.GetSpringBean("csStateService");
		csStateService.updateCsState$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsStateService csStateService = $.GetSpringBean("csStateService");
			csStateService.deleteCsStateById(this.getCssId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsStateService csStateService = $.GetSpringBean("csStateService");
		return csStateService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCssId(){
		return this.cssId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCssId$(){
		String strValue="";
		 strValue=$.str(this.getCssId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCssId(Long cssId){
		this.cssId = cssId;
		this.setSeted(F.cssId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCssHost(){
		return this.cssHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCssHost$(){
		String strValue="";
		if(this.getCssHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCssHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCssHost(Long cssHost){
		this.cssHost = cssHost;
		this.setSeted(F.cssHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cssHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCssHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCssHost()!=null){
 			srvHost = SrvHost.get(this.getCssHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCssHost(), srvHost);
	 	return srvHost;
	}
	/*******************************车牌号**********************************/	
	/**
	* 车牌号 [非空]      
	**/
	public String getCssNumber(){
		return this.cssNumber;
	}
	/**
	* 获取车牌号格式化(toString)
	**/
	public String getCssNumber$(){
		String strValue="";
		 strValue=$.str(this.getCssNumber());
	 	 return strValue;
	}
	/**
	* 车牌号 [非空]      
	**/
	public void setCssNumber(String cssNumber){
		this.cssNumber = cssNumber;
		this.setSeted(F.cssNumber);
	}
	/*******************************关联车辆**********************************/	
	/**
	* 关联车辆  [CsCar]     
	**/
	public Long getCssCar(){
		return this.cssCar;
	}
	/**
	* 获取关联车辆格式化(toString)
	**/
	public String getCssCar$(){
		String strValue="";
		if(this.getCssCar()!=null){
				strValue+=$.str(CsCar.getKeyValue(this.getCssCar()));
		}			
	 	 return strValue;
	}
	/**
	* 关联车辆  [CsCar]     
	**/
	public void setCssCar(Long cssCar){
		this.cssCar = cssCar;
		this.setSeted(F.cssCar);
	}
	/**
	* 获取关联对象[车辆]
	**/	 			
 	public CsCar get$cssCar(){
 		com.ccclubs.model.CsCar csCar = (com.ccclubs.model.CsCar) $.GetRequest("CsCar$"+this.getCssCar());
 		if(csCar!=null)
			return csCar;
		if(this.getCssCar()!=null){
 			csCar = CsCar.get(this.getCssCar());
 		}
 		$.SetRequest("CsCar$"+this.getCssCar(), csCar);
	 	return csCar;
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCssAddTime(){
		return this.cssAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCssAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCssAddTime(Date cssAddTime){
		this.cssAddTime = cssAddTime;
		this.setSeted(F.cssAddTime);
	}
	/*******************************下位机时间**********************************/	
	/**
	* 下位机时间       
	**/
	public Date getCssCurrentTime(){
		return this.cssCurrentTime;
	}
	/**
	* 获取下位机时间格式化(toString)
	**/
	public String getCssCurrentTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssCurrentTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 下位机时间       
	**/
	public void setCssCurrentTime(Date cssCurrentTime){
		this.cssCurrentTime = cssCurrentTime;
		this.setSeted(F.cssCurrentTime);
	}
	/*******************************租赁状态**********************************/	
	/**
	* 租赁状态       
	**/
	public String getCssRented(){
		return this.cssRented;
	}
	/**
	* 获取租赁状态格式化(toString)
	**/
	public String getCssRented$(){
		String strValue="";
		 strValue=$.str(this.getCssRented());
	 	 return strValue;
	}
	/**
	* 租赁状态       
	**/
	public void setCssRented(String cssRented){
		this.cssRented = cssRented;
		this.setSeted(F.cssRented);
	}
	/*******************************有报警**********************************/	
	/**
	* 有报警       
	**/
	public String getCssWarn(){
		return this.cssWarn;
	}
	/**
	* 获取有报警格式化(toString)
	**/
	public String getCssWarn$(){
		String strValue="";
		 strValue=$.str(this.getCssWarn());
	 	 return strValue;
	}
	/**
	* 有报警       
	**/
	public void setCssWarn(String cssWarn){
		this.cssWarn = cssWarn;
		this.setSeted(F.cssWarn);
	}
	/*******************************RFID卡号**********************************/	
	/**
	* RFID卡号       
	**/
	public String getCssRfid(){
		return this.cssRfid;
	}
	/**
	* 获取RFID卡号格式化(toString)
	**/
	public String getCssRfid$(){
		String strValue="";
		 strValue=$.str(this.getCssRfid());
	 	 return strValue;
	}
	/**
	* RFID卡号       
	**/
	public void setCssRfid(String cssRfid){
		this.cssRfid = cssRfid;
		this.setSeted(F.cssRfid);
	}
	/*******************************用户RFID**********************************/	
	/**
	* 用户RFID       
	**/
	public String getCssRfidDte(){
		return this.cssRfidDte;
	}
	/**
	* 获取用户RFID格式化(toString)
	**/
	public String getCssRfidDte$(){
		String strValue="";
		 strValue=$.str(this.getCssRfidDte());
	 	 return strValue;
	}
	/**
	* 用户RFID       
	**/
	public void setCssRfidDte(String cssRfidDte){
		this.cssRfidDte = cssRfidDte;
		this.setSeted(F.cssRfidDte);
	}
	/*******************************OBD里程**********************************/	
	/**
	* OBD里程       
	**/
	public String getCssObdMile(){
		return this.cssObdMile;
	}
	/**
	* 获取OBD里程格式化(toString)
	**/
	public String getCssObdMile$(){
		String strValue="";
		 strValue=$.str(this.getCssObdMile());
	 	 return strValue;
	}
	/**
	* OBD里程       
	**/
	public void setCssObdMile(String cssObdMile){
		this.cssObdMile = cssObdMile;
		this.setSeted(F.cssObdMile);
	}
	/*******************************发动机温度**********************************/	
	/**
	* 发动机温度       
	**/
	public String getCssEngineT(){
		return this.cssEngineT;
	}
	/**
	* 获取发动机温度格式化(toString)
	**/
	public String getCssEngineT$(){
		String strValue="";
		 strValue=$.str(this.getCssEngineT());
	 	 return strValue;
	}
	/**
	* 发动机温度       
	**/
	public void setCssEngineT(String cssEngineT){
		this.cssEngineT = cssEngineT;
		this.setSeted(F.cssEngineT);
	}
	/*******************************总里程**********************************/	
	/**
	* 总里程       
	**/
	public String getCssMileage(){
		return this.cssMileage;
	}
	/**
	* 获取总里程格式化(toString)
	**/
	public String getCssMileage$(){
		String strValue="";
		 strValue=$.str(this.getCssMileage());
	 	 return strValue;
	}
	/**
	* 总里程       
	**/
	public void setCssMileage(String cssMileage){
		this.cssMileage = cssMileage;
		this.setSeted(F.cssMileage);
	}
	/*******************************车速**********************************/	
	/**
	* 车速       
	**/
	public String getCssSpeed(){
		return this.cssSpeed;
	}
	/**
	* 获取车速格式化(toString)
	**/
	public String getCssSpeed$(){
		String strValue="";
		 strValue=$.str(this.getCssSpeed());
	 	 return strValue;
	}
	/**
	* 车速       
	**/
	public void setCssSpeed(String cssSpeed){
		this.cssSpeed = cssSpeed;
		this.setSeted(F.cssSpeed);
	}
	/*******************************转速**********************************/	
	/**
	* 转速       
	**/
	public String getCssMotor(){
		return this.cssMotor;
	}
	/**
	* 获取转速格式化(toString)
	**/
	public String getCssMotor$(){
		String strValue="";
		 strValue=$.str(this.getCssMotor());
	 	 return strValue;
	}
	/**
	* 转速       
	**/
	public void setCssMotor(String cssMotor){
		this.cssMotor = cssMotor;
		this.setSeted(F.cssMotor);
	}
	/*******************************燃油量**********************************/	
	/**
	* 燃油量       
	**/
	public String getCssOil(){
		return this.cssOil;
	}
	/**
	* 获取燃油量格式化(toString)
	**/
	public String getCssOil$(){
		String strValue="";
		 strValue=$.str(this.getCssOil());
	 	 return strValue;
	}
	/**
	* 燃油量       
	**/
	public void setCssOil(String cssOil){
		this.cssOil = cssOil;
		this.setSeted(F.cssOil);
	}
	/*******************************蓄电量**********************************/	
	/**
	* 蓄电量       
	**/
	public String getCssPower(){
		return this.cssPower;
	}
	/**
	* 获取蓄电量格式化(toString)
	**/
	public String getCssPower$(){
		String strValue="";
		 strValue=$.str(this.getCssPower());
	 	 return strValue;
	}
	/**
	* 蓄电量       
	**/
	public void setCssPower(String cssPower){
		this.cssPower = cssPower;
		this.setSeted(F.cssPower);
	}
	/*******************************动力电池电量**********************************/	
	/**
	* 动力电池电量       
	**/
	public String getCssEvBattery(){
		return this.cssEvBattery;
	}
	/**
	* 获取动力电池电量格式化(toString)
	**/
	public String getCssEvBattery$(){
		String strValue="";
		 strValue=$.str(this.getCssEvBattery());
	 	 return strValue;
	}
	/**
	* 动力电池电量       
	**/
	public void setCssEvBattery(String cssEvBattery){
		this.cssEvBattery = cssEvBattery;
		this.setSeted(F.cssEvBattery);
	}
	/*******************************在充电**********************************/	
	/**
	* 在充电       
	**/
	public String getCssCharging(){
		return this.cssCharging;
	}
	/**
	* 获取在充电格式化(toString)
	**/
	public String getCssCharging$(){
		String strValue="";
		 strValue=$.str(this.getCssCharging());
	 	 return strValue;
	}
	/**
	* 在充电       
	**/
	public void setCssCharging(String cssCharging){
		this.cssCharging = cssCharging;
		this.setSeted(F.cssCharging);
	}
	/*******************************油里程**********************************/	
	/**
	* 油里程       
	**/
	public String getCssFuelMileage(){
		return this.cssFuelMileage;
	}
	/**
	* 获取油里程格式化(toString)
	**/
	public String getCssFuelMileage$(){
		String strValue="";
		 strValue=$.str(this.getCssFuelMileage());
	 	 return strValue;
	}
	/**
	* 油里程       
	**/
	public void setCssFuelMileage(String cssFuelMileage){
		this.cssFuelMileage = cssFuelMileage;
		this.setSeted(F.cssFuelMileage);
	}
	/*******************************电量程**********************************/	
	/**
	* 电量程       
	**/
	public String getCssElectricMileage(){
		return this.cssElectricMileage;
	}
	/**
	* 获取电量程格式化(toString)
	**/
	public String getCssElectricMileage$(){
		String strValue="";
		 strValue=$.str(this.getCssElectricMileage());
	 	 return strValue;
	}
	/**
	* 电量程       
	**/
	public void setCssElectricMileage(String cssElectricMileage){
		this.cssElectricMileage = cssElectricMileage;
		this.setSeted(F.cssElectricMileage);
	}
	/*******************************续航里程**********************************/	
	/**
	* 续航里程       
	**/
	public String getCssEndurance(){
		return this.cssEndurance;
	}
	/**
	* 获取续航里程格式化(toString)
	**/
	public String getCssEndurance$(){
		String strValue="";
		 strValue=$.str(this.getCssEndurance());
	 	 return strValue;
	}
	/**
	* 续航里程       
	**/
	public void setCssEndurance(String cssEndurance){
		this.cssEndurance = cssEndurance;
		this.setSeted(F.cssEndurance);
	}
	/*******************************温度**********************************/	
	/**
	* 温度       
	**/
	public String getCssTemperature(){
		return this.cssTemperature;
	}
	/**
	* 获取温度格式化(toString)
	**/
	public String getCssTemperature$(){
		String strValue="";
		 strValue=$.str(this.getCssTemperature());
	 	 return strValue;
	}
	/**
	* 温度       
	**/
	public void setCssTemperature(String cssTemperature){
		this.cssTemperature = cssTemperature;
		this.setSeted(F.cssTemperature);
	}
	/*******************************信号强度**********************************/	
	/**
	* 信号强度       
	**/
	public String getCssCsq(){
		return this.cssCsq;
	}
	/**
	* 获取信号强度格式化(toString)
	**/
	public String getCssCsq$(){
		String strValue="";
		 strValue=$.str(this.getCssCsq());
	 	 return strValue;
	}
	/**
	* 信号强度       
	**/
	public void setCssCsq(String cssCsq){
		this.cssCsq = cssCsq;
		this.setSeted(F.cssCsq);
	}
	/*******************************功耗**********************************/	
	/**
	* 功耗       
	**/
	public String getCssPowerConsumption(){
		return this.cssPowerConsumption;
	}
	/**
	* 获取功耗格式化(toString)
	**/
	public String getCssPowerConsumption$(){
		String strValue="";
		 strValue=$.str(this.getCssPowerConsumption());
	 	 return strValue;
	}
	/**
	* 功耗       
	**/
	public void setCssPowerConsumption(String cssPowerConsumption){
		this.cssPowerConsumption = cssPowerConsumption;
		this.setSeted(F.cssPowerConsumption);
	}
	/*******************************经度**********************************/	
	/**
	* 经度       
	**/
	public String getCssLongitude(){
		return this.cssLongitude;
	}
	/**
	* 获取经度格式化(toString)
	**/
	public String getCssLongitude$(){
		String strValue="";
		 strValue=$.str(this.getCssLongitude());
	 	 return strValue;
	}
	/**
	* 经度       
	**/
	public void setCssLongitude(String cssLongitude){
		this.cssLongitude = cssLongitude;
		this.setSeted(F.cssLongitude);
	}
	/*******************************纬度**********************************/	
	/**
	* 纬度       
	**/
	public String getCssLatitude(){
		return this.cssLatitude;
	}
	/**
	* 获取纬度格式化(toString)
	**/
	public String getCssLatitude$(){
		String strValue="";
		 strValue=$.str(this.getCssLatitude());
	 	 return strValue;
	}
	/**
	* 纬度       
	**/
	public void setCssLatitude(String cssLatitude){
		this.cssLatitude = cssLatitude;
		this.setSeted(F.cssLatitude);
	}
	/*******************************当前订单**********************************/	
	/**
	* 当前订单  [CsOrder]     
	**/
	public Long getCssOrder(){
		return this.cssOrder;
	}
	/**
	* 获取当前订单格式化(toString)
	**/
	public String getCssOrder$(){
		String strValue="";
		if(this.getCssOrder()!=null){
				strValue+=$.str(CsOrder.getKeyValue(this.getCssOrder()));
		}			
	 	 return strValue;
	}
	/**
	* 当前订单  [CsOrder]     
	**/
	public void setCssOrder(Long cssOrder){
		this.cssOrder = cssOrder;
		this.setSeted(F.cssOrder);
	}
	/**
	* 获取关联对象[系统订单]
	**/	 			
 	public CsOrder get$cssOrder(){
 		com.ccclubs.model.CsOrder csOrder = (com.ccclubs.model.CsOrder) $.GetRequest("CsOrder$"+this.getCssOrder());
 		if(csOrder!=null)
			return csOrder;
		if(this.getCssOrder()!=null){
 			csOrder = CsOrder.get(this.getCssOrder());
 		}
 		$.SetRequest("CsOrder$"+this.getCssOrder(), csOrder);
	 	return csOrder;
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsState.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsState.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsState.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsState.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsState.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsState.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsState.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsState.this);
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
		public M cssId(Object cssId){this.put("cssId", cssId);return this;};
	 	/** and css_id is null */
 		public M cssIdNull(){if(this.get("cssIdNot")==null)this.put("cssIdNot", "");this.put("cssId", null);return this;};
 		/** not .... */
 		public M cssIdNot(){this.put("cssIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M cssHost(Object cssHost){this.put("cssHost", cssHost);return this;};
	 	/** and css_host is null */
 		public M cssHostNull(){if(this.get("cssHostNot")==null)this.put("cssHostNot", "");this.put("cssHost", null);return this;};
 		/** not .... */
 		public M cssHostNot(){this.put("cssHostNot", "not");return this;};
		/** 车牌号 [非空]       **/
		public M cssNumber(Object cssNumber){this.put("cssNumber", cssNumber);return this;};
	 	/** and css_number is null */
 		public M cssNumberNull(){if(this.get("cssNumberNot")==null)this.put("cssNumberNot", "");this.put("cssNumber", null);return this;};
 		/** not .... */
 		public M cssNumberNot(){this.put("cssNumberNot", "not");return this;};
		/** 关联车辆  [CsCar]      **/
		public M cssCar(Object cssCar){this.put("cssCar", cssCar);return this;};
	 	/** and css_car is null */
 		public M cssCarNull(){if(this.get("cssCarNot")==null)this.put("cssCarNot", "");this.put("cssCar", null);return this;};
 		/** not .... */
 		public M cssCarNot(){this.put("cssCarNot", "not");return this;};
		public M cssCar$on(CsCar.M value){
			this.put("CsCar", value);
			this.put("cssCar$on", value);
			return this;
		};	
		/** 添加时间 [非空]       **/
		public M cssAddTime(Object cssAddTime){this.put("cssAddTime", cssAddTime);return this;};
	 	/** and css_add_time is null */
 		public M cssAddTimeNull(){if(this.get("cssAddTimeNot")==null)this.put("cssAddTimeNot", "");this.put("cssAddTime", null);return this;};
 		/** not .... */
 		public M cssAddTimeNot(){this.put("cssAddTimeNot", "not");return this;};
 		/** and css_add_time >= ? */
 		public M cssAddTimeStart(Object start){this.put("cssAddTimeStart", start);return this;};			
 		/** and css_add_time <= ? */
 		public M cssAddTimeEnd(Object end){this.put("cssAddTimeEnd", end);return this;};
		/** 下位机时间        **/
		public M cssCurrentTime(Object cssCurrentTime){this.put("cssCurrentTime", cssCurrentTime);return this;};
	 	/** and css_current_time is null */
 		public M cssCurrentTimeNull(){if(this.get("cssCurrentTimeNot")==null)this.put("cssCurrentTimeNot", "");this.put("cssCurrentTime", null);return this;};
 		/** not .... */
 		public M cssCurrentTimeNot(){this.put("cssCurrentTimeNot", "not");return this;};
 		/** and css_current_time >= ? */
 		public M cssCurrentTimeStart(Object start){this.put("cssCurrentTimeStart", start);return this;};			
 		/** and css_current_time <= ? */
 		public M cssCurrentTimeEnd(Object end){this.put("cssCurrentTimeEnd", end);return this;};
		/** 租赁状态        **/
		public M cssRented(Object cssRented){this.put("cssRented", cssRented);return this;};
	 	/** and css_rented is null */
 		public M cssRentedNull(){if(this.get("cssRentedNot")==null)this.put("cssRentedNot", "");this.put("cssRented", null);return this;};
 		/** not .... */
 		public M cssRentedNot(){this.put("cssRentedNot", "not");return this;};
		/** 有报警        **/
		public M cssWarn(Object cssWarn){this.put("cssWarn", cssWarn);return this;};
	 	/** and css_warn is null */
 		public M cssWarnNull(){if(this.get("cssWarnNot")==null)this.put("cssWarnNot", "");this.put("cssWarn", null);return this;};
 		/** not .... */
 		public M cssWarnNot(){this.put("cssWarnNot", "not");return this;};
		/** RFID卡号        **/
		public M cssRfid(Object cssRfid){this.put("cssRfid", cssRfid);return this;};
	 	/** and css_rfid is null */
 		public M cssRfidNull(){if(this.get("cssRfidNot")==null)this.put("cssRfidNot", "");this.put("cssRfid", null);return this;};
 		/** not .... */
 		public M cssRfidNot(){this.put("cssRfidNot", "not");return this;};
		/** 用户RFID        **/
		public M cssRfidDte(Object cssRfidDte){this.put("cssRfidDte", cssRfidDte);return this;};
	 	/** and css_rfid_dte is null */
 		public M cssRfidDteNull(){if(this.get("cssRfidDteNot")==null)this.put("cssRfidDteNot", "");this.put("cssRfidDte", null);return this;};
 		/** not .... */
 		public M cssRfidDteNot(){this.put("cssRfidDteNot", "not");return this;};
		/** OBD里程        **/
		public M cssObdMile(Object cssObdMile){this.put("cssObdMile", cssObdMile);return this;};
	 	/** and css_obd_mile is null */
 		public M cssObdMileNull(){if(this.get("cssObdMileNot")==null)this.put("cssObdMileNot", "");this.put("cssObdMile", null);return this;};
 		/** not .... */
 		public M cssObdMileNot(){this.put("cssObdMileNot", "not");return this;};
		/** 发动机温度        **/
		public M cssEngineT(Object cssEngineT){this.put("cssEngineT", cssEngineT);return this;};
	 	/** and css_engine_t is null */
 		public M cssEngineTNull(){if(this.get("cssEngineTNot")==null)this.put("cssEngineTNot", "");this.put("cssEngineT", null);return this;};
 		/** not .... */
 		public M cssEngineTNot(){this.put("cssEngineTNot", "not");return this;};
		/** 总里程        **/
		public M cssMileage(Object cssMileage){this.put("cssMileage", cssMileage);return this;};
	 	/** and css_mileage is null */
 		public M cssMileageNull(){if(this.get("cssMileageNot")==null)this.put("cssMileageNot", "");this.put("cssMileage", null);return this;};
 		/** not .... */
 		public M cssMileageNot(){this.put("cssMileageNot", "not");return this;};
		/** 车速        **/
		public M cssSpeed(Object cssSpeed){this.put("cssSpeed", cssSpeed);return this;};
	 	/** and css_speed is null */
 		public M cssSpeedNull(){if(this.get("cssSpeedNot")==null)this.put("cssSpeedNot", "");this.put("cssSpeed", null);return this;};
 		/** not .... */
 		public M cssSpeedNot(){this.put("cssSpeedNot", "not");return this;};
		/** 转速        **/
		public M cssMotor(Object cssMotor){this.put("cssMotor", cssMotor);return this;};
	 	/** and css_motor is null */
 		public M cssMotorNull(){if(this.get("cssMotorNot")==null)this.put("cssMotorNot", "");this.put("cssMotor", null);return this;};
 		/** not .... */
 		public M cssMotorNot(){this.put("cssMotorNot", "not");return this;};
		/** 燃油量        **/
		public M cssOil(Object cssOil){this.put("cssOil", cssOil);return this;};
	 	/** and css_oil is null */
 		public M cssOilNull(){if(this.get("cssOilNot")==null)this.put("cssOilNot", "");this.put("cssOil", null);return this;};
 		/** not .... */
 		public M cssOilNot(){this.put("cssOilNot", "not");return this;};
		/** 蓄电量        **/
		public M cssPower(Object cssPower){this.put("cssPower", cssPower);return this;};
	 	/** and css_power is null */
 		public M cssPowerNull(){if(this.get("cssPowerNot")==null)this.put("cssPowerNot", "");this.put("cssPower", null);return this;};
 		/** not .... */
 		public M cssPowerNot(){this.put("cssPowerNot", "not");return this;};
		/** 动力电池电量        **/
		public M cssEvBattery(Object cssEvBattery){this.put("cssEvBattery", cssEvBattery);return this;};
	 	/** and css_ev_battery is null */
 		public M cssEvBatteryNull(){if(this.get("cssEvBatteryNot")==null)this.put("cssEvBatteryNot", "");this.put("cssEvBattery", null);return this;};
 		/** not .... */
 		public M cssEvBatteryNot(){this.put("cssEvBatteryNot", "not");return this;};
		/** 在充电        **/
		public M cssCharging(Object cssCharging){this.put("cssCharging", cssCharging);return this;};
	 	/** and css_charging is null */
 		public M cssChargingNull(){if(this.get("cssChargingNot")==null)this.put("cssChargingNot", "");this.put("cssCharging", null);return this;};
 		/** not .... */
 		public M cssChargingNot(){this.put("cssChargingNot", "not");return this;};
		/** 油里程        **/
		public M cssFuelMileage(Object cssFuelMileage){this.put("cssFuelMileage", cssFuelMileage);return this;};
	 	/** and css_fuel_mileage is null */
 		public M cssFuelMileageNull(){if(this.get("cssFuelMileageNot")==null)this.put("cssFuelMileageNot", "");this.put("cssFuelMileage", null);return this;};
 		/** not .... */
 		public M cssFuelMileageNot(){this.put("cssFuelMileageNot", "not");return this;};
		/** 电量程        **/
		public M cssElectricMileage(Object cssElectricMileage){this.put("cssElectricMileage", cssElectricMileage);return this;};
	 	/** and css_electric_mileage is null */
 		public M cssElectricMileageNull(){if(this.get("cssElectricMileageNot")==null)this.put("cssElectricMileageNot", "");this.put("cssElectricMileage", null);return this;};
 		/** not .... */
 		public M cssElectricMileageNot(){this.put("cssElectricMileageNot", "not");return this;};
		/** 续航里程        **/
		public M cssEndurance(Object cssEndurance){this.put("cssEndurance", cssEndurance);return this;};
	 	/** and css_endurance is null */
 		public M cssEnduranceNull(){if(this.get("cssEnduranceNot")==null)this.put("cssEnduranceNot", "");this.put("cssEndurance", null);return this;};
 		/** not .... */
 		public M cssEnduranceNot(){this.put("cssEnduranceNot", "not");return this;};
		/** 温度        **/
		public M cssTemperature(Object cssTemperature){this.put("cssTemperature", cssTemperature);return this;};
	 	/** and css_temperature is null */
 		public M cssTemperatureNull(){if(this.get("cssTemperatureNot")==null)this.put("cssTemperatureNot", "");this.put("cssTemperature", null);return this;};
 		/** not .... */
 		public M cssTemperatureNot(){this.put("cssTemperatureNot", "not");return this;};
		/** 信号强度        **/
		public M cssCsq(Object cssCsq){this.put("cssCsq", cssCsq);return this;};
	 	/** and css_csq is null */
 		public M cssCsqNull(){if(this.get("cssCsqNot")==null)this.put("cssCsqNot", "");this.put("cssCsq", null);return this;};
 		/** not .... */
 		public M cssCsqNot(){this.put("cssCsqNot", "not");return this;};
		/** 功耗        **/
		public M cssPowerConsumption(Object cssPowerConsumption){this.put("cssPowerConsumption", cssPowerConsumption);return this;};
	 	/** and css_power_consumption is null */
 		public M cssPowerConsumptionNull(){if(this.get("cssPowerConsumptionNot")==null)this.put("cssPowerConsumptionNot", "");this.put("cssPowerConsumption", null);return this;};
 		/** not .... */
 		public M cssPowerConsumptionNot(){this.put("cssPowerConsumptionNot", "not");return this;};
		/** 经度        **/
		public M cssLongitude(Object cssLongitude){this.put("cssLongitude", cssLongitude);return this;};
	 	/** and css_longitude is null */
 		public M cssLongitudeNull(){if(this.get("cssLongitudeNot")==null)this.put("cssLongitudeNot", "");this.put("cssLongitude", null);return this;};
 		/** not .... */
 		public M cssLongitudeNot(){this.put("cssLongitudeNot", "not");return this;};
		/** 纬度        **/
		public M cssLatitude(Object cssLatitude){this.put("cssLatitude", cssLatitude);return this;};
	 	/** and css_latitude is null */
 		public M cssLatitudeNull(){if(this.get("cssLatitudeNot")==null)this.put("cssLatitudeNot", "");this.put("cssLatitude", null);return this;};
 		/** not .... */
 		public M cssLatitudeNot(){this.put("cssLatitudeNot", "not");return this;};
		/** 当前订单  [CsOrder]      **/
		public M cssOrder(Object cssOrder){this.put("cssOrder", cssOrder);return this;};
	 	/** and css_order is null */
 		public M cssOrderNull(){if(this.get("cssOrderNot")==null)this.put("cssOrderNot", "");this.put("cssOrder", null);return this;};
 		/** not .... */
 		public M cssOrderNot(){this.put("cssOrderNot", "not");return this;};
		/** and css_order >= ? */
		public M cssOrderMin(Object min){this.put("cssOrderMin", min);return this;};
		/** and css_order <= ? */
		public M cssOrderMax(Object max){this.put("cssOrderMax", max);return this;};
		public M cssOrder$on(CsOrder.M value){
			this.put("CsOrder", value);
			this.put("cssOrder$on", value);
			return this;
		};	
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有车辆当前状态 **/
		public @api List<CsState> list(Integer size){
			return getCsStateList(this,size);
		}
		/** 获取车辆当前状态分页 **/
		public @api Page<CsState> page(int page,int size){
			return getCsStatePage(page, size , this);
		}
		/** 根据查询条件取车辆当前状态 **/
		public @api CsState get(){
			return getCsState(this);
		}
		/** 获取车辆当前状态数 **/
		public @api Long count(){
			return getCsStateCount(this);
		}
		/** 获取车辆当前状态表达式 **/
		public @api <T> T eval(String strEval){
			return getCsStateEval(strEval,this);
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
			updateCsState(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cssId="cssId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cssHost="cssHost";
		/** 车牌号 [非空]       **/
		public final static @type(String.class) @like String cssNumber="cssNumber";
		/** 关联车辆  [CsCar]      **/
		public final static @type(Long.class)  String cssCar="cssCar";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cssAddTime="cssAddTime";
	 	/** and css_add_time >= ? */
 		public final static @type(Date.class) String cssAddTimeStart="cssAddTimeStart";
 		/** and css_add_time <= ? */
 		public final static @type(Date.class) String cssAddTimeEnd="cssAddTimeEnd";
		/** 下位机时间        **/
		public final static @type(Date.class)  String cssCurrentTime="cssCurrentTime";
	 	/** and css_current_time >= ? */
 		public final static @type(Date.class) String cssCurrentTimeStart="cssCurrentTimeStart";
 		/** and css_current_time <= ? */
 		public final static @type(Date.class) String cssCurrentTimeEnd="cssCurrentTimeEnd";
		/** 租赁状态        **/
		public final static @type(String.class) @like String cssRented="cssRented";
		/** 有报警        **/
		public final static @type(String.class) @like String cssWarn="cssWarn";
		/** RFID卡号        **/
		public final static @type(String.class) @like String cssRfid="cssRfid";
		/** 用户RFID        **/
		public final static @type(String.class) @like String cssRfidDte="cssRfidDte";
		/** OBD里程        **/
		public final static @type(String.class) @like String cssObdMile="cssObdMile";
		/** 发动机温度        **/
		public final static @type(String.class) @like String cssEngineT="cssEngineT";
		/** 总里程        **/
		public final static @type(String.class) @like String cssMileage="cssMileage";
		/** 车速        **/
		public final static @type(String.class) @like String cssSpeed="cssSpeed";
		/** 转速        **/
		public final static @type(String.class) @like String cssMotor="cssMotor";
		/** 燃油量        **/
		public final static @type(String.class) @like String cssOil="cssOil";
		/** 蓄电量        **/
		public final static @type(String.class) @like String cssPower="cssPower";
		/** 动力电池电量        **/
		public final static @type(String.class) @like String cssEvBattery="cssEvBattery";
		/** 在充电        **/
		public final static @type(String.class) @like String cssCharging="cssCharging";
		/** 油里程        **/
		public final static @type(String.class) @like String cssFuelMileage="cssFuelMileage";
		/** 电量程        **/
		public final static @type(String.class) @like String cssElectricMileage="cssElectricMileage";
		/** 续航里程        **/
		public final static @type(String.class) @like String cssEndurance="cssEndurance";
		/** 温度        **/
		public final static @type(String.class) @like String cssTemperature="cssTemperature";
		/** 信号强度        **/
		public final static @type(String.class) @like String cssCsq="cssCsq";
		/** 功耗        **/
		public final static @type(String.class) @like String cssPowerConsumption="cssPowerConsumption";
		/** 经度        **/
		public final static @type(String.class) @like String cssLongitude="cssLongitude";
		/** 纬度        **/
		public final static @type(String.class) @like String cssLatitude="cssLatitude";
		/** 当前订单  [CsOrder]      **/
		public final static @type(Long.class)  String cssOrder="cssOrder";
		/** and css_order >= ? */
		public final static @type(Long.class) String cssOrderMin="cssOrderMin";
		/** and css_order <= ? */
		public final static @type(Long.class) String cssOrderMax="cssOrderMax";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cssId="css_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cssHost="css_host";
		/** 车牌号 [非空]       **/
		public final static String cssNumber="css_number";
		/** 关联车辆  [CsCar]      **/
		public final static String cssCar="css_car";
		/** 添加时间 [非空]       **/
		public final static String cssAddTime="css_add_time";
		/** 下位机时间        **/
		public final static String cssCurrentTime="css_current_time";
		/** 租赁状态        **/
		public final static String cssRented="css_rented";
		/** 有报警        **/
		public final static String cssWarn="css_warn";
		/** RFID卡号        **/
		public final static String cssRfid="css_rfid";
		/** 用户RFID        **/
		public final static String cssRfidDte="css_rfid_dte";
		/** OBD里程        **/
		public final static String cssObdMile="css_obd_mile";
		/** 发动机温度        **/
		public final static String cssEngineT="css_engine_t";
		/** 总里程        **/
		public final static String cssMileage="css_mileage";
		/** 车速        **/
		public final static String cssSpeed="css_speed";
		/** 转速        **/
		public final static String cssMotor="css_motor";
		/** 燃油量        **/
		public final static String cssOil="css_oil";
		/** 蓄电量        **/
		public final static String cssPower="css_power";
		/** 动力电池电量        **/
		public final static String cssEvBattery="css_ev_battery";
		/** 在充电        **/
		public final static String cssCharging="css_charging";
		/** 油里程        **/
		public final static String cssFuelMileage="css_fuel_mileage";
		/** 电量程        **/
		public final static String cssElectricMileage="css_electric_mileage";
		/** 续航里程        **/
		public final static String cssEndurance="css_endurance";
		/** 温度        **/
		public final static String cssTemperature="css_temperature";
		/** 信号强度        **/
		public final static String cssCsq="css_csq";
		/** 功耗        **/
		public final static String cssPowerConsumption="css_power_consumption";
		/** 经度        **/
		public final static String cssLongitude="css_longitude";
		/** 纬度        **/
		public final static String cssLatitude="css_latitude";
		/** 当前订单  [CsOrder]      **/
		public final static String cssOrder="css_order";
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
				$.Set(name,CsState.getCsState(params));
			else
				$.Set(name,CsState.getCsStateList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取车辆当前状态数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsState) $.GetRequest("CsState$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsState.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsState.getCsState(params);
			else
				value = CsState.getCsStateList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsState.Get($.add(CsState.F.cssId,param));
		else if(!$.empty(param.toString()))
			value = CsState.get(Long.valueOf(param.toString()));
		$.SetRequest("CsState$"+param.hashCode(), value);
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
	public void mergeSet(CsState old){
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