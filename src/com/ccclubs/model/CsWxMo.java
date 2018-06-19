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

@namespace("configurator/wx/wxmo")
public @caption("微信上行") @table("cs_wx_mo") class CsWxMo implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cswm_id")   @primary  @note("  ") Long cswmId;// 主键 非空     
	private @caption("城市") @column("cswm_host")    @relate("$cswmHost") @RelateClass(SrvHost.class)  @note("  ") Long cswmHost;// 非空     
 	private SrvHost $cswmHost;//关联对象[运营城市]
	private @caption("平台类型") @column("cswm_platform")    @note(" 1:微信 2:支付宝  ") Short cswmPlatform;// 非空 1:微信 2:支付宝     
	private @caption("三方标识") @column("cswm_openid")    @note("  ") String cswmOpenid;// 非空     
	private @caption("关联会员") @column("cswm_member")    @relate("$cswmMember") @RelateClass(CsMember.class)  @note("  ") Long cswmMember;//     
 	private CsMember $cswmMember;//关联对象[会员帐号]
	private @caption("消息类型") @column("cswm_msg_type")    @note(" 0:普通消息 1:事件消息  ") Short cswmMsgType;// 非空 0:普通消息 1:事件消息     
	private @caption("内容类型") @column("cswm_content_type")    @note(" 0:文本 1:图片 2:图文 3:其它  ") Short cswmContentType;// 非空 0:文本 1:图片 2:图文 3:其它     
	private @caption("消息内容") @column("cswm_content")    @note("  直接保存xml或json") String cswmContent;//  直接保存xml或json   
	private @caption("添加时间") @column("cswm_add_time")    @note("  ") Date cswmAddTime;// 非空     
	private @caption("程序掩码") @column("cswm_mask")    @note(" 0:默认  ") Integer cswmMask;// 0:默认     
	private @caption("状态") @column("cswm_status")    @note(" 1:正常 0:无效  ") Short cswmStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsWxMo(){
	
	}
	
	//主键构造函数
	public CsWxMo(Long id){
		this.cswmId = id;
	}
	
	/**所有字段构造函数 CsWxMo(cswmHost,cswmPlatform,cswmOpenid,cswmMember,cswmMsgType,cswmContentType,cswmContent,cswmAddTime,cswmMask,cswmStatus)
	 CsWxMo(
	 	$.getLong("cswmHost")//城市 [非空]
	 	,$.getShort("cswmPlatform")//平台类型 [非空]
	 	,$.getString("cswmOpenid")//三方标识 [非空]
	 	,$.getLong("cswmMember")//关联会员
	 	,$.getShort("cswmMsgType")//消息类型 [非空]
	 	,$.getShort("cswmContentType")//内容类型 [非空]
	 	,$.getString("cswmContent")//消息内容
	 	,$.getDate("cswmAddTime")//添加时间 [非空]
	 	,$.getInteger("cswmMask")//程序掩码
	 	,$.getShort("cswmStatus")//状态 [非空]
	 )
	**/
	public CsWxMo(Long cswmHost,Short cswmPlatform,String cswmOpenid,Long cswmMember,Short cswmMsgType,Short cswmContentType,String cswmContent,Date cswmAddTime,Integer cswmMask,Short cswmStatus){
		this.cswmHost=cswmHost;
		this.cswmPlatform=cswmPlatform;
		this.cswmOpenid=cswmOpenid;
		this.cswmMember=cswmMember;
		this.cswmMsgType=cswmMsgType;
		this.cswmContentType=cswmContentType;
		this.cswmContent=cswmContent;
		this.cswmAddTime=cswmAddTime;
		this.cswmMask=cswmMask;
		this.cswmStatus=cswmStatus;
	}
	
	//设置非空字段
	public CsWxMo setNotNull(Long cswmHost,Short cswmPlatform,String cswmOpenid,Short cswmMsgType,Short cswmContentType,Date cswmAddTime,Short cswmStatus){
		this.cswmHost=cswmHost;
		this.cswmPlatform=cswmPlatform;
		this.cswmOpenid=cswmOpenid;
		this.cswmMsgType=cswmMsgType;
		this.cswmContentType=cswmContentType;
		this.cswmAddTime=cswmAddTime;
		this.cswmStatus=cswmStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsWxMo cswmId(Long cswmId){
		this.cswmId = cswmId;
		this.setSeted(F.cswmId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsWxMo cswmHost(Long cswmHost){
		this.cswmHost = cswmHost;
		this.setSeted(F.cswmHost);
		return this;
	}
	/** 平台类型 [非空]   1:微信 2:支付宝     **/
	public CsWxMo cswmPlatform(Short cswmPlatform){
		this.cswmPlatform = cswmPlatform;
		this.setSeted(F.cswmPlatform);
		return this;
	}
	/** 三方标识 [非空]       **/
	public CsWxMo cswmOpenid(String cswmOpenid){
		this.cswmOpenid = cswmOpenid;
		this.setSeted(F.cswmOpenid);
		return this;
	}
	/** 关联会员  [CsMember]      **/
	public CsWxMo cswmMember(Long cswmMember){
		this.cswmMember = cswmMember;
		this.setSeted(F.cswmMember);
		return this;
	}
	/** 消息类型 [非空]   0:普通消息 1:事件消息     **/
	public CsWxMo cswmMsgType(Short cswmMsgType){
		this.cswmMsgType = cswmMsgType;
		this.setSeted(F.cswmMsgType);
		return this;
	}
	/** 内容类型 [非空]   0:文本 1:图片 2:图文 3:其它     **/
	public CsWxMo cswmContentType(Short cswmContentType){
		this.cswmContentType = cswmContentType;
		this.setSeted(F.cswmContentType);
		return this;
	}
	/** 消息内容     直接保存xml或json   **/
	public CsWxMo cswmContent(String cswmContent){
		this.cswmContent = cswmContent;
		this.setSeted(F.cswmContent);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsWxMo cswmAddTime(Date cswmAddTime){
		this.cswmAddTime = cswmAddTime;
		this.setSeted(F.cswmAddTime);
		return this;
	}
	/** 程序掩码    0:默认     **/
	public CsWxMo cswmMask(Integer cswmMask){
		this.cswmMask = cswmMask;
		this.setSeted(F.cswmMask);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsWxMo cswmStatus(Short cswmStatus){
		this.cswmStatus = cswmStatus;
		this.setSeted(F.cswmStatus);
		return this;
	}
	
	
	//克隆对象
	public CsWxMo clone(){
		CsWxMo clone = new CsWxMo();
		clone.cswmHost=this.cswmHost;
		clone.cswmPlatform=this.cswmPlatform;
		clone.cswmOpenid=this.cswmOpenid;
		clone.cswmMsgType=this.cswmMsgType;
		clone.cswmContentType=this.cswmContentType;
		clone.cswmAddTime=this.cswmAddTime;
		clone.cswmStatus=this.cswmStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取微信上行
	 * @param id
	 * @return
	 */
	public static @api CsWxMo get(Long id){		
		return getCsWxMoById(id);
	}
	/**
	 * 获取所有微信上行
	 * @return
	 */
	public static @api List<CsWxMo> list(Map params,Integer size){
		return getCsWxMoList(params,size);
	}
	/**
	 * 获取微信上行分页
	 * @return
	 */
	public static @api Page<CsWxMo> page(int page,int size,Map params){
		return getCsWxMoPage(page, size , params);
	}
	/**
	 * 根据查询条件取微信上行
	 * @param params
	 * @return
	 */
	public static @api CsWxMo Get(Map params){
		return getCsWxMo(params);
	}
	/**
	 * 获取微信上行数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsWxMoCount(params);
	}
	/**
	 * 获取微信上行数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsWxMoEval(eval,params);
	}
	
	/**
	 * 根据ID取微信上行
	 * @param id
	 * @return
	 */
	public static @api CsWxMo getCsWxMoById(Long id){		
		CsWxMo csWxMo = (CsWxMo) $.GetRequest("CsWxMo$"+id);
		if(csWxMo!=null)
			return csWxMo;
		com.ccclubs.service.admin.ICsWxMoService csWxMoService = $.GetSpringBean("csWxMoService");		
		return csWxMoService.getCsWxMoById(id);
	}
	
	
	/**
	 * 根据ID取微信上行的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsWxMo.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsWxMo csWxMo = get(id);
		if(csWxMo!=null){
			String strValue = csWxMo.getCswmId$();
			if(!"CswmId".equals("CswmId"))
				strValue+="("+csWxMo.getCswmId$()+")";
			MemCache.setValue(CsWxMo.class, id ,strValue);
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
	 * 获取所有微信上行
	 * @return
	 */
	public static @api List<CsWxMo> getCsWxMoList(Map params,Integer size){
		com.ccclubs.service.admin.ICsWxMoService csWxMoService = $.GetSpringBean("csWxMoService");
		return csWxMoService.getCsWxMoList(params, size);
	}
	
	/**
	 * 获取微信上行分页
	 * @return
	 */
	public static @api Page<CsWxMo> getCsWxMoPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsWxMoService csWxMoService = $.GetSpringBean("csWxMoService");
		return csWxMoService.getCsWxMoPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取微信上行
	 * @param params
	 * @return
	 */
	public static @api CsWxMo getCsWxMo(Map params){
		com.ccclubs.service.admin.ICsWxMoService csWxMoService = $.GetSpringBean("csWxMoService");
		return csWxMoService.getCsWxMo(params);
	}
	
	/**
	 * 获取微信上行数
	 * @return
	 */
	public static @api Long getCsWxMoCount(Map params){
		com.ccclubs.service.admin.ICsWxMoService csWxMoService = $.GetSpringBean("csWxMoService");
		return csWxMoService.getCsWxMoCount(params);
	}
		
		
	/**
	 * 获取微信上行自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsWxMoEval(String eval,Map params){
		com.ccclubs.service.admin.ICsWxMoService csWxMoService = $.GetSpringBean("csWxMoService");
		return csWxMoService.getCsWxMoEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsWxMo(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsWxMoService csWxMoService = $.GetSpringBean("csWxMoService");
		csWxMoService.updateCsWxMoByConfirm(set, where);
	}
	
	
	/**
	 * 保存微信上行对象
	 * @param params
	 * @return
	 */
	public CsWxMo save(){
		com.ccclubs.service.admin.ICsWxMoService csWxMoService = $.GetSpringBean("csWxMoService");
		if(this.getCswmId()!=null)
			csWxMoService.updateCsWxMo(this);
		else
			return csWxMoService.saveCsWxMo(this);
		return this;
	}
	
	
	/**
	 * 更新微信上行对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsWxMoService csWxMoService = $.GetSpringBean("csWxMoService");
		csWxMoService.updateCsWxMo$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsWxMoService csWxMoService = $.GetSpringBean("csWxMoService");
		if($.equals($.config("logic_delete"),"true"))
			csWxMoService.removeCsWxMoById(this.getCswmId());
		else
			csWxMoService.deleteCsWxMoById(this.getCswmId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsWxMoService csWxMoService = $.GetSpringBean("csWxMoService");
		return csWxMoService.executeTransaction(function);
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
	* 平台类型 [非空]   1:微信 2:支付宝    
	**/
	public Short getCswmPlatform(){
		return this.cswmPlatform;
	}
	/**
	* 获取平台类型格式化(toString)
	**/
	public String getCswmPlatform$(){
		String strValue="";
		 if($.equals($.str(this.getCswmPlatform()),"1"))
			strValue=$.str("微信");		 
		 if($.equals($.str(this.getCswmPlatform()),"2"))
			strValue=$.str("支付宝");		 
	 	 return strValue;
	}
	/**
	* 平台类型 [非空]   1:微信 2:支付宝    
	**/
	public void setCswmPlatform(Short cswmPlatform){
		this.cswmPlatform = cswmPlatform;
		this.setSeted(F.cswmPlatform);
	}
	/*******************************三方标识**********************************/	
	/**
	* 三方标识 [非空]      
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
	* 三方标识 [非空]      
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
	/*******************************消息类型**********************************/	
	/**
	* 消息类型 [非空]   0:普通消息 1:事件消息    
	**/
	public Short getCswmMsgType(){
		return this.cswmMsgType;
	}
	/**
	* 获取消息类型格式化(toString)
	**/
	public String getCswmMsgType$(){
		String strValue="";
		 if($.equals($.str(this.getCswmMsgType()),"0"))
			strValue=$.str("普通消息");		 
		 if($.equals($.str(this.getCswmMsgType()),"1"))
			strValue=$.str("事件消息");		 
	 	 return strValue;
	}
	/**
	* 消息类型 [非空]   0:普通消息 1:事件消息    
	**/
	public void setCswmMsgType(Short cswmMsgType){
		this.cswmMsgType = cswmMsgType;
		this.setSeted(F.cswmMsgType);
	}
	/*******************************内容类型**********************************/	
	/**
	* 内容类型 [非空]   0:文本 1:图片 2:图文 3:其它    
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
			strValue=$.str("其它");		 
	 	 return strValue;
	}
	/**
	* 内容类型 [非空]   0:文本 1:图片 2:图文 3:其它    
	**/
	public void setCswmContentType(Short cswmContentType){
		this.cswmContentType = cswmContentType;
		this.setSeted(F.cswmContentType);
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
	/*******************************程序掩码**********************************/	
	/**
	* 程序掩码    0:默认    
	**/
	public Integer getCswmMask(){
		return this.cswmMask;
	}
	/**
	* 获取程序掩码格式化(toString)
	**/
	public String getCswmMask$(){
		String strValue="";
		if(this.getCswmMask()!=null){
			if((this.getCswmMask() & 0 ) == 0)
				strValue+="["+$.str("默认")+"]";		 
		}
	 	 return strValue;
	}
	/**
	* 程序掩码    0:默认    
	**/
	public void setCswmMask(Integer cswmMask){
		this.cswmMask = cswmMask;
		this.setSeted(F.cswmMask);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCswmStatus(){
		return this.cswmStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCswmStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCswmStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCswmStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCswmStatus(Short cswmStatus){
		this.cswmStatus = cswmStatus;
		this.setSeted(F.cswmStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsWxMo.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsWxMo.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsWxMo.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsWxMo.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsWxMo.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsWxMo.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsWxMo.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsWxMo.this);
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
		/** 平台类型 [非空]   1:微信 2:支付宝     **/
		public M cswmPlatform(Object cswmPlatform){this.put("cswmPlatform", cswmPlatform);return this;};
	 	/** and cswm_platform is null */
 		public M cswmPlatformNull(){if(this.get("cswmPlatformNot")==null)this.put("cswmPlatformNot", "");this.put("cswmPlatform", null);return this;};
 		/** not .... */
 		public M cswmPlatformNot(){this.put("cswmPlatformNot", "not");return this;};
		/** 三方标识 [非空]       **/
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
		/** 消息类型 [非空]   0:普通消息 1:事件消息     **/
		public M cswmMsgType(Object cswmMsgType){this.put("cswmMsgType", cswmMsgType);return this;};
	 	/** and cswm_msg_type is null */
 		public M cswmMsgTypeNull(){if(this.get("cswmMsgTypeNot")==null)this.put("cswmMsgTypeNot", "");this.put("cswmMsgType", null);return this;};
 		/** not .... */
 		public M cswmMsgTypeNot(){this.put("cswmMsgTypeNot", "not");return this;};
		/** 内容类型 [非空]   0:文本 1:图片 2:图文 3:其它     **/
		public M cswmContentType(Object cswmContentType){this.put("cswmContentType", cswmContentType);return this;};
	 	/** and cswm_content_type is null */
 		public M cswmContentTypeNull(){if(this.get("cswmContentTypeNot")==null)this.put("cswmContentTypeNot", "");this.put("cswmContentType", null);return this;};
 		/** not .... */
 		public M cswmContentTypeNot(){this.put("cswmContentTypeNot", "not");return this;};
		/** 消息内容     直接保存xml或json   **/
		public M cswmContent(Object cswmContent){this.put("cswmContent", cswmContent);return this;};
	 	/** and cswm_content is null */
 		public M cswmContentNull(){if(this.get("cswmContentNot")==null)this.put("cswmContentNot", "");this.put("cswmContent", null);return this;};
 		/** not .... */
 		public M cswmContentNot(){this.put("cswmContentNot", "not");return this;};
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
		/** 程序掩码    0:默认     **/
		public M cswmMask(Object cswmMask){this.put("cswmMask", cswmMask);return this;};
	 	/** and cswm_mask is null */
 		public M cswmMaskNull(){if(this.get("cswmMaskNot")==null)this.put("cswmMaskNot", "");this.put("cswmMask", null);return this;};
 		/** not .... */
 		public M cswmMaskNot(){this.put("cswmMaskNot", "not");return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M cswmStatus(Object cswmStatus){this.put("cswmStatus", cswmStatus);return this;};
	 	/** and cswm_status is null */
 		public M cswmStatusNull(){if(this.get("cswmStatusNot")==null)this.put("cswmStatusNot", "");this.put("cswmStatus", null);return this;};
 		/** not .... */
 		public M cswmStatusNot(){this.put("cswmStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有微信上行 **/
		public @api List<CsWxMo> list(Integer size){
			return getCsWxMoList(this,size);
		}
		/** 获取微信上行分页 **/
		public @api Page<CsWxMo> page(int page,int size){
			return getCsWxMoPage(page, size , this);
		}
		/** 根据查询条件取微信上行 **/
		public @api CsWxMo get(){
			return getCsWxMo(this);
		}
		/** 获取微信上行数 **/
		public @api Long count(){
			return getCsWxMoCount(this);
		}
		/** 获取微信上行表达式 **/
		public @api <T> T eval(String strEval){
			return getCsWxMoEval(strEval,this);
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
			updateCsWxMo(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cswmId="cswmId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cswmHost="cswmHost";
		/** 平台类型 [非空]   1:微信 2:支付宝     **/
		public final static @type(Short.class)  String cswmPlatform="cswmPlatform";
		/** 三方标识 [非空]       **/
		public final static @type(String.class) @like String cswmOpenid="cswmOpenid";
		/** 关联会员  [CsMember]      **/
		public final static @type(Long.class)  String cswmMember="cswmMember";
		/** 消息类型 [非空]   0:普通消息 1:事件消息     **/
		public final static @type(Short.class)  String cswmMsgType="cswmMsgType";
		/** 内容类型 [非空]   0:文本 1:图片 2:图文 3:其它     **/
		public final static @type(Short.class)  String cswmContentType="cswmContentType";
		/** 消息内容     直接保存xml或json   **/
		public final static @type(String.class) @like String cswmContent="cswmContent";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cswmAddTime="cswmAddTime";
	 	/** and cswm_add_time >= ? */
 		public final static @type(Date.class) String cswmAddTimeStart="cswmAddTimeStart";
 		/** and cswm_add_time <= ? */
 		public final static @type(Date.class) String cswmAddTimeEnd="cswmAddTimeEnd";
		/** 程序掩码    0:默认     **/
		public final static @type(Integer.class)  String cswmMask="cswmMask";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String cswmStatus="cswmStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cswmId="cswm_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cswmHost="cswm_host";
		/** 平台类型 [非空]   1:微信 2:支付宝     **/
		public final static String cswmPlatform="cswm_platform";
		/** 三方标识 [非空]       **/
		public final static String cswmOpenid="cswm_openid";
		/** 关联会员  [CsMember]      **/
		public final static String cswmMember="cswm_member";
		/** 消息类型 [非空]   0:普通消息 1:事件消息     **/
		public final static String cswmMsgType="cswm_msg_type";
		/** 内容类型 [非空]   0:文本 1:图片 2:图文 3:其它     **/
		public final static String cswmContentType="cswm_content_type";
		/** 消息内容     直接保存xml或json   **/
		public final static String cswmContent="cswm_content";
		/** 添加时间 [非空]       **/
		public final static String cswmAddTime="cswm_add_time";
		/** 程序掩码    0:默认     **/
		public final static String cswmMask="cswm_mask";
		/** 状态 [非空]   1:正常 0:无效     **/
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
				$.Set(name,CsWxMo.getCsWxMo(params));
			else
				$.Set(name,CsWxMo.getCsWxMoList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取微信上行数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsWxMo) $.GetRequest("CsWxMo$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsWxMo.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsWxMo.getCsWxMo(params);
			else
				value = CsWxMo.getCsWxMoList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsWxMo.Get($.add(CsWxMo.F.cswmId,param));
		else if(!$.empty(param.toString()))
			value = CsWxMo.get(Long.valueOf(param.toString()));
		$.SetRequest("CsWxMo$"+param.hashCode(), value);
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
	public void mergeSet(CsWxMo old){
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