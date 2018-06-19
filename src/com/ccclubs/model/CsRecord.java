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

@namespace("finance/record")
public @caption("消费记录") @table("cs_record") class CsRecord implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csr_id")   @primary  @note("  ") Long csrId;// 主键 非空     
	private @caption("城市") @column("csr_host")    @relate("$csrHost") @RelateClass(SrvHost.class)  @note("  ") Long csrHost;// 非空     
 	private SrvHost $csrHost;//关联对象[运营城市]
	private @caption("摘要") @column("csr_remark")    @note("  ") String csrRemark;//     
	private @caption("消费会员") @column("csr_member")    @relate("$csrMember") @RelateClass(CsMember.class)  @note("  ") Long csrMember;// 非空    ${basePath}${proname}/user/member_inquire.do 
 	private CsMember $csrMember;//关联对象[会员帐号]
	private @caption("金额类型") @column("csr_money_type")    @note(" 0:保证金 1:现金券  ") Short csrMoneyType;// 非空 0:保证金 1:现金券     
	private @caption("科目类型") @column("csr_record_subject")    @relate("$csrRecordSubject") @RelateClass(CsRecordSubject.class)  @note("  ") Long csrRecordSubject;// 非空     
 	private CsRecordSubject $csrRecordSubject;//关联对象[消费科目]
	private @caption("消费类型") @column("csr_record_type")    @relate("$csrRecordType") @RelateClass(CsRecordType.class)  @note("  ") Long csrRecordType;// 非空     
 	private CsRecordType $csrRecordType;//关联对象[消费类型]
	private @caption("数额") @column("csr_amount")    @note("  ") Double csrAmount;// 非空     
	private @caption("余额") @column("csr_remain")    @note("  帐户余额或现金券余额或积分余量") Double csrRemain;// 非空  帐户余额或现金券余额或积分余量   
	private @caption("时间") @column("csr_add_time")    @note("  ") Date csrAddTime;// 非空     
	private @caption("关联订单") @column("csr_order")    @relate("$csrOrder") @RelateClass(CsOrder.class)  @note("  ") Long csrOrder;//     
 	private CsOrder $csrOrder;//关联对象[系统订单]
	private @caption("关联编号") @column("csr_relate")    @note("  支付宝充值？等") Long csrRelate;//  支付宝充值？等   
	private @caption("其它关联") @column("csr_object")    @note("  ") String csrObject;//     存储格式:类名@ID,其中类名包括
	private Object $csrObject;//泛关联对象，对象类名由csrObject字段中标明
	private @caption("操作人") @column("csr_editor")    @relate("$csrEditor") @RelateClass(SrvUser.class)  @note("  ") Long csrEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csrEditor;//关联对象[用户]
	
	//默认构造函数
	public CsRecord(){
	
	}
	
	//主键构造函数
	public CsRecord(Long id){
		this.csrId = id;
	}
	
	/**所有字段构造函数 CsRecord(csrHost,csrRemark,csrMember,csrMoneyType,csrRecordSubject,csrRecordType,csrAmount,csrRemain,csrAddTime,csrOrder,csrRelate,csrObject,csrEditor)
	 CsRecord(
	 	$.getLong("csrHost")//城市 [非空]
	 	,$.getString("csrRemark")//摘要
	 	,$.getLong("csrMember")//消费会员 [非空]
	 	,$.getShort("csrMoneyType")//金额类型 [非空]
	 	,$.getLong("csrRecordSubject")//科目类型 [非空]
	 	,$.getLong("csrRecordType")//消费类型 [非空]
	 	,$.getDouble("csrAmount")//数额 [非空]
	 	,$.getDouble("csrRemain")//余额 [非空]
	 	,$.getDate("csrAddTime")//时间 [非空]
	 	,$.getLong("csrOrder")//关联订单
	 	,$.getLong("csrRelate")//关联编号
	 	,$.getString("csrObject")//其它关联
	 	,$.getLong("csrEditor")//操作人 [非空]
	 )
	**/
	public CsRecord(Long csrHost,String csrRemark,Long csrMember,Short csrMoneyType,Long csrRecordSubject,Long csrRecordType,Double csrAmount,Double csrRemain,Date csrAddTime,Long csrOrder,Long csrRelate,String csrObject,Long csrEditor){
		this.csrHost=csrHost;
		this.csrRemark=csrRemark;
		this.csrMember=csrMember;
		this.csrMoneyType=csrMoneyType;
		this.csrRecordSubject=csrRecordSubject;
		this.csrRecordType=csrRecordType;
		this.csrAmount=csrAmount;
		this.csrRemain=csrRemain;
		this.csrAddTime=csrAddTime;
		this.csrOrder=csrOrder;
		this.csrRelate=csrRelate;
		this.csrObject=csrObject;
		this.csrEditor=csrEditor;
	}
	
	//设置非空字段
	public CsRecord setNotNull(Long csrHost,Long csrMember,Short csrMoneyType,Long csrRecordSubject,Long csrRecordType,Double csrAmount,Double csrRemain,Date csrAddTime,Long csrEditor){
		this.csrHost=csrHost;
		this.csrMember=csrMember;
		this.csrMoneyType=csrMoneyType;
		this.csrRecordSubject=csrRecordSubject;
		this.csrRecordType=csrRecordType;
		this.csrAmount=csrAmount;
		this.csrRemain=csrRemain;
		this.csrAddTime=csrAddTime;
		this.csrEditor=csrEditor;
		return this;
	}
	/** 编号 [非空]       **/
	public CsRecord csrId(Long csrId){
		this.csrId = csrId;
		this.setSeted(F.csrId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsRecord csrHost(Long csrHost){
		this.csrHost = csrHost;
		this.setSeted(F.csrHost);
		return this;
	}
	/** 摘要        **/
	public CsRecord csrRemark(String csrRemark){
		this.csrRemark = csrRemark;
		this.setSeted(F.csrRemark);
		return this;
	}
	/** 消费会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
	public CsRecord csrMember(Long csrMember){
		this.csrMember = csrMember;
		this.setSeted(F.csrMember);
		return this;
	}
	/** 金额类型 [非空]   0:保证金 1:现金券     **/
	public CsRecord csrMoneyType(Short csrMoneyType){
		this.csrMoneyType = csrMoneyType;
		this.setSeted(F.csrMoneyType);
		return this;
	}
	/** 科目类型 [非空] [CsRecordSubject]      **/
	public CsRecord csrRecordSubject(Long csrRecordSubject){
		this.csrRecordSubject = csrRecordSubject;
		this.setSeted(F.csrRecordSubject);
		return this;
	}
	/** 消费类型 [非空] [CsRecordType]      **/
	public CsRecord csrRecordType(Long csrRecordType){
		this.csrRecordType = csrRecordType;
		this.setSeted(F.csrRecordType);
		return this;
	}
	/** 数额 [非空]       **/
	public CsRecord csrAmount(Double csrAmount){
		this.csrAmount = csrAmount;
		this.setSeted(F.csrAmount);
		return this;
	}
	/** 余额 [非空]    帐户余额或现金券余额或积分余量   **/
	public CsRecord csrRemain(Double csrRemain){
		this.csrRemain = csrRemain;
		this.setSeted(F.csrRemain);
		return this;
	}
	/** 时间 [非空]       **/
	public CsRecord csrAddTime(Date csrAddTime){
		this.csrAddTime = csrAddTime;
		this.setSeted(F.csrAddTime);
		return this;
	}
	/** 关联订单  [CsOrder]      **/
	public CsRecord csrOrder(Long csrOrder){
		this.csrOrder = csrOrder;
		this.setSeted(F.csrOrder);
		return this;
	}
	/** 关联编号     支付宝充值？等   **/
	public CsRecord csrRelate(Long csrRelate){
		this.csrRelate = csrRelate;
		this.setSeted(F.csrRelate);
		return this;
	}
	/** 其它关联        **/
	public CsRecord csrObject(String csrObject){
		this.csrObject = csrObject;
		this.setSeted(F.csrObject);
		return this;
	}
	/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsRecord csrEditor(Long csrEditor){
		this.csrEditor = csrEditor;
		this.setSeted(F.csrEditor);
		return this;
	}
	
	
	//克隆对象
	public CsRecord clone(){
		CsRecord clone = new CsRecord();
		clone.csrHost=this.csrHost;
		clone.csrMember=this.csrMember;
		clone.csrMoneyType=this.csrMoneyType;
		clone.csrRecordSubject=this.csrRecordSubject;
		clone.csrRecordType=this.csrRecordType;
		clone.csrAmount=this.csrAmount;
		clone.csrRemain=this.csrRemain;
		clone.csrAddTime=this.csrAddTime;
		clone.csrEditor=this.csrEditor;
		return clone;
	}
	
	
	/**
	 * 根据ID取消费记录
	 * @param id
	 * @return
	 */
	public static @api CsRecord get(Long id){		
		return getCsRecordById(id);
	}
	/**
	 * 获取所有消费记录
	 * @return
	 */
	public static @api List<CsRecord> list(Map params,Integer size){
		return getCsRecordList(params,size);
	}
	/**
	 * 获取消费记录分页
	 * @return
	 */
	public static @api Page<CsRecord> page(int page,int size,Map params){
		return getCsRecordPage(page, size , params);
	}
	/**
	 * 根据查询条件取消费记录
	 * @param params
	 * @return
	 */
	public static @api CsRecord Get(Map params){
		return getCsRecord(params);
	}
	/**
	 * 获取消费记录数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsRecordCount(params);
	}
	/**
	 * 获取消费记录数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsRecordEval(eval,params);
	}
	
	/**
	 * 根据ID取消费记录
	 * @param id
	 * @return
	 */
	public static @api CsRecord getCsRecordById(Long id){		
		CsRecord csRecord = (CsRecord) $.GetRequest("CsRecord$"+id);
		if(csRecord!=null)
			return csRecord;
		com.ccclubs.service.admin.ICsRecordService csRecordService = $.GetSpringBean("csRecordService");		
		return csRecordService.getCsRecordById(id);
	}
	
	
	/**
	 * 根据ID取消费记录的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsRecord.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsRecord csRecord = get(id);
		if(csRecord!=null){
			String strValue = csRecord.getCsrId$();
			if(!"CsrId".equals("CsrId"))
				strValue+="("+csRecord.getCsrId$()+")";
			if(!"CsrId".equals("CsrRemark"))
				strValue+="("+csRecord.getCsrRemark$()+")";
			MemCache.setValue(CsRecord.class, id ,strValue);
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
		if(!"CsrId".equals("CsrRemark"))
			keyValue+="("+this.getCsrRemark$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有消费记录
	 * @return
	 */
	public static @api List<CsRecord> getCsRecordList(Map params,Integer size){
		com.ccclubs.service.admin.ICsRecordService csRecordService = $.GetSpringBean("csRecordService");
		return csRecordService.getCsRecordList(params, size);
	}
	
	/**
	 * 获取消费记录分页
	 * @return
	 */
	public static @api Page<CsRecord> getCsRecordPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsRecordService csRecordService = $.GetSpringBean("csRecordService");
		return csRecordService.getCsRecordPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取消费记录
	 * @param params
	 * @return
	 */
	public static @api CsRecord getCsRecord(Map params){
		com.ccclubs.service.admin.ICsRecordService csRecordService = $.GetSpringBean("csRecordService");
		return csRecordService.getCsRecord(params);
	}
	
	/**
	 * 获取消费记录数
	 * @return
	 */
	public static @api Long getCsRecordCount(Map params){
		com.ccclubs.service.admin.ICsRecordService csRecordService = $.GetSpringBean("csRecordService");
		return csRecordService.getCsRecordCount(params);
	}
		
		
	/**
	 * 获取消费记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsRecordEval(String eval,Map params){
		com.ccclubs.service.admin.ICsRecordService csRecordService = $.GetSpringBean("csRecordService");
		return csRecordService.getCsRecordEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsRecord(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsRecordService csRecordService = $.GetSpringBean("csRecordService");
		csRecordService.updateCsRecordByConfirm(set, where);
	}
	
	
	/**
	 * 保存消费记录对象
	 * @param params
	 * @return
	 */
	public CsRecord save(){
		com.ccclubs.service.admin.ICsRecordService csRecordService = $.GetSpringBean("csRecordService");
		if(this.getCsrId()!=null)
			csRecordService.updateCsRecord(this);
		else
			return csRecordService.saveCsRecord(this);
		return this;
	}
	
	
	/**
	 * 更新消费记录对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsRecordService csRecordService = $.GetSpringBean("csRecordService");
		csRecordService.updateCsRecord$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsRecordService csRecordService = $.GetSpringBean("csRecordService");
			csRecordService.deleteCsRecordById(this.getCsrId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsRecordService csRecordService = $.GetSpringBean("csRecordService");
		return csRecordService.executeTransaction(function);
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
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsrHost(){
		return this.csrHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsrHost$(){
		String strValue="";
		if(this.getCsrHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsrHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
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
	/*******************************摘要**********************************/	
	/**
	* 摘要       
	**/
	public String getCsrRemark(){
		return this.csrRemark;
	}
	/**
	* 获取摘要格式化(toString)
	**/
	public String getCsrRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsrRemark());
	 	 return strValue;
	}
	/**
	* 摘要       
	**/
	public void setCsrRemark(String csrRemark){
		this.csrRemark = csrRemark;
		this.setSeted(F.csrRemark);
	}
	/*******************************消费会员**********************************/	
	/**
	* 消费会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public Long getCsrMember(){
		return this.csrMember;
	}
	/**
	* 获取消费会员格式化(toString)
	**/
	public String getCsrMember$(){
		String strValue="";
		if(this.getCsrMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsrMember()));
		}			
	 	 return strValue;
	}
	/**
	* 消费会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
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
	/*******************************金额类型**********************************/	
	/**
	* 金额类型 [非空]   0:保证金 1:现金券    
	**/
	public Short getCsrMoneyType(){
		return this.csrMoneyType;
	}
	/**
	* 获取金额类型格式化(toString)
	**/
	public String getCsrMoneyType$(){
		String strValue="";
		 if($.equals($.str(this.getCsrMoneyType()),"0"))
			strValue=$.str("保证金");		 
		 if($.equals($.str(this.getCsrMoneyType()),"1"))
			strValue=$.str("现金券");		 
	 	 return strValue;
	}
	/**
	* 金额类型 [非空]   0:保证金 1:现金券    
	**/
	public void setCsrMoneyType(Short csrMoneyType){
		this.csrMoneyType = csrMoneyType;
		this.setSeted(F.csrMoneyType);
	}
	/*******************************科目类型**********************************/	
	/**
	* 科目类型 [非空] [CsRecordSubject]     
	**/
	public Long getCsrRecordSubject(){
		return this.csrRecordSubject;
	}
	/**
	* 获取科目类型格式化(toString)
	**/
	public String getCsrRecordSubject$(){
		String strValue="";
		if(this.getCsrRecordSubject()!=null){
				strValue+=$.str(CsRecordSubject.getKeyValue(this.getCsrRecordSubject()));
		}			
	 	 return strValue;
	}
	/**
	* 科目类型 [非空] [CsRecordSubject]     
	**/
	public void setCsrRecordSubject(Long csrRecordSubject){
		this.csrRecordSubject = csrRecordSubject;
		this.setSeted(F.csrRecordSubject);
	}
	/**
	* 获取关联对象[消费科目]
	**/	 			
 	public CsRecordSubject get$csrRecordSubject(){
 		com.ccclubs.model.CsRecordSubject csRecordSubject = (com.ccclubs.model.CsRecordSubject) $.GetRequest("CsRecordSubject$"+this.getCsrRecordSubject());
 		if(csRecordSubject!=null)
			return csRecordSubject;
		if(this.getCsrRecordSubject()!=null){
 			csRecordSubject = CsRecordSubject.get(this.getCsrRecordSubject());
 		}
 		$.SetRequest("CsRecordSubject$"+this.getCsrRecordSubject(), csRecordSubject);
	 	return csRecordSubject;
	}
	/*******************************消费类型**********************************/	
	/**
	* 消费类型 [非空] [CsRecordType]     
	**/
	public Long getCsrRecordType(){
		return this.csrRecordType;
	}
	/**
	* 获取消费类型格式化(toString)
	**/
	public String getCsrRecordType$(){
		String strValue="";
		if(this.getCsrRecordType()!=null){
				strValue+=$.str(CsRecordType.getKeyValue(this.getCsrRecordType()));
		}			
	 	 return strValue;
	}
	/**
	* 消费类型 [非空] [CsRecordType]     
	**/
	public void setCsrRecordType(Long csrRecordType){
		this.csrRecordType = csrRecordType;
		this.setSeted(F.csrRecordType);
	}
	/**
	* 获取关联对象[消费类型]
	**/	 			
 	public CsRecordType get$csrRecordType(){
 		com.ccclubs.model.CsRecordType csRecordType = (com.ccclubs.model.CsRecordType) $.GetRequest("CsRecordType$"+this.getCsrRecordType());
 		if(csRecordType!=null)
			return csRecordType;
		if(this.getCsrRecordType()!=null){
 			csRecordType = CsRecordType.get(this.getCsrRecordType());
 		}
 		$.SetRequest("CsRecordType$"+this.getCsrRecordType(), csRecordType);
	 	return csRecordType;
	}
	/*******************************数额**********************************/	
	/**
	* 数额 [非空]      
	**/
	public Double getCsrAmount(){
		return this.csrAmount;
	}
	/**
	* 获取数额格式化(toString)
	**/
	public String getCsrAmount$(){
		String strValue="";
		 strValue=$.str(this.getCsrAmount());	
	 	 return strValue;
	}
	/**
	* 数额 [非空]      
	**/
	public void setCsrAmount(Double csrAmount){
		this.csrAmount = csrAmount;
		this.setSeted(F.csrAmount);
	}
	/*******************************余额**********************************/	
	/**
	* 余额 [非空]    帐户余额或现金券余额或积分余量  
	**/
	public Double getCsrRemain(){
		return this.csrRemain;
	}
	/**
	* 获取余额格式化(toString)
	**/
	public String getCsrRemain$(){
		String strValue="";
		 strValue=$.str(this.getCsrRemain());	
	 	 return strValue;
	}
	/**
	* 余额 [非空]    帐户余额或现金券余额或积分余量  
	**/
	public void setCsrRemain(Double csrRemain){
		this.csrRemain = csrRemain;
		this.setSeted(F.csrRemain);
	}
	/*******************************时间**********************************/	
	/**
	* 时间 [非空]      
	**/
	public Date getCsrAddTime(){
		return this.csrAddTime;
	}
	/**
	* 获取时间格式化(toString)
	**/
	public String getCsrAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsrAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 时间 [非空]      
	**/
	public void setCsrAddTime(Date csrAddTime){
		this.csrAddTime = csrAddTime;
		this.setSeted(F.csrAddTime);
	}
	/*******************************关联订单**********************************/	
	/**
	* 关联订单  [CsOrder]     
	**/
	public Long getCsrOrder(){
		return this.csrOrder;
	}
	/**
	* 获取关联订单格式化(toString)
	**/
	public String getCsrOrder$(){
		String strValue="";
		if(this.getCsrOrder()!=null){
				strValue+=$.str(CsOrder.getKeyValue(this.getCsrOrder()));
		}			
	 	 return strValue;
	}
	/**
	* 关联订单  [CsOrder]     
	**/
	public void setCsrOrder(Long csrOrder){
		this.csrOrder = csrOrder;
		this.setSeted(F.csrOrder);
	}
	/**
	* 获取关联对象[系统订单]
	**/	 			
 	public CsOrder get$csrOrder(){
 		com.ccclubs.model.CsOrder csOrder = (com.ccclubs.model.CsOrder) $.GetRequest("CsOrder$"+this.getCsrOrder());
 		if(csOrder!=null)
			return csOrder;
		if(this.getCsrOrder()!=null){
 			csOrder = CsOrder.get(this.getCsrOrder());
 		}
 		$.SetRequest("CsOrder$"+this.getCsrOrder(), csOrder);
	 	return csOrder;
	}
	/*******************************关联编号**********************************/	
	/**
	* 关联编号     支付宝充值？等  
	**/
	public Long getCsrRelate(){
		return this.csrRelate;
	}
	/**
	* 获取关联编号格式化(toString)
	**/
	public String getCsrRelate$(){
		String strValue="";
		 strValue=$.str(this.getCsrRelate());
	 	 return strValue;
	}
	/**
	* 关联编号     支付宝充值？等  
	**/
	public void setCsrRelate(Long csrRelate){
		this.csrRelate = csrRelate;
		this.setSeted(F.csrRelate);
	}
	/*******************************其它关联**********************************/	
	/**
	* 其它关联       
	**/
	public String getCsrObject(){
		return this.csrObject;
	}
	/**
	* 获取其它关联格式化(toString)
	**/
	public String getCsrObject$(){
		String strValue="";
	 	 Object object = this.get$csrObject();
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
	* 其它关联       
	**/
	public void setCsrObject(String csrObject){
		this.csrObject = csrObject;
		this.setSeted(F.csrObject);
	}
	/**
	* 泛关联对象，对象类名由csrObject字段中标明
	**/	 			
 	public <T> T get$csrObject(){
		if($.empty(this.csrObject))
 			return null;
 		try{
	 		String ClassName = this.csrObject.split("@")[0];
	 		Long id = Long.parseLong(this.csrObject.split("@")[1]);
	 		Class<?> cls = Class.forName("com.ccclubs.model."+ClassName);
	 		return (T)cls.getMethod("get", Long.class).invoke(null, id);	
 		}catch(Exception ex){
 			ex.printStackTrace();
 			return null;
 		}
	}
	/*******************************操作人**********************************/	
	/**
	* 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsrEditor(){
		return this.csrEditor;
	}
	/**
	* 获取操作人格式化(toString)
	**/
	public String getCsrEditor$(){
		String strValue="";
		if(this.getCsrEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsrEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsrEditor(Long csrEditor){
		this.csrEditor = csrEditor;
		this.setSeted(F.csrEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csrEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsrEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsrEditor()!=null){
 			srvUser = SrvUser.get(this.getCsrEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCsrEditor(), srvUser);
	 	return srvUser;
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsRecord.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRecord.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRecord.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsRecord.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsRecord.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRecord.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRecord.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsRecord.this);
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
		/** 城市 [非空] [SrvHost]      **/
		public M csrHost(Object csrHost){this.put("csrHost", csrHost);return this;};
	 	/** and csr_host is null */
 		public M csrHostNull(){if(this.get("csrHostNot")==null)this.put("csrHostNot", "");this.put("csrHost", null);return this;};
 		/** not .... */
 		public M csrHostNot(){this.put("csrHostNot", "not");return this;};
		/** 摘要        **/
		public M csrRemark(Object csrRemark){this.put("csrRemark", csrRemark);return this;};
	 	/** and csr_remark is null */
 		public M csrRemarkNull(){if(this.get("csrRemarkNot")==null)this.put("csrRemarkNot", "");this.put("csrRemark", null);return this;};
 		/** not .... */
 		public M csrRemarkNot(){this.put("csrRemarkNot", "not");return this;};
		/** 消费会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
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
		/** 金额类型 [非空]   0:保证金 1:现金券     **/
		public M csrMoneyType(Object csrMoneyType){this.put("csrMoneyType", csrMoneyType);return this;};
	 	/** and csr_money_type is null */
 		public M csrMoneyTypeNull(){if(this.get("csrMoneyTypeNot")==null)this.put("csrMoneyTypeNot", "");this.put("csrMoneyType", null);return this;};
 		/** not .... */
 		public M csrMoneyTypeNot(){this.put("csrMoneyTypeNot", "not");return this;};
		/** 科目类型 [非空] [CsRecordSubject]      **/
		public M csrRecordSubject(Object csrRecordSubject){this.put("csrRecordSubject", csrRecordSubject);return this;};
	 	/** and csr_record_subject is null */
 		public M csrRecordSubjectNull(){if(this.get("csrRecordSubjectNot")==null)this.put("csrRecordSubjectNot", "");this.put("csrRecordSubject", null);return this;};
 		/** not .... */
 		public M csrRecordSubjectNot(){this.put("csrRecordSubjectNot", "not");return this;};
		public M csrRecordSubject$on(CsRecordSubject.M value){
			this.put("CsRecordSubject", value);
			this.put("csrRecordSubject$on", value);
			return this;
		};	
		/** 消费类型 [非空] [CsRecordType]      **/
		public M csrRecordType(Object csrRecordType){this.put("csrRecordType", csrRecordType);return this;};
	 	/** and csr_record_type is null */
 		public M csrRecordTypeNull(){if(this.get("csrRecordTypeNot")==null)this.put("csrRecordTypeNot", "");this.put("csrRecordType", null);return this;};
 		/** not .... */
 		public M csrRecordTypeNot(){this.put("csrRecordTypeNot", "not");return this;};
		public M csrRecordType$on(CsRecordType.M value){
			this.put("CsRecordType", value);
			this.put("csrRecordType$on", value);
			return this;
		};	
		/** 数额 [非空]       **/
		public M csrAmount(Object csrAmount){this.put("csrAmount", csrAmount);return this;};
	 	/** and csr_amount is null */
 		public M csrAmountNull(){if(this.get("csrAmountNot")==null)this.put("csrAmountNot", "");this.put("csrAmount", null);return this;};
 		/** not .... */
 		public M csrAmountNot(){this.put("csrAmountNot", "not");return this;};
		/** and csr_amount >= ? */
		public M csrAmountMin(Object min){this.put("csrAmountMin", min);return this;};
		/** and csr_amount <= ? */
		public M csrAmountMax(Object max){this.put("csrAmountMax", max);return this;};
		/** 余额 [非空]    帐户余额或现金券余额或积分余量   **/
		public M csrRemain(Object csrRemain){this.put("csrRemain", csrRemain);return this;};
	 	/** and csr_remain is null */
 		public M csrRemainNull(){if(this.get("csrRemainNot")==null)this.put("csrRemainNot", "");this.put("csrRemain", null);return this;};
 		/** not .... */
 		public M csrRemainNot(){this.put("csrRemainNot", "not");return this;};
		/** and csr_remain >= ? */
		public M csrRemainMin(Object min){this.put("csrRemainMin", min);return this;};
		/** and csr_remain <= ? */
		public M csrRemainMax(Object max){this.put("csrRemainMax", max);return this;};
		/** 时间 [非空]       **/
		public M csrAddTime(Object csrAddTime){this.put("csrAddTime", csrAddTime);return this;};
	 	/** and csr_add_time is null */
 		public M csrAddTimeNull(){if(this.get("csrAddTimeNot")==null)this.put("csrAddTimeNot", "");this.put("csrAddTime", null);return this;};
 		/** not .... */
 		public M csrAddTimeNot(){this.put("csrAddTimeNot", "not");return this;};
 		/** and csr_add_time >= ? */
 		public M csrAddTimeStart(Object start){this.put("csrAddTimeStart", start);return this;};			
 		/** and csr_add_time <= ? */
 		public M csrAddTimeEnd(Object end){this.put("csrAddTimeEnd", end);return this;};
		/** 关联订单  [CsOrder]      **/
		public M csrOrder(Object csrOrder){this.put("csrOrder", csrOrder);return this;};
	 	/** and csr_order is null */
 		public M csrOrderNull(){if(this.get("csrOrderNot")==null)this.put("csrOrderNot", "");this.put("csrOrder", null);return this;};
 		/** not .... */
 		public M csrOrderNot(){this.put("csrOrderNot", "not");return this;};
		public M csrOrder$on(CsOrder.M value){
			this.put("CsOrder", value);
			this.put("csrOrder$on", value);
			return this;
		};	
		/** 关联编号     支付宝充值？等   **/
		public M csrRelate(Object csrRelate){this.put("csrRelate", csrRelate);return this;};
	 	/** and csr_relate is null */
 		public M csrRelateNull(){if(this.get("csrRelateNot")==null)this.put("csrRelateNot", "");this.put("csrRelate", null);return this;};
 		/** not .... */
 		public M csrRelateNot(){this.put("csrRelateNot", "not");return this;};
		/** and csr_relate >= ? */
		public M csrRelateMin(Object min){this.put("csrRelateMin", min);return this;};
		/** and csr_relate <= ? */
		public M csrRelateMax(Object max){this.put("csrRelateMax", max);return this;};
		/** 其它关联        **/
		public M csrObject(Object csrObject){this.put("csrObject", csrObject);return this;};
	 	/** and csr_object is null */
 		public M csrObjectNull(){if(this.get("csrObjectNot")==null)this.put("csrObjectNot", "");this.put("csrObject", null);return this;};
 		/** not .... */
 		public M csrObjectNot(){this.put("csrObjectNot", "not");return this;};
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csrEditor(Object csrEditor){this.put("csrEditor", csrEditor);return this;};
	 	/** and csr_editor is null */
 		public M csrEditorNull(){if(this.get("csrEditorNot")==null)this.put("csrEditorNot", "");this.put("csrEditor", null);return this;};
 		/** not .... */
 		public M csrEditorNot(){this.put("csrEditorNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有消费记录 **/
		public @api List<CsRecord> list(Integer size){
			return getCsRecordList(this,size);
		}
		/** 获取消费记录分页 **/
		public @api Page<CsRecord> page(int page,int size){
			return getCsRecordPage(page, size , this);
		}
		/** 根据查询条件取消费记录 **/
		public @api CsRecord get(){
			return getCsRecord(this);
		}
		/** 获取消费记录数 **/
		public @api Long count(){
			return getCsRecordCount(this);
		}
		/** 获取消费记录表达式 **/
		public @api <T> T eval(String strEval){
			return getCsRecordEval(strEval,this);
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
			updateCsRecord(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csrId="csrId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csrHost="csrHost";
		/** 摘要        **/
		public final static @type(String.class) @like String csrRemark="csrRemark";
		/** 消费会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static @type(Long.class)  String csrMember="csrMember";
		/** 金额类型 [非空]   0:保证金 1:现金券     **/
		public final static @type(Short.class)  String csrMoneyType="csrMoneyType";
		/** 科目类型 [非空] [CsRecordSubject]      **/
		public final static @type(Long.class)  String csrRecordSubject="csrRecordSubject";
		/** 消费类型 [非空] [CsRecordType]      **/
		public final static @type(Long.class)  String csrRecordType="csrRecordType";
		/** 数额 [非空]       **/
		public final static @type(Double.class)  String csrAmount="csrAmount";
		/** and csr_amount >= ? */
		public final static @type(Double.class) String csrAmountMin="csrAmountMin";
		/** and csr_amount <= ? */
		public final static @type(Double.class) String csrAmountMax="csrAmountMax";
		/** 余额 [非空]    帐户余额或现金券余额或积分余量   **/
		public final static @type(Double.class)  String csrRemain="csrRemain";
		/** and csr_remain >= ? */
		public final static @type(Double.class) String csrRemainMin="csrRemainMin";
		/** and csr_remain <= ? */
		public final static @type(Double.class) String csrRemainMax="csrRemainMax";
		/** 时间 [非空]       **/
		public final static @type(Date.class)  String csrAddTime="csrAddTime";
	 	/** and csr_add_time >= ? */
 		public final static @type(Date.class) String csrAddTimeStart="csrAddTimeStart";
 		/** and csr_add_time <= ? */
 		public final static @type(Date.class) String csrAddTimeEnd="csrAddTimeEnd";
		/** 关联订单  [CsOrder]      **/
		public final static @type(Long.class)  String csrOrder="csrOrder";
		/** 关联编号     支付宝充值？等   **/
		public final static @type(Long.class)  String csrRelate="csrRelate";
		/** and csr_relate >= ? */
		public final static @type(Long.class) String csrRelateMin="csrRelateMin";
		/** and csr_relate <= ? */
		public final static @type(Long.class) String csrRelateMax="csrRelateMax";
		/** 其它关联        **/
		public final static @type(String.class)  String csrObject="csrObject";
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csrEditor="csrEditor";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csrId="csr_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csrHost="csr_host";
		/** 摘要        **/
		public final static String csrRemark="csr_remark";
		/** 消费会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static String csrMember="csr_member";
		/** 金额类型 [非空]   0:保证金 1:现金券     **/
		public final static String csrMoneyType="csr_money_type";
		/** 科目类型 [非空] [CsRecordSubject]      **/
		public final static String csrRecordSubject="csr_record_subject";
		/** 消费类型 [非空] [CsRecordType]      **/
		public final static String csrRecordType="csr_record_type";
		/** 数额 [非空]       **/
		public final static String csrAmount="csr_amount";
		/** 余额 [非空]    帐户余额或现金券余额或积分余量   **/
		public final static String csrRemain="csr_remain";
		/** 时间 [非空]       **/
		public final static String csrAddTime="csr_add_time";
		/** 关联订单  [CsOrder]      **/
		public final static String csrOrder="csr_order";
		/** 关联编号     支付宝充值？等   **/
		public final static String csrRelate="csr_relate";
		/** 其它关联        **/
		public final static String csrObject="csr_object";
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csrEditor="csr_editor";
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
				$.Set(name,CsRecord.getCsRecord(params));
			else
				$.Set(name,CsRecord.getCsRecordList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取消费记录数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsRecord) $.GetRequest("CsRecord$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsRecord.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsRecord.getCsRecord(params);
			else
				value = CsRecord.getCsRecordList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsRecord.Get($.add(CsRecord.F.csrId,param));
		else if(!$.empty(param.toString()))
			value = CsRecord.get(Long.valueOf(param.toString()));
		$.SetRequest("CsRecord$"+param.hashCode(), value);
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
	public void mergeSet(CsRecord old){
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