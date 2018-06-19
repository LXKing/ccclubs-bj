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

@namespace("configurator/wx/wxmt")
public @caption("微信下行") @table("cs_wx_mt") class CsWxMt implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cswm_id")   @primary  @note("  ") Long cswmId;// 主键 非空     
	private @caption("城市") @column("cswm_host")    @relate("$cswmHost") @RelateClass(SrvHost.class)  @note("  ") Long cswmHost;// 非空     
 	private SrvHost $cswmHost;//关联对象[运营城市]
	private @caption("平台类型") @column("cswm_platform")    @note(" 0:全部 1:微信 2:支付宝  ") Short cswmPlatform;// 非空 0:全部 1:微信 2:支付宝     
	private @caption("三方标识") @column("cswm_openid")    @note("  ") String cswmOpenid;//     
	private @caption("关联会员") @column("cswm_member")    @relate("$cswmMember") @RelateClass(CsMember.class)  @note("  ") Long cswmMember;//     
 	private CsMember $cswmMember;//关联对象[会员帐号]
	private @caption("分类标识") @column("cswm_msg_flag")    @note("  ") String cswmMsgFlag;//     
	private @caption("内容类型") @column("cswm_content_type")    @note(" 0:文本 1:图片 2:图文 3:模板  ") Short cswmContentType;// 非空 0:文本 1:图片 2:图文 3:模板     
	private @caption("关联对象") @column("cswm_relate")    @note("  ") String cswmRelate;//    CsTrouble,CsViolat,CsOrder 存储格式:类名@ID,其中类名包括CsTrouble或CsViolat或CsOrder
	private Object $cswmRelate;//泛关联对象，对象类名由cswmRelate字段中标明
	private @caption("消息内容") @column("cswm_content")    @note("  直接保存xml或json") String cswmContent;//  直接保存xml或json   
	private @caption("关联上行") @column("cswm_mo")    @relate("$cswmMo") @RelateClass(CsWxMo.class)  @note("  ") Long cswmMo;//     
 	private CsWxMo $cswmMo;//关联对象[微信上行]
	private @caption("发送时间") @column("cswm_send_time")    @note("  ") Date cswmSendTime;//     
	private @caption("发送状态") @column("cswm_mask")    @note(" 1:微信已发 2:支付宝已发  ") Integer cswmMask;// 1:微信已发 2:支付宝已发     
	private @caption("成功状态") @column("cswm_success")    @note(" 1:微信成功 2:支付宝成功  ") Integer cswmSuccess;// 1:微信成功 2:支付宝成功     
	private @caption("添加时间") @column("cswm_add_time")    @note("  ") Date cswmAddTime;// 非空     
	private @caption("数据状态") @column("cswm_status")    @note(" 0:默认  ") Short cswmStatus;// 非空 0:默认     
	
	//默认构造函数
	public CsWxMt(){
	
	}
	
	//主键构造函数
	public CsWxMt(Long id){
		this.cswmId = id;
	}
	
	/**所有字段构造函数 CsWxMt(cswmHost,cswmPlatform,cswmOpenid,cswmMember,cswmMsgFlag,cswmContentType,cswmRelate,cswmContent,cswmMo,cswmSendTime,cswmMask,cswmSuccess,cswmAddTime,cswmStatus)
	 CsWxMt(
	 	$.getLong("cswmHost")//城市 [非空]
	 	,$.getShort("cswmPlatform")//平台类型 [非空]
	 	,$.getString("cswmOpenid")//三方标识
	 	,$.getLong("cswmMember")//关联会员
	 	,$.getString("cswmMsgFlag")//分类标识
	 	,$.getShort("cswmContentType")//内容类型 [非空]
	 	,$.getString("cswmRelate")//关联对象
	 	,$.getString("cswmContent")//消息内容
	 	,$.getLong("cswmMo")//关联上行
	 	,$.getDate("cswmSendTime")//发送时间
	 	,$.getInteger("cswmMask")//发送状态
	 	,$.getInteger("cswmSuccess")//成功状态
	 	,$.getDate("cswmAddTime")//添加时间 [非空]
	 	,$.getShort("cswmStatus")//数据状态 [非空]
	 )
	**/
	public CsWxMt(Long cswmHost,Short cswmPlatform,String cswmOpenid,Long cswmMember,String cswmMsgFlag,Short cswmContentType,String cswmRelate,String cswmContent,Long cswmMo,Date cswmSendTime,Integer cswmMask,Integer cswmSuccess,Date cswmAddTime,Short cswmStatus){
		this.cswmHost=cswmHost;
		this.cswmPlatform=cswmPlatform;
		this.cswmOpenid=cswmOpenid;
		this.cswmMember=cswmMember;
		this.cswmMsgFlag=cswmMsgFlag;
		this.cswmContentType=cswmContentType;
		this.cswmRelate=cswmRelate;
		this.cswmContent=cswmContent;
		this.cswmMo=cswmMo;
		this.cswmSendTime=cswmSendTime;
		this.cswmMask=cswmMask;
		this.cswmSuccess=cswmSuccess;
		this.cswmAddTime=cswmAddTime;
		this.cswmStatus=cswmStatus;
	}
	
	//设置非空字段
	public CsWxMt setNotNull(Long cswmHost,Short cswmPlatform,Short cswmContentType,Date cswmAddTime,Short cswmStatus){
		this.cswmHost=cswmHost;
		this.cswmPlatform=cswmPlatform;
		this.cswmContentType=cswmContentType;
		this.cswmAddTime=cswmAddTime;
		this.cswmStatus=cswmStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsWxMt cswmId(Long cswmId){
		this.cswmId = cswmId;
		this.setSeted(F.cswmId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsWxMt cswmHost(Long cswmHost){
		this.cswmHost = cswmHost;
		this.setSeted(F.cswmHost);
		return this;
	}
	/** 平台类型 [非空]   0:全部 1:微信 2:支付宝     **/
	public CsWxMt cswmPlatform(Short cswmPlatform){
		this.cswmPlatform = cswmPlatform;
		this.setSeted(F.cswmPlatform);
		return this;
	}
	/** 三方标识        **/
	public CsWxMt cswmOpenid(String cswmOpenid){
		this.cswmOpenid = cswmOpenid;
		this.setSeted(F.cswmOpenid);
		return this;
	}
	/** 关联会员  [CsMember]      **/
	public CsWxMt cswmMember(Long cswmMember){
		this.cswmMember = cswmMember;
		this.setSeted(F.cswmMember);
		return this;
	}
	/** 分类标识        **/
	public CsWxMt cswmMsgFlag(String cswmMsgFlag){
		this.cswmMsgFlag = cswmMsgFlag;
		this.setSeted(F.cswmMsgFlag);
		return this;
	}
	/** 内容类型 [非空]   0:文本 1:图片 2:图文 3:模板     **/
	public CsWxMt cswmContentType(Short cswmContentType){
		this.cswmContentType = cswmContentType;
		this.setSeted(F.cswmContentType);
		return this;
	}
	/** 关联对象       CsTrouble,CsViolat,CsOrder **/
	public CsWxMt cswmRelate(String cswmRelate){
		this.cswmRelate = cswmRelate;
		this.setSeted(F.cswmRelate);
		return this;
	}
	/** 消息内容     直接保存xml或json   **/
	public CsWxMt cswmContent(String cswmContent){
		this.cswmContent = cswmContent;
		this.setSeted(F.cswmContent);
		return this;
	}
	/** 关联上行  [CsWxMo]      **/
	public CsWxMt cswmMo(Long cswmMo){
		this.cswmMo = cswmMo;
		this.setSeted(F.cswmMo);
		return this;
	}
	/** 发送时间        **/
	public CsWxMt cswmSendTime(Date cswmSendTime){
		this.cswmSendTime = cswmSendTime;
		this.setSeted(F.cswmSendTime);
		return this;
	}
	/** 发送状态    1:微信已发 2:支付宝已发     **/
	public CsWxMt cswmMask(Integer cswmMask){
		this.cswmMask = cswmMask;
		this.setSeted(F.cswmMask);
		return this;
	}
	/** 成功状态    1:微信成功 2:支付宝成功     **/
	public CsWxMt cswmSuccess(Integer cswmSuccess){
		this.cswmSuccess = cswmSuccess;
		this.setSeted(F.cswmSuccess);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsWxMt cswmAddTime(Date cswmAddTime){
		this.cswmAddTime = cswmAddTime;
		this.setSeted(F.cswmAddTime);
		return this;
	}
	/** 数据状态 [非空]   0:默认     **/
	public CsWxMt cswmStatus(Short cswmStatus){
		this.cswmStatus = cswmStatus;
		this.setSeted(F.cswmStatus);
		return this;
	}
	
	
	//克隆对象
	public CsWxMt clone(){
		CsWxMt clone = new CsWxMt();
		clone.cswmHost=this.cswmHost;
		clone.cswmPlatform=this.cswmPlatform;
		clone.cswmContentType=this.cswmContentType;
		clone.cswmAddTime=this.cswmAddTime;
		clone.cswmStatus=this.cswmStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取微信下行
	 * @param id
	 * @return
	 */
	public static @api CsWxMt get(Long id){		
		return getCsWxMtById(id);
	}
	/**
	 * 获取所有微信下行
	 * @return
	 */
	public static @api List<CsWxMt> list(Map params,Integer size){
		return getCsWxMtList(params,size);
	}
	/**
	 * 获取微信下行分页
	 * @return
	 */
	public static @api Page<CsWxMt> page(int page,int size,Map params){
		return getCsWxMtPage(page, size , params);
	}
	/**
	 * 根据查询条件取微信下行
	 * @param params
	 * @return
	 */
	public static @api CsWxMt Get(Map params){
		return getCsWxMt(params);
	}
	/**
	 * 获取微信下行数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsWxMtCount(params);
	}
	/**
	 * 获取微信下行数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsWxMtEval(eval,params);
	}
	
	/**
	 * 根据ID取微信下行
	 * @param id
	 * @return
	 */
	public static @api CsWxMt getCsWxMtById(Long id){		
		CsWxMt csWxMt = (CsWxMt) $.GetRequest("CsWxMt$"+id);
		if(csWxMt!=null)
			return csWxMt;
		com.ccclubs.service.admin.ICsWxMtService csWxMtService = $.GetSpringBean("csWxMtService");		
		return csWxMtService.getCsWxMtById(id);
	}
	
	
	/**
	 * 根据ID取微信下行的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsWxMt.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsWxMt csWxMt = get(id);
		if(csWxMt!=null){
			String strValue = csWxMt.getCswmId$();
			if(!"CswmId".equals("CswmId"))
				strValue+="("+csWxMt.getCswmId$()+")";
			MemCache.setValue(CsWxMt.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCswmId$();
		if(!"CswmId".equals("CswmId"))
			keyValue+="("+this.getCswmId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有微信下行
	 * @return
	 */
	public static @api List<CsWxMt> getCsWxMtList(Map params,Integer size){
		com.ccclubs.service.admin.ICsWxMtService csWxMtService = $.GetSpringBean("csWxMtService");
		return csWxMtService.getCsWxMtList(params, size);
	}
	
	/**
	 * 获取微信下行分页
	 * @return
	 */
	public static @api Page<CsWxMt> getCsWxMtPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsWxMtService csWxMtService = $.GetSpringBean("csWxMtService");
		return csWxMtService.getCsWxMtPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取微信下行
	 * @param params
	 * @return
	 */
	public static @api CsWxMt getCsWxMt(Map params){
		com.ccclubs.service.admin.ICsWxMtService csWxMtService = $.GetSpringBean("csWxMtService");
		return csWxMtService.getCsWxMt(params);
	}
	
	/**
	 * 获取微信下行数
	 * @return
	 */
	public static @api Long getCsWxMtCount(Map params){
		com.ccclubs.service.admin.ICsWxMtService csWxMtService = $.GetSpringBean("csWxMtService");
		return csWxMtService.getCsWxMtCount(params);
	}
		
		
	/**
	 * 获取微信下行自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsWxMtEval(String eval,Map params){
		com.ccclubs.service.admin.ICsWxMtService csWxMtService = $.GetSpringBean("csWxMtService");
		return csWxMtService.getCsWxMtEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsWxMt(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsWxMtService csWxMtService = $.GetSpringBean("csWxMtService");
		csWxMtService.updateCsWxMtByConfirm(set, where);
	}
	
	
	/**
	 * 保存微信下行对象
	 * @param params
	 * @return
	 */
	public CsWxMt save(){
		com.ccclubs.service.admin.ICsWxMtService csWxMtService = $.GetSpringBean("csWxMtService");
		if(this.getCswmId()!=null)
			csWxMtService.updateCsWxMt(this);
		else
			return csWxMtService.saveCsWxMt(this);
		return this;
	}
	
	
	/**
	 * 更新微信下行对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsWxMtService csWxMtService = $.GetSpringBean("csWxMtService");
		csWxMtService.updateCsWxMt$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsWxMtService csWxMtService = $.GetSpringBean("csWxMtService");
		if($.equals($.config("logic_delete"),"true"))
			csWxMtService.removeCsWxMtById(this.getCswmId());
		else
			csWxMtService.deleteCsWxMtById(this.getCswmId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsWxMtService csWxMtService = $.GetSpringBean("csWxMtService");
		return csWxMtService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCswmId(){
		return this.cswmId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCswmId$(){
		String strValue="";
		 strValue=$.str(this.getCswmId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCswmId(Long cswmId){
		this.cswmId = cswmId;
		this.setSeted(F.cswmId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCswmHost(){
		return this.cswmHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCswmHost$(){
		String strValue="";
		if(this.getCswmHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCswmHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCswmHost(Long cswmHost){
		this.cswmHost = cswmHost;
		this.setSeted(F.cswmHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cswmHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCswmHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCswmHost()!=null){
 			srvHost = SrvHost.get(this.getCswmHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCswmHost(), srvHost);
	 	return srvHost;
	}
	/*******************************平台类型**********************************/	
	/**
	* 平台类型 [非空]   0:全部 1:微信 2:支付宝    
	**/
	public Short getCswmPlatform(){
		return this.cswmPlatform;
	}
	/**
	* 获取平台类型格式化(toString)
	**/
	public String getCswmPlatform$(){
		String strValue="";
		 if($.equals($.str(this.getCswmPlatform()),"0"))
			strValue=$.str("全部");		 
		 if($.equals($.str(this.getCswmPlatform()),"1"))
			strValue=$.str("微信");		 
		 if($.equals($.str(this.getCswmPlatform()),"2"))
			strValue=$.str("支付宝");		 
	 	 return strValue;
	}
	/**
	* 平台类型 [非空]   0:全部 1:微信 2:支付宝    
	**/
	public void setCswmPlatform(Short cswmPlatform){
		this.cswmPlatform = cswmPlatform;
		this.setSeted(F.cswmPlatform);
	}
	/*******************************三方标识**********************************/	
	/**
	* 三方标识       
	**/
	public String getCswmOpenid(){
		return this.cswmOpenid;
	}
	/**
	* 获取三方标识格式化(toString)
	**/
	public String getCswmOpenid$(){
		String strValue="";
		 strValue=$.str(this.getCswmOpenid());
	 	 return strValue;
	}
	/**
	* 三方标识       
	**/
	public void setCswmOpenid(String cswmOpenid){
		this.cswmOpenid = cswmOpenid;
		this.setSeted(F.cswmOpenid);
	}
	/*******************************关联会员**********************************/	
	/**
	* 关联会员  [CsMember]     
	**/
	public Long getCswmMember(){
		return this.cswmMember;
	}
	/**
	* 获取关联会员格式化(toString)
	**/
	public String getCswmMember$(){
		String strValue="";
		if(this.getCswmMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCswmMember()));
		}			
	 	 return strValue;
	}
	/**
	* 关联会员  [CsMember]     
	**/
	public void setCswmMember(Long cswmMember){
		this.cswmMember = cswmMember;
		this.setSeted(F.cswmMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$cswmMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCswmMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCswmMember()!=null){
 			csMember = CsMember.get(this.getCswmMember());
 		}
 		$.SetRequest("CsMember$"+this.getCswmMember(), csMember);
	 	return csMember;
	}
	/*******************************分类标识**********************************/	
	/**
	* 分类标识       
	**/
	public String getCswmMsgFlag(){
		return this.cswmMsgFlag;
	}
	/**
	* 获取分类标识格式化(toString)
	**/
	public String getCswmMsgFlag$(){
		String strValue="";
		 strValue=$.str(this.getCswmMsgFlag());
	 	 return strValue;
	}
	/**
	* 分类标识       
	**/
	public void setCswmMsgFlag(String cswmMsgFlag){
		this.cswmMsgFlag = cswmMsgFlag;
		this.setSeted(F.cswmMsgFlag);
	}
	/*******************************内容类型**********************************/	
	/**
	* 内容类型 [非空]   0:文本 1:图片 2:图文 3:模板    
	**/
	public Short getCswmContentType(){
		return this.cswmContentType;
	}
	/**
	* 获取内容类型格式化(toString)
	**/
	public String getCswmContentType$(){
		String strValue="";
		 if($.equals($.str(this.getCswmContentType()),"0"))
			strValue=$.str("文本");		 
		 if($.equals($.str(this.getCswmContentType()),"1"))
			strValue=$.str("图片");		 
		 if($.equals($.str(this.getCswmContentType()),"2"))
			strValue=$.str("图文");		 
		 if($.equals($.str(this.getCswmContentType()),"3"))
			strValue=$.str("模板");		 
	 	 return strValue;
	}
	/**
	* 内容类型 [非空]   0:文本 1:图片 2:图文 3:模板    
	**/
	public void setCswmContentType(Short cswmContentType){
		this.cswmContentType = cswmContentType;
		this.setSeted(F.cswmContentType);
	}
	/*******************************关联对象**********************************/	
	/**
	* 关联对象       CsTrouble,CsViolat,CsOrder
	**/
	public String getCswmRelate(){
		return this.cswmRelate;
	}
	/**
	* 获取关联对象格式化(toString)
	**/
	public String getCswmRelate$(){
		String strValue="";
	 	 Object object = this.get$cswmRelate();
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
	* 关联对象       CsTrouble,CsViolat,CsOrder
	**/
	public void setCswmRelate(String cswmRelate){
		this.cswmRelate = cswmRelate;
		this.setSeted(F.cswmRelate);
	}
	/**
	* 泛关联对象，对象类名由cswmRelate字段中标明
	**/	 			
 	public <T> T get$cswmRelate(){
		if($.empty(this.cswmRelate))
 			return null;
 		try{
	 		String ClassName = this.cswmRelate.split("@")[0];
	 		Long id = Long.parseLong(this.cswmRelate.split("@")[1]);
	 		Class<?> cls = Class.forName("com.ccclubs.model."+ClassName);
	 		return (T)cls.getMethod("get", Long.class).invoke(null, id);	
 		}catch(Exception ex){
 			ex.printStackTrace();
 			return null;
 		}
	}
	/*******************************消息内容**********************************/	
	/**
	* 消息内容     直接保存xml或json  
	**/
	public String getCswmContent(){
		return this.cswmContent;
	}
	/**
	* 获取消息内容格式化(toString)
	**/
	public String getCswmContent$(){
		String strValue="";
		 strValue=$.str(this.getCswmContent());
	 	 return strValue;
	}
	/**
	* 消息内容     直接保存xml或json  
	**/
	public void setCswmContent(String cswmContent){
		this.cswmContent = cswmContent;
		this.setSeted(F.cswmContent);
	}
	/*******************************关联上行**********************************/	
	/**
	* 关联上行  [CsWxMo]     
	**/
	public Long getCswmMo(){
		return this.cswmMo;
	}
	/**
	* 获取关联上行格式化(toString)
	**/
	public String getCswmMo$(){
		String strValue="";
		if(this.getCswmMo()!=null){
				strValue+=$.str(CsWxMo.getKeyValue(this.getCswmMo()));
		}			
	 	 return strValue;
	}
	/**
	* 关联上行  [CsWxMo]     
	**/
	public void setCswmMo(Long cswmMo){
		this.cswmMo = cswmMo;
		this.setSeted(F.cswmMo);
	}
	/**
	* 获取关联对象[微信上行]
	**/	 			
 	public CsWxMo get$cswmMo(){
 		com.ccclubs.model.CsWxMo csWxMo = (com.ccclubs.model.CsWxMo) $.GetRequest("CsWxMo$"+this.getCswmMo());
 		if(csWxMo!=null)
			return csWxMo;
		if(this.getCswmMo()!=null){
 			csWxMo = CsWxMo.get(this.getCswmMo());
 		}
 		$.SetRequest("CsWxMo$"+this.getCswmMo(), csWxMo);
	 	return csWxMo;
	}
	/*******************************发送时间**********************************/	
	/**
	* 发送时间       
	**/
	public Date getCswmSendTime(){
		return this.cswmSendTime;
	}
	/**
	* 获取发送时间格式化(toString)
	**/
	public String getCswmSendTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCswmSendTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 发送时间       
	**/
	public void setCswmSendTime(Date cswmSendTime){
		this.cswmSendTime = cswmSendTime;
		this.setSeted(F.cswmSendTime);
	}
	/*******************************发送状态**********************************/	
	/**
	* 发送状态    1:微信已发 2:支付宝已发    
	**/
	public Integer getCswmMask(){
		return this.cswmMask;
	}
	/**
	* 获取发送状态格式化(toString)
	**/
	public String getCswmMask$(){
		String strValue="";
		if(this.getCswmMask()!=null){
			if((this.getCswmMask() & 1 ) == 1)
				strValue+="["+$.str("微信已发")+"]";		 
			if((this.getCswmMask() & 2 ) == 2)
				strValue+="["+$.str("支付宝已发")+"]";		 
		}
	 	 return strValue;
	}
	/**
	* 发送状态    1:微信已发 2:支付宝已发    
	**/
	public void setCswmMask(Integer cswmMask){
		this.cswmMask = cswmMask;
		this.setSeted(F.cswmMask);
	}
	/*******************************成功状态**********************************/	
	/**
	* 成功状态    1:微信成功 2:支付宝成功    
	**/
	public Integer getCswmSuccess(){
		return this.cswmSuccess;
	}
	/**
	* 获取成功状态格式化(toString)
	**/
	public String getCswmSuccess$(){
		String strValue="";
		if(this.getCswmSuccess()!=null){
			if((this.getCswmSuccess() & 1 ) == 1)
				strValue+="["+$.str("微信成功")+"]";		 
			if((this.getCswmSuccess() & 2 ) == 2)
				strValue+="["+$.str("支付宝成功")+"]";		 
		}
	 	 return strValue;
	}
	/**
	* 成功状态    1:微信成功 2:支付宝成功    
	**/
	public void setCswmSuccess(Integer cswmSuccess){
		this.cswmSuccess = cswmSuccess;
		this.setSeted(F.cswmSuccess);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCswmAddTime(){
		return this.cswmAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCswmAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCswmAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCswmAddTime(Date cswmAddTime){
		this.cswmAddTime = cswmAddTime;
		this.setSeted(F.cswmAddTime);
	}
	/*******************************数据状态**********************************/	
	/**
	* 数据状态 [非空]   0:默认    
	**/
	public Short getCswmStatus(){
		return this.cswmStatus;
	}
	/**
	* 获取数据状态格式化(toString)
	**/
	public String getCswmStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCswmStatus()),"0"))
			strValue=$.str("默认");		 
	 	 return strValue;
	}
	/**
	* 数据状态 [非空]   0:默认    
	**/
	public void setCswmStatus(Short cswmStatus){
		this.cswmStatus = cswmStatus;
		this.setSeted(F.cswmStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsWxMt.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsWxMt.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsWxMt.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsWxMt.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsWxMt.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsWxMt.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsWxMt.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsWxMt.this);
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
		public M cswmId(Object cswmId){this.put("cswmId", cswmId);return this;};
	 	/** and cswm_id is null */
 		public M cswmIdNull(){if(this.get("cswmIdNot")==null)this.put("cswmIdNot", "");this.put("cswmId", null);return this;};
 		/** not .... */
 		public M cswmIdNot(){this.put("cswmIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M cswmHost(Object cswmHost){this.put("cswmHost", cswmHost);return this;};
	 	/** and cswm_host is null */
 		public M cswmHostNull(){if(this.get("cswmHostNot")==null)this.put("cswmHostNot", "");this.put("cswmHost", null);return this;};
 		/** not .... */
 		public M cswmHostNot(){this.put("cswmHostNot", "not");return this;};
		/** 平台类型 [非空]   0:全部 1:微信 2:支付宝     **/
		public M cswmPlatform(Object cswmPlatform){this.put("cswmPlatform", cswmPlatform);return this;};
	 	/** and cswm_platform is null */
 		public M cswmPlatformNull(){if(this.get("cswmPlatformNot")==null)this.put("cswmPlatformNot", "");this.put("cswmPlatform", null);return this;};
 		/** not .... */
 		public M cswmPlatformNot(){this.put("cswmPlatformNot", "not");return this;};
		/** 三方标识        **/
		public M cswmOpenid(Object cswmOpenid){this.put("cswmOpenid", cswmOpenid);return this;};
	 	/** and cswm_openid is null */
 		public M cswmOpenidNull(){if(this.get("cswmOpenidNot")==null)this.put("cswmOpenidNot", "");this.put("cswmOpenid", null);return this;};
 		/** not .... */
 		public M cswmOpenidNot(){this.put("cswmOpenidNot", "not");return this;};
		/** 关联会员  [CsMember]      **/
		public M cswmMember(Object cswmMember){this.put("cswmMember", cswmMember);return this;};
	 	/** and cswm_member is null */
 		public M cswmMemberNull(){if(this.get("cswmMemberNot")==null)this.put("cswmMemberNot", "");this.put("cswmMember", null);return this;};
 		/** not .... */
 		public M cswmMemberNot(){this.put("cswmMemberNot", "not");return this;};
		public M cswmMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("cswmMember$on", value);
			return this;
		};	
		/** 分类标识        **/
		public M cswmMsgFlag(Object cswmMsgFlag){this.put("cswmMsgFlag", cswmMsgFlag);return this;};
	 	/** and cswm_msg_flag is null */
 		public M cswmMsgFlagNull(){if(this.get("cswmMsgFlagNot")==null)this.put("cswmMsgFlagNot", "");this.put("cswmMsgFlag", null);return this;};
 		/** not .... */
 		public M cswmMsgFlagNot(){this.put("cswmMsgFlagNot", "not");return this;};
		/** 内容类型 [非空]   0:文本 1:图片 2:图文 3:模板     **/
		public M cswmContentType(Object cswmContentType){this.put("cswmContentType", cswmContentType);return this;};
	 	/** and cswm_content_type is null */
 		public M cswmContentTypeNull(){if(this.get("cswmContentTypeNot")==null)this.put("cswmContentTypeNot", "");this.put("cswmContentType", null);return this;};
 		/** not .... */
 		public M cswmContentTypeNot(){this.put("cswmContentTypeNot", "not");return this;};
		/** 关联对象       CsTrouble,CsViolat,CsOrder **/
		public M cswmRelate(Object cswmRelate){this.put("cswmRelate", cswmRelate);return this;};
	 	/** and cswm_relate is null */
 		public M cswmRelateNull(){if(this.get("cswmRelateNot")==null)this.put("cswmRelateNot", "");this.put("cswmRelate", null);return this;};
 		/** not .... */
 		public M cswmRelateNot(){this.put("cswmRelateNot", "not");return this;};
		/** 消息内容     直接保存xml或json   **/
		public M cswmContent(Object cswmContent){this.put("cswmContent", cswmContent);return this;};
	 	/** and cswm_content is null */
 		public M cswmContentNull(){if(this.get("cswmContentNot")==null)this.put("cswmContentNot", "");this.put("cswmContent", null);return this;};
 		/** not .... */
 		public M cswmContentNot(){this.put("cswmContentNot", "not");return this;};
		/** 关联上行  [CsWxMo]      **/
		public M cswmMo(Object cswmMo){this.put("cswmMo", cswmMo);return this;};
	 	/** and cswm_mo is null */
 		public M cswmMoNull(){if(this.get("cswmMoNot")==null)this.put("cswmMoNot", "");this.put("cswmMo", null);return this;};
 		/** not .... */
 		public M cswmMoNot(){this.put("cswmMoNot", "not");return this;};
		public M cswmMo$on(CsWxMo.M value){
			this.put("CsWxMo", value);
			this.put("cswmMo$on", value);
			return this;
		};	
		/** 发送时间        **/
		public M cswmSendTime(Object cswmSendTime){this.put("cswmSendTime", cswmSendTime);return this;};
	 	/** and cswm_send_time is null */
 		public M cswmSendTimeNull(){if(this.get("cswmSendTimeNot")==null)this.put("cswmSendTimeNot", "");this.put("cswmSendTime", null);return this;};
 		/** not .... */
 		public M cswmSendTimeNot(){this.put("cswmSendTimeNot", "not");return this;};
 		/** and cswm_send_time >= ? */
 		public M cswmSendTimeStart(Object start){this.put("cswmSendTimeStart", start);return this;};			
 		/** and cswm_send_time <= ? */
 		public M cswmSendTimeEnd(Object end){this.put("cswmSendTimeEnd", end);return this;};
		/** 发送状态    1:微信已发 2:支付宝已发     **/
		public M cswmMask(Object cswmMask){this.put("cswmMask", cswmMask);return this;};
	 	/** and cswm_mask is null */
 		public M cswmMaskNull(){if(this.get("cswmMaskNot")==null)this.put("cswmMaskNot", "");this.put("cswmMask", null);return this;};
 		/** not .... */
 		public M cswmMaskNot(){this.put("cswmMaskNot", "not");return this;};
		/** 成功状态    1:微信成功 2:支付宝成功     **/
		public M cswmSuccess(Object cswmSuccess){this.put("cswmSuccess", cswmSuccess);return this;};
	 	/** and cswm_success is null */
 		public M cswmSuccessNull(){if(this.get("cswmSuccessNot")==null)this.put("cswmSuccessNot", "");this.put("cswmSuccess", null);return this;};
 		/** not .... */
 		public M cswmSuccessNot(){this.put("cswmSuccessNot", "not");return this;};
		/** 添加时间 [非空]       **/
		public M cswmAddTime(Object cswmAddTime){this.put("cswmAddTime", cswmAddTime);return this;};
	 	/** and cswm_add_time is null */
 		public M cswmAddTimeNull(){if(this.get("cswmAddTimeNot")==null)this.put("cswmAddTimeNot", "");this.put("cswmAddTime", null);return this;};
 		/** not .... */
 		public M cswmAddTimeNot(){this.put("cswmAddTimeNot", "not");return this;};
 		/** and cswm_add_time >= ? */
 		public M cswmAddTimeStart(Object start){this.put("cswmAddTimeStart", start);return this;};			
 		/** and cswm_add_time <= ? */
 		public M cswmAddTimeEnd(Object end){this.put("cswmAddTimeEnd", end);return this;};
		/** 数据状态 [非空]   0:默认     **/
		public M cswmStatus(Object cswmStatus){this.put("cswmStatus", cswmStatus);return this;};
	 	/** and cswm_status is null */
 		public M cswmStatusNull(){if(this.get("cswmStatusNot")==null)this.put("cswmStatusNot", "");this.put("cswmStatus", null);return this;};
 		/** not .... */
 		public M cswmStatusNot(){this.put("cswmStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有微信下行 **/
		public @api List<CsWxMt> list(Integer size){
			return getCsWxMtList(this,size);
		}
		/** 获取微信下行分页 **/
		public @api Page<CsWxMt> page(int page,int size){
			return getCsWxMtPage(page, size , this);
		}
		/** 根据查询条件取微信下行 **/
		public @api CsWxMt get(){
			return getCsWxMt(this);
		}
		/** 获取微信下行数 **/
		public @api Long count(){
			return getCsWxMtCount(this);
		}
		/** 获取微信下行表达式 **/
		public @api <T> T eval(String strEval){
			return getCsWxMtEval(strEval,this);
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
			updateCsWxMt(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cswmId="cswmId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cswmHost="cswmHost";
		/** 平台类型 [非空]   0:全部 1:微信 2:支付宝     **/
		public final static @type(Short.class)  String cswmPlatform="cswmPlatform";
		/** 三方标识        **/
		public final static @type(String.class) @like String cswmOpenid="cswmOpenid";
		/** 关联会员  [CsMember]      **/
		public final static @type(Long.class)  String cswmMember="cswmMember";
		/** 分类标识        **/
		public final static @type(String.class) @like String cswmMsgFlag="cswmMsgFlag";
		/** 内容类型 [非空]   0:文本 1:图片 2:图文 3:模板     **/
		public final static @type(Short.class)  String cswmContentType="cswmContentType";
		/** 关联对象       CsTrouble,CsViolat,CsOrder **/
		public final static @type(String.class)  String cswmRelate="cswmRelate";
		/** 消息内容     直接保存xml或json   **/
		public final static @type(String.class) @like String cswmContent="cswmContent";
		/** 关联上行  [CsWxMo]      **/
		public final static @type(Long.class)  String cswmMo="cswmMo";
		/** 发送时间        **/
		public final static @type(Date.class)  String cswmSendTime="cswmSendTime";
	 	/** and cswm_send_time >= ? */
 		public final static @type(Date.class) String cswmSendTimeStart="cswmSendTimeStart";
 		/** and cswm_send_time <= ? */
 		public final static @type(Date.class) String cswmSendTimeEnd="cswmSendTimeEnd";
		/** 发送状态    1:微信已发 2:支付宝已发     **/
		public final static @type(Integer.class)  String cswmMask="cswmMask";
		/** 成功状态    1:微信成功 2:支付宝成功     **/
		public final static @type(Integer.class)  String cswmSuccess="cswmSuccess";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cswmAddTime="cswmAddTime";
	 	/** and cswm_add_time >= ? */
 		public final static @type(Date.class) String cswmAddTimeStart="cswmAddTimeStart";
 		/** and cswm_add_time <= ? */
 		public final static @type(Date.class) String cswmAddTimeEnd="cswmAddTimeEnd";
		/** 数据状态 [非空]   0:默认     **/
		public final static @type(Short.class)  String cswmStatus="cswmStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cswmId="cswm_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cswmHost="cswm_host";
		/** 平台类型 [非空]   0:全部 1:微信 2:支付宝     **/
		public final static String cswmPlatform="cswm_platform";
		/** 三方标识        **/
		public final static String cswmOpenid="cswm_openid";
		/** 关联会员  [CsMember]      **/
		public final static String cswmMember="cswm_member";
		/** 分类标识        **/
		public final static String cswmMsgFlag="cswm_msg_flag";
		/** 内容类型 [非空]   0:文本 1:图片 2:图文 3:模板     **/
		public final static String cswmContentType="cswm_content_type";
		/** 关联对象       CsTrouble,CsViolat,CsOrder **/
		public final static String cswmRelate="cswm_relate";
		/** 消息内容     直接保存xml或json   **/
		public final static String cswmContent="cswm_content";
		/** 关联上行  [CsWxMo]      **/
		public final static String cswmMo="cswm_mo";
		/** 发送时间        **/
		public final static String cswmSendTime="cswm_send_time";
		/** 发送状态    1:微信已发 2:支付宝已发     **/
		public final static String cswmMask="cswm_mask";
		/** 成功状态    1:微信成功 2:支付宝成功     **/
		public final static String cswmSuccess="cswm_success";
		/** 添加时间 [非空]       **/
		public final static String cswmAddTime="cswm_add_time";
		/** 数据状态 [非空]   0:默认     **/
		public final static String cswmStatus="cswm_status";
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
				$.Set(name,CsWxMt.getCsWxMt(params));
			else
				$.Set(name,CsWxMt.getCsWxMtList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取微信下行数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsWxMt) $.GetRequest("CsWxMt$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsWxMt.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsWxMt.getCsWxMt(params);
			else
				value = CsWxMt.getCsWxMtList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsWxMt.Get($.add(CsWxMt.F.cswmId,param));
		else if(!$.empty(param.toString()))
			value = CsWxMt.get(Long.valueOf(param.toString()));
		$.SetRequest("CsWxMt$"+param.hashCode(), value);
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
	public void mergeSet(CsWxMt old){
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