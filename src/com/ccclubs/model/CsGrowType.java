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

@namespace("configurator/growtype")
public @caption("成长分类型") @table("cs_grow_type") class CsGrowType implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csgt_id")   @primary  @note("  ") Long csgtId;// 主键 非空     
	private @caption("类型名称") @column("csgt_name")    @note("  ") String csgtName;// 非空     
	private @caption("人工操作项") @column("csgt_is_artif")    @note("  ") Boolean csgtIsArtif;// 非空     
	private @caption("与订单相关") @column("csgt_is_order")    @note("  ") Boolean csgtIsOrder;// 非空     
	private @caption("变化值") @column("csgt_value")    @note("  ") Integer csgtValue;// 非空     
	private @caption("类型说明") @column("csgt_profile")    @note("  ") String csgtProfile;//     
	private @caption("状态") @column("csgt_status")    @note(" 1:正常 0:无效  ") Short csgtStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsGrowType(){
	
	}
	
	//主键构造函数
	public CsGrowType(Long id){
		this.csgtId = id;
	}
	
	/**所有字段构造函数 CsGrowType(csgtName,csgtIsArtif,csgtIsOrder,csgtValue,csgtProfile,csgtStatus)
	 CsGrowType(
	 	$.getString("csgtName")//类型名称 [非空]
	 	,$.getBoolean("csgtIsArtif")//人工操作项 [非空]
	 	,$.getBoolean("csgtIsOrder")//与订单相关 [非空]
	 	,$.getInteger("csgtValue")//变化值 [非空]
	 	,$.getString("csgtProfile")//类型说明
	 	,$.getShort("csgtStatus")//状态 [非空]
	 )
	**/
	public CsGrowType(String csgtName,Boolean csgtIsArtif,Boolean csgtIsOrder,Integer csgtValue,String csgtProfile,Short csgtStatus){
		this.csgtName=csgtName;
		this.csgtIsArtif=csgtIsArtif;
		this.csgtIsOrder=csgtIsOrder;
		this.csgtValue=csgtValue;
		this.csgtProfile=csgtProfile;
		this.csgtStatus=csgtStatus;
	}
	
	//设置非空字段
	public CsGrowType setNotNull(String csgtName,Boolean csgtIsArtif,Boolean csgtIsOrder,Integer csgtValue,Short csgtStatus){
		this.csgtName=csgtName;
		this.csgtIsArtif=csgtIsArtif;
		this.csgtIsOrder=csgtIsOrder;
		this.csgtValue=csgtValue;
		this.csgtStatus=csgtStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsGrowType csgtId(Long csgtId){
		this.csgtId = csgtId;
		this.setSeted(F.csgtId);
		return this;
	}
	/** 类型名称 [非空]       **/
	public CsGrowType csgtName(String csgtName){
		this.csgtName = csgtName;
		this.setSeted(F.csgtName);
		return this;
	}
	/** 人工操作项 [非空]       **/
	public CsGrowType csgtIsArtif(Boolean csgtIsArtif){
		this.csgtIsArtif = csgtIsArtif;
		this.setSeted(F.csgtIsArtif);
		return this;
	}
	/** 与订单相关 [非空]       **/
	public CsGrowType csgtIsOrder(Boolean csgtIsOrder){
		this.csgtIsOrder = csgtIsOrder;
		this.setSeted(F.csgtIsOrder);
		return this;
	}
	/** 变化值 [非空]       **/
	public CsGrowType csgtValue(Integer csgtValue){
		this.csgtValue = csgtValue;
		this.setSeted(F.csgtValue);
		return this;
	}
	/** 类型说明        **/
	public CsGrowType csgtProfile(String csgtProfile){
		this.csgtProfile = csgtProfile;
		this.setSeted(F.csgtProfile);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsGrowType csgtStatus(Short csgtStatus){
		this.csgtStatus = csgtStatus;
		this.setSeted(F.csgtStatus);
		return this;
	}
	
	
	//克隆对象
	public CsGrowType clone(){
		CsGrowType clone = new CsGrowType();
		clone.csgtName=this.csgtName;
		clone.csgtIsArtif=this.csgtIsArtif;
		clone.csgtIsOrder=this.csgtIsOrder;
		clone.csgtValue=this.csgtValue;
		clone.csgtStatus=this.csgtStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取成长分类型
	 * @param id
	 * @return
	 */
	public static @api CsGrowType get(Long id){		
		return getCsGrowTypeById(id);
	}
	/**
	 * 获取所有成长分类型
	 * @return
	 */
	public static @api List<CsGrowType> list(Map params,Integer size){
		return getCsGrowTypeList(params,size);
	}
	/**
	 * 获取成长分类型分页
	 * @return
	 */
	public static @api Page<CsGrowType> page(int page,int size,Map params){
		return getCsGrowTypePage(page, size , params);
	}
	/**
	 * 根据查询条件取成长分类型
	 * @param params
	 * @return
	 */
	public static @api CsGrowType Get(Map params){
		return getCsGrowType(params);
	}
	/**
	 * 获取成长分类型数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsGrowTypeCount(params);
	}
	/**
	 * 获取成长分类型数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsGrowTypeEval(eval,params);
	}
	
	/**
	 * 根据ID取成长分类型
	 * @param id
	 * @return
	 */
	public static @api CsGrowType getCsGrowTypeById(Long id){		
		CsGrowType csGrowType = (CsGrowType) $.GetRequest("CsGrowType$"+id);
		if(csGrowType!=null)
			return csGrowType;
		com.ccclubs.service.admin.ICsGrowTypeService csGrowTypeService = $.GetSpringBean("csGrowTypeService");		
		return csGrowTypeService.getCsGrowTypeById(id);
	}
	
	
	/**
	 * 根据ID取成长分类型的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsGrowType.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsGrowType csGrowType = get(id);
		if(csGrowType!=null){
			String strValue = csGrowType.getCsgtName$();
			if(!"CsgtName".equals("CsgtName"))
				strValue+="("+csGrowType.getCsgtName$()+")";
			MemCache.setValue(CsGrowType.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsgtName$();
		if(!"CsgtName".equals("CsgtName"))
			keyValue+="("+this.getCsgtName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有成长分类型
	 * @return
	 */
	public static @api List<CsGrowType> getCsGrowTypeList(Map params,Integer size){
		com.ccclubs.service.admin.ICsGrowTypeService csGrowTypeService = $.GetSpringBean("csGrowTypeService");
		return csGrowTypeService.getCsGrowTypeList(params, size);
	}
	
	/**
	 * 获取成长分类型分页
	 * @return
	 */
	public static @api Page<CsGrowType> getCsGrowTypePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsGrowTypeService csGrowTypeService = $.GetSpringBean("csGrowTypeService");
		return csGrowTypeService.getCsGrowTypePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取成长分类型
	 * @param params
	 * @return
	 */
	public static @api CsGrowType getCsGrowType(Map params){
		com.ccclubs.service.admin.ICsGrowTypeService csGrowTypeService = $.GetSpringBean("csGrowTypeService");
		return csGrowTypeService.getCsGrowType(params);
	}
	
	/**
	 * 获取成长分类型数
	 * @return
	 */
	public static @api Long getCsGrowTypeCount(Map params){
		com.ccclubs.service.admin.ICsGrowTypeService csGrowTypeService = $.GetSpringBean("csGrowTypeService");
		return csGrowTypeService.getCsGrowTypeCount(params);
	}
		
		
	/**
	 * 获取成长分类型自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsGrowTypeEval(String eval,Map params){
		com.ccclubs.service.admin.ICsGrowTypeService csGrowTypeService = $.GetSpringBean("csGrowTypeService");
		return csGrowTypeService.getCsGrowTypeEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsGrowType(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsGrowTypeService csGrowTypeService = $.GetSpringBean("csGrowTypeService");
		csGrowTypeService.updateCsGrowTypeByConfirm(set, where);
	}
	
	
	/**
	 * 保存成长分类型对象
	 * @param params
	 * @return
	 */
	public CsGrowType save(){
		com.ccclubs.service.admin.ICsGrowTypeService csGrowTypeService = $.GetSpringBean("csGrowTypeService");
		if(this.getCsgtId()!=null)
			csGrowTypeService.updateCsGrowType(this);
		else
			return csGrowTypeService.saveCsGrowType(this);
		return this;
	}
	
	
	/**
	 * 更新成长分类型对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsGrowTypeService csGrowTypeService = $.GetSpringBean("csGrowTypeService");
		csGrowTypeService.updateCsGrowType$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsGrowTypeService csGrowTypeService = $.GetSpringBean("csGrowTypeService");
		if($.equals($.config("logic_delete"),"true"))
			csGrowTypeService.removeCsGrowTypeById(this.getCsgtId());
		else
			csGrowTypeService.deleteCsGrowTypeById(this.getCsgtId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsGrowTypeService csGrowTypeService = $.GetSpringBean("csGrowTypeService");
		return csGrowTypeService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsgtId(){
		return this.csgtId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsgtId$(){
		String strValue="";
		 strValue=$.str(this.getCsgtId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsgtId(Long csgtId){
		this.csgtId = csgtId;
		this.setSeted(F.csgtId);
	}
	/*******************************类型名称**********************************/	
	/**
	* 类型名称 [非空]      
	**/
	public String getCsgtName(){
		return this.csgtName;
	}
	/**
	* 获取类型名称格式化(toString)
	**/
	public String getCsgtName$(){
		String strValue="";
		 strValue=$.str(this.getCsgtName());
	 	 return strValue;
	}
	/**
	* 类型名称 [非空]      
	**/
	public void setCsgtName(String csgtName){
		this.csgtName = csgtName;
		this.setSeted(F.csgtName);
	}
	/*******************************人工操作项**********************************/	
	/**
	* 人工操作项 [非空]      
	**/
	public Boolean getCsgtIsArtif(){
		return this.csgtIsArtif;
	}
	/**
	* 获取人工操作项格式化(toString)
	**/
	public String getCsgtIsArtif$(){
		String strValue="";
		 strValue=$.str((this.getCsgtIsArtif()!=null && this.getCsgtIsArtif())?"是":"否");					 	
	 	 return strValue;
	}
	/**
	* 人工操作项 [非空]      
	**/
	public void setCsgtIsArtif(Boolean csgtIsArtif){
		this.csgtIsArtif = csgtIsArtif;
		this.setSeted(F.csgtIsArtif);
	}
	/*******************************与订单相关**********************************/	
	/**
	* 与订单相关 [非空]      
	**/
	public Boolean getCsgtIsOrder(){
		return this.csgtIsOrder;
	}
	/**
	* 获取与订单相关格式化(toString)
	**/
	public String getCsgtIsOrder$(){
		String strValue="";
		 strValue=$.str((this.getCsgtIsOrder()!=null && this.getCsgtIsOrder())?"是":"否");					 	
	 	 return strValue;
	}
	/**
	* 与订单相关 [非空]      
	**/
	public void setCsgtIsOrder(Boolean csgtIsOrder){
		this.csgtIsOrder = csgtIsOrder;
		this.setSeted(F.csgtIsOrder);
	}
	/*******************************变化值**********************************/	
	/**
	* 变化值 [非空]      
	**/
	public Integer getCsgtValue(){
		return this.csgtValue;
	}
	/**
	* 获取变化值格式化(toString)
	**/
	public String getCsgtValue$(){
		String strValue="";
		 strValue=$.str(this.getCsgtValue());
	 	 return strValue;
	}
	/**
	* 变化值 [非空]      
	**/
	public void setCsgtValue(Integer csgtValue){
		this.csgtValue = csgtValue;
		this.setSeted(F.csgtValue);
	}
	/*******************************类型说明**********************************/	
	/**
	* 类型说明       
	**/
	public String getCsgtProfile(){
		return this.csgtProfile;
	}
	/**
	* 获取类型说明格式化(toString)
	**/
	public String getCsgtProfile$(){
		String strValue="";
		 strValue=$.str(this.getCsgtProfile());
	 	 return strValue;
	}
	/**
	* 类型说明       
	**/
	public void setCsgtProfile(String csgtProfile){
		this.csgtProfile = csgtProfile;
		this.setSeted(F.csgtProfile);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsgtStatus(){
		return this.csgtStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsgtStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsgtStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsgtStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsgtStatus(Short csgtStatus){
		this.csgtStatus = csgtStatus;
		this.setSeted(F.csgtStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsGrowType.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsGrowType.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsGrowType.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsGrowType.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsGrowType.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsGrowType.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsGrowType.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsGrowType.this);
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
		public M csgtId(Object csgtId){this.put("csgtId", csgtId);return this;};
	 	/** and csgt_id is null */
 		public M csgtIdNull(){if(this.get("csgtIdNot")==null)this.put("csgtIdNot", "");this.put("csgtId", null);return this;};
 		/** not .... */
 		public M csgtIdNot(){this.put("csgtIdNot", "not");return this;};
		/** 类型名称 [非空]       **/
		public M csgtName(Object csgtName){this.put("csgtName", csgtName);return this;};
	 	/** and csgt_name is null */
 		public M csgtNameNull(){if(this.get("csgtNameNot")==null)this.put("csgtNameNot", "");this.put("csgtName", null);return this;};
 		/** not .... */
 		public M csgtNameNot(){this.put("csgtNameNot", "not");return this;};
		/** 人工操作项 [非空]       **/
		public M csgtIsArtif(Object csgtIsArtif){this.put("csgtIsArtif", csgtIsArtif);return this;};
	 	/** and csgt_is_artif is null */
 		public M csgtIsArtifNull(){if(this.get("csgtIsArtifNot")==null)this.put("csgtIsArtifNot", "");this.put("csgtIsArtif", null);return this;};
 		/** not .... */
 		public M csgtIsArtifNot(){this.put("csgtIsArtifNot", "not");return this;};
		/** 与订单相关 [非空]       **/
		public M csgtIsOrder(Object csgtIsOrder){this.put("csgtIsOrder", csgtIsOrder);return this;};
	 	/** and csgt_is_order is null */
 		public M csgtIsOrderNull(){if(this.get("csgtIsOrderNot")==null)this.put("csgtIsOrderNot", "");this.put("csgtIsOrder", null);return this;};
 		/** not .... */
 		public M csgtIsOrderNot(){this.put("csgtIsOrderNot", "not");return this;};
		/** 变化值 [非空]       **/
		public M csgtValue(Object csgtValue){this.put("csgtValue", csgtValue);return this;};
	 	/** and csgt_value is null */
 		public M csgtValueNull(){if(this.get("csgtValueNot")==null)this.put("csgtValueNot", "");this.put("csgtValue", null);return this;};
 		/** not .... */
 		public M csgtValueNot(){this.put("csgtValueNot", "not");return this;};
		/** and csgt_value >= ? */
		public M csgtValueMin(Object min){this.put("csgtValueMin", min);return this;};
		/** and csgt_value <= ? */
		public M csgtValueMax(Object max){this.put("csgtValueMax", max);return this;};
		/** 类型说明        **/
		public M csgtProfile(Object csgtProfile){this.put("csgtProfile", csgtProfile);return this;};
	 	/** and csgt_profile is null */
 		public M csgtProfileNull(){if(this.get("csgtProfileNot")==null)this.put("csgtProfileNot", "");this.put("csgtProfile", null);return this;};
 		/** not .... */
 		public M csgtProfileNot(){this.put("csgtProfileNot", "not");return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csgtStatus(Object csgtStatus){this.put("csgtStatus", csgtStatus);return this;};
	 	/** and csgt_status is null */
 		public M csgtStatusNull(){if(this.get("csgtStatusNot")==null)this.put("csgtStatusNot", "");this.put("csgtStatus", null);return this;};
 		/** not .... */
 		public M csgtStatusNot(){this.put("csgtStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有成长分类型 **/
		public @api List<CsGrowType> list(Integer size){
			return getCsGrowTypeList(this,size);
		}
		/** 获取成长分类型分页 **/
		public @api Page<CsGrowType> page(int page,int size){
			return getCsGrowTypePage(page, size , this);
		}
		/** 根据查询条件取成长分类型 **/
		public @api CsGrowType get(){
			return getCsGrowType(this);
		}
		/** 获取成长分类型数 **/
		public @api Long count(){
			return getCsGrowTypeCount(this);
		}
		/** 获取成长分类型表达式 **/
		public @api <T> T eval(String strEval){
			return getCsGrowTypeEval(strEval,this);
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
			updateCsGrowType(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csgtId="csgtId";
		/** 类型名称 [非空]       **/
		public final static @type(String.class) @like String csgtName="csgtName";
		/** 人工操作项 [非空]       **/
		public final static @type(Boolean.class)  String csgtIsArtif="csgtIsArtif";
		/** 与订单相关 [非空]       **/
		public final static @type(Boolean.class)  String csgtIsOrder="csgtIsOrder";
		/** 变化值 [非空]       **/
		public final static @type(Integer.class)  String csgtValue="csgtValue";
		/** and csgt_value >= ? */
		public final static @type(Integer.class) String csgtValueMin="csgtValueMin";
		/** and csgt_value <= ? */
		public final static @type(Integer.class) String csgtValueMax="csgtValueMax";
		/** 类型说明        **/
		public final static @type(String.class) @like String csgtProfile="csgtProfile";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csgtStatus="csgtStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csgtId="csgt_id";
		/** 类型名称 [非空]       **/
		public final static String csgtName="csgt_name";
		/** 人工操作项 [非空]       **/
		public final static String csgtIsArtif="csgt_is_artif";
		/** 与订单相关 [非空]       **/
		public final static String csgtIsOrder="csgt_is_order";
		/** 变化值 [非空]       **/
		public final static String csgtValue="csgt_value";
		/** 类型说明        **/
		public final static String csgtProfile="csgt_profile";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csgtStatus="csgt_status";
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
				$.Set(name,CsGrowType.getCsGrowType(params));
			else
				$.Set(name,CsGrowType.getCsGrowTypeList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取成长分类型数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsGrowType) $.GetRequest("CsGrowType$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsGrowType.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsGrowType.getCsGrowType(params);
			else
				value = CsGrowType.getCsGrowTypeList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsGrowType.Get($.add(CsGrowType.F.csgtId,param));
		else if(!$.empty(param.toString()))
			value = CsGrowType.get(Long.valueOf(param.toString()));
		$.SetRequest("CsGrowType$"+param.hashCode(), value);
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
	public void mergeSet(CsGrowType old){
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