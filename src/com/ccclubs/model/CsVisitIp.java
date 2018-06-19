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

@namespace("extension/visitip")
public @caption("独立IP访问") @table("cs_visit_ip") class CsVisitIp implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csvi_id")   @primary  @note("  ") Long csviId;// 主键 非空     
	private @caption("城市") @column("csvi_host")    @relate("$csviHost") @RelateClass(SrvHost.class)  @note("  ") Long csviHost;// 非空     
 	private SrvHost $csviHost;//关联对象[运营城市]
	private @caption("IP地址") @column("csvi_address")    @note("  ") String csviAddress;// 非空     
	private @caption("所属渠道") @column("csvi_channel")    @relate("$csviChannel") @RelateClass(CsChannel.class)  @note("  ") Long csviChannel;//     
 	private CsChannel $csviChannel;//关联对象[访问渠道]
	private @caption("所属策划") @column("csvi_plan")    @relate("$csviPlan") @RelateClass(CsMarketPlan.class)  @note("  ") Long csviPlan;//     
 	private CsMarketPlan $csviPlan;//关联对象[营销方案]
	private @caption("所属活动") @column("csvi_activity")    @relate("$csviActivity") @RelateClass(CsActivity.class)  @note("  ") Long csviActivity;//     
 	private CsActivity $csviActivity;//关联对象[营销活动]
	private @caption("源地址") @column("csvi_url_from")    @note("  ") String csviUrlFrom;//     
	private @caption("目标地址") @column("csvi_url_to")    @note("  ") String csviUrlTo;//     
	private @caption("访问时间") @column("csvi_add_time")    @note("  ") Date csviAddTime;// 非空     
	
	//默认构造函数
	public CsVisitIp(){
	
	}
	
	//主键构造函数
	public CsVisitIp(Long id){
		this.csviId = id;
	}
	
	/**所有字段构造函数 CsVisitIp(csviHost,csviAddress,csviChannel,csviPlan,csviActivity,csviUrlFrom,csviUrlTo,csviAddTime)
	 CsVisitIp(
	 	$.getLong("csviHost")//城市 [非空]
	 	,$.getString("csviAddress")//IP地址 [非空]
	 	,$.getLong("csviChannel")//所属渠道
	 	,$.getLong("csviPlan")//所属策划
	 	,$.getLong("csviActivity")//所属活动
	 	,$.getString("csviUrlFrom")//源地址
	 	,$.getString("csviUrlTo")//目标地址
	 	,$.getDate("csviAddTime")//访问时间 [非空]
	 )
	**/
	public CsVisitIp(Long csviHost,String csviAddress,Long csviChannel,Long csviPlan,Long csviActivity,String csviUrlFrom,String csviUrlTo,Date csviAddTime){
		this.csviHost=csviHost;
		this.csviAddress=csviAddress;
		this.csviChannel=csviChannel;
		this.csviPlan=csviPlan;
		this.csviActivity=csviActivity;
		this.csviUrlFrom=csviUrlFrom;
		this.csviUrlTo=csviUrlTo;
		this.csviAddTime=csviAddTime;
	}
	
	//设置非空字段
	public CsVisitIp setNotNull(Long csviHost,String csviAddress,Date csviAddTime){
		this.csviHost=csviHost;
		this.csviAddress=csviAddress;
		this.csviAddTime=csviAddTime;
		return this;
	}
	/** 编号 [非空]       **/
	public CsVisitIp csviId(Long csviId){
		this.csviId = csviId;
		this.setSeted(F.csviId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsVisitIp csviHost(Long csviHost){
		this.csviHost = csviHost;
		this.setSeted(F.csviHost);
		return this;
	}
	/** IP地址 [非空]       **/
	public CsVisitIp csviAddress(String csviAddress){
		this.csviAddress = csviAddress;
		this.setSeted(F.csviAddress);
		return this;
	}
	/** 所属渠道  [CsChannel]      **/
	public CsVisitIp csviChannel(Long csviChannel){
		this.csviChannel = csviChannel;
		this.setSeted(F.csviChannel);
		return this;
	}
	/** 所属策划  [CsMarketPlan]      **/
	public CsVisitIp csviPlan(Long csviPlan){
		this.csviPlan = csviPlan;
		this.setSeted(F.csviPlan);
		return this;
	}
	/** 所属活动  [CsActivity]      **/
	public CsVisitIp csviActivity(Long csviActivity){
		this.csviActivity = csviActivity;
		this.setSeted(F.csviActivity);
		return this;
	}
	/** 源地址        **/
	public CsVisitIp csviUrlFrom(String csviUrlFrom){
		this.csviUrlFrom = csviUrlFrom;
		this.setSeted(F.csviUrlFrom);
		return this;
	}
	/** 目标地址        **/
	public CsVisitIp csviUrlTo(String csviUrlTo){
		this.csviUrlTo = csviUrlTo;
		this.setSeted(F.csviUrlTo);
		return this;
	}
	/** 访问时间 [非空]       **/
	public CsVisitIp csviAddTime(Date csviAddTime){
		this.csviAddTime = csviAddTime;
		this.setSeted(F.csviAddTime);
		return this;
	}
	
	
	//克隆对象
	public CsVisitIp clone(){
		CsVisitIp clone = new CsVisitIp();
		clone.csviHost=this.csviHost;
		clone.csviAddress=this.csviAddress;
		clone.csviAddTime=this.csviAddTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取独立IP访问
	 * @param id
	 * @return
	 */
	public static @api CsVisitIp get(Long id){		
		return getCsVisitIpById(id);
	}
	/**
	 * 获取所有独立IP访问
	 * @return
	 */
	public static @api List<CsVisitIp> list(Map params,Integer size){
		return getCsVisitIpList(params,size);
	}
	/**
	 * 获取独立IP访问分页
	 * @return
	 */
	public static @api Page<CsVisitIp> page(int page,int size,Map params){
		return getCsVisitIpPage(page, size , params);
	}
	/**
	 * 根据查询条件取独立IP访问
	 * @param params
	 * @return
	 */
	public static @api CsVisitIp Get(Map params){
		return getCsVisitIp(params);
	}
	/**
	 * 获取独立IP访问数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsVisitIpCount(params);
	}
	/**
	 * 获取独立IP访问数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsVisitIpEval(eval,params);
	}
	
	/**
	 * 根据ID取独立IP访问
	 * @param id
	 * @return
	 */
	public static @api CsVisitIp getCsVisitIpById(Long id){		
		CsVisitIp csVisitIp = (CsVisitIp) $.GetRequest("CsVisitIp$"+id);
		if(csVisitIp!=null)
			return csVisitIp;
		com.ccclubs.service.admin.ICsVisitIpService csVisitIpService = $.GetSpringBean("csVisitIpService");		
		return csVisitIpService.getCsVisitIpById(id);
	}
	
	
	/**
	 * 根据ID取独立IP访问的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsVisitIp.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsVisitIp csVisitIp = get(id);
		if(csVisitIp!=null){
			String strValue = csVisitIp.getCsviAddress$();
			if(!"CsviAddress".equals("CsviAddress"))
				strValue+="("+csVisitIp.getCsviAddress$()+")";
			MemCache.setValue(CsVisitIp.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsviAddress$();
		if(!"CsviAddress".equals("CsviAddress"))
			keyValue+="("+this.getCsviAddress$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有独立IP访问
	 * @return
	 */
	public static @api List<CsVisitIp> getCsVisitIpList(Map params,Integer size){
		com.ccclubs.service.admin.ICsVisitIpService csVisitIpService = $.GetSpringBean("csVisitIpService");
		return csVisitIpService.getCsVisitIpList(params, size);
	}
	
	/**
	 * 获取独立IP访问分页
	 * @return
	 */
	public static @api Page<CsVisitIp> getCsVisitIpPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsVisitIpService csVisitIpService = $.GetSpringBean("csVisitIpService");
		return csVisitIpService.getCsVisitIpPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取独立IP访问
	 * @param params
	 * @return
	 */
	public static @api CsVisitIp getCsVisitIp(Map params){
		com.ccclubs.service.admin.ICsVisitIpService csVisitIpService = $.GetSpringBean("csVisitIpService");
		return csVisitIpService.getCsVisitIp(params);
	}
	
	/**
	 * 获取独立IP访问数
	 * @return
	 */
	public static @api Long getCsVisitIpCount(Map params){
		com.ccclubs.service.admin.ICsVisitIpService csVisitIpService = $.GetSpringBean("csVisitIpService");
		return csVisitIpService.getCsVisitIpCount(params);
	}
		
		
	/**
	 * 获取独立IP访问自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsVisitIpEval(String eval,Map params){
		com.ccclubs.service.admin.ICsVisitIpService csVisitIpService = $.GetSpringBean("csVisitIpService");
		return csVisitIpService.getCsVisitIpEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsVisitIp(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsVisitIpService csVisitIpService = $.GetSpringBean("csVisitIpService");
		csVisitIpService.updateCsVisitIpByConfirm(set, where);
	}
	
	
	/**
	 * 保存独立IP访问对象
	 * @param params
	 * @return
	 */
	public CsVisitIp save(){
		com.ccclubs.service.admin.ICsVisitIpService csVisitIpService = $.GetSpringBean("csVisitIpService");
		if(this.getCsviId()!=null)
			csVisitIpService.updateCsVisitIp(this);
		else
			return csVisitIpService.saveCsVisitIp(this);
		return this;
	}
	
	
	/**
	 * 更新独立IP访问对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsVisitIpService csVisitIpService = $.GetSpringBean("csVisitIpService");
		csVisitIpService.updateCsVisitIp$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsVisitIpService csVisitIpService = $.GetSpringBean("csVisitIpService");
			csVisitIpService.deleteCsVisitIpById(this.getCsviId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsVisitIpService csVisitIpService = $.GetSpringBean("csVisitIpService");
		return csVisitIpService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsviId(){
		return this.csviId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsviId$(){
		String strValue="";
		 strValue=$.str(this.getCsviId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsviId(Long csviId){
		this.csviId = csviId;
		this.setSeted(F.csviId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsviHost(){
		return this.csviHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsviHost$(){
		String strValue="";
		if(this.getCsviHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsviHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsviHost(Long csviHost){
		this.csviHost = csviHost;
		this.setSeted(F.csviHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csviHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsviHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsviHost()!=null){
 			srvHost = SrvHost.get(this.getCsviHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsviHost(), srvHost);
	 	return srvHost;
	}
	/*******************************IP地址**********************************/	
	/**
	* IP地址 [非空]      
	**/
	public String getCsviAddress(){
		return this.csviAddress;
	}
	/**
	* 获取IP地址格式化(toString)
	**/
	public String getCsviAddress$(){
		String strValue="";
		 strValue=$.str(this.getCsviAddress());
	 	 return strValue;
	}
	/**
	* IP地址 [非空]      
	**/
	public void setCsviAddress(String csviAddress){
		this.csviAddress = csviAddress;
		this.setSeted(F.csviAddress);
	}
	/*******************************所属渠道**********************************/	
	/**
	* 所属渠道  [CsChannel]     
	**/
	public Long getCsviChannel(){
		return this.csviChannel;
	}
	/**
	* 获取所属渠道格式化(toString)
	**/
	public String getCsviChannel$(){
		String strValue="";
		if(this.getCsviChannel()!=null){
				strValue+=$.str(CsChannel.getKeyValue(this.getCsviChannel()));
		}			
	 	 return strValue;
	}
	/**
	* 所属渠道  [CsChannel]     
	**/
	public void setCsviChannel(Long csviChannel){
		this.csviChannel = csviChannel;
		this.setSeted(F.csviChannel);
	}
	/**
	* 获取关联对象[访问渠道]
	**/	 			
 	public CsChannel get$csviChannel(){
 		com.ccclubs.model.CsChannel csChannel = (com.ccclubs.model.CsChannel) $.GetRequest("CsChannel$"+this.getCsviChannel());
 		if(csChannel!=null)
			return csChannel;
		if(this.getCsviChannel()!=null){
 			csChannel = CsChannel.get(this.getCsviChannel());
 		}
 		$.SetRequest("CsChannel$"+this.getCsviChannel(), csChannel);
	 	return csChannel;
	}
	/*******************************所属策划**********************************/	
	/**
	* 所属策划  [CsMarketPlan]     
	**/
	public Long getCsviPlan(){
		return this.csviPlan;
	}
	/**
	* 获取所属策划格式化(toString)
	**/
	public String getCsviPlan$(){
		String strValue="";
		if(this.getCsviPlan()!=null){
				strValue+=$.str(CsMarketPlan.getKeyValue(this.getCsviPlan()));
		}			
	 	 return strValue;
	}
	/**
	* 所属策划  [CsMarketPlan]     
	**/
	public void setCsviPlan(Long csviPlan){
		this.csviPlan = csviPlan;
		this.setSeted(F.csviPlan);
	}
	/**
	* 获取关联对象[营销方案]
	**/	 			
 	public CsMarketPlan get$csviPlan(){
 		com.ccclubs.model.CsMarketPlan csMarketPlan = (com.ccclubs.model.CsMarketPlan) $.GetRequest("CsMarketPlan$"+this.getCsviPlan());
 		if(csMarketPlan!=null)
			return csMarketPlan;
		if(this.getCsviPlan()!=null){
 			csMarketPlan = CsMarketPlan.get(this.getCsviPlan());
 		}
 		$.SetRequest("CsMarketPlan$"+this.getCsviPlan(), csMarketPlan);
	 	return csMarketPlan;
	}
	/*******************************所属活动**********************************/	
	/**
	* 所属活动  [CsActivity]     
	**/
	public Long getCsviActivity(){
		return this.csviActivity;
	}
	/**
	* 获取所属活动格式化(toString)
	**/
	public String getCsviActivity$(){
		String strValue="";
		if(this.getCsviActivity()!=null){
				strValue+=$.str(CsActivity.getKeyValue(this.getCsviActivity()));
		}			
	 	 return strValue;
	}
	/**
	* 所属活动  [CsActivity]     
	**/
	public void setCsviActivity(Long csviActivity){
		this.csviActivity = csviActivity;
		this.setSeted(F.csviActivity);
	}
	/**
	* 获取关联对象[营销活动]
	**/	 			
 	public CsActivity get$csviActivity(){
 		com.ccclubs.model.CsActivity csActivity = (com.ccclubs.model.CsActivity) $.GetRequest("CsActivity$"+this.getCsviActivity());
 		if(csActivity!=null)
			return csActivity;
		if(this.getCsviActivity()!=null){
 			csActivity = CsActivity.get(this.getCsviActivity());
 		}
 		$.SetRequest("CsActivity$"+this.getCsviActivity(), csActivity);
	 	return csActivity;
	}
	/*******************************源地址**********************************/	
	/**
	* 源地址       
	**/
	public String getCsviUrlFrom(){
		return this.csviUrlFrom;
	}
	/**
	* 获取源地址格式化(toString)
	**/
	public String getCsviUrlFrom$(){
		String strValue="";
		 strValue=$.str(this.getCsviUrlFrom());
	 	 return strValue;
	}
	/**
	* 源地址       
	**/
	public void setCsviUrlFrom(String csviUrlFrom){
		this.csviUrlFrom = csviUrlFrom;
		this.setSeted(F.csviUrlFrom);
	}
	/*******************************目标地址**********************************/	
	/**
	* 目标地址       
	**/
	public String getCsviUrlTo(){
		return this.csviUrlTo;
	}
	/**
	* 获取目标地址格式化(toString)
	**/
	public String getCsviUrlTo$(){
		String strValue="";
		 strValue=$.str(this.getCsviUrlTo());
	 	 return strValue;
	}
	/**
	* 目标地址       
	**/
	public void setCsviUrlTo(String csviUrlTo){
		this.csviUrlTo = csviUrlTo;
		this.setSeted(F.csviUrlTo);
	}
	/*******************************访问时间**********************************/	
	/**
	* 访问时间 [非空]      
	**/
	public Date getCsviAddTime(){
		return this.csviAddTime;
	}
	/**
	* 获取访问时间格式化(toString)
	**/
	public String getCsviAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsviAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 访问时间 [非空]      
	**/
	public void setCsviAddTime(Date csviAddTime){
		this.csviAddTime = csviAddTime;
		this.setSeted(F.csviAddTime);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsVisitIp.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsVisitIp.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsVisitIp.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsVisitIp.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsVisitIp.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsVisitIp.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsVisitIp.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsVisitIp.this);
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
		public M csviId(Object csviId){this.put("csviId", csviId);return this;};
	 	/** and csvi_id is null */
 		public M csviIdNull(){if(this.get("csviIdNot")==null)this.put("csviIdNot", "");this.put("csviId", null);return this;};
 		/** not .... */
 		public M csviIdNot(){this.put("csviIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csviHost(Object csviHost){this.put("csviHost", csviHost);return this;};
	 	/** and csvi_host is null */
 		public M csviHostNull(){if(this.get("csviHostNot")==null)this.put("csviHostNot", "");this.put("csviHost", null);return this;};
 		/** not .... */
 		public M csviHostNot(){this.put("csviHostNot", "not");return this;};
		/** IP地址 [非空]       **/
		public M csviAddress(Object csviAddress){this.put("csviAddress", csviAddress);return this;};
	 	/** and csvi_address is null */
 		public M csviAddressNull(){if(this.get("csviAddressNot")==null)this.put("csviAddressNot", "");this.put("csviAddress", null);return this;};
 		/** not .... */
 		public M csviAddressNot(){this.put("csviAddressNot", "not");return this;};
		/** 所属渠道  [CsChannel]      **/
		public M csviChannel(Object csviChannel){this.put("csviChannel", csviChannel);return this;};
	 	/** and csvi_channel is null */
 		public M csviChannelNull(){if(this.get("csviChannelNot")==null)this.put("csviChannelNot", "");this.put("csviChannel", null);return this;};
 		/** not .... */
 		public M csviChannelNot(){this.put("csviChannelNot", "not");return this;};
		public M csviChannel$on(CsChannel.M value){
			this.put("CsChannel", value);
			this.put("csviChannel$on", value);
			return this;
		};	
		/** 所属策划  [CsMarketPlan]      **/
		public M csviPlan(Object csviPlan){this.put("csviPlan", csviPlan);return this;};
	 	/** and csvi_plan is null */
 		public M csviPlanNull(){if(this.get("csviPlanNot")==null)this.put("csviPlanNot", "");this.put("csviPlan", null);return this;};
 		/** not .... */
 		public M csviPlanNot(){this.put("csviPlanNot", "not");return this;};
		public M csviPlan$on(CsMarketPlan.M value){
			this.put("CsMarketPlan", value);
			this.put("csviPlan$on", value);
			return this;
		};	
		/** 所属活动  [CsActivity]      **/
		public M csviActivity(Object csviActivity){this.put("csviActivity", csviActivity);return this;};
	 	/** and csvi_activity is null */
 		public M csviActivityNull(){if(this.get("csviActivityNot")==null)this.put("csviActivityNot", "");this.put("csviActivity", null);return this;};
 		/** not .... */
 		public M csviActivityNot(){this.put("csviActivityNot", "not");return this;};
		public M csviActivity$on(CsActivity.M value){
			this.put("CsActivity", value);
			this.put("csviActivity$on", value);
			return this;
		};	
		/** 源地址        **/
		public M csviUrlFrom(Object csviUrlFrom){this.put("csviUrlFrom", csviUrlFrom);return this;};
	 	/** and csvi_url_from is null */
 		public M csviUrlFromNull(){if(this.get("csviUrlFromNot")==null)this.put("csviUrlFromNot", "");this.put("csviUrlFrom", null);return this;};
 		/** not .... */
 		public M csviUrlFromNot(){this.put("csviUrlFromNot", "not");return this;};
		/** 目标地址        **/
		public M csviUrlTo(Object csviUrlTo){this.put("csviUrlTo", csviUrlTo);return this;};
	 	/** and csvi_url_to is null */
 		public M csviUrlToNull(){if(this.get("csviUrlToNot")==null)this.put("csviUrlToNot", "");this.put("csviUrlTo", null);return this;};
 		/** not .... */
 		public M csviUrlToNot(){this.put("csviUrlToNot", "not");return this;};
		/** 访问时间 [非空]       **/
		public M csviAddTime(Object csviAddTime){this.put("csviAddTime", csviAddTime);return this;};
	 	/** and csvi_add_time is null */
 		public M csviAddTimeNull(){if(this.get("csviAddTimeNot")==null)this.put("csviAddTimeNot", "");this.put("csviAddTime", null);return this;};
 		/** not .... */
 		public M csviAddTimeNot(){this.put("csviAddTimeNot", "not");return this;};
 		/** and csvi_add_time >= ? */
 		public M csviAddTimeStart(Object start){this.put("csviAddTimeStart", start);return this;};			
 		/** and csvi_add_time <= ? */
 		public M csviAddTimeEnd(Object end){this.put("csviAddTimeEnd", end);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有独立IP访问 **/
		public @api List<CsVisitIp> list(Integer size){
			return getCsVisitIpList(this,size);
		}
		/** 获取独立IP访问分页 **/
		public @api Page<CsVisitIp> page(int page,int size){
			return getCsVisitIpPage(page, size , this);
		}
		/** 根据查询条件取独立IP访问 **/
		public @api CsVisitIp get(){
			return getCsVisitIp(this);
		}
		/** 获取独立IP访问数 **/
		public @api Long count(){
			return getCsVisitIpCount(this);
		}
		/** 获取独立IP访问表达式 **/
		public @api <T> T eval(String strEval){
			return getCsVisitIpEval(strEval,this);
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
			updateCsVisitIp(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csviId="csviId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csviHost="csviHost";
		/** IP地址 [非空]       **/
		public final static @type(String.class) @like String csviAddress="csviAddress";
		/** 所属渠道  [CsChannel]      **/
		public final static @type(Long.class)  String csviChannel="csviChannel";
		/** 所属策划  [CsMarketPlan]      **/
		public final static @type(Long.class)  String csviPlan="csviPlan";
		/** 所属活动  [CsActivity]      **/
		public final static @type(Long.class)  String csviActivity="csviActivity";
		/** 源地址        **/
		public final static @type(String.class) @like String csviUrlFrom="csviUrlFrom";
		/** 目标地址        **/
		public final static @type(String.class) @like String csviUrlTo="csviUrlTo";
		/** 访问时间 [非空]       **/
		public final static @type(Date.class)  String csviAddTime="csviAddTime";
	 	/** and csvi_add_time >= ? */
 		public final static @type(Date.class) String csviAddTimeStart="csviAddTimeStart";
 		/** and csvi_add_time <= ? */
 		public final static @type(Date.class) String csviAddTimeEnd="csviAddTimeEnd";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csviId="csvi_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csviHost="csvi_host";
		/** IP地址 [非空]       **/
		public final static String csviAddress="csvi_address";
		/** 所属渠道  [CsChannel]      **/
		public final static String csviChannel="csvi_channel";
		/** 所属策划  [CsMarketPlan]      **/
		public final static String csviPlan="csvi_plan";
		/** 所属活动  [CsActivity]      **/
		public final static String csviActivity="csvi_activity";
		/** 源地址        **/
		public final static String csviUrlFrom="csvi_url_from";
		/** 目标地址        **/
		public final static String csviUrlTo="csvi_url_to";
		/** 访问时间 [非空]       **/
		public final static String csviAddTime="csvi_add_time";
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
				$.Set(name,CsVisitIp.getCsVisitIp(params));
			else
				$.Set(name,CsVisitIp.getCsVisitIpList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取独立IP访问数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsVisitIp) $.GetRequest("CsVisitIp$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsVisitIp.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsVisitIp.getCsVisitIp(params);
			else
				value = CsVisitIp.getCsVisitIpList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsVisitIp.Get($.add(CsVisitIp.F.csviId,param));
		else if(!$.empty(param.toString()))
			value = CsVisitIp.get(Long.valueOf(param.toString()));
		$.SetRequest("CsVisitIp$"+param.hashCode(), value);
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
	public void mergeSet(CsVisitIp old){
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