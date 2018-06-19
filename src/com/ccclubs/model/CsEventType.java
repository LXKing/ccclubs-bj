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

@namespace("systematic/eventtype")
public @caption("系统事件类型") @table("cs_event_type") class CsEventType implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cset_id")   @primary  @note("  ") Long csetId;// 主键 非空     
	private @caption("事件类型名称") @column("cset_name")    @note("  ") String csetName;// 非空     
	private @caption("事件级别") @column("cset_level")    @note(" 0:普通级别 1:全员提示  ") Short csetLevel;// 非空 0:普通级别 1:全员提示     
	private @caption("关联项目") @column("cset_project")    @relate("$csetProject") @RelateClass(SrvProject.class)  @note("  ") Long csetProject;//     
 	private SrvProject $csetProject;//关联对象[项目]
	private @caption("处理地址") @column("cset_action")    @note("  ") String csetAction;//     
	private @caption("时间间隔") @column("cset_interval")    @note(" 0:不限制 60:一分钟 600:十分钟 1800:半小时 3600:一小时 7200:两小时 43200:半天 86400:一天 2592000:一个月  ") Long csetInterval;// 0:不限制 60:一分钟 600:十分钟 1800:半小时 3600:一小时 7200:两小时 43200:半天 86400:一天 2592000:一个月     
	private @caption("修改时间") @column("cset_update_time")    @note("  ") Date csetUpdateTime;// 非空     
	private @caption("添加时间") @column("cset_add_time")    @note("  ") Date csetAddTime;// 非空     
	private @caption("状态") @column("cset_status")    @note(" 1:启用 0:禁用  ") Short csetStatus;// 非空 1:启用 0:禁用     
	
	//默认构造函数
	public CsEventType(){
	
	}
	
	//主键构造函数
	public CsEventType(Long id){
		this.csetId = id;
	}
	
	/**所有字段构造函数 CsEventType(csetName,csetLevel,csetProject,csetAction,csetInterval,csetUpdateTime,csetAddTime,csetStatus)
	 CsEventType(
	 	$.getString("csetName")//事件类型名称 [非空]
	 	,$.getShort("csetLevel")//事件级别 [非空]
	 	,$.getLong("csetProject")//关联项目
	 	,$.getString("csetAction")//处理地址
	 	,$.getLong("csetInterval")//时间间隔
	 	,$.getDate("csetUpdateTime")//修改时间 [非空]
	 	,$.getDate("csetAddTime")//添加时间 [非空]
	 	,$.getShort("csetStatus")//状态 [非空]
	 )
	**/
	public CsEventType(String csetName,Short csetLevel,Long csetProject,String csetAction,Long csetInterval,Date csetUpdateTime,Date csetAddTime,Short csetStatus){
		this.csetName=csetName;
		this.csetLevel=csetLevel;
		this.csetProject=csetProject;
		this.csetAction=csetAction;
		this.csetInterval=csetInterval;
		this.csetUpdateTime=csetUpdateTime;
		this.csetAddTime=csetAddTime;
		this.csetStatus=csetStatus;
	}
	
	//设置非空字段
	public CsEventType setNotNull(String csetName,Short csetLevel,Date csetUpdateTime,Date csetAddTime,Short csetStatus){
		this.csetName=csetName;
		this.csetLevel=csetLevel;
		this.csetUpdateTime=csetUpdateTime;
		this.csetAddTime=csetAddTime;
		this.csetStatus=csetStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsEventType csetId(Long csetId){
		this.csetId = csetId;
		this.setSeted(F.csetId);
		return this;
	}
	/** 事件类型名称 [非空]       **/
	public CsEventType csetName(String csetName){
		this.csetName = csetName;
		this.setSeted(F.csetName);
		return this;
	}
	/** 事件级别 [非空]   0:普通级别 1:全员提示     **/
	public CsEventType csetLevel(Short csetLevel){
		this.csetLevel = csetLevel;
		this.setSeted(F.csetLevel);
		return this;
	}
	/** 关联项目  [SrvProject]      **/
	public CsEventType csetProject(Long csetProject){
		this.csetProject = csetProject;
		this.setSeted(F.csetProject);
		return this;
	}
	/** 处理地址        **/
	public CsEventType csetAction(String csetAction){
		this.csetAction = csetAction;
		this.setSeted(F.csetAction);
		return this;
	}
	/** 时间间隔    0:不限制 60:一分钟 600:十分钟 1800:半小时 3600:一小时 7200:两小时 43200:半天 86400:一天 2592000:一个月     **/
	public CsEventType csetInterval(Long csetInterval){
		this.csetInterval = csetInterval;
		this.setSeted(F.csetInterval);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsEventType csetUpdateTime(Date csetUpdateTime){
		this.csetUpdateTime = csetUpdateTime;
		this.setSeted(F.csetUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsEventType csetAddTime(Date csetAddTime){
		this.csetAddTime = csetAddTime;
		this.setSeted(F.csetAddTime);
		return this;
	}
	/** 状态 [非空]   1:启用 0:禁用     **/
	public CsEventType csetStatus(Short csetStatus){
		this.csetStatus = csetStatus;
		this.setSeted(F.csetStatus);
		return this;
	}
	
	
	//克隆对象
	public CsEventType clone(){
		CsEventType clone = new CsEventType();
		clone.csetName=this.csetName;
		clone.csetLevel=this.csetLevel;
		clone.csetUpdateTime=this.csetUpdateTime;
		clone.csetAddTime=this.csetAddTime;
		clone.csetStatus=this.csetStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取系统事件类型
	 * @param id
	 * @return
	 */
	public static @api CsEventType get(Long id){		
		return getCsEventTypeById(id);
	}
	/**
	 * 获取所有系统事件类型
	 * @return
	 */
	public static @api List<CsEventType> list(Map params,Integer size){
		return getCsEventTypeList(params,size);
	}
	/**
	 * 获取系统事件类型分页
	 * @return
	 */
	public static @api Page<CsEventType> page(int page,int size,Map params){
		return getCsEventTypePage(page, size , params);
	}
	/**
	 * 根据查询条件取系统事件类型
	 * @param params
	 * @return
	 */
	public static @api CsEventType Get(Map params){
		return getCsEventType(params);
	}
	/**
	 * 获取系统事件类型数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsEventTypeCount(params);
	}
	/**
	 * 获取系统事件类型数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsEventTypeEval(eval,params);
	}
	
	/**
	 * 根据ID取系统事件类型
	 * @param id
	 * @return
	 */
	public static @api CsEventType getCsEventTypeById(Long id){		
		CsEventType csEventType = (CsEventType) $.GetRequest("CsEventType$"+id);
		if(csEventType!=null)
			return csEventType;
		com.ccclubs.service.admin.ICsEventTypeService csEventTypeService = $.GetSpringBean("csEventTypeService");		
		return csEventTypeService.getCsEventTypeById(id);
	}
	
	
	/**
	 * 根据ID取系统事件类型的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsEventType.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsEventType csEventType = get(id);
		if(csEventType!=null){
			String strValue = csEventType.getCsetName$();
			if(!"CsetName".equals("CsetName"))
				strValue+="("+csEventType.getCsetName$()+")";
			MemCache.setValue(CsEventType.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsetName$();
		if(!"CsetName".equals("CsetName"))
			keyValue+="("+this.getCsetName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有系统事件类型
	 * @return
	 */
	public static @api List<CsEventType> getCsEventTypeList(Map params,Integer size){
		com.ccclubs.service.admin.ICsEventTypeService csEventTypeService = $.GetSpringBean("csEventTypeService");
		return csEventTypeService.getCsEventTypeList(params, size);
	}
	
	/**
	 * 获取系统事件类型分页
	 * @return
	 */
	public static @api Page<CsEventType> getCsEventTypePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsEventTypeService csEventTypeService = $.GetSpringBean("csEventTypeService");
		return csEventTypeService.getCsEventTypePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取系统事件类型
	 * @param params
	 * @return
	 */
	public static @api CsEventType getCsEventType(Map params){
		com.ccclubs.service.admin.ICsEventTypeService csEventTypeService = $.GetSpringBean("csEventTypeService");
		return csEventTypeService.getCsEventType(params);
	}
	
	/**
	 * 获取系统事件类型数
	 * @return
	 */
	public static @api Long getCsEventTypeCount(Map params){
		com.ccclubs.service.admin.ICsEventTypeService csEventTypeService = $.GetSpringBean("csEventTypeService");
		return csEventTypeService.getCsEventTypeCount(params);
	}
		
		
	/**
	 * 获取系统事件类型自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsEventTypeEval(String eval,Map params){
		com.ccclubs.service.admin.ICsEventTypeService csEventTypeService = $.GetSpringBean("csEventTypeService");
		return csEventTypeService.getCsEventTypeEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsEventType(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsEventTypeService csEventTypeService = $.GetSpringBean("csEventTypeService");
		csEventTypeService.updateCsEventTypeByConfirm(set, where);
	}
	
	
	/**
	 * 保存系统事件类型对象
	 * @param params
	 * @return
	 */
	public CsEventType save(){
		com.ccclubs.service.admin.ICsEventTypeService csEventTypeService = $.GetSpringBean("csEventTypeService");
		if(this.getCsetId()!=null)
			csEventTypeService.updateCsEventType(this);
		else
			return csEventTypeService.saveCsEventType(this);
		return this;
	}
	
	
	/**
	 * 更新系统事件类型对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsEventTypeService csEventTypeService = $.GetSpringBean("csEventTypeService");
		csEventTypeService.updateCsEventType$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsEventTypeService csEventTypeService = $.GetSpringBean("csEventTypeService");
		if($.equals($.config("logic_delete"),"true"))
			csEventTypeService.removeCsEventTypeById(this.getCsetId());
		else
			csEventTypeService.deleteCsEventTypeById(this.getCsetId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsEventTypeService csEventTypeService = $.GetSpringBean("csEventTypeService");
		return csEventTypeService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsetId(){
		return this.csetId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsetId$(){
		String strValue="";
		 strValue=$.str(this.getCsetId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsetId(Long csetId){
		this.csetId = csetId;
		this.setSeted(F.csetId);
	}
	/*******************************事件类型名称**********************************/	
	/**
	* 事件类型名称 [非空]      
	**/
	public String getCsetName(){
		return this.csetName;
	}
	/**
	* 获取事件类型名称格式化(toString)
	**/
	public String getCsetName$(){
		String strValue="";
		 strValue=$.str(this.getCsetName());
	 	 return strValue;
	}
	/**
	* 事件类型名称 [非空]      
	**/
	public void setCsetName(String csetName){
		this.csetName = csetName;
		this.setSeted(F.csetName);
	}
	/*******************************事件级别**********************************/	
	/**
	* 事件级别 [非空]   0:普通级别 1:全员提示    
	**/
	public Short getCsetLevel(){
		return this.csetLevel;
	}
	/**
	* 获取事件级别格式化(toString)
	**/
	public String getCsetLevel$(){
		String strValue="";
		 if($.equals($.str(this.getCsetLevel()),"0"))
			strValue=$.str("普通级别");		 
		 if($.equals($.str(this.getCsetLevel()),"1"))
			strValue=$.str("全员提示");		 
	 	 return strValue;
	}
	/**
	* 事件级别 [非空]   0:普通级别 1:全员提示    
	**/
	public void setCsetLevel(Short csetLevel){
		this.csetLevel = csetLevel;
		this.setSeted(F.csetLevel);
	}
	/*******************************关联项目**********************************/	
	/**
	* 关联项目  [SrvProject]     
	**/
	public Long getCsetProject(){
		return this.csetProject;
	}
	/**
	* 获取关联项目格式化(toString)
	**/
	public String getCsetProject$(){
		String strValue="";
		if(this.getCsetProject()!=null){
				strValue+=$.str(SrvProject.getKeyValue(this.getCsetProject()));
		}			
	 	 return strValue;
	}
	/**
	* 关联项目  [SrvProject]     
	**/
	public void setCsetProject(Long csetProject){
		this.csetProject = csetProject;
		this.setSeted(F.csetProject);
	}
	/**
	* 获取关联对象[项目]
	**/	 			
 	public SrvProject get$csetProject(){
 		com.ccclubs.model.SrvProject srvProject = (com.ccclubs.model.SrvProject) $.GetRequest("SrvProject$"+this.getCsetProject());
 		if(srvProject!=null)
			return srvProject;
		if(this.getCsetProject()!=null){
 			srvProject = SrvProject.get(this.getCsetProject());
 		}
 		$.SetRequest("SrvProject$"+this.getCsetProject(), srvProject);
	 	return srvProject;
	}
	/*******************************处理地址**********************************/	
	/**
	* 处理地址       
	**/
	public String getCsetAction(){
		return this.csetAction;
	}
	/**
	* 获取处理地址格式化(toString)
	**/
	public String getCsetAction$(){
		String strValue="";
		 strValue=$.str(this.getCsetAction());
	 	 return strValue;
	}
	/**
	* 处理地址       
	**/
	public void setCsetAction(String csetAction){
		this.csetAction = csetAction;
		this.setSeted(F.csetAction);
	}
	/*******************************时间间隔**********************************/	
	/**
	* 时间间隔    0:不限制 60:一分钟 600:十分钟 1800:半小时 3600:一小时 7200:两小时 43200:半天 86400:一天 2592000:一个月    
	**/
	public Long getCsetInterval(){
		return this.csetInterval;
	}
	/**
	* 获取时间间隔格式化(toString)
	**/
	public String getCsetInterval$(){
		String strValue="";
		 if($.equals($.str(this.getCsetInterval()),"0"))
			strValue=$.str("不限制");		 
		 if($.equals($.str(this.getCsetInterval()),"60"))
			strValue=$.str("一分钟");		 
		 if($.equals($.str(this.getCsetInterval()),"600"))
			strValue=$.str("十分钟");		 
		 if($.equals($.str(this.getCsetInterval()),"1800"))
			strValue=$.str("半小时");		 
		 if($.equals($.str(this.getCsetInterval()),"3600"))
			strValue=$.str("一小时");		 
		 if($.equals($.str(this.getCsetInterval()),"7200"))
			strValue=$.str("两小时");		 
		 if($.equals($.str(this.getCsetInterval()),"43200"))
			strValue=$.str("半天");		 
		 if($.equals($.str(this.getCsetInterval()),"86400"))
			strValue=$.str("一天");		 
		 if($.equals($.str(this.getCsetInterval()),"2592000"))
			strValue=$.str("一个月");		 
	 	 return strValue;
	}
	/**
	* 时间间隔    0:不限制 60:一分钟 600:十分钟 1800:半小时 3600:一小时 7200:两小时 43200:半天 86400:一天 2592000:一个月    
	**/
	public void setCsetInterval(Long csetInterval){
		this.csetInterval = csetInterval;
		this.setSeted(F.csetInterval);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsetUpdateTime(){
		return this.csetUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsetUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsetUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsetUpdateTime(Date csetUpdateTime){
		this.csetUpdateTime = csetUpdateTime;
		this.setSeted(F.csetUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsetAddTime(){
		return this.csetAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsetAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsetAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsetAddTime(Date csetAddTime){
		this.csetAddTime = csetAddTime;
		this.setSeted(F.csetAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:启用 0:禁用    
	**/
	public Short getCsetStatus(){
		return this.csetStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsetStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsetStatus()),"1"))
			strValue=$.str("启用");		 
		 if($.equals($.str(this.getCsetStatus()),"0"))
			strValue=$.str("禁用");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:启用 0:禁用    
	**/
	public void setCsetStatus(Short csetStatus){
		this.csetStatus = csetStatus;
		this.setSeted(F.csetStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsEventType.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsEventType.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsEventType.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsEventType.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsEventType.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsEventType.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsEventType.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsEventType.this);
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
		public M csetId(Object csetId){this.put("csetId", csetId);return this;};
	 	/** and cset_id is null */
 		public M csetIdNull(){if(this.get("csetIdNot")==null)this.put("csetIdNot", "");this.put("csetId", null);return this;};
 		/** not .... */
 		public M csetIdNot(){this.put("csetIdNot", "not");return this;};
		/** 事件类型名称 [非空]       **/
		public M csetName(Object csetName){this.put("csetName", csetName);return this;};
	 	/** and cset_name is null */
 		public M csetNameNull(){if(this.get("csetNameNot")==null)this.put("csetNameNot", "");this.put("csetName", null);return this;};
 		/** not .... */
 		public M csetNameNot(){this.put("csetNameNot", "not");return this;};
		/** 事件级别 [非空]   0:普通级别 1:全员提示     **/
		public M csetLevel(Object csetLevel){this.put("csetLevel", csetLevel);return this;};
	 	/** and cset_level is null */
 		public M csetLevelNull(){if(this.get("csetLevelNot")==null)this.put("csetLevelNot", "");this.put("csetLevel", null);return this;};
 		/** not .... */
 		public M csetLevelNot(){this.put("csetLevelNot", "not");return this;};
		/** 关联项目  [SrvProject]      **/
		public M csetProject(Object csetProject){this.put("csetProject", csetProject);return this;};
	 	/** and cset_project is null */
 		public M csetProjectNull(){if(this.get("csetProjectNot")==null)this.put("csetProjectNot", "");this.put("csetProject", null);return this;};
 		/** not .... */
 		public M csetProjectNot(){this.put("csetProjectNot", "not");return this;};
		/** 处理地址        **/
		public M csetAction(Object csetAction){this.put("csetAction", csetAction);return this;};
	 	/** and cset_action is null */
 		public M csetActionNull(){if(this.get("csetActionNot")==null)this.put("csetActionNot", "");this.put("csetAction", null);return this;};
 		/** not .... */
 		public M csetActionNot(){this.put("csetActionNot", "not");return this;};
		/** 时间间隔    0:不限制 60:一分钟 600:十分钟 1800:半小时 3600:一小时 7200:两小时 43200:半天 86400:一天 2592000:一个月     **/
		public M csetInterval(Object csetInterval){this.put("csetInterval", csetInterval);return this;};
	 	/** and cset_interval is null */
 		public M csetIntervalNull(){if(this.get("csetIntervalNot")==null)this.put("csetIntervalNot", "");this.put("csetInterval", null);return this;};
 		/** not .... */
 		public M csetIntervalNot(){this.put("csetIntervalNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csetUpdateTime(Object csetUpdateTime){this.put("csetUpdateTime", csetUpdateTime);return this;};
	 	/** and cset_update_time is null */
 		public M csetUpdateTimeNull(){if(this.get("csetUpdateTimeNot")==null)this.put("csetUpdateTimeNot", "");this.put("csetUpdateTime", null);return this;};
 		/** not .... */
 		public M csetUpdateTimeNot(){this.put("csetUpdateTimeNot", "not");return this;};
 		/** and cset_update_time >= ? */
 		public M csetUpdateTimeStart(Object start){this.put("csetUpdateTimeStart", start);return this;};			
 		/** and cset_update_time <= ? */
 		public M csetUpdateTimeEnd(Object end){this.put("csetUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csetAddTime(Object csetAddTime){this.put("csetAddTime", csetAddTime);return this;};
	 	/** and cset_add_time is null */
 		public M csetAddTimeNull(){if(this.get("csetAddTimeNot")==null)this.put("csetAddTimeNot", "");this.put("csetAddTime", null);return this;};
 		/** not .... */
 		public M csetAddTimeNot(){this.put("csetAddTimeNot", "not");return this;};
 		/** and cset_add_time >= ? */
 		public M csetAddTimeStart(Object start){this.put("csetAddTimeStart", start);return this;};			
 		/** and cset_add_time <= ? */
 		public M csetAddTimeEnd(Object end){this.put("csetAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:启用 0:禁用     **/
		public M csetStatus(Object csetStatus){this.put("csetStatus", csetStatus);return this;};
	 	/** and cset_status is null */
 		public M csetStatusNull(){if(this.get("csetStatusNot")==null)this.put("csetStatusNot", "");this.put("csetStatus", null);return this;};
 		/** not .... */
 		public M csetStatusNot(){this.put("csetStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有系统事件类型 **/
		public @api List<CsEventType> list(Integer size){
			return getCsEventTypeList(this,size);
		}
		/** 获取系统事件类型分页 **/
		public @api Page<CsEventType> page(int page,int size){
			return getCsEventTypePage(page, size , this);
		}
		/** 根据查询条件取系统事件类型 **/
		public @api CsEventType get(){
			return getCsEventType(this);
		}
		/** 获取系统事件类型数 **/
		public @api Long count(){
			return getCsEventTypeCount(this);
		}
		/** 获取系统事件类型表达式 **/
		public @api <T> T eval(String strEval){
			return getCsEventTypeEval(strEval,this);
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
			updateCsEventType(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csetId="csetId";
		/** 事件类型名称 [非空]       **/
		public final static @type(String.class) @like String csetName="csetName";
		/** 事件级别 [非空]   0:普通级别 1:全员提示     **/
		public final static @type(Short.class)  String csetLevel="csetLevel";
		/** 关联项目  [SrvProject]      **/
		public final static @type(Long.class)  String csetProject="csetProject";
		/** 处理地址        **/
		public final static @type(String.class) @like String csetAction="csetAction";
		/** 时间间隔    0:不限制 60:一分钟 600:十分钟 1800:半小时 3600:一小时 7200:两小时 43200:半天 86400:一天 2592000:一个月     **/
		public final static @type(Long.class)  String csetInterval="csetInterval";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csetUpdateTime="csetUpdateTime";
	 	/** and cset_update_time >= ? */
 		public final static @type(Date.class) String csetUpdateTimeStart="csetUpdateTimeStart";
 		/** and cset_update_time <= ? */
 		public final static @type(Date.class) String csetUpdateTimeEnd="csetUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csetAddTime="csetAddTime";
	 	/** and cset_add_time >= ? */
 		public final static @type(Date.class) String csetAddTimeStart="csetAddTimeStart";
 		/** and cset_add_time <= ? */
 		public final static @type(Date.class) String csetAddTimeEnd="csetAddTimeEnd";
		/** 状态 [非空]   1:启用 0:禁用     **/
		public final static @type(Short.class)  String csetStatus="csetStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csetId="cset_id";
		/** 事件类型名称 [非空]       **/
		public final static String csetName="cset_name";
		/** 事件级别 [非空]   0:普通级别 1:全员提示     **/
		public final static String csetLevel="cset_level";
		/** 关联项目  [SrvProject]      **/
		public final static String csetProject="cset_project";
		/** 处理地址        **/
		public final static String csetAction="cset_action";
		/** 时间间隔    0:不限制 60:一分钟 600:十分钟 1800:半小时 3600:一小时 7200:两小时 43200:半天 86400:一天 2592000:一个月     **/
		public final static String csetInterval="cset_interval";
		/** 修改时间 [非空]       **/
		public final static String csetUpdateTime="cset_update_time";
		/** 添加时间 [非空]       **/
		public final static String csetAddTime="cset_add_time";
		/** 状态 [非空]   1:启用 0:禁用     **/
		public final static String csetStatus="cset_status";
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
				$.Set(name,CsEventType.getCsEventType(params));
			else
				$.Set(name,CsEventType.getCsEventTypeList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取系统事件类型数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsEventType) $.GetRequest("CsEventType$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsEventType.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsEventType.getCsEventType(params);
			else
				value = CsEventType.getCsEventTypeList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsEventType.Get($.add(CsEventType.F.csetId,param));
		else if(!$.empty(param.toString()))
			value = CsEventType.get(Long.valueOf(param.toString()));
		$.SetRequest("CsEventType$"+param.hashCode(), value);
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
	public void mergeSet(CsEventType old){
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