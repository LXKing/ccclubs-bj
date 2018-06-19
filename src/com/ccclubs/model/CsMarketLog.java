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

@namespace("operate/marketlog")
public @caption("营销跟踪") @table("cs_market_log") class CsMarketLog implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csml_id")   @primary  @note("  ") Long csmlId;// 主键 非空     
	private @caption("城市") @column("csml_host")    @relate("$csmlHost") @RelateClass(SrvHost.class)  @note("  ") Long csmlHost;// 非空     
 	private SrvHost $csmlHost;//关联对象[运营城市]
	private @caption("标题") @column("csml_title")    @note("  ") String csmlTitle;// 非空     
	private @caption("所属方案") @column("csml_plan")    @relate("$csmlPlan") @RelateClass(CsMarketPlan.class)  @note("  ") Long csmlPlan;// 非空     
 	private CsMarketPlan $csmlPlan;//关联对象[营销方案]
	private @caption("描述") @column("csml_profile")    @note("  ") String csmlProfile;// 非空     
	private @caption("执行人") @column("csml_user")    @relate("$csmlUser") @RelateClass(SrvUser.class)  @note("  ") Long csmlUser;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csmlUser;//关联对象[用户]
	private @caption("修改时间") @column("csml_update_time")    @note("  ") Date csmlUpdateTime;// 非空     
	private @caption("添加时间") @column("csml_add_time")    @note("  ") Date csmlAddTime;// 非空     
	private @caption("状态") @column("csml_status")    @note(" 0:默认  ") Short csmlStatus;// 非空 0:默认     
	
	//默认构造函数
	public CsMarketLog(){
	
	}
	
	//主键构造函数
	public CsMarketLog(Long id){
		this.csmlId = id;
	}
	
	/**所有字段构造函数 CsMarketLog(csmlHost,csmlTitle,csmlPlan,csmlProfile,csmlUser,csmlUpdateTime,csmlAddTime,csmlStatus)
	 CsMarketLog(
	 	$.getLong("csmlHost")//城市 [非空]
	 	,$.getString("csmlTitle")//标题 [非空]
	 	,$.getLong("csmlPlan")//所属方案 [非空]
	 	,$.getString("csmlProfile")//描述 [非空]
	 	,$.getLong("csmlUser")//执行人 [非空]
	 	,$.getDate("csmlUpdateTime")//修改时间 [非空]
	 	,$.getDate("csmlAddTime")//添加时间 [非空]
	 	,$.getShort("csmlStatus")//状态 [非空]
	 )
	**/
	public CsMarketLog(Long csmlHost,String csmlTitle,Long csmlPlan,String csmlProfile,Long csmlUser,Date csmlUpdateTime,Date csmlAddTime,Short csmlStatus){
		this.csmlHost=csmlHost;
		this.csmlTitle=csmlTitle;
		this.csmlPlan=csmlPlan;
		this.csmlProfile=csmlProfile;
		this.csmlUser=csmlUser;
		this.csmlUpdateTime=csmlUpdateTime;
		this.csmlAddTime=csmlAddTime;
		this.csmlStatus=csmlStatus;
	}
	
	//设置非空字段
	public CsMarketLog setNotNull(Long csmlHost,String csmlTitle,Long csmlPlan,String csmlProfile,Long csmlUser,Date csmlUpdateTime,Date csmlAddTime,Short csmlStatus){
		this.csmlHost=csmlHost;
		this.csmlTitle=csmlTitle;
		this.csmlPlan=csmlPlan;
		this.csmlProfile=csmlProfile;
		this.csmlUser=csmlUser;
		this.csmlUpdateTime=csmlUpdateTime;
		this.csmlAddTime=csmlAddTime;
		this.csmlStatus=csmlStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsMarketLog csmlId(Long csmlId){
		this.csmlId = csmlId;
		this.setSeted(F.csmlId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsMarketLog csmlHost(Long csmlHost){
		this.csmlHost = csmlHost;
		this.setSeted(F.csmlHost);
		return this;
	}
	/** 标题 [非空]       **/
	public CsMarketLog csmlTitle(String csmlTitle){
		this.csmlTitle = csmlTitle;
		this.setSeted(F.csmlTitle);
		return this;
	}
	/** 所属方案 [非空] [CsMarketPlan]      **/
	public CsMarketLog csmlPlan(Long csmlPlan){
		this.csmlPlan = csmlPlan;
		this.setSeted(F.csmlPlan);
		return this;
	}
	/** 描述 [非空]       **/
	public CsMarketLog csmlProfile(String csmlProfile){
		this.csmlProfile = csmlProfile;
		this.setSeted(F.csmlProfile);
		return this;
	}
	/** 执行人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsMarketLog csmlUser(Long csmlUser){
		this.csmlUser = csmlUser;
		this.setSeted(F.csmlUser);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsMarketLog csmlUpdateTime(Date csmlUpdateTime){
		this.csmlUpdateTime = csmlUpdateTime;
		this.setSeted(F.csmlUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsMarketLog csmlAddTime(Date csmlAddTime){
		this.csmlAddTime = csmlAddTime;
		this.setSeted(F.csmlAddTime);
		return this;
	}
	/** 状态 [非空]   0:默认     **/
	public CsMarketLog csmlStatus(Short csmlStatus){
		this.csmlStatus = csmlStatus;
		this.setSeted(F.csmlStatus);
		return this;
	}
	
	
	//克隆对象
	public CsMarketLog clone(){
		CsMarketLog clone = new CsMarketLog();
		clone.csmlHost=this.csmlHost;
		clone.csmlTitle=this.csmlTitle;
		clone.csmlPlan=this.csmlPlan;
		clone.csmlProfile=this.csmlProfile;
		clone.csmlUser=this.csmlUser;
		clone.csmlUpdateTime=this.csmlUpdateTime;
		clone.csmlAddTime=this.csmlAddTime;
		clone.csmlStatus=this.csmlStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取营销跟踪
	 * @param id
	 * @return
	 */
	public static @api CsMarketLog get(Long id){		
		return getCsMarketLogById(id);
	}
	/**
	 * 获取所有营销跟踪
	 * @return
	 */
	public static @api List<CsMarketLog> list(Map params,Integer size){
		return getCsMarketLogList(params,size);
	}
	/**
	 * 获取营销跟踪分页
	 * @return
	 */
	public static @api Page<CsMarketLog> page(int page,int size,Map params){
		return getCsMarketLogPage(page, size , params);
	}
	/**
	 * 根据查询条件取营销跟踪
	 * @param params
	 * @return
	 */
	public static @api CsMarketLog Get(Map params){
		return getCsMarketLog(params);
	}
	/**
	 * 获取营销跟踪数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsMarketLogCount(params);
	}
	/**
	 * 获取营销跟踪数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsMarketLogEval(eval,params);
	}
	
	/**
	 * 根据ID取营销跟踪
	 * @param id
	 * @return
	 */
	public static @api CsMarketLog getCsMarketLogById(Long id){		
		CsMarketLog csMarketLog = (CsMarketLog) $.GetRequest("CsMarketLog$"+id);
		if(csMarketLog!=null)
			return csMarketLog;
		com.ccclubs.service.admin.ICsMarketLogService csMarketLogService = $.GetSpringBean("csMarketLogService");		
		return csMarketLogService.getCsMarketLogById(id);
	}
	
	
	/**
	 * 根据ID取营销跟踪的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsMarketLog.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsMarketLog csMarketLog = get(id);
		if(csMarketLog!=null){
			String strValue = csMarketLog.getCsmlTitle$();
			if(!"CsmlTitle".equals("CsmlTitle"))
				strValue+="("+csMarketLog.getCsmlTitle$()+")";
			MemCache.setValue(CsMarketLog.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsmlTitle$();
		if(!"CsmlTitle".equals("CsmlTitle"))
			keyValue+="("+this.getCsmlTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有营销跟踪
	 * @return
	 */
	public static @api List<CsMarketLog> getCsMarketLogList(Map params,Integer size){
		com.ccclubs.service.admin.ICsMarketLogService csMarketLogService = $.GetSpringBean("csMarketLogService");
		return csMarketLogService.getCsMarketLogList(params, size);
	}
	
	/**
	 * 获取营销跟踪分页
	 * @return
	 */
	public static @api Page<CsMarketLog> getCsMarketLogPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsMarketLogService csMarketLogService = $.GetSpringBean("csMarketLogService");
		return csMarketLogService.getCsMarketLogPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取营销跟踪
	 * @param params
	 * @return
	 */
	public static @api CsMarketLog getCsMarketLog(Map params){
		com.ccclubs.service.admin.ICsMarketLogService csMarketLogService = $.GetSpringBean("csMarketLogService");
		return csMarketLogService.getCsMarketLog(params);
	}
	
	/**
	 * 获取营销跟踪数
	 * @return
	 */
	public static @api Long getCsMarketLogCount(Map params){
		com.ccclubs.service.admin.ICsMarketLogService csMarketLogService = $.GetSpringBean("csMarketLogService");
		return csMarketLogService.getCsMarketLogCount(params);
	}
		
		
	/**
	 * 获取营销跟踪自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsMarketLogEval(String eval,Map params){
		com.ccclubs.service.admin.ICsMarketLogService csMarketLogService = $.GetSpringBean("csMarketLogService");
		return csMarketLogService.getCsMarketLogEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsMarketLog(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsMarketLogService csMarketLogService = $.GetSpringBean("csMarketLogService");
		csMarketLogService.updateCsMarketLogByConfirm(set, where);
	}
	
	
	/**
	 * 保存营销跟踪对象
	 * @param params
	 * @return
	 */
	public CsMarketLog save(){
		com.ccclubs.service.admin.ICsMarketLogService csMarketLogService = $.GetSpringBean("csMarketLogService");
		if(this.getCsmlId()!=null)
			csMarketLogService.updateCsMarketLog(this);
		else
			return csMarketLogService.saveCsMarketLog(this);
		return this;
	}
	
	
	/**
	 * 更新营销跟踪对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsMarketLogService csMarketLogService = $.GetSpringBean("csMarketLogService");
		csMarketLogService.updateCsMarketLog$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsMarketLogService csMarketLogService = $.GetSpringBean("csMarketLogService");
		if($.equals($.config("logic_delete"),"true"))
			csMarketLogService.removeCsMarketLogById(this.getCsmlId());
		else
			csMarketLogService.deleteCsMarketLogById(this.getCsmlId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsMarketLogService csMarketLogService = $.GetSpringBean("csMarketLogService");
		return csMarketLogService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsmlId(){
		return this.csmlId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsmlId$(){
		String strValue="";
		 strValue=$.str(this.getCsmlId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsmlId(Long csmlId){
		this.csmlId = csmlId;
		this.setSeted(F.csmlId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsmlHost(){
		return this.csmlHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsmlHost$(){
		String strValue="";
		if(this.getCsmlHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsmlHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsmlHost(Long csmlHost){
		this.csmlHost = csmlHost;
		this.setSeted(F.csmlHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csmlHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsmlHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsmlHost()!=null){
 			srvHost = SrvHost.get(this.getCsmlHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsmlHost(), srvHost);
	 	return srvHost;
	}
	/*******************************标题**********************************/	
	/**
	* 标题 [非空]      
	**/
	public String getCsmlTitle(){
		return this.csmlTitle;
	}
	/**
	* 获取标题格式化(toString)
	**/
	public String getCsmlTitle$(){
		String strValue="";
		 strValue=$.str(this.getCsmlTitle());
	 	 return strValue;
	}
	/**
	* 标题 [非空]      
	**/
	public void setCsmlTitle(String csmlTitle){
		this.csmlTitle = csmlTitle;
		this.setSeted(F.csmlTitle);
	}
	/*******************************所属方案**********************************/	
	/**
	* 所属方案 [非空] [CsMarketPlan]     
	**/
	public Long getCsmlPlan(){
		return this.csmlPlan;
	}
	/**
	* 获取所属方案格式化(toString)
	**/
	public String getCsmlPlan$(){
		String strValue="";
		if(this.getCsmlPlan()!=null){
				strValue+=$.str(CsMarketPlan.getKeyValue(this.getCsmlPlan()));
		}			
	 	 return strValue;
	}
	/**
	* 所属方案 [非空] [CsMarketPlan]     
	**/
	public void setCsmlPlan(Long csmlPlan){
		this.csmlPlan = csmlPlan;
		this.setSeted(F.csmlPlan);
	}
	/**
	* 获取关联对象[营销方案]
	**/	 			
 	public CsMarketPlan get$csmlPlan(){
 		com.ccclubs.model.CsMarketPlan csMarketPlan = (com.ccclubs.model.CsMarketPlan) $.GetRequest("CsMarketPlan$"+this.getCsmlPlan());
 		if(csMarketPlan!=null)
			return csMarketPlan;
		if(this.getCsmlPlan()!=null){
 			csMarketPlan = CsMarketPlan.get(this.getCsmlPlan());
 		}
 		$.SetRequest("CsMarketPlan$"+this.getCsmlPlan(), csMarketPlan);
	 	return csMarketPlan;
	}
	/*******************************描述**********************************/	
	/**
	* 描述 [非空]      
	**/
	public String getCsmlProfile(){
		return this.csmlProfile;
	}
	/**
	* 获取描述格式化(toString)
	**/
	public String getCsmlProfile$(){
		String strValue="";
		 strValue=$.str(this.getCsmlProfile());
	 	 return strValue;
	}
	/**
	* 描述 [非空]      
	**/
	public void setCsmlProfile(String csmlProfile){
		this.csmlProfile = csmlProfile;
		this.setSeted(F.csmlProfile);
	}
	/*******************************执行人**********************************/	
	/**
	* 执行人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsmlUser(){
		return this.csmlUser;
	}
	/**
	* 获取执行人格式化(toString)
	**/
	public String getCsmlUser$(){
		String strValue="";
		if(this.getCsmlUser()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsmlUser()));
		}			
	 	 return strValue;
	}
	/**
	* 执行人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsmlUser(Long csmlUser){
		this.csmlUser = csmlUser;
		this.setSeted(F.csmlUser);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csmlUser(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsmlUser());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsmlUser()!=null){
 			srvUser = SrvUser.get(this.getCsmlUser());
 		}
 		$.SetRequest("SrvUser$"+this.getCsmlUser(), srvUser);
	 	return srvUser;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsmlUpdateTime(){
		return this.csmlUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsmlUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmlUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsmlUpdateTime(Date csmlUpdateTime){
		this.csmlUpdateTime = csmlUpdateTime;
		this.setSeted(F.csmlUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsmlAddTime(){
		return this.csmlAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsmlAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmlAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsmlAddTime(Date csmlAddTime){
		this.csmlAddTime = csmlAddTime;
		this.setSeted(F.csmlAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:默认    
	**/
	public Short getCsmlStatus(){
		return this.csmlStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsmlStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsmlStatus()),"0"))
			strValue=$.str("默认");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:默认    
	**/
	public void setCsmlStatus(Short csmlStatus){
		this.csmlStatus = csmlStatus;
		this.setSeted(F.csmlStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsMarketLog.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMarketLog.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMarketLog.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsMarketLog.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsMarketLog.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMarketLog.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMarketLog.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsMarketLog.this);
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
		public M csmlId(Object csmlId){this.put("csmlId", csmlId);return this;};
	 	/** and csml_id is null */
 		public M csmlIdNull(){if(this.get("csmlIdNot")==null)this.put("csmlIdNot", "");this.put("csmlId", null);return this;};
 		/** not .... */
 		public M csmlIdNot(){this.put("csmlIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csmlHost(Object csmlHost){this.put("csmlHost", csmlHost);return this;};
	 	/** and csml_host is null */
 		public M csmlHostNull(){if(this.get("csmlHostNot")==null)this.put("csmlHostNot", "");this.put("csmlHost", null);return this;};
 		/** not .... */
 		public M csmlHostNot(){this.put("csmlHostNot", "not");return this;};
		/** 标题 [非空]       **/
		public M csmlTitle(Object csmlTitle){this.put("csmlTitle", csmlTitle);return this;};
	 	/** and csml_title is null */
 		public M csmlTitleNull(){if(this.get("csmlTitleNot")==null)this.put("csmlTitleNot", "");this.put("csmlTitle", null);return this;};
 		/** not .... */
 		public M csmlTitleNot(){this.put("csmlTitleNot", "not");return this;};
		/** 所属方案 [非空] [CsMarketPlan]      **/
		public M csmlPlan(Object csmlPlan){this.put("csmlPlan", csmlPlan);return this;};
	 	/** and csml_plan is null */
 		public M csmlPlanNull(){if(this.get("csmlPlanNot")==null)this.put("csmlPlanNot", "");this.put("csmlPlan", null);return this;};
 		/** not .... */
 		public M csmlPlanNot(){this.put("csmlPlanNot", "not");return this;};
		public M csmlPlan$on(CsMarketPlan.M value){
			this.put("CsMarketPlan", value);
			this.put("csmlPlan$on", value);
			return this;
		};	
		/** 描述 [非空]       **/
		public M csmlProfile(Object csmlProfile){this.put("csmlProfile", csmlProfile);return this;};
	 	/** and csml_profile is null */
 		public M csmlProfileNull(){if(this.get("csmlProfileNot")==null)this.put("csmlProfileNot", "");this.put("csmlProfile", null);return this;};
 		/** not .... */
 		public M csmlProfileNot(){this.put("csmlProfileNot", "not");return this;};
		/** 执行人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csmlUser(Object csmlUser){this.put("csmlUser", csmlUser);return this;};
	 	/** and csml_user is null */
 		public M csmlUserNull(){if(this.get("csmlUserNot")==null)this.put("csmlUserNot", "");this.put("csmlUser", null);return this;};
 		/** not .... */
 		public M csmlUserNot(){this.put("csmlUserNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csmlUpdateTime(Object csmlUpdateTime){this.put("csmlUpdateTime", csmlUpdateTime);return this;};
	 	/** and csml_update_time is null */
 		public M csmlUpdateTimeNull(){if(this.get("csmlUpdateTimeNot")==null)this.put("csmlUpdateTimeNot", "");this.put("csmlUpdateTime", null);return this;};
 		/** not .... */
 		public M csmlUpdateTimeNot(){this.put("csmlUpdateTimeNot", "not");return this;};
 		/** and csml_update_time >= ? */
 		public M csmlUpdateTimeStart(Object start){this.put("csmlUpdateTimeStart", start);return this;};			
 		/** and csml_update_time <= ? */
 		public M csmlUpdateTimeEnd(Object end){this.put("csmlUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csmlAddTime(Object csmlAddTime){this.put("csmlAddTime", csmlAddTime);return this;};
	 	/** and csml_add_time is null */
 		public M csmlAddTimeNull(){if(this.get("csmlAddTimeNot")==null)this.put("csmlAddTimeNot", "");this.put("csmlAddTime", null);return this;};
 		/** not .... */
 		public M csmlAddTimeNot(){this.put("csmlAddTimeNot", "not");return this;};
 		/** and csml_add_time >= ? */
 		public M csmlAddTimeStart(Object start){this.put("csmlAddTimeStart", start);return this;};			
 		/** and csml_add_time <= ? */
 		public M csmlAddTimeEnd(Object end){this.put("csmlAddTimeEnd", end);return this;};
		/** 状态 [非空]   0:默认     **/
		public M csmlStatus(Object csmlStatus){this.put("csmlStatus", csmlStatus);return this;};
	 	/** and csml_status is null */
 		public M csmlStatusNull(){if(this.get("csmlStatusNot")==null)this.put("csmlStatusNot", "");this.put("csmlStatus", null);return this;};
 		/** not .... */
 		public M csmlStatusNot(){this.put("csmlStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有营销跟踪 **/
		public @api List<CsMarketLog> list(Integer size){
			return getCsMarketLogList(this,size);
		}
		/** 获取营销跟踪分页 **/
		public @api Page<CsMarketLog> page(int page,int size){
			return getCsMarketLogPage(page, size , this);
		}
		/** 根据查询条件取营销跟踪 **/
		public @api CsMarketLog get(){
			return getCsMarketLog(this);
		}
		/** 获取营销跟踪数 **/
		public @api Long count(){
			return getCsMarketLogCount(this);
		}
		/** 获取营销跟踪表达式 **/
		public @api <T> T eval(String strEval){
			return getCsMarketLogEval(strEval,this);
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
			updateCsMarketLog(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csmlId="csmlId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csmlHost="csmlHost";
		/** 标题 [非空]       **/
		public final static @type(String.class) @like String csmlTitle="csmlTitle";
		/** 所属方案 [非空] [CsMarketPlan]      **/
		public final static @type(Long.class)  String csmlPlan="csmlPlan";
		/** 描述 [非空]       **/
		public final static @type(String.class) @like String csmlProfile="csmlProfile";
		/** 执行人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csmlUser="csmlUser";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csmlUpdateTime="csmlUpdateTime";
	 	/** and csml_update_time >= ? */
 		public final static @type(Date.class) String csmlUpdateTimeStart="csmlUpdateTimeStart";
 		/** and csml_update_time <= ? */
 		public final static @type(Date.class) String csmlUpdateTimeEnd="csmlUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csmlAddTime="csmlAddTime";
	 	/** and csml_add_time >= ? */
 		public final static @type(Date.class) String csmlAddTimeStart="csmlAddTimeStart";
 		/** and csml_add_time <= ? */
 		public final static @type(Date.class) String csmlAddTimeEnd="csmlAddTimeEnd";
		/** 状态 [非空]   0:默认     **/
		public final static @type(Short.class)  String csmlStatus="csmlStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csmlId="csml_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csmlHost="csml_host";
		/** 标题 [非空]       **/
		public final static String csmlTitle="csml_title";
		/** 所属方案 [非空] [CsMarketPlan]      **/
		public final static String csmlPlan="csml_plan";
		/** 描述 [非空]       **/
		public final static String csmlProfile="csml_profile";
		/** 执行人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csmlUser="csml_user";
		/** 修改时间 [非空]       **/
		public final static String csmlUpdateTime="csml_update_time";
		/** 添加时间 [非空]       **/
		public final static String csmlAddTime="csml_add_time";
		/** 状态 [非空]   0:默认     **/
		public final static String csmlStatus="csml_status";
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
				$.Set(name,CsMarketLog.getCsMarketLog(params));
			else
				$.Set(name,CsMarketLog.getCsMarketLogList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取营销跟踪数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsMarketLog) $.GetRequest("CsMarketLog$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsMarketLog.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsMarketLog.getCsMarketLog(params);
			else
				value = CsMarketLog.getCsMarketLogList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsMarketLog.Get($.add(CsMarketLog.F.csmlId,param));
		else if(!$.empty(param.toString()))
			value = CsMarketLog.get(Long.valueOf(param.toString()));
		$.SetRequest("CsMarketLog$"+param.hashCode(), value);
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
	public void mergeSet(CsMarketLog old){
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