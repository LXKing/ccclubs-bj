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

@namespace("shop/detail")
public @caption("订单明细") @table("cs_shop_order_item") class CsShopOrderItem implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cssoi_id")   @primary  @note("  ") Long cssoiId;// 主键 非空     
	private @caption("所属订单") @column("cssoi_order")    @relate("$cssoiOrder") @RelateClass(CsShopOrder.class)  @note("  ") Long cssoiOrder;// 非空     
 	private CsShopOrder $cssoiOrder;//关联对象[商城订单]
	private @caption("商品信息") @column("cssoi_item")    @relate("$cssoiItem") @RelateClass(CsItem.class)  @note("  ") Long cssoiItem;// 非空     
 	private CsItem $cssoiItem;//关联对象[商品信息]
	private @caption("会员帐号") @column("cssoi_member")    @relate("$cssoiMember") @RelateClass(CsMember.class)  @note("  ") Long cssoiMember;//     
 	private CsMember $cssoiMember;//关联对象[会员帐号]
	private @caption("商品名称") @column("cssoi_title")    @note("  ") String cssoiTitle;// 非空     
	private @caption("商品价格") @column("cssoi_price")    @note("  ") Double cssoiPrice;// 非空     
	private @caption("优惠折扣") @column("cssoi_rebate")    @note("  ") Double cssoiRebate;// 非空     
	private @caption("购买数量") @column("cssoi_count")    @note("  ") Integer cssoiCount;// 非空     
	private @caption("金额小计") @column("cssoi_money")    @note("  ") Double cssoiMoney;// 非空     
	private @caption("实际金额") @column("cssoi_realpay")    @note("  ") Double cssoiRealpay;// 非空     
	private @caption("修改人") @column("cssoi_editor")    @relate("$cssoiEditor") @RelateClass(SrvUser.class)  @note("  ") Long cssoiEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cssoiEditor;//关联对象[用户]
	private @caption("备注") @column("cssoi_remark")    @note("  ") String cssoiRemark;//     
	private @caption("修改时间") @column("cssoi_update_time")    @note("  ") Date cssoiUpdateTime;// 非空     
	private @caption("添加时间") @column("cssoi_add_time")    @note("  ") Date cssoiAddTime;// 非空     
	private @caption("状态") @column("cssoi_status")    @note(" 1:正常 0:无效  ") Short cssoiStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsShopOrderItem(){
	
	}
	
	//主键构造函数
	public CsShopOrderItem(Long id){
		this.cssoiId = id;
	}
	
	/**所有字段构造函数 CsShopOrderItem(cssoiOrder,cssoiItem,cssoiMember,cssoiTitle,cssoiPrice,cssoiRebate,cssoiCount,cssoiMoney,cssoiRealpay,cssoiEditor,cssoiRemark,cssoiUpdateTime,cssoiAddTime,cssoiStatus)
	 CsShopOrderItem(
	 	$.getLong("cssoiOrder")//所属订单 [非空]
	 	,$.getLong("cssoiItem")//商品信息 [非空]
	 	,$.getLong("cssoiMember")//会员帐号
	 	,$.getString("cssoiTitle")//商品名称 [非空]
	 	,$.getDouble("cssoiPrice")//商品价格 [非空]
	 	,$.getDouble("cssoiRebate")//优惠折扣 [非空]
	 	,$.getInteger("cssoiCount")//购买数量 [非空]
	 	,$.getDouble("cssoiMoney")//金额小计 [非空]
	 	,$.getDouble("cssoiRealpay")//实际金额 [非空]
	 	,$.getLong("cssoiEditor")//修改人 [非空]
	 	,$.getString("cssoiRemark")//备注
	 	,$.getDate("cssoiUpdateTime")//修改时间 [非空]
	 	,$.getDate("cssoiAddTime")//添加时间 [非空]
	 	,$.getShort("cssoiStatus")//状态 [非空]
	 )
	**/
	public CsShopOrderItem(Long cssoiOrder,Long cssoiItem,Long cssoiMember,String cssoiTitle,Double cssoiPrice,Double cssoiRebate,Integer cssoiCount,Double cssoiMoney,Double cssoiRealpay,Long cssoiEditor,String cssoiRemark,Date cssoiUpdateTime,Date cssoiAddTime,Short cssoiStatus){
		this.cssoiOrder=cssoiOrder;
		this.cssoiItem=cssoiItem;
		this.cssoiMember=cssoiMember;
		this.cssoiTitle=cssoiTitle;
		this.cssoiPrice=cssoiPrice;
		this.cssoiRebate=cssoiRebate;
		this.cssoiCount=cssoiCount;
		this.cssoiMoney=cssoiMoney;
		this.cssoiRealpay=cssoiRealpay;
		this.cssoiEditor=cssoiEditor;
		this.cssoiRemark=cssoiRemark;
		this.cssoiUpdateTime=cssoiUpdateTime;
		this.cssoiAddTime=cssoiAddTime;
		this.cssoiStatus=cssoiStatus;
	}
	
	//设置非空字段
	public CsShopOrderItem setNotNull(Long cssoiOrder,Long cssoiItem,String cssoiTitle,Double cssoiPrice,Double cssoiRebate,Integer cssoiCount,Double cssoiMoney,Double cssoiRealpay,Long cssoiEditor,Date cssoiUpdateTime,Date cssoiAddTime,Short cssoiStatus){
		this.cssoiOrder=cssoiOrder;
		this.cssoiItem=cssoiItem;
		this.cssoiTitle=cssoiTitle;
		this.cssoiPrice=cssoiPrice;
		this.cssoiRebate=cssoiRebate;
		this.cssoiCount=cssoiCount;
		this.cssoiMoney=cssoiMoney;
		this.cssoiRealpay=cssoiRealpay;
		this.cssoiEditor=cssoiEditor;
		this.cssoiUpdateTime=cssoiUpdateTime;
		this.cssoiAddTime=cssoiAddTime;
		this.cssoiStatus=cssoiStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsShopOrderItem cssoiId(Long cssoiId){
		this.cssoiId = cssoiId;
		this.setSeted(F.cssoiId);
		return this;
	}
	/** 所属订单 [非空] [CsShopOrder]      **/
	public CsShopOrderItem cssoiOrder(Long cssoiOrder){
		this.cssoiOrder = cssoiOrder;
		this.setSeted(F.cssoiOrder);
		return this;
	}
	/** 商品信息 [非空] [CsItem]      **/
	public CsShopOrderItem cssoiItem(Long cssoiItem){
		this.cssoiItem = cssoiItem;
		this.setSeted(F.cssoiItem);
		return this;
	}
	/** 会员帐号  [CsMember]      **/
	public CsShopOrderItem cssoiMember(Long cssoiMember){
		this.cssoiMember = cssoiMember;
		this.setSeted(F.cssoiMember);
		return this;
	}
	/** 商品名称 [非空]       **/
	public CsShopOrderItem cssoiTitle(String cssoiTitle){
		this.cssoiTitle = cssoiTitle;
		this.setSeted(F.cssoiTitle);
		return this;
	}
	/** 商品价格 [非空]       **/
	public CsShopOrderItem cssoiPrice(Double cssoiPrice){
		this.cssoiPrice = cssoiPrice;
		this.setSeted(F.cssoiPrice);
		return this;
	}
	/** 优惠折扣 [非空]       **/
	public CsShopOrderItem cssoiRebate(Double cssoiRebate){
		this.cssoiRebate = cssoiRebate;
		this.setSeted(F.cssoiRebate);
		return this;
	}
	/** 购买数量 [非空]       **/
	public CsShopOrderItem cssoiCount(Integer cssoiCount){
		this.cssoiCount = cssoiCount;
		this.setSeted(F.cssoiCount);
		return this;
	}
	/** 金额小计 [非空]       **/
	public CsShopOrderItem cssoiMoney(Double cssoiMoney){
		this.cssoiMoney = cssoiMoney;
		this.setSeted(F.cssoiMoney);
		return this;
	}
	/** 实际金额 [非空]       **/
	public CsShopOrderItem cssoiRealpay(Double cssoiRealpay){
		this.cssoiRealpay = cssoiRealpay;
		this.setSeted(F.cssoiRealpay);
		return this;
	}
	/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsShopOrderItem cssoiEditor(Long cssoiEditor){
		this.cssoiEditor = cssoiEditor;
		this.setSeted(F.cssoiEditor);
		return this;
	}
	/** 备注        **/
	public CsShopOrderItem cssoiRemark(String cssoiRemark){
		this.cssoiRemark = cssoiRemark;
		this.setSeted(F.cssoiRemark);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsShopOrderItem cssoiUpdateTime(Date cssoiUpdateTime){
		this.cssoiUpdateTime = cssoiUpdateTime;
		this.setSeted(F.cssoiUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsShopOrderItem cssoiAddTime(Date cssoiAddTime){
		this.cssoiAddTime = cssoiAddTime;
		this.setSeted(F.cssoiAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsShopOrderItem cssoiStatus(Short cssoiStatus){
		this.cssoiStatus = cssoiStatus;
		this.setSeted(F.cssoiStatus);
		return this;
	}
	
	
	//克隆对象
	public CsShopOrderItem clone(){
		CsShopOrderItem clone = new CsShopOrderItem();
		clone.cssoiOrder=this.cssoiOrder;
		clone.cssoiItem=this.cssoiItem;
		clone.cssoiTitle=this.cssoiTitle;
		clone.cssoiPrice=this.cssoiPrice;
		clone.cssoiRebate=this.cssoiRebate;
		clone.cssoiCount=this.cssoiCount;
		clone.cssoiMoney=this.cssoiMoney;
		clone.cssoiRealpay=this.cssoiRealpay;
		clone.cssoiEditor=this.cssoiEditor;
		clone.cssoiUpdateTime=this.cssoiUpdateTime;
		clone.cssoiAddTime=this.cssoiAddTime;
		clone.cssoiStatus=this.cssoiStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取订单明细
	 * @param id
	 * @return
	 */
	public static @api CsShopOrderItem get(Long id){		
		return getCsShopOrderItemById(id);
	}
	/**
	 * 获取所有订单明细
	 * @return
	 */
	public static @api List<CsShopOrderItem> list(Map params,Integer size){
		return getCsShopOrderItemList(params,size);
	}
	/**
	 * 获取订单明细分页
	 * @return
	 */
	public static @api Page<CsShopOrderItem> page(int page,int size,Map params){
		return getCsShopOrderItemPage(page, size , params);
	}
	/**
	 * 根据查询条件取订单明细
	 * @param params
	 * @return
	 */
	public static @api CsShopOrderItem Get(Map params){
		return getCsShopOrderItem(params);
	}
	/**
	 * 获取订单明细数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsShopOrderItemCount(params);
	}
	/**
	 * 获取订单明细数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsShopOrderItemEval(eval,params);
	}
	
	/**
	 * 根据ID取订单明细
	 * @param id
	 * @return
	 */
	public static @api CsShopOrderItem getCsShopOrderItemById(Long id){		
		CsShopOrderItem csShopOrderItem = (CsShopOrderItem) $.GetRequest("CsShopOrderItem$"+id);
		if(csShopOrderItem!=null)
			return csShopOrderItem;
		com.ccclubs.service.admin.ICsShopOrderItemService csShopOrderItemService = $.GetSpringBean("csShopOrderItemService");		
		return csShopOrderItemService.getCsShopOrderItemById(id);
	}
	
	
	/**
	 * 根据ID取订单明细的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsShopOrderItem.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsShopOrderItem csShopOrderItem = get(id);
		if(csShopOrderItem!=null){
			String strValue = csShopOrderItem.getCssoiTitle$();
			if(!"CssoiTitle".equals("CssoiTitle"))
				strValue+="("+csShopOrderItem.getCssoiTitle$()+")";
			MemCache.setValue(CsShopOrderItem.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCssoiTitle$();
		if(!"CssoiTitle".equals("CssoiTitle"))
			keyValue+="("+this.getCssoiTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有订单明细
	 * @return
	 */
	public static @api List<CsShopOrderItem> getCsShopOrderItemList(Map params,Integer size){
		com.ccclubs.service.admin.ICsShopOrderItemService csShopOrderItemService = $.GetSpringBean("csShopOrderItemService");
		return csShopOrderItemService.getCsShopOrderItemList(params, size);
	}
	
	/**
	 * 获取订单明细分页
	 * @return
	 */
	public static @api Page<CsShopOrderItem> getCsShopOrderItemPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsShopOrderItemService csShopOrderItemService = $.GetSpringBean("csShopOrderItemService");
		return csShopOrderItemService.getCsShopOrderItemPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取订单明细
	 * @param params
	 * @return
	 */
	public static @api CsShopOrderItem getCsShopOrderItem(Map params){
		com.ccclubs.service.admin.ICsShopOrderItemService csShopOrderItemService = $.GetSpringBean("csShopOrderItemService");
		return csShopOrderItemService.getCsShopOrderItem(params);
	}
	
	/**
	 * 获取订单明细数
	 * @return
	 */
	public static @api Long getCsShopOrderItemCount(Map params){
		com.ccclubs.service.admin.ICsShopOrderItemService csShopOrderItemService = $.GetSpringBean("csShopOrderItemService");
		return csShopOrderItemService.getCsShopOrderItemCount(params);
	}
		
		
	/**
	 * 获取订单明细自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsShopOrderItemEval(String eval,Map params){
		com.ccclubs.service.admin.ICsShopOrderItemService csShopOrderItemService = $.GetSpringBean("csShopOrderItemService");
		return csShopOrderItemService.getCsShopOrderItemEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsShopOrderItem(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsShopOrderItemService csShopOrderItemService = $.GetSpringBean("csShopOrderItemService");
		csShopOrderItemService.updateCsShopOrderItemByConfirm(set, where);
	}
	
	
	/**
	 * 保存订单明细对象
	 * @param params
	 * @return
	 */
	public CsShopOrderItem save(){
		com.ccclubs.service.admin.ICsShopOrderItemService csShopOrderItemService = $.GetSpringBean("csShopOrderItemService");
		if(this.getCssoiId()!=null)
			csShopOrderItemService.updateCsShopOrderItem(this);
		else
			return csShopOrderItemService.saveCsShopOrderItem(this);
		return this;
	}
	
	
	/**
	 * 更新订单明细对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsShopOrderItemService csShopOrderItemService = $.GetSpringBean("csShopOrderItemService");
		csShopOrderItemService.updateCsShopOrderItem$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsShopOrderItemService csShopOrderItemService = $.GetSpringBean("csShopOrderItemService");
		if($.equals($.config("logic_delete"),"true"))
			csShopOrderItemService.removeCsShopOrderItemById(this.getCssoiId());
		else
			csShopOrderItemService.deleteCsShopOrderItemById(this.getCssoiId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsShopOrderItemService csShopOrderItemService = $.GetSpringBean("csShopOrderItemService");
		return csShopOrderItemService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCssoiId(){
		return this.cssoiId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCssoiId$(){
		String strValue="";
		 strValue=$.str(this.getCssoiId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCssoiId(Long cssoiId){
		this.cssoiId = cssoiId;
		this.setSeted(F.cssoiId);
	}
	/*******************************所属订单**********************************/	
	/**
	* 所属订单 [非空] [CsShopOrder]     
	**/
	public Long getCssoiOrder(){
		return this.cssoiOrder;
	}
	/**
	* 获取所属订单格式化(toString)
	**/
	public String getCssoiOrder$(){
		String strValue="";
		if(this.getCssoiOrder()!=null){
				strValue+=$.str(CsShopOrder.getKeyValue(this.getCssoiOrder()));
		}			
	 	 return strValue;
	}
	/**
	* 所属订单 [非空] [CsShopOrder]     
	**/
	public void setCssoiOrder(Long cssoiOrder){
		this.cssoiOrder = cssoiOrder;
		this.setSeted(F.cssoiOrder);
	}
	/**
	* 获取关联对象[商城订单]
	**/	 			
 	public CsShopOrder get$cssoiOrder(){
 		com.ccclubs.model.CsShopOrder csShopOrder = (com.ccclubs.model.CsShopOrder) $.GetRequest("CsShopOrder$"+this.getCssoiOrder());
 		if(csShopOrder!=null)
			return csShopOrder;
		if(this.getCssoiOrder()!=null){
 			csShopOrder = CsShopOrder.get(this.getCssoiOrder());
 		}
 		$.SetRequest("CsShopOrder$"+this.getCssoiOrder(), csShopOrder);
	 	return csShopOrder;
	}
	/*******************************商品信息**********************************/	
	/**
	* 商品信息 [非空] [CsItem]     
	**/
	public Long getCssoiItem(){
		return this.cssoiItem;
	}
	/**
	* 获取商品信息格式化(toString)
	**/
	public String getCssoiItem$(){
		String strValue="";
		if(this.getCssoiItem()!=null){
				strValue+=$.str(CsItem.getKeyValue(this.getCssoiItem()));
		}			
	 	 return strValue;
	}
	/**
	* 商品信息 [非空] [CsItem]     
	**/
	public void setCssoiItem(Long cssoiItem){
		this.cssoiItem = cssoiItem;
		this.setSeted(F.cssoiItem);
	}
	/**
	* 获取关联对象[商品信息]
	**/	 			
 	public CsItem get$cssoiItem(){
 		com.ccclubs.model.CsItem csItem = (com.ccclubs.model.CsItem) $.GetRequest("CsItem$"+this.getCssoiItem());
 		if(csItem!=null)
			return csItem;
		if(this.getCssoiItem()!=null){
 			csItem = CsItem.get(this.getCssoiItem());
 		}
 		$.SetRequest("CsItem$"+this.getCssoiItem(), csItem);
	 	return csItem;
	}
	/*******************************会员帐号**********************************/	
	/**
	* 会员帐号  [CsMember]     
	**/
	public Long getCssoiMember(){
		return this.cssoiMember;
	}
	/**
	* 获取会员帐号格式化(toString)
	**/
	public String getCssoiMember$(){
		String strValue="";
		if(this.getCssoiMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCssoiMember()));
		}			
	 	 return strValue;
	}
	/**
	* 会员帐号  [CsMember]     
	**/
	public void setCssoiMember(Long cssoiMember){
		this.cssoiMember = cssoiMember;
		this.setSeted(F.cssoiMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$cssoiMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCssoiMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCssoiMember()!=null){
 			csMember = CsMember.get(this.getCssoiMember());
 		}
 		$.SetRequest("CsMember$"+this.getCssoiMember(), csMember);
	 	return csMember;
	}
	/*******************************商品名称**********************************/	
	/**
	* 商品名称 [非空]      
	**/
	public String getCssoiTitle(){
		return this.cssoiTitle;
	}
	/**
	* 获取商品名称格式化(toString)
	**/
	public String getCssoiTitle$(){
		String strValue="";
		 strValue=$.str(this.getCssoiTitle());
	 	 return strValue;
	}
	/**
	* 商品名称 [非空]      
	**/
	public void setCssoiTitle(String cssoiTitle){
		this.cssoiTitle = cssoiTitle;
		this.setSeted(F.cssoiTitle);
	}
	/*******************************商品价格**********************************/	
	/**
	* 商品价格 [非空]      
	**/
	public Double getCssoiPrice(){
		return this.cssoiPrice;
	}
	/**
	* 获取商品价格格式化(toString)
	**/
	public String getCssoiPrice$(){
		String strValue="";
		 strValue=$.str(this.getCssoiPrice());	
	 	 return strValue;
	}
	/**
	* 商品价格 [非空]      
	**/
	public void setCssoiPrice(Double cssoiPrice){
		this.cssoiPrice = cssoiPrice;
		this.setSeted(F.cssoiPrice);
	}
	/*******************************优惠折扣**********************************/	
	/**
	* 优惠折扣 [非空]      
	**/
	public Double getCssoiRebate(){
		return this.cssoiRebate;
	}
	/**
	* 获取优惠折扣格式化(toString)
	**/
	public String getCssoiRebate$(){
		String strValue="";
		 strValue=$.str(this.getCssoiRebate());	
	 	 return strValue;
	}
	/**
	* 优惠折扣 [非空]      
	**/
	public void setCssoiRebate(Double cssoiRebate){
		this.cssoiRebate = cssoiRebate;
		this.setSeted(F.cssoiRebate);
	}
	/*******************************购买数量**********************************/	
	/**
	* 购买数量 [非空]      
	**/
	public Integer getCssoiCount(){
		return this.cssoiCount;
	}
	/**
	* 获取购买数量格式化(toString)
	**/
	public String getCssoiCount$(){
		String strValue="";
		 strValue=$.str(this.getCssoiCount());
	 	 return strValue;
	}
	/**
	* 购买数量 [非空]      
	**/
	public void setCssoiCount(Integer cssoiCount){
		this.cssoiCount = cssoiCount;
		this.setSeted(F.cssoiCount);
	}
	/*******************************金额小计**********************************/	
	/**
	* 金额小计 [非空]      
	**/
	public Double getCssoiMoney(){
		return this.cssoiMoney;
	}
	/**
	* 获取金额小计格式化(toString)
	**/
	public String getCssoiMoney$(){
		String strValue="";
		 strValue=$.str(this.getCssoiMoney());	
	 	 return strValue;
	}
	/**
	* 金额小计 [非空]      
	**/
	public void setCssoiMoney(Double cssoiMoney){
		this.cssoiMoney = cssoiMoney;
		this.setSeted(F.cssoiMoney);
	}
	/*******************************实际金额**********************************/	
	/**
	* 实际金额 [非空]      
	**/
	public Double getCssoiRealpay(){
		return this.cssoiRealpay;
	}
	/**
	* 获取实际金额格式化(toString)
	**/
	public String getCssoiRealpay$(){
		String strValue="";
		 strValue=$.str(this.getCssoiRealpay());	
	 	 return strValue;
	}
	/**
	* 实际金额 [非空]      
	**/
	public void setCssoiRealpay(Double cssoiRealpay){
		this.cssoiRealpay = cssoiRealpay;
		this.setSeted(F.cssoiRealpay);
	}
	/*******************************修改人**********************************/	
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCssoiEditor(){
		return this.cssoiEditor;
	}
	/**
	* 获取修改人格式化(toString)
	**/
	public String getCssoiEditor$(){
		String strValue="";
		if(this.getCssoiEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCssoiEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCssoiEditor(Long cssoiEditor){
		this.cssoiEditor = cssoiEditor;
		this.setSeted(F.cssoiEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cssoiEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCssoiEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCssoiEditor()!=null){
 			srvUser = SrvUser.get(this.getCssoiEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCssoiEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getCssoiRemark(){
		return this.cssoiRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCssoiRemark$(){
		String strValue="";
		 strValue=$.str(this.getCssoiRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setCssoiRemark(String cssoiRemark){
		this.cssoiRemark = cssoiRemark;
		this.setSeted(F.cssoiRemark);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCssoiUpdateTime(){
		return this.cssoiUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCssoiUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssoiUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCssoiUpdateTime(Date cssoiUpdateTime){
		this.cssoiUpdateTime = cssoiUpdateTime;
		this.setSeted(F.cssoiUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCssoiAddTime(){
		return this.cssoiAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCssoiAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssoiAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCssoiAddTime(Date cssoiAddTime){
		this.cssoiAddTime = cssoiAddTime;
		this.setSeted(F.cssoiAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCssoiStatus(){
		return this.cssoiStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCssoiStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCssoiStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCssoiStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCssoiStatus(Short cssoiStatus){
		this.cssoiStatus = cssoiStatus;
		this.setSeted(F.cssoiStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsShopOrderItem.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsShopOrderItem.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsShopOrderItem.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsShopOrderItem.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsShopOrderItem.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsShopOrderItem.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsShopOrderItem.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsShopOrderItem.this);
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
		public M cssoiId(Object cssoiId){this.put("cssoiId", cssoiId);return this;};
	 	/** and cssoi_id is null */
 		public M cssoiIdNull(){if(this.get("cssoiIdNot")==null)this.put("cssoiIdNot", "");this.put("cssoiId", null);return this;};
 		/** not .... */
 		public M cssoiIdNot(){this.put("cssoiIdNot", "not");return this;};
		/** 所属订单 [非空] [CsShopOrder]      **/
		public M cssoiOrder(Object cssoiOrder){this.put("cssoiOrder", cssoiOrder);return this;};
	 	/** and cssoi_order is null */
 		public M cssoiOrderNull(){if(this.get("cssoiOrderNot")==null)this.put("cssoiOrderNot", "");this.put("cssoiOrder", null);return this;};
 		/** not .... */
 		public M cssoiOrderNot(){this.put("cssoiOrderNot", "not");return this;};
		public M cssoiOrder$on(CsShopOrder.M value){
			this.put("CsShopOrder", value);
			this.put("cssoiOrder$on", value);
			return this;
		};	
		/** 商品信息 [非空] [CsItem]      **/
		public M cssoiItem(Object cssoiItem){this.put("cssoiItem", cssoiItem);return this;};
	 	/** and cssoi_item is null */
 		public M cssoiItemNull(){if(this.get("cssoiItemNot")==null)this.put("cssoiItemNot", "");this.put("cssoiItem", null);return this;};
 		/** not .... */
 		public M cssoiItemNot(){this.put("cssoiItemNot", "not");return this;};
		public M cssoiItem$on(CsItem.M value){
			this.put("CsItem", value);
			this.put("cssoiItem$on", value);
			return this;
		};	
		/** 会员帐号  [CsMember]      **/
		public M cssoiMember(Object cssoiMember){this.put("cssoiMember", cssoiMember);return this;};
	 	/** and cssoi_member is null */
 		public M cssoiMemberNull(){if(this.get("cssoiMemberNot")==null)this.put("cssoiMemberNot", "");this.put("cssoiMember", null);return this;};
 		/** not .... */
 		public M cssoiMemberNot(){this.put("cssoiMemberNot", "not");return this;};
		public M cssoiMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("cssoiMember$on", value);
			return this;
		};	
		/** 商品名称 [非空]       **/
		public M cssoiTitle(Object cssoiTitle){this.put("cssoiTitle", cssoiTitle);return this;};
	 	/** and cssoi_title is null */
 		public M cssoiTitleNull(){if(this.get("cssoiTitleNot")==null)this.put("cssoiTitleNot", "");this.put("cssoiTitle", null);return this;};
 		/** not .... */
 		public M cssoiTitleNot(){this.put("cssoiTitleNot", "not");return this;};
		/** 商品价格 [非空]       **/
		public M cssoiPrice(Object cssoiPrice){this.put("cssoiPrice", cssoiPrice);return this;};
	 	/** and cssoi_price is null */
 		public M cssoiPriceNull(){if(this.get("cssoiPriceNot")==null)this.put("cssoiPriceNot", "");this.put("cssoiPrice", null);return this;};
 		/** not .... */
 		public M cssoiPriceNot(){this.put("cssoiPriceNot", "not");return this;};
		/** and cssoi_price >= ? */
		public M cssoiPriceMin(Object min){this.put("cssoiPriceMin", min);return this;};
		/** and cssoi_price <= ? */
		public M cssoiPriceMax(Object max){this.put("cssoiPriceMax", max);return this;};
		/** 优惠折扣 [非空]       **/
		public M cssoiRebate(Object cssoiRebate){this.put("cssoiRebate", cssoiRebate);return this;};
	 	/** and cssoi_rebate is null */
 		public M cssoiRebateNull(){if(this.get("cssoiRebateNot")==null)this.put("cssoiRebateNot", "");this.put("cssoiRebate", null);return this;};
 		/** not .... */
 		public M cssoiRebateNot(){this.put("cssoiRebateNot", "not");return this;};
		/** and cssoi_rebate >= ? */
		public M cssoiRebateMin(Object min){this.put("cssoiRebateMin", min);return this;};
		/** and cssoi_rebate <= ? */
		public M cssoiRebateMax(Object max){this.put("cssoiRebateMax", max);return this;};
		/** 购买数量 [非空]       **/
		public M cssoiCount(Object cssoiCount){this.put("cssoiCount", cssoiCount);return this;};
	 	/** and cssoi_count is null */
 		public M cssoiCountNull(){if(this.get("cssoiCountNot")==null)this.put("cssoiCountNot", "");this.put("cssoiCount", null);return this;};
 		/** not .... */
 		public M cssoiCountNot(){this.put("cssoiCountNot", "not");return this;};
		/** and cssoi_count >= ? */
		public M cssoiCountMin(Object min){this.put("cssoiCountMin", min);return this;};
		/** and cssoi_count <= ? */
		public M cssoiCountMax(Object max){this.put("cssoiCountMax", max);return this;};
		/** 金额小计 [非空]       **/
		public M cssoiMoney(Object cssoiMoney){this.put("cssoiMoney", cssoiMoney);return this;};
	 	/** and cssoi_money is null */
 		public M cssoiMoneyNull(){if(this.get("cssoiMoneyNot")==null)this.put("cssoiMoneyNot", "");this.put("cssoiMoney", null);return this;};
 		/** not .... */
 		public M cssoiMoneyNot(){this.put("cssoiMoneyNot", "not");return this;};
		/** and cssoi_money >= ? */
		public M cssoiMoneyMin(Object min){this.put("cssoiMoneyMin", min);return this;};
		/** and cssoi_money <= ? */
		public M cssoiMoneyMax(Object max){this.put("cssoiMoneyMax", max);return this;};
		/** 实际金额 [非空]       **/
		public M cssoiRealpay(Object cssoiRealpay){this.put("cssoiRealpay", cssoiRealpay);return this;};
	 	/** and cssoi_realpay is null */
 		public M cssoiRealpayNull(){if(this.get("cssoiRealpayNot")==null)this.put("cssoiRealpayNot", "");this.put("cssoiRealpay", null);return this;};
 		/** not .... */
 		public M cssoiRealpayNot(){this.put("cssoiRealpayNot", "not");return this;};
		/** and cssoi_realpay >= ? */
		public M cssoiRealpayMin(Object min){this.put("cssoiRealpayMin", min);return this;};
		/** and cssoi_realpay <= ? */
		public M cssoiRealpayMax(Object max){this.put("cssoiRealpayMax", max);return this;};
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cssoiEditor(Object cssoiEditor){this.put("cssoiEditor", cssoiEditor);return this;};
	 	/** and cssoi_editor is null */
 		public M cssoiEditorNull(){if(this.get("cssoiEditorNot")==null)this.put("cssoiEditorNot", "");this.put("cssoiEditor", null);return this;};
 		/** not .... */
 		public M cssoiEditorNot(){this.put("cssoiEditorNot", "not");return this;};
		/** 备注        **/
		public M cssoiRemark(Object cssoiRemark){this.put("cssoiRemark", cssoiRemark);return this;};
	 	/** and cssoi_remark is null */
 		public M cssoiRemarkNull(){if(this.get("cssoiRemarkNot")==null)this.put("cssoiRemarkNot", "");this.put("cssoiRemark", null);return this;};
 		/** not .... */
 		public M cssoiRemarkNot(){this.put("cssoiRemarkNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M cssoiUpdateTime(Object cssoiUpdateTime){this.put("cssoiUpdateTime", cssoiUpdateTime);return this;};
	 	/** and cssoi_update_time is null */
 		public M cssoiUpdateTimeNull(){if(this.get("cssoiUpdateTimeNot")==null)this.put("cssoiUpdateTimeNot", "");this.put("cssoiUpdateTime", null);return this;};
 		/** not .... */
 		public M cssoiUpdateTimeNot(){this.put("cssoiUpdateTimeNot", "not");return this;};
 		/** and cssoi_update_time >= ? */
 		public M cssoiUpdateTimeStart(Object start){this.put("cssoiUpdateTimeStart", start);return this;};			
 		/** and cssoi_update_time <= ? */
 		public M cssoiUpdateTimeEnd(Object end){this.put("cssoiUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cssoiAddTime(Object cssoiAddTime){this.put("cssoiAddTime", cssoiAddTime);return this;};
	 	/** and cssoi_add_time is null */
 		public M cssoiAddTimeNull(){if(this.get("cssoiAddTimeNot")==null)this.put("cssoiAddTimeNot", "");this.put("cssoiAddTime", null);return this;};
 		/** not .... */
 		public M cssoiAddTimeNot(){this.put("cssoiAddTimeNot", "not");return this;};
 		/** and cssoi_add_time >= ? */
 		public M cssoiAddTimeStart(Object start){this.put("cssoiAddTimeStart", start);return this;};			
 		/** and cssoi_add_time <= ? */
 		public M cssoiAddTimeEnd(Object end){this.put("cssoiAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M cssoiStatus(Object cssoiStatus){this.put("cssoiStatus", cssoiStatus);return this;};
	 	/** and cssoi_status is null */
 		public M cssoiStatusNull(){if(this.get("cssoiStatusNot")==null)this.put("cssoiStatusNot", "");this.put("cssoiStatus", null);return this;};
 		/** not .... */
 		public M cssoiStatusNot(){this.put("cssoiStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有订单明细 **/
		public @api List<CsShopOrderItem> list(Integer size){
			return getCsShopOrderItemList(this,size);
		}
		/** 获取订单明细分页 **/
		public @api Page<CsShopOrderItem> page(int page,int size){
			return getCsShopOrderItemPage(page, size , this);
		}
		/** 根据查询条件取订单明细 **/
		public @api CsShopOrderItem get(){
			return getCsShopOrderItem(this);
		}
		/** 获取订单明细数 **/
		public @api Long count(){
			return getCsShopOrderItemCount(this);
		}
		/** 获取订单明细表达式 **/
		public @api <T> T eval(String strEval){
			return getCsShopOrderItemEval(strEval,this);
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
			updateCsShopOrderItem(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cssoiId="cssoiId";
		/** 所属订单 [非空] [CsShopOrder]      **/
		public final static @type(Long.class)  String cssoiOrder="cssoiOrder";
		/** 商品信息 [非空] [CsItem]      **/
		public final static @type(Long.class)  String cssoiItem="cssoiItem";
		/** 会员帐号  [CsMember]      **/
		public final static @type(Long.class)  String cssoiMember="cssoiMember";
		/** 商品名称 [非空]       **/
		public final static @type(String.class) @like String cssoiTitle="cssoiTitle";
		/** 商品价格 [非空]       **/
		public final static @type(Double.class)  String cssoiPrice="cssoiPrice";
		/** and cssoi_price >= ? */
		public final static @type(Double.class) String cssoiPriceMin="cssoiPriceMin";
		/** and cssoi_price <= ? */
		public final static @type(Double.class) String cssoiPriceMax="cssoiPriceMax";
		/** 优惠折扣 [非空]       **/
		public final static @type(Double.class)  String cssoiRebate="cssoiRebate";
		/** and cssoi_rebate >= ? */
		public final static @type(Double.class) String cssoiRebateMin="cssoiRebateMin";
		/** and cssoi_rebate <= ? */
		public final static @type(Double.class) String cssoiRebateMax="cssoiRebateMax";
		/** 购买数量 [非空]       **/
		public final static @type(Integer.class)  String cssoiCount="cssoiCount";
		/** and cssoi_count >= ? */
		public final static @type(Integer.class) String cssoiCountMin="cssoiCountMin";
		/** and cssoi_count <= ? */
		public final static @type(Integer.class) String cssoiCountMax="cssoiCountMax";
		/** 金额小计 [非空]       **/
		public final static @type(Double.class)  String cssoiMoney="cssoiMoney";
		/** and cssoi_money >= ? */
		public final static @type(Double.class) String cssoiMoneyMin="cssoiMoneyMin";
		/** and cssoi_money <= ? */
		public final static @type(Double.class) String cssoiMoneyMax="cssoiMoneyMax";
		/** 实际金额 [非空]       **/
		public final static @type(Double.class)  String cssoiRealpay="cssoiRealpay";
		/** and cssoi_realpay >= ? */
		public final static @type(Double.class) String cssoiRealpayMin="cssoiRealpayMin";
		/** and cssoi_realpay <= ? */
		public final static @type(Double.class) String cssoiRealpayMax="cssoiRealpayMax";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String cssoiEditor="cssoiEditor";
		/** 备注        **/
		public final static @type(String.class) @like String cssoiRemark="cssoiRemark";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cssoiUpdateTime="cssoiUpdateTime";
	 	/** and cssoi_update_time >= ? */
 		public final static @type(Date.class) String cssoiUpdateTimeStart="cssoiUpdateTimeStart";
 		/** and cssoi_update_time <= ? */
 		public final static @type(Date.class) String cssoiUpdateTimeEnd="cssoiUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cssoiAddTime="cssoiAddTime";
	 	/** and cssoi_add_time >= ? */
 		public final static @type(Date.class) String cssoiAddTimeStart="cssoiAddTimeStart";
 		/** and cssoi_add_time <= ? */
 		public final static @type(Date.class) String cssoiAddTimeEnd="cssoiAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String cssoiStatus="cssoiStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cssoiId="cssoi_id";
		/** 所属订单 [非空] [CsShopOrder]      **/
		public final static String cssoiOrder="cssoi_order";
		/** 商品信息 [非空] [CsItem]      **/
		public final static String cssoiItem="cssoi_item";
		/** 会员帐号  [CsMember]      **/
		public final static String cssoiMember="cssoi_member";
		/** 商品名称 [非空]       **/
		public final static String cssoiTitle="cssoi_title";
		/** 商品价格 [非空]       **/
		public final static String cssoiPrice="cssoi_price";
		/** 优惠折扣 [非空]       **/
		public final static String cssoiRebate="cssoi_rebate";
		/** 购买数量 [非空]       **/
		public final static String cssoiCount="cssoi_count";
		/** 金额小计 [非空]       **/
		public final static String cssoiMoney="cssoi_money";
		/** 实际金额 [非空]       **/
		public final static String cssoiRealpay="cssoi_realpay";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cssoiEditor="cssoi_editor";
		/** 备注        **/
		public final static String cssoiRemark="cssoi_remark";
		/** 修改时间 [非空]       **/
		public final static String cssoiUpdateTime="cssoi_update_time";
		/** 添加时间 [非空]       **/
		public final static String cssoiAddTime="cssoi_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String cssoiStatus="cssoi_status";
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
				$.Set(name,CsShopOrderItem.getCsShopOrderItem(params));
			else
				$.Set(name,CsShopOrderItem.getCsShopOrderItemList(params, size));
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
		Object value = (com.ccclubs.model.CsShopOrderItem) $.GetRequest("CsShopOrderItem$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsShopOrderItem.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsShopOrderItem.getCsShopOrderItem(params);
			else
				value = CsShopOrderItem.getCsShopOrderItemList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsShopOrderItem.Get($.add(CsShopOrderItem.F.cssoiId,param));
		else if(!$.empty(param.toString()))
			value = CsShopOrderItem.get(Long.valueOf(param.toString()));
		$.SetRequest("CsShopOrderItem$"+param.hashCode(), value);
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
	public void mergeSet(CsShopOrderItem old){
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