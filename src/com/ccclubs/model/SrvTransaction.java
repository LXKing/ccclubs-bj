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

@namespace("systematic/transaction")
public @caption("数据事务") @table("srv_transaction") class SrvTransaction implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("st_id")   @primary  @note("  ") Long stId;// 主键 非空     
	private @caption("调用入口") @column("st_entry")    @note("  ") String stEntry;// 非空     
	private @caption("调用详情") @column("st_details")    @note("  ") String stDetails;// 非空     
	private @caption("修改模块") @column("st_models")    @note("  ") String stModels;// 非空     
	
	//默认构造函数
	public SrvTransaction(){
	
	}
	
	//主键构造函数
	public SrvTransaction(Long id){
		this.stId = id;
	}
	
	/**所有字段构造函数 SrvTransaction(stEntry,stDetails,stModels)
	 SrvTransaction(
	 	$.getString("stEntry")//调用入口 [非空]
	 	,$.getString("stDetails")//调用详情 [非空]
	 	,$.getString("stModels")//修改模块 [非空]
	 )
	**/
	public SrvTransaction(String stEntry,String stDetails,String stModels){
		this.stEntry=stEntry;
		this.stDetails=stDetails;
		this.stModels=stModels;
	}
	
	//设置非空字段
	public SrvTransaction setNotNull(String stEntry,String stDetails,String stModels){
		this.stEntry=stEntry;
		this.stDetails=stDetails;
		this.stModels=stModels;
		return this;
	}
	/** 编号 [非空]       **/
	public SrvTransaction stId(Long stId){
		this.stId = stId;
		this.setSeted(F.stId);
		return this;
	}
	/** 调用入口 [非空]       **/
	public SrvTransaction stEntry(String stEntry){
		this.stEntry = stEntry;
		this.setSeted(F.stEntry);
		return this;
	}
	/** 调用详情 [非空]       **/
	public SrvTransaction stDetails(String stDetails){
		this.stDetails = stDetails;
		this.setSeted(F.stDetails);
		return this;
	}
	/** 修改模块 [非空]       **/
	public SrvTransaction stModels(String stModels){
		this.stModels = stModels;
		this.setSeted(F.stModels);
		return this;
	}
	
	
	//克隆对象
	public SrvTransaction clone(){
		SrvTransaction clone = new SrvTransaction();
		clone.stEntry=this.stEntry;
		clone.stDetails=this.stDetails;
		clone.stModels=this.stModels;
		return clone;
	}
	
	
	/**
	 * 根据ID取数据事务
	 * @param id
	 * @return
	 */
	public static @api SrvTransaction get(Long id){		
		return getSrvTransactionById(id);
	}
	/**
	 * 获取所有数据事务
	 * @return
	 */
	public static @api List<SrvTransaction> list(Map params,Integer size){
		return getSrvTransactionList(params,size);
	}
	/**
	 * 获取数据事务分页
	 * @return
	 */
	public static @api Page<SrvTransaction> page(int page,int size,Map params){
		return getSrvTransactionPage(page, size , params);
	}
	/**
	 * 根据查询条件取数据事务
	 * @param params
	 * @return
	 */
	public static @api SrvTransaction Get(Map params){
		return getSrvTransaction(params);
	}
	/**
	 * 获取数据事务数
	 * @return
	 */
	public static @api Long count(Map params){
		return getSrvTransactionCount(params);
	}
	/**
	 * 获取数据事务数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getSrvTransactionEval(eval,params);
	}
	
	/**
	 * 根据ID取数据事务
	 * @param id
	 * @return
	 */
	public static @api SrvTransaction getSrvTransactionById(Long id){		
		SrvTransaction srvTransaction = (SrvTransaction) $.GetRequest("SrvTransaction$"+id);
		if(srvTransaction!=null)
			return srvTransaction;
		com.ccclubs.service.admin.ISrvTransactionService srvTransactionService = $.GetSpringBean("srvTransactionService");		
		return srvTransactionService.getSrvTransactionById(id);
	}
	
	
	/**
	 * 根据ID取数据事务的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(SrvTransaction.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		SrvTransaction srvTransaction = get(id);
		if(srvTransaction!=null){
			String strValue = srvTransaction.getStEntry$();
			if(!"StEntry".equals("StEntry"))
				strValue+="("+srvTransaction.getStEntry$()+")";
			MemCache.setValue(SrvTransaction.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getStEntry$();
		if(!"StEntry".equals("StEntry"))
			keyValue+="("+this.getStEntry$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有数据事务
	 * @return
	 */
	public static @api List<SrvTransaction> getSrvTransactionList(Map params,Integer size){
		com.ccclubs.service.admin.ISrvTransactionService srvTransactionService = $.GetSpringBean("srvTransactionService");
		return srvTransactionService.getSrvTransactionList(params, size);
	}
	
	/**
	 * 获取数据事务分页
	 * @return
	 */
	public static @api Page<SrvTransaction> getSrvTransactionPage(int page,int size,Map params){
		com.ccclubs.service.admin.ISrvTransactionService srvTransactionService = $.GetSpringBean("srvTransactionService");
		return srvTransactionService.getSrvTransactionPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取数据事务
	 * @param params
	 * @return
	 */
	public static @api SrvTransaction getSrvTransaction(Map params){
		com.ccclubs.service.admin.ISrvTransactionService srvTransactionService = $.GetSpringBean("srvTransactionService");
		return srvTransactionService.getSrvTransaction(params);
	}
	
	/**
	 * 获取数据事务数
	 * @return
	 */
	public static @api Long getSrvTransactionCount(Map params){
		com.ccclubs.service.admin.ISrvTransactionService srvTransactionService = $.GetSpringBean("srvTransactionService");
		return srvTransactionService.getSrvTransactionCount(params);
	}
		
		
	/**
	 * 获取数据事务自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getSrvTransactionEval(String eval,Map params){
		com.ccclubs.service.admin.ISrvTransactionService srvTransactionService = $.GetSpringBean("srvTransactionService");
		return srvTransactionService.getSrvTransactionEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateSrvTransaction(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ISrvTransactionService srvTransactionService = $.GetSpringBean("srvTransactionService");
		srvTransactionService.updateSrvTransactionByConfirm(set, where);
	}
	
	
	/**
	 * 保存数据事务对象
	 * @param params
	 * @return
	 */
	public SrvTransaction save(){
		com.ccclubs.service.admin.ISrvTransactionService srvTransactionService = $.GetSpringBean("srvTransactionService");
		if(this.getStId()!=null)
			srvTransactionService.updateSrvTransaction(this);
		else
			return srvTransactionService.saveSrvTransaction(this);
		return this;
	}
	
	
	/**
	 * 更新数据事务对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ISrvTransactionService srvTransactionService = $.GetSpringBean("srvTransactionService");
		srvTransactionService.updateSrvTransaction$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ISrvTransactionService srvTransactionService = $.GetSpringBean("srvTransactionService");
			srvTransactionService.deleteSrvTransactionById(this.getStId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ISrvTransactionService srvTransactionService = $.GetSpringBean("srvTransactionService");
		return srvTransactionService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getStId(){
		return this.stId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getStId$(){
		String strValue="";
		 strValue=$.str(this.getStId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setStId(Long stId){
		this.stId = stId;
		this.setSeted(F.stId);
	}
	/*******************************调用入口**********************************/	
	/**
	* 调用入口 [非空]      
	**/
	public String getStEntry(){
		return this.stEntry;
	}
	/**
	* 获取调用入口格式化(toString)
	**/
	public String getStEntry$(){
		String strValue="";
		 strValue=$.str(this.getStEntry());
	 	 return strValue;
	}
	/**
	* 调用入口 [非空]      
	**/
	public void setStEntry(String stEntry){
		this.stEntry = stEntry;
		this.setSeted(F.stEntry);
	}
	/*******************************调用详情**********************************/	
	/**
	* 调用详情 [非空]      
	**/
	public String getStDetails(){
		return this.stDetails;
	}
	/**
	* 获取调用详情格式化(toString)
	**/
	public String getStDetails$(){
		String strValue="";
		 strValue=$.str(this.getStDetails());
	 	 return strValue;
	}
	/**
	* 调用详情 [非空]      
	**/
	public void setStDetails(String stDetails){
		this.stDetails = stDetails;
		this.setSeted(F.stDetails);
	}
	/*******************************修改模块**********************************/	
	/**
	* 修改模块 [非空]      
	**/
	public String getStModels(){
		return this.stModels;
	}
	/**
	* 获取修改模块格式化(toString)
	**/
	public String getStModels$(){
		String strValue="";
		 strValue=$.str(this.getStModels());
	 	 return strValue;
	}
	/**
	* 修改模块 [非空]      
	**/
	public void setStModels(String stModels){
		this.stModels = stModels;
		this.setSeted(F.stModels);
	}
	/************LAZY3Q_DEFINE_CODE************/
	
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${SrvTransaction.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvTransaction.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvTransaction.class.getMethod("get$"+$.forMat(key.toString())).invoke(SrvTransaction.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${SrvTransaction.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvTransaction.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvTransaction.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(SrvTransaction.this);
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
		public M stId(Object stId){this.put("stId", stId);return this;};
	 	/** and st_id is null */
 		public M stIdNull(){if(this.get("stIdNot")==null)this.put("stIdNot", "");this.put("stId", null);return this;};
 		/** not .... */
 		public M stIdNot(){this.put("stIdNot", "not");return this;};
		/** 调用入口 [非空]       **/
		public M stEntry(Object stEntry){this.put("stEntry", stEntry);return this;};
	 	/** and st_entry is null */
 		public M stEntryNull(){if(this.get("stEntryNot")==null)this.put("stEntryNot", "");this.put("stEntry", null);return this;};
 		/** not .... */
 		public M stEntryNot(){this.put("stEntryNot", "not");return this;};
		/** 调用详情 [非空]       **/
		public M stDetails(Object stDetails){this.put("stDetails", stDetails);return this;};
	 	/** and st_details is null */
 		public M stDetailsNull(){if(this.get("stDetailsNot")==null)this.put("stDetailsNot", "");this.put("stDetails", null);return this;};
 		/** not .... */
 		public M stDetailsNot(){this.put("stDetailsNot", "not");return this;};
		/** 修改模块 [非空]       **/
		public M stModels(Object stModels){this.put("stModels", stModels);return this;};
	 	/** and st_models is null */
 		public M stModelsNull(){if(this.get("stModelsNot")==null)this.put("stModelsNot", "");this.put("stModels", null);return this;};
 		/** not .... */
 		public M stModelsNot(){this.put("stModelsNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有数据事务 **/
		public @api List<SrvTransaction> list(Integer size){
			return getSrvTransactionList(this,size);
		}
		/** 获取数据事务分页 **/
		public @api Page<SrvTransaction> page(int page,int size){
			return getSrvTransactionPage(page, size , this);
		}
		/** 根据查询条件取数据事务 **/
		public @api SrvTransaction get(){
			return getSrvTransaction(this);
		}
		/** 获取数据事务数 **/
		public @api Long count(){
			return getSrvTransactionCount(this);
		}
		/** 获取数据事务表达式 **/
		public @api <T> T eval(String strEval){
			return getSrvTransactionEval(strEval,this);
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
			updateSrvTransaction(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String stId="stId";
		/** 调用入口 [非空]       **/
		public final static @type(String.class) @like String stEntry="stEntry";
		/** 调用详情 [非空]       **/
		public final static @type(String.class) @like String stDetails="stDetails";
		/** 修改模块 [非空]       **/
		public final static @type(String.class) @like String stModels="stModels";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String stId="st_id";
		/** 调用入口 [非空]       **/
		public final static String stEntry="st_entry";
		/** 调用详情 [非空]       **/
		public final static String stDetails="st_details";
		/** 修改模块 [非空]       **/
		public final static String stModels="st_models";
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
				$.Set(name,SrvTransaction.getSrvTransaction(params));
			else
				$.Set(name,SrvTransaction.getSrvTransactionList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取数据事务数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.SrvTransaction) $.GetRequest("SrvTransaction$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = SrvTransaction.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = SrvTransaction.getSrvTransaction(params);
			else
				value = SrvTransaction.getSrvTransactionList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = SrvTransaction.Get($.add(SrvTransaction.F.stId,param));
		else if(!$.empty(param.toString()))
			value = SrvTransaction.get(Long.valueOf(param.toString()));
		$.SetRequest("SrvTransaction$"+param.hashCode(), value);
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
	public void mergeSet(SrvTransaction old){
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