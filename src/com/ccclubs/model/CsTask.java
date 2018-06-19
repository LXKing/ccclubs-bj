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

@namespace("configurator/task")
public @caption("数据任务") @table("cs_task") class CsTask implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cst_id")   @primary  @note("  ") Long cstId;// 主键 非空     
	private @caption("任务名称") @column("cst_name")    @note("  ") String cstName;// 非空     
	private @caption("任务标识") @column("cst_flag")    @note("  ") String cstFlag;// 非空     
	private @caption("处理人员") @column("cst_user_ids")    @relate("$cstUserIds") @RelateClass(SrvUser.class)  @note("  ") String cstUserIds;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cstUserIds;//关联对象[用户]
	private @caption("SQL语句") @column("cst_sql")    @note("  ") String cstSql;// 非空     
	private @caption("消息提醒") @column("cst_tips")    @note("  消息提醒格式{sql字段}") String cstTips;// 非空  消息提醒格式{sql字段}   
	private @caption("处理地址") @column("cst_url")    @note("  ") String cstUrl;//     
	private @caption("编辑时间") @column("cst_edit_time")    @note("  ") Date cstEditTime;// 非空     
	private @caption("状态") @column("cst_status")    @note(" 1:正常 0:无效  ") Short cstStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsTask(){
	
	}
	
	//主键构造函数
	public CsTask(Long id){
		this.cstId = id;
	}
	
	/**所有字段构造函数 CsTask(cstName,cstFlag,cstUserIds,cstSql,cstTips,cstUrl,cstEditTime,cstStatus)
	 CsTask(
	 	$.getString("cstName")//任务名称 [非空]
	 	,$.getString("cstFlag")//任务标识 [非空]
	 	,$.getString("cstUserIds")//处理人员
	 	,$.getString("cstSql")//SQL语句 [非空]
	 	,$.getString("cstTips")//消息提醒 [非空]
	 	,$.getString("cstUrl")//处理地址
	 	,$.getDate("cstEditTime")//编辑时间 [非空]
	 	,$.getShort("cstStatus")//状态 [非空]
	 )
	**/
	public CsTask(String cstName,String cstFlag,String cstUserIds,String cstSql,String cstTips,String cstUrl,Date cstEditTime,Short cstStatus){
		this.cstName=cstName;
		this.cstFlag=cstFlag;
		this.cstUserIds=cstUserIds;
		this.cstSql=cstSql;
		this.cstTips=cstTips;
		this.cstUrl=cstUrl;
		this.cstEditTime=cstEditTime;
		this.cstStatus=cstStatus;
	}
	
	//设置非空字段
	public CsTask setNotNull(String cstName,String cstFlag,String cstSql,String cstTips,Date cstEditTime,Short cstStatus){
		this.cstName=cstName;
		this.cstFlag=cstFlag;
		this.cstSql=cstSql;
		this.cstTips=cstTips;
		this.cstEditTime=cstEditTime;
		this.cstStatus=cstStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsTask cstId(Long cstId){
		this.cstId = cstId;
		this.setSeted(F.cstId);
		return this;
	}
	/** 任务名称 [非空]       **/
	public CsTask cstName(String cstName){
		this.cstName = cstName;
		this.setSeted(F.cstName);
		return this;
	}
	/** 任务标识 [非空]       **/
	public CsTask cstFlag(String cstFlag){
		this.cstFlag = cstFlag;
		this.setSeted(F.cstFlag);
		return this;
	}
	/** 处理人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsTask cstUserIds(String cstUserIds){
		this.cstUserIds = cstUserIds;
		this.setSeted(F.cstUserIds);
		return this;
	}
	/** SQL语句 [非空]       **/
	public CsTask cstSql(String cstSql){
		this.cstSql = cstSql;
		this.setSeted(F.cstSql);
		return this;
	}
	/** 消息提醒 [非空]    消息提醒格式{sql字段}   **/
	public CsTask cstTips(String cstTips){
		this.cstTips = cstTips;
		this.setSeted(F.cstTips);
		return this;
	}
	/** 处理地址        **/
	public CsTask cstUrl(String cstUrl){
		this.cstUrl = cstUrl;
		this.setSeted(F.cstUrl);
		return this;
	}
	/** 编辑时间 [非空]       **/
	public CsTask cstEditTime(Date cstEditTime){
		this.cstEditTime = cstEditTime;
		this.setSeted(F.cstEditTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsTask cstStatus(Short cstStatus){
		this.cstStatus = cstStatus;
		this.setSeted(F.cstStatus);
		return this;
	}
	
	
	//克隆对象
	public CsTask clone(){
		CsTask clone = new CsTask();
		clone.cstName=this.cstName;
		clone.cstFlag=this.cstFlag;
		clone.cstSql=this.cstSql;
		clone.cstTips=this.cstTips;
		clone.cstEditTime=this.cstEditTime;
		clone.cstStatus=this.cstStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取数据任务
	 * @param id
	 * @return
	 */
	public static @api CsTask get(Long id){		
		return getCsTaskById(id);
	}
	/**
	 * 获取所有数据任务
	 * @return
	 */
	public static @api List<CsTask> list(Map params,Integer size){
		return getCsTaskList(params,size);
	}
	/**
	 * 获取数据任务分页
	 * @return
	 */
	public static @api Page<CsTask> page(int page,int size,Map params){
		return getCsTaskPage(page, size , params);
	}
	/**
	 * 根据查询条件取数据任务
	 * @param params
	 * @return
	 */
	public static @api CsTask Get(Map params){
		return getCsTask(params);
	}
	/**
	 * 获取数据任务数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsTaskCount(params);
	}
	/**
	 * 获取数据任务数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsTaskEval(eval,params);
	}
	
	/**
	 * 根据ID取数据任务
	 * @param id
	 * @return
	 */
	public static @api CsTask getCsTaskById(Long id){		
		CsTask csTask = (CsTask) $.GetRequest("CsTask$"+id);
		if(csTask!=null)
			return csTask;
		com.ccclubs.service.admin.ICsTaskService csTaskService = $.GetSpringBean("csTaskService");		
		return csTaskService.getCsTaskById(id);
	}
	
	
	/**
	 * 根据ID取数据任务的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsTask.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsTask csTask = get(id);
		if(csTask!=null){
			String strValue = csTask.getCstName$();
			if(!"CstName".equals("CstName"))
				strValue+="("+csTask.getCstName$()+")";
			MemCache.setValue(CsTask.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCstName$();
		if(!"CstName".equals("CstName"))
			keyValue+="("+this.getCstName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有数据任务
	 * @return
	 */
	public static @api List<CsTask> getCsTaskList(Map params,Integer size){
		com.ccclubs.service.admin.ICsTaskService csTaskService = $.GetSpringBean("csTaskService");
		return csTaskService.getCsTaskList(params, size);
	}
	
	/**
	 * 获取数据任务分页
	 * @return
	 */
	public static @api Page<CsTask> getCsTaskPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsTaskService csTaskService = $.GetSpringBean("csTaskService");
		return csTaskService.getCsTaskPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取数据任务
	 * @param params
	 * @return
	 */
	public static @api CsTask getCsTask(Map params){
		com.ccclubs.service.admin.ICsTaskService csTaskService = $.GetSpringBean("csTaskService");
		return csTaskService.getCsTask(params);
	}
	
	/**
	 * 获取数据任务数
	 * @return
	 */
	public static @api Long getCsTaskCount(Map params){
		com.ccclubs.service.admin.ICsTaskService csTaskService = $.GetSpringBean("csTaskService");
		return csTaskService.getCsTaskCount(params);
	}
		
		
	/**
	 * 获取数据任务自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsTaskEval(String eval,Map params){
		com.ccclubs.service.admin.ICsTaskService csTaskService = $.GetSpringBean("csTaskService");
		return csTaskService.getCsTaskEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsTask(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsTaskService csTaskService = $.GetSpringBean("csTaskService");
		csTaskService.updateCsTaskByConfirm(set, where);
	}
	
	
	/**
	 * 保存数据任务对象
	 * @param params
	 * @return
	 */
	public CsTask save(){
		com.ccclubs.service.admin.ICsTaskService csTaskService = $.GetSpringBean("csTaskService");
		if(this.getCstId()!=null)
			csTaskService.updateCsTask(this);
		else
			return csTaskService.saveCsTask(this);
		return this;
	}
	
	
	/**
	 * 更新数据任务对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsTaskService csTaskService = $.GetSpringBean("csTaskService");
		csTaskService.updateCsTask$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsTaskService csTaskService = $.GetSpringBean("csTaskService");
			csTaskService.deleteCsTaskById(this.getCstId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsTaskService csTaskService = $.GetSpringBean("csTaskService");
		return csTaskService.executeTransaction(function);
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
	/*******************************任务名称**********************************/	
	/**
	* 任务名称 [非空]      
	**/
	public String getCstName(){
		return this.cstName;
	}
	/**
	* 获取任务名称格式化(toString)
	**/
	public String getCstName$(){
		String strValue="";
		 strValue=$.str(this.getCstName());
	 	 return strValue;
	}
	/**
	* 任务名称 [非空]      
	**/
	public void setCstName(String cstName){
		this.cstName = cstName;
		this.setSeted(F.cstName);
	}
	/*******************************任务标识**********************************/	
	/**
	* 任务标识 [非空]      
	**/
	public String getCstFlag(){
		return this.cstFlag;
	}
	/**
	* 获取任务标识格式化(toString)
	**/
	public String getCstFlag$(){
		String strValue="";
		 strValue=$.str(this.getCstFlag());
	 	 return strValue;
	}
	/**
	* 任务标识 [非空]      
	**/
	public void setCstFlag(String cstFlag){
		this.cstFlag = cstFlag;
		this.setSeted(F.cstFlag);
	}
	/*******************************处理人员**********************************/	
	/**
	* 处理人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public String getCstUserIds(){
		return this.cstUserIds;
	}
	/**
	* 获取处理人员格式化(toString)
	**/
	public String getCstUserIds$(){
		String strValue="";
			com.ccclubs.model.SrvUser srvUser = com.ccclubs.model.SrvUser.getSrvUser($.add("suFamily",this.getCstUserIds()));
		if(srvUser!=null)
			strValue+="["+$.str(srvUser.getSuRealName())+"]";
	 	 return strValue;
	}
	/**
	* 处理人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCstUserIds(String cstUserIds){
		this.cstUserIds = cstUserIds;
		this.setSeted(F.cstUserIds);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cstUserIds(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCstUserIds());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCstUserIds()!=null){
 			srvUser = SrvUser.Get($.add(SrvUser.F.suId,this.getCstUserIds()));
 		}
 		$.SetRequest("SrvUser$"+this.getCstUserIds(), srvUser);
	 	return srvUser;
	}
	/*******************************SQL语句**********************************/	
	/**
	* SQL语句 [非空]      
	**/
	public String getCstSql(){
		return this.cstSql;
	}
	/**
	* 获取SQL语句格式化(toString)
	**/
	public String getCstSql$(){
		String strValue="";
		 strValue=$.str(this.getCstSql());
	 	 return strValue;
	}
	/**
	* SQL语句 [非空]      
	**/
	public void setCstSql(String cstSql){
		this.cstSql = cstSql;
		this.setSeted(F.cstSql);
	}
	/*******************************消息提醒**********************************/	
	/**
	* 消息提醒 [非空]    消息提醒格式{sql字段}  
	**/
	public String getCstTips(){
		return this.cstTips;
	}
	/**
	* 获取消息提醒格式化(toString)
	**/
	public String getCstTips$(){
		String strValue="";
		 strValue=$.str(this.getCstTips());
	 	 return strValue;
	}
	/**
	* 消息提醒 [非空]    消息提醒格式{sql字段}  
	**/
	public void setCstTips(String cstTips){
		this.cstTips = cstTips;
		this.setSeted(F.cstTips);
	}
	/*******************************处理地址**********************************/	
	/**
	* 处理地址       
	**/
	public String getCstUrl(){
		return this.cstUrl;
	}
	/**
	* 获取处理地址格式化(toString)
	**/
	public String getCstUrl$(){
		String strValue="";
		 strValue=$.str(this.getCstUrl());
	 	 return strValue;
	}
	/**
	* 处理地址       
	**/
	public void setCstUrl(String cstUrl){
		this.cstUrl = cstUrl;
		this.setSeted(F.cstUrl);
	}
	/*******************************编辑时间**********************************/	
	/**
	* 编辑时间 [非空]      
	**/
	public Date getCstEditTime(){
		return this.cstEditTime;
	}
	/**
	* 获取编辑时间格式化(toString)
	**/
	public String getCstEditTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCstEditTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 编辑时间 [非空]      
	**/
	public void setCstEditTime(Date cstEditTime){
		this.cstEditTime = cstEditTime;
		this.setSeted(F.cstEditTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCstStatus(){
		return this.cstStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCstStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCstStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCstStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCstStatus(Short cstStatus){
		this.cstStatus = cstStatus;
		this.setSeted(F.cstStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsTask.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsTask.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsTask.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsTask.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsTask.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsTask.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsTask.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsTask.this);
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
		/** 任务名称 [非空]       **/
		public M cstName(Object cstName){this.put("cstName", cstName);return this;};
	 	/** and cst_name is null */
 		public M cstNameNull(){if(this.get("cstNameNot")==null)this.put("cstNameNot", "");this.put("cstName", null);return this;};
 		/** not .... */
 		public M cstNameNot(){this.put("cstNameNot", "not");return this;};
		/** 任务标识 [非空]       **/
		public M cstFlag(Object cstFlag){this.put("cstFlag", cstFlag);return this;};
	 	/** and cst_flag is null */
 		public M cstFlagNull(){if(this.get("cstFlagNot")==null)this.put("cstFlagNot", "");this.put("cstFlag", null);return this;};
 		/** not .... */
 		public M cstFlagNot(){this.put("cstFlagNot", "not");return this;};
		/** 处理人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cstUserIds(Object cstUserIds){this.put("cstUserIds", cstUserIds);return this;};
	 	/** and cst_user_ids is null */
 		public M cstUserIdsNull(){if(this.get("cstUserIdsNot")==null)this.put("cstUserIdsNot", "");this.put("cstUserIds", null);return this;};
 		/** not .... */
 		public M cstUserIdsNot(){this.put("cstUserIdsNot", "not");return this;};
		/** SQL语句 [非空]       **/
		public M cstSql(Object cstSql){this.put("cstSql", cstSql);return this;};
	 	/** and cst_sql is null */
 		public M cstSqlNull(){if(this.get("cstSqlNot")==null)this.put("cstSqlNot", "");this.put("cstSql", null);return this;};
 		/** not .... */
 		public M cstSqlNot(){this.put("cstSqlNot", "not");return this;};
		/** 消息提醒 [非空]    消息提醒格式{sql字段}   **/
		public M cstTips(Object cstTips){this.put("cstTips", cstTips);return this;};
	 	/** and cst_tips is null */
 		public M cstTipsNull(){if(this.get("cstTipsNot")==null)this.put("cstTipsNot", "");this.put("cstTips", null);return this;};
 		/** not .... */
 		public M cstTipsNot(){this.put("cstTipsNot", "not");return this;};
		/** 处理地址        **/
		public M cstUrl(Object cstUrl){this.put("cstUrl", cstUrl);return this;};
	 	/** and cst_url is null */
 		public M cstUrlNull(){if(this.get("cstUrlNot")==null)this.put("cstUrlNot", "");this.put("cstUrl", null);return this;};
 		/** not .... */
 		public M cstUrlNot(){this.put("cstUrlNot", "not");return this;};
		/** 编辑时间 [非空]       **/
		public M cstEditTime(Object cstEditTime){this.put("cstEditTime", cstEditTime);return this;};
	 	/** and cst_edit_time is null */
 		public M cstEditTimeNull(){if(this.get("cstEditTimeNot")==null)this.put("cstEditTimeNot", "");this.put("cstEditTime", null);return this;};
 		/** not .... */
 		public M cstEditTimeNot(){this.put("cstEditTimeNot", "not");return this;};
 		/** and cst_edit_time >= ? */
 		public M cstEditTimeStart(Object start){this.put("cstEditTimeStart", start);return this;};			
 		/** and cst_edit_time <= ? */
 		public M cstEditTimeEnd(Object end){this.put("cstEditTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M cstStatus(Object cstStatus){this.put("cstStatus", cstStatus);return this;};
	 	/** and cst_status is null */
 		public M cstStatusNull(){if(this.get("cstStatusNot")==null)this.put("cstStatusNot", "");this.put("cstStatus", null);return this;};
 		/** not .... */
 		public M cstStatusNot(){this.put("cstStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有数据任务 **/
		public @api List<CsTask> list(Integer size){
			return getCsTaskList(this,size);
		}
		/** 获取数据任务分页 **/
		public @api Page<CsTask> page(int page,int size){
			return getCsTaskPage(page, size , this);
		}
		/** 根据查询条件取数据任务 **/
		public @api CsTask get(){
			return getCsTask(this);
		}
		/** 获取数据任务数 **/
		public @api Long count(){
			return getCsTaskCount(this);
		}
		/** 获取数据任务表达式 **/
		public @api <T> T eval(String strEval){
			return getCsTaskEval(strEval,this);
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
			updateCsTask(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cstId="cstId";
		/** 任务名称 [非空]       **/
		public final static @type(String.class) @like String cstName="cstName";
		/** 任务标识 [非空]       **/
		public final static @type(String.class) @like String cstFlag="cstFlag";
		/** 处理人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(String.class)  String cstUserIds="cstUserIds";
		/** SQL语句 [非空]       **/
		public final static @type(String.class) @like String cstSql="cstSql";
		/** 消息提醒 [非空]    消息提醒格式{sql字段}   **/
		public final static @type(String.class) @like String cstTips="cstTips";
		/** 处理地址        **/
		public final static @type(String.class) @like String cstUrl="cstUrl";
		/** 编辑时间 [非空]       **/
		public final static @type(Date.class)  String cstEditTime="cstEditTime";
	 	/** and cst_edit_time >= ? */
 		public final static @type(Date.class) String cstEditTimeStart="cstEditTimeStart";
 		/** and cst_edit_time <= ? */
 		public final static @type(Date.class) String cstEditTimeEnd="cstEditTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String cstStatus="cstStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cstId="cst_id";
		/** 任务名称 [非空]       **/
		public final static String cstName="cst_name";
		/** 任务标识 [非空]       **/
		public final static String cstFlag="cst_flag";
		/** 处理人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cstUserIds="cst_user_ids";
		/** SQL语句 [非空]       **/
		public final static String cstSql="cst_sql";
		/** 消息提醒 [非空]    消息提醒格式{sql字段}   **/
		public final static String cstTips="cst_tips";
		/** 处理地址        **/
		public final static String cstUrl="cst_url";
		/** 编辑时间 [非空]       **/
		public final static String cstEditTime="cst_edit_time";
		/** 状态 [非空]   1:正常 0:无效     **/
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
				$.Set(name,CsTask.getCsTask(params));
			else
				$.Set(name,CsTask.getCsTaskList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取数据任务数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsTask) $.GetRequest("CsTask$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsTask.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsTask.getCsTask(params);
			else
				value = CsTask.getCsTaskList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsTask.Get($.add(CsTask.F.cstId,param));
		else if(!$.empty(param.toString()))
			value = CsTask.get(Long.valueOf(param.toString()));
		$.SetRequest("CsTask$"+param.hashCode(), value);
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
	public void mergeSet(CsTask old){
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