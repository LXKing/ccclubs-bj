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

@namespace("sale/survey")
public @caption("市场调查") @table("cs_sale_survey") class CsSaleSurvey implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csss_id")   @primary  @note("  ") Long csssId;// 主键 非空     
	private @caption("城市") @column("csss_host")    @relate("$csssHost") @RelateClass(SrvHost.class)  @note("  ") Long csssHost;// 非空     
 	private SrvHost $csssHost;//关联对象[运营城市]
	private @caption("调查主题") @column("csss_subject")    @note("  ") String csssSubject;// 非空     
	private @caption("所属活动") @column("csss_event")    @relate("$csssEvent") @RelateClass(CsSaleEvent.class)  @note("  ") Long csssEvent;// 非空     
 	private CsSaleEvent $csssEvent;//关联对象[销售活动]
	private @caption("调查类型") @column("csss_type")    @relate("$csssType") @RelateClass(SrvProperty.class)  @note(" survey_type:市场调查类型  比如：综合性调查 区域品牌 竞争 价格 认知满意度 负面调查 行业调查 宏观政策 容量") Long csssType;// 非空 survey_type:市场调查类型  比如：综合性调查 区域品牌 竞争 价格 认知满意度 负面调查 行业调查 宏观政策 容量   
 	private SrvProperty $csssType;//关联对象[系统属性]
	private @caption("调查结论") @column("csss_detail")    @note("  ") String csssDetail;// 非空     
	private @caption("添加人") @column("csss_user")    @relate("$csssUser") @RelateClass(SrvUser.class)  @note("  ") Long csssUser;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csssUser;//关联对象[用户]
	private @caption("修改时间") @column("csss_update_time")    @note("  ") Date csssUpdateTime;// 非空     
	private @caption("添加时间") @column("csss_add_time")    @note("  ") Date csssAddTime;// 非空     
	private @caption("状态") @column("csss_status")    @note(" 1:正常 0:无效  ") Short csssStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsSaleSurvey(){
	
	}
	
	//主键构造函数
	public CsSaleSurvey(Long id){
		this.csssId = id;
	}
	
	/**所有字段构造函数 CsSaleSurvey(csssHost,csssSubject,csssEvent,csssType,csssDetail,csssUser,csssUpdateTime,csssAddTime,csssStatus)
	 CsSaleSurvey(
	 	$.getLong("csssHost")//城市 [非空]
	 	,$.getString("csssSubject")//调查主题 [非空]
	 	,$.getLong("csssEvent")//所属活动 [非空]
	 	,$.getLong("csssType")//调查类型 [非空]
	 	,$.getString("csssDetail")//调查结论 [非空]
	 	,$.getLong("csssUser")//添加人 [非空]
	 	,$.getDate("csssUpdateTime")//修改时间 [非空]
	 	,$.getDate("csssAddTime")//添加时间 [非空]
	 	,$.getShort("csssStatus")//状态 [非空]
	 )
	**/
	public CsSaleSurvey(Long csssHost,String csssSubject,Long csssEvent,Long csssType,String csssDetail,Long csssUser,Date csssUpdateTime,Date csssAddTime,Short csssStatus){
		this.csssHost=csssHost;
		this.csssSubject=csssSubject;
		this.csssEvent=csssEvent;
		this.csssType=csssType;
		this.csssDetail=csssDetail;
		this.csssUser=csssUser;
		this.csssUpdateTime=csssUpdateTime;
		this.csssAddTime=csssAddTime;
		this.csssStatus=csssStatus;
	}
	
	//设置非空字段
	public CsSaleSurvey setNotNull(Long csssHost,String csssSubject,Long csssEvent,Long csssType,String csssDetail,Long csssUser,Date csssUpdateTime,Date csssAddTime,Short csssStatus){
		this.csssHost=csssHost;
		this.csssSubject=csssSubject;
		this.csssEvent=csssEvent;
		this.csssType=csssType;
		this.csssDetail=csssDetail;
		this.csssUser=csssUser;
		this.csssUpdateTime=csssUpdateTime;
		this.csssAddTime=csssAddTime;
		this.csssStatus=csssStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsSaleSurvey csssId(Long csssId){
		this.csssId = csssId;
		this.setSeted(F.csssId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsSaleSurvey csssHost(Long csssHost){
		this.csssHost = csssHost;
		this.setSeted(F.csssHost);
		return this;
	}
	/** 调查主题 [非空]       **/
	public CsSaleSurvey csssSubject(String csssSubject){
		this.csssSubject = csssSubject;
		this.setSeted(F.csssSubject);
		return this;
	}
	/** 所属活动 [非空] [CsSaleEvent]      **/
	public CsSaleSurvey csssEvent(Long csssEvent){
		this.csssEvent = csssEvent;
		this.setSeted(F.csssEvent);
		return this;
	}
	/** 调查类型 [非空] [SrvProperty]  survey_type:市场调查类型  比如：综合性调查 区域品牌 竞争 价格 认知满意度 负面调查 行业调查 宏观政策 容量   **/
	public CsSaleSurvey csssType(Long csssType){
		this.csssType = csssType;
		this.setSeted(F.csssType);
		return this;
	}
	/** 调查结论 [非空]       **/
	public CsSaleSurvey csssDetail(String csssDetail){
		this.csssDetail = csssDetail;
		this.setSeted(F.csssDetail);
		return this;
	}
	/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsSaleSurvey csssUser(Long csssUser){
		this.csssUser = csssUser;
		this.setSeted(F.csssUser);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsSaleSurvey csssUpdateTime(Date csssUpdateTime){
		this.csssUpdateTime = csssUpdateTime;
		this.setSeted(F.csssUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsSaleSurvey csssAddTime(Date csssAddTime){
		this.csssAddTime = csssAddTime;
		this.setSeted(F.csssAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsSaleSurvey csssStatus(Short csssStatus){
		this.csssStatus = csssStatus;
		this.setSeted(F.csssStatus);
		return this;
	}
	
	
	//克隆对象
	public CsSaleSurvey clone(){
		CsSaleSurvey clone = new CsSaleSurvey();
		clone.csssHost=this.csssHost;
		clone.csssSubject=this.csssSubject;
		clone.csssEvent=this.csssEvent;
		clone.csssType=this.csssType;
		clone.csssDetail=this.csssDetail;
		clone.csssUser=this.csssUser;
		clone.csssUpdateTime=this.csssUpdateTime;
		clone.csssAddTime=this.csssAddTime;
		clone.csssStatus=this.csssStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取市场调查
	 * @param id
	 * @return
	 */
	public static @api CsSaleSurvey get(Long id){		
		return getCsSaleSurveyById(id);
	}
	/**
	 * 获取所有市场调查
	 * @return
	 */
	public static @api List<CsSaleSurvey> list(Map params,Integer size){
		return getCsSaleSurveyList(params,size);
	}
	/**
	 * 获取市场调查分页
	 * @return
	 */
	public static @api Page<CsSaleSurvey> page(int page,int size,Map params){
		return getCsSaleSurveyPage(page, size , params);
	}
	/**
	 * 根据查询条件取市场调查
	 * @param params
	 * @return
	 */
	public static @api CsSaleSurvey Get(Map params){
		return getCsSaleSurvey(params);
	}
	/**
	 * 获取市场调查数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsSaleSurveyCount(params);
	}
	/**
	 * 获取市场调查数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsSaleSurveyEval(eval,params);
	}
	
	/**
	 * 根据ID取市场调查
	 * @param id
	 * @return
	 */
	public static @api CsSaleSurvey getCsSaleSurveyById(Long id){		
		CsSaleSurvey csSaleSurvey = (CsSaleSurvey) $.GetRequest("CsSaleSurvey$"+id);
		if(csSaleSurvey!=null)
			return csSaleSurvey;
		com.ccclubs.service.admin.ICsSaleSurveyService csSaleSurveyService = $.GetSpringBean("csSaleSurveyService");		
		return csSaleSurveyService.getCsSaleSurveyById(id);
	}
	
	
	/**
	 * 根据ID取市场调查的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsSaleSurvey.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsSaleSurvey csSaleSurvey = get(id);
		if(csSaleSurvey!=null){
			String strValue = csSaleSurvey.getCsssSubject$();
			if(!"CsssSubject".equals("CsssSubject"))
				strValue+="("+csSaleSurvey.getCsssSubject$()+")";
			MemCache.setValue(CsSaleSurvey.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsssSubject$();
		if(!"CsssSubject".equals("CsssSubject"))
			keyValue+="("+this.getCsssSubject$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有市场调查
	 * @return
	 */
	public static @api List<CsSaleSurvey> getCsSaleSurveyList(Map params,Integer size){
		com.ccclubs.service.admin.ICsSaleSurveyService csSaleSurveyService = $.GetSpringBean("csSaleSurveyService");
		return csSaleSurveyService.getCsSaleSurveyList(params, size);
	}
	
	/**
	 * 获取市场调查分页
	 * @return
	 */
	public static @api Page<CsSaleSurvey> getCsSaleSurveyPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsSaleSurveyService csSaleSurveyService = $.GetSpringBean("csSaleSurveyService");
		return csSaleSurveyService.getCsSaleSurveyPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取市场调查
	 * @param params
	 * @return
	 */
	public static @api CsSaleSurvey getCsSaleSurvey(Map params){
		com.ccclubs.service.admin.ICsSaleSurveyService csSaleSurveyService = $.GetSpringBean("csSaleSurveyService");
		return csSaleSurveyService.getCsSaleSurvey(params);
	}
	
	/**
	 * 获取市场调查数
	 * @return
	 */
	public static @api Long getCsSaleSurveyCount(Map params){
		com.ccclubs.service.admin.ICsSaleSurveyService csSaleSurveyService = $.GetSpringBean("csSaleSurveyService");
		return csSaleSurveyService.getCsSaleSurveyCount(params);
	}
		
		
	/**
	 * 获取市场调查自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsSaleSurveyEval(String eval,Map params){
		com.ccclubs.service.admin.ICsSaleSurveyService csSaleSurveyService = $.GetSpringBean("csSaleSurveyService");
		return csSaleSurveyService.getCsSaleSurveyEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsSaleSurvey(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsSaleSurveyService csSaleSurveyService = $.GetSpringBean("csSaleSurveyService");
		csSaleSurveyService.updateCsSaleSurveyByConfirm(set, where);
	}
	
	
	/**
	 * 保存市场调查对象
	 * @param params
	 * @return
	 */
	public CsSaleSurvey save(){
		com.ccclubs.service.admin.ICsSaleSurveyService csSaleSurveyService = $.GetSpringBean("csSaleSurveyService");
		if(this.getCsssId()!=null)
			csSaleSurveyService.updateCsSaleSurvey(this);
		else
			return csSaleSurveyService.saveCsSaleSurvey(this);
		return this;
	}
	
	
	/**
	 * 更新市场调查对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsSaleSurveyService csSaleSurveyService = $.GetSpringBean("csSaleSurveyService");
		csSaleSurveyService.updateCsSaleSurvey$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsSaleSurveyService csSaleSurveyService = $.GetSpringBean("csSaleSurveyService");
		if($.equals($.config("logic_delete"),"true"))
			csSaleSurveyService.removeCsSaleSurveyById(this.getCsssId());
		else
			csSaleSurveyService.deleteCsSaleSurveyById(this.getCsssId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsSaleSurveyService csSaleSurveyService = $.GetSpringBean("csSaleSurveyService");
		return csSaleSurveyService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsssId(){
		return this.csssId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsssId$(){
		String strValue="";
		 strValue=$.str(this.getCsssId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsssId(Long csssId){
		this.csssId = csssId;
		this.setSeted(F.csssId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsssHost(){
		return this.csssHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsssHost$(){
		String strValue="";
		if(this.getCsssHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsssHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsssHost(Long csssHost){
		this.csssHost = csssHost;
		this.setSeted(F.csssHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csssHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsssHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsssHost()!=null){
 			srvHost = SrvHost.get(this.getCsssHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsssHost(), srvHost);
	 	return srvHost;
	}
	/*******************************调查主题**********************************/	
	/**
	* 调查主题 [非空]      
	**/
	public String getCsssSubject(){
		return this.csssSubject;
	}
	/**
	* 获取调查主题格式化(toString)
	**/
	public String getCsssSubject$(){
		String strValue="";
		 strValue=$.str(this.getCsssSubject());
	 	 return strValue;
	}
	/**
	* 调查主题 [非空]      
	**/
	public void setCsssSubject(String csssSubject){
		this.csssSubject = csssSubject;
		this.setSeted(F.csssSubject);
	}
	/*******************************所属活动**********************************/	
	/**
	* 所属活动 [非空] [CsSaleEvent]     
	**/
	public Long getCsssEvent(){
		return this.csssEvent;
	}
	/**
	* 获取所属活动格式化(toString)
	**/
	public String getCsssEvent$(){
		String strValue="";
		if(this.getCsssEvent()!=null){
				strValue+=$.str(CsSaleEvent.getKeyValue(this.getCsssEvent()));
		}			
	 	 return strValue;
	}
	/**
	* 所属活动 [非空] [CsSaleEvent]     
	**/
	public void setCsssEvent(Long csssEvent){
		this.csssEvent = csssEvent;
		this.setSeted(F.csssEvent);
	}
	/**
	* 获取关联对象[销售活动]
	**/	 			
 	public CsSaleEvent get$csssEvent(){
 		com.ccclubs.model.CsSaleEvent csSaleEvent = (com.ccclubs.model.CsSaleEvent) $.GetRequest("CsSaleEvent$"+this.getCsssEvent());
 		if(csSaleEvent!=null)
			return csSaleEvent;
		if(this.getCsssEvent()!=null){
 			csSaleEvent = CsSaleEvent.get(this.getCsssEvent());
 		}
 		$.SetRequest("CsSaleEvent$"+this.getCsssEvent(), csSaleEvent);
	 	return csSaleEvent;
	}
	/*******************************调查类型**********************************/	
	/**
	* 调查类型 [非空] [SrvProperty]  survey_type:市场调查类型  比如：综合性调查 区域品牌 竞争 价格 认知满意度 负面调查 行业调查 宏观政策 容量  
	**/
	public Long getCsssType(){
		return this.csssType;
	}
	/**
	* 获取调查类型格式化(toString)
	**/
	public String getCsssType$(){
		String strValue="";
		if(this.getCsssType()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCsssType()));
		}			
	 	 return strValue;
	}
	/**
	* 调查类型 [非空] [SrvProperty]  survey_type:市场调查类型  比如：综合性调查 区域品牌 竞争 价格 认知满意度 负面调查 行业调查 宏观政策 容量  
	**/
	public void setCsssType(Long csssType){
		this.csssType = csssType;
		this.setSeted(F.csssType);
	}
	/**
	* 获取关联对象[系统属性]
	**/	 			
 	public SrvProperty get$csssType(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCsssType());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCsssType()!=null){
 			srvProperty = SrvProperty.get(this.getCsssType());
 		}
 		$.SetRequest("SrvProperty$"+this.getCsssType(), srvProperty);
	 	return srvProperty;
	}
	/*******************************调查结论**********************************/	
	/**
	* 调查结论 [非空]      
	**/
	public String getCsssDetail(){
		return this.csssDetail;
	}
	/**
	* 获取调查结论格式化(toString)
	**/
	public String getCsssDetail$(){
		String strValue="";
		 strValue=$.str(this.getCsssDetail());
	 	 return strValue;
	}
	/**
	* 调查结论 [非空]      
	**/
	public void setCsssDetail(String csssDetail){
		this.csssDetail = csssDetail;
		this.setSeted(F.csssDetail);
	}
	/*******************************添加人**********************************/	
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsssUser(){
		return this.csssUser;
	}
	/**
	* 获取添加人格式化(toString)
	**/
	public String getCsssUser$(){
		String strValue="";
		if(this.getCsssUser()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsssUser()));
		}			
	 	 return strValue;
	}
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsssUser(Long csssUser){
		this.csssUser = csssUser;
		this.setSeted(F.csssUser);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csssUser(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsssUser());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsssUser()!=null){
 			srvUser = SrvUser.get(this.getCsssUser());
 		}
 		$.SetRequest("SrvUser$"+this.getCsssUser(), srvUser);
	 	return srvUser;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsssUpdateTime(){
		return this.csssUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsssUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsssUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsssUpdateTime(Date csssUpdateTime){
		this.csssUpdateTime = csssUpdateTime;
		this.setSeted(F.csssUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsssAddTime(){
		return this.csssAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsssAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsssAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsssAddTime(Date csssAddTime){
		this.csssAddTime = csssAddTime;
		this.setSeted(F.csssAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsssStatus(){
		return this.csssStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsssStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsssStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsssStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsssStatus(Short csssStatus){
		this.csssStatus = csssStatus;
		this.setSeted(F.csssStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsSaleSurvey.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsSaleSurvey.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsSaleSurvey.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsSaleSurvey.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsSaleSurvey.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsSaleSurvey.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsSaleSurvey.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsSaleSurvey.this);
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
		public M csssId(Object csssId){this.put("csssId", csssId);return this;};
	 	/** and csss_id is null */
 		public M csssIdNull(){if(this.get("csssIdNot")==null)this.put("csssIdNot", "");this.put("csssId", null);return this;};
 		/** not .... */
 		public M csssIdNot(){this.put("csssIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csssHost(Object csssHost){this.put("csssHost", csssHost);return this;};
	 	/** and csss_host is null */
 		public M csssHostNull(){if(this.get("csssHostNot")==null)this.put("csssHostNot", "");this.put("csssHost", null);return this;};
 		/** not .... */
 		public M csssHostNot(){this.put("csssHostNot", "not");return this;};
		/** 调查主题 [非空]       **/
		public M csssSubject(Object csssSubject){this.put("csssSubject", csssSubject);return this;};
	 	/** and csss_subject is null */
 		public M csssSubjectNull(){if(this.get("csssSubjectNot")==null)this.put("csssSubjectNot", "");this.put("csssSubject", null);return this;};
 		/** not .... */
 		public M csssSubjectNot(){this.put("csssSubjectNot", "not");return this;};
		/** 所属活动 [非空] [CsSaleEvent]      **/
		public M csssEvent(Object csssEvent){this.put("csssEvent", csssEvent);return this;};
	 	/** and csss_event is null */
 		public M csssEventNull(){if(this.get("csssEventNot")==null)this.put("csssEventNot", "");this.put("csssEvent", null);return this;};
 		/** not .... */
 		public M csssEventNot(){this.put("csssEventNot", "not");return this;};
		public M csssEvent$on(CsSaleEvent.M value){
			this.put("CsSaleEvent", value);
			this.put("csssEvent$on", value);
			return this;
		};	
		/** 调查类型 [非空] [SrvProperty]  survey_type:市场调查类型  比如：综合性调查 区域品牌 竞争 价格 认知满意度 负面调查 行业调查 宏观政策 容量   **/
		public M csssType(Object csssType){this.put("csssType", csssType);return this;};
	 	/** and csss_type is null */
 		public M csssTypeNull(){if(this.get("csssTypeNot")==null)this.put("csssTypeNot", "");this.put("csssType", null);return this;};
 		/** not .... */
 		public M csssTypeNot(){this.put("csssTypeNot", "not");return this;};
		public M csssType$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("csssType$on", value);
			return this;
		};	
		/** 调查结论 [非空]       **/
		public M csssDetail(Object csssDetail){this.put("csssDetail", csssDetail);return this;};
	 	/** and csss_detail is null */
 		public M csssDetailNull(){if(this.get("csssDetailNot")==null)this.put("csssDetailNot", "");this.put("csssDetail", null);return this;};
 		/** not .... */
 		public M csssDetailNot(){this.put("csssDetailNot", "not");return this;};
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csssUser(Object csssUser){this.put("csssUser", csssUser);return this;};
	 	/** and csss_user is null */
 		public M csssUserNull(){if(this.get("csssUserNot")==null)this.put("csssUserNot", "");this.put("csssUser", null);return this;};
 		/** not .... */
 		public M csssUserNot(){this.put("csssUserNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csssUpdateTime(Object csssUpdateTime){this.put("csssUpdateTime", csssUpdateTime);return this;};
	 	/** and csss_update_time is null */
 		public M csssUpdateTimeNull(){if(this.get("csssUpdateTimeNot")==null)this.put("csssUpdateTimeNot", "");this.put("csssUpdateTime", null);return this;};
 		/** not .... */
 		public M csssUpdateTimeNot(){this.put("csssUpdateTimeNot", "not");return this;};
 		/** and csss_update_time >= ? */
 		public M csssUpdateTimeStart(Object start){this.put("csssUpdateTimeStart", start);return this;};			
 		/** and csss_update_time <= ? */
 		public M csssUpdateTimeEnd(Object end){this.put("csssUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csssAddTime(Object csssAddTime){this.put("csssAddTime", csssAddTime);return this;};
	 	/** and csss_add_time is null */
 		public M csssAddTimeNull(){if(this.get("csssAddTimeNot")==null)this.put("csssAddTimeNot", "");this.put("csssAddTime", null);return this;};
 		/** not .... */
 		public M csssAddTimeNot(){this.put("csssAddTimeNot", "not");return this;};
 		/** and csss_add_time >= ? */
 		public M csssAddTimeStart(Object start){this.put("csssAddTimeStart", start);return this;};			
 		/** and csss_add_time <= ? */
 		public M csssAddTimeEnd(Object end){this.put("csssAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csssStatus(Object csssStatus){this.put("csssStatus", csssStatus);return this;};
	 	/** and csss_status is null */
 		public M csssStatusNull(){if(this.get("csssStatusNot")==null)this.put("csssStatusNot", "");this.put("csssStatus", null);return this;};
 		/** not .... */
 		public M csssStatusNot(){this.put("csssStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有市场调查 **/
		public @api List<CsSaleSurvey> list(Integer size){
			return getCsSaleSurveyList(this,size);
		}
		/** 获取市场调查分页 **/
		public @api Page<CsSaleSurvey> page(int page,int size){
			return getCsSaleSurveyPage(page, size , this);
		}
		/** 根据查询条件取市场调查 **/
		public @api CsSaleSurvey get(){
			return getCsSaleSurvey(this);
		}
		/** 获取市场调查数 **/
		public @api Long count(){
			return getCsSaleSurveyCount(this);
		}
		/** 获取市场调查表达式 **/
		public @api <T> T eval(String strEval){
			return getCsSaleSurveyEval(strEval,this);
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
			updateCsSaleSurvey(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csssId="csssId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csssHost="csssHost";
		/** 调查主题 [非空]       **/
		public final static @type(String.class) @like String csssSubject="csssSubject";
		/** 所属活动 [非空] [CsSaleEvent]      **/
		public final static @type(Long.class)  String csssEvent="csssEvent";
		/** 调查类型 [非空] [SrvProperty]  survey_type:市场调查类型  比如：综合性调查 区域品牌 竞争 价格 认知满意度 负面调查 行业调查 宏观政策 容量   **/
		public final static @type(Long.class)  String csssType="csssType";
		/** 调查结论 [非空]       **/
		public final static @type(String.class) @like String csssDetail="csssDetail";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csssUser="csssUser";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csssUpdateTime="csssUpdateTime";
	 	/** and csss_update_time >= ? */
 		public final static @type(Date.class) String csssUpdateTimeStart="csssUpdateTimeStart";
 		/** and csss_update_time <= ? */
 		public final static @type(Date.class) String csssUpdateTimeEnd="csssUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csssAddTime="csssAddTime";
	 	/** and csss_add_time >= ? */
 		public final static @type(Date.class) String csssAddTimeStart="csssAddTimeStart";
 		/** and csss_add_time <= ? */
 		public final static @type(Date.class) String csssAddTimeEnd="csssAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csssStatus="csssStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csssId="csss_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csssHost="csss_host";
		/** 调查主题 [非空]       **/
		public final static String csssSubject="csss_subject";
		/** 所属活动 [非空] [CsSaleEvent]      **/
		public final static String csssEvent="csss_event";
		/** 调查类型 [非空] [SrvProperty]  survey_type:市场调查类型  比如：综合性调查 区域品牌 竞争 价格 认知满意度 负面调查 行业调查 宏观政策 容量   **/
		public final static String csssType="csss_type";
		/** 调查结论 [非空]       **/
		public final static String csssDetail="csss_detail";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csssUser="csss_user";
		/** 修改时间 [非空]       **/
		public final static String csssUpdateTime="csss_update_time";
		/** 添加时间 [非空]       **/
		public final static String csssAddTime="csss_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csssStatus="csss_status";
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
				$.Set(name,CsSaleSurvey.getCsSaleSurvey(params));
			else
				$.Set(name,CsSaleSurvey.getCsSaleSurveyList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取市场调查数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsSaleSurvey) $.GetRequest("CsSaleSurvey$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsSaleSurvey.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsSaleSurvey.getCsSaleSurvey(params);
			else
				value = CsSaleSurvey.getCsSaleSurveyList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsSaleSurvey.Get($.add(CsSaleSurvey.F.csssId,param));
		else if(!$.empty(param.toString()))
			value = CsSaleSurvey.get(Long.valueOf(param.toString()));
		$.SetRequest("CsSaleSurvey$"+param.hashCode(), value);
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
	public void mergeSet(CsSaleSurvey old){
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