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

@namespace("fee/rule")
public @caption("规则") @table("cs_rule") class CsRule implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csr_id")   @primary  @note("  ") Long csrId;// 主键 非空     
	private @caption("规则名称") @column("csr_name")    @note("  ") String csrName;// 非空     
	//private @caption("商品列表") @RelateClass(CsGoods.class) List<CsGoods> csrGoods;//反向关联的商品列表
	private @caption("规则表达式") @column("csr_expression")    @note("  [(时间属性)]{min,max}  A-X表示24个小时 w工作日 c双休日 h节假日") String csrExpression;// 非空  [(时间属性)]{min,max}  A-X表示24个小时 w工作日 c双休日 h节假日   
	private @caption("优先级") @column("csr_priority")    @note("  ") Short csrPriority;//     
	private @caption("计量表达式") @column("csr_meas")    @note("  {M}:当前分钟 {H}当前小时 {K}当前公里 +加 -减 *乘 /除") String csrMeas;//(单位：算式)  {M}:当前分钟 {H}当前小时 {K}当前公里 +加 -减 *乘 /除   
	private @caption("规则描述") @column("csr_profile")  @hidden   @note("  ") String csrProfile;//     
	private @caption("状态") @column("csr_status")    @note(" 1:有效 0:无效  ") Short csrStatus;// 非空 1:有效 0:无效     
	
	//默认构造函数
	public CsRule(){
	
	}
	
	//主键构造函数
	public CsRule(Long id){
		this.csrId = id;
	}
	
	/**所有字段构造函数 CsRule(csrName,csrExpression,csrPriority,csrMeas,csrProfile,csrStatus)
	 CsRule(
	 	$.getString("csrName")//规则名称 [非空]
	 	,$.getString("csrExpression")//规则表达式 [非空]
	 	,$.getShort("csrPriority")//优先级
	 	,$.getString("csrMeas")//计量表达式
	 	,$.getString("csrProfile")//规则描述
	 	,$.getShort("csrStatus")//状态 [非空]
	 )
	**/
	public CsRule(String csrName,String csrExpression,Short csrPriority,String csrMeas,String csrProfile,Short csrStatus){
		this.csrName=csrName;
		this.csrExpression=csrExpression;
		this.csrPriority=csrPriority;
		this.csrMeas=csrMeas;
		this.csrProfile=csrProfile;
		this.csrStatus=csrStatus;
	}
	
	//设置非空字段
	public CsRule setNotNull(String csrName,String csrExpression,Short csrStatus){
		this.csrName=csrName;
		this.csrExpression=csrExpression;
		this.csrStatus=csrStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsRule csrId(Long csrId){
		this.csrId = csrId;
		this.setSeted(F.csrId);
		return this;
	}
	/** 规则名称 [非空]       **/
	public CsRule csrName(String csrName){
		this.csrName = csrName;
		this.setSeted(F.csrName);
		return this;
	}
	/** 规则表达式 [非空]    [(时间属性)]{min,max}  A-X表示24个小时 w工作日 c双休日 h节假日   **/
	public CsRule csrExpression(String csrExpression){
		this.csrExpression = csrExpression;
		this.setSeted(F.csrExpression);
		return this;
	}
	/** 优先级        **/
	public CsRule csrPriority(Short csrPriority){
		this.csrPriority = csrPriority;
		this.setSeted(F.csrPriority);
		return this;
	}
	/** 计量表达式     {M}:当前分钟 {H}当前小时 {K}当前公里 +加 -减 *乘 /除   **/
	public CsRule csrMeas(String csrMeas){
		this.csrMeas = csrMeas;
		this.setSeted(F.csrMeas);
		return this;
	}
	/** 规则描述        **/
	public CsRule csrProfile(String csrProfile){
		this.csrProfile = csrProfile;
		this.setSeted(F.csrProfile);
		return this;
	}
	/** 状态 [非空]   1:有效 0:无效     **/
	public CsRule csrStatus(Short csrStatus){
		this.csrStatus = csrStatus;
		this.setSeted(F.csrStatus);
		return this;
	}
	
	
	//克隆对象
	public CsRule clone(){
		CsRule clone = new CsRule();
		clone.csrName=this.csrName;
		clone.csrExpression=this.csrExpression;
		clone.csrStatus=this.csrStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取规则
	 * @param id
	 * @return
	 */
	public static @api CsRule get(Long id){		
		return getCsRuleById(id);
	}
	/**
	 * 获取所有规则
	 * @return
	 */
	public static @api List<CsRule> list(Map params,Integer size){
		return getCsRuleList(params,size);
	}
	/**
	 * 获取规则分页
	 * @return
	 */
	public static @api Page<CsRule> page(int page,int size,Map params){
		return getCsRulePage(page, size , params);
	}
	/**
	 * 根据查询条件取规则
	 * @param params
	 * @return
	 */
	public static @api CsRule Get(Map params){
		return getCsRule(params);
	}
	/**
	 * 获取规则数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsRuleCount(params);
	}
	/**
	 * 获取规则数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsRuleEval(eval,params);
	}
	
	/**
	 * 根据ID取规则
	 * @param id
	 * @return
	 */
	public static @api CsRule getCsRuleById(Long id){		
		CsRule csRule = (CsRule) $.GetRequest("CsRule$"+id);
		if(csRule!=null)
			return csRule;
		com.ccclubs.service.admin.ICsRuleService csRuleService = $.GetSpringBean("csRuleService");		
		return csRuleService.getCsRuleById(id);
	}
	
	
	/**
	 * 根据ID取规则的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsRule.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsRule csRule = get(id);
		if(csRule!=null){
			String strValue = csRule.getCsrName$();
			if(!"CsrName".equals("CsrName"))
				strValue+="("+csRule.getCsrName$()+")";
			MemCache.setValue(CsRule.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsrName$();
		if(!"CsrName".equals("CsrName"))
			keyValue+="("+this.getCsrName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有规则
	 * @return
	 */
	public static @api List<CsRule> getCsRuleList(Map params,Integer size){
		com.ccclubs.service.admin.ICsRuleService csRuleService = $.GetSpringBean("csRuleService");
		return csRuleService.getCsRuleList(params, size);
	}
	
	/**
	 * 获取规则分页
	 * @return
	 */
	public static @api Page<CsRule> getCsRulePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsRuleService csRuleService = $.GetSpringBean("csRuleService");
		return csRuleService.getCsRulePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取规则
	 * @param params
	 * @return
	 */
	public static @api CsRule getCsRule(Map params){
		com.ccclubs.service.admin.ICsRuleService csRuleService = $.GetSpringBean("csRuleService");
		return csRuleService.getCsRule(params);
	}
	
	/**
	 * 获取规则数
	 * @return
	 */
	public static @api Long getCsRuleCount(Map params){
		com.ccclubs.service.admin.ICsRuleService csRuleService = $.GetSpringBean("csRuleService");
		return csRuleService.getCsRuleCount(params);
	}
		
		
	/**
	 * 获取规则自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsRuleEval(String eval,Map params){
		com.ccclubs.service.admin.ICsRuleService csRuleService = $.GetSpringBean("csRuleService");
		return csRuleService.getCsRuleEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsRule(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsRuleService csRuleService = $.GetSpringBean("csRuleService");
		csRuleService.updateCsRuleByConfirm(set, where);
	}
	
	
	/**
	 * 保存规则对象
	 * @param params
	 * @return
	 */
	public CsRule save(){
		com.ccclubs.service.admin.ICsRuleService csRuleService = $.GetSpringBean("csRuleService");
		if(this.getCsrId()!=null)
			csRuleService.updateCsRule(this);
		else
			return csRuleService.saveCsRule(this);
		return this;
	}
	
	
	/**
	 * 更新规则对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsRuleService csRuleService = $.GetSpringBean("csRuleService");
		csRuleService.updateCsRule$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsRuleService csRuleService = $.GetSpringBean("csRuleService");
		if($.equals($.config("logic_delete"),"true"))
			csRuleService.removeCsRuleById(this.getCsrId());
		else
			csRuleService.deleteCsRuleById(this.getCsrId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsRuleService csRuleService = $.GetSpringBean("csRuleService");
		return csRuleService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsrId(){
		return this.csrId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsrId$(){
		String strValue="";
		 strValue=$.str(this.getCsrId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsrId(Long csrId){
		this.csrId = csrId;
		this.setSeted(F.csrId);
	}
	/*******************************规则名称**********************************/	
	/**
	* 规则名称 [非空]      
	**/
	public String getCsrName(){
		return this.csrName;
	}
	/**
	* 获取规则名称格式化(toString)
	**/
	public String getCsrName$(){
		String strValue="";
		 strValue=$.str(this.getCsrName());
	 	 return strValue;
	}
	/**
	* 规则名称 [非空]      
	**/
	public void setCsrName(String csrName){
		this.csrName = csrName;
		this.setSeted(F.csrName);
	}
	/*******************************反向关联的商品列表**********************************/	
	/**
	* 获取与当前对象反向关联的商品列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsGoods> getCsrGoods(){
		com.ccclubs.service.admin.ICsGoodsService csGoodsService = $.GetSpringBean("csGoodsService");
		Map params = new HashMap();
		params.put("csgRule",this.getCsrId());
		return csGoodsService.getCsGoodsList(params,-1);
	}
	/**
	* 获取商品列表格式化(toString)
	**/
	public String getCsrGoods$(){
		return "[...]";
	}
	/*******************************规则表达式**********************************/	
	/**
	* 规则表达式 [非空]    [(时间属性)]{min,max}  A-X表示24个小时 w工作日 c双休日 h节假日  
	**/
	public String getCsrExpression(){
		return this.csrExpression;
	}
	/**
	* 获取规则表达式格式化(toString)
	**/
	public String getCsrExpression$(){
		String strValue="";
		 strValue=$.str(this.getCsrExpression());
	 	 return strValue;
	}
	/**
	* 规则表达式 [非空]    [(时间属性)]{min,max}  A-X表示24个小时 w工作日 c双休日 h节假日  
	**/
	public void setCsrExpression(String csrExpression){
		this.csrExpression = csrExpression;
		this.setSeted(F.csrExpression);
	}
	/*******************************优先级**********************************/	
	/**
	* 优先级       
	**/
	public Short getCsrPriority(){
		return this.csrPriority;
	}
	/**
	* 获取优先级格式化(toString)
	**/
	public String getCsrPriority$(){
		String strValue="";
		 strValue=$.str(this.getCsrPriority());
	 	 return strValue;
	}
	/**
	* 优先级       
	**/
	public void setCsrPriority(Short csrPriority){
		this.csrPriority = csrPriority;
		this.setSeted(F.csrPriority);
	}
	/*******************************计量表达式**********************************/	
	/**
	* 计量表达式     {M}:当前分钟 {H}当前小时 {K}当前公里 +加 -减 *乘 /除  
	**/
	public String getCsrMeas(){
		return this.csrMeas;
	}
	/**
	* 获取计量表达式格式化(toString)
	**/
	public String getCsrMeas$(){
		String strValue="";
		 strValue=$.str(this.getCsrMeas());
	 	 return strValue;
	}
	/**
	* 计量表达式     {M}:当前分钟 {H}当前小时 {K}当前公里 +加 -减 *乘 /除  
	**/
	public void setCsrMeas(String csrMeas){
		this.csrMeas = csrMeas;
		this.setSeted(F.csrMeas);
	}
	/*******************************规则描述**********************************/	
	/**
	* 规则描述       
	**/
	public String getCsrProfile(){
		return this.csrProfile;
	}
	/**
	* 获取规则描述格式化(toString)
	**/
	public String getCsrProfile$(){
		String strValue="";
		 strValue=$.str(this.getCsrProfile());
	 	 return strValue;
	}
	/**
	* 规则描述       
	**/
	public void setCsrProfile(String csrProfile){
		this.csrProfile = csrProfile;
		this.setSeted(F.csrProfile);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:有效 0:无效    
	**/
	public Short getCsrStatus(){
		return this.csrStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsrStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsrStatus()),"1"))
			strValue=$.str("有效");		 
		 if($.equals($.str(this.getCsrStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:有效 0:无效    
	**/
	public void setCsrStatus(Short csrStatus){
		this.csrStatus = csrStatus;
		this.setSeted(F.csrStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsRule.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRule.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRule.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsRule.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsRule.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRule.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRule.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsRule.this);
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
		public M csrId(Object csrId){this.put("csrId", csrId);return this;};
	 	/** and csr_id is null */
 		public M csrIdNull(){if(this.get("csrIdNot")==null)this.put("csrIdNot", "");this.put("csrId", null);return this;};
 		/** not .... */
 		public M csrIdNot(){this.put("csrIdNot", "not");return this;};
		/** 规则名称 [非空]       **/
		public M csrName(Object csrName){this.put("csrName", csrName);return this;};
	 	/** and csr_name is null */
 		public M csrNameNull(){if(this.get("csrNameNot")==null)this.put("csrNameNot", "");this.put("csrName", null);return this;};
 		/** not .... */
 		public M csrNameNot(){this.put("csrNameNot", "not");return this;};
		/** 规则表达式 [非空]    [(时间属性)]{min,max}  A-X表示24个小时 w工作日 c双休日 h节假日   **/
		public M csrExpression(Object csrExpression){this.put("csrExpression", csrExpression);return this;};
	 	/** and csr_expression is null */
 		public M csrExpressionNull(){if(this.get("csrExpressionNot")==null)this.put("csrExpressionNot", "");this.put("csrExpression", null);return this;};
 		/** not .... */
 		public M csrExpressionNot(){this.put("csrExpressionNot", "not");return this;};
		/** 优先级        **/
		public M csrPriority(Object csrPriority){this.put("csrPriority", csrPriority);return this;};
	 	/** and csr_priority is null */
 		public M csrPriorityNull(){if(this.get("csrPriorityNot")==null)this.put("csrPriorityNot", "");this.put("csrPriority", null);return this;};
 		/** not .... */
 		public M csrPriorityNot(){this.put("csrPriorityNot", "not");return this;};
		/** and csr_priority >= ? */
		public M csrPriorityMin(Object min){this.put("csrPriorityMin", min);return this;};
		/** and csr_priority <= ? */
		public M csrPriorityMax(Object max){this.put("csrPriorityMax", max);return this;};
		/** 计量表达式     {M}:当前分钟 {H}当前小时 {K}当前公里 +加 -减 *乘 /除   **/
		public M csrMeas(Object csrMeas){this.put("csrMeas", csrMeas);return this;};
	 	/** and csr_meas is null */
 		public M csrMeasNull(){if(this.get("csrMeasNot")==null)this.put("csrMeasNot", "");this.put("csrMeas", null);return this;};
 		/** not .... */
 		public M csrMeasNot(){this.put("csrMeasNot", "not");return this;};
		/** 规则描述        **/
		public M csrProfile(Object csrProfile){this.put("csrProfile", csrProfile);return this;};
	 	/** and csr_profile is null */
 		public M csrProfileNull(){if(this.get("csrProfileNot")==null)this.put("csrProfileNot", "");this.put("csrProfile", null);return this;};
 		/** not .... */
 		public M csrProfileNot(){this.put("csrProfileNot", "not");return this;};
		/** 状态 [非空]   1:有效 0:无效     **/
		public M csrStatus(Object csrStatus){this.put("csrStatus", csrStatus);return this;};
	 	/** and csr_status is null */
 		public M csrStatusNull(){if(this.get("csrStatusNot")==null)this.put("csrStatusNot", "");this.put("csrStatus", null);return this;};
 		/** not .... */
 		public M csrStatusNot(){this.put("csrStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有规则 **/
		public @api List<CsRule> list(Integer size){
			return getCsRuleList(this,size);
		}
		/** 获取规则分页 **/
		public @api Page<CsRule> page(int page,int size){
			return getCsRulePage(page, size , this);
		}
		/** 根据查询条件取规则 **/
		public @api CsRule get(){
			return getCsRule(this);
		}
		/** 获取规则数 **/
		public @api Long count(){
			return getCsRuleCount(this);
		}
		/** 获取规则表达式 **/
		public @api <T> T eval(String strEval){
			return getCsRuleEval(strEval,this);
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
			updateCsRule(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csrId="csrId";
		/** 规则名称 [非空]       **/
		public final static @type(String.class) @like String csrName="csrName";
		/** 规则表达式 [非空]    [(时间属性)]{min,max}  A-X表示24个小时 w工作日 c双休日 h节假日   **/
		public final static @type(String.class) @like String csrExpression="csrExpression";
		/** 优先级        **/
		public final static @type(Short.class)  String csrPriority="csrPriority";
		/** and csr_priority >= ? */
		public final static @type(Short.class) String csrPriorityMin="csrPriorityMin";
		/** and csr_priority <= ? */
		public final static @type(Short.class) String csrPriorityMax="csrPriorityMax";
		/** 计量表达式     {M}:当前分钟 {H}当前小时 {K}当前公里 +加 -减 *乘 /除   **/
		public final static @type(String.class) @like String csrMeas="csrMeas";
		/** 规则描述        **/
		public final static @type(String.class) @like String csrProfile="csrProfile";
		/** 状态 [非空]   1:有效 0:无效     **/
		public final static @type(Short.class)  String csrStatus="csrStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csrId="csr_id";
		/** 规则名称 [非空]       **/
		public final static String csrName="csr_name";
		/** 规则表达式 [非空]    [(时间属性)]{min,max}  A-X表示24个小时 w工作日 c双休日 h节假日   **/
		public final static String csrExpression="csr_expression";
		/** 优先级        **/
		public final static String csrPriority="csr_priority";
		/** 计量表达式     {M}:当前分钟 {H}当前小时 {K}当前公里 +加 -减 *乘 /除   **/
		public final static String csrMeas="csr_meas";
		/** 规则描述        **/
		public final static String csrProfile="csr_profile";
		/** 状态 [非空]   1:有效 0:无效     **/
		public final static String csrStatus="csr_status";
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
				$.Set(name,CsRule.getCsRule(params));
			else
				$.Set(name,CsRule.getCsRuleList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取规则数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsRule) $.GetRequest("CsRule$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsRule.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsRule.getCsRule(params);
			else
				value = CsRule.getCsRuleList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsRule.Get($.add(CsRule.F.csrId,param));
		else if(!$.empty(param.toString()))
			value = CsRule.get(Long.valueOf(param.toString()));
		$.SetRequest("CsRule$"+param.hashCode(), value);
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
	public void mergeSet(CsRule old){
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