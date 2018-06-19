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

@namespace("systematic/notice")
public @caption("系统通知") @table("cs_notice") class CsNotice implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csn_id")   @primary  @note("  ") Long csnId;// 主键 非空     
	private @caption("通知内容") @column("csn_content")    @note("  ") String csnContent;// 非空     
	private @caption("提醒时间") @column("csn_alarm_time")    @note("  ") Date csnAlarmTime;//     
	private @caption("处理地址") @column("csn_url")    @note("  ") String csnUrl;//     
	private @caption("标识") @column("csn_flag")    @note("  ") String csnFlag;//     
	private @caption("发送人") @column("csn_sender")    @relate("$csnSender") @RelateClass(SrvUser.class)  @note("  ") Long csnSender;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csnSender;//关联对象[用户]
	private @caption("接收人") @column("csn_receiver")    @relate("$csnReceiver") @RelateClass(SrvUser.class)  @note("  ") Long csnReceiver;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csnReceiver;//关联对象[用户]
	private @caption("处理描述") @column("csn_remark")    @note("  ") String csnRemark;//     
	private @caption("处理日志") @column("csn_log")    @note("  ") String csnLog;//     
	private @caption("添加时间") @column("csn_add_time")    @note("  ") Date csnAddTime;// 非空     
	private @caption("处理时间") @column("csn_deal_time")    @note("  ") Date csnDealTime;//     
	private @caption("状态") @column("csn_status")    @note(" 0:待处理 1:提醒报警 2:已处理  ") Short csnStatus;// 非空 0:待处理 1:提醒报警 2:已处理     
	
	//默认构造函数
	public CsNotice(){
	
	}
	
	//主键构造函数
	public CsNotice(Long id){
		this.csnId = id;
	}
	
	/**所有字段构造函数 CsNotice(csnContent,csnAlarmTime,csnUrl,csnFlag,csnSender,csnReceiver,csnRemark,csnLog,csnAddTime,csnDealTime,csnStatus)
	 CsNotice(
	 	$.getString("csnContent")//通知内容 [非空]
	 	,$.getDate("csnAlarmTime")//提醒时间
	 	,$.getString("csnUrl")//处理地址
	 	,$.getString("csnFlag")//标识
	 	,$.getLong("csnSender")//发送人 [非空]
	 	,$.getLong("csnReceiver")//接收人 [非空]
	 	,$.getString("csnRemark")//处理描述
	 	,$.getString("csnLog")//处理日志
	 	,$.getDate("csnAddTime")//添加时间 [非空]
	 	,$.getDate("csnDealTime")//处理时间
	 	,$.getShort("csnStatus")//状态 [非空]
	 )
	**/
	public CsNotice(String csnContent,Date csnAlarmTime,String csnUrl,String csnFlag,Long csnSender,Long csnReceiver,String csnRemark,String csnLog,Date csnAddTime,Date csnDealTime,Short csnStatus){
		this.csnContent=csnContent;
		this.csnAlarmTime=csnAlarmTime;
		this.csnUrl=csnUrl;
		this.csnFlag=csnFlag;
		this.csnSender=csnSender;
		this.csnReceiver=csnReceiver;
		this.csnRemark=csnRemark;
		this.csnLog=csnLog;
		this.csnAddTime=csnAddTime;
		this.csnDealTime=csnDealTime;
		this.csnStatus=csnStatus;
	}
	
	//设置非空字段
	public CsNotice setNotNull(String csnContent,Long csnSender,Long csnReceiver,Date csnAddTime,Short csnStatus){
		this.csnContent=csnContent;
		this.csnSender=csnSender;
		this.csnReceiver=csnReceiver;
		this.csnAddTime=csnAddTime;
		this.csnStatus=csnStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsNotice csnId(Long csnId){
		this.csnId = csnId;
		this.setSeted(F.csnId);
		return this;
	}
	/** 通知内容 [非空]       **/
	public CsNotice csnContent(String csnContent){
		this.csnContent = csnContent;
		this.setSeted(F.csnContent);
		return this;
	}
	/** 提醒时间        **/
	public CsNotice csnAlarmTime(Date csnAlarmTime){
		this.csnAlarmTime = csnAlarmTime;
		this.setSeted(F.csnAlarmTime);
		return this;
	}
	/** 处理地址        **/
	public CsNotice csnUrl(String csnUrl){
		this.csnUrl = csnUrl;
		this.setSeted(F.csnUrl);
		return this;
	}
	/** 标识        **/
	public CsNotice csnFlag(String csnFlag){
		this.csnFlag = csnFlag;
		this.setSeted(F.csnFlag);
		return this;
	}
	/** 发送人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsNotice csnSender(Long csnSender){
		this.csnSender = csnSender;
		this.setSeted(F.csnSender);
		return this;
	}
	/** 接收人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsNotice csnReceiver(Long csnReceiver){
		this.csnReceiver = csnReceiver;
		this.setSeted(F.csnReceiver);
		return this;
	}
	/** 处理描述        **/
	public CsNotice csnRemark(String csnRemark){
		this.csnRemark = csnRemark;
		this.setSeted(F.csnRemark);
		return this;
	}
	/** 处理日志        **/
	public CsNotice csnLog(String csnLog){
		this.csnLog = csnLog;
		this.setSeted(F.csnLog);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsNotice csnAddTime(Date csnAddTime){
		this.csnAddTime = csnAddTime;
		this.setSeted(F.csnAddTime);
		return this;
	}
	/** 处理时间        **/
	public CsNotice csnDealTime(Date csnDealTime){
		this.csnDealTime = csnDealTime;
		this.setSeted(F.csnDealTime);
		return this;
	}
	/** 状态 [非空]   0:待处理 1:提醒报警 2:已处理     **/
	public CsNotice csnStatus(Short csnStatus){
		this.csnStatus = csnStatus;
		this.setSeted(F.csnStatus);
		return this;
	}
	
	
	//克隆对象
	public CsNotice clone(){
		CsNotice clone = new CsNotice();
		clone.csnContent=this.csnContent;
		clone.csnSender=this.csnSender;
		clone.csnReceiver=this.csnReceiver;
		clone.csnAddTime=this.csnAddTime;
		clone.csnStatus=this.csnStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取系统通知
	 * @param id
	 * @return
	 */
	public static @api CsNotice get(Long id){		
		return getCsNoticeById(id);
	}
	/**
	 * 获取所有系统通知
	 * @return
	 */
	public static @api List<CsNotice> list(Map params,Integer size){
		return getCsNoticeList(params,size);
	}
	/**
	 * 获取系统通知分页
	 * @return
	 */
	public static @api Page<CsNotice> page(int page,int size,Map params){
		return getCsNoticePage(page, size , params);
	}
	/**
	 * 根据查询条件取系统通知
	 * @param params
	 * @return
	 */
	public static @api CsNotice Get(Map params){
		return getCsNotice(params);
	}
	/**
	 * 获取系统通知数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsNoticeCount(params);
	}
	/**
	 * 获取系统通知数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsNoticeEval(eval,params);
	}
	
	/**
	 * 根据ID取系统通知
	 * @param id
	 * @return
	 */
	public static @api CsNotice getCsNoticeById(Long id){		
		CsNotice csNotice = (CsNotice) $.GetRequest("CsNotice$"+id);
		if(csNotice!=null)
			return csNotice;
		com.ccclubs.service.admin.ICsNoticeService csNoticeService = $.GetSpringBean("csNoticeService");		
		return csNoticeService.getCsNoticeById(id);
	}
	
	
	/**
	 * 根据ID取系统通知的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsNotice.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsNotice csNotice = get(id);
		if(csNotice!=null){
			String strValue = csNotice.getCsnContent$();
			if(!"CsnContent".equals("CsnContent"))
				strValue+="("+csNotice.getCsnContent$()+")";
			MemCache.setValue(CsNotice.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsnContent$();
		if(!"CsnContent".equals("CsnContent"))
			keyValue+="("+this.getCsnContent$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有系统通知
	 * @return
	 */
	public static @api List<CsNotice> getCsNoticeList(Map params,Integer size){
		com.ccclubs.service.admin.ICsNoticeService csNoticeService = $.GetSpringBean("csNoticeService");
		return csNoticeService.getCsNoticeList(params, size);
	}
	
	/**
	 * 获取系统通知分页
	 * @return
	 */
	public static @api Page<CsNotice> getCsNoticePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsNoticeService csNoticeService = $.GetSpringBean("csNoticeService");
		return csNoticeService.getCsNoticePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取系统通知
	 * @param params
	 * @return
	 */
	public static @api CsNotice getCsNotice(Map params){
		com.ccclubs.service.admin.ICsNoticeService csNoticeService = $.GetSpringBean("csNoticeService");
		return csNoticeService.getCsNotice(params);
	}
	
	/**
	 * 获取系统通知数
	 * @return
	 */
	public static @api Long getCsNoticeCount(Map params){
		com.ccclubs.service.admin.ICsNoticeService csNoticeService = $.GetSpringBean("csNoticeService");
		return csNoticeService.getCsNoticeCount(params);
	}
		
		
	/**
	 * 获取系统通知自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsNoticeEval(String eval,Map params){
		com.ccclubs.service.admin.ICsNoticeService csNoticeService = $.GetSpringBean("csNoticeService");
		return csNoticeService.getCsNoticeEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsNotice(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsNoticeService csNoticeService = $.GetSpringBean("csNoticeService");
		csNoticeService.updateCsNoticeByConfirm(set, where);
	}
	
	
	/**
	 * 保存系统通知对象
	 * @param params
	 * @return
	 */
	public CsNotice save(){
		com.ccclubs.service.admin.ICsNoticeService csNoticeService = $.GetSpringBean("csNoticeService");
		if(this.getCsnId()!=null)
			csNoticeService.updateCsNotice(this);
		else
			return csNoticeService.saveCsNotice(this);
		return this;
	}
	
	
	/**
	 * 更新系统通知对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsNoticeService csNoticeService = $.GetSpringBean("csNoticeService");
		csNoticeService.updateCsNotice$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsNoticeService csNoticeService = $.GetSpringBean("csNoticeService");
		if($.equals($.config("logic_delete"),"true"))
			csNoticeService.removeCsNoticeById(this.getCsnId());
		else
			csNoticeService.deleteCsNoticeById(this.getCsnId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsNoticeService csNoticeService = $.GetSpringBean("csNoticeService");
		return csNoticeService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsnId(){
		return this.csnId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsnId$(){
		String strValue="";
		 strValue=$.str(this.getCsnId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsnId(Long csnId){
		this.csnId = csnId;
		this.setSeted(F.csnId);
	}
	/*******************************通知内容**********************************/	
	/**
	* 通知内容 [非空]      
	**/
	public String getCsnContent(){
		return this.csnContent;
	}
	/**
	* 获取通知内容格式化(toString)
	**/
	public String getCsnContent$(){
		String strValue="";
		 strValue=$.str(this.getCsnContent());
	 	 return strValue;
	}
	/**
	* 通知内容 [非空]      
	**/
	public void setCsnContent(String csnContent){
		this.csnContent = csnContent;
		this.setSeted(F.csnContent);
	}
	/*******************************提醒时间**********************************/	
	/**
	* 提醒时间       
	**/
	public Date getCsnAlarmTime(){
		return this.csnAlarmTime;
	}
	/**
	* 获取提醒时间格式化(toString)
	**/
	public String getCsnAlarmTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsnAlarmTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 提醒时间       
	**/
	public void setCsnAlarmTime(Date csnAlarmTime){
		this.csnAlarmTime = csnAlarmTime;
		this.setSeted(F.csnAlarmTime);
	}
	/*******************************处理地址**********************************/	
	/**
	* 处理地址       
	**/
	public String getCsnUrl(){
		return this.csnUrl;
	}
	/**
	* 获取处理地址格式化(toString)
	**/
	public String getCsnUrl$(){
		String strValue="";
		 strValue=$.str(this.getCsnUrl());
	 	 return strValue;
	}
	/**
	* 处理地址       
	**/
	public void setCsnUrl(String csnUrl){
		this.csnUrl = csnUrl;
		this.setSeted(F.csnUrl);
	}
	/*******************************标识**********************************/	
	/**
	* 标识       
	**/
	public String getCsnFlag(){
		return this.csnFlag;
	}
	/**
	* 获取标识格式化(toString)
	**/
	public String getCsnFlag$(){
		String strValue="";
		 strValue=$.str(this.getCsnFlag());
	 	 return strValue;
	}
	/**
	* 标识       
	**/
	public void setCsnFlag(String csnFlag){
		this.csnFlag = csnFlag;
		this.setSeted(F.csnFlag);
	}
	/*******************************发送人**********************************/	
	/**
	* 发送人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsnSender(){
		return this.csnSender;
	}
	/**
	* 获取发送人格式化(toString)
	**/
	public String getCsnSender$(){
		String strValue="";
		if(this.getCsnSender()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsnSender()));
		}			
	 	 return strValue;
	}
	/**
	* 发送人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsnSender(Long csnSender){
		this.csnSender = csnSender;
		this.setSeted(F.csnSender);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csnSender(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsnSender());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsnSender()!=null){
 			srvUser = SrvUser.get(this.getCsnSender());
 		}
 		$.SetRequest("SrvUser$"+this.getCsnSender(), srvUser);
	 	return srvUser;
	}
	/*******************************接收人**********************************/	
	/**
	* 接收人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsnReceiver(){
		return this.csnReceiver;
	}
	/**
	* 获取接收人格式化(toString)
	**/
	public String getCsnReceiver$(){
		String strValue="";
		if(this.getCsnReceiver()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsnReceiver()));
		}			
	 	 return strValue;
	}
	/**
	* 接收人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsnReceiver(Long csnReceiver){
		this.csnReceiver = csnReceiver;
		this.setSeted(F.csnReceiver);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csnReceiver(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsnReceiver());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsnReceiver()!=null){
 			srvUser = SrvUser.get(this.getCsnReceiver());
 		}
 		$.SetRequest("SrvUser$"+this.getCsnReceiver(), srvUser);
	 	return srvUser;
	}
	/*******************************处理描述**********************************/	
	/**
	* 处理描述       
	**/
	public String getCsnRemark(){
		return this.csnRemark;
	}
	/**
	* 获取处理描述格式化(toString)
	**/
	public String getCsnRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsnRemark());
	 	 return strValue;
	}
	/**
	* 处理描述       
	**/
	public void setCsnRemark(String csnRemark){
		this.csnRemark = csnRemark;
		this.setSeted(F.csnRemark);
	}
	/*******************************处理日志**********************************/	
	/**
	* 处理日志       
	**/
	public String getCsnLog(){
		return this.csnLog;
	}
	/**
	* 获取处理日志格式化(toString)
	**/
	public String getCsnLog$(){
		String strValue="";
		 strValue=$.str(this.getCsnLog());
	 	 return strValue;
	}
	/**
	* 处理日志       
	**/
	public void setCsnLog(String csnLog){
		this.csnLog = csnLog;
		this.setSeted(F.csnLog);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsnAddTime(){
		return this.csnAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsnAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsnAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsnAddTime(Date csnAddTime){
		this.csnAddTime = csnAddTime;
		this.setSeted(F.csnAddTime);
	}
	/*******************************处理时间**********************************/	
	/**
	* 处理时间       
	**/
	public Date getCsnDealTime(){
		return this.csnDealTime;
	}
	/**
	* 获取处理时间格式化(toString)
	**/
	public String getCsnDealTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsnDealTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 处理时间       
	**/
	public void setCsnDealTime(Date csnDealTime){
		this.csnDealTime = csnDealTime;
		this.setSeted(F.csnDealTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:待处理 1:提醒报警 2:已处理    
	**/
	public Short getCsnStatus(){
		return this.csnStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsnStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsnStatus()),"0"))
			strValue=$.str("待处理");		 
		 if($.equals($.str(this.getCsnStatus()),"1"))
			strValue=$.str("提醒报警");		 
		 if($.equals($.str(this.getCsnStatus()),"2"))
			strValue=$.str("已处理");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:待处理 1:提醒报警 2:已处理    
	**/
	public void setCsnStatus(Short csnStatus){
		this.csnStatus = csnStatus;
		this.setSeted(F.csnStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsNotice.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsNotice.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsNotice.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsNotice.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsNotice.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsNotice.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsNotice.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsNotice.this);
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
		public M csnId(Object csnId){this.put("csnId", csnId);return this;};
	 	/** and csn_id is null */
 		public M csnIdNull(){if(this.get("csnIdNot")==null)this.put("csnIdNot", "");this.put("csnId", null);return this;};
 		/** not .... */
 		public M csnIdNot(){this.put("csnIdNot", "not");return this;};
		/** 通知内容 [非空]       **/
		public M csnContent(Object csnContent){this.put("csnContent", csnContent);return this;};
	 	/** and csn_content is null */
 		public M csnContentNull(){if(this.get("csnContentNot")==null)this.put("csnContentNot", "");this.put("csnContent", null);return this;};
 		/** not .... */
 		public M csnContentNot(){this.put("csnContentNot", "not");return this;};
		/** 提醒时间        **/
		public M csnAlarmTime(Object csnAlarmTime){this.put("csnAlarmTime", csnAlarmTime);return this;};
	 	/** and csn_alarm_time is null */
 		public M csnAlarmTimeNull(){if(this.get("csnAlarmTimeNot")==null)this.put("csnAlarmTimeNot", "");this.put("csnAlarmTime", null);return this;};
 		/** not .... */
 		public M csnAlarmTimeNot(){this.put("csnAlarmTimeNot", "not");return this;};
 		/** and csn_alarm_time >= ? */
 		public M csnAlarmTimeStart(Object start){this.put("csnAlarmTimeStart", start);return this;};			
 		/** and csn_alarm_time <= ? */
 		public M csnAlarmTimeEnd(Object end){this.put("csnAlarmTimeEnd", end);return this;};
		/** 处理地址        **/
		public M csnUrl(Object csnUrl){this.put("csnUrl", csnUrl);return this;};
	 	/** and csn_url is null */
 		public M csnUrlNull(){if(this.get("csnUrlNot")==null)this.put("csnUrlNot", "");this.put("csnUrl", null);return this;};
 		/** not .... */
 		public M csnUrlNot(){this.put("csnUrlNot", "not");return this;};
		/** 标识        **/
		public M csnFlag(Object csnFlag){this.put("csnFlag", csnFlag);return this;};
	 	/** and csn_flag is null */
 		public M csnFlagNull(){if(this.get("csnFlagNot")==null)this.put("csnFlagNot", "");this.put("csnFlag", null);return this;};
 		/** not .... */
 		public M csnFlagNot(){this.put("csnFlagNot", "not");return this;};
		/** 发送人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csnSender(Object csnSender){this.put("csnSender", csnSender);return this;};
	 	/** and csn_sender is null */
 		public M csnSenderNull(){if(this.get("csnSenderNot")==null)this.put("csnSenderNot", "");this.put("csnSender", null);return this;};
 		/** not .... */
 		public M csnSenderNot(){this.put("csnSenderNot", "not");return this;};
		/** 接收人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csnReceiver(Object csnReceiver){this.put("csnReceiver", csnReceiver);return this;};
	 	/** and csn_receiver is null */
 		public M csnReceiverNull(){if(this.get("csnReceiverNot")==null)this.put("csnReceiverNot", "");this.put("csnReceiver", null);return this;};
 		/** not .... */
 		public M csnReceiverNot(){this.put("csnReceiverNot", "not");return this;};
		/** 处理描述        **/
		public M csnRemark(Object csnRemark){this.put("csnRemark", csnRemark);return this;};
	 	/** and csn_remark is null */
 		public M csnRemarkNull(){if(this.get("csnRemarkNot")==null)this.put("csnRemarkNot", "");this.put("csnRemark", null);return this;};
 		/** not .... */
 		public M csnRemarkNot(){this.put("csnRemarkNot", "not");return this;};
		/** 处理日志        **/
		public M csnLog(Object csnLog){this.put("csnLog", csnLog);return this;};
	 	/** and csn_log is null */
 		public M csnLogNull(){if(this.get("csnLogNot")==null)this.put("csnLogNot", "");this.put("csnLog", null);return this;};
 		/** not .... */
 		public M csnLogNot(){this.put("csnLogNot", "not");return this;};
		/** 添加时间 [非空]       **/
		public M csnAddTime(Object csnAddTime){this.put("csnAddTime", csnAddTime);return this;};
	 	/** and csn_add_time is null */
 		public M csnAddTimeNull(){if(this.get("csnAddTimeNot")==null)this.put("csnAddTimeNot", "");this.put("csnAddTime", null);return this;};
 		/** not .... */
 		public M csnAddTimeNot(){this.put("csnAddTimeNot", "not");return this;};
 		/** and csn_add_time >= ? */
 		public M csnAddTimeStart(Object start){this.put("csnAddTimeStart", start);return this;};			
 		/** and csn_add_time <= ? */
 		public M csnAddTimeEnd(Object end){this.put("csnAddTimeEnd", end);return this;};
		/** 处理时间        **/
		public M csnDealTime(Object csnDealTime){this.put("csnDealTime", csnDealTime);return this;};
	 	/** and csn_deal_time is null */
 		public M csnDealTimeNull(){if(this.get("csnDealTimeNot")==null)this.put("csnDealTimeNot", "");this.put("csnDealTime", null);return this;};
 		/** not .... */
 		public M csnDealTimeNot(){this.put("csnDealTimeNot", "not");return this;};
 		/** and csn_deal_time >= ? */
 		public M csnDealTimeStart(Object start){this.put("csnDealTimeStart", start);return this;};			
 		/** and csn_deal_time <= ? */
 		public M csnDealTimeEnd(Object end){this.put("csnDealTimeEnd", end);return this;};
		/** 状态 [非空]   0:待处理 1:提醒报警 2:已处理     **/
		public M csnStatus(Object csnStatus){this.put("csnStatus", csnStatus);return this;};
	 	/** and csn_status is null */
 		public M csnStatusNull(){if(this.get("csnStatusNot")==null)this.put("csnStatusNot", "");this.put("csnStatus", null);return this;};
 		/** not .... */
 		public M csnStatusNot(){this.put("csnStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有系统通知 **/
		public @api List<CsNotice> list(Integer size){
			return getCsNoticeList(this,size);
		}
		/** 获取系统通知分页 **/
		public @api Page<CsNotice> page(int page,int size){
			return getCsNoticePage(page, size , this);
		}
		/** 根据查询条件取系统通知 **/
		public @api CsNotice get(){
			return getCsNotice(this);
		}
		/** 获取系统通知数 **/
		public @api Long count(){
			return getCsNoticeCount(this);
		}
		/** 获取系统通知表达式 **/
		public @api <T> T eval(String strEval){
			return getCsNoticeEval(strEval,this);
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
			updateCsNotice(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csnId="csnId";
		/** 通知内容 [非空]       **/
		public final static @type(String.class) @like String csnContent="csnContent";
		/** 提醒时间        **/
		public final static @type(Date.class)  String csnAlarmTime="csnAlarmTime";
	 	/** and csn_alarm_time >= ? */
 		public final static @type(Date.class) String csnAlarmTimeStart="csnAlarmTimeStart";
 		/** and csn_alarm_time <= ? */
 		public final static @type(Date.class) String csnAlarmTimeEnd="csnAlarmTimeEnd";
		/** 处理地址        **/
		public final static @type(String.class) @like String csnUrl="csnUrl";
		/** 标识        **/
		public final static @type(String.class) @like String csnFlag="csnFlag";
		/** 发送人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csnSender="csnSender";
		/** 接收人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csnReceiver="csnReceiver";
		/** 处理描述        **/
		public final static @type(String.class) @like String csnRemark="csnRemark";
		/** 处理日志        **/
		public final static @type(String.class) @like String csnLog="csnLog";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csnAddTime="csnAddTime";
	 	/** and csn_add_time >= ? */
 		public final static @type(Date.class) String csnAddTimeStart="csnAddTimeStart";
 		/** and csn_add_time <= ? */
 		public final static @type(Date.class) String csnAddTimeEnd="csnAddTimeEnd";
		/** 处理时间        **/
		public final static @type(Date.class)  String csnDealTime="csnDealTime";
	 	/** and csn_deal_time >= ? */
 		public final static @type(Date.class) String csnDealTimeStart="csnDealTimeStart";
 		/** and csn_deal_time <= ? */
 		public final static @type(Date.class) String csnDealTimeEnd="csnDealTimeEnd";
		/** 状态 [非空]   0:待处理 1:提醒报警 2:已处理     **/
		public final static @type(Short.class)  String csnStatus="csnStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csnId="csn_id";
		/** 通知内容 [非空]       **/
		public final static String csnContent="csn_content";
		/** 提醒时间        **/
		public final static String csnAlarmTime="csn_alarm_time";
		/** 处理地址        **/
		public final static String csnUrl="csn_url";
		/** 标识        **/
		public final static String csnFlag="csn_flag";
		/** 发送人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csnSender="csn_sender";
		/** 接收人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csnReceiver="csn_receiver";
		/** 处理描述        **/
		public final static String csnRemark="csn_remark";
		/** 处理日志        **/
		public final static String csnLog="csn_log";
		/** 添加时间 [非空]       **/
		public final static String csnAddTime="csn_add_time";
		/** 处理时间        **/
		public final static String csnDealTime="csn_deal_time";
		/** 状态 [非空]   0:待处理 1:提醒报警 2:已处理     **/
		public final static String csnStatus="csn_status";
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
				$.Set(name,CsNotice.getCsNotice(params));
			else
				$.Set(name,CsNotice.getCsNoticeList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取系统通知数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsNotice) $.GetRequest("CsNotice$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsNotice.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsNotice.getCsNotice(params);
			else
				value = CsNotice.getCsNoticeList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsNotice.Get($.add(CsNotice.F.csnId,param));
		else if(!$.empty(param.toString()))
			value = CsNotice.get(Long.valueOf(param.toString()));
		$.SetRequest("CsNotice$"+param.hashCode(), value);
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
	public void mergeSet(CsNotice old){
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