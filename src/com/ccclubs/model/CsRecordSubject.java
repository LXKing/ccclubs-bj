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

@namespace("configurator/recordsubject")
public @caption("消费科目") @table("cs_record_subject") class CsRecordSubject implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csrs_id")   @primary  @note("  ") Long csrsId;// 主键 非空     
	private @caption("科目名称") @column("csrs_name")    @note("  ") String csrsName;// 非空     
	private @caption("系统收入项") @column("csrs_is_income")    @note("  ") Boolean csrsIsIncome;// 非空     
	private @caption("商家收入项") @column("csrs_is_oper_in")    @note("  ") Boolean csrsIsOperIn;// 非空     
	private @caption("商家支出项") @column("csrs_is_oper_out")    @note("  ") Boolean csrsIsOperOut;// 非空     
	private @caption("会员收入项") @column("csrs_is_user_in")    @note("  ") Boolean csrsIsUserIn;// 非空     
	private @caption("会员支出项") @column("csrs_is_user_out")    @note("  ") Boolean csrsIsUserOut;// 非空     
	private @caption("人工操作项") @column("csrs_is_artif")    @note("  ") Boolean csrsIsArtif;// 非空     
	private @caption("订单相关项") @column("csrs_is_order")    @note("  ") Boolean csrsIsOrder;// 非空     
	private @caption("是否开票项") @column("csrs_is_invoice")    @note("  ") Boolean csrsIsInvoice;// 非空     
	
	//默认构造函数
	public CsRecordSubject(){
	
	}
	
	//主键构造函数
	public CsRecordSubject(Long id){
		this.csrsId = id;
	}
	
	/**所有字段构造函数 CsRecordSubject(csrsName,csrsIsIncome,csrsIsOperIn,csrsIsOperOut,csrsIsUserIn,csrsIsUserOut,csrsIsArtif,csrsIsOrder,csrsIsInvoice)
	 CsRecordSubject(
	 	$.getString("csrsName")//科目名称 [非空]
	 	,$.getBoolean("csrsIsIncome")//系统收入项 [非空]
	 	,$.getBoolean("csrsIsOperIn")//商家收入项 [非空]
	 	,$.getBoolean("csrsIsOperOut")//商家支出项 [非空]
	 	,$.getBoolean("csrsIsUserIn")//会员收入项 [非空]
	 	,$.getBoolean("csrsIsUserOut")//会员支出项 [非空]
	 	,$.getBoolean("csrsIsArtif")//人工操作项 [非空]
	 	,$.getBoolean("csrsIsOrder")//订单相关项 [非空]
	 	,$.getBoolean("csrsIsInvoice")//是否开票项 [非空]
	 )
	**/
	public CsRecordSubject(String csrsName,Boolean csrsIsIncome,Boolean csrsIsOperIn,Boolean csrsIsOperOut,Boolean csrsIsUserIn,Boolean csrsIsUserOut,Boolean csrsIsArtif,Boolean csrsIsOrder,Boolean csrsIsInvoice){
		this.csrsName=csrsName;
		this.csrsIsIncome=csrsIsIncome;
		this.csrsIsOperIn=csrsIsOperIn;
		this.csrsIsOperOut=csrsIsOperOut;
		this.csrsIsUserIn=csrsIsUserIn;
		this.csrsIsUserOut=csrsIsUserOut;
		this.csrsIsArtif=csrsIsArtif;
		this.csrsIsOrder=csrsIsOrder;
		this.csrsIsInvoice=csrsIsInvoice;
	}
	
	//设置非空字段
	public CsRecordSubject setNotNull(String csrsName,Boolean csrsIsIncome,Boolean csrsIsOperIn,Boolean csrsIsOperOut,Boolean csrsIsUserIn,Boolean csrsIsUserOut,Boolean csrsIsArtif,Boolean csrsIsOrder,Boolean csrsIsInvoice){
		this.csrsName=csrsName;
		this.csrsIsIncome=csrsIsIncome;
		this.csrsIsOperIn=csrsIsOperIn;
		this.csrsIsOperOut=csrsIsOperOut;
		this.csrsIsUserIn=csrsIsUserIn;
		this.csrsIsUserOut=csrsIsUserOut;
		this.csrsIsArtif=csrsIsArtif;
		this.csrsIsOrder=csrsIsOrder;
		this.csrsIsInvoice=csrsIsInvoice;
		return this;
	}
	/** 编号 [非空]       **/
	public CsRecordSubject csrsId(Long csrsId){
		this.csrsId = csrsId;
		this.setSeted(F.csrsId);
		return this;
	}
	/** 科目名称 [非空]       **/
	public CsRecordSubject csrsName(String csrsName){
		this.csrsName = csrsName;
		this.setSeted(F.csrsName);
		return this;
	}
	/** 系统收入项 [非空]       **/
	public CsRecordSubject csrsIsIncome(Boolean csrsIsIncome){
		this.csrsIsIncome = csrsIsIncome;
		this.setSeted(F.csrsIsIncome);
		return this;
	}
	/** 商家收入项 [非空]       **/
	public CsRecordSubject csrsIsOperIn(Boolean csrsIsOperIn){
		this.csrsIsOperIn = csrsIsOperIn;
		this.setSeted(F.csrsIsOperIn);
		return this;
	}
	/** 商家支出项 [非空]       **/
	public CsRecordSubject csrsIsOperOut(Boolean csrsIsOperOut){
		this.csrsIsOperOut = csrsIsOperOut;
		this.setSeted(F.csrsIsOperOut);
		return this;
	}
	/** 会员收入项 [非空]       **/
	public CsRecordSubject csrsIsUserIn(Boolean csrsIsUserIn){
		this.csrsIsUserIn = csrsIsUserIn;
		this.setSeted(F.csrsIsUserIn);
		return this;
	}
	/** 会员支出项 [非空]       **/
	public CsRecordSubject csrsIsUserOut(Boolean csrsIsUserOut){
		this.csrsIsUserOut = csrsIsUserOut;
		this.setSeted(F.csrsIsUserOut);
		return this;
	}
	/** 人工操作项 [非空]       **/
	public CsRecordSubject csrsIsArtif(Boolean csrsIsArtif){
		this.csrsIsArtif = csrsIsArtif;
		this.setSeted(F.csrsIsArtif);
		return this;
	}
	/** 订单相关项 [非空]       **/
	public CsRecordSubject csrsIsOrder(Boolean csrsIsOrder){
		this.csrsIsOrder = csrsIsOrder;
		this.setSeted(F.csrsIsOrder);
		return this;
	}
	/** 是否开票项 [非空]       **/
	public CsRecordSubject csrsIsInvoice(Boolean csrsIsInvoice){
		this.csrsIsInvoice = csrsIsInvoice;
		this.setSeted(F.csrsIsInvoice);
		return this;
	}
	
	
	//克隆对象
	public CsRecordSubject clone(){
		CsRecordSubject clone = new CsRecordSubject();
		clone.csrsName=this.csrsName;
		clone.csrsIsIncome=this.csrsIsIncome;
		clone.csrsIsOperIn=this.csrsIsOperIn;
		clone.csrsIsOperOut=this.csrsIsOperOut;
		clone.csrsIsUserIn=this.csrsIsUserIn;
		clone.csrsIsUserOut=this.csrsIsUserOut;
		clone.csrsIsArtif=this.csrsIsArtif;
		clone.csrsIsOrder=this.csrsIsOrder;
		clone.csrsIsInvoice=this.csrsIsInvoice;
		return clone;
	}
	
	
	/**
	 * 根据ID取消费科目
	 * @param id
	 * @return
	 */
	public static @api CsRecordSubject get(Long id){		
		return getCsRecordSubjectById(id);
	}
	/**
	 * 获取所有消费科目
	 * @return
	 */
	public static @api List<CsRecordSubject> list(Map params,Integer size){
		return getCsRecordSubjectList(params,size);
	}
	/**
	 * 获取消费科目分页
	 * @return
	 */
	public static @api Page<CsRecordSubject> page(int page,int size,Map params){
		return getCsRecordSubjectPage(page, size , params);
	}
	/**
	 * 根据查询条件取消费科目
	 * @param params
	 * @return
	 */
	public static @api CsRecordSubject Get(Map params){
		return getCsRecordSubject(params);
	}
	/**
	 * 获取消费科目数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsRecordSubjectCount(params);
	}
	/**
	 * 获取消费科目数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsRecordSubjectEval(eval,params);
	}
	
	/**
	 * 根据ID取消费科目
	 * @param id
	 * @return
	 */
	public static @api CsRecordSubject getCsRecordSubjectById(Long id){		
		CsRecordSubject csRecordSubject = (CsRecordSubject) $.GetRequest("CsRecordSubject$"+id);
		if(csRecordSubject!=null)
			return csRecordSubject;
		com.ccclubs.service.admin.ICsRecordSubjectService csRecordSubjectService = $.GetSpringBean("csRecordSubjectService");		
		return csRecordSubjectService.getCsRecordSubjectById(id);
	}
	
	
	/**
	 * 根据ID取消费科目的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsRecordSubject.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsRecordSubject csRecordSubject = get(id);
		if(csRecordSubject!=null){
			String strValue = csRecordSubject.getCsrsName$();
			if(!"CsrsName".equals("CsrsName"))
				strValue+="("+csRecordSubject.getCsrsName$()+")";
			MemCache.setValue(CsRecordSubject.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsrsName$();
		if(!"CsrsName".equals("CsrsName"))
			keyValue+="("+this.getCsrsName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有消费科目
	 * @return
	 */
	public static @api List<CsRecordSubject> getCsRecordSubjectList(Map params,Integer size){
		com.ccclubs.service.admin.ICsRecordSubjectService csRecordSubjectService = $.GetSpringBean("csRecordSubjectService");
		return csRecordSubjectService.getCsRecordSubjectList(params, size);
	}
	
	/**
	 * 获取消费科目分页
	 * @return
	 */
	public static @api Page<CsRecordSubject> getCsRecordSubjectPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsRecordSubjectService csRecordSubjectService = $.GetSpringBean("csRecordSubjectService");
		return csRecordSubjectService.getCsRecordSubjectPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取消费科目
	 * @param params
	 * @return
	 */
	public static @api CsRecordSubject getCsRecordSubject(Map params){
		com.ccclubs.service.admin.ICsRecordSubjectService csRecordSubjectService = $.GetSpringBean("csRecordSubjectService");
		return csRecordSubjectService.getCsRecordSubject(params);
	}
	
	/**
	 * 获取消费科目数
	 * @return
	 */
	public static @api Long getCsRecordSubjectCount(Map params){
		com.ccclubs.service.admin.ICsRecordSubjectService csRecordSubjectService = $.GetSpringBean("csRecordSubjectService");
		return csRecordSubjectService.getCsRecordSubjectCount(params);
	}
		
		
	/**
	 * 获取消费科目自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsRecordSubjectEval(String eval,Map params){
		com.ccclubs.service.admin.ICsRecordSubjectService csRecordSubjectService = $.GetSpringBean("csRecordSubjectService");
		return csRecordSubjectService.getCsRecordSubjectEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsRecordSubject(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsRecordSubjectService csRecordSubjectService = $.GetSpringBean("csRecordSubjectService");
		csRecordSubjectService.updateCsRecordSubjectByConfirm(set, where);
	}
	
	
	/**
	 * 保存消费科目对象
	 * @param params
	 * @return
	 */
	public CsRecordSubject save(){
		com.ccclubs.service.admin.ICsRecordSubjectService csRecordSubjectService = $.GetSpringBean("csRecordSubjectService");
		if(this.getCsrsId()!=null)
			csRecordSubjectService.updateCsRecordSubject(this);
		else
			return csRecordSubjectService.saveCsRecordSubject(this);
		return this;
	}
	
	
	/**
	 * 更新消费科目对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsRecordSubjectService csRecordSubjectService = $.GetSpringBean("csRecordSubjectService");
		csRecordSubjectService.updateCsRecordSubject$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsRecordSubjectService csRecordSubjectService = $.GetSpringBean("csRecordSubjectService");
			csRecordSubjectService.deleteCsRecordSubjectById(this.getCsrsId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsRecordSubjectService csRecordSubjectService = $.GetSpringBean("csRecordSubjectService");
		return csRecordSubjectService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsrsId(){
		return this.csrsId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsrsId$(){
		String strValue="";
		 strValue=$.str(this.getCsrsId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsrsId(Long csrsId){
		this.csrsId = csrsId;
		this.setSeted(F.csrsId);
	}
	/*******************************科目名称**********************************/	
	/**
	* 科目名称 [非空]      
	**/
	public String getCsrsName(){
		return this.csrsName;
	}
	/**
	* 获取科目名称格式化(toString)
	**/
	public String getCsrsName$(){
		String strValue="";
		 strValue=$.str(this.getCsrsName());
	 	 return strValue;
	}
	/**
	* 科目名称 [非空]      
	**/
	public void setCsrsName(String csrsName){
		this.csrsName = csrsName;
		this.setSeted(F.csrsName);
	}
	/*******************************系统收入项**********************************/	
	/**
	* 系统收入项 [非空]      
	**/
	public Boolean getCsrsIsIncome(){
		return this.csrsIsIncome;
	}
	/**
	* 获取系统收入项格式化(toString)
	**/
	public String getCsrsIsIncome$(){
		String strValue="";
		 strValue=$.str((this.getCsrsIsIncome()!=null && this.getCsrsIsIncome())?"是":"否");					 	
	 	 return strValue;
	}
	/**
	* 系统收入项 [非空]      
	**/
	public void setCsrsIsIncome(Boolean csrsIsIncome){
		this.csrsIsIncome = csrsIsIncome;
		this.setSeted(F.csrsIsIncome);
	}
	/*******************************商家收入项**********************************/	
	/**
	* 商家收入项 [非空]      
	**/
	public Boolean getCsrsIsOperIn(){
		return this.csrsIsOperIn;
	}
	/**
	* 获取商家收入项格式化(toString)
	**/
	public String getCsrsIsOperIn$(){
		String strValue="";
		 strValue=$.str((this.getCsrsIsOperIn()!=null && this.getCsrsIsOperIn())?"是":"否");					 	
	 	 return strValue;
	}
	/**
	* 商家收入项 [非空]      
	**/
	public void setCsrsIsOperIn(Boolean csrsIsOperIn){
		this.csrsIsOperIn = csrsIsOperIn;
		this.setSeted(F.csrsIsOperIn);
	}
	/*******************************商家支出项**********************************/	
	/**
	* 商家支出项 [非空]      
	**/
	public Boolean getCsrsIsOperOut(){
		return this.csrsIsOperOut;
	}
	/**
	* 获取商家支出项格式化(toString)
	**/
	public String getCsrsIsOperOut$(){
		String strValue="";
		 strValue=$.str((this.getCsrsIsOperOut()!=null && this.getCsrsIsOperOut())?"是":"否");					 	
	 	 return strValue;
	}
	/**
	* 商家支出项 [非空]      
	**/
	public void setCsrsIsOperOut(Boolean csrsIsOperOut){
		this.csrsIsOperOut = csrsIsOperOut;
		this.setSeted(F.csrsIsOperOut);
	}
	/*******************************会员收入项**********************************/	
	/**
	* 会员收入项 [非空]      
	**/
	public Boolean getCsrsIsUserIn(){
		return this.csrsIsUserIn;
	}
	/**
	* 获取会员收入项格式化(toString)
	**/
	public String getCsrsIsUserIn$(){
		String strValue="";
		 strValue=$.str((this.getCsrsIsUserIn()!=null && this.getCsrsIsUserIn())?"是":"否");					 	
	 	 return strValue;
	}
	/**
	* 会员收入项 [非空]      
	**/
	public void setCsrsIsUserIn(Boolean csrsIsUserIn){
		this.csrsIsUserIn = csrsIsUserIn;
		this.setSeted(F.csrsIsUserIn);
	}
	/*******************************会员支出项**********************************/	
	/**
	* 会员支出项 [非空]      
	**/
	public Boolean getCsrsIsUserOut(){
		return this.csrsIsUserOut;
	}
	/**
	* 获取会员支出项格式化(toString)
	**/
	public String getCsrsIsUserOut$(){
		String strValue="";
		 strValue=$.str((this.getCsrsIsUserOut()!=null && this.getCsrsIsUserOut())?"是":"否");					 	
	 	 return strValue;
	}
	/**
	* 会员支出项 [非空]      
	**/
	public void setCsrsIsUserOut(Boolean csrsIsUserOut){
		this.csrsIsUserOut = csrsIsUserOut;
		this.setSeted(F.csrsIsUserOut);
	}
	/*******************************人工操作项**********************************/	
	/**
	* 人工操作项 [非空]      
	**/
	public Boolean getCsrsIsArtif(){
		return this.csrsIsArtif;
	}
	/**
	* 获取人工操作项格式化(toString)
	**/
	public String getCsrsIsArtif$(){
		String strValue="";
		 strValue=$.str((this.getCsrsIsArtif()!=null && this.getCsrsIsArtif())?"是":"否");					 	
	 	 return strValue;
	}
	/**
	* 人工操作项 [非空]      
	**/
	public void setCsrsIsArtif(Boolean csrsIsArtif){
		this.csrsIsArtif = csrsIsArtif;
		this.setSeted(F.csrsIsArtif);
	}
	/*******************************订单相关项**********************************/	
	/**
	* 订单相关项 [非空]      
	**/
	public Boolean getCsrsIsOrder(){
		return this.csrsIsOrder;
	}
	/**
	* 获取订单相关项格式化(toString)
	**/
	public String getCsrsIsOrder$(){
		String strValue="";
		 strValue=$.str((this.getCsrsIsOrder()!=null && this.getCsrsIsOrder())?"是":"否");					 	
	 	 return strValue;
	}
	/**
	* 订单相关项 [非空]      
	**/
	public void setCsrsIsOrder(Boolean csrsIsOrder){
		this.csrsIsOrder = csrsIsOrder;
		this.setSeted(F.csrsIsOrder);
	}
	/*******************************是否开票项**********************************/	
	/**
	* 是否开票项 [非空]      
	**/
	public Boolean getCsrsIsInvoice(){
		return this.csrsIsInvoice;
	}
	/**
	* 获取是否开票项格式化(toString)
	**/
	public String getCsrsIsInvoice$(){
		String strValue="";
		 strValue=$.str((this.getCsrsIsInvoice()!=null && this.getCsrsIsInvoice())?"是":"否");					 	
	 	 return strValue;
	}
	/**
	* 是否开票项 [非空]      
	**/
	public void setCsrsIsInvoice(Boolean csrsIsInvoice){
		this.csrsIsInvoice = csrsIsInvoice;
		this.setSeted(F.csrsIsInvoice);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsRecordSubject.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRecordSubject.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRecordSubject.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsRecordSubject.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsRecordSubject.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRecordSubject.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRecordSubject.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsRecordSubject.this);
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
		public M csrsId(Object csrsId){this.put("csrsId", csrsId);return this;};
	 	/** and csrs_id is null */
 		public M csrsIdNull(){if(this.get("csrsIdNot")==null)this.put("csrsIdNot", "");this.put("csrsId", null);return this;};
 		/** not .... */
 		public M csrsIdNot(){this.put("csrsIdNot", "not");return this;};
		/** 科目名称 [非空]       **/
		public M csrsName(Object csrsName){this.put("csrsName", csrsName);return this;};
	 	/** and csrs_name is null */
 		public M csrsNameNull(){if(this.get("csrsNameNot")==null)this.put("csrsNameNot", "");this.put("csrsName", null);return this;};
 		/** not .... */
 		public M csrsNameNot(){this.put("csrsNameNot", "not");return this;};
		/** 系统收入项 [非空]       **/
		public M csrsIsIncome(Object csrsIsIncome){this.put("csrsIsIncome", csrsIsIncome);return this;};
	 	/** and csrs_is_income is null */
 		public M csrsIsIncomeNull(){if(this.get("csrsIsIncomeNot")==null)this.put("csrsIsIncomeNot", "");this.put("csrsIsIncome", null);return this;};
 		/** not .... */
 		public M csrsIsIncomeNot(){this.put("csrsIsIncomeNot", "not");return this;};
		/** 商家收入项 [非空]       **/
		public M csrsIsOperIn(Object csrsIsOperIn){this.put("csrsIsOperIn", csrsIsOperIn);return this;};
	 	/** and csrs_is_oper_in is null */
 		public M csrsIsOperInNull(){if(this.get("csrsIsOperInNot")==null)this.put("csrsIsOperInNot", "");this.put("csrsIsOperIn", null);return this;};
 		/** not .... */
 		public M csrsIsOperInNot(){this.put("csrsIsOperInNot", "not");return this;};
		/** 商家支出项 [非空]       **/
		public M csrsIsOperOut(Object csrsIsOperOut){this.put("csrsIsOperOut", csrsIsOperOut);return this;};
	 	/** and csrs_is_oper_out is null */
 		public M csrsIsOperOutNull(){if(this.get("csrsIsOperOutNot")==null)this.put("csrsIsOperOutNot", "");this.put("csrsIsOperOut", null);return this;};
 		/** not .... */
 		public M csrsIsOperOutNot(){this.put("csrsIsOperOutNot", "not");return this;};
		/** 会员收入项 [非空]       **/
		public M csrsIsUserIn(Object csrsIsUserIn){this.put("csrsIsUserIn", csrsIsUserIn);return this;};
	 	/** and csrs_is_user_in is null */
 		public M csrsIsUserInNull(){if(this.get("csrsIsUserInNot")==null)this.put("csrsIsUserInNot", "");this.put("csrsIsUserIn", null);return this;};
 		/** not .... */
 		public M csrsIsUserInNot(){this.put("csrsIsUserInNot", "not");return this;};
		/** 会员支出项 [非空]       **/
		public M csrsIsUserOut(Object csrsIsUserOut){this.put("csrsIsUserOut", csrsIsUserOut);return this;};
	 	/** and csrs_is_user_out is null */
 		public M csrsIsUserOutNull(){if(this.get("csrsIsUserOutNot")==null)this.put("csrsIsUserOutNot", "");this.put("csrsIsUserOut", null);return this;};
 		/** not .... */
 		public M csrsIsUserOutNot(){this.put("csrsIsUserOutNot", "not");return this;};
		/** 人工操作项 [非空]       **/
		public M csrsIsArtif(Object csrsIsArtif){this.put("csrsIsArtif", csrsIsArtif);return this;};
	 	/** and csrs_is_artif is null */
 		public M csrsIsArtifNull(){if(this.get("csrsIsArtifNot")==null)this.put("csrsIsArtifNot", "");this.put("csrsIsArtif", null);return this;};
 		/** not .... */
 		public M csrsIsArtifNot(){this.put("csrsIsArtifNot", "not");return this;};
		/** 订单相关项 [非空]       **/
		public M csrsIsOrder(Object csrsIsOrder){this.put("csrsIsOrder", csrsIsOrder);return this;};
	 	/** and csrs_is_order is null */
 		public M csrsIsOrderNull(){if(this.get("csrsIsOrderNot")==null)this.put("csrsIsOrderNot", "");this.put("csrsIsOrder", null);return this;};
 		/** not .... */
 		public M csrsIsOrderNot(){this.put("csrsIsOrderNot", "not");return this;};
		/** 是否开票项 [非空]       **/
		public M csrsIsInvoice(Object csrsIsInvoice){this.put("csrsIsInvoice", csrsIsInvoice);return this;};
	 	/** and csrs_is_invoice is null */
 		public M csrsIsInvoiceNull(){if(this.get("csrsIsInvoiceNot")==null)this.put("csrsIsInvoiceNot", "");this.put("csrsIsInvoice", null);return this;};
 		/** not .... */
 		public M csrsIsInvoiceNot(){this.put("csrsIsInvoiceNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有消费科目 **/
		public @api List<CsRecordSubject> list(Integer size){
			return getCsRecordSubjectList(this,size);
		}
		/** 获取消费科目分页 **/
		public @api Page<CsRecordSubject> page(int page,int size){
			return getCsRecordSubjectPage(page, size , this);
		}
		/** 根据查询条件取消费科目 **/
		public @api CsRecordSubject get(){
			return getCsRecordSubject(this);
		}
		/** 获取消费科目数 **/
		public @api Long count(){
			return getCsRecordSubjectCount(this);
		}
		/** 获取消费科目表达式 **/
		public @api <T> T eval(String strEval){
			return getCsRecordSubjectEval(strEval,this);
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
			updateCsRecordSubject(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csrsId="csrsId";
		/** 科目名称 [非空]       **/
		public final static @type(String.class) @like String csrsName="csrsName";
		/** 系统收入项 [非空]       **/
		public final static @type(Boolean.class)  String csrsIsIncome="csrsIsIncome";
		/** 商家收入项 [非空]       **/
		public final static @type(Boolean.class)  String csrsIsOperIn="csrsIsOperIn";
		/** 商家支出项 [非空]       **/
		public final static @type(Boolean.class)  String csrsIsOperOut="csrsIsOperOut";
		/** 会员收入项 [非空]       **/
		public final static @type(Boolean.class)  String csrsIsUserIn="csrsIsUserIn";
		/** 会员支出项 [非空]       **/
		public final static @type(Boolean.class)  String csrsIsUserOut="csrsIsUserOut";
		/** 人工操作项 [非空]       **/
		public final static @type(Boolean.class)  String csrsIsArtif="csrsIsArtif";
		/** 订单相关项 [非空]       **/
		public final static @type(Boolean.class)  String csrsIsOrder="csrsIsOrder";
		/** 是否开票项 [非空]       **/
		public final static @type(Boolean.class)  String csrsIsInvoice="csrsIsInvoice";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csrsId="csrs_id";
		/** 科目名称 [非空]       **/
		public final static String csrsName="csrs_name";
		/** 系统收入项 [非空]       **/
		public final static String csrsIsIncome="csrs_is_income";
		/** 商家收入项 [非空]       **/
		public final static String csrsIsOperIn="csrs_is_oper_in";
		/** 商家支出项 [非空]       **/
		public final static String csrsIsOperOut="csrs_is_oper_out";
		/** 会员收入项 [非空]       **/
		public final static String csrsIsUserIn="csrs_is_user_in";
		/** 会员支出项 [非空]       **/
		public final static String csrsIsUserOut="csrs_is_user_out";
		/** 人工操作项 [非空]       **/
		public final static String csrsIsArtif="csrs_is_artif";
		/** 订单相关项 [非空]       **/
		public final static String csrsIsOrder="csrs_is_order";
		/** 是否开票项 [非空]       **/
		public final static String csrsIsInvoice="csrs_is_invoice";
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
				$.Set(name,CsRecordSubject.getCsRecordSubject(params));
			else
				$.Set(name,CsRecordSubject.getCsRecordSubjectList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取消费科目数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsRecordSubject) $.GetRequest("CsRecordSubject$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsRecordSubject.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsRecordSubject.getCsRecordSubject(params);
			else
				value = CsRecordSubject.getCsRecordSubjectList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsRecordSubject.Get($.add(CsRecordSubject.F.csrsId,param));
		else if(!$.empty(param.toString()))
			value = CsRecordSubject.get(Long.valueOf(param.toString()));
		$.SetRequest("CsRecordSubject$"+param.hashCode(), value);
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
	public void mergeSet(CsRecordSubject old){
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