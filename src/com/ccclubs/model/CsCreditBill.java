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

@namespace("finance/credit/bill")
public @caption("信用账单") @table("cs_credit_bill") class CsCreditBill implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cscb_id")   @primary  @note("  ") Long cscbId;// 主键 非空     
	private @caption("城市") @column("cscb_host")    @relate("$cscbHost") @RelateClass(SrvHost.class)  @note("  ") Long cscbHost;// 非空     
 	private SrvHost $cscbHost;//关联对象[运营城市]
	private @caption("账单描述") @column("cscb_title")    @note("  ") String cscbTitle;// 非空     
	private @caption("所属会员") @column("cscb_member")    @relate("$cscbMember") @RelateClass(CsMember.class)  @note("  ") Long cscbMember;// 非空     
 	private CsMember $cscbMember;//关联对象[会员帐号]
	private @caption("所属信用卡") @column("cscb_credit_card")    @relate("$cscbCreditCard") @RelateClass(CsCreditCard.class)  @note("  ") Long cscbCreditCard;// 非空     
 	private CsCreditCard $cscbCreditCard;//关联对象[信用卡]
	private @caption("关联订单") @column("cscb_order")    @relate("$cscbOrder") @RelateClass(CsOrder.class)  @note("  ") Long cscbOrder;//     
 	private CsOrder $cscbOrder;//关联对象[系统订单]
	private @caption("关联违章") @column("cscb_violat")    @relate("$cscbViolat") @RelateClass(CsViolat.class)  @note("  ") Long cscbViolat;//     
 	private CsViolat $cscbViolat;//关联对象[车辆违章]
	private @caption("关联事故") @column("cscb_trouble")    @relate("$cscbTrouble") @RelateClass(CsTrouble.class)  @note("  ") Long cscbTrouble;//     
 	private CsTrouble $cscbTrouble;//关联对象[车辆事故]
	private @caption("总欠账额") @column("cscb_value")    @note("  ") Double cscbValue;// 非空     
	private @caption("已付额") @column("cscb_payment")    @note("  ") Double cscbPayment;// 非空     
	private @caption("还款时间") @column("cscb_repay_time")    @note("  ") Date cscbRepayTime;//     
	private @caption("修改时间") @column("cscb_update_time")    @note("  ") Date cscbUpdateTime;// 非空     
	private @caption("添加时间") @column("cscb_add_time")    @note("  ") Date cscbAddTime;// 非空     
	private @caption("操作人员") @column("cscb_editor")    @relate("$cscbEditor") @RelateClass(SrvUser.class)  @note("  ") Long cscbEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cscbEditor;//关联对象[用户]
	private @caption("备注信息") @column("cscb_remark")    @note("  ") String cscbRemark;//     
	private @caption("跟踪信息") @column("cscb_process")    @note(" 1:催缴短信已发 2:催缴电话已打  ") String cscbProcess;// 1:催缴短信已发 2:催缴电话已打     
	private @caption("还款交易号") @column("cscb_pay_serial")    @note("  三方状态同步返回的交易号") String cscbPaySerial;//  三方状态同步返回的交易号   
	private @caption("同步状态") @column("cscb_state")    @note(" 0:未同步 1:已同步 2:不同步 3:待撤单 4:已撤单  描述此帐单是否已同步到第三方") Short cscbState;// 0:未同步 1:已同步 2:不同步 3:待撤单 4:已撤单  描述此帐单是否已同步到第三方   
	private @caption("状态") @column("cscb_status")    @note(" 0:待还款 1:已还款 2:已关闭 3:已代付 4:已坏帐  ") Short cscbStatus;// 非空 0:待还款 1:已还款 2:已关闭 3:已代付 4:已坏帐     
	
	//默认构造函数
	public CsCreditBill(){
	
	}
	
	//主键构造函数
	public CsCreditBill(Long id){
		this.cscbId = id;
	}
	
	/**所有字段构造函数 CsCreditBill(cscbHost,cscbTitle,cscbMember,cscbCreditCard,cscbOrder,cscbViolat,cscbTrouble,cscbValue,cscbPayment,cscbRepayTime,cscbUpdateTime,cscbAddTime,cscbEditor,cscbRemark,cscbProcess,cscbPaySerial,cscbState,cscbStatus)
	 CsCreditBill(
	 	$.getLong("cscbHost")//城市 [非空]
	 	,$.getString("cscbTitle")//账单描述 [非空]
	 	,$.getLong("cscbMember")//所属会员 [非空]
	 	,$.getLong("cscbCreditCard")//所属信用卡 [非空]
	 	,$.getLong("cscbOrder")//关联订单
	 	,$.getLong("cscbViolat")//关联违章
	 	,$.getLong("cscbTrouble")//关联事故
	 	,$.getDouble("cscbValue")//总欠账额 [非空]
	 	,$.getDouble("cscbPayment")//已付额 [非空]
	 	,$.getDate("cscbRepayTime")//还款时间
	 	,$.getDate("cscbUpdateTime")//修改时间 [非空]
	 	,$.getDate("cscbAddTime")//添加时间 [非空]
	 	,$.getLong("cscbEditor")//操作人员 [非空]
	 	,$.getString("cscbRemark")//备注信息
	 	,$.getString("cscbProcess")//跟踪信息
	 	,$.getString("cscbPaySerial")//还款交易号
	 	,$.getShort("cscbState")//同步状态
	 	,$.getShort("cscbStatus")//状态 [非空]
	 )
	**/
	public CsCreditBill(Long cscbHost,String cscbTitle,Long cscbMember,Long cscbCreditCard,Long cscbOrder,Long cscbViolat,Long cscbTrouble,Double cscbValue,Double cscbPayment,Date cscbRepayTime,Date cscbUpdateTime,Date cscbAddTime,Long cscbEditor,String cscbRemark,String cscbProcess,String cscbPaySerial,Short cscbState,Short cscbStatus){
		this.cscbHost=cscbHost;
		this.cscbTitle=cscbTitle;
		this.cscbMember=cscbMember;
		this.cscbCreditCard=cscbCreditCard;
		this.cscbOrder=cscbOrder;
		this.cscbViolat=cscbViolat;
		this.cscbTrouble=cscbTrouble;
		this.cscbValue=cscbValue;
		this.cscbPayment=cscbPayment;
		this.cscbRepayTime=cscbRepayTime;
		this.cscbUpdateTime=cscbUpdateTime;
		this.cscbAddTime=cscbAddTime;
		this.cscbEditor=cscbEditor;
		this.cscbRemark=cscbRemark;
		this.cscbProcess=cscbProcess;
		this.cscbPaySerial=cscbPaySerial;
		this.cscbState=cscbState;
		this.cscbStatus=cscbStatus;
	}
	
	//设置非空字段
	public CsCreditBill setNotNull(Long cscbHost,String cscbTitle,Long cscbMember,Long cscbCreditCard,Double cscbValue,Double cscbPayment,Date cscbUpdateTime,Date cscbAddTime,Long cscbEditor,Short cscbStatus){
		this.cscbHost=cscbHost;
		this.cscbTitle=cscbTitle;
		this.cscbMember=cscbMember;
		this.cscbCreditCard=cscbCreditCard;
		this.cscbValue=cscbValue;
		this.cscbPayment=cscbPayment;
		this.cscbUpdateTime=cscbUpdateTime;
		this.cscbAddTime=cscbAddTime;
		this.cscbEditor=cscbEditor;
		this.cscbStatus=cscbStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsCreditBill cscbId(Long cscbId){
		this.cscbId = cscbId;
		this.setSeted(F.cscbId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsCreditBill cscbHost(Long cscbHost){
		this.cscbHost = cscbHost;
		this.setSeted(F.cscbHost);
		return this;
	}
	/** 账单描述 [非空]       **/
	public CsCreditBill cscbTitle(String cscbTitle){
		this.cscbTitle = cscbTitle;
		this.setSeted(F.cscbTitle);
		return this;
	}
	/** 所属会员 [非空] [CsMember]      **/
	public CsCreditBill cscbMember(Long cscbMember){
		this.cscbMember = cscbMember;
		this.setSeted(F.cscbMember);
		return this;
	}
	/** 所属信用卡 [非空] [CsCreditCard]      **/
	public CsCreditBill cscbCreditCard(Long cscbCreditCard){
		this.cscbCreditCard = cscbCreditCard;
		this.setSeted(F.cscbCreditCard);
		return this;
	}
	/** 关联订单  [CsOrder]      **/
	public CsCreditBill cscbOrder(Long cscbOrder){
		this.cscbOrder = cscbOrder;
		this.setSeted(F.cscbOrder);
		return this;
	}
	/** 关联违章  [CsViolat]      **/
	public CsCreditBill cscbViolat(Long cscbViolat){
		this.cscbViolat = cscbViolat;
		this.setSeted(F.cscbViolat);
		return this;
	}
	/** 关联事故  [CsTrouble]      **/
	public CsCreditBill cscbTrouble(Long cscbTrouble){
		this.cscbTrouble = cscbTrouble;
		this.setSeted(F.cscbTrouble);
		return this;
	}
	/** 总欠账额 [非空]       **/
	public CsCreditBill cscbValue(Double cscbValue){
		this.cscbValue = cscbValue;
		this.setSeted(F.cscbValue);
		return this;
	}
	/** 已付额 [非空]       **/
	public CsCreditBill cscbPayment(Double cscbPayment){
		this.cscbPayment = cscbPayment;
		this.setSeted(F.cscbPayment);
		return this;
	}
	/** 还款时间        **/
	public CsCreditBill cscbRepayTime(Date cscbRepayTime){
		this.cscbRepayTime = cscbRepayTime;
		this.setSeted(F.cscbRepayTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsCreditBill cscbUpdateTime(Date cscbUpdateTime){
		this.cscbUpdateTime = cscbUpdateTime;
		this.setSeted(F.cscbUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsCreditBill cscbAddTime(Date cscbAddTime){
		this.cscbAddTime = cscbAddTime;
		this.setSeted(F.cscbAddTime);
		return this;
	}
	/** 操作人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsCreditBill cscbEditor(Long cscbEditor){
		this.cscbEditor = cscbEditor;
		this.setSeted(F.cscbEditor);
		return this;
	}
	/** 备注信息        **/
	public CsCreditBill cscbRemark(String cscbRemark){
		this.cscbRemark = cscbRemark;
		this.setSeted(F.cscbRemark);
		return this;
	}
	/** 跟踪信息    1:催缴短信已发 2:催缴电话已打     **/
	public CsCreditBill cscbProcess(String cscbProcess){
		this.cscbProcess = cscbProcess;
		this.setSeted(F.cscbProcess);
		return this;
	}
	/** 还款交易号     三方状态同步返回的交易号   **/
	public CsCreditBill cscbPaySerial(String cscbPaySerial){
		this.cscbPaySerial = cscbPaySerial;
		this.setSeted(F.cscbPaySerial);
		return this;
	}
	/** 同步状态    0:未同步 1:已同步 2:不同步 3:待撤单 4:已撤单  描述此帐单是否已同步到第三方   **/
	public CsCreditBill cscbState(Short cscbState){
		this.cscbState = cscbState;
		this.setSeted(F.cscbState);
		return this;
	}
	/** 状态 [非空]   0:待还款 1:已还款 2:已关闭 3:已代付 4:已坏帐     **/
	public CsCreditBill cscbStatus(Short cscbStatus){
		this.cscbStatus = cscbStatus;
		this.setSeted(F.cscbStatus);
		return this;
	}
	
	
	//克隆对象
	public CsCreditBill clone(){
		CsCreditBill clone = new CsCreditBill();
		clone.cscbHost=this.cscbHost;
		clone.cscbTitle=this.cscbTitle;
		clone.cscbMember=this.cscbMember;
		clone.cscbCreditCard=this.cscbCreditCard;
		clone.cscbValue=this.cscbValue;
		clone.cscbPayment=this.cscbPayment;
		clone.cscbUpdateTime=this.cscbUpdateTime;
		clone.cscbAddTime=this.cscbAddTime;
		clone.cscbEditor=this.cscbEditor;
		clone.cscbStatus=this.cscbStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取信用账单
	 * @param id
	 * @return
	 */
	public static @api CsCreditBill get(Long id){		
		return getCsCreditBillById(id);
	}
	/**
	 * 获取所有信用账单
	 * @return
	 */
	public static @api List<CsCreditBill> list(Map params,Integer size){
		return getCsCreditBillList(params,size);
	}
	/**
	 * 获取信用账单分页
	 * @return
	 */
	public static @api Page<CsCreditBill> page(int page,int size,Map params){
		return getCsCreditBillPage(page, size , params);
	}
	/**
	 * 根据查询条件取信用账单
	 * @param params
	 * @return
	 */
	public static @api CsCreditBill Get(Map params){
		return getCsCreditBill(params);
	}
	/**
	 * 获取信用账单数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsCreditBillCount(params);
	}
	/**
	 * 获取信用账单数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsCreditBillEval(eval,params);
	}
	
	/**
	 * 根据ID取信用账单
	 * @param id
	 * @return
	 */
	public static @api CsCreditBill getCsCreditBillById(Long id){		
		CsCreditBill csCreditBill = (CsCreditBill) $.GetRequest("CsCreditBill$"+id);
		if(csCreditBill!=null)
			return csCreditBill;
		com.ccclubs.service.admin.ICsCreditBillService csCreditBillService = $.GetSpringBean("csCreditBillService");		
		return csCreditBillService.getCsCreditBillById(id);
	}
	
	
	/**
	 * 根据ID取信用账单的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsCreditBill.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsCreditBill csCreditBill = get(id);
		if(csCreditBill!=null){
			String strValue = csCreditBill.getCscbId$();
			if(!"CscbId".equals("CscbId"))
				strValue+="("+csCreditBill.getCscbId$()+")";
			if(!"CscbId".equals("CscbStatus"))
				strValue+="("+csCreditBill.getCscbStatus$()+")";
			MemCache.setValue(CsCreditBill.class, id ,strValue);
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
		if(!"CscbId".equals("CscbStatus"))
			keyValue+="("+this.getCscbStatus$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有信用账单
	 * @return
	 */
	public static @api List<CsCreditBill> getCsCreditBillList(Map params,Integer size){
		com.ccclubs.service.admin.ICsCreditBillService csCreditBillService = $.GetSpringBean("csCreditBillService");
		return csCreditBillService.getCsCreditBillList(params, size);
	}
	
	/**
	 * 获取信用账单分页
	 * @return
	 */
	public static @api Page<CsCreditBill> getCsCreditBillPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsCreditBillService csCreditBillService = $.GetSpringBean("csCreditBillService");
		return csCreditBillService.getCsCreditBillPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取信用账单
	 * @param params
	 * @return
	 */
	public static @api CsCreditBill getCsCreditBill(Map params){
		com.ccclubs.service.admin.ICsCreditBillService csCreditBillService = $.GetSpringBean("csCreditBillService");
		return csCreditBillService.getCsCreditBill(params);
	}
	
	/**
	 * 获取信用账单数
	 * @return
	 */
	public static @api Long getCsCreditBillCount(Map params){
		com.ccclubs.service.admin.ICsCreditBillService csCreditBillService = $.GetSpringBean("csCreditBillService");
		return csCreditBillService.getCsCreditBillCount(params);
	}
		
		
	/**
	 * 获取信用账单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsCreditBillEval(String eval,Map params){
		com.ccclubs.service.admin.ICsCreditBillService csCreditBillService = $.GetSpringBean("csCreditBillService");
		return csCreditBillService.getCsCreditBillEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsCreditBill(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsCreditBillService csCreditBillService = $.GetSpringBean("csCreditBillService");
		csCreditBillService.updateCsCreditBillByConfirm(set, where);
	}
	
	
	/**
	 * 保存信用账单对象
	 * @param params
	 * @return
	 */
	public CsCreditBill save(){
		com.ccclubs.service.admin.ICsCreditBillService csCreditBillService = $.GetSpringBean("csCreditBillService");
		if(this.getCscbId()!=null)
			csCreditBillService.updateCsCreditBill(this);
		else
			return csCreditBillService.saveCsCreditBill(this);
		return this;
	}
	
	
	/**
	 * 更新信用账单对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsCreditBillService csCreditBillService = $.GetSpringBean("csCreditBillService");
		csCreditBillService.updateCsCreditBill$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsCreditBillService csCreditBillService = $.GetSpringBean("csCreditBillService");
		if($.equals($.config("logic_delete"),"true"))
			csCreditBillService.removeCsCreditBillById(this.getCscbId());
		else
			csCreditBillService.deleteCsCreditBillById(this.getCscbId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsCreditBillService csCreditBillService = $.GetSpringBean("csCreditBillService");
		return csCreditBillService.executeTransaction(function);
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
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCscbHost(){
		return this.cscbHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCscbHost$(){
		String strValue="";
		if(this.getCscbHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCscbHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCscbHost(Long cscbHost){
		this.cscbHost = cscbHost;
		this.setSeted(F.cscbHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cscbHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCscbHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCscbHost()!=null){
 			srvHost = SrvHost.get(this.getCscbHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCscbHost(), srvHost);
	 	return srvHost;
	}
	/*******************************账单描述**********************************/	
	/**
	* 账单描述 [非空]      
	**/
	public String getCscbTitle(){
		return this.cscbTitle;
	}
	/**
	* 获取账单描述格式化(toString)
	**/
	public String getCscbTitle$(){
		String strValue="";
		 strValue=$.str(this.getCscbTitle());
	 	 return strValue;
	}
	/**
	* 账单描述 [非空]      
	**/
	public void setCscbTitle(String cscbTitle){
		this.cscbTitle = cscbTitle;
		this.setSeted(F.cscbTitle);
	}
	/*******************************所属会员**********************************/	
	/**
	* 所属会员 [非空] [CsMember]     
	**/
	public Long getCscbMember(){
		return this.cscbMember;
	}
	/**
	* 获取所属会员格式化(toString)
	**/
	public String getCscbMember$(){
		String strValue="";
		if(this.getCscbMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCscbMember()));
		}			
	 	 return strValue;
	}
	/**
	* 所属会员 [非空] [CsMember]     
	**/
	public void setCscbMember(Long cscbMember){
		this.cscbMember = cscbMember;
		this.setSeted(F.cscbMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$cscbMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCscbMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCscbMember()!=null){
 			csMember = CsMember.get(this.getCscbMember());
 		}
 		$.SetRequest("CsMember$"+this.getCscbMember(), csMember);
	 	return csMember;
	}
	/*******************************所属信用卡**********************************/	
	/**
	* 所属信用卡 [非空] [CsCreditCard]     
	**/
	public Long getCscbCreditCard(){
		return this.cscbCreditCard;
	}
	/**
	* 获取所属信用卡格式化(toString)
	**/
	public String getCscbCreditCard$(){
		String strValue="";
		if(this.getCscbCreditCard()!=null){
				strValue+=$.str(CsCreditCard.getKeyValue(this.getCscbCreditCard()));
		}			
	 	 return strValue;
	}
	/**
	* 所属信用卡 [非空] [CsCreditCard]     
	**/
	public void setCscbCreditCard(Long cscbCreditCard){
		this.cscbCreditCard = cscbCreditCard;
		this.setSeted(F.cscbCreditCard);
	}
	/**
	* 获取关联对象[信用卡]
	**/	 			
 	public CsCreditCard get$cscbCreditCard(){
 		com.ccclubs.model.CsCreditCard csCreditCard = (com.ccclubs.model.CsCreditCard) $.GetRequest("CsCreditCard$"+this.getCscbCreditCard());
 		if(csCreditCard!=null)
			return csCreditCard;
		if(this.getCscbCreditCard()!=null){
 			csCreditCard = CsCreditCard.get(this.getCscbCreditCard());
 		}
 		$.SetRequest("CsCreditCard$"+this.getCscbCreditCard(), csCreditCard);
	 	return csCreditCard;
	}
	/*******************************关联订单**********************************/	
	/**
	* 关联订单  [CsOrder]     
	**/
	public Long getCscbOrder(){
		return this.cscbOrder;
	}
	/**
	* 获取关联订单格式化(toString)
	**/
	public String getCscbOrder$(){
		String strValue="";
		if(this.getCscbOrder()!=null){
				strValue+=$.str(CsOrder.getKeyValue(this.getCscbOrder()));
		}			
	 	 return strValue;
	}
	/**
	* 关联订单  [CsOrder]     
	**/
	public void setCscbOrder(Long cscbOrder){
		this.cscbOrder = cscbOrder;
		this.setSeted(F.cscbOrder);
	}
	/**
	* 获取关联对象[系统订单]
	**/	 			
 	public CsOrder get$cscbOrder(){
 		com.ccclubs.model.CsOrder csOrder = (com.ccclubs.model.CsOrder) $.GetRequest("CsOrder$"+this.getCscbOrder());
 		if(csOrder!=null)
			return csOrder;
		if(this.getCscbOrder()!=null){
 			csOrder = CsOrder.get(this.getCscbOrder());
 		}
 		$.SetRequest("CsOrder$"+this.getCscbOrder(), csOrder);
	 	return csOrder;
	}
	/*******************************关联违章**********************************/	
	/**
	* 关联违章  [CsViolat]     
	**/
	public Long getCscbViolat(){
		return this.cscbViolat;
	}
	/**
	* 获取关联违章格式化(toString)
	**/
	public String getCscbViolat$(){
		String strValue="";
		if(this.getCscbViolat()!=null){
				strValue+=$.str(CsViolat.getKeyValue(this.getCscbViolat()));
		}			
	 	 return strValue;
	}
	/**
	* 关联违章  [CsViolat]     
	**/
	public void setCscbViolat(Long cscbViolat){
		this.cscbViolat = cscbViolat;
		this.setSeted(F.cscbViolat);
	}
	/**
	* 获取关联对象[车辆违章]
	**/	 			
 	public CsViolat get$cscbViolat(){
 		com.ccclubs.model.CsViolat csViolat = (com.ccclubs.model.CsViolat) $.GetRequest("CsViolat$"+this.getCscbViolat());
 		if(csViolat!=null)
			return csViolat;
		if(this.getCscbViolat()!=null){
 			csViolat = CsViolat.get(this.getCscbViolat());
 		}
 		$.SetRequest("CsViolat$"+this.getCscbViolat(), csViolat);
	 	return csViolat;
	}
	/*******************************关联事故**********************************/	
	/**
	* 关联事故  [CsTrouble]     
	**/
	public Long getCscbTrouble(){
		return this.cscbTrouble;
	}
	/**
	* 获取关联事故格式化(toString)
	**/
	public String getCscbTrouble$(){
		String strValue="";
		if(this.getCscbTrouble()!=null){
				strValue+=$.str(CsTrouble.getKeyValue(this.getCscbTrouble()));
		}			
	 	 return strValue;
	}
	/**
	* 关联事故  [CsTrouble]     
	**/
	public void setCscbTrouble(Long cscbTrouble){
		this.cscbTrouble = cscbTrouble;
		this.setSeted(F.cscbTrouble);
	}
	/**
	* 获取关联对象[车辆事故]
	**/	 			
 	public CsTrouble get$cscbTrouble(){
 		com.ccclubs.model.CsTrouble csTrouble = (com.ccclubs.model.CsTrouble) $.GetRequest("CsTrouble$"+this.getCscbTrouble());
 		if(csTrouble!=null)
			return csTrouble;
		if(this.getCscbTrouble()!=null){
 			csTrouble = CsTrouble.get(this.getCscbTrouble());
 		}
 		$.SetRequest("CsTrouble$"+this.getCscbTrouble(), csTrouble);
	 	return csTrouble;
	}
	/*******************************总欠账额**********************************/	
	/**
	* 总欠账额 [非空]      
	**/
	public Double getCscbValue(){
		return this.cscbValue;
	}
	/**
	* 获取总欠账额格式化(toString)
	**/
	public String getCscbValue$(){
		String strValue="";
		 strValue=$.str(this.getCscbValue());	
	 	 return strValue;
	}
	/**
	* 总欠账额 [非空]      
	**/
	public void setCscbValue(Double cscbValue){
		this.cscbValue = cscbValue;
		this.setSeted(F.cscbValue);
	}
	/*******************************已付额**********************************/	
	/**
	* 已付额 [非空]      
	**/
	public Double getCscbPayment(){
		return this.cscbPayment;
	}
	/**
	* 获取已付额格式化(toString)
	**/
	public String getCscbPayment$(){
		String strValue="";
		 strValue=$.str(this.getCscbPayment());	
	 	 return strValue;
	}
	/**
	* 已付额 [非空]      
	**/
	public void setCscbPayment(Double cscbPayment){
		this.cscbPayment = cscbPayment;
		this.setSeted(F.cscbPayment);
	}
	/*******************************还款时间**********************************/	
	/**
	* 还款时间       
	**/
	public Date getCscbRepayTime(){
		return this.cscbRepayTime;
	}
	/**
	* 获取还款时间格式化(toString)
	**/
	public String getCscbRepayTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscbRepayTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 还款时间       
	**/
	public void setCscbRepayTime(Date cscbRepayTime){
		this.cscbRepayTime = cscbRepayTime;
		this.setSeted(F.cscbRepayTime);
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
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCscbAddTime(){
		return this.cscbAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCscbAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscbAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCscbAddTime(Date cscbAddTime){
		this.cscbAddTime = cscbAddTime;
		this.setSeted(F.cscbAddTime);
	}
	/*******************************操作人员**********************************/	
	/**
	* 操作人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCscbEditor(){
		return this.cscbEditor;
	}
	/**
	* 获取操作人员格式化(toString)
	**/
	public String getCscbEditor$(){
		String strValue="";
		if(this.getCscbEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCscbEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 操作人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCscbEditor(Long cscbEditor){
		this.cscbEditor = cscbEditor;
		this.setSeted(F.cscbEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cscbEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCscbEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCscbEditor()!=null){
 			srvUser = SrvUser.get(this.getCscbEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCscbEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息       
	**/
	public String getCscbRemark(){
		return this.cscbRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCscbRemark$(){
		String strValue="";
		 strValue=$.str(this.getCscbRemark());
	 	 return strValue;
	}
	/**
	* 备注信息       
	**/
	public void setCscbRemark(String cscbRemark){
		this.cscbRemark = cscbRemark;
		this.setSeted(F.cscbRemark);
	}
	/*******************************跟踪信息**********************************/	
	/**
	* 跟踪信息    1:催缴短信已发 2:催缴电话已打    
	**/
	public String getCscbProcess(){
		return this.cscbProcess;
	}
	/**
	* 获取跟踪信息格式化(toString)
	**/
	public String getCscbProcess$(){
		String strValue="";
		if($.idin(this.getCscbProcess(),1))
			strValue+=$.str("催缴短信已发,");								
		if($.idin(this.getCscbProcess(),2))
			strValue+=$.str("催缴电话已打,");								
	 	 return strValue;
	}
	/**
	* 跟踪信息    1:催缴短信已发 2:催缴电话已打    
	**/
	public void setCscbProcess(String cscbProcess){
		this.cscbProcess = cscbProcess;
		this.setSeted(F.cscbProcess);
	}
	/*******************************还款交易号**********************************/	
	/**
	* 还款交易号     三方状态同步返回的交易号  
	**/
	public String getCscbPaySerial(){
		return this.cscbPaySerial;
	}
	/**
	* 获取还款交易号格式化(toString)
	**/
	public String getCscbPaySerial$(){
		String strValue="";
		 strValue=$.str(this.getCscbPaySerial());
	 	 return strValue;
	}
	/**
	* 还款交易号     三方状态同步返回的交易号  
	**/
	public void setCscbPaySerial(String cscbPaySerial){
		this.cscbPaySerial = cscbPaySerial;
		this.setSeted(F.cscbPaySerial);
	}
	/*******************************同步状态**********************************/	
	/**
	* 同步状态    0:未同步 1:已同步 2:不同步 3:待撤单 4:已撤单  描述此帐单是否已同步到第三方  
	**/
	public Short getCscbState(){
		return this.cscbState;
	}
	/**
	* 获取同步状态格式化(toString)
	**/
	public String getCscbState$(){
		String strValue="";
		 if($.equals($.str(this.getCscbState()),"0"))
			strValue=$.str("未同步");		 
		 if($.equals($.str(this.getCscbState()),"1"))
			strValue=$.str("已同步");		 
		 if($.equals($.str(this.getCscbState()),"2"))
			strValue=$.str("不同步");		 
		 if($.equals($.str(this.getCscbState()),"3"))
			strValue=$.str("待撤单");		 
		 if($.equals($.str(this.getCscbState()),"4"))
			strValue=$.str("已撤单");		 
	 	 return strValue;
	}
	/**
	* 同步状态    0:未同步 1:已同步 2:不同步 3:待撤单 4:已撤单  描述此帐单是否已同步到第三方  
	**/
	public void setCscbState(Short cscbState){
		this.cscbState = cscbState;
		this.setSeted(F.cscbState);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:待还款 1:已还款 2:已关闭 3:已代付 4:已坏帐    
	**/
	public Short getCscbStatus(){
		return this.cscbStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCscbStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCscbStatus()),"0"))
			strValue=$.str("待还款");		 
		 if($.equals($.str(this.getCscbStatus()),"1"))
			strValue=$.str("已还款");		 
		 if($.equals($.str(this.getCscbStatus()),"2"))
			strValue=$.str("已关闭");		 
		 if($.equals($.str(this.getCscbStatus()),"3"))
			strValue=$.str("已代付");		 
		 if($.equals($.str(this.getCscbStatus()),"4"))
			strValue=$.str("已坏帐");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:待还款 1:已还款 2:已关闭 3:已代付 4:已坏帐    
	**/
	public void setCscbStatus(Short cscbStatus){
		this.cscbStatus = cscbStatus;
		this.setSeted(F.cscbStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsCreditBill.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCreditBill.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCreditBill.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsCreditBill.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsCreditBill.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCreditBill.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCreditBill.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsCreditBill.this);
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
		/** 城市 [非空] [SrvHost]      **/
		public M cscbHost(Object cscbHost){this.put("cscbHost", cscbHost);return this;};
	 	/** and cscb_host is null */
 		public M cscbHostNull(){if(this.get("cscbHostNot")==null)this.put("cscbHostNot", "");this.put("cscbHost", null);return this;};
 		/** not .... */
 		public M cscbHostNot(){this.put("cscbHostNot", "not");return this;};
		/** 账单描述 [非空]       **/
		public M cscbTitle(Object cscbTitle){this.put("cscbTitle", cscbTitle);return this;};
	 	/** and cscb_title is null */
 		public M cscbTitleNull(){if(this.get("cscbTitleNot")==null)this.put("cscbTitleNot", "");this.put("cscbTitle", null);return this;};
 		/** not .... */
 		public M cscbTitleNot(){this.put("cscbTitleNot", "not");return this;};
		/** 所属会员 [非空] [CsMember]      **/
		public M cscbMember(Object cscbMember){this.put("cscbMember", cscbMember);return this;};
	 	/** and cscb_member is null */
 		public M cscbMemberNull(){if(this.get("cscbMemberNot")==null)this.put("cscbMemberNot", "");this.put("cscbMember", null);return this;};
 		/** not .... */
 		public M cscbMemberNot(){this.put("cscbMemberNot", "not");return this;};
		public M cscbMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("cscbMember$on", value);
			return this;
		};	
		/** 所属信用卡 [非空] [CsCreditCard]      **/
		public M cscbCreditCard(Object cscbCreditCard){this.put("cscbCreditCard", cscbCreditCard);return this;};
	 	/** and cscb_credit_card is null */
 		public M cscbCreditCardNull(){if(this.get("cscbCreditCardNot")==null)this.put("cscbCreditCardNot", "");this.put("cscbCreditCard", null);return this;};
 		/** not .... */
 		public M cscbCreditCardNot(){this.put("cscbCreditCardNot", "not");return this;};
		public M cscbCreditCard$on(CsCreditCard.M value){
			this.put("CsCreditCard", value);
			this.put("cscbCreditCard$on", value);
			return this;
		};	
		/** 关联订单  [CsOrder]      **/
		public M cscbOrder(Object cscbOrder){this.put("cscbOrder", cscbOrder);return this;};
	 	/** and cscb_order is null */
 		public M cscbOrderNull(){if(this.get("cscbOrderNot")==null)this.put("cscbOrderNot", "");this.put("cscbOrder", null);return this;};
 		/** not .... */
 		public M cscbOrderNot(){this.put("cscbOrderNot", "not");return this;};
		public M cscbOrder$on(CsOrder.M value){
			this.put("CsOrder", value);
			this.put("cscbOrder$on", value);
			return this;
		};	
		/** 关联违章  [CsViolat]      **/
		public M cscbViolat(Object cscbViolat){this.put("cscbViolat", cscbViolat);return this;};
	 	/** and cscb_violat is null */
 		public M cscbViolatNull(){if(this.get("cscbViolatNot")==null)this.put("cscbViolatNot", "");this.put("cscbViolat", null);return this;};
 		/** not .... */
 		public M cscbViolatNot(){this.put("cscbViolatNot", "not");return this;};
		public M cscbViolat$on(CsViolat.M value){
			this.put("CsViolat", value);
			this.put("cscbViolat$on", value);
			return this;
		};	
		/** 关联事故  [CsTrouble]      **/
		public M cscbTrouble(Object cscbTrouble){this.put("cscbTrouble", cscbTrouble);return this;};
	 	/** and cscb_trouble is null */
 		public M cscbTroubleNull(){if(this.get("cscbTroubleNot")==null)this.put("cscbTroubleNot", "");this.put("cscbTrouble", null);return this;};
 		/** not .... */
 		public M cscbTroubleNot(){this.put("cscbTroubleNot", "not");return this;};
		public M cscbTrouble$on(CsTrouble.M value){
			this.put("CsTrouble", value);
			this.put("cscbTrouble$on", value);
			return this;
		};	
		/** 总欠账额 [非空]       **/
		public M cscbValue(Object cscbValue){this.put("cscbValue", cscbValue);return this;};
	 	/** and cscb_value is null */
 		public M cscbValueNull(){if(this.get("cscbValueNot")==null)this.put("cscbValueNot", "");this.put("cscbValue", null);return this;};
 		/** not .... */
 		public M cscbValueNot(){this.put("cscbValueNot", "not");return this;};
		/** and cscb_value >= ? */
		public M cscbValueMin(Object min){this.put("cscbValueMin", min);return this;};
		/** and cscb_value <= ? */
		public M cscbValueMax(Object max){this.put("cscbValueMax", max);return this;};
		/** 已付额 [非空]       **/
		public M cscbPayment(Object cscbPayment){this.put("cscbPayment", cscbPayment);return this;};
	 	/** and cscb_payment is null */
 		public M cscbPaymentNull(){if(this.get("cscbPaymentNot")==null)this.put("cscbPaymentNot", "");this.put("cscbPayment", null);return this;};
 		/** not .... */
 		public M cscbPaymentNot(){this.put("cscbPaymentNot", "not");return this;};
		/** and cscb_payment >= ? */
		public M cscbPaymentMin(Object min){this.put("cscbPaymentMin", min);return this;};
		/** and cscb_payment <= ? */
		public M cscbPaymentMax(Object max){this.put("cscbPaymentMax", max);return this;};
		/** 还款时间        **/
		public M cscbRepayTime(Object cscbRepayTime){this.put("cscbRepayTime", cscbRepayTime);return this;};
	 	/** and cscb_repay_time is null */
 		public M cscbRepayTimeNull(){if(this.get("cscbRepayTimeNot")==null)this.put("cscbRepayTimeNot", "");this.put("cscbRepayTime", null);return this;};
 		/** not .... */
 		public M cscbRepayTimeNot(){this.put("cscbRepayTimeNot", "not");return this;};
 		/** and cscb_repay_time >= ? */
 		public M cscbRepayTimeStart(Object start){this.put("cscbRepayTimeStart", start);return this;};			
 		/** and cscb_repay_time <= ? */
 		public M cscbRepayTimeEnd(Object end){this.put("cscbRepayTimeEnd", end);return this;};
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
		/** 添加时间 [非空]       **/
		public M cscbAddTime(Object cscbAddTime){this.put("cscbAddTime", cscbAddTime);return this;};
	 	/** and cscb_add_time is null */
 		public M cscbAddTimeNull(){if(this.get("cscbAddTimeNot")==null)this.put("cscbAddTimeNot", "");this.put("cscbAddTime", null);return this;};
 		/** not .... */
 		public M cscbAddTimeNot(){this.put("cscbAddTimeNot", "not");return this;};
 		/** and cscb_add_time >= ? */
 		public M cscbAddTimeStart(Object start){this.put("cscbAddTimeStart", start);return this;};			
 		/** and cscb_add_time <= ? */
 		public M cscbAddTimeEnd(Object end){this.put("cscbAddTimeEnd", end);return this;};
		/** 操作人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cscbEditor(Object cscbEditor){this.put("cscbEditor", cscbEditor);return this;};
	 	/** and cscb_editor is null */
 		public M cscbEditorNull(){if(this.get("cscbEditorNot")==null)this.put("cscbEditorNot", "");this.put("cscbEditor", null);return this;};
 		/** not .... */
 		public M cscbEditorNot(){this.put("cscbEditorNot", "not");return this;};
		/** 备注信息        **/
		public M cscbRemark(Object cscbRemark){this.put("cscbRemark", cscbRemark);return this;};
	 	/** and cscb_remark is null */
 		public M cscbRemarkNull(){if(this.get("cscbRemarkNot")==null)this.put("cscbRemarkNot", "");this.put("cscbRemark", null);return this;};
 		/** not .... */
 		public M cscbRemarkNot(){this.put("cscbRemarkNot", "not");return this;};
		/** 跟踪信息    1:催缴短信已发 2:催缴电话已打     **/
		public M cscbProcess(Object cscbProcess){this.put("cscbProcess", cscbProcess);return this;};
	 	/** and cscb_process is null */
 		public M cscbProcessNull(){if(this.get("cscbProcessNot")==null)this.put("cscbProcessNot", "");this.put("cscbProcess", null);return this;};
 		/** not .... */
 		public M cscbProcessNot(){this.put("cscbProcessNot", "not");return this;};
		/** 还款交易号     三方状态同步返回的交易号   **/
		public M cscbPaySerial(Object cscbPaySerial){this.put("cscbPaySerial", cscbPaySerial);return this;};
	 	/** and cscb_pay_serial is null */
 		public M cscbPaySerialNull(){if(this.get("cscbPaySerialNot")==null)this.put("cscbPaySerialNot", "");this.put("cscbPaySerial", null);return this;};
 		/** not .... */
 		public M cscbPaySerialNot(){this.put("cscbPaySerialNot", "not");return this;};
		/** 同步状态    0:未同步 1:已同步 2:不同步 3:待撤单 4:已撤单  描述此帐单是否已同步到第三方   **/
		public M cscbState(Object cscbState){this.put("cscbState", cscbState);return this;};
	 	/** and cscb_state is null */
 		public M cscbStateNull(){if(this.get("cscbStateNot")==null)this.put("cscbStateNot", "");this.put("cscbState", null);return this;};
 		/** not .... */
 		public M cscbStateNot(){this.put("cscbStateNot", "not");return this;};
		/** 状态 [非空]   0:待还款 1:已还款 2:已关闭 3:已代付 4:已坏帐     **/
		public M cscbStatus(Object cscbStatus){this.put("cscbStatus", cscbStatus);return this;};
	 	/** and cscb_status is null */
 		public M cscbStatusNull(){if(this.get("cscbStatusNot")==null)this.put("cscbStatusNot", "");this.put("cscbStatus", null);return this;};
 		/** not .... */
 		public M cscbStatusNot(){this.put("cscbStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有信用账单 **/
		public @api List<CsCreditBill> list(Integer size){
			return getCsCreditBillList(this,size);
		}
		/** 获取信用账单分页 **/
		public @api Page<CsCreditBill> page(int page,int size){
			return getCsCreditBillPage(page, size , this);
		}
		/** 根据查询条件取信用账单 **/
		public @api CsCreditBill get(){
			return getCsCreditBill(this);
		}
		/** 获取信用账单数 **/
		public @api Long count(){
			return getCsCreditBillCount(this);
		}
		/** 获取信用账单表达式 **/
		public @api <T> T eval(String strEval){
			return getCsCreditBillEval(strEval,this);
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
			updateCsCreditBill(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscbId="cscbId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cscbHost="cscbHost";
		/** 账单描述 [非空]       **/
		public final static @type(String.class) @like String cscbTitle="cscbTitle";
		/** 所属会员 [非空] [CsMember]      **/
		public final static @type(Long.class)  String cscbMember="cscbMember";
		/** 所属信用卡 [非空] [CsCreditCard]      **/
		public final static @type(Long.class)  String cscbCreditCard="cscbCreditCard";
		/** 关联订单  [CsOrder]      **/
		public final static @type(Long.class)  String cscbOrder="cscbOrder";
		/** 关联违章  [CsViolat]      **/
		public final static @type(Long.class)  String cscbViolat="cscbViolat";
		/** 关联事故  [CsTrouble]      **/
		public final static @type(Long.class)  String cscbTrouble="cscbTrouble";
		/** 总欠账额 [非空]       **/
		public final static @type(Double.class)  String cscbValue="cscbValue";
		/** and cscb_value >= ? */
		public final static @type(Double.class) String cscbValueMin="cscbValueMin";
		/** and cscb_value <= ? */
		public final static @type(Double.class) String cscbValueMax="cscbValueMax";
		/** 已付额 [非空]       **/
		public final static @type(Double.class)  String cscbPayment="cscbPayment";
		/** and cscb_payment >= ? */
		public final static @type(Double.class) String cscbPaymentMin="cscbPaymentMin";
		/** and cscb_payment <= ? */
		public final static @type(Double.class) String cscbPaymentMax="cscbPaymentMax";
		/** 还款时间        **/
		public final static @type(Date.class)  String cscbRepayTime="cscbRepayTime";
	 	/** and cscb_repay_time >= ? */
 		public final static @type(Date.class) String cscbRepayTimeStart="cscbRepayTimeStart";
 		/** and cscb_repay_time <= ? */
 		public final static @type(Date.class) String cscbRepayTimeEnd="cscbRepayTimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cscbUpdateTime="cscbUpdateTime";
	 	/** and cscb_update_time >= ? */
 		public final static @type(Date.class) String cscbUpdateTimeStart="cscbUpdateTimeStart";
 		/** and cscb_update_time <= ? */
 		public final static @type(Date.class) String cscbUpdateTimeEnd="cscbUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cscbAddTime="cscbAddTime";
	 	/** and cscb_add_time >= ? */
 		public final static @type(Date.class) String cscbAddTimeStart="cscbAddTimeStart";
 		/** and cscb_add_time <= ? */
 		public final static @type(Date.class) String cscbAddTimeEnd="cscbAddTimeEnd";
		/** 操作人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String cscbEditor="cscbEditor";
		/** 备注信息        **/
		public final static @type(String.class) @like String cscbRemark="cscbRemark";
		/** 跟踪信息    1:催缴短信已发 2:催缴电话已打     **/
		public final static @type(String.class) @like String cscbProcess="cscbProcess";
		/** 还款交易号     三方状态同步返回的交易号   **/
		public final static @type(String.class) @like String cscbPaySerial="cscbPaySerial";
		/** 同步状态    0:未同步 1:已同步 2:不同步 3:待撤单 4:已撤单  描述此帐单是否已同步到第三方   **/
		public final static @type(Short.class)  String cscbState="cscbState";
		/** 状态 [非空]   0:待还款 1:已还款 2:已关闭 3:已代付 4:已坏帐     **/
		public final static @type(Short.class)  String cscbStatus="cscbStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscbId="cscb_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cscbHost="cscb_host";
		/** 账单描述 [非空]       **/
		public final static String cscbTitle="cscb_title";
		/** 所属会员 [非空] [CsMember]      **/
		public final static String cscbMember="cscb_member";
		/** 所属信用卡 [非空] [CsCreditCard]      **/
		public final static String cscbCreditCard="cscb_credit_card";
		/** 关联订单  [CsOrder]      **/
		public final static String cscbOrder="cscb_order";
		/** 关联违章  [CsViolat]      **/
		public final static String cscbViolat="cscb_violat";
		/** 关联事故  [CsTrouble]      **/
		public final static String cscbTrouble="cscb_trouble";
		/** 总欠账额 [非空]       **/
		public final static String cscbValue="cscb_value";
		/** 已付额 [非空]       **/
		public final static String cscbPayment="cscb_payment";
		/** 还款时间        **/
		public final static String cscbRepayTime="cscb_repay_time";
		/** 修改时间 [非空]       **/
		public final static String cscbUpdateTime="cscb_update_time";
		/** 添加时间 [非空]       **/
		public final static String cscbAddTime="cscb_add_time";
		/** 操作人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cscbEditor="cscb_editor";
		/** 备注信息        **/
		public final static String cscbRemark="cscb_remark";
		/** 跟踪信息    1:催缴短信已发 2:催缴电话已打     **/
		public final static String cscbProcess="cscb_process";
		/** 还款交易号     三方状态同步返回的交易号   **/
		public final static String cscbPaySerial="cscb_pay_serial";
		/** 同步状态    0:未同步 1:已同步 2:不同步 3:待撤单 4:已撤单  描述此帐单是否已同步到第三方   **/
		public final static String cscbState="cscb_state";
		/** 状态 [非空]   0:待还款 1:已还款 2:已关闭 3:已代付 4:已坏帐     **/
		public final static String cscbStatus="cscb_status";
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
				$.Set(name,CsCreditBill.getCsCreditBill(params));
			else
				$.Set(name,CsCreditBill.getCsCreditBillList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取信用账单数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsCreditBill) $.GetRequest("CsCreditBill$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsCreditBill.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsCreditBill.getCsCreditBill(params);
			else
				value = CsCreditBill.getCsCreditBillList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsCreditBill.Get($.add(CsCreditBill.F.cscbId,param));
		else if(!$.empty(param.toString()))
			value = CsCreditBill.get(Long.valueOf(param.toString()));
		$.SetRequest("CsCreditBill$"+param.hashCode(), value);
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
	public void mergeSet(CsCreditBill old){
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