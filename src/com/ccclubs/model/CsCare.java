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

@namespace("csm/care")
public @caption("客户关怀") @table("cs_care") class CsCare implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csc_id")   @primary  @note("  ") Long cscId;// 主键 非空     
	private @caption("城市") @column("csc_host")    @relate("$cscHost") @RelateClass(SrvHost.class)  @note("  ") Long cscHost;// 非空     
 	private SrvHost $cscHost;//关联对象[运营城市]
	private @caption("关怀会员") @column("csc_member")    @relate("$cscMember") @RelateClass(CsMember.class)  @note("  ") Long cscMember;// 非空     
 	private CsMember $cscMember;//关联对象[会员帐号]
	private @caption("执行人") @column("csc_user")    @relate("$cscUser") @RelateClass(SrvUser.class)  @note("  ") Long cscUser;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cscUser;//关联对象[用户]
	private @caption("关怀类型") @column("csc_sort")    @relate("$cscSort") @RelateClass(SrvProperty.class)  @note(" care_sort:客户关怀类型  ") Long cscSort;// 非空 care_sort:客户关怀类型     
 	private SrvProperty $cscSort;//关联对象[系统属性]
	private @caption("关怀方式") @column("csc_type")    @note(" 0:打电话 1:发短信 2:上门 3:电子邮件 4:信函 5:送礼品 6:其它  ") Short cscType;// 非空 0:打电话 1:发短信 2:上门 3:电子邮件 4:信函 5:送礼品 6:其它     
	private @caption("关怀主题") @column("csc_title")    @note("  ") String cscTitle;// 非空     
	private @caption("关怀描述") @column("csc_desc")    @note("  ") String cscDesc;//     
	private @caption("修改时间") @column("csc_update_time")    @note("  ") Date cscUpdateTime;// 非空     
	private @caption("添加时间") @column("csc_add_time")    @note("  ") Date cscAddTime;// 非空     
	private @caption("提醒时间") @column("csc_alarm_time")    @note("  ") Date cscAlarmTime;//     
	private @caption("状态") @column("csc_status")    @note(" 0:待跟踪 1:已完成  ") Short cscStatus;// 非空 0:待跟踪 1:已完成     
	
	//默认构造函数
	public CsCare(){
	
	}
	
	//主键构造函数
	public CsCare(Long id){
		this.cscId = id;
	}
	
	/**所有字段构造函数 CsCare(cscHost,cscMember,cscUser,cscSort,cscType,cscTitle,cscDesc,cscUpdateTime,cscAddTime,cscAlarmTime,cscStatus)
	 CsCare(
	 	$.getLong("cscHost")//城市 [非空]
	 	,$.getLong("cscMember")//关怀会员 [非空]
	 	,$.getLong("cscUser")//执行人 [非空]
	 	,$.getLong("cscSort")//关怀类型 [非空]
	 	,$.getShort("cscType")//关怀方式 [非空]
	 	,$.getString("cscTitle")//关怀主题 [非空]
	 	,$.getString("cscDesc")//关怀描述
	 	,$.getDate("cscUpdateTime")//修改时间 [非空]
	 	,$.getDate("cscAddTime")//添加时间 [非空]
	 	,$.getDate("cscAlarmTime")//提醒时间
	 	,$.getShort("cscStatus")//状态 [非空]
	 )
	**/
	public CsCare(Long cscHost,Long cscMember,Long cscUser,Long cscSort,Short cscType,String cscTitle,String cscDesc,Date cscUpdateTime,Date cscAddTime,Date cscAlarmTime,Short cscStatus){
		this.cscHost=cscHost;
		this.cscMember=cscMember;
		this.cscUser=cscUser;
		this.cscSort=cscSort;
		this.cscType=cscType;
		this.cscTitle=cscTitle;
		this.cscDesc=cscDesc;
		this.cscUpdateTime=cscUpdateTime;
		this.cscAddTime=cscAddTime;
		this.cscAlarmTime=cscAlarmTime;
		this.cscStatus=cscStatus;
	}
	
	//设置非空字段
	public CsCare setNotNull(Long cscHost,Long cscMember,Long cscUser,Long cscSort,Short cscType,String cscTitle,Date cscUpdateTime,Date cscAddTime,Short cscStatus){
		this.cscHost=cscHost;
		this.cscMember=cscMember;
		this.cscUser=cscUser;
		this.cscSort=cscSort;
		this.cscType=cscType;
		this.cscTitle=cscTitle;
		this.cscUpdateTime=cscUpdateTime;
		this.cscAddTime=cscAddTime;
		this.cscStatus=cscStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsCare cscId(Long cscId){
		this.cscId = cscId;
		this.setSeted(F.cscId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsCare cscHost(Long cscHost){
		this.cscHost = cscHost;
		this.setSeted(F.cscHost);
		return this;
	}
	/** 关怀会员 [非空] [CsMember]      **/
	public CsCare cscMember(Long cscMember){
		this.cscMember = cscMember;
		this.setSeted(F.cscMember);
		return this;
	}
	/** 执行人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsCare cscUser(Long cscUser){
		this.cscUser = cscUser;
		this.setSeted(F.cscUser);
		return this;
	}
	/** 关怀类型 [非空] [SrvProperty]  care_sort:客户关怀类型     **/
	public CsCare cscSort(Long cscSort){
		this.cscSort = cscSort;
		this.setSeted(F.cscSort);
		return this;
	}
	/** 关怀方式 [非空]   0:打电话 1:发短信 2:上门 3:电子邮件 4:信函 5:送礼品 6:其它     **/
	public CsCare cscType(Short cscType){
		this.cscType = cscType;
		this.setSeted(F.cscType);
		return this;
	}
	/** 关怀主题 [非空]       **/
	public CsCare cscTitle(String cscTitle){
		this.cscTitle = cscTitle;
		this.setSeted(F.cscTitle);
		return this;
	}
	/** 关怀描述        **/
	public CsCare cscDesc(String cscDesc){
		this.cscDesc = cscDesc;
		this.setSeted(F.cscDesc);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsCare cscUpdateTime(Date cscUpdateTime){
		this.cscUpdateTime = cscUpdateTime;
		this.setSeted(F.cscUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsCare cscAddTime(Date cscAddTime){
		this.cscAddTime = cscAddTime;
		this.setSeted(F.cscAddTime);
		return this;
	}
	/** 提醒时间        **/
	public CsCare cscAlarmTime(Date cscAlarmTime){
		this.cscAlarmTime = cscAlarmTime;
		this.setSeted(F.cscAlarmTime);
		return this;
	}
	/** 状态 [非空]   0:待跟踪 1:已完成     **/
	public CsCare cscStatus(Short cscStatus){
		this.cscStatus = cscStatus;
		this.setSeted(F.cscStatus);
		return this;
	}
	
	
	//克隆对象
	public CsCare clone(){
		CsCare clone = new CsCare();
		clone.cscHost=this.cscHost;
		clone.cscMember=this.cscMember;
		clone.cscUser=this.cscUser;
		clone.cscSort=this.cscSort;
		clone.cscType=this.cscType;
		clone.cscTitle=this.cscTitle;
		clone.cscUpdateTime=this.cscUpdateTime;
		clone.cscAddTime=this.cscAddTime;
		clone.cscStatus=this.cscStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取客户关怀
	 * @param id
	 * @return
	 */
	public static @api CsCare get(Long id){		
		return getCsCareById(id);
	}
	/**
	 * 获取所有客户关怀
	 * @return
	 */
	public static @api List<CsCare> list(Map params,Integer size){
		return getCsCareList(params,size);
	}
	/**
	 * 获取客户关怀分页
	 * @return
	 */
	public static @api Page<CsCare> page(int page,int size,Map params){
		return getCsCarePage(page, size , params);
	}
	/**
	 * 根据查询条件取客户关怀
	 * @param params
	 * @return
	 */
	public static @api CsCare Get(Map params){
		return getCsCare(params);
	}
	/**
	 * 获取客户关怀数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsCareCount(params);
	}
	/**
	 * 获取客户关怀数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsCareEval(eval,params);
	}
	
	/**
	 * 根据ID取客户关怀
	 * @param id
	 * @return
	 */
	public static @api CsCare getCsCareById(Long id){		
		CsCare csCare = (CsCare) $.GetRequest("CsCare$"+id);
		if(csCare!=null)
			return csCare;
		com.ccclubs.service.admin.ICsCareService csCareService = $.GetSpringBean("csCareService");		
		return csCareService.getCsCareById(id);
	}
	
	
	/**
	 * 根据ID取客户关怀的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsCare.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsCare csCare = get(id);
		if(csCare!=null){
			String strValue = csCare.getCscTitle$();
			if(!"CscTitle".equals("CscTitle"))
				strValue+="("+csCare.getCscTitle$()+")";
			MemCache.setValue(CsCare.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCscTitle$();
		if(!"CscTitle".equals("CscTitle"))
			keyValue+="("+this.getCscTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有客户关怀
	 * @return
	 */
	public static @api List<CsCare> getCsCareList(Map params,Integer size){
		com.ccclubs.service.admin.ICsCareService csCareService = $.GetSpringBean("csCareService");
		return csCareService.getCsCareList(params, size);
	}
	
	/**
	 * 获取客户关怀分页
	 * @return
	 */
	public static @api Page<CsCare> getCsCarePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsCareService csCareService = $.GetSpringBean("csCareService");
		return csCareService.getCsCarePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取客户关怀
	 * @param params
	 * @return
	 */
	public static @api CsCare getCsCare(Map params){
		com.ccclubs.service.admin.ICsCareService csCareService = $.GetSpringBean("csCareService");
		return csCareService.getCsCare(params);
	}
	
	/**
	 * 获取客户关怀数
	 * @return
	 */
	public static @api Long getCsCareCount(Map params){
		com.ccclubs.service.admin.ICsCareService csCareService = $.GetSpringBean("csCareService");
		return csCareService.getCsCareCount(params);
	}
		
		
	/**
	 * 获取客户关怀自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsCareEval(String eval,Map params){
		com.ccclubs.service.admin.ICsCareService csCareService = $.GetSpringBean("csCareService");
		return csCareService.getCsCareEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsCare(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsCareService csCareService = $.GetSpringBean("csCareService");
		csCareService.updateCsCareByConfirm(set, where);
	}
	
	
	/**
	 * 保存客户关怀对象
	 * @param params
	 * @return
	 */
	public CsCare save(){
		com.ccclubs.service.admin.ICsCareService csCareService = $.GetSpringBean("csCareService");
		if(this.getCscId()!=null)
			csCareService.updateCsCare(this);
		else
			return csCareService.saveCsCare(this);
		return this;
	}
	
	
	/**
	 * 更新客户关怀对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsCareService csCareService = $.GetSpringBean("csCareService");
		csCareService.updateCsCare$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsCareService csCareService = $.GetSpringBean("csCareService");
		if($.equals($.config("logic_delete"),"true"))
			csCareService.removeCsCareById(this.getCscId());
		else
			csCareService.deleteCsCareById(this.getCscId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsCareService csCareService = $.GetSpringBean("csCareService");
		return csCareService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCscId(){
		return this.cscId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCscId$(){
		String strValue="";
		 strValue=$.str(this.getCscId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCscId(Long cscId){
		this.cscId = cscId;
		this.setSeted(F.cscId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCscHost(){
		return this.cscHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCscHost$(){
		String strValue="";
		if(this.getCscHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCscHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCscHost(Long cscHost){
		this.cscHost = cscHost;
		this.setSeted(F.cscHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cscHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCscHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCscHost()!=null){
 			srvHost = SrvHost.get(this.getCscHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCscHost(), srvHost);
	 	return srvHost;
	}
	/*******************************关怀会员**********************************/	
	/**
	* 关怀会员 [非空] [CsMember]     
	**/
	public Long getCscMember(){
		return this.cscMember;
	}
	/**
	* 获取关怀会员格式化(toString)
	**/
	public String getCscMember$(){
		String strValue="";
		if(this.getCscMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCscMember()));
		}			
	 	 return strValue;
	}
	/**
	* 关怀会员 [非空] [CsMember]     
	**/
	public void setCscMember(Long cscMember){
		this.cscMember = cscMember;
		this.setSeted(F.cscMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$cscMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCscMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCscMember()!=null){
 			csMember = CsMember.get(this.getCscMember());
 		}
 		$.SetRequest("CsMember$"+this.getCscMember(), csMember);
	 	return csMember;
	}
	/*******************************执行人**********************************/	
	/**
	* 执行人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCscUser(){
		return this.cscUser;
	}
	/**
	* 获取执行人格式化(toString)
	**/
	public String getCscUser$(){
		String strValue="";
		if(this.getCscUser()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCscUser()));
		}			
	 	 return strValue;
	}
	/**
	* 执行人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCscUser(Long cscUser){
		this.cscUser = cscUser;
		this.setSeted(F.cscUser);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cscUser(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCscUser());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCscUser()!=null){
 			srvUser = SrvUser.get(this.getCscUser());
 		}
 		$.SetRequest("SrvUser$"+this.getCscUser(), srvUser);
	 	return srvUser;
	}
	/*******************************关怀类型**********************************/	
	/**
	* 关怀类型 [非空] [SrvProperty]  care_sort:客户关怀类型    
	**/
	public Long getCscSort(){
		return this.cscSort;
	}
	/**
	* 获取关怀类型格式化(toString)
	**/
	public String getCscSort$(){
		String strValue="";
		if(this.getCscSort()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCscSort()));
		}			
	 	 return strValue;
	}
	/**
	* 关怀类型 [非空] [SrvProperty]  care_sort:客户关怀类型    
	**/
	public void setCscSort(Long cscSort){
		this.cscSort = cscSort;
		this.setSeted(F.cscSort);
	}
	/**
	* 获取关联对象[系统属性]
	**/	 			
 	public SrvProperty get$cscSort(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCscSort());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCscSort()!=null){
 			srvProperty = SrvProperty.get(this.getCscSort());
 		}
 		$.SetRequest("SrvProperty$"+this.getCscSort(), srvProperty);
	 	return srvProperty;
	}
	/*******************************关怀方式**********************************/	
	/**
	* 关怀方式 [非空]   0:打电话 1:发短信 2:上门 3:电子邮件 4:信函 5:送礼品 6:其它    
	**/
	public Short getCscType(){
		return this.cscType;
	}
	/**
	* 获取关怀方式格式化(toString)
	**/
	public String getCscType$(){
		String strValue="";
		 if($.equals($.str(this.getCscType()),"0"))
			strValue=$.str("打电话");		 
		 if($.equals($.str(this.getCscType()),"1"))
			strValue=$.str("发短信");		 
		 if($.equals($.str(this.getCscType()),"2"))
			strValue=$.str("上门");		 
		 if($.equals($.str(this.getCscType()),"3"))
			strValue=$.str("电子邮件");		 
		 if($.equals($.str(this.getCscType()),"4"))
			strValue=$.str("信函");		 
		 if($.equals($.str(this.getCscType()),"5"))
			strValue=$.str("送礼品");		 
		 if($.equals($.str(this.getCscType()),"6"))
			strValue=$.str("其它");		 
	 	 return strValue;
	}
	/**
	* 关怀方式 [非空]   0:打电话 1:发短信 2:上门 3:电子邮件 4:信函 5:送礼品 6:其它    
	**/
	public void setCscType(Short cscType){
		this.cscType = cscType;
		this.setSeted(F.cscType);
	}
	/*******************************关怀主题**********************************/	
	/**
	* 关怀主题 [非空]      
	**/
	public String getCscTitle(){
		return this.cscTitle;
	}
	/**
	* 获取关怀主题格式化(toString)
	**/
	public String getCscTitle$(){
		String strValue="";
		 strValue=$.str(this.getCscTitle());
	 	 return strValue;
	}
	/**
	* 关怀主题 [非空]      
	**/
	public void setCscTitle(String cscTitle){
		this.cscTitle = cscTitle;
		this.setSeted(F.cscTitle);
	}
	/*******************************关怀描述**********************************/	
	/**
	* 关怀描述       
	**/
	public String getCscDesc(){
		return this.cscDesc;
	}
	/**
	* 获取关怀描述格式化(toString)
	**/
	public String getCscDesc$(){
		String strValue="";
		 strValue=$.str(this.getCscDesc());
	 	 return strValue;
	}
	/**
	* 关怀描述       
	**/
	public void setCscDesc(String cscDesc){
		this.cscDesc = cscDesc;
		this.setSeted(F.cscDesc);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCscUpdateTime(){
		return this.cscUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCscUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCscUpdateTime(Date cscUpdateTime){
		this.cscUpdateTime = cscUpdateTime;
		this.setSeted(F.cscUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCscAddTime(){
		return this.cscAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCscAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCscAddTime(Date cscAddTime){
		this.cscAddTime = cscAddTime;
		this.setSeted(F.cscAddTime);
	}
	/*******************************提醒时间**********************************/	
	/**
	* 提醒时间       
	**/
	public Date getCscAlarmTime(){
		return this.cscAlarmTime;
	}
	/**
	* 获取提醒时间格式化(toString)
	**/
	public String getCscAlarmTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscAlarmTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 提醒时间       
	**/
	public void setCscAlarmTime(Date cscAlarmTime){
		this.cscAlarmTime = cscAlarmTime;
		this.setSeted(F.cscAlarmTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:待跟踪 1:已完成    
	**/
	public Short getCscStatus(){
		return this.cscStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCscStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCscStatus()),"0"))
			strValue=$.str("待跟踪");		 
		 if($.equals($.str(this.getCscStatus()),"1"))
			strValue=$.str("已完成");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:待跟踪 1:已完成    
	**/
	public void setCscStatus(Short cscStatus){
		this.cscStatus = cscStatus;
		this.setSeted(F.cscStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsCare.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCare.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCare.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsCare.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsCare.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCare.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCare.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsCare.this);
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
		public M cscId(Object cscId){this.put("cscId", cscId);return this;};
	 	/** and csc_id is null */
 		public M cscIdNull(){if(this.get("cscIdNot")==null)this.put("cscIdNot", "");this.put("cscId", null);return this;};
 		/** not .... */
 		public M cscIdNot(){this.put("cscIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M cscHost(Object cscHost){this.put("cscHost", cscHost);return this;};
	 	/** and csc_host is null */
 		public M cscHostNull(){if(this.get("cscHostNot")==null)this.put("cscHostNot", "");this.put("cscHost", null);return this;};
 		/** not .... */
 		public M cscHostNot(){this.put("cscHostNot", "not");return this;};
		/** 关怀会员 [非空] [CsMember]      **/
		public M cscMember(Object cscMember){this.put("cscMember", cscMember);return this;};
	 	/** and csc_member is null */
 		public M cscMemberNull(){if(this.get("cscMemberNot")==null)this.put("cscMemberNot", "");this.put("cscMember", null);return this;};
 		/** not .... */
 		public M cscMemberNot(){this.put("cscMemberNot", "not");return this;};
		public M cscMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("cscMember$on", value);
			return this;
		};	
		/** 执行人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cscUser(Object cscUser){this.put("cscUser", cscUser);return this;};
	 	/** and csc_user is null */
 		public M cscUserNull(){if(this.get("cscUserNot")==null)this.put("cscUserNot", "");this.put("cscUser", null);return this;};
 		/** not .... */
 		public M cscUserNot(){this.put("cscUserNot", "not");return this;};
		/** 关怀类型 [非空] [SrvProperty]  care_sort:客户关怀类型     **/
		public M cscSort(Object cscSort){this.put("cscSort", cscSort);return this;};
	 	/** and csc_sort is null */
 		public M cscSortNull(){if(this.get("cscSortNot")==null)this.put("cscSortNot", "");this.put("cscSort", null);return this;};
 		/** not .... */
 		public M cscSortNot(){this.put("cscSortNot", "not");return this;};
		public M cscSort$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("cscSort$on", value);
			return this;
		};	
		/** 关怀方式 [非空]   0:打电话 1:发短信 2:上门 3:电子邮件 4:信函 5:送礼品 6:其它     **/
		public M cscType(Object cscType){this.put("cscType", cscType);return this;};
	 	/** and csc_type is null */
 		public M cscTypeNull(){if(this.get("cscTypeNot")==null)this.put("cscTypeNot", "");this.put("cscType", null);return this;};
 		/** not .... */
 		public M cscTypeNot(){this.put("cscTypeNot", "not");return this;};
		/** 关怀主题 [非空]       **/
		public M cscTitle(Object cscTitle){this.put("cscTitle", cscTitle);return this;};
	 	/** and csc_title is null */
 		public M cscTitleNull(){if(this.get("cscTitleNot")==null)this.put("cscTitleNot", "");this.put("cscTitle", null);return this;};
 		/** not .... */
 		public M cscTitleNot(){this.put("cscTitleNot", "not");return this;};
		/** 关怀描述        **/
		public M cscDesc(Object cscDesc){this.put("cscDesc", cscDesc);return this;};
	 	/** and csc_desc is null */
 		public M cscDescNull(){if(this.get("cscDescNot")==null)this.put("cscDescNot", "");this.put("cscDesc", null);return this;};
 		/** not .... */
 		public M cscDescNot(){this.put("cscDescNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M cscUpdateTime(Object cscUpdateTime){this.put("cscUpdateTime", cscUpdateTime);return this;};
	 	/** and csc_update_time is null */
 		public M cscUpdateTimeNull(){if(this.get("cscUpdateTimeNot")==null)this.put("cscUpdateTimeNot", "");this.put("cscUpdateTime", null);return this;};
 		/** not .... */
 		public M cscUpdateTimeNot(){this.put("cscUpdateTimeNot", "not");return this;};
 		/** and csc_update_time >= ? */
 		public M cscUpdateTimeStart(Object start){this.put("cscUpdateTimeStart", start);return this;};			
 		/** and csc_update_time <= ? */
 		public M cscUpdateTimeEnd(Object end){this.put("cscUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cscAddTime(Object cscAddTime){this.put("cscAddTime", cscAddTime);return this;};
	 	/** and csc_add_time is null */
 		public M cscAddTimeNull(){if(this.get("cscAddTimeNot")==null)this.put("cscAddTimeNot", "");this.put("cscAddTime", null);return this;};
 		/** not .... */
 		public M cscAddTimeNot(){this.put("cscAddTimeNot", "not");return this;};
 		/** and csc_add_time >= ? */
 		public M cscAddTimeStart(Object start){this.put("cscAddTimeStart", start);return this;};			
 		/** and csc_add_time <= ? */
 		public M cscAddTimeEnd(Object end){this.put("cscAddTimeEnd", end);return this;};
		/** 提醒时间        **/
		public M cscAlarmTime(Object cscAlarmTime){this.put("cscAlarmTime", cscAlarmTime);return this;};
	 	/** and csc_alarm_time is null */
 		public M cscAlarmTimeNull(){if(this.get("cscAlarmTimeNot")==null)this.put("cscAlarmTimeNot", "");this.put("cscAlarmTime", null);return this;};
 		/** not .... */
 		public M cscAlarmTimeNot(){this.put("cscAlarmTimeNot", "not");return this;};
 		/** and csc_alarm_time >= ? */
 		public M cscAlarmTimeStart(Object start){this.put("cscAlarmTimeStart", start);return this;};			
 		/** and csc_alarm_time <= ? */
 		public M cscAlarmTimeEnd(Object end){this.put("cscAlarmTimeEnd", end);return this;};
		/** 状态 [非空]   0:待跟踪 1:已完成     **/
		public M cscStatus(Object cscStatus){this.put("cscStatus", cscStatus);return this;};
	 	/** and csc_status is null */
 		public M cscStatusNull(){if(this.get("cscStatusNot")==null)this.put("cscStatusNot", "");this.put("cscStatus", null);return this;};
 		/** not .... */
 		public M cscStatusNot(){this.put("cscStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有客户关怀 **/
		public @api List<CsCare> list(Integer size){
			return getCsCareList(this,size);
		}
		/** 获取客户关怀分页 **/
		public @api Page<CsCare> page(int page,int size){
			return getCsCarePage(page, size , this);
		}
		/** 根据查询条件取客户关怀 **/
		public @api CsCare get(){
			return getCsCare(this);
		}
		/** 获取客户关怀数 **/
		public @api Long count(){
			return getCsCareCount(this);
		}
		/** 获取客户关怀表达式 **/
		public @api <T> T eval(String strEval){
			return getCsCareEval(strEval,this);
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
			updateCsCare(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscId="cscId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cscHost="cscHost";
		/** 关怀会员 [非空] [CsMember]      **/
		public final static @type(Long.class)  String cscMember="cscMember";
		/** 执行人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String cscUser="cscUser";
		/** 关怀类型 [非空] [SrvProperty]  care_sort:客户关怀类型     **/
		public final static @type(Long.class)  String cscSort="cscSort";
		/** 关怀方式 [非空]   0:打电话 1:发短信 2:上门 3:电子邮件 4:信函 5:送礼品 6:其它     **/
		public final static @type(Short.class)  String cscType="cscType";
		/** 关怀主题 [非空]       **/
		public final static @type(String.class) @like String cscTitle="cscTitle";
		/** 关怀描述        **/
		public final static @type(String.class) @like String cscDesc="cscDesc";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cscUpdateTime="cscUpdateTime";
	 	/** and csc_update_time >= ? */
 		public final static @type(Date.class) String cscUpdateTimeStart="cscUpdateTimeStart";
 		/** and csc_update_time <= ? */
 		public final static @type(Date.class) String cscUpdateTimeEnd="cscUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cscAddTime="cscAddTime";
	 	/** and csc_add_time >= ? */
 		public final static @type(Date.class) String cscAddTimeStart="cscAddTimeStart";
 		/** and csc_add_time <= ? */
 		public final static @type(Date.class) String cscAddTimeEnd="cscAddTimeEnd";
		/** 提醒时间        **/
		public final static @type(Date.class)  String cscAlarmTime="cscAlarmTime";
	 	/** and csc_alarm_time >= ? */
 		public final static @type(Date.class) String cscAlarmTimeStart="cscAlarmTimeStart";
 		/** and csc_alarm_time <= ? */
 		public final static @type(Date.class) String cscAlarmTimeEnd="cscAlarmTimeEnd";
		/** 状态 [非空]   0:待跟踪 1:已完成     **/
		public final static @type(Short.class)  String cscStatus="cscStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscId="csc_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cscHost="csc_host";
		/** 关怀会员 [非空] [CsMember]      **/
		public final static String cscMember="csc_member";
		/** 执行人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cscUser="csc_user";
		/** 关怀类型 [非空] [SrvProperty]  care_sort:客户关怀类型     **/
		public final static String cscSort="csc_sort";
		/** 关怀方式 [非空]   0:打电话 1:发短信 2:上门 3:电子邮件 4:信函 5:送礼品 6:其它     **/
		public final static String cscType="csc_type";
		/** 关怀主题 [非空]       **/
		public final static String cscTitle="csc_title";
		/** 关怀描述        **/
		public final static String cscDesc="csc_desc";
		/** 修改时间 [非空]       **/
		public final static String cscUpdateTime="csc_update_time";
		/** 添加时间 [非空]       **/
		public final static String cscAddTime="csc_add_time";
		/** 提醒时间        **/
		public final static String cscAlarmTime="csc_alarm_time";
		/** 状态 [非空]   0:待跟踪 1:已完成     **/
		public final static String cscStatus="csc_status";
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
				$.Set(name,CsCare.getCsCare(params));
			else
				$.Set(name,CsCare.getCsCareList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取客户关怀数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsCare) $.GetRequest("CsCare$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsCare.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsCare.getCsCare(params);
			else
				value = CsCare.getCsCareList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsCare.Get($.add(CsCare.F.cscId,param));
		else if(!$.empty(param.toString()))
			value = CsCare.get(Long.valueOf(param.toString()));
		$.SetRequest("CsCare$"+param.hashCode(), value);
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
	public void mergeSet(CsCare old){
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