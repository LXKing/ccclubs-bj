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

@namespace("unit/fee")
public @caption("价格配置") @table("cs_unit_fee") class CsUnitFee implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csuf_id")   @primary  @note("  ") Long csufId;// 主键 非空     
	private @caption("城市") @column("csuf_host")    @relate("$csufHost") @RelateClass(SrvHost.class)  @note("  ") Long csufHost;// 非空     
 	private SrvHost $csufHost;//关联对象[运营城市]
	private @caption("名称") @column("csuf_name")    @note("  ") String csufName;// 非空     
	private @caption("所属单位") @column("csuf_unit_info")    @relate("$csufUnitInfo") @RelateClass(CsUnitInfo.class)  @note("  ") Long csufUnitInfo;// 非空     
 	private CsUnitInfo $csufUnitInfo;//关联对象[企业信息]
	private @caption("车型") @column("csuf_model")    @relate("$csufModel") @RelateClass(CsCarModel.class)  @note("  ") Long csufModel;// 非空     
 	private CsCarModel $csufModel;//关联对象[车型]
	private @caption("天租价格") @column("csuf_day_price")    @note("  设置为0表示不启用该价格") Double csufDayPrice;// 非空  设置为0表示不启用该价格   
	private @caption("小时价格") @column("csuf_hour_price")    @note("  设置为0表示不启用该价格") Double csufHourPrice;// 非空  设置为0表示不启用该价格   
	private @caption("里程费") @column("csuf_mileage")    @note("  设置为0表示不启用该价格") Double csufMileage;// 非空  设置为0表示不启用该价格   
	
	//默认构造函数
	public CsUnitFee(){
	
	}
	
	//主键构造函数
	public CsUnitFee(Long id){
		this.csufId = id;
	}
	
	/**所有字段构造函数 CsUnitFee(csufHost,csufName,csufUnitInfo,csufModel,csufDayPrice,csufHourPrice,csufMileage)
	 CsUnitFee(
	 	$.getLong("csufHost")//城市 [非空]
	 	,$.getString("csufName")//名称 [非空]
	 	,$.getLong("csufUnitInfo")//所属单位 [非空]
	 	,$.getLong("csufModel")//车型 [非空]
	 	,$.getDouble("csufDayPrice")//天租价格 [非空]
	 	,$.getDouble("csufHourPrice")//小时价格 [非空]
	 	,$.getDouble("csufMileage")//里程费 [非空]
	 )
	**/
	public CsUnitFee(Long csufHost,String csufName,Long csufUnitInfo,Long csufModel,Double csufDayPrice,Double csufHourPrice,Double csufMileage){
		this.csufHost=csufHost;
		this.csufName=csufName;
		this.csufUnitInfo=csufUnitInfo;
		this.csufModel=csufModel;
		this.csufDayPrice=csufDayPrice;
		this.csufHourPrice=csufHourPrice;
		this.csufMileage=csufMileage;
	}
	
	//设置非空字段
	public CsUnitFee setNotNull(Long csufHost,String csufName,Long csufUnitInfo,Long csufModel,Double csufDayPrice,Double csufHourPrice,Double csufMileage){
		this.csufHost=csufHost;
		this.csufName=csufName;
		this.csufUnitInfo=csufUnitInfo;
		this.csufModel=csufModel;
		this.csufDayPrice=csufDayPrice;
		this.csufHourPrice=csufHourPrice;
		this.csufMileage=csufMileage;
		return this;
	}
	/** 编号 [非空]       **/
	public CsUnitFee csufId(Long csufId){
		this.csufId = csufId;
		this.setSeted(F.csufId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsUnitFee csufHost(Long csufHost){
		this.csufHost = csufHost;
		this.setSeted(F.csufHost);
		return this;
	}
	/** 名称 [非空]       **/
	public CsUnitFee csufName(String csufName){
		this.csufName = csufName;
		this.setSeted(F.csufName);
		return this;
	}
	/** 所属单位 [非空] [CsUnitInfo]      **/
	public CsUnitFee csufUnitInfo(Long csufUnitInfo){
		this.csufUnitInfo = csufUnitInfo;
		this.setSeted(F.csufUnitInfo);
		return this;
	}
	/** 车型 [非空] [CsCarModel]      **/
	public CsUnitFee csufModel(Long csufModel){
		this.csufModel = csufModel;
		this.setSeted(F.csufModel);
		return this;
	}
	/** 天租价格 [非空]    设置为0表示不启用该价格   **/
	public CsUnitFee csufDayPrice(Double csufDayPrice){
		this.csufDayPrice = csufDayPrice;
		this.setSeted(F.csufDayPrice);
		return this;
	}
	/** 小时价格 [非空]    设置为0表示不启用该价格   **/
	public CsUnitFee csufHourPrice(Double csufHourPrice){
		this.csufHourPrice = csufHourPrice;
		this.setSeted(F.csufHourPrice);
		return this;
	}
	/** 里程费 [非空]    设置为0表示不启用该价格   **/
	public CsUnitFee csufMileage(Double csufMileage){
		this.csufMileage = csufMileage;
		this.setSeted(F.csufMileage);
		return this;
	}
	
	
	//克隆对象
	public CsUnitFee clone(){
		CsUnitFee clone = new CsUnitFee();
		clone.csufHost=this.csufHost;
		clone.csufName=this.csufName;
		clone.csufUnitInfo=this.csufUnitInfo;
		clone.csufModel=this.csufModel;
		clone.csufDayPrice=this.csufDayPrice;
		clone.csufHourPrice=this.csufHourPrice;
		clone.csufMileage=this.csufMileage;
		return clone;
	}
	
	
	/**
	 * 根据ID取价格配置
	 * @param id
	 * @return
	 */
	public static @api CsUnitFee get(Long id){		
		return getCsUnitFeeById(id);
	}
	/**
	 * 获取所有价格配置
	 * @return
	 */
	public static @api List<CsUnitFee> list(Map params,Integer size){
		return getCsUnitFeeList(params,size);
	}
	/**
	 * 获取价格配置分页
	 * @return
	 */
	public static @api Page<CsUnitFee> page(int page,int size,Map params){
		return getCsUnitFeePage(page, size , params);
	}
	/**
	 * 根据查询条件取价格配置
	 * @param params
	 * @return
	 */
	public static @api CsUnitFee Get(Map params){
		return getCsUnitFee(params);
	}
	/**
	 * 获取价格配置数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsUnitFeeCount(params);
	}
	/**
	 * 获取价格配置数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsUnitFeeEval(eval,params);
	}
	
	/**
	 * 根据ID取价格配置
	 * @param id
	 * @return
	 */
	public static @api CsUnitFee getCsUnitFeeById(Long id){		
		CsUnitFee csUnitFee = (CsUnitFee) $.GetRequest("CsUnitFee$"+id);
		if(csUnitFee!=null)
			return csUnitFee;
		com.ccclubs.service.admin.ICsUnitFeeService csUnitFeeService = $.GetSpringBean("csUnitFeeService");		
		return csUnitFeeService.getCsUnitFeeById(id);
	}
	
	
	/**
	 * 根据ID取价格配置的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsUnitFee.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsUnitFee csUnitFee = get(id);
		if(csUnitFee!=null){
			String strValue = csUnitFee.getCsufName$();
			if(!"CsufName".equals("CsufName"))
				strValue+="("+csUnitFee.getCsufName$()+")";
			MemCache.setValue(CsUnitFee.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsufName$();
		if(!"CsufName".equals("CsufName"))
			keyValue+="("+this.getCsufName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有价格配置
	 * @return
	 */
	public static @api List<CsUnitFee> getCsUnitFeeList(Map params,Integer size){
		com.ccclubs.service.admin.ICsUnitFeeService csUnitFeeService = $.GetSpringBean("csUnitFeeService");
		return csUnitFeeService.getCsUnitFeeList(params, size);
	}
	
	/**
	 * 获取价格配置分页
	 * @return
	 */
	public static @api Page<CsUnitFee> getCsUnitFeePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsUnitFeeService csUnitFeeService = $.GetSpringBean("csUnitFeeService");
		return csUnitFeeService.getCsUnitFeePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取价格配置
	 * @param params
	 * @return
	 */
	public static @api CsUnitFee getCsUnitFee(Map params){
		com.ccclubs.service.admin.ICsUnitFeeService csUnitFeeService = $.GetSpringBean("csUnitFeeService");
		return csUnitFeeService.getCsUnitFee(params);
	}
	
	/**
	 * 获取价格配置数
	 * @return
	 */
	public static @api Long getCsUnitFeeCount(Map params){
		com.ccclubs.service.admin.ICsUnitFeeService csUnitFeeService = $.GetSpringBean("csUnitFeeService");
		return csUnitFeeService.getCsUnitFeeCount(params);
	}
		
		
	/**
	 * 获取价格配置自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsUnitFeeEval(String eval,Map params){
		com.ccclubs.service.admin.ICsUnitFeeService csUnitFeeService = $.GetSpringBean("csUnitFeeService");
		return csUnitFeeService.getCsUnitFeeEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsUnitFee(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsUnitFeeService csUnitFeeService = $.GetSpringBean("csUnitFeeService");
		csUnitFeeService.updateCsUnitFeeByConfirm(set, where);
	}
	
	
	/**
	 * 保存价格配置对象
	 * @param params
	 * @return
	 */
	public CsUnitFee save(){
		com.ccclubs.service.admin.ICsUnitFeeService csUnitFeeService = $.GetSpringBean("csUnitFeeService");
		if(this.getCsufId()!=null)
			csUnitFeeService.updateCsUnitFee(this);
		else
			return csUnitFeeService.saveCsUnitFee(this);
		return this;
	}
	
	
	/**
	 * 更新价格配置对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsUnitFeeService csUnitFeeService = $.GetSpringBean("csUnitFeeService");
		csUnitFeeService.updateCsUnitFee$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsUnitFeeService csUnitFeeService = $.GetSpringBean("csUnitFeeService");
			csUnitFeeService.deleteCsUnitFeeById(this.getCsufId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsUnitFeeService csUnitFeeService = $.GetSpringBean("csUnitFeeService");
		return csUnitFeeService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsufId(){
		return this.csufId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsufId$(){
		String strValue="";
		 strValue=$.str(this.getCsufId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsufId(Long csufId){
		this.csufId = csufId;
		this.setSeted(F.csufId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsufHost(){
		return this.csufHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsufHost$(){
		String strValue="";
		if(this.getCsufHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsufHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsufHost(Long csufHost){
		this.csufHost = csufHost;
		this.setSeted(F.csufHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csufHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsufHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsufHost()!=null){
 			srvHost = SrvHost.get(this.getCsufHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsufHost(), srvHost);
	 	return srvHost;
	}
	/*******************************名称**********************************/	
	/**
	* 名称 [非空]      
	**/
	public String getCsufName(){
		return this.csufName;
	}
	/**
	* 获取名称格式化(toString)
	**/
	public String getCsufName$(){
		String strValue="";
		 strValue=$.str(this.getCsufName());
	 	 return strValue;
	}
	/**
	* 名称 [非空]      
	**/
	public void setCsufName(String csufName){
		this.csufName = csufName;
		this.setSeted(F.csufName);
	}
	/*******************************所属单位**********************************/	
	/**
	* 所属单位 [非空] [CsUnitInfo]     
	**/
	public Long getCsufUnitInfo(){
		return this.csufUnitInfo;
	}
	/**
	* 获取所属单位格式化(toString)
	**/
	public String getCsufUnitInfo$(){
		String strValue="";
		if(this.getCsufUnitInfo()!=null){
				strValue+=$.str(CsUnitInfo.getKeyValue(this.getCsufUnitInfo()));
		}			
	 	 return strValue;
	}
	/**
	* 所属单位 [非空] [CsUnitInfo]     
	**/
	public void setCsufUnitInfo(Long csufUnitInfo){
		this.csufUnitInfo = csufUnitInfo;
		this.setSeted(F.csufUnitInfo);
	}
	/**
	* 获取关联对象[企业信息]
	**/	 			
 	public CsUnitInfo get$csufUnitInfo(){
 		com.ccclubs.model.CsUnitInfo csUnitInfo = (com.ccclubs.model.CsUnitInfo) $.GetRequest("CsUnitInfo$"+this.getCsufUnitInfo());
 		if(csUnitInfo!=null)
			return csUnitInfo;
		if(this.getCsufUnitInfo()!=null){
 			csUnitInfo = CsUnitInfo.get(this.getCsufUnitInfo());
 		}
 		$.SetRequest("CsUnitInfo$"+this.getCsufUnitInfo(), csUnitInfo);
	 	return csUnitInfo;
	}
	/*******************************车型**********************************/	
	/**
	* 车型 [非空] [CsCarModel]     
	**/
	public Long getCsufModel(){
		return this.csufModel;
	}
	/**
	* 获取车型格式化(toString)
	**/
	public String getCsufModel$(){
		String strValue="";
		if(this.getCsufModel()!=null){
				strValue+=$.str(CsCarModel.getKeyValue(this.getCsufModel()));
		}			
	 	 return strValue;
	}
	/**
	* 车型 [非空] [CsCarModel]     
	**/
	public void setCsufModel(Long csufModel){
		this.csufModel = csufModel;
		this.setSeted(F.csufModel);
	}
	/**
	* 获取关联对象[车型]
	**/	 			
 	public CsCarModel get$csufModel(){
 		com.ccclubs.model.CsCarModel csCarModel = (com.ccclubs.model.CsCarModel) $.GetRequest("CsCarModel$"+this.getCsufModel());
 		if(csCarModel!=null)
			return csCarModel;
		if(this.getCsufModel()!=null){
 			csCarModel = CsCarModel.get(this.getCsufModel());
 		}
 		$.SetRequest("CsCarModel$"+this.getCsufModel(), csCarModel);
	 	return csCarModel;
	}
	/*******************************天租价格**********************************/	
	/**
	* 天租价格 [非空]    设置为0表示不启用该价格  
	**/
	public Double getCsufDayPrice(){
		return this.csufDayPrice;
	}
	/**
	* 获取天租价格格式化(toString)
	**/
	public String getCsufDayPrice$(){
		String strValue="";
		 strValue=$.str(this.getCsufDayPrice());	
	 	 return strValue;
	}
	/**
	* 天租价格 [非空]    设置为0表示不启用该价格  
	**/
	public void setCsufDayPrice(Double csufDayPrice){
		this.csufDayPrice = csufDayPrice;
		this.setSeted(F.csufDayPrice);
	}
	/*******************************小时价格**********************************/	
	/**
	* 小时价格 [非空]    设置为0表示不启用该价格  
	**/
	public Double getCsufHourPrice(){
		return this.csufHourPrice;
	}
	/**
	* 获取小时价格格式化(toString)
	**/
	public String getCsufHourPrice$(){
		String strValue="";
		 strValue=$.str(this.getCsufHourPrice());	
	 	 return strValue;
	}
	/**
	* 小时价格 [非空]    设置为0表示不启用该价格  
	**/
	public void setCsufHourPrice(Double csufHourPrice){
		this.csufHourPrice = csufHourPrice;
		this.setSeted(F.csufHourPrice);
	}
	/*******************************里程费**********************************/	
	/**
	* 里程费 [非空]    设置为0表示不启用该价格  
	**/
	public Double getCsufMileage(){
		return this.csufMileage;
	}
	/**
	* 获取里程费格式化(toString)
	**/
	public String getCsufMileage$(){
		String strValue="";
		 strValue=$.str(this.getCsufMileage());	
	 	 return strValue;
	}
	/**
	* 里程费 [非空]    设置为0表示不启用该价格  
	**/
	public void setCsufMileage(Double csufMileage){
		this.csufMileage = csufMileage;
		this.setSeted(F.csufMileage);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsUnitFee.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUnitFee.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUnitFee.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsUnitFee.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsUnitFee.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUnitFee.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUnitFee.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsUnitFee.this);
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
		public M csufId(Object csufId){this.put("csufId", csufId);return this;};
	 	/** and csuf_id is null */
 		public M csufIdNull(){if(this.get("csufIdNot")==null)this.put("csufIdNot", "");this.put("csufId", null);return this;};
 		/** not .... */
 		public M csufIdNot(){this.put("csufIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csufHost(Object csufHost){this.put("csufHost", csufHost);return this;};
	 	/** and csuf_host is null */
 		public M csufHostNull(){if(this.get("csufHostNot")==null)this.put("csufHostNot", "");this.put("csufHost", null);return this;};
 		/** not .... */
 		public M csufHostNot(){this.put("csufHostNot", "not");return this;};
		/** 名称 [非空]       **/
		public M csufName(Object csufName){this.put("csufName", csufName);return this;};
	 	/** and csuf_name is null */
 		public M csufNameNull(){if(this.get("csufNameNot")==null)this.put("csufNameNot", "");this.put("csufName", null);return this;};
 		/** not .... */
 		public M csufNameNot(){this.put("csufNameNot", "not");return this;};
		/** 所属单位 [非空] [CsUnitInfo]      **/
		public M csufUnitInfo(Object csufUnitInfo){this.put("csufUnitInfo", csufUnitInfo);return this;};
	 	/** and csuf_unit_info is null */
 		public M csufUnitInfoNull(){if(this.get("csufUnitInfoNot")==null)this.put("csufUnitInfoNot", "");this.put("csufUnitInfo", null);return this;};
 		/** not .... */
 		public M csufUnitInfoNot(){this.put("csufUnitInfoNot", "not");return this;};
		public M csufUnitInfo$on(CsUnitInfo.M value){
			this.put("CsUnitInfo", value);
			this.put("csufUnitInfo$on", value);
			return this;
		};	
		/** 车型 [非空] [CsCarModel]      **/
		public M csufModel(Object csufModel){this.put("csufModel", csufModel);return this;};
	 	/** and csuf_model is null */
 		public M csufModelNull(){if(this.get("csufModelNot")==null)this.put("csufModelNot", "");this.put("csufModel", null);return this;};
 		/** not .... */
 		public M csufModelNot(){this.put("csufModelNot", "not");return this;};
		public M csufModel$on(CsCarModel.M value){
			this.put("CsCarModel", value);
			this.put("csufModel$on", value);
			return this;
		};	
		/** 天租价格 [非空]    设置为0表示不启用该价格   **/
		public M csufDayPrice(Object csufDayPrice){this.put("csufDayPrice", csufDayPrice);return this;};
	 	/** and csuf_day_price is null */
 		public M csufDayPriceNull(){if(this.get("csufDayPriceNot")==null)this.put("csufDayPriceNot", "");this.put("csufDayPrice", null);return this;};
 		/** not .... */
 		public M csufDayPriceNot(){this.put("csufDayPriceNot", "not");return this;};
		/** and csuf_day_price >= ? */
		public M csufDayPriceMin(Object min){this.put("csufDayPriceMin", min);return this;};
		/** and csuf_day_price <= ? */
		public M csufDayPriceMax(Object max){this.put("csufDayPriceMax", max);return this;};
		/** 小时价格 [非空]    设置为0表示不启用该价格   **/
		public M csufHourPrice(Object csufHourPrice){this.put("csufHourPrice", csufHourPrice);return this;};
	 	/** and csuf_hour_price is null */
 		public M csufHourPriceNull(){if(this.get("csufHourPriceNot")==null)this.put("csufHourPriceNot", "");this.put("csufHourPrice", null);return this;};
 		/** not .... */
 		public M csufHourPriceNot(){this.put("csufHourPriceNot", "not");return this;};
		/** and csuf_hour_price >= ? */
		public M csufHourPriceMin(Object min){this.put("csufHourPriceMin", min);return this;};
		/** and csuf_hour_price <= ? */
		public M csufHourPriceMax(Object max){this.put("csufHourPriceMax", max);return this;};
		/** 里程费 [非空]    设置为0表示不启用该价格   **/
		public M csufMileage(Object csufMileage){this.put("csufMileage", csufMileage);return this;};
	 	/** and csuf_mileage is null */
 		public M csufMileageNull(){if(this.get("csufMileageNot")==null)this.put("csufMileageNot", "");this.put("csufMileage", null);return this;};
 		/** not .... */
 		public M csufMileageNot(){this.put("csufMileageNot", "not");return this;};
		/** and csuf_mileage >= ? */
		public M csufMileageMin(Object min){this.put("csufMileageMin", min);return this;};
		/** and csuf_mileage <= ? */
		public M csufMileageMax(Object max){this.put("csufMileageMax", max);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有价格配置 **/
		public @api List<CsUnitFee> list(Integer size){
			return getCsUnitFeeList(this,size);
		}
		/** 获取价格配置分页 **/
		public @api Page<CsUnitFee> page(int page,int size){
			return getCsUnitFeePage(page, size , this);
		}
		/** 根据查询条件取价格配置 **/
		public @api CsUnitFee get(){
			return getCsUnitFee(this);
		}
		/** 获取价格配置数 **/
		public @api Long count(){
			return getCsUnitFeeCount(this);
		}
		/** 获取价格配置表达式 **/
		public @api <T> T eval(String strEval){
			return getCsUnitFeeEval(strEval,this);
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
			updateCsUnitFee(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csufId="csufId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csufHost="csufHost";
		/** 名称 [非空]       **/
		public final static @type(String.class) @like String csufName="csufName";
		/** 所属单位 [非空] [CsUnitInfo]      **/
		public final static @type(Long.class)  String csufUnitInfo="csufUnitInfo";
		/** 车型 [非空] [CsCarModel]      **/
		public final static @type(Long.class)  String csufModel="csufModel";
		/** 天租价格 [非空]    设置为0表示不启用该价格   **/
		public final static @type(Double.class)  String csufDayPrice="csufDayPrice";
		/** and csuf_day_price >= ? */
		public final static @type(Double.class) String csufDayPriceMin="csufDayPriceMin";
		/** and csuf_day_price <= ? */
		public final static @type(Double.class) String csufDayPriceMax="csufDayPriceMax";
		/** 小时价格 [非空]    设置为0表示不启用该价格   **/
		public final static @type(Double.class)  String csufHourPrice="csufHourPrice";
		/** and csuf_hour_price >= ? */
		public final static @type(Double.class) String csufHourPriceMin="csufHourPriceMin";
		/** and csuf_hour_price <= ? */
		public final static @type(Double.class) String csufHourPriceMax="csufHourPriceMax";
		/** 里程费 [非空]    设置为0表示不启用该价格   **/
		public final static @type(Double.class)  String csufMileage="csufMileage";
		/** and csuf_mileage >= ? */
		public final static @type(Double.class) String csufMileageMin="csufMileageMin";
		/** and csuf_mileage <= ? */
		public final static @type(Double.class) String csufMileageMax="csufMileageMax";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csufId="csuf_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csufHost="csuf_host";
		/** 名称 [非空]       **/
		public final static String csufName="csuf_name";
		/** 所属单位 [非空] [CsUnitInfo]      **/
		public final static String csufUnitInfo="csuf_unit_info";
		/** 车型 [非空] [CsCarModel]      **/
		public final static String csufModel="csuf_model";
		/** 天租价格 [非空]    设置为0表示不启用该价格   **/
		public final static String csufDayPrice="csuf_day_price";
		/** 小时价格 [非空]    设置为0表示不启用该价格   **/
		public final static String csufHourPrice="csuf_hour_price";
		/** 里程费 [非空]    设置为0表示不启用该价格   **/
		public final static String csufMileage="csuf_mileage";
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
				$.Set(name,CsUnitFee.getCsUnitFee(params));
			else
				$.Set(name,CsUnitFee.getCsUnitFeeList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取价格配置数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsUnitFee) $.GetRequest("CsUnitFee$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsUnitFee.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsUnitFee.getCsUnitFee(params);
			else
				value = CsUnitFee.getCsUnitFeeList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsUnitFee.Get($.add(CsUnitFee.F.csufId,param));
		else if(!$.empty(param.toString()))
			value = CsUnitFee.get(Long.valueOf(param.toString()));
		$.SetRequest("CsUnitFee$"+param.hashCode(), value);
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
	public void mergeSet(CsUnitFee old){
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