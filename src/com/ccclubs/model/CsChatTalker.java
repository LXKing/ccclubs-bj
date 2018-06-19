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

@namespace("csm/talker")
public @caption("在线访客") @table("cs_chat_talker") class CsChatTalker implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csct_id")   @primary  @note("  ") Long csctId;// 主键 非空     
	private @caption("城市") @column("csct_host")    @relate("$csctHost") @RelateClass(SrvHost.class)  @note("  ") Long csctHost;// 非空     
 	private SrvHost $csctHost;//关联对象[运营城市]
	private @caption("名称姓名") @column("csct_name")    @note("  客户姓名") String csctName;// 非空  客户姓名   
	private @caption("使用头像") @column("csct_header")    @note("  ") String csctHeader;//     
	private @caption("个性签名") @column("csct_sign")    @note("  ") String csctSign;//     
	private @caption("关联会员") @column("csct_member")    @relate("$csctMember") @RelateClass(CsMember.class)  @note("  此项有值为会员") Long csctMember;//  此项有值为会员   
 	private CsMember $csctMember;//关联对象[会员帐号]
	private @caption("会话标识") @column("csct_flag")    @note("  clientid或openid") String csctFlag;//  clientid或openid   
	private @caption("来源平台") @column("csct_platform")    @note(" 0:后台 1:网站 2:微站 3:微信 4:支付宝 5:短信 6:QQ  ") Short csctPlatform;// 非空 0:后台 1:网站 2:微站 3:微信 4:支付宝 5:短信 6:QQ     
	private @caption("上线次数") @column("csct_count")    @note("  ") Integer csctCount;// 非空     
	private @caption("IP地址") @column("csct_last_ip")    @note("  ") String csctLastIp;//     
	private @caption("当前客服") @column("csct_servicer")    @relate("$csctServicer") @RelateClass(SrvUser.class)  @note("  ") Long csctServicer;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csctServicer;//关联对象[用户]
	private @caption("最近时间") @column("csct_last_time")    @note("  ") Date csctLastTime;//     
	private @caption("添加时间") @column("csct_add_time")    @note("  ") Date csctAddTime;// 非空     
	private @caption("备注信息") @column("csct_remark")    @note("  ") String csctRemark;//     
	private @caption("客服状态") @column("csct_state")    @note(" 0:排队中 1:沟通中 2:已结束  ") Short csctState;// 非空 0:排队中 1:沟通中 2:已结束     
	private @caption("当前状态") @column("csct_status")    @note(" 1:在线 0:不在线  ") Short csctStatus;// 非空 1:在线 0:不在线     
	
	//默认构造函数
	public CsChatTalker(){
	
	}
	
	//主键构造函数
	public CsChatTalker(Long id){
		this.csctId = id;
	}
	
	/**所有字段构造函数 CsChatTalker(csctHost,csctName,csctHeader,csctSign,csctMember,csctFlag,csctPlatform,csctCount,csctLastIp,csctServicer,csctLastTime,csctAddTime,csctRemark,csctState,csctStatus)
	 CsChatTalker(
	 	$.getLong("csctHost")//城市 [非空]
	 	,$.getString("csctName")//名称姓名 [非空]
	 	,$.getString("csctHeader")//使用头像
	 	,$.getString("csctSign")//个性签名
	 	,$.getLong("csctMember")//关联会员
	 	,$.getString("csctFlag")//会话标识
	 	,$.getShort("csctPlatform")//来源平台 [非空]
	 	,$.getInteger("csctCount")//上线次数 [非空]
	 	,$.getString("csctLastIp")//IP地址
	 	,$.getLong("csctServicer")//当前客服
	 	,$.getDate("csctLastTime")//最近时间
	 	,$.getDate("csctAddTime")//添加时间 [非空]
	 	,$.getString("csctRemark")//备注信息
	 	,$.getShort("csctState")//客服状态 [非空]
	 	,$.getShort("csctStatus")//当前状态 [非空]
	 )
	**/
	public CsChatTalker(Long csctHost,String csctName,String csctHeader,String csctSign,Long csctMember,String csctFlag,Short csctPlatform,Integer csctCount,String csctLastIp,Long csctServicer,Date csctLastTime,Date csctAddTime,String csctRemark,Short csctState,Short csctStatus){
		this.csctHost=csctHost;
		this.csctName=csctName;
		this.csctHeader=csctHeader;
		this.csctSign=csctSign;
		this.csctMember=csctMember;
		this.csctFlag=csctFlag;
		this.csctPlatform=csctPlatform;
		this.csctCount=csctCount;
		this.csctLastIp=csctLastIp;
		this.csctServicer=csctServicer;
		this.csctLastTime=csctLastTime;
		this.csctAddTime=csctAddTime;
		this.csctRemark=csctRemark;
		this.csctState=csctState;
		this.csctStatus=csctStatus;
	}
	
	//设置非空字段
	public CsChatTalker setNotNull(Long csctHost,String csctName,Short csctPlatform,Integer csctCount,Date csctAddTime,Short csctState,Short csctStatus){
		this.csctHost=csctHost;
		this.csctName=csctName;
		this.csctPlatform=csctPlatform;
		this.csctCount=csctCount;
		this.csctAddTime=csctAddTime;
		this.csctState=csctState;
		this.csctStatus=csctStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsChatTalker csctId(Long csctId){
		this.csctId = csctId;
		this.setSeted(F.csctId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsChatTalker csctHost(Long csctHost){
		this.csctHost = csctHost;
		this.setSeted(F.csctHost);
		return this;
	}
	/** 名称姓名 [非空]    客户姓名   **/
	public CsChatTalker csctName(String csctName){
		this.csctName = csctName;
		this.setSeted(F.csctName);
		return this;
	}
	/** 使用头像        **/
	public CsChatTalker csctHeader(String csctHeader){
		this.csctHeader = csctHeader;
		this.setSeted(F.csctHeader);
		return this;
	}
	/** 个性签名        **/
	public CsChatTalker csctSign(String csctSign){
		this.csctSign = csctSign;
		this.setSeted(F.csctSign);
		return this;
	}
	/** 关联会员  [CsMember]   此项有值为会员   **/
	public CsChatTalker csctMember(Long csctMember){
		this.csctMember = csctMember;
		this.setSeted(F.csctMember);
		return this;
	}
	/** 会话标识     clientid或openid   **/
	public CsChatTalker csctFlag(String csctFlag){
		this.csctFlag = csctFlag;
		this.setSeted(F.csctFlag);
		return this;
	}
	/** 来源平台 [非空]   0:后台 1:网站 2:微站 3:微信 4:支付宝 5:短信 6:QQ     **/
	public CsChatTalker csctPlatform(Short csctPlatform){
		this.csctPlatform = csctPlatform;
		this.setSeted(F.csctPlatform);
		return this;
	}
	/** 上线次数 [非空]       **/
	public CsChatTalker csctCount(Integer csctCount){
		this.csctCount = csctCount;
		this.setSeted(F.csctCount);
		return this;
	}
	/** IP地址        **/
	public CsChatTalker csctLastIp(String csctLastIp){
		this.csctLastIp = csctLastIp;
		this.setSeted(F.csctLastIp);
		return this;
	}
	/** 当前客服  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsChatTalker csctServicer(Long csctServicer){
		this.csctServicer = csctServicer;
		this.setSeted(F.csctServicer);
		return this;
	}
	/** 最近时间        **/
	public CsChatTalker csctLastTime(Date csctLastTime){
		this.csctLastTime = csctLastTime;
		this.setSeted(F.csctLastTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsChatTalker csctAddTime(Date csctAddTime){
		this.csctAddTime = csctAddTime;
		this.setSeted(F.csctAddTime);
		return this;
	}
	/** 备注信息        **/
	public CsChatTalker csctRemark(String csctRemark){
		this.csctRemark = csctRemark;
		this.setSeted(F.csctRemark);
		return this;
	}
	/** 客服状态 [非空]   0:排队中 1:沟通中 2:已结束     **/
	public CsChatTalker csctState(Short csctState){
		this.csctState = csctState;
		this.setSeted(F.csctState);
		return this;
	}
	/** 当前状态 [非空]   1:在线 0:不在线     **/
	public CsChatTalker csctStatus(Short csctStatus){
		this.csctStatus = csctStatus;
		this.setSeted(F.csctStatus);
		return this;
	}
	
	
	//克隆对象
	public CsChatTalker clone(){
		CsChatTalker clone = new CsChatTalker();
		clone.csctHost=this.csctHost;
		clone.csctName=this.csctName;
		clone.csctPlatform=this.csctPlatform;
		clone.csctCount=this.csctCount;
		clone.csctAddTime=this.csctAddTime;
		clone.csctState=this.csctState;
		clone.csctStatus=this.csctStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取在线访客
	 * @param id
	 * @return
	 */
	public static @api CsChatTalker get(Long id){		
		return getCsChatTalkerById(id);
	}
	/**
	 * 获取所有在线访客
	 * @return
	 */
	public static @api List<CsChatTalker> list(Map params,Integer size){
		return getCsChatTalkerList(params,size);
	}
	/**
	 * 获取在线访客分页
	 * @return
	 */
	public static @api Page<CsChatTalker> page(int page,int size,Map params){
		return getCsChatTalkerPage(page, size , params);
	}
	/**
	 * 根据查询条件取在线访客
	 * @param params
	 * @return
	 */
	public static @api CsChatTalker Get(Map params){
		return getCsChatTalker(params);
	}
	/**
	 * 获取在线访客数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsChatTalkerCount(params);
	}
	/**
	 * 获取在线访客数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsChatTalkerEval(eval,params);
	}
	
	/**
	 * 根据ID取在线访客
	 * @param id
	 * @return
	 */
	public static @api CsChatTalker getCsChatTalkerById(Long id){		
		CsChatTalker csChatTalker = (CsChatTalker) $.GetRequest("CsChatTalker$"+id);
		if(csChatTalker!=null)
			return csChatTalker;
		com.ccclubs.service.admin.ICsChatTalkerService csChatTalkerService = $.GetSpringBean("csChatTalkerService");		
		return csChatTalkerService.getCsChatTalkerById(id);
	}
	
	
	/**
	 * 根据ID取在线访客的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsChatTalker.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsChatTalker csChatTalker = get(id);
		if(csChatTalker!=null){
			String strValue = csChatTalker.getCsctName$();
			if(!"CsctName".equals("CsctName"))
				strValue+="("+csChatTalker.getCsctName$()+")";
			MemCache.setValue(CsChatTalker.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsctName$();
		if(!"CsctName".equals("CsctName"))
			keyValue+="("+this.getCsctName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有在线访客
	 * @return
	 */
	public static @api List<CsChatTalker> getCsChatTalkerList(Map params,Integer size){
		com.ccclubs.service.admin.ICsChatTalkerService csChatTalkerService = $.GetSpringBean("csChatTalkerService");
		return csChatTalkerService.getCsChatTalkerList(params, size);
	}
	
	/**
	 * 获取在线访客分页
	 * @return
	 */
	public static @api Page<CsChatTalker> getCsChatTalkerPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsChatTalkerService csChatTalkerService = $.GetSpringBean("csChatTalkerService");
		return csChatTalkerService.getCsChatTalkerPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取在线访客
	 * @param params
	 * @return
	 */
	public static @api CsChatTalker getCsChatTalker(Map params){
		com.ccclubs.service.admin.ICsChatTalkerService csChatTalkerService = $.GetSpringBean("csChatTalkerService");
		return csChatTalkerService.getCsChatTalker(params);
	}
	
	/**
	 * 获取在线访客数
	 * @return
	 */
	public static @api Long getCsChatTalkerCount(Map params){
		com.ccclubs.service.admin.ICsChatTalkerService csChatTalkerService = $.GetSpringBean("csChatTalkerService");
		return csChatTalkerService.getCsChatTalkerCount(params);
	}
		
		
	/**
	 * 获取在线访客自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsChatTalkerEval(String eval,Map params){
		com.ccclubs.service.admin.ICsChatTalkerService csChatTalkerService = $.GetSpringBean("csChatTalkerService");
		return csChatTalkerService.getCsChatTalkerEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsChatTalker(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsChatTalkerService csChatTalkerService = $.GetSpringBean("csChatTalkerService");
		csChatTalkerService.updateCsChatTalkerByConfirm(set, where);
	}
	
	
	/**
	 * 保存在线访客对象
	 * @param params
	 * @return
	 */
	public CsChatTalker save(){
		com.ccclubs.service.admin.ICsChatTalkerService csChatTalkerService = $.GetSpringBean("csChatTalkerService");
		if(this.getCsctId()!=null)
			csChatTalkerService.updateCsChatTalker(this);
		else
			return csChatTalkerService.saveCsChatTalker(this);
		return this;
	}
	
	
	/**
	 * 更新在线访客对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsChatTalkerService csChatTalkerService = $.GetSpringBean("csChatTalkerService");
		csChatTalkerService.updateCsChatTalker$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsChatTalkerService csChatTalkerService = $.GetSpringBean("csChatTalkerService");
		if($.equals($.config("logic_delete"),"true"))
			csChatTalkerService.removeCsChatTalkerById(this.getCsctId());
		else
			csChatTalkerService.deleteCsChatTalkerById(this.getCsctId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsChatTalkerService csChatTalkerService = $.GetSpringBean("csChatTalkerService");
		return csChatTalkerService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsctId(){
		return this.csctId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsctId$(){
		String strValue="";
		 strValue=$.str(this.getCsctId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsctId(Long csctId){
		this.csctId = csctId;
		this.setSeted(F.csctId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsctHost(){
		return this.csctHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsctHost$(){
		String strValue="";
		if(this.getCsctHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsctHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsctHost(Long csctHost){
		this.csctHost = csctHost;
		this.setSeted(F.csctHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csctHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsctHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsctHost()!=null){
 			srvHost = SrvHost.get(this.getCsctHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsctHost(), srvHost);
	 	return srvHost;
	}
	/*******************************名称姓名**********************************/	
	/**
	* 名称姓名 [非空]    客户姓名  
	**/
	public String getCsctName(){
		return this.csctName;
	}
	/**
	* 获取名称姓名格式化(toString)
	**/
	public String getCsctName$(){
		String strValue="";
		 strValue=$.str(this.getCsctName());
	 	 return strValue;
	}
	/**
	* 名称姓名 [非空]    客户姓名  
	**/
	public void setCsctName(String csctName){
		this.csctName = csctName;
		this.setSeted(F.csctName);
	}
	/*******************************使用头像**********************************/	
	/**
	* 使用头像       
	**/
	public String getCsctHeader(){
		return this.csctHeader;
	}
	/**
	* 获取使用头像格式化(toString)
	**/
	public String getCsctHeader$(){
		String strValue="";
		 strValue=$.str(this.getCsctHeader());
	 	 return strValue;
	}
	/**
	* 使用头像       
	**/
	public void setCsctHeader(String csctHeader){
		this.csctHeader = csctHeader;
		this.setSeted(F.csctHeader);
	}
	/*******************************个性签名**********************************/	
	/**
	* 个性签名       
	**/
	public String getCsctSign(){
		return this.csctSign;
	}
	/**
	* 获取个性签名格式化(toString)
	**/
	public String getCsctSign$(){
		String strValue="";
		 strValue=$.str(this.getCsctSign());
	 	 return strValue;
	}
	/**
	* 个性签名       
	**/
	public void setCsctSign(String csctSign){
		this.csctSign = csctSign;
		this.setSeted(F.csctSign);
	}
	/*******************************关联会员**********************************/	
	/**
	* 关联会员  [CsMember]   此项有值为会员  
	**/
	public Long getCsctMember(){
		return this.csctMember;
	}
	/**
	* 获取关联会员格式化(toString)
	**/
	public String getCsctMember$(){
		String strValue="";
		if(this.getCsctMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsctMember()));
		}			
	 	 return strValue;
	}
	/**
	* 关联会员  [CsMember]   此项有值为会员  
	**/
	public void setCsctMember(Long csctMember){
		this.csctMember = csctMember;
		this.setSeted(F.csctMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csctMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsctMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsctMember()!=null){
 			csMember = CsMember.get(this.getCsctMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsctMember(), csMember);
	 	return csMember;
	}
	/*******************************会话标识**********************************/	
	/**
	* 会话标识     clientid或openid  
	**/
	public String getCsctFlag(){
		return this.csctFlag;
	}
	/**
	* 获取会话标识格式化(toString)
	**/
	public String getCsctFlag$(){
		String strValue="";
		 strValue=$.str(this.getCsctFlag());
	 	 return strValue;
	}
	/**
	* 会话标识     clientid或openid  
	**/
	public void setCsctFlag(String csctFlag){
		this.csctFlag = csctFlag;
		this.setSeted(F.csctFlag);
	}
	/*******************************来源平台**********************************/	
	/**
	* 来源平台 [非空]   0:后台 1:网站 2:微站 3:微信 4:支付宝 5:短信 6:QQ    
	**/
	public Short getCsctPlatform(){
		return this.csctPlatform;
	}
	/**
	* 获取来源平台格式化(toString)
	**/
	public String getCsctPlatform$(){
		String strValue="";
		 if($.equals($.str(this.getCsctPlatform()),"0"))
			strValue=$.str("后台");		 
		 if($.equals($.str(this.getCsctPlatform()),"1"))
			strValue=$.str("网站");		 
		 if($.equals($.str(this.getCsctPlatform()),"2"))
			strValue=$.str("微站");		 
		 if($.equals($.str(this.getCsctPlatform()),"3"))
			strValue=$.str("微信");		 
		 if($.equals($.str(this.getCsctPlatform()),"4"))
			strValue=$.str("支付宝");		 
		 if($.equals($.str(this.getCsctPlatform()),"5"))
			strValue=$.str("短信");		 
		 if($.equals($.str(this.getCsctPlatform()),"6"))
			strValue=$.str("QQ");		 
	 	 return strValue;
	}
	/**
	* 来源平台 [非空]   0:后台 1:网站 2:微站 3:微信 4:支付宝 5:短信 6:QQ    
	**/
	public void setCsctPlatform(Short csctPlatform){
		this.csctPlatform = csctPlatform;
		this.setSeted(F.csctPlatform);
	}
	/*******************************上线次数**********************************/	
	/**
	* 上线次数 [非空]      
	**/
	public Integer getCsctCount(){
		return this.csctCount;
	}
	/**
	* 获取上线次数格式化(toString)
	**/
	public String getCsctCount$(){
		String strValue="";
		 strValue=$.str(this.getCsctCount());
	 	 return strValue;
	}
	/**
	* 上线次数 [非空]      
	**/
	public void setCsctCount(Integer csctCount){
		this.csctCount = csctCount;
		this.setSeted(F.csctCount);
	}
	/*******************************IP地址**********************************/	
	/**
	* IP地址       
	**/
	public String getCsctLastIp(){
		return this.csctLastIp;
	}
	/**
	* 获取IP地址格式化(toString)
	**/
	public String getCsctLastIp$(){
		String strValue="";
		 strValue=$.str(this.getCsctLastIp());
	 	 return strValue;
	}
	/**
	* IP地址       
	**/
	public void setCsctLastIp(String csctLastIp){
		this.csctLastIp = csctLastIp;
		this.setSeted(F.csctLastIp);
	}
	/*******************************当前客服**********************************/	
	/**
	* 当前客服  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsctServicer(){
		return this.csctServicer;
	}
	/**
	* 获取当前客服格式化(toString)
	**/
	public String getCsctServicer$(){
		String strValue="";
		if(this.getCsctServicer()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsctServicer()));
		}			
	 	 return strValue;
	}
	/**
	* 当前客服  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsctServicer(Long csctServicer){
		this.csctServicer = csctServicer;
		this.setSeted(F.csctServicer);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csctServicer(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsctServicer());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsctServicer()!=null){
 			srvUser = SrvUser.get(this.getCsctServicer());
 		}
 		$.SetRequest("SrvUser$"+this.getCsctServicer(), srvUser);
	 	return srvUser;
	}
	/*******************************最近时间**********************************/	
	/**
	* 最近时间       
	**/
	public Date getCsctLastTime(){
		return this.csctLastTime;
	}
	/**
	* 获取最近时间格式化(toString)
	**/
	public String getCsctLastTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsctLastTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 最近时间       
	**/
	public void setCsctLastTime(Date csctLastTime){
		this.csctLastTime = csctLastTime;
		this.setSeted(F.csctLastTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsctAddTime(){
		return this.csctAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsctAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsctAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsctAddTime(Date csctAddTime){
		this.csctAddTime = csctAddTime;
		this.setSeted(F.csctAddTime);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息       
	**/
	public String getCsctRemark(){
		return this.csctRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCsctRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsctRemark());
	 	 return strValue;
	}
	/**
	* 备注信息       
	**/
	public void setCsctRemark(String csctRemark){
		this.csctRemark = csctRemark;
		this.setSeted(F.csctRemark);
	}
	/*******************************客服状态**********************************/	
	/**
	* 客服状态 [非空]   0:排队中 1:沟通中 2:已结束    
	**/
	public Short getCsctState(){
		return this.csctState;
	}
	/**
	* 获取客服状态格式化(toString)
	**/
	public String getCsctState$(){
		String strValue="";
		 if($.equals($.str(this.getCsctState()),"0"))
			strValue=$.str("排队中");		 
		 if($.equals($.str(this.getCsctState()),"1"))
			strValue=$.str("沟通中");		 
		 if($.equals($.str(this.getCsctState()),"2"))
			strValue=$.str("已结束");		 
	 	 return strValue;
	}
	/**
	* 客服状态 [非空]   0:排队中 1:沟通中 2:已结束    
	**/
	public void setCsctState(Short csctState){
		this.csctState = csctState;
		this.setSeted(F.csctState);
	}
	/*******************************当前状态**********************************/	
	/**
	* 当前状态 [非空]   1:在线 0:不在线    
	**/
	public Short getCsctStatus(){
		return this.csctStatus;
	}
	/**
	* 获取当前状态格式化(toString)
	**/
	public String getCsctStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsctStatus()),"1"))
			strValue=$.str("在线");		 
		 if($.equals($.str(this.getCsctStatus()),"0"))
			strValue=$.str("不在线");		 
	 	 return strValue;
	}
	/**
	* 当前状态 [非空]   1:在线 0:不在线    
	**/
	public void setCsctStatus(Short csctStatus){
		this.csctStatus = csctStatus;
		this.setSeted(F.csctStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsChatTalker.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsChatTalker.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsChatTalker.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsChatTalker.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsChatTalker.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsChatTalker.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsChatTalker.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsChatTalker.this);
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
		public M csctId(Object csctId){this.put("csctId", csctId);return this;};
	 	/** and csct_id is null */
 		public M csctIdNull(){if(this.get("csctIdNot")==null)this.put("csctIdNot", "");this.put("csctId", null);return this;};
 		/** not .... */
 		public M csctIdNot(){this.put("csctIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csctHost(Object csctHost){this.put("csctHost", csctHost);return this;};
	 	/** and csct_host is null */
 		public M csctHostNull(){if(this.get("csctHostNot")==null)this.put("csctHostNot", "");this.put("csctHost", null);return this;};
 		/** not .... */
 		public M csctHostNot(){this.put("csctHostNot", "not");return this;};
		/** 名称姓名 [非空]    客户姓名   **/
		public M csctName(Object csctName){this.put("csctName", csctName);return this;};
	 	/** and csct_name is null */
 		public M csctNameNull(){if(this.get("csctNameNot")==null)this.put("csctNameNot", "");this.put("csctName", null);return this;};
 		/** not .... */
 		public M csctNameNot(){this.put("csctNameNot", "not");return this;};
		/** 使用头像        **/
		public M csctHeader(Object csctHeader){this.put("csctHeader", csctHeader);return this;};
	 	/** and csct_header is null */
 		public M csctHeaderNull(){if(this.get("csctHeaderNot")==null)this.put("csctHeaderNot", "");this.put("csctHeader", null);return this;};
 		/** not .... */
 		public M csctHeaderNot(){this.put("csctHeaderNot", "not");return this;};
		/** 个性签名        **/
		public M csctSign(Object csctSign){this.put("csctSign", csctSign);return this;};
	 	/** and csct_sign is null */
 		public M csctSignNull(){if(this.get("csctSignNot")==null)this.put("csctSignNot", "");this.put("csctSign", null);return this;};
 		/** not .... */
 		public M csctSignNot(){this.put("csctSignNot", "not");return this;};
		/** 关联会员  [CsMember]   此项有值为会员   **/
		public M csctMember(Object csctMember){this.put("csctMember", csctMember);return this;};
	 	/** and csct_member is null */
 		public M csctMemberNull(){if(this.get("csctMemberNot")==null)this.put("csctMemberNot", "");this.put("csctMember", null);return this;};
 		/** not .... */
 		public M csctMemberNot(){this.put("csctMemberNot", "not");return this;};
		public M csctMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csctMember$on", value);
			return this;
		};	
		/** 会话标识     clientid或openid   **/
		public M csctFlag(Object csctFlag){this.put("csctFlag", csctFlag);return this;};
	 	/** and csct_flag is null */
 		public M csctFlagNull(){if(this.get("csctFlagNot")==null)this.put("csctFlagNot", "");this.put("csctFlag", null);return this;};
 		/** not .... */
 		public M csctFlagNot(){this.put("csctFlagNot", "not");return this;};
		/** 来源平台 [非空]   0:后台 1:网站 2:微站 3:微信 4:支付宝 5:短信 6:QQ     **/
		public M csctPlatform(Object csctPlatform){this.put("csctPlatform", csctPlatform);return this;};
	 	/** and csct_platform is null */
 		public M csctPlatformNull(){if(this.get("csctPlatformNot")==null)this.put("csctPlatformNot", "");this.put("csctPlatform", null);return this;};
 		/** not .... */
 		public M csctPlatformNot(){this.put("csctPlatformNot", "not");return this;};
		/** 上线次数 [非空]       **/
		public M csctCount(Object csctCount){this.put("csctCount", csctCount);return this;};
	 	/** and csct_count is null */
 		public M csctCountNull(){if(this.get("csctCountNot")==null)this.put("csctCountNot", "");this.put("csctCount", null);return this;};
 		/** not .... */
 		public M csctCountNot(){this.put("csctCountNot", "not");return this;};
		/** and csct_count >= ? */
		public M csctCountMin(Object min){this.put("csctCountMin", min);return this;};
		/** and csct_count <= ? */
		public M csctCountMax(Object max){this.put("csctCountMax", max);return this;};
		/** IP地址        **/
		public M csctLastIp(Object csctLastIp){this.put("csctLastIp", csctLastIp);return this;};
	 	/** and csct_last_ip is null */
 		public M csctLastIpNull(){if(this.get("csctLastIpNot")==null)this.put("csctLastIpNot", "");this.put("csctLastIp", null);return this;};
 		/** not .... */
 		public M csctLastIpNot(){this.put("csctLastIpNot", "not");return this;};
		/** 当前客服  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csctServicer(Object csctServicer){this.put("csctServicer", csctServicer);return this;};
	 	/** and csct_servicer is null */
 		public M csctServicerNull(){if(this.get("csctServicerNot")==null)this.put("csctServicerNot", "");this.put("csctServicer", null);return this;};
 		/** not .... */
 		public M csctServicerNot(){this.put("csctServicerNot", "not");return this;};
		/** 最近时间        **/
		public M csctLastTime(Object csctLastTime){this.put("csctLastTime", csctLastTime);return this;};
	 	/** and csct_last_time is null */
 		public M csctLastTimeNull(){if(this.get("csctLastTimeNot")==null)this.put("csctLastTimeNot", "");this.put("csctLastTime", null);return this;};
 		/** not .... */
 		public M csctLastTimeNot(){this.put("csctLastTimeNot", "not");return this;};
 		/** and csct_last_time >= ? */
 		public M csctLastTimeStart(Object start){this.put("csctLastTimeStart", start);return this;};			
 		/** and csct_last_time <= ? */
 		public M csctLastTimeEnd(Object end){this.put("csctLastTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csctAddTime(Object csctAddTime){this.put("csctAddTime", csctAddTime);return this;};
	 	/** and csct_add_time is null */
 		public M csctAddTimeNull(){if(this.get("csctAddTimeNot")==null)this.put("csctAddTimeNot", "");this.put("csctAddTime", null);return this;};
 		/** not .... */
 		public M csctAddTimeNot(){this.put("csctAddTimeNot", "not");return this;};
 		/** and csct_add_time >= ? */
 		public M csctAddTimeStart(Object start){this.put("csctAddTimeStart", start);return this;};			
 		/** and csct_add_time <= ? */
 		public M csctAddTimeEnd(Object end){this.put("csctAddTimeEnd", end);return this;};
		/** 备注信息        **/
		public M csctRemark(Object csctRemark){this.put("csctRemark", csctRemark);return this;};
	 	/** and csct_remark is null */
 		public M csctRemarkNull(){if(this.get("csctRemarkNot")==null)this.put("csctRemarkNot", "");this.put("csctRemark", null);return this;};
 		/** not .... */
 		public M csctRemarkNot(){this.put("csctRemarkNot", "not");return this;};
		/** 客服状态 [非空]   0:排队中 1:沟通中 2:已结束     **/
		public M csctState(Object csctState){this.put("csctState", csctState);return this;};
	 	/** and csct_state is null */
 		public M csctStateNull(){if(this.get("csctStateNot")==null)this.put("csctStateNot", "");this.put("csctState", null);return this;};
 		/** not .... */
 		public M csctStateNot(){this.put("csctStateNot", "not");return this;};
		/** 当前状态 [非空]   1:在线 0:不在线     **/
		public M csctStatus(Object csctStatus){this.put("csctStatus", csctStatus);return this;};
	 	/** and csct_status is null */
 		public M csctStatusNull(){if(this.get("csctStatusNot")==null)this.put("csctStatusNot", "");this.put("csctStatus", null);return this;};
 		/** not .... */
 		public M csctStatusNot(){this.put("csctStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有在线访客 **/
		public @api List<CsChatTalker> list(Integer size){
			return getCsChatTalkerList(this,size);
		}
		/** 获取在线访客分页 **/
		public @api Page<CsChatTalker> page(int page,int size){
			return getCsChatTalkerPage(page, size , this);
		}
		/** 根据查询条件取在线访客 **/
		public @api CsChatTalker get(){
			return getCsChatTalker(this);
		}
		/** 获取在线访客数 **/
		public @api Long count(){
			return getCsChatTalkerCount(this);
		}
		/** 获取在线访客表达式 **/
		public @api <T> T eval(String strEval){
			return getCsChatTalkerEval(strEval,this);
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
			updateCsChatTalker(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csctId="csctId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csctHost="csctHost";
		/** 名称姓名 [非空]    客户姓名   **/
		public final static @type(String.class) @like String csctName="csctName";
		/** 使用头像        **/
		public final static @type(String.class)  String csctHeader="csctHeader";
		/** 个性签名        **/
		public final static @type(String.class) @like String csctSign="csctSign";
		/** 关联会员  [CsMember]   此项有值为会员   **/
		public final static @type(Long.class)  String csctMember="csctMember";
		/** 会话标识     clientid或openid   **/
		public final static @type(String.class) @like String csctFlag="csctFlag";
		/** 来源平台 [非空]   0:后台 1:网站 2:微站 3:微信 4:支付宝 5:短信 6:QQ     **/
		public final static @type(Short.class)  String csctPlatform="csctPlatform";
		/** 上线次数 [非空]       **/
		public final static @type(Integer.class)  String csctCount="csctCount";
		/** and csct_count >= ? */
		public final static @type(Integer.class) String csctCountMin="csctCountMin";
		/** and csct_count <= ? */
		public final static @type(Integer.class) String csctCountMax="csctCountMax";
		/** IP地址        **/
		public final static @type(String.class) @like String csctLastIp="csctLastIp";
		/** 当前客服  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csctServicer="csctServicer";
		/** 最近时间        **/
		public final static @type(Date.class)  String csctLastTime="csctLastTime";
	 	/** and csct_last_time >= ? */
 		public final static @type(Date.class) String csctLastTimeStart="csctLastTimeStart";
 		/** and csct_last_time <= ? */
 		public final static @type(Date.class) String csctLastTimeEnd="csctLastTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csctAddTime="csctAddTime";
	 	/** and csct_add_time >= ? */
 		public final static @type(Date.class) String csctAddTimeStart="csctAddTimeStart";
 		/** and csct_add_time <= ? */
 		public final static @type(Date.class) String csctAddTimeEnd="csctAddTimeEnd";
		/** 备注信息        **/
		public final static @type(String.class) @like String csctRemark="csctRemark";
		/** 客服状态 [非空]   0:排队中 1:沟通中 2:已结束     **/
		public final static @type(Short.class)  String csctState="csctState";
		/** 当前状态 [非空]   1:在线 0:不在线     **/
		public final static @type(Short.class)  String csctStatus="csctStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csctId="csct_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csctHost="csct_host";
		/** 名称姓名 [非空]    客户姓名   **/
		public final static String csctName="csct_name";
		/** 使用头像        **/
		public final static String csctHeader="csct_header";
		/** 个性签名        **/
		public final static String csctSign="csct_sign";
		/** 关联会员  [CsMember]   此项有值为会员   **/
		public final static String csctMember="csct_member";
		/** 会话标识     clientid或openid   **/
		public final static String csctFlag="csct_flag";
		/** 来源平台 [非空]   0:后台 1:网站 2:微站 3:微信 4:支付宝 5:短信 6:QQ     **/
		public final static String csctPlatform="csct_platform";
		/** 上线次数 [非空]       **/
		public final static String csctCount="csct_count";
		/** IP地址        **/
		public final static String csctLastIp="csct_last_ip";
		/** 当前客服  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csctServicer="csct_servicer";
		/** 最近时间        **/
		public final static String csctLastTime="csct_last_time";
		/** 添加时间 [非空]       **/
		public final static String csctAddTime="csct_add_time";
		/** 备注信息        **/
		public final static String csctRemark="csct_remark";
		/** 客服状态 [非空]   0:排队中 1:沟通中 2:已结束     **/
		public final static String csctState="csct_state";
		/** 当前状态 [非空]   1:在线 0:不在线     **/
		public final static String csctStatus="csct_status";
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
				$.Set(name,CsChatTalker.getCsChatTalker(params));
			else
				$.Set(name,CsChatTalker.getCsChatTalkerList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取在线访客数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsChatTalker) $.GetRequest("CsChatTalker$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsChatTalker.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsChatTalker.getCsChatTalker(params);
			else
				value = CsChatTalker.getCsChatTalkerList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsChatTalker.Get($.add(CsChatTalker.F.csctId,param));
		else if(!$.empty(param.toString()))
			value = CsChatTalker.get(Long.valueOf(param.toString()));
		$.SetRequest("CsChatTalker$"+param.hashCode(), value);
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
	public void mergeSet(CsChatTalker old){
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