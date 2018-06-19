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

@namespace("service/repair")
public @caption("车辆维修") @table("cs_repair") class CsRepair implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csr_id")   @primary  @note("  ") Long csrId;// 主键 非空     
	private @caption("城市") @column("csr_host")    @relate("$csrHost") @RelateClass(SrvHost.class)  @note("  ") Long csrHost;// 非空     
 	private SrvHost $csrHost;//关联对象[运营城市]
	private @caption("报修描述") @column("csr_profile")    @note("  ") String csrProfile;// 非空     
	private @caption("维修车辆") @column("csr_car")    @relate("$csrCar") @RelateClass(CsCar.class)  @note("  ") Long csrCar;// 非空     
 	private CsCar $csrCar;//关联对象[车辆]
	private @caption("送修地点") @column("csr_adr")    @relate("$csrAdr") @RelateClass(CsRepairAdr.class)  @note("  ") Long csrAdr;// 非空     
 	private CsRepairAdr $csrAdr;//关联对象[维修地点]
	private @caption("事故订单") @column("csr_order")    @relate("$csrOrder") @RelateClass(CsOrder.class)  @note("  ") Long csrOrder;//     
 	private CsOrder $csrOrder;//关联对象[系统订单]
	private @caption("送修人") @column("csr_sender")    @relate("$csrSender") @RelateClass(SrvUser.class)  @note("  ") Long csrSender;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csrSender;//关联对象[用户]
	private @caption("进厂日期") @column("csr_in_date")    @note("  ") Date csrInDate;//     
	private @caption("出厂日期") @column("csr_out_date")    @note("  ") Date csrOutDate;//     
	private @caption("维修描述") @column("csr_descript")    @note("  ") String csrDescript;//     
	private @caption("维修单号") @column("csr_repair_no")    @note("  ") String csrRepairNo;//     
	private @caption("维修费用") @column("csr_money")    @note("  ") Double csrMoney;//     
	private @caption("已结算？") @column("csr_is_settle")    @note("  ") Boolean csrIsSettle;//     
	private @caption("结算日期") @column("csr_settle_time")    @note("  ") Date csrSettleTime;//     
	private @caption("操作人") @column("csr_editor")    @relate("$csrEditor") @RelateClass(SrvUser.class)  @note("  ") Long csrEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csrEditor;//关联对象[用户]
	private @caption("添加时间") @column("csr_add_time")    @note("  ") Date csrAddTime;// 非空     
	private @caption("备注信息") @column("csr_memo")    @note(" textarea:content text:sign  ") String csrMemo;// textarea:content text:sign     
	private @caption("状态") @column("csr_status")    @note(" 0:正在维修 1:维修完成  ") Short csrStatus;// 非空 0:正在维修 1:维修完成     
	
	//默认构造函数
	public CsRepair(){
	
	}
	
	//主键构造函数
	public CsRepair(Long id){
		this.csrId = id;
	}
	
	/**所有字段构造函数 CsRepair(csrHost,csrProfile,csrCar,csrAdr,csrOrder,csrSender,csrInDate,csrOutDate,csrDescript,csrRepairNo,csrMoney,csrIsSettle,csrSettleTime,csrEditor,csrAddTime,csrMemo,csrStatus)
	 CsRepair(
	 	$.getLong("csrHost")//城市 [非空]
	 	,$.getString("csrProfile")//报修描述 [非空]
	 	,$.getLong("csrCar")//维修车辆 [非空]
	 	,$.getLong("csrAdr")//送修地点 [非空]
	 	,$.getLong("csrOrder")//事故订单
	 	,$.getLong("csrSender")//送修人
	 	,$.getDate("csrInDate")//进厂日期
	 	,$.getDate("csrOutDate")//出厂日期
	 	,$.getString("csrDescript")//维修描述
	 	,$.getString("csrRepairNo")//维修单号
	 	,$.getDouble("csrMoney")//维修费用
	 	,$.getBoolean("csrIsSettle")//已结算？
	 	,$.getDate("csrSettleTime")//结算日期
	 	,$.getLong("csrEditor")//操作人 [非空]
	 	,$.getDate("csrAddTime")//添加时间 [非空]
	 	,$.getString("csrMemo")//备注信息
	 	,$.getShort("csrStatus")//状态 [非空]
	 )
	**/
	public CsRepair(Long csrHost,String csrProfile,Long csrCar,Long csrAdr,Long csrOrder,Long csrSender,Date csrInDate,Date csrOutDate,String csrDescript,String csrRepairNo,Double csrMoney,Boolean csrIsSettle,Date csrSettleTime,Long csrEditor,Date csrAddTime,String csrMemo,Short csrStatus){
		this.csrHost=csrHost;
		this.csrProfile=csrProfile;
		this.csrCar=csrCar;
		this.csrAdr=csrAdr;
		this.csrOrder=csrOrder;
		this.csrSender=csrSender;
		this.csrInDate=csrInDate;
		this.csrOutDate=csrOutDate;
		this.csrDescript=csrDescript;
		this.csrRepairNo=csrRepairNo;
		this.csrMoney=csrMoney;
		this.csrIsSettle=csrIsSettle;
		this.csrSettleTime=csrSettleTime;
		this.csrEditor=csrEditor;
		this.csrAddTime=csrAddTime;
		this.csrMemo=csrMemo;
		this.csrStatus=csrStatus;
	}
	
	//设置非空字段
	public CsRepair setNotNull(Long csrHost,String csrProfile,Long csrCar,Long csrAdr,Long csrEditor,Date csrAddTime,Short csrStatus){
		this.csrHost=csrHost;
		this.csrProfile=csrProfile;
		this.csrCar=csrCar;
		this.csrAdr=csrAdr;
		this.csrEditor=csrEditor;
		this.csrAddTime=csrAddTime;
		this.csrStatus=csrStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsRepair csrId(Long csrId){
		this.csrId = csrId;
		this.setSeted(F.csrId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsRepair csrHost(Long csrHost){
		this.csrHost = csrHost;
		this.setSeted(F.csrHost);
		return this;
	}
	/** 报修描述 [非空]       **/
	public CsRepair csrProfile(String csrProfile){
		this.csrProfile = csrProfile;
		this.setSeted(F.csrProfile);
		return this;
	}
	/** 维修车辆 [非空] [CsCar]      **/
	public CsRepair csrCar(Long csrCar){
		this.csrCar = csrCar;
		this.setSeted(F.csrCar);
		return this;
	}
	/** 送修地点 [非空] [CsRepairAdr]      **/
	public CsRepair csrAdr(Long csrAdr){
		this.csrAdr = csrAdr;
		this.setSeted(F.csrAdr);
		return this;
	}
	/** 事故订单  [CsOrder]      **/
	public CsRepair csrOrder(Long csrOrder){
		this.csrOrder = csrOrder;
		this.setSeted(F.csrOrder);
		return this;
	}
	/** 送修人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsRepair csrSender(Long csrSender){
		this.csrSender = csrSender;
		this.setSeted(F.csrSender);
		return this;
	}
	/** 进厂日期        **/
	public CsRepair csrInDate(Date csrInDate){
		this.csrInDate = csrInDate;
		this.setSeted(F.csrInDate);
		return this;
	}
	/** 出厂日期        **/
	public CsRepair csrOutDate(Date csrOutDate){
		this.csrOutDate = csrOutDate;
		this.setSeted(F.csrOutDate);
		return this;
	}
	/** 维修描述        **/
	public CsRepair csrDescript(String csrDescript){
		this.csrDescript = csrDescript;
		this.setSeted(F.csrDescript);
		return this;
	}
	/** 维修单号        **/
	public CsRepair csrRepairNo(String csrRepairNo){
		this.csrRepairNo = csrRepairNo;
		this.setSeted(F.csrRepairNo);
		return this;
	}
	/** 维修费用        **/
	public CsRepair csrMoney(Double csrMoney){
		this.csrMoney = csrMoney;
		this.setSeted(F.csrMoney);
		return this;
	}
	/** 已结算？        **/
	public CsRepair csrIsSettle(Boolean csrIsSettle){
		this.csrIsSettle = csrIsSettle;
		this.setSeted(F.csrIsSettle);
		return this;
	}
	/** 结算日期        **/
	public CsRepair csrSettleTime(Date csrSettleTime){
		this.csrSettleTime = csrSettleTime;
		this.setSeted(F.csrSettleTime);
		return this;
	}
	/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsRepair csrEditor(Long csrEditor){
		this.csrEditor = csrEditor;
		this.setSeted(F.csrEditor);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsRepair csrAddTime(Date csrAddTime){
		this.csrAddTime = csrAddTime;
		this.setSeted(F.csrAddTime);
		return this;
	}
	/** 备注信息    textarea:content text:sign     **/
	public CsRepair csrMemo(String csrMemo){
		this.csrMemo = csrMemo;
		this.setSeted(F.csrMemo);
		return this;
	}
	/** 状态 [非空]   0:正在维修 1:维修完成     **/
	public CsRepair csrStatus(Short csrStatus){
		this.csrStatus = csrStatus;
		this.setSeted(F.csrStatus);
		return this;
	}
	
	
	//克隆对象
	public CsRepair clone(){
		CsRepair clone = new CsRepair();
		clone.csrHost=this.csrHost;
		clone.csrProfile=this.csrProfile;
		clone.csrCar=this.csrCar;
		clone.csrAdr=this.csrAdr;
		clone.csrEditor=this.csrEditor;
		clone.csrAddTime=this.csrAddTime;
		clone.csrStatus=this.csrStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取车辆维修
	 * @param id
	 * @return
	 */
	public static @api CsRepair get(Long id){		
		return getCsRepairById(id);
	}
	/**
	 * 获取所有车辆维修
	 * @return
	 */
	public static @api List<CsRepair> list(Map params,Integer size){
		return getCsRepairList(params,size);
	}
	/**
	 * 获取车辆维修分页
	 * @return
	 */
	public static @api Page<CsRepair> page(int page,int size,Map params){
		return getCsRepairPage(page, size , params);
	}
	/**
	 * 根据查询条件取车辆维修
	 * @param params
	 * @return
	 */
	public static @api CsRepair Get(Map params){
		return getCsRepair(params);
	}
	/**
	 * 获取车辆维修数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsRepairCount(params);
	}
	/**
	 * 获取车辆维修数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsRepairEval(eval,params);
	}
	
	/**
	 * 根据ID取车辆维修
	 * @param id
	 * @return
	 */
	public static @api CsRepair getCsRepairById(Long id){		
		CsRepair csRepair = (CsRepair) $.GetRequest("CsRepair$"+id);
		if(csRepair!=null)
			return csRepair;
		com.ccclubs.service.admin.ICsRepairService csRepairService = $.GetSpringBean("csRepairService");		
		return csRepairService.getCsRepairById(id);
	}
	
	
	/**
	 * 根据ID取车辆维修的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsRepair.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsRepair csRepair = get(id);
		if(csRepair!=null){
			String strValue = csRepair.getCsrProfile$();
			if(!"CsrProfile".equals("CsrProfile"))
				strValue+="("+csRepair.getCsrProfile$()+")";
			MemCache.setValue(CsRepair.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsrProfile$();
		if(!"CsrProfile".equals("CsrProfile"))
			keyValue+="("+this.getCsrProfile$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有车辆维修
	 * @return
	 */
	public static @api List<CsRepair> getCsRepairList(Map params,Integer size){
		com.ccclubs.service.admin.ICsRepairService csRepairService = $.GetSpringBean("csRepairService");
		return csRepairService.getCsRepairList(params, size);
	}
	
	/**
	 * 获取车辆维修分页
	 * @return
	 */
	public static @api Page<CsRepair> getCsRepairPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsRepairService csRepairService = $.GetSpringBean("csRepairService");
		return csRepairService.getCsRepairPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取车辆维修
	 * @param params
	 * @return
	 */
	public static @api CsRepair getCsRepair(Map params){
		com.ccclubs.service.admin.ICsRepairService csRepairService = $.GetSpringBean("csRepairService");
		return csRepairService.getCsRepair(params);
	}
	
	/**
	 * 获取车辆维修数
	 * @return
	 */
	public static @api Long getCsRepairCount(Map params){
		com.ccclubs.service.admin.ICsRepairService csRepairService = $.GetSpringBean("csRepairService");
		return csRepairService.getCsRepairCount(params);
	}
		
		
	/**
	 * 获取车辆维修自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsRepairEval(String eval,Map params){
		com.ccclubs.service.admin.ICsRepairService csRepairService = $.GetSpringBean("csRepairService");
		return csRepairService.getCsRepairEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsRepair(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsRepairService csRepairService = $.GetSpringBean("csRepairService");
		csRepairService.updateCsRepairByConfirm(set, where);
	}
	
	
	/**
	 * 保存车辆维修对象
	 * @param params
	 * @return
	 */
	public CsRepair save(){
		com.ccclubs.service.admin.ICsRepairService csRepairService = $.GetSpringBean("csRepairService");
		if(this.getCsrId()!=null)
			csRepairService.updateCsRepair(this);
		else
			return csRepairService.saveCsRepair(this);
		return this;
	}
	
	
	/**
	 * 更新车辆维修对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsRepairService csRepairService = $.GetSpringBean("csRepairService");
		csRepairService.updateCsRepair$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsRepairService csRepairService = $.GetSpringBean("csRepairService");
		if($.equals($.config("logic_delete"),"true"))
			csRepairService.removeCsRepairById(this.getCsrId());
		else
			csRepairService.deleteCsRepairById(this.getCsrId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsRepairService csRepairService = $.GetSpringBean("csRepairService");
		return csRepairService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsrId(){
		return this.csrId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsrId$(){
		String strValue="";
		 strValue=$.str(this.getCsrId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsrId(Long csrId){
		this.csrId = csrId;
		this.setSeted(F.csrId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsrHost(){
		return this.csrHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsrHost$(){
		String strValue="";
		if(this.getCsrHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsrHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsrHost(Long csrHost){
		this.csrHost = csrHost;
		this.setSeted(F.csrHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csrHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsrHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsrHost()!=null){
 			srvHost = SrvHost.get(this.getCsrHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsrHost(), srvHost);
	 	return srvHost;
	}
	/*******************************报修描述**********************************/	
	/**
	* 报修描述 [非空]      
	**/
	public String getCsrProfile(){
		return this.csrProfile;
	}
	/**
	* 获取报修描述格式化(toString)
	**/
	public String getCsrProfile$(){
		String strValue="";
		 strValue=$.str(this.getCsrProfile());
	 	 return strValue;
	}
	/**
	* 报修描述 [非空]      
	**/
	public void setCsrProfile(String csrProfile){
		this.csrProfile = csrProfile;
		this.setSeted(F.csrProfile);
	}
	/*******************************维修车辆**********************************/	
	/**
	* 维修车辆 [非空] [CsCar]     
	**/
	public Long getCsrCar(){
		return this.csrCar;
	}
	/**
	* 获取维修车辆格式化(toString)
	**/
	public String getCsrCar$(){
		String strValue="";
		if(this.getCsrCar()!=null){
				strValue+=$.str(CsCar.getKeyValue(this.getCsrCar()));
		}			
	 	 return strValue;
	}
	/**
	* 维修车辆 [非空] [CsCar]     
	**/
	public void setCsrCar(Long csrCar){
		this.csrCar = csrCar;
		this.setSeted(F.csrCar);
	}
	/**
	* 获取关联对象[车辆]
	**/	 			
 	public CsCar get$csrCar(){
 		com.ccclubs.model.CsCar csCar = (com.ccclubs.model.CsCar) $.GetRequest("CsCar$"+this.getCsrCar());
 		if(csCar!=null)
			return csCar;
		if(this.getCsrCar()!=null){
 			csCar = CsCar.get(this.getCsrCar());
 		}
 		$.SetRequest("CsCar$"+this.getCsrCar(), csCar);
	 	return csCar;
	}
	/*******************************送修地点**********************************/	
	/**
	* 送修地点 [非空] [CsRepairAdr]     
	**/
	public Long getCsrAdr(){
		return this.csrAdr;
	}
	/**
	* 获取送修地点格式化(toString)
	**/
	public String getCsrAdr$(){
		String strValue="";
		if(this.getCsrAdr()!=null){
				strValue+=$.str(CsRepairAdr.getKeyValue(this.getCsrAdr()));
		}			
	 	 return strValue;
	}
	/**
	* 送修地点 [非空] [CsRepairAdr]     
	**/
	public void setCsrAdr(Long csrAdr){
		this.csrAdr = csrAdr;
		this.setSeted(F.csrAdr);
	}
	/**
	* 获取关联对象[维修地点]
	**/	 			
 	public CsRepairAdr get$csrAdr(){
 		com.ccclubs.model.CsRepairAdr csRepairAdr = (com.ccclubs.model.CsRepairAdr) $.GetRequest("CsRepairAdr$"+this.getCsrAdr());
 		if(csRepairAdr!=null)
			return csRepairAdr;
		if(this.getCsrAdr()!=null){
 			csRepairAdr = CsRepairAdr.get(this.getCsrAdr());
 		}
 		$.SetRequest("CsRepairAdr$"+this.getCsrAdr(), csRepairAdr);
	 	return csRepairAdr;
	}
	/*******************************事故订单**********************************/	
	/**
	* 事故订单  [CsOrder]     
	**/
	public Long getCsrOrder(){
		return this.csrOrder;
	}
	/**
	* 获取事故订单格式化(toString)
	**/
	public String getCsrOrder$(){
		String strValue="";
		if(this.getCsrOrder()!=null){
				strValue+=$.str(CsOrder.getKeyValue(this.getCsrOrder()));
		}			
	 	 return strValue;
	}
	/**
	* 事故订单  [CsOrder]     
	**/
	public void setCsrOrder(Long csrOrder){
		this.csrOrder = csrOrder;
		this.setSeted(F.csrOrder);
	}
	/**
	* 获取关联对象[系统订单]
	**/	 			
 	public CsOrder get$csrOrder(){
 		com.ccclubs.model.CsOrder csOrder = (com.ccclubs.model.CsOrder) $.GetRequest("CsOrder$"+this.getCsrOrder());
 		if(csOrder!=null)
			return csOrder;
		if(this.getCsrOrder()!=null){
 			csOrder = CsOrder.get(this.getCsrOrder());
 		}
 		$.SetRequest("CsOrder$"+this.getCsrOrder(), csOrder);
	 	return csOrder;
	}
	/*******************************送修人**********************************/	
	/**
	* 送修人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsrSender(){
		return this.csrSender;
	}
	/**
	* 获取送修人格式化(toString)
	**/
	public String getCsrSender$(){
		String strValue="";
		if(this.getCsrSender()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsrSender()));
		}			
	 	 return strValue;
	}
	/**
	* 送修人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsrSender(Long csrSender){
		this.csrSender = csrSender;
		this.setSeted(F.csrSender);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csrSender(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsrSender());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsrSender()!=null){
 			srvUser = SrvUser.get(this.getCsrSender());
 		}
 		$.SetRequest("SrvUser$"+this.getCsrSender(), srvUser);
	 	return srvUser;
	}
	/*******************************进厂日期**********************************/	
	/**
	* 进厂日期       
	**/
	public Date getCsrInDate(){
		return this.csrInDate;
	}
	/**
	* 获取进厂日期格式化(toString)
	**/
	public String getCsrInDate$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsrInDate(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 进厂日期       
	**/
	public void setCsrInDate(Date csrInDate){
		this.csrInDate = csrInDate;
		this.setSeted(F.csrInDate);
	}
	/*******************************出厂日期**********************************/	
	/**
	* 出厂日期       
	**/
	public Date getCsrOutDate(){
		return this.csrOutDate;
	}
	/**
	* 获取出厂日期格式化(toString)
	**/
	public String getCsrOutDate$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsrOutDate(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 出厂日期       
	**/
	public void setCsrOutDate(Date csrOutDate){
		this.csrOutDate = csrOutDate;
		this.setSeted(F.csrOutDate);
	}
	/*******************************维修描述**********************************/	
	/**
	* 维修描述       
	**/
	public String getCsrDescript(){
		return this.csrDescript;
	}
	/**
	* 获取维修描述格式化(toString)
	**/
	public String getCsrDescript$(){
		String strValue="";
		 strValue=$.str(this.getCsrDescript());
	 	 return strValue;
	}
	/**
	* 维修描述       
	**/
	public void setCsrDescript(String csrDescript){
		this.csrDescript = csrDescript;
		this.setSeted(F.csrDescript);
	}
	/*******************************维修单号**********************************/	
	/**
	* 维修单号       
	**/
	public String getCsrRepairNo(){
		return this.csrRepairNo;
	}
	/**
	* 获取维修单号格式化(toString)
	**/
	public String getCsrRepairNo$(){
		String strValue="";
		 strValue=$.str(this.getCsrRepairNo());
	 	 return strValue;
	}
	/**
	* 维修单号       
	**/
	public void setCsrRepairNo(String csrRepairNo){
		this.csrRepairNo = csrRepairNo;
		this.setSeted(F.csrRepairNo);
	}
	/*******************************维修费用**********************************/	
	/**
	* 维修费用       
	**/
	public Double getCsrMoney(){
		return this.csrMoney;
	}
	/**
	* 获取维修费用格式化(toString)
	**/
	public String getCsrMoney$(){
		String strValue="";
		 strValue=$.str(this.getCsrMoney());	
	 	 return strValue;
	}
	/**
	* 维修费用       
	**/
	public void setCsrMoney(Double csrMoney){
		this.csrMoney = csrMoney;
		this.setSeted(F.csrMoney);
	}
	/*******************************已结算？**********************************/	
	/**
	* 已结算？       
	**/
	public Boolean getCsrIsSettle(){
		return this.csrIsSettle;
	}
	/**
	* 获取已结算？格式化(toString)
	**/
	public String getCsrIsSettle$(){
		String strValue="";
		 strValue=$.str((this.getCsrIsSettle()!=null && this.getCsrIsSettle())?"是":"否");					 	
	 	 return strValue;
	}
	/**
	* 已结算？       
	**/
	public void setCsrIsSettle(Boolean csrIsSettle){
		this.csrIsSettle = csrIsSettle;
		this.setSeted(F.csrIsSettle);
	}
	/*******************************结算日期**********************************/	
	/**
	* 结算日期       
	**/
	public Date getCsrSettleTime(){
		return this.csrSettleTime;
	}
	/**
	* 获取结算日期格式化(toString)
	**/
	public String getCsrSettleTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsrSettleTime(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 结算日期       
	**/
	public void setCsrSettleTime(Date csrSettleTime){
		this.csrSettleTime = csrSettleTime;
		this.setSeted(F.csrSettleTime);
	}
	/*******************************操作人**********************************/	
	/**
	* 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsrEditor(){
		return this.csrEditor;
	}
	/**
	* 获取操作人格式化(toString)
	**/
	public String getCsrEditor$(){
		String strValue="";
		if(this.getCsrEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsrEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsrEditor(Long csrEditor){
		this.csrEditor = csrEditor;
		this.setSeted(F.csrEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csrEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsrEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsrEditor()!=null){
 			srvUser = SrvUser.get(this.getCsrEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCsrEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsrAddTime(){
		return this.csrAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsrAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsrAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsrAddTime(Date csrAddTime){
		this.csrAddTime = csrAddTime;
		this.setSeted(F.csrAddTime);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息    textarea:content text:sign    
	**/
	public String getCsrMemo(){
		return this.csrMemo;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCsrMemo$(){
		String strValue="";
		 strValue=$.str(this.getCsrMemo());
	 	 return strValue;
	}
	/**
	* 备注信息    textarea:content text:sign    
	**/
	public void setCsrMemo(String csrMemo){
		this.csrMemo = csrMemo;
		this.setSeted(F.csrMemo);
	}
	/**
	* 备注信息对应内部类
	**/
	class CsrMemo{
		String content;//备注内容
		String sign;//签名
		public void setContent(String content){
			this.content=content;
		}
		public String getContent(){
			return this.content;
		}
		public void setSign(String sign){
			this.sign=sign;
		}
		public String getSign(){
			return this.sign;
		}
	}
	/**
	* 获取备注信息格式化列表
	**/ 			
 	public List<CsrMemo> get$csrMemo(){
 		List<CsrMemo> array = new ArrayList();
 		List<Map> list = $.eval(this.csrMemo);
 		for(Map map:list){
 			CsrMemo csrMemo = new CsrMemo();
 			csrMemo.setContent($.parseString(map.get("content")));
 			csrMemo.setSign($.parseString(map.get("sign")));
 			array.add(csrMemo);
 		}
 		return array;
 	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:正在维修 1:维修完成    
	**/
	public Short getCsrStatus(){
		return this.csrStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsrStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsrStatus()),"0"))
			strValue=$.str("正在维修");		 
		 if($.equals($.str(this.getCsrStatus()),"1"))
			strValue=$.str("维修完成");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:正在维修 1:维修完成    
	**/
	public void setCsrStatus(Short csrStatus){
		this.csrStatus = csrStatus;
		this.setSeted(F.csrStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsRepair.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRepair.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRepair.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsRepair.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsRepair.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRepair.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRepair.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsRepair.this);
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
		public M csrId(Object csrId){this.put("csrId", csrId);return this;};
	 	/** and csr_id is null */
 		public M csrIdNull(){if(this.get("csrIdNot")==null)this.put("csrIdNot", "");this.put("csrId", null);return this;};
 		/** not .... */
 		public M csrIdNot(){this.put("csrIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csrHost(Object csrHost){this.put("csrHost", csrHost);return this;};
	 	/** and csr_host is null */
 		public M csrHostNull(){if(this.get("csrHostNot")==null)this.put("csrHostNot", "");this.put("csrHost", null);return this;};
 		/** not .... */
 		public M csrHostNot(){this.put("csrHostNot", "not");return this;};
		/** 报修描述 [非空]       **/
		public M csrProfile(Object csrProfile){this.put("csrProfile", csrProfile);return this;};
	 	/** and csr_profile is null */
 		public M csrProfileNull(){if(this.get("csrProfileNot")==null)this.put("csrProfileNot", "");this.put("csrProfile", null);return this;};
 		/** not .... */
 		public M csrProfileNot(){this.put("csrProfileNot", "not");return this;};
		/** 维修车辆 [非空] [CsCar]      **/
		public M csrCar(Object csrCar){this.put("csrCar", csrCar);return this;};
	 	/** and csr_car is null */
 		public M csrCarNull(){if(this.get("csrCarNot")==null)this.put("csrCarNot", "");this.put("csrCar", null);return this;};
 		/** not .... */
 		public M csrCarNot(){this.put("csrCarNot", "not");return this;};
		public M csrCar$on(CsCar.M value){
			this.put("CsCar", value);
			this.put("csrCar$on", value);
			return this;
		};	
		/** 送修地点 [非空] [CsRepairAdr]      **/
		public M csrAdr(Object csrAdr){this.put("csrAdr", csrAdr);return this;};
	 	/** and csr_adr is null */
 		public M csrAdrNull(){if(this.get("csrAdrNot")==null)this.put("csrAdrNot", "");this.put("csrAdr", null);return this;};
 		/** not .... */
 		public M csrAdrNot(){this.put("csrAdrNot", "not");return this;};
		public M csrAdr$on(CsRepairAdr.M value){
			this.put("CsRepairAdr", value);
			this.put("csrAdr$on", value);
			return this;
		};	
		/** 事故订单  [CsOrder]      **/
		public M csrOrder(Object csrOrder){this.put("csrOrder", csrOrder);return this;};
	 	/** and csr_order is null */
 		public M csrOrderNull(){if(this.get("csrOrderNot")==null)this.put("csrOrderNot", "");this.put("csrOrder", null);return this;};
 		/** not .... */
 		public M csrOrderNot(){this.put("csrOrderNot", "not");return this;};
		public M csrOrder$on(CsOrder.M value){
			this.put("CsOrder", value);
			this.put("csrOrder$on", value);
			return this;
		};	
		/** 送修人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csrSender(Object csrSender){this.put("csrSender", csrSender);return this;};
	 	/** and csr_sender is null */
 		public M csrSenderNull(){if(this.get("csrSenderNot")==null)this.put("csrSenderNot", "");this.put("csrSender", null);return this;};
 		/** not .... */
 		public M csrSenderNot(){this.put("csrSenderNot", "not");return this;};
		/** 进厂日期        **/
		public M csrInDate(Object csrInDate){this.put("csrInDate", csrInDate);return this;};
	 	/** and csr_in_date is null */
 		public M csrInDateNull(){if(this.get("csrInDateNot")==null)this.put("csrInDateNot", "");this.put("csrInDate", null);return this;};
 		/** not .... */
 		public M csrInDateNot(){this.put("csrInDateNot", "not");return this;};
 		/** and csr_in_date >= ? */
 		public M csrInDateStart(Object start){this.put("csrInDateStart", start);return this;};			
 		/** and csr_in_date <= ? */
 		public M csrInDateEnd(Object end){this.put("csrInDateEnd", end);return this;};
		/** 出厂日期        **/
		public M csrOutDate(Object csrOutDate){this.put("csrOutDate", csrOutDate);return this;};
	 	/** and csr_out_date is null */
 		public M csrOutDateNull(){if(this.get("csrOutDateNot")==null)this.put("csrOutDateNot", "");this.put("csrOutDate", null);return this;};
 		/** not .... */
 		public M csrOutDateNot(){this.put("csrOutDateNot", "not");return this;};
 		/** and csr_out_date >= ? */
 		public M csrOutDateStart(Object start){this.put("csrOutDateStart", start);return this;};			
 		/** and csr_out_date <= ? */
 		public M csrOutDateEnd(Object end){this.put("csrOutDateEnd", end);return this;};
		/** 维修描述        **/
		public M csrDescript(Object csrDescript){this.put("csrDescript", csrDescript);return this;};
	 	/** and csr_descript is null */
 		public M csrDescriptNull(){if(this.get("csrDescriptNot")==null)this.put("csrDescriptNot", "");this.put("csrDescript", null);return this;};
 		/** not .... */
 		public M csrDescriptNot(){this.put("csrDescriptNot", "not");return this;};
		/** 维修单号        **/
		public M csrRepairNo(Object csrRepairNo){this.put("csrRepairNo", csrRepairNo);return this;};
	 	/** and csr_repair_no is null */
 		public M csrRepairNoNull(){if(this.get("csrRepairNoNot")==null)this.put("csrRepairNoNot", "");this.put("csrRepairNo", null);return this;};
 		/** not .... */
 		public M csrRepairNoNot(){this.put("csrRepairNoNot", "not");return this;};
		/** 维修费用        **/
		public M csrMoney(Object csrMoney){this.put("csrMoney", csrMoney);return this;};
	 	/** and csr_money is null */
 		public M csrMoneyNull(){if(this.get("csrMoneyNot")==null)this.put("csrMoneyNot", "");this.put("csrMoney", null);return this;};
 		/** not .... */
 		public M csrMoneyNot(){this.put("csrMoneyNot", "not");return this;};
		/** and csr_money >= ? */
		public M csrMoneyMin(Object min){this.put("csrMoneyMin", min);return this;};
		/** and csr_money <= ? */
		public M csrMoneyMax(Object max){this.put("csrMoneyMax", max);return this;};
		/** 已结算？        **/
		public M csrIsSettle(Object csrIsSettle){this.put("csrIsSettle", csrIsSettle);return this;};
	 	/** and csr_is_settle is null */
 		public M csrIsSettleNull(){if(this.get("csrIsSettleNot")==null)this.put("csrIsSettleNot", "");this.put("csrIsSettle", null);return this;};
 		/** not .... */
 		public M csrIsSettleNot(){this.put("csrIsSettleNot", "not");return this;};
		/** 结算日期        **/
		public M csrSettleTime(Object csrSettleTime){this.put("csrSettleTime", csrSettleTime);return this;};
	 	/** and csr_settle_time is null */
 		public M csrSettleTimeNull(){if(this.get("csrSettleTimeNot")==null)this.put("csrSettleTimeNot", "");this.put("csrSettleTime", null);return this;};
 		/** not .... */
 		public M csrSettleTimeNot(){this.put("csrSettleTimeNot", "not");return this;};
 		/** and csr_settle_time >= ? */
 		public M csrSettleTimeStart(Object start){this.put("csrSettleTimeStart", start);return this;};			
 		/** and csr_settle_time <= ? */
 		public M csrSettleTimeEnd(Object end){this.put("csrSettleTimeEnd", end);return this;};
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csrEditor(Object csrEditor){this.put("csrEditor", csrEditor);return this;};
	 	/** and csr_editor is null */
 		public M csrEditorNull(){if(this.get("csrEditorNot")==null)this.put("csrEditorNot", "");this.put("csrEditor", null);return this;};
 		/** not .... */
 		public M csrEditorNot(){this.put("csrEditorNot", "not");return this;};
		/** 添加时间 [非空]       **/
		public M csrAddTime(Object csrAddTime){this.put("csrAddTime", csrAddTime);return this;};
	 	/** and csr_add_time is null */
 		public M csrAddTimeNull(){if(this.get("csrAddTimeNot")==null)this.put("csrAddTimeNot", "");this.put("csrAddTime", null);return this;};
 		/** not .... */
 		public M csrAddTimeNot(){this.put("csrAddTimeNot", "not");return this;};
 		/** and csr_add_time >= ? */
 		public M csrAddTimeStart(Object start){this.put("csrAddTimeStart", start);return this;};			
 		/** and csr_add_time <= ? */
 		public M csrAddTimeEnd(Object end){this.put("csrAddTimeEnd", end);return this;};
		/** 备注信息    textarea:content text:sign     **/
		public M csrMemo(Object csrMemo){this.put("csrMemo", csrMemo);return this;};
	 	/** and csr_memo is null */
 		public M csrMemoNull(){if(this.get("csrMemoNot")==null)this.put("csrMemoNot", "");this.put("csrMemo", null);return this;};
 		/** not .... */
 		public M csrMemoNot(){this.put("csrMemoNot", "not");return this;};
		/** 状态 [非空]   0:正在维修 1:维修完成     **/
		public M csrStatus(Object csrStatus){this.put("csrStatus", csrStatus);return this;};
	 	/** and csr_status is null */
 		public M csrStatusNull(){if(this.get("csrStatusNot")==null)this.put("csrStatusNot", "");this.put("csrStatus", null);return this;};
 		/** not .... */
 		public M csrStatusNot(){this.put("csrStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有车辆维修 **/
		public @api List<CsRepair> list(Integer size){
			return getCsRepairList(this,size);
		}
		/** 获取车辆维修分页 **/
		public @api Page<CsRepair> page(int page,int size){
			return getCsRepairPage(page, size , this);
		}
		/** 根据查询条件取车辆维修 **/
		public @api CsRepair get(){
			return getCsRepair(this);
		}
		/** 获取车辆维修数 **/
		public @api Long count(){
			return getCsRepairCount(this);
		}
		/** 获取车辆维修表达式 **/
		public @api <T> T eval(String strEval){
			return getCsRepairEval(strEval,this);
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
			updateCsRepair(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csrId="csrId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csrHost="csrHost";
		/** 报修描述 [非空]       **/
		public final static @type(String.class) @like String csrProfile="csrProfile";
		/** 维修车辆 [非空] [CsCar]      **/
		public final static @type(Long.class)  String csrCar="csrCar";
		/** 送修地点 [非空] [CsRepairAdr]      **/
		public final static @type(Long.class)  String csrAdr="csrAdr";
		/** 事故订单  [CsOrder]      **/
		public final static @type(Long.class)  String csrOrder="csrOrder";
		/** 送修人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csrSender="csrSender";
		/** 进厂日期        **/
		public final static @type(Date.class)  String csrInDate="csrInDate";
	 	/** and csr_in_date >= ? */
 		public final static @type(Date.class) String csrInDateStart="csrInDateStart";
 		/** and csr_in_date <= ? */
 		public final static @type(Date.class) String csrInDateEnd="csrInDateEnd";
		/** 出厂日期        **/
		public final static @type(Date.class)  String csrOutDate="csrOutDate";
	 	/** and csr_out_date >= ? */
 		public final static @type(Date.class) String csrOutDateStart="csrOutDateStart";
 		/** and csr_out_date <= ? */
 		public final static @type(Date.class) String csrOutDateEnd="csrOutDateEnd";
		/** 维修描述        **/
		public final static @type(String.class) @like String csrDescript="csrDescript";
		/** 维修单号        **/
		public final static @type(String.class) @like String csrRepairNo="csrRepairNo";
		/** 维修费用        **/
		public final static @type(Double.class)  String csrMoney="csrMoney";
		/** and csr_money >= ? */
		public final static @type(Double.class) String csrMoneyMin="csrMoneyMin";
		/** and csr_money <= ? */
		public final static @type(Double.class) String csrMoneyMax="csrMoneyMax";
		/** 已结算？        **/
		public final static @type(Boolean.class)  String csrIsSettle="csrIsSettle";
		/** 结算日期        **/
		public final static @type(Date.class)  String csrSettleTime="csrSettleTime";
	 	/** and csr_settle_time >= ? */
 		public final static @type(Date.class) String csrSettleTimeStart="csrSettleTimeStart";
 		/** and csr_settle_time <= ? */
 		public final static @type(Date.class) String csrSettleTimeEnd="csrSettleTimeEnd";
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csrEditor="csrEditor";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csrAddTime="csrAddTime";
	 	/** and csr_add_time >= ? */
 		public final static @type(Date.class) String csrAddTimeStart="csrAddTimeStart";
 		/** and csr_add_time <= ? */
 		public final static @type(Date.class) String csrAddTimeEnd="csrAddTimeEnd";
		/** 备注信息    textarea:content text:sign     **/
		public final static @type(String.class)  String csrMemo="csrMemo";
		/** 状态 [非空]   0:正在维修 1:维修完成     **/
		public final static @type(Short.class)  String csrStatus="csrStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csrId="csr_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csrHost="csr_host";
		/** 报修描述 [非空]       **/
		public final static String csrProfile="csr_profile";
		/** 维修车辆 [非空] [CsCar]      **/
		public final static String csrCar="csr_car";
		/** 送修地点 [非空] [CsRepairAdr]      **/
		public final static String csrAdr="csr_adr";
		/** 事故订单  [CsOrder]      **/
		public final static String csrOrder="csr_order";
		/** 送修人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csrSender="csr_sender";
		/** 进厂日期        **/
		public final static String csrInDate="csr_in_date";
		/** 出厂日期        **/
		public final static String csrOutDate="csr_out_date";
		/** 维修描述        **/
		public final static String csrDescript="csr_descript";
		/** 维修单号        **/
		public final static String csrRepairNo="csr_repair_no";
		/** 维修费用        **/
		public final static String csrMoney="csr_money";
		/** 已结算？        **/
		public final static String csrIsSettle="csr_is_settle";
		/** 结算日期        **/
		public final static String csrSettleTime="csr_settle_time";
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csrEditor="csr_editor";
		/** 添加时间 [非空]       **/
		public final static String csrAddTime="csr_add_time";
		/** 备注信息    textarea:content text:sign     **/
		public final static String csrMemo="csr_memo";
		/** 状态 [非空]   0:正在维修 1:维修完成     **/
		public final static String csrStatus="csr_status";
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
				$.Set(name,CsRepair.getCsRepair(params));
			else
				$.Set(name,CsRepair.getCsRepairList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取车辆维修数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsRepair) $.GetRequest("CsRepair$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsRepair.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsRepair.getCsRepair(params);
			else
				value = CsRepair.getCsRepairList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsRepair.Get($.add(CsRepair.F.csrId,param));
		else if(!$.empty(param.toString()))
			value = CsRepair.get(Long.valueOf(param.toString()));
		$.SetRequest("CsRepair$"+param.hashCode(), value);
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
	public void mergeSet(CsRepair old){
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