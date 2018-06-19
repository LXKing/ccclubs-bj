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

@namespace("finance/alipay/alipayrefund")
public @caption("接口退款") @table("cs_alipay_refund") class CsAlipayRefund implements java.io.Serializable
{
	private static final long serialVersionUID = 1l;
	private @caption("编号") @column("csar_id")   @primary  @note("  ") Long csarId;// 主键 非空     
	private @caption("运营地区") @column("csar_host")    @relate("$csarHost") @RelateClass(SrvHost.class)  @note("  ") Long csarHost;// 非空     
 	private SrvHost $csarHost;//关联对象[运营城市]
	private @caption("接口类型") @column("csar_bank_type")    @note(" 0:支付宝 10:招商银行 21:支付宝转帐 22:银行卡转帐 23:邮局汇款 24:现金退款  ") Short csarBankType;// 非空 0:支付宝 10:招商银行 21:支付宝转帐 22:银行卡转帐 23:邮局汇款 24:现金退款     
	private @caption("退款批次号") @column("csar_trade_no")    @note("  ") String csarTradeNo;// 非空     
	private @caption("退款会员") @column("csar_member")    @relate("$csarMember") @RelateClass(CsMember.class)  @note("  ") Long csarMember;// 非空    ${basePath}${proname}/user/member_inquire.do 
 	private CsMember $csarMember;//关联对象[会员帐号]
	private @caption("会员姓名") @column("csar_real_name")    @note("  ") String csarRealName;// 非空     
	private @caption("退款金额") @column("csar_amount")    @note("  ") Double csarAmount;// 非空     
	private @caption("关联退款申请") @column("csar_refund")    @relate("$csarRefund") @RelateClass(CsRefund.class)  @note("  ") Long csarRefund;// 非空     
 	private CsRefund $csarRefund;//关联对象[会员退款]
	private @caption("原接口充值") @column("csar_record")    @relate("$csarRecord") @RelateClass(CsAlipayRecord.class)  @note("  ") Long csarRecord;// 非空     
 	private CsAlipayRecord $csarRecord;//关联对象[接口充值]
	private @caption("备注信息") @column("csar_alipay_no")    @note("  ") String csarAlipayNo;// 非空     
	private @caption("修改时间") @column("csar_update_time")    @note("  ") Date csarUpdateTime;// 非空     
	private @caption("添加时间") @column("csar_add_time")    @note("  ") Date csarAddTime;// 非空     
	private @caption("状态") @column("csar_status")    @note(" 0:等待 1:成功 2:失败 3:取消  ") Short csarStatus;// 非空 0:等待 1:成功 2:失败 3:取消     
	
	//默认构造函数
	public CsAlipayRefund(){
	
	}
	
	//主键构造函数
	public CsAlipayRefund(Long id){
		this.csarId = id;
	}
	
	/**所有字段构造函数 CsAlipayRefund(csarHost,csarBankType,csarTradeNo,csarMember,csarRealName,csarAmount,csarRefund,csarRecord,csarAlipayNo,csarUpdateTime,csarAddTime,csarStatus)
	 CsAlipayRefund(
	 	$.getLong("csarHost")//运营地区 [非空]
	 	,$.getShort("csarBankType")//接口类型 [非空]
	 	,$.getString("csarTradeNo")//退款批次号 [非空]
	 	,$.getLong("csarMember")//退款会员 [非空]
	 	,$.getString("csarRealName")//会员姓名 [非空]
	 	,$.getDouble("csarAmount")//退款金额 [非空]
	 	,$.getLong("csarRefund")//关联退款申请 [非空]
	 	,$.getLong("csarRecord")//原接口充值 [非空]
	 	,$.getString("csarAlipayNo")//备注信息 [非空]
	 	,$.getDate("csarUpdateTime")//修改时间 [非空]
	 	,$.getDate("csarAddTime")//添加时间 [非空]
	 	,$.getShort("csarStatus")//状态 [非空]
	 )
	**/
	public CsAlipayRefund(Long csarHost,Short csarBankType,String csarTradeNo,Long csarMember,String csarRealName,Double csarAmount,Long csarRefund,Long csarRecord,String csarAlipayNo,Date csarUpdateTime,Date csarAddTime,Short csarStatus){
		this.csarHost=csarHost;
		this.csarBankType=csarBankType;
		this.csarTradeNo=csarTradeNo;
		this.csarMember=csarMember;
		this.csarRealName=csarRealName;
		this.csarAmount=csarAmount;
		this.csarRefund=csarRefund;
		this.csarRecord=csarRecord;
		this.csarAlipayNo=csarAlipayNo;
		this.csarUpdateTime=csarUpdateTime;
		this.csarAddTime=csarAddTime;
		this.csarStatus=csarStatus;
	}
	
	//设置非空字段
	public CsAlipayRefund setNotNull(Long csarHost,Short csarBankType,String csarTradeNo,Long csarMember,String csarRealName,Double csarAmount,Long csarRefund,Long csarRecord,String csarAlipayNo,Date csarUpdateTime,Date csarAddTime,Short csarStatus){
		this.csarHost=csarHost;
		this.csarBankType=csarBankType;
		this.csarTradeNo=csarTradeNo;
		this.csarMember=csarMember;
		this.csarRealName=csarRealName;
		this.csarAmount=csarAmount;
		this.csarRefund=csarRefund;
		this.csarRecord=csarRecord;
		this.csarAlipayNo=csarAlipayNo;
		this.csarUpdateTime=csarUpdateTime;
		this.csarAddTime=csarAddTime;
		this.csarStatus=csarStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsAlipayRefund csarId(Long csarId){
		this.csarId = csarId;
		this.setSeted(F.csarId);
		return this;
	}
	/** 运营地区 [非空] [SrvHost]      **/
	public CsAlipayRefund csarHost(Long csarHost){
		this.csarHost = csarHost;
		this.setSeted(F.csarHost);
		return this;
	}
	/** 接口类型 [非空]   0:支付宝 10:招商银行 21:支付宝转帐 22:银行卡转帐 23:邮局汇款 24:现金退款     **/
	public CsAlipayRefund csarBankType(Short csarBankType){
		this.csarBankType = csarBankType;
		this.setSeted(F.csarBankType);
		return this;
	}
	/** 退款批次号 [非空]       **/
	public CsAlipayRefund csarTradeNo(String csarTradeNo){
		this.csarTradeNo = csarTradeNo;
		this.setSeted(F.csarTradeNo);
		return this;
	}
	/** 退款会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
	public CsAlipayRefund csarMember(Long csarMember){
		this.csarMember = csarMember;
		this.setSeted(F.csarMember);
		return this;
	}
	/** 会员姓名 [非空]       **/
	public CsAlipayRefund csarRealName(String csarRealName){
		this.csarRealName = csarRealName;
		this.setSeted(F.csarRealName);
		return this;
	}
	/** 退款金额 [非空]       **/
	public CsAlipayRefund csarAmount(Double csarAmount){
		this.csarAmount = csarAmount;
		this.setSeted(F.csarAmount);
		return this;
	}
	/** 关联退款申请 [非空] [CsRefund]      **/
	public CsAlipayRefund csarRefund(Long csarRefund){
		this.csarRefund = csarRefund;
		this.setSeted(F.csarRefund);
		return this;
	}
	/** 原接口充值 [非空] [CsAlipayRecord]      **/
	public CsAlipayRefund csarRecord(Long csarRecord){
		this.csarRecord = csarRecord;
		this.setSeted(F.csarRecord);
		return this;
	}
	/** 备注信息 [非空]       **/
	public CsAlipayRefund csarAlipayNo(String csarAlipayNo){
		this.csarAlipayNo = csarAlipayNo;
		this.setSeted(F.csarAlipayNo);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsAlipayRefund csarUpdateTime(Date csarUpdateTime){
		this.csarUpdateTime = csarUpdateTime;
		this.setSeted(F.csarUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsAlipayRefund csarAddTime(Date csarAddTime){
		this.csarAddTime = csarAddTime;
		this.setSeted(F.csarAddTime);
		return this;
	}
	/** 状态 [非空]   0:等待 1:成功 2:失败 3:取消     **/
	public CsAlipayRefund csarStatus(Short csarStatus){
		this.csarStatus = csarStatus;
		this.setSeted(F.csarStatus);
		return this;
	}
	
	
	//克隆对象
	public CsAlipayRefund clone(){
		CsAlipayRefund clone = new CsAlipayRefund();
		clone.csarHost=this.csarHost;
		clone.csarBankType=this.csarBankType;
		clone.csarTradeNo=this.csarTradeNo;
		clone.csarMember=this.csarMember;
		clone.csarRealName=this.csarRealName;
		clone.csarAmount=this.csarAmount;
		clone.csarRefund=this.csarRefund;
		clone.csarRecord=this.csarRecord;
		clone.csarAlipayNo=this.csarAlipayNo;
		clone.csarUpdateTime=this.csarUpdateTime;
		clone.csarAddTime=this.csarAddTime;
		clone.csarStatus=this.csarStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取接口退款
	 * @param id
	 * @return
	 */
	public static @api CsAlipayRefund get(Long id){		
		return getCsAlipayRefundById(id);
	}
	/**
	 * 获取所有接口退款
	 * @return
	 */
	public static @api List<CsAlipayRefund> list(Map params,Integer size){
		return getCsAlipayRefundList(params,size);
	}
	/**
	 * 获取接口退款分页
	 * @return
	 */
	public static @api Page<CsAlipayRefund> page(int page,int size,Map params){
		return getCsAlipayRefundPage(page, size , params);
	}
	/**
	 * 根据查询条件取接口退款
	 * @param params
	 * @return
	 */
	public static @api CsAlipayRefund Get(Map params){
		return getCsAlipayRefund(params);
	}
	/**
	 * 获取接口退款数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsAlipayRefundCount(params);
	}
	/**
	 * 获取接口退款数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsAlipayRefundEval(eval,params);
	}
	
	/**
	 * 根据ID取接口退款
	 * @param id
	 * @return
	 */
	public static @api CsAlipayRefund getCsAlipayRefundById(Long id){		
		CsAlipayRefund csAlipayRefund = (CsAlipayRefund) $.GetRequest("CsAlipayRefund$"+id);
		if(csAlipayRefund!=null)
			return csAlipayRefund;
		com.ccclubs.service.admin.ICsAlipayRefundService csAlipayRefundService = $.GetSpringBean("csAlipayRefundService");		
		return csAlipayRefundService.getCsAlipayRefundById(id);
	}
	
	
	/**
	 * 根据ID取接口退款的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsAlipayRefund.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsAlipayRefund csAlipayRefund = get(id);
		if(csAlipayRefund!=null){
			String strValue = csAlipayRefund.getCsarTradeNo$();
			if(!"CsarTradeNo".equals("CsarTradeNo"))
				strValue+="("+csAlipayRefund.getCsarTradeNo$()+")";
			MemCache.setValue(CsAlipayRefund.class, id ,strValue);
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
	 * 获取所有接口退款
	 * @return
	 */
	public static @api List<CsAlipayRefund> getCsAlipayRefundList(Map params,Integer size){
		com.ccclubs.service.admin.ICsAlipayRefundService csAlipayRefundService = $.GetSpringBean("csAlipayRefundService");
		return csAlipayRefundService.getCsAlipayRefundList(params, size);
	}
	
	/**
	 * 获取接口退款分页
	 * @return
	 */
	public static @api Page<CsAlipayRefund> getCsAlipayRefundPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsAlipayRefundService csAlipayRefundService = $.GetSpringBean("csAlipayRefundService");
		return csAlipayRefundService.getCsAlipayRefundPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取接口退款
	 * @param params
	 * @return
	 */
	public static @api CsAlipayRefund getCsAlipayRefund(Map params){
		com.ccclubs.service.admin.ICsAlipayRefundService csAlipayRefundService = $.GetSpringBean("csAlipayRefundService");
		return csAlipayRefundService.getCsAlipayRefund(params);
	}
	
	/**
	 * 获取接口退款数
	 * @return
	 */
	public static @api Long getCsAlipayRefundCount(Map params){
		com.ccclubs.service.admin.ICsAlipayRefundService csAlipayRefundService = $.GetSpringBean("csAlipayRefundService");
		return csAlipayRefundService.getCsAlipayRefundCount(params);
	}
		
		
	/**
	 * 获取接口退款自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsAlipayRefundEval(String eval,Map params){
		com.ccclubs.service.admin.ICsAlipayRefundService csAlipayRefundService = $.GetSpringBean("csAlipayRefundService");
		return csAlipayRefundService.getCsAlipayRefundEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsAlipayRefund(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsAlipayRefundService csAlipayRefundService = $.GetSpringBean("csAlipayRefundService");
		csAlipayRefundService.updateCsAlipayRefundByConfirm(set, where);
	}
	
	
	/**
	 * 保存接口退款对象
	 * @param params
	 * @return
	 */
	public CsAlipayRefund save(){
		com.ccclubs.service.admin.ICsAlipayRefundService csAlipayRefundService = $.GetSpringBean("csAlipayRefundService");
		if(this.getCsarId()!=null)
			csAlipayRefundService.updateCsAlipayRefund(this);
		else
			return csAlipayRefundService.saveCsAlipayRefund(this);
		return this;
	}
	
	
	/**
	 * 更新接口退款对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsAlipayRefundService csAlipayRefundService = $.GetSpringBean("csAlipayRefundService");
		csAlipayRefundService.updateCsAlipayRefund$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsAlipayRefundService csAlipayRefundService = $.GetSpringBean("csAlipayRefundService");
		if($.equals($.config("logic_delete"),"true"))
			csAlipayRefundService.removeCsAlipayRefundById(this.getCsarId());
		else
			csAlipayRefundService.deleteCsAlipayRefundById(this.getCsarId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsAlipayRefundService csAlipayRefundService = $.GetSpringBean("csAlipayRefundService");
		return csAlipayRefundService.executeTransaction(function);
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
	* 接口类型 [非空]   0:支付宝 10:招商银行 21:支付宝转帐 22:银行卡转帐 23:邮局汇款 24:现金退款    
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
		 if($.equals($.str(this.getCsarBankType()),"21"))
			strValue=$.str("支付宝转帐");		 
		 if($.equals($.str(this.getCsarBankType()),"22"))
			strValue=$.str("银行卡转帐");		 
		 if($.equals($.str(this.getCsarBankType()),"23"))
			strValue=$.str("邮局汇款");		 
		 if($.equals($.str(this.getCsarBankType()),"24"))
			strValue=$.str("现金退款");		 
	 	 return strValue;
	}
	/**
	* 接口类型 [非空]   0:支付宝 10:招商银行 21:支付宝转帐 22:银行卡转帐 23:邮局汇款 24:现金退款    
	**/
	public void setCsarBankType(Short csarBankType){
		this.csarBankType = csarBankType;
		this.setSeted(F.csarBankType);
	}
	/*******************************退款批次号**********************************/	
	/**
	* 退款批次号 [非空]      
	**/
	public String getCsarTradeNo(){
		return this.csarTradeNo;
	}
	/**
	* 获取退款批次号格式化(toString)
	**/
	public String getCsarTradeNo$(){
		String strValue="";
		 strValue=$.str(this.getCsarTradeNo());
	 	 return strValue;
	}
	/**
	* 退款批次号 [非空]      
	**/
	public void setCsarTradeNo(String csarTradeNo){
		this.csarTradeNo = csarTradeNo;
		this.setSeted(F.csarTradeNo);
	}
	/*******************************退款会员**********************************/	
	/**
	* 退款会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public Long getCsarMember(){
		return this.csarMember;
	}
	/**
	* 获取退款会员格式化(toString)
	**/
	public String getCsarMember$(){
		String strValue="";
		if(this.getCsarMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsarMember()));
		}			
	 	 return strValue;
	}
	/**
	* 退款会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
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
	/*******************************退款金额**********************************/	
	/**
	* 退款金额 [非空]      
	**/
	public Double getCsarAmount(){
		return this.csarAmount;
	}
	/**
	* 获取退款金额格式化(toString)
	**/
	public String getCsarAmount$(){
		String strValue="";
		 strValue=$.str(this.getCsarAmount());	
	 	 return strValue;
	}
	/**
	* 退款金额 [非空]      
	**/
	public void setCsarAmount(Double csarAmount){
		this.csarAmount = csarAmount;
		this.setSeted(F.csarAmount);
	}
	/*******************************关联退款申请**********************************/	
	/**
	* 关联退款申请 [非空] [CsRefund]     
	**/
	public Long getCsarRefund(){
		return this.csarRefund;
	}
	/**
	* 获取关联退款申请格式化(toString)
	**/
	public String getCsarRefund$(){
		String strValue="";
		if(this.getCsarRefund()!=null){
				strValue+=$.str(CsRefund.getKeyValue(this.getCsarRefund()));
		}			
	 	 return strValue;
	}
	/**
	* 关联退款申请 [非空] [CsRefund]     
	**/
	public void setCsarRefund(Long csarRefund){
		this.csarRefund = csarRefund;
		this.setSeted(F.csarRefund);
	}
	/**
	* 获取关联对象[会员退款]
	**/	 			
 	public CsRefund get$csarRefund(){
 		com.ccclubs.model.CsRefund csRefund = (com.ccclubs.model.CsRefund) $.GetRequest("CsRefund$"+this.getCsarRefund());
 		if(csRefund!=null)
			return csRefund;
		if(this.getCsarRefund()!=null){
 			csRefund = CsRefund.get(this.getCsarRefund());
 		}
 		$.SetRequest("CsRefund$"+this.getCsarRefund(), csRefund);
	 	return csRefund;
	}
	/*******************************原接口充值**********************************/	
	/**
	* 原接口充值 [非空] [CsAlipayRecord]     
	**/
	public Long getCsarRecord(){
		return this.csarRecord;
	}
	/**
	* 获取原接口充值格式化(toString)
	**/
	public String getCsarRecord$(){
		String strValue="";
		if(this.getCsarRecord()!=null){
				strValue+=$.str(CsAlipayRecord.getKeyValue(this.getCsarRecord()));
		}			
	 	 return strValue;
	}
	/**
	* 原接口充值 [非空] [CsAlipayRecord]     
	**/
	public void setCsarRecord(Long csarRecord){
		this.csarRecord = csarRecord;
		this.setSeted(F.csarRecord);
	}
	/**
	* 获取关联对象[接口充值]
	**/	 			
 	public CsAlipayRecord get$csarRecord(){
 		com.ccclubs.model.CsAlipayRecord csAlipayRecord = (com.ccclubs.model.CsAlipayRecord) $.GetRequest("CsAlipayRecord$"+this.getCsarRecord());
 		if(csAlipayRecord!=null)
			return csAlipayRecord;
		if(this.getCsarRecord()!=null){
 			csAlipayRecord = CsAlipayRecord.get(this.getCsarRecord());
 		}
 		$.SetRequest("CsAlipayRecord$"+this.getCsarRecord(), csAlipayRecord);
	 	return csAlipayRecord;
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息 [非空]      
	**/
	public String getCsarAlipayNo(){
		return this.csarAlipayNo;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCsarAlipayNo$(){
		String strValue="";
		 strValue=$.str(this.getCsarAlipayNo());
	 	 return strValue;
	}
	/**
	* 备注信息 [非空]      
	**/
	public void setCsarAlipayNo(String csarAlipayNo){
		this.csarAlipayNo = csarAlipayNo;
		this.setSeted(F.csarAlipayNo);
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
	* 状态 [非空]   0:等待 1:成功 2:失败 3:取消    
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
		 if($.equals($.str(this.getCsarStatus()),"3"))
			strValue=$.str("取消");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:等待 1:成功 2:失败 3:取消    
	**/
	public void setCsarStatus(Short csarStatus){
		this.csarStatus = csarStatus;
		this.setSeted(F.csarStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsAlipayRefund.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsAlipayRefund.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsAlipayRefund.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsAlipayRefund.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsAlipayRefund.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsAlipayRefund.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsAlipayRefund.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsAlipayRefund.this);
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
		/** 接口类型 [非空]   0:支付宝 10:招商银行 21:支付宝转帐 22:银行卡转帐 23:邮局汇款 24:现金退款     **/
		public M csarBankType(Object csarBankType){this.put("csarBankType", csarBankType);return this;};
	 	/** and csar_bank_type is null */
 		public M csarBankTypeNull(){if(this.get("csarBankTypeNot")==null)this.put("csarBankTypeNot", "");this.put("csarBankType", null);return this;};
 		/** not .... */
 		public M csarBankTypeNot(){this.put("csarBankTypeNot", "not");return this;};
		/** 退款批次号 [非空]       **/
		public M csarTradeNo(Object csarTradeNo){this.put("csarTradeNo", csarTradeNo);return this;};
	 	/** and csar_trade_no is null */
 		public M csarTradeNoNull(){if(this.get("csarTradeNoNot")==null)this.put("csarTradeNoNot", "");this.put("csarTradeNo", null);return this;};
 		/** not .... */
 		public M csarTradeNoNot(){this.put("csarTradeNoNot", "not");return this;};
		/** 退款会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
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
		/** 退款金额 [非空]       **/
		public M csarAmount(Object csarAmount){this.put("csarAmount", csarAmount);return this;};
	 	/** and csar_amount is null */
 		public M csarAmountNull(){if(this.get("csarAmountNot")==null)this.put("csarAmountNot", "");this.put("csarAmount", null);return this;};
 		/** not .... */
 		public M csarAmountNot(){this.put("csarAmountNot", "not");return this;};
		/** and csar_amount >= ? */
		public M csarAmountMin(Object min){this.put("csarAmountMin", min);return this;};
		/** and csar_amount <= ? */
		public M csarAmountMax(Object max){this.put("csarAmountMax", max);return this;};
		/** 关联退款申请 [非空] [CsRefund]      **/
		public M csarRefund(Object csarRefund){this.put("csarRefund", csarRefund);return this;};
	 	/** and csar_refund is null */
 		public M csarRefundNull(){if(this.get("csarRefundNot")==null)this.put("csarRefundNot", "");this.put("csarRefund", null);return this;};
 		/** not .... */
 		public M csarRefundNot(){this.put("csarRefundNot", "not");return this;};
		public M csarRefund$on(CsRefund.M value){
			this.put("CsRefund", value);
			this.put("csarRefund$on", value);
			return this;
		};	
		/** 原接口充值 [非空] [CsAlipayRecord]      **/
		public M csarRecord(Object csarRecord){this.put("csarRecord", csarRecord);return this;};
	 	/** and csar_record is null */
 		public M csarRecordNull(){if(this.get("csarRecordNot")==null)this.put("csarRecordNot", "");this.put("csarRecord", null);return this;};
 		/** not .... */
 		public M csarRecordNot(){this.put("csarRecordNot", "not");return this;};
		public M csarRecord$on(CsAlipayRecord.M value){
			this.put("CsAlipayRecord", value);
			this.put("csarRecord$on", value);
			return this;
		};	
		/** 备注信息 [非空]       **/
		public M csarAlipayNo(Object csarAlipayNo){this.put("csarAlipayNo", csarAlipayNo);return this;};
	 	/** and csar_alipay_no is null */
 		public M csarAlipayNoNull(){if(this.get("csarAlipayNoNot")==null)this.put("csarAlipayNoNot", "");this.put("csarAlipayNo", null);return this;};
 		/** not .... */
 		public M csarAlipayNoNot(){this.put("csarAlipayNoNot", "not");return this;};
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
		/** 状态 [非空]   0:等待 1:成功 2:失败 3:取消     **/
		public M csarStatus(Object csarStatus){this.put("csarStatus", csarStatus);return this;};
	 	/** and csar_status is null */
 		public M csarStatusNull(){if(this.get("csarStatusNot")==null)this.put("csarStatusNot", "");this.put("csarStatus", null);return this;};
 		/** not .... */
 		public M csarStatusNot(){this.put("csarStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有接口退款 **/
		public @api List<CsAlipayRefund> list(Integer size){
			return getCsAlipayRefundList(this,size);
		}
		/** 获取接口退款分页 **/
		public @api Page<CsAlipayRefund> page(int page,int size){
			return getCsAlipayRefundPage(page, size , this);
		}
		/** 根据查询条件取接口退款 **/
		public @api CsAlipayRefund get(){
			return getCsAlipayRefund(this);
		}
		/** 获取接口退款数 **/
		public @api Long count(){
			return getCsAlipayRefundCount(this);
		}
		/** 获取接口退款表达式 **/
		public @api <T> T eval(String strEval){
			return getCsAlipayRefundEval(strEval,this);
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
			updateCsAlipayRefund(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csarId="csarId";
		/** 运营地区 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csarHost="csarHost";
		/** 接口类型 [非空]   0:支付宝 10:招商银行 21:支付宝转帐 22:银行卡转帐 23:邮局汇款 24:现金退款     **/
		public final static @type(Short.class)  String csarBankType="csarBankType";
		/** 退款批次号 [非空]       **/
		public final static @type(String.class) @like String csarTradeNo="csarTradeNo";
		/** 退款会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static @type(Long.class)  String csarMember="csarMember";
		/** 会员姓名 [非空]       **/
		public final static @type(String.class) @like String csarRealName="csarRealName";
		/** 退款金额 [非空]       **/
		public final static @type(Double.class)  String csarAmount="csarAmount";
		/** and csar_amount >= ? */
		public final static @type(Double.class) String csarAmountMin="csarAmountMin";
		/** and csar_amount <= ? */
		public final static @type(Double.class) String csarAmountMax="csarAmountMax";
		/** 关联退款申请 [非空] [CsRefund]      **/
		public final static @type(Long.class)  String csarRefund="csarRefund";
		/** 原接口充值 [非空] [CsAlipayRecord]      **/
		public final static @type(Long.class)  String csarRecord="csarRecord";
		/** 备注信息 [非空]       **/
		public final static @type(String.class) @like String csarAlipayNo="csarAlipayNo";
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
		/** 状态 [非空]   0:等待 1:成功 2:失败 3:取消     **/
		public final static @type(Short.class)  String csarStatus="csarStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csarId="csar_id";
		/** 运营地区 [非空] [SrvHost]      **/
		public final static String csarHost="csar_host";
		/** 接口类型 [非空]   0:支付宝 10:招商银行 21:支付宝转帐 22:银行卡转帐 23:邮局汇款 24:现金退款     **/
		public final static String csarBankType="csar_bank_type";
		/** 退款批次号 [非空]       **/
		public final static String csarTradeNo="csar_trade_no";
		/** 退款会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static String csarMember="csar_member";
		/** 会员姓名 [非空]       **/
		public final static String csarRealName="csar_real_name";
		/** 退款金额 [非空]       **/
		public final static String csarAmount="csar_amount";
		/** 关联退款申请 [非空] [CsRefund]      **/
		public final static String csarRefund="csar_refund";
		/** 原接口充值 [非空] [CsAlipayRecord]      **/
		public final static String csarRecord="csar_record";
		/** 备注信息 [非空]       **/
		public final static String csarAlipayNo="csar_alipay_no";
		/** 修改时间 [非空]       **/
		public final static String csarUpdateTime="csar_update_time";
		/** 添加时间 [非空]       **/
		public final static String csarAddTime="csar_add_time";
		/** 状态 [非空]   0:等待 1:成功 2:失败 3:取消     **/
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
				$.Set(name,CsAlipayRefund.getCsAlipayRefund(params));
			else
				$.Set(name,CsAlipayRefund.getCsAlipayRefundList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取接口退款数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsAlipayRefund) $.GetRequest("CsAlipayRefund$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsAlipayRefund.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsAlipayRefund.getCsAlipayRefund(params);
			else
				value = CsAlipayRefund.getCsAlipayRefundList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsAlipayRefund.Get($.add(CsAlipayRefund.F.csarId,param));
		else if(!$.empty(param.toString()))
			value = CsAlipayRefund.get(Long.valueOf(param.toString()));
		$.SetRequest("CsAlipayRefund$"+param.hashCode(), value);
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
	public void mergeSet(CsAlipayRefund old){
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