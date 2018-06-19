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

@namespace("systematic/log")
public @caption("系统日志") @table("cs_log") class CsLog implements java.io.Serializable
{
	private static final long serialVersionUID =          1l;
	private @caption("编号") @column("csl_id")   @primary  @note("  ") Long cslId;// 主键 非空     
	private @caption("域") @column("csl_host")    @relate("$cslHost") @RelateClass(SrvHost.class)  @note("  ") Long cslHost;//     
 	private SrvHost $cslHost;//关联对象[运营城市]
	private @caption("操作名称") @column("csl_name")    @note("  ") String cslName;// 非空     
	private @caption("操作模块") @column("csl_model")    @relate("$cslModel") @RelateClass(SrvProperty.class)  @note(" model_class:系统数据模型类  ") Long cslModel;// model_class:系统数据模型类     
 	private SrvProperty $cslModel;//关联对象[系统属性]
	private @caption("操作类型") @column("csl_type")    @relate("$cslType") @RelateClass(SrvProperty.class)  @note(" log_type:日志操作类型  ") Long cslType;// log_type:日志操作类型     
 	private SrvProperty $cslType;//关联对象[系统属性]
	private @caption("操作人") @column("csl_user")    @relate("$cslUser") @RelateClass(SrvUser.class)  @note("  ") Long cslUser;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cslUser;//关联对象[用户]
	private @caption("模块类名") @column("csl_class")    @note("  ") String cslClass;//     
	private @caption("数据ID") @column("csl_relate")    @note("  ") Long cslRelate;//     
	private @caption("数据状态") @column("csl_detail")    @note("  ") String cslDetail;//     
	private @caption("操作时间") @column("csl_add_time")    @note("  ") Date cslAddTime;// 非空     
	
	//默认构造函数
	public CsLog(){
	
	}
	
	//主键构造函数
	public CsLog(Long id){
		this.cslId = id;
	}
	
	/**所有字段构造函数 CsLog(cslHost,cslName,cslModel,cslType,cslUser,cslClass,cslRelate,cslDetail,cslAddTime)
	 CsLog(
	 	$.getLong("cslHost")//域
	 	,$.getString("cslName")//操作名称 [非空]
	 	,$.getLong("cslModel")//操作模块
	 	,$.getLong("cslType")//操作类型
	 	,$.getLong("cslUser")//操作人 [非空]
	 	,$.getString("cslClass")//模块类名
	 	,$.getLong("cslRelate")//数据ID
	 	,$.getString("cslDetail")//数据状态
	 	,$.getDate("cslAddTime")//操作时间 [非空]
	 )
	**/
	public CsLog(Long cslHost,String cslName,Long cslModel,Long cslType,Long cslUser,String cslClass,Long cslRelate,String cslDetail,Date cslAddTime){
		this.cslHost=cslHost;
		this.cslName=cslName;
		this.cslModel=cslModel;
		this.cslType=cslType;
		this.cslUser=cslUser;
		this.cslClass=cslClass;
		this.cslRelate=cslRelate;
		this.cslDetail=cslDetail;
		this.cslAddTime=cslAddTime;
	}
	
	//设置非空字段
	public CsLog setNotNull(String cslName,Long cslUser,Date cslAddTime){
		this.cslName=cslName;
		this.cslUser=cslUser;
		this.cslAddTime=cslAddTime;
		return this;
	}
	/** 编号 [非空]       **/
	public CsLog cslId(Long cslId){
		this.cslId = cslId;
		this.setSeted(F.cslId);
		return this;
	}
	/** 域  [SrvHost]      **/
	public CsLog cslHost(Long cslHost){
		this.cslHost = cslHost;
		this.setSeted(F.cslHost);
		return this;
	}
	/** 操作名称 [非空]       **/
	public CsLog cslName(String cslName){
		this.cslName = cslName;
		this.setSeted(F.cslName);
		return this;
	}
	/** 操作模块  [SrvProperty]  model_class:系统数据模型类     **/
	public CsLog cslModel(Long cslModel){
		this.cslModel = cslModel;
		this.setSeted(F.cslModel);
		return this;
	}
	/** 操作类型  [SrvProperty]  log_type:日志操作类型     **/
	public CsLog cslType(Long cslType){
		this.cslType = cslType;
		this.setSeted(F.cslType);
		return this;
	}
	/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsLog cslUser(Long cslUser){
		this.cslUser = cslUser;
		this.setSeted(F.cslUser);
		return this;
	}
	/** 模块类名        **/
	public CsLog cslClass(String cslClass){
		this.cslClass = cslClass;
		this.setSeted(F.cslClass);
		return this;
	}
	/** 数据ID        **/
	public CsLog cslRelate(Long cslRelate){
		this.cslRelate = cslRelate;
		this.setSeted(F.cslRelate);
		return this;
	}
	/** 数据状态        **/
	public CsLog cslDetail(String cslDetail){
		this.cslDetail = cslDetail;
		this.setSeted(F.cslDetail);
		return this;
	}
	/** 操作时间 [非空]       **/
	public CsLog cslAddTime(Date cslAddTime){
		this.cslAddTime = cslAddTime;
		this.setSeted(F.cslAddTime);
		return this;
	}
	
	
	//克隆对象
	public CsLog clone(){
		CsLog clone = new CsLog();
		clone.cslName=this.cslName;
		clone.cslUser=this.cslUser;
		clone.cslAddTime=this.cslAddTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取系统日志
	 * @param id
	 * @return
	 */
	public static @api CsLog get(Long id){		
		return getCsLogById(id);
	}
	/**
	 * 获取所有系统日志
	 * @return
	 */
	public static @api List<CsLog> list(Map params,Integer size){
		return getCsLogList(params,size);
	}
	/**
	 * 获取系统日志分页
	 * @return
	 */
	public static @api Page<CsLog> page(int page,int size,Map params){
		return getCsLogPage(page, size , params);
	}
	/**
	 * 根据查询条件取系统日志
	 * @param params
	 * @return
	 */
	public static @api CsLog Get(Map params){
		return getCsLog(params);
	}
	/**
	 * 获取系统日志数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsLogCount(params);
	}
	/**
	 * 获取系统日志数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsLogEval(eval,params);
	}
	
	/**
	 * 根据ID取系统日志
	 * @param id
	 * @return
	 */
	public static @api CsLog getCsLogById(Long id){		
		CsLog csLog = (CsLog) $.GetRequest("CsLog$"+id);
		if(csLog!=null)
			return csLog;
		com.ccclubs.service.admin.ICsLogService csLogService = $.GetSpringBean("csLogService");		
		return csLogService.getCsLogById(id);
	}
	
	
	/**
	 * 根据ID取系统日志的标识键值
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
		String keyValue = this.getCslName$();
		if(!"CslName".equals("CslName"))
			keyValue+="("+this.getCslName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有系统日志
	 * @return
	 */
	public static @api List<CsLog> getCsLogList(Map params,Integer size){
		com.ccclubs.service.admin.ICsLogService csLogService = $.GetSpringBean("csLogService");
		return csLogService.getCsLogList(params, size);
	}
	
	/**
	 * 获取系统日志分页
	 * @return
	 */
	public static @api Page<CsLog> getCsLogPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsLogService csLogService = $.GetSpringBean("csLogService");
		return csLogService.getCsLogPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取系统日志
	 * @param params
	 * @return
	 */
	public static @api CsLog getCsLog(Map params){
		com.ccclubs.service.admin.ICsLogService csLogService = $.GetSpringBean("csLogService");
		return csLogService.getCsLog(params);
	}
	
	/**
	 * 获取系统日志数
	 * @return
	 */
	public static @api Long getCsLogCount(Map params){
		com.ccclubs.service.admin.ICsLogService csLogService = $.GetSpringBean("csLogService");
		return csLogService.getCsLogCount(params);
	}
		
		
	/**
	 * 获取系统日志自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsLogEval(String eval,Map params){
		com.ccclubs.service.admin.ICsLogService csLogService = $.GetSpringBean("csLogService");
		return csLogService.getCsLogEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsLog(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsLogService csLogService = $.GetSpringBean("csLogService");
		csLogService.updateCsLogByConfirm(set, where);
	}
	
	
	/**
	 * 保存系统日志对象
	 * @param params
	 * @return
	 */
	public CsLog save(){
		com.ccclubs.service.admin.ICsLogService csLogService = $.GetSpringBean("csLogService");
		if(this.getCslId()!=null)
			csLogService.updateCsLog(this);
		else
			return csLogService.saveCsLog(this);
		return this;
	}
	
	
	/**
	 * 更新系统日志对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsLogService csLogService = $.GetSpringBean("csLogService");
		csLogService.updateCsLog$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsLogService csLogService = $.GetSpringBean("csLogService");
			csLogService.deleteCsLogById(this.getCslId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsLogService csLogService = $.GetSpringBean("csLogService");
		return csLogService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCslId(){
		return this.cslId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCslId$(){
		String strValue="";
		 strValue=$.str(this.getCslId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCslId(Long cslId){
		this.cslId = cslId;
		this.setSeted(F.cslId);
	}
	/*******************************域**********************************/	
	/**
	* 域  [SrvHost]     
	**/
	public Long getCslHost(){
		return this.cslHost;
	}
	/**
	* 获取域格式化(toString)
	**/
	public String getCslHost$(){
		String strValue="";
		if(this.getCslHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCslHost()));
		}			
	 	 return strValue;
	}
	/**
	* 域  [SrvHost]     
	**/
	public void setCslHost(Long cslHost){
		this.cslHost = cslHost;
		this.setSeted(F.cslHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cslHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCslHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCslHost()!=null){
 			srvHost = SrvHost.get(this.getCslHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCslHost(), srvHost);
	 	return srvHost;
	}
	/*******************************操作名称**********************************/	
	/**
	* 操作名称 [非空]      
	**/
	public String getCslName(){
		return this.cslName;
	}
	/**
	* 获取操作名称格式化(toString)
	**/
	public String getCslName$(){
		String strValue="";
		 strValue=$.str(this.getCslName());
	 	 return strValue;
	}
	/**
	* 操作名称 [非空]      
	**/
	public void setCslName(String cslName){
		this.cslName = cslName;
		this.setSeted(F.cslName);
	}
	/*******************************操作模块**********************************/	
	/**
	* 操作模块  [SrvProperty]  model_class:系统数据模型类    
	**/
	public Long getCslModel(){
		return this.cslModel;
	}
	/**
	* 获取操作模块格式化(toString)
	**/
	public String getCslModel$(){
		String strValue="";
		if(this.getCslModel()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCslModel()));
		}			
	 	 return strValue;
	}
	/**
	* 操作模块  [SrvProperty]  model_class:系统数据模型类    
	**/
	public void setCslModel(Long cslModel){
		this.cslModel = cslModel;
		this.setSeted(F.cslModel);
	}
	/**
	* 获取关联对象[系统属性]
	**/	 			
 	public SrvProperty get$cslModel(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCslModel());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCslModel()!=null){
 			srvProperty = SrvProperty.get(this.getCslModel());
 		}
 		$.SetRequest("SrvProperty$"+this.getCslModel(), srvProperty);
	 	return srvProperty;
	}
	/*******************************操作类型**********************************/	
	/**
	* 操作类型  [SrvProperty]  log_type:日志操作类型    
	**/
	public Long getCslType(){
		return this.cslType;
	}
	/**
	* 获取操作类型格式化(toString)
	**/
	public String getCslType$(){
		String strValue="";
		if(this.getCslType()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCslType()));
		}			
	 	 return strValue;
	}
	/**
	* 操作类型  [SrvProperty]  log_type:日志操作类型    
	**/
	public void setCslType(Long cslType){
		this.cslType = cslType;
		this.setSeted(F.cslType);
	}
	/**
	* 获取关联对象[系统属性]
	**/	 			
 	public SrvProperty get$cslType(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCslType());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCslType()!=null){
 			srvProperty = SrvProperty.get(this.getCslType());
 		}
 		$.SetRequest("SrvProperty$"+this.getCslType(), srvProperty);
	 	return srvProperty;
	}
	/*******************************操作人**********************************/	
	/**
	* 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCslUser(){
		return this.cslUser;
	}
	/**
	* 获取操作人格式化(toString)
	**/
	public String getCslUser$(){
		String strValue="";
		if(this.getCslUser()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCslUser()));
		}			
	 	 return strValue;
	}
	/**
	* 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCslUser(Long cslUser){
		this.cslUser = cslUser;
		this.setSeted(F.cslUser);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cslUser(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCslUser());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCslUser()!=null){
 			srvUser = SrvUser.get(this.getCslUser());
 		}
 		$.SetRequest("SrvUser$"+this.getCslUser(), srvUser);
	 	return srvUser;
	}
	/*******************************模块类名**********************************/	
	/**
	* 模块类名       
	**/
	public String getCslClass(){
		return this.cslClass;
	}
	/**
	* 获取模块类名格式化(toString)
	**/
	public String getCslClass$(){
		String strValue="";
		 strValue=$.str(this.getCslClass());
	 	 return strValue;
	}
	/**
	* 模块类名       
	**/
	public void setCslClass(String cslClass){
		this.cslClass = cslClass;
		this.setSeted(F.cslClass);
	}
	/*******************************数据ID**********************************/	
	/**
	* 数据ID       
	**/
	public Long getCslRelate(){
		return this.cslRelate;
	}
	/**
	* 获取数据ID格式化(toString)
	**/
	public String getCslRelate$(){
		String strValue="";
		 strValue=$.str(this.getCslRelate());
	 	 return strValue;
	}
	/**
	* 数据ID       
	**/
	public void setCslRelate(Long cslRelate){
		this.cslRelate = cslRelate;
		this.setSeted(F.cslRelate);
	}
	/*******************************数据状态**********************************/	
	/**
	* 数据状态       
	**/
	public String getCslDetail(){
		return this.cslDetail;
	}
	/**
	* 获取数据状态格式化(toString)
	**/
	public String getCslDetail$(){
		String strValue="";
		 strValue=$.str(this.getCslDetail());
	 	 return strValue;
	}
	/**
	* 数据状态       
	**/
	public void setCslDetail(String cslDetail){
		this.cslDetail = cslDetail;
		this.setSeted(F.cslDetail);
	}
	/*******************************操作时间**********************************/	
	/**
	* 操作时间 [非空]      
	**/
	public Date getCslAddTime(){
		return this.cslAddTime;
	}
	/**
	* 获取操作时间格式化(toString)
	**/
	public String getCslAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCslAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 操作时间 [非空]      
	**/
	public void setCslAddTime(Date cslAddTime){
		this.cslAddTime = cslAddTime;
		this.setSeted(F.cslAddTime);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsLog.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsLog.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsLog.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsLog.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsLog.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsLog.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsLog.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsLog.this);
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
		public M cslId(Object cslId){this.put("cslId", cslId);return this;};
	 	/** and csl_id is null */
 		public M cslIdNull(){if(this.get("cslIdNot")==null)this.put("cslIdNot", "");this.put("cslId", null);return this;};
 		/** not .... */
 		public M cslIdNot(){this.put("cslIdNot", "not");return this;};
		/** 域  [SrvHost]      **/
		public M cslHost(Object cslHost){this.put("cslHost", cslHost);return this;};
	 	/** and csl_host is null */
 		public M cslHostNull(){if(this.get("cslHostNot")==null)this.put("cslHostNot", "");this.put("cslHost", null);return this;};
 		/** not .... */
 		public M cslHostNot(){this.put("cslHostNot", "not");return this;};
		/** 操作名称 [非空]       **/
		public M cslName(Object cslName){this.put("cslName", cslName);return this;};
	 	/** and csl_name is null */
 		public M cslNameNull(){if(this.get("cslNameNot")==null)this.put("cslNameNot", "");this.put("cslName", null);return this;};
 		/** not .... */
 		public M cslNameNot(){this.put("cslNameNot", "not");return this;};
		/** 操作模块  [SrvProperty]  model_class:系统数据模型类     **/
		public M cslModel(Object cslModel){this.put("cslModel", cslModel);return this;};
	 	/** and csl_model is null */
 		public M cslModelNull(){if(this.get("cslModelNot")==null)this.put("cslModelNot", "");this.put("cslModel", null);return this;};
 		/** not .... */
 		public M cslModelNot(){this.put("cslModelNot", "not");return this;};
		public M cslModel$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("cslModel$on", value);
			return this;
		};	
		/** 操作类型  [SrvProperty]  log_type:日志操作类型     **/
		public M cslType(Object cslType){this.put("cslType", cslType);return this;};
	 	/** and csl_type is null */
 		public M cslTypeNull(){if(this.get("cslTypeNot")==null)this.put("cslTypeNot", "");this.put("cslType", null);return this;};
 		/** not .... */
 		public M cslTypeNot(){this.put("cslTypeNot", "not");return this;};
		public M cslType$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("cslType$on", value);
			return this;
		};	
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cslUser(Object cslUser){this.put("cslUser", cslUser);return this;};
	 	/** and csl_user is null */
 		public M cslUserNull(){if(this.get("cslUserNot")==null)this.put("cslUserNot", "");this.put("cslUser", null);return this;};
 		/** not .... */
 		public M cslUserNot(){this.put("cslUserNot", "not");return this;};
		/** 模块类名        **/
		public M cslClass(Object cslClass){this.put("cslClass", cslClass);return this;};
	 	/** and csl_class is null */
 		public M cslClassNull(){if(this.get("cslClassNot")==null)this.put("cslClassNot", "");this.put("cslClass", null);return this;};
 		/** not .... */
 		public M cslClassNot(){this.put("cslClassNot", "not");return this;};
		/** 数据ID        **/
		public M cslRelate(Object cslRelate){this.put("cslRelate", cslRelate);return this;};
	 	/** and csl_relate is null */
 		public M cslRelateNull(){if(this.get("cslRelateNot")==null)this.put("cslRelateNot", "");this.put("cslRelate", null);return this;};
 		/** not .... */
 		public M cslRelateNot(){this.put("cslRelateNot", "not");return this;};
		/** and csl_relate >= ? */
		public M cslRelateMin(Object min){this.put("cslRelateMin", min);return this;};
		/** and csl_relate <= ? */
		public M cslRelateMax(Object max){this.put("cslRelateMax", max);return this;};
		/** 数据状态        **/
		public M cslDetail(Object cslDetail){this.put("cslDetail", cslDetail);return this;};
	 	/** and csl_detail is null */
 		public M cslDetailNull(){if(this.get("cslDetailNot")==null)this.put("cslDetailNot", "");this.put("cslDetail", null);return this;};
 		/** not .... */
 		public M cslDetailNot(){this.put("cslDetailNot", "not");return this;};
		/** 操作时间 [非空]       **/
		public M cslAddTime(Object cslAddTime){this.put("cslAddTime", cslAddTime);return this;};
	 	/** and csl_add_time is null */
 		public M cslAddTimeNull(){if(this.get("cslAddTimeNot")==null)this.put("cslAddTimeNot", "");this.put("cslAddTime", null);return this;};
 		/** not .... */
 		public M cslAddTimeNot(){this.put("cslAddTimeNot", "not");return this;};
 		/** and csl_add_time >= ? */
 		public M cslAddTimeStart(Object start){this.put("cslAddTimeStart", start);return this;};			
 		/** and csl_add_time <= ? */
 		public M cslAddTimeEnd(Object end){this.put("cslAddTimeEnd", end);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有系统日志 **/
		public @api List<CsLog> list(Integer size){
			return getCsLogList(this,size);
		}
		/** 获取系统日志分页 **/
		public @api Page<CsLog> page(int page,int size){
			return getCsLogPage(page, size , this);
		}
		/** 根据查询条件取系统日志 **/
		public @api CsLog get(){
			return getCsLog(this);
		}
		/** 获取系统日志数 **/
		public @api Long count(){
			return getCsLogCount(this);
		}
		/** 获取系统日志表达式 **/
		public @api <T> T eval(String strEval){
			return getCsLogEval(strEval,this);
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
			updateCsLog(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cslId="cslId";
		/** 域  [SrvHost]      **/
		public final static @type(Long.class)  String cslHost="cslHost";
		/** 操作名称 [非空]       **/
		public final static @type(String.class) @like String cslName="cslName";
		/** 操作模块  [SrvProperty]  model_class:系统数据模型类     **/
		public final static @type(Long.class)  String cslModel="cslModel";
		/** 操作类型  [SrvProperty]  log_type:日志操作类型     **/
		public final static @type(Long.class)  String cslType="cslType";
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String cslUser="cslUser";
		/** 模块类名        **/
		public final static @type(String.class) @like String cslClass="cslClass";
		/** 数据ID        **/
		public final static @type(Long.class)  String cslRelate="cslRelate";
		/** and csl_relate >= ? */
		public final static @type(Long.class) String cslRelateMin="cslRelateMin";
		/** and csl_relate <= ? */
		public final static @type(Long.class) String cslRelateMax="cslRelateMax";
		/** 数据状态        **/
		public final static @type(String.class) @like String cslDetail="cslDetail";
		/** 操作时间 [非空]       **/
		public final static @type(Date.class)  String cslAddTime="cslAddTime";
	 	/** and csl_add_time >= ? */
 		public final static @type(Date.class) String cslAddTimeStart="cslAddTimeStart";
 		/** and csl_add_time <= ? */
 		public final static @type(Date.class) String cslAddTimeEnd="cslAddTimeEnd";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cslId="csl_id";
		/** 域  [SrvHost]      **/
		public final static String cslHost="csl_host";
		/** 操作名称 [非空]       **/
		public final static String cslName="csl_name";
		/** 操作模块  [SrvProperty]  model_class:系统数据模型类     **/
		public final static String cslModel="csl_model";
		/** 操作类型  [SrvProperty]  log_type:日志操作类型     **/
		public final static String cslType="csl_type";
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cslUser="csl_user";
		/** 模块类名        **/
		public final static String cslClass="csl_class";
		/** 数据ID        **/
		public final static String cslRelate="csl_relate";
		/** 数据状态        **/
		public final static String cslDetail="csl_detail";
		/** 操作时间 [非空]       **/
		public final static String cslAddTime="csl_add_time";
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
				$.Set(name,CsLog.getCsLog(params));
			else
				$.Set(name,CsLog.getCsLogList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取系统日志数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsLog) $.GetRequest("CsLog$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsLog.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsLog.getCsLog(params);
			else
				value = CsLog.getCsLogList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsLog.Get($.add(CsLog.F.cslId,param));
		else if(!$.empty(param.toString()))
			value = CsLog.get(Long.valueOf(param.toString()));
		$.SetRequest("CsLog$"+param.hashCode(), value);
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
	public void mergeSet(CsLog old){
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