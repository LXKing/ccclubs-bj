package com.ccclubs.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.dao.impl.MemCache;
import com.ccclubs.model.CsGoods.C;
import com.ccclubs.model.CsGoods.F;
import com.ccclubs.model.CsGoods.M;
import com.lazy3q.lang.RelateClass;
import com.lazy3q.lang.api;
import com.lazy3q.lang.caption;
import com.lazy3q.lang.column;
import com.lazy3q.lang.like;
import com.lazy3q.lang.namespace;
import com.lazy3q.lang.note;
import com.lazy3q.lang.primary;
import com.lazy3q.lang.relate;
import com.lazy3q.lang.table;
import com.lazy3q.lang.type;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.tags.BodyTag;
import com.lazy3q.web.util.Page;
@namespace("unit/csUnderlineMember")
public @caption("线下人员管理") @table("cs_underline_member") class CsUnderlineMember implements java.io.Serializable
{

	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cum_id")   @primary  @note("  ") Long cumId;// 主键 非空     

	private @caption("城市") @column("cum_host")    @relate("$cumHost") @RelateClass(SrvHost.class)  @note("  ") Long cumHost;// 非空     
	private SrvHost $cumHost;//关联对象[产品]
	
	
	
	private @caption("所在区域") @column("cum_area")    @relate("$cumArea") @RelateClass(CsArea.class)  @note("  ") Long cumArea;
	private CsArea $cumArea;//关联对象[产品]
	
	
	private @caption("对接人") @column("cum_user")    @note("  ") String  cumUser;// 非空    


	private @caption("联系方式") @column("cum_mobile")    @note("  ") String  cumMobile;// 非空     

	
	
	
	private @caption("添加时间") @column("cum_add_time")    @note("  ") Date  cumAddTime;// 非空     
	
	private @caption("更新新时间") @column("cum_update_time")    @note("  ") Date  cumUpdateTime;// 非空     


	 
	private @caption("状态") @column("cum_status")    @note("  ") Short  cumStatus;// 非空     

	
	
	//默认构造函数
	public CsUnderlineMember(){
	
	}
	
	//主键构造函数
	public CsUnderlineMember(Long id){
		this.cumId = id;
	}
	
	
	/**所有字段构造函数
	**/
	public CsUnderlineMember(Long cumHost,Date cumAddTime,Date cumUpdateTime,	Short cumStatus ,String cumUser,String cumMobile,Long cumArea){
	
		this.cumHost=cumHost;
		this.cumAddTime=cumAddTime;
		this.cumUpdateTime=cumUpdateTime;
		this.cumStatus=cumStatus;
		this.cumUser=cumUser;
		this.cumMobile=cumMobile;
		this.cumArea=cumArea;
		
	}
	
	//设置非空字段
	public CsUnderlineMember setNotNull(Long cumHost){
		this.cumHost=cumHost;
		return this;
	}
	/******************************************************************************/
	
	/** 编号 [非空]       **/
	public CsUnderlineMember cumId(Long cumId){
		this.cumId = cumId;
		this.setSeted(F.cumId);
		return this;
	}
	//
	public CsUnderlineMember cumHost(Long cumHost){
		this.cumHost = cumHost;
		this.setSeted(F.cumHost);
		return this;
	}
	
	public CsUnderlineMember cumAddTime(Date cumAddTime){
		this.cumAddTime = cumAddTime;
		this.setSeted(F.cumAddTime);
		return this;
	}
	
	public CsUnderlineMember cumUpdateTime(Date cumUpdateTime){
		this.cumUpdateTime = cumUpdateTime;
		this.setSeted(F.cumUpdateTime);
		return this;
	}
	
	
	public CsUnderlineMember cumStatus(Short cumStatus){
		this.cumStatus = cumStatus;
		this.setSeted(F.cumStatus);
		return this;
	}
	
	
	public CsUnderlineMember cumUser(String cumUser){
		this.cumUser = cumUser;
		this.setSeted(F.cumUser);
		return this;
	}
	public CsUnderlineMember cumMobile(String cumMobile){
		this.cumMobile = cumMobile;
		this.setSeted(F.cumMobile);
		return this;
	}
	
	
	
	
	public CsUnderlineMember cumArea(Long cumArea){
		this.cumArea = cumArea;
		this.setSeted(F.cumArea);
		return this;
	}
	
	//克隆对象
		public CsUnderlineMember clone(){
			CsUnderlineMember clone = new CsUnderlineMember();
			clone.cumHost=this.cumHost;
			clone.cumAddTime=this.cumAddTime;
			clone.cumStatus=this.cumStatus;
			return clone;
		}
	
	/**
	 * 
	 * @return
	 */
	public static @api Page<CsUnderlineMember> page(int page,int size,Map params){
		return getCsUnderlineMemberOnPage(page, size , params);
	}
	/**
	 * 获取分页
	 * @return
	 */
	public static @api Page<CsUnderlineMember> getCsUnderlineMemberOnPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsUnderlineMemberService CsUnderlineMemberService = $.GetSpringBean("csUnderlineMemberService");
		return CsUnderlineMemberService.getCsUnderlineMemberPage(page, size , params);
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
				$.Set(name,CsUnderlineMember.getCsUnderlineMember(params));
			else
				$.Set(name,CsUnderlineMember.getCsUnderlineMemberList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	
	/**
	 * 获取所有数据
	 * @return
	 */
	public static @api List<CsUnderlineMember> getCsUnderlineMemberList(Map params,Integer size){
		com.ccclubs.service.admin.ICsUnderlineMemberService CsUnderlineMemberService = $.GetSpringBean("csUnderlineMemberService");
		return CsUnderlineMemberService.getCsUnderlineMemberList(params, size);
	}
	
	/**
	 * 根据查询条件获取数据
	 * @param params
	 * @return
	 */
	public static @api CsUnderlineMember getCsUnderlineMember(Map params){
		com.ccclubs.service.admin.ICsUnderlineMemberService CsUnderlineMemberService = $.GetSpringBean("csUnderlineMemberService");
		return CsUnderlineMemberService.getCsUnderlineMember(params);
	}
	
	
	/**
	 * 根据ID取数据
	 * @param id
	 * @return
	 */
	public static @api CsUnderlineMember getCsUnderlineMemberById(Long id){		
		CsUnderlineMember CsUnderlineMember = (CsUnderlineMember) $.GetRequest("CsUnderlineMember$"+id);
		if(CsUnderlineMember!=null)
			return CsUnderlineMember;
		com.ccclubs.service.admin.ICsUnderlineMemberService CsUnderlineMemberService = $.GetSpringBean("csUnderlineMemberService");		
		return CsUnderlineMemberService.getCsUnderlineMemberById(id);
	}
	
	/**
	 * 根据ID取数据
	 * @param id
	 * @return
	 */
	public static @api CsUnderlineMember get(Long id){		
		return getCsUnderlineMemberById(id);
	}
	
	
	/**
	 * 根据查询条件取数据
	 * @param params
	 * @return
	 */
	public static @api CsUnderlineMember Get(Map params){
		return getCsUnderlineMember(params);
	}
	
	/**
	* 获取静态内容数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsUnderlineMember) $.GetRequest("CsUnderlineMember$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsUnderlineMember.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsUnderlineMember.getCsUnderlineMember(params);
			else
				value = CsUnderlineMember.getCsUnderlineMemberList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsUnderlineMember.Get($.add(CsUnderlineMember.F.cumId,param));
		else if(!$.empty(param.toString()))
			value = CsUnderlineMember.get(Long.valueOf(param.toString()));
		$.SetRequest("CsUnderlineMember$"+param.hashCode(), value);
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
	public void mergeSet(CsUnderlineMember old){
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
	
	
	
	private String suffix;
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	/*********************************************************************************/
	/**
	 * 获取数据分页
	 * @return
	 */
	public static @api Page<CsUnderlineMember> getCsUnderlineMemberPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsUnderlineMemberService CsUnderlineMemberService = $.GetSpringBean("csUnderlineMemberService");
		return CsUnderlineMemberService.getCsUnderlineMemberPage(page, size , params);
	}
	
	/**
	 * 获取数量
	 * @return
	 */
	public static @api Long getCsUnderlineMemberCount(Map params){
		com.ccclubs.service.admin.ICsUnderlineMemberService CsUnderlineMemberService = $.GetSpringBean("csUnderlineMemberService");
		return CsUnderlineMemberService.getCsUnderlineMemberCount(params);
	}
	
	/**
	 * 获取商品自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsUnderlineMemberEval(String eval,Map params){
		com.ccclubs.service.admin.ICsUnderlineMemberService CsUnderlineMemberService = $.GetSpringBean("csUnderlineMemberService");
		return CsUnderlineMemberService.getCsUnderlineMemberEval(eval,params);
	}
	
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsUnderlineMember(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsUnderlineMemberService CsUnderlineMemberService = $.GetSpringBean("csUnderlineMemberService");
		CsUnderlineMemberService.updateCsUnderlineMemberByConfirm(set, where);
	}
	
	/**
	 * 保存对象
	 * @param params
	 * @return
	 */
	public CsUnderlineMember save(){
		com.ccclubs.service.admin.ICsUnderlineMemberService CsUnderlineMemberService = $.GetSpringBean("csUnderlineMemberService");
		if(this.getCumId()!=null)
			CsUnderlineMemberService.updateCsUnderlineMember(this);
		else
			return CsUnderlineMemberService.saveCsUnderlineMember(this);
		return this;
	}
	
	
	
	
	/**
	 * 更新商品对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsUnderlineMemberService CsUnderlineMemberService = $.GetSpringBean("csUnderlineMemberService");
		CsUnderlineMemberService.updateCsUnderlineMember$NotNull(this);
	}
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsUnderlineMemberService CsUnderlineMemberService = $.GetSpringBean("csUnderlineMemberService");
		if($.equals($.config("logic_delete"),"true"))
			CsUnderlineMemberService.removeCsUnderlineMemberById(this.getCumId());
		else
			CsUnderlineMemberService.deleteCsUnderlineMemberById(this.getCumId());
	}
	
	
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsUnderlineMemberService CsUnderlineMemberService = $.GetSpringBean("csUnderlineMemberService");
		return CsUnderlineMemberService.executeTransaction(function);
	}
	
	
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsGoods.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsGoods.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUnderlineMember.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsUnderlineMember.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsGoods.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsGoods.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUnderlineMember.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsUnderlineMember.this);
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
		public M cumId(Object cumId){this.put("cumId", cumId);return this;};
	 	/** and csg_id is null */
 		public M cumIdNull(){if(this.get("cumIdNot")==null)this.put("cumIdNot", "");this.put("cumId", null);return this;};
 	
 		public M cumIdNot(){this.put("cumIdNot", "not");return this;};
		//
 		public M cumArea(Object cumArea){this.put("cumArea", cumArea);return this;};
 		public M cumAreaNull(){if(this.get("cumArea")==null)this.put("cumAreaNot", "");this.put("cumArea", null);return this;};
 		public M cumAreaNot(){this.put("cumAreaNot", "not");return this;};
 		public M cumArea$on(CsArea.M value){
			this.put("CsArea", value);
			this.put("cumArea$on", value);
			return this;
		};	
		//
		public M cumHost(Object cumHost){this.put("cumHost", cumHost);return this;};
 		public M cumHostNull(){if(this.get("cumHostNot")==null)this.put("cumHostNot", "");this.put("cumHost", null);return this;};
 		public M cumHostNot(){this.put("cumHostNot", "not");return this;};
 		public M cumHost$on(SrvHost.M value){
			this.put("SrvHost", value);
			this.put("cumHost$on", value);
			return this;
		};	
 		public M cumAddTime(Object cumAddTime){this.put("cumAddTime", cumAddTime);return this;};
 		public M cumAddTimeNull(){if(this.get("ccumAddTimeNot")==null)this.put("cumAddTimeNot", "");this.put("cumAddTime", null);return this;};
 		public M cumAddTimeNot(){this.put("cumAddTimeNot", "not");return this;};
 		public M cumAddTimeStart(Object start){this.put("cumAddTimeStart", start);return this;};			
 		public M cumAddTimeEnd(Object end){this.put("cumAddTimeEnd", end);return this;};
 		//
 		public M cumUpdateTime(Object cumUpdateTime){this.put("cumUpdateTime", cumUpdateTime);return this;};
 		public M cumUpdateTimeNull(){if(this.get("csosCloseDoorTimeNot")==null)this.put("cumUpdateTimeNot", "");this.put("cumUpdateTime", null);return this;};
 		public M cumUpdateTimeNot(){this.put("cumUpdateTimeNot", "not");return this;};
 		public M cumUpdateTimeStart(Object start){this.put("cumUpdateTimeStart", start);return this;};			
 		public M cumUpdateTimeEnd(Object end){this.put("cumUpdateTimeEnd", end);return this;};
 		//
 		//
 		public M cumStatus(Object cumStatus){this.put("cumStatus", cumStatus);return this;};
 		public M cumStatusNull(){if(this.get("cumStatusNot")==null)this.put("cumStatusNot", "");this.put("cumStatus", null);return this;};
 		public M cumStatusNot(){this.put("cumStatusNot", "not");return this;};
		//
 		public M cumUser(Object cumUser){this.put("cumUser", cumUser);return this;};
 		public M cumUserNull(){if(this.get("cumUserNot")==null)this.put("cumUserNot", "");this.put("cumUser", null);return this;};
 		public M cumUserNot(){this.put("cumUserNot", "not");return this;};
		//
		public M cumMobile(Object cumMobile){this.put("cumMobile", cumMobile);return this;};
 		public M cumMobileNull(){if(this.get("cumMobile")==null)this.put("cumMobileNot", "");this.put("cumMobile", null);return this;};
 		public M cumMobileNot(){this.put("cumMobileNot", "not");return this;};
		//
 		public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	
	 	/** 获取所有数据 **/
		public @api List<CsUnderlineMember> list(Integer size){
			return getCsUnderlineMemberList(this,size);
		}
		/** 获取数据分页 **/
		public @api Page<CsUnderlineMember> page(int page,int size){
			return getCsUnderlineMemberPage(page, size , this);
		}
		/** 根据查询条件取数据 **/
		public @api CsUnderlineMember get(){
			return getCsUnderlineMember(this);
		}
		/** 获取数 **/
		public @api Long count(){
			return getCsUnderlineMemberCount(this);
		}
		/** 获取数据表达式 **/
		public @api <T> T eval(String strEval){
			return getCsUnderlineMemberEval(strEval,this);
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
			updateCsUnderlineMember(set,where);
		}
	}
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cumId="cumId";
		/** 城市 [非空] [CsUserType]      **/
		public final static @type(Long.class)  String cumHost="cumHost";
		/** 网点 [CsRule]      **/
 		/**添加时间 */
 		public final static @type(Date.class) String cumAddTime="cumAddTime";
 		public final static @type(Date.class) String cumAddTimeStart="cumAddTimeStart";
 		public final static @type(Date.class) String cumAddTimeEnd="cumAddTimeEnd";
 		
		/** 更新时间 [非空]       **/
		public final static @type(Date.class)  String cumUpdateTime="cumUpdateTime";
		public final static @type(Date.class) String cumUpdateTimeStart="cumUpdateTimeStart";
 		public final static @type(Date.class) String cumUpdateTimeEnd="cumUpdateTimeEnd";
 		/** 状态 */
 		public final static @type(Short.class) String cumStatus="cumStatus";
		/** 车辆位置    **/
		public final static @type(String.class)  String cumUser="cumUser";
		/***人员位置***/
		public final static @type(String.class)  String cumMobile="cumMobile";
		/****人车距离**/
		/******/
		public final static @type(Long.class)  String cumArea="cumArea";
		
		
		
	}
	
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cumId="cum_id";
		/** 名称 [非空]       **/
		public final static String cumArea="cum_area";
		/** 计费类型 [非空] [CsUserType]      **/
		public final static String cumHost="cum_host";
		public final static String cumAddTime="cum_add_time";
		public final static String cumUpdateTime="cum_update_time";
		public final static String cumStatus="cum_status";
		public final static String cumUser="cum_user";
		public final static String cumMobile="cum_mobile";
		

		
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
	
	/*********************************************************/
	
	/**
	 * 根据ID取商品的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	//-----------------------------呆改
		String value = MemCache.getValue(CsUnderlineMember.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsUnderlineMember CsUnderlineMember = get(id);
//		if(CsUnderlineMember!=null){
//			String strValue = CsUnderlineMember.getCsgName$();
//			if(!"CsgName".equals("CsgName"))
//				strValue+="("+CsUnderlineMember.getCsgName$()+")";
//			MemCache.setValue(CsUnderlineMember.class, id ,strValue);
//			return strValue;
//		}
		return null;
	}	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){//----------------呆改
		String keyValue =null;
//		if(!"CsgName".equals("CsgName"))
//			keyValue+="("+this.getCsgName$()+")";
		return keyValue;
	}

	/**********************setter/getter***函数***********************************************************/
	public Long getCumId() {
		return this.cumId;
	}

	/**
	* 获取编号格式化(toString)
	**/
	public String getCumId$(){
		String strValue="";
		 strValue=$.str(this.getCumId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCumId(Long cumId){
		this.cumId = cumId;
		this.setSeted(F.cumId);
	}

	/**********************************/	
	public Long getCumHost() {
		return this.cumHost;
	}

	public void setCumHost(Long cumHost) {
		this.cumHost = cumHost;
		this.setSeted(F.cumHost);
	}
	public String getCumHost$(){
		String strValue="";
		 if(this.getCumHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCumHost()));
		}		
	 	 return strValue;
	}
	
	
	/**********************************/	
	
	/**********************************/	
	/**********************************/	
	/***********添加时间***********************/	
	public Date getCumAddTime() {
		return this.cumAddTime;
	}

	public void setCumAddTime(Date cumAddTime) {
		this.cumAddTime = cumAddTime;
		this.setSeted(F.cumAddTime);
	}
	
	public String getCumAddTime$(){
		 String strValue="";
		 strValue=$.str($.date(this.getCumAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	
	
	/*************更新新时间*********************/	
	public Date getCumUpdateTime() {
		return this.cumUpdateTime;
	}

	public void setCumUpdateTime(Date cumUpdateTime) {
		this.cumUpdateTime = cumUpdateTime;
		this.setSeted(F.cumUpdateTime);
	}
	public String getCumUpdateTime$(){
		 String strValue="";
		 strValue=$.str($.date(this.getCumUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	
	
	/************状态：1=未完成 2=已完成**********************/	
	public Short getCumStatus() {
		return this.cumStatus;
	}

	public void setCumStatus(Short cumStatus) {
		this.cumStatus = cumStatus;
		this.setSeted(F.cumStatus);
	}
	
	public String getCumStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCumStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCumStatus()),"-1"))
			strValue=$.str("已删除");		 
	 	 return strValue;
	}
	/************车辆位置**********************/	
	public String getCumUser() {
		return this.cumUser;
	}

	public void setCumUser(String cumUser) {
		this.cumUser = cumUser;
		this.setSeted(F.cumUser);
		
	}
	public String getCumUser$(){
		String strValue="";
		 strValue=$.str(this.getCumUser());
	 	 return strValue;
	}
	
	/************人员位置**********************/	
	public String getCumMobile() {
		return this.cumMobile;
	}

	public void setCumMobile(String cumMobile) {
		this.cumMobile = cumMobile;
		this.setSeted(F.cumMobile);
	}
	public String getCumMobile$(){
		String strValue="";
		 strValue=$.str(this.getCumMobile());
	 	 return strValue;
	}
	
	
	public Long getCumArea() {
		return this.cumArea;
	}

	public void setCumArea(Long cumArea) {
		this.cumArea = cumArea;
		this.setSeted(F.cumArea);
	}
	public String getCumArea$(){
		String strValue="";
		 if(this.getCumArea()!=null){
				strValue+=$.str(CsArea.getKeyValue(this.getCumArea()));
		}			
	 	 return strValue;
	}
}
