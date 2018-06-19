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

@namespace("object/chargebill")
public @caption("充电订单") @table("cs_charge_bill") class CsChargeBill implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cscb_id")   @primary   @note("  ") Long cscbId;
	private @caption("流水号") @column("cscb_serialnumber")      @note("  ") String cscbSerialnumber;
	private @caption("所属电站") @column("cscb_station")      @note("  ") Long cscbStation;
	private @caption("电桩编号") @column("cscb_pile_code")      @note("  ") String cscbPileCode;
	private @caption("车牌号") @column("cscb_carno")      @note("  ") String cscbCarno;
	private @caption("客户编号") @column("cscb_cust_id")      @note("  ") String cscbCustId;
	private @caption("客户名称") @column("cscb_cust_name")      @note("  ") String cscbCustName;
	private @caption("客户卡号") @column("cscb_cust_cardno")      @note("  ") String cscbCustCardno;
	private @caption("订单开始电量") @column("cscb_start_energy")      @note("  ") Double cscbStartEnergy;
	private @caption("订单结束电量") @column("cscb_finish_energy")      @note("  ") Double cscbFinishEnergy;
	private @caption("订单开始时间") @column("cscb_start_time")      @note("  ") Date cscbStartTime;
	private @caption("订单结束时间") @column("cscb_finish_time")      @note("  ") Date cscbFinishTime;
	private @caption("总电量") @column("cscb_total_power")      @note("  ") Double cscbTotalPower;
	private @caption("总电费") @column("cscb_total_elecfee")      @note("  ") Double cscbTotalElecfee;
	private @caption("订单状态") @column("cscb_status")      @note("  ") String cscbStatus;
	private @caption("电桩厂商") @column("cscb_provider")      @note("  ") String cscbProvider;
	private @caption("其它数据") @column("cscb_data")      @note("  ") String cscbData;
	private @caption("创建时间") @column("cscb_add_time")      @note("  ") Date cscbAddTime;
	private @caption("修改时间") @column("cscb_update_time")      @note("  ") Date cscbUpdateTime;
	
	//默认构造函数
	public CsChargeBill(){
	
	}
	
	//主键构造函数
	public CsChargeBill(Long id){
		this.cscbId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsChargeBill(String cscbSerialnumber,Long cscbStation,String cscbPileCode,String cscbCarno,String cscbCustId,String cscbCustName,String cscbCustCardno,Double cscbStartEnergy,Double cscbFinishEnergy,Date cscbStartTime,Date cscbFinishTime,Double cscbTotalPower,Double cscbTotalElecfee,String cscbStatus,String cscbProvider,String cscbData,Date cscbAddTime,Date cscbUpdateTime){
		this.cscbSerialnumber=cscbSerialnumber;
		this.cscbStation=cscbStation;
		this.cscbPileCode=cscbPileCode;
		this.cscbCarno=cscbCarno;
		this.cscbCustId=cscbCustId;
		this.cscbCustName=cscbCustName;
		this.cscbCustCardno=cscbCustCardno;
		this.cscbStartEnergy=cscbStartEnergy;
		this.cscbFinishEnergy=cscbFinishEnergy;
		this.cscbStartTime=cscbStartTime;
		this.cscbFinishTime=cscbFinishTime;
		this.cscbTotalPower=cscbTotalPower;
		this.cscbTotalElecfee=cscbTotalElecfee;
		this.cscbStatus=cscbStatus;
		this.cscbProvider=cscbProvider;
		this.cscbData=cscbData;
		this.cscbAddTime=cscbAddTime;
		this.cscbUpdateTime=cscbUpdateTime;
	}
	
	//设置非空字段
	public CsChargeBill setNotNull(String cscbPileCode){
		this.cscbPileCode=cscbPileCode;
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsChargeBill cscbId(Long cscbId){
		this.cscbId = cscbId;
		this.setSeted(F.cscbId);
		return this;
	}
	/** 流水号 [非空]       **/
	public CsChargeBill cscbSerialnumber(String cscbSerialnumber){
		this.cscbSerialnumber = cscbSerialnumber;
		this.setSeted(F.cscbSerialnumber);
		return this;
	}
	/** 所属电站 [非空]       **/
	public CsChargeBill cscbStation(Long cscbStation){
		this.cscbStation = cscbStation;
		this.setSeted(F.cscbStation);
		return this;
	}
	/** 电桩编号 [可空]       **/
	public CsChargeBill cscbPileCode(String cscbPileCode){
		this.cscbPileCode = cscbPileCode;
		this.setSeted(F.cscbPileCode);
		return this;
	}
	/** 车牌号 [非空]       **/
	public CsChargeBill cscbCarno(String cscbCarno){
		this.cscbCarno = cscbCarno;
		this.setSeted(F.cscbCarno);
		return this;
	}
	/** 客户编号 [非空]       **/
	public CsChargeBill cscbCustId(String cscbCustId){
		this.cscbCustId = cscbCustId;
		this.setSeted(F.cscbCustId);
		return this;
	}
	/** 客户名称 [非空]       **/
	public CsChargeBill cscbCustName(String cscbCustName){
		this.cscbCustName = cscbCustName;
		this.setSeted(F.cscbCustName);
		return this;
	}
	/** 客户卡号 [非空]       **/
	public CsChargeBill cscbCustCardno(String cscbCustCardno){
		this.cscbCustCardno = cscbCustCardno;
		this.setSeted(F.cscbCustCardno);
		return this;
	}
	/** 订单开始电量 [非空]       **/
	public CsChargeBill cscbStartEnergy(Double cscbStartEnergy){
		this.cscbStartEnergy = cscbStartEnergy;
		this.setSeted(F.cscbStartEnergy);
		return this;
	}
	/** 订单结束电量 [非空]       **/
	public CsChargeBill cscbFinishEnergy(Double cscbFinishEnergy){
		this.cscbFinishEnergy = cscbFinishEnergy;
		this.setSeted(F.cscbFinishEnergy);
		return this;
	}
	/** 订单开始时间 [非空]       **/
	public CsChargeBill cscbStartTime(Date cscbStartTime){
		this.cscbStartTime = cscbStartTime;
		this.setSeted(F.cscbStartTime);
		return this;
	}
	/** 订单结束时间 [非空]       **/
	public CsChargeBill cscbFinishTime(Date cscbFinishTime){
		this.cscbFinishTime = cscbFinishTime;
		this.setSeted(F.cscbFinishTime);
		return this;
	}
	/** 总电量 [非空]       **/
	public CsChargeBill cscbTotalPower(Double cscbTotalPower){
		this.cscbTotalPower = cscbTotalPower;
		this.setSeted(F.cscbTotalPower);
		return this;
	}
	/** 总电费 [非空]       **/
	public CsChargeBill cscbTotalElecfee(Double cscbTotalElecfee){
		this.cscbTotalElecfee = cscbTotalElecfee;
		this.setSeted(F.cscbTotalElecfee);
		return this;
	}
	/** 订单状态 [非空]       **/
	public CsChargeBill cscbStatus(String cscbStatus){
		this.cscbStatus = cscbStatus;
		this.setSeted(F.cscbStatus);
		return this;
	}
	/** 电桩厂商 [非空]       **/
	public CsChargeBill cscbProvider(String cscbProvider){
		this.cscbProvider = cscbProvider;
		this.setSeted(F.cscbProvider);
		return this;
	}
	/** 其它数据 [非空]       **/
	public CsChargeBill cscbData(String cscbData){
		this.cscbData = cscbData;
		this.setSeted(F.cscbData);
		return this;
	}
	/** 创建时间 [非空]       **/
	public CsChargeBill cscbAddTime(Date cscbAddTime){
		this.cscbAddTime = cscbAddTime;
		this.setSeted(F.cscbAddTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsChargeBill cscbUpdateTime(Date cscbUpdateTime){
		this.cscbUpdateTime = cscbUpdateTime;
		this.setSeted(F.cscbUpdateTime);
		return this;
	}
	
	//克隆对象
	public CsChargeBill clone(){
		CsChargeBill clone = new CsChargeBill();
		clone.cscbId=this.cscbId;
		clone.cscbSerialnumber=this.cscbSerialnumber;
		clone.cscbStation=this.cscbStation;
		clone.cscbPileCode=this.cscbPileCode;
		clone.cscbCarno=this.cscbCarno;
		clone.cscbCustId=this.cscbCustId;
		clone.cscbCustName=this.cscbCustName;
		clone.cscbCustCardno=this.cscbCustCardno;
		clone.cscbStartEnergy=this.cscbStartEnergy;
		clone.cscbFinishEnergy=this.cscbFinishEnergy;
		clone.cscbStartTime=this.cscbStartTime;
		clone.cscbFinishTime=this.cscbFinishTime;
		clone.cscbTotalPower=this.cscbTotalPower;
		clone.cscbTotalElecfee=this.cscbTotalElecfee;
		clone.cscbStatus=this.cscbStatus;
		clone.cscbProvider=this.cscbProvider;
		clone.cscbData=this.cscbData;
		clone.cscbAddTime=this.cscbAddTime;
		clone.cscbUpdateTime=this.cscbUpdateTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取充电订单
	 * @param id
	 * @return
	 */
	public static @api CsChargeBill get(Long id){		
		return getCsChargeBillById(id);
	}
	/**
	 * 获取所有充电订单
	 * @return
	 */
	public static @api List<CsChargeBill> list(Map params,Integer size){
		return getCsChargeBillList(params,size);
	}
	/**
	 * 获取充电订单分页
	 * @return
	 */
	public static @api Page<CsChargeBill> page(int page,int size,Map params){
		return getCsChargeBillPage(page, size , params);
	}
	/**
	 * 根据查询条件取充电订单
	 * @param params
	 * @return
	 */
	public static @api CsChargeBill Get(Map params){
		return getCsChargeBill(params);
	}
	/**
	 * 获取充电订单数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsChargeBillCount(params);
	}
	/**
	 * 获取充电订单数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsChargeBillEval(eval,params);
	}
	
	/**
	 * 根据ID取充电订单
	 * @param id
	 * @return
	 */
	public static @api CsChargeBill getCsChargeBillById(Long id){		
		CsChargeBill csChargeBill = (CsChargeBill) $.GetRequest("CsChargeBill$"+id);
		if(csChargeBill!=null)
			return csChargeBill;
		com.ccclubs.service.admin.ICsChargeBillService csChargeBillService = $.GetSpringBean("csChargeBillService");		
		return csChargeBillService.getCsChargeBillById(id);
	}
	
	
	/**
	 * 根据ID取充电订单的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsChargeBill.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsChargeBill csChargeBill = get(id);
		if(csChargeBill!=null){
			String strValue = csChargeBill.getCscbId$();
			if(!"CscbId".equals("CscbId"))
				strValue+="("+csChargeBill.getCscbId$()+")";
			MemCache.setValue(CsChargeBill.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCscbId$();
		if(!"CscbId".equals("CscbId"))
			keyValue+="("+this.getCscbId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有充电订单
	 * @return
	 */
	public static @api List<CsChargeBill> getCsChargeBillList(Map params,Integer size){
		com.ccclubs.service.admin.ICsChargeBillService csChargeBillService = $.GetSpringBean("csChargeBillService");
		return csChargeBillService.getCsChargeBillList(params, size);
	}
	
	/**
	 * 获取充电订单分页
	 * @return
	 */
	public static @api Page<CsChargeBill> getCsChargeBillPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsChargeBillService csChargeBillService = $.GetSpringBean("csChargeBillService");
		return csChargeBillService.getCsChargeBillPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取充电订单
	 * @param params
	 * @return
	 */
	public static @api CsChargeBill getCsChargeBill(Map params){
		com.ccclubs.service.admin.ICsChargeBillService csChargeBillService = $.GetSpringBean("csChargeBillService");
		return csChargeBillService.getCsChargeBill(params);
	}
	
	/**
	 * 获取充电订单数
	 * @return
	 */
	public static @api Long getCsChargeBillCount(Map params){
		com.ccclubs.service.admin.ICsChargeBillService csChargeBillService = $.GetSpringBean("csChargeBillService");
		return csChargeBillService.getCsChargeBillCount(params);
	}
		
		
	/**
	 * 获取充电订单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsChargeBillEval(String eval,Map params){
		com.ccclubs.service.admin.ICsChargeBillService csChargeBillService = $.GetSpringBean("csChargeBillService");
		return csChargeBillService.getCsChargeBillEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsChargeBill(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsChargeBillService csChargeBillService = $.GetSpringBean("csChargeBillService");
		csChargeBillService.updateCsChargeBillByConfirm(set, where);
	}
	
	
	/**
	 * 保存充电订单对象
	 * @param params
	 * @return
	 */
	public CsChargeBill save(){
		com.ccclubs.service.admin.ICsChargeBillService csChargeBillService = $.GetSpringBean("csChargeBillService");
		if(this.getCscbId()!=null)
			csChargeBillService.updateCsChargeBill(this);
		else
			return csChargeBillService.saveCsChargeBill(this);
		return this;
	}
	
	
	/**
	 * 更新充电订单对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsChargeBillService csChargeBillService = $.GetSpringBean("csChargeBillService");
		csChargeBillService.updateCsChargeBill$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsChargeBillService csChargeBillService = $.GetSpringBean("csChargeBillService");
		if($.equals($.config("logic_delete"),"true"))
			csChargeBillService.removeCsChargeBillById(this.getCscbId());
		else
			csChargeBillService.deleteCsChargeBillById(this.getCscbId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsChargeBillService csChargeBillService = $.GetSpringBean("csChargeBillService");
		return csChargeBillService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCscbId(){
		return this.cscbId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCscbId$(){
		String strValue="";
		 strValue=$.str(this.getCscbId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCscbId(Long cscbId){
		this.cscbId = cscbId;
		this.setSeted(F.cscbId);
	}
	/*******************************流水号**********************************/	
	/**
	* 流水号 [非空]      
	**/
	public String getCscbSerialnumber(){
		return this.cscbSerialnumber;
	}
	/**
	* 获取流水号格式化(toString)
	**/
	public String getCscbSerialnumber$(){
		String strValue="";
		 strValue=$.str(this.getCscbSerialnumber());
	 	 return strValue;
	}
	/**
	* 流水号 [非空]     
	**/
	public void setCscbSerialnumber(String cscbSerialnumber){
		this.cscbSerialnumber = cscbSerialnumber;
		this.setSeted(F.cscbSerialnumber);
	}
	/*******************************所属电站**********************************/	
	/**
	* 所属电站 [非空]      
	**/
	public Long getCscbStation(){
		return this.cscbStation;
	}
	/**
	* 获取所属电站格式化(toString)
	**/
	public String getCscbStation$(){
		String strValue="";
		 strValue=$.str(this.getCscbStation());
	 	 return strValue;
	}
	/**
	* 所属电站 [非空]     
	**/
	public void setCscbStation(Long cscbStation){
		this.cscbStation = cscbStation;
		this.setSeted(F.cscbStation);
	}
	/*******************************电桩编号**********************************/	
	/**
	* 电桩编号 [可空]      
	**/
	public String getCscbPileCode(){
		return this.cscbPileCode;
	}
	/**
	* 获取电桩编号格式化(toString)
	**/
	public String getCscbPileCode$(){
		String strValue="";
		 strValue=$.str(this.getCscbPileCode());
	 	 return strValue;
	}
	/**
	* 电桩编号 [可空]     
	**/
	public void setCscbPileCode(String cscbPileCode){
		this.cscbPileCode = cscbPileCode;
		this.setSeted(F.cscbPileCode);
	}
	/*******************************车牌号**********************************/	
	/**
	* 车牌号 [非空]      
	**/
	public String getCscbCarno(){
		return this.cscbCarno;
	}
	/**
	* 获取车牌号格式化(toString)
	**/
	public String getCscbCarno$(){
		String strValue="";
		 strValue=$.str(this.getCscbCarno());
	 	 return strValue;
	}
	/**
	* 车牌号 [非空]     
	**/
	public void setCscbCarno(String cscbCarno){
		this.cscbCarno = cscbCarno;
		this.setSeted(F.cscbCarno);
	}
	/*******************************客户编号**********************************/	
	/**
	* 客户编号 [非空]      
	**/
	public String getCscbCustId(){
		return this.cscbCustId;
	}
	/**
	* 获取客户编号格式化(toString)
	**/
	public String getCscbCustId$(){
		String strValue="";
		 strValue=$.str(this.getCscbCustId());
	 	 return strValue;
	}
	/**
	* 客户编号 [非空]     
	**/
	public void setCscbCustId(String cscbCustId){
		this.cscbCustId = cscbCustId;
		this.setSeted(F.cscbCustId);
	}
	/*******************************客户名称**********************************/	
	/**
	* 客户名称 [非空]      
	**/
	public String getCscbCustName(){
		return this.cscbCustName;
	}
	/**
	* 获取客户名称格式化(toString)
	**/
	public String getCscbCustName$(){
		String strValue="";
		 strValue=$.str(this.getCscbCustName());
	 	 return strValue;
	}
	/**
	* 客户名称 [非空]     
	**/
	public void setCscbCustName(String cscbCustName){
		this.cscbCustName = cscbCustName;
		this.setSeted(F.cscbCustName);
	}
	/*******************************客户卡号**********************************/	
	/**
	* 客户卡号 [非空]      
	**/
	public String getCscbCustCardno(){
		return this.cscbCustCardno;
	}
	/**
	* 获取客户卡号格式化(toString)
	**/
	public String getCscbCustCardno$(){
		String strValue="";
		 strValue=$.str(this.getCscbCustCardno());
	 	 return strValue;
	}
	/**
	* 客户卡号 [非空]     
	**/
	public void setCscbCustCardno(String cscbCustCardno){
		this.cscbCustCardno = cscbCustCardno;
		this.setSeted(F.cscbCustCardno);
	}
	/*******************************订单开始电量**********************************/	
	/**
	* 订单开始电量 [非空]      
	**/
	public Double getCscbStartEnergy(){
		return this.cscbStartEnergy;
	}
	/**
	* 获取订单开始电量格式化(toString)
	**/
	public String getCscbStartEnergy$(){
		String strValue="";
		 strValue=$.str(this.getCscbStartEnergy());
	 	 return strValue;
	}
	/**
	* 订单开始电量 [非空]     
	**/
	public void setCscbStartEnergy(Double cscbStartEnergy){
		this.cscbStartEnergy = cscbStartEnergy;
		this.setSeted(F.cscbStartEnergy);
	}
	/*******************************订单结束电量**********************************/	
	/**
	* 订单结束电量 [非空]      
	**/
	public Double getCscbFinishEnergy(){
		return this.cscbFinishEnergy;
	}
	/**
	* 获取订单结束电量格式化(toString)
	**/
	public String getCscbFinishEnergy$(){
		String strValue="";
		 strValue=$.str(this.getCscbFinishEnergy());
	 	 return strValue;
	}
	/**
	* 订单结束电量 [非空]     
	**/
	public void setCscbFinishEnergy(Double cscbFinishEnergy){
		this.cscbFinishEnergy = cscbFinishEnergy;
		this.setSeted(F.cscbFinishEnergy);
	}
	/*******************************订单开始时间**********************************/	
	/**
	* 订单开始时间 [非空]      
	**/
	public Date getCscbStartTime(){
		return this.cscbStartTime;
	}
	/**
	* 获取订单开始时间格式化(toString)
	**/
	public String getCscbStartTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscbStartTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 订单开始时间 [非空]     
	**/
	public void setCscbStartTime(Date cscbStartTime){
		this.cscbStartTime = cscbStartTime;
		this.setSeted(F.cscbStartTime);
	}
	/*******************************订单结束时间**********************************/	
	/**
	* 订单结束时间 [非空]      
	**/
	public Date getCscbFinishTime(){
		return this.cscbFinishTime;
	}
	/**
	* 获取订单结束时间格式化(toString)
	**/
	public String getCscbFinishTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscbFinishTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 订单结束时间 [非空]     
	**/
	public void setCscbFinishTime(Date cscbFinishTime){
		this.cscbFinishTime = cscbFinishTime;
		this.setSeted(F.cscbFinishTime);
	}
	/*******************************总电量**********************************/	
	/**
	* 总电量 [非空]      
	**/
	public Double getCscbTotalPower(){
		return this.cscbTotalPower;
	}
	/**
	* 获取总电量格式化(toString)
	**/
	public String getCscbTotalPower$(){
		String strValue="";
		 strValue=$.str(this.getCscbTotalPower());
	 	 return strValue;
	}
	/**
	* 总电量 [非空]     
	**/
	public void setCscbTotalPower(Double cscbTotalPower){
		this.cscbTotalPower = cscbTotalPower;
		this.setSeted(F.cscbTotalPower);
	}
	/*******************************总电费**********************************/	
	/**
	* 总电费 [非空]      
	**/
	public Double getCscbTotalElecfee(){
		return this.cscbTotalElecfee;
	}
	/**
	* 获取总电费格式化(toString)
	**/
	public String getCscbTotalElecfee$(){
		String strValue="";
		 strValue=$.str(this.getCscbTotalElecfee());
	 	 return strValue;
	}
	/**
	* 总电费 [非空]     
	**/
	public void setCscbTotalElecfee(Double cscbTotalElecfee){
		this.cscbTotalElecfee = cscbTotalElecfee;
		this.setSeted(F.cscbTotalElecfee);
	}
	/*******************************订单状态**********************************/	
	/**
	* 订单状态 [非空]      
	**/
	public String getCscbStatus(){
		return this.cscbStatus;
	}
	/**
	* 获取订单状态格式化(toString)
	**/
	public String getCscbStatus$(){
		String strValue="";
		 strValue=$.str(this.getCscbStatus());
	 	 return strValue;
	}
	/**
	* 订单状态 [非空]     
	**/
	public void setCscbStatus(String cscbStatus){
		this.cscbStatus = cscbStatus;
		this.setSeted(F.cscbStatus);
	}
	/*******************************电桩厂商**********************************/	
	/**
	* 电桩厂商 [非空]      
	**/
	public String getCscbProvider(){
		return this.cscbProvider;
	}
	/**
	* 获取电桩厂商格式化(toString)
	**/
	public String getCscbProvider$(){
		String strValue="";
		 strValue=$.str(this.getCscbProvider());
	 	 return strValue;
	}
	/**
	* 电桩厂商 [非空]     
	**/
	public void setCscbProvider(String cscbProvider){
		this.cscbProvider = cscbProvider;
		this.setSeted(F.cscbProvider);
	}
	/*******************************其它数据**********************************/	
	/**
	* 其它数据 [非空]      
	**/
	public String getCscbData(){
		return this.cscbData;
	}
	/**
	* 获取其它数据格式化(toString)
	**/
	public String getCscbData$(){
		String strValue="";
		 strValue=$.str(this.getCscbData());
	 	 return strValue;
	}
	/**
	* 其它数据 [非空]     
	**/
	public void setCscbData(String cscbData){
		this.cscbData = cscbData;
		this.setSeted(F.cscbData);
	}
	/*******************************创建时间**********************************/	
	/**
	* 创建时间 [非空]      
	**/
	public Date getCscbAddTime(){
		return this.cscbAddTime;
	}
	/**
	* 获取创建时间格式化(toString)
	**/
	public String getCscbAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscbAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 创建时间 [非空]     
	**/
	public void setCscbAddTime(Date cscbAddTime){
		this.cscbAddTime = cscbAddTime;
		this.setSeted(F.cscbAddTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCscbUpdateTime(){
		return this.cscbUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCscbUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscbUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCscbUpdateTime(Date cscbUpdateTime){
		this.cscbUpdateTime = cscbUpdateTime;
		this.setSeted(F.cscbUpdateTime);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsChargeBill.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsChargeBill.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsChargeBill.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsChargeBill.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsChargeBill.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsChargeBill.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsChargeBill.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsChargeBill.this);
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
		public M cscbId(Object cscbId){this.put("cscbId", cscbId);return this;};
		/** and cscb_id is null */
		public M cscbIdNull(){if(this.get("cscbIdNot")==null)this.put("cscbIdNot", "");this.put("cscbId", null);return this;};
		/** not .... */
		public M cscbIdNot(){this.put("cscbIdNot", "not");return this;};
		/** 流水号 [非空]       **/
		public M cscbSerialnumber(Object cscbSerialnumber){this.put("cscbSerialnumber", cscbSerialnumber);return this;};
		/** and cscb_serialnumber is null */
		public M cscbSerialnumberNull(){if(this.get("cscbSerialnumberNot")==null)this.put("cscbSerialnumberNot", "");this.put("cscbSerialnumber", null);return this;};
		/** not .... */
		public M cscbSerialnumberNot(){this.put("cscbSerialnumberNot", "not");return this;};
		/** 所属电站 [非空]       **/
		public M cscbStation(Object cscbStation){this.put("cscbStation", cscbStation);return this;};
		/** and cscb_station is null */
		public M cscbStationNull(){if(this.get("cscbStationNot")==null)this.put("cscbStationNot", "");this.put("cscbStation", null);return this;};
		/** not .... */
		public M cscbStationNot(){this.put("cscbStationNot", "not");return this;};
		/** 电桩编号 [可空]       **/
		public M cscbPileCode(Object cscbPileCode){this.put("cscbPileCode", cscbPileCode);return this;};
		/** and cscb_pile_code is null */
		public M cscbPileCodeNull(){if(this.get("cscbPileCodeNot")==null)this.put("cscbPileCodeNot", "");this.put("cscbPileCode", null);return this;};
		/** not .... */
		public M cscbPileCodeNot(){this.put("cscbPileCodeNot", "not");return this;};
		/** 车牌号 [非空]       **/
		public M cscbCarno(Object cscbCarno){this.put("cscbCarno", cscbCarno);return this;};
		/** and cscb_carno is null */
		public M cscbCarnoNull(){if(this.get("cscbCarnoNot")==null)this.put("cscbCarnoNot", "");this.put("cscbCarno", null);return this;};
		/** not .... */
		public M cscbCarnoNot(){this.put("cscbCarnoNot", "not");return this;};
		/** 客户编号 [非空]       **/
		public M cscbCustId(Object cscbCustId){this.put("cscbCustId", cscbCustId);return this;};
		/** and cscb_cust_id is null */
		public M cscbCustIdNull(){if(this.get("cscbCustIdNot")==null)this.put("cscbCustIdNot", "");this.put("cscbCustId", null);return this;};
		/** not .... */
		public M cscbCustIdNot(){this.put("cscbCustIdNot", "not");return this;};
		/** 客户名称 [非空]       **/
		public M cscbCustName(Object cscbCustName){this.put("cscbCustName", cscbCustName);return this;};
		/** and cscb_cust_name is null */
		public M cscbCustNameNull(){if(this.get("cscbCustNameNot")==null)this.put("cscbCustNameNot", "");this.put("cscbCustName", null);return this;};
		/** not .... */
		public M cscbCustNameNot(){this.put("cscbCustNameNot", "not");return this;};
		/** 客户卡号 [非空]       **/
		public M cscbCustCardno(Object cscbCustCardno){this.put("cscbCustCardno", cscbCustCardno);return this;};
		/** and cscb_cust_cardno is null */
		public M cscbCustCardnoNull(){if(this.get("cscbCustCardnoNot")==null)this.put("cscbCustCardnoNot", "");this.put("cscbCustCardno", null);return this;};
		/** not .... */
		public M cscbCustCardnoNot(){this.put("cscbCustCardnoNot", "not");return this;};
		/** 订单开始电量 [非空]       **/
		public M cscbStartEnergy(Object cscbStartEnergy){this.put("cscbStartEnergy", cscbStartEnergy);return this;};
		/** and cscb_start_energy is null */
		public M cscbStartEnergyNull(){if(this.get("cscbStartEnergyNot")==null)this.put("cscbStartEnergyNot", "");this.put("cscbStartEnergy", null);return this;};
		/** not .... */
		public M cscbStartEnergyNot(){this.put("cscbStartEnergyNot", "not");return this;};
		/** 订单结束电量 [非空]       **/
		public M cscbFinishEnergy(Object cscbFinishEnergy){this.put("cscbFinishEnergy", cscbFinishEnergy);return this;};
		/** and cscb_finish_energy is null */
		public M cscbFinishEnergyNull(){if(this.get("cscbFinishEnergyNot")==null)this.put("cscbFinishEnergyNot", "");this.put("cscbFinishEnergy", null);return this;};
		/** not .... */
		public M cscbFinishEnergyNot(){this.put("cscbFinishEnergyNot", "not");return this;};
		/** 订单开始时间 [非空]       **/
		public M cscbStartTime(Object cscbStartTime){this.put("cscbStartTime", cscbStartTime);return this;};
		/** and cscb_start_time is null */
		public M cscbStartTimeNull(){if(this.get("cscbStartTimeNot")==null)this.put("cscbStartTimeNot", "");this.put("cscbStartTime", null);return this;};
		/** not .... */
		public M cscbStartTimeNot(){this.put("cscbStartTimeNot", "not");return this;};
		/** and cscb_start_time >= ? */
 		public M cscbStartTimeStart(Object start){this.put("cscbStartTimeStart", start);return this;};			
 		/** and cscb_start_time <= ? */
 		public M cscbStartTimeEnd(Object end){this.put("cscbStartTimeEnd", end);return this;};
		/** 订单结束时间 [非空]       **/
		public M cscbFinishTime(Object cscbFinishTime){this.put("cscbFinishTime", cscbFinishTime);return this;};
		/** and cscb_finish_time is null */
		public M cscbFinishTimeNull(){if(this.get("cscbFinishTimeNot")==null)this.put("cscbFinishTimeNot", "");this.put("cscbFinishTime", null);return this;};
		/** not .... */
		public M cscbFinishTimeNot(){this.put("cscbFinishTimeNot", "not");return this;};
		/** and cscb_finish_time >= ? */
 		public M cscbFinishTimeStart(Object start){this.put("cscbFinishTimeStart", start);return this;};			
 		/** and cscb_finish_time <= ? */
 		public M cscbFinishTimeEnd(Object end){this.put("cscbFinishTimeEnd", end);return this;};
		/** 总电量 [非空]       **/
		public M cscbTotalPower(Object cscbTotalPower){this.put("cscbTotalPower", cscbTotalPower);return this;};
		/** and cscb_total_power is null */
		public M cscbTotalPowerNull(){if(this.get("cscbTotalPowerNot")==null)this.put("cscbTotalPowerNot", "");this.put("cscbTotalPower", null);return this;};
		/** not .... */
		public M cscbTotalPowerNot(){this.put("cscbTotalPowerNot", "not");return this;};
		/** 总电费 [非空]       **/
		public M cscbTotalElecfee(Object cscbTotalElecfee){this.put("cscbTotalElecfee", cscbTotalElecfee);return this;};
		/** and cscb_total_elecfee is null */
		public M cscbTotalElecfeeNull(){if(this.get("cscbTotalElecfeeNot")==null)this.put("cscbTotalElecfeeNot", "");this.put("cscbTotalElecfee", null);return this;};
		/** not .... */
		public M cscbTotalElecfeeNot(){this.put("cscbTotalElecfeeNot", "not");return this;};
		/** 订单状态 [非空]       **/
		public M cscbStatus(Object cscbStatus){this.put("cscbStatus", cscbStatus);return this;};
		/** and cscb_status is null */
		public M cscbStatusNull(){if(this.get("cscbStatusNot")==null)this.put("cscbStatusNot", "");this.put("cscbStatus", null);return this;};
		/** not .... */
		public M cscbStatusNot(){this.put("cscbStatusNot", "not");return this;};
		/** 电桩厂商 [非空]       **/
		public M cscbProvider(Object cscbProvider){this.put("cscbProvider", cscbProvider);return this;};
		/** and cscb_provider is null */
		public M cscbProviderNull(){if(this.get("cscbProviderNot")==null)this.put("cscbProviderNot", "");this.put("cscbProvider", null);return this;};
		/** not .... */
		public M cscbProviderNot(){this.put("cscbProviderNot", "not");return this;};
		/** 其它数据 [非空]       **/
		public M cscbData(Object cscbData){this.put("cscbData", cscbData);return this;};
		/** and cscb_data is null */
		public M cscbDataNull(){if(this.get("cscbDataNot")==null)this.put("cscbDataNot", "");this.put("cscbData", null);return this;};
		/** not .... */
		public M cscbDataNot(){this.put("cscbDataNot", "not");return this;};
		/** 创建时间 [非空]       **/
		public M cscbAddTime(Object cscbAddTime){this.put("cscbAddTime", cscbAddTime);return this;};
		/** and cscb_add_time is null */
		public M cscbAddTimeNull(){if(this.get("cscbAddTimeNot")==null)this.put("cscbAddTimeNot", "");this.put("cscbAddTime", null);return this;};
		/** not .... */
		public M cscbAddTimeNot(){this.put("cscbAddTimeNot", "not");return this;};
		/** and cscb_add_time >= ? */
 		public M cscbAddTimeStart(Object start){this.put("cscbAddTimeStart", start);return this;};			
 		/** and cscb_add_time <= ? */
 		public M cscbAddTimeEnd(Object end){this.put("cscbAddTimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M cscbUpdateTime(Object cscbUpdateTime){this.put("cscbUpdateTime", cscbUpdateTime);return this;};
		/** and cscb_update_time is null */
		public M cscbUpdateTimeNull(){if(this.get("cscbUpdateTimeNot")==null)this.put("cscbUpdateTimeNot", "");this.put("cscbUpdateTime", null);return this;};
		/** not .... */
		public M cscbUpdateTimeNot(){this.put("cscbUpdateTimeNot", "not");return this;};
		/** and cscb_update_time >= ? */
 		public M cscbUpdateTimeStart(Object start){this.put("cscbUpdateTimeStart", start);return this;};			
 		/** and cscb_update_time <= ? */
 		public M cscbUpdateTimeEnd(Object end){this.put("cscbUpdateTimeEnd", end);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有充电订单 **/
		public @api List<CsChargeBill> list(Integer size){
			return getCsChargeBillList(this,size);
		}
		/** 获取充电订单分页 **/
		public @api Page<CsChargeBill> page(int page,int size){
			return getCsChargeBillPage(page, size , this);
		}
		/** 根据查询条件取充电订单 **/
		public @api CsChargeBill get(){
			return getCsChargeBill(this);
		}
		/** 获取充电订单数 **/
		public @api Long count(){
			return getCsChargeBillCount(this);
		}
		/** 获取充电订单表达式 **/
		public @api <T> T eval(String strEval){
			return getCsChargeBillEval(strEval,this);
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
			updateCsChargeBill(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscbId="cscbId";
		/** 流水号 [非空]       **/
		public final static @type(String.class)  String cscbSerialnumber="cscbSerialnumber";
		/** 所属电站 [非空]       **/
		public final static @type(Long.class)  String cscbStation="cscbStation";
		/** 电桩编号 [可空]       **/
		public final static @type(String.class)  String cscbPileCode="cscbPileCode";
		/** 车牌号 [非空]       **/
		public final static @type(String.class)  String cscbCarno="cscbCarno";
		/** 客户编号 [非空]       **/
		public final static @type(String.class)  String cscbCustId="cscbCustId";
		/** 客户名称 [非空]       **/
		public final static @type(String.class)  String cscbCustName="cscbCustName";
		/** 客户卡号 [非空]       **/
		public final static @type(String.class)  String cscbCustCardno="cscbCustCardno";
		/** 订单开始电量 [非空]       **/
		public final static @type(Double.class)  String cscbStartEnergy="cscbStartEnergy";
		/** 订单结束电量 [非空]       **/
		public final static @type(Double.class)  String cscbFinishEnergy="cscbFinishEnergy";
		/** 订单开始时间 [非空]       **/
		public final static @type(Date.class)  String cscbStartTime="cscbStartTime";
		/** and cscb_start_time >= ? */
 		public final static @type(Date.class) String cscbStartTimeStart="cscbStartTimeStart";
 		/** and cscb_start_time <= ? */
 		public final static @type(Date.class) String cscbStartTimeEnd="cscbStartTimeEnd";
		/** 订单结束时间 [非空]       **/
		public final static @type(Date.class)  String cscbFinishTime="cscbFinishTime";
		/** and cscb_finish_time >= ? */
 		public final static @type(Date.class) String cscbFinishTimeStart="cscbFinishTimeStart";
 		/** and cscb_finish_time <= ? */
 		public final static @type(Date.class) String cscbFinishTimeEnd="cscbFinishTimeEnd";
		/** 总电量 [非空]       **/
		public final static @type(Double.class)  String cscbTotalPower="cscbTotalPower";
		/** 总电费 [非空]       **/
		public final static @type(Double.class)  String cscbTotalElecfee="cscbTotalElecfee";
		/** 订单状态 [非空]       **/
		public final static @type(String.class)  String cscbStatus="cscbStatus";
		/** 电桩厂商 [非空]       **/
		public final static @type(String.class)  String cscbProvider="cscbProvider";
		/** 其它数据 [非空]       **/
		public final static @type(String.class)  String cscbData="cscbData";
		/** 创建时间 [非空]       **/
		public final static @type(Date.class)  String cscbAddTime="cscbAddTime";
		/** and cscb_add_time >= ? */
 		public final static @type(Date.class) String cscbAddTimeStart="cscbAddTimeStart";
 		/** and cscb_add_time <= ? */
 		public final static @type(Date.class) String cscbAddTimeEnd="cscbAddTimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cscbUpdateTime="cscbUpdateTime";
		/** and cscb_update_time >= ? */
 		public final static @type(Date.class) String cscbUpdateTimeStart="cscbUpdateTimeStart";
 		/** and cscb_update_time <= ? */
 		public final static @type(Date.class) String cscbUpdateTimeEnd="cscbUpdateTimeEnd";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscbId="cscb_id";
		/** 流水号 [非空]       **/
		public final static String cscbSerialnumber="cscb_serialnumber";
		/** 所属电站 [非空]       **/
		public final static String cscbStation="cscb_station";
		/** 电桩编号 [可空]       **/
		public final static String cscbPileCode="cscb_pile_code";
		/** 车牌号 [非空]       **/
		public final static String cscbCarno="cscb_carno";
		/** 客户编号 [非空]       **/
		public final static String cscbCustId="cscb_cust_id";
		/** 客户名称 [非空]       **/
		public final static String cscbCustName="cscb_cust_name";
		/** 客户卡号 [非空]       **/
		public final static String cscbCustCardno="cscb_cust_cardno";
		/** 订单开始电量 [非空]       **/
		public final static String cscbStartEnergy="cscb_start_energy";
		/** 订单结束电量 [非空]       **/
		public final static String cscbFinishEnergy="cscb_finish_energy";
		/** 订单开始时间 [非空]       **/
		public final static String cscbStartTime="cscb_start_time";
		/** 订单结束时间 [非空]       **/
		public final static String cscbFinishTime="cscb_finish_time";
		/** 总电量 [非空]       **/
		public final static String cscbTotalPower="cscb_total_power";
		/** 总电费 [非空]       **/
		public final static String cscbTotalElecfee="cscb_total_elecfee";
		/** 订单状态 [非空]       **/
		public final static String cscbStatus="cscb_status";
		/** 电桩厂商 [非空]       **/
		public final static String cscbProvider="cscb_provider";
		/** 其它数据 [非空]       **/
		public final static String cscbData="cscb_data";
		/** 创建时间 [非空]       **/
		public final static String cscbAddTime="cscb_add_time";
		/** 修改时间 [非空]       **/
		public final static String cscbUpdateTime="cscb_update_time";
		
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
				$.Set(name,CsChargeBill.getCsChargeBill(params));
			else
				$.Set(name,CsChargeBill.getCsChargeBillList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取充电订单数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsChargeBill) $.GetRequest("CsChargeBill$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsChargeBill.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsChargeBill.getCsChargeBill(params);
			else
				value = CsChargeBill.getCsChargeBillList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsChargeBill.Get($.add(CsChargeBill.F.cscbId,param));
		else if(!$.empty(param.toString()))
			value = CsChargeBill.get(Long.valueOf(param.toString()));
		$.SetRequest("CsChargeBill$"+param.hashCode(), value);
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
	public void mergeSet(CsChargeBill old){
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