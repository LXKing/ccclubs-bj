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

@namespace("user/integralrecord")
public @caption("积分记录") @table("cs_integral_record") class CsIntegralRecord implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csr_id")   @primary  @note("  ") Long csrId;// 主键 非空     
	private @caption("城市") @column("csr_host")    @relate("$csrHost") @RelateClass(SrvHost.class)  @note("  ") Long csrHost;// 非空     
 	private SrvHost $csrHost;//关联对象[运营城市]
	private @caption("消费会员") @column("csr_member")    @relate("$csrMember") @RelateClass(CsMember.class)  @note("  ") Long csrMember;// 非空    ${basePath}${proname}/user/member_inquire.do 
 	private CsMember $csrMember;//关联对象[会员帐号]
	private @caption("积分类型") @column("csr_type")    @note("  ") String csrType;// 非空     
	private @caption("变化积分") @column("csr_amount")    @note("  ") Double csrAmount;// 非空     
	private @caption("剩余积分") @column("csr_remain")    @note("  帐户余额或现金券余额或积分余量") Double csrRemain;// 非空  帐户余额或现金券余额或积分余量   
	private @caption("描述") @column("csr_remark")    @note("  ") String csrRemark;//     
	private @caption("发生时间") @column("csr_add_time")    @note("  ") Date csrAddTime;// 非空     
	private @caption("关联订单") @column("csr_order")    @relate("$csrOrder") @RelateClass(CsOrder.class)  @note("  ") Long csrOrder;//     
 	private CsOrder $csrOrder;//关联对象[系统订单]
	private @caption("关联编号") @column("csr_relate")    @note("  支付宝充值？等") Long csrRelate;//  支付宝充值？等   
	private @caption("序列号") @column("csr_serial")  @hidden   @note("  ") String csrSerial;//     
	private @caption("操作人") @column("csr_editor")    @relate("$csrEditor") @RelateClass(SrvUser.class)  @note("  ") Long csrEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csrEditor;//关联对象[用户]
	
	//默认构造函数
	public CsIntegralRecord(){
	
	}
	
	//主键构造函数
	public CsIntegralRecord(Long id){
		this.csrId = id;
	}
	
	/**所有字段构造函数 CsIntegralRecord(csrHost,csrMember,csrType,csrAmount,csrRemain,csrRemark,csrAddTime,csrOrder,csrRelate,csrSerial,csrEditor)
	 CsIntegralRecord(
	 	$.getLong("csrHost")//城市 [非空]
	 	,$.getLong("csrMember")//消费会员 [非空]
	 	,$.getString("csrType")//积分类型 [非空]
	 	,$.getDouble("csrAmount")//变化积分 [非空]
	 	,$.getDouble("csrRemain")//剩余积分 [非空]
	 	,$.getString("csrRemark")//描述
	 	,$.getDate("csrAddTime")//发生时间 [非空]
	 	,$.getLong("csrOrder")//关联订单
	 	,$.getLong("csrRelate")//关联编号
	 	,$.getString("csrSerial")//序列号
	 	,$.getLong("csrEditor")//操作人 [非空]
	 )
	**/
	public CsIntegralRecord(Long csrHost,Long csrMember,String csrType,Double csrAmount,Double csrRemain,String csrRemark,Date csrAddTime,Long csrOrder,Long csrRelate,String csrSerial,Long csrEditor){
		this.csrHost=csrHost;
		this.csrMember=csrMember;
		this.csrType=csrType;
		this.csrAmount=csrAmount;
		this.csrRemain=csrRemain;
		this.csrRemark=csrRemark;
		this.csrAddTime=csrAddTime;
		this.csrOrder=csrOrder;
		this.csrRelate=csrRelate;
		this.csrSerial=csrSerial;
		this.csrEditor=csrEditor;
	}
	
	//设置非空字段
	public CsIntegralRecord setNotNull(Long csrHost,Long csrMember,String csrType,Double csrAmount,Double csrRemain,Date csrAddTime,Long csrEditor){
		this.csrHost=csrHost;
		this.csrMember=csrMember;
		this.csrType=csrType;
		this.csrAmount=csrAmount;
		this.csrRemain=csrRemain;
		this.csrAddTime=csrAddTime;
		this.csrEditor=csrEditor;
		return this;
	}
	/** 编号 [非空]       **/
	public CsIntegralRecord csrId(Long csrId){
		this.csrId = csrId;
		this.setSeted(F.csrId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsIntegralRecord csrHost(Long csrHost){
		this.csrHost = csrHost;
		this.setSeted(F.csrHost);
		return this;
	}
	/** 消费会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
	public CsIntegralRecord csrMember(Long csrMember){
		this.csrMember = csrMember;
		this.setSeted(F.csrMember);
		return this;
	}
	/** 积分类型 [非空]       **/
	public CsIntegralRecord csrType(String csrType){
		this.csrType = csrType;
		this.setSeted(F.csrType);
		return this;
	}
	/** 变化积分 [非空]       **/
	public CsIntegralRecord csrAmount(Double csrAmount){
		this.csrAmount = csrAmount;
		this.setSeted(F.csrAmount);
		return this;
	}
	/** 剩余积分 [非空]    帐户余额或现金券余额或积分余量   **/
	public CsIntegralRecord csrRemain(Double csrRemain){
		this.csrRemain = csrRemain;
		this.setSeted(F.csrRemain);
		return this;
	}
	/** 描述        **/
	public CsIntegralRecord csrRemark(String csrRemark){
		this.csrRemark = csrRemark;
		this.setSeted(F.csrRemark);
		return this;
	}
	/** 发生时间 [非空]       **/
	public CsIntegralRecord csrAddTime(Date csrAddTime){
		this.csrAddTime = csrAddTime;
		this.setSeted(F.csrAddTime);
		return this;
	}
	/** 关联订单  [CsOrder]      **/
	public CsIntegralRecord csrOrder(Long csrOrder){
		this.csrOrder = csrOrder;
		this.setSeted(F.csrOrder);
		return this;
	}
	/** 关联编号     支付宝充值？等   **/
	public CsIntegralRecord csrRelate(Long csrRelate){
		this.csrRelate = csrRelate;
		this.setSeted(F.csrRelate);
		return this;
	}
	/** 序列号        **/
	public CsIntegralRecord csrSerial(String csrSerial){
		this.csrSerial = csrSerial;
		this.setSeted(F.csrSerial);
		return this;
	}
	/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsIntegralRecord csrEditor(Long csrEditor){
		this.csrEditor = csrEditor;
		this.setSeted(F.csrEditor);
		return this;
	}
	
	
	//克隆对象
	public CsIntegralRecord clone(){
		CsIntegralRecord clone = new CsIntegralRecord();
		clone.csrHost=this.csrHost;
		clone.csrMember=this.csrMember;
		clone.csrType=this.csrType;
		clone.csrAmount=this.csrAmount;
		clone.csrRemain=this.csrRemain;
		clone.csrAddTime=this.csrAddTime;
		clone.csrEditor=this.csrEditor;
		return clone;
	}
	
	
	/**
	 * 根据ID取积分记录
	 * @param id
	 * @return
	 */
	public static @api CsIntegralRecord get(Long id){		
		return getCsIntegralRecordById(id);
	}
	/**
	 * 获取所有积分记录
	 * @return
	 */
	public static @api List<CsIntegralRecord> list(Map params,Integer size){
		return getCsIntegralRecordList(params,size);
	}
	/**
	 * 获取积分记录分页
	 * @return
	 */
	public static @api Page<CsIntegralRecord> page(int page,int size,Map params){
		return getCsIntegralRecordPage(page, size , params);
	}
	/**
	 * 根据查询条件取积分记录
	 * @param params
	 * @return
	 */
	public static @api CsIntegralRecord Get(Map params){
		return getCsIntegralRecord(params);
	}
	/**
	 * 获取积分记录数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsIntegralRecordCount(params);
	}
	/**
	 * 获取积分记录数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsIntegralRecordEval(eval,params);
	}
	
	/**
	 * 根据ID取积分记录
	 * @param id
	 * @return
	 */
	public static @api CsIntegralRecord getCsIntegralRecordById(Long id){		
		CsIntegralRecord csIntegralRecord = (CsIntegralRecord) $.GetRequest("CsIntegralRecord$"+id);
		if(csIntegralRecord!=null)
			return csIntegralRecord;
		com.ccclubs.service.admin.ICsIntegralRecordService csIntegralRecordService = $.GetSpringBean("csIntegralRecordService");		
		return csIntegralRecordService.getCsIntegralRecordById(id);
	}
	
	
	/**
	 * 根据ID取积分记录的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsIntegralRecord.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsIntegralRecord csIntegralRecord = get(id);
		if(csIntegralRecord!=null){
			String strValue = csIntegralRecord.getCsrId$();
			if(!"CsrId".equals("CsrId"))
				strValue+="("+csIntegralRecord.getCsrId$()+")";
			MemCache.setValue(CsIntegralRecord.class, id ,strValue);
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
	 * 获取所有积分记录
	 * @return
	 */
	public static @api List<CsIntegralRecord> getCsIntegralRecordList(Map params,Integer size){
		com.ccclubs.service.admin.ICsIntegralRecordService csIntegralRecordService = $.GetSpringBean("csIntegralRecordService");
		return csIntegralRecordService.getCsIntegralRecordList(params, size);
	}
	
	/**
	 * 获取积分记录分页
	 * @return
	 */
	public static @api Page<CsIntegralRecord> getCsIntegralRecordPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsIntegralRecordService csIntegralRecordService = $.GetSpringBean("csIntegralRecordService");
		return csIntegralRecordService.getCsIntegralRecordPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取积分记录
	 * @param params
	 * @return
	 */
	public static @api CsIntegralRecord getCsIntegralRecord(Map params){
		com.ccclubs.service.admin.ICsIntegralRecordService csIntegralRecordService = $.GetSpringBean("csIntegralRecordService");
		return csIntegralRecordService.getCsIntegralRecord(params);
	}
	
	/**
	 * 获取积分记录数
	 * @return
	 */
	public static @api Long getCsIntegralRecordCount(Map params){
		com.ccclubs.service.admin.ICsIntegralRecordService csIntegralRecordService = $.GetSpringBean("csIntegralRecordService");
		return csIntegralRecordService.getCsIntegralRecordCount(params);
	}
		
		
	/**
	 * 获取积分记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsIntegralRecordEval(String eval,Map params){
		com.ccclubs.service.admin.ICsIntegralRecordService csIntegralRecordService = $.GetSpringBean("csIntegralRecordService");
		return csIntegralRecordService.getCsIntegralRecordEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsIntegralRecord(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsIntegralRecordService csIntegralRecordService = $.GetSpringBean("csIntegralRecordService");
		csIntegralRecordService.updateCsIntegralRecordByConfirm(set, where);
	}
	
	
	/**
	 * 保存积分记录对象
	 * @param params
	 * @return
	 */
	public CsIntegralRecord save(){
		com.ccclubs.service.admin.ICsIntegralRecordService csIntegralRecordService = $.GetSpringBean("csIntegralRecordService");
		if(this.getCsrId()!=null)
			csIntegralRecordService.updateCsIntegralRecord(this);
		else
			return csIntegralRecordService.saveCsIntegralRecord(this);
		return this;
	}
	
	
	/**
	 * 更新积分记录对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsIntegralRecordService csIntegralRecordService = $.GetSpringBean("csIntegralRecordService");
		csIntegralRecordService.updateCsIntegralRecord$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsIntegralRecordService csIntegralRecordService = $.GetSpringBean("csIntegralRecordService");
			csIntegralRecordService.deleteCsIntegralRecordById(this.getCsrId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsIntegralRecordService csIntegralRecordService = $.GetSpringBean("csIntegralRecordService");
		return csIntegralRecordService.executeTransaction(function);
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
	/*******************************积分类型**********************************/	
	/**
	* 积分类型 [非空]      
	**/
	public String getCsrType(){
		return this.csrType;
	}
	/**
	* 获取积分类型格式化(toString)
	**/
	public String getCsrType$(){
		String strValue="";
		 strValue=$.str(this.getCsrType());
	 	 return strValue;
	}
	/**
	* 积分类型 [非空]      
	**/
	public void setCsrType(String csrType){
		this.csrType = csrType;
		this.setSeted(F.csrType);
	}
	/*******************************变化积分**********************************/	
	/**
	* 变化积分 [非空]      
	**/
	public Double getCsrAmount(){
		return this.csrAmount;
	}
	/**
	* 获取变化积分格式化(toString)
	**/
	public String getCsrAmount$(){
		String strValue="";
		 strValue=$.str(this.getCsrAmount());	
	 	 return strValue;
	}
	/**
	* 变化积分 [非空]      
	**/
	public void setCsrAmount(Double csrAmount){
		this.csrAmount = csrAmount;
		this.setSeted(F.csrAmount);
	}
	/*******************************剩余积分**********************************/	
	/**
	* 剩余积分 [非空]    帐户余额或现金券余额或积分余量  
	**/
	public Double getCsrRemain(){
		return this.csrRemain;
	}
	/**
	* 获取剩余积分格式化(toString)
	**/
	public String getCsrRemain$(){
		String strValue="";
		 strValue=$.str(this.getCsrRemain());	
	 	 return strValue;
	}
	/**
	* 剩余积分 [非空]    帐户余额或现金券余额或积分余量  
	**/
	public void setCsrRemain(Double csrRemain){
		this.csrRemain = csrRemain;
		this.setSeted(F.csrRemain);
	}
	/*******************************描述**********************************/	
	/**
	* 描述       
	**/
	public String getCsrRemark(){
		return this.csrRemark;
	}
	/**
	* 获取描述格式化(toString)
	**/
	public String getCsrRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsrRemark());
	 	 return strValue;
	}
	/**
	* 描述       
	**/
	public void setCsrRemark(String csrRemark){
		this.csrRemark = csrRemark;
		this.setSeted(F.csrRemark);
	}
	/*******************************发生时间**********************************/	
	/**
	* 发生时间 [非空]      
	**/
	public Date getCsrAddTime(){
		return this.csrAddTime;
	}
	/**
	* 获取发生时间格式化(toString)
	**/
	public String getCsrAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsrAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 发生时间 [非空]      
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
	/*******************************序列号**********************************/	
	/**
	* 序列号       
	**/
	public String getCsrSerial(){
		return this.csrSerial;
	}
	/**
	* 获取序列号格式化(toString)
	**/
	public String getCsrSerial$(){
		String strValue="";
		 strValue=$.str(this.getCsrSerial());
	 	 return strValue;
	}
	/**
	* 序列号       
	**/
	public void setCsrSerial(String csrSerial){
		this.csrSerial = csrSerial;
		this.setSeted(F.csrSerial);
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
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsIntegralRecord.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsIntegralRecord.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsIntegralRecord.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsIntegralRecord.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsIntegralRecord.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsIntegralRecord.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsIntegralRecord.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsIntegralRecord.this);
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
		/** 积分类型 [非空]       **/
		public M csrType(Object csrType){this.put("csrType", csrType);return this;};
	 	/** and csr_type is null */
 		public M csrTypeNull(){if(this.get("csrTypeNot")==null)this.put("csrTypeNot", "");this.put("csrType", null);return this;};
 		/** not .... */
 		public M csrTypeNot(){this.put("csrTypeNot", "not");return this;};
		/** 变化积分 [非空]       **/
		public M csrAmount(Object csrAmount){this.put("csrAmount", csrAmount);return this;};
	 	/** and csr_amount is null */
 		public M csrAmountNull(){if(this.get("csrAmountNot")==null)this.put("csrAmountNot", "");this.put("csrAmount", null);return this;};
 		/** not .... */
 		public M csrAmountNot(){this.put("csrAmountNot", "not");return this;};
		/** and csr_amount >= ? */
		public M csrAmountMin(Object min){this.put("csrAmountMin", min);return this;};
		/** and csr_amount <= ? */
		public M csrAmountMax(Object max){this.put("csrAmountMax", max);return this;};
		/** 剩余积分 [非空]    帐户余额或现金券余额或积分余量   **/
		public M csrRemain(Object csrRemain){this.put("csrRemain", csrRemain);return this;};
	 	/** and csr_remain is null */
 		public M csrRemainNull(){if(this.get("csrRemainNot")==null)this.put("csrRemainNot", "");this.put("csrRemain", null);return this;};
 		/** not .... */
 		public M csrRemainNot(){this.put("csrRemainNot", "not");return this;};
		/** and csr_remain >= ? */
		public M csrRemainMin(Object min){this.put("csrRemainMin", min);return this;};
		/** and csr_remain <= ? */
		public M csrRemainMax(Object max){this.put("csrRemainMax", max);return this;};
		/** 描述        **/
		public M csrRemark(Object csrRemark){this.put("csrRemark", csrRemark);return this;};
	 	/** and csr_remark is null */
 		public M csrRemarkNull(){if(this.get("csrRemarkNot")==null)this.put("csrRemarkNot", "");this.put("csrRemark", null);return this;};
 		/** not .... */
 		public M csrRemarkNot(){this.put("csrRemarkNot", "not");return this;};
		/** 发生时间 [非空]       **/
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
		/** 序列号        **/
		public M csrSerial(Object csrSerial){this.put("csrSerial", csrSerial);return this;};
	 	/** and csr_serial is null */
 		public M csrSerialNull(){if(this.get("csrSerialNot")==null)this.put("csrSerialNot", "");this.put("csrSerial", null);return this;};
 		/** not .... */
 		public M csrSerialNot(){this.put("csrSerialNot", "not");return this;};
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csrEditor(Object csrEditor){this.put("csrEditor", csrEditor);return this;};
	 	/** and csr_editor is null */
 		public M csrEditorNull(){if(this.get("csrEditorNot")==null)this.put("csrEditorNot", "");this.put("csrEditor", null);return this;};
 		/** not .... */
 		public M csrEditorNot(){this.put("csrEditorNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有积分记录 **/
		public @api List<CsIntegralRecord> list(Integer size){
			return getCsIntegralRecordList(this,size);
		}
		/** 获取积分记录分页 **/
		public @api Page<CsIntegralRecord> page(int page,int size){
			return getCsIntegralRecordPage(page, size , this);
		}
		/** 根据查询条件取积分记录 **/
		public @api CsIntegralRecord get(){
			return getCsIntegralRecord(this);
		}
		/** 获取积分记录数 **/
		public @api Long count(){
			return getCsIntegralRecordCount(this);
		}
		/** 获取积分记录表达式 **/
		public @api <T> T eval(String strEval){
			return getCsIntegralRecordEval(strEval,this);
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
			updateCsIntegralRecord(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csrId="csrId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csrHost="csrHost";
		/** 消费会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static @type(Long.class)  String csrMember="csrMember";
		/** 积分类型 [非空]       **/
		public final static @type(String.class) @like String csrType="csrType";
		/** 变化积分 [非空]       **/
		public final static @type(Double.class)  String csrAmount="csrAmount";
		/** and csr_amount >= ? */
		public final static @type(Double.class) String csrAmountMin="csrAmountMin";
		/** and csr_amount <= ? */
		public final static @type(Double.class) String csrAmountMax="csrAmountMax";
		/** 剩余积分 [非空]    帐户余额或现金券余额或积分余量   **/
		public final static @type(Double.class)  String csrRemain="csrRemain";
		/** and csr_remain >= ? */
		public final static @type(Double.class) String csrRemainMin="csrRemainMin";
		/** and csr_remain <= ? */
		public final static @type(Double.class) String csrRemainMax="csrRemainMax";
		/** 描述        **/
		public final static @type(String.class) @like String csrRemark="csrRemark";
		/** 发生时间 [非空]       **/
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
		/** 序列号        **/
		public final static @type(String.class) @like String csrSerial="csrSerial";
		/** 操作人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csrEditor="csrEditor";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csrId="csr_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csrHost="csr_host";
		/** 消费会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static String csrMember="csr_member";
		/** 积分类型 [非空]       **/
		public final static String csrType="csr_type";
		/** 变化积分 [非空]       **/
		public final static String csrAmount="csr_amount";
		/** 剩余积分 [非空]    帐户余额或现金券余额或积分余量   **/
		public final static String csrRemain="csr_remain";
		/** 描述        **/
		public final static String csrRemark="csr_remark";
		/** 发生时间 [非空]       **/
		public final static String csrAddTime="csr_add_time";
		/** 关联订单  [CsOrder]      **/
		public final static String csrOrder="csr_order";
		/** 关联编号     支付宝充值？等   **/
		public final static String csrRelate="csr_relate";
		/** 序列号        **/
		public final static String csrSerial="csr_serial";
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
				$.Set(name,CsIntegralRecord.getCsIntegralRecord(params));
			else
				$.Set(name,CsIntegralRecord.getCsIntegralRecordList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取积分记录数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsIntegralRecord) $.GetRequest("CsIntegralRecord$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsIntegralRecord.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsIntegralRecord.getCsIntegralRecord(params);
			else
				value = CsIntegralRecord.getCsIntegralRecordList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsIntegralRecord.Get($.add(CsIntegralRecord.F.csrId,param));
		else if(!$.empty(param.toString()))
			value = CsIntegralRecord.get(Long.valueOf(param.toString()));
		$.SetRequest("CsIntegralRecord$"+param.hashCode(), value);
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
	public void mergeSet(CsIntegralRecord old){
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