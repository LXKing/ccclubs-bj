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

@namespace("configurator/holiday")
public @caption("节假日") @table("cs_holiday") class CsHoliday implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csh_id")   @primary  @note("  ") Long cshId;// 主键 非空     
	private @caption("节假日名称") @column("csh_name")    @note("  比如：国庆节、国庆节调班") String cshName;// 非空  比如：国庆节、国庆节调班   
	private @caption("日期") @column("csh_date")    @note("  ") Date cshDate;// 非空     
	private @caption("类型") @column("csh_type")    @note(" 0:放假 1:调班  调班日期按工作日计费") Short cshType;// 非空 0:放假 1:调班  调班日期按工作日计费   
	private @caption("星期几") @column("csh_index")    @note(" 1:星期天 2:星期一 3:星期二 4:星期三 5:星期四 6:星期五 7:星期六  类型为调班时有效，表示当天限行为星期几") Short cshIndex;// 1:星期天 2:星期一 3:星期二 4:星期三 5:星期四 6:星期五 7:星期六  类型为调班时有效，表示当天限行为星期几   
	
	//默认构造函数
	public CsHoliday(){
	
	}
	
	//主键构造函数
	public CsHoliday(Long id){
		this.cshId = id;
	}
	
	/**所有字段构造函数 CsHoliday(cshName,cshDate,cshType,cshIndex)
	 CsHoliday(
	 	$.getString("cshName")//节假日名称 [非空]
	 	,$.getDate("cshDate")//日期 [非空]
	 	,$.getShort("cshType")//类型 [非空]
	 	,$.getShort("cshIndex")//星期几
	 )
	**/
	public CsHoliday(String cshName,Date cshDate,Short cshType,Short cshIndex){
		this.cshName=cshName;
		this.cshDate=cshDate;
		this.cshType=cshType;
		this.cshIndex=cshIndex;
	}
	
	//设置非空字段
	public CsHoliday setNotNull(String cshName,Date cshDate,Short cshType){
		this.cshName=cshName;
		this.cshDate=cshDate;
		this.cshType=cshType;
		return this;
	}
	/** 编号 [非空]       **/
	public CsHoliday cshId(Long cshId){
		this.cshId = cshId;
		this.setSeted(F.cshId);
		return this;
	}
	/** 节假日名称 [非空]    比如：国庆节、国庆节调班   **/
	public CsHoliday cshName(String cshName){
		this.cshName = cshName;
		this.setSeted(F.cshName);
		return this;
	}
	/** 日期 [非空]       **/
	public CsHoliday cshDate(Date cshDate){
		this.cshDate = cshDate;
		this.setSeted(F.cshDate);
		return this;
	}
	/** 类型 [非空]   0:放假 1:调班  调班日期按工作日计费   **/
	public CsHoliday cshType(Short cshType){
		this.cshType = cshType;
		this.setSeted(F.cshType);
		return this;
	}
	/** 星期几    1:星期天 2:星期一 3:星期二 4:星期三 5:星期四 6:星期五 7:星期六  类型为调班时有效，表示当天限行为星期几   **/
	public CsHoliday cshIndex(Short cshIndex){
		this.cshIndex = cshIndex;
		this.setSeted(F.cshIndex);
		return this;
	}
	
	
	//克隆对象
	public CsHoliday clone(){
		CsHoliday clone = new CsHoliday();
		clone.cshName=this.cshName;
		clone.cshDate=this.cshDate;
		clone.cshType=this.cshType;
		return clone;
	}
	
	
	/**
	 * 根据ID取节假日
	 * @param id
	 * @return
	 */
	public static @api CsHoliday get(Long id){		
		return getCsHolidayById(id);
	}
	/**
	 * 获取所有节假日
	 * @return
	 */
	public static @api List<CsHoliday> list(Map params,Integer size){
		return getCsHolidayList(params,size);
	}
	/**
	 * 获取节假日分页
	 * @return
	 */
	public static @api Page<CsHoliday> page(int page,int size,Map params){
		return getCsHolidayPage(page, size , params);
	}
	/**
	 * 根据查询条件取节假日
	 * @param params
	 * @return
	 */
	public static @api CsHoliday Get(Map params){
		return getCsHoliday(params);
	}
	/**
	 * 获取节假日数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsHolidayCount(params);
	}
	/**
	 * 获取节假日数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsHolidayEval(eval,params);
	}
	
	/**
	 * 根据ID取节假日
	 * @param id
	 * @return
	 */
	public static @api CsHoliday getCsHolidayById(Long id){		
		CsHoliday csHoliday = (CsHoliday) $.GetRequest("CsHoliday$"+id);
		if(csHoliday!=null)
			return csHoliday;
		com.ccclubs.service.admin.ICsHolidayService csHolidayService = $.GetSpringBean("csHolidayService");		
		return csHolidayService.getCsHolidayById(id);
	}
	
	
	/**
	 * 根据ID取节假日的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsHoliday.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsHoliday csHoliday = get(id);
		if(csHoliday!=null){
			String strValue = csHoliday.getCshId$();
			if(!"CshId".equals("CshId"))
				strValue+="("+csHoliday.getCshId$()+")";
			if(!"CshId".equals("CshName"))
				strValue+="("+csHoliday.getCshName$()+")";
			MemCache.setValue(CsHoliday.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCshId$();
		if(!"CshId".equals("CshId"))
			keyValue+="("+this.getCshId$()+")";
		if(!"CshId".equals("CshName"))
			keyValue+="("+this.getCshName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有节假日
	 * @return
	 */
	public static @api List<CsHoliday> getCsHolidayList(Map params,Integer size){
		com.ccclubs.service.admin.ICsHolidayService csHolidayService = $.GetSpringBean("csHolidayService");
		return csHolidayService.getCsHolidayList(params, size);
	}
	
	/**
	 * 获取节假日分页
	 * @return
	 */
	public static @api Page<CsHoliday> getCsHolidayPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsHolidayService csHolidayService = $.GetSpringBean("csHolidayService");
		return csHolidayService.getCsHolidayPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取节假日
	 * @param params
	 * @return
	 */
	public static @api CsHoliday getCsHoliday(Map params){
		com.ccclubs.service.admin.ICsHolidayService csHolidayService = $.GetSpringBean("csHolidayService");
		return csHolidayService.getCsHoliday(params);
	}
	
	/**
	 * 获取节假日数
	 * @return
	 */
	public static @api Long getCsHolidayCount(Map params){
		com.ccclubs.service.admin.ICsHolidayService csHolidayService = $.GetSpringBean("csHolidayService");
		return csHolidayService.getCsHolidayCount(params);
	}
		
		
	/**
	 * 获取节假日自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsHolidayEval(String eval,Map params){
		com.ccclubs.service.admin.ICsHolidayService csHolidayService = $.GetSpringBean("csHolidayService");
		return csHolidayService.getCsHolidayEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsHoliday(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsHolidayService csHolidayService = $.GetSpringBean("csHolidayService");
		csHolidayService.updateCsHolidayByConfirm(set, where);
	}
	
	
	/**
	 * 保存节假日对象
	 * @param params
	 * @return
	 */
	public CsHoliday save(){
		com.ccclubs.service.admin.ICsHolidayService csHolidayService = $.GetSpringBean("csHolidayService");
		if(this.getCshId()!=null)
			csHolidayService.updateCsHoliday(this);
		else
			return csHolidayService.saveCsHoliday(this);
		return this;
	}
	
	
	/**
	 * 更新节假日对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsHolidayService csHolidayService = $.GetSpringBean("csHolidayService");
		csHolidayService.updateCsHoliday$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsHolidayService csHolidayService = $.GetSpringBean("csHolidayService");
			csHolidayService.deleteCsHolidayById(this.getCshId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsHolidayService csHolidayService = $.GetSpringBean("csHolidayService");
		return csHolidayService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCshId(){
		return this.cshId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCshId$(){
		String strValue="";
		 strValue=$.str(this.getCshId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCshId(Long cshId){
		this.cshId = cshId;
		this.setSeted(F.cshId);
	}
	/*******************************节假日名称**********************************/	
	/**
	* 节假日名称 [非空]    比如：国庆节、国庆节调班  
	**/
	public String getCshName(){
		return this.cshName;
	}
	/**
	* 获取节假日名称格式化(toString)
	**/
	public String getCshName$(){
		String strValue="";
		 strValue=$.str(this.getCshName());
	 	 return strValue;
	}
	/**
	* 节假日名称 [非空]    比如：国庆节、国庆节调班  
	**/
	public void setCshName(String cshName){
		this.cshName = cshName;
		this.setSeted(F.cshName);
	}
	/*******************************日期**********************************/	
	/**
	* 日期 [非空]      
	**/
	public Date getCshDate(){
		return this.cshDate;
	}
	/**
	* 获取日期格式化(toString)
	**/
	public String getCshDate$(){
		String strValue="";
		 strValue=$.str($.date(this.getCshDate(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 日期 [非空]      
	**/
	public void setCshDate(Date cshDate){
		this.cshDate = cshDate;
		this.setSeted(F.cshDate);
	}
	/*******************************类型**********************************/	
	/**
	* 类型 [非空]   0:放假 1:调班  调班日期按工作日计费  
	**/
	public Short getCshType(){
		return this.cshType;
	}
	/**
	* 获取类型格式化(toString)
	**/
	public String getCshType$(){
		String strValue="";
		 if($.equals($.str(this.getCshType()),"0"))
			strValue=$.str("放假");		 
		 if($.equals($.str(this.getCshType()),"1"))
			strValue=$.str("调班");		 
	 	 return strValue;
	}
	/**
	* 类型 [非空]   0:放假 1:调班  调班日期按工作日计费  
	**/
	public void setCshType(Short cshType){
		this.cshType = cshType;
		this.setSeted(F.cshType);
	}
	/*******************************星期几**********************************/	
	/**
	* 星期几    1:星期天 2:星期一 3:星期二 4:星期三 5:星期四 6:星期五 7:星期六  类型为调班时有效，表示当天限行为星期几  
	**/
	public Short getCshIndex(){
		return this.cshIndex;
	}
	/**
	* 获取星期几格式化(toString)
	**/
	public String getCshIndex$(){
		String strValue="";
		 if($.equals($.str(this.getCshIndex()),"1"))
			strValue=$.str("星期天");		 
		 if($.equals($.str(this.getCshIndex()),"2"))
			strValue=$.str("星期一");		 
		 if($.equals($.str(this.getCshIndex()),"3"))
			strValue=$.str("星期二");		 
		 if($.equals($.str(this.getCshIndex()),"4"))
			strValue=$.str("星期三");		 
		 if($.equals($.str(this.getCshIndex()),"5"))
			strValue=$.str("星期四");		 
		 if($.equals($.str(this.getCshIndex()),"6"))
			strValue=$.str("星期五");		 
		 if($.equals($.str(this.getCshIndex()),"7"))
			strValue=$.str("星期六");		 
	 	 return strValue;
	}
	/**
	* 星期几    1:星期天 2:星期一 3:星期二 4:星期三 5:星期四 6:星期五 7:星期六  类型为调班时有效，表示当天限行为星期几  
	**/
	public void setCshIndex(Short cshIndex){
		this.cshIndex = cshIndex;
		this.setSeted(F.cshIndex);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsHoliday.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsHoliday.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsHoliday.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsHoliday.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsHoliday.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsHoliday.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsHoliday.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsHoliday.this);
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
		public M cshId(Object cshId){this.put("cshId", cshId);return this;};
	 	/** and csh_id is null */
 		public M cshIdNull(){if(this.get("cshIdNot")==null)this.put("cshIdNot", "");this.put("cshId", null);return this;};
 		/** not .... */
 		public M cshIdNot(){this.put("cshIdNot", "not");return this;};
		/** 节假日名称 [非空]    比如：国庆节、国庆节调班   **/
		public M cshName(Object cshName){this.put("cshName", cshName);return this;};
	 	/** and csh_name is null */
 		public M cshNameNull(){if(this.get("cshNameNot")==null)this.put("cshNameNot", "");this.put("cshName", null);return this;};
 		/** not .... */
 		public M cshNameNot(){this.put("cshNameNot", "not");return this;};
		/** 日期 [非空]       **/
		public M cshDate(Object cshDate){this.put("cshDate", cshDate);return this;};
	 	/** and csh_date is null */
 		public M cshDateNull(){if(this.get("cshDateNot")==null)this.put("cshDateNot", "");this.put("cshDate", null);return this;};
 		/** not .... */
 		public M cshDateNot(){this.put("cshDateNot", "not");return this;};
 		/** and csh_date >= ? */
 		public M cshDateStart(Object start){this.put("cshDateStart", start);return this;};			
 		/** and csh_date <= ? */
 		public M cshDateEnd(Object end){this.put("cshDateEnd", end);return this;};
		/** 类型 [非空]   0:放假 1:调班  调班日期按工作日计费   **/
		public M cshType(Object cshType){this.put("cshType", cshType);return this;};
	 	/** and csh_type is null */
 		public M cshTypeNull(){if(this.get("cshTypeNot")==null)this.put("cshTypeNot", "");this.put("cshType", null);return this;};
 		/** not .... */
 		public M cshTypeNot(){this.put("cshTypeNot", "not");return this;};
		/** 星期几    1:星期天 2:星期一 3:星期二 4:星期三 5:星期四 6:星期五 7:星期六  类型为调班时有效，表示当天限行为星期几   **/
		public M cshIndex(Object cshIndex){this.put("cshIndex", cshIndex);return this;};
	 	/** and csh_index is null */
 		public M cshIndexNull(){if(this.get("cshIndexNot")==null)this.put("cshIndexNot", "");this.put("cshIndex", null);return this;};
 		/** not .... */
 		public M cshIndexNot(){this.put("cshIndexNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有节假日 **/
		public @api List<CsHoliday> list(Integer size){
			return getCsHolidayList(this,size);
		}
		/** 获取节假日分页 **/
		public @api Page<CsHoliday> page(int page,int size){
			return getCsHolidayPage(page, size , this);
		}
		/** 根据查询条件取节假日 **/
		public @api CsHoliday get(){
			return getCsHoliday(this);
		}
		/** 获取节假日数 **/
		public @api Long count(){
			return getCsHolidayCount(this);
		}
		/** 获取节假日表达式 **/
		public @api <T> T eval(String strEval){
			return getCsHolidayEval(strEval,this);
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
			updateCsHoliday(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cshId="cshId";
		/** 节假日名称 [非空]    比如：国庆节、国庆节调班   **/
		public final static @type(String.class) @like String cshName="cshName";
		/** 日期 [非空]       **/
		public final static @type(Date.class)  String cshDate="cshDate";
	 	/** and csh_date >= ? */
 		public final static @type(Date.class) String cshDateStart="cshDateStart";
 		/** and csh_date <= ? */
 		public final static @type(Date.class) String cshDateEnd="cshDateEnd";
		/** 类型 [非空]   0:放假 1:调班  调班日期按工作日计费   **/
		public final static @type(Short.class)  String cshType="cshType";
		/** 星期几    1:星期天 2:星期一 3:星期二 4:星期三 5:星期四 6:星期五 7:星期六  类型为调班时有效，表示当天限行为星期几   **/
		public final static @type(Short.class)  String cshIndex="cshIndex";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cshId="csh_id";
		/** 节假日名称 [非空]    比如：国庆节、国庆节调班   **/
		public final static String cshName="csh_name";
		/** 日期 [非空]       **/
		public final static String cshDate="csh_date";
		/** 类型 [非空]   0:放假 1:调班  调班日期按工作日计费   **/
		public final static String cshType="csh_type";
		/** 星期几    1:星期天 2:星期一 3:星期二 4:星期三 5:星期四 6:星期五 7:星期六  类型为调班时有效，表示当天限行为星期几   **/
		public final static String cshIndex="csh_index";
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
				$.Set(name,CsHoliday.getCsHoliday(params));
			else
				$.Set(name,CsHoliday.getCsHolidayList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取节假日数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsHoliday) $.GetRequest("CsHoliday$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsHoliday.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsHoliday.getCsHoliday(params);
			else
				value = CsHoliday.getCsHolidayList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsHoliday.Get($.add(CsHoliday.F.cshId,param));
		else if(!$.empty(param.toString()))
			value = CsHoliday.get(Long.valueOf(param.toString()));
		$.SetRequest("CsHoliday$"+param.hashCode(), value);
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
	public void mergeSet(CsHoliday old){
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