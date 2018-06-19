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

@namespace("service/longorder")
public @caption("长租订单") @table("cs_long_order") class CsLongOrder implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("订单编号") @column("cslo_id")   @primary  @note("  ") Long csloId;// 主键 非空     
	private @caption("城市") @column("cslo_host")    @relate("$csloHost") @RelateClass(SrvHost.class)  @note("  ") Long csloHost;// 非空     
 	private SrvHost $csloHost;//关联对象[运营城市]
	private @caption("合同号") @column("cslo_pack_no")    @note("  ") String csloPackNo;//     
	private @caption("会员帐号") @column("cslo_member")    @relate("$csloMember") @RelateClass(CsMember.class)  @note("  ") Long csloMember;// 非空    ${basePath}${proname}/user/member_inquire.do 
 	private CsMember $csloMember;//关联对象[会员帐号]
	private @caption("合同备注") @column("cslo_pack_profile")    @note("  ") String csloPackProfile;//     
	private @caption("合同图片") @column("cslo_pack_image")    @note("  ") String csloPackImage;//     
	//private @caption("车辆明细") @RelateClass(CsLongOrderDetail.class) List<CsLongOrderDetail> details;//反向关联的长单明细列表
	//private @caption("缴费明细") @RelateClass(CsLongOrderFee.class) List<CsLongOrderFee> fees;//反向关联的长单缴费列表
	private @caption("车辆数") @column("cslo_count")    @note("  ") Integer csloCount;// 非空     
	private @caption("订单租期") @column("cslo_lease_term")    @note("  ") Double csloLeaseTerm;//(单位：个月) 非空     
	private @caption("押金合计") @column("cslo_deposit")    @note("  ") Double csloDeposit;// 非空     
	private @caption("付款周期") @column("cslo_pay_cycle")    @note(" 0:一次付清 1:按月付 2:按季付 3:半年付 4:按年付  ") Short csloPayCycle;// 非空 0:一次付清 1:按月付 2:按季付 3:半年付 4:按年付     
	private @caption("应付金额") @column("cslo_need_pay")    @note("  ") Double csloNeedPay;// 非空     
	private @caption("实付金额") @column("cslo_real_pay")    @note("  ") Double csloRealPay;// 非空     
	private @caption("合同签订时间") @column("cslo_pact_time")    @note("  ") Date csloPactTime;// 非空     
	private @caption("合同开始时间") @column("cslo_start_time")    @note("  ") Date csloStartTime;// 非空     
	private @caption("合同结束时间") @column("cslo_finish_time")    @note("  ") Date csloFinishTime;// 非空     
	private @caption("修改时间") @column("cslo_update_time")    @note("  ") Date csloUpdateTime;// 非空     
	private @caption("添加时间") @column("cslo_add_time")    @note("  ") Date csloAddTime;// 非空     
	private @caption("序列号") @column("cslo_serial")    @note("  ") String csloSerial;//     
	private @caption("订单备注") @column("cslo_remark")    @note("  ") String csloRemark;//     
	private @caption("操作人") @column("cslo_editor")    @relate("$csloEditor") @RelateClass(SrvUser.class)  @note("  ") Long csloEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csloEditor;//关联对象[用户]
	private @caption("报警信息") @column("cslo_error")    @note("  ") String csloError;//     
	private @caption("状态") @column("cslo_status")    @note(" 0:已预定 1:使用中 2:已还车 3:已取消 4:已完成 5:待处理 6:已退款 7:已作废  ") Short csloStatus;// 非空 0:已预定 1:使用中 2:已还车 3:已取消 4:已完成 5:待处理 6:已退款 7:已作废     
	
	//默认构造函数
	public CsLongOrder(){
	
	}
	
	//主键构造函数
	public CsLongOrder(Long id){
		this.csloId = id;
	}
	
	/**所有字段构造函数 CsLongOrder(csloHost,csloPackNo,csloMember,csloPackProfile,csloPackImage,csloCount,csloLeaseTerm,csloDeposit,csloPayCycle,csloNeedPay,csloRealPay,csloPactTime,csloStartTime,csloFinishTime,csloUpdateTime,csloAddTime,csloSerial,csloRemark,csloEditor,csloError,csloStatus)
	 CsLongOrder(
	 	$.getLong("csloHost")//城市 [非空]
	 	,$.getString("csloPackNo")//合同号
	 	,$.getLong("csloMember")//会员帐号 [非空]
	 	,$.getString("csloPackProfile")//合同备注
	 	,$.getString("csloPackImage")//合同图片
	 	,$.getInteger("csloCount")//车辆数 [非空]
	 	,$.getDouble("csloLeaseTerm")//订单租期 [非空]
	 	,$.getDouble("csloDeposit")//押金合计 [非空]
	 	,$.getShort("csloPayCycle")//付款周期 [非空]
	 	,$.getDouble("csloNeedPay")//应付金额 [非空]
	 	,$.getDouble("csloRealPay")//实付金额 [非空]
	 	,$.getDate("csloPactTime")//合同签订时间 [非空]
	 	,$.getDate("csloStartTime")//合同开始时间 [非空]
	 	,$.getDate("csloFinishTime")//合同结束时间 [非空]
	 	,$.getDate("csloUpdateTime")//修改时间 [非空]
	 	,$.getDate("csloAddTime")//添加时间 [非空]
	 	,$.getString("csloSerial")//序列号
	 	,$.getString("csloRemark")//订单备注
	 	,$.getLong("csloEditor")//操作人 [非空]
	 	,$.getString("csloError")//报警信息
	 	,$.getShort("csloStatus")//状态 [非空]
	 )
	**/
	public CsLongOrder(Long csloHost,String csloPackNo,Long csloMember,String csloPackProfile,String csloPackImage,Integer csloCount,Double csloLeaseTerm,Double csloDeposit,Short csloPayCycle,Double csloNeedPay,Double csloRealPay,Date csloPactTime,Date csloStartTime,Date csloFinishTime,Date csloUpdateTime,Date csloAddTime,String csloSerial,String csloRemark,Long csloEditor,String csloError,Short csloStatus){
		this.csloHost=csloHost;
		this.csloPackNo=csloPackNo;
		this.csloMember=csloMember;
		this.csloPackProfile=csloPackProfile;
		this.csloPackImage=csloPackImage;
		this.csloCount=csloCount;
		this.csloLeaseTerm=csloLeaseTerm;
		this.csloDeposit=csloDeposit;
		this.csloPayCycle=csloPayCycle;
		this.csloNeedPay=csloNeedPay;
		this.csloRealPay=csloRealPay;
		this.csloPactTime=csloPactTime;
		this.csloStartTime=csloStartTime;
		this.csloFinishTime=csloFinishTime;
		this.csloUpdateTime=csloUpdateTime;
		this.csloAddTime=csloAddTime;
		this.csloSerial=csloSerial;
		this.csloRemark=csloRemark;
		this.csloEditor=csloEditor;
		this.csloError=csloError;
		this.csloStatus=csloStatus;
	}
	
	//设置非空字段
	public CsLongOrder setNotNull(Long csloHost,Long csloMember,Integer csloCount,Double csloLeaseTerm,Double csloDeposit,Short csloPayCycle,Double csloNeedPay,Double csloRealPay,Date csloPactTime,Date csloStartTime,Date csloFinishTime,Date csloUpdateTime,Date csloAddTime,Long csloEditor,Short csloStatus){
		this.csloHost=csloHost;
		this.csloMember=csloMember;
		this.csloCount=csloCount;
		this.csloLeaseTerm=csloLeaseTerm;
		this.csloDeposit=csloDeposit;
		this.csloPayCycle=csloPayCycle;
		this.csloNeedPay=csloNeedPay;
		this.csloRealPay=csloRealPay;
		this.csloPactTime=csloPactTime;
		this.csloStartTime=csloStartTime;
		this.csloFinishTime=csloFinishTime;
		this.csloUpdateTime=csloUpdateTime;
		this.csloAddTime=csloAddTime;
		this.csloEditor=csloEditor;
		this.csloStatus=csloStatus;
		return this;
	}
	/** 订单编号 [非空]       **/
	public CsLongOrder csloId(Long csloId){
		this.csloId = csloId;
		this.setSeted(F.csloId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsLongOrder csloHost(Long csloHost){
		this.csloHost = csloHost;
		this.setSeted(F.csloHost);
		return this;
	}
	/** 合同号        **/
	public CsLongOrder csloPackNo(String csloPackNo){
		this.csloPackNo = csloPackNo;
		this.setSeted(F.csloPackNo);
		return this;
	}
	/** 会员帐号 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
	public CsLongOrder csloMember(Long csloMember){
		this.csloMember = csloMember;
		this.setSeted(F.csloMember);
		return this;
	}
	/** 合同备注        **/
	public CsLongOrder csloPackProfile(String csloPackProfile){
		this.csloPackProfile = csloPackProfile;
		this.setSeted(F.csloPackProfile);
		return this;
	}
	/** 合同图片        **/
	public CsLongOrder csloPackImage(String csloPackImage){
		this.csloPackImage = csloPackImage;
		this.setSeted(F.csloPackImage);
		return this;
	}
	/** 车辆数 [非空]       **/
	public CsLongOrder csloCount(Integer csloCount){
		this.csloCount = csloCount;
		this.setSeted(F.csloCount);
		return this;
	}
	/** 订单租期 [非空]       **/
	public CsLongOrder csloLeaseTerm(Double csloLeaseTerm){
		this.csloLeaseTerm = csloLeaseTerm;
		this.setSeted(F.csloLeaseTerm);
		return this;
	}
	/** 押金合计 [非空]       **/
	public CsLongOrder csloDeposit(Double csloDeposit){
		this.csloDeposit = csloDeposit;
		this.setSeted(F.csloDeposit);
		return this;
	}
	/** 付款周期 [非空]   0:一次付清 1:按月付 2:按季付 3:半年付 4:按年付     **/
	public CsLongOrder csloPayCycle(Short csloPayCycle){
		this.csloPayCycle = csloPayCycle;
		this.setSeted(F.csloPayCycle);
		return this;
	}
	/** 应付金额 [非空]       **/
	public CsLongOrder csloNeedPay(Double csloNeedPay){
		this.csloNeedPay = csloNeedPay;
		this.setSeted(F.csloNeedPay);
		return this;
	}
	/** 实付金额 [非空]       **/
	public CsLongOrder csloRealPay(Double csloRealPay){
		this.csloRealPay = csloRealPay;
		this.setSeted(F.csloRealPay);
		return this;
	}
	/** 合同签订时间 [非空]       **/
	public CsLongOrder csloPactTime(Date csloPactTime){
		this.csloPactTime = csloPactTime;
		this.setSeted(F.csloPactTime);
		return this;
	}
	/** 合同开始时间 [非空]       **/
	public CsLongOrder csloStartTime(Date csloStartTime){
		this.csloStartTime = csloStartTime;
		this.setSeted(F.csloStartTime);
		return this;
	}
	/** 合同结束时间 [非空]       **/
	public CsLongOrder csloFinishTime(Date csloFinishTime){
		this.csloFinishTime = csloFinishTime;
		this.setSeted(F.csloFinishTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsLongOrder csloUpdateTime(Date csloUpdateTime){
		this.csloUpdateTime = csloUpdateTime;
		this.setSeted(F.csloUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsLongOrder csloAddTime(Date csloAddTime){
		this.csloAddTime = csloAddTime;
		this.setSeted(F.csloAddTime);
		return this;
	}
	/** 序列号        **/
	public CsLongOrder csloSerial(String csloSerial){
		this.csloSerial = csloSerial;
		this.setSeted(F.csloSerial);
		return this;
	}
	/** 订单备注        **/
	public CsLongOrder csloRemark(String csloRemark){
		this.csloRemark = csloRemark;
		this.setSeted(F.csloRemark);
		return this;
	}
	/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsLongOrder csloEditor(Long csloEditor){
		this.csloEditor = csloEditor;
		this.setSeted(F.csloEditor);
		return this;
	}
	/** 报警信息        **/
	public CsLongOrder csloError(String csloError){
		this.csloError = csloError;
		this.setSeted(F.csloError);
		return this;
	}
	/** 状态 [非空]   0:已预定 1:使用中 2:已还车 3:已取消 4:已完成 5:待处理 6:已退款 7:已作废     **/
	public CsLongOrder csloStatus(Short csloStatus){
		this.csloStatus = csloStatus;
		this.setSeted(F.csloStatus);
		return this;
	}
	
	
	//克隆对象
	public CsLongOrder clone(){
		CsLongOrder clone = new CsLongOrder();
		clone.csloHost=this.csloHost;
		clone.csloMember=this.csloMember;
		clone.csloCount=this.csloCount;
		clone.csloLeaseTerm=this.csloLeaseTerm;
		clone.csloDeposit=this.csloDeposit;
		clone.csloPayCycle=this.csloPayCycle;
		clone.csloNeedPay=this.csloNeedPay;
		clone.csloRealPay=this.csloRealPay;
		clone.csloPactTime=this.csloPactTime;
		clone.csloStartTime=this.csloStartTime;
		clone.csloFinishTime=this.csloFinishTime;
		clone.csloUpdateTime=this.csloUpdateTime;
		clone.csloAddTime=this.csloAddTime;
		clone.csloEditor=this.csloEditor;
		clone.csloStatus=this.csloStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取长租订单
	 * @param id
	 * @return
	 */
	public static @api CsLongOrder get(Long id){		
		return getCsLongOrderById(id);
	}
	/**
	 * 获取所有长租订单
	 * @return
	 */
	public static @api List<CsLongOrder> list(Map params,Integer size){
		return getCsLongOrderList(params,size);
	}
	/**
	 * 获取长租订单分页
	 * @return
	 */
	public static @api Page<CsLongOrder> page(int page,int size,Map params){
		return getCsLongOrderPage(page, size , params);
	}
	/**
	 * 根据查询条件取长租订单
	 * @param params
	 * @return
	 */
	public static @api CsLongOrder Get(Map params){
		return getCsLongOrder(params);
	}
	/**
	 * 获取长租订单数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsLongOrderCount(params);
	}
	/**
	 * 获取长租订单数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsLongOrderEval(eval,params);
	}
	
	/**
	 * 根据ID取长租订单
	 * @param id
	 * @return
	 */
	public static @api CsLongOrder getCsLongOrderById(Long id){		
		CsLongOrder csLongOrder = (CsLongOrder) $.GetRequest("CsLongOrder$"+id);
		if(csLongOrder!=null)
			return csLongOrder;
		com.ccclubs.service.admin.ICsLongOrderService csLongOrderService = $.GetSpringBean("csLongOrderService");		
		return csLongOrderService.getCsLongOrderById(id);
	}
	
	
	/**
	 * 根据ID取长租订单的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsLongOrder.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsLongOrder csLongOrder = get(id);
		if(csLongOrder!=null){
			String strValue = csLongOrder.getCsloId$();
			if(!"CsloId".equals("CsloId"))
				strValue+="("+csLongOrder.getCsloId$()+")";
			if(!"CsloId".equals("CsloStatus"))
				strValue+="("+csLongOrder.getCsloStatus$()+")";
			MemCache.setValue(CsLongOrder.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsloId$();
		if(!"CsloId".equals("CsloId"))
			keyValue+="("+this.getCsloId$()+")";
		if(!"CsloId".equals("CsloStatus"))
			keyValue+="("+this.getCsloStatus$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有长租订单
	 * @return
	 */
	public static @api List<CsLongOrder> getCsLongOrderList(Map params,Integer size){
		com.ccclubs.service.admin.ICsLongOrderService csLongOrderService = $.GetSpringBean("csLongOrderService");
		return csLongOrderService.getCsLongOrderList(params, size);
	}
	
	/**
	 * 获取长租订单分页
	 * @return
	 */
	public static @api Page<CsLongOrder> getCsLongOrderPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsLongOrderService csLongOrderService = $.GetSpringBean("csLongOrderService");
		return csLongOrderService.getCsLongOrderPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取长租订单
	 * @param params
	 * @return
	 */
	public static @api CsLongOrder getCsLongOrder(Map params){
		com.ccclubs.service.admin.ICsLongOrderService csLongOrderService = $.GetSpringBean("csLongOrderService");
		return csLongOrderService.getCsLongOrder(params);
	}
	
	/**
	 * 获取长租订单数
	 * @return
	 */
	public static @api Long getCsLongOrderCount(Map params){
		com.ccclubs.service.admin.ICsLongOrderService csLongOrderService = $.GetSpringBean("csLongOrderService");
		return csLongOrderService.getCsLongOrderCount(params);
	}
		
		
	/**
	 * 获取长租订单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsLongOrderEval(String eval,Map params){
		com.ccclubs.service.admin.ICsLongOrderService csLongOrderService = $.GetSpringBean("csLongOrderService");
		return csLongOrderService.getCsLongOrderEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsLongOrder(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsLongOrderService csLongOrderService = $.GetSpringBean("csLongOrderService");
		csLongOrderService.updateCsLongOrderByConfirm(set, where);
	}
	
	
	/**
	 * 保存长租订单对象
	 * @param params
	 * @return
	 */
	public CsLongOrder save(){
		com.ccclubs.service.admin.ICsLongOrderService csLongOrderService = $.GetSpringBean("csLongOrderService");
		if(this.getCsloId()!=null)
			csLongOrderService.updateCsLongOrder(this);
		else
			return csLongOrderService.saveCsLongOrder(this);
		return this;
	}
	
	
	/**
	 * 更新长租订单对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsLongOrderService csLongOrderService = $.GetSpringBean("csLongOrderService");
		csLongOrderService.updateCsLongOrder$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsLongOrderService csLongOrderService = $.GetSpringBean("csLongOrderService");
		if($.equals($.config("logic_delete"),"true"))
			csLongOrderService.removeCsLongOrderById(this.getCsloId());
		else
			csLongOrderService.deleteCsLongOrderById(this.getCsloId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsLongOrderService csLongOrderService = $.GetSpringBean("csLongOrderService");
		return csLongOrderService.executeTransaction(function);
	}
	/*******************************订单编号**********************************/	
	/**
	* 订单编号 [非空]      
	**/
	public Long getCsloId(){
		return this.csloId;
	}
	/**
	* 获取订单编号格式化(toString)
	**/
	public String getCsloId$(){
		String strValue="";
		 strValue=$.str(this.getCsloId());
	 	 return strValue;
	}
	/**
	* 订单编号 [非空]      
	**/
	public void setCsloId(Long csloId){
		this.csloId = csloId;
		this.setSeted(F.csloId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsloHost(){
		return this.csloHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsloHost$(){
		String strValue="";
		if(this.getCsloHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsloHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsloHost(Long csloHost){
		this.csloHost = csloHost;
		this.setSeted(F.csloHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csloHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsloHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsloHost()!=null){
 			srvHost = SrvHost.get(this.getCsloHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsloHost(), srvHost);
	 	return srvHost;
	}
	/*******************************合同号**********************************/	
	/**
	* 合同号       
	**/
	public String getCsloPackNo(){
		return this.csloPackNo;
	}
	/**
	* 获取合同号格式化(toString)
	**/
	public String getCsloPackNo$(){
		String strValue="";
		 strValue=$.str(this.getCsloPackNo());
	 	 return strValue;
	}
	/**
	* 合同号       
	**/
	public void setCsloPackNo(String csloPackNo){
		this.csloPackNo = csloPackNo;
		this.setSeted(F.csloPackNo);
	}
	/*******************************会员帐号**********************************/	
	/**
	* 会员帐号 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public Long getCsloMember(){
		return this.csloMember;
	}
	/**
	* 获取会员帐号格式化(toString)
	**/
	public String getCsloMember$(){
		String strValue="";
		if(this.getCsloMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsloMember()));
		}			
	 	 return strValue;
	}
	/**
	* 会员帐号 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public void setCsloMember(Long csloMember){
		this.csloMember = csloMember;
		this.setSeted(F.csloMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csloMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsloMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsloMember()!=null){
 			csMember = CsMember.get(this.getCsloMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsloMember(), csMember);
	 	return csMember;
	}
	/*******************************合同备注**********************************/	
	/**
	* 合同备注       
	**/
	public String getCsloPackProfile(){
		return this.csloPackProfile;
	}
	/**
	* 获取合同备注格式化(toString)
	**/
	public String getCsloPackProfile$(){
		String strValue="";
		 strValue=$.str(this.getCsloPackProfile());
	 	 return strValue;
	}
	/**
	* 合同备注       
	**/
	public void setCsloPackProfile(String csloPackProfile){
		this.csloPackProfile = csloPackProfile;
		this.setSeted(F.csloPackProfile);
	}
	/*******************************合同图片**********************************/	
	/**
	* 合同图片       
	**/
	public String getCsloPackImage(){
		return this.csloPackImage;
	}
	/**
	* 获取合同图片格式化(toString)
	**/
	public String getCsloPackImage$(){
		String strValue="";
		 strValue=$.str(this.getCsloPackImage());
	 	 return strValue;
	}
	/**
	* 合同图片       
	**/
	public void setCsloPackImage(String csloPackImage){
		this.csloPackImage = csloPackImage;
		this.setSeted(F.csloPackImage);
	}
	/*******************************反向关联的长单明细列表**********************************/	
	/**
	* 获取与当前对象反向关联的长单明细列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsLongOrderDetail> getDetails(){
		com.ccclubs.service.admin.ICsLongOrderDetailService csLongOrderDetailService = $.GetSpringBean("csLongOrderDetailService");
		Map params = new HashMap();
		params.put("cslodLongOrder",this.getCsloId());
		return csLongOrderDetailService.getCsLongOrderDetailList(params,-1);
	}
	/**
	* 获取车辆明细格式化(toString)
	**/
	public String getDetails$(){
		return "[...]";
	}
	/*******************************反向关联的长单缴费列表**********************************/	
	/**
	* 获取与当前对象反向关联的长单缴费列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsLongOrderFee> getFees(){
		com.ccclubs.service.admin.ICsLongOrderFeeService csLongOrderFeeService = $.GetSpringBean("csLongOrderFeeService");
		Map params = new HashMap();
		params.put("cslofLongorder",this.getCsloId());
		return csLongOrderFeeService.getCsLongOrderFeeList(params,-1);
	}
	/**
	* 获取缴费明细格式化(toString)
	**/
	public String getFees$(){
		return "[...]";
	}
	/*******************************车辆数**********************************/	
	/**
	* 车辆数 [非空]      
	**/
	public Integer getCsloCount(){
		return this.csloCount;
	}
	/**
	* 获取车辆数格式化(toString)
	**/
	public String getCsloCount$(){
		String strValue="";
		 strValue=$.str(this.getCsloCount());
	 	 return strValue;
	}
	/**
	* 车辆数 [非空]      
	**/
	public void setCsloCount(Integer csloCount){
		this.csloCount = csloCount;
		this.setSeted(F.csloCount);
	}
	/*******************************订单租期**********************************/	
	/**
	* 订单租期 [非空]      
	**/
	public Double getCsloLeaseTerm(){
		return this.csloLeaseTerm;
	}
	/**
	* 获取订单租期格式化(toString)
	**/
	public String getCsloLeaseTerm$(){
		String strValue="";
		 strValue=$.str(this.getCsloLeaseTerm());	
	 	 return strValue;
	}
	/**
	* 订单租期 [非空]      
	**/
	public void setCsloLeaseTerm(Double csloLeaseTerm){
		this.csloLeaseTerm = csloLeaseTerm;
		this.setSeted(F.csloLeaseTerm);
	}
	/*******************************押金合计**********************************/	
	/**
	* 押金合计 [非空]      
	**/
	public Double getCsloDeposit(){
		return this.csloDeposit;
	}
	/**
	* 获取押金合计格式化(toString)
	**/
	public String getCsloDeposit$(){
		String strValue="";
		 strValue=$.str(this.getCsloDeposit());	
	 	 return strValue;
	}
	/**
	* 押金合计 [非空]      
	**/
	public void setCsloDeposit(Double csloDeposit){
		this.csloDeposit = csloDeposit;
		this.setSeted(F.csloDeposit);
	}
	/*******************************付款周期**********************************/	
	/**
	* 付款周期 [非空]   0:一次付清 1:按月付 2:按季付 3:半年付 4:按年付    
	**/
	public Short getCsloPayCycle(){
		return this.csloPayCycle;
	}
	/**
	* 获取付款周期格式化(toString)
	**/
	public String getCsloPayCycle$(){
		String strValue="";
		 if($.equals($.str(this.getCsloPayCycle()),"0"))
			strValue=$.str("一次付清");		 
		 if($.equals($.str(this.getCsloPayCycle()),"1"))
			strValue=$.str("按月付");		 
		 if($.equals($.str(this.getCsloPayCycle()),"2"))
			strValue=$.str("按季付");		 
		 if($.equals($.str(this.getCsloPayCycle()),"3"))
			strValue=$.str("半年付");		 
		 if($.equals($.str(this.getCsloPayCycle()),"4"))
			strValue=$.str("按年付");		 
	 	 return strValue;
	}
	/**
	* 付款周期 [非空]   0:一次付清 1:按月付 2:按季付 3:半年付 4:按年付    
	**/
	public void setCsloPayCycle(Short csloPayCycle){
		this.csloPayCycle = csloPayCycle;
		this.setSeted(F.csloPayCycle);
	}
	/*******************************应付金额**********************************/	
	/**
	* 应付金额 [非空]      
	**/
	public Double getCsloNeedPay(){
		return this.csloNeedPay;
	}
	/**
	* 获取应付金额格式化(toString)
	**/
	public String getCsloNeedPay$(){
		String strValue="";
		 strValue=$.str(this.getCsloNeedPay());	
	 	 return strValue;
	}
	/**
	* 应付金额 [非空]      
	**/
	public void setCsloNeedPay(Double csloNeedPay){
		this.csloNeedPay = csloNeedPay;
		this.setSeted(F.csloNeedPay);
	}
	/*******************************实付金额**********************************/	
	/**
	* 实付金额 [非空]      
	**/
	public Double getCsloRealPay(){
		return this.csloRealPay;
	}
	/**
	* 获取实付金额格式化(toString)
	**/
	public String getCsloRealPay$(){
		String strValue="";
		 strValue=$.str(this.getCsloRealPay());	
	 	 return strValue;
	}
	/**
	* 实付金额 [非空]      
	**/
	public void setCsloRealPay(Double csloRealPay){
		this.csloRealPay = csloRealPay;
		this.setSeted(F.csloRealPay);
	}
	/*******************************合同签订时间**********************************/	
	/**
	* 合同签订时间 [非空]      
	**/
	public Date getCsloPactTime(){
		return this.csloPactTime;
	}
	/**
	* 获取合同签订时间格式化(toString)
	**/
	public String getCsloPactTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsloPactTime(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 合同签订时间 [非空]      
	**/
	public void setCsloPactTime(Date csloPactTime){
		this.csloPactTime = csloPactTime;
		this.setSeted(F.csloPactTime);
	}
	/*******************************合同开始时间**********************************/	
	/**
	* 合同开始时间 [非空]      
	**/
	public Date getCsloStartTime(){
		return this.csloStartTime;
	}
	/**
	* 获取合同开始时间格式化(toString)
	**/
	public String getCsloStartTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsloStartTime(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 合同开始时间 [非空]      
	**/
	public void setCsloStartTime(Date csloStartTime){
		this.csloStartTime = csloStartTime;
		this.setSeted(F.csloStartTime);
	}
	/*******************************合同结束时间**********************************/	
	/**
	* 合同结束时间 [非空]      
	**/
	public Date getCsloFinishTime(){
		return this.csloFinishTime;
	}
	/**
	* 获取合同结束时间格式化(toString)
	**/
	public String getCsloFinishTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsloFinishTime(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 合同结束时间 [非空]      
	**/
	public void setCsloFinishTime(Date csloFinishTime){
		this.csloFinishTime = csloFinishTime;
		this.setSeted(F.csloFinishTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsloUpdateTime(){
		return this.csloUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsloUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsloUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsloUpdateTime(Date csloUpdateTime){
		this.csloUpdateTime = csloUpdateTime;
		this.setSeted(F.csloUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsloAddTime(){
		return this.csloAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsloAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsloAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsloAddTime(Date csloAddTime){
		this.csloAddTime = csloAddTime;
		this.setSeted(F.csloAddTime);
	}
	/*******************************序列号**********************************/	
	/**
	* 序列号       
	**/
	public String getCsloSerial(){
		return this.csloSerial;
	}
	/**
	* 获取序列号格式化(toString)
	**/
	public String getCsloSerial$(){
		String strValue="";
		 strValue=$.str(this.getCsloSerial());
	 	 return strValue;
	}
	/**
	* 序列号       
	**/
	public void setCsloSerial(String csloSerial){
		this.csloSerial = csloSerial;
		this.setSeted(F.csloSerial);
	}
	/*******************************订单备注**********************************/	
	/**
	* 订单备注       
	**/
	public String getCsloRemark(){
		return this.csloRemark;
	}
	/**
	* 获取订单备注格式化(toString)
	**/
	public String getCsloRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsloRemark());
	 	 return strValue;
	}
	/**
	* 订单备注       
	**/
	public void setCsloRemark(String csloRemark){
		this.csloRemark = csloRemark;
		this.setSeted(F.csloRemark);
	}
	/*******************************操作人**********************************/	
	/**
	* 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsloEditor(){
		return this.csloEditor;
	}
	/**
	* 获取操作人格式化(toString)
	**/
	public String getCsloEditor$(){
		String strValue="";
		if(this.getCsloEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsloEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsloEditor(Long csloEditor){
		this.csloEditor = csloEditor;
		this.setSeted(F.csloEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csloEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsloEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsloEditor()!=null){
 			srvUser = SrvUser.get(this.getCsloEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCsloEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************报警信息**********************************/	
	/**
	* 报警信息       
	**/
	public String getCsloError(){
		return this.csloError;
	}
	/**
	* 获取报警信息格式化(toString)
	**/
	public String getCsloError$(){
		String strValue="";
		 strValue=$.str(this.getCsloError());
	 	 return strValue;
	}
	/**
	* 报警信息       
	**/
	public void setCsloError(String csloError){
		this.csloError = csloError;
		this.setSeted(F.csloError);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:已预定 1:使用中 2:已还车 3:已取消 4:已完成 5:待处理 6:已退款 7:已作废    
	**/
	public Short getCsloStatus(){
		return this.csloStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsloStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsloStatus()),"0"))
			strValue=$.str("已预定");		 
		 if($.equals($.str(this.getCsloStatus()),"1"))
			strValue=$.str("使用中");		 
		 if($.equals($.str(this.getCsloStatus()),"2"))
			strValue=$.str("已还车");		 
		 if($.equals($.str(this.getCsloStatus()),"3"))
			strValue=$.str("已取消");		 
		 if($.equals($.str(this.getCsloStatus()),"4"))
			strValue=$.str("已完成");		 
		 if($.equals($.str(this.getCsloStatus()),"5"))
			strValue=$.str("待处理");		 
		 if($.equals($.str(this.getCsloStatus()),"6"))
			strValue=$.str("已退款");		 
		 if($.equals($.str(this.getCsloStatus()),"7"))
			strValue=$.str("已作废");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:已预定 1:使用中 2:已还车 3:已取消 4:已完成 5:待处理 6:已退款 7:已作废    
	**/
	public void setCsloStatus(Short csloStatus){
		this.csloStatus = csloStatus;
		this.setSeted(F.csloStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/**
	 * 计算租期与应付额
	 */
	public static void compute(Long id) {
		CsLongOrder lOrder = CsLongOrder.get(id);
		Long day = ((lOrder.csloFinishTime.getTime()-lOrder.csloStartTime.getTime())/$.TIME.DAY.time);
		Double month = day/30.5; 
		Double decimal = month%1.0d;
		if(decimal<0.1 || decimal>0.9){
			month = Math.rint(month);//四舍5入取整
		}else{
			month = Double.valueOf($.digit(month, 2));
		}
		lOrder.csloLeaseTerm(month);
		Integer carCount = lOrder.getDetails().size();
		//设置车辆数
		lOrder.setCsloCount(carCount.intValue());
		//设置应付额month*lOrder.getCsloPrice()*carCount
		Double needPay = CsLongOrderDetail.where().cslodLongOrder(id).eval("sum(cslod_price*"+month+")");
		lOrder.csloNeedPay(needPay);
		//设置已付额 
		Double payed = CsLongOrderFee.where().cslofLongorder(id).cslofType(1).eval("sum(cslof_ammount)");
		lOrder.csloRealPay(payed);
		final CsLongOrder fOrder = lOrder;
		fOrder.update();
	}
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsLongOrder.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsLongOrder.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsLongOrder.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsLongOrder.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsLongOrder.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsLongOrder.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsLongOrder.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsLongOrder.this);
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
		/** 订单编号 [非空]       **/
		public M csloId(Object csloId){this.put("csloId", csloId);return this;};
	 	/** and cslo_id is null */
 		public M csloIdNull(){if(this.get("csloIdNot")==null)this.put("csloIdNot", "");this.put("csloId", null);return this;};
 		/** not .... */
 		public M csloIdNot(){this.put("csloIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csloHost(Object csloHost){this.put("csloHost", csloHost);return this;};
	 	/** and cslo_host is null */
 		public M csloHostNull(){if(this.get("csloHostNot")==null)this.put("csloHostNot", "");this.put("csloHost", null);return this;};
 		/** not .... */
 		public M csloHostNot(){this.put("csloHostNot", "not");return this;};
		/** 合同号        **/
		public M csloPackNo(Object csloPackNo){this.put("csloPackNo", csloPackNo);return this;};
	 	/** and cslo_pack_no is null */
 		public M csloPackNoNull(){if(this.get("csloPackNoNot")==null)this.put("csloPackNoNot", "");this.put("csloPackNo", null);return this;};
 		/** not .... */
 		public M csloPackNoNot(){this.put("csloPackNoNot", "not");return this;};
		/** 会员帐号 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public M csloMember(Object csloMember){this.put("csloMember", csloMember);return this;};
	 	/** and cslo_member is null */
 		public M csloMemberNull(){if(this.get("csloMemberNot")==null)this.put("csloMemberNot", "");this.put("csloMember", null);return this;};
 		/** not .... */
 		public M csloMemberNot(){this.put("csloMemberNot", "not");return this;};
		public M csloMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csloMember$on", value);
			return this;
		};	
		/** 合同备注        **/
		public M csloPackProfile(Object csloPackProfile){this.put("csloPackProfile", csloPackProfile);return this;};
	 	/** and cslo_pack_profile is null */
 		public M csloPackProfileNull(){if(this.get("csloPackProfileNot")==null)this.put("csloPackProfileNot", "");this.put("csloPackProfile", null);return this;};
 		/** not .... */
 		public M csloPackProfileNot(){this.put("csloPackProfileNot", "not");return this;};
		/** 合同图片        **/
		public M csloPackImage(Object csloPackImage){this.put("csloPackImage", csloPackImage);return this;};
	 	/** and cslo_pack_image is null */
 		public M csloPackImageNull(){if(this.get("csloPackImageNot")==null)this.put("csloPackImageNot", "");this.put("csloPackImage", null);return this;};
 		/** not .... */
 		public M csloPackImageNot(){this.put("csloPackImageNot", "not");return this;};
		/** 车辆数 [非空]       **/
		public M csloCount(Object csloCount){this.put("csloCount", csloCount);return this;};
	 	/** and cslo_count is null */
 		public M csloCountNull(){if(this.get("csloCountNot")==null)this.put("csloCountNot", "");this.put("csloCount", null);return this;};
 		/** not .... */
 		public M csloCountNot(){this.put("csloCountNot", "not");return this;};
		/** and cslo_count >= ? */
		public M csloCountMin(Object min){this.put("csloCountMin", min);return this;};
		/** and cslo_count <= ? */
		public M csloCountMax(Object max){this.put("csloCountMax", max);return this;};
		/** 订单租期 [非空]       **/
		public M csloLeaseTerm(Object csloLeaseTerm){this.put("csloLeaseTerm", csloLeaseTerm);return this;};
	 	/** and cslo_lease_term is null */
 		public M csloLeaseTermNull(){if(this.get("csloLeaseTermNot")==null)this.put("csloLeaseTermNot", "");this.put("csloLeaseTerm", null);return this;};
 		/** not .... */
 		public M csloLeaseTermNot(){this.put("csloLeaseTermNot", "not");return this;};
		/** and cslo_lease_term >= ? */
		public M csloLeaseTermMin(Object min){this.put("csloLeaseTermMin", min);return this;};
		/** and cslo_lease_term <= ? */
		public M csloLeaseTermMax(Object max){this.put("csloLeaseTermMax", max);return this;};
		/** 押金合计 [非空]       **/
		public M csloDeposit(Object csloDeposit){this.put("csloDeposit", csloDeposit);return this;};
	 	/** and cslo_deposit is null */
 		public M csloDepositNull(){if(this.get("csloDepositNot")==null)this.put("csloDepositNot", "");this.put("csloDeposit", null);return this;};
 		/** not .... */
 		public M csloDepositNot(){this.put("csloDepositNot", "not");return this;};
		/** and cslo_deposit >= ? */
		public M csloDepositMin(Object min){this.put("csloDepositMin", min);return this;};
		/** and cslo_deposit <= ? */
		public M csloDepositMax(Object max){this.put("csloDepositMax", max);return this;};
		/** 付款周期 [非空]   0:一次付清 1:按月付 2:按季付 3:半年付 4:按年付     **/
		public M csloPayCycle(Object csloPayCycle){this.put("csloPayCycle", csloPayCycle);return this;};
	 	/** and cslo_pay_cycle is null */
 		public M csloPayCycleNull(){if(this.get("csloPayCycleNot")==null)this.put("csloPayCycleNot", "");this.put("csloPayCycle", null);return this;};
 		/** not .... */
 		public M csloPayCycleNot(){this.put("csloPayCycleNot", "not");return this;};
		/** 应付金额 [非空]       **/
		public M csloNeedPay(Object csloNeedPay){this.put("csloNeedPay", csloNeedPay);return this;};
	 	/** and cslo_need_pay is null */
 		public M csloNeedPayNull(){if(this.get("csloNeedPayNot")==null)this.put("csloNeedPayNot", "");this.put("csloNeedPay", null);return this;};
 		/** not .... */
 		public M csloNeedPayNot(){this.put("csloNeedPayNot", "not");return this;};
		/** and cslo_need_pay >= ? */
		public M csloNeedPayMin(Object min){this.put("csloNeedPayMin", min);return this;};
		/** and cslo_need_pay <= ? */
		public M csloNeedPayMax(Object max){this.put("csloNeedPayMax", max);return this;};
		/** 实付金额 [非空]       **/
		public M csloRealPay(Object csloRealPay){this.put("csloRealPay", csloRealPay);return this;};
	 	/** and cslo_real_pay is null */
 		public M csloRealPayNull(){if(this.get("csloRealPayNot")==null)this.put("csloRealPayNot", "");this.put("csloRealPay", null);return this;};
 		/** not .... */
 		public M csloRealPayNot(){this.put("csloRealPayNot", "not");return this;};
		/** and cslo_real_pay >= ? */
		public M csloRealPayMin(Object min){this.put("csloRealPayMin", min);return this;};
		/** and cslo_real_pay <= ? */
		public M csloRealPayMax(Object max){this.put("csloRealPayMax", max);return this;};
		/** 合同签订时间 [非空]       **/
		public M csloPactTime(Object csloPactTime){this.put("csloPactTime", csloPactTime);return this;};
	 	/** and cslo_pact_time is null */
 		public M csloPactTimeNull(){if(this.get("csloPactTimeNot")==null)this.put("csloPactTimeNot", "");this.put("csloPactTime", null);return this;};
 		/** not .... */
 		public M csloPactTimeNot(){this.put("csloPactTimeNot", "not");return this;};
 		/** and cslo_pact_time >= ? */
 		public M csloPactTimeStart(Object start){this.put("csloPactTimeStart", start);return this;};			
 		/** and cslo_pact_time <= ? */
 		public M csloPactTimeEnd(Object end){this.put("csloPactTimeEnd", end);return this;};
		/** 合同开始时间 [非空]       **/
		public M csloStartTime(Object csloStartTime){this.put("csloStartTime", csloStartTime);return this;};
	 	/** and cslo_start_time is null */
 		public M csloStartTimeNull(){if(this.get("csloStartTimeNot")==null)this.put("csloStartTimeNot", "");this.put("csloStartTime", null);return this;};
 		/** not .... */
 		public M csloStartTimeNot(){this.put("csloStartTimeNot", "not");return this;};
 		/** and cslo_start_time >= ? */
 		public M csloStartTimeStart(Object start){this.put("csloStartTimeStart", start);return this;};			
 		/** and cslo_start_time <= ? */
 		public M csloStartTimeEnd(Object end){this.put("csloStartTimeEnd", end);return this;};
		/** 合同结束时间 [非空]       **/
		public M csloFinishTime(Object csloFinishTime){this.put("csloFinishTime", csloFinishTime);return this;};
	 	/** and cslo_finish_time is null */
 		public M csloFinishTimeNull(){if(this.get("csloFinishTimeNot")==null)this.put("csloFinishTimeNot", "");this.put("csloFinishTime", null);return this;};
 		/** not .... */
 		public M csloFinishTimeNot(){this.put("csloFinishTimeNot", "not");return this;};
 		/** and cslo_finish_time >= ? */
 		public M csloFinishTimeStart(Object start){this.put("csloFinishTimeStart", start);return this;};			
 		/** and cslo_finish_time <= ? */
 		public M csloFinishTimeEnd(Object end){this.put("csloFinishTimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M csloUpdateTime(Object csloUpdateTime){this.put("csloUpdateTime", csloUpdateTime);return this;};
	 	/** and cslo_update_time is null */
 		public M csloUpdateTimeNull(){if(this.get("csloUpdateTimeNot")==null)this.put("csloUpdateTimeNot", "");this.put("csloUpdateTime", null);return this;};
 		/** not .... */
 		public M csloUpdateTimeNot(){this.put("csloUpdateTimeNot", "not");return this;};
 		/** and cslo_update_time >= ? */
 		public M csloUpdateTimeStart(Object start){this.put("csloUpdateTimeStart", start);return this;};			
 		/** and cslo_update_time <= ? */
 		public M csloUpdateTimeEnd(Object end){this.put("csloUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csloAddTime(Object csloAddTime){this.put("csloAddTime", csloAddTime);return this;};
	 	/** and cslo_add_time is null */
 		public M csloAddTimeNull(){if(this.get("csloAddTimeNot")==null)this.put("csloAddTimeNot", "");this.put("csloAddTime", null);return this;};
 		/** not .... */
 		public M csloAddTimeNot(){this.put("csloAddTimeNot", "not");return this;};
 		/** and cslo_add_time >= ? */
 		public M csloAddTimeStart(Object start){this.put("csloAddTimeStart", start);return this;};			
 		/** and cslo_add_time <= ? */
 		public M csloAddTimeEnd(Object end){this.put("csloAddTimeEnd", end);return this;};
		/** 序列号        **/
		public M csloSerial(Object csloSerial){this.put("csloSerial", csloSerial);return this;};
	 	/** and cslo_serial is null */
 		public M csloSerialNull(){if(this.get("csloSerialNot")==null)this.put("csloSerialNot", "");this.put("csloSerial", null);return this;};
 		/** not .... */
 		public M csloSerialNot(){this.put("csloSerialNot", "not");return this;};
		/** 订单备注        **/
		public M csloRemark(Object csloRemark){this.put("csloRemark", csloRemark);return this;};
	 	/** and cslo_remark is null */
 		public M csloRemarkNull(){if(this.get("csloRemarkNot")==null)this.put("csloRemarkNot", "");this.put("csloRemark", null);return this;};
 		/** not .... */
 		public M csloRemarkNot(){this.put("csloRemarkNot", "not");return this;};
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csloEditor(Object csloEditor){this.put("csloEditor", csloEditor);return this;};
	 	/** and cslo_editor is null */
 		public M csloEditorNull(){if(this.get("csloEditorNot")==null)this.put("csloEditorNot", "");this.put("csloEditor", null);return this;};
 		/** not .... */
 		public M csloEditorNot(){this.put("csloEditorNot", "not");return this;};
		/** 报警信息        **/
		public M csloError(Object csloError){this.put("csloError", csloError);return this;};
	 	/** and cslo_error is null */
 		public M csloErrorNull(){if(this.get("csloErrorNot")==null)this.put("csloErrorNot", "");this.put("csloError", null);return this;};
 		/** not .... */
 		public M csloErrorNot(){this.put("csloErrorNot", "not");return this;};
		/** 状态 [非空]   0:已预定 1:使用中 2:已还车 3:已取消 4:已完成 5:待处理 6:已退款 7:已作废     **/
		public M csloStatus(Object csloStatus){this.put("csloStatus", csloStatus);return this;};
	 	/** and cslo_status is null */
 		public M csloStatusNull(){if(this.get("csloStatusNot")==null)this.put("csloStatusNot", "");this.put("csloStatus", null);return this;};
 		/** not .... */
 		public M csloStatusNot(){this.put("csloStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有长租订单 **/
		public @api List<CsLongOrder> list(Integer size){
			return getCsLongOrderList(this,size);
		}
		/** 获取长租订单分页 **/
		public @api Page<CsLongOrder> page(int page,int size){
			return getCsLongOrderPage(page, size , this);
		}
		/** 根据查询条件取长租订单 **/
		public @api CsLongOrder get(){
			return getCsLongOrder(this);
		}
		/** 获取长租订单数 **/
		public @api Long count(){
			return getCsLongOrderCount(this);
		}
		/** 获取长租订单表达式 **/
		public @api <T> T eval(String strEval){
			return getCsLongOrderEval(strEval,this);
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
			updateCsLongOrder(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 订单编号 [非空]       **/
		public final static @type(Long.class)  String csloId="csloId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csloHost="csloHost";
		/** 合同号        **/
		public final static @type(String.class) @like String csloPackNo="csloPackNo";
		/** 会员帐号 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static @type(Long.class)  String csloMember="csloMember";
		/** 合同备注        **/
		public final static @type(String.class) @like String csloPackProfile="csloPackProfile";
		/** 合同图片        **/
		public final static @type(String.class)  String csloPackImage="csloPackImage";
		/** 车辆数 [非空]       **/
		public final static @type(Integer.class)  String csloCount="csloCount";
		/** and cslo_count >= ? */
		public final static @type(Integer.class) String csloCountMin="csloCountMin";
		/** and cslo_count <= ? */
		public final static @type(Integer.class) String csloCountMax="csloCountMax";
		/** 订单租期 [非空]       **/
		public final static @type(Double.class)  String csloLeaseTerm="csloLeaseTerm";
		/** and cslo_lease_term >= ? */
		public final static @type(Double.class) String csloLeaseTermMin="csloLeaseTermMin";
		/** and cslo_lease_term <= ? */
		public final static @type(Double.class) String csloLeaseTermMax="csloLeaseTermMax";
		/** 押金合计 [非空]       **/
		public final static @type(Double.class)  String csloDeposit="csloDeposit";
		/** and cslo_deposit >= ? */
		public final static @type(Double.class) String csloDepositMin="csloDepositMin";
		/** and cslo_deposit <= ? */
		public final static @type(Double.class) String csloDepositMax="csloDepositMax";
		/** 付款周期 [非空]   0:一次付清 1:按月付 2:按季付 3:半年付 4:按年付     **/
		public final static @type(Short.class)  String csloPayCycle="csloPayCycle";
		/** 应付金额 [非空]       **/
		public final static @type(Double.class)  String csloNeedPay="csloNeedPay";
		/** and cslo_need_pay >= ? */
		public final static @type(Double.class) String csloNeedPayMin="csloNeedPayMin";
		/** and cslo_need_pay <= ? */
		public final static @type(Double.class) String csloNeedPayMax="csloNeedPayMax";
		/** 实付金额 [非空]       **/
		public final static @type(Double.class)  String csloRealPay="csloRealPay";
		/** and cslo_real_pay >= ? */
		public final static @type(Double.class) String csloRealPayMin="csloRealPayMin";
		/** and cslo_real_pay <= ? */
		public final static @type(Double.class) String csloRealPayMax="csloRealPayMax";
		/** 合同签订时间 [非空]       **/
		public final static @type(Date.class)  String csloPactTime="csloPactTime";
	 	/** and cslo_pact_time >= ? */
 		public final static @type(Date.class) String csloPactTimeStart="csloPactTimeStart";
 		/** and cslo_pact_time <= ? */
 		public final static @type(Date.class) String csloPactTimeEnd="csloPactTimeEnd";
		/** 合同开始时间 [非空]       **/
		public final static @type(Date.class)  String csloStartTime="csloStartTime";
	 	/** and cslo_start_time >= ? */
 		public final static @type(Date.class) String csloStartTimeStart="csloStartTimeStart";
 		/** and cslo_start_time <= ? */
 		public final static @type(Date.class) String csloStartTimeEnd="csloStartTimeEnd";
		/** 合同结束时间 [非空]       **/
		public final static @type(Date.class)  String csloFinishTime="csloFinishTime";
	 	/** and cslo_finish_time >= ? */
 		public final static @type(Date.class) String csloFinishTimeStart="csloFinishTimeStart";
 		/** and cslo_finish_time <= ? */
 		public final static @type(Date.class) String csloFinishTimeEnd="csloFinishTimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csloUpdateTime="csloUpdateTime";
	 	/** and cslo_update_time >= ? */
 		public final static @type(Date.class) String csloUpdateTimeStart="csloUpdateTimeStart";
 		/** and cslo_update_time <= ? */
 		public final static @type(Date.class) String csloUpdateTimeEnd="csloUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csloAddTime="csloAddTime";
	 	/** and cslo_add_time >= ? */
 		public final static @type(Date.class) String csloAddTimeStart="csloAddTimeStart";
 		/** and cslo_add_time <= ? */
 		public final static @type(Date.class) String csloAddTimeEnd="csloAddTimeEnd";
		/** 序列号        **/
		public final static @type(String.class) @like String csloSerial="csloSerial";
		/** 订单备注        **/
		public final static @type(String.class) @like String csloRemark="csloRemark";
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csloEditor="csloEditor";
		/** 报警信息        **/
		public final static @type(String.class) @like String csloError="csloError";
		/** 状态 [非空]   0:已预定 1:使用中 2:已还车 3:已取消 4:已完成 5:待处理 6:已退款 7:已作废     **/
		public final static @type(Short.class)  String csloStatus="csloStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 订单编号 [非空]       **/
		public final static String csloId="cslo_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csloHost="cslo_host";
		/** 合同号        **/
		public final static String csloPackNo="cslo_pack_no";
		/** 会员帐号 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static String csloMember="cslo_member";
		/** 合同备注        **/
		public final static String csloPackProfile="cslo_pack_profile";
		/** 合同图片        **/
		public final static String csloPackImage="cslo_pack_image";
		/** 车辆数 [非空]       **/
		public final static String csloCount="cslo_count";
		/** 订单租期 [非空]       **/
		public final static String csloLeaseTerm="cslo_lease_term";
		/** 押金合计 [非空]       **/
		public final static String csloDeposit="cslo_deposit";
		/** 付款周期 [非空]   0:一次付清 1:按月付 2:按季付 3:半年付 4:按年付     **/
		public final static String csloPayCycle="cslo_pay_cycle";
		/** 应付金额 [非空]       **/
		public final static String csloNeedPay="cslo_need_pay";
		/** 实付金额 [非空]       **/
		public final static String csloRealPay="cslo_real_pay";
		/** 合同签订时间 [非空]       **/
		public final static String csloPactTime="cslo_pact_time";
		/** 合同开始时间 [非空]       **/
		public final static String csloStartTime="cslo_start_time";
		/** 合同结束时间 [非空]       **/
		public final static String csloFinishTime="cslo_finish_time";
		/** 修改时间 [非空]       **/
		public final static String csloUpdateTime="cslo_update_time";
		/** 添加时间 [非空]       **/
		public final static String csloAddTime="cslo_add_time";
		/** 序列号        **/
		public final static String csloSerial="cslo_serial";
		/** 订单备注        **/
		public final static String csloRemark="cslo_remark";
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csloEditor="cslo_editor";
		/** 报警信息        **/
		public final static String csloError="cslo_error";
		/** 状态 [非空]   0:已预定 1:使用中 2:已还车 3:已取消 4:已完成 5:待处理 6:已退款 7:已作废     **/
		public final static String csloStatus="cslo_status";
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
				$.Set(name,CsLongOrder.getCsLongOrder(params));
			else
				$.Set(name,CsLongOrder.getCsLongOrderList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取长租订单数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsLongOrder) $.GetRequest("CsLongOrder$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsLongOrder.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsLongOrder.getCsLongOrder(params);
			else
				value = CsLongOrder.getCsLongOrderList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsLongOrder.Get($.add(CsLongOrder.F.csloId,param));
		else if(!$.empty(param.toString()))
			value = CsLongOrder.get(Long.valueOf(param.toString()));
		$.SetRequest("CsLongOrder$"+param.hashCode(), value);
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
	public void mergeSet(CsLongOrder old){
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