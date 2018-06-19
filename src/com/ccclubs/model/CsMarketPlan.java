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

@namespace("operate/marketplan")
public @caption("营销方案") @table("cs_market_plan") class CsMarketPlan implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csmp_id")   @primary  @note("  ") Long csmpId;// 主键 非空     
	private @caption("城市") @column("csmp_host")    @relate("$csmpHost") @RelateClass(SrvHost.class)  @note("  ") Long csmpHost;// 非空     
 	private SrvHost $csmpHost;//关联对象[运营城市]
	private @caption("方案主题") @column("csmp_name")    @note("  ") String csmpName;// 非空     
	private @caption("营销类型") @column("csmp_type")    @note(" 0:会员营销 1:产品营销  ") Short csmpType;// 非空 0:会员营销 1:产品营销     
	private @caption("营销手段") @column("csmp_method")    @relate("$csmpMethod") @RelateClass(SrvProperty.class)  @note(" market_method:营销手段  比如：体验营销、病毒营销、上门营销等等") Long csmpMethod;// 非空 market_method:营销手段  比如：体验营销、病毒营销、上门营销等等   
 	private SrvProperty $csmpMethod;//关联对象[系统属性]
	private @caption("策划人") @column("csmp_person")    @relate("$csmpPerson") @RelateClass(SrvUser.class)  @note("  ") Long csmpPerson;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csmpPerson;//关联对象[用户]
	private @caption("方案简介") @column("csmp_profile")  @hidden   @note("  ") String csmpProfile;//     
	private @caption("策划书") @column("csmp_plan_book")  @hidden   @note("  ") String csmpPlanBook;//     
	private @caption("思维导图") @column("csmp_mind_map")  @hidden   @note("  ") String csmpMindMap;//     
	private @caption("启用V指标") @column("csmp_v_index")    @note("  是否开启UV,PV,IP指标") Boolean csmpVIndex;// 非空  是否开启UV,PV,IP指标   
	//private @caption("指标") @RelateClass(CsMarketIndex.class) List<CsMarketIndex> indexes;//反向关联的营销指标列表
	//private @caption("执行维护") @RelateClass(CsMarketLog.class) List<CsMarketLog> logs;//反向关联的营销跟踪列表
	private @caption("开始时间") @column("csmp_start")    @note("  ") Date csmpStart;//     
	private @caption("结束时间") @column("csmp_finish")    @note("  ") Date csmpFinish;//     
	private @caption("修改时间") @column("csmp_update_time")    @note("  ") Date csmpUpdateTime;// 非空     
	private @caption("添加时间") @column("csmp_add_time")    @note("  ") Date csmpAddTime;// 非空     
	private @caption("备注信息") @column("csmp_remark")    @note("  ") String csmpRemark;//     
	private @caption("状态") @column("csmp_status")    @note(" 0:策划中 1:执行中 2:执行完成 3:已取消  ") Short csmpStatus;// 非空 0:策划中 1:执行中 2:执行完成 3:已取消     
	
	//默认构造函数
	public CsMarketPlan(){
	
	}
	
	//主键构造函数
	public CsMarketPlan(Long id){
		this.csmpId = id;
	}
	
	/**所有字段构造函数 CsMarketPlan(csmpHost,csmpName,csmpType,csmpMethod,csmpPerson,csmpProfile,csmpPlanBook,csmpMindMap,csmpVIndex,csmpStart,csmpFinish,csmpUpdateTime,csmpAddTime,csmpRemark,csmpStatus)
	 CsMarketPlan(
	 	$.getLong("csmpHost")//城市 [非空]
	 	,$.getString("csmpName")//方案主题 [非空]
	 	,$.getShort("csmpType")//营销类型 [非空]
	 	,$.getLong("csmpMethod")//营销手段 [非空]
	 	,$.getLong("csmpPerson")//策划人 [非空]
	 	,$.getString("csmpProfile")//方案简介
	 	,$.getString("csmpPlanBook")//策划书
	 	,$.getString("csmpMindMap")//思维导图
	 	,$.getBoolean("csmpVIndex")//启用V指标 [非空]
	 	,$.getDate("csmpStart")//开始时间
	 	,$.getDate("csmpFinish")//结束时间
	 	,$.getDate("csmpUpdateTime")//修改时间 [非空]
	 	,$.getDate("csmpAddTime")//添加时间 [非空]
	 	,$.getString("csmpRemark")//备注信息
	 	,$.getShort("csmpStatus")//状态 [非空]
	 )
	**/
	public CsMarketPlan(Long csmpHost,String csmpName,Short csmpType,Long csmpMethod,Long csmpPerson,String csmpProfile,String csmpPlanBook,String csmpMindMap,Boolean csmpVIndex,Date csmpStart,Date csmpFinish,Date csmpUpdateTime,Date csmpAddTime,String csmpRemark,Short csmpStatus){
		this.csmpHost=csmpHost;
		this.csmpName=csmpName;
		this.csmpType=csmpType;
		this.csmpMethod=csmpMethod;
		this.csmpPerson=csmpPerson;
		this.csmpProfile=csmpProfile;
		this.csmpPlanBook=csmpPlanBook;
		this.csmpMindMap=csmpMindMap;
		this.csmpVIndex=csmpVIndex;
		this.csmpStart=csmpStart;
		this.csmpFinish=csmpFinish;
		this.csmpUpdateTime=csmpUpdateTime;
		this.csmpAddTime=csmpAddTime;
		this.csmpRemark=csmpRemark;
		this.csmpStatus=csmpStatus;
	}
	
	//设置非空字段
	public CsMarketPlan setNotNull(Long csmpHost,String csmpName,Short csmpType,Long csmpMethod,Long csmpPerson,Boolean csmpVIndex,Date csmpUpdateTime,Date csmpAddTime,Short csmpStatus){
		this.csmpHost=csmpHost;
		this.csmpName=csmpName;
		this.csmpType=csmpType;
		this.csmpMethod=csmpMethod;
		this.csmpPerson=csmpPerson;
		this.csmpVIndex=csmpVIndex;
		this.csmpUpdateTime=csmpUpdateTime;
		this.csmpAddTime=csmpAddTime;
		this.csmpStatus=csmpStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsMarketPlan csmpId(Long csmpId){
		this.csmpId = csmpId;
		this.setSeted(F.csmpId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsMarketPlan csmpHost(Long csmpHost){
		this.csmpHost = csmpHost;
		this.setSeted(F.csmpHost);
		return this;
	}
	/** 方案主题 [非空]       **/
	public CsMarketPlan csmpName(String csmpName){
		this.csmpName = csmpName;
		this.setSeted(F.csmpName);
		return this;
	}
	/** 营销类型 [非空]   0:会员营销 1:产品营销     **/
	public CsMarketPlan csmpType(Short csmpType){
		this.csmpType = csmpType;
		this.setSeted(F.csmpType);
		return this;
	}
	/** 营销手段 [非空] [SrvProperty]  market_method:营销手段  比如：体验营销、病毒营销、上门营销等等   **/
	public CsMarketPlan csmpMethod(Long csmpMethod){
		this.csmpMethod = csmpMethod;
		this.setSeted(F.csmpMethod);
		return this;
	}
	/** 策划人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsMarketPlan csmpPerson(Long csmpPerson){
		this.csmpPerson = csmpPerson;
		this.setSeted(F.csmpPerson);
		return this;
	}
	/** 方案简介        **/
	public CsMarketPlan csmpProfile(String csmpProfile){
		this.csmpProfile = csmpProfile;
		this.setSeted(F.csmpProfile);
		return this;
	}
	/** 策划书        **/
	public CsMarketPlan csmpPlanBook(String csmpPlanBook){
		this.csmpPlanBook = csmpPlanBook;
		this.setSeted(F.csmpPlanBook);
		return this;
	}
	/** 思维导图        **/
	public CsMarketPlan csmpMindMap(String csmpMindMap){
		this.csmpMindMap = csmpMindMap;
		this.setSeted(F.csmpMindMap);
		return this;
	}
	/** 启用V指标 [非空]    是否开启UV,PV,IP指标   **/
	public CsMarketPlan csmpVIndex(Boolean csmpVIndex){
		this.csmpVIndex = csmpVIndex;
		this.setSeted(F.csmpVIndex);
		return this;
	}
	/** 开始时间        **/
	public CsMarketPlan csmpStart(Date csmpStart){
		this.csmpStart = csmpStart;
		this.setSeted(F.csmpStart);
		return this;
	}
	/** 结束时间        **/
	public CsMarketPlan csmpFinish(Date csmpFinish){
		this.csmpFinish = csmpFinish;
		this.setSeted(F.csmpFinish);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsMarketPlan csmpUpdateTime(Date csmpUpdateTime){
		this.csmpUpdateTime = csmpUpdateTime;
		this.setSeted(F.csmpUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsMarketPlan csmpAddTime(Date csmpAddTime){
		this.csmpAddTime = csmpAddTime;
		this.setSeted(F.csmpAddTime);
		return this;
	}
	/** 备注信息        **/
	public CsMarketPlan csmpRemark(String csmpRemark){
		this.csmpRemark = csmpRemark;
		this.setSeted(F.csmpRemark);
		return this;
	}
	/** 状态 [非空]   0:策划中 1:执行中 2:执行完成 3:已取消     **/
	public CsMarketPlan csmpStatus(Short csmpStatus){
		this.csmpStatus = csmpStatus;
		this.setSeted(F.csmpStatus);
		return this;
	}
	
	
	//克隆对象
	public CsMarketPlan clone(){
		CsMarketPlan clone = new CsMarketPlan();
		clone.csmpHost=this.csmpHost;
		clone.csmpName=this.csmpName;
		clone.csmpType=this.csmpType;
		clone.csmpMethod=this.csmpMethod;
		clone.csmpPerson=this.csmpPerson;
		clone.csmpVIndex=this.csmpVIndex;
		clone.csmpUpdateTime=this.csmpUpdateTime;
		clone.csmpAddTime=this.csmpAddTime;
		clone.csmpStatus=this.csmpStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取营销方案
	 * @param id
	 * @return
	 */
	public static @api CsMarketPlan get(Long id){		
		return getCsMarketPlanById(id);
	}
	/**
	 * 获取所有营销方案
	 * @return
	 */
	public static @api List<CsMarketPlan> list(Map params,Integer size){
		return getCsMarketPlanList(params,size);
	}
	/**
	 * 获取营销方案分页
	 * @return
	 */
	public static @api Page<CsMarketPlan> page(int page,int size,Map params){
		return getCsMarketPlanPage(page, size , params);
	}
	/**
	 * 根据查询条件取营销方案
	 * @param params
	 * @return
	 */
	public static @api CsMarketPlan Get(Map params){
		return getCsMarketPlan(params);
	}
	/**
	 * 获取营销方案数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsMarketPlanCount(params);
	}
	/**
	 * 获取营销方案数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsMarketPlanEval(eval,params);
	}
	
	/**
	 * 根据ID取营销方案
	 * @param id
	 * @return
	 */
	public static @api CsMarketPlan getCsMarketPlanById(Long id){		
		CsMarketPlan csMarketPlan = (CsMarketPlan) $.GetRequest("CsMarketPlan$"+id);
		if(csMarketPlan!=null)
			return csMarketPlan;
		com.ccclubs.service.admin.ICsMarketPlanService csMarketPlanService = $.GetSpringBean("csMarketPlanService");		
		return csMarketPlanService.getCsMarketPlanById(id);
	}
	
	
	/**
	 * 根据ID取营销方案的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsMarketPlan.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsMarketPlan csMarketPlan = get(id);
		if(csMarketPlan!=null){
			String strValue = csMarketPlan.getCsmpName$();
			if(!"CsmpName".equals("CsmpName"))
				strValue+="("+csMarketPlan.getCsmpName$()+")";
			MemCache.setValue(CsMarketPlan.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsmpName$();
		if(!"CsmpName".equals("CsmpName"))
			keyValue+="("+this.getCsmpName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有营销方案
	 * @return
	 */
	public static @api List<CsMarketPlan> getCsMarketPlanList(Map params,Integer size){
		com.ccclubs.service.admin.ICsMarketPlanService csMarketPlanService = $.GetSpringBean("csMarketPlanService");
		return csMarketPlanService.getCsMarketPlanList(params, size);
	}
	
	/**
	 * 获取营销方案分页
	 * @return
	 */
	public static @api Page<CsMarketPlan> getCsMarketPlanPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsMarketPlanService csMarketPlanService = $.GetSpringBean("csMarketPlanService");
		return csMarketPlanService.getCsMarketPlanPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取营销方案
	 * @param params
	 * @return
	 */
	public static @api CsMarketPlan getCsMarketPlan(Map params){
		com.ccclubs.service.admin.ICsMarketPlanService csMarketPlanService = $.GetSpringBean("csMarketPlanService");
		return csMarketPlanService.getCsMarketPlan(params);
	}
	
	/**
	 * 获取营销方案数
	 * @return
	 */
	public static @api Long getCsMarketPlanCount(Map params){
		com.ccclubs.service.admin.ICsMarketPlanService csMarketPlanService = $.GetSpringBean("csMarketPlanService");
		return csMarketPlanService.getCsMarketPlanCount(params);
	}
		
		
	/**
	 * 获取营销方案自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsMarketPlanEval(String eval,Map params){
		com.ccclubs.service.admin.ICsMarketPlanService csMarketPlanService = $.GetSpringBean("csMarketPlanService");
		return csMarketPlanService.getCsMarketPlanEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsMarketPlan(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsMarketPlanService csMarketPlanService = $.GetSpringBean("csMarketPlanService");
		csMarketPlanService.updateCsMarketPlanByConfirm(set, where);
	}
	
	
	/**
	 * 保存营销方案对象
	 * @param params
	 * @return
	 */
	public CsMarketPlan save(){
		com.ccclubs.service.admin.ICsMarketPlanService csMarketPlanService = $.GetSpringBean("csMarketPlanService");
		if(this.getCsmpId()!=null)
			csMarketPlanService.updateCsMarketPlan(this);
		else
			return csMarketPlanService.saveCsMarketPlan(this);
		return this;
	}
	
	
	/**
	 * 更新营销方案对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsMarketPlanService csMarketPlanService = $.GetSpringBean("csMarketPlanService");
		csMarketPlanService.updateCsMarketPlan$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsMarketPlanService csMarketPlanService = $.GetSpringBean("csMarketPlanService");
		if($.equals($.config("logic_delete"),"true"))
			csMarketPlanService.removeCsMarketPlanById(this.getCsmpId());
		else
			csMarketPlanService.deleteCsMarketPlanById(this.getCsmpId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsMarketPlanService csMarketPlanService = $.GetSpringBean("csMarketPlanService");
		return csMarketPlanService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsmpId(){
		return this.csmpId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsmpId$(){
		String strValue="";
		 strValue=$.str(this.getCsmpId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsmpId(Long csmpId){
		this.csmpId = csmpId;
		this.setSeted(F.csmpId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsmpHost(){
		return this.csmpHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsmpHost$(){
		String strValue="";
		if(this.getCsmpHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsmpHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsmpHost(Long csmpHost){
		this.csmpHost = csmpHost;
		this.setSeted(F.csmpHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csmpHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsmpHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsmpHost()!=null){
 			srvHost = SrvHost.get(this.getCsmpHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsmpHost(), srvHost);
	 	return srvHost;
	}
	/*******************************方案主题**********************************/	
	/**
	* 方案主题 [非空]      
	**/
	public String getCsmpName(){
		return this.csmpName;
	}
	/**
	* 获取方案主题格式化(toString)
	**/
	public String getCsmpName$(){
		String strValue="";
		 strValue=$.str(this.getCsmpName());
	 	 return strValue;
	}
	/**
	* 方案主题 [非空]      
	**/
	public void setCsmpName(String csmpName){
		this.csmpName = csmpName;
		this.setSeted(F.csmpName);
	}
	/*******************************营销类型**********************************/	
	/**
	* 营销类型 [非空]   0:会员营销 1:产品营销    
	**/
	public Short getCsmpType(){
		return this.csmpType;
	}
	/**
	* 获取营销类型格式化(toString)
	**/
	public String getCsmpType$(){
		String strValue="";
		 if($.equals($.str(this.getCsmpType()),"0"))
			strValue=$.str("会员营销");		 
		 if($.equals($.str(this.getCsmpType()),"1"))
			strValue=$.str("产品营销");		 
	 	 return strValue;
	}
	/**
	* 营销类型 [非空]   0:会员营销 1:产品营销    
	**/
	public void setCsmpType(Short csmpType){
		this.csmpType = csmpType;
		this.setSeted(F.csmpType);
	}
	/*******************************营销手段**********************************/	
	/**
	* 营销手段 [非空] [SrvProperty]  market_method:营销手段  比如：体验营销、病毒营销、上门营销等等  
	**/
	public Long getCsmpMethod(){
		return this.csmpMethod;
	}
	/**
	* 获取营销手段格式化(toString)
	**/
	public String getCsmpMethod$(){
		String strValue="";
		if(this.getCsmpMethod()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCsmpMethod()));
		}			
	 	 return strValue;
	}
	/**
	* 营销手段 [非空] [SrvProperty]  market_method:营销手段  比如：体验营销、病毒营销、上门营销等等  
	**/
	public void setCsmpMethod(Long csmpMethod){
		this.csmpMethod = csmpMethod;
		this.setSeted(F.csmpMethod);
	}
	/**
	* 获取关联对象[系统属性]
	**/	 			
 	public SrvProperty get$csmpMethod(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCsmpMethod());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCsmpMethod()!=null){
 			srvProperty = SrvProperty.get(this.getCsmpMethod());
 		}
 		$.SetRequest("SrvProperty$"+this.getCsmpMethod(), srvProperty);
	 	return srvProperty;
	}
	/*******************************策划人**********************************/	
	/**
	* 策划人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsmpPerson(){
		return this.csmpPerson;
	}
	/**
	* 获取策划人格式化(toString)
	**/
	public String getCsmpPerson$(){
		String strValue="";
		if(this.getCsmpPerson()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsmpPerson()));
		}			
	 	 return strValue;
	}
	/**
	* 策划人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsmpPerson(Long csmpPerson){
		this.csmpPerson = csmpPerson;
		this.setSeted(F.csmpPerson);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csmpPerson(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsmpPerson());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsmpPerson()!=null){
 			srvUser = SrvUser.get(this.getCsmpPerson());
 		}
 		$.SetRequest("SrvUser$"+this.getCsmpPerson(), srvUser);
	 	return srvUser;
	}
	/*******************************方案简介**********************************/	
	/**
	* 方案简介       
	**/
	public String getCsmpProfile(){
		return this.csmpProfile;
	}
	/**
	* 获取方案简介格式化(toString)
	**/
	public String getCsmpProfile$(){
		String strValue="";
		 strValue=$.str(this.getCsmpProfile());
	 	 return strValue;
	}
	/**
	* 方案简介       
	**/
	public void setCsmpProfile(String csmpProfile){
		this.csmpProfile = csmpProfile;
		this.setSeted(F.csmpProfile);
	}
	/*******************************策划书**********************************/	
	/**
	* 策划书       
	**/
	public String getCsmpPlanBook(){
		return this.csmpPlanBook;
	}
	/**
	* 获取策划书格式化(toString)
	**/
	public String getCsmpPlanBook$(){
		String strValue="";
		 strValue=$.str(this.getCsmpPlanBook());
	 	 return strValue;
	}
	/**
	* 策划书       
	**/
	public void setCsmpPlanBook(String csmpPlanBook){
		this.csmpPlanBook = csmpPlanBook;
		this.setSeted(F.csmpPlanBook);
	}
	/*******************************思维导图**********************************/	
	/**
	* 思维导图       
	**/
	public String getCsmpMindMap(){
		return this.csmpMindMap;
	}
	/**
	* 获取思维导图格式化(toString)
	**/
	public String getCsmpMindMap$(){
		String strValue="";
		 strValue=$.str(this.getCsmpMindMap());
	 	 return strValue;
	}
	/**
	* 思维导图       
	**/
	public void setCsmpMindMap(String csmpMindMap){
		this.csmpMindMap = csmpMindMap;
		this.setSeted(F.csmpMindMap);
	}
	/*******************************启用V指标**********************************/	
	/**
	* 启用V指标 [非空]    是否开启UV,PV,IP指标  
	**/
	public Boolean getCsmpVIndex(){
		return this.csmpVIndex;
	}
	/**
	* 获取启用V指标格式化(toString)
	**/
	public String getCsmpVIndex$(){
		String strValue="";
		  strValue=$.str((this.getCsmpVIndex()!=null && this.getCsmpVIndex())?"是":"否");			
	 	 return strValue;
	}
	/**
	* 启用V指标 [非空]    是否开启UV,PV,IP指标  
	**/
	public void setCsmpVIndex(Boolean csmpVIndex){
		this.csmpVIndex = csmpVIndex;
		this.setSeted(F.csmpVIndex);
	}
	/*******************************反向关联的营销指标列表**********************************/	
	/**
	* 获取与当前对象反向关联的营销指标列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsMarketIndex> getIndexes(){
		com.ccclubs.service.admin.ICsMarketIndexService csMarketIndexService = $.GetSpringBean("csMarketIndexService");
		Map params = new HashMap();
		params.put("csmiPlan",this.getCsmpId());
		return csMarketIndexService.getCsMarketIndexList(params,-1);
	}
	/**
	* 获取指标格式化(toString)
	**/
	public String getIndexes$(){
		return "[...]";
	}
	/*******************************反向关联的营销跟踪列表**********************************/	
	/**
	* 获取与当前对象反向关联的营销跟踪列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsMarketLog> getLogs(){
		com.ccclubs.service.admin.ICsMarketLogService csMarketLogService = $.GetSpringBean("csMarketLogService");
		Map params = new HashMap();
		params.put("csmlPlan",this.getCsmpId());
		return csMarketLogService.getCsMarketLogList(params,-1);
	}
	/**
	* 获取执行维护格式化(toString)
	**/
	public String getLogs$(){
		return "[...]";
	}
	/*******************************开始时间**********************************/	
	/**
	* 开始时间       
	**/
	public Date getCsmpStart(){
		return this.csmpStart;
	}
	/**
	* 获取开始时间格式化(toString)
	**/
	public String getCsmpStart$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmpStart(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 开始时间       
	**/
	public void setCsmpStart(Date csmpStart){
		this.csmpStart = csmpStart;
		this.setSeted(F.csmpStart);
	}
	/*******************************结束时间**********************************/	
	/**
	* 结束时间       
	**/
	public Date getCsmpFinish(){
		return this.csmpFinish;
	}
	/**
	* 获取结束时间格式化(toString)
	**/
	public String getCsmpFinish$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmpFinish(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 结束时间       
	**/
	public void setCsmpFinish(Date csmpFinish){
		this.csmpFinish = csmpFinish;
		this.setSeted(F.csmpFinish);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsmpUpdateTime(){
		return this.csmpUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsmpUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmpUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsmpUpdateTime(Date csmpUpdateTime){
		this.csmpUpdateTime = csmpUpdateTime;
		this.setSeted(F.csmpUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsmpAddTime(){
		return this.csmpAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsmpAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmpAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsmpAddTime(Date csmpAddTime){
		this.csmpAddTime = csmpAddTime;
		this.setSeted(F.csmpAddTime);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息       
	**/
	public String getCsmpRemark(){
		return this.csmpRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCsmpRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsmpRemark());
	 	 return strValue;
	}
	/**
	* 备注信息       
	**/
	public void setCsmpRemark(String csmpRemark){
		this.csmpRemark = csmpRemark;
		this.setSeted(F.csmpRemark);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:策划中 1:执行中 2:执行完成 3:已取消    
	**/
	public Short getCsmpStatus(){
		return this.csmpStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsmpStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsmpStatus()),"0"))
			strValue=$.str("策划中");		 
		 if($.equals($.str(this.getCsmpStatus()),"1"))
			strValue=$.str("执行中");		 
		 if($.equals($.str(this.getCsmpStatus()),"2"))
			strValue=$.str("执行完成");		 
		 if($.equals($.str(this.getCsmpStatus()),"3"))
			strValue=$.str("已取消");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:策划中 1:执行中 2:执行完成 3:已取消    
	**/
	public void setCsmpStatus(Short csmpStatus){
		this.csmpStatus = csmpStatus;
		this.setSeted(F.csmpStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsMarketPlan.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMarketPlan.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMarketPlan.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsMarketPlan.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsMarketPlan.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMarketPlan.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMarketPlan.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsMarketPlan.this);
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
		public M csmpId(Object csmpId){this.put("csmpId", csmpId);return this;};
	 	/** and csmp_id is null */
 		public M csmpIdNull(){if(this.get("csmpIdNot")==null)this.put("csmpIdNot", "");this.put("csmpId", null);return this;};
 		/** not .... */
 		public M csmpIdNot(){this.put("csmpIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csmpHost(Object csmpHost){this.put("csmpHost", csmpHost);return this;};
	 	/** and csmp_host is null */
 		public M csmpHostNull(){if(this.get("csmpHostNot")==null)this.put("csmpHostNot", "");this.put("csmpHost", null);return this;};
 		/** not .... */
 		public M csmpHostNot(){this.put("csmpHostNot", "not");return this;};
		/** 方案主题 [非空]       **/
		public M csmpName(Object csmpName){this.put("csmpName", csmpName);return this;};
	 	/** and csmp_name is null */
 		public M csmpNameNull(){if(this.get("csmpNameNot")==null)this.put("csmpNameNot", "");this.put("csmpName", null);return this;};
 		/** not .... */
 		public M csmpNameNot(){this.put("csmpNameNot", "not");return this;};
		/** 营销类型 [非空]   0:会员营销 1:产品营销     **/
		public M csmpType(Object csmpType){this.put("csmpType", csmpType);return this;};
	 	/** and csmp_type is null */
 		public M csmpTypeNull(){if(this.get("csmpTypeNot")==null)this.put("csmpTypeNot", "");this.put("csmpType", null);return this;};
 		/** not .... */
 		public M csmpTypeNot(){this.put("csmpTypeNot", "not");return this;};
		/** 营销手段 [非空] [SrvProperty]  market_method:营销手段  比如：体验营销、病毒营销、上门营销等等   **/
		public M csmpMethod(Object csmpMethod){this.put("csmpMethod", csmpMethod);return this;};
	 	/** and csmp_method is null */
 		public M csmpMethodNull(){if(this.get("csmpMethodNot")==null)this.put("csmpMethodNot", "");this.put("csmpMethod", null);return this;};
 		/** not .... */
 		public M csmpMethodNot(){this.put("csmpMethodNot", "not");return this;};
		public M csmpMethod$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("csmpMethod$on", value);
			return this;
		};	
		/** 策划人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csmpPerson(Object csmpPerson){this.put("csmpPerson", csmpPerson);return this;};
	 	/** and csmp_person is null */
 		public M csmpPersonNull(){if(this.get("csmpPersonNot")==null)this.put("csmpPersonNot", "");this.put("csmpPerson", null);return this;};
 		/** not .... */
 		public M csmpPersonNot(){this.put("csmpPersonNot", "not");return this;};
		/** 方案简介        **/
		public M csmpProfile(Object csmpProfile){this.put("csmpProfile", csmpProfile);return this;};
	 	/** and csmp_profile is null */
 		public M csmpProfileNull(){if(this.get("csmpProfileNot")==null)this.put("csmpProfileNot", "");this.put("csmpProfile", null);return this;};
 		/** not .... */
 		public M csmpProfileNot(){this.put("csmpProfileNot", "not");return this;};
		/** 策划书        **/
		public M csmpPlanBook(Object csmpPlanBook){this.put("csmpPlanBook", csmpPlanBook);return this;};
	 	/** and csmp_plan_book is null */
 		public M csmpPlanBookNull(){if(this.get("csmpPlanBookNot")==null)this.put("csmpPlanBookNot", "");this.put("csmpPlanBook", null);return this;};
 		/** not .... */
 		public M csmpPlanBookNot(){this.put("csmpPlanBookNot", "not");return this;};
		/** 思维导图        **/
		public M csmpMindMap(Object csmpMindMap){this.put("csmpMindMap", csmpMindMap);return this;};
	 	/** and csmp_mind_map is null */
 		public M csmpMindMapNull(){if(this.get("csmpMindMapNot")==null)this.put("csmpMindMapNot", "");this.put("csmpMindMap", null);return this;};
 		/** not .... */
 		public M csmpMindMapNot(){this.put("csmpMindMapNot", "not");return this;};
		/** 启用V指标 [非空]    是否开启UV,PV,IP指标   **/
		public M csmpVIndex(Object csmpVIndex){this.put("csmpVIndex", csmpVIndex);return this;};
	 	/** and csmp_v_index is null */
 		public M csmpVIndexNull(){if(this.get("csmpVIndexNot")==null)this.put("csmpVIndexNot", "");this.put("csmpVIndex", null);return this;};
 		/** not .... */
 		public M csmpVIndexNot(){this.put("csmpVIndexNot", "not");return this;};
		/** 开始时间        **/
		public M csmpStart(Object csmpStart){this.put("csmpStart", csmpStart);return this;};
	 	/** and csmp_start is null */
 		public M csmpStartNull(){if(this.get("csmpStartNot")==null)this.put("csmpStartNot", "");this.put("csmpStart", null);return this;};
 		/** not .... */
 		public M csmpStartNot(){this.put("csmpStartNot", "not");return this;};
 		/** and csmp_start >= ? */
 		public M csmpStartStart(Object start){this.put("csmpStartStart", start);return this;};			
 		/** and csmp_start <= ? */
 		public M csmpStartEnd(Object end){this.put("csmpStartEnd", end);return this;};
		/** 结束时间        **/
		public M csmpFinish(Object csmpFinish){this.put("csmpFinish", csmpFinish);return this;};
	 	/** and csmp_finish is null */
 		public M csmpFinishNull(){if(this.get("csmpFinishNot")==null)this.put("csmpFinishNot", "");this.put("csmpFinish", null);return this;};
 		/** not .... */
 		public M csmpFinishNot(){this.put("csmpFinishNot", "not");return this;};
 		/** and csmp_finish >= ? */
 		public M csmpFinishStart(Object start){this.put("csmpFinishStart", start);return this;};			
 		/** and csmp_finish <= ? */
 		public M csmpFinishEnd(Object end){this.put("csmpFinishEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M csmpUpdateTime(Object csmpUpdateTime){this.put("csmpUpdateTime", csmpUpdateTime);return this;};
	 	/** and csmp_update_time is null */
 		public M csmpUpdateTimeNull(){if(this.get("csmpUpdateTimeNot")==null)this.put("csmpUpdateTimeNot", "");this.put("csmpUpdateTime", null);return this;};
 		/** not .... */
 		public M csmpUpdateTimeNot(){this.put("csmpUpdateTimeNot", "not");return this;};
 		/** and csmp_update_time >= ? */
 		public M csmpUpdateTimeStart(Object start){this.put("csmpUpdateTimeStart", start);return this;};			
 		/** and csmp_update_time <= ? */
 		public M csmpUpdateTimeEnd(Object end){this.put("csmpUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csmpAddTime(Object csmpAddTime){this.put("csmpAddTime", csmpAddTime);return this;};
	 	/** and csmp_add_time is null */
 		public M csmpAddTimeNull(){if(this.get("csmpAddTimeNot")==null)this.put("csmpAddTimeNot", "");this.put("csmpAddTime", null);return this;};
 		/** not .... */
 		public M csmpAddTimeNot(){this.put("csmpAddTimeNot", "not");return this;};
 		/** and csmp_add_time >= ? */
 		public M csmpAddTimeStart(Object start){this.put("csmpAddTimeStart", start);return this;};			
 		/** and csmp_add_time <= ? */
 		public M csmpAddTimeEnd(Object end){this.put("csmpAddTimeEnd", end);return this;};
		/** 备注信息        **/
		public M csmpRemark(Object csmpRemark){this.put("csmpRemark", csmpRemark);return this;};
	 	/** and csmp_remark is null */
 		public M csmpRemarkNull(){if(this.get("csmpRemarkNot")==null)this.put("csmpRemarkNot", "");this.put("csmpRemark", null);return this;};
 		/** not .... */
 		public M csmpRemarkNot(){this.put("csmpRemarkNot", "not");return this;};
		/** 状态 [非空]   0:策划中 1:执行中 2:执行完成 3:已取消     **/
		public M csmpStatus(Object csmpStatus){this.put("csmpStatus", csmpStatus);return this;};
	 	/** and csmp_status is null */
 		public M csmpStatusNull(){if(this.get("csmpStatusNot")==null)this.put("csmpStatusNot", "");this.put("csmpStatus", null);return this;};
 		/** not .... */
 		public M csmpStatusNot(){this.put("csmpStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有营销方案 **/
		public @api List<CsMarketPlan> list(Integer size){
			return getCsMarketPlanList(this,size);
		}
		/** 获取营销方案分页 **/
		public @api Page<CsMarketPlan> page(int page,int size){
			return getCsMarketPlanPage(page, size , this);
		}
		/** 根据查询条件取营销方案 **/
		public @api CsMarketPlan get(){
			return getCsMarketPlan(this);
		}
		/** 获取营销方案数 **/
		public @api Long count(){
			return getCsMarketPlanCount(this);
		}
		/** 获取营销方案表达式 **/
		public @api <T> T eval(String strEval){
			return getCsMarketPlanEval(strEval,this);
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
			updateCsMarketPlan(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csmpId="csmpId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csmpHost="csmpHost";
		/** 方案主题 [非空]       **/
		public final static @type(String.class) @like String csmpName="csmpName";
		/** 营销类型 [非空]   0:会员营销 1:产品营销     **/
		public final static @type(Short.class)  String csmpType="csmpType";
		/** 营销手段 [非空] [SrvProperty]  market_method:营销手段  比如：体验营销、病毒营销、上门营销等等   **/
		public final static @type(Long.class)  String csmpMethod="csmpMethod";
		/** 策划人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csmpPerson="csmpPerson";
		/** 方案简介        **/
		public final static @type(String.class) @like String csmpProfile="csmpProfile";
		/** 策划书        **/
		public final static @type(String.class)  String csmpPlanBook="csmpPlanBook";
		/** 思维导图        **/
		public final static @type(String.class)  String csmpMindMap="csmpMindMap";
		/** 启用V指标 [非空]    是否开启UV,PV,IP指标   **/
		public final static @type(Boolean.class)  String csmpVIndex="csmpVIndex";
		/** 开始时间        **/
		public final static @type(Date.class)  String csmpStart="csmpStart";
	 	/** and csmp_start >= ? */
 		public final static @type(Date.class) String csmpStartStart="csmpStartStart";
 		/** and csmp_start <= ? */
 		public final static @type(Date.class) String csmpStartEnd="csmpStartEnd";
		/** 结束时间        **/
		public final static @type(Date.class)  String csmpFinish="csmpFinish";
	 	/** and csmp_finish >= ? */
 		public final static @type(Date.class) String csmpFinishStart="csmpFinishStart";
 		/** and csmp_finish <= ? */
 		public final static @type(Date.class) String csmpFinishEnd="csmpFinishEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csmpUpdateTime="csmpUpdateTime";
	 	/** and csmp_update_time >= ? */
 		public final static @type(Date.class) String csmpUpdateTimeStart="csmpUpdateTimeStart";
 		/** and csmp_update_time <= ? */
 		public final static @type(Date.class) String csmpUpdateTimeEnd="csmpUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csmpAddTime="csmpAddTime";
	 	/** and csmp_add_time >= ? */
 		public final static @type(Date.class) String csmpAddTimeStart="csmpAddTimeStart";
 		/** and csmp_add_time <= ? */
 		public final static @type(Date.class) String csmpAddTimeEnd="csmpAddTimeEnd";
		/** 备注信息        **/
		public final static @type(String.class) @like String csmpRemark="csmpRemark";
		/** 状态 [非空]   0:策划中 1:执行中 2:执行完成 3:已取消     **/
		public final static @type(Short.class)  String csmpStatus="csmpStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csmpId="csmp_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csmpHost="csmp_host";
		/** 方案主题 [非空]       **/
		public final static String csmpName="csmp_name";
		/** 营销类型 [非空]   0:会员营销 1:产品营销     **/
		public final static String csmpType="csmp_type";
		/** 营销手段 [非空] [SrvProperty]  market_method:营销手段  比如：体验营销、病毒营销、上门营销等等   **/
		public final static String csmpMethod="csmp_method";
		/** 策划人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csmpPerson="csmp_person";
		/** 方案简介        **/
		public final static String csmpProfile="csmp_profile";
		/** 策划书        **/
		public final static String csmpPlanBook="csmp_plan_book";
		/** 思维导图        **/
		public final static String csmpMindMap="csmp_mind_map";
		/** 启用V指标 [非空]    是否开启UV,PV,IP指标   **/
		public final static String csmpVIndex="csmp_v_index";
		/** 开始时间        **/
		public final static String csmpStart="csmp_start";
		/** 结束时间        **/
		public final static String csmpFinish="csmp_finish";
		/** 修改时间 [非空]       **/
		public final static String csmpUpdateTime="csmp_update_time";
		/** 添加时间 [非空]       **/
		public final static String csmpAddTime="csmp_add_time";
		/** 备注信息        **/
		public final static String csmpRemark="csmp_remark";
		/** 状态 [非空]   0:策划中 1:执行中 2:执行完成 3:已取消     **/
		public final static String csmpStatus="csmp_status";
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
				$.Set(name,CsMarketPlan.getCsMarketPlan(params));
			else
				$.Set(name,CsMarketPlan.getCsMarketPlanList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取营销方案数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsMarketPlan) $.GetRequest("CsMarketPlan$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsMarketPlan.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsMarketPlan.getCsMarketPlan(params);
			else
				value = CsMarketPlan.getCsMarketPlanList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsMarketPlan.Get($.add(CsMarketPlan.F.csmpId,param));
		else if(!$.empty(param.toString()))
			value = CsMarketPlan.get(Long.valueOf(param.toString()));
		$.SetRequest("CsMarketPlan$"+param.hashCode(), value);
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
	public void mergeSet(CsMarketPlan old){
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