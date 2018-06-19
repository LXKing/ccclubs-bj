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

@namespace("finance/cashrecord")
public @caption("提现记录") @table("cs_cash_record") class CsCashRecord implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cscr_id")   @primary  @note("  ") Long cscrId;// 主键 非空     
	private @caption("城市") @column("cscr_host")    @relate("$cscrHost") @RelateClass(SrvHost.class)  @note("  ") Long cscrHost;// 非空     
 	private SrvHost $cscrHost;//关联对象[运营城市]
	private @caption("流水号") @column("cscr_trade_no")    @note("  运营商+城市+年月日+年月日") String cscrTradeNo;// 非空  运营商+城市+年月日+年月日   
	private @caption("提取额") @column("cscr_ammount")    @note("  ") Double cscrAmmount;// 非空     
	private @caption("摘要") @column("cscr_title")    @note("  ") String cscrTitle;// 非空     
	//private @caption("详单明细") @RelateClass(CsOperRecord.class) List<CsOperRecord> io;//反向关联的收支记录列表
	private @caption("付款信息") @column("cscr_voucher")    @note("  描述财务打款的信息，方便查账") String cscrVoucher;//  描述财务打款的信息，方便查账   
	private @caption("付款截图") @column("cscr_image")  @hidden   @note("  ") String cscrImage;//     
	private @caption("添加人") @column("cscr_adder")    @relate("$cscrAdder") @RelateClass(SrvUser.class)  @note("  ") Long cscrAdder;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cscrAdder;//关联对象[用户]
	private @caption("审核人") @column("cscr_checker")    @relate("$cscrChecker") @RelateClass(SrvUser.class)  @note("  ") Long cscrChecker;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cscrChecker;//关联对象[用户]
	private @caption("备注") @column("cscr_remark")    @note("  ") String cscrRemark;//     
	private @caption("修改时间") @column("cscr_update_time")    @note("  ") Date cscrUpdateTime;// 非空     
	private @caption("添加时间") @column("cscr_add_time")    @note("  ") Date cscrAddTime;// 非空     
	private @caption("状态") @column("cscr_status")    @note(" 1:待审核 2:已审核 3:已完成 0:未通过  ") Short cscrStatus;// 非空 1:待审核 2:已审核 3:已完成 0:未通过     
	
	//默认构造函数
	public CsCashRecord(){
	
	}
	
	//主键构造函数
	public CsCashRecord(Long id){
		this.cscrId = id;
	}
	
	/**所有字段构造函数 CsCashRecord(cscrHost,cscrTradeNo,cscrAmmount,cscrTitle,cscrVoucher,cscrImage,cscrAdder,cscrChecker,cscrRemark,cscrUpdateTime,cscrAddTime,cscrStatus)
	 CsCashRecord(
	 	$.getLong("cscrHost")//城市 [非空]
	 	,$.getString("cscrTradeNo")//流水号 [非空]
	 	,$.getDouble("cscrAmmount")//提取额 [非空]
	 	,$.getString("cscrTitle")//摘要 [非空]
	 	,$.getString("cscrVoucher")//付款信息
	 	,$.getString("cscrImage")//付款截图
	 	,$.getLong("cscrAdder")//添加人 [非空]
	 	,$.getLong("cscrChecker")//审核人
	 	,$.getString("cscrRemark")//备注
	 	,$.getDate("cscrUpdateTime")//修改时间 [非空]
	 	,$.getDate("cscrAddTime")//添加时间 [非空]
	 	,$.getShort("cscrStatus")//状态 [非空]
	 )
	**/
	public CsCashRecord(Long cscrHost,String cscrTradeNo,Double cscrAmmount,String cscrTitle,String cscrVoucher,String cscrImage,Long cscrAdder,Long cscrChecker,String cscrRemark,Date cscrUpdateTime,Date cscrAddTime,Short cscrStatus){
		this.cscrHost=cscrHost;
		this.cscrTradeNo=cscrTradeNo;
		this.cscrAmmount=cscrAmmount;
		this.cscrTitle=cscrTitle;
		this.cscrVoucher=cscrVoucher;
		this.cscrImage=cscrImage;
		this.cscrAdder=cscrAdder;
		this.cscrChecker=cscrChecker;
		this.cscrRemark=cscrRemark;
		this.cscrUpdateTime=cscrUpdateTime;
		this.cscrAddTime=cscrAddTime;
		this.cscrStatus=cscrStatus;
	}
	
	//设置非空字段
	public CsCashRecord setNotNull(Long cscrHost,String cscrTradeNo,Double cscrAmmount,String cscrTitle,Long cscrAdder,Date cscrUpdateTime,Date cscrAddTime,Short cscrStatus){
		this.cscrHost=cscrHost;
		this.cscrTradeNo=cscrTradeNo;
		this.cscrAmmount=cscrAmmount;
		this.cscrTitle=cscrTitle;
		this.cscrAdder=cscrAdder;
		this.cscrUpdateTime=cscrUpdateTime;
		this.cscrAddTime=cscrAddTime;
		this.cscrStatus=cscrStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsCashRecord cscrId(Long cscrId){
		this.cscrId = cscrId;
		this.setSeted(F.cscrId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsCashRecord cscrHost(Long cscrHost){
		this.cscrHost = cscrHost;
		this.setSeted(F.cscrHost);
		return this;
	}
	/** 流水号 [非空]    运营商+城市+年月日+年月日   **/
	public CsCashRecord cscrTradeNo(String cscrTradeNo){
		this.cscrTradeNo = cscrTradeNo;
		this.setSeted(F.cscrTradeNo);
		return this;
	}
	/** 提取额 [非空]       **/
	public CsCashRecord cscrAmmount(Double cscrAmmount){
		this.cscrAmmount = cscrAmmount;
		this.setSeted(F.cscrAmmount);
		return this;
	}
	/** 摘要 [非空]       **/
	public CsCashRecord cscrTitle(String cscrTitle){
		this.cscrTitle = cscrTitle;
		this.setSeted(F.cscrTitle);
		return this;
	}
	/** 付款信息     描述财务打款的信息，方便查账   **/
	public CsCashRecord cscrVoucher(String cscrVoucher){
		this.cscrVoucher = cscrVoucher;
		this.setSeted(F.cscrVoucher);
		return this;
	}
	/** 付款截图        **/
	public CsCashRecord cscrImage(String cscrImage){
		this.cscrImage = cscrImage;
		this.setSeted(F.cscrImage);
		return this;
	}
	/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsCashRecord cscrAdder(Long cscrAdder){
		this.cscrAdder = cscrAdder;
		this.setSeted(F.cscrAdder);
		return this;
	}
	/** 审核人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsCashRecord cscrChecker(Long cscrChecker){
		this.cscrChecker = cscrChecker;
		this.setSeted(F.cscrChecker);
		return this;
	}
	/** 备注        **/
	public CsCashRecord cscrRemark(String cscrRemark){
		this.cscrRemark = cscrRemark;
		this.setSeted(F.cscrRemark);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsCashRecord cscrUpdateTime(Date cscrUpdateTime){
		this.cscrUpdateTime = cscrUpdateTime;
		this.setSeted(F.cscrUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsCashRecord cscrAddTime(Date cscrAddTime){
		this.cscrAddTime = cscrAddTime;
		this.setSeted(F.cscrAddTime);
		return this;
	}
	/** 状态 [非空]   1:待审核 2:已审核 3:已完成 0:未通过     **/
	public CsCashRecord cscrStatus(Short cscrStatus){
		this.cscrStatus = cscrStatus;
		this.setSeted(F.cscrStatus);
		return this;
	}
	
	
	//克隆对象
	public CsCashRecord clone(){
		CsCashRecord clone = new CsCashRecord();
		clone.cscrHost=this.cscrHost;
		clone.cscrTradeNo=this.cscrTradeNo;
		clone.cscrAmmount=this.cscrAmmount;
		clone.cscrTitle=this.cscrTitle;
		clone.cscrAdder=this.cscrAdder;
		clone.cscrUpdateTime=this.cscrUpdateTime;
		clone.cscrAddTime=this.cscrAddTime;
		clone.cscrStatus=this.cscrStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取提现记录
	 * @param id
	 * @return
	 */
	public static @api CsCashRecord get(Long id){		
		return getCsCashRecordById(id);
	}
	/**
	 * 获取所有提现记录
	 * @return
	 */
	public static @api List<CsCashRecord> list(Map params,Integer size){
		return getCsCashRecordList(params,size);
	}
	/**
	 * 获取提现记录分页
	 * @return
	 */
	public static @api Page<CsCashRecord> page(int page,int size,Map params){
		return getCsCashRecordPage(page, size , params);
	}
	/**
	 * 根据查询条件取提现记录
	 * @param params
	 * @return
	 */
	public static @api CsCashRecord Get(Map params){
		return getCsCashRecord(params);
	}
	/**
	 * 获取提现记录数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsCashRecordCount(params);
	}
	/**
	 * 获取提现记录数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsCashRecordEval(eval,params);
	}
	
	/**
	 * 根据ID取提现记录
	 * @param id
	 * @return
	 */
	public static @api CsCashRecord getCsCashRecordById(Long id){		
		CsCashRecord csCashRecord = (CsCashRecord) $.GetRequest("CsCashRecord$"+id);
		if(csCashRecord!=null)
			return csCashRecord;
		com.ccclubs.service.admin.ICsCashRecordService csCashRecordService = $.GetSpringBean("csCashRecordService");		
		return csCashRecordService.getCsCashRecordById(id);
	}
	
	
	/**
	 * 根据ID取提现记录的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsCashRecord.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsCashRecord csCashRecord = get(id);
		if(csCashRecord!=null){
			String strValue = csCashRecord.getCscrTradeNo$();
			if(!"CscrTradeNo".equals("CscrTradeNo"))
				strValue+="("+csCashRecord.getCscrTradeNo$()+")";
			MemCache.setValue(CsCashRecord.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCscrTradeNo$();
		if(!"CscrTradeNo".equals("CscrTradeNo"))
			keyValue+="("+this.getCscrTradeNo$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有提现记录
	 * @return
	 */
	public static @api List<CsCashRecord> getCsCashRecordList(Map params,Integer size){
		com.ccclubs.service.admin.ICsCashRecordService csCashRecordService = $.GetSpringBean("csCashRecordService");
		return csCashRecordService.getCsCashRecordList(params, size);
	}
	
	/**
	 * 获取提现记录分页
	 * @return
	 */
	public static @api Page<CsCashRecord> getCsCashRecordPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsCashRecordService csCashRecordService = $.GetSpringBean("csCashRecordService");
		return csCashRecordService.getCsCashRecordPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取提现记录
	 * @param params
	 * @return
	 */
	public static @api CsCashRecord getCsCashRecord(Map params){
		com.ccclubs.service.admin.ICsCashRecordService csCashRecordService = $.GetSpringBean("csCashRecordService");
		return csCashRecordService.getCsCashRecord(params);
	}
	
	/**
	 * 获取提现记录数
	 * @return
	 */
	public static @api Long getCsCashRecordCount(Map params){
		com.ccclubs.service.admin.ICsCashRecordService csCashRecordService = $.GetSpringBean("csCashRecordService");
		return csCashRecordService.getCsCashRecordCount(params);
	}
		
		
	/**
	 * 获取提现记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsCashRecordEval(String eval,Map params){
		com.ccclubs.service.admin.ICsCashRecordService csCashRecordService = $.GetSpringBean("csCashRecordService");
		return csCashRecordService.getCsCashRecordEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsCashRecord(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsCashRecordService csCashRecordService = $.GetSpringBean("csCashRecordService");
		csCashRecordService.updateCsCashRecordByConfirm(set, where);
	}
	
	
	/**
	 * 保存提现记录对象
	 * @param params
	 * @return
	 */
	public CsCashRecord save(){
		com.ccclubs.service.admin.ICsCashRecordService csCashRecordService = $.GetSpringBean("csCashRecordService");
		if(this.getCscrId()!=null)
			csCashRecordService.updateCsCashRecord(this);
		else
			return csCashRecordService.saveCsCashRecord(this);
		return this;
	}
	
	
	/**
	 * 更新提现记录对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsCashRecordService csCashRecordService = $.GetSpringBean("csCashRecordService");
		csCashRecordService.updateCsCashRecord$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsCashRecordService csCashRecordService = $.GetSpringBean("csCashRecordService");
		if($.equals($.config("logic_delete"),"true"))
			csCashRecordService.removeCsCashRecordById(this.getCscrId());
		else
			csCashRecordService.deleteCsCashRecordById(this.getCscrId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsCashRecordService csCashRecordService = $.GetSpringBean("csCashRecordService");
		return csCashRecordService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCscrId(){
		return this.cscrId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCscrId$(){
		String strValue="";
		 strValue=$.str(this.getCscrId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCscrId(Long cscrId){
		this.cscrId = cscrId;
		this.setSeted(F.cscrId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCscrHost(){
		return this.cscrHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCscrHost$(){
		String strValue="";
		if(this.getCscrHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCscrHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCscrHost(Long cscrHost){
		this.cscrHost = cscrHost;
		this.setSeted(F.cscrHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cscrHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCscrHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCscrHost()!=null){
 			srvHost = SrvHost.get(this.getCscrHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCscrHost(), srvHost);
	 	return srvHost;
	}
	/*******************************流水号**********************************/	
	/**
	* 流水号 [非空]    运营商+城市+年月日+年月日  
	**/
	public String getCscrTradeNo(){
		return this.cscrTradeNo;
	}
	/**
	* 获取流水号格式化(toString)
	**/
	public String getCscrTradeNo$(){
		String strValue="";
		 strValue=$.str(this.getCscrTradeNo());
	 	 return strValue;
	}
	/**
	* 流水号 [非空]    运营商+城市+年月日+年月日  
	**/
	public void setCscrTradeNo(String cscrTradeNo){
		this.cscrTradeNo = cscrTradeNo;
		this.setSeted(F.cscrTradeNo);
	}
	/*******************************提取额**********************************/	
	/**
	* 提取额 [非空]      
	**/
	public Double getCscrAmmount(){
		return this.cscrAmmount;
	}
	/**
	* 获取提取额格式化(toString)
	**/
	public String getCscrAmmount$(){
		String strValue="";
		 strValue=$.str(this.getCscrAmmount());	
	 	 return strValue;
	}
	/**
	* 提取额 [非空]      
	**/
	public void setCscrAmmount(Double cscrAmmount){
		this.cscrAmmount = cscrAmmount;
		this.setSeted(F.cscrAmmount);
	}
	/*******************************摘要**********************************/	
	/**
	* 摘要 [非空]      
	**/
	public String getCscrTitle(){
		return this.cscrTitle;
	}
	/**
	* 获取摘要格式化(toString)
	**/
	public String getCscrTitle$(){
		String strValue="";
		 strValue=$.str(this.getCscrTitle());
	 	 return strValue;
	}
	/**
	* 摘要 [非空]      
	**/
	public void setCscrTitle(String cscrTitle){
		this.cscrTitle = cscrTitle;
		this.setSeted(F.cscrTitle);
	}
	/*******************************反向关联的收支记录列表**********************************/	
	/**
	* 获取与当前对象反向关联的收支记录列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsOperRecord> getIo(){
		com.ccclubs.service.admin.ICsOperRecordService csOperRecordService = $.GetSpringBean("csOperRecordService");
		Map params = new HashMap();
		params.put("csorCashNo",this.getCscrId());
		return csOperRecordService.getCsOperRecordList(params,-1);
	}
	/**
	* 获取详单明细格式化(toString)
	**/
	public String getIo$(){
		return "[...]";
	}
	/*******************************付款信息**********************************/	
	/**
	* 付款信息     描述财务打款的信息，方便查账  
	**/
	public String getCscrVoucher(){
		return this.cscrVoucher;
	}
	/**
	* 获取付款信息格式化(toString)
	**/
	public String getCscrVoucher$(){
		String strValue="";
		 strValue=$.str(this.getCscrVoucher());
	 	 return strValue;
	}
	/**
	* 付款信息     描述财务打款的信息，方便查账  
	**/
	public void setCscrVoucher(String cscrVoucher){
		this.cscrVoucher = cscrVoucher;
		this.setSeted(F.cscrVoucher);
	}
	/*******************************付款截图**********************************/	
	/**
	* 付款截图       
	**/
	public String getCscrImage(){
		return this.cscrImage;
	}
	/**
	* 获取付款截图格式化(toString)
	**/
	public String getCscrImage$(){
		String strValue="";
		 strValue=$.str(this.getCscrImage());
	 	 return strValue;
	}
	/**
	* 付款截图       
	**/
	public void setCscrImage(String cscrImage){
		this.cscrImage = cscrImage;
		this.setSeted(F.cscrImage);
	}
	/*******************************添加人**********************************/	
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCscrAdder(){
		return this.cscrAdder;
	}
	/**
	* 获取添加人格式化(toString)
	**/
	public String getCscrAdder$(){
		String strValue="";
		if(this.getCscrAdder()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCscrAdder()));
		}			
	 	 return strValue;
	}
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCscrAdder(Long cscrAdder){
		this.cscrAdder = cscrAdder;
		this.setSeted(F.cscrAdder);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cscrAdder(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCscrAdder());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCscrAdder()!=null){
 			srvUser = SrvUser.get(this.getCscrAdder());
 		}
 		$.SetRequest("SrvUser$"+this.getCscrAdder(), srvUser);
	 	return srvUser;
	}
	/*******************************审核人**********************************/	
	/**
	* 审核人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCscrChecker(){
		return this.cscrChecker;
	}
	/**
	* 获取审核人格式化(toString)
	**/
	public String getCscrChecker$(){
		String strValue="";
		if(this.getCscrChecker()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCscrChecker()));
		}			
	 	 return strValue;
	}
	/**
	* 审核人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCscrChecker(Long cscrChecker){
		this.cscrChecker = cscrChecker;
		this.setSeted(F.cscrChecker);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cscrChecker(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCscrChecker());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCscrChecker()!=null){
 			srvUser = SrvUser.get(this.getCscrChecker());
 		}
 		$.SetRequest("SrvUser$"+this.getCscrChecker(), srvUser);
	 	return srvUser;
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getCscrRemark(){
		return this.cscrRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCscrRemark$(){
		String strValue="";
		 strValue=$.str(this.getCscrRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setCscrRemark(String cscrRemark){
		this.cscrRemark = cscrRemark;
		this.setSeted(F.cscrRemark);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCscrUpdateTime(){
		return this.cscrUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCscrUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscrUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCscrUpdateTime(Date cscrUpdateTime){
		this.cscrUpdateTime = cscrUpdateTime;
		this.setSeted(F.cscrUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCscrAddTime(){
		return this.cscrAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCscrAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscrAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCscrAddTime(Date cscrAddTime){
		this.cscrAddTime = cscrAddTime;
		this.setSeted(F.cscrAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:待审核 2:已审核 3:已完成 0:未通过    
	**/
	public Short getCscrStatus(){
		return this.cscrStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCscrStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCscrStatus()),"1"))
			strValue=$.str("待审核");		 
		 if($.equals($.str(this.getCscrStatus()),"2"))
			strValue=$.str("已审核");		 
		 if($.equals($.str(this.getCscrStatus()),"3"))
			strValue=$.str("已完成");		 
		 if($.equals($.str(this.getCscrStatus()),"0"))
			strValue=$.str("未通过");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:待审核 2:已审核 3:已完成 0:未通过    
	**/
	public void setCscrStatus(Short cscrStatus){
		this.cscrStatus = cscrStatus;
		this.setSeted(F.cscrStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsCashRecord.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCashRecord.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCashRecord.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsCashRecord.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsCashRecord.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCashRecord.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCashRecord.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsCashRecord.this);
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
		public M cscrId(Object cscrId){this.put("cscrId", cscrId);return this;};
	 	/** and cscr_id is null */
 		public M cscrIdNull(){if(this.get("cscrIdNot")==null)this.put("cscrIdNot", "");this.put("cscrId", null);return this;};
 		/** not .... */
 		public M cscrIdNot(){this.put("cscrIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M cscrHost(Object cscrHost){this.put("cscrHost", cscrHost);return this;};
	 	/** and cscr_host is null */
 		public M cscrHostNull(){if(this.get("cscrHostNot")==null)this.put("cscrHostNot", "");this.put("cscrHost", null);return this;};
 		/** not .... */
 		public M cscrHostNot(){this.put("cscrHostNot", "not");return this;};
		/** 流水号 [非空]    运营商+城市+年月日+年月日   **/
		public M cscrTradeNo(Object cscrTradeNo){this.put("cscrTradeNo", cscrTradeNo);return this;};
	 	/** and cscr_trade_no is null */
 		public M cscrTradeNoNull(){if(this.get("cscrTradeNoNot")==null)this.put("cscrTradeNoNot", "");this.put("cscrTradeNo", null);return this;};
 		/** not .... */
 		public M cscrTradeNoNot(){this.put("cscrTradeNoNot", "not");return this;};
		/** 提取额 [非空]       **/
		public M cscrAmmount(Object cscrAmmount){this.put("cscrAmmount", cscrAmmount);return this;};
	 	/** and cscr_ammount is null */
 		public M cscrAmmountNull(){if(this.get("cscrAmmountNot")==null)this.put("cscrAmmountNot", "");this.put("cscrAmmount", null);return this;};
 		/** not .... */
 		public M cscrAmmountNot(){this.put("cscrAmmountNot", "not");return this;};
		/** and cscr_ammount >= ? */
		public M cscrAmmountMin(Object min){this.put("cscrAmmountMin", min);return this;};
		/** and cscr_ammount <= ? */
		public M cscrAmmountMax(Object max){this.put("cscrAmmountMax", max);return this;};
		/** 摘要 [非空]       **/
		public M cscrTitle(Object cscrTitle){this.put("cscrTitle", cscrTitle);return this;};
	 	/** and cscr_title is null */
 		public M cscrTitleNull(){if(this.get("cscrTitleNot")==null)this.put("cscrTitleNot", "");this.put("cscrTitle", null);return this;};
 		/** not .... */
 		public M cscrTitleNot(){this.put("cscrTitleNot", "not");return this;};
		/** 付款信息     描述财务打款的信息，方便查账   **/
		public M cscrVoucher(Object cscrVoucher){this.put("cscrVoucher", cscrVoucher);return this;};
	 	/** and cscr_voucher is null */
 		public M cscrVoucherNull(){if(this.get("cscrVoucherNot")==null)this.put("cscrVoucherNot", "");this.put("cscrVoucher", null);return this;};
 		/** not .... */
 		public M cscrVoucherNot(){this.put("cscrVoucherNot", "not");return this;};
		/** 付款截图        **/
		public M cscrImage(Object cscrImage){this.put("cscrImage", cscrImage);return this;};
	 	/** and cscr_image is null */
 		public M cscrImageNull(){if(this.get("cscrImageNot")==null)this.put("cscrImageNot", "");this.put("cscrImage", null);return this;};
 		/** not .... */
 		public M cscrImageNot(){this.put("cscrImageNot", "not");return this;};
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cscrAdder(Object cscrAdder){this.put("cscrAdder", cscrAdder);return this;};
	 	/** and cscr_adder is null */
 		public M cscrAdderNull(){if(this.get("cscrAdderNot")==null)this.put("cscrAdderNot", "");this.put("cscrAdder", null);return this;};
 		/** not .... */
 		public M cscrAdderNot(){this.put("cscrAdderNot", "not");return this;};
		/** 审核人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cscrChecker(Object cscrChecker){this.put("cscrChecker", cscrChecker);return this;};
	 	/** and cscr_checker is null */
 		public M cscrCheckerNull(){if(this.get("cscrCheckerNot")==null)this.put("cscrCheckerNot", "");this.put("cscrChecker", null);return this;};
 		/** not .... */
 		public M cscrCheckerNot(){this.put("cscrCheckerNot", "not");return this;};
		/** 备注        **/
		public M cscrRemark(Object cscrRemark){this.put("cscrRemark", cscrRemark);return this;};
	 	/** and cscr_remark is null */
 		public M cscrRemarkNull(){if(this.get("cscrRemarkNot")==null)this.put("cscrRemarkNot", "");this.put("cscrRemark", null);return this;};
 		/** not .... */
 		public M cscrRemarkNot(){this.put("cscrRemarkNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M cscrUpdateTime(Object cscrUpdateTime){this.put("cscrUpdateTime", cscrUpdateTime);return this;};
	 	/** and cscr_update_time is null */
 		public M cscrUpdateTimeNull(){if(this.get("cscrUpdateTimeNot")==null)this.put("cscrUpdateTimeNot", "");this.put("cscrUpdateTime", null);return this;};
 		/** not .... */
 		public M cscrUpdateTimeNot(){this.put("cscrUpdateTimeNot", "not");return this;};
 		/** and cscr_update_time >= ? */
 		public M cscrUpdateTimeStart(Object start){this.put("cscrUpdateTimeStart", start);return this;};			
 		/** and cscr_update_time <= ? */
 		public M cscrUpdateTimeEnd(Object end){this.put("cscrUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cscrAddTime(Object cscrAddTime){this.put("cscrAddTime", cscrAddTime);return this;};
	 	/** and cscr_add_time is null */
 		public M cscrAddTimeNull(){if(this.get("cscrAddTimeNot")==null)this.put("cscrAddTimeNot", "");this.put("cscrAddTime", null);return this;};
 		/** not .... */
 		public M cscrAddTimeNot(){this.put("cscrAddTimeNot", "not");return this;};
 		/** and cscr_add_time >= ? */
 		public M cscrAddTimeStart(Object start){this.put("cscrAddTimeStart", start);return this;};			
 		/** and cscr_add_time <= ? */
 		public M cscrAddTimeEnd(Object end){this.put("cscrAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:待审核 2:已审核 3:已完成 0:未通过     **/
		public M cscrStatus(Object cscrStatus){this.put("cscrStatus", cscrStatus);return this;};
	 	/** and cscr_status is null */
 		public M cscrStatusNull(){if(this.get("cscrStatusNot")==null)this.put("cscrStatusNot", "");this.put("cscrStatus", null);return this;};
 		/** not .... */
 		public M cscrStatusNot(){this.put("cscrStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有提现记录 **/
		public @api List<CsCashRecord> list(Integer size){
			return getCsCashRecordList(this,size);
		}
		/** 获取提现记录分页 **/
		public @api Page<CsCashRecord> page(int page,int size){
			return getCsCashRecordPage(page, size , this);
		}
		/** 根据查询条件取提现记录 **/
		public @api CsCashRecord get(){
			return getCsCashRecord(this);
		}
		/** 获取提现记录数 **/
		public @api Long count(){
			return getCsCashRecordCount(this);
		}
		/** 获取提现记录表达式 **/
		public @api <T> T eval(String strEval){
			return getCsCashRecordEval(strEval,this);
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
			updateCsCashRecord(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscrId="cscrId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cscrHost="cscrHost";
		/** 流水号 [非空]    运营商+城市+年月日+年月日   **/
		public final static @type(String.class) @like String cscrTradeNo="cscrTradeNo";
		/** 提取额 [非空]       **/
		public final static @type(Double.class)  String cscrAmmount="cscrAmmount";
		/** and cscr_ammount >= ? */
		public final static @type(Double.class) String cscrAmmountMin="cscrAmmountMin";
		/** and cscr_ammount <= ? */
		public final static @type(Double.class) String cscrAmmountMax="cscrAmmountMax";
		/** 摘要 [非空]       **/
		public final static @type(String.class) @like String cscrTitle="cscrTitle";
		/** 付款信息     描述财务打款的信息，方便查账   **/
		public final static @type(String.class) @like String cscrVoucher="cscrVoucher";
		/** 付款截图        **/
		public final static @type(String.class)  String cscrImage="cscrImage";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String cscrAdder="cscrAdder";
		/** 审核人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String cscrChecker="cscrChecker";
		/** 备注        **/
		public final static @type(String.class) @like String cscrRemark="cscrRemark";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cscrUpdateTime="cscrUpdateTime";
	 	/** and cscr_update_time >= ? */
 		public final static @type(Date.class) String cscrUpdateTimeStart="cscrUpdateTimeStart";
 		/** and cscr_update_time <= ? */
 		public final static @type(Date.class) String cscrUpdateTimeEnd="cscrUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cscrAddTime="cscrAddTime";
	 	/** and cscr_add_time >= ? */
 		public final static @type(Date.class) String cscrAddTimeStart="cscrAddTimeStart";
 		/** and cscr_add_time <= ? */
 		public final static @type(Date.class) String cscrAddTimeEnd="cscrAddTimeEnd";
		/** 状态 [非空]   1:待审核 2:已审核 3:已完成 0:未通过     **/
		public final static @type(Short.class)  String cscrStatus="cscrStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscrId="cscr_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cscrHost="cscr_host";
		/** 流水号 [非空]    运营商+城市+年月日+年月日   **/
		public final static String cscrTradeNo="cscr_trade_no";
		/** 提取额 [非空]       **/
		public final static String cscrAmmount="cscr_ammount";
		/** 摘要 [非空]       **/
		public final static String cscrTitle="cscr_title";
		/** 付款信息     描述财务打款的信息，方便查账   **/
		public final static String cscrVoucher="cscr_voucher";
		/** 付款截图        **/
		public final static String cscrImage="cscr_image";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cscrAdder="cscr_adder";
		/** 审核人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cscrChecker="cscr_checker";
		/** 备注        **/
		public final static String cscrRemark="cscr_remark";
		/** 修改时间 [非空]       **/
		public final static String cscrUpdateTime="cscr_update_time";
		/** 添加时间 [非空]       **/
		public final static String cscrAddTime="cscr_add_time";
		/** 状态 [非空]   1:待审核 2:已审核 3:已完成 0:未通过     **/
		public final static String cscrStatus="cscr_status";
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
				$.Set(name,CsCashRecord.getCsCashRecord(params));
			else
				$.Set(name,CsCashRecord.getCsCashRecordList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取提现记录数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsCashRecord) $.GetRequest("CsCashRecord$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsCashRecord.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsCashRecord.getCsCashRecord(params);
			else
				value = CsCashRecord.getCsCashRecordList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsCashRecord.Get($.add(CsCashRecord.F.cscrId,param));
		else if(!$.empty(param.toString()))
			value = CsCashRecord.get(Long.valueOf(param.toString()));
		$.SetRequest("CsCashRecord$"+param.hashCode(), value);
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
	public void mergeSet(CsCashRecord old){
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