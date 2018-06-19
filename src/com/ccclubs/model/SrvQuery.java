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

@namespace("configurator/query")
public @caption("表单查询") @table("srv_query") class SrvQuery implements java.io.Serializable
{
	private static final long serialVersionUID =          1l;
	private @caption("编号") @column("sq_id")   @primary  @note("  ") Long sqId;// 主键 非空     
	private @caption("查询名称") @column("sq_name")    @note("  ") String sqName;// 非空     
	private @caption("图标") @column("sq_icon")    @note("  ") String sqIcon;// 非空     
	private @caption("项目路径") @column("sq_path")    @note("  ") String sqPath;// 非空     
	private @caption("查询参数") @column("sq_params")  @hidden   @note("  ") String sqParams;// 非空     
	private @caption("添加人") @column("sq_editor")    @relate("$sqEditor") @RelateClass(SrvUser.class)  @note("  ") Long sqEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $sqEditor;//关联对象[用户]
	private @caption("修改时间") @column("sq_update_time")    @note("  ") Date sqUpdateTime;// 非空     
	private @caption("添加时间") @column("sq_add_time")    @note("  ") Date sqAddTime;// 非空     
	private @caption("状态") @column("sq_status")    @note(" 1:正常 0:无效  ") Short sqStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public SrvQuery(){
	
	}
	
	//主键构造函数
	public SrvQuery(Long id){
		this.sqId = id;
	}
	
	/**所有字段构造函数 SrvQuery(sqName,sqIcon,sqPath,sqParams,sqEditor,sqUpdateTime,sqAddTime,sqStatus)
	 SrvQuery(
	 	$.getString("sqName")//查询名称 [非空]
	 	,$.getString("sqIcon")//图标 [非空]
	 	,$.getString("sqPath")//项目路径 [非空]
	 	,$.getString("sqParams")//查询参数 [非空]
	 	,$.getLong("sqEditor")//添加人 [非空]
	 	,$.getDate("sqUpdateTime")//修改时间 [非空]
	 	,$.getDate("sqAddTime")//添加时间 [非空]
	 	,$.getShort("sqStatus")//状态 [非空]
	 )
	**/
	public SrvQuery(String sqName,String sqIcon,String sqPath,String sqParams,Long sqEditor,Date sqUpdateTime,Date sqAddTime,Short sqStatus){
		this.sqName=sqName;
		this.sqIcon=sqIcon;
		this.sqPath=sqPath;
		this.sqParams=sqParams;
		this.sqEditor=sqEditor;
		this.sqUpdateTime=sqUpdateTime;
		this.sqAddTime=sqAddTime;
		this.sqStatus=sqStatus;
	}
	
	//设置非空字段
	public SrvQuery setNotNull(String sqName,String sqIcon,String sqPath,String sqParams,Long sqEditor,Date sqUpdateTime,Date sqAddTime,Short sqStatus){
		this.sqName=sqName;
		this.sqIcon=sqIcon;
		this.sqPath=sqPath;
		this.sqParams=sqParams;
		this.sqEditor=sqEditor;
		this.sqUpdateTime=sqUpdateTime;
		this.sqAddTime=sqAddTime;
		this.sqStatus=sqStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public SrvQuery sqId(Long sqId){
		this.sqId = sqId;
		this.setSeted(F.sqId);
		return this;
	}
	/** 查询名称 [非空]       **/
	public SrvQuery sqName(String sqName){
		this.sqName = sqName;
		this.setSeted(F.sqName);
		return this;
	}
	/** 图标 [非空]       **/
	public SrvQuery sqIcon(String sqIcon){
		this.sqIcon = sqIcon;
		this.setSeted(F.sqIcon);
		return this;
	}
	/** 项目路径 [非空]       **/
	public SrvQuery sqPath(String sqPath){
		this.sqPath = sqPath;
		this.setSeted(F.sqPath);
		return this;
	}
	/** 查询参数 [非空]       **/
	public SrvQuery sqParams(String sqParams){
		this.sqParams = sqParams;
		this.setSeted(F.sqParams);
		return this;
	}
	/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public SrvQuery sqEditor(Long sqEditor){
		this.sqEditor = sqEditor;
		this.setSeted(F.sqEditor);
		return this;
	}
	/** 修改时间 [非空]       **/
	public SrvQuery sqUpdateTime(Date sqUpdateTime){
		this.sqUpdateTime = sqUpdateTime;
		this.setSeted(F.sqUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public SrvQuery sqAddTime(Date sqAddTime){
		this.sqAddTime = sqAddTime;
		this.setSeted(F.sqAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public SrvQuery sqStatus(Short sqStatus){
		this.sqStatus = sqStatus;
		this.setSeted(F.sqStatus);
		return this;
	}
	
	
	//克隆对象
	public SrvQuery clone(){
		SrvQuery clone = new SrvQuery();
		clone.sqName=this.sqName;
		clone.sqIcon=this.sqIcon;
		clone.sqPath=this.sqPath;
		clone.sqParams=this.sqParams;
		clone.sqEditor=this.sqEditor;
		clone.sqUpdateTime=this.sqUpdateTime;
		clone.sqAddTime=this.sqAddTime;
		clone.sqStatus=this.sqStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取表单查询
	 * @param id
	 * @return
	 */
	public static @api SrvQuery get(Long id){		
		return getSrvQueryById(id);
	}
	/**
	 * 获取所有表单查询
	 * @return
	 */
	public static @api List<SrvQuery> list(Map params,Integer size){
		return getSrvQueryList(params,size);
	}
	/**
	 * 获取表单查询分页
	 * @return
	 */
	public static @api Page<SrvQuery> page(int page,int size,Map params){
		return getSrvQueryPage(page, size , params);
	}
	/**
	 * 根据查询条件取表单查询
	 * @param params
	 * @return
	 */
	public static @api SrvQuery Get(Map params){
		return getSrvQuery(params);
	}
	/**
	 * 获取表单查询数
	 * @return
	 */
	public static @api Long count(Map params){
		return getSrvQueryCount(params);
	}
	/**
	 * 获取表单查询数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getSrvQueryEval(eval,params);
	}
	
	/**
	 * 根据ID取表单查询
	 * @param id
	 * @return
	 */
	public static @api SrvQuery getSrvQueryById(Long id){		
		SrvQuery srvQuery = (SrvQuery) $.GetRequest("SrvQuery$"+id);
		if(srvQuery!=null)
			return srvQuery;
		com.ccclubs.service.admin.ISrvQueryService srvQueryService = $.GetSpringBean("srvQueryService");		
		return srvQueryService.getSrvQueryById(id);
	}
	
	
	/**
	 * 根据ID取表单查询的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(SrvQuery.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		SrvQuery srvQuery = get(id);
		if(srvQuery!=null){
			String strValue = srvQuery.getSqName$();
			if(!"SqName".equals("SqName"))
				strValue+="("+srvQuery.getSqName$()+")";
			MemCache.setValue(SrvQuery.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getSqName$();
		if(!"SqName".equals("SqName"))
			keyValue+="("+this.getSqName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有表单查询
	 * @return
	 */
	public static @api List<SrvQuery> getSrvQueryList(Map params,Integer size){
		com.ccclubs.service.admin.ISrvQueryService srvQueryService = $.GetSpringBean("srvQueryService");
		return srvQueryService.getSrvQueryList(params, size);
	}
	
	/**
	 * 获取表单查询分页
	 * @return
	 */
	public static @api Page<SrvQuery> getSrvQueryPage(int page,int size,Map params){
		com.ccclubs.service.admin.ISrvQueryService srvQueryService = $.GetSpringBean("srvQueryService");
		return srvQueryService.getSrvQueryPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取表单查询
	 * @param params
	 * @return
	 */
	public static @api SrvQuery getSrvQuery(Map params){
		com.ccclubs.service.admin.ISrvQueryService srvQueryService = $.GetSpringBean("srvQueryService");
		return srvQueryService.getSrvQuery(params);
	}
	
	/**
	 * 获取表单查询数
	 * @return
	 */
	public static @api Long getSrvQueryCount(Map params){
		com.ccclubs.service.admin.ISrvQueryService srvQueryService = $.GetSpringBean("srvQueryService");
		return srvQueryService.getSrvQueryCount(params);
	}
		
		
	/**
	 * 获取表单查询自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getSrvQueryEval(String eval,Map params){
		com.ccclubs.service.admin.ISrvQueryService srvQueryService = $.GetSpringBean("srvQueryService");
		return srvQueryService.getSrvQueryEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateSrvQuery(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ISrvQueryService srvQueryService = $.GetSpringBean("srvQueryService");
		srvQueryService.updateSrvQueryByConfirm(set, where);
	}
	
	
	/**
	 * 保存表单查询对象
	 * @param params
	 * @return
	 */
	public SrvQuery save(){
		com.ccclubs.service.admin.ISrvQueryService srvQueryService = $.GetSpringBean("srvQueryService");
		if(this.getSqId()!=null)
			srvQueryService.updateSrvQuery(this);
		else
			return srvQueryService.saveSrvQuery(this);
		return this;
	}
	
	
	/**
	 * 更新表单查询对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ISrvQueryService srvQueryService = $.GetSpringBean("srvQueryService");
		srvQueryService.updateSrvQuery$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ISrvQueryService srvQueryService = $.GetSpringBean("srvQueryService");
		if($.equals($.config("logic_delete"),"true"))
			srvQueryService.removeSrvQueryById(this.getSqId());
		else
			srvQueryService.deleteSrvQueryById(this.getSqId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ISrvQueryService srvQueryService = $.GetSpringBean("srvQueryService");
		return srvQueryService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getSqId(){
		return this.sqId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getSqId$(){
		String strValue="";
		 strValue=$.str(this.getSqId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setSqId(Long sqId){
		this.sqId = sqId;
		this.setSeted(F.sqId);
	}
	/*******************************查询名称**********************************/	
	/**
	* 查询名称 [非空]      
	**/
	public String getSqName(){
		return this.sqName;
	}
	/**
	* 获取查询名称格式化(toString)
	**/
	public String getSqName$(){
		String strValue="";
		 strValue=$.str(this.getSqName());
	 	 return strValue;
	}
	/**
	* 查询名称 [非空]      
	**/
	public void setSqName(String sqName){
		this.sqName = sqName;
		this.setSeted(F.sqName);
	}
	/*******************************图标**********************************/	
	/**
	* 图标 [非空]      
	**/
	public String getSqIcon(){
		return this.sqIcon;
	}
	/**
	* 获取图标格式化(toString)
	**/
	public String getSqIcon$(){
		String strValue="";
		 strValue=$.str(this.getSqIcon());		 
	 
	 	 return strValue;
	}
	/**
	* 图标 [非空]      
	**/
	public void setSqIcon(String sqIcon){
		this.sqIcon = sqIcon;
		this.setSeted(F.sqIcon);
	}
	/*******************************项目路径**********************************/	
	/**
	* 项目路径 [非空]      
	**/
	public String getSqPath(){
		return this.sqPath;
	}
	/**
	* 获取项目路径格式化(toString)
	**/
	public String getSqPath$(){
		String strValue="";
		 strValue=$.str(this.getSqPath());
	 	 return strValue;
	}
	/**
	* 项目路径 [非空]      
	**/
	public void setSqPath(String sqPath){
		this.sqPath = sqPath;
		this.setSeted(F.sqPath);
	}
	/*******************************查询参数**********************************/	
	/**
	* 查询参数 [非空]      
	**/
	public String getSqParams(){
		return this.sqParams;
	}
	/**
	* 获取查询参数格式化(toString)
	**/
	public String getSqParams$(){
		String strValue="";
		 strValue=$.str(this.getSqParams());
	 	 return strValue;
	}
	/**
	* 查询参数 [非空]      
	**/
	public void setSqParams(String sqParams){
		this.sqParams = sqParams;
		this.setSeted(F.sqParams);
	}
	/*******************************添加人**********************************/	
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getSqEditor(){
		return this.sqEditor;
	}
	/**
	* 获取添加人格式化(toString)
	**/
	public String getSqEditor$(){
		String strValue="";
		if(this.getSqEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getSqEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setSqEditor(Long sqEditor){
		this.sqEditor = sqEditor;
		this.setSeted(F.sqEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$sqEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getSqEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getSqEditor()!=null){
 			srvUser = SrvUser.get(this.getSqEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getSqEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getSqUpdateTime(){
		return this.sqUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getSqUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getSqUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setSqUpdateTime(Date sqUpdateTime){
		this.sqUpdateTime = sqUpdateTime;
		this.setSeted(F.sqUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getSqAddTime(){
		return this.sqAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getSqAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getSqAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setSqAddTime(Date sqAddTime){
		this.sqAddTime = sqAddTime;
		this.setSeted(F.sqAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getSqStatus(){
		return this.sqStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getSqStatus$(){
		String strValue="";
		 if($.equals($.str(this.getSqStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getSqStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setSqStatus(Short sqStatus){
		this.sqStatus = sqStatus;
		this.setSeted(F.sqStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${SrvQuery.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvQuery.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvQuery.class.getMethod("get$"+$.forMat(key.toString())).invoke(SrvQuery.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${SrvQuery.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvQuery.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvQuery.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(SrvQuery.this);
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
		public M sqId(Object sqId){this.put("sqId", sqId);return this;};
	 	/** and sq_id is null */
 		public M sqIdNull(){if(this.get("sqIdNot")==null)this.put("sqIdNot", "");this.put("sqId", null);return this;};
 		/** not .... */
 		public M sqIdNot(){this.put("sqIdNot", "not");return this;};
		/** 查询名称 [非空]       **/
		public M sqName(Object sqName){this.put("sqName", sqName);return this;};
	 	/** and sq_name is null */
 		public M sqNameNull(){if(this.get("sqNameNot")==null)this.put("sqNameNot", "");this.put("sqName", null);return this;};
 		/** not .... */
 		public M sqNameNot(){this.put("sqNameNot", "not");return this;};
		/** 图标 [非空]       **/
		public M sqIcon(Object sqIcon){this.put("sqIcon", sqIcon);return this;};
	 	/** and sq_icon is null */
 		public M sqIconNull(){if(this.get("sqIconNot")==null)this.put("sqIconNot", "");this.put("sqIcon", null);return this;};
 		/** not .... */
 		public M sqIconNot(){this.put("sqIconNot", "not");return this;};
		/** 项目路径 [非空]       **/
		public M sqPath(Object sqPath){this.put("sqPath", sqPath);return this;};
	 	/** and sq_path is null */
 		public M sqPathNull(){if(this.get("sqPathNot")==null)this.put("sqPathNot", "");this.put("sqPath", null);return this;};
 		/** not .... */
 		public M sqPathNot(){this.put("sqPathNot", "not");return this;};
		/** 查询参数 [非空]       **/
		public M sqParams(Object sqParams){this.put("sqParams", sqParams);return this;};
	 	/** and sq_params is null */
 		public M sqParamsNull(){if(this.get("sqParamsNot")==null)this.put("sqParamsNot", "");this.put("sqParams", null);return this;};
 		/** not .... */
 		public M sqParamsNot(){this.put("sqParamsNot", "not");return this;};
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M sqEditor(Object sqEditor){this.put("sqEditor", sqEditor);return this;};
	 	/** and sq_editor is null */
 		public M sqEditorNull(){if(this.get("sqEditorNot")==null)this.put("sqEditorNot", "");this.put("sqEditor", null);return this;};
 		/** not .... */
 		public M sqEditorNot(){this.put("sqEditorNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M sqUpdateTime(Object sqUpdateTime){this.put("sqUpdateTime", sqUpdateTime);return this;};
	 	/** and sq_update_time is null */
 		public M sqUpdateTimeNull(){if(this.get("sqUpdateTimeNot")==null)this.put("sqUpdateTimeNot", "");this.put("sqUpdateTime", null);return this;};
 		/** not .... */
 		public M sqUpdateTimeNot(){this.put("sqUpdateTimeNot", "not");return this;};
 		/** and sq_update_time >= ? */
 		public M sqUpdateTimeStart(Object start){this.put("sqUpdateTimeStart", start);return this;};			
 		/** and sq_update_time <= ? */
 		public M sqUpdateTimeEnd(Object end){this.put("sqUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M sqAddTime(Object sqAddTime){this.put("sqAddTime", sqAddTime);return this;};
	 	/** and sq_add_time is null */
 		public M sqAddTimeNull(){if(this.get("sqAddTimeNot")==null)this.put("sqAddTimeNot", "");this.put("sqAddTime", null);return this;};
 		/** not .... */
 		public M sqAddTimeNot(){this.put("sqAddTimeNot", "not");return this;};
 		/** and sq_add_time >= ? */
 		public M sqAddTimeStart(Object start){this.put("sqAddTimeStart", start);return this;};			
 		/** and sq_add_time <= ? */
 		public M sqAddTimeEnd(Object end){this.put("sqAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M sqStatus(Object sqStatus){this.put("sqStatus", sqStatus);return this;};
	 	/** and sq_status is null */
 		public M sqStatusNull(){if(this.get("sqStatusNot")==null)this.put("sqStatusNot", "");this.put("sqStatus", null);return this;};
 		/** not .... */
 		public M sqStatusNot(){this.put("sqStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有表单查询 **/
		public @api List<SrvQuery> list(Integer size){
			return getSrvQueryList(this,size);
		}
		/** 获取表单查询分页 **/
		public @api Page<SrvQuery> page(int page,int size){
			return getSrvQueryPage(page, size , this);
		}
		/** 根据查询条件取表单查询 **/
		public @api SrvQuery get(){
			return getSrvQuery(this);
		}
		/** 获取表单查询数 **/
		public @api Long count(){
			return getSrvQueryCount(this);
		}
		/** 获取表单查询表达式 **/
		public @api <T> T eval(String strEval){
			return getSrvQueryEval(strEval,this);
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
			updateSrvQuery(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String sqId="sqId";
		/** 查询名称 [非空]       **/
		public final static @type(String.class) @like String sqName="sqName";
		/** 图标 [非空]       **/
		public final static @type(String.class)  String sqIcon="sqIcon";
		/** 项目路径 [非空]       **/
		public final static @type(String.class) @like String sqPath="sqPath";
		/** 查询参数 [非空]       **/
		public final static @type(String.class) @like String sqParams="sqParams";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String sqEditor="sqEditor";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String sqUpdateTime="sqUpdateTime";
	 	/** and sq_update_time >= ? */
 		public final static @type(Date.class) String sqUpdateTimeStart="sqUpdateTimeStart";
 		/** and sq_update_time <= ? */
 		public final static @type(Date.class) String sqUpdateTimeEnd="sqUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String sqAddTime="sqAddTime";
	 	/** and sq_add_time >= ? */
 		public final static @type(Date.class) String sqAddTimeStart="sqAddTimeStart";
 		/** and sq_add_time <= ? */
 		public final static @type(Date.class) String sqAddTimeEnd="sqAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String sqStatus="sqStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String sqId="sq_id";
		/** 查询名称 [非空]       **/
		public final static String sqName="sq_name";
		/** 图标 [非空]       **/
		public final static String sqIcon="sq_icon";
		/** 项目路径 [非空]       **/
		public final static String sqPath="sq_path";
		/** 查询参数 [非空]       **/
		public final static String sqParams="sq_params";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String sqEditor="sq_editor";
		/** 修改时间 [非空]       **/
		public final static String sqUpdateTime="sq_update_time";
		/** 添加时间 [非空]       **/
		public final static String sqAddTime="sq_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String sqStatus="sq_status";
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
				$.Set(name,SrvQuery.getSrvQuery(params));
			else
				$.Set(name,SrvQuery.getSrvQueryList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取表单查询数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.SrvQuery) $.GetRequest("SrvQuery$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = SrvQuery.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = SrvQuery.getSrvQuery(params);
			else
				value = SrvQuery.getSrvQueryList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = SrvQuery.Get($.add(SrvQuery.F.sqId,param));
		else if(!$.empty(param.toString()))
			value = SrvQuery.get(Long.valueOf(param.toString()));
		$.SetRequest("SrvQuery$"+param.hashCode(), value);
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
	public void mergeSet(SrvQuery old){
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