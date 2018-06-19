package com.ccclubs.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.ccclubs.model.SrvApiClient;
import com.lazy3q.web.helper.$;

import com.lazy3q.web.tags.BodyTag;

import com.lazy3q.web.util.Page;
import com.lazy3q.lang.*;
import com.lazy3q.util.Function;
import com.ccclubs.dao.impl.MemCache;

@namespace("object/chargepile")
public @caption("充电桩") @table("cs_charge_pile") class CsChargePile implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cscp_id")   @primary  @note("  ") Long cscpId;
	private @caption("所属电站") @column("cscp_station")     @note("  ") Long cscpStation;
	private @caption("终端编号") @column("cscp_code")     @note("  ") String cscpCode;
	private @caption("名称") @column("cscp_name")     @note("  ") String cscpName;
	private @caption("类型") @column("cscp_type")     @note("  ") String cscpType;
	private @caption("状态") @column("cscp_state")     @note("  ") String cscpState;
	private @caption("电压") @column("cscp_voltage")     @note("  ") String cscpVoltage;
	private @caption("电流") @column("cscp_galvanic")     @note("  ") String cscpGalvanic;
	private @caption("功率") @column("cscp_power")     @note("  ") String cscpPower;
	private @caption("电桩厂商") @column("cscp_provider")     @note("  ") String cscpProvider;
	private @caption("其它数据") @column("cscp_data")     @note("  ") String cscpData;
	private @caption("创建时间") @column("cscp_add_time")     @note("  ") Date cscpAddTime;
	private @caption("修改时间") @column("cscp_update_time")     @note("  ") Date cscpUpdateTime;
	
	//默认构造函数
	public CsChargePile(){
	
	}
	
	//主键构造函数
	public CsChargePile(Long id){
		this.cscpId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsChargePile(Long cscpStation,String cscpCode,String cscpName,String cscpType,String cscpState,String cscpVoltage,String cscpGalvanic,String cscpPower,String cscpProvider,String cscpData,Date cscpAddTime,Date cscpUpdateTime){
		this.cscpStation=cscpStation;
		this.cscpCode=cscpCode;
		this.cscpName=cscpName;
		this.cscpType=cscpType;
		this.cscpState=cscpState;
		this.cscpVoltage=cscpVoltage;
		this.cscpGalvanic=cscpGalvanic;
		this.cscpPower=cscpPower;
		this.cscpProvider=cscpProvider;
		this.cscpData=cscpData;
		this.cscpAddTime=cscpAddTime;
		this.cscpUpdateTime=cscpUpdateTime;
	}
	
	//设置非空字段
	public CsChargePile setNotNull(){
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsChargePile cscpId(Long cscpId){
		this.cscpId = cscpId;
		this.setSeted(F.cscpId);
		return this;
	}
	/** 所属电站 [非空]       **/
	public CsChargePile cscpStation(Long cscpStation){
		this.cscpStation = cscpStation;
		this.setSeted(F.cscpStation);
		return this;
	}
	/** 终端编号 [非空]       **/
	public CsChargePile cscpCode(String cscpCode){
		this.cscpCode = cscpCode;
		this.setSeted(F.cscpCode);
		return this;
	}
	/** 名称 [非空]       **/
	public CsChargePile cscpName(String cscpName){
		this.cscpName = cscpName;
		this.setSeted(F.cscpName);
		return this;
	}
	/** 类型 [非空]       **/
	public CsChargePile cscpType(String cscpType){
		this.cscpType = cscpType;
		this.setSeted(F.cscpType);
		return this;
	}
	/** 状态 [非空]       **/
	public CsChargePile cscpState(String cscpState){
		this.cscpState = cscpState;
		this.setSeted(F.cscpState);
		return this;
	}
	/** 电压 [非空]       **/
	public CsChargePile cscpVoltage(String cscpVoltage){
		this.cscpVoltage = cscpVoltage;
		this.setSeted(F.cscpVoltage);
		return this;
	}
	/** 电流 [非空]       **/
	public CsChargePile cscpGalvanic(String cscpGalvanic){
		this.cscpGalvanic = cscpGalvanic;
		this.setSeted(F.cscpGalvanic);
		return this;
	}
	/** 功率 [非空]       **/
	public CsChargePile cscpPower(String cscpPower){
		this.cscpPower = cscpPower;
		this.setSeted(F.cscpPower);
		return this;
	}
	/** 电桩厂商 [非空]       **/
	public CsChargePile cscpProvider(String cscpProvider){
		this.cscpProvider = cscpProvider;
		this.setSeted(F.cscpProvider);
		return this;
	}
	/** 其它数据 [非空]       **/
	public CsChargePile cscpData(String cscpData){
		this.cscpData = cscpData;
		this.setSeted(F.cscpData);
		return this;
	}
	/** 创建时间 [非空]       **/
	public CsChargePile cscpAddTime(Date cscpAddTime){
		this.cscpAddTime = cscpAddTime;
		this.setSeted(F.cscpAddTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsChargePile cscpUpdateTime(Date cscpUpdateTime){
		this.cscpUpdateTime = cscpUpdateTime;
		this.setSeted(F.cscpUpdateTime);
		return this;
	}
	
	//克隆对象
	public CsChargePile clone(){
		CsChargePile clone = new CsChargePile();
		clone.cscpId=this.cscpId;
		clone.cscpStation=this.cscpStation;
		clone.cscpCode=this.cscpCode;
		clone.cscpName=this.cscpName;
		clone.cscpType=this.cscpType;
		clone.cscpState=this.cscpState;
		clone.cscpVoltage=this.cscpVoltage;
		clone.cscpGalvanic=this.cscpGalvanic;
		clone.cscpPower=this.cscpPower;
		clone.cscpProvider=this.cscpProvider;
		clone.cscpData=this.cscpData;
		clone.cscpAddTime=this.cscpAddTime;
		clone.cscpUpdateTime=this.cscpUpdateTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取充电桩
	 * @param id
	 * @return
	 */
	public static @api CsChargePile get(Long id){		
		return getCsChargePileById(id);
	}
	/**
	 * 获取所有充电桩
	 * @return
	 */
	public static @api List<CsChargePile> list(Map params,Integer size){
		return getCsChargePileList(params,size);
	}
	/**
	 * 获取充电桩分页
	 * @return
	 */
	public static @api Page<CsChargePile> page(int page,int size,Map params){
		return getCsChargePilePage(page, size , params);
	}
	/**
	 * 根据查询条件取充电桩
	 * @param params
	 * @return
	 */
	public static @api CsChargePile Get(Map params){
		return getCsChargePile(params);
	}
	/**
	 * 获取充电桩数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsChargePileCount(params);
	}
	/**
	 * 获取充电桩数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsChargePileEval(eval,params);
	}
	
	/**
	 * 根据ID取充电桩
	 * @param id
	 * @return
	 */
	public static @api CsChargePile getCsChargePileById(Long id){		
		CsChargePile csChargePile = (CsChargePile) $.GetRequest("CsChargePile$"+id);
		if(csChargePile!=null)
			return csChargePile;
		com.ccclubs.service.admin.ICsChargePileService csChargePileService = $.GetSpringBean("csChargePileService");		
		return csChargePileService.getCsChargePileById(id);
	}
	/**
	 * 根据ID取充电桩的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsChargePile.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsChargePile csChargePile = get(id);
		if(csChargePile!=null){
			String strValue = csChargePile.getCsCpId$();
			if(!"CsCpId".equals("CsCpId"))
				strValue+="("+csChargePile.getCsCpId$()+")";
			MemCache.setValue(CsChargePile.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String strValue = this.getCsCpId$();
		if(!"CsCpId".equals("CsCpId"))
			strValue+="("+this.getCsCpId$()+")";
		return strValue;
	}
	
	/**
	 * 获取所有充电桩
	 * @return
	 */
	public static @api List<CsChargePile> getCsChargePileList(Map params,Integer size){
		com.ccclubs.service.admin.ICsChargePileService csChargePileService = $.GetSpringBean("csChargePileService");
		return csChargePileService.getCsChargePileList(params, size);
	}
	
	/**
	 * 获取充电桩分页
	 * @return
	 */
	public static @api Page<CsChargePile> getCsChargePilePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsChargePileService csChargePileService = $.GetSpringBean("csChargePileService");
		return csChargePileService.getCsChargePilePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取充电桩
	 * @param params
	 * @return
	 */
	public static @api CsChargePile getCsChargePile(Map params){
		com.ccclubs.service.admin.ICsChargePileService csChargePileService = $.GetSpringBean("csChargePileService");
		return csChargePileService.getCsChargePile(params);
	}
	
	/**
	 * 获取充电桩数
	 * @return
	 */
	public static @api Long getCsChargePileCount(Map params){
		com.ccclubs.service.admin.ICsChargePileService csChargePileService = $.GetSpringBean("csChargePileService");
		return csChargePileService.getCsChargePileCount(params);
	}
		
		
	/**
	 * 获取充电桩自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsChargePileEval(String eval,Map params){
		com.ccclubs.service.admin.ICsChargePileService csChargePileService = $.GetSpringBean("csChargePileService");
		return csChargePileService.getCsChargePileEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsChargePile(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsChargePileService csChargePileService = $.GetSpringBean("csChargePileService");
		csChargePileService.updateCsChargePileByConfirm(set, where);
	}
	
	
	/**
	 * 保存充电桩对象
	 * @param params
	 * @return
	 */
	public CsChargePile save(){
		com.ccclubs.service.admin.ICsChargePileService csChargePileService = $.GetSpringBean("csChargePileService");
		if(this.getCsCpId()!=null)
			csChargePileService.updateCsChargePile(this);
		else
			return csChargePileService.saveCsChargePile(this);
		return this;
	}
	
	
	/**
	 * 更新充电桩对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsChargePileService csChargePileService = $.GetSpringBean("csChargePileService");
		csChargePileService.updateCsChargePile$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsChargePileService csChargePileService = $.GetSpringBean("csChargePileService");
			csChargePileService.deleteCsChargePileById(this.getCsCpId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsChargePileService csChargePileService = $.GetSpringBean("csChargePileService");
		return csChargePileService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsCpId(){
		return this.cscpId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsCpId$(){
		String strValue="";
		 strValue=$.str(this.getCsCpId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCsCpId(Long cscpId){
		this.cscpId = cscpId;
		this.setSeted(F.cscpId);
	}
	/*******************************所属电站**********************************/	
	/**
	* 所属电站 [非空]      
	**/
	public Long getCsCpStation(){
		return this.cscpStation;
	}
	/**
	* 获取所属电站格式化(toString)
	**/
	public String getCsCpStation$(){
		String strValue="";
		 strValue=$.str(this.getCsCpStation());
	 	 return strValue;
	}
	/**
	* 所属电站 [非空]     
	**/
	public void setCsCpStation(Long cscpStation){
		this.cscpStation = cscpStation;
		this.setSeted(F.cscpStation);
	}
	/*******************************终端编号**********************************/	
	/**
	* 终端编号 [非空]      
	**/
	public String getCsCpCode(){
		return this.cscpCode;
	}
	/**
	* 获取终端编号格式化(toString)
	**/
	public String getCsCpCode$(){
		String strValue="";
		 strValue=$.str(this.getCsCpCode());
	 	 return strValue;
	}
	/**
	* 终端编号 [非空]     
	**/
	public void setCsCpCode(String cscpCode){
		this.cscpCode = cscpCode;
		this.setSeted(F.cscpCode);
	}
	/*******************************名称**********************************/	
	/**
	* 名称 [非空]      
	**/
	public String getCsCpName(){
		return this.cscpName;
	}
	/**
	* 获取名称格式化(toString)
	**/
	public String getCsCpName$(){
		String strValue="";
		 strValue=$.str(this.getCsCpName());
	 	 return strValue;
	}
	/**
	* 名称 [非空]     
	**/
	public void setCsCpName(String cscpName){
		this.cscpName = cscpName;
		this.setSeted(F.cscpName);
	}
	/*******************************类型**********************************/	
	/**
	* 类型 [非空]      
	**/
	public String getCsCpType(){
		return this.cscpType;
	}
	/**
	* 获取类型格式化(toString)
	**/
	public String getCsCpType$(){
		String strValue="";
		 strValue=$.str(this.getCsCpType());
	 	 return strValue;
	}
	/**
	* 类型 [非空]     
	**/
	public void setCsCpType(String cscpType){
		this.cscpType = cscpType;
		this.setSeted(F.cscpType);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]      
	**/
	public String getCsCpState(){
		return this.cscpState;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsCpState$(){
		String strValue="";
		 strValue=$.str(this.getCsCpState());
	 	 return strValue;
	}
	/**
	* 状态 [非空]     
	**/
	public void setCsCpState(String cscpState){
		this.cscpState = cscpState;
		this.setSeted(F.cscpState);
	}
	/*******************************电压**********************************/	
	/**
	* 电压 [非空]      
	**/
	public String getCsCpVoltage(){
		return this.cscpVoltage;
	}
	/**
	* 获取电压格式化(toString)
	**/
	public String getCsCpVoltage$(){
		String strValue="";
		 strValue=$.str(this.getCsCpVoltage());
	 	 return strValue;
	}
	/**
	* 电压 [非空]     
	**/
	public void setCsCpVoltage(String cscpVoltage){
		this.cscpVoltage = cscpVoltage;
		this.setSeted(F.cscpVoltage);
	}
	/*******************************电流**********************************/	
	/**
	* 电流 [非空]      
	**/
	public String getCsCpGalvanic(){
		return this.cscpGalvanic;
	}
	/**
	* 获取电流格式化(toString)
	**/
	public String getCsCpGalvanic$(){
		String strValue="";
		 strValue=$.str(this.getCsCpGalvanic());
	 	 return strValue;
	}
	/**
	* 电流 [非空]     
	**/
	public void setCsCpGalvanic(String cscpGalvanic){
		this.cscpGalvanic = cscpGalvanic;
		this.setSeted(F.cscpGalvanic);
	}
	/*******************************功率**********************************/	
	/**
	* 功率 [非空]      
	**/
	public String getCsCpPower(){
		return this.cscpPower;
	}
	/**
	* 获取功率格式化(toString)
	**/
	public String getCsCpPower$(){
		String strValue="";
		 strValue=$.str(this.getCsCpPower());
	 	 return strValue;
	}
	/**
	* 功率 [非空]     
	**/
	public void setCsCpPower(String cscpPower){
		this.cscpPower = cscpPower;
		this.setSeted(F.cscpPower);
	}
	/*******************************电桩厂商**********************************/	
	/**
	* 电桩厂商 [非空]      
	**/
	public String getCsCpProvider(){
		return this.cscpProvider;
	}
	/**
	* 获取电桩厂商格式化(toString)
	**/
	public String getCsCpProvider$(){
		String strValue="";
		 strValue=$.str(this.getCsCpProvider());
	 	 return strValue;
	}
	/**
	* 电桩厂商 [非空]     
	**/
	public void setCsCpProvider(String cscpProvider){
		this.cscpProvider = cscpProvider;
		this.setSeted(F.cscpProvider);
	}
	/*******************************其它数据**********************************/	
	/**
	* 其它数据 [非空]      
	**/
	public String getCsCpData(){
		return this.cscpData;
	}
	/**
	* 获取其它数据格式化(toString)
	**/
	public String getCsCpData$(){
		String strValue="";
		 strValue=$.str(this.getCsCpData());
	 	 return strValue;
	}
	/**
	* 其它数据 [非空]     
	**/
	public void setCsCpData(String cscpData){
		this.cscpData = cscpData;
		this.setSeted(F.cscpData);
	}
	/*******************************创建时间**********************************/	
	/**
	* 创建时间 [非空]      
	**/
	public Date getCsCpAddTime(){
		return this.cscpAddTime;
	}
	/**
	* 获取创建时间格式化(toString)
	**/
	public String getCsCpAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsCpAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 创建时间 [非空]     
	**/
	public void setCsCpAddTime(Date cscpAddTime){
		this.cscpAddTime = cscpAddTime;
		this.setSeted(F.cscpAddTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsCpUpdateTime(){
		return this.cscpUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsCpUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsCpUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCsCpUpdateTime(Date cscpUpdateTime){
		this.cscpUpdateTime = cscpUpdateTime;
		this.setSeted(F.cscpUpdateTime);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsChargePile.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsChargePile.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsChargePile.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsChargePile.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsChargePile.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsChargePile.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsChargePile.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsChargePile.this);
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
		public M cscpId(Object cscpId){this.put("cscpId", cscpId);return this;};
		/** and cscp_id is null */
		public M cscpIdNull(){if(this.get("cscpIdNot")==null)this.put("cscpIdNot", "");this.put("cscpId", null);return this;};
		/** not .... */
		public M cscpIdNot(){this.put("cscpIdNot", "not");return this;};
		/** 所属电站 [非空]       **/
		public M cscpStation(Object cscpStation){this.put("cscpStation", cscpStation);return this;};
		/** and cscp_station is null */
		public M cscpStationNull(){if(this.get("cscpStationNot")==null)this.put("cscpStationNot", "");this.put("cscpStation", null);return this;};
		/** not .... */
		public M cscpStationNot(){this.put("cscpStationNot", "not");return this;};
		/** 终端编号 [非空]       **/
		public M cscpCode(Object cscpCode){this.put("cscpCode", cscpCode);return this;};
		/** and cscp_code is null */
		public M cscpCodeNull(){if(this.get("cscpCodeNot")==null)this.put("cscpCodeNot", "");this.put("cscpCode", null);return this;};
		/** not .... */
		public M cscpCodeNot(){this.put("cscpCodeNot", "not");return this;};
		/** 名称 [非空]       **/
		public M cscpName(Object cscpName){this.put("cscpName", cscpName);return this;};
		/** and cscp_name is null */
		public M cscpNameNull(){if(this.get("cscpNameNot")==null)this.put("cscpNameNot", "");this.put("cscpName", null);return this;};
		/** not .... */
		public M cscpNameNot(){this.put("cscpNameNot", "not");return this;};
		/** 类型 [非空]       **/
		public M cscpType(Object cscpType){this.put("cscpType", cscpType);return this;};
		/** and cscp_type is null */
		public M cscpTypeNull(){if(this.get("cscpTypeNot")==null)this.put("cscpTypeNot", "");this.put("cscpType", null);return this;};
		/** not .... */
		public M cscpTypeNot(){this.put("cscpTypeNot", "not");return this;};
		/** 状态 [非空]       **/
		public M cscpState(Object cscpState){this.put("cscpState", cscpState);return this;};
		/** and cscp_state is null */
		public M cscpStateNull(){if(this.get("cscpStateNot")==null)this.put("cscpStateNot", "");this.put("cscpState", null);return this;};
		/** not .... */
		public M cscpStateNot(){this.put("cscpStateNot", "not");return this;};
		/** 电压 [非空]       **/
		public M cscpVoltage(Object cscpVoltage){this.put("cscpVoltage", cscpVoltage);return this;};
		/** and cscp_voltage is null */
		public M cscpVoltageNull(){if(this.get("cscpVoltageNot")==null)this.put("cscpVoltageNot", "");this.put("cscpVoltage", null);return this;};
		/** not .... */
		public M cscpVoltageNot(){this.put("cscpVoltageNot", "not");return this;};
		/** 电流 [非空]       **/
		public M cscpGalvanic(Object cscpGalvanic){this.put("cscpGalvanic", cscpGalvanic);return this;};
		/** and cscp_galvanic is null */
		public M cscpGalvanicNull(){if(this.get("cscpGalvanicNot")==null)this.put("cscpGalvanicNot", "");this.put("cscpGalvanic", null);return this;};
		/** not .... */
		public M cscpGalvanicNot(){this.put("cscpGalvanicNot", "not");return this;};
		/** 功率 [非空]       **/
		public M cscpPower(Object cscpPower){this.put("cscpPower", cscpPower);return this;};
		/** and cscp_power is null */
		public M cscpPowerNull(){if(this.get("cscpPowerNot")==null)this.put("cscpPowerNot", "");this.put("cscpPower", null);return this;};
		/** not .... */
		public M cscpPowerNot(){this.put("cscpPowerNot", "not");return this;};
		/** 电桩厂商 [非空]       **/
		public M cscpProvider(Object cscpProvider){this.put("cscpProvider", cscpProvider);return this;};
		/** and cscp_provider is null */
		public M cscpProviderNull(){if(this.get("cscpProviderNot")==null)this.put("cscpProviderNot", "");this.put("cscpProvider", null);return this;};
		/** not .... */
		public M cscpProviderNot(){this.put("cscpProviderNot", "not");return this;};
		/** 其它数据 [非空]       **/
		public M cscpData(Object cscpData){this.put("cscpData", cscpData);return this;};
		/** and cscp_data is null */
		public M cscpDataNull(){if(this.get("cscpDataNot")==null)this.put("cscpDataNot", "");this.put("cscpData", null);return this;};
		/** not .... */
		public M cscpDataNot(){this.put("cscpDataNot", "not");return this;};
		/** 创建时间 [非空]       **/
		public M cscpAddTime(Object cscpAddTime){this.put("cscpAddTime", cscpAddTime);return this;};
		/** and cscp_add_time is null */
		public M cscpAddTimeNull(){if(this.get("cscpAddTimeNot")==null)this.put("cscpAddTimeNot", "");this.put("cscpAddTime", null);return this;};
		/** not .... */
		public M cscpAddTimeNot(){this.put("cscpAddTimeNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M cscpUpdateTime(Object cscpUpdateTime){this.put("cscpUpdateTime", cscpUpdateTime);return this;};
		/** and cscp_update_time is null */
		public M cscpUpdateTimeNull(){if(this.get("cscpUpdateTimeNot")==null)this.put("cscpUpdateTimeNot", "");this.put("cscpUpdateTime", null);return this;};
		/** not .... */
		public M cscpUpdateTimeNot(){this.put("cscpUpdateTimeNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有充电桩 **/
		public @api List<CsChargePile> list(Integer size){
			return getCsChargePileList(this,size);
		}
		/** 获取充电桩分页 **/
		public @api Page<CsChargePile> page(int page,int size){
			return getCsChargePilePage(page, size , this);
		}
		/** 根据查询条件取充电桩 **/
		public @api CsChargePile get(){
			return getCsChargePile(this);
		}
		/** 获取充电桩数 **/
		public @api Long count(){
			return getCsChargePileCount(this);
		}
		/** 获取充电桩表达式 **/
		public @api <T> T eval(String strEval){
			return getCsChargePileEval(strEval,this);
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
			updateCsChargePile(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscpId="cscpId";
		/** 所属电站 [非空]       **/
		public final static @type(Long.class)  String cscpStation="cscpStation";
		/** 终端编号 [非空]       **/
		public final static @type(String.class)  String cscpCode="cscpCode";
		/** 名称 [非空]       **/
		public final static @type(String.class)  String cscpName="cscpName";
		/** 类型 [非空]       **/
		public final static @type(String.class)  String cscpType="cscpType";
		/** 状态 [非空]       **/
		public final static @type(String.class)  String cscpState="cscpState";
		/** 电压 [非空]       **/
		public final static @type(String.class)  String cscpVoltage="cscpVoltage";
		/** 电流 [非空]       **/
		public final static @type(String.class)  String cscpGalvanic="cscpGalvanic";
		/** 功率 [非空]       **/
		public final static @type(String.class)  String cscpPower="cscpPower";
		/** 电桩厂商 [非空]       **/
		public final static @type(String.class)  String cscpProvider="cscpProvider";
		/** 其它数据 [非空]       **/
		public final static @type(String.class)  String cscpData="cscpData";
		/** 创建时间 [非空]       **/
		public final static @type(Date.class)  String cscpAddTime="cscpAddTime";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cscpUpdateTime="cscpUpdateTime";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscpId="cscp_id";
		/** 所属电站 [非空]       **/
		public final static String cscpStation="cscp_station";
		/** 终端编号 [非空]       **/
		public final static String cscpCode="cscp_code";
		/** 名称 [非空]       **/
		public final static String cscpName="cscp_name";
		/** 类型 [非空]       **/
		public final static String cscpType="cscp_type";
		/** 状态 [非空]       **/
		public final static String cscpState="cscp_state";
		/** 电压 [非空]       **/
		public final static String cscpVoltage="cscp_voltage";
		/** 电流 [非空]       **/
		public final static String cscpGalvanic="cscp_galvanic";
		/** 功率 [非空]       **/
		public final static String cscpPower="cscp_power";
		/** 电桩厂商 [非空]       **/
		public final static String cscpProvider="cscp_provider";
		/** 其它数据 [非空]       **/
		public final static String cscpData="cscp_data";
		/** 创建时间 [非空]       **/
		public final static String cscpAddTime="cscp_add_time";
		/** 修改时间 [非空]       **/
		public final static String cscpUpdateTime="cscp_update_time";
		
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
				$.Set(name,CsChargePile.getCsChargePile(params));
			else
				$.Set(name,CsChargePile.getCsChargePileList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取充电桩数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsChargePile) $.GetRequest("CsChargePile$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsChargePile.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsChargePile.getCsChargePile(params);
			else
				value = CsChargePile.getCsChargePileList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsChargePile.Get($.add(CsChargePile.F.cscpId,param));
		else if(!$.empty(param.toString()))
			value = CsChargePile.get(Long.valueOf(param.toString()));
		$.SetRequest("CsChargePile$"+param.hashCode(), value);
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
	public void mergeSet(CsChargePile old){
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