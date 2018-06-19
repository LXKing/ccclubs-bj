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

@namespace("operate/marketindex")
public @caption("营销指标") @table("cs_market_index") class CsMarketIndex implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csmi_id")   @primary  @note("  ") Long csmiId;// 主键 非空     
	private @caption("城市") @column("csmi_host")    @relate("$csmiHost") @RelateClass(SrvHost.class)  @note("  ") Long csmiHost;// 非空     
 	private SrvHost $csmiHost;//关联对象[运营城市]
	private @caption("指标名称") @column("csmi_name")    @note("  ") String csmiName;// 非空     
	private @caption("所属方案") @column("csmi_plan")    @relate("$csmiPlan") @RelateClass(CsMarketPlan.class)  @note("  ") Long csmiPlan;// 非空     
 	private CsMarketPlan $csmiPlan;//关联对象[营销方案]
	private @caption("指标类型") @column("csmi_type")    @note(" 0:其它 1:PV 2:UV 3:IP 4:注册 5:充值 6:订单 7:维护  ") Short csmiType;// 非空 0:其它 1:PV 2:UV 3:IP 4:注册 5:充值 6:订单 7:维护     
	private @caption("说明") @column("csmi_desc")    @note("  ") String csmiDesc;// 非空     
	private @caption("目标设定") @column("csmi_target")    @note("  ") Double csmiTarget;// 非空     
	private @caption("实际完成") @column("csmi_result")    @note("  ") Double csmiResult;//     
	private @caption("完成率") @column("csmi_rate")    @note("  ") Double csmiRate;//     
	private @caption("状态") @column("csmi_status")    @note(" 0:执行中 1:已完成  ") Short csmiStatus;// 非空 0:执行中 1:已完成     
	
	//默认构造函数
	public CsMarketIndex(){
	
	}
	
	//主键构造函数
	public CsMarketIndex(Long id){
		this.csmiId = id;
	}
	
	/**所有字段构造函数 CsMarketIndex(csmiHost,csmiName,csmiPlan,csmiType,csmiDesc,csmiTarget,csmiResult,csmiRate,csmiStatus)
	 CsMarketIndex(
	 	$.getLong("csmiHost")//城市 [非空]
	 	,$.getString("csmiName")//指标名称 [非空]
	 	,$.getLong("csmiPlan")//所属方案 [非空]
	 	,$.getShort("csmiType")//指标类型 [非空]
	 	,$.getString("csmiDesc")//说明 [非空]
	 	,$.getDouble("csmiTarget")//目标设定 [非空]
	 	,$.getDouble("csmiResult")//实际完成
	 	,$.getDouble("csmiRate")//完成率
	 	,$.getShort("csmiStatus")//状态 [非空]
	 )
	**/
	public CsMarketIndex(Long csmiHost,String csmiName,Long csmiPlan,Short csmiType,String csmiDesc,Double csmiTarget,Double csmiResult,Double csmiRate,Short csmiStatus){
		this.csmiHost=csmiHost;
		this.csmiName=csmiName;
		this.csmiPlan=csmiPlan;
		this.csmiType=csmiType;
		this.csmiDesc=csmiDesc;
		this.csmiTarget=csmiTarget;
		this.csmiResult=csmiResult;
		this.csmiRate=csmiRate;
		this.csmiStatus=csmiStatus;
	}
	
	//设置非空字段
	public CsMarketIndex setNotNull(Long csmiHost,String csmiName,Long csmiPlan,Short csmiType,String csmiDesc,Double csmiTarget,Short csmiStatus){
		this.csmiHost=csmiHost;
		this.csmiName=csmiName;
		this.csmiPlan=csmiPlan;
		this.csmiType=csmiType;
		this.csmiDesc=csmiDesc;
		this.csmiTarget=csmiTarget;
		this.csmiStatus=csmiStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsMarketIndex csmiId(Long csmiId){
		this.csmiId = csmiId;
		this.setSeted(F.csmiId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsMarketIndex csmiHost(Long csmiHost){
		this.csmiHost = csmiHost;
		this.setSeted(F.csmiHost);
		return this;
	}
	/** 指标名称 [非空]       **/
	public CsMarketIndex csmiName(String csmiName){
		this.csmiName = csmiName;
		this.setSeted(F.csmiName);
		return this;
	}
	/** 所属方案 [非空] [CsMarketPlan]      **/
	public CsMarketIndex csmiPlan(Long csmiPlan){
		this.csmiPlan = csmiPlan;
		this.setSeted(F.csmiPlan);
		return this;
	}
	/** 指标类型 [非空]   0:其它 1:PV 2:UV 3:IP 4:注册 5:充值 6:订单 7:维护     **/
	public CsMarketIndex csmiType(Short csmiType){
		this.csmiType = csmiType;
		this.setSeted(F.csmiType);
		return this;
	}
	/** 说明 [非空]       **/
	public CsMarketIndex csmiDesc(String csmiDesc){
		this.csmiDesc = csmiDesc;
		this.setSeted(F.csmiDesc);
		return this;
	}
	/** 目标设定 [非空]       **/
	public CsMarketIndex csmiTarget(Double csmiTarget){
		this.csmiTarget = csmiTarget;
		this.setSeted(F.csmiTarget);
		return this;
	}
	/** 实际完成        **/
	public CsMarketIndex csmiResult(Double csmiResult){
		this.csmiResult = csmiResult;
		this.setSeted(F.csmiResult);
		return this;
	}
	/** 完成率        **/
	public CsMarketIndex csmiRate(Double csmiRate){
		this.csmiRate = csmiRate;
		this.setSeted(F.csmiRate);
		return this;
	}
	/** 状态 [非空]   0:执行中 1:已完成     **/
	public CsMarketIndex csmiStatus(Short csmiStatus){
		this.csmiStatus = csmiStatus;
		this.setSeted(F.csmiStatus);
		return this;
	}
	
	
	//克隆对象
	public CsMarketIndex clone(){
		CsMarketIndex clone = new CsMarketIndex();
		clone.csmiHost=this.csmiHost;
		clone.csmiName=this.csmiName;
		clone.csmiPlan=this.csmiPlan;
		clone.csmiType=this.csmiType;
		clone.csmiDesc=this.csmiDesc;
		clone.csmiTarget=this.csmiTarget;
		clone.csmiStatus=this.csmiStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取营销指标
	 * @param id
	 * @return
	 */
	public static @api CsMarketIndex get(Long id){		
		return getCsMarketIndexById(id);
	}
	/**
	 * 获取所有营销指标
	 * @return
	 */
	public static @api List<CsMarketIndex> list(Map params,Integer size){
		return getCsMarketIndexList(params,size);
	}
	/**
	 * 获取营销指标分页
	 * @return
	 */
	public static @api Page<CsMarketIndex> page(int page,int size,Map params){
		return getCsMarketIndexPage(page, size , params);
	}
	/**
	 * 根据查询条件取营销指标
	 * @param params
	 * @return
	 */
	public static @api CsMarketIndex Get(Map params){
		return getCsMarketIndex(params);
	}
	/**
	 * 获取营销指标数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsMarketIndexCount(params);
	}
	/**
	 * 获取营销指标数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsMarketIndexEval(eval,params);
	}
	
	/**
	 * 根据ID取营销指标
	 * @param id
	 * @return
	 */
	public static @api CsMarketIndex getCsMarketIndexById(Long id){		
		CsMarketIndex csMarketIndex = (CsMarketIndex) $.GetRequest("CsMarketIndex$"+id);
		if(csMarketIndex!=null)
			return csMarketIndex;
		com.ccclubs.service.admin.ICsMarketIndexService csMarketIndexService = $.GetSpringBean("csMarketIndexService");		
		return csMarketIndexService.getCsMarketIndexById(id);
	}
	
	
	/**
	 * 根据ID取营销指标的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsMarketIndex.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsMarketIndex csMarketIndex = get(id);
		if(csMarketIndex!=null){
			String strValue = csMarketIndex.getCsmiName$();
			if(!"CsmiName".equals("CsmiName"))
				strValue+="("+csMarketIndex.getCsmiName$()+")";
			MemCache.setValue(CsMarketIndex.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsmiName$();
		if(!"CsmiName".equals("CsmiName"))
			keyValue+="("+this.getCsmiName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有营销指标
	 * @return
	 */
	public static @api List<CsMarketIndex> getCsMarketIndexList(Map params,Integer size){
		com.ccclubs.service.admin.ICsMarketIndexService csMarketIndexService = $.GetSpringBean("csMarketIndexService");
		return csMarketIndexService.getCsMarketIndexList(params, size);
	}
	
	/**
	 * 获取营销指标分页
	 * @return
	 */
	public static @api Page<CsMarketIndex> getCsMarketIndexPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsMarketIndexService csMarketIndexService = $.GetSpringBean("csMarketIndexService");
		return csMarketIndexService.getCsMarketIndexPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取营销指标
	 * @param params
	 * @return
	 */
	public static @api CsMarketIndex getCsMarketIndex(Map params){
		com.ccclubs.service.admin.ICsMarketIndexService csMarketIndexService = $.GetSpringBean("csMarketIndexService");
		return csMarketIndexService.getCsMarketIndex(params);
	}
	
	/**
	 * 获取营销指标数
	 * @return
	 */
	public static @api Long getCsMarketIndexCount(Map params){
		com.ccclubs.service.admin.ICsMarketIndexService csMarketIndexService = $.GetSpringBean("csMarketIndexService");
		return csMarketIndexService.getCsMarketIndexCount(params);
	}
		
		
	/**
	 * 获取营销指标自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsMarketIndexEval(String eval,Map params){
		com.ccclubs.service.admin.ICsMarketIndexService csMarketIndexService = $.GetSpringBean("csMarketIndexService");
		return csMarketIndexService.getCsMarketIndexEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsMarketIndex(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsMarketIndexService csMarketIndexService = $.GetSpringBean("csMarketIndexService");
		csMarketIndexService.updateCsMarketIndexByConfirm(set, where);
	}
	
	
	/**
	 * 保存营销指标对象
	 * @param params
	 * @return
	 */
	public CsMarketIndex save(){
		com.ccclubs.service.admin.ICsMarketIndexService csMarketIndexService = $.GetSpringBean("csMarketIndexService");
		if(this.getCsmiId()!=null)
			csMarketIndexService.updateCsMarketIndex(this);
		else
			return csMarketIndexService.saveCsMarketIndex(this);
		return this;
	}
	
	
	/**
	 * 更新营销指标对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsMarketIndexService csMarketIndexService = $.GetSpringBean("csMarketIndexService");
		csMarketIndexService.updateCsMarketIndex$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsMarketIndexService csMarketIndexService = $.GetSpringBean("csMarketIndexService");
		if($.equals($.config("logic_delete"),"true"))
			csMarketIndexService.removeCsMarketIndexById(this.getCsmiId());
		else
			csMarketIndexService.deleteCsMarketIndexById(this.getCsmiId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsMarketIndexService csMarketIndexService = $.GetSpringBean("csMarketIndexService");
		return csMarketIndexService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsmiId(){
		return this.csmiId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsmiId$(){
		String strValue="";
		 strValue=$.str(this.getCsmiId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsmiId(Long csmiId){
		this.csmiId = csmiId;
		this.setSeted(F.csmiId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsmiHost(){
		return this.csmiHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsmiHost$(){
		String strValue="";
		if(this.getCsmiHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsmiHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsmiHost(Long csmiHost){
		this.csmiHost = csmiHost;
		this.setSeted(F.csmiHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csmiHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsmiHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsmiHost()!=null){
 			srvHost = SrvHost.get(this.getCsmiHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsmiHost(), srvHost);
	 	return srvHost;
	}
	/*******************************指标名称**********************************/	
	/**
	* 指标名称 [非空]      
	**/
	public String getCsmiName(){
		return this.csmiName;
	}
	/**
	* 获取指标名称格式化(toString)
	**/
	public String getCsmiName$(){
		String strValue="";
		 strValue=$.str(this.getCsmiName());
	 	 return strValue;
	}
	/**
	* 指标名称 [非空]      
	**/
	public void setCsmiName(String csmiName){
		this.csmiName = csmiName;
		this.setSeted(F.csmiName);
	}
	/*******************************所属方案**********************************/	
	/**
	* 所属方案 [非空] [CsMarketPlan]     
	**/
	public Long getCsmiPlan(){
		return this.csmiPlan;
	}
	/**
	* 获取所属方案格式化(toString)
	**/
	public String getCsmiPlan$(){
		String strValue="";
		if(this.getCsmiPlan()!=null){
				strValue+=$.str(CsMarketPlan.getKeyValue(this.getCsmiPlan()));
		}			
	 	 return strValue;
	}
	/**
	* 所属方案 [非空] [CsMarketPlan]     
	**/
	public void setCsmiPlan(Long csmiPlan){
		this.csmiPlan = csmiPlan;
		this.setSeted(F.csmiPlan);
	}
	/**
	* 获取关联对象[营销方案]
	**/	 			
 	public CsMarketPlan get$csmiPlan(){
 		com.ccclubs.model.CsMarketPlan csMarketPlan = (com.ccclubs.model.CsMarketPlan) $.GetRequest("CsMarketPlan$"+this.getCsmiPlan());
 		if(csMarketPlan!=null)
			return csMarketPlan;
		if(this.getCsmiPlan()!=null){
 			csMarketPlan = CsMarketPlan.get(this.getCsmiPlan());
 		}
 		$.SetRequest("CsMarketPlan$"+this.getCsmiPlan(), csMarketPlan);
	 	return csMarketPlan;
	}
	/*******************************指标类型**********************************/	
	/**
	* 指标类型 [非空]   0:其它 1:PV 2:UV 3:IP 4:注册 5:充值 6:订单 7:维护    
	**/
	public Short getCsmiType(){
		return this.csmiType;
	}
	/**
	* 获取指标类型格式化(toString)
	**/
	public String getCsmiType$(){
		String strValue="";
		 if($.equals($.str(this.getCsmiType()),"0"))
			strValue=$.str("其它");		 
		 if($.equals($.str(this.getCsmiType()),"1"))
			strValue=$.str("PV");		 
		 if($.equals($.str(this.getCsmiType()),"2"))
			strValue=$.str("UV");		 
		 if($.equals($.str(this.getCsmiType()),"3"))
			strValue=$.str("IP");		 
		 if($.equals($.str(this.getCsmiType()),"4"))
			strValue=$.str("注册");		 
		 if($.equals($.str(this.getCsmiType()),"5"))
			strValue=$.str("充值");		 
		 if($.equals($.str(this.getCsmiType()),"6"))
			strValue=$.str("订单");		 
		 if($.equals($.str(this.getCsmiType()),"7"))
			strValue=$.str("维护");		 
	 	 return strValue;
	}
	/**
	* 指标类型 [非空]   0:其它 1:PV 2:UV 3:IP 4:注册 5:充值 6:订单 7:维护    
	**/
	public void setCsmiType(Short csmiType){
		this.csmiType = csmiType;
		this.setSeted(F.csmiType);
	}
	/*******************************说明**********************************/	
	/**
	* 说明 [非空]      
	**/
	public String getCsmiDesc(){
		return this.csmiDesc;
	}
	/**
	* 获取说明格式化(toString)
	**/
	public String getCsmiDesc$(){
		String strValue="";
		 strValue=$.str(this.getCsmiDesc());
	 	 return strValue;
	}
	/**
	* 说明 [非空]      
	**/
	public void setCsmiDesc(String csmiDesc){
		this.csmiDesc = csmiDesc;
		this.setSeted(F.csmiDesc);
	}
	/*******************************目标设定**********************************/	
	/**
	* 目标设定 [非空]      
	**/
	public Double getCsmiTarget(){
		return this.csmiTarget;
	}
	/**
	* 获取目标设定格式化(toString)
	**/
	public String getCsmiTarget$(){
		String strValue="";
		 strValue=$.str(this.getCsmiTarget());	
	 	 return strValue;
	}
	/**
	* 目标设定 [非空]      
	**/
	public void setCsmiTarget(Double csmiTarget){
		this.csmiTarget = csmiTarget;
		this.setSeted(F.csmiTarget);
	}
	/*******************************实际完成**********************************/	
	/**
	* 实际完成       
	**/
	public Double getCsmiResult(){
		return this.csmiResult;
	}
	/**
	* 获取实际完成格式化(toString)
	**/
	public String getCsmiResult$(){
		String strValue="";
		 strValue=$.str(this.getCsmiResult());	
	 	 return strValue;
	}
	/**
	* 实际完成       
	**/
	public void setCsmiResult(Double csmiResult){
		this.csmiResult = csmiResult;
		this.setSeted(F.csmiResult);
	}
	/*******************************完成率**********************************/	
	/**
	* 完成率       
	**/
	public Double getCsmiRate(){
		return this.csmiRate;
	}
	/**
	* 获取完成率格式化(toString)
	**/
	public String getCsmiRate$(){
		String strValue="";
		 strValue=$.str(this.getCsmiRate());	
	 	 return strValue;
	}
	/**
	* 完成率       
	**/
	public void setCsmiRate(Double csmiRate){
		this.csmiRate = csmiRate;
		this.setSeted(F.csmiRate);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:执行中 1:已完成    
	**/
	public Short getCsmiStatus(){
		return this.csmiStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsmiStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsmiStatus()),"0"))
			strValue=$.str("执行中");		 
		 if($.equals($.str(this.getCsmiStatus()),"1"))
			strValue=$.str("已完成");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:执行中 1:已完成    
	**/
	public void setCsmiStatus(Short csmiStatus){
		this.csmiStatus = csmiStatus;
		this.setSeted(F.csmiStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsMarketIndex.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMarketIndex.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMarketIndex.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsMarketIndex.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsMarketIndex.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMarketIndex.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMarketIndex.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsMarketIndex.this);
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
		public M csmiId(Object csmiId){this.put("csmiId", csmiId);return this;};
	 	/** and csmi_id is null */
 		public M csmiIdNull(){if(this.get("csmiIdNot")==null)this.put("csmiIdNot", "");this.put("csmiId", null);return this;};
 		/** not .... */
 		public M csmiIdNot(){this.put("csmiIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csmiHost(Object csmiHost){this.put("csmiHost", csmiHost);return this;};
	 	/** and csmi_host is null */
 		public M csmiHostNull(){if(this.get("csmiHostNot")==null)this.put("csmiHostNot", "");this.put("csmiHost", null);return this;};
 		/** not .... */
 		public M csmiHostNot(){this.put("csmiHostNot", "not");return this;};
		/** 指标名称 [非空]       **/
		public M csmiName(Object csmiName){this.put("csmiName", csmiName);return this;};
	 	/** and csmi_name is null */
 		public M csmiNameNull(){if(this.get("csmiNameNot")==null)this.put("csmiNameNot", "");this.put("csmiName", null);return this;};
 		/** not .... */
 		public M csmiNameNot(){this.put("csmiNameNot", "not");return this;};
		/** 所属方案 [非空] [CsMarketPlan]      **/
		public M csmiPlan(Object csmiPlan){this.put("csmiPlan", csmiPlan);return this;};
	 	/** and csmi_plan is null */
 		public M csmiPlanNull(){if(this.get("csmiPlanNot")==null)this.put("csmiPlanNot", "");this.put("csmiPlan", null);return this;};
 		/** not .... */
 		public M csmiPlanNot(){this.put("csmiPlanNot", "not");return this;};
		public M csmiPlan$on(CsMarketPlan.M value){
			this.put("CsMarketPlan", value);
			this.put("csmiPlan$on", value);
			return this;
		};	
		/** 指标类型 [非空]   0:其它 1:PV 2:UV 3:IP 4:注册 5:充值 6:订单 7:维护     **/
		public M csmiType(Object csmiType){this.put("csmiType", csmiType);return this;};
	 	/** and csmi_type is null */
 		public M csmiTypeNull(){if(this.get("csmiTypeNot")==null)this.put("csmiTypeNot", "");this.put("csmiType", null);return this;};
 		/** not .... */
 		public M csmiTypeNot(){this.put("csmiTypeNot", "not");return this;};
		/** 说明 [非空]       **/
		public M csmiDesc(Object csmiDesc){this.put("csmiDesc", csmiDesc);return this;};
	 	/** and csmi_desc is null */
 		public M csmiDescNull(){if(this.get("csmiDescNot")==null)this.put("csmiDescNot", "");this.put("csmiDesc", null);return this;};
 		/** not .... */
 		public M csmiDescNot(){this.put("csmiDescNot", "not");return this;};
		/** 目标设定 [非空]       **/
		public M csmiTarget(Object csmiTarget){this.put("csmiTarget", csmiTarget);return this;};
	 	/** and csmi_target is null */
 		public M csmiTargetNull(){if(this.get("csmiTargetNot")==null)this.put("csmiTargetNot", "");this.put("csmiTarget", null);return this;};
 		/** not .... */
 		public M csmiTargetNot(){this.put("csmiTargetNot", "not");return this;};
		/** and csmi_target >= ? */
		public M csmiTargetMin(Object min){this.put("csmiTargetMin", min);return this;};
		/** and csmi_target <= ? */
		public M csmiTargetMax(Object max){this.put("csmiTargetMax", max);return this;};
		/** 实际完成        **/
		public M csmiResult(Object csmiResult){this.put("csmiResult", csmiResult);return this;};
	 	/** and csmi_result is null */
 		public M csmiResultNull(){if(this.get("csmiResultNot")==null)this.put("csmiResultNot", "");this.put("csmiResult", null);return this;};
 		/** not .... */
 		public M csmiResultNot(){this.put("csmiResultNot", "not");return this;};
		/** and csmi_result >= ? */
		public M csmiResultMin(Object min){this.put("csmiResultMin", min);return this;};
		/** and csmi_result <= ? */
		public M csmiResultMax(Object max){this.put("csmiResultMax", max);return this;};
		/** 完成率        **/
		public M csmiRate(Object csmiRate){this.put("csmiRate", csmiRate);return this;};
	 	/** and csmi_rate is null */
 		public M csmiRateNull(){if(this.get("csmiRateNot")==null)this.put("csmiRateNot", "");this.put("csmiRate", null);return this;};
 		/** not .... */
 		public M csmiRateNot(){this.put("csmiRateNot", "not");return this;};
		/** and csmi_rate >= ? */
		public M csmiRateMin(Object min){this.put("csmiRateMin", min);return this;};
		/** and csmi_rate <= ? */
		public M csmiRateMax(Object max){this.put("csmiRateMax", max);return this;};
		/** 状态 [非空]   0:执行中 1:已完成     **/
		public M csmiStatus(Object csmiStatus){this.put("csmiStatus", csmiStatus);return this;};
	 	/** and csmi_status is null */
 		public M csmiStatusNull(){if(this.get("csmiStatusNot")==null)this.put("csmiStatusNot", "");this.put("csmiStatus", null);return this;};
 		/** not .... */
 		public M csmiStatusNot(){this.put("csmiStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有营销指标 **/
		public @api List<CsMarketIndex> list(Integer size){
			return getCsMarketIndexList(this,size);
		}
		/** 获取营销指标分页 **/
		public @api Page<CsMarketIndex> page(int page,int size){
			return getCsMarketIndexPage(page, size , this);
		}
		/** 根据查询条件取营销指标 **/
		public @api CsMarketIndex get(){
			return getCsMarketIndex(this);
		}
		/** 获取营销指标数 **/
		public @api Long count(){
			return getCsMarketIndexCount(this);
		}
		/** 获取营销指标表达式 **/
		public @api <T> T eval(String strEval){
			return getCsMarketIndexEval(strEval,this);
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
			updateCsMarketIndex(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csmiId="csmiId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csmiHost="csmiHost";
		/** 指标名称 [非空]       **/
		public final static @type(String.class) @like String csmiName="csmiName";
		/** 所属方案 [非空] [CsMarketPlan]      **/
		public final static @type(Long.class)  String csmiPlan="csmiPlan";
		/** 指标类型 [非空]   0:其它 1:PV 2:UV 3:IP 4:注册 5:充值 6:订单 7:维护     **/
		public final static @type(Short.class)  String csmiType="csmiType";
		/** 说明 [非空]       **/
		public final static @type(String.class) @like String csmiDesc="csmiDesc";
		/** 目标设定 [非空]       **/
		public final static @type(Double.class)  String csmiTarget="csmiTarget";
		/** and csmi_target >= ? */
		public final static @type(Double.class) String csmiTargetMin="csmiTargetMin";
		/** and csmi_target <= ? */
		public final static @type(Double.class) String csmiTargetMax="csmiTargetMax";
		/** 实际完成        **/
		public final static @type(Double.class)  String csmiResult="csmiResult";
		/** and csmi_result >= ? */
		public final static @type(Double.class) String csmiResultMin="csmiResultMin";
		/** and csmi_result <= ? */
		public final static @type(Double.class) String csmiResultMax="csmiResultMax";
		/** 完成率        **/
		public final static @type(Double.class)  String csmiRate="csmiRate";
		/** and csmi_rate >= ? */
		public final static @type(Double.class) String csmiRateMin="csmiRateMin";
		/** and csmi_rate <= ? */
		public final static @type(Double.class) String csmiRateMax="csmiRateMax";
		/** 状态 [非空]   0:执行中 1:已完成     **/
		public final static @type(Short.class)  String csmiStatus="csmiStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csmiId="csmi_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csmiHost="csmi_host";
		/** 指标名称 [非空]       **/
		public final static String csmiName="csmi_name";
		/** 所属方案 [非空] [CsMarketPlan]      **/
		public final static String csmiPlan="csmi_plan";
		/** 指标类型 [非空]   0:其它 1:PV 2:UV 3:IP 4:注册 5:充值 6:订单 7:维护     **/
		public final static String csmiType="csmi_type";
		/** 说明 [非空]       **/
		public final static String csmiDesc="csmi_desc";
		/** 目标设定 [非空]       **/
		public final static String csmiTarget="csmi_target";
		/** 实际完成        **/
		public final static String csmiResult="csmi_result";
		/** 完成率        **/
		public final static String csmiRate="csmi_rate";
		/** 状态 [非空]   0:执行中 1:已完成     **/
		public final static String csmiStatus="csmi_status";
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
				$.Set(name,CsMarketIndex.getCsMarketIndex(params));
			else
				$.Set(name,CsMarketIndex.getCsMarketIndexList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取营销指标数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsMarketIndex) $.GetRequest("CsMarketIndex$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsMarketIndex.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsMarketIndex.getCsMarketIndex(params);
			else
				value = CsMarketIndex.getCsMarketIndexList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsMarketIndex.Get($.add(CsMarketIndex.F.csmiId,param));
		else if(!$.empty(param.toString()))
			value = CsMarketIndex.get(Long.valueOf(param.toString()));
		$.SetRequest("CsMarketIndex$"+param.hashCode(), value);
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
	public void mergeSet(CsMarketIndex old){
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