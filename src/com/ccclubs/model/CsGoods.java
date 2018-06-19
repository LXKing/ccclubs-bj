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

@namespace("fee/goods")
public @caption("商品") @table("cs_goods") class CsGoods implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csg_id")   @primary  @note("  ") Long csgId;// 主键 非空     
	private @caption("名称") @column("csg_name")    @note("  ") String csgName;// 非空     
	private @caption("所属产品") @column("csg_product")    @relate("$csgProduct") @RelateClass(CsProduct.class)  @note("  ") Long csgProduct;// 非空     
 	private CsProduct $csgProduct;//关联对象[产品]
	private @caption("计费类型") @column("csg_user_type")    @relate("$csgUserType") @RelateClass(CsUserType.class)  @note("  ") Long csgUserType;// 非空     
 	private CsUserType $csgUserType;//关联对象[计费方式]
	private @caption("单位规则") @column("csg_rule")    @relate("$csgRule") @RelateClass(CsRule.class)  @note("  ") Long csgRule;//     
 	private CsRule $csgRule;//关联对象[规则]
	private @caption("商品描述") @column("csg_profile")  @hidden   @note("  ") String csgProfile;//     
	private @caption("修改时间") @column("csg_update_time")    @note("  ") Date csgUpdateTime;// 非空     
	private @caption("添加时间") @column("csg_add_time")    @note("  ") Date csgAddTime;// 非空     
	private @caption("状态") @column("csg_status")    @note(" 1:正常 0:无效  ") Short csgStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsGoods(){
	
	}
	
	//主键构造函数
	public CsGoods(Long id){
		this.csgId = id;
	}
	
	/**所有字段构造函数 CsGoods(csgName,csgProduct,csgUserType,csgRule,csgProfile,csgUpdateTime,csgAddTime,csgStatus)
	 CsGoods(
	 	$.getString("csgName")//名称 [非空]
	 	,$.getLong("csgProduct")//所属产品 [非空]
	 	,$.getLong("csgUserType")//计费类型 [非空]
	 	,$.getLong("csgRule")//单位规则
	 	,$.getString("csgProfile")//商品描述
	 	,$.getDate("csgUpdateTime")//修改时间 [非空]
	 	,$.getDate("csgAddTime")//添加时间 [非空]
	 	,$.getShort("csgStatus")//状态 [非空]
	 )
	**/
	public CsGoods(String csgName,Long csgProduct,Long csgUserType,Long csgRule,String csgProfile,Date csgUpdateTime,Date csgAddTime,Short csgStatus){
		this.csgName=csgName;
		this.csgProduct=csgProduct;
		this.csgUserType=csgUserType;
		this.csgRule=csgRule;
		this.csgProfile=csgProfile;
		this.csgUpdateTime=csgUpdateTime;
		this.csgAddTime=csgAddTime;
		this.csgStatus=csgStatus;
	}
	
	//设置非空字段
	public CsGoods setNotNull(String csgName,Long csgProduct,Long csgUserType,Date csgUpdateTime,Date csgAddTime,Short csgStatus){
		this.csgName=csgName;
		this.csgProduct=csgProduct;
		this.csgUserType=csgUserType;
		this.csgUpdateTime=csgUpdateTime;
		this.csgAddTime=csgAddTime;
		this.csgStatus=csgStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsGoods csgId(Long csgId){
		this.csgId = csgId;
		this.setSeted(F.csgId);
		return this;
	}
	/** 名称 [非空]       **/
	public CsGoods csgName(String csgName){
		this.csgName = csgName;
		this.setSeted(F.csgName);
		return this;
	}
	/** 所属产品 [非空] [CsProduct]      **/
	public CsGoods csgProduct(Long csgProduct){
		this.csgProduct = csgProduct;
		this.setSeted(F.csgProduct);
		return this;
	}
	/** 计费类型 [非空] [CsUserType]      **/
	public CsGoods csgUserType(Long csgUserType){
		this.csgUserType = csgUserType;
		this.setSeted(F.csgUserType);
		return this;
	}
	/** 单位规则  [CsRule]      **/
	public CsGoods csgRule(Long csgRule){
		this.csgRule = csgRule;
		this.setSeted(F.csgRule);
		return this;
	}
	/** 商品描述        **/
	public CsGoods csgProfile(String csgProfile){
		this.csgProfile = csgProfile;
		this.setSeted(F.csgProfile);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsGoods csgUpdateTime(Date csgUpdateTime){
		this.csgUpdateTime = csgUpdateTime;
		this.setSeted(F.csgUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsGoods csgAddTime(Date csgAddTime){
		this.csgAddTime = csgAddTime;
		this.setSeted(F.csgAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsGoods csgStatus(Short csgStatus){
		this.csgStatus = csgStatus;
		this.setSeted(F.csgStatus);
		return this;
	}
	
	
	//克隆对象
	public CsGoods clone(){
		CsGoods clone = new CsGoods();
		clone.csgName=this.csgName;
		clone.csgProduct=this.csgProduct;
		clone.csgUserType=this.csgUserType;
		clone.csgUpdateTime=this.csgUpdateTime;
		clone.csgAddTime=this.csgAddTime;
		clone.csgStatus=this.csgStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取商品
	 * @param id
	 * @return
	 */
	public static @api CsGoods get(Long id){		
		return getCsGoodsById(id);
	}
	/**
	 * 获取所有商品
	 * @return
	 */
	public static @api List<CsGoods> list(Map params,Integer size){
		return getCsGoodsList(params,size);
	}
	/**
	 * 获取商品分页
	 * @return
	 */
	public static @api Page<CsGoods> page(int page,int size,Map params){
		return getCsGoodsPage(page, size , params);
	}
	/**
	 * 根据查询条件取商品
	 * @param params
	 * @return
	 */
	public static @api CsGoods Get(Map params){
		return getCsGoods(params);
	}
	/**
	 * 获取商品数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsGoodsCount(params);
	}
	/**
	 * 获取商品数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsGoodsEval(eval,params);
	}
	
	/**
	 * 根据ID取商品
	 * @param id
	 * @return
	 */
	public static @api CsGoods getCsGoodsById(Long id){		
		CsGoods csGoods = (CsGoods) $.GetRequest("CsGoods$"+id);
		if(csGoods!=null)
			return csGoods;
		com.ccclubs.service.admin.ICsGoodsService csGoodsService = $.GetSpringBean("csGoodsService");		
		return csGoodsService.getCsGoodsById(id);
	}
	
	
	/**
	 * 根据ID取商品的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsGoods.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsGoods csGoods = get(id);
		if(csGoods!=null){
			String strValue = csGoods.getCsgName$();
			if(!"CsgName".equals("CsgName"))
				strValue+="("+csGoods.getCsgName$()+")";
			MemCache.setValue(CsGoods.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsgName$();
		if(!"CsgName".equals("CsgName"))
			keyValue+="("+this.getCsgName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有商品
	 * @return
	 */
	public static @api List<CsGoods> getCsGoodsList(Map params,Integer size){
		com.ccclubs.service.admin.ICsGoodsService csGoodsService = $.GetSpringBean("csGoodsService");
		return csGoodsService.getCsGoodsList(params, size);
	}
	
	/**
	 * 获取商品分页
	 * @return
	 */
	public static @api Page<CsGoods> getCsGoodsPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsGoodsService csGoodsService = $.GetSpringBean("csGoodsService");
		return csGoodsService.getCsGoodsPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取商品
	 * @param params
	 * @return
	 */
	public static @api CsGoods getCsGoods(Map params){
		com.ccclubs.service.admin.ICsGoodsService csGoodsService = $.GetSpringBean("csGoodsService");
		return csGoodsService.getCsGoods(params);
	}
	
	/**
	 * 获取商品数
	 * @return
	 */
	public static @api Long getCsGoodsCount(Map params){
		com.ccclubs.service.admin.ICsGoodsService csGoodsService = $.GetSpringBean("csGoodsService");
		return csGoodsService.getCsGoodsCount(params);
	}
		
		
	/**
	 * 获取商品自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsGoodsEval(String eval,Map params){
		com.ccclubs.service.admin.ICsGoodsService csGoodsService = $.GetSpringBean("csGoodsService");
		return csGoodsService.getCsGoodsEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsGoods(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsGoodsService csGoodsService = $.GetSpringBean("csGoodsService");
		csGoodsService.updateCsGoodsByConfirm(set, where);
	}
	
	
	/**
	 * 保存商品对象
	 * @param params
	 * @return
	 */
	public CsGoods save(){
		com.ccclubs.service.admin.ICsGoodsService csGoodsService = $.GetSpringBean("csGoodsService");
		if(this.getCsgId()!=null)
			csGoodsService.updateCsGoods(this);
		else
			return csGoodsService.saveCsGoods(this);
		return this;
	}
	
	
	/**
	 * 更新商品对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsGoodsService csGoodsService = $.GetSpringBean("csGoodsService");
		csGoodsService.updateCsGoods$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsGoodsService csGoodsService = $.GetSpringBean("csGoodsService");
		if($.equals($.config("logic_delete"),"true"))
			csGoodsService.removeCsGoodsById(this.getCsgId());
		else
			csGoodsService.deleteCsGoodsById(this.getCsgId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsGoodsService csGoodsService = $.GetSpringBean("csGoodsService");
		return csGoodsService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsgId(){
		return this.csgId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsgId$(){
		String strValue="";
		 strValue=$.str(this.getCsgId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsgId(Long csgId){
		this.csgId = csgId;
		this.setSeted(F.csgId);
	}
	/*******************************名称**********************************/	
	/**
	* 名称 [非空]      
	**/
	public String getCsgName(){
		return this.csgName;
	}
	/**
	* 获取名称格式化(toString)
	**/
	public String getCsgName$(){
		String strValue="";
		 strValue=$.str(this.getCsgName());
	 	 return strValue;
	}
	/**
	* 名称 [非空]      
	**/
	public void setCsgName(String csgName){
		this.csgName = csgName;
		this.setSeted(F.csgName);
	}
	/*******************************所属产品**********************************/	
	/**
	* 所属产品 [非空] [CsProduct]     
	**/
	public Long getCsgProduct(){
		return this.csgProduct;
	}
	/**
	* 获取所属产品格式化(toString)
	**/
	public String getCsgProduct$(){
		String strValue="";
		if(this.getCsgProduct()!=null){
				strValue+=$.str(CsProduct.getKeyValue(this.getCsgProduct()));
		}			
	 	 return strValue;
	}
	/**
	* 所属产品 [非空] [CsProduct]     
	**/
	public void setCsgProduct(Long csgProduct){
		this.csgProduct = csgProduct;
		this.setSeted(F.csgProduct);
	}
	/**
	* 获取关联对象[产品]
	**/	 			
 	public CsProduct get$csgProduct(){
 		com.ccclubs.model.CsProduct csProduct = (com.ccclubs.model.CsProduct) $.GetRequest("CsProduct$"+this.getCsgProduct());
 		if(csProduct!=null)
			return csProduct;
		if(this.getCsgProduct()!=null){
 			csProduct = CsProduct.get(this.getCsgProduct());
 		}
 		$.SetRequest("CsProduct$"+this.getCsgProduct(), csProduct);
	 	return csProduct;
	}
	/*******************************计费类型**********************************/	
	/**
	* 计费类型 [非空] [CsUserType]     
	**/
	public Long getCsgUserType(){
		return this.csgUserType;
	}
	/**
	* 获取计费类型格式化(toString)
	**/
	public String getCsgUserType$(){
		String strValue="";
		if(this.getCsgUserType()!=null){
				strValue+=$.str(CsUserType.getKeyValue(this.getCsgUserType()));
		}			
	 	 return strValue;
	}
	/**
	* 计费类型 [非空] [CsUserType]     
	**/
	public void setCsgUserType(Long csgUserType){
		this.csgUserType = csgUserType;
		this.setSeted(F.csgUserType);
	}
	/**
	* 获取关联对象[计费方式]
	**/	 			
 	public CsUserType get$csgUserType(){
 		com.ccclubs.model.CsUserType csUserType = (com.ccclubs.model.CsUserType) $.GetRequest("CsUserType$"+this.getCsgUserType());
 		if(csUserType!=null)
			return csUserType;
		if(this.getCsgUserType()!=null){
 			csUserType = CsUserType.get(this.getCsgUserType());
 		}
 		$.SetRequest("CsUserType$"+this.getCsgUserType(), csUserType);
	 	return csUserType;
	}
	/*******************************单位规则**********************************/	
	/**
	* 单位规则  [CsRule]     
	**/
	public Long getCsgRule(){
		return this.csgRule;
	}
	/**
	* 获取单位规则格式化(toString)
	**/
	public String getCsgRule$(){
		String strValue="";
		if(this.getCsgRule()!=null){
				strValue+=$.str(CsRule.getKeyValue(this.getCsgRule()));
		}			
	 	 return strValue;
	}
	/**
	* 单位规则  [CsRule]     
	**/
	public void setCsgRule(Long csgRule){
		this.csgRule = csgRule;
		this.setSeted(F.csgRule);
	}
	/**
	* 获取关联对象[规则]
	**/	 			
 	public CsRule get$csgRule(){
 		com.ccclubs.model.CsRule csRule = (com.ccclubs.model.CsRule) $.GetRequest("CsRule$"+this.getCsgRule());
 		if(csRule!=null)
			return csRule;
		if(this.getCsgRule()!=null){
 			csRule = CsRule.get(this.getCsgRule());
 		}
 		$.SetRequest("CsRule$"+this.getCsgRule(), csRule);
	 	return csRule;
	}
	/*******************************商品描述**********************************/	
	/**
	* 商品描述       
	**/
	public String getCsgProfile(){
		return this.csgProfile;
	}
	/**
	* 获取商品描述格式化(toString)
	**/
	public String getCsgProfile$(){
		String strValue="";
		 strValue=$.str(this.getCsgProfile());
	 	 return strValue;
	}
	/**
	* 商品描述       
	**/
	public void setCsgProfile(String csgProfile){
		this.csgProfile = csgProfile;
		this.setSeted(F.csgProfile);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsgUpdateTime(){
		return this.csgUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsgUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsgUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsgUpdateTime(Date csgUpdateTime){
		this.csgUpdateTime = csgUpdateTime;
		this.setSeted(F.csgUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsgAddTime(){
		return this.csgAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsgAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsgAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsgAddTime(Date csgAddTime){
		this.csgAddTime = csgAddTime;
		this.setSeted(F.csgAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsgStatus(){
		return this.csgStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsgStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsgStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsgStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsgStatus(Short csgStatus){
		this.csgStatus = csgStatus;
		this.setSeted(F.csgStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsGoods.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsGoods.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsGoods.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsGoods.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsGoods.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsGoods.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsGoods.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsGoods.this);
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
		public M csgId(Object csgId){this.put("csgId", csgId);return this;};
	 	/** and csg_id is null */
 		public M csgIdNull(){if(this.get("csgIdNot")==null)this.put("csgIdNot", "");this.put("csgId", null);return this;};
 		/** not .... */
 		public M csgIdNot(){this.put("csgIdNot", "not");return this;};
		/** 名称 [非空]       **/
		public M csgName(Object csgName){this.put("csgName", csgName);return this;};
	 	/** and csg_name is null */
 		public M csgNameNull(){if(this.get("csgNameNot")==null)this.put("csgNameNot", "");this.put("csgName", null);return this;};
 		/** not .... */
 		public M csgNameNot(){this.put("csgNameNot", "not");return this;};
		/** 所属产品 [非空] [CsProduct]      **/
		public M csgProduct(Object csgProduct){this.put("csgProduct", csgProduct);return this;};
	 	/** and csg_product is null */
 		public M csgProductNull(){if(this.get("csgProductNot")==null)this.put("csgProductNot", "");this.put("csgProduct", null);return this;};
 		/** not .... */
 		public M csgProductNot(){this.put("csgProductNot", "not");return this;};
		public M csgProduct$on(CsProduct.M value){
			this.put("CsProduct", value);
			this.put("csgProduct$on", value);
			return this;
		};	
		/** 计费类型 [非空] [CsUserType]      **/
		public M csgUserType(Object csgUserType){this.put("csgUserType", csgUserType);return this;};
	 	/** and csg_user_type is null */
 		public M csgUserTypeNull(){if(this.get("csgUserTypeNot")==null)this.put("csgUserTypeNot", "");this.put("csgUserType", null);return this;};
 		/** not .... */
 		public M csgUserTypeNot(){this.put("csgUserTypeNot", "not");return this;};
		public M csgUserType$on(CsUserType.M value){
			this.put("CsUserType", value);
			this.put("csgUserType$on", value);
			return this;
		};	
		/** 单位规则  [CsRule]      **/
		public M csgRule(Object csgRule){this.put("csgRule", csgRule);return this;};
	 	/** and csg_rule is null */
 		public M csgRuleNull(){if(this.get("csgRuleNot")==null)this.put("csgRuleNot", "");this.put("csgRule", null);return this;};
 		/** not .... */
 		public M csgRuleNot(){this.put("csgRuleNot", "not");return this;};
		public M csgRule$on(CsRule.M value){
			this.put("CsRule", value);
			this.put("csgRule$on", value);
			return this;
		};	
		/** 商品描述        **/
		public M csgProfile(Object csgProfile){this.put("csgProfile", csgProfile);return this;};
	 	/** and csg_profile is null */
 		public M csgProfileNull(){if(this.get("csgProfileNot")==null)this.put("csgProfileNot", "");this.put("csgProfile", null);return this;};
 		/** not .... */
 		public M csgProfileNot(){this.put("csgProfileNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csgUpdateTime(Object csgUpdateTime){this.put("csgUpdateTime", csgUpdateTime);return this;};
	 	/** and csg_update_time is null */
 		public M csgUpdateTimeNull(){if(this.get("csgUpdateTimeNot")==null)this.put("csgUpdateTimeNot", "");this.put("csgUpdateTime", null);return this;};
 		/** not .... */
 		public M csgUpdateTimeNot(){this.put("csgUpdateTimeNot", "not");return this;};
 		/** and csg_update_time >= ? */
 		public M csgUpdateTimeStart(Object start){this.put("csgUpdateTimeStart", start);return this;};			
 		/** and csg_update_time <= ? */
 		public M csgUpdateTimeEnd(Object end){this.put("csgUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csgAddTime(Object csgAddTime){this.put("csgAddTime", csgAddTime);return this;};
	 	/** and csg_add_time is null */
 		public M csgAddTimeNull(){if(this.get("csgAddTimeNot")==null)this.put("csgAddTimeNot", "");this.put("csgAddTime", null);return this;};
 		/** not .... */
 		public M csgAddTimeNot(){this.put("csgAddTimeNot", "not");return this;};
 		/** and csg_add_time >= ? */
 		public M csgAddTimeStart(Object start){this.put("csgAddTimeStart", start);return this;};			
 		/** and csg_add_time <= ? */
 		public M csgAddTimeEnd(Object end){this.put("csgAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csgStatus(Object csgStatus){this.put("csgStatus", csgStatus);return this;};
	 	/** and csg_status is null */
 		public M csgStatusNull(){if(this.get("csgStatusNot")==null)this.put("csgStatusNot", "");this.put("csgStatus", null);return this;};
 		/** not .... */
 		public M csgStatusNot(){this.put("csgStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有商品 **/
		public @api List<CsGoods> list(Integer size){
			return getCsGoodsList(this,size);
		}
		/** 获取商品分页 **/
		public @api Page<CsGoods> page(int page,int size){
			return getCsGoodsPage(page, size , this);
		}
		/** 根据查询条件取商品 **/
		public @api CsGoods get(){
			return getCsGoods(this);
		}
		/** 获取商品数 **/
		public @api Long count(){
			return getCsGoodsCount(this);
		}
		/** 获取商品表达式 **/
		public @api <T> T eval(String strEval){
			return getCsGoodsEval(strEval,this);
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
			updateCsGoods(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csgId="csgId";
		/** 名称 [非空]       **/
		public final static @type(String.class) @like String csgName="csgName";
		/** 所属产品 [非空] [CsProduct]      **/
		public final static @type(Long.class)  String csgProduct="csgProduct";
		/** 计费类型 [非空] [CsUserType]      **/
		public final static @type(Long.class)  String csgUserType="csgUserType";
		/** 单位规则  [CsRule]      **/
		public final static @type(Long.class)  String csgRule="csgRule";
		/** 商品描述        **/
		public final static @type(String.class) @like String csgProfile="csgProfile";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csgUpdateTime="csgUpdateTime";
	 	/** and csg_update_time >= ? */
 		public final static @type(Date.class) String csgUpdateTimeStart="csgUpdateTimeStart";
 		/** and csg_update_time <= ? */
 		public final static @type(Date.class) String csgUpdateTimeEnd="csgUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csgAddTime="csgAddTime";
	 	/** and csg_add_time >= ? */
 		public final static @type(Date.class) String csgAddTimeStart="csgAddTimeStart";
 		/** and csg_add_time <= ? */
 		public final static @type(Date.class) String csgAddTimeEnd="csgAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csgStatus="csgStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csgId="csg_id";
		/** 名称 [非空]       **/
		public final static String csgName="csg_name";
		/** 所属产品 [非空] [CsProduct]      **/
		public final static String csgProduct="csg_product";
		/** 计费类型 [非空] [CsUserType]      **/
		public final static String csgUserType="csg_user_type";
		/** 单位规则  [CsRule]      **/
		public final static String csgRule="csg_rule";
		/** 商品描述        **/
		public final static String csgProfile="csg_profile";
		/** 修改时间 [非空]       **/
		public final static String csgUpdateTime="csg_update_time";
		/** 添加时间 [非空]       **/
		public final static String csgAddTime="csg_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csgStatus="csg_status";
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
				$.Set(name,CsGoods.getCsGoods(params));
			else
				$.Set(name,CsGoods.getCsGoodsList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取商品数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsGoods) $.GetRequest("CsGoods$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsGoods.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsGoods.getCsGoods(params);
			else
				value = CsGoods.getCsGoodsList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsGoods.Get($.add(CsGoods.F.csgId,param));
		else if(!$.empty(param.toString()))
			value = CsGoods.get(Long.valueOf(param.toString()));
		$.SetRequest("CsGoods$"+param.hashCode(), value);
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
	public void mergeSet(CsGoods old){
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