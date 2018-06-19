package com.ccclubs.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.dao.impl.MemCache;
import com.lazy3q.lang.api;
import com.lazy3q.lang.caption;
import com.lazy3q.lang.column;
import com.lazy3q.lang.namespace;
import com.lazy3q.lang.note;
import com.lazy3q.lang.primary;
import com.lazy3q.lang.table;
import com.lazy3q.lang.type;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.tags.BodyTag;
import com.lazy3q.web.util.Page;

@namespace("object/carparts")
public @caption("车辆部件") @table("cs_car_parts") class CsCarParts implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cscp_id")   @primary   @note("  ") Long cscpId;
	private @caption("姓名") @column("cscp_name")      @note("  ") String cscpName;
	private @caption("父级ID") @column("cscp_parent_id")      @note("  ") Long cscpParentId;
	private @caption("级别") @column("cscp_level")      @note("1:一级 2:二级 3:三级") Short cscpLevel;
	private @caption("数据状态") @column("cscp_status")      @note(" 1:正常 0:禁用  ") Short cscpStatus;
	private @caption("创建时间") @column("cscp_add_time")      @note("  ") Date cscpAddTime;
	private @caption("别名") @column("cscp_byname")      @note("  ") String cscpByname;
	
	//默认构造函数
	public CsCarParts(){
	
	}
	
	//主键构造函数
	public CsCarParts(Long id){
		this.cscpId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsCarParts(String cscpName,Long cscpParentId,Short cscpLevel,Short cscpStatus,Date cscpAddTime,String cscpByname){
		this.cscpName=cscpName;
		this.cscpParentId=cscpParentId;
		this.cscpLevel=cscpLevel;
		this.cscpStatus=cscpStatus;
		this.cscpAddTime=cscpAddTime;
		this.cscpByname=cscpByname;
	}
	
	//设置非空字段
	public CsCarParts setNotNull(String cscpName,Long cscpParentId,Short cscpLevel,Short cscpStatus,String cscpByname){
		this.cscpName=cscpName;
		this.cscpParentId=cscpParentId;
		this.cscpLevel=cscpLevel;
		this.cscpStatus=cscpStatus;
		this.cscpByname=cscpByname;
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsCarParts cscpId(Long cscpId){
		this.cscpId = cscpId;
		this.setSeted(F.cscpId);
		return this;
	}
	/** 姓名 [可空]       **/
	public CsCarParts cscpName(String cscpName){
		this.cscpName = cscpName;
		this.setSeted(F.cscpName);
		return this;
	}
	/** 父级ID [可空]       **/
	public CsCarParts cscpParentId(Long cscpParentId){
		this.cscpParentId = cscpParentId;
		this.setSeted(F.cscpParentId);
		return this;
	}
	/** 级别 [可空]       **/
	public CsCarParts cscpLevel(Short cscpLevel){
		this.cscpLevel = cscpLevel;
		this.setSeted(F.cscpLevel);
		return this;
	}
	/** 数据状态 [可空]       **/
	public CsCarParts cscpStatus(Short cscpStatus){
		this.cscpStatus = cscpStatus;
		this.setSeted(F.cscpStatus);
		return this;
	}
	/** 创建时间 [非空]       **/
	public CsCarParts cscpAddTime(Date cscpAddTime){
		this.cscpAddTime = cscpAddTime;
		this.setSeted(F.cscpAddTime);
		return this;
	}
	/** 别名 [可空]       **/
	public CsCarParts cscpByname(String cscpByname){
		this.cscpByname = cscpByname;
		this.setSeted(F.cscpByname);
		return this;
	}
	
	//克隆对象
	public CsCarParts clone(){
		CsCarParts clone = new CsCarParts();
		clone.cscpId=this.cscpId;
		clone.cscpName=this.cscpName;
		clone.cscpParentId=this.cscpParentId;
		clone.cscpLevel=this.cscpLevel;
		clone.cscpStatus=this.cscpStatus;
		clone.cscpAddTime=this.cscpAddTime;
		clone.cscpByname=this.cscpByname;
		return clone;
	}
	
	
	/**
	 * 根据ID取车辆部件
	 * @param id
	 * @return
	 */
	public static @api CsCarParts get(Long id){		
		return getCsCarPartsById(id);
	}
	/**
	 * 获取所有车辆部件
	 * @return
	 */
	public static @api List<CsCarParts> list(Map params,Integer size){
		return getCsCarPartsList(params,size);
	}
	/**
	 * 获取车辆部件分页
	 * @return
	 */
	public static @api Page<CsCarParts> page(int page,int size,Map params){
		return getCsCarPartsPage(page, size , params);
	}
	/**
	 * 根据查询条件取车辆部件
	 * @param params
	 * @return
	 */
	public static @api CsCarParts Get(Map params){
		return getCsCarParts(params);
	}
	/**
	 * 获取车辆部件数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsCarPartsCount(params);
	}
	/**
	 * 获取车辆部件数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsCarPartsEval(eval,params);
	}
	
	/**
	 * 根据ID取车辆部件
	 * @param id
	 * @return
	 */
	public static @api CsCarParts getCsCarPartsById(Long id){		
		CsCarParts csCarParts = (CsCarParts) $.GetRequest("CsCarParts$"+id);
		if(csCarParts!=null)
			return csCarParts;
		com.ccclubs.service.admin.ICsCarPartsService csCarPartsService = $.GetSpringBean("csCarPartsService");		
		return csCarPartsService.getCsCarPartsById(id);
	}
	
	
	/**
	 * 根据ID取车辆部件的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsCarParts.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsCarParts csCarParts = get(id);
		if(csCarParts!=null){
			String strValue = csCarParts.getCscpId$();
			if(!"CscpId".equals("CscpId"))
				strValue+="("+csCarParts.getCscpId$()+")";
			MemCache.setValue(CsCarParts.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCscpId$();
		if(!"CscpId".equals("CscpId"))
			keyValue+="("+this.getCscpId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有车辆部件
	 * @return
	 */
	public static @api List<CsCarParts> getCsCarPartsList(Map params,Integer size){
		com.ccclubs.service.admin.ICsCarPartsService csCarPartsService = $.GetSpringBean("csCarPartsService");
		return csCarPartsService.getCsCarPartsList(params, size);
	}
	
	/**
	 * 获取车辆部件分页
	 * @return
	 */
	public static @api Page<CsCarParts> getCsCarPartsPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsCarPartsService csCarPartsService = $.GetSpringBean("csCarPartsService");
		return csCarPartsService.getCsCarPartsPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取车辆部件
	 * @param params
	 * @return
	 */
	public static @api CsCarParts getCsCarParts(Map params){
		com.ccclubs.service.admin.ICsCarPartsService csCarPartsService = $.GetSpringBean("csCarPartsService");
		return csCarPartsService.getCsCarParts(params);
	}
	
	/**
	 * 获取车辆部件数
	 * @return
	 */
	public static @api Long getCsCarPartsCount(Map params){
		com.ccclubs.service.admin.ICsCarPartsService csCarPartsService = $.GetSpringBean("csCarPartsService");
		return csCarPartsService.getCsCarPartsCount(params);
	}
		
		
	/**
	 * 获取车辆部件自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsCarPartsEval(String eval,Map params){
		com.ccclubs.service.admin.ICsCarPartsService csCarPartsService = $.GetSpringBean("csCarPartsService");
		return csCarPartsService.getCsCarPartsEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsCarParts(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsCarPartsService csCarPartsService = $.GetSpringBean("csCarPartsService");
		csCarPartsService.updateCsCarPartsByConfirm(set, where);
	}
	
	
	/**
	 * 保存车辆部件对象
	 * @param params
	 * @return
	 */
	public CsCarParts save(){
		com.ccclubs.service.admin.ICsCarPartsService csCarPartsService = $.GetSpringBean("csCarPartsService");
		if(this.getCscpId()!=null)
			csCarPartsService.updateCsCarParts(this);
		else
			return csCarPartsService.saveCsCarParts(this);
		return this;
	}
	
	
	/**
	 * 更新车辆部件对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsCarPartsService csCarPartsService = $.GetSpringBean("csCarPartsService");
		csCarPartsService.updateCsCarParts$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsCarPartsService csCarPartsService = $.GetSpringBean("csCarPartsService");
		if($.equals($.config("logic_delete"),"true"))
			csCarPartsService.removeCsCarPartsById(this.getCscpId());
		else
			csCarPartsService.deleteCsCarPartsById(this.getCscpId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsCarPartsService csCarPartsService = $.GetSpringBean("csCarPartsService");
		return csCarPartsService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCscpId(){
		return this.cscpId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCscpId$(){
		String strValue="";
		 strValue=$.str(this.getCscpId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCscpId(Long cscpId){
		this.cscpId = cscpId;
		this.setSeted(F.cscpId);
	}
	/*******************************姓名**********************************/	
	/**
	* 姓名 [可空]      
	**/
	public String getCscpName(){
		return this.cscpName;
	}
	/**
	* 获取姓名格式化(toString)
	**/
	public String getCscpName$(){
		String strValue="";
		 strValue=$.str(this.getCscpName());
	 	 return strValue;
	}
	/**
	* 姓名 [可空]     
	**/
	public void setCscpName(String cscpName){
		this.cscpName = cscpName;
		this.setSeted(F.cscpName);
	}
	/*******************************父级ID**********************************/	
	/**
	* 父级ID [可空]      
	**/
	public Long getCscpParentId(){
		return this.cscpParentId;
	}
	/**
	* 获取父级ID格式化(toString)
	**/
	public String getCscpParentId$(){
		String strValue="";
		 strValue=$.str(this.getCscpParentId());
	 	 return strValue;
	}
	/**
	* 父级ID [可空]     
	**/
	public void setCscpParentId(Long cscpParentId){
		this.cscpParentId = cscpParentId;
		this.setSeted(F.cscpParentId);
	}
	/*******************************级别**********************************/	
	/**
	* 级别 [可空]      
	**/
	public Short getCscpLevel(){
		return this.cscpLevel;
	}
	/**
	* 获取级别格式化(toString)
	**/
	public String getCscpLevel$(){
		String strValue="";
		 strValue=$.str(this.getCscpLevel());
	 	 return strValue;
	}
	/**
	* 级别 [可空]     
	**/
	public void setCscpLevel(Short cscpLevel){
		this.cscpLevel = cscpLevel;
		this.setSeted(F.cscpLevel);
	}
	/*******************************数据状态**********************************/	
	/**
	* 数据状态 [可空]      
	**/
	public Short getCscpStatus(){
		return this.cscpStatus;
	}
	/**
	* 获取数据状态格式化(toString)
	**/
	public String getCscpStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCscpStatus()),"1"))
			 strValue=$.str("正常");
		 if($.equals($.str(this.getCscpStatus()),"0"))
			 strValue=$.str("禁用");
	 	 return strValue;
	}
	/**
	* 数据状态 [可空]     
	**/
	public void setCscpStatus(Short cscpStatus){
		this.cscpStatus = cscpStatus;
		this.setSeted(F.cscpStatus);
	}
	/*******************************创建时间**********************************/	
	/**
	* 创建时间 [非空]      
	**/
	public Date getCscpAddTime(){
		return this.cscpAddTime;
	}
	/**
	* 获取创建时间格式化(toString)
	**/
	public String getCscpAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscpAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 创建时间 [非空]     
	**/
	public void setCscpAddTime(Date cscpAddTime){
		this.cscpAddTime = cscpAddTime;
		this.setSeted(F.cscpAddTime);
	}
	/*******************************别名**********************************/	
	/**
	* 别名 [可空]      
	**/
	public String getCscpByname(){
		return this.cscpByname;
	}
	/**
	* 获取别名格式化(toString)
	**/
	public String getCscpByname$(){
		String strValue="";
		 strValue=$.str(this.getCscpByname());
	 	 return strValue;
	}
	/**
	* 别名 [可空]     
	**/
	public void setCscpByname(String cscpByname){
		this.cscpByname = cscpByname;
		this.setSeted(F.cscpByname);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsCarParts.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCarParts.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCarParts.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsCarParts.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsCarParts.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCarParts.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCarParts.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsCarParts.this);
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
		public M cscpId(Object cscpId){this.put("cscpId", cscpId);return this;};
		/** and cscp_id is null */
		public M cscpIdNull(){if(this.get("cscpIdNot")==null)this.put("cscpIdNot", "");this.put("cscpId", null);return this;};
		/** not .... */
		public M cscpIdNot(){this.put("cscpIdNot", "not");return this;};
		/** and cscp_id >= ? */
		public M cscpIdMin(Object min){this.put("cscpIdMin", min);return this;};
		/** and cscp_id <= ? */
		public M cscpIdMax(Object max){this.put("cscpIdMax", max);return this;};
		/** 姓名 [可空]       **/
		public M cscpName(Object cscpName){this.put("cscpName", cscpName);return this;};
		/** and cscp_name is null */
		public M cscpNameNull(){if(this.get("cscpNameNot")==null)this.put("cscpNameNot", "");this.put("cscpName", null);return this;};
		/** not .... */
		public M cscpNameNot(){this.put("cscpNameNot", "not");return this;};
		/** 父级ID [可空]       **/
		public M cscpParentId(Object cscpParentId){this.put("cscpParentId", cscpParentId);return this;};
		/** and cscp_parent_id is null */
		public M cscpParentIdNull(){if(this.get("cscpParentIdNot")==null)this.put("cscpParentIdNot", "");this.put("cscpParentId", null);return this;};
		/** not .... */
		public M cscpParentIdNot(){this.put("cscpParentIdNot", "not");return this;};
		/** and cscp_parent_id >= ? */
		public M cscpParentIdMin(Object min){this.put("cscpParentIdMin", min);return this;};
		/** and cscp_parent_id <= ? */
		public M cscpParentIdMax(Object max){this.put("cscpParentIdMax", max);return this;};
		/** 级别 [可空]       **/
		public M cscpLevel(Object cscpLevel){this.put("cscpLevel", cscpLevel);return this;};
		/** and cscp_level is null */
		public M cscpLevelNull(){if(this.get("cscpLevelNot")==null)this.put("cscpLevelNot", "");this.put("cscpLevel", null);return this;};
		/** not .... */
		public M cscpLevelNot(){this.put("cscpLevelNot", "not");return this;};
		/** and cscp_level >= ? */
		public M cscpLevelMin(Object min){this.put("cscpLevelMin", min);return this;};
		/** and cscp_level <= ? */
		public M cscpLevelMax(Object max){this.put("cscpLevelMax", max);return this;};
		/** 数据状态 [可空]       **/
		public M cscpStatus(Object cscpStatus){this.put("cscpStatus", cscpStatus);return this;};
		/** and cscp_status is null */
		public M cscpStatusNull(){if(this.get("cscpStatusNot")==null)this.put("cscpStatusNot", "");this.put("cscpStatus", null);return this;};
		/** not .... */
		public M cscpStatusNot(){this.put("cscpStatusNot", "not");return this;};
		/** and cscp_status >= ? */
		public M cscpStatusMin(Object min){this.put("cscpStatusMin", min);return this;};
		/** and cscp_status <= ? */
		public M cscpStatusMax(Object max){this.put("cscpStatusMax", max);return this;};
		/** 创建时间 [非空]       **/
		public M cscpAddTime(Object cscpAddTime){this.put("cscpAddTime", cscpAddTime);return this;};
		/** and cscp_add_time is null */
		public M cscpAddTimeNull(){if(this.get("cscpAddTimeNot")==null)this.put("cscpAddTimeNot", "");this.put("cscpAddTime", null);return this;};
		/** not .... */
		public M cscpAddTimeNot(){this.put("cscpAddTimeNot", "not");return this;};
		/** and cscp_add_time >= ? */
 		public M cscpAddTimeStart(Object start){this.put("cscpAddTimeStart", start);return this;};			
 		/** and cscp_add_time <= ? */
 		public M cscpAddTimeEnd(Object end){this.put("cscpAddTimeEnd", end);return this;};
		/** 别名 [可空]       **/
		public M cscpByname(Object cscpByname){this.put("cscpByname", cscpByname);return this;};
		/** and cscp_byname is null */
		public M cscpBynameNull(){if(this.get("cscpBynameNot")==null)this.put("cscpBynameNot", "");this.put("cscpByname", null);return this;};
		/** not .... */
		public M cscpBynameNot(){this.put("cscpBynameNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有车辆部件 **/
		public @api List<CsCarParts> list(Integer size){
			return getCsCarPartsList(this,size);
		}
		/** 获取车辆部件分页 **/
		public @api Page<CsCarParts> page(int page,int size){
			return getCsCarPartsPage(page, size , this);
		}
		/** 根据查询条件取车辆部件 **/
		public @api CsCarParts get(){
			return getCsCarParts(this);
		}
		/** 获取车辆部件数 **/
		public @api Long count(){
			return getCsCarPartsCount(this);
		}
		/** 获取车辆部件表达式 **/
		public @api <T> T eval(String strEval){
			return getCsCarPartsEval(strEval,this);
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
			updateCsCarParts(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscpId="cscpId";
		/** 姓名 [可空]       **/
		public final static @type(String.class)  String cscpName="cscpName";
		/** 父级ID [可空]       **/
		public final static @type(Long.class)  String cscpParentId="cscpParentId";
		/** 级别 [可空]       **/
		public final static @type(Short.class)  String cscpLevel="cscpLevel";
		/** 数据状态 [可空]       **/
		public final static @type(Short.class)  String cscpStatus="cscpStatus";
		/** 创建时间 [非空]       **/
		public final static @type(Date.class)  String cscpAddTime="cscpAddTime";
		/** and cscp_add_time >= ? */
 		public final static @type(Date.class) String cscpAddTimeStart="cscpAddTimeStart";
 		/** and cscp_add_time <= ? */
 		public final static @type(Date.class) String cscpAddTimeEnd="cscpAddTimeEnd";
		/** 别名 [可空]       **/
		public final static @type(String.class)  String cscpByname="cscpByname";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscpId="cscp_id";
		/** 姓名 [可空]       **/
		public final static String cscpName="cscp_name";
		/** 父级ID [可空]       **/
		public final static String cscpParentId="cscp_parent_id";
		/** 级别 [可空]       **/
		public final static String cscpLevel="cscp_level";
		/** 数据状态 [可空]       **/
		public final static String cscpStatus="cscp_status";
		/** 创建时间 [非空]       **/
		public final static String cscpAddTime="cscp_add_time";
		/** 别名 [可空]       **/
		public final static String cscpByname="cscp_byname";
		
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
				$.Set(name,CsCarParts.getCsCarParts(params));
			else
				$.Set(name,CsCarParts.getCsCarPartsList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取车辆部件数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsCarParts) $.GetRequest("CsCarParts$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsCarParts.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsCarParts.getCsCarParts(params);
			else
				value = CsCarParts.getCsCarPartsList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsCarParts.Get($.add(CsCarParts.F.cscpId,param));
		else if(!$.empty(param.toString()))
			value = CsCarParts.get(Long.valueOf(param.toString()));
		$.SetRequest("CsCarParts$"+param.hashCode(), value);
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
	public void mergeSet(CsCarParts old){
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