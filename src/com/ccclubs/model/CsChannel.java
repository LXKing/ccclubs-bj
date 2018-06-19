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

@namespace("extension/channel")
public @caption("访问渠道") @table("cs_channel") class CsChannel implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csc_id")   @primary  @note("  ") Long cscId;// 主键 非空     
	private @caption("城市") @column("csc_host")    @relate("$cscHost") @RelateClass(SrvHost.class)  @note("  ") Long cscHost;// 非空     
 	private SrvHost $cscHost;//关联对象[运营城市]
	private @caption("渠道名称") @column("csc_name")    @note("  ") String cscName;// 非空     
	private @caption("渠道类型") @column("csc_type")    @relate("$cscType") @RelateClass(SrvProperty.class)  @note(" channel_type:渠道类型  ") Long cscType;// 非空 channel_type:渠道类型     
 	private SrvProperty $cscType;//关联对象[系统属性]
	private @caption("开启V指标") @column("csc_v_index")    @note("  是否开启UV,PV,IP指标") Boolean cscVIndex;// 非空  是否开启UV,PV,IP指标   
	//private @caption("指标") @RelateClass(CsChannelIndex.class) List<CsChannelIndex> indexes;//反向关联的渠道指标列表
	//private @caption("跟踪维护") @RelateClass(CsChannelLog.class) List<CsChannelLog> logs;//反向关联的渠道跟踪维护列表
	private @caption("描述说明") @column("csc_desc")    @note("  ") String cscDesc;//     
	private @caption("修改时间") @column("csc_update_time")    @note("  ") Date cscUpdateTime;// 非空     
	private @caption("添加时间") @column("csc_add_time")    @note("  ") Date cscAddTime;// 非空     
	private @caption("状态") @column("csc_status")    @note(" 1:正常 0:无效  ") Short cscStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsChannel(){
	
	}
	
	//主键构造函数
	public CsChannel(Long id){
		this.cscId = id;
	}
	
	/**所有字段构造函数 CsChannel(cscHost,cscName,cscType,cscVIndex,cscDesc,cscUpdateTime,cscAddTime,cscStatus)
	 CsChannel(
	 	$.getLong("cscHost")//城市 [非空]
	 	,$.getString("cscName")//渠道名称 [非空]
	 	,$.getLong("cscType")//渠道类型 [非空]
	 	,$.getBoolean("cscVIndex")//开启V指标 [非空]
	 	,$.getString("cscDesc")//描述说明
	 	,$.getDate("cscUpdateTime")//修改时间 [非空]
	 	,$.getDate("cscAddTime")//添加时间 [非空]
	 	,$.getShort("cscStatus")//状态 [非空]
	 )
	**/
	public CsChannel(Long cscHost,String cscName,Long cscType,Boolean cscVIndex,String cscDesc,Date cscUpdateTime,Date cscAddTime,Short cscStatus){
		this.cscHost=cscHost;
		this.cscName=cscName;
		this.cscType=cscType;
		this.cscVIndex=cscVIndex;
		this.cscDesc=cscDesc;
		this.cscUpdateTime=cscUpdateTime;
		this.cscAddTime=cscAddTime;
		this.cscStatus=cscStatus;
	}
	
	//设置非空字段
	public CsChannel setNotNull(Long cscHost,String cscName,Long cscType,Boolean cscVIndex,Date cscUpdateTime,Date cscAddTime,Short cscStatus){
		this.cscHost=cscHost;
		this.cscName=cscName;
		this.cscType=cscType;
		this.cscVIndex=cscVIndex;
		this.cscUpdateTime=cscUpdateTime;
		this.cscAddTime=cscAddTime;
		this.cscStatus=cscStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsChannel cscId(Long cscId){
		this.cscId = cscId;
		this.setSeted(F.cscId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsChannel cscHost(Long cscHost){
		this.cscHost = cscHost;
		this.setSeted(F.cscHost);
		return this;
	}
	/** 渠道名称 [非空]       **/
	public CsChannel cscName(String cscName){
		this.cscName = cscName;
		this.setSeted(F.cscName);
		return this;
	}
	/** 渠道类型 [非空] [SrvProperty]  channel_type:渠道类型     **/
	public CsChannel cscType(Long cscType){
		this.cscType = cscType;
		this.setSeted(F.cscType);
		return this;
	}
	/** 开启V指标 [非空]    是否开启UV,PV,IP指标   **/
	public CsChannel cscVIndex(Boolean cscVIndex){
		this.cscVIndex = cscVIndex;
		this.setSeted(F.cscVIndex);
		return this;
	}
	/** 描述说明        **/
	public CsChannel cscDesc(String cscDesc){
		this.cscDesc = cscDesc;
		this.setSeted(F.cscDesc);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsChannel cscUpdateTime(Date cscUpdateTime){
		this.cscUpdateTime = cscUpdateTime;
		this.setSeted(F.cscUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsChannel cscAddTime(Date cscAddTime){
		this.cscAddTime = cscAddTime;
		this.setSeted(F.cscAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsChannel cscStatus(Short cscStatus){
		this.cscStatus = cscStatus;
		this.setSeted(F.cscStatus);
		return this;
	}
	
	
	//克隆对象
	public CsChannel clone(){
		CsChannel clone = new CsChannel();
		clone.cscHost=this.cscHost;
		clone.cscName=this.cscName;
		clone.cscType=this.cscType;
		clone.cscVIndex=this.cscVIndex;
		clone.cscUpdateTime=this.cscUpdateTime;
		clone.cscAddTime=this.cscAddTime;
		clone.cscStatus=this.cscStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取访问渠道
	 * @param id
	 * @return
	 */
	public static @api CsChannel get(Long id){		
		return getCsChannelById(id);
	}
	/**
	 * 获取所有访问渠道
	 * @return
	 */
	public static @api List<CsChannel> list(Map params,Integer size){
		return getCsChannelList(params,size);
	}
	/**
	 * 获取访问渠道分页
	 * @return
	 */
	public static @api Page<CsChannel> page(int page,int size,Map params){
		return getCsChannelPage(page, size , params);
	}
	/**
	 * 根据查询条件取访问渠道
	 * @param params
	 * @return
	 */
	public static @api CsChannel Get(Map params){
		return getCsChannel(params);
	}
	/**
	 * 获取访问渠道数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsChannelCount(params);
	}
	/**
	 * 获取访问渠道数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsChannelEval(eval,params);
	}
	
	/**
	 * 根据ID取访问渠道
	 * @param id
	 * @return
	 */
	public static @api CsChannel getCsChannelById(Long id){		
		CsChannel csChannel = (CsChannel) $.GetRequest("CsChannel$"+id);
		if(csChannel!=null)
			return csChannel;
		com.ccclubs.service.admin.ICsChannelService csChannelService = $.GetSpringBean("csChannelService");		
		return csChannelService.getCsChannelById(id);
	}
	
	
	/**
	 * 根据ID取访问渠道的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsChannel.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsChannel csChannel = get(id);
		if(csChannel!=null){
			String strValue = csChannel.getCscName$();
			if(!"CscName".equals("CscName"))
				strValue+="("+csChannel.getCscName$()+")";
			MemCache.setValue(CsChannel.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCscName$();
		if(!"CscName".equals("CscName"))
			keyValue+="("+this.getCscName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有访问渠道
	 * @return
	 */
	public static @api List<CsChannel> getCsChannelList(Map params,Integer size){
		com.ccclubs.service.admin.ICsChannelService csChannelService = $.GetSpringBean("csChannelService");
		return csChannelService.getCsChannelList(params, size);
	}
	
	/**
	 * 获取访问渠道分页
	 * @return
	 */
	public static @api Page<CsChannel> getCsChannelPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsChannelService csChannelService = $.GetSpringBean("csChannelService");
		return csChannelService.getCsChannelPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取访问渠道
	 * @param params
	 * @return
	 */
	public static @api CsChannel getCsChannel(Map params){
		com.ccclubs.service.admin.ICsChannelService csChannelService = $.GetSpringBean("csChannelService");
		return csChannelService.getCsChannel(params);
	}
	
	/**
	 * 获取访问渠道数
	 * @return
	 */
	public static @api Long getCsChannelCount(Map params){
		com.ccclubs.service.admin.ICsChannelService csChannelService = $.GetSpringBean("csChannelService");
		return csChannelService.getCsChannelCount(params);
	}
		
		
	/**
	 * 获取访问渠道自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsChannelEval(String eval,Map params){
		com.ccclubs.service.admin.ICsChannelService csChannelService = $.GetSpringBean("csChannelService");
		return csChannelService.getCsChannelEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsChannel(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsChannelService csChannelService = $.GetSpringBean("csChannelService");
		csChannelService.updateCsChannelByConfirm(set, where);
	}
	
	
	/**
	 * 保存访问渠道对象
	 * @param params
	 * @return
	 */
	public CsChannel save(){
		com.ccclubs.service.admin.ICsChannelService csChannelService = $.GetSpringBean("csChannelService");
		if(this.getCscId()!=null)
			csChannelService.updateCsChannel(this);
		else
			return csChannelService.saveCsChannel(this);
		return this;
	}
	
	
	/**
	 * 更新访问渠道对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsChannelService csChannelService = $.GetSpringBean("csChannelService");
		csChannelService.updateCsChannel$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsChannelService csChannelService = $.GetSpringBean("csChannelService");
		if($.equals($.config("logic_delete"),"true"))
			csChannelService.removeCsChannelById(this.getCscId());
		else
			csChannelService.deleteCsChannelById(this.getCscId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsChannelService csChannelService = $.GetSpringBean("csChannelService");
		return csChannelService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCscId(){
		return this.cscId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCscId$(){
		String strValue="";
		 strValue=$.str(this.getCscId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCscId(Long cscId){
		this.cscId = cscId;
		this.setSeted(F.cscId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCscHost(){
		return this.cscHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCscHost$(){
		String strValue="";
		if(this.getCscHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCscHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCscHost(Long cscHost){
		this.cscHost = cscHost;
		this.setSeted(F.cscHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cscHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCscHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCscHost()!=null){
 			srvHost = SrvHost.get(this.getCscHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCscHost(), srvHost);
	 	return srvHost;
	}
	/*******************************渠道名称**********************************/	
	/**
	* 渠道名称 [非空]      
	**/
	public String getCscName(){
		return this.cscName;
	}
	/**
	* 获取渠道名称格式化(toString)
	**/
	public String getCscName$(){
		String strValue="";
		 strValue=$.str(this.getCscName());
	 	 return strValue;
	}
	/**
	* 渠道名称 [非空]      
	**/
	public void setCscName(String cscName){
		this.cscName = cscName;
		this.setSeted(F.cscName);
	}
	/*******************************渠道类型**********************************/	
	/**
	* 渠道类型 [非空] [SrvProperty]  channel_type:渠道类型    
	**/
	public Long getCscType(){
		return this.cscType;
	}
	/**
	* 获取渠道类型格式化(toString)
	**/
	public String getCscType$(){
		String strValue="";
		if(this.getCscType()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCscType()));
		}			
	 	 return strValue;
	}
	/**
	* 渠道类型 [非空] [SrvProperty]  channel_type:渠道类型    
	**/
	public void setCscType(Long cscType){
		this.cscType = cscType;
		this.setSeted(F.cscType);
	}
	/**
	* 获取关联对象[系统属性]
	**/	 			
 	public SrvProperty get$cscType(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCscType());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCscType()!=null){
 			srvProperty = SrvProperty.get(this.getCscType());
 		}
 		$.SetRequest("SrvProperty$"+this.getCscType(), srvProperty);
	 	return srvProperty;
	}
	/*******************************开启V指标**********************************/	
	/**
	* 开启V指标 [非空]    是否开启UV,PV,IP指标  
	**/
	public Boolean getCscVIndex(){
		return this.cscVIndex;
	}
	/**
	* 获取开启V指标格式化(toString)
	**/
	public String getCscVIndex$(){
		String strValue="";
		  strValue=$.str((this.getCscVIndex()!=null && this.getCscVIndex())?"是":"否");			
	 	 return strValue;
	}
	/**
	* 开启V指标 [非空]    是否开启UV,PV,IP指标  
	**/
	public void setCscVIndex(Boolean cscVIndex){
		this.cscVIndex = cscVIndex;
		this.setSeted(F.cscVIndex);
	}
	/*******************************反向关联的渠道指标列表**********************************/	
	/**
	* 获取与当前对象反向关联的渠道指标列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsChannelIndex> getIndexes(){
		com.ccclubs.service.admin.ICsChannelIndexService csChannelIndexService = $.GetSpringBean("csChannelIndexService");
		Map params = new HashMap();
		params.put("csciPlan",this.getCscId());
		return csChannelIndexService.getCsChannelIndexList(params,-1);
	}
	/**
	* 获取指标格式化(toString)
	**/
	public String getIndexes$(){
		return "[...]";
	}
	/*******************************反向关联的渠道跟踪维护列表**********************************/	
	/**
	* 获取与当前对象反向关联的渠道跟踪维护列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsChannelLog> getLogs(){
		com.ccclubs.service.admin.ICsChannelLogService csChannelLogService = $.GetSpringBean("csChannelLogService");
		Map params = new HashMap();
		params.put("csclPlan",this.getCscId());
		return csChannelLogService.getCsChannelLogList(params,-1);
	}
	/**
	* 获取跟踪维护格式化(toString)
	**/
	public String getLogs$(){
		return "[...]";
	}
	/*******************************描述说明**********************************/	
	/**
	* 描述说明       
	**/
	public String getCscDesc(){
		return this.cscDesc;
	}
	/**
	* 获取描述说明格式化(toString)
	**/
	public String getCscDesc$(){
		String strValue="";
		 strValue=$.str(this.getCscDesc());
	 	 return strValue;
	}
	/**
	* 描述说明       
	**/
	public void setCscDesc(String cscDesc){
		this.cscDesc = cscDesc;
		this.setSeted(F.cscDesc);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCscUpdateTime(){
		return this.cscUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCscUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCscUpdateTime(Date cscUpdateTime){
		this.cscUpdateTime = cscUpdateTime;
		this.setSeted(F.cscUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCscAddTime(){
		return this.cscAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCscAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCscAddTime(Date cscAddTime){
		this.cscAddTime = cscAddTime;
		this.setSeted(F.cscAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCscStatus(){
		return this.cscStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCscStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCscStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCscStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCscStatus(Short cscStatus){
		this.cscStatus = cscStatus;
		this.setSeted(F.cscStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsChannel.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsChannel.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsChannel.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsChannel.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsChannel.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsChannel.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsChannel.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsChannel.this);
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
		public M cscId(Object cscId){this.put("cscId", cscId);return this;};
	 	/** and csc_id is null */
 		public M cscIdNull(){if(this.get("cscIdNot")==null)this.put("cscIdNot", "");this.put("cscId", null);return this;};
 		/** not .... */
 		public M cscIdNot(){this.put("cscIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M cscHost(Object cscHost){this.put("cscHost", cscHost);return this;};
	 	/** and csc_host is null */
 		public M cscHostNull(){if(this.get("cscHostNot")==null)this.put("cscHostNot", "");this.put("cscHost", null);return this;};
 		/** not .... */
 		public M cscHostNot(){this.put("cscHostNot", "not");return this;};
		/** 渠道名称 [非空]       **/
		public M cscName(Object cscName){this.put("cscName", cscName);return this;};
	 	/** and csc_name is null */
 		public M cscNameNull(){if(this.get("cscNameNot")==null)this.put("cscNameNot", "");this.put("cscName", null);return this;};
 		/** not .... */
 		public M cscNameNot(){this.put("cscNameNot", "not");return this;};
		/** 渠道类型 [非空] [SrvProperty]  channel_type:渠道类型     **/
		public M cscType(Object cscType){this.put("cscType", cscType);return this;};
	 	/** and csc_type is null */
 		public M cscTypeNull(){if(this.get("cscTypeNot")==null)this.put("cscTypeNot", "");this.put("cscType", null);return this;};
 		/** not .... */
 		public M cscTypeNot(){this.put("cscTypeNot", "not");return this;};
		public M cscType$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("cscType$on", value);
			return this;
		};	
		/** 开启V指标 [非空]    是否开启UV,PV,IP指标   **/
		public M cscVIndex(Object cscVIndex){this.put("cscVIndex", cscVIndex);return this;};
	 	/** and csc_v_index is null */
 		public M cscVIndexNull(){if(this.get("cscVIndexNot")==null)this.put("cscVIndexNot", "");this.put("cscVIndex", null);return this;};
 		/** not .... */
 		public M cscVIndexNot(){this.put("cscVIndexNot", "not");return this;};
		/** 描述说明        **/
		public M cscDesc(Object cscDesc){this.put("cscDesc", cscDesc);return this;};
	 	/** and csc_desc is null */
 		public M cscDescNull(){if(this.get("cscDescNot")==null)this.put("cscDescNot", "");this.put("cscDesc", null);return this;};
 		/** not .... */
 		public M cscDescNot(){this.put("cscDescNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M cscUpdateTime(Object cscUpdateTime){this.put("cscUpdateTime", cscUpdateTime);return this;};
	 	/** and csc_update_time is null */
 		public M cscUpdateTimeNull(){if(this.get("cscUpdateTimeNot")==null)this.put("cscUpdateTimeNot", "");this.put("cscUpdateTime", null);return this;};
 		/** not .... */
 		public M cscUpdateTimeNot(){this.put("cscUpdateTimeNot", "not");return this;};
 		/** and csc_update_time >= ? */
 		public M cscUpdateTimeStart(Object start){this.put("cscUpdateTimeStart", start);return this;};			
 		/** and csc_update_time <= ? */
 		public M cscUpdateTimeEnd(Object end){this.put("cscUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cscAddTime(Object cscAddTime){this.put("cscAddTime", cscAddTime);return this;};
	 	/** and csc_add_time is null */
 		public M cscAddTimeNull(){if(this.get("cscAddTimeNot")==null)this.put("cscAddTimeNot", "");this.put("cscAddTime", null);return this;};
 		/** not .... */
 		public M cscAddTimeNot(){this.put("cscAddTimeNot", "not");return this;};
 		/** and csc_add_time >= ? */
 		public M cscAddTimeStart(Object start){this.put("cscAddTimeStart", start);return this;};			
 		/** and csc_add_time <= ? */
 		public M cscAddTimeEnd(Object end){this.put("cscAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M cscStatus(Object cscStatus){this.put("cscStatus", cscStatus);return this;};
	 	/** and csc_status is null */
 		public M cscStatusNull(){if(this.get("cscStatusNot")==null)this.put("cscStatusNot", "");this.put("cscStatus", null);return this;};
 		/** not .... */
 		public M cscStatusNot(){this.put("cscStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有访问渠道 **/
		public @api List<CsChannel> list(Integer size){
			return getCsChannelList(this,size);
		}
		/** 获取访问渠道分页 **/
		public @api Page<CsChannel> page(int page,int size){
			return getCsChannelPage(page, size , this);
		}
		/** 根据查询条件取访问渠道 **/
		public @api CsChannel get(){
			return getCsChannel(this);
		}
		/** 获取访问渠道数 **/
		public @api Long count(){
			return getCsChannelCount(this);
		}
		/** 获取访问渠道表达式 **/
		public @api <T> T eval(String strEval){
			return getCsChannelEval(strEval,this);
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
			updateCsChannel(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscId="cscId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cscHost="cscHost";
		/** 渠道名称 [非空]       **/
		public final static @type(String.class) @like String cscName="cscName";
		/** 渠道类型 [非空] [SrvProperty]  channel_type:渠道类型     **/
		public final static @type(Long.class)  String cscType="cscType";
		/** 开启V指标 [非空]    是否开启UV,PV,IP指标   **/
		public final static @type(Boolean.class)  String cscVIndex="cscVIndex";
		/** 描述说明        **/
		public final static @type(String.class) @like String cscDesc="cscDesc";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cscUpdateTime="cscUpdateTime";
	 	/** and csc_update_time >= ? */
 		public final static @type(Date.class) String cscUpdateTimeStart="cscUpdateTimeStart";
 		/** and csc_update_time <= ? */
 		public final static @type(Date.class) String cscUpdateTimeEnd="cscUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cscAddTime="cscAddTime";
	 	/** and csc_add_time >= ? */
 		public final static @type(Date.class) String cscAddTimeStart="cscAddTimeStart";
 		/** and csc_add_time <= ? */
 		public final static @type(Date.class) String cscAddTimeEnd="cscAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String cscStatus="cscStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscId="csc_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cscHost="csc_host";
		/** 渠道名称 [非空]       **/
		public final static String cscName="csc_name";
		/** 渠道类型 [非空] [SrvProperty]  channel_type:渠道类型     **/
		public final static String cscType="csc_type";
		/** 开启V指标 [非空]    是否开启UV,PV,IP指标   **/
		public final static String cscVIndex="csc_v_index";
		/** 描述说明        **/
		public final static String cscDesc="csc_desc";
		/** 修改时间 [非空]       **/
		public final static String cscUpdateTime="csc_update_time";
		/** 添加时间 [非空]       **/
		public final static String cscAddTime="csc_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String cscStatus="csc_status";
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
				$.Set(name,CsChannel.getCsChannel(params));
			else
				$.Set(name,CsChannel.getCsChannelList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取访问渠道数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsChannel) $.GetRequest("CsChannel$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsChannel.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsChannel.getCsChannel(params);
			else
				value = CsChannel.getCsChannelList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsChannel.Get($.add(CsChannel.F.cscId,param));
		else if(!$.empty(param.toString()))
			value = CsChannel.get(Long.valueOf(param.toString()));
		$.SetRequest("CsChannel$"+param.hashCode(), value);
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
	public void mergeSet(CsChannel old){
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