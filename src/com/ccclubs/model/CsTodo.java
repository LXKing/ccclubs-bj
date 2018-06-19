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

@namespace("systematic/todo")
public @caption("待办事项") @table("cs_todo") class CsTodo implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cst_id")   @primary  @note("  ") Long cstId;// 主键 非空     
	private @caption("待办事标题") @column("cst_title")    @note("  ") String cstTitle;// 非空     
	private @caption("设定提醒时间") @column("cst_alarm_time")    @note("  ") Date cstAlarmTime;//     
	private @caption("所有人") @column("cst_user")    @relate("$cstUser") @RelateClass(SrvUser.class)  @note("  ") Long cstUser;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cstUser;//关联对象[用户]
	private @caption("待办事内容") @column("cst_content")    @note("  ") String cstContent;//     
	private @caption("修改时间") @column("cst_update_time")    @note("  ") Date cstUpdateTime;// 非空     
	private @caption("添加时间") @column("cst_add_time")    @note("  ") Date cstAddTime;// 非空     
	private @caption("状态") @column("cst_status")    @note(" 0:待处理 1:提醒报警 2:已完成  ") Short cstStatus;// 非空 0:待处理 1:提醒报警 2:已完成     
	
	//默认构造函数
	public CsTodo(){
	
	}
	
	//主键构造函数
	public CsTodo(Long id){
		this.cstId = id;
	}
	
	/**所有字段构造函数 CsTodo(cstTitle,cstAlarmTime,cstUser,cstContent,cstUpdateTime,cstAddTime,cstStatus)
	 CsTodo(
	 	$.getString("cstTitle")//待办事标题 [非空]
	 	,$.getDate("cstAlarmTime")//设定提醒时间
	 	,$.getLong("cstUser")//所有人 [非空]
	 	,$.getString("cstContent")//待办事内容
	 	,$.getDate("cstUpdateTime")//修改时间 [非空]
	 	,$.getDate("cstAddTime")//添加时间 [非空]
	 	,$.getShort("cstStatus")//状态 [非空]
	 )
	**/
	public CsTodo(String cstTitle,Date cstAlarmTime,Long cstUser,String cstContent,Date cstUpdateTime,Date cstAddTime,Short cstStatus){
		this.cstTitle=cstTitle;
		this.cstAlarmTime=cstAlarmTime;
		this.cstUser=cstUser;
		this.cstContent=cstContent;
		this.cstUpdateTime=cstUpdateTime;
		this.cstAddTime=cstAddTime;
		this.cstStatus=cstStatus;
	}
	
	//设置非空字段
	public CsTodo setNotNull(String cstTitle,Long cstUser,Date cstUpdateTime,Date cstAddTime,Short cstStatus){
		this.cstTitle=cstTitle;
		this.cstUser=cstUser;
		this.cstUpdateTime=cstUpdateTime;
		this.cstAddTime=cstAddTime;
		this.cstStatus=cstStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsTodo cstId(Long cstId){
		this.cstId = cstId;
		this.setSeted(F.cstId);
		return this;
	}
	/** 待办事标题 [非空]       **/
	public CsTodo cstTitle(String cstTitle){
		this.cstTitle = cstTitle;
		this.setSeted(F.cstTitle);
		return this;
	}
	/** 设定提醒时间        **/
	public CsTodo cstAlarmTime(Date cstAlarmTime){
		this.cstAlarmTime = cstAlarmTime;
		this.setSeted(F.cstAlarmTime);
		return this;
	}
	/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsTodo cstUser(Long cstUser){
		this.cstUser = cstUser;
		this.setSeted(F.cstUser);
		return this;
	}
	/** 待办事内容        **/
	public CsTodo cstContent(String cstContent){
		this.cstContent = cstContent;
		this.setSeted(F.cstContent);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsTodo cstUpdateTime(Date cstUpdateTime){
		this.cstUpdateTime = cstUpdateTime;
		this.setSeted(F.cstUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsTodo cstAddTime(Date cstAddTime){
		this.cstAddTime = cstAddTime;
		this.setSeted(F.cstAddTime);
		return this;
	}
	/** 状态 [非空]   0:待处理 1:提醒报警 2:已完成     **/
	public CsTodo cstStatus(Short cstStatus){
		this.cstStatus = cstStatus;
		this.setSeted(F.cstStatus);
		return this;
	}
	
	
	//克隆对象
	public CsTodo clone(){
		CsTodo clone = new CsTodo();
		clone.cstTitle=this.cstTitle;
		clone.cstUser=this.cstUser;
		clone.cstUpdateTime=this.cstUpdateTime;
		clone.cstAddTime=this.cstAddTime;
		clone.cstStatus=this.cstStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取待办事项
	 * @param id
	 * @return
	 */
	public static @api CsTodo get(Long id){		
		return getCsTodoById(id);
	}
	/**
	 * 获取所有待办事项
	 * @return
	 */
	public static @api List<CsTodo> list(Map params,Integer size){
		return getCsTodoList(params,size);
	}
	/**
	 * 获取待办事项分页
	 * @return
	 */
	public static @api Page<CsTodo> page(int page,int size,Map params){
		return getCsTodoPage(page, size , params);
	}
	/**
	 * 根据查询条件取待办事项
	 * @param params
	 * @return
	 */
	public static @api CsTodo Get(Map params){
		return getCsTodo(params);
	}
	/**
	 * 获取待办事项数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsTodoCount(params);
	}
	/**
	 * 获取待办事项数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsTodoEval(eval,params);
	}
	
	/**
	 * 根据ID取待办事项
	 * @param id
	 * @return
	 */
	public static @api CsTodo getCsTodoById(Long id){		
		CsTodo csTodo = (CsTodo) $.GetRequest("CsTodo$"+id);
		if(csTodo!=null)
			return csTodo;
		com.ccclubs.service.admin.ICsTodoService csTodoService = $.GetSpringBean("csTodoService");		
		return csTodoService.getCsTodoById(id);
	}
	
	
	/**
	 * 根据ID取待办事项的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsTodo.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsTodo csTodo = get(id);
		if(csTodo!=null){
			String strValue = csTodo.getCstTitle$();
			if(!"CstTitle".equals("CstTitle"))
				strValue+="("+csTodo.getCstTitle$()+")";
			MemCache.setValue(CsTodo.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCstTitle$();
		if(!"CstTitle".equals("CstTitle"))
			keyValue+="("+this.getCstTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有待办事项
	 * @return
	 */
	public static @api List<CsTodo> getCsTodoList(Map params,Integer size){
		com.ccclubs.service.admin.ICsTodoService csTodoService = $.GetSpringBean("csTodoService");
		return csTodoService.getCsTodoList(params, size);
	}
	
	/**
	 * 获取待办事项分页
	 * @return
	 */
	public static @api Page<CsTodo> getCsTodoPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsTodoService csTodoService = $.GetSpringBean("csTodoService");
		return csTodoService.getCsTodoPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取待办事项
	 * @param params
	 * @return
	 */
	public static @api CsTodo getCsTodo(Map params){
		com.ccclubs.service.admin.ICsTodoService csTodoService = $.GetSpringBean("csTodoService");
		return csTodoService.getCsTodo(params);
	}
	
	/**
	 * 获取待办事项数
	 * @return
	 */
	public static @api Long getCsTodoCount(Map params){
		com.ccclubs.service.admin.ICsTodoService csTodoService = $.GetSpringBean("csTodoService");
		return csTodoService.getCsTodoCount(params);
	}
		
		
	/**
	 * 获取待办事项自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsTodoEval(String eval,Map params){
		com.ccclubs.service.admin.ICsTodoService csTodoService = $.GetSpringBean("csTodoService");
		return csTodoService.getCsTodoEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsTodo(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsTodoService csTodoService = $.GetSpringBean("csTodoService");
		csTodoService.updateCsTodoByConfirm(set, where);
	}
	
	
	/**
	 * 保存待办事项对象
	 * @param params
	 * @return
	 */
	public CsTodo save(){
		com.ccclubs.service.admin.ICsTodoService csTodoService = $.GetSpringBean("csTodoService");
		if(this.getCstId()!=null)
			csTodoService.updateCsTodo(this);
		else
			return csTodoService.saveCsTodo(this);
		return this;
	}
	
	
	/**
	 * 更新待办事项对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsTodoService csTodoService = $.GetSpringBean("csTodoService");
		csTodoService.updateCsTodo$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsTodoService csTodoService = $.GetSpringBean("csTodoService");
		if($.equals($.config("logic_delete"),"true"))
			csTodoService.removeCsTodoById(this.getCstId());
		else
			csTodoService.deleteCsTodoById(this.getCstId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsTodoService csTodoService = $.GetSpringBean("csTodoService");
		return csTodoService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCstId(){
		return this.cstId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCstId$(){
		String strValue="";
		 strValue=$.str(this.getCstId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCstId(Long cstId){
		this.cstId = cstId;
		this.setSeted(F.cstId);
	}
	/*******************************待办事标题**********************************/	
	/**
	* 待办事标题 [非空]      
	**/
	public String getCstTitle(){
		return this.cstTitle;
	}
	/**
	* 获取待办事标题格式化(toString)
	**/
	public String getCstTitle$(){
		String strValue="";
		 strValue=$.str(this.getCstTitle());
	 	 return strValue;
	}
	/**
	* 待办事标题 [非空]      
	**/
	public void setCstTitle(String cstTitle){
		this.cstTitle = cstTitle;
		this.setSeted(F.cstTitle);
	}
	/*******************************设定提醒时间**********************************/	
	/**
	* 设定提醒时间       
	**/
	public Date getCstAlarmTime(){
		return this.cstAlarmTime;
	}
	/**
	* 获取设定提醒时间格式化(toString)
	**/
	public String getCstAlarmTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCstAlarmTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 设定提醒时间       
	**/
	public void setCstAlarmTime(Date cstAlarmTime){
		this.cstAlarmTime = cstAlarmTime;
		this.setSeted(F.cstAlarmTime);
	}
	/*******************************所有人**********************************/	
	/**
	* 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCstUser(){
		return this.cstUser;
	}
	/**
	* 获取所有人格式化(toString)
	**/
	public String getCstUser$(){
		String strValue="";
		if(this.getCstUser()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCstUser()));
		}			
	 	 return strValue;
	}
	/**
	* 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCstUser(Long cstUser){
		this.cstUser = cstUser;
		this.setSeted(F.cstUser);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cstUser(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCstUser());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCstUser()!=null){
 			srvUser = SrvUser.get(this.getCstUser());
 		}
 		$.SetRequest("SrvUser$"+this.getCstUser(), srvUser);
	 	return srvUser;
	}
	/*******************************待办事内容**********************************/	
	/**
	* 待办事内容       
	**/
	public String getCstContent(){
		return this.cstContent;
	}
	/**
	* 获取待办事内容格式化(toString)
	**/
	public String getCstContent$(){
		String strValue="";
		 strValue=$.str(this.getCstContent());
	 	 return strValue;
	}
	/**
	* 待办事内容       
	**/
	public void setCstContent(String cstContent){
		this.cstContent = cstContent;
		this.setSeted(F.cstContent);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCstUpdateTime(){
		return this.cstUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCstUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCstUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCstUpdateTime(Date cstUpdateTime){
		this.cstUpdateTime = cstUpdateTime;
		this.setSeted(F.cstUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCstAddTime(){
		return this.cstAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCstAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCstAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCstAddTime(Date cstAddTime){
		this.cstAddTime = cstAddTime;
		this.setSeted(F.cstAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:待处理 1:提醒报警 2:已完成    
	**/
	public Short getCstStatus(){
		return this.cstStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCstStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCstStatus()),"0"))
			strValue=$.str("待处理");		 
		 if($.equals($.str(this.getCstStatus()),"1"))
			strValue=$.str("提醒报警");		 
		 if($.equals($.str(this.getCstStatus()),"2"))
			strValue=$.str("已完成");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:待处理 1:提醒报警 2:已完成    
	**/
	public void setCstStatus(Short cstStatus){
		this.cstStatus = cstStatus;
		this.setSeted(F.cstStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsTodo.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsTodo.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsTodo.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsTodo.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsTodo.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsTodo.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsTodo.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsTodo.this);
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
		public M cstId(Object cstId){this.put("cstId", cstId);return this;};
	 	/** and cst_id is null */
 		public M cstIdNull(){if(this.get("cstIdNot")==null)this.put("cstIdNot", "");this.put("cstId", null);return this;};
 		/** not .... */
 		public M cstIdNot(){this.put("cstIdNot", "not");return this;};
		/** 待办事标题 [非空]       **/
		public M cstTitle(Object cstTitle){this.put("cstTitle", cstTitle);return this;};
	 	/** and cst_title is null */
 		public M cstTitleNull(){if(this.get("cstTitleNot")==null)this.put("cstTitleNot", "");this.put("cstTitle", null);return this;};
 		/** not .... */
 		public M cstTitleNot(){this.put("cstTitleNot", "not");return this;};
		/** 设定提醒时间        **/
		public M cstAlarmTime(Object cstAlarmTime){this.put("cstAlarmTime", cstAlarmTime);return this;};
	 	/** and cst_alarm_time is null */
 		public M cstAlarmTimeNull(){if(this.get("cstAlarmTimeNot")==null)this.put("cstAlarmTimeNot", "");this.put("cstAlarmTime", null);return this;};
 		/** not .... */
 		public M cstAlarmTimeNot(){this.put("cstAlarmTimeNot", "not");return this;};
 		/** and cst_alarm_time >= ? */
 		public M cstAlarmTimeStart(Object start){this.put("cstAlarmTimeStart", start);return this;};			
 		/** and cst_alarm_time <= ? */
 		public M cstAlarmTimeEnd(Object end){this.put("cstAlarmTimeEnd", end);return this;};
		/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cstUser(Object cstUser){this.put("cstUser", cstUser);return this;};
	 	/** and cst_user is null */
 		public M cstUserNull(){if(this.get("cstUserNot")==null)this.put("cstUserNot", "");this.put("cstUser", null);return this;};
 		/** not .... */
 		public M cstUserNot(){this.put("cstUserNot", "not");return this;};
		/** 待办事内容        **/
		public M cstContent(Object cstContent){this.put("cstContent", cstContent);return this;};
	 	/** and cst_content is null */
 		public M cstContentNull(){if(this.get("cstContentNot")==null)this.put("cstContentNot", "");this.put("cstContent", null);return this;};
 		/** not .... */
 		public M cstContentNot(){this.put("cstContentNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M cstUpdateTime(Object cstUpdateTime){this.put("cstUpdateTime", cstUpdateTime);return this;};
	 	/** and cst_update_time is null */
 		public M cstUpdateTimeNull(){if(this.get("cstUpdateTimeNot")==null)this.put("cstUpdateTimeNot", "");this.put("cstUpdateTime", null);return this;};
 		/** not .... */
 		public M cstUpdateTimeNot(){this.put("cstUpdateTimeNot", "not");return this;};
 		/** and cst_update_time >= ? */
 		public M cstUpdateTimeStart(Object start){this.put("cstUpdateTimeStart", start);return this;};			
 		/** and cst_update_time <= ? */
 		public M cstUpdateTimeEnd(Object end){this.put("cstUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cstAddTime(Object cstAddTime){this.put("cstAddTime", cstAddTime);return this;};
	 	/** and cst_add_time is null */
 		public M cstAddTimeNull(){if(this.get("cstAddTimeNot")==null)this.put("cstAddTimeNot", "");this.put("cstAddTime", null);return this;};
 		/** not .... */
 		public M cstAddTimeNot(){this.put("cstAddTimeNot", "not");return this;};
 		/** and cst_add_time >= ? */
 		public M cstAddTimeStart(Object start){this.put("cstAddTimeStart", start);return this;};			
 		/** and cst_add_time <= ? */
 		public M cstAddTimeEnd(Object end){this.put("cstAddTimeEnd", end);return this;};
		/** 状态 [非空]   0:待处理 1:提醒报警 2:已完成     **/
		public M cstStatus(Object cstStatus){this.put("cstStatus", cstStatus);return this;};
	 	/** and cst_status is null */
 		public M cstStatusNull(){if(this.get("cstStatusNot")==null)this.put("cstStatusNot", "");this.put("cstStatus", null);return this;};
 		/** not .... */
 		public M cstStatusNot(){this.put("cstStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有待办事项 **/
		public @api List<CsTodo> list(Integer size){
			return getCsTodoList(this,size);
		}
		/** 获取待办事项分页 **/
		public @api Page<CsTodo> page(int page,int size){
			return getCsTodoPage(page, size , this);
		}
		/** 根据查询条件取待办事项 **/
		public @api CsTodo get(){
			return getCsTodo(this);
		}
		/** 获取待办事项数 **/
		public @api Long count(){
			return getCsTodoCount(this);
		}
		/** 获取待办事项表达式 **/
		public @api <T> T eval(String strEval){
			return getCsTodoEval(strEval,this);
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
			updateCsTodo(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cstId="cstId";
		/** 待办事标题 [非空]       **/
		public final static @type(String.class) @like String cstTitle="cstTitle";
		/** 设定提醒时间        **/
		public final static @type(Date.class)  String cstAlarmTime="cstAlarmTime";
	 	/** and cst_alarm_time >= ? */
 		public final static @type(Date.class) String cstAlarmTimeStart="cstAlarmTimeStart";
 		/** and cst_alarm_time <= ? */
 		public final static @type(Date.class) String cstAlarmTimeEnd="cstAlarmTimeEnd";
		/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String cstUser="cstUser";
		/** 待办事内容        **/
		public final static @type(String.class) @like String cstContent="cstContent";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cstUpdateTime="cstUpdateTime";
	 	/** and cst_update_time >= ? */
 		public final static @type(Date.class) String cstUpdateTimeStart="cstUpdateTimeStart";
 		/** and cst_update_time <= ? */
 		public final static @type(Date.class) String cstUpdateTimeEnd="cstUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cstAddTime="cstAddTime";
	 	/** and cst_add_time >= ? */
 		public final static @type(Date.class) String cstAddTimeStart="cstAddTimeStart";
 		/** and cst_add_time <= ? */
 		public final static @type(Date.class) String cstAddTimeEnd="cstAddTimeEnd";
		/** 状态 [非空]   0:待处理 1:提醒报警 2:已完成     **/
		public final static @type(Short.class)  String cstStatus="cstStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cstId="cst_id";
		/** 待办事标题 [非空]       **/
		public final static String cstTitle="cst_title";
		/** 设定提醒时间        **/
		public final static String cstAlarmTime="cst_alarm_time";
		/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cstUser="cst_user";
		/** 待办事内容        **/
		public final static String cstContent="cst_content";
		/** 修改时间 [非空]       **/
		public final static String cstUpdateTime="cst_update_time";
		/** 添加时间 [非空]       **/
		public final static String cstAddTime="cst_add_time";
		/** 状态 [非空]   0:待处理 1:提醒报警 2:已完成     **/
		public final static String cstStatus="cst_status";
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
				$.Set(name,CsTodo.getCsTodo(params));
			else
				$.Set(name,CsTodo.getCsTodoList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取待办事项数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsTodo) $.GetRequest("CsTodo$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsTodo.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsTodo.getCsTodo(params);
			else
				value = CsTodo.getCsTodoList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsTodo.Get($.add(CsTodo.F.cstId,param));
		else if(!$.empty(param.toString()))
			value = CsTodo.get(Long.valueOf(param.toString()));
		$.SetRequest("CsTodo$"+param.hashCode(), value);
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
	public void mergeSet(CsTodo old){
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