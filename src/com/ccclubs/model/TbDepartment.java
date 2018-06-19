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

@namespace("oa/hr/department")
public @caption("公司部门") @table("tb_department") class TbDepartment implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("tbd_id")   @primary  @note("  ") Long tbdId;// 主键 非空     
	private @caption("城市") @column("tbd_host")    @relate("$tbdHost") @RelateClass(SrvHost.class)  @note("  ") Long tbdHost;// 非空     
 	private SrvHost $tbdHost;//关联对象[运营城市]
	private @caption("部门名称") @column("tbd_name")    @note("  ") String tbdName;// 非空     
	private @caption("状态") @column("tbd_status")    @note(" 1:正常  ") Short tbdStatus;// 非空 1:正常     
	
	//默认构造函数
	public TbDepartment(){
	
	}
	
	//主键构造函数
	public TbDepartment(Long id){
		this.tbdId = id;
	}
	
	/**所有字段构造函数 TbDepartment(tbdHost,tbdName,tbdStatus)
	 TbDepartment(
	 	$.getLong("tbdHost")//城市 [非空]
	 	,$.getString("tbdName")//部门名称 [非空]
	 	,$.getShort("tbdStatus")//状态 [非空]
	 )
	**/
	public TbDepartment(Long tbdHost,String tbdName,Short tbdStatus){
		this.tbdHost=tbdHost;
		this.tbdName=tbdName;
		this.tbdStatus=tbdStatus;
	}
	
	//设置非空字段
	public TbDepartment setNotNull(Long tbdHost,String tbdName,Short tbdStatus){
		this.tbdHost=tbdHost;
		this.tbdName=tbdName;
		this.tbdStatus=tbdStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public TbDepartment tbdId(Long tbdId){
		this.tbdId = tbdId;
		this.setSeted(F.tbdId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public TbDepartment tbdHost(Long tbdHost){
		this.tbdHost = tbdHost;
		this.setSeted(F.tbdHost);
		return this;
	}
	/** 部门名称 [非空]       **/
	public TbDepartment tbdName(String tbdName){
		this.tbdName = tbdName;
		this.setSeted(F.tbdName);
		return this;
	}
	/** 状态 [非空]   1:正常     **/
	public TbDepartment tbdStatus(Short tbdStatus){
		this.tbdStatus = tbdStatus;
		this.setSeted(F.tbdStatus);
		return this;
	}
	
	
	//克隆对象
	public TbDepartment clone(){
		TbDepartment clone = new TbDepartment();
		clone.tbdHost=this.tbdHost;
		clone.tbdName=this.tbdName;
		clone.tbdStatus=this.tbdStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取公司部门
	 * @param id
	 * @return
	 */
	public static @api TbDepartment get(Long id){		
		return getTbDepartmentById(id);
	}
	/**
	 * 获取所有公司部门
	 * @return
	 */
	public static @api List<TbDepartment> list(Map params,Integer size){
		return getTbDepartmentList(params,size);
	}
	/**
	 * 获取公司部门分页
	 * @return
	 */
	public static @api Page<TbDepartment> page(int page,int size,Map params){
		return getTbDepartmentPage(page, size , params);
	}
	/**
	 * 根据查询条件取公司部门
	 * @param params
	 * @return
	 */
	public static @api TbDepartment Get(Map params){
		return getTbDepartment(params);
	}
	/**
	 * 获取公司部门数
	 * @return
	 */
	public static @api Long count(Map params){
		return getTbDepartmentCount(params);
	}
	/**
	 * 获取公司部门数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getTbDepartmentEval(eval,params);
	}
	
	/**
	 * 根据ID取公司部门
	 * @param id
	 * @return
	 */
	public static @api TbDepartment getTbDepartmentById(Long id){		
		TbDepartment tbDepartment = (TbDepartment) $.GetRequest("TbDepartment$"+id);
		if(tbDepartment!=null)
			return tbDepartment;
		com.ccclubs.service.admin.ITbDepartmentService tbDepartmentService = $.GetSpringBean("tbDepartmentService");		
		return tbDepartmentService.getTbDepartmentById(id);
	}
	
	
	/**
	 * 根据ID取公司部门的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(TbDepartment.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		TbDepartment tbDepartment = get(id);
		if(tbDepartment!=null){
			String strValue = tbDepartment.getTbdName$();
			if(!"TbdName".equals("TbdName"))
				strValue+="("+tbDepartment.getTbdName$()+")";
			MemCache.setValue(TbDepartment.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getTbdName$();
		if(!"TbdName".equals("TbdName"))
			keyValue+="("+this.getTbdName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有公司部门
	 * @return
	 */
	public static @api List<TbDepartment> getTbDepartmentList(Map params,Integer size){
		com.ccclubs.service.admin.ITbDepartmentService tbDepartmentService = $.GetSpringBean("tbDepartmentService");
		return tbDepartmentService.getTbDepartmentList(params, size);
	}
	
	/**
	 * 获取公司部门分页
	 * @return
	 */
	public static @api Page<TbDepartment> getTbDepartmentPage(int page,int size,Map params){
		com.ccclubs.service.admin.ITbDepartmentService tbDepartmentService = $.GetSpringBean("tbDepartmentService");
		return tbDepartmentService.getTbDepartmentPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取公司部门
	 * @param params
	 * @return
	 */
	public static @api TbDepartment getTbDepartment(Map params){
		com.ccclubs.service.admin.ITbDepartmentService tbDepartmentService = $.GetSpringBean("tbDepartmentService");
		return tbDepartmentService.getTbDepartment(params);
	}
	
	/**
	 * 获取公司部门数
	 * @return
	 */
	public static @api Long getTbDepartmentCount(Map params){
		com.ccclubs.service.admin.ITbDepartmentService tbDepartmentService = $.GetSpringBean("tbDepartmentService");
		return tbDepartmentService.getTbDepartmentCount(params);
	}
		
		
	/**
	 * 获取公司部门自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getTbDepartmentEval(String eval,Map params){
		com.ccclubs.service.admin.ITbDepartmentService tbDepartmentService = $.GetSpringBean("tbDepartmentService");
		return tbDepartmentService.getTbDepartmentEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateTbDepartment(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ITbDepartmentService tbDepartmentService = $.GetSpringBean("tbDepartmentService");
		tbDepartmentService.updateTbDepartmentByConfirm(set, where);
	}
	
	
	/**
	 * 保存公司部门对象
	 * @param params
	 * @return
	 */
	public TbDepartment save(){
		com.ccclubs.service.admin.ITbDepartmentService tbDepartmentService = $.GetSpringBean("tbDepartmentService");
		if(this.getTbdId()!=null)
			tbDepartmentService.updateTbDepartment(this);
		else
			return tbDepartmentService.saveTbDepartment(this);
		return this;
	}
	
	
	/**
	 * 更新公司部门对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ITbDepartmentService tbDepartmentService = $.GetSpringBean("tbDepartmentService");
		tbDepartmentService.updateTbDepartment$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ITbDepartmentService tbDepartmentService = $.GetSpringBean("tbDepartmentService");
		if($.equals($.config("logic_delete"),"true"))
			tbDepartmentService.removeTbDepartmentById(this.getTbdId());
		else
			tbDepartmentService.deleteTbDepartmentById(this.getTbdId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ITbDepartmentService tbDepartmentService = $.GetSpringBean("tbDepartmentService");
		return tbDepartmentService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getTbdId(){
		return this.tbdId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getTbdId$(){
		String strValue="";
		 strValue=$.str(this.getTbdId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setTbdId(Long tbdId){
		this.tbdId = tbdId;
		this.setSeted(F.tbdId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getTbdHost(){
		return this.tbdHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getTbdHost$(){
		String strValue="";
		if(this.getTbdHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getTbdHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setTbdHost(Long tbdHost){
		this.tbdHost = tbdHost;
		this.setSeted(F.tbdHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$tbdHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getTbdHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getTbdHost()!=null){
 			srvHost = SrvHost.get(this.getTbdHost());
 		}
 		$.SetRequest("SrvHost$"+this.getTbdHost(), srvHost);
	 	return srvHost;
	}
	/*******************************部门名称**********************************/	
	/**
	* 部门名称 [非空]      
	**/
	public String getTbdName(){
		return this.tbdName;
	}
	/**
	* 获取部门名称格式化(toString)
	**/
	public String getTbdName$(){
		String strValue="";
		 strValue=$.str(this.getTbdName());
	 	 return strValue;
	}
	/**
	* 部门名称 [非空]      
	**/
	public void setTbdName(String tbdName){
		this.tbdName = tbdName;
		this.setSeted(F.tbdName);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常    
	**/
	public Short getTbdStatus(){
		return this.tbdStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getTbdStatus$(){
		String strValue="";
		 if($.equals($.str(this.getTbdStatus()),"1"))
			strValue=$.str("正常");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常    
	**/
	public void setTbdStatus(Short tbdStatus){
		this.tbdStatus = tbdStatus;
		this.setSeted(F.tbdStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${TbDepartment.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbDepartment.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbDepartment.class.getMethod("get$"+$.forMat(key.toString())).invoke(TbDepartment.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${TbDepartment.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${TbDepartment.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return TbDepartment.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(TbDepartment.this);
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
		public M tbdId(Object tbdId){this.put("tbdId", tbdId);return this;};
	 	/** and tbd_id is null */
 		public M tbdIdNull(){if(this.get("tbdIdNot")==null)this.put("tbdIdNot", "");this.put("tbdId", null);return this;};
 		/** not .... */
 		public M tbdIdNot(){this.put("tbdIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M tbdHost(Object tbdHost){this.put("tbdHost", tbdHost);return this;};
	 	/** and tbd_host is null */
 		public M tbdHostNull(){if(this.get("tbdHostNot")==null)this.put("tbdHostNot", "");this.put("tbdHost", null);return this;};
 		/** not .... */
 		public M tbdHostNot(){this.put("tbdHostNot", "not");return this;};
		/** 部门名称 [非空]       **/
		public M tbdName(Object tbdName){this.put("tbdName", tbdName);return this;};
	 	/** and tbd_name is null */
 		public M tbdNameNull(){if(this.get("tbdNameNot")==null)this.put("tbdNameNot", "");this.put("tbdName", null);return this;};
 		/** not .... */
 		public M tbdNameNot(){this.put("tbdNameNot", "not");return this;};
		/** 状态 [非空]   1:正常     **/
		public M tbdStatus(Object tbdStatus){this.put("tbdStatus", tbdStatus);return this;};
	 	/** and tbd_status is null */
 		public M tbdStatusNull(){if(this.get("tbdStatusNot")==null)this.put("tbdStatusNot", "");this.put("tbdStatus", null);return this;};
 		/** not .... */
 		public M tbdStatusNot(){this.put("tbdStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有公司部门 **/
		public @api List<TbDepartment> list(Integer size){
			return getTbDepartmentList(this,size);
		}
		/** 获取公司部门分页 **/
		public @api Page<TbDepartment> page(int page,int size){
			return getTbDepartmentPage(page, size , this);
		}
		/** 根据查询条件取公司部门 **/
		public @api TbDepartment get(){
			return getTbDepartment(this);
		}
		/** 获取公司部门数 **/
		public @api Long count(){
			return getTbDepartmentCount(this);
		}
		/** 获取公司部门表达式 **/
		public @api <T> T eval(String strEval){
			return getTbDepartmentEval(strEval,this);
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
			updateTbDepartment(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String tbdId="tbdId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String tbdHost="tbdHost";
		/** 部门名称 [非空]       **/
		public final static @type(String.class) @like String tbdName="tbdName";
		/** 状态 [非空]   1:正常     **/
		public final static @type(Short.class)  String tbdStatus="tbdStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String tbdId="tbd_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String tbdHost="tbd_host";
		/** 部门名称 [非空]       **/
		public final static String tbdName="tbd_name";
		/** 状态 [非空]   1:正常     **/
		public final static String tbdStatus="tbd_status";
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
				$.Set(name,TbDepartment.getTbDepartment(params));
			else
				$.Set(name,TbDepartment.getTbDepartmentList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取公司部门数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.TbDepartment) $.GetRequest("TbDepartment$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = TbDepartment.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = TbDepartment.getTbDepartment(params);
			else
				value = TbDepartment.getTbDepartmentList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = TbDepartment.Get($.add(TbDepartment.F.tbdId,param));
		else if(!$.empty(param.toString()))
			value = TbDepartment.get(Long.valueOf(param.toString()));
		$.SetRequest("TbDepartment$"+param.hashCode(), value);
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
	public void mergeSet(TbDepartment old){
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