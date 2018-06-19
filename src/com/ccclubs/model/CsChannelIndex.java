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

@namespace("extension/channelindex")
public @caption("渠道指标") @table("cs_channel_index") class CsChannelIndex implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csci_id")   @primary  @note("  ") Long csciId;// 主键 非空     
	private @caption("城市") @column("csci_host")    @relate("$csciHost") @RelateClass(SrvHost.class)  @note("  ") Long csciHost;// 非空     
 	private SrvHost $csciHost;//关联对象[运营城市]
	private @caption("指标名称") @column("csci_name")    @note("  ") String csciName;// 非空     
	private @caption("所属渠道") @column("csci_plan")    @relate("$csciPlan") @RelateClass(CsChannel.class)  @note("  ") Long csciPlan;// 非空     
 	private CsChannel $csciPlan;//关联对象[访问渠道]
	private @caption("指标类型") @column("csci_type")    @note(" 0:其它 1:PV 2:UV 3:IP 4:注册 5:充值 6:订单 7:维护  ") Short csciType;// 非空 0:其它 1:PV 2:UV 3:IP 4:注册 5:充值 6:订单 7:维护     
	private @caption("说明") @column("csci_desc")    @note("  ") String csciDesc;// 非空     
	private @caption("目标设定") @column("csci_target")    @note("  ") Double csciTarget;// 非空     
	private @caption("实际完成") @column("csci_result")    @note("  ") Double csciResult;//     
	private @caption("完成率") @column("csci_rate")    @note("  ") Double csciRate;//     
	private @caption("状态") @column("csci_status")    @note(" 0:执行中 1:已完成  ") Short csciStatus;// 非空 0:执行中 1:已完成     
	
	//默认构造函数
	public CsChannelIndex(){
	
	}
	
	//主键构造函数
	public CsChannelIndex(Long id){
		this.csciId = id;
	}
	
	/**所有字段构造函数 CsChannelIndex(csciHost,csciName,csciPlan,csciType,csciDesc,csciTarget,csciResult,csciRate,csciStatus)
	 CsChannelIndex(
	 	$.getLong("csciHost")//城市 [非空]
	 	,$.getString("csciName")//指标名称 [非空]
	 	,$.getLong("csciPlan")//所属渠道 [非空]
	 	,$.getShort("csciType")//指标类型 [非空]
	 	,$.getString("csciDesc")//说明 [非空]
	 	,$.getDouble("csciTarget")//目标设定 [非空]
	 	,$.getDouble("csciResult")//实际完成
	 	,$.getDouble("csciRate")//完成率
	 	,$.getShort("csciStatus")//状态 [非空]
	 )
	**/
	public CsChannelIndex(Long csciHost,String csciName,Long csciPlan,Short csciType,String csciDesc,Double csciTarget,Double csciResult,Double csciRate,Short csciStatus){
		this.csciHost=csciHost;
		this.csciName=csciName;
		this.csciPlan=csciPlan;
		this.csciType=csciType;
		this.csciDesc=csciDesc;
		this.csciTarget=csciTarget;
		this.csciResult=csciResult;
		this.csciRate=csciRate;
		this.csciStatus=csciStatus;
	}
	
	//设置非空字段
	public CsChannelIndex setNotNull(Long csciHost,String csciName,Long csciPlan,Short csciType,String csciDesc,Double csciTarget,Short csciStatus){
		this.csciHost=csciHost;
		this.csciName=csciName;
		this.csciPlan=csciPlan;
		this.csciType=csciType;
		this.csciDesc=csciDesc;
		this.csciTarget=csciTarget;
		this.csciStatus=csciStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsChannelIndex csciId(Long csciId){
		this.csciId = csciId;
		this.setSeted(F.csciId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsChannelIndex csciHost(Long csciHost){
		this.csciHost = csciHost;
		this.setSeted(F.csciHost);
		return this;
	}
	/** 指标名称 [非空]       **/
	public CsChannelIndex csciName(String csciName){
		this.csciName = csciName;
		this.setSeted(F.csciName);
		return this;
	}
	/** 所属渠道 [非空] [CsChannel]      **/
	public CsChannelIndex csciPlan(Long csciPlan){
		this.csciPlan = csciPlan;
		this.setSeted(F.csciPlan);
		return this;
	}
	/** 指标类型 [非空]   0:其它 1:PV 2:UV 3:IP 4:注册 5:充值 6:订单 7:维护     **/
	public CsChannelIndex csciType(Short csciType){
		this.csciType = csciType;
		this.setSeted(F.csciType);
		return this;
	}
	/** 说明 [非空]       **/
	public CsChannelIndex csciDesc(String csciDesc){
		this.csciDesc = csciDesc;
		this.setSeted(F.csciDesc);
		return this;
	}
	/** 目标设定 [非空]       **/
	public CsChannelIndex csciTarget(Double csciTarget){
		this.csciTarget = csciTarget;
		this.setSeted(F.csciTarget);
		return this;
	}
	/** 实际完成        **/
	public CsChannelIndex csciResult(Double csciResult){
		this.csciResult = csciResult;
		this.setSeted(F.csciResult);
		return this;
	}
	/** 完成率        **/
	public CsChannelIndex csciRate(Double csciRate){
		this.csciRate = csciRate;
		this.setSeted(F.csciRate);
		return this;
	}
	/** 状态 [非空]   0:执行中 1:已完成     **/
	public CsChannelIndex csciStatus(Short csciStatus){
		this.csciStatus = csciStatus;
		this.setSeted(F.csciStatus);
		return this;
	}
	
	
	//克隆对象
	public CsChannelIndex clone(){
		CsChannelIndex clone = new CsChannelIndex();
		clone.csciHost=this.csciHost;
		clone.csciName=this.csciName;
		clone.csciPlan=this.csciPlan;
		clone.csciType=this.csciType;
		clone.csciDesc=this.csciDesc;
		clone.csciTarget=this.csciTarget;
		clone.csciStatus=this.csciStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取渠道指标
	 * @param id
	 * @return
	 */
	public static @api CsChannelIndex get(Long id){		
		return getCsChannelIndexById(id);
	}
	/**
	 * 获取所有渠道指标
	 * @return
	 */
	public static @api List<CsChannelIndex> list(Map params,Integer size){
		return getCsChannelIndexList(params,size);
	}
	/**
	 * 获取渠道指标分页
	 * @return
	 */
	public static @api Page<CsChannelIndex> page(int page,int size,Map params){
		return getCsChannelIndexPage(page, size , params);
	}
	/**
	 * 根据查询条件取渠道指标
	 * @param params
	 * @return
	 */
	public static @api CsChannelIndex Get(Map params){
		return getCsChannelIndex(params);
	}
	/**
	 * 获取渠道指标数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsChannelIndexCount(params);
	}
	/**
	 * 获取渠道指标数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsChannelIndexEval(eval,params);
	}
	
	/**
	 * 根据ID取渠道指标
	 * @param id
	 * @return
	 */
	public static @api CsChannelIndex getCsChannelIndexById(Long id){		
		CsChannelIndex csChannelIndex = (CsChannelIndex) $.GetRequest("CsChannelIndex$"+id);
		if(csChannelIndex!=null)
			return csChannelIndex;
		com.ccclubs.service.admin.ICsChannelIndexService csChannelIndexService = $.GetSpringBean("csChannelIndexService");		
		return csChannelIndexService.getCsChannelIndexById(id);
	}
	
	
	/**
	 * 根据ID取渠道指标的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsChannelIndex.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsChannelIndex csChannelIndex = get(id);
		if(csChannelIndex!=null){
			String strValue = csChannelIndex.getCsciName$();
			if(!"CsciName".equals("CsciName"))
				strValue+="("+csChannelIndex.getCsciName$()+")";
			MemCache.setValue(CsChannelIndex.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsciName$();
		if(!"CsciName".equals("CsciName"))
			keyValue+="("+this.getCsciName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有渠道指标
	 * @return
	 */
	public static @api List<CsChannelIndex> getCsChannelIndexList(Map params,Integer size){
		com.ccclubs.service.admin.ICsChannelIndexService csChannelIndexService = $.GetSpringBean("csChannelIndexService");
		return csChannelIndexService.getCsChannelIndexList(params, size);
	}
	
	/**
	 * 获取渠道指标分页
	 * @return
	 */
	public static @api Page<CsChannelIndex> getCsChannelIndexPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsChannelIndexService csChannelIndexService = $.GetSpringBean("csChannelIndexService");
		return csChannelIndexService.getCsChannelIndexPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取渠道指标
	 * @param params
	 * @return
	 */
	public static @api CsChannelIndex getCsChannelIndex(Map params){
		com.ccclubs.service.admin.ICsChannelIndexService csChannelIndexService = $.GetSpringBean("csChannelIndexService");
		return csChannelIndexService.getCsChannelIndex(params);
	}
	
	/**
	 * 获取渠道指标数
	 * @return
	 */
	public static @api Long getCsChannelIndexCount(Map params){
		com.ccclubs.service.admin.ICsChannelIndexService csChannelIndexService = $.GetSpringBean("csChannelIndexService");
		return csChannelIndexService.getCsChannelIndexCount(params);
	}
		
		
	/**
	 * 获取渠道指标自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsChannelIndexEval(String eval,Map params){
		com.ccclubs.service.admin.ICsChannelIndexService csChannelIndexService = $.GetSpringBean("csChannelIndexService");
		return csChannelIndexService.getCsChannelIndexEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsChannelIndex(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsChannelIndexService csChannelIndexService = $.GetSpringBean("csChannelIndexService");
		csChannelIndexService.updateCsChannelIndexByConfirm(set, where);
	}
	
	
	/**
	 * 保存渠道指标对象
	 * @param params
	 * @return
	 */
	public CsChannelIndex save(){
		com.ccclubs.service.admin.ICsChannelIndexService csChannelIndexService = $.GetSpringBean("csChannelIndexService");
		if(this.getCsciId()!=null)
			csChannelIndexService.updateCsChannelIndex(this);
		else
			return csChannelIndexService.saveCsChannelIndex(this);
		return this;
	}
	
	
	/**
	 * 更新渠道指标对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsChannelIndexService csChannelIndexService = $.GetSpringBean("csChannelIndexService");
		csChannelIndexService.updateCsChannelIndex$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsChannelIndexService csChannelIndexService = $.GetSpringBean("csChannelIndexService");
		if($.equals($.config("logic_delete"),"true"))
			csChannelIndexService.removeCsChannelIndexById(this.getCsciId());
		else
			csChannelIndexService.deleteCsChannelIndexById(this.getCsciId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsChannelIndexService csChannelIndexService = $.GetSpringBean("csChannelIndexService");
		return csChannelIndexService.executeTransaction(function);
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
	/*******************************指标名称**********************************/	
	/**
	* 指标名称 [非空]      
	**/
	public String getCsciName(){
		return this.csciName;
	}
	/**
	* 获取指标名称格式化(toString)
	**/
	public String getCsciName$(){
		String strValue="";
		 strValue=$.str(this.getCsciName());
	 	 return strValue;
	}
	/**
	* 指标名称 [非空]      
	**/
	public void setCsciName(String csciName){
		this.csciName = csciName;
		this.setSeted(F.csciName);
	}
	/*******************************所属渠道**********************************/	
	/**
	* 所属渠道 [非空] [CsChannel]     
	**/
	public Long getCsciPlan(){
		return this.csciPlan;
	}
	/**
	* 获取所属渠道格式化(toString)
	**/
	public String getCsciPlan$(){
		String strValue="";
		if(this.getCsciPlan()!=null){
				strValue+=$.str(CsChannel.getKeyValue(this.getCsciPlan()));
		}			
	 	 return strValue;
	}
	/**
	* 所属渠道 [非空] [CsChannel]     
	**/
	public void setCsciPlan(Long csciPlan){
		this.csciPlan = csciPlan;
		this.setSeted(F.csciPlan);
	}
	/**
	* 获取关联对象[访问渠道]
	**/	 			
 	public CsChannel get$csciPlan(){
 		com.ccclubs.model.CsChannel csChannel = (com.ccclubs.model.CsChannel) $.GetRequest("CsChannel$"+this.getCsciPlan());
 		if(csChannel!=null)
			return csChannel;
		if(this.getCsciPlan()!=null){
 			csChannel = CsChannel.get(this.getCsciPlan());
 		}
 		$.SetRequest("CsChannel$"+this.getCsciPlan(), csChannel);
	 	return csChannel;
	}
	/*******************************指标类型**********************************/	
	/**
	* 指标类型 [非空]   0:其它 1:PV 2:UV 3:IP 4:注册 5:充值 6:订单 7:维护    
	**/
	public Short getCsciType(){
		return this.csciType;
	}
	/**
	* 获取指标类型格式化(toString)
	**/
	public String getCsciType$(){
		String strValue="";
		 if($.equals($.str(this.getCsciType()),"0"))
			strValue=$.str("其它");		 
		 if($.equals($.str(this.getCsciType()),"1"))
			strValue=$.str("PV");		 
		 if($.equals($.str(this.getCsciType()),"2"))
			strValue=$.str("UV");		 
		 if($.equals($.str(this.getCsciType()),"3"))
			strValue=$.str("IP");		 
		 if($.equals($.str(this.getCsciType()),"4"))
			strValue=$.str("注册");		 
		 if($.equals($.str(this.getCsciType()),"5"))
			strValue=$.str("充值");		 
		 if($.equals($.str(this.getCsciType()),"6"))
			strValue=$.str("订单");		 
		 if($.equals($.str(this.getCsciType()),"7"))
			strValue=$.str("维护");		 
	 	 return strValue;
	}
	/**
	* 指标类型 [非空]   0:其它 1:PV 2:UV 3:IP 4:注册 5:充值 6:订单 7:维护    
	**/
	public void setCsciType(Short csciType){
		this.csciType = csciType;
		this.setSeted(F.csciType);
	}
	/*******************************说明**********************************/	
	/**
	* 说明 [非空]      
	**/
	public String getCsciDesc(){
		return this.csciDesc;
	}
	/**
	* 获取说明格式化(toString)
	**/
	public String getCsciDesc$(){
		String strValue="";
		 strValue=$.str(this.getCsciDesc());
	 	 return strValue;
	}
	/**
	* 说明 [非空]      
	**/
	public void setCsciDesc(String csciDesc){
		this.csciDesc = csciDesc;
		this.setSeted(F.csciDesc);
	}
	/*******************************目标设定**********************************/	
	/**
	* 目标设定 [非空]      
	**/
	public Double getCsciTarget(){
		return this.csciTarget;
	}
	/**
	* 获取目标设定格式化(toString)
	**/
	public String getCsciTarget$(){
		String strValue="";
		 strValue=$.str(this.getCsciTarget());	
	 	 return strValue;
	}
	/**
	* 目标设定 [非空]      
	**/
	public void setCsciTarget(Double csciTarget){
		this.csciTarget = csciTarget;
		this.setSeted(F.csciTarget);
	}
	/*******************************实际完成**********************************/	
	/**
	* 实际完成       
	**/
	public Double getCsciResult(){
		return this.csciResult;
	}
	/**
	* 获取实际完成格式化(toString)
	**/
	public String getCsciResult$(){
		String strValue="";
		 strValue=$.str(this.getCsciResult());	
	 	 return strValue;
	}
	/**
	* 实际完成       
	**/
	public void setCsciResult(Double csciResult){
		this.csciResult = csciResult;
		this.setSeted(F.csciResult);
	}
	/*******************************完成率**********************************/	
	/**
	* 完成率       
	**/
	public Double getCsciRate(){
		return this.csciRate;
	}
	/**
	* 获取完成率格式化(toString)
	**/
	public String getCsciRate$(){
		String strValue="";
		 strValue=$.str(this.getCsciRate());	
	 	 return strValue;
	}
	/**
	* 完成率       
	**/
	public void setCsciRate(Double csciRate){
		this.csciRate = csciRate;
		this.setSeted(F.csciRate);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:执行中 1:已完成    
	**/
	public Short getCsciStatus(){
		return this.csciStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsciStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsciStatus()),"0"))
			strValue=$.str("执行中");		 
		 if($.equals($.str(this.getCsciStatus()),"1"))
			strValue=$.str("已完成");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:执行中 1:已完成    
	**/
	public void setCsciStatus(Short csciStatus){
		this.csciStatus = csciStatus;
		this.setSeted(F.csciStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsChannelIndex.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsChannelIndex.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsChannelIndex.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsChannelIndex.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsChannelIndex.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsChannelIndex.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsChannelIndex.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsChannelIndex.this);
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
		/** 指标名称 [非空]       **/
		public M csciName(Object csciName){this.put("csciName", csciName);return this;};
	 	/** and csci_name is null */
 		public M csciNameNull(){if(this.get("csciNameNot")==null)this.put("csciNameNot", "");this.put("csciName", null);return this;};
 		/** not .... */
 		public M csciNameNot(){this.put("csciNameNot", "not");return this;};
		/** 所属渠道 [非空] [CsChannel]      **/
		public M csciPlan(Object csciPlan){this.put("csciPlan", csciPlan);return this;};
	 	/** and csci_plan is null */
 		public M csciPlanNull(){if(this.get("csciPlanNot")==null)this.put("csciPlanNot", "");this.put("csciPlan", null);return this;};
 		/** not .... */
 		public M csciPlanNot(){this.put("csciPlanNot", "not");return this;};
		public M csciPlan$on(CsChannel.M value){
			this.put("CsChannel", value);
			this.put("csciPlan$on", value);
			return this;
		};	
		/** 指标类型 [非空]   0:其它 1:PV 2:UV 3:IP 4:注册 5:充值 6:订单 7:维护     **/
		public M csciType(Object csciType){this.put("csciType", csciType);return this;};
	 	/** and csci_type is null */
 		public M csciTypeNull(){if(this.get("csciTypeNot")==null)this.put("csciTypeNot", "");this.put("csciType", null);return this;};
 		/** not .... */
 		public M csciTypeNot(){this.put("csciTypeNot", "not");return this;};
		/** 说明 [非空]       **/
		public M csciDesc(Object csciDesc){this.put("csciDesc", csciDesc);return this;};
	 	/** and csci_desc is null */
 		public M csciDescNull(){if(this.get("csciDescNot")==null)this.put("csciDescNot", "");this.put("csciDesc", null);return this;};
 		/** not .... */
 		public M csciDescNot(){this.put("csciDescNot", "not");return this;};
		/** 目标设定 [非空]       **/
		public M csciTarget(Object csciTarget){this.put("csciTarget", csciTarget);return this;};
	 	/** and csci_target is null */
 		public M csciTargetNull(){if(this.get("csciTargetNot")==null)this.put("csciTargetNot", "");this.put("csciTarget", null);return this;};
 		/** not .... */
 		public M csciTargetNot(){this.put("csciTargetNot", "not");return this;};
		/** and csci_target >= ? */
		public M csciTargetMin(Object min){this.put("csciTargetMin", min);return this;};
		/** and csci_target <= ? */
		public M csciTargetMax(Object max){this.put("csciTargetMax", max);return this;};
		/** 实际完成        **/
		public M csciResult(Object csciResult){this.put("csciResult", csciResult);return this;};
	 	/** and csci_result is null */
 		public M csciResultNull(){if(this.get("csciResultNot")==null)this.put("csciResultNot", "");this.put("csciResult", null);return this;};
 		/** not .... */
 		public M csciResultNot(){this.put("csciResultNot", "not");return this;};
		/** and csci_result >= ? */
		public M csciResultMin(Object min){this.put("csciResultMin", min);return this;};
		/** and csci_result <= ? */
		public M csciResultMax(Object max){this.put("csciResultMax", max);return this;};
		/** 完成率        **/
		public M csciRate(Object csciRate){this.put("csciRate", csciRate);return this;};
	 	/** and csci_rate is null */
 		public M csciRateNull(){if(this.get("csciRateNot")==null)this.put("csciRateNot", "");this.put("csciRate", null);return this;};
 		/** not .... */
 		public M csciRateNot(){this.put("csciRateNot", "not");return this;};
		/** and csci_rate >= ? */
		public M csciRateMin(Object min){this.put("csciRateMin", min);return this;};
		/** and csci_rate <= ? */
		public M csciRateMax(Object max){this.put("csciRateMax", max);return this;};
		/** 状态 [非空]   0:执行中 1:已完成     **/
		public M csciStatus(Object csciStatus){this.put("csciStatus", csciStatus);return this;};
	 	/** and csci_status is null */
 		public M csciStatusNull(){if(this.get("csciStatusNot")==null)this.put("csciStatusNot", "");this.put("csciStatus", null);return this;};
 		/** not .... */
 		public M csciStatusNot(){this.put("csciStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有渠道指标 **/
		public @api List<CsChannelIndex> list(Integer size){
			return getCsChannelIndexList(this,size);
		}
		/** 获取渠道指标分页 **/
		public @api Page<CsChannelIndex> page(int page,int size){
			return getCsChannelIndexPage(page, size , this);
		}
		/** 根据查询条件取渠道指标 **/
		public @api CsChannelIndex get(){
			return getCsChannelIndex(this);
		}
		/** 获取渠道指标数 **/
		public @api Long count(){
			return getCsChannelIndexCount(this);
		}
		/** 获取渠道指标表达式 **/
		public @api <T> T eval(String strEval){
			return getCsChannelIndexEval(strEval,this);
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
			updateCsChannelIndex(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csciId="csciId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csciHost="csciHost";
		/** 指标名称 [非空]       **/
		public final static @type(String.class) @like String csciName="csciName";
		/** 所属渠道 [非空] [CsChannel]      **/
		public final static @type(Long.class)  String csciPlan="csciPlan";
		/** 指标类型 [非空]   0:其它 1:PV 2:UV 3:IP 4:注册 5:充值 6:订单 7:维护     **/
		public final static @type(Short.class)  String csciType="csciType";
		/** 说明 [非空]       **/
		public final static @type(String.class) @like String csciDesc="csciDesc";
		/** 目标设定 [非空]       **/
		public final static @type(Double.class)  String csciTarget="csciTarget";
		/** and csci_target >= ? */
		public final static @type(Double.class) String csciTargetMin="csciTargetMin";
		/** and csci_target <= ? */
		public final static @type(Double.class) String csciTargetMax="csciTargetMax";
		/** 实际完成        **/
		public final static @type(Double.class)  String csciResult="csciResult";
		/** and csci_result >= ? */
		public final static @type(Double.class) String csciResultMin="csciResultMin";
		/** and csci_result <= ? */
		public final static @type(Double.class) String csciResultMax="csciResultMax";
		/** 完成率        **/
		public final static @type(Double.class)  String csciRate="csciRate";
		/** and csci_rate >= ? */
		public final static @type(Double.class) String csciRateMin="csciRateMin";
		/** and csci_rate <= ? */
		public final static @type(Double.class) String csciRateMax="csciRateMax";
		/** 状态 [非空]   0:执行中 1:已完成     **/
		public final static @type(Short.class)  String csciStatus="csciStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csciId="csci_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csciHost="csci_host";
		/** 指标名称 [非空]       **/
		public final static String csciName="csci_name";
		/** 所属渠道 [非空] [CsChannel]      **/
		public final static String csciPlan="csci_plan";
		/** 指标类型 [非空]   0:其它 1:PV 2:UV 3:IP 4:注册 5:充值 6:订单 7:维护     **/
		public final static String csciType="csci_type";
		/** 说明 [非空]       **/
		public final static String csciDesc="csci_desc";
		/** 目标设定 [非空]       **/
		public final static String csciTarget="csci_target";
		/** 实际完成        **/
		public final static String csciResult="csci_result";
		/** 完成率        **/
		public final static String csciRate="csci_rate";
		/** 状态 [非空]   0:执行中 1:已完成     **/
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
				$.Set(name,CsChannelIndex.getCsChannelIndex(params));
			else
				$.Set(name,CsChannelIndex.getCsChannelIndexList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取渠道指标数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsChannelIndex) $.GetRequest("CsChannelIndex$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsChannelIndex.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsChannelIndex.getCsChannelIndex(params);
			else
				value = CsChannelIndex.getCsChannelIndexList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsChannelIndex.Get($.add(CsChannelIndex.F.csciId,param));
		else if(!$.empty(param.toString()))
			value = CsChannelIndex.get(Long.valueOf(param.toString()));
		$.SetRequest("CsChannelIndex$"+param.hashCode(), value);
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
	public void mergeSet(CsChannelIndex old){
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