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

@namespace("unit/mt")
public @caption("推送消息") @table("cs_unit_mt") class CsUnitMt implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csum_id")   @primary  @note("  ") Long csumId;// 主键 非空     
	private @caption("城市") @column("csum_host")    @relate("$csumHost") @RelateClass(SrvHost.class)  @note("  ") Long csumHost;// 非空     
 	private SrvHost $csumHost;//关联对象[运营城市]
	private @caption("所属企业") @column("csum_info")    @relate("$csumInfo") @RelateClass(CsUnitInfo.class)  @note("  ") Long csumInfo;// 非空     
 	private CsUnitInfo $csumInfo;//关联对象[企业信息]
	private @caption("所属人员") @column("csum_person")    @note("  ") Long csumPerson;// 非空     
	private @caption("消息标题") @column("csum_title")    @note("  ") String csumTitle;// 非空     
	private @caption("消息内容") @column("csum_content")    @note("  ") String csumContent;// 非空     
	private @caption("消息类型") @column("csum_type")    @note(" 1:待审核消息 2:系统消息  ") Integer csumType;// 非空 1:待审核消息     
	private @caption("关联数据") @column("csum_data")    @note("  ") String csumData;// 非空    CsUnitOrder 存储格式:类名@ID,其中类名包括CsUnitOrder
	private Object $csumData;//泛关联对象，对象类名由csumData字段中标明
	private @caption("读取时间") @column("csum_read_time")    @note("  ") Date csumReadTime;//     
	private @caption("修改时间") @column("csum_update_time")    @note("  ") Date csumUpdateTime;// 非空     
	private @caption("添加时间") @column("csum_add_time")    @note("  ") Date csumAddTime;// 非空     
	private @caption("状态") @column("csum_status")    @note(" 1:已读 0:未读  ") Short csumStatus;// 非空 1:已读 0:未读     
	
	//默认构造函数
	public CsUnitMt(){
	
	}
	
	//主键构造函数
	public CsUnitMt(Long id){
		this.csumId = id;
	}
	
	/**所有字段构造函数 CsUnitMt(csumHost,csumInfo,csumPerson,csumTitle,csumContent,csumType,csumData,csumReadTime,csumUpdateTime,csumAddTime,csumStatus)
	 CsUnitMt(
	 	$.getLong("csumHost")//城市 [非空]
	 	,$.getLong("csumInfo")//所属企业 [非空]
	 	,$.getLong("csumPerson")//所属人员 [非空]
	 	,$.getString("csumTitle")//消息标题 [非空]
	 	,$.getString("csumContent")//消息内容 [非空]
	 	,$.getInteger("csumType")//消息类型 [非空]
	 	,$.getString("csumData")//关联数据 [非空]
	 	,$.getDate("csumReadTime")//读取时间
	 	,$.getDate("csumUpdateTime")//修改时间 [非空]
	 	,$.getDate("csumAddTime")//添加时间 [非空]
	 	,$.getShort("csumStatus")//状态 [非空]
	 )
	**/
	public CsUnitMt(Long csumHost,Long csumInfo,Long csumPerson,String csumTitle,String csumContent,Integer csumType,String csumData,Date csumReadTime,Date csumUpdateTime,Date csumAddTime,Short csumStatus){
		this.csumHost=csumHost;
		this.csumInfo=csumInfo;
		this.csumPerson=csumPerson;
		this.csumTitle=csumTitle;
		this.csumContent=csumContent;
		this.csumType=csumType;
		this.csumData=csumData;
		this.csumReadTime=csumReadTime;
		this.csumUpdateTime=csumUpdateTime;
		this.csumAddTime=csumAddTime;
		this.csumStatus=csumStatus;
	}
	
	//设置非空字段
	public CsUnitMt setNotNull(Long csumHost,Long csumInfo,Long csumPerson,String csumTitle,String csumContent,Integer csumType,String csumData,Date csumUpdateTime,Date csumAddTime,Short csumStatus){
		this.csumHost=csumHost;
		this.csumInfo=csumInfo;
		this.csumPerson=csumPerson;
		this.csumTitle=csumTitle;
		this.csumContent=csumContent;
		this.csumType=csumType;
		this.csumData=csumData;
		this.csumUpdateTime=csumUpdateTime;
		this.csumAddTime=csumAddTime;
		this.csumStatus=csumStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsUnitMt csumId(Long csumId){
		this.csumId = csumId;
		this.setSeted(F.csumId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsUnitMt csumHost(Long csumHost){
		this.csumHost = csumHost;
		this.setSeted(F.csumHost);
		return this;
	}
	/** 所属企业 [非空] [CsUnitInfo]      **/
	public CsUnitMt csumInfo(Long csumInfo){
		this.csumInfo = csumInfo;
		this.setSeted(F.csumInfo);
		return this;
	}
	/** 所属人员 [非空]       **/
	public CsUnitMt csumPerson(Long csumPerson){
		this.csumPerson = csumPerson;
		this.setSeted(F.csumPerson);
		return this;
	}
	/** 消息标题 [非空]       **/
	public CsUnitMt csumTitle(String csumTitle){
		this.csumTitle = csumTitle;
		this.setSeted(F.csumTitle);
		return this;
	}
	/** 消息内容 [非空]       **/
	public CsUnitMt csumContent(String csumContent){
		this.csumContent = csumContent;
		this.setSeted(F.csumContent);
		return this;
	}
	/** 消息类型 [非空]   1:待审核消息     **/
	public CsUnitMt csumType(Integer csumType){
		this.csumType = csumType;
		this.setSeted(F.csumType);
		return this;
	}
	/** 关联数据 [非空]      CsUnitOrder **/
	public CsUnitMt csumData(String csumData){
		this.csumData = csumData;
		this.setSeted(F.csumData);
		return this;
	}
	/** 读取时间        **/
	public CsUnitMt csumReadTime(Date csumReadTime){
		this.csumReadTime = csumReadTime;
		this.setSeted(F.csumReadTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsUnitMt csumUpdateTime(Date csumUpdateTime){
		this.csumUpdateTime = csumUpdateTime;
		this.setSeted(F.csumUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsUnitMt csumAddTime(Date csumAddTime){
		this.csumAddTime = csumAddTime;
		this.setSeted(F.csumAddTime);
		return this;
	}
	/** 状态 [非空]   1:已读 0:未读     **/
	public CsUnitMt csumStatus(Short csumStatus){
		this.csumStatus = csumStatus;
		this.setSeted(F.csumStatus);
		return this;
	}
	
	
	//克隆对象
	public CsUnitMt clone(){
		CsUnitMt clone = new CsUnitMt();
		clone.csumHost=this.csumHost;
		clone.csumInfo=this.csumInfo;
		clone.csumPerson=this.csumPerson;
		clone.csumTitle=this.csumTitle;
		clone.csumContent=this.csumContent;
		clone.csumType=this.csumType;
		clone.csumData=this.csumData;
		clone.csumUpdateTime=this.csumUpdateTime;
		clone.csumAddTime=this.csumAddTime;
		clone.csumStatus=this.csumStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取推送消息
	 * @param id
	 * @return
	 */
	public static @api CsUnitMt get(Long id){		
		return getCsUnitMtById(id);
	}
	/**
	 * 获取所有推送消息
	 * @return
	 */
	public static @api List<CsUnitMt> list(Map params,Integer size){
		return getCsUnitMtList(params,size);
	}
	/**
	 * 获取推送消息分页
	 * @return
	 */
	public static @api Page<CsUnitMt> page(int page,int size,Map params){
		return getCsUnitMtPage(page, size , params);
	}
	/**
	 * 根据查询条件取推送消息
	 * @param params
	 * @return
	 */
	public static @api CsUnitMt Get(Map params){
		return getCsUnitMt(params);
	}
	/**
	 * 获取推送消息数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsUnitMtCount(params);
	}
	/**
	 * 获取推送消息数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsUnitMtEval(eval,params);
	}
	
	/**
	 * 根据ID取推送消息
	 * @param id
	 * @return
	 */
	public static @api CsUnitMt getCsUnitMtById(Long id){		
		CsUnitMt csUnitMt = (CsUnitMt) $.GetRequest("CsUnitMt$"+id);
		if(csUnitMt!=null)
			return csUnitMt;
		com.ccclubs.service.admin.ICsUnitMtService csUnitMtService = $.GetSpringBean("csUnitMtService");		
		return csUnitMtService.getCsUnitMtById(id);
	}
	
	
	/**
	 * 根据ID取推送消息的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsUnitMt.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsUnitMt csUnitMt = get(id);
		if(csUnitMt!=null){
			String strValue = csUnitMt.getCsumTitle$();
			if(!"CsumTitle".equals("CsumTitle"))
				strValue+="("+csUnitMt.getCsumTitle$()+")";
			MemCache.setValue(CsUnitMt.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsumTitle$();
		if(!"CsumTitle".equals("CsumTitle"))
			keyValue+="("+this.getCsumTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有推送消息
	 * @return
	 */
	public static @api List<CsUnitMt> getCsUnitMtList(Map params,Integer size){
		com.ccclubs.service.admin.ICsUnitMtService csUnitMtService = $.GetSpringBean("csUnitMtService");
		return csUnitMtService.getCsUnitMtList(params, size);
	}
	
	/**
	 * 获取推送消息分页
	 * @return
	 */
	public static @api Page<CsUnitMt> getCsUnitMtPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsUnitMtService csUnitMtService = $.GetSpringBean("csUnitMtService");
		return csUnitMtService.getCsUnitMtPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取推送消息
	 * @param params
	 * @return
	 */
	public static @api CsUnitMt getCsUnitMt(Map params){
		com.ccclubs.service.admin.ICsUnitMtService csUnitMtService = $.GetSpringBean("csUnitMtService");
		return csUnitMtService.getCsUnitMt(params);
	}
	
	/**
	 * 获取推送消息数
	 * @return
	 */
	public static @api Long getCsUnitMtCount(Map params){
		com.ccclubs.service.admin.ICsUnitMtService csUnitMtService = $.GetSpringBean("csUnitMtService");
		return csUnitMtService.getCsUnitMtCount(params);
	}
		
		
	/**
	 * 获取推送消息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsUnitMtEval(String eval,Map params){
		com.ccclubs.service.admin.ICsUnitMtService csUnitMtService = $.GetSpringBean("csUnitMtService");
		return csUnitMtService.getCsUnitMtEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsUnitMt(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsUnitMtService csUnitMtService = $.GetSpringBean("csUnitMtService");
		csUnitMtService.updateCsUnitMtByConfirm(set, where);
	}
	
	
	/**
	 * 保存推送消息对象
	 * @param params
	 * @return
	 */
	public CsUnitMt save(){
		com.ccclubs.service.admin.ICsUnitMtService csUnitMtService = $.GetSpringBean("csUnitMtService");
		if(this.getCsumId()!=null)
			csUnitMtService.updateCsUnitMt(this);
		else
			return csUnitMtService.saveCsUnitMt(this);
		return this;
	}
	
	
	/**
	 * 更新推送消息对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsUnitMtService csUnitMtService = $.GetSpringBean("csUnitMtService");
		csUnitMtService.updateCsUnitMt$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsUnitMtService csUnitMtService = $.GetSpringBean("csUnitMtService");
		if($.equals($.config("logic_delete"),"true"))
			csUnitMtService.removeCsUnitMtById(this.getCsumId());
		else
			csUnitMtService.deleteCsUnitMtById(this.getCsumId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsUnitMtService csUnitMtService = $.GetSpringBean("csUnitMtService");
		return csUnitMtService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsumId(){
		return this.csumId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsumId$(){
		String strValue="";
		 strValue=$.str(this.getCsumId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsumId(Long csumId){
		this.csumId = csumId;
		this.setSeted(F.csumId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsumHost(){
		return this.csumHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsumHost$(){
		String strValue="";
		if(this.getCsumHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsumHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsumHost(Long csumHost){
		this.csumHost = csumHost;
		this.setSeted(F.csumHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csumHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsumHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsumHost()!=null){
 			srvHost = SrvHost.get(this.getCsumHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsumHost(), srvHost);
	 	return srvHost;
	}
	/*******************************所属企业**********************************/	
	/**
	* 所属企业 [非空] [CsUnitInfo]     
	**/
	public Long getCsumInfo(){
		return this.csumInfo;
	}
	/**
	* 获取所属企业格式化(toString)
	**/
	public String getCsumInfo$(){
		String strValue="";
		if(this.getCsumInfo()!=null){
				strValue+=$.str(CsUnitInfo.getKeyValue(this.getCsumInfo()));
		}			
	 	 return strValue;
	}
	/**
	* 所属企业 [非空] [CsUnitInfo]     
	**/
	public void setCsumInfo(Long csumInfo){
		this.csumInfo = csumInfo;
		this.setSeted(F.csumInfo);
	}
	/**
	* 获取关联对象[企业信息]
	**/	 			
 	public CsUnitInfo get$csumInfo(){
 		com.ccclubs.model.CsUnitInfo csUnitInfo = (com.ccclubs.model.CsUnitInfo) $.GetRequest("CsUnitInfo$"+this.getCsumInfo());
 		if(csUnitInfo!=null)
			return csUnitInfo;
		if(this.getCsumInfo()!=null){
 			csUnitInfo = CsUnitInfo.get(this.getCsumInfo());
 		}
 		$.SetRequest("CsUnitInfo$"+this.getCsumInfo(), csUnitInfo);
	 	return csUnitInfo;
	}
	/*******************************所属人员**********************************/	
	/**
	* 所属人员 [非空]      
	**/
	public Long getCsumPerson(){
		return this.csumPerson;
	}
	/**
	* 获取所属人员格式化(toString)
	**/
	public String getCsumPerson$(){
		String strValue="";
		 strValue=$.str(this.getCsumPerson());
	 	 return strValue;
	}
	/**
	* 所属人员 [非空]      
	**/
	public void setCsumPerson(Long csumPerson){
		this.csumPerson = csumPerson;
		this.setSeted(F.csumPerson);
	}
	/*******************************消息标题**********************************/	
	/**
	* 消息标题 [非空]      
	**/
	public String getCsumTitle(){
		return this.csumTitle;
	}
	/**
	* 获取消息标题格式化(toString)
	**/
	public String getCsumTitle$(){
		String strValue="";
		 strValue=$.str(this.getCsumTitle());
	 	 return strValue;
	}
	/**
	* 消息标题 [非空]      
	**/
	public void setCsumTitle(String csumTitle){
		this.csumTitle = csumTitle;
		this.setSeted(F.csumTitle);
	}
	/*******************************消息内容**********************************/	
	/**
	* 消息内容 [非空]      
	**/
	public String getCsumContent(){
		return this.csumContent;
	}
	/**
	* 获取消息内容格式化(toString)
	**/
	public String getCsumContent$(){
		String strValue="";
		 strValue=$.str(this.getCsumContent());
	 	 return strValue;
	}
	/**
	* 消息内容 [非空]      
	**/
	public void setCsumContent(String csumContent){
		this.csumContent = csumContent;
		this.setSeted(F.csumContent);
	}
	/*******************************消息类型**********************************/	
	/**
	* 消息类型 [非空]   1:待审核消息    
	**/
	public Integer getCsumType(){
		return this.csumType;
	}
	/**
	* 获取消息类型格式化(toString)
	**/
	public String getCsumType$(){
		String strValue="";
		if(getCsumType() == 1){
			strValue = "待审核消息";
		}
		if(getCsumType() == 2){
			strValue = "系统消息";
		}
	 	return strValue;
	}
	/**
	* 消息类型 [非空]   1:待审核消息    
	**/
	public void setCsumType(Integer csumType){
		this.csumType = csumType;
		this.setSeted(F.csumType);
	}
	/*******************************关联数据**********************************/	
	/**
	* 关联数据 [非空]      CsUnitOrder
	**/
	public String getCsumData(){
		return this.csumData;
	}
	/**
	* 获取关联数据格式化(toString)
	**/
	public String getCsumData$(){
		String strValue="";
	 	 Object object = this.get$csumData();
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
	* 关联数据 [非空]      CsUnitOrder
	**/
	public void setCsumData(String csumData){
		this.csumData = csumData;
		this.setSeted(F.csumData);
	}
	/**
	* 泛关联对象，对象类名由csumData字段中标明
	**/	 			
 	public <T> T get$csumData(){
		if($.empty(this.csumData))
 			return null;
 		try{
	 		String ClassName = this.csumData.split("@")[0];
	 		Long id = Long.parseLong(this.csumData.split("@")[1]);
	 		Class<?> cls = Class.forName("com.ccclubs.model."+ClassName);
	 		return (T)cls.getMethod("get", Long.class).invoke(null, id);	
 		}catch(Exception ex){
 			ex.printStackTrace();
 			return null;
 		}
	}
	/*******************************读取时间**********************************/	
	/**
	* 读取时间       
	**/
	public Date getCsumReadTime(){
		return this.csumReadTime;
	}
	/**
	* 获取读取时间格式化(toString)
	**/
	public String getCsumReadTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsumReadTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 读取时间       
	**/
	public void setCsumReadTime(Date csumReadTime){
		this.csumReadTime = csumReadTime;
		this.setSeted(F.csumReadTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsumUpdateTime(){
		return this.csumUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsumUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsumUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsumUpdateTime(Date csumUpdateTime){
		this.csumUpdateTime = csumUpdateTime;
		this.setSeted(F.csumUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsumAddTime(){
		return this.csumAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsumAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsumAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsumAddTime(Date csumAddTime){
		this.csumAddTime = csumAddTime;
		this.setSeted(F.csumAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:已读 0:未读    
	**/
	public Short getCsumStatus(){
		return this.csumStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsumStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsumStatus()),"1"))
			strValue=$.str("已读");		 
		 if($.equals($.str(this.getCsumStatus()),"0"))
			strValue=$.str("未读");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:已读 0:未读    
	**/
	public void setCsumStatus(Short csumStatus){
		this.csumStatus = csumStatus;
		this.setSeted(F.csumStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsUnitMt.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUnitMt.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUnitMt.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsUnitMt.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsUnitMt.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUnitMt.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUnitMt.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsUnitMt.this);
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
		public M csumId(Object csumId){this.put("csumId", csumId);return this;};
	 	/** and csum_id is null */
 		public M csumIdNull(){if(this.get("csumIdNot")==null)this.put("csumIdNot", "");this.put("csumId", null);return this;};
 		/** not .... */
 		public M csumIdNot(){this.put("csumIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csumHost(Object csumHost){this.put("csumHost", csumHost);return this;};
	 	/** and csum_host is null */
 		public M csumHostNull(){if(this.get("csumHostNot")==null)this.put("csumHostNot", "");this.put("csumHost", null);return this;};
 		/** not .... */
 		public M csumHostNot(){this.put("csumHostNot", "not");return this;};
		/** 所属企业 [非空] [CsUnitInfo]      **/
		public M csumInfo(Object csumInfo){this.put("csumInfo", csumInfo);return this;};
	 	/** and csum_info is null */
 		public M csumInfoNull(){if(this.get("csumInfoNot")==null)this.put("csumInfoNot", "");this.put("csumInfo", null);return this;};
 		/** not .... */
 		public M csumInfoNot(){this.put("csumInfoNot", "not");return this;};
		public M csumInfo$on(CsUnitInfo.M value){
			this.put("CsUnitInfo", value);
			this.put("csumInfo$on", value);
			return this;
		};	
		/** 所属人员 [非空]       **/
		public M csumPerson(Object csumPerson){this.put("csumPerson", csumPerson);return this;};
	 	/** and csum_person is null */
 		public M csumPersonNull(){if(this.get("csumPersonNot")==null)this.put("csumPersonNot", "");this.put("csumPerson", null);return this;};
 		/** not .... */
 		public M csumPersonNot(){this.put("csumPersonNot", "not");return this;};
		/** and csum_person >= ? */
		public M csumPersonMin(Object min){this.put("csumPersonMin", min);return this;};
		/** and csum_person <= ? */
		public M csumPersonMax(Object max){this.put("csumPersonMax", max);return this;};
		/** 消息标题 [非空]       **/
		public M csumTitle(Object csumTitle){this.put("csumTitle", csumTitle);return this;};
	 	/** and csum_title is null */
 		public M csumTitleNull(){if(this.get("csumTitleNot")==null)this.put("csumTitleNot", "");this.put("csumTitle", null);return this;};
 		/** not .... */
 		public M csumTitleNot(){this.put("csumTitleNot", "not");return this;};
		/** 消息内容 [非空]       **/
		public M csumContent(Object csumContent){this.put("csumContent", csumContent);return this;};
	 	/** and csum_content is null */
 		public M csumContentNull(){if(this.get("csumContentNot")==null)this.put("csumContentNot", "");this.put("csumContent", null);return this;};
 		/** not .... */
 		public M csumContentNot(){this.put("csumContentNot", "not");return this;};
		/** 消息类型 [非空]   1:待审核消息     **/
		public M csumType(Object csumType){this.put("csumType", csumType);return this;};
	 	/** and csum_type is null */
 		public M csumTypeNull(){if(this.get("csumTypeNot")==null)this.put("csumTypeNot", "");this.put("csumType", null);return this;};
 		/** not .... */
 		public M csumTypeNot(){this.put("csumTypeNot", "not");return this;};
		/** and csum_type >= ? */
		public M csumTypeMin(Object min){this.put("csumTypeMin", min);return this;};
		/** and csum_type <= ? */
		public M csumTypeMax(Object max){this.put("csumTypeMax", max);return this;};
		/** 关联数据 [非空]      CsUnitOrder **/
		public M csumData(Object csumData){this.put("csumData", csumData);return this;};
	 	/** and csum_data is null */
 		public M csumDataNull(){if(this.get("csumDataNot")==null)this.put("csumDataNot", "");this.put("csumData", null);return this;};
 		/** not .... */
 		public M csumDataNot(){this.put("csumDataNot", "not");return this;};
		/** 读取时间        **/
		public M csumReadTime(Object csumReadTime){this.put("csumReadTime", csumReadTime);return this;};
	 	/** and csum_read_time is null */
 		public M csumReadTimeNull(){if(this.get("csumReadTimeNot")==null)this.put("csumReadTimeNot", "");this.put("csumReadTime", null);return this;};
 		/** not .... */
 		public M csumReadTimeNot(){this.put("csumReadTimeNot", "not");return this;};
 		/** and csum_read_time >= ? */
 		public M csumReadTimeStart(Object start){this.put("csumReadTimeStart", start);return this;};			
 		/** and csum_read_time <= ? */
 		public M csumReadTimeEnd(Object end){this.put("csumReadTimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M csumUpdateTime(Object csumUpdateTime){this.put("csumUpdateTime", csumUpdateTime);return this;};
	 	/** and csum_update_time is null */
 		public M csumUpdateTimeNull(){if(this.get("csumUpdateTimeNot")==null)this.put("csumUpdateTimeNot", "");this.put("csumUpdateTime", null);return this;};
 		/** not .... */
 		public M csumUpdateTimeNot(){this.put("csumUpdateTimeNot", "not");return this;};
 		/** and csum_update_time >= ? */
 		public M csumUpdateTimeStart(Object start){this.put("csumUpdateTimeStart", start);return this;};			
 		/** and csum_update_time <= ? */
 		public M csumUpdateTimeEnd(Object end){this.put("csumUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csumAddTime(Object csumAddTime){this.put("csumAddTime", csumAddTime);return this;};
	 	/** and csum_add_time is null */
 		public M csumAddTimeNull(){if(this.get("csumAddTimeNot")==null)this.put("csumAddTimeNot", "");this.put("csumAddTime", null);return this;};
 		/** not .... */
 		public M csumAddTimeNot(){this.put("csumAddTimeNot", "not");return this;};
 		/** and csum_add_time >= ? */
 		public M csumAddTimeStart(Object start){this.put("csumAddTimeStart", start);return this;};			
 		/** and csum_add_time <= ? */
 		public M csumAddTimeEnd(Object end){this.put("csumAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:已读 0:未读     **/
		public M csumStatus(Object csumStatus){this.put("csumStatus", csumStatus);return this;};
	 	/** and csum_status is null */
 		public M csumStatusNull(){if(this.get("csumStatusNot")==null)this.put("csumStatusNot", "");this.put("csumStatus", null);return this;};
 		/** not .... */
 		public M csumStatusNot(){this.put("csumStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有推送消息 **/
		public @api List<CsUnitMt> list(Integer size){
			return getCsUnitMtList(this,size);
		}
		/** 获取推送消息分页 **/
		public @api Page<CsUnitMt> page(int page,int size){
			return getCsUnitMtPage(page, size , this);
		}
		/** 根据查询条件取推送消息 **/
		public @api CsUnitMt get(){
			return getCsUnitMt(this);
		}
		/** 获取推送消息数 **/
		public @api Long count(){
			return getCsUnitMtCount(this);
		}
		/** 获取推送消息表达式 **/
		public @api <T> T eval(String strEval){
			return getCsUnitMtEval(strEval,this);
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
			updateCsUnitMt(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csumId="csumId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csumHost="csumHost";
		/** 所属企业 [非空] [CsUnitInfo]      **/
		public final static @type(Long.class)  String csumInfo="csumInfo";
		/** 所属人员 [非空]       **/
		public final static @type(Long.class)  String csumPerson="csumPerson";
		/** and csum_person >= ? */
		public final static @type(Long.class) String csumPersonMin="csumPersonMin";
		/** and csum_person <= ? */
		public final static @type(Long.class) String csumPersonMax="csumPersonMax";
		/** 消息标题 [非空]       **/
		public final static @type(String.class) @like String csumTitle="csumTitle";
		/** 消息内容 [非空]       **/
		public final static @type(String.class) @like String csumContent="csumContent";
		/** 消息类型 [非空]   1:待审核消息     **/
		public final static @type(Integer.class)  String csumType="csumType";
		/** and csum_type >= ? */
		public final static @type(Integer.class) String csumTypeMin="csumTypeMin";
		/** and csum_type <= ? */
		public final static @type(Integer.class) String csumTypeMax="csumTypeMax";
		/** 关联数据 [非空]      CsUnitOrder **/
		public final static @type(String.class)  String csumData="csumData";
		/** 读取时间        **/
		public final static @type(Date.class)  String csumReadTime="csumReadTime";
	 	/** and csum_read_time >= ? */
 		public final static @type(Date.class) String csumReadTimeStart="csumReadTimeStart";
 		/** and csum_read_time <= ? */
 		public final static @type(Date.class) String csumReadTimeEnd="csumReadTimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csumUpdateTime="csumUpdateTime";
	 	/** and csum_update_time >= ? */
 		public final static @type(Date.class) String csumUpdateTimeStart="csumUpdateTimeStart";
 		/** and csum_update_time <= ? */
 		public final static @type(Date.class) String csumUpdateTimeEnd="csumUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csumAddTime="csumAddTime";
	 	/** and csum_add_time >= ? */
 		public final static @type(Date.class) String csumAddTimeStart="csumAddTimeStart";
 		/** and csum_add_time <= ? */
 		public final static @type(Date.class) String csumAddTimeEnd="csumAddTimeEnd";
		/** 状态 [非空]   1:已读 0:未读     **/
		public final static @type(Short.class)  String csumStatus="csumStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csumId="csum_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csumHost="csum_host";
		/** 所属企业 [非空] [CsUnitInfo]      **/
		public final static String csumInfo="csum_info";
		/** 所属人员 [非空]       **/
		public final static String csumPerson="csum_person";
		/** 消息标题 [非空]       **/
		public final static String csumTitle="csum_title";
		/** 消息内容 [非空]       **/
		public final static String csumContent="csum_content";
		/** 消息类型 [非空]   1:待审核消息     **/
		public final static String csumType="csum_type";
		/** 关联数据 [非空]      CsUnitOrder **/
		public final static String csumData="csum_data";
		/** 读取时间        **/
		public final static String csumReadTime="csum_read_time";
		/** 修改时间 [非空]       **/
		public final static String csumUpdateTime="csum_update_time";
		/** 添加时间 [非空]       **/
		public final static String csumAddTime="csum_add_time";
		/** 状态 [非空]   1:已读 0:未读     **/
		public final static String csumStatus="csum_status";
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
				$.Set(name,CsUnitMt.getCsUnitMt(params));
			else
				$.Set(name,CsUnitMt.getCsUnitMtList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取推送消息数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsUnitMt) $.GetRequest("CsUnitMt$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsUnitMt.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsUnitMt.getCsUnitMt(params);
			else
				value = CsUnitMt.getCsUnitMtList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsUnitMt.Get($.add(CsUnitMt.F.csumId,param));
		else if(!$.empty(param.toString()))
			value = CsUnitMt.get(Long.valueOf(param.toString()));
		$.SetRequest("CsUnitMt$"+param.hashCode(), value);
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
	public void mergeSet(CsUnitMt old){
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