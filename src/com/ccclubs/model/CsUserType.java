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

@namespace("user/type")
public @caption("计费方式") @table("cs_user_type") class CsUserType implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csut_id")   @primary  @note("  ") Long csutId;// 主键 非空     
	private @caption("名称") @column("csut_name")    @note("  ") String csutName;// 非空     
	private @caption("描述") @column("csut_profile")    @note("  ") Long csutProfile;//     
	private @caption("标识") @column("csut_flag")    @note(" USER_TYPE_PERSONAL:USER_TYPE_PERSONAL USER_TYPE_ENTERPRISE:USER_TYPE_ENTERPRISE USER_TYPE_DAYHOUR:USER_TYPE_DAYHOUR USER_TYPE_HOUR:USER_TYPE_HOUR  ") String csutFlag;// 非空 USER_TYPE_PERSONAL:USER_TYPE_PERSONAL USER_TYPE_ENTERPRISE:USER_TYPE_ENTERPRISE USER_TYPE_DAYHOUR:USER_TYPE_DAYHOUR USER_TYPE_HOUR:USER_TYPE_HOUR     
	private @caption("状态") @column("csut_status")    @note(" 1:正常  ") Short csutStatus;// 非空 1:正常     
	
	//默认构造函数
	public CsUserType(){
	
	}
	
	//主键构造函数
	public CsUserType(Long id){
		this.csutId = id;
	}
	
	/**所有字段构造函数 CsUserType(csutName,csutProfile,csutFlag,csutStatus)
	 CsUserType(
	 	$.getString("csutName")//名称 [非空]
	 	,$.getLong("csutProfile")//描述
	 	,$.getString("csutFlag")//标识 [非空]
	 	,$.getShort("csutStatus")//状态 [非空]
	 )
	**/
	public CsUserType(String csutName,Long csutProfile,String csutFlag,Short csutStatus){
		this.csutName=csutName;
		this.csutProfile=csutProfile;
		this.csutFlag=csutFlag;
		this.csutStatus=csutStatus;
	}
	
	//设置非空字段
	public CsUserType setNotNull(String csutName,String csutFlag,Short csutStatus){
		this.csutName=csutName;
		this.csutFlag=csutFlag;
		this.csutStatus=csutStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsUserType csutId(Long csutId){
		this.csutId = csutId;
		this.setSeted(F.csutId);
		return this;
	}
	/** 名称 [非空]       **/
	public CsUserType csutName(String csutName){
		this.csutName = csutName;
		this.setSeted(F.csutName);
		return this;
	}
	/** 描述        **/
	public CsUserType csutProfile(Long csutProfile){
		this.csutProfile = csutProfile;
		this.setSeted(F.csutProfile);
		return this;
	}
	/** 标识 [非空]   USER_TYPE_PERSONAL:USER_TYPE_PERSONAL USER_TYPE_ENTERPRISE:USER_TYPE_ENTERPRISE USER_TYPE_DAYHOUR:USER_TYPE_DAYHOUR USER_TYPE_HOUR:USER_TYPE_HOUR     **/
	public CsUserType csutFlag(String csutFlag){
		this.csutFlag = csutFlag;
		this.setSeted(F.csutFlag);
		return this;
	}
	/** 状态 [非空]   1:正常     **/
	public CsUserType csutStatus(Short csutStatus){
		this.csutStatus = csutStatus;
		this.setSeted(F.csutStatus);
		return this;
	}
	
	
	//克隆对象
	public CsUserType clone(){
		CsUserType clone = new CsUserType();
		clone.csutName=this.csutName;
		clone.csutFlag=this.csutFlag;
		clone.csutStatus=this.csutStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取计费方式
	 * @param id
	 * @return
	 */
	public static @api CsUserType get(Long id){		
		return getCsUserTypeById(id);
	}
	/**
	 * 获取所有计费方式
	 * @return
	 */
	public static @api List<CsUserType> list(Map params,Integer size){
		return getCsUserTypeList(params,size);
	}
	/**
	 * 获取计费方式分页
	 * @return
	 */
	public static @api Page<CsUserType> page(int page,int size,Map params){
		return getCsUserTypePage(page, size , params);
	}
	/**
	 * 根据查询条件取计费方式
	 * @param params
	 * @return
	 */
	public static @api CsUserType Get(Map params){
		return getCsUserType(params);
	}
	/**
	 * 获取计费方式数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsUserTypeCount(params);
	}
	/**
	 * 获取计费方式数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsUserTypeEval(eval,params);
	}
	
	/**
	 * 根据ID取计费方式
	 * @param id
	 * @return
	 */
	public static @api CsUserType getCsUserTypeById(Long id){		
		CsUserType csUserType = (CsUserType) $.GetRequest("CsUserType$"+id);
		if(csUserType!=null)
			return csUserType;
		com.ccclubs.service.admin.ICsUserTypeService csUserTypeService = $.GetSpringBean("csUserTypeService");		
		return csUserTypeService.getCsUserTypeById(id);
	}
	
	
	/**
	 * 根据ID取计费方式的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsUserType.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsUserType csUserType = get(id);
		if(csUserType!=null){
			String strValue = csUserType.getCsutName$();
			if(!"CsutName".equals("CsutName"))
				strValue+="("+csUserType.getCsutName$()+")";
			MemCache.setValue(CsUserType.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsutName$();
		if(!"CsutName".equals("CsutName"))
			keyValue+="("+this.getCsutName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有计费方式
	 * @return
	 */
	public static @api List<CsUserType> getCsUserTypeList(Map params,Integer size){
		com.ccclubs.service.admin.ICsUserTypeService csUserTypeService = $.GetSpringBean("csUserTypeService");
		return csUserTypeService.getCsUserTypeList(params, size);
	}
	
	/**
	 * 获取计费方式分页
	 * @return
	 */
	public static @api Page<CsUserType> getCsUserTypePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsUserTypeService csUserTypeService = $.GetSpringBean("csUserTypeService");
		return csUserTypeService.getCsUserTypePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取计费方式
	 * @param params
	 * @return
	 */
	public static @api CsUserType getCsUserType(Map params){
		com.ccclubs.service.admin.ICsUserTypeService csUserTypeService = $.GetSpringBean("csUserTypeService");
		return csUserTypeService.getCsUserType(params);
	}
	
	/**
	 * 获取计费方式数
	 * @return
	 */
	public static @api Long getCsUserTypeCount(Map params){
		com.ccclubs.service.admin.ICsUserTypeService csUserTypeService = $.GetSpringBean("csUserTypeService");
		return csUserTypeService.getCsUserTypeCount(params);
	}
		
		
	/**
	 * 获取计费方式自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsUserTypeEval(String eval,Map params){
		com.ccclubs.service.admin.ICsUserTypeService csUserTypeService = $.GetSpringBean("csUserTypeService");
		return csUserTypeService.getCsUserTypeEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsUserType(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsUserTypeService csUserTypeService = $.GetSpringBean("csUserTypeService");
		csUserTypeService.updateCsUserTypeByConfirm(set, where);
	}
	
	
	/**
	 * 保存计费方式对象
	 * @param params
	 * @return
	 */
	public CsUserType save(){
		com.ccclubs.service.admin.ICsUserTypeService csUserTypeService = $.GetSpringBean("csUserTypeService");
		if(this.getCsutId()!=null)
			csUserTypeService.updateCsUserType(this);
		else
			return csUserTypeService.saveCsUserType(this);
		return this;
	}
	
	
	/**
	 * 更新计费方式对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsUserTypeService csUserTypeService = $.GetSpringBean("csUserTypeService");
		csUserTypeService.updateCsUserType$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsUserTypeService csUserTypeService = $.GetSpringBean("csUserTypeService");
		if($.equals($.config("logic_delete"),"true"))
			csUserTypeService.removeCsUserTypeById(this.getCsutId());
		else
			csUserTypeService.deleteCsUserTypeById(this.getCsutId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsUserTypeService csUserTypeService = $.GetSpringBean("csUserTypeService");
		return csUserTypeService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsutId(){
		return this.csutId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsutId$(){
		String strValue="";
		 strValue=$.str(this.getCsutId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsutId(Long csutId){
		this.csutId = csutId;
		this.setSeted(F.csutId);
	}
	/*******************************名称**********************************/	
	/**
	* 名称 [非空]      
	**/
	public String getCsutName(){
		return this.csutName;
	}
	/**
	* 获取名称格式化(toString)
	**/
	public String getCsutName$(){
		String strValue="";
		 strValue=$.str(this.getCsutName());
	 	 return strValue;
	}
	/**
	* 名称 [非空]      
	**/
	public void setCsutName(String csutName){
		this.csutName = csutName;
		this.setSeted(F.csutName);
	}
	/*******************************描述**********************************/	
	/**
	* 描述       
	**/
	public Long getCsutProfile(){
		return this.csutProfile;
	}
	/**
	* 获取描述格式化(toString)
	**/
	public String getCsutProfile$(){
		String strValue="";
		 strValue=$.str(this.getCsutProfile());
	 	 return strValue;
	}
	/**
	* 描述       
	**/
	public void setCsutProfile(Long csutProfile){
		this.csutProfile = csutProfile;
		this.setSeted(F.csutProfile);
	}
	/*******************************标识**********************************/	
	/**
	* 标识 [非空]   USER_TYPE_PERSONAL:USER_TYPE_PERSONAL USER_TYPE_ENTERPRISE:USER_TYPE_ENTERPRISE USER_TYPE_DAYHOUR:USER_TYPE_DAYHOUR USER_TYPE_HOUR:USER_TYPE_HOUR    
	**/
	public String getCsutFlag(){
		return this.csutFlag;
	}
	/**
	* 获取标识格式化(toString)
	**/
	public String getCsutFlag$(){
		String strValue="";
		 strValue=$.str(this.getCsutFlag());
	 	 return strValue;
	}
	/**
	* 标识 [非空]   USER_TYPE_PERSONAL:USER_TYPE_PERSONAL USER_TYPE_ENTERPRISE:USER_TYPE_ENTERPRISE USER_TYPE_DAYHOUR:USER_TYPE_DAYHOUR USER_TYPE_HOUR:USER_TYPE_HOUR    
	**/
	public void setCsutFlag(String csutFlag){
		this.csutFlag = csutFlag;
		this.setSeted(F.csutFlag);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常    
	**/
	public Short getCsutStatus(){
		return this.csutStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsutStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsutStatus()),"1"))
			strValue=$.str("正常");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常    
	**/
	public void setCsutStatus(Short csutStatus){
		this.csutStatus = csutStatus;
		this.setSeted(F.csutStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	public static final String USER_TYPE_PERSONAL = "USER_TYPE_PERSONAL";		//普通计费策略
	public static final String USER_TYPE_ENTERPRISE = "USER_TYPE_ENTERPRISE";	//长单计费策略
	public static final String USER_TYPE_DAYHOUR = "USER_TYPE_DAYHOUR";			//天小时策略
	public static final String USER_TYPE_HOUR = "USER_TYPE_HOUR";				//小时计费策略
	public static final String USER_TYPE_TIME_SHARE = "USER_TYPE_TIME_SHARE";	//分时计费策略
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsUserType.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUserType.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUserType.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsUserType.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsUserType.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUserType.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUserType.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsUserType.this);
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
		public M csutId(Object csutId){this.put("csutId", csutId);return this;};
	 	/** and csut_id is null */
 		public M csutIdNull(){if(this.get("csutIdNot")==null)this.put("csutIdNot", "");this.put("csutId", null);return this;};
 		/** not .... */
 		public M csutIdNot(){this.put("csutIdNot", "not");return this;};
		/** 名称 [非空]       **/
		public M csutName(Object csutName){this.put("csutName", csutName);return this;};
	 	/** and csut_name is null */
 		public M csutNameNull(){if(this.get("csutNameNot")==null)this.put("csutNameNot", "");this.put("csutName", null);return this;};
 		/** not .... */
 		public M csutNameNot(){this.put("csutNameNot", "not");return this;};
		/** 描述        **/
		public M csutProfile(Object csutProfile){this.put("csutProfile", csutProfile);return this;};
	 	/** and csut_profile is null */
 		public M csutProfileNull(){if(this.get("csutProfileNot")==null)this.put("csutProfileNot", "");this.put("csutProfile", null);return this;};
 		/** not .... */
 		public M csutProfileNot(){this.put("csutProfileNot", "not");return this;};
		/** and csut_profile >= ? */
		public M csutProfileMin(Object min){this.put("csutProfileMin", min);return this;};
		/** and csut_profile <= ? */
		public M csutProfileMax(Object max){this.put("csutProfileMax", max);return this;};
		/** 标识 [非空]   USER_TYPE_PERSONAL:USER_TYPE_PERSONAL USER_TYPE_ENTERPRISE:USER_TYPE_ENTERPRISE USER_TYPE_DAYHOUR:USER_TYPE_DAYHOUR USER_TYPE_HOUR:USER_TYPE_HOUR     **/
		public M csutFlag(Object csutFlag){this.put("csutFlag", csutFlag);return this;};
	 	/** and csut_flag is null */
 		public M csutFlagNull(){if(this.get("csutFlagNot")==null)this.put("csutFlagNot", "");this.put("csutFlag", null);return this;};
 		/** not .... */
 		public M csutFlagNot(){this.put("csutFlagNot", "not");return this;};
		/** 状态 [非空]   1:正常     **/
		public M csutStatus(Object csutStatus){this.put("csutStatus", csutStatus);return this;};
	 	/** and csut_status is null */
 		public M csutStatusNull(){if(this.get("csutStatusNot")==null)this.put("csutStatusNot", "");this.put("csutStatus", null);return this;};
 		/** not .... */
 		public M csutStatusNot(){this.put("csutStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有计费方式 **/
		public @api List<CsUserType> list(Integer size){
			return getCsUserTypeList(this,size);
		}
		/** 获取计费方式分页 **/
		public @api Page<CsUserType> page(int page,int size){
			return getCsUserTypePage(page, size , this);
		}
		/** 根据查询条件取计费方式 **/
		public @api CsUserType get(){
			return getCsUserType(this);
		}
		/** 获取计费方式数 **/
		public @api Long count(){
			return getCsUserTypeCount(this);
		}
		/** 获取计费方式表达式 **/
		public @api <T> T eval(String strEval){
			return getCsUserTypeEval(strEval,this);
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
			updateCsUserType(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csutId="csutId";
		/** 名称 [非空]       **/
		public final static @type(String.class) @like String csutName="csutName";
		/** 描述        **/
		public final static @type(Long.class)  String csutProfile="csutProfile";
		/** and csut_profile >= ? */
		public final static @type(Long.class) String csutProfileMin="csutProfileMin";
		/** and csut_profile <= ? */
		public final static @type(Long.class) String csutProfileMax="csutProfileMax";
		/** 标识 [非空]   USER_TYPE_PERSONAL:USER_TYPE_PERSONAL USER_TYPE_ENTERPRISE:USER_TYPE_ENTERPRISE USER_TYPE_DAYHOUR:USER_TYPE_DAYHOUR USER_TYPE_HOUR:USER_TYPE_HOUR     **/
		public final static @type(String.class) @like String csutFlag="csutFlag";
		/** 状态 [非空]   1:正常     **/
		public final static @type(Short.class)  String csutStatus="csutStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csutId="csut_id";
		/** 名称 [非空]       **/
		public final static String csutName="csut_name";
		/** 描述        **/
		public final static String csutProfile="csut_profile";
		/** 标识 [非空]   USER_TYPE_PERSONAL:USER_TYPE_PERSONAL USER_TYPE_ENTERPRISE:USER_TYPE_ENTERPRISE USER_TYPE_DAYHOUR:USER_TYPE_DAYHOUR USER_TYPE_HOUR:USER_TYPE_HOUR     **/
		public final static String csutFlag="csut_flag";
		/** 状态 [非空]   1:正常     **/
		public final static String csutStatus="csut_status";
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
				$.Set(name,CsUserType.getCsUserType(params));
			else
				$.Set(name,CsUserType.getCsUserTypeList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取计费方式数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsUserType) $.GetRequest("CsUserType$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsUserType.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsUserType.getCsUserType(params);
			else
				value = CsUserType.getCsUserTypeList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsUserType.Get($.add(CsUserType.F.csutId,param));
		else if(!$.empty(param.toString()))
			value = CsUserType.get(Long.valueOf(param.toString()));
		$.SetRequest("CsUserType$"+param.hashCode(), value);
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
	public void mergeSet(CsUserType old){
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