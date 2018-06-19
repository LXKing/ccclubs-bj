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

@namespace("extension/strategy")
public @caption("品牌战略") @table("cs_strategy") class CsStrategy implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("css_id")   @primary  @note("  ") Long cssId;// 主键 非空     
	private @caption("战略标题") @column("css_title")    @note("  ") String cssTitle;// 非空     
	private @caption("战略详情") @column("css_content")  @hidden   @note("  ") String cssContent;// 非空     
	private @caption("构想图") @column("css_mind")  @hidden   @note("  ") String cssMind;//     
	private @caption("添加人") @column("css_adder")    @relate("$cssAdder") @RelateClass(SrvUser.class)  @note("  ") Long cssAdder;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cssAdder;//关联对象[用户]
	private @caption("修改人") @column("css_editor")    @relate("$cssEditor") @RelateClass(SrvUser.class)  @note("  ") Long cssEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cssEditor;//关联对象[用户]
	private @caption("修改时间") @column("css_update_time")    @note("  ") Date cssUpdateTime;// 非空     
	private @caption("添加时间") @column("css_add_time")    @note("  ") Date cssAddTime;// 非空     
	private @caption("状态") @column("css_status")    @note(" 1:正常 0:无效  ") Short cssStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsStrategy(){
	
	}
	
	//主键构造函数
	public CsStrategy(Long id){
		this.cssId = id;
	}
	
	/**所有字段构造函数 CsStrategy(cssTitle,cssContent,cssMind,cssAdder,cssEditor,cssUpdateTime,cssAddTime,cssStatus)
	 CsStrategy(
	 	$.getString("cssTitle")//战略标题 [非空]
	 	,$.getString("cssContent")//战略详情 [非空]
	 	,$.getString("cssMind")//构想图
	 	,$.getLong("cssAdder")//添加人 [非空]
	 	,$.getLong("cssEditor")//修改人 [非空]
	 	,$.getDate("cssUpdateTime")//修改时间 [非空]
	 	,$.getDate("cssAddTime")//添加时间 [非空]
	 	,$.getShort("cssStatus")//状态 [非空]
	 )
	**/
	public CsStrategy(String cssTitle,String cssContent,String cssMind,Long cssAdder,Long cssEditor,Date cssUpdateTime,Date cssAddTime,Short cssStatus){
		this.cssTitle=cssTitle;
		this.cssContent=cssContent;
		this.cssMind=cssMind;
		this.cssAdder=cssAdder;
		this.cssEditor=cssEditor;
		this.cssUpdateTime=cssUpdateTime;
		this.cssAddTime=cssAddTime;
		this.cssStatus=cssStatus;
	}
	
	//设置非空字段
	public CsStrategy setNotNull(String cssTitle,String cssContent,Long cssAdder,Long cssEditor,Date cssUpdateTime,Date cssAddTime,Short cssStatus){
		this.cssTitle=cssTitle;
		this.cssContent=cssContent;
		this.cssAdder=cssAdder;
		this.cssEditor=cssEditor;
		this.cssUpdateTime=cssUpdateTime;
		this.cssAddTime=cssAddTime;
		this.cssStatus=cssStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsStrategy cssId(Long cssId){
		this.cssId = cssId;
		this.setSeted(F.cssId);
		return this;
	}
	/** 战略标题 [非空]       **/
	public CsStrategy cssTitle(String cssTitle){
		this.cssTitle = cssTitle;
		this.setSeted(F.cssTitle);
		return this;
	}
	/** 战略详情 [非空]       **/
	public CsStrategy cssContent(String cssContent){
		this.cssContent = cssContent;
		this.setSeted(F.cssContent);
		return this;
	}
	/** 构想图        **/
	public CsStrategy cssMind(String cssMind){
		this.cssMind = cssMind;
		this.setSeted(F.cssMind);
		return this;
	}
	/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsStrategy cssAdder(Long cssAdder){
		this.cssAdder = cssAdder;
		this.setSeted(F.cssAdder);
		return this;
	}
	/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsStrategy cssEditor(Long cssEditor){
		this.cssEditor = cssEditor;
		this.setSeted(F.cssEditor);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsStrategy cssUpdateTime(Date cssUpdateTime){
		this.cssUpdateTime = cssUpdateTime;
		this.setSeted(F.cssUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsStrategy cssAddTime(Date cssAddTime){
		this.cssAddTime = cssAddTime;
		this.setSeted(F.cssAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsStrategy cssStatus(Short cssStatus){
		this.cssStatus = cssStatus;
		this.setSeted(F.cssStatus);
		return this;
	}
	
	
	//克隆对象
	public CsStrategy clone(){
		CsStrategy clone = new CsStrategy();
		clone.cssTitle=this.cssTitle;
		clone.cssContent=this.cssContent;
		clone.cssAdder=this.cssAdder;
		clone.cssEditor=this.cssEditor;
		clone.cssUpdateTime=this.cssUpdateTime;
		clone.cssAddTime=this.cssAddTime;
		clone.cssStatus=this.cssStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取品牌战略
	 * @param id
	 * @return
	 */
	public static @api CsStrategy get(Long id){		
		return getCsStrategyById(id);
	}
	/**
	 * 获取所有品牌战略
	 * @return
	 */
	public static @api List<CsStrategy> list(Map params,Integer size){
		return getCsStrategyList(params,size);
	}
	/**
	 * 获取品牌战略分页
	 * @return
	 */
	public static @api Page<CsStrategy> page(int page,int size,Map params){
		return getCsStrategyPage(page, size , params);
	}
	/**
	 * 根据查询条件取品牌战略
	 * @param params
	 * @return
	 */
	public static @api CsStrategy Get(Map params){
		return getCsStrategy(params);
	}
	/**
	 * 获取品牌战略数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsStrategyCount(params);
	}
	/**
	 * 获取品牌战略数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsStrategyEval(eval,params);
	}
	
	/**
	 * 根据ID取品牌战略
	 * @param id
	 * @return
	 */
	public static @api CsStrategy getCsStrategyById(Long id){		
		CsStrategy csStrategy = (CsStrategy) $.GetRequest("CsStrategy$"+id);
		if(csStrategy!=null)
			return csStrategy;
		com.ccclubs.service.admin.ICsStrategyService csStrategyService = $.GetSpringBean("csStrategyService");		
		return csStrategyService.getCsStrategyById(id);
	}
	
	
	/**
	 * 根据ID取品牌战略的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsStrategy.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsStrategy csStrategy = get(id);
		if(csStrategy!=null){
			String strValue = csStrategy.getCssTitle$();
			if(!"CssTitle".equals("CssTitle"))
				strValue+="("+csStrategy.getCssTitle$()+")";
			MemCache.setValue(CsStrategy.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCssTitle$();
		if(!"CssTitle".equals("CssTitle"))
			keyValue+="("+this.getCssTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有品牌战略
	 * @return
	 */
	public static @api List<CsStrategy> getCsStrategyList(Map params,Integer size){
		com.ccclubs.service.admin.ICsStrategyService csStrategyService = $.GetSpringBean("csStrategyService");
		return csStrategyService.getCsStrategyList(params, size);
	}
	
	/**
	 * 获取品牌战略分页
	 * @return
	 */
	public static @api Page<CsStrategy> getCsStrategyPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsStrategyService csStrategyService = $.GetSpringBean("csStrategyService");
		return csStrategyService.getCsStrategyPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取品牌战略
	 * @param params
	 * @return
	 */
	public static @api CsStrategy getCsStrategy(Map params){
		com.ccclubs.service.admin.ICsStrategyService csStrategyService = $.GetSpringBean("csStrategyService");
		return csStrategyService.getCsStrategy(params);
	}
	
	/**
	 * 获取品牌战略数
	 * @return
	 */
	public static @api Long getCsStrategyCount(Map params){
		com.ccclubs.service.admin.ICsStrategyService csStrategyService = $.GetSpringBean("csStrategyService");
		return csStrategyService.getCsStrategyCount(params);
	}
		
		
	/**
	 * 获取品牌战略自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsStrategyEval(String eval,Map params){
		com.ccclubs.service.admin.ICsStrategyService csStrategyService = $.GetSpringBean("csStrategyService");
		return csStrategyService.getCsStrategyEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsStrategy(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsStrategyService csStrategyService = $.GetSpringBean("csStrategyService");
		csStrategyService.updateCsStrategyByConfirm(set, where);
	}
	
	
	/**
	 * 保存品牌战略对象
	 * @param params
	 * @return
	 */
	public CsStrategy save(){
		com.ccclubs.service.admin.ICsStrategyService csStrategyService = $.GetSpringBean("csStrategyService");
		if(this.getCssId()!=null)
			csStrategyService.updateCsStrategy(this);
		else
			return csStrategyService.saveCsStrategy(this);
		return this;
	}
	
	
	/**
	 * 更新品牌战略对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsStrategyService csStrategyService = $.GetSpringBean("csStrategyService");
		csStrategyService.updateCsStrategy$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsStrategyService csStrategyService = $.GetSpringBean("csStrategyService");
		if($.equals($.config("logic_delete"),"true"))
			csStrategyService.removeCsStrategyById(this.getCssId());
		else
			csStrategyService.deleteCsStrategyById(this.getCssId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsStrategyService csStrategyService = $.GetSpringBean("csStrategyService");
		return csStrategyService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCssId(){
		return this.cssId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCssId$(){
		String strValue="";
		 strValue=$.str(this.getCssId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCssId(Long cssId){
		this.cssId = cssId;
		this.setSeted(F.cssId);
	}
	/*******************************战略标题**********************************/	
	/**
	* 战略标题 [非空]      
	**/
	public String getCssTitle(){
		return this.cssTitle;
	}
	/**
	* 获取战略标题格式化(toString)
	**/
	public String getCssTitle$(){
		String strValue="";
		 strValue=$.str(this.getCssTitle());
	 	 return strValue;
	}
	/**
	* 战略标题 [非空]      
	**/
	public void setCssTitle(String cssTitle){
		this.cssTitle = cssTitle;
		this.setSeted(F.cssTitle);
	}
	/*******************************战略详情**********************************/	
	/**
	* 战略详情 [非空]      
	**/
	public String getCssContent(){
		return this.cssContent;
	}
	/**
	* 获取战略详情格式化(toString)
	**/
	public String getCssContent$(){
		String strValue="";
		 strValue=$.str(this.getCssContent());
	 	 return strValue;
	}
	/**
	* 战略详情 [非空]      
	**/
	public void setCssContent(String cssContent){
		this.cssContent = cssContent;
		this.setSeted(F.cssContent);
	}
	/*******************************构想图**********************************/	
	/**
	* 构想图       
	**/
	public String getCssMind(){
		return this.cssMind;
	}
	/**
	* 获取构想图格式化(toString)
	**/
	public String getCssMind$(){
		String strValue="";
		 strValue=$.str(this.getCssMind());
	 	 return strValue;
	}
	/**
	* 构想图       
	**/
	public void setCssMind(String cssMind){
		this.cssMind = cssMind;
		this.setSeted(F.cssMind);
	}
	/*******************************添加人**********************************/	
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCssAdder(){
		return this.cssAdder;
	}
	/**
	* 获取添加人格式化(toString)
	**/
	public String getCssAdder$(){
		String strValue="";
		if(this.getCssAdder()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCssAdder()));
		}			
	 	 return strValue;
	}
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCssAdder(Long cssAdder){
		this.cssAdder = cssAdder;
		this.setSeted(F.cssAdder);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cssAdder(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCssAdder());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCssAdder()!=null){
 			srvUser = SrvUser.get(this.getCssAdder());
 		}
 		$.SetRequest("SrvUser$"+this.getCssAdder(), srvUser);
	 	return srvUser;
	}
	/*******************************修改人**********************************/	
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCssEditor(){
		return this.cssEditor;
	}
	/**
	* 获取修改人格式化(toString)
	**/
	public String getCssEditor$(){
		String strValue="";
		if(this.getCssEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCssEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCssEditor(Long cssEditor){
		this.cssEditor = cssEditor;
		this.setSeted(F.cssEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cssEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCssEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCssEditor()!=null){
 			srvUser = SrvUser.get(this.getCssEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCssEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCssUpdateTime(){
		return this.cssUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCssUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCssUpdateTime(Date cssUpdateTime){
		this.cssUpdateTime = cssUpdateTime;
		this.setSeted(F.cssUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCssAddTime(){
		return this.cssAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCssAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCssAddTime(Date cssAddTime){
		this.cssAddTime = cssAddTime;
		this.setSeted(F.cssAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCssStatus(){
		return this.cssStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCssStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCssStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCssStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCssStatus(Short cssStatus){
		this.cssStatus = cssStatus;
		this.setSeted(F.cssStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsStrategy.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsStrategy.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsStrategy.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsStrategy.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsStrategy.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsStrategy.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsStrategy.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsStrategy.this);
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
		public M cssId(Object cssId){this.put("cssId", cssId);return this;};
	 	/** and css_id is null */
 		public M cssIdNull(){if(this.get("cssIdNot")==null)this.put("cssIdNot", "");this.put("cssId", null);return this;};
 		/** not .... */
 		public M cssIdNot(){this.put("cssIdNot", "not");return this;};
		/** 战略标题 [非空]       **/
		public M cssTitle(Object cssTitle){this.put("cssTitle", cssTitle);return this;};
	 	/** and css_title is null */
 		public M cssTitleNull(){if(this.get("cssTitleNot")==null)this.put("cssTitleNot", "");this.put("cssTitle", null);return this;};
 		/** not .... */
 		public M cssTitleNot(){this.put("cssTitleNot", "not");return this;};
		/** 战略详情 [非空]       **/
		public M cssContent(Object cssContent){this.put("cssContent", cssContent);return this;};
	 	/** and css_content is null */
 		public M cssContentNull(){if(this.get("cssContentNot")==null)this.put("cssContentNot", "");this.put("cssContent", null);return this;};
 		/** not .... */
 		public M cssContentNot(){this.put("cssContentNot", "not");return this;};
		/** 构想图        **/
		public M cssMind(Object cssMind){this.put("cssMind", cssMind);return this;};
	 	/** and css_mind is null */
 		public M cssMindNull(){if(this.get("cssMindNot")==null)this.put("cssMindNot", "");this.put("cssMind", null);return this;};
 		/** not .... */
 		public M cssMindNot(){this.put("cssMindNot", "not");return this;};
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cssAdder(Object cssAdder){this.put("cssAdder", cssAdder);return this;};
	 	/** and css_adder is null */
 		public M cssAdderNull(){if(this.get("cssAdderNot")==null)this.put("cssAdderNot", "");this.put("cssAdder", null);return this;};
 		/** not .... */
 		public M cssAdderNot(){this.put("cssAdderNot", "not");return this;};
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cssEditor(Object cssEditor){this.put("cssEditor", cssEditor);return this;};
	 	/** and css_editor is null */
 		public M cssEditorNull(){if(this.get("cssEditorNot")==null)this.put("cssEditorNot", "");this.put("cssEditor", null);return this;};
 		/** not .... */
 		public M cssEditorNot(){this.put("cssEditorNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M cssUpdateTime(Object cssUpdateTime){this.put("cssUpdateTime", cssUpdateTime);return this;};
	 	/** and css_update_time is null */
 		public M cssUpdateTimeNull(){if(this.get("cssUpdateTimeNot")==null)this.put("cssUpdateTimeNot", "");this.put("cssUpdateTime", null);return this;};
 		/** not .... */
 		public M cssUpdateTimeNot(){this.put("cssUpdateTimeNot", "not");return this;};
 		/** and css_update_time >= ? */
 		public M cssUpdateTimeStart(Object start){this.put("cssUpdateTimeStart", start);return this;};			
 		/** and css_update_time <= ? */
 		public M cssUpdateTimeEnd(Object end){this.put("cssUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cssAddTime(Object cssAddTime){this.put("cssAddTime", cssAddTime);return this;};
	 	/** and css_add_time is null */
 		public M cssAddTimeNull(){if(this.get("cssAddTimeNot")==null)this.put("cssAddTimeNot", "");this.put("cssAddTime", null);return this;};
 		/** not .... */
 		public M cssAddTimeNot(){this.put("cssAddTimeNot", "not");return this;};
 		/** and css_add_time >= ? */
 		public M cssAddTimeStart(Object start){this.put("cssAddTimeStart", start);return this;};			
 		/** and css_add_time <= ? */
 		public M cssAddTimeEnd(Object end){this.put("cssAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M cssStatus(Object cssStatus){this.put("cssStatus", cssStatus);return this;};
	 	/** and css_status is null */
 		public M cssStatusNull(){if(this.get("cssStatusNot")==null)this.put("cssStatusNot", "");this.put("cssStatus", null);return this;};
 		/** not .... */
 		public M cssStatusNot(){this.put("cssStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有品牌战略 **/
		public @api List<CsStrategy> list(Integer size){
			return getCsStrategyList(this,size);
		}
		/** 获取品牌战略分页 **/
		public @api Page<CsStrategy> page(int page,int size){
			return getCsStrategyPage(page, size , this);
		}
		/** 根据查询条件取品牌战略 **/
		public @api CsStrategy get(){
			return getCsStrategy(this);
		}
		/** 获取品牌战略数 **/
		public @api Long count(){
			return getCsStrategyCount(this);
		}
		/** 获取品牌战略表达式 **/
		public @api <T> T eval(String strEval){
			return getCsStrategyEval(strEval,this);
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
			updateCsStrategy(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cssId="cssId";
		/** 战略标题 [非空]       **/
		public final static @type(String.class) @like String cssTitle="cssTitle";
		/** 战略详情 [非空]       **/
		public final static @type(String.class) @like String cssContent="cssContent";
		/** 构想图        **/
		public final static @type(String.class)  String cssMind="cssMind";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String cssAdder="cssAdder";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String cssEditor="cssEditor";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cssUpdateTime="cssUpdateTime";
	 	/** and css_update_time >= ? */
 		public final static @type(Date.class) String cssUpdateTimeStart="cssUpdateTimeStart";
 		/** and css_update_time <= ? */
 		public final static @type(Date.class) String cssUpdateTimeEnd="cssUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cssAddTime="cssAddTime";
	 	/** and css_add_time >= ? */
 		public final static @type(Date.class) String cssAddTimeStart="cssAddTimeStart";
 		/** and css_add_time <= ? */
 		public final static @type(Date.class) String cssAddTimeEnd="cssAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String cssStatus="cssStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cssId="css_id";
		/** 战略标题 [非空]       **/
		public final static String cssTitle="css_title";
		/** 战略详情 [非空]       **/
		public final static String cssContent="css_content";
		/** 构想图        **/
		public final static String cssMind="css_mind";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cssAdder="css_adder";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cssEditor="css_editor";
		/** 修改时间 [非空]       **/
		public final static String cssUpdateTime="css_update_time";
		/** 添加时间 [非空]       **/
		public final static String cssAddTime="css_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String cssStatus="css_status";
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
				$.Set(name,CsStrategy.getCsStrategy(params));
			else
				$.Set(name,CsStrategy.getCsStrategyList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取品牌战略数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsStrategy) $.GetRequest("CsStrategy$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsStrategy.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsStrategy.getCsStrategy(params);
			else
				value = CsStrategy.getCsStrategyList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsStrategy.Get($.add(CsStrategy.F.cssId,param));
		else if(!$.empty(param.toString()))
			value = CsStrategy.get(Long.valueOf(param.toString()));
		$.SetRequest("CsStrategy$"+param.hashCode(), value);
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
	public void mergeSet(CsStrategy old){
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