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

@namespace("systematic/event")
public @caption("系统事件") @table("cs_event") class CsEvent implements java.io.Serializable
{
	private static final long serialVersionUID =          1l;
	private @caption("编号") @column("cse_id")   @primary  @note("  ") Long cseId;// 主键 非空     
	private @caption("事件内容") @column("cse_content")    @note("  ") String cseContent;// 非空     
	private @caption("事件类型") @column("cse_type")    @relate("$cseType") @RelateClass(CsEventType.class)  @note("  ") Long cseType;// 非空     
 	private CsEventType $cseType;//关联对象[系统事件类型]
	private @caption("关联ID") @column("cse_data_id")    @note("  ") String cseDataId;//     存储格式:类名@ID,其中类名包括
	private Object $cseDataId;//泛关联对象，对象类名由cseDataId字段中标明
	private @caption("修改时间") @column("cse_update_time")    @note("  ") Date cseUpdateTime;// 非空     
	private @caption("添加时间") @column("cse_add_time")    @note("  ") Date cseAddTime;// 非空     
	private @caption("状态") @column("cse_status")    @note(" 1:正常 0:无效  ") Short cseStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsEvent(){
	
	}
	
	//主键构造函数
	public CsEvent(Long id){
		this.cseId = id;
	}
	
	/**所有字段构造函数 CsEvent(cseContent,cseType,cseDataId,cseUpdateTime,cseAddTime,cseStatus)
	 CsEvent(
	 	$.getString("cseContent")//事件内容 [非空]
	 	,$.getLong("cseType")//事件类型 [非空]
	 	,$.getString("cseDataId")//关联ID
	 	,$.getDate("cseUpdateTime")//修改时间 [非空]
	 	,$.getDate("cseAddTime")//添加时间 [非空]
	 	,$.getShort("cseStatus")//状态 [非空]
	 )
	**/
	public CsEvent(String cseContent,Long cseType,String cseDataId,Date cseUpdateTime,Date cseAddTime,Short cseStatus){
		this.cseContent=cseContent;
		this.cseType=cseType;
		this.cseDataId=cseDataId;
		this.cseUpdateTime=cseUpdateTime;
		this.cseAddTime=cseAddTime;
		this.cseStatus=cseStatus;
	}
	
	//设置非空字段
	public CsEvent setNotNull(String cseContent,Long cseType,Date cseUpdateTime,Date cseAddTime,Short cseStatus){
		this.cseContent=cseContent;
		this.cseType=cseType;
		this.cseUpdateTime=cseUpdateTime;
		this.cseAddTime=cseAddTime;
		this.cseStatus=cseStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsEvent cseId(Long cseId){
		this.cseId = cseId;
		this.setSeted(F.cseId);
		return this;
	}
	/** 事件内容 [非空]       **/
	public CsEvent cseContent(String cseContent){
		this.cseContent = cseContent;
		this.setSeted(F.cseContent);
		return this;
	}
	/** 事件类型 [非空] [CsEventType]      **/
	public CsEvent cseType(Long cseType){
		this.cseType = cseType;
		this.setSeted(F.cseType);
		return this;
	}
	/** 关联ID        **/
	public CsEvent cseDataId(String cseDataId){
		this.cseDataId = cseDataId;
		this.setSeted(F.cseDataId);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsEvent cseUpdateTime(Date cseUpdateTime){
		this.cseUpdateTime = cseUpdateTime;
		this.setSeted(F.cseUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsEvent cseAddTime(Date cseAddTime){
		this.cseAddTime = cseAddTime;
		this.setSeted(F.cseAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsEvent cseStatus(Short cseStatus){
		this.cseStatus = cseStatus;
		this.setSeted(F.cseStatus);
		return this;
	}
	
	
	//克隆对象
	public CsEvent clone(){
		CsEvent clone = new CsEvent();
		clone.cseContent=this.cseContent;
		clone.cseType=this.cseType;
		clone.cseUpdateTime=this.cseUpdateTime;
		clone.cseAddTime=this.cseAddTime;
		clone.cseStatus=this.cseStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取系统事件
	 * @param id
	 * @return
	 */
	public static @api CsEvent get(Long id){		
		return getCsEventById(id);
	}
	/**
	 * 获取所有系统事件
	 * @return
	 */
	public static @api List<CsEvent> list(Map params,Integer size){
		return getCsEventList(params,size);
	}
	/**
	 * 获取系统事件分页
	 * @return
	 */
	public static @api Page<CsEvent> page(int page,int size,Map params){
		return getCsEventPage(page, size , params);
	}
	/**
	 * 根据查询条件取系统事件
	 * @param params
	 * @return
	 */
	public static @api CsEvent Get(Map params){
		return getCsEvent(params);
	}
	/**
	 * 获取系统事件数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsEventCount(params);
	}
	/**
	 * 获取系统事件数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsEventEval(eval,params);
	}
	
	/**
	 * 根据ID取系统事件
	 * @param id
	 * @return
	 */
	public static @api CsEvent getCsEventById(Long id){		
		CsEvent csEvent = (CsEvent) $.GetRequest("CsEvent$"+id);
		if(csEvent!=null)
			return csEvent;
		com.ccclubs.service.admin.ICsEventService csEventService = $.GetSpringBean("csEventService");		
		return csEventService.getCsEventById(id);
	}
	
	
	/**
	 * 根据ID取系统事件的标识键值
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
		String keyValue = this.getCseContent$();
		if(!"CseContent".equals("CseContent"))
			keyValue+="("+this.getCseContent$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有系统事件
	 * @return
	 */
	public static @api List<CsEvent> getCsEventList(Map params,Integer size){
		com.ccclubs.service.admin.ICsEventService csEventService = $.GetSpringBean("csEventService");
		return csEventService.getCsEventList(params, size);
	}
	
	/**
	 * 获取系统事件分页
	 * @return
	 */
	public static @api Page<CsEvent> getCsEventPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsEventService csEventService = $.GetSpringBean("csEventService");
		return csEventService.getCsEventPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取系统事件
	 * @param params
	 * @return
	 */
	public static @api CsEvent getCsEvent(Map params){
		com.ccclubs.service.admin.ICsEventService csEventService = $.GetSpringBean("csEventService");
		return csEventService.getCsEvent(params);
	}
	
	/**
	 * 获取系统事件数
	 * @return
	 */
	public static @api Long getCsEventCount(Map params){
		com.ccclubs.service.admin.ICsEventService csEventService = $.GetSpringBean("csEventService");
		return csEventService.getCsEventCount(params);
	}
		
		
	/**
	 * 获取系统事件自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsEventEval(String eval,Map params){
		com.ccclubs.service.admin.ICsEventService csEventService = $.GetSpringBean("csEventService");
		return csEventService.getCsEventEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsEvent(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsEventService csEventService = $.GetSpringBean("csEventService");
		csEventService.updateCsEventByConfirm(set, where);
	}
	
	
	/**
	 * 保存系统事件对象
	 * @param params
	 * @return
	 */
	public CsEvent save(){
		com.ccclubs.service.admin.ICsEventService csEventService = $.GetSpringBean("csEventService");
		if(this.getCseId()!=null)
			csEventService.updateCsEvent(this);
		else
			return csEventService.saveCsEvent(this);
		return this;
	}
	
	
	/**
	 * 更新系统事件对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsEventService csEventService = $.GetSpringBean("csEventService");
		csEventService.updateCsEvent$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsEventService csEventService = $.GetSpringBean("csEventService");
		if($.equals($.config("logic_delete"),"true"))
			csEventService.removeCsEventById(this.getCseId());
		else
			csEventService.deleteCsEventById(this.getCseId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsEventService csEventService = $.GetSpringBean("csEventService");
		return csEventService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCseId(){
		return this.cseId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCseId$(){
		String strValue="";
		 strValue=$.str(this.getCseId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCseId(Long cseId){
		this.cseId = cseId;
		this.setSeted(F.cseId);
	}
	/*******************************事件内容**********************************/	
	/**
	* 事件内容 [非空]      
	**/
	public String getCseContent(){
		return this.cseContent;
	}
	/**
	* 获取事件内容格式化(toString)
	**/
	public String getCseContent$(){
		String strValue="";
		 strValue=$.str(this.getCseContent());
	 	 return strValue;
	}
	/**
	* 事件内容 [非空]      
	**/
	public void setCseContent(String cseContent){
		this.cseContent = cseContent;
		this.setSeted(F.cseContent);
	}
	/*******************************事件类型**********************************/	
	/**
	* 事件类型 [非空] [CsEventType]     
	**/
	public Long getCseType(){
		return this.cseType;
	}
	/**
	* 获取事件类型格式化(toString)
	**/
	public String getCseType$(){
		String strValue="";
		if(this.getCseType()!=null){
				strValue+=$.str(CsEventType.getKeyValue(this.getCseType()));
		}			
	 	 return strValue;
	}
	/**
	* 事件类型 [非空] [CsEventType]     
	**/
	public void setCseType(Long cseType){
		this.cseType = cseType;
		this.setSeted(F.cseType);
	}
	/**
	* 获取关联对象[系统事件类型]
	**/	 			
 	public CsEventType get$cseType(){
 		com.ccclubs.model.CsEventType csEventType = (com.ccclubs.model.CsEventType) $.GetRequest("CsEventType$"+this.getCseType());
 		if(csEventType!=null)
			return csEventType;
		if(this.getCseType()!=null){
 			csEventType = CsEventType.get(this.getCseType());
 		}
 		$.SetRequest("CsEventType$"+this.getCseType(), csEventType);
	 	return csEventType;
	}
	/*******************************关联ID**********************************/	
	/**
	* 关联ID       
	**/
	public String getCseDataId(){
		return this.cseDataId;
	}
	/**
	* 获取关联ID格式化(toString)
	**/
	public String getCseDataId$(){
		String strValue="";
	 	 Object object = this.get$cseDataId();
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
	* 关联ID       
	**/
	public void setCseDataId(String cseDataId){
		this.cseDataId = cseDataId;
		this.setSeted(F.cseDataId);
	}
	/**
	* 泛关联对象，对象类名由cseDataId字段中标明
	**/	 			
 	public <T> T get$cseDataId(){
		if($.empty(this.cseDataId))
 			return null;
 		try{
	 		String ClassName = this.cseDataId.split("@")[0];
	 		Long id = Long.parseLong(this.cseDataId.split("@")[1]);
	 		Class<?> cls = Class.forName("com.ccclubs.model."+ClassName);
	 		return (T)cls.getMethod("get", Long.class).invoke(null, id);	
 		}catch(Exception ex){
 			ex.printStackTrace();
 			return null;
 		}
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCseUpdateTime(){
		return this.cseUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCseUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCseUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCseUpdateTime(Date cseUpdateTime){
		this.cseUpdateTime = cseUpdateTime;
		this.setSeted(F.cseUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCseAddTime(){
		return this.cseAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCseAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCseAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCseAddTime(Date cseAddTime){
		this.cseAddTime = cseAddTime;
		this.setSeted(F.cseAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCseStatus(){
		return this.cseStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCseStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCseStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCseStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCseStatus(Short cseStatus){
		this.cseStatus = cseStatus;
		this.setSeted(F.cseStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsEvent.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsEvent.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsEvent.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsEvent.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsEvent.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsEvent.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsEvent.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsEvent.this);
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
		public M cseId(Object cseId){this.put("cseId", cseId);return this;};
	 	/** and cse_id is null */
 		public M cseIdNull(){if(this.get("cseIdNot")==null)this.put("cseIdNot", "");this.put("cseId", null);return this;};
 		/** not .... */
 		public M cseIdNot(){this.put("cseIdNot", "not");return this;};
		/** 事件内容 [非空]       **/
		public M cseContent(Object cseContent){this.put("cseContent", cseContent);return this;};
	 	/** and cse_content is null */
 		public M cseContentNull(){if(this.get("cseContentNot")==null)this.put("cseContentNot", "");this.put("cseContent", null);return this;};
 		/** not .... */
 		public M cseContentNot(){this.put("cseContentNot", "not");return this;};
		/** 事件类型 [非空] [CsEventType]      **/
		public M cseType(Object cseType){this.put("cseType", cseType);return this;};
	 	/** and cse_type is null */
 		public M cseTypeNull(){if(this.get("cseTypeNot")==null)this.put("cseTypeNot", "");this.put("cseType", null);return this;};
 		/** not .... */
 		public M cseTypeNot(){this.put("cseTypeNot", "not");return this;};
		public M cseType$on(CsEventType.M value){
			this.put("CsEventType", value);
			this.put("cseType$on", value);
			return this;
		};	
		/** 关联ID        **/
		public M cseDataId(Object cseDataId){this.put("cseDataId", cseDataId);return this;};
	 	/** and cse_data_id is null */
 		public M cseDataIdNull(){if(this.get("cseDataIdNot")==null)this.put("cseDataIdNot", "");this.put("cseDataId", null);return this;};
 		/** not .... */
 		public M cseDataIdNot(){this.put("cseDataIdNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M cseUpdateTime(Object cseUpdateTime){this.put("cseUpdateTime", cseUpdateTime);return this;};
	 	/** and cse_update_time is null */
 		public M cseUpdateTimeNull(){if(this.get("cseUpdateTimeNot")==null)this.put("cseUpdateTimeNot", "");this.put("cseUpdateTime", null);return this;};
 		/** not .... */
 		public M cseUpdateTimeNot(){this.put("cseUpdateTimeNot", "not");return this;};
 		/** and cse_update_time >= ? */
 		public M cseUpdateTimeStart(Object start){this.put("cseUpdateTimeStart", start);return this;};			
 		/** and cse_update_time <= ? */
 		public M cseUpdateTimeEnd(Object end){this.put("cseUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cseAddTime(Object cseAddTime){this.put("cseAddTime", cseAddTime);return this;};
	 	/** and cse_add_time is null */
 		public M cseAddTimeNull(){if(this.get("cseAddTimeNot")==null)this.put("cseAddTimeNot", "");this.put("cseAddTime", null);return this;};
 		/** not .... */
 		public M cseAddTimeNot(){this.put("cseAddTimeNot", "not");return this;};
 		/** and cse_add_time >= ? */
 		public M cseAddTimeStart(Object start){this.put("cseAddTimeStart", start);return this;};			
 		/** and cse_add_time <= ? */
 		public M cseAddTimeEnd(Object end){this.put("cseAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M cseStatus(Object cseStatus){this.put("cseStatus", cseStatus);return this;};
	 	/** and cse_status is null */
 		public M cseStatusNull(){if(this.get("cseStatusNot")==null)this.put("cseStatusNot", "");this.put("cseStatus", null);return this;};
 		/** not .... */
 		public M cseStatusNot(){this.put("cseStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有系统事件 **/
		public @api List<CsEvent> list(Integer size){
			return getCsEventList(this,size);
		}
		/** 获取系统事件分页 **/
		public @api Page<CsEvent> page(int page,int size){
			return getCsEventPage(page, size , this);
		}
		/** 根据查询条件取系统事件 **/
		public @api CsEvent get(){
			return getCsEvent(this);
		}
		/** 获取系统事件数 **/
		public @api Long count(){
			return getCsEventCount(this);
		}
		/** 获取系统事件表达式 **/
		public @api <T> T eval(String strEval){
			return getCsEventEval(strEval,this);
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
			updateCsEvent(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cseId="cseId";
		/** 事件内容 [非空]       **/
		public final static @type(String.class) @like String cseContent="cseContent";
		/** 事件类型 [非空] [CsEventType]      **/
		public final static @type(Long.class)  String cseType="cseType";
		/** 关联ID        **/
		public final static @type(String.class)  String cseDataId="cseDataId";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cseUpdateTime="cseUpdateTime";
	 	/** and cse_update_time >= ? */
 		public final static @type(Date.class) String cseUpdateTimeStart="cseUpdateTimeStart";
 		/** and cse_update_time <= ? */
 		public final static @type(Date.class) String cseUpdateTimeEnd="cseUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cseAddTime="cseAddTime";
	 	/** and cse_add_time >= ? */
 		public final static @type(Date.class) String cseAddTimeStart="cseAddTimeStart";
 		/** and cse_add_time <= ? */
 		public final static @type(Date.class) String cseAddTimeEnd="cseAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String cseStatus="cseStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cseId="cse_id";
		/** 事件内容 [非空]       **/
		public final static String cseContent="cse_content";
		/** 事件类型 [非空] [CsEventType]      **/
		public final static String cseType="cse_type";
		/** 关联ID        **/
		public final static String cseDataId="cse_data_id";
		/** 修改时间 [非空]       **/
		public final static String cseUpdateTime="cse_update_time";
		/** 添加时间 [非空]       **/
		public final static String cseAddTime="cse_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String cseStatus="cse_status";
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
				$.Set(name,CsEvent.getCsEvent(params));
			else
				$.Set(name,CsEvent.getCsEventList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取系统事件数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsEvent) $.GetRequest("CsEvent$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsEvent.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsEvent.getCsEvent(params);
			else
				value = CsEvent.getCsEventList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsEvent.Get($.add(CsEvent.F.cseId,param));
		else if(!$.empty(param.toString()))
			value = CsEvent.get(Long.valueOf(param.toString()));
		$.SetRequest("CsEvent$"+param.hashCode(), value);
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
	public void mergeSet(CsEvent old){
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