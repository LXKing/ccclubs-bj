package com.ccclubs.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.ccclubs.model.CsSpecialCar.M;
import com.lazy3q.web.helper.$;

import com.lazy3q.web.tags.BodyTag;

import com.lazy3q.web.util.Page;
import com.lazy3q.lang.*;
import com.lazy3q.util.Function;
import com.ccclubs.dao.impl.MemCache;

@namespace("object/serv")
public @caption("服务监控报警") @table("cs_serv_alarm") class CsServAlarm implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cssa_id")   @primary   @note("  ") Long cssaId;
	private @caption("所在城市") @column("cssa_host")    @relate("$cssaHost") @RelateClass(SrvHost.class)  @note("  ") Long cssaHost;
	private SrvHost $cssaHost;//关联对象[所在城市]
	private @caption("网点") @column("cssa_outlets")    @relate("$cssaOutlets") @RelateClass(CsOutlets.class)  @note("  ") Long cssaOutlets;
	private CsOutlets $cssaOutlets;//关联对象[网点]
	private @caption("报警分类") @column("cssa_type")    @relate("$cssaType") @RelateClass(SrvProperty.class)  @note("  ") Long cssaType;
	private SrvProperty $cssaType;//关联对象[报警分类]
	private @caption("报警标题") @column("cssa_title")      @note("  ") String cssaTitle;
	private @caption("报警内容") @column("cssa_content")      @note("  ") String cssaContent;
	private @caption("监控对象") @column("cssa_relate")      @note("  ") String cssaRelate;
	private @caption("来源") @column("cssa_srouce")      @note("  ") String cssaSrouce;
	private @caption("报警状态") @column("cssa_status")      @note(" 1:正常 0:无效  ") Short cssaStatus;
	private @caption("其它数据") @column("cssa_data")      @note("  ") String cssaData;
	private @caption("创建时间") @column("cssa_add_time")      @note("  ") Date cssaAddTime;
	private @caption("修改时间") @column("cssa_update_time")      @note("  ") Date cssaUpdateTime;
	
	//默认构造函数
	public CsServAlarm(){
	
	}
	
	//主键构造函数
	public CsServAlarm(Long id){
		this.cssaId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsServAlarm(Long cssaHost,Long cssaOutlets,Long cssaType,String cssaTitle,String cssaContent,String cssaRelate,String cssaSrouce,Short cssaStatus,String cssaData,Date cssaAddTime,Date cssaUpdateTime){
		this.cssaHost=cssaHost;
		this.cssaOutlets=cssaOutlets;
		this.cssaType=cssaType;
		this.cssaTitle=cssaTitle;
		this.cssaContent=cssaContent;
		this.cssaRelate=cssaRelate;
		this.cssaSrouce=cssaSrouce;
		this.cssaStatus=cssaStatus;
		this.cssaData=cssaData;
		this.cssaAddTime=cssaAddTime;
		this.cssaUpdateTime=cssaUpdateTime;
	}
	
	//设置非空字段
	public CsServAlarm setNotNull(Long cssaType){
		this.cssaType=cssaType;
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsServAlarm cssaId(Long cssaId){
		this.cssaId = cssaId;
		this.setSeted(F.cssaId);
		return this;
	}
	/** 所在城市 [非空]       **/
	public CsServAlarm cssaHost(Long cssaHost){
		this.cssaHost = cssaHost;
		this.setSeted(F.cssaHost);
		return this;
	}
	/** 网点 [非空]       **/
	public CsServAlarm cssaOutlets(Long cssaOutlets){
		this.cssaOutlets = cssaOutlets;
		this.setSeted(F.cssaOutlets);
		return this;
	}
	/** 报警分类 [可空]       **/
	public CsServAlarm cssaType(Long cssaType){
		this.cssaType = cssaType;
		this.setSeted(F.cssaType);
		return this;
	}
	/** 报警标题 [非空]       **/
	public CsServAlarm cssaTitle(String cssaTitle){
		this.cssaTitle = cssaTitle;
		this.setSeted(F.cssaTitle);
		return this;
	}
	/** 报警内容 [非空]       **/
	public CsServAlarm cssaContent(String cssaContent){
		this.cssaContent = cssaContent;
		this.setSeted(F.cssaContent);
		return this;
	}
	/** 监控对象 [非空]       **/
	public CsServAlarm cssaRelate(String cssaRelate){
		this.cssaRelate = cssaRelate;
		this.setSeted(F.cssaRelate);
		return this;
	}
	/** 来源 [非空]       **/
	public CsServAlarm cssaSrouce(String cssaSrouce){
		this.cssaSrouce = cssaSrouce;
		this.setSeted(F.cssaSrouce);
		return this;
	}
	/** 报警状态 [非空]       **/
	public CsServAlarm cssaStatus(Short cssaStatus){
		this.cssaStatus = cssaStatus;
		this.setSeted(F.cssaStatus);
		return this;
	}
	/** 其它数据 [非空]       **/
	public CsServAlarm cssaData(String cssaData){
		this.cssaData = cssaData;
		this.setSeted(F.cssaData);
		return this;
	}
	/** 创建时间 [非空]       **/
	public CsServAlarm cssaAddTime(Date cssaAddTime){
		this.cssaAddTime = cssaAddTime;
		this.setSeted(F.cssaAddTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsServAlarm cssaUpdateTime(Date cssaUpdateTime){
		this.cssaUpdateTime = cssaUpdateTime;
		this.setSeted(F.cssaUpdateTime);
		return this;
	}
	
	//克隆对象
	public CsServAlarm clone(){
		CsServAlarm clone = new CsServAlarm();
		clone.cssaId=this.cssaId;
		clone.cssaHost=this.cssaHost;
		clone.cssaOutlets=this.cssaOutlets;
		clone.cssaType=this.cssaType;
		clone.cssaTitle=this.cssaTitle;
		clone.cssaContent=this.cssaContent;
		clone.cssaRelate=this.cssaRelate;
		clone.cssaSrouce=this.cssaSrouce;
		clone.cssaStatus=this.cssaStatus;
		clone.cssaData=this.cssaData;
		clone.cssaAddTime=this.cssaAddTime;
		clone.cssaUpdateTime=this.cssaUpdateTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取服务监控报警
	 * @param id
	 * @return
	 */
	public static @api CsServAlarm get(Long id){		
		return getCsServAlarmById(id);
	}
	/**
	 * 获取所有服务监控报警
	 * @return
	 */
	public static @api List<CsServAlarm> list(Map params,Integer size){
		return getCsServAlarmList(params,size);
	}
	/**
	 * 获取服务监控报警分页
	 * @return
	 */
	public static @api Page<CsServAlarm> page(int page,int size,Map params){
		return getCsServAlarmPage(page, size , params);
	}
	/**
	 * 根据查询条件取服务监控报警
	 * @param params
	 * @return
	 */
	public static @api CsServAlarm Get(Map params){
		return getCsServAlarm(params);
	}
	/**
	 * 获取服务监控报警数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsServAlarmCount(params);
	}
	/**
	 * 获取服务监控报警数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsServAlarmEval(eval,params);
	}
	
	/**
	 * 根据ID取服务监控报警
	 * @param id
	 * @return
	 */
	public static @api CsServAlarm getCsServAlarmById(Long id){		
		CsServAlarm csServAlarm = (CsServAlarm) $.GetRequest("CsServAlarm$"+id);
		if(csServAlarm!=null)
			return csServAlarm;
		com.ccclubs.service.admin.ICsServAlarmService csServAlarmService = $.GetSpringBean("csServAlarmService");		
		return csServAlarmService.getCsServAlarmById(id);
	}
	
	
	/**
	 * 根据ID取服务监控报警的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsServAlarm.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsServAlarm csServAlarm = get(id);
		if(csServAlarm!=null){
			String strValue = csServAlarm.getCssaId$();
			if(!"CssaId".equals("CssaId"))
				strValue+="("+csServAlarm.getCssaId$()+")";
			MemCache.setValue(CsServAlarm.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCssaId$();
		if(!"CssaId".equals("CssaId"))
			keyValue+="("+this.getCssaId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有服务监控报警
	 * @return
	 */
	public static @api List<CsServAlarm> getCsServAlarmList(Map params,Integer size){
		com.ccclubs.service.admin.ICsServAlarmService csServAlarmService = $.GetSpringBean("csServAlarmService");
		return csServAlarmService.getCsServAlarmList(params, size);
	}
	
	/**
	 * 获取服务监控报警分页
	 * @return
	 */
	public static @api Page<CsServAlarm> getCsServAlarmPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsServAlarmService csServAlarmService = $.GetSpringBean("csServAlarmService");
		return csServAlarmService.getCsServAlarmPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取服务监控报警
	 * @param params
	 * @return
	 */
	public static @api CsServAlarm getCsServAlarm(Map params){
		com.ccclubs.service.admin.ICsServAlarmService csServAlarmService = $.GetSpringBean("csServAlarmService");
		return csServAlarmService.getCsServAlarm(params);
	}
	
	/**
	 * 获取服务监控报警数
	 * @return
	 */
	public static @api Long getCsServAlarmCount(Map params){
		com.ccclubs.service.admin.ICsServAlarmService csServAlarmService = $.GetSpringBean("csServAlarmService");
		return csServAlarmService.getCsServAlarmCount(params);
	}
		
		
	/**
	 * 获取服务监控报警自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsServAlarmEval(String eval,Map params){
		com.ccclubs.service.admin.ICsServAlarmService csServAlarmService = $.GetSpringBean("csServAlarmService");
		return csServAlarmService.getCsServAlarmEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsServAlarm(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsServAlarmService csServAlarmService = $.GetSpringBean("csServAlarmService");
		csServAlarmService.updateCsServAlarmByConfirm(set, where);
	}
	
	
	/**
	 * 保存服务监控报警对象
	 * @param params
	 * @return
	 */
	public CsServAlarm save(){
		com.ccclubs.service.admin.ICsServAlarmService csServAlarmService = $.GetSpringBean("csServAlarmService");
		if(this.getCssaId()!=null)
			csServAlarmService.updateCsServAlarm(this);
		else
			return csServAlarmService.saveCsServAlarm(this);
		return this;
	}
	
	
	/**
	 * 更新服务监控报警对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsServAlarmService csServAlarmService = $.GetSpringBean("csServAlarmService");
		csServAlarmService.updateCsServAlarm$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsServAlarmService csServAlarmService = $.GetSpringBean("csServAlarmService");
		if($.equals($.config("logic_delete"),"true"))
			csServAlarmService.removeCsServAlarmById(this.getCssaId());
		else
			csServAlarmService.deleteCsServAlarmById(this.getCssaId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsServAlarmService csServAlarmService = $.GetSpringBean("csServAlarmService");
		return csServAlarmService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCssaId(){
		return this.cssaId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCssaId$(){
		String strValue="";
		 strValue=$.str(this.getCssaId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCssaId(Long cssaId){
		this.cssaId = cssaId;
		this.setSeted(F.cssaId);
	}
	/*******************************所在城市**********************************/	
	/**
	* 所在城市 [非空]      
	**/
	public Long getCssaHost(){
		return this.cssaHost;
	}
	/**
	* 获取所在城市格式化(toString)
	**/
	public String getCssaHost$(){
		String strValue="";
		if(this.getCssaHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCssaHost()));
		}			
	 	 return strValue;
	}
	/**
	* 所在城市 [非空]     
	**/
	public void setCssaHost(Long cssaHost){
		this.cssaHost = cssaHost;
		this.setSeted(F.cssaHost);
	}
	/**
	* 获取关联对象[所在城市]
	**/	 			
 	public SrvHost get$cssaHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCssaHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCssaHost()!=null){
			srvHost = SrvHost.get(this.getCssaHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCssaHost(), srvHost);
	 	return srvHost;
	}
	/*******************************网点**********************************/	
	/**
	* 网点 [非空]      
	**/
	public Long getCssaOutlets(){
		return this.cssaOutlets;
	}
	/**
	* 获取网点格式化(toString)
	**/
	public String getCssaOutlets$(){
		String strValue="";
		if(this.getCssaOutlets()!=null){
				strValue+=$.str(CsOutlets.getKeyValue(this.getCssaOutlets()));
		}			
	 	 return strValue;
	}
	/**
	* 网点 [非空]     
	**/
	public void setCssaOutlets(Long cssaOutlets){
		this.cssaOutlets = cssaOutlets;
		this.setSeted(F.cssaOutlets);
	}
	/**
	* 获取关联对象[网点]
	**/	 			
 	public CsOutlets get$cssaOutlets(){
 		com.ccclubs.model.CsOutlets csOutlets = (com.ccclubs.model.CsOutlets) $.GetRequest("CsOutlets$"+this.getCssaOutlets());
 		if(csOutlets!=null)
			return csOutlets;
		if(this.getCssaOutlets()!=null){
			csOutlets = CsOutlets.get(this.getCssaOutlets());
 		}
 		$.SetRequest("CsOutlets$"+this.getCssaOutlets(), csOutlets);
	 	return csOutlets;
	}
	/*******************************报警分类**********************************/	
	/**
	* 报警分类 [可空]      
	**/
	public Long getCssaType(){
		return this.cssaType;
	}
	/**
	* 获取报警分类格式化(toString)
	**/
	public String getCssaType$(){
		String strValue="";
		 if($.equals($.str(this.getCssaType()),"1"))
			 strValue=$.str("车辆掉线提醒");
		 if($.equals($.str(this.getCssaType()),"2"))
			 strValue=$.str("小电瓶亏电提醒");
		 if($.equals($.str(this.getCssaType()),"3"))
			 strValue=$.str("车辆低电量提醒");
	 	 return strValue;
	}
	/**
	* 报警分类 [可空]     
	**/
	public void setCssaType(Long cssaType){
		this.cssaType = cssaType;
		this.setSeted(F.cssaType);
	}
	/**
	* 获取关联对象[报警分类]
	**/	 			
 	public SrvProperty get$cssaType(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCssaType());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCssaType()!=null){
			srvProperty = SrvProperty.get(this.getCssaType());
 		}
 		$.SetRequest("SrvProperty$"+this.getCssaType(), srvProperty);
	 	return srvProperty;
	}
	/*******************************报警标题**********************************/	
	/**
	* 报警标题 [非空]      
	**/
	public String getCssaTitle(){
		return this.cssaTitle;
	}
	/**
	* 获取报警标题格式化(toString)
	**/
	public String getCssaTitle$(){
		String strValue="";
		 strValue=$.str(this.getCssaTitle());
	 	 return strValue;
	}
	/**
	* 报警标题 [非空]     
	**/
	public void setCssaTitle(String cssaTitle){
		this.cssaTitle = cssaTitle;
		this.setSeted(F.cssaTitle);
	}
	/*******************************报警内容**********************************/	
	/**
	* 报警内容 [非空]      
	**/
	public String getCssaContent(){
		return this.cssaContent;
	}
	/**
	* 获取报警内容格式化(toString)
	**/
	public String getCssaContent$(){
		String strValue="";
		 strValue=$.str(this.getCssaContent());
	 	 return strValue;
	}
	/**
	* 报警内容 [非空]     
	**/
	public void setCssaContent(String cssaContent){
		this.cssaContent = cssaContent;
		this.setSeted(F.cssaContent);
	}
	/*******************************监控对象**********************************/	
	/**
	* 监控对象 [非空]      
	**/
	public String getCssaRelate(){
		return this.cssaRelate;
	}
	/**
	* 获取监控对象格式化(toString)
	**/
	public String getCssaRelate$(){
		String strValue="";
		 strValue=$.str(this.getCssaRelate());
	 	 return strValue;
	}
	/**
	* 监控对象 [非空]     
	**/
	public void setCssaRelate(String cssaRelate){
		this.cssaRelate = cssaRelate;
		this.setSeted(F.cssaRelate);
	}
	/*******************************来源**********************************/	
	/**
	* 来源 [非空]      
	**/
	public String getCssaSrouce(){
		return this.cssaSrouce;
	}
	/**
	* 获取来源格式化(toString)
	**/
	public String getCssaSrouce$(){
		String strValue="";
		 strValue=$.str(this.getCssaSrouce());
	 	 return strValue;
	}
	/**
	* 来源 [非空]     
	**/
	public void setCssaSrouce(String cssaSrouce){
		this.cssaSrouce = cssaSrouce;
		this.setSeted(F.cssaSrouce);
	}
	/*******************************报警状态**********************************/	
	/**
	* 报警状态 [非空]      
	**/
	public Short getCssaStatus(){
		return this.cssaStatus;
	}
	/**
	* 获取报警状态格式化(toString)
	**/
	public String getCssaStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCssaStatus()),"1"))
			 strValue=$.str("正常");
		 if($.equals($.str(this.getCssaStatus()),"0"))
			 strValue=$.str("无效");
	 	 return strValue;
	}
	/**
	* 报警状态 [非空]     
	**/
	public void setCssaStatus(Short cssaStatus){
		this.cssaStatus = cssaStatus;
		this.setSeted(F.cssaStatus);
	}
	/*******************************其它数据**********************************/	
	/**
	* 其它数据 [非空]      
	**/
	public String getCssaData(){
		return this.cssaData;
	}
	/**
	* 获取其它数据格式化(toString)
	**/
	public String getCssaData$(){
		String strValue="";
		 strValue=$.str(this.getCssaData());
	 	 return strValue;
	}
	/**
	* 其它数据 [非空]     
	**/
	public void setCssaData(String cssaData){
		this.cssaData = cssaData;
		this.setSeted(F.cssaData);
	}
	/*******************************创建时间**********************************/	
	/**
	* 创建时间 [非空]      
	**/
	public Date getCssaAddTime(){
		return this.cssaAddTime;
	}
	/**
	* 获取创建时间格式化(toString)
	**/
	public String getCssaAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssaAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 创建时间 [非空]     
	**/
	public void setCssaAddTime(Date cssaAddTime){
		this.cssaAddTime = cssaAddTime;
		this.setSeted(F.cssaAddTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCssaUpdateTime(){
		return this.cssaUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCssaUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssaUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCssaUpdateTime(Date cssaUpdateTime){
		this.cssaUpdateTime = cssaUpdateTime;
		this.setSeted(F.cssaUpdateTime);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsServAlarm.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsServAlarm.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsServAlarm.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsServAlarm.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsServAlarm.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsServAlarm.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsServAlarm.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsServAlarm.this);
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
		public M cssaId(Object cssaId){this.put("cssaId", cssaId);return this;};
		/** and cssa_id is null */
		public M cssaIdNull(){if(this.get("cssaIdNot")==null)this.put("cssaIdNot", "");this.put("cssaId", null);return this;};
		/** not .... */
		public M cssaIdNot(){this.put("cssaIdNot", "not");return this;};
		/** and cssa_id >= ? */
		public M cssaIdMin(Object min){this.put("cssaIdMin", min);return this;};
		/** and cssa_id <= ? */
		public M cssaIdMax(Object max){this.put("cssaIdMax", max);return this;};
		/** 所在城市 [非空]       **/
		public M cssaHost(Object cssaHost){this.put("cssaHost", cssaHost);return this;};
		/** and cssa_host is null */
		public M cssaHostNull(){if(this.get("cssaHostNot")==null)this.put("cssaHostNot", "");this.put("cssaHost", null);return this;};
		/** not .... */
		public M cssaHostNot(){this.put("cssaHostNot", "not");return this;};
		/** and cssa_host >= ? */
		public M cssaHostMin(Object min){this.put("cssaHostMin", min);return this;};
		/** and cssa_host <= ? */
		public M cssaHostMax(Object max){this.put("cssaHostMax", max);return this;};
		/** 网点 [非空]       **/
		public M cssaOutlets(Object cssaOutlets){this.put("cssaOutlets", cssaOutlets);return this;};
		/** and cssa_outlets is null */
		public M cssaOutletsNull(){if(this.get("cssaOutletsNot")==null)this.put("cssaOutletsNot", "");this.put("cssaOutlets", null);return this;};
		/** not .... */
		public M cssaOutletsNot(){this.put("cssaOutletsNot", "not");return this;};
		public M cssaOutlets$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("cssaOutlets$on", value);
			return this;
		};	
		/** and cssa_outlets >= ? */
		public M cssaOutletsMin(Object min){this.put("cssaOutletsMin", min);return this;};
		/** and cssa_outlets <= ? */
		public M cssaOutletsMax(Object max){this.put("cssaOutletsMax", max);return this;};
		/** 报警分类 [可空]       **/
		public M cssaType(Object cssaType){this.put("cssaType", cssaType);return this;};
		/** and cssa_type is null */
		public M cssaTypeNull(){if(this.get("cssaTypeNot")==null)this.put("cssaTypeNot", "");this.put("cssaType", null);return this;};
		/** not .... */
		public M cssaTypeNot(){this.put("cssaTypeNot", "not");return this;};
		public M cssaType$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("cssaType$on", value);
			return this;
		};	
		/** and cssa_type >= ? */
		public M cssaTypeMin(Object min){this.put("cssaTypeMin", min);return this;};
		/** and cssa_type <= ? */
		public M cssaTypeMax(Object max){this.put("cssaTypeMax", max);return this;};
		/** 报警标题 [非空]       **/
		public M cssaTitle(Object cssaTitle){this.put("cssaTitle", cssaTitle);return this;};
		/** and cssa_title is null */
		public M cssaTitleNull(){if(this.get("cssaTitleNot")==null)this.put("cssaTitleNot", "");this.put("cssaTitle", null);return this;};
		/** not .... */
		public M cssaTitleNot(){this.put("cssaTitleNot", "not");return this;};
		/** 报警内容 [非空]       **/
		public M cssaContent(Object cssaContent){this.put("cssaContent", cssaContent);return this;};
		/** and cssa_content is null */
		public M cssaContentNull(){if(this.get("cssaContentNot")==null)this.put("cssaContentNot", "");this.put("cssaContent", null);return this;};
		/** not .... */
		public M cssaContentNot(){this.put("cssaContentNot", "not");return this;};
		/** 监控对象 [非空]       **/
		public M cssaRelate(Object cssaRelate){this.put("cssaRelate", cssaRelate);return this;};
		/** and cssa_relate is null */
		public M cssaRelateNull(){if(this.get("cssaRelateNot")==null)this.put("cssaRelateNot", "");this.put("cssaRelate", null);return this;};
		/** not .... */
		public M cssaRelateNot(){this.put("cssaRelateNot", "not");return this;};
		/** 来源 [非空]       **/
		public M cssaSrouce(Object cssaSrouce){this.put("cssaSrouce", cssaSrouce);return this;};
		/** and cssa_srouce is null */
		public M cssaSrouceNull(){if(this.get("cssaSrouceNot")==null)this.put("cssaSrouceNot", "");this.put("cssaSrouce", null);return this;};
		/** not .... */
		public M cssaSrouceNot(){this.put("cssaSrouceNot", "not");return this;};
		/** 报警状态 [非空]       **/
		public M cssaStatus(Object cssaStatus){this.put("cssaStatus", cssaStatus);return this;};
		/** and cssa_status is null */
		public M cssaStatusNull(){if(this.get("cssaStatusNot")==null)this.put("cssaStatusNot", "");this.put("cssaStatus", null);return this;};
		/** not .... */
		public M cssaStatusNot(){this.put("cssaStatusNot", "not");return this;};
		/** and cssa_status >= ? */
		public M cssaStatusMin(Object min){this.put("cssaStatusMin", min);return this;};
		/** and cssa_status <= ? */
		public M cssaStatusMax(Object max){this.put("cssaStatusMax", max);return this;};
		/** 其它数据 [非空]       **/
		public M cssaData(Object cssaData){this.put("cssaData", cssaData);return this;};
		/** and cssa_data is null */
		public M cssaDataNull(){if(this.get("cssaDataNot")==null)this.put("cssaDataNot", "");this.put("cssaData", null);return this;};
		/** not .... */
		public M cssaDataNot(){this.put("cssaDataNot", "not");return this;};
		/** 创建时间 [非空]       **/
		public M cssaAddTime(Object cssaAddTime){this.put("cssaAddTime", cssaAddTime);return this;};
		/** and cssa_add_time is null */
		public M cssaAddTimeNull(){if(this.get("cssaAddTimeNot")==null)this.put("cssaAddTimeNot", "");this.put("cssaAddTime", null);return this;};
		/** not .... */
		public M cssaAddTimeNot(){this.put("cssaAddTimeNot", "not");return this;};
		/** and cssa_add_time >= ? */
 		public M cssaAddTimeStart(Object start){this.put("cssaAddTimeStart", start);return this;};			
 		/** and cssa_add_time <= ? */
 		public M cssaAddTimeEnd(Object end){this.put("cssaAddTimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M cssaUpdateTime(Object cssaUpdateTime){this.put("cssaUpdateTime", cssaUpdateTime);return this;};
		/** and cssa_update_time is null */
		public M cssaUpdateTimeNull(){if(this.get("cssaUpdateTimeNot")==null)this.put("cssaUpdateTimeNot", "");this.put("cssaUpdateTime", null);return this;};
		/** not .... */
		public M cssaUpdateTimeNot(){this.put("cssaUpdateTimeNot", "not");return this;};
		/** and cssa_update_time >= ? */
 		public M cssaUpdateTimeStart(Object start){this.put("cssaUpdateTimeStart", start);return this;};			
 		/** and cssa_update_time <= ? */
 		public M cssaUpdateTimeEnd(Object end){this.put("cssaUpdateTimeEnd", end);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有服务监控报警 **/
		public @api List<CsServAlarm> list(Integer size){
			return getCsServAlarmList(this,size);
		}
		/** 获取服务监控报警分页 **/
		public @api Page<CsServAlarm> page(int page,int size){
			return getCsServAlarmPage(page, size , this);
		}
		/** 根据查询条件取服务监控报警 **/
		public @api CsServAlarm get(){
			return getCsServAlarm(this);
		}
		/** 获取服务监控报警数 **/
		public @api Long count(){
			return getCsServAlarmCount(this);
		}
		/** 获取服务监控报警表达式 **/
		public @api <T> T eval(String strEval){
			return getCsServAlarmEval(strEval,this);
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
			updateCsServAlarm(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cssaId="cssaId";
		/** 所在城市 [非空]       **/
		public final static @type(Long.class)  String cssaHost="cssaHost";
		/** 网点 [非空]       **/
		public final static @type(Long.class)  String cssaOutlets="cssaOutlets";
		/** 报警分类 [可空]       **/
		public final static @type(Long.class)  String cssaType="cssaType";
		/** 报警标题 [非空]       **/
		public final static @type(String.class)  String cssaTitle="cssaTitle";
		/** 报警内容 [非空]       **/
		public final static @type(String.class)  String cssaContent="cssaContent";
		/** 监控对象 [非空]       **/
		public final static @type(String.class)  String cssaRelate="cssaRelate";
		/** 来源 [非空]       **/
		public final static @type(String.class)  String cssaSrouce="cssaSrouce";
		/** 报警状态 [非空]       **/
		public final static @type(Short.class)  String cssaStatus="cssaStatus";
		/** 其它数据 [非空]       **/
		public final static @type(String.class)  String cssaData="cssaData";
		/** 创建时间 [非空]       **/
		public final static @type(Date.class)  String cssaAddTime="cssaAddTime";
		/** and cssa_add_time >= ? */
 		public final static @type(Date.class) String cssaAddTimeStart="cssaAddTimeStart";
 		/** and cssa_add_time <= ? */
 		public final static @type(Date.class) String cssaAddTimeEnd="cssaAddTimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cssaUpdateTime="cssaUpdateTime";
		/** and cssa_update_time >= ? */
 		public final static @type(Date.class) String cssaUpdateTimeStart="cssaUpdateTimeStart";
 		/** and cssa_update_time <= ? */
 		public final static @type(Date.class) String cssaUpdateTimeEnd="cssaUpdateTimeEnd";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cssaId="cssa_id";
		/** 所在城市 [非空]       **/
		public final static String cssaHost="cssa_host";
		/** 网点 [非空]       **/
		public final static String cssaOutlets="cssa_outlets";
		/** 报警分类 [可空]       **/
		public final static String cssaType="cssa_type";
		/** 报警标题 [非空]       **/
		public final static String cssaTitle="cssa_title";
		/** 报警内容 [非空]       **/
		public final static String cssaContent="cssa_content";
		/** 监控对象 [非空]       **/
		public final static String cssaRelate="cssa_relate";
		/** 来源 [非空]       **/
		public final static String cssaSrouce="cssa_srouce";
		/** 报警状态 [非空]       **/
		public final static String cssaStatus="cssa_status";
		/** 其它数据 [非空]       **/
		public final static String cssaData="cssa_data";
		/** 创建时间 [非空]       **/
		public final static String cssaAddTime="cssa_add_time";
		/** 修改时间 [非空]       **/
		public final static String cssaUpdateTime="cssa_update_time";
		
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
				$.Set(name,CsServAlarm.getCsServAlarm(params));
			else
				$.Set(name,CsServAlarm.getCsServAlarmList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取服务监控报警数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsServAlarm) $.GetRequest("CsServAlarm$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsServAlarm.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsServAlarm.getCsServAlarm(params);
			else
				value = CsServAlarm.getCsServAlarmList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsServAlarm.Get($.add(CsServAlarm.F.cssaId,param));
		else if(!$.empty(param.toString()))
			value = CsServAlarm.get(Long.valueOf(param.toString()));
		$.SetRequest("CsServAlarm$"+param.hashCode(), value);
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
	public void mergeSet(CsServAlarm old){
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