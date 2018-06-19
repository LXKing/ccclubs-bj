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

@namespace("fee/item")
public @caption("商品信息") @table("cs_item") class CsItem implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csi_id")   @primary  @note("  ") Long csiId;// 主键 非空     
	private @caption("商品名称") @column("csi_title")    @note("  ") String csiTitle;// 非空     
	private @caption("商品类型") @column("csi_type")    @note(" 0:实物 1:现金券 2:免费小时 3:红包 4:套餐  ") Short csiType;// 非空 0:实物 1:现金券 2:免费小时 3:红包 4:套餐     
	private @caption("商品分类") @column("csi_sort")    @relate("$csiSort") @RelateClass(SrvProperty.class)  @note(" 宝贝分类:'ITEM_TYPE'  ") Long csiSort;// 非空 宝贝分类:'ITEM_TYPE'    select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) 
 	private SrvProperty $csiSort;//关联对象[系统属性]
	private @caption("商品价格") @column("csi_price")    @note("  ") Double csiPrice;//(单位：元) 非空     
	private @caption("折扣") @column("csi_rebate")    @note("  0到1之间") Double csiRebate;// 非空  0到1之间   
	private @caption("标识") @column("csi_flag")    @note("  ") String csiFlag;//     
	private @caption("附加字串") @column("csi_data_string")    @note("  ") String csiDataString;//     
	private @caption("附加数字") @column("csi_data_number")    @note("  ") Long csiDataNumber;//     
	private @caption("附加小数") @column("csi_data_decimal")    @note("  ") Double csiDataDecimal;//     
	private @caption("缩略图") @column("csi_thumb")    @note("  ") String csiThumb;//     
	private @caption("图片列表") @column("csi_images")    @note("  ") String csiImages;//     
	private @caption("描述") @column("csi_depict")    @note("  ") String csiDepict;//     
	private @caption("已售数") @column("csi_sale")    @note("  ") Integer csiSale;// 非空     
	private @caption("优先级") @column("csi_level")    @note("  大于0的整数，越大优先级越高") Integer csiLevel;//  大于0的整数，越大优先级越高   
	private @caption("备注") @column("csi_remark")    @note("  ") String csiRemark;//     
	private @caption("修改时间") @column("csi_update_time")    @note("  ") Date csiUpdateTime;// 非空     
	private @caption("添加时间") @column("csi_add_time")    @note("  ") Date csiAddTime;// 非空     
	private @caption("状态") @column("csi_status")    @note(" 1:正常 2:下架 0:无效  ") Short csiStatus;// 非空 1:正常 2:下架 0:无效     
	
	//默认构造函数
	public CsItem(){
	
	}
	
	//主键构造函数
	public CsItem(Long id){
		this.csiId = id;
	}
	
	/**所有字段构造函数 CsItem(csiTitle,csiType,csiSort,csiPrice,csiRebate,csiFlag,csiDataString,csiDataNumber,csiDataDecimal,csiThumb,csiImages,csiDepict,csiSale,csiLevel,csiRemark,csiUpdateTime,csiAddTime,csiStatus)
	 CsItem(
	 	$.getString("csiTitle")//商品名称 [非空]
	 	,$.getShort("csiType")//商品类型 [非空]
	 	,$.getLong("csiSort")//商品分类 [非空]
	 	,$.getDouble("csiPrice")//商品价格 [非空]
	 	,$.getDouble("csiRebate")//折扣 [非空]
	 	,$.getString("csiFlag")//标识
	 	,$.getString("csiDataString")//附加字串
	 	,$.getLong("csiDataNumber")//附加数字
	 	,$.getDouble("csiDataDecimal")//附加小数
	 	,$.getString("csiThumb")//缩略图
	 	,$.getString("csiImages")//图片列表
	 	,$.getString("csiDepict")//描述
	 	,$.getInteger("csiSale")//已售数 [非空]
	 	,$.getInteger("csiLevel")//优先级
	 	,$.getString("csiRemark")//备注
	 	,$.getDate("csiUpdateTime")//修改时间 [非空]
	 	,$.getDate("csiAddTime")//添加时间 [非空]
	 	,$.getShort("csiStatus")//状态 [非空]
	 )
	**/
	public CsItem(String csiTitle,Short csiType,Long csiSort,Double csiPrice,Double csiRebate,String csiFlag,String csiDataString,Long csiDataNumber,Double csiDataDecimal,String csiThumb,String csiImages,String csiDepict,Integer csiSale,Integer csiLevel,String csiRemark,Date csiUpdateTime,Date csiAddTime,Short csiStatus){
		this.csiTitle=csiTitle;
		this.csiType=csiType;
		this.csiSort=csiSort;
		this.csiPrice=csiPrice;
		this.csiRebate=csiRebate;
		this.csiFlag=csiFlag;
		this.csiDataString=csiDataString;
		this.csiDataNumber=csiDataNumber;
		this.csiDataDecimal=csiDataDecimal;
		this.csiThumb=csiThumb;
		this.csiImages=csiImages;
		this.csiDepict=csiDepict;
		this.csiSale=csiSale;
		this.csiLevel=csiLevel;
		this.csiRemark=csiRemark;
		this.csiUpdateTime=csiUpdateTime;
		this.csiAddTime=csiAddTime;
		this.csiStatus=csiStatus;
	}
	
	//设置非空字段
	public CsItem setNotNull(String csiTitle,Short csiType,Long csiSort,Double csiPrice,Double csiRebate,Integer csiSale,Date csiUpdateTime,Date csiAddTime,Short csiStatus){
		this.csiTitle=csiTitle;
		this.csiType=csiType;
		this.csiSort=csiSort;
		this.csiPrice=csiPrice;
		this.csiRebate=csiRebate;
		this.csiSale=csiSale;
		this.csiUpdateTime=csiUpdateTime;
		this.csiAddTime=csiAddTime;
		this.csiStatus=csiStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsItem csiId(Long csiId){
		this.csiId = csiId;
		this.setSeted(F.csiId);
		return this;
	}
	/** 商品名称 [非空]       **/
	public CsItem csiTitle(String csiTitle){
		this.csiTitle = csiTitle;
		this.setSeted(F.csiTitle);
		return this;
	}
	/** 商品类型 [非空]   0:实物 1:现金券 2:免费小时 3:红包 4:套餐     **/
	public CsItem csiType(Short csiType){
		this.csiType = csiType;
		this.setSeted(F.csiType);
		return this;
	}
	/** 商品分类 [非空] [SrvProperty]  宝贝分类:'ITEM_TYPE'    select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
	public CsItem csiSort(Long csiSort){
		this.csiSort = csiSort;
		this.setSeted(F.csiSort);
		return this;
	}
	/** 商品价格 [非空]       **/
	public CsItem csiPrice(Double csiPrice){
		this.csiPrice = csiPrice;
		this.setSeted(F.csiPrice);
		return this;
	}
	/** 折扣 [非空]    0到1之间   **/
	public CsItem csiRebate(Double csiRebate){
		this.csiRebate = csiRebate;
		this.setSeted(F.csiRebate);
		return this;
	}
	/** 标识        **/
	public CsItem csiFlag(String csiFlag){
		this.csiFlag = csiFlag;
		this.setSeted(F.csiFlag);
		return this;
	}
	/** 附加字串        **/
	public CsItem csiDataString(String csiDataString){
		this.csiDataString = csiDataString;
		this.setSeted(F.csiDataString);
		return this;
	}
	/** 附加数字        **/
	public CsItem csiDataNumber(Long csiDataNumber){
		this.csiDataNumber = csiDataNumber;
		this.setSeted(F.csiDataNumber);
		return this;
	}
	/** 附加小数        **/
	public CsItem csiDataDecimal(Double csiDataDecimal){
		this.csiDataDecimal = csiDataDecimal;
		this.setSeted(F.csiDataDecimal);
		return this;
	}
	/** 缩略图        **/
	public CsItem csiThumb(String csiThumb){
		this.csiThumb = csiThumb;
		this.setSeted(F.csiThumb);
		return this;
	}
	/** 图片列表        **/
	public CsItem csiImages(String csiImages){
		this.csiImages = csiImages;
		this.setSeted(F.csiImages);
		return this;
	}
	/** 描述        **/
	public CsItem csiDepict(String csiDepict){
		this.csiDepict = csiDepict;
		this.setSeted(F.csiDepict);
		return this;
	}
	/** 已售数 [非空]       **/
	public CsItem csiSale(Integer csiSale){
		this.csiSale = csiSale;
		this.setSeted(F.csiSale);
		return this;
	}
	/** 优先级     大于0的整数，越大优先级越高   **/
	public CsItem csiLevel(Integer csiLevel){
		this.csiLevel = csiLevel;
		this.setSeted(F.csiLevel);
		return this;
	}
	/** 备注        **/
	public CsItem csiRemark(String csiRemark){
		this.csiRemark = csiRemark;
		this.setSeted(F.csiRemark);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsItem csiUpdateTime(Date csiUpdateTime){
		this.csiUpdateTime = csiUpdateTime;
		this.setSeted(F.csiUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsItem csiAddTime(Date csiAddTime){
		this.csiAddTime = csiAddTime;
		this.setSeted(F.csiAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 2:下架 0:无效     **/
	public CsItem csiStatus(Short csiStatus){
		this.csiStatus = csiStatus;
		this.setSeted(F.csiStatus);
		return this;
	}
	
	
	//克隆对象
	public CsItem clone(){
		CsItem clone = new CsItem();
		clone.csiTitle=this.csiTitle;
		clone.csiType=this.csiType;
		clone.csiSort=this.csiSort;
		clone.csiPrice=this.csiPrice;
		clone.csiRebate=this.csiRebate;
		clone.csiSale=this.csiSale;
		clone.csiUpdateTime=this.csiUpdateTime;
		clone.csiAddTime=this.csiAddTime;
		clone.csiStatus=this.csiStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取商品信息
	 * @param id
	 * @return
	 */
	public static @api CsItem get(Long id){		
		return getCsItemById(id);
	}
	/**
	 * 获取所有商品信息
	 * @return
	 */
	public static @api List<CsItem> list(Map params,Integer size){
		return getCsItemList(params,size);
	}
	/**
	 * 获取商品信息分页
	 * @return
	 */
	public static @api Page<CsItem> page(int page,int size,Map params){
		return getCsItemPage(page, size , params);
	}
	/**
	 * 根据查询条件取商品信息
	 * @param params
	 * @return
	 */
	public static @api CsItem Get(Map params){
		return getCsItem(params);
	}
	/**
	 * 获取商品信息数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsItemCount(params);
	}
	/**
	 * 获取商品信息数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsItemEval(eval,params);
	}
	
	/**
	 * 根据ID取商品信息
	 * @param id
	 * @return
	 */
	public static @api CsItem getCsItemById(Long id){		
		CsItem csItem = (CsItem) $.GetRequest("CsItem$"+id);
		if(csItem!=null)
			return csItem;
		com.ccclubs.service.admin.ICsItemService csItemService = $.GetSpringBean("csItemService");		
		return csItemService.getCsItemById(id);
	}
	
	
	/**
	 * 根据ID取商品信息的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsItem.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsItem csItem = get(id);
		if(csItem!=null){
			String strValue = csItem.getCsiTitle$();
			if(!"CsiTitle".equals("CsiTitle"))
				strValue+="("+csItem.getCsiTitle$()+")";
			MemCache.setValue(CsItem.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsiTitle$();
		if(!"CsiTitle".equals("CsiTitle"))
			keyValue+="("+this.getCsiTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有商品信息
	 * @return
	 */
	public static @api List<CsItem> getCsItemList(Map params,Integer size){
		com.ccclubs.service.admin.ICsItemService csItemService = $.GetSpringBean("csItemService");
		return csItemService.getCsItemList(params, size);
	}
	
	/**
	 * 获取商品信息分页
	 * @return
	 */
	public static @api Page<CsItem> getCsItemPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsItemService csItemService = $.GetSpringBean("csItemService");
		return csItemService.getCsItemPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取商品信息
	 * @param params
	 * @return
	 */
	public static @api CsItem getCsItem(Map params){
		com.ccclubs.service.admin.ICsItemService csItemService = $.GetSpringBean("csItemService");
		return csItemService.getCsItem(params);
	}
	
	/**
	 * 获取商品信息数
	 * @return
	 */
	public static @api Long getCsItemCount(Map params){
		com.ccclubs.service.admin.ICsItemService csItemService = $.GetSpringBean("csItemService");
		return csItemService.getCsItemCount(params);
	}
		
		
	/**
	 * 获取商品信息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsItemEval(String eval,Map params){
		com.ccclubs.service.admin.ICsItemService csItemService = $.GetSpringBean("csItemService");
		return csItemService.getCsItemEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsItem(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsItemService csItemService = $.GetSpringBean("csItemService");
		csItemService.updateCsItemByConfirm(set, where);
	}
	
	
	/**
	 * 保存商品信息对象
	 * @param params
	 * @return
	 */
	public CsItem save(){
		com.ccclubs.service.admin.ICsItemService csItemService = $.GetSpringBean("csItemService");
		if(this.getCsiId()!=null)
			csItemService.updateCsItem(this);
		else
			return csItemService.saveCsItem(this);
		return this;
	}
	
	
	/**
	 * 更新商品信息对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsItemService csItemService = $.GetSpringBean("csItemService");
		csItemService.updateCsItem$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsItemService csItemService = $.GetSpringBean("csItemService");
		if($.equals($.config("logic_delete"),"true"))
			csItemService.removeCsItemById(this.getCsiId());
		else
			csItemService.deleteCsItemById(this.getCsiId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsItemService csItemService = $.GetSpringBean("csItemService");
		return csItemService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsiId(){
		return this.csiId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsiId$(){
		String strValue="";
		 strValue=$.str(this.getCsiId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsiId(Long csiId){
		this.csiId = csiId;
		this.setSeted(F.csiId);
	}
	/*******************************商品名称**********************************/	
	/**
	* 商品名称 [非空]      
	**/
	public String getCsiTitle(){
		return this.csiTitle;
	}
	/**
	* 获取商品名称格式化(toString)
	**/
	public String getCsiTitle$(){
		String strValue="";
		 strValue=$.str(this.getCsiTitle());
	 	 return strValue;
	}
	/**
	* 商品名称 [非空]      
	**/
	public void setCsiTitle(String csiTitle){
		this.csiTitle = csiTitle;
		this.setSeted(F.csiTitle);
	}
	/*******************************商品类型**********************************/	
	/**
	* 商品类型 [非空]   0:实物 1:现金券 2:免费小时 3:红包 4:套餐    
	**/
	public Short getCsiType(){
		return this.csiType;
	}
	/**
	* 获取商品类型格式化(toString)
	**/
	public String getCsiType$(){
		String strValue="";
		 if($.equals($.str(this.getCsiType()),"0"))
			strValue=$.str("实物");		 
		 if($.equals($.str(this.getCsiType()),"1"))
			strValue=$.str("现金券");		 
		 if($.equals($.str(this.getCsiType()),"2"))
			strValue=$.str("免费小时");		 
		 if($.equals($.str(this.getCsiType()),"3"))
			strValue=$.str("红包");		 
		 if($.equals($.str(this.getCsiType()),"4"))
			strValue=$.str("套餐");		 
	 	 return strValue;
	}
	/**
	* 商品类型 [非空]   0:实物 1:现金券 2:免费小时 3:红包 4:套餐    
	**/
	public void setCsiType(Short csiType){
		this.csiType = csiType;
		this.setSeted(F.csiType);
	}
	/*******************************商品分类**********************************/	
	/**
	* 商品分类 [非空] [SrvProperty]  宝贝分类:'ITEM_TYPE'    select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)
	**/
	public Long getCsiSort(){
		return this.csiSort;
	}
	/**
	* 获取商品分类格式化(toString)
	**/
	public String getCsiSort$(){
		String strValue="";
		if(this.getCsiSort()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCsiSort()));
		}			
	 	 return strValue;
	}
	/**
	* 商品分类 [非空] [SrvProperty]  宝贝分类:'ITEM_TYPE'    select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)
	**/
	public void setCsiSort(Long csiSort){
		this.csiSort = csiSort;
		this.setSeted(F.csiSort);
	}
	/**
	* 获取关联对象[系统属性]
	**/	 			
 	public SrvProperty get$csiSort(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCsiSort());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCsiSort()!=null){
 			srvProperty = SrvProperty.get(this.getCsiSort());
 		}
 		$.SetRequest("SrvProperty$"+this.getCsiSort(), srvProperty);
	 	return srvProperty;
	}
	/*******************************商品价格**********************************/	
	/**
	* 商品价格 [非空]      
	**/
	public Double getCsiPrice(){
		return this.csiPrice;
	}
	/**
	* 获取商品价格格式化(toString)
	**/
	public String getCsiPrice$(){
		String strValue="";
		 strValue=$.str(this.getCsiPrice());	
	 	 return strValue;
	}
	/**
	* 商品价格 [非空]      
	**/
	public void setCsiPrice(Double csiPrice){
		this.csiPrice = csiPrice;
		this.setSeted(F.csiPrice);
	}
	/*******************************折扣**********************************/	
	/**
	* 折扣 [非空]    0到1之间  
	**/
	public Double getCsiRebate(){
		return this.csiRebate;
	}
	/**
	* 获取折扣格式化(toString)
	**/
	public String getCsiRebate$(){
		String strValue="";
		 strValue=$.str(this.getCsiRebate());	
	 	 return strValue;
	}
	/**
	* 折扣 [非空]    0到1之间  
	**/
	public void setCsiRebate(Double csiRebate){
		this.csiRebate = csiRebate;
		this.setSeted(F.csiRebate);
	}
	/*******************************标识**********************************/	
	/**
	* 标识       
	**/
	public String getCsiFlag(){
		return this.csiFlag;
	}
	/**
	* 获取标识格式化(toString)
	**/
	public String getCsiFlag$(){
		String strValue="";
		 strValue=$.str(this.getCsiFlag());
	 	 return strValue;
	}
	/**
	* 标识       
	**/
	public void setCsiFlag(String csiFlag){
		this.csiFlag = csiFlag;
		this.setSeted(F.csiFlag);
	}
	/*******************************附加字串**********************************/	
	/**
	* 附加字串       
	**/
	public String getCsiDataString(){
		return this.csiDataString;
	}
	/**
	* 获取附加字串格式化(toString)
	**/
	public String getCsiDataString$(){
		String strValue="";
		 strValue=$.str(this.getCsiDataString());
	 	 return strValue;
	}
	/**
	* 附加字串       
	**/
	public void setCsiDataString(String csiDataString){
		this.csiDataString = csiDataString;
		this.setSeted(F.csiDataString);
	}
	/*******************************附加数字**********************************/	
	/**
	* 附加数字       
	**/
	public Long getCsiDataNumber(){
		return this.csiDataNumber;
	}
	/**
	* 获取附加数字格式化(toString)
	**/
	public String getCsiDataNumber$(){
		String strValue="";
		 strValue=$.str(this.getCsiDataNumber());
	 	 return strValue;
	}
	/**
	* 附加数字       
	**/
	public void setCsiDataNumber(Long csiDataNumber){
		this.csiDataNumber = csiDataNumber;
		this.setSeted(F.csiDataNumber);
	}
	/*******************************附加小数**********************************/	
	/**
	* 附加小数       
	**/
	public Double getCsiDataDecimal(){
		return this.csiDataDecimal;
	}
	/**
	* 获取附加小数格式化(toString)
	**/
	public String getCsiDataDecimal$(){
		String strValue="";
		 strValue=$.str(this.getCsiDataDecimal());	
	 	 return strValue;
	}
	/**
	* 附加小数       
	**/
	public void setCsiDataDecimal(Double csiDataDecimal){
		this.csiDataDecimal = csiDataDecimal;
		this.setSeted(F.csiDataDecimal);
	}
	/*******************************缩略图**********************************/	
	/**
	* 缩略图       
	**/
	public String getCsiThumb(){
		return this.csiThumb;
	}
	/**
	* 获取缩略图格式化(toString)
	**/
	public String getCsiThumb$(){
		String strValue="";
		 strValue=$.str(this.getCsiThumb());
	 	 return strValue;
	}
	/**
	* 缩略图       
	**/
	public void setCsiThumb(String csiThumb){
		this.csiThumb = csiThumb;
		this.setSeted(F.csiThumb);
	}
	/*******************************图片列表**********************************/	
	/**
	* 图片列表       
	**/
	public String getCsiImages(){
		return this.csiImages;
	}
	/**
	* 获取图片列表格式化(toString)
	**/
	public String getCsiImages$(){
		String strValue="";
		 strValue=$.str(this.getCsiImages());
	 	 return strValue;
	}
	/**
	* 图片列表       
	**/
	public void setCsiImages(String csiImages){
		this.csiImages = csiImages;
		this.setSeted(F.csiImages);
	}
	/*******************************描述**********************************/	
	/**
	* 描述       
	**/
	public String getCsiDepict(){
		return this.csiDepict;
	}
	/**
	* 获取描述格式化(toString)
	**/
	public String getCsiDepict$(){
		String strValue="";
		 strValue=$.str(this.getCsiDepict());
	 	 return strValue;
	}
	/**
	* 描述       
	**/
	public void setCsiDepict(String csiDepict){
		this.csiDepict = csiDepict;
		this.setSeted(F.csiDepict);
	}
	/*******************************已售数**********************************/	
	/**
	* 已售数 [非空]      
	**/
	public Integer getCsiSale(){
		return this.csiSale;
	}
	/**
	* 获取已售数格式化(toString)
	**/
	public String getCsiSale$(){
		String strValue="";
		 strValue=$.str(this.getCsiSale());
	 	 return strValue;
	}
	/**
	* 已售数 [非空]      
	**/
	public void setCsiSale(Integer csiSale){
		this.csiSale = csiSale;
		this.setSeted(F.csiSale);
	}
	/*******************************优先级**********************************/	
	/**
	* 优先级     大于0的整数，越大优先级越高  
	**/
	public Integer getCsiLevel(){
		return this.csiLevel;
	}
	/**
	* 获取优先级格式化(toString)
	**/
	public String getCsiLevel$(){
		String strValue="";
		 strValue=$.str(this.getCsiLevel());
	 	 return strValue;
	}
	/**
	* 优先级     大于0的整数，越大优先级越高  
	**/
	public void setCsiLevel(Integer csiLevel){
		this.csiLevel = csiLevel;
		this.setSeted(F.csiLevel);
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getCsiRemark(){
		return this.csiRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCsiRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsiRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setCsiRemark(String csiRemark){
		this.csiRemark = csiRemark;
		this.setSeted(F.csiRemark);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsiUpdateTime(){
		return this.csiUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsiUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsiUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsiUpdateTime(Date csiUpdateTime){
		this.csiUpdateTime = csiUpdateTime;
		this.setSeted(F.csiUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsiAddTime(){
		return this.csiAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsiAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsiAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsiAddTime(Date csiAddTime){
		this.csiAddTime = csiAddTime;
		this.setSeted(F.csiAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 2:下架 0:无效    
	**/
	public Short getCsiStatus(){
		return this.csiStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsiStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsiStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsiStatus()),"2"))
			strValue=$.str("下架");		 
		 if($.equals($.str(this.getCsiStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 2:下架 0:无效    
	**/
	public void setCsiStatus(Short csiStatus){
		this.csiStatus = csiStatus;
		this.setSeted(F.csiStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsItem.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsItem.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsItem.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsItem.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsItem.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsItem.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsItem.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsItem.this);
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
		public M csiId(Object csiId){this.put("csiId", csiId);return this;};
	 	/** and csi_id is null */
 		public M csiIdNull(){if(this.get("csiIdNot")==null)this.put("csiIdNot", "");this.put("csiId", null);return this;};
 		/** not .... */
 		public M csiIdNot(){this.put("csiIdNot", "not");return this;};
		/** 商品名称 [非空]       **/
		public M csiTitle(Object csiTitle){this.put("csiTitle", csiTitle);return this;};
	 	/** and csi_title is null */
 		public M csiTitleNull(){if(this.get("csiTitleNot")==null)this.put("csiTitleNot", "");this.put("csiTitle", null);return this;};
 		/** not .... */
 		public M csiTitleNot(){this.put("csiTitleNot", "not");return this;};
		/** 商品类型 [非空]   0:实物 1:现金券 2:免费小时 3:红包 4:套餐     **/
		public M csiType(Object csiType){this.put("csiType", csiType);return this;};
	 	/** and csi_type is null */
 		public M csiTypeNull(){if(this.get("csiTypeNot")==null)this.put("csiTypeNot", "");this.put("csiType", null);return this;};
 		/** not .... */
 		public M csiTypeNot(){this.put("csiTypeNot", "not");return this;};
		/** 商品分类 [非空] [SrvProperty]  宝贝分类:'ITEM_TYPE'    select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
		public M csiSort(Object csiSort){this.put("csiSort", csiSort);return this;};
	 	/** and csi_sort is null */
 		public M csiSortNull(){if(this.get("csiSortNot")==null)this.put("csiSortNot", "");this.put("csiSort", null);return this;};
 		/** not .... */
 		public M csiSortNot(){this.put("csiSortNot", "not");return this;};
		public M csiSort$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("csiSort$on", value);
			return this;
		};	
		/** 商品价格 [非空]       **/
		public M csiPrice(Object csiPrice){this.put("csiPrice", csiPrice);return this;};
	 	/** and csi_price is null */
 		public M csiPriceNull(){if(this.get("csiPriceNot")==null)this.put("csiPriceNot", "");this.put("csiPrice", null);return this;};
 		/** not .... */
 		public M csiPriceNot(){this.put("csiPriceNot", "not");return this;};
		/** and csi_price >= ? */
		public M csiPriceMin(Object min){this.put("csiPriceMin", min);return this;};
		/** and csi_price <= ? */
		public M csiPriceMax(Object max){this.put("csiPriceMax", max);return this;};
		/** 折扣 [非空]    0到1之间   **/
		public M csiRebate(Object csiRebate){this.put("csiRebate", csiRebate);return this;};
	 	/** and csi_rebate is null */
 		public M csiRebateNull(){if(this.get("csiRebateNot")==null)this.put("csiRebateNot", "");this.put("csiRebate", null);return this;};
 		/** not .... */
 		public M csiRebateNot(){this.put("csiRebateNot", "not");return this;};
		/** and csi_rebate >= ? */
		public M csiRebateMin(Object min){this.put("csiRebateMin", min);return this;};
		/** and csi_rebate <= ? */
		public M csiRebateMax(Object max){this.put("csiRebateMax", max);return this;};
		/** 标识        **/
		public M csiFlag(Object csiFlag){this.put("csiFlag", csiFlag);return this;};
	 	/** and csi_flag is null */
 		public M csiFlagNull(){if(this.get("csiFlagNot")==null)this.put("csiFlagNot", "");this.put("csiFlag", null);return this;};
 		/** not .... */
 		public M csiFlagNot(){this.put("csiFlagNot", "not");return this;};
		/** 附加字串        **/
		public M csiDataString(Object csiDataString){this.put("csiDataString", csiDataString);return this;};
	 	/** and csi_data_string is null */
 		public M csiDataStringNull(){if(this.get("csiDataStringNot")==null)this.put("csiDataStringNot", "");this.put("csiDataString", null);return this;};
 		/** not .... */
 		public M csiDataStringNot(){this.put("csiDataStringNot", "not");return this;};
		/** 附加数字        **/
		public M csiDataNumber(Object csiDataNumber){this.put("csiDataNumber", csiDataNumber);return this;};
	 	/** and csi_data_number is null */
 		public M csiDataNumberNull(){if(this.get("csiDataNumberNot")==null)this.put("csiDataNumberNot", "");this.put("csiDataNumber", null);return this;};
 		/** not .... */
 		public M csiDataNumberNot(){this.put("csiDataNumberNot", "not");return this;};
		/** and csi_data_number >= ? */
		public M csiDataNumberMin(Object min){this.put("csiDataNumberMin", min);return this;};
		/** and csi_data_number <= ? */
		public M csiDataNumberMax(Object max){this.put("csiDataNumberMax", max);return this;};
		/** 附加小数        **/
		public M csiDataDecimal(Object csiDataDecimal){this.put("csiDataDecimal", csiDataDecimal);return this;};
	 	/** and csi_data_decimal is null */
 		public M csiDataDecimalNull(){if(this.get("csiDataDecimalNot")==null)this.put("csiDataDecimalNot", "");this.put("csiDataDecimal", null);return this;};
 		/** not .... */
 		public M csiDataDecimalNot(){this.put("csiDataDecimalNot", "not");return this;};
		/** and csi_data_decimal >= ? */
		public M csiDataDecimalMin(Object min){this.put("csiDataDecimalMin", min);return this;};
		/** and csi_data_decimal <= ? */
		public M csiDataDecimalMax(Object max){this.put("csiDataDecimalMax", max);return this;};
		/** 缩略图        **/
		public M csiThumb(Object csiThumb){this.put("csiThumb", csiThumb);return this;};
	 	/** and csi_thumb is null */
 		public M csiThumbNull(){if(this.get("csiThumbNot")==null)this.put("csiThumbNot", "");this.put("csiThumb", null);return this;};
 		/** not .... */
 		public M csiThumbNot(){this.put("csiThumbNot", "not");return this;};
		/** 图片列表        **/
		public M csiImages(Object csiImages){this.put("csiImages", csiImages);return this;};
	 	/** and csi_images is null */
 		public M csiImagesNull(){if(this.get("csiImagesNot")==null)this.put("csiImagesNot", "");this.put("csiImages", null);return this;};
 		/** not .... */
 		public M csiImagesNot(){this.put("csiImagesNot", "not");return this;};
		/** 描述        **/
		public M csiDepict(Object csiDepict){this.put("csiDepict", csiDepict);return this;};
	 	/** and csi_depict is null */
 		public M csiDepictNull(){if(this.get("csiDepictNot")==null)this.put("csiDepictNot", "");this.put("csiDepict", null);return this;};
 		/** not .... */
 		public M csiDepictNot(){this.put("csiDepictNot", "not");return this;};
		/** 已售数 [非空]       **/
		public M csiSale(Object csiSale){this.put("csiSale", csiSale);return this;};
	 	/** and csi_sale is null */
 		public M csiSaleNull(){if(this.get("csiSaleNot")==null)this.put("csiSaleNot", "");this.put("csiSale", null);return this;};
 		/** not .... */
 		public M csiSaleNot(){this.put("csiSaleNot", "not");return this;};
		/** and csi_sale >= ? */
		public M csiSaleMin(Object min){this.put("csiSaleMin", min);return this;};
		/** and csi_sale <= ? */
		public M csiSaleMax(Object max){this.put("csiSaleMax", max);return this;};
		/** 优先级     大于0的整数，越大优先级越高   **/
		public M csiLevel(Object csiLevel){this.put("csiLevel", csiLevel);return this;};
	 	/** and csi_level is null */
 		public M csiLevelNull(){if(this.get("csiLevelNot")==null)this.put("csiLevelNot", "");this.put("csiLevel", null);return this;};
 		/** not .... */
 		public M csiLevelNot(){this.put("csiLevelNot", "not");return this;};
		/** and csi_level >= ? */
		public M csiLevelMin(Object min){this.put("csiLevelMin", min);return this;};
		/** and csi_level <= ? */
		public M csiLevelMax(Object max){this.put("csiLevelMax", max);return this;};
		/** 备注        **/
		public M csiRemark(Object csiRemark){this.put("csiRemark", csiRemark);return this;};
	 	/** and csi_remark is null */
 		public M csiRemarkNull(){if(this.get("csiRemarkNot")==null)this.put("csiRemarkNot", "");this.put("csiRemark", null);return this;};
 		/** not .... */
 		public M csiRemarkNot(){this.put("csiRemarkNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csiUpdateTime(Object csiUpdateTime){this.put("csiUpdateTime", csiUpdateTime);return this;};
	 	/** and csi_update_time is null */
 		public M csiUpdateTimeNull(){if(this.get("csiUpdateTimeNot")==null)this.put("csiUpdateTimeNot", "");this.put("csiUpdateTime", null);return this;};
 		/** not .... */
 		public M csiUpdateTimeNot(){this.put("csiUpdateTimeNot", "not");return this;};
 		/** and csi_update_time >= ? */
 		public M csiUpdateTimeStart(Object start){this.put("csiUpdateTimeStart", start);return this;};			
 		/** and csi_update_time <= ? */
 		public M csiUpdateTimeEnd(Object end){this.put("csiUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csiAddTime(Object csiAddTime){this.put("csiAddTime", csiAddTime);return this;};
	 	/** and csi_add_time is null */
 		public M csiAddTimeNull(){if(this.get("csiAddTimeNot")==null)this.put("csiAddTimeNot", "");this.put("csiAddTime", null);return this;};
 		/** not .... */
 		public M csiAddTimeNot(){this.put("csiAddTimeNot", "not");return this;};
 		/** and csi_add_time >= ? */
 		public M csiAddTimeStart(Object start){this.put("csiAddTimeStart", start);return this;};			
 		/** and csi_add_time <= ? */
 		public M csiAddTimeEnd(Object end){this.put("csiAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 2:下架 0:无效     **/
		public M csiStatus(Object csiStatus){this.put("csiStatus", csiStatus);return this;};
	 	/** and csi_status is null */
 		public M csiStatusNull(){if(this.get("csiStatusNot")==null)this.put("csiStatusNot", "");this.put("csiStatus", null);return this;};
 		/** not .... */
 		public M csiStatusNot(){this.put("csiStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有商品信息 **/
		public @api List<CsItem> list(Integer size){
			return getCsItemList(this,size);
		}
		/** 获取商品信息分页 **/
		public @api Page<CsItem> page(int page,int size){
			return getCsItemPage(page, size , this);
		}
		/** 根据查询条件取商品信息 **/
		public @api CsItem get(){
			return getCsItem(this);
		}
		/** 获取商品信息数 **/
		public @api Long count(){
			return getCsItemCount(this);
		}
		/** 获取商品信息表达式 **/
		public @api <T> T eval(String strEval){
			return getCsItemEval(strEval,this);
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
			updateCsItem(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csiId="csiId";
		/** 商品名称 [非空]       **/
		public final static @type(String.class) @like String csiTitle="csiTitle";
		/** 商品类型 [非空]   0:实物 1:现金券 2:免费小时 3:红包 4:套餐     **/
		public final static @type(Short.class)  String csiType="csiType";
		/** 商品分类 [非空] [SrvProperty]  宝贝分类:'ITEM_TYPE'    select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
		public final static @type(Long.class)  String csiSort="csiSort";
		/** 商品价格 [非空]       **/
		public final static @type(Double.class)  String csiPrice="csiPrice";
		/** and csi_price >= ? */
		public final static @type(Double.class) String csiPriceMin="csiPriceMin";
		/** and csi_price <= ? */
		public final static @type(Double.class) String csiPriceMax="csiPriceMax";
		/** 折扣 [非空]    0到1之间   **/
		public final static @type(Double.class)  String csiRebate="csiRebate";
		/** and csi_rebate >= ? */
		public final static @type(Double.class) String csiRebateMin="csiRebateMin";
		/** and csi_rebate <= ? */
		public final static @type(Double.class) String csiRebateMax="csiRebateMax";
		/** 标识        **/
		public final static @type(String.class) @like String csiFlag="csiFlag";
		/** 附加字串        **/
		public final static @type(String.class) @like String csiDataString="csiDataString";
		/** 附加数字        **/
		public final static @type(Long.class)  String csiDataNumber="csiDataNumber";
		/** and csi_data_number >= ? */
		public final static @type(Long.class) String csiDataNumberMin="csiDataNumberMin";
		/** and csi_data_number <= ? */
		public final static @type(Long.class) String csiDataNumberMax="csiDataNumberMax";
		/** 附加小数        **/
		public final static @type(Double.class)  String csiDataDecimal="csiDataDecimal";
		/** and csi_data_decimal >= ? */
		public final static @type(Double.class) String csiDataDecimalMin="csiDataDecimalMin";
		/** and csi_data_decimal <= ? */
		public final static @type(Double.class) String csiDataDecimalMax="csiDataDecimalMax";
		/** 缩略图        **/
		public final static @type(String.class)  String csiThumb="csiThumb";
		/** 图片列表        **/
		public final static @type(String.class)  String csiImages="csiImages";
		/** 描述        **/
		public final static @type(String.class) @like String csiDepict="csiDepict";
		/** 已售数 [非空]       **/
		public final static @type(Integer.class)  String csiSale="csiSale";
		/** and csi_sale >= ? */
		public final static @type(Integer.class) String csiSaleMin="csiSaleMin";
		/** and csi_sale <= ? */
		public final static @type(Integer.class) String csiSaleMax="csiSaleMax";
		/** 优先级     大于0的整数，越大优先级越高   **/
		public final static @type(Integer.class)  String csiLevel="csiLevel";
		/** and csi_level >= ? */
		public final static @type(Integer.class) String csiLevelMin="csiLevelMin";
		/** and csi_level <= ? */
		public final static @type(Integer.class) String csiLevelMax="csiLevelMax";
		/** 备注        **/
		public final static @type(String.class) @like String csiRemark="csiRemark";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csiUpdateTime="csiUpdateTime";
	 	/** and csi_update_time >= ? */
 		public final static @type(Date.class) String csiUpdateTimeStart="csiUpdateTimeStart";
 		/** and csi_update_time <= ? */
 		public final static @type(Date.class) String csiUpdateTimeEnd="csiUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csiAddTime="csiAddTime";
	 	/** and csi_add_time >= ? */
 		public final static @type(Date.class) String csiAddTimeStart="csiAddTimeStart";
 		/** and csi_add_time <= ? */
 		public final static @type(Date.class) String csiAddTimeEnd="csiAddTimeEnd";
		/** 状态 [非空]   1:正常 2:下架 0:无效     **/
		public final static @type(Short.class)  String csiStatus="csiStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csiId="csi_id";
		/** 商品名称 [非空]       **/
		public final static String csiTitle="csi_title";
		/** 商品类型 [非空]   0:实物 1:现金券 2:免费小时 3:红包 4:套餐     **/
		public final static String csiType="csi_type";
		/** 商品分类 [非空] [SrvProperty]  宝贝分类:'ITEM_TYPE'    select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
		public final static String csiSort="csi_sort";
		/** 商品价格 [非空]       **/
		public final static String csiPrice="csi_price";
		/** 折扣 [非空]    0到1之间   **/
		public final static String csiRebate="csi_rebate";
		/** 标识        **/
		public final static String csiFlag="csi_flag";
		/** 附加字串        **/
		public final static String csiDataString="csi_data_string";
		/** 附加数字        **/
		public final static String csiDataNumber="csi_data_number";
		/** 附加小数        **/
		public final static String csiDataDecimal="csi_data_decimal";
		/** 缩略图        **/
		public final static String csiThumb="csi_thumb";
		/** 图片列表        **/
		public final static String csiImages="csi_images";
		/** 描述        **/
		public final static String csiDepict="csi_depict";
		/** 已售数 [非空]       **/
		public final static String csiSale="csi_sale";
		/** 优先级     大于0的整数，越大优先级越高   **/
		public final static String csiLevel="csi_level";
		/** 备注        **/
		public final static String csiRemark="csi_remark";
		/** 修改时间 [非空]       **/
		public final static String csiUpdateTime="csi_update_time";
		/** 添加时间 [非空]       **/
		public final static String csiAddTime="csi_add_time";
		/** 状态 [非空]   1:正常 2:下架 0:无效     **/
		public final static String csiStatus="csi_status";
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
				$.Set(name,CsItem.getCsItem(params));
			else
				$.Set(name,CsItem.getCsItemList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取商品信息数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsItem) $.GetRequest("CsItem$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsItem.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsItem.getCsItem(params);
			else
				value = CsItem.getCsItemList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsItem.Get($.add(CsItem.F.csiId,param));
		else if(!$.empty(param.toString()))
			value = CsItem.get(Long.valueOf(param.toString()));
		$.SetRequest("CsItem$"+param.hashCode(), value);
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
	public void mergeSet(CsItem old){
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