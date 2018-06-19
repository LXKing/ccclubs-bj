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

@namespace("csm/callin")
public @caption("客户来电") @table("cs_call_in") class CsCallIn implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csci_id")   @primary  @note("  ") Long csciId;// 主键 非空     
	private @caption("城市") @column("csci_host")    @relate("$csciHost") @RelateClass(SrvHost.class)  @note("  ") Long csciHost;// 非空     
 	private SrvHost $csciHost;//关联对象[运营城市]
	private @caption("来电号码") @column("csci_number")    @note("  ") String csciNumber;// 非空     
	private @caption("来电会员") @column("csci_member")    @relate("$csciMember") @RelateClass(CsMember.class)  @note("  ") Long csciMember;//     
 	private CsMember $csciMember;//关联对象[会员帐号]
	private @caption("姓名") @column("csci_name")    @note("  ") String csciName;//     
	private @caption("接听人") @column("csci_answer")    @relate("$csciAnswer") @RelateClass(SrvUser.class)  @note("  ") Long csciAnswer;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csciAnswer;//关联对象[用户]
	private @caption("通话时长") @column("csci_how_long")    @note("  ") Integer csciHowLong;//(单位：毫秒)     
	private @caption("修改时间") @column("csci_update_time")    @note("  ") Date csciUpdateTime;// 非空     
	private @caption("来电时间") @column("csci_add_time")    @note("  ") Date csciAddTime;// 非空     
	private @caption("状态") @column("csci_status")    @note(" 1:已接听 0:未接听  ") Short csciStatus;// 非空 1:已接听 0:未接听     
	
	//默认构造函数
	public CsCallIn(){
	
	}
	
	//主键构造函数
	public CsCallIn(Long id){
		this.csciId = id;
	}
	
	/**所有字段构造函数 CsCallIn(csciHost,csciNumber,csciMember,csciName,csciAnswer,csciHowLong,csciUpdateTime,csciAddTime,csciStatus)
	 CsCallIn(
	 	$.getLong("csciHost")//城市 [非空]
	 	,$.getString("csciNumber")//来电号码 [非空]
	 	,$.getLong("csciMember")//来电会员
	 	,$.getString("csciName")//姓名
	 	,$.getLong("csciAnswer")//接听人
	 	,$.getInteger("csciHowLong")//通话时长
	 	,$.getDate("csciUpdateTime")//修改时间 [非空]
	 	,$.getDate("csciAddTime")//来电时间 [非空]
	 	,$.getShort("csciStatus")//状态 [非空]
	 )
	**/
	public CsCallIn(Long csciHost,String csciNumber,Long csciMember,String csciName,Long csciAnswer,Integer csciHowLong,Date csciUpdateTime,Date csciAddTime,Short csciStatus){
		this.csciHost=csciHost;
		this.csciNumber=csciNumber;
		this.csciMember=csciMember;
		this.csciName=csciName;
		this.csciAnswer=csciAnswer;
		this.csciHowLong=csciHowLong;
		this.csciUpdateTime=csciUpdateTime;
		this.csciAddTime=csciAddTime;
		this.csciStatus=csciStatus;
	}
	
	//设置非空字段
	public CsCallIn setNotNull(Long csciHost,String csciNumber,Date csciUpdateTime,Date csciAddTime,Short csciStatus){
		this.csciHost=csciHost;
		this.csciNumber=csciNumber;
		this.csciUpdateTime=csciUpdateTime;
		this.csciAddTime=csciAddTime;
		this.csciStatus=csciStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsCallIn csciId(Long csciId){
		this.csciId = csciId;
		this.setSeted(F.csciId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsCallIn csciHost(Long csciHost){
		this.csciHost = csciHost;
		this.setSeted(F.csciHost);
		return this;
	}
	/** 来电号码 [非空]       **/
	public CsCallIn csciNumber(String csciNumber){
		this.csciNumber = csciNumber;
		this.setSeted(F.csciNumber);
		return this;
	}
	/** 来电会员  [CsMember]      **/
	public CsCallIn csciMember(Long csciMember){
		this.csciMember = csciMember;
		this.setSeted(F.csciMember);
		return this;
	}
	/** 姓名        **/
	public CsCallIn csciName(String csciName){
		this.csciName = csciName;
		this.setSeted(F.csciName);
		return this;
	}
	/** 接听人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsCallIn csciAnswer(Long csciAnswer){
		this.csciAnswer = csciAnswer;
		this.setSeted(F.csciAnswer);
		return this;
	}
	/** 通话时长        **/
	public CsCallIn csciHowLong(Integer csciHowLong){
		this.csciHowLong = csciHowLong;
		this.setSeted(F.csciHowLong);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsCallIn csciUpdateTime(Date csciUpdateTime){
		this.csciUpdateTime = csciUpdateTime;
		this.setSeted(F.csciUpdateTime);
		return this;
	}
	/** 来电时间 [非空]       **/
	public CsCallIn csciAddTime(Date csciAddTime){
		this.csciAddTime = csciAddTime;
		this.setSeted(F.csciAddTime);
		return this;
	}
	/** 状态 [非空]   1:已接听 0:未接听     **/
	public CsCallIn csciStatus(Short csciStatus){
		this.csciStatus = csciStatus;
		this.setSeted(F.csciStatus);
		return this;
	}
	
	
	//克隆对象
	public CsCallIn clone(){
		CsCallIn clone = new CsCallIn();
		clone.csciHost=this.csciHost;
		clone.csciNumber=this.csciNumber;
		clone.csciUpdateTime=this.csciUpdateTime;
		clone.csciAddTime=this.csciAddTime;
		clone.csciStatus=this.csciStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取客户来电
	 * @param id
	 * @return
	 */
	public static @api CsCallIn get(Long id){		
		return getCsCallInById(id);
	}
	/**
	 * 获取所有客户来电
	 * @return
	 */
	public static @api List<CsCallIn> list(Map params,Integer size){
		return getCsCallInList(params,size);
	}
	/**
	 * 获取客户来电分页
	 * @return
	 */
	public static @api Page<CsCallIn> page(int page,int size,Map params){
		return getCsCallInPage(page, size , params);
	}
	/**
	 * 根据查询条件取客户来电
	 * @param params
	 * @return
	 */
	public static @api CsCallIn Get(Map params){
		return getCsCallIn(params);
	}
	/**
	 * 获取客户来电数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsCallInCount(params);
	}
	/**
	 * 获取客户来电数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsCallInEval(eval,params);
	}
	
	/**
	 * 根据ID取客户来电
	 * @param id
	 * @return
	 */
	public static @api CsCallIn getCsCallInById(Long id){		
		CsCallIn csCallIn = (CsCallIn) $.GetRequest("CsCallIn$"+id);
		if(csCallIn!=null)
			return csCallIn;
		com.ccclubs.service.admin.ICsCallInService csCallInService = $.GetSpringBean("csCallInService");		
		return csCallInService.getCsCallInById(id);
	}
	
	
	/**
	 * 根据ID取客户来电的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsCallIn.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsCallIn csCallIn = get(id);
		if(csCallIn!=null){
			String strValue = csCallIn.getCsciNumber$();
			if(!"CsciNumber".equals("CsciNumber"))
				strValue+="("+csCallIn.getCsciNumber$()+")";
			MemCache.setValue(CsCallIn.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsciNumber$();
		if(!"CsciNumber".equals("CsciNumber"))
			keyValue+="("+this.getCsciNumber$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有客户来电
	 * @return
	 */
	public static @api List<CsCallIn> getCsCallInList(Map params,Integer size){
		com.ccclubs.service.admin.ICsCallInService csCallInService = $.GetSpringBean("csCallInService");
		return csCallInService.getCsCallInList(params, size);
	}
	
	/**
	 * 获取客户来电分页
	 * @return
	 */
	public static @api Page<CsCallIn> getCsCallInPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsCallInService csCallInService = $.GetSpringBean("csCallInService");
		return csCallInService.getCsCallInPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取客户来电
	 * @param params
	 * @return
	 */
	public static @api CsCallIn getCsCallIn(Map params){
		com.ccclubs.service.admin.ICsCallInService csCallInService = $.GetSpringBean("csCallInService");
		return csCallInService.getCsCallIn(params);
	}
	
	/**
	 * 获取客户来电数
	 * @return
	 */
	public static @api Long getCsCallInCount(Map params){
		com.ccclubs.service.admin.ICsCallInService csCallInService = $.GetSpringBean("csCallInService");
		return csCallInService.getCsCallInCount(params);
	}
		
		
	/**
	 * 获取客户来电自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsCallInEval(String eval,Map params){
		com.ccclubs.service.admin.ICsCallInService csCallInService = $.GetSpringBean("csCallInService");
		return csCallInService.getCsCallInEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsCallIn(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsCallInService csCallInService = $.GetSpringBean("csCallInService");
		csCallInService.updateCsCallInByConfirm(set, where);
	}
	
	
	/**
	 * 保存客户来电对象
	 * @param params
	 * @return
	 */
	public CsCallIn save(){
		com.ccclubs.service.admin.ICsCallInService csCallInService = $.GetSpringBean("csCallInService");
		if(this.getCsciId()!=null)
			csCallInService.updateCsCallIn(this);
		else
			return csCallInService.saveCsCallIn(this);
		return this;
	}
	
	
	/**
	 * 更新客户来电对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsCallInService csCallInService = $.GetSpringBean("csCallInService");
		csCallInService.updateCsCallIn$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsCallInService csCallInService = $.GetSpringBean("csCallInService");
		if($.equals($.config("logic_delete"),"true"))
			csCallInService.removeCsCallInById(this.getCsciId());
		else
			csCallInService.deleteCsCallInById(this.getCsciId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsCallInService csCallInService = $.GetSpringBean("csCallInService");
		return csCallInService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsciId(){
		return this.csciId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsciId$(){
		String strValue="";
		 strValue=$.str(this.getCsciId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsciId(Long csciId){
		this.csciId = csciId;
		this.setSeted(F.csciId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsciHost(){
		return this.csciHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsciHost$(){
		String strValue="";
		if(this.getCsciHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsciHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsciHost(Long csciHost){
		this.csciHost = csciHost;
		this.setSeted(F.csciHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csciHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsciHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsciHost()!=null){
 			srvHost = SrvHost.get(this.getCsciHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsciHost(), srvHost);
	 	return srvHost;
	}
	/*******************************来电号码**********************************/	
	/**
	* 来电号码 [非空]      
	**/
	public String getCsciNumber(){
		return this.csciNumber;
	}
	/**
	* 获取来电号码格式化(toString)
	**/
	public String getCsciNumber$(){
		String strValue="";
		 strValue=$.str(this.getCsciNumber());
	 	 return strValue;
	}
	/**
	* 来电号码 [非空]      
	**/
	public void setCsciNumber(String csciNumber){
		this.csciNumber = csciNumber;
		this.setSeted(F.csciNumber);
	}
	/*******************************来电会员**********************************/	
	/**
	* 来电会员  [CsMember]     
	**/
	public Long getCsciMember(){
		return this.csciMember;
	}
	/**
	* 获取来电会员格式化(toString)
	**/
	public String getCsciMember$(){
		String strValue="";
		if(this.getCsciMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsciMember()));
		}			
	 	 return strValue;
	}
	/**
	* 来电会员  [CsMember]     
	**/
	public void setCsciMember(Long csciMember){
		this.csciMember = csciMember;
		this.setSeted(F.csciMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csciMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsciMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsciMember()!=null){
 			csMember = CsMember.get(this.getCsciMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsciMember(), csMember);
	 	return csMember;
	}
	/*******************************姓名**********************************/	
	/**
	* 姓名       
	**/
	public String getCsciName(){
		return this.csciName;
	}
	/**
	* 获取姓名格式化(toString)
	**/
	public String getCsciName$(){
		String strValue="";
		 strValue=$.str(this.getCsciName());
	 	 return strValue;
	}
	/**
	* 姓名       
	**/
	public void setCsciName(String csciName){
		this.csciName = csciName;
		this.setSeted(F.csciName);
	}
	/*******************************接听人**********************************/	
	/**
	* 接听人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsciAnswer(){
		return this.csciAnswer;
	}
	/**
	* 获取接听人格式化(toString)
	**/
	public String getCsciAnswer$(){
		String strValue="";
		if(this.getCsciAnswer()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsciAnswer()));
		}			
	 	 return strValue;
	}
	/**
	* 接听人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsciAnswer(Long csciAnswer){
		this.csciAnswer = csciAnswer;
		this.setSeted(F.csciAnswer);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csciAnswer(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsciAnswer());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsciAnswer()!=null){
 			srvUser = SrvUser.get(this.getCsciAnswer());
 		}
 		$.SetRequest("SrvUser$"+this.getCsciAnswer(), srvUser);
	 	return srvUser;
	}
	/*******************************通话时长**********************************/	
	/**
	* 通话时长       
	**/
	public Integer getCsciHowLong(){
		return this.csciHowLong;
	}
	/**
	* 获取通话时长格式化(toString)
	**/
	public String getCsciHowLong$(){
		String strValue="";
		 strValue=$.str(this.getCsciHowLong());
	 	 return strValue;
	}
	/**
	* 通话时长       
	**/
	public void setCsciHowLong(Integer csciHowLong){
		this.csciHowLong = csciHowLong;
		this.setSeted(F.csciHowLong);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsciUpdateTime(){
		return this.csciUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsciUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsciUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsciUpdateTime(Date csciUpdateTime){
		this.csciUpdateTime = csciUpdateTime;
		this.setSeted(F.csciUpdateTime);
	}
	/*******************************来电时间**********************************/	
	/**
	* 来电时间 [非空]      
	**/
	public Date getCsciAddTime(){
		return this.csciAddTime;
	}
	/**
	* 获取来电时间格式化(toString)
	**/
	public String getCsciAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsciAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 来电时间 [非空]      
	**/
	public void setCsciAddTime(Date csciAddTime){
		this.csciAddTime = csciAddTime;
		this.setSeted(F.csciAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:已接听 0:未接听    
	**/
	public Short getCsciStatus(){
		return this.csciStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsciStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsciStatus()),"1"))
			strValue=$.str("已接听");		 
		 if($.equals($.str(this.getCsciStatus()),"0"))
			strValue=$.str("未接听");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:已接听 0:未接听    
	**/
	public void setCsciStatus(Short csciStatus){
		this.csciStatus = csciStatus;
		this.setSeted(F.csciStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsCallIn.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCallIn.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCallIn.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsCallIn.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsCallIn.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCallIn.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCallIn.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsCallIn.this);
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
		public M csciId(Object csciId){this.put("csciId", csciId);return this;};
	 	/** and csci_id is null */
 		public M csciIdNull(){if(this.get("csciIdNot")==null)this.put("csciIdNot", "");this.put("csciId", null);return this;};
 		/** not .... */
 		public M csciIdNot(){this.put("csciIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csciHost(Object csciHost){this.put("csciHost", csciHost);return this;};
	 	/** and csci_host is null */
 		public M csciHostNull(){if(this.get("csciHostNot")==null)this.put("csciHostNot", "");this.put("csciHost", null);return this;};
 		/** not .... */
 		public M csciHostNot(){this.put("csciHostNot", "not");return this;};
		/** 来电号码 [非空]       **/
		public M csciNumber(Object csciNumber){this.put("csciNumber", csciNumber);return this;};
	 	/** and csci_number is null */
 		public M csciNumberNull(){if(this.get("csciNumberNot")==null)this.put("csciNumberNot", "");this.put("csciNumber", null);return this;};
 		/** not .... */
 		public M csciNumberNot(){this.put("csciNumberNot", "not");return this;};
		/** 来电会员  [CsMember]      **/
		public M csciMember(Object csciMember){this.put("csciMember", csciMember);return this;};
	 	/** and csci_member is null */
 		public M csciMemberNull(){if(this.get("csciMemberNot")==null)this.put("csciMemberNot", "");this.put("csciMember", null);return this;};
 		/** not .... */
 		public M csciMemberNot(){this.put("csciMemberNot", "not");return this;};
		public M csciMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csciMember$on", value);
			return this;
		};	
		/** 姓名        **/
		public M csciName(Object csciName){this.put("csciName", csciName);return this;};
	 	/** and csci_name is null */
 		public M csciNameNull(){if(this.get("csciNameNot")==null)this.put("csciNameNot", "");this.put("csciName", null);return this;};
 		/** not .... */
 		public M csciNameNot(){this.put("csciNameNot", "not");return this;};
		/** 接听人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csciAnswer(Object csciAnswer){this.put("csciAnswer", csciAnswer);return this;};
	 	/** and csci_answer is null */
 		public M csciAnswerNull(){if(this.get("csciAnswerNot")==null)this.put("csciAnswerNot", "");this.put("csciAnswer", null);return this;};
 		/** not .... */
 		public M csciAnswerNot(){this.put("csciAnswerNot", "not");return this;};
		/** 通话时长        **/
		public M csciHowLong(Object csciHowLong){this.put("csciHowLong", csciHowLong);return this;};
	 	/** and csci_how_long is null */
 		public M csciHowLongNull(){if(this.get("csciHowLongNot")==null)this.put("csciHowLongNot", "");this.put("csciHowLong", null);return this;};
 		/** not .... */
 		public M csciHowLongNot(){this.put("csciHowLongNot", "not");return this;};
		/** and csci_how_long >= ? */
		public M csciHowLongMin(Object min){this.put("csciHowLongMin", min);return this;};
		/** and csci_how_long <= ? */
		public M csciHowLongMax(Object max){this.put("csciHowLongMax", max);return this;};
		/** 修改时间 [非空]       **/
		public M csciUpdateTime(Object csciUpdateTime){this.put("csciUpdateTime", csciUpdateTime);return this;};
	 	/** and csci_update_time is null */
 		public M csciUpdateTimeNull(){if(this.get("csciUpdateTimeNot")==null)this.put("csciUpdateTimeNot", "");this.put("csciUpdateTime", null);return this;};
 		/** not .... */
 		public M csciUpdateTimeNot(){this.put("csciUpdateTimeNot", "not");return this;};
 		/** and csci_update_time >= ? */
 		public M csciUpdateTimeStart(Object start){this.put("csciUpdateTimeStart", start);return this;};			
 		/** and csci_update_time <= ? */
 		public M csciUpdateTimeEnd(Object end){this.put("csciUpdateTimeEnd", end);return this;};
		/** 来电时间 [非空]       **/
		public M csciAddTime(Object csciAddTime){this.put("csciAddTime", csciAddTime);return this;};
	 	/** and csci_add_time is null */
 		public M csciAddTimeNull(){if(this.get("csciAddTimeNot")==null)this.put("csciAddTimeNot", "");this.put("csciAddTime", null);return this;};
 		/** not .... */
 		public M csciAddTimeNot(){this.put("csciAddTimeNot", "not");return this;};
 		/** and csci_add_time >= ? */
 		public M csciAddTimeStart(Object start){this.put("csciAddTimeStart", start);return this;};			
 		/** and csci_add_time <= ? */
 		public M csciAddTimeEnd(Object end){this.put("csciAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:已接听 0:未接听     **/
		public M csciStatus(Object csciStatus){this.put("csciStatus", csciStatus);return this;};
	 	/** and csci_status is null */
 		public M csciStatusNull(){if(this.get("csciStatusNot")==null)this.put("csciStatusNot", "");this.put("csciStatus", null);return this;};
 		/** not .... */
 		public M csciStatusNot(){this.put("csciStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有客户来电 **/
		public @api List<CsCallIn> list(Integer size){
			return getCsCallInList(this,size);
		}
		/** 获取客户来电分页 **/
		public @api Page<CsCallIn> page(int page,int size){
			return getCsCallInPage(page, size , this);
		}
		/** 根据查询条件取客户来电 **/
		public @api CsCallIn get(){
			return getCsCallIn(this);
		}
		/** 获取客户来电数 **/
		public @api Long count(){
			return getCsCallInCount(this);
		}
		/** 获取客户来电表达式 **/
		public @api <T> T eval(String strEval){
			return getCsCallInEval(strEval,this);
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
			updateCsCallIn(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csciId="csciId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csciHost="csciHost";
		/** 来电号码 [非空]       **/
		public final static @type(String.class) @like String csciNumber="csciNumber";
		/** 来电会员  [CsMember]      **/
		public final static @type(Long.class)  String csciMember="csciMember";
		/** 姓名        **/
		public final static @type(String.class) @like String csciName="csciName";
		/** 接听人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csciAnswer="csciAnswer";
		/** 通话时长        **/
		public final static @type(Integer.class)  String csciHowLong="csciHowLong";
		/** and csci_how_long >= ? */
		public final static @type(Integer.class) String csciHowLongMin="csciHowLongMin";
		/** and csci_how_long <= ? */
		public final static @type(Integer.class) String csciHowLongMax="csciHowLongMax";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csciUpdateTime="csciUpdateTime";
	 	/** and csci_update_time >= ? */
 		public final static @type(Date.class) String csciUpdateTimeStart="csciUpdateTimeStart";
 		/** and csci_update_time <= ? */
 		public final static @type(Date.class) String csciUpdateTimeEnd="csciUpdateTimeEnd";
		/** 来电时间 [非空]       **/
		public final static @type(Date.class)  String csciAddTime="csciAddTime";
	 	/** and csci_add_time >= ? */
 		public final static @type(Date.class) String csciAddTimeStart="csciAddTimeStart";
 		/** and csci_add_time <= ? */
 		public final static @type(Date.class) String csciAddTimeEnd="csciAddTimeEnd";
		/** 状态 [非空]   1:已接听 0:未接听     **/
		public final static @type(Short.class)  String csciStatus="csciStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csciId="csci_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csciHost="csci_host";
		/** 来电号码 [非空]       **/
		public final static String csciNumber="csci_number";
		/** 来电会员  [CsMember]      **/
		public final static String csciMember="csci_member";
		/** 姓名        **/
		public final static String csciName="csci_name";
		/** 接听人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csciAnswer="csci_answer";
		/** 通话时长        **/
		public final static String csciHowLong="csci_how_long";
		/** 修改时间 [非空]       **/
		public final static String csciUpdateTime="csci_update_time";
		/** 来电时间 [非空]       **/
		public final static String csciAddTime="csci_add_time";
		/** 状态 [非空]   1:已接听 0:未接听     **/
		public final static String csciStatus="csci_status";
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
				$.Set(name,CsCallIn.getCsCallIn(params));
			else
				$.Set(name,CsCallIn.getCsCallInList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取客户来电数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsCallIn) $.GetRequest("CsCallIn$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsCallIn.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsCallIn.getCsCallIn(params);
			else
				value = CsCallIn.getCsCallInList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsCallIn.Get($.add(CsCallIn.F.csciId,param));
		else if(!$.empty(param.toString()))
			value = CsCallIn.get(Long.valueOf(param.toString()));
		$.SetRequest("CsCallIn$"+param.hashCode(), value);
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
	public void mergeSet(CsCallIn old){
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