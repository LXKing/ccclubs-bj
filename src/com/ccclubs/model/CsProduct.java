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

@namespace("fee/product")
public @caption("产品") @table("cs_product") class CsProduct implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csp_id")   @primary  @note("  ") Long cspId;// 主键 非空     
	private @caption("名称") @column("csp_name")    @note("  ") String cspName;// 非空     
	private @caption("产品标识") @column("csp_flag")    @note("  ") String cspFlag;// 非空     
	//private @caption("商品列表") @RelateClass(CsGoods.class) List<CsGoods> cspGoods;//反向关联的商品列表
	private @caption("产品描述") @column("csp_profile")  @hidden   @note("  ") String cspProfile;//     
	private @caption("修改时间") @column("csp_update_time")    @note("  ") Date cspUpdateTime;// 非空     
	private @caption("添加时间") @column("csp_add_time")    @note("  ") Date cspAddTime;// 非空     
	private @caption("状态") @column("csp_status")    @note(" 1:正常 0:无效  ") Short cspStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsProduct(){
	
	}
	
	//主键构造函数
	public CsProduct(Long id){
		this.cspId = id;
	}
	
	/**所有字段构造函数 CsProduct(cspName,cspFlag,cspProfile,cspUpdateTime,cspAddTime,cspStatus)
	 CsProduct(
	 	$.getString("cspName")//名称 [非空]
	 	,$.getString("cspFlag")//产品标识 [非空]
	 	,$.getString("cspProfile")//产品描述
	 	,$.getDate("cspUpdateTime")//修改时间 [非空]
	 	,$.getDate("cspAddTime")//添加时间 [非空]
	 	,$.getShort("cspStatus")//状态 [非空]
	 )
	**/
	public CsProduct(String cspName,String cspFlag,String cspProfile,Date cspUpdateTime,Date cspAddTime,Short cspStatus){
		this.cspName=cspName;
		this.cspFlag=cspFlag;
		this.cspProfile=cspProfile;
		this.cspUpdateTime=cspUpdateTime;
		this.cspAddTime=cspAddTime;
		this.cspStatus=cspStatus;
	}
	
	//设置非空字段
	public CsProduct setNotNull(String cspName,String cspFlag,Date cspUpdateTime,Date cspAddTime,Short cspStatus){
		this.cspName=cspName;
		this.cspFlag=cspFlag;
		this.cspUpdateTime=cspUpdateTime;
		this.cspAddTime=cspAddTime;
		this.cspStatus=cspStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsProduct cspId(Long cspId){
		this.cspId = cspId;
		this.setSeted(F.cspId);
		return this;
	}
	/** 名称 [非空]       **/
	public CsProduct cspName(String cspName){
		this.cspName = cspName;
		this.setSeted(F.cspName);
		return this;
	}
	/** 产品标识 [非空]       **/
	public CsProduct cspFlag(String cspFlag){
		this.cspFlag = cspFlag;
		this.setSeted(F.cspFlag);
		return this;
	}
	/** 产品描述        **/
	public CsProduct cspProfile(String cspProfile){
		this.cspProfile = cspProfile;
		this.setSeted(F.cspProfile);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsProduct cspUpdateTime(Date cspUpdateTime){
		this.cspUpdateTime = cspUpdateTime;
		this.setSeted(F.cspUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsProduct cspAddTime(Date cspAddTime){
		this.cspAddTime = cspAddTime;
		this.setSeted(F.cspAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsProduct cspStatus(Short cspStatus){
		this.cspStatus = cspStatus;
		this.setSeted(F.cspStatus);
		return this;
	}
	
	
	//克隆对象
	public CsProduct clone(){
		CsProduct clone = new CsProduct();
		clone.cspName=this.cspName;
		clone.cspFlag=this.cspFlag;
		clone.cspUpdateTime=this.cspUpdateTime;
		clone.cspAddTime=this.cspAddTime;
		clone.cspStatus=this.cspStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取产品
	 * @param id
	 * @return
	 */
	public static @api CsProduct get(Long id){		
		return getCsProductById(id);
	}
	/**
	 * 获取所有产品
	 * @return
	 */
	public static @api List<CsProduct> list(Map params,Integer size){
		return getCsProductList(params,size);
	}
	/**
	 * 获取产品分页
	 * @return
	 */
	public static @api Page<CsProduct> page(int page,int size,Map params){
		return getCsProductPage(page, size , params);
	}
	/**
	 * 根据查询条件取产品
	 * @param params
	 * @return
	 */
	public static @api CsProduct Get(Map params){
		return getCsProduct(params);
	}
	/**
	 * 获取产品数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsProductCount(params);
	}
	/**
	 * 获取产品数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsProductEval(eval,params);
	}
	
	/**
	 * 根据ID取产品
	 * @param id
	 * @return
	 */
	public static @api CsProduct getCsProductById(Long id){		
		CsProduct csProduct = (CsProduct) $.GetRequest("CsProduct$"+id);
		if(csProduct!=null)
			return csProduct;
		com.ccclubs.service.admin.ICsProductService csProductService = $.GetSpringBean("csProductService");		
		return csProductService.getCsProductById(id);
	}
	
	
	/**
	 * 根据ID取产品的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsProduct.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsProduct csProduct = get(id);
		if(csProduct!=null){
			String strValue = csProduct.getCspName$();
			if(!"CspName".equals("CspName"))
				strValue+="("+csProduct.getCspName$()+")";
			MemCache.setValue(CsProduct.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCspName$();
		if(!"CspName".equals("CspName"))
			keyValue+="("+this.getCspName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有产品
	 * @return
	 */
	public static @api List<CsProduct> getCsProductList(Map params,Integer size){
		com.ccclubs.service.admin.ICsProductService csProductService = $.GetSpringBean("csProductService");
		return csProductService.getCsProductList(params, size);
	}
	
	/**
	 * 获取产品分页
	 * @return
	 */
	public static @api Page<CsProduct> getCsProductPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsProductService csProductService = $.GetSpringBean("csProductService");
		return csProductService.getCsProductPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取产品
	 * @param params
	 * @return
	 */
	public static @api CsProduct getCsProduct(Map params){
		com.ccclubs.service.admin.ICsProductService csProductService = $.GetSpringBean("csProductService");
		return csProductService.getCsProduct(params);
	}
	
	/**
	 * 获取产品数
	 * @return
	 */
	public static @api Long getCsProductCount(Map params){
		com.ccclubs.service.admin.ICsProductService csProductService = $.GetSpringBean("csProductService");
		return csProductService.getCsProductCount(params);
	}
		
		
	/**
	 * 获取产品自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsProductEval(String eval,Map params){
		com.ccclubs.service.admin.ICsProductService csProductService = $.GetSpringBean("csProductService");
		return csProductService.getCsProductEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsProduct(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsProductService csProductService = $.GetSpringBean("csProductService");
		csProductService.updateCsProductByConfirm(set, where);
	}
	
	
	/**
	 * 保存产品对象
	 * @param params
	 * @return
	 */
	public CsProduct save(){
		com.ccclubs.service.admin.ICsProductService csProductService = $.GetSpringBean("csProductService");
		if(this.getCspId()!=null)
			csProductService.updateCsProduct(this);
		else
			return csProductService.saveCsProduct(this);
		return this;
	}
	
	
	/**
	 * 更新产品对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsProductService csProductService = $.GetSpringBean("csProductService");
		csProductService.updateCsProduct$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsProductService csProductService = $.GetSpringBean("csProductService");
		if($.equals($.config("logic_delete"),"true"))
			csProductService.removeCsProductById(this.getCspId());
		else
			csProductService.deleteCsProductById(this.getCspId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsProductService csProductService = $.GetSpringBean("csProductService");
		return csProductService.executeTransaction(function);
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
	/*******************************名称**********************************/	
	/**
	* 名称 [非空]      
	**/
	public String getCspName(){
		return this.cspName;
	}
	/**
	* 获取名称格式化(toString)
	**/
	public String getCspName$(){
		String strValue="";
		 strValue=$.str(this.getCspName());
	 	 return strValue;
	}
	/**
	* 名称 [非空]      
	**/
	public void setCspName(String cspName){
		this.cspName = cspName;
		this.setSeted(F.cspName);
	}
	/*******************************产品标识**********************************/	
	/**
	* 产品标识 [非空]      
	**/
	public String getCspFlag(){
		return this.cspFlag;
	}
	/**
	* 获取产品标识格式化(toString)
	**/
	public String getCspFlag$(){
		String strValue="";
		 strValue=$.str(this.getCspFlag());
	 	 return strValue;
	}
	/**
	* 产品标识 [非空]      
	**/
	public void setCspFlag(String cspFlag){
		this.cspFlag = cspFlag;
		this.setSeted(F.cspFlag);
	}
	/*******************************反向关联的商品列表**********************************/	
	/**
	* 获取与当前对象反向关联的商品列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsGoods> getCspGoods(){
		com.ccclubs.service.admin.ICsGoodsService csGoodsService = $.GetSpringBean("csGoodsService");
		Map params = new HashMap();
		params.put("csgProduct",this.getCspId());
		return csGoodsService.getCsGoodsList(params,-1);
	}
	/**
	* 获取商品列表格式化(toString)
	**/
	public String getCspGoods$(){
		return "[...]";
	}
	/*******************************产品描述**********************************/	
	/**
	* 产品描述       
	**/
	public String getCspProfile(){
		return this.cspProfile;
	}
	/**
	* 获取产品描述格式化(toString)
	**/
	public String getCspProfile$(){
		String strValue="";
		 strValue=$.str(this.getCspProfile());
	 	 return strValue;
	}
	/**
	* 产品描述       
	**/
	public void setCspProfile(String cspProfile){
		this.cspProfile = cspProfile;
		this.setSeted(F.cspProfile);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCspUpdateTime(){
		return this.cspUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCspUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCspUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCspUpdateTime(Date cspUpdateTime){
		this.cspUpdateTime = cspUpdateTime;
		this.setSeted(F.cspUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCspAddTime(){
		return this.cspAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCspAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCspAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCspAddTime(Date cspAddTime){
		this.cspAddTime = cspAddTime;
		this.setSeted(F.cspAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCspStatus(){
		return this.cspStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCspStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCspStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCspStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCspStatus(Short cspStatus){
		this.cspStatus = cspStatus;
		this.setSeted(F.cspStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsProduct.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsProduct.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsProduct.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsProduct.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsProduct.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsProduct.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsProduct.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsProduct.this);
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
		/** 名称 [非空]       **/
		public M cspName(Object cspName){this.put("cspName", cspName);return this;};
	 	/** and csp_name is null */
 		public M cspNameNull(){if(this.get("cspNameNot")==null)this.put("cspNameNot", "");this.put("cspName", null);return this;};
 		/** not .... */
 		public M cspNameNot(){this.put("cspNameNot", "not");return this;};
		/** 产品标识 [非空]       **/
		public M cspFlag(Object cspFlag){this.put("cspFlag", cspFlag);return this;};
	 	/** and csp_flag is null */
 		public M cspFlagNull(){if(this.get("cspFlagNot")==null)this.put("cspFlagNot", "");this.put("cspFlag", null);return this;};
 		/** not .... */
 		public M cspFlagNot(){this.put("cspFlagNot", "not");return this;};
		/** 产品描述        **/
		public M cspProfile(Object cspProfile){this.put("cspProfile", cspProfile);return this;};
	 	/** and csp_profile is null */
 		public M cspProfileNull(){if(this.get("cspProfileNot")==null)this.put("cspProfileNot", "");this.put("cspProfile", null);return this;};
 		/** not .... */
 		public M cspProfileNot(){this.put("cspProfileNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M cspUpdateTime(Object cspUpdateTime){this.put("cspUpdateTime", cspUpdateTime);return this;};
	 	/** and csp_update_time is null */
 		public M cspUpdateTimeNull(){if(this.get("cspUpdateTimeNot")==null)this.put("cspUpdateTimeNot", "");this.put("cspUpdateTime", null);return this;};
 		/** not .... */
 		public M cspUpdateTimeNot(){this.put("cspUpdateTimeNot", "not");return this;};
 		/** and csp_update_time >= ? */
 		public M cspUpdateTimeStart(Object start){this.put("cspUpdateTimeStart", start);return this;};			
 		/** and csp_update_time <= ? */
 		public M cspUpdateTimeEnd(Object end){this.put("cspUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cspAddTime(Object cspAddTime){this.put("cspAddTime", cspAddTime);return this;};
	 	/** and csp_add_time is null */
 		public M cspAddTimeNull(){if(this.get("cspAddTimeNot")==null)this.put("cspAddTimeNot", "");this.put("cspAddTime", null);return this;};
 		/** not .... */
 		public M cspAddTimeNot(){this.put("cspAddTimeNot", "not");return this;};
 		/** and csp_add_time >= ? */
 		public M cspAddTimeStart(Object start){this.put("cspAddTimeStart", start);return this;};			
 		/** and csp_add_time <= ? */
 		public M cspAddTimeEnd(Object end){this.put("cspAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M cspStatus(Object cspStatus){this.put("cspStatus", cspStatus);return this;};
	 	/** and csp_status is null */
 		public M cspStatusNull(){if(this.get("cspStatusNot")==null)this.put("cspStatusNot", "");this.put("cspStatus", null);return this;};
 		/** not .... */
 		public M cspStatusNot(){this.put("cspStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有产品 **/
		public @api List<CsProduct> list(Integer size){
			return getCsProductList(this,size);
		}
		/** 获取产品分页 **/
		public @api Page<CsProduct> page(int page,int size){
			return getCsProductPage(page, size , this);
		}
		/** 根据查询条件取产品 **/
		public @api CsProduct get(){
			return getCsProduct(this);
		}
		/** 获取产品数 **/
		public @api Long count(){
			return getCsProductCount(this);
		}
		/** 获取产品表达式 **/
		public @api <T> T eval(String strEval){
			return getCsProductEval(strEval,this);
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
			updateCsProduct(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cspId="cspId";
		/** 名称 [非空]       **/
		public final static @type(String.class) @like String cspName="cspName";
		/** 产品标识 [非空]       **/
		public final static @type(String.class) @like String cspFlag="cspFlag";
		/** 产品描述        **/
		public final static @type(String.class) @like String cspProfile="cspProfile";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cspUpdateTime="cspUpdateTime";
	 	/** and csp_update_time >= ? */
 		public final static @type(Date.class) String cspUpdateTimeStart="cspUpdateTimeStart";
 		/** and csp_update_time <= ? */
 		public final static @type(Date.class) String cspUpdateTimeEnd="cspUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cspAddTime="cspAddTime";
	 	/** and csp_add_time >= ? */
 		public final static @type(Date.class) String cspAddTimeStart="cspAddTimeStart";
 		/** and csp_add_time <= ? */
 		public final static @type(Date.class) String cspAddTimeEnd="cspAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String cspStatus="cspStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cspId="csp_id";
		/** 名称 [非空]       **/
		public final static String cspName="csp_name";
		/** 产品标识 [非空]       **/
		public final static String cspFlag="csp_flag";
		/** 产品描述        **/
		public final static String cspProfile="csp_profile";
		/** 修改时间 [非空]       **/
		public final static String cspUpdateTime="csp_update_time";
		/** 添加时间 [非空]       **/
		public final static String cspAddTime="csp_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String cspStatus="csp_status";
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
				$.Set(name,CsProduct.getCsProduct(params));
			else
				$.Set(name,CsProduct.getCsProductList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取产品数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsProduct) $.GetRequest("CsProduct$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsProduct.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsProduct.getCsProduct(params);
			else
				value = CsProduct.getCsProductList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsProduct.Get($.add(CsProduct.F.cspId,param));
		else if(!$.empty(param.toString()))
			value = CsProduct.get(Long.valueOf(param.toString()));
		$.SetRequest("CsProduct$"+param.hashCode(), value);
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
	public void mergeSet(CsProduct old){
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