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

@namespace("extension/channellog")
public @caption("渠道跟踪维护") @table("cs_channel_log") class CsChannelLog implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cscl_id")   @primary  @note("  ") Long csclId;// 主键 非空     
	private @caption("城市") @column("cscl_host")    @relate("$csclHost") @RelateClass(SrvHost.class)  @note("  ") Long csclHost;// 非空     
 	private SrvHost $csclHost;//关联对象[运营城市]
	private @caption("标题") @column("cscl_title")    @note("  ") String csclTitle;// 非空     
	private @caption("所属渠道") @column("cscl_plan")    @relate("$csclPlan") @RelateClass(CsChannel.class)  @note("  ") Long csclPlan;// 非空     
 	private CsChannel $csclPlan;//关联对象[访问渠道]
	private @caption("描述") @column("cscl_profile")    @note("  ") String csclProfile;// 非空     
	private @caption("执行人") @column("cscl_user")    @relate("$csclUser") @RelateClass(SrvUser.class)  @note("  ") Long csclUser;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csclUser;//关联对象[用户]
	private @caption("修改时间") @column("cscl_update_time")    @note("  ") Date csclUpdateTime;// 非空     
	private @caption("添加时间") @column("cscl_add_time")    @note("  ") Date csclAddTime;// 非空     
	private @caption("状态") @column("cscl_status")    @note(" 0:默认  ") Short csclStatus;// 非空 0:默认     
	
	//默认构造函数
	public CsChannelLog(){
	
	}
	
	//主键构造函数
	public CsChannelLog(Long id){
		this.csclId = id;
	}
	
	/**所有字段构造函数 CsChannelLog(csclHost,csclTitle,csclPlan,csclProfile,csclUser,csclUpdateTime,csclAddTime,csclStatus)
	 CsChannelLog(
	 	$.getLong("csclHost")//城市 [非空]
	 	,$.getString("csclTitle")//标题 [非空]
	 	,$.getLong("csclPlan")//所属渠道 [非空]
	 	,$.getString("csclProfile")//描述 [非空]
	 	,$.getLong("csclUser")//执行人 [非空]
	 	,$.getDate("csclUpdateTime")//修改时间 [非空]
	 	,$.getDate("csclAddTime")//添加时间 [非空]
	 	,$.getShort("csclStatus")//状态 [非空]
	 )
	**/
	public CsChannelLog(Long csclHost,String csclTitle,Long csclPlan,String csclProfile,Long csclUser,Date csclUpdateTime,Date csclAddTime,Short csclStatus){
		this.csclHost=csclHost;
		this.csclTitle=csclTitle;
		this.csclPlan=csclPlan;
		this.csclProfile=csclProfile;
		this.csclUser=csclUser;
		this.csclUpdateTime=csclUpdateTime;
		this.csclAddTime=csclAddTime;
		this.csclStatus=csclStatus;
	}
	
	//设置非空字段
	public CsChannelLog setNotNull(Long csclHost,String csclTitle,Long csclPlan,String csclProfile,Long csclUser,Date csclUpdateTime,Date csclAddTime,Short csclStatus){
		this.csclHost=csclHost;
		this.csclTitle=csclTitle;
		this.csclPlan=csclPlan;
		this.csclProfile=csclProfile;
		this.csclUser=csclUser;
		this.csclUpdateTime=csclUpdateTime;
		this.csclAddTime=csclAddTime;
		this.csclStatus=csclStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsChannelLog csclId(Long csclId){
		this.csclId = csclId;
		this.setSeted(F.csclId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsChannelLog csclHost(Long csclHost){
		this.csclHost = csclHost;
		this.setSeted(F.csclHost);
		return this;
	}
	/** 标题 [非空]       **/
	public CsChannelLog csclTitle(String csclTitle){
		this.csclTitle = csclTitle;
		this.setSeted(F.csclTitle);
		return this;
	}
	/** 所属渠道 [非空] [CsChannel]      **/
	public CsChannelLog csclPlan(Long csclPlan){
		this.csclPlan = csclPlan;
		this.setSeted(F.csclPlan);
		return this;
	}
	/** 描述 [非空]       **/
	public CsChannelLog csclProfile(String csclProfile){
		this.csclProfile = csclProfile;
		this.setSeted(F.csclProfile);
		return this;
	}
	/** 执行人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsChannelLog csclUser(Long csclUser){
		this.csclUser = csclUser;
		this.setSeted(F.csclUser);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsChannelLog csclUpdateTime(Date csclUpdateTime){
		this.csclUpdateTime = csclUpdateTime;
		this.setSeted(F.csclUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsChannelLog csclAddTime(Date csclAddTime){
		this.csclAddTime = csclAddTime;
		this.setSeted(F.csclAddTime);
		return this;
	}
	/** 状态 [非空]   0:默认     **/
	public CsChannelLog csclStatus(Short csclStatus){
		this.csclStatus = csclStatus;
		this.setSeted(F.csclStatus);
		return this;
	}
	
	
	//克隆对象
	public CsChannelLog clone(){
		CsChannelLog clone = new CsChannelLog();
		clone.csclHost=this.csclHost;
		clone.csclTitle=this.csclTitle;
		clone.csclPlan=this.csclPlan;
		clone.csclProfile=this.csclProfile;
		clone.csclUser=this.csclUser;
		clone.csclUpdateTime=this.csclUpdateTime;
		clone.csclAddTime=this.csclAddTime;
		clone.csclStatus=this.csclStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取渠道跟踪维护
	 * @param id
	 * @return
	 */
	public static @api CsChannelLog get(Long id){		
		return getCsChannelLogById(id);
	}
	/**
	 * 获取所有渠道跟踪维护
	 * @return
	 */
	public static @api List<CsChannelLog> list(Map params,Integer size){
		return getCsChannelLogList(params,size);
	}
	/**
	 * 获取渠道跟踪维护分页
	 * @return
	 */
	public static @api Page<CsChannelLog> page(int page,int size,Map params){
		return getCsChannelLogPage(page, size , params);
	}
	/**
	 * 根据查询条件取渠道跟踪维护
	 * @param params
	 * @return
	 */
	public static @api CsChannelLog Get(Map params){
		return getCsChannelLog(params);
	}
	/**
	 * 获取渠道跟踪维护数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsChannelLogCount(params);
	}
	/**
	 * 获取渠道跟踪维护数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsChannelLogEval(eval,params);
	}
	
	/**
	 * 根据ID取渠道跟踪维护
	 * @param id
	 * @return
	 */
	public static @api CsChannelLog getCsChannelLogById(Long id){		
		CsChannelLog csChannelLog = (CsChannelLog) $.GetRequest("CsChannelLog$"+id);
		if(csChannelLog!=null)
			return csChannelLog;
		com.ccclubs.service.admin.ICsChannelLogService csChannelLogService = $.GetSpringBean("csChannelLogService");		
		return csChannelLogService.getCsChannelLogById(id);
	}
	
	
	/**
	 * 根据ID取渠道跟踪维护的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsChannelLog.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsChannelLog csChannelLog = get(id);
		if(csChannelLog!=null){
			String strValue = csChannelLog.getCsclTitle$();
			if(!"CsclTitle".equals("CsclTitle"))
				strValue+="("+csChannelLog.getCsclTitle$()+")";
			MemCache.setValue(CsChannelLog.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsclTitle$();
		if(!"CsclTitle".equals("CsclTitle"))
			keyValue+="("+this.getCsclTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有渠道跟踪维护
	 * @return
	 */
	public static @api List<CsChannelLog> getCsChannelLogList(Map params,Integer size){
		com.ccclubs.service.admin.ICsChannelLogService csChannelLogService = $.GetSpringBean("csChannelLogService");
		return csChannelLogService.getCsChannelLogList(params, size);
	}
	
	/**
	 * 获取渠道跟踪维护分页
	 * @return
	 */
	public static @api Page<CsChannelLog> getCsChannelLogPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsChannelLogService csChannelLogService = $.GetSpringBean("csChannelLogService");
		return csChannelLogService.getCsChannelLogPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取渠道跟踪维护
	 * @param params
	 * @return
	 */
	public static @api CsChannelLog getCsChannelLog(Map params){
		com.ccclubs.service.admin.ICsChannelLogService csChannelLogService = $.GetSpringBean("csChannelLogService");
		return csChannelLogService.getCsChannelLog(params);
	}
	
	/**
	 * 获取渠道跟踪维护数
	 * @return
	 */
	public static @api Long getCsChannelLogCount(Map params){
		com.ccclubs.service.admin.ICsChannelLogService csChannelLogService = $.GetSpringBean("csChannelLogService");
		return csChannelLogService.getCsChannelLogCount(params);
	}
		
		
	/**
	 * 获取渠道跟踪维护自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsChannelLogEval(String eval,Map params){
		com.ccclubs.service.admin.ICsChannelLogService csChannelLogService = $.GetSpringBean("csChannelLogService");
		return csChannelLogService.getCsChannelLogEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsChannelLog(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsChannelLogService csChannelLogService = $.GetSpringBean("csChannelLogService");
		csChannelLogService.updateCsChannelLogByConfirm(set, where);
	}
	
	
	/**
	 * 保存渠道跟踪维护对象
	 * @param params
	 * @return
	 */
	public CsChannelLog save(){
		com.ccclubs.service.admin.ICsChannelLogService csChannelLogService = $.GetSpringBean("csChannelLogService");
		if(this.getCsclId()!=null)
			csChannelLogService.updateCsChannelLog(this);
		else
			return csChannelLogService.saveCsChannelLog(this);
		return this;
	}
	
	
	/**
	 * 更新渠道跟踪维护对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsChannelLogService csChannelLogService = $.GetSpringBean("csChannelLogService");
		csChannelLogService.updateCsChannelLog$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsChannelLogService csChannelLogService = $.GetSpringBean("csChannelLogService");
		if($.equals($.config("logic_delete"),"true"))
			csChannelLogService.removeCsChannelLogById(this.getCsclId());
		else
			csChannelLogService.deleteCsChannelLogById(this.getCsclId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsChannelLogService csChannelLogService = $.GetSpringBean("csChannelLogService");
		return csChannelLogService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsclId(){
		return this.csclId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsclId$(){
		String strValue="";
		 strValue=$.str(this.getCsclId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsclId(Long csclId){
		this.csclId = csclId;
		this.setSeted(F.csclId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsclHost(){
		return this.csclHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsclHost$(){
		String strValue="";
		if(this.getCsclHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsclHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsclHost(Long csclHost){
		this.csclHost = csclHost;
		this.setSeted(F.csclHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csclHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsclHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsclHost()!=null){
 			srvHost = SrvHost.get(this.getCsclHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsclHost(), srvHost);
	 	return srvHost;
	}
	/*******************************标题**********************************/	
	/**
	* 标题 [非空]      
	**/
	public String getCsclTitle(){
		return this.csclTitle;
	}
	/**
	* 获取标题格式化(toString)
	**/
	public String getCsclTitle$(){
		String strValue="";
		 strValue=$.str(this.getCsclTitle());
	 	 return strValue;
	}
	/**
	* 标题 [非空]      
	**/
	public void setCsclTitle(String csclTitle){
		this.csclTitle = csclTitle;
		this.setSeted(F.csclTitle);
	}
	/*******************************所属渠道**********************************/	
	/**
	* 所属渠道 [非空] [CsChannel]     
	**/
	public Long getCsclPlan(){
		return this.csclPlan;
	}
	/**
	* 获取所属渠道格式化(toString)
	**/
	public String getCsclPlan$(){
		String strValue="";
		if(this.getCsclPlan()!=null){
				strValue+=$.str(CsChannel.getKeyValue(this.getCsclPlan()));
		}			
	 	 return strValue;
	}
	/**
	* 所属渠道 [非空] [CsChannel]     
	**/
	public void setCsclPlan(Long csclPlan){
		this.csclPlan = csclPlan;
		this.setSeted(F.csclPlan);
	}
	/**
	* 获取关联对象[访问渠道]
	**/	 			
 	public CsChannel get$csclPlan(){
 		com.ccclubs.model.CsChannel csChannel = (com.ccclubs.model.CsChannel) $.GetRequest("CsChannel$"+this.getCsclPlan());
 		if(csChannel!=null)
			return csChannel;
		if(this.getCsclPlan()!=null){
 			csChannel = CsChannel.get(this.getCsclPlan());
 		}
 		$.SetRequest("CsChannel$"+this.getCsclPlan(), csChannel);
	 	return csChannel;
	}
	/*******************************描述**********************************/	
	/**
	* 描述 [非空]      
	**/
	public String getCsclProfile(){
		return this.csclProfile;
	}
	/**
	* 获取描述格式化(toString)
	**/
	public String getCsclProfile$(){
		String strValue="";
		 strValue=$.str(this.getCsclProfile());
	 	 return strValue;
	}
	/**
	* 描述 [非空]      
	**/
	public void setCsclProfile(String csclProfile){
		this.csclProfile = csclProfile;
		this.setSeted(F.csclProfile);
	}
	/*******************************执行人**********************************/	
	/**
	* 执行人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsclUser(){
		return this.csclUser;
	}
	/**
	* 获取执行人格式化(toString)
	**/
	public String getCsclUser$(){
		String strValue="";
		if(this.getCsclUser()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsclUser()));
		}			
	 	 return strValue;
	}
	/**
	* 执行人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsclUser(Long csclUser){
		this.csclUser = csclUser;
		this.setSeted(F.csclUser);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csclUser(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsclUser());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsclUser()!=null){
 			srvUser = SrvUser.get(this.getCsclUser());
 		}
 		$.SetRequest("SrvUser$"+this.getCsclUser(), srvUser);
	 	return srvUser;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsclUpdateTime(){
		return this.csclUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsclUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsclUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsclUpdateTime(Date csclUpdateTime){
		this.csclUpdateTime = csclUpdateTime;
		this.setSeted(F.csclUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsclAddTime(){
		return this.csclAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsclAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsclAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsclAddTime(Date csclAddTime){
		this.csclAddTime = csclAddTime;
		this.setSeted(F.csclAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:默认    
	**/
	public Short getCsclStatus(){
		return this.csclStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsclStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsclStatus()),"0"))
			strValue=$.str("默认");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:默认    
	**/
	public void setCsclStatus(Short csclStatus){
		this.csclStatus = csclStatus;
		this.setSeted(F.csclStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsChannelLog.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsChannelLog.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsChannelLog.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsChannelLog.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsChannelLog.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsChannelLog.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsChannelLog.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsChannelLog.this);
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
		public M csclId(Object csclId){this.put("csclId", csclId);return this;};
	 	/** and cscl_id is null */
 		public M csclIdNull(){if(this.get("csclIdNot")==null)this.put("csclIdNot", "");this.put("csclId", null);return this;};
 		/** not .... */
 		public M csclIdNot(){this.put("csclIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csclHost(Object csclHost){this.put("csclHost", csclHost);return this;};
	 	/** and cscl_host is null */
 		public M csclHostNull(){if(this.get("csclHostNot")==null)this.put("csclHostNot", "");this.put("csclHost", null);return this;};
 		/** not .... */
 		public M csclHostNot(){this.put("csclHostNot", "not");return this;};
		/** 标题 [非空]       **/
		public M csclTitle(Object csclTitle){this.put("csclTitle", csclTitle);return this;};
	 	/** and cscl_title is null */
 		public M csclTitleNull(){if(this.get("csclTitleNot")==null)this.put("csclTitleNot", "");this.put("csclTitle", null);return this;};
 		/** not .... */
 		public M csclTitleNot(){this.put("csclTitleNot", "not");return this;};
		/** 所属渠道 [非空] [CsChannel]      **/
		public M csclPlan(Object csclPlan){this.put("csclPlan", csclPlan);return this;};
	 	/** and cscl_plan is null */
 		public M csclPlanNull(){if(this.get("csclPlanNot")==null)this.put("csclPlanNot", "");this.put("csclPlan", null);return this;};
 		/** not .... */
 		public M csclPlanNot(){this.put("csclPlanNot", "not");return this;};
		public M csclPlan$on(CsChannel.M value){
			this.put("CsChannel", value);
			this.put("csclPlan$on", value);
			return this;
		};	
		/** 描述 [非空]       **/
		public M csclProfile(Object csclProfile){this.put("csclProfile", csclProfile);return this;};
	 	/** and cscl_profile is null */
 		public M csclProfileNull(){if(this.get("csclProfileNot")==null)this.put("csclProfileNot", "");this.put("csclProfile", null);return this;};
 		/** not .... */
 		public M csclProfileNot(){this.put("csclProfileNot", "not");return this;};
		/** 执行人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csclUser(Object csclUser){this.put("csclUser", csclUser);return this;};
	 	/** and cscl_user is null */
 		public M csclUserNull(){if(this.get("csclUserNot")==null)this.put("csclUserNot", "");this.put("csclUser", null);return this;};
 		/** not .... */
 		public M csclUserNot(){this.put("csclUserNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csclUpdateTime(Object csclUpdateTime){this.put("csclUpdateTime", csclUpdateTime);return this;};
	 	/** and cscl_update_time is null */
 		public M csclUpdateTimeNull(){if(this.get("csclUpdateTimeNot")==null)this.put("csclUpdateTimeNot", "");this.put("csclUpdateTime", null);return this;};
 		/** not .... */
 		public M csclUpdateTimeNot(){this.put("csclUpdateTimeNot", "not");return this;};
 		/** and cscl_update_time >= ? */
 		public M csclUpdateTimeStart(Object start){this.put("csclUpdateTimeStart", start);return this;};			
 		/** and cscl_update_time <= ? */
 		public M csclUpdateTimeEnd(Object end){this.put("csclUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csclAddTime(Object csclAddTime){this.put("csclAddTime", csclAddTime);return this;};
	 	/** and cscl_add_time is null */
 		public M csclAddTimeNull(){if(this.get("csclAddTimeNot")==null)this.put("csclAddTimeNot", "");this.put("csclAddTime", null);return this;};
 		/** not .... */
 		public M csclAddTimeNot(){this.put("csclAddTimeNot", "not");return this;};
 		/** and cscl_add_time >= ? */
 		public M csclAddTimeStart(Object start){this.put("csclAddTimeStart", start);return this;};			
 		/** and cscl_add_time <= ? */
 		public M csclAddTimeEnd(Object end){this.put("csclAddTimeEnd", end);return this;};
		/** 状态 [非空]   0:默认     **/
		public M csclStatus(Object csclStatus){this.put("csclStatus", csclStatus);return this;};
	 	/** and cscl_status is null */
 		public M csclStatusNull(){if(this.get("csclStatusNot")==null)this.put("csclStatusNot", "");this.put("csclStatus", null);return this;};
 		/** not .... */
 		public M csclStatusNot(){this.put("csclStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有渠道跟踪维护 **/
		public @api List<CsChannelLog> list(Integer size){
			return getCsChannelLogList(this,size);
		}
		/** 获取渠道跟踪维护分页 **/
		public @api Page<CsChannelLog> page(int page,int size){
			return getCsChannelLogPage(page, size , this);
		}
		/** 根据查询条件取渠道跟踪维护 **/
		public @api CsChannelLog get(){
			return getCsChannelLog(this);
		}
		/** 获取渠道跟踪维护数 **/
		public @api Long count(){
			return getCsChannelLogCount(this);
		}
		/** 获取渠道跟踪维护表达式 **/
		public @api <T> T eval(String strEval){
			return getCsChannelLogEval(strEval,this);
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
			updateCsChannelLog(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csclId="csclId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csclHost="csclHost";
		/** 标题 [非空]       **/
		public final static @type(String.class) @like String csclTitle="csclTitle";
		/** 所属渠道 [非空] [CsChannel]      **/
		public final static @type(Long.class)  String csclPlan="csclPlan";
		/** 描述 [非空]       **/
		public final static @type(String.class) @like String csclProfile="csclProfile";
		/** 执行人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csclUser="csclUser";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csclUpdateTime="csclUpdateTime";
	 	/** and cscl_update_time >= ? */
 		public final static @type(Date.class) String csclUpdateTimeStart="csclUpdateTimeStart";
 		/** and cscl_update_time <= ? */
 		public final static @type(Date.class) String csclUpdateTimeEnd="csclUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csclAddTime="csclAddTime";
	 	/** and cscl_add_time >= ? */
 		public final static @type(Date.class) String csclAddTimeStart="csclAddTimeStart";
 		/** and cscl_add_time <= ? */
 		public final static @type(Date.class) String csclAddTimeEnd="csclAddTimeEnd";
		/** 状态 [非空]   0:默认     **/
		public final static @type(Short.class)  String csclStatus="csclStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csclId="cscl_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csclHost="cscl_host";
		/** 标题 [非空]       **/
		public final static String csclTitle="cscl_title";
		/** 所属渠道 [非空] [CsChannel]      **/
		public final static String csclPlan="cscl_plan";
		/** 描述 [非空]       **/
		public final static String csclProfile="cscl_profile";
		/** 执行人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csclUser="cscl_user";
		/** 修改时间 [非空]       **/
		public final static String csclUpdateTime="cscl_update_time";
		/** 添加时间 [非空]       **/
		public final static String csclAddTime="cscl_add_time";
		/** 状态 [非空]   0:默认     **/
		public final static String csclStatus="cscl_status";
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
				$.Set(name,CsChannelLog.getCsChannelLog(params));
			else
				$.Set(name,CsChannelLog.getCsChannelLogList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取渠道跟踪维护数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsChannelLog) $.GetRequest("CsChannelLog$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsChannelLog.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsChannelLog.getCsChannelLog(params);
			else
				value = CsChannelLog.getCsChannelLogList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsChannelLog.Get($.add(CsChannelLog.F.csclId,param));
		else if(!$.empty(param.toString()))
			value = CsChannelLog.get(Long.valueOf(param.toString()));
		$.SetRequest("CsChannelLog$"+param.hashCode(), value);
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
	public void mergeSet(CsChannelLog old){
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