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

@namespace("finance/invoice")
public @caption("发票记录") @table("cs_invoice") class CsInvoice implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csi_id")   @primary  @note("  ") Long csiId;// 主键 非空     
	private @caption("城市") @column("csi_host")    @relate("$csiHost") @RelateClass(SrvHost.class)  @note("  ") Long csiHost;// 非空     
 	private SrvHost $csiHost;//关联对象[运营城市]
	private @caption("抬头名称") @column("csi_title")    @note("  ") String csiTitle;// 非空     
	private @caption("开票内容") @column("csi_content")    @relate("$csiContent") @RelateClass(SrvProperty.class)  @note(" invoice_content:发票开票内容  ") Long csiContent;// 非空 invoice_content:发票开票内容     
 	private SrvProperty $csiContent;//关联对象[系统属性]
	private @caption("抬头类型") @column("csi_type")    @note(" 0:个人 1:公司  ") Short csiType;// 非空 0:个人 1:公司     
	private @caption("税号") @column("csi_tariff_no")    @note("  ") String csiTariffNo;//     
	private @caption("开票额") @column("csi_money")    @note("  ") Double csiMoney;//     
	private @caption("所属会员") @column("csi_member")    @relate("$csiMember") @RelateClass(CsMember.class)  @note("  ") Long csiMember;// 非空     
 	private CsMember $csiMember;//关联对象[会员帐号]
	private @caption("寄送方式") @column("csi_send_type")    @note(" 0:快递 1:上门取件  ") Short csiSendType;// 非空 0:快递 1:上门取件     
	private @caption("寄送地址") @column("csi_address")    @relate("$csiAddress") @RelateClass(CsAddress.class)  @note(" {csiMember}:csaMember  ") Long csiAddress;// {csiMember}:csaMember     
 	private CsAddress $csiAddress;//关联对象[会员地址]
	private @caption("审核人") @column("csi_checker")    @relate("$csiChecker") @RelateClass(SrvUser.class)  @note("  ") Long csiChecker;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csiChecker;//关联对象[用户]
	private @caption("开票人") @column("csi_editor")    @relate("$csiEditor") @RelateClass(SrvUser.class)  @note("  ") Long csiEditor;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csiEditor;//关联对象[用户]
	private @caption("添加人") @column("csi_adder")    @relate("$csiAdder") @RelateClass(SrvUser.class)  @note("  ") Long csiAdder;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csiAdder;//关联对象[用户]
	private @caption("备注信息") @column("csi_remark")    @note("  ") String csiRemark;//     
	private @caption("审核时间") @column("csi_check_time")    @note("  ") Date csiCheckTime;//     
	private @caption("开票时间") @column("csi_invoice_time")    @note("  ") Date csiInvoiceTime;//     
	private @caption("修改时间") @column("csi_update_time")    @note("  ") Date csiUpdateTime;// 非空     
	private @caption("添加时间") @column("csi_add_time")    @note("  ") Date csiAddTime;// 非空     
	private @caption("状态") @column("csi_status")    @note(" 0:待审核 1:已审核待开票 2:已开票 3:审核未通过 4:取消  ") Short csiStatus;// 非空 0:待审核 1:已审核待开票 2:已开票 3:审核未通过 4:取消     
	
	//默认构造函数
	public CsInvoice(){
	
	}
	
	//主键构造函数
	public CsInvoice(Long id){
		this.csiId = id;
	}
	
	/**所有字段构造函数 CsInvoice(csiHost,csiTitle,csiContent,csiType,csiTariffNo,csiMoney,csiMember,csiSendType,csiAddress,csiChecker,csiEditor,csiAdder,csiRemark,csiCheckTime,csiInvoiceTime,csiUpdateTime,csiAddTime,csiStatus)
	 CsInvoice(
	 	$.getLong("csiHost")//城市 [非空]
	 	,$.getString("csiTitle")//抬头名称 [非空]
	 	,$.getLong("csiContent")//开票内容 [非空]
	 	,$.getShort("csiType")//抬头类型 [非空]
	 	,$.getString("csiTariffNo")//税号
	 	,$.getDouble("csiMoney")//开票额
	 	,$.getLong("csiMember")//所属会员 [非空]
	 	,$.getShort("csiSendType")//寄送方式 [非空]
	 	,$.getLong("csiAddress")//寄送地址
	 	,$.getLong("csiChecker")//审核人
	 	,$.getLong("csiEditor")//开票人
	 	,$.getLong("csiAdder")//添加人 [非空]
	 	,$.getString("csiRemark")//备注信息
	 	,$.getDate("csiCheckTime")//审核时间
	 	,$.getDate("csiInvoiceTime")//开票时间
	 	,$.getDate("csiUpdateTime")//修改时间 [非空]
	 	,$.getDate("csiAddTime")//添加时间 [非空]
	 	,$.getShort("csiStatus")//状态 [非空]
	 )
	**/
	public CsInvoice(Long csiHost,String csiTitle,Long csiContent,Short csiType,String csiTariffNo,Double csiMoney,Long csiMember,Short csiSendType,Long csiAddress,Long csiChecker,Long csiEditor,Long csiAdder,String csiRemark,Date csiCheckTime,Date csiInvoiceTime,Date csiUpdateTime,Date csiAddTime,Short csiStatus){
		this.csiHost=csiHost;
		this.csiTitle=csiTitle;
		this.csiContent=csiContent;
		this.csiType=csiType;
		this.csiTariffNo=csiTariffNo;
		this.csiMoney=csiMoney;
		this.csiMember=csiMember;
		this.csiSendType=csiSendType;
		this.csiAddress=csiAddress;
		this.csiChecker=csiChecker;
		this.csiEditor=csiEditor;
		this.csiAdder=csiAdder;
		this.csiRemark=csiRemark;
		this.csiCheckTime=csiCheckTime;
		this.csiInvoiceTime=csiInvoiceTime;
		this.csiUpdateTime=csiUpdateTime;
		this.csiAddTime=csiAddTime;
		this.csiStatus=csiStatus;
	}
	
	//设置非空字段
	public CsInvoice setNotNull(Long csiHost,String csiTitle,Long csiContent,Short csiType,Long csiMember,Short csiSendType,Long csiAdder,Date csiUpdateTime,Date csiAddTime,Short csiStatus){
		this.csiHost=csiHost;
		this.csiTitle=csiTitle;
		this.csiContent=csiContent;
		this.csiType=csiType;
		this.csiMember=csiMember;
		this.csiSendType=csiSendType;
		this.csiAdder=csiAdder;
		this.csiUpdateTime=csiUpdateTime;
		this.csiAddTime=csiAddTime;
		this.csiStatus=csiStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsInvoice csiId(Long csiId){
		this.csiId = csiId;
		this.setSeted(F.csiId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsInvoice csiHost(Long csiHost){
		this.csiHost = csiHost;
		this.setSeted(F.csiHost);
		return this;
	}
	/** 抬头名称 [非空]       **/
	public CsInvoice csiTitle(String csiTitle){
		this.csiTitle = csiTitle;
		this.setSeted(F.csiTitle);
		return this;
	}
	/** 开票内容 [非空] [SrvProperty]  invoice_content:发票开票内容     **/
	public CsInvoice csiContent(Long csiContent){
		this.csiContent = csiContent;
		this.setSeted(F.csiContent);
		return this;
	}
	/** 抬头类型 [非空]   0:个人 1:公司     **/
	public CsInvoice csiType(Short csiType){
		this.csiType = csiType;
		this.setSeted(F.csiType);
		return this;
	}
	/** 税号        **/
	public CsInvoice csiTariffNo(String csiTariffNo){
		this.csiTariffNo = csiTariffNo;
		this.setSeted(F.csiTariffNo);
		return this;
	}
	/** 开票额        **/
	public CsInvoice csiMoney(Double csiMoney){
		this.csiMoney = csiMoney;
		this.setSeted(F.csiMoney);
		return this;
	}
	/** 所属会员 [非空] [CsMember]      **/
	public CsInvoice csiMember(Long csiMember){
		this.csiMember = csiMember;
		this.setSeted(F.csiMember);
		return this;
	}
	/** 寄送方式 [非空]   0:快递 1:上门取件     **/
	public CsInvoice csiSendType(Short csiSendType){
		this.csiSendType = csiSendType;
		this.setSeted(F.csiSendType);
		return this;
	}
	/** 寄送地址  [CsAddress]  {csiMember}:csaMember     **/
	public CsInvoice csiAddress(Long csiAddress){
		this.csiAddress = csiAddress;
		this.setSeted(F.csiAddress);
		return this;
	}
	/** 审核人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsInvoice csiChecker(Long csiChecker){
		this.csiChecker = csiChecker;
		this.setSeted(F.csiChecker);
		return this;
	}
	/** 开票人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsInvoice csiEditor(Long csiEditor){
		this.csiEditor = csiEditor;
		this.setSeted(F.csiEditor);
		return this;
	}
	/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsInvoice csiAdder(Long csiAdder){
		this.csiAdder = csiAdder;
		this.setSeted(F.csiAdder);
		return this;
	}
	/** 备注信息        **/
	public CsInvoice csiRemark(String csiRemark){
		this.csiRemark = csiRemark;
		this.setSeted(F.csiRemark);
		return this;
	}
	/** 审核时间        **/
	public CsInvoice csiCheckTime(Date csiCheckTime){
		this.csiCheckTime = csiCheckTime;
		this.setSeted(F.csiCheckTime);
		return this;
	}
	/** 开票时间        **/
	public CsInvoice csiInvoiceTime(Date csiInvoiceTime){
		this.csiInvoiceTime = csiInvoiceTime;
		this.setSeted(F.csiInvoiceTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsInvoice csiUpdateTime(Date csiUpdateTime){
		this.csiUpdateTime = csiUpdateTime;
		this.setSeted(F.csiUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsInvoice csiAddTime(Date csiAddTime){
		this.csiAddTime = csiAddTime;
		this.setSeted(F.csiAddTime);
		return this;
	}
	/** 状态 [非空]   0:待审核 1:已审核待开票 2:已开票 3:审核未通过 4:取消     **/
	public CsInvoice csiStatus(Short csiStatus){
		this.csiStatus = csiStatus;
		this.setSeted(F.csiStatus);
		return this;
	}
	
	
	//克隆对象
	public CsInvoice clone(){
		CsInvoice clone = new CsInvoice();
		clone.csiHost=this.csiHost;
		clone.csiTitle=this.csiTitle;
		clone.csiContent=this.csiContent;
		clone.csiType=this.csiType;
		clone.csiMember=this.csiMember;
		clone.csiSendType=this.csiSendType;
		clone.csiAdder=this.csiAdder;
		clone.csiUpdateTime=this.csiUpdateTime;
		clone.csiAddTime=this.csiAddTime;
		clone.csiStatus=this.csiStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取发票记录
	 * @param id
	 * @return
	 */
	public static @api CsInvoice get(Long id){		
		return getCsInvoiceById(id);
	}
	/**
	 * 获取所有发票记录
	 * @return
	 */
	public static @api List<CsInvoice> list(Map params,Integer size){
		return getCsInvoiceList(params,size);
	}
	/**
	 * 获取发票记录分页
	 * @return
	 */
	public static @api Page<CsInvoice> page(int page,int size,Map params){
		return getCsInvoicePage(page, size , params);
	}
	/**
	 * 根据查询条件取发票记录
	 * @param params
	 * @return
	 */
	public static @api CsInvoice Get(Map params){
		return getCsInvoice(params);
	}
	/**
	 * 获取发票记录数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsInvoiceCount(params);
	}
	/**
	 * 获取发票记录数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsInvoiceEval(eval,params);
	}
	
	/**
	 * 根据ID取发票记录
	 * @param id
	 * @return
	 */
	public static @api CsInvoice getCsInvoiceById(Long id){		
		CsInvoice csInvoice = (CsInvoice) $.GetRequest("CsInvoice$"+id);
		if(csInvoice!=null)
			return csInvoice;
		com.ccclubs.service.admin.ICsInvoiceService csInvoiceService = $.GetSpringBean("csInvoiceService");		
		return csInvoiceService.getCsInvoiceById(id);
	}
	
	
	/**
	 * 根据ID取发票记录的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsInvoice.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsInvoice csInvoice = get(id);
		if(csInvoice!=null){
			String strValue = csInvoice.getCsiTitle$();
			if(!"CsiTitle".equals("CsiTitle"))
				strValue+="("+csInvoice.getCsiTitle$()+")";
			MemCache.setValue(CsInvoice.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsiTitle$();
		if(!"CsiTitle".equals("CsiTitle"))
			keyValue+="("+this.getCsiTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有发票记录
	 * @return
	 */
	public static @api List<CsInvoice> getCsInvoiceList(Map params,Integer size){
		com.ccclubs.service.admin.ICsInvoiceService csInvoiceService = $.GetSpringBean("csInvoiceService");
		return csInvoiceService.getCsInvoiceList(params, size);
	}
	
	/**
	 * 获取发票记录分页
	 * @return
	 */
	public static @api Page<CsInvoice> getCsInvoicePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsInvoiceService csInvoiceService = $.GetSpringBean("csInvoiceService");
		return csInvoiceService.getCsInvoicePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取发票记录
	 * @param params
	 * @return
	 */
	public static @api CsInvoice getCsInvoice(Map params){
		com.ccclubs.service.admin.ICsInvoiceService csInvoiceService = $.GetSpringBean("csInvoiceService");
		return csInvoiceService.getCsInvoice(params);
	}
	
	/**
	 * 获取发票记录数
	 * @return
	 */
	public static @api Long getCsInvoiceCount(Map params){
		com.ccclubs.service.admin.ICsInvoiceService csInvoiceService = $.GetSpringBean("csInvoiceService");
		return csInvoiceService.getCsInvoiceCount(params);
	}
		
		
	/**
	 * 获取发票记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsInvoiceEval(String eval,Map params){
		com.ccclubs.service.admin.ICsInvoiceService csInvoiceService = $.GetSpringBean("csInvoiceService");
		return csInvoiceService.getCsInvoiceEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsInvoice(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsInvoiceService csInvoiceService = $.GetSpringBean("csInvoiceService");
		csInvoiceService.updateCsInvoiceByConfirm(set, where);
	}
	
	
	/**
	 * 保存发票记录对象
	 * @param params
	 * @return
	 */
	public CsInvoice save(){
		com.ccclubs.service.admin.ICsInvoiceService csInvoiceService = $.GetSpringBean("csInvoiceService");
		if(this.getCsiId()!=null)
			csInvoiceService.updateCsInvoice(this);
		else
			return csInvoiceService.saveCsInvoice(this);
		return this;
	}
	
	
	/**
	 * 更新发票记录对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsInvoiceService csInvoiceService = $.GetSpringBean("csInvoiceService");
		csInvoiceService.updateCsInvoice$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsInvoiceService csInvoiceService = $.GetSpringBean("csInvoiceService");
		if($.equals($.config("logic_delete"),"true"))
			csInvoiceService.removeCsInvoiceById(this.getCsiId());
		else
			csInvoiceService.deleteCsInvoiceById(this.getCsiId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsInvoiceService csInvoiceService = $.GetSpringBean("csInvoiceService");
		return csInvoiceService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsiId(){
		return this.csiId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsiId$(){
		String strValue="";
		 strValue=$.str(this.getCsiId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsiId(Long csiId){
		this.csiId = csiId;
		this.setSeted(F.csiId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsiHost(){
		return this.csiHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsiHost$(){
		String strValue="";
		if(this.getCsiHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsiHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsiHost(Long csiHost){
		this.csiHost = csiHost;
		this.setSeted(F.csiHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csiHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsiHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsiHost()!=null){
 			srvHost = SrvHost.get(this.getCsiHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsiHost(), srvHost);
	 	return srvHost;
	}
	/*******************************抬头名称**********************************/	
	/**
	* 抬头名称 [非空]      
	**/
	public String getCsiTitle(){
		return this.csiTitle;
	}
	/**
	* 获取抬头名称格式化(toString)
	**/
	public String getCsiTitle$(){
		String strValue="";
		 strValue=$.str(this.getCsiTitle());
	 	 return strValue;
	}
	/**
	* 抬头名称 [非空]      
	**/
	public void setCsiTitle(String csiTitle){
		this.csiTitle = csiTitle;
		this.setSeted(F.csiTitle);
	}
	/*******************************开票内容**********************************/	
	/**
	* 开票内容 [非空] [SrvProperty]  invoice_content:发票开票内容    
	**/
	public Long getCsiContent(){
		return this.csiContent;
	}
	/**
	* 获取开票内容格式化(toString)
	**/
	public String getCsiContent$(){
		String strValue="";
		if(this.getCsiContent()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCsiContent()));
		}			
	 	 return strValue;
	}
	/**
	* 开票内容 [非空] [SrvProperty]  invoice_content:发票开票内容    
	**/
	public void setCsiContent(Long csiContent){
		this.csiContent = csiContent;
		this.setSeted(F.csiContent);
	}
	/**
	* 获取关联对象[系统属性]
	**/	 			
 	public SrvProperty get$csiContent(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCsiContent());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCsiContent()!=null){
 			srvProperty = SrvProperty.get(this.getCsiContent());
 		}
 		$.SetRequest("SrvProperty$"+this.getCsiContent(), srvProperty);
	 	return srvProperty;
	}
	/*******************************抬头类型**********************************/	
	/**
	* 抬头类型 [非空]   0:个人 1:公司    
	**/
	public Short getCsiType(){
		return this.csiType;
	}
	/**
	* 获取抬头类型格式化(toString)
	**/
	public String getCsiType$(){
		String strValue="";
		 if($.equals($.str(this.getCsiType()),"0"))
			strValue=$.str("个人");		 
		 if($.equals($.str(this.getCsiType()),"1"))
			strValue=$.str("公司");		 
	 	 return strValue;
	}
	/**
	* 抬头类型 [非空]   0:个人 1:公司    
	**/
	public void setCsiType(Short csiType){
		this.csiType = csiType;
		this.setSeted(F.csiType);
	}
	/*******************************税号**********************************/	
	/**
	* 税号       
	**/
	public String getCsiTariffNo(){
		return this.csiTariffNo;
	}
	/**
	* 获取税号格式化(toString)
	**/
	public String getCsiTariffNo$(){
		String strValue="";
		 strValue=$.str(this.getCsiTariffNo());
	 	 return strValue;
	}
	/**
	* 税号       
	**/
	public void setCsiTariffNo(String csiTariffNo){
		this.csiTariffNo = csiTariffNo;
		this.setSeted(F.csiTariffNo);
	}
	/*******************************开票额**********************************/	
	/**
	* 开票额       
	**/
	public Double getCsiMoney(){
		return this.csiMoney;
	}
	/**
	* 获取开票额格式化(toString)
	**/
	public String getCsiMoney$(){
		String strValue="";
		 strValue=$.str(this.getCsiMoney());	
	 	 return strValue;
	}
	/**
	* 开票额       
	**/
	public void setCsiMoney(Double csiMoney){
		this.csiMoney = csiMoney;
		this.setSeted(F.csiMoney);
	}
	/*******************************所属会员**********************************/	
	/**
	* 所属会员 [非空] [CsMember]     
	**/
	public Long getCsiMember(){
		return this.csiMember;
	}
	/**
	* 获取所属会员格式化(toString)
	**/
	public String getCsiMember$(){
		String strValue="";
		if(this.getCsiMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsiMember()));
		}			
	 	 return strValue;
	}
	/**
	* 所属会员 [非空] [CsMember]     
	**/
	public void setCsiMember(Long csiMember){
		this.csiMember = csiMember;
		this.setSeted(F.csiMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csiMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsiMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsiMember()!=null){
 			csMember = CsMember.get(this.getCsiMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsiMember(), csMember);
	 	return csMember;
	}
	/*******************************寄送方式**********************************/	
	/**
	* 寄送方式 [非空]   0:快递 1:上门取件    
	**/
	public Short getCsiSendType(){
		return this.csiSendType;
	}
	/**
	* 获取寄送方式格式化(toString)
	**/
	public String getCsiSendType$(){
		String strValue="";
		 if($.equals($.str(this.getCsiSendType()),"0"))
			strValue=$.str("快递");		 
		 if($.equals($.str(this.getCsiSendType()),"1"))
			strValue=$.str("上门取件");		 
	 	 return strValue;
	}
	/**
	* 寄送方式 [非空]   0:快递 1:上门取件    
	**/
	public void setCsiSendType(Short csiSendType){
		this.csiSendType = csiSendType;
		this.setSeted(F.csiSendType);
	}
	/*******************************寄送地址**********************************/	
	/**
	* 寄送地址  [CsAddress]  {csiMember}:csaMember    
	**/
	public Long getCsiAddress(){
		return this.csiAddress;
	}
	/**
	* 获取寄送地址格式化(toString)
	**/
	public String getCsiAddress$(){
		String strValue="";
		if(this.getCsiAddress()!=null){
				strValue+=$.str(CsAddress.getKeyValue(this.getCsiAddress()));
		}			
	 	 return strValue;
	}
	/**
	* 寄送地址  [CsAddress]  {csiMember}:csaMember    
	**/
	public void setCsiAddress(Long csiAddress){
		this.csiAddress = csiAddress;
		this.setSeted(F.csiAddress);
	}
	/**
	* 获取关联对象[会员地址]
	**/	 			
 	public CsAddress get$csiAddress(){
 		com.ccclubs.model.CsAddress csAddress = (com.ccclubs.model.CsAddress) $.GetRequest("CsAddress$"+this.getCsiAddress());
 		if(csAddress!=null)
			return csAddress;
		if(this.getCsiAddress()!=null){
 			csAddress = CsAddress.get(this.getCsiAddress());
 		}
 		$.SetRequest("CsAddress$"+this.getCsiAddress(), csAddress);
	 	return csAddress;
	}
	/*******************************审核人**********************************/	
	/**
	* 审核人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsiChecker(){
		return this.csiChecker;
	}
	/**
	* 获取审核人格式化(toString)
	**/
	public String getCsiChecker$(){
		String strValue="";
		if(this.getCsiChecker()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsiChecker()));
		}			
	 	 return strValue;
	}
	/**
	* 审核人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsiChecker(Long csiChecker){
		this.csiChecker = csiChecker;
		this.setSeted(F.csiChecker);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csiChecker(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsiChecker());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsiChecker()!=null){
 			srvUser = SrvUser.get(this.getCsiChecker());
 		}
 		$.SetRequest("SrvUser$"+this.getCsiChecker(), srvUser);
	 	return srvUser;
	}
	/*******************************开票人**********************************/	
	/**
	* 开票人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsiEditor(){
		return this.csiEditor;
	}
	/**
	* 获取开票人格式化(toString)
	**/
	public String getCsiEditor$(){
		String strValue="";
		if(this.getCsiEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsiEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 开票人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsiEditor(Long csiEditor){
		this.csiEditor = csiEditor;
		this.setSeted(F.csiEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csiEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsiEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsiEditor()!=null){
 			srvUser = SrvUser.get(this.getCsiEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCsiEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************添加人**********************************/	
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsiAdder(){
		return this.csiAdder;
	}
	/**
	* 获取添加人格式化(toString)
	**/
	public String getCsiAdder$(){
		String strValue="";
		if(this.getCsiAdder()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsiAdder()));
		}			
	 	 return strValue;
	}
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsiAdder(Long csiAdder){
		this.csiAdder = csiAdder;
		this.setSeted(F.csiAdder);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csiAdder(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsiAdder());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsiAdder()!=null){
 			srvUser = SrvUser.get(this.getCsiAdder());
 		}
 		$.SetRequest("SrvUser$"+this.getCsiAdder(), srvUser);
	 	return srvUser;
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息       
	**/
	public String getCsiRemark(){
		return this.csiRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCsiRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsiRemark());
	 	 return strValue;
	}
	/**
	* 备注信息       
	**/
	public void setCsiRemark(String csiRemark){
		this.csiRemark = csiRemark;
		this.setSeted(F.csiRemark);
	}
	/*******************************审核时间**********************************/	
	/**
	* 审核时间       
	**/
	public Date getCsiCheckTime(){
		return this.csiCheckTime;
	}
	/**
	* 获取审核时间格式化(toString)
	**/
	public String getCsiCheckTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsiCheckTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 审核时间       
	**/
	public void setCsiCheckTime(Date csiCheckTime){
		this.csiCheckTime = csiCheckTime;
		this.setSeted(F.csiCheckTime);
	}
	/*******************************开票时间**********************************/	
	/**
	* 开票时间       
	**/
	public Date getCsiInvoiceTime(){
		return this.csiInvoiceTime;
	}
	/**
	* 获取开票时间格式化(toString)
	**/
	public String getCsiInvoiceTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsiInvoiceTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 开票时间       
	**/
	public void setCsiInvoiceTime(Date csiInvoiceTime){
		this.csiInvoiceTime = csiInvoiceTime;
		this.setSeted(F.csiInvoiceTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsiUpdateTime(){
		return this.csiUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsiUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsiUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsiUpdateTime(Date csiUpdateTime){
		this.csiUpdateTime = csiUpdateTime;
		this.setSeted(F.csiUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsiAddTime(){
		return this.csiAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsiAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsiAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsiAddTime(Date csiAddTime){
		this.csiAddTime = csiAddTime;
		this.setSeted(F.csiAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:待审核 1:已审核待开票 2:已开票 3:审核未通过 4:取消    
	**/
	public Short getCsiStatus(){
		return this.csiStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsiStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsiStatus()),"0"))
			strValue=$.str("待审核");		 
		 if($.equals($.str(this.getCsiStatus()),"1"))
			strValue=$.str("已审核待开票");		 
		 if($.equals($.str(this.getCsiStatus()),"2"))
			strValue=$.str("已开票");		 
		 if($.equals($.str(this.getCsiStatus()),"3"))
			strValue=$.str("审核未通过");		 
		 if($.equals($.str(this.getCsiStatus()),"4"))
			strValue=$.str("取消");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:待审核 1:已审核待开票 2:已开票 3:审核未通过 4:取消    
	**/
	public void setCsiStatus(Short csiStatus){
		this.csiStatus = csiStatus;
		this.setSeted(F.csiStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsInvoice.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsInvoice.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsInvoice.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsInvoice.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsInvoice.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsInvoice.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsInvoice.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsInvoice.this);
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
		public M csiId(Object csiId){this.put("csiId", csiId);return this;};
	 	/** and csi_id is null */
 		public M csiIdNull(){if(this.get("csiIdNot")==null)this.put("csiIdNot", "");this.put("csiId", null);return this;};
 		/** not .... */
 		public M csiIdNot(){this.put("csiIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csiHost(Object csiHost){this.put("csiHost", csiHost);return this;};
	 	/** and csi_host is null */
 		public M csiHostNull(){if(this.get("csiHostNot")==null)this.put("csiHostNot", "");this.put("csiHost", null);return this;};
 		/** not .... */
 		public M csiHostNot(){this.put("csiHostNot", "not");return this;};
		/** 抬头名称 [非空]       **/
		public M csiTitle(Object csiTitle){this.put("csiTitle", csiTitle);return this;};
	 	/** and csi_title is null */
 		public M csiTitleNull(){if(this.get("csiTitleNot")==null)this.put("csiTitleNot", "");this.put("csiTitle", null);return this;};
 		/** not .... */
 		public M csiTitleNot(){this.put("csiTitleNot", "not");return this;};
		/** 开票内容 [非空] [SrvProperty]  invoice_content:发票开票内容     **/
		public M csiContent(Object csiContent){this.put("csiContent", csiContent);return this;};
	 	/** and csi_content is null */
 		public M csiContentNull(){if(this.get("csiContentNot")==null)this.put("csiContentNot", "");this.put("csiContent", null);return this;};
 		/** not .... */
 		public M csiContentNot(){this.put("csiContentNot", "not");return this;};
		public M csiContent$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("csiContent$on", value);
			return this;
		};	
		/** 抬头类型 [非空]   0:个人 1:公司     **/
		public M csiType(Object csiType){this.put("csiType", csiType);return this;};
	 	/** and csi_type is null */
 		public M csiTypeNull(){if(this.get("csiTypeNot")==null)this.put("csiTypeNot", "");this.put("csiType", null);return this;};
 		/** not .... */
 		public M csiTypeNot(){this.put("csiTypeNot", "not");return this;};
		/** 税号        **/
		public M csiTariffNo(Object csiTariffNo){this.put("csiTariffNo", csiTariffNo);return this;};
	 	/** and csi_tariff_no is null */
 		public M csiTariffNoNull(){if(this.get("csiTariffNoNot")==null)this.put("csiTariffNoNot", "");this.put("csiTariffNo", null);return this;};
 		/** not .... */
 		public M csiTariffNoNot(){this.put("csiTariffNoNot", "not");return this;};
		/** 开票额        **/
		public M csiMoney(Object csiMoney){this.put("csiMoney", csiMoney);return this;};
	 	/** and csi_money is null */
 		public M csiMoneyNull(){if(this.get("csiMoneyNot")==null)this.put("csiMoneyNot", "");this.put("csiMoney", null);return this;};
 		/** not .... */
 		public M csiMoneyNot(){this.put("csiMoneyNot", "not");return this;};
		/** and csi_money >= ? */
		public M csiMoneyMin(Object min){this.put("csiMoneyMin", min);return this;};
		/** and csi_money <= ? */
		public M csiMoneyMax(Object max){this.put("csiMoneyMax", max);return this;};
		/** 所属会员 [非空] [CsMember]      **/
		public M csiMember(Object csiMember){this.put("csiMember", csiMember);return this;};
	 	/** and csi_member is null */
 		public M csiMemberNull(){if(this.get("csiMemberNot")==null)this.put("csiMemberNot", "");this.put("csiMember", null);return this;};
 		/** not .... */
 		public M csiMemberNot(){this.put("csiMemberNot", "not");return this;};
		public M csiMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csiMember$on", value);
			return this;
		};	
		/** 寄送方式 [非空]   0:快递 1:上门取件     **/
		public M csiSendType(Object csiSendType){this.put("csiSendType", csiSendType);return this;};
	 	/** and csi_send_type is null */
 		public M csiSendTypeNull(){if(this.get("csiSendTypeNot")==null)this.put("csiSendTypeNot", "");this.put("csiSendType", null);return this;};
 		/** not .... */
 		public M csiSendTypeNot(){this.put("csiSendTypeNot", "not");return this;};
		/** 寄送地址  [CsAddress]  {csiMember}:csaMember     **/
		public M csiAddress(Object csiAddress){this.put("csiAddress", csiAddress);return this;};
	 	/** and csi_address is null */
 		public M csiAddressNull(){if(this.get("csiAddressNot")==null)this.put("csiAddressNot", "");this.put("csiAddress", null);return this;};
 		/** not .... */
 		public M csiAddressNot(){this.put("csiAddressNot", "not");return this;};
		public M csiAddress$on(CsAddress.M value){
			this.put("CsAddress", value);
			this.put("csiAddress$on", value);
			return this;
		};	
		/** 审核人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csiChecker(Object csiChecker){this.put("csiChecker", csiChecker);return this;};
	 	/** and csi_checker is null */
 		public M csiCheckerNull(){if(this.get("csiCheckerNot")==null)this.put("csiCheckerNot", "");this.put("csiChecker", null);return this;};
 		/** not .... */
 		public M csiCheckerNot(){this.put("csiCheckerNot", "not");return this;};
		/** 开票人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csiEditor(Object csiEditor){this.put("csiEditor", csiEditor);return this;};
	 	/** and csi_editor is null */
 		public M csiEditorNull(){if(this.get("csiEditorNot")==null)this.put("csiEditorNot", "");this.put("csiEditor", null);return this;};
 		/** not .... */
 		public M csiEditorNot(){this.put("csiEditorNot", "not");return this;};
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csiAdder(Object csiAdder){this.put("csiAdder", csiAdder);return this;};
	 	/** and csi_adder is null */
 		public M csiAdderNull(){if(this.get("csiAdderNot")==null)this.put("csiAdderNot", "");this.put("csiAdder", null);return this;};
 		/** not .... */
 		public M csiAdderNot(){this.put("csiAdderNot", "not");return this;};
		/** 备注信息        **/
		public M csiRemark(Object csiRemark){this.put("csiRemark", csiRemark);return this;};
	 	/** and csi_remark is null */
 		public M csiRemarkNull(){if(this.get("csiRemarkNot")==null)this.put("csiRemarkNot", "");this.put("csiRemark", null);return this;};
 		/** not .... */
 		public M csiRemarkNot(){this.put("csiRemarkNot", "not");return this;};
		/** 审核时间        **/
		public M csiCheckTime(Object csiCheckTime){this.put("csiCheckTime", csiCheckTime);return this;};
	 	/** and csi_check_time is null */
 		public M csiCheckTimeNull(){if(this.get("csiCheckTimeNot")==null)this.put("csiCheckTimeNot", "");this.put("csiCheckTime", null);return this;};
 		/** not .... */
 		public M csiCheckTimeNot(){this.put("csiCheckTimeNot", "not");return this;};
 		/** and csi_check_time >= ? */
 		public M csiCheckTimeStart(Object start){this.put("csiCheckTimeStart", start);return this;};			
 		/** and csi_check_time <= ? */
 		public M csiCheckTimeEnd(Object end){this.put("csiCheckTimeEnd", end);return this;};
		/** 开票时间        **/
		public M csiInvoiceTime(Object csiInvoiceTime){this.put("csiInvoiceTime", csiInvoiceTime);return this;};
	 	/** and csi_invoice_time is null */
 		public M csiInvoiceTimeNull(){if(this.get("csiInvoiceTimeNot")==null)this.put("csiInvoiceTimeNot", "");this.put("csiInvoiceTime", null);return this;};
 		/** not .... */
 		public M csiInvoiceTimeNot(){this.put("csiInvoiceTimeNot", "not");return this;};
 		/** and csi_invoice_time >= ? */
 		public M csiInvoiceTimeStart(Object start){this.put("csiInvoiceTimeStart", start);return this;};			
 		/** and csi_invoice_time <= ? */
 		public M csiInvoiceTimeEnd(Object end){this.put("csiInvoiceTimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M csiUpdateTime(Object csiUpdateTime){this.put("csiUpdateTime", csiUpdateTime);return this;};
	 	/** and csi_update_time is null */
 		public M csiUpdateTimeNull(){if(this.get("csiUpdateTimeNot")==null)this.put("csiUpdateTimeNot", "");this.put("csiUpdateTime", null);return this;};
 		/** not .... */
 		public M csiUpdateTimeNot(){this.put("csiUpdateTimeNot", "not");return this;};
 		/** and csi_update_time >= ? */
 		public M csiUpdateTimeStart(Object start){this.put("csiUpdateTimeStart", start);return this;};			
 		/** and csi_update_time <= ? */
 		public M csiUpdateTimeEnd(Object end){this.put("csiUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csiAddTime(Object csiAddTime){this.put("csiAddTime", csiAddTime);return this;};
	 	/** and csi_add_time is null */
 		public M csiAddTimeNull(){if(this.get("csiAddTimeNot")==null)this.put("csiAddTimeNot", "");this.put("csiAddTime", null);return this;};
 		/** not .... */
 		public M csiAddTimeNot(){this.put("csiAddTimeNot", "not");return this;};
 		/** and csi_add_time >= ? */
 		public M csiAddTimeStart(Object start){this.put("csiAddTimeStart", start);return this;};			
 		/** and csi_add_time <= ? */
 		public M csiAddTimeEnd(Object end){this.put("csiAddTimeEnd", end);return this;};
		/** 状态 [非空]   0:待审核 1:已审核待开票 2:已开票 3:审核未通过 4:取消     **/
		public M csiStatus(Object csiStatus){this.put("csiStatus", csiStatus);return this;};
	 	/** and csi_status is null */
 		public M csiStatusNull(){if(this.get("csiStatusNot")==null)this.put("csiStatusNot", "");this.put("csiStatus", null);return this;};
 		/** not .... */
 		public M csiStatusNot(){this.put("csiStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有发票记录 **/
		public @api List<CsInvoice> list(Integer size){
			return getCsInvoiceList(this,size);
		}
		/** 获取发票记录分页 **/
		public @api Page<CsInvoice> page(int page,int size){
			return getCsInvoicePage(page, size , this);
		}
		/** 根据查询条件取发票记录 **/
		public @api CsInvoice get(){
			return getCsInvoice(this);
		}
		/** 获取发票记录数 **/
		public @api Long count(){
			return getCsInvoiceCount(this);
		}
		/** 获取发票记录表达式 **/
		public @api <T> T eval(String strEval){
			return getCsInvoiceEval(strEval,this);
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
			updateCsInvoice(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csiId="csiId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csiHost="csiHost";
		/** 抬头名称 [非空]       **/
		public final static @type(String.class) @like String csiTitle="csiTitle";
		/** 开票内容 [非空] [SrvProperty]  invoice_content:发票开票内容     **/
		public final static @type(Long.class)  String csiContent="csiContent";
		/** 抬头类型 [非空]   0:个人 1:公司     **/
		public final static @type(Short.class)  String csiType="csiType";
		/** 税号        **/
		public final static @type(String.class) @like String csiTariffNo="csiTariffNo";
		/** 开票额        **/
		public final static @type(Double.class)  String csiMoney="csiMoney";
		/** and csi_money >= ? */
		public final static @type(Double.class) String csiMoneyMin="csiMoneyMin";
		/** and csi_money <= ? */
		public final static @type(Double.class) String csiMoneyMax="csiMoneyMax";
		/** 所属会员 [非空] [CsMember]      **/
		public final static @type(Long.class)  String csiMember="csiMember";
		/** 寄送方式 [非空]   0:快递 1:上门取件     **/
		public final static @type(Short.class)  String csiSendType="csiSendType";
		/** 寄送地址  [CsAddress]  {csiMember}:csaMember     **/
		public final static @type(Long.class)  String csiAddress="csiAddress";
		/** 审核人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csiChecker="csiChecker";
		/** 开票人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csiEditor="csiEditor";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csiAdder="csiAdder";
		/** 备注信息        **/
		public final static @type(String.class) @like String csiRemark="csiRemark";
		/** 审核时间        **/
		public final static @type(Date.class)  String csiCheckTime="csiCheckTime";
	 	/** and csi_check_time >= ? */
 		public final static @type(Date.class) String csiCheckTimeStart="csiCheckTimeStart";
 		/** and csi_check_time <= ? */
 		public final static @type(Date.class) String csiCheckTimeEnd="csiCheckTimeEnd";
		/** 开票时间        **/
		public final static @type(Date.class)  String csiInvoiceTime="csiInvoiceTime";
	 	/** and csi_invoice_time >= ? */
 		public final static @type(Date.class) String csiInvoiceTimeStart="csiInvoiceTimeStart";
 		/** and csi_invoice_time <= ? */
 		public final static @type(Date.class) String csiInvoiceTimeEnd="csiInvoiceTimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csiUpdateTime="csiUpdateTime";
	 	/** and csi_update_time >= ? */
 		public final static @type(Date.class) String csiUpdateTimeStart="csiUpdateTimeStart";
 		/** and csi_update_time <= ? */
 		public final static @type(Date.class) String csiUpdateTimeEnd="csiUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csiAddTime="csiAddTime";
	 	/** and csi_add_time >= ? */
 		public final static @type(Date.class) String csiAddTimeStart="csiAddTimeStart";
 		/** and csi_add_time <= ? */
 		public final static @type(Date.class) String csiAddTimeEnd="csiAddTimeEnd";
		/** 状态 [非空]   0:待审核 1:已审核待开票 2:已开票 3:审核未通过 4:取消     **/
		public final static @type(Short.class)  String csiStatus="csiStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csiId="csi_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csiHost="csi_host";
		/** 抬头名称 [非空]       **/
		public final static String csiTitle="csi_title";
		/** 开票内容 [非空] [SrvProperty]  invoice_content:发票开票内容     **/
		public final static String csiContent="csi_content";
		/** 抬头类型 [非空]   0:个人 1:公司     **/
		public final static String csiType="csi_type";
		/** 税号        **/
		public final static String csiTariffNo="csi_tariff_no";
		/** 开票额        **/
		public final static String csiMoney="csi_money";
		/** 所属会员 [非空] [CsMember]      **/
		public final static String csiMember="csi_member";
		/** 寄送方式 [非空]   0:快递 1:上门取件     **/
		public final static String csiSendType="csi_send_type";
		/** 寄送地址  [CsAddress]  {csiMember}:csaMember     **/
		public final static String csiAddress="csi_address";
		/** 审核人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csiChecker="csi_checker";
		/** 开票人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csiEditor="csi_editor";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csiAdder="csi_adder";
		/** 备注信息        **/
		public final static String csiRemark="csi_remark";
		/** 审核时间        **/
		public final static String csiCheckTime="csi_check_time";
		/** 开票时间        **/
		public final static String csiInvoiceTime="csi_invoice_time";
		/** 修改时间 [非空]       **/
		public final static String csiUpdateTime="csi_update_time";
		/** 添加时间 [非空]       **/
		public final static String csiAddTime="csi_add_time";
		/** 状态 [非空]   0:待审核 1:已审核待开票 2:已开票 3:审核未通过 4:取消     **/
		public final static String csiStatus="csi_status";
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
				$.Set(name,CsInvoice.getCsInvoice(params));
			else
				$.Set(name,CsInvoice.getCsInvoiceList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取发票记录数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsInvoice) $.GetRequest("CsInvoice$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsInvoice.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsInvoice.getCsInvoice(params);
			else
				value = CsInvoice.getCsInvoiceList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsInvoice.Get($.add(CsInvoice.F.csiId,param));
		else if(!$.empty(param.toString()))
			value = CsInvoice.get(Long.valueOf(param.toString()));
		$.SetRequest("CsInvoice$"+param.hashCode(), value);
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
	public void mergeSet(CsInvoice old){
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