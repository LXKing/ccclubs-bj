package com.ccclubs.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.dao.impl.MemCache;
import com.lazy3q.lang.RelateClass;
import com.lazy3q.lang.api;
import com.lazy3q.lang.caption;
import com.lazy3q.lang.column;
import com.lazy3q.lang.namespace;
import com.lazy3q.lang.note;
import com.lazy3q.lang.primary;
import com.lazy3q.lang.relate;
import com.lazy3q.lang.table;
import com.lazy3q.lang.type;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.tags.BodyTag;
import com.lazy3q.web.util.Page;

@namespace("unit/bill")
public @caption("企业对账") @table("cs_unit_bill") class CsUnitBill implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csub_id")   @primary   @note("  ") Long csubId;
	private @caption("运营地区") @column("csub_host")    @relate("$csubHost") @RelateClass(SrvHost.class)  @note("  ") Long csubHost;
	private SrvHost $csubHost;//关联对象[运营地区]
	private @caption("帐单月份") @column("csub_month")      @note("  yyyy-MM") String csubMonth;
	private @caption("所属单位") @column("csub_unit")    @relate("$csubUnit") @RelateClass(CsUnitInfo.class)  @note("  ") Long csubUnit;
	private CsUnitInfo $csubUnit;//关联对象[所属单位]
	private @caption("接机次数") @column("csub_air_s")      @note("  ") Integer csubAirS;
	private @caption("订单个数") @column("csub_order_s")      @note("  ") Integer csubOrderS;
	private @caption("专车个数") @column("csub_special_s")      @note("  ") Integer csubSpecialS;
	private @caption("接送机费") @column("csub_fee_air")      @note("  ") Double csubFeeAir;
	private @caption("订单费用") @column("csub_fee_order")      @note("  ") Double csubFeeOrder;
	private @caption("专车费用") @column("csub_fee_special")      @note("  ") Double csubFeeSpecial;
	private @caption("应付金额") @column("csub_need_pay")      @note("  全部应付金额") Double csubNeedPay;
	private @caption("已付金额") @column("csub_real_pay")      @note("  实际已付金额") Double csubRealPay;
	private @caption("修改人") @column("csub_editor")    @relate("$csubEditor") @RelateClass(SrvUser.class)  @note("  ") Long csubEditor;
	private SrvUser $csubEditor;//关联对象[修改人]
	private @caption("修改时间") @column("csub_update_time")      @note("  ") Date csubUpdateTime;
	private @caption("添加时间") @column("csub_add_time")      @note("  ") Date csubAddTime;
	private @caption("财务备注") @column("csub_summary")      @note("  ") String csubSummary;
	private @caption("状态") @column("csub_status")      @note(" 0:未审核 1:待结算 2:已结算  ") Short csubStatus;
	private @caption("收入合计") @column("csub_income")      @note("") Double csubIncome;
	private @caption("支出合计") @column("csub_payout")      @note("") Double csubPayout;
	private @caption("部门") @column("csub_group")    @relate("$csubGroup") @RelateClass(CsUnitGroup.class)  @note("") Long csubGroup;
	private CsUnitGroup $csubGroup;//关联对象[部门]
	private @caption("里程") @column("csub_mileage")      @note("") Double csubMileage;
	private @caption("时长") @column("csub_duration")      @note("") Double csubDuration;
	
	//默认构造函数
	public CsUnitBill(){
	
	}
	
	//主键构造函数
	public CsUnitBill(Long id){
		this.csubId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsUnitBill(Long csubHost,String csubMonth,Long csubUnit,Integer csubAirS,Integer csubOrderS,Integer csubSpecialS,Double csubFeeAir,Double csubFeeOrder,Double csubFeeSpecial,Double csubNeedPay,Double csubRealPay,Long csubEditor,Date csubUpdateTime,Date csubAddTime,String csubSummary,Short csubStatus,Double csubIncome,Double csubPayout,Long csubGroup,Double csubMileage,Double csubDuration){
		this.csubHost=csubHost;
		this.csubMonth=csubMonth;
		this.csubUnit=csubUnit;
		this.csubAirS=csubAirS;
		this.csubOrderS=csubOrderS;
		this.csubSpecialS=csubSpecialS;
		this.csubFeeAir=csubFeeAir;
		this.csubFeeOrder=csubFeeOrder;
		this.csubFeeSpecial=csubFeeSpecial;
		this.csubNeedPay=csubNeedPay;
		this.csubRealPay=csubRealPay;
		this.csubEditor=csubEditor;
		this.csubUpdateTime=csubUpdateTime;
		this.csubAddTime=csubAddTime;
		this.csubSummary=csubSummary;
		this.csubStatus=csubStatus;
		this.csubIncome=csubIncome;
		this.csubPayout=csubPayout;
		this.csubGroup=csubGroup;
		this.csubMileage=csubMileage;
		this.csubDuration=csubDuration;
	}
	
	//设置非空字段
	public CsUnitBill setNotNull(Long csubGroup){
		this.csubGroup=csubGroup;
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsUnitBill csubId(Long csubId){
		this.csubId = csubId;
		this.setSeted(F.csubId);
		return this;
	}
	/** 运营地区 [非空]       **/
	public CsUnitBill csubHost(Long csubHost){
		this.csubHost = csubHost;
		this.setSeted(F.csubHost);
		return this;
	}
	/** 帐单月份 [非空]       **/
	public CsUnitBill csubMonth(String csubMonth){
		this.csubMonth = csubMonth;
		this.setSeted(F.csubMonth);
		return this;
	}
	/** 所属单位 [非空]       **/
	public CsUnitBill csubUnit(Long csubUnit){
		this.csubUnit = csubUnit;
		this.setSeted(F.csubUnit);
		return this;
	}
	/** 接机次数 [非空]       **/
	public CsUnitBill csubAirS(Integer csubAirS){
		this.csubAirS = csubAirS;
		this.setSeted(F.csubAirS);
		return this;
	}
	/** 订单个数 [非空]       **/
	public CsUnitBill csubOrderS(Integer csubOrderS){
		this.csubOrderS = csubOrderS;
		this.setSeted(F.csubOrderS);
		return this;
	}
	/** 专车个数 [非空]       **/
	public CsUnitBill csubSpecialS(Integer csubSpecialS){
		this.csubSpecialS = csubSpecialS;
		this.setSeted(F.csubSpecialS);
		return this;
	}
	/** 接送机费 [非空]       **/
	public CsUnitBill csubFeeAir(Double csubFeeAir){
		this.csubFeeAir = csubFeeAir;
		this.setSeted(F.csubFeeAir);
		return this;
	}
	/** 订单费用 [非空]       **/
	public CsUnitBill csubFeeOrder(Double csubFeeOrder){
		this.csubFeeOrder = csubFeeOrder;
		this.setSeted(F.csubFeeOrder);
		return this;
	}
	/** 专车费用 [非空]       **/
	public CsUnitBill csubFeeSpecial(Double csubFeeSpecial){
		this.csubFeeSpecial = csubFeeSpecial;
		this.setSeted(F.csubFeeSpecial);
		return this;
	}
	/** 应付金额 [非空]       **/
	public CsUnitBill csubNeedPay(Double csubNeedPay){
		this.csubNeedPay = csubNeedPay;
		this.setSeted(F.csubNeedPay);
		return this;
	}
	/** 已付金额 [非空]       **/
	public CsUnitBill csubRealPay(Double csubRealPay){
		this.csubRealPay = csubRealPay;
		this.setSeted(F.csubRealPay);
		return this;
	}
	/** 修改人 [非空]       **/
	public CsUnitBill csubEditor(Long csubEditor){
		this.csubEditor = csubEditor;
		this.setSeted(F.csubEditor);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsUnitBill csubUpdateTime(Date csubUpdateTime){
		this.csubUpdateTime = csubUpdateTime;
		this.setSeted(F.csubUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsUnitBill csubAddTime(Date csubAddTime){
		this.csubAddTime = csubAddTime;
		this.setSeted(F.csubAddTime);
		return this;
	}
	/** 财务备注 [非空]       **/
	public CsUnitBill csubSummary(String csubSummary){
		this.csubSummary = csubSummary;
		this.setSeted(F.csubSummary);
		return this;
	}
	/** 状态 [非空]       **/
	public CsUnitBill csubStatus(Short csubStatus){
		this.csubStatus = csubStatus;
		this.setSeted(F.csubStatus);
		return this;
	}
	/** 收入合计 [非空]       **/
	public CsUnitBill csubIncome(Double csubIncome){
		this.csubIncome = csubIncome;
		this.setSeted(F.csubIncome);
		return this;
	}
	/** 支出合计 [非空]       **/
	public CsUnitBill csubPayout(Double csubPayout){
		this.csubPayout = csubPayout;
		this.setSeted(F.csubPayout);
		return this;
	}
	/** 部门 [可空]       **/
	public CsUnitBill csubGroup(Long csubGroup){
		this.csubGroup = csubGroup;
		this.setSeted(F.csubGroup);
		return this;
	}
	/** 里程 [非空]       **/
	public CsUnitBill csubMileage(Double csubMileage){
		this.csubMileage = csubMileage;
		this.setSeted(F.csubMileage);
		return this;
	}
	/** 时长 [非空]       **/
	public CsUnitBill csubDuration(Double csubDuration){
		this.csubDuration = csubDuration;
		this.setSeted(F.csubDuration);
		return this;
	}
	
	//克隆对象
	public CsUnitBill clone(){
		CsUnitBill clone = new CsUnitBill();
		clone.csubId=this.csubId;
		clone.csubHost=this.csubHost;
		clone.csubMonth=this.csubMonth;
		clone.csubUnit=this.csubUnit;
		clone.csubAirS=this.csubAirS;
		clone.csubOrderS=this.csubOrderS;
		clone.csubSpecialS=this.csubSpecialS;
		clone.csubFeeAir=this.csubFeeAir;
		clone.csubFeeOrder=this.csubFeeOrder;
		clone.csubFeeSpecial=this.csubFeeSpecial;
		clone.csubNeedPay=this.csubNeedPay;
		clone.csubRealPay=this.csubRealPay;
		clone.csubEditor=this.csubEditor;
		clone.csubUpdateTime=this.csubUpdateTime;
		clone.csubAddTime=this.csubAddTime;
		clone.csubSummary=this.csubSummary;
		clone.csubStatus=this.csubStatus;
		clone.csubIncome=this.csubIncome;
		clone.csubPayout=this.csubPayout;
		clone.csubGroup=this.csubGroup;
		clone.csubMileage=this.csubMileage;
		clone.csubDuration=this.csubDuration;
		return clone;
	}
	
	
	/**
	 * 根据ID取企业对账
	 * @param id
	 * @return
	 */
	public static @api CsUnitBill get(Long id){		
		return getCsUnitBillById(id);
	}
	/**
	 * 获取所有企业对账
	 * @return
	 */
	public static @api List<CsUnitBill> list(Map params,Integer size){
		return getCsUnitBillList(params,size);
	}
	/**
	 * 获取企业对账分页
	 * @return
	 */
	public static @api Page<CsUnitBill> page(int page,int size,Map params){
		return getCsUnitBillPage(page, size , params);
	}
	/**
	 * 根据查询条件取企业对账
	 * @param params
	 * @return
	 */
	public static @api CsUnitBill Get(Map params){
		return getCsUnitBill(params);
	}
	/**
	 * 获取企业对账数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsUnitBillCount(params);
	}
	/**
	 * 获取企业对账数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsUnitBillEval(eval,params);
	}
	
	/**
	 * 根据ID取企业对账
	 * @param id
	 * @return
	 */
	public static @api CsUnitBill getCsUnitBillById(Long id){		
		CsUnitBill csUnitBill = (CsUnitBill) $.GetRequest("CsUnitBill$"+id);
		if(csUnitBill!=null)
			return csUnitBill;
		com.ccclubs.service.admin.ICsUnitBillService csUnitBillService = $.GetSpringBean("csUnitBillService");		
		return csUnitBillService.getCsUnitBillById(id);
	}
	
	
	/**
	 * 根据ID取企业对账的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsUnitBill.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsUnitBill csUnitBill = get(id);
		if(csUnitBill!=null){
			String strValue = csUnitBill.getCsubMonth$();
			if(!"CsubMonth".equals("CsubMonth"))
				strValue+="("+csUnitBill.getCsubMonth$()+")";
			MemCache.setValue(CsUnitBill.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsubMonth$();
		if(!"CsubMonth".equals("CsubMonth"))
			keyValue+="("+this.getCsubMonth$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有企业对账
	 * @return
	 */
	public static @api List<CsUnitBill> getCsUnitBillList(Map params,Integer size){
		com.ccclubs.service.admin.ICsUnitBillService csUnitBillService = $.GetSpringBean("csUnitBillService");
		return csUnitBillService.getCsUnitBillList(params, size);
	}
	
	/**
	 * 获取企业对账分页
	 * @return
	 */
	public static @api Page<CsUnitBill> getCsUnitBillPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsUnitBillService csUnitBillService = $.GetSpringBean("csUnitBillService");
		return csUnitBillService.getCsUnitBillPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取企业对账
	 * @param params
	 * @return
	 */
	public static @api CsUnitBill getCsUnitBill(Map params){
		com.ccclubs.service.admin.ICsUnitBillService csUnitBillService = $.GetSpringBean("csUnitBillService");
		return csUnitBillService.getCsUnitBill(params);
	}
	
	/**
	 * 获取企业对账数
	 * @return
	 */
	public static @api Long getCsUnitBillCount(Map params){
		com.ccclubs.service.admin.ICsUnitBillService csUnitBillService = $.GetSpringBean("csUnitBillService");
		return csUnitBillService.getCsUnitBillCount(params);
	}
		
		
	/**
	 * 获取企业对账自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsUnitBillEval(String eval,Map params){
		com.ccclubs.service.admin.ICsUnitBillService csUnitBillService = $.GetSpringBean("csUnitBillService");
		return csUnitBillService.getCsUnitBillEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsUnitBill(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsUnitBillService csUnitBillService = $.GetSpringBean("csUnitBillService");
		csUnitBillService.updateCsUnitBillByConfirm(set, where);
	}
	
	
	/**
	 * 保存企业对账对象
	 * @param params
	 * @return
	 */
	public CsUnitBill save(){
		com.ccclubs.service.admin.ICsUnitBillService csUnitBillService = $.GetSpringBean("csUnitBillService");
		if(this.getCsubId()!=null)
			csUnitBillService.updateCsUnitBill(this);
		else
			return csUnitBillService.saveCsUnitBill(this);
		return this;
	}
	
	
	/**
	 * 更新企业对账对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsUnitBillService csUnitBillService = $.GetSpringBean("csUnitBillService");
		csUnitBillService.updateCsUnitBill$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsUnitBillService csUnitBillService = $.GetSpringBean("csUnitBillService");
		if($.equals($.config("logic_delete"),"true"))
			csUnitBillService.removeCsUnitBillById(this.getCsubId());
		else
			csUnitBillService.deleteCsUnitBillById(this.getCsubId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsUnitBillService csUnitBillService = $.GetSpringBean("csUnitBillService");
		return csUnitBillService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsubId(){
		return this.csubId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsubId$(){
		String strValue="";
		 strValue=$.str(this.getCsubId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCsubId(Long csubId){
		this.csubId = csubId;
		this.setSeted(F.csubId);
	}
	/*******************************运营地区**********************************/	
	/**
	* 运营地区 [非空]      
	**/
	public Long getCsubHost(){
		return this.csubHost;
	}
	/**
	* 获取运营地区格式化(toString)
	**/
	public String getCsubHost$(){
		String strValue="";
		if(this.getCsubHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsubHost()));
		}			
	 	 return strValue;
	}
	/**
	* 运营地区 [非空]     
	**/
	public void setCsubHost(Long csubHost){
		this.csubHost = csubHost;
		this.setSeted(F.csubHost);
	}
	/**
	* 获取关联对象[运营地区]
	**/	 			
 	public SrvHost get$csubHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsubHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsubHost()!=null){
			srvHost = SrvHost.get(this.getCsubHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsubHost(), srvHost);
	 	return srvHost;
	}
	/*******************************帐单月份**********************************/	
	/**
	* 帐单月份 [非空]      
	**/
	public String getCsubMonth(){
		return this.csubMonth;
	}
	/**
	* 获取帐单月份格式化(toString)
	**/
	public String getCsubMonth$(){
		String strValue="";
		 strValue=$.str(this.getCsubMonth());
	 	 return strValue;
	}
	/**
	* 帐单月份 [非空]     
	**/
	public void setCsubMonth(String csubMonth){
		this.csubMonth = csubMonth;
		this.setSeted(F.csubMonth);
	}
	/*******************************所属单位**********************************/	
	/**
	* 所属单位 [非空]      
	**/
	public Long getCsubUnit(){
		return this.csubUnit;
	}
	/**
	* 获取所属单位格式化(toString)
	**/
	public String getCsubUnit$(){
		String strValue="";
		if(this.getCsubUnit()!=null){
				strValue+=$.str(CsUnitInfo.getKeyValue(this.getCsubUnit()));
		}			
	 	 return strValue;
	}
	/**
	* 所属单位 [非空]     
	**/
	public void setCsubUnit(Long csubUnit){
		this.csubUnit = csubUnit;
		this.setSeted(F.csubUnit);
	}
	/**
	* 获取关联对象[所属单位]
	**/	 			
 	public CsUnitInfo get$csubUnit(){
 		com.ccclubs.model.CsUnitInfo csUnitInfo = (com.ccclubs.model.CsUnitInfo) $.GetRequest("CsUnitInfo$"+this.getCsubUnit());
 		if(csUnitInfo!=null)
			return csUnitInfo;
		if(this.getCsubUnit()!=null){
			csUnitInfo = CsUnitInfo.get(this.getCsubUnit());
 		}
 		$.SetRequest("CsUnitInfo$"+this.getCsubUnit(), csUnitInfo);
	 	return csUnitInfo;
	}
 	
 	/*******************************反向关联的企业订单列表**********************************/	
	/**
	* 获取与当前对象反向关联的企业订单列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsUnitOrder> getDetails(){
		com.ccclubs.service.admin.ICsUnitOrderService csUnitOrderService = $.GetSpringBean("csUnitOrderService");
		Map params = new HashMap();
		params.put("csuoStatement",this.getCsubId());
		return csUnitOrderService.getCsUnitOrderList(params,-1);
	}
	/**
	* 获取订单明细格式化(toString)
	**/
	public String getDetails$(){
		return "[...]";
	}
	
	/*******************************反向关联的专车订单列表**********************************/	
	/**
	* 获取与当前对象反向关联的专车订单列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsSpecialCar> getSpecials(){
		com.ccclubs.service.admin.ICsSpecialCarService csSpecialCarService = $.GetSpringBean("csSpecialCarService");
		Map params = new HashMap();
		params.put("csscStatement",this.getCsubId());
		return csSpecialCarService.getCsSpecialCarList(params,-1);
	}
	/**
	* 获取专车订单格式化(toString)
	**/
	public String getSpecials$(){
		return "[...]";
	}
	/*******************************接机次数**********************************/	
	/**
	* 接机次数 [非空]      
	**/
	public Integer getCsubAirS(){
		return this.csubAirS;
	}
	/**
	* 获取接机次数格式化(toString)
	**/
	public String getCsubAirS$(){
		String strValue="";
		 strValue=$.str(this.getCsubAirS());
	 	 return strValue;
	}
	/**
	* 接机次数 [非空]     
	**/
	public void setCsubAirS(Integer csubAirS){
		this.csubAirS = csubAirS;
		this.setSeted(F.csubAirS);
	}
	/*******************************订单个数**********************************/	
	/**
	* 订单个数 [非空]      
	**/
	public Integer getCsubOrderS(){
		return this.csubOrderS;
	}
	/**
	* 获取订单个数格式化(toString)
	**/
	public String getCsubOrderS$(){
		String strValue="";
		 strValue=$.str(this.getCsubOrderS());
	 	 return strValue;
	}
	/**
	* 订单个数 [非空]     
	**/
	public void setCsubOrderS(Integer csubOrderS){
		this.csubOrderS = csubOrderS;
		this.setSeted(F.csubOrderS);
	}
	/*******************************专车个数**********************************/	
	/**
	* 专车个数 [非空]      
	**/
	public Integer getCsubSpecialS(){
		return this.csubSpecialS;
	}
	/**
	* 获取专车个数格式化(toString)
	**/
	public String getCsubSpecialS$(){
		String strValue="";
		 strValue=$.str(this.getCsubSpecialS());
	 	 return strValue;
	}
	/**
	* 专车个数 [非空]     
	**/
	public void setCsubSpecialS(Integer csubSpecialS){
		this.csubSpecialS = csubSpecialS;
		this.setSeted(F.csubSpecialS);
	}
	/*******************************接送机费**********************************/	
	/**
	* 接送机费 [非空]      
	**/
	public Double getCsubFeeAir(){
		return this.csubFeeAir;
	}
	/**
	* 获取接送机费格式化(toString)
	**/
	public String getCsubFeeAir$(){
		String strValue="";
		 strValue=$.str(this.getCsubFeeAir());
	 	 return strValue;
	}
	/**
	* 接送机费 [非空]     
	**/
	public void setCsubFeeAir(Double csubFeeAir){
		this.csubFeeAir = csubFeeAir;
		this.setSeted(F.csubFeeAir);
	}
	/*******************************订单费用**********************************/	
	/**
	* 订单费用 [非空]      
	**/
	public Double getCsubFeeOrder(){
		return this.csubFeeOrder;
	}
	/**
	* 获取订单费用格式化(toString)
	**/
	public String getCsubFeeOrder$(){
		String strValue="";
		 strValue=$.str(this.getCsubFeeOrder());
	 	 return strValue;
	}
	/**
	* 订单费用 [非空]     
	**/
	public void setCsubFeeOrder(Double csubFeeOrder){
		this.csubFeeOrder = csubFeeOrder;
		this.setSeted(F.csubFeeOrder);
	}
	/*******************************专车费用**********************************/	
	/**
	* 专车费用 [非空]      
	**/
	public Double getCsubFeeSpecial(){
		return this.csubFeeSpecial;
	}
	/**
	* 获取专车费用格式化(toString)
	**/
	public String getCsubFeeSpecial$(){
		String strValue="";
		 strValue=$.str(this.getCsubFeeSpecial());
	 	 return strValue;
	}
	/**
	* 专车费用 [非空]     
	**/
	public void setCsubFeeSpecial(Double csubFeeSpecial){
		this.csubFeeSpecial = csubFeeSpecial;
		this.setSeted(F.csubFeeSpecial);
	}
	/*******************************应付金额**********************************/	
	/**
	* 应付金额 [非空]      
	**/
	public Double getCsubNeedPay(){
		return this.csubNeedPay;
	}
	/**
	* 获取应付金额格式化(toString)
	**/
	public String getCsubNeedPay$(){
		String strValue="";
		 strValue=$.str(this.getCsubNeedPay());
	 	 return strValue;
	}
	/**
	* 应付金额 [非空]     
	**/
	public void setCsubNeedPay(Double csubNeedPay){
		this.csubNeedPay = csubNeedPay;
		this.setSeted(F.csubNeedPay);
	}
	/*******************************已付金额**********************************/	
	/**
	* 已付金额 [非空]      
	**/
	public Double getCsubRealPay(){
		return this.csubRealPay;
	}
	/**
	* 获取已付金额格式化(toString)
	**/
	public String getCsubRealPay$(){
		String strValue="";
		 strValue=$.str(this.getCsubRealPay());
	 	 return strValue;
	}
	/**
	* 已付金额 [非空]     
	**/
	public void setCsubRealPay(Double csubRealPay){
		this.csubRealPay = csubRealPay;
		this.setSeted(F.csubRealPay);
	}
	/*******************************修改人**********************************/	
	/**
	* 修改人 [非空]      
	**/
	public Long getCsubEditor(){
		return this.csubEditor;
	}
	/**
	* 获取修改人格式化(toString)
	**/
	public String getCsubEditor$(){
		String strValue="";
		if(this.getCsubEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsubEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 修改人 [非空]     
	**/
	public void setCsubEditor(Long csubEditor){
		this.csubEditor = csubEditor;
		this.setSeted(F.csubEditor);
	}
	/**
	* 获取关联对象[修改人]
	**/	 			
 	public SrvUser get$csubEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsubEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsubEditor()!=null){
			srvUser = SrvUser.get(this.getCsubEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCsubEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsubUpdateTime(){
		return this.csubUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsubUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsubUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCsubUpdateTime(Date csubUpdateTime){
		this.csubUpdateTime = csubUpdateTime;
		this.setSeted(F.csubUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsubAddTime(){
		return this.csubAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsubAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsubAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]     
	**/
	public void setCsubAddTime(Date csubAddTime){
		this.csubAddTime = csubAddTime;
		this.setSeted(F.csubAddTime);
	}
	/*******************************财务备注**********************************/	
	/**
	* 财务备注 [非空]      
	**/
	public String getCsubSummary(){
		return this.csubSummary;
	}
	/**
	* 获取财务备注格式化(toString)
	**/
	public String getCsubSummary$(){
		String strValue="";
		 strValue=$.str(this.getCsubSummary());
	 	 return strValue;
	}
	/**
	* 财务备注 [非空]     
	**/
	public void setCsubSummary(String csubSummary){
		this.csubSummary = csubSummary;
		this.setSeted(F.csubSummary);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]      
	**/
	public Short getCsubStatus(){
		return this.csubStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsubStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsubStatus()),"2"))
			 strValue=$.str("已结算");
		 if($.equals($.str(this.getCsubStatus()),"1"))
			 strValue=$.str("待结算");
		 if($.equals($.str(this.getCsubStatus()),"0"))
			 strValue=$.str("未审核");
	 	 return strValue;
	}
	/**
	* 状态 [非空]     
	**/
	public void setCsubStatus(Short csubStatus){
		this.csubStatus = csubStatus;
		this.setSeted(F.csubStatus);
	}
	/*******************************收入合计**********************************/	
	/**
	* 收入合计 [非空]      
	**/
	public Double getCsubIncome(){
		return this.csubIncome;
	}
	/**
	* 获取收入合计格式化(toString)
	**/
	public String getCsubIncome$(){
		String strValue="";
		 strValue=$.str(this.getCsubIncome());
	 	 return strValue;
	}
	/**
	* 收入合计 [非空]     
	**/
	public void setCsubIncome(Double csubIncome){
		this.csubIncome = csubIncome;
		this.setSeted(F.csubIncome);
	}
	/*******************************支出合计**********************************/	
	/**
	* 支出合计 [非空]      
	**/
	public Double getCsubPayout(){
		return this.csubPayout;
	}
	/**
	* 获取支出合计格式化(toString)
	**/
	public String getCsubPayout$(){
		String strValue="";
		 strValue=$.str(this.getCsubPayout());
	 	 return strValue;
	}
	/**
	* 支出合计 [非空]     
	**/
	public void setCsubPayout(Double csubPayout){
		this.csubPayout = csubPayout;
		this.setSeted(F.csubPayout);
	}
	/*******************************部门**********************************/	
	/**
	* 部门 [可空]      
	**/
	public Long getCsubGroup(){
		return this.csubGroup;
	}
	/**
	* 获取部门格式化(toString)
	**/
	public String getCsubGroup$(){
		String strValue="";
		if(this.getCsubGroup()!=null){
				strValue+=$.str(CsUnitGroup.getKeyValue(this.getCsubGroup()));
		}			
	 	 return strValue;
	}
	/**
	* 部门 [可空]     
	**/
	public void setCsubGroup(Long csubGroup){
		this.csubGroup = csubGroup;
		this.setSeted(F.csubGroup);
	}
	/**
	* 获取关联对象[部门]
	**/	 			
 	public CsUnitGroup get$csubGroup(){
 		com.ccclubs.model.CsUnitGroup csUnitGroup = (com.ccclubs.model.CsUnitGroup) $.GetRequest("CsUnitGroup$"+this.getCsubGroup());
 		if(csUnitGroup!=null)
			return csUnitGroup;
		if(this.getCsubGroup()!=null){
			csUnitGroup = CsUnitGroup.get(this.getCsubGroup());
 		}
 		$.SetRequest("CsUnitGroup$"+this.getCsubGroup(), csUnitGroup);
	 	return csUnitGroup;
	}
	/*******************************里程**********************************/	
	/**
	* 里程 [非空]      
	**/
	public Double getCsubMileage(){
		return this.csubMileage;
	}
	/**
	* 获取里程格式化(toString)
	**/
	public String getCsubMileage$(){
		String strValue="";
		 strValue=$.str(this.getCsubMileage());
	 	 return strValue;
	}
	/**
	* 里程 [非空]     
	**/
	public void setCsubMileage(Double csubMileage){
		this.csubMileage = csubMileage;
		this.setSeted(F.csubMileage);
	}
	/*******************************时长**********************************/	
	/**
	* 时长 [非空]      
	**/
	public Double getCsubDuration(){
		return this.csubDuration;
	}
	/**
	* 获取时长格式化(toString)
	**/
	public String getCsubDuration$(){
		String strValue="";
		 strValue=$.str(this.getCsubDuration());
	 	 return strValue;
	}
	/**
	* 时长 [非空]     
	**/
	public void setCsubDuration(Double csubDuration){
		this.csubDuration = csubDuration;
		this.setSeted(F.csubDuration);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsUnitBill.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUnitBill.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUnitBill.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsUnitBill.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsUnitBill.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUnitBill.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUnitBill.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsUnitBill.this);
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
		public M csubId(Object csubId){this.put("csubId", csubId);return this;};
		/** and csub_id is null */
		public M csubIdNull(){if(this.get("csubIdNot")==null)this.put("csubIdNot", "");this.put("csubId", null);return this;};
		/** not .... */
		public M csubIdNot(){this.put("csubIdNot", "not");return this;};
		/** and csub_id >= ? */
		public M csubIdMin(Object min){this.put("csubIdMin", min);return this;};
		/** and csub_id <= ? */
		public M csubIdMax(Object max){this.put("csubIdMax", max);return this;};
		/** 运营地区 [非空]       **/
		public M csubHost(Object csubHost){this.put("csubHost", csubHost);return this;};
		/** and csub_host is null */
		public M csubHostNull(){if(this.get("csubHostNot")==null)this.put("csubHostNot", "");this.put("csubHost", null);return this;};
		/** not .... */
		public M csubHostNot(){this.put("csubHostNot", "not");return this;};
		/** and csub_host >= ? */
		public M csubHostMin(Object min){this.put("csubHostMin", min);return this;};
		/** and csub_host <= ? */
		public M csubHostMax(Object max){this.put("csubHostMax", max);return this;};
		/** 帐单月份 [非空]       **/
		public M csubMonth(Object csubMonth){this.put("csubMonth", csubMonth);return this;};
		/** and csub_month is null */
		public M csubMonthNull(){if(this.get("csubMonthNot")==null)this.put("csubMonthNot", "");this.put("csubMonth", null);return this;};
		/** not .... */
		public M csubMonthNot(){this.put("csubMonthNot", "not");return this;};
		/** 所属单位 [非空]       **/
		public M csubUnit(Object csubUnit){this.put("csubUnit", csubUnit);return this;};
		/** and csub_unit is null */
		public M csubUnitNull(){if(this.get("csubUnitNot")==null)this.put("csubUnitNot", "");this.put("csubUnit", null);return this;};
		/** not .... */
		public M csubUnitNot(){this.put("csubUnitNot", "not");return this;};
		public M csubUnit$on(CsUnitInfo.M value){
			this.put("CsUnitInfo", value);
			this.put("csubUnit$on", value);
			return this;
		};	
		/** and csub_unit >= ? */
		public M csubUnitMin(Object min){this.put("csubUnitMin", min);return this;};
		/** and csub_unit <= ? */
		public M csubUnitMax(Object max){this.put("csubUnitMax", max);return this;};
		/** 接机次数 [非空]       **/
		public M csubAirS(Object csubAirS){this.put("csubAirS", csubAirS);return this;};
		/** and csub_air_s is null */
		public M csubAirSNull(){if(this.get("csubAirSNot")==null)this.put("csubAirSNot", "");this.put("csubAirS", null);return this;};
		/** not .... */
		public M csubAirSNot(){this.put("csubAirSNot", "not");return this;};
		/** and csub_air_s >= ? */
		public M csubAirSMin(Object min){this.put("csubAirSMin", min);return this;};
		/** and csub_air_s <= ? */
		public M csubAirSMax(Object max){this.put("csubAirSMax", max);return this;};
		/** 订单个数 [非空]       **/
		public M csubOrderS(Object csubOrderS){this.put("csubOrderS", csubOrderS);return this;};
		/** and csub_order_s is null */
		public M csubOrderSNull(){if(this.get("csubOrderSNot")==null)this.put("csubOrderSNot", "");this.put("csubOrderS", null);return this;};
		/** not .... */
		public M csubOrderSNot(){this.put("csubOrderSNot", "not");return this;};
		/** and csub_order_s >= ? */
		public M csubOrderSMin(Object min){this.put("csubOrderSMin", min);return this;};
		/** and csub_order_s <= ? */
		public M csubOrderSMax(Object max){this.put("csubOrderSMax", max);return this;};
		/** 专车个数 [非空]       **/
		public M csubSpecialS(Object csubSpecialS){this.put("csubSpecialS", csubSpecialS);return this;};
		/** and csub_special_s is null */
		public M csubSpecialSNull(){if(this.get("csubSpecialSNot")==null)this.put("csubSpecialSNot", "");this.put("csubSpecialS", null);return this;};
		/** not .... */
		public M csubSpecialSNot(){this.put("csubSpecialSNot", "not");return this;};
		/** and csub_special_s >= ? */
		public M csubSpecialSMin(Object min){this.put("csubSpecialSMin", min);return this;};
		/** and csub_special_s <= ? */
		public M csubSpecialSMax(Object max){this.put("csubSpecialSMax", max);return this;};
		/** 接送机费 [非空]       **/
		public M csubFeeAir(Object csubFeeAir){this.put("csubFeeAir", csubFeeAir);return this;};
		/** and csub_fee_air is null */
		public M csubFeeAirNull(){if(this.get("csubFeeAirNot")==null)this.put("csubFeeAirNot", "");this.put("csubFeeAir", null);return this;};
		/** not .... */
		public M csubFeeAirNot(){this.put("csubFeeAirNot", "not");return this;};
		/** and csub_fee_air >= ? */
		public M csubFeeAirMin(Object min){this.put("csubFeeAirMin", min);return this;};
		/** and csub_fee_air <= ? */
		public M csubFeeAirMax(Object max){this.put("csubFeeAirMax", max);return this;};
		/** 订单费用 [非空]       **/
		public M csubFeeOrder(Object csubFeeOrder){this.put("csubFeeOrder", csubFeeOrder);return this;};
		/** and csub_fee_order is null */
		public M csubFeeOrderNull(){if(this.get("csubFeeOrderNot")==null)this.put("csubFeeOrderNot", "");this.put("csubFeeOrder", null);return this;};
		/** not .... */
		public M csubFeeOrderNot(){this.put("csubFeeOrderNot", "not");return this;};
		/** and csub_fee_order >= ? */
		public M csubFeeOrderMin(Object min){this.put("csubFeeOrderMin", min);return this;};
		/** and csub_fee_order <= ? */
		public M csubFeeOrderMax(Object max){this.put("csubFeeOrderMax", max);return this;};
		/** 专车费用 [非空]       **/
		public M csubFeeSpecial(Object csubFeeSpecial){this.put("csubFeeSpecial", csubFeeSpecial);return this;};
		/** and csub_fee_special is null */
		public M csubFeeSpecialNull(){if(this.get("csubFeeSpecialNot")==null)this.put("csubFeeSpecialNot", "");this.put("csubFeeSpecial", null);return this;};
		/** not .... */
		public M csubFeeSpecialNot(){this.put("csubFeeSpecialNot", "not");return this;};
		/** and csub_fee_special >= ? */
		public M csubFeeSpecialMin(Object min){this.put("csubFeeSpecialMin", min);return this;};
		/** and csub_fee_special <= ? */
		public M csubFeeSpecialMax(Object max){this.put("csubFeeSpecialMax", max);return this;};
		/** 应付金额 [非空]       **/
		public M csubNeedPay(Object csubNeedPay){this.put("csubNeedPay", csubNeedPay);return this;};
		/** and csub_need_pay is null */
		public M csubNeedPayNull(){if(this.get("csubNeedPayNot")==null)this.put("csubNeedPayNot", "");this.put("csubNeedPay", null);return this;};
		/** not .... */
		public M csubNeedPayNot(){this.put("csubNeedPayNot", "not");return this;};
		/** and csub_need_pay >= ? */
		public M csubNeedPayMin(Object min){this.put("csubNeedPayMin", min);return this;};
		/** and csub_need_pay <= ? */
		public M csubNeedPayMax(Object max){this.put("csubNeedPayMax", max);return this;};
		/** 已付金额 [非空]       **/
		public M csubRealPay(Object csubRealPay){this.put("csubRealPay", csubRealPay);return this;};
		/** and csub_real_pay is null */
		public M csubRealPayNull(){if(this.get("csubRealPayNot")==null)this.put("csubRealPayNot", "");this.put("csubRealPay", null);return this;};
		/** not .... */
		public M csubRealPayNot(){this.put("csubRealPayNot", "not");return this;};
		/** and csub_real_pay >= ? */
		public M csubRealPayMin(Object min){this.put("csubRealPayMin", min);return this;};
		/** and csub_real_pay <= ? */
		public M csubRealPayMax(Object max){this.put("csubRealPayMax", max);return this;};
		/** 修改人 [非空]       **/
		public M csubEditor(Object csubEditor){this.put("csubEditor", csubEditor);return this;};
		/** and csub_editor is null */
		public M csubEditorNull(){if(this.get("csubEditorNot")==null)this.put("csubEditorNot", "");this.put("csubEditor", null);return this;};
		/** not .... */
		public M csubEditorNot(){this.put("csubEditorNot", "not");return this;};
		public M csubEditor$on(SrvUser.M value){
			this.put("SrvUser", value);
			this.put("csubEditor$on", value);
			return this;
		};	
		/** and csub_editor >= ? */
		public M csubEditorMin(Object min){this.put("csubEditorMin", min);return this;};
		/** and csub_editor <= ? */
		public M csubEditorMax(Object max){this.put("csubEditorMax", max);return this;};
		/** 修改时间 [非空]       **/
		public M csubUpdateTime(Object csubUpdateTime){this.put("csubUpdateTime", csubUpdateTime);return this;};
		/** and csub_update_time is null */
		public M csubUpdateTimeNull(){if(this.get("csubUpdateTimeNot")==null)this.put("csubUpdateTimeNot", "");this.put("csubUpdateTime", null);return this;};
		/** not .... */
		public M csubUpdateTimeNot(){this.put("csubUpdateTimeNot", "not");return this;};
		/** and csub_update_time >= ? */
 		public M csubUpdateTimeStart(Object start){this.put("csubUpdateTimeStart", start);return this;};			
 		/** and csub_update_time <= ? */
 		public M csubUpdateTimeEnd(Object end){this.put("csubUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csubAddTime(Object csubAddTime){this.put("csubAddTime", csubAddTime);return this;};
		/** and csub_add_time is null */
		public M csubAddTimeNull(){if(this.get("csubAddTimeNot")==null)this.put("csubAddTimeNot", "");this.put("csubAddTime", null);return this;};
		/** not .... */
		public M csubAddTimeNot(){this.put("csubAddTimeNot", "not");return this;};
		/** and csub_add_time >= ? */
 		public M csubAddTimeStart(Object start){this.put("csubAddTimeStart", start);return this;};			
 		/** and csub_add_time <= ? */
 		public M csubAddTimeEnd(Object end){this.put("csubAddTimeEnd", end);return this;};
		/** 财务备注 [非空]       **/
		public M csubSummary(Object csubSummary){this.put("csubSummary", csubSummary);return this;};
		/** and csub_summary is null */
		public M csubSummaryNull(){if(this.get("csubSummaryNot")==null)this.put("csubSummaryNot", "");this.put("csubSummary", null);return this;};
		/** not .... */
		public M csubSummaryNot(){this.put("csubSummaryNot", "not");return this;};
		/** 状态 [非空]       **/
		public M csubStatus(Object csubStatus){this.put("csubStatus", csubStatus);return this;};
		/** and csub_status is null */
		public M csubStatusNull(){if(this.get("csubStatusNot")==null)this.put("csubStatusNot", "");this.put("csubStatus", null);return this;};
		/** not .... */
		public M csubStatusNot(){this.put("csubStatusNot", "not");return this;};
		/** and csub_status >= ? */
		public M csubStatusMin(Object min){this.put("csubStatusMin", min);return this;};
		/** and csub_status <= ? */
		public M csubStatusMax(Object max){this.put("csubStatusMax", max);return this;};
		/** 收入合计 [非空]       **/
		public M csubIncome(Object csubIncome){this.put("csubIncome", csubIncome);return this;};
		/** and csub_income is null */
		public M csubIncomeNull(){if(this.get("csubIncomeNot")==null)this.put("csubIncomeNot", "");this.put("csubIncome", null);return this;};
		/** not .... */
		public M csubIncomeNot(){this.put("csubIncomeNot", "not");return this;};
		/** and csub_income >= ? */
		public M csubIncomeMin(Object min){this.put("csubIncomeMin", min);return this;};
		/** and csub_income <= ? */
		public M csubIncomeMax(Object max){this.put("csubIncomeMax", max);return this;};
		/** 支出合计 [非空]       **/
		public M csubPayout(Object csubPayout){this.put("csubPayout", csubPayout);return this;};
		/** and csub_payout is null */
		public M csubPayoutNull(){if(this.get("csubPayoutNot")==null)this.put("csubPayoutNot", "");this.put("csubPayout", null);return this;};
		/** not .... */
		public M csubPayoutNot(){this.put("csubPayoutNot", "not");return this;};
		/** and csub_payout >= ? */
		public M csubPayoutMin(Object min){this.put("csubPayoutMin", min);return this;};
		/** and csub_payout <= ? */
		public M csubPayoutMax(Object max){this.put("csubPayoutMax", max);return this;};
		/** 部门 [可空]       **/
		public M csubGroup(Object csubGroup){this.put("csubGroup", csubGroup);return this;};
		/** and csub_group is null */
		public M csubGroupNull(){if(this.get("csubGroupNot")==null)this.put("csubGroupNot", "");this.put("csubGroup", null);return this;};
		/** not .... */
		public M csubGroupNot(){this.put("csubGroupNot", "not");return this;};
		public M csubGroup$on(CsUnitGroup.M value){
			this.put("CsUnitGroup", value);
			this.put("csubGroup$on", value);
			return this;
		};	
		/** and csub_group >= ? */
		public M csubGroupMin(Object min){this.put("csubGroupMin", min);return this;};
		/** and csub_group <= ? */
		public M csubGroupMax(Object max){this.put("csubGroupMax", max);return this;};
		/** 里程 [非空]       **/
		public M csubMileage(Object csubMileage){this.put("csubMileage", csubMileage);return this;};
		/** and csub_mileage is null */
		public M csubMileageNull(){if(this.get("csubMileageNot")==null)this.put("csubMileageNot", "");this.put("csubMileage", null);return this;};
		/** not .... */
		public M csubMileageNot(){this.put("csubMileageNot", "not");return this;};
		/** and csub_mileage >= ? */
		public M csubMileageMin(Object min){this.put("csubMileageMin", min);return this;};
		/** and csub_mileage <= ? */
		public M csubMileageMax(Object max){this.put("csubMileageMax", max);return this;};
		/** 时长 [非空]       **/
		public M csubDuration(Object csubDuration){this.put("csubDuration", csubDuration);return this;};
		/** and csub_duration is null */
		public M csubDurationNull(){if(this.get("csubDurationNot")==null)this.put("csubDurationNot", "");this.put("csubDuration", null);return this;};
		/** not .... */
		public M csubDurationNot(){this.put("csubDurationNot", "not");return this;};
		/** and csub_duration >= ? */
		public M csubDurationMin(Object min){this.put("csubDurationMin", min);return this;};
		/** and csub_duration <= ? */
		public M csubDurationMax(Object max){this.put("csubDurationMax", max);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有企业对账 **/
		public @api List<CsUnitBill> list(Integer size){
			return getCsUnitBillList(this,size);
		}
		/** 获取企业对账分页 **/
		public @api Page<CsUnitBill> page(int page,int size){
			return getCsUnitBillPage(page, size , this);
		}
		/** 根据查询条件取企业对账 **/
		public @api CsUnitBill get(){
			return getCsUnitBill(this);
		}
		/** 获取企业对账数 **/
		public @api Long count(){
			return getCsUnitBillCount(this);
		}
		/** 获取企业对账表达式 **/
		public @api <T> T eval(String strEval){
			return getCsUnitBillEval(strEval,this);
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
			updateCsUnitBill(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csubId="csubId";
		/** 运营地区 [非空]       **/
		public final static @type(Long.class)  String csubHost="csubHost";
		/** 帐单月份 [非空]       **/
		public final static @type(String.class)  String csubMonth="csubMonth";
		/** 所属单位 [非空]       **/
		public final static @type(Long.class)  String csubUnit="csubUnit";
		/** 接机次数 [非空]       **/
		public final static @type(Integer.class)  String csubAirS="csubAirS";
		/** 订单个数 [非空]       **/
		public final static @type(Integer.class)  String csubOrderS="csubOrderS";
		/** 专车个数 [非空]       **/
		public final static @type(Integer.class)  String csubSpecialS="csubSpecialS";
		/** 接送机费 [非空]       **/
		public final static @type(Double.class)  String csubFeeAir="csubFeeAir";
		/** 订单费用 [非空]       **/
		public final static @type(Double.class)  String csubFeeOrder="csubFeeOrder";
		/** 专车费用 [非空]       **/
		public final static @type(Double.class)  String csubFeeSpecial="csubFeeSpecial";
		/** 应付金额 [非空]       **/
		public final static @type(Double.class)  String csubNeedPay="csubNeedPay";
		/** 已付金额 [非空]       **/
		public final static @type(Double.class)  String csubRealPay="csubRealPay";
		/** 修改人 [非空]       **/
		public final static @type(Long.class)  String csubEditor="csubEditor";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csubUpdateTime="csubUpdateTime";
		/** and csub_update_time >= ? */
 		public final static @type(Date.class) String csubUpdateTimeStart="csubUpdateTimeStart";
 		/** and csub_update_time <= ? */
 		public final static @type(Date.class) String csubUpdateTimeEnd="csubUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csubAddTime="csubAddTime";
		/** and csub_add_time >= ? */
 		public final static @type(Date.class) String csubAddTimeStart="csubAddTimeStart";
 		/** and csub_add_time <= ? */
 		public final static @type(Date.class) String csubAddTimeEnd="csubAddTimeEnd";
		/** 财务备注 [非空]       **/
		public final static @type(String.class)  String csubSummary="csubSummary";
		/** 状态 [非空]       **/
		public final static @type(Short.class)  String csubStatus="csubStatus";
		/** 收入合计 [非空]       **/
		public final static @type(Double.class)  String csubIncome="csubIncome";
		/** 支出合计 [非空]       **/
		public final static @type(Double.class)  String csubPayout="csubPayout";
		/** 部门 [可空]       **/
		public final static @type(Long.class)  String csubGroup="csubGroup";
		/** 里程 [非空]       **/
		public final static @type(Double.class)  String csubMileage="csubMileage";
		/** 时长 [非空]       **/
		public final static @type(Double.class)  String csubDuration="csubDuration";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csubId="csub_id";
		/** 运营地区 [非空]       **/
		public final static String csubHost="csub_host";
		/** 帐单月份 [非空]       **/
		public final static String csubMonth="csub_month";
		/** 所属单位 [非空]       **/
		public final static String csubUnit="csub_unit";
		/** 接机次数 [非空]       **/
		public final static String csubAirS="csub_air_s";
		/** 订单个数 [非空]       **/
		public final static String csubOrderS="csub_order_s";
		/** 专车个数 [非空]       **/
		public final static String csubSpecialS="csub_special_s";
		/** 接送机费 [非空]       **/
		public final static String csubFeeAir="csub_fee_air";
		/** 订单费用 [非空]       **/
		public final static String csubFeeOrder="csub_fee_order";
		/** 专车费用 [非空]       **/
		public final static String csubFeeSpecial="csub_fee_special";
		/** 应付金额 [非空]       **/
		public final static String csubNeedPay="csub_need_pay";
		/** 已付金额 [非空]       **/
		public final static String csubRealPay="csub_real_pay";
		/** 修改人 [非空]       **/
		public final static String csubEditor="csub_editor";
		/** 修改时间 [非空]       **/
		public final static String csubUpdateTime="csub_update_time";
		/** 添加时间 [非空]       **/
		public final static String csubAddTime="csub_add_time";
		/** 财务备注 [非空]       **/
		public final static String csubSummary="csub_summary";
		/** 状态 [非空]       **/
		public final static String csubStatus="csub_status";
		/** 收入合计 [非空]       **/
		public final static String csubIncome="csub_income";
		/** 支出合计 [非空]       **/
		public final static String csubPayout="csub_payout";
		/** 部门 [可空]       **/
		public final static String csubGroup="csub_group";
		/** 里程 [非空]       **/
		public final static String csubMileage="csub_mileage";
		/** 时长 [非空]       **/
		public final static String csubDuration="csub_duration";
		
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
				$.Set(name,CsUnitBill.getCsUnitBill(params));
			else
				$.Set(name,CsUnitBill.getCsUnitBillList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取企业对账数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsUnitBill) $.GetRequest("CsUnitBill$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsUnitBill.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsUnitBill.getCsUnitBill(params);
			else
				value = CsUnitBill.getCsUnitBillList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsUnitBill.Get($.add(CsUnitBill.F.csubId,param));
		else if(!$.empty(param.toString()))
			value = CsUnitBill.get(Long.valueOf(param.toString()));
		$.SetRequest("CsUnitBill$"+param.hashCode(), value);
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
	public void mergeSet(CsUnitBill old){
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