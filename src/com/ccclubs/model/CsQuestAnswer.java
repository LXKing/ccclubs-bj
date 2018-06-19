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

@namespace("csm/revisit/answer")
public @caption("问题及选项") @table("cs_quest_answer") class CsQuestAnswer implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csqa_id")   @primary  @note("  ") Long csqaId;// 主键 非空     
	private @caption("标题内容") @column("csqa_title")    @note("  第一级表示问题，第二级表示答案") String csqaTitle;// 非空  第一级表示问题，第二级表示答案   
	private @caption("所属问题") @column("csqa_parent")  @hidden   @relate("$csqaParent") @RelateClass(CsQuestAnswer.class)  @note("  ") Long csqaParent;// 非空     
 	private CsQuestAnswer $csqaParent;//关联对象[问题及选项]
	
	//默认构造函数
	public CsQuestAnswer(){
	
	}
	
	//主键构造函数
	public CsQuestAnswer(Long id){
		this.csqaId = id;
	}
	
	/**所有字段构造函数 CsQuestAnswer(csqaTitle,csqaParent)
	 CsQuestAnswer(
	 	$.getString("csqaTitle")//标题内容 [非空]
	 	,$.getLong("csqaParent")//所属问题 [非空]
	 )
	**/
	public CsQuestAnswer(String csqaTitle,Long csqaParent){
		this.csqaTitle=csqaTitle;
		this.csqaParent=csqaParent;
	}
	
	//设置非空字段
	public CsQuestAnswer setNotNull(String csqaTitle,Long csqaParent){
		this.csqaTitle=csqaTitle;
		this.csqaParent=csqaParent;
		return this;
	}
	/** 编号 [非空]       **/
	public CsQuestAnswer csqaId(Long csqaId){
		this.csqaId = csqaId;
		this.setSeted(F.csqaId);
		return this;
	}
	/** 标题内容 [非空]    第一级表示问题，第二级表示答案   **/
	public CsQuestAnswer csqaTitle(String csqaTitle){
		this.csqaTitle = csqaTitle;
		this.setSeted(F.csqaTitle);
		return this;
	}
	/** 所属问题 [非空] [CsQuestAnswer]      **/
	public CsQuestAnswer csqaParent(Long csqaParent){
		this.csqaParent = csqaParent;
		this.setSeted(F.csqaParent);
		return this;
	}
	
	
	//克隆对象
	public CsQuestAnswer clone(){
		CsQuestAnswer clone = new CsQuestAnswer();
		clone.csqaTitle=this.csqaTitle;
		clone.csqaParent=this.csqaParent;
		return clone;
	}
	
	
	/**
	 * 根据ID取问题及选项
	 * @param id
	 * @return
	 */
	public static @api CsQuestAnswer get(Long id){		
		return getCsQuestAnswerById(id);
	}
	/**
	 * 获取所有问题及选项
	 * @return
	 */
	public static @api List<CsQuestAnswer> list(Map params,Integer size){
		return getCsQuestAnswerList(params,size);
	}
	/**
	 * 获取问题及选项分页
	 * @return
	 */
	public static @api Page<CsQuestAnswer> page(int page,int size,Map params){
		return getCsQuestAnswerPage(page, size , params);
	}
	/**
	 * 根据查询条件取问题及选项
	 * @param params
	 * @return
	 */
	public static @api CsQuestAnswer Get(Map params){
		return getCsQuestAnswer(params);
	}
	/**
	 * 获取问题及选项数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsQuestAnswerCount(params);
	}
	/**
	 * 获取问题及选项数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsQuestAnswerEval(eval,params);
	}
	
	/**
	 * 根据ID取问题及选项
	 * @param id
	 * @return
	 */
	public static @api CsQuestAnswer getCsQuestAnswerById(Long id){		
		CsQuestAnswer csQuestAnswer = (CsQuestAnswer) $.GetRequest("CsQuestAnswer$"+id);
		if(csQuestAnswer!=null)
			return csQuestAnswer;
		com.ccclubs.service.admin.ICsQuestAnswerService csQuestAnswerService = $.GetSpringBean("csQuestAnswerService");		
		return csQuestAnswerService.getCsQuestAnswerById(id);
	}
	
	
	/**
	 * 根据ID取问题及选项的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsQuestAnswer.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsQuestAnswer csQuestAnswer = get(id);
		if(csQuestAnswer!=null){
			String strValue = csQuestAnswer.getCsqaTitle$();
			if(!"CsqaTitle".equals("CsqaTitle"))
				strValue+="("+csQuestAnswer.getCsqaTitle$()+")";
			MemCache.setValue(CsQuestAnswer.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsqaTitle$();
		if(!"CsqaTitle".equals("CsqaTitle"))
			keyValue+="("+this.getCsqaTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有问题及选项
	 * @return
	 */
	public static @api List<CsQuestAnswer> getCsQuestAnswerList(Map params,Integer size){
		com.ccclubs.service.admin.ICsQuestAnswerService csQuestAnswerService = $.GetSpringBean("csQuestAnswerService");
		return csQuestAnswerService.getCsQuestAnswerList(params, size);
	}
	
	/**
	 * 获取问题及选项分页
	 * @return
	 */
	public static @api Page<CsQuestAnswer> getCsQuestAnswerPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsQuestAnswerService csQuestAnswerService = $.GetSpringBean("csQuestAnswerService");
		return csQuestAnswerService.getCsQuestAnswerPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取问题及选项
	 * @param params
	 * @return
	 */
	public static @api CsQuestAnswer getCsQuestAnswer(Map params){
		com.ccclubs.service.admin.ICsQuestAnswerService csQuestAnswerService = $.GetSpringBean("csQuestAnswerService");
		return csQuestAnswerService.getCsQuestAnswer(params);
	}
	
	/**
	 * 获取问题及选项数
	 * @return
	 */
	public static @api Long getCsQuestAnswerCount(Map params){
		com.ccclubs.service.admin.ICsQuestAnswerService csQuestAnswerService = $.GetSpringBean("csQuestAnswerService");
		return csQuestAnswerService.getCsQuestAnswerCount(params);
	}
		
		
	/**
	 * 获取问题及选项自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsQuestAnswerEval(String eval,Map params){
		com.ccclubs.service.admin.ICsQuestAnswerService csQuestAnswerService = $.GetSpringBean("csQuestAnswerService");
		return csQuestAnswerService.getCsQuestAnswerEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsQuestAnswer(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsQuestAnswerService csQuestAnswerService = $.GetSpringBean("csQuestAnswerService");
		csQuestAnswerService.updateCsQuestAnswerByConfirm(set, where);
	}
	
	
	/**
	 * 保存问题及选项对象
	 * @param params
	 * @return
	 */
	public CsQuestAnswer save(){
		com.ccclubs.service.admin.ICsQuestAnswerService csQuestAnswerService = $.GetSpringBean("csQuestAnswerService");
		if(this.getCsqaId()!=null)
			csQuestAnswerService.updateCsQuestAnswer(this);
		else
			return csQuestAnswerService.saveCsQuestAnswer(this);
		return this;
	}
	
	
	/**
	 * 更新问题及选项对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsQuestAnswerService csQuestAnswerService = $.GetSpringBean("csQuestAnswerService");
		csQuestAnswerService.updateCsQuestAnswer$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsQuestAnswerService csQuestAnswerService = $.GetSpringBean("csQuestAnswerService");
			csQuestAnswerService.deleteCsQuestAnswerById(this.getCsqaId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsQuestAnswerService csQuestAnswerService = $.GetSpringBean("csQuestAnswerService");
		return csQuestAnswerService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsqaId(){
		return this.csqaId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsqaId$(){
		String strValue="";
		 strValue=$.str(this.getCsqaId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsqaId(Long csqaId){
		this.csqaId = csqaId;
		this.setSeted(F.csqaId);
	}
	/*******************************标题内容**********************************/	
	/**
	* 标题内容 [非空]    第一级表示问题，第二级表示答案  
	**/
	public String getCsqaTitle(){
		return this.csqaTitle;
	}
	/**
	* 获取标题内容格式化(toString)
	**/
	public String getCsqaTitle$(){
		String strValue="";
		 strValue=$.str(this.getCsqaTitle());
	 	 return strValue;
	}
	/**
	* 标题内容 [非空]    第一级表示问题，第二级表示答案  
	**/
	public void setCsqaTitle(String csqaTitle){
		this.csqaTitle = csqaTitle;
		this.setSeted(F.csqaTitle);
	}
	/*******************************所属问题**********************************/	
	/**
	* 所属问题 [非空] [CsQuestAnswer]     
	**/
	public Long getCsqaParent(){
		return this.csqaParent;
	}
	/**
	* 获取所属问题格式化(toString)
	**/
	public String getCsqaParent$(){
		String strValue="";
		if(this.getCsqaParent()!=null){
				strValue+=$.str(CsQuestAnswer.getKeyValue(this.getCsqaParent()));
		}			
	 	 return strValue;
	}
	/**
	* 所属问题 [非空] [CsQuestAnswer]     
	**/
	public void setCsqaParent(Long csqaParent){
		this.csqaParent = csqaParent;
		this.setSeted(F.csqaParent);
	}
	/**
	* 获取关联对象[问题及选项]
	**/	 			
 	public CsQuestAnswer get$csqaParent(){
 		com.ccclubs.model.CsQuestAnswer csQuestAnswer = (com.ccclubs.model.CsQuestAnswer) $.GetRequest("CsQuestAnswer$"+this.getCsqaParent());
 		if(csQuestAnswer!=null)
			return csQuestAnswer;
		if(this.getCsqaParent()!=null){
 			csQuestAnswer = CsQuestAnswer.get(this.getCsqaParent());
 		}
 		$.SetRequest("CsQuestAnswer$"+this.getCsqaParent(), csQuestAnswer);
	 	return csQuestAnswer;
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsQuestAnswer.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsQuestAnswer.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsQuestAnswer.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsQuestAnswer.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsQuestAnswer.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsQuestAnswer.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsQuestAnswer.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsQuestAnswer.this);
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
		public M csqaId(Object csqaId){this.put("csqaId", csqaId);return this;};
	 	/** and csqa_id is null */
 		public M csqaIdNull(){if(this.get("csqaIdNot")==null)this.put("csqaIdNot", "");this.put("csqaId", null);return this;};
 		/** not .... */
 		public M csqaIdNot(){this.put("csqaIdNot", "not");return this;};
		/** 标题内容 [非空]    第一级表示问题，第二级表示答案   **/
		public M csqaTitle(Object csqaTitle){this.put("csqaTitle", csqaTitle);return this;};
	 	/** and csqa_title is null */
 		public M csqaTitleNull(){if(this.get("csqaTitleNot")==null)this.put("csqaTitleNot", "");this.put("csqaTitle", null);return this;};
 		/** not .... */
 		public M csqaTitleNot(){this.put("csqaTitleNot", "not");return this;};
		/** 所属问题 [非空] [CsQuestAnswer]      **/
		public M csqaParent(Object csqaParent){this.put("csqaParent", csqaParent);return this;};
	 	/** and csqa_parent is null */
 		public M csqaParentNull(){if(this.get("csqaParentNot")==null)this.put("csqaParentNot", "");this.put("csqaParent", null);return this;};
 		/** not .... */
 		public M csqaParentNot(){this.put("csqaParentNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有问题及选项 **/
		public @api List<CsQuestAnswer> list(Integer size){
			return getCsQuestAnswerList(this,size);
		}
		/** 获取问题及选项分页 **/
		public @api Page<CsQuestAnswer> page(int page,int size){
			return getCsQuestAnswerPage(page, size , this);
		}
		/** 根据查询条件取问题及选项 **/
		public @api CsQuestAnswer get(){
			return getCsQuestAnswer(this);
		}
		/** 获取问题及选项数 **/
		public @api Long count(){
			return getCsQuestAnswerCount(this);
		}
		/** 获取问题及选项表达式 **/
		public @api <T> T eval(String strEval){
			return getCsQuestAnswerEval(strEval,this);
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
			updateCsQuestAnswer(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csqaId="csqaId";
		/** 标题内容 [非空]    第一级表示问题，第二级表示答案   **/
		public final static @type(String.class) @like String csqaTitle="csqaTitle";
		/** 所属问题 [非空] [CsQuestAnswer]      **/
		public final static @type(Long.class)  String csqaParent="csqaParent";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csqaId="csqa_id";
		/** 标题内容 [非空]    第一级表示问题，第二级表示答案   **/
		public final static String csqaTitle="csqa_title";
		/** 所属问题 [非空] [CsQuestAnswer]      **/
		public final static String csqaParent="csqa_parent";
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
				$.Set(name,CsQuestAnswer.getCsQuestAnswer(params));
			else
				$.Set(name,CsQuestAnswer.getCsQuestAnswerList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取问题及选项数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsQuestAnswer) $.GetRequest("CsQuestAnswer$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsQuestAnswer.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsQuestAnswer.getCsQuestAnswer(params);
			else
				value = CsQuestAnswer.getCsQuestAnswerList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsQuestAnswer.Get($.add(CsQuestAnswer.F.csqaId,param));
		else if(!$.empty(param.toString()))
			value = CsQuestAnswer.get(Long.valueOf(param.toString()));
		$.SetRequest("CsQuestAnswer$"+param.hashCode(), value);
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
	public void mergeSet(CsQuestAnswer old){
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