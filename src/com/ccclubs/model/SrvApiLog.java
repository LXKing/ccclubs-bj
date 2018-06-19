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

@namespace("systematic/apilog")
public @caption("API日志") @table("srv_api_log") class SrvApiLog implements java.io.Serializable
{
	private static final long serialVersionUID =          1l;
	private @caption("编号") @column("sal_id")   @primary  @note("  ") Long salId;// 主键 非空     
	private @caption("调用应用") @column("sal_client")    @relate("$salClient") @RelateClass(SrvApiClient.class)  @note("  ") Long salClient;// 非空     
 	private SrvApiClient $salClient;//关联对象[API客户端]
	private @caption("调用API") @column("sal_api")    @relate("$salApi") @RelateClass(SrvApi.class)  @note("  ") String salApi;// 非空    select * from srv_api where 1=1 
 	private SrvApi $salApi;//关联对象[API接口]
	private @caption("调用参数") @column("sal_params")    @note("  ") String salParams;// 非空     
	private @caption("返回数据") @column("sal_return")    @note("  ") String salReturn;// 非空     
	private @caption("添加时间") @column("sal_invoke_time")    @note("  ") Date salInvokeTime;// 非空     
	private @caption("状态") @column("sal_status")    @note(" 1:默认  ") Short salStatus;// 非空 1:默认     
	
	//默认构造函数
	public SrvApiLog(){
	
	}
	
	//主键构造函数
	public SrvApiLog(Long id){
		this.salId = id;
	}
	
	/**所有字段构造函数 SrvApiLog(salClient,salApi,salParams,salReturn,salInvokeTime,salStatus)
	 SrvApiLog(
	 	$.getLong("salClient")//调用应用 [非空]
	 	,$.getString("salApi")//调用API [非空]
	 	,$.getString("salParams")//调用参数 [非空]
	 	,$.getString("salReturn")//返回数据 [非空]
	 	,$.getDate("salInvokeTime")//添加时间 [非空]
	 	,$.getShort("salStatus")//状态 [非空]
	 )
	**/
	public SrvApiLog(Long salClient,String salApi,String salParams,String salReturn,Date salInvokeTime,Short salStatus){
		this.salClient=salClient;
		this.salApi=salApi;
		this.salParams=salParams;
		this.salReturn=salReturn;
		this.salInvokeTime=salInvokeTime;
		this.salStatus=salStatus;
	}
	
	//设置非空字段
	public SrvApiLog setNotNull(Long salClient,String salApi,String salParams,String salReturn,Date salInvokeTime,Short salStatus){
		this.salClient=salClient;
		this.salApi=salApi;
		this.salParams=salParams;
		this.salReturn=salReturn;
		this.salInvokeTime=salInvokeTime;
		this.salStatus=salStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public SrvApiLog salId(Long salId){
		this.salId = salId;
		this.setSeted(F.salId);
		return this;
	}
	/** 调用应用 [非空] [SrvApiClient]      **/
	public SrvApiLog salClient(Long salClient){
		this.salClient = salClient;
		this.setSeted(F.salClient);
		return this;
	}
	/** 调用API [非空] [SrvApi]     select * from srv_api where 1=1 **/
	public SrvApiLog salApi(String salApi){
		this.salApi = salApi;
		this.setSeted(F.salApi);
		return this;
	}
	/** 调用参数 [非空]       **/
	public SrvApiLog salParams(String salParams){
		this.salParams = salParams;
		this.setSeted(F.salParams);
		return this;
	}
	/** 返回数据 [非空]       **/
	public SrvApiLog salReturn(String salReturn){
		this.salReturn = salReturn;
		this.setSeted(F.salReturn);
		return this;
	}
	/** 添加时间 [非空]       **/
	public SrvApiLog salInvokeTime(Date salInvokeTime){
		this.salInvokeTime = salInvokeTime;
		this.setSeted(F.salInvokeTime);
		return this;
	}
	/** 状态 [非空]   1:默认     **/
	public SrvApiLog salStatus(Short salStatus){
		this.salStatus = salStatus;
		this.setSeted(F.salStatus);
		return this;
	}
	
	
	//克隆对象
	public SrvApiLog clone(){
		SrvApiLog clone = new SrvApiLog();
		clone.salClient=this.salClient;
		clone.salApi=this.salApi;
		clone.salParams=this.salParams;
		clone.salReturn=this.salReturn;
		clone.salInvokeTime=this.salInvokeTime;
		clone.salStatus=this.salStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取API日志
	 * @param id
	 * @return
	 */
	public static @api SrvApiLog get(Long id){		
		return getSrvApiLogById(id);
	}
	/**
	 * 获取所有API日志
	 * @return
	 */
	public static @api List<SrvApiLog> list(Map params,Integer size){
		return getSrvApiLogList(params,size);
	}
	/**
	 * 获取API日志分页
	 * @return
	 */
	public static @api Page<SrvApiLog> page(int page,int size,Map params){
		return getSrvApiLogPage(page, size , params);
	}
	/**
	 * 根据查询条件取API日志
	 * @param params
	 * @return
	 */
	public static @api SrvApiLog Get(Map params){
		return getSrvApiLog(params);
	}
	/**
	 * 获取API日志数
	 * @return
	 */
	public static @api Long count(Map params){
		return getSrvApiLogCount(params);
	}
	/**
	 * 获取API日志数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getSrvApiLogEval(eval,params);
	}
	
	/**
	 * 根据ID取API日志
	 * @param id
	 * @return
	 */
	public static @api SrvApiLog getSrvApiLogById(Long id){		
		SrvApiLog srvApiLog = (SrvApiLog) $.GetRequest("SrvApiLog$"+id);
		if(srvApiLog!=null)
			return srvApiLog;
		com.ccclubs.service.admin.ISrvApiLogService srvApiLogService = $.GetSpringBean("srvApiLogService");		
		return srvApiLogService.getSrvApiLogById(id);
	}
	
	
	/**
	 * 根据ID取API日志的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		//当前对象不支持键值缓存
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getSalId$();
		if(!"SalId".equals("SalId"))
			keyValue+="("+this.getSalId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有API日志
	 * @return
	 */
	public static @api List<SrvApiLog> getSrvApiLogList(Map params,Integer size){
		com.ccclubs.service.admin.ISrvApiLogService srvApiLogService = $.GetSpringBean("srvApiLogService");
		return srvApiLogService.getSrvApiLogList(params, size);
	}
	
	/**
	 * 获取API日志分页
	 * @return
	 */
	public static @api Page<SrvApiLog> getSrvApiLogPage(int page,int size,Map params){
		com.ccclubs.service.admin.ISrvApiLogService srvApiLogService = $.GetSpringBean("srvApiLogService");
		return srvApiLogService.getSrvApiLogPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取API日志
	 * @param params
	 * @return
	 */
	public static @api SrvApiLog getSrvApiLog(Map params){
		com.ccclubs.service.admin.ISrvApiLogService srvApiLogService = $.GetSpringBean("srvApiLogService");
		return srvApiLogService.getSrvApiLog(params);
	}
	
	/**
	 * 获取API日志数
	 * @return
	 */
	public static @api Long getSrvApiLogCount(Map params){
		com.ccclubs.service.admin.ISrvApiLogService srvApiLogService = $.GetSpringBean("srvApiLogService");
		return srvApiLogService.getSrvApiLogCount(params);
	}
		
		
	/**
	 * 获取API日志自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getSrvApiLogEval(String eval,Map params){
		com.ccclubs.service.admin.ISrvApiLogService srvApiLogService = $.GetSpringBean("srvApiLogService");
		return srvApiLogService.getSrvApiLogEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateSrvApiLog(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ISrvApiLogService srvApiLogService = $.GetSpringBean("srvApiLogService");
		srvApiLogService.updateSrvApiLogByConfirm(set, where);
	}
	
	
	/**
	 * 保存API日志对象
	 * @param params
	 * @return
	 */
	public SrvApiLog save(){
		com.ccclubs.service.admin.ISrvApiLogService srvApiLogService = $.GetSpringBean("srvApiLogService");
		if(this.getSalId()!=null)
			srvApiLogService.updateSrvApiLog(this);
		else
			return srvApiLogService.saveSrvApiLog(this);
		return this;
	}
	
	
	/**
	 * 更新API日志对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ISrvApiLogService srvApiLogService = $.GetSpringBean("srvApiLogService");
		srvApiLogService.updateSrvApiLog$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ISrvApiLogService srvApiLogService = $.GetSpringBean("srvApiLogService");
		if($.equals($.config("logic_delete"),"true"))
			srvApiLogService.removeSrvApiLogById(this.getSalId());
		else
			srvApiLogService.deleteSrvApiLogById(this.getSalId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ISrvApiLogService srvApiLogService = $.GetSpringBean("srvApiLogService");
		return srvApiLogService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getSalId(){
		return this.salId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getSalId$(){
		String strValue="";
		 strValue=$.str(this.getSalId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setSalId(Long salId){
		this.salId = salId;
		this.setSeted(F.salId);
	}
	/*******************************调用应用**********************************/	
	/**
	* 调用应用 [非空] [SrvApiClient]     
	**/
	public Long getSalClient(){
		return this.salClient;
	}
	/**
	* 获取调用应用格式化(toString)
	**/
	public String getSalClient$(){
		String strValue="";
		if(this.getSalClient()!=null){
				strValue+=$.str(SrvApiClient.getKeyValue(this.getSalClient()));
		}			
	 	 return strValue;
	}
	/**
	* 调用应用 [非空] [SrvApiClient]     
	**/
	public void setSalClient(Long salClient){
		this.salClient = salClient;
		this.setSeted(F.salClient);
	}
	/**
	* 获取关联对象[API客户端]
	**/	 			
 	public SrvApiClient get$salClient(){
 		com.ccclubs.model.SrvApiClient srvApiClient = (com.ccclubs.model.SrvApiClient) $.GetRequest("SrvApiClient$"+this.getSalClient());
 		if(srvApiClient!=null)
			return srvApiClient;
		if(this.getSalClient()!=null){
 			srvApiClient = SrvApiClient.get(this.getSalClient());
 		}
 		$.SetRequest("SrvApiClient$"+this.getSalClient(), srvApiClient);
	 	return srvApiClient;
	}
	/*******************************调用API**********************************/	
	/**
	* 调用API [非空] [SrvApi]     select * from srv_api where 1=1
	**/
	public String getSalApi(){
		return this.salApi;
	}
	/**
	* 获取调用API格式化(toString)
	**/
	public String getSalApi$(){
		String strValue="";
			
		if(!$.empty(this.getSalApi())){
			List<com.ccclubs.model.SrvApi> items = com.ccclubs.model.SrvApi.getSrvApiList($.add("definex","sa_id in ("+$.str(this.getSalApi()).replace("#","")+")"),-1);
			for(com.ccclubs.model.SrvApi item1:items)
				strValue+="["+$.str(item1.getSaName())+"]";
		}
	 	 return strValue;
	}
	/**
	* 调用API [非空] [SrvApi]     select * from srv_api where 1=1
	**/
	public void setSalApi(String salApi){
		this.salApi = salApi;
		this.setSeted(F.salApi);
	}
			
	/**
	* 获取调用API列表
	**/ 			
 	public List<com.ccclubs.model.SrvApi> get$salApi(){
		if(!$.empty(this.getSalApi())){
			return com.ccclubs.model.SrvApi.getSrvApiList($.add("definex","sa_id in ("+$.str(this.getSalApi()).replace("#","")+")"),-1);
		}else
			return new ArrayList();
	}
	/*******************************调用参数**********************************/	
	/**
	* 调用参数 [非空]      
	**/
	public String getSalParams(){
		return this.salParams;
	}
	/**
	* 获取调用参数格式化(toString)
	**/
	public String getSalParams$(){
		String strValue="";
		 strValue=$.str(this.getSalParams());
	 	 return strValue;
	}
	/**
	* 调用参数 [非空]      
	**/
	public void setSalParams(String salParams){
		this.salParams = salParams;
		this.setSeted(F.salParams);
	}
	/*******************************返回数据**********************************/	
	/**
	* 返回数据 [非空]      
	**/
	public String getSalReturn(){
		return this.salReturn;
	}
	/**
	* 获取返回数据格式化(toString)
	**/
	public String getSalReturn$(){
		String strValue="";
		 strValue=$.str(this.getSalReturn());
	 	 return strValue;
	}
	/**
	* 返回数据 [非空]      
	**/
	public void setSalReturn(String salReturn){
		this.salReturn = salReturn;
		this.setSeted(F.salReturn);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getSalInvokeTime(){
		return this.salInvokeTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getSalInvokeTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getSalInvokeTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setSalInvokeTime(Date salInvokeTime){
		this.salInvokeTime = salInvokeTime;
		this.setSeted(F.salInvokeTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:默认    
	**/
	public Short getSalStatus(){
		return this.salStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getSalStatus$(){
		String strValue="";
		 if($.equals($.str(this.getSalStatus()),"1"))
			strValue=$.str("默认");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:默认    
	**/
	public void setSalStatus(Short salStatus){
		this.salStatus = salStatus;
		this.setSeted(F.salStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${SrvApiLog.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvApiLog.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvApiLog.class.getMethod("get$"+$.forMat(key.toString())).invoke(SrvApiLog.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${SrvApiLog.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvApiLog.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvApiLog.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(SrvApiLog.this);
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
		public M salId(Object salId){this.put("salId", salId);return this;};
	 	/** and sal_id is null */
 		public M salIdNull(){if(this.get("salIdNot")==null)this.put("salIdNot", "");this.put("salId", null);return this;};
 		/** not .... */
 		public M salIdNot(){this.put("salIdNot", "not");return this;};
		/** 调用应用 [非空] [SrvApiClient]      **/
		public M salClient(Object salClient){this.put("salClient", salClient);return this;};
	 	/** and sal_client is null */
 		public M salClientNull(){if(this.get("salClientNot")==null)this.put("salClientNot", "");this.put("salClient", null);return this;};
 		/** not .... */
 		public M salClientNot(){this.put("salClientNot", "not");return this;};
		public M salClient$on(SrvApiClient.M value){
			this.put("SrvApiClient", value);
			this.put("salClient$on", value);
			return this;
		};	
		/** 调用API [非空] [SrvApi]     select * from srv_api where 1=1 **/
		public M salApi(Object salApi){this.put("salApi", salApi);return this;};
	 	/** and sal_api is null */
 		public M salApiNull(){if(this.get("salApiNot")==null)this.put("salApiNot", "");this.put("salApi", null);return this;};
 		/** not .... */
 		public M salApiNot(){this.put("salApiNot", "not");return this;};
		public M salApi$on(SrvApi.M value){
			this.put("SrvApi", value);
			this.put("salApi$on", value);
			return this;
		};	
		/** 调用参数 [非空]       **/
		public M salParams(Object salParams){this.put("salParams", salParams);return this;};
	 	/** and sal_params is null */
 		public M salParamsNull(){if(this.get("salParamsNot")==null)this.put("salParamsNot", "");this.put("salParams", null);return this;};
 		/** not .... */
 		public M salParamsNot(){this.put("salParamsNot", "not");return this;};
		/** 返回数据 [非空]       **/
		public M salReturn(Object salReturn){this.put("salReturn", salReturn);return this;};
	 	/** and sal_return is null */
 		public M salReturnNull(){if(this.get("salReturnNot")==null)this.put("salReturnNot", "");this.put("salReturn", null);return this;};
 		/** not .... */
 		public M salReturnNot(){this.put("salReturnNot", "not");return this;};
		/** 添加时间 [非空]       **/
		public M salInvokeTime(Object salInvokeTime){this.put("salInvokeTime", salInvokeTime);return this;};
	 	/** and sal_invoke_time is null */
 		public M salInvokeTimeNull(){if(this.get("salInvokeTimeNot")==null)this.put("salInvokeTimeNot", "");this.put("salInvokeTime", null);return this;};
 		/** not .... */
 		public M salInvokeTimeNot(){this.put("salInvokeTimeNot", "not");return this;};
 		/** and sal_invoke_time >= ? */
 		public M salInvokeTimeStart(Object start){this.put("salInvokeTimeStart", start);return this;};			
 		/** and sal_invoke_time <= ? */
 		public M salInvokeTimeEnd(Object end){this.put("salInvokeTimeEnd", end);return this;};
		/** 状态 [非空]   1:默认     **/
		public M salStatus(Object salStatus){this.put("salStatus", salStatus);return this;};
	 	/** and sal_status is null */
 		public M salStatusNull(){if(this.get("salStatusNot")==null)this.put("salStatusNot", "");this.put("salStatus", null);return this;};
 		/** not .... */
 		public M salStatusNot(){this.put("salStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有API日志 **/
		public @api List<SrvApiLog> list(Integer size){
			return getSrvApiLogList(this,size);
		}
		/** 获取API日志分页 **/
		public @api Page<SrvApiLog> page(int page,int size){
			return getSrvApiLogPage(page, size , this);
		}
		/** 根据查询条件取API日志 **/
		public @api SrvApiLog get(){
			return getSrvApiLog(this);
		}
		/** 获取API日志数 **/
		public @api Long count(){
			return getSrvApiLogCount(this);
		}
		/** 获取API日志表达式 **/
		public @api <T> T eval(String strEval){
			return getSrvApiLogEval(strEval,this);
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
			updateSrvApiLog(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String salId="salId";
		/** 调用应用 [非空] [SrvApiClient]      **/
		public final static @type(Long.class)  String salClient="salClient";
		/** 调用API [非空] [SrvApi]     select * from srv_api where 1=1 **/
		public final static @type(String.class)  String salApi="salApi";
		/** 调用参数 [非空]       **/
		public final static @type(String.class) @like String salParams="salParams";
		/** 返回数据 [非空]       **/
		public final static @type(String.class) @like String salReturn="salReturn";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String salInvokeTime="salInvokeTime";
	 	/** and sal_invoke_time >= ? */
 		public final static @type(Date.class) String salInvokeTimeStart="salInvokeTimeStart";
 		/** and sal_invoke_time <= ? */
 		public final static @type(Date.class) String salInvokeTimeEnd="salInvokeTimeEnd";
		/** 状态 [非空]   1:默认     **/
		public final static @type(Short.class)  String salStatus="salStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String salId="sal_id";
		/** 调用应用 [非空] [SrvApiClient]      **/
		public final static String salClient="sal_client";
		/** 调用API [非空] [SrvApi]     select * from srv_api where 1=1 **/
		public final static String salApi="sal_api";
		/** 调用参数 [非空]       **/
		public final static String salParams="sal_params";
		/** 返回数据 [非空]       **/
		public final static String salReturn="sal_return";
		/** 添加时间 [非空]       **/
		public final static String salInvokeTime="sal_invoke_time";
		/** 状态 [非空]   1:默认     **/
		public final static String salStatus="sal_status";
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
				$.Set(name,SrvApiLog.getSrvApiLog(params));
			else
				$.Set(name,SrvApiLog.getSrvApiLogList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取API日志数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.SrvApiLog) $.GetRequest("SrvApiLog$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = SrvApiLog.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = SrvApiLog.getSrvApiLog(params);
			else
				value = SrvApiLog.getSrvApiLogList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = SrvApiLog.Get($.add(SrvApiLog.F.salId,param));
		else if(!$.empty(param.toString()))
			value = SrvApiLog.get(Long.valueOf(param.toString()));
		$.SetRequest("SrvApiLog$"+param.hashCode(), value);
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
	public void mergeSet(SrvApiLog old){
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