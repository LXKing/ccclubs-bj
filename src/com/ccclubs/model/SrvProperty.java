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

@namespace("configurator/property")
public @caption("系统属性") @table("srv_property") class SrvProperty implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("sp_id")   @primary  @note("  ") Long spId;// 主键 非空     
	private @caption("名称") @column("sp_name")    @note("  属性名称，如html中select的option的text内容") String spName;// 非空  属性名称，如html中select的option的text内容   
	private @caption("类型") @column("sp_parent")  @hidden   @relate("$spParent") @RelateClass(SrvProperty.class)  @note("  ") Long spParent;// 非空     
 	private SrvProperty $spParent;//关联对象[系统属性]
	private @caption("标识") @column("sp_flag")    @note("  程序变量命名规则格式") String spFlag;//  程序变量命名规则格式   
	private @caption("数值") @column("sp_number")    @note("  ") Long spNumber;//     
	private @caption("串值") @column("sp_value")    @note("  ") String spValue;//     
	private @caption("文本") @column("sp_text")    @note("  ") String spText;//     
	private @caption("状态") @column("sp_status")    @note(" 1:有效 0:内容  ") Short spStatus;// 非空 1:有效 0:内容     
	
	//默认构造函数
	public SrvProperty(){
	
	}
	
	//主键构造函数
	public SrvProperty(Long id){
		this.spId = id;
	}
	
	/**所有字段构造函数 SrvProperty(spName,spParent,spFlag,spNumber,spValue,spText,spStatus)
	 SrvProperty(
	 	$.getString("spName")//名称 [非空]
	 	,$.getLong("spParent")//类型 [非空]
	 	,$.getString("spFlag")//标识
	 	,$.getLong("spNumber")//数值
	 	,$.getString("spValue")//串值
	 	,$.getString("spText")//文本
	 	,$.getShort("spStatus")//状态 [非空]
	 )
	**/
	public SrvProperty(String spName,Long spParent,String spFlag,Long spNumber,String spValue,String spText,Short spStatus){
		this.spName=spName;
		this.spParent=spParent;
		this.spFlag=spFlag;
		this.spNumber=spNumber;
		this.spValue=spValue;
		this.spText=spText;
		this.spStatus=spStatus;
	}
	
	//设置非空字段
	public SrvProperty setNotNull(String spName,Long spParent,Short spStatus){
		this.spName=spName;
		this.spParent=spParent;
		this.spStatus=spStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public SrvProperty spId(Long spId){
		this.spId = spId;
		this.setSeted(F.spId);
		return this;
	}
	/** 名称 [非空]    属性名称，如html中select的option的text内容   **/
	public SrvProperty spName(String spName){
		this.spName = spName;
		this.setSeted(F.spName);
		return this;
	}
	/** 类型 [非空] [SrvProperty]      **/
	public SrvProperty spParent(Long spParent){
		this.spParent = spParent;
		this.setSeted(F.spParent);
		return this;
	}
	/** 标识     程序变量命名规则格式   **/
	public SrvProperty spFlag(String spFlag){
		this.spFlag = spFlag;
		this.setSeted(F.spFlag);
		return this;
	}
	/** 数值        **/
	public SrvProperty spNumber(Long spNumber){
		this.spNumber = spNumber;
		this.setSeted(F.spNumber);
		return this;
	}
	/** 串值        **/
	public SrvProperty spValue(String spValue){
		this.spValue = spValue;
		this.setSeted(F.spValue);
		return this;
	}
	/** 文本        **/
	public SrvProperty spText(String spText){
		this.spText = spText;
		this.setSeted(F.spText);
		return this;
	}
	/** 状态 [非空]   1:有效 0:内容     **/
	public SrvProperty spStatus(Short spStatus){
		this.spStatus = spStatus;
		this.setSeted(F.spStatus);
		return this;
	}
	
	
	//克隆对象
	public SrvProperty clone(){
		SrvProperty clone = new SrvProperty();
		clone.spName=this.spName;
		clone.spParent=this.spParent;
		clone.spStatus=this.spStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取系统属性
	 * @param id
	 * @return
	 */
	public static @api SrvProperty get(Long id){		
		return getSrvPropertyById(id);
	}
	/**
	 * 获取所有系统属性
	 * @return
	 */
	public static @api List<SrvProperty> list(Map params,Integer size){
		return getSrvPropertyList(params,size);
	}
	/**
	 * 获取系统属性分页
	 * @return
	 */
	public static @api Page<SrvProperty> page(int page,int size,Map params){
		return getSrvPropertyPage(page, size , params);
	}
	/**
	 * 根据查询条件取系统属性
	 * @param params
	 * @return
	 */
	public static @api SrvProperty Get(Map params){
		return getSrvProperty(params);
	}
	/**
	 * 获取系统属性数
	 * @return
	 */
	public static @api Long count(Map params){
		return getSrvPropertyCount(params);
	}
	/**
	 * 获取系统属性数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getSrvPropertyEval(eval,params);
	}
	
	/**
	 * 根据ID取系统属性
	 * @param id
	 * @return
	 */
	public static @api SrvProperty getSrvPropertyById(Long id){		
		SrvProperty srvProperty = (SrvProperty) $.GetRequest("SrvProperty$"+id);
		if(srvProperty!=null)
			return srvProperty;
		com.ccclubs.service.admin.ISrvPropertyService srvPropertyService = $.GetSpringBean("srvPropertyService");		
		return srvPropertyService.getSrvPropertyById(id);
	}
	
	
	/**
	 * 根据ID取系统属性的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(SrvProperty.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		SrvProperty srvProperty = get(id);
		if(srvProperty!=null){
			String strValue = srvProperty.getSpName$();
			if(!"SpName".equals("SpName"))
				strValue+="("+srvProperty.getSpName$()+")";
			MemCache.setValue(SrvProperty.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getSpName$();
		if(!"SpName".equals("SpName"))
			keyValue+="("+this.getSpName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有系统属性
	 * @return
	 */
	public static @api List<SrvProperty> getSrvPropertyList(Map params,Integer size){
		com.ccclubs.service.admin.ISrvPropertyService srvPropertyService = $.GetSpringBean("srvPropertyService");
		return srvPropertyService.getSrvPropertyList(params, size);
	}
	
	/**
	 * 获取系统属性分页
	 * @return
	 */
	public static @api Page<SrvProperty> getSrvPropertyPage(int page,int size,Map params){
		com.ccclubs.service.admin.ISrvPropertyService srvPropertyService = $.GetSpringBean("srvPropertyService");
		return srvPropertyService.getSrvPropertyPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取系统属性
	 * @param params
	 * @return
	 */
	public static @api SrvProperty getSrvProperty(Map params){
		com.ccclubs.service.admin.ISrvPropertyService srvPropertyService = $.GetSpringBean("srvPropertyService");
		return srvPropertyService.getSrvProperty(params);
	}
	
	/**
	 * 获取系统属性数
	 * @return
	 */
	public static @api Long getSrvPropertyCount(Map params){
		com.ccclubs.service.admin.ISrvPropertyService srvPropertyService = $.GetSpringBean("srvPropertyService");
		return srvPropertyService.getSrvPropertyCount(params);
	}
		
		
	/**
	 * 获取系统属性自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getSrvPropertyEval(String eval,Map params){
		com.ccclubs.service.admin.ISrvPropertyService srvPropertyService = $.GetSpringBean("srvPropertyService");
		return srvPropertyService.getSrvPropertyEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateSrvProperty(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ISrvPropertyService srvPropertyService = $.GetSpringBean("srvPropertyService");
		srvPropertyService.updateSrvPropertyByConfirm(set, where);
	}
	
	
	/**
	 * 保存系统属性对象
	 * @param params
	 * @return
	 */
	public SrvProperty save(){
		com.ccclubs.service.admin.ISrvPropertyService srvPropertyService = $.GetSpringBean("srvPropertyService");
		if(this.getSpId()!=null)
			srvPropertyService.updateSrvProperty(this);
		else
			return srvPropertyService.saveSrvProperty(this);
		return this;
	}
	
	
	/**
	 * 更新系统属性对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ISrvPropertyService srvPropertyService = $.GetSpringBean("srvPropertyService");
		srvPropertyService.updateSrvProperty$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ISrvPropertyService srvPropertyService = $.GetSpringBean("srvPropertyService");
			srvPropertyService.deleteSrvPropertyById(this.getSpId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ISrvPropertyService srvPropertyService = $.GetSpringBean("srvPropertyService");
		return srvPropertyService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getSpId(){
		return this.spId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getSpId$(){
		String strValue="";
		 strValue=$.str(this.getSpId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setSpId(Long spId){
		this.spId = spId;
		this.setSeted(F.spId);
	}
	/*******************************名称**********************************/	
	/**
	* 名称 [非空]    属性名称，如html中select的option的text内容  
	**/
	public String getSpName(){
		return this.spName;
	}
	/**
	* 获取名称格式化(toString)
	**/
	public String getSpName$(){
		String strValue="";
		 strValue=$.str(this.getSpName());
	 	 return strValue;
	}
	/**
	* 名称 [非空]    属性名称，如html中select的option的text内容  
	**/
	public void setSpName(String spName){
		this.spName = spName;
		this.setSeted(F.spName);
	}
	/*******************************类型**********************************/	
	/**
	* 类型 [非空] [SrvProperty]     
	**/
	public Long getSpParent(){
		return this.spParent;
	}
	/**
	* 获取类型格式化(toString)
	**/
	public String getSpParent$(){
		String strValue="";
		if(this.getSpParent()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getSpParent()));
		}			
	 	 return strValue;
	}
	/**
	* 类型 [非空] [SrvProperty]     
	**/
	public void setSpParent(Long spParent){
		this.spParent = spParent;
		this.setSeted(F.spParent);
	}
	/**
	* 获取关联对象[系统属性]
	**/	 			
 	public SrvProperty get$spParent(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getSpParent());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getSpParent()!=null){
 			srvProperty = SrvProperty.get(this.getSpParent());
 		}
 		$.SetRequest("SrvProperty$"+this.getSpParent(), srvProperty);
	 	return srvProperty;
	}
	/*******************************标识**********************************/	
	/**
	* 标识     程序变量命名规则格式  
	**/
	public String getSpFlag(){
		return this.spFlag;
	}
	/**
	* 获取标识格式化(toString)
	**/
	public String getSpFlag$(){
		String strValue="";
		 strValue=$.str(this.getSpFlag());
	 	 return strValue;
	}
	/**
	* 标识     程序变量命名规则格式  
	**/
	public void setSpFlag(String spFlag){
		this.spFlag = spFlag;
		this.setSeted(F.spFlag);
	}
	/*******************************数值**********************************/	
	/**
	* 数值       
	**/
	public Long getSpNumber(){
		return this.spNumber;
	}
	/**
	* 获取数值格式化(toString)
	**/
	public String getSpNumber$(){
		String strValue="";
		 strValue=$.str(this.getSpNumber());
	 	 return strValue;
	}
	/**
	* 数值       
	**/
	public void setSpNumber(Long spNumber){
		this.spNumber = spNumber;
		this.setSeted(F.spNumber);
	}
	/*******************************串值**********************************/	
	/**
	* 串值       
	**/
	public String getSpValue(){
		return this.spValue;
	}
	/**
	* 获取串值格式化(toString)
	**/
	public String getSpValue$(){
		String strValue="";
		 strValue=$.str(this.getSpValue());
	 	 return strValue;
	}
	/**
	* 串值       
	**/
	public void setSpValue(String spValue){
		this.spValue = spValue;
		this.setSeted(F.spValue);
	}
	/*******************************文本**********************************/	
	/**
	* 文本       
	**/
	public String getSpText(){
		return this.spText;
	}
	/**
	* 获取文本格式化(toString)
	**/
	public String getSpText$(){
		String strValue="";
		 strValue=$.str(this.getSpText());
	 	 return strValue;
	}
	/**
	* 文本       
	**/
	public void setSpText(String spText){
		this.spText = spText;
		this.setSeted(F.spText);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:有效 0:内容    
	**/
	public Short getSpStatus(){
		return this.spStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getSpStatus$(){
		String strValue="";
		 if($.equals($.str(this.getSpStatus()),"1"))
			strValue=$.str("有效");		 
		 if($.equals($.str(this.getSpStatus()),"0"))
			strValue=$.str("内容");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:有效 0:内容    
	**/
	public void setSpStatus(Short spStatus){
		this.spStatus = spStatus;
		this.setSeted(F.spStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${SrvProperty.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvProperty.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvProperty.class.getMethod("get$"+$.forMat(key.toString())).invoke(SrvProperty.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${SrvProperty.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvProperty.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvProperty.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(SrvProperty.this);
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
		public M spId(Object spId){this.put("spId", spId);return this;};
	 	/** and sp_id is null */
 		public M spIdNull(){if(this.get("spIdNot")==null)this.put("spIdNot", "");this.put("spId", null);return this;};
 		/** not .... */
 		public M spIdNot(){this.put("spIdNot", "not");return this;};
		/** 名称 [非空]    属性名称，如html中select的option的text内容   **/
		public M spName(Object spName){this.put("spName", spName);return this;};
	 	/** and sp_name is null */
 		public M spNameNull(){if(this.get("spNameNot")==null)this.put("spNameNot", "");this.put("spName", null);return this;};
 		/** not .... */
 		public M spNameNot(){this.put("spNameNot", "not");return this;};
		/** 类型 [非空] [SrvProperty]      **/
		public M spParent(Object spParent){this.put("spParent", spParent);return this;};
	 	/** and sp_parent is null */
 		public M spParentNull(){if(this.get("spParentNot")==null)this.put("spParentNot", "");this.put("spParent", null);return this;};
 		/** not .... */
 		public M spParentNot(){this.put("spParentNot", "not");return this;};
		public M spParent$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("spParent$on", value);
			return this;
		};	
		/** 标识     程序变量命名规则格式   **/
		public M spFlag(Object spFlag){this.put("spFlag", spFlag);return this;};
	 	/** and sp_flag is null */
 		public M spFlagNull(){if(this.get("spFlagNot")==null)this.put("spFlagNot", "");this.put("spFlag", null);return this;};
 		/** not .... */
 		public M spFlagNot(){this.put("spFlagNot", "not");return this;};
		/** 数值        **/
		public M spNumber(Object spNumber){this.put("spNumber", spNumber);return this;};
	 	/** and sp_number is null */
 		public M spNumberNull(){if(this.get("spNumberNot")==null)this.put("spNumberNot", "");this.put("spNumber", null);return this;};
 		/** not .... */
 		public M spNumberNot(){this.put("spNumberNot", "not");return this;};
		/** and sp_number >= ? */
		public M spNumberMin(Object min){this.put("spNumberMin", min);return this;};
		/** and sp_number <= ? */
		public M spNumberMax(Object max){this.put("spNumberMax", max);return this;};
		/** 串值        **/
		public M spValue(Object spValue){this.put("spValue", spValue);return this;};
	 	/** and sp_value is null */
 		public M spValueNull(){if(this.get("spValueNot")==null)this.put("spValueNot", "");this.put("spValue", null);return this;};
 		/** not .... */
 		public M spValueNot(){this.put("spValueNot", "not");return this;};
		/** 文本        **/
		public M spText(Object spText){this.put("spText", spText);return this;};
	 	/** and sp_text is null */
 		public M spTextNull(){if(this.get("spTextNot")==null)this.put("spTextNot", "");this.put("spText", null);return this;};
 		/** not .... */
 		public M spTextNot(){this.put("spTextNot", "not");return this;};
		/** 状态 [非空]   1:有效 0:内容     **/
		public M spStatus(Object spStatus){this.put("spStatus", spStatus);return this;};
	 	/** and sp_status is null */
 		public M spStatusNull(){if(this.get("spStatusNot")==null)this.put("spStatusNot", "");this.put("spStatus", null);return this;};
 		/** not .... */
 		public M spStatusNot(){this.put("spStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有系统属性 **/
		public @api List<SrvProperty> list(Integer size){
			return getSrvPropertyList(this,size);
		}
		/** 获取系统属性分页 **/
		public @api Page<SrvProperty> page(int page,int size){
			return getSrvPropertyPage(page, size , this);
		}
		/** 根据查询条件取系统属性 **/
		public @api SrvProperty get(){
			return getSrvProperty(this);
		}
		/** 获取系统属性数 **/
		public @api Long count(){
			return getSrvPropertyCount(this);
		}
		/** 获取系统属性表达式 **/
		public @api <T> T eval(String strEval){
			return getSrvPropertyEval(strEval,this);
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
			updateSrvProperty(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String spId="spId";
		/** 名称 [非空]    属性名称，如html中select的option的text内容   **/
		public final static @type(String.class) @like String spName="spName";
		/** 类型 [非空] [SrvProperty]      **/
		public final static @type(Long.class)  String spParent="spParent";
		/** 标识     程序变量命名规则格式   **/
		public final static @type(String.class) @like String spFlag="spFlag";
		/** 数值        **/
		public final static @type(Long.class)  String spNumber="spNumber";
		/** and sp_number >= ? */
		public final static @type(Long.class) String spNumberMin="spNumberMin";
		/** and sp_number <= ? */
		public final static @type(Long.class) String spNumberMax="spNumberMax";
		/** 串值        **/
		public final static @type(String.class) @like String spValue="spValue";
		/** 文本        **/
		public final static @type(String.class) @like String spText="spText";
		/** 状态 [非空]   1:有效 0:内容     **/
		public final static @type(Short.class)  String spStatus="spStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String spId="sp_id";
		/** 名称 [非空]    属性名称，如html中select的option的text内容   **/
		public final static String spName="sp_name";
		/** 类型 [非空] [SrvProperty]      **/
		public final static String spParent="sp_parent";
		/** 标识     程序变量命名规则格式   **/
		public final static String spFlag="sp_flag";
		/** 数值        **/
		public final static String spNumber="sp_number";
		/** 串值        **/
		public final static String spValue="sp_value";
		/** 文本        **/
		public final static String spText="sp_text";
		/** 状态 [非空]   1:有效 0:内容     **/
		public final static String spStatus="sp_status";
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
				$.Set(name,SrvProperty.getSrvProperty(params));
			else
				$.Set(name,SrvProperty.getSrvPropertyList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取系统属性数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = SrvProperty.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = SrvProperty.getSrvProperty(params);
			else
				value = SrvProperty.getSrvPropertyList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = SrvProperty.Get($.add(SrvProperty.F.spId,param));
		else if(!$.empty(param.toString()))
			value = SrvProperty.get(Long.valueOf(param.toString()));
		$.SetRequest("SrvProperty$"+param.hashCode(), value);
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
	public void mergeSet(SrvProperty old){
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