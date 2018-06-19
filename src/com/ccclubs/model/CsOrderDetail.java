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

@namespace("service/orderdetail")
public @caption("订单明细") @table("cs_order_detail") class CsOrderDetail implements java.io.Serializable
{
	private static final long serialVersionUID =          1l;
	private @caption("编号") @column("csod_id")   @primary  @note("  ") Long csodId;// 主键 非空     
	private @caption("城市") @column("csod_host")    @relate("$csodHost") @RelateClass(SrvHost.class)  @note("  ") Long csodHost;// 非空     
 	private SrvHost $csodHost;//关联对象[运营城市]
	private @caption("所属定单") @column("csod_order")    @relate("$csodOrder") @RelateClass(CsOrder.class)  @note("  ") Long csodOrder;// 非空     
 	private CsOrder $csodOrder;//关联对象[系统订单]
	private @caption("所属产品") @column("csod_product")    @relate("$csodProduct") @RelateClass(CsProduct.class)  @note("  ") Long csodProduct;// 非空     
 	private CsProduct $csodProduct;//关联对象[产品]
	private @caption("所属商品") @column("csod_goods")    @relate("$csodGoods") @RelateClass(CsGoods.class)  @note("  ") Long csodGoods;// 非空     
 	private CsGoods $csodGoods;//关联对象[商品]
	private @caption("所属价格") @column("csod_pricer")    @relate("$csodPricer") @RelateClass(CsPrice.class)  @note("  ") Long csodPricer;// 非空     
 	private CsPrice $csodPricer;//关联对象[价格]
	private @caption("所属网点") @column("csod_outlets")    @relate("$csodOutlets") @RelateClass(CsOutlets.class)  @note("  ") Long csodOutlets;// 非空    ${basePath}${proname}/object/outlets_tree.do?parent={param} 
 	private CsOutlets $csodOutlets;//关联对象[网点]
	private @caption("所属车型") @column("csod_model")    @relate("$csodModel") @RelateClass(CsCarModel.class)  @note("  ") Long csodModel;// 非空     
 	private CsCarModel $csodModel;//关联对象[车型]
	private @caption("所属车辆") @column("csod_car")    @relate("$csodCar") @RelateClass(CsCar.class)  @note("  ") Long csodCar;// 非空     
 	private CsCar $csodCar;//关联对象[车辆]
	private @caption("所属商家") @column("csod_provid")    @relate("$csodProvid") @RelateClass(CsProvid.class)  @note("  ") Long csodProvid;// 非空     
 	private CsProvid $csodProvid;//关联对象[供应商]
	private @caption("开始时间") @column("csod_start")    @note("  ") Date csodStart;//     
	private @caption("结束时间") @column("csod_end")    @note("  ") Date csodEnd;//     
	private @caption("单位") @column("csod_rule")    @relate("$csodRule") @RelateClass(CsRule.class)  @note("  ") Long csodRule;// 非空     
 	private CsRule $csodRule;//关联对象[规则]
	private @caption("理论数量") @column("csod_count")    @note("  ") Double csodCount;// 非空     
	private @caption("实际数量") @column("csod_count_real")    @note("  ") Double csodCountReal;// 非空     
	private @caption("理论单价") @column("csod_price")    @note("  ") Double csodPrice;// 非空     
	private @caption("实际单价") @column("csod_price_real")    @note("  ") Double csodPriceReal;// 非空     
	private @caption("理论金额") @column("csod_total")    @note("  ") Double csodTotal;// 非空     
	private @caption("折扣") @column("csod_rebate")    @note("  0到1,比如0.75表示七五折") Double csodRebate;// 非空  0到1,比如0.75表示七五折   
	private @caption("实际金额") @column("csod_total_real")    @note("  实付金额=实际单价*实际数量") Double csodTotalReal;// 非空  实付金额=实际单价*实际数量   
	private @caption("备注") @column("csod_remark")    @note("  ") String csodRemark;//     
	private @caption("添加时间") @column("csod_add_time")    @note("  ") Date csodAddTime;// 非空     
	
	//默认构造函数
	public CsOrderDetail(){
	
	}
	
	//主键构造函数
	public CsOrderDetail(Long id){
		this.csodId = id;
	}
	
	/**所有字段构造函数 CsOrderDetail(csodHost,csodOrder,csodProduct,csodGoods,csodPricer,csodOutlets,csodModel,csodCar,csodProvid,csodStart,csodEnd,csodRule,csodCount,csodCountReal,csodPrice,csodPriceReal,csodTotal,csodRebate,csodTotalReal,csodRemark,csodAddTime)
	 CsOrderDetail(
	 	$.getLong("csodHost")//城市 [非空]
	 	,$.getLong("csodOrder")//所属定单 [非空]
	 	,$.getLong("csodProduct")//所属产品 [非空]
	 	,$.getLong("csodGoods")//所属商品 [非空]
	 	,$.getLong("csodPricer")//所属价格 [非空]
	 	,$.getLong("csodOutlets")//所属网点 [非空]
	 	,$.getLong("csodModel")//所属车型 [非空]
	 	,$.getLong("csodCar")//所属车辆 [非空]
	 	,$.getLong("csodProvid")//所属商家 [非空]
	 	,$.getDate("csodStart")//开始时间
	 	,$.getDate("csodEnd")//结束时间
	 	,$.getLong("csodRule")//单位 [非空]
	 	,$.getDouble("csodCount")//理论数量 [非空]
	 	,$.getDouble("csodCountReal")//实际数量 [非空]
	 	,$.getDouble("csodPrice")//理论单价 [非空]
	 	,$.getDouble("csodPriceReal")//实际单价 [非空]
	 	,$.getDouble("csodTotal")//理论金额 [非空]
	 	,$.getDouble("csodRebate")//折扣 [非空]
	 	,$.getDouble("csodTotalReal")//实际金额 [非空]
	 	,$.getString("csodRemark")//备注
	 	,$.getDate("csodAddTime")//添加时间 [非空]
	 )
	**/
	public CsOrderDetail(Long csodHost,Long csodOrder,Long csodProduct,Long csodGoods,Long csodPricer,Long csodOutlets,Long csodModel,Long csodCar,Long csodProvid,Date csodStart,Date csodEnd,Long csodRule,Double csodCount,Double csodCountReal,Double csodPrice,Double csodPriceReal,Double csodTotal,Double csodRebate,Double csodTotalReal,String csodRemark,Date csodAddTime){
		this.csodHost=csodHost;
		this.csodOrder=csodOrder;
		this.csodProduct=csodProduct;
		this.csodGoods=csodGoods;
		this.csodPricer=csodPricer;
		this.csodOutlets=csodOutlets;
		this.csodModel=csodModel;
		this.csodCar=csodCar;
		this.csodProvid=csodProvid;
		this.csodStart=csodStart;
		this.csodEnd=csodEnd;
		this.csodRule=csodRule;
		this.csodCount=csodCount;
		this.csodCountReal=csodCountReal;
		this.csodPrice=csodPrice;
		this.csodPriceReal=csodPriceReal;
		this.csodTotal=csodTotal;
		this.csodRebate=csodRebate;
		this.csodTotalReal=csodTotalReal;
		this.csodRemark=csodRemark;
		this.csodAddTime=csodAddTime;
	}
	
	//设置非空字段
	public CsOrderDetail setNotNull(Long csodHost,Long csodOrder,Long csodProduct,Long csodGoods,Long csodPricer,Long csodOutlets,Long csodModel,Long csodCar,Long csodProvid,Long csodRule,Double csodCount,Double csodCountReal,Double csodPrice,Double csodPriceReal,Double csodTotal,Double csodRebate,Double csodTotalReal,Date csodAddTime){
		this.csodHost=csodHost;
		this.csodOrder=csodOrder;
		this.csodProduct=csodProduct;
		this.csodGoods=csodGoods;
		this.csodPricer=csodPricer;
		this.csodOutlets=csodOutlets;
		this.csodModel=csodModel;
		this.csodCar=csodCar;
		this.csodProvid=csodProvid;
		this.csodRule=csodRule;
		this.csodCount=csodCount;
		this.csodCountReal=csodCountReal;
		this.csodPrice=csodPrice;
		this.csodPriceReal=csodPriceReal;
		this.csodTotal=csodTotal;
		this.csodRebate=csodRebate;
		this.csodTotalReal=csodTotalReal;
		this.csodAddTime=csodAddTime;
		return this;
	}
	/** 编号 [非空]       **/
	public CsOrderDetail csodId(Long csodId){
		this.csodId = csodId;
		this.setSeted(F.csodId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsOrderDetail csodHost(Long csodHost){
		this.csodHost = csodHost;
		this.setSeted(F.csodHost);
		return this;
	}
	/** 所属定单 [非空] [CsOrder]      **/
	public CsOrderDetail csodOrder(Long csodOrder){
		this.csodOrder = csodOrder;
		this.setSeted(F.csodOrder);
		return this;
	}
	/** 所属产品 [非空] [CsProduct]      **/
	public CsOrderDetail csodProduct(Long csodProduct){
		this.csodProduct = csodProduct;
		this.setSeted(F.csodProduct);
		return this;
	}
	/** 所属商品 [非空] [CsGoods]      **/
	public CsOrderDetail csodGoods(Long csodGoods){
		this.csodGoods = csodGoods;
		this.setSeted(F.csodGoods);
		return this;
	}
	/** 所属价格 [非空] [CsPrice]      **/
	public CsOrderDetail csodPricer(Long csodPricer){
		this.csodPricer = csodPricer;
		this.setSeted(F.csodPricer);
		return this;
	}
	/** 所属网点 [非空] [CsOutlets]     ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
	public CsOrderDetail csodOutlets(Long csodOutlets){
		this.csodOutlets = csodOutlets;
		this.setSeted(F.csodOutlets);
		return this;
	}
	/** 所属车型 [非空] [CsCarModel]      **/
	public CsOrderDetail csodModel(Long csodModel){
		this.csodModel = csodModel;
		this.setSeted(F.csodModel);
		return this;
	}
	/** 所属车辆 [非空] [CsCar]      **/
	public CsOrderDetail csodCar(Long csodCar){
		this.csodCar = csodCar;
		this.setSeted(F.csodCar);
		return this;
	}
	/** 所属商家 [非空] [CsProvid]      **/
	public CsOrderDetail csodProvid(Long csodProvid){
		this.csodProvid = csodProvid;
		this.setSeted(F.csodProvid);
		return this;
	}
	/** 开始时间        **/
	public CsOrderDetail csodStart(Date csodStart){
		this.csodStart = csodStart;
		this.setSeted(F.csodStart);
		return this;
	}
	/** 结束时间        **/
	public CsOrderDetail csodEnd(Date csodEnd){
		this.csodEnd = csodEnd;
		this.setSeted(F.csodEnd);
		return this;
	}
	/** 单位 [非空] [CsRule]      **/
	public CsOrderDetail csodRule(Long csodRule){
		this.csodRule = csodRule;
		this.setSeted(F.csodRule);
		return this;
	}
	/** 理论数量 [非空]       **/
	public CsOrderDetail csodCount(Double csodCount){
		this.csodCount = csodCount;
		this.setSeted(F.csodCount);
		return this;
	}
	/** 实际数量 [非空]       **/
	public CsOrderDetail csodCountReal(Double csodCountReal){
		this.csodCountReal = csodCountReal;
		this.setSeted(F.csodCountReal);
		return this;
	}
	/** 理论单价 [非空]       **/
	public CsOrderDetail csodPrice(Double csodPrice){
		this.csodPrice = csodPrice;
		this.setSeted(F.csodPrice);
		return this;
	}
	/** 实际单价 [非空]       **/
	public CsOrderDetail csodPriceReal(Double csodPriceReal){
		this.csodPriceReal = csodPriceReal;
		this.setSeted(F.csodPriceReal);
		return this;
	}
	/** 理论金额 [非空]       **/
	public CsOrderDetail csodTotal(Double csodTotal){
		this.csodTotal = csodTotal;
		this.setSeted(F.csodTotal);
		return this;
	}
	/** 折扣 [非空]    0到1,比如0.75表示七五折   **/
	public CsOrderDetail csodRebate(Double csodRebate){
		this.csodRebate = csodRebate;
		this.setSeted(F.csodRebate);
		return this;
	}
	/** 实际金额 [非空]    实付金额=实际单价*实际数量   **/
	public CsOrderDetail csodTotalReal(Double csodTotalReal){
		this.csodTotalReal = csodTotalReal;
		this.setSeted(F.csodTotalReal);
		return this;
	}
	/** 备注        **/
	public CsOrderDetail csodRemark(String csodRemark){
		this.csodRemark = csodRemark;
		this.setSeted(F.csodRemark);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsOrderDetail csodAddTime(Date csodAddTime){
		this.csodAddTime = csodAddTime;
		this.setSeted(F.csodAddTime);
		return this;
	}
	
	
	//克隆对象
	public CsOrderDetail clone(){
		CsOrderDetail clone = new CsOrderDetail();
		clone.csodHost=this.csodHost;
		clone.csodOrder=this.csodOrder;
		clone.csodProduct=this.csodProduct;
		clone.csodGoods=this.csodGoods;
		clone.csodPricer=this.csodPricer;
		clone.csodOutlets=this.csodOutlets;
		clone.csodModel=this.csodModel;
		clone.csodCar=this.csodCar;
		clone.csodProvid=this.csodProvid;
		clone.csodRule=this.csodRule;
		clone.csodCount=this.csodCount;
		clone.csodCountReal=this.csodCountReal;
		clone.csodPrice=this.csodPrice;
		clone.csodPriceReal=this.csodPriceReal;
		clone.csodTotal=this.csodTotal;
		clone.csodRebate=this.csodRebate;
		clone.csodTotalReal=this.csodTotalReal;
		clone.csodAddTime=this.csodAddTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取订单明细
	 * @param id
	 * @return
	 */
	public static @api CsOrderDetail get(Long id){		
		return getCsOrderDetailById(id);
	}
	/**
	 * 获取所有订单明细
	 * @return
	 */
	public static @api List<CsOrderDetail> list(Map params,Integer size){
		return getCsOrderDetailList(params,size);
	}
	/**
	 * 获取订单明细分页
	 * @return
	 */
	public static @api Page<CsOrderDetail> page(int page,int size,Map params){
		return getCsOrderDetailPage(page, size , params);
	}
	/**
	 * 根据查询条件取订单明细
	 * @param params
	 * @return
	 */
	public static @api CsOrderDetail Get(Map params){
		return getCsOrderDetail(params);
	}
	/**
	 * 获取订单明细数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsOrderDetailCount(params);
	}
	/**
	 * 获取订单明细数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsOrderDetailEval(eval,params);
	}
	
	/**
	 * 根据ID取订单明细
	 * @param id
	 * @return
	 */
	public static @api CsOrderDetail getCsOrderDetailById(Long id){		
		CsOrderDetail csOrderDetail = (CsOrderDetail) $.GetRequest("CsOrderDetail$"+id);
		if(csOrderDetail!=null)
			return csOrderDetail;
		com.ccclubs.service.admin.ICsOrderDetailService csOrderDetailService = $.GetSpringBean("csOrderDetailService");		
		return csOrderDetailService.getCsOrderDetailById(id);
	}
	
	
	/**
	 * 根据ID取订单明细的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		//当前对象不支持键值缓存
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsodId$();
		if(!"CsodId".equals("CsodId"))
			keyValue+="("+this.getCsodId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有订单明细
	 * @return
	 */
	public static @api List<CsOrderDetail> getCsOrderDetailList(Map params,Integer size){
		com.ccclubs.service.admin.ICsOrderDetailService csOrderDetailService = $.GetSpringBean("csOrderDetailService");
		return csOrderDetailService.getCsOrderDetailList(params, size);
	}
	
	/**
	 * 获取订单明细分页
	 * @return
	 */
	public static @api Page<CsOrderDetail> getCsOrderDetailPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsOrderDetailService csOrderDetailService = $.GetSpringBean("csOrderDetailService");
		return csOrderDetailService.getCsOrderDetailPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取订单明细
	 * @param params
	 * @return
	 */
	public static @api CsOrderDetail getCsOrderDetail(Map params){
		com.ccclubs.service.admin.ICsOrderDetailService csOrderDetailService = $.GetSpringBean("csOrderDetailService");
		return csOrderDetailService.getCsOrderDetail(params);
	}
	
	/**
	 * 获取订单明细数
	 * @return
	 */
	public static @api Long getCsOrderDetailCount(Map params){
		com.ccclubs.service.admin.ICsOrderDetailService csOrderDetailService = $.GetSpringBean("csOrderDetailService");
		return csOrderDetailService.getCsOrderDetailCount(params);
	}
		
		
	/**
	 * 获取订单明细自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsOrderDetailEval(String eval,Map params){
		com.ccclubs.service.admin.ICsOrderDetailService csOrderDetailService = $.GetSpringBean("csOrderDetailService");
		return csOrderDetailService.getCsOrderDetailEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsOrderDetail(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsOrderDetailService csOrderDetailService = $.GetSpringBean("csOrderDetailService");
		csOrderDetailService.updateCsOrderDetailByConfirm(set, where);
	}
	
	
	/**
	 * 保存订单明细对象
	 * @param params
	 * @return
	 */
	public CsOrderDetail save(){
		com.ccclubs.service.admin.ICsOrderDetailService csOrderDetailService = $.GetSpringBean("csOrderDetailService");
		if(this.getCsodId()!=null)
			csOrderDetailService.updateCsOrderDetail(this);
		else
			return csOrderDetailService.saveCsOrderDetail(this);
		return this;
	}
	
	
	/**
	 * 更新订单明细对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsOrderDetailService csOrderDetailService = $.GetSpringBean("csOrderDetailService");
		csOrderDetailService.updateCsOrderDetail$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsOrderDetailService csOrderDetailService = $.GetSpringBean("csOrderDetailService");
			csOrderDetailService.deleteCsOrderDetailById(this.getCsodId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsOrderDetailService csOrderDetailService = $.GetSpringBean("csOrderDetailService");
		return csOrderDetailService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsodId(){
		return this.csodId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsodId$(){
		String strValue="";
		 strValue=$.str(this.getCsodId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsodId(Long csodId){
		this.csodId = csodId;
		this.setSeted(F.csodId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsodHost(){
		return this.csodHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsodHost$(){
		String strValue="";
		if(this.getCsodHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsodHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsodHost(Long csodHost){
		this.csodHost = csodHost;
		this.setSeted(F.csodHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csodHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsodHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsodHost()!=null){
 			srvHost = SrvHost.get(this.getCsodHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsodHost(), srvHost);
	 	return srvHost;
	}
	/*******************************所属定单**********************************/	
	/**
	* 所属定单 [非空] [CsOrder]     
	**/
	public Long getCsodOrder(){
		return this.csodOrder;
	}
	/**
	* 获取所属定单格式化(toString)
	**/
	public String getCsodOrder$(){
		String strValue="";
		if(this.getCsodOrder()!=null){
				strValue+=$.str(CsOrder.getKeyValue(this.getCsodOrder()));
		}			
	 	 return strValue;
	}
	/**
	* 所属定单 [非空] [CsOrder]     
	**/
	public void setCsodOrder(Long csodOrder){
		this.csodOrder = csodOrder;
		this.setSeted(F.csodOrder);
	}
	/**
	* 获取关联对象[系统订单]
	**/	 			
 	public CsOrder get$csodOrder(){
 		com.ccclubs.model.CsOrder csOrder = (com.ccclubs.model.CsOrder) $.GetRequest("CsOrder$"+this.getCsodOrder());
 		if(csOrder!=null)
			return csOrder;
		if(this.getCsodOrder()!=null){
 			csOrder = CsOrder.get(this.getCsodOrder());
 		}
 		$.SetRequest("CsOrder$"+this.getCsodOrder(), csOrder);
	 	return csOrder;
	}
	/*******************************所属产品**********************************/	
	/**
	* 所属产品 [非空] [CsProduct]     
	**/
	public Long getCsodProduct(){
		return this.csodProduct;
	}
	/**
	* 获取所属产品格式化(toString)
	**/
	public String getCsodProduct$(){
		String strValue="";
		if(this.getCsodProduct()!=null){
				strValue+=$.str(CsProduct.getKeyValue(this.getCsodProduct()));
		}			
	 	 return strValue;
	}
	/**
	* 所属产品 [非空] [CsProduct]     
	**/
	public void setCsodProduct(Long csodProduct){
		this.csodProduct = csodProduct;
		this.setSeted(F.csodProduct);
	}
	/**
	* 获取关联对象[产品]
	**/	 			
 	public CsProduct get$csodProduct(){
 		com.ccclubs.model.CsProduct csProduct = (com.ccclubs.model.CsProduct) $.GetRequest("CsProduct$"+this.getCsodProduct());
 		if(csProduct!=null)
			return csProduct;
		if(this.getCsodProduct()!=null){
 			csProduct = CsProduct.get(this.getCsodProduct());
 		}
 		$.SetRequest("CsProduct$"+this.getCsodProduct(), csProduct);
	 	return csProduct;
	}
	/*******************************所属商品**********************************/	
	/**
	* 所属商品 [非空] [CsGoods]     
	**/
	public Long getCsodGoods(){
		return this.csodGoods;
	}
	/**
	* 获取所属商品格式化(toString)
	**/
	public String getCsodGoods$(){
		String strValue="";
		if(this.getCsodGoods()!=null){
				strValue+=$.str(CsGoods.getKeyValue(this.getCsodGoods()));
		}			
	 	 return strValue;
	}
	/**
	* 所属商品 [非空] [CsGoods]     
	**/
	public void setCsodGoods(Long csodGoods){
		this.csodGoods = csodGoods;
		this.setSeted(F.csodGoods);
	}
	/**
	* 获取关联对象[商品]
	**/	 			
 	public CsGoods get$csodGoods(){
 		com.ccclubs.model.CsGoods csGoods = (com.ccclubs.model.CsGoods) $.GetRequest("CsGoods$"+this.getCsodGoods());
 		if(csGoods!=null)
			return csGoods;
		if(this.getCsodGoods()!=null){
 			csGoods = CsGoods.get(this.getCsodGoods());
 		}
 		$.SetRequest("CsGoods$"+this.getCsodGoods(), csGoods);
	 	return csGoods;
	}
	/*******************************所属价格**********************************/	
	/**
	* 所属价格 [非空] [CsPrice]     
	**/
	public Long getCsodPricer(){
		return this.csodPricer;
	}
	/**
	* 获取所属价格格式化(toString)
	**/
	public String getCsodPricer$(){
		String strValue="";
		if(this.getCsodPricer()!=null){
				strValue+=$.str(CsPrice.getKeyValue(this.getCsodPricer()));
		}			
	 	 return strValue;
	}
	/**
	* 所属价格 [非空] [CsPrice]     
	**/
	public void setCsodPricer(Long csodPricer){
		this.csodPricer = csodPricer;
		this.setSeted(F.csodPricer);
	}
	/**
	* 获取关联对象[价格]
	**/	 			
 	public CsPrice get$csodPricer(){
 		com.ccclubs.model.CsPrice csPrice = (com.ccclubs.model.CsPrice) $.GetRequest("CsPrice$"+this.getCsodPricer());
 		if(csPrice!=null)
			return csPrice;
		if(this.getCsodPricer()!=null){
 			csPrice = CsPrice.get(this.getCsodPricer());
 		}
 		$.SetRequest("CsPrice$"+this.getCsodPricer(), csPrice);
	 	return csPrice;
	}
	/*******************************所属网点**********************************/	
	/**
	* 所属网点 [非空] [CsOutlets]     ${basePath}${proname}/object/outlets_tree.do?parent={param}
	**/
	public Long getCsodOutlets(){
		return this.csodOutlets;
	}
	/**
	* 获取所属网点格式化(toString)
	**/
	public String getCsodOutlets$(){
		String strValue="";
		if(this.getCsodOutlets()!=null){
				strValue+=$.str(CsOutlets.getKeyValue(this.getCsodOutlets()));
		}			
	 	 return strValue;
	}
	/**
	* 所属网点 [非空] [CsOutlets]     ${basePath}${proname}/object/outlets_tree.do?parent={param}
	**/
	public void setCsodOutlets(Long csodOutlets){
		this.csodOutlets = csodOutlets;
		this.setSeted(F.csodOutlets);
	}
	/**
	* 获取关联对象[网点]
	**/	 			
 	public CsOutlets get$csodOutlets(){
 		com.ccclubs.model.CsOutlets csOutlets = (com.ccclubs.model.CsOutlets) $.GetRequest("CsOutlets$"+this.getCsodOutlets());
 		if(csOutlets!=null)
			return csOutlets;
		if(this.getCsodOutlets()!=null){
 			csOutlets = CsOutlets.get(this.getCsodOutlets());
 		}
 		$.SetRequest("CsOutlets$"+this.getCsodOutlets(), csOutlets);
	 	return csOutlets;
	}
	/*******************************所属车型**********************************/	
	/**
	* 所属车型 [非空] [CsCarModel]     
	**/
	public Long getCsodModel(){
		return this.csodModel;
	}
	/**
	* 获取所属车型格式化(toString)
	**/
	public String getCsodModel$(){
		String strValue="";
		if(this.getCsodModel()!=null){
				strValue+=$.str(CsCarModel.getKeyValue(this.getCsodModel()));
		}			
	 	 return strValue;
	}
	/**
	* 所属车型 [非空] [CsCarModel]     
	**/
	public void setCsodModel(Long csodModel){
		this.csodModel = csodModel;
		this.setSeted(F.csodModel);
	}
	/**
	* 获取关联对象[车型]
	**/	 			
 	public CsCarModel get$csodModel(){
 		com.ccclubs.model.CsCarModel csCarModel = (com.ccclubs.model.CsCarModel) $.GetRequest("CsCarModel$"+this.getCsodModel());
 		if(csCarModel!=null)
			return csCarModel;
		if(this.getCsodModel()!=null){
 			csCarModel = CsCarModel.get(this.getCsodModel());
 		}
 		$.SetRequest("CsCarModel$"+this.getCsodModel(), csCarModel);
	 	return csCarModel;
	}
	/*******************************所属车辆**********************************/	
	/**
	* 所属车辆 [非空] [CsCar]     
	**/
	public Long getCsodCar(){
		return this.csodCar;
	}
	/**
	* 获取所属车辆格式化(toString)
	**/
	public String getCsodCar$(){
		String strValue="";
		if(this.getCsodCar()!=null){
				strValue+=$.str(CsCar.getKeyValue(this.getCsodCar()));
		}			
	 	 return strValue;
	}
	/**
	* 所属车辆 [非空] [CsCar]     
	**/
	public void setCsodCar(Long csodCar){
		this.csodCar = csodCar;
		this.setSeted(F.csodCar);
	}
	/**
	* 获取关联对象[车辆]
	**/	 			
 	public CsCar get$csodCar(){
 		com.ccclubs.model.CsCar csCar = (com.ccclubs.model.CsCar) $.GetRequest("CsCar$"+this.getCsodCar());
 		if(csCar!=null)
			return csCar;
		if(this.getCsodCar()!=null){
 			csCar = CsCar.get(this.getCsodCar());
 		}
 		$.SetRequest("CsCar$"+this.getCsodCar(), csCar);
	 	return csCar;
	}
	/*******************************所属商家**********************************/	
	/**
	* 所属商家 [非空] [CsProvid]     
	**/
	public Long getCsodProvid(){
		return this.csodProvid;
	}
	/**
	* 获取所属商家格式化(toString)
	**/
	public String getCsodProvid$(){
		String strValue="";
		if(this.getCsodProvid()!=null){
				strValue+=$.str(CsProvid.getKeyValue(this.getCsodProvid()));
		}			
	 	 return strValue;
	}
	/**
	* 所属商家 [非空] [CsProvid]     
	**/
	public void setCsodProvid(Long csodProvid){
		this.csodProvid = csodProvid;
		this.setSeted(F.csodProvid);
	}
	/**
	* 获取关联对象[供应商]
	**/	 			
 	public CsProvid get$csodProvid(){
 		com.ccclubs.model.CsProvid csProvid = (com.ccclubs.model.CsProvid) $.GetRequest("CsProvid$"+this.getCsodProvid());
 		if(csProvid!=null)
			return csProvid;
		if(this.getCsodProvid()!=null){
 			csProvid = CsProvid.get(this.getCsodProvid());
 		}
 		$.SetRequest("CsProvid$"+this.getCsodProvid(), csProvid);
	 	return csProvid;
	}
	/*******************************开始时间**********************************/	
	/**
	* 开始时间       
	**/
	public Date getCsodStart(){
		return this.csodStart;
	}
	/**
	* 获取开始时间格式化(toString)
	**/
	public String getCsodStart$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsodStart(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 开始时间       
	**/
	public void setCsodStart(Date csodStart){
		this.csodStart = csodStart;
		this.setSeted(F.csodStart);
	}
	/*******************************结束时间**********************************/	
	/**
	* 结束时间       
	**/
	public Date getCsodEnd(){
		return this.csodEnd;
	}
	/**
	* 获取结束时间格式化(toString)
	**/
	public String getCsodEnd$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsodEnd(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 结束时间       
	**/
	public void setCsodEnd(Date csodEnd){
		this.csodEnd = csodEnd;
		this.setSeted(F.csodEnd);
	}
	/*******************************单位**********************************/	
	/**
	* 单位 [非空] [CsRule]     
	**/
	public Long getCsodRule(){
		return this.csodRule;
	}
	/**
	* 获取单位格式化(toString)
	**/
	public String getCsodRule$(){
		String strValue="";
		if(this.getCsodRule()!=null){
				strValue+=$.str(CsRule.getKeyValue(this.getCsodRule()));
		}			
	 	 return strValue;
	}
	/**
	* 单位 [非空] [CsRule]     
	**/
	public void setCsodRule(Long csodRule){
		this.csodRule = csodRule;
		this.setSeted(F.csodRule);
	}
	/**
	* 获取关联对象[规则]
	**/	 			
 	public CsRule get$csodRule(){
 		com.ccclubs.model.CsRule csRule = (com.ccclubs.model.CsRule) $.GetRequest("CsRule$"+this.getCsodRule());
 		if(csRule!=null)
			return csRule;
		if(this.getCsodRule()!=null){
 			csRule = CsRule.get(this.getCsodRule());
 		}
 		$.SetRequest("CsRule$"+this.getCsodRule(), csRule);
	 	return csRule;
	}
	/*******************************理论数量**********************************/	
	/**
	* 理论数量 [非空]      
	**/
	public Double getCsodCount(){
		return this.csodCount;
	}
	/**
	* 获取理论数量格式化(toString)
	**/
	public String getCsodCount$(){
		String strValue="";
		 strValue=$.str(this.getCsodCount());	
	 	 return strValue;
	}
	/**
	* 理论数量 [非空]      
	**/
	public void setCsodCount(Double csodCount){
		this.csodCount = csodCount;
		this.setSeted(F.csodCount);
	}
	/*******************************实际数量**********************************/	
	/**
	* 实际数量 [非空]      
	**/
	public Double getCsodCountReal(){
		return this.csodCountReal;
	}
	/**
	* 获取实际数量格式化(toString)
	**/
	public String getCsodCountReal$(){
		String strValue="";
		 strValue=$.str(this.getCsodCountReal());	
	 	 return strValue;
	}
	/**
	* 实际数量 [非空]      
	**/
	public void setCsodCountReal(Double csodCountReal){
		this.csodCountReal = csodCountReal;
		this.setSeted(F.csodCountReal);
	}
	/*******************************理论单价**********************************/	
	/**
	* 理论单价 [非空]      
	**/
	public Double getCsodPrice(){
		return this.csodPrice;
	}
	/**
	* 获取理论单价格式化(toString)
	**/
	public String getCsodPrice$(){
		String strValue="";
		 strValue=$.str(this.getCsodPrice());	
	 	 return strValue;
	}
	/**
	* 理论单价 [非空]      
	**/
	public void setCsodPrice(Double csodPrice){
		this.csodPrice = csodPrice;
		this.setSeted(F.csodPrice);
	}
	/*******************************实际单价**********************************/	
	/**
	* 实际单价 [非空]      
	**/
	public Double getCsodPriceReal(){
		return this.csodPriceReal;
	}
	/**
	* 获取实际单价格式化(toString)
	**/
	public String getCsodPriceReal$(){
		String strValue="";
		 strValue=$.str(this.getCsodPriceReal());	
	 	 return strValue;
	}
	/**
	* 实际单价 [非空]      
	**/
	public void setCsodPriceReal(Double csodPriceReal){
		this.csodPriceReal = csodPriceReal;
		this.setSeted(F.csodPriceReal);
	}
	/*******************************理论金额**********************************/	
	/**
	* 理论金额 [非空]      
	**/
	public Double getCsodTotal(){
		return this.csodTotal;
	}
	/**
	* 获取理论金额格式化(toString)
	**/
	public String getCsodTotal$(){
		String strValue="";
		 strValue=$.str(this.getCsodTotal());	
	 	 return strValue;
	}
	/**
	* 理论金额 [非空]      
	**/
	public void setCsodTotal(Double csodTotal){
		this.csodTotal = csodTotal;
		this.setSeted(F.csodTotal);
	}
	/*******************************折扣**********************************/	
	/**
	* 折扣 [非空]    0到1,比如0.75表示七五折  
	**/
	public Double getCsodRebate(){
		return this.csodRebate;
	}
	/**
	* 获取折扣格式化(toString)
	**/
	public String getCsodRebate$(){
		String strValue="";
		 strValue=$.str(this.getCsodRebate());	
	 	 return strValue;
	}
	/**
	* 折扣 [非空]    0到1,比如0.75表示七五折  
	**/
	public void setCsodRebate(Double csodRebate){
		this.csodRebate = csodRebate;
		this.setSeted(F.csodRebate);
	}
	/*******************************实际金额**********************************/	
	/**
	* 实际金额 [非空]    实付金额=实际单价*实际数量  
	**/
	public Double getCsodTotalReal(){
		return this.csodTotalReal;
	}
	/**
	* 获取实际金额格式化(toString)
	**/
	public String getCsodTotalReal$(){
		String strValue="";
		 strValue=$.str(this.getCsodTotalReal());	
	 	 return strValue;
	}
	/**
	* 实际金额 [非空]    实付金额=实际单价*实际数量  
	**/
	public void setCsodTotalReal(Double csodTotalReal){
		this.csodTotalReal = csodTotalReal;
		this.setSeted(F.csodTotalReal);
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getCsodRemark(){
		return this.csodRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCsodRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsodRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setCsodRemark(String csodRemark){
		this.csodRemark = csodRemark;
		this.setSeted(F.csodRemark);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsodAddTime(){
		return this.csodAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsodAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsodAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsodAddTime(Date csodAddTime){
		this.csodAddTime = csodAddTime;
		this.setSeted(F.csodAddTime);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsOrderDetail.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsOrderDetail.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsOrderDetail.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsOrderDetail.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsOrderDetail.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsOrderDetail.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsOrderDetail.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsOrderDetail.this);
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
		public M csodId(Object csodId){this.put("csodId", csodId);return this;};
	 	/** and csod_id is null */
 		public M csodIdNull(){if(this.get("csodIdNot")==null)this.put("csodIdNot", "");this.put("csodId", null);return this;};
 		/** not .... */
 		public M csodIdNot(){this.put("csodIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csodHost(Object csodHost){this.put("csodHost", csodHost);return this;};
	 	/** and csod_host is null */
 		public M csodHostNull(){if(this.get("csodHostNot")==null)this.put("csodHostNot", "");this.put("csodHost", null);return this;};
 		/** not .... */
 		public M csodHostNot(){this.put("csodHostNot", "not");return this;};
		/** 所属定单 [非空] [CsOrder]      **/
		public M csodOrder(Object csodOrder){this.put("csodOrder", csodOrder);return this;};
	 	/** and csod_order is null */
 		public M csodOrderNull(){if(this.get("csodOrderNot")==null)this.put("csodOrderNot", "");this.put("csodOrder", null);return this;};
 		/** not .... */
 		public M csodOrderNot(){this.put("csodOrderNot", "not");return this;};
		public M csodOrder$on(CsOrder.M value){
			this.put("CsOrder", value);
			this.put("csodOrder$on", value);
			return this;
		};	
		/** 所属产品 [非空] [CsProduct]      **/
		public M csodProduct(Object csodProduct){this.put("csodProduct", csodProduct);return this;};
	 	/** and csod_product is null */
 		public M csodProductNull(){if(this.get("csodProductNot")==null)this.put("csodProductNot", "");this.put("csodProduct", null);return this;};
 		/** not .... */
 		public M csodProductNot(){this.put("csodProductNot", "not");return this;};
		public M csodProduct$on(CsProduct.M value){
			this.put("CsProduct", value);
			this.put("csodProduct$on", value);
			return this;
		};	
		/** 所属商品 [非空] [CsGoods]      **/
		public M csodGoods(Object csodGoods){this.put("csodGoods", csodGoods);return this;};
	 	/** and csod_goods is null */
 		public M csodGoodsNull(){if(this.get("csodGoodsNot")==null)this.put("csodGoodsNot", "");this.put("csodGoods", null);return this;};
 		/** not .... */
 		public M csodGoodsNot(){this.put("csodGoodsNot", "not");return this;};
		public M csodGoods$on(CsGoods.M value){
			this.put("CsGoods", value);
			this.put("csodGoods$on", value);
			return this;
		};	
		/** 所属价格 [非空] [CsPrice]      **/
		public M csodPricer(Object csodPricer){this.put("csodPricer", csodPricer);return this;};
	 	/** and csod_pricer is null */
 		public M csodPricerNull(){if(this.get("csodPricerNot")==null)this.put("csodPricerNot", "");this.put("csodPricer", null);return this;};
 		/** not .... */
 		public M csodPricerNot(){this.put("csodPricerNot", "not");return this;};
		public M csodPricer$on(CsPrice.M value){
			this.put("CsPrice", value);
			this.put("csodPricer$on", value);
			return this;
		};	
		/** 所属网点 [非空] [CsOutlets]     ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
		public M csodOutlets(Object csodOutlets){this.put("csodOutlets", csodOutlets);return this;};
	 	/** and csod_outlets is null */
 		public M csodOutletsNull(){if(this.get("csodOutletsNot")==null)this.put("csodOutletsNot", "");this.put("csodOutlets", null);return this;};
 		/** not .... */
 		public M csodOutletsNot(){this.put("csodOutletsNot", "not");return this;};
		public M csodOutlets$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("csodOutlets$on", value);
			return this;
		};	
		/** 所属车型 [非空] [CsCarModel]      **/
		public M csodModel(Object csodModel){this.put("csodModel", csodModel);return this;};
	 	/** and csod_model is null */
 		public M csodModelNull(){if(this.get("csodModelNot")==null)this.put("csodModelNot", "");this.put("csodModel", null);return this;};
 		/** not .... */
 		public M csodModelNot(){this.put("csodModelNot", "not");return this;};
		public M csodModel$on(CsCarModel.M value){
			this.put("CsCarModel", value);
			this.put("csodModel$on", value);
			return this;
		};	
		/** 所属车辆 [非空] [CsCar]      **/
		public M csodCar(Object csodCar){this.put("csodCar", csodCar);return this;};
	 	/** and csod_car is null */
 		public M csodCarNull(){if(this.get("csodCarNot")==null)this.put("csodCarNot", "");this.put("csodCar", null);return this;};
 		/** not .... */
 		public M csodCarNot(){this.put("csodCarNot", "not");return this;};
		public M csodCar$on(CsCar.M value){
			this.put("CsCar", value);
			this.put("csodCar$on", value);
			return this;
		};	
		/** 所属商家 [非空] [CsProvid]      **/
		public M csodProvid(Object csodProvid){this.put("csodProvid", csodProvid);return this;};
	 	/** and csod_provid is null */
 		public M csodProvidNull(){if(this.get("csodProvidNot")==null)this.put("csodProvidNot", "");this.put("csodProvid", null);return this;};
 		/** not .... */
 		public M csodProvidNot(){this.put("csodProvidNot", "not");return this;};
		public M csodProvid$on(CsProvid.M value){
			this.put("CsProvid", value);
			this.put("csodProvid$on", value);
			return this;
		};	
		/** 开始时间        **/
		public M csodStart(Object csodStart){this.put("csodStart", csodStart);return this;};
	 	/** and csod_start is null */
 		public M csodStartNull(){if(this.get("csodStartNot")==null)this.put("csodStartNot", "");this.put("csodStart", null);return this;};
 		/** not .... */
 		public M csodStartNot(){this.put("csodStartNot", "not");return this;};
 		/** and csod_start >= ? */
 		public M csodStartStart(Object start){this.put("csodStartStart", start);return this;};			
 		/** and csod_start <= ? */
 		public M csodStartEnd(Object end){this.put("csodStartEnd", end);return this;};
		/** 结束时间        **/
		public M csodEnd(Object csodEnd){this.put("csodEnd", csodEnd);return this;};
	 	/** and csod_end is null */
 		public M csodEndNull(){if(this.get("csodEndNot")==null)this.put("csodEndNot", "");this.put("csodEnd", null);return this;};
 		/** not .... */
 		public M csodEndNot(){this.put("csodEndNot", "not");return this;};
 		/** and csod_end >= ? */
 		public M csodEndStart(Object start){this.put("csodEndStart", start);return this;};			
 		/** and csod_end <= ? */
 		public M csodEndEnd(Object end){this.put("csodEndEnd", end);return this;};
		/** 单位 [非空] [CsRule]      **/
		public M csodRule(Object csodRule){this.put("csodRule", csodRule);return this;};
	 	/** and csod_rule is null */
 		public M csodRuleNull(){if(this.get("csodRuleNot")==null)this.put("csodRuleNot", "");this.put("csodRule", null);return this;};
 		/** not .... */
 		public M csodRuleNot(){this.put("csodRuleNot", "not");return this;};
		public M csodRule$on(CsRule.M value){
			this.put("CsRule", value);
			this.put("csodRule$on", value);
			return this;
		};	
		/** 理论数量 [非空]       **/
		public M csodCount(Object csodCount){this.put("csodCount", csodCount);return this;};
	 	/** and csod_count is null */
 		public M csodCountNull(){if(this.get("csodCountNot")==null)this.put("csodCountNot", "");this.put("csodCount", null);return this;};
 		/** not .... */
 		public M csodCountNot(){this.put("csodCountNot", "not");return this;};
		/** and csod_count >= ? */
		public M csodCountMin(Object min){this.put("csodCountMin", min);return this;};
		/** and csod_count <= ? */
		public M csodCountMax(Object max){this.put("csodCountMax", max);return this;};
		/** 实际数量 [非空]       **/
		public M csodCountReal(Object csodCountReal){this.put("csodCountReal", csodCountReal);return this;};
	 	/** and csod_count_real is null */
 		public M csodCountRealNull(){if(this.get("csodCountRealNot")==null)this.put("csodCountRealNot", "");this.put("csodCountReal", null);return this;};
 		/** not .... */
 		public M csodCountRealNot(){this.put("csodCountRealNot", "not");return this;};
		/** and csod_count_real >= ? */
		public M csodCountRealMin(Object min){this.put("csodCountRealMin", min);return this;};
		/** and csod_count_real <= ? */
		public M csodCountRealMax(Object max){this.put("csodCountRealMax", max);return this;};
		/** 理论单价 [非空]       **/
		public M csodPrice(Object csodPrice){this.put("csodPrice", csodPrice);return this;};
	 	/** and csod_price is null */
 		public M csodPriceNull(){if(this.get("csodPriceNot")==null)this.put("csodPriceNot", "");this.put("csodPrice", null);return this;};
 		/** not .... */
 		public M csodPriceNot(){this.put("csodPriceNot", "not");return this;};
		/** and csod_price >= ? */
		public M csodPriceMin(Object min){this.put("csodPriceMin", min);return this;};
		/** and csod_price <= ? */
		public M csodPriceMax(Object max){this.put("csodPriceMax", max);return this;};
		/** 实际单价 [非空]       **/
		public M csodPriceReal(Object csodPriceReal){this.put("csodPriceReal", csodPriceReal);return this;};
	 	/** and csod_price_real is null */
 		public M csodPriceRealNull(){if(this.get("csodPriceRealNot")==null)this.put("csodPriceRealNot", "");this.put("csodPriceReal", null);return this;};
 		/** not .... */
 		public M csodPriceRealNot(){this.put("csodPriceRealNot", "not");return this;};
		/** and csod_price_real >= ? */
		public M csodPriceRealMin(Object min){this.put("csodPriceRealMin", min);return this;};
		/** and csod_price_real <= ? */
		public M csodPriceRealMax(Object max){this.put("csodPriceRealMax", max);return this;};
		/** 理论金额 [非空]       **/
		public M csodTotal(Object csodTotal){this.put("csodTotal", csodTotal);return this;};
	 	/** and csod_total is null */
 		public M csodTotalNull(){if(this.get("csodTotalNot")==null)this.put("csodTotalNot", "");this.put("csodTotal", null);return this;};
 		/** not .... */
 		public M csodTotalNot(){this.put("csodTotalNot", "not");return this;};
		/** and csod_total >= ? */
		public M csodTotalMin(Object min){this.put("csodTotalMin", min);return this;};
		/** and csod_total <= ? */
		public M csodTotalMax(Object max){this.put("csodTotalMax", max);return this;};
		/** 折扣 [非空]    0到1,比如0.75表示七五折   **/
		public M csodRebate(Object csodRebate){this.put("csodRebate", csodRebate);return this;};
	 	/** and csod_rebate is null */
 		public M csodRebateNull(){if(this.get("csodRebateNot")==null)this.put("csodRebateNot", "");this.put("csodRebate", null);return this;};
 		/** not .... */
 		public M csodRebateNot(){this.put("csodRebateNot", "not");return this;};
		/** and csod_rebate >= ? */
		public M csodRebateMin(Object min){this.put("csodRebateMin", min);return this;};
		/** and csod_rebate <= ? */
		public M csodRebateMax(Object max){this.put("csodRebateMax", max);return this;};
		/** 实际金额 [非空]    实付金额=实际单价*实际数量   **/
		public M csodTotalReal(Object csodTotalReal){this.put("csodTotalReal", csodTotalReal);return this;};
	 	/** and csod_total_real is null */
 		public M csodTotalRealNull(){if(this.get("csodTotalRealNot")==null)this.put("csodTotalRealNot", "");this.put("csodTotalReal", null);return this;};
 		/** not .... */
 		public M csodTotalRealNot(){this.put("csodTotalRealNot", "not");return this;};
		/** and csod_total_real >= ? */
		public M csodTotalRealMin(Object min){this.put("csodTotalRealMin", min);return this;};
		/** and csod_total_real <= ? */
		public M csodTotalRealMax(Object max){this.put("csodTotalRealMax", max);return this;};
		/** 备注        **/
		public M csodRemark(Object csodRemark){this.put("csodRemark", csodRemark);return this;};
	 	/** and csod_remark is null */
 		public M csodRemarkNull(){if(this.get("csodRemarkNot")==null)this.put("csodRemarkNot", "");this.put("csodRemark", null);return this;};
 		/** not .... */
 		public M csodRemarkNot(){this.put("csodRemarkNot", "not");return this;};
		/** 添加时间 [非空]       **/
		public M csodAddTime(Object csodAddTime){this.put("csodAddTime", csodAddTime);return this;};
	 	/** and csod_add_time is null */
 		public M csodAddTimeNull(){if(this.get("csodAddTimeNot")==null)this.put("csodAddTimeNot", "");this.put("csodAddTime", null);return this;};
 		/** not .... */
 		public M csodAddTimeNot(){this.put("csodAddTimeNot", "not");return this;};
 		/** and csod_add_time >= ? */
 		public M csodAddTimeStart(Object start){this.put("csodAddTimeStart", start);return this;};			
 		/** and csod_add_time <= ? */
 		public M csodAddTimeEnd(Object end){this.put("csodAddTimeEnd", end);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有订单明细 **/
		public @api List<CsOrderDetail> list(Integer size){
			return getCsOrderDetailList(this,size);
		}
		/** 获取订单明细分页 **/
		public @api Page<CsOrderDetail> page(int page,int size){
			return getCsOrderDetailPage(page, size , this);
		}
		/** 根据查询条件取订单明细 **/
		public @api CsOrderDetail get(){
			return getCsOrderDetail(this);
		}
		/** 获取订单明细数 **/
		public @api Long count(){
			return getCsOrderDetailCount(this);
		}
		/** 获取订单明细表达式 **/
		public @api <T> T eval(String strEval){
			return getCsOrderDetailEval(strEval,this);
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
			updateCsOrderDetail(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csodId="csodId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csodHost="csodHost";
		/** 所属定单 [非空] [CsOrder]      **/
		public final static @type(Long.class)  String csodOrder="csodOrder";
		/** 所属产品 [非空] [CsProduct]      **/
		public final static @type(Long.class)  String csodProduct="csodProduct";
		/** 所属商品 [非空] [CsGoods]      **/
		public final static @type(Long.class)  String csodGoods="csodGoods";
		/** 所属价格 [非空] [CsPrice]      **/
		public final static @type(Long.class)  String csodPricer="csodPricer";
		/** 所属网点 [非空] [CsOutlets]     ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
		public final static @type(Long.class)  String csodOutlets="csodOutlets";
		/** 所属车型 [非空] [CsCarModel]      **/
		public final static @type(Long.class)  String csodModel="csodModel";
		/** 所属车辆 [非空] [CsCar]      **/
		public final static @type(Long.class)  String csodCar="csodCar";
		/** 所属商家 [非空] [CsProvid]      **/
		public final static @type(Long.class)  String csodProvid="csodProvid";
		/** 开始时间        **/
		public final static @type(Date.class)  String csodStart="csodStart";
	 	/** and csod_start >= ? */
 		public final static @type(Date.class) String csodStartStart="csodStartStart";
 		/** and csod_start <= ? */
 		public final static @type(Date.class) String csodStartEnd="csodStartEnd";
		/** 结束时间        **/
		public final static @type(Date.class)  String csodEnd="csodEnd";
	 	/** and csod_end >= ? */
 		public final static @type(Date.class) String csodEndStart="csodEndStart";
 		/** and csod_end <= ? */
 		public final static @type(Date.class) String csodEndEnd="csodEndEnd";
		/** 单位 [非空] [CsRule]      **/
		public final static @type(Long.class)  String csodRule="csodRule";
		/** 理论数量 [非空]       **/
		public final static @type(Double.class)  String csodCount="csodCount";
		/** and csod_count >= ? */
		public final static @type(Double.class) String csodCountMin="csodCountMin";
		/** and csod_count <= ? */
		public final static @type(Double.class) String csodCountMax="csodCountMax";
		/** 实际数量 [非空]       **/
		public final static @type(Double.class)  String csodCountReal="csodCountReal";
		/** and csod_count_real >= ? */
		public final static @type(Double.class) String csodCountRealMin="csodCountRealMin";
		/** and csod_count_real <= ? */
		public final static @type(Double.class) String csodCountRealMax="csodCountRealMax";
		/** 理论单价 [非空]       **/
		public final static @type(Double.class)  String csodPrice="csodPrice";
		/** and csod_price >= ? */
		public final static @type(Double.class) String csodPriceMin="csodPriceMin";
		/** and csod_price <= ? */
		public final static @type(Double.class) String csodPriceMax="csodPriceMax";
		/** 实际单价 [非空]       **/
		public final static @type(Double.class)  String csodPriceReal="csodPriceReal";
		/** and csod_price_real >= ? */
		public final static @type(Double.class) String csodPriceRealMin="csodPriceRealMin";
		/** and csod_price_real <= ? */
		public final static @type(Double.class) String csodPriceRealMax="csodPriceRealMax";
		/** 理论金额 [非空]       **/
		public final static @type(Double.class)  String csodTotal="csodTotal";
		/** and csod_total >= ? */
		public final static @type(Double.class) String csodTotalMin="csodTotalMin";
		/** and csod_total <= ? */
		public final static @type(Double.class) String csodTotalMax="csodTotalMax";
		/** 折扣 [非空]    0到1,比如0.75表示七五折   **/
		public final static @type(Double.class)  String csodRebate="csodRebate";
		/** and csod_rebate >= ? */
		public final static @type(Double.class) String csodRebateMin="csodRebateMin";
		/** and csod_rebate <= ? */
		public final static @type(Double.class) String csodRebateMax="csodRebateMax";
		/** 实际金额 [非空]    实付金额=实际单价*实际数量   **/
		public final static @type(Double.class)  String csodTotalReal="csodTotalReal";
		/** and csod_total_real >= ? */
		public final static @type(Double.class) String csodTotalRealMin="csodTotalRealMin";
		/** and csod_total_real <= ? */
		public final static @type(Double.class) String csodTotalRealMax="csodTotalRealMax";
		/** 备注        **/
		public final static @type(String.class) @like String csodRemark="csodRemark";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csodAddTime="csodAddTime";
	 	/** and csod_add_time >= ? */
 		public final static @type(Date.class) String csodAddTimeStart="csodAddTimeStart";
 		/** and csod_add_time <= ? */
 		public final static @type(Date.class) String csodAddTimeEnd="csodAddTimeEnd";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csodId="csod_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csodHost="csod_host";
		/** 所属定单 [非空] [CsOrder]      **/
		public final static String csodOrder="csod_order";
		/** 所属产品 [非空] [CsProduct]      **/
		public final static String csodProduct="csod_product";
		/** 所属商品 [非空] [CsGoods]      **/
		public final static String csodGoods="csod_goods";
		/** 所属价格 [非空] [CsPrice]      **/
		public final static String csodPricer="csod_pricer";
		/** 所属网点 [非空] [CsOutlets]     ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
		public final static String csodOutlets="csod_outlets";
		/** 所属车型 [非空] [CsCarModel]      **/
		public final static String csodModel="csod_model";
		/** 所属车辆 [非空] [CsCar]      **/
		public final static String csodCar="csod_car";
		/** 所属商家 [非空] [CsProvid]      **/
		public final static String csodProvid="csod_provid";
		/** 开始时间        **/
		public final static String csodStart="csod_start";
		/** 结束时间        **/
		public final static String csodEnd="csod_end";
		/** 单位 [非空] [CsRule]      **/
		public final static String csodRule="csod_rule";
		/** 理论数量 [非空]       **/
		public final static String csodCount="csod_count";
		/** 实际数量 [非空]       **/
		public final static String csodCountReal="csod_count_real";
		/** 理论单价 [非空]       **/
		public final static String csodPrice="csod_price";
		/** 实际单价 [非空]       **/
		public final static String csodPriceReal="csod_price_real";
		/** 理论金额 [非空]       **/
		public final static String csodTotal="csod_total";
		/** 折扣 [非空]    0到1,比如0.75表示七五折   **/
		public final static String csodRebate="csod_rebate";
		/** 实际金额 [非空]    实付金额=实际单价*实际数量   **/
		public final static String csodTotalReal="csod_total_real";
		/** 备注        **/
		public final static String csodRemark="csod_remark";
		/** 添加时间 [非空]       **/
		public final static String csodAddTime="csod_add_time";
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
				$.Set(name,CsOrderDetail.getCsOrderDetail(params));
			else
				$.Set(name,CsOrderDetail.getCsOrderDetailList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取订单明细数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsOrderDetail) $.GetRequest("CsOrderDetail$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsOrderDetail.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsOrderDetail.getCsOrderDetail(params);
			else
				value = CsOrderDetail.getCsOrderDetailList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsOrderDetail.Get($.add(CsOrderDetail.F.csodId,param));
		else if(!$.empty(param.toString()))
			value = CsOrderDetail.get(Long.valueOf(param.toString()));
		$.SetRequest("CsOrderDetail$"+param.hashCode(), value);
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
	public void mergeSet(CsOrderDetail old){
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