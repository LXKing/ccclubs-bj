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

@namespace("user/message")
public @caption("会员消息") @table("cs_message") class CsMessage implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csm_id")   @primary  @note("  ") Long csmId;// 主键 非空     
	private @caption("标题") @column("csm_title")    @note("  ") String csmTitle;// 非空     
	private @caption("消息内容") @column("csm_content")    @note("  ") String csmContent;// 非空     
	private @caption("关联对象") @column("csm_relate")    @note("  ") Long csmRelate;//     
	private @caption("关联类型") @column("csm_object")    @note("  ") String csmObject;//     
	private @caption("附件") @column("csm_attach")    @note("  ") String csmAttach;//     
	private @caption("接收人") @column("csm_receiver")    @relate("$csmReceiver") @RelateClass(CsMember.class)  @note("  ") Long csmReceiver;// 非空    ${basePath}admin/user/member_inquire.do 
 	private CsMember $csmReceiver;//关联对象[会员帐号]
	private @caption("发送人") @column("csm_sender")    @relate("$csmSender") @RelateClass(CsMember.class)  @note("  ") Long csmSender;// 非空    ${basePath}admin/user/member_inquire.do 
 	private CsMember $csmSender;//关联对象[会员帐号]
	private @caption("接收时间") @column("csm_rcv_time")    @note("  ") Date csmRcvTime;// 非空     
	private @caption("添加时间") @column("csm_snd_time")    @note("  ") Date csmSndTime;// 非空     
	private @caption("接收状态") @column("csm_rcv_status")    @note(" 0:未读 1:已读 2:已删  ") Short csmRcvStatus;// 非空 0:未读 1:已读 2:已删     
	private @caption("发送状态") @column("csm_snd_status")    @note("  ") Short csmSndStatus;// 非空     
	private @caption("系统状态") @column("csm_status")    @note(" 1:正常 0:无效  ") Short csmStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsMessage(){
	
	}
	
	//主键构造函数
	public CsMessage(Long id){
		this.csmId = id;
	}
	
	/**所有字段构造函数 CsMessage(csmTitle,csmContent,csmRelate,csmObject,csmAttach,csmReceiver,csmSender,csmRcvTime,csmSndTime,csmRcvStatus,csmSndStatus,csmStatus)
	 CsMessage(
	 	$.getString("csmTitle")//标题 [非空]
	 	,$.getString("csmContent")//消息内容 [非空]
	 	,$.getLong("csmRelate")//关联对象
	 	,$.getString("csmObject")//关联类型
	 	,$.getString("csmAttach")//附件
	 	,$.getLong("csmReceiver")//接收人 [非空]
	 	,$.getLong("csmSender")//发送人 [非空]
	 	,$.getDate("csmRcvTime")//接收时间 [非空]
	 	,$.getDate("csmSndTime")//添加时间 [非空]
	 	,$.getShort("csmRcvStatus")//接收状态 [非空]
	 	,$.getShort("csmSndStatus")//发送状态 [非空]
	 	,$.getShort("csmStatus")//系统状态 [非空]
	 )
	**/
	public CsMessage(String csmTitle,String csmContent,Long csmRelate,String csmObject,String csmAttach,Long csmReceiver,Long csmSender,Date csmRcvTime,Date csmSndTime,Short csmRcvStatus,Short csmSndStatus,Short csmStatus){
		this.csmTitle=csmTitle;
		this.csmContent=csmContent;
		this.csmRelate=csmRelate;
		this.csmObject=csmObject;
		this.csmAttach=csmAttach;
		this.csmReceiver=csmReceiver;
		this.csmSender=csmSender;
		this.csmRcvTime=csmRcvTime;
		this.csmSndTime=csmSndTime;
		this.csmRcvStatus=csmRcvStatus;
		this.csmSndStatus=csmSndStatus;
		this.csmStatus=csmStatus;
	}
	
	//设置非空字段
	public CsMessage setNotNull(String csmTitle,String csmContent,Long csmReceiver,Long csmSender,Date csmRcvTime,Date csmSndTime,Short csmRcvStatus,Short csmSndStatus,Short csmStatus){
		this.csmTitle=csmTitle;
		this.csmContent=csmContent;
		this.csmReceiver=csmReceiver;
		this.csmSender=csmSender;
		this.csmRcvTime=csmRcvTime;
		this.csmSndTime=csmSndTime;
		this.csmRcvStatus=csmRcvStatus;
		this.csmSndStatus=csmSndStatus;
		this.csmStatus=csmStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsMessage csmId(Long csmId){
		this.csmId = csmId;
		this.setSeted(F.csmId);
		return this;
	}
	/** 标题 [非空]       **/
	public CsMessage csmTitle(String csmTitle){
		this.csmTitle = csmTitle;
		this.setSeted(F.csmTitle);
		return this;
	}
	/** 消息内容 [非空]       **/
	public CsMessage csmContent(String csmContent){
		this.csmContent = csmContent;
		this.setSeted(F.csmContent);
		return this;
	}
	/** 关联对象        **/
	public CsMessage csmRelate(Long csmRelate){
		this.csmRelate = csmRelate;
		this.setSeted(F.csmRelate);
		return this;
	}
	/** 关联类型        **/
	public CsMessage csmObject(String csmObject){
		this.csmObject = csmObject;
		this.setSeted(F.csmObject);
		return this;
	}
	/** 附件        **/
	public CsMessage csmAttach(String csmAttach){
		this.csmAttach = csmAttach;
		this.setSeted(F.csmAttach);
		return this;
	}
	/** 接收人 [非空] [CsMember]     ${basePath}admin/user/member_inquire.do **/
	public CsMessage csmReceiver(Long csmReceiver){
		this.csmReceiver = csmReceiver;
		this.setSeted(F.csmReceiver);
		return this;
	}
	/** 发送人 [非空] [CsMember]     ${basePath}admin/user/member_inquire.do **/
	public CsMessage csmSender(Long csmSender){
		this.csmSender = csmSender;
		this.setSeted(F.csmSender);
		return this;
	}
	/** 接收时间 [非空]       **/
	public CsMessage csmRcvTime(Date csmRcvTime){
		this.csmRcvTime = csmRcvTime;
		this.setSeted(F.csmRcvTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsMessage csmSndTime(Date csmSndTime){
		this.csmSndTime = csmSndTime;
		this.setSeted(F.csmSndTime);
		return this;
	}
	/** 接收状态 [非空]   0:未读 1:已读 2:已删     **/
	public CsMessage csmRcvStatus(Short csmRcvStatus){
		this.csmRcvStatus = csmRcvStatus;
		this.setSeted(F.csmRcvStatus);
		return this;
	}
	/** 发送状态 [非空]       **/
	public CsMessage csmSndStatus(Short csmSndStatus){
		this.csmSndStatus = csmSndStatus;
		this.setSeted(F.csmSndStatus);
		return this;
	}
	/** 系统状态 [非空]   1:正常 0:无效     **/
	public CsMessage csmStatus(Short csmStatus){
		this.csmStatus = csmStatus;
		this.setSeted(F.csmStatus);
		return this;
	}
	
	
	//克隆对象
	public CsMessage clone(){
		CsMessage clone = new CsMessage();
		clone.csmTitle=this.csmTitle;
		clone.csmContent=this.csmContent;
		clone.csmReceiver=this.csmReceiver;
		clone.csmSender=this.csmSender;
		clone.csmRcvTime=this.csmRcvTime;
		clone.csmSndTime=this.csmSndTime;
		clone.csmRcvStatus=this.csmRcvStatus;
		clone.csmSndStatus=this.csmSndStatus;
		clone.csmStatus=this.csmStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取会员消息
	 * @param id
	 * @return
	 */
	public static @api CsMessage get(Long id){		
		return getCsMessageById(id);
	}
	/**
	 * 获取所有会员消息
	 * @return
	 */
	public static @api List<CsMessage> list(Map params,Integer size){
		return getCsMessageList(params,size);
	}
	/**
	 * 获取会员消息分页
	 * @return
	 */
	public static @api Page<CsMessage> page(int page,int size,Map params){
		return getCsMessagePage(page, size , params);
	}
	/**
	 * 根据查询条件取会员消息
	 * @param params
	 * @return
	 */
	public static @api CsMessage Get(Map params){
		return getCsMessage(params);
	}
	/**
	 * 获取会员消息数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsMessageCount(params);
	}
	/**
	 * 获取会员消息数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsMessageEval(eval,params);
	}
	
	/**
	 * 根据ID取会员消息
	 * @param id
	 * @return
	 */
	public static @api CsMessage getCsMessageById(Long id){		
		CsMessage csMessage = (CsMessage) $.GetRequest("CsMessage$"+id);
		if(csMessage!=null)
			return csMessage;
		com.ccclubs.service.admin.ICsMessageService csMessageService = $.GetSpringBean("csMessageService");		
		return csMessageService.getCsMessageById(id);
	}
	
	
	/**
	 * 根据ID取会员消息的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsMessage.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsMessage csMessage = get(id);
		if(csMessage!=null){
			String strValue = csMessage.getCsmTitle$();
			if(!"CsmTitle".equals("CsmTitle"))
				strValue+="("+csMessage.getCsmTitle$()+")";
			MemCache.setValue(CsMessage.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsmTitle$();
		if(!"CsmTitle".equals("CsmTitle"))
			keyValue+="("+this.getCsmTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有会员消息
	 * @return
	 */
	public static @api List<CsMessage> getCsMessageList(Map params,Integer size){
		com.ccclubs.service.admin.ICsMessageService csMessageService = $.GetSpringBean("csMessageService");
		return csMessageService.getCsMessageList(params, size);
	}
	
	/**
	 * 获取会员消息分页
	 * @return
	 */
	public static @api Page<CsMessage> getCsMessagePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsMessageService csMessageService = $.GetSpringBean("csMessageService");
		return csMessageService.getCsMessagePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取会员消息
	 * @param params
	 * @return
	 */
	public static @api CsMessage getCsMessage(Map params){
		com.ccclubs.service.admin.ICsMessageService csMessageService = $.GetSpringBean("csMessageService");
		return csMessageService.getCsMessage(params);
	}
	
	/**
	 * 获取会员消息数
	 * @return
	 */
	public static @api Long getCsMessageCount(Map params){
		com.ccclubs.service.admin.ICsMessageService csMessageService = $.GetSpringBean("csMessageService");
		return csMessageService.getCsMessageCount(params);
	}
		
		
	/**
	 * 获取会员消息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsMessageEval(String eval,Map params){
		com.ccclubs.service.admin.ICsMessageService csMessageService = $.GetSpringBean("csMessageService");
		return csMessageService.getCsMessageEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsMessage(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsMessageService csMessageService = $.GetSpringBean("csMessageService");
		csMessageService.updateCsMessageByConfirm(set, where);
	}
	
	
	/**
	 * 保存会员消息对象
	 * @param params
	 * @return
	 */
	public CsMessage save(){
		com.ccclubs.service.admin.ICsMessageService csMessageService = $.GetSpringBean("csMessageService");
		if(this.getCsmId()!=null)
			csMessageService.updateCsMessage(this);
		else
			return csMessageService.saveCsMessage(this);
		return this;
	}
	
	
	/**
	 * 更新会员消息对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsMessageService csMessageService = $.GetSpringBean("csMessageService");
		csMessageService.updateCsMessage$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsMessageService csMessageService = $.GetSpringBean("csMessageService");
		if($.equals($.config("logic_delete"),"true"))
			csMessageService.removeCsMessageById(this.getCsmId());
		else
			csMessageService.deleteCsMessageById(this.getCsmId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsMessageService csMessageService = $.GetSpringBean("csMessageService");
		return csMessageService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsmId(){
		return this.csmId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsmId$(){
		String strValue="";
		 strValue=$.str(this.getCsmId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsmId(Long csmId){
		this.csmId = csmId;
		this.setSeted(F.csmId);
	}
	/*******************************标题**********************************/	
	/**
	* 标题 [非空]      
	**/
	public String getCsmTitle(){
		return this.csmTitle;
	}
	/**
	* 获取标题格式化(toString)
	**/
	public String getCsmTitle$(){
		String strValue="";
		 strValue=$.str(this.getCsmTitle());
	 	 return strValue;
	}
	/**
	* 标题 [非空]      
	**/
	public void setCsmTitle(String csmTitle){
		this.csmTitle = csmTitle;
		this.setSeted(F.csmTitle);
	}
	/*******************************消息内容**********************************/	
	/**
	* 消息内容 [非空]      
	**/
	public String getCsmContent(){
		return this.csmContent;
	}
	/**
	* 获取消息内容格式化(toString)
	**/
	public String getCsmContent$(){
		String strValue="";
		 strValue=$.str(this.getCsmContent());
	 	 return strValue;
	}
	/**
	* 消息内容 [非空]      
	**/
	public void setCsmContent(String csmContent){
		this.csmContent = csmContent;
		this.setSeted(F.csmContent);
	}
	/*******************************关联对象**********************************/	
	/**
	* 关联对象       
	**/
	public Long getCsmRelate(){
		return this.csmRelate;
	}
	/**
	* 获取关联对象格式化(toString)
	**/
	public String getCsmRelate$(){
		String strValue="";
		 strValue=$.str(this.getCsmRelate());
	 	 return strValue;
	}
	/**
	* 关联对象       
	**/
	public void setCsmRelate(Long csmRelate){
		this.csmRelate = csmRelate;
		this.setSeted(F.csmRelate);
	}
	/*******************************关联类型**********************************/	
	/**
	* 关联类型       
	**/
	public String getCsmObject(){
		return this.csmObject;
	}
	/**
	* 获取关联类型格式化(toString)
	**/
	public String getCsmObject$(){
		String strValue="";
		 strValue=$.str(this.getCsmObject());
	 	 return strValue;
	}
	/**
	* 关联类型       
	**/
	public void setCsmObject(String csmObject){
		this.csmObject = csmObject;
		this.setSeted(F.csmObject);
	}
	/*******************************附件**********************************/	
	/**
	* 附件       
	**/
	public String getCsmAttach(){
		return this.csmAttach;
	}
	/**
	* 获取附件格式化(toString)
	**/
	public String getCsmAttach$(){
		String strValue="";
		 strValue=$.str(this.getCsmAttach());
	 	 return strValue;
	}
	/**
	* 附件       
	**/
	public void setCsmAttach(String csmAttach){
		this.csmAttach = csmAttach;
		this.setSeted(F.csmAttach);
	}
	/*******************************接收人**********************************/	
	/**
	* 接收人 [非空] [CsMember]     ${basePath}admin/user/member_inquire.do
	**/
	public Long getCsmReceiver(){
		return this.csmReceiver;
	}
	/**
	* 获取接收人格式化(toString)
	**/
	public String getCsmReceiver$(){
		String strValue="";
		if(this.getCsmReceiver()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsmReceiver()));
		}			
	 	 return strValue;
	}
	/**
	* 接收人 [非空] [CsMember]     ${basePath}admin/user/member_inquire.do
	**/
	public void setCsmReceiver(Long csmReceiver){
		this.csmReceiver = csmReceiver;
		this.setSeted(F.csmReceiver);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csmReceiver(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsmReceiver());
 		if(csMember!=null)
			return csMember;
		if(this.getCsmReceiver()!=null){
 			csMember = CsMember.get(this.getCsmReceiver());
 		}
 		$.SetRequest("CsMember$"+this.getCsmReceiver(), csMember);
	 	return csMember;
	}
	/*******************************发送人**********************************/	
	/**
	* 发送人 [非空] [CsMember]     ${basePath}admin/user/member_inquire.do
	**/
	public Long getCsmSender(){
		return this.csmSender;
	}
	/**
	* 获取发送人格式化(toString)
	**/
	public String getCsmSender$(){
		String strValue="";
		if(this.getCsmSender()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsmSender()));
		}			
	 	 return strValue;
	}
	/**
	* 发送人 [非空] [CsMember]     ${basePath}admin/user/member_inquire.do
	**/
	public void setCsmSender(Long csmSender){
		this.csmSender = csmSender;
		this.setSeted(F.csmSender);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csmSender(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsmSender());
 		if(csMember!=null)
			return csMember;
		if(this.getCsmSender()!=null){
 			csMember = CsMember.get(this.getCsmSender());
 		}
 		$.SetRequest("CsMember$"+this.getCsmSender(), csMember);
	 	return csMember;
	}
	/*******************************接收时间**********************************/	
	/**
	* 接收时间 [非空]      
	**/
	public Date getCsmRcvTime(){
		return this.csmRcvTime;
	}
	/**
	* 获取接收时间格式化(toString)
	**/
	public String getCsmRcvTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmRcvTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 接收时间 [非空]      
	**/
	public void setCsmRcvTime(Date csmRcvTime){
		this.csmRcvTime = csmRcvTime;
		this.setSeted(F.csmRcvTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsmSndTime(){
		return this.csmSndTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsmSndTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmSndTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsmSndTime(Date csmSndTime){
		this.csmSndTime = csmSndTime;
		this.setSeted(F.csmSndTime);
	}
	/*******************************接收状态**********************************/	
	/**
	* 接收状态 [非空]   0:未读 1:已读 2:已删    
	**/
	public Short getCsmRcvStatus(){
		return this.csmRcvStatus;
	}
	/**
	* 获取接收状态格式化(toString)
	**/
	public String getCsmRcvStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsmRcvStatus()),"0"))
			strValue=$.str("未读");		 
		 if($.equals($.str(this.getCsmRcvStatus()),"1"))
			strValue=$.str("已读");		 
		 if($.equals($.str(this.getCsmRcvStatus()),"2"))
			strValue=$.str("已删");		 
	 	 return strValue;
	}
	/**
	* 接收状态 [非空]   0:未读 1:已读 2:已删    
	**/
	public void setCsmRcvStatus(Short csmRcvStatus){
		this.csmRcvStatus = csmRcvStatus;
		this.setSeted(F.csmRcvStatus);
	}
	/*******************************发送状态**********************************/	
	/**
	* 发送状态 [非空]      
	**/
	public Short getCsmSndStatus(){
		return this.csmSndStatus;
	}
	/**
	* 获取发送状态格式化(toString)
	**/
	public String getCsmSndStatus$(){
		String strValue="";
		 strValue=$.str(this.getCsmSndStatus());
	 	 return strValue;
	}
	/**
	* 发送状态 [非空]      
	**/
	public void setCsmSndStatus(Short csmSndStatus){
		this.csmSndStatus = csmSndStatus;
		this.setSeted(F.csmSndStatus);
	}
	/*******************************系统状态**********************************/	
	/**
	* 系统状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsmStatus(){
		return this.csmStatus;
	}
	/**
	* 获取系统状态格式化(toString)
	**/
	public String getCsmStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsmStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsmStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 系统状态 [非空]   1:正常 0:无效    
	**/
	public void setCsmStatus(Short csmStatus){
		this.csmStatus = csmStatus;
		this.setSeted(F.csmStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsMessage.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMessage.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMessage.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsMessage.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsMessage.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMessage.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMessage.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsMessage.this);
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
		public M csmId(Object csmId){this.put("csmId", csmId);return this;};
	 	/** and csm_id is null */
 		public M csmIdNull(){if(this.get("csmIdNot")==null)this.put("csmIdNot", "");this.put("csmId", null);return this;};
 		/** not .... */
 		public M csmIdNot(){this.put("csmIdNot", "not");return this;};
		/** 标题 [非空]       **/
		public M csmTitle(Object csmTitle){this.put("csmTitle", csmTitle);return this;};
	 	/** and csm_title is null */
 		public M csmTitleNull(){if(this.get("csmTitleNot")==null)this.put("csmTitleNot", "");this.put("csmTitle", null);return this;};
 		/** not .... */
 		public M csmTitleNot(){this.put("csmTitleNot", "not");return this;};
		/** 消息内容 [非空]       **/
		public M csmContent(Object csmContent){this.put("csmContent", csmContent);return this;};
	 	/** and csm_content is null */
 		public M csmContentNull(){if(this.get("csmContentNot")==null)this.put("csmContentNot", "");this.put("csmContent", null);return this;};
 		/** not .... */
 		public M csmContentNot(){this.put("csmContentNot", "not");return this;};
		/** 关联对象        **/
		public M csmRelate(Object csmRelate){this.put("csmRelate", csmRelate);return this;};
	 	/** and csm_relate is null */
 		public M csmRelateNull(){if(this.get("csmRelateNot")==null)this.put("csmRelateNot", "");this.put("csmRelate", null);return this;};
 		/** not .... */
 		public M csmRelateNot(){this.put("csmRelateNot", "not");return this;};
		/** and csm_relate >= ? */
		public M csmRelateMin(Object min){this.put("csmRelateMin", min);return this;};
		/** and csm_relate <= ? */
		public M csmRelateMax(Object max){this.put("csmRelateMax", max);return this;};
		/** 关联类型        **/
		public M csmObject(Object csmObject){this.put("csmObject", csmObject);return this;};
	 	/** and csm_object is null */
 		public M csmObjectNull(){if(this.get("csmObjectNot")==null)this.put("csmObjectNot", "");this.put("csmObject", null);return this;};
 		/** not .... */
 		public M csmObjectNot(){this.put("csmObjectNot", "not");return this;};
		/** 附件        **/
		public M csmAttach(Object csmAttach){this.put("csmAttach", csmAttach);return this;};
	 	/** and csm_attach is null */
 		public M csmAttachNull(){if(this.get("csmAttachNot")==null)this.put("csmAttachNot", "");this.put("csmAttach", null);return this;};
 		/** not .... */
 		public M csmAttachNot(){this.put("csmAttachNot", "not");return this;};
		/** 接收人 [非空] [CsMember]     ${basePath}admin/user/member_inquire.do **/
		public M csmReceiver(Object csmReceiver){this.put("csmReceiver", csmReceiver);return this;};
	 	/** and csm_receiver is null */
 		public M csmReceiverNull(){if(this.get("csmReceiverNot")==null)this.put("csmReceiverNot", "");this.put("csmReceiver", null);return this;};
 		/** not .... */
 		public M csmReceiverNot(){this.put("csmReceiverNot", "not");return this;};
		public M csmReceiver$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csmReceiver$on", value);
			return this;
		};	
		/** 发送人 [非空] [CsMember]     ${basePath}admin/user/member_inquire.do **/
		public M csmSender(Object csmSender){this.put("csmSender", csmSender);return this;};
	 	/** and csm_sender is null */
 		public M csmSenderNull(){if(this.get("csmSenderNot")==null)this.put("csmSenderNot", "");this.put("csmSender", null);return this;};
 		/** not .... */
 		public M csmSenderNot(){this.put("csmSenderNot", "not");return this;};
		public M csmSender$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csmSender$on", value);
			return this;
		};	
		/** 接收时间 [非空]       **/
		public M csmRcvTime(Object csmRcvTime){this.put("csmRcvTime", csmRcvTime);return this;};
	 	/** and csm_rcv_time is null */
 		public M csmRcvTimeNull(){if(this.get("csmRcvTimeNot")==null)this.put("csmRcvTimeNot", "");this.put("csmRcvTime", null);return this;};
 		/** not .... */
 		public M csmRcvTimeNot(){this.put("csmRcvTimeNot", "not");return this;};
 		/** and csm_rcv_time >= ? */
 		public M csmRcvTimeStart(Object start){this.put("csmRcvTimeStart", start);return this;};			
 		/** and csm_rcv_time <= ? */
 		public M csmRcvTimeEnd(Object end){this.put("csmRcvTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csmSndTime(Object csmSndTime){this.put("csmSndTime", csmSndTime);return this;};
	 	/** and csm_snd_time is null */
 		public M csmSndTimeNull(){if(this.get("csmSndTimeNot")==null)this.put("csmSndTimeNot", "");this.put("csmSndTime", null);return this;};
 		/** not .... */
 		public M csmSndTimeNot(){this.put("csmSndTimeNot", "not");return this;};
 		/** and csm_snd_time >= ? */
 		public M csmSndTimeStart(Object start){this.put("csmSndTimeStart", start);return this;};			
 		/** and csm_snd_time <= ? */
 		public M csmSndTimeEnd(Object end){this.put("csmSndTimeEnd", end);return this;};
		/** 接收状态 [非空]   0:未读 1:已读 2:已删     **/
		public M csmRcvStatus(Object csmRcvStatus){this.put("csmRcvStatus", csmRcvStatus);return this;};
	 	/** and csm_rcv_status is null */
 		public M csmRcvStatusNull(){if(this.get("csmRcvStatusNot")==null)this.put("csmRcvStatusNot", "");this.put("csmRcvStatus", null);return this;};
 		/** not .... */
 		public M csmRcvStatusNot(){this.put("csmRcvStatusNot", "not");return this;};
		/** 发送状态 [非空]       **/
		public M csmSndStatus(Object csmSndStatus){this.put("csmSndStatus", csmSndStatus);return this;};
	 	/** and csm_snd_status is null */
 		public M csmSndStatusNull(){if(this.get("csmSndStatusNot")==null)this.put("csmSndStatusNot", "");this.put("csmSndStatus", null);return this;};
 		/** not .... */
 		public M csmSndStatusNot(){this.put("csmSndStatusNot", "not");return this;};
		/** 系统状态 [非空]   1:正常 0:无效     **/
		public M csmStatus(Object csmStatus){this.put("csmStatus", csmStatus);return this;};
	 	/** and csm_status is null */
 		public M csmStatusNull(){if(this.get("csmStatusNot")==null)this.put("csmStatusNot", "");this.put("csmStatus", null);return this;};
 		/** not .... */
 		public M csmStatusNot(){this.put("csmStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有会员消息 **/
		public @api List<CsMessage> list(Integer size){
			return getCsMessageList(this,size);
		}
		/** 获取会员消息分页 **/
		public @api Page<CsMessage> page(int page,int size){
			return getCsMessagePage(page, size , this);
		}
		/** 根据查询条件取会员消息 **/
		public @api CsMessage get(){
			return getCsMessage(this);
		}
		/** 获取会员消息数 **/
		public @api Long count(){
			return getCsMessageCount(this);
		}
		/** 获取会员消息表达式 **/
		public @api <T> T eval(String strEval){
			return getCsMessageEval(strEval,this);
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
			updateCsMessage(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csmId="csmId";
		/** 标题 [非空]       **/
		public final static @type(String.class) @like String csmTitle="csmTitle";
		/** 消息内容 [非空]       **/
		public final static @type(String.class) @like String csmContent="csmContent";
		/** 关联对象        **/
		public final static @type(Long.class)  String csmRelate="csmRelate";
		/** and csm_relate >= ? */
		public final static @type(Long.class) String csmRelateMin="csmRelateMin";
		/** and csm_relate <= ? */
		public final static @type(Long.class) String csmRelateMax="csmRelateMax";
		/** 关联类型        **/
		public final static @type(String.class) @like String csmObject="csmObject";
		/** 附件        **/
		public final static @type(String.class)  String csmAttach="csmAttach";
		/** 接收人 [非空] [CsMember]     ${basePath}admin/user/member_inquire.do **/
		public final static @type(Long.class)  String csmReceiver="csmReceiver";
		/** 发送人 [非空] [CsMember]     ${basePath}admin/user/member_inquire.do **/
		public final static @type(Long.class)  String csmSender="csmSender";
		/** 接收时间 [非空]       **/
		public final static @type(Date.class)  String csmRcvTime="csmRcvTime";
	 	/** and csm_rcv_time >= ? */
 		public final static @type(Date.class) String csmRcvTimeStart="csmRcvTimeStart";
 		/** and csm_rcv_time <= ? */
 		public final static @type(Date.class) String csmRcvTimeEnd="csmRcvTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csmSndTime="csmSndTime";
	 	/** and csm_snd_time >= ? */
 		public final static @type(Date.class) String csmSndTimeStart="csmSndTimeStart";
 		/** and csm_snd_time <= ? */
 		public final static @type(Date.class) String csmSndTimeEnd="csmSndTimeEnd";
		/** 接收状态 [非空]   0:未读 1:已读 2:已删     **/
		public final static @type(Short.class)  String csmRcvStatus="csmRcvStatus";
		/** 发送状态 [非空]       **/
		public final static @type(Short.class)  String csmSndStatus="csmSndStatus";
		/** 系统状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csmStatus="csmStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csmId="csm_id";
		/** 标题 [非空]       **/
		public final static String csmTitle="csm_title";
		/** 消息内容 [非空]       **/
		public final static String csmContent="csm_content";
		/** 关联对象        **/
		public final static String csmRelate="csm_relate";
		/** 关联类型        **/
		public final static String csmObject="csm_object";
		/** 附件        **/
		public final static String csmAttach="csm_attach";
		/** 接收人 [非空] [CsMember]     ${basePath}admin/user/member_inquire.do **/
		public final static String csmReceiver="csm_receiver";
		/** 发送人 [非空] [CsMember]     ${basePath}admin/user/member_inquire.do **/
		public final static String csmSender="csm_sender";
		/** 接收时间 [非空]       **/
		public final static String csmRcvTime="csm_rcv_time";
		/** 添加时间 [非空]       **/
		public final static String csmSndTime="csm_snd_time";
		/** 接收状态 [非空]   0:未读 1:已读 2:已删     **/
		public final static String csmRcvStatus="csm_rcv_status";
		/** 发送状态 [非空]       **/
		public final static String csmSndStatus="csm_snd_status";
		/** 系统状态 [非空]   1:正常 0:无效     **/
		public final static String csmStatus="csm_status";
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
				$.Set(name,CsMessage.getCsMessage(params));
			else
				$.Set(name,CsMessage.getCsMessageList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取会员消息数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsMessage) $.GetRequest("CsMessage$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsMessage.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsMessage.getCsMessage(params);
			else
				value = CsMessage.getCsMessageList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsMessage.Get($.add(CsMessage.F.csmId,param));
		else if(!$.empty(param.toString()))
			value = CsMessage.get(Long.valueOf(param.toString()));
		$.SetRequest("CsMessage$"+param.hashCode(), value);
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
	public void mergeSet(CsMessage old){
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