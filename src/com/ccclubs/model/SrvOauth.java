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

@namespace("systematic/oauth")
public @caption("用户授权") @table("srv_oauth") class SrvOauth implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("so_id")   @primary  @note("  ") Long soId;// 主键 非空     
	private @caption("Token") @column("so_token")    @note("  ") String soToken;// 非空     
	private @caption("帐号ID") @column("so_user_id")    @note("  ") Long soUserId;// 非空     
	private @caption("API客户端") @column("so_api_client")    @relate("$soApiClient") @RelateClass(SrvApiClient.class)  @note("  ") Long soApiClient;// 非空     
 	private SrvApiClient $soApiClient;//关联对象[API客户端]
	private @caption("三方标识") @column("so_ticket")    @note("  ") String soTicket;//     
	private @caption("登录时间") @column("so_update_time")    @note("  ") Date soUpdateTime;// 非空     
	
	//默认构造函数
	public SrvOauth(){
	
	}
	
	//主键构造函数
	public SrvOauth(Long id){
		this.soId = id;
	}
	
	/**所有字段构造函数 SrvOauth(soToken,soUserId,soApiClient,soTicket,soUpdateTime)
	 SrvOauth(
	 	$.getString("soToken")//Token [非空]
	 	,$.getLong("soUserId")//帐号ID [非空]
	 	,$.getLong("soApiClient")//API客户端 [非空]
	 	,$.getString("soTicket")//三方标识
	 	,$.getDate("soUpdateTime")//登录时间 [非空]
	 )
	**/
	public SrvOauth(String soToken,Long soUserId,Long soApiClient,String soTicket,Date soUpdateTime){
		this.soToken=soToken;
		this.soUserId=soUserId;
		this.soApiClient=soApiClient;
		this.soTicket=soTicket;
		this.soUpdateTime=soUpdateTime;
	}
	
	//设置非空字段
	public SrvOauth setNotNull(String soToken,Long soUserId,Long soApiClient,Date soUpdateTime){
		this.soToken=soToken;
		this.soUserId=soUserId;
		this.soApiClient=soApiClient;
		this.soUpdateTime=soUpdateTime;
		return this;
	}
	/** 编号 [非空]       **/
	public SrvOauth soId(Long soId){
		this.soId = soId;
		this.setSeted(F.soId);
		return this;
	}
	/** Token [非空]       **/
	public SrvOauth soToken(String soToken){
		this.soToken = soToken;
		this.setSeted(F.soToken);
		return this;
	}
	/** 帐号ID [非空]       **/
	public SrvOauth soUserId(Long soUserId){
		this.soUserId = soUserId;
		this.setSeted(F.soUserId);
		return this;
	}
	/** API客户端 [非空] [SrvApiClient]      **/
	public SrvOauth soApiClient(Long soApiClient){
		this.soApiClient = soApiClient;
		this.setSeted(F.soApiClient);
		return this;
	}
	/** 三方标识        **/
	public SrvOauth soTicket(String soTicket){
		this.soTicket = soTicket;
		this.setSeted(F.soTicket);
		return this;
	}
	/** 登录时间 [非空]       **/
	public SrvOauth soUpdateTime(Date soUpdateTime){
		this.soUpdateTime = soUpdateTime;
		this.setSeted(F.soUpdateTime);
		return this;
	}
	
	
	//克隆对象
	public SrvOauth clone(){
		SrvOauth clone = new SrvOauth();
		clone.soToken=this.soToken;
		clone.soUserId=this.soUserId;
		clone.soApiClient=this.soApiClient;
		clone.soUpdateTime=this.soUpdateTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取用户授权
	 * @param id
	 * @return
	 */
	public static @api SrvOauth get(Long id){		
		return getSrvOauthById(id);
	}
	/**
	 * 获取所有用户授权
	 * @return
	 */
	public static @api List<SrvOauth> list(Map params,Integer size){
		return getSrvOauthList(params,size);
	}
	/**
	 * 获取用户授权分页
	 * @return
	 */
	public static @api Page<SrvOauth> page(int page,int size,Map params){
		return getSrvOauthPage(page, size , params);
	}
	/**
	 * 根据查询条件取用户授权
	 * @param params
	 * @return
	 */
	public static @api SrvOauth Get(Map params){
		return getSrvOauth(params);
	}
	/**
	 * 获取用户授权数
	 * @return
	 */
	public static @api Long count(Map params){
		return getSrvOauthCount(params);
	}
	/**
	 * 获取用户授权数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getSrvOauthEval(eval,params);
	}
	
	/**
	 * 根据ID取用户授权
	 * @param id
	 * @return
	 */
	public static @api SrvOauth getSrvOauthById(Long id){		
		SrvOauth srvOauth = (SrvOauth) $.GetRequest("SrvOauth$"+id);
		if(srvOauth!=null)
			return srvOauth;
		com.ccclubs.service.admin.ISrvOauthService srvOauthService = $.GetSpringBean("srvOauthService");		
		return srvOauthService.getSrvOauthById(id);
	}
	
	
	/**
	 * 根据ID取用户授权的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(SrvOauth.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		SrvOauth srvOauth = get(id);
		if(srvOauth!=null){
			String strValue = srvOauth.getSoToken$();
			if(!"SoToken".equals("SoToken"))
				strValue+="("+srvOauth.getSoToken$()+")";
			MemCache.setValue(SrvOauth.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getSoToken$();
		if(!"SoToken".equals("SoToken"))
			keyValue+="("+this.getSoToken$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有用户授权
	 * @return
	 */
	public static @api List<SrvOauth> getSrvOauthList(Map params,Integer size){
		com.ccclubs.service.admin.ISrvOauthService srvOauthService = $.GetSpringBean("srvOauthService");
		return srvOauthService.getSrvOauthList(params, size);
	}
	
	/**
	 * 获取用户授权分页
	 * @return
	 */
	public static @api Page<SrvOauth> getSrvOauthPage(int page,int size,Map params){
		com.ccclubs.service.admin.ISrvOauthService srvOauthService = $.GetSpringBean("srvOauthService");
		return srvOauthService.getSrvOauthPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取用户授权
	 * @param params
	 * @return
	 */
	public static @api SrvOauth getSrvOauth(Map params){
		com.ccclubs.service.admin.ISrvOauthService srvOauthService = $.GetSpringBean("srvOauthService");
		return srvOauthService.getSrvOauth(params);
	}
	
	/**
	 * 获取用户授权数
	 * @return
	 */
	public static @api Long getSrvOauthCount(Map params){
		com.ccclubs.service.admin.ISrvOauthService srvOauthService = $.GetSpringBean("srvOauthService");
		return srvOauthService.getSrvOauthCount(params);
	}
		
		
	/**
	 * 获取用户授权自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getSrvOauthEval(String eval,Map params){
		com.ccclubs.service.admin.ISrvOauthService srvOauthService = $.GetSpringBean("srvOauthService");
		return srvOauthService.getSrvOauthEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateSrvOauth(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ISrvOauthService srvOauthService = $.GetSpringBean("srvOauthService");
		srvOauthService.updateSrvOauthByConfirm(set, where);
	}
	
	
	/**
	 * 保存用户授权对象
	 * @param params
	 * @return
	 */
	public SrvOauth save(){
		com.ccclubs.service.admin.ISrvOauthService srvOauthService = $.GetSpringBean("srvOauthService");
		if(this.getSoId()!=null)
			srvOauthService.updateSrvOauth(this);
		else
			return srvOauthService.saveSrvOauth(this);
		return this;
	}
	
	
	/**
	 * 更新用户授权对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ISrvOauthService srvOauthService = $.GetSpringBean("srvOauthService");
		srvOauthService.updateSrvOauth$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ISrvOauthService srvOauthService = $.GetSpringBean("srvOauthService");
			srvOauthService.deleteSrvOauthById(this.getSoId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ISrvOauthService srvOauthService = $.GetSpringBean("srvOauthService");
		return srvOauthService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getSoId(){
		return this.soId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getSoId$(){
		String strValue="";
		 strValue=$.str(this.getSoId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setSoId(Long soId){
		this.soId = soId;
		this.setSeted(F.soId);
	}
	/*******************************Token**********************************/	
	/**
	* Token [非空]      
	**/
	public String getSoToken(){
		return this.soToken;
	}
	/**
	* 获取Token格式化(toString)
	**/
	public String getSoToken$(){
		String strValue="";
		 strValue=$.str(this.getSoToken());
	 	 return strValue;
	}
	/**
	* Token [非空]      
	**/
	public void setSoToken(String soToken){
		this.soToken = soToken;
		this.setSeted(F.soToken);
	}
	/*******************************帐号ID**********************************/	
	/**
	* 帐号ID [非空]      
	**/
	public Long getSoUserId(){
		return this.soUserId;
	}
	/**
	* 获取帐号ID格式化(toString)
	**/
	public String getSoUserId$(){
		String strValue="";
		 strValue=$.str(this.getSoUserId());
	 	 return strValue;
	}
	/**
	* 帐号ID [非空]      
	**/
	public void setSoUserId(Long soUserId){
		this.soUserId = soUserId;
		this.setSeted(F.soUserId);
	}
	/*******************************API客户端**********************************/	
	/**
	* API客户端 [非空] [SrvApiClient]     
	**/
	public Long getSoApiClient(){
		return this.soApiClient;
	}
	/**
	* 获取API客户端格式化(toString)
	**/
	public String getSoApiClient$(){
		String strValue="";
		if(this.getSoApiClient()!=null){
				strValue+=$.str(SrvApiClient.getKeyValue(this.getSoApiClient()));
		}			
	 	 return strValue;
	}
	/**
	* API客户端 [非空] [SrvApiClient]     
	**/
	public void setSoApiClient(Long soApiClient){
		this.soApiClient = soApiClient;
		this.setSeted(F.soApiClient);
	}
	/**
	* 获取关联对象[API客户端]
	**/	 			
 	public SrvApiClient get$soApiClient(){
 		com.ccclubs.model.SrvApiClient srvApiClient = (com.ccclubs.model.SrvApiClient) $.GetRequest("SrvApiClient$"+this.getSoApiClient());
 		if(srvApiClient!=null)
			return srvApiClient;
		if(this.getSoApiClient()!=null){
 			srvApiClient = SrvApiClient.get(this.getSoApiClient());
 		}
 		$.SetRequest("SrvApiClient$"+this.getSoApiClient(), srvApiClient);
	 	return srvApiClient;
	}
	/*******************************三方标识**********************************/	
	/**
	* 三方标识       
	**/
	public String getSoTicket(){
		return this.soTicket;
	}
	/**
	* 获取三方标识格式化(toString)
	**/
	public String getSoTicket$(){
		String strValue="";
		 strValue=$.str(this.getSoTicket());
	 	 return strValue;
	}
	/**
	* 三方标识       
	**/
	public void setSoTicket(String soTicket){
		this.soTicket = soTicket;
		this.setSeted(F.soTicket);
	}
	/*******************************登录时间**********************************/	
	/**
	* 登录时间 [非空]      
	**/
	public Date getSoUpdateTime(){
		return this.soUpdateTime;
	}
	/**
	* 获取登录时间格式化(toString)
	**/
	public String getSoUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getSoUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 登录时间 [非空]      
	**/
	public void setSoUpdateTime(Date soUpdateTime){
		this.soUpdateTime = soUpdateTime;
		this.setSeted(F.soUpdateTime);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${SrvOauth.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvOauth.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvOauth.class.getMethod("get$"+$.forMat(key.toString())).invoke(SrvOauth.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${SrvOauth.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvOauth.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvOauth.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(SrvOauth.this);
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
		public M soId(Object soId){this.put("soId", soId);return this;};
	 	/** and so_id is null */
 		public M soIdNull(){if(this.get("soIdNot")==null)this.put("soIdNot", "");this.put("soId", null);return this;};
 		/** not .... */
 		public M soIdNot(){this.put("soIdNot", "not");return this;};
		/** Token [非空]       **/
		public M soToken(Object soToken){this.put("soToken", soToken);return this;};
	 	/** and so_token is null */
 		public M soTokenNull(){if(this.get("soTokenNot")==null)this.put("soTokenNot", "");this.put("soToken", null);return this;};
 		/** not .... */
 		public M soTokenNot(){this.put("soTokenNot", "not");return this;};
		/** 帐号ID [非空]       **/
		public M soUserId(Object soUserId){this.put("soUserId", soUserId);return this;};
	 	/** and so_user_id is null */
 		public M soUserIdNull(){if(this.get("soUserIdNot")==null)this.put("soUserIdNot", "");this.put("soUserId", null);return this;};
 		/** not .... */
 		public M soUserIdNot(){this.put("soUserIdNot", "not");return this;};
		/** and so_user_id >= ? */
		public M soUserIdMin(Object min){this.put("soUserIdMin", min);return this;};
		/** and so_user_id <= ? */
		public M soUserIdMax(Object max){this.put("soUserIdMax", max);return this;};
		/** API客户端 [非空] [SrvApiClient]      **/
		public M soApiClient(Object soApiClient){this.put("soApiClient", soApiClient);return this;};
	 	/** and so_api_client is null */
 		public M soApiClientNull(){if(this.get("soApiClientNot")==null)this.put("soApiClientNot", "");this.put("soApiClient", null);return this;};
 		/** not .... */
 		public M soApiClientNot(){this.put("soApiClientNot", "not");return this;};
		public M soApiClient$on(SrvApiClient.M value){
			this.put("SrvApiClient", value);
			this.put("soApiClient$on", value);
			return this;
		};	
		/** 三方标识        **/
		public M soTicket(Object soTicket){this.put("soTicket", soTicket);return this;};
	 	/** and so_ticket is null */
 		public M soTicketNull(){if(this.get("soTicketNot")==null)this.put("soTicketNot", "");this.put("soTicket", null);return this;};
 		/** not .... */
 		public M soTicketNot(){this.put("soTicketNot", "not");return this;};
		/** 登录时间 [非空]       **/
		public M soUpdateTime(Object soUpdateTime){this.put("soUpdateTime", soUpdateTime);return this;};
	 	/** and so_update_time is null */
 		public M soUpdateTimeNull(){if(this.get("soUpdateTimeNot")==null)this.put("soUpdateTimeNot", "");this.put("soUpdateTime", null);return this;};
 		/** not .... */
 		public M soUpdateTimeNot(){this.put("soUpdateTimeNot", "not");return this;};
 		/** and so_update_time >= ? */
 		public M soUpdateTimeStart(Object start){this.put("soUpdateTimeStart", start);return this;};			
 		/** and so_update_time <= ? */
 		public M soUpdateTimeEnd(Object end){this.put("soUpdateTimeEnd", end);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有用户授权 **/
		public @api List<SrvOauth> list(Integer size){
			return getSrvOauthList(this,size);
		}
		/** 获取用户授权分页 **/
		public @api Page<SrvOauth> page(int page,int size){
			return getSrvOauthPage(page, size , this);
		}
		/** 根据查询条件取用户授权 **/
		public @api SrvOauth get(){
			return getSrvOauth(this);
		}
		/** 获取用户授权数 **/
		public @api Long count(){
			return getSrvOauthCount(this);
		}
		/** 获取用户授权表达式 **/
		public @api <T> T eval(String strEval){
			return getSrvOauthEval(strEval,this);
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
			updateSrvOauth(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String soId="soId";
		/** Token [非空]       **/
		public final static @type(String.class) @like String soToken="soToken";
		/** 帐号ID [非空]       **/
		public final static @type(Long.class)  String soUserId="soUserId";
		/** and so_user_id >= ? */
		public final static @type(Long.class) String soUserIdMin="soUserIdMin";
		/** and so_user_id <= ? */
		public final static @type(Long.class) String soUserIdMax="soUserIdMax";
		/** API客户端 [非空] [SrvApiClient]      **/
		public final static @type(Long.class)  String soApiClient="soApiClient";
		/** 三方标识        **/
		public final static @type(String.class) @like String soTicket="soTicket";
		/** 登录时间 [非空]       **/
		public final static @type(Date.class)  String soUpdateTime="soUpdateTime";
	 	/** and so_update_time >= ? */
 		public final static @type(Date.class) String soUpdateTimeStart="soUpdateTimeStart";
 		/** and so_update_time <= ? */
 		public final static @type(Date.class) String soUpdateTimeEnd="soUpdateTimeEnd";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String soId="so_id";
		/** Token [非空]       **/
		public final static String soToken="so_token";
		/** 帐号ID [非空]       **/
		public final static String soUserId="so_user_id";
		/** API客户端 [非空] [SrvApiClient]      **/
		public final static String soApiClient="so_api_client";
		/** 三方标识        **/
		public final static String soTicket="so_ticket";
		/** 登录时间 [非空]       **/
		public final static String soUpdateTime="so_update_time";
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
				$.Set(name,SrvOauth.getSrvOauth(params));
			else
				$.Set(name,SrvOauth.getSrvOauthList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取用户授权数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.SrvOauth) $.GetRequest("SrvOauth$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = SrvOauth.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = SrvOauth.getSrvOauth(params);
			else
				value = SrvOauth.getSrvOauthList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = SrvOauth.Get($.add(SrvOauth.F.soId,param));
		else if(!$.empty(param.toString()))
			value = SrvOauth.get(Long.valueOf(param.toString()));
		$.SetRequest("SrvOauth$"+param.hashCode(), value);
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
	public void mergeSet(SrvOauth old){
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