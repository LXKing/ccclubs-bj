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

@namespace("unit/drivercar")
public @caption("司机车辆") @table("cs_driver_car") class CsDriverCar implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csdc_id")   @primary   @note("  ") Long csdcId;
	private @caption("城市") @column("csdc_host")    @relate("$csdcHost") @RelateClass(SrvHost.class)  @note("  ") Long csdcHost;
	private SrvHost $csdcHost;//关联对象[城市]
	private @caption("网点") @column("csdc_outlets")    @relate("$csdcOutlets") @RelateClass(CsOutlets.class)  @note("  ") Long csdcOutlets;
	private CsOutlets $csdcOutlets;//关联对象[网点]
	private @caption("司机") @column("csdc_driver")    @relate("$csdcDriver") @RelateClass(CsDriver.class)  @note("  ") Long csdcDriver;
	private CsDriver $csdcDriver;//关联对象[司机]
	private @caption("车辆") @column("csdc_car")    @relate("$csdcCar") @RelateClass(CsCarRes.class)  @note("  ") Long csdcCar;
	private CsCarRes $csdcCar;//关联对象[车辆]
	private @caption("业务状态") @column("csdc_state")      @note(" 0:出车 1:已接单 2:离线 ") Short csdcState;
	private @caption("数据状态") @column("csdc_status")      @note(" 1:正常 0:禁用  ") Short csdcStatus;
	private @caption("创建时间") @column("csdc_add_time")      @note("  ") Date csdcAddTime;
	private @caption("修改时间") @column("csdc_update_time")      @note("  ") Date csdcUpdateTime;
	
	//默认构造函数
	public CsDriverCar(){
	
	}
	
	//主键构造函数
	public CsDriverCar(Long id){
		this.csdcId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsDriverCar(Long csdcHost,Long csdcOutlets,Long csdcDriver,Long csdcCar,Short csdcState,Short csdcStatus,Date csdcAddTime,Date csdcUpdateTime){
		this.csdcHost=csdcHost;
		this.csdcOutlets=csdcOutlets;
		this.csdcDriver=csdcDriver;
		this.csdcCar=csdcCar;
		this.csdcState=csdcState;
		this.csdcStatus=csdcStatus;
		this.csdcAddTime=csdcAddTime;
		this.csdcUpdateTime=csdcUpdateTime;
	}
	
	//设置非空字段
	public CsDriverCar setNotNull(Long csdcHost,Long csdcOutlets,Long csdcDriver,Long csdcCar,Short csdcState,Short csdcStatus){
		this.csdcHost=csdcHost;
		this.csdcOutlets=csdcOutlets;
		this.csdcDriver=csdcDriver;
		this.csdcCar=csdcCar;
		this.csdcState=csdcState;
		this.csdcStatus=csdcStatus;
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsDriverCar csdcId(Long csdcId){
		this.csdcId = csdcId;
		this.setSeted(F.csdcId);
		return this;
	}
	/** 城市 [可空]       **/
	public CsDriverCar csdcHost(Long csdcHost){
		this.csdcHost = csdcHost;
		this.setSeted(F.csdcHost);
		return this;
	}
	/** 网点 [可空]       **/
	public CsDriverCar csdcOutlets(Long csdcOutlets){
		this.csdcOutlets = csdcOutlets;
		this.setSeted(F.csdcOutlets);
		return this;
	}
	/** 司机 [可空]       **/
	public CsDriverCar csdcDriver(Long csdcDriver){
		this.csdcDriver = csdcDriver;
		this.setSeted(F.csdcDriver);
		return this;
	}
	/** 车辆 [可空]       **/
	public CsDriverCar csdcCar(Long csdcCar){
		this.csdcCar = csdcCar;
		this.setSeted(F.csdcCar);
		return this;
	}
	/** 业务状态 [可空]       **/
	public CsDriverCar csdcState(Short csdcState){
		this.csdcState = csdcState;
		this.setSeted(F.csdcState);
		return this;
	}
	/** 数据状态 [可空]       **/
	public CsDriverCar csdcStatus(Short csdcStatus){
		this.csdcStatus = csdcStatus;
		this.setSeted(F.csdcStatus);
		return this;
	}
	/** 创建时间 [非空]       **/
	public CsDriverCar csdcAddTime(Date csdcAddTime){
		this.csdcAddTime = csdcAddTime;
		this.setSeted(F.csdcAddTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsDriverCar csdcUpdateTime(Date csdcUpdateTime){
		this.csdcUpdateTime = csdcUpdateTime;
		this.setSeted(F.csdcUpdateTime);
		return this;
	}
	
	//克隆对象
	public CsDriverCar clone(){
		CsDriverCar clone = new CsDriverCar();
		clone.csdcId=this.csdcId;
		clone.csdcHost=this.csdcHost;
		clone.csdcOutlets=this.csdcOutlets;
		clone.csdcDriver=this.csdcDriver;
		clone.csdcCar=this.csdcCar;
		clone.csdcState=this.csdcState;
		clone.csdcStatus=this.csdcStatus;
		clone.csdcAddTime=this.csdcAddTime;
		clone.csdcUpdateTime=this.csdcUpdateTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取司机车辆
	 * @param id
	 * @return
	 */
	public static @api CsDriverCar get(Long id){		
		return getCsDriverCarById(id);
	}
	/**
	 * 获取所有司机车辆
	 * @return
	 */
	public static @api List<CsDriverCar> list(Map params,Integer size){
		return getCsDriverCarList(params,size);
	}
	/**
	 * 获取司机车辆分页
	 * @return
	 */
	public static @api Page<CsDriverCar> page(int page,int size,Map params){
		return getCsDriverCarPage(page, size , params);
	}
	/**
	 * 根据查询条件取司机车辆
	 * @param params
	 * @return
	 */
	public static @api CsDriverCar Get(Map params){
		return getCsDriverCar(params);
	}
	/**
	 * 获取司机车辆数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsDriverCarCount(params);
	}
	/**
	 * 获取司机车辆数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsDriverCarEval(eval,params);
	}
	
	/**
	 * 根据ID取司机车辆
	 * @param id
	 * @return
	 */
	public static @api CsDriverCar getCsDriverCarById(Long id){		
		CsDriverCar csDriverCar = (CsDriverCar) $.GetRequest("CsDriverCar$"+id);
		if(csDriverCar!=null)
			return csDriverCar;
		com.ccclubs.service.admin.ICsDriverCarService csDriverCarService = $.GetSpringBean("csDriverCarService");		
		return csDriverCarService.getCsDriverCarById(id);
	}
	
	
	/**
	 * 根据ID取司机车辆的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsDriverCar.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsDriverCar csDriverCar = get(id);
		if(csDriverCar!=null){
			String strValue = csDriverCar.getCsdcId$();
			if(!"CsdcId".equals("CsdcId"))
				strValue+="("+csDriverCar.getCsdcId$()+")";
			MemCache.setValue(CsDriverCar.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsdcId$();
		if(!"CsdcId".equals("CsdcId"))
			keyValue+="("+this.getCsdcId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有司机车辆
	 * @return
	 */
	public static @api List<CsDriverCar> getCsDriverCarList(Map params,Integer size){
		com.ccclubs.service.admin.ICsDriverCarService csDriverCarService = $.GetSpringBean("csDriverCarService");
		return csDriverCarService.getCsDriverCarList(params, size);
	}
	
	/**
	 * 获取司机车辆分页
	 * @return
	 */
	public static @api Page<CsDriverCar> getCsDriverCarPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsDriverCarService csDriverCarService = $.GetSpringBean("csDriverCarService");
		return csDriverCarService.getCsDriverCarPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取司机车辆
	 * @param params
	 * @return
	 */
	public static @api CsDriverCar getCsDriverCar(Map params){
		com.ccclubs.service.admin.ICsDriverCarService csDriverCarService = $.GetSpringBean("csDriverCarService");
		return csDriverCarService.getCsDriverCar(params);
	}
	
	/**
	 * 获取司机车辆数
	 * @return
	 */
	public static @api Long getCsDriverCarCount(Map params){
		com.ccclubs.service.admin.ICsDriverCarService csDriverCarService = $.GetSpringBean("csDriverCarService");
		return csDriverCarService.getCsDriverCarCount(params);
	}
		
		
	/**
	 * 获取司机车辆自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsDriverCarEval(String eval,Map params){
		com.ccclubs.service.admin.ICsDriverCarService csDriverCarService = $.GetSpringBean("csDriverCarService");
		return csDriverCarService.getCsDriverCarEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsDriverCar(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsDriverCarService csDriverCarService = $.GetSpringBean("csDriverCarService");
		csDriverCarService.updateCsDriverCarByConfirm(set, where);
	}
	
	
	/**
	 * 保存司机车辆对象
	 * @param params
	 * @return
	 */
	public CsDriverCar save(){
		com.ccclubs.service.admin.ICsDriverCarService csDriverCarService = $.GetSpringBean("csDriverCarService");
		if(this.getCsdcId()!=null)
			csDriverCarService.updateCsDriverCar(this);
		else
			return csDriverCarService.saveCsDriverCar(this);
		return this;
	}
	
	
	/**
	 * 更新司机车辆对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsDriverCarService csDriverCarService = $.GetSpringBean("csDriverCarService");
		csDriverCarService.updateCsDriverCar$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsDriverCarService csDriverCarService = $.GetSpringBean("csDriverCarService");
		if($.equals($.config("logic_delete"),"true"))
			csDriverCarService.removeCsDriverCarById(this.getCsdcId());
		else
			csDriverCarService.deleteCsDriverCarById(this.getCsdcId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsDriverCarService csDriverCarService = $.GetSpringBean("csDriverCarService");
		return csDriverCarService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsdcId(){
		return this.csdcId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsdcId$(){
		String strValue="";
		 strValue=$.str(this.getCsdcId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCsdcId(Long csdcId){
		this.csdcId = csdcId;
		this.setSeted(F.csdcId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [可空]      
	**/
	public Long getCsdcHost(){
		return this.csdcHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsdcHost$(){
		String strValue="";
		if(this.getCsdcHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsdcHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [可空]     
	**/
	public void setCsdcHost(Long csdcHost){
		this.csdcHost = csdcHost;
		this.setSeted(F.csdcHost);
	}
	/**
	* 获取关联对象[城市]
	**/	 			
 	public SrvHost get$csdcHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsdcHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsdcHost()!=null){
			srvHost = SrvHost.get(this.getCsdcHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsdcHost(), srvHost);
	 	return srvHost;
	}
	/*******************************网点**********************************/	
	/**
	* 网点 [可空]      
	**/
	public Long getCsdcOutlets(){
		return this.csdcOutlets;
	}
	/**
	* 获取网点格式化(toString)
	**/
	public String getCsdcOutlets$(){
		String strValue="";
		if(this.getCsdcOutlets()!=null){
				strValue+=$.str(CsOutlets.getKeyValue(this.getCsdcOutlets()));
		}			
	 	 return strValue;
	}
	/**
	* 网点 [可空]     
	**/
	public void setCsdcOutlets(Long csdcOutlets){
		this.csdcOutlets = csdcOutlets;
		this.setSeted(F.csdcOutlets);
	}
	/**
	* 获取关联对象[网点]
	**/	 			
 	public CsOutlets get$csdcOutlets(){
 		com.ccclubs.model.CsOutlets csOutlets = (com.ccclubs.model.CsOutlets) $.GetRequest("CsOutlets$"+this.getCsdcOutlets());
 		if(csOutlets!=null)
			return csOutlets;
		if(this.getCsdcOutlets()!=null){
			csOutlets = CsOutlets.get(this.getCsdcOutlets());
 		}
 		$.SetRequest("CsOutlets$"+this.getCsdcOutlets(), csOutlets);
	 	return csOutlets;
	}
	/*******************************司机**********************************/	
	/**
	* 司机 [可空]      
	**/
	public Long getCsdcDriver(){
		return this.csdcDriver;
	}
	/**
	* 获取司机格式化(toString)
	**/
	public String getCsdcDriver$(){
		String strValue="";
		if(this.getCsdcDriver()!=null){
				strValue+=$.str(CsDriver.getKeyValue(this.getCsdcDriver()));
		}			
	 	 return strValue;
	}
	/**
	* 司机 [可空]     
	**/
	public void setCsdcDriver(Long csdcDriver){
		this.csdcDriver = csdcDriver;
		this.setSeted(F.csdcDriver);
	}
	/**
	* 获取关联对象[司机]
	**/	 			
 	public CsDriver get$csdcDriver(){
 		com.ccclubs.model.CsDriver csDriver = (com.ccclubs.model.CsDriver) $.GetRequest("CsDriver$"+this.getCsdcDriver());
 		if(csDriver!=null)
			return csDriver;
		if(this.getCsdcDriver()!=null){
			csDriver = CsDriver.get(this.getCsdcDriver());
 		}
 		$.SetRequest("CsDriver$"+this.getCsdcDriver(), csDriver);
	 	return csDriver;
	}
	/*******************************车辆**********************************/	
	/**
	* 车辆 [可空]      
	**/
	public Long getCsdcCar(){
		return this.csdcCar;
	}
	/**
	* 获取车辆格式化(toString)
	**/
	public String getCsdcCar$(){
		String strValue="";
		if(this.getCsdcCar()!=null){
				strValue+=$.str(CsCarRes.getKeyValue(this.getCsdcCar()));
		}			
	 	 return strValue;
	}
	/**
	* 车辆 [可空]     
	**/
	public void setCsdcCar(Long csdcCar){
		this.csdcCar = csdcCar;
		this.setSeted(F.csdcCar);
	}
	/**
	* 获取关联对象[车辆]
	**/	 			
 	public CsCarRes get$csdcCar(){
 		com.ccclubs.model.CsCarRes csCarRes = (com.ccclubs.model.CsCarRes) $.GetRequest("CsCarRes$"+this.getCsdcCar());
 		if(csCarRes!=null)
			return csCarRes;
		if(this.getCsdcCar()!=null){
			csCarRes = CsCarRes.get(this.getCsdcCar());
 		}
 		$.SetRequest("CsCarRes$"+this.getCsdcCar(), csCarRes);
	 	return csCarRes;
	}
	/*******************************业务状态**********************************/	
	/**
	* 业务状态 [可空]      
	**/
	public Short getCsdcState(){
		return this.csdcState;
	}
	/**
	* 获取业务状态格式化(toString)
	**/
	public String getCsdcState$(){
		String strValue="";
		 if($.equals($.str(this.getCsdcState()),"1"))
			 strValue=$.str("已接单");
		 if($.equals($.str(this.getCsdcState()),"0"))
			 strValue=$.str("出车");
		 if($.equals($.str(this.getCsdcState()),"2"))
			 strValue=$.str("离线");
	 	 return strValue;
	}
	/**
	* 业务状态 [可空]     
	**/
	public void setCsdcState(Short csdcState){
		this.csdcState = csdcState;
		this.setSeted(F.csdcState);
	}
	/*******************************数据状态**********************************/	
	/**
	* 数据状态 [可空]      
	**/
	public Short getCsdcStatus(){
		return this.csdcStatus;
	}
	/**
	* 获取数据状态格式化(toString)
	**/
	public String getCsdcStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsdcStatus()),"1"))
			 strValue=$.str("正常");
		 if($.equals($.str(this.getCsdcStatus()),"0"))
			 strValue=$.str("禁用");
	 	 return strValue;
	}
	/**
	* 数据状态 [可空]     
	**/
	public void setCsdcStatus(Short csdcStatus){
		this.csdcStatus = csdcStatus;
		this.setSeted(F.csdcStatus);
	}
	/*******************************创建时间**********************************/	
	/**
	* 创建时间 [非空]      
	**/
	public Date getCsdcAddTime(){
		return this.csdcAddTime;
	}
	/**
	* 获取创建时间格式化(toString)
	**/
	public String getCsdcAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsdcAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 创建时间 [非空]     
	**/
	public void setCsdcAddTime(Date csdcAddTime){
		this.csdcAddTime = csdcAddTime;
		this.setSeted(F.csdcAddTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsdcUpdateTime(){
		return this.csdcUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsdcUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsdcUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCsdcUpdateTime(Date csdcUpdateTime){
		this.csdcUpdateTime = csdcUpdateTime;
		this.setSeted(F.csdcUpdateTime);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsDriverCar.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsDriverCar.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsDriverCar.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsDriverCar.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsDriverCar.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsDriverCar.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsDriverCar.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsDriverCar.this);
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
		public M csdcId(Object csdcId){this.put("csdcId", csdcId);return this;};
		/** and csdc_id is null */
		public M csdcIdNull(){if(this.get("csdcIdNot")==null)this.put("csdcIdNot", "");this.put("csdcId", null);return this;};
		/** not .... */
		public M csdcIdNot(){this.put("csdcIdNot", "not");return this;};
		/** 城市 [可空]       **/
		public M csdcHost(Object csdcHost){this.put("csdcHost", csdcHost);return this;};
		/** and csdc_host is null */
		public M csdcHostNull(){if(this.get("csdcHostNot")==null)this.put("csdcHostNot", "");this.put("csdcHost", null);return this;};
		/** not .... */
		public M csdcHostNot(){this.put("csdcHostNot", "not");return this;};
		/** 网点 [可空]       **/
		public M csdcOutlets(Object csdcOutlets){this.put("csdcOutlets", csdcOutlets);return this;};
		/** and csdc_outlets is null */
		public M csdcOutletsNull(){if(this.get("csdcOutletsNot")==null)this.put("csdcOutletsNot", "");this.put("csdcOutlets", null);return this;};
		/** not .... */
		public M csdcOutletsNot(){this.put("csdcOutletsNot", "not");return this;};
		public M csdcOutlets$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("csdcOutlets$on", value);
			return this;
		};	
		/** 司机 [可空]       **/
		public M csdcDriver(Object csdcDriver){this.put("csdcDriver", csdcDriver);return this;};
		/** and csdc_driver is null */
		public M csdcDriverNull(){if(this.get("csdcDriverNot")==null)this.put("csdcDriverNot", "");this.put("csdcDriver", null);return this;};
		/** not .... */
		public M csdcDriverNot(){this.put("csdcDriverNot", "not");return this;};
		public M csdcDriver$on(CsDriver.M value){
			this.put("CsDriver", value);
			this.put("csdcDriver$on", value);
			return this;
		};	
		/** 车辆 [可空]       **/
		public M csdcCar(Object csdcCar){this.put("csdcCar", csdcCar);return this;};
		/** and csdc_car is null */
		public M csdcCarNull(){if(this.get("csdcCarNot")==null)this.put("csdcCarNot", "");this.put("csdcCar", null);return this;};
		/** not .... */
		public M csdcCarNot(){this.put("csdcCarNot", "not");return this;};
		public M csdcCar$on(CsCarRes.M value){
			this.put("CsCarRes", value);
			this.put("csdcCar$on", value);
			return this;
		};	
		/** 业务状态 [可空]       **/
		public M csdcState(Object csdcState){this.put("csdcState", csdcState);return this;};
		/** and csdc_state is null */
		public M csdcStateNull(){if(this.get("csdcStateNot")==null)this.put("csdcStateNot", "");this.put("csdcState", null);return this;};
		/** not .... */
		public M csdcStateNot(){this.put("csdcStateNot", "not");return this;};
		/** 数据状态 [可空]       **/
		public M csdcStatus(Object csdcStatus){this.put("csdcStatus", csdcStatus);return this;};
		/** and csdc_status is null */
		public M csdcStatusNull(){if(this.get("csdcStatusNot")==null)this.put("csdcStatusNot", "");this.put("csdcStatus", null);return this;};
		/** not .... */
		public M csdcStatusNot(){this.put("csdcStatusNot", "not");return this;};
		/** 创建时间 [非空]       **/
		public M csdcAddTime(Object csdcAddTime){this.put("csdcAddTime", csdcAddTime);return this;};
		/** and csdc_add_time is null */
		public M csdcAddTimeNull(){if(this.get("csdcAddTimeNot")==null)this.put("csdcAddTimeNot", "");this.put("csdcAddTime", null);return this;};
		/** not .... */
		public M csdcAddTimeNot(){this.put("csdcAddTimeNot", "not");return this;};
		/** and csdc_add_time >= ? */
 		public M csdcAddTimeStart(Object start){this.put("csdcAddTimeStart", start);return this;};			
 		/** and csdc_add_time <= ? */
 		public M csdcAddTimeEnd(Object end){this.put("csdcAddTimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M csdcUpdateTime(Object csdcUpdateTime){this.put("csdcUpdateTime", csdcUpdateTime);return this;};
		/** and csdc_update_time is null */
		public M csdcUpdateTimeNull(){if(this.get("csdcUpdateTimeNot")==null)this.put("csdcUpdateTimeNot", "");this.put("csdcUpdateTime", null);return this;};
		/** not .... */
		public M csdcUpdateTimeNot(){this.put("csdcUpdateTimeNot", "not");return this;};
		/** and csdc_update_time >= ? */
 		public M csdcUpdateTimeStart(Object start){this.put("csdcUpdateTimeStart", start);return this;};			
 		/** and csdc_update_time <= ? */
 		public M csdcUpdateTimeEnd(Object end){this.put("csdcUpdateTimeEnd", end);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有司机车辆 **/
		public @api List<CsDriverCar> list(Integer size){
			return getCsDriverCarList(this,size);
		}
		/** 获取司机车辆分页 **/
		public @api Page<CsDriverCar> page(int page,int size){
			return getCsDriverCarPage(page, size , this);
		}
		/** 根据查询条件取司机车辆 **/
		public @api CsDriverCar get(){
			return getCsDriverCar(this);
		}
		/** 获取司机车辆数 **/
		public @api Long count(){
			return getCsDriverCarCount(this);
		}
		/** 获取司机车辆表达式 **/
		public @api <T> T eval(String strEval){
			return getCsDriverCarEval(strEval,this);
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
			updateCsDriverCar(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csdcId="csdcId";
		/** 城市 [可空]       **/
		public final static @type(Long.class)  String csdcHost="csdcHost";
		/** 网点 [可空]       **/
		public final static @type(Long.class)  String csdcOutlets="csdcOutlets";
		/** 司机 [可空]       **/
		public final static @type(Long.class)  String csdcDriver="csdcDriver";
		/** 车辆 [可空]       **/
		public final static @type(Long.class)  String csdcCar="csdcCar";
		/** 业务状态 [可空]       **/
		public final static @type(Short.class)  String csdcState="csdcState";
		/** 数据状态 [可空]       **/
		public final static @type(Short.class)  String csdcStatus="csdcStatus";
		/** 创建时间 [非空]       **/
		public final static @type(Date.class)  String csdcAddTime="csdcAddTime";
		/** and csdc_add_time >= ? */
 		public final static @type(Date.class) String csdcAddTimeStart="csdcAddTimeStart";
 		/** and csdc_add_time <= ? */
 		public final static @type(Date.class) String csdcAddTimeEnd="csdcAddTimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csdcUpdateTime="csdcUpdateTime";
		/** and csdc_update_time >= ? */
 		public final static @type(Date.class) String csdcUpdateTimeStart="csdcUpdateTimeStart";
 		/** and csdc_update_time <= ? */
 		public final static @type(Date.class) String csdcUpdateTimeEnd="csdcUpdateTimeEnd";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csdcId="csdc_id";
		/** 城市 [可空]       **/
		public final static String csdcHost="csdc_host";
		/** 网点 [可空]       **/
		public final static String csdcOutlets="csdc_outlets";
		/** 司机 [可空]       **/
		public final static String csdcDriver="csdc_driver";
		/** 车辆 [可空]       **/
		public final static String csdcCar="csdc_car";
		/** 业务状态 [可空]       **/
		public final static String csdcState="csdc_state";
		/** 数据状态 [可空]       **/
		public final static String csdcStatus="csdc_status";
		/** 创建时间 [非空]       **/
		public final static String csdcAddTime="csdc_add_time";
		/** 修改时间 [非空]       **/
		public final static String csdcUpdateTime="csdc_update_time";
		
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
				$.Set(name,CsDriverCar.getCsDriverCar(params));
			else
				$.Set(name,CsDriverCar.getCsDriverCarList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取司机车辆数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsDriverCar) $.GetRequest("CsDriverCar$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsDriverCar.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsDriverCar.getCsDriverCar(params);
			else
				value = CsDriverCar.getCsDriverCarList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsDriverCar.Get($.add(CsDriverCar.F.csdcId,param));
		else if(!$.empty(param.toString()))
			value = CsDriverCar.get(Long.valueOf(param.toString()));
		$.SetRequest("CsDriverCar$"+param.hashCode(), value);
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
	public void mergeSet(CsDriverCar old){
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