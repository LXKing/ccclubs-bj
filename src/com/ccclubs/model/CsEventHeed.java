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

@namespace("systematic/eventheed")
public @caption("事件关注") @table("cs_event_heed") class CsEventHeed implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cseh_id")   @primary  @note("  ") Long csehId;// 主键 非空     
	private @caption("事件类型") @column("cseh_event_type")    @relate("$csehEventType") @RelateClass(CsEventType.class)  @note("  ") Long csehEventType;// 非空     
 	private CsEventType $csehEventType;//关联对象[系统事件类型]
	private @caption("关注人") @column("cseh_heed_user")    @relate("$csehHeedUser") @RelateClass(SrvUser.class)  @note("  ") Long csehHeedUser;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csehHeedUser;//关联对象[用户]
	private @caption("处理方式") @column("cseh_heed_type")    @note(" 0:消息提示 1:系统通知 3:邮件通知 2:短信通知 4:微信通知  ") String csehHeedType;// 非空 0:消息提示 1:系统通知 3:邮件通知 2:短信通知 4:微信通知     
	private @caption("修改时间") @column("cseh_update_time")    @note("  ") Date csehUpdateTime;// 非空     
	private @caption("添加时间") @column("cseh_add_time")    @note("  ") Date csehAddTime;// 非空     
	private @caption("状态") @column("cseh_status")    @note(" 1:正常 0:无效  ") Short csehStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsEventHeed(){
	
	}
	
	//主键构造函数
	public CsEventHeed(Long id){
		this.csehId = id;
	}
	
	/**所有字段构造函数 CsEventHeed(csehEventType,csehHeedUser,csehHeedType,csehUpdateTime,csehAddTime,csehStatus)
	 CsEventHeed(
	 	$.getLong("csehEventType")//事件类型 [非空]
	 	,$.getLong("csehHeedUser")//关注人 [非空]
	 	,$.getString("csehHeedType")//处理方式 [非空]
	 	,$.getDate("csehUpdateTime")//修改时间 [非空]
	 	,$.getDate("csehAddTime")//添加时间 [非空]
	 	,$.getShort("csehStatus")//状态 [非空]
	 )
	**/
	public CsEventHeed(Long csehEventType,Long csehHeedUser,String csehHeedType,Date csehUpdateTime,Date csehAddTime,Short csehStatus){
		this.csehEventType=csehEventType;
		this.csehHeedUser=csehHeedUser;
		this.csehHeedType=csehHeedType;
		this.csehUpdateTime=csehUpdateTime;
		this.csehAddTime=csehAddTime;
		this.csehStatus=csehStatus;
	}
	
	//设置非空字段
	public CsEventHeed setNotNull(Long csehEventType,Long csehHeedUser,String csehHeedType,Date csehUpdateTime,Date csehAddTime,Short csehStatus){
		this.csehEventType=csehEventType;
		this.csehHeedUser=csehHeedUser;
		this.csehHeedType=csehHeedType;
		this.csehUpdateTime=csehUpdateTime;
		this.csehAddTime=csehAddTime;
		this.csehStatus=csehStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsEventHeed csehId(Long csehId){
		this.csehId = csehId;
		this.setSeted(F.csehId);
		return this;
	}
	/** 事件类型 [非空] [CsEventType]      **/
	public CsEventHeed csehEventType(Long csehEventType){
		this.csehEventType = csehEventType;
		this.setSeted(F.csehEventType);
		return this;
	}
	/** 关注人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsEventHeed csehHeedUser(Long csehHeedUser){
		this.csehHeedUser = csehHeedUser;
		this.setSeted(F.csehHeedUser);
		return this;
	}
	/** 处理方式 [非空]   0:消息提示 1:系统通知 3:邮件通知 2:短信通知 4:微信通知     **/
	public CsEventHeed csehHeedType(String csehHeedType){
		this.csehHeedType = csehHeedType;
		this.setSeted(F.csehHeedType);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsEventHeed csehUpdateTime(Date csehUpdateTime){
		this.csehUpdateTime = csehUpdateTime;
		this.setSeted(F.csehUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsEventHeed csehAddTime(Date csehAddTime){
		this.csehAddTime = csehAddTime;
		this.setSeted(F.csehAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsEventHeed csehStatus(Short csehStatus){
		this.csehStatus = csehStatus;
		this.setSeted(F.csehStatus);
		return this;
	}
	
	
	//克隆对象
	public CsEventHeed clone(){
		CsEventHeed clone = new CsEventHeed();
		clone.csehEventType=this.csehEventType;
		clone.csehHeedUser=this.csehHeedUser;
		clone.csehHeedType=this.csehHeedType;
		clone.csehUpdateTime=this.csehUpdateTime;
		clone.csehAddTime=this.csehAddTime;
		clone.csehStatus=this.csehStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取事件关注
	 * @param id
	 * @return
	 */
	public static @api CsEventHeed get(Long id){		
		return getCsEventHeedById(id);
	}
	/**
	 * 获取所有事件关注
	 * @return
	 */
	public static @api List<CsEventHeed> list(Map params,Integer size){
		return getCsEventHeedList(params,size);
	}
	/**
	 * 获取事件关注分页
	 * @return
	 */
	public static @api Page<CsEventHeed> page(int page,int size,Map params){
		return getCsEventHeedPage(page, size , params);
	}
	/**
	 * 根据查询条件取事件关注
	 * @param params
	 * @return
	 */
	public static @api CsEventHeed Get(Map params){
		return getCsEventHeed(params);
	}
	/**
	 * 获取事件关注数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsEventHeedCount(params);
	}
	/**
	 * 获取事件关注数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsEventHeedEval(eval,params);
	}
	
	/**
	 * 根据ID取事件关注
	 * @param id
	 * @return
	 */
	public static @api CsEventHeed getCsEventHeedById(Long id){		
		CsEventHeed csEventHeed = (CsEventHeed) $.GetRequest("CsEventHeed$"+id);
		if(csEventHeed!=null)
			return csEventHeed;
		com.ccclubs.service.admin.ICsEventHeedService csEventHeedService = $.GetSpringBean("csEventHeedService");		
		return csEventHeedService.getCsEventHeedById(id);
	}
	
	
	/**
	 * 根据ID取事件关注的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsEventHeed.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsEventHeed csEventHeed = get(id);
		if(csEventHeed!=null){
			String strValue = csEventHeed.getCsehId$();
			if(!"CsehId".equals("CsehId"))
				strValue+="("+csEventHeed.getCsehId$()+")";
			MemCache.setValue(CsEventHeed.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsehId$();
		if(!"CsehId".equals("CsehId"))
			keyValue+="("+this.getCsehId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有事件关注
	 * @return
	 */
	public static @api List<CsEventHeed> getCsEventHeedList(Map params,Integer size){
		com.ccclubs.service.admin.ICsEventHeedService csEventHeedService = $.GetSpringBean("csEventHeedService");
		return csEventHeedService.getCsEventHeedList(params, size);
	}
	
	/**
	 * 获取事件关注分页
	 * @return
	 */
	public static @api Page<CsEventHeed> getCsEventHeedPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsEventHeedService csEventHeedService = $.GetSpringBean("csEventHeedService");
		return csEventHeedService.getCsEventHeedPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取事件关注
	 * @param params
	 * @return
	 */
	public static @api CsEventHeed getCsEventHeed(Map params){
		com.ccclubs.service.admin.ICsEventHeedService csEventHeedService = $.GetSpringBean("csEventHeedService");
		return csEventHeedService.getCsEventHeed(params);
	}
	
	/**
	 * 获取事件关注数
	 * @return
	 */
	public static @api Long getCsEventHeedCount(Map params){
		com.ccclubs.service.admin.ICsEventHeedService csEventHeedService = $.GetSpringBean("csEventHeedService");
		return csEventHeedService.getCsEventHeedCount(params);
	}
		
		
	/**
	 * 获取事件关注自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsEventHeedEval(String eval,Map params){
		com.ccclubs.service.admin.ICsEventHeedService csEventHeedService = $.GetSpringBean("csEventHeedService");
		return csEventHeedService.getCsEventHeedEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsEventHeed(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsEventHeedService csEventHeedService = $.GetSpringBean("csEventHeedService");
		csEventHeedService.updateCsEventHeedByConfirm(set, where);
	}
	
	
	/**
	 * 保存事件关注对象
	 * @param params
	 * @return
	 */
	public CsEventHeed save(){
		com.ccclubs.service.admin.ICsEventHeedService csEventHeedService = $.GetSpringBean("csEventHeedService");
		if(this.getCsehId()!=null)
			csEventHeedService.updateCsEventHeed(this);
		else
			return csEventHeedService.saveCsEventHeed(this);
		return this;
	}
	
	
	/**
	 * 更新事件关注对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsEventHeedService csEventHeedService = $.GetSpringBean("csEventHeedService");
		csEventHeedService.updateCsEventHeed$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsEventHeedService csEventHeedService = $.GetSpringBean("csEventHeedService");
		if($.equals($.config("logic_delete"),"true"))
			csEventHeedService.removeCsEventHeedById(this.getCsehId());
		else
			csEventHeedService.deleteCsEventHeedById(this.getCsehId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsEventHeedService csEventHeedService = $.GetSpringBean("csEventHeedService");
		return csEventHeedService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsehId(){
		return this.csehId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsehId$(){
		String strValue="";
		 strValue=$.str(this.getCsehId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsehId(Long csehId){
		this.csehId = csehId;
		this.setSeted(F.csehId);
	}
	/*******************************事件类型**********************************/	
	/**
	* 事件类型 [非空] [CsEventType]     
	**/
	public Long getCsehEventType(){
		return this.csehEventType;
	}
	/**
	* 获取事件类型格式化(toString)
	**/
	public String getCsehEventType$(){
		String strValue="";
		if(this.getCsehEventType()!=null){
				strValue+=$.str(CsEventType.getKeyValue(this.getCsehEventType()));
		}			
	 	 return strValue;
	}
	/**
	* 事件类型 [非空] [CsEventType]     
	**/
	public void setCsehEventType(Long csehEventType){
		this.csehEventType = csehEventType;
		this.setSeted(F.csehEventType);
	}
	/**
	* 获取关联对象[系统事件类型]
	**/	 			
 	public CsEventType get$csehEventType(){
 		com.ccclubs.model.CsEventType csEventType = (com.ccclubs.model.CsEventType) $.GetRequest("CsEventType$"+this.getCsehEventType());
 		if(csEventType!=null)
			return csEventType;
		if(this.getCsehEventType()!=null){
 			csEventType = CsEventType.get(this.getCsehEventType());
 		}
 		$.SetRequest("CsEventType$"+this.getCsehEventType(), csEventType);
	 	return csEventType;
	}
	/*******************************关注人**********************************/	
	/**
	* 关注人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsehHeedUser(){
		return this.csehHeedUser;
	}
	/**
	* 获取关注人格式化(toString)
	**/
	public String getCsehHeedUser$(){
		String strValue="";
		if(this.getCsehHeedUser()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsehHeedUser()));
		}			
	 	 return strValue;
	}
	/**
	* 关注人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsehHeedUser(Long csehHeedUser){
		this.csehHeedUser = csehHeedUser;
		this.setSeted(F.csehHeedUser);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csehHeedUser(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsehHeedUser());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsehHeedUser()!=null){
 			srvUser = SrvUser.get(this.getCsehHeedUser());
 		}
 		$.SetRequest("SrvUser$"+this.getCsehHeedUser(), srvUser);
	 	return srvUser;
	}
	/*******************************处理方式**********************************/	
	/**
	* 处理方式 [非空]   0:消息提示 1:系统通知 3:邮件通知 2:短信通知 4:微信通知    
	**/
	public String getCsehHeedType(){
		return this.csehHeedType;
	}
	/**
	* 获取处理方式格式化(toString)
	**/
	public String getCsehHeedType$(){
		String strValue="";
		if($.idin(this.getCsehHeedType(),0))
			strValue+=$.str("消息提示,");								
		if($.idin(this.getCsehHeedType(),1))
			strValue+=$.str("系统通知,");								
		if($.idin(this.getCsehHeedType(),3))
			strValue+=$.str("邮件通知,");								
		if($.idin(this.getCsehHeedType(),2))
			strValue+=$.str("短信通知,");								
		if($.idin(this.getCsehHeedType(),4))
			strValue+=$.str("微信通知,");								
	 	 return strValue;
	}
	/**
	* 处理方式 [非空]   0:消息提示 1:系统通知 3:邮件通知 2:短信通知 4:微信通知    
	**/
	public void setCsehHeedType(String csehHeedType){
		this.csehHeedType = csehHeedType;
		this.setSeted(F.csehHeedType);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsehUpdateTime(){
		return this.csehUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsehUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsehUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsehUpdateTime(Date csehUpdateTime){
		this.csehUpdateTime = csehUpdateTime;
		this.setSeted(F.csehUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsehAddTime(){
		return this.csehAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsehAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsehAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsehAddTime(Date csehAddTime){
		this.csehAddTime = csehAddTime;
		this.setSeted(F.csehAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsehStatus(){
		return this.csehStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsehStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsehStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsehStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsehStatus(Short csehStatus){
		this.csehStatus = csehStatus;
		this.setSeted(F.csehStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsEventHeed.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsEventHeed.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsEventHeed.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsEventHeed.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsEventHeed.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsEventHeed.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsEventHeed.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsEventHeed.this);
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
		public M csehId(Object csehId){this.put("csehId", csehId);return this;};
	 	/** and cseh_id is null */
 		public M csehIdNull(){if(this.get("csehIdNot")==null)this.put("csehIdNot", "");this.put("csehId", null);return this;};
 		/** not .... */
 		public M csehIdNot(){this.put("csehIdNot", "not");return this;};
		/** 事件类型 [非空] [CsEventType]      **/
		public M csehEventType(Object csehEventType){this.put("csehEventType", csehEventType);return this;};
	 	/** and cseh_event_type is null */
 		public M csehEventTypeNull(){if(this.get("csehEventTypeNot")==null)this.put("csehEventTypeNot", "");this.put("csehEventType", null);return this;};
 		/** not .... */
 		public M csehEventTypeNot(){this.put("csehEventTypeNot", "not");return this;};
		public M csehEventType$on(CsEventType.M value){
			this.put("CsEventType", value);
			this.put("csehEventType$on", value);
			return this;
		};	
		/** 关注人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csehHeedUser(Object csehHeedUser){this.put("csehHeedUser", csehHeedUser);return this;};
	 	/** and cseh_heed_user is null */
 		public M csehHeedUserNull(){if(this.get("csehHeedUserNot")==null)this.put("csehHeedUserNot", "");this.put("csehHeedUser", null);return this;};
 		/** not .... */
 		public M csehHeedUserNot(){this.put("csehHeedUserNot", "not");return this;};
		/** 处理方式 [非空]   0:消息提示 1:系统通知 3:邮件通知 2:短信通知 4:微信通知     **/
		public M csehHeedType(Object csehHeedType){this.put("csehHeedType", csehHeedType);return this;};
	 	/** and cseh_heed_type is null */
 		public M csehHeedTypeNull(){if(this.get("csehHeedTypeNot")==null)this.put("csehHeedTypeNot", "");this.put("csehHeedType", null);return this;};
 		/** not .... */
 		public M csehHeedTypeNot(){this.put("csehHeedTypeNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csehUpdateTime(Object csehUpdateTime){this.put("csehUpdateTime", csehUpdateTime);return this;};
	 	/** and cseh_update_time is null */
 		public M csehUpdateTimeNull(){if(this.get("csehUpdateTimeNot")==null)this.put("csehUpdateTimeNot", "");this.put("csehUpdateTime", null);return this;};
 		/** not .... */
 		public M csehUpdateTimeNot(){this.put("csehUpdateTimeNot", "not");return this;};
 		/** and cseh_update_time >= ? */
 		public M csehUpdateTimeStart(Object start){this.put("csehUpdateTimeStart", start);return this;};			
 		/** and cseh_update_time <= ? */
 		public M csehUpdateTimeEnd(Object end){this.put("csehUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csehAddTime(Object csehAddTime){this.put("csehAddTime", csehAddTime);return this;};
	 	/** and cseh_add_time is null */
 		public M csehAddTimeNull(){if(this.get("csehAddTimeNot")==null)this.put("csehAddTimeNot", "");this.put("csehAddTime", null);return this;};
 		/** not .... */
 		public M csehAddTimeNot(){this.put("csehAddTimeNot", "not");return this;};
 		/** and cseh_add_time >= ? */
 		public M csehAddTimeStart(Object start){this.put("csehAddTimeStart", start);return this;};			
 		/** and cseh_add_time <= ? */
 		public M csehAddTimeEnd(Object end){this.put("csehAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csehStatus(Object csehStatus){this.put("csehStatus", csehStatus);return this;};
	 	/** and cseh_status is null */
 		public M csehStatusNull(){if(this.get("csehStatusNot")==null)this.put("csehStatusNot", "");this.put("csehStatus", null);return this;};
 		/** not .... */
 		public M csehStatusNot(){this.put("csehStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有事件关注 **/
		public @api List<CsEventHeed> list(Integer size){
			return getCsEventHeedList(this,size);
		}
		/** 获取事件关注分页 **/
		public @api Page<CsEventHeed> page(int page,int size){
			return getCsEventHeedPage(page, size , this);
		}
		/** 根据查询条件取事件关注 **/
		public @api CsEventHeed get(){
			return getCsEventHeed(this);
		}
		/** 获取事件关注数 **/
		public @api Long count(){
			return getCsEventHeedCount(this);
		}
		/** 获取事件关注表达式 **/
		public @api <T> T eval(String strEval){
			return getCsEventHeedEval(strEval,this);
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
			updateCsEventHeed(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csehId="csehId";
		/** 事件类型 [非空] [CsEventType]      **/
		public final static @type(Long.class)  String csehEventType="csehEventType";
		/** 关注人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csehHeedUser="csehHeedUser";
		/** 处理方式 [非空]   0:消息提示 1:系统通知 3:邮件通知 2:短信通知 4:微信通知     **/
		public final static @type(String.class) @like String csehHeedType="csehHeedType";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csehUpdateTime="csehUpdateTime";
	 	/** and cseh_update_time >= ? */
 		public final static @type(Date.class) String csehUpdateTimeStart="csehUpdateTimeStart";
 		/** and cseh_update_time <= ? */
 		public final static @type(Date.class) String csehUpdateTimeEnd="csehUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csehAddTime="csehAddTime";
	 	/** and cseh_add_time >= ? */
 		public final static @type(Date.class) String csehAddTimeStart="csehAddTimeStart";
 		/** and cseh_add_time <= ? */
 		public final static @type(Date.class) String csehAddTimeEnd="csehAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csehStatus="csehStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csehId="cseh_id";
		/** 事件类型 [非空] [CsEventType]      **/
		public final static String csehEventType="cseh_event_type";
		/** 关注人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csehHeedUser="cseh_heed_user";
		/** 处理方式 [非空]   0:消息提示 1:系统通知 3:邮件通知 2:短信通知 4:微信通知     **/
		public final static String csehHeedType="cseh_heed_type";
		/** 修改时间 [非空]       **/
		public final static String csehUpdateTime="cseh_update_time";
		/** 添加时间 [非空]       **/
		public final static String csehAddTime="cseh_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csehStatus="cseh_status";
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
				$.Set(name,CsEventHeed.getCsEventHeed(params));
			else
				$.Set(name,CsEventHeed.getCsEventHeedList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取事件关注数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsEventHeed) $.GetRequest("CsEventHeed$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsEventHeed.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsEventHeed.getCsEventHeed(params);
			else
				value = CsEventHeed.getCsEventHeedList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsEventHeed.Get($.add(CsEventHeed.F.csehId,param));
		else if(!$.empty(param.toString()))
			value = CsEventHeed.get(Long.valueOf(param.toString()));
		$.SetRequest("CsEventHeed$"+param.hashCode(), value);
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
	public void mergeSet(CsEventHeed old){
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