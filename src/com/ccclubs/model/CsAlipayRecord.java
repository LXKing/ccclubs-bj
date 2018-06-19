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

@namespace("finance/alipay/alipayrecord")
public @caption("接口充值") @table("cs_alipay_record") class CsAlipayRecord implements java.io.Serializable
{
	private static final long serialVersionUID = 1l;
	private @caption("编号") @column("csar_id")   @primary  @note("  ") Long csarId;// 主键 非空     
	private @caption("运营地区") @column("csar_host")    @relate("$csarHost") @RelateClass(SrvHost.class)  @note("  ") Long csarHost;// 非空     
 	private SrvHost $csarHost;//关联对象[运营城市]
	private @caption("接口类型") @column("csar_bank_type")    @note(" 0:支付宝 10:招商银行  ") Short csarBankType;// 非空 0:支付宝 10:招商银行     
	private @caption("交易号") @column("csar_trade_no")    @note("  ") String csarTradeNo;// 非空     
	private @caption("充值会员") @column("csar_member")    @relate("$csarMember") @RelateClass(CsMember.class)  @note("  ") Long csarMember;// 非空    ${basePath}${proname}/user/member_inquire.do 
 	private CsMember $csarMember;//关联对象[会员帐号]
	private @caption("会员姓名") @column("csar_real_name")    @note("  ") String csarRealName;// 非空     
	private @caption("充值金额") @column("csar_amount")    @note("  ") Double csarAmount;// 非空     
	private @caption("购买宝贝") @column("csar_buy_item")    @relate("$csarBuyItem") @RelateClass(CsItem.class)  @note("  标识充值完成后打算购买哪个宝贝") Long csarBuyItem;//  标识充值完成后打算购买哪个宝贝   
 	private CsItem $csarBuyItem;//关联对象[商品信息]
	private @caption("购买个数") @column("csar_buy_count")    @note("  标识充值完成后打算购买哪个宝贝的个数") Integer csarBuyCount;//  标识充值完成后打算购买哪个宝贝的个数   
	private @caption("已退金额") @column("csar_refund")    @note("  ") Double csarRefund;//     
	private @caption("支付接口") @column("csar_pay_method")    @note("  ") String csarPayMethod;//     
	private @caption("银行名称") @column("csar_blank")    @note("  ") String csarBlank;//     
	private @caption("支付宝交易号") @column("csar_alipay_no")    @note("  ") String csarAlipayNo;//     
	private @caption("支付宝帐号") @column("csar_buyer_account")    @note("  ") String csarBuyerAccount;//     
	private @caption("三方客户端ID") @column("csar_client_id")    @relate("$csarClientId") @RelateClass(SrvApiClient.class)  @note("  ") Long csarClientId;//     
 	private SrvApiClient $csarClientId;//关联对象[API客户端]
	private @caption("三方回调") @column("csar_third_back")    @note("  ") String csarThirdBack;//     
	private @caption("修改时间") @column("csar_update_time")    @note("  ") Date csarUpdateTime;// 非空     
	private @caption("添加时间") @column("csar_add_time")    @note("  ") Date csarAddTime;// 非空     
	private @caption("状态") @column("csar_status")    @note(" 0:等待 1:成功 2:失败  ") Short csarStatus;// 非空 0:等待 1:成功 2:失败     
	
	//默认构造函数
	public CsAlipayRecord(){
	
	}
	
	//主键构造函数
	public CsAlipayRecord(Long id){
		this.csarId = id;
	}
	
	/**所有字段构造函数 CsAlipayRecord(csarHost,csarBankType,csarTradeNo,csarMember,csarRealName,csarAmount,csarBuyItem,csarBuyCount,csarRefund,csarPayMethod,csarBlank,csarAlipayNo,csarBuyerAccount,csarClientId,csarThirdBack,csarUpdateTime,csarAddTime,csarStatus)
	 CsAlipayRecord(
	 	$.getLong("csarHost")//运营地区 [非空]
	 	,$.getShort("csarBankType")//接口类型 [非空]
	 	,$.getString("csarTradeNo")//交易号 [非空]
	 	,$.getLong("csarMember")//充值会员 [非空]
	 	,$.getString("csarRealName")//会员姓名 [非空]
	 	,$.getDouble("csarAmount")//充值金额 [非空]
	 	,$.getLong("csarBuyItem")//购买宝贝
	 	,$.getInteger("csarBuyCount")//购买个数
	 	,$.getDouble("csarRefund")//已退金额
	 	,$.getString("csarPayMethod")//支付接口
	 	,$.getString("csarBlank")//银行名称
	 	,$.getString("csarAlipayNo")//支付宝交易号
	 	,$.getString("csarBuyerAccount")//支付宝帐号
	 	,$.getLong("csarClientId")//三方客户端ID
	 	,$.getString("csarThirdBack")//三方回调
	 	,$.getDate("csarUpdateTime")//修改时间 [非空]
	 	,$.getDate("csarAddTime")//添加时间 [非空]
	 	,$.getShort("csarStatus")//状态 [非空]
	 )
	**/
	public CsAlipayRecord(Long csarHost,Short csarBankType,String csarTradeNo,Long csarMember,String csarRealName,Double csarAmount,Long csarBuyItem,Integer csarBuyCount,Double csarRefund,String csarPayMethod,String csarBlank,String csarAlipayNo,String csarBuyerAccount,Long csarClientId,String csarThirdBack,Date csarUpdateTime,Date csarAddTime,Short csarStatus){
		this.csarHost=csarHost;
		this.csarBankType=csarBankType;
		this.csarTradeNo=csarTradeNo;
		this.csarMember=csarMember;
		this.csarRealName=csarRealName;
		this.csarAmount=csarAmount;
		this.csarBuyItem=csarBuyItem;
		this.csarBuyCount=csarBuyCount;
		this.csarRefund=csarRefund;
		this.csarPayMethod=csarPayMethod;
		this.csarBlank=csarBlank;
		this.csarAlipayNo=csarAlipayNo;
		this.csarBuyerAccount=csarBuyerAccount;
		this.csarClientId=csarClientId;
		this.csarThirdBack=csarThirdBack;
		this.csarUpdateTime=csarUpdateTime;
		this.csarAddTime=csarAddTime;
		this.csarStatus=csarStatus;
	}
	
	//设置非空字段
	public CsAlipayRecord setNotNull(Long csarHost,Short csarBankType,String csarTradeNo,Long csarMember,String csarRealName,Double csarAmount,Date csarUpdateTime,Date csarAddTime,Short csarStatus){
		this.csarHost=csarHost;
		this.csarBankType=csarBankType;
		this.csarTradeNo=csarTradeNo;
		this.csarMember=csarMember;
		this.csarRealName=csarRealName;
		this.csarAmount=csarAmount;
		this.csarUpdateTime=csarUpdateTime;
		this.csarAddTime=csarAddTime;
		this.csarStatus=csarStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsAlipayRecord csarId(Long csarId){
		this.csarId = csarId;
		this.setSeted(F.csarId);
		return this;
	}
	/** 运营地区 [非空] [SrvHost]      **/
	public CsAlipayRecord csarHost(Long csarHost){
		this.csarHost = csarHost;
		this.setSeted(F.csarHost);
		return this;
	}
	/** 接口类型 [非空]   0:支付宝 10:招商银行     **/
	public CsAlipayRecord csarBankType(Short csarBankType){
		this.csarBankType = csarBankType;
		this.setSeted(F.csarBankType);
		return this;
	}
	/** 交易号 [非空]       **/
	public CsAlipayRecord csarTradeNo(String csarTradeNo){
		this.csarTradeNo = csarTradeNo;
		this.setSeted(F.csarTradeNo);
		return this;
	}
	/** 充值会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
	public CsAlipayRecord csarMember(Long csarMember){
		this.csarMember = csarMember;
		this.setSeted(F.csarMember);
		return this;
	}
	/** 会员姓名 [非空]       **/
	public CsAlipayRecord csarRealName(String csarRealName){
		this.csarRealName = csarRealName;
		this.setSeted(F.csarRealName);
		return this;
	}
	/** 充值金额 [非空]       **/
	public CsAlipayRecord csarAmount(Double csarAmount){
		this.csarAmount = csarAmount;
		this.setSeted(F.csarAmount);
		return this;
	}
	/** 购买宝贝  [CsItem]   标识充值完成后打算购买哪个宝贝   **/
	public CsAlipayRecord csarBuyItem(Long csarBuyItem){
		this.csarBuyItem = csarBuyItem;
		this.setSeted(F.csarBuyItem);
		return this;
	}
	/** 购买个数     标识充值完成后打算购买哪个宝贝的个数   **/
	public CsAlipayRecord csarBuyCount(Integer csarBuyCount){
		this.csarBuyCount = csarBuyCount;
		this.setSeted(F.csarBuyCount);
		return this;
	}
	/** 已退金额        **/
	public CsAlipayRecord csarRefund(Double csarRefund){
		this.csarRefund = csarRefund;
		this.setSeted(F.csarRefund);
		return this;
	}
	/** 支付接口        **/
	public CsAlipayRecord csarPayMethod(String csarPayMethod){
		this.csarPayMethod = csarPayMethod;
		this.setSeted(F.csarPayMethod);
		return this;
	}
	/** 银行名称        **/
	public CsAlipayRecord csarBlank(String csarBlank){
		this.csarBlank = csarBlank;
		this.setSeted(F.csarBlank);
		return this;
	}
	/** 支付宝交易号        **/
	public CsAlipayRecord csarAlipayNo(String csarAlipayNo){
		this.csarAlipayNo = csarAlipayNo;
		this.setSeted(F.csarAlipayNo);
		return this;
	}
	/** 支付宝帐号        **/
	public CsAlipayRecord csarBuyerAccount(String csarBuyerAccount){
		this.csarBuyerAccount = csarBuyerAccount;
		this.setSeted(F.csarBuyerAccount);
		return this;
	}
	/** 三方客户端ID  [SrvApiClient]      **/
	public CsAlipayRecord csarClientId(Long csarClientId){
		this.csarClientId = csarClientId;
		this.setSeted(F.csarClientId);
		return this;
	}
	/** 三方回调        **/
	public CsAlipayRecord csarThirdBack(String csarThirdBack){
		this.csarThirdBack = csarThirdBack;
		this.setSeted(F.csarThirdBack);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsAlipayRecord csarUpdateTime(Date csarUpdateTime){
		this.csarUpdateTime = csarUpdateTime;
		this.setSeted(F.csarUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsAlipayRecord csarAddTime(Date csarAddTime){
		this.csarAddTime = csarAddTime;
		this.setSeted(F.csarAddTime);
		return this;
	}
	/** 状态 [非空]   0:等待 1:成功 2:失败     **/
	public CsAlipayRecord csarStatus(Short csarStatus){
		this.csarStatus = csarStatus;
		this.setSeted(F.csarStatus);
		return this;
	}
	
	
	//克隆对象
	public CsAlipayRecord clone(){
		CsAlipayRecord clone = new CsAlipayRecord();
		clone.csarHost=this.csarHost;
		clone.csarBankType=this.csarBankType;
		clone.csarTradeNo=this.csarTradeNo;
		clone.csarMember=this.csarMember;
		clone.csarRealName=this.csarRealName;
		clone.csarAmount=this.csarAmount;
		clone.csarUpdateTime=this.csarUpdateTime;
		clone.csarAddTime=this.csarAddTime;
		clone.csarStatus=this.csarStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取接口充值
	 * @param id
	 * @return
	 */
	public static @api CsAlipayRecord get(Long id){		
		return getCsAlipayRecordById(id);
	}
	/**
	 * 获取所有接口充值
	 * @return
	 */
	public static @api List<CsAlipayRecord> list(Map params,Integer size){
		return getCsAlipayRecordList(params,size);
	}
	/**
	 * 获取接口充值分页
	 * @return
	 */
	public static @api Page<CsAlipayRecord> page(int page,int size,Map params){
		return getCsAlipayRecordPage(page, size , params);
	}
	/**
	 * 根据查询条件取接口充值
	 * @param params
	 * @return
	 */
	public static @api CsAlipayRecord Get(Map params){
		return getCsAlipayRecord(params);
	}
	/**
	 * 获取接口充值数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsAlipayRecordCount(params);
	}
	/**
	 * 获取接口充值数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsAlipayRecordEval(eval,params);
	}
	
	/**
	 * 根据ID取接口充值
	 * @param id
	 * @return
	 */
	public static @api CsAlipayRecord getCsAlipayRecordById(Long id){		
		CsAlipayRecord csAlipayRecord = (CsAlipayRecord) $.GetRequest("CsAlipayRecord$"+id);
		if(csAlipayRecord!=null)
			return csAlipayRecord;
		com.ccclubs.service.admin.ICsAlipayRecordService csAlipayRecordService = $.GetSpringBean("csAlipayRecordService");		
		return csAlipayRecordService.getCsAlipayRecordById(id);
	}
	
	
	/**
	 * 根据ID取接口充值的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsAlipayRecord.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsAlipayRecord csAlipayRecord = get(id);
		if(csAlipayRecord!=null){
			String strValue = csAlipayRecord.getCsarTradeNo$();
			if(!"CsarTradeNo".equals("CsarTradeNo"))
				strValue+="("+csAlipayRecord.getCsarTradeNo$()+")";
			MemCache.setValue(CsAlipayRecord.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsarTradeNo$();
		if(!"CsarTradeNo".equals("CsarTradeNo"))
			keyValue+="("+this.getCsarTradeNo$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有接口充值
	 * @return
	 */
	public static @api List<CsAlipayRecord> getCsAlipayRecordList(Map params,Integer size){
		com.ccclubs.service.admin.ICsAlipayRecordService csAlipayRecordService = $.GetSpringBean("csAlipayRecordService");
		return csAlipayRecordService.getCsAlipayRecordList(params, size);
	}
	
	/**
	 * 获取接口充值分页
	 * @return
	 */
	public static @api Page<CsAlipayRecord> getCsAlipayRecordPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsAlipayRecordService csAlipayRecordService = $.GetSpringBean("csAlipayRecordService");
		return csAlipayRecordService.getCsAlipayRecordPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取接口充值
	 * @param params
	 * @return
	 */
	public static @api CsAlipayRecord getCsAlipayRecord(Map params){
		com.ccclubs.service.admin.ICsAlipayRecordService csAlipayRecordService = $.GetSpringBean("csAlipayRecordService");
		return csAlipayRecordService.getCsAlipayRecord(params);
	}
	
	/**
	 * 获取接口充值数
	 * @return
	 */
	public static @api Long getCsAlipayRecordCount(Map params){
		com.ccclubs.service.admin.ICsAlipayRecordService csAlipayRecordService = $.GetSpringBean("csAlipayRecordService");
		return csAlipayRecordService.getCsAlipayRecordCount(params);
	}
		
		
	/**
	 * 获取接口充值自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsAlipayRecordEval(String eval,Map params){
		com.ccclubs.service.admin.ICsAlipayRecordService csAlipayRecordService = $.GetSpringBean("csAlipayRecordService");
		return csAlipayRecordService.getCsAlipayRecordEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsAlipayRecord(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsAlipayRecordService csAlipayRecordService = $.GetSpringBean("csAlipayRecordService");
		csAlipayRecordService.updateCsAlipayRecordByConfirm(set, where);
	}
	
	
	/**
	 * 保存接口充值对象
	 * @param params
	 * @return
	 */
	public CsAlipayRecord save(){
		com.ccclubs.service.admin.ICsAlipayRecordService csAlipayRecordService = $.GetSpringBean("csAlipayRecordService");
		if(this.getCsarId()!=null)
			csAlipayRecordService.updateCsAlipayRecord(this);
		else
			return csAlipayRecordService.saveCsAlipayRecord(this);
		return this;
	}
	
	
	/**
	 * 更新接口充值对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsAlipayRecordService csAlipayRecordService = $.GetSpringBean("csAlipayRecordService");
		csAlipayRecordService.updateCsAlipayRecord$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsAlipayRecordService csAlipayRecordService = $.GetSpringBean("csAlipayRecordService");
		if($.equals($.config("logic_delete"),"true"))
			csAlipayRecordService.removeCsAlipayRecordById(this.getCsarId());
		else
			csAlipayRecordService.deleteCsAlipayRecordById(this.getCsarId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsAlipayRecordService csAlipayRecordService = $.GetSpringBean("csAlipayRecordService");
		return csAlipayRecordService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsarId(){
		return this.csarId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsarId$(){
		String strValue="";
		 strValue=$.str(this.getCsarId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsarId(Long csarId){
		this.csarId = csarId;
		this.setSeted(F.csarId);
	}
	/*******************************运营地区**********************************/	
	/**
	* 运营地区 [非空] [SrvHost]     
	**/
	public Long getCsarHost(){
		return this.csarHost;
	}
	/**
	* 获取运营地区格式化(toString)
	**/
	public String getCsarHost$(){
		String strValue="";
		if(this.getCsarHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsarHost()));
		}			
	 	 return strValue;
	}
	/**
	* 运营地区 [非空] [SrvHost]     
	**/
	public void setCsarHost(Long csarHost){
		this.csarHost = csarHost;
		this.setSeted(F.csarHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csarHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsarHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsarHost()!=null){
 			srvHost = SrvHost.get(this.getCsarHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsarHost(), srvHost);
	 	return srvHost;
	}
	/*******************************接口类型**********************************/	
	/**
	* 接口类型 [非空]   0:支付宝 10:招商银行    
	**/
	public Short getCsarBankType(){
		return this.csarBankType;
	}
	/**
	* 获取接口类型格式化(toString)
	**/
	public String getCsarBankType$(){
		String strValue="";
		 if($.equals($.str(this.getCsarBankType()),"0"))
			strValue=$.str("支付宝");		 
		 if($.equals($.str(this.getCsarBankType()),"10"))
			strValue=$.str("招商银行");		 
	 	 return strValue;
	}
	/**
	* 接口类型 [非空]   0:支付宝 10:招商银行    
	**/
	public void setCsarBankType(Short csarBankType){
		this.csarBankType = csarBankType;
		this.setSeted(F.csarBankType);
	}
	/*******************************交易号**********************************/	
	/**
	* 交易号 [非空]      
	**/
	public String getCsarTradeNo(){
		return this.csarTradeNo;
	}
	/**
	* 获取交易号格式化(toString)
	**/
	public String getCsarTradeNo$(){
		String strValue="";
		 strValue=$.str(this.getCsarTradeNo());
	 	 return strValue;
	}
	/**
	* 交易号 [非空]      
	**/
	public void setCsarTradeNo(String csarTradeNo){
		this.csarTradeNo = csarTradeNo;
		this.setSeted(F.csarTradeNo);
	}
	/*******************************充值会员**********************************/	
	/**
	* 充值会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public Long getCsarMember(){
		return this.csarMember;
	}
	/**
	* 获取充值会员格式化(toString)
	**/
	public String getCsarMember$(){
		String strValue="";
		if(this.getCsarMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsarMember()));
		}			
	 	 return strValue;
	}
	/**
	* 充值会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public void setCsarMember(Long csarMember){
		this.csarMember = csarMember;
		this.setSeted(F.csarMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csarMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsarMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsarMember()!=null){
 			csMember = CsMember.get(this.getCsarMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsarMember(), csMember);
	 	return csMember;
	}
	/*******************************会员姓名**********************************/	
	/**
	* 会员姓名 [非空]      
	**/
	public String getCsarRealName(){
		return this.csarRealName;
	}
	/**
	* 获取会员姓名格式化(toString)
	**/
	public String getCsarRealName$(){
		String strValue="";
		 strValue=$.str(this.getCsarRealName());
	 	 return strValue;
	}
	/**
	* 会员姓名 [非空]      
	**/
	public void setCsarRealName(String csarRealName){
		this.csarRealName = csarRealName;
		this.setSeted(F.csarRealName);
	}
	/*******************************充值金额**********************************/	
	/**
	* 充值金额 [非空]      
	**/
	public Double getCsarAmount(){
		return this.csarAmount;
	}
	/**
	* 获取充值金额格式化(toString)
	**/
	public String getCsarAmount$(){
		String strValue="";
		 strValue=$.str(this.getCsarAmount());	
	 	 return strValue;
	}
	/**
	* 充值金额 [非空]      
	**/
	public void setCsarAmount(Double csarAmount){
		this.csarAmount = csarAmount;
		this.setSeted(F.csarAmount);
	}
	/*******************************购买宝贝**********************************/	
	/**
	* 购买宝贝  [CsItem]   标识充值完成后打算购买哪个宝贝  
	**/
	public Long getCsarBuyItem(){
		return this.csarBuyItem;
	}
	/**
	* 获取购买宝贝格式化(toString)
	**/
	public String getCsarBuyItem$(){
		String strValue="";
		if(this.getCsarBuyItem()!=null){
				strValue+=$.str(CsItem.getKeyValue(this.getCsarBuyItem()));
		}			
	 	 return strValue;
	}
	/**
	* 购买宝贝  [CsItem]   标识充值完成后打算购买哪个宝贝  
	**/
	public void setCsarBuyItem(Long csarBuyItem){
		this.csarBuyItem = csarBuyItem;
		this.setSeted(F.csarBuyItem);
	}
	/**
	* 获取关联对象[商品信息]
	**/	 			
 	public CsItem get$csarBuyItem(){
 		com.ccclubs.model.CsItem csItem = (com.ccclubs.model.CsItem) $.GetRequest("CsItem$"+this.getCsarBuyItem());
 		if(csItem!=null)
			return csItem;
		if(this.getCsarBuyItem()!=null){
 			csItem = CsItem.get(this.getCsarBuyItem());
 		}
 		$.SetRequest("CsItem$"+this.getCsarBuyItem(), csItem);
	 	return csItem;
	}
	/*******************************购买个数**********************************/	
	/**
	* 购买个数     标识充值完成后打算购买哪个宝贝的个数  
	**/
	public Integer getCsarBuyCount(){
		return this.csarBuyCount;
	}
	/**
	* 获取购买个数格式化(toString)
	**/
	public String getCsarBuyCount$(){
		String strValue="";
		 strValue=$.str(this.getCsarBuyCount());
	 	 return strValue;
	}
	/**
	* 购买个数     标识充值完成后打算购买哪个宝贝的个数  
	**/
	public void setCsarBuyCount(Integer csarBuyCount){
		this.csarBuyCount = csarBuyCount;
		this.setSeted(F.csarBuyCount);
	}
	/*******************************已退金额**********************************/	
	/**
	* 已退金额       
	**/
	public Double getCsarRefund(){
		return this.csarRefund;
	}
	/**
	* 获取已退金额格式化(toString)
	**/
	public String getCsarRefund$(){
		String strValue="";
		 strValue=$.str(this.getCsarRefund());	
	 	 return strValue;
	}
	/**
	* 已退金额       
	**/
	public void setCsarRefund(Double csarRefund){
		this.csarRefund = csarRefund;
		this.setSeted(F.csarRefund);
	}
	/*******************************支付接口**********************************/	
	/**
	* 支付接口       
	**/
	public String getCsarPayMethod(){
		return this.csarPayMethod;
	}
	/**
	* 获取支付接口格式化(toString)
	**/
	public String getCsarPayMethod$(){
		String strValue="";
		 strValue=$.str(this.getCsarPayMethod());
	 	 return strValue;
	}
	/**
	* 支付接口       
	**/
	public void setCsarPayMethod(String csarPayMethod){
		this.csarPayMethod = csarPayMethod;
		this.setSeted(F.csarPayMethod);
	}
	/*******************************银行名称**********************************/	
	/**
	* 银行名称       
	**/
	public String getCsarBlank(){
		return this.csarBlank;
	}
	/**
	* 获取银行名称格式化(toString)
	**/
	public String getCsarBlank$(){
		String strValue="";
		 strValue=$.str(this.getCsarBlank());
	 	 return strValue;
	}
	/**
	* 银行名称       
	**/
	public void setCsarBlank(String csarBlank){
		this.csarBlank = csarBlank;
		this.setSeted(F.csarBlank);
	}
	/*******************************支付宝交易号**********************************/	
	/**
	* 支付宝交易号       
	**/
	public String getCsarAlipayNo(){
		return this.csarAlipayNo;
	}
	/**
	* 获取支付宝交易号格式化(toString)
	**/
	public String getCsarAlipayNo$(){
		String strValue="";
		 strValue=$.str(this.getCsarAlipayNo());
	 	 return strValue;
	}
	/**
	* 支付宝交易号       
	**/
	public void setCsarAlipayNo(String csarAlipayNo){
		this.csarAlipayNo = csarAlipayNo;
		this.setSeted(F.csarAlipayNo);
	}
	/*******************************支付宝帐号**********************************/	
	/**
	* 支付宝帐号       
	**/
	public String getCsarBuyerAccount(){
		return this.csarBuyerAccount;
	}
	/**
	* 获取支付宝帐号格式化(toString)
	**/
	public String getCsarBuyerAccount$(){
		String strValue="";
		 strValue=$.str(this.getCsarBuyerAccount());
	 	 return strValue;
	}
	/**
	* 支付宝帐号       
	**/
	public void setCsarBuyerAccount(String csarBuyerAccount){
		this.csarBuyerAccount = csarBuyerAccount;
		this.setSeted(F.csarBuyerAccount);
	}
	/*******************************三方客户端ID**********************************/	
	/**
	* 三方客户端ID  [SrvApiClient]     
	**/
	public Long getCsarClientId(){
		return this.csarClientId;
	}
	/**
	* 获取三方客户端ID格式化(toString)
	**/
	public String getCsarClientId$(){
		String strValue="";
		if(this.getCsarClientId()!=null){
				strValue+=$.str(SrvApiClient.getKeyValue(this.getCsarClientId()));
		}			
	 	 return strValue;
	}
	/**
	* 三方客户端ID  [SrvApiClient]     
	**/
	public void setCsarClientId(Long csarClientId){
		this.csarClientId = csarClientId;
		this.setSeted(F.csarClientId);
	}
	/**
	* 获取关联对象[API客户端]
	**/	 			
 	public SrvApiClient get$csarClientId(){
 		com.ccclubs.model.SrvApiClient srvApiClient = (com.ccclubs.model.SrvApiClient) $.GetRequest("SrvApiClient$"+this.getCsarClientId());
 		if(srvApiClient!=null)
			return srvApiClient;
		if(this.getCsarClientId()!=null){
 			srvApiClient = SrvApiClient.get(this.getCsarClientId());
 		}
 		$.SetRequest("SrvApiClient$"+this.getCsarClientId(), srvApiClient);
	 	return srvApiClient;
	}
	/*******************************三方回调**********************************/	
	/**
	* 三方回调       
	**/
	public String getCsarThirdBack(){
		return this.csarThirdBack;
	}
	/**
	* 获取三方回调格式化(toString)
	**/
	public String getCsarThirdBack$(){
		String strValue="";
		 strValue=$.str(this.getCsarThirdBack());
	 	 return strValue;
	}
	/**
	* 三方回调       
	**/
	public void setCsarThirdBack(String csarThirdBack){
		this.csarThirdBack = csarThirdBack;
		this.setSeted(F.csarThirdBack);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsarUpdateTime(){
		return this.csarUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsarUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsarUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsarUpdateTime(Date csarUpdateTime){
		this.csarUpdateTime = csarUpdateTime;
		this.setSeted(F.csarUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsarAddTime(){
		return this.csarAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsarAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsarAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsarAddTime(Date csarAddTime){
		this.csarAddTime = csarAddTime;
		this.setSeted(F.csarAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:等待 1:成功 2:失败    
	**/
	public Short getCsarStatus(){
		return this.csarStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsarStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsarStatus()),"0"))
			strValue=$.str("等待");		 
		 if($.equals($.str(this.getCsarStatus()),"1"))
			strValue=$.str("成功");		 
		 if($.equals($.str(this.getCsarStatus()),"2"))
			strValue=$.str("失败");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:等待 1:成功 2:失败    
	**/
	public void setCsarStatus(Short csarStatus){
		this.csarStatus = csarStatus;
		this.setSeted(F.csarStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsAlipayRecord.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsAlipayRecord.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsAlipayRecord.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsAlipayRecord.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsAlipayRecord.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsAlipayRecord.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsAlipayRecord.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsAlipayRecord.this);
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
		public M csarId(Object csarId){this.put("csarId", csarId);return this;};
	 	/** and csar_id is null */
 		public M csarIdNull(){if(this.get("csarIdNot")==null)this.put("csarIdNot", "");this.put("csarId", null);return this;};
 		/** not .... */
 		public M csarIdNot(){this.put("csarIdNot", "not");return this;};
		/** 运营地区 [非空] [SrvHost]      **/
		public M csarHost(Object csarHost){this.put("csarHost", csarHost);return this;};
	 	/** and csar_host is null */
 		public M csarHostNull(){if(this.get("csarHostNot")==null)this.put("csarHostNot", "");this.put("csarHost", null);return this;};
 		/** not .... */
 		public M csarHostNot(){this.put("csarHostNot", "not");return this;};
		/** 接口类型 [非空]   0:支付宝 10:招商银行     **/
		public M csarBankType(Object csarBankType){this.put("csarBankType", csarBankType);return this;};
	 	/** and csar_bank_type is null */
 		public M csarBankTypeNull(){if(this.get("csarBankTypeNot")==null)this.put("csarBankTypeNot", "");this.put("csarBankType", null);return this;};
 		/** not .... */
 		public M csarBankTypeNot(){this.put("csarBankTypeNot", "not");return this;};
		/** 交易号 [非空]       **/
		public M csarTradeNo(Object csarTradeNo){this.put("csarTradeNo", csarTradeNo);return this;};
	 	/** and csar_trade_no is null */
 		public M csarTradeNoNull(){if(this.get("csarTradeNoNot")==null)this.put("csarTradeNoNot", "");this.put("csarTradeNo", null);return this;};
 		/** not .... */
 		public M csarTradeNoNot(){this.put("csarTradeNoNot", "not");return this;};
		/** 充值会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public M csarMember(Object csarMember){this.put("csarMember", csarMember);return this;};
	 	/** and csar_member is null */
 		public M csarMemberNull(){if(this.get("csarMemberNot")==null)this.put("csarMemberNot", "");this.put("csarMember", null);return this;};
 		/** not .... */
 		public M csarMemberNot(){this.put("csarMemberNot", "not");return this;};
		public M csarMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csarMember$on", value);
			return this;
		};	
		/** 会员姓名 [非空]       **/
		public M csarRealName(Object csarRealName){this.put("csarRealName", csarRealName);return this;};
	 	/** and csar_real_name is null */
 		public M csarRealNameNull(){if(this.get("csarRealNameNot")==null)this.put("csarRealNameNot", "");this.put("csarRealName", null);return this;};
 		/** not .... */
 		public M csarRealNameNot(){this.put("csarRealNameNot", "not");return this;};
		/** 充值金额 [非空]       **/
		public M csarAmount(Object csarAmount){this.put("csarAmount", csarAmount);return this;};
	 	/** and csar_amount is null */
 		public M csarAmountNull(){if(this.get("csarAmountNot")==null)this.put("csarAmountNot", "");this.put("csarAmount", null);return this;};
 		/** not .... */
 		public M csarAmountNot(){this.put("csarAmountNot", "not");return this;};
		/** and csar_amount >= ? */
		public M csarAmountMin(Object min){this.put("csarAmountMin", min);return this;};
		/** and csar_amount <= ? */
		public M csarAmountMax(Object max){this.put("csarAmountMax", max);return this;};
		/** 购买宝贝  [CsItem]   标识充值完成后打算购买哪个宝贝   **/
		public M csarBuyItem(Object csarBuyItem){this.put("csarBuyItem", csarBuyItem);return this;};
	 	/** and csar_buy_item is null */
 		public M csarBuyItemNull(){if(this.get("csarBuyItemNot")==null)this.put("csarBuyItemNot", "");this.put("csarBuyItem", null);return this;};
 		/** not .... */
 		public M csarBuyItemNot(){this.put("csarBuyItemNot", "not");return this;};
		public M csarBuyItem$on(CsItem.M value){
			this.put("CsItem", value);
			this.put("csarBuyItem$on", value);
			return this;
		};	
		/** 购买个数     标识充值完成后打算购买哪个宝贝的个数   **/
		public M csarBuyCount(Object csarBuyCount){this.put("csarBuyCount", csarBuyCount);return this;};
	 	/** and csar_buy_count is null */
 		public M csarBuyCountNull(){if(this.get("csarBuyCountNot")==null)this.put("csarBuyCountNot", "");this.put("csarBuyCount", null);return this;};
 		/** not .... */
 		public M csarBuyCountNot(){this.put("csarBuyCountNot", "not");return this;};
		/** and csar_buy_count >= ? */
		public M csarBuyCountMin(Object min){this.put("csarBuyCountMin", min);return this;};
		/** and csar_buy_count <= ? */
		public M csarBuyCountMax(Object max){this.put("csarBuyCountMax", max);return this;};
		/** 已退金额        **/
		public M csarRefund(Object csarRefund){this.put("csarRefund", csarRefund);return this;};
	 	/** and csar_refund is null */
 		public M csarRefundNull(){if(this.get("csarRefundNot")==null)this.put("csarRefundNot", "");this.put("csarRefund", null);return this;};
 		/** not .... */
 		public M csarRefundNot(){this.put("csarRefundNot", "not");return this;};
		/** and csar_refund >= ? */
		public M csarRefundMin(Object min){this.put("csarRefundMin", min);return this;};
		/** and csar_refund <= ? */
		public M csarRefundMax(Object max){this.put("csarRefundMax", max);return this;};
		/** 支付接口        **/
		public M csarPayMethod(Object csarPayMethod){this.put("csarPayMethod", csarPayMethod);return this;};
	 	/** and csar_pay_method is null */
 		public M csarPayMethodNull(){if(this.get("csarPayMethodNot")==null)this.put("csarPayMethodNot", "");this.put("csarPayMethod", null);return this;};
 		/** not .... */
 		public M csarPayMethodNot(){this.put("csarPayMethodNot", "not");return this;};
		/** 银行名称        **/
		public M csarBlank(Object csarBlank){this.put("csarBlank", csarBlank);return this;};
	 	/** and csar_blank is null */
 		public M csarBlankNull(){if(this.get("csarBlankNot")==null)this.put("csarBlankNot", "");this.put("csarBlank", null);return this;};
 		/** not .... */
 		public M csarBlankNot(){this.put("csarBlankNot", "not");return this;};
		/** 支付宝交易号        **/
		public M csarAlipayNo(Object csarAlipayNo){this.put("csarAlipayNo", csarAlipayNo);return this;};
	 	/** and csar_alipay_no is null */
 		public M csarAlipayNoNull(){if(this.get("csarAlipayNoNot")==null)this.put("csarAlipayNoNot", "");this.put("csarAlipayNo", null);return this;};
 		/** not .... */
 		public M csarAlipayNoNot(){this.put("csarAlipayNoNot", "not");return this;};
		/** 支付宝帐号        **/
		public M csarBuyerAccount(Object csarBuyerAccount){this.put("csarBuyerAccount", csarBuyerAccount);return this;};
	 	/** and csar_buyer_account is null */
 		public M csarBuyerAccountNull(){if(this.get("csarBuyerAccountNot")==null)this.put("csarBuyerAccountNot", "");this.put("csarBuyerAccount", null);return this;};
 		/** not .... */
 		public M csarBuyerAccountNot(){this.put("csarBuyerAccountNot", "not");return this;};
		/** 三方客户端ID  [SrvApiClient]      **/
		public M csarClientId(Object csarClientId){this.put("csarClientId", csarClientId);return this;};
	 	/** and csar_client_id is null */
 		public M csarClientIdNull(){if(this.get("csarClientIdNot")==null)this.put("csarClientIdNot", "");this.put("csarClientId", null);return this;};
 		/** not .... */
 		public M csarClientIdNot(){this.put("csarClientIdNot", "not");return this;};
		public M csarClientId$on(SrvApiClient.M value){
			this.put("SrvApiClient", value);
			this.put("csarClientId$on", value);
			return this;
		};	
		/** 三方回调        **/
		public M csarThirdBack(Object csarThirdBack){this.put("csarThirdBack", csarThirdBack);return this;};
	 	/** and csar_third_back is null */
 		public M csarThirdBackNull(){if(this.get("csarThirdBackNot")==null)this.put("csarThirdBackNot", "");this.put("csarThirdBack", null);return this;};
 		/** not .... */
 		public M csarThirdBackNot(){this.put("csarThirdBackNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csarUpdateTime(Object csarUpdateTime){this.put("csarUpdateTime", csarUpdateTime);return this;};
	 	/** and csar_update_time is null */
 		public M csarUpdateTimeNull(){if(this.get("csarUpdateTimeNot")==null)this.put("csarUpdateTimeNot", "");this.put("csarUpdateTime", null);return this;};
 		/** not .... */
 		public M csarUpdateTimeNot(){this.put("csarUpdateTimeNot", "not");return this;};
 		/** and csar_update_time >= ? */
 		public M csarUpdateTimeStart(Object start){this.put("csarUpdateTimeStart", start);return this;};			
 		/** and csar_update_time <= ? */
 		public M csarUpdateTimeEnd(Object end){this.put("csarUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csarAddTime(Object csarAddTime){this.put("csarAddTime", csarAddTime);return this;};
	 	/** and csar_add_time is null */
 		public M csarAddTimeNull(){if(this.get("csarAddTimeNot")==null)this.put("csarAddTimeNot", "");this.put("csarAddTime", null);return this;};
 		/** not .... */
 		public M csarAddTimeNot(){this.put("csarAddTimeNot", "not");return this;};
 		/** and csar_add_time >= ? */
 		public M csarAddTimeStart(Object start){this.put("csarAddTimeStart", start);return this;};			
 		/** and csar_add_time <= ? */
 		public M csarAddTimeEnd(Object end){this.put("csarAddTimeEnd", end);return this;};
		/** 状态 [非空]   0:等待 1:成功 2:失败     **/
		public M csarStatus(Object csarStatus){this.put("csarStatus", csarStatus);return this;};
	 	/** and csar_status is null */
 		public M csarStatusNull(){if(this.get("csarStatusNot")==null)this.put("csarStatusNot", "");this.put("csarStatus", null);return this;};
 		/** not .... */
 		public M csarStatusNot(){this.put("csarStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有接口充值 **/
		public @api List<CsAlipayRecord> list(Integer size){
			return getCsAlipayRecordList(this,size);
		}
		/** 获取接口充值分页 **/
		public @api Page<CsAlipayRecord> page(int page,int size){
			return getCsAlipayRecordPage(page, size , this);
		}
		/** 根据查询条件取接口充值 **/
		public @api CsAlipayRecord get(){
			return getCsAlipayRecord(this);
		}
		/** 获取接口充值数 **/
		public @api Long count(){
			return getCsAlipayRecordCount(this);
		}
		/** 获取接口充值表达式 **/
		public @api <T> T eval(String strEval){
			return getCsAlipayRecordEval(strEval,this);
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
			updateCsAlipayRecord(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csarId="csarId";
		/** 运营地区 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csarHost="csarHost";
		/** 接口类型 [非空]   0:支付宝 10:招商银行     **/
		public final static @type(Short.class)  String csarBankType="csarBankType";
		/** 交易号 [非空]       **/
		public final static @type(String.class) @like String csarTradeNo="csarTradeNo";
		/** 充值会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static @type(Long.class)  String csarMember="csarMember";
		/** 会员姓名 [非空]       **/
		public final static @type(String.class) @like String csarRealName="csarRealName";
		/** 充值金额 [非空]       **/
		public final static @type(Double.class)  String csarAmount="csarAmount";
		/** and csar_amount >= ? */
		public final static @type(Double.class) String csarAmountMin="csarAmountMin";
		/** and csar_amount <= ? */
		public final static @type(Double.class) String csarAmountMax="csarAmountMax";
		/** 购买宝贝  [CsItem]   标识充值完成后打算购买哪个宝贝   **/
		public final static @type(Long.class)  String csarBuyItem="csarBuyItem";
		/** 购买个数     标识充值完成后打算购买哪个宝贝的个数   **/
		public final static @type(Integer.class)  String csarBuyCount="csarBuyCount";
		/** and csar_buy_count >= ? */
		public final static @type(Integer.class) String csarBuyCountMin="csarBuyCountMin";
		/** and csar_buy_count <= ? */
		public final static @type(Integer.class) String csarBuyCountMax="csarBuyCountMax";
		/** 已退金额        **/
		public final static @type(Double.class)  String csarRefund="csarRefund";
		/** and csar_refund >= ? */
		public final static @type(Double.class) String csarRefundMin="csarRefundMin";
		/** and csar_refund <= ? */
		public final static @type(Double.class) String csarRefundMax="csarRefundMax";
		/** 支付接口        **/
		public final static @type(String.class) @like String csarPayMethod="csarPayMethod";
		/** 银行名称        **/
		public final static @type(String.class) @like String csarBlank="csarBlank";
		/** 支付宝交易号        **/
		public final static @type(String.class) @like String csarAlipayNo="csarAlipayNo";
		/** 支付宝帐号        **/
		public final static @type(String.class) @like String csarBuyerAccount="csarBuyerAccount";
		/** 三方客户端ID  [SrvApiClient]      **/
		public final static @type(Long.class)  String csarClientId="csarClientId";
		/** 三方回调        **/
		public final static @type(String.class) @like String csarThirdBack="csarThirdBack";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csarUpdateTime="csarUpdateTime";
	 	/** and csar_update_time >= ? */
 		public final static @type(Date.class) String csarUpdateTimeStart="csarUpdateTimeStart";
 		/** and csar_update_time <= ? */
 		public final static @type(Date.class) String csarUpdateTimeEnd="csarUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csarAddTime="csarAddTime";
	 	/** and csar_add_time >= ? */
 		public final static @type(Date.class) String csarAddTimeStart="csarAddTimeStart";
 		/** and csar_add_time <= ? */
 		public final static @type(Date.class) String csarAddTimeEnd="csarAddTimeEnd";
		/** 状态 [非空]   0:等待 1:成功 2:失败     **/
		public final static @type(Short.class)  String csarStatus="csarStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csarId="csar_id";
		/** 运营地区 [非空] [SrvHost]      **/
		public final static String csarHost="csar_host";
		/** 接口类型 [非空]   0:支付宝 10:招商银行     **/
		public final static String csarBankType="csar_bank_type";
		/** 交易号 [非空]       **/
		public final static String csarTradeNo="csar_trade_no";
		/** 充值会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static String csarMember="csar_member";
		/** 会员姓名 [非空]       **/
		public final static String csarRealName="csar_real_name";
		/** 充值金额 [非空]       **/
		public final static String csarAmount="csar_amount";
		/** 购买宝贝  [CsItem]   标识充值完成后打算购买哪个宝贝   **/
		public final static String csarBuyItem="csar_buy_item";
		/** 购买个数     标识充值完成后打算购买哪个宝贝的个数   **/
		public final static String csarBuyCount="csar_buy_count";
		/** 已退金额        **/
		public final static String csarRefund="csar_refund";
		/** 支付接口        **/
		public final static String csarPayMethod="csar_pay_method";
		/** 银行名称        **/
		public final static String csarBlank="csar_blank";
		/** 支付宝交易号        **/
		public final static String csarAlipayNo="csar_alipay_no";
		/** 支付宝帐号        **/
		public final static String csarBuyerAccount="csar_buyer_account";
		/** 三方客户端ID  [SrvApiClient]      **/
		public final static String csarClientId="csar_client_id";
		/** 三方回调        **/
		public final static String csarThirdBack="csar_third_back";
		/** 修改时间 [非空]       **/
		public final static String csarUpdateTime="csar_update_time";
		/** 添加时间 [非空]       **/
		public final static String csarAddTime="csar_add_time";
		/** 状态 [非空]   0:等待 1:成功 2:失败     **/
		public final static String csarStatus="csar_status";
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
				$.Set(name,CsAlipayRecord.getCsAlipayRecord(params));
			else
				$.Set(name,CsAlipayRecord.getCsAlipayRecordList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取接口充值数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsAlipayRecord) $.GetRequest("CsAlipayRecord$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsAlipayRecord.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsAlipayRecord.getCsAlipayRecord(params);
			else
				value = CsAlipayRecord.getCsAlipayRecordList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsAlipayRecord.Get($.add(CsAlipayRecord.F.csarId,param));
		else if(!$.empty(param.toString()))
			value = CsAlipayRecord.get(Long.valueOf(param.toString()));
		$.SetRequest("CsAlipayRecord$"+param.hashCode(), value);
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
	public void mergeSet(CsAlipayRecord old){
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