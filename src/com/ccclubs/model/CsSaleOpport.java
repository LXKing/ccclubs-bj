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

@namespace("sale/opport")
public @caption("销售机会") @table("cs_sale_opport") class CsSaleOpport implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csso_id")   @primary  @note("  ") Long cssoId;// 主键 非空     
	private @caption("城市") @column("csso_host")    @relate("$cssoHost") @RelateClass(SrvHost.class)  @note("  ") Long cssoHost;// 非空     
 	private SrvHost $cssoHost;//关联对象[运营城市]
	private @caption("机会标题") @column("csso_title")    @note("  ") String cssoTitle;// 非空     
	private @caption("归属战役") @column("csso_battle")    @relate("$cssoBattle") @RelateClass(TbSaleBattle.class)  @note("  ") Long cssoBattle;// 非空     
 	private TbSaleBattle $cssoBattle;//关联对象[销售战役]
	private @caption("归属活动") @column("csso_event")    @relate("$cssoEvent") @RelateClass(CsSaleEvent.class)  @note("  ") Long cssoEvent;// 非空     
 	private CsSaleEvent $cssoEvent;//关联对象[销售活动]
	private @caption("机会来源") @column("csso_from")    @relate("$cssoFrom") @RelateClass(SrvProperty.class)  @note(" opport_from:销售机会来源  比如：电话来访?独立开发?客户介绍?朋友介绍?媒体宣传?网络搜索?网络广告?报纸广告?代理商?会议促销?公开招标?客户主动上门") Long cssoFrom;// 非空 opport_from:销售机会来源  比如：电话来访?独立开发?客户介绍?朋友介绍?媒体宣传?网络搜索?网络广告?报纸广告?代理商?会议促销?公开招标?客户主动上门   
 	private SrvProperty $cssoFrom;//关联对象[系统属性]
	private @caption("客户资料") @column("csso_custom")    @relate("$cssoCustom") @RelateClass(TbSaleCustom.class)  @note("  ") Long cssoCustom;// 非空     
 	private TbSaleCustom $cssoCustom;//关联对象[客户资料]
	private @caption("客户姓名") @column("csso_contact")    @note("  ") String cssoContact;//     
	private @caption("联系电话") @column("csso_number")    @note("  ") String cssoNumber;//     
	private @caption("成功机率") @column("csso_rate")    @note(" 0:1% 1:25% 2:50% 3:75% 4:99%  ") Short cssoRate;// 非空 0:1% 1:25% 2:50% 3:75% 4:99%     
	private @caption("所有人") @column("csso_onwer")    @relate("$cssoOnwer") @RelateClass(SrvUser.class)  @note("  ") String cssoOnwer;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cssoOnwer;//关联对象[用户]
	private @caption("添加人") @column("csso_user")    @relate("$cssoUser") @RelateClass(SrvUser.class)  @note("  ") Long cssoUser;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cssoUser;//关联对象[用户]
	private @caption("跟单标签") @column("csso_tag")  @hidden   @relate("$cssoTag") @RelateClass(SrvProperty.class)  @note(" opport_tag:销售机会跟单标签  ") String cssoTag;// opport_tag:销售机会跟单标签     
 	private SrvProperty $cssoTag;//关联对象[系统属性]
	private @caption("机会描述") @column("csso_profile")    @note("  ") String cssoProfile;//     
	private @caption("修改时间") @column("csso_update_time")    @note("  ") Date cssoUpdateTime;// 非空     
	private @caption("添加时间") @column("csso_add_time")    @note("  ") Date cssoAddTime;// 非空     
	private @caption("状态") @column("csso_status")    @note(" 0:跟踪 1:成功 2:搁置 3:失败 4:失效  ") Short cssoStatus;// 非空 0:跟踪 1:成功 2:搁置 3:失败 4:失效     
	
	//默认构造函数
	public CsSaleOpport(){
	
	}
	
	//主键构造函数
	public CsSaleOpport(Long id){
		this.cssoId = id;
	}
	
	/**所有字段构造函数 CsSaleOpport(cssoHost,cssoTitle,cssoBattle,cssoEvent,cssoFrom,cssoCustom,cssoContact,cssoNumber,cssoRate,cssoOnwer,cssoUser,cssoTag,cssoProfile,cssoUpdateTime,cssoAddTime,cssoStatus)
	 CsSaleOpport(
	 	$.getLong("cssoHost")//城市 [非空]
	 	,$.getString("cssoTitle")//机会标题 [非空]
	 	,$.getLong("cssoBattle")//归属战役 [非空]
	 	,$.getLong("cssoEvent")//归属活动 [非空]
	 	,$.getLong("cssoFrom")//机会来源 [非空]
	 	,$.getLong("cssoCustom")//客户资料 [非空]
	 	,$.getString("cssoContact")//客户姓名
	 	,$.getString("cssoNumber")//联系电话
	 	,$.getShort("cssoRate")//成功机率 [非空]
	 	,$.getString("cssoOnwer")//所有人 [非空]
	 	,$.getLong("cssoUser")//添加人 [非空]
	 	,$.getString("cssoTag")//跟单标签
	 	,$.getString("cssoProfile")//机会描述
	 	,$.getDate("cssoUpdateTime")//修改时间 [非空]
	 	,$.getDate("cssoAddTime")//添加时间 [非空]
	 	,$.getShort("cssoStatus")//状态 [非空]
	 )
	**/
	public CsSaleOpport(Long cssoHost,String cssoTitle,Long cssoBattle,Long cssoEvent,Long cssoFrom,Long cssoCustom,String cssoContact,String cssoNumber,Short cssoRate,String cssoOnwer,Long cssoUser,String cssoTag,String cssoProfile,Date cssoUpdateTime,Date cssoAddTime,Short cssoStatus){
		this.cssoHost=cssoHost;
		this.cssoTitle=cssoTitle;
		this.cssoBattle=cssoBattle;
		this.cssoEvent=cssoEvent;
		this.cssoFrom=cssoFrom;
		this.cssoCustom=cssoCustom;
		this.cssoContact=cssoContact;
		this.cssoNumber=cssoNumber;
		this.cssoRate=cssoRate;
		this.cssoOnwer=cssoOnwer;
		this.cssoUser=cssoUser;
		this.cssoTag=cssoTag;
		this.cssoProfile=cssoProfile;
		this.cssoUpdateTime=cssoUpdateTime;
		this.cssoAddTime=cssoAddTime;
		this.cssoStatus=cssoStatus;
	}
	
	//设置非空字段
	public CsSaleOpport setNotNull(Long cssoHost,String cssoTitle,Long cssoBattle,Long cssoEvent,Long cssoFrom,Long cssoCustom,Short cssoRate,String cssoOnwer,Long cssoUser,Date cssoUpdateTime,Date cssoAddTime,Short cssoStatus){
		this.cssoHost=cssoHost;
		this.cssoTitle=cssoTitle;
		this.cssoBattle=cssoBattle;
		this.cssoEvent=cssoEvent;
		this.cssoFrom=cssoFrom;
		this.cssoCustom=cssoCustom;
		this.cssoRate=cssoRate;
		this.cssoOnwer=cssoOnwer;
		this.cssoUser=cssoUser;
		this.cssoUpdateTime=cssoUpdateTime;
		this.cssoAddTime=cssoAddTime;
		this.cssoStatus=cssoStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsSaleOpport cssoId(Long cssoId){
		this.cssoId = cssoId;
		this.setSeted(F.cssoId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsSaleOpport cssoHost(Long cssoHost){
		this.cssoHost = cssoHost;
		this.setSeted(F.cssoHost);
		return this;
	}
	/** 机会标题 [非空]       **/
	public CsSaleOpport cssoTitle(String cssoTitle){
		this.cssoTitle = cssoTitle;
		this.setSeted(F.cssoTitle);
		return this;
	}
	/** 归属战役 [非空] [TbSaleBattle]      **/
	public CsSaleOpport cssoBattle(Long cssoBattle){
		this.cssoBattle = cssoBattle;
		this.setSeted(F.cssoBattle);
		return this;
	}
	/** 归属活动 [非空] [CsSaleEvent]      **/
	public CsSaleOpport cssoEvent(Long cssoEvent){
		this.cssoEvent = cssoEvent;
		this.setSeted(F.cssoEvent);
		return this;
	}
	/** 机会来源 [非空] [SrvProperty]  opport_from:销售机会来源  比如：电话来访?独立开发?客户介绍?朋友介绍?媒体宣传?网络搜索?网络广告?报纸广告?代理商?会议促销?公开招标?客户主动上门   **/
	public CsSaleOpport cssoFrom(Long cssoFrom){
		this.cssoFrom = cssoFrom;
		this.setSeted(F.cssoFrom);
		return this;
	}
	/** 客户资料 [非空] [TbSaleCustom]      **/
	public CsSaleOpport cssoCustom(Long cssoCustom){
		this.cssoCustom = cssoCustom;
		this.setSeted(F.cssoCustom);
		return this;
	}
	/** 客户姓名        **/
	public CsSaleOpport cssoContact(String cssoContact){
		this.cssoContact = cssoContact;
		this.setSeted(F.cssoContact);
		return this;
	}
	/** 联系电话        **/
	public CsSaleOpport cssoNumber(String cssoNumber){
		this.cssoNumber = cssoNumber;
		this.setSeted(F.cssoNumber);
		return this;
	}
	/** 成功机率 [非空]   0:1% 1:25% 2:50% 3:75% 4:99%     **/
	public CsSaleOpport cssoRate(Short cssoRate){
		this.cssoRate = cssoRate;
		this.setSeted(F.cssoRate);
		return this;
	}
	/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsSaleOpport cssoOnwer(String cssoOnwer){
		this.cssoOnwer = cssoOnwer;
		this.setSeted(F.cssoOnwer);
		return this;
	}
	/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsSaleOpport cssoUser(Long cssoUser){
		this.cssoUser = cssoUser;
		this.setSeted(F.cssoUser);
		return this;
	}
	/** 跟单标签  [SrvProperty]  opport_tag:销售机会跟单标签     **/
	public CsSaleOpport cssoTag(String cssoTag){
		this.cssoTag = cssoTag;
		this.setSeted(F.cssoTag);
		return this;
	}
	/** 机会描述        **/
	public CsSaleOpport cssoProfile(String cssoProfile){
		this.cssoProfile = cssoProfile;
		this.setSeted(F.cssoProfile);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsSaleOpport cssoUpdateTime(Date cssoUpdateTime){
		this.cssoUpdateTime = cssoUpdateTime;
		this.setSeted(F.cssoUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsSaleOpport cssoAddTime(Date cssoAddTime){
		this.cssoAddTime = cssoAddTime;
		this.setSeted(F.cssoAddTime);
		return this;
	}
	/** 状态 [非空]   0:跟踪 1:成功 2:搁置 3:失败 4:失效     **/
	public CsSaleOpport cssoStatus(Short cssoStatus){
		this.cssoStatus = cssoStatus;
		this.setSeted(F.cssoStatus);
		return this;
	}
	
	
	//克隆对象
	public CsSaleOpport clone(){
		CsSaleOpport clone = new CsSaleOpport();
		clone.cssoHost=this.cssoHost;
		clone.cssoTitle=this.cssoTitle;
		clone.cssoBattle=this.cssoBattle;
		clone.cssoEvent=this.cssoEvent;
		clone.cssoFrom=this.cssoFrom;
		clone.cssoCustom=this.cssoCustom;
		clone.cssoRate=this.cssoRate;
		clone.cssoOnwer=this.cssoOnwer;
		clone.cssoUser=this.cssoUser;
		clone.cssoUpdateTime=this.cssoUpdateTime;
		clone.cssoAddTime=this.cssoAddTime;
		clone.cssoStatus=this.cssoStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取销售机会
	 * @param id
	 * @return
	 */
	public static @api CsSaleOpport get(Long id){		
		return getCsSaleOpportById(id);
	}
	/**
	 * 获取所有销售机会
	 * @return
	 */
	public static @api List<CsSaleOpport> list(Map params,Integer size){
		return getCsSaleOpportList(params,size);
	}
	/**
	 * 获取销售机会分页
	 * @return
	 */
	public static @api Page<CsSaleOpport> page(int page,int size,Map params){
		return getCsSaleOpportPage(page, size , params);
	}
	/**
	 * 根据查询条件取销售机会
	 * @param params
	 * @return
	 */
	public static @api CsSaleOpport Get(Map params){
		return getCsSaleOpport(params);
	}
	/**
	 * 获取销售机会数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsSaleOpportCount(params);
	}
	/**
	 * 获取销售机会数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsSaleOpportEval(eval,params);
	}
	
	/**
	 * 根据ID取销售机会
	 * @param id
	 * @return
	 */
	public static @api CsSaleOpport getCsSaleOpportById(Long id){		
		CsSaleOpport csSaleOpport = (CsSaleOpport) $.GetRequest("CsSaleOpport$"+id);
		if(csSaleOpport!=null)
			return csSaleOpport;
		com.ccclubs.service.admin.ICsSaleOpportService csSaleOpportService = $.GetSpringBean("csSaleOpportService");		
		return csSaleOpportService.getCsSaleOpportById(id);
	}
	
	
	/**
	 * 根据ID取销售机会的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsSaleOpport.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsSaleOpport csSaleOpport = get(id);
		if(csSaleOpport!=null){
			String strValue = csSaleOpport.getCssoTitle$();
			if(!"CssoTitle".equals("CssoTitle"))
				strValue+="("+csSaleOpport.getCssoTitle$()+")";
			MemCache.setValue(CsSaleOpport.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCssoTitle$();
		if(!"CssoTitle".equals("CssoTitle"))
			keyValue+="("+this.getCssoTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有销售机会
	 * @return
	 */
	public static @api List<CsSaleOpport> getCsSaleOpportList(Map params,Integer size){
		com.ccclubs.service.admin.ICsSaleOpportService csSaleOpportService = $.GetSpringBean("csSaleOpportService");
		return csSaleOpportService.getCsSaleOpportList(params, size);
	}
	
	/**
	 * 获取销售机会分页
	 * @return
	 */
	public static @api Page<CsSaleOpport> getCsSaleOpportPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsSaleOpportService csSaleOpportService = $.GetSpringBean("csSaleOpportService");
		return csSaleOpportService.getCsSaleOpportPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取销售机会
	 * @param params
	 * @return
	 */
	public static @api CsSaleOpport getCsSaleOpport(Map params){
		com.ccclubs.service.admin.ICsSaleOpportService csSaleOpportService = $.GetSpringBean("csSaleOpportService");
		return csSaleOpportService.getCsSaleOpport(params);
	}
	
	/**
	 * 获取销售机会数
	 * @return
	 */
	public static @api Long getCsSaleOpportCount(Map params){
		com.ccclubs.service.admin.ICsSaleOpportService csSaleOpportService = $.GetSpringBean("csSaleOpportService");
		return csSaleOpportService.getCsSaleOpportCount(params);
	}
		
		
	/**
	 * 获取销售机会自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsSaleOpportEval(String eval,Map params){
		com.ccclubs.service.admin.ICsSaleOpportService csSaleOpportService = $.GetSpringBean("csSaleOpportService");
		return csSaleOpportService.getCsSaleOpportEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsSaleOpport(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsSaleOpportService csSaleOpportService = $.GetSpringBean("csSaleOpportService");
		csSaleOpportService.updateCsSaleOpportByConfirm(set, where);
	}
	
	
	/**
	 * 保存销售机会对象
	 * @param params
	 * @return
	 */
	public CsSaleOpport save(){
		com.ccclubs.service.admin.ICsSaleOpportService csSaleOpportService = $.GetSpringBean("csSaleOpportService");
		if(this.getCssoId()!=null)
			csSaleOpportService.updateCsSaleOpport(this);
		else
			return csSaleOpportService.saveCsSaleOpport(this);
		return this;
	}
	
	
	/**
	 * 更新销售机会对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsSaleOpportService csSaleOpportService = $.GetSpringBean("csSaleOpportService");
		csSaleOpportService.updateCsSaleOpport$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsSaleOpportService csSaleOpportService = $.GetSpringBean("csSaleOpportService");
		if($.equals($.config("logic_delete"),"true"))
			csSaleOpportService.removeCsSaleOpportById(this.getCssoId());
		else
			csSaleOpportService.deleteCsSaleOpportById(this.getCssoId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsSaleOpportService csSaleOpportService = $.GetSpringBean("csSaleOpportService");
		return csSaleOpportService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCssoId(){
		return this.cssoId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCssoId$(){
		String strValue="";
		 strValue=$.str(this.getCssoId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCssoId(Long cssoId){
		this.cssoId = cssoId;
		this.setSeted(F.cssoId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCssoHost(){
		return this.cssoHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCssoHost$(){
		String strValue="";
		if(this.getCssoHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCssoHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCssoHost(Long cssoHost){
		this.cssoHost = cssoHost;
		this.setSeted(F.cssoHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cssoHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCssoHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCssoHost()!=null){
 			srvHost = SrvHost.get(this.getCssoHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCssoHost(), srvHost);
	 	return srvHost;
	}
	/*******************************机会标题**********************************/	
	/**
	* 机会标题 [非空]      
	**/
	public String getCssoTitle(){
		return this.cssoTitle;
	}
	/**
	* 获取机会标题格式化(toString)
	**/
	public String getCssoTitle$(){
		String strValue="";
		 strValue=$.str(this.getCssoTitle());
	 	 return strValue;
	}
	/**
	* 机会标题 [非空]      
	**/
	public void setCssoTitle(String cssoTitle){
		this.cssoTitle = cssoTitle;
		this.setSeted(F.cssoTitle);
	}
	/*******************************归属战役**********************************/	
	/**
	* 归属战役 [非空] [TbSaleBattle]     
	**/
	public Long getCssoBattle(){
		return this.cssoBattle;
	}
	/**
	* 获取归属战役格式化(toString)
	**/
	public String getCssoBattle$(){
		String strValue="";
		if(this.getCssoBattle()!=null){
				strValue+=$.str(TbSaleBattle.getKeyValue(this.getCssoBattle()));
		}			
	 	 return strValue;
	}
	/**
	* 归属战役 [非空] [TbSaleBattle]     
	**/
	public void setCssoBattle(Long cssoBattle){
		this.cssoBattle = cssoBattle;
		this.setSeted(F.cssoBattle);
	}
	/**
	* 获取关联对象[销售战役]
	**/	 			
 	public TbSaleBattle get$cssoBattle(){
 		com.ccclubs.model.TbSaleBattle tbSaleBattle = (com.ccclubs.model.TbSaleBattle) $.GetRequest("TbSaleBattle$"+this.getCssoBattle());
 		if(tbSaleBattle!=null)
			return tbSaleBattle;
		if(this.getCssoBattle()!=null){
 			tbSaleBattle = TbSaleBattle.get(this.getCssoBattle());
 		}
 		$.SetRequest("TbSaleBattle$"+this.getCssoBattle(), tbSaleBattle);
	 	return tbSaleBattle;
	}
	/*******************************归属活动**********************************/	
	/**
	* 归属活动 [非空] [CsSaleEvent]     
	**/
	public Long getCssoEvent(){
		return this.cssoEvent;
	}
	/**
	* 获取归属活动格式化(toString)
	**/
	public String getCssoEvent$(){
		String strValue="";
		if(this.getCssoEvent()!=null){
				strValue+=$.str(CsSaleEvent.getKeyValue(this.getCssoEvent()));
		}			
	 	 return strValue;
	}
	/**
	* 归属活动 [非空] [CsSaleEvent]     
	**/
	public void setCssoEvent(Long cssoEvent){
		this.cssoEvent = cssoEvent;
		this.setSeted(F.cssoEvent);
	}
	/**
	* 获取关联对象[销售活动]
	**/	 			
 	public CsSaleEvent get$cssoEvent(){
 		com.ccclubs.model.CsSaleEvent csSaleEvent = (com.ccclubs.model.CsSaleEvent) $.GetRequest("CsSaleEvent$"+this.getCssoEvent());
 		if(csSaleEvent!=null)
			return csSaleEvent;
		if(this.getCssoEvent()!=null){
 			csSaleEvent = CsSaleEvent.get(this.getCssoEvent());
 		}
 		$.SetRequest("CsSaleEvent$"+this.getCssoEvent(), csSaleEvent);
	 	return csSaleEvent;
	}
	/*******************************机会来源**********************************/	
	/**
	* 机会来源 [非空] [SrvProperty]  opport_from:销售机会来源  比如：电话来访?独立开发?客户介绍?朋友介绍?媒体宣传?网络搜索?网络广告?报纸广告?代理商?会议促销?公开招标?客户主动上门  
	**/
	public Long getCssoFrom(){
		return this.cssoFrom;
	}
	/**
	* 获取机会来源格式化(toString)
	**/
	public String getCssoFrom$(){
		String strValue="";
		if(this.getCssoFrom()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCssoFrom()));
		}			
	 	 return strValue;
	}
	/**
	* 机会来源 [非空] [SrvProperty]  opport_from:销售机会来源  比如：电话来访?独立开发?客户介绍?朋友介绍?媒体宣传?网络搜索?网络广告?报纸广告?代理商?会议促销?公开招标?客户主动上门  
	**/
	public void setCssoFrom(Long cssoFrom){
		this.cssoFrom = cssoFrom;
		this.setSeted(F.cssoFrom);
	}
	/**
	* 获取关联对象[系统属性]
	**/	 			
 	public SrvProperty get$cssoFrom(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCssoFrom());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCssoFrom()!=null){
 			srvProperty = SrvProperty.get(this.getCssoFrom());
 		}
 		$.SetRequest("SrvProperty$"+this.getCssoFrom(), srvProperty);
	 	return srvProperty;
	}
	/*******************************客户资料**********************************/	
	/**
	* 客户资料 [非空] [TbSaleCustom]     
	**/
	public Long getCssoCustom(){
		return this.cssoCustom;
	}
	/**
	* 获取客户资料格式化(toString)
	**/
	public String getCssoCustom$(){
		String strValue="";
		if(this.getCssoCustom()!=null){
				strValue+=$.str(TbSaleCustom.getKeyValue(this.getCssoCustom()));
		}			
	 	 return strValue;
	}
	/**
	* 客户资料 [非空] [TbSaleCustom]     
	**/
	public void setCssoCustom(Long cssoCustom){
		this.cssoCustom = cssoCustom;
		this.setSeted(F.cssoCustom);
	}
	/**
	* 获取关联对象[客户资料]
	**/	 			
 	public TbSaleCustom get$cssoCustom(){
 		com.ccclubs.model.TbSaleCustom tbSaleCustom = (com.ccclubs.model.TbSaleCustom) $.GetRequest("TbSaleCustom$"+this.getCssoCustom());
 		if(tbSaleCustom!=null)
			return tbSaleCustom;
		if(this.getCssoCustom()!=null){
 			tbSaleCustom = TbSaleCustom.get(this.getCssoCustom());
 		}
 		$.SetRequest("TbSaleCustom$"+this.getCssoCustom(), tbSaleCustom);
	 	return tbSaleCustom;
	}
	/*******************************客户姓名**********************************/	
	/**
	* 客户姓名       
	**/
	public String getCssoContact(){
		return this.cssoContact;
	}
	/**
	* 获取客户姓名格式化(toString)
	**/
	public String getCssoContact$(){
		String strValue="";
		 strValue=$.str(this.getCssoContact());
	 	 return strValue;
	}
	/**
	* 客户姓名       
	**/
	public void setCssoContact(String cssoContact){
		this.cssoContact = cssoContact;
		this.setSeted(F.cssoContact);
	}
	/*******************************联系电话**********************************/	
	/**
	* 联系电话       
	**/
	public String getCssoNumber(){
		return this.cssoNumber;
	}
	/**
	* 获取联系电话格式化(toString)
	**/
	public String getCssoNumber$(){
		String strValue="";
		 strValue=$.str(this.getCssoNumber());
	 	 return strValue;
	}
	/**
	* 联系电话       
	**/
	public void setCssoNumber(String cssoNumber){
		this.cssoNumber = cssoNumber;
		this.setSeted(F.cssoNumber);
	}
	/*******************************成功机率**********************************/	
	/**
	* 成功机率 [非空]   0:1% 1:25% 2:50% 3:75% 4:99%    
	**/
	public Short getCssoRate(){
		return this.cssoRate;
	}
	/**
	* 获取成功机率格式化(toString)
	**/
	public String getCssoRate$(){
		String strValue="";
		 if($.equals($.str(this.getCssoRate()),"0"))
			strValue=$.str("1%");		 
		 if($.equals($.str(this.getCssoRate()),"1"))
			strValue=$.str("25%");		 
		 if($.equals($.str(this.getCssoRate()),"2"))
			strValue=$.str("50%");		 
		 if($.equals($.str(this.getCssoRate()),"3"))
			strValue=$.str("75%");		 
		 if($.equals($.str(this.getCssoRate()),"4"))
			strValue=$.str("99%");		 
	 	 return strValue;
	}
	/**
	* 成功机率 [非空]   0:1% 1:25% 2:50% 3:75% 4:99%    
	**/
	public void setCssoRate(Short cssoRate){
		this.cssoRate = cssoRate;
		this.setSeted(F.cssoRate);
	}
	/*******************************所有人**********************************/	
	/**
	* 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public String getCssoOnwer(){
		return this.cssoOnwer;
	}
	/**
	* 获取所有人格式化(toString)
	**/
	public String getCssoOnwer$(){
		String strValue="";
			com.ccclubs.model.SrvUser srvUser = com.ccclubs.model.SrvUser.getSrvUser($.add("suFamily",this.getCssoOnwer()));
		if(srvUser!=null)
			strValue+="["+$.str(srvUser.getSuRealName())+"]";
	 	 return strValue;
	}
	/**
	* 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCssoOnwer(String cssoOnwer){
		this.cssoOnwer = cssoOnwer;
		this.setSeted(F.cssoOnwer);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cssoOnwer(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCssoOnwer());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCssoOnwer()!=null){
 			srvUser = SrvUser.Get($.add(SrvUser.F.suId,this.getCssoOnwer()));
 		}
 		$.SetRequest("SrvUser$"+this.getCssoOnwer(), srvUser);
	 	return srvUser;
	}
	/*******************************添加人**********************************/	
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCssoUser(){
		return this.cssoUser;
	}
	/**
	* 获取添加人格式化(toString)
	**/
	public String getCssoUser$(){
		String strValue="";
		if(this.getCssoUser()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCssoUser()));
		}			
	 	 return strValue;
	}
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCssoUser(Long cssoUser){
		this.cssoUser = cssoUser;
		this.setSeted(F.cssoUser);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cssoUser(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCssoUser());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCssoUser()!=null){
 			srvUser = SrvUser.get(this.getCssoUser());
 		}
 		$.SetRequest("SrvUser$"+this.getCssoUser(), srvUser);
	 	return srvUser;
	}
	/*******************************跟单标签**********************************/	
	/**
	* 跟单标签  [SrvProperty]  opport_tag:销售机会跟单标签    
	**/
	public String getCssoTag(){
		return this.cssoTag;
	}
	/**
	* 获取跟单标签格式化(toString)
	**/
	public String getCssoTag$(){
		String strValue="";
			
		if(!$.empty(this.getCssoTag())){
			List<com.ccclubs.model.SrvProperty> items = com.ccclubs.model.SrvProperty.getSrvPropertyList($.add("definex","sp_id in ("+$.str(this.getCssoTag()).replace("#","")+")"),-1);
			for(com.ccclubs.model.SrvProperty item1:items)
				strValue+="["+$.str(item1.getSpName())+"]";
		}
	 	 return strValue;
	}
	/**
	* 跟单标签  [SrvProperty]  opport_tag:销售机会跟单标签    
	**/
	public void setCssoTag(String cssoTag){
		this.cssoTag = cssoTag;
		this.setSeted(F.cssoTag);
	}
			
	/**
	* 获取跟单标签列表
	**/ 			
 	public List<com.ccclubs.model.SrvProperty> get$cssoTag(){
		if(!$.empty(this.getCssoTag())){
			return com.ccclubs.model.SrvProperty.getSrvPropertyList($.add("definex","sp_id in ("+$.str(this.getCssoTag()).replace("#","")+")"),-1);
		}else
			return new ArrayList();
	}
	/*******************************机会描述**********************************/	
	/**
	* 机会描述       
	**/
	public String getCssoProfile(){
		return this.cssoProfile;
	}
	/**
	* 获取机会描述格式化(toString)
	**/
	public String getCssoProfile$(){
		String strValue="";
		 strValue=$.str(this.getCssoProfile());
	 	 return strValue;
	}
	/**
	* 机会描述       
	**/
	public void setCssoProfile(String cssoProfile){
		this.cssoProfile = cssoProfile;
		this.setSeted(F.cssoProfile);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCssoUpdateTime(){
		return this.cssoUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCssoUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssoUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCssoUpdateTime(Date cssoUpdateTime){
		this.cssoUpdateTime = cssoUpdateTime;
		this.setSeted(F.cssoUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCssoAddTime(){
		return this.cssoAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCssoAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssoAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCssoAddTime(Date cssoAddTime){
		this.cssoAddTime = cssoAddTime;
		this.setSeted(F.cssoAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:跟踪 1:成功 2:搁置 3:失败 4:失效    
	**/
	public Short getCssoStatus(){
		return this.cssoStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCssoStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCssoStatus()),"0"))
			strValue=$.str("跟踪");		 
		 if($.equals($.str(this.getCssoStatus()),"1"))
			strValue=$.str("成功");		 
		 if($.equals($.str(this.getCssoStatus()),"2"))
			strValue=$.str("搁置");		 
		 if($.equals($.str(this.getCssoStatus()),"3"))
			strValue=$.str("失败");		 
		 if($.equals($.str(this.getCssoStatus()),"4"))
			strValue=$.str("失效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:跟踪 1:成功 2:搁置 3:失败 4:失效    
	**/
	public void setCssoStatus(Short cssoStatus){
		this.cssoStatus = cssoStatus;
		this.setSeted(F.cssoStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsSaleOpport.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsSaleOpport.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsSaleOpport.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsSaleOpport.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsSaleOpport.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsSaleOpport.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsSaleOpport.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsSaleOpport.this);
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
		public M cssoId(Object cssoId){this.put("cssoId", cssoId);return this;};
	 	/** and csso_id is null */
 		public M cssoIdNull(){if(this.get("cssoIdNot")==null)this.put("cssoIdNot", "");this.put("cssoId", null);return this;};
 		/** not .... */
 		public M cssoIdNot(){this.put("cssoIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M cssoHost(Object cssoHost){this.put("cssoHost", cssoHost);return this;};
	 	/** and csso_host is null */
 		public M cssoHostNull(){if(this.get("cssoHostNot")==null)this.put("cssoHostNot", "");this.put("cssoHost", null);return this;};
 		/** not .... */
 		public M cssoHostNot(){this.put("cssoHostNot", "not");return this;};
		/** 机会标题 [非空]       **/
		public M cssoTitle(Object cssoTitle){this.put("cssoTitle", cssoTitle);return this;};
	 	/** and csso_title is null */
 		public M cssoTitleNull(){if(this.get("cssoTitleNot")==null)this.put("cssoTitleNot", "");this.put("cssoTitle", null);return this;};
 		/** not .... */
 		public M cssoTitleNot(){this.put("cssoTitleNot", "not");return this;};
		/** 归属战役 [非空] [TbSaleBattle]      **/
		public M cssoBattle(Object cssoBattle){this.put("cssoBattle", cssoBattle);return this;};
	 	/** and csso_battle is null */
 		public M cssoBattleNull(){if(this.get("cssoBattleNot")==null)this.put("cssoBattleNot", "");this.put("cssoBattle", null);return this;};
 		/** not .... */
 		public M cssoBattleNot(){this.put("cssoBattleNot", "not");return this;};
		public M cssoBattle$on(TbSaleBattle.M value){
			this.put("TbSaleBattle", value);
			this.put("cssoBattle$on", value);
			return this;
		};	
		/** 归属活动 [非空] [CsSaleEvent]      **/
		public M cssoEvent(Object cssoEvent){this.put("cssoEvent", cssoEvent);return this;};
	 	/** and csso_event is null */
 		public M cssoEventNull(){if(this.get("cssoEventNot")==null)this.put("cssoEventNot", "");this.put("cssoEvent", null);return this;};
 		/** not .... */
 		public M cssoEventNot(){this.put("cssoEventNot", "not");return this;};
		public M cssoEvent$on(CsSaleEvent.M value){
			this.put("CsSaleEvent", value);
			this.put("cssoEvent$on", value);
			return this;
		};	
		/** 机会来源 [非空] [SrvProperty]  opport_from:销售机会来源  比如：电话来访?独立开发?客户介绍?朋友介绍?媒体宣传?网络搜索?网络广告?报纸广告?代理商?会议促销?公开招标?客户主动上门   **/
		public M cssoFrom(Object cssoFrom){this.put("cssoFrom", cssoFrom);return this;};
	 	/** and csso_from is null */
 		public M cssoFromNull(){if(this.get("cssoFromNot")==null)this.put("cssoFromNot", "");this.put("cssoFrom", null);return this;};
 		/** not .... */
 		public M cssoFromNot(){this.put("cssoFromNot", "not");return this;};
		public M cssoFrom$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("cssoFrom$on", value);
			return this;
		};	
		/** 客户资料 [非空] [TbSaleCustom]      **/
		public M cssoCustom(Object cssoCustom){this.put("cssoCustom", cssoCustom);return this;};
	 	/** and csso_custom is null */
 		public M cssoCustomNull(){if(this.get("cssoCustomNot")==null)this.put("cssoCustomNot", "");this.put("cssoCustom", null);return this;};
 		/** not .... */
 		public M cssoCustomNot(){this.put("cssoCustomNot", "not");return this;};
		public M cssoCustom$on(TbSaleCustom.M value){
			this.put("TbSaleCustom", value);
			this.put("cssoCustom$on", value);
			return this;
		};	
		/** 客户姓名        **/
		public M cssoContact(Object cssoContact){this.put("cssoContact", cssoContact);return this;};
	 	/** and csso_contact is null */
 		public M cssoContactNull(){if(this.get("cssoContactNot")==null)this.put("cssoContactNot", "");this.put("cssoContact", null);return this;};
 		/** not .... */
 		public M cssoContactNot(){this.put("cssoContactNot", "not");return this;};
		/** 联系电话        **/
		public M cssoNumber(Object cssoNumber){this.put("cssoNumber", cssoNumber);return this;};
	 	/** and csso_number is null */
 		public M cssoNumberNull(){if(this.get("cssoNumberNot")==null)this.put("cssoNumberNot", "");this.put("cssoNumber", null);return this;};
 		/** not .... */
 		public M cssoNumberNot(){this.put("cssoNumberNot", "not");return this;};
		/** 成功机率 [非空]   0:1% 1:25% 2:50% 3:75% 4:99%     **/
		public M cssoRate(Object cssoRate){this.put("cssoRate", cssoRate);return this;};
	 	/** and csso_rate is null */
 		public M cssoRateNull(){if(this.get("cssoRateNot")==null)this.put("cssoRateNot", "");this.put("cssoRate", null);return this;};
 		/** not .... */
 		public M cssoRateNot(){this.put("cssoRateNot", "not");return this;};
		/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cssoOnwer(Object cssoOnwer){this.put("cssoOnwer", cssoOnwer);return this;};
	 	/** and csso_onwer is null */
 		public M cssoOnwerNull(){if(this.get("cssoOnwerNot")==null)this.put("cssoOnwerNot", "");this.put("cssoOnwer", null);return this;};
 		/** not .... */
 		public M cssoOnwerNot(){this.put("cssoOnwerNot", "not");return this;};
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cssoUser(Object cssoUser){this.put("cssoUser", cssoUser);return this;};
	 	/** and csso_user is null */
 		public M cssoUserNull(){if(this.get("cssoUserNot")==null)this.put("cssoUserNot", "");this.put("cssoUser", null);return this;};
 		/** not .... */
 		public M cssoUserNot(){this.put("cssoUserNot", "not");return this;};
		/** 跟单标签  [SrvProperty]  opport_tag:销售机会跟单标签     **/
		public M cssoTag(Object cssoTag){this.put("cssoTag", cssoTag);return this;};
	 	/** and csso_tag is null */
 		public M cssoTagNull(){if(this.get("cssoTagNot")==null)this.put("cssoTagNot", "");this.put("cssoTag", null);return this;};
 		/** not .... */
 		public M cssoTagNot(){this.put("cssoTagNot", "not");return this;};
		/** 机会描述        **/
		public M cssoProfile(Object cssoProfile){this.put("cssoProfile", cssoProfile);return this;};
	 	/** and csso_profile is null */
 		public M cssoProfileNull(){if(this.get("cssoProfileNot")==null)this.put("cssoProfileNot", "");this.put("cssoProfile", null);return this;};
 		/** not .... */
 		public M cssoProfileNot(){this.put("cssoProfileNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M cssoUpdateTime(Object cssoUpdateTime){this.put("cssoUpdateTime", cssoUpdateTime);return this;};
	 	/** and csso_update_time is null */
 		public M cssoUpdateTimeNull(){if(this.get("cssoUpdateTimeNot")==null)this.put("cssoUpdateTimeNot", "");this.put("cssoUpdateTime", null);return this;};
 		/** not .... */
 		public M cssoUpdateTimeNot(){this.put("cssoUpdateTimeNot", "not");return this;};
 		/** and csso_update_time >= ? */
 		public M cssoUpdateTimeStart(Object start){this.put("cssoUpdateTimeStart", start);return this;};			
 		/** and csso_update_time <= ? */
 		public M cssoUpdateTimeEnd(Object end){this.put("cssoUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cssoAddTime(Object cssoAddTime){this.put("cssoAddTime", cssoAddTime);return this;};
	 	/** and csso_add_time is null */
 		public M cssoAddTimeNull(){if(this.get("cssoAddTimeNot")==null)this.put("cssoAddTimeNot", "");this.put("cssoAddTime", null);return this;};
 		/** not .... */
 		public M cssoAddTimeNot(){this.put("cssoAddTimeNot", "not");return this;};
 		/** and csso_add_time >= ? */
 		public M cssoAddTimeStart(Object start){this.put("cssoAddTimeStart", start);return this;};			
 		/** and csso_add_time <= ? */
 		public M cssoAddTimeEnd(Object end){this.put("cssoAddTimeEnd", end);return this;};
		/** 状态 [非空]   0:跟踪 1:成功 2:搁置 3:失败 4:失效     **/
		public M cssoStatus(Object cssoStatus){this.put("cssoStatus", cssoStatus);return this;};
	 	/** and csso_status is null */
 		public M cssoStatusNull(){if(this.get("cssoStatusNot")==null)this.put("cssoStatusNot", "");this.put("cssoStatus", null);return this;};
 		/** not .... */
 		public M cssoStatusNot(){this.put("cssoStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有销售机会 **/
		public @api List<CsSaleOpport> list(Integer size){
			return getCsSaleOpportList(this,size);
		}
		/** 获取销售机会分页 **/
		public @api Page<CsSaleOpport> page(int page,int size){
			return getCsSaleOpportPage(page, size , this);
		}
		/** 根据查询条件取销售机会 **/
		public @api CsSaleOpport get(){
			return getCsSaleOpport(this);
		}
		/** 获取销售机会数 **/
		public @api Long count(){
			return getCsSaleOpportCount(this);
		}
		/** 获取销售机会表达式 **/
		public @api <T> T eval(String strEval){
			return getCsSaleOpportEval(strEval,this);
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
			updateCsSaleOpport(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cssoId="cssoId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cssoHost="cssoHost";
		/** 机会标题 [非空]       **/
		public final static @type(String.class) @like String cssoTitle="cssoTitle";
		/** 归属战役 [非空] [TbSaleBattle]      **/
		public final static @type(Long.class)  String cssoBattle="cssoBattle";
		/** 归属活动 [非空] [CsSaleEvent]      **/
		public final static @type(Long.class)  String cssoEvent="cssoEvent";
		/** 机会来源 [非空] [SrvProperty]  opport_from:销售机会来源  比如：电话来访?独立开发?客户介绍?朋友介绍?媒体宣传?网络搜索?网络广告?报纸广告?代理商?会议促销?公开招标?客户主动上门   **/
		public final static @type(Long.class)  String cssoFrom="cssoFrom";
		/** 客户资料 [非空] [TbSaleCustom]      **/
		public final static @type(Long.class)  String cssoCustom="cssoCustom";
		/** 客户姓名        **/
		public final static @type(String.class) @like String cssoContact="cssoContact";
		/** 联系电话        **/
		public final static @type(String.class) @like String cssoNumber="cssoNumber";
		/** 成功机率 [非空]   0:1% 1:25% 2:50% 3:75% 4:99%     **/
		public final static @type(Short.class)  String cssoRate="cssoRate";
		/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(String.class)  String cssoOnwer="cssoOnwer";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String cssoUser="cssoUser";
		/** 跟单标签  [SrvProperty]  opport_tag:销售机会跟单标签     **/
		public final static @type(String.class) @like String cssoTag="cssoTag";
		/** 机会描述        **/
		public final static @type(String.class) @like String cssoProfile="cssoProfile";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cssoUpdateTime="cssoUpdateTime";
	 	/** and csso_update_time >= ? */
 		public final static @type(Date.class) String cssoUpdateTimeStart="cssoUpdateTimeStart";
 		/** and csso_update_time <= ? */
 		public final static @type(Date.class) String cssoUpdateTimeEnd="cssoUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cssoAddTime="cssoAddTime";
	 	/** and csso_add_time >= ? */
 		public final static @type(Date.class) String cssoAddTimeStart="cssoAddTimeStart";
 		/** and csso_add_time <= ? */
 		public final static @type(Date.class) String cssoAddTimeEnd="cssoAddTimeEnd";
		/** 状态 [非空]   0:跟踪 1:成功 2:搁置 3:失败 4:失效     **/
		public final static @type(Short.class)  String cssoStatus="cssoStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cssoId="csso_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cssoHost="csso_host";
		/** 机会标题 [非空]       **/
		public final static String cssoTitle="csso_title";
		/** 归属战役 [非空] [TbSaleBattle]      **/
		public final static String cssoBattle="csso_battle";
		/** 归属活动 [非空] [CsSaleEvent]      **/
		public final static String cssoEvent="csso_event";
		/** 机会来源 [非空] [SrvProperty]  opport_from:销售机会来源  比如：电话来访?独立开发?客户介绍?朋友介绍?媒体宣传?网络搜索?网络广告?报纸广告?代理商?会议促销?公开招标?客户主动上门   **/
		public final static String cssoFrom="csso_from";
		/** 客户资料 [非空] [TbSaleCustom]      **/
		public final static String cssoCustom="csso_custom";
		/** 客户姓名        **/
		public final static String cssoContact="csso_contact";
		/** 联系电话        **/
		public final static String cssoNumber="csso_number";
		/** 成功机率 [非空]   0:1% 1:25% 2:50% 3:75% 4:99%     **/
		public final static String cssoRate="csso_rate";
		/** 所有人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cssoOnwer="csso_onwer";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cssoUser="csso_user";
		/** 跟单标签  [SrvProperty]  opport_tag:销售机会跟单标签     **/
		public final static String cssoTag="csso_tag";
		/** 机会描述        **/
		public final static String cssoProfile="csso_profile";
		/** 修改时间 [非空]       **/
		public final static String cssoUpdateTime="csso_update_time";
		/** 添加时间 [非空]       **/
		public final static String cssoAddTime="csso_add_time";
		/** 状态 [非空]   0:跟踪 1:成功 2:搁置 3:失败 4:失效     **/
		public final static String cssoStatus="csso_status";
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
				$.Set(name,CsSaleOpport.getCsSaleOpport(params));
			else
				$.Set(name,CsSaleOpport.getCsSaleOpportList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取销售机会数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsSaleOpport) $.GetRequest("CsSaleOpport$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsSaleOpport.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsSaleOpport.getCsSaleOpport(params);
			else
				value = CsSaleOpport.getCsSaleOpportList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsSaleOpport.Get($.add(CsSaleOpport.F.cssoId,param));
		else if(!$.empty(param.toString()))
			value = CsSaleOpport.get(Long.valueOf(param.toString()));
		$.SetRequest("CsSaleOpport$"+param.hashCode(), value);
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
	public void mergeSet(CsSaleOpport old){
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