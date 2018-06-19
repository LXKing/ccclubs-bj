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

@namespace("service/repairadr")
public @caption("维修地点") @table("cs_repair_adr") class CsRepairAdr implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csra_id")   @primary  @note("  ") Long csraId;// 主键 非空     
	private @caption("城市") @column("csra_host")    @relate("$csraHost") @RelateClass(SrvHost.class)  @note("  ") Long csraHost;// 非空     
 	private SrvHost $csraHost;//关联对象[运营城市]
	private @caption("名称") @column("csra_name")    @note("  ") String csraName;// 非空     
	private @caption("详细地址") @column("csra_address")  @hidden   @note("  ") String csraAddress;//     
	
	//默认构造函数
	public CsRepairAdr(){
	
	}
	
	//主键构造函数
	public CsRepairAdr(Long id){
		this.csraId = id;
	}
	
	/**所有字段构造函数 CsRepairAdr(csraHost,csraName,csraAddress)
	 CsRepairAdr(
	 	$.getLong("csraHost")//城市 [非空]
	 	,$.getString("csraName")//名称 [非空]
	 	,$.getString("csraAddress")//详细地址
	 )
	**/
	public CsRepairAdr(Long csraHost,String csraName,String csraAddress){
		this.csraHost=csraHost;
		this.csraName=csraName;
		this.csraAddress=csraAddress;
	}
	
	//设置非空字段
	public CsRepairAdr setNotNull(Long csraHost,String csraName){
		this.csraHost=csraHost;
		this.csraName=csraName;
		return this;
	}
	/** 编号 [非空]       **/
	public CsRepairAdr csraId(Long csraId){
		this.csraId = csraId;
		this.setSeted(F.csraId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsRepairAdr csraHost(Long csraHost){
		this.csraHost = csraHost;
		this.setSeted(F.csraHost);
		return this;
	}
	/** 名称 [非空]       **/
	public CsRepairAdr csraName(String csraName){
		this.csraName = csraName;
		this.setSeted(F.csraName);
		return this;
	}
	/** 详细地址        **/
	public CsRepairAdr csraAddress(String csraAddress){
		this.csraAddress = csraAddress;
		this.setSeted(F.csraAddress);
		return this;
	}
	
	
	//克隆对象
	public CsRepairAdr clone(){
		CsRepairAdr clone = new CsRepairAdr();
		clone.csraHost=this.csraHost;
		clone.csraName=this.csraName;
		return clone;
	}
	
	
	/**
	 * 根据ID取维修地点
	 * @param id
	 * @return
	 */
	public static @api CsRepairAdr get(Long id){		
		return getCsRepairAdrById(id);
	}
	/**
	 * 获取所有维修地点
	 * @return
	 */
	public static @api List<CsRepairAdr> list(Map params,Integer size){
		return getCsRepairAdrList(params,size);
	}
	/**
	 * 获取维修地点分页
	 * @return
	 */
	public static @api Page<CsRepairAdr> page(int page,int size,Map params){
		return getCsRepairAdrPage(page, size , params);
	}
	/**
	 * 根据查询条件取维修地点
	 * @param params
	 * @return
	 */
	public static @api CsRepairAdr Get(Map params){
		return getCsRepairAdr(params);
	}
	/**
	 * 获取维修地点数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsRepairAdrCount(params);
	}
	/**
	 * 获取维修地点数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsRepairAdrEval(eval,params);
	}
	
	/**
	 * 根据ID取维修地点
	 * @param id
	 * @return
	 */
	public static @api CsRepairAdr getCsRepairAdrById(Long id){		
		CsRepairAdr csRepairAdr = (CsRepairAdr) $.GetRequest("CsRepairAdr$"+id);
		if(csRepairAdr!=null)
			return csRepairAdr;
		com.ccclubs.service.admin.ICsRepairAdrService csRepairAdrService = $.GetSpringBean("csRepairAdrService");		
		return csRepairAdrService.getCsRepairAdrById(id);
	}
	
	
	/**
	 * 根据ID取维修地点的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsRepairAdr.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsRepairAdr csRepairAdr = get(id);
		if(csRepairAdr!=null){
			String strValue = csRepairAdr.getCsraName$();
			if(!"CsraName".equals("CsraName"))
				strValue+="("+csRepairAdr.getCsraName$()+")";
			MemCache.setValue(CsRepairAdr.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsraName$();
		if(!"CsraName".equals("CsraName"))
			keyValue+="("+this.getCsraName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有维修地点
	 * @return
	 */
	public static @api List<CsRepairAdr> getCsRepairAdrList(Map params,Integer size){
		com.ccclubs.service.admin.ICsRepairAdrService csRepairAdrService = $.GetSpringBean("csRepairAdrService");
		return csRepairAdrService.getCsRepairAdrList(params, size);
	}
	
	/**
	 * 获取维修地点分页
	 * @return
	 */
	public static @api Page<CsRepairAdr> getCsRepairAdrPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsRepairAdrService csRepairAdrService = $.GetSpringBean("csRepairAdrService");
		return csRepairAdrService.getCsRepairAdrPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取维修地点
	 * @param params
	 * @return
	 */
	public static @api CsRepairAdr getCsRepairAdr(Map params){
		com.ccclubs.service.admin.ICsRepairAdrService csRepairAdrService = $.GetSpringBean("csRepairAdrService");
		return csRepairAdrService.getCsRepairAdr(params);
	}
	
	/**
	 * 获取维修地点数
	 * @return
	 */
	public static @api Long getCsRepairAdrCount(Map params){
		com.ccclubs.service.admin.ICsRepairAdrService csRepairAdrService = $.GetSpringBean("csRepairAdrService");
		return csRepairAdrService.getCsRepairAdrCount(params);
	}
		
		
	/**
	 * 获取维修地点自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsRepairAdrEval(String eval,Map params){
		com.ccclubs.service.admin.ICsRepairAdrService csRepairAdrService = $.GetSpringBean("csRepairAdrService");
		return csRepairAdrService.getCsRepairAdrEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsRepairAdr(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsRepairAdrService csRepairAdrService = $.GetSpringBean("csRepairAdrService");
		csRepairAdrService.updateCsRepairAdrByConfirm(set, where);
	}
	
	
	/**
	 * 保存维修地点对象
	 * @param params
	 * @return
	 */
	public CsRepairAdr save(){
		com.ccclubs.service.admin.ICsRepairAdrService csRepairAdrService = $.GetSpringBean("csRepairAdrService");
		if(this.getCsraId()!=null)
			csRepairAdrService.updateCsRepairAdr(this);
		else
			return csRepairAdrService.saveCsRepairAdr(this);
		return this;
	}
	
	
	/**
	 * 更新维修地点对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsRepairAdrService csRepairAdrService = $.GetSpringBean("csRepairAdrService");
		csRepairAdrService.updateCsRepairAdr$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsRepairAdrService csRepairAdrService = $.GetSpringBean("csRepairAdrService");
			csRepairAdrService.deleteCsRepairAdrById(this.getCsraId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsRepairAdrService csRepairAdrService = $.GetSpringBean("csRepairAdrService");
		return csRepairAdrService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsraId(){
		return this.csraId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsraId$(){
		String strValue="";
		 strValue=$.str(this.getCsraId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsraId(Long csraId){
		this.csraId = csraId;
		this.setSeted(F.csraId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsraHost(){
		return this.csraHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsraHost$(){
		String strValue="";
		if(this.getCsraHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsraHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsraHost(Long csraHost){
		this.csraHost = csraHost;
		this.setSeted(F.csraHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csraHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsraHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsraHost()!=null){
 			srvHost = SrvHost.get(this.getCsraHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsraHost(), srvHost);
	 	return srvHost;
	}
	/*******************************名称**********************************/	
	/**
	* 名称 [非空]      
	**/
	public String getCsraName(){
		return this.csraName;
	}
	/**
	* 获取名称格式化(toString)
	**/
	public String getCsraName$(){
		String strValue="";
		 strValue=$.str(this.getCsraName());
	 	 return strValue;
	}
	/**
	* 名称 [非空]      
	**/
	public void setCsraName(String csraName){
		this.csraName = csraName;
		this.setSeted(F.csraName);
	}
	/*******************************详细地址**********************************/	
	/**
	* 详细地址       
	**/
	public String getCsraAddress(){
		return this.csraAddress;
	}
	/**
	* 获取详细地址格式化(toString)
	**/
	public String getCsraAddress$(){
		String strValue="";
		 strValue=$.str(this.getCsraAddress());
	 	 return strValue;
	}
	/**
	* 详细地址       
	**/
	public void setCsraAddress(String csraAddress){
		this.csraAddress = csraAddress;
		this.setSeted(F.csraAddress);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsRepairAdr.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRepairAdr.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRepairAdr.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsRepairAdr.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsRepairAdr.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRepairAdr.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRepairAdr.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsRepairAdr.this);
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
		public M csraId(Object csraId){this.put("csraId", csraId);return this;};
	 	/** and csra_id is null */
 		public M csraIdNull(){if(this.get("csraIdNot")==null)this.put("csraIdNot", "");this.put("csraId", null);return this;};
 		/** not .... */
 		public M csraIdNot(){this.put("csraIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csraHost(Object csraHost){this.put("csraHost", csraHost);return this;};
	 	/** and csra_host is null */
 		public M csraHostNull(){if(this.get("csraHostNot")==null)this.put("csraHostNot", "");this.put("csraHost", null);return this;};
 		/** not .... */
 		public M csraHostNot(){this.put("csraHostNot", "not");return this;};
		/** 名称 [非空]       **/
		public M csraName(Object csraName){this.put("csraName", csraName);return this;};
	 	/** and csra_name is null */
 		public M csraNameNull(){if(this.get("csraNameNot")==null)this.put("csraNameNot", "");this.put("csraName", null);return this;};
 		/** not .... */
 		public M csraNameNot(){this.put("csraNameNot", "not");return this;};
		/** 详细地址        **/
		public M csraAddress(Object csraAddress){this.put("csraAddress", csraAddress);return this;};
	 	/** and csra_address is null */
 		public M csraAddressNull(){if(this.get("csraAddressNot")==null)this.put("csraAddressNot", "");this.put("csraAddress", null);return this;};
 		/** not .... */
 		public M csraAddressNot(){this.put("csraAddressNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有维修地点 **/
		public @api List<CsRepairAdr> list(Integer size){
			return getCsRepairAdrList(this,size);
		}
		/** 获取维修地点分页 **/
		public @api Page<CsRepairAdr> page(int page,int size){
			return getCsRepairAdrPage(page, size , this);
		}
		/** 根据查询条件取维修地点 **/
		public @api CsRepairAdr get(){
			return getCsRepairAdr(this);
		}
		/** 获取维修地点数 **/
		public @api Long count(){
			return getCsRepairAdrCount(this);
		}
		/** 获取维修地点表达式 **/
		public @api <T> T eval(String strEval){
			return getCsRepairAdrEval(strEval,this);
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
			updateCsRepairAdr(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csraId="csraId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csraHost="csraHost";
		/** 名称 [非空]       **/
		public final static @type(String.class) @like String csraName="csraName";
		/** 详细地址        **/
		public final static @type(String.class) @like String csraAddress="csraAddress";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csraId="csra_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csraHost="csra_host";
		/** 名称 [非空]       **/
		public final static String csraName="csra_name";
		/** 详细地址        **/
		public final static String csraAddress="csra_address";
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
				$.Set(name,CsRepairAdr.getCsRepairAdr(params));
			else
				$.Set(name,CsRepairAdr.getCsRepairAdrList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取维修地点数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsRepairAdr) $.GetRequest("CsRepairAdr$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsRepairAdr.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsRepairAdr.getCsRepairAdr(params);
			else
				value = CsRepairAdr.getCsRepairAdrList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsRepairAdr.Get($.add(CsRepairAdr.F.csraId,param));
		else if(!$.empty(param.toString()))
			value = CsRepairAdr.get(Long.valueOf(param.toString()));
		$.SetRequest("CsRepairAdr$"+param.hashCode(), value);
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
	public void mergeSet(CsRepairAdr old){
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