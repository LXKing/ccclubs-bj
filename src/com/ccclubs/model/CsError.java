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

@namespace("systematic/error")
public @caption("错误信息") @table("cs_error") class CsError implements java.io.Serializable
{
	private static final long serialVersionUID =          1l;
	private @caption("编号") @column("cse_id")   @primary  @note("  ") Long cseId;// 主键 外键 非空     
	private @caption("错误标题") @column("cse_title")    @note("  ") String cseTitle;// 非空     
	private @caption("错误内容") @column("cse_content")    @note("  ") String cseContent;//     
	private @caption("标识") @column("cse_flag")    @note("  ") String cseFlag;//     
	private @caption("修改时间") @column("cse_update_time")    @note("  ") Date cseUpdateTime;// 非空     
	private @caption("添加时间") @column("cse_add_time")    @note("  ") Date cseAddTime;// 非空     
	private @caption("状态") @column("cse_status")    @note(" 1:正常 0:无效  ") Short cseStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsError(){
	
	}
	
	//主键构造函数
	public CsError(Long id){
		this.cseId = id;
	}
	
	/**所有字段构造函数 CsError(cseTitle,cseContent,cseFlag,cseUpdateTime,cseAddTime,cseStatus)
	 CsError(
	 	$.getString("cseTitle")//错误标题 [非空]
	 	,$.getString("cseContent")//错误内容
	 	,$.getString("cseFlag")//标识
	 	,$.getDate("cseUpdateTime")//修改时间 [非空]
	 	,$.getDate("cseAddTime")//添加时间 [非空]
	 	,$.getShort("cseStatus")//状态 [非空]
	 )
	**/
	public CsError(String cseTitle,String cseContent,String cseFlag,Date cseUpdateTime,Date cseAddTime,Short cseStatus){
		this.cseTitle=cseTitle;
		this.cseContent=cseContent;
		this.cseFlag=cseFlag;
		this.cseUpdateTime=cseUpdateTime;
		this.cseAddTime=cseAddTime;
		this.cseStatus=cseStatus;
	}
	
	//设置非空字段
	public CsError setNotNull(String cseTitle,Date cseUpdateTime,Date cseAddTime,Short cseStatus){
		this.cseTitle=cseTitle;
		this.cseUpdateTime=cseUpdateTime;
		this.cseAddTime=cseAddTime;
		this.cseStatus=cseStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsError cseId(Long cseId){
		this.cseId = cseId;
		this.setSeted(F.cseId);
		return this;
	}
	/** 错误标题 [非空]       **/
	public CsError cseTitle(String cseTitle){
		this.cseTitle = cseTitle;
		this.setSeted(F.cseTitle);
		return this;
	}
	/** 错误内容        **/
	public CsError cseContent(String cseContent){
		this.cseContent = cseContent;
		this.setSeted(F.cseContent);
		return this;
	}
	/** 标识        **/
	public CsError cseFlag(String cseFlag){
		this.cseFlag = cseFlag;
		this.setSeted(F.cseFlag);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsError cseUpdateTime(Date cseUpdateTime){
		this.cseUpdateTime = cseUpdateTime;
		this.setSeted(F.cseUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsError cseAddTime(Date cseAddTime){
		this.cseAddTime = cseAddTime;
		this.setSeted(F.cseAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsError cseStatus(Short cseStatus){
		this.cseStatus = cseStatus;
		this.setSeted(F.cseStatus);
		return this;
	}
	
	
	//克隆对象
	public CsError clone(){
		CsError clone = new CsError();
		clone.cseTitle=this.cseTitle;
		clone.cseUpdateTime=this.cseUpdateTime;
		clone.cseAddTime=this.cseAddTime;
		clone.cseStatus=this.cseStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取错误信息
	 * @param id
	 * @return
	 */
	public static @api CsError get(Long id){		
		return getCsErrorById(id);
	}
	/**
	 * 获取所有错误信息
	 * @return
	 */
	public static @api List<CsError> list(Map params,Integer size){
		return getCsErrorList(params,size);
	}
	/**
	 * 获取错误信息分页
	 * @return
	 */
	public static @api Page<CsError> page(int page,int size,Map params){
		return getCsErrorPage(page, size , params);
	}
	/**
	 * 根据查询条件取错误信息
	 * @param params
	 * @return
	 */
	public static @api CsError Get(Map params){
		return getCsError(params);
	}
	/**
	 * 获取错误信息数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsErrorCount(params);
	}
	/**
	 * 获取错误信息数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsErrorEval(eval,params);
	}
	
	/**
	 * 根据ID取错误信息
	 * @param id
	 * @return
	 */
	public static @api CsError getCsErrorById(Long id){		
		CsError csError = (CsError) $.GetRequest("CsError$"+id);
		if(csError!=null)
			return csError;
		com.ccclubs.service.admin.ICsErrorService csErrorService = $.GetSpringBean("csErrorService");		
		return csErrorService.getCsErrorById(id);
	}
	
	
	/**
	 * 根据ID取错误信息的标识键值
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
		String keyValue = this.getCseTitle$();
		if(!"CseTitle".equals("CseTitle"))
			keyValue+="("+this.getCseTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有错误信息
	 * @return
	 */
	public static @api List<CsError> getCsErrorList(Map params,Integer size){
		com.ccclubs.service.admin.ICsErrorService csErrorService = $.GetSpringBean("csErrorService");
		return csErrorService.getCsErrorList(params, size);
	}
	
	/**
	 * 获取错误信息分页
	 * @return
	 */
	public static @api Page<CsError> getCsErrorPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsErrorService csErrorService = $.GetSpringBean("csErrorService");
		return csErrorService.getCsErrorPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取错误信息
	 * @param params
	 * @return
	 */
	public static @api CsError getCsError(Map params){
		com.ccclubs.service.admin.ICsErrorService csErrorService = $.GetSpringBean("csErrorService");
		return csErrorService.getCsError(params);
	}
	
	/**
	 * 获取错误信息数
	 * @return
	 */
	public static @api Long getCsErrorCount(Map params){
		com.ccclubs.service.admin.ICsErrorService csErrorService = $.GetSpringBean("csErrorService");
		return csErrorService.getCsErrorCount(params);
	}
		
		
	/**
	 * 获取错误信息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsErrorEval(String eval,Map params){
		com.ccclubs.service.admin.ICsErrorService csErrorService = $.GetSpringBean("csErrorService");
		return csErrorService.getCsErrorEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsError(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsErrorService csErrorService = $.GetSpringBean("csErrorService");
		csErrorService.updateCsErrorByConfirm(set, where);
	}
	
	
	/**
	 * 保存错误信息对象
	 * @param params
	 * @return
	 */
	public CsError save(){
		com.ccclubs.service.admin.ICsErrorService csErrorService = $.GetSpringBean("csErrorService");
		if(this.getCseId()!=null)
			csErrorService.updateCsError(this);
		else
			return csErrorService.saveCsError(this);
		return this;
	}
	
	
	/**
	 * 更新错误信息对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsErrorService csErrorService = $.GetSpringBean("csErrorService");
		csErrorService.updateCsError$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsErrorService csErrorService = $.GetSpringBean("csErrorService");
			csErrorService.deleteCsErrorById(this.getCseId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsErrorService csErrorService = $.GetSpringBean("csErrorService");
		return csErrorService.executeTransaction(function);
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
	/*******************************错误标题**********************************/	
	/**
	* 错误标题 [非空]      
	**/
	public String getCseTitle(){
		return this.cseTitle;
	}
	/**
	* 获取错误标题格式化(toString)
	**/
	public String getCseTitle$(){
		String strValue="";
		 strValue=$.str(this.getCseTitle());
	 	 return strValue;
	}
	/**
	* 错误标题 [非空]      
	**/
	public void setCseTitle(String cseTitle){
		this.cseTitle = cseTitle;
		this.setSeted(F.cseTitle);
	}
	/*******************************错误内容**********************************/	
	/**
	* 错误内容       
	**/
	public String getCseContent(){
		return this.cseContent;
	}
	/**
	* 获取错误内容格式化(toString)
	**/
	public String getCseContent$(){
		String strValue="";
		 strValue=$.str(this.getCseContent());
	 	 return strValue;
	}
	/**
	* 错误内容       
	**/
	public void setCseContent(String cseContent){
		this.cseContent = cseContent;
		this.setSeted(F.cseContent);
	}
	/*******************************标识**********************************/	
	/**
	* 标识       
	**/
	public String getCseFlag(){
		return this.cseFlag;
	}
	/**
	* 获取标识格式化(toString)
	**/
	public String getCseFlag$(){
		String strValue="";
		 strValue=$.str(this.getCseFlag());
	 	 return strValue;
	}
	/**
	* 标识       
	**/
	public void setCseFlag(String cseFlag){
		this.cseFlag = cseFlag;
		this.setSeted(F.cseFlag);
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
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsError.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsError.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsError.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsError.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsError.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsError.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsError.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsError.this);
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
		/** 错误标题 [非空]       **/
		public M cseTitle(Object cseTitle){this.put("cseTitle", cseTitle);return this;};
	 	/** and cse_title is null */
 		public M cseTitleNull(){if(this.get("cseTitleNot")==null)this.put("cseTitleNot", "");this.put("cseTitle", null);return this;};
 		/** not .... */
 		public M cseTitleNot(){this.put("cseTitleNot", "not");return this;};
		/** 错误内容        **/
		public M cseContent(Object cseContent){this.put("cseContent", cseContent);return this;};
	 	/** and cse_content is null */
 		public M cseContentNull(){if(this.get("cseContentNot")==null)this.put("cseContentNot", "");this.put("cseContent", null);return this;};
 		/** not .... */
 		public M cseContentNot(){this.put("cseContentNot", "not");return this;};
		/** 标识        **/
		public M cseFlag(Object cseFlag){this.put("cseFlag", cseFlag);return this;};
	 	/** and cse_flag is null */
 		public M cseFlagNull(){if(this.get("cseFlagNot")==null)this.put("cseFlagNot", "");this.put("cseFlag", null);return this;};
 		/** not .... */
 		public M cseFlagNot(){this.put("cseFlagNot", "not");return this;};
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
	 	/** 获取所有错误信息 **/
		public @api List<CsError> list(Integer size){
			return getCsErrorList(this,size);
		}
		/** 获取错误信息分页 **/
		public @api Page<CsError> page(int page,int size){
			return getCsErrorPage(page, size , this);
		}
		/** 根据查询条件取错误信息 **/
		public @api CsError get(){
			return getCsError(this);
		}
		/** 获取错误信息数 **/
		public @api Long count(){
			return getCsErrorCount(this);
		}
		/** 获取错误信息表达式 **/
		public @api <T> T eval(String strEval){
			return getCsErrorEval(strEval,this);
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
			updateCsError(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cseId="cseId";
		/** 错误标题 [非空]       **/
		public final static @type(String.class) @like String cseTitle="cseTitle";
		/** 错误内容        **/
		public final static @type(String.class) @like String cseContent="cseContent";
		/** 标识        **/
		public final static @type(String.class) @like String cseFlag="cseFlag";
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
		/** 错误标题 [非空]       **/
		public final static String cseTitle="cse_title";
		/** 错误内容        **/
		public final static String cseContent="cse_content";
		/** 标识        **/
		public final static String cseFlag="cse_flag";
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
				$.Set(name,CsError.getCsError(params));
			else
				$.Set(name,CsError.getCsErrorList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取错误信息数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsError) $.GetRequest("CsError$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsError.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsError.getCsError(params);
			else
				value = CsError.getCsErrorList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsError.Get($.add(CsError.F.cseId,param));
		else if(!$.empty(param.toString()))
			value = CsError.get(Long.valueOf(param.toString()));
		$.SetRequest("CsError$"+param.hashCode(), value);
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
	public void mergeSet(CsError old){
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