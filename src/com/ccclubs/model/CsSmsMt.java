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

@namespace("systematic/smsmt")
public @caption("短信发送") @table("cs_sms_mt") class CsSmsMt implements java.io.Serializable
{
	private static final long serialVersionUID =          1l;
	private @caption("编号") @column("cssm_id")   @primary  @note("  ") Long cssmId;// 主键 非空     
	private @caption("城市") @column("cssm_host")    @relate("$cssmHost") @RelateClass(SrvHost.class)  @note("  ") Long cssmHost;// 非空     
 	private SrvHost $cssmHost;//关联对象[运营城市]
	private @caption("短信类型") @column("cssm_type")    @note(" 1:代码类短信 2:通知类短信 3:提示类短信 4:广告类短信  <strong style='color:blue;'>《《 慎重 》》选择“代码类短信”！！</strong>") Short cssmType;// 非空 1:代码类短信 2:通知类短信 3:提示类短信 4:广告类短信  <strong style='color:blue;'>《《 慎重 》》选择“代码类短信”！！</strong>   
	private @caption("接收号码") @column("cssm_mobile")    @note("  ") String cssmMobile;// 非空     
	private @caption("短信内容") @column("cssm_content")    @note("  ") String cssmContent;// 非空     
	private @caption("操作人") @column("cssm_editor")    @relate("$cssmEditor") @RelateClass(SrvUser.class)  @note("  ") Long cssmEditor;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cssmEditor;//关联对象[用户]
	private @caption("发送通道") @column("cssm_channel")    @relate("$cssmChannel") @RelateClass(SrvProperty.class)  @note(" 短信通道:'SMS_CHANNEL'  ") Long cssmChannel;// 短信通道:'SMS_CHANNEL'    select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) 
 	private SrvProperty $cssmChannel;//关联对象[系统属性]
	private @caption("修改时间") @column("cssm_update_time")    @note("  ") Date cssmUpdateTime;// 非空     
	private @caption("添加时间") @column("cssm_add_time")    @note("  ") Date cssmAddTime;// 非空     
	private @caption("备注") @column("cssm_remark")    @note("  ") String cssmRemark;//     
	private @caption("状态") @column("cssm_status")    @note(" 1:待发送 2:已发送 3:发送失败 0:停止发送  ") Short cssmStatus;// 非空 1:待发送 2:已发送 3:发送失败 0:停止发送     
	
	//默认构造函数
	public CsSmsMt(){
	
	}
	
	//主键构造函数
	public CsSmsMt(Long id){
		this.cssmId = id;
	}
	
	/**所有字段构造函数 CsSmsMt(cssmHost,cssmType,cssmMobile,cssmContent,cssmEditor,cssmChannel,cssmUpdateTime,cssmAddTime,cssmRemark,cssmStatus)
	 CsSmsMt(
	 	$.getLong("cssmHost")//城市 [非空]
	 	,$.getShort("cssmType")//短信类型 [非空]
	 	,$.getString("cssmMobile")//接收号码 [非空]
	 	,$.getString("cssmContent")//短信内容 [非空]
	 	,$.getLong("cssmEditor")//操作人
	 	,$.getLong("cssmChannel")//发送通道
	 	,$.getDate("cssmUpdateTime")//修改时间 [非空]
	 	,$.getDate("cssmAddTime")//添加时间 [非空]
	 	,$.getString("cssmRemark")//备注
	 	,$.getShort("cssmStatus")//状态 [非空]
	 )
	**/
	public CsSmsMt(Long cssmHost,Short cssmType,String cssmMobile,String cssmContent,Long cssmEditor,Long cssmChannel,Date cssmUpdateTime,Date cssmAddTime,String cssmRemark,Short cssmStatus){
		this.cssmHost=cssmHost;
		this.cssmType=cssmType;
		this.cssmMobile=cssmMobile;
		this.cssmContent=cssmContent;
		this.cssmEditor=cssmEditor;
		this.cssmChannel=cssmChannel;
		this.cssmUpdateTime=cssmUpdateTime;
		this.cssmAddTime=cssmAddTime;
		this.cssmRemark=cssmRemark;
		this.cssmStatus=cssmStatus;
	}
	
	//设置非空字段
	public CsSmsMt setNotNull(Long cssmHost,Short cssmType,String cssmMobile,String cssmContent,Date cssmUpdateTime,Date cssmAddTime,Short cssmStatus){
		this.cssmHost=cssmHost;
		this.cssmType=cssmType;
		this.cssmMobile=cssmMobile;
		this.cssmContent=cssmContent;
		this.cssmUpdateTime=cssmUpdateTime;
		this.cssmAddTime=cssmAddTime;
		this.cssmStatus=cssmStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsSmsMt cssmId(Long cssmId){
		this.cssmId = cssmId;
		this.setSeted(F.cssmId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsSmsMt cssmHost(Long cssmHost){
		this.cssmHost = cssmHost;
		this.setSeted(F.cssmHost);
		return this;
	}
	/** 短信类型 [非空]   1:代码类短信 2:通知类短信 3:提示类短信 4:广告类短信  <strong style='color:blue;'>《《 慎重 》》选择“代码类短信”！！</strong>   **/
	public CsSmsMt cssmType(Short cssmType){
		this.cssmType = cssmType;
		this.setSeted(F.cssmType);
		return this;
	}
	/** 接收号码 [非空]       **/
	public CsSmsMt cssmMobile(String cssmMobile){
		this.cssmMobile = cssmMobile;
		this.setSeted(F.cssmMobile);
		return this;
	}
	/** 短信内容 [非空]       **/
	public CsSmsMt cssmContent(String cssmContent){
		this.cssmContent = cssmContent;
		this.setSeted(F.cssmContent);
		return this;
	}
	/** 操作人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsSmsMt cssmEditor(Long cssmEditor){
		this.cssmEditor = cssmEditor;
		this.setSeted(F.cssmEditor);
		return this;
	}
	/** 发送通道  [SrvProperty]  短信通道:'SMS_CHANNEL'    select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
	public CsSmsMt cssmChannel(Long cssmChannel){
		this.cssmChannel = cssmChannel;
		this.setSeted(F.cssmChannel);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsSmsMt cssmUpdateTime(Date cssmUpdateTime){
		this.cssmUpdateTime = cssmUpdateTime;
		this.setSeted(F.cssmUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsSmsMt cssmAddTime(Date cssmAddTime){
		this.cssmAddTime = cssmAddTime;
		this.setSeted(F.cssmAddTime);
		return this;
	}
	/** 备注        **/
	public CsSmsMt cssmRemark(String cssmRemark){
		this.cssmRemark = cssmRemark;
		this.setSeted(F.cssmRemark);
		return this;
	}
	/** 状态 [非空]   1:待发送 2:已发送 3:发送失败 0:停止发送     **/
	public CsSmsMt cssmStatus(Short cssmStatus){
		this.cssmStatus = cssmStatus;
		this.setSeted(F.cssmStatus);
		return this;
	}
	
	
	//克隆对象
	public CsSmsMt clone(){
		CsSmsMt clone = new CsSmsMt();
		clone.cssmHost=this.cssmHost;
		clone.cssmType=this.cssmType;
		clone.cssmMobile=this.cssmMobile;
		clone.cssmContent=this.cssmContent;
		clone.cssmUpdateTime=this.cssmUpdateTime;
		clone.cssmAddTime=this.cssmAddTime;
		clone.cssmStatus=this.cssmStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取短信发送
	 * @param id
	 * @return
	 */
	public static @api CsSmsMt get(Long id){		
		return getCsSmsMtById(id);
	}
	/**
	 * 获取所有短信发送
	 * @return
	 */
	public static @api List<CsSmsMt> list(Map params,Integer size){
		return getCsSmsMtList(params,size);
	}
	/**
	 * 获取短信发送分页
	 * @return
	 */
	public static @api Page<CsSmsMt> page(int page,int size,Map params){
		return getCsSmsMtPage(page, size , params);
	}
	/**
	 * 根据查询条件取短信发送
	 * @param params
	 * @return
	 */
	public static @api CsSmsMt Get(Map params){
		return getCsSmsMt(params);
	}
	/**
	 * 获取短信发送数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsSmsMtCount(params);
	}
	/**
	 * 获取短信发送数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsSmsMtEval(eval,params);
	}
	
	/**
	 * 根据ID取短信发送
	 * @param id
	 * @return
	 */
	public static @api CsSmsMt getCsSmsMtById(Long id){		
		CsSmsMt csSmsMt = (CsSmsMt) $.GetRequest("CsSmsMt$"+id);
		if(csSmsMt!=null)
			return csSmsMt;
		com.ccclubs.service.admin.ICsSmsMtService csSmsMtService = $.GetSpringBean("csSmsMtService");		
		return csSmsMtService.getCsSmsMtById(id);
	}
	
	
	/**
	 * 根据ID取短信发送的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		//当前对象不支持键值缓存
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCssmMobile$();
		if(!"CssmMobile".equals("CssmMobile"))
			keyValue+="("+this.getCssmMobile$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有短信发送
	 * @return
	 */
	public static @api List<CsSmsMt> getCsSmsMtList(Map params,Integer size){
		com.ccclubs.service.admin.ICsSmsMtService csSmsMtService = $.GetSpringBean("csSmsMtService");
		return csSmsMtService.getCsSmsMtList(params, size);
	}
	
	/**
	 * 获取短信发送分页
	 * @return
	 */
	public static @api Page<CsSmsMt> getCsSmsMtPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsSmsMtService csSmsMtService = $.GetSpringBean("csSmsMtService");
		return csSmsMtService.getCsSmsMtPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取短信发送
	 * @param params
	 * @return
	 */
	public static @api CsSmsMt getCsSmsMt(Map params){
		com.ccclubs.service.admin.ICsSmsMtService csSmsMtService = $.GetSpringBean("csSmsMtService");
		return csSmsMtService.getCsSmsMt(params);
	}
	
	/**
	 * 获取短信发送数
	 * @return
	 */
	public static @api Long getCsSmsMtCount(Map params){
		com.ccclubs.service.admin.ICsSmsMtService csSmsMtService = $.GetSpringBean("csSmsMtService");
		return csSmsMtService.getCsSmsMtCount(params);
	}
		
		
	/**
	 * 获取短信发送自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsSmsMtEval(String eval,Map params){
		com.ccclubs.service.admin.ICsSmsMtService csSmsMtService = $.GetSpringBean("csSmsMtService");
		return csSmsMtService.getCsSmsMtEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsSmsMt(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsSmsMtService csSmsMtService = $.GetSpringBean("csSmsMtService");
		csSmsMtService.updateCsSmsMtByConfirm(set, where);
	}
	
	
	/**
	 * 保存短信发送对象
	 * @param params
	 * @return
	 */
	public CsSmsMt save(){
		com.ccclubs.service.admin.ICsSmsMtService csSmsMtService = $.GetSpringBean("csSmsMtService");
		if(this.getCssmId()!=null)
			csSmsMtService.updateCsSmsMt(this);
		else
			return csSmsMtService.saveCsSmsMt(this);
		return this;
	}
	
	
	/**
	 * 更新短信发送对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsSmsMtService csSmsMtService = $.GetSpringBean("csSmsMtService");
		csSmsMtService.updateCsSmsMt$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsSmsMtService csSmsMtService = $.GetSpringBean("csSmsMtService");
		if($.equals($.config("logic_delete"),"true"))
			csSmsMtService.removeCsSmsMtById(this.getCssmId());
		else
			csSmsMtService.deleteCsSmsMtById(this.getCssmId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsSmsMtService csSmsMtService = $.GetSpringBean("csSmsMtService");
		return csSmsMtService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCssmId(){
		return this.cssmId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCssmId$(){
		String strValue="";
		 strValue=$.str(this.getCssmId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCssmId(Long cssmId){
		this.cssmId = cssmId;
		this.setSeted(F.cssmId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCssmHost(){
		return this.cssmHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCssmHost$(){
		String strValue="";
		if(this.getCssmHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCssmHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCssmHost(Long cssmHost){
		this.cssmHost = cssmHost;
		this.setSeted(F.cssmHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cssmHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCssmHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCssmHost()!=null){
 			srvHost = SrvHost.get(this.getCssmHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCssmHost(), srvHost);
	 	return srvHost;
	}
	/*******************************短信类型**********************************/	
	/**
	* 短信类型 [非空]   1:代码类短信 2:通知类短信 3:提示类短信 4:广告类短信  <strong style='color:blue;'>《《 慎重 》》选择“代码类短信”！！</strong>  
	**/
	public Short getCssmType(){
		return this.cssmType;
	}
	/**
	* 获取短信类型格式化(toString)
	**/
	public String getCssmType$(){
		String strValue="";
		 if($.equals($.str(this.getCssmType()),"1"))
			strValue=$.str("代码类短信");		 
		 if($.equals($.str(this.getCssmType()),"2"))
			strValue=$.str("通知类短信");		 
		 if($.equals($.str(this.getCssmType()),"3"))
			strValue=$.str("提示类短信");		 
		 if($.equals($.str(this.getCssmType()),"4"))
			strValue=$.str("广告类短信");		 
	 	 return strValue;
	}
	/**
	* 短信类型 [非空]   1:代码类短信 2:通知类短信 3:提示类短信 4:广告类短信  <strong style='color:blue;'>《《 慎重 》》选择“代码类短信”！！</strong>  
	**/
	public void setCssmType(Short cssmType){
		this.cssmType = cssmType;
		this.setSeted(F.cssmType);
	}
	/*******************************接收号码**********************************/	
	/**
	* 接收号码 [非空]      
	**/
	public String getCssmMobile(){
		return this.cssmMobile;
	}
	/**
	* 获取接收号码格式化(toString)
	**/
	public String getCssmMobile$(){
		String strValue="";
		 strValue=$.str(this.getCssmMobile());
	 	 return strValue;
	}
	/**
	* 接收号码 [非空]      
	**/
	public void setCssmMobile(String cssmMobile){
		this.cssmMobile = cssmMobile;
		this.setSeted(F.cssmMobile);
	}
	/*******************************短信内容**********************************/	
	/**
	* 短信内容 [非空]      
	**/
	public String getCssmContent(){
		return this.cssmContent;
	}
	/**
	* 获取短信内容格式化(toString)
	**/
	public String getCssmContent$(){
		String strValue="";
		 strValue=$.str(this.getCssmContent());
	 	 return strValue;
	}
	/**
	* 短信内容 [非空]      
	**/
	public void setCssmContent(String cssmContent){
		this.cssmContent = cssmContent;
		this.setSeted(F.cssmContent);
	}
	/*******************************操作人**********************************/	
	/**
	* 操作人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCssmEditor(){
		return this.cssmEditor;
	}
	/**
	* 获取操作人格式化(toString)
	**/
	public String getCssmEditor$(){
		String strValue="";
		if(this.getCssmEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCssmEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 操作人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCssmEditor(Long cssmEditor){
		this.cssmEditor = cssmEditor;
		this.setSeted(F.cssmEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cssmEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCssmEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCssmEditor()!=null){
 			srvUser = SrvUser.get(this.getCssmEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCssmEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************发送通道**********************************/	
	/**
	* 发送通道  [SrvProperty]  短信通道:'SMS_CHANNEL'    select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)
	**/
	public Long getCssmChannel(){
		return this.cssmChannel;
	}
	/**
	* 获取发送通道格式化(toString)
	**/
	public String getCssmChannel$(){
		String strValue="";
		if(this.getCssmChannel()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCssmChannel()));
		}			
	 	 return strValue;
	}
	/**
	* 发送通道  [SrvProperty]  短信通道:'SMS_CHANNEL'    select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)
	**/
	public void setCssmChannel(Long cssmChannel){
		this.cssmChannel = cssmChannel;
		this.setSeted(F.cssmChannel);
	}
	/**
	* 获取关联对象[系统属性]
	**/	 			
 	public SrvProperty get$cssmChannel(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCssmChannel());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCssmChannel()!=null){
 			srvProperty = SrvProperty.get(this.getCssmChannel());
 		}
 		$.SetRequest("SrvProperty$"+this.getCssmChannel(), srvProperty);
	 	return srvProperty;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCssmUpdateTime(){
		return this.cssmUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCssmUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssmUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCssmUpdateTime(Date cssmUpdateTime){
		this.cssmUpdateTime = cssmUpdateTime;
		this.setSeted(F.cssmUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCssmAddTime(){
		return this.cssmAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCssmAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssmAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCssmAddTime(Date cssmAddTime){
		this.cssmAddTime = cssmAddTime;
		this.setSeted(F.cssmAddTime);
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getCssmRemark(){
		return this.cssmRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCssmRemark$(){
		String strValue="";
		 strValue=$.str(this.getCssmRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setCssmRemark(String cssmRemark){
		this.cssmRemark = cssmRemark;
		this.setSeted(F.cssmRemark);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:待发送 2:已发送 3:发送失败 0:停止发送    
	**/
	public Short getCssmStatus(){
		return this.cssmStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCssmStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCssmStatus()),"1"))
			strValue=$.str("待发送");		 
		 if($.equals($.str(this.getCssmStatus()),"2"))
			strValue=$.str("已发送");		 
		 if($.equals($.str(this.getCssmStatus()),"3"))
			strValue=$.str("发送失败");		 
		 if($.equals($.str(this.getCssmStatus()),"0"))
			strValue=$.str("停止发送");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:待发送 2:已发送 3:发送失败 0:停止发送    
	**/
	public void setCssmStatus(Short cssmStatus){
		this.cssmStatus = cssmStatus;
		this.setSeted(F.cssmStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsSmsMt.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsSmsMt.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsSmsMt.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsSmsMt.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsSmsMt.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsSmsMt.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsSmsMt.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsSmsMt.this);
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
		public M cssmId(Object cssmId){this.put("cssmId", cssmId);return this;};
	 	/** and cssm_id is null */
 		public M cssmIdNull(){if(this.get("cssmIdNot")==null)this.put("cssmIdNot", "");this.put("cssmId", null);return this;};
 		/** not .... */
 		public M cssmIdNot(){this.put("cssmIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M cssmHost(Object cssmHost){this.put("cssmHost", cssmHost);return this;};
	 	/** and cssm_host is null */
 		public M cssmHostNull(){if(this.get("cssmHostNot")==null)this.put("cssmHostNot", "");this.put("cssmHost", null);return this;};
 		/** not .... */
 		public M cssmHostNot(){this.put("cssmHostNot", "not");return this;};
		/** 短信类型 [非空]   1:代码类短信 2:通知类短信 3:提示类短信 4:广告类短信  <strong style='color:blue;'>《《 慎重 》》选择“代码类短信”！！</strong>   **/
		public M cssmType(Object cssmType){this.put("cssmType", cssmType);return this;};
	 	/** and cssm_type is null */
 		public M cssmTypeNull(){if(this.get("cssmTypeNot")==null)this.put("cssmTypeNot", "");this.put("cssmType", null);return this;};
 		/** not .... */
 		public M cssmTypeNot(){this.put("cssmTypeNot", "not");return this;};
		/** 接收号码 [非空]       **/
		public M cssmMobile(Object cssmMobile){this.put("cssmMobile", cssmMobile);return this;};
	 	/** and cssm_mobile is null */
 		public M cssmMobileNull(){if(this.get("cssmMobileNot")==null)this.put("cssmMobileNot", "");this.put("cssmMobile", null);return this;};
 		/** not .... */
 		public M cssmMobileNot(){this.put("cssmMobileNot", "not");return this;};
		/** 短信内容 [非空]       **/
		public M cssmContent(Object cssmContent){this.put("cssmContent", cssmContent);return this;};
	 	/** and cssm_content is null */
 		public M cssmContentNull(){if(this.get("cssmContentNot")==null)this.put("cssmContentNot", "");this.put("cssmContent", null);return this;};
 		/** not .... */
 		public M cssmContentNot(){this.put("cssmContentNot", "not");return this;};
		/** 操作人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cssmEditor(Object cssmEditor){this.put("cssmEditor", cssmEditor);return this;};
	 	/** and cssm_editor is null */
 		public M cssmEditorNull(){if(this.get("cssmEditorNot")==null)this.put("cssmEditorNot", "");this.put("cssmEditor", null);return this;};
 		/** not .... */
 		public M cssmEditorNot(){this.put("cssmEditorNot", "not");return this;};
		/** 发送通道  [SrvProperty]  短信通道:'SMS_CHANNEL'    select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
		public M cssmChannel(Object cssmChannel){this.put("cssmChannel", cssmChannel);return this;};
	 	/** and cssm_channel is null */
 		public M cssmChannelNull(){if(this.get("cssmChannelNot")==null)this.put("cssmChannelNot", "");this.put("cssmChannel", null);return this;};
 		/** not .... */
 		public M cssmChannelNot(){this.put("cssmChannelNot", "not");return this;};
		public M cssmChannel$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("cssmChannel$on", value);
			return this;
		};	
		/** 修改时间 [非空]       **/
		public M cssmUpdateTime(Object cssmUpdateTime){this.put("cssmUpdateTime", cssmUpdateTime);return this;};
	 	/** and cssm_update_time is null */
 		public M cssmUpdateTimeNull(){if(this.get("cssmUpdateTimeNot")==null)this.put("cssmUpdateTimeNot", "");this.put("cssmUpdateTime", null);return this;};
 		/** not .... */
 		public M cssmUpdateTimeNot(){this.put("cssmUpdateTimeNot", "not");return this;};
 		/** and cssm_update_time >= ? */
 		public M cssmUpdateTimeStart(Object start){this.put("cssmUpdateTimeStart", start);return this;};			
 		/** and cssm_update_time <= ? */
 		public M cssmUpdateTimeEnd(Object end){this.put("cssmUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cssmAddTime(Object cssmAddTime){this.put("cssmAddTime", cssmAddTime);return this;};
	 	/** and cssm_add_time is null */
 		public M cssmAddTimeNull(){if(this.get("cssmAddTimeNot")==null)this.put("cssmAddTimeNot", "");this.put("cssmAddTime", null);return this;};
 		/** not .... */
 		public M cssmAddTimeNot(){this.put("cssmAddTimeNot", "not");return this;};
 		/** and cssm_add_time >= ? */
 		public M cssmAddTimeStart(Object start){this.put("cssmAddTimeStart", start);return this;};			
 		/** and cssm_add_time <= ? */
 		public M cssmAddTimeEnd(Object end){this.put("cssmAddTimeEnd", end);return this;};
		/** 备注        **/
		public M cssmRemark(Object cssmRemark){this.put("cssmRemark", cssmRemark);return this;};
	 	/** and cssm_remark is null */
 		public M cssmRemarkNull(){if(this.get("cssmRemarkNot")==null)this.put("cssmRemarkNot", "");this.put("cssmRemark", null);return this;};
 		/** not .... */
 		public M cssmRemarkNot(){this.put("cssmRemarkNot", "not");return this;};
		/** 状态 [非空]   1:待发送 2:已发送 3:发送失败 0:停止发送     **/
		public M cssmStatus(Object cssmStatus){this.put("cssmStatus", cssmStatus);return this;};
	 	/** and cssm_status is null */
 		public M cssmStatusNull(){if(this.get("cssmStatusNot")==null)this.put("cssmStatusNot", "");this.put("cssmStatus", null);return this;};
 		/** not .... */
 		public M cssmStatusNot(){this.put("cssmStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有短信发送 **/
		public @api List<CsSmsMt> list(Integer size){
			return getCsSmsMtList(this,size);
		}
		/** 获取短信发送分页 **/
		public @api Page<CsSmsMt> page(int page,int size){
			return getCsSmsMtPage(page, size , this);
		}
		/** 根据查询条件取短信发送 **/
		public @api CsSmsMt get(){
			return getCsSmsMt(this);
		}
		/** 获取短信发送数 **/
		public @api Long count(){
			return getCsSmsMtCount(this);
		}
		/** 获取短信发送表达式 **/
		public @api <T> T eval(String strEval){
			return getCsSmsMtEval(strEval,this);
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
			updateCsSmsMt(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cssmId="cssmId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cssmHost="cssmHost";
		/** 短信类型 [非空]   1:代码类短信 2:通知类短信 3:提示类短信 4:广告类短信  <strong style='color:blue;'>《《 慎重 》》选择“代码类短信”！！</strong>   **/
		public final static @type(Short.class)  String cssmType="cssmType";
		/** 接收号码 [非空]       **/
		public final static @type(String.class) @like String cssmMobile="cssmMobile";
		/** 短信内容 [非空]       **/
		public final static @type(String.class) @like String cssmContent="cssmContent";
		/** 操作人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String cssmEditor="cssmEditor";
		/** 发送通道  [SrvProperty]  短信通道:'SMS_CHANNEL'    select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
		public final static @type(Long.class)  String cssmChannel="cssmChannel";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cssmUpdateTime="cssmUpdateTime";
	 	/** and cssm_update_time >= ? */
 		public final static @type(Date.class) String cssmUpdateTimeStart="cssmUpdateTimeStart";
 		/** and cssm_update_time <= ? */
 		public final static @type(Date.class) String cssmUpdateTimeEnd="cssmUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cssmAddTime="cssmAddTime";
	 	/** and cssm_add_time >= ? */
 		public final static @type(Date.class) String cssmAddTimeStart="cssmAddTimeStart";
 		/** and cssm_add_time <= ? */
 		public final static @type(Date.class) String cssmAddTimeEnd="cssmAddTimeEnd";
		/** 备注        **/
		public final static @type(String.class) @like String cssmRemark="cssmRemark";
		/** 状态 [非空]   1:待发送 2:已发送 3:发送失败 0:停止发送     **/
		public final static @type(Short.class)  String cssmStatus="cssmStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cssmId="cssm_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cssmHost="cssm_host";
		/** 短信类型 [非空]   1:代码类短信 2:通知类短信 3:提示类短信 4:广告类短信  <strong style='color:blue;'>《《 慎重 》》选择“代码类短信”！！</strong>   **/
		public final static String cssmType="cssm_type";
		/** 接收号码 [非空]       **/
		public final static String cssmMobile="cssm_mobile";
		/** 短信内容 [非空]       **/
		public final static String cssmContent="cssm_content";
		/** 操作人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cssmEditor="cssm_editor";
		/** 发送通道  [SrvProperty]  短信通道:'SMS_CHANNEL'    select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
		public final static String cssmChannel="cssm_channel";
		/** 修改时间 [非空]       **/
		public final static String cssmUpdateTime="cssm_update_time";
		/** 添加时间 [非空]       **/
		public final static String cssmAddTime="cssm_add_time";
		/** 备注        **/
		public final static String cssmRemark="cssm_remark";
		/** 状态 [非空]   1:待发送 2:已发送 3:发送失败 0:停止发送     **/
		public final static String cssmStatus="cssm_status";
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
				$.Set(name,CsSmsMt.getCsSmsMt(params));
			else
				$.Set(name,CsSmsMt.getCsSmsMtList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取短信发送数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsSmsMt) $.GetRequest("CsSmsMt$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsSmsMt.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsSmsMt.getCsSmsMt(params);
			else
				value = CsSmsMt.getCsSmsMtList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsSmsMt.Get($.add(CsSmsMt.F.cssmId,param));
		else if(!$.empty(param.toString()))
			value = CsSmsMt.get(Long.valueOf(param.toString()));
		$.SetRequest("CsSmsMt$"+param.hashCode(), value);
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
	public void mergeSet(CsSmsMt old){
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