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

@namespace("operate/activitys/activity/promotion")
public @caption("促销配置") @table("cs_promotion") class CsPromotion implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csp_id")   @primary  @note("  ") Long cspId;// 主键 非空     
	private @caption("城市") @column("csp_host")    @relate("$cspHost") @RelateClass(SrvHost.class)  @note("  ") Long cspHost;// 非空     
 	private SrvHost $cspHost;//关联对象[运营城市]
	private @caption("促销名称") @column("csp_name")    @note("  ") String cspName;// 非空     
	private @caption("所属活动") @column("csp_activity")    @relate("$cspActivity") @RelateClass(CsActivity.class)  @note("  ") Long cspActivity;// 非空     
 	private CsActivity $cspActivity;//关联对象[营销活动]
	private @caption("促销类型") @column("csp_type")    @note(" 0:充值类 1:购买类 2:消费类  ") Short cspType;// 非空 0:充值类 1:购买类 2:消费类     
	private @caption("充值金额") @column("csp_recharge")    @note("  ") Double cspRecharge;// 非空     
	private @caption("消费额度") @column("csp_consume")    @note("  ") Double cspConsume;// 非空     
	private @caption("销售价格") @column("csp_price")    @note("  ") Double cspPrice;// 非空     
	private @caption("关联套餐") @column("csp_package")    @relate("$cspPackage") @RelateClass(CsPack.class)  @note("  ") Long cspPackage;//     
 	private CsPack $cspPackage;//关联对象[系统套餐]
	private @caption("关联宝贝") @column("csp_items")    @relate("$cspItems") @RelateClass(CsItem.class)  @note("  ") String cspItems;//    select * from cs_item where 1=1 
 	private CsItem $cspItems;//关联对象[商品信息]
	private @caption("关联赠品") @column("csp_presents")    @relate("$cspPresents") @RelateClass(CsPresent.class)  @note("  ") String cspPresents;//    select * from cs_present where 1=1 
 	private CsPresent $cspPresents;//关联对象[礼品赠品]
	private @caption("修改时间") @column("csp_update_time")    @note("  ") Date cspUpdateTime;// 非空     
	private @caption("添加时间") @column("csp_add_time")    @note("  ") Date cspAddTime;// 非空     
	private @caption("状态") @column("csp_status")    @note(" 0:配置中 1:运营中 2:无效  ") Short cspStatus;// 非空 0:配置中 1:运营中 2:无效     
	
	//默认构造函数
	public CsPromotion(){
	
	}
	
	//主键构造函数
	public CsPromotion(Long id){
		this.cspId = id;
	}
	
	/**所有字段构造函数 CsPromotion(cspHost,cspName,cspActivity,cspType,cspRecharge,cspConsume,cspPrice,cspPackage,cspItems,cspPresents,cspUpdateTime,cspAddTime,cspStatus)
	 CsPromotion(
	 	$.getLong("cspHost")//城市 [非空]
	 	,$.getString("cspName")//促销名称 [非空]
	 	,$.getLong("cspActivity")//所属活动 [非空]
	 	,$.getShort("cspType")//促销类型 [非空]
	 	,$.getDouble("cspRecharge")//充值金额 [非空]
	 	,$.getDouble("cspConsume")//消费额度 [非空]
	 	,$.getDouble("cspPrice")//销售价格 [非空]
	 	,$.getLong("cspPackage")//关联套餐
	 	,$.getString("cspItems")//关联宝贝
	 	,$.getString("cspPresents")//关联赠品
	 	,$.getDate("cspUpdateTime")//修改时间 [非空]
	 	,$.getDate("cspAddTime")//添加时间 [非空]
	 	,$.getShort("cspStatus")//状态 [非空]
	 )
	**/
	public CsPromotion(Long cspHost,String cspName,Long cspActivity,Short cspType,Double cspRecharge,Double cspConsume,Double cspPrice,Long cspPackage,String cspItems,String cspPresents,Date cspUpdateTime,Date cspAddTime,Short cspStatus){
		this.cspHost=cspHost;
		this.cspName=cspName;
		this.cspActivity=cspActivity;
		this.cspType=cspType;
		this.cspRecharge=cspRecharge;
		this.cspConsume=cspConsume;
		this.cspPrice=cspPrice;
		this.cspPackage=cspPackage;
		this.cspItems=cspItems;
		this.cspPresents=cspPresents;
		this.cspUpdateTime=cspUpdateTime;
		this.cspAddTime=cspAddTime;
		this.cspStatus=cspStatus;
	}
	
	//设置非空字段
	public CsPromotion setNotNull(Long cspHost,String cspName,Long cspActivity,Short cspType,Double cspRecharge,Double cspConsume,Double cspPrice,Date cspUpdateTime,Date cspAddTime,Short cspStatus){
		this.cspHost=cspHost;
		this.cspName=cspName;
		this.cspActivity=cspActivity;
		this.cspType=cspType;
		this.cspRecharge=cspRecharge;
		this.cspConsume=cspConsume;
		this.cspPrice=cspPrice;
		this.cspUpdateTime=cspUpdateTime;
		this.cspAddTime=cspAddTime;
		this.cspStatus=cspStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsPromotion cspId(Long cspId){
		this.cspId = cspId;
		this.setSeted(F.cspId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsPromotion cspHost(Long cspHost){
		this.cspHost = cspHost;
		this.setSeted(F.cspHost);
		return this;
	}
	/** 促销名称 [非空]       **/
	public CsPromotion cspName(String cspName){
		this.cspName = cspName;
		this.setSeted(F.cspName);
		return this;
	}
	/** 所属活动 [非空] [CsActivity]      **/
	public CsPromotion cspActivity(Long cspActivity){
		this.cspActivity = cspActivity;
		this.setSeted(F.cspActivity);
		return this;
	}
	/** 促销类型 [非空]   0:充值类 1:购买类 2:消费类     **/
	public CsPromotion cspType(Short cspType){
		this.cspType = cspType;
		this.setSeted(F.cspType);
		return this;
	}
	/** 充值金额 [非空]       **/
	public CsPromotion cspRecharge(Double cspRecharge){
		this.cspRecharge = cspRecharge;
		this.setSeted(F.cspRecharge);
		return this;
	}
	/** 消费额度 [非空]       **/
	public CsPromotion cspConsume(Double cspConsume){
		this.cspConsume = cspConsume;
		this.setSeted(F.cspConsume);
		return this;
	}
	/** 销售价格 [非空]       **/
	public CsPromotion cspPrice(Double cspPrice){
		this.cspPrice = cspPrice;
		this.setSeted(F.cspPrice);
		return this;
	}
	/** 关联套餐  [CsPack]      **/
	public CsPromotion cspPackage(Long cspPackage){
		this.cspPackage = cspPackage;
		this.setSeted(F.cspPackage);
		return this;
	}
	/** 关联宝贝  [CsItem]     select * from cs_item where 1=1 **/
	public CsPromotion cspItems(String cspItems){
		this.cspItems = cspItems;
		this.setSeted(F.cspItems);
		return this;
	}
	/** 关联赠品  [CsPresent]     select * from cs_present where 1=1 **/
	public CsPromotion cspPresents(String cspPresents){
		this.cspPresents = cspPresents;
		this.setSeted(F.cspPresents);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsPromotion cspUpdateTime(Date cspUpdateTime){
		this.cspUpdateTime = cspUpdateTime;
		this.setSeted(F.cspUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsPromotion cspAddTime(Date cspAddTime){
		this.cspAddTime = cspAddTime;
		this.setSeted(F.cspAddTime);
		return this;
	}
	/** 状态 [非空]   0:配置中 1:运营中 2:无效     **/
	public CsPromotion cspStatus(Short cspStatus){
		this.cspStatus = cspStatus;
		this.setSeted(F.cspStatus);
		return this;
	}
	
	
	//克隆对象
	public CsPromotion clone(){
		CsPromotion clone = new CsPromotion();
		clone.cspHost=this.cspHost;
		clone.cspName=this.cspName;
		clone.cspActivity=this.cspActivity;
		clone.cspType=this.cspType;
		clone.cspRecharge=this.cspRecharge;
		clone.cspConsume=this.cspConsume;
		clone.cspPrice=this.cspPrice;
		clone.cspUpdateTime=this.cspUpdateTime;
		clone.cspAddTime=this.cspAddTime;
		clone.cspStatus=this.cspStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取促销配置
	 * @param id
	 * @return
	 */
	public static @api CsPromotion get(Long id){		
		return getCsPromotionById(id);
	}
	/**
	 * 获取所有促销配置
	 * @return
	 */
	public static @api List<CsPromotion> list(Map params,Integer size){
		return getCsPromotionList(params,size);
	}
	/**
	 * 获取促销配置分页
	 * @return
	 */
	public static @api Page<CsPromotion> page(int page,int size,Map params){
		return getCsPromotionPage(page, size , params);
	}
	/**
	 * 根据查询条件取促销配置
	 * @param params
	 * @return
	 */
	public static @api CsPromotion Get(Map params){
		return getCsPromotion(params);
	}
	/**
	 * 获取促销配置数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsPromotionCount(params);
	}
	/**
	 * 获取促销配置数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsPromotionEval(eval,params);
	}
	
	/**
	 * 根据ID取促销配置
	 * @param id
	 * @return
	 */
	public static @api CsPromotion getCsPromotionById(Long id){		
		CsPromotion csPromotion = (CsPromotion) $.GetRequest("CsPromotion$"+id);
		if(csPromotion!=null)
			return csPromotion;
		com.ccclubs.service.admin.ICsPromotionService csPromotionService = $.GetSpringBean("csPromotionService");		
		return csPromotionService.getCsPromotionById(id);
	}
	
	
	/**
	 * 根据ID取促销配置的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsPromotion.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsPromotion csPromotion = get(id);
		if(csPromotion!=null){
			String strValue = csPromotion.getCspName$();
			if(!"CspName".equals("CspName"))
				strValue+="("+csPromotion.getCspName$()+")";
			MemCache.setValue(CsPromotion.class, id ,strValue);
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
	 * 获取所有促销配置
	 * @return
	 */
	public static @api List<CsPromotion> getCsPromotionList(Map params,Integer size){
		com.ccclubs.service.admin.ICsPromotionService csPromotionService = $.GetSpringBean("csPromotionService");
		return csPromotionService.getCsPromotionList(params, size);
	}
	
	/**
	 * 获取促销配置分页
	 * @return
	 */
	public static @api Page<CsPromotion> getCsPromotionPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsPromotionService csPromotionService = $.GetSpringBean("csPromotionService");
		return csPromotionService.getCsPromotionPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取促销配置
	 * @param params
	 * @return
	 */
	public static @api CsPromotion getCsPromotion(Map params){
		com.ccclubs.service.admin.ICsPromotionService csPromotionService = $.GetSpringBean("csPromotionService");
		return csPromotionService.getCsPromotion(params);
	}
	
	/**
	 * 获取促销配置数
	 * @return
	 */
	public static @api Long getCsPromotionCount(Map params){
		com.ccclubs.service.admin.ICsPromotionService csPromotionService = $.GetSpringBean("csPromotionService");
		return csPromotionService.getCsPromotionCount(params);
	}
		
		
	/**
	 * 获取促销配置自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsPromotionEval(String eval,Map params){
		com.ccclubs.service.admin.ICsPromotionService csPromotionService = $.GetSpringBean("csPromotionService");
		return csPromotionService.getCsPromotionEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsPromotion(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsPromotionService csPromotionService = $.GetSpringBean("csPromotionService");
		csPromotionService.updateCsPromotionByConfirm(set, where);
	}
	
	
	/**
	 * 保存促销配置对象
	 * @param params
	 * @return
	 */
	public CsPromotion save(){
		com.ccclubs.service.admin.ICsPromotionService csPromotionService = $.GetSpringBean("csPromotionService");
		if(this.getCspId()!=null)
			csPromotionService.updateCsPromotion(this);
		else
			return csPromotionService.saveCsPromotion(this);
		return this;
	}
	
	
	/**
	 * 更新促销配置对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsPromotionService csPromotionService = $.GetSpringBean("csPromotionService");
		csPromotionService.updateCsPromotion$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsPromotionService csPromotionService = $.GetSpringBean("csPromotionService");
		if($.equals($.config("logic_delete"),"true"))
			csPromotionService.removeCsPromotionById(this.getCspId());
		else
			csPromotionService.deleteCsPromotionById(this.getCspId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsPromotionService csPromotionService = $.GetSpringBean("csPromotionService");
		return csPromotionService.executeTransaction(function);
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
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCspHost(){
		return this.cspHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCspHost$(){
		String strValue="";
		if(this.getCspHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCspHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCspHost(Long cspHost){
		this.cspHost = cspHost;
		this.setSeted(F.cspHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cspHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCspHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCspHost()!=null){
 			srvHost = SrvHost.get(this.getCspHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCspHost(), srvHost);
	 	return srvHost;
	}
	/*******************************促销名称**********************************/	
	/**
	* 促销名称 [非空]      
	**/
	public String getCspName(){
		return this.cspName;
	}
	/**
	* 获取促销名称格式化(toString)
	**/
	public String getCspName$(){
		String strValue="";
		 strValue=$.str(this.getCspName());
	 	 return strValue;
	}
	/**
	* 促销名称 [非空]      
	**/
	public void setCspName(String cspName){
		this.cspName = cspName;
		this.setSeted(F.cspName);
	}
	/*******************************所属活动**********************************/	
	/**
	* 所属活动 [非空] [CsActivity]     
	**/
	public Long getCspActivity(){
		return this.cspActivity;
	}
	/**
	* 获取所属活动格式化(toString)
	**/
	public String getCspActivity$(){
		String strValue="";
		if(this.getCspActivity()!=null){
				strValue+=$.str(CsActivity.getKeyValue(this.getCspActivity()));
		}			
	 	 return strValue;
	}
	/**
	* 所属活动 [非空] [CsActivity]     
	**/
	public void setCspActivity(Long cspActivity){
		this.cspActivity = cspActivity;
		this.setSeted(F.cspActivity);
	}
	/**
	* 获取关联对象[营销活动]
	**/	 			
 	public CsActivity get$cspActivity(){
 		com.ccclubs.model.CsActivity csActivity = (com.ccclubs.model.CsActivity) $.GetRequest("CsActivity$"+this.getCspActivity());
 		if(csActivity!=null)
			return csActivity;
		if(this.getCspActivity()!=null){
 			csActivity = CsActivity.get(this.getCspActivity());
 		}
 		$.SetRequest("CsActivity$"+this.getCspActivity(), csActivity);
	 	return csActivity;
	}
	/*******************************促销类型**********************************/	
	/**
	* 促销类型 [非空]   0:充值类 1:购买类 2:消费类    
	**/
	public Short getCspType(){
		return this.cspType;
	}
	/**
	* 获取促销类型格式化(toString)
	**/
	public String getCspType$(){
		String strValue="";
		 if($.equals($.str(this.getCspType()),"0"))
			strValue=$.str("充值类");		 
		 if($.equals($.str(this.getCspType()),"1"))
			strValue=$.str("购买类");		 
		 if($.equals($.str(this.getCspType()),"2"))
			strValue=$.str("消费类");		 
	 	 return strValue;
	}
	/**
	* 促销类型 [非空]   0:充值类 1:购买类 2:消费类    
	**/
	public void setCspType(Short cspType){
		this.cspType = cspType;
		this.setSeted(F.cspType);
	}
	/*******************************充值金额**********************************/	
	/**
	* 充值金额 [非空]      
	**/
	public Double getCspRecharge(){
		return this.cspRecharge;
	}
	/**
	* 获取充值金额格式化(toString)
	**/
	public String getCspRecharge$(){
		String strValue="";
		 strValue=$.str(this.getCspRecharge());	
	 	 return strValue;
	}
	/**
	* 充值金额 [非空]      
	**/
	public void setCspRecharge(Double cspRecharge){
		this.cspRecharge = cspRecharge;
		this.setSeted(F.cspRecharge);
	}
	/*******************************消费额度**********************************/	
	/**
	* 消费额度 [非空]      
	**/
	public Double getCspConsume(){
		return this.cspConsume;
	}
	/**
	* 获取消费额度格式化(toString)
	**/
	public String getCspConsume$(){
		String strValue="";
		 strValue=$.str(this.getCspConsume());	
	 	 return strValue;
	}
	/**
	* 消费额度 [非空]      
	**/
	public void setCspConsume(Double cspConsume){
		this.cspConsume = cspConsume;
		this.setSeted(F.cspConsume);
	}
	/*******************************销售价格**********************************/	
	/**
	* 销售价格 [非空]      
	**/
	public Double getCspPrice(){
		return this.cspPrice;
	}
	/**
	* 获取销售价格格式化(toString)
	**/
	public String getCspPrice$(){
		String strValue="";
		 strValue=$.str(this.getCspPrice());	
	 	 return strValue;
	}
	/**
	* 销售价格 [非空]      
	**/
	public void setCspPrice(Double cspPrice){
		this.cspPrice = cspPrice;
		this.setSeted(F.cspPrice);
	}
	/*******************************关联套餐**********************************/	
	/**
	* 关联套餐  [CsPack]     
	**/
	public Long getCspPackage(){
		return this.cspPackage;
	}
	/**
	* 获取关联套餐格式化(toString)
	**/
	public String getCspPackage$(){
		String strValue="";
		if(this.getCspPackage()!=null){
				strValue+=$.str(CsPack.getKeyValue(this.getCspPackage()));
		}			
	 	 return strValue;
	}
	/**
	* 关联套餐  [CsPack]     
	**/
	public void setCspPackage(Long cspPackage){
		this.cspPackage = cspPackage;
		this.setSeted(F.cspPackage);
	}
	/**
	* 获取关联对象[系统套餐]
	**/	 			
 	public CsPack get$cspPackage(){
 		com.ccclubs.model.CsPack csPack = (com.ccclubs.model.CsPack) $.GetRequest("CsPack$"+this.getCspPackage());
 		if(csPack!=null)
			return csPack;
		if(this.getCspPackage()!=null){
 			csPack = CsPack.get(this.getCspPackage());
 		}
 		$.SetRequest("CsPack$"+this.getCspPackage(), csPack);
	 	return csPack;
	}
	/*******************************关联宝贝**********************************/	
	/**
	* 关联宝贝  [CsItem]     select * from cs_item where 1=1
	**/
	public String getCspItems(){
		return this.cspItems;
	}
	/**
	* 获取关联宝贝格式化(toString)
	**/
	public String getCspItems$(){
		String strValue="";
			
		if(!$.empty(this.getCspItems())){
			List<com.ccclubs.model.CsItem> items = com.ccclubs.model.CsItem.getCsItemList($.add("definex","csi_id in ("+$.str(this.getCspItems()).replace("#","")+")"),-1);
			for(com.ccclubs.model.CsItem item1:items)
				strValue+="["+$.str(item1.getCsiTitle())+"]";
		}
	 	 return strValue;
	}
	/**
	* 关联宝贝  [CsItem]     select * from cs_item where 1=1
	**/
	public void setCspItems(String cspItems){
		this.cspItems = cspItems;
		this.setSeted(F.cspItems);
	}
			
	/**
	* 获取关联宝贝列表
	**/ 			
 	public List<com.ccclubs.model.CsItem> get$cspItems(){
		if(!$.empty(this.getCspItems())){
			return com.ccclubs.model.CsItem.getCsItemList($.add("definex","csi_id in ("+$.str(this.getCspItems()).replace("#","")+")"),-1);
		}else
			return new ArrayList();
	}
	/*******************************关联赠品**********************************/	
	/**
	* 关联赠品  [CsPresent]     select * from cs_present where 1=1
	**/
	public String getCspPresents(){
		return this.cspPresents;
	}
	/**
	* 获取关联赠品格式化(toString)
	**/
	public String getCspPresents$(){
		String strValue="";
			
		if(!$.empty(this.getCspPresents())){
			List<com.ccclubs.model.CsPresent> items = com.ccclubs.model.CsPresent.getCsPresentList($.add("definex","csp_id in ("+$.str(this.getCspPresents()).replace("#","")+")"),-1);
			for(com.ccclubs.model.CsPresent item1:items)
				strValue+="["+$.str(item1.getCspName())+"]";
		}
	 	 return strValue;
	}
	/**
	* 关联赠品  [CsPresent]     select * from cs_present where 1=1
	**/
	public void setCspPresents(String cspPresents){
		this.cspPresents = cspPresents;
		this.setSeted(F.cspPresents);
	}
			
	/**
	* 获取关联赠品列表
	**/ 			
 	public List<com.ccclubs.model.CsPresent> get$cspPresents(){
		if(!$.empty(this.getCspPresents())){
			return com.ccclubs.model.CsPresent.getCsPresentList($.add("definex","csp_id in ("+$.str(this.getCspPresents()).replace("#","")+")"),-1);
		}else
			return new ArrayList();
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
	* 状态 [非空]   0:配置中 1:运营中 2:无效    
	**/
	public Short getCspStatus(){
		return this.cspStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCspStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCspStatus()),"0"))
			strValue=$.str("配置中");		 
		 if($.equals($.str(this.getCspStatus()),"1"))
			strValue=$.str("运营中");		 
		 if($.equals($.str(this.getCspStatus()),"2"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:配置中 1:运营中 2:无效    
	**/
	public void setCspStatus(Short cspStatus){
		this.cspStatus = cspStatus;
		this.setSeted(F.cspStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsPromotion.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsPromotion.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsPromotion.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsPromotion.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsPromotion.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsPromotion.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsPromotion.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsPromotion.this);
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
		/** 城市 [非空] [SrvHost]      **/
		public M cspHost(Object cspHost){this.put("cspHost", cspHost);return this;};
	 	/** and csp_host is null */
 		public M cspHostNull(){if(this.get("cspHostNot")==null)this.put("cspHostNot", "");this.put("cspHost", null);return this;};
 		/** not .... */
 		public M cspHostNot(){this.put("cspHostNot", "not");return this;};
		/** 促销名称 [非空]       **/
		public M cspName(Object cspName){this.put("cspName", cspName);return this;};
	 	/** and csp_name is null */
 		public M cspNameNull(){if(this.get("cspNameNot")==null)this.put("cspNameNot", "");this.put("cspName", null);return this;};
 		/** not .... */
 		public M cspNameNot(){this.put("cspNameNot", "not");return this;};
		/** 所属活动 [非空] [CsActivity]      **/
		public M cspActivity(Object cspActivity){this.put("cspActivity", cspActivity);return this;};
	 	/** and csp_activity is null */
 		public M cspActivityNull(){if(this.get("cspActivityNot")==null)this.put("cspActivityNot", "");this.put("cspActivity", null);return this;};
 		/** not .... */
 		public M cspActivityNot(){this.put("cspActivityNot", "not");return this;};
		public M cspActivity$on(CsActivity.M value){
			this.put("CsActivity", value);
			this.put("cspActivity$on", value);
			return this;
		};	
		/** 促销类型 [非空]   0:充值类 1:购买类 2:消费类     **/
		public M cspType(Object cspType){this.put("cspType", cspType);return this;};
	 	/** and csp_type is null */
 		public M cspTypeNull(){if(this.get("cspTypeNot")==null)this.put("cspTypeNot", "");this.put("cspType", null);return this;};
 		/** not .... */
 		public M cspTypeNot(){this.put("cspTypeNot", "not");return this;};
		/** 充值金额 [非空]       **/
		public M cspRecharge(Object cspRecharge){this.put("cspRecharge", cspRecharge);return this;};
	 	/** and csp_recharge is null */
 		public M cspRechargeNull(){if(this.get("cspRechargeNot")==null)this.put("cspRechargeNot", "");this.put("cspRecharge", null);return this;};
 		/** not .... */
 		public M cspRechargeNot(){this.put("cspRechargeNot", "not");return this;};
		/** and csp_recharge >= ? */
		public M cspRechargeMin(Object min){this.put("cspRechargeMin", min);return this;};
		/** and csp_recharge <= ? */
		public M cspRechargeMax(Object max){this.put("cspRechargeMax", max);return this;};
		/** 消费额度 [非空]       **/
		public M cspConsume(Object cspConsume){this.put("cspConsume", cspConsume);return this;};
	 	/** and csp_consume is null */
 		public M cspConsumeNull(){if(this.get("cspConsumeNot")==null)this.put("cspConsumeNot", "");this.put("cspConsume", null);return this;};
 		/** not .... */
 		public M cspConsumeNot(){this.put("cspConsumeNot", "not");return this;};
		/** and csp_consume >= ? */
		public M cspConsumeMin(Object min){this.put("cspConsumeMin", min);return this;};
		/** and csp_consume <= ? */
		public M cspConsumeMax(Object max){this.put("cspConsumeMax", max);return this;};
		/** 销售价格 [非空]       **/
		public M cspPrice(Object cspPrice){this.put("cspPrice", cspPrice);return this;};
	 	/** and csp_price is null */
 		public M cspPriceNull(){if(this.get("cspPriceNot")==null)this.put("cspPriceNot", "");this.put("cspPrice", null);return this;};
 		/** not .... */
 		public M cspPriceNot(){this.put("cspPriceNot", "not");return this;};
		/** and csp_price >= ? */
		public M cspPriceMin(Object min){this.put("cspPriceMin", min);return this;};
		/** and csp_price <= ? */
		public M cspPriceMax(Object max){this.put("cspPriceMax", max);return this;};
		/** 关联套餐  [CsPack]      **/
		public M cspPackage(Object cspPackage){this.put("cspPackage", cspPackage);return this;};
	 	/** and csp_package is null */
 		public M cspPackageNull(){if(this.get("cspPackageNot")==null)this.put("cspPackageNot", "");this.put("cspPackage", null);return this;};
 		/** not .... */
 		public M cspPackageNot(){this.put("cspPackageNot", "not");return this;};
		public M cspPackage$on(CsPack.M value){
			this.put("CsPack", value);
			this.put("cspPackage$on", value);
			return this;
		};	
		/** 关联宝贝  [CsItem]     select * from cs_item where 1=1 **/
		public M cspItems(Object cspItems){this.put("cspItems", cspItems);return this;};
	 	/** and csp_items is null */
 		public M cspItemsNull(){if(this.get("cspItemsNot")==null)this.put("cspItemsNot", "");this.put("cspItems", null);return this;};
 		/** not .... */
 		public M cspItemsNot(){this.put("cspItemsNot", "not");return this;};
		/** 关联赠品  [CsPresent]     select * from cs_present where 1=1 **/
		public M cspPresents(Object cspPresents){this.put("cspPresents", cspPresents);return this;};
	 	/** and csp_presents is null */
 		public M cspPresentsNull(){if(this.get("cspPresentsNot")==null)this.put("cspPresentsNot", "");this.put("cspPresents", null);return this;};
 		/** not .... */
 		public M cspPresentsNot(){this.put("cspPresentsNot", "not");return this;};
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
		/** 状态 [非空]   0:配置中 1:运营中 2:无效     **/
		public M cspStatus(Object cspStatus){this.put("cspStatus", cspStatus);return this;};
	 	/** and csp_status is null */
 		public M cspStatusNull(){if(this.get("cspStatusNot")==null)this.put("cspStatusNot", "");this.put("cspStatus", null);return this;};
 		/** not .... */
 		public M cspStatusNot(){this.put("cspStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有促销配置 **/
		public @api List<CsPromotion> list(Integer size){
			return getCsPromotionList(this,size);
		}
		/** 获取促销配置分页 **/
		public @api Page<CsPromotion> page(int page,int size){
			return getCsPromotionPage(page, size , this);
		}
		/** 根据查询条件取促销配置 **/
		public @api CsPromotion get(){
			return getCsPromotion(this);
		}
		/** 获取促销配置数 **/
		public @api Long count(){
			return getCsPromotionCount(this);
		}
		/** 获取促销配置表达式 **/
		public @api <T> T eval(String strEval){
			return getCsPromotionEval(strEval,this);
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
			updateCsPromotion(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cspId="cspId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cspHost="cspHost";
		/** 促销名称 [非空]       **/
		public final static @type(String.class) @like String cspName="cspName";
		/** 所属活动 [非空] [CsActivity]      **/
		public final static @type(Long.class)  String cspActivity="cspActivity";
		/** 促销类型 [非空]   0:充值类 1:购买类 2:消费类     **/
		public final static @type(Short.class)  String cspType="cspType";
		/** 充值金额 [非空]       **/
		public final static @type(Double.class)  String cspRecharge="cspRecharge";
		/** and csp_recharge >= ? */
		public final static @type(Double.class) String cspRechargeMin="cspRechargeMin";
		/** and csp_recharge <= ? */
		public final static @type(Double.class) String cspRechargeMax="cspRechargeMax";
		/** 消费额度 [非空]       **/
		public final static @type(Double.class)  String cspConsume="cspConsume";
		/** and csp_consume >= ? */
		public final static @type(Double.class) String cspConsumeMin="cspConsumeMin";
		/** and csp_consume <= ? */
		public final static @type(Double.class) String cspConsumeMax="cspConsumeMax";
		/** 销售价格 [非空]       **/
		public final static @type(Double.class)  String cspPrice="cspPrice";
		/** and csp_price >= ? */
		public final static @type(Double.class) String cspPriceMin="cspPriceMin";
		/** and csp_price <= ? */
		public final static @type(Double.class) String cspPriceMax="cspPriceMax";
		/** 关联套餐  [CsPack]      **/
		public final static @type(Long.class)  String cspPackage="cspPackage";
		/** 关联宝贝  [CsItem]     select * from cs_item where 1=1 **/
		public final static @type(String.class)  String cspItems="cspItems";
		/** 关联赠品  [CsPresent]     select * from cs_present where 1=1 **/
		public final static @type(String.class)  String cspPresents="cspPresents";
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
		/** 状态 [非空]   0:配置中 1:运营中 2:无效     **/
		public final static @type(Short.class)  String cspStatus="cspStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cspId="csp_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cspHost="csp_host";
		/** 促销名称 [非空]       **/
		public final static String cspName="csp_name";
		/** 所属活动 [非空] [CsActivity]      **/
		public final static String cspActivity="csp_activity";
		/** 促销类型 [非空]   0:充值类 1:购买类 2:消费类     **/
		public final static String cspType="csp_type";
		/** 充值金额 [非空]       **/
		public final static String cspRecharge="csp_recharge";
		/** 消费额度 [非空]       **/
		public final static String cspConsume="csp_consume";
		/** 销售价格 [非空]       **/
		public final static String cspPrice="csp_price";
		/** 关联套餐  [CsPack]      **/
		public final static String cspPackage="csp_package";
		/** 关联宝贝  [CsItem]     select * from cs_item where 1=1 **/
		public final static String cspItems="csp_items";
		/** 关联赠品  [CsPresent]     select * from cs_present where 1=1 **/
		public final static String cspPresents="csp_presents";
		/** 修改时间 [非空]       **/
		public final static String cspUpdateTime="csp_update_time";
		/** 添加时间 [非空]       **/
		public final static String cspAddTime="csp_add_time";
		/** 状态 [非空]   0:配置中 1:运营中 2:无效     **/
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
				$.Set(name,CsPromotion.getCsPromotion(params));
			else
				$.Set(name,CsPromotion.getCsPromotionList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取促销配置数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsPromotion) $.GetRequest("CsPromotion$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsPromotion.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsPromotion.getCsPromotion(params);
			else
				value = CsPromotion.getCsPromotionList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsPromotion.Get($.add(CsPromotion.F.cspId,param));
		else if(!$.empty(param.toString()))
			value = CsPromotion.get(Long.valueOf(param.toString()));
		$.SetRequest("CsPromotion$"+param.hashCode(), value);
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
	public void mergeSet(CsPromotion old){
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