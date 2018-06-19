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

@namespace("unit/airport")
public @caption("机场服务") @table("cs_unit_airport") class CsUnitAirport implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csua_id")   @primary  @note("  ") Long csuaId;// 主键 非空     
	private @caption("城市") @column("csua_host")    @relate("$csuaHost") @RelateClass(SrvHost.class)  @note("  ") Long csuaHost;// 非空     
 	private SrvHost $csuaHost;//关联对象[运营城市]
	private @caption("所属单位") @column("csua_unit_info")    @relate("$csuaUnitInfo") @RelateClass(CsUnitInfo.class)  @note("  ") Long csuaUnitInfo;// 非空     
 	private CsUnitInfo $csuaUnitInfo;//关联对象[企业信息]
	private @caption("类型") @column("csua_type")    @note(" 0:接机 1:送机  ") Short csuaType;// 非空 0:接机 1:送机     
	private @caption("航班号") @column("csua_flight")    @note("  ") String csuaFlight;// 非空     
	private @caption("时间") @column("csua_time")    @note("  ") Date csuaTime;// 非空     
	private @caption("联系人") @column("csua_contact")    @note("  ") String csuaContact;// 非空     
	private @caption("联系电话") @column("csua_mobile")    @note("  ") String csuaMobile;// 非空     
	private @caption("人数") @column("csua_count")    @note("  ") Integer csuaCount;// 非空     
	private @caption("结账单") @column("csua_statement")    @relate("$csuaStatement") @RelateClass(CsUnitBill.class)  @note("  ") Long csuaStatement;//     
 	private CsUnitBill $csuaStatement;//关联对象[企业对账]
	private @caption("修改时间") @column("csua_update_time")    @note("  ") Date csuaUpdateTime;// 非空     
	private @caption("添加时间") @column("csua_add_time")    @note("  ") Date csuaAddTime;// 非空     
	private @caption("备注") @column("csua_remark")    @note("  ") String csuaRemark;//     
	private @caption("状态") @column("csua_status")    @note(" 0:未处理 1:已处理 2:审核失败  ") Short csuaStatus;// 非空 0:未处理 1:已处理 2:审核失败     
	
	//默认构造函数
	public CsUnitAirport(){
	
	}
	
	//主键构造函数
	public CsUnitAirport(Long id){
		this.csuaId = id;
	}
	
	/**所有字段构造函数 CsUnitAirport(csuaHost,csuaUnitInfo,csuaType,csuaFlight,csuaTime,csuaContact,csuaMobile,csuaCount,csuaStatement,csuaUpdateTime,csuaAddTime,csuaRemark,csuaStatus)
	 CsUnitAirport(
	 	$.getLong("csuaHost")//城市 [非空]
	 	,$.getLong("csuaUnitInfo")//所属单位 [非空]
	 	,$.getShort("csuaType")//类型 [非空]
	 	,$.getString("csuaFlight")//航班号 [非空]
	 	,$.getDate("csuaTime")//时间 [非空]
	 	,$.getString("csuaContact")//联系人 [非空]
	 	,$.getString("csuaMobile")//联系电话 [非空]
	 	,$.getInteger("csuaCount")//人数 [非空]
	 	,$.getLong("csuaStatement")//结账单
	 	,$.getDate("csuaUpdateTime")//修改时间 [非空]
	 	,$.getDate("csuaAddTime")//添加时间 [非空]
	 	,$.getString("csuaRemark")//备注
	 	,$.getShort("csuaStatus")//状态 [非空]
	 )
	**/
	public CsUnitAirport(Long csuaHost,Long csuaUnitInfo,Short csuaType,String csuaFlight,Date csuaTime,String csuaContact,String csuaMobile,Integer csuaCount,Long csuaStatement,Date csuaUpdateTime,Date csuaAddTime,String csuaRemark,Short csuaStatus){
		this.csuaHost=csuaHost;
		this.csuaUnitInfo=csuaUnitInfo;
		this.csuaType=csuaType;
		this.csuaFlight=csuaFlight;
		this.csuaTime=csuaTime;
		this.csuaContact=csuaContact;
		this.csuaMobile=csuaMobile;
		this.csuaCount=csuaCount;
		this.csuaStatement=csuaStatement;
		this.csuaUpdateTime=csuaUpdateTime;
		this.csuaAddTime=csuaAddTime;
		this.csuaRemark=csuaRemark;
		this.csuaStatus=csuaStatus;
	}
	
	//设置非空字段
	public CsUnitAirport setNotNull(Long csuaHost,Long csuaUnitInfo,Short csuaType,String csuaFlight,Date csuaTime,String csuaContact,String csuaMobile,Integer csuaCount,Date csuaUpdateTime,Date csuaAddTime,Short csuaStatus){
		this.csuaHost=csuaHost;
		this.csuaUnitInfo=csuaUnitInfo;
		this.csuaType=csuaType;
		this.csuaFlight=csuaFlight;
		this.csuaTime=csuaTime;
		this.csuaContact=csuaContact;
		this.csuaMobile=csuaMobile;
		this.csuaCount=csuaCount;
		this.csuaUpdateTime=csuaUpdateTime;
		this.csuaAddTime=csuaAddTime;
		this.csuaStatus=csuaStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsUnitAirport csuaId(Long csuaId){
		this.csuaId = csuaId;
		this.setSeted(F.csuaId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsUnitAirport csuaHost(Long csuaHost){
		this.csuaHost = csuaHost;
		this.setSeted(F.csuaHost);
		return this;
	}
	/** 所属单位 [非空] [CsUnitInfo]      **/
	public CsUnitAirport csuaUnitInfo(Long csuaUnitInfo){
		this.csuaUnitInfo = csuaUnitInfo;
		this.setSeted(F.csuaUnitInfo);
		return this;
	}
	/** 类型 [非空]   0:接机 1:送机     **/
	public CsUnitAirport csuaType(Short csuaType){
		this.csuaType = csuaType;
		this.setSeted(F.csuaType);
		return this;
	}
	/** 航班号 [非空]       **/
	public CsUnitAirport csuaFlight(String csuaFlight){
		this.csuaFlight = csuaFlight;
		this.setSeted(F.csuaFlight);
		return this;
	}
	/** 时间 [非空]       **/
	public CsUnitAirport csuaTime(Date csuaTime){
		this.csuaTime = csuaTime;
		this.setSeted(F.csuaTime);
		return this;
	}
	/** 联系人 [非空]       **/
	public CsUnitAirport csuaContact(String csuaContact){
		this.csuaContact = csuaContact;
		this.setSeted(F.csuaContact);
		return this;
	}
	/** 联系电话 [非空]       **/
	public CsUnitAirport csuaMobile(String csuaMobile){
		this.csuaMobile = csuaMobile;
		this.setSeted(F.csuaMobile);
		return this;
	}
	/** 人数 [非空]       **/
	public CsUnitAirport csuaCount(Integer csuaCount){
		this.csuaCount = csuaCount;
		this.setSeted(F.csuaCount);
		return this;
	}
	/** 结账单  [CsUnitBill]      **/
	public CsUnitAirport csuaStatement(Long csuaStatement){
		this.csuaStatement = csuaStatement;
		this.setSeted(F.csuaStatement);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsUnitAirport csuaUpdateTime(Date csuaUpdateTime){
		this.csuaUpdateTime = csuaUpdateTime;
		this.setSeted(F.csuaUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsUnitAirport csuaAddTime(Date csuaAddTime){
		this.csuaAddTime = csuaAddTime;
		this.setSeted(F.csuaAddTime);
		return this;
	}
	/** 备注        **/
	public CsUnitAirport csuaRemark(String csuaRemark){
		this.csuaRemark = csuaRemark;
		this.setSeted(F.csuaRemark);
		return this;
	}
	/** 状态 [非空]   0:未处理 1:已处理 2:审核失败     **/
	public CsUnitAirport csuaStatus(Short csuaStatus){
		this.csuaStatus = csuaStatus;
		this.setSeted(F.csuaStatus);
		return this;
	}
	
	
	//克隆对象
	public CsUnitAirport clone(){
		CsUnitAirport clone = new CsUnitAirport();
		clone.csuaHost=this.csuaHost;
		clone.csuaUnitInfo=this.csuaUnitInfo;
		clone.csuaType=this.csuaType;
		clone.csuaFlight=this.csuaFlight;
		clone.csuaTime=this.csuaTime;
		clone.csuaContact=this.csuaContact;
		clone.csuaMobile=this.csuaMobile;
		clone.csuaCount=this.csuaCount;
		clone.csuaUpdateTime=this.csuaUpdateTime;
		clone.csuaAddTime=this.csuaAddTime;
		clone.csuaStatus=this.csuaStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取机场服务
	 * @param id
	 * @return
	 */
	public static @api CsUnitAirport get(Long id){		
		return getCsUnitAirportById(id);
	}
	/**
	 * 获取所有机场服务
	 * @return
	 */
	public static @api List<CsUnitAirport> list(Map params,Integer size){
		return getCsUnitAirportList(params,size);
	}
	/**
	 * 获取机场服务分页
	 * @return
	 */
	public static @api Page<CsUnitAirport> page(int page,int size,Map params){
		return getCsUnitAirportPage(page, size , params);
	}
	/**
	 * 根据查询条件取机场服务
	 * @param params
	 * @return
	 */
	public static @api CsUnitAirport Get(Map params){
		return getCsUnitAirport(params);
	}
	/**
	 * 获取机场服务数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsUnitAirportCount(params);
	}
	/**
	 * 获取机场服务数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsUnitAirportEval(eval,params);
	}
	
	/**
	 * 根据ID取机场服务
	 * @param id
	 * @return
	 */
	public static @api CsUnitAirport getCsUnitAirportById(Long id){		
		CsUnitAirport csUnitAirport = (CsUnitAirport) $.GetRequest("CsUnitAirport$"+id);
		if(csUnitAirport!=null)
			return csUnitAirport;
		com.ccclubs.service.admin.ICsUnitAirportService csUnitAirportService = $.GetSpringBean("csUnitAirportService");		
		return csUnitAirportService.getCsUnitAirportById(id);
	}
	
	
	/**
	 * 根据ID取机场服务的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsUnitAirport.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsUnitAirport csUnitAirport = get(id);
		if(csUnitAirport!=null){
			String strValue = csUnitAirport.getCsuaId$();
			if(!"CsuaId".equals("CsuaId"))
				strValue+="("+csUnitAirport.getCsuaId$()+")";
			MemCache.setValue(CsUnitAirport.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsuaId$();
		if(!"CsuaId".equals("CsuaId"))
			keyValue+="("+this.getCsuaId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有机场服务
	 * @return
	 */
	public static @api List<CsUnitAirport> getCsUnitAirportList(Map params,Integer size){
		com.ccclubs.service.admin.ICsUnitAirportService csUnitAirportService = $.GetSpringBean("csUnitAirportService");
		return csUnitAirportService.getCsUnitAirportList(params, size);
	}
	
	/**
	 * 获取机场服务分页
	 * @return
	 */
	public static @api Page<CsUnitAirport> getCsUnitAirportPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsUnitAirportService csUnitAirportService = $.GetSpringBean("csUnitAirportService");
		return csUnitAirportService.getCsUnitAirportPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取机场服务
	 * @param params
	 * @return
	 */
	public static @api CsUnitAirport getCsUnitAirport(Map params){
		com.ccclubs.service.admin.ICsUnitAirportService csUnitAirportService = $.GetSpringBean("csUnitAirportService");
		return csUnitAirportService.getCsUnitAirport(params);
	}
	
	/**
	 * 获取机场服务数
	 * @return
	 */
	public static @api Long getCsUnitAirportCount(Map params){
		com.ccclubs.service.admin.ICsUnitAirportService csUnitAirportService = $.GetSpringBean("csUnitAirportService");
		return csUnitAirportService.getCsUnitAirportCount(params);
	}
		
		
	/**
	 * 获取机场服务自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsUnitAirportEval(String eval,Map params){
		com.ccclubs.service.admin.ICsUnitAirportService csUnitAirportService = $.GetSpringBean("csUnitAirportService");
		return csUnitAirportService.getCsUnitAirportEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsUnitAirport(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsUnitAirportService csUnitAirportService = $.GetSpringBean("csUnitAirportService");
		csUnitAirportService.updateCsUnitAirportByConfirm(set, where);
	}
	
	
	/**
	 * 保存机场服务对象
	 * @param params
	 * @return
	 */
	public CsUnitAirport save(){
		com.ccclubs.service.admin.ICsUnitAirportService csUnitAirportService = $.GetSpringBean("csUnitAirportService");
		if(this.getCsuaId()!=null)
			csUnitAirportService.updateCsUnitAirport(this);
		else
			return csUnitAirportService.saveCsUnitAirport(this);
		return this;
	}
	
	
	/**
	 * 更新机场服务对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsUnitAirportService csUnitAirportService = $.GetSpringBean("csUnitAirportService");
		csUnitAirportService.updateCsUnitAirport$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsUnitAirportService csUnitAirportService = $.GetSpringBean("csUnitAirportService");
		if($.equals($.config("logic_delete"),"true"))
			csUnitAirportService.removeCsUnitAirportById(this.getCsuaId());
		else
			csUnitAirportService.deleteCsUnitAirportById(this.getCsuaId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsUnitAirportService csUnitAirportService = $.GetSpringBean("csUnitAirportService");
		return csUnitAirportService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsuaId(){
		return this.csuaId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsuaId$(){
		String strValue="";
		 strValue=$.str(this.getCsuaId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsuaId(Long csuaId){
		this.csuaId = csuaId;
		this.setSeted(F.csuaId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsuaHost(){
		return this.csuaHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsuaHost$(){
		String strValue="";
		if(this.getCsuaHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsuaHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsuaHost(Long csuaHost){
		this.csuaHost = csuaHost;
		this.setSeted(F.csuaHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csuaHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsuaHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsuaHost()!=null){
 			srvHost = SrvHost.get(this.getCsuaHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsuaHost(), srvHost);
	 	return srvHost;
	}
	/*******************************所属单位**********************************/	
	/**
	* 所属单位 [非空] [CsUnitInfo]     
	**/
	public Long getCsuaUnitInfo(){
		return this.csuaUnitInfo;
	}
	/**
	* 获取所属单位格式化(toString)
	**/
	public String getCsuaUnitInfo$(){
		String strValue="";
		if(this.getCsuaUnitInfo()!=null){
				strValue+=$.str(CsUnitInfo.getKeyValue(this.getCsuaUnitInfo()));
		}			
	 	 return strValue;
	}
	/**
	* 所属单位 [非空] [CsUnitInfo]     
	**/
	public void setCsuaUnitInfo(Long csuaUnitInfo){
		this.csuaUnitInfo = csuaUnitInfo;
		this.setSeted(F.csuaUnitInfo);
	}
	/**
	* 获取关联对象[企业信息]
	**/	 			
 	public CsUnitInfo get$csuaUnitInfo(){
 		com.ccclubs.model.CsUnitInfo csUnitInfo = (com.ccclubs.model.CsUnitInfo) $.GetRequest("CsUnitInfo$"+this.getCsuaUnitInfo());
 		if(csUnitInfo!=null)
			return csUnitInfo;
		if(this.getCsuaUnitInfo()!=null){
 			csUnitInfo = CsUnitInfo.get(this.getCsuaUnitInfo());
 		}
 		$.SetRequest("CsUnitInfo$"+this.getCsuaUnitInfo(), csUnitInfo);
	 	return csUnitInfo;
	}
	/*******************************类型**********************************/	
	/**
	* 类型 [非空]   0:接机 1:送机    
	**/
	public Short getCsuaType(){
		return this.csuaType;
	}
	/**
	* 获取类型格式化(toString)
	**/
	public String getCsuaType$(){
		String strValue="";
		 if($.equals($.str(this.getCsuaType()),"0"))
			strValue=$.str("接机");		 
		 if($.equals($.str(this.getCsuaType()),"1"))
			strValue=$.str("送机");		 
	 	 return strValue;
	}
	/**
	* 类型 [非空]   0:接机 1:送机    
	**/
	public void setCsuaType(Short csuaType){
		this.csuaType = csuaType;
		this.setSeted(F.csuaType);
	}
	/*******************************航班号**********************************/	
	/**
	* 航班号 [非空]      
	**/
	public String getCsuaFlight(){
		return this.csuaFlight;
	}
	/**
	* 获取航班号格式化(toString)
	**/
	public String getCsuaFlight$(){
		String strValue="";
		 strValue=$.str(this.getCsuaFlight());
	 	 return strValue;
	}
	/**
	* 航班号 [非空]      
	**/
	public void setCsuaFlight(String csuaFlight){
		this.csuaFlight = csuaFlight;
		this.setSeted(F.csuaFlight);
	}
	/*******************************时间**********************************/	
	/**
	* 时间 [非空]      
	**/
	public Date getCsuaTime(){
		return this.csuaTime;
	}
	/**
	* 获取时间格式化(toString)
	**/
	public String getCsuaTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsuaTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 时间 [非空]      
	**/
	public void setCsuaTime(Date csuaTime){
		this.csuaTime = csuaTime;
		this.setSeted(F.csuaTime);
	}
	/*******************************联系人**********************************/	
	/**
	* 联系人 [非空]      
	**/
	public String getCsuaContact(){
		return this.csuaContact;
	}
	/**
	* 获取联系人格式化(toString)
	**/
	public String getCsuaContact$(){
		String strValue="";
		 strValue=$.str(this.getCsuaContact());
	 	 return strValue;
	}
	/**
	* 联系人 [非空]      
	**/
	public void setCsuaContact(String csuaContact){
		this.csuaContact = csuaContact;
		this.setSeted(F.csuaContact);
	}
	/*******************************联系电话**********************************/	
	/**
	* 联系电话 [非空]      
	**/
	public String getCsuaMobile(){
		return this.csuaMobile;
	}
	/**
	* 获取联系电话格式化(toString)
	**/
	public String getCsuaMobile$(){
		String strValue="";
		 strValue=$.str(this.getCsuaMobile());
	 	 return strValue;
	}
	/**
	* 联系电话 [非空]      
	**/
	public void setCsuaMobile(String csuaMobile){
		this.csuaMobile = csuaMobile;
		this.setSeted(F.csuaMobile);
	}
	/*******************************人数**********************************/	
	/**
	* 人数 [非空]      
	**/
	public Integer getCsuaCount(){
		return this.csuaCount;
	}
	/**
	* 获取人数格式化(toString)
	**/
	public String getCsuaCount$(){
		String strValue="";
		 strValue=$.str(this.getCsuaCount());
	 	 return strValue;
	}
	/**
	* 人数 [非空]      
	**/
	public void setCsuaCount(Integer csuaCount){
		this.csuaCount = csuaCount;
		this.setSeted(F.csuaCount);
	}
	/*******************************结账单**********************************/	
	/**
	* 结账单  [CsUnitBill]     
	**/
	public Long getCsuaStatement(){
		return this.csuaStatement;
	}
	/**
	* 获取结账单格式化(toString)
	**/
	public String getCsuaStatement$(){
		String strValue="";
		if(this.getCsuaStatement()!=null){
				strValue+=$.str(CsUnitBill.getKeyValue(this.getCsuaStatement()));
		}			
	 	 return strValue;
	}
	/**
	* 结账单  [CsUnitBill]     
	**/
	public void setCsuaStatement(Long csuaStatement){
		this.csuaStatement = csuaStatement;
		this.setSeted(F.csuaStatement);
	}
	/**
	* 获取关联对象[企业对账]
	**/	 			
 	public CsUnitBill get$csuaStatement(){
 		com.ccclubs.model.CsUnitBill csUnitBill = (com.ccclubs.model.CsUnitBill) $.GetRequest("CsUnitBill$"+this.getCsuaStatement());
 		if(csUnitBill!=null)
			return csUnitBill;
		if(this.getCsuaStatement()!=null){
 			csUnitBill = CsUnitBill.get(this.getCsuaStatement());
 		}
 		$.SetRequest("CsUnitBill$"+this.getCsuaStatement(), csUnitBill);
	 	return csUnitBill;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsuaUpdateTime(){
		return this.csuaUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsuaUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsuaUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsuaUpdateTime(Date csuaUpdateTime){
		this.csuaUpdateTime = csuaUpdateTime;
		this.setSeted(F.csuaUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsuaAddTime(){
		return this.csuaAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsuaAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsuaAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsuaAddTime(Date csuaAddTime){
		this.csuaAddTime = csuaAddTime;
		this.setSeted(F.csuaAddTime);
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getCsuaRemark(){
		return this.csuaRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCsuaRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsuaRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setCsuaRemark(String csuaRemark){
		this.csuaRemark = csuaRemark;
		this.setSeted(F.csuaRemark);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:未处理 1:已处理 2:审核失败    
	**/
	public Short getCsuaStatus(){
		return this.csuaStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsuaStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsuaStatus()),"0"))
			strValue=$.str("未处理");		 
		 if($.equals($.str(this.getCsuaStatus()),"1"))
			strValue=$.str("已处理");		 
		 if($.equals($.str(this.getCsuaStatus()),"2"))
			strValue=$.str("审核失败");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:未处理 1:已处理 2:审核失败    
	**/
	public void setCsuaStatus(Short csuaStatus){
		this.csuaStatus = csuaStatus;
		this.setSeted(F.csuaStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsUnitAirport.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUnitAirport.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUnitAirport.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsUnitAirport.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsUnitAirport.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUnitAirport.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUnitAirport.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsUnitAirport.this);
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
		public M csuaId(Object csuaId){this.put("csuaId", csuaId);return this;};
	 	/** and csua_id is null */
 		public M csuaIdNull(){if(this.get("csuaIdNot")==null)this.put("csuaIdNot", "");this.put("csuaId", null);return this;};
 		/** not .... */
 		public M csuaIdNot(){this.put("csuaIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csuaHost(Object csuaHost){this.put("csuaHost", csuaHost);return this;};
	 	/** and csua_host is null */
 		public M csuaHostNull(){if(this.get("csuaHostNot")==null)this.put("csuaHostNot", "");this.put("csuaHost", null);return this;};
 		/** not .... */
 		public M csuaHostNot(){this.put("csuaHostNot", "not");return this;};
		/** 所属单位 [非空] [CsUnitInfo]      **/
		public M csuaUnitInfo(Object csuaUnitInfo){this.put("csuaUnitInfo", csuaUnitInfo);return this;};
	 	/** and csua_unit_info is null */
 		public M csuaUnitInfoNull(){if(this.get("csuaUnitInfoNot")==null)this.put("csuaUnitInfoNot", "");this.put("csuaUnitInfo", null);return this;};
 		/** not .... */
 		public M csuaUnitInfoNot(){this.put("csuaUnitInfoNot", "not");return this;};
		public M csuaUnitInfo$on(CsUnitInfo.M value){
			this.put("CsUnitInfo", value);
			this.put("csuaUnitInfo$on", value);
			return this;
		};	
		/** 类型 [非空]   0:接机 1:送机     **/
		public M csuaType(Object csuaType){this.put("csuaType", csuaType);return this;};
	 	/** and csua_type is null */
 		public M csuaTypeNull(){if(this.get("csuaTypeNot")==null)this.put("csuaTypeNot", "");this.put("csuaType", null);return this;};
 		/** not .... */
 		public M csuaTypeNot(){this.put("csuaTypeNot", "not");return this;};
		/** 航班号 [非空]       **/
		public M csuaFlight(Object csuaFlight){this.put("csuaFlight", csuaFlight);return this;};
	 	/** and csua_flight is null */
 		public M csuaFlightNull(){if(this.get("csuaFlightNot")==null)this.put("csuaFlightNot", "");this.put("csuaFlight", null);return this;};
 		/** not .... */
 		public M csuaFlightNot(){this.put("csuaFlightNot", "not");return this;};
		/** 时间 [非空]       **/
		public M csuaTime(Object csuaTime){this.put("csuaTime", csuaTime);return this;};
	 	/** and csua_time is null */
 		public M csuaTimeNull(){if(this.get("csuaTimeNot")==null)this.put("csuaTimeNot", "");this.put("csuaTime", null);return this;};
 		/** not .... */
 		public M csuaTimeNot(){this.put("csuaTimeNot", "not");return this;};
 		/** and csua_time >= ? */
 		public M csuaTimeStart(Object start){this.put("csuaTimeStart", start);return this;};			
 		/** and csua_time <= ? */
 		public M csuaTimeEnd(Object end){this.put("csuaTimeEnd", end);return this;};
		/** 联系人 [非空]       **/
		public M csuaContact(Object csuaContact){this.put("csuaContact", csuaContact);return this;};
	 	/** and csua_contact is null */
 		public M csuaContactNull(){if(this.get("csuaContactNot")==null)this.put("csuaContactNot", "");this.put("csuaContact", null);return this;};
 		/** not .... */
 		public M csuaContactNot(){this.put("csuaContactNot", "not");return this;};
		/** 联系电话 [非空]       **/
		public M csuaMobile(Object csuaMobile){this.put("csuaMobile", csuaMobile);return this;};
	 	/** and csua_mobile is null */
 		public M csuaMobileNull(){if(this.get("csuaMobileNot")==null)this.put("csuaMobileNot", "");this.put("csuaMobile", null);return this;};
 		/** not .... */
 		public M csuaMobileNot(){this.put("csuaMobileNot", "not");return this;};
		/** 人数 [非空]       **/
		public M csuaCount(Object csuaCount){this.put("csuaCount", csuaCount);return this;};
	 	/** and csua_count is null */
 		public M csuaCountNull(){if(this.get("csuaCountNot")==null)this.put("csuaCountNot", "");this.put("csuaCount", null);return this;};
 		/** not .... */
 		public M csuaCountNot(){this.put("csuaCountNot", "not");return this;};
		/** and csua_count >= ? */
		public M csuaCountMin(Object min){this.put("csuaCountMin", min);return this;};
		/** and csua_count <= ? */
		public M csuaCountMax(Object max){this.put("csuaCountMax", max);return this;};
		/** 结账单  [CsUnitBill]      **/
		public M csuaStatement(Object csuaStatement){this.put("csuaStatement", csuaStatement);return this;};
	 	/** and csua_statement is null */
 		public M csuaStatementNull(){if(this.get("csuaStatementNot")==null)this.put("csuaStatementNot", "");this.put("csuaStatement", null);return this;};
 		/** not .... */
 		public M csuaStatementNot(){this.put("csuaStatementNot", "not");return this;};
		public M csuaStatement$on(CsUnitBill.M value){
			this.put("CsUnitBill", value);
			this.put("csuaStatement$on", value);
			return this;
		};	
		/** 修改时间 [非空]       **/
		public M csuaUpdateTime(Object csuaUpdateTime){this.put("csuaUpdateTime", csuaUpdateTime);return this;};
	 	/** and csua_update_time is null */
 		public M csuaUpdateTimeNull(){if(this.get("csuaUpdateTimeNot")==null)this.put("csuaUpdateTimeNot", "");this.put("csuaUpdateTime", null);return this;};
 		/** not .... */
 		public M csuaUpdateTimeNot(){this.put("csuaUpdateTimeNot", "not");return this;};
 		/** and csua_update_time >= ? */
 		public M csuaUpdateTimeStart(Object start){this.put("csuaUpdateTimeStart", start);return this;};			
 		/** and csua_update_time <= ? */
 		public M csuaUpdateTimeEnd(Object end){this.put("csuaUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csuaAddTime(Object csuaAddTime){this.put("csuaAddTime", csuaAddTime);return this;};
	 	/** and csua_add_time is null */
 		public M csuaAddTimeNull(){if(this.get("csuaAddTimeNot")==null)this.put("csuaAddTimeNot", "");this.put("csuaAddTime", null);return this;};
 		/** not .... */
 		public M csuaAddTimeNot(){this.put("csuaAddTimeNot", "not");return this;};
 		/** and csua_add_time >= ? */
 		public M csuaAddTimeStart(Object start){this.put("csuaAddTimeStart", start);return this;};			
 		/** and csua_add_time <= ? */
 		public M csuaAddTimeEnd(Object end){this.put("csuaAddTimeEnd", end);return this;};
		/** 备注        **/
		public M csuaRemark(Object csuaRemark){this.put("csuaRemark", csuaRemark);return this;};
	 	/** and csua_remark is null */
 		public M csuaRemarkNull(){if(this.get("csuaRemarkNot")==null)this.put("csuaRemarkNot", "");this.put("csuaRemark", null);return this;};
 		/** not .... */
 		public M csuaRemarkNot(){this.put("csuaRemarkNot", "not");return this;};
		/** 状态 [非空]   0:未处理 1:已处理 2:审核失败     **/
		public M csuaStatus(Object csuaStatus){this.put("csuaStatus", csuaStatus);return this;};
	 	/** and csua_status is null */
 		public M csuaStatusNull(){if(this.get("csuaStatusNot")==null)this.put("csuaStatusNot", "");this.put("csuaStatus", null);return this;};
 		/** not .... */
 		public M csuaStatusNot(){this.put("csuaStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有机场服务 **/
		public @api List<CsUnitAirport> list(Integer size){
			return getCsUnitAirportList(this,size);
		}
		/** 获取机场服务分页 **/
		public @api Page<CsUnitAirport> page(int page,int size){
			return getCsUnitAirportPage(page, size , this);
		}
		/** 根据查询条件取机场服务 **/
		public @api CsUnitAirport get(){
			return getCsUnitAirport(this);
		}
		/** 获取机场服务数 **/
		public @api Long count(){
			return getCsUnitAirportCount(this);
		}
		/** 获取机场服务表达式 **/
		public @api <T> T eval(String strEval){
			return getCsUnitAirportEval(strEval,this);
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
			updateCsUnitAirport(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csuaId="csuaId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csuaHost="csuaHost";
		/** 所属单位 [非空] [CsUnitInfo]      **/
		public final static @type(Long.class)  String csuaUnitInfo="csuaUnitInfo";
		/** 类型 [非空]   0:接机 1:送机     **/
		public final static @type(Short.class)  String csuaType="csuaType";
		/** 航班号 [非空]       **/
		public final static @type(String.class) @like String csuaFlight="csuaFlight";
		/** 时间 [非空]       **/
		public final static @type(Date.class)  String csuaTime="csuaTime";
	 	/** and csua_time >= ? */
 		public final static @type(Date.class) String csuaTimeStart="csuaTimeStart";
 		/** and csua_time <= ? */
 		public final static @type(Date.class) String csuaTimeEnd="csuaTimeEnd";
		/** 联系人 [非空]       **/
		public final static @type(String.class) @like String csuaContact="csuaContact";
		/** 联系电话 [非空]       **/
		public final static @type(String.class) @like String csuaMobile="csuaMobile";
		/** 人数 [非空]       **/
		public final static @type(Integer.class)  String csuaCount="csuaCount";
		/** and csua_count >= ? */
		public final static @type(Integer.class) String csuaCountMin="csuaCountMin";
		/** and csua_count <= ? */
		public final static @type(Integer.class) String csuaCountMax="csuaCountMax";
		/** 结账单  [CsUnitBill]      **/
		public final static @type(Long.class)  String csuaStatement="csuaStatement";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csuaUpdateTime="csuaUpdateTime";
	 	/** and csua_update_time >= ? */
 		public final static @type(Date.class) String csuaUpdateTimeStart="csuaUpdateTimeStart";
 		/** and csua_update_time <= ? */
 		public final static @type(Date.class) String csuaUpdateTimeEnd="csuaUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csuaAddTime="csuaAddTime";
	 	/** and csua_add_time >= ? */
 		public final static @type(Date.class) String csuaAddTimeStart="csuaAddTimeStart";
 		/** and csua_add_time <= ? */
 		public final static @type(Date.class) String csuaAddTimeEnd="csuaAddTimeEnd";
		/** 备注        **/
		public final static @type(String.class) @like String csuaRemark="csuaRemark";
		/** 状态 [非空]   0:未处理 1:已处理 2:审核失败     **/
		public final static @type(Short.class)  String csuaStatus="csuaStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csuaId="csua_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csuaHost="csua_host";
		/** 所属单位 [非空] [CsUnitInfo]      **/
		public final static String csuaUnitInfo="csua_unit_info";
		/** 类型 [非空]   0:接机 1:送机     **/
		public final static String csuaType="csua_type";
		/** 航班号 [非空]       **/
		public final static String csuaFlight="csua_flight";
		/** 时间 [非空]       **/
		public final static String csuaTime="csua_time";
		/** 联系人 [非空]       **/
		public final static String csuaContact="csua_contact";
		/** 联系电话 [非空]       **/
		public final static String csuaMobile="csua_mobile";
		/** 人数 [非空]       **/
		public final static String csuaCount="csua_count";
		/** 结账单  [CsUnitBill]      **/
		public final static String csuaStatement="csua_statement";
		/** 修改时间 [非空]       **/
		public final static String csuaUpdateTime="csua_update_time";
		/** 添加时间 [非空]       **/
		public final static String csuaAddTime="csua_add_time";
		/** 备注        **/
		public final static String csuaRemark="csua_remark";
		/** 状态 [非空]   0:未处理 1:已处理 2:审核失败     **/
		public final static String csuaStatus="csua_status";
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
				$.Set(name,CsUnitAirport.getCsUnitAirport(params));
			else
				$.Set(name,CsUnitAirport.getCsUnitAirportList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取机场服务数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsUnitAirport) $.GetRequest("CsUnitAirport$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsUnitAirport.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsUnitAirport.getCsUnitAirport(params);
			else
				value = CsUnitAirport.getCsUnitAirportList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsUnitAirport.Get($.add(CsUnitAirport.F.csuaId,param));
		else if(!$.empty(param.toString()))
			value = CsUnitAirport.get(Long.valueOf(param.toString()));
		$.SetRequest("CsUnitAirport$"+param.hashCode(), value);
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
	public void mergeSet(CsUnitAirport old){
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