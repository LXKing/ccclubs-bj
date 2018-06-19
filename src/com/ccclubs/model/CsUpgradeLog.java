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

@namespace("systematic/upgradelog")
public @caption("升级日志") @table("cs_upgrade_log") class CsUpgradeLog implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csul_id")   @primary  @note("  ") Long csulId;// 主键 非空     
	private @caption("升级名称") @column("csul_title")    @note("  ") String csulTitle;// 非空     
	private @caption("升级类型") @column("csul_type")    @note(" 0:系统 1:后台 2:网站 3:微信 4:支付宝 5:APP 6:API 7:车机  ") Short csulType;// 非空 0:系统 1:后台 2:网站 3:微信 4:支付宝 5:APP 6:API 7:车机     
	private @caption("升级描述") @column("csul_describe")    @note("  ") String csulDescribe;//     
	private @caption("上线日期") @column("csul_on_time")    @note("  ") Date csulOnTime;// 非空     
	private @caption("添加人") @column("csul_adder")    @relate("$csulAdder") @RelateClass(SrvUser.class)  @note("  ") Long csulAdder;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csulAdder;//关联对象[用户]
	private @caption("修改时间") @column("csul_update_time")    @note("  ") Date csulUpdateTime;// 非空     
	private @caption("添加时间") @column("csul_add_time")    @note("  ") Date csulAddTime;// 非空     
	private @caption("状态") @column("csul_status")    @note(" 1:正常 0:无效  ") Short csulStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsUpgradeLog(){
	
	}
	
	//主键构造函数
	public CsUpgradeLog(Long id){
		this.csulId = id;
	}
	
	/**所有字段构造函数 CsUpgradeLog(csulTitle,csulType,csulDescribe,csulOnTime,csulAdder,csulUpdateTime,csulAddTime,csulStatus)
	 CsUpgradeLog(
	 	$.getString("csulTitle")//升级名称 [非空]
	 	,$.getShort("csulType")//升级类型 [非空]
	 	,$.getString("csulDescribe")//升级描述
	 	,$.getDate("csulOnTime")//上线日期 [非空]
	 	,$.getLong("csulAdder")//添加人 [非空]
	 	,$.getDate("csulUpdateTime")//修改时间 [非空]
	 	,$.getDate("csulAddTime")//添加时间 [非空]
	 	,$.getShort("csulStatus")//状态 [非空]
	 )
	**/
	public CsUpgradeLog(String csulTitle,Short csulType,String csulDescribe,Date csulOnTime,Long csulAdder,Date csulUpdateTime,Date csulAddTime,Short csulStatus){
		this.csulTitle=csulTitle;
		this.csulType=csulType;
		this.csulDescribe=csulDescribe;
		this.csulOnTime=csulOnTime;
		this.csulAdder=csulAdder;
		this.csulUpdateTime=csulUpdateTime;
		this.csulAddTime=csulAddTime;
		this.csulStatus=csulStatus;
	}
	
	//设置非空字段
	public CsUpgradeLog setNotNull(String csulTitle,Short csulType,Date csulOnTime,Long csulAdder,Date csulUpdateTime,Date csulAddTime,Short csulStatus){
		this.csulTitle=csulTitle;
		this.csulType=csulType;
		this.csulOnTime=csulOnTime;
		this.csulAdder=csulAdder;
		this.csulUpdateTime=csulUpdateTime;
		this.csulAddTime=csulAddTime;
		this.csulStatus=csulStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsUpgradeLog csulId(Long csulId){
		this.csulId = csulId;
		this.setSeted(F.csulId);
		return this;
	}
	/** 升级名称 [非空]       **/
	public CsUpgradeLog csulTitle(String csulTitle){
		this.csulTitle = csulTitle;
		this.setSeted(F.csulTitle);
		return this;
	}
	/** 升级类型 [非空]   0:系统 1:后台 2:网站 3:微信 4:支付宝 5:APP 6:API 7:车机     **/
	public CsUpgradeLog csulType(Short csulType){
		this.csulType = csulType;
		this.setSeted(F.csulType);
		return this;
	}
	/** 升级描述        **/
	public CsUpgradeLog csulDescribe(String csulDescribe){
		this.csulDescribe = csulDescribe;
		this.setSeted(F.csulDescribe);
		return this;
	}
	/** 上线日期 [非空]       **/
	public CsUpgradeLog csulOnTime(Date csulOnTime){
		this.csulOnTime = csulOnTime;
		this.setSeted(F.csulOnTime);
		return this;
	}
	/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsUpgradeLog csulAdder(Long csulAdder){
		this.csulAdder = csulAdder;
		this.setSeted(F.csulAdder);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsUpgradeLog csulUpdateTime(Date csulUpdateTime){
		this.csulUpdateTime = csulUpdateTime;
		this.setSeted(F.csulUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsUpgradeLog csulAddTime(Date csulAddTime){
		this.csulAddTime = csulAddTime;
		this.setSeted(F.csulAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsUpgradeLog csulStatus(Short csulStatus){
		this.csulStatus = csulStatus;
		this.setSeted(F.csulStatus);
		return this;
	}
	
	
	//克隆对象
	public CsUpgradeLog clone(){
		CsUpgradeLog clone = new CsUpgradeLog();
		clone.csulTitle=this.csulTitle;
		clone.csulType=this.csulType;
		clone.csulOnTime=this.csulOnTime;
		clone.csulAdder=this.csulAdder;
		clone.csulUpdateTime=this.csulUpdateTime;
		clone.csulAddTime=this.csulAddTime;
		clone.csulStatus=this.csulStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取升级日志
	 * @param id
	 * @return
	 */
	public static @api CsUpgradeLog get(Long id){		
		return getCsUpgradeLogById(id);
	}
	/**
	 * 获取所有升级日志
	 * @return
	 */
	public static @api List<CsUpgradeLog> list(Map params,Integer size){
		return getCsUpgradeLogList(params,size);
	}
	/**
	 * 获取升级日志分页
	 * @return
	 */
	public static @api Page<CsUpgradeLog> page(int page,int size,Map params){
		return getCsUpgradeLogPage(page, size , params);
	}
	/**
	 * 根据查询条件取升级日志
	 * @param params
	 * @return
	 */
	public static @api CsUpgradeLog Get(Map params){
		return getCsUpgradeLog(params);
	}
	/**
	 * 获取升级日志数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsUpgradeLogCount(params);
	}
	/**
	 * 获取升级日志数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsUpgradeLogEval(eval,params);
	}
	
	/**
	 * 根据ID取升级日志
	 * @param id
	 * @return
	 */
	public static @api CsUpgradeLog getCsUpgradeLogById(Long id){		
		CsUpgradeLog csUpgradeLog = (CsUpgradeLog) $.GetRequest("CsUpgradeLog$"+id);
		if(csUpgradeLog!=null)
			return csUpgradeLog;
		com.ccclubs.service.admin.ICsUpgradeLogService csUpgradeLogService = $.GetSpringBean("csUpgradeLogService");		
		return csUpgradeLogService.getCsUpgradeLogById(id);
	}
	
	
	/**
	 * 根据ID取升级日志的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsUpgradeLog.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsUpgradeLog csUpgradeLog = get(id);
		if(csUpgradeLog!=null){
			String strValue = csUpgradeLog.getCsulTitle$();
			if(!"CsulTitle".equals("CsulTitle"))
				strValue+="("+csUpgradeLog.getCsulTitle$()+")";
			MemCache.setValue(CsUpgradeLog.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsulTitle$();
		if(!"CsulTitle".equals("CsulTitle"))
			keyValue+="("+this.getCsulTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有升级日志
	 * @return
	 */
	public static @api List<CsUpgradeLog> getCsUpgradeLogList(Map params,Integer size){
		com.ccclubs.service.admin.ICsUpgradeLogService csUpgradeLogService = $.GetSpringBean("csUpgradeLogService");
		return csUpgradeLogService.getCsUpgradeLogList(params, size);
	}
	
	/**
	 * 获取升级日志分页
	 * @return
	 */
	public static @api Page<CsUpgradeLog> getCsUpgradeLogPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsUpgradeLogService csUpgradeLogService = $.GetSpringBean("csUpgradeLogService");
		return csUpgradeLogService.getCsUpgradeLogPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取升级日志
	 * @param params
	 * @return
	 */
	public static @api CsUpgradeLog getCsUpgradeLog(Map params){
		com.ccclubs.service.admin.ICsUpgradeLogService csUpgradeLogService = $.GetSpringBean("csUpgradeLogService");
		return csUpgradeLogService.getCsUpgradeLog(params);
	}
	
	/**
	 * 获取升级日志数
	 * @return
	 */
	public static @api Long getCsUpgradeLogCount(Map params){
		com.ccclubs.service.admin.ICsUpgradeLogService csUpgradeLogService = $.GetSpringBean("csUpgradeLogService");
		return csUpgradeLogService.getCsUpgradeLogCount(params);
	}
		
		
	/**
	 * 获取升级日志自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsUpgradeLogEval(String eval,Map params){
		com.ccclubs.service.admin.ICsUpgradeLogService csUpgradeLogService = $.GetSpringBean("csUpgradeLogService");
		return csUpgradeLogService.getCsUpgradeLogEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsUpgradeLog(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsUpgradeLogService csUpgradeLogService = $.GetSpringBean("csUpgradeLogService");
		csUpgradeLogService.updateCsUpgradeLogByConfirm(set, where);
	}
	
	
	/**
	 * 保存升级日志对象
	 * @param params
	 * @return
	 */
	public CsUpgradeLog save(){
		com.ccclubs.service.admin.ICsUpgradeLogService csUpgradeLogService = $.GetSpringBean("csUpgradeLogService");
		if(this.getCsulId()!=null)
			csUpgradeLogService.updateCsUpgradeLog(this);
		else
			return csUpgradeLogService.saveCsUpgradeLog(this);
		return this;
	}
	
	
	/**
	 * 更新升级日志对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsUpgradeLogService csUpgradeLogService = $.GetSpringBean("csUpgradeLogService");
		csUpgradeLogService.updateCsUpgradeLog$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsUpgradeLogService csUpgradeLogService = $.GetSpringBean("csUpgradeLogService");
		if($.equals($.config("logic_delete"),"true"))
			csUpgradeLogService.removeCsUpgradeLogById(this.getCsulId());
		else
			csUpgradeLogService.deleteCsUpgradeLogById(this.getCsulId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsUpgradeLogService csUpgradeLogService = $.GetSpringBean("csUpgradeLogService");
		return csUpgradeLogService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsulId(){
		return this.csulId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsulId$(){
		String strValue="";
		 strValue=$.str(this.getCsulId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsulId(Long csulId){
		this.csulId = csulId;
		this.setSeted(F.csulId);
	}
	/*******************************升级名称**********************************/	
	/**
	* 升级名称 [非空]      
	**/
	public String getCsulTitle(){
		return this.csulTitle;
	}
	/**
	* 获取升级名称格式化(toString)
	**/
	public String getCsulTitle$(){
		String strValue="";
		 strValue=$.str(this.getCsulTitle());
	 	 return strValue;
	}
	/**
	* 升级名称 [非空]      
	**/
	public void setCsulTitle(String csulTitle){
		this.csulTitle = csulTitle;
		this.setSeted(F.csulTitle);
	}
	/*******************************升级类型**********************************/	
	/**
	* 升级类型 [非空]   0:系统 1:后台 2:网站 3:微信 4:支付宝 5:APP 6:API 7:车机    
	**/
	public Short getCsulType(){
		return this.csulType;
	}
	/**
	* 获取升级类型格式化(toString)
	**/
	public String getCsulType$(){
		String strValue="";
		 if($.equals($.str(this.getCsulType()),"0"))
			strValue=$.str("系统");		 
		 if($.equals($.str(this.getCsulType()),"1"))
			strValue=$.str("后台");		 
		 if($.equals($.str(this.getCsulType()),"2"))
			strValue=$.str("网站");		 
		 if($.equals($.str(this.getCsulType()),"3"))
			strValue=$.str("微信");		 
		 if($.equals($.str(this.getCsulType()),"4"))
			strValue=$.str("支付宝");		 
		 if($.equals($.str(this.getCsulType()),"5"))
			strValue=$.str("APP");		 
		 if($.equals($.str(this.getCsulType()),"6"))
			strValue=$.str("API");		 
		 if($.equals($.str(this.getCsulType()),"7"))
			strValue=$.str("车机");		 
	 	 return strValue;
	}
	/**
	* 升级类型 [非空]   0:系统 1:后台 2:网站 3:微信 4:支付宝 5:APP 6:API 7:车机    
	**/
	public void setCsulType(Short csulType){
		this.csulType = csulType;
		this.setSeted(F.csulType);
	}
	/*******************************升级描述**********************************/	
	/**
	* 升级描述       
	**/
	public String getCsulDescribe(){
		return this.csulDescribe;
	}
	/**
	* 获取升级描述格式化(toString)
	**/
	public String getCsulDescribe$(){
		String strValue="";
		 strValue=$.str(this.getCsulDescribe());
	 	 return strValue;
	}
	/**
	* 升级描述       
	**/
	public void setCsulDescribe(String csulDescribe){
		this.csulDescribe = csulDescribe;
		this.setSeted(F.csulDescribe);
	}
	/*******************************上线日期**********************************/	
	/**
	* 上线日期 [非空]      
	**/
	public Date getCsulOnTime(){
		return this.csulOnTime;
	}
	/**
	* 获取上线日期格式化(toString)
	**/
	public String getCsulOnTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsulOnTime(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 上线日期 [非空]      
	**/
	public void setCsulOnTime(Date csulOnTime){
		this.csulOnTime = csulOnTime;
		this.setSeted(F.csulOnTime);
	}
	/*******************************添加人**********************************/	
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsulAdder(){
		return this.csulAdder;
	}
	/**
	* 获取添加人格式化(toString)
	**/
	public String getCsulAdder$(){
		String strValue="";
		if(this.getCsulAdder()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsulAdder()));
		}			
	 	 return strValue;
	}
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsulAdder(Long csulAdder){
		this.csulAdder = csulAdder;
		this.setSeted(F.csulAdder);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csulAdder(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsulAdder());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsulAdder()!=null){
 			srvUser = SrvUser.get(this.getCsulAdder());
 		}
 		$.SetRequest("SrvUser$"+this.getCsulAdder(), srvUser);
	 	return srvUser;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsulUpdateTime(){
		return this.csulUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsulUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsulUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsulUpdateTime(Date csulUpdateTime){
		this.csulUpdateTime = csulUpdateTime;
		this.setSeted(F.csulUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsulAddTime(){
		return this.csulAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsulAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsulAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsulAddTime(Date csulAddTime){
		this.csulAddTime = csulAddTime;
		this.setSeted(F.csulAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsulStatus(){
		return this.csulStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsulStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsulStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsulStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsulStatus(Short csulStatus){
		this.csulStatus = csulStatus;
		this.setSeted(F.csulStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsUpgradeLog.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUpgradeLog.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUpgradeLog.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsUpgradeLog.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsUpgradeLog.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUpgradeLog.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUpgradeLog.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsUpgradeLog.this);
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
		public M csulId(Object csulId){this.put("csulId", csulId);return this;};
	 	/** and csul_id is null */
 		public M csulIdNull(){if(this.get("csulIdNot")==null)this.put("csulIdNot", "");this.put("csulId", null);return this;};
 		/** not .... */
 		public M csulIdNot(){this.put("csulIdNot", "not");return this;};
		/** 升级名称 [非空]       **/
		public M csulTitle(Object csulTitle){this.put("csulTitle", csulTitle);return this;};
	 	/** and csul_title is null */
 		public M csulTitleNull(){if(this.get("csulTitleNot")==null)this.put("csulTitleNot", "");this.put("csulTitle", null);return this;};
 		/** not .... */
 		public M csulTitleNot(){this.put("csulTitleNot", "not");return this;};
		/** 升级类型 [非空]   0:系统 1:后台 2:网站 3:微信 4:支付宝 5:APP 6:API 7:车机     **/
		public M csulType(Object csulType){this.put("csulType", csulType);return this;};
	 	/** and csul_type is null */
 		public M csulTypeNull(){if(this.get("csulTypeNot")==null)this.put("csulTypeNot", "");this.put("csulType", null);return this;};
 		/** not .... */
 		public M csulTypeNot(){this.put("csulTypeNot", "not");return this;};
		/** 升级描述        **/
		public M csulDescribe(Object csulDescribe){this.put("csulDescribe", csulDescribe);return this;};
	 	/** and csul_describe is null */
 		public M csulDescribeNull(){if(this.get("csulDescribeNot")==null)this.put("csulDescribeNot", "");this.put("csulDescribe", null);return this;};
 		/** not .... */
 		public M csulDescribeNot(){this.put("csulDescribeNot", "not");return this;};
		/** 上线日期 [非空]       **/
		public M csulOnTime(Object csulOnTime){this.put("csulOnTime", csulOnTime);return this;};
	 	/** and csul_on_time is null */
 		public M csulOnTimeNull(){if(this.get("csulOnTimeNot")==null)this.put("csulOnTimeNot", "");this.put("csulOnTime", null);return this;};
 		/** not .... */
 		public M csulOnTimeNot(){this.put("csulOnTimeNot", "not");return this;};
 		/** and csul_on_time >= ? */
 		public M csulOnTimeStart(Object start){this.put("csulOnTimeStart", start);return this;};			
 		/** and csul_on_time <= ? */
 		public M csulOnTimeEnd(Object end){this.put("csulOnTimeEnd", end);return this;};
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csulAdder(Object csulAdder){this.put("csulAdder", csulAdder);return this;};
	 	/** and csul_adder is null */
 		public M csulAdderNull(){if(this.get("csulAdderNot")==null)this.put("csulAdderNot", "");this.put("csulAdder", null);return this;};
 		/** not .... */
 		public M csulAdderNot(){this.put("csulAdderNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csulUpdateTime(Object csulUpdateTime){this.put("csulUpdateTime", csulUpdateTime);return this;};
	 	/** and csul_update_time is null */
 		public M csulUpdateTimeNull(){if(this.get("csulUpdateTimeNot")==null)this.put("csulUpdateTimeNot", "");this.put("csulUpdateTime", null);return this;};
 		/** not .... */
 		public M csulUpdateTimeNot(){this.put("csulUpdateTimeNot", "not");return this;};
 		/** and csul_update_time >= ? */
 		public M csulUpdateTimeStart(Object start){this.put("csulUpdateTimeStart", start);return this;};			
 		/** and csul_update_time <= ? */
 		public M csulUpdateTimeEnd(Object end){this.put("csulUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csulAddTime(Object csulAddTime){this.put("csulAddTime", csulAddTime);return this;};
	 	/** and csul_add_time is null */
 		public M csulAddTimeNull(){if(this.get("csulAddTimeNot")==null)this.put("csulAddTimeNot", "");this.put("csulAddTime", null);return this;};
 		/** not .... */
 		public M csulAddTimeNot(){this.put("csulAddTimeNot", "not");return this;};
 		/** and csul_add_time >= ? */
 		public M csulAddTimeStart(Object start){this.put("csulAddTimeStart", start);return this;};			
 		/** and csul_add_time <= ? */
 		public M csulAddTimeEnd(Object end){this.put("csulAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csulStatus(Object csulStatus){this.put("csulStatus", csulStatus);return this;};
	 	/** and csul_status is null */
 		public M csulStatusNull(){if(this.get("csulStatusNot")==null)this.put("csulStatusNot", "");this.put("csulStatus", null);return this;};
 		/** not .... */
 		public M csulStatusNot(){this.put("csulStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有升级日志 **/
		public @api List<CsUpgradeLog> list(Integer size){
			return getCsUpgradeLogList(this,size);
		}
		/** 获取升级日志分页 **/
		public @api Page<CsUpgradeLog> page(int page,int size){
			return getCsUpgradeLogPage(page, size , this);
		}
		/** 根据查询条件取升级日志 **/
		public @api CsUpgradeLog get(){
			return getCsUpgradeLog(this);
		}
		/** 获取升级日志数 **/
		public @api Long count(){
			return getCsUpgradeLogCount(this);
		}
		/** 获取升级日志表达式 **/
		public @api <T> T eval(String strEval){
			return getCsUpgradeLogEval(strEval,this);
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
			updateCsUpgradeLog(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csulId="csulId";
		/** 升级名称 [非空]       **/
		public final static @type(String.class) @like String csulTitle="csulTitle";
		/** 升级类型 [非空]   0:系统 1:后台 2:网站 3:微信 4:支付宝 5:APP 6:API 7:车机     **/
		public final static @type(Short.class)  String csulType="csulType";
		/** 升级描述        **/
		public final static @type(String.class) @like String csulDescribe="csulDescribe";
		/** 上线日期 [非空]       **/
		public final static @type(Date.class)  String csulOnTime="csulOnTime";
	 	/** and csul_on_time >= ? */
 		public final static @type(Date.class) String csulOnTimeStart="csulOnTimeStart";
 		/** and csul_on_time <= ? */
 		public final static @type(Date.class) String csulOnTimeEnd="csulOnTimeEnd";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csulAdder="csulAdder";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csulUpdateTime="csulUpdateTime";
	 	/** and csul_update_time >= ? */
 		public final static @type(Date.class) String csulUpdateTimeStart="csulUpdateTimeStart";
 		/** and csul_update_time <= ? */
 		public final static @type(Date.class) String csulUpdateTimeEnd="csulUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csulAddTime="csulAddTime";
	 	/** and csul_add_time >= ? */
 		public final static @type(Date.class) String csulAddTimeStart="csulAddTimeStart";
 		/** and csul_add_time <= ? */
 		public final static @type(Date.class) String csulAddTimeEnd="csulAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csulStatus="csulStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csulId="csul_id";
		/** 升级名称 [非空]       **/
		public final static String csulTitle="csul_title";
		/** 升级类型 [非空]   0:系统 1:后台 2:网站 3:微信 4:支付宝 5:APP 6:API 7:车机     **/
		public final static String csulType="csul_type";
		/** 升级描述        **/
		public final static String csulDescribe="csul_describe";
		/** 上线日期 [非空]       **/
		public final static String csulOnTime="csul_on_time";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csulAdder="csul_adder";
		/** 修改时间 [非空]       **/
		public final static String csulUpdateTime="csul_update_time";
		/** 添加时间 [非空]       **/
		public final static String csulAddTime="csul_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csulStatus="csul_status";
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
				$.Set(name,CsUpgradeLog.getCsUpgradeLog(params));
			else
				$.Set(name,CsUpgradeLog.getCsUpgradeLogList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取升级日志数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsUpgradeLog) $.GetRequest("CsUpgradeLog$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsUpgradeLog.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsUpgradeLog.getCsUpgradeLog(params);
			else
				value = CsUpgradeLog.getCsUpgradeLogList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsUpgradeLog.Get($.add(CsUpgradeLog.F.csulId,param));
		else if(!$.empty(param.toString()))
			value = CsUpgradeLog.get(Long.valueOf(param.toString()));
		$.SetRequest("CsUpgradeLog$"+param.hashCode(), value);
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
	public void mergeSet(CsUpgradeLog old){
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