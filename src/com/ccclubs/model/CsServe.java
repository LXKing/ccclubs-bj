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

@namespace("csm/serve")
public @caption("客户服务") @table("cs_serve") class CsServe implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("css_id")   @primary  @note("  ") Long cssId;// 主键 非空     
	private @caption("城市") @column("css_host")    @relate("$cssHost") @RelateClass(SrvHost.class)  @note("  ") Long cssHost;// 非空     
 	private SrvHost $cssHost;//关联对象[运营城市]
	private @caption("会员") @column("css_member")    @relate("$cssMember") @RelateClass(CsMember.class)  @note("  ") Long cssMember;// 非空     
 	private CsMember $cssMember;//关联对象[会员帐号]
	private @caption("处理人员") @column("css_user")    @relate("$cssUser") @RelateClass(SrvUser.class)  @note("  ") Long cssUser;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cssUser;//关联对象[用户]
	private @caption("服务类型") @column("css_sort")    @relate("$cssSort") @RelateClass(SrvProperty.class)  @note(" serve_sort:客户服务类型  ") Long cssSort;// 非空 serve_sort:客户服务类型     
 	private SrvProperty $cssSort;//关联对象[系统属性]
	private @caption("服务方式") @column("css_type")    @note(" 0:打电话 1:发短信 2:上门 3:电子邮件 4:信函 5:送礼品 6:其它  ") Short cssType;// 非空 0:打电话 1:发短信 2:上门 3:电子邮件 4:信函 5:送礼品 6:其它     
	private @caption("联系方向") @column("css_dir")    @note(" 0:车纷享>客户 1:客户>车纷享  ") Short cssDir;// 非空 0:车纷享>客户 1:客户>车纷享     
	private @caption("服务标题") @column("css_title")    @note("  ") String cssTitle;// 非空     
	private @caption("服务描述") @column("css_desc")    @note("  ") String cssDesc;//     
	private @caption("修改时间") @column("css_update_time")    @note("  ") Date cssUpdateTime;// 非空     
	private @caption("添加时间") @column("css_add_time")    @note("  ") Date cssAddTime;// 非空     
	private @caption("提醒时间") @column("css_alarm_time")    @note("  ") Date cssAlarmTime;//     
	private @caption("状态") @column("css_status")    @note(" 0:待跟踪 1:已完成  ") Short cssStatus;// 非空 0:待跟踪 1:已完成     
	
	//默认构造函数
	public CsServe(){
	
	}
	
	//主键构造函数
	public CsServe(Long id){
		this.cssId = id;
	}
	
	/**所有字段构造函数 CsServe(cssHost,cssMember,cssUser,cssSort,cssType,cssDir,cssTitle,cssDesc,cssUpdateTime,cssAddTime,cssAlarmTime,cssStatus)
	 CsServe(
	 	$.getLong("cssHost")//城市 [非空]
	 	,$.getLong("cssMember")//会员 [非空]
	 	,$.getLong("cssUser")//处理人员 [非空]
	 	,$.getLong("cssSort")//服务类型 [非空]
	 	,$.getShort("cssType")//服务方式 [非空]
	 	,$.getShort("cssDir")//联系方向 [非空]
	 	,$.getString("cssTitle")//服务标题 [非空]
	 	,$.getString("cssDesc")//服务描述
	 	,$.getDate("cssUpdateTime")//修改时间 [非空]
	 	,$.getDate("cssAddTime")//添加时间 [非空]
	 	,$.getDate("cssAlarmTime")//提醒时间
	 	,$.getShort("cssStatus")//状态 [非空]
	 )
	**/
	public CsServe(Long cssHost,Long cssMember,Long cssUser,Long cssSort,Short cssType,Short cssDir,String cssTitle,String cssDesc,Date cssUpdateTime,Date cssAddTime,Date cssAlarmTime,Short cssStatus){
		this.cssHost=cssHost;
		this.cssMember=cssMember;
		this.cssUser=cssUser;
		this.cssSort=cssSort;
		this.cssType=cssType;
		this.cssDir=cssDir;
		this.cssTitle=cssTitle;
		this.cssDesc=cssDesc;
		this.cssUpdateTime=cssUpdateTime;
		this.cssAddTime=cssAddTime;
		this.cssAlarmTime=cssAlarmTime;
		this.cssStatus=cssStatus;
	}
	
	//设置非空字段
	public CsServe setNotNull(Long cssHost,Long cssMember,Long cssUser,Long cssSort,Short cssType,Short cssDir,String cssTitle,Date cssUpdateTime,Date cssAddTime,Short cssStatus){
		this.cssHost=cssHost;
		this.cssMember=cssMember;
		this.cssUser=cssUser;
		this.cssSort=cssSort;
		this.cssType=cssType;
		this.cssDir=cssDir;
		this.cssTitle=cssTitle;
		this.cssUpdateTime=cssUpdateTime;
		this.cssAddTime=cssAddTime;
		this.cssStatus=cssStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsServe cssId(Long cssId){
		this.cssId = cssId;
		this.setSeted(F.cssId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsServe cssHost(Long cssHost){
		this.cssHost = cssHost;
		this.setSeted(F.cssHost);
		return this;
	}
	/** 会员 [非空] [CsMember]      **/
	public CsServe cssMember(Long cssMember){
		this.cssMember = cssMember;
		this.setSeted(F.cssMember);
		return this;
	}
	/** 处理人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsServe cssUser(Long cssUser){
		this.cssUser = cssUser;
		this.setSeted(F.cssUser);
		return this;
	}
	/** 服务类型 [非空] [SrvProperty]  serve_sort:客户服务类型     **/
	public CsServe cssSort(Long cssSort){
		this.cssSort = cssSort;
		this.setSeted(F.cssSort);
		return this;
	}
	/** 服务方式 [非空]   0:打电话 1:发短信 2:上门 3:电子邮件 4:信函 5:送礼品 6:其它     **/
	public CsServe cssType(Short cssType){
		this.cssType = cssType;
		this.setSeted(F.cssType);
		return this;
	}
	/** 联系方向 [非空]   0:车纷享>客户 1:客户>车纷享     **/
	public CsServe cssDir(Short cssDir){
		this.cssDir = cssDir;
		this.setSeted(F.cssDir);
		return this;
	}
	/** 服务标题 [非空]       **/
	public CsServe cssTitle(String cssTitle){
		this.cssTitle = cssTitle;
		this.setSeted(F.cssTitle);
		return this;
	}
	/** 服务描述        **/
	public CsServe cssDesc(String cssDesc){
		this.cssDesc = cssDesc;
		this.setSeted(F.cssDesc);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsServe cssUpdateTime(Date cssUpdateTime){
		this.cssUpdateTime = cssUpdateTime;
		this.setSeted(F.cssUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsServe cssAddTime(Date cssAddTime){
		this.cssAddTime = cssAddTime;
		this.setSeted(F.cssAddTime);
		return this;
	}
	/** 提醒时间        **/
	public CsServe cssAlarmTime(Date cssAlarmTime){
		this.cssAlarmTime = cssAlarmTime;
		this.setSeted(F.cssAlarmTime);
		return this;
	}
	/** 状态 [非空]   0:待跟踪 1:已完成     **/
	public CsServe cssStatus(Short cssStatus){
		this.cssStatus = cssStatus;
		this.setSeted(F.cssStatus);
		return this;
	}
	
	
	//克隆对象
	public CsServe clone(){
		CsServe clone = new CsServe();
		clone.cssHost=this.cssHost;
		clone.cssMember=this.cssMember;
		clone.cssUser=this.cssUser;
		clone.cssSort=this.cssSort;
		clone.cssType=this.cssType;
		clone.cssDir=this.cssDir;
		clone.cssTitle=this.cssTitle;
		clone.cssUpdateTime=this.cssUpdateTime;
		clone.cssAddTime=this.cssAddTime;
		clone.cssStatus=this.cssStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取客户服务
	 * @param id
	 * @return
	 */
	public static @api CsServe get(Long id){		
		return getCsServeById(id);
	}
	/**
	 * 获取所有客户服务
	 * @return
	 */
	public static @api List<CsServe> list(Map params,Integer size){
		return getCsServeList(params,size);
	}
	/**
	 * 获取客户服务分页
	 * @return
	 */
	public static @api Page<CsServe> page(int page,int size,Map params){
		return getCsServePage(page, size , params);
	}
	/**
	 * 根据查询条件取客户服务
	 * @param params
	 * @return
	 */
	public static @api CsServe Get(Map params){
		return getCsServe(params);
	}
	/**
	 * 获取客户服务数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsServeCount(params);
	}
	/**
	 * 获取客户服务数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsServeEval(eval,params);
	}
	
	/**
	 * 根据ID取客户服务
	 * @param id
	 * @return
	 */
	public static @api CsServe getCsServeById(Long id){		
		CsServe csServe = (CsServe) $.GetRequest("CsServe$"+id);
		if(csServe!=null)
			return csServe;
		com.ccclubs.service.admin.ICsServeService csServeService = $.GetSpringBean("csServeService");		
		return csServeService.getCsServeById(id);
	}
	
	
	/**
	 * 根据ID取客户服务的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsServe.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsServe csServe = get(id);
		if(csServe!=null){
			String strValue = csServe.getCssTitle$();
			if(!"CssTitle".equals("CssTitle"))
				strValue+="("+csServe.getCssTitle$()+")";
			MemCache.setValue(CsServe.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCssTitle$();
		if(!"CssTitle".equals("CssTitle"))
			keyValue+="("+this.getCssTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有客户服务
	 * @return
	 */
	public static @api List<CsServe> getCsServeList(Map params,Integer size){
		com.ccclubs.service.admin.ICsServeService csServeService = $.GetSpringBean("csServeService");
		return csServeService.getCsServeList(params, size);
	}
	
	/**
	 * 获取客户服务分页
	 * @return
	 */
	public static @api Page<CsServe> getCsServePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsServeService csServeService = $.GetSpringBean("csServeService");
		return csServeService.getCsServePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取客户服务
	 * @param params
	 * @return
	 */
	public static @api CsServe getCsServe(Map params){
		com.ccclubs.service.admin.ICsServeService csServeService = $.GetSpringBean("csServeService");
		return csServeService.getCsServe(params);
	}
	
	/**
	 * 获取客户服务数
	 * @return
	 */
	public static @api Long getCsServeCount(Map params){
		com.ccclubs.service.admin.ICsServeService csServeService = $.GetSpringBean("csServeService");
		return csServeService.getCsServeCount(params);
	}
		
		
	/**
	 * 获取客户服务自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsServeEval(String eval,Map params){
		com.ccclubs.service.admin.ICsServeService csServeService = $.GetSpringBean("csServeService");
		return csServeService.getCsServeEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsServe(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsServeService csServeService = $.GetSpringBean("csServeService");
		csServeService.updateCsServeByConfirm(set, where);
	}
	
	
	/**
	 * 保存客户服务对象
	 * @param params
	 * @return
	 */
	public CsServe save(){
		com.ccclubs.service.admin.ICsServeService csServeService = $.GetSpringBean("csServeService");
		if(this.getCssId()!=null)
			csServeService.updateCsServe(this);
		else
			return csServeService.saveCsServe(this);
		return this;
	}
	
	
	/**
	 * 更新客户服务对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsServeService csServeService = $.GetSpringBean("csServeService");
		csServeService.updateCsServe$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsServeService csServeService = $.GetSpringBean("csServeService");
		if($.equals($.config("logic_delete"),"true"))
			csServeService.removeCsServeById(this.getCssId());
		else
			csServeService.deleteCsServeById(this.getCssId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsServeService csServeService = $.GetSpringBean("csServeService");
		return csServeService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCssId(){
		return this.cssId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCssId$(){
		String strValue="";
		 strValue=$.str(this.getCssId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCssId(Long cssId){
		this.cssId = cssId;
		this.setSeted(F.cssId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCssHost(){
		return this.cssHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCssHost$(){
		String strValue="";
		if(this.getCssHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCssHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCssHost(Long cssHost){
		this.cssHost = cssHost;
		this.setSeted(F.cssHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cssHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCssHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCssHost()!=null){
 			srvHost = SrvHost.get(this.getCssHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCssHost(), srvHost);
	 	return srvHost;
	}
	/*******************************会员**********************************/	
	/**
	* 会员 [非空] [CsMember]     
	**/
	public Long getCssMember(){
		return this.cssMember;
	}
	/**
	* 获取会员格式化(toString)
	**/
	public String getCssMember$(){
		String strValue="";
		if(this.getCssMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCssMember()));
		}			
	 	 return strValue;
	}
	/**
	* 会员 [非空] [CsMember]     
	**/
	public void setCssMember(Long cssMember){
		this.cssMember = cssMember;
		this.setSeted(F.cssMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$cssMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCssMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCssMember()!=null){
 			csMember = CsMember.get(this.getCssMember());
 		}
 		$.SetRequest("CsMember$"+this.getCssMember(), csMember);
	 	return csMember;
	}
	/*******************************处理人员**********************************/	
	/**
	* 处理人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCssUser(){
		return this.cssUser;
	}
	/**
	* 获取处理人员格式化(toString)
	**/
	public String getCssUser$(){
		String strValue="";
		if(this.getCssUser()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCssUser()));
		}			
	 	 return strValue;
	}
	/**
	* 处理人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCssUser(Long cssUser){
		this.cssUser = cssUser;
		this.setSeted(F.cssUser);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cssUser(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCssUser());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCssUser()!=null){
 			srvUser = SrvUser.get(this.getCssUser());
 		}
 		$.SetRequest("SrvUser$"+this.getCssUser(), srvUser);
	 	return srvUser;
	}
	/*******************************服务类型**********************************/	
	/**
	* 服务类型 [非空] [SrvProperty]  serve_sort:客户服务类型    
	**/
	public Long getCssSort(){
		return this.cssSort;
	}
	/**
	* 获取服务类型格式化(toString)
	**/
	public String getCssSort$(){
		String strValue="";
		if(this.getCssSort()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCssSort()));
		}			
	 	 return strValue;
	}
	/**
	* 服务类型 [非空] [SrvProperty]  serve_sort:客户服务类型    
	**/
	public void setCssSort(Long cssSort){
		this.cssSort = cssSort;
		this.setSeted(F.cssSort);
	}
	/**
	* 获取关联对象[系统属性]
	**/	 			
 	public SrvProperty get$cssSort(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCssSort());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCssSort()!=null){
 			srvProperty = SrvProperty.get(this.getCssSort());
 		}
 		$.SetRequest("SrvProperty$"+this.getCssSort(), srvProperty);
	 	return srvProperty;
	}
	/*******************************服务方式**********************************/	
	/**
	* 服务方式 [非空]   0:打电话 1:发短信 2:上门 3:电子邮件 4:信函 5:送礼品 6:其它    
	**/
	public Short getCssType(){
		return this.cssType;
	}
	/**
	* 获取服务方式格式化(toString)
	**/
	public String getCssType$(){
		String strValue="";
		 if($.equals($.str(this.getCssType()),"0"))
			strValue=$.str("打电话");		 
		 if($.equals($.str(this.getCssType()),"1"))
			strValue=$.str("发短信");		 
		 if($.equals($.str(this.getCssType()),"2"))
			strValue=$.str("上门");		 
		 if($.equals($.str(this.getCssType()),"3"))
			strValue=$.str("电子邮件");		 
		 if($.equals($.str(this.getCssType()),"4"))
			strValue=$.str("信函");		 
		 if($.equals($.str(this.getCssType()),"5"))
			strValue=$.str("送礼品");		 
		 if($.equals($.str(this.getCssType()),"6"))
			strValue=$.str("其它");		 
	 	 return strValue;
	}
	/**
	* 服务方式 [非空]   0:打电话 1:发短信 2:上门 3:电子邮件 4:信函 5:送礼品 6:其它    
	**/
	public void setCssType(Short cssType){
		this.cssType = cssType;
		this.setSeted(F.cssType);
	}
	/*******************************联系方向**********************************/	
	/**
	* 联系方向 [非空]   0:车纷享>客户 1:客户>车纷享    
	**/
	public Short getCssDir(){
		return this.cssDir;
	}
	/**
	* 获取联系方向格式化(toString)
	**/
	public String getCssDir$(){
		String strValue="";
		 if($.equals($.str(this.getCssDir()),"0"))
			strValue=$.str("车纷享>客户");		 
		 if($.equals($.str(this.getCssDir()),"1"))
			strValue=$.str("客户>车纷享");		 
	 	 return strValue;
	}
	/**
	* 联系方向 [非空]   0:车纷享>客户 1:客户>车纷享    
	**/
	public void setCssDir(Short cssDir){
		this.cssDir = cssDir;
		this.setSeted(F.cssDir);
	}
	/*******************************服务标题**********************************/	
	/**
	* 服务标题 [非空]      
	**/
	public String getCssTitle(){
		return this.cssTitle;
	}
	/**
	* 获取服务标题格式化(toString)
	**/
	public String getCssTitle$(){
		String strValue="";
		 strValue=$.str(this.getCssTitle());
	 	 return strValue;
	}
	/**
	* 服务标题 [非空]      
	**/
	public void setCssTitle(String cssTitle){
		this.cssTitle = cssTitle;
		this.setSeted(F.cssTitle);
	}
	/*******************************服务描述**********************************/	
	/**
	* 服务描述       
	**/
	public String getCssDesc(){
		return this.cssDesc;
	}
	/**
	* 获取服务描述格式化(toString)
	**/
	public String getCssDesc$(){
		String strValue="";
		 strValue=$.str(this.getCssDesc());
	 	 return strValue;
	}
	/**
	* 服务描述       
	**/
	public void setCssDesc(String cssDesc){
		this.cssDesc = cssDesc;
		this.setSeted(F.cssDesc);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCssUpdateTime(){
		return this.cssUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCssUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCssUpdateTime(Date cssUpdateTime){
		this.cssUpdateTime = cssUpdateTime;
		this.setSeted(F.cssUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCssAddTime(){
		return this.cssAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCssAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCssAddTime(Date cssAddTime){
		this.cssAddTime = cssAddTime;
		this.setSeted(F.cssAddTime);
	}
	/*******************************提醒时间**********************************/	
	/**
	* 提醒时间       
	**/
	public Date getCssAlarmTime(){
		return this.cssAlarmTime;
	}
	/**
	* 获取提醒时间格式化(toString)
	**/
	public String getCssAlarmTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssAlarmTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 提醒时间       
	**/
	public void setCssAlarmTime(Date cssAlarmTime){
		this.cssAlarmTime = cssAlarmTime;
		this.setSeted(F.cssAlarmTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:待跟踪 1:已完成    
	**/
	public Short getCssStatus(){
		return this.cssStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCssStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCssStatus()),"0"))
			strValue=$.str("待跟踪");		 
		 if($.equals($.str(this.getCssStatus()),"1"))
			strValue=$.str("已完成");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:待跟踪 1:已完成    
	**/
	public void setCssStatus(Short cssStatus){
		this.cssStatus = cssStatus;
		this.setSeted(F.cssStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsServe.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsServe.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsServe.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsServe.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsServe.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsServe.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsServe.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsServe.this);
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
		public M cssId(Object cssId){this.put("cssId", cssId);return this;};
	 	/** and css_id is null */
 		public M cssIdNull(){if(this.get("cssIdNot")==null)this.put("cssIdNot", "");this.put("cssId", null);return this;};
 		/** not .... */
 		public M cssIdNot(){this.put("cssIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M cssHost(Object cssHost){this.put("cssHost", cssHost);return this;};
	 	/** and css_host is null */
 		public M cssHostNull(){if(this.get("cssHostNot")==null)this.put("cssHostNot", "");this.put("cssHost", null);return this;};
 		/** not .... */
 		public M cssHostNot(){this.put("cssHostNot", "not");return this;};
		/** 会员 [非空] [CsMember]      **/
		public M cssMember(Object cssMember){this.put("cssMember", cssMember);return this;};
	 	/** and css_member is null */
 		public M cssMemberNull(){if(this.get("cssMemberNot")==null)this.put("cssMemberNot", "");this.put("cssMember", null);return this;};
 		/** not .... */
 		public M cssMemberNot(){this.put("cssMemberNot", "not");return this;};
		public M cssMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("cssMember$on", value);
			return this;
		};	
		/** 处理人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cssUser(Object cssUser){this.put("cssUser", cssUser);return this;};
	 	/** and css_user is null */
 		public M cssUserNull(){if(this.get("cssUserNot")==null)this.put("cssUserNot", "");this.put("cssUser", null);return this;};
 		/** not .... */
 		public M cssUserNot(){this.put("cssUserNot", "not");return this;};
		/** 服务类型 [非空] [SrvProperty]  serve_sort:客户服务类型     **/
		public M cssSort(Object cssSort){this.put("cssSort", cssSort);return this;};
	 	/** and css_sort is null */
 		public M cssSortNull(){if(this.get("cssSortNot")==null)this.put("cssSortNot", "");this.put("cssSort", null);return this;};
 		/** not .... */
 		public M cssSortNot(){this.put("cssSortNot", "not");return this;};
		public M cssSort$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("cssSort$on", value);
			return this;
		};	
		/** 服务方式 [非空]   0:打电话 1:发短信 2:上门 3:电子邮件 4:信函 5:送礼品 6:其它     **/
		public M cssType(Object cssType){this.put("cssType", cssType);return this;};
	 	/** and css_type is null */
 		public M cssTypeNull(){if(this.get("cssTypeNot")==null)this.put("cssTypeNot", "");this.put("cssType", null);return this;};
 		/** not .... */
 		public M cssTypeNot(){this.put("cssTypeNot", "not");return this;};
		/** 联系方向 [非空]   0:车纷享>客户 1:客户>车纷享     **/
		public M cssDir(Object cssDir){this.put("cssDir", cssDir);return this;};
	 	/** and css_dir is null */
 		public M cssDirNull(){if(this.get("cssDirNot")==null)this.put("cssDirNot", "");this.put("cssDir", null);return this;};
 		/** not .... */
 		public M cssDirNot(){this.put("cssDirNot", "not");return this;};
		/** 服务标题 [非空]       **/
		public M cssTitle(Object cssTitle){this.put("cssTitle", cssTitle);return this;};
	 	/** and css_title is null */
 		public M cssTitleNull(){if(this.get("cssTitleNot")==null)this.put("cssTitleNot", "");this.put("cssTitle", null);return this;};
 		/** not .... */
 		public M cssTitleNot(){this.put("cssTitleNot", "not");return this;};
		/** 服务描述        **/
		public M cssDesc(Object cssDesc){this.put("cssDesc", cssDesc);return this;};
	 	/** and css_desc is null */
 		public M cssDescNull(){if(this.get("cssDescNot")==null)this.put("cssDescNot", "");this.put("cssDesc", null);return this;};
 		/** not .... */
 		public M cssDescNot(){this.put("cssDescNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M cssUpdateTime(Object cssUpdateTime){this.put("cssUpdateTime", cssUpdateTime);return this;};
	 	/** and css_update_time is null */
 		public M cssUpdateTimeNull(){if(this.get("cssUpdateTimeNot")==null)this.put("cssUpdateTimeNot", "");this.put("cssUpdateTime", null);return this;};
 		/** not .... */
 		public M cssUpdateTimeNot(){this.put("cssUpdateTimeNot", "not");return this;};
 		/** and css_update_time >= ? */
 		public M cssUpdateTimeStart(Object start){this.put("cssUpdateTimeStart", start);return this;};			
 		/** and css_update_time <= ? */
 		public M cssUpdateTimeEnd(Object end){this.put("cssUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cssAddTime(Object cssAddTime){this.put("cssAddTime", cssAddTime);return this;};
	 	/** and css_add_time is null */
 		public M cssAddTimeNull(){if(this.get("cssAddTimeNot")==null)this.put("cssAddTimeNot", "");this.put("cssAddTime", null);return this;};
 		/** not .... */
 		public M cssAddTimeNot(){this.put("cssAddTimeNot", "not");return this;};
 		/** and css_add_time >= ? */
 		public M cssAddTimeStart(Object start){this.put("cssAddTimeStart", start);return this;};			
 		/** and css_add_time <= ? */
 		public M cssAddTimeEnd(Object end){this.put("cssAddTimeEnd", end);return this;};
		/** 提醒时间        **/
		public M cssAlarmTime(Object cssAlarmTime){this.put("cssAlarmTime", cssAlarmTime);return this;};
	 	/** and css_alarm_time is null */
 		public M cssAlarmTimeNull(){if(this.get("cssAlarmTimeNot")==null)this.put("cssAlarmTimeNot", "");this.put("cssAlarmTime", null);return this;};
 		/** not .... */
 		public M cssAlarmTimeNot(){this.put("cssAlarmTimeNot", "not");return this;};
 		/** and css_alarm_time >= ? */
 		public M cssAlarmTimeStart(Object start){this.put("cssAlarmTimeStart", start);return this;};			
 		/** and css_alarm_time <= ? */
 		public M cssAlarmTimeEnd(Object end){this.put("cssAlarmTimeEnd", end);return this;};
		/** 状态 [非空]   0:待跟踪 1:已完成     **/
		public M cssStatus(Object cssStatus){this.put("cssStatus", cssStatus);return this;};
	 	/** and css_status is null */
 		public M cssStatusNull(){if(this.get("cssStatusNot")==null)this.put("cssStatusNot", "");this.put("cssStatus", null);return this;};
 		/** not .... */
 		public M cssStatusNot(){this.put("cssStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有客户服务 **/
		public @api List<CsServe> list(Integer size){
			return getCsServeList(this,size);
		}
		/** 获取客户服务分页 **/
		public @api Page<CsServe> page(int page,int size){
			return getCsServePage(page, size , this);
		}
		/** 根据查询条件取客户服务 **/
		public @api CsServe get(){
			return getCsServe(this);
		}
		/** 获取客户服务数 **/
		public @api Long count(){
			return getCsServeCount(this);
		}
		/** 获取客户服务表达式 **/
		public @api <T> T eval(String strEval){
			return getCsServeEval(strEval,this);
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
			updateCsServe(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cssId="cssId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cssHost="cssHost";
		/** 会员 [非空] [CsMember]      **/
		public final static @type(Long.class)  String cssMember="cssMember";
		/** 处理人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String cssUser="cssUser";
		/** 服务类型 [非空] [SrvProperty]  serve_sort:客户服务类型     **/
		public final static @type(Long.class)  String cssSort="cssSort";
		/** 服务方式 [非空]   0:打电话 1:发短信 2:上门 3:电子邮件 4:信函 5:送礼品 6:其它     **/
		public final static @type(Short.class)  String cssType="cssType";
		/** 联系方向 [非空]   0:车纷享>客户 1:客户>车纷享     **/
		public final static @type(Short.class)  String cssDir="cssDir";
		/** 服务标题 [非空]       **/
		public final static @type(String.class) @like String cssTitle="cssTitle";
		/** 服务描述        **/
		public final static @type(String.class) @like String cssDesc="cssDesc";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cssUpdateTime="cssUpdateTime";
	 	/** and css_update_time >= ? */
 		public final static @type(Date.class) String cssUpdateTimeStart="cssUpdateTimeStart";
 		/** and css_update_time <= ? */
 		public final static @type(Date.class) String cssUpdateTimeEnd="cssUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cssAddTime="cssAddTime";
	 	/** and css_add_time >= ? */
 		public final static @type(Date.class) String cssAddTimeStart="cssAddTimeStart";
 		/** and css_add_time <= ? */
 		public final static @type(Date.class) String cssAddTimeEnd="cssAddTimeEnd";
		/** 提醒时间        **/
		public final static @type(Date.class)  String cssAlarmTime="cssAlarmTime";
	 	/** and css_alarm_time >= ? */
 		public final static @type(Date.class) String cssAlarmTimeStart="cssAlarmTimeStart";
 		/** and css_alarm_time <= ? */
 		public final static @type(Date.class) String cssAlarmTimeEnd="cssAlarmTimeEnd";
		/** 状态 [非空]   0:待跟踪 1:已完成     **/
		public final static @type(Short.class)  String cssStatus="cssStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cssId="css_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cssHost="css_host";
		/** 会员 [非空] [CsMember]      **/
		public final static String cssMember="css_member";
		/** 处理人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cssUser="css_user";
		/** 服务类型 [非空] [SrvProperty]  serve_sort:客户服务类型     **/
		public final static String cssSort="css_sort";
		/** 服务方式 [非空]   0:打电话 1:发短信 2:上门 3:电子邮件 4:信函 5:送礼品 6:其它     **/
		public final static String cssType="css_type";
		/** 联系方向 [非空]   0:车纷享>客户 1:客户>车纷享     **/
		public final static String cssDir="css_dir";
		/** 服务标题 [非空]       **/
		public final static String cssTitle="css_title";
		/** 服务描述        **/
		public final static String cssDesc="css_desc";
		/** 修改时间 [非空]       **/
		public final static String cssUpdateTime="css_update_time";
		/** 添加时间 [非空]       **/
		public final static String cssAddTime="css_add_time";
		/** 提醒时间        **/
		public final static String cssAlarmTime="css_alarm_time";
		/** 状态 [非空]   0:待跟踪 1:已完成     **/
		public final static String cssStatus="css_status";
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
				$.Set(name,CsServe.getCsServe(params));
			else
				$.Set(name,CsServe.getCsServeList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取客户服务数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsServe) $.GetRequest("CsServe$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsServe.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsServe.getCsServe(params);
			else
				value = CsServe.getCsServeList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsServe.Get($.add(CsServe.F.cssId,param));
		else if(!$.empty(param.toString()))
			value = CsServe.get(Long.valueOf(param.toString()));
		$.SetRequest("CsServe$"+param.hashCode(), value);
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
	public void mergeSet(CsServe old){
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