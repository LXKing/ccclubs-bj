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

@namespace("configurator/script")
public @caption("后台事务脚本") @table("srv_action_script") class SrvActionScript implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("ss_id")   @primary  @note("  ") Long ssId;// 主键 非空     
	private @caption("脚本名称") @column("ss_name")    @note("  ") String ssName;// 非空     
	private @caption("类名全称") @column("ss_model_class")    @note("  ") String ssModelClass;// 非空     
	private @caption("事件名称") @column("ss_event")    @note("  ") String ssEvent;// 非空     
	private @caption("脚本代码") @column("ss_code")  @hidden   @note("  脚本代码为java代码") String ssCode;//  脚本代码为java代码   
	private @caption("修改时间") @column("ss_update_time")    @note("  ") Date ssUpdateTime;// 非空     
	private @caption("添加时间") @column("ss_add_time")    @note("  ") Date ssAddTime;// 非空     
	private @caption("状态") @column("ss_status")    @note(" 1:正常 0:无效  ") Short ssStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public SrvActionScript(){
	
	}
	
	//主键构造函数
	public SrvActionScript(Long id){
		this.ssId = id;
	}
	
	/**所有字段构造函数 SrvActionScript(ssName,ssModelClass,ssEvent,ssCode,ssUpdateTime,ssAddTime,ssStatus)
	 SrvActionScript(
	 	$.getString("ssName")//脚本名称 [非空]
	 	,$.getString("ssModelClass")//类名全称 [非空]
	 	,$.getString("ssEvent")//事件名称 [非空]
	 	,$.getString("ssCode")//脚本代码
	 	,$.getDate("ssUpdateTime")//修改时间 [非空]
	 	,$.getDate("ssAddTime")//添加时间 [非空]
	 	,$.getShort("ssStatus")//状态 [非空]
	 )
	**/
	public SrvActionScript(String ssName,String ssModelClass,String ssEvent,String ssCode,Date ssUpdateTime,Date ssAddTime,Short ssStatus){
		this.ssName=ssName;
		this.ssModelClass=ssModelClass;
		this.ssEvent=ssEvent;
		this.ssCode=ssCode;
		this.ssUpdateTime=ssUpdateTime;
		this.ssAddTime=ssAddTime;
		this.ssStatus=ssStatus;
	}
	
	//设置非空字段
	public SrvActionScript setNotNull(String ssName,String ssModelClass,String ssEvent,Date ssUpdateTime,Date ssAddTime,Short ssStatus){
		this.ssName=ssName;
		this.ssModelClass=ssModelClass;
		this.ssEvent=ssEvent;
		this.ssUpdateTime=ssUpdateTime;
		this.ssAddTime=ssAddTime;
		this.ssStatus=ssStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public SrvActionScript ssId(Long ssId){
		this.ssId = ssId;
		this.setSeted(F.ssId);
		return this;
	}
	/** 脚本名称 [非空]       **/
	public SrvActionScript ssName(String ssName){
		this.ssName = ssName;
		this.setSeted(F.ssName);
		return this;
	}
	/** 类名全称 [非空]       **/
	public SrvActionScript ssModelClass(String ssModelClass){
		this.ssModelClass = ssModelClass;
		this.setSeted(F.ssModelClass);
		return this;
	}
	/** 事件名称 [非空]       **/
	public SrvActionScript ssEvent(String ssEvent){
		this.ssEvent = ssEvent;
		this.setSeted(F.ssEvent);
		return this;
	}
	/** 脚本代码     脚本代码为java代码   **/
	public SrvActionScript ssCode(String ssCode){
		this.ssCode = ssCode;
		this.setSeted(F.ssCode);
		return this;
	}
	/** 修改时间 [非空]       **/
	public SrvActionScript ssUpdateTime(Date ssUpdateTime){
		this.ssUpdateTime = ssUpdateTime;
		this.setSeted(F.ssUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public SrvActionScript ssAddTime(Date ssAddTime){
		this.ssAddTime = ssAddTime;
		this.setSeted(F.ssAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public SrvActionScript ssStatus(Short ssStatus){
		this.ssStatus = ssStatus;
		this.setSeted(F.ssStatus);
		return this;
	}
	
	
	//克隆对象
	public SrvActionScript clone(){
		SrvActionScript clone = new SrvActionScript();
		clone.ssName=this.ssName;
		clone.ssModelClass=this.ssModelClass;
		clone.ssEvent=this.ssEvent;
		clone.ssUpdateTime=this.ssUpdateTime;
		clone.ssAddTime=this.ssAddTime;
		clone.ssStatus=this.ssStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取后台事务脚本
	 * @param id
	 * @return
	 */
	public static @api SrvActionScript get(Long id){		
		return getSrvActionScriptById(id);
	}
	/**
	 * 获取所有后台事务脚本
	 * @return
	 */
	public static @api List<SrvActionScript> list(Map params,Integer size){
		return getSrvActionScriptList(params,size);
	}
	/**
	 * 获取后台事务脚本分页
	 * @return
	 */
	public static @api Page<SrvActionScript> page(int page,int size,Map params){
		return getSrvActionScriptPage(page, size , params);
	}
	/**
	 * 根据查询条件取后台事务脚本
	 * @param params
	 * @return
	 */
	public static @api SrvActionScript Get(Map params){
		return getSrvActionScript(params);
	}
	/**
	 * 获取后台事务脚本数
	 * @return
	 */
	public static @api Long count(Map params){
		return getSrvActionScriptCount(params);
	}
	/**
	 * 获取后台事务脚本数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getSrvActionScriptEval(eval,params);
	}
	
	/**
	 * 根据ID取后台事务脚本
	 * @param id
	 * @return
	 */
	public static @api SrvActionScript getSrvActionScriptById(Long id){		
		SrvActionScript srvActionScript = (SrvActionScript) $.GetRequest("SrvActionScript$"+id);
		if(srvActionScript!=null)
			return srvActionScript;
		com.ccclubs.service.admin.ISrvActionScriptService srvActionScriptService = $.GetSpringBean("srvActionScriptService");		
		return srvActionScriptService.getSrvActionScriptById(id);
	}
	
	
	/**
	 * 根据ID取后台事务脚本的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(SrvActionScript.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		SrvActionScript srvActionScript = get(id);
		if(srvActionScript!=null){
			String strValue = srvActionScript.getSsName$();
			if(!"SsName".equals("SsName"))
				strValue+="("+srvActionScript.getSsName$()+")";
			MemCache.setValue(SrvActionScript.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getSsName$();
		if(!"SsName".equals("SsName"))
			keyValue+="("+this.getSsName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有后台事务脚本
	 * @return
	 */
	public static @api List<SrvActionScript> getSrvActionScriptList(Map params,Integer size){
		com.ccclubs.service.admin.ISrvActionScriptService srvActionScriptService = $.GetSpringBean("srvActionScriptService");
		return srvActionScriptService.getSrvActionScriptList(params, size);
	}
	
	/**
	 * 获取后台事务脚本分页
	 * @return
	 */
	public static @api Page<SrvActionScript> getSrvActionScriptPage(int page,int size,Map params){
		com.ccclubs.service.admin.ISrvActionScriptService srvActionScriptService = $.GetSpringBean("srvActionScriptService");
		return srvActionScriptService.getSrvActionScriptPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取后台事务脚本
	 * @param params
	 * @return
	 */
	public static @api SrvActionScript getSrvActionScript(Map params){
		com.ccclubs.service.admin.ISrvActionScriptService srvActionScriptService = $.GetSpringBean("srvActionScriptService");
		return srvActionScriptService.getSrvActionScript(params);
	}
	
	/**
	 * 获取后台事务脚本数
	 * @return
	 */
	public static @api Long getSrvActionScriptCount(Map params){
		com.ccclubs.service.admin.ISrvActionScriptService srvActionScriptService = $.GetSpringBean("srvActionScriptService");
		return srvActionScriptService.getSrvActionScriptCount(params);
	}
		
		
	/**
	 * 获取后台事务脚本自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getSrvActionScriptEval(String eval,Map params){
		com.ccclubs.service.admin.ISrvActionScriptService srvActionScriptService = $.GetSpringBean("srvActionScriptService");
		return srvActionScriptService.getSrvActionScriptEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateSrvActionScript(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ISrvActionScriptService srvActionScriptService = $.GetSpringBean("srvActionScriptService");
		srvActionScriptService.updateSrvActionScriptByConfirm(set, where);
	}
	
	
	/**
	 * 保存后台事务脚本对象
	 * @param params
	 * @return
	 */
	public SrvActionScript save(){
		com.ccclubs.service.admin.ISrvActionScriptService srvActionScriptService = $.GetSpringBean("srvActionScriptService");
		if(this.getSsId()!=null)
			srvActionScriptService.updateSrvActionScript(this);
		else
			return srvActionScriptService.saveSrvActionScript(this);
		return this;
	}
	
	
	/**
	 * 更新后台事务脚本对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ISrvActionScriptService srvActionScriptService = $.GetSpringBean("srvActionScriptService");
		srvActionScriptService.updateSrvActionScript$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ISrvActionScriptService srvActionScriptService = $.GetSpringBean("srvActionScriptService");
		if($.equals($.config("logic_delete"),"true"))
			srvActionScriptService.removeSrvActionScriptById(this.getSsId());
		else
			srvActionScriptService.deleteSrvActionScriptById(this.getSsId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ISrvActionScriptService srvActionScriptService = $.GetSpringBean("srvActionScriptService");
		return srvActionScriptService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getSsId(){
		return this.ssId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getSsId$(){
		String strValue="";
		 strValue=$.str(this.getSsId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setSsId(Long ssId){
		this.ssId = ssId;
		this.setSeted(F.ssId);
	}
	/*******************************脚本名称**********************************/	
	/**
	* 脚本名称 [非空]      
	**/
	public String getSsName(){
		return this.ssName;
	}
	/**
	* 获取脚本名称格式化(toString)
	**/
	public String getSsName$(){
		String strValue="";
		 strValue=$.str(this.getSsName());
	 	 return strValue;
	}
	/**
	* 脚本名称 [非空]      
	**/
	public void setSsName(String ssName){
		this.ssName = ssName;
		this.setSeted(F.ssName);
	}
	/*******************************类名全称**********************************/	
	/**
	* 类名全称 [非空]      
	**/
	public String getSsModelClass(){
		return this.ssModelClass;
	}
	/**
	* 获取类名全称格式化(toString)
	**/
	public String getSsModelClass$(){
		String strValue="";
		 strValue=$.str(this.getSsModelClass());
	 	 return strValue;
	}
	/**
	* 类名全称 [非空]      
	**/
	public void setSsModelClass(String ssModelClass){
		this.ssModelClass = ssModelClass;
		this.setSeted(F.ssModelClass);
	}
	/*******************************事件名称**********************************/	
	/**
	* 事件名称 [非空]      
	**/
	public String getSsEvent(){
		return this.ssEvent;
	}
	/**
	* 获取事件名称格式化(toString)
	**/
	public String getSsEvent$(){
		String strValue="";
		 strValue=$.str(this.getSsEvent());
	 	 return strValue;
	}
	/**
	* 事件名称 [非空]      
	**/
	public void setSsEvent(String ssEvent){
		this.ssEvent = ssEvent;
		this.setSeted(F.ssEvent);
	}
	/*******************************脚本代码**********************************/	
	/**
	* 脚本代码     脚本代码为java代码  
	**/
	public String getSsCode(){
		return this.ssCode;
	}
	/**
	* 获取脚本代码格式化(toString)
	**/
	public String getSsCode$(){
		String strValue="";
		 strValue=$.str(this.getSsCode());
	 	 return strValue;
	}
	/**
	* 脚本代码     脚本代码为java代码  
	**/
	public void setSsCode(String ssCode){
		this.ssCode = ssCode;
		this.setSeted(F.ssCode);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getSsUpdateTime(){
		return this.ssUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getSsUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getSsUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setSsUpdateTime(Date ssUpdateTime){
		this.ssUpdateTime = ssUpdateTime;
		this.setSeted(F.ssUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getSsAddTime(){
		return this.ssAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getSsAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getSsAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setSsAddTime(Date ssAddTime){
		this.ssAddTime = ssAddTime;
		this.setSeted(F.ssAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getSsStatus(){
		return this.ssStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getSsStatus$(){
		String strValue="";
		 if($.equals($.str(this.getSsStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getSsStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setSsStatus(Short ssStatus){
		this.ssStatus = ssStatus;
		this.setSeted(F.ssStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${SrvActionScript.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvActionScript.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvActionScript.class.getMethod("get$"+$.forMat(key.toString())).invoke(SrvActionScript.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${SrvActionScript.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvActionScript.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvActionScript.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(SrvActionScript.this);
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
		public M ssId(Object ssId){this.put("ssId", ssId);return this;};
	 	/** and ss_id is null */
 		public M ssIdNull(){if(this.get("ssIdNot")==null)this.put("ssIdNot", "");this.put("ssId", null);return this;};
 		/** not .... */
 		public M ssIdNot(){this.put("ssIdNot", "not");return this;};
		/** 脚本名称 [非空]       **/
		public M ssName(Object ssName){this.put("ssName", ssName);return this;};
	 	/** and ss_name is null */
 		public M ssNameNull(){if(this.get("ssNameNot")==null)this.put("ssNameNot", "");this.put("ssName", null);return this;};
 		/** not .... */
 		public M ssNameNot(){this.put("ssNameNot", "not");return this;};
		/** 类名全称 [非空]       **/
		public M ssModelClass(Object ssModelClass){this.put("ssModelClass", ssModelClass);return this;};
	 	/** and ss_model_class is null */
 		public M ssModelClassNull(){if(this.get("ssModelClassNot")==null)this.put("ssModelClassNot", "");this.put("ssModelClass", null);return this;};
 		/** not .... */
 		public M ssModelClassNot(){this.put("ssModelClassNot", "not");return this;};
		/** 事件名称 [非空]       **/
		public M ssEvent(Object ssEvent){this.put("ssEvent", ssEvent);return this;};
	 	/** and ss_event is null */
 		public M ssEventNull(){if(this.get("ssEventNot")==null)this.put("ssEventNot", "");this.put("ssEvent", null);return this;};
 		/** not .... */
 		public M ssEventNot(){this.put("ssEventNot", "not");return this;};
		/** 脚本代码     脚本代码为java代码   **/
		public M ssCode(Object ssCode){this.put("ssCode", ssCode);return this;};
	 	/** and ss_code is null */
 		public M ssCodeNull(){if(this.get("ssCodeNot")==null)this.put("ssCodeNot", "");this.put("ssCode", null);return this;};
 		/** not .... */
 		public M ssCodeNot(){this.put("ssCodeNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M ssUpdateTime(Object ssUpdateTime){this.put("ssUpdateTime", ssUpdateTime);return this;};
	 	/** and ss_update_time is null */
 		public M ssUpdateTimeNull(){if(this.get("ssUpdateTimeNot")==null)this.put("ssUpdateTimeNot", "");this.put("ssUpdateTime", null);return this;};
 		/** not .... */
 		public M ssUpdateTimeNot(){this.put("ssUpdateTimeNot", "not");return this;};
 		/** and ss_update_time >= ? */
 		public M ssUpdateTimeStart(Object start){this.put("ssUpdateTimeStart", start);return this;};			
 		/** and ss_update_time <= ? */
 		public M ssUpdateTimeEnd(Object end){this.put("ssUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M ssAddTime(Object ssAddTime){this.put("ssAddTime", ssAddTime);return this;};
	 	/** and ss_add_time is null */
 		public M ssAddTimeNull(){if(this.get("ssAddTimeNot")==null)this.put("ssAddTimeNot", "");this.put("ssAddTime", null);return this;};
 		/** not .... */
 		public M ssAddTimeNot(){this.put("ssAddTimeNot", "not");return this;};
 		/** and ss_add_time >= ? */
 		public M ssAddTimeStart(Object start){this.put("ssAddTimeStart", start);return this;};			
 		/** and ss_add_time <= ? */
 		public M ssAddTimeEnd(Object end){this.put("ssAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M ssStatus(Object ssStatus){this.put("ssStatus", ssStatus);return this;};
	 	/** and ss_status is null */
 		public M ssStatusNull(){if(this.get("ssStatusNot")==null)this.put("ssStatusNot", "");this.put("ssStatus", null);return this;};
 		/** not .... */
 		public M ssStatusNot(){this.put("ssStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有后台事务脚本 **/
		public @api List<SrvActionScript> list(Integer size){
			return getSrvActionScriptList(this,size);
		}
		/** 获取后台事务脚本分页 **/
		public @api Page<SrvActionScript> page(int page,int size){
			return getSrvActionScriptPage(page, size , this);
		}
		/** 根据查询条件取后台事务脚本 **/
		public @api SrvActionScript get(){
			return getSrvActionScript(this);
		}
		/** 获取后台事务脚本数 **/
		public @api Long count(){
			return getSrvActionScriptCount(this);
		}
		/** 获取后台事务脚本表达式 **/
		public @api <T> T eval(String strEval){
			return getSrvActionScriptEval(strEval,this);
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
			updateSrvActionScript(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String ssId="ssId";
		/** 脚本名称 [非空]       **/
		public final static @type(String.class) @like String ssName="ssName";
		/** 类名全称 [非空]       **/
		public final static @type(String.class) @like String ssModelClass="ssModelClass";
		/** 事件名称 [非空]       **/
		public final static @type(String.class) @like String ssEvent="ssEvent";
		/** 脚本代码     脚本代码为java代码   **/
		public final static @type(String.class) @like String ssCode="ssCode";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String ssUpdateTime="ssUpdateTime";
	 	/** and ss_update_time >= ? */
 		public final static @type(Date.class) String ssUpdateTimeStart="ssUpdateTimeStart";
 		/** and ss_update_time <= ? */
 		public final static @type(Date.class) String ssUpdateTimeEnd="ssUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String ssAddTime="ssAddTime";
	 	/** and ss_add_time >= ? */
 		public final static @type(Date.class) String ssAddTimeStart="ssAddTimeStart";
 		/** and ss_add_time <= ? */
 		public final static @type(Date.class) String ssAddTimeEnd="ssAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String ssStatus="ssStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String ssId="ss_id";
		/** 脚本名称 [非空]       **/
		public final static String ssName="ss_name";
		/** 类名全称 [非空]       **/
		public final static String ssModelClass="ss_model_class";
		/** 事件名称 [非空]       **/
		public final static String ssEvent="ss_event";
		/** 脚本代码     脚本代码为java代码   **/
		public final static String ssCode="ss_code";
		/** 修改时间 [非空]       **/
		public final static String ssUpdateTime="ss_update_time";
		/** 添加时间 [非空]       **/
		public final static String ssAddTime="ss_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String ssStatus="ss_status";
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
				$.Set(name,SrvActionScript.getSrvActionScript(params));
			else
				$.Set(name,SrvActionScript.getSrvActionScriptList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取后台事务脚本数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.SrvActionScript) $.GetRequest("SrvActionScript$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = SrvActionScript.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = SrvActionScript.getSrvActionScript(params);
			else
				value = SrvActionScript.getSrvActionScriptList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = SrvActionScript.Get($.add(SrvActionScript.F.ssId,param));
		else if(!$.empty(param.toString()))
			value = SrvActionScript.get(Long.valueOf(param.toString()));
		$.SetRequest("SrvActionScript$"+param.hashCode(), value);
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
	public void mergeSet(SrvActionScript old){
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