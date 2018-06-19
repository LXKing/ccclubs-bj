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

@namespace("object/rcgcard")
public @caption("充值卡") @table("cs_recharge_card") class CsRechargeCard implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csrc_id")   @primary  @note("  ") Long csrcId;// 主键 非空     
	private @caption("城市") @column("csrc_host")    @relate("$csrcHost") @RelateClass(SrvHost.class)  @note("  ") Long csrcHost;// 非空     
 	private SrvHost $csrcHost;//关联对象[运营城市]
	private @caption("卡号") @column("csrc_number")    @note("  ") String csrcNumber;// 非空     
	private @caption("密码") @column("csrc_password")    @note("  ") String csrcPassword;// 非空     
	private @caption("类型") @column("csrc_type")    @note(" 0:现金券 1:免费小时 2:红包  ") Short csrcType;// 非空 0:现金券 1:免费小时 2:红包     
	private @caption("面额") @column("csrc_denomination")    @note("  ") Double csrcDenomination;// 非空     
	private @caption("分类") @column("csrc_method")    @relate("$csrcMethod") @RelateClass(SrvProperty.class)  @note(" rcgcard_method:充值卡分类  ") Long csrcMethod;// 非空 rcgcard_method:充值卡分类     
 	private SrvProperty $csrcMethod;//关联对象[系统属性]
	private @caption("内容有效期") @column("csrc_validity")    @note(" 1:1个月 2:2个月 3:3个月 6:半年 12:1年 0:自定义  指明该充值卡对应的内容的有效期时长") Short csrcValidity;// 非空 1:1个月 2:2个月 3:3个月 6:半年 12:1年 0:自定义  指明该充值卡对应的内容的有效期时长   
	private @caption("内容有效至") @column("csrc_validity_util")    @note("  ") Date csrcValidityUtil;//     
	private @caption("充值有效至") @column("csrc_util")    @note("  指明该充值卡的有效时间") Date csrcUtil;//  指明该充值卡的有效时间   
	private @caption("使用会员") @column("csrc_member")    @relate("$csrcMember") @RelateClass(CsMember.class)  @note("  ") Long csrcMember;//     
 	private CsMember $csrcMember;//关联对象[会员帐号]
	private @caption("使用时间") @column("csrc_use_time")    @note("  ") Date csrcUseTime;//     
	private @caption("添加时间") @column("csrc_add_time")    @note("  ") Date csrcAddTime;// 非空     
	private @caption("状态") @column("csrc_status")    @note(" 1:库存 2:出库(发出) 3:已使用 0:无效卡  ") Short csrcStatus;// 非空 1:库存 2:出库(发出) 3:已使用 0:无效卡     
	
	//默认构造函数
	public CsRechargeCard(){
	
	}
	
	//主键构造函数
	public CsRechargeCard(Long id){
		this.csrcId = id;
	}
	
	/**所有字段构造函数 CsRechargeCard(csrcHost,csrcNumber,csrcPassword,csrcType,csrcDenomination,csrcMethod,csrcValidity,csrcValidityUtil,csrcUtil,csrcMember,csrcUseTime,csrcAddTime,csrcStatus)
	 CsRechargeCard(
	 	$.getLong("csrcHost")//城市 [非空]
	 	,$.getString("csrcNumber")//卡号 [非空]
	 	,$.getString("csrcPassword")//密码 [非空]
	 	,$.getShort("csrcType")//类型 [非空]
	 	,$.getDouble("csrcDenomination")//面额 [非空]
	 	,$.getLong("csrcMethod")//分类 [非空]
	 	,$.getShort("csrcValidity")//内容有效期 [非空]
	 	,$.getDate("csrcValidityUtil")//内容有效至
	 	,$.getDate("csrcUtil")//充值有效至
	 	,$.getLong("csrcMember")//使用会员
	 	,$.getDate("csrcUseTime")//使用时间
	 	,$.getDate("csrcAddTime")//添加时间 [非空]
	 	,$.getShort("csrcStatus")//状态 [非空]
	 )
	**/
	public CsRechargeCard(Long csrcHost,String csrcNumber,String csrcPassword,Short csrcType,Double csrcDenomination,Long csrcMethod,Short csrcValidity,Date csrcValidityUtil,Date csrcUtil,Long csrcMember,Date csrcUseTime,Date csrcAddTime,Short csrcStatus){
		this.csrcHost=csrcHost;
		this.csrcNumber=csrcNumber;
		this.csrcPassword=csrcPassword;
		this.csrcType=csrcType;
		this.csrcDenomination=csrcDenomination;
		this.csrcMethod=csrcMethod;
		this.csrcValidity=csrcValidity;
		this.csrcValidityUtil=csrcValidityUtil;
		this.csrcUtil=csrcUtil;
		this.csrcMember=csrcMember;
		this.csrcUseTime=csrcUseTime;
		this.csrcAddTime=csrcAddTime;
		this.csrcStatus=csrcStatus;
	}
	
	//设置非空字段
	public CsRechargeCard setNotNull(Long csrcHost,String csrcNumber,String csrcPassword,Short csrcType,Double csrcDenomination,Long csrcMethod,Short csrcValidity,Date csrcAddTime,Short csrcStatus){
		this.csrcHost=csrcHost;
		this.csrcNumber=csrcNumber;
		this.csrcPassword=csrcPassword;
		this.csrcType=csrcType;
		this.csrcDenomination=csrcDenomination;
		this.csrcMethod=csrcMethod;
		this.csrcValidity=csrcValidity;
		this.csrcAddTime=csrcAddTime;
		this.csrcStatus=csrcStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsRechargeCard csrcId(Long csrcId){
		this.csrcId = csrcId;
		this.setSeted(F.csrcId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsRechargeCard csrcHost(Long csrcHost){
		this.csrcHost = csrcHost;
		this.setSeted(F.csrcHost);
		return this;
	}
	/** 卡号 [非空]       **/
	public CsRechargeCard csrcNumber(String csrcNumber){
		this.csrcNumber = csrcNumber;
		this.setSeted(F.csrcNumber);
		return this;
	}
	/** 密码 [非空]       **/
	public CsRechargeCard csrcPassword(String csrcPassword){
		this.csrcPassword = csrcPassword;
		this.setSeted(F.csrcPassword);
		return this;
	}
	/** 类型 [非空]   0:现金券 1:免费小时 2:红包     **/
	public CsRechargeCard csrcType(Short csrcType){
		this.csrcType = csrcType;
		this.setSeted(F.csrcType);
		return this;
	}
	/** 面额 [非空]       **/
	public CsRechargeCard csrcDenomination(Double csrcDenomination){
		this.csrcDenomination = csrcDenomination;
		this.setSeted(F.csrcDenomination);
		return this;
	}
	/** 分类 [非空] [SrvProperty]  rcgcard_method:充值卡分类     **/
	public CsRechargeCard csrcMethod(Long csrcMethod){
		this.csrcMethod = csrcMethod;
		this.setSeted(F.csrcMethod);
		return this;
	}
	/** 内容有效期 [非空]   1:1个月 2:2个月 3:3个月 6:半年 12:1年 0:自定义  指明该充值卡对应的内容的有效期时长   **/
	public CsRechargeCard csrcValidity(Short csrcValidity){
		this.csrcValidity = csrcValidity;
		this.setSeted(F.csrcValidity);
		return this;
	}
	/** 内容有效至        **/
	public CsRechargeCard csrcValidityUtil(Date csrcValidityUtil){
		this.csrcValidityUtil = csrcValidityUtil;
		this.setSeted(F.csrcValidityUtil);
		return this;
	}
	/** 充值有效至     指明该充值卡的有效时间   **/
	public CsRechargeCard csrcUtil(Date csrcUtil){
		this.csrcUtil = csrcUtil;
		this.setSeted(F.csrcUtil);
		return this;
	}
	/** 使用会员  [CsMember]      **/
	public CsRechargeCard csrcMember(Long csrcMember){
		this.csrcMember = csrcMember;
		this.setSeted(F.csrcMember);
		return this;
	}
	/** 使用时间        **/
	public CsRechargeCard csrcUseTime(Date csrcUseTime){
		this.csrcUseTime = csrcUseTime;
		this.setSeted(F.csrcUseTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsRechargeCard csrcAddTime(Date csrcAddTime){
		this.csrcAddTime = csrcAddTime;
		this.setSeted(F.csrcAddTime);
		return this;
	}
	/** 状态 [非空]   1:库存 2:出库(发出) 3:已使用 0:无效卡     **/
	public CsRechargeCard csrcStatus(Short csrcStatus){
		this.csrcStatus = csrcStatus;
		this.setSeted(F.csrcStatus);
		return this;
	}
	
	
	//克隆对象
	public CsRechargeCard clone(){
		CsRechargeCard clone = new CsRechargeCard();
		clone.csrcHost=this.csrcHost;
		clone.csrcNumber=this.csrcNumber;
		clone.csrcPassword=this.csrcPassword;
		clone.csrcType=this.csrcType;
		clone.csrcDenomination=this.csrcDenomination;
		clone.csrcMethod=this.csrcMethod;
		clone.csrcValidity=this.csrcValidity;
		clone.csrcAddTime=this.csrcAddTime;
		clone.csrcStatus=this.csrcStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取充值卡
	 * @param id
	 * @return
	 */
	public static @api CsRechargeCard get(Long id){		
		return getCsRechargeCardById(id);
	}
	/**
	 * 获取所有充值卡
	 * @return
	 */
	public static @api List<CsRechargeCard> list(Map params,Integer size){
		return getCsRechargeCardList(params,size);
	}
	/**
	 * 获取充值卡分页
	 * @return
	 */
	public static @api Page<CsRechargeCard> page(int page,int size,Map params){
		return getCsRechargeCardPage(page, size , params);
	}
	/**
	 * 根据查询条件取充值卡
	 * @param params
	 * @return
	 */
	public static @api CsRechargeCard Get(Map params){
		return getCsRechargeCard(params);
	}
	/**
	 * 获取充值卡数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsRechargeCardCount(params);
	}
	/**
	 * 获取充值卡数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsRechargeCardEval(eval,params);
	}
	
	/**
	 * 根据ID取充值卡
	 * @param id
	 * @return
	 */
	public static @api CsRechargeCard getCsRechargeCardById(Long id){		
		CsRechargeCard csRechargeCard = (CsRechargeCard) $.GetRequest("CsRechargeCard$"+id);
		if(csRechargeCard!=null)
			return csRechargeCard;
		com.ccclubs.service.admin.ICsRechargeCardService csRechargeCardService = $.GetSpringBean("csRechargeCardService");		
		return csRechargeCardService.getCsRechargeCardById(id);
	}
	
	
	/**
	 * 根据ID取充值卡的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsRechargeCard.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsRechargeCard csRechargeCard = get(id);
		if(csRechargeCard!=null){
			String strValue = csRechargeCard.getCsrcNumber$();
			if(!"CsrcNumber".equals("CsrcNumber"))
				strValue+="("+csRechargeCard.getCsrcNumber$()+")";
			MemCache.setValue(CsRechargeCard.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsrcNumber$();
		if(!"CsrcNumber".equals("CsrcNumber"))
			keyValue+="("+this.getCsrcNumber$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有充值卡
	 * @return
	 */
	public static @api List<CsRechargeCard> getCsRechargeCardList(Map params,Integer size){
		com.ccclubs.service.admin.ICsRechargeCardService csRechargeCardService = $.GetSpringBean("csRechargeCardService");
		return csRechargeCardService.getCsRechargeCardList(params, size);
	}
	
	/**
	 * 获取充值卡分页
	 * @return
	 */
	public static @api Page<CsRechargeCard> getCsRechargeCardPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsRechargeCardService csRechargeCardService = $.GetSpringBean("csRechargeCardService");
		return csRechargeCardService.getCsRechargeCardPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取充值卡
	 * @param params
	 * @return
	 */
	public static @api CsRechargeCard getCsRechargeCard(Map params){
		com.ccclubs.service.admin.ICsRechargeCardService csRechargeCardService = $.GetSpringBean("csRechargeCardService");
		return csRechargeCardService.getCsRechargeCard(params);
	}
	
	/**
	 * 获取充值卡数
	 * @return
	 */
	public static @api Long getCsRechargeCardCount(Map params){
		com.ccclubs.service.admin.ICsRechargeCardService csRechargeCardService = $.GetSpringBean("csRechargeCardService");
		return csRechargeCardService.getCsRechargeCardCount(params);
	}
		
		
	/**
	 * 获取充值卡自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsRechargeCardEval(String eval,Map params){
		com.ccclubs.service.admin.ICsRechargeCardService csRechargeCardService = $.GetSpringBean("csRechargeCardService");
		return csRechargeCardService.getCsRechargeCardEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsRechargeCard(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsRechargeCardService csRechargeCardService = $.GetSpringBean("csRechargeCardService");
		csRechargeCardService.updateCsRechargeCardByConfirm(set, where);
	}
	
	
	/**
	 * 保存充值卡对象
	 * @param params
	 * @return
	 */
	public CsRechargeCard save(){
		com.ccclubs.service.admin.ICsRechargeCardService csRechargeCardService = $.GetSpringBean("csRechargeCardService");
		if(this.getCsrcId()!=null)
			csRechargeCardService.updateCsRechargeCard(this);
		else
			return csRechargeCardService.saveCsRechargeCard(this);
		return this;
	}
	
	
	/**
	 * 更新充值卡对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsRechargeCardService csRechargeCardService = $.GetSpringBean("csRechargeCardService");
		csRechargeCardService.updateCsRechargeCard$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsRechargeCardService csRechargeCardService = $.GetSpringBean("csRechargeCardService");
		if($.equals($.config("logic_delete"),"true"))
			csRechargeCardService.removeCsRechargeCardById(this.getCsrcId());
		else
			csRechargeCardService.deleteCsRechargeCardById(this.getCsrcId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsRechargeCardService csRechargeCardService = $.GetSpringBean("csRechargeCardService");
		return csRechargeCardService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsrcId(){
		return this.csrcId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsrcId$(){
		String strValue="";
		 strValue=$.str(this.getCsrcId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsrcId(Long csrcId){
		this.csrcId = csrcId;
		this.setSeted(F.csrcId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsrcHost(){
		return this.csrcHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsrcHost$(){
		String strValue="";
		if(this.getCsrcHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsrcHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsrcHost(Long csrcHost){
		this.csrcHost = csrcHost;
		this.setSeted(F.csrcHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csrcHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsrcHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsrcHost()!=null){
 			srvHost = SrvHost.get(this.getCsrcHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsrcHost(), srvHost);
	 	return srvHost;
	}
	/*******************************卡号**********************************/	
	/**
	* 卡号 [非空]      
	**/
	public String getCsrcNumber(){
		return this.csrcNumber;
	}
	/**
	* 获取卡号格式化(toString)
	**/
	public String getCsrcNumber$(){
		String strValue="";
		 strValue=$.str(this.getCsrcNumber());
	 	 return strValue;
	}
	/**
	* 卡号 [非空]      
	**/
	public void setCsrcNumber(String csrcNumber){
		this.csrcNumber = csrcNumber;
		this.setSeted(F.csrcNumber);
	}
	/*******************************密码**********************************/	
	/**
	* 密码 [非空]      
	**/
	public String getCsrcPassword(){
		return this.csrcPassword;
	}
	/**
	* 获取密码格式化(toString)
	**/
	public String getCsrcPassword$(){
		String strValue="";
		 strValue=$.str(this.getCsrcPassword());
	 	 return strValue;
	}
	/**
	* 密码 [非空]      
	**/
	public void setCsrcPassword(String csrcPassword){
		this.csrcPassword = csrcPassword;
		this.setSeted(F.csrcPassword);
	}
	/*******************************类型**********************************/	
	/**
	* 类型 [非空]   0:现金券 1:免费小时 2:红包    
	**/
	public Short getCsrcType(){
		return this.csrcType;
	}
	/**
	* 获取类型格式化(toString)
	**/
	public String getCsrcType$(){
		String strValue="";
		 if($.equals($.str(this.getCsrcType()),"0"))
			strValue=$.str("现金券");		 
		 if($.equals($.str(this.getCsrcType()),"1"))
			strValue=$.str("免费小时");		 
		 if($.equals($.str(this.getCsrcType()),"2"))
			strValue=$.str("红包");		 
	 	 return strValue;
	}
	/**
	* 类型 [非空]   0:现金券 1:免费小时 2:红包    
	**/
	public void setCsrcType(Short csrcType){
		this.csrcType = csrcType;
		this.setSeted(F.csrcType);
	}
	/*******************************面额**********************************/	
	/**
	* 面额 [非空]      
	**/
	public Double getCsrcDenomination(){
		return this.csrcDenomination;
	}
	/**
	* 获取面额格式化(toString)
	**/
	public String getCsrcDenomination$(){
		String strValue="";
		 strValue=$.str(this.getCsrcDenomination());	
	 	 return strValue;
	}
	/**
	* 面额 [非空]      
	**/
	public void setCsrcDenomination(Double csrcDenomination){
		this.csrcDenomination = csrcDenomination;
		this.setSeted(F.csrcDenomination);
	}
	/*******************************分类**********************************/	
	/**
	* 分类 [非空] [SrvProperty]  rcgcard_method:充值卡分类    
	**/
	public Long getCsrcMethod(){
		return this.csrcMethod;
	}
	/**
	* 获取分类格式化(toString)
	**/
	public String getCsrcMethod$(){
		String strValue="";
		if(this.getCsrcMethod()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCsrcMethod()));
		}			
	 	 return strValue;
	}
	/**
	* 分类 [非空] [SrvProperty]  rcgcard_method:充值卡分类    
	**/
	public void setCsrcMethod(Long csrcMethod){
		this.csrcMethod = csrcMethod;
		this.setSeted(F.csrcMethod);
	}
	/**
	* 获取关联对象[系统属性]
	**/	 			
 	public SrvProperty get$csrcMethod(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCsrcMethod());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCsrcMethod()!=null){
 			srvProperty = SrvProperty.get(this.getCsrcMethod());
 		}
 		$.SetRequest("SrvProperty$"+this.getCsrcMethod(), srvProperty);
	 	return srvProperty;
	}
	/*******************************内容有效期**********************************/	
	/**
	* 内容有效期 [非空]   1:1个月 2:2个月 3:3个月 6:半年 12:1年 0:自定义  指明该充值卡对应的内容的有效期时长  
	**/
	public Short getCsrcValidity(){
		return this.csrcValidity;
	}
	/**
	* 获取内容有效期格式化(toString)
	**/
	public String getCsrcValidity$(){
		String strValue="";
		 if($.equals($.str(this.getCsrcValidity()),"1"))
			strValue=$.str("1个月");		 
		 if($.equals($.str(this.getCsrcValidity()),"2"))
			strValue=$.str("2个月");		 
		 if($.equals($.str(this.getCsrcValidity()),"3"))
			strValue=$.str("3个月");		 
		 if($.equals($.str(this.getCsrcValidity()),"6"))
			strValue=$.str("半年");		 
		 if($.equals($.str(this.getCsrcValidity()),"12"))
			strValue=$.str("1年");		 
		 if($.equals($.str(this.getCsrcValidity()),"0"))
			strValue=$.str("自定义");		 
	 	 return strValue;
	}
	/**
	* 内容有效期 [非空]   1:1个月 2:2个月 3:3个月 6:半年 12:1年 0:自定义  指明该充值卡对应的内容的有效期时长  
	**/
	public void setCsrcValidity(Short csrcValidity){
		this.csrcValidity = csrcValidity;
		this.setSeted(F.csrcValidity);
	}
	/*******************************内容有效至**********************************/	
	/**
	* 内容有效至       
	**/
	public Date getCsrcValidityUtil(){
		return this.csrcValidityUtil;
	}
	/**
	* 获取内容有效至格式化(toString)
	**/
	public String getCsrcValidityUtil$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsrcValidityUtil(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 内容有效至       
	**/
	public void setCsrcValidityUtil(Date csrcValidityUtil){
		this.csrcValidityUtil = csrcValidityUtil;
		this.setSeted(F.csrcValidityUtil);
	}
	/*******************************充值有效至**********************************/	
	/**
	* 充值有效至     指明该充值卡的有效时间  
	**/
	public Date getCsrcUtil(){
		return this.csrcUtil;
	}
	/**
	* 获取充值有效至格式化(toString)
	**/
	public String getCsrcUtil$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsrcUtil(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 充值有效至     指明该充值卡的有效时间  
	**/
	public void setCsrcUtil(Date csrcUtil){
		this.csrcUtil = csrcUtil;
		this.setSeted(F.csrcUtil);
	}
	/*******************************使用会员**********************************/	
	/**
	* 使用会员  [CsMember]     
	**/
	public Long getCsrcMember(){
		return this.csrcMember;
	}
	/**
	* 获取使用会员格式化(toString)
	**/
	public String getCsrcMember$(){
		String strValue="";
		if(this.getCsrcMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsrcMember()));
		}			
	 	 return strValue;
	}
	/**
	* 使用会员  [CsMember]     
	**/
	public void setCsrcMember(Long csrcMember){
		this.csrcMember = csrcMember;
		this.setSeted(F.csrcMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csrcMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsrcMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsrcMember()!=null){
 			csMember = CsMember.get(this.getCsrcMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsrcMember(), csMember);
	 	return csMember;
	}
	/*******************************使用时间**********************************/	
	/**
	* 使用时间       
	**/
	public Date getCsrcUseTime(){
		return this.csrcUseTime;
	}
	/**
	* 获取使用时间格式化(toString)
	**/
	public String getCsrcUseTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsrcUseTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 使用时间       
	**/
	public void setCsrcUseTime(Date csrcUseTime){
		this.csrcUseTime = csrcUseTime;
		this.setSeted(F.csrcUseTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsrcAddTime(){
		return this.csrcAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsrcAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsrcAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsrcAddTime(Date csrcAddTime){
		this.csrcAddTime = csrcAddTime;
		this.setSeted(F.csrcAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:库存 2:出库(发出) 3:已使用 0:无效卡    
	**/
	public Short getCsrcStatus(){
		return this.csrcStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsrcStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsrcStatus()),"1"))
			strValue=$.str("库存");		 
		 if($.equals($.str(this.getCsrcStatus()),"2"))
			strValue=$.str("出库(发出)");		 
		 if($.equals($.str(this.getCsrcStatus()),"3"))
			strValue=$.str("已使用");		 
		 if($.equals($.str(this.getCsrcStatus()),"0"))
			strValue=$.str("无效卡");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:库存 2:出库(发出) 3:已使用 0:无效卡    
	**/
	public void setCsrcStatus(Short csrcStatus){
		this.csrcStatus = csrcStatus;
		this.setSeted(F.csrcStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsRechargeCard.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRechargeCard.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRechargeCard.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsRechargeCard.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsRechargeCard.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRechargeCard.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRechargeCard.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsRechargeCard.this);
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
		public M csrcId(Object csrcId){this.put("csrcId", csrcId);return this;};
	 	/** and csrc_id is null */
 		public M csrcIdNull(){if(this.get("csrcIdNot")==null)this.put("csrcIdNot", "");this.put("csrcId", null);return this;};
 		/** not .... */
 		public M csrcIdNot(){this.put("csrcIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csrcHost(Object csrcHost){this.put("csrcHost", csrcHost);return this;};
	 	/** and csrc_host is null */
 		public M csrcHostNull(){if(this.get("csrcHostNot")==null)this.put("csrcHostNot", "");this.put("csrcHost", null);return this;};
 		/** not .... */
 		public M csrcHostNot(){this.put("csrcHostNot", "not");return this;};
		/** 卡号 [非空]       **/
		public M csrcNumber(Object csrcNumber){this.put("csrcNumber", csrcNumber);return this;};
	 	/** and csrc_number is null */
 		public M csrcNumberNull(){if(this.get("csrcNumberNot")==null)this.put("csrcNumberNot", "");this.put("csrcNumber", null);return this;};
 		/** not .... */
 		public M csrcNumberNot(){this.put("csrcNumberNot", "not");return this;};
		/** 密码 [非空]       **/
		public M csrcPassword(Object csrcPassword){this.put("csrcPassword", csrcPassword);return this;};
	 	/** and csrc_password is null */
 		public M csrcPasswordNull(){if(this.get("csrcPasswordNot")==null)this.put("csrcPasswordNot", "");this.put("csrcPassword", null);return this;};
 		/** not .... */
 		public M csrcPasswordNot(){this.put("csrcPasswordNot", "not");return this;};
		/** 类型 [非空]   0:现金券 1:免费小时 2:红包     **/
		public M csrcType(Object csrcType){this.put("csrcType", csrcType);return this;};
	 	/** and csrc_type is null */
 		public M csrcTypeNull(){if(this.get("csrcTypeNot")==null)this.put("csrcTypeNot", "");this.put("csrcType", null);return this;};
 		/** not .... */
 		public M csrcTypeNot(){this.put("csrcTypeNot", "not");return this;};
		/** 面额 [非空]       **/
		public M csrcDenomination(Object csrcDenomination){this.put("csrcDenomination", csrcDenomination);return this;};
	 	/** and csrc_denomination is null */
 		public M csrcDenominationNull(){if(this.get("csrcDenominationNot")==null)this.put("csrcDenominationNot", "");this.put("csrcDenomination", null);return this;};
 		/** not .... */
 		public M csrcDenominationNot(){this.put("csrcDenominationNot", "not");return this;};
		/** and csrc_denomination >= ? */
		public M csrcDenominationMin(Object min){this.put("csrcDenominationMin", min);return this;};
		/** and csrc_denomination <= ? */
		public M csrcDenominationMax(Object max){this.put("csrcDenominationMax", max);return this;};
		/** 分类 [非空] [SrvProperty]  rcgcard_method:充值卡分类     **/
		public M csrcMethod(Object csrcMethod){this.put("csrcMethod", csrcMethod);return this;};
	 	/** and csrc_method is null */
 		public M csrcMethodNull(){if(this.get("csrcMethodNot")==null)this.put("csrcMethodNot", "");this.put("csrcMethod", null);return this;};
 		/** not .... */
 		public M csrcMethodNot(){this.put("csrcMethodNot", "not");return this;};
		public M csrcMethod$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("csrcMethod$on", value);
			return this;
		};	
		/** 内容有效期 [非空]   1:1个月 2:2个月 3:3个月 6:半年 12:1年 0:自定义  指明该充值卡对应的内容的有效期时长   **/
		public M csrcValidity(Object csrcValidity){this.put("csrcValidity", csrcValidity);return this;};
	 	/** and csrc_validity is null */
 		public M csrcValidityNull(){if(this.get("csrcValidityNot")==null)this.put("csrcValidityNot", "");this.put("csrcValidity", null);return this;};
 		/** not .... */
 		public M csrcValidityNot(){this.put("csrcValidityNot", "not");return this;};
		/** 内容有效至        **/
		public M csrcValidityUtil(Object csrcValidityUtil){this.put("csrcValidityUtil", csrcValidityUtil);return this;};
	 	/** and csrc_validity_util is null */
 		public M csrcValidityUtilNull(){if(this.get("csrcValidityUtilNot")==null)this.put("csrcValidityUtilNot", "");this.put("csrcValidityUtil", null);return this;};
 		/** not .... */
 		public M csrcValidityUtilNot(){this.put("csrcValidityUtilNot", "not");return this;};
 		/** and csrc_validity_util >= ? */
 		public M csrcValidityUtilStart(Object start){this.put("csrcValidityUtilStart", start);return this;};			
 		/** and csrc_validity_util <= ? */
 		public M csrcValidityUtilEnd(Object end){this.put("csrcValidityUtilEnd", end);return this;};
		/** 充值有效至     指明该充值卡的有效时间   **/
		public M csrcUtil(Object csrcUtil){this.put("csrcUtil", csrcUtil);return this;};
	 	/** and csrc_util is null */
 		public M csrcUtilNull(){if(this.get("csrcUtilNot")==null)this.put("csrcUtilNot", "");this.put("csrcUtil", null);return this;};
 		/** not .... */
 		public M csrcUtilNot(){this.put("csrcUtilNot", "not");return this;};
 		/** and csrc_util >= ? */
 		public M csrcUtilStart(Object start){this.put("csrcUtilStart", start);return this;};			
 		/** and csrc_util <= ? */
 		public M csrcUtilEnd(Object end){this.put("csrcUtilEnd", end);return this;};
		/** 使用会员  [CsMember]      **/
		public M csrcMember(Object csrcMember){this.put("csrcMember", csrcMember);return this;};
	 	/** and csrc_member is null */
 		public M csrcMemberNull(){if(this.get("csrcMemberNot")==null)this.put("csrcMemberNot", "");this.put("csrcMember", null);return this;};
 		/** not .... */
 		public M csrcMemberNot(){this.put("csrcMemberNot", "not");return this;};
		public M csrcMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csrcMember$on", value);
			return this;
		};	
		/** 使用时间        **/
		public M csrcUseTime(Object csrcUseTime){this.put("csrcUseTime", csrcUseTime);return this;};
	 	/** and csrc_use_time is null */
 		public M csrcUseTimeNull(){if(this.get("csrcUseTimeNot")==null)this.put("csrcUseTimeNot", "");this.put("csrcUseTime", null);return this;};
 		/** not .... */
 		public M csrcUseTimeNot(){this.put("csrcUseTimeNot", "not");return this;};
 		/** and csrc_use_time >= ? */
 		public M csrcUseTimeStart(Object start){this.put("csrcUseTimeStart", start);return this;};			
 		/** and csrc_use_time <= ? */
 		public M csrcUseTimeEnd(Object end){this.put("csrcUseTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csrcAddTime(Object csrcAddTime){this.put("csrcAddTime", csrcAddTime);return this;};
	 	/** and csrc_add_time is null */
 		public M csrcAddTimeNull(){if(this.get("csrcAddTimeNot")==null)this.put("csrcAddTimeNot", "");this.put("csrcAddTime", null);return this;};
 		/** not .... */
 		public M csrcAddTimeNot(){this.put("csrcAddTimeNot", "not");return this;};
 		/** and csrc_add_time >= ? */
 		public M csrcAddTimeStart(Object start){this.put("csrcAddTimeStart", start);return this;};			
 		/** and csrc_add_time <= ? */
 		public M csrcAddTimeEnd(Object end){this.put("csrcAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:库存 2:出库(发出) 3:已使用 0:无效卡     **/
		public M csrcStatus(Object csrcStatus){this.put("csrcStatus", csrcStatus);return this;};
	 	/** and csrc_status is null */
 		public M csrcStatusNull(){if(this.get("csrcStatusNot")==null)this.put("csrcStatusNot", "");this.put("csrcStatus", null);return this;};
 		/** not .... */
 		public M csrcStatusNot(){this.put("csrcStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有充值卡 **/
		public @api List<CsRechargeCard> list(Integer size){
			return getCsRechargeCardList(this,size);
		}
		/** 获取充值卡分页 **/
		public @api Page<CsRechargeCard> page(int page,int size){
			return getCsRechargeCardPage(page, size , this);
		}
		/** 根据查询条件取充值卡 **/
		public @api CsRechargeCard get(){
			return getCsRechargeCard(this);
		}
		/** 获取充值卡数 **/
		public @api Long count(){
			return getCsRechargeCardCount(this);
		}
		/** 获取充值卡表达式 **/
		public @api <T> T eval(String strEval){
			return getCsRechargeCardEval(strEval,this);
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
			updateCsRechargeCard(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csrcId="csrcId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csrcHost="csrcHost";
		/** 卡号 [非空]       **/
		public final static @type(String.class) @like String csrcNumber="csrcNumber";
		/** 密码 [非空]       **/
		public final static @type(String.class) @like String csrcPassword="csrcPassword";
		/** 类型 [非空]   0:现金券 1:免费小时 2:红包     **/
		public final static @type(Short.class)  String csrcType="csrcType";
		/** 面额 [非空]       **/
		public final static @type(Double.class)  String csrcDenomination="csrcDenomination";
		/** and csrc_denomination >= ? */
		public final static @type(Double.class) String csrcDenominationMin="csrcDenominationMin";
		/** and csrc_denomination <= ? */
		public final static @type(Double.class) String csrcDenominationMax="csrcDenominationMax";
		/** 分类 [非空] [SrvProperty]  rcgcard_method:充值卡分类     **/
		public final static @type(Long.class)  String csrcMethod="csrcMethod";
		/** 内容有效期 [非空]   1:1个月 2:2个月 3:3个月 6:半年 12:1年 0:自定义  指明该充值卡对应的内容的有效期时长   **/
		public final static @type(Short.class)  String csrcValidity="csrcValidity";
		/** 内容有效至        **/
		public final static @type(Date.class)  String csrcValidityUtil="csrcValidityUtil";
	 	/** and csrc_validity_util >= ? */
 		public final static @type(Date.class) String csrcValidityUtilStart="csrcValidityUtilStart";
 		/** and csrc_validity_util <= ? */
 		public final static @type(Date.class) String csrcValidityUtilEnd="csrcValidityUtilEnd";
		/** 充值有效至     指明该充值卡的有效时间   **/
		public final static @type(Date.class)  String csrcUtil="csrcUtil";
	 	/** and csrc_util >= ? */
 		public final static @type(Date.class) String csrcUtilStart="csrcUtilStart";
 		/** and csrc_util <= ? */
 		public final static @type(Date.class) String csrcUtilEnd="csrcUtilEnd";
		/** 使用会员  [CsMember]      **/
		public final static @type(Long.class)  String csrcMember="csrcMember";
		/** 使用时间        **/
		public final static @type(Date.class)  String csrcUseTime="csrcUseTime";
	 	/** and csrc_use_time >= ? */
 		public final static @type(Date.class) String csrcUseTimeStart="csrcUseTimeStart";
 		/** and csrc_use_time <= ? */
 		public final static @type(Date.class) String csrcUseTimeEnd="csrcUseTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csrcAddTime="csrcAddTime";
	 	/** and csrc_add_time >= ? */
 		public final static @type(Date.class) String csrcAddTimeStart="csrcAddTimeStart";
 		/** and csrc_add_time <= ? */
 		public final static @type(Date.class) String csrcAddTimeEnd="csrcAddTimeEnd";
		/** 状态 [非空]   1:库存 2:出库(发出) 3:已使用 0:无效卡     **/
		public final static @type(Short.class)  String csrcStatus="csrcStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csrcId="csrc_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csrcHost="csrc_host";
		/** 卡号 [非空]       **/
		public final static String csrcNumber="csrc_number";
		/** 密码 [非空]       **/
		public final static String csrcPassword="csrc_password";
		/** 类型 [非空]   0:现金券 1:免费小时 2:红包     **/
		public final static String csrcType="csrc_type";
		/** 面额 [非空]       **/
		public final static String csrcDenomination="csrc_denomination";
		/** 分类 [非空] [SrvProperty]  rcgcard_method:充值卡分类     **/
		public final static String csrcMethod="csrc_method";
		/** 内容有效期 [非空]   1:1个月 2:2个月 3:3个月 6:半年 12:1年 0:自定义  指明该充值卡对应的内容的有效期时长   **/
		public final static String csrcValidity="csrc_validity";
		/** 内容有效至        **/
		public final static String csrcValidityUtil="csrc_validity_util";
		/** 充值有效至     指明该充值卡的有效时间   **/
		public final static String csrcUtil="csrc_util";
		/** 使用会员  [CsMember]      **/
		public final static String csrcMember="csrc_member";
		/** 使用时间        **/
		public final static String csrcUseTime="csrc_use_time";
		/** 添加时间 [非空]       **/
		public final static String csrcAddTime="csrc_add_time";
		/** 状态 [非空]   1:库存 2:出库(发出) 3:已使用 0:无效卡     **/
		public final static String csrcStatus="csrc_status";
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
				$.Set(name,CsRechargeCard.getCsRechargeCard(params));
			else
				$.Set(name,CsRechargeCard.getCsRechargeCardList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取充值卡数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsRechargeCard) $.GetRequest("CsRechargeCard$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsRechargeCard.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsRechargeCard.getCsRechargeCard(params);
			else
				value = CsRechargeCard.getCsRechargeCardList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsRechargeCard.Get($.add(CsRechargeCard.F.csrcId,param));
		else if(!$.empty(param.toString()))
			value = CsRechargeCard.get(Long.valueOf(param.toString()));
		$.SetRequest("CsRechargeCard$"+param.hashCode(), value);
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
	public void mergeSet(CsRechargeCard old){
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