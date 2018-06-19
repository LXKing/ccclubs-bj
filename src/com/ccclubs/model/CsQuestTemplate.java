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

@namespace("csm/revisit/quest")
public @caption("问卷模板") @table("cs_quest_template") class CsQuestTemplate implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csqt_id")   @primary  @note("  ") Long csqtId;// 主键 非空     
	private @caption("模板名称") @column("csqt_name")    @note("  ") String csqtName;// 非空     
	private @caption("类型") @column("csqt_type")    @note(" 0:调查报告表 1:客户回访表 2:投诉反馈表  ") Short csqtType;// 非空 0:调查报告表 1:客户回访表 2:投诉反馈表     
	private @caption("模板设计") @column("csqt_content")  @hidden   @note("  ") String csqtContent;//     
	private @caption("标识") @column("csqt_flag")    @note("  ") String csqtFlag;//     
	private @caption("修改人") @column("csqt_editor")    @relate("$csqtEditor") @RelateClass(SrvUser.class)  @note("  ") Long csqtEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csqtEditor;//关联对象[用户]
	private @caption("修改时间") @column("csqt_update_time")    @note("  ") Date csqtUpdateTime;// 非空     
	private @caption("添加时间") @column("csqt_add_time")    @note("  ") Date csqtAddTime;// 非空     
	private @caption("状态") @column("csqt_status")    @note(" 1:正常 0:无效  ") Short csqtStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsQuestTemplate(){
	
	}
	
	//主键构造函数
	public CsQuestTemplate(Long id){
		this.csqtId = id;
	}
	
	/**所有字段构造函数 CsQuestTemplate(csqtName,csqtType,csqtContent,csqtFlag,csqtEditor,csqtUpdateTime,csqtAddTime,csqtStatus)
	 CsQuestTemplate(
	 	$.getString("csqtName")//模板名称 [非空]
	 	,$.getShort("csqtType")//类型 [非空]
	 	,$.getString("csqtContent")//模板设计
	 	,$.getString("csqtFlag")//标识
	 	,$.getLong("csqtEditor")//修改人 [非空]
	 	,$.getDate("csqtUpdateTime")//修改时间 [非空]
	 	,$.getDate("csqtAddTime")//添加时间 [非空]
	 	,$.getShort("csqtStatus")//状态 [非空]
	 )
	**/
	public CsQuestTemplate(String csqtName,Short csqtType,String csqtContent,String csqtFlag,Long csqtEditor,Date csqtUpdateTime,Date csqtAddTime,Short csqtStatus){
		this.csqtName=csqtName;
		this.csqtType=csqtType;
		this.csqtContent=csqtContent;
		this.csqtFlag=csqtFlag;
		this.csqtEditor=csqtEditor;
		this.csqtUpdateTime=csqtUpdateTime;
		this.csqtAddTime=csqtAddTime;
		this.csqtStatus=csqtStatus;
	}
	
	//设置非空字段
	public CsQuestTemplate setNotNull(String csqtName,Short csqtType,Long csqtEditor,Date csqtUpdateTime,Date csqtAddTime,Short csqtStatus){
		this.csqtName=csqtName;
		this.csqtType=csqtType;
		this.csqtEditor=csqtEditor;
		this.csqtUpdateTime=csqtUpdateTime;
		this.csqtAddTime=csqtAddTime;
		this.csqtStatus=csqtStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsQuestTemplate csqtId(Long csqtId){
		this.csqtId = csqtId;
		this.setSeted(F.csqtId);
		return this;
	}
	/** 模板名称 [非空]       **/
	public CsQuestTemplate csqtName(String csqtName){
		this.csqtName = csqtName;
		this.setSeted(F.csqtName);
		return this;
	}
	/** 类型 [非空]   0:调查报告表 1:客户回访表 2:投诉反馈表     **/
	public CsQuestTemplate csqtType(Short csqtType){
		this.csqtType = csqtType;
		this.setSeted(F.csqtType);
		return this;
	}
	/** 模板设计        **/
	public CsQuestTemplate csqtContent(String csqtContent){
		this.csqtContent = csqtContent;
		this.setSeted(F.csqtContent);
		return this;
	}
	/** 标识        **/
	public CsQuestTemplate csqtFlag(String csqtFlag){
		this.csqtFlag = csqtFlag;
		this.setSeted(F.csqtFlag);
		return this;
	}
	/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsQuestTemplate csqtEditor(Long csqtEditor){
		this.csqtEditor = csqtEditor;
		this.setSeted(F.csqtEditor);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsQuestTemplate csqtUpdateTime(Date csqtUpdateTime){
		this.csqtUpdateTime = csqtUpdateTime;
		this.setSeted(F.csqtUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsQuestTemplate csqtAddTime(Date csqtAddTime){
		this.csqtAddTime = csqtAddTime;
		this.setSeted(F.csqtAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsQuestTemplate csqtStatus(Short csqtStatus){
		this.csqtStatus = csqtStatus;
		this.setSeted(F.csqtStatus);
		return this;
	}
	
	
	//克隆对象
	public CsQuestTemplate clone(){
		CsQuestTemplate clone = new CsQuestTemplate();
		clone.csqtName=this.csqtName;
		clone.csqtType=this.csqtType;
		clone.csqtEditor=this.csqtEditor;
		clone.csqtUpdateTime=this.csqtUpdateTime;
		clone.csqtAddTime=this.csqtAddTime;
		clone.csqtStatus=this.csqtStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取问卷模板
	 * @param id
	 * @return
	 */
	public static @api CsQuestTemplate get(Long id){		
		return getCsQuestTemplateById(id);
	}
	/**
	 * 获取所有问卷模板
	 * @return
	 */
	public static @api List<CsQuestTemplate> list(Map params,Integer size){
		return getCsQuestTemplateList(params,size);
	}
	/**
	 * 获取问卷模板分页
	 * @return
	 */
	public static @api Page<CsQuestTemplate> page(int page,int size,Map params){
		return getCsQuestTemplatePage(page, size , params);
	}
	/**
	 * 根据查询条件取问卷模板
	 * @param params
	 * @return
	 */
	public static @api CsQuestTemplate Get(Map params){
		return getCsQuestTemplate(params);
	}
	/**
	 * 获取问卷模板数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsQuestTemplateCount(params);
	}
	/**
	 * 获取问卷模板数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsQuestTemplateEval(eval,params);
	}
	
	/**
	 * 根据ID取问卷模板
	 * @param id
	 * @return
	 */
	public static @api CsQuestTemplate getCsQuestTemplateById(Long id){		
		CsQuestTemplate csQuestTemplate = (CsQuestTemplate) $.GetRequest("CsQuestTemplate$"+id);
		if(csQuestTemplate!=null)
			return csQuestTemplate;
		com.ccclubs.service.admin.ICsQuestTemplateService csQuestTemplateService = $.GetSpringBean("csQuestTemplateService");		
		return csQuestTemplateService.getCsQuestTemplateById(id);
	}
	
	
	/**
	 * 根据ID取问卷模板的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsQuestTemplate.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsQuestTemplate csQuestTemplate = get(id);
		if(csQuestTemplate!=null){
			String strValue = csQuestTemplate.getCsqtName$();
			if(!"CsqtName".equals("CsqtName"))
				strValue+="("+csQuestTemplate.getCsqtName$()+")";
			MemCache.setValue(CsQuestTemplate.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsqtName$();
		if(!"CsqtName".equals("CsqtName"))
			keyValue+="("+this.getCsqtName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有问卷模板
	 * @return
	 */
	public static @api List<CsQuestTemplate> getCsQuestTemplateList(Map params,Integer size){
		com.ccclubs.service.admin.ICsQuestTemplateService csQuestTemplateService = $.GetSpringBean("csQuestTemplateService");
		return csQuestTemplateService.getCsQuestTemplateList(params, size);
	}
	
	/**
	 * 获取问卷模板分页
	 * @return
	 */
	public static @api Page<CsQuestTemplate> getCsQuestTemplatePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsQuestTemplateService csQuestTemplateService = $.GetSpringBean("csQuestTemplateService");
		return csQuestTemplateService.getCsQuestTemplatePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取问卷模板
	 * @param params
	 * @return
	 */
	public static @api CsQuestTemplate getCsQuestTemplate(Map params){
		com.ccclubs.service.admin.ICsQuestTemplateService csQuestTemplateService = $.GetSpringBean("csQuestTemplateService");
		return csQuestTemplateService.getCsQuestTemplate(params);
	}
	
	/**
	 * 获取问卷模板数
	 * @return
	 */
	public static @api Long getCsQuestTemplateCount(Map params){
		com.ccclubs.service.admin.ICsQuestTemplateService csQuestTemplateService = $.GetSpringBean("csQuestTemplateService");
		return csQuestTemplateService.getCsQuestTemplateCount(params);
	}
		
		
	/**
	 * 获取问卷模板自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsQuestTemplateEval(String eval,Map params){
		com.ccclubs.service.admin.ICsQuestTemplateService csQuestTemplateService = $.GetSpringBean("csQuestTemplateService");
		return csQuestTemplateService.getCsQuestTemplateEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsQuestTemplate(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsQuestTemplateService csQuestTemplateService = $.GetSpringBean("csQuestTemplateService");
		csQuestTemplateService.updateCsQuestTemplateByConfirm(set, where);
	}
	
	
	/**
	 * 保存问卷模板对象
	 * @param params
	 * @return
	 */
	public CsQuestTemplate save(){
		com.ccclubs.service.admin.ICsQuestTemplateService csQuestTemplateService = $.GetSpringBean("csQuestTemplateService");
		if(this.getCsqtId()!=null)
			csQuestTemplateService.updateCsQuestTemplate(this);
		else
			return csQuestTemplateService.saveCsQuestTemplate(this);
		return this;
	}
	
	
	/**
	 * 更新问卷模板对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsQuestTemplateService csQuestTemplateService = $.GetSpringBean("csQuestTemplateService");
		csQuestTemplateService.updateCsQuestTemplate$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsQuestTemplateService csQuestTemplateService = $.GetSpringBean("csQuestTemplateService");
		if($.equals($.config("logic_delete"),"true"))
			csQuestTemplateService.removeCsQuestTemplateById(this.getCsqtId());
		else
			csQuestTemplateService.deleteCsQuestTemplateById(this.getCsqtId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsQuestTemplateService csQuestTemplateService = $.GetSpringBean("csQuestTemplateService");
		return csQuestTemplateService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsqtId(){
		return this.csqtId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsqtId$(){
		String strValue="";
		 strValue=$.str(this.getCsqtId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsqtId(Long csqtId){
		this.csqtId = csqtId;
		this.setSeted(F.csqtId);
	}
	/*******************************模板名称**********************************/	
	/**
	* 模板名称 [非空]      
	**/
	public String getCsqtName(){
		return this.csqtName;
	}
	/**
	* 获取模板名称格式化(toString)
	**/
	public String getCsqtName$(){
		String strValue="";
		 strValue=$.str(this.getCsqtName());
	 	 return strValue;
	}
	/**
	* 模板名称 [非空]      
	**/
	public void setCsqtName(String csqtName){
		this.csqtName = csqtName;
		this.setSeted(F.csqtName);
	}
	/*******************************类型**********************************/	
	/**
	* 类型 [非空]   0:调查报告表 1:客户回访表 2:投诉反馈表    
	**/
	public Short getCsqtType(){
		return this.csqtType;
	}
	/**
	* 获取类型格式化(toString)
	**/
	public String getCsqtType$(){
		String strValue="";
		 if($.equals($.str(this.getCsqtType()),"0"))
			strValue=$.str("调查报告表");		 
		 if($.equals($.str(this.getCsqtType()),"1"))
			strValue=$.str("客户回访表");		 
		 if($.equals($.str(this.getCsqtType()),"2"))
			strValue=$.str("投诉反馈表");		 
	 	 return strValue;
	}
	/**
	* 类型 [非空]   0:调查报告表 1:客户回访表 2:投诉反馈表    
	**/
	public void setCsqtType(Short csqtType){
		this.csqtType = csqtType;
		this.setSeted(F.csqtType);
	}
	/*******************************模板设计**********************************/	
	/**
	* 模板设计       
	**/
	public String getCsqtContent(){
		return this.csqtContent;
	}
	/**
	* 获取模板设计格式化(toString)
	**/
	public String getCsqtContent$(){
		String strValue="";
		 strValue=$.str(this.getCsqtContent());
	 	 return strValue;
	}
	/**
	* 模板设计       
	**/
	public void setCsqtContent(String csqtContent){
		this.csqtContent = csqtContent;
		this.setSeted(F.csqtContent);
	}
	/*******************************标识**********************************/	
	/**
	* 标识       
	**/
	public String getCsqtFlag(){
		return this.csqtFlag;
	}
	/**
	* 获取标识格式化(toString)
	**/
	public String getCsqtFlag$(){
		String strValue="";
		 strValue=$.str(this.getCsqtFlag());
	 	 return strValue;
	}
	/**
	* 标识       
	**/
	public void setCsqtFlag(String csqtFlag){
		this.csqtFlag = csqtFlag;
		this.setSeted(F.csqtFlag);
	}
	/*******************************修改人**********************************/	
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsqtEditor(){
		return this.csqtEditor;
	}
	/**
	* 获取修改人格式化(toString)
	**/
	public String getCsqtEditor$(){
		String strValue="";
		if(this.getCsqtEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsqtEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsqtEditor(Long csqtEditor){
		this.csqtEditor = csqtEditor;
		this.setSeted(F.csqtEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csqtEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsqtEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsqtEditor()!=null){
 			srvUser = SrvUser.get(this.getCsqtEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCsqtEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsqtUpdateTime(){
		return this.csqtUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsqtUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsqtUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsqtUpdateTime(Date csqtUpdateTime){
		this.csqtUpdateTime = csqtUpdateTime;
		this.setSeted(F.csqtUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsqtAddTime(){
		return this.csqtAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsqtAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsqtAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsqtAddTime(Date csqtAddTime){
		this.csqtAddTime = csqtAddTime;
		this.setSeted(F.csqtAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsqtStatus(){
		return this.csqtStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsqtStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsqtStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsqtStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsqtStatus(Short csqtStatus){
		this.csqtStatus = csqtStatus;
		this.setSeted(F.csqtStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsQuestTemplate.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsQuestTemplate.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsQuestTemplate.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsQuestTemplate.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsQuestTemplate.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsQuestTemplate.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsQuestTemplate.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsQuestTemplate.this);
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
		public M csqtId(Object csqtId){this.put("csqtId", csqtId);return this;};
	 	/** and csqt_id is null */
 		public M csqtIdNull(){if(this.get("csqtIdNot")==null)this.put("csqtIdNot", "");this.put("csqtId", null);return this;};
 		/** not .... */
 		public M csqtIdNot(){this.put("csqtIdNot", "not");return this;};
		/** 模板名称 [非空]       **/
		public M csqtName(Object csqtName){this.put("csqtName", csqtName);return this;};
	 	/** and csqt_name is null */
 		public M csqtNameNull(){if(this.get("csqtNameNot")==null)this.put("csqtNameNot", "");this.put("csqtName", null);return this;};
 		/** not .... */
 		public M csqtNameNot(){this.put("csqtNameNot", "not");return this;};
		/** 类型 [非空]   0:调查报告表 1:客户回访表 2:投诉反馈表     **/
		public M csqtType(Object csqtType){this.put("csqtType", csqtType);return this;};
	 	/** and csqt_type is null */
 		public M csqtTypeNull(){if(this.get("csqtTypeNot")==null)this.put("csqtTypeNot", "");this.put("csqtType", null);return this;};
 		/** not .... */
 		public M csqtTypeNot(){this.put("csqtTypeNot", "not");return this;};
		/** 模板设计        **/
		public M csqtContent(Object csqtContent){this.put("csqtContent", csqtContent);return this;};
	 	/** and csqt_content is null */
 		public M csqtContentNull(){if(this.get("csqtContentNot")==null)this.put("csqtContentNot", "");this.put("csqtContent", null);return this;};
 		/** not .... */
 		public M csqtContentNot(){this.put("csqtContentNot", "not");return this;};
		/** 标识        **/
		public M csqtFlag(Object csqtFlag){this.put("csqtFlag", csqtFlag);return this;};
	 	/** and csqt_flag is null */
 		public M csqtFlagNull(){if(this.get("csqtFlagNot")==null)this.put("csqtFlagNot", "");this.put("csqtFlag", null);return this;};
 		/** not .... */
 		public M csqtFlagNot(){this.put("csqtFlagNot", "not");return this;};
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csqtEditor(Object csqtEditor){this.put("csqtEditor", csqtEditor);return this;};
	 	/** and csqt_editor is null */
 		public M csqtEditorNull(){if(this.get("csqtEditorNot")==null)this.put("csqtEditorNot", "");this.put("csqtEditor", null);return this;};
 		/** not .... */
 		public M csqtEditorNot(){this.put("csqtEditorNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csqtUpdateTime(Object csqtUpdateTime){this.put("csqtUpdateTime", csqtUpdateTime);return this;};
	 	/** and csqt_update_time is null */
 		public M csqtUpdateTimeNull(){if(this.get("csqtUpdateTimeNot")==null)this.put("csqtUpdateTimeNot", "");this.put("csqtUpdateTime", null);return this;};
 		/** not .... */
 		public M csqtUpdateTimeNot(){this.put("csqtUpdateTimeNot", "not");return this;};
 		/** and csqt_update_time >= ? */
 		public M csqtUpdateTimeStart(Object start){this.put("csqtUpdateTimeStart", start);return this;};			
 		/** and csqt_update_time <= ? */
 		public M csqtUpdateTimeEnd(Object end){this.put("csqtUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csqtAddTime(Object csqtAddTime){this.put("csqtAddTime", csqtAddTime);return this;};
	 	/** and csqt_add_time is null */
 		public M csqtAddTimeNull(){if(this.get("csqtAddTimeNot")==null)this.put("csqtAddTimeNot", "");this.put("csqtAddTime", null);return this;};
 		/** not .... */
 		public M csqtAddTimeNot(){this.put("csqtAddTimeNot", "not");return this;};
 		/** and csqt_add_time >= ? */
 		public M csqtAddTimeStart(Object start){this.put("csqtAddTimeStart", start);return this;};			
 		/** and csqt_add_time <= ? */
 		public M csqtAddTimeEnd(Object end){this.put("csqtAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csqtStatus(Object csqtStatus){this.put("csqtStatus", csqtStatus);return this;};
	 	/** and csqt_status is null */
 		public M csqtStatusNull(){if(this.get("csqtStatusNot")==null)this.put("csqtStatusNot", "");this.put("csqtStatus", null);return this;};
 		/** not .... */
 		public M csqtStatusNot(){this.put("csqtStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有问卷模板 **/
		public @api List<CsQuestTemplate> list(Integer size){
			return getCsQuestTemplateList(this,size);
		}
		/** 获取问卷模板分页 **/
		public @api Page<CsQuestTemplate> page(int page,int size){
			return getCsQuestTemplatePage(page, size , this);
		}
		/** 根据查询条件取问卷模板 **/
		public @api CsQuestTemplate get(){
			return getCsQuestTemplate(this);
		}
		/** 获取问卷模板数 **/
		public @api Long count(){
			return getCsQuestTemplateCount(this);
		}
		/** 获取问卷模板表达式 **/
		public @api <T> T eval(String strEval){
			return getCsQuestTemplateEval(strEval,this);
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
			updateCsQuestTemplate(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csqtId="csqtId";
		/** 模板名称 [非空]       **/
		public final static @type(String.class) @like String csqtName="csqtName";
		/** 类型 [非空]   0:调查报告表 1:客户回访表 2:投诉反馈表     **/
		public final static @type(Short.class)  String csqtType="csqtType";
		/** 模板设计        **/
		public final static @type(String.class) @like String csqtContent="csqtContent";
		/** 标识        **/
		public final static @type(String.class) @like String csqtFlag="csqtFlag";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csqtEditor="csqtEditor";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csqtUpdateTime="csqtUpdateTime";
	 	/** and csqt_update_time >= ? */
 		public final static @type(Date.class) String csqtUpdateTimeStart="csqtUpdateTimeStart";
 		/** and csqt_update_time <= ? */
 		public final static @type(Date.class) String csqtUpdateTimeEnd="csqtUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csqtAddTime="csqtAddTime";
	 	/** and csqt_add_time >= ? */
 		public final static @type(Date.class) String csqtAddTimeStart="csqtAddTimeStart";
 		/** and csqt_add_time <= ? */
 		public final static @type(Date.class) String csqtAddTimeEnd="csqtAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csqtStatus="csqtStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csqtId="csqt_id";
		/** 模板名称 [非空]       **/
		public final static String csqtName="csqt_name";
		/** 类型 [非空]   0:调查报告表 1:客户回访表 2:投诉反馈表     **/
		public final static String csqtType="csqt_type";
		/** 模板设计        **/
		public final static String csqtContent="csqt_content";
		/** 标识        **/
		public final static String csqtFlag="csqt_flag";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csqtEditor="csqt_editor";
		/** 修改时间 [非空]       **/
		public final static String csqtUpdateTime="csqt_update_time";
		/** 添加时间 [非空]       **/
		public final static String csqtAddTime="csqt_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csqtStatus="csqt_status";
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
				$.Set(name,CsQuestTemplate.getCsQuestTemplate(params));
			else
				$.Set(name,CsQuestTemplate.getCsQuestTemplateList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取问卷模板数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsQuestTemplate) $.GetRequest("CsQuestTemplate$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsQuestTemplate.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsQuestTemplate.getCsQuestTemplate(params);
			else
				value = CsQuestTemplate.getCsQuestTemplateList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsQuestTemplate.Get($.add(CsQuestTemplate.F.csqtId,param));
		else if(!$.empty(param.toString()))
			value = CsQuestTemplate.get(Long.valueOf(param.toString()));
		$.SetRequest("CsQuestTemplate$"+param.hashCode(), value);
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
	public void mergeSet(CsQuestTemplate old){
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