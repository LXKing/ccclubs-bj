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

@namespace("configurator/place")
public @caption("省市地区") @table("cs_place") class CsPlace implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csp_id")   @primary  @note("  ") Long cspId;// 主键 非空     
	private @caption("名称") @column("csp_name")    @note("  属性名称，如html中select的option的text内容") String cspName;// 非空  属性名称，如html中select的option的text内容   
	private @caption("类型") @column("csp_parent")  @hidden   @relate("$cspParent") @RelateClass(CsPlace.class)  @note("  ") Long cspParent;// 非空     
 	private CsPlace $cspParent;//关联对象[省市地区]
	private @caption("标识") @column("csp_flag")    @note("  程序变量命名规则格式") String cspFlag;//  程序变量命名规则格式   
	private @caption("状态") @column("csp_status")    @note(" 1:有效 0:内容  ") Short cspStatus;// 非空 1:有效 0:内容     
	
	//默认构造函数
	public CsPlace(){
	
	}
	
	//主键构造函数
	public CsPlace(Long id){
		this.cspId = id;
	}
	
	/**所有字段构造函数 CsPlace(cspName,cspParent,cspFlag,cspStatus)
	 CsPlace(
	 	$.getString("cspName")//名称 [非空]
	 	,$.getLong("cspParent")//类型 [非空]
	 	,$.getString("cspFlag")//标识
	 	,$.getShort("cspStatus")//状态 [非空]
	 )
	**/
	public CsPlace(String cspName,Long cspParent,String cspFlag,Short cspStatus){
		this.cspName=cspName;
		this.cspParent=cspParent;
		this.cspFlag=cspFlag;
		this.cspStatus=cspStatus;
	}
	
	//设置非空字段
	public CsPlace setNotNull(String cspName,Long cspParent,Short cspStatus){
		this.cspName=cspName;
		this.cspParent=cspParent;
		this.cspStatus=cspStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsPlace cspId(Long cspId){
		this.cspId = cspId;
		this.setSeted(F.cspId);
		return this;
	}
	/** 名称 [非空]    属性名称，如html中select的option的text内容   **/
	public CsPlace cspName(String cspName){
		this.cspName = cspName;
		this.setSeted(F.cspName);
		return this;
	}
	/** 类型 [非空] [CsPlace]      **/
	public CsPlace cspParent(Long cspParent){
		this.cspParent = cspParent;
		this.setSeted(F.cspParent);
		return this;
	}
	/** 标识     程序变量命名规则格式   **/
	public CsPlace cspFlag(String cspFlag){
		this.cspFlag = cspFlag;
		this.setSeted(F.cspFlag);
		return this;
	}
	/** 状态 [非空]   1:有效 0:内容     **/
	public CsPlace cspStatus(Short cspStatus){
		this.cspStatus = cspStatus;
		this.setSeted(F.cspStatus);
		return this;
	}
	
	
	//克隆对象
	public CsPlace clone(){
		CsPlace clone = new CsPlace();
		clone.cspName=this.cspName;
		clone.cspParent=this.cspParent;
		clone.cspStatus=this.cspStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取省市地区
	 * @param id
	 * @return
	 */
	public static @api CsPlace get(Long id){		
		return getCsPlaceById(id);
	}
	/**
	 * 获取所有省市地区
	 * @return
	 */
	public static @api List<CsPlace> list(Map params,Integer size){
		return getCsPlaceList(params,size);
	}
	/**
	 * 获取省市地区分页
	 * @return
	 */
	public static @api Page<CsPlace> page(int page,int size,Map params){
		return getCsPlacePage(page, size , params);
	}
	/**
	 * 根据查询条件取省市地区
	 * @param params
	 * @return
	 */
	public static @api CsPlace Get(Map params){
		return getCsPlace(params);
	}
	/**
	 * 获取省市地区数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsPlaceCount(params);
	}
	/**
	 * 获取省市地区数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsPlaceEval(eval,params);
	}
	
	/**
	 * 根据ID取省市地区
	 * @param id
	 * @return
	 */
	public static @api CsPlace getCsPlaceById(Long id){		
		CsPlace csPlace = (CsPlace) $.GetRequest("CsPlace$"+id);
		if(csPlace!=null)
			return csPlace;
		com.ccclubs.service.admin.ICsPlaceService csPlaceService = $.GetSpringBean("csPlaceService");		
		return csPlaceService.getCsPlaceById(id);
	}
	
	
	/**
	 * 根据ID取省市地区的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsPlace.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsPlace csPlace = get(id);
		if(csPlace!=null){
			String strValue = csPlace.getCspName$();
			if(!"CspName".equals("CspName"))
				strValue+="("+csPlace.getCspName$()+")";
			MemCache.setValue(CsPlace.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCspName$();
		if(!"CspName".equals("CspName"))
			keyValue+="("+this.getCspName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有省市地区
	 * @return
	 */
	public static @api List<CsPlace> getCsPlaceList(Map params,Integer size){
		com.ccclubs.service.admin.ICsPlaceService csPlaceService = $.GetSpringBean("csPlaceService");
		return csPlaceService.getCsPlaceList(params, size);
	}
	
	/**
	 * 获取省市地区分页
	 * @return
	 */
	public static @api Page<CsPlace> getCsPlacePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsPlaceService csPlaceService = $.GetSpringBean("csPlaceService");
		return csPlaceService.getCsPlacePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取省市地区
	 * @param params
	 * @return
	 */
	public static @api CsPlace getCsPlace(Map params){
		com.ccclubs.service.admin.ICsPlaceService csPlaceService = $.GetSpringBean("csPlaceService");
		return csPlaceService.getCsPlace(params);
	}
	
	/**
	 * 获取省市地区数
	 * @return
	 */
	public static @api Long getCsPlaceCount(Map params){
		com.ccclubs.service.admin.ICsPlaceService csPlaceService = $.GetSpringBean("csPlaceService");
		return csPlaceService.getCsPlaceCount(params);
	}
		
		
	/**
	 * 获取省市地区自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsPlaceEval(String eval,Map params){
		com.ccclubs.service.admin.ICsPlaceService csPlaceService = $.GetSpringBean("csPlaceService");
		return csPlaceService.getCsPlaceEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsPlace(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsPlaceService csPlaceService = $.GetSpringBean("csPlaceService");
		csPlaceService.updateCsPlaceByConfirm(set, where);
	}
	
	
	/**
	 * 保存省市地区对象
	 * @param params
	 * @return
	 */
	public CsPlace save(){
		com.ccclubs.service.admin.ICsPlaceService csPlaceService = $.GetSpringBean("csPlaceService");
		if(this.getCspId()!=null)
			csPlaceService.updateCsPlace(this);
		else
			return csPlaceService.saveCsPlace(this);
		return this;
	}
	
	
	/**
	 * 更新省市地区对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsPlaceService csPlaceService = $.GetSpringBean("csPlaceService");
		csPlaceService.updateCsPlace$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsPlaceService csPlaceService = $.GetSpringBean("csPlaceService");
			csPlaceService.deleteCsPlaceById(this.getCspId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsPlaceService csPlaceService = $.GetSpringBean("csPlaceService");
		return csPlaceService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCspId(){
		return this.cspId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCspId$(){
		String strValue="";
		 strValue=$.str(this.getCspId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCspId(Long cspId){
		this.cspId = cspId;
		this.setSeted(F.cspId);
	}
	/*******************************名称**********************************/	
	/**
	* 名称 [非空]    属性名称，如html中select的option的text内容  
	**/
	public String getCspName(){
		return this.cspName;
	}
	/**
	* 获取名称格式化(toString)
	**/
	public String getCspName$(){
		String strValue="";
		 strValue=$.str(this.getCspName());
	 	 return strValue;
	}
	/**
	* 名称 [非空]    属性名称，如html中select的option的text内容  
	**/
	public void setCspName(String cspName){
		this.cspName = cspName;
		this.setSeted(F.cspName);
	}
	/*******************************类型**********************************/	
	/**
	* 类型 [非空] [CsPlace]     
	**/
	public Long getCspParent(){
		return this.cspParent;
	}
	/**
	* 获取类型格式化(toString)
	**/
	public String getCspParent$(){
		String strValue="";
		if(this.getCspParent()!=null){
				strValue+=$.str(CsPlace.getKeyValue(this.getCspParent()));
		}			
	 	 return strValue;
	}
	/**
	* 类型 [非空] [CsPlace]     
	**/
	public void setCspParent(Long cspParent){
		this.cspParent = cspParent;
		this.setSeted(F.cspParent);
	}
	/**
	* 获取关联对象[省市地区]
	**/	 			
 	public CsPlace get$cspParent(){
 		com.ccclubs.model.CsPlace csPlace = (com.ccclubs.model.CsPlace) $.GetRequest("CsPlace$"+this.getCspParent());
 		if(csPlace!=null)
			return csPlace;
		if(this.getCspParent()!=null){
 			csPlace = CsPlace.get(this.getCspParent());
 		}
 		$.SetRequest("CsPlace$"+this.getCspParent(), csPlace);
	 	return csPlace;
	}
	/*******************************标识**********************************/	
	/**
	* 标识     程序变量命名规则格式  
	**/
	public String getCspFlag(){
		return this.cspFlag;
	}
	/**
	* 获取标识格式化(toString)
	**/
	public String getCspFlag$(){
		String strValue="";
		 strValue=$.str(this.getCspFlag());
	 	 return strValue;
	}
	/**
	* 标识     程序变量命名规则格式  
	**/
	public void setCspFlag(String cspFlag){
		this.cspFlag = cspFlag;
		this.setSeted(F.cspFlag);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:有效 0:内容    
	**/
	public Short getCspStatus(){
		return this.cspStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCspStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCspStatus()),"1"))
			strValue=$.str("有效");		 
		 if($.equals($.str(this.getCspStatus()),"0"))
			strValue=$.str("内容");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:有效 0:内容    
	**/
	public void setCspStatus(Short cspStatus){
		this.cspStatus = cspStatus;
		this.setSeted(F.cspStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsPlace.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsPlace.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsPlace.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsPlace.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsPlace.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsPlace.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsPlace.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsPlace.this);
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
		public M cspId(Object cspId){this.put("cspId", cspId);return this;};
	 	/** and csp_id is null */
 		public M cspIdNull(){if(this.get("cspIdNot")==null)this.put("cspIdNot", "");this.put("cspId", null);return this;};
 		/** not .... */
 		public M cspIdNot(){this.put("cspIdNot", "not");return this;};
		/** 名称 [非空]    属性名称，如html中select的option的text内容   **/
		public M cspName(Object cspName){this.put("cspName", cspName);return this;};
	 	/** and csp_name is null */
 		public M cspNameNull(){if(this.get("cspNameNot")==null)this.put("cspNameNot", "");this.put("cspName", null);return this;};
 		/** not .... */
 		public M cspNameNot(){this.put("cspNameNot", "not");return this;};
		/** 类型 [非空] [CsPlace]      **/
		public M cspParent(Object cspParent){this.put("cspParent", cspParent);return this;};
	 	/** and csp_parent is null */
 		public M cspParentNull(){if(this.get("cspParentNot")==null)this.put("cspParentNot", "");this.put("cspParent", null);return this;};
 		/** not .... */
 		public M cspParentNot(){this.put("cspParentNot", "not");return this;};
		public M cspParent$on(CsPlace.M value){
			this.put("CsPlace", value);
			this.put("cspParent$on", value);
			return this;
		};	
		/** 标识     程序变量命名规则格式   **/
		public M cspFlag(Object cspFlag){this.put("cspFlag", cspFlag);return this;};
	 	/** and csp_flag is null */
 		public M cspFlagNull(){if(this.get("cspFlagNot")==null)this.put("cspFlagNot", "");this.put("cspFlag", null);return this;};
 		/** not .... */
 		public M cspFlagNot(){this.put("cspFlagNot", "not");return this;};
		/** 状态 [非空]   1:有效 0:内容     **/
		public M cspStatus(Object cspStatus){this.put("cspStatus", cspStatus);return this;};
	 	/** and csp_status is null */
 		public M cspStatusNull(){if(this.get("cspStatusNot")==null)this.put("cspStatusNot", "");this.put("cspStatus", null);return this;};
 		/** not .... */
 		public M cspStatusNot(){this.put("cspStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有省市地区 **/
		public @api List<CsPlace> list(Integer size){
			return getCsPlaceList(this,size);
		}
		/** 获取省市地区分页 **/
		public @api Page<CsPlace> page(int page,int size){
			return getCsPlacePage(page, size , this);
		}
		/** 根据查询条件取省市地区 **/
		public @api CsPlace get(){
			return getCsPlace(this);
		}
		/** 获取省市地区数 **/
		public @api Long count(){
			return getCsPlaceCount(this);
		}
		/** 获取省市地区表达式 **/
		public @api <T> T eval(String strEval){
			return getCsPlaceEval(strEval,this);
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
			updateCsPlace(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cspId="cspId";
		/** 名称 [非空]    属性名称，如html中select的option的text内容   **/
		public final static @type(String.class) @like String cspName="cspName";
		/** 类型 [非空] [CsPlace]      **/
		public final static @type(Long.class)  String cspParent="cspParent";
		/** 标识     程序变量命名规则格式   **/
		public final static @type(String.class) @like String cspFlag="cspFlag";
		/** 状态 [非空]   1:有效 0:内容     **/
		public final static @type(Short.class)  String cspStatus="cspStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cspId="csp_id";
		/** 名称 [非空]    属性名称，如html中select的option的text内容   **/
		public final static String cspName="csp_name";
		/** 类型 [非空] [CsPlace]      **/
		public final static String cspParent="csp_parent";
		/** 标识     程序变量命名规则格式   **/
		public final static String cspFlag="csp_flag";
		/** 状态 [非空]   1:有效 0:内容     **/
		public final static String cspStatus="csp_status";
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
				$.Set(name,CsPlace.getCsPlace(params));
			else
				$.Set(name,CsPlace.getCsPlaceList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取省市地区数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsPlace) $.GetRequest("CsPlace$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsPlace.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsPlace.getCsPlace(params);
			else
				value = CsPlace.getCsPlaceList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsPlace.Get($.add(CsPlace.F.cspId,param));
		else if(!$.empty(param.toString()))
			value = CsPlace.get(Long.valueOf(param.toString()));
		$.SetRequest("CsPlace$"+param.hashCode(), value);
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
	public void mergeSet(CsPlace old){
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