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

@namespace("finance/refund")
public @caption("会员退款") @table("cs_refund") class CsRefund implements java.io.Serializable
{
	private static final long serialVersionUID = 1l;
	private @caption("编号") @column("csr_id")   @primary  @note("  ") Long csrId;// 主键 非空     
	private @caption("运营地区") @column("csr_host")    @relate("$csrHost") @RelateClass(SrvHost.class)  @note("  ") Long csrHost;// 非空     
 	private SrvHost $csrHost;//关联对象[运营城市]
	private @caption("退款会员") @column("csr_member")    @relate("$csrMember") @RelateClass(CsMember.class)  @note("  ") Long csrMember;// 非空    ${basePath}${proname}/user/member_inquire.do 
 	private CsMember $csrMember;//关联对象[会员帐号]
	private @caption("已退金额") @column("csr_amount")    @note("  ") Double csrAmount;// 非空     
	private @caption("退款方式") @column("csr_return_type")    @note(" 0:接口退款 1:支付宝转帐 2:银行卡转帐 3:邮局汇款 4:上门取现  ") Short csrReturnType;// 0:接口退款 1:支付宝转帐 2:银行卡转帐 3:邮局汇款 4:上门取现     
	private @caption("收款帐号") @column("csr_account_no")    @note("  ") String csrAccountNo;// 非空     
	private @caption("收款姓名") @column("csr_account_name")    @note("  ") String csrAccountName;// 非空     
	private @caption("手机号码") @column("csr_mobile")    @note("  ") String csrMobile;// 非空     
	//private @caption("退款明细") @RelateClass(CsAlipayRefund.class) List<CsAlipayRefund> refunds;//反向关联的接口退款列表
	private @caption("审核人员") @column("csr_checker")    @relate("$csrChecker") @RelateClass(SrvUser.class)  @note("  ") Long csrChecker;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csrChecker;//关联对象[用户]
	private @caption("退款人员") @column("csr_backer")    @relate("$csrBacker") @RelateClass(SrvUser.class)  @note("  ") Long csrBacker;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csrBacker;//关联对象[用户]
	private @caption("退款备录") @column("csr_memo")    @note("  ") String csrMemo;//     
	private @caption("添加时间") @column("csr_add_time")    @note("  ") Date csrAddTime;// 非空     
	private @caption("审核时间") @column("csr_check_time")    @note("  没有审核时间则设置为预计时间") Date csrCheckTime;// 非空  没有审核时间则设置为预计时间   
	private @caption("退款时间") @column("csr_back_time")    @note("  没有退款时间则设置为预计时间") Date csrBackTime;// 非空  没有退款时间则设置为预计时间   
	private @caption("完成时间") @column("csr_end_time")    @note("  没有完成时间则设置为预计时间") Date csrEndTime;// 非空  没有完成时间则设置为预计时间   
	private @caption("备注信息") @column("csr_remark")    @note("  ") String csrRemark;//     
	private @caption("状态") @column("csr_status")    @note(" 0:等待审核 1:等待退款 2:退款完成 3:退款失败 4:退款取消  ") Short csrStatus;// 非空 0:等待审核 1:等待退款 2:退款完成 3:退款失败 4:退款取消     
	
	//默认构造函数
	public CsRefund(){
	
	}
	
	//主键构造函数
	public CsRefund(Long id){
		this.csrId = id;
	}
	
	/**所有字段构造函数 CsRefund(csrHost,csrMember,csrAmount,csrReturnType,csrAccountNo,csrAccountName,csrMobile,csrChecker,csrBacker,csrMemo,csrAddTime,csrCheckTime,csrBackTime,csrEndTime,csrRemark,csrStatus)
	 CsRefund(
	 	$.getLong("csrHost")//运营地区 [非空]
	 	,$.getLong("csrMember")//退款会员 [非空]
	 	,$.getDouble("csrAmount")//已退金额 [非空]
	 	,$.getShort("csrReturnType")//退款方式
	 	,$.getString("csrAccountNo")//收款帐号 [非空]
	 	,$.getString("csrAccountName")//收款姓名 [非空]
	 	,$.getString("csrMobile")//手机号码 [非空]
	 	,$.getLong("csrChecker")//审核人员
	 	,$.getLong("csrBacker")//退款人员
	 	,$.getString("csrMemo")//退款备录
	 	,$.getDate("csrAddTime")//添加时间 [非空]
	 	,$.getDate("csrCheckTime")//审核时间 [非空]
	 	,$.getDate("csrBackTime")//退款时间 [非空]
	 	,$.getDate("csrEndTime")//完成时间 [非空]
	 	,$.getString("csrRemark")//备注信息
	 	,$.getShort("csrStatus")//状态 [非空]
	 )
	**/
	public CsRefund(Long csrHost,Long csrMember,Double csrAmount,Short csrReturnType,String csrAccountNo,String csrAccountName,String csrMobile,Long csrChecker,Long csrBacker,String csrMemo,Date csrAddTime,Date csrCheckTime,Date csrBackTime,Date csrEndTime,String csrRemark,Short csrStatus){
		this.csrHost=csrHost;
		this.csrMember=csrMember;
		this.csrAmount=csrAmount;
		this.csrReturnType=csrReturnType;
		this.csrAccountNo=csrAccountNo;
		this.csrAccountName=csrAccountName;
		this.csrMobile=csrMobile;
		this.csrChecker=csrChecker;
		this.csrBacker=csrBacker;
		this.csrMemo=csrMemo;
		this.csrAddTime=csrAddTime;
		this.csrCheckTime=csrCheckTime;
		this.csrBackTime=csrBackTime;
		this.csrEndTime=csrEndTime;
		this.csrRemark=csrRemark;
		this.csrStatus=csrStatus;
	}
	
	//设置非空字段
	public CsRefund setNotNull(Long csrHost,Long csrMember,Double csrAmount,String csrAccountNo,String csrAccountName,String csrMobile,Date csrAddTime,Date csrCheckTime,Date csrBackTime,Date csrEndTime,Short csrStatus){
		this.csrHost=csrHost;
		this.csrMember=csrMember;
		this.csrAmount=csrAmount;
		this.csrAccountNo=csrAccountNo;
		this.csrAccountName=csrAccountName;
		this.csrMobile=csrMobile;
		this.csrAddTime=csrAddTime;
		this.csrCheckTime=csrCheckTime;
		this.csrBackTime=csrBackTime;
		this.csrEndTime=csrEndTime;
		this.csrStatus=csrStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsRefund csrId(Long csrId){
		this.csrId = csrId;
		this.setSeted(F.csrId);
		return this;
	}
	/** 运营地区 [非空] [SrvHost]      **/
	public CsRefund csrHost(Long csrHost){
		this.csrHost = csrHost;
		this.setSeted(F.csrHost);
		return this;
	}
	/** 退款会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
	public CsRefund csrMember(Long csrMember){
		this.csrMember = csrMember;
		this.setSeted(F.csrMember);
		return this;
	}
	/** 已退金额 [非空]       **/
	public CsRefund csrAmount(Double csrAmount){
		this.csrAmount = csrAmount;
		this.setSeted(F.csrAmount);
		return this;
	}
	/** 退款方式    0:接口退款 1:支付宝转帐 2:银行卡转帐 3:邮局汇款 4:上门取现     **/
	public CsRefund csrReturnType(Short csrReturnType){
		this.csrReturnType = csrReturnType;
		this.setSeted(F.csrReturnType);
		return this;
	}
	/** 收款帐号 [非空]       **/
	public CsRefund csrAccountNo(String csrAccountNo){
		this.csrAccountNo = csrAccountNo;
		this.setSeted(F.csrAccountNo);
		return this;
	}
	/** 收款姓名 [非空]       **/
	public CsRefund csrAccountName(String csrAccountName){
		this.csrAccountName = csrAccountName;
		this.setSeted(F.csrAccountName);
		return this;
	}
	/** 手机号码 [非空]       **/
	public CsRefund csrMobile(String csrMobile){
		this.csrMobile = csrMobile;
		this.setSeted(F.csrMobile);
		return this;
	}
	/** 审核人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsRefund csrChecker(Long csrChecker){
		this.csrChecker = csrChecker;
		this.setSeted(F.csrChecker);
		return this;
	}
	/** 退款人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsRefund csrBacker(Long csrBacker){
		this.csrBacker = csrBacker;
		this.setSeted(F.csrBacker);
		return this;
	}
	/** 退款备录        **/
	public CsRefund csrMemo(String csrMemo){
		this.csrMemo = csrMemo;
		this.setSeted(F.csrMemo);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsRefund csrAddTime(Date csrAddTime){
		this.csrAddTime = csrAddTime;
		this.setSeted(F.csrAddTime);
		return this;
	}
	/** 审核时间 [非空]    没有审核时间则设置为预计时间   **/
	public CsRefund csrCheckTime(Date csrCheckTime){
		this.csrCheckTime = csrCheckTime;
		this.setSeted(F.csrCheckTime);
		return this;
	}
	/** 退款时间 [非空]    没有退款时间则设置为预计时间   **/
	public CsRefund csrBackTime(Date csrBackTime){
		this.csrBackTime = csrBackTime;
		this.setSeted(F.csrBackTime);
		return this;
	}
	/** 完成时间 [非空]    没有完成时间则设置为预计时间   **/
	public CsRefund csrEndTime(Date csrEndTime){
		this.csrEndTime = csrEndTime;
		this.setSeted(F.csrEndTime);
		return this;
	}
	/** 备注信息        **/
	public CsRefund csrRemark(String csrRemark){
		this.csrRemark = csrRemark;
		this.setSeted(F.csrRemark);
		return this;
	}
	/** 状态 [非空]   0:等待审核 1:等待退款 2:退款完成 3:退款失败 4:退款取消     **/
	public CsRefund csrStatus(Short csrStatus){
		this.csrStatus = csrStatus;
		this.setSeted(F.csrStatus);
		return this;
	}
	
	
	//克隆对象
	public CsRefund clone(){
		CsRefund clone = new CsRefund();
		clone.csrHost=this.csrHost;
		clone.csrMember=this.csrMember;
		clone.csrAmount=this.csrAmount;
		clone.csrAccountNo=this.csrAccountNo;
		clone.csrAccountName=this.csrAccountName;
		clone.csrMobile=this.csrMobile;
		clone.csrAddTime=this.csrAddTime;
		clone.csrCheckTime=this.csrCheckTime;
		clone.csrBackTime=this.csrBackTime;
		clone.csrEndTime=this.csrEndTime;
		clone.csrStatus=this.csrStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取会员退款
	 * @param id
	 * @return
	 */
	public static @api CsRefund get(Long id){		
		return getCsRefundById(id);
	}
	/**
	 * 获取所有会员退款
	 * @return
	 */
	public static @api List<CsRefund> list(Map params,Integer size){
		return getCsRefundList(params,size);
	}
	/**
	 * 获取会员退款分页
	 * @return
	 */
	public static @api Page<CsRefund> page(int page,int size,Map params){
		return getCsRefundPage(page, size , params);
	}
	/**
	 * 根据查询条件取会员退款
	 * @param params
	 * @return
	 */
	public static @api CsRefund Get(Map params){
		return getCsRefund(params);
	}
	/**
	 * 获取会员退款数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsRefundCount(params);
	}
	/**
	 * 获取会员退款数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsRefundEval(eval,params);
	}
	
	/**
	 * 根据ID取会员退款
	 * @param id
	 * @return
	 */
	public static @api CsRefund getCsRefundById(Long id){		
		CsRefund csRefund = (CsRefund) $.GetRequest("CsRefund$"+id);
		if(csRefund!=null)
			return csRefund;
		com.ccclubs.service.admin.ICsRefundService csRefundService = $.GetSpringBean("csRefundService");		
		return csRefundService.getCsRefundById(id);
	}
	
	
	/**
	 * 根据ID取会员退款的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsRefund.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsRefund csRefund = get(id);
		if(csRefund!=null){
			String strValue = csRefund.getCsrId$();
			if(!"CsrId".equals("CsrId"))
				strValue+="("+csRefund.getCsrId$()+")";
			MemCache.setValue(CsRefund.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsrId$();
		if(!"CsrId".equals("CsrId"))
			keyValue+="("+this.getCsrId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有会员退款
	 * @return
	 */
	public static @api List<CsRefund> getCsRefundList(Map params,Integer size){
		com.ccclubs.service.admin.ICsRefundService csRefundService = $.GetSpringBean("csRefundService");
		return csRefundService.getCsRefundList(params, size);
	}
	
	/**
	 * 获取会员退款分页
	 * @return
	 */
	public static @api Page<CsRefund> getCsRefundPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsRefundService csRefundService = $.GetSpringBean("csRefundService");
		return csRefundService.getCsRefundPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取会员退款
	 * @param params
	 * @return
	 */
	public static @api CsRefund getCsRefund(Map params){
		com.ccclubs.service.admin.ICsRefundService csRefundService = $.GetSpringBean("csRefundService");
		return csRefundService.getCsRefund(params);
	}
	
	/**
	 * 获取会员退款数
	 * @return
	 */
	public static @api Long getCsRefundCount(Map params){
		com.ccclubs.service.admin.ICsRefundService csRefundService = $.GetSpringBean("csRefundService");
		return csRefundService.getCsRefundCount(params);
	}
		
		
	/**
	 * 获取会员退款自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsRefundEval(String eval,Map params){
		com.ccclubs.service.admin.ICsRefundService csRefundService = $.GetSpringBean("csRefundService");
		return csRefundService.getCsRefundEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsRefund(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsRefundService csRefundService = $.GetSpringBean("csRefundService");
		csRefundService.updateCsRefundByConfirm(set, where);
	}
	
	
	/**
	 * 保存会员退款对象
	 * @param params
	 * @return
	 */
	public CsRefund save(){
		com.ccclubs.service.admin.ICsRefundService csRefundService = $.GetSpringBean("csRefundService");
		if(this.getCsrId()!=null)
			csRefundService.updateCsRefund(this);
		else
			return csRefundService.saveCsRefund(this);
		return this;
	}
	
	
	/**
	 * 更新会员退款对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsRefundService csRefundService = $.GetSpringBean("csRefundService");
		csRefundService.updateCsRefund$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsRefundService csRefundService = $.GetSpringBean("csRefundService");
		if($.equals($.config("logic_delete"),"true"))
			csRefundService.removeCsRefundById(this.getCsrId());
		else
			csRefundService.deleteCsRefundById(this.getCsrId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsRefundService csRefundService = $.GetSpringBean("csRefundService");
		return csRefundService.executeTransaction(function);
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
	/*******************************运营地区**********************************/	
	/**
	* 运营地区 [非空] [SrvHost]     
	**/
	public Long getCsrHost(){
		return this.csrHost;
	}
	/**
	* 获取运营地区格式化(toString)
	**/
	public String getCsrHost$(){
		String strValue="";
		if(this.getCsrHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsrHost()));
		}			
	 	 return strValue;
	}
	/**
	* 运营地区 [非空] [SrvHost]     
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
	/*******************************退款会员**********************************/	
	/**
	* 退款会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public Long getCsrMember(){
		return this.csrMember;
	}
	/**
	* 获取退款会员格式化(toString)
	**/
	public String getCsrMember$(){
		String strValue="";
		if(this.getCsrMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsrMember()));
		}			
	 	 return strValue;
	}
	/**
	* 退款会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public void setCsrMember(Long csrMember){
		this.csrMember = csrMember;
		this.setSeted(F.csrMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csrMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsrMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsrMember()!=null){
 			csMember = CsMember.get(this.getCsrMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsrMember(), csMember);
	 	return csMember;
	}
	/*******************************已退金额**********************************/	
	/**
	* 已退金额 [非空]      
	**/
	public Double getCsrAmount(){
		return this.csrAmount;
	}
	/**
	* 获取已退金额格式化(toString)
	**/
	public String getCsrAmount$(){
		String strValue="";
		 strValue=$.str(this.getCsrAmount());	
	 	 return strValue;
	}
	/**
	* 已退金额 [非空]      
	**/
	public void setCsrAmount(Double csrAmount){
		this.csrAmount = csrAmount;
		this.setSeted(F.csrAmount);
	}
	/*******************************退款方式**********************************/	
	/**
	* 退款方式    0:接口退款 1:支付宝转帐 2:银行卡转帐 3:邮局汇款 4:上门取现    
	**/
	public Short getCsrReturnType(){
		return this.csrReturnType;
	}
	/**
	* 获取退款方式格式化(toString)
	**/
	public String getCsrReturnType$(){
		String strValue="";
		 if($.equals($.str(this.getCsrReturnType()),"0"))
			strValue=$.str("接口退款");		 
		 if($.equals($.str(this.getCsrReturnType()),"1"))
			strValue=$.str("支付宝转帐");		 
		 if($.equals($.str(this.getCsrReturnType()),"2"))
			strValue=$.str("银行卡转帐");		 
		 if($.equals($.str(this.getCsrReturnType()),"3"))
			strValue=$.str("邮局汇款");		 
		 if($.equals($.str(this.getCsrReturnType()),"4"))
			strValue=$.str("上门取现");		 
	 	 return strValue;
	}
	/**
	* 退款方式    0:接口退款 1:支付宝转帐 2:银行卡转帐 3:邮局汇款 4:上门取现    
	**/
	public void setCsrReturnType(Short csrReturnType){
		this.csrReturnType = csrReturnType;
		this.setSeted(F.csrReturnType);
	}
	/*******************************收款帐号**********************************/	
	/**
	* 收款帐号 [非空]      
	**/
	public String getCsrAccountNo(){
		return this.csrAccountNo;
	}
	/**
	* 获取收款帐号格式化(toString)
	**/
	public String getCsrAccountNo$(){
		String strValue="";
		 strValue=$.str(this.getCsrAccountNo());
	 	 return strValue;
	}
	/**
	* 收款帐号 [非空]      
	**/
	public void setCsrAccountNo(String csrAccountNo){
		this.csrAccountNo = csrAccountNo;
		this.setSeted(F.csrAccountNo);
	}
	/*******************************收款姓名**********************************/	
	/**
	* 收款姓名 [非空]      
	**/
	public String getCsrAccountName(){
		return this.csrAccountName;
	}
	/**
	* 获取收款姓名格式化(toString)
	**/
	public String getCsrAccountName$(){
		String strValue="";
		 strValue=$.str(this.getCsrAccountName());
	 	 return strValue;
	}
	/**
	* 收款姓名 [非空]      
	**/
	public void setCsrAccountName(String csrAccountName){
		this.csrAccountName = csrAccountName;
		this.setSeted(F.csrAccountName);
	}
	/*******************************手机号码**********************************/	
	/**
	* 手机号码 [非空]      
	**/
	public String getCsrMobile(){
		return this.csrMobile;
	}
	/**
	* 获取手机号码格式化(toString)
	**/
	public String getCsrMobile$(){
		String strValue="";
		 strValue=$.str(this.getCsrMobile());
	 	 return strValue;
	}
	/**
	* 手机号码 [非空]      
	**/
	public void setCsrMobile(String csrMobile){
		this.csrMobile = csrMobile;
		this.setSeted(F.csrMobile);
	}
	/*******************************反向关联的接口退款列表**********************************/	
	/**
	* 获取与当前对象反向关联的接口退款列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsAlipayRefund> getRefunds(){
		com.ccclubs.service.admin.ICsAlipayRefundService csAlipayRefundService = $.GetSpringBean("csAlipayRefundService");
		Map params = new HashMap();
		params.put("csarRefund",this.getCsrId());
		return csAlipayRefundService.getCsAlipayRefundList(params,-1);
	}
	/**
	* 获取退款明细格式化(toString)
	**/
	public String getRefunds$(){
		return "[...]";
	}
	/*******************************审核人员**********************************/	
	/**
	* 审核人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsrChecker(){
		return this.csrChecker;
	}
	/**
	* 获取审核人员格式化(toString)
	**/
	public String getCsrChecker$(){
		String strValue="";
		if(this.getCsrChecker()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsrChecker()));
		}			
	 	 return strValue;
	}
	/**
	* 审核人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsrChecker(Long csrChecker){
		this.csrChecker = csrChecker;
		this.setSeted(F.csrChecker);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csrChecker(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsrChecker());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsrChecker()!=null){
 			srvUser = SrvUser.get(this.getCsrChecker());
 		}
 		$.SetRequest("SrvUser$"+this.getCsrChecker(), srvUser);
	 	return srvUser;
	}
	/*******************************退款人员**********************************/	
	/**
	* 退款人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsrBacker(){
		return this.csrBacker;
	}
	/**
	* 获取退款人员格式化(toString)
	**/
	public String getCsrBacker$(){
		String strValue="";
		if(this.getCsrBacker()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsrBacker()));
		}			
	 	 return strValue;
	}
	/**
	* 退款人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsrBacker(Long csrBacker){
		this.csrBacker = csrBacker;
		this.setSeted(F.csrBacker);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csrBacker(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsrBacker());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsrBacker()!=null){
 			srvUser = SrvUser.get(this.getCsrBacker());
 		}
 		$.SetRequest("SrvUser$"+this.getCsrBacker(), srvUser);
	 	return srvUser;
	}
	/*******************************退款备录**********************************/	
	/**
	* 退款备录       
	**/
	public String getCsrMemo(){
		return this.csrMemo;
	}
	/**
	* 获取退款备录格式化(toString)
	**/
	public String getCsrMemo$(){
		String strValue="";
		 strValue=$.str(this.getCsrMemo());
	 	 return strValue;
	}
	/**
	* 退款备录       
	**/
	public void setCsrMemo(String csrMemo){
		this.csrMemo = csrMemo;
		this.setSeted(F.csrMemo);
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
	/*******************************审核时间**********************************/	
	/**
	* 审核时间 [非空]    没有审核时间则设置为预计时间  
	**/
	public Date getCsrCheckTime(){
		return this.csrCheckTime;
	}
	/**
	* 获取审核时间格式化(toString)
	**/
	public String getCsrCheckTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsrCheckTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 审核时间 [非空]    没有审核时间则设置为预计时间  
	**/
	public void setCsrCheckTime(Date csrCheckTime){
		this.csrCheckTime = csrCheckTime;
		this.setSeted(F.csrCheckTime);
	}
	/*******************************退款时间**********************************/	
	/**
	* 退款时间 [非空]    没有退款时间则设置为预计时间  
	**/
	public Date getCsrBackTime(){
		return this.csrBackTime;
	}
	/**
	* 获取退款时间格式化(toString)
	**/
	public String getCsrBackTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsrBackTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 退款时间 [非空]    没有退款时间则设置为预计时间  
	**/
	public void setCsrBackTime(Date csrBackTime){
		this.csrBackTime = csrBackTime;
		this.setSeted(F.csrBackTime);
	}
	/*******************************完成时间**********************************/	
	/**
	* 完成时间 [非空]    没有完成时间则设置为预计时间  
	**/
	public Date getCsrEndTime(){
		return this.csrEndTime;
	}
	/**
	* 获取完成时间格式化(toString)
	**/
	public String getCsrEndTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsrEndTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 完成时间 [非空]    没有完成时间则设置为预计时间  
	**/
	public void setCsrEndTime(Date csrEndTime){
		this.csrEndTime = csrEndTime;
		this.setSeted(F.csrEndTime);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息       
	**/
	public String getCsrRemark(){
		return this.csrRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCsrRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsrRemark());
	 	 return strValue;
	}
	/**
	* 备注信息       
	**/
	public void setCsrRemark(String csrRemark){
		this.csrRemark = csrRemark;
		this.setSeted(F.csrRemark);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:等待审核 1:等待退款 2:退款完成 3:退款失败 4:退款取消    
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
			strValue=$.str("等待审核");		 
		 if($.equals($.str(this.getCsrStatus()),"1"))
			strValue=$.str("等待退款");		 
		 if($.equals($.str(this.getCsrStatus()),"2"))
			strValue=$.str("退款完成");		 
		 if($.equals($.str(this.getCsrStatus()),"3"))
			strValue=$.str("退款失败");		 
		 if($.equals($.str(this.getCsrStatus()),"4"))
			strValue=$.str("退款取消");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:等待审核 1:等待退款 2:退款完成 3:退款失败 4:退款取消    
	**/
	public void setCsrStatus(Short csrStatus){
		this.csrStatus = csrStatus;
		this.setSeted(F.csrStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsRefund.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRefund.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRefund.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsRefund.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsRefund.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRefund.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRefund.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsRefund.this);
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
		/** 运营地区 [非空] [SrvHost]      **/
		public M csrHost(Object csrHost){this.put("csrHost", csrHost);return this;};
	 	/** and csr_host is null */
 		public M csrHostNull(){if(this.get("csrHostNot")==null)this.put("csrHostNot", "");this.put("csrHost", null);return this;};
 		/** not .... */
 		public M csrHostNot(){this.put("csrHostNot", "not");return this;};
		/** 退款会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public M csrMember(Object csrMember){this.put("csrMember", csrMember);return this;};
	 	/** and csr_member is null */
 		public M csrMemberNull(){if(this.get("csrMemberNot")==null)this.put("csrMemberNot", "");this.put("csrMember", null);return this;};
 		/** not .... */
 		public M csrMemberNot(){this.put("csrMemberNot", "not");return this;};
		public M csrMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csrMember$on", value);
			return this;
		};	
		/** 已退金额 [非空]       **/
		public M csrAmount(Object csrAmount){this.put("csrAmount", csrAmount);return this;};
	 	/** and csr_amount is null */
 		public M csrAmountNull(){if(this.get("csrAmountNot")==null)this.put("csrAmountNot", "");this.put("csrAmount", null);return this;};
 		/** not .... */
 		public M csrAmountNot(){this.put("csrAmountNot", "not");return this;};
		/** and csr_amount >= ? */
		public M csrAmountMin(Object min){this.put("csrAmountMin", min);return this;};
		/** and csr_amount <= ? */
		public M csrAmountMax(Object max){this.put("csrAmountMax", max);return this;};
		/** 退款方式    0:接口退款 1:支付宝转帐 2:银行卡转帐 3:邮局汇款 4:上门取现     **/
		public M csrReturnType(Object csrReturnType){this.put("csrReturnType", csrReturnType);return this;};
	 	/** and csr_return_type is null */
 		public M csrReturnTypeNull(){if(this.get("csrReturnTypeNot")==null)this.put("csrReturnTypeNot", "");this.put("csrReturnType", null);return this;};
 		/** not .... */
 		public M csrReturnTypeNot(){this.put("csrReturnTypeNot", "not");return this;};
		/** 收款帐号 [非空]       **/
		public M csrAccountNo(Object csrAccountNo){this.put("csrAccountNo", csrAccountNo);return this;};
	 	/** and csr_account_no is null */
 		public M csrAccountNoNull(){if(this.get("csrAccountNoNot")==null)this.put("csrAccountNoNot", "");this.put("csrAccountNo", null);return this;};
 		/** not .... */
 		public M csrAccountNoNot(){this.put("csrAccountNoNot", "not");return this;};
		/** 收款姓名 [非空]       **/
		public M csrAccountName(Object csrAccountName){this.put("csrAccountName", csrAccountName);return this;};
	 	/** and csr_account_name is null */
 		public M csrAccountNameNull(){if(this.get("csrAccountNameNot")==null)this.put("csrAccountNameNot", "");this.put("csrAccountName", null);return this;};
 		/** not .... */
 		public M csrAccountNameNot(){this.put("csrAccountNameNot", "not");return this;};
		/** 手机号码 [非空]       **/
		public M csrMobile(Object csrMobile){this.put("csrMobile", csrMobile);return this;};
	 	/** and csr_mobile is null */
 		public M csrMobileNull(){if(this.get("csrMobileNot")==null)this.put("csrMobileNot", "");this.put("csrMobile", null);return this;};
 		/** not .... */
 		public M csrMobileNot(){this.put("csrMobileNot", "not");return this;};
		/** 审核人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csrChecker(Object csrChecker){this.put("csrChecker", csrChecker);return this;};
	 	/** and csr_checker is null */
 		public M csrCheckerNull(){if(this.get("csrCheckerNot")==null)this.put("csrCheckerNot", "");this.put("csrChecker", null);return this;};
 		/** not .... */
 		public M csrCheckerNot(){this.put("csrCheckerNot", "not");return this;};
		/** 退款人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csrBacker(Object csrBacker){this.put("csrBacker", csrBacker);return this;};
	 	/** and csr_backer is null */
 		public M csrBackerNull(){if(this.get("csrBackerNot")==null)this.put("csrBackerNot", "");this.put("csrBacker", null);return this;};
 		/** not .... */
 		public M csrBackerNot(){this.put("csrBackerNot", "not");return this;};
		/** 退款备录        **/
		public M csrMemo(Object csrMemo){this.put("csrMemo", csrMemo);return this;};
	 	/** and csr_memo is null */
 		public M csrMemoNull(){if(this.get("csrMemoNot")==null)this.put("csrMemoNot", "");this.put("csrMemo", null);return this;};
 		/** not .... */
 		public M csrMemoNot(){this.put("csrMemoNot", "not");return this;};
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
		/** 审核时间 [非空]    没有审核时间则设置为预计时间   **/
		public M csrCheckTime(Object csrCheckTime){this.put("csrCheckTime", csrCheckTime);return this;};
	 	/** and csr_check_time is null */
 		public M csrCheckTimeNull(){if(this.get("csrCheckTimeNot")==null)this.put("csrCheckTimeNot", "");this.put("csrCheckTime", null);return this;};
 		/** not .... */
 		public M csrCheckTimeNot(){this.put("csrCheckTimeNot", "not");return this;};
 		/** and csr_check_time >= ? */
 		public M csrCheckTimeStart(Object start){this.put("csrCheckTimeStart", start);return this;};			
 		/** and csr_check_time <= ? */
 		public M csrCheckTimeEnd(Object end){this.put("csrCheckTimeEnd", end);return this;};
		/** 退款时间 [非空]    没有退款时间则设置为预计时间   **/
		public M csrBackTime(Object csrBackTime){this.put("csrBackTime", csrBackTime);return this;};
	 	/** and csr_back_time is null */
 		public M csrBackTimeNull(){if(this.get("csrBackTimeNot")==null)this.put("csrBackTimeNot", "");this.put("csrBackTime", null);return this;};
 		/** not .... */
 		public M csrBackTimeNot(){this.put("csrBackTimeNot", "not");return this;};
 		/** and csr_back_time >= ? */
 		public M csrBackTimeStart(Object start){this.put("csrBackTimeStart", start);return this;};			
 		/** and csr_back_time <= ? */
 		public M csrBackTimeEnd(Object end){this.put("csrBackTimeEnd", end);return this;};
		/** 完成时间 [非空]    没有完成时间则设置为预计时间   **/
		public M csrEndTime(Object csrEndTime){this.put("csrEndTime", csrEndTime);return this;};
	 	/** and csr_end_time is null */
 		public M csrEndTimeNull(){if(this.get("csrEndTimeNot")==null)this.put("csrEndTimeNot", "");this.put("csrEndTime", null);return this;};
 		/** not .... */
 		public M csrEndTimeNot(){this.put("csrEndTimeNot", "not");return this;};
 		/** and csr_end_time >= ? */
 		public M csrEndTimeStart(Object start){this.put("csrEndTimeStart", start);return this;};			
 		/** and csr_end_time <= ? */
 		public M csrEndTimeEnd(Object end){this.put("csrEndTimeEnd", end);return this;};
		/** 备注信息        **/
		public M csrRemark(Object csrRemark){this.put("csrRemark", csrRemark);return this;};
	 	/** and csr_remark is null */
 		public M csrRemarkNull(){if(this.get("csrRemarkNot")==null)this.put("csrRemarkNot", "");this.put("csrRemark", null);return this;};
 		/** not .... */
 		public M csrRemarkNot(){this.put("csrRemarkNot", "not");return this;};
		/** 状态 [非空]   0:等待审核 1:等待退款 2:退款完成 3:退款失败 4:退款取消     **/
		public M csrStatus(Object csrStatus){this.put("csrStatus", csrStatus);return this;};
	 	/** and csr_status is null */
 		public M csrStatusNull(){if(this.get("csrStatusNot")==null)this.put("csrStatusNot", "");this.put("csrStatus", null);return this;};
 		/** not .... */
 		public M csrStatusNot(){this.put("csrStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有会员退款 **/
		public @api List<CsRefund> list(Integer size){
			return getCsRefundList(this,size);
		}
		/** 获取会员退款分页 **/
		public @api Page<CsRefund> page(int page,int size){
			return getCsRefundPage(page, size , this);
		}
		/** 根据查询条件取会员退款 **/
		public @api CsRefund get(){
			return getCsRefund(this);
		}
		/** 获取会员退款数 **/
		public @api Long count(){
			return getCsRefundCount(this);
		}
		/** 获取会员退款表达式 **/
		public @api <T> T eval(String strEval){
			return getCsRefundEval(strEval,this);
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
			updateCsRefund(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csrId="csrId";
		/** 运营地区 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csrHost="csrHost";
		/** 退款会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static @type(Long.class)  String csrMember="csrMember";
		/** 已退金额 [非空]       **/
		public final static @type(Double.class)  String csrAmount="csrAmount";
		/** and csr_amount >= ? */
		public final static @type(Double.class) String csrAmountMin="csrAmountMin";
		/** and csr_amount <= ? */
		public final static @type(Double.class) String csrAmountMax="csrAmountMax";
		/** 退款方式    0:接口退款 1:支付宝转帐 2:银行卡转帐 3:邮局汇款 4:上门取现     **/
		public final static @type(Short.class)  String csrReturnType="csrReturnType";
		/** 收款帐号 [非空]       **/
		public final static @type(String.class) @like String csrAccountNo="csrAccountNo";
		/** 收款姓名 [非空]       **/
		public final static @type(String.class) @like String csrAccountName="csrAccountName";
		/** 手机号码 [非空]       **/
		public final static @type(String.class) @like String csrMobile="csrMobile";
		/** 审核人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csrChecker="csrChecker";
		/** 退款人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csrBacker="csrBacker";
		/** 退款备录        **/
		public final static @type(String.class) @like String csrMemo="csrMemo";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csrAddTime="csrAddTime";
	 	/** and csr_add_time >= ? */
 		public final static @type(Date.class) String csrAddTimeStart="csrAddTimeStart";
 		/** and csr_add_time <= ? */
 		public final static @type(Date.class) String csrAddTimeEnd="csrAddTimeEnd";
		/** 审核时间 [非空]    没有审核时间则设置为预计时间   **/
		public final static @type(Date.class)  String csrCheckTime="csrCheckTime";
	 	/** and csr_check_time >= ? */
 		public final static @type(Date.class) String csrCheckTimeStart="csrCheckTimeStart";
 		/** and csr_check_time <= ? */
 		public final static @type(Date.class) String csrCheckTimeEnd="csrCheckTimeEnd";
		/** 退款时间 [非空]    没有退款时间则设置为预计时间   **/
		public final static @type(Date.class)  String csrBackTime="csrBackTime";
	 	/** and csr_back_time >= ? */
 		public final static @type(Date.class) String csrBackTimeStart="csrBackTimeStart";
 		/** and csr_back_time <= ? */
 		public final static @type(Date.class) String csrBackTimeEnd="csrBackTimeEnd";
		/** 完成时间 [非空]    没有完成时间则设置为预计时间   **/
		public final static @type(Date.class)  String csrEndTime="csrEndTime";
	 	/** and csr_end_time >= ? */
 		public final static @type(Date.class) String csrEndTimeStart="csrEndTimeStart";
 		/** and csr_end_time <= ? */
 		public final static @type(Date.class) String csrEndTimeEnd="csrEndTimeEnd";
		/** 备注信息        **/
		public final static @type(String.class) @like String csrRemark="csrRemark";
		/** 状态 [非空]   0:等待审核 1:等待退款 2:退款完成 3:退款失败 4:退款取消     **/
		public final static @type(Short.class)  String csrStatus="csrStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csrId="csr_id";
		/** 运营地区 [非空] [SrvHost]      **/
		public final static String csrHost="csr_host";
		/** 退款会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static String csrMember="csr_member";
		/** 已退金额 [非空]       **/
		public final static String csrAmount="csr_amount";
		/** 退款方式    0:接口退款 1:支付宝转帐 2:银行卡转帐 3:邮局汇款 4:上门取现     **/
		public final static String csrReturnType="csr_return_type";
		/** 收款帐号 [非空]       **/
		public final static String csrAccountNo="csr_account_no";
		/** 收款姓名 [非空]       **/
		public final static String csrAccountName="csr_account_name";
		/** 手机号码 [非空]       **/
		public final static String csrMobile="csr_mobile";
		/** 审核人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csrChecker="csr_checker";
		/** 退款人员  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csrBacker="csr_backer";
		/** 退款备录        **/
		public final static String csrMemo="csr_memo";
		/** 添加时间 [非空]       **/
		public final static String csrAddTime="csr_add_time";
		/** 审核时间 [非空]    没有审核时间则设置为预计时间   **/
		public final static String csrCheckTime="csr_check_time";
		/** 退款时间 [非空]    没有退款时间则设置为预计时间   **/
		public final static String csrBackTime="csr_back_time";
		/** 完成时间 [非空]    没有完成时间则设置为预计时间   **/
		public final static String csrEndTime="csr_end_time";
		/** 备注信息        **/
		public final static String csrRemark="csr_remark";
		/** 状态 [非空]   0:等待审核 1:等待退款 2:退款完成 3:退款失败 4:退款取消     **/
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
				$.Set(name,CsRefund.getCsRefund(params));
			else
				$.Set(name,CsRefund.getCsRefundList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取会员退款数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsRefund) $.GetRequest("CsRefund$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsRefund.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsRefund.getCsRefund(params);
			else
				value = CsRefund.getCsRefundList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsRefund.Get($.add(CsRefund.F.csrId,param));
		else if(!$.empty(param.toString()))
			value = CsRefund.get(Long.valueOf(param.toString()));
		$.SetRequest("CsRefund$"+param.hashCode(), value);
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
	public void mergeSet(CsRefund old){
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