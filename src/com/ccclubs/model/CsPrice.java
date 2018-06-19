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

@namespace("fee/price")
public @caption("价格") @table("cs_price") class CsPrice implements java.io.Serializable
{
	private static final long serialVersionUID =            1l;
	private @caption("编号") @column("csp_id")   @primary  @note("  ") Long cspId;// 主键 非空     
	private @caption("计费类型") @column("csp_user_type")    @relate("$cspUserType") @RelateClass(CsUserType.class)  @note("  ") Long cspUserType;// 非空    get:hfts(lz:or(csoHost,get:host())) 
 	private CsUserType $cspUserType;//关联对象[计费方式]
	private @caption("商品") @column("csp_goods")    @relate("$cspGoods") @RelateClass(CsGoods.class)  @note("  ") Long cspGoods;// 非空     
 	private CsGoods $cspGoods;//关联对象[商品]
	private @caption("网点") @column("csp_outets")    @relate("$cspOutets") @RelateClass(CsOutlets.class)  @note(" 城市:lz:or(csoHost,get:host())  ") Long cspOutets;// 非空 城市:lz:or(csoHost,get:host())    select * from cs_outlets where cso_host=? 
 	private CsOutlets $cspOutets;//关联对象[网点]
	private @caption("车型") @column("csp_model")    @relate("$cspModel") @RelateClass(CsCarModel.class)  @note("  ") Long cspModel;// 非空    get:hms(lz:or(csoHost,get:host())) 
 	private CsCarModel $cspModel;//关联对象[车型]
	private @caption("价格") @column("csp_price")    @note("  ") Double cspPrice;// 非空     
	
	//默认构造函数
	public CsPrice(){
	
	}
	
	//主键构造函数
	public CsPrice(Long id){
		this.cspId = id;
	}
	
	/**所有字段构造函数 CsPrice(cspUserType,cspGoods,cspOutets,cspModel,cspPrice)
	 CsPrice(
	 	$.getLong("cspUserType")//计费类型 [非空]
	 	,$.getLong("cspGoods")//商品 [非空]
	 	,$.getLong("cspOutets")//网点 [非空]
	 	,$.getLong("cspModel")//车型 [非空]
	 	,$.getDouble("cspPrice")//价格 [非空]
	 )
	**/
	public CsPrice(Long cspUserType,Long cspGoods,Long cspOutets,Long cspModel,Double cspPrice){
		this.cspUserType=cspUserType;
		this.cspGoods=cspGoods;
		this.cspOutets=cspOutets;
		this.cspModel=cspModel;
		this.cspPrice=cspPrice;
	}
	
	//设置非空字段
	public CsPrice setNotNull(Long cspUserType,Long cspGoods,Long cspOutets,Long cspModel,Double cspPrice){
		this.cspUserType=cspUserType;
		this.cspGoods=cspGoods;
		this.cspOutets=cspOutets;
		this.cspModel=cspModel;
		this.cspPrice=cspPrice;
		return this;
	}
	/** 编号 [非空]       **/
	public CsPrice cspId(Long cspId){
		this.cspId = cspId;
		this.setSeted(F.cspId);
		return this;
	}
	/** 计费类型 [非空] [CsUserType]     get:hfts(lz:or(csoHost,get:host())) **/
	public CsPrice cspUserType(Long cspUserType){
		this.cspUserType = cspUserType;
		this.setSeted(F.cspUserType);
		return this;
	}
	/** 商品 [非空] [CsGoods]      **/
	public CsPrice cspGoods(Long cspGoods){
		this.cspGoods = cspGoods;
		this.setSeted(F.cspGoods);
		return this;
	}
	/** 网点 [非空] [CsOutlets]  城市:lz:or(csoHost,get:host())    select * from cs_outlets where cso_host=? **/
	public CsPrice cspOutets(Long cspOutets){
		this.cspOutets = cspOutets;
		this.setSeted(F.cspOutets);
		return this;
	}
	/** 车型 [非空] [CsCarModel]     get:hms(lz:or(csoHost,get:host())) **/
	public CsPrice cspModel(Long cspModel){
		this.cspModel = cspModel;
		this.setSeted(F.cspModel);
		return this;
	}
	/** 价格 [非空]       **/
	public CsPrice cspPrice(Double cspPrice){
		this.cspPrice = cspPrice;
		this.setSeted(F.cspPrice);
		return this;
	}
	
	
	//克隆对象
	public CsPrice clone(){
		CsPrice clone = new CsPrice();
		clone.cspUserType=this.cspUserType;
		clone.cspGoods=this.cspGoods;
		clone.cspOutets=this.cspOutets;
		clone.cspModel=this.cspModel;
		clone.cspPrice=this.cspPrice;
		return clone;
	}
	
	
	/**
	 * 根据ID取价格
	 * @param id
	 * @return
	 */
	public static @api CsPrice get(Long id){		
		return getCsPriceById(id);
	}
	/**
	 * 获取所有价格
	 * @return
	 */
	public static @api List<CsPrice> list(Map params,Integer size){
		return getCsPriceList(params,size);
	}
	/**
	 * 获取价格分页
	 * @return
	 */
	public static @api Page<CsPrice> page(int page,int size,Map params){
		return getCsPricePage(page, size , params);
	}
	/**
	 * 根据查询条件取价格
	 * @param params
	 * @return
	 */
	public static @api CsPrice Get(Map params){
		return getCsPrice(params);
	}
	/**
	 * 获取价格数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsPriceCount(params);
	}
	/**
	 * 获取价格数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsPriceEval(eval,params);
	}
	
	/**
	 * 根据ID取价格
	 * @param id
	 * @return
	 */
	public static @api CsPrice getCsPriceById(Long id){		
		CsPrice csPrice = (CsPrice) $.GetRequest("CsPrice$"+id);
		if(csPrice!=null)
			return csPrice;
		com.ccclubs.service.admin.ICsPriceService csPriceService = $.GetSpringBean("csPriceService");		
		return csPriceService.getCsPriceById(id);
	}
	
	
	/**
	 * 根据ID取价格的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsPrice.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsPrice csPrice = get(id);
		if(csPrice!=null){
			String strValue = csPrice.getCspId$();
			if(!"CspId".equals("CspId"))
				strValue+="("+csPrice.getCspId$()+")";
			MemCache.setValue(CsPrice.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCspId$();
		if(!"CspId".equals("CspId"))
			keyValue+="("+this.getCspId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有价格
	 * @return
	 */
	public static @api List<CsPrice> getCsPriceList(Map params,Integer size){
		com.ccclubs.service.admin.ICsPriceService csPriceService = $.GetSpringBean("csPriceService");
		return csPriceService.getCsPriceList(params, size);
	}
	
	/**
	 * 获取价格分页
	 * @return
	 */
	public static @api Page<CsPrice> getCsPricePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsPriceService csPriceService = $.GetSpringBean("csPriceService");
		return csPriceService.getCsPricePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取价格
	 * @param params
	 * @return
	 */
	public static @api CsPrice getCsPrice(Map params){
		com.ccclubs.service.admin.ICsPriceService csPriceService = $.GetSpringBean("csPriceService");
		return csPriceService.getCsPrice(params);
	}
	
	/**
	 * 获取价格数
	 * @return
	 */
	public static @api Long getCsPriceCount(Map params){
		com.ccclubs.service.admin.ICsPriceService csPriceService = $.GetSpringBean("csPriceService");
		return csPriceService.getCsPriceCount(params);
	}
		
		
	/**
	 * 获取价格自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsPriceEval(String eval,Map params){
		com.ccclubs.service.admin.ICsPriceService csPriceService = $.GetSpringBean("csPriceService");
		return csPriceService.getCsPriceEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsPrice(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsPriceService csPriceService = $.GetSpringBean("csPriceService");
		csPriceService.updateCsPriceByConfirm(set, where);
	}
	
	
	/**
	 * 保存价格对象
	 * @param params
	 * @return
	 */
	public CsPrice save(){
		com.ccclubs.service.admin.ICsPriceService csPriceService = $.GetSpringBean("csPriceService");
		if(this.getCspId()!=null)
			csPriceService.updateCsPrice(this);
		else
			return csPriceService.saveCsPrice(this);
		return this;
	}
	
	
	/**
	 * 更新价格对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsPriceService csPriceService = $.GetSpringBean("csPriceService");
		csPriceService.updateCsPrice$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsPriceService csPriceService = $.GetSpringBean("csPriceService");
			csPriceService.deleteCsPriceById(this.getCspId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsPriceService csPriceService = $.GetSpringBean("csPriceService");
		return csPriceService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCspId(){
		return this.cspId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCspId$(){
		String strValue="";
		 strValue=$.str(this.getCspId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCspId(Long cspId){
		this.cspId = cspId;
		this.setSeted(F.cspId);
	}
	/*******************************计费类型**********************************/	
	/**
	* 计费类型 [非空] [CsUserType]     get:hfts(lz:or(csoHost,get:host()))
	**/
	public Long getCspUserType(){
		return this.cspUserType;
	}
	/**
	* 获取计费类型格式化(toString)
	**/
	public String getCspUserType$(){
		String strValue="";
		if(this.getCspUserType()!=null){
				strValue+=$.str(CsUserType.getKeyValue(this.getCspUserType()));
		}			
	 	 return strValue;
	}
	/**
	* 计费类型 [非空] [CsUserType]     get:hfts(lz:or(csoHost,get:host()))
	**/
	public void setCspUserType(Long cspUserType){
		this.cspUserType = cspUserType;
		this.setSeted(F.cspUserType);
	}
	/**
	* 获取关联对象[计费方式]
	**/	 			
 	public CsUserType get$cspUserType(){
 		com.ccclubs.model.CsUserType csUserType = (com.ccclubs.model.CsUserType) $.GetRequest("CsUserType$"+this.getCspUserType());
 		if(csUserType!=null)
			return csUserType;
		if(this.getCspUserType()!=null){
 			csUserType = CsUserType.get(this.getCspUserType());
 		}
 		$.SetRequest("CsUserType$"+this.getCspUserType(), csUserType);
	 	return csUserType;
	}
	/*******************************商品**********************************/	
	/**
	* 商品 [非空] [CsGoods]     
	**/
	public Long getCspGoods(){
		return this.cspGoods;
	}
	/**
	* 获取商品格式化(toString)
	**/
	public String getCspGoods$(){
		String strValue="";
		if(this.getCspGoods()!=null){
				strValue+=$.str(CsGoods.getKeyValue(this.getCspGoods()));
		}			
	 	 return strValue;
	}
	/**
	* 商品 [非空] [CsGoods]     
	**/
	public void setCspGoods(Long cspGoods){
		this.cspGoods = cspGoods;
		this.setSeted(F.cspGoods);
	}
	/**
	* 获取关联对象[商品]
	**/	 			
 	public CsGoods get$cspGoods(){
 		com.ccclubs.model.CsGoods csGoods = (com.ccclubs.model.CsGoods) $.GetRequest("CsGoods$"+this.getCspGoods());
 		if(csGoods!=null)
			return csGoods;
		if(this.getCspGoods()!=null){
 			csGoods = CsGoods.get(this.getCspGoods());
 		}
 		$.SetRequest("CsGoods$"+this.getCspGoods(), csGoods);
	 	return csGoods;
	}
	/*******************************网点**********************************/	
	/**
	* 网点 [非空] [CsOutlets]  城市:lz:or(csoHost,get:host())    select * from cs_outlets where cso_host=?
	**/
	public Long getCspOutets(){
		return this.cspOutets;
	}
	/**
	* 获取网点格式化(toString)
	**/
	public String getCspOutets$(){
		String strValue="";
		if(this.getCspOutets()!=null){
				strValue+=$.str(CsOutlets.getKeyValue(this.getCspOutets()));
		}			
	 	 return strValue;
	}
	/**
	* 网点 [非空] [CsOutlets]  城市:lz:or(csoHost,get:host())    select * from cs_outlets where cso_host=?
	**/
	public void setCspOutets(Long cspOutets){
		this.cspOutets = cspOutets;
		this.setSeted(F.cspOutets);
	}
	/**
	* 获取关联对象[网点]
	**/	 			
 	public CsOutlets get$cspOutets(){
 		com.ccclubs.model.CsOutlets csOutlets = (com.ccclubs.model.CsOutlets) $.GetRequest("CsOutlets$"+this.getCspOutets());
 		if(csOutlets!=null)
			return csOutlets;
		if(this.getCspOutets()!=null){
 			csOutlets = CsOutlets.get(this.getCspOutets());
 		}
 		$.SetRequest("CsOutlets$"+this.getCspOutets(), csOutlets);
	 	return csOutlets;
	}
	/*******************************车型**********************************/	
	/**
	* 车型 [非空] [CsCarModel]     get:hms(lz:or(csoHost,get:host()))
	**/
	public Long getCspModel(){
		return this.cspModel;
	}
	/**
	* 获取车型格式化(toString)
	**/
	public String getCspModel$(){
		String strValue="";
		if(this.getCspModel()!=null){
				strValue+=$.str(CsCarModel.getKeyValue(this.getCspModel()));
		}			
	 	 return strValue;
	}
	/**
	* 车型 [非空] [CsCarModel]     get:hms(lz:or(csoHost,get:host()))
	**/
	public void setCspModel(Long cspModel){
		this.cspModel = cspModel;
		this.setSeted(F.cspModel);
	}
	/**
	* 获取关联对象[车型]
	**/	 			
 	public CsCarModel get$cspModel(){
 		com.ccclubs.model.CsCarModel csCarModel = (com.ccclubs.model.CsCarModel) $.GetRequest("CsCarModel$"+this.getCspModel());
 		if(csCarModel!=null)
			return csCarModel;
		if(this.getCspModel()!=null){
 			csCarModel = CsCarModel.get(this.getCspModel());
 		}
 		$.SetRequest("CsCarModel$"+this.getCspModel(), csCarModel);
	 	return csCarModel;
	}
	/*******************************价格**********************************/	
	/**
	* 价格 [非空]      
	**/
	public Double getCspPrice(){
		return this.cspPrice;
	}
	/**
	* 获取价格格式化(toString)
	**/
	public String getCspPrice$(){
		String strValue="";
		 strValue=$.str(this.getCspPrice());	
	 	 return strValue;
	}
	/**
	* 价格 [非空]      
	**/
	public void setCspPrice(Double cspPrice){
		this.cspPrice = cspPrice;
		this.setSeted(F.cspPrice);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsPrice.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsPrice.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsPrice.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsPrice.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsPrice.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsPrice.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsPrice.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsPrice.this);
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
		public M cspId(Object cspId){this.put("cspId", cspId);return this;};
	 	/** and csp_id is null */
 		public M cspIdNull(){if(this.get("cspIdNot")==null)this.put("cspIdNot", "");this.put("cspId", null);return this;};
 		/** not .... */
 		public M cspIdNot(){this.put("cspIdNot", "not");return this;};
		/** 计费类型 [非空] [CsUserType]     get:hfts(lz:or(csoHost,get:host())) **/
		public M cspUserType(Object cspUserType){this.put("cspUserType", cspUserType);return this;};
	 	/** and csp_user_type is null */
 		public M cspUserTypeNull(){if(this.get("cspUserTypeNot")==null)this.put("cspUserTypeNot", "");this.put("cspUserType", null);return this;};
 		/** not .... */
 		public M cspUserTypeNot(){this.put("cspUserTypeNot", "not");return this;};
		public M cspUserType$on(CsUserType.M value){
			this.put("CsUserType", value);
			this.put("cspUserType$on", value);
			return this;
		};	
		/** 商品 [非空] [CsGoods]      **/
		public M cspGoods(Object cspGoods){this.put("cspGoods", cspGoods);return this;};
	 	/** and csp_goods is null */
 		public M cspGoodsNull(){if(this.get("cspGoodsNot")==null)this.put("cspGoodsNot", "");this.put("cspGoods", null);return this;};
 		/** not .... */
 		public M cspGoodsNot(){this.put("cspGoodsNot", "not");return this;};
		public M cspGoods$on(CsGoods.M value){
			this.put("CsGoods", value);
			this.put("cspGoods$on", value);
			return this;
		};	
		/** 网点 [非空] [CsOutlets]  城市:lz:or(csoHost,get:host())    select * from cs_outlets where cso_host=? **/
		public M cspOutets(Object cspOutets){this.put("cspOutets", cspOutets);return this;};
	 	/** and csp_outets is null */
 		public M cspOutetsNull(){if(this.get("cspOutetsNot")==null)this.put("cspOutetsNot", "");this.put("cspOutets", null);return this;};
 		/** not .... */
 		public M cspOutetsNot(){this.put("cspOutetsNot", "not");return this;};
		public M cspOutets$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("cspOutets$on", value);
			return this;
		};	
		/** 车型 [非空] [CsCarModel]     get:hms(lz:or(csoHost,get:host())) **/
		public M cspModel(Object cspModel){this.put("cspModel", cspModel);return this;};
	 	/** and csp_model is null */
 		public M cspModelNull(){if(this.get("cspModelNot")==null)this.put("cspModelNot", "");this.put("cspModel", null);return this;};
 		/** not .... */
 		public M cspModelNot(){this.put("cspModelNot", "not");return this;};
		public M cspModel$on(CsCarModel.M value){
			this.put("CsCarModel", value);
			this.put("cspModel$on", value);
			return this;
		};	
		/** 价格 [非空]       **/
		public M cspPrice(Object cspPrice){this.put("cspPrice", cspPrice);return this;};
	 	/** and csp_price is null */
 		public M cspPriceNull(){if(this.get("cspPriceNot")==null)this.put("cspPriceNot", "");this.put("cspPrice", null);return this;};
 		/** not .... */
 		public M cspPriceNot(){this.put("cspPriceNot", "not");return this;};
		/** and csp_price >= ? */
		public M cspPriceMin(Object min){this.put("cspPriceMin", min);return this;};
		/** and csp_price <= ? */
		public M cspPriceMax(Object max){this.put("cspPriceMax", max);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有价格 **/
		public @api List<CsPrice> list(Integer size){
			return getCsPriceList(this,size);
		}
		/** 获取价格分页 **/
		public @api Page<CsPrice> page(int page,int size){
			return getCsPricePage(page, size , this);
		}
		/** 根据查询条件取价格 **/
		public @api CsPrice get(){
			return getCsPrice(this);
		}
		/** 获取价格数 **/
		public @api Long count(){
			return getCsPriceCount(this);
		}
		/** 获取价格表达式 **/
		public @api <T> T eval(String strEval){
			return getCsPriceEval(strEval,this);
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
			updateCsPrice(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cspId="cspId";
		/** 计费类型 [非空] [CsUserType]     get:hfts(lz:or(csoHost,get:host())) **/
		public final static @type(Long.class)  String cspUserType="cspUserType";
		/** 商品 [非空] [CsGoods]      **/
		public final static @type(Long.class)  String cspGoods="cspGoods";
		/** 网点 [非空] [CsOutlets]  城市:lz:or(csoHost,get:host())    select * from cs_outlets where cso_host=? **/
		public final static @type(Long.class)  String cspOutets="cspOutets";
		/** 车型 [非空] [CsCarModel]     get:hms(lz:or(csoHost,get:host())) **/
		public final static @type(Long.class)  String cspModel="cspModel";
		/** 价格 [非空]       **/
		public final static @type(Double.class)  String cspPrice="cspPrice";
		/** and csp_price >= ? */
		public final static @type(Double.class) String cspPriceMin="cspPriceMin";
		/** and csp_price <= ? */
		public final static @type(Double.class) String cspPriceMax="cspPriceMax";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cspId="csp_id";
		/** 计费类型 [非空] [CsUserType]     get:hfts(lz:or(csoHost,get:host())) **/
		public final static String cspUserType="csp_user_type";
		/** 商品 [非空] [CsGoods]      **/
		public final static String cspGoods="csp_goods";
		/** 网点 [非空] [CsOutlets]  城市:lz:or(csoHost,get:host())    select * from cs_outlets where cso_host=? **/
		public final static String cspOutets="csp_outets";
		/** 车型 [非空] [CsCarModel]     get:hms(lz:or(csoHost,get:host())) **/
		public final static String cspModel="csp_model";
		/** 价格 [非空]       **/
		public final static String cspPrice="csp_price";
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
				$.Set(name,CsPrice.getCsPrice(params));
			else
				$.Set(name,CsPrice.getCsPriceList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取价格数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsPrice) $.GetRequest("CsPrice$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsPrice.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsPrice.getCsPrice(params);
			else
				value = CsPrice.getCsPriceList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsPrice.Get($.add(CsPrice.F.cspId,param));
		else if(!$.empty(param.toString()))
			value = CsPrice.get(Long.valueOf(param.toString()));
		$.SetRequest("CsPrice$"+param.hashCode(), value);
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
	public void mergeSet(CsPrice old){
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