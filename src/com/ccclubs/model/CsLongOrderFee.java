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

@namespace("service/longorderfee")
public @caption("长单缴费") @table("cs_long_order_fee") class CsLongOrderFee implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cslof_id")   @primary  @note("  ") Long cslofId;// 主键 非空     
	private @caption("城市") @column("cslof_host")    @relate("$cslofHost") @RelateClass(SrvHost.class)  @note("  ") Long cslofHost;// 非空     
 	private SrvHost $cslofHost;//关联对象[运营城市]
	private @caption("所属长租") @column("cslof_longorder")    @relate("$cslofLongorder") @RelateClass(CsLongOrder.class)  @note("  ") Long cslofLongorder;// 非空     
 	private CsLongOrder $cslofLongorder;//关联对象[长租订单]
	private @caption("项目名称") @column("cslof_name")    @note("  ") String cslofName;// 非空     
	private @caption("费用类型") @column("cslof_type")    @note(" 0:押金 1:租金  ") Short cslofType;// 非空 0:押金 1:租金     
	private @caption("交费方式") @column("cslof_pay_type")    @note(" 0:现金支付 1:转帐支付 2:余额支付  ") Short cslofPayType;// 非空 0:现金支付 1:转帐支付 2:余额支付     
	private @caption("费用金额") @column("cslof_ammount")    @note("  ") Double cslofAmmount;// 非空     
	private @caption("备注信息") @column("cslof_remark")    @note("  ") String cslofRemark;// 非空     
	private @caption("修改时间") @column("cslof_update_time")    @note("  ") Date cslofUpdateTime;// 非空     
	private @caption("添加时间") @column("cslof_add_time")    @note("  ") Date cslofAddTime;// 非空     
	private @caption("入帐信息") @column("cslof_state_info")    @note("  ") String cslofStateInfo;//     
	private @caption("财务确认") @column("cslof_state")    @note(" 0:待确认 1:已确认  ") Short cslofState;// 非空 0:待确认 1:已确认     
	private @caption("状态") @column("cslof_status")    @note(" 0:已交款 1:已退款  ") Short cslofStatus;// 非空 0:已交款 1:已退款     
	
	//默认构造函数
	public CsLongOrderFee(){
	
	}
	
	//主键构造函数
	public CsLongOrderFee(Long id){
		this.cslofId = id;
	}
	
	/**所有字段构造函数 CsLongOrderFee(cslofHost,cslofLongorder,cslofName,cslofType,cslofPayType,cslofAmmount,cslofRemark,cslofUpdateTime,cslofAddTime,cslofStateInfo,cslofState,cslofStatus)
	 CsLongOrderFee(
	 	$.getLong("cslofHost")//城市 [非空]
	 	,$.getLong("cslofLongorder")//所属长租 [非空]
	 	,$.getString("cslofName")//项目名称 [非空]
	 	,$.getShort("cslofType")//费用类型 [非空]
	 	,$.getShort("cslofPayType")//交费方式 [非空]
	 	,$.getDouble("cslofAmmount")//费用金额 [非空]
	 	,$.getString("cslofRemark")//备注信息 [非空]
	 	,$.getDate("cslofUpdateTime")//修改时间 [非空]
	 	,$.getDate("cslofAddTime")//添加时间 [非空]
	 	,$.getString("cslofStateInfo")//入帐信息
	 	,$.getShort("cslofState")//财务确认 [非空]
	 	,$.getShort("cslofStatus")//状态 [非空]
	 )
	**/
	public CsLongOrderFee(Long cslofHost,Long cslofLongorder,String cslofName,Short cslofType,Short cslofPayType,Double cslofAmmount,String cslofRemark,Date cslofUpdateTime,Date cslofAddTime,String cslofStateInfo,Short cslofState,Short cslofStatus){
		this.cslofHost=cslofHost;
		this.cslofLongorder=cslofLongorder;
		this.cslofName=cslofName;
		this.cslofType=cslofType;
		this.cslofPayType=cslofPayType;
		this.cslofAmmount=cslofAmmount;
		this.cslofRemark=cslofRemark;
		this.cslofUpdateTime=cslofUpdateTime;
		this.cslofAddTime=cslofAddTime;
		this.cslofStateInfo=cslofStateInfo;
		this.cslofState=cslofState;
		this.cslofStatus=cslofStatus;
	}
	
	//设置非空字段
	public CsLongOrderFee setNotNull(Long cslofHost,Long cslofLongorder,String cslofName,Short cslofType,Short cslofPayType,Double cslofAmmount,String cslofRemark,Date cslofUpdateTime,Date cslofAddTime,Short cslofState,Short cslofStatus){
		this.cslofHost=cslofHost;
		this.cslofLongorder=cslofLongorder;
		this.cslofName=cslofName;
		this.cslofType=cslofType;
		this.cslofPayType=cslofPayType;
		this.cslofAmmount=cslofAmmount;
		this.cslofRemark=cslofRemark;
		this.cslofUpdateTime=cslofUpdateTime;
		this.cslofAddTime=cslofAddTime;
		this.cslofState=cslofState;
		this.cslofStatus=cslofStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsLongOrderFee cslofId(Long cslofId){
		this.cslofId = cslofId;
		this.setSeted(F.cslofId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsLongOrderFee cslofHost(Long cslofHost){
		this.cslofHost = cslofHost;
		this.setSeted(F.cslofHost);
		return this;
	}
	/** 所属长租 [非空] [CsLongOrder]      **/
	public CsLongOrderFee cslofLongorder(Long cslofLongorder){
		this.cslofLongorder = cslofLongorder;
		this.setSeted(F.cslofLongorder);
		return this;
	}
	/** 项目名称 [非空]       **/
	public CsLongOrderFee cslofName(String cslofName){
		this.cslofName = cslofName;
		this.setSeted(F.cslofName);
		return this;
	}
	/** 费用类型 [非空]   0:押金 1:租金     **/
	public CsLongOrderFee cslofType(Short cslofType){
		this.cslofType = cslofType;
		this.setSeted(F.cslofType);
		return this;
	}
	/** 交费方式 [非空]   0:现金支付 1:转帐支付 2:余额支付     **/
	public CsLongOrderFee cslofPayType(Short cslofPayType){
		this.cslofPayType = cslofPayType;
		this.setSeted(F.cslofPayType);
		return this;
	}
	/** 费用金额 [非空]       **/
	public CsLongOrderFee cslofAmmount(Double cslofAmmount){
		this.cslofAmmount = cslofAmmount;
		this.setSeted(F.cslofAmmount);
		return this;
	}
	/** 备注信息 [非空]       **/
	public CsLongOrderFee cslofRemark(String cslofRemark){
		this.cslofRemark = cslofRemark;
		this.setSeted(F.cslofRemark);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsLongOrderFee cslofUpdateTime(Date cslofUpdateTime){
		this.cslofUpdateTime = cslofUpdateTime;
		this.setSeted(F.cslofUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsLongOrderFee cslofAddTime(Date cslofAddTime){
		this.cslofAddTime = cslofAddTime;
		this.setSeted(F.cslofAddTime);
		return this;
	}
	/** 入帐信息        **/
	public CsLongOrderFee cslofStateInfo(String cslofStateInfo){
		this.cslofStateInfo = cslofStateInfo;
		this.setSeted(F.cslofStateInfo);
		return this;
	}
	/** 财务确认 [非空]   0:待确认 1:已确认     **/
	public CsLongOrderFee cslofState(Short cslofState){
		this.cslofState = cslofState;
		this.setSeted(F.cslofState);
		return this;
	}
	/** 状态 [非空]   0:已交款 1:已退款     **/
	public CsLongOrderFee cslofStatus(Short cslofStatus){
		this.cslofStatus = cslofStatus;
		this.setSeted(F.cslofStatus);
		return this;
	}
	
	
	//克隆对象
	public CsLongOrderFee clone(){
		CsLongOrderFee clone = new CsLongOrderFee();
		clone.cslofHost=this.cslofHost;
		clone.cslofLongorder=this.cslofLongorder;
		clone.cslofName=this.cslofName;
		clone.cslofType=this.cslofType;
		clone.cslofPayType=this.cslofPayType;
		clone.cslofAmmount=this.cslofAmmount;
		clone.cslofRemark=this.cslofRemark;
		clone.cslofUpdateTime=this.cslofUpdateTime;
		clone.cslofAddTime=this.cslofAddTime;
		clone.cslofState=this.cslofState;
		clone.cslofStatus=this.cslofStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取长单缴费
	 * @param id
	 * @return
	 */
	public static @api CsLongOrderFee get(Long id){		
		return getCsLongOrderFeeById(id);
	}
	/**
	 * 获取所有长单缴费
	 * @return
	 */
	public static @api List<CsLongOrderFee> list(Map params,Integer size){
		return getCsLongOrderFeeList(params,size);
	}
	/**
	 * 获取长单缴费分页
	 * @return
	 */
	public static @api Page<CsLongOrderFee> page(int page,int size,Map params){
		return getCsLongOrderFeePage(page, size , params);
	}
	/**
	 * 根据查询条件取长单缴费
	 * @param params
	 * @return
	 */
	public static @api CsLongOrderFee Get(Map params){
		return getCsLongOrderFee(params);
	}
	/**
	 * 获取长单缴费数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsLongOrderFeeCount(params);
	}
	/**
	 * 获取长单缴费数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsLongOrderFeeEval(eval,params);
	}
	
	/**
	 * 根据ID取长单缴费
	 * @param id
	 * @return
	 */
	public static @api CsLongOrderFee getCsLongOrderFeeById(Long id){		
		CsLongOrderFee csLongOrderFee = (CsLongOrderFee) $.GetRequest("CsLongOrderFee$"+id);
		if(csLongOrderFee!=null)
			return csLongOrderFee;
		com.ccclubs.service.admin.ICsLongOrderFeeService csLongOrderFeeService = $.GetSpringBean("csLongOrderFeeService");		
		return csLongOrderFeeService.getCsLongOrderFeeById(id);
	}
	
	
	/**
	 * 根据ID取长单缴费的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsLongOrderFee.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsLongOrderFee csLongOrderFee = get(id);
		if(csLongOrderFee!=null){
			String strValue = csLongOrderFee.getCslofName$();
			if(!"CslofName".equals("CslofName"))
				strValue+="("+csLongOrderFee.getCslofName$()+")";
			MemCache.setValue(CsLongOrderFee.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCslofName$();
		if(!"CslofName".equals("CslofName"))
			keyValue+="("+this.getCslofName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有长单缴费
	 * @return
	 */
	public static @api List<CsLongOrderFee> getCsLongOrderFeeList(Map params,Integer size){
		com.ccclubs.service.admin.ICsLongOrderFeeService csLongOrderFeeService = $.GetSpringBean("csLongOrderFeeService");
		return csLongOrderFeeService.getCsLongOrderFeeList(params, size);
	}
	
	/**
	 * 获取长单缴费分页
	 * @return
	 */
	public static @api Page<CsLongOrderFee> getCsLongOrderFeePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsLongOrderFeeService csLongOrderFeeService = $.GetSpringBean("csLongOrderFeeService");
		return csLongOrderFeeService.getCsLongOrderFeePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取长单缴费
	 * @param params
	 * @return
	 */
	public static @api CsLongOrderFee getCsLongOrderFee(Map params){
		com.ccclubs.service.admin.ICsLongOrderFeeService csLongOrderFeeService = $.GetSpringBean("csLongOrderFeeService");
		return csLongOrderFeeService.getCsLongOrderFee(params);
	}
	
	/**
	 * 获取长单缴费数
	 * @return
	 */
	public static @api Long getCsLongOrderFeeCount(Map params){
		com.ccclubs.service.admin.ICsLongOrderFeeService csLongOrderFeeService = $.GetSpringBean("csLongOrderFeeService");
		return csLongOrderFeeService.getCsLongOrderFeeCount(params);
	}
		
		
	/**
	 * 获取长单缴费自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsLongOrderFeeEval(String eval,Map params){
		com.ccclubs.service.admin.ICsLongOrderFeeService csLongOrderFeeService = $.GetSpringBean("csLongOrderFeeService");
		return csLongOrderFeeService.getCsLongOrderFeeEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsLongOrderFee(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsLongOrderFeeService csLongOrderFeeService = $.GetSpringBean("csLongOrderFeeService");
		csLongOrderFeeService.updateCsLongOrderFeeByConfirm(set, where);
	}
	
	
	/**
	 * 保存长单缴费对象
	 * @param params
	 * @return
	 */
	public CsLongOrderFee save(){
		com.ccclubs.service.admin.ICsLongOrderFeeService csLongOrderFeeService = $.GetSpringBean("csLongOrderFeeService");
		if(this.getCslofId()!=null)
			csLongOrderFeeService.updateCsLongOrderFee(this);
		else
			return csLongOrderFeeService.saveCsLongOrderFee(this);
		return this;
	}
	
	
	/**
	 * 更新长单缴费对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsLongOrderFeeService csLongOrderFeeService = $.GetSpringBean("csLongOrderFeeService");
		csLongOrderFeeService.updateCsLongOrderFee$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsLongOrderFeeService csLongOrderFeeService = $.GetSpringBean("csLongOrderFeeService");
		if($.equals($.config("logic_delete"),"true"))
			csLongOrderFeeService.removeCsLongOrderFeeById(this.getCslofId());
		else
			csLongOrderFeeService.deleteCsLongOrderFeeById(this.getCslofId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsLongOrderFeeService csLongOrderFeeService = $.GetSpringBean("csLongOrderFeeService");
		return csLongOrderFeeService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCslofId(){
		return this.cslofId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCslofId$(){
		String strValue="";
		 strValue=$.str(this.getCslofId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCslofId(Long cslofId){
		this.cslofId = cslofId;
		this.setSeted(F.cslofId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCslofHost(){
		return this.cslofHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCslofHost$(){
		String strValue="";
		if(this.getCslofHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCslofHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCslofHost(Long cslofHost){
		this.cslofHost = cslofHost;
		this.setSeted(F.cslofHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cslofHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCslofHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCslofHost()!=null){
 			srvHost = SrvHost.get(this.getCslofHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCslofHost(), srvHost);
	 	return srvHost;
	}
	/*******************************所属长租**********************************/	
	/**
	* 所属长租 [非空] [CsLongOrder]     
	**/
	public Long getCslofLongorder(){
		return this.cslofLongorder;
	}
	/**
	* 获取所属长租格式化(toString)
	**/
	public String getCslofLongorder$(){
		String strValue="";
		if(this.getCslofLongorder()!=null){
				strValue+=$.str(CsLongOrder.getKeyValue(this.getCslofLongorder()));
		}			
	 	 return strValue;
	}
	/**
	* 所属长租 [非空] [CsLongOrder]     
	**/
	public void setCslofLongorder(Long cslofLongorder){
		this.cslofLongorder = cslofLongorder;
		this.setSeted(F.cslofLongorder);
	}
	/**
	* 获取关联对象[长租订单]
	**/	 			
 	public CsLongOrder get$cslofLongorder(){
 		com.ccclubs.model.CsLongOrder csLongOrder = (com.ccclubs.model.CsLongOrder) $.GetRequest("CsLongOrder$"+this.getCslofLongorder());
 		if(csLongOrder!=null)
			return csLongOrder;
		if(this.getCslofLongorder()!=null){
 			csLongOrder = CsLongOrder.get(this.getCslofLongorder());
 		}
 		$.SetRequest("CsLongOrder$"+this.getCslofLongorder(), csLongOrder);
	 	return csLongOrder;
	}
	/*******************************项目名称**********************************/	
	/**
	* 项目名称 [非空]      
	**/
	public String getCslofName(){
		return this.cslofName;
	}
	/**
	* 获取项目名称格式化(toString)
	**/
	public String getCslofName$(){
		String strValue="";
		 strValue=$.str(this.getCslofName());
	 	 return strValue;
	}
	/**
	* 项目名称 [非空]      
	**/
	public void setCslofName(String cslofName){
		this.cslofName = cslofName;
		this.setSeted(F.cslofName);
	}
	/*******************************费用类型**********************************/	
	/**
	* 费用类型 [非空]   0:押金 1:租金    
	**/
	public Short getCslofType(){
		return this.cslofType;
	}
	/**
	* 获取费用类型格式化(toString)
	**/
	public String getCslofType$(){
		String strValue="";
		 if($.equals($.str(this.getCslofType()),"0"))
			strValue=$.str("押金");		 
		 if($.equals($.str(this.getCslofType()),"1"))
			strValue=$.str("租金");		 
	 	 return strValue;
	}
	/**
	* 费用类型 [非空]   0:押金 1:租金    
	**/
	public void setCslofType(Short cslofType){
		this.cslofType = cslofType;
		this.setSeted(F.cslofType);
	}
	/*******************************交费方式**********************************/	
	/**
	* 交费方式 [非空]   0:现金支付 1:转帐支付 2:余额支付    
	**/
	public Short getCslofPayType(){
		return this.cslofPayType;
	}
	/**
	* 获取交费方式格式化(toString)
	**/
	public String getCslofPayType$(){
		String strValue="";
		 if($.equals($.str(this.getCslofPayType()),"0"))
			strValue=$.str("现金支付");		 
		 if($.equals($.str(this.getCslofPayType()),"1"))
			strValue=$.str("转帐支付");		 
		 if($.equals($.str(this.getCslofPayType()),"2"))
			strValue=$.str("余额支付");		 
	 	 return strValue;
	}
	/**
	* 交费方式 [非空]   0:现金支付 1:转帐支付 2:余额支付    
	**/
	public void setCslofPayType(Short cslofPayType){
		this.cslofPayType = cslofPayType;
		this.setSeted(F.cslofPayType);
	}
	/*******************************费用金额**********************************/	
	/**
	* 费用金额 [非空]      
	**/
	public Double getCslofAmmount(){
		return this.cslofAmmount;
	}
	/**
	* 获取费用金额格式化(toString)
	**/
	public String getCslofAmmount$(){
		String strValue="";
		 strValue=$.str(this.getCslofAmmount());	
	 	 return strValue;
	}
	/**
	* 费用金额 [非空]      
	**/
	public void setCslofAmmount(Double cslofAmmount){
		this.cslofAmmount = cslofAmmount;
		this.setSeted(F.cslofAmmount);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息 [非空]      
	**/
	public String getCslofRemark(){
		return this.cslofRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCslofRemark$(){
		String strValue="";
		 strValue=$.str(this.getCslofRemark());
	 	 return strValue;
	}
	/**
	* 备注信息 [非空]      
	**/
	public void setCslofRemark(String cslofRemark){
		this.cslofRemark = cslofRemark;
		this.setSeted(F.cslofRemark);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCslofUpdateTime(){
		return this.cslofUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCslofUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCslofUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCslofUpdateTime(Date cslofUpdateTime){
		this.cslofUpdateTime = cslofUpdateTime;
		this.setSeted(F.cslofUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCslofAddTime(){
		return this.cslofAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCslofAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCslofAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCslofAddTime(Date cslofAddTime){
		this.cslofAddTime = cslofAddTime;
		this.setSeted(F.cslofAddTime);
	}
	/*******************************入帐信息**********************************/	
	/**
	* 入帐信息       
	**/
	public String getCslofStateInfo(){
		return this.cslofStateInfo;
	}
	/**
	* 获取入帐信息格式化(toString)
	**/
	public String getCslofStateInfo$(){
		String strValue="";
		 strValue=$.str(this.getCslofStateInfo());
	 	 return strValue;
	}
	/**
	* 入帐信息       
	**/
	public void setCslofStateInfo(String cslofStateInfo){
		this.cslofStateInfo = cslofStateInfo;
		this.setSeted(F.cslofStateInfo);
	}
	/*******************************财务确认**********************************/	
	/**
	* 财务确认 [非空]   0:待确认 1:已确认    
	**/
	public Short getCslofState(){
		return this.cslofState;
	}
	/**
	* 获取财务确认格式化(toString)
	**/
	public String getCslofState$(){
		String strValue="";
		 if($.equals($.str(this.getCslofState()),"0"))
			strValue=$.str("待确认");		 
		 if($.equals($.str(this.getCslofState()),"1"))
			strValue=$.str("已确认");		 
	 	 return strValue;
	}
	/**
	* 财务确认 [非空]   0:待确认 1:已确认    
	**/
	public void setCslofState(Short cslofState){
		this.cslofState = cslofState;
		this.setSeted(F.cslofState);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:已交款 1:已退款    
	**/
	public Short getCslofStatus(){
		return this.cslofStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCslofStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCslofStatus()),"0"))
			strValue=$.str("已交款");		 
		 if($.equals($.str(this.getCslofStatus()),"1"))
			strValue=$.str("已退款");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:已交款 1:已退款    
	**/
	public void setCslofStatus(Short cslofStatus){
		this.cslofStatus = cslofStatus;
		this.setSeted(F.cslofStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsLongOrderFee.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsLongOrderFee.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsLongOrderFee.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsLongOrderFee.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsLongOrderFee.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsLongOrderFee.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsLongOrderFee.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsLongOrderFee.this);
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
		public M cslofId(Object cslofId){this.put("cslofId", cslofId);return this;};
	 	/** and cslof_id is null */
 		public M cslofIdNull(){if(this.get("cslofIdNot")==null)this.put("cslofIdNot", "");this.put("cslofId", null);return this;};
 		/** not .... */
 		public M cslofIdNot(){this.put("cslofIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M cslofHost(Object cslofHost){this.put("cslofHost", cslofHost);return this;};
	 	/** and cslof_host is null */
 		public M cslofHostNull(){if(this.get("cslofHostNot")==null)this.put("cslofHostNot", "");this.put("cslofHost", null);return this;};
 		/** not .... */
 		public M cslofHostNot(){this.put("cslofHostNot", "not");return this;};
		/** 所属长租 [非空] [CsLongOrder]      **/
		public M cslofLongorder(Object cslofLongorder){this.put("cslofLongorder", cslofLongorder);return this;};
	 	/** and cslof_longorder is null */
 		public M cslofLongorderNull(){if(this.get("cslofLongorderNot")==null)this.put("cslofLongorderNot", "");this.put("cslofLongorder", null);return this;};
 		/** not .... */
 		public M cslofLongorderNot(){this.put("cslofLongorderNot", "not");return this;};
		public M cslofLongorder$on(CsLongOrder.M value){
			this.put("CsLongOrder", value);
			this.put("cslofLongorder$on", value);
			return this;
		};	
		/** 项目名称 [非空]       **/
		public M cslofName(Object cslofName){this.put("cslofName", cslofName);return this;};
	 	/** and cslof_name is null */
 		public M cslofNameNull(){if(this.get("cslofNameNot")==null)this.put("cslofNameNot", "");this.put("cslofName", null);return this;};
 		/** not .... */
 		public M cslofNameNot(){this.put("cslofNameNot", "not");return this;};
		/** 费用类型 [非空]   0:押金 1:租金     **/
		public M cslofType(Object cslofType){this.put("cslofType", cslofType);return this;};
	 	/** and cslof_type is null */
 		public M cslofTypeNull(){if(this.get("cslofTypeNot")==null)this.put("cslofTypeNot", "");this.put("cslofType", null);return this;};
 		/** not .... */
 		public M cslofTypeNot(){this.put("cslofTypeNot", "not");return this;};
		/** 交费方式 [非空]   0:现金支付 1:转帐支付 2:余额支付     **/
		public M cslofPayType(Object cslofPayType){this.put("cslofPayType", cslofPayType);return this;};
	 	/** and cslof_pay_type is null */
 		public M cslofPayTypeNull(){if(this.get("cslofPayTypeNot")==null)this.put("cslofPayTypeNot", "");this.put("cslofPayType", null);return this;};
 		/** not .... */
 		public M cslofPayTypeNot(){this.put("cslofPayTypeNot", "not");return this;};
		/** 费用金额 [非空]       **/
		public M cslofAmmount(Object cslofAmmount){this.put("cslofAmmount", cslofAmmount);return this;};
	 	/** and cslof_ammount is null */
 		public M cslofAmmountNull(){if(this.get("cslofAmmountNot")==null)this.put("cslofAmmountNot", "");this.put("cslofAmmount", null);return this;};
 		/** not .... */
 		public M cslofAmmountNot(){this.put("cslofAmmountNot", "not");return this;};
		/** and cslof_ammount >= ? */
		public M cslofAmmountMin(Object min){this.put("cslofAmmountMin", min);return this;};
		/** and cslof_ammount <= ? */
		public M cslofAmmountMax(Object max){this.put("cslofAmmountMax", max);return this;};
		/** 备注信息 [非空]       **/
		public M cslofRemark(Object cslofRemark){this.put("cslofRemark", cslofRemark);return this;};
	 	/** and cslof_remark is null */
 		public M cslofRemarkNull(){if(this.get("cslofRemarkNot")==null)this.put("cslofRemarkNot", "");this.put("cslofRemark", null);return this;};
 		/** not .... */
 		public M cslofRemarkNot(){this.put("cslofRemarkNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M cslofUpdateTime(Object cslofUpdateTime){this.put("cslofUpdateTime", cslofUpdateTime);return this;};
	 	/** and cslof_update_time is null */
 		public M cslofUpdateTimeNull(){if(this.get("cslofUpdateTimeNot")==null)this.put("cslofUpdateTimeNot", "");this.put("cslofUpdateTime", null);return this;};
 		/** not .... */
 		public M cslofUpdateTimeNot(){this.put("cslofUpdateTimeNot", "not");return this;};
 		/** and cslof_update_time >= ? */
 		public M cslofUpdateTimeStart(Object start){this.put("cslofUpdateTimeStart", start);return this;};			
 		/** and cslof_update_time <= ? */
 		public M cslofUpdateTimeEnd(Object end){this.put("cslofUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cslofAddTime(Object cslofAddTime){this.put("cslofAddTime", cslofAddTime);return this;};
	 	/** and cslof_add_time is null */
 		public M cslofAddTimeNull(){if(this.get("cslofAddTimeNot")==null)this.put("cslofAddTimeNot", "");this.put("cslofAddTime", null);return this;};
 		/** not .... */
 		public M cslofAddTimeNot(){this.put("cslofAddTimeNot", "not");return this;};
 		/** and cslof_add_time >= ? */
 		public M cslofAddTimeStart(Object start){this.put("cslofAddTimeStart", start);return this;};			
 		/** and cslof_add_time <= ? */
 		public M cslofAddTimeEnd(Object end){this.put("cslofAddTimeEnd", end);return this;};
		/** 入帐信息        **/
		public M cslofStateInfo(Object cslofStateInfo){this.put("cslofStateInfo", cslofStateInfo);return this;};
	 	/** and cslof_state_info is null */
 		public M cslofStateInfoNull(){if(this.get("cslofStateInfoNot")==null)this.put("cslofStateInfoNot", "");this.put("cslofStateInfo", null);return this;};
 		/** not .... */
 		public M cslofStateInfoNot(){this.put("cslofStateInfoNot", "not");return this;};
		/** 财务确认 [非空]   0:待确认 1:已确认     **/
		public M cslofState(Object cslofState){this.put("cslofState", cslofState);return this;};
	 	/** and cslof_state is null */
 		public M cslofStateNull(){if(this.get("cslofStateNot")==null)this.put("cslofStateNot", "");this.put("cslofState", null);return this;};
 		/** not .... */
 		public M cslofStateNot(){this.put("cslofStateNot", "not");return this;};
		/** 状态 [非空]   0:已交款 1:已退款     **/
		public M cslofStatus(Object cslofStatus){this.put("cslofStatus", cslofStatus);return this;};
	 	/** and cslof_status is null */
 		public M cslofStatusNull(){if(this.get("cslofStatusNot")==null)this.put("cslofStatusNot", "");this.put("cslofStatus", null);return this;};
 		/** not .... */
 		public M cslofStatusNot(){this.put("cslofStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有长单缴费 **/
		public @api List<CsLongOrderFee> list(Integer size){
			return getCsLongOrderFeeList(this,size);
		}
		/** 获取长单缴费分页 **/
		public @api Page<CsLongOrderFee> page(int page,int size){
			return getCsLongOrderFeePage(page, size , this);
		}
		/** 根据查询条件取长单缴费 **/
		public @api CsLongOrderFee get(){
			return getCsLongOrderFee(this);
		}
		/** 获取长单缴费数 **/
		public @api Long count(){
			return getCsLongOrderFeeCount(this);
		}
		/** 获取长单缴费表达式 **/
		public @api <T> T eval(String strEval){
			return getCsLongOrderFeeEval(strEval,this);
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
			updateCsLongOrderFee(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cslofId="cslofId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cslofHost="cslofHost";
		/** 所属长租 [非空] [CsLongOrder]      **/
		public final static @type(Long.class)  String cslofLongorder="cslofLongorder";
		/** 项目名称 [非空]       **/
		public final static @type(String.class) @like String cslofName="cslofName";
		/** 费用类型 [非空]   0:押金 1:租金     **/
		public final static @type(Short.class)  String cslofType="cslofType";
		/** 交费方式 [非空]   0:现金支付 1:转帐支付 2:余额支付     **/
		public final static @type(Short.class)  String cslofPayType="cslofPayType";
		/** 费用金额 [非空]       **/
		public final static @type(Double.class)  String cslofAmmount="cslofAmmount";
		/** and cslof_ammount >= ? */
		public final static @type(Double.class) String cslofAmmountMin="cslofAmmountMin";
		/** and cslof_ammount <= ? */
		public final static @type(Double.class) String cslofAmmountMax="cslofAmmountMax";
		/** 备注信息 [非空]       **/
		public final static @type(String.class) @like String cslofRemark="cslofRemark";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cslofUpdateTime="cslofUpdateTime";
	 	/** and cslof_update_time >= ? */
 		public final static @type(Date.class) String cslofUpdateTimeStart="cslofUpdateTimeStart";
 		/** and cslof_update_time <= ? */
 		public final static @type(Date.class) String cslofUpdateTimeEnd="cslofUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cslofAddTime="cslofAddTime";
	 	/** and cslof_add_time >= ? */
 		public final static @type(Date.class) String cslofAddTimeStart="cslofAddTimeStart";
 		/** and cslof_add_time <= ? */
 		public final static @type(Date.class) String cslofAddTimeEnd="cslofAddTimeEnd";
		/** 入帐信息        **/
		public final static @type(String.class) @like String cslofStateInfo="cslofStateInfo";
		/** 财务确认 [非空]   0:待确认 1:已确认     **/
		public final static @type(Short.class)  String cslofState="cslofState";
		/** 状态 [非空]   0:已交款 1:已退款     **/
		public final static @type(Short.class)  String cslofStatus="cslofStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cslofId="cslof_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cslofHost="cslof_host";
		/** 所属长租 [非空] [CsLongOrder]      **/
		public final static String cslofLongorder="cslof_longorder";
		/** 项目名称 [非空]       **/
		public final static String cslofName="cslof_name";
		/** 费用类型 [非空]   0:押金 1:租金     **/
		public final static String cslofType="cslof_type";
		/** 交费方式 [非空]   0:现金支付 1:转帐支付 2:余额支付     **/
		public final static String cslofPayType="cslof_pay_type";
		/** 费用金额 [非空]       **/
		public final static String cslofAmmount="cslof_ammount";
		/** 备注信息 [非空]       **/
		public final static String cslofRemark="cslof_remark";
		/** 修改时间 [非空]       **/
		public final static String cslofUpdateTime="cslof_update_time";
		/** 添加时间 [非空]       **/
		public final static String cslofAddTime="cslof_add_time";
		/** 入帐信息        **/
		public final static String cslofStateInfo="cslof_state_info";
		/** 财务确认 [非空]   0:待确认 1:已确认     **/
		public final static String cslofState="cslof_state";
		/** 状态 [非空]   0:已交款 1:已退款     **/
		public final static String cslofStatus="cslof_status";
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
				$.Set(name,CsLongOrderFee.getCsLongOrderFee(params));
			else
				$.Set(name,CsLongOrderFee.getCsLongOrderFeeList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取长单缴费数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsLongOrderFee) $.GetRequest("CsLongOrderFee$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsLongOrderFee.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsLongOrderFee.getCsLongOrderFee(params);
			else
				value = CsLongOrderFee.getCsLongOrderFeeList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsLongOrderFee.Get($.add(CsLongOrderFee.F.cslofId,param));
		else if(!$.empty(param.toString()))
			value = CsLongOrderFee.get(Long.valueOf(param.toString()));
		$.SetRequest("CsLongOrderFee$"+param.hashCode(), value);
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
	public void mergeSet(CsLongOrderFee old){
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