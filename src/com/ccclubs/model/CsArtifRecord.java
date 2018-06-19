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

@namespace("finance/recharge")
public @caption("充值扣款") @table("cs_artif_record") class CsArtifRecord implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csar_id")   @primary  @note("  ") Long csarId;// 主键 非空     
	private @caption("城市") @column("csar_host")    @relate("$csarHost") @RelateClass(SrvHost.class)  @note("  ") Long csarHost;// 非空     
 	private SrvHost $csarHost;//关联对象[运营城市]
	private @caption("摘要") @column("csar_title")    @note(" recharge_abstract:充值扣款摘要  ") String csarTitle;// 非空 recharge_abstract:充值扣款摘要     
	private @caption("会员") @column("csar_member")    @relate("$csarMember") @RelateClass(CsMember.class)  @note("  ") Long csarMember;// 非空     
 	private CsMember $csarMember;//关联对象[会员帐号]
	private @caption("金额") @column("csar_count")    @note("  ") Double csarCount;// 非空     
	private @caption("金额类型") @column("csar_money_type")    @note(" 0:保证金 1:现金券  ") Short csarMoneyType;// 非空 0:保证金 1:现金券     
	private @caption("类型科目") @column("csar_record_subject")    @relate("$csarRecordSubject") @RelateClass(CsRecordSubject.class)  @note(" true:csrsIsArtif  ") Long csarRecordSubject;// 非空 true:csrsIsArtif     
 	private CsRecordSubject $csarRecordSubject;//关联对象[消费科目]
	private @caption("关联订单") @column("csar_order")    @relate("$csarOrder") @RelateClass(CsOrder.class)  @note("  ") Long csarOrder;//     
 	private CsOrder $csarOrder;//关联对象[系统订单]
	private @caption("其它关联") @column("csar_relate")    @note("  ") String csarRelate;//    CsRefund,CsAlipayRecord,CsCreditBill,CsTrouble,CsViolat 存储格式:类名@ID,其中类名包括CsRefund或CsAlipayRecord或CsCreditBill或CsTrouble或CsViolat
	private Object $csarRelate;//泛关联对象，对象类名由csarRelate字段中标明
	private @caption("现金已收讫") @column("csar_received")    @note("  ") Boolean csarReceived;//     
	private @caption("现金入帐凭据") @column("csar_voucher")    @note("  ") String csarVoucher;//     
	private @caption("添加人") @column("csar_adder")    @relate("$csarAdder") @RelateClass(SrvUser.class)  @note("  ") Long csarAdder;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csarAdder;//关联对象[用户]
	private @caption("修改人") @column("csar_editor")    @relate("$csarEditor") @RelateClass(SrvUser.class)  @note("  ") Long csarEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csarEditor;//关联对象[用户]
	private @caption("财务审核人") @column("csar_confirmer")    @note("  ") Long csarConfirmer;//     
	private @caption("修改时间") @column("csar_update_time")    @note("  ") Date csarUpdateTime;// 非空     
	private @caption("添加时间") @column("csar_add_time")    @note("  ") Date csarAddTime;// 非空     
	private @caption("审核时间") @column("csar_confirm_time")    @note("  ") Date csarConfirmTime;//     
	private @caption("备注") @column("csar_remark")    @note("  ") String csarRemark;//     
	private @caption("状态") @column("csar_status")    @note(" 0:待审核 1:已审核 2:审核未通过  ") Short csarStatus;// 非空 0:待审核 1:已审核 2:审核未通过     
	
	//默认构造函数
	public CsArtifRecord(){
	
	}
	
	//主键构造函数
	public CsArtifRecord(Long id){
		this.csarId = id;
	}
	
	/**所有字段构造函数 CsArtifRecord(csarHost,csarTitle,csarMember,csarCount,csarMoneyType,csarRecordSubject,csarOrder,csarRelate,csarReceived,csarVoucher,csarAdder,csarEditor,csarConfirmer,csarUpdateTime,csarAddTime,csarConfirmTime,csarRemark,csarStatus)
	 CsArtifRecord(
	 	$.getLong("csarHost")//城市 [非空]
	 	,$.getString("csarTitle")//摘要 [非空]
	 	,$.getLong("csarMember")//会员 [非空]
	 	,$.getDouble("csarCount")//金额 [非空]
	 	,$.getShort("csarMoneyType")//金额类型 [非空]
	 	,$.getLong("csarRecordSubject")//类型科目 [非空]
	 	,$.getLong("csarOrder")//关联订单
	 	,$.getString("csarRelate")//其它关联
	 	,$.getBoolean("csarReceived")//现金已收讫
	 	,$.getString("csarVoucher")//现金入帐凭据
	 	,$.getLong("csarAdder")//添加人 [非空]
	 	,$.getLong("csarEditor")//修改人 [非空]
	 	,$.getLong("csarConfirmer")//财务审核人
	 	,$.getDate("csarUpdateTime")//修改时间 [非空]
	 	,$.getDate("csarAddTime")//添加时间 [非空]
	 	,$.getDate("csarConfirmTime")//审核时间
	 	,$.getString("csarRemark")//备注
	 	,$.getShort("csarStatus")//状态 [非空]
	 )
	**/
	public CsArtifRecord(Long csarHost,String csarTitle,Long csarMember,Double csarCount,Short csarMoneyType,Long csarRecordSubject,Long csarOrder,String csarRelate,Boolean csarReceived,String csarVoucher,Long csarAdder,Long csarEditor,Long csarConfirmer,Date csarUpdateTime,Date csarAddTime,Date csarConfirmTime,String csarRemark,Short csarStatus){
		this.csarHost=csarHost;
		this.csarTitle=csarTitle;
		this.csarMember=csarMember;
		this.csarCount=csarCount;
		this.csarMoneyType=csarMoneyType;
		this.csarRecordSubject=csarRecordSubject;
		this.csarOrder=csarOrder;
		this.csarRelate=csarRelate;
		this.csarReceived=csarReceived;
		this.csarVoucher=csarVoucher;
		this.csarAdder=csarAdder;
		this.csarEditor=csarEditor;
		this.csarConfirmer=csarConfirmer;
		this.csarUpdateTime=csarUpdateTime;
		this.csarAddTime=csarAddTime;
		this.csarConfirmTime=csarConfirmTime;
		this.csarRemark=csarRemark;
		this.csarStatus=csarStatus;
	}
	
	//设置非空字段
	public CsArtifRecord setNotNull(Long csarHost,String csarTitle,Long csarMember,Double csarCount,Short csarMoneyType,Long csarRecordSubject,Long csarAdder,Long csarEditor,Date csarUpdateTime,Date csarAddTime,Short csarStatus){
		this.csarHost=csarHost;
		this.csarTitle=csarTitle;
		this.csarMember=csarMember;
		this.csarCount=csarCount;
		this.csarMoneyType=csarMoneyType;
		this.csarRecordSubject=csarRecordSubject;
		this.csarAdder=csarAdder;
		this.csarEditor=csarEditor;
		this.csarUpdateTime=csarUpdateTime;
		this.csarAddTime=csarAddTime;
		this.csarStatus=csarStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsArtifRecord csarId(Long csarId){
		this.csarId = csarId;
		this.setSeted(F.csarId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsArtifRecord csarHost(Long csarHost){
		this.csarHost = csarHost;
		this.setSeted(F.csarHost);
		return this;
	}
	/** 摘要 [非空]   recharge_abstract:充值扣款摘要     **/
	public CsArtifRecord csarTitle(String csarTitle){
		this.csarTitle = csarTitle;
		this.setSeted(F.csarTitle);
		return this;
	}
	/** 会员 [非空] [CsMember]      **/
	public CsArtifRecord csarMember(Long csarMember){
		this.csarMember = csarMember;
		this.setSeted(F.csarMember);
		return this;
	}
	/** 金额 [非空]       **/
	public CsArtifRecord csarCount(Double csarCount){
		this.csarCount = csarCount;
		this.setSeted(F.csarCount);
		return this;
	}
	/** 金额类型 [非空]   0:保证金 1:现金券     **/
	public CsArtifRecord csarMoneyType(Short csarMoneyType){
		this.csarMoneyType = csarMoneyType;
		this.setSeted(F.csarMoneyType);
		return this;
	}
	/** 类型科目 [非空] [CsRecordSubject]  true:csrsIsArtif     **/
	public CsArtifRecord csarRecordSubject(Long csarRecordSubject){
		this.csarRecordSubject = csarRecordSubject;
		this.setSeted(F.csarRecordSubject);
		return this;
	}
	/** 关联订单  [CsOrder]      **/
	public CsArtifRecord csarOrder(Long csarOrder){
		this.csarOrder = csarOrder;
		this.setSeted(F.csarOrder);
		return this;
	}
	/** 其它关联       CsRefund,CsAlipayRecord,CsCreditBill,CsTrouble,CsViolat **/
	public CsArtifRecord csarRelate(String csarRelate){
		this.csarRelate = csarRelate;
		this.setSeted(F.csarRelate);
		return this;
	}
	/** 现金已收讫        **/
	public CsArtifRecord csarReceived(Boolean csarReceived){
		this.csarReceived = csarReceived;
		this.setSeted(F.csarReceived);
		return this;
	}
	/** 现金入帐凭据        **/
	public CsArtifRecord csarVoucher(String csarVoucher){
		this.csarVoucher = csarVoucher;
		this.setSeted(F.csarVoucher);
		return this;
	}
	/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsArtifRecord csarAdder(Long csarAdder){
		this.csarAdder = csarAdder;
		this.setSeted(F.csarAdder);
		return this;
	}
	/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsArtifRecord csarEditor(Long csarEditor){
		this.csarEditor = csarEditor;
		this.setSeted(F.csarEditor);
		return this;
	}
	/** 财务审核人        **/
	public CsArtifRecord csarConfirmer(Long csarConfirmer){
		this.csarConfirmer = csarConfirmer;
		this.setSeted(F.csarConfirmer);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsArtifRecord csarUpdateTime(Date csarUpdateTime){
		this.csarUpdateTime = csarUpdateTime;
		this.setSeted(F.csarUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsArtifRecord csarAddTime(Date csarAddTime){
		this.csarAddTime = csarAddTime;
		this.setSeted(F.csarAddTime);
		return this;
	}
	/** 审核时间        **/
	public CsArtifRecord csarConfirmTime(Date csarConfirmTime){
		this.csarConfirmTime = csarConfirmTime;
		this.setSeted(F.csarConfirmTime);
		return this;
	}
	/** 备注        **/
	public CsArtifRecord csarRemark(String csarRemark){
		this.csarRemark = csarRemark;
		this.setSeted(F.csarRemark);
		return this;
	}
	/** 状态 [非空]   0:待审核 1:已审核 2:审核未通过     **/
	public CsArtifRecord csarStatus(Short csarStatus){
		this.csarStatus = csarStatus;
		this.setSeted(F.csarStatus);
		return this;
	}
	
	
	//克隆对象
	public CsArtifRecord clone(){
		CsArtifRecord clone = new CsArtifRecord();
		clone.csarHost=this.csarHost;
		clone.csarTitle=this.csarTitle;
		clone.csarMember=this.csarMember;
		clone.csarCount=this.csarCount;
		clone.csarMoneyType=this.csarMoneyType;
		clone.csarRecordSubject=this.csarRecordSubject;
		clone.csarAdder=this.csarAdder;
		clone.csarEditor=this.csarEditor;
		clone.csarUpdateTime=this.csarUpdateTime;
		clone.csarAddTime=this.csarAddTime;
		clone.csarStatus=this.csarStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取充值扣款
	 * @param id
	 * @return
	 */
	public static @api CsArtifRecord get(Long id){		
		return getCsArtifRecordById(id);
	}
	/**
	 * 获取所有充值扣款
	 * @return
	 */
	public static @api List<CsArtifRecord> list(Map params,Integer size){
		return getCsArtifRecordList(params,size);
	}
	/**
	 * 获取充值扣款分页
	 * @return
	 */
	public static @api Page<CsArtifRecord> page(int page,int size,Map params){
		return getCsArtifRecordPage(page, size , params);
	}
	/**
	 * 根据查询条件取充值扣款
	 * @param params
	 * @return
	 */
	public static @api CsArtifRecord Get(Map params){
		return getCsArtifRecord(params);
	}
	/**
	 * 获取充值扣款数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsArtifRecordCount(params);
	}
	/**
	 * 获取充值扣款数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsArtifRecordEval(eval,params);
	}
	
	/**
	 * 根据ID取充值扣款
	 * @param id
	 * @return
	 */
	public static @api CsArtifRecord getCsArtifRecordById(Long id){		
		CsArtifRecord csArtifRecord = (CsArtifRecord) $.GetRequest("CsArtifRecord$"+id);
		if(csArtifRecord!=null)
			return csArtifRecord;
		com.ccclubs.service.admin.ICsArtifRecordService csArtifRecordService = $.GetSpringBean("csArtifRecordService");		
		return csArtifRecordService.getCsArtifRecordById(id);
	}
	
	
	/**
	 * 根据ID取充值扣款的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsArtifRecord.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsArtifRecord csArtifRecord = get(id);
		if(csArtifRecord!=null){
			String strValue = csArtifRecord.getCsarHost$();
			if(!"CsarHost".equals("CsarHost"))
				strValue+="("+csArtifRecord.getCsarHost$()+")";
			if(!"CsarHost".equals("CsarRecordSubject"))
				strValue+="("+csArtifRecord.getCsarRecordSubject$()+")";
			MemCache.setValue(CsArtifRecord.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsarHost$();
		if(!"CsarHost".equals("CsarHost"))
			keyValue+="("+this.getCsarHost$()+")";
		if(!"CsarHost".equals("CsarRecordSubject"))
			keyValue+="("+this.getCsarRecordSubject$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有充值扣款
	 * @return
	 */
	public static @api List<CsArtifRecord> getCsArtifRecordList(Map params,Integer size){
		com.ccclubs.service.admin.ICsArtifRecordService csArtifRecordService = $.GetSpringBean("csArtifRecordService");
		return csArtifRecordService.getCsArtifRecordList(params, size);
	}
	
	/**
	 * 获取充值扣款分页
	 * @return
	 */
	public static @api Page<CsArtifRecord> getCsArtifRecordPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsArtifRecordService csArtifRecordService = $.GetSpringBean("csArtifRecordService");
		return csArtifRecordService.getCsArtifRecordPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取充值扣款
	 * @param params
	 * @return
	 */
	public static @api CsArtifRecord getCsArtifRecord(Map params){
		com.ccclubs.service.admin.ICsArtifRecordService csArtifRecordService = $.GetSpringBean("csArtifRecordService");
		return csArtifRecordService.getCsArtifRecord(params);
	}
	
	/**
	 * 获取充值扣款数
	 * @return
	 */
	public static @api Long getCsArtifRecordCount(Map params){
		com.ccclubs.service.admin.ICsArtifRecordService csArtifRecordService = $.GetSpringBean("csArtifRecordService");
		return csArtifRecordService.getCsArtifRecordCount(params);
	}
		
		
	/**
	 * 获取充值扣款自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsArtifRecordEval(String eval,Map params){
		com.ccclubs.service.admin.ICsArtifRecordService csArtifRecordService = $.GetSpringBean("csArtifRecordService");
		return csArtifRecordService.getCsArtifRecordEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsArtifRecord(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsArtifRecordService csArtifRecordService = $.GetSpringBean("csArtifRecordService");
		csArtifRecordService.updateCsArtifRecordByConfirm(set, where);
	}
	
	
	/**
	 * 保存充值扣款对象
	 * @param params
	 * @return
	 */
	public CsArtifRecord save(){
		com.ccclubs.service.admin.ICsArtifRecordService csArtifRecordService = $.GetSpringBean("csArtifRecordService");
		if(this.getCsarId()!=null)
			csArtifRecordService.updateCsArtifRecord(this);
		else
			return csArtifRecordService.saveCsArtifRecord(this);
		return this;
	}
	
	
	/**
	 * 更新充值扣款对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsArtifRecordService csArtifRecordService = $.GetSpringBean("csArtifRecordService");
		csArtifRecordService.updateCsArtifRecord$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsArtifRecordService csArtifRecordService = $.GetSpringBean("csArtifRecordService");
		if($.equals($.config("logic_delete"),"true"))
			csArtifRecordService.removeCsArtifRecordById(this.getCsarId());
		else
			csArtifRecordService.deleteCsArtifRecordById(this.getCsarId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsArtifRecordService csArtifRecordService = $.GetSpringBean("csArtifRecordService");
		return csArtifRecordService.executeTransaction(function);
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
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsarHost(){
		return this.csarHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsarHost$(){
		String strValue="";
		if(this.getCsarHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsarHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
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
	/*******************************摘要**********************************/	
	/**
	* 摘要 [非空]   recharge_abstract:充值扣款摘要    
	**/
	public String getCsarTitle(){
		return this.csarTitle;
	}
	/**
	* 获取摘要格式化(toString)
	**/
	public String getCsarTitle$(){
		String strValue="";
		 strValue=$.str(this.getCsarTitle());
	 	 return strValue;
	}
	/**
	* 摘要 [非空]   recharge_abstract:充值扣款摘要    
	**/
	public void setCsarTitle(String csarTitle){
		this.csarTitle = csarTitle;
		this.setSeted(F.csarTitle);
	}
	/*******************************会员**********************************/	
	/**
	* 会员 [非空] [CsMember]     
	**/
	public Long getCsarMember(){
		return this.csarMember;
	}
	/**
	* 获取会员格式化(toString)
	**/
	public String getCsarMember$(){
		String strValue="";
		if(this.getCsarMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsarMember()));
		}			
	 	 return strValue;
	}
	/**
	* 会员 [非空] [CsMember]     
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
	/*******************************金额**********************************/	
	/**
	* 金额 [非空]      
	**/
	public Double getCsarCount(){
		return this.csarCount;
	}
	/**
	* 获取金额格式化(toString)
	**/
	public String getCsarCount$(){
		String strValue="";
		 strValue=$.str(this.getCsarCount());	
	 	 return strValue;
	}
	/**
	* 金额 [非空]      
	**/
	public void setCsarCount(Double csarCount){
		this.csarCount = csarCount;
		this.setSeted(F.csarCount);
	}
	/*******************************金额类型**********************************/	
	/**
	* 金额类型 [非空]   0:保证金 1:现金券    
	**/
	public Short getCsarMoneyType(){
		return this.csarMoneyType;
	}
	/**
	* 获取金额类型格式化(toString)
	**/
	public String getCsarMoneyType$(){
		String strValue="";
		 if($.equals($.str(this.getCsarMoneyType()),"0"))
			strValue=$.str("保证金");		 
		 if($.equals($.str(this.getCsarMoneyType()),"1"))
			strValue=$.str("现金券");		 
	 	 return strValue;
	}
	/**
	* 金额类型 [非空]   0:保证金 1:现金券    
	**/
	public void setCsarMoneyType(Short csarMoneyType){
		this.csarMoneyType = csarMoneyType;
		this.setSeted(F.csarMoneyType);
	}
	/*******************************类型科目**********************************/	
	/**
	* 类型科目 [非空] [CsRecordSubject]  true:csrsIsArtif    
	**/
	public Long getCsarRecordSubject(){
		return this.csarRecordSubject;
	}
	/**
	* 获取类型科目格式化(toString)
	**/
	public String getCsarRecordSubject$(){
		String strValue="";
		if(this.getCsarRecordSubject()!=null){
				strValue+=$.str(CsRecordSubject.getKeyValue(this.getCsarRecordSubject()));
		}			
	 	 return strValue;
	}
	/**
	* 类型科目 [非空] [CsRecordSubject]  true:csrsIsArtif    
	**/
	public void setCsarRecordSubject(Long csarRecordSubject){
		this.csarRecordSubject = csarRecordSubject;
		this.setSeted(F.csarRecordSubject);
	}
	/**
	* 获取关联对象[消费科目]
	**/	 			
 	public CsRecordSubject get$csarRecordSubject(){
 		com.ccclubs.model.CsRecordSubject csRecordSubject = (com.ccclubs.model.CsRecordSubject) $.GetRequest("CsRecordSubject$"+this.getCsarRecordSubject());
 		if(csRecordSubject!=null)
			return csRecordSubject;
		if(this.getCsarRecordSubject()!=null){
 			csRecordSubject = CsRecordSubject.get(this.getCsarRecordSubject());
 		}
 		$.SetRequest("CsRecordSubject$"+this.getCsarRecordSubject(), csRecordSubject);
	 	return csRecordSubject;
	}
	/*******************************关联订单**********************************/	
	/**
	* 关联订单  [CsOrder]     
	**/
	public Long getCsarOrder(){
		return this.csarOrder;
	}
	/**
	* 获取关联订单格式化(toString)
	**/
	public String getCsarOrder$(){
		String strValue="";
		if(this.getCsarOrder()!=null){
				strValue+=$.str(CsOrder.getKeyValue(this.getCsarOrder()));
		}			
	 	 return strValue;
	}
	/**
	* 关联订单  [CsOrder]     
	**/
	public void setCsarOrder(Long csarOrder){
		this.csarOrder = csarOrder;
		this.setSeted(F.csarOrder);
	}
	/**
	* 获取关联对象[系统订单]
	**/	 			
 	public CsOrder get$csarOrder(){
 		com.ccclubs.model.CsOrder csOrder = (com.ccclubs.model.CsOrder) $.GetRequest("CsOrder$"+this.getCsarOrder());
 		if(csOrder!=null)
			return csOrder;
		if(this.getCsarOrder()!=null){
 			csOrder = CsOrder.get(this.getCsarOrder());
 		}
 		$.SetRequest("CsOrder$"+this.getCsarOrder(), csOrder);
	 	return csOrder;
	}
	/*******************************其它关联**********************************/	
	/**
	* 其它关联       CsRefund,CsAlipayRecord,CsCreditBill,CsTrouble,CsViolat
	**/
	public String getCsarRelate(){
		return this.csarRelate;
	}
	/**
	* 获取其它关联格式化(toString)
	**/
	public String getCsarRelate$(){
		String strValue="";
	 	 Object object = this.get$csarRelate();
	 	 if(object!=null){
	 		caption caption = object.getClass().getAnnotation(caption.class);
	 		if(caption!=null)strValue+="["+caption.value()+"]";
	 		try {
				strValue+=object.getClass().getMethod("getKeyValue").invoke(object);
			} catch (Exception e) {
				e.printStackTrace();
			}
	 	 }
	 	 return strValue;
	}
	/**
	* 其它关联       CsRefund,CsAlipayRecord,CsCreditBill,CsTrouble,CsViolat
	**/
	public void setCsarRelate(String csarRelate){
		this.csarRelate = csarRelate;
		this.setSeted(F.csarRelate);
	}
	/**
	* 泛关联对象，对象类名由csarRelate字段中标明
	**/	 			
 	public <T> T get$csarRelate(){
		if($.empty(this.csarRelate))
 			return null;
 		try{
	 		String ClassName = this.csarRelate.split("@")[0];
	 		Long id = Long.parseLong(this.csarRelate.split("@")[1]);
	 		Class<?> cls = Class.forName("com.ccclubs.model."+ClassName);
	 		return (T)cls.getMethod("get", Long.class).invoke(null, id);	
 		}catch(Exception ex){
 			ex.printStackTrace();
 			return null;
 		}
	}
	/*******************************现金已收讫**********************************/	
	/**
	* 现金已收讫       
	**/
	public Boolean getCsarReceived(){
		return this.csarReceived;
	}
	/**
	* 获取现金已收讫格式化(toString)
	**/
	public String getCsarReceived$(){
		String strValue="";
		  strValue=$.str((this.getCsarReceived()!=null && this.getCsarReceived())?"是":"否");			
	 	 return strValue;
	}
	/**
	* 现金已收讫       
	**/
	public void setCsarReceived(Boolean csarReceived){
		this.csarReceived = csarReceived;
		this.setSeted(F.csarReceived);
	}
	/*******************************现金入帐凭据**********************************/	
	/**
	* 现金入帐凭据       
	**/
	public String getCsarVoucher(){
		return this.csarVoucher;
	}
	/**
	* 获取现金入帐凭据格式化(toString)
	**/
	public String getCsarVoucher$(){
		String strValue="";
		 strValue=$.str(this.getCsarVoucher());
	 	 return strValue;
	}
	/**
	* 现金入帐凭据       
	**/
	public void setCsarVoucher(String csarVoucher){
		this.csarVoucher = csarVoucher;
		this.setSeted(F.csarVoucher);
	}
	/*******************************添加人**********************************/	
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsarAdder(){
		return this.csarAdder;
	}
	/**
	* 获取添加人格式化(toString)
	**/
	public String getCsarAdder$(){
		String strValue="";
		if(this.getCsarAdder()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsarAdder()));
		}			
	 	 return strValue;
	}
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsarAdder(Long csarAdder){
		this.csarAdder = csarAdder;
		this.setSeted(F.csarAdder);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csarAdder(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsarAdder());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsarAdder()!=null){
 			srvUser = SrvUser.get(this.getCsarAdder());
 		}
 		$.SetRequest("SrvUser$"+this.getCsarAdder(), srvUser);
	 	return srvUser;
	}
	/*******************************修改人**********************************/	
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsarEditor(){
		return this.csarEditor;
	}
	/**
	* 获取修改人格式化(toString)
	**/
	public String getCsarEditor$(){
		String strValue="";
		if(this.getCsarEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsarEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsarEditor(Long csarEditor){
		this.csarEditor = csarEditor;
		this.setSeted(F.csarEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csarEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsarEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsarEditor()!=null){
 			srvUser = SrvUser.get(this.getCsarEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCsarEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************财务审核人**********************************/	
	/**
	* 财务审核人       
	**/
	public Long getCsarConfirmer(){
		return this.csarConfirmer;
	}
	/**
	* 获取财务审核人格式化(toString)
	**/
	public String getCsarConfirmer$(){
		String strValue="";
		 strValue=$.str(this.getCsarConfirmer());
	 	 return strValue;
	}
	/**
	* 财务审核人       
	**/
	public void setCsarConfirmer(Long csarConfirmer){
		this.csarConfirmer = csarConfirmer;
		this.setSeted(F.csarConfirmer);
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
	/*******************************审核时间**********************************/	
	/**
	* 审核时间       
	**/
	public Date getCsarConfirmTime(){
		return this.csarConfirmTime;
	}
	/**
	* 获取审核时间格式化(toString)
	**/
	public String getCsarConfirmTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsarConfirmTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 审核时间       
	**/
	public void setCsarConfirmTime(Date csarConfirmTime){
		this.csarConfirmTime = csarConfirmTime;
		this.setSeted(F.csarConfirmTime);
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getCsarRemark(){
		return this.csarRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCsarRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsarRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setCsarRemark(String csarRemark){
		this.csarRemark = csarRemark;
		this.setSeted(F.csarRemark);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:待审核 1:已审核 2:审核未通过    
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
			strValue=$.str("待审核");		 
		 if($.equals($.str(this.getCsarStatus()),"1"))
			strValue=$.str("已审核");		 
		 if($.equals($.str(this.getCsarStatus()),"2"))
			strValue=$.str("审核未通过");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:待审核 1:已审核 2:审核未通过    
	**/
	public void setCsarStatus(Short csarStatus){
		this.csarStatus = csarStatus;
		this.setSeted(F.csarStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsArtifRecord.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsArtifRecord.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsArtifRecord.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsArtifRecord.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsArtifRecord.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsArtifRecord.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsArtifRecord.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsArtifRecord.this);
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
		/** 城市 [非空] [SrvHost]      **/
		public M csarHost(Object csarHost){this.put("csarHost", csarHost);return this;};
	 	/** and csar_host is null */
 		public M csarHostNull(){if(this.get("csarHostNot")==null)this.put("csarHostNot", "");this.put("csarHost", null);return this;};
 		/** not .... */
 		public M csarHostNot(){this.put("csarHostNot", "not");return this;};
		/** 摘要 [非空]   recharge_abstract:充值扣款摘要     **/
		public M csarTitle(Object csarTitle){this.put("csarTitle", csarTitle);return this;};
	 	/** and csar_title is null */
 		public M csarTitleNull(){if(this.get("csarTitleNot")==null)this.put("csarTitleNot", "");this.put("csarTitle", null);return this;};
 		/** not .... */
 		public M csarTitleNot(){this.put("csarTitleNot", "not");return this;};
		/** 会员 [非空] [CsMember]      **/
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
		/** 金额 [非空]       **/
		public M csarCount(Object csarCount){this.put("csarCount", csarCount);return this;};
	 	/** and csar_count is null */
 		public M csarCountNull(){if(this.get("csarCountNot")==null)this.put("csarCountNot", "");this.put("csarCount", null);return this;};
 		/** not .... */
 		public M csarCountNot(){this.put("csarCountNot", "not");return this;};
		/** and csar_count >= ? */
		public M csarCountMin(Object min){this.put("csarCountMin", min);return this;};
		/** and csar_count <= ? */
		public M csarCountMax(Object max){this.put("csarCountMax", max);return this;};
		/** 金额类型 [非空]   0:保证金 1:现金券     **/
		public M csarMoneyType(Object csarMoneyType){this.put("csarMoneyType", csarMoneyType);return this;};
	 	/** and csar_money_type is null */
 		public M csarMoneyTypeNull(){if(this.get("csarMoneyTypeNot")==null)this.put("csarMoneyTypeNot", "");this.put("csarMoneyType", null);return this;};
 		/** not .... */
 		public M csarMoneyTypeNot(){this.put("csarMoneyTypeNot", "not");return this;};
		/** 类型科目 [非空] [CsRecordSubject]  true:csrsIsArtif     **/
		public M csarRecordSubject(Object csarRecordSubject){this.put("csarRecordSubject", csarRecordSubject);return this;};
	 	/** and csar_record_subject is null */
 		public M csarRecordSubjectNull(){if(this.get("csarRecordSubjectNot")==null)this.put("csarRecordSubjectNot", "");this.put("csarRecordSubject", null);return this;};
 		/** not .... */
 		public M csarRecordSubjectNot(){this.put("csarRecordSubjectNot", "not");return this;};
		public M csarRecordSubject$on(CsRecordSubject.M value){
			this.put("CsRecordSubject", value);
			this.put("csarRecordSubject$on", value);
			return this;
		};	
		/** 关联订单  [CsOrder]      **/
		public M csarOrder(Object csarOrder){this.put("csarOrder", csarOrder);return this;};
	 	/** and csar_order is null */
 		public M csarOrderNull(){if(this.get("csarOrderNot")==null)this.put("csarOrderNot", "");this.put("csarOrder", null);return this;};
 		/** not .... */
 		public M csarOrderNot(){this.put("csarOrderNot", "not");return this;};
		public M csarOrder$on(CsOrder.M value){
			this.put("CsOrder", value);
			this.put("csarOrder$on", value);
			return this;
		};	
		/** 其它关联       CsRefund,CsAlipayRecord,CsCreditBill,CsTrouble,CsViolat **/
		public M csarRelate(Object csarRelate){this.put("csarRelate", csarRelate);return this;};
	 	/** and csar_relate is null */
 		public M csarRelateNull(){if(this.get("csarRelateNot")==null)this.put("csarRelateNot", "");this.put("csarRelate", null);return this;};
 		/** not .... */
 		public M csarRelateNot(){this.put("csarRelateNot", "not");return this;};
		/** 现金已收讫        **/
		public M csarReceived(Object csarReceived){this.put("csarReceived", csarReceived);return this;};
	 	/** and csar_received is null */
 		public M csarReceivedNull(){if(this.get("csarReceivedNot")==null)this.put("csarReceivedNot", "");this.put("csarReceived", null);return this;};
 		/** not .... */
 		public M csarReceivedNot(){this.put("csarReceivedNot", "not");return this;};
		/** 现金入帐凭据        **/
		public M csarVoucher(Object csarVoucher){this.put("csarVoucher", csarVoucher);return this;};
	 	/** and csar_voucher is null */
 		public M csarVoucherNull(){if(this.get("csarVoucherNot")==null)this.put("csarVoucherNot", "");this.put("csarVoucher", null);return this;};
 		/** not .... */
 		public M csarVoucherNot(){this.put("csarVoucherNot", "not");return this;};
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csarAdder(Object csarAdder){this.put("csarAdder", csarAdder);return this;};
	 	/** and csar_adder is null */
 		public M csarAdderNull(){if(this.get("csarAdderNot")==null)this.put("csarAdderNot", "");this.put("csarAdder", null);return this;};
 		/** not .... */
 		public M csarAdderNot(){this.put("csarAdderNot", "not");return this;};
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csarEditor(Object csarEditor){this.put("csarEditor", csarEditor);return this;};
	 	/** and csar_editor is null */
 		public M csarEditorNull(){if(this.get("csarEditorNot")==null)this.put("csarEditorNot", "");this.put("csarEditor", null);return this;};
 		/** not .... */
 		public M csarEditorNot(){this.put("csarEditorNot", "not");return this;};
		/** 财务审核人        **/
		public M csarConfirmer(Object csarConfirmer){this.put("csarConfirmer", csarConfirmer);return this;};
	 	/** and csar_confirmer is null */
 		public M csarConfirmerNull(){if(this.get("csarConfirmerNot")==null)this.put("csarConfirmerNot", "");this.put("csarConfirmer", null);return this;};
 		/** not .... */
 		public M csarConfirmerNot(){this.put("csarConfirmerNot", "not");return this;};
		/** and csar_confirmer >= ? */
		public M csarConfirmerMin(Object min){this.put("csarConfirmerMin", min);return this;};
		/** and csar_confirmer <= ? */
		public M csarConfirmerMax(Object max){this.put("csarConfirmerMax", max);return this;};
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
		/** 审核时间        **/
		public M csarConfirmTime(Object csarConfirmTime){this.put("csarConfirmTime", csarConfirmTime);return this;};
	 	/** and csar_confirm_time is null */
 		public M csarConfirmTimeNull(){if(this.get("csarConfirmTimeNot")==null)this.put("csarConfirmTimeNot", "");this.put("csarConfirmTime", null);return this;};
 		/** not .... */
 		public M csarConfirmTimeNot(){this.put("csarConfirmTimeNot", "not");return this;};
 		/** and csar_confirm_time >= ? */
 		public M csarConfirmTimeStart(Object start){this.put("csarConfirmTimeStart", start);return this;};			
 		/** and csar_confirm_time <= ? */
 		public M csarConfirmTimeEnd(Object end){this.put("csarConfirmTimeEnd", end);return this;};
		/** 备注        **/
		public M csarRemark(Object csarRemark){this.put("csarRemark", csarRemark);return this;};
	 	/** and csar_remark is null */
 		public M csarRemarkNull(){if(this.get("csarRemarkNot")==null)this.put("csarRemarkNot", "");this.put("csarRemark", null);return this;};
 		/** not .... */
 		public M csarRemarkNot(){this.put("csarRemarkNot", "not");return this;};
		/** 状态 [非空]   0:待审核 1:已审核 2:审核未通过     **/
		public M csarStatus(Object csarStatus){this.put("csarStatus", csarStatus);return this;};
	 	/** and csar_status is null */
 		public M csarStatusNull(){if(this.get("csarStatusNot")==null)this.put("csarStatusNot", "");this.put("csarStatus", null);return this;};
 		/** not .... */
 		public M csarStatusNot(){this.put("csarStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有充值扣款 **/
		public @api List<CsArtifRecord> list(Integer size){
			return getCsArtifRecordList(this,size);
		}
		/** 获取充值扣款分页 **/
		public @api Page<CsArtifRecord> page(int page,int size){
			return getCsArtifRecordPage(page, size , this);
		}
		/** 根据查询条件取充值扣款 **/
		public @api CsArtifRecord get(){
			return getCsArtifRecord(this);
		}
		/** 获取充值扣款数 **/
		public @api Long count(){
			return getCsArtifRecordCount(this);
		}
		/** 获取充值扣款表达式 **/
		public @api <T> T eval(String strEval){
			return getCsArtifRecordEval(strEval,this);
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
			updateCsArtifRecord(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csarId="csarId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csarHost="csarHost";
		/** 摘要 [非空]   recharge_abstract:充值扣款摘要     **/
		public final static @type(String.class) @like String csarTitle="csarTitle";
		/** 会员 [非空] [CsMember]      **/
		public final static @type(Long.class)  String csarMember="csarMember";
		/** 金额 [非空]       **/
		public final static @type(Double.class)  String csarCount="csarCount";
		/** and csar_count >= ? */
		public final static @type(Double.class) String csarCountMin="csarCountMin";
		/** and csar_count <= ? */
		public final static @type(Double.class) String csarCountMax="csarCountMax";
		/** 金额类型 [非空]   0:保证金 1:现金券     **/
		public final static @type(Short.class)  String csarMoneyType="csarMoneyType";
		/** 类型科目 [非空] [CsRecordSubject]  true:csrsIsArtif     **/
		public final static @type(Long.class)  String csarRecordSubject="csarRecordSubject";
		/** 关联订单  [CsOrder]      **/
		public final static @type(Long.class)  String csarOrder="csarOrder";
		/** 其它关联       CsRefund,CsAlipayRecord,CsCreditBill,CsTrouble,CsViolat **/
		public final static @type(String.class)  String csarRelate="csarRelate";
		/** 现金已收讫        **/
		public final static @type(Boolean.class)  String csarReceived="csarReceived";
		/** 现金入帐凭据        **/
		public final static @type(String.class) @like String csarVoucher="csarVoucher";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csarAdder="csarAdder";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csarEditor="csarEditor";
		/** 财务审核人        **/
		public final static @type(Long.class)  String csarConfirmer="csarConfirmer";
		/** and csar_confirmer >= ? */
		public final static @type(Long.class) String csarConfirmerMin="csarConfirmerMin";
		/** and csar_confirmer <= ? */
		public final static @type(Long.class) String csarConfirmerMax="csarConfirmerMax";
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
		/** 审核时间        **/
		public final static @type(Date.class)  String csarConfirmTime="csarConfirmTime";
	 	/** and csar_confirm_time >= ? */
 		public final static @type(Date.class) String csarConfirmTimeStart="csarConfirmTimeStart";
 		/** and csar_confirm_time <= ? */
 		public final static @type(Date.class) String csarConfirmTimeEnd="csarConfirmTimeEnd";
		/** 备注        **/
		public final static @type(String.class) @like String csarRemark="csarRemark";
		/** 状态 [非空]   0:待审核 1:已审核 2:审核未通过     **/
		public final static @type(Short.class)  String csarStatus="csarStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csarId="csar_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csarHost="csar_host";
		/** 摘要 [非空]   recharge_abstract:充值扣款摘要     **/
		public final static String csarTitle="csar_title";
		/** 会员 [非空] [CsMember]      **/
		public final static String csarMember="csar_member";
		/** 金额 [非空]       **/
		public final static String csarCount="csar_count";
		/** 金额类型 [非空]   0:保证金 1:现金券     **/
		public final static String csarMoneyType="csar_money_type";
		/** 类型科目 [非空] [CsRecordSubject]  true:csrsIsArtif     **/
		public final static String csarRecordSubject="csar_record_subject";
		/** 关联订单  [CsOrder]      **/
		public final static String csarOrder="csar_order";
		/** 其它关联       CsRefund,CsAlipayRecord,CsCreditBill,CsTrouble,CsViolat **/
		public final static String csarRelate="csar_relate";
		/** 现金已收讫        **/
		public final static String csarReceived="csar_received";
		/** 现金入帐凭据        **/
		public final static String csarVoucher="csar_voucher";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csarAdder="csar_adder";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csarEditor="csar_editor";
		/** 财务审核人        **/
		public final static String csarConfirmer="csar_confirmer";
		/** 修改时间 [非空]       **/
		public final static String csarUpdateTime="csar_update_time";
		/** 添加时间 [非空]       **/
		public final static String csarAddTime="csar_add_time";
		/** 审核时间        **/
		public final static String csarConfirmTime="csar_confirm_time";
		/** 备注        **/
		public final static String csarRemark="csar_remark";
		/** 状态 [非空]   0:待审核 1:已审核 2:审核未通过     **/
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
				$.Set(name,CsArtifRecord.getCsArtifRecord(params));
			else
				$.Set(name,CsArtifRecord.getCsArtifRecordList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取充值扣款数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsArtifRecord) $.GetRequest("CsArtifRecord$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsArtifRecord.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsArtifRecord.getCsArtifRecord(params);
			else
				value = CsArtifRecord.getCsArtifRecordList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsArtifRecord.Get($.add(CsArtifRecord.F.csarId,param));
		else if(!$.empty(param.toString()))
			value = CsArtifRecord.get(Long.valueOf(param.toString()));
		$.SetRequest("CsArtifRecord$"+param.hashCode(), value);
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
	public void mergeSet(CsArtifRecord old){
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