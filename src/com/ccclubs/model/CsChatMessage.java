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

@namespace("csm/message")
public @caption("客服消息") @table("cs_chat_message") class CsChatMessage implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cscm_id")   @primary  @note("  ") Long cscmId;// 主键 非空     
	private @caption("城市") @column("cscm_host")    @relate("$cscmHost") @RelateClass(SrvHost.class)  @note("  ") Long cscmHost;// 非空     
 	private SrvHost $cscmHost;//关联对象[运营城市]
	private @caption("消息内容") @column("cscm_content")    @note("  ") String cscmContent;// 非空     
	private @caption("内容类型") @column("cscm_type")    @note(" text:文本 image:图片 news:图文 html:特殊  ") Short cscmType;// 非空 text:文本 image:图片 news:图文 html:特殊     
	private @caption("发送来源") @column("cscm_from")    @note("  ") String cscmFrom;// 非空     
	private @caption("接收目标") @column("cscm_target")    @note("  ") String cscmTarget;// 非空     
	private @caption("修改时间") @column("cscm_update_time")    @note("  ") Date cscmUpdateTime;// 非空     
	private @caption("添加时间") @column("cscm_add_time")    @note("  ") Date cscmAddTime;// 非空     
	private @caption("接收状态") @column("cscm_state")    @note(" 0:未读 1:已读  ") Short cscmState;// 非空 0:未读 1:已读     
	private @caption("状态") @column("cscm_status")    @note(" 1:默认 0:无效  ") Short cscmStatus;// 非空 1:默认 0:无效     
	
	//默认构造函数
	public CsChatMessage(){
	
	}
	
	//主键构造函数
	public CsChatMessage(Long id){
		this.cscmId = id;
	}
	
	/**所有字段构造函数 CsChatMessage(cscmHost,cscmContent,cscmType,cscmFrom,cscmTarget,cscmUpdateTime,cscmAddTime,cscmState,cscmStatus)
	 CsChatMessage(
	 	$.getLong("cscmHost")//城市 [非空]
	 	,$.getString("cscmContent")//消息内容 [非空]
	 	,$.getShort("cscmType")//内容类型 [非空]
	 	,$.getString("cscmFrom")//发送来源 [非空]
	 	,$.getString("cscmTarget")//接收目标 [非空]
	 	,$.getDate("cscmUpdateTime")//修改时间 [非空]
	 	,$.getDate("cscmAddTime")//添加时间 [非空]
	 	,$.getShort("cscmState")//接收状态 [非空]
	 	,$.getShort("cscmStatus")//状态 [非空]
	 )
	**/
	public CsChatMessage(Long cscmHost,String cscmContent,Short cscmType,String cscmFrom,String cscmTarget,Date cscmUpdateTime,Date cscmAddTime,Short cscmState,Short cscmStatus){
		this.cscmHost=cscmHost;
		this.cscmContent=cscmContent;
		this.cscmType=cscmType;
		this.cscmFrom=cscmFrom;
		this.cscmTarget=cscmTarget;
		this.cscmUpdateTime=cscmUpdateTime;
		this.cscmAddTime=cscmAddTime;
		this.cscmState=cscmState;
		this.cscmStatus=cscmStatus;
	}
	
	//设置非空字段
	public CsChatMessage setNotNull(Long cscmHost,String cscmContent,Short cscmType,String cscmFrom,String cscmTarget,Date cscmUpdateTime,Date cscmAddTime,Short cscmState,Short cscmStatus){
		this.cscmHost=cscmHost;
		this.cscmContent=cscmContent;
		this.cscmType=cscmType;
		this.cscmFrom=cscmFrom;
		this.cscmTarget=cscmTarget;
		this.cscmUpdateTime=cscmUpdateTime;
		this.cscmAddTime=cscmAddTime;
		this.cscmState=cscmState;
		this.cscmStatus=cscmStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsChatMessage cscmId(Long cscmId){
		this.cscmId = cscmId;
		this.setSeted(F.cscmId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsChatMessage cscmHost(Long cscmHost){
		this.cscmHost = cscmHost;
		this.setSeted(F.cscmHost);
		return this;
	}
	/** 消息内容 [非空]       **/
	public CsChatMessage cscmContent(String cscmContent){
		this.cscmContent = cscmContent;
		this.setSeted(F.cscmContent);
		return this;
	}
	/** 内容类型 [非空]   text:文本 image:图片 news:图文 html:特殊     **/
	public CsChatMessage cscmType(Short cscmType){
		this.cscmType = cscmType;
		this.setSeted(F.cscmType);
		return this;
	}
	/** 发送来源 [非空]       **/
	public CsChatMessage cscmFrom(String cscmFrom){
		this.cscmFrom = cscmFrom;
		this.setSeted(F.cscmFrom);
		return this;
	}
	/** 接收目标 [非空]       **/
	public CsChatMessage cscmTarget(String cscmTarget){
		this.cscmTarget = cscmTarget;
		this.setSeted(F.cscmTarget);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsChatMessage cscmUpdateTime(Date cscmUpdateTime){
		this.cscmUpdateTime = cscmUpdateTime;
		this.setSeted(F.cscmUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsChatMessage cscmAddTime(Date cscmAddTime){
		this.cscmAddTime = cscmAddTime;
		this.setSeted(F.cscmAddTime);
		return this;
	}
	/** 接收状态 [非空]   0:未读 1:已读     **/
	public CsChatMessage cscmState(Short cscmState){
		this.cscmState = cscmState;
		this.setSeted(F.cscmState);
		return this;
	}
	/** 状态 [非空]   1:默认 0:无效     **/
	public CsChatMessage cscmStatus(Short cscmStatus){
		this.cscmStatus = cscmStatus;
		this.setSeted(F.cscmStatus);
		return this;
	}
	
	
	//克隆对象
	public CsChatMessage clone(){
		CsChatMessage clone = new CsChatMessage();
		clone.cscmHost=this.cscmHost;
		clone.cscmContent=this.cscmContent;
		clone.cscmType=this.cscmType;
		clone.cscmFrom=this.cscmFrom;
		clone.cscmTarget=this.cscmTarget;
		clone.cscmUpdateTime=this.cscmUpdateTime;
		clone.cscmAddTime=this.cscmAddTime;
		clone.cscmState=this.cscmState;
		clone.cscmStatus=this.cscmStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取客服消息
	 * @param id
	 * @return
	 */
	public static @api CsChatMessage get(Long id){		
		return getCsChatMessageById(id);
	}
	/**
	 * 获取所有客服消息
	 * @return
	 */
	public static @api List<CsChatMessage> list(Map params,Integer size){
		return getCsChatMessageList(params,size);
	}
	/**
	 * 获取客服消息分页
	 * @return
	 */
	public static @api Page<CsChatMessage> page(int page,int size,Map params){
		return getCsChatMessagePage(page, size , params);
	}
	/**
	 * 根据查询条件取客服消息
	 * @param params
	 * @return
	 */
	public static @api CsChatMessage Get(Map params){
		return getCsChatMessage(params);
	}
	/**
	 * 获取客服消息数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsChatMessageCount(params);
	}
	/**
	 * 获取客服消息数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsChatMessageEval(eval,params);
	}
	
	/**
	 * 根据ID取客服消息
	 * @param id
	 * @return
	 */
	public static @api CsChatMessage getCsChatMessageById(Long id){		
		CsChatMessage csChatMessage = (CsChatMessage) $.GetRequest("CsChatMessage$"+id);
		if(csChatMessage!=null)
			return csChatMessage;
		com.ccclubs.service.admin.ICsChatMessageService csChatMessageService = $.GetSpringBean("csChatMessageService");		
		return csChatMessageService.getCsChatMessageById(id);
	}
	
	
	/**
	 * 根据ID取客服消息的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsChatMessage.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsChatMessage csChatMessage = get(id);
		if(csChatMessage!=null){
			String strValue = csChatMessage.getCscmContent$();
			if(!"CscmContent".equals("CscmContent"))
				strValue+="("+csChatMessage.getCscmContent$()+")";
			MemCache.setValue(CsChatMessage.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCscmContent$();
		if(!"CscmContent".equals("CscmContent"))
			keyValue+="("+this.getCscmContent$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有客服消息
	 * @return
	 */
	public static @api List<CsChatMessage> getCsChatMessageList(Map params,Integer size){
		com.ccclubs.service.admin.ICsChatMessageService csChatMessageService = $.GetSpringBean("csChatMessageService");
		return csChatMessageService.getCsChatMessageList(params, size);
	}
	
	/**
	 * 获取客服消息分页
	 * @return
	 */
	public static @api Page<CsChatMessage> getCsChatMessagePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsChatMessageService csChatMessageService = $.GetSpringBean("csChatMessageService");
		return csChatMessageService.getCsChatMessagePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取客服消息
	 * @param params
	 * @return
	 */
	public static @api CsChatMessage getCsChatMessage(Map params){
		com.ccclubs.service.admin.ICsChatMessageService csChatMessageService = $.GetSpringBean("csChatMessageService");
		return csChatMessageService.getCsChatMessage(params);
	}
	
	/**
	 * 获取客服消息数
	 * @return
	 */
	public static @api Long getCsChatMessageCount(Map params){
		com.ccclubs.service.admin.ICsChatMessageService csChatMessageService = $.GetSpringBean("csChatMessageService");
		return csChatMessageService.getCsChatMessageCount(params);
	}
		
		
	/**
	 * 获取客服消息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsChatMessageEval(String eval,Map params){
		com.ccclubs.service.admin.ICsChatMessageService csChatMessageService = $.GetSpringBean("csChatMessageService");
		return csChatMessageService.getCsChatMessageEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsChatMessage(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsChatMessageService csChatMessageService = $.GetSpringBean("csChatMessageService");
		csChatMessageService.updateCsChatMessageByConfirm(set, where);
	}
	
	
	/**
	 * 保存客服消息对象
	 * @param params
	 * @return
	 */
	public CsChatMessage save(){
		com.ccclubs.service.admin.ICsChatMessageService csChatMessageService = $.GetSpringBean("csChatMessageService");
		if(this.getCscmId()!=null)
			csChatMessageService.updateCsChatMessage(this);
		else
			return csChatMessageService.saveCsChatMessage(this);
		return this;
	}
	
	
	/**
	 * 更新客服消息对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsChatMessageService csChatMessageService = $.GetSpringBean("csChatMessageService");
		csChatMessageService.updateCsChatMessage$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsChatMessageService csChatMessageService = $.GetSpringBean("csChatMessageService");
		if($.equals($.config("logic_delete"),"true"))
			csChatMessageService.removeCsChatMessageById(this.getCscmId());
		else
			csChatMessageService.deleteCsChatMessageById(this.getCscmId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsChatMessageService csChatMessageService = $.GetSpringBean("csChatMessageService");
		return csChatMessageService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCscmId(){
		return this.cscmId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCscmId$(){
		String strValue="";
		 strValue=$.str(this.getCscmId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCscmId(Long cscmId){
		this.cscmId = cscmId;
		this.setSeted(F.cscmId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCscmHost(){
		return this.cscmHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCscmHost$(){
		String strValue="";
		if(this.getCscmHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCscmHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCscmHost(Long cscmHost){
		this.cscmHost = cscmHost;
		this.setSeted(F.cscmHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cscmHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCscmHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCscmHost()!=null){
 			srvHost = SrvHost.get(this.getCscmHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCscmHost(), srvHost);
	 	return srvHost;
	}
	/*******************************消息内容**********************************/	
	/**
	* 消息内容 [非空]      
	**/
	public String getCscmContent(){
		return this.cscmContent;
	}
	/**
	* 获取消息内容格式化(toString)
	**/
	public String getCscmContent$(){
		String strValue="";
		 strValue=$.str(this.getCscmContent());
	 	 return strValue;
	}
	/**
	* 消息内容 [非空]      
	**/
	public void setCscmContent(String cscmContent){
		this.cscmContent = cscmContent;
		this.setSeted(F.cscmContent);
	}
	/*******************************内容类型**********************************/	
	/**
	* 内容类型 [非空]   text:文本 image:图片 news:图文 html:特殊    
	**/
	public Short getCscmType(){
		return this.cscmType;
	}
	/**
	* 获取内容类型格式化(toString)
	**/
	public String getCscmType$(){
		String strValue="";
		 if($.equals($.str(this.getCscmType()),"text"))
			strValue=$.str("文本");		 
		 if($.equals($.str(this.getCscmType()),"image"))
			strValue=$.str("图片");		 
		 if($.equals($.str(this.getCscmType()),"news"))
			strValue=$.str("图文");		 
		 if($.equals($.str(this.getCscmType()),"html"))
			strValue=$.str("特殊");		 
	 	 return strValue;
	}
	/**
	* 内容类型 [非空]   text:文本 image:图片 news:图文 html:特殊    
	**/
	public void setCscmType(Short cscmType){
		this.cscmType = cscmType;
		this.setSeted(F.cscmType);
	}
	/*******************************发送来源**********************************/	
	/**
	* 发送来源 [非空]      
	**/
	public String getCscmFrom(){
		return this.cscmFrom;
	}
	/**
	* 获取发送来源格式化(toString)
	**/
	public String getCscmFrom$(){
		String strValue="";
		 strValue=$.str(this.getCscmFrom());
	 	 return strValue;
	}
	/**
	* 发送来源 [非空]      
	**/
	public void setCscmFrom(String cscmFrom){
		this.cscmFrom = cscmFrom;
		this.setSeted(F.cscmFrom);
	}
	/*******************************接收目标**********************************/	
	/**
	* 接收目标 [非空]      
	**/
	public String getCscmTarget(){
		return this.cscmTarget;
	}
	/**
	* 获取接收目标格式化(toString)
	**/
	public String getCscmTarget$(){
		String strValue="";
		 strValue=$.str(this.getCscmTarget());
	 	 return strValue;
	}
	/**
	* 接收目标 [非空]      
	**/
	public void setCscmTarget(String cscmTarget){
		this.cscmTarget = cscmTarget;
		this.setSeted(F.cscmTarget);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCscmUpdateTime(){
		return this.cscmUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCscmUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscmUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCscmUpdateTime(Date cscmUpdateTime){
		this.cscmUpdateTime = cscmUpdateTime;
		this.setSeted(F.cscmUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCscmAddTime(){
		return this.cscmAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCscmAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscmAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCscmAddTime(Date cscmAddTime){
		this.cscmAddTime = cscmAddTime;
		this.setSeted(F.cscmAddTime);
	}
	/*******************************接收状态**********************************/	
	/**
	* 接收状态 [非空]   0:未读 1:已读    
	**/
	public Short getCscmState(){
		return this.cscmState;
	}
	/**
	* 获取接收状态格式化(toString)
	**/
	public String getCscmState$(){
		String strValue="";
		 if($.equals($.str(this.getCscmState()),"0"))
			strValue=$.str("未读");		 
		 if($.equals($.str(this.getCscmState()),"1"))
			strValue=$.str("已读");		 
	 	 return strValue;
	}
	/**
	* 接收状态 [非空]   0:未读 1:已读    
	**/
	public void setCscmState(Short cscmState){
		this.cscmState = cscmState;
		this.setSeted(F.cscmState);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:默认 0:无效    
	**/
	public Short getCscmStatus(){
		return this.cscmStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCscmStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCscmStatus()),"1"))
			strValue=$.str("默认");		 
		 if($.equals($.str(this.getCscmStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:默认 0:无效    
	**/
	public void setCscmStatus(Short cscmStatus){
		this.cscmStatus = cscmStatus;
		this.setSeted(F.cscmStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsChatMessage.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsChatMessage.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsChatMessage.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsChatMessage.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsChatMessage.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsChatMessage.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsChatMessage.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsChatMessage.this);
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
		public M cscmId(Object cscmId){this.put("cscmId", cscmId);return this;};
	 	/** and cscm_id is null */
 		public M cscmIdNull(){if(this.get("cscmIdNot")==null)this.put("cscmIdNot", "");this.put("cscmId", null);return this;};
 		/** not .... */
 		public M cscmIdNot(){this.put("cscmIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M cscmHost(Object cscmHost){this.put("cscmHost", cscmHost);return this;};
	 	/** and cscm_host is null */
 		public M cscmHostNull(){if(this.get("cscmHostNot")==null)this.put("cscmHostNot", "");this.put("cscmHost", null);return this;};
 		/** not .... */
 		public M cscmHostNot(){this.put("cscmHostNot", "not");return this;};
		/** 消息内容 [非空]       **/
		public M cscmContent(Object cscmContent){this.put("cscmContent", cscmContent);return this;};
	 	/** and cscm_content is null */
 		public M cscmContentNull(){if(this.get("cscmContentNot")==null)this.put("cscmContentNot", "");this.put("cscmContent", null);return this;};
 		/** not .... */
 		public M cscmContentNot(){this.put("cscmContentNot", "not");return this;};
		/** 内容类型 [非空]   text:文本 image:图片 news:图文 html:特殊     **/
		public M cscmType(Object cscmType){this.put("cscmType", cscmType);return this;};
	 	/** and cscm_type is null */
 		public M cscmTypeNull(){if(this.get("cscmTypeNot")==null)this.put("cscmTypeNot", "");this.put("cscmType", null);return this;};
 		/** not .... */
 		public M cscmTypeNot(){this.put("cscmTypeNot", "not");return this;};
		/** 发送来源 [非空]       **/
		public M cscmFrom(Object cscmFrom){this.put("cscmFrom", cscmFrom);return this;};
	 	/** and cscm_from is null */
 		public M cscmFromNull(){if(this.get("cscmFromNot")==null)this.put("cscmFromNot", "");this.put("cscmFrom", null);return this;};
 		/** not .... */
 		public M cscmFromNot(){this.put("cscmFromNot", "not");return this;};
		/** 接收目标 [非空]       **/
		public M cscmTarget(Object cscmTarget){this.put("cscmTarget", cscmTarget);return this;};
	 	/** and cscm_target is null */
 		public M cscmTargetNull(){if(this.get("cscmTargetNot")==null)this.put("cscmTargetNot", "");this.put("cscmTarget", null);return this;};
 		/** not .... */
 		public M cscmTargetNot(){this.put("cscmTargetNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M cscmUpdateTime(Object cscmUpdateTime){this.put("cscmUpdateTime", cscmUpdateTime);return this;};
	 	/** and cscm_update_time is null */
 		public M cscmUpdateTimeNull(){if(this.get("cscmUpdateTimeNot")==null)this.put("cscmUpdateTimeNot", "");this.put("cscmUpdateTime", null);return this;};
 		/** not .... */
 		public M cscmUpdateTimeNot(){this.put("cscmUpdateTimeNot", "not");return this;};
 		/** and cscm_update_time >= ? */
 		public M cscmUpdateTimeStart(Object start){this.put("cscmUpdateTimeStart", start);return this;};			
 		/** and cscm_update_time <= ? */
 		public M cscmUpdateTimeEnd(Object end){this.put("cscmUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cscmAddTime(Object cscmAddTime){this.put("cscmAddTime", cscmAddTime);return this;};
	 	/** and cscm_add_time is null */
 		public M cscmAddTimeNull(){if(this.get("cscmAddTimeNot")==null)this.put("cscmAddTimeNot", "");this.put("cscmAddTime", null);return this;};
 		/** not .... */
 		public M cscmAddTimeNot(){this.put("cscmAddTimeNot", "not");return this;};
 		/** and cscm_add_time >= ? */
 		public M cscmAddTimeStart(Object start){this.put("cscmAddTimeStart", start);return this;};			
 		/** and cscm_add_time <= ? */
 		public M cscmAddTimeEnd(Object end){this.put("cscmAddTimeEnd", end);return this;};
		/** 接收状态 [非空]   0:未读 1:已读     **/
		public M cscmState(Object cscmState){this.put("cscmState", cscmState);return this;};
	 	/** and cscm_state is null */
 		public M cscmStateNull(){if(this.get("cscmStateNot")==null)this.put("cscmStateNot", "");this.put("cscmState", null);return this;};
 		/** not .... */
 		public M cscmStateNot(){this.put("cscmStateNot", "not");return this;};
		/** 状态 [非空]   1:默认 0:无效     **/
		public M cscmStatus(Object cscmStatus){this.put("cscmStatus", cscmStatus);return this;};
	 	/** and cscm_status is null */
 		public M cscmStatusNull(){if(this.get("cscmStatusNot")==null)this.put("cscmStatusNot", "");this.put("cscmStatus", null);return this;};
 		/** not .... */
 		public M cscmStatusNot(){this.put("cscmStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有客服消息 **/
		public @api List<CsChatMessage> list(Integer size){
			return getCsChatMessageList(this,size);
		}
		/** 获取客服消息分页 **/
		public @api Page<CsChatMessage> page(int page,int size){
			return getCsChatMessagePage(page, size , this);
		}
		/** 根据查询条件取客服消息 **/
		public @api CsChatMessage get(){
			return getCsChatMessage(this);
		}
		/** 获取客服消息数 **/
		public @api Long count(){
			return getCsChatMessageCount(this);
		}
		/** 获取客服消息表达式 **/
		public @api <T> T eval(String strEval){
			return getCsChatMessageEval(strEval,this);
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
			updateCsChatMessage(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscmId="cscmId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cscmHost="cscmHost";
		/** 消息内容 [非空]       **/
		public final static @type(String.class) @like String cscmContent="cscmContent";
		/** 内容类型 [非空]   text:文本 image:图片 news:图文 html:特殊     **/
		public final static @type(Short.class)  String cscmType="cscmType";
		/** 发送来源 [非空]       **/
		public final static @type(String.class) @like String cscmFrom="cscmFrom";
		/** 接收目标 [非空]       **/
		public final static @type(String.class) @like String cscmTarget="cscmTarget";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cscmUpdateTime="cscmUpdateTime";
	 	/** and cscm_update_time >= ? */
 		public final static @type(Date.class) String cscmUpdateTimeStart="cscmUpdateTimeStart";
 		/** and cscm_update_time <= ? */
 		public final static @type(Date.class) String cscmUpdateTimeEnd="cscmUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cscmAddTime="cscmAddTime";
	 	/** and cscm_add_time >= ? */
 		public final static @type(Date.class) String cscmAddTimeStart="cscmAddTimeStart";
 		/** and cscm_add_time <= ? */
 		public final static @type(Date.class) String cscmAddTimeEnd="cscmAddTimeEnd";
		/** 接收状态 [非空]   0:未读 1:已读     **/
		public final static @type(Short.class)  String cscmState="cscmState";
		/** 状态 [非空]   1:默认 0:无效     **/
		public final static @type(Short.class)  String cscmStatus="cscmStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscmId="cscm_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cscmHost="cscm_host";
		/** 消息内容 [非空]       **/
		public final static String cscmContent="cscm_content";
		/** 内容类型 [非空]   text:文本 image:图片 news:图文 html:特殊     **/
		public final static String cscmType="cscm_type";
		/** 发送来源 [非空]       **/
		public final static String cscmFrom="cscm_from";
		/** 接收目标 [非空]       **/
		public final static String cscmTarget="cscm_target";
		/** 修改时间 [非空]       **/
		public final static String cscmUpdateTime="cscm_update_time";
		/** 添加时间 [非空]       **/
		public final static String cscmAddTime="cscm_add_time";
		/** 接收状态 [非空]   0:未读 1:已读     **/
		public final static String cscmState="cscm_state";
		/** 状态 [非空]   1:默认 0:无效     **/
		public final static String cscmStatus="cscm_status";
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
				$.Set(name,CsChatMessage.getCsChatMessage(params));
			else
				$.Set(name,CsChatMessage.getCsChatMessageList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取客服消息数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsChatMessage) $.GetRequest("CsChatMessage$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsChatMessage.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsChatMessage.getCsChatMessage(params);
			else
				value = CsChatMessage.getCsChatMessageList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsChatMessage.Get($.add(CsChatMessage.F.cscmId,param));
		else if(!$.empty(param.toString()))
			value = CsChatMessage.get(Long.valueOf(param.toString()));
		$.SetRequest("CsChatMessage$"+param.hashCode(), value);
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
	public void mergeSet(CsChatMessage old){
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