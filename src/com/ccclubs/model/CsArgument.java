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

@namespace("configurator/argument")
public @caption("系统参数") @table("cs_argument") class CsArgument implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csa_id")   @primary  @note("  ") Long csaId;// 主键 非空     
	private @caption("参数名称") @column("csa_name")    @note("  ") String csaName;// 非空     
	private @caption("参数标识") @column("csa_flag")    @note("  ") String csaFlag;// 非空     
	private @caption("参数值") @column("csa_value")    @note("  ") String csaValue;// 非空     
	private @caption("参数描述") @column("csa_describe")    @note("  ") String csaDescribe;//     
	
	//默认构造函数
	public CsArgument(){
	
	}
	
	//主键构造函数
	public CsArgument(Long id){
		this.csaId = id;
	}
	
	/**所有字段构造函数 CsArgument(csaName,csaFlag,csaValue,csaDescribe)
	 CsArgument(
	 	$.getString("csaName")//参数名称 [非空]
	 	,$.getString("csaFlag")//参数标识 [非空]
	 	,$.getString("csaValue")//参数值 [非空]
	 	,$.getString("csaDescribe")//参数描述
	 )
	**/
	public CsArgument(String csaName,String csaFlag,String csaValue,String csaDescribe){
		this.csaName=csaName;
		this.csaFlag=csaFlag;
		this.csaValue=csaValue;
		this.csaDescribe=csaDescribe;
	}
	
	//设置非空字段
	public CsArgument setNotNull(String csaName,String csaFlag,String csaValue){
		this.csaName=csaName;
		this.csaFlag=csaFlag;
		this.csaValue=csaValue;
		return this;
	}
	/** 编号 [非空]       **/
	public CsArgument csaId(Long csaId){
		this.csaId = csaId;
		this.setSeted(F.csaId);
		return this;
	}
	/** 参数名称 [非空]       **/
	public CsArgument csaName(String csaName){
		this.csaName = csaName;
		this.setSeted(F.csaName);
		return this;
	}
	/** 参数标识 [非空]       **/
	public CsArgument csaFlag(String csaFlag){
		this.csaFlag = csaFlag;
		this.setSeted(F.csaFlag);
		return this;
	}
	/** 参数值 [非空]       **/
	public CsArgument csaValue(String csaValue){
		this.csaValue = csaValue;
		this.setSeted(F.csaValue);
		return this;
	}
	/** 参数描述        **/
	public CsArgument csaDescribe(String csaDescribe){
		this.csaDescribe = csaDescribe;
		this.setSeted(F.csaDescribe);
		return this;
	}
	
	
	//克隆对象
	public CsArgument clone(){
		CsArgument clone = new CsArgument();
		clone.csaName=this.csaName;
		clone.csaFlag=this.csaFlag;
		clone.csaValue=this.csaValue;
		return clone;
	}
	
	
	/**
	 * 根据ID取系统参数
	 * @param id
	 * @return
	 */
	public static @api CsArgument get(Long id){		
		return getCsArgumentById(id);
	}
	/**
	 * 获取所有系统参数
	 * @return
	 */
	public static @api List<CsArgument> list(Map params,Integer size){
		return getCsArgumentList(params,size);
	}
	/**
	 * 获取系统参数分页
	 * @return
	 */
	public static @api Page<CsArgument> page(int page,int size,Map params){
		return getCsArgumentPage(page, size , params);
	}
	/**
	 * 根据查询条件取系统参数
	 * @param params
	 * @return
	 */
	public static @api CsArgument Get(Map params){
		return getCsArgument(params);
	}
	/**
	 * 获取系统参数数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsArgumentCount(params);
	}
	/**
	 * 获取系统参数数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsArgumentEval(eval,params);
	}
	
	/**
	 * 根据ID取系统参数
	 * @param id
	 * @return
	 */
	public static @api CsArgument getCsArgumentById(Long id){		
		CsArgument csArgument = (CsArgument) $.GetRequest("CsArgument$"+id);
		if(csArgument!=null)
			return csArgument;
		com.ccclubs.service.admin.ICsArgumentService csArgumentService = $.GetSpringBean("csArgumentService");		
		return csArgumentService.getCsArgumentById(id);
	}
	
	
	/**
	 * 根据ID取系统参数的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsArgument.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsArgument csArgument = get(id);
		if(csArgument!=null){
			String strValue = csArgument.getCsaName$();
			if(!"CsaName".equals("CsaName"))
				strValue+="("+csArgument.getCsaName$()+")";
			MemCache.setValue(CsArgument.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsaName$();
		if(!"CsaName".equals("CsaName"))
			keyValue+="("+this.getCsaName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有系统参数
	 * @return
	 */
	public static @api List<CsArgument> getCsArgumentList(Map params,Integer size){
		com.ccclubs.service.admin.ICsArgumentService csArgumentService = $.GetSpringBean("csArgumentService");
		return csArgumentService.getCsArgumentList(params, size);
	}
	
	/**
	 * 获取系统参数分页
	 * @return
	 */
	public static @api Page<CsArgument> getCsArgumentPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsArgumentService csArgumentService = $.GetSpringBean("csArgumentService");
		return csArgumentService.getCsArgumentPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取系统参数
	 * @param params
	 * @return
	 */
	public static @api CsArgument getCsArgument(Map params){
		com.ccclubs.service.admin.ICsArgumentService csArgumentService = $.GetSpringBean("csArgumentService");
		return csArgumentService.getCsArgument(params);
	}
	
	/**
	 * 获取系统参数数
	 * @return
	 */
	public static @api Long getCsArgumentCount(Map params){
		com.ccclubs.service.admin.ICsArgumentService csArgumentService = $.GetSpringBean("csArgumentService");
		return csArgumentService.getCsArgumentCount(params);
	}
		
		
	/**
	 * 获取系统参数自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsArgumentEval(String eval,Map params){
		com.ccclubs.service.admin.ICsArgumentService csArgumentService = $.GetSpringBean("csArgumentService");
		return csArgumentService.getCsArgumentEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsArgument(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsArgumentService csArgumentService = $.GetSpringBean("csArgumentService");
		csArgumentService.updateCsArgumentByConfirm(set, where);
	}
	
	
	/**
	 * 保存系统参数对象
	 * @param params
	 * @return
	 */
	public CsArgument save(){
		com.ccclubs.service.admin.ICsArgumentService csArgumentService = $.GetSpringBean("csArgumentService");
		if(this.getCsaId()!=null)
			csArgumentService.updateCsArgument(this);
		else
			return csArgumentService.saveCsArgument(this);
		return this;
	}
	
	
	/**
	 * 更新系统参数对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsArgumentService csArgumentService = $.GetSpringBean("csArgumentService");
		csArgumentService.updateCsArgument$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsArgumentService csArgumentService = $.GetSpringBean("csArgumentService");
			csArgumentService.deleteCsArgumentById(this.getCsaId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsArgumentService csArgumentService = $.GetSpringBean("csArgumentService");
		return csArgumentService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsaId(){
		return this.csaId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsaId$(){
		String strValue="";
		 strValue=$.str(this.getCsaId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsaId(Long csaId){
		this.csaId = csaId;
		this.setSeted(F.csaId);
	}
	/*******************************参数名称**********************************/	
	/**
	* 参数名称 [非空]      
	**/
	public String getCsaName(){
		return this.csaName;
	}
	/**
	* 获取参数名称格式化(toString)
	**/
	public String getCsaName$(){
		String strValue="";
		 strValue=$.str(this.getCsaName());
	 	 return strValue;
	}
	/**
	* 参数名称 [非空]      
	**/
	public void setCsaName(String csaName){
		this.csaName = csaName;
		this.setSeted(F.csaName);
	}
	/*******************************参数标识**********************************/	
	/**
	* 参数标识 [非空]      
	**/
	public String getCsaFlag(){
		return this.csaFlag;
	}
	/**
	* 获取参数标识格式化(toString)
	**/
	public String getCsaFlag$(){
		String strValue="";
		 strValue=$.str(this.getCsaFlag());
	 	 return strValue;
	}
	/**
	* 参数标识 [非空]      
	**/
	public void setCsaFlag(String csaFlag){
		this.csaFlag = csaFlag;
		this.setSeted(F.csaFlag);
	}
	/*******************************参数值**********************************/	
	/**
	* 参数值 [非空]      
	**/
	public String getCsaValue(){
		return this.csaValue;
	}
	/**
	* 获取参数值格式化(toString)
	**/
	public String getCsaValue$(){
		String strValue="";
		 strValue=$.str(this.getCsaValue());
	 	 return strValue;
	}
	/**
	* 参数值 [非空]      
	**/
	public void setCsaValue(String csaValue){
		this.csaValue = csaValue;
		this.setSeted(F.csaValue);
	}
	/*******************************参数描述**********************************/	
	/**
	* 参数描述       
	**/
	public String getCsaDescribe(){
		return this.csaDescribe;
	}
	/**
	* 获取参数描述格式化(toString)
	**/
	public String getCsaDescribe$(){
		String strValue="";
		 strValue=$.str(this.getCsaDescribe());
	 	 return strValue;
	}
	/**
	* 参数描述       
	**/
	public void setCsaDescribe(String csaDescribe){
		this.csaDescribe = csaDescribe;
		this.setSeted(F.csaDescribe);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsArgument.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsArgument.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsArgument.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsArgument.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsArgument.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsArgument.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsArgument.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsArgument.this);
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
		public M csaId(Object csaId){this.put("csaId", csaId);return this;};
	 	/** and csa_id is null */
 		public M csaIdNull(){if(this.get("csaIdNot")==null)this.put("csaIdNot", "");this.put("csaId", null);return this;};
 		/** not .... */
 		public M csaIdNot(){this.put("csaIdNot", "not");return this;};
		/** 参数名称 [非空]       **/
		public M csaName(Object csaName){this.put("csaName", csaName);return this;};
	 	/** and csa_name is null */
 		public M csaNameNull(){if(this.get("csaNameNot")==null)this.put("csaNameNot", "");this.put("csaName", null);return this;};
 		/** not .... */
 		public M csaNameNot(){this.put("csaNameNot", "not");return this;};
		/** 参数标识 [非空]       **/
		public M csaFlag(Object csaFlag){this.put("csaFlag", csaFlag);return this;};
	 	/** and csa_flag is null */
 		public M csaFlagNull(){if(this.get("csaFlagNot")==null)this.put("csaFlagNot", "");this.put("csaFlag", null);return this;};
 		/** not .... */
 		public M csaFlagNot(){this.put("csaFlagNot", "not");return this;};
		/** 参数值 [非空]       **/
		public M csaValue(Object csaValue){this.put("csaValue", csaValue);return this;};
	 	/** and csa_value is null */
 		public M csaValueNull(){if(this.get("csaValueNot")==null)this.put("csaValueNot", "");this.put("csaValue", null);return this;};
 		/** not .... */
 		public M csaValueNot(){this.put("csaValueNot", "not");return this;};
		/** 参数描述        **/
		public M csaDescribe(Object csaDescribe){this.put("csaDescribe", csaDescribe);return this;};
	 	/** and csa_describe is null */
 		public M csaDescribeNull(){if(this.get("csaDescribeNot")==null)this.put("csaDescribeNot", "");this.put("csaDescribe", null);return this;};
 		/** not .... */
 		public M csaDescribeNot(){this.put("csaDescribeNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有系统参数 **/
		public @api List<CsArgument> list(Integer size){
			return getCsArgumentList(this,size);
		}
		/** 获取系统参数分页 **/
		public @api Page<CsArgument> page(int page,int size){
			return getCsArgumentPage(page, size , this);
		}
		/** 根据查询条件取系统参数 **/
		public @api CsArgument get(){
			return getCsArgument(this);
		}
		/** 获取系统参数数 **/
		public @api Long count(){
			return getCsArgumentCount(this);
		}
		/** 获取系统参数表达式 **/
		public @api <T> T eval(String strEval){
			return getCsArgumentEval(strEval,this);
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
			updateCsArgument(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csaId="csaId";
		/** 参数名称 [非空]       **/
		public final static @type(String.class) @like String csaName="csaName";
		/** 参数标识 [非空]       **/
		public final static @type(String.class) @like String csaFlag="csaFlag";
		/** 参数值 [非空]       **/
		public final static @type(String.class) @like String csaValue="csaValue";
		/** 参数描述        **/
		public final static @type(String.class) @like String csaDescribe="csaDescribe";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csaId="csa_id";
		/** 参数名称 [非空]       **/
		public final static String csaName="csa_name";
		/** 参数标识 [非空]       **/
		public final static String csaFlag="csa_flag";
		/** 参数值 [非空]       **/
		public final static String csaValue="csa_value";
		/** 参数描述        **/
		public final static String csaDescribe="csa_describe";
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
				$.Set(name,CsArgument.getCsArgument(params));
			else
				$.Set(name,CsArgument.getCsArgumentList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取系统参数数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsArgument) $.GetRequest("CsArgument$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsArgument.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsArgument.getCsArgument(params);
			else
				value = CsArgument.getCsArgumentList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsArgument.Get($.add(CsArgument.F.csaId,param));
		else if(!$.empty(param.toString()))
			value = CsArgument.get(Long.valueOf(param.toString()));
		$.SetRequest("CsArgument$"+param.hashCode(), value);
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
	public void mergeSet(CsArgument old){
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