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

@namespace("control/alarm")
public @caption("车辆警报") @table("cs_alarm") class CsAlarm implements java.io.Serializable
{
	private static final long serialVersionUID =          1l;
	private @caption("编号") @column("csa_id")   @primary  @note("  ") Long csaId;// 主键 非空     
	private @caption("城市") @column("csa_host")    @relate("$csaHost") @RelateClass(SrvHost.class)  @note("  ") Long csaHost;// 非空     
 	private SrvHost $csaHost;//关联对象[运营城市]
	private @caption("车牌号") @column("csa_number")    @note("  ") String csaNumber;// 非空    ${basePath}${proname}/object/car_numbers.do 
	private @caption("接收时间") @column("csa_add_time")    @note("  ") Date csaAddTime;// 非空     
	private @caption("报警信息") @column("csa_info")    @note(" 1:正常 0:无效  ") String csaInfo;// 非空 1:正常 0:无效     
	private @caption("当前订单") @column("csa_order")    @relate("$csaOrder") @RelateClass(CsOrder.class)  @note("  ") Long csaOrder;// 非空     
 	private CsOrder $csaOrder;//关联对象[系统订单]
	
	//默认构造函数
	public CsAlarm(){
	
	}
	
	//主键构造函数
	public CsAlarm(Long id){
		this.csaId = id;
	}
	
	/**所有字段构造函数 CsAlarm(csaHost,csaNumber,csaAddTime,csaInfo,csaOrder)
	 CsAlarm(
	 	$.getLong("csaHost")//城市 [非空]
	 	,$.getString("csaNumber")//车牌号 [非空]
	 	,$.getDate("csaAddTime")//接收时间 [非空]
	 	,$.getString("csaInfo")//报警信息 [非空]
	 	,$.getLong("csaOrder")//当前订单 [非空]
	 )
	**/
	public CsAlarm(Long csaHost,String csaNumber,Date csaAddTime,String csaInfo,Long csaOrder){
		this.csaHost=csaHost;
		this.csaNumber=csaNumber;
		this.csaAddTime=csaAddTime;
		this.csaInfo=csaInfo;
		this.csaOrder=csaOrder;
	}
	
	//设置非空字段
	public CsAlarm setNotNull(Long csaHost,String csaNumber,Date csaAddTime,String csaInfo,Long csaOrder){
		this.csaHost=csaHost;
		this.csaNumber=csaNumber;
		this.csaAddTime=csaAddTime;
		this.csaInfo=csaInfo;
		this.csaOrder=csaOrder;
		return this;
	}
	/** 编号 [非空]       **/
	public CsAlarm csaId(Long csaId){
		this.csaId = csaId;
		this.setSeted(F.csaId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsAlarm csaHost(Long csaHost){
		this.csaHost = csaHost;
		this.setSeted(F.csaHost);
		return this;
	}
	/** 车牌号 [非空]      ${basePath}${proname}/object/car_numbers.do **/
	public CsAlarm csaNumber(String csaNumber){
		this.csaNumber = csaNumber;
		this.setSeted(F.csaNumber);
		return this;
	}
	/** 接收时间 [非空]       **/
	public CsAlarm csaAddTime(Date csaAddTime){
		this.csaAddTime = csaAddTime;
		this.setSeted(F.csaAddTime);
		return this;
	}
	/** 报警信息 [非空]   1:正常 0:无效     **/
	public CsAlarm csaInfo(String csaInfo){
		this.csaInfo = csaInfo;
		this.setSeted(F.csaInfo);
		return this;
	}
	/** 当前订单 [非空] [CsOrder]      **/
	public CsAlarm csaOrder(Long csaOrder){
		this.csaOrder = csaOrder;
		this.setSeted(F.csaOrder);
		return this;
	}
	
	
	//克隆对象
	public CsAlarm clone(){
		CsAlarm clone = new CsAlarm();
		clone.csaHost=this.csaHost;
		clone.csaNumber=this.csaNumber;
		clone.csaAddTime=this.csaAddTime;
		clone.csaInfo=this.csaInfo;
		clone.csaOrder=this.csaOrder;
		return clone;
	}
	
	
	/**
	 * 根据ID取车辆警报
	 * @param id
	 * @return
	 */
	public static @api CsAlarm get(Long id){		
		return getCsAlarmById(id);
	}
	/**
	 * 获取所有车辆警报
	 * @return
	 */
	public static @api List<CsAlarm> list(Map params,Integer size){
		return getCsAlarmList(params,size);
	}
	/**
	 * 获取车辆警报分页
	 * @return
	 */
	public static @api Page<CsAlarm> page(int page,int size,Map params){
		return getCsAlarmPage(page, size , params);
	}
	/**
	 * 根据查询条件取车辆警报
	 * @param params
	 * @return
	 */
	public static @api CsAlarm Get(Map params){
		return getCsAlarm(params);
	}
	/**
	 * 获取车辆警报数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsAlarmCount(params);
	}
	/**
	 * 获取车辆警报数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsAlarmEval(eval,params);
	}
	
	/**
	 * 根据ID取车辆警报
	 * @param id
	 * @return
	 */
	public static @api CsAlarm getCsAlarmById(Long id){		
		CsAlarm csAlarm = (CsAlarm) $.GetRequest("CsAlarm$"+id);
		if(csAlarm!=null)
			return csAlarm;
		com.ccclubs.service.admin.ICsAlarmService csAlarmService = $.GetSpringBean("csAlarmService");		
		return csAlarmService.getCsAlarmById(id);
	}
	
	
	/**
	 * 根据ID取车辆警报的标识键值
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
		String keyValue = this.getCsaInfo$();
		if(!"CsaInfo".equals("CsaInfo"))
			keyValue+="("+this.getCsaInfo$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有车辆警报
	 * @return
	 */
	public static @api List<CsAlarm> getCsAlarmList(Map params,Integer size){
		com.ccclubs.service.admin.ICsAlarmService csAlarmService = $.GetSpringBean("csAlarmService");
		return csAlarmService.getCsAlarmList(params, size);
	}
	
	/**
	 * 获取车辆警报分页
	 * @return
	 */
	public static @api Page<CsAlarm> getCsAlarmPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsAlarmService csAlarmService = $.GetSpringBean("csAlarmService");
		return csAlarmService.getCsAlarmPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取车辆警报
	 * @param params
	 * @return
	 */
	public static @api CsAlarm getCsAlarm(Map params){
		com.ccclubs.service.admin.ICsAlarmService csAlarmService = $.GetSpringBean("csAlarmService");
		return csAlarmService.getCsAlarm(params);
	}
	
	/**
	 * 获取车辆警报数
	 * @return
	 */
	public static @api Long getCsAlarmCount(Map params){
		com.ccclubs.service.admin.ICsAlarmService csAlarmService = $.GetSpringBean("csAlarmService");
		return csAlarmService.getCsAlarmCount(params);
	}
		
		
	/**
	 * 获取车辆警报自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsAlarmEval(String eval,Map params){
		com.ccclubs.service.admin.ICsAlarmService csAlarmService = $.GetSpringBean("csAlarmService");
		return csAlarmService.getCsAlarmEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsAlarm(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsAlarmService csAlarmService = $.GetSpringBean("csAlarmService");
		csAlarmService.updateCsAlarmByConfirm(set, where);
	}
	
	
	/**
	 * 保存车辆警报对象
	 * @param params
	 * @return
	 */
	public CsAlarm save(){
		com.ccclubs.service.admin.ICsAlarmService csAlarmService = $.GetSpringBean("csAlarmService");
		if(this.getCsaId()!=null)
			csAlarmService.updateCsAlarm(this);
		else
			return csAlarmService.saveCsAlarm(this);
		return this;
	}
	
	
	/**
	 * 更新车辆警报对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsAlarmService csAlarmService = $.GetSpringBean("csAlarmService");
		csAlarmService.updateCsAlarm$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsAlarmService csAlarmService = $.GetSpringBean("csAlarmService");
			csAlarmService.deleteCsAlarmById(this.getCsaId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsAlarmService csAlarmService = $.GetSpringBean("csAlarmService");
		return csAlarmService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsaId(){
		return this.csaId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsaId$(){
		String strValue="";
		 strValue=$.str(this.getCsaId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsaId(Long csaId){
		this.csaId = csaId;
		this.setSeted(F.csaId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsaHost(){
		return this.csaHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsaHost$(){
		String strValue="";
		if(this.getCsaHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsaHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsaHost(Long csaHost){
		this.csaHost = csaHost;
		this.setSeted(F.csaHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csaHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsaHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsaHost()!=null){
 			srvHost = SrvHost.get(this.getCsaHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsaHost(), srvHost);
	 	return srvHost;
	}
	/*******************************车牌号**********************************/	
	/**
	* 车牌号 [非空]      ${basePath}${proname}/object/car_numbers.do
	**/
	public String getCsaNumber(){
		return this.csaNumber;
	}
	/**
	* 获取车牌号格式化(toString)
	**/
	public String getCsaNumber$(){
		String strValue="";
		 strValue=$.str(this.getCsaNumber());	
	 	 return strValue;
	}
	/**
	* 车牌号 [非空]      ${basePath}${proname}/object/car_numbers.do
	**/
	public void setCsaNumber(String csaNumber){
		this.csaNumber = csaNumber;
		this.setSeted(F.csaNumber);
	}
	/*******************************接收时间**********************************/	
	/**
	* 接收时间 [非空]      
	**/
	public Date getCsaAddTime(){
		return this.csaAddTime;
	}
	/**
	* 获取接收时间格式化(toString)
	**/
	public String getCsaAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsaAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 接收时间 [非空]      
	**/
	public void setCsaAddTime(Date csaAddTime){
		this.csaAddTime = csaAddTime;
		this.setSeted(F.csaAddTime);
	}
	/*******************************报警信息**********************************/	
	/**
	* 报警信息 [非空]   1:正常 0:无效    
	**/
	public String getCsaInfo(){
		return this.csaInfo;
	}
	/**
	* 获取报警信息格式化(toString)
	**/
	public String getCsaInfo$(){
		String strValue="";
		 strValue=$.str(this.getCsaInfo());
	 	 return strValue;
	}
	/**
	* 报警信息 [非空]   1:正常 0:无效    
	**/
	public void setCsaInfo(String csaInfo){
		this.csaInfo = csaInfo;
		this.setSeted(F.csaInfo);
	}
	/*******************************当前订单**********************************/	
	/**
	* 当前订单 [非空] [CsOrder]     
	**/
	public Long getCsaOrder(){
		return this.csaOrder;
	}
	/**
	* 获取当前订单格式化(toString)
	**/
	public String getCsaOrder$(){
		String strValue="";
		if(this.getCsaOrder()!=null){
				strValue+=$.str(CsOrder.getKeyValue(this.getCsaOrder()));
		}			
	 	 return strValue;
	}
	/**
	* 当前订单 [非空] [CsOrder]     
	**/
	public void setCsaOrder(Long csaOrder){
		this.csaOrder = csaOrder;
		this.setSeted(F.csaOrder);
	}
	/**
	* 获取关联对象[系统订单]
	**/	 			
 	public CsOrder get$csaOrder(){
 		com.ccclubs.model.CsOrder csOrder = (com.ccclubs.model.CsOrder) $.GetRequest("CsOrder$"+this.getCsaOrder());
 		if(csOrder!=null)
			return csOrder;
		if(this.getCsaOrder()!=null){
 			csOrder = CsOrder.get(this.getCsaOrder());
 		}
 		$.SetRequest("CsOrder$"+this.getCsaOrder(), csOrder);
	 	return csOrder;
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsAlarm.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsAlarm.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsAlarm.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsAlarm.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsAlarm.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsAlarm.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsAlarm.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsAlarm.this);
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
		public M csaId(Object csaId){this.put("csaId", csaId);return this;};
	 	/** and csa_id is null */
 		public M csaIdNull(){if(this.get("csaIdNot")==null)this.put("csaIdNot", "");this.put("csaId", null);return this;};
 		/** not .... */
 		public M csaIdNot(){this.put("csaIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csaHost(Object csaHost){this.put("csaHost", csaHost);return this;};
	 	/** and csa_host is null */
 		public M csaHostNull(){if(this.get("csaHostNot")==null)this.put("csaHostNot", "");this.put("csaHost", null);return this;};
 		/** not .... */
 		public M csaHostNot(){this.put("csaHostNot", "not");return this;};
		/** 车牌号 [非空]      ${basePath}${proname}/object/car_numbers.do **/
		public M csaNumber(Object csaNumber){this.put("csaNumber", csaNumber);return this;};
	 	/** and csa_number is null */
 		public M csaNumberNull(){if(this.get("csaNumberNot")==null)this.put("csaNumberNot", "");this.put("csaNumber", null);return this;};
 		/** not .... */
 		public M csaNumberNot(){this.put("csaNumberNot", "not");return this;};
		/** 接收时间 [非空]       **/
		public M csaAddTime(Object csaAddTime){this.put("csaAddTime", csaAddTime);return this;};
	 	/** and csa_add_time is null */
 		public M csaAddTimeNull(){if(this.get("csaAddTimeNot")==null)this.put("csaAddTimeNot", "");this.put("csaAddTime", null);return this;};
 		/** not .... */
 		public M csaAddTimeNot(){this.put("csaAddTimeNot", "not");return this;};
 		/** and csa_add_time >= ? */
 		public M csaAddTimeStart(Object start){this.put("csaAddTimeStart", start);return this;};			
 		/** and csa_add_time <= ? */
 		public M csaAddTimeEnd(Object end){this.put("csaAddTimeEnd", end);return this;};
		/** 报警信息 [非空]   1:正常 0:无效     **/
		public M csaInfo(Object csaInfo){this.put("csaInfo", csaInfo);return this;};
	 	/** and csa_info is null */
 		public M csaInfoNull(){if(this.get("csaInfoNot")==null)this.put("csaInfoNot", "");this.put("csaInfo", null);return this;};
 		/** not .... */
 		public M csaInfoNot(){this.put("csaInfoNot", "not");return this;};
		/** 当前订单 [非空] [CsOrder]      **/
		public M csaOrder(Object csaOrder){this.put("csaOrder", csaOrder);return this;};
	 	/** and csa_order is null */
 		public M csaOrderNull(){if(this.get("csaOrderNot")==null)this.put("csaOrderNot", "");this.put("csaOrder", null);return this;};
 		/** not .... */
 		public M csaOrderNot(){this.put("csaOrderNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有车辆警报 **/
		public @api List<CsAlarm> list(Integer size){
			return getCsAlarmList(this,size);
		}
		/** 获取车辆警报分页 **/
		public @api Page<CsAlarm> page(int page,int size){
			return getCsAlarmPage(page, size , this);
		}
		/** 根据查询条件取车辆警报 **/
		public @api CsAlarm get(){
			return getCsAlarm(this);
		}
		/** 获取车辆警报数 **/
		public @api Long count(){
			return getCsAlarmCount(this);
		}
		/** 获取车辆警报表达式 **/
		public @api <T> T eval(String strEval){
			return getCsAlarmEval(strEval,this);
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
			updateCsAlarm(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csaId="csaId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csaHost="csaHost";
		/** 车牌号 [非空]      ${basePath}${proname}/object/car_numbers.do **/
		public final static @type(String.class)  String csaNumber="csaNumber";
		/** 接收时间 [非空]       **/
		public final static @type(Date.class)  String csaAddTime="csaAddTime";
	 	/** and csa_add_time >= ? */
 		public final static @type(Date.class) String csaAddTimeStart="csaAddTimeStart";
 		/** and csa_add_time <= ? */
 		public final static @type(Date.class) String csaAddTimeEnd="csaAddTimeEnd";
		/** 报警信息 [非空]   1:正常 0:无效     **/
		public final static @type(String.class) @like String csaInfo="csaInfo";
		/** 当前订单 [非空] [CsOrder]      **/
		public final static @type(Long.class)  String csaOrder="csaOrder";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csaId="csa_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csaHost="csa_host";
		/** 车牌号 [非空]      ${basePath}${proname}/object/car_numbers.do **/
		public final static String csaNumber="csa_number";
		/** 接收时间 [非空]       **/
		public final static String csaAddTime="csa_add_time";
		/** 报警信息 [非空]   1:正常 0:无效     **/
		public final static String csaInfo="csa_info";
		/** 当前订单 [非空] [CsOrder]      **/
		public final static String csaOrder="csa_order";
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
				$.Set(name,CsAlarm.getCsAlarm(params));
			else
				$.Set(name,CsAlarm.getCsAlarmList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取车辆警报数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsAlarm) $.GetRequest("CsAlarm$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsAlarm.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsAlarm.getCsAlarm(params);
			else
				value = CsAlarm.getCsAlarmList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsAlarm.Get($.add(CsAlarm.F.csaId,param));
		else if(!$.empty(param.toString()))
			value = CsAlarm.get(Long.valueOf(param.toString()));
		$.SetRequest("CsAlarm$"+param.hashCode(), value);
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
	public void mergeSet(CsAlarm old){
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