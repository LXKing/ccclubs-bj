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

@namespace("configurator/recordtype")
public @caption("消费类型") @table("cs_record_type") class CsRecordType implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csrt_id")   @primary  @note("  ") Long csrtId;// 主键 非空     
	private @caption("类型名称") @column("csrt_type_name")    @note("  ") String csrtTypeName;// 非空     
	private @caption("券额类型") @column("csrt_money_type")    @note(" 0:保证金 1:现金券  ") Short csrtMoneyType;// 非空 0:保证金 1:现金券     
	private @caption("是否收入项") @column("csrt_is_income")    @note("  ") Boolean csrtIsIncome;// 非空     
	private @caption("是否开票项") @column("csrt_can_invoice")    @note("  ") Boolean csrtCanInvoice;// 非空     
	private @caption("对应科目") @column("csrt_subject")    @relate("$csrtSubject") @RelateClass(CsRecordSubject.class)  @note("  ") Long csrtSubject;//     
 	private CsRecordSubject $csrtSubject;//关联对象[消费科目]
	private @caption("状态") @column("csrt_status")    @note(" 1:正常 0:无效  ") Short csrtStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsRecordType(){
	
	}
	
	//主键构造函数
	public CsRecordType(Long id){
		this.csrtId = id;
	}
	
	/**所有字段构造函数 CsRecordType(csrtTypeName,csrtMoneyType,csrtIsIncome,csrtCanInvoice,csrtSubject,csrtStatus)
	 CsRecordType(
	 	$.getString("csrtTypeName")//类型名称 [非空]
	 	,$.getShort("csrtMoneyType")//券额类型 [非空]
	 	,$.getBoolean("csrtIsIncome")//是否收入项 [非空]
	 	,$.getBoolean("csrtCanInvoice")//是否开票项 [非空]
	 	,$.getLong("csrtSubject")//对应科目
	 	,$.getShort("csrtStatus")//状态 [非空]
	 )
	**/
	public CsRecordType(String csrtTypeName,Short csrtMoneyType,Boolean csrtIsIncome,Boolean csrtCanInvoice,Long csrtSubject,Short csrtStatus){
		this.csrtTypeName=csrtTypeName;
		this.csrtMoneyType=csrtMoneyType;
		this.csrtIsIncome=csrtIsIncome;
		this.csrtCanInvoice=csrtCanInvoice;
		this.csrtSubject=csrtSubject;
		this.csrtStatus=csrtStatus;
	}
	
	//设置非空字段
	public CsRecordType setNotNull(String csrtTypeName,Short csrtMoneyType,Boolean csrtIsIncome,Boolean csrtCanInvoice,Short csrtStatus){
		this.csrtTypeName=csrtTypeName;
		this.csrtMoneyType=csrtMoneyType;
		this.csrtIsIncome=csrtIsIncome;
		this.csrtCanInvoice=csrtCanInvoice;
		this.csrtStatus=csrtStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsRecordType csrtId(Long csrtId){
		this.csrtId = csrtId;
		this.setSeted(F.csrtId);
		return this;
	}
	/** 类型名称 [非空]       **/
	public CsRecordType csrtTypeName(String csrtTypeName){
		this.csrtTypeName = csrtTypeName;
		this.setSeted(F.csrtTypeName);
		return this;
	}
	/** 券额类型 [非空]   0:保证金 1:现金券     **/
	public CsRecordType csrtMoneyType(Short csrtMoneyType){
		this.csrtMoneyType = csrtMoneyType;
		this.setSeted(F.csrtMoneyType);
		return this;
	}
	/** 是否收入项 [非空]       **/
	public CsRecordType csrtIsIncome(Boolean csrtIsIncome){
		this.csrtIsIncome = csrtIsIncome;
		this.setSeted(F.csrtIsIncome);
		return this;
	}
	/** 是否开票项 [非空]       **/
	public CsRecordType csrtCanInvoice(Boolean csrtCanInvoice){
		this.csrtCanInvoice = csrtCanInvoice;
		this.setSeted(F.csrtCanInvoice);
		return this;
	}
	/** 对应科目  [CsRecordSubject]      **/
	public CsRecordType csrtSubject(Long csrtSubject){
		this.csrtSubject = csrtSubject;
		this.setSeted(F.csrtSubject);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsRecordType csrtStatus(Short csrtStatus){
		this.csrtStatus = csrtStatus;
		this.setSeted(F.csrtStatus);
		return this;
	}
	
	
	//克隆对象
	public CsRecordType clone(){
		CsRecordType clone = new CsRecordType();
		clone.csrtTypeName=this.csrtTypeName;
		clone.csrtMoneyType=this.csrtMoneyType;
		clone.csrtIsIncome=this.csrtIsIncome;
		clone.csrtCanInvoice=this.csrtCanInvoice;
		clone.csrtStatus=this.csrtStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取消费类型
	 * @param id
	 * @return
	 */
	public static @api CsRecordType get(Long id){		
		return getCsRecordTypeById(id);
	}
	/**
	 * 获取所有消费类型
	 * @return
	 */
	public static @api List<CsRecordType> list(Map params,Integer size){
		return getCsRecordTypeList(params,size);
	}
	/**
	 * 获取消费类型分页
	 * @return
	 */
	public static @api Page<CsRecordType> page(int page,int size,Map params){
		return getCsRecordTypePage(page, size , params);
	}
	/**
	 * 根据查询条件取消费类型
	 * @param params
	 * @return
	 */
	public static @api CsRecordType Get(Map params){
		return getCsRecordType(params);
	}
	/**
	 * 获取消费类型数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsRecordTypeCount(params);
	}
	/**
	 * 获取消费类型数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsRecordTypeEval(eval,params);
	}
	
	/**
	 * 根据ID取消费类型
	 * @param id
	 * @return
	 */
	public static @api CsRecordType getCsRecordTypeById(Long id){		
		CsRecordType csRecordType = (CsRecordType) $.GetRequest("CsRecordType$"+id);
		if(csRecordType!=null)
			return csRecordType;
		com.ccclubs.service.admin.ICsRecordTypeService csRecordTypeService = $.GetSpringBean("csRecordTypeService");		
		return csRecordTypeService.getCsRecordTypeById(id);
	}
	
	
	/**
	 * 根据ID取消费类型的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsRecordType.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsRecordType csRecordType = get(id);
		if(csRecordType!=null){
			String strValue = csRecordType.getCsrtTypeName$();
			if(!"CsrtTypeName".equals("CsrtTypeName"))
				strValue+="("+csRecordType.getCsrtTypeName$()+")";
			if(!"CsrtTypeName".equals("CsrtMoneyType"))
				strValue+="("+csRecordType.getCsrtMoneyType$()+")";
			MemCache.setValue(CsRecordType.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsrtTypeName$();
		if(!"CsrtTypeName".equals("CsrtTypeName"))
			keyValue+="("+this.getCsrtTypeName$()+")";
		if(!"CsrtTypeName".equals("CsrtMoneyType"))
			keyValue+="("+this.getCsrtMoneyType$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有消费类型
	 * @return
	 */
	public static @api List<CsRecordType> getCsRecordTypeList(Map params,Integer size){
		com.ccclubs.service.admin.ICsRecordTypeService csRecordTypeService = $.GetSpringBean("csRecordTypeService");
		return csRecordTypeService.getCsRecordTypeList(params, size);
	}
	
	/**
	 * 获取消费类型分页
	 * @return
	 */
	public static @api Page<CsRecordType> getCsRecordTypePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsRecordTypeService csRecordTypeService = $.GetSpringBean("csRecordTypeService");
		return csRecordTypeService.getCsRecordTypePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取消费类型
	 * @param params
	 * @return
	 */
	public static @api CsRecordType getCsRecordType(Map params){
		com.ccclubs.service.admin.ICsRecordTypeService csRecordTypeService = $.GetSpringBean("csRecordTypeService");
		return csRecordTypeService.getCsRecordType(params);
	}
	
	/**
	 * 获取消费类型数
	 * @return
	 */
	public static @api Long getCsRecordTypeCount(Map params){
		com.ccclubs.service.admin.ICsRecordTypeService csRecordTypeService = $.GetSpringBean("csRecordTypeService");
		return csRecordTypeService.getCsRecordTypeCount(params);
	}
		
		
	/**
	 * 获取消费类型自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsRecordTypeEval(String eval,Map params){
		com.ccclubs.service.admin.ICsRecordTypeService csRecordTypeService = $.GetSpringBean("csRecordTypeService");
		return csRecordTypeService.getCsRecordTypeEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsRecordType(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsRecordTypeService csRecordTypeService = $.GetSpringBean("csRecordTypeService");
		csRecordTypeService.updateCsRecordTypeByConfirm(set, where);
	}
	
	
	/**
	 * 保存消费类型对象
	 * @param params
	 * @return
	 */
	public CsRecordType save(){
		com.ccclubs.service.admin.ICsRecordTypeService csRecordTypeService = $.GetSpringBean("csRecordTypeService");
		if(this.getCsrtId()!=null)
			csRecordTypeService.updateCsRecordType(this);
		else
			return csRecordTypeService.saveCsRecordType(this);
		return this;
	}
	
	
	/**
	 * 更新消费类型对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsRecordTypeService csRecordTypeService = $.GetSpringBean("csRecordTypeService");
		csRecordTypeService.updateCsRecordType$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsRecordTypeService csRecordTypeService = $.GetSpringBean("csRecordTypeService");
		if($.equals($.config("logic_delete"),"true"))
			csRecordTypeService.removeCsRecordTypeById(this.getCsrtId());
		else
			csRecordTypeService.deleteCsRecordTypeById(this.getCsrtId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsRecordTypeService csRecordTypeService = $.GetSpringBean("csRecordTypeService");
		return csRecordTypeService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsrtId(){
		return this.csrtId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsrtId$(){
		String strValue="";
		 strValue=$.str(this.getCsrtId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsrtId(Long csrtId){
		this.csrtId = csrtId;
		this.setSeted(F.csrtId);
	}
	/*******************************类型名称**********************************/	
	/**
	* 类型名称 [非空]      
	**/
	public String getCsrtTypeName(){
		return this.csrtTypeName;
	}
	/**
	* 获取类型名称格式化(toString)
	**/
	public String getCsrtTypeName$(){
		String strValue="";
		 strValue=$.str(this.getCsrtTypeName());
	 	 return strValue;
	}
	/**
	* 类型名称 [非空]      
	**/
	public void setCsrtTypeName(String csrtTypeName){
		this.csrtTypeName = csrtTypeName;
		this.setSeted(F.csrtTypeName);
	}
	/*******************************券额类型**********************************/	
	/**
	* 券额类型 [非空]   0:保证金 1:现金券    
	**/
	public Short getCsrtMoneyType(){
		return this.csrtMoneyType;
	}
	/**
	* 获取券额类型格式化(toString)
	**/
	public String getCsrtMoneyType$(){
		String strValue="";
		 if($.equals($.str(this.getCsrtMoneyType()),"0"))
			strValue=$.str("保证金");		 
		 if($.equals($.str(this.getCsrtMoneyType()),"1"))
			strValue=$.str("现金券");		 
	 	 return strValue;
	}
	/**
	* 券额类型 [非空]   0:保证金 1:现金券    
	**/
	public void setCsrtMoneyType(Short csrtMoneyType){
		this.csrtMoneyType = csrtMoneyType;
		this.setSeted(F.csrtMoneyType);
	}
	/*******************************是否收入项**********************************/	
	/**
	* 是否收入项 [非空]      
	**/
	public Boolean getCsrtIsIncome(){
		return this.csrtIsIncome;
	}
	/**
	* 获取是否收入项格式化(toString)
	**/
	public String getCsrtIsIncome$(){
		String strValue="";
		 strValue=$.str((this.getCsrtIsIncome()!=null && this.getCsrtIsIncome())?"是":"否");					 	
	 	 return strValue;
	}
	/**
	* 是否收入项 [非空]      
	**/
	public void setCsrtIsIncome(Boolean csrtIsIncome){
		this.csrtIsIncome = csrtIsIncome;
		this.setSeted(F.csrtIsIncome);
	}
	/*******************************是否开票项**********************************/	
	/**
	* 是否开票项 [非空]      
	**/
	public Boolean getCsrtCanInvoice(){
		return this.csrtCanInvoice;
	}
	/**
	* 获取是否开票项格式化(toString)
	**/
	public String getCsrtCanInvoice$(){
		String strValue="";
		 strValue=$.str((this.getCsrtCanInvoice()!=null && this.getCsrtCanInvoice())?"是":"否");					 	
	 	 return strValue;
	}
	/**
	* 是否开票项 [非空]      
	**/
	public void setCsrtCanInvoice(Boolean csrtCanInvoice){
		this.csrtCanInvoice = csrtCanInvoice;
		this.setSeted(F.csrtCanInvoice);
	}
	/*******************************对应科目**********************************/	
	/**
	* 对应科目  [CsRecordSubject]     
	**/
	public Long getCsrtSubject(){
		return this.csrtSubject;
	}
	/**
	* 获取对应科目格式化(toString)
	**/
	public String getCsrtSubject$(){
		String strValue="";
		if(this.getCsrtSubject()!=null){
				strValue+=$.str(CsRecordSubject.getKeyValue(this.getCsrtSubject()));
		}			
	 	 return strValue;
	}
	/**
	* 对应科目  [CsRecordSubject]     
	**/
	public void setCsrtSubject(Long csrtSubject){
		this.csrtSubject = csrtSubject;
		this.setSeted(F.csrtSubject);
	}
	/**
	* 获取关联对象[消费科目]
	**/	 			
 	public CsRecordSubject get$csrtSubject(){
 		com.ccclubs.model.CsRecordSubject csRecordSubject = (com.ccclubs.model.CsRecordSubject) $.GetRequest("CsRecordSubject$"+this.getCsrtSubject());
 		if(csRecordSubject!=null)
			return csRecordSubject;
		if(this.getCsrtSubject()!=null){
 			csRecordSubject = CsRecordSubject.get(this.getCsrtSubject());
 		}
 		$.SetRequest("CsRecordSubject$"+this.getCsrtSubject(), csRecordSubject);
	 	return csRecordSubject;
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsrtStatus(){
		return this.csrtStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsrtStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsrtStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsrtStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsrtStatus(Short csrtStatus){
		this.csrtStatus = csrtStatus;
		this.setSeted(F.csrtStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsRecordType.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRecordType.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRecordType.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsRecordType.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsRecordType.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRecordType.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRecordType.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsRecordType.this);
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
		public M csrtId(Object csrtId){this.put("csrtId", csrtId);return this;};
	 	/** and csrt_id is null */
 		public M csrtIdNull(){if(this.get("csrtIdNot")==null)this.put("csrtIdNot", "");this.put("csrtId", null);return this;};
 		/** not .... */
 		public M csrtIdNot(){this.put("csrtIdNot", "not");return this;};
		/** 类型名称 [非空]       **/
		public M csrtTypeName(Object csrtTypeName){this.put("csrtTypeName", csrtTypeName);return this;};
	 	/** and csrt_type_name is null */
 		public M csrtTypeNameNull(){if(this.get("csrtTypeNameNot")==null)this.put("csrtTypeNameNot", "");this.put("csrtTypeName", null);return this;};
 		/** not .... */
 		public M csrtTypeNameNot(){this.put("csrtTypeNameNot", "not");return this;};
		/** 券额类型 [非空]   0:保证金 1:现金券     **/
		public M csrtMoneyType(Object csrtMoneyType){this.put("csrtMoneyType", csrtMoneyType);return this;};
	 	/** and csrt_money_type is null */
 		public M csrtMoneyTypeNull(){if(this.get("csrtMoneyTypeNot")==null)this.put("csrtMoneyTypeNot", "");this.put("csrtMoneyType", null);return this;};
 		/** not .... */
 		public M csrtMoneyTypeNot(){this.put("csrtMoneyTypeNot", "not");return this;};
		/** 是否收入项 [非空]       **/
		public M csrtIsIncome(Object csrtIsIncome){this.put("csrtIsIncome", csrtIsIncome);return this;};
	 	/** and csrt_is_income is null */
 		public M csrtIsIncomeNull(){if(this.get("csrtIsIncomeNot")==null)this.put("csrtIsIncomeNot", "");this.put("csrtIsIncome", null);return this;};
 		/** not .... */
 		public M csrtIsIncomeNot(){this.put("csrtIsIncomeNot", "not");return this;};
		/** 是否开票项 [非空]       **/
		public M csrtCanInvoice(Object csrtCanInvoice){this.put("csrtCanInvoice", csrtCanInvoice);return this;};
	 	/** and csrt_can_invoice is null */
 		public M csrtCanInvoiceNull(){if(this.get("csrtCanInvoiceNot")==null)this.put("csrtCanInvoiceNot", "");this.put("csrtCanInvoice", null);return this;};
 		/** not .... */
 		public M csrtCanInvoiceNot(){this.put("csrtCanInvoiceNot", "not");return this;};
		/** 对应科目  [CsRecordSubject]      **/
		public M csrtSubject(Object csrtSubject){this.put("csrtSubject", csrtSubject);return this;};
	 	/** and csrt_subject is null */
 		public M csrtSubjectNull(){if(this.get("csrtSubjectNot")==null)this.put("csrtSubjectNot", "");this.put("csrtSubject", null);return this;};
 		/** not .... */
 		public M csrtSubjectNot(){this.put("csrtSubjectNot", "not");return this;};
		public M csrtSubject$on(CsRecordSubject.M value){
			this.put("CsRecordSubject", value);
			this.put("csrtSubject$on", value);
			return this;
		};	
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csrtStatus(Object csrtStatus){this.put("csrtStatus", csrtStatus);return this;};
	 	/** and csrt_status is null */
 		public M csrtStatusNull(){if(this.get("csrtStatusNot")==null)this.put("csrtStatusNot", "");this.put("csrtStatus", null);return this;};
 		/** not .... */
 		public M csrtStatusNot(){this.put("csrtStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有消费类型 **/
		public @api List<CsRecordType> list(Integer size){
			return getCsRecordTypeList(this,size);
		}
		/** 获取消费类型分页 **/
		public @api Page<CsRecordType> page(int page,int size){
			return getCsRecordTypePage(page, size , this);
		}
		/** 根据查询条件取消费类型 **/
		public @api CsRecordType get(){
			return getCsRecordType(this);
		}
		/** 获取消费类型数 **/
		public @api Long count(){
			return getCsRecordTypeCount(this);
		}
		/** 获取消费类型表达式 **/
		public @api <T> T eval(String strEval){
			return getCsRecordTypeEval(strEval,this);
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
			updateCsRecordType(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csrtId="csrtId";
		/** 类型名称 [非空]       **/
		public final static @type(String.class) @like String csrtTypeName="csrtTypeName";
		/** 券额类型 [非空]   0:保证金 1:现金券     **/
		public final static @type(Short.class)  String csrtMoneyType="csrtMoneyType";
		/** 是否收入项 [非空]       **/
		public final static @type(Boolean.class)  String csrtIsIncome="csrtIsIncome";
		/** 是否开票项 [非空]       **/
		public final static @type(Boolean.class)  String csrtCanInvoice="csrtCanInvoice";
		/** 对应科目  [CsRecordSubject]      **/
		public final static @type(Long.class)  String csrtSubject="csrtSubject";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csrtStatus="csrtStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csrtId="csrt_id";
		/** 类型名称 [非空]       **/
		public final static String csrtTypeName="csrt_type_name";
		/** 券额类型 [非空]   0:保证金 1:现金券     **/
		public final static String csrtMoneyType="csrt_money_type";
		/** 是否收入项 [非空]       **/
		public final static String csrtIsIncome="csrt_is_income";
		/** 是否开票项 [非空]       **/
		public final static String csrtCanInvoice="csrt_can_invoice";
		/** 对应科目  [CsRecordSubject]      **/
		public final static String csrtSubject="csrt_subject";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csrtStatus="csrt_status";
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
				$.Set(name,CsRecordType.getCsRecordType(params));
			else
				$.Set(name,CsRecordType.getCsRecordTypeList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取消费类型数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsRecordType) $.GetRequest("CsRecordType$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsRecordType.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsRecordType.getCsRecordType(params);
			else
				value = CsRecordType.getCsRecordTypeList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsRecordType.Get($.add(CsRecordType.F.csrtId,param));
		else if(!$.empty(param.toString()))
			value = CsRecordType.get(Long.valueOf(param.toString()));
		$.SetRequest("CsRecordType$"+param.hashCode(), value);
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
	public void mergeSet(CsRecordType old){
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