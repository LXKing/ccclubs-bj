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

@namespace("oa/model")
public @caption("表单设计") @table("srv_model") class SrvModel implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("sm_id")   @primary  @note("  ") Long smId;// 主键 非空     
	private @caption("对象名称") @column("sm_name")    @note("  ") String smName;// 非空     
	private @caption("英文名称") @column("sm_action")    @note("  ") String smAction;// 非空     
	private @caption("相对路径") @column("sm_namespace")    @note("  ") String smNamespace;//     
	private @caption("结构类型") @column("sm_display")    @note(" table:普通列表 tree:层级树形 form:单条表单  ") String smDisplay;// 非空 table:普通列表 tree:层级树形 form:单条表单     
	private @caption("支持功能") @column("sm_functions")    @note(" 1:查询 2:添加 3:修改 4:删除 5:导出 6:报表 7:工作流  ") String smFunctions;// 1:查询 2:添加 3:修改 4:删除 5:导出 6:报表 7:工作流     
	private @caption("字段设计") @column("sm_fields")  @hidden   @note("  ") String smFields;//     
	private @caption("设计人") @column("sm_editor")    @relate("$smEditor") @RelateClass(SrvUser.class)  @note("  ") Long smEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $smEditor;//关联对象[用户]
	private @caption("修改时间") @column("sm_update_time")    @note("  ") Date smUpdateTime;// 非空     
	private @caption("添加时间") @column("sm_add_time")    @note("  ") Date smAddTime;// 非空     
	private @caption("状态") @column("sm_status")    @note(" 0:新增|测试 1:上线使用  ") Short smStatus;// 非空 0:新增|测试 1:上线使用     
	
	//默认构造函数
	public SrvModel(){
	
	}
	
	//主键构造函数
	public SrvModel(Long id){
		this.smId = id;
	}
	
	/**所有字段构造函数 SrvModel(smName,smAction,smNamespace,smDisplay,smFunctions,smFields,smEditor,smUpdateTime,smAddTime,smStatus)
	 SrvModel(
	 	$.getString("smName")//对象名称 [非空]
	 	,$.getString("smAction")//英文名称 [非空]
	 	,$.getString("smNamespace")//相对路径
	 	,$.getString("smDisplay")//结构类型 [非空]
	 	,$.getString("smFunctions")//支持功能
	 	,$.getString("smFields")//字段设计
	 	,$.getLong("smEditor")//设计人 [非空]
	 	,$.getDate("smUpdateTime")//修改时间 [非空]
	 	,$.getDate("smAddTime")//添加时间 [非空]
	 	,$.getShort("smStatus")//状态 [非空]
	 )
	**/
	public SrvModel(String smName,String smAction,String smNamespace,String smDisplay,String smFunctions,String smFields,Long smEditor,Date smUpdateTime,Date smAddTime,Short smStatus){
		this.smName=smName;
		this.smAction=smAction;
		this.smNamespace=smNamespace;
		this.smDisplay=smDisplay;
		this.smFunctions=smFunctions;
		this.smFields=smFields;
		this.smEditor=smEditor;
		this.smUpdateTime=smUpdateTime;
		this.smAddTime=smAddTime;
		this.smStatus=smStatus;
	}
	
	//设置非空字段
	public SrvModel setNotNull(String smName,String smAction,String smDisplay,Long smEditor,Date smUpdateTime,Date smAddTime,Short smStatus){
		this.smName=smName;
		this.smAction=smAction;
		this.smDisplay=smDisplay;
		this.smEditor=smEditor;
		this.smUpdateTime=smUpdateTime;
		this.smAddTime=smAddTime;
		this.smStatus=smStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public SrvModel smId(Long smId){
		this.smId = smId;
		this.setSeted(F.smId);
		return this;
	}
	/** 对象名称 [非空]       **/
	public SrvModel smName(String smName){
		this.smName = smName;
		this.setSeted(F.smName);
		return this;
	}
	/** 英文名称 [非空]       **/
	public SrvModel smAction(String smAction){
		this.smAction = smAction;
		this.setSeted(F.smAction);
		return this;
	}
	/** 相对路径        **/
	public SrvModel smNamespace(String smNamespace){
		this.smNamespace = smNamespace;
		this.setSeted(F.smNamespace);
		return this;
	}
	/** 结构类型 [非空]   table:普通列表 tree:层级树形 form:单条表单     **/
	public SrvModel smDisplay(String smDisplay){
		this.smDisplay = smDisplay;
		this.setSeted(F.smDisplay);
		return this;
	}
	/** 支持功能    1:查询 2:添加 3:修改 4:删除 5:导出 6:报表 7:工作流     **/
	public SrvModel smFunctions(String smFunctions){
		this.smFunctions = smFunctions;
		this.setSeted(F.smFunctions);
		return this;
	}
	/** 字段设计        **/
	public SrvModel smFields(String smFields){
		this.smFields = smFields;
		this.setSeted(F.smFields);
		return this;
	}
	/** 设计人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public SrvModel smEditor(Long smEditor){
		this.smEditor = smEditor;
		this.setSeted(F.smEditor);
		return this;
	}
	/** 修改时间 [非空]       **/
	public SrvModel smUpdateTime(Date smUpdateTime){
		this.smUpdateTime = smUpdateTime;
		this.setSeted(F.smUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public SrvModel smAddTime(Date smAddTime){
		this.smAddTime = smAddTime;
		this.setSeted(F.smAddTime);
		return this;
	}
	/** 状态 [非空]   0:新增|测试 1:上线使用     **/
	public SrvModel smStatus(Short smStatus){
		this.smStatus = smStatus;
		this.setSeted(F.smStatus);
		return this;
	}
	
	
	//克隆对象
	public SrvModel clone(){
		SrvModel clone = new SrvModel();
		clone.smName=this.smName;
		clone.smAction=this.smAction;
		clone.smDisplay=this.smDisplay;
		clone.smEditor=this.smEditor;
		clone.smUpdateTime=this.smUpdateTime;
		clone.smAddTime=this.smAddTime;
		clone.smStatus=this.smStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取表单设计
	 * @param id
	 * @return
	 */
	public static @api SrvModel get(Long id){		
		return getSrvModelById(id);
	}
	/**
	 * 获取所有表单设计
	 * @return
	 */
	public static @api List<SrvModel> list(Map params,Integer size){
		return getSrvModelList(params,size);
	}
	/**
	 * 获取表单设计分页
	 * @return
	 */
	public static @api Page<SrvModel> page(int page,int size,Map params){
		return getSrvModelPage(page, size , params);
	}
	/**
	 * 根据查询条件取表单设计
	 * @param params
	 * @return
	 */
	public static @api SrvModel Get(Map params){
		return getSrvModel(params);
	}
	/**
	 * 获取表单设计数
	 * @return
	 */
	public static @api Long count(Map params){
		return getSrvModelCount(params);
	}
	/**
	 * 获取表单设计数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getSrvModelEval(eval,params);
	}
	
	/**
	 * 根据ID取表单设计
	 * @param id
	 * @return
	 */
	public static @api SrvModel getSrvModelById(Long id){		
		SrvModel srvModel = (SrvModel) $.GetRequest("SrvModel$"+id);
		if(srvModel!=null)
			return srvModel;
		com.ccclubs.service.admin.ISrvModelService srvModelService = $.GetSpringBean("srvModelService");		
		return srvModelService.getSrvModelById(id);
	}
	
	
	/**
	 * 根据ID取表单设计的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(SrvModel.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		SrvModel srvModel = get(id);
		if(srvModel!=null){
			String strValue = srvModel.getSmName$();
			if(!"SmName".equals("SmName"))
				strValue+="("+srvModel.getSmName$()+")";
			MemCache.setValue(SrvModel.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getSmName$();
		if(!"SmName".equals("SmName"))
			keyValue+="("+this.getSmName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有表单设计
	 * @return
	 */
	public static @api List<SrvModel> getSrvModelList(Map params,Integer size){
		com.ccclubs.service.admin.ISrvModelService srvModelService = $.GetSpringBean("srvModelService");
		return srvModelService.getSrvModelList(params, size);
	}
	
	/**
	 * 获取表单设计分页
	 * @return
	 */
	public static @api Page<SrvModel> getSrvModelPage(int page,int size,Map params){
		com.ccclubs.service.admin.ISrvModelService srvModelService = $.GetSpringBean("srvModelService");
		return srvModelService.getSrvModelPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取表单设计
	 * @param params
	 * @return
	 */
	public static @api SrvModel getSrvModel(Map params){
		com.ccclubs.service.admin.ISrvModelService srvModelService = $.GetSpringBean("srvModelService");
		return srvModelService.getSrvModel(params);
	}
	
	/**
	 * 获取表单设计数
	 * @return
	 */
	public static @api Long getSrvModelCount(Map params){
		com.ccclubs.service.admin.ISrvModelService srvModelService = $.GetSpringBean("srvModelService");
		return srvModelService.getSrvModelCount(params);
	}
		
		
	/**
	 * 获取表单设计自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getSrvModelEval(String eval,Map params){
		com.ccclubs.service.admin.ISrvModelService srvModelService = $.GetSpringBean("srvModelService");
		return srvModelService.getSrvModelEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateSrvModel(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ISrvModelService srvModelService = $.GetSpringBean("srvModelService");
		srvModelService.updateSrvModelByConfirm(set, where);
	}
	
	
	/**
	 * 保存表单设计对象
	 * @param params
	 * @return
	 */
	public SrvModel save(){
		com.ccclubs.service.admin.ISrvModelService srvModelService = $.GetSpringBean("srvModelService");
		if(this.getSmId()!=null)
			srvModelService.updateSrvModel(this);
		else
			return srvModelService.saveSrvModel(this);
		return this;
	}
	
	
	/**
	 * 更新表单设计对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ISrvModelService srvModelService = $.GetSpringBean("srvModelService");
		srvModelService.updateSrvModel$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ISrvModelService srvModelService = $.GetSpringBean("srvModelService");
		if($.equals($.config("logic_delete"),"true"))
			srvModelService.removeSrvModelById(this.getSmId());
		else
			srvModelService.deleteSrvModelById(this.getSmId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ISrvModelService srvModelService = $.GetSpringBean("srvModelService");
		return srvModelService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getSmId(){
		return this.smId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getSmId$(){
		String strValue="";
		 strValue=$.str(this.getSmId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setSmId(Long smId){
		this.smId = smId;
		this.setSeted(F.smId);
	}
	/*******************************对象名称**********************************/	
	/**
	* 对象名称 [非空]      
	**/
	public String getSmName(){
		return this.smName;
	}
	/**
	* 获取对象名称格式化(toString)
	**/
	public String getSmName$(){
		String strValue="";
		 strValue=$.str(this.getSmName());
	 	 return strValue;
	}
	/**
	* 对象名称 [非空]      
	**/
	public void setSmName(String smName){
		this.smName = smName;
		this.setSeted(F.smName);
	}
	/*******************************英文名称**********************************/	
	/**
	* 英文名称 [非空]      
	**/
	public String getSmAction(){
		return this.smAction;
	}
	/**
	* 获取英文名称格式化(toString)
	**/
	public String getSmAction$(){
		String strValue="";
		 strValue=$.str(this.getSmAction());
	 	 return strValue;
	}
	/**
	* 英文名称 [非空]      
	**/
	public void setSmAction(String smAction){
		this.smAction = smAction;
		this.setSeted(F.smAction);
	}
	/*******************************相对路径**********************************/	
	/**
	* 相对路径       
	**/
	public String getSmNamespace(){
		return this.smNamespace;
	}
	/**
	* 获取相对路径格式化(toString)
	**/
	public String getSmNamespace$(){
		String strValue="";
		 strValue=$.str(this.getSmNamespace());
	 	 return strValue;
	}
	/**
	* 相对路径       
	**/
	public void setSmNamespace(String smNamespace){
		this.smNamespace = smNamespace;
		this.setSeted(F.smNamespace);
	}
	/*******************************结构类型**********************************/	
	/**
	* 结构类型 [非空]   table:普通列表 tree:层级树形 form:单条表单    
	**/
	public String getSmDisplay(){
		return this.smDisplay;
	}
	/**
	* 获取结构类型格式化(toString)
	**/
	public String getSmDisplay$(){
		String strValue="";
		 if($.equals($.str(this.getSmDisplay()),"table"))
			strValue=$.str("普通列表");		 
		 if($.equals($.str(this.getSmDisplay()),"tree"))
			strValue=$.str("层级树形");		 
		 if($.equals($.str(this.getSmDisplay()),"form"))
			strValue=$.str("单条表单");		 
	 	 return strValue;
	}
	/**
	* 结构类型 [非空]   table:普通列表 tree:层级树形 form:单条表单    
	**/
	public void setSmDisplay(String smDisplay){
		this.smDisplay = smDisplay;
		this.setSeted(F.smDisplay);
	}
	/*******************************支持功能**********************************/	
	/**
	* 支持功能    1:查询 2:添加 3:修改 4:删除 5:导出 6:报表 7:工作流    
	**/
	public String getSmFunctions(){
		return this.smFunctions;
	}
	/**
	* 获取支持功能格式化(toString)
	**/
	public String getSmFunctions$(){
		String strValue="";
		if($.idin(this.getSmFunctions(),1))
			strValue+=$.str("查询,");								
		if($.idin(this.getSmFunctions(),2))
			strValue+=$.str("添加,");								
		if($.idin(this.getSmFunctions(),3))
			strValue+=$.str("修改,");								
		if($.idin(this.getSmFunctions(),4))
			strValue+=$.str("删除,");								
		if($.idin(this.getSmFunctions(),5))
			strValue+=$.str("导出,");								
		if($.idin(this.getSmFunctions(),6))
			strValue+=$.str("报表,");								
		if($.idin(this.getSmFunctions(),7))
			strValue+=$.str("工作流,");								
	 	 return strValue;
	}
	/**
	* 支持功能    1:查询 2:添加 3:修改 4:删除 5:导出 6:报表 7:工作流    
	**/
	public void setSmFunctions(String smFunctions){
		this.smFunctions = smFunctions;
		this.setSeted(F.smFunctions);
	}
	/*******************************字段设计**********************************/	
	/**
	* 字段设计       
	**/
	public String getSmFields(){
		return this.smFields;
	}
	/**
	* 获取字段设计格式化(toString)
	**/
	public String getSmFields$(){
		String strValue="";
		 strValue=$.str(this.getSmFields());
	 	 return strValue;
	}
	/**
	* 字段设计       
	**/
	public void setSmFields(String smFields){
		this.smFields = smFields;
		this.setSeted(F.smFields);
	}
	/*******************************设计人**********************************/	
	/**
	* 设计人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getSmEditor(){
		return this.smEditor;
	}
	/**
	* 获取设计人格式化(toString)
	**/
	public String getSmEditor$(){
		String strValue="";
		if(this.getSmEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getSmEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 设计人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setSmEditor(Long smEditor){
		this.smEditor = smEditor;
		this.setSeted(F.smEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$smEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getSmEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getSmEditor()!=null){
 			srvUser = SrvUser.get(this.getSmEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getSmEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getSmUpdateTime(){
		return this.smUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getSmUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getSmUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setSmUpdateTime(Date smUpdateTime){
		this.smUpdateTime = smUpdateTime;
		this.setSeted(F.smUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getSmAddTime(){
		return this.smAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getSmAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getSmAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setSmAddTime(Date smAddTime){
		this.smAddTime = smAddTime;
		this.setSeted(F.smAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:新增|测试 1:上线使用    
	**/
	public Short getSmStatus(){
		return this.smStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getSmStatus$(){
		String strValue="";
		 if($.equals($.str(this.getSmStatus()),"0"))
			strValue=$.str("新增|测试");		 
		 if($.equals($.str(this.getSmStatus()),"1"))
			strValue=$.str("上线使用");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:新增|测试 1:上线使用    
	**/
	public void setSmStatus(Short smStatus){
		this.smStatus = smStatus;
		this.setSeted(F.smStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${SrvModel.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvModel.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvModel.class.getMethod("get$"+$.forMat(key.toString())).invoke(SrvModel.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${SrvModel.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvModel.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvModel.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(SrvModel.this);
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
		public M smId(Object smId){this.put("smId", smId);return this;};
	 	/** and sm_id is null */
 		public M smIdNull(){if(this.get("smIdNot")==null)this.put("smIdNot", "");this.put("smId", null);return this;};
 		/** not .... */
 		public M smIdNot(){this.put("smIdNot", "not");return this;};
		/** 对象名称 [非空]       **/
		public M smName(Object smName){this.put("smName", smName);return this;};
	 	/** and sm_name is null */
 		public M smNameNull(){if(this.get("smNameNot")==null)this.put("smNameNot", "");this.put("smName", null);return this;};
 		/** not .... */
 		public M smNameNot(){this.put("smNameNot", "not");return this;};
		/** 英文名称 [非空]       **/
		public M smAction(Object smAction){this.put("smAction", smAction);return this;};
	 	/** and sm_action is null */
 		public M smActionNull(){if(this.get("smActionNot")==null)this.put("smActionNot", "");this.put("smAction", null);return this;};
 		/** not .... */
 		public M smActionNot(){this.put("smActionNot", "not");return this;};
		/** 相对路径        **/
		public M smNamespace(Object smNamespace){this.put("smNamespace", smNamespace);return this;};
	 	/** and sm_namespace is null */
 		public M smNamespaceNull(){if(this.get("smNamespaceNot")==null)this.put("smNamespaceNot", "");this.put("smNamespace", null);return this;};
 		/** not .... */
 		public M smNamespaceNot(){this.put("smNamespaceNot", "not");return this;};
		/** 结构类型 [非空]   table:普通列表 tree:层级树形 form:单条表单     **/
		public M smDisplay(Object smDisplay){this.put("smDisplay", smDisplay);return this;};
	 	/** and sm_display is null */
 		public M smDisplayNull(){if(this.get("smDisplayNot")==null)this.put("smDisplayNot", "");this.put("smDisplay", null);return this;};
 		/** not .... */
 		public M smDisplayNot(){this.put("smDisplayNot", "not");return this;};
		/** 支持功能    1:查询 2:添加 3:修改 4:删除 5:导出 6:报表 7:工作流     **/
		public M smFunctions(Object smFunctions){this.put("smFunctions", smFunctions);return this;};
	 	/** and sm_functions is null */
 		public M smFunctionsNull(){if(this.get("smFunctionsNot")==null)this.put("smFunctionsNot", "");this.put("smFunctions", null);return this;};
 		/** not .... */
 		public M smFunctionsNot(){this.put("smFunctionsNot", "not");return this;};
		/** 字段设计        **/
		public M smFields(Object smFields){this.put("smFields", smFields);return this;};
	 	/** and sm_fields is null */
 		public M smFieldsNull(){if(this.get("smFieldsNot")==null)this.put("smFieldsNot", "");this.put("smFields", null);return this;};
 		/** not .... */
 		public M smFieldsNot(){this.put("smFieldsNot", "not");return this;};
		/** 设计人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M smEditor(Object smEditor){this.put("smEditor", smEditor);return this;};
	 	/** and sm_editor is null */
 		public M smEditorNull(){if(this.get("smEditorNot")==null)this.put("smEditorNot", "");this.put("smEditor", null);return this;};
 		/** not .... */
 		public M smEditorNot(){this.put("smEditorNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M smUpdateTime(Object smUpdateTime){this.put("smUpdateTime", smUpdateTime);return this;};
	 	/** and sm_update_time is null */
 		public M smUpdateTimeNull(){if(this.get("smUpdateTimeNot")==null)this.put("smUpdateTimeNot", "");this.put("smUpdateTime", null);return this;};
 		/** not .... */
 		public M smUpdateTimeNot(){this.put("smUpdateTimeNot", "not");return this;};
 		/** and sm_update_time >= ? */
 		public M smUpdateTimeStart(Object start){this.put("smUpdateTimeStart", start);return this;};			
 		/** and sm_update_time <= ? */
 		public M smUpdateTimeEnd(Object end){this.put("smUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M smAddTime(Object smAddTime){this.put("smAddTime", smAddTime);return this;};
	 	/** and sm_add_time is null */
 		public M smAddTimeNull(){if(this.get("smAddTimeNot")==null)this.put("smAddTimeNot", "");this.put("smAddTime", null);return this;};
 		/** not .... */
 		public M smAddTimeNot(){this.put("smAddTimeNot", "not");return this;};
 		/** and sm_add_time >= ? */
 		public M smAddTimeStart(Object start){this.put("smAddTimeStart", start);return this;};			
 		/** and sm_add_time <= ? */
 		public M smAddTimeEnd(Object end){this.put("smAddTimeEnd", end);return this;};
		/** 状态 [非空]   0:新增|测试 1:上线使用     **/
		public M smStatus(Object smStatus){this.put("smStatus", smStatus);return this;};
	 	/** and sm_status is null */
 		public M smStatusNull(){if(this.get("smStatusNot")==null)this.put("smStatusNot", "");this.put("smStatus", null);return this;};
 		/** not .... */
 		public M smStatusNot(){this.put("smStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有表单设计 **/
		public @api List<SrvModel> list(Integer size){
			return getSrvModelList(this,size);
		}
		/** 获取表单设计分页 **/
		public @api Page<SrvModel> page(int page,int size){
			return getSrvModelPage(page, size , this);
		}
		/** 根据查询条件取表单设计 **/
		public @api SrvModel get(){
			return getSrvModel(this);
		}
		/** 获取表单设计数 **/
		public @api Long count(){
			return getSrvModelCount(this);
		}
		/** 获取表单设计表达式 **/
		public @api <T> T eval(String strEval){
			return getSrvModelEval(strEval,this);
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
			updateSrvModel(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String smId="smId";
		/** 对象名称 [非空]       **/
		public final static @type(String.class) @like String smName="smName";
		/** 英文名称 [非空]       **/
		public final static @type(String.class) @like String smAction="smAction";
		/** 相对路径        **/
		public final static @type(String.class) @like String smNamespace="smNamespace";
		/** 结构类型 [非空]   table:普通列表 tree:层级树形 form:单条表单     **/
		public final static @type(String.class)  String smDisplay="smDisplay";
		/** 支持功能    1:查询 2:添加 3:修改 4:删除 5:导出 6:报表 7:工作流     **/
		public final static @type(String.class) @like String smFunctions="smFunctions";
		/** 字段设计        **/
		public final static @type(String.class) @like String smFields="smFields";
		/** 设计人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String smEditor="smEditor";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String smUpdateTime="smUpdateTime";
	 	/** and sm_update_time >= ? */
 		public final static @type(Date.class) String smUpdateTimeStart="smUpdateTimeStart";
 		/** and sm_update_time <= ? */
 		public final static @type(Date.class) String smUpdateTimeEnd="smUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String smAddTime="smAddTime";
	 	/** and sm_add_time >= ? */
 		public final static @type(Date.class) String smAddTimeStart="smAddTimeStart";
 		/** and sm_add_time <= ? */
 		public final static @type(Date.class) String smAddTimeEnd="smAddTimeEnd";
		/** 状态 [非空]   0:新增|测试 1:上线使用     **/
		public final static @type(Short.class)  String smStatus="smStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String smId="sm_id";
		/** 对象名称 [非空]       **/
		public final static String smName="sm_name";
		/** 英文名称 [非空]       **/
		public final static String smAction="sm_action";
		/** 相对路径        **/
		public final static String smNamespace="sm_namespace";
		/** 结构类型 [非空]   table:普通列表 tree:层级树形 form:单条表单     **/
		public final static String smDisplay="sm_display";
		/** 支持功能    1:查询 2:添加 3:修改 4:删除 5:导出 6:报表 7:工作流     **/
		public final static String smFunctions="sm_functions";
		/** 字段设计        **/
		public final static String smFields="sm_fields";
		/** 设计人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String smEditor="sm_editor";
		/** 修改时间 [非空]       **/
		public final static String smUpdateTime="sm_update_time";
		/** 添加时间 [非空]       **/
		public final static String smAddTime="sm_add_time";
		/** 状态 [非空]   0:新增|测试 1:上线使用     **/
		public final static String smStatus="sm_status";
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
				$.Set(name,SrvModel.getSrvModel(params));
			else
				$.Set(name,SrvModel.getSrvModelList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取表单设计数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.SrvModel) $.GetRequest("SrvModel$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = SrvModel.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = SrvModel.getSrvModel(params);
			else
				value = SrvModel.getSrvModelList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = SrvModel.Get($.add(SrvModel.F.smId,param));
		else if(!$.empty(param.toString()))
			value = SrvModel.get(Long.valueOf(param.toString()));
		$.SetRequest("SrvModel$"+param.hashCode(), value);
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
	public void mergeSet(SrvModel old){
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