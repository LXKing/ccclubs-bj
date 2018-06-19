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

@namespace("sale/event")
public @caption("销售活动") @table("cs_sale_event") class CsSaleEvent implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csse_id")   @primary  @note("  ") Long csseId;// 主键 非空     
	private @caption("城市") @column("csse_host")    @relate("$csseHost") @RelateClass(SrvHost.class)  @note("  ") Long csseHost;// 非空     
 	private SrvHost $csseHost;//关联对象[运营城市]
	private @caption("活动主题") @column("csse_subject")    @note("  ") String csseSubject;// 非空     
	private @caption("归属战役") @column("csse_battle")    @relate("$csseBattle") @RelateClass(TbSaleBattle.class)  @note("  ") Long csseBattle;// 非空     
 	private TbSaleBattle $csseBattle;//关联对象[销售战役]
	private @caption("活动类型") @column("csse_type")    @relate("$csseType") @RelateClass(SrvProperty.class)  @note(" sale_event_type:销售活动类型  比如：促销活动 展架直销 会议培训 展会 研讨会 网络推广") Long csseType;// 非空 sale_event_type:销售活动类型  比如：促销活动 展架直销 会议培训 展会 研讨会 网络推广   
 	private SrvProperty $csseType;//关联对象[系统属性]
	private @caption("负责人") @column("csse_person")    @relate("$cssePerson") @RelateClass(SrvUser.class)  @note("  ") Long cssePerson;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cssePerson;//关联对象[用户]
	private @caption("位置") @column("csse_postion")  @hidden   @note("  ") String cssePostion;// 非空     
	private @caption("详细地点") @column("csse_address")    @note("  ") String csseAddress;//     
	private @caption("活动说明") @column("csse_desc")  @hidden   @note("  ") String csseDesc;// 非空     
	private @caption("开始时间") @column("csse_start")    @note("  ") Date csseStart;// 非空     
	private @caption("结束时间") @column("csse_finish")    @note("  ") Date csseFinish;// 非空     
	private @caption("修改时间") @column("csse_update_time")    @note("  ") Date csseUpdateTime;// 非空     
	private @caption("添加时间") @column("csse_add_time")    @note("  ") Date csseAddTime;// 非空     
	private @caption("备注信息") @column("csse_remark")    @note("  ") String csseRemark;//     
	private @caption("状态") @column("csse_status")    @note(" 0:筹备中 1:进行中 2:已完成 3:已取消  ") Short csseStatus;// 非空 0:筹备中 1:进行中 2:已完成 3:已取消     
	
	//默认构造函数
	public CsSaleEvent(){
	
	}
	
	//主键构造函数
	public CsSaleEvent(Long id){
		this.csseId = id;
	}
	
	/**所有字段构造函数 CsSaleEvent(csseHost,csseSubject,csseBattle,csseType,cssePerson,cssePostion,csseAddress,csseDesc,csseStart,csseFinish,csseUpdateTime,csseAddTime,csseRemark,csseStatus)
	 CsSaleEvent(
	 	$.getLong("csseHost")//城市 [非空]
	 	,$.getString("csseSubject")//活动主题 [非空]
	 	,$.getLong("csseBattle")//归属战役 [非空]
	 	,$.getLong("csseType")//活动类型 [非空]
	 	,$.getLong("cssePerson")//负责人 [非空]
	 	,$.getString("cssePostion")//位置 [非空]
	 	,$.getString("csseAddress")//详细地点
	 	,$.getString("csseDesc")//活动说明 [非空]
	 	,$.getDate("csseStart")//开始时间 [非空]
	 	,$.getDate("csseFinish")//结束时间 [非空]
	 	,$.getDate("csseUpdateTime")//修改时间 [非空]
	 	,$.getDate("csseAddTime")//添加时间 [非空]
	 	,$.getString("csseRemark")//备注信息
	 	,$.getShort("csseStatus")//状态 [非空]
	 )
	**/
	public CsSaleEvent(Long csseHost,String csseSubject,Long csseBattle,Long csseType,Long cssePerson,String cssePostion,String csseAddress,String csseDesc,Date csseStart,Date csseFinish,Date csseUpdateTime,Date csseAddTime,String csseRemark,Short csseStatus){
		this.csseHost=csseHost;
		this.csseSubject=csseSubject;
		this.csseBattle=csseBattle;
		this.csseType=csseType;
		this.cssePerson=cssePerson;
		this.cssePostion=cssePostion;
		this.csseAddress=csseAddress;
		this.csseDesc=csseDesc;
		this.csseStart=csseStart;
		this.csseFinish=csseFinish;
		this.csseUpdateTime=csseUpdateTime;
		this.csseAddTime=csseAddTime;
		this.csseRemark=csseRemark;
		this.csseStatus=csseStatus;
	}
	
	//设置非空字段
	public CsSaleEvent setNotNull(Long csseHost,String csseSubject,Long csseBattle,Long csseType,Long cssePerson,String cssePostion,String csseDesc,Date csseStart,Date csseFinish,Date csseUpdateTime,Date csseAddTime,Short csseStatus){
		this.csseHost=csseHost;
		this.csseSubject=csseSubject;
		this.csseBattle=csseBattle;
		this.csseType=csseType;
		this.cssePerson=cssePerson;
		this.cssePostion=cssePostion;
		this.csseDesc=csseDesc;
		this.csseStart=csseStart;
		this.csseFinish=csseFinish;
		this.csseUpdateTime=csseUpdateTime;
		this.csseAddTime=csseAddTime;
		this.csseStatus=csseStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsSaleEvent csseId(Long csseId){
		this.csseId = csseId;
		this.setSeted(F.csseId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsSaleEvent csseHost(Long csseHost){
		this.csseHost = csseHost;
		this.setSeted(F.csseHost);
		return this;
	}
	/** 活动主题 [非空]       **/
	public CsSaleEvent csseSubject(String csseSubject){
		this.csseSubject = csseSubject;
		this.setSeted(F.csseSubject);
		return this;
	}
	/** 归属战役 [非空] [TbSaleBattle]      **/
	public CsSaleEvent csseBattle(Long csseBattle){
		this.csseBattle = csseBattle;
		this.setSeted(F.csseBattle);
		return this;
	}
	/** 活动类型 [非空] [SrvProperty]  sale_event_type:销售活动类型  比如：促销活动 展架直销 会议培训 展会 研讨会 网络推广   **/
	public CsSaleEvent csseType(Long csseType){
		this.csseType = csseType;
		this.setSeted(F.csseType);
		return this;
	}
	/** 负责人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsSaleEvent cssePerson(Long cssePerson){
		this.cssePerson = cssePerson;
		this.setSeted(F.cssePerson);
		return this;
	}
	/** 位置 [非空]       **/
	public CsSaleEvent cssePostion(String cssePostion){
		this.cssePostion = cssePostion;
		this.setSeted(F.cssePostion);
		return this;
	}
	/** 详细地点        **/
	public CsSaleEvent csseAddress(String csseAddress){
		this.csseAddress = csseAddress;
		this.setSeted(F.csseAddress);
		return this;
	}
	/** 活动说明 [非空]       **/
	public CsSaleEvent csseDesc(String csseDesc){
		this.csseDesc = csseDesc;
		this.setSeted(F.csseDesc);
		return this;
	}
	/** 开始时间 [非空]       **/
	public CsSaleEvent csseStart(Date csseStart){
		this.csseStart = csseStart;
		this.setSeted(F.csseStart);
		return this;
	}
	/** 结束时间 [非空]       **/
	public CsSaleEvent csseFinish(Date csseFinish){
		this.csseFinish = csseFinish;
		this.setSeted(F.csseFinish);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsSaleEvent csseUpdateTime(Date csseUpdateTime){
		this.csseUpdateTime = csseUpdateTime;
		this.setSeted(F.csseUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsSaleEvent csseAddTime(Date csseAddTime){
		this.csseAddTime = csseAddTime;
		this.setSeted(F.csseAddTime);
		return this;
	}
	/** 备注信息        **/
	public CsSaleEvent csseRemark(String csseRemark){
		this.csseRemark = csseRemark;
		this.setSeted(F.csseRemark);
		return this;
	}
	/** 状态 [非空]   0:筹备中 1:进行中 2:已完成 3:已取消     **/
	public CsSaleEvent csseStatus(Short csseStatus){
		this.csseStatus = csseStatus;
		this.setSeted(F.csseStatus);
		return this;
	}
	
	
	//克隆对象
	public CsSaleEvent clone(){
		CsSaleEvent clone = new CsSaleEvent();
		clone.csseHost=this.csseHost;
		clone.csseSubject=this.csseSubject;
		clone.csseBattle=this.csseBattle;
		clone.csseType=this.csseType;
		clone.cssePerson=this.cssePerson;
		clone.cssePostion=this.cssePostion;
		clone.csseDesc=this.csseDesc;
		clone.csseStart=this.csseStart;
		clone.csseFinish=this.csseFinish;
		clone.csseUpdateTime=this.csseUpdateTime;
		clone.csseAddTime=this.csseAddTime;
		clone.csseStatus=this.csseStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取销售活动
	 * @param id
	 * @return
	 */
	public static @api CsSaleEvent get(Long id){		
		return getCsSaleEventById(id);
	}
	/**
	 * 获取所有销售活动
	 * @return
	 */
	public static @api List<CsSaleEvent> list(Map params,Integer size){
		return getCsSaleEventList(params,size);
	}
	/**
	 * 获取销售活动分页
	 * @return
	 */
	public static @api Page<CsSaleEvent> page(int page,int size,Map params){
		return getCsSaleEventPage(page, size , params);
	}
	/**
	 * 根据查询条件取销售活动
	 * @param params
	 * @return
	 */
	public static @api CsSaleEvent Get(Map params){
		return getCsSaleEvent(params);
	}
	/**
	 * 获取销售活动数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsSaleEventCount(params);
	}
	/**
	 * 获取销售活动数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsSaleEventEval(eval,params);
	}
	
	/**
	 * 根据ID取销售活动
	 * @param id
	 * @return
	 */
	public static @api CsSaleEvent getCsSaleEventById(Long id){		
		CsSaleEvent csSaleEvent = (CsSaleEvent) $.GetRequest("CsSaleEvent$"+id);
		if(csSaleEvent!=null)
			return csSaleEvent;
		com.ccclubs.service.admin.ICsSaleEventService csSaleEventService = $.GetSpringBean("csSaleEventService");		
		return csSaleEventService.getCsSaleEventById(id);
	}
	
	
	/**
	 * 根据ID取销售活动的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsSaleEvent.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsSaleEvent csSaleEvent = get(id);
		if(csSaleEvent!=null){
			String strValue = csSaleEvent.getCsseSubject$();
			if(!"CsseSubject".equals("CsseSubject"))
				strValue+="("+csSaleEvent.getCsseSubject$()+")";
			MemCache.setValue(CsSaleEvent.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsseSubject$();
		if(!"CsseSubject".equals("CsseSubject"))
			keyValue+="("+this.getCsseSubject$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有销售活动
	 * @return
	 */
	public static @api List<CsSaleEvent> getCsSaleEventList(Map params,Integer size){
		com.ccclubs.service.admin.ICsSaleEventService csSaleEventService = $.GetSpringBean("csSaleEventService");
		return csSaleEventService.getCsSaleEventList(params, size);
	}
	
	/**
	 * 获取销售活动分页
	 * @return
	 */
	public static @api Page<CsSaleEvent> getCsSaleEventPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsSaleEventService csSaleEventService = $.GetSpringBean("csSaleEventService");
		return csSaleEventService.getCsSaleEventPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取销售活动
	 * @param params
	 * @return
	 */
	public static @api CsSaleEvent getCsSaleEvent(Map params){
		com.ccclubs.service.admin.ICsSaleEventService csSaleEventService = $.GetSpringBean("csSaleEventService");
		return csSaleEventService.getCsSaleEvent(params);
	}
	
	/**
	 * 获取销售活动数
	 * @return
	 */
	public static @api Long getCsSaleEventCount(Map params){
		com.ccclubs.service.admin.ICsSaleEventService csSaleEventService = $.GetSpringBean("csSaleEventService");
		return csSaleEventService.getCsSaleEventCount(params);
	}
		
		
	/**
	 * 获取销售活动自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsSaleEventEval(String eval,Map params){
		com.ccclubs.service.admin.ICsSaleEventService csSaleEventService = $.GetSpringBean("csSaleEventService");
		return csSaleEventService.getCsSaleEventEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsSaleEvent(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsSaleEventService csSaleEventService = $.GetSpringBean("csSaleEventService");
		csSaleEventService.updateCsSaleEventByConfirm(set, where);
	}
	
	
	/**
	 * 保存销售活动对象
	 * @param params
	 * @return
	 */
	public CsSaleEvent save(){
		com.ccclubs.service.admin.ICsSaleEventService csSaleEventService = $.GetSpringBean("csSaleEventService");
		if(this.getCsseId()!=null)
			csSaleEventService.updateCsSaleEvent(this);
		else
			return csSaleEventService.saveCsSaleEvent(this);
		return this;
	}
	
	
	/**
	 * 更新销售活动对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsSaleEventService csSaleEventService = $.GetSpringBean("csSaleEventService");
		csSaleEventService.updateCsSaleEvent$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsSaleEventService csSaleEventService = $.GetSpringBean("csSaleEventService");
		if($.equals($.config("logic_delete"),"true"))
			csSaleEventService.removeCsSaleEventById(this.getCsseId());
		else
			csSaleEventService.deleteCsSaleEventById(this.getCsseId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsSaleEventService csSaleEventService = $.GetSpringBean("csSaleEventService");
		return csSaleEventService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsseId(){
		return this.csseId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsseId$(){
		String strValue="";
		 strValue=$.str(this.getCsseId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsseId(Long csseId){
		this.csseId = csseId;
		this.setSeted(F.csseId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsseHost(){
		return this.csseHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsseHost$(){
		String strValue="";
		if(this.getCsseHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsseHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsseHost(Long csseHost){
		this.csseHost = csseHost;
		this.setSeted(F.csseHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csseHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsseHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsseHost()!=null){
 			srvHost = SrvHost.get(this.getCsseHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsseHost(), srvHost);
	 	return srvHost;
	}
	/*******************************活动主题**********************************/	
	/**
	* 活动主题 [非空]      
	**/
	public String getCsseSubject(){
		return this.csseSubject;
	}
	/**
	* 获取活动主题格式化(toString)
	**/
	public String getCsseSubject$(){
		String strValue="";
		 strValue=$.str(this.getCsseSubject());
	 	 return strValue;
	}
	/**
	* 活动主题 [非空]      
	**/
	public void setCsseSubject(String csseSubject){
		this.csseSubject = csseSubject;
		this.setSeted(F.csseSubject);
	}
	/*******************************归属战役**********************************/	
	/**
	* 归属战役 [非空] [TbSaleBattle]     
	**/
	public Long getCsseBattle(){
		return this.csseBattle;
	}
	/**
	* 获取归属战役格式化(toString)
	**/
	public String getCsseBattle$(){
		String strValue="";
		if(this.getCsseBattle()!=null){
				strValue+=$.str(TbSaleBattle.getKeyValue(this.getCsseBattle()));
		}			
	 	 return strValue;
	}
	/**
	* 归属战役 [非空] [TbSaleBattle]     
	**/
	public void setCsseBattle(Long csseBattle){
		this.csseBattle = csseBattle;
		this.setSeted(F.csseBattle);
	}
	/**
	* 获取关联对象[销售战役]
	**/	 			
 	public TbSaleBattle get$csseBattle(){
 		com.ccclubs.model.TbSaleBattle tbSaleBattle = (com.ccclubs.model.TbSaleBattle) $.GetRequest("TbSaleBattle$"+this.getCsseBattle());
 		if(tbSaleBattle!=null)
			return tbSaleBattle;
		if(this.getCsseBattle()!=null){
 			tbSaleBattle = TbSaleBattle.get(this.getCsseBattle());
 		}
 		$.SetRequest("TbSaleBattle$"+this.getCsseBattle(), tbSaleBattle);
	 	return tbSaleBattle;
	}
	/*******************************活动类型**********************************/	
	/**
	* 活动类型 [非空] [SrvProperty]  sale_event_type:销售活动类型  比如：促销活动 展架直销 会议培训 展会 研讨会 网络推广  
	**/
	public Long getCsseType(){
		return this.csseType;
	}
	/**
	* 获取活动类型格式化(toString)
	**/
	public String getCsseType$(){
		String strValue="";
		if(this.getCsseType()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCsseType()));
		}			
	 	 return strValue;
	}
	/**
	* 活动类型 [非空] [SrvProperty]  sale_event_type:销售活动类型  比如：促销活动 展架直销 会议培训 展会 研讨会 网络推广  
	**/
	public void setCsseType(Long csseType){
		this.csseType = csseType;
		this.setSeted(F.csseType);
	}
	/**
	* 获取关联对象[系统属性]
	**/	 			
 	public SrvProperty get$csseType(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCsseType());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCsseType()!=null){
 			srvProperty = SrvProperty.get(this.getCsseType());
 		}
 		$.SetRequest("SrvProperty$"+this.getCsseType(), srvProperty);
	 	return srvProperty;
	}
	/*******************************负责人**********************************/	
	/**
	* 负责人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCssePerson(){
		return this.cssePerson;
	}
	/**
	* 获取负责人格式化(toString)
	**/
	public String getCssePerson$(){
		String strValue="";
		if(this.getCssePerson()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCssePerson()));
		}			
	 	 return strValue;
	}
	/**
	* 负责人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCssePerson(Long cssePerson){
		this.cssePerson = cssePerson;
		this.setSeted(F.cssePerson);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cssePerson(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCssePerson());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCssePerson()!=null){
 			srvUser = SrvUser.get(this.getCssePerson());
 		}
 		$.SetRequest("SrvUser$"+this.getCssePerson(), srvUser);
	 	return srvUser;
	}
	/*******************************位置**********************************/	
	/**
	* 位置 [非空]      
	**/
	public String getCssePostion(){
		return this.cssePostion;
	}
	/**
	* 获取位置格式化(toString)
	**/
	public String getCssePostion$(){
		String strValue="";
		 strValue=$.str(this.getCssePostion());
	 	 return strValue;
	}
	/**
	* 位置 [非空]      
	**/
	public void setCssePostion(String cssePostion){
		this.cssePostion = cssePostion;
		this.setSeted(F.cssePostion);
	}
	/*******************************详细地点**********************************/	
	/**
	* 详细地点       
	**/
	public String getCsseAddress(){
		return this.csseAddress;
	}
	/**
	* 获取详细地点格式化(toString)
	**/
	public String getCsseAddress$(){
		String strValue="";
		 strValue=$.str(this.getCsseAddress());
	 	 return strValue;
	}
	/**
	* 详细地点       
	**/
	public void setCsseAddress(String csseAddress){
		this.csseAddress = csseAddress;
		this.setSeted(F.csseAddress);
	}
	/*******************************活动说明**********************************/	
	/**
	* 活动说明 [非空]      
	**/
	public String getCsseDesc(){
		return this.csseDesc;
	}
	/**
	* 获取活动说明格式化(toString)
	**/
	public String getCsseDesc$(){
		String strValue="";
		 strValue=$.str(this.getCsseDesc());
	 	 return strValue;
	}
	/**
	* 活动说明 [非空]      
	**/
	public void setCsseDesc(String csseDesc){
		this.csseDesc = csseDesc;
		this.setSeted(F.csseDesc);
	}
	/*******************************开始时间**********************************/	
	/**
	* 开始时间 [非空]      
	**/
	public Date getCsseStart(){
		return this.csseStart;
	}
	/**
	* 获取开始时间格式化(toString)
	**/
	public String getCsseStart$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsseStart(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 开始时间 [非空]      
	**/
	public void setCsseStart(Date csseStart){
		this.csseStart = csseStart;
		this.setSeted(F.csseStart);
	}
	/*******************************结束时间**********************************/	
	/**
	* 结束时间 [非空]      
	**/
	public Date getCsseFinish(){
		return this.csseFinish;
	}
	/**
	* 获取结束时间格式化(toString)
	**/
	public String getCsseFinish$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsseFinish(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 结束时间 [非空]      
	**/
	public void setCsseFinish(Date csseFinish){
		this.csseFinish = csseFinish;
		this.setSeted(F.csseFinish);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsseUpdateTime(){
		return this.csseUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsseUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsseUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsseUpdateTime(Date csseUpdateTime){
		this.csseUpdateTime = csseUpdateTime;
		this.setSeted(F.csseUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsseAddTime(){
		return this.csseAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsseAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsseAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsseAddTime(Date csseAddTime){
		this.csseAddTime = csseAddTime;
		this.setSeted(F.csseAddTime);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息       
	**/
	public String getCsseRemark(){
		return this.csseRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCsseRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsseRemark());
	 	 return strValue;
	}
	/**
	* 备注信息       
	**/
	public void setCsseRemark(String csseRemark){
		this.csseRemark = csseRemark;
		this.setSeted(F.csseRemark);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:筹备中 1:进行中 2:已完成 3:已取消    
	**/
	public Short getCsseStatus(){
		return this.csseStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsseStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsseStatus()),"0"))
			strValue=$.str("筹备中");		 
		 if($.equals($.str(this.getCsseStatus()),"1"))
			strValue=$.str("进行中");		 
		 if($.equals($.str(this.getCsseStatus()),"2"))
			strValue=$.str("已完成");		 
		 if($.equals($.str(this.getCsseStatus()),"3"))
			strValue=$.str("已取消");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:筹备中 1:进行中 2:已完成 3:已取消    
	**/
	public void setCsseStatus(Short csseStatus){
		this.csseStatus = csseStatus;
		this.setSeted(F.csseStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsSaleEvent.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsSaleEvent.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsSaleEvent.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsSaleEvent.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsSaleEvent.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsSaleEvent.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsSaleEvent.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsSaleEvent.this);
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
		public M csseId(Object csseId){this.put("csseId", csseId);return this;};
	 	/** and csse_id is null */
 		public M csseIdNull(){if(this.get("csseIdNot")==null)this.put("csseIdNot", "");this.put("csseId", null);return this;};
 		/** not .... */
 		public M csseIdNot(){this.put("csseIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csseHost(Object csseHost){this.put("csseHost", csseHost);return this;};
	 	/** and csse_host is null */
 		public M csseHostNull(){if(this.get("csseHostNot")==null)this.put("csseHostNot", "");this.put("csseHost", null);return this;};
 		/** not .... */
 		public M csseHostNot(){this.put("csseHostNot", "not");return this;};
		/** 活动主题 [非空]       **/
		public M csseSubject(Object csseSubject){this.put("csseSubject", csseSubject);return this;};
	 	/** and csse_subject is null */
 		public M csseSubjectNull(){if(this.get("csseSubjectNot")==null)this.put("csseSubjectNot", "");this.put("csseSubject", null);return this;};
 		/** not .... */
 		public M csseSubjectNot(){this.put("csseSubjectNot", "not");return this;};
		/** 归属战役 [非空] [TbSaleBattle]      **/
		public M csseBattle(Object csseBattle){this.put("csseBattle", csseBattle);return this;};
	 	/** and csse_battle is null */
 		public M csseBattleNull(){if(this.get("csseBattleNot")==null)this.put("csseBattleNot", "");this.put("csseBattle", null);return this;};
 		/** not .... */
 		public M csseBattleNot(){this.put("csseBattleNot", "not");return this;};
		public M csseBattle$on(TbSaleBattle.M value){
			this.put("TbSaleBattle", value);
			this.put("csseBattle$on", value);
			return this;
		};	
		/** 活动类型 [非空] [SrvProperty]  sale_event_type:销售活动类型  比如：促销活动 展架直销 会议培训 展会 研讨会 网络推广   **/
		public M csseType(Object csseType){this.put("csseType", csseType);return this;};
	 	/** and csse_type is null */
 		public M csseTypeNull(){if(this.get("csseTypeNot")==null)this.put("csseTypeNot", "");this.put("csseType", null);return this;};
 		/** not .... */
 		public M csseTypeNot(){this.put("csseTypeNot", "not");return this;};
		public M csseType$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("csseType$on", value);
			return this;
		};	
		/** 负责人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cssePerson(Object cssePerson){this.put("cssePerson", cssePerson);return this;};
	 	/** and csse_person is null */
 		public M cssePersonNull(){if(this.get("cssePersonNot")==null)this.put("cssePersonNot", "");this.put("cssePerson", null);return this;};
 		/** not .... */
 		public M cssePersonNot(){this.put("cssePersonNot", "not");return this;};
		/** 位置 [非空]       **/
		public M cssePostion(Object cssePostion){this.put("cssePostion", cssePostion);return this;};
	 	/** and csse_postion is null */
 		public M cssePostionNull(){if(this.get("cssePostionNot")==null)this.put("cssePostionNot", "");this.put("cssePostion", null);return this;};
 		/** not .... */
 		public M cssePostionNot(){this.put("cssePostionNot", "not");return this;};
		/** 详细地点        **/
		public M csseAddress(Object csseAddress){this.put("csseAddress", csseAddress);return this;};
	 	/** and csse_address is null */
 		public M csseAddressNull(){if(this.get("csseAddressNot")==null)this.put("csseAddressNot", "");this.put("csseAddress", null);return this;};
 		/** not .... */
 		public M csseAddressNot(){this.put("csseAddressNot", "not");return this;};
		/** 活动说明 [非空]       **/
		public M csseDesc(Object csseDesc){this.put("csseDesc", csseDesc);return this;};
	 	/** and csse_desc is null */
 		public M csseDescNull(){if(this.get("csseDescNot")==null)this.put("csseDescNot", "");this.put("csseDesc", null);return this;};
 		/** not .... */
 		public M csseDescNot(){this.put("csseDescNot", "not");return this;};
		/** 开始时间 [非空]       **/
		public M csseStart(Object csseStart){this.put("csseStart", csseStart);return this;};
	 	/** and csse_start is null */
 		public M csseStartNull(){if(this.get("csseStartNot")==null)this.put("csseStartNot", "");this.put("csseStart", null);return this;};
 		/** not .... */
 		public M csseStartNot(){this.put("csseStartNot", "not");return this;};
 		/** and csse_start >= ? */
 		public M csseStartStart(Object start){this.put("csseStartStart", start);return this;};			
 		/** and csse_start <= ? */
 		public M csseStartEnd(Object end){this.put("csseStartEnd", end);return this;};
		/** 结束时间 [非空]       **/
		public M csseFinish(Object csseFinish){this.put("csseFinish", csseFinish);return this;};
	 	/** and csse_finish is null */
 		public M csseFinishNull(){if(this.get("csseFinishNot")==null)this.put("csseFinishNot", "");this.put("csseFinish", null);return this;};
 		/** not .... */
 		public M csseFinishNot(){this.put("csseFinishNot", "not");return this;};
 		/** and csse_finish >= ? */
 		public M csseFinishStart(Object start){this.put("csseFinishStart", start);return this;};			
 		/** and csse_finish <= ? */
 		public M csseFinishEnd(Object end){this.put("csseFinishEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M csseUpdateTime(Object csseUpdateTime){this.put("csseUpdateTime", csseUpdateTime);return this;};
	 	/** and csse_update_time is null */
 		public M csseUpdateTimeNull(){if(this.get("csseUpdateTimeNot")==null)this.put("csseUpdateTimeNot", "");this.put("csseUpdateTime", null);return this;};
 		/** not .... */
 		public M csseUpdateTimeNot(){this.put("csseUpdateTimeNot", "not");return this;};
 		/** and csse_update_time >= ? */
 		public M csseUpdateTimeStart(Object start){this.put("csseUpdateTimeStart", start);return this;};			
 		/** and csse_update_time <= ? */
 		public M csseUpdateTimeEnd(Object end){this.put("csseUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csseAddTime(Object csseAddTime){this.put("csseAddTime", csseAddTime);return this;};
	 	/** and csse_add_time is null */
 		public M csseAddTimeNull(){if(this.get("csseAddTimeNot")==null)this.put("csseAddTimeNot", "");this.put("csseAddTime", null);return this;};
 		/** not .... */
 		public M csseAddTimeNot(){this.put("csseAddTimeNot", "not");return this;};
 		/** and csse_add_time >= ? */
 		public M csseAddTimeStart(Object start){this.put("csseAddTimeStart", start);return this;};			
 		/** and csse_add_time <= ? */
 		public M csseAddTimeEnd(Object end){this.put("csseAddTimeEnd", end);return this;};
		/** 备注信息        **/
		public M csseRemark(Object csseRemark){this.put("csseRemark", csseRemark);return this;};
	 	/** and csse_remark is null */
 		public M csseRemarkNull(){if(this.get("csseRemarkNot")==null)this.put("csseRemarkNot", "");this.put("csseRemark", null);return this;};
 		/** not .... */
 		public M csseRemarkNot(){this.put("csseRemarkNot", "not");return this;};
		/** 状态 [非空]   0:筹备中 1:进行中 2:已完成 3:已取消     **/
		public M csseStatus(Object csseStatus){this.put("csseStatus", csseStatus);return this;};
	 	/** and csse_status is null */
 		public M csseStatusNull(){if(this.get("csseStatusNot")==null)this.put("csseStatusNot", "");this.put("csseStatus", null);return this;};
 		/** not .... */
 		public M csseStatusNot(){this.put("csseStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有销售活动 **/
		public @api List<CsSaleEvent> list(Integer size){
			return getCsSaleEventList(this,size);
		}
		/** 获取销售活动分页 **/
		public @api Page<CsSaleEvent> page(int page,int size){
			return getCsSaleEventPage(page, size , this);
		}
		/** 根据查询条件取销售活动 **/
		public @api CsSaleEvent get(){
			return getCsSaleEvent(this);
		}
		/** 获取销售活动数 **/
		public @api Long count(){
			return getCsSaleEventCount(this);
		}
		/** 获取销售活动表达式 **/
		public @api <T> T eval(String strEval){
			return getCsSaleEventEval(strEval,this);
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
			updateCsSaleEvent(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csseId="csseId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csseHost="csseHost";
		/** 活动主题 [非空]       **/
		public final static @type(String.class) @like String csseSubject="csseSubject";
		/** 归属战役 [非空] [TbSaleBattle]      **/
		public final static @type(Long.class)  String csseBattle="csseBattle";
		/** 活动类型 [非空] [SrvProperty]  sale_event_type:销售活动类型  比如：促销活动 展架直销 会议培训 展会 研讨会 网络推广   **/
		public final static @type(Long.class)  String csseType="csseType";
		/** 负责人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String cssePerson="cssePerson";
		/** 位置 [非空]       **/
		public final static @type(String.class) @like String cssePostion="cssePostion";
		/** 详细地点        **/
		public final static @type(String.class) @like String csseAddress="csseAddress";
		/** 活动说明 [非空]       **/
		public final static @type(String.class) @like String csseDesc="csseDesc";
		/** 开始时间 [非空]       **/
		public final static @type(Date.class)  String csseStart="csseStart";
	 	/** and csse_start >= ? */
 		public final static @type(Date.class) String csseStartStart="csseStartStart";
 		/** and csse_start <= ? */
 		public final static @type(Date.class) String csseStartEnd="csseStartEnd";
		/** 结束时间 [非空]       **/
		public final static @type(Date.class)  String csseFinish="csseFinish";
	 	/** and csse_finish >= ? */
 		public final static @type(Date.class) String csseFinishStart="csseFinishStart";
 		/** and csse_finish <= ? */
 		public final static @type(Date.class) String csseFinishEnd="csseFinishEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csseUpdateTime="csseUpdateTime";
	 	/** and csse_update_time >= ? */
 		public final static @type(Date.class) String csseUpdateTimeStart="csseUpdateTimeStart";
 		/** and csse_update_time <= ? */
 		public final static @type(Date.class) String csseUpdateTimeEnd="csseUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csseAddTime="csseAddTime";
	 	/** and csse_add_time >= ? */
 		public final static @type(Date.class) String csseAddTimeStart="csseAddTimeStart";
 		/** and csse_add_time <= ? */
 		public final static @type(Date.class) String csseAddTimeEnd="csseAddTimeEnd";
		/** 备注信息        **/
		public final static @type(String.class) @like String csseRemark="csseRemark";
		/** 状态 [非空]   0:筹备中 1:进行中 2:已完成 3:已取消     **/
		public final static @type(Short.class)  String csseStatus="csseStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csseId="csse_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csseHost="csse_host";
		/** 活动主题 [非空]       **/
		public final static String csseSubject="csse_subject";
		/** 归属战役 [非空] [TbSaleBattle]      **/
		public final static String csseBattle="csse_battle";
		/** 活动类型 [非空] [SrvProperty]  sale_event_type:销售活动类型  比如：促销活动 展架直销 会议培训 展会 研讨会 网络推广   **/
		public final static String csseType="csse_type";
		/** 负责人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cssePerson="csse_person";
		/** 位置 [非空]       **/
		public final static String cssePostion="csse_postion";
		/** 详细地点        **/
		public final static String csseAddress="csse_address";
		/** 活动说明 [非空]       **/
		public final static String csseDesc="csse_desc";
		/** 开始时间 [非空]       **/
		public final static String csseStart="csse_start";
		/** 结束时间 [非空]       **/
		public final static String csseFinish="csse_finish";
		/** 修改时间 [非空]       **/
		public final static String csseUpdateTime="csse_update_time";
		/** 添加时间 [非空]       **/
		public final static String csseAddTime="csse_add_time";
		/** 备注信息        **/
		public final static String csseRemark="csse_remark";
		/** 状态 [非空]   0:筹备中 1:进行中 2:已完成 3:已取消     **/
		public final static String csseStatus="csse_status";
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
				$.Set(name,CsSaleEvent.getCsSaleEvent(params));
			else
				$.Set(name,CsSaleEvent.getCsSaleEventList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取销售活动数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsSaleEvent) $.GetRequest("CsSaleEvent$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsSaleEvent.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsSaleEvent.getCsSaleEvent(params);
			else
				value = CsSaleEvent.getCsSaleEventList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsSaleEvent.Get($.add(CsSaleEvent.F.csseId,param));
		else if(!$.empty(param.toString()))
			value = CsSaleEvent.get(Long.valueOf(param.toString()));
		$.SetRequest("CsSaleEvent$"+param.hashCode(), value);
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
	public void mergeSet(CsSaleEvent old){
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