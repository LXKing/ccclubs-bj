package com.ccclubs.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.ccclubs.model.CsSpecialCar.M;
import com.lazy3q.web.helper.$;

import com.lazy3q.web.tags.BodyTag;

import com.lazy3q.web.util.Page;
import com.lazy3q.lang.*;
import com.lazy3q.util.Function;
import com.ccclubs.dao.impl.MemCache;

@namespace("object/chargestation")
public @caption("充电站") @table("cs_charge_station") class CsChargeStation implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cscs_id")   @primary   @note("  ") Long cscsId;
	private @caption("电站编码") @column("cscs_sta_code")      @note("  ") String cscsStaCode;
	private @caption("电站名称") @column("cscs_sta_name")      @note("  ") String cscsStaName;
	private @caption("电站类型") @column("cscs_type")      @note("  ") String cscsType;
	private @caption("运营状态") @column("cscs_op_state")      @note("  ") String cscsOpState;
	private @caption("所在省") @column("cscs_province")      @note("  ") String cscsProvince;
	private @caption("所在城市") @column("cscs_city")      @note("  ") String cscsCity;
	private @caption("所在区域") @column("cscs_area")      @note("  ") String cscsArea;
	private @caption("电站详细地址") @column("cscs_address")      @note("  ") String cscsAddress;
	private @caption("电站经度") @column("cscs_lng")      @note("  ") String cscsLng;
	private @caption("电站纬度") @column("cscs_lat")      @note("  ") String cscsLat;
	private @caption("收费标准") @column("cscs_price")      @note("  ") String cscsPrice;
	private @caption("电桩厂商") @column("cscs_provider")      @note("  ") String cscsProvider;
	private @caption("其它数据") @column("cscs_data")      @note("  ") String cscsData;
	private @caption("创建时间") @column("cscs_add_time")      @note("  ") Date cscsAddTime;
	private @caption("修改时间") @column("cscs_update_time")      @note("  ") Date cscsUpdateTime;
	
	//默认构造函数
	public CsChargeStation(){
	
	}
	
	//主键构造函数
	public CsChargeStation(Long id){
		this.cscsId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsChargeStation(String cscsStaCode,String cscsStaName,String cscsType,String cscsOpState,String cscsProvince,String cscsCity,String cscsArea,String cscsAddress,String cscsLng,String cscsLat,String cscsPrice,String cscsProvider,String cscsData,Date cscsAddTime,Date cscsUpdateTime){
		this.cscsStaCode=cscsStaCode;
		this.cscsStaName=cscsStaName;
		this.cscsType=cscsType;
		this.cscsOpState=cscsOpState;
		this.cscsProvince=cscsProvince;
		this.cscsCity=cscsCity;
		this.cscsArea=cscsArea;
		this.cscsAddress=cscsAddress;
		this.cscsLng=cscsLng;
		this.cscsLat=cscsLat;
		this.cscsPrice=cscsPrice;
		this.cscsProvider=cscsProvider;
		this.cscsData=cscsData;
		this.cscsAddTime=cscsAddTime;
		this.cscsUpdateTime=cscsUpdateTime;
	}
	
	//设置非空字段
	public CsChargeStation setNotNull(String cscsStaCode){
		this.cscsStaCode=cscsStaCode;
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsChargeStation cscsId(Long cscsId){
		this.cscsId = cscsId;
		this.setSeted(F.cscsId);
		return this;
	}
	/** 电站编码 [可空]       **/
	public CsChargeStation cscsStaCode(String cscsStaCode){
		this.cscsStaCode = cscsStaCode;
		this.setSeted(F.cscsStaCode);
		return this;
	}
	/** 电站名称 [非空]       **/
	public CsChargeStation cscsStaName(String cscsStaName){
		this.cscsStaName = cscsStaName;
		this.setSeted(F.cscsStaName);
		return this;
	}
	/** 电站类型 [非空]       **/
	public CsChargeStation cscsType(String cscsType){
		this.cscsType = cscsType;
		this.setSeted(F.cscsType);
		return this;
	}
	/** 运营状态 [非空]       **/
	public CsChargeStation cscsOpState(String cscsOpState){
		this.cscsOpState = cscsOpState;
		this.setSeted(F.cscsOpState);
		return this;
	}
	/** 所在省 [非空]       **/
	public CsChargeStation cscsProvince(String cscsProvince){
		this.cscsProvince = cscsProvince;
		this.setSeted(F.cscsProvince);
		return this;
	}
	/** 所在城市 [非空]       **/
	public CsChargeStation cscsCity(String cscsCity){
		this.cscsCity = cscsCity;
		this.setSeted(F.cscsCity);
		return this;
	}
	/** 所在区域 [非空]       **/
	public CsChargeStation cscsArea(String cscsArea){
		this.cscsArea = cscsArea;
		this.setSeted(F.cscsArea);
		return this;
	}
	/** 电站详细地址 [非空]       **/
	public CsChargeStation cscsAddress(String cscsAddress){
		this.cscsAddress = cscsAddress;
		this.setSeted(F.cscsAddress);
		return this;
	}
	/** 电站经度 [非空]       **/
	public CsChargeStation cscsLng(String cscsLng){
		this.cscsLng = cscsLng;
		this.setSeted(F.cscsLng);
		return this;
	}
	/** 电站纬度 [非空]       **/
	public CsChargeStation cscsLat(String cscsLat){
		this.cscsLat = cscsLat;
		this.setSeted(F.cscsLat);
		return this;
	}
	/** 收费标准 [非空]       **/
	public CsChargeStation cscsPrice(String cscsPrice){
		this.cscsPrice = cscsPrice;
		this.setSeted(F.cscsPrice);
		return this;
	}
	/** 电桩厂商 [非空]       **/
	public CsChargeStation cscsProvider(String cscsProvider){
		this.cscsProvider = cscsProvider;
		this.setSeted(F.cscsProvider);
		return this;
	}
	/** 其它数据 [非空]       **/
	public CsChargeStation cscsData(String cscsData){
		this.cscsData = cscsData;
		this.setSeted(F.cscsData);
		return this;
	}
	/** 创建时间 [非空]       **/
	public CsChargeStation cscsAddTime(Date cscsAddTime){
		this.cscsAddTime = cscsAddTime;
		this.setSeted(F.cscsAddTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsChargeStation cscsUpdateTime(Date cscsUpdateTime){
		this.cscsUpdateTime = cscsUpdateTime;
		this.setSeted(F.cscsUpdateTime);
		return this;
	}
	
	//克隆对象
	public CsChargeStation clone(){
		CsChargeStation clone = new CsChargeStation();
		clone.cscsId=this.cscsId;
		clone.cscsStaCode=this.cscsStaCode;
		clone.cscsStaName=this.cscsStaName;
		clone.cscsType=this.cscsType;
		clone.cscsOpState=this.cscsOpState;
		clone.cscsProvince=this.cscsProvince;
		clone.cscsCity=this.cscsCity;
		clone.cscsArea=this.cscsArea;
		clone.cscsAddress=this.cscsAddress;
		clone.cscsLng=this.cscsLng;
		clone.cscsLat=this.cscsLat;
		clone.cscsPrice=this.cscsPrice;
		clone.cscsProvider=this.cscsProvider;
		clone.cscsData=this.cscsData;
		clone.cscsAddTime=this.cscsAddTime;
		clone.cscsUpdateTime=this.cscsUpdateTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取充电站
	 * @param id
	 * @return
	 */
	public static @api CsChargeStation get(Long id){		
		return getCsChargeStationById(id);
	}
	/**
	 * 获取所有充电站
	 * @return
	 */
	public static @api List<CsChargeStation> list(Map params,Integer size){
		return getCsChargeStationList(params,size);
	}
	/**
	 * 获取充电站分页
	 * @return
	 */
	public static @api Page<CsChargeStation> page(int page,int size,Map params){
		return getCsChargeStationPage(page, size , params);
	}
	/**
	 * 根据查询条件取充电站
	 * @param params
	 * @return
	 */
	public static @api CsChargeStation Get(Map params){
		return getCsChargeStation(params);
	}
	/**
	 * 获取充电站数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsChargeStationCount(params);
	}
	/**
	 * 获取充电站数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsChargeStationEval(eval,params);
	}
	
	/**
	 * 根据ID取充电站
	 * @param id
	 * @return
	 */
	public static @api CsChargeStation getCsChargeStationById(Long id){		
		CsChargeStation csChargeStation = (CsChargeStation) $.GetRequest("CsChargeStation$"+id);
		if(csChargeStation!=null)
			return csChargeStation;
		com.ccclubs.service.admin.ICsChargeStationService csChargeStationService = $.GetSpringBean("csChargeStationService");		
		return csChargeStationService.getCsChargeStationById(id);
	}
	
	
	/**
	 * 根据ID取充电站的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsChargeStation.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsChargeStation csChargeStation = get(id);
		if(csChargeStation!=null){
			String strValue = csChargeStation.getCscsId$();
			if(!"CscsId".equals("CscsId"))
				strValue+="("+csChargeStation.getCscsId$()+")";
			MemCache.setValue(CsChargeStation.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCscsId$();
		if(!"CscsId".equals("CscsId"))
			keyValue+="("+this.getCscsId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有充电站
	 * @return
	 */
	public static @api List<CsChargeStation> getCsChargeStationList(Map params,Integer size){
		com.ccclubs.service.admin.ICsChargeStationService csChargeStationService = $.GetSpringBean("csChargeStationService");
		return csChargeStationService.getCsChargeStationList(params, size);
	}
	
	/**
	 * 获取充电站分页
	 * @return
	 */
	public static @api Page<CsChargeStation> getCsChargeStationPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsChargeStationService csChargeStationService = $.GetSpringBean("csChargeStationService");
		return csChargeStationService.getCsChargeStationPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取充电站
	 * @param params
	 * @return
	 */
	public static @api CsChargeStation getCsChargeStation(Map params){
		com.ccclubs.service.admin.ICsChargeStationService csChargeStationService = $.GetSpringBean("csChargeStationService");
		return csChargeStationService.getCsChargeStation(params);
	}
	
	/**
	 * 获取充电站数
	 * @return
	 */
	public static @api Long getCsChargeStationCount(Map params){
		com.ccclubs.service.admin.ICsChargeStationService csChargeStationService = $.GetSpringBean("csChargeStationService");
		return csChargeStationService.getCsChargeStationCount(params);
	}
		
		
	/**
	 * 获取充电站自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsChargeStationEval(String eval,Map params){
		com.ccclubs.service.admin.ICsChargeStationService csChargeStationService = $.GetSpringBean("csChargeStationService");
		return csChargeStationService.getCsChargeStationEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsChargeStation(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsChargeStationService csChargeStationService = $.GetSpringBean("csChargeStationService");
		csChargeStationService.updateCsChargeStationByConfirm(set, where);
	}
	
	
	/**
	 * 保存充电站对象
	 * @param params
	 * @return
	 */
	public CsChargeStation save(){
		com.ccclubs.service.admin.ICsChargeStationService csChargeStationService = $.GetSpringBean("csChargeStationService");
		if(this.getCscsId()!=null)
			csChargeStationService.updateCsChargeStation(this);
		else
			return csChargeStationService.saveCsChargeStation(this);
		return this;
	}
	
	
	/**
	 * 更新充电站对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsChargeStationService csChargeStationService = $.GetSpringBean("csChargeStationService");
		csChargeStationService.updateCsChargeStation$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsChargeStationService csChargeStationService = $.GetSpringBean("csChargeStationService");
		if($.equals($.config("logic_delete"),"true"))
			csChargeStationService.removeCsChargeStationById(this.getCscsId());
		else
			csChargeStationService.deleteCsChargeStationById(this.getCscsId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsChargeStationService csChargeStationService = $.GetSpringBean("csChargeStationService");
		return csChargeStationService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCscsId(){
		return this.cscsId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCscsId$(){
		String strValue="";
		 strValue=$.str(this.getCscsId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCscsId(Long cscsId){
		this.cscsId = cscsId;
		this.setSeted(F.cscsId);
	}
	/*******************************电站编码**********************************/	
	/**
	* 电站编码 [可空]      
	**/
	public String getCscsStaCode(){
		return this.cscsStaCode;
	}
	/**
	* 获取电站编码格式化(toString)
	**/
	public String getCscsStaCode$(){
		String strValue="";
		 strValue=$.str(this.getCscsStaCode());
	 	 return strValue;
	}
	/**
	* 电站编码 [可空]     
	**/
	public void setCscsStaCode(String cscsStaCode){
		this.cscsStaCode = cscsStaCode;
		this.setSeted(F.cscsStaCode);
	}
	/*******************************电站名称**********************************/	
	/**
	* 电站名称 [非空]      
	**/
	public String getCscsStaName(){
		return this.cscsStaName;
	}
	/**
	* 获取电站名称格式化(toString)
	**/
	public String getCscsStaName$(){
		String strValue="";
		 strValue=$.str(this.getCscsStaName());
	 	 return strValue;
	}
	/**
	* 电站名称 [非空]     
	**/
	public void setCscsStaName(String cscsStaName){
		this.cscsStaName = cscsStaName;
		this.setSeted(F.cscsStaName);
	}
	/*******************************电站类型**********************************/	
	/**
	* 电站类型 [非空]      
	**/
	public String getCscsType(){
		return this.cscsType;
	}
	/**
	* 获取电站类型格式化(toString)
	**/
	public String getCscsType$(){
		String strValue="";
		 strValue=$.str(this.getCscsType());
	 	 return strValue;
	}
	/**
	* 电站类型 [非空]     
	**/
	public void setCscsType(String cscsType){
		this.cscsType = cscsType;
		this.setSeted(F.cscsType);
	}
	/*******************************运营状态**********************************/	
	/**
	* 运营状态 [非空]      
	**/
	public String getCscsOpState(){
		return this.cscsOpState;
	}
	/**
	* 获取运营状态格式化(toString)
	**/
	public String getCscsOpState$(){
		String strValue="";
		 strValue=$.str(this.getCscsOpState());
	 	 return strValue;
	}
	/**
	* 运营状态 [非空]     
	**/
	public void setCscsOpState(String cscsOpState){
		this.cscsOpState = cscsOpState;
		this.setSeted(F.cscsOpState);
	}
	/*******************************所在省**********************************/	
	/**
	* 所在省 [非空]      
	**/
	public String getCscsProvince(){
		return this.cscsProvince;
	}
	/**
	* 获取所在省格式化(toString)
	**/
	public String getCscsProvince$(){
		String strValue="";
		 strValue=$.str(this.getCscsProvince());
	 	 return strValue;
	}
	/**
	* 所在省 [非空]     
	**/
	public void setCscsProvince(String cscsProvince){
		this.cscsProvince = cscsProvince;
		this.setSeted(F.cscsProvince);
	}
	/*******************************所在城市**********************************/	
	/**
	* 所在城市 [非空]      
	**/
	public String getCscsCity(){
		return this.cscsCity;
	}
	/**
	* 获取所在城市格式化(toString)
	**/
	public String getCscsCity$(){
		String strValue="";
		 strValue=$.str(this.getCscsCity());
	 	 return strValue;
	}
	/**
	* 所在城市 [非空]     
	**/
	public void setCscsCity(String cscsCity){
		this.cscsCity = cscsCity;
		this.setSeted(F.cscsCity);
	}
	/*******************************所在区域**********************************/	
	/**
	* 所在区域 [非空]      
	**/
	public String getCscsArea(){
		return this.cscsArea;
	}
	/**
	* 获取所在区域格式化(toString)
	**/
	public String getCscsArea$(){
		String strValue="";
		 strValue=$.str(this.getCscsArea());
	 	 return strValue;
	}
	/**
	* 所在区域 [非空]     
	**/
	public void setCscsArea(String cscsArea){
		this.cscsArea = cscsArea;
		this.setSeted(F.cscsArea);
	}
	/*******************************电站详细地址**********************************/	
	/**
	* 电站详细地址 [非空]      
	**/
	public String getCscsAddress(){
		return this.cscsAddress;
	}
	/**
	* 获取电站详细地址格式化(toString)
	**/
	public String getCscsAddress$(){
		String strValue="";
		 strValue=$.str(this.getCscsAddress());
	 	 return strValue;
	}
	/**
	* 电站详细地址 [非空]     
	**/
	public void setCscsAddress(String cscsAddress){
		this.cscsAddress = cscsAddress;
		this.setSeted(F.cscsAddress);
	}
	/*******************************电站经度**********************************/	
	/**
	* 电站经度 [非空]      
	**/
	public String getCscsLng(){
		return this.cscsLng;
	}
	/**
	* 获取电站经度格式化(toString)
	**/
	public String getCscsLng$(){
		String strValue="";
		 strValue=$.str(this.getCscsLng());
	 	 return strValue;
	}
	/**
	* 电站经度 [非空]     
	**/
	public void setCscsLng(String cscsLng){
		this.cscsLng = cscsLng;
		this.setSeted(F.cscsLng);
	}
	/*******************************电站纬度**********************************/	
	/**
	* 电站纬度 [非空]      
	**/
	public String getCscsLat(){
		return this.cscsLat;
	}
	/**
	* 获取电站纬度格式化(toString)
	**/
	public String getCscsLat$(){
		String strValue="";
		 strValue=$.str(this.getCscsLat());
	 	 return strValue;
	}
	/**
	* 电站纬度 [非空]     
	**/
	public void setCscsLat(String cscsLat){
		this.cscsLat = cscsLat;
		this.setSeted(F.cscsLat);
	}
	/*******************************收费标准**********************************/	
	/**
	* 收费标准 [非空]      
	**/
	public String getCscsPrice(){
		return this.cscsPrice;
	}
	/**
	* 获取收费标准格式化(toString)
	**/
	public String getCscsPrice$(){
		String strValue="";
		 strValue=$.str(this.getCscsPrice());
	 	 return strValue;
	}
	/**
	* 收费标准 [非空]     
	**/
	public void setCscsPrice(String cscsPrice){
		this.cscsPrice = cscsPrice;
		this.setSeted(F.cscsPrice);
	}
	/*******************************电桩厂商**********************************/	
	/**
	* 电桩厂商 [非空]      
	**/
	public String getCscsProvider(){
		return this.cscsProvider;
	}
	/**
	* 获取电桩厂商格式化(toString)
	**/
	public String getCscsProvider$(){
		String strValue="";
		 strValue=$.str(this.getCscsProvider());
	 	 return strValue;
	}
	/**
	* 电桩厂商 [非空]     
	**/
	public void setCscsProvider(String cscsProvider){
		this.cscsProvider = cscsProvider;
		this.setSeted(F.cscsProvider);
	}
	/*******************************其它数据**********************************/	
	/**
	* 其它数据 [非空]      
	**/
	public String getCscsData(){
		return this.cscsData;
	}
	/**
	* 获取其它数据格式化(toString)
	**/
	public String getCscsData$(){
		String strValue="";
		 strValue=$.str(this.getCscsData());
	 	 return strValue;
	}
	/**
	* 其它数据 [非空]     
	**/
	public void setCscsData(String cscsData){
		this.cscsData = cscsData;
		this.setSeted(F.cscsData);
	}
	/*******************************创建时间**********************************/	
	/**
	* 创建时间 [非空]      
	**/
	public Date getCscsAddTime(){
		return this.cscsAddTime;
	}
	/**
	* 获取创建时间格式化(toString)
	**/
	public String getCscsAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscsAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 创建时间 [非空]     
	**/
	public void setCscsAddTime(Date cscsAddTime){
		this.cscsAddTime = cscsAddTime;
		this.setSeted(F.cscsAddTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCscsUpdateTime(){
		return this.cscsUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCscsUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscsUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCscsUpdateTime(Date cscsUpdateTime){
		this.cscsUpdateTime = cscsUpdateTime;
		this.setSeted(F.cscsUpdateTime);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsChargeStation.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsChargeStation.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsChargeStation.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsChargeStation.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsChargeStation.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsChargeStation.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsChargeStation.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsChargeStation.this);
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
		public M cscsId(Object cscsId){this.put("cscsId", cscsId);return this;};
		/** and cscs_id is null */
		public M cscsIdNull(){if(this.get("cscsIdNot")==null)this.put("cscsIdNot", "");this.put("cscsId", null);return this;};
		/** not .... */
		public M cscsIdNot(){this.put("cscsIdNot", "not");return this;};
		/** 电站编码 [可空]       **/
		public M cscsStaCode(Object cscsStaCode){this.put("cscsStaCode", cscsStaCode);return this;};
		/** and cscs_sta_code is null */
		public M cscsStaCodeNull(){if(this.get("cscsStaCodeNot")==null)this.put("cscsStaCodeNot", "");this.put("cscsStaCode", null);return this;};
		/** not .... */
		public M cscsStaCodeNot(){this.put("cscsStaCodeNot", "not");return this;};
		/** 电站名称 [非空]       **/
		public M cscsStaName(Object cscsStaName){this.put("cscsStaName", cscsStaName);return this;};
		/** and cscs_sta_name is null */
		public M cscsStaNameNull(){if(this.get("cscsStaNameNot")==null)this.put("cscsStaNameNot", "");this.put("cscsStaName", null);return this;};
		/** not .... */
		public M cscsStaNameNot(){this.put("cscsStaNameNot", "not");return this;};
		/** 电站类型 [非空]       **/
		public M cscsType(Object cscsType){this.put("cscsType", cscsType);return this;};
		/** and cscs_type is null */
		public M cscsTypeNull(){if(this.get("cscsTypeNot")==null)this.put("cscsTypeNot", "");this.put("cscsType", null);return this;};
		/** not .... */
		public M cscsTypeNot(){this.put("cscsTypeNot", "not");return this;};
		/** 运营状态 [非空]       **/
		public M cscsOpState(Object cscsOpState){this.put("cscsOpState", cscsOpState);return this;};
		/** and cscs_op_state is null */
		public M cscsOpStateNull(){if(this.get("cscsOpStateNot")==null)this.put("cscsOpStateNot", "");this.put("cscsOpState", null);return this;};
		/** not .... */
		public M cscsOpStateNot(){this.put("cscsOpStateNot", "not");return this;};
		/** 所在省 [非空]       **/
		public M cscsProvince(Object cscsProvince){this.put("cscsProvince", cscsProvince);return this;};
		/** and cscs_province is null */
		public M cscsProvinceNull(){if(this.get("cscsProvinceNot")==null)this.put("cscsProvinceNot", "");this.put("cscsProvince", null);return this;};
		/** not .... */
		public M cscsProvinceNot(){this.put("cscsProvinceNot", "not");return this;};
		/** 所在城市 [非空]       **/
		public M cscsCity(Object cscsCity){this.put("cscsCity", cscsCity);return this;};
		/** and cscs_city is null */
		public M cscsCityNull(){if(this.get("cscsCityNot")==null)this.put("cscsCityNot", "");this.put("cscsCity", null);return this;};
		/** not .... */
		public M cscsCityNot(){this.put("cscsCityNot", "not");return this;};
		/** 所在区域 [非空]       **/
		public M cscsArea(Object cscsArea){this.put("cscsArea", cscsArea);return this;};
		/** and cscs_area is null */
		public M cscsAreaNull(){if(this.get("cscsAreaNot")==null)this.put("cscsAreaNot", "");this.put("cscsArea", null);return this;};
		/** not .... */
		public M cscsAreaNot(){this.put("cscsAreaNot", "not");return this;};
		/** 电站详细地址 [非空]       **/
		public M cscsAddress(Object cscsAddress){this.put("cscsAddress", cscsAddress);return this;};
		/** and cscs_address is null */
		public M cscsAddressNull(){if(this.get("cscsAddressNot")==null)this.put("cscsAddressNot", "");this.put("cscsAddress", null);return this;};
		/** not .... */
		public M cscsAddressNot(){this.put("cscsAddressNot", "not");return this;};
		/** 电站经度 [非空]       **/
		public M cscsLng(Object cscsLng){this.put("cscsLng", cscsLng);return this;};
		/** and cscs_lng is null */
		public M cscsLngNull(){if(this.get("cscsLngNot")==null)this.put("cscsLngNot", "");this.put("cscsLng", null);return this;};
		/** not .... */
		public M cscsLngNot(){this.put("cscsLngNot", "not");return this;};
		/** 电站纬度 [非空]       **/
		public M cscsLat(Object cscsLat){this.put("cscsLat", cscsLat);return this;};
		/** and cscs_lat is null */
		public M cscsLatNull(){if(this.get("cscsLatNot")==null)this.put("cscsLatNot", "");this.put("cscsLat", null);return this;};
		/** not .... */
		public M cscsLatNot(){this.put("cscsLatNot", "not");return this;};
		/** 收费标准 [非空]       **/
		public M cscsPrice(Object cscsPrice){this.put("cscsPrice", cscsPrice);return this;};
		/** and cscs_price is null */
		public M cscsPriceNull(){if(this.get("cscsPriceNot")==null)this.put("cscsPriceNot", "");this.put("cscsPrice", null);return this;};
		/** not .... */
		public M cscsPriceNot(){this.put("cscsPriceNot", "not");return this;};
		/** 电桩厂商 [非空]       **/
		public M cscsProvider(Object cscsProvider){this.put("cscsProvider", cscsProvider);return this;};
		/** and cscs_provider is null */
		public M cscsProviderNull(){if(this.get("cscsProviderNot")==null)this.put("cscsProviderNot", "");this.put("cscsProvider", null);return this;};
		/** not .... */
		public M cscsProviderNot(){this.put("cscsProviderNot", "not");return this;};
		/** 其它数据 [非空]       **/
		public M cscsData(Object cscsData){this.put("cscsData", cscsData);return this;};
		/** and cscs_data is null */
		public M cscsDataNull(){if(this.get("cscsDataNot")==null)this.put("cscsDataNot", "");this.put("cscsData", null);return this;};
		/** not .... */
		public M cscsDataNot(){this.put("cscsDataNot", "not");return this;};
		/** 创建时间 [非空]       **/
		public M cscsAddTime(Object cscsAddTime){this.put("cscsAddTime", cscsAddTime);return this;};
		/** and cscs_add_time is null */
		public M cscsAddTimeNull(){if(this.get("cscsAddTimeNot")==null)this.put("cscsAddTimeNot", "");this.put("cscsAddTime", null);return this;};
		/** not .... */
		public M cscsAddTimeNot(){this.put("cscsAddTimeNot", "not");return this;};
		/** and cscs_add_time >= ? */
 		public M cscsAddTimeStart(Object start){this.put("cscsAddTimeStart", start);return this;};			
 		/** and cscs_add_time <= ? */
 		public M cscsAddTimeEnd(Object end){this.put("cscsAddTimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M cscsUpdateTime(Object cscsUpdateTime){this.put("cscsUpdateTime", cscsUpdateTime);return this;};
		/** and cscs_update_time is null */
		public M cscsUpdateTimeNull(){if(this.get("cscsUpdateTimeNot")==null)this.put("cscsUpdateTimeNot", "");this.put("cscsUpdateTime", null);return this;};
		/** not .... */
		public M cscsUpdateTimeNot(){this.put("cscsUpdateTimeNot", "not");return this;};
		/** and cscs_update_time >= ? */
 		public M cscsUpdateTimeStart(Object start){this.put("cscsUpdateTimeStart", start);return this;};			
 		/** and cscs_update_time <= ? */
 		public M cscsUpdateTimeEnd(Object end){this.put("cscsUpdateTimeEnd", end);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有充电站 **/
		public @api List<CsChargeStation> list(Integer size){
			return getCsChargeStationList(this,size);
		}
		/** 获取充电站分页 **/
		public @api Page<CsChargeStation> page(int page,int size){
			return getCsChargeStationPage(page, size , this);
		}
		/** 根据查询条件取充电站 **/
		public @api CsChargeStation get(){
			return getCsChargeStation(this);
		}
		/** 获取充电站数 **/
		public @api Long count(){
			return getCsChargeStationCount(this);
		}
		/** 获取充电站表达式 **/
		public @api <T> T eval(String strEval){
			return getCsChargeStationEval(strEval,this);
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
			updateCsChargeStation(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscsId="cscsId";
		/** 电站编码 [可空]       **/
		public final static @type(String.class)  String cscsStaCode="cscsStaCode";
		/** 电站名称 [非空]       **/
		public final static @type(String.class)  String cscsStaName="cscsStaName";
		/** 电站类型 [非空]       **/
		public final static @type(String.class)  String cscsType="cscsType";
		/** 运营状态 [非空]       **/
		public final static @type(String.class)  String cscsOpState="cscsOpState";
		/** 所在省 [非空]       **/
		public final static @type(String.class)  String cscsProvince="cscsProvince";
		/** 所在城市 [非空]       **/
		public final static @type(String.class)  String cscsCity="cscsCity";
		/** 所在区域 [非空]       **/
		public final static @type(String.class)  String cscsArea="cscsArea";
		/** 电站详细地址 [非空]       **/
		public final static @type(String.class)  String cscsAddress="cscsAddress";
		/** 电站经度 [非空]       **/
		public final static @type(String.class)  String cscsLng="cscsLng";
		/** 电站纬度 [非空]       **/
		public final static @type(String.class)  String cscsLat="cscsLat";
		/** 收费标准 [非空]       **/
		public final static @type(String.class)  String cscsPrice="cscsPrice";
		/** 电桩厂商 [非空]       **/
		public final static @type(String.class)  String cscsProvider="cscsProvider";
		/** 其它数据 [非空]       **/
		public final static @type(String.class)  String cscsData="cscsData";
		/** 创建时间 [非空]       **/
		public final static @type(Date.class)  String cscsAddTime="cscsAddTime";
		/** and cscs_add_time >= ? */
 		public final static @type(Date.class) String cscsAddTimeStart="cscsAddTimeStart";
 		/** and cscs_add_time <= ? */
 		public final static @type(Date.class) String cscsAddTimeEnd="cscsAddTimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cscsUpdateTime="cscsUpdateTime";
		/** and cscs_update_time >= ? */
 		public final static @type(Date.class) String cscsUpdateTimeStart="cscsUpdateTimeStart";
 		/** and cscs_update_time <= ? */
 		public final static @type(Date.class) String cscsUpdateTimeEnd="cscsUpdateTimeEnd";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscsId="cscs_id";
		/** 电站编码 [可空]       **/
		public final static String cscsStaCode="cscs_sta_code";
		/** 电站名称 [非空]       **/
		public final static String cscsStaName="cscs_sta_name";
		/** 电站类型 [非空]       **/
		public final static String cscsType="cscs_type";
		/** 运营状态 [非空]       **/
		public final static String cscsOpState="cscs_op_state";
		/** 所在省 [非空]       **/
		public final static String cscsProvince="cscs_province";
		/** 所在城市 [非空]       **/
		public final static String cscsCity="cscs_city";
		/** 所在区域 [非空]       **/
		public final static String cscsArea="cscs_area";
		/** 电站详细地址 [非空]       **/
		public final static String cscsAddress="cscs_address";
		/** 电站经度 [非空]       **/
		public final static String cscsLng="cscs_lng";
		/** 电站纬度 [非空]       **/
		public final static String cscsLat="cscs_lat";
		/** 收费标准 [非空]       **/
		public final static String cscsPrice="cscs_price";
		/** 电桩厂商 [非空]       **/
		public final static String cscsProvider="cscs_provider";
		/** 其它数据 [非空]       **/
		public final static String cscsData="cscs_data";
		/** 创建时间 [非空]       **/
		public final static String cscsAddTime="cscs_add_time";
		/** 修改时间 [非空]       **/
		public final static String cscsUpdateTime="cscs_update_time";
		
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
				$.Set(name,CsChargeStation.getCsChargeStation(params));
			else
				$.Set(name,CsChargeStation.getCsChargeStationList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取充电站数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsChargeStation) $.GetRequest("CsChargeStation$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsChargeStation.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsChargeStation.getCsChargeStation(params);
			else
				value = CsChargeStation.getCsChargeStationList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsChargeStation.Get($.add(CsChargeStation.F.cscsId,param));
		else if(!$.empty(param.toString()))
			value = CsChargeStation.get(Long.valueOf(param.toString()));
		$.SetRequest("CsChargeStation$"+param.hashCode(), value);
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
	public void mergeSet(CsChargeStation old){
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