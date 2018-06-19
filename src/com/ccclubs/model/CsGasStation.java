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

@namespace("configurator/gasstation")
public @caption("加油站") @table("cs_gas_station") class CsGasStation implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csgs_id")   @primary  @note("  ") Long csgsId;// 主键 非空     
	private @caption("城市") @column("csgs_host")    @relate("$csgsHost") @RelateClass(SrvHost.class)  @note("  ") Long csgsHost;// 非空     
 	private SrvHost $csgsHost;//关联对象[运营城市]
	private @caption("名称") @column("csgs_name")    @note("  ") String csgsName;// 非空     
	private @caption("所在区域") @column("csgs_area")    @relate("$csgsArea") @RelateClass(CsArea.class)  @note(" {csgsHost}:csaHost  ") Long csgsArea;// 非空 {csgsHost}:csaHost     
 	private CsArea $csgsArea;//关联对象[网点区域]
	private @caption("位置信息") @column("csgs_location")    @note("  ") String csgsLocation;// 非空     
	private @caption("地址") @column("csgs_address")    @note("  ") String csgsAddress;// 非空     
	private @caption("添加时间") @column("csgs_add_time")    @note("  ") Date csgsAddTime;// 非空     
	private @caption("状态") @column("csgs_status")    @note(" 1:正常 0:无效  ") Short csgsStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsGasStation(){
	
	}
	
	//主键构造函数
	public CsGasStation(Long id){
		this.csgsId = id;
	}
	
	/**所有字段构造函数 CsGasStation(csgsHost,csgsName,csgsArea,csgsLocation,csgsAddress,csgsAddTime,csgsStatus)
	 CsGasStation(
	 	$.getLong("csgsHost")//城市 [非空]
	 	,$.getString("csgsName")//名称 [非空]
	 	,$.getLong("csgsArea")//所在区域 [非空]
	 	,$.getString("csgsLocation")//位置信息 [非空]
	 	,$.getString("csgsAddress")//地址 [非空]
	 	,$.getDate("csgsAddTime")//添加时间 [非空]
	 	,$.getShort("csgsStatus")//状态 [非空]
	 )
	**/
	public CsGasStation(Long csgsHost,String csgsName,Long csgsArea,String csgsLocation,String csgsAddress,Date csgsAddTime,Short csgsStatus){
		this.csgsHost=csgsHost;
		this.csgsName=csgsName;
		this.csgsArea=csgsArea;
		this.csgsLocation=csgsLocation;
		this.csgsAddress=csgsAddress;
		this.csgsAddTime=csgsAddTime;
		this.csgsStatus=csgsStatus;
	}
	
	//设置非空字段
	public CsGasStation setNotNull(Long csgsHost,String csgsName,Long csgsArea,String csgsLocation,String csgsAddress,Date csgsAddTime,Short csgsStatus){
		this.csgsHost=csgsHost;
		this.csgsName=csgsName;
		this.csgsArea=csgsArea;
		this.csgsLocation=csgsLocation;
		this.csgsAddress=csgsAddress;
		this.csgsAddTime=csgsAddTime;
		this.csgsStatus=csgsStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsGasStation csgsId(Long csgsId){
		this.csgsId = csgsId;
		this.setSeted(F.csgsId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsGasStation csgsHost(Long csgsHost){
		this.csgsHost = csgsHost;
		this.setSeted(F.csgsHost);
		return this;
	}
	/** 名称 [非空]       **/
	public CsGasStation csgsName(String csgsName){
		this.csgsName = csgsName;
		this.setSeted(F.csgsName);
		return this;
	}
	/** 所在区域 [非空] [CsArea]  {csgsHost}:csaHost     **/
	public CsGasStation csgsArea(Long csgsArea){
		this.csgsArea = csgsArea;
		this.setSeted(F.csgsArea);
		return this;
	}
	/** 位置信息 [非空]       **/
	public CsGasStation csgsLocation(String csgsLocation){
		this.csgsLocation = csgsLocation;
		this.setSeted(F.csgsLocation);
		return this;
	}
	/** 地址 [非空]       **/
	public CsGasStation csgsAddress(String csgsAddress){
		this.csgsAddress = csgsAddress;
		this.setSeted(F.csgsAddress);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsGasStation csgsAddTime(Date csgsAddTime){
		this.csgsAddTime = csgsAddTime;
		this.setSeted(F.csgsAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsGasStation csgsStatus(Short csgsStatus){
		this.csgsStatus = csgsStatus;
		this.setSeted(F.csgsStatus);
		return this;
	}
	
	
	//克隆对象
	public CsGasStation clone(){
		CsGasStation clone = new CsGasStation();
		clone.csgsHost=this.csgsHost;
		clone.csgsName=this.csgsName;
		clone.csgsArea=this.csgsArea;
		clone.csgsLocation=this.csgsLocation;
		clone.csgsAddress=this.csgsAddress;
		clone.csgsAddTime=this.csgsAddTime;
		clone.csgsStatus=this.csgsStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取加油站
	 * @param id
	 * @return
	 */
	public static @api CsGasStation get(Long id){		
		return getCsGasStationById(id);
	}
	/**
	 * 获取所有加油站
	 * @return
	 */
	public static @api List<CsGasStation> list(Map params,Integer size){
		return getCsGasStationList(params,size);
	}
	/**
	 * 获取加油站分页
	 * @return
	 */
	public static @api Page<CsGasStation> page(int page,int size,Map params){
		return getCsGasStationPage(page, size , params);
	}
	/**
	 * 根据查询条件取加油站
	 * @param params
	 * @return
	 */
	public static @api CsGasStation Get(Map params){
		return getCsGasStation(params);
	}
	/**
	 * 获取加油站数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsGasStationCount(params);
	}
	/**
	 * 获取加油站数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsGasStationEval(eval,params);
	}
	
	/**
	 * 根据ID取加油站
	 * @param id
	 * @return
	 */
	public static @api CsGasStation getCsGasStationById(Long id){		
		CsGasStation csGasStation = (CsGasStation) $.GetRequest("CsGasStation$"+id);
		if(csGasStation!=null)
			return csGasStation;
		com.ccclubs.service.admin.ICsGasStationService csGasStationService = $.GetSpringBean("csGasStationService");		
		return csGasStationService.getCsGasStationById(id);
	}
	
	
	/**
	 * 根据ID取加油站的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsGasStation.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsGasStation csGasStation = get(id);
		if(csGasStation!=null){
			String strValue = csGasStation.getCsgsName$();
			if(!"CsgsName".equals("CsgsName"))
				strValue+="("+csGasStation.getCsgsName$()+")";
			MemCache.setValue(CsGasStation.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsgsName$();
		if(!"CsgsName".equals("CsgsName"))
			keyValue+="("+this.getCsgsName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有加油站
	 * @return
	 */
	public static @api List<CsGasStation> getCsGasStationList(Map params,Integer size){
		com.ccclubs.service.admin.ICsGasStationService csGasStationService = $.GetSpringBean("csGasStationService");
		return csGasStationService.getCsGasStationList(params, size);
	}
	
	/**
	 * 获取加油站分页
	 * @return
	 */
	public static @api Page<CsGasStation> getCsGasStationPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsGasStationService csGasStationService = $.GetSpringBean("csGasStationService");
		return csGasStationService.getCsGasStationPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取加油站
	 * @param params
	 * @return
	 */
	public static @api CsGasStation getCsGasStation(Map params){
		com.ccclubs.service.admin.ICsGasStationService csGasStationService = $.GetSpringBean("csGasStationService");
		return csGasStationService.getCsGasStation(params);
	}
	
	/**
	 * 获取加油站数
	 * @return
	 */
	public static @api Long getCsGasStationCount(Map params){
		com.ccclubs.service.admin.ICsGasStationService csGasStationService = $.GetSpringBean("csGasStationService");
		return csGasStationService.getCsGasStationCount(params);
	}
		
		
	/**
	 * 获取加油站自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsGasStationEval(String eval,Map params){
		com.ccclubs.service.admin.ICsGasStationService csGasStationService = $.GetSpringBean("csGasStationService");
		return csGasStationService.getCsGasStationEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsGasStation(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsGasStationService csGasStationService = $.GetSpringBean("csGasStationService");
		csGasStationService.updateCsGasStationByConfirm(set, where);
	}
	
	
	/**
	 * 保存加油站对象
	 * @param params
	 * @return
	 */
	public CsGasStation save(){
		com.ccclubs.service.admin.ICsGasStationService csGasStationService = $.GetSpringBean("csGasStationService");
		if(this.getCsgsId()!=null)
			csGasStationService.updateCsGasStation(this);
		else
			return csGasStationService.saveCsGasStation(this);
		return this;
	}
	
	
	/**
	 * 更新加油站对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsGasStationService csGasStationService = $.GetSpringBean("csGasStationService");
		csGasStationService.updateCsGasStation$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsGasStationService csGasStationService = $.GetSpringBean("csGasStationService");
		if($.equals($.config("logic_delete"),"true"))
			csGasStationService.removeCsGasStationById(this.getCsgsId());
		else
			csGasStationService.deleteCsGasStationById(this.getCsgsId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsGasStationService csGasStationService = $.GetSpringBean("csGasStationService");
		return csGasStationService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsgsId(){
		return this.csgsId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsgsId$(){
		String strValue="";
		 strValue=$.str(this.getCsgsId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsgsId(Long csgsId){
		this.csgsId = csgsId;
		this.setSeted(F.csgsId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsgsHost(){
		return this.csgsHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsgsHost$(){
		String strValue="";
		if(this.getCsgsHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsgsHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsgsHost(Long csgsHost){
		this.csgsHost = csgsHost;
		this.setSeted(F.csgsHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csgsHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsgsHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsgsHost()!=null){
 			srvHost = SrvHost.get(this.getCsgsHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsgsHost(), srvHost);
	 	return srvHost;
	}
	/*******************************名称**********************************/	
	/**
	* 名称 [非空]      
	**/
	public String getCsgsName(){
		return this.csgsName;
	}
	/**
	* 获取名称格式化(toString)
	**/
	public String getCsgsName$(){
		String strValue="";
		 strValue=$.str(this.getCsgsName());
	 	 return strValue;
	}
	/**
	* 名称 [非空]      
	**/
	public void setCsgsName(String csgsName){
		this.csgsName = csgsName;
		this.setSeted(F.csgsName);
	}
	/*******************************所在区域**********************************/	
	/**
	* 所在区域 [非空] [CsArea]  {csgsHost}:csaHost    
	**/
	public Long getCsgsArea(){
		return this.csgsArea;
	}
	/**
	* 获取所在区域格式化(toString)
	**/
	public String getCsgsArea$(){
		String strValue="";
		if(this.getCsgsArea()!=null){
				strValue+=$.str(CsArea.getKeyValue(this.getCsgsArea()));
		}			
	 	 return strValue;
	}
	/**
	* 所在区域 [非空] [CsArea]  {csgsHost}:csaHost    
	**/
	public void setCsgsArea(Long csgsArea){
		this.csgsArea = csgsArea;
		this.setSeted(F.csgsArea);
	}
	/**
	* 获取关联对象[网点区域]
	**/	 			
 	public CsArea get$csgsArea(){
 		com.ccclubs.model.CsArea csArea = (com.ccclubs.model.CsArea) $.GetRequest("CsArea$"+this.getCsgsArea());
 		if(csArea!=null)
			return csArea;
		if(this.getCsgsArea()!=null){
 			csArea = CsArea.get(this.getCsgsArea());
 		}
 		$.SetRequest("CsArea$"+this.getCsgsArea(), csArea);
	 	return csArea;
	}
	/*******************************位置信息**********************************/	
	/**
	* 位置信息 [非空]      
	**/
	public String getCsgsLocation(){
		return this.csgsLocation;
	}
	/**
	* 获取位置信息格式化(toString)
	**/
	public String getCsgsLocation$(){
		String strValue="";
		 strValue=$.str(this.getCsgsLocation());
	 	 return strValue;
	}
	/**
	* 位置信息 [非空]      
	**/
	public void setCsgsLocation(String csgsLocation){
		this.csgsLocation = csgsLocation;
		this.setSeted(F.csgsLocation);
	}
	/*******************************地址**********************************/	
	/**
	* 地址 [非空]      
	**/
	public String getCsgsAddress(){
		return this.csgsAddress;
	}
	/**
	* 获取地址格式化(toString)
	**/
	public String getCsgsAddress$(){
		String strValue="";
		 strValue=$.str(this.getCsgsAddress());
	 	 return strValue;
	}
	/**
	* 地址 [非空]      
	**/
	public void setCsgsAddress(String csgsAddress){
		this.csgsAddress = csgsAddress;
		this.setSeted(F.csgsAddress);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsgsAddTime(){
		return this.csgsAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsgsAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsgsAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsgsAddTime(Date csgsAddTime){
		this.csgsAddTime = csgsAddTime;
		this.setSeted(F.csgsAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsgsStatus(){
		return this.csgsStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsgsStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsgsStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsgsStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsgsStatus(Short csgsStatus){
		this.csgsStatus = csgsStatus;
		this.setSeted(F.csgsStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsGasStation.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsGasStation.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsGasStation.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsGasStation.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsGasStation.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsGasStation.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsGasStation.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsGasStation.this);
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
		public M csgsId(Object csgsId){this.put("csgsId", csgsId);return this;};
	 	/** and csgs_id is null */
 		public M csgsIdNull(){if(this.get("csgsIdNot")==null)this.put("csgsIdNot", "");this.put("csgsId", null);return this;};
 		/** not .... */
 		public M csgsIdNot(){this.put("csgsIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csgsHost(Object csgsHost){this.put("csgsHost", csgsHost);return this;};
	 	/** and csgs_host is null */
 		public M csgsHostNull(){if(this.get("csgsHostNot")==null)this.put("csgsHostNot", "");this.put("csgsHost", null);return this;};
 		/** not .... */
 		public M csgsHostNot(){this.put("csgsHostNot", "not");return this;};
		/** 名称 [非空]       **/
		public M csgsName(Object csgsName){this.put("csgsName", csgsName);return this;};
	 	/** and csgs_name is null */
 		public M csgsNameNull(){if(this.get("csgsNameNot")==null)this.put("csgsNameNot", "");this.put("csgsName", null);return this;};
 		/** not .... */
 		public M csgsNameNot(){this.put("csgsNameNot", "not");return this;};
		/** 所在区域 [非空] [CsArea]  {csgsHost}:csaHost     **/
		public M csgsArea(Object csgsArea){this.put("csgsArea", csgsArea);return this;};
	 	/** and csgs_area is null */
 		public M csgsAreaNull(){if(this.get("csgsAreaNot")==null)this.put("csgsAreaNot", "");this.put("csgsArea", null);return this;};
 		/** not .... */
 		public M csgsAreaNot(){this.put("csgsAreaNot", "not");return this;};
		public M csgsArea$on(CsArea.M value){
			this.put("CsArea", value);
			this.put("csgsArea$on", value);
			return this;
		};	
		/** 位置信息 [非空]       **/
		public M csgsLocation(Object csgsLocation){this.put("csgsLocation", csgsLocation);return this;};
	 	/** and csgs_location is null */
 		public M csgsLocationNull(){if(this.get("csgsLocationNot")==null)this.put("csgsLocationNot", "");this.put("csgsLocation", null);return this;};
 		/** not .... */
 		public M csgsLocationNot(){this.put("csgsLocationNot", "not");return this;};
		/** 地址 [非空]       **/
		public M csgsAddress(Object csgsAddress){this.put("csgsAddress", csgsAddress);return this;};
	 	/** and csgs_address is null */
 		public M csgsAddressNull(){if(this.get("csgsAddressNot")==null)this.put("csgsAddressNot", "");this.put("csgsAddress", null);return this;};
 		/** not .... */
 		public M csgsAddressNot(){this.put("csgsAddressNot", "not");return this;};
		/** 添加时间 [非空]       **/
		public M csgsAddTime(Object csgsAddTime){this.put("csgsAddTime", csgsAddTime);return this;};
	 	/** and csgs_add_time is null */
 		public M csgsAddTimeNull(){if(this.get("csgsAddTimeNot")==null)this.put("csgsAddTimeNot", "");this.put("csgsAddTime", null);return this;};
 		/** not .... */
 		public M csgsAddTimeNot(){this.put("csgsAddTimeNot", "not");return this;};
 		/** and csgs_add_time >= ? */
 		public M csgsAddTimeStart(Object start){this.put("csgsAddTimeStart", start);return this;};			
 		/** and csgs_add_time <= ? */
 		public M csgsAddTimeEnd(Object end){this.put("csgsAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csgsStatus(Object csgsStatus){this.put("csgsStatus", csgsStatus);return this;};
	 	/** and csgs_status is null */
 		public M csgsStatusNull(){if(this.get("csgsStatusNot")==null)this.put("csgsStatusNot", "");this.put("csgsStatus", null);return this;};
 		/** not .... */
 		public M csgsStatusNot(){this.put("csgsStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有加油站 **/
		public @api List<CsGasStation> list(Integer size){
			return getCsGasStationList(this,size);
		}
		/** 获取加油站分页 **/
		public @api Page<CsGasStation> page(int page,int size){
			return getCsGasStationPage(page, size , this);
		}
		/** 根据查询条件取加油站 **/
		public @api CsGasStation get(){
			return getCsGasStation(this);
		}
		/** 获取加油站数 **/
		public @api Long count(){
			return getCsGasStationCount(this);
		}
		/** 获取加油站表达式 **/
		public @api <T> T eval(String strEval){
			return getCsGasStationEval(strEval,this);
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
			updateCsGasStation(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csgsId="csgsId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csgsHost="csgsHost";
		/** 名称 [非空]       **/
		public final static @type(String.class) @like String csgsName="csgsName";
		/** 所在区域 [非空] [CsArea]  {csgsHost}:csaHost     **/
		public final static @type(Long.class)  String csgsArea="csgsArea";
		/** 位置信息 [非空]       **/
		public final static @type(String.class) @like String csgsLocation="csgsLocation";
		/** 地址 [非空]       **/
		public final static @type(String.class) @like String csgsAddress="csgsAddress";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csgsAddTime="csgsAddTime";
	 	/** and csgs_add_time >= ? */
 		public final static @type(Date.class) String csgsAddTimeStart="csgsAddTimeStart";
 		/** and csgs_add_time <= ? */
 		public final static @type(Date.class) String csgsAddTimeEnd="csgsAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csgsStatus="csgsStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csgsId="csgs_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csgsHost="csgs_host";
		/** 名称 [非空]       **/
		public final static String csgsName="csgs_name";
		/** 所在区域 [非空] [CsArea]  {csgsHost}:csaHost     **/
		public final static String csgsArea="csgs_area";
		/** 位置信息 [非空]       **/
		public final static String csgsLocation="csgs_location";
		/** 地址 [非空]       **/
		public final static String csgsAddress="csgs_address";
		/** 添加时间 [非空]       **/
		public final static String csgsAddTime="csgs_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csgsStatus="csgs_status";
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
				$.Set(name,CsGasStation.getCsGasStation(params));
			else
				$.Set(name,CsGasStation.getCsGasStationList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取加油站数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsGasStation) $.GetRequest("CsGasStation$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsGasStation.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsGasStation.getCsGasStation(params);
			else
				value = CsGasStation.getCsGasStationList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsGasStation.Get($.add(CsGasStation.F.csgsId,param));
		else if(!$.empty(param.toString()))
			value = CsGasStation.get(Long.valueOf(param.toString()));
		$.SetRequest("CsGasStation$"+param.hashCode(), value);
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
	public void mergeSet(CsGasStation old){
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