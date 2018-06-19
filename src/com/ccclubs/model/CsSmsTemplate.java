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

@namespace("configurator/template")
public @caption("短信模版") @table("cs_sms_template") class CsSmsTemplate implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csst_id")   @primary  @note("  ") Long csstId;// 主键 非空     
	private @caption("城市") @column("csst_host")    @relate("$csstHost") @RelateClass(SrvHost.class)  @note("  ") Long csstHost;// 非空     
 	private SrvHost $csstHost;//关联对象[运营城市]
	private @caption("模版名称") @column("csst_name")    @note("  ") String csstName;// 非空     
	private @caption("模版内容") @column("csst_content")    @note("  ") String csstContent;// 非空     
	private @caption("短信类型") @column("csst_type")    @note(" 1:代码类短信 2:通知类短信 3:提示类短信 4:广告类短信  ") Long csstType;// 非空 1:代码类短信 2:通知类短信 3:提示类短信 4:广告类短信     
	private @caption("模版标识") @column("csst_flag")    @note("  ") String csstFlag;//     
	private @caption("添加人") @column("csst_editor")    @relate("$csstEditor") @RelateClass(SrvUser.class)  @note("  ") Long csstEditor;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csstEditor;//关联对象[用户]
	private @caption("修改时间") @column("csst_update_time")    @note("  ") Date csstUpdateTime;// 非空     
	private @caption("添加时间") @column("csst_add_time")    @note("  ") Date csstAddTime;// 非空     
	private @caption("状态") @column("csst_status")    @note(" 1:正常 0:无效  ") Short csstStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsSmsTemplate(){
	
	}
	
	//主键构造函数
	public CsSmsTemplate(Long id){
		this.csstId = id;
	}
	
	/**所有字段构造函数 CsSmsTemplate(csstHost,csstName,csstContent,csstType,csstFlag,csstEditor,csstUpdateTime,csstAddTime,csstStatus)
	 CsSmsTemplate(
	 	$.getLong("csstHost")//城市 [非空]
	 	,$.getString("csstName")//模版名称 [非空]
	 	,$.getString("csstContent")//模版内容 [非空]
	 	,$.getLong("csstType")//短信类型 [非空]
	 	,$.getString("csstFlag")//模版标识
	 	,$.getLong("csstEditor")//添加人
	 	,$.getDate("csstUpdateTime")//修改时间 [非空]
	 	,$.getDate("csstAddTime")//添加时间 [非空]
	 	,$.getShort("csstStatus")//状态 [非空]
	 )
	**/
	public CsSmsTemplate(Long csstHost,String csstName,String csstContent,Long csstType,String csstFlag,Long csstEditor,Date csstUpdateTime,Date csstAddTime,Short csstStatus){
		this.csstHost=csstHost;
		this.csstName=csstName;
		this.csstContent=csstContent;
		this.csstType=csstType;
		this.csstFlag=csstFlag;
		this.csstEditor=csstEditor;
		this.csstUpdateTime=csstUpdateTime;
		this.csstAddTime=csstAddTime;
		this.csstStatus=csstStatus;
	}
	
	//设置非空字段
	public CsSmsTemplate setNotNull(Long csstHost,String csstName,String csstContent,Long csstType,Date csstUpdateTime,Date csstAddTime,Short csstStatus){
		this.csstHost=csstHost;
		this.csstName=csstName;
		this.csstContent=csstContent;
		this.csstType=csstType;
		this.csstUpdateTime=csstUpdateTime;
		this.csstAddTime=csstAddTime;
		this.csstStatus=csstStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsSmsTemplate csstId(Long csstId){
		this.csstId = csstId;
		this.setSeted(F.csstId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsSmsTemplate csstHost(Long csstHost){
		this.csstHost = csstHost;
		this.setSeted(F.csstHost);
		return this;
	}
	/** 模版名称 [非空]       **/
	public CsSmsTemplate csstName(String csstName){
		this.csstName = csstName;
		this.setSeted(F.csstName);
		return this;
	}
	/** 模版内容 [非空]       **/
	public CsSmsTemplate csstContent(String csstContent){
		this.csstContent = csstContent;
		this.setSeted(F.csstContent);
		return this;
	}
	/** 短信类型 [非空]   1:代码类短信 2:通知类短信 3:提示类短信 4:广告类短信     **/
	public CsSmsTemplate csstType(Long csstType){
		this.csstType = csstType;
		this.setSeted(F.csstType);
		return this;
	}
	/** 模版标识        **/
	public CsSmsTemplate csstFlag(String csstFlag){
		this.csstFlag = csstFlag;
		this.setSeted(F.csstFlag);
		return this;
	}
	/** 添加人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsSmsTemplate csstEditor(Long csstEditor){
		this.csstEditor = csstEditor;
		this.setSeted(F.csstEditor);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsSmsTemplate csstUpdateTime(Date csstUpdateTime){
		this.csstUpdateTime = csstUpdateTime;
		this.setSeted(F.csstUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsSmsTemplate csstAddTime(Date csstAddTime){
		this.csstAddTime = csstAddTime;
		this.setSeted(F.csstAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsSmsTemplate csstStatus(Short csstStatus){
		this.csstStatus = csstStatus;
		this.setSeted(F.csstStatus);
		return this;
	}
	
	
	//克隆对象
	public CsSmsTemplate clone(){
		CsSmsTemplate clone = new CsSmsTemplate();
		clone.csstHost=this.csstHost;
		clone.csstName=this.csstName;
		clone.csstContent=this.csstContent;
		clone.csstType=this.csstType;
		clone.csstUpdateTime=this.csstUpdateTime;
		clone.csstAddTime=this.csstAddTime;
		clone.csstStatus=this.csstStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取短信模版
	 * @param id
	 * @return
	 */
	public static @api CsSmsTemplate get(Long id){		
		return getCsSmsTemplateById(id);
	}
	/**
	 * 获取所有短信模版
	 * @return
	 */
	public static @api List<CsSmsTemplate> list(Map params,Integer size){
		return getCsSmsTemplateList(params,size);
	}
	/**
	 * 获取短信模版分页
	 * @return
	 */
	public static @api Page<CsSmsTemplate> page(int page,int size,Map params){
		return getCsSmsTemplatePage(page, size , params);
	}
	/**
	 * 根据查询条件取短信模版
	 * @param params
	 * @return
	 */
	public static @api CsSmsTemplate Get(Map params){
		return getCsSmsTemplate(params);
	}
	/**
	 * 获取短信模版数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsSmsTemplateCount(params);
	}
	/**
	 * 获取短信模版数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsSmsTemplateEval(eval,params);
	}
	
	/**
	 * 根据ID取短信模版
	 * @param id
	 * @return
	 */
	public static @api CsSmsTemplate getCsSmsTemplateById(Long id){		
		CsSmsTemplate csSmsTemplate = (CsSmsTemplate) $.GetRequest("CsSmsTemplate$"+id);
		if(csSmsTemplate!=null)
			return csSmsTemplate;
		com.ccclubs.service.admin.ICsSmsTemplateService csSmsTemplateService = $.GetSpringBean("csSmsTemplateService");		
		return csSmsTemplateService.getCsSmsTemplateById(id);
	}
	
	
	/**
	 * 根据ID取短信模版的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsSmsTemplate.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsSmsTemplate csSmsTemplate = get(id);
		if(csSmsTemplate!=null){
			String strValue = csSmsTemplate.getCsstName$();
			if(!"CsstName".equals("CsstName"))
				strValue+="("+csSmsTemplate.getCsstName$()+")";
			MemCache.setValue(CsSmsTemplate.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsstName$();
		if(!"CsstName".equals("CsstName"))
			keyValue+="("+this.getCsstName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有短信模版
	 * @return
	 */
	public static @api List<CsSmsTemplate> getCsSmsTemplateList(Map params,Integer size){
		com.ccclubs.service.admin.ICsSmsTemplateService csSmsTemplateService = $.GetSpringBean("csSmsTemplateService");
		return csSmsTemplateService.getCsSmsTemplateList(params, size);
	}
	
	/**
	 * 获取短信模版分页
	 * @return
	 */
	public static @api Page<CsSmsTemplate> getCsSmsTemplatePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsSmsTemplateService csSmsTemplateService = $.GetSpringBean("csSmsTemplateService");
		return csSmsTemplateService.getCsSmsTemplatePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取短信模版
	 * @param params
	 * @return
	 */
	public static @api CsSmsTemplate getCsSmsTemplate(Map params){
		com.ccclubs.service.admin.ICsSmsTemplateService csSmsTemplateService = $.GetSpringBean("csSmsTemplateService");
		return csSmsTemplateService.getCsSmsTemplate(params);
	}
	
	/**
	 * 获取短信模版数
	 * @return
	 */
	public static @api Long getCsSmsTemplateCount(Map params){
		com.ccclubs.service.admin.ICsSmsTemplateService csSmsTemplateService = $.GetSpringBean("csSmsTemplateService");
		return csSmsTemplateService.getCsSmsTemplateCount(params);
	}
		
		
	/**
	 * 获取短信模版自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsSmsTemplateEval(String eval,Map params){
		com.ccclubs.service.admin.ICsSmsTemplateService csSmsTemplateService = $.GetSpringBean("csSmsTemplateService");
		return csSmsTemplateService.getCsSmsTemplateEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsSmsTemplate(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsSmsTemplateService csSmsTemplateService = $.GetSpringBean("csSmsTemplateService");
		csSmsTemplateService.updateCsSmsTemplateByConfirm(set, where);
	}
	
	
	/**
	 * 保存短信模版对象
	 * @param params
	 * @return
	 */
	public CsSmsTemplate save(){
		com.ccclubs.service.admin.ICsSmsTemplateService csSmsTemplateService = $.GetSpringBean("csSmsTemplateService");
		if(this.getCsstId()!=null)
			csSmsTemplateService.updateCsSmsTemplate(this);
		else
			return csSmsTemplateService.saveCsSmsTemplate(this);
		return this;
	}
	
	
	/**
	 * 更新短信模版对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsSmsTemplateService csSmsTemplateService = $.GetSpringBean("csSmsTemplateService");
		csSmsTemplateService.updateCsSmsTemplate$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsSmsTemplateService csSmsTemplateService = $.GetSpringBean("csSmsTemplateService");
		if($.equals($.config("logic_delete"),"true"))
			csSmsTemplateService.removeCsSmsTemplateById(this.getCsstId());
		else
			csSmsTemplateService.deleteCsSmsTemplateById(this.getCsstId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsSmsTemplateService csSmsTemplateService = $.GetSpringBean("csSmsTemplateService");
		return csSmsTemplateService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsstId(){
		return this.csstId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsstId$(){
		String strValue="";
		 strValue=$.str(this.getCsstId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsstId(Long csstId){
		this.csstId = csstId;
		this.setSeted(F.csstId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsstHost(){
		return this.csstHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsstHost$(){
		String strValue="";
		if(this.getCsstHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsstHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsstHost(Long csstHost){
		this.csstHost = csstHost;
		this.setSeted(F.csstHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csstHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsstHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsstHost()!=null){
 			srvHost = SrvHost.get(this.getCsstHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsstHost(), srvHost);
	 	return srvHost;
	}
	/*******************************模版名称**********************************/	
	/**
	* 模版名称 [非空]      
	**/
	public String getCsstName(){
		return this.csstName;
	}
	/**
	* 获取模版名称格式化(toString)
	**/
	public String getCsstName$(){
		String strValue="";
		 strValue=$.str(this.getCsstName());
	 	 return strValue;
	}
	/**
	* 模版名称 [非空]      
	**/
	public void setCsstName(String csstName){
		this.csstName = csstName;
		this.setSeted(F.csstName);
	}
	/*******************************模版内容**********************************/	
	/**
	* 模版内容 [非空]      
	**/
	public String getCsstContent(){
		return this.csstContent;
	}
	/**
	* 获取模版内容格式化(toString)
	**/
	public String getCsstContent$(){
		String strValue="";
		 strValue=$.str(this.getCsstContent());
	 	 return strValue;
	}
	/**
	* 模版内容 [非空]      
	**/
	public void setCsstContent(String csstContent){
		this.csstContent = csstContent;
		this.setSeted(F.csstContent);
	}
	/*******************************短信类型**********************************/	
	/**
	* 短信类型 [非空]   1:代码类短信 2:通知类短信 3:提示类短信 4:广告类短信    
	**/
	public Long getCsstType(){
		return this.csstType;
	}
	/**
	* 获取短信类型格式化(toString)
	**/
	public String getCsstType$(){
		String strValue="";
		 if($.equals($.str(this.getCsstType()),"1"))
			strValue=$.str("代码类短信");		 
		 if($.equals($.str(this.getCsstType()),"2"))
			strValue=$.str("通知类短信");		 
		 if($.equals($.str(this.getCsstType()),"3"))
			strValue=$.str("提示类短信");		 
		 if($.equals($.str(this.getCsstType()),"4"))
			strValue=$.str("广告类短信");		 
	 	 return strValue;
	}
	/**
	* 短信类型 [非空]   1:代码类短信 2:通知类短信 3:提示类短信 4:广告类短信    
	**/
	public void setCsstType(Long csstType){
		this.csstType = csstType;
		this.setSeted(F.csstType);
	}
	/*******************************模版标识**********************************/	
	/**
	* 模版标识       
	**/
	public String getCsstFlag(){
		return this.csstFlag;
	}
	/**
	* 获取模版标识格式化(toString)
	**/
	public String getCsstFlag$(){
		String strValue="";
		 strValue=$.str(this.getCsstFlag());
	 	 return strValue;
	}
	/**
	* 模版标识       
	**/
	public void setCsstFlag(String csstFlag){
		this.csstFlag = csstFlag;
		this.setSeted(F.csstFlag);
	}
	/*******************************添加人**********************************/	
	/**
	* 添加人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsstEditor(){
		return this.csstEditor;
	}
	/**
	* 获取添加人格式化(toString)
	**/
	public String getCsstEditor$(){
		String strValue="";
		if(this.getCsstEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsstEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 添加人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsstEditor(Long csstEditor){
		this.csstEditor = csstEditor;
		this.setSeted(F.csstEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csstEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsstEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsstEditor()!=null){
 			srvUser = SrvUser.get(this.getCsstEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCsstEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsstUpdateTime(){
		return this.csstUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsstUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsstUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsstUpdateTime(Date csstUpdateTime){
		this.csstUpdateTime = csstUpdateTime;
		this.setSeted(F.csstUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsstAddTime(){
		return this.csstAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsstAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsstAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsstAddTime(Date csstAddTime){
		this.csstAddTime = csstAddTime;
		this.setSeted(F.csstAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsstStatus(){
		return this.csstStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsstStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsstStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsstStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsstStatus(Short csstStatus){
		this.csstStatus = csstStatus;
		this.setSeted(F.csstStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsSmsTemplate.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsSmsTemplate.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsSmsTemplate.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsSmsTemplate.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsSmsTemplate.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsSmsTemplate.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsSmsTemplate.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsSmsTemplate.this);
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
		public M csstId(Object csstId){this.put("csstId", csstId);return this;};
	 	/** and csst_id is null */
 		public M csstIdNull(){if(this.get("csstIdNot")==null)this.put("csstIdNot", "");this.put("csstId", null);return this;};
 		/** not .... */
 		public M csstIdNot(){this.put("csstIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csstHost(Object csstHost){this.put("csstHost", csstHost);return this;};
	 	/** and csst_host is null */
 		public M csstHostNull(){if(this.get("csstHostNot")==null)this.put("csstHostNot", "");this.put("csstHost", null);return this;};
 		/** not .... */
 		public M csstHostNot(){this.put("csstHostNot", "not");return this;};
		/** 模版名称 [非空]       **/
		public M csstName(Object csstName){this.put("csstName", csstName);return this;};
	 	/** and csst_name is null */
 		public M csstNameNull(){if(this.get("csstNameNot")==null)this.put("csstNameNot", "");this.put("csstName", null);return this;};
 		/** not .... */
 		public M csstNameNot(){this.put("csstNameNot", "not");return this;};
		/** 模版内容 [非空]       **/
		public M csstContent(Object csstContent){this.put("csstContent", csstContent);return this;};
	 	/** and csst_content is null */
 		public M csstContentNull(){if(this.get("csstContentNot")==null)this.put("csstContentNot", "");this.put("csstContent", null);return this;};
 		/** not .... */
 		public M csstContentNot(){this.put("csstContentNot", "not");return this;};
		/** 短信类型 [非空]   1:代码类短信 2:通知类短信 3:提示类短信 4:广告类短信     **/
		public M csstType(Object csstType){this.put("csstType", csstType);return this;};
	 	/** and csst_type is null */
 		public M csstTypeNull(){if(this.get("csstTypeNot")==null)this.put("csstTypeNot", "");this.put("csstType", null);return this;};
 		/** not .... */
 		public M csstTypeNot(){this.put("csstTypeNot", "not");return this;};
		/** 模版标识        **/
		public M csstFlag(Object csstFlag){this.put("csstFlag", csstFlag);return this;};
	 	/** and csst_flag is null */
 		public M csstFlagNull(){if(this.get("csstFlagNot")==null)this.put("csstFlagNot", "");this.put("csstFlag", null);return this;};
 		/** not .... */
 		public M csstFlagNot(){this.put("csstFlagNot", "not");return this;};
		/** 添加人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csstEditor(Object csstEditor){this.put("csstEditor", csstEditor);return this;};
	 	/** and csst_editor is null */
 		public M csstEditorNull(){if(this.get("csstEditorNot")==null)this.put("csstEditorNot", "");this.put("csstEditor", null);return this;};
 		/** not .... */
 		public M csstEditorNot(){this.put("csstEditorNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csstUpdateTime(Object csstUpdateTime){this.put("csstUpdateTime", csstUpdateTime);return this;};
	 	/** and csst_update_time is null */
 		public M csstUpdateTimeNull(){if(this.get("csstUpdateTimeNot")==null)this.put("csstUpdateTimeNot", "");this.put("csstUpdateTime", null);return this;};
 		/** not .... */
 		public M csstUpdateTimeNot(){this.put("csstUpdateTimeNot", "not");return this;};
 		/** and csst_update_time >= ? */
 		public M csstUpdateTimeStart(Object start){this.put("csstUpdateTimeStart", start);return this;};			
 		/** and csst_update_time <= ? */
 		public M csstUpdateTimeEnd(Object end){this.put("csstUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csstAddTime(Object csstAddTime){this.put("csstAddTime", csstAddTime);return this;};
	 	/** and csst_add_time is null */
 		public M csstAddTimeNull(){if(this.get("csstAddTimeNot")==null)this.put("csstAddTimeNot", "");this.put("csstAddTime", null);return this;};
 		/** not .... */
 		public M csstAddTimeNot(){this.put("csstAddTimeNot", "not");return this;};
 		/** and csst_add_time >= ? */
 		public M csstAddTimeStart(Object start){this.put("csstAddTimeStart", start);return this;};			
 		/** and csst_add_time <= ? */
 		public M csstAddTimeEnd(Object end){this.put("csstAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csstStatus(Object csstStatus){this.put("csstStatus", csstStatus);return this;};
	 	/** and csst_status is null */
 		public M csstStatusNull(){if(this.get("csstStatusNot")==null)this.put("csstStatusNot", "");this.put("csstStatus", null);return this;};
 		/** not .... */
 		public M csstStatusNot(){this.put("csstStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有短信模版 **/
		public @api List<CsSmsTemplate> list(Integer size){
			return getCsSmsTemplateList(this,size);
		}
		/** 获取短信模版分页 **/
		public @api Page<CsSmsTemplate> page(int page,int size){
			return getCsSmsTemplatePage(page, size , this);
		}
		/** 根据查询条件取短信模版 **/
		public @api CsSmsTemplate get(){
			return getCsSmsTemplate(this);
		}
		/** 获取短信模版数 **/
		public @api Long count(){
			return getCsSmsTemplateCount(this);
		}
		/** 获取短信模版表达式 **/
		public @api <T> T eval(String strEval){
			return getCsSmsTemplateEval(strEval,this);
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
			updateCsSmsTemplate(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csstId="csstId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csstHost="csstHost";
		/** 模版名称 [非空]       **/
		public final static @type(String.class) @like String csstName="csstName";
		/** 模版内容 [非空]       **/
		public final static @type(String.class) @like String csstContent="csstContent";
		/** 短信类型 [非空]   1:代码类短信 2:通知类短信 3:提示类短信 4:广告类短信     **/
		public final static @type(Long.class)  String csstType="csstType";
		/** 模版标识        **/
		public final static @type(String.class) @like String csstFlag="csstFlag";
		/** 添加人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csstEditor="csstEditor";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csstUpdateTime="csstUpdateTime";
	 	/** and csst_update_time >= ? */
 		public final static @type(Date.class) String csstUpdateTimeStart="csstUpdateTimeStart";
 		/** and csst_update_time <= ? */
 		public final static @type(Date.class) String csstUpdateTimeEnd="csstUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csstAddTime="csstAddTime";
	 	/** and csst_add_time >= ? */
 		public final static @type(Date.class) String csstAddTimeStart="csstAddTimeStart";
 		/** and csst_add_time <= ? */
 		public final static @type(Date.class) String csstAddTimeEnd="csstAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csstStatus="csstStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csstId="csst_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csstHost="csst_host";
		/** 模版名称 [非空]       **/
		public final static String csstName="csst_name";
		/** 模版内容 [非空]       **/
		public final static String csstContent="csst_content";
		/** 短信类型 [非空]   1:代码类短信 2:通知类短信 3:提示类短信 4:广告类短信     **/
		public final static String csstType="csst_type";
		/** 模版标识        **/
		public final static String csstFlag="csst_flag";
		/** 添加人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csstEditor="csst_editor";
		/** 修改时间 [非空]       **/
		public final static String csstUpdateTime="csst_update_time";
		/** 添加时间 [非空]       **/
		public final static String csstAddTime="csst_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csstStatus="csst_status";
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
				$.Set(name,CsSmsTemplate.getCsSmsTemplate(params));
			else
				$.Set(name,CsSmsTemplate.getCsSmsTemplateList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取短信模版数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsSmsTemplate) $.GetRequest("CsSmsTemplate$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsSmsTemplate.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsSmsTemplate.getCsSmsTemplate(params);
			else
				value = CsSmsTemplate.getCsSmsTemplateList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsSmsTemplate.Get($.add(CsSmsTemplate.F.csstId,param));
		else if(!$.empty(param.toString()))
			value = CsSmsTemplate.get(Long.valueOf(param.toString()));
		$.SetRequest("CsSmsTemplate$"+param.hashCode(), value);
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
	public void mergeSet(CsSmsTemplate old){
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