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

@namespace("configurator/apiclient")
public @caption("API客户端") @table("srv_api_client") class SrvApiClient implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("sac_id")   @primary  @note("  ") Long sacId;// 主键 非空     
	private @caption("客户端ID") @column("sac_client")    @note("  ") String sacClient;// 非空     
	private @caption("客户密钥") @column("sac_secret")    @note("  ") String sacSecret;// 非空     
	private @caption("描述") @column("sac_desc")    @note("  ") String sacDesc;//     
	private @caption("修改时间") @column("sac_update_time")    @note("  ") Date sacUpdateTime;// 非空     
	private @caption("添加时间") @column("sac_add_time")    @note("  ") Date sacAddTime;// 非空     
	private @caption("状态") @column("sac_status")    @note(" 1:正常 0:无效  ") Short sacStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public SrvApiClient(){
	
	}
	
	//主键构造函数
	public SrvApiClient(Long id){
		this.sacId = id;
	}
	
	/**所有字段构造函数 SrvApiClient(sacClient,sacSecret,sacDesc,sacUpdateTime,sacAddTime,sacStatus)
	 SrvApiClient(
	 	$.getString("sacClient")//客户端ID [非空]
	 	,$.getString("sacSecret")//客户密钥 [非空]
	 	,$.getString("sacDesc")//描述
	 	,$.getDate("sacUpdateTime")//修改时间 [非空]
	 	,$.getDate("sacAddTime")//添加时间 [非空]
	 	,$.getShort("sacStatus")//状态 [非空]
	 )
	**/
	public SrvApiClient(String sacClient,String sacSecret,String sacDesc,Date sacUpdateTime,Date sacAddTime,Short sacStatus){
		this.sacClient=sacClient;
		this.sacSecret=sacSecret;
		this.sacDesc=sacDesc;
		this.sacUpdateTime=sacUpdateTime;
		this.sacAddTime=sacAddTime;
		this.sacStatus=sacStatus;
	}
	
	//设置非空字段
	public SrvApiClient setNotNull(String sacClient,String sacSecret,Date sacUpdateTime,Date sacAddTime,Short sacStatus){
		this.sacClient=sacClient;
		this.sacSecret=sacSecret;
		this.sacUpdateTime=sacUpdateTime;
		this.sacAddTime=sacAddTime;
		this.sacStatus=sacStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public SrvApiClient sacId(Long sacId){
		this.sacId = sacId;
		this.setSeted(F.sacId);
		return this;
	}
	/** 客户端ID [非空]       **/
	public SrvApiClient sacClient(String sacClient){
		this.sacClient = sacClient;
		this.setSeted(F.sacClient);
		return this;
	}
	/** 客户密钥 [非空]       **/
	public SrvApiClient sacSecret(String sacSecret){
		this.sacSecret = sacSecret;
		this.setSeted(F.sacSecret);
		return this;
	}
	/** 描述        **/
	public SrvApiClient sacDesc(String sacDesc){
		this.sacDesc = sacDesc;
		this.setSeted(F.sacDesc);
		return this;
	}
	/** 修改时间 [非空]       **/
	public SrvApiClient sacUpdateTime(Date sacUpdateTime){
		this.sacUpdateTime = sacUpdateTime;
		this.setSeted(F.sacUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public SrvApiClient sacAddTime(Date sacAddTime){
		this.sacAddTime = sacAddTime;
		this.setSeted(F.sacAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public SrvApiClient sacStatus(Short sacStatus){
		this.sacStatus = sacStatus;
		this.setSeted(F.sacStatus);
		return this;
	}
	
	
	//克隆对象
	public SrvApiClient clone(){
		SrvApiClient clone = new SrvApiClient();
		clone.sacClient=this.sacClient;
		clone.sacSecret=this.sacSecret;
		clone.sacUpdateTime=this.sacUpdateTime;
		clone.sacAddTime=this.sacAddTime;
		clone.sacStatus=this.sacStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取API客户端
	 * @param id
	 * @return
	 */
	public static @api SrvApiClient get(Long id){		
		return getSrvApiClientById(id);
	}
	/**
	 * 获取所有API客户端
	 * @return
	 */
	public static @api List<SrvApiClient> list(Map params,Integer size){
		return getSrvApiClientList(params,size);
	}
	/**
	 * 获取API客户端分页
	 * @return
	 */
	public static @api Page<SrvApiClient> page(int page,int size,Map params){
		return getSrvApiClientPage(page, size , params);
	}
	/**
	 * 根据查询条件取API客户端
	 * @param params
	 * @return
	 */
	public static @api SrvApiClient Get(Map params){
		return getSrvApiClient(params);
	}
	/**
	 * 获取API客户端数
	 * @return
	 */
	public static @api Long count(Map params){
		return getSrvApiClientCount(params);
	}
	/**
	 * 获取API客户端数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getSrvApiClientEval(eval,params);
	}
	
	/**
	 * 根据ID取API客户端
	 * @param id
	 * @return
	 */
	public static @api SrvApiClient getSrvApiClientById(Long id){		
		SrvApiClient srvApiClient = (SrvApiClient) $.GetRequest("SrvApiClient$"+id);
		if(srvApiClient!=null)
			return srvApiClient;
		com.ccclubs.service.admin.ISrvApiClientService srvApiClientService = $.GetSpringBean("srvApiClientService");		
		return srvApiClientService.getSrvApiClientById(id);
	}
	
	
	/**
	 * 根据ID取API客户端的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(SrvApiClient.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		SrvApiClient srvApiClient = get(id);
		if(srvApiClient!=null){
			String strValue = srvApiClient.getSacClient$();
			if(!"SacClient".equals("SacClient"))
				strValue+="("+srvApiClient.getSacClient$()+")";
			MemCache.setValue(SrvApiClient.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getSacClient$();
		if(!"SacClient".equals("SacClient"))
			keyValue+="("+this.getSacClient$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有API客户端
	 * @return
	 */
	public static @api List<SrvApiClient> getSrvApiClientList(Map params,Integer size){
		com.ccclubs.service.admin.ISrvApiClientService srvApiClientService = $.GetSpringBean("srvApiClientService");
		return srvApiClientService.getSrvApiClientList(params, size);
	}
	
	/**
	 * 获取API客户端分页
	 * @return
	 */
	public static @api Page<SrvApiClient> getSrvApiClientPage(int page,int size,Map params){
		com.ccclubs.service.admin.ISrvApiClientService srvApiClientService = $.GetSpringBean("srvApiClientService");
		return srvApiClientService.getSrvApiClientPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取API客户端
	 * @param params
	 * @return
	 */
	public static @api SrvApiClient getSrvApiClient(Map params){
		com.ccclubs.service.admin.ISrvApiClientService srvApiClientService = $.GetSpringBean("srvApiClientService");
		return srvApiClientService.getSrvApiClient(params);
	}
	
	/**
	 * 获取API客户端数
	 * @return
	 */
	public static @api Long getSrvApiClientCount(Map params){
		com.ccclubs.service.admin.ISrvApiClientService srvApiClientService = $.GetSpringBean("srvApiClientService");
		return srvApiClientService.getSrvApiClientCount(params);
	}
		
		
	/**
	 * 获取API客户端自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getSrvApiClientEval(String eval,Map params){
		com.ccclubs.service.admin.ISrvApiClientService srvApiClientService = $.GetSpringBean("srvApiClientService");
		return srvApiClientService.getSrvApiClientEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateSrvApiClient(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ISrvApiClientService srvApiClientService = $.GetSpringBean("srvApiClientService");
		srvApiClientService.updateSrvApiClientByConfirm(set, where);
	}
	
	
	/**
	 * 保存API客户端对象
	 * @param params
	 * @return
	 */
	public SrvApiClient save(){
		com.ccclubs.service.admin.ISrvApiClientService srvApiClientService = $.GetSpringBean("srvApiClientService");
		if(this.getSacId()!=null)
			srvApiClientService.updateSrvApiClient(this);
		else
			return srvApiClientService.saveSrvApiClient(this);
		return this;
	}
	
	
	/**
	 * 更新API客户端对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ISrvApiClientService srvApiClientService = $.GetSpringBean("srvApiClientService");
		srvApiClientService.updateSrvApiClient$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ISrvApiClientService srvApiClientService = $.GetSpringBean("srvApiClientService");
		if($.equals($.config("logic_delete"),"true"))
			srvApiClientService.removeSrvApiClientById(this.getSacId());
		else
			srvApiClientService.deleteSrvApiClientById(this.getSacId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ISrvApiClientService srvApiClientService = $.GetSpringBean("srvApiClientService");
		return srvApiClientService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getSacId(){
		return this.sacId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getSacId$(){
		String strValue="";
		 strValue=$.str(this.getSacId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setSacId(Long sacId){
		this.sacId = sacId;
		this.setSeted(F.sacId);
	}
	/*******************************客户端ID**********************************/	
	/**
	* 客户端ID [非空]      
	**/
	public String getSacClient(){
		return this.sacClient;
	}
	/**
	* 获取客户端ID格式化(toString)
	**/
	public String getSacClient$(){
		String strValue="";
		 strValue=$.str(this.getSacClient());
	 	 return strValue;
	}
	/**
	* 客户端ID [非空]      
	**/
	public void setSacClient(String sacClient){
		this.sacClient = sacClient;
		this.setSeted(F.sacClient);
	}
	/*******************************客户密钥**********************************/	
	/**
	* 客户密钥 [非空]      
	**/
	public String getSacSecret(){
		return this.sacSecret;
	}
	/**
	* 获取客户密钥格式化(toString)
	**/
	public String getSacSecret$(){
		String strValue="";
		 strValue=$.str(this.getSacSecret());
	 	 return strValue;
	}
	/**
	* 客户密钥 [非空]      
	**/
	public void setSacSecret(String sacSecret){
		this.sacSecret = sacSecret;
		this.setSeted(F.sacSecret);
	}
	/*******************************描述**********************************/	
	/**
	* 描述       
	**/
	public String getSacDesc(){
		return this.sacDesc;
	}
	/**
	* 获取描述格式化(toString)
	**/
	public String getSacDesc$(){
		String strValue="";
		 strValue=$.str(this.getSacDesc());
	 	 return strValue;
	}
	/**
	* 描述       
	**/
	public void setSacDesc(String sacDesc){
		this.sacDesc = sacDesc;
		this.setSeted(F.sacDesc);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getSacUpdateTime(){
		return this.sacUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getSacUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getSacUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setSacUpdateTime(Date sacUpdateTime){
		this.sacUpdateTime = sacUpdateTime;
		this.setSeted(F.sacUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getSacAddTime(){
		return this.sacAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getSacAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getSacAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setSacAddTime(Date sacAddTime){
		this.sacAddTime = sacAddTime;
		this.setSeted(F.sacAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getSacStatus(){
		return this.sacStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getSacStatus$(){
		String strValue="";
		 if($.equals($.str(this.getSacStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getSacStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setSacStatus(Short sacStatus){
		this.sacStatus = sacStatus;
		this.setSeted(F.sacStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${SrvApiClient.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvApiClient.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvApiClient.class.getMethod("get$"+$.forMat(key.toString())).invoke(SrvApiClient.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${SrvApiClient.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvApiClient.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvApiClient.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(SrvApiClient.this);
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
		public M sacId(Object sacId){this.put("sacId", sacId);return this;};
	 	/** and sac_id is null */
 		public M sacIdNull(){if(this.get("sacIdNot")==null)this.put("sacIdNot", "");this.put("sacId", null);return this;};
 		/** not .... */
 		public M sacIdNot(){this.put("sacIdNot", "not");return this;};
		/** 客户端ID [非空]       **/
		public M sacClient(Object sacClient){this.put("sacClient", sacClient);return this;};
	 	/** and sac_client is null */
 		public M sacClientNull(){if(this.get("sacClientNot")==null)this.put("sacClientNot", "");this.put("sacClient", null);return this;};
 		/** not .... */
 		public M sacClientNot(){this.put("sacClientNot", "not");return this;};
		/** 客户密钥 [非空]       **/
		public M sacSecret(Object sacSecret){this.put("sacSecret", sacSecret);return this;};
	 	/** and sac_secret is null */
 		public M sacSecretNull(){if(this.get("sacSecretNot")==null)this.put("sacSecretNot", "");this.put("sacSecret", null);return this;};
 		/** not .... */
 		public M sacSecretNot(){this.put("sacSecretNot", "not");return this;};
		/** 描述        **/
		public M sacDesc(Object sacDesc){this.put("sacDesc", sacDesc);return this;};
	 	/** and sac_desc is null */
 		public M sacDescNull(){if(this.get("sacDescNot")==null)this.put("sacDescNot", "");this.put("sacDesc", null);return this;};
 		/** not .... */
 		public M sacDescNot(){this.put("sacDescNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M sacUpdateTime(Object sacUpdateTime){this.put("sacUpdateTime", sacUpdateTime);return this;};
	 	/** and sac_update_time is null */
 		public M sacUpdateTimeNull(){if(this.get("sacUpdateTimeNot")==null)this.put("sacUpdateTimeNot", "");this.put("sacUpdateTime", null);return this;};
 		/** not .... */
 		public M sacUpdateTimeNot(){this.put("sacUpdateTimeNot", "not");return this;};
 		/** and sac_update_time >= ? */
 		public M sacUpdateTimeStart(Object start){this.put("sacUpdateTimeStart", start);return this;};			
 		/** and sac_update_time <= ? */
 		public M sacUpdateTimeEnd(Object end){this.put("sacUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M sacAddTime(Object sacAddTime){this.put("sacAddTime", sacAddTime);return this;};
	 	/** and sac_add_time is null */
 		public M sacAddTimeNull(){if(this.get("sacAddTimeNot")==null)this.put("sacAddTimeNot", "");this.put("sacAddTime", null);return this;};
 		/** not .... */
 		public M sacAddTimeNot(){this.put("sacAddTimeNot", "not");return this;};
 		/** and sac_add_time >= ? */
 		public M sacAddTimeStart(Object start){this.put("sacAddTimeStart", start);return this;};			
 		/** and sac_add_time <= ? */
 		public M sacAddTimeEnd(Object end){this.put("sacAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M sacStatus(Object sacStatus){this.put("sacStatus", sacStatus);return this;};
	 	/** and sac_status is null */
 		public M sacStatusNull(){if(this.get("sacStatusNot")==null)this.put("sacStatusNot", "");this.put("sacStatus", null);return this;};
 		/** not .... */
 		public M sacStatusNot(){this.put("sacStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有API客户端 **/
		public @api List<SrvApiClient> list(Integer size){
			return getSrvApiClientList(this,size);
		}
		/** 获取API客户端分页 **/
		public @api Page<SrvApiClient> page(int page,int size){
			return getSrvApiClientPage(page, size , this);
		}
		/** 根据查询条件取API客户端 **/
		public @api SrvApiClient get(){
			return getSrvApiClient(this);
		}
		/** 获取API客户端数 **/
		public @api Long count(){
			return getSrvApiClientCount(this);
		}
		/** 获取API客户端表达式 **/
		public @api <T> T eval(String strEval){
			return getSrvApiClientEval(strEval,this);
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
			updateSrvApiClient(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String sacId="sacId";
		/** 客户端ID [非空]       **/
		public final static @type(String.class) @like String sacClient="sacClient";
		/** 客户密钥 [非空]       **/
		public final static @type(String.class) @like String sacSecret="sacSecret";
		/** 描述        **/
		public final static @type(String.class) @like String sacDesc="sacDesc";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String sacUpdateTime="sacUpdateTime";
	 	/** and sac_update_time >= ? */
 		public final static @type(Date.class) String sacUpdateTimeStart="sacUpdateTimeStart";
 		/** and sac_update_time <= ? */
 		public final static @type(Date.class) String sacUpdateTimeEnd="sacUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String sacAddTime="sacAddTime";
	 	/** and sac_add_time >= ? */
 		public final static @type(Date.class) String sacAddTimeStart="sacAddTimeStart";
 		/** and sac_add_time <= ? */
 		public final static @type(Date.class) String sacAddTimeEnd="sacAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String sacStatus="sacStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String sacId="sac_id";
		/** 客户端ID [非空]       **/
		public final static String sacClient="sac_client";
		/** 客户密钥 [非空]       **/
		public final static String sacSecret="sac_secret";
		/** 描述        **/
		public final static String sacDesc="sac_desc";
		/** 修改时间 [非空]       **/
		public final static String sacUpdateTime="sac_update_time";
		/** 添加时间 [非空]       **/
		public final static String sacAddTime="sac_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String sacStatus="sac_status";
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
				$.Set(name,SrvApiClient.getSrvApiClient(params));
			else
				$.Set(name,SrvApiClient.getSrvApiClientList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取API客户端数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.SrvApiClient) $.GetRequest("SrvApiClient$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = SrvApiClient.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = SrvApiClient.getSrvApiClient(params);
			else
				value = SrvApiClient.getSrvApiClientList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = SrvApiClient.Get($.add(SrvApiClient.F.sacId,param));
		else if(!$.empty(param.toString()))
			value = SrvApiClient.get(Long.valueOf(param.toString()));
		$.SetRequest("SrvApiClient$"+param.hashCode(), value);
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
	public void mergeSet(SrvApiClient old){
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