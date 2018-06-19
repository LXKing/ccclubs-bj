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

@namespace("fee/packinfo")
public @caption("套餐内容") @table("cs_pack_info") class CsPackInfo implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cspi_id")   @primary  @note("  ") Long cspiId;// 主键 非空     
	private @caption("名称") @column("cspi_name")    @note("  ") String cspiName;//     
	private @caption("所属套餐") @column("cspi_pack")    @relate("$cspiPack") @RelateClass(CsPack.class)  @note("  ") Long cspiPack;// 非空     
 	private CsPack $cspiPack;//关联对象[系统套餐]
	private @caption("所属商品") @column("cspi_goods")    @relate("$cspiGoods") @RelateClass(CsGoods.class)  @note("  ") Long cspiGoods;// 非空     
 	private CsGoods $cspiGoods;//关联对象[商品]
	private @caption("所属网点") @column("cspi_outlets")    @relate("$cspiOutlets") @RelateClass(CsOutlets.class)  @note("  适用到的网点，不选表示适用于所有网点") Long cspiOutlets;//  适用到的网点，不选表示适用于所有网点   
 	private CsOutlets $cspiOutlets;//关联对象[网点]
	private @caption("所属车型") @column("cspi_model")    @relate("$cspiModel") @RelateClass(CsCarModel.class)  @note("  适用到的车型，不选表示适用于所有车型") String cspiModel;//  适用到的车型，不选表示适用于所有车型   
 	private CsCarModel $cspiModel;//关联对象[车型]
	private @caption("类型") @column("cspi_type")    @note(" 0:数量 1:价格 2:折扣  当类型为折扣时，值为0到1之间") Short cspiType;// 非空 0:数量 1:价格 2:折扣  当类型为折扣时，值为0到1之间   
	private @caption("值") @column("cspi_value")    @note("  数量或价格或折扣，由类型决定") Double cspiValue;// 非空  数量或价格或折扣，由类型决定   
	private @caption("函数脚本") @column("cspi_script")    @note("  优惠项脚本，二次干预优惠项，返回false表示不能使用") String cspiScript;//  优惠项脚本，二次干预优惠项，返回false表示不能使用   
	private @caption("添加时间") @column("cspi_add_time")    @note("  ") Date cspiAddTime;// 非空     
	
	//默认构造函数
	public CsPackInfo(){
	
	}
	
	//主键构造函数
	public CsPackInfo(Long id){
		this.cspiId = id;
	}
	
	/**所有字段构造函数 CsPackInfo(cspiName,cspiPack,cspiGoods,cspiOutlets,cspiModel,cspiType,cspiValue,cspiScript,cspiAddTime)
	 CsPackInfo(
	 	$.getString("cspiName")//名称
	 	,$.getLong("cspiPack")//所属套餐 [非空]
	 	,$.getLong("cspiGoods")//所属商品 [非空]
	 	,$.getLong("cspiOutlets")//所属网点
	 	,$.getString("cspiModel")//所属车型
	 	,$.getShort("cspiType")//类型 [非空]
	 	,$.getDouble("cspiValue")//值 [非空]
	 	,$.getString("cspiScript")//函数脚本
	 	,$.getDate("cspiAddTime")//添加时间 [非空]
	 )
	**/
	public CsPackInfo(String cspiName,Long cspiPack,Long cspiGoods,Long cspiOutlets,String cspiModel,Short cspiType,Double cspiValue,String cspiScript,Date cspiAddTime){
		this.cspiName=cspiName;
		this.cspiPack=cspiPack;
		this.cspiGoods=cspiGoods;
		this.cspiOutlets=cspiOutlets;
		this.cspiModel=cspiModel;
		this.cspiType=cspiType;
		this.cspiValue=cspiValue;
		this.cspiScript=cspiScript;
		this.cspiAddTime=cspiAddTime;
	}
	
	//设置非空字段
	public CsPackInfo setNotNull(Long cspiPack,Long cspiGoods,Short cspiType,Double cspiValue,Date cspiAddTime){
		this.cspiPack=cspiPack;
		this.cspiGoods=cspiGoods;
		this.cspiType=cspiType;
		this.cspiValue=cspiValue;
		this.cspiAddTime=cspiAddTime;
		return this;
	}
	/** 编号 [非空]       **/
	public CsPackInfo cspiId(Long cspiId){
		this.cspiId = cspiId;
		this.setSeted(F.cspiId);
		return this;
	}
	/** 名称        **/
	public CsPackInfo cspiName(String cspiName){
		this.cspiName = cspiName;
		this.setSeted(F.cspiName);
		return this;
	}
	/** 所属套餐 [非空] [CsPack]      **/
	public CsPackInfo cspiPack(Long cspiPack){
		this.cspiPack = cspiPack;
		this.setSeted(F.cspiPack);
		return this;
	}
	/** 所属商品 [非空] [CsGoods]      **/
	public CsPackInfo cspiGoods(Long cspiGoods){
		this.cspiGoods = cspiGoods;
		this.setSeted(F.cspiGoods);
		return this;
	}
	/** 所属网点  [CsOutlets]   适用到的网点，不选表示适用于所有网点   **/
	public CsPackInfo cspiOutlets(Long cspiOutlets){
		this.cspiOutlets = cspiOutlets;
		this.setSeted(F.cspiOutlets);
		return this;
	}
	/** 所属车型  [CsCarModel]   适用到的车型，不选表示适用于所有车型   **/
	public CsPackInfo cspiModel(String cspiModel){
		this.cspiModel = cspiModel;
		this.setSeted(F.cspiModel);
		return this;
	}
	/** 类型 [非空]   0:数量 1:价格 2:折扣  当类型为折扣时，值为0到1之间   **/
	public CsPackInfo cspiType(Short cspiType){
		this.cspiType = cspiType;
		this.setSeted(F.cspiType);
		return this;
	}
	/** 值 [非空]    数量或价格或折扣，由类型决定   **/
	public CsPackInfo cspiValue(Double cspiValue){
		this.cspiValue = cspiValue;
		this.setSeted(F.cspiValue);
		return this;
	}
	/** 函数脚本     优惠项脚本，二次干预优惠项，返回false表示不能使用   **/
	public CsPackInfo cspiScript(String cspiScript){
		this.cspiScript = cspiScript;
		this.setSeted(F.cspiScript);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsPackInfo cspiAddTime(Date cspiAddTime){
		this.cspiAddTime = cspiAddTime;
		this.setSeted(F.cspiAddTime);
		return this;
	}
	
	
	//克隆对象
	public CsPackInfo clone(){
		CsPackInfo clone = new CsPackInfo();
		clone.cspiPack=this.cspiPack;
		clone.cspiGoods=this.cspiGoods;
		clone.cspiType=this.cspiType;
		clone.cspiValue=this.cspiValue;
		clone.cspiAddTime=this.cspiAddTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取套餐内容
	 * @param id
	 * @return
	 */
	public static @api CsPackInfo get(Long id){		
		return getCsPackInfoById(id);
	}
	/**
	 * 获取所有套餐内容
	 * @return
	 */
	public static @api List<CsPackInfo> list(Map params,Integer size){
		return getCsPackInfoList(params,size);
	}
	/**
	 * 获取套餐内容分页
	 * @return
	 */
	public static @api Page<CsPackInfo> page(int page,int size,Map params){
		return getCsPackInfoPage(page, size , params);
	}
	/**
	 * 根据查询条件取套餐内容
	 * @param params
	 * @return
	 */
	public static @api CsPackInfo Get(Map params){
		return getCsPackInfo(params);
	}
	/**
	 * 获取套餐内容数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsPackInfoCount(params);
	}
	/**
	 * 获取套餐内容数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsPackInfoEval(eval,params);
	}
	
	/**
	 * 根据ID取套餐内容
	 * @param id
	 * @return
	 */
	public static @api CsPackInfo getCsPackInfoById(Long id){		
		CsPackInfo csPackInfo = (CsPackInfo) $.GetRequest("CsPackInfo$"+id);
		if(csPackInfo!=null)
			return csPackInfo;
		com.ccclubs.service.admin.ICsPackInfoService csPackInfoService = $.GetSpringBean("csPackInfoService");		
		return csPackInfoService.getCsPackInfoById(id);
	}
	
	
	/**
	 * 根据ID取套餐内容的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsPackInfo.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsPackInfo csPackInfo = get(id);
		if(csPackInfo!=null){
			String strValue = csPackInfo.getCspiName$();
			if(!"CspiName".equals("CspiName"))
				strValue+="("+csPackInfo.getCspiName$()+")";
			MemCache.setValue(CsPackInfo.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCspiName$();
		if(!"CspiName".equals("CspiName"))
			keyValue+="("+this.getCspiName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有套餐内容
	 * @return
	 */
	public static @api List<CsPackInfo> getCsPackInfoList(Map params,Integer size){
		com.ccclubs.service.admin.ICsPackInfoService csPackInfoService = $.GetSpringBean("csPackInfoService");
		return csPackInfoService.getCsPackInfoList(params, size);
	}
	
	/**
	 * 获取套餐内容分页
	 * @return
	 */
	public static @api Page<CsPackInfo> getCsPackInfoPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsPackInfoService csPackInfoService = $.GetSpringBean("csPackInfoService");
		return csPackInfoService.getCsPackInfoPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取套餐内容
	 * @param params
	 * @return
	 */
	public static @api CsPackInfo getCsPackInfo(Map params){
		com.ccclubs.service.admin.ICsPackInfoService csPackInfoService = $.GetSpringBean("csPackInfoService");
		return csPackInfoService.getCsPackInfo(params);
	}
	
	/**
	 * 获取套餐内容数
	 * @return
	 */
	public static @api Long getCsPackInfoCount(Map params){
		com.ccclubs.service.admin.ICsPackInfoService csPackInfoService = $.GetSpringBean("csPackInfoService");
		return csPackInfoService.getCsPackInfoCount(params);
	}
		
		
	/**
	 * 获取套餐内容自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsPackInfoEval(String eval,Map params){
		com.ccclubs.service.admin.ICsPackInfoService csPackInfoService = $.GetSpringBean("csPackInfoService");
		return csPackInfoService.getCsPackInfoEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsPackInfo(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsPackInfoService csPackInfoService = $.GetSpringBean("csPackInfoService");
		csPackInfoService.updateCsPackInfoByConfirm(set, where);
	}
	
	
	/**
	 * 保存套餐内容对象
	 * @param params
	 * @return
	 */
	public CsPackInfo save(){
		com.ccclubs.service.admin.ICsPackInfoService csPackInfoService = $.GetSpringBean("csPackInfoService");
		if(this.getCspiId()!=null)
			csPackInfoService.updateCsPackInfo(this);
		else
			return csPackInfoService.saveCsPackInfo(this);
		return this;
	}
	
	
	/**
	 * 更新套餐内容对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsPackInfoService csPackInfoService = $.GetSpringBean("csPackInfoService");
		csPackInfoService.updateCsPackInfo$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsPackInfoService csPackInfoService = $.GetSpringBean("csPackInfoService");
			csPackInfoService.deleteCsPackInfoById(this.getCspiId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsPackInfoService csPackInfoService = $.GetSpringBean("csPackInfoService");
		return csPackInfoService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCspiId(){
		return this.cspiId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCspiId$(){
		String strValue="";
		 strValue=$.str(this.getCspiId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCspiId(Long cspiId){
		this.cspiId = cspiId;
		this.setSeted(F.cspiId);
	}
	/*******************************名称**********************************/	
	/**
	* 名称       
	**/
	public String getCspiName(){
		return this.cspiName;
	}
	/**
	* 获取名称格式化(toString)
	**/
	public String getCspiName$(){
		String strValue="";
		 strValue=$.str(this.getCspiName());
	 	 return strValue;
	}
	/**
	* 名称       
	**/
	public void setCspiName(String cspiName){
		this.cspiName = cspiName;
		this.setSeted(F.cspiName);
	}
	/*******************************所属套餐**********************************/	
	/**
	* 所属套餐 [非空] [CsPack]     
	**/
	public Long getCspiPack(){
		return this.cspiPack;
	}
	/**
	* 获取所属套餐格式化(toString)
	**/
	public String getCspiPack$(){
		String strValue="";
		if(this.getCspiPack()!=null){
				strValue+=$.str(CsPack.getKeyValue(this.getCspiPack()));
		}			
	 	 return strValue;
	}
	/**
	* 所属套餐 [非空] [CsPack]     
	**/
	public void setCspiPack(Long cspiPack){
		this.cspiPack = cspiPack;
		this.setSeted(F.cspiPack);
	}
	/**
	* 获取关联对象[系统套餐]
	**/	 			
 	public CsPack get$cspiPack(){
 		com.ccclubs.model.CsPack csPack = (com.ccclubs.model.CsPack) $.GetRequest("CsPack$"+this.getCspiPack());
 		if(csPack!=null)
			return csPack;
		if(this.getCspiPack()!=null){
 			csPack = CsPack.get(this.getCspiPack());
 		}
 		$.SetRequest("CsPack$"+this.getCspiPack(), csPack);
	 	return csPack;
	}
	/*******************************所属商品**********************************/	
	/**
	* 所属商品 [非空] [CsGoods]     
	**/
	public Long getCspiGoods(){
		return this.cspiGoods;
	}
	/**
	* 获取所属商品格式化(toString)
	**/
	public String getCspiGoods$(){
		String strValue="";
		if(this.getCspiGoods()!=null){
				strValue+=$.str(CsGoods.getKeyValue(this.getCspiGoods()));
		}			
	 	 return strValue;
	}
	/**
	* 所属商品 [非空] [CsGoods]     
	**/
	public void setCspiGoods(Long cspiGoods){
		this.cspiGoods = cspiGoods;
		this.setSeted(F.cspiGoods);
	}
	/**
	* 获取关联对象[商品]
	**/	 			
 	public CsGoods get$cspiGoods(){
 		com.ccclubs.model.CsGoods csGoods = (com.ccclubs.model.CsGoods) $.GetRequest("CsGoods$"+this.getCspiGoods());
 		if(csGoods!=null)
			return csGoods;
		if(this.getCspiGoods()!=null){
 			csGoods = CsGoods.get(this.getCspiGoods());
 		}
 		$.SetRequest("CsGoods$"+this.getCspiGoods(), csGoods);
	 	return csGoods;
	}
	/*******************************所属网点**********************************/	
	/**
	* 所属网点  [CsOutlets]   适用到的网点，不选表示适用于所有网点  
	**/
	public Long getCspiOutlets(){
		return this.cspiOutlets;
	}
	/**
	* 获取所属网点格式化(toString)
	**/
	public String getCspiOutlets$(){
		String strValue="";
		if(this.getCspiOutlets()!=null){
				strValue+=$.str(CsOutlets.getKeyValue(this.getCspiOutlets()));
		}			
	 	 return strValue;
	}
	/**
	* 所属网点  [CsOutlets]   适用到的网点，不选表示适用于所有网点  
	**/
	public void setCspiOutlets(Long cspiOutlets){
		this.cspiOutlets = cspiOutlets;
		this.setSeted(F.cspiOutlets);
	}
	/**
	* 获取关联对象[网点]
	**/	 			
 	public CsOutlets get$cspiOutlets(){
 		com.ccclubs.model.CsOutlets csOutlets = (com.ccclubs.model.CsOutlets) $.GetRequest("CsOutlets$"+this.getCspiOutlets());
 		if(csOutlets!=null)
			return csOutlets;
		if(this.getCspiOutlets()!=null){
 			csOutlets = CsOutlets.get(this.getCspiOutlets());
 		}
 		$.SetRequest("CsOutlets$"+this.getCspiOutlets(), csOutlets);
	 	return csOutlets;
	}
	/*******************************所属车型**********************************/	
	/**
	* 所属车型  [CsCarModel]   适用到的车型，不选表示适用于所有车型  
	**/
	public String getCspiModel(){
		return this.cspiModel;
	}
	/**
	* 获取所属车型格式化(toString)
	**/
	public String getCspiModel$(){
		String strValue="";
			
		if(!$.empty(this.getCspiModel())){
			List<com.ccclubs.model.CsCarModel> items = com.ccclubs.model.CsCarModel.getCsCarModelList($.add("definex","cscm_id in ("+$.str(this.getCspiModel()).replace("#","")+")"),-1);
			for(com.ccclubs.model.CsCarModel item1:items)
				strValue+="["+$.str(item1.getCscmName())+"]";
		}
	 	 return strValue;
	}
	/**
	* 所属车型  [CsCarModel]   适用到的车型，不选表示适用于所有车型  
	**/
	public void setCspiModel(String cspiModel){
		this.cspiModel = cspiModel;
		this.setSeted(F.cspiModel);
	}
			
	/**
	* 获取所属车型列表
	**/ 			
 	public List<com.ccclubs.model.CsCarModel> get$cspiModel(){
		if(!$.empty(this.getCspiModel())){
			return com.ccclubs.model.CsCarModel.getCsCarModelList($.add("definex","cscm_id in ("+$.str(this.getCspiModel()).replace("#","")+")"),-1);
		}else
			return new ArrayList();
	}
	/*******************************类型**********************************/	
	/**
	* 类型 [非空]   0:数量 1:价格 2:折扣  当类型为折扣时，值为0到1之间  
	**/
	public Short getCspiType(){
		return this.cspiType;
	}
	/**
	* 获取类型格式化(toString)
	**/
	public String getCspiType$(){
		String strValue="";
		 if($.equals($.str(this.getCspiType()),"0"))
			strValue=$.str("数量");		 
		 if($.equals($.str(this.getCspiType()),"1"))
			strValue=$.str("价格");		 
		 if($.equals($.str(this.getCspiType()),"2"))
			strValue=$.str("折扣");		 
	 	 return strValue;
	}
	/**
	* 类型 [非空]   0:数量 1:价格 2:折扣  当类型为折扣时，值为0到1之间  
	**/
	public void setCspiType(Short cspiType){
		this.cspiType = cspiType;
		this.setSeted(F.cspiType);
	}
	/*******************************值**********************************/	
	/**
	* 值 [非空]    数量或价格或折扣，由类型决定  
	**/
	public Double getCspiValue(){
		return this.cspiValue;
	}
	/**
	* 获取值格式化(toString)
	**/
	public String getCspiValue$(){
		String strValue="";
		 strValue=$.str(this.getCspiValue());	
	 	 return strValue;
	}
	/**
	* 值 [非空]    数量或价格或折扣，由类型决定  
	**/
	public void setCspiValue(Double cspiValue){
		this.cspiValue = cspiValue;
		this.setSeted(F.cspiValue);
	}
	/*******************************函数脚本**********************************/	
	/**
	* 函数脚本     优惠项脚本，二次干预优惠项，返回false表示不能使用  
	**/
	public String getCspiScript(){
		return this.cspiScript;
	}
	/**
	* 获取函数脚本格式化(toString)
	**/
	public String getCspiScript$(){
		String strValue="";
		 strValue=$.str(this.getCspiScript());
	 	 return strValue;
	}
	/**
	* 函数脚本     优惠项脚本，二次干预优惠项，返回false表示不能使用  
	**/
	public void setCspiScript(String cspiScript){
		this.cspiScript = cspiScript;
		this.setSeted(F.cspiScript);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCspiAddTime(){
		return this.cspiAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCspiAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCspiAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCspiAddTime(Date cspiAddTime){
		this.cspiAddTime = cspiAddTime;
		this.setSeted(F.cspiAddTime);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsPackInfo.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsPackInfo.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsPackInfo.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsPackInfo.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsPackInfo.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsPackInfo.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsPackInfo.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsPackInfo.this);
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
		public M cspiId(Object cspiId){this.put("cspiId", cspiId);return this;};
	 	/** and cspi_id is null */
 		public M cspiIdNull(){if(this.get("cspiIdNot")==null)this.put("cspiIdNot", "");this.put("cspiId", null);return this;};
 		/** not .... */
 		public M cspiIdNot(){this.put("cspiIdNot", "not");return this;};
		/** 名称        **/
		public M cspiName(Object cspiName){this.put("cspiName", cspiName);return this;};
	 	/** and cspi_name is null */
 		public M cspiNameNull(){if(this.get("cspiNameNot")==null)this.put("cspiNameNot", "");this.put("cspiName", null);return this;};
 		/** not .... */
 		public M cspiNameNot(){this.put("cspiNameNot", "not");return this;};
		/** 所属套餐 [非空] [CsPack]      **/
		public M cspiPack(Object cspiPack){this.put("cspiPack", cspiPack);return this;};
	 	/** and cspi_pack is null */
 		public M cspiPackNull(){if(this.get("cspiPackNot")==null)this.put("cspiPackNot", "");this.put("cspiPack", null);return this;};
 		/** not .... */
 		public M cspiPackNot(){this.put("cspiPackNot", "not");return this;};
		public M cspiPack$on(CsPack.M value){
			this.put("CsPack", value);
			this.put("cspiPack$on", value);
			return this;
		};	
		/** 所属商品 [非空] [CsGoods]      **/
		public M cspiGoods(Object cspiGoods){this.put("cspiGoods", cspiGoods);return this;};
	 	/** and cspi_goods is null */
 		public M cspiGoodsNull(){if(this.get("cspiGoodsNot")==null)this.put("cspiGoodsNot", "");this.put("cspiGoods", null);return this;};
 		/** not .... */
 		public M cspiGoodsNot(){this.put("cspiGoodsNot", "not");return this;};
		public M cspiGoods$on(CsGoods.M value){
			this.put("CsGoods", value);
			this.put("cspiGoods$on", value);
			return this;
		};	
		/** 所属网点  [CsOutlets]   适用到的网点，不选表示适用于所有网点   **/
		public M cspiOutlets(Object cspiOutlets){this.put("cspiOutlets", cspiOutlets);return this;};
	 	/** and cspi_outlets is null */
 		public M cspiOutletsNull(){if(this.get("cspiOutletsNot")==null)this.put("cspiOutletsNot", "");this.put("cspiOutlets", null);return this;};
 		/** not .... */
 		public M cspiOutletsNot(){this.put("cspiOutletsNot", "not");return this;};
		public M cspiOutlets$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("cspiOutlets$on", value);
			return this;
		};	
		/** 所属车型  [CsCarModel]   适用到的车型，不选表示适用于所有车型   **/
		public M cspiModel(Object cspiModel){this.put("cspiModel", cspiModel);return this;};
	 	/** and cspi_model is null */
 		public M cspiModelNull(){if(this.get("cspiModelNot")==null)this.put("cspiModelNot", "");this.put("cspiModel", null);return this;};
 		/** not .... */
 		public M cspiModelNot(){this.put("cspiModelNot", "not");return this;};
		/** 类型 [非空]   0:数量 1:价格 2:折扣  当类型为折扣时，值为0到1之间   **/
		public M cspiType(Object cspiType){this.put("cspiType", cspiType);return this;};
	 	/** and cspi_type is null */
 		public M cspiTypeNull(){if(this.get("cspiTypeNot")==null)this.put("cspiTypeNot", "");this.put("cspiType", null);return this;};
 		/** not .... */
 		public M cspiTypeNot(){this.put("cspiTypeNot", "not");return this;};
		/** 值 [非空]    数量或价格或折扣，由类型决定   **/
		public M cspiValue(Object cspiValue){this.put("cspiValue", cspiValue);return this;};
	 	/** and cspi_value is null */
 		public M cspiValueNull(){if(this.get("cspiValueNot")==null)this.put("cspiValueNot", "");this.put("cspiValue", null);return this;};
 		/** not .... */
 		public M cspiValueNot(){this.put("cspiValueNot", "not");return this;};
		/** and cspi_value >= ? */
		public M cspiValueMin(Object min){this.put("cspiValueMin", min);return this;};
		/** and cspi_value <= ? */
		public M cspiValueMax(Object max){this.put("cspiValueMax", max);return this;};
		/** 函数脚本     优惠项脚本，二次干预优惠项，返回false表示不能使用   **/
		public M cspiScript(Object cspiScript){this.put("cspiScript", cspiScript);return this;};
	 	/** and cspi_script is null */
 		public M cspiScriptNull(){if(this.get("cspiScriptNot")==null)this.put("cspiScriptNot", "");this.put("cspiScript", null);return this;};
 		/** not .... */
 		public M cspiScriptNot(){this.put("cspiScriptNot", "not");return this;};
		/** 添加时间 [非空]       **/
		public M cspiAddTime(Object cspiAddTime){this.put("cspiAddTime", cspiAddTime);return this;};
	 	/** and cspi_add_time is null */
 		public M cspiAddTimeNull(){if(this.get("cspiAddTimeNot")==null)this.put("cspiAddTimeNot", "");this.put("cspiAddTime", null);return this;};
 		/** not .... */
 		public M cspiAddTimeNot(){this.put("cspiAddTimeNot", "not");return this;};
 		/** and cspi_add_time >= ? */
 		public M cspiAddTimeStart(Object start){this.put("cspiAddTimeStart", start);return this;};			
 		/** and cspi_add_time <= ? */
 		public M cspiAddTimeEnd(Object end){this.put("cspiAddTimeEnd", end);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有套餐内容 **/
		public @api List<CsPackInfo> list(Integer size){
			return getCsPackInfoList(this,size);
		}
		/** 获取套餐内容分页 **/
		public @api Page<CsPackInfo> page(int page,int size){
			return getCsPackInfoPage(page, size , this);
		}
		/** 根据查询条件取套餐内容 **/
		public @api CsPackInfo get(){
			return getCsPackInfo(this);
		}
		/** 获取套餐内容数 **/
		public @api Long count(){
			return getCsPackInfoCount(this);
		}
		/** 获取套餐内容表达式 **/
		public @api <T> T eval(String strEval){
			return getCsPackInfoEval(strEval,this);
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
			updateCsPackInfo(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cspiId="cspiId";
		/** 名称        **/
		public final static @type(String.class) @like String cspiName="cspiName";
		/** 所属套餐 [非空] [CsPack]      **/
		public final static @type(Long.class)  String cspiPack="cspiPack";
		/** 所属商品 [非空] [CsGoods]      **/
		public final static @type(Long.class)  String cspiGoods="cspiGoods";
		/** 所属网点  [CsOutlets]   适用到的网点，不选表示适用于所有网点   **/
		public final static @type(Long.class)  String cspiOutlets="cspiOutlets";
		/** 所属车型  [CsCarModel]   适用到的车型，不选表示适用于所有车型   **/
		public final static @type(String.class) @like String cspiModel="cspiModel";
		/** 类型 [非空]   0:数量 1:价格 2:折扣  当类型为折扣时，值为0到1之间   **/
		public final static @type(Short.class)  String cspiType="cspiType";
		/** 值 [非空]    数量或价格或折扣，由类型决定   **/
		public final static @type(Double.class)  String cspiValue="cspiValue";
		/** and cspi_value >= ? */
		public final static @type(Double.class) String cspiValueMin="cspiValueMin";
		/** and cspi_value <= ? */
		public final static @type(Double.class) String cspiValueMax="cspiValueMax";
		/** 函数脚本     优惠项脚本，二次干预优惠项，返回false表示不能使用   **/
		public final static @type(String.class) @like String cspiScript="cspiScript";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cspiAddTime="cspiAddTime";
	 	/** and cspi_add_time >= ? */
 		public final static @type(Date.class) String cspiAddTimeStart="cspiAddTimeStart";
 		/** and cspi_add_time <= ? */
 		public final static @type(Date.class) String cspiAddTimeEnd="cspiAddTimeEnd";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cspiId="cspi_id";
		/** 名称        **/
		public final static String cspiName="cspi_name";
		/** 所属套餐 [非空] [CsPack]      **/
		public final static String cspiPack="cspi_pack";
		/** 所属商品 [非空] [CsGoods]      **/
		public final static String cspiGoods="cspi_goods";
		/** 所属网点  [CsOutlets]   适用到的网点，不选表示适用于所有网点   **/
		public final static String cspiOutlets="cspi_outlets";
		/** 所属车型  [CsCarModel]   适用到的车型，不选表示适用于所有车型   **/
		public final static String cspiModel="cspi_model";
		/** 类型 [非空]   0:数量 1:价格 2:折扣  当类型为折扣时，值为0到1之间   **/
		public final static String cspiType="cspi_type";
		/** 值 [非空]    数量或价格或折扣，由类型决定   **/
		public final static String cspiValue="cspi_value";
		/** 函数脚本     优惠项脚本，二次干预优惠项，返回false表示不能使用   **/
		public final static String cspiScript="cspi_script";
		/** 添加时间 [非空]       **/
		public final static String cspiAddTime="cspi_add_time";
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
				$.Set(name,CsPackInfo.getCsPackInfo(params));
			else
				$.Set(name,CsPackInfo.getCsPackInfoList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取套餐内容数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsPackInfo) $.GetRequest("CsPackInfo$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsPackInfo.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsPackInfo.getCsPackInfo(params);
			else
				value = CsPackInfo.getCsPackInfoList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsPackInfo.Get($.add(CsPackInfo.F.cspiId,param));
		else if(!$.empty(param.toString()))
			value = CsPackInfo.get(Long.valueOf(param.toString()));
		$.SetRequest("CsPackInfo$"+param.hashCode(), value);
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
	public void mergeSet(CsPackInfo old){
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