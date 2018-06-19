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

@namespace("systematic/srvlock")
public @caption("事务锁") @table("srv_lock") class SrvLock implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("sl_id")   @primary  @note("  ") Long slId;// 主键 非空     
	private @caption("名称") @column("sl_profile")    @note("  ") String slProfile;// 非空     
	private @caption("类型") @column("sl_type")    @note("  ") String slType;// 非空     
	private @caption("时间") @column("sl_time")    @note("  ") Date slTime;// 非空     
	
	//默认构造函数
	public SrvLock(){
	
	}
	
	//主键构造函数
	public SrvLock(Long id){
		this.slId = id;
	}
	
	/**所有字段构造函数 SrvLock(slProfile,slType,slTime)
	 SrvLock(
	 	$.getString("slProfile")//名称 [非空]
	 	,$.getString("slType")//类型 [非空]
	 	,$.getDate("slTime")//时间 [非空]
	 )
	**/
	public SrvLock(String slProfile,String slType,Date slTime){
		this.slProfile=slProfile;
		this.slType=slType;
		this.slTime=slTime;
	}
	
	//设置非空字段
	public SrvLock setNotNull(String slProfile,String slType,Date slTime){
		this.slProfile=slProfile;
		this.slType=slType;
		this.slTime=slTime;
		return this;
	}
	/** 编号 [非空]       **/
	public SrvLock slId(Long slId){
		this.slId = slId;
		this.setSeted(F.slId);
		return this;
	}
	/** 名称 [非空]       **/
	public SrvLock slProfile(String slProfile){
		this.slProfile = slProfile;
		this.setSeted(F.slProfile);
		return this;
	}
	/** 类型 [非空]       **/
	public SrvLock slType(String slType){
		this.slType = slType;
		this.setSeted(F.slType);
		return this;
	}
	/** 时间 [非空]       **/
	public SrvLock slTime(Date slTime){
		this.slTime = slTime;
		this.setSeted(F.slTime);
		return this;
	}
	
	
	//克隆对象
	public SrvLock clone(){
		SrvLock clone = new SrvLock();
		clone.slProfile=this.slProfile;
		clone.slType=this.slType;
		clone.slTime=this.slTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取事务锁
	 * @param id
	 * @return
	 */
	public static @api SrvLock get(Long id){		
		return getSrvLockById(id);
	}
	/**
	 * 获取所有事务锁
	 * @return
	 */
	public static @api List<SrvLock> list(Map params,Integer size){
		return getSrvLockList(params,size);
	}
	/**
	 * 获取事务锁分页
	 * @return
	 */
	public static @api Page<SrvLock> page(int page,int size,Map params){
		return getSrvLockPage(page, size , params);
	}
	/**
	 * 根据查询条件取事务锁
	 * @param params
	 * @return
	 */
	public static @api SrvLock Get(Map params){
		return getSrvLock(params);
	}
	/**
	 * 获取事务锁数
	 * @return
	 */
	public static @api Long count(Map params){
		return getSrvLockCount(params);
	}
	/**
	 * 获取事务锁数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getSrvLockEval(eval,params);
	}
	
	/**
	 * 根据ID取事务锁
	 * @param id
	 * @return
	 */
	public static @api SrvLock getSrvLockById(Long id){		
		SrvLock srvLock = (SrvLock) $.GetRequest("SrvLock$"+id);
		if(srvLock!=null)
			return srvLock;
		com.ccclubs.service.admin.ISrvLockService srvLockService = $.GetSpringBean("srvLockService");		
		return srvLockService.getSrvLockById(id);
	}
	
	
	/**
	 * 根据ID取事务锁的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(SrvLock.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		SrvLock srvLock = get(id);
		if(srvLock!=null){
			String strValue = srvLock.getSlProfile$();
			if(!"SlProfile".equals("SlProfile"))
				strValue+="("+srvLock.getSlProfile$()+")";
			MemCache.setValue(SrvLock.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getSlProfile$();
		if(!"SlProfile".equals("SlProfile"))
			keyValue+="("+this.getSlProfile$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有事务锁
	 * @return
	 */
	public static @api List<SrvLock> getSrvLockList(Map params,Integer size){
		com.ccclubs.service.admin.ISrvLockService srvLockService = $.GetSpringBean("srvLockService");
		return srvLockService.getSrvLockList(params, size);
	}
	
	/**
	 * 获取事务锁分页
	 * @return
	 */
	public static @api Page<SrvLock> getSrvLockPage(int page,int size,Map params){
		com.ccclubs.service.admin.ISrvLockService srvLockService = $.GetSpringBean("srvLockService");
		return srvLockService.getSrvLockPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取事务锁
	 * @param params
	 * @return
	 */
	public static @api SrvLock getSrvLock(Map params){
		com.ccclubs.service.admin.ISrvLockService srvLockService = $.GetSpringBean("srvLockService");
		return srvLockService.getSrvLock(params);
	}
	
	/**
	 * 获取事务锁数
	 * @return
	 */
	public static @api Long getSrvLockCount(Map params){
		com.ccclubs.service.admin.ISrvLockService srvLockService = $.GetSpringBean("srvLockService");
		return srvLockService.getSrvLockCount(params);
	}
		
		
	/**
	 * 获取事务锁自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getSrvLockEval(String eval,Map params){
		com.ccclubs.service.admin.ISrvLockService srvLockService = $.GetSpringBean("srvLockService");
		return srvLockService.getSrvLockEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateSrvLock(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ISrvLockService srvLockService = $.GetSpringBean("srvLockService");
		srvLockService.updateSrvLockByConfirm(set, where);
	}
	
	
	/**
	 * 保存事务锁对象
	 * @param params
	 * @return
	 */
	public SrvLock save(){
		com.ccclubs.service.admin.ISrvLockService srvLockService = $.GetSpringBean("srvLockService");
		if(this.getSlId()!=null)
			srvLockService.updateSrvLock(this);
		else
			return srvLockService.saveSrvLock(this);
		return this;
	}
	
	
	/**
	 * 更新事务锁对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ISrvLockService srvLockService = $.GetSpringBean("srvLockService");
		srvLockService.updateSrvLock$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ISrvLockService srvLockService = $.GetSpringBean("srvLockService");
			srvLockService.deleteSrvLockById(this.getSlId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ISrvLockService srvLockService = $.GetSpringBean("srvLockService");
		return srvLockService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getSlId(){
		return this.slId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getSlId$(){
		String strValue="";
		 strValue=$.str(this.getSlId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setSlId(Long slId){
		this.slId = slId;
		this.setSeted(F.slId);
	}
	/*******************************名称**********************************/	
	/**
	* 名称 [非空]      
	**/
	public String getSlProfile(){
		return this.slProfile;
	}
	/**
	* 获取名称格式化(toString)
	**/
	public String getSlProfile$(){
		String strValue="";
		 strValue=$.str(this.getSlProfile());
	 	 return strValue;
	}
	/**
	* 名称 [非空]      
	**/
	public void setSlProfile(String slProfile){
		this.slProfile = slProfile;
		this.setSeted(F.slProfile);
	}
	/*******************************类型**********************************/	
	/**
	* 类型 [非空]      
	**/
	public String getSlType(){
		return this.slType;
	}
	/**
	* 获取类型格式化(toString)
	**/
	public String getSlType$(){
		String strValue="";
		 strValue=$.str(this.getSlType());
	 	 return strValue;
	}
	/**
	* 类型 [非空]      
	**/
	public void setSlType(String slType){
		this.slType = slType;
		this.setSeted(F.slType);
	}
	/*******************************时间**********************************/	
	/**
	* 时间 [非空]      
	**/
	public Date getSlTime(){
		return this.slTime;
	}
	/**
	* 获取时间格式化(toString)
	**/
	public String getSlTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getSlTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 时间 [非空]      
	**/
	public void setSlTime(Date slTime){
		this.slTime = slTime;
		this.setSeted(F.slTime);
	}
	/************LAZY3Q_DEFINE_CODE************/
	public enum LockType{
		订单事务锁
	}
	public static void lock(LockType lockType,String profile){
		SrvLock srvLock = SrvLock.where().slType(lockType.name()).get();
		if(srvLock==null){
			srvLock = new SrvLock(
				profile//概述 [非空]
			 	,lockType.name()//类型 [非空]
			 	,new Date()//时间 [非空]
			 ).save();
		}
		srvLock.slTime(new Date()).slProfile(profile).update();
	}	
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${SrvLock.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvLock.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvLock.class.getMethod("get$"+$.forMat(key.toString())).invoke(SrvLock.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${SrvLock.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvLock.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvLock.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(SrvLock.this);
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
		public M slId(Object slId){this.put("slId", slId);return this;};
	 	/** and sl_id is null */
 		public M slIdNull(){if(this.get("slIdNot")==null)this.put("slIdNot", "");this.put("slId", null);return this;};
 		/** not .... */
 		public M slIdNot(){this.put("slIdNot", "not");return this;};
		/** 名称 [非空]       **/
		public M slProfile(Object slProfile){this.put("slProfile", slProfile);return this;};
	 	/** and sl_profile is null */
 		public M slProfileNull(){if(this.get("slProfileNot")==null)this.put("slProfileNot", "");this.put("slProfile", null);return this;};
 		/** not .... */
 		public M slProfileNot(){this.put("slProfileNot", "not");return this;};
		/** 类型 [非空]       **/
		public M slType(Object slType){this.put("slType", slType);return this;};
	 	/** and sl_type is null */
 		public M slTypeNull(){if(this.get("slTypeNot")==null)this.put("slTypeNot", "");this.put("slType", null);return this;};
 		/** not .... */
 		public M slTypeNot(){this.put("slTypeNot", "not");return this;};
		/** 时间 [非空]       **/
		public M slTime(Object slTime){this.put("slTime", slTime);return this;};
	 	/** and sl_time is null */
 		public M slTimeNull(){if(this.get("slTimeNot")==null)this.put("slTimeNot", "");this.put("slTime", null);return this;};
 		/** not .... */
 		public M slTimeNot(){this.put("slTimeNot", "not");return this;};
 		/** and sl_time >= ? */
 		public M slTimeStart(Object start){this.put("slTimeStart", start);return this;};			
 		/** and sl_time <= ? */
 		public M slTimeEnd(Object end){this.put("slTimeEnd", end);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有事务锁 **/
		public @api List<SrvLock> list(Integer size){
			return getSrvLockList(this,size);
		}
		/** 获取事务锁分页 **/
		public @api Page<SrvLock> page(int page,int size){
			return getSrvLockPage(page, size , this);
		}
		/** 根据查询条件取事务锁 **/
		public @api SrvLock get(){
			return getSrvLock(this);
		}
		/** 获取事务锁数 **/
		public @api Long count(){
			return getSrvLockCount(this);
		}
		/** 获取事务锁表达式 **/
		public @api <T> T eval(String strEval){
			return getSrvLockEval(strEval,this);
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
			updateSrvLock(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String slId="slId";
		/** 名称 [非空]       **/
		public final static @type(String.class) @like String slProfile="slProfile";
		/** 类型 [非空]       **/
		public final static @type(String.class) @like String slType="slType";
		/** 时间 [非空]       **/
		public final static @type(Date.class)  String slTime="slTime";
	 	/** and sl_time >= ? */
 		public final static @type(Date.class) String slTimeStart="slTimeStart";
 		/** and sl_time <= ? */
 		public final static @type(Date.class) String slTimeEnd="slTimeEnd";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String slId="sl_id";
		/** 名称 [非空]       **/
		public final static String slProfile="sl_profile";
		/** 类型 [非空]       **/
		public final static String slType="sl_type";
		/** 时间 [非空]       **/
		public final static String slTime="sl_time";
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
				$.Set(name,SrvLock.getSrvLock(params));
			else
				$.Set(name,SrvLock.getSrvLockList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取事务锁数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.SrvLock) $.GetRequest("SrvLock$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = SrvLock.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = SrvLock.getSrvLock(params);
			else
				value = SrvLock.getSrvLockList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = SrvLock.Get($.add(SrvLock.F.slId,param));
		else if(!$.empty(param.toString()))
			value = SrvLock.get(Long.valueOf(param.toString()));
		$.SetRequest("SrvLock$"+param.hashCode(), value);
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
	public void mergeSet(SrvLock old){
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