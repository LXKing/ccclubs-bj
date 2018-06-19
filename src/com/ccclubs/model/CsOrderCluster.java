package com.ccclubs.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.ccclubs.model.CsSpecialCar.M;
import com.lazy3q.web.helper.$;

import com.lazy3q.web.tags.BodyTag;

import com.lazy3q.web.util.Page;
import com.lazy3q.lang.*;
import com.lazy3q.util.Function;
import com.ccclubs.dao.impl.MemCache;

@namespace("service/order")
public @caption("订单簇") @table("cs_order_cluster") class CsOrderCluster implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csoc_id")   @primary   @note("  ") Long csocId;
	private @caption("城市") @column("csoc_host")    @relate("$csocHost") @RelateClass(SrvHost.class)  @note("  ") Long csocHost;
	private SrvHost $csocHost;//关联对象[城市]
	private @caption("子订单集") @column("csoc_sub_orders")      @note("  ") String csocSubOrders;
	private @caption("支付会员") @column("csoc_pay_member")    @relate("$csocPayMember") @RelateClass(CsMember.class)  @note("  ") Long csocPayMember;
	private CsMember $csocPayMember;//关联对象[支付会员]
	private @caption("手机号码") @column("csoc_mobile")      @note("  ") String csocMobile;
	private @caption("订单总价") @column("csoc_price")      @note("  ") Double csocPrice;
	private @caption("押金/保证金") @column("csoc_margin_need")      @note("  ") Double csocMarginNeed;
	private @caption("订单总时长") @column("csoc_total_duration")      @note("  ") Double csocTotalDuration;
	private @caption("保证金支付") @column("csoc_pay_money")      @note("  ") Double csocPayMoney;
	private @caption("现金券支付") @column("csoc_pay_coupon")      @note("  ") Double csocPayCoupon;
	private @caption("红包支付") @column("csoc_pay_coin")      @note("  ") Double csocPayCoin;
	private @caption("应付金额") @column("csoc_pay_need")      @note("  ") Double csocPayNeed;
	private @caption("实付金额") @column("csoc_pay_real")      @note("  ") Double csocPayReal;
	private @caption("租金费用") @column("csoc_pay_rent")      @note("  ") Double csocPayRent;
	private @caption("免责费用") @column("csoc_pay_insure")      @note("  ") Double csocPayInsure;
	private @caption("超时费用") @column("csoc_pay_timeout")      @note("  ") Double csocPayTimeout;
	private @caption("支付明细") @column("csoc_pay_details")      @note("  ") String csocPayDetails;
	private @caption("外部类型") @column("csoc_out_type")      @note(" 1:套餐  ") Short csocOutType;
	private @caption("外部ID") @column("csoc_out_id")      @note("  ") Long csocOutId;
	private @caption("订单开始时间") @column("csoc_start_time")      @note("  ") Date csocStartTime;
	private @caption("订单结束时间") @column("csoc_finish_time")      @note("  ") Date csocFinishTime;
	private @caption("数据状态") @column("csoc_status")      @note(" 0:已预定 1:使用中  3:已取消 4:已完成  ") Short csocStatus;
	private @caption("创建时间") @column("csoc_add_time")      @note("  ") Date csocAddTime;
	private @caption("修改时间") @column("csoc_update_time")      @note("  ") Date csocUpdateTime;
	
	//默认构造函数
	public CsOrderCluster(){
	
	}
	
	//主键构造函数
	public CsOrderCluster(Long id){
		this.csocId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsOrderCluster(Long csocHost,String csocSubOrders,Long csocPayMember,String csocMobile,Double csocPrice,Double csocMarginNeed,Double csocTotalDuration,Double csocPayMoney,Double csocPayCoupon,Double csocPayCoin,Double csocPayNeed,Double csocPayReal,Double csocPayRent,Double csocPayInsure,Double csocPayTimeout,String csocPayDetails,Short csocOutType,Long csocOutId,Date csocStartTime,Date csocFinishTime,Short csocStatus,Date csocAddTime,Date csocUpdateTime){
		this.csocHost=csocHost;
		this.csocSubOrders=csocSubOrders;
		this.csocPayMember=csocPayMember;
		this.csocMobile=csocMobile;
		this.csocPrice=csocPrice;
		this.csocMarginNeed=csocMarginNeed;
		this.csocTotalDuration=csocTotalDuration;
		this.csocPayMoney=csocPayMoney;
		this.csocPayCoupon=csocPayCoupon;
		this.csocPayCoin=csocPayCoin;
		this.csocPayNeed=csocPayNeed;
		this.csocPayReal=csocPayReal;
		this.csocPayRent=csocPayRent;
		this.csocPayInsure=csocPayInsure;
		this.csocPayTimeout=csocPayTimeout;
		this.csocPayDetails=csocPayDetails;
		this.csocOutType=csocOutType;
		this.csocOutId=csocOutId;
		this.csocStartTime=csocStartTime;
		this.csocFinishTime=csocFinishTime;
		this.csocStatus=csocStatus;
		this.csocAddTime=csocAddTime;
		this.csocUpdateTime=csocUpdateTime;
	}
	
	//设置非空字段
	public CsOrderCluster setNotNull(Long csocHost,String csocSubOrders,Long csocPayMember,String csocMobile,Double csocPrice,Double csocMarginNeed,Double csocTotalDuration,Double csocPayMoney,Double csocPayCoupon,Double csocPayCoin,Double csocPayNeed,Double csocPayReal,Double csocPayRent,Double csocPayInsure,Double csocPayTimeout,String csocPayDetails,Date csocStartTime,Date csocFinishTime,Short csocStatus){
		this.csocHost=csocHost;
		this.csocSubOrders=csocSubOrders;
		this.csocPayMember=csocPayMember;
		this.csocMobile=csocMobile;
		this.csocPrice=csocPrice;
		this.csocMarginNeed=csocMarginNeed;
		this.csocTotalDuration=csocTotalDuration;
		this.csocPayMoney=csocPayMoney;
		this.csocPayCoupon=csocPayCoupon;
		this.csocPayCoin=csocPayCoin;
		this.csocPayNeed=csocPayNeed;
		this.csocPayReal=csocPayReal;
		this.csocPayRent=csocPayRent;
		this.csocPayInsure=csocPayInsure;
		this.csocPayTimeout=csocPayTimeout;
		this.csocPayDetails=csocPayDetails;
		this.csocStartTime=csocStartTime;
		this.csocFinishTime=csocFinishTime;
		this.csocStatus=csocStatus;
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsOrderCluster csocId(Long csocId){
		this.csocId = csocId;
		this.setSeted(F.csocId);
		return this;
	}
	/** 城市 [可空]       **/
	public CsOrderCluster csocHost(Long csocHost){
		this.csocHost = csocHost;
		this.setSeted(F.csocHost);
		return this;
	}
	/** 子订单集 [可空]       **/
	public CsOrderCluster csocSubOrders(String csocSubOrders){
		this.csocSubOrders = csocSubOrders;
		this.setSeted(F.csocSubOrders);
		return this;
	}
	/** 支付会员 [可空]       **/
	public CsOrderCluster csocPayMember(Long csocPayMember){
		this.csocPayMember = csocPayMember;
		this.setSeted(F.csocPayMember);
		return this;
	}
	/** 手机号码 [可空]       **/
	public CsOrderCluster csocMobile(String csocMobile){
		this.csocMobile = csocMobile;
		this.setSeted(F.csocMobile);
		return this;
	}
	/** 订单总价 [可空]       **/
	public CsOrderCluster csocPrice(Double csocPrice){
		this.csocPrice = csocPrice;
		this.setSeted(F.csocPrice);
		return this;
	}
	/** 押金/保证金 [可空]       **/
	public CsOrderCluster csocMarginNeed(Double csocMarginNeed){
		this.csocMarginNeed = csocMarginNeed;
		this.setSeted(F.csocMarginNeed);
		return this;
	}
	/** 订单总时长 [可空]       **/
	public CsOrderCluster csocTotalDuration(Double csocTotalDuration){
		this.csocTotalDuration = csocTotalDuration;
		this.setSeted(F.csocTotalDuration);
		return this;
	}
	/** 保证金支付 [可空]       **/
	public CsOrderCluster csocPayMoney(Double csocPayMoney){
		this.csocPayMoney = csocPayMoney;
		this.setSeted(F.csocPayMoney);
		return this;
	}
	/** 现金券支付 [可空]       **/
	public CsOrderCluster csocPayCoupon(Double csocPayCoupon){
		this.csocPayCoupon = csocPayCoupon;
		this.setSeted(F.csocPayCoupon);
		return this;
	}
	/** 红包支付 [可空]       **/
	public CsOrderCluster csocPayCoin(Double csocPayCoin){
		this.csocPayCoin = csocPayCoin;
		this.setSeted(F.csocPayCoin);
		return this;
	}
	/** 应付金额 [可空]       **/
	public CsOrderCluster csocPayNeed(Double csocPayNeed){
		this.csocPayNeed = csocPayNeed;
		this.setSeted(F.csocPayNeed);
		return this;
	}
	/** 实付金额 [可空]       **/
	public CsOrderCluster csocPayReal(Double csocPayReal){
		this.csocPayReal = csocPayReal;
		this.setSeted(F.csocPayReal);
		return this;
	}
	/** 租金费用 [可空]       **/
	public CsOrderCluster csocPayRent(Double csocPayRent){
		this.csocPayRent = csocPayRent;
		this.setSeted(F.csocPayRent);
		return this;
	}
	/** 免责费用 [可空]       **/
	public CsOrderCluster csocPayInsure(Double csocPayInsure){
		this.csocPayInsure = csocPayInsure;
		this.setSeted(F.csocPayInsure);
		return this;
	}
	/** 超时费用 [可空]       **/
	public CsOrderCluster csocPayTimeout(Double csocPayTimeout){
		this.csocPayTimeout = csocPayTimeout;
		this.setSeted(F.csocPayTimeout);
		return this;
	}
	/** 支付明细 [可空]       **/
	public CsOrderCluster csocPayDetails(String csocPayDetails){
		this.csocPayDetails = csocPayDetails;
		this.setSeted(F.csocPayDetails);
		return this;
	}
	/** 外部类型 [非空]       **/
	public CsOrderCluster csocOutType(Short csocOutType){
		this.csocOutType = csocOutType;
		this.setSeted(F.csocOutType);
		return this;
	}
	/** 外部ID [非空]       **/
	public CsOrderCluster csocOutId(Long csocOutId){
		this.csocOutId = csocOutId;
		this.setSeted(F.csocOutId);
		return this;
	}
	/** 订单开始时间 [可空]       **/
	public CsOrderCluster csocStartTime(Date csocStartTime){
		this.csocStartTime = csocStartTime;
		this.setSeted(F.csocStartTime);
		return this;
	}
	/** 订单结束时间 [可空]       **/
	public CsOrderCluster csocFinishTime(Date csocFinishTime){
		this.csocFinishTime = csocFinishTime;
		this.setSeted(F.csocFinishTime);
		return this;
	}
	/** 数据状态 [可空]       **/
	public CsOrderCluster csocStatus(Short csocStatus){
		this.csocStatus = csocStatus;
		this.setSeted(F.csocStatus);
		return this;
	}
	/** 创建时间 [非空]       **/
	public CsOrderCluster csocAddTime(Date csocAddTime){
		this.csocAddTime = csocAddTime;
		this.setSeted(F.csocAddTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsOrderCluster csocUpdateTime(Date csocUpdateTime){
		this.csocUpdateTime = csocUpdateTime;
		this.setSeted(F.csocUpdateTime);
		return this;
	}
	
	//克隆对象
	public CsOrderCluster clone(){
		CsOrderCluster clone = new CsOrderCluster();
		clone.csocId=this.csocId;
		clone.csocHost=this.csocHost;
		clone.csocSubOrders=this.csocSubOrders;
		clone.csocPayMember=this.csocPayMember;
		clone.csocMobile=this.csocMobile;
		clone.csocPrice=this.csocPrice;
		clone.csocMarginNeed=this.csocMarginNeed;
		clone.csocTotalDuration=this.csocTotalDuration;
		clone.csocPayMoney=this.csocPayMoney;
		clone.csocPayCoupon=this.csocPayCoupon;
		clone.csocPayCoin=this.csocPayCoin;
		clone.csocPayNeed=this.csocPayNeed;
		clone.csocPayReal=this.csocPayReal;
		clone.csocPayRent=this.csocPayRent;
		clone.csocPayInsure=this.csocPayInsure;
		clone.csocPayTimeout=this.csocPayTimeout;
		clone.csocPayDetails=this.csocPayDetails;
		clone.csocOutType=this.csocOutType;
		clone.csocOutId=this.csocOutId;
		clone.csocStartTime=this.csocStartTime;
		clone.csocFinishTime=this.csocFinishTime;
		clone.csocStatus=this.csocStatus;
		clone.csocAddTime=this.csocAddTime;
		clone.csocUpdateTime=this.csocUpdateTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取订单簇
	 * @param id
	 * @return
	 */
	public static @api CsOrderCluster get(Long id){		
		return getCsOrderClusterById(id);
	}
	/**
	 * 获取所有订单簇
	 * @return
	 */
	public static @api List<CsOrderCluster> list(Map params,Integer size){
		return getCsOrderClusterList(params,size);
	}
	/**
	 * 获取订单簇分页
	 * @return
	 */
	public static @api Page<CsOrderCluster> page(int page,int size,Map params){
		return getCsOrderClusterPage(page, size , params);
	}
	/**
	 * 根据查询条件取订单簇
	 * @param params
	 * @return
	 */
	public static @api CsOrderCluster Get(Map params){
		return getCsOrderCluster(params);
	}
	/**
	 * 获取订单簇数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsOrderClusterCount(params);
	}
	/**
	 * 获取订单簇数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsOrderClusterEval(eval,params);
	}
	
	/**
	 * 根据ID取订单簇
	 * @param id
	 * @return
	 */
	public static @api CsOrderCluster getCsOrderClusterById(Long id){		
		CsOrderCluster csOrderCluster = (CsOrderCluster) $.GetRequest("CsOrderCluster$"+id);
		if(csOrderCluster!=null)
			return csOrderCluster;
		com.ccclubs.service.admin.ICsOrderClusterService csOrderClusterService = $.GetSpringBean("csOrderClusterService");		
		return csOrderClusterService.getCsOrderClusterById(id);
	}
	
	
	/**
	 * 根据ID取订单簇的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsOrderCluster.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsOrderCluster csOrderCluster = get(id);
		if(csOrderCluster!=null){
			String strValue = csOrderCluster.getCsocId$();
			if(!"CsocId".equals("CsocId"))
				strValue+="("+csOrderCluster.getCsocId$()+")";
			MemCache.setValue(CsOrderCluster.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsocId$();
		if(!"CsocId".equals("CsocId"))
			keyValue+="("+this.getCsocId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有订单簇
	 * @return
	 */
	public static @api List<CsOrderCluster> getCsOrderClusterList(Map params,Integer size){
		com.ccclubs.service.admin.ICsOrderClusterService csOrderClusterService = $.GetSpringBean("csOrderClusterService");
		return csOrderClusterService.getCsOrderClusterList(params, size);
	}
	
	/**
	 * 获取订单簇分页
	 * @return
	 */
	public static @api Page<CsOrderCluster> getCsOrderClusterPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsOrderClusterService csOrderClusterService = $.GetSpringBean("csOrderClusterService");
		return csOrderClusterService.getCsOrderClusterPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取订单簇
	 * @param params
	 * @return
	 */
	public static @api CsOrderCluster getCsOrderCluster(Map params){
		com.ccclubs.service.admin.ICsOrderClusterService csOrderClusterService = $.GetSpringBean("csOrderClusterService");
		return csOrderClusterService.getCsOrderCluster(params);
	}
	
	/**
	 * 获取订单簇数
	 * @return
	 */
	public static @api Long getCsOrderClusterCount(Map params){
		com.ccclubs.service.admin.ICsOrderClusterService csOrderClusterService = $.GetSpringBean("csOrderClusterService");
		return csOrderClusterService.getCsOrderClusterCount(params);
	}
		
		
	/**
	 * 获取订单簇自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsOrderClusterEval(String eval,Map params){
		com.ccclubs.service.admin.ICsOrderClusterService csOrderClusterService = $.GetSpringBean("csOrderClusterService");
		return csOrderClusterService.getCsOrderClusterEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsOrderCluster(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsOrderClusterService csOrderClusterService = $.GetSpringBean("csOrderClusterService");
		csOrderClusterService.updateCsOrderClusterByConfirm(set, where);
	}
	
	
	/**
	 * 保存订单簇对象
	 * @param params
	 * @return
	 */
	public CsOrderCluster save(){
		com.ccclubs.service.admin.ICsOrderClusterService csOrderClusterService = $.GetSpringBean("csOrderClusterService");
		if(this.getCsocId()!=null)
			csOrderClusterService.updateCsOrderCluster(this);
		else
			return csOrderClusterService.saveCsOrderCluster(this);
		return this;
	}
	
	
	/**
	 * 更新订单簇对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsOrderClusterService csOrderClusterService = $.GetSpringBean("csOrderClusterService");
		csOrderClusterService.updateCsOrderCluster$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsOrderClusterService csOrderClusterService = $.GetSpringBean("csOrderClusterService");
		if($.equals($.config("logic_delete"),"true"))
			csOrderClusterService.removeCsOrderClusterById(this.getCsocId());
		else
			csOrderClusterService.deleteCsOrderClusterById(this.getCsocId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsOrderClusterService csOrderClusterService = $.GetSpringBean("csOrderClusterService");
		return csOrderClusterService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsocId(){
		return this.csocId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsocId$(){
		String strValue="";
		 strValue=$.str(this.getCsocId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCsocId(Long csocId){
		this.csocId = csocId;
		this.setSeted(F.csocId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [可空]      
	**/
	public Long getCsocHost(){
		return this.csocHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsocHost$(){
		String strValue="";
		if(this.getCsocHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsocHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [可空]     
	**/
	public void setCsocHost(Long csocHost){
		this.csocHost = csocHost;
		this.setSeted(F.csocHost);
	}
	/**
	* 获取关联对象[城市]
	**/	 			
 	public SrvHost get$csocHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsocHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsocHost()!=null){
			srvHost = SrvHost.get(this.getCsocHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsocHost(), srvHost);
	 	return srvHost;
	}
	/*******************************子订单集**********************************/	
	/**
	* 子订单集 [可空]      
	**/
	public String getCsocSubOrders(){
		return this.csocSubOrders;
	}
	/**
	* 获取子订单集格式化(toString)
	**/
	public String getCsocSubOrders$(){
		String strValue="";
		 strValue=$.str(this.getCsocSubOrders());
	 	 return strValue;
	}
	/**
	* 子订单集 [可空]     
	**/
	public void setCsocSubOrders(String csocSubOrders){
		this.csocSubOrders = csocSubOrders;
		this.setSeted(F.csocSubOrders);
	}
	/*******************************支付会员**********************************/	
	/**
	* 支付会员 [可空]      
	**/
	public Long getCsocPayMember(){
		return this.csocPayMember;
	}
	/**
	* 获取支付会员格式化(toString)
	**/
	public String getCsocPayMember$(){
		String strValue="";
		if(this.getCsocPayMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsocPayMember()));
		}			
	 	 return strValue;
	}
	/**
	* 支付会员 [可空]     
	**/
	public void setCsocPayMember(Long csocPayMember){
		this.csocPayMember = csocPayMember;
		this.setSeted(F.csocPayMember);
	}
	/**
	* 获取关联对象[支付会员]
	**/	 			
 	public CsMember get$csocPayMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsocPayMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsocPayMember()!=null){
			csMember = CsMember.get(this.getCsocPayMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsocPayMember(), csMember);
	 	return csMember;
	}
	/*******************************手机号码**********************************/	
	/**
	* 手机号码 [可空]      
	**/
	public String getCsocMobile(){
		return this.csocMobile;
	}
	/**
	* 获取手机号码格式化(toString)
	**/
	public String getCsocMobile$(){
		String strValue="";
		 strValue=$.str(this.getCsocMobile());
	 	 return strValue;
	}
	/**
	* 手机号码 [可空]     
	**/
	public void setCsocMobile(String csocMobile){
		this.csocMobile = csocMobile;
		this.setSeted(F.csocMobile);
	}
	/*******************************订单总价**********************************/	
	/**
	* 订单总价 [可空]      
	**/
	public Double getCsocPrice(){
		return this.csocPrice;
	}
	/**
	* 获取订单总价格式化(toString)
	**/
	public String getCsocPrice$(){
		String strValue="";
		 strValue=$.str(this.getCsocPrice());
	 	 return strValue;
	}
	/**
	* 订单总价 [可空]     
	**/
	public void setCsocPrice(Double csocPrice){
		this.csocPrice = csocPrice;
		this.setSeted(F.csocPrice);
	}
	/*******************************押金/保证金**********************************/	
	/**
	* 押金/保证金 [可空]      
	**/
	public Double getCsocMarginNeed(){
		return this.csocMarginNeed;
	}
	/**
	* 获取押金/保证金格式化(toString)
	**/
	public String getCsocMarginNeed$(){
		String strValue="";
		 strValue=$.str(this.getCsocMarginNeed());
	 	 return strValue;
	}
	/**
	* 押金/保证金 [可空]     
	**/
	public void setCsocMarginNeed(Double csocMarginNeed){
		this.csocMarginNeed = csocMarginNeed;
		this.setSeted(F.csocMarginNeed);
	}
	/*******************************订单总时长**********************************/	
	/**
	* 订单总时长 [可空]      
	**/
	public Double getCsocTotalDuration(){
		return this.csocTotalDuration;
	}
	/**
	* 获取订单总时长格式化(toString)
	**/
	public String getCsocTotalDuration$(){
		String strValue="";
		 strValue=$.str(this.getCsocTotalDuration());
	 	 return strValue;
	}
	/**
	* 订单总时长 [可空]     
	**/
	public void setCsocTotalDuration(Double csocTotalDuration){
		this.csocTotalDuration = csocTotalDuration;
		this.setSeted(F.csocTotalDuration);
	}
	/*******************************保证金支付**********************************/	
	/**
	* 保证金支付 [可空]      
	**/
	public Double getCsocPayMoney(){
		return this.csocPayMoney;
	}
	/**
	* 获取保证金支付格式化(toString)
	**/
	public String getCsocPayMoney$(){
		String strValue="";
		 strValue=$.str(this.getCsocPayMoney());
	 	 return strValue;
	}
	/**
	* 保证金支付 [可空]     
	**/
	public void setCsocPayMoney(Double csocPayMoney){
		this.csocPayMoney = csocPayMoney;
		this.setSeted(F.csocPayMoney);
	}
	/*******************************现金券支付**********************************/	
	/**
	* 现金券支付 [可空]      
	**/
	public Double getCsocPayCoupon(){
		return this.csocPayCoupon;
	}
	/**
	* 获取现金券支付格式化(toString)
	**/
	public String getCsocPayCoupon$(){
		String strValue="";
		 strValue=$.str(this.getCsocPayCoupon());
	 	 return strValue;
	}
	/**
	* 现金券支付 [可空]     
	**/
	public void setCsocPayCoupon(Double csocPayCoupon){
		this.csocPayCoupon = csocPayCoupon;
		this.setSeted(F.csocPayCoupon);
	}
	/*******************************红包支付**********************************/	
	/**
	* 红包支付 [可空]      
	**/
	public Double getCsocPayCoin(){
		return this.csocPayCoin;
	}
	/**
	* 获取红包支付格式化(toString)
	**/
	public String getCsocPayCoin$(){
		String strValue="";
		 strValue=$.str(this.getCsocPayCoin());
	 	 return strValue;
	}
	/**
	* 红包支付 [可空]     
	**/
	public void setCsocPayCoin(Double csocPayCoin){
		this.csocPayCoin = csocPayCoin;
		this.setSeted(F.csocPayCoin);
	}
	/*******************************应付金额**********************************/	
	/**
	* 应付金额 [可空]      
	**/
	public Double getCsocPayNeed(){
		return this.csocPayNeed;
	}
	/**
	* 获取应付金额格式化(toString)
	**/
	public String getCsocPayNeed$(){
		String strValue="";
		 strValue=$.str(this.getCsocPayNeed());
	 	 return strValue;
	}
	/**
	* 应付金额 [可空]     
	**/
	public void setCsocPayNeed(Double csocPayNeed){
		this.csocPayNeed = csocPayNeed;
		this.setSeted(F.csocPayNeed);
	}
	/*******************************实付金额**********************************/	
	/**
	* 实付金额 [可空]      
	**/
	public Double getCsocPayReal(){
		return this.csocPayReal;
	}
	/**
	* 获取实付金额格式化(toString)
	**/
	public String getCsocPayReal$(){
		String strValue="";
		 strValue=$.str(this.getCsocPayReal());
	 	 return strValue;
	}
	/**
	* 实付金额 [可空]     
	**/
	public void setCsocPayReal(Double csocPayReal){
		this.csocPayReal = csocPayReal;
		this.setSeted(F.csocPayReal);
	}
	/*******************************租金费用**********************************/	
	/**
	* 租金费用 [可空]      
	**/
	public Double getCsocPayRent(){
		return this.csocPayRent;
	}
	/**
	* 获取租金费用格式化(toString)
	**/
	public String getCsocPayRent$(){
		String strValue="";
		 strValue=$.str(this.getCsocPayRent());
	 	 return strValue;
	}
	/**
	* 租金费用 [可空]     
	**/
	public void setCsocPayRent(Double csocPayRent){
		this.csocPayRent = csocPayRent;
		this.setSeted(F.csocPayRent);
	}
	/*******************************免责费用**********************************/	
	/**
	* 免责费用 [可空]      
	**/
	public Double getCsocPayInsure(){
		return this.csocPayInsure;
	}
	/**
	* 获取免责费用格式化(toString)
	**/
	public String getCsocPayInsure$(){
		String strValue="";
		 strValue=$.str(this.getCsocPayInsure());
	 	 return strValue;
	}
	/**
	* 免责费用 [可空]     
	**/
	public void setCsocPayInsure(Double csocPayInsure){
		this.csocPayInsure = csocPayInsure;
		this.setSeted(F.csocPayInsure);
	}
	/*******************************超时费用**********************************/	
	/**
	* 超时费用 [可空]      
	**/
	public Double getCsocPayTimeout(){
		return this.csocPayTimeout;
	}
	/**
	* 获取超时费用格式化(toString)
	**/
	public String getCsocPayTimeout$(){
		String strValue="";
		 strValue=$.str(this.getCsocPayTimeout());
	 	 return strValue;
	}
	/**
	* 超时费用 [可空]     
	**/
	public void setCsocPayTimeout(Double csocPayTimeout){
		this.csocPayTimeout = csocPayTimeout;
		this.setSeted(F.csocPayTimeout);
	}
	/*******************************支付明细**********************************/	
	/**
	* 支付明细 [可空]      
	**/
	public String getCsocPayDetails(){
		return this.csocPayDetails;
	}
	/**
	* 获取支付明细格式化(toString)
	**/
	public String getCsocPayDetails$(){
		String strValue="";
		 strValue=$.str(this.getCsocPayDetails());
	 	 return strValue;
	}
	/**
	* 支付明细 [可空]     
	**/
	public void setCsocPayDetails(String csocPayDetails){
		this.csocPayDetails = csocPayDetails;
		this.setSeted(F.csocPayDetails);
	}
	/*******************************外部类型**********************************/	
	/**
	* 外部类型 [非空]      
	**/
	public Short getCsocOutType(){
		return this.csocOutType;
	}
	/**
	* 获取外部类型格式化(toString)
	**/
	public String getCsocOutType$(){
		String strValue="";
		 strValue=$.str(this.getCsocOutType());
	 	 return strValue;
	}
	/**
	* 外部类型 [非空]     
	**/
	public void setCsocOutType(Short csocOutType){
		this.csocOutType = csocOutType;
		this.setSeted(F.csocOutType);
	}
	/*******************************外部ID**********************************/	
	/**
	* 外部ID [非空]      
	**/
	public Long getCsocOutId(){
		return this.csocOutId;
	}
	/**
	* 获取外部ID格式化(toString)
	**/
	public String getCsocOutId$(){
		String strValue="";
		 strValue=$.str(this.getCsocOutId());
	 	 return strValue;
	}
	/**
	* 外部ID [非空]     
	**/
	public void setCsocOutId(Long csocOutId){
		this.csocOutId = csocOutId;
		this.setSeted(F.csocOutId);
	}
	/*******************************订单开始时间**********************************/	
	/**
	* 订单开始时间 [可空]      
	**/
	public Date getCsocStartTime(){
		return this.csocStartTime;
	}
	/**
	* 获取订单开始时间格式化(toString)
	**/
	public String getCsocStartTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsocStartTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 订单开始时间 [可空]     
	**/
	public void setCsocStartTime(Date csocStartTime){
		this.csocStartTime = csocStartTime;
		this.setSeted(F.csocStartTime);
	}
	/*******************************订单结束时间**********************************/	
	/**
	* 订单结束时间 [可空]      
	**/
	public Date getCsocFinishTime(){
		return this.csocFinishTime;
	}
	/**
	* 获取订单结束时间格式化(toString)
	**/
	public String getCsocFinishTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsocFinishTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 订单结束时间 [可空]     
	**/
	public void setCsocFinishTime(Date csocFinishTime){
		this.csocFinishTime = csocFinishTime;
		this.setSeted(F.csocFinishTime);
	}
	/*******************************数据状态**********************************/	
	/**
	* 数据状态 [可空]      
	**/
	public Short getCsocStatus(){
		return this.csocStatus;
	}
	/**
	* 获取数据状态格式化(toString)
	**/
	public String getCsocStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsocStatus()),"3"))
			 strValue=$.str("已取消");
		 if($.equals($.str(this.getCsocStatus()),"1"))
			 strValue=$.str("使用中");
		 if($.equals($.str(this.getCsocStatus()),"0"))
			 strValue=$.str("已预定");
		 if($.equals($.str(this.getCsocStatus()),"4"))
			 strValue=$.str("已完成");
	 	 return strValue;
	}
	/**
	* 数据状态 [可空]     
	**/
	public void setCsocStatus(Short csocStatus){
		this.csocStatus = csocStatus;
		this.setSeted(F.csocStatus);
	}
	/*******************************创建时间**********************************/	
	/**
	* 创建时间 [非空]      
	**/
	public Date getCsocAddTime(){
		return this.csocAddTime;
	}
	/**
	* 获取创建时间格式化(toString)
	**/
	public String getCsocAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsocAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 创建时间 [非空]     
	**/
	public void setCsocAddTime(Date csocAddTime){
		this.csocAddTime = csocAddTime;
		this.setSeted(F.csocAddTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsocUpdateTime(){
		return this.csocUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsocUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsocUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCsocUpdateTime(Date csocUpdateTime){
		this.csocUpdateTime = csocUpdateTime;
		this.setSeted(F.csocUpdateTime);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsOrderCluster.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsOrderCluster.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsOrderCluster.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsOrderCluster.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsOrderCluster.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsOrderCluster.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsOrderCluster.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsOrderCluster.this);
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
		public M csocId(Object csocId){this.put("csocId", csocId);return this;};
		/** and csoc_id is null */
		public M csocIdNull(){if(this.get("csocIdNot")==null)this.put("csocIdNot", "");this.put("csocId", null);return this;};
		/** not .... */
		public M csocIdNot(){this.put("csocIdNot", "not");return this;};
		/** and csoc_id >= ? */
		public M csocIdMin(Object min){this.put("csocIdMin", min);return this;};
		/** and csoc_id <= ? */
		public M csocIdMax(Object max){this.put("csocIdMax", max);return this;};
		/** 城市 [可空]       **/
		public M csocHost(Object csocHost){this.put("csocHost", csocHost);return this;};
		/** and csoc_host is null */
		public M csocHostNull(){if(this.get("csocHostNot")==null)this.put("csocHostNot", "");this.put("csocHost", null);return this;};
		/** not .... */
		public M csocHostNot(){this.put("csocHostNot", "not");return this;};
		/** and csoc_host >= ? */
		public M csocHostMin(Object min){this.put("csocHostMin", min);return this;};
		/** and csoc_host <= ? */
		public M csocHostMax(Object max){this.put("csocHostMax", max);return this;};
		/** 子订单集 [可空]       **/
		public M csocSubOrders(Object csocSubOrders){this.put("csocSubOrders", csocSubOrders);return this;};
		/** and csoc_sub_orders is null */
		public M csocSubOrdersNull(){if(this.get("csocSubOrdersNot")==null)this.put("csocSubOrdersNot", "");this.put("csocSubOrders", null);return this;};
		/** not .... */
		public M csocSubOrdersNot(){this.put("csocSubOrdersNot", "not");return this;};
		/** 支付会员 [可空]       **/
		public M csocPayMember(Object csocPayMember){this.put("csocPayMember", csocPayMember);return this;};
		/** and csoc_pay_member is null */
		public M csocPayMemberNull(){if(this.get("csocPayMemberNot")==null)this.put("csocPayMemberNot", "");this.put("csocPayMember", null);return this;};
		/** not .... */
		public M csocPayMemberNot(){this.put("csocPayMemberNot", "not");return this;};
		public M csocPayMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csocPayMember$on", value);
			return this;
		};	
		/** and csoc_pay_member >= ? */
		public M csocPayMemberMin(Object min){this.put("csocPayMemberMin", min);return this;};
		/** and csoc_pay_member <= ? */
		public M csocPayMemberMax(Object max){this.put("csocPayMemberMax", max);return this;};
		/** 手机号码 [可空]       **/
		public M csocMobile(Object csocMobile){this.put("csocMobile", csocMobile);return this;};
		/** and csoc_mobile is null */
		public M csocMobileNull(){if(this.get("csocMobileNot")==null)this.put("csocMobileNot", "");this.put("csocMobile", null);return this;};
		/** not .... */
		public M csocMobileNot(){this.put("csocMobileNot", "not");return this;};
		/** 订单总价 [可空]       **/
		public M csocPrice(Object csocPrice){this.put("csocPrice", csocPrice);return this;};
		/** and csoc_price is null */
		public M csocPriceNull(){if(this.get("csocPriceNot")==null)this.put("csocPriceNot", "");this.put("csocPrice", null);return this;};
		/** not .... */
		public M csocPriceNot(){this.put("csocPriceNot", "not");return this;};
		/** and csoc_price >= ? */
		public M csocPriceMin(Object min){this.put("csocPriceMin", min);return this;};
		/** and csoc_price <= ? */
		public M csocPriceMax(Object max){this.put("csocPriceMax", max);return this;};
		/** 押金/保证金 [可空]       **/
		public M csocMarginNeed(Object csocMarginNeed){this.put("csocMarginNeed", csocMarginNeed);return this;};
		/** and csoc_margin_need is null */
		public M csocMarginNeedNull(){if(this.get("csocMarginNeedNot")==null)this.put("csocMarginNeedNot", "");this.put("csocMarginNeed", null);return this;};
		/** not .... */
		public M csocMarginNeedNot(){this.put("csocMarginNeedNot", "not");return this;};
		/** and csoc_margin_need >= ? */
		public M csocMarginNeedMin(Object min){this.put("csocMarginNeedMin", min);return this;};
		/** and csoc_margin_need <= ? */
		public M csocMarginNeedMax(Object max){this.put("csocMarginNeedMax", max);return this;};
		/** 订单总时长 [可空]       **/
		public M csocTotalDuration(Object csocTotalDuration){this.put("csocTotalDuration", csocTotalDuration);return this;};
		/** and csoc_total_duration is null */
		public M csocTotalDurationNull(){if(this.get("csocTotalDurationNot")==null)this.put("csocTotalDurationNot", "");this.put("csocTotalDuration", null);return this;};
		/** not .... */
		public M csocTotalDurationNot(){this.put("csocTotalDurationNot", "not");return this;};
		/** and csoc_total_duration >= ? */
		public M csocTotalDurationMin(Object min){this.put("csocTotalDurationMin", min);return this;};
		/** and csoc_total_duration <= ? */
		public M csocTotalDurationMax(Object max){this.put("csocTotalDurationMax", max);return this;};
		/** 保证金支付 [可空]       **/
		public M csocPayMoney(Object csocPayMoney){this.put("csocPayMoney", csocPayMoney);return this;};
		/** and csoc_pay_money is null */
		public M csocPayMoneyNull(){if(this.get("csocPayMoneyNot")==null)this.put("csocPayMoneyNot", "");this.put("csocPayMoney", null);return this;};
		/** not .... */
		public M csocPayMoneyNot(){this.put("csocPayMoneyNot", "not");return this;};
		/** and csoc_pay_money >= ? */
		public M csocPayMoneyMin(Object min){this.put("csocPayMoneyMin", min);return this;};
		/** and csoc_pay_money <= ? */
		public M csocPayMoneyMax(Object max){this.put("csocPayMoneyMax", max);return this;};
		/** 现金券支付 [可空]       **/
		public M csocPayCoupon(Object csocPayCoupon){this.put("csocPayCoupon", csocPayCoupon);return this;};
		/** and csoc_pay_coupon is null */
		public M csocPayCouponNull(){if(this.get("csocPayCouponNot")==null)this.put("csocPayCouponNot", "");this.put("csocPayCoupon", null);return this;};
		/** not .... */
		public M csocPayCouponNot(){this.put("csocPayCouponNot", "not");return this;};
		/** and csoc_pay_coupon >= ? */
		public M csocPayCouponMin(Object min){this.put("csocPayCouponMin", min);return this;};
		/** and csoc_pay_coupon <= ? */
		public M csocPayCouponMax(Object max){this.put("csocPayCouponMax", max);return this;};
		/** 红包支付 [可空]       **/
		public M csocPayCoin(Object csocPayCoin){this.put("csocPayCoin", csocPayCoin);return this;};
		/** and csoc_pay_coin is null */
		public M csocPayCoinNull(){if(this.get("csocPayCoinNot")==null)this.put("csocPayCoinNot", "");this.put("csocPayCoin", null);return this;};
		/** not .... */
		public M csocPayCoinNot(){this.put("csocPayCoinNot", "not");return this;};
		/** and csoc_pay_coin >= ? */
		public M csocPayCoinMin(Object min){this.put("csocPayCoinMin", min);return this;};
		/** and csoc_pay_coin <= ? */
		public M csocPayCoinMax(Object max){this.put("csocPayCoinMax", max);return this;};
		/** 应付金额 [可空]       **/
		public M csocPayNeed(Object csocPayNeed){this.put("csocPayNeed", csocPayNeed);return this;};
		/** and csoc_pay_need is null */
		public M csocPayNeedNull(){if(this.get("csocPayNeedNot")==null)this.put("csocPayNeedNot", "");this.put("csocPayNeed", null);return this;};
		/** not .... */
		public M csocPayNeedNot(){this.put("csocPayNeedNot", "not");return this;};
		/** and csoc_pay_need >= ? */
		public M csocPayNeedMin(Object min){this.put("csocPayNeedMin", min);return this;};
		/** and csoc_pay_need <= ? */
		public M csocPayNeedMax(Object max){this.put("csocPayNeedMax", max);return this;};
		/** 实付金额 [可空]       **/
		public M csocPayReal(Object csocPayReal){this.put("csocPayReal", csocPayReal);return this;};
		/** and csoc_pay_real is null */
		public M csocPayRealNull(){if(this.get("csocPayRealNot")==null)this.put("csocPayRealNot", "");this.put("csocPayReal", null);return this;};
		/** not .... */
		public M csocPayRealNot(){this.put("csocPayRealNot", "not");return this;};
		/** and csoc_pay_real >= ? */
		public M csocPayRealMin(Object min){this.put("csocPayRealMin", min);return this;};
		/** and csoc_pay_real <= ? */
		public M csocPayRealMax(Object max){this.put("csocPayRealMax", max);return this;};
		/** 租金费用 [可空]       **/
		public M csocPayRent(Object csocPayRent){this.put("csocPayRent", csocPayRent);return this;};
		/** and csoc_pay_rent is null */
		public M csocPayRentNull(){if(this.get("csocPayRentNot")==null)this.put("csocPayRentNot", "");this.put("csocPayRent", null);return this;};
		/** not .... */
		public M csocPayRentNot(){this.put("csocPayRentNot", "not");return this;};
		/** and csoc_pay_rent >= ? */
		public M csocPayRentMin(Object min){this.put("csocPayRentMin", min);return this;};
		/** and csoc_pay_rent <= ? */
		public M csocPayRentMax(Object max){this.put("csocPayRentMax", max);return this;};
		/** 免责费用 [可空]       **/
		public M csocPayInsure(Object csocPayInsure){this.put("csocPayInsure", csocPayInsure);return this;};
		/** and csoc_pay_insure is null */
		public M csocPayInsureNull(){if(this.get("csocPayInsureNot")==null)this.put("csocPayInsureNot", "");this.put("csocPayInsure", null);return this;};
		/** not .... */
		public M csocPayInsureNot(){this.put("csocPayInsureNot", "not");return this;};
		/** and csoc_pay_insure >= ? */
		public M csocPayInsureMin(Object min){this.put("csocPayInsureMin", min);return this;};
		/** and csoc_pay_insure <= ? */
		public M csocPayInsureMax(Object max){this.put("csocPayInsureMax", max);return this;};
		/** 超时费用 [可空]       **/
		public M csocPayTimeout(Object csocPayTimeout){this.put("csocPayTimeout", csocPayTimeout);return this;};
		/** and csoc_pay_timeout is null */
		public M csocPayTimeoutNull(){if(this.get("csocPayTimeoutNot")==null)this.put("csocPayTimeoutNot", "");this.put("csocPayTimeout", null);return this;};
		/** not .... */
		public M csocPayTimeoutNot(){this.put("csocPayTimeoutNot", "not");return this;};
		/** and csoc_pay_timeout >= ? */
		public M csocPayTimeoutMin(Object min){this.put("csocPayTimeoutMin", min);return this;};
		/** and csoc_pay_timeout <= ? */
		public M csocPayTimeoutMax(Object max){this.put("csocPayTimeoutMax", max);return this;};
		/** 支付明细 [可空]       **/
		public M csocPayDetails(Object csocPayDetails){this.put("csocPayDetails", csocPayDetails);return this;};
		/** and csoc_pay_details is null */
		public M csocPayDetailsNull(){if(this.get("csocPayDetailsNot")==null)this.put("csocPayDetailsNot", "");this.put("csocPayDetails", null);return this;};
		/** not .... */
		public M csocPayDetailsNot(){this.put("csocPayDetailsNot", "not");return this;};
		/** 外部类型 [非空]       **/
		public M csocOutType(Object csocOutType){this.put("csocOutType", csocOutType);return this;};
		/** and csoc_out_type is null */
		public M csocOutTypeNull(){if(this.get("csocOutTypeNot")==null)this.put("csocOutTypeNot", "");this.put("csocOutType", null);return this;};
		/** not .... */
		public M csocOutTypeNot(){this.put("csocOutTypeNot", "not");return this;};
		/** and csoc_out_type >= ? */
		public M csocOutTypeMin(Object min){this.put("csocOutTypeMin", min);return this;};
		/** and csoc_out_type <= ? */
		public M csocOutTypeMax(Object max){this.put("csocOutTypeMax", max);return this;};
		/** 外部ID [非空]       **/
		public M csocOutId(Object csocOutId){this.put("csocOutId", csocOutId);return this;};
		/** and csoc_out_id is null */
		public M csocOutIdNull(){if(this.get("csocOutIdNot")==null)this.put("csocOutIdNot", "");this.put("csocOutId", null);return this;};
		/** not .... */
		public M csocOutIdNot(){this.put("csocOutIdNot", "not");return this;};
		/** and csoc_out_id >= ? */
		public M csocOutIdMin(Object min){this.put("csocOutIdMin", min);return this;};
		/** and csoc_out_id <= ? */
		public M csocOutIdMax(Object max){this.put("csocOutIdMax", max);return this;};
		/** 订单开始时间 [可空]       **/
		public M csocStartTime(Object csocStartTime){this.put("csocStartTime", csocStartTime);return this;};
		/** and csoc_start_time is null */
		public M csocStartTimeNull(){if(this.get("csocStartTimeNot")==null)this.put("csocStartTimeNot", "");this.put("csocStartTime", null);return this;};
		/** not .... */
		public M csocStartTimeNot(){this.put("csocStartTimeNot", "not");return this;};
		/** and csoc_start_time >= ? */
 		public M csocStartTimeStart(Object start){this.put("csocStartTimeStart", start);return this;};			
 		/** and csoc_start_time <= ? */
 		public M csocStartTimeEnd(Object end){this.put("csocStartTimeEnd", end);return this;};
		/** 订单结束时间 [可空]       **/
		public M csocFinishTime(Object csocFinishTime){this.put("csocFinishTime", csocFinishTime);return this;};
		/** and csoc_finish_time is null */
		public M csocFinishTimeNull(){if(this.get("csocFinishTimeNot")==null)this.put("csocFinishTimeNot", "");this.put("csocFinishTime", null);return this;};
		/** not .... */
		public M csocFinishTimeNot(){this.put("csocFinishTimeNot", "not");return this;};
		/** and csoc_finish_time >= ? */
 		public M csocFinishTimeStart(Object start){this.put("csocFinishTimeStart", start);return this;};			
 		/** and csoc_finish_time <= ? */
 		public M csocFinishTimeEnd(Object end){this.put("csocFinishTimeEnd", end);return this;};
		/** 数据状态 [可空]       **/
		public M csocStatus(Object csocStatus){this.put("csocStatus", csocStatus);return this;};
		/** and csoc_status is null */
		public M csocStatusNull(){if(this.get("csocStatusNot")==null)this.put("csocStatusNot", "");this.put("csocStatus", null);return this;};
		/** not .... */
		public M csocStatusNot(){this.put("csocStatusNot", "not");return this;};
		/** and csoc_status >= ? */
		public M csocStatusMin(Object min){this.put("csocStatusMin", min);return this;};
		/** and csoc_status <= ? */
		public M csocStatusMax(Object max){this.put("csocStatusMax", max);return this;};
		/** 创建时间 [非空]       **/
		public M csocAddTime(Object csocAddTime){this.put("csocAddTime", csocAddTime);return this;};
		/** and csoc_add_time is null */
		public M csocAddTimeNull(){if(this.get("csocAddTimeNot")==null)this.put("csocAddTimeNot", "");this.put("csocAddTime", null);return this;};
		/** not .... */
		public M csocAddTimeNot(){this.put("csocAddTimeNot", "not");return this;};
		/** and csoc_add_time >= ? */
 		public M csocAddTimeStart(Object start){this.put("csocAddTimeStart", start);return this;};			
 		/** and csoc_add_time <= ? */
 		public M csocAddTimeEnd(Object end){this.put("csocAddTimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M csocUpdateTime(Object csocUpdateTime){this.put("csocUpdateTime", csocUpdateTime);return this;};
		/** and csoc_update_time is null */
		public M csocUpdateTimeNull(){if(this.get("csocUpdateTimeNot")==null)this.put("csocUpdateTimeNot", "");this.put("csocUpdateTime", null);return this;};
		/** not .... */
		public M csocUpdateTimeNot(){this.put("csocUpdateTimeNot", "not");return this;};
		/** and csoc_update_time >= ? */
 		public M csocUpdateTimeStart(Object start){this.put("csocUpdateTimeStart", start);return this;};			
 		/** and csoc_update_time <= ? */
 		public M csocUpdateTimeEnd(Object end){this.put("csocUpdateTimeEnd", end);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有订单簇 **/
		public @api List<CsOrderCluster> list(Integer size){
			return getCsOrderClusterList(this,size);
		}
		/** 获取订单簇分页 **/
		public @api Page<CsOrderCluster> page(int page,int size){
			return getCsOrderClusterPage(page, size , this);
		}
		/** 根据查询条件取订单簇 **/
		public @api CsOrderCluster get(){
			return getCsOrderCluster(this);
		}
		/** 获取订单簇数 **/
		public @api Long count(){
			return getCsOrderClusterCount(this);
		}
		/** 获取订单簇表达式 **/
		public @api <T> T eval(String strEval){
			return getCsOrderClusterEval(strEval,this);
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
			updateCsOrderCluster(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csocId="csocId";
		/** 城市 [可空]       **/
		public final static @type(Long.class)  String csocHost="csocHost";
		/** 子订单集 [可空]       **/
		public final static @type(String.class)  String csocSubOrders="csocSubOrders";
		/** 支付会员 [可空]       **/
		public final static @type(Long.class)  String csocPayMember="csocPayMember";
		/** 手机号码 [可空]       **/
		public final static @type(String.class)  String csocMobile="csocMobile";
		/** 订单总价 [可空]       **/
		public final static @type(Double.class)  String csocPrice="csocPrice";
		/** 押金/保证金 [可空]       **/
		public final static @type(Double.class)  String csocMarginNeed="csocMarginNeed";
		/** 订单总时长 [可空]       **/
		public final static @type(Double.class)  String csocTotalDuration="csocTotalDuration";
		/** 保证金支付 [可空]       **/
		public final static @type(Double.class)  String csocPayMoney="csocPayMoney";
		/** 现金券支付 [可空]       **/
		public final static @type(Double.class)  String csocPayCoupon="csocPayCoupon";
		/** 红包支付 [可空]       **/
		public final static @type(Double.class)  String csocPayCoin="csocPayCoin";
		/** 应付金额 [可空]       **/
		public final static @type(Double.class)  String csocPayNeed="csocPayNeed";
		/** 实付金额 [可空]       **/
		public final static @type(Double.class)  String csocPayReal="csocPayReal";
		/** 租金费用 [可空]       **/
		public final static @type(Double.class)  String csocPayRent="csocPayRent";
		/** 免责费用 [可空]       **/
		public final static @type(Double.class)  String csocPayInsure="csocPayInsure";
		/** 超时费用 [可空]       **/
		public final static @type(Double.class)  String csocPayTimeout="csocPayTimeout";
		/** 支付明细 [可空]       **/
		public final static @type(String.class)  String csocPayDetails="csocPayDetails";
		/** 外部类型 [非空]       **/
		public final static @type(Short.class)  String csocOutType="csocOutType";
		/** 外部ID [非空]       **/
		public final static @type(Long.class)  String csocOutId="csocOutId";
		/** 订单开始时间 [可空]       **/
		public final static @type(Date.class)  String csocStartTime="csocStartTime";
		/** and csoc_start_time >= ? */
 		public final static @type(Date.class) String csocStartTimeStart="csocStartTimeStart";
 		/** and csoc_start_time <= ? */
 		public final static @type(Date.class) String csocStartTimeEnd="csocStartTimeEnd";
		/** 订单结束时间 [可空]       **/
		public final static @type(Date.class)  String csocFinishTime="csocFinishTime";
		/** and csoc_finish_time >= ? */
 		public final static @type(Date.class) String csocFinishTimeStart="csocFinishTimeStart";
 		/** and csoc_finish_time <= ? */
 		public final static @type(Date.class) String csocFinishTimeEnd="csocFinishTimeEnd";
		/** 数据状态 [可空]       **/
		public final static @type(Short.class)  String csocStatus="csocStatus";
		/** 创建时间 [非空]       **/
		public final static @type(Date.class)  String csocAddTime="csocAddTime";
		/** and csoc_add_time >= ? */
 		public final static @type(Date.class) String csocAddTimeStart="csocAddTimeStart";
 		/** and csoc_add_time <= ? */
 		public final static @type(Date.class) String csocAddTimeEnd="csocAddTimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csocUpdateTime="csocUpdateTime";
		/** and csoc_update_time >= ? */
 		public final static @type(Date.class) String csocUpdateTimeStart="csocUpdateTimeStart";
 		/** and csoc_update_time <= ? */
 		public final static @type(Date.class) String csocUpdateTimeEnd="csocUpdateTimeEnd";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csocId="csoc_id";
		/** 城市 [可空]       **/
		public final static String csocHost="csoc_host";
		/** 子订单集 [可空]       **/
		public final static String csocSubOrders="csoc_sub_orders";
		/** 支付会员 [可空]       **/
		public final static String csocPayMember="csoc_pay_member";
		/** 手机号码 [可空]       **/
		public final static String csocMobile="csoc_mobile";
		/** 订单总价 [可空]       **/
		public final static String csocPrice="csoc_price";
		/** 押金/保证金 [可空]       **/
		public final static String csocMarginNeed="csoc_margin_need";
		/** 订单总时长 [可空]       **/
		public final static String csocTotalDuration="csoc_total_duration";
		/** 保证金支付 [可空]       **/
		public final static String csocPayMoney="csoc_pay_money";
		/** 现金券支付 [可空]       **/
		public final static String csocPayCoupon="csoc_pay_coupon";
		/** 红包支付 [可空]       **/
		public final static String csocPayCoin="csoc_pay_coin";
		/** 应付金额 [可空]       **/
		public final static String csocPayNeed="csoc_pay_need";
		/** 实付金额 [可空]       **/
		public final static String csocPayReal="csoc_pay_real";
		/** 租金费用 [可空]       **/
		public final static String csocPayRent="csoc_pay_rent";
		/** 免责费用 [可空]       **/
		public final static String csocPayInsure="csoc_pay_insure";
		/** 超时费用 [可空]       **/
		public final static String csocPayTimeout="csoc_pay_timeout";
		/** 支付明细 [可空]       **/
		public final static String csocPayDetails="csoc_pay_details";
		/** 外部类型 [非空]       **/
		public final static String csocOutType="csoc_out_type";
		/** 外部ID [非空]       **/
		public final static String csocOutId="csoc_out_id";
		/** 订单开始时间 [可空]       **/
		public final static String csocStartTime="csoc_start_time";
		/** 订单结束时间 [可空]       **/
		public final static String csocFinishTime="csoc_finish_time";
		/** 数据状态 [可空]       **/
		public final static String csocStatus="csoc_status";
		/** 创建时间 [非空]       **/
		public final static String csocAddTime="csoc_add_time";
		/** 修改时间 [非空]       **/
		public final static String csocUpdateTime="csoc_update_time";
		
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
				$.Set(name,CsOrderCluster.getCsOrderCluster(params));
			else
				$.Set(name,CsOrderCluster.getCsOrderClusterList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取订单簇数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsOrderCluster) $.GetRequest("CsOrderCluster$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsOrderCluster.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsOrderCluster.getCsOrderCluster(params);
			else
				value = CsOrderCluster.getCsOrderClusterList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsOrderCluster.Get($.add(CsOrderCluster.F.csocId,param));
		else if(!$.empty(param.toString()))
			value = CsOrderCluster.get(Long.valueOf(param.toString()));
		$.SetRequest("CsOrderCluster$"+param.hashCode(), value);
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
	public void mergeSet(CsOrderCluster old){
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