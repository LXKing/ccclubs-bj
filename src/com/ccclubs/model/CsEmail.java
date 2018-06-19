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

@namespace("systematic/email")
public @caption("邮件内容") @table("cs_email") class CsEmail implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cse_id")   @primary  @note("  ") Long cseId;// 主键 非空     
	private @caption("接收人") @column("cse_to")    @note("  ") String cseTo;// 非空     
	private @caption("邮件内容") @column("cse_content")    @note("  ") String cseContent;// 非空     
	private @caption("发送人") @column("cse_from")    @note("  ") String cseFrom;//     
	private @caption("发送时间") @column("cse_send_time")    @note("  ") Date cseSendTime;//     
	private @caption("添加时间") @column("cse_add_time")    @note("  ") Date cseAddTime;// 非空     
	private @caption("状态") @column("cse_status")    @note(" 0:待发送 1:发送成功 2:发送失败  ") Short cseStatus;// 非空 0:待发送 1:发送成功 2:发送失败     
	
	//默认构造函数
	public CsEmail(){
	
	}
	
	//主键构造函数
	public CsEmail(Long id){
		this.cseId = id;
	}
	
	/**所有字段构造函数 CsEmail(cseTo,cseContent,cseFrom,cseSendTime,cseAddTime,cseStatus)
	 CsEmail(
	 	$.getString("cseTo")//接收人 [非空]
	 	,$.getString("cseContent")//邮件内容 [非空]
	 	,$.getString("cseFrom")//发送人
	 	,$.getDate("cseSendTime")//发送时间
	 	,$.getDate("cseAddTime")//添加时间 [非空]
	 	,$.getShort("cseStatus")//状态 [非空]
	 )
	**/
	public CsEmail(String cseTo,String cseContent,String cseFrom,Date cseSendTime,Date cseAddTime,Short cseStatus){
		this.cseTo=cseTo;
		this.cseContent=cseContent;
		this.cseFrom=cseFrom;
		this.cseSendTime=cseSendTime;
		this.cseAddTime=cseAddTime;
		this.cseStatus=cseStatus;
	}
	
	//设置非空字段
	public CsEmail setNotNull(String cseTo,String cseContent,Date cseAddTime,Short cseStatus){
		this.cseTo=cseTo;
		this.cseContent=cseContent;
		this.cseAddTime=cseAddTime;
		this.cseStatus=cseStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsEmail cseId(Long cseId){
		this.cseId = cseId;
		this.setSeted(F.cseId);
		return this;
	}
	/** 接收人 [非空]       **/
	public CsEmail cseTo(String cseTo){
		this.cseTo = cseTo;
		this.setSeted(F.cseTo);
		return this;
	}
	/** 邮件内容 [非空]       **/
	public CsEmail cseContent(String cseContent){
		this.cseContent = cseContent;
		this.setSeted(F.cseContent);
		return this;
	}
	/** 发送人        **/
	public CsEmail cseFrom(String cseFrom){
		this.cseFrom = cseFrom;
		this.setSeted(F.cseFrom);
		return this;
	}
	/** 发送时间        **/
	public CsEmail cseSendTime(Date cseSendTime){
		this.cseSendTime = cseSendTime;
		this.setSeted(F.cseSendTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsEmail cseAddTime(Date cseAddTime){
		this.cseAddTime = cseAddTime;
		this.setSeted(F.cseAddTime);
		return this;
	}
	/** 状态 [非空]   0:待发送 1:发送成功 2:发送失败     **/
	public CsEmail cseStatus(Short cseStatus){
		this.cseStatus = cseStatus;
		this.setSeted(F.cseStatus);
		return this;
	}
	
	
	//克隆对象
	public CsEmail clone(){
		CsEmail clone = new CsEmail();
		clone.cseTo=this.cseTo;
		clone.cseContent=this.cseContent;
		clone.cseAddTime=this.cseAddTime;
		clone.cseStatus=this.cseStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取邮件内容
	 * @param id
	 * @return
	 */
	public static @api CsEmail get(Long id){		
		return getCsEmailById(id);
	}
	/**
	 * 获取所有邮件内容
	 * @return
	 */
	public static @api List<CsEmail> list(Map params,Integer size){
		return getCsEmailList(params,size);
	}
	/**
	 * 获取邮件内容分页
	 * @return
	 */
	public static @api Page<CsEmail> page(int page,int size,Map params){
		return getCsEmailPage(page, size , params);
	}
	/**
	 * 根据查询条件取邮件内容
	 * @param params
	 * @return
	 */
	public static @api CsEmail Get(Map params){
		return getCsEmail(params);
	}
	/**
	 * 获取邮件内容数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsEmailCount(params);
	}
	/**
	 * 获取邮件内容数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsEmailEval(eval,params);
	}
	
	/**
	 * 根据ID取邮件内容
	 * @param id
	 * @return
	 */
	public static @api CsEmail getCsEmailById(Long id){		
		CsEmail csEmail = (CsEmail) $.GetRequest("CsEmail$"+id);
		if(csEmail!=null)
			return csEmail;
		com.ccclubs.service.admin.ICsEmailService csEmailService = $.GetSpringBean("csEmailService");		
		return csEmailService.getCsEmailById(id);
	}
	
	
	/**
	 * 根据ID取邮件内容的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsEmail.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsEmail csEmail = get(id);
		if(csEmail!=null){
			String strValue = csEmail.getCseId$();
			if(!"CseId".equals("CseId"))
				strValue+="("+csEmail.getCseId$()+")";
			if(!"CseId".equals("CseTo"))
				strValue+="("+csEmail.getCseTo$()+")";
			if(!"CseId".equals("CseContent"))
				strValue+="("+csEmail.getCseContent$()+")";
			MemCache.setValue(CsEmail.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCseId$();
		if(!"CseId".equals("CseId"))
			keyValue+="("+this.getCseId$()+")";
		if(!"CseId".equals("CseTo"))
			keyValue+="("+this.getCseTo$()+")";
		if(!"CseId".equals("CseContent"))
			keyValue+="("+this.getCseContent$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有邮件内容
	 * @return
	 */
	public static @api List<CsEmail> getCsEmailList(Map params,Integer size){
		com.ccclubs.service.admin.ICsEmailService csEmailService = $.GetSpringBean("csEmailService");
		return csEmailService.getCsEmailList(params, size);
	}
	
	/**
	 * 获取邮件内容分页
	 * @return
	 */
	public static @api Page<CsEmail> getCsEmailPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsEmailService csEmailService = $.GetSpringBean("csEmailService");
		return csEmailService.getCsEmailPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取邮件内容
	 * @param params
	 * @return
	 */
	public static @api CsEmail getCsEmail(Map params){
		com.ccclubs.service.admin.ICsEmailService csEmailService = $.GetSpringBean("csEmailService");
		return csEmailService.getCsEmail(params);
	}
	
	/**
	 * 获取邮件内容数
	 * @return
	 */
	public static @api Long getCsEmailCount(Map params){
		com.ccclubs.service.admin.ICsEmailService csEmailService = $.GetSpringBean("csEmailService");
		return csEmailService.getCsEmailCount(params);
	}
		
		
	/**
	 * 获取邮件内容自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsEmailEval(String eval,Map params){
		com.ccclubs.service.admin.ICsEmailService csEmailService = $.GetSpringBean("csEmailService");
		return csEmailService.getCsEmailEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsEmail(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsEmailService csEmailService = $.GetSpringBean("csEmailService");
		csEmailService.updateCsEmailByConfirm(set, where);
	}
	
	
	/**
	 * 保存邮件内容对象
	 * @param params
	 * @return
	 */
	public CsEmail save(){
		com.ccclubs.service.admin.ICsEmailService csEmailService = $.GetSpringBean("csEmailService");
		if(this.getCseId()!=null)
			csEmailService.updateCsEmail(this);
		else
			return csEmailService.saveCsEmail(this);
		return this;
	}
	
	
	/**
	 * 更新邮件内容对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsEmailService csEmailService = $.GetSpringBean("csEmailService");
		csEmailService.updateCsEmail$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsEmailService csEmailService = $.GetSpringBean("csEmailService");
		if($.equals($.config("logic_delete"),"true"))
			csEmailService.removeCsEmailById(this.getCseId());
		else
			csEmailService.deleteCsEmailById(this.getCseId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsEmailService csEmailService = $.GetSpringBean("csEmailService");
		return csEmailService.executeTransaction(function);
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
	/*******************************接收人**********************************/	
	/**
	* 接收人 [非空]      
	**/
	public String getCseTo(){
		return this.cseTo;
	}
	/**
	* 获取接收人格式化(toString)
	**/
	public String getCseTo$(){
		String strValue="";
		 strValue=$.str(this.getCseTo());
	 	 return strValue;
	}
	/**
	* 接收人 [非空]      
	**/
	public void setCseTo(String cseTo){
		this.cseTo = cseTo;
		this.setSeted(F.cseTo);
	}
	/*******************************邮件内容**********************************/	
	/**
	* 邮件内容 [非空]      
	**/
	public String getCseContent(){
		return this.cseContent;
	}
	/**
	* 获取邮件内容格式化(toString)
	**/
	public String getCseContent$(){
		String strValue="";
		 strValue=$.str(this.getCseContent());
	 	 return strValue;
	}
	/**
	* 邮件内容 [非空]      
	**/
	public void setCseContent(String cseContent){
		this.cseContent = cseContent;
		this.setSeted(F.cseContent);
	}
	/*******************************发送人**********************************/	
	/**
	* 发送人       
	**/
	public String getCseFrom(){
		return this.cseFrom;
	}
	/**
	* 获取发送人格式化(toString)
	**/
	public String getCseFrom$(){
		String strValue="";
		 strValue=$.str(this.getCseFrom());
	 	 return strValue;
	}
	/**
	* 发送人       
	**/
	public void setCseFrom(String cseFrom){
		this.cseFrom = cseFrom;
		this.setSeted(F.cseFrom);
	}
	/*******************************发送时间**********************************/	
	/**
	* 发送时间       
	**/
	public Date getCseSendTime(){
		return this.cseSendTime;
	}
	/**
	* 获取发送时间格式化(toString)
	**/
	public String getCseSendTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCseSendTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 发送时间       
	**/
	public void setCseSendTime(Date cseSendTime){
		this.cseSendTime = cseSendTime;
		this.setSeted(F.cseSendTime);
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
	* 状态 [非空]   0:待发送 1:发送成功 2:发送失败    
	**/
	public Short getCseStatus(){
		return this.cseStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCseStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCseStatus()),"0"))
			strValue=$.str("待发送");		 
		 if($.equals($.str(this.getCseStatus()),"1"))
			strValue=$.str("发送成功");		 
		 if($.equals($.str(this.getCseStatus()),"2"))
			strValue=$.str("发送失败");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:待发送 1:发送成功 2:发送失败    
	**/
	public void setCseStatus(Short cseStatus){
		this.cseStatus = cseStatus;
		this.setSeted(F.cseStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsEmail.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsEmail.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsEmail.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsEmail.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsEmail.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsEmail.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsEmail.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsEmail.this);
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
		/** 接收人 [非空]       **/
		public M cseTo(Object cseTo){this.put("cseTo", cseTo);return this;};
	 	/** and cse_to is null */
 		public M cseToNull(){if(this.get("cseToNot")==null)this.put("cseToNot", "");this.put("cseTo", null);return this;};
 		/** not .... */
 		public M cseToNot(){this.put("cseToNot", "not");return this;};
		/** 邮件内容 [非空]       **/
		public M cseContent(Object cseContent){this.put("cseContent", cseContent);return this;};
	 	/** and cse_content is null */
 		public M cseContentNull(){if(this.get("cseContentNot")==null)this.put("cseContentNot", "");this.put("cseContent", null);return this;};
 		/** not .... */
 		public M cseContentNot(){this.put("cseContentNot", "not");return this;};
		/** 发送人        **/
		public M cseFrom(Object cseFrom){this.put("cseFrom", cseFrom);return this;};
	 	/** and cse_from is null */
 		public M cseFromNull(){if(this.get("cseFromNot")==null)this.put("cseFromNot", "");this.put("cseFrom", null);return this;};
 		/** not .... */
 		public M cseFromNot(){this.put("cseFromNot", "not");return this;};
		/** 发送时间        **/
		public M cseSendTime(Object cseSendTime){this.put("cseSendTime", cseSendTime);return this;};
	 	/** and cse_send_time is null */
 		public M cseSendTimeNull(){if(this.get("cseSendTimeNot")==null)this.put("cseSendTimeNot", "");this.put("cseSendTime", null);return this;};
 		/** not .... */
 		public M cseSendTimeNot(){this.put("cseSendTimeNot", "not");return this;};
 		/** and cse_send_time >= ? */
 		public M cseSendTimeStart(Object start){this.put("cseSendTimeStart", start);return this;};			
 		/** and cse_send_time <= ? */
 		public M cseSendTimeEnd(Object end){this.put("cseSendTimeEnd", end);return this;};
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
		/** 状态 [非空]   0:待发送 1:发送成功 2:发送失败     **/
		public M cseStatus(Object cseStatus){this.put("cseStatus", cseStatus);return this;};
	 	/** and cse_status is null */
 		public M cseStatusNull(){if(this.get("cseStatusNot")==null)this.put("cseStatusNot", "");this.put("cseStatus", null);return this;};
 		/** not .... */
 		public M cseStatusNot(){this.put("cseStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有邮件内容 **/
		public @api List<CsEmail> list(Integer size){
			return getCsEmailList(this,size);
		}
		/** 获取邮件内容分页 **/
		public @api Page<CsEmail> page(int page,int size){
			return getCsEmailPage(page, size , this);
		}
		/** 根据查询条件取邮件内容 **/
		public @api CsEmail get(){
			return getCsEmail(this);
		}
		/** 获取邮件内容数 **/
		public @api Long count(){
			return getCsEmailCount(this);
		}
		/** 获取邮件内容表达式 **/
		public @api <T> T eval(String strEval){
			return getCsEmailEval(strEval,this);
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
			updateCsEmail(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cseId="cseId";
		/** 接收人 [非空]       **/
		public final static @type(String.class) @like String cseTo="cseTo";
		/** 邮件内容 [非空]       **/
		public final static @type(String.class) @like String cseContent="cseContent";
		/** 发送人        **/
		public final static @type(String.class) @like String cseFrom="cseFrom";
		/** 发送时间        **/
		public final static @type(Date.class)  String cseSendTime="cseSendTime";
	 	/** and cse_send_time >= ? */
 		public final static @type(Date.class) String cseSendTimeStart="cseSendTimeStart";
 		/** and cse_send_time <= ? */
 		public final static @type(Date.class) String cseSendTimeEnd="cseSendTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cseAddTime="cseAddTime";
	 	/** and cse_add_time >= ? */
 		public final static @type(Date.class) String cseAddTimeStart="cseAddTimeStart";
 		/** and cse_add_time <= ? */
 		public final static @type(Date.class) String cseAddTimeEnd="cseAddTimeEnd";
		/** 状态 [非空]   0:待发送 1:发送成功 2:发送失败     **/
		public final static @type(Short.class)  String cseStatus="cseStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cseId="cse_id";
		/** 接收人 [非空]       **/
		public final static String cseTo="cse_to";
		/** 邮件内容 [非空]       **/
		public final static String cseContent="cse_content";
		/** 发送人        **/
		public final static String cseFrom="cse_from";
		/** 发送时间        **/
		public final static String cseSendTime="cse_send_time";
		/** 添加时间 [非空]       **/
		public final static String cseAddTime="cse_add_time";
		/** 状态 [非空]   0:待发送 1:发送成功 2:发送失败     **/
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
				$.Set(name,CsEmail.getCsEmail(params));
			else
				$.Set(name,CsEmail.getCsEmailList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取邮件内容数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsEmail) $.GetRequest("CsEmail$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsEmail.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsEmail.getCsEmail(params);
			else
				value = CsEmail.getCsEmailList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsEmail.Get($.add(CsEmail.F.cseId,param));
		else if(!$.empty(param.toString()))
			value = CsEmail.get(Long.valueOf(param.toString()));
		$.SetRequest("CsEmail$"+param.hashCode(), value);
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
	public void mergeSet(CsEmail old){
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