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

@namespace("service/longorderdetail")
public @caption("长单明细") @table("cs_long_order_detail") class CsLongOrderDetail implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cslod_id")   @primary  @note("  ") Long cslodId;// 主键 非空     
	private @caption("城市") @column("cslod_host")    @relate("$cslodHost") @RelateClass(SrvHost.class)  @note("  ") Long cslodHost;// 非空     
 	private SrvHost $cslodHost;//关联对象[运营城市]
	private @caption("所属订单") @column("cslod_long_order")    @relate("$cslodLongOrder") @RelateClass(CsLongOrder.class)  @note("  ") Long cslodLongOrder;// 非空     
 	private CsLongOrder $cslodLongOrder;//关联对象[长租订单]
	private @caption("预订车辆") @column("cslod_car")    @relate("$cslodCar") @RelateClass(CsCar.class)  @note("  ") Long cslodCar;// 非空     
 	private CsCar $cslodCar;//关联对象[车辆]
	private @caption("每月租金") @column("cslod_price")    @note("  ") Double cslodPrice;// 非空     
	private @caption("交付时里程") @column("cslod_set_kilo")    @note("  ") Long cslodSetKilo;//     
	private @caption("还车时里程") @column("cslod_ret_kilo")    @note("  ") Long cslodRetKilo;//     
	private @caption("车辆交付时间") @column("cslod_set_time")    @note("  ") Date cslodSetTime;//     
	private @caption("车辆归还时间") @column("cslod_ret_time")    @note("  ") Date cslodRetTime;//     
	private @caption("修改时间") @column("cslod_update_time")    @note("  ") Date cslodUpdateTime;// 非空     
	private @caption("添加时间") @column("cslod_add_time")    @note("  ") Date cslodAddTime;// 非空     
	private @caption("操作人") @column("cslod_editor")    @relate("$cslodEditor") @RelateClass(SrvUser.class)  @note("  ") Long cslodEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cslodEditor;//关联对象[用户]
	private @caption("备注信息") @column("cslod_remark")    @note("  ") String cslodRemark;//     
	private @caption("状态") @column("cslod_status")    @note(" 0:待交付 1:使用中 2:已结束 3:已作废  ") Short cslodStatus;// 非空 0:待交付 1:使用中 2:已结束 3:已作废     
	
	//默认构造函数
	public CsLongOrderDetail(){
	
	}
	
	//主键构造函数
	public CsLongOrderDetail(Long id){
		this.cslodId = id;
	}
	
	/**所有字段构造函数 CsLongOrderDetail(cslodHost,cslodLongOrder,cslodCar,cslodPrice,cslodSetKilo,cslodRetKilo,cslodSetTime,cslodRetTime,cslodUpdateTime,cslodAddTime,cslodEditor,cslodRemark,cslodStatus)
	 CsLongOrderDetail(
	 	$.getLong("cslodHost")//城市 [非空]
	 	,$.getLong("cslodLongOrder")//所属订单 [非空]
	 	,$.getLong("cslodCar")//预订车辆 [非空]
	 	,$.getDouble("cslodPrice")//每月租金 [非空]
	 	,$.getLong("cslodSetKilo")//交付时里程
	 	,$.getLong("cslodRetKilo")//还车时里程
	 	,$.getDate("cslodSetTime")//车辆交付时间
	 	,$.getDate("cslodRetTime")//车辆归还时间
	 	,$.getDate("cslodUpdateTime")//修改时间 [非空]
	 	,$.getDate("cslodAddTime")//添加时间 [非空]
	 	,$.getLong("cslodEditor")//操作人 [非空]
	 	,$.getString("cslodRemark")//备注信息
	 	,$.getShort("cslodStatus")//状态 [非空]
	 )
	**/
	public CsLongOrderDetail(Long cslodHost,Long cslodLongOrder,Long cslodCar,Double cslodPrice,Long cslodSetKilo,Long cslodRetKilo,Date cslodSetTime,Date cslodRetTime,Date cslodUpdateTime,Date cslodAddTime,Long cslodEditor,String cslodRemark,Short cslodStatus){
		this.cslodHost=cslodHost;
		this.cslodLongOrder=cslodLongOrder;
		this.cslodCar=cslodCar;
		this.cslodPrice=cslodPrice;
		this.cslodSetKilo=cslodSetKilo;
		this.cslodRetKilo=cslodRetKilo;
		this.cslodSetTime=cslodSetTime;
		this.cslodRetTime=cslodRetTime;
		this.cslodUpdateTime=cslodUpdateTime;
		this.cslodAddTime=cslodAddTime;
		this.cslodEditor=cslodEditor;
		this.cslodRemark=cslodRemark;
		this.cslodStatus=cslodStatus;
	}
	
	//设置非空字段
	public CsLongOrderDetail setNotNull(Long cslodHost,Long cslodLongOrder,Long cslodCar,Double cslodPrice,Date cslodUpdateTime,Date cslodAddTime,Long cslodEditor,Short cslodStatus){
		this.cslodHost=cslodHost;
		this.cslodLongOrder=cslodLongOrder;
		this.cslodCar=cslodCar;
		this.cslodPrice=cslodPrice;
		this.cslodUpdateTime=cslodUpdateTime;
		this.cslodAddTime=cslodAddTime;
		this.cslodEditor=cslodEditor;
		this.cslodStatus=cslodStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsLongOrderDetail cslodId(Long cslodId){
		this.cslodId = cslodId;
		this.setSeted(F.cslodId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsLongOrderDetail cslodHost(Long cslodHost){
		this.cslodHost = cslodHost;
		this.setSeted(F.cslodHost);
		return this;
	}
	/** 所属订单 [非空] [CsLongOrder]      **/
	public CsLongOrderDetail cslodLongOrder(Long cslodLongOrder){
		this.cslodLongOrder = cslodLongOrder;
		this.setSeted(F.cslodLongOrder);
		return this;
	}
	/** 预订车辆 [非空] [CsCar]      **/
	public CsLongOrderDetail cslodCar(Long cslodCar){
		this.cslodCar = cslodCar;
		this.setSeted(F.cslodCar);
		return this;
	}
	/** 每月租金 [非空]       **/
	public CsLongOrderDetail cslodPrice(Double cslodPrice){
		this.cslodPrice = cslodPrice;
		this.setSeted(F.cslodPrice);
		return this;
	}
	/** 交付时里程        **/
	public CsLongOrderDetail cslodSetKilo(Long cslodSetKilo){
		this.cslodSetKilo = cslodSetKilo;
		this.setSeted(F.cslodSetKilo);
		return this;
	}
	/** 还车时里程        **/
	public CsLongOrderDetail cslodRetKilo(Long cslodRetKilo){
		this.cslodRetKilo = cslodRetKilo;
		this.setSeted(F.cslodRetKilo);
		return this;
	}
	/** 车辆交付时间        **/
	public CsLongOrderDetail cslodSetTime(Date cslodSetTime){
		this.cslodSetTime = cslodSetTime;
		this.setSeted(F.cslodSetTime);
		return this;
	}
	/** 车辆归还时间        **/
	public CsLongOrderDetail cslodRetTime(Date cslodRetTime){
		this.cslodRetTime = cslodRetTime;
		this.setSeted(F.cslodRetTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsLongOrderDetail cslodUpdateTime(Date cslodUpdateTime){
		this.cslodUpdateTime = cslodUpdateTime;
		this.setSeted(F.cslodUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsLongOrderDetail cslodAddTime(Date cslodAddTime){
		this.cslodAddTime = cslodAddTime;
		this.setSeted(F.cslodAddTime);
		return this;
	}
	/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsLongOrderDetail cslodEditor(Long cslodEditor){
		this.cslodEditor = cslodEditor;
		this.setSeted(F.cslodEditor);
		return this;
	}
	/** 备注信息        **/
	public CsLongOrderDetail cslodRemark(String cslodRemark){
		this.cslodRemark = cslodRemark;
		this.setSeted(F.cslodRemark);
		return this;
	}
	/** 状态 [非空]   0:待交付 1:使用中 2:已结束 3:已作废     **/
	public CsLongOrderDetail cslodStatus(Short cslodStatus){
		this.cslodStatus = cslodStatus;
		this.setSeted(F.cslodStatus);
		return this;
	}
	
	
	//克隆对象
	public CsLongOrderDetail clone(){
		CsLongOrderDetail clone = new CsLongOrderDetail();
		clone.cslodHost=this.cslodHost;
		clone.cslodLongOrder=this.cslodLongOrder;
		clone.cslodCar=this.cslodCar;
		clone.cslodPrice=this.cslodPrice;
		clone.cslodUpdateTime=this.cslodUpdateTime;
		clone.cslodAddTime=this.cslodAddTime;
		clone.cslodEditor=this.cslodEditor;
		clone.cslodStatus=this.cslodStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取长单明细
	 * @param id
	 * @return
	 */
	public static @api CsLongOrderDetail get(Long id){		
		return getCsLongOrderDetailById(id);
	}
	/**
	 * 获取所有长单明细
	 * @return
	 */
	public static @api List<CsLongOrderDetail> list(Map params,Integer size){
		return getCsLongOrderDetailList(params,size);
	}
	/**
	 * 获取长单明细分页
	 * @return
	 */
	public static @api Page<CsLongOrderDetail> page(int page,int size,Map params){
		return getCsLongOrderDetailPage(page, size , params);
	}
	/**
	 * 根据查询条件取长单明细
	 * @param params
	 * @return
	 */
	public static @api CsLongOrderDetail Get(Map params){
		return getCsLongOrderDetail(params);
	}
	/**
	 * 获取长单明细数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsLongOrderDetailCount(params);
	}
	/**
	 * 获取长单明细数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsLongOrderDetailEval(eval,params);
	}
	
	/**
	 * 根据ID取长单明细
	 * @param id
	 * @return
	 */
	public static @api CsLongOrderDetail getCsLongOrderDetailById(Long id){		
		CsLongOrderDetail csLongOrderDetail = (CsLongOrderDetail) $.GetRequest("CsLongOrderDetail$"+id);
		if(csLongOrderDetail!=null)
			return csLongOrderDetail;
		com.ccclubs.service.admin.ICsLongOrderDetailService csLongOrderDetailService = $.GetSpringBean("csLongOrderDetailService");		
		return csLongOrderDetailService.getCsLongOrderDetailById(id);
	}
	
	
	/**
	 * 根据ID取长单明细的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsLongOrderDetail.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsLongOrderDetail csLongOrderDetail = get(id);
		if(csLongOrderDetail!=null){
			String strValue = csLongOrderDetail.getCslodId$();
			if(!"CslodId".equals("CslodId"))
				strValue+="("+csLongOrderDetail.getCslodId$()+")";
			if(!"CslodId".equals("CslodStatus"))
				strValue+="("+csLongOrderDetail.getCslodStatus$()+")";
			MemCache.setValue(CsLongOrderDetail.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCslodId$();
		if(!"CslodId".equals("CslodId"))
			keyValue+="("+this.getCslodId$()+")";
		if(!"CslodId".equals("CslodStatus"))
			keyValue+="("+this.getCslodStatus$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有长单明细
	 * @return
	 */
	public static @api List<CsLongOrderDetail> getCsLongOrderDetailList(Map params,Integer size){
		com.ccclubs.service.admin.ICsLongOrderDetailService csLongOrderDetailService = $.GetSpringBean("csLongOrderDetailService");
		return csLongOrderDetailService.getCsLongOrderDetailList(params, size);
	}
	
	/**
	 * 获取长单明细分页
	 * @return
	 */
	public static @api Page<CsLongOrderDetail> getCsLongOrderDetailPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsLongOrderDetailService csLongOrderDetailService = $.GetSpringBean("csLongOrderDetailService");
		return csLongOrderDetailService.getCsLongOrderDetailPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取长单明细
	 * @param params
	 * @return
	 */
	public static @api CsLongOrderDetail getCsLongOrderDetail(Map params){
		com.ccclubs.service.admin.ICsLongOrderDetailService csLongOrderDetailService = $.GetSpringBean("csLongOrderDetailService");
		return csLongOrderDetailService.getCsLongOrderDetail(params);
	}
	
	/**
	 * 获取长单明细数
	 * @return
	 */
	public static @api Long getCsLongOrderDetailCount(Map params){
		com.ccclubs.service.admin.ICsLongOrderDetailService csLongOrderDetailService = $.GetSpringBean("csLongOrderDetailService");
		return csLongOrderDetailService.getCsLongOrderDetailCount(params);
	}
		
		
	/**
	 * 获取长单明细自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsLongOrderDetailEval(String eval,Map params){
		com.ccclubs.service.admin.ICsLongOrderDetailService csLongOrderDetailService = $.GetSpringBean("csLongOrderDetailService");
		return csLongOrderDetailService.getCsLongOrderDetailEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsLongOrderDetail(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsLongOrderDetailService csLongOrderDetailService = $.GetSpringBean("csLongOrderDetailService");
		csLongOrderDetailService.updateCsLongOrderDetailByConfirm(set, where);
	}
	
	
	/**
	 * 保存长单明细对象
	 * @param params
	 * @return
	 */
	public CsLongOrderDetail save(){
		com.ccclubs.service.admin.ICsLongOrderDetailService csLongOrderDetailService = $.GetSpringBean("csLongOrderDetailService");
		if(this.getCslodId()!=null)
			csLongOrderDetailService.updateCsLongOrderDetail(this);
		else
			return csLongOrderDetailService.saveCsLongOrderDetail(this);
		return this;
	}
	
	
	/**
	 * 更新长单明细对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsLongOrderDetailService csLongOrderDetailService = $.GetSpringBean("csLongOrderDetailService");
		csLongOrderDetailService.updateCsLongOrderDetail$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsLongOrderDetailService csLongOrderDetailService = $.GetSpringBean("csLongOrderDetailService");
		if($.equals($.config("logic_delete"),"true"))
			csLongOrderDetailService.removeCsLongOrderDetailById(this.getCslodId());
		else
			csLongOrderDetailService.deleteCsLongOrderDetailById(this.getCslodId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsLongOrderDetailService csLongOrderDetailService = $.GetSpringBean("csLongOrderDetailService");
		return csLongOrderDetailService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCslodId(){
		return this.cslodId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCslodId$(){
		String strValue="";
		 strValue=$.str(this.getCslodId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCslodId(Long cslodId){
		this.cslodId = cslodId;
		this.setSeted(F.cslodId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCslodHost(){
		return this.cslodHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCslodHost$(){
		String strValue="";
		if(this.getCslodHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCslodHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCslodHost(Long cslodHost){
		this.cslodHost = cslodHost;
		this.setSeted(F.cslodHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cslodHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCslodHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCslodHost()!=null){
 			srvHost = SrvHost.get(this.getCslodHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCslodHost(), srvHost);
	 	return srvHost;
	}
	/*******************************所属订单**********************************/	
	/**
	* 所属订单 [非空] [CsLongOrder]     
	**/
	public Long getCslodLongOrder(){
		return this.cslodLongOrder;
	}
	/**
	* 获取所属订单格式化(toString)
	**/
	public String getCslodLongOrder$(){
		String strValue="";
		if(this.getCslodLongOrder()!=null){
				strValue+=$.str(CsLongOrder.getKeyValue(this.getCslodLongOrder()));
		}			
	 	 return strValue;
	}
	/**
	* 所属订单 [非空] [CsLongOrder]     
	**/
	public void setCslodLongOrder(Long cslodLongOrder){
		this.cslodLongOrder = cslodLongOrder;
		this.setSeted(F.cslodLongOrder);
	}
	/**
	* 获取关联对象[长租订单]
	**/	 			
 	public CsLongOrder get$cslodLongOrder(){
 		com.ccclubs.model.CsLongOrder csLongOrder = (com.ccclubs.model.CsLongOrder) $.GetRequest("CsLongOrder$"+this.getCslodLongOrder());
 		if(csLongOrder!=null)
			return csLongOrder;
		if(this.getCslodLongOrder()!=null){
 			csLongOrder = CsLongOrder.get(this.getCslodLongOrder());
 		}
 		$.SetRequest("CsLongOrder$"+this.getCslodLongOrder(), csLongOrder);
	 	return csLongOrder;
	}
	/*******************************预订车辆**********************************/	
	/**
	* 预订车辆 [非空] [CsCar]     
	**/
	public Long getCslodCar(){
		return this.cslodCar;
	}
	/**
	* 获取预订车辆格式化(toString)
	**/
	public String getCslodCar$(){
		String strValue="";
		if(this.getCslodCar()!=null){
				strValue+=$.str(CsCar.getKeyValue(this.getCslodCar()));
		}			
	 	 return strValue;
	}
	/**
	* 预订车辆 [非空] [CsCar]     
	**/
	public void setCslodCar(Long cslodCar){
		this.cslodCar = cslodCar;
		this.setSeted(F.cslodCar);
	}
	/**
	* 获取关联对象[车辆]
	**/	 			
 	public CsCar get$cslodCar(){
 		com.ccclubs.model.CsCar csCar = (com.ccclubs.model.CsCar) $.GetRequest("CsCar$"+this.getCslodCar());
 		if(csCar!=null)
			return csCar;
		if(this.getCslodCar()!=null){
 			csCar = CsCar.get(this.getCslodCar());
 		}
 		$.SetRequest("CsCar$"+this.getCslodCar(), csCar);
	 	return csCar;
	}
	/*******************************每月租金**********************************/	
	/**
	* 每月租金 [非空]      
	**/
	public Double getCslodPrice(){
		return this.cslodPrice;
	}
	/**
	* 获取每月租金格式化(toString)
	**/
	public String getCslodPrice$(){
		String strValue="";
		 strValue=$.str(this.getCslodPrice());	
	 	 return strValue;
	}
	/**
	* 每月租金 [非空]      
	**/
	public void setCslodPrice(Double cslodPrice){
		this.cslodPrice = cslodPrice;
		this.setSeted(F.cslodPrice);
	}
	/*******************************交付时里程**********************************/	
	/**
	* 交付时里程       
	**/
	public Long getCslodSetKilo(){
		return this.cslodSetKilo;
	}
	/**
	* 获取交付时里程格式化(toString)
	**/
	public String getCslodSetKilo$(){
		String strValue="";
		 strValue=$.str(this.getCslodSetKilo());
	 	 return strValue;
	}
	/**
	* 交付时里程       
	**/
	public void setCslodSetKilo(Long cslodSetKilo){
		this.cslodSetKilo = cslodSetKilo;
		this.setSeted(F.cslodSetKilo);
	}
	/*******************************还车时里程**********************************/	
	/**
	* 还车时里程       
	**/
	public Long getCslodRetKilo(){
		return this.cslodRetKilo;
	}
	/**
	* 获取还车时里程格式化(toString)
	**/
	public String getCslodRetKilo$(){
		String strValue="";
		 strValue=$.str(this.getCslodRetKilo());
	 	 return strValue;
	}
	/**
	* 还车时里程       
	**/
	public void setCslodRetKilo(Long cslodRetKilo){
		this.cslodRetKilo = cslodRetKilo;
		this.setSeted(F.cslodRetKilo);
	}
	/*******************************车辆交付时间**********************************/	
	/**
	* 车辆交付时间       
	**/
	public Date getCslodSetTime(){
		return this.cslodSetTime;
	}
	/**
	* 获取车辆交付时间格式化(toString)
	**/
	public String getCslodSetTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCslodSetTime(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 车辆交付时间       
	**/
	public void setCslodSetTime(Date cslodSetTime){
		this.cslodSetTime = cslodSetTime;
		this.setSeted(F.cslodSetTime);
	}
	/*******************************车辆归还时间**********************************/	
	/**
	* 车辆归还时间       
	**/
	public Date getCslodRetTime(){
		return this.cslodRetTime;
	}
	/**
	* 获取车辆归还时间格式化(toString)
	**/
	public String getCslodRetTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCslodRetTime(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 车辆归还时间       
	**/
	public void setCslodRetTime(Date cslodRetTime){
		this.cslodRetTime = cslodRetTime;
		this.setSeted(F.cslodRetTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCslodUpdateTime(){
		return this.cslodUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCslodUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCslodUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCslodUpdateTime(Date cslodUpdateTime){
		this.cslodUpdateTime = cslodUpdateTime;
		this.setSeted(F.cslodUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCslodAddTime(){
		return this.cslodAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCslodAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCslodAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCslodAddTime(Date cslodAddTime){
		this.cslodAddTime = cslodAddTime;
		this.setSeted(F.cslodAddTime);
	}
	/*******************************操作人**********************************/	
	/**
	* 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCslodEditor(){
		return this.cslodEditor;
	}
	/**
	* 获取操作人格式化(toString)
	**/
	public String getCslodEditor$(){
		String strValue="";
		if(this.getCslodEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCslodEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCslodEditor(Long cslodEditor){
		this.cslodEditor = cslodEditor;
		this.setSeted(F.cslodEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cslodEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCslodEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCslodEditor()!=null){
 			srvUser = SrvUser.get(this.getCslodEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCslodEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息       
	**/
	public String getCslodRemark(){
		return this.cslodRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCslodRemark$(){
		String strValue="";
		 strValue=$.str(this.getCslodRemark());
	 	 return strValue;
	}
	/**
	* 备注信息       
	**/
	public void setCslodRemark(String cslodRemark){
		this.cslodRemark = cslodRemark;
		this.setSeted(F.cslodRemark);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:待交付 1:使用中 2:已结束 3:已作废    
	**/
	public Short getCslodStatus(){
		return this.cslodStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCslodStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCslodStatus()),"0"))
			strValue=$.str("待交付");		 
		 if($.equals($.str(this.getCslodStatus()),"1"))
			strValue=$.str("使用中");		 
		 if($.equals($.str(this.getCslodStatus()),"2"))
			strValue=$.str("已结束");		 
		 if($.equals($.str(this.getCslodStatus()),"3"))
			strValue=$.str("已作废");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:待交付 1:使用中 2:已结束 3:已作废    
	**/
	public void setCslodStatus(Short cslodStatus){
		this.cslodStatus = cslodStatus;
		this.setSeted(F.cslodStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsLongOrderDetail.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsLongOrderDetail.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsLongOrderDetail.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsLongOrderDetail.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsLongOrderDetail.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsLongOrderDetail.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsLongOrderDetail.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsLongOrderDetail.this);
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
		public M cslodId(Object cslodId){this.put("cslodId", cslodId);return this;};
	 	/** and cslod_id is null */
 		public M cslodIdNull(){if(this.get("cslodIdNot")==null)this.put("cslodIdNot", "");this.put("cslodId", null);return this;};
 		/** not .... */
 		public M cslodIdNot(){this.put("cslodIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M cslodHost(Object cslodHost){this.put("cslodHost", cslodHost);return this;};
	 	/** and cslod_host is null */
 		public M cslodHostNull(){if(this.get("cslodHostNot")==null)this.put("cslodHostNot", "");this.put("cslodHost", null);return this;};
 		/** not .... */
 		public M cslodHostNot(){this.put("cslodHostNot", "not");return this;};
		/** 所属订单 [非空] [CsLongOrder]      **/
		public M cslodLongOrder(Object cslodLongOrder){this.put("cslodLongOrder", cslodLongOrder);return this;};
	 	/** and cslod_long_order is null */
 		public M cslodLongOrderNull(){if(this.get("cslodLongOrderNot")==null)this.put("cslodLongOrderNot", "");this.put("cslodLongOrder", null);return this;};
 		/** not .... */
 		public M cslodLongOrderNot(){this.put("cslodLongOrderNot", "not");return this;};
		public M cslodLongOrder$on(CsLongOrder.M value){
			this.put("CsLongOrder", value);
			this.put("cslodLongOrder$on", value);
			return this;
		};	
		/** 预订车辆 [非空] [CsCar]      **/
		public M cslodCar(Object cslodCar){this.put("cslodCar", cslodCar);return this;};
	 	/** and cslod_car is null */
 		public M cslodCarNull(){if(this.get("cslodCarNot")==null)this.put("cslodCarNot", "");this.put("cslodCar", null);return this;};
 		/** not .... */
 		public M cslodCarNot(){this.put("cslodCarNot", "not");return this;};
		public M cslodCar$on(CsCar.M value){
			this.put("CsCar", value);
			this.put("cslodCar$on", value);
			return this;
		};	
		/** 每月租金 [非空]       **/
		public M cslodPrice(Object cslodPrice){this.put("cslodPrice", cslodPrice);return this;};
	 	/** and cslod_price is null */
 		public M cslodPriceNull(){if(this.get("cslodPriceNot")==null)this.put("cslodPriceNot", "");this.put("cslodPrice", null);return this;};
 		/** not .... */
 		public M cslodPriceNot(){this.put("cslodPriceNot", "not");return this;};
		/** and cslod_price >= ? */
		public M cslodPriceMin(Object min){this.put("cslodPriceMin", min);return this;};
		/** and cslod_price <= ? */
		public M cslodPriceMax(Object max){this.put("cslodPriceMax", max);return this;};
		/** 交付时里程        **/
		public M cslodSetKilo(Object cslodSetKilo){this.put("cslodSetKilo", cslodSetKilo);return this;};
	 	/** and cslod_set_kilo is null */
 		public M cslodSetKiloNull(){if(this.get("cslodSetKiloNot")==null)this.put("cslodSetKiloNot", "");this.put("cslodSetKilo", null);return this;};
 		/** not .... */
 		public M cslodSetKiloNot(){this.put("cslodSetKiloNot", "not");return this;};
		/** and cslod_set_kilo >= ? */
		public M cslodSetKiloMin(Object min){this.put("cslodSetKiloMin", min);return this;};
		/** and cslod_set_kilo <= ? */
		public M cslodSetKiloMax(Object max){this.put("cslodSetKiloMax", max);return this;};
		/** 还车时里程        **/
		public M cslodRetKilo(Object cslodRetKilo){this.put("cslodRetKilo", cslodRetKilo);return this;};
	 	/** and cslod_ret_kilo is null */
 		public M cslodRetKiloNull(){if(this.get("cslodRetKiloNot")==null)this.put("cslodRetKiloNot", "");this.put("cslodRetKilo", null);return this;};
 		/** not .... */
 		public M cslodRetKiloNot(){this.put("cslodRetKiloNot", "not");return this;};
		/** and cslod_ret_kilo >= ? */
		public M cslodRetKiloMin(Object min){this.put("cslodRetKiloMin", min);return this;};
		/** and cslod_ret_kilo <= ? */
		public M cslodRetKiloMax(Object max){this.put("cslodRetKiloMax", max);return this;};
		/** 车辆交付时间        **/
		public M cslodSetTime(Object cslodSetTime){this.put("cslodSetTime", cslodSetTime);return this;};
	 	/** and cslod_set_time is null */
 		public M cslodSetTimeNull(){if(this.get("cslodSetTimeNot")==null)this.put("cslodSetTimeNot", "");this.put("cslodSetTime", null);return this;};
 		/** not .... */
 		public M cslodSetTimeNot(){this.put("cslodSetTimeNot", "not");return this;};
 		/** and cslod_set_time >= ? */
 		public M cslodSetTimeStart(Object start){this.put("cslodSetTimeStart", start);return this;};			
 		/** and cslod_set_time <= ? */
 		public M cslodSetTimeEnd(Object end){this.put("cslodSetTimeEnd", end);return this;};
		/** 车辆归还时间        **/
		public M cslodRetTime(Object cslodRetTime){this.put("cslodRetTime", cslodRetTime);return this;};
	 	/** and cslod_ret_time is null */
 		public M cslodRetTimeNull(){if(this.get("cslodRetTimeNot")==null)this.put("cslodRetTimeNot", "");this.put("cslodRetTime", null);return this;};
 		/** not .... */
 		public M cslodRetTimeNot(){this.put("cslodRetTimeNot", "not");return this;};
 		/** and cslod_ret_time >= ? */
 		public M cslodRetTimeStart(Object start){this.put("cslodRetTimeStart", start);return this;};			
 		/** and cslod_ret_time <= ? */
 		public M cslodRetTimeEnd(Object end){this.put("cslodRetTimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M cslodUpdateTime(Object cslodUpdateTime){this.put("cslodUpdateTime", cslodUpdateTime);return this;};
	 	/** and cslod_update_time is null */
 		public M cslodUpdateTimeNull(){if(this.get("cslodUpdateTimeNot")==null)this.put("cslodUpdateTimeNot", "");this.put("cslodUpdateTime", null);return this;};
 		/** not .... */
 		public M cslodUpdateTimeNot(){this.put("cslodUpdateTimeNot", "not");return this;};
 		/** and cslod_update_time >= ? */
 		public M cslodUpdateTimeStart(Object start){this.put("cslodUpdateTimeStart", start);return this;};			
 		/** and cslod_update_time <= ? */
 		public M cslodUpdateTimeEnd(Object end){this.put("cslodUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cslodAddTime(Object cslodAddTime){this.put("cslodAddTime", cslodAddTime);return this;};
	 	/** and cslod_add_time is null */
 		public M cslodAddTimeNull(){if(this.get("cslodAddTimeNot")==null)this.put("cslodAddTimeNot", "");this.put("cslodAddTime", null);return this;};
 		/** not .... */
 		public M cslodAddTimeNot(){this.put("cslodAddTimeNot", "not");return this;};
 		/** and cslod_add_time >= ? */
 		public M cslodAddTimeStart(Object start){this.put("cslodAddTimeStart", start);return this;};			
 		/** and cslod_add_time <= ? */
 		public M cslodAddTimeEnd(Object end){this.put("cslodAddTimeEnd", end);return this;};
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cslodEditor(Object cslodEditor){this.put("cslodEditor", cslodEditor);return this;};
	 	/** and cslod_editor is null */
 		public M cslodEditorNull(){if(this.get("cslodEditorNot")==null)this.put("cslodEditorNot", "");this.put("cslodEditor", null);return this;};
 		/** not .... */
 		public M cslodEditorNot(){this.put("cslodEditorNot", "not");return this;};
		/** 备注信息        **/
		public M cslodRemark(Object cslodRemark){this.put("cslodRemark", cslodRemark);return this;};
	 	/** and cslod_remark is null */
 		public M cslodRemarkNull(){if(this.get("cslodRemarkNot")==null)this.put("cslodRemarkNot", "");this.put("cslodRemark", null);return this;};
 		/** not .... */
 		public M cslodRemarkNot(){this.put("cslodRemarkNot", "not");return this;};
		/** 状态 [非空]   0:待交付 1:使用中 2:已结束 3:已作废     **/
		public M cslodStatus(Object cslodStatus){this.put("cslodStatus", cslodStatus);return this;};
	 	/** and cslod_status is null */
 		public M cslodStatusNull(){if(this.get("cslodStatusNot")==null)this.put("cslodStatusNot", "");this.put("cslodStatus", null);return this;};
 		/** not .... */
 		public M cslodStatusNot(){this.put("cslodStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有长单明细 **/
		public @api List<CsLongOrderDetail> list(Integer size){
			return getCsLongOrderDetailList(this,size);
		}
		/** 获取长单明细分页 **/
		public @api Page<CsLongOrderDetail> page(int page,int size){
			return getCsLongOrderDetailPage(page, size , this);
		}
		/** 根据查询条件取长单明细 **/
		public @api CsLongOrderDetail get(){
			return getCsLongOrderDetail(this);
		}
		/** 获取长单明细数 **/
		public @api Long count(){
			return getCsLongOrderDetailCount(this);
		}
		/** 获取长单明细表达式 **/
		public @api <T> T eval(String strEval){
			return getCsLongOrderDetailEval(strEval,this);
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
			updateCsLongOrderDetail(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cslodId="cslodId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cslodHost="cslodHost";
		/** 所属订单 [非空] [CsLongOrder]      **/
		public final static @type(Long.class)  String cslodLongOrder="cslodLongOrder";
		/** 预订车辆 [非空] [CsCar]      **/
		public final static @type(Long.class)  String cslodCar="cslodCar";
		/** 每月租金 [非空]       **/
		public final static @type(Double.class)  String cslodPrice="cslodPrice";
		/** and cslod_price >= ? */
		public final static @type(Double.class) String cslodPriceMin="cslodPriceMin";
		/** and cslod_price <= ? */
		public final static @type(Double.class) String cslodPriceMax="cslodPriceMax";
		/** 交付时里程        **/
		public final static @type(Long.class)  String cslodSetKilo="cslodSetKilo";
		/** and cslod_set_kilo >= ? */
		public final static @type(Long.class) String cslodSetKiloMin="cslodSetKiloMin";
		/** and cslod_set_kilo <= ? */
		public final static @type(Long.class) String cslodSetKiloMax="cslodSetKiloMax";
		/** 还车时里程        **/
		public final static @type(Long.class)  String cslodRetKilo="cslodRetKilo";
		/** and cslod_ret_kilo >= ? */
		public final static @type(Long.class) String cslodRetKiloMin="cslodRetKiloMin";
		/** and cslod_ret_kilo <= ? */
		public final static @type(Long.class) String cslodRetKiloMax="cslodRetKiloMax";
		/** 车辆交付时间        **/
		public final static @type(Date.class)  String cslodSetTime="cslodSetTime";
	 	/** and cslod_set_time >= ? */
 		public final static @type(Date.class) String cslodSetTimeStart="cslodSetTimeStart";
 		/** and cslod_set_time <= ? */
 		public final static @type(Date.class) String cslodSetTimeEnd="cslodSetTimeEnd";
		/** 车辆归还时间        **/
		public final static @type(Date.class)  String cslodRetTime="cslodRetTime";
	 	/** and cslod_ret_time >= ? */
 		public final static @type(Date.class) String cslodRetTimeStart="cslodRetTimeStart";
 		/** and cslod_ret_time <= ? */
 		public final static @type(Date.class) String cslodRetTimeEnd="cslodRetTimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cslodUpdateTime="cslodUpdateTime";
	 	/** and cslod_update_time >= ? */
 		public final static @type(Date.class) String cslodUpdateTimeStart="cslodUpdateTimeStart";
 		/** and cslod_update_time <= ? */
 		public final static @type(Date.class) String cslodUpdateTimeEnd="cslodUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cslodAddTime="cslodAddTime";
	 	/** and cslod_add_time >= ? */
 		public final static @type(Date.class) String cslodAddTimeStart="cslodAddTimeStart";
 		/** and cslod_add_time <= ? */
 		public final static @type(Date.class) String cslodAddTimeEnd="cslodAddTimeEnd";
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String cslodEditor="cslodEditor";
		/** 备注信息        **/
		public final static @type(String.class) @like String cslodRemark="cslodRemark";
		/** 状态 [非空]   0:待交付 1:使用中 2:已结束 3:已作废     **/
		public final static @type(Short.class)  String cslodStatus="cslodStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cslodId="cslod_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cslodHost="cslod_host";
		/** 所属订单 [非空] [CsLongOrder]      **/
		public final static String cslodLongOrder="cslod_long_order";
		/** 预订车辆 [非空] [CsCar]      **/
		public final static String cslodCar="cslod_car";
		/** 每月租金 [非空]       **/
		public final static String cslodPrice="cslod_price";
		/** 交付时里程        **/
		public final static String cslodSetKilo="cslod_set_kilo";
		/** 还车时里程        **/
		public final static String cslodRetKilo="cslod_ret_kilo";
		/** 车辆交付时间        **/
		public final static String cslodSetTime="cslod_set_time";
		/** 车辆归还时间        **/
		public final static String cslodRetTime="cslod_ret_time";
		/** 修改时间 [非空]       **/
		public final static String cslodUpdateTime="cslod_update_time";
		/** 添加时间 [非空]       **/
		public final static String cslodAddTime="cslod_add_time";
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cslodEditor="cslod_editor";
		/** 备注信息        **/
		public final static String cslodRemark="cslod_remark";
		/** 状态 [非空]   0:待交付 1:使用中 2:已结束 3:已作废     **/
		public final static String cslodStatus="cslod_status";
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
				$.Set(name,CsLongOrderDetail.getCsLongOrderDetail(params));
			else
				$.Set(name,CsLongOrderDetail.getCsLongOrderDetailList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取长单明细数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsLongOrderDetail) $.GetRequest("CsLongOrderDetail$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsLongOrderDetail.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsLongOrderDetail.getCsLongOrderDetail(params);
			else
				value = CsLongOrderDetail.getCsLongOrderDetailList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsLongOrderDetail.Get($.add(CsLongOrderDetail.F.cslodId,param));
		else if(!$.empty(param.toString()))
			value = CsLongOrderDetail.get(Long.valueOf(param.toString()));
		$.SetRequest("CsLongOrderDetail$"+param.hashCode(), value);
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
	public void mergeSet(CsLongOrderDetail old){
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