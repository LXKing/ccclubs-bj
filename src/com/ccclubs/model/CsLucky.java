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

@namespace("operate/lucky/lucky")
public @caption("抽奖活动") @table("cs_lucky") class CsLucky implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csl_id")   @primary  @note("  ") Long cslId;// 主键 非空     
	private @caption("活动名称") @column("csl_name")    @note("  ") String cslName;// 非空     
	private @caption("活动标识") @column("csl_flag")    @note("  尽量不要改") String cslFlag;//  尽量不要改   
	private @caption("抽奖类型") @column("csl_type")    @note(" 0:大转盘  如大转盘、老虎机、刮刮奖") Short cslType;// 非空 0:大转盘  如大转盘、老虎机、刮刮奖   
	//private @caption("奖项") @RelateClass(CsLuckyItem.class) List<CsLuckyItem> cslItems;//反向关联的抽奖奖项列表
	private @caption("开始时间") @column("csl_start")    @note("  ") Date cslStart;// 非空     
	private @caption("结束时间") @column("csl_finish")    @note("  ") Date cslFinish;// 非空     
	private @caption("抽奖价格") @column("csl_price")    @note("  ") Double cslPrice;//(单位：积分) 非空     
	private @caption("编辑人") @column("csl_editor")    @relate("$cslEditor") @RelateClass(SrvUser.class)  @note("  ") Long cslEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cslEditor;//关联对象[用户]
	private @caption("修改时间") @column("csl_update_time")    @note("  ") Date cslUpdateTime;// 非空     
	private @caption("添加时间") @column("csl_add_time")    @note("  ") Date cslAddTime;// 非空     
	private @caption("状态") @column("csl_status")    @note(" 1:正常 0:无效  ") Short cslStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsLucky(){
	
	}
	
	//主键构造函数
	public CsLucky(Long id){
		this.cslId = id;
	}
	
	/**所有字段构造函数 CsLucky(cslName,cslFlag,cslType,cslStart,cslFinish,cslPrice,cslEditor,cslUpdateTime,cslAddTime,cslStatus)
	 CsLucky(
	 	$.getString("cslName")//活动名称 [非空]
	 	,$.getString("cslFlag")//活动标识
	 	,$.getShort("cslType")//抽奖类型 [非空]
	 	,$.getDate("cslStart")//开始时间 [非空]
	 	,$.getDate("cslFinish")//结束时间 [非空]
	 	,$.getDouble("cslPrice")//抽奖价格 [非空]
	 	,$.getLong("cslEditor")//编辑人 [非空]
	 	,$.getDate("cslUpdateTime")//修改时间 [非空]
	 	,$.getDate("cslAddTime")//添加时间 [非空]
	 	,$.getShort("cslStatus")//状态 [非空]
	 )
	**/
	public CsLucky(String cslName,String cslFlag,Short cslType,Date cslStart,Date cslFinish,Double cslPrice,Long cslEditor,Date cslUpdateTime,Date cslAddTime,Short cslStatus){
		this.cslName=cslName;
		this.cslFlag=cslFlag;
		this.cslType=cslType;
		this.cslStart=cslStart;
		this.cslFinish=cslFinish;
		this.cslPrice=cslPrice;
		this.cslEditor=cslEditor;
		this.cslUpdateTime=cslUpdateTime;
		this.cslAddTime=cslAddTime;
		this.cslStatus=cslStatus;
	}
	
	//设置非空字段
	public CsLucky setNotNull(String cslName,Short cslType,Date cslStart,Date cslFinish,Double cslPrice,Long cslEditor,Date cslUpdateTime,Date cslAddTime,Short cslStatus){
		this.cslName=cslName;
		this.cslType=cslType;
		this.cslStart=cslStart;
		this.cslFinish=cslFinish;
		this.cslPrice=cslPrice;
		this.cslEditor=cslEditor;
		this.cslUpdateTime=cslUpdateTime;
		this.cslAddTime=cslAddTime;
		this.cslStatus=cslStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsLucky cslId(Long cslId){
		this.cslId = cslId;
		this.setSeted(F.cslId);
		return this;
	}
	/** 活动名称 [非空]       **/
	public CsLucky cslName(String cslName){
		this.cslName = cslName;
		this.setSeted(F.cslName);
		return this;
	}
	/** 活动标识     尽量不要改   **/
	public CsLucky cslFlag(String cslFlag){
		this.cslFlag = cslFlag;
		this.setSeted(F.cslFlag);
		return this;
	}
	/** 抽奖类型 [非空]   0:大转盘  如大转盘、老虎机、刮刮奖   **/
	public CsLucky cslType(Short cslType){
		this.cslType = cslType;
		this.setSeted(F.cslType);
		return this;
	}
	/** 开始时间 [非空]       **/
	public CsLucky cslStart(Date cslStart){
		this.cslStart = cslStart;
		this.setSeted(F.cslStart);
		return this;
	}
	/** 结束时间 [非空]       **/
	public CsLucky cslFinish(Date cslFinish){
		this.cslFinish = cslFinish;
		this.setSeted(F.cslFinish);
		return this;
	}
	/** 抽奖价格 [非空]       **/
	public CsLucky cslPrice(Double cslPrice){
		this.cslPrice = cslPrice;
		this.setSeted(F.cslPrice);
		return this;
	}
	/** 编辑人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsLucky cslEditor(Long cslEditor){
		this.cslEditor = cslEditor;
		this.setSeted(F.cslEditor);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsLucky cslUpdateTime(Date cslUpdateTime){
		this.cslUpdateTime = cslUpdateTime;
		this.setSeted(F.cslUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsLucky cslAddTime(Date cslAddTime){
		this.cslAddTime = cslAddTime;
		this.setSeted(F.cslAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsLucky cslStatus(Short cslStatus){
		this.cslStatus = cslStatus;
		this.setSeted(F.cslStatus);
		return this;
	}
	
	
	//克隆对象
	public CsLucky clone(){
		CsLucky clone = new CsLucky();
		clone.cslName=this.cslName;
		clone.cslType=this.cslType;
		clone.cslStart=this.cslStart;
		clone.cslFinish=this.cslFinish;
		clone.cslPrice=this.cslPrice;
		clone.cslEditor=this.cslEditor;
		clone.cslUpdateTime=this.cslUpdateTime;
		clone.cslAddTime=this.cslAddTime;
		clone.cslStatus=this.cslStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取抽奖活动
	 * @param id
	 * @return
	 */
	public static @api CsLucky get(Long id){		
		return getCsLuckyById(id);
	}
	/**
	 * 获取所有抽奖活动
	 * @return
	 */
	public static @api List<CsLucky> list(Map params,Integer size){
		return getCsLuckyList(params,size);
	}
	/**
	 * 获取抽奖活动分页
	 * @return
	 */
	public static @api Page<CsLucky> page(int page,int size,Map params){
		return getCsLuckyPage(page, size , params);
	}
	/**
	 * 根据查询条件取抽奖活动
	 * @param params
	 * @return
	 */
	public static @api CsLucky Get(Map params){
		return getCsLucky(params);
	}
	/**
	 * 获取抽奖活动数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsLuckyCount(params);
	}
	/**
	 * 获取抽奖活动数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsLuckyEval(eval,params);
	}
	
	/**
	 * 根据ID取抽奖活动
	 * @param id
	 * @return
	 */
	public static @api CsLucky getCsLuckyById(Long id){		
		CsLucky csLucky = (CsLucky) $.GetRequest("CsLucky$"+id);
		if(csLucky!=null)
			return csLucky;
		com.ccclubs.service.admin.ICsLuckyService csLuckyService = $.GetSpringBean("csLuckyService");		
		return csLuckyService.getCsLuckyById(id);
	}
	
	
	/**
	 * 根据ID取抽奖活动的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsLucky.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsLucky csLucky = get(id);
		if(csLucky!=null){
			String strValue = csLucky.getCslName$();
			if(!"CslName".equals("CslName"))
				strValue+="("+csLucky.getCslName$()+")";
			MemCache.setValue(CsLucky.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCslName$();
		if(!"CslName".equals("CslName"))
			keyValue+="("+this.getCslName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有抽奖活动
	 * @return
	 */
	public static @api List<CsLucky> getCsLuckyList(Map params,Integer size){
		com.ccclubs.service.admin.ICsLuckyService csLuckyService = $.GetSpringBean("csLuckyService");
		return csLuckyService.getCsLuckyList(params, size);
	}
	
	/**
	 * 获取抽奖活动分页
	 * @return
	 */
	public static @api Page<CsLucky> getCsLuckyPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsLuckyService csLuckyService = $.GetSpringBean("csLuckyService");
		return csLuckyService.getCsLuckyPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取抽奖活动
	 * @param params
	 * @return
	 */
	public static @api CsLucky getCsLucky(Map params){
		com.ccclubs.service.admin.ICsLuckyService csLuckyService = $.GetSpringBean("csLuckyService");
		return csLuckyService.getCsLucky(params);
	}
	
	/**
	 * 获取抽奖活动数
	 * @return
	 */
	public static @api Long getCsLuckyCount(Map params){
		com.ccclubs.service.admin.ICsLuckyService csLuckyService = $.GetSpringBean("csLuckyService");
		return csLuckyService.getCsLuckyCount(params);
	}
		
		
	/**
	 * 获取抽奖活动自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsLuckyEval(String eval,Map params){
		com.ccclubs.service.admin.ICsLuckyService csLuckyService = $.GetSpringBean("csLuckyService");
		return csLuckyService.getCsLuckyEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsLucky(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsLuckyService csLuckyService = $.GetSpringBean("csLuckyService");
		csLuckyService.updateCsLuckyByConfirm(set, where);
	}
	
	
	/**
	 * 保存抽奖活动对象
	 * @param params
	 * @return
	 */
	public CsLucky save(){
		com.ccclubs.service.admin.ICsLuckyService csLuckyService = $.GetSpringBean("csLuckyService");
		if(this.getCslId()!=null)
			csLuckyService.updateCsLucky(this);
		else
			return csLuckyService.saveCsLucky(this);
		return this;
	}
	
	
	/**
	 * 更新抽奖活动对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsLuckyService csLuckyService = $.GetSpringBean("csLuckyService");
		csLuckyService.updateCsLucky$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsLuckyService csLuckyService = $.GetSpringBean("csLuckyService");
		if($.equals($.config("logic_delete"),"true"))
			csLuckyService.removeCsLuckyById(this.getCslId());
		else
			csLuckyService.deleteCsLuckyById(this.getCslId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsLuckyService csLuckyService = $.GetSpringBean("csLuckyService");
		return csLuckyService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCslId(){
		return this.cslId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCslId$(){
		String strValue="";
		 strValue=$.str(this.getCslId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCslId(Long cslId){
		this.cslId = cslId;
		this.setSeted(F.cslId);
	}
	/*******************************活动名称**********************************/	
	/**
	* 活动名称 [非空]      
	**/
	public String getCslName(){
		return this.cslName;
	}
	/**
	* 获取活动名称格式化(toString)
	**/
	public String getCslName$(){
		String strValue="";
		 strValue=$.str(this.getCslName());
	 	 return strValue;
	}
	/**
	* 活动名称 [非空]      
	**/
	public void setCslName(String cslName){
		this.cslName = cslName;
		this.setSeted(F.cslName);
	}
	/*******************************活动标识**********************************/	
	/**
	* 活动标识     尽量不要改  
	**/
	public String getCslFlag(){
		return this.cslFlag;
	}
	/**
	* 获取活动标识格式化(toString)
	**/
	public String getCslFlag$(){
		String strValue="";
		 strValue=$.str(this.getCslFlag());
	 	 return strValue;
	}
	/**
	* 活动标识     尽量不要改  
	**/
	public void setCslFlag(String cslFlag){
		this.cslFlag = cslFlag;
		this.setSeted(F.cslFlag);
	}
	/*******************************抽奖类型**********************************/	
	/**
	* 抽奖类型 [非空]   0:大转盘  如大转盘、老虎机、刮刮奖  
	**/
	public Short getCslType(){
		return this.cslType;
	}
	/**
	* 获取抽奖类型格式化(toString)
	**/
	public String getCslType$(){
		String strValue="";
		 if($.equals($.str(this.getCslType()),"0"))
			strValue=$.str("大转盘");		 
	 	 return strValue;
	}
	/**
	* 抽奖类型 [非空]   0:大转盘  如大转盘、老虎机、刮刮奖  
	**/
	public void setCslType(Short cslType){
		this.cslType = cslType;
		this.setSeted(F.cslType);
	}
	/*******************************反向关联的抽奖奖项列表**********************************/	
	/**
	* 获取与当前对象反向关联的抽奖奖项列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsLuckyItem> getCslItems(){
		com.ccclubs.service.admin.ICsLuckyItemService csLuckyItemService = $.GetSpringBean("csLuckyItemService");
		Map params = new HashMap();
		params.put("csliLuckyId",this.getCslId());
		return csLuckyItemService.getCsLuckyItemList(params,-1);
	}
	/**
	* 获取奖项格式化(toString)
	**/
	public String getCslItems$(){
		return "[...]";
	}
	/*******************************开始时间**********************************/	
	/**
	* 开始时间 [非空]      
	**/
	public Date getCslStart(){
		return this.cslStart;
	}
	/**
	* 获取开始时间格式化(toString)
	**/
	public String getCslStart$(){
		String strValue="";
		 strValue=$.str($.date(this.getCslStart(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 开始时间 [非空]      
	**/
	public void setCslStart(Date cslStart){
		this.cslStart = cslStart;
		this.setSeted(F.cslStart);
	}
	/*******************************结束时间**********************************/	
	/**
	* 结束时间 [非空]      
	**/
	public Date getCslFinish(){
		return this.cslFinish;
	}
	/**
	* 获取结束时间格式化(toString)
	**/
	public String getCslFinish$(){
		String strValue="";
		 strValue=$.str($.date(this.getCslFinish(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 结束时间 [非空]      
	**/
	public void setCslFinish(Date cslFinish){
		this.cslFinish = cslFinish;
		this.setSeted(F.cslFinish);
	}
	/*******************************抽奖价格**********************************/	
	/**
	* 抽奖价格 [非空]      
	**/
	public Double getCslPrice(){
		return this.cslPrice;
	}
	/**
	* 获取抽奖价格格式化(toString)
	**/
	public String getCslPrice$(){
		String strValue="";
		 strValue=$.str(this.getCslPrice());	
	 	 return strValue;
	}
	/**
	* 抽奖价格 [非空]      
	**/
	public void setCslPrice(Double cslPrice){
		this.cslPrice = cslPrice;
		this.setSeted(F.cslPrice);
	}
	/*******************************编辑人**********************************/	
	/**
	* 编辑人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCslEditor(){
		return this.cslEditor;
	}
	/**
	* 获取编辑人格式化(toString)
	**/
	public String getCslEditor$(){
		String strValue="";
		if(this.getCslEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCslEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 编辑人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCslEditor(Long cslEditor){
		this.cslEditor = cslEditor;
		this.setSeted(F.cslEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cslEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCslEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCslEditor()!=null){
 			srvUser = SrvUser.get(this.getCslEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCslEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCslUpdateTime(){
		return this.cslUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCslUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCslUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCslUpdateTime(Date cslUpdateTime){
		this.cslUpdateTime = cslUpdateTime;
		this.setSeted(F.cslUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCslAddTime(){
		return this.cslAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCslAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCslAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCslAddTime(Date cslAddTime){
		this.cslAddTime = cslAddTime;
		this.setSeted(F.cslAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCslStatus(){
		return this.cslStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCslStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCslStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCslStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCslStatus(Short cslStatus){
		this.cslStatus = cslStatus;
		this.setSeted(F.cslStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsLucky.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsLucky.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsLucky.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsLucky.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsLucky.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsLucky.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsLucky.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsLucky.this);
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
		public M cslId(Object cslId){this.put("cslId", cslId);return this;};
	 	/** and csl_id is null */
 		public M cslIdNull(){if(this.get("cslIdNot")==null)this.put("cslIdNot", "");this.put("cslId", null);return this;};
 		/** not .... */
 		public M cslIdNot(){this.put("cslIdNot", "not");return this;};
		/** 活动名称 [非空]       **/
		public M cslName(Object cslName){this.put("cslName", cslName);return this;};
	 	/** and csl_name is null */
 		public M cslNameNull(){if(this.get("cslNameNot")==null)this.put("cslNameNot", "");this.put("cslName", null);return this;};
 		/** not .... */
 		public M cslNameNot(){this.put("cslNameNot", "not");return this;};
		/** 活动标识     尽量不要改   **/
		public M cslFlag(Object cslFlag){this.put("cslFlag", cslFlag);return this;};
	 	/** and csl_flag is null */
 		public M cslFlagNull(){if(this.get("cslFlagNot")==null)this.put("cslFlagNot", "");this.put("cslFlag", null);return this;};
 		/** not .... */
 		public M cslFlagNot(){this.put("cslFlagNot", "not");return this;};
		/** 抽奖类型 [非空]   0:大转盘  如大转盘、老虎机、刮刮奖   **/
		public M cslType(Object cslType){this.put("cslType", cslType);return this;};
	 	/** and csl_type is null */
 		public M cslTypeNull(){if(this.get("cslTypeNot")==null)this.put("cslTypeNot", "");this.put("cslType", null);return this;};
 		/** not .... */
 		public M cslTypeNot(){this.put("cslTypeNot", "not");return this;};
		/** 开始时间 [非空]       **/
		public M cslStart(Object cslStart){this.put("cslStart", cslStart);return this;};
	 	/** and csl_start is null */
 		public M cslStartNull(){if(this.get("cslStartNot")==null)this.put("cslStartNot", "");this.put("cslStart", null);return this;};
 		/** not .... */
 		public M cslStartNot(){this.put("cslStartNot", "not");return this;};
 		/** and csl_start >= ? */
 		public M cslStartStart(Object start){this.put("cslStartStart", start);return this;};			
 		/** and csl_start <= ? */
 		public M cslStartEnd(Object end){this.put("cslStartEnd", end);return this;};
		/** 结束时间 [非空]       **/
		public M cslFinish(Object cslFinish){this.put("cslFinish", cslFinish);return this;};
	 	/** and csl_finish is null */
 		public M cslFinishNull(){if(this.get("cslFinishNot")==null)this.put("cslFinishNot", "");this.put("cslFinish", null);return this;};
 		/** not .... */
 		public M cslFinishNot(){this.put("cslFinishNot", "not");return this;};
 		/** and csl_finish >= ? */
 		public M cslFinishStart(Object start){this.put("cslFinishStart", start);return this;};			
 		/** and csl_finish <= ? */
 		public M cslFinishEnd(Object end){this.put("cslFinishEnd", end);return this;};
		/** 抽奖价格 [非空]       **/
		public M cslPrice(Object cslPrice){this.put("cslPrice", cslPrice);return this;};
	 	/** and csl_price is null */
 		public M cslPriceNull(){if(this.get("cslPriceNot")==null)this.put("cslPriceNot", "");this.put("cslPrice", null);return this;};
 		/** not .... */
 		public M cslPriceNot(){this.put("cslPriceNot", "not");return this;};
		/** and csl_price >= ? */
		public M cslPriceMin(Object min){this.put("cslPriceMin", min);return this;};
		/** and csl_price <= ? */
		public M cslPriceMax(Object max){this.put("cslPriceMax", max);return this;};
		/** 编辑人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cslEditor(Object cslEditor){this.put("cslEditor", cslEditor);return this;};
	 	/** and csl_editor is null */
 		public M cslEditorNull(){if(this.get("cslEditorNot")==null)this.put("cslEditorNot", "");this.put("cslEditor", null);return this;};
 		/** not .... */
 		public M cslEditorNot(){this.put("cslEditorNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M cslUpdateTime(Object cslUpdateTime){this.put("cslUpdateTime", cslUpdateTime);return this;};
	 	/** and csl_update_time is null */
 		public M cslUpdateTimeNull(){if(this.get("cslUpdateTimeNot")==null)this.put("cslUpdateTimeNot", "");this.put("cslUpdateTime", null);return this;};
 		/** not .... */
 		public M cslUpdateTimeNot(){this.put("cslUpdateTimeNot", "not");return this;};
 		/** and csl_update_time >= ? */
 		public M cslUpdateTimeStart(Object start){this.put("cslUpdateTimeStart", start);return this;};			
 		/** and csl_update_time <= ? */
 		public M cslUpdateTimeEnd(Object end){this.put("cslUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cslAddTime(Object cslAddTime){this.put("cslAddTime", cslAddTime);return this;};
	 	/** and csl_add_time is null */
 		public M cslAddTimeNull(){if(this.get("cslAddTimeNot")==null)this.put("cslAddTimeNot", "");this.put("cslAddTime", null);return this;};
 		/** not .... */
 		public M cslAddTimeNot(){this.put("cslAddTimeNot", "not");return this;};
 		/** and csl_add_time >= ? */
 		public M cslAddTimeStart(Object start){this.put("cslAddTimeStart", start);return this;};			
 		/** and csl_add_time <= ? */
 		public M cslAddTimeEnd(Object end){this.put("cslAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M cslStatus(Object cslStatus){this.put("cslStatus", cslStatus);return this;};
	 	/** and csl_status is null */
 		public M cslStatusNull(){if(this.get("cslStatusNot")==null)this.put("cslStatusNot", "");this.put("cslStatus", null);return this;};
 		/** not .... */
 		public M cslStatusNot(){this.put("cslStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有抽奖活动 **/
		public @api List<CsLucky> list(Integer size){
			return getCsLuckyList(this,size);
		}
		/** 获取抽奖活动分页 **/
		public @api Page<CsLucky> page(int page,int size){
			return getCsLuckyPage(page, size , this);
		}
		/** 根据查询条件取抽奖活动 **/
		public @api CsLucky get(){
			return getCsLucky(this);
		}
		/** 获取抽奖活动数 **/
		public @api Long count(){
			return getCsLuckyCount(this);
		}
		/** 获取抽奖活动表达式 **/
		public @api <T> T eval(String strEval){
			return getCsLuckyEval(strEval,this);
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
			updateCsLucky(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cslId="cslId";
		/** 活动名称 [非空]       **/
		public final static @type(String.class) @like String cslName="cslName";
		/** 活动标识     尽量不要改   **/
		public final static @type(String.class) @like String cslFlag="cslFlag";
		/** 抽奖类型 [非空]   0:大转盘  如大转盘、老虎机、刮刮奖   **/
		public final static @type(Short.class)  String cslType="cslType";
		/** 开始时间 [非空]       **/
		public final static @type(Date.class)  String cslStart="cslStart";
	 	/** and csl_start >= ? */
 		public final static @type(Date.class) String cslStartStart="cslStartStart";
 		/** and csl_start <= ? */
 		public final static @type(Date.class) String cslStartEnd="cslStartEnd";
		/** 结束时间 [非空]       **/
		public final static @type(Date.class)  String cslFinish="cslFinish";
	 	/** and csl_finish >= ? */
 		public final static @type(Date.class) String cslFinishStart="cslFinishStart";
 		/** and csl_finish <= ? */
 		public final static @type(Date.class) String cslFinishEnd="cslFinishEnd";
		/** 抽奖价格 [非空]       **/
		public final static @type(Double.class)  String cslPrice="cslPrice";
		/** and csl_price >= ? */
		public final static @type(Double.class) String cslPriceMin="cslPriceMin";
		/** and csl_price <= ? */
		public final static @type(Double.class) String cslPriceMax="cslPriceMax";
		/** 编辑人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String cslEditor="cslEditor";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cslUpdateTime="cslUpdateTime";
	 	/** and csl_update_time >= ? */
 		public final static @type(Date.class) String cslUpdateTimeStart="cslUpdateTimeStart";
 		/** and csl_update_time <= ? */
 		public final static @type(Date.class) String cslUpdateTimeEnd="cslUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cslAddTime="cslAddTime";
	 	/** and csl_add_time >= ? */
 		public final static @type(Date.class) String cslAddTimeStart="cslAddTimeStart";
 		/** and csl_add_time <= ? */
 		public final static @type(Date.class) String cslAddTimeEnd="cslAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String cslStatus="cslStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cslId="csl_id";
		/** 活动名称 [非空]       **/
		public final static String cslName="csl_name";
		/** 活动标识     尽量不要改   **/
		public final static String cslFlag="csl_flag";
		/** 抽奖类型 [非空]   0:大转盘  如大转盘、老虎机、刮刮奖   **/
		public final static String cslType="csl_type";
		/** 开始时间 [非空]       **/
		public final static String cslStart="csl_start";
		/** 结束时间 [非空]       **/
		public final static String cslFinish="csl_finish";
		/** 抽奖价格 [非空]       **/
		public final static String cslPrice="csl_price";
		/** 编辑人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cslEditor="csl_editor";
		/** 修改时间 [非空]       **/
		public final static String cslUpdateTime="csl_update_time";
		/** 添加时间 [非空]       **/
		public final static String cslAddTime="csl_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String cslStatus="csl_status";
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
				$.Set(name,CsLucky.getCsLucky(params));
			else
				$.Set(name,CsLucky.getCsLuckyList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取抽奖活动数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsLucky) $.GetRequest("CsLucky$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsLucky.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsLucky.getCsLucky(params);
			else
				value = CsLucky.getCsLuckyList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsLucky.Get($.add(CsLucky.F.cslId,param));
		else if(!$.empty(param.toString()))
			value = CsLucky.get(Long.valueOf(param.toString()));
		$.SetRequest("CsLucky$"+param.hashCode(), value);
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
	public void mergeSet(CsLucky old){
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