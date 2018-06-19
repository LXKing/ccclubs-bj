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

@namespace("finance/operecord")
public @caption("收支记录") @table("cs_oper_record") class CsOperRecord implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csor_id")   @primary  @note("  ") Long csorId;// 主键 外键 非空     
	private @caption("城市") @column("csor_host")    @relate("$csorHost") @RelateClass(SrvHost.class)  @note("  ") Long csorHost;// 非空     
 	private SrvHost $csorHost;//关联对象[运营城市]
	private @caption("摘要") @column("csor_title")    @note("  ") String csorTitle;// 非空     
	private @caption("金额类型") @column("csor_money_type")    @note(" 0:保证金 1:优惠券  ") Short csorMoneyType;// 非空 0:保证金 1:优惠券     
	private @caption("科目类型") @column("csor_record_type")    @relate("$csorRecordType") @RelateClass(CsRecordSubject.class)  @note("  ") Long csorRecordType;// 非空     
 	private CsRecordSubject $csorRecordType;//关联对象[消费科目]
	private @caption("收入") @column("csor_in")    @note("  ") Double csorIn;//     
	private @caption("支出") @column("csor_out")    @note("  ") Double csorOut;//     
	private @caption("关联消费记录") @column("csor_record")    @relate("$csorRecord") @RelateClass(CsRecord.class)  @note("  ") Long csorRecord;// 非空     
 	private CsRecord $csorRecord;//关联对象[消费记录]
	private @caption("关联会员") @column("csor_member")    @relate("$csorMember") @RelateClass(CsMember.class)  @note("  ") Long csorMember;//     
 	private CsMember $csorMember;//关联对象[会员帐号]
	private @caption("关联订单") @column("csor_order")    @relate("$csorOrder") @RelateClass(CsOrder.class)  @note("  ") Long csorOrder;//     
 	private CsOrder $csorOrder;//关联对象[系统订单]
	private @caption("其它关联") @column("csor_object")    @note("  类型@id") String csorObject;//  类型@id  CsTrouble,CsViolat,CsRepair,CsMaintain 存储格式:类名@ID,其中类名包括CsTrouble或CsViolat或CsRepair或CsMaintain
	private Object $csorObject;//泛关联对象，对象类名由csorObject字段中标明
	private @caption("发生时间") @column("csor_date_time")    @note("  ") Date csorDateTime;// 非空     
	private @caption("结账流水") @column("csor_cash_no")    @relate("$csorCashNo") @RelateClass(CsCashRecord.class)  @note("  ") Long csorCashNo;//     
 	private CsCashRecord $csorCashNo;//关联对象[提现记录]
	private @caption("添加人") @column("csor_adder")    @relate("$csorAdder") @RelateClass(SrvUser.class)  @note("  ") Long csorAdder;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csorAdder;//关联对象[用户]
	private @caption("状态") @column("csor_status")    @note(" 1:正常 2:争议 0:无效  ") Short csorStatus;// 非空 1:正常 2:争议 0:无效     
	
	//默认构造函数
	public CsOperRecord(){
	
	}
	
	//主键构造函数
	public CsOperRecord(Long id){
		this.csorId = id;
	}
	
	/**所有字段构造函数 CsOperRecord(csorHost,csorTitle,csorMoneyType,csorRecordType,csorIn,csorOut,csorRecord,csorMember,csorOrder,csorObject,csorDateTime,csorCashNo,csorAdder,csorStatus)
	 CsOperRecord(
	 	$.getLong("csorHost")//城市 [非空]
	 	,$.getString("csorTitle")//摘要 [非空]
	 	,$.getShort("csorMoneyType")//金额类型 [非空]
	 	,$.getLong("csorRecordType")//科目类型 [非空]
	 	,$.getDouble("csorIn")//收入
	 	,$.getDouble("csorOut")//支出
	 	,$.getLong("csorRecord")//关联消费记录 [非空]
	 	,$.getLong("csorMember")//关联会员
	 	,$.getLong("csorOrder")//关联订单
	 	,$.getString("csorObject")//其它关联
	 	,$.getDate("csorDateTime")//发生时间 [非空]
	 	,$.getLong("csorCashNo")//结账流水
	 	,$.getLong("csorAdder")//添加人 [非空]
	 	,$.getShort("csorStatus")//状态 [非空]
	 )
	**/
	public CsOperRecord(Long csorHost,String csorTitle,Short csorMoneyType,Long csorRecordType,Double csorIn,Double csorOut,Long csorRecord,Long csorMember,Long csorOrder,String csorObject,Date csorDateTime,Long csorCashNo,Long csorAdder,Short csorStatus){
		this.csorHost=csorHost;
		this.csorTitle=csorTitle;
		this.csorMoneyType=csorMoneyType;
		this.csorRecordType=csorRecordType;
		this.csorIn=csorIn;
		this.csorOut=csorOut;
		this.csorRecord=csorRecord;
		this.csorMember=csorMember;
		this.csorOrder=csorOrder;
		this.csorObject=csorObject;
		this.csorDateTime=csorDateTime;
		this.csorCashNo=csorCashNo;
		this.csorAdder=csorAdder;
		this.csorStatus=csorStatus;
	}
	
	//设置非空字段
	public CsOperRecord setNotNull(Long csorHost,String csorTitle,Short csorMoneyType,Long csorRecordType,Long csorRecord,Date csorDateTime,Long csorAdder,Short csorStatus){
		this.csorHost=csorHost;
		this.csorTitle=csorTitle;
		this.csorMoneyType=csorMoneyType;
		this.csorRecordType=csorRecordType;
		this.csorRecord=csorRecord;
		this.csorDateTime=csorDateTime;
		this.csorAdder=csorAdder;
		this.csorStatus=csorStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsOperRecord csorId(Long csorId){
		this.csorId = csorId;
		this.setSeted(F.csorId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsOperRecord csorHost(Long csorHost){
		this.csorHost = csorHost;
		this.setSeted(F.csorHost);
		return this;
	}
	/** 摘要 [非空]       **/
	public CsOperRecord csorTitle(String csorTitle){
		this.csorTitle = csorTitle;
		this.setSeted(F.csorTitle);
		return this;
	}
	/** 金额类型 [非空]   0:保证金 1:优惠券     **/
	public CsOperRecord csorMoneyType(Short csorMoneyType){
		this.csorMoneyType = csorMoneyType;
		this.setSeted(F.csorMoneyType);
		return this;
	}
	/** 科目类型 [非空] [CsRecordSubject]      **/
	public CsOperRecord csorRecordType(Long csorRecordType){
		this.csorRecordType = csorRecordType;
		this.setSeted(F.csorRecordType);
		return this;
	}
	/** 收入        **/
	public CsOperRecord csorIn(Double csorIn){
		this.csorIn = csorIn;
		this.setSeted(F.csorIn);
		return this;
	}
	/** 支出        **/
	public CsOperRecord csorOut(Double csorOut){
		this.csorOut = csorOut;
		this.setSeted(F.csorOut);
		return this;
	}
	/** 关联消费记录 [非空] [CsRecord]      **/
	public CsOperRecord csorRecord(Long csorRecord){
		this.csorRecord = csorRecord;
		this.setSeted(F.csorRecord);
		return this;
	}
	/** 关联会员  [CsMember]      **/
	public CsOperRecord csorMember(Long csorMember){
		this.csorMember = csorMember;
		this.setSeted(F.csorMember);
		return this;
	}
	/** 关联订单  [CsOrder]      **/
	public CsOperRecord csorOrder(Long csorOrder){
		this.csorOrder = csorOrder;
		this.setSeted(F.csorOrder);
		return this;
	}
	/** 其它关联     类型@id  CsTrouble,CsViolat,CsRepair,CsMaintain **/
	public CsOperRecord csorObject(String csorObject){
		this.csorObject = csorObject;
		this.setSeted(F.csorObject);
		return this;
	}
	/** 发生时间 [非空]       **/
	public CsOperRecord csorDateTime(Date csorDateTime){
		this.csorDateTime = csorDateTime;
		this.setSeted(F.csorDateTime);
		return this;
	}
	/** 结账流水  [CsCashRecord]      **/
	public CsOperRecord csorCashNo(Long csorCashNo){
		this.csorCashNo = csorCashNo;
		this.setSeted(F.csorCashNo);
		return this;
	}
	/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsOperRecord csorAdder(Long csorAdder){
		this.csorAdder = csorAdder;
		this.setSeted(F.csorAdder);
		return this;
	}
	/** 状态 [非空]   1:正常 2:争议 0:无效     **/
	public CsOperRecord csorStatus(Short csorStatus){
		this.csorStatus = csorStatus;
		this.setSeted(F.csorStatus);
		return this;
	}
	
	
	//克隆对象
	public CsOperRecord clone(){
		CsOperRecord clone = new CsOperRecord();
		clone.csorHost=this.csorHost;
		clone.csorTitle=this.csorTitle;
		clone.csorMoneyType=this.csorMoneyType;
		clone.csorRecordType=this.csorRecordType;
		clone.csorRecord=this.csorRecord;
		clone.csorDateTime=this.csorDateTime;
		clone.csorAdder=this.csorAdder;
		clone.csorStatus=this.csorStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取收支记录
	 * @param id
	 * @return
	 */
	public static @api CsOperRecord get(Long id){		
		return getCsOperRecordById(id);
	}
	/**
	 * 获取所有收支记录
	 * @return
	 */
	public static @api List<CsOperRecord> list(Map params,Integer size){
		return getCsOperRecordList(params,size);
	}
	/**
	 * 获取收支记录分页
	 * @return
	 */
	public static @api Page<CsOperRecord> page(int page,int size,Map params){
		return getCsOperRecordPage(page, size , params);
	}
	/**
	 * 根据查询条件取收支记录
	 * @param params
	 * @return
	 */
	public static @api CsOperRecord Get(Map params){
		return getCsOperRecord(params);
	}
	/**
	 * 获取收支记录数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsOperRecordCount(params);
	}
	/**
	 * 获取收支记录数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsOperRecordEval(eval,params);
	}
	
	/**
	 * 根据ID取收支记录
	 * @param id
	 * @return
	 */
	public static @api CsOperRecord getCsOperRecordById(Long id){		
		CsOperRecord csOperRecord = (CsOperRecord) $.GetRequest("CsOperRecord$"+id);
		if(csOperRecord!=null)
			return csOperRecord;
		com.ccclubs.service.admin.ICsOperRecordService csOperRecordService = $.GetSpringBean("csOperRecordService");		
		return csOperRecordService.getCsOperRecordById(id);
	}
	
	
	/**
	 * 根据ID取收支记录的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsOperRecord.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsOperRecord csOperRecord = get(id);
		if(csOperRecord!=null){
			String strValue = csOperRecord.getCsorTitle$();
			if(!"CsorTitle".equals("CsorTitle"))
				strValue+="("+csOperRecord.getCsorTitle$()+")";
			if(!"CsorTitle".equals("CsorRecordType"))
				strValue+="("+csOperRecord.getCsorRecordType$()+")";
			MemCache.setValue(CsOperRecord.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsorTitle$();
		if(!"CsorTitle".equals("CsorTitle"))
			keyValue+="("+this.getCsorTitle$()+")";
		if(!"CsorTitle".equals("CsorRecordType"))
			keyValue+="("+this.getCsorRecordType$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有收支记录
	 * @return
	 */
	public static @api List<CsOperRecord> getCsOperRecordList(Map params,Integer size){
		com.ccclubs.service.admin.ICsOperRecordService csOperRecordService = $.GetSpringBean("csOperRecordService");
		return csOperRecordService.getCsOperRecordList(params, size);
	}
	
	/**
	 * 获取收支记录分页
	 * @return
	 */
	public static @api Page<CsOperRecord> getCsOperRecordPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsOperRecordService csOperRecordService = $.GetSpringBean("csOperRecordService");
		return csOperRecordService.getCsOperRecordPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取收支记录
	 * @param params
	 * @return
	 */
	public static @api CsOperRecord getCsOperRecord(Map params){
		com.ccclubs.service.admin.ICsOperRecordService csOperRecordService = $.GetSpringBean("csOperRecordService");
		return csOperRecordService.getCsOperRecord(params);
	}
	
	/**
	 * 获取收支记录数
	 * @return
	 */
	public static @api Long getCsOperRecordCount(Map params){
		com.ccclubs.service.admin.ICsOperRecordService csOperRecordService = $.GetSpringBean("csOperRecordService");
		return csOperRecordService.getCsOperRecordCount(params);
	}
		
		
	/**
	 * 获取收支记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsOperRecordEval(String eval,Map params){
		com.ccclubs.service.admin.ICsOperRecordService csOperRecordService = $.GetSpringBean("csOperRecordService");
		return csOperRecordService.getCsOperRecordEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsOperRecord(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsOperRecordService csOperRecordService = $.GetSpringBean("csOperRecordService");
		csOperRecordService.updateCsOperRecordByConfirm(set, where);
	}
	
	
	/**
	 * 保存收支记录对象
	 * @param params
	 * @return
	 */
	public CsOperRecord save(){
		com.ccclubs.service.admin.ICsOperRecordService csOperRecordService = $.GetSpringBean("csOperRecordService");
		if(this.getCsorId()!=null)
			csOperRecordService.updateCsOperRecord(this);
		else
			return csOperRecordService.saveCsOperRecord(this);
		return this;
	}
	
	
	/**
	 * 更新收支记录对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsOperRecordService csOperRecordService = $.GetSpringBean("csOperRecordService");
		csOperRecordService.updateCsOperRecord$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsOperRecordService csOperRecordService = $.GetSpringBean("csOperRecordService");
		if($.equals($.config("logic_delete"),"true"))
			csOperRecordService.removeCsOperRecordById(this.getCsorId());
		else
			csOperRecordService.deleteCsOperRecordById(this.getCsorId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsOperRecordService csOperRecordService = $.GetSpringBean("csOperRecordService");
		return csOperRecordService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsorId(){
		return this.csorId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsorId$(){
		String strValue="";
		 strValue=$.str(this.getCsorId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsorId(Long csorId){
		this.csorId = csorId;
		this.setSeted(F.csorId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsorHost(){
		return this.csorHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsorHost$(){
		String strValue="";
		if(this.getCsorHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsorHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsorHost(Long csorHost){
		this.csorHost = csorHost;
		this.setSeted(F.csorHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csorHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsorHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsorHost()!=null){
 			srvHost = SrvHost.get(this.getCsorHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsorHost(), srvHost);
	 	return srvHost;
	}
	/*******************************摘要**********************************/	
	/**
	* 摘要 [非空]      
	**/
	public String getCsorTitle(){
		return this.csorTitle;
	}
	/**
	* 获取摘要格式化(toString)
	**/
	public String getCsorTitle$(){
		String strValue="";
		 strValue=$.str(this.getCsorTitle());
	 	 return strValue;
	}
	/**
	* 摘要 [非空]      
	**/
	public void setCsorTitle(String csorTitle){
		this.csorTitle = csorTitle;
		this.setSeted(F.csorTitle);
	}
	/*******************************金额类型**********************************/	
	/**
	* 金额类型 [非空]   0:保证金 1:优惠券    
	**/
	public Short getCsorMoneyType(){
		return this.csorMoneyType;
	}
	/**
	* 获取金额类型格式化(toString)
	**/
	public String getCsorMoneyType$(){
		String strValue="";
		 if($.equals($.str(this.getCsorMoneyType()),"0"))
			strValue=$.str("保证金");		 
		 if($.equals($.str(this.getCsorMoneyType()),"1"))
			strValue=$.str("优惠券");		 
	 	 return strValue;
	}
	/**
	* 金额类型 [非空]   0:保证金 1:优惠券    
	**/
	public void setCsorMoneyType(Short csorMoneyType){
		this.csorMoneyType = csorMoneyType;
		this.setSeted(F.csorMoneyType);
	}
	/*******************************科目类型**********************************/	
	/**
	* 科目类型 [非空] [CsRecordSubject]     
	**/
	public Long getCsorRecordType(){
		return this.csorRecordType;
	}
	/**
	* 获取科目类型格式化(toString)
	**/
	public String getCsorRecordType$(){
		String strValue="";
		if(this.getCsorRecordType()!=null){
				strValue+=$.str(CsRecordSubject.getKeyValue(this.getCsorRecordType()));
		}			
	 	 return strValue;
	}
	/**
	* 科目类型 [非空] [CsRecordSubject]     
	**/
	public void setCsorRecordType(Long csorRecordType){
		this.csorRecordType = csorRecordType;
		this.setSeted(F.csorRecordType);
	}
	/**
	* 获取关联对象[消费科目]
	**/	 			
 	public CsRecordSubject get$csorRecordType(){
 		com.ccclubs.model.CsRecordSubject csRecordSubject = (com.ccclubs.model.CsRecordSubject) $.GetRequest("CsRecordSubject$"+this.getCsorRecordType());
 		if(csRecordSubject!=null)
			return csRecordSubject;
		if(this.getCsorRecordType()!=null){
 			csRecordSubject = CsRecordSubject.get(this.getCsorRecordType());
 		}
 		$.SetRequest("CsRecordSubject$"+this.getCsorRecordType(), csRecordSubject);
	 	return csRecordSubject;
	}
	/*******************************收入**********************************/	
	/**
	* 收入       
	**/
	public Double getCsorIn(){
		return this.csorIn;
	}
	/**
	* 获取收入格式化(toString)
	**/
	public String getCsorIn$(){
		String strValue="";
		 strValue=$.str(this.getCsorIn());	
	 	 return strValue;
	}
	/**
	* 收入       
	**/
	public void setCsorIn(Double csorIn){
		this.csorIn = csorIn;
		this.setSeted(F.csorIn);
	}
	/*******************************支出**********************************/	
	/**
	* 支出       
	**/
	public Double getCsorOut(){
		return this.csorOut;
	}
	/**
	* 获取支出格式化(toString)
	**/
	public String getCsorOut$(){
		String strValue="";
		 strValue=$.str(this.getCsorOut());	
	 	 return strValue;
	}
	/**
	* 支出       
	**/
	public void setCsorOut(Double csorOut){
		this.csorOut = csorOut;
		this.setSeted(F.csorOut);
	}
	/*******************************关联消费记录**********************************/	
	/**
	* 关联消费记录 [非空] [CsRecord]     
	**/
	public Long getCsorRecord(){
		return this.csorRecord;
	}
	/**
	* 获取关联消费记录格式化(toString)
	**/
	public String getCsorRecord$(){
		String strValue="";
		if(this.getCsorRecord()!=null){
				strValue+=$.str(CsRecord.getKeyValue(this.getCsorRecord()));
		}			
	 	 return strValue;
	}
	/**
	* 关联消费记录 [非空] [CsRecord]     
	**/
	public void setCsorRecord(Long csorRecord){
		this.csorRecord = csorRecord;
		this.setSeted(F.csorRecord);
	}
	/**
	* 获取关联对象[消费记录]
	**/	 			
 	public CsRecord get$csorRecord(){
 		com.ccclubs.model.CsRecord csRecord = (com.ccclubs.model.CsRecord) $.GetRequest("CsRecord$"+this.getCsorRecord());
 		if(csRecord!=null)
			return csRecord;
		if(this.getCsorRecord()!=null){
 			csRecord = CsRecord.get(this.getCsorRecord());
 		}
 		$.SetRequest("CsRecord$"+this.getCsorRecord(), csRecord);
	 	return csRecord;
	}
	/*******************************关联会员**********************************/	
	/**
	* 关联会员  [CsMember]     
	**/
	public Long getCsorMember(){
		return this.csorMember;
	}
	/**
	* 获取关联会员格式化(toString)
	**/
	public String getCsorMember$(){
		String strValue="";
		if(this.getCsorMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsorMember()));
		}			
	 	 return strValue;
	}
	/**
	* 关联会员  [CsMember]     
	**/
	public void setCsorMember(Long csorMember){
		this.csorMember = csorMember;
		this.setSeted(F.csorMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csorMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsorMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsorMember()!=null){
 			csMember = CsMember.get(this.getCsorMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsorMember(), csMember);
	 	return csMember;
	}
	/*******************************关联订单**********************************/	
	/**
	* 关联订单  [CsOrder]     
	**/
	public Long getCsorOrder(){
		return this.csorOrder;
	}
	/**
	* 获取关联订单格式化(toString)
	**/
	public String getCsorOrder$(){
		String strValue="";
		if(this.getCsorOrder()!=null){
				strValue+=$.str(CsOrder.getKeyValue(this.getCsorOrder()));
		}			
	 	 return strValue;
	}
	/**
	* 关联订单  [CsOrder]     
	**/
	public void setCsorOrder(Long csorOrder){
		this.csorOrder = csorOrder;
		this.setSeted(F.csorOrder);
	}
	/**
	* 获取关联对象[系统订单]
	**/	 			
 	public CsOrder get$csorOrder(){
 		com.ccclubs.model.CsOrder csOrder = (com.ccclubs.model.CsOrder) $.GetRequest("CsOrder$"+this.getCsorOrder());
 		if(csOrder!=null)
			return csOrder;
		if(this.getCsorOrder()!=null){
 			csOrder = CsOrder.get(this.getCsorOrder());
 		}
 		$.SetRequest("CsOrder$"+this.getCsorOrder(), csOrder);
	 	return csOrder;
	}
	/*******************************其它关联**********************************/	
	/**
	* 其它关联     类型@id  CsTrouble,CsViolat,CsRepair,CsMaintain
	**/
	public String getCsorObject(){
		return this.csorObject;
	}
	/**
	* 获取其它关联格式化(toString)
	**/
	public String getCsorObject$(){
		String strValue="";
	 	 Object object = this.get$csorObject();
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
	* 其它关联     类型@id  CsTrouble,CsViolat,CsRepair,CsMaintain
	**/
	public void setCsorObject(String csorObject){
		this.csorObject = csorObject;
		this.setSeted(F.csorObject);
	}
	/**
	* 泛关联对象，对象类名由csorObject字段中标明
	**/	 			
 	public <T> T get$csorObject(){
		if($.empty(this.csorObject))
 			return null;
 		try{
	 		String ClassName = this.csorObject.split("@")[0];
	 		Long id = Long.parseLong(this.csorObject.split("@")[1]);
	 		Class<?> cls = Class.forName("com.ccclubs.model."+ClassName);
	 		return (T)cls.getMethod("get", Long.class).invoke(null, id);	
 		}catch(Exception ex){
 			ex.printStackTrace();
 			return null;
 		}
	}
	/*******************************发生时间**********************************/	
	/**
	* 发生时间 [非空]      
	**/
	public Date getCsorDateTime(){
		return this.csorDateTime;
	}
	/**
	* 获取发生时间格式化(toString)
	**/
	public String getCsorDateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsorDateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 发生时间 [非空]      
	**/
	public void setCsorDateTime(Date csorDateTime){
		this.csorDateTime = csorDateTime;
		this.setSeted(F.csorDateTime);
	}
	/*******************************结账流水**********************************/	
	/**
	* 结账流水  [CsCashRecord]     
	**/
	public Long getCsorCashNo(){
		return this.csorCashNo;
	}
	/**
	* 获取结账流水格式化(toString)
	**/
	public String getCsorCashNo$(){
		String strValue="";
		if(this.getCsorCashNo()!=null){
				strValue+=$.str(CsCashRecord.getKeyValue(this.getCsorCashNo()));
		}			
	 	 return strValue;
	}
	/**
	* 结账流水  [CsCashRecord]     
	**/
	public void setCsorCashNo(Long csorCashNo){
		this.csorCashNo = csorCashNo;
		this.setSeted(F.csorCashNo);
	}
	/**
	* 获取关联对象[提现记录]
	**/	 			
 	public CsCashRecord get$csorCashNo(){
 		com.ccclubs.model.CsCashRecord csCashRecord = (com.ccclubs.model.CsCashRecord) $.GetRequest("CsCashRecord$"+this.getCsorCashNo());
 		if(csCashRecord!=null)
			return csCashRecord;
		if(this.getCsorCashNo()!=null){
 			csCashRecord = CsCashRecord.get(this.getCsorCashNo());
 		}
 		$.SetRequest("CsCashRecord$"+this.getCsorCashNo(), csCashRecord);
	 	return csCashRecord;
	}
	/*******************************添加人**********************************/	
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsorAdder(){
		return this.csorAdder;
	}
	/**
	* 获取添加人格式化(toString)
	**/
	public String getCsorAdder$(){
		String strValue="";
		if(this.getCsorAdder()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsorAdder()));
		}			
	 	 return strValue;
	}
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsorAdder(Long csorAdder){
		this.csorAdder = csorAdder;
		this.setSeted(F.csorAdder);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csorAdder(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsorAdder());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsorAdder()!=null){
 			srvUser = SrvUser.get(this.getCsorAdder());
 		}
 		$.SetRequest("SrvUser$"+this.getCsorAdder(), srvUser);
	 	return srvUser;
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 2:争议 0:无效    
	**/
	public Short getCsorStatus(){
		return this.csorStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsorStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsorStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsorStatus()),"2"))
			strValue=$.str("争议");		 
		 if($.equals($.str(this.getCsorStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 2:争议 0:无效    
	**/
	public void setCsorStatus(Short csorStatus){
		this.csorStatus = csorStatus;
		this.setSeted(F.csorStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsOperRecord.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsOperRecord.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsOperRecord.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsOperRecord.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsOperRecord.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsOperRecord.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsOperRecord.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsOperRecord.this);
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
		public M csorId(Object csorId){this.put("csorId", csorId);return this;};
	 	/** and csor_id is null */
 		public M csorIdNull(){if(this.get("csorIdNot")==null)this.put("csorIdNot", "");this.put("csorId", null);return this;};
 		/** not .... */
 		public M csorIdNot(){this.put("csorIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csorHost(Object csorHost){this.put("csorHost", csorHost);return this;};
	 	/** and csor_host is null */
 		public M csorHostNull(){if(this.get("csorHostNot")==null)this.put("csorHostNot", "");this.put("csorHost", null);return this;};
 		/** not .... */
 		public M csorHostNot(){this.put("csorHostNot", "not");return this;};
		/** 摘要 [非空]       **/
		public M csorTitle(Object csorTitle){this.put("csorTitle", csorTitle);return this;};
	 	/** and csor_title is null */
 		public M csorTitleNull(){if(this.get("csorTitleNot")==null)this.put("csorTitleNot", "");this.put("csorTitle", null);return this;};
 		/** not .... */
 		public M csorTitleNot(){this.put("csorTitleNot", "not");return this;};
		/** 金额类型 [非空]   0:保证金 1:优惠券     **/
		public M csorMoneyType(Object csorMoneyType){this.put("csorMoneyType", csorMoneyType);return this;};
	 	/** and csor_money_type is null */
 		public M csorMoneyTypeNull(){if(this.get("csorMoneyTypeNot")==null)this.put("csorMoneyTypeNot", "");this.put("csorMoneyType", null);return this;};
 		/** not .... */
 		public M csorMoneyTypeNot(){this.put("csorMoneyTypeNot", "not");return this;};
		/** 科目类型 [非空] [CsRecordSubject]      **/
		public M csorRecordType(Object csorRecordType){this.put("csorRecordType", csorRecordType);return this;};
	 	/** and csor_record_type is null */
 		public M csorRecordTypeNull(){if(this.get("csorRecordTypeNot")==null)this.put("csorRecordTypeNot", "");this.put("csorRecordType", null);return this;};
 		/** not .... */
 		public M csorRecordTypeNot(){this.put("csorRecordTypeNot", "not");return this;};
		public M csorRecordType$on(CsRecordSubject.M value){
			this.put("CsRecordSubject", value);
			this.put("csorRecordType$on", value);
			return this;
		};	
		/** 收入        **/
		public M csorIn(Object csorIn){this.put("csorIn", csorIn);return this;};
	 	/** and csor_in is null */
 		public M csorInNull(){if(this.get("csorInNot")==null)this.put("csorInNot", "");this.put("csorIn", null);return this;};
 		/** not .... */
 		public M csorInNot(){this.put("csorInNot", "not");return this;};
		/** and csor_in >= ? */
		public M csorInMin(Object min){this.put("csorInMin", min);return this;};
		/** and csor_in <= ? */
		public M csorInMax(Object max){this.put("csorInMax", max);return this;};
		/** 支出        **/
		public M csorOut(Object csorOut){this.put("csorOut", csorOut);return this;};
	 	/** and csor_out is null */
 		public M csorOutNull(){if(this.get("csorOutNot")==null)this.put("csorOutNot", "");this.put("csorOut", null);return this;};
 		/** not .... */
 		public M csorOutNot(){this.put("csorOutNot", "not");return this;};
		/** and csor_out >= ? */
		public M csorOutMin(Object min){this.put("csorOutMin", min);return this;};
		/** and csor_out <= ? */
		public M csorOutMax(Object max){this.put("csorOutMax", max);return this;};
		/** 关联消费记录 [非空] [CsRecord]      **/
		public M csorRecord(Object csorRecord){this.put("csorRecord", csorRecord);return this;};
	 	/** and csor_record is null */
 		public M csorRecordNull(){if(this.get("csorRecordNot")==null)this.put("csorRecordNot", "");this.put("csorRecord", null);return this;};
 		/** not .... */
 		public M csorRecordNot(){this.put("csorRecordNot", "not");return this;};
		public M csorRecord$on(CsRecord.M value){
			this.put("CsRecord", value);
			this.put("csorRecord$on", value);
			return this;
		};	
		/** 关联会员  [CsMember]      **/
		public M csorMember(Object csorMember){this.put("csorMember", csorMember);return this;};
	 	/** and csor_member is null */
 		public M csorMemberNull(){if(this.get("csorMemberNot")==null)this.put("csorMemberNot", "");this.put("csorMember", null);return this;};
 		/** not .... */
 		public M csorMemberNot(){this.put("csorMemberNot", "not");return this;};
		public M csorMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csorMember$on", value);
			return this;
		};	
		/** 关联订单  [CsOrder]      **/
		public M csorOrder(Object csorOrder){this.put("csorOrder", csorOrder);return this;};
	 	/** and csor_order is null */
 		public M csorOrderNull(){if(this.get("csorOrderNot")==null)this.put("csorOrderNot", "");this.put("csorOrder", null);return this;};
 		/** not .... */
 		public M csorOrderNot(){this.put("csorOrderNot", "not");return this;};
		public M csorOrder$on(CsOrder.M value){
			this.put("CsOrder", value);
			this.put("csorOrder$on", value);
			return this;
		};	
		/** 其它关联     类型@id  CsTrouble,CsViolat,CsRepair,CsMaintain **/
		public M csorObject(Object csorObject){this.put("csorObject", csorObject);return this;};
	 	/** and csor_object is null */
 		public M csorObjectNull(){if(this.get("csorObjectNot")==null)this.put("csorObjectNot", "");this.put("csorObject", null);return this;};
 		/** not .... */
 		public M csorObjectNot(){this.put("csorObjectNot", "not");return this;};
		/** 发生时间 [非空]       **/
		public M csorDateTime(Object csorDateTime){this.put("csorDateTime", csorDateTime);return this;};
	 	/** and csor_date_time is null */
 		public M csorDateTimeNull(){if(this.get("csorDateTimeNot")==null)this.put("csorDateTimeNot", "");this.put("csorDateTime", null);return this;};
 		/** not .... */
 		public M csorDateTimeNot(){this.put("csorDateTimeNot", "not");return this;};
 		/** and csor_date_time >= ? */
 		public M csorDateTimeStart(Object start){this.put("csorDateTimeStart", start);return this;};			
 		/** and csor_date_time <= ? */
 		public M csorDateTimeEnd(Object end){this.put("csorDateTimeEnd", end);return this;};
		/** 结账流水  [CsCashRecord]      **/
		public M csorCashNo(Object csorCashNo){this.put("csorCashNo", csorCashNo);return this;};
	 	/** and csor_cash_no is null */
 		public M csorCashNoNull(){if(this.get("csorCashNoNot")==null)this.put("csorCashNoNot", "");this.put("csorCashNo", null);return this;};
 		/** not .... */
 		public M csorCashNoNot(){this.put("csorCashNoNot", "not");return this;};
		public M csorCashNo$on(CsCashRecord.M value){
			this.put("CsCashRecord", value);
			this.put("csorCashNo$on", value);
			return this;
		};	
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csorAdder(Object csorAdder){this.put("csorAdder", csorAdder);return this;};
	 	/** and csor_adder is null */
 		public M csorAdderNull(){if(this.get("csorAdderNot")==null)this.put("csorAdderNot", "");this.put("csorAdder", null);return this;};
 		/** not .... */
 		public M csorAdderNot(){this.put("csorAdderNot", "not");return this;};
		/** 状态 [非空]   1:正常 2:争议 0:无效     **/
		public M csorStatus(Object csorStatus){this.put("csorStatus", csorStatus);return this;};
	 	/** and csor_status is null */
 		public M csorStatusNull(){if(this.get("csorStatusNot")==null)this.put("csorStatusNot", "");this.put("csorStatus", null);return this;};
 		/** not .... */
 		public M csorStatusNot(){this.put("csorStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有收支记录 **/
		public @api List<CsOperRecord> list(Integer size){
			return getCsOperRecordList(this,size);
		}
		/** 获取收支记录分页 **/
		public @api Page<CsOperRecord> page(int page,int size){
			return getCsOperRecordPage(page, size , this);
		}
		/** 根据查询条件取收支记录 **/
		public @api CsOperRecord get(){
			return getCsOperRecord(this);
		}
		/** 获取收支记录数 **/
		public @api Long count(){
			return getCsOperRecordCount(this);
		}
		/** 获取收支记录表达式 **/
		public @api <T> T eval(String strEval){
			return getCsOperRecordEval(strEval,this);
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
			updateCsOperRecord(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csorId="csorId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csorHost="csorHost";
		/** 摘要 [非空]       **/
		public final static @type(String.class) @like String csorTitle="csorTitle";
		/** 金额类型 [非空]   0:保证金 1:优惠券     **/
		public final static @type(Short.class)  String csorMoneyType="csorMoneyType";
		/** 科目类型 [非空] [CsRecordSubject]      **/
		public final static @type(Long.class)  String csorRecordType="csorRecordType";
		/** 收入        **/
		public final static @type(Double.class)  String csorIn="csorIn";
		/** and csor_in >= ? */
		public final static @type(Double.class) String csorInMin="csorInMin";
		/** and csor_in <= ? */
		public final static @type(Double.class) String csorInMax="csorInMax";
		/** 支出        **/
		public final static @type(Double.class)  String csorOut="csorOut";
		/** and csor_out >= ? */
		public final static @type(Double.class) String csorOutMin="csorOutMin";
		/** and csor_out <= ? */
		public final static @type(Double.class) String csorOutMax="csorOutMax";
		/** 关联消费记录 [非空] [CsRecord]      **/
		public final static @type(Long.class)  String csorRecord="csorRecord";
		/** 关联会员  [CsMember]      **/
		public final static @type(Long.class)  String csorMember="csorMember";
		/** 关联订单  [CsOrder]      **/
		public final static @type(Long.class)  String csorOrder="csorOrder";
		/** 其它关联     类型@id  CsTrouble,CsViolat,CsRepair,CsMaintain **/
		public final static @type(String.class)  String csorObject="csorObject";
		/** 发生时间 [非空]       **/
		public final static @type(Date.class)  String csorDateTime="csorDateTime";
	 	/** and csor_date_time >= ? */
 		public final static @type(Date.class) String csorDateTimeStart="csorDateTimeStart";
 		/** and csor_date_time <= ? */
 		public final static @type(Date.class) String csorDateTimeEnd="csorDateTimeEnd";
		/** 结账流水  [CsCashRecord]      **/
		public final static @type(Long.class)  String csorCashNo="csorCashNo";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csorAdder="csorAdder";
		/** 状态 [非空]   1:正常 2:争议 0:无效     **/
		public final static @type(Short.class)  String csorStatus="csorStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csorId="csor_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csorHost="csor_host";
		/** 摘要 [非空]       **/
		public final static String csorTitle="csor_title";
		/** 金额类型 [非空]   0:保证金 1:优惠券     **/
		public final static String csorMoneyType="csor_money_type";
		/** 科目类型 [非空] [CsRecordSubject]      **/
		public final static String csorRecordType="csor_record_type";
		/** 收入        **/
		public final static String csorIn="csor_in";
		/** 支出        **/
		public final static String csorOut="csor_out";
		/** 关联消费记录 [非空] [CsRecord]      **/
		public final static String csorRecord="csor_record";
		/** 关联会员  [CsMember]      **/
		public final static String csorMember="csor_member";
		/** 关联订单  [CsOrder]      **/
		public final static String csorOrder="csor_order";
		/** 其它关联     类型@id  CsTrouble,CsViolat,CsRepair,CsMaintain **/
		public final static String csorObject="csor_object";
		/** 发生时间 [非空]       **/
		public final static String csorDateTime="csor_date_time";
		/** 结账流水  [CsCashRecord]      **/
		public final static String csorCashNo="csor_cash_no";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csorAdder="csor_adder";
		/** 状态 [非空]   1:正常 2:争议 0:无效     **/
		public final static String csorStatus="csor_status";
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
				$.Set(name,CsOperRecord.getCsOperRecord(params));
			else
				$.Set(name,CsOperRecord.getCsOperRecordList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取收支记录数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsOperRecord) $.GetRequest("CsOperRecord$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsOperRecord.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsOperRecord.getCsOperRecord(params);
			else
				value = CsOperRecord.getCsOperRecordList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsOperRecord.Get($.add(CsOperRecord.F.csorId,param));
		else if(!$.empty(param.toString()))
			value = CsOperRecord.get(Long.valueOf(param.toString()));
		$.SetRequest("CsOperRecord$"+param.hashCode(), value);
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
	public void mergeSet(CsOperRecord old){
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