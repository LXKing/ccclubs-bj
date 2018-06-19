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

@namespace("oa/workflow")
public @caption("工作流") @table("srv_workflow") class SrvWorkflow implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("sw_id")   @primary  @note("  ") Long swId;// 主键 非空     
	private @caption("名称") @column("sw_name")    @note("  ") String swName;// 非空     
	private @caption("工作流描述") @column("sw_profile")  @hidden   @note("  ") String swProfile;//     
	private @caption("流程图设计") @column("sw_paint")  @hidden   @note("  ") String swPaint;// 非空     
	private @caption("流程配置") @column("sw_config")  @hidden   @note("  ") String swConfig;// 非空     
	private @caption("设计人") @column("sw_editor")    @relate("$swEditor") @RelateClass(SrvUser.class)  @note("  ") Long swEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $swEditor;//关联对象[用户]
	private @caption("修改时间") @column("sw_update_time")    @note("  ") Date swUpdateTime;// 非空     
	private @caption("添加时间") @column("sw_add_time")    @note("  ") Date swAddTime;// 非空     
	private @caption("状态") @column("sw_status")    @note(" 1:正常 0:无效  ") Short swStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public SrvWorkflow(){
	
	}
	
	//主键构造函数
	public SrvWorkflow(Long id){
		this.swId = id;
	}
	
	/**所有字段构造函数 SrvWorkflow(swName,swProfile,swPaint,swConfig,swEditor,swUpdateTime,swAddTime,swStatus)
	 SrvWorkflow(
	 	$.getString("swName")//名称 [非空]
	 	,$.getString("swProfile")//工作流描述
	 	,$.getString("swPaint")//流程图设计 [非空]
	 	,$.getString("swConfig")//流程配置 [非空]
	 	,$.getLong("swEditor")//设计人 [非空]
	 	,$.getDate("swUpdateTime")//修改时间 [非空]
	 	,$.getDate("swAddTime")//添加时间 [非空]
	 	,$.getShort("swStatus")//状态 [非空]
	 )
	**/
	public SrvWorkflow(String swName,String swProfile,String swPaint,String swConfig,Long swEditor,Date swUpdateTime,Date swAddTime,Short swStatus){
		this.swName=swName;
		this.swProfile=swProfile;
		this.swPaint=swPaint;
		this.swConfig=swConfig;
		this.swEditor=swEditor;
		this.swUpdateTime=swUpdateTime;
		this.swAddTime=swAddTime;
		this.swStatus=swStatus;
	}
	
	//设置非空字段
	public SrvWorkflow setNotNull(String swName,String swPaint,String swConfig,Long swEditor,Date swUpdateTime,Date swAddTime,Short swStatus){
		this.swName=swName;
		this.swPaint=swPaint;
		this.swConfig=swConfig;
		this.swEditor=swEditor;
		this.swUpdateTime=swUpdateTime;
		this.swAddTime=swAddTime;
		this.swStatus=swStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public SrvWorkflow swId(Long swId){
		this.swId = swId;
		this.setSeted(F.swId);
		return this;
	}
	/** 名称 [非空]       **/
	public SrvWorkflow swName(String swName){
		this.swName = swName;
		this.setSeted(F.swName);
		return this;
	}
	/** 工作流描述        **/
	public SrvWorkflow swProfile(String swProfile){
		this.swProfile = swProfile;
		this.setSeted(F.swProfile);
		return this;
	}
	/** 流程图设计 [非空]       **/
	public SrvWorkflow swPaint(String swPaint){
		this.swPaint = swPaint;
		this.setSeted(F.swPaint);
		return this;
	}
	/** 流程配置 [非空]       **/
	public SrvWorkflow swConfig(String swConfig){
		this.swConfig = swConfig;
		this.setSeted(F.swConfig);
		return this;
	}
	/** 设计人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public SrvWorkflow swEditor(Long swEditor){
		this.swEditor = swEditor;
		this.setSeted(F.swEditor);
		return this;
	}
	/** 修改时间 [非空]       **/
	public SrvWorkflow swUpdateTime(Date swUpdateTime){
		this.swUpdateTime = swUpdateTime;
		this.setSeted(F.swUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public SrvWorkflow swAddTime(Date swAddTime){
		this.swAddTime = swAddTime;
		this.setSeted(F.swAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public SrvWorkflow swStatus(Short swStatus){
		this.swStatus = swStatus;
		this.setSeted(F.swStatus);
		return this;
	}
	
	
	//克隆对象
	public SrvWorkflow clone(){
		SrvWorkflow clone = new SrvWorkflow();
		clone.swName=this.swName;
		clone.swPaint=this.swPaint;
		clone.swConfig=this.swConfig;
		clone.swEditor=this.swEditor;
		clone.swUpdateTime=this.swUpdateTime;
		clone.swAddTime=this.swAddTime;
		clone.swStatus=this.swStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取工作流
	 * @param id
	 * @return
	 */
	public static @api SrvWorkflow get(Long id){		
		return getSrvWorkflowById(id);
	}
	/**
	 * 获取所有工作流
	 * @return
	 */
	public static @api List<SrvWorkflow> list(Map params,Integer size){
		return getSrvWorkflowList(params,size);
	}
	/**
	 * 获取工作流分页
	 * @return
	 */
	public static @api Page<SrvWorkflow> page(int page,int size,Map params){
		return getSrvWorkflowPage(page, size , params);
	}
	/**
	 * 根据查询条件取工作流
	 * @param params
	 * @return
	 */
	public static @api SrvWorkflow Get(Map params){
		return getSrvWorkflow(params);
	}
	/**
	 * 获取工作流数
	 * @return
	 */
	public static @api Long count(Map params){
		return getSrvWorkflowCount(params);
	}
	/**
	 * 获取工作流数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getSrvWorkflowEval(eval,params);
	}
	
	/**
	 * 根据ID取工作流
	 * @param id
	 * @return
	 */
	public static @api SrvWorkflow getSrvWorkflowById(Long id){		
		SrvWorkflow srvWorkflow = (SrvWorkflow) $.GetRequest("SrvWorkflow$"+id);
		if(srvWorkflow!=null)
			return srvWorkflow;
		com.ccclubs.service.admin.ISrvWorkflowService srvWorkflowService = $.GetSpringBean("srvWorkflowService");		
		return srvWorkflowService.getSrvWorkflowById(id);
	}
	
	
	/**
	 * 根据ID取工作流的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(SrvWorkflow.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		SrvWorkflow srvWorkflow = get(id);
		if(srvWorkflow!=null){
			String strValue = srvWorkflow.getSwName$();
			if(!"SwName".equals("SwName"))
				strValue+="("+srvWorkflow.getSwName$()+")";
			MemCache.setValue(SrvWorkflow.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getSwName$();
		if(!"SwName".equals("SwName"))
			keyValue+="("+this.getSwName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有工作流
	 * @return
	 */
	public static @api List<SrvWorkflow> getSrvWorkflowList(Map params,Integer size){
		com.ccclubs.service.admin.ISrvWorkflowService srvWorkflowService = $.GetSpringBean("srvWorkflowService");
		return srvWorkflowService.getSrvWorkflowList(params, size);
	}
	
	/**
	 * 获取工作流分页
	 * @return
	 */
	public static @api Page<SrvWorkflow> getSrvWorkflowPage(int page,int size,Map params){
		com.ccclubs.service.admin.ISrvWorkflowService srvWorkflowService = $.GetSpringBean("srvWorkflowService");
		return srvWorkflowService.getSrvWorkflowPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取工作流
	 * @param params
	 * @return
	 */
	public static @api SrvWorkflow getSrvWorkflow(Map params){
		com.ccclubs.service.admin.ISrvWorkflowService srvWorkflowService = $.GetSpringBean("srvWorkflowService");
		return srvWorkflowService.getSrvWorkflow(params);
	}
	
	/**
	 * 获取工作流数
	 * @return
	 */
	public static @api Long getSrvWorkflowCount(Map params){
		com.ccclubs.service.admin.ISrvWorkflowService srvWorkflowService = $.GetSpringBean("srvWorkflowService");
		return srvWorkflowService.getSrvWorkflowCount(params);
	}
		
		
	/**
	 * 获取工作流自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getSrvWorkflowEval(String eval,Map params){
		com.ccclubs.service.admin.ISrvWorkflowService srvWorkflowService = $.GetSpringBean("srvWorkflowService");
		return srvWorkflowService.getSrvWorkflowEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateSrvWorkflow(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ISrvWorkflowService srvWorkflowService = $.GetSpringBean("srvWorkflowService");
		srvWorkflowService.updateSrvWorkflowByConfirm(set, where);
	}
	
	
	/**
	 * 保存工作流对象
	 * @param params
	 * @return
	 */
	public SrvWorkflow save(){
		com.ccclubs.service.admin.ISrvWorkflowService srvWorkflowService = $.GetSpringBean("srvWorkflowService");
		if(this.getSwId()!=null)
			srvWorkflowService.updateSrvWorkflow(this);
		else
			return srvWorkflowService.saveSrvWorkflow(this);
		return this;
	}
	
	
	/**
	 * 更新工作流对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ISrvWorkflowService srvWorkflowService = $.GetSpringBean("srvWorkflowService");
		srvWorkflowService.updateSrvWorkflow$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ISrvWorkflowService srvWorkflowService = $.GetSpringBean("srvWorkflowService");
		if($.equals($.config("logic_delete"),"true"))
			srvWorkflowService.removeSrvWorkflowById(this.getSwId());
		else
			srvWorkflowService.deleteSrvWorkflowById(this.getSwId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ISrvWorkflowService srvWorkflowService = $.GetSpringBean("srvWorkflowService");
		return srvWorkflowService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getSwId(){
		return this.swId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getSwId$(){
		String strValue="";
		 strValue=$.str(this.getSwId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setSwId(Long swId){
		this.swId = swId;
		this.setSeted(F.swId);
	}
	/*******************************名称**********************************/	
	/**
	* 名称 [非空]      
	**/
	public String getSwName(){
		return this.swName;
	}
	/**
	* 获取名称格式化(toString)
	**/
	public String getSwName$(){
		String strValue="";
		 strValue=$.str(this.getSwName());
	 	 return strValue;
	}
	/**
	* 名称 [非空]      
	**/
	public void setSwName(String swName){
		this.swName = swName;
		this.setSeted(F.swName);
	}
	/*******************************工作流描述**********************************/	
	/**
	* 工作流描述       
	**/
	public String getSwProfile(){
		return this.swProfile;
	}
	/**
	* 获取工作流描述格式化(toString)
	**/
	public String getSwProfile$(){
		String strValue="";
		 strValue=$.str(this.getSwProfile());
	 	 return strValue;
	}
	/**
	* 工作流描述       
	**/
	public void setSwProfile(String swProfile){
		this.swProfile = swProfile;
		this.setSeted(F.swProfile);
	}
	/*******************************流程图设计**********************************/	
	/**
	* 流程图设计 [非空]      
	**/
	public String getSwPaint(){
		return this.swPaint;
	}
	/**
	* 获取流程图设计格式化(toString)
	**/
	public String getSwPaint$(){
		String strValue="";
		 strValue=$.str(this.getSwPaint());
	 	 return strValue;
	}
	/**
	* 流程图设计 [非空]      
	**/
	public void setSwPaint(String swPaint){
		this.swPaint = swPaint;
		this.setSeted(F.swPaint);
	}
	/*******************************流程配置**********************************/	
	/**
	* 流程配置 [非空]      
	**/
	public String getSwConfig(){
		return this.swConfig;
	}
	/**
	* 获取流程配置格式化(toString)
	**/
	public String getSwConfig$(){
		String strValue="";
		 strValue=$.str(this.getSwConfig());
	 	 return strValue;
	}
	/**
	* 流程配置 [非空]      
	**/
	public void setSwConfig(String swConfig){
		this.swConfig = swConfig;
		this.setSeted(F.swConfig);
	}
	/*******************************设计人**********************************/	
	/**
	* 设计人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getSwEditor(){
		return this.swEditor;
	}
	/**
	* 获取设计人格式化(toString)
	**/
	public String getSwEditor$(){
		String strValue="";
		if(this.getSwEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getSwEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 设计人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setSwEditor(Long swEditor){
		this.swEditor = swEditor;
		this.setSeted(F.swEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$swEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getSwEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getSwEditor()!=null){
 			srvUser = SrvUser.get(this.getSwEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getSwEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getSwUpdateTime(){
		return this.swUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getSwUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getSwUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setSwUpdateTime(Date swUpdateTime){
		this.swUpdateTime = swUpdateTime;
		this.setSeted(F.swUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getSwAddTime(){
		return this.swAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getSwAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getSwAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setSwAddTime(Date swAddTime){
		this.swAddTime = swAddTime;
		this.setSeted(F.swAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getSwStatus(){
		return this.swStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getSwStatus$(){
		String strValue="";
		 if($.equals($.str(this.getSwStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getSwStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setSwStatus(Short swStatus){
		this.swStatus = swStatus;
		this.setSeted(F.swStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${SrvWorkflow.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvWorkflow.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvWorkflow.class.getMethod("get$"+$.forMat(key.toString())).invoke(SrvWorkflow.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${SrvWorkflow.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvWorkflow.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvWorkflow.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(SrvWorkflow.this);
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
		public M swId(Object swId){this.put("swId", swId);return this;};
	 	/** and sw_id is null */
 		public M swIdNull(){if(this.get("swIdNot")==null)this.put("swIdNot", "");this.put("swId", null);return this;};
 		/** not .... */
 		public M swIdNot(){this.put("swIdNot", "not");return this;};
		/** 名称 [非空]       **/
		public M swName(Object swName){this.put("swName", swName);return this;};
	 	/** and sw_name is null */
 		public M swNameNull(){if(this.get("swNameNot")==null)this.put("swNameNot", "");this.put("swName", null);return this;};
 		/** not .... */
 		public M swNameNot(){this.put("swNameNot", "not");return this;};
		/** 工作流描述        **/
		public M swProfile(Object swProfile){this.put("swProfile", swProfile);return this;};
	 	/** and sw_profile is null */
 		public M swProfileNull(){if(this.get("swProfileNot")==null)this.put("swProfileNot", "");this.put("swProfile", null);return this;};
 		/** not .... */
 		public M swProfileNot(){this.put("swProfileNot", "not");return this;};
		/** 流程图设计 [非空]       **/
		public M swPaint(Object swPaint){this.put("swPaint", swPaint);return this;};
	 	/** and sw_paint is null */
 		public M swPaintNull(){if(this.get("swPaintNot")==null)this.put("swPaintNot", "");this.put("swPaint", null);return this;};
 		/** not .... */
 		public M swPaintNot(){this.put("swPaintNot", "not");return this;};
		/** 流程配置 [非空]       **/
		public M swConfig(Object swConfig){this.put("swConfig", swConfig);return this;};
	 	/** and sw_config is null */
 		public M swConfigNull(){if(this.get("swConfigNot")==null)this.put("swConfigNot", "");this.put("swConfig", null);return this;};
 		/** not .... */
 		public M swConfigNot(){this.put("swConfigNot", "not");return this;};
		/** 设计人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M swEditor(Object swEditor){this.put("swEditor", swEditor);return this;};
	 	/** and sw_editor is null */
 		public M swEditorNull(){if(this.get("swEditorNot")==null)this.put("swEditorNot", "");this.put("swEditor", null);return this;};
 		/** not .... */
 		public M swEditorNot(){this.put("swEditorNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M swUpdateTime(Object swUpdateTime){this.put("swUpdateTime", swUpdateTime);return this;};
	 	/** and sw_update_time is null */
 		public M swUpdateTimeNull(){if(this.get("swUpdateTimeNot")==null)this.put("swUpdateTimeNot", "");this.put("swUpdateTime", null);return this;};
 		/** not .... */
 		public M swUpdateTimeNot(){this.put("swUpdateTimeNot", "not");return this;};
 		/** and sw_update_time >= ? */
 		public M swUpdateTimeStart(Object start){this.put("swUpdateTimeStart", start);return this;};			
 		/** and sw_update_time <= ? */
 		public M swUpdateTimeEnd(Object end){this.put("swUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M swAddTime(Object swAddTime){this.put("swAddTime", swAddTime);return this;};
	 	/** and sw_add_time is null */
 		public M swAddTimeNull(){if(this.get("swAddTimeNot")==null)this.put("swAddTimeNot", "");this.put("swAddTime", null);return this;};
 		/** not .... */
 		public M swAddTimeNot(){this.put("swAddTimeNot", "not");return this;};
 		/** and sw_add_time >= ? */
 		public M swAddTimeStart(Object start){this.put("swAddTimeStart", start);return this;};			
 		/** and sw_add_time <= ? */
 		public M swAddTimeEnd(Object end){this.put("swAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M swStatus(Object swStatus){this.put("swStatus", swStatus);return this;};
	 	/** and sw_status is null */
 		public M swStatusNull(){if(this.get("swStatusNot")==null)this.put("swStatusNot", "");this.put("swStatus", null);return this;};
 		/** not .... */
 		public M swStatusNot(){this.put("swStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有工作流 **/
		public @api List<SrvWorkflow> list(Integer size){
			return getSrvWorkflowList(this,size);
		}
		/** 获取工作流分页 **/
		public @api Page<SrvWorkflow> page(int page,int size){
			return getSrvWorkflowPage(page, size , this);
		}
		/** 根据查询条件取工作流 **/
		public @api SrvWorkflow get(){
			return getSrvWorkflow(this);
		}
		/** 获取工作流数 **/
		public @api Long count(){
			return getSrvWorkflowCount(this);
		}
		/** 获取工作流表达式 **/
		public @api <T> T eval(String strEval){
			return getSrvWorkflowEval(strEval,this);
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
			updateSrvWorkflow(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String swId="swId";
		/** 名称 [非空]       **/
		public final static @type(String.class) @like String swName="swName";
		/** 工作流描述        **/
		public final static @type(String.class) @like String swProfile="swProfile";
		/** 流程图设计 [非空]       **/
		public final static @type(String.class) @like String swPaint="swPaint";
		/** 流程配置 [非空]       **/
		public final static @type(String.class) @like String swConfig="swConfig";
		/** 设计人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String swEditor="swEditor";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String swUpdateTime="swUpdateTime";
	 	/** and sw_update_time >= ? */
 		public final static @type(Date.class) String swUpdateTimeStart="swUpdateTimeStart";
 		/** and sw_update_time <= ? */
 		public final static @type(Date.class) String swUpdateTimeEnd="swUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String swAddTime="swAddTime";
	 	/** and sw_add_time >= ? */
 		public final static @type(Date.class) String swAddTimeStart="swAddTimeStart";
 		/** and sw_add_time <= ? */
 		public final static @type(Date.class) String swAddTimeEnd="swAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String swStatus="swStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String swId="sw_id";
		/** 名称 [非空]       **/
		public final static String swName="sw_name";
		/** 工作流描述        **/
		public final static String swProfile="sw_profile";
		/** 流程图设计 [非空]       **/
		public final static String swPaint="sw_paint";
		/** 流程配置 [非空]       **/
		public final static String swConfig="sw_config";
		/** 设计人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String swEditor="sw_editor";
		/** 修改时间 [非空]       **/
		public final static String swUpdateTime="sw_update_time";
		/** 添加时间 [非空]       **/
		public final static String swAddTime="sw_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String swStatus="sw_status";
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
				$.Set(name,SrvWorkflow.getSrvWorkflow(params));
			else
				$.Set(name,SrvWorkflow.getSrvWorkflowList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取工作流数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.SrvWorkflow) $.GetRequest("SrvWorkflow$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = SrvWorkflow.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = SrvWorkflow.getSrvWorkflow(params);
			else
				value = SrvWorkflow.getSrvWorkflowList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = SrvWorkflow.Get($.add(SrvWorkflow.F.swId,param));
		else if(!$.empty(param.toString()))
			value = SrvWorkflow.get(Long.valueOf(param.toString()));
		$.SetRequest("SrvWorkflow$"+param.hashCode(), value);
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
	public void mergeSet(SrvWorkflow old){
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