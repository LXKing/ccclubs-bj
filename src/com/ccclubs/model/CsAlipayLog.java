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

@namespace("finance/alipay/alipaylog")
public @caption("接口日志") @table("cs_alipay_log") class CsAlipayLog implements java.io.Serializable
{
	private static final long serialVersionUID = 1l;
	private @caption("编号") @column("csal_id")   @primary  @note("  ") Long csalId;// 主键 非空     
	private @caption("接口类型") @column("csal_bank_type")    @note(" 0:支付宝 10:招商银行  ") Short csalBankType;// 非空 0:支付宝 10:招商银行     
	private @caption("关联充值") @column("csal_record")    @relate("$csalRecord") @RelateClass(CsAlipayRecord.class)  @note("  ") Long csalRecord;//     
 	private CsAlipayRecord $csalRecord;//关联对象[接口充值]
	private @caption("关联退款") @column("csal_refund")    @relate("$csalRefund") @RelateClass(CsAlipayRefund.class)  @note("  ") Long csalRefund;//     
 	private CsAlipayRefund $csalRefund;//关联对象[接口退款]
	private @caption("关联其它") @column("csal_other")    @note("  比如订单号、会员ID") Long csalOther;//  比如订单号、会员ID   
	private @caption("模型类名") @column("csal_model_class")    @note("  比如CsOrder、CsMember") String csalModelClass;//  比如CsOrder、CsMember   
	private @caption("类型") @column("csal_type")    @note(" 0:进站 1:出站  ") Short csalType;// 非空 0:进站 1:出站     
	private @caption("日志内容") @column("csal_log")    @note("  ") String csalLog;//     
	private @caption("添加时间") @column("csal_add_time")    @note("  ") Date csalAddTime;// 非空     
	private @caption("状态") @column("csal_status")    @note(" 1:正常 0:无效  ") Short csalStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsAlipayLog(){
	
	}
	
	//主键构造函数
	public CsAlipayLog(Long id){
		this.csalId = id;
	}
	
	/**所有字段构造函数 CsAlipayLog(csalBankType,csalRecord,csalRefund,csalOther,csalModelClass,csalType,csalLog,csalAddTime,csalStatus)
	 CsAlipayLog(
	 	$.getShort("csalBankType")//接口类型 [非空]
	 	,$.getLong("csalRecord")//关联充值
	 	,$.getLong("csalRefund")//关联退款
	 	,$.getLong("csalOther")//关联其它
	 	,$.getString("csalModelClass")//模型类名
	 	,$.getShort("csalType")//类型 [非空]
	 	,$.getString("csalLog")//日志内容
	 	,$.getDate("csalAddTime")//添加时间 [非空]
	 	,$.getShort("csalStatus")//状态 [非空]
	 )
	**/
	public CsAlipayLog(Short csalBankType,Long csalRecord,Long csalRefund,Long csalOther,String csalModelClass,Short csalType,String csalLog,Date csalAddTime,Short csalStatus){
		this.csalBankType=csalBankType;
		this.csalRecord=csalRecord;
		this.csalRefund=csalRefund;
		this.csalOther=csalOther;
		this.csalModelClass=csalModelClass;
		this.csalType=csalType;
		this.csalLog=csalLog;
		this.csalAddTime=csalAddTime;
		this.csalStatus=csalStatus;
	}
	
	//设置非空字段
	public CsAlipayLog setNotNull(Short csalBankType,Short csalType,Date csalAddTime,Short csalStatus){
		this.csalBankType=csalBankType;
		this.csalType=csalType;
		this.csalAddTime=csalAddTime;
		this.csalStatus=csalStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsAlipayLog csalId(Long csalId){
		this.csalId = csalId;
		this.setSeted(F.csalId);
		return this;
	}
	/** 接口类型 [非空]   0:支付宝 10:招商银行     **/
	public CsAlipayLog csalBankType(Short csalBankType){
		this.csalBankType = csalBankType;
		this.setSeted(F.csalBankType);
		return this;
	}
	/** 关联充值  [CsAlipayRecord]      **/
	public CsAlipayLog csalRecord(Long csalRecord){
		this.csalRecord = csalRecord;
		this.setSeted(F.csalRecord);
		return this;
	}
	/** 关联退款  [CsAlipayRefund]      **/
	public CsAlipayLog csalRefund(Long csalRefund){
		this.csalRefund = csalRefund;
		this.setSeted(F.csalRefund);
		return this;
	}
	/** 关联其它     比如订单号、会员ID   **/
	public CsAlipayLog csalOther(Long csalOther){
		this.csalOther = csalOther;
		this.setSeted(F.csalOther);
		return this;
	}
	/** 模型类名     比如CsOrder、CsMember   **/
	public CsAlipayLog csalModelClass(String csalModelClass){
		this.csalModelClass = csalModelClass;
		this.setSeted(F.csalModelClass);
		return this;
	}
	/** 类型 [非空]   0:进站 1:出站     **/
	public CsAlipayLog csalType(Short csalType){
		this.csalType = csalType;
		this.setSeted(F.csalType);
		return this;
	}
	/** 日志内容        **/
	public CsAlipayLog csalLog(String csalLog){
		this.csalLog = csalLog;
		this.setSeted(F.csalLog);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsAlipayLog csalAddTime(Date csalAddTime){
		this.csalAddTime = csalAddTime;
		this.setSeted(F.csalAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsAlipayLog csalStatus(Short csalStatus){
		this.csalStatus = csalStatus;
		this.setSeted(F.csalStatus);
		return this;
	}
	
	
	//克隆对象
	public CsAlipayLog clone(){
		CsAlipayLog clone = new CsAlipayLog();
		clone.csalBankType=this.csalBankType;
		clone.csalType=this.csalType;
		clone.csalAddTime=this.csalAddTime;
		clone.csalStatus=this.csalStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取接口日志
	 * @param id
	 * @return
	 */
	public static @api CsAlipayLog get(Long id){		
		return getCsAlipayLogById(id);
	}
	/**
	 * 获取所有接口日志
	 * @return
	 */
	public static @api List<CsAlipayLog> list(Map params,Integer size){
		return getCsAlipayLogList(params,size);
	}
	/**
	 * 获取接口日志分页
	 * @return
	 */
	public static @api Page<CsAlipayLog> page(int page,int size,Map params){
		return getCsAlipayLogPage(page, size , params);
	}
	/**
	 * 根据查询条件取接口日志
	 * @param params
	 * @return
	 */
	public static @api CsAlipayLog Get(Map params){
		return getCsAlipayLog(params);
	}
	/**
	 * 获取接口日志数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsAlipayLogCount(params);
	}
	/**
	 * 获取接口日志数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsAlipayLogEval(eval,params);
	}
	
	/**
	 * 根据ID取接口日志
	 * @param id
	 * @return
	 */
	public static @api CsAlipayLog getCsAlipayLogById(Long id){		
		CsAlipayLog csAlipayLog = (CsAlipayLog) $.GetRequest("CsAlipayLog$"+id);
		if(csAlipayLog!=null)
			return csAlipayLog;
		com.ccclubs.service.admin.ICsAlipayLogService csAlipayLogService = $.GetSpringBean("csAlipayLogService");		
		return csAlipayLogService.getCsAlipayLogById(id);
	}
	
	
	/**
	 * 根据ID取接口日志的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsAlipayLog.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsAlipayLog csAlipayLog = get(id);
		if(csAlipayLog!=null){
			String strValue = csAlipayLog.getCsalId$();
			if(!"CsalId".equals("CsalId"))
				strValue+="("+csAlipayLog.getCsalId$()+")";
			MemCache.setValue(CsAlipayLog.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsalId$();
		if(!"CsalId".equals("CsalId"))
			keyValue+="("+this.getCsalId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有接口日志
	 * @return
	 */
	public static @api List<CsAlipayLog> getCsAlipayLogList(Map params,Integer size){
		com.ccclubs.service.admin.ICsAlipayLogService csAlipayLogService = $.GetSpringBean("csAlipayLogService");
		return csAlipayLogService.getCsAlipayLogList(params, size);
	}
	
	/**
	 * 获取接口日志分页
	 * @return
	 */
	public static @api Page<CsAlipayLog> getCsAlipayLogPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsAlipayLogService csAlipayLogService = $.GetSpringBean("csAlipayLogService");
		return csAlipayLogService.getCsAlipayLogPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取接口日志
	 * @param params
	 * @return
	 */
	public static @api CsAlipayLog getCsAlipayLog(Map params){
		com.ccclubs.service.admin.ICsAlipayLogService csAlipayLogService = $.GetSpringBean("csAlipayLogService");
		return csAlipayLogService.getCsAlipayLog(params);
	}
	
	/**
	 * 获取接口日志数
	 * @return
	 */
	public static @api Long getCsAlipayLogCount(Map params){
		com.ccclubs.service.admin.ICsAlipayLogService csAlipayLogService = $.GetSpringBean("csAlipayLogService");
		return csAlipayLogService.getCsAlipayLogCount(params);
	}
		
		
	/**
	 * 获取接口日志自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsAlipayLogEval(String eval,Map params){
		com.ccclubs.service.admin.ICsAlipayLogService csAlipayLogService = $.GetSpringBean("csAlipayLogService");
		return csAlipayLogService.getCsAlipayLogEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsAlipayLog(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsAlipayLogService csAlipayLogService = $.GetSpringBean("csAlipayLogService");
		csAlipayLogService.updateCsAlipayLogByConfirm(set, where);
	}
	
	
	/**
	 * 保存接口日志对象
	 * @param params
	 * @return
	 */
	public CsAlipayLog save(){
		com.ccclubs.service.admin.ICsAlipayLogService csAlipayLogService = $.GetSpringBean("csAlipayLogService");
		if(this.getCsalId()!=null)
			csAlipayLogService.updateCsAlipayLog(this);
		else
			return csAlipayLogService.saveCsAlipayLog(this);
		return this;
	}
	
	
	/**
	 * 更新接口日志对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsAlipayLogService csAlipayLogService = $.GetSpringBean("csAlipayLogService");
		csAlipayLogService.updateCsAlipayLog$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsAlipayLogService csAlipayLogService = $.GetSpringBean("csAlipayLogService");
		if($.equals($.config("logic_delete"),"true"))
			csAlipayLogService.removeCsAlipayLogById(this.getCsalId());
		else
			csAlipayLogService.deleteCsAlipayLogById(this.getCsalId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsAlipayLogService csAlipayLogService = $.GetSpringBean("csAlipayLogService");
		return csAlipayLogService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsalId(){
		return this.csalId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsalId$(){
		String strValue="";
		 strValue=$.str(this.getCsalId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsalId(Long csalId){
		this.csalId = csalId;
		this.setSeted(F.csalId);
	}
	/*******************************接口类型**********************************/	
	/**
	* 接口类型 [非空]   0:支付宝 10:招商银行    
	**/
	public Short getCsalBankType(){
		return this.csalBankType;
	}
	/**
	* 获取接口类型格式化(toString)
	**/
	public String getCsalBankType$(){
		String strValue="";
		 if($.equals($.str(this.getCsalBankType()),"0"))
			strValue=$.str("支付宝");		 
		 if($.equals($.str(this.getCsalBankType()),"10"))
			strValue=$.str("招商银行");		 
	 	 return strValue;
	}
	/**
	* 接口类型 [非空]   0:支付宝 10:招商银行    
	**/
	public void setCsalBankType(Short csalBankType){
		this.csalBankType = csalBankType;
		this.setSeted(F.csalBankType);
	}
	/*******************************关联充值**********************************/	
	/**
	* 关联充值  [CsAlipayRecord]     
	**/
	public Long getCsalRecord(){
		return this.csalRecord;
	}
	/**
	* 获取关联充值格式化(toString)
	**/
	public String getCsalRecord$(){
		String strValue="";
		if(this.getCsalRecord()!=null){
				strValue+=$.str(CsAlipayRecord.getKeyValue(this.getCsalRecord()));
		}			
	 	 return strValue;
	}
	/**
	* 关联充值  [CsAlipayRecord]     
	**/
	public void setCsalRecord(Long csalRecord){
		this.csalRecord = csalRecord;
		this.setSeted(F.csalRecord);
	}
	/**
	* 获取关联对象[接口充值]
	**/	 			
 	public CsAlipayRecord get$csalRecord(){
 		com.ccclubs.model.CsAlipayRecord csAlipayRecord = (com.ccclubs.model.CsAlipayRecord) $.GetRequest("CsAlipayRecord$"+this.getCsalRecord());
 		if(csAlipayRecord!=null)
			return csAlipayRecord;
		if(this.getCsalRecord()!=null){
 			csAlipayRecord = CsAlipayRecord.get(this.getCsalRecord());
 		}
 		$.SetRequest("CsAlipayRecord$"+this.getCsalRecord(), csAlipayRecord);
	 	return csAlipayRecord;
	}
	/*******************************关联退款**********************************/	
	/**
	* 关联退款  [CsAlipayRefund]     
	**/
	public Long getCsalRefund(){
		return this.csalRefund;
	}
	/**
	* 获取关联退款格式化(toString)
	**/
	public String getCsalRefund$(){
		String strValue="";
		if(this.getCsalRefund()!=null){
				strValue+=$.str(CsAlipayRefund.getKeyValue(this.getCsalRefund()));
		}			
	 	 return strValue;
	}
	/**
	* 关联退款  [CsAlipayRefund]     
	**/
	public void setCsalRefund(Long csalRefund){
		this.csalRefund = csalRefund;
		this.setSeted(F.csalRefund);
	}
	/**
	* 获取关联对象[接口退款]
	**/	 			
 	public CsAlipayRefund get$csalRefund(){
 		com.ccclubs.model.CsAlipayRefund csAlipayRefund = (com.ccclubs.model.CsAlipayRefund) $.GetRequest("CsAlipayRefund$"+this.getCsalRefund());
 		if(csAlipayRefund!=null)
			return csAlipayRefund;
		if(this.getCsalRefund()!=null){
 			csAlipayRefund = CsAlipayRefund.get(this.getCsalRefund());
 		}
 		$.SetRequest("CsAlipayRefund$"+this.getCsalRefund(), csAlipayRefund);
	 	return csAlipayRefund;
	}
	/*******************************关联其它**********************************/	
	/**
	* 关联其它     比如订单号、会员ID  
	**/
	public Long getCsalOther(){
		return this.csalOther;
	}
	/**
	* 获取关联其它格式化(toString)
	**/
	public String getCsalOther$(){
		String strValue="";
		 strValue=$.str(this.getCsalOther());
	 	 return strValue;
	}
	/**
	* 关联其它     比如订单号、会员ID  
	**/
	public void setCsalOther(Long csalOther){
		this.csalOther = csalOther;
		this.setSeted(F.csalOther);
	}
	/*******************************模型类名**********************************/	
	/**
	* 模型类名     比如CsOrder、CsMember  
	**/
	public String getCsalModelClass(){
		return this.csalModelClass;
	}
	/**
	* 获取模型类名格式化(toString)
	**/
	public String getCsalModelClass$(){
		String strValue="";
		 strValue=$.str(this.getCsalModelClass());
	 	 return strValue;
	}
	/**
	* 模型类名     比如CsOrder、CsMember  
	**/
	public void setCsalModelClass(String csalModelClass){
		this.csalModelClass = csalModelClass;
		this.setSeted(F.csalModelClass);
	}
	/*******************************类型**********************************/	
	/**
	* 类型 [非空]   0:进站 1:出站    
	**/
	public Short getCsalType(){
		return this.csalType;
	}
	/**
	* 获取类型格式化(toString)
	**/
	public String getCsalType$(){
		String strValue="";
		 if($.equals($.str(this.getCsalType()),"0"))
			strValue=$.str("进站");		 
		 if($.equals($.str(this.getCsalType()),"1"))
			strValue=$.str("出站");		 
	 	 return strValue;
	}
	/**
	* 类型 [非空]   0:进站 1:出站    
	**/
	public void setCsalType(Short csalType){
		this.csalType = csalType;
		this.setSeted(F.csalType);
	}
	/*******************************日志内容**********************************/	
	/**
	* 日志内容       
	**/
	public String getCsalLog(){
		return this.csalLog;
	}
	/**
	* 获取日志内容格式化(toString)
	**/
	public String getCsalLog$(){
		String strValue="";
		 strValue=$.str(this.getCsalLog());
	 	 return strValue;
	}
	/**
	* 日志内容       
	**/
	public void setCsalLog(String csalLog){
		this.csalLog = csalLog;
		this.setSeted(F.csalLog);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsalAddTime(){
		return this.csalAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsalAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsalAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsalAddTime(Date csalAddTime){
		this.csalAddTime = csalAddTime;
		this.setSeted(F.csalAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsalStatus(){
		return this.csalStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsalStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsalStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsalStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsalStatus(Short csalStatus){
		this.csalStatus = csalStatus;
		this.setSeted(F.csalStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsAlipayLog.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsAlipayLog.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsAlipayLog.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsAlipayLog.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsAlipayLog.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsAlipayLog.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsAlipayLog.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsAlipayLog.this);
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
		public M csalId(Object csalId){this.put("csalId", csalId);return this;};
	 	/** and csal_id is null */
 		public M csalIdNull(){if(this.get("csalIdNot")==null)this.put("csalIdNot", "");this.put("csalId", null);return this;};
 		/** not .... */
 		public M csalIdNot(){this.put("csalIdNot", "not");return this;};
		/** 接口类型 [非空]   0:支付宝 10:招商银行     **/
		public M csalBankType(Object csalBankType){this.put("csalBankType", csalBankType);return this;};
	 	/** and csal_bank_type is null */
 		public M csalBankTypeNull(){if(this.get("csalBankTypeNot")==null)this.put("csalBankTypeNot", "");this.put("csalBankType", null);return this;};
 		/** not .... */
 		public M csalBankTypeNot(){this.put("csalBankTypeNot", "not");return this;};
		/** 关联充值  [CsAlipayRecord]      **/
		public M csalRecord(Object csalRecord){this.put("csalRecord", csalRecord);return this;};
	 	/** and csal_record is null */
 		public M csalRecordNull(){if(this.get("csalRecordNot")==null)this.put("csalRecordNot", "");this.put("csalRecord", null);return this;};
 		/** not .... */
 		public M csalRecordNot(){this.put("csalRecordNot", "not");return this;};
		public M csalRecord$on(CsAlipayRecord.M value){
			this.put("CsAlipayRecord", value);
			this.put("csalRecord$on", value);
			return this;
		};	
		/** 关联退款  [CsAlipayRefund]      **/
		public M csalRefund(Object csalRefund){this.put("csalRefund", csalRefund);return this;};
	 	/** and csal_refund is null */
 		public M csalRefundNull(){if(this.get("csalRefundNot")==null)this.put("csalRefundNot", "");this.put("csalRefund", null);return this;};
 		/** not .... */
 		public M csalRefundNot(){this.put("csalRefundNot", "not");return this;};
		public M csalRefund$on(CsAlipayRefund.M value){
			this.put("CsAlipayRefund", value);
			this.put("csalRefund$on", value);
			return this;
		};	
		/** 关联其它     比如订单号、会员ID   **/
		public M csalOther(Object csalOther){this.put("csalOther", csalOther);return this;};
	 	/** and csal_other is null */
 		public M csalOtherNull(){if(this.get("csalOtherNot")==null)this.put("csalOtherNot", "");this.put("csalOther", null);return this;};
 		/** not .... */
 		public M csalOtherNot(){this.put("csalOtherNot", "not");return this;};
		/** and csal_other >= ? */
		public M csalOtherMin(Object min){this.put("csalOtherMin", min);return this;};
		/** and csal_other <= ? */
		public M csalOtherMax(Object max){this.put("csalOtherMax", max);return this;};
		/** 模型类名     比如CsOrder、CsMember   **/
		public M csalModelClass(Object csalModelClass){this.put("csalModelClass", csalModelClass);return this;};
	 	/** and csal_model_class is null */
 		public M csalModelClassNull(){if(this.get("csalModelClassNot")==null)this.put("csalModelClassNot", "");this.put("csalModelClass", null);return this;};
 		/** not .... */
 		public M csalModelClassNot(){this.put("csalModelClassNot", "not");return this;};
		/** 类型 [非空]   0:进站 1:出站     **/
		public M csalType(Object csalType){this.put("csalType", csalType);return this;};
	 	/** and csal_type is null */
 		public M csalTypeNull(){if(this.get("csalTypeNot")==null)this.put("csalTypeNot", "");this.put("csalType", null);return this;};
 		/** not .... */
 		public M csalTypeNot(){this.put("csalTypeNot", "not");return this;};
		/** 日志内容        **/
		public M csalLog(Object csalLog){this.put("csalLog", csalLog);return this;};
	 	/** and csal_log is null */
 		public M csalLogNull(){if(this.get("csalLogNot")==null)this.put("csalLogNot", "");this.put("csalLog", null);return this;};
 		/** not .... */
 		public M csalLogNot(){this.put("csalLogNot", "not");return this;};
		/** 添加时间 [非空]       **/
		public M csalAddTime(Object csalAddTime){this.put("csalAddTime", csalAddTime);return this;};
	 	/** and csal_add_time is null */
 		public M csalAddTimeNull(){if(this.get("csalAddTimeNot")==null)this.put("csalAddTimeNot", "");this.put("csalAddTime", null);return this;};
 		/** not .... */
 		public M csalAddTimeNot(){this.put("csalAddTimeNot", "not");return this;};
 		/** and csal_add_time >= ? */
 		public M csalAddTimeStart(Object start){this.put("csalAddTimeStart", start);return this;};			
 		/** and csal_add_time <= ? */
 		public M csalAddTimeEnd(Object end){this.put("csalAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csalStatus(Object csalStatus){this.put("csalStatus", csalStatus);return this;};
	 	/** and csal_status is null */
 		public M csalStatusNull(){if(this.get("csalStatusNot")==null)this.put("csalStatusNot", "");this.put("csalStatus", null);return this;};
 		/** not .... */
 		public M csalStatusNot(){this.put("csalStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有接口日志 **/
		public @api List<CsAlipayLog> list(Integer size){
			return getCsAlipayLogList(this,size);
		}
		/** 获取接口日志分页 **/
		public @api Page<CsAlipayLog> page(int page,int size){
			return getCsAlipayLogPage(page, size , this);
		}
		/** 根据查询条件取接口日志 **/
		public @api CsAlipayLog get(){
			return getCsAlipayLog(this);
		}
		/** 获取接口日志数 **/
		public @api Long count(){
			return getCsAlipayLogCount(this);
		}
		/** 获取接口日志表达式 **/
		public @api <T> T eval(String strEval){
			return getCsAlipayLogEval(strEval,this);
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
			updateCsAlipayLog(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csalId="csalId";
		/** 接口类型 [非空]   0:支付宝 10:招商银行     **/
		public final static @type(Short.class)  String csalBankType="csalBankType";
		/** 关联充值  [CsAlipayRecord]      **/
		public final static @type(Long.class)  String csalRecord="csalRecord";
		/** 关联退款  [CsAlipayRefund]      **/
		public final static @type(Long.class)  String csalRefund="csalRefund";
		/** 关联其它     比如订单号、会员ID   **/
		public final static @type(Long.class)  String csalOther="csalOther";
		/** and csal_other >= ? */
		public final static @type(Long.class) String csalOtherMin="csalOtherMin";
		/** and csal_other <= ? */
		public final static @type(Long.class) String csalOtherMax="csalOtherMax";
		/** 模型类名     比如CsOrder、CsMember   **/
		public final static @type(String.class) @like String csalModelClass="csalModelClass";
		/** 类型 [非空]   0:进站 1:出站     **/
		public final static @type(Short.class)  String csalType="csalType";
		/** 日志内容        **/
		public final static @type(String.class) @like String csalLog="csalLog";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csalAddTime="csalAddTime";
	 	/** and csal_add_time >= ? */
 		public final static @type(Date.class) String csalAddTimeStart="csalAddTimeStart";
 		/** and csal_add_time <= ? */
 		public final static @type(Date.class) String csalAddTimeEnd="csalAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csalStatus="csalStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csalId="csal_id";
		/** 接口类型 [非空]   0:支付宝 10:招商银行     **/
		public final static String csalBankType="csal_bank_type";
		/** 关联充值  [CsAlipayRecord]      **/
		public final static String csalRecord="csal_record";
		/** 关联退款  [CsAlipayRefund]      **/
		public final static String csalRefund="csal_refund";
		/** 关联其它     比如订单号、会员ID   **/
		public final static String csalOther="csal_other";
		/** 模型类名     比如CsOrder、CsMember   **/
		public final static String csalModelClass="csal_model_class";
		/** 类型 [非空]   0:进站 1:出站     **/
		public final static String csalType="csal_type";
		/** 日志内容        **/
		public final static String csalLog="csal_log";
		/** 添加时间 [非空]       **/
		public final static String csalAddTime="csal_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csalStatus="csal_status";
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
				$.Set(name,CsAlipayLog.getCsAlipayLog(params));
			else
				$.Set(name,CsAlipayLog.getCsAlipayLogList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取接口日志数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsAlipayLog) $.GetRequest("CsAlipayLog$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsAlipayLog.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsAlipayLog.getCsAlipayLog(params);
			else
				value = CsAlipayLog.getCsAlipayLogList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsAlipayLog.Get($.add(CsAlipayLog.F.csalId,param));
		else if(!$.empty(param.toString()))
			value = CsAlipayLog.get(Long.valueOf(param.toString()));
		$.SetRequest("CsAlipayLog$"+param.hashCode(), value);
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
	public void mergeSet(CsAlipayLog old){
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