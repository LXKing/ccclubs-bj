package com.ccclubs.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.ccclubs.model.CsSpecialCar.M;
import com.lazy3q.web.helper.$;

import com.lazy3q.web.tags.BodyTag;

import com.lazy3q.web.util.Page;
import com.lazy3q.lang.*;
import com.lazy3q.util.Function;
import com.ccclubs.dao.impl.MemCache;

@namespace("csm/complain")
public @caption("意见反馈") @table("cs_complain") class CsComplain implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csc_id")   @primary   @note("  ") Long cscId;
	private @caption("城市") @column("csc_host")    @relate("$cscHost") @RelateClass(SrvHost.class)  @note("  ") Long cscHost;
	private SrvHost $cscHost;//关联对象[城市]
	private @caption("反馈标题") @column("csc_title")      @note("  ") String cscTitle;
	private @caption("反馈内容") @column("csc_content")      @note("  ") String cscContent;
	private @caption("类型") @column("csc_type")      @note(" 0:投诉 1:建议 2:咨询  3:产品建议 4:驾驶体验") Short cscType;
	private @caption("反馈人") @column("csc_member")    @relate("$cscMember") @RelateClass(CsMember.class)  @note("  ") Long cscMember;
	private CsMember $cscMember;//关联对象[反馈人]
	private @caption("处理描述") @column("csc_dispose")      @note("  ") String cscDispose;
	private @caption("处理人") @column("csc_dealer")      @note("  ") Long cscDealer;
	private @caption("处理时间") @column("csc_update_time")      @note("  ") Date cscUpdateTime;
	private @caption("添加时间") @column("csc_add_time")      @note("  ") Date cscAddTime;
	private @caption("意见采纳") @column("csc_accept")      @note(" 0:未采纳 1:已采纳  ") Short cscAccept;
	private @caption("状态") @column("csc_status")      @note(" 1:待处理 2:已处理 0:无效  ") Short cscStatus;
	private @caption("订单来源") @column("csc_source")      @note(" 1:订单 2:帮助反馈") String cscSource;
	private @caption("外部关联") @column("csc_object")      @note("  ") String cscObject;
	private Object $cscObject;//泛关联对象，对象类名由csrObject字段中标明
	private @caption("评定等级") @column("csc_level")      @note(" 0:未评分 1:一星 2:二星 3:三星 4:四星 5:五星  ") Short cscLevel;
	private @caption("评定等级") @column("csc_level1")      @note(" 0:未评分 1:一星 2:二星 3:三星 4:四星 5:五星  ") Short cscLevel1;
	
	//默认构造函数
	public CsComplain(){
	
	}
	
	//主键构造函数
	public CsComplain(Long id){
		this.cscId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsComplain(Long cscHost,String cscTitle,String cscContent,Short cscType,Long cscMember,String cscDispose,Long cscDealer,Date cscUpdateTime,Date cscAddTime,Short cscAccept,Short cscStatus,String cscSource,String cscObject,Short cscLevel,Short cscLevel1){
		this.cscHost=cscHost;
		this.cscTitle=cscTitle;
		this.cscContent=cscContent;
		this.cscType=cscType;
		this.cscMember=cscMember;
		this.cscDispose=cscDispose;
		this.cscDealer=cscDealer;
		this.cscUpdateTime=cscUpdateTime;
		this.cscAddTime=cscAddTime;
		this.cscAccept=cscAccept;
		this.cscStatus=cscStatus;
		this.cscSource=cscSource;
		this.cscObject=cscObject;
		this.cscLevel=cscLevel;
		this.cscLevel1=cscLevel1;
	}
	
	//设置非空字段
	public CsComplain setNotNull(Long cscHost,String cscTitle,String cscContent,Short cscType,Long cscMember,Date cscUpdateTime,Date cscAddTime,Short cscAccept,Short cscStatus){
		this.cscHost=cscHost;
		this.cscTitle=cscTitle;
		this.cscContent=cscContent;
		this.cscType=cscType;
		this.cscMember=cscMember;
		this.cscUpdateTime=cscUpdateTime;
		this.cscAddTime=cscAddTime;
		this.cscAccept=cscAccept;
		this.cscStatus=cscStatus;
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsComplain cscId(Long cscId){
		this.cscId = cscId;
		this.setSeted(F.cscId);
		return this;
	}
	/** 城市 [非空]       **/
	public CsComplain cscHost(Long cscHost){
		this.cscHost = cscHost;
		this.setSeted(F.cscHost);
		return this;
	}
	/** 反馈标题 [非空]       **/
	public CsComplain cscTitle(String cscTitle){
		this.cscTitle = cscTitle;
		this.setSeted(F.cscTitle);
		return this;
	}
	/** 反馈内容 [非空]       **/
	public CsComplain cscContent(String cscContent){
		this.cscContent = cscContent;
		this.setSeted(F.cscContent);
		return this;
	}
	/** 类型 [非空]       **/
	public CsComplain cscType(Short cscType){
		this.cscType = cscType;
		this.setSeted(F.cscType);
		return this;
	}
	/** 反馈人 [非空]       **/
	public CsComplain cscMember(Long cscMember){
		this.cscMember = cscMember;
		this.setSeted(F.cscMember);
		return this;
	}
	/** 处理描述 [非空]       **/
	public CsComplain cscDispose(String cscDispose){
		this.cscDispose = cscDispose;
		this.setSeted(F.cscDispose);
		return this;
	}
	/** 处理人 [非空]       **/
	public CsComplain cscDealer(Long cscDealer){
		this.cscDealer = cscDealer;
		this.setSeted(F.cscDealer);
		return this;
	}
	/** 处理时间 [非空]       **/
	public CsComplain cscUpdateTime(Date cscUpdateTime){
		this.cscUpdateTime = cscUpdateTime;
		this.setSeted(F.cscUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsComplain cscAddTime(Date cscAddTime){
		this.cscAddTime = cscAddTime;
		this.setSeted(F.cscAddTime);
		return this;
	}
	/** 意见采纳 [非空]       **/
	public CsComplain cscAccept(Short cscAccept){
		this.cscAccept = cscAccept;
		this.setSeted(F.cscAccept);
		return this;
	}
	/** 状态 [非空]       **/
	public CsComplain cscStatus(Short cscStatus){
		this.cscStatus = cscStatus;
		this.setSeted(F.cscStatus);
		return this;
	}
	/** 订单来源 [非空]       **/
	public CsComplain cscSource(String cscSource){
		this.cscSource = cscSource;
		this.setSeted(F.cscSource);
		return this;
	}
	/** 外部关联 [非空]       **/
	public CsComplain cscObject(String cscObject){
		this.cscObject = cscObject;
		this.setSeted(F.cscObject);
		return this;
	}
	/** 评定等级 [非空]       **/
	public CsComplain cscLevel(Short cscLevel){
		this.cscLevel = cscLevel;
		this.setSeted(F.cscLevel);
		return this;
	}
	/** 评定等级 [非空]       **/
	public CsComplain cscLevel1(Short cscLevel1){
		this.cscLevel1 = cscLevel1;
		this.setSeted(F.cscLevel1);
		return this;
	}
	
	//克隆对象
	public CsComplain clone(){
		CsComplain clone = new CsComplain();
		clone.cscId=this.cscId;
		clone.cscHost=this.cscHost;
		clone.cscTitle=this.cscTitle;
		clone.cscContent=this.cscContent;
		clone.cscType=this.cscType;
		clone.cscMember=this.cscMember;
		clone.cscDispose=this.cscDispose;
		clone.cscDealer=this.cscDealer;
		clone.cscUpdateTime=this.cscUpdateTime;
		clone.cscAddTime=this.cscAddTime;
		clone.cscAccept=this.cscAccept;
		clone.cscStatus=this.cscStatus;
		clone.cscSource=this.cscSource;
		clone.cscObject=this.cscObject;
		clone.cscLevel=this.cscLevel;
		clone.cscLevel1=this.cscLevel1;
		return clone;
	}
	
	
	/**
	 * 根据ID取意见反馈
	 * @param id
	 * @return
	 */
	public static @api CsComplain get(Long id){		
		return getCsComplainById(id);
	}
	/**
	 * 获取所有意见反馈
	 * @return
	 */
	public static @api List<CsComplain> list(Map params,Integer size){
		return getCsComplainList(params,size);
	}
	/**
	 * 获取意见反馈分页
	 * @return
	 */
	public static @api Page<CsComplain> page(int page,int size,Map params){
		return getCsComplainPage(page, size , params);
	}
	/**
	 * 根据查询条件取意见反馈
	 * @param params
	 * @return
	 */
	public static @api CsComplain Get(Map params){
		return getCsComplain(params);
	}
	/**
	 * 获取意见反馈数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsComplainCount(params);
	}
	/**
	 * 获取意见反馈数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsComplainEval(eval,params);
	}
	
	/**
	 * 根据ID取意见反馈
	 * @param id
	 * @return
	 */
	public static @api CsComplain getCsComplainById(Long id){		
		CsComplain csComplain = (CsComplain) $.GetRequest("CsComplain$"+id);
		if(csComplain!=null)
			return csComplain;
		com.ccclubs.service.admin.ICsComplainService csComplainService = $.GetSpringBean("csComplainService");		
		return csComplainService.getCsComplainById(id);
	}
	
	
	/**
	 * 根据ID取意见反馈的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsComplain.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsComplain csComplain = get(id);
		if(csComplain!=null){
			String strValue = csComplain.getCscTitle$();
			if(!"CscTitle".equals("CscTitle"))
				strValue+="("+csComplain.getCscTitle$()+")";
			if(!"CscTitle".equals("CscContent"))
				strValue+="("+csComplain.getCscContent$()+")";
			MemCache.setValue(CsComplain.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCscTitle$();
		if(!"CscTitle".equals("CscTitle"))
			keyValue+="("+this.getCscTitle$()+")";
		if(!"CscTitle".equals("CscContent"))
			keyValue+="("+this.getCscContent$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有意见反馈
	 * @return
	 */
	public static @api List<CsComplain> getCsComplainList(Map params,Integer size){
		com.ccclubs.service.admin.ICsComplainService csComplainService = $.GetSpringBean("csComplainService");
		return csComplainService.getCsComplainList(params, size);
	}
	
	/**
	 * 获取意见反馈分页
	 * @return
	 */
	public static @api Page<CsComplain> getCsComplainPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsComplainService csComplainService = $.GetSpringBean("csComplainService");
		return csComplainService.getCsComplainPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取意见反馈
	 * @param params
	 * @return
	 */
	public static @api CsComplain getCsComplain(Map params){
		com.ccclubs.service.admin.ICsComplainService csComplainService = $.GetSpringBean("csComplainService");
		return csComplainService.getCsComplain(params);
	}
	
	/**
	 * 获取意见反馈数
	 * @return
	 */
	public static @api Long getCsComplainCount(Map params){
		com.ccclubs.service.admin.ICsComplainService csComplainService = $.GetSpringBean("csComplainService");
		return csComplainService.getCsComplainCount(params);
	}
		
		
	/**
	 * 获取意见反馈自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsComplainEval(String eval,Map params){
		com.ccclubs.service.admin.ICsComplainService csComplainService = $.GetSpringBean("csComplainService");
		return csComplainService.getCsComplainEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsComplain(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsComplainService csComplainService = $.GetSpringBean("csComplainService");
		csComplainService.updateCsComplainByConfirm(set, where);
	}
	
	
	/**
	 * 保存意见反馈对象
	 * @param params
	 * @return
	 */
	public CsComplain save(){
		com.ccclubs.service.admin.ICsComplainService csComplainService = $.GetSpringBean("csComplainService");
		if(this.getCscId()!=null)
			csComplainService.updateCsComplain(this);
		else
			return csComplainService.saveCsComplain(this);
		return this;
	}
	
	
	/**
	 * 更新意见反馈对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsComplainService csComplainService = $.GetSpringBean("csComplainService");
		csComplainService.updateCsComplain$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsComplainService csComplainService = $.GetSpringBean("csComplainService");
		if($.equals($.config("logic_delete"),"true"))
			csComplainService.removeCsComplainById(this.getCscId());
		else
			csComplainService.deleteCsComplainById(this.getCscId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsComplainService csComplainService = $.GetSpringBean("csComplainService");
		return csComplainService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCscId(){
		return this.cscId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCscId$(){
		String strValue="";
		 strValue=$.str(this.getCscId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCscId(Long cscId){
		this.cscId = cscId;
		this.setSeted(F.cscId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空]      
	**/
	public Long getCscHost(){
		return this.cscHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCscHost$(){
		String strValue="";
		if(this.getCscHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCscHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空]     
	**/
	public void setCscHost(Long cscHost){
		this.cscHost = cscHost;
		this.setSeted(F.cscHost);
	}
	/**
	* 获取关联对象[城市]
	**/	 			
 	public SrvHost get$cscHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCscHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCscHost()!=null){
			srvHost = SrvHost.get(this.getCscHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCscHost(), srvHost);
	 	return srvHost;
	}
	/*******************************反馈标题**********************************/	
	/**
	* 反馈标题 [非空]      
	**/
	public String getCscTitle(){
		return this.cscTitle;
	}
	/**
	* 获取反馈标题格式化(toString)
	**/
	public String getCscTitle$(){
		String strValue="";
		 strValue=$.str(this.getCscTitle());
	 	 return strValue;
	}
	/**
	* 反馈标题 [非空]     
	**/
	public void setCscTitle(String cscTitle){
		this.cscTitle = cscTitle;
		this.setSeted(F.cscTitle);
	}
	/*******************************反馈内容**********************************/	
	/**
	* 反馈内容 [非空]      
	**/
	public String getCscContent(){
		return this.cscContent;
	}
	/**
	* 获取反馈内容格式化(toString)
	**/
	public String getCscContent$(){
		String strValue="";
		 strValue=$.str(this.getCscContent());
	 	 return strValue;
	}
	/**
	* 反馈内容 [非空]     
	**/
	public void setCscContent(String cscContent){
		this.cscContent = cscContent;
		this.setSeted(F.cscContent);
	}
	/*******************************类型**********************************/	
	/**
	* 类型 [非空]      
	**/
	public Short getCscType(){
		return this.cscType;
	}
	/**
	* 获取类型格式化(toString)
	**/
	public String getCscType$(){
		String strValue="";
		 if($.equals($.str(this.getCscType()),"2"))
			 strValue=$.str("咨询");
		 if($.equals($.str(this.getCscType()),"1"))
			 strValue=$.str("建议");
		 if($.equals($.str(this.getCscType()),"0"))
			 strValue=$.str("投诉");
		 if($.equals($.str(this.getCscType()),"3"))
			 strValue=$.str("产品建议");
		 if($.equals($.str(this.getCscType()),"4"))
			 strValue=$.str("驾驶体验");
	 	 return strValue;
	}
	/**
	* 类型 [非空]     
	**/
	public void setCscType(Short cscType){
		this.cscType = cscType;
		this.setSeted(F.cscType);
	}
	/*******************************反馈人**********************************/	
	/**
	* 反馈人 [非空]      
	**/
	public Long getCscMember(){
		return this.cscMember;
	}
	/**
	* 获取反馈人格式化(toString)
	**/
	public String getCscMember$(){
		String strValue="";
		if(this.getCscMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCscMember()));
		}			
	 	 return strValue;
	}
	/**
	* 反馈人 [非空]     
	**/
	public void setCscMember(Long cscMember){
		this.cscMember = cscMember;
		this.setSeted(F.cscMember);
	}
	/**
	* 获取关联对象[反馈人]
	**/	 			
 	public CsMember get$cscMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCscMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCscMember()!=null){
			csMember = CsMember.get(this.getCscMember());
 		}
 		$.SetRequest("CsMember$"+this.getCscMember(), csMember);
	 	return csMember;
	}
	/*******************************处理描述**********************************/	
	/**
	* 处理描述 [非空]      
	**/
	public String getCscDispose(){
		return this.cscDispose;
	}
	/**
	* 获取处理描述格式化(toString)
	**/
	public String getCscDispose$(){
		String strValue="";
		 strValue=$.str(this.getCscDispose());
	 	 return strValue;
	}
	/**
	* 处理描述 [非空]     
	**/
	public void setCscDispose(String cscDispose){
		this.cscDispose = cscDispose;
		this.setSeted(F.cscDispose);
	}
	/*******************************处理人**********************************/	
	/**
	* 处理人 [非空]      
	**/
	public Long getCscDealer(){
		return this.cscDealer;
	}
	/**
	* 获取处理人格式化(toString)
	**/
	public String getCscDealer$(){
		String strValue="";
		 strValue=$.str(this.getCscDealer());
	 	 return strValue;
	}
	/**
	* 处理人 [非空]     
	**/
	public void setCscDealer(Long cscDealer){
		this.cscDealer = cscDealer;
		this.setSeted(F.cscDealer);
	}
	/*******************************处理时间**********************************/	
	/**
	* 处理时间 [非空]      
	**/
	public Date getCscUpdateTime(){
		return this.cscUpdateTime;
	}
	/**
	* 获取处理时间格式化(toString)
	**/
	public String getCscUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 处理时间 [非空]     
	**/
	public void setCscUpdateTime(Date cscUpdateTime){
		this.cscUpdateTime = cscUpdateTime;
		this.setSeted(F.cscUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCscAddTime(){
		return this.cscAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCscAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]     
	**/
	public void setCscAddTime(Date cscAddTime){
		this.cscAddTime = cscAddTime;
		this.setSeted(F.cscAddTime);
	}
	/*******************************意见采纳**********************************/	
	/**
	* 意见采纳 [非空]      
	**/
	public Short getCscAccept(){
		return this.cscAccept;
	}
	/**
	* 获取意见采纳格式化(toString)
	**/
	public String getCscAccept$(){
		String strValue="";
		 if($.equals($.str(this.getCscAccept()),"1"))
			 strValue=$.str("已采纳");
		 if($.equals($.str(this.getCscAccept()),"0"))
			 strValue=$.str("未采纳");
	 	 return strValue;
	}
	/**
	* 意见采纳 [非空]     
	**/
	public void setCscAccept(Short cscAccept){
		this.cscAccept = cscAccept;
		this.setSeted(F.cscAccept);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]      
	**/
	public Short getCscStatus(){
		return this.cscStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCscStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCscStatus()),"2"))
			 strValue=$.str("已处理");
		 if($.equals($.str(this.getCscStatus()),"1"))
			 strValue=$.str("待处理");
		 if($.equals($.str(this.getCscStatus()),"0"))
			 strValue=$.str("无效");
	 	 return strValue;
	}
	/**
	* 状态 [非空]     
	**/
	public void setCscStatus(Short cscStatus){
		this.cscStatus = cscStatus;
		this.setSeted(F.cscStatus);
	}
	/*******************************订单来源**********************************/	
	/**
	* 订单来源 [非空]      
	**/
	public String getCscSource(){
		return this.cscSource;
	}
	/**
	* 获取订单来源格式化(toString)
	**/
	public String getCscSource$(){
		String strValue="";
		if($.equals($.str(this.getCscSource()),"1"))
			strValue=$.str("订单评价");
		if($.equals($.str(this.getCscSource()),"2"))
			strValue=$.str("帮助与反馈");
	 	 return strValue;
	}
	/**
	* 订单来源 [非空]     
	**/
	public void setCscSource(String cscSource){
		this.cscSource = cscSource;
		this.setSeted(F.cscSource);
	}
	/*******************************外部关联**********************************/	
	/**
	* 外部关联 [非空]      
	**/
	public String getCscObject(){
		return this.cscObject;
	}
	/**
	* 获取其它关联格式化(toString)
	**/
	public String getCscObject$(){
		String strValue="";
	 	 Object object = this.get$cscObject();
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
	* 外部关联 [非空]     
	**/
	public void setCscObject(String cscObject){
		this.cscObject = cscObject;
		this.setSeted(F.cscObject);
	}
	
	/**
	* 泛关联对象，对象类名由csrObject字段中标明
	**/	 			
 	public <T> T get$cscObject(){
		if($.empty(this.cscObject))
 			return null;
 		try{
	 		String ClassName = this.cscObject.split("@")[0];
	 		Long id = Long.parseLong(this.cscObject.split("@")[1]);
	 		Class<?> cls = Class.forName("com.ccclubs.model."+ClassName);
	 		return (T)cls.getMethod("get", Long.class).invoke(null, id);	
 		}catch(Exception ex){
 			ex.printStackTrace();
 			return null;
 		}
	}
	
	/*******************************评定等级**********************************/	
	/**
	* 评定等级 [非空]      
	**/
	public Short getCscLevel(){
		return this.cscLevel;
	}
	/**
	* 获取评定等级格式化(toString)
	**/
	public String getCscLevel$(){
		String strValue="";
		 if($.equals($.str(this.getCscLevel()),"3"))
			 strValue=$.str("三星");
		 if($.equals($.str(this.getCscLevel()),"2"))
			 strValue=$.str("二星");
		 if($.equals($.str(this.getCscLevel()),"1"))
			 strValue=$.str("一星");
		 if($.equals($.str(this.getCscLevel()),"0"))
			 strValue=$.str("未评分");
		 if($.equals($.str(this.getCscLevel()),"5"))
			 strValue=$.str("五星");
		 if($.equals($.str(this.getCscLevel()),"4"))
			 strValue=$.str("四星");
	 	 return strValue;
	}
	/**
	* 评定等级 [非空]     
	**/
	public void setCscLevel(Short cscLevel){
		this.cscLevel = cscLevel;
		this.setSeted(F.cscLevel);
	}
	/*******************************评定等级**********************************/	
	/**
	* 评定等级 [非空]      
	**/
	public Short getCscLevel1(){
		return this.cscLevel1;
	}
	/**
	* 获取评定等级格式化(toString)
	**/
	public String getCscLevel1$(){
		String strValue="";
		 if($.equals($.str(this.getCscLevel1()),"3"))
			 strValue=$.str("三星");
		 if($.equals($.str(this.getCscLevel1()),"2"))
			 strValue=$.str("二星");
		 if($.equals($.str(this.getCscLevel1()),"1"))
			 strValue=$.str("一星");
		 if($.equals($.str(this.getCscLevel1()),"0"))
			 strValue=$.str("未评分");
		 if($.equals($.str(this.getCscLevel1()),"5"))
			 strValue=$.str("五星");
		 if($.equals($.str(this.getCscLevel1()),"4"))
			 strValue=$.str("四星");
	 	 return strValue;
	}
	/**
	* 评定等级 [非空]     
	**/
	public void setCscLevel1(Short cscLevel1){
		this.cscLevel1 = cscLevel1;
		this.setSeted(F.cscLevel1);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsComplain.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsComplain.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsComplain.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsComplain.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsComplain.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsComplain.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsComplain.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsComplain.this);
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
		public M cscId(Object cscId){this.put("cscId", cscId);return this;};
		/** and csc_id is null */
		public M cscIdNull(){if(this.get("cscIdNot")==null)this.put("cscIdNot", "");this.put("cscId", null);return this;};
		/** not .... */
		public M cscIdNot(){this.put("cscIdNot", "not");return this;};
		/** and csc_id >= ? */
		public M cscIdMin(Object min){this.put("cscIdMin", min);return this;};
		/** and csc_id <= ? */
		public M cscIdMax(Object max){this.put("cscIdMax", max);return this;};
		/** 城市 [非空]       **/
		public M cscHost(Object cscHost){this.put("cscHost", cscHost);return this;};
		/** and csc_host is null */
		public M cscHostNull(){if(this.get("cscHostNot")==null)this.put("cscHostNot", "");this.put("cscHost", null);return this;};
		/** not .... */
		public M cscHostNot(){this.put("cscHostNot", "not");return this;};
		/** and csc_host >= ? */
		public M cscHostMin(Object min){this.put("cscHostMin", min);return this;};
		/** and csc_host <= ? */
		public M cscHostMax(Object max){this.put("cscHostMax", max);return this;};
		/** 反馈标题 [非空]       **/
		public M cscTitle(Object cscTitle){this.put("cscTitle", cscTitle);return this;};
		/** and csc_title is null */
		public M cscTitleNull(){if(this.get("cscTitleNot")==null)this.put("cscTitleNot", "");this.put("cscTitle", null);return this;};
		/** not .... */
		public M cscTitleNot(){this.put("cscTitleNot", "not");return this;};
		/** 反馈内容 [非空]       **/
		public M cscContent(Object cscContent){this.put("cscContent", cscContent);return this;};
		/** and csc_content is null */
		public M cscContentNull(){if(this.get("cscContentNot")==null)this.put("cscContentNot", "");this.put("cscContent", null);return this;};
		/** not .... */
		public M cscContentNot(){this.put("cscContentNot", "not");return this;};
		/** 类型 [非空]       **/
		public M cscType(Object cscType){this.put("cscType", cscType);return this;};
		/** and csc_type is null */
		public M cscTypeNull(){if(this.get("cscTypeNot")==null)this.put("cscTypeNot", "");this.put("cscType", null);return this;};
		/** not .... */
		public M cscTypeNot(){this.put("cscTypeNot", "not");return this;};
		/** and csc_type >= ? */
		public M cscTypeMin(Object min){this.put("cscTypeMin", min);return this;};
		/** and csc_type <= ? */
		public M cscTypeMax(Object max){this.put("cscTypeMax", max);return this;};
		/** 反馈人 [非空]       **/
		public M cscMember(Object cscMember){this.put("cscMember", cscMember);return this;};
		/** and csc_member is null */
		public M cscMemberNull(){if(this.get("cscMemberNot")==null)this.put("cscMemberNot", "");this.put("cscMember", null);return this;};
		/** not .... */
		public M cscMemberNot(){this.put("cscMemberNot", "not");return this;};
		public M cscMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("cscMember$on", value);
			return this;
		};	
		/** and csc_member >= ? */
		public M cscMemberMin(Object min){this.put("cscMemberMin", min);return this;};
		/** and csc_member <= ? */
		public M cscMemberMax(Object max){this.put("cscMemberMax", max);return this;};
		/** 处理描述 [非空]       **/
		public M cscDispose(Object cscDispose){this.put("cscDispose", cscDispose);return this;};
		/** and csc_dispose is null */
		public M cscDisposeNull(){if(this.get("cscDisposeNot")==null)this.put("cscDisposeNot", "");this.put("cscDispose", null);return this;};
		/** not .... */
		public M cscDisposeNot(){this.put("cscDisposeNot", "not");return this;};
		/** 处理人 [非空]       **/
		public M cscDealer(Object cscDealer){this.put("cscDealer", cscDealer);return this;};
		/** and csc_dealer is null */
		public M cscDealerNull(){if(this.get("cscDealerNot")==null)this.put("cscDealerNot", "");this.put("cscDealer", null);return this;};
		/** not .... */
		public M cscDealerNot(){this.put("cscDealerNot", "not");return this;};
		/** and csc_dealer >= ? */
		public M cscDealerMin(Object min){this.put("cscDealerMin", min);return this;};
		/** and csc_dealer <= ? */
		public M cscDealerMax(Object max){this.put("cscDealerMax", max);return this;};
		/** 处理时间 [非空]       **/
		public M cscUpdateTime(Object cscUpdateTime){this.put("cscUpdateTime", cscUpdateTime);return this;};
		/** and csc_update_time is null */
		public M cscUpdateTimeNull(){if(this.get("cscUpdateTimeNot")==null)this.put("cscUpdateTimeNot", "");this.put("cscUpdateTime", null);return this;};
		/** not .... */
		public M cscUpdateTimeNot(){this.put("cscUpdateTimeNot", "not");return this;};
		/** and csc_update_time >= ? */
 		public M cscUpdateTimeStart(Object start){this.put("cscUpdateTimeStart", start);return this;};			
 		/** and csc_update_time <= ? */
 		public M cscUpdateTimeEnd(Object end){this.put("cscUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cscAddTime(Object cscAddTime){this.put("cscAddTime", cscAddTime);return this;};
		/** and csc_add_time is null */
		public M cscAddTimeNull(){if(this.get("cscAddTimeNot")==null)this.put("cscAddTimeNot", "");this.put("cscAddTime", null);return this;};
		/** not .... */
		public M cscAddTimeNot(){this.put("cscAddTimeNot", "not");return this;};
		/** and csc_add_time >= ? */
 		public M cscAddTimeStart(Object start){this.put("cscAddTimeStart", start);return this;};			
 		/** and csc_add_time <= ? */
 		public M cscAddTimeEnd(Object end){this.put("cscAddTimeEnd", end);return this;};
		/** 意见采纳 [非空]       **/
		public M cscAccept(Object cscAccept){this.put("cscAccept", cscAccept);return this;};
		/** and csc_accept is null */
		public M cscAcceptNull(){if(this.get("cscAcceptNot")==null)this.put("cscAcceptNot", "");this.put("cscAccept", null);return this;};
		/** not .... */
		public M cscAcceptNot(){this.put("cscAcceptNot", "not");return this;};
		/** and csc_accept >= ? */
		public M cscAcceptMin(Object min){this.put("cscAcceptMin", min);return this;};
		/** and csc_accept <= ? */
		public M cscAcceptMax(Object max){this.put("cscAcceptMax", max);return this;};
		/** 状态 [非空]       **/
		public M cscStatus(Object cscStatus){this.put("cscStatus", cscStatus);return this;};
		/** and csc_status is null */
		public M cscStatusNull(){if(this.get("cscStatusNot")==null)this.put("cscStatusNot", "");this.put("cscStatus", null);return this;};
		/** not .... */
		public M cscStatusNot(){this.put("cscStatusNot", "not");return this;};
		/** and csc_status >= ? */
		public M cscStatusMin(Object min){this.put("cscStatusMin", min);return this;};
		/** and csc_status <= ? */
		public M cscStatusMax(Object max){this.put("cscStatusMax", max);return this;};
		/** 订单来源 [非空]       **/
		public M cscSource(Object cscSource){this.put("cscSource", cscSource);return this;};
		/** and csc_source is null */
		public M cscSourceNull(){if(this.get("cscSourceNot")==null)this.put("cscSourceNot", "");this.put("cscSource", null);return this;};
		/** not .... */
		public M cscSourceNot(){this.put("cscSourceNot", "not");return this;};
		/** 外部关联 [非空]       **/
		public M cscObject(Object cscObject){this.put("cscObject", cscObject);return this;};
		/** and csc_object is null */
		public M cscObjectNull(){if(this.get("cscObjectNot")==null)this.put("cscObjectNot", "");this.put("cscObject", null);return this;};
		/** not .... */
		public M cscObjectNot(){this.put("cscObjectNot", "not");return this;};
		/** 评定等级 [非空]       **/
		public M cscLevel(Object cscLevel){this.put("cscLevel", cscLevel);return this;};
		/** and csc_level is null */
		public M cscLevelNull(){if(this.get("cscLevelNot")==null)this.put("cscLevelNot", "");this.put("cscLevel", null);return this;};
		/** not .... */
		public M cscLevelNot(){this.put("cscLevelNot", "not");return this;};
		/** and csc_level >= ? */
		public M cscLevelMin(Object min){this.put("cscLevelMin", min);return this;};
		/** and csc_level <= ? */
		public M cscLevelMax(Object max){this.put("cscLevelMax", max);return this;};
		/** 评定等级 [非空]       **/
		public M cscLevel1(Object cscLevel1){this.put("cscLevel1", cscLevel1);return this;};
		/** and csc_level1 is null */
		public M cscLevel1Null(){if(this.get("cscLevel1Not")==null)this.put("cscLevel1Not", "");this.put("cscLevel1", null);return this;};
		/** not .... */
		public M cscLevel1Not(){this.put("cscLevel1Not", "not");return this;};
		/** and csc_level1 >= ? */
		public M cscLevel1Min(Object min){this.put("cscLevel1Min", min);return this;};
		/** and csc_level1 <= ? */
		public M cscLevel1Max(Object max){this.put("cscLevel1Max", max);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有意见反馈 **/
		public @api List<CsComplain> list(Integer size){
			return getCsComplainList(this,size);
		}
		/** 获取意见反馈分页 **/
		public @api Page<CsComplain> page(int page,int size){
			return getCsComplainPage(page, size , this);
		}
		/** 根据查询条件取意见反馈 **/
		public @api CsComplain get(){
			return getCsComplain(this);
		}
		/** 获取意见反馈数 **/
		public @api Long count(){
			return getCsComplainCount(this);
		}
		/** 获取意见反馈表达式 **/
		public @api <T> T eval(String strEval){
			return getCsComplainEval(strEval,this);
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
			updateCsComplain(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscId="cscId";
		/** 城市 [非空]       **/
		public final static @type(Long.class)  String cscHost="cscHost";
		/** 反馈标题 [非空]       **/
		public final static @type(String.class)@like  String cscTitle="cscTitle";
		/** 反馈内容 [非空]       **/
		public final static @type(String.class)@like  String cscContent="cscContent";
		/** 类型 [非空]       **/
		public final static @type(Short.class)  String cscType="cscType";
		/** 反馈人 [非空]       **/
		public final static @type(Long.class)  String cscMember="cscMember";
		/** 处理描述 [非空]       **/
		public final static @type(String.class)@like  String cscDispose="cscDispose";
		/** 处理人 [非空]       **/
		public final static @type(Long.class)  String cscDealer="cscDealer";
		/** 处理时间 [非空]       **/
		public final static @type(Date.class)  String cscUpdateTime="cscUpdateTime";
		/** and csc_update_time >= ? */
 		public final static @type(Date.class) String cscUpdateTimeStart="cscUpdateTimeStart";
 		/** and csc_update_time <= ? */
 		public final static @type(Date.class) String cscUpdateTimeEnd="cscUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cscAddTime="cscAddTime";
		/** and csc_add_time >= ? */
 		public final static @type(Date.class) String cscAddTimeStart="cscAddTimeStart";
 		/** and csc_add_time <= ? */
 		public final static @type(Date.class) String cscAddTimeEnd="cscAddTimeEnd";
		/** 意见采纳 [非空]       **/
		public final static @type(Short.class)  String cscAccept="cscAccept";
		/** 状态 [非空]       **/
		public final static @type(Short.class)  String cscStatus="cscStatus";
		/** 订单来源 [非空]       **/
		public final static @type(String.class)  String cscSource="cscSource";
		/** 外部关联 [非空]       **/
		public final static @type(String.class)  String cscObject="cscObject";
		/** 评定等级 [非空]       **/
		public final static @type(Short.class)  String cscLevel="cscLevel";
		/** 评定等级 [非空]       **/
		public final static @type(Short.class)  String cscLevel1="cscLevel1";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscId="csc_id";
		/** 城市 [非空]       **/
		public final static String cscHost="csc_host";
		/** 反馈标题 [非空]       **/
		public final static String cscTitle="csc_title";
		/** 反馈内容 [非空]       **/
		public final static String cscContent="csc_content";
		/** 类型 [非空]       **/
		public final static String cscType="csc_type";
		/** 反馈人 [非空]       **/
		public final static String cscMember="csc_member";
		/** 处理描述 [非空]       **/
		public final static String cscDispose="csc_dispose";
		/** 处理人 [非空]       **/
		public final static String cscDealer="csc_dealer";
		/** 处理时间 [非空]       **/
		public final static String cscUpdateTime="csc_update_time";
		/** 添加时间 [非空]       **/
		public final static String cscAddTime="csc_add_time";
		/** 意见采纳 [非空]       **/
		public final static String cscAccept="csc_accept";
		/** 状态 [非空]       **/
		public final static String cscStatus="csc_status";
		/** 订单来源 [非空]       **/
		public final static String cscSource="csc_source";
		/** 外部关联 [非空]       **/
		public final static String cscObject="csc_object";
		/** 评定等级 [非空]       **/
		public final static String cscLevel="csc_level";
		/** 评定等级 [非空]       **/
		public final static String cscLevel1="csc_level1";
		
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
				$.Set(name,CsComplain.getCsComplain(params));
			else
				$.Set(name,CsComplain.getCsComplainList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取意见反馈数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsComplain) $.GetRequest("CsComplain$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsComplain.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsComplain.getCsComplain(params);
			else
				value = CsComplain.getCsComplainList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsComplain.Get($.add(CsComplain.F.cscId,param));
		else if(!$.empty(param.toString()))
			value = CsComplain.get(Long.valueOf(param.toString()));
		$.SetRequest("CsComplain$"+param.hashCode(), value);
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
	public void mergeSet(CsComplain old){
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