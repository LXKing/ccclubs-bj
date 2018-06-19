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

@namespace("oa/outside/outsideqr")
public @caption("巡检作业") @table("cs_outside_qr") class CsOutsideQr implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csoq_id")   @primary  @note("  ") Long csoqId;// 主键 非空     
	private @caption("城市") @column("csoq_host")    @relate("$csoqHost") @RelateClass(SrvHost.class)  @note("  ") Long csoqHost;// 非空     
 	private SrvHost $csoqHost;//关联对象[运营城市]
	private @caption("人员") @column("csoq_adder")    @relate("$csoqAdder") @RelateClass(SrvUser.class)  @note("  ") Long csoqAdder;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csoqAdder;//关联对象[用户]
	private @caption("车辆") @column("csoq_car")    @relate("$csoqCar") @RelateClass(CsCar.class)  @note("  ") Long csoqCar;// 非空     
 	private CsCar $csoqCar;//关联对象[车辆]
	private @caption("作业类型") @column("csoq_type")    @relate("$csoqType") @RelateClass(SrvProperty.class)  @note(" outsideqr_type:巡检作业类型  ") Long csoqType;// 非空 outsideqr_type:巡检作业类型     
 	private SrvProperty $csoqType;//关联对象[系统属性]
	private @caption("人员位置") @column("csoq_user_pos_start")    @note("  ") String csoqUserPosStart;//(单位：开始) 非空     
	private @caption("车辆位置") @column("csoq_car_pos_start")    @note("  ") String csoqCarPosStart;//(单位：开始) 非空     
	private @caption("人车距离") @column("csoq_distance_start")    @note("  ") Integer csoqDistanceStart;//(单位：米/开始) 非空     
	private @caption("人员位置") @column("csoq_use_pos_end")    @note("  ") String csoqUsePosEnd;//(单位：完成)     
	private @caption("车辆位置") @column("csoq_car_pos_end")    @note("  ") String csoqCarPosEnd;//(单位：完成)     
	private @caption("人车距离") @column("csoq_distance_end")    @note("  ") Integer csoqDistanceEnd;//(单位：米/完成)     
	private @caption("图片列表") @column("csoq_images")  @hidden   @note("  ") String csoqImages;//     
	private @caption("开始时间") @column("csoq_add_time")    @note("  ") Date csoqAddTime;// 非空     
	private @caption("结束时间") @column("csoq_end_time")    @note("  ") Date csoqEndTime;//     
	private @caption("作业时长") @column("csoq_time_long")    @note("  ") Integer csoqTimeLong;//(单位：分钟)     
	private @caption("备注信息") @column("csoq_remark")    @note("  ") String csoqRemark;//     
	private @caption("状态") @column("csoq_status")    @note(" 1:正常 0:无效  ") Short csoqStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsOutsideQr(){
	
	}
	
	//主键构造函数
	public CsOutsideQr(Long id){
		this.csoqId = id;
	}
	
	/**所有字段构造函数 CsOutsideQr(csoqHost,csoqAdder,csoqCar,csoqType,csoqUserPosStart,csoqCarPosStart,csoqDistanceStart,csoqUsePosEnd,csoqCarPosEnd,csoqDistanceEnd,csoqImages,csoqAddTime,csoqEndTime,csoqTimeLong,csoqRemark,csoqStatus)
	 CsOutsideQr(
	 	$.getLong("csoqHost")//城市 [非空]
	 	,$.getLong("csoqAdder")//人员 [非空]
	 	,$.getLong("csoqCar")//车辆 [非空]
	 	,$.getLong("csoqType")//作业类型 [非空]
	 	,$.getString("csoqUserPosStart")//人员位置 [非空]
	 	,$.getString("csoqCarPosStart")//车辆位置 [非空]
	 	,$.getInteger("csoqDistanceStart")//人车距离 [非空]
	 	,$.getString("csoqUsePosEnd")//人员位置
	 	,$.getString("csoqCarPosEnd")//车辆位置
	 	,$.getInteger("csoqDistanceEnd")//人车距离
	 	,$.getString("csoqImages")//图片列表
	 	,$.getDate("csoqAddTime")//开始时间 [非空]
	 	,$.getDate("csoqEndTime")//结束时间
	 	,$.getInteger("csoqTimeLong")//作业时长
	 	,$.getString("csoqRemark")//备注信息
	 	,$.getShort("csoqStatus")//状态 [非空]
	 )
	**/
	public CsOutsideQr(Long csoqHost,Long csoqAdder,Long csoqCar,Long csoqType,String csoqUserPosStart,String csoqCarPosStart,Integer csoqDistanceStart,String csoqUsePosEnd,String csoqCarPosEnd,Integer csoqDistanceEnd,String csoqImages,Date csoqAddTime,Date csoqEndTime,Integer csoqTimeLong,String csoqRemark,Short csoqStatus){
		this.csoqHost=csoqHost;
		this.csoqAdder=csoqAdder;
		this.csoqCar=csoqCar;
		this.csoqType=csoqType;
		this.csoqUserPosStart=csoqUserPosStart;
		this.csoqCarPosStart=csoqCarPosStart;
		this.csoqDistanceStart=csoqDistanceStart;
		this.csoqUsePosEnd=csoqUsePosEnd;
		this.csoqCarPosEnd=csoqCarPosEnd;
		this.csoqDistanceEnd=csoqDistanceEnd;
		this.csoqImages=csoqImages;
		this.csoqAddTime=csoqAddTime;
		this.csoqEndTime=csoqEndTime;
		this.csoqTimeLong=csoqTimeLong;
		this.csoqRemark=csoqRemark;
		this.csoqStatus=csoqStatus;
	}
	
	//设置非空字段
	public CsOutsideQr setNotNull(Long csoqHost,Long csoqAdder,Long csoqCar,Long csoqType,String csoqUserPosStart,String csoqCarPosStart,Integer csoqDistanceStart,Date csoqAddTime,Short csoqStatus){
		this.csoqHost=csoqHost;
		this.csoqAdder=csoqAdder;
		this.csoqCar=csoqCar;
		this.csoqType=csoqType;
		this.csoqUserPosStart=csoqUserPosStart;
		this.csoqCarPosStart=csoqCarPosStart;
		this.csoqDistanceStart=csoqDistanceStart;
		this.csoqAddTime=csoqAddTime;
		this.csoqStatus=csoqStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsOutsideQr csoqId(Long csoqId){
		this.csoqId = csoqId;
		this.setSeted(F.csoqId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsOutsideQr csoqHost(Long csoqHost){
		this.csoqHost = csoqHost;
		this.setSeted(F.csoqHost);
		return this;
	}
	/** 人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsOutsideQr csoqAdder(Long csoqAdder){
		this.csoqAdder = csoqAdder;
		this.setSeted(F.csoqAdder);
		return this;
	}
	/** 车辆 [非空] [CsCar]      **/
	public CsOutsideQr csoqCar(Long csoqCar){
		this.csoqCar = csoqCar;
		this.setSeted(F.csoqCar);
		return this;
	}
	/** 作业类型 [非空] [SrvProperty]  outsideqr_type:巡检作业类型     **/
	public CsOutsideQr csoqType(Long csoqType){
		this.csoqType = csoqType;
		this.setSeted(F.csoqType);
		return this;
	}
	/** 人员位置 [非空]       **/
	public CsOutsideQr csoqUserPosStart(String csoqUserPosStart){
		this.csoqUserPosStart = csoqUserPosStart;
		this.setSeted(F.csoqUserPosStart);
		return this;
	}
	/** 车辆位置 [非空]       **/
	public CsOutsideQr csoqCarPosStart(String csoqCarPosStart){
		this.csoqCarPosStart = csoqCarPosStart;
		this.setSeted(F.csoqCarPosStart);
		return this;
	}
	/** 人车距离 [非空]       **/
	public CsOutsideQr csoqDistanceStart(Integer csoqDistanceStart){
		this.csoqDistanceStart = csoqDistanceStart;
		this.setSeted(F.csoqDistanceStart);
		return this;
	}
	/** 人员位置        **/
	public CsOutsideQr csoqUsePosEnd(String csoqUsePosEnd){
		this.csoqUsePosEnd = csoqUsePosEnd;
		this.setSeted(F.csoqUsePosEnd);
		return this;
	}
	/** 车辆位置        **/
	public CsOutsideQr csoqCarPosEnd(String csoqCarPosEnd){
		this.csoqCarPosEnd = csoqCarPosEnd;
		this.setSeted(F.csoqCarPosEnd);
		return this;
	}
	/** 人车距离        **/
	public CsOutsideQr csoqDistanceEnd(Integer csoqDistanceEnd){
		this.csoqDistanceEnd = csoqDistanceEnd;
		this.setSeted(F.csoqDistanceEnd);
		return this;
	}
	/** 图片列表        **/
	public CsOutsideQr csoqImages(String csoqImages){
		this.csoqImages = csoqImages;
		this.setSeted(F.csoqImages);
		return this;
	}
	/** 开始时间 [非空]       **/
	public CsOutsideQr csoqAddTime(Date csoqAddTime){
		this.csoqAddTime = csoqAddTime;
		this.setSeted(F.csoqAddTime);
		return this;
	}
	/** 结束时间        **/
	public CsOutsideQr csoqEndTime(Date csoqEndTime){
		this.csoqEndTime = csoqEndTime;
		this.setSeted(F.csoqEndTime);
		return this;
	}
	/** 作业时长        **/
	public CsOutsideQr csoqTimeLong(Integer csoqTimeLong){
		this.csoqTimeLong = csoqTimeLong;
		this.setSeted(F.csoqTimeLong);
		return this;
	}
	/** 备注信息        **/
	public CsOutsideQr csoqRemark(String csoqRemark){
		this.csoqRemark = csoqRemark;
		this.setSeted(F.csoqRemark);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsOutsideQr csoqStatus(Short csoqStatus){
		this.csoqStatus = csoqStatus;
		this.setSeted(F.csoqStatus);
		return this;
	}
	
	
	//克隆对象
	public CsOutsideQr clone(){
		CsOutsideQr clone = new CsOutsideQr();
		clone.csoqHost=this.csoqHost;
		clone.csoqAdder=this.csoqAdder;
		clone.csoqCar=this.csoqCar;
		clone.csoqType=this.csoqType;
		clone.csoqUserPosStart=this.csoqUserPosStart;
		clone.csoqCarPosStart=this.csoqCarPosStart;
		clone.csoqDistanceStart=this.csoqDistanceStart;
		clone.csoqAddTime=this.csoqAddTime;
		clone.csoqStatus=this.csoqStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取巡检作业
	 * @param id
	 * @return
	 */
	public static @api CsOutsideQr get(Long id){		
		return getCsOutsideQrById(id);
	}
	/**
	 * 获取所有巡检作业
	 * @return
	 */
	public static @api List<CsOutsideQr> list(Map params,Integer size){
		return getCsOutsideQrList(params,size);
	}
	/**
	 * 获取巡检作业分页
	 * @return
	 */
	public static @api Page<CsOutsideQr> page(int page,int size,Map params){
		return getCsOutsideQrPage(page, size , params);
	}
	/**
	 * 根据查询条件取巡检作业
	 * @param params
	 * @return
	 */
	public static @api CsOutsideQr Get(Map params){
		return getCsOutsideQr(params);
	}
	/**
	 * 获取巡检作业数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsOutsideQrCount(params);
	}
	/**
	 * 获取巡检作业数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsOutsideQrEval(eval,params);
	}
	
	/**
	 * 根据ID取巡检作业
	 * @param id
	 * @return
	 */
	public static @api CsOutsideQr getCsOutsideQrById(Long id){		
		CsOutsideQr csOutsideQr = (CsOutsideQr) $.GetRequest("CsOutsideQr$"+id);
		if(csOutsideQr!=null)
			return csOutsideQr;
		com.ccclubs.service.admin.ICsOutsideQrService csOutsideQrService = $.GetSpringBean("csOutsideQrService");		
		return csOutsideQrService.getCsOutsideQrById(id);
	}
	
	
	/**
	 * 根据ID取巡检作业的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsOutsideQr.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsOutsideQr csOutsideQr = get(id);
		if(csOutsideQr!=null){
			String strValue = csOutsideQr.getCsoqId$();
			if(!"CsoqId".equals("CsoqId"))
				strValue+="("+csOutsideQr.getCsoqId$()+")";
			MemCache.setValue(CsOutsideQr.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsoqId$();
		if(!"CsoqId".equals("CsoqId"))
			keyValue+="("+this.getCsoqId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有巡检作业
	 * @return
	 */
	public static @api List<CsOutsideQr> getCsOutsideQrList(Map params,Integer size){
		com.ccclubs.service.admin.ICsOutsideQrService csOutsideQrService = $.GetSpringBean("csOutsideQrService");
		return csOutsideQrService.getCsOutsideQrList(params, size);
	}
	
	/**
	 * 获取巡检作业分页
	 * @return
	 */
	public static @api Page<CsOutsideQr> getCsOutsideQrPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsOutsideQrService csOutsideQrService = $.GetSpringBean("csOutsideQrService");
		return csOutsideQrService.getCsOutsideQrPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取巡检作业
	 * @param params
	 * @return
	 */
	public static @api CsOutsideQr getCsOutsideQr(Map params){
		com.ccclubs.service.admin.ICsOutsideQrService csOutsideQrService = $.GetSpringBean("csOutsideQrService");
		return csOutsideQrService.getCsOutsideQr(params);
	}
	
	/**
	 * 获取巡检作业数
	 * @return
	 */
	public static @api Long getCsOutsideQrCount(Map params){
		com.ccclubs.service.admin.ICsOutsideQrService csOutsideQrService = $.GetSpringBean("csOutsideQrService");
		return csOutsideQrService.getCsOutsideQrCount(params);
	}
		
		
	/**
	 * 获取巡检作业自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsOutsideQrEval(String eval,Map params){
		com.ccclubs.service.admin.ICsOutsideQrService csOutsideQrService = $.GetSpringBean("csOutsideQrService");
		return csOutsideQrService.getCsOutsideQrEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsOutsideQr(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsOutsideQrService csOutsideQrService = $.GetSpringBean("csOutsideQrService");
		csOutsideQrService.updateCsOutsideQrByConfirm(set, where);
	}
	
	
	/**
	 * 保存巡检作业对象
	 * @param params
	 * @return
	 */
	public CsOutsideQr save(){
		com.ccclubs.service.admin.ICsOutsideQrService csOutsideQrService = $.GetSpringBean("csOutsideQrService");
		if(this.getCsoqId()!=null)
			csOutsideQrService.updateCsOutsideQr(this);
		else
			return csOutsideQrService.saveCsOutsideQr(this);
		return this;
	}
	
	
	/**
	 * 更新巡检作业对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsOutsideQrService csOutsideQrService = $.GetSpringBean("csOutsideQrService");
		csOutsideQrService.updateCsOutsideQr$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsOutsideQrService csOutsideQrService = $.GetSpringBean("csOutsideQrService");
		if($.equals($.config("logic_delete"),"true"))
			csOutsideQrService.removeCsOutsideQrById(this.getCsoqId());
		else
			csOutsideQrService.deleteCsOutsideQrById(this.getCsoqId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsOutsideQrService csOutsideQrService = $.GetSpringBean("csOutsideQrService");
		return csOutsideQrService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsoqId(){
		return this.csoqId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsoqId$(){
		String strValue="";
		 strValue=$.str(this.getCsoqId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsoqId(Long csoqId){
		this.csoqId = csoqId;
		this.setSeted(F.csoqId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsoqHost(){
		return this.csoqHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsoqHost$(){
		String strValue="";
		if(this.getCsoqHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsoqHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsoqHost(Long csoqHost){
		this.csoqHost = csoqHost;
		this.setSeted(F.csoqHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csoqHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsoqHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsoqHost()!=null){
 			srvHost = SrvHost.get(this.getCsoqHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsoqHost(), srvHost);
	 	return srvHost;
	}
	/*******************************人员**********************************/	
	/**
	* 人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsoqAdder(){
		return this.csoqAdder;
	}
	/**
	* 获取人员格式化(toString)
	**/
	public String getCsoqAdder$(){
		String strValue="";
		if(this.getCsoqAdder()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsoqAdder()));
		}			
	 	 return strValue;
	}
	/**
	* 人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsoqAdder(Long csoqAdder){
		this.csoqAdder = csoqAdder;
		this.setSeted(F.csoqAdder);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csoqAdder(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsoqAdder());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsoqAdder()!=null){
 			srvUser = SrvUser.get(this.getCsoqAdder());
 		}
 		$.SetRequest("SrvUser$"+this.getCsoqAdder(), srvUser);
	 	return srvUser;
	}
	/*******************************车辆**********************************/	
	/**
	* 车辆 [非空] [CsCar]     
	**/
	public Long getCsoqCar(){
		return this.csoqCar;
	}
	/**
	* 获取车辆格式化(toString)
	**/
	public String getCsoqCar$(){
		String strValue="";
		if(this.getCsoqCar()!=null){
				strValue+=$.str(CsCar.getKeyValue(this.getCsoqCar()));
		}			
	 	 return strValue;
	}
	/**
	* 车辆 [非空] [CsCar]     
	**/
	public void setCsoqCar(Long csoqCar){
		this.csoqCar = csoqCar;
		this.setSeted(F.csoqCar);
	}
	/**
	* 获取关联对象[车辆]
	**/	 			
 	public CsCar get$csoqCar(){
 		com.ccclubs.model.CsCar csCar = (com.ccclubs.model.CsCar) $.GetRequest("CsCar$"+this.getCsoqCar());
 		if(csCar!=null)
			return csCar;
		if(this.getCsoqCar()!=null){
 			csCar = CsCar.get(this.getCsoqCar());
 		}
 		$.SetRequest("CsCar$"+this.getCsoqCar(), csCar);
	 	return csCar;
	}
	/*******************************作业类型**********************************/	
	/**
	* 作业类型 [非空] [SrvProperty]  outsideqr_type:巡检作业类型    
	**/
	public Long getCsoqType(){
		return this.csoqType;
	}
	/**
	* 获取作业类型格式化(toString)
	**/
	public String getCsoqType$(){
		String strValue="";
		if(this.getCsoqType()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCsoqType()));
		}			
	 	 return strValue;
	}
	/**
	* 作业类型 [非空] [SrvProperty]  outsideqr_type:巡检作业类型    
	**/
	public void setCsoqType(Long csoqType){
		this.csoqType = csoqType;
		this.setSeted(F.csoqType);
	}
	/**
	* 获取关联对象[系统属性]
	**/	 			
 	public SrvProperty get$csoqType(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCsoqType());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCsoqType()!=null){
 			srvProperty = SrvProperty.get(this.getCsoqType());
 		}
 		$.SetRequest("SrvProperty$"+this.getCsoqType(), srvProperty);
	 	return srvProperty;
	}
	/*******************************人员位置**********************************/	
	/**
	* 人员位置 [非空]      
	**/
	public String getCsoqUserPosStart(){
		return this.csoqUserPosStart;
	}
	/**
	* 获取人员位置格式化(toString)
	**/
	public String getCsoqUserPosStart$(){
		String strValue="";
		 strValue=$.str(this.getCsoqUserPosStart());
	 	 return strValue;
	}
	/**
	* 人员位置 [非空]      
	**/
	public void setCsoqUserPosStart(String csoqUserPosStart){
		this.csoqUserPosStart = csoqUserPosStart;
		this.setSeted(F.csoqUserPosStart);
	}
	/*******************************车辆位置**********************************/	
	/**
	* 车辆位置 [非空]      
	**/
	public String getCsoqCarPosStart(){
		return this.csoqCarPosStart;
	}
	/**
	* 获取车辆位置格式化(toString)
	**/
	public String getCsoqCarPosStart$(){
		String strValue="";
		 strValue=$.str(this.getCsoqCarPosStart());
	 	 return strValue;
	}
	/**
	* 车辆位置 [非空]      
	**/
	public void setCsoqCarPosStart(String csoqCarPosStart){
		this.csoqCarPosStart = csoqCarPosStart;
		this.setSeted(F.csoqCarPosStart);
	}
	/*******************************人车距离**********************************/	
	/**
	* 人车距离 [非空]      
	**/
	public Integer getCsoqDistanceStart(){
		return this.csoqDistanceStart;
	}
	/**
	* 获取人车距离格式化(toString)
	**/
	public String getCsoqDistanceStart$(){
		String strValue="";
		 strValue=$.str(this.getCsoqDistanceStart());
	 	 return strValue;
	}
	/**
	* 人车距离 [非空]      
	**/
	public void setCsoqDistanceStart(Integer csoqDistanceStart){
		this.csoqDistanceStart = csoqDistanceStart;
		this.setSeted(F.csoqDistanceStart);
	}
	/*******************************人员位置**********************************/	
	/**
	* 人员位置       
	**/
	public String getCsoqUsePosEnd(){
		return this.csoqUsePosEnd;
	}
	/**
	* 获取人员位置格式化(toString)
	**/
	public String getCsoqUsePosEnd$(){
		String strValue="";
		 strValue=$.str(this.getCsoqUsePosEnd());
	 	 return strValue;
	}
	/**
	* 人员位置       
	**/
	public void setCsoqUsePosEnd(String csoqUsePosEnd){
		this.csoqUsePosEnd = csoqUsePosEnd;
		this.setSeted(F.csoqUsePosEnd);
	}
	/*******************************车辆位置**********************************/	
	/**
	* 车辆位置       
	**/
	public String getCsoqCarPosEnd(){
		return this.csoqCarPosEnd;
	}
	/**
	* 获取车辆位置格式化(toString)
	**/
	public String getCsoqCarPosEnd$(){
		String strValue="";
		 strValue=$.str(this.getCsoqCarPosEnd());
	 	 return strValue;
	}
	/**
	* 车辆位置       
	**/
	public void setCsoqCarPosEnd(String csoqCarPosEnd){
		this.csoqCarPosEnd = csoqCarPosEnd;
		this.setSeted(F.csoqCarPosEnd);
	}
	/*******************************人车距离**********************************/	
	/**
	* 人车距离       
	**/
	public Integer getCsoqDistanceEnd(){
		return this.csoqDistanceEnd;
	}
	/**
	* 获取人车距离格式化(toString)
	**/
	public String getCsoqDistanceEnd$(){
		String strValue="";
		 strValue=$.str(this.getCsoqDistanceEnd());
	 	 return strValue;
	}
	/**
	* 人车距离       
	**/
	public void setCsoqDistanceEnd(Integer csoqDistanceEnd){
		this.csoqDistanceEnd = csoqDistanceEnd;
		this.setSeted(F.csoqDistanceEnd);
	}
	/*******************************图片列表**********************************/	
	/**
	* 图片列表       
	**/
	public String getCsoqImages(){
		return this.csoqImages;
	}
	/**
	* 获取图片列表格式化(toString)
	**/
	public String getCsoqImages$(){
		String strValue="";
		 strValue=$.str(this.getCsoqImages());
	 	 return strValue;
	}
	/**
	* 图片列表       
	**/
	public void setCsoqImages(String csoqImages){
		this.csoqImages = csoqImages;
		this.setSeted(F.csoqImages);
	}
	/*******************************开始时间**********************************/	
	/**
	* 开始时间 [非空]      
	**/
	public Date getCsoqAddTime(){
		return this.csoqAddTime;
	}
	/**
	* 获取开始时间格式化(toString)
	**/
	public String getCsoqAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsoqAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 开始时间 [非空]      
	**/
	public void setCsoqAddTime(Date csoqAddTime){
		this.csoqAddTime = csoqAddTime;
		this.setSeted(F.csoqAddTime);
	}
	/*******************************结束时间**********************************/	
	/**
	* 结束时间       
	**/
	public Date getCsoqEndTime(){
		return this.csoqEndTime;
	}
	/**
	* 获取结束时间格式化(toString)
	**/
	public String getCsoqEndTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsoqEndTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 结束时间       
	**/
	public void setCsoqEndTime(Date csoqEndTime){
		this.csoqEndTime = csoqEndTime;
		this.setSeted(F.csoqEndTime);
	}
	/*******************************作业时长**********************************/	
	/**
	* 作业时长       
	**/
	public Integer getCsoqTimeLong(){
		return this.csoqTimeLong;
	}
	/**
	* 获取作业时长格式化(toString)
	**/
	public String getCsoqTimeLong$(){
		String strValue="";
		 strValue=$.str(this.getCsoqTimeLong());
	 	 return strValue;
	}
	/**
	* 作业时长       
	**/
	public void setCsoqTimeLong(Integer csoqTimeLong){
		this.csoqTimeLong = csoqTimeLong;
		this.setSeted(F.csoqTimeLong);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息       
	**/
	public String getCsoqRemark(){
		return this.csoqRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCsoqRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsoqRemark());
	 	 return strValue;
	}
	/**
	* 备注信息       
	**/
	public void setCsoqRemark(String csoqRemark){
		this.csoqRemark = csoqRemark;
		this.setSeted(F.csoqRemark);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsoqStatus(){
		return this.csoqStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsoqStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsoqStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsoqStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsoqStatus(Short csoqStatus){
		this.csoqStatus = csoqStatus;
		this.setSeted(F.csoqStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsOutsideQr.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsOutsideQr.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsOutsideQr.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsOutsideQr.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsOutsideQr.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsOutsideQr.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsOutsideQr.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsOutsideQr.this);
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
		public M csoqId(Object csoqId){this.put("csoqId", csoqId);return this;};
	 	/** and csoq_id is null */
 		public M csoqIdNull(){if(this.get("csoqIdNot")==null)this.put("csoqIdNot", "");this.put("csoqId", null);return this;};
 		/** not .... */
 		public M csoqIdNot(){this.put("csoqIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csoqHost(Object csoqHost){this.put("csoqHost", csoqHost);return this;};
	 	/** and csoq_host is null */
 		public M csoqHostNull(){if(this.get("csoqHostNot")==null)this.put("csoqHostNot", "");this.put("csoqHost", null);return this;};
 		/** not .... */
 		public M csoqHostNot(){this.put("csoqHostNot", "not");return this;};
		/** 人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csoqAdder(Object csoqAdder){this.put("csoqAdder", csoqAdder);return this;};
	 	/** and csoq_adder is null */
 		public M csoqAdderNull(){if(this.get("csoqAdderNot")==null)this.put("csoqAdderNot", "");this.put("csoqAdder", null);return this;};
 		/** not .... */
 		public M csoqAdderNot(){this.put("csoqAdderNot", "not");return this;};
		/** 车辆 [非空] [CsCar]      **/
		public M csoqCar(Object csoqCar){this.put("csoqCar", csoqCar);return this;};
	 	/** and csoq_car is null */
 		public M csoqCarNull(){if(this.get("csoqCarNot")==null)this.put("csoqCarNot", "");this.put("csoqCar", null);return this;};
 		/** not .... */
 		public M csoqCarNot(){this.put("csoqCarNot", "not");return this;};
		public M csoqCar$on(CsCar.M value){
			this.put("CsCar", value);
			this.put("csoqCar$on", value);
			return this;
		};	
		/** 作业类型 [非空] [SrvProperty]  outsideqr_type:巡检作业类型     **/
		public M csoqType(Object csoqType){this.put("csoqType", csoqType);return this;};
	 	/** and csoq_type is null */
 		public M csoqTypeNull(){if(this.get("csoqTypeNot")==null)this.put("csoqTypeNot", "");this.put("csoqType", null);return this;};
 		/** not .... */
 		public M csoqTypeNot(){this.put("csoqTypeNot", "not");return this;};
		public M csoqType$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("csoqType$on", value);
			return this;
		};	
		/** 人员位置 [非空]       **/
		public M csoqUserPosStart(Object csoqUserPosStart){this.put("csoqUserPosStart", csoqUserPosStart);return this;};
	 	/** and csoq_user_pos_start is null */
 		public M csoqUserPosStartNull(){if(this.get("csoqUserPosStartNot")==null)this.put("csoqUserPosStartNot", "");this.put("csoqUserPosStart", null);return this;};
 		/** not .... */
 		public M csoqUserPosStartNot(){this.put("csoqUserPosStartNot", "not");return this;};
		/** 车辆位置 [非空]       **/
		public M csoqCarPosStart(Object csoqCarPosStart){this.put("csoqCarPosStart", csoqCarPosStart);return this;};
	 	/** and csoq_car_pos_start is null */
 		public M csoqCarPosStartNull(){if(this.get("csoqCarPosStartNot")==null)this.put("csoqCarPosStartNot", "");this.put("csoqCarPosStart", null);return this;};
 		/** not .... */
 		public M csoqCarPosStartNot(){this.put("csoqCarPosStartNot", "not");return this;};
		/** 人车距离 [非空]       **/
		public M csoqDistanceStart(Object csoqDistanceStart){this.put("csoqDistanceStart", csoqDistanceStart);return this;};
	 	/** and csoq_distance_start is null */
 		public M csoqDistanceStartNull(){if(this.get("csoqDistanceStartNot")==null)this.put("csoqDistanceStartNot", "");this.put("csoqDistanceStart", null);return this;};
 		/** not .... */
 		public M csoqDistanceStartNot(){this.put("csoqDistanceStartNot", "not");return this;};
		/** and csoq_distance_start >= ? */
		public M csoqDistanceStartMin(Object min){this.put("csoqDistanceStartMin", min);return this;};
		/** and csoq_distance_start <= ? */
		public M csoqDistanceStartMax(Object max){this.put("csoqDistanceStartMax", max);return this;};
		/** 人员位置        **/
		public M csoqUsePosEnd(Object csoqUsePosEnd){this.put("csoqUsePosEnd", csoqUsePosEnd);return this;};
	 	/** and csoq_use_pos_end is null */
 		public M csoqUsePosEndNull(){if(this.get("csoqUsePosEndNot")==null)this.put("csoqUsePosEndNot", "");this.put("csoqUsePosEnd", null);return this;};
 		/** not .... */
 		public M csoqUsePosEndNot(){this.put("csoqUsePosEndNot", "not");return this;};
		/** 车辆位置        **/
		public M csoqCarPosEnd(Object csoqCarPosEnd){this.put("csoqCarPosEnd", csoqCarPosEnd);return this;};
	 	/** and csoq_car_pos_end is null */
 		public M csoqCarPosEndNull(){if(this.get("csoqCarPosEndNot")==null)this.put("csoqCarPosEndNot", "");this.put("csoqCarPosEnd", null);return this;};
 		/** not .... */
 		public M csoqCarPosEndNot(){this.put("csoqCarPosEndNot", "not");return this;};
		/** 人车距离        **/
		public M csoqDistanceEnd(Object csoqDistanceEnd){this.put("csoqDistanceEnd", csoqDistanceEnd);return this;};
	 	/** and csoq_distance_end is null */
 		public M csoqDistanceEndNull(){if(this.get("csoqDistanceEndNot")==null)this.put("csoqDistanceEndNot", "");this.put("csoqDistanceEnd", null);return this;};
 		/** not .... */
 		public M csoqDistanceEndNot(){this.put("csoqDistanceEndNot", "not");return this;};
		/** and csoq_distance_end >= ? */
		public M csoqDistanceEndMin(Object min){this.put("csoqDistanceEndMin", min);return this;};
		/** and csoq_distance_end <= ? */
		public M csoqDistanceEndMax(Object max){this.put("csoqDistanceEndMax", max);return this;};
		/** 图片列表        **/
		public M csoqImages(Object csoqImages){this.put("csoqImages", csoqImages);return this;};
	 	/** and csoq_images is null */
 		public M csoqImagesNull(){if(this.get("csoqImagesNot")==null)this.put("csoqImagesNot", "");this.put("csoqImages", null);return this;};
 		/** not .... */
 		public M csoqImagesNot(){this.put("csoqImagesNot", "not");return this;};
		/** 开始时间 [非空]       **/
		public M csoqAddTime(Object csoqAddTime){this.put("csoqAddTime", csoqAddTime);return this;};
	 	/** and csoq_add_time is null */
 		public M csoqAddTimeNull(){if(this.get("csoqAddTimeNot")==null)this.put("csoqAddTimeNot", "");this.put("csoqAddTime", null);return this;};
 		/** not .... */
 		public M csoqAddTimeNot(){this.put("csoqAddTimeNot", "not");return this;};
 		/** and csoq_add_time >= ? */
 		public M csoqAddTimeStart(Object start){this.put("csoqAddTimeStart", start);return this;};			
 		/** and csoq_add_time <= ? */
 		public M csoqAddTimeEnd(Object end){this.put("csoqAddTimeEnd", end);return this;};
		/** 结束时间        **/
		public M csoqEndTime(Object csoqEndTime){this.put("csoqEndTime", csoqEndTime);return this;};
	 	/** and csoq_end_time is null */
 		public M csoqEndTimeNull(){if(this.get("csoqEndTimeNot")==null)this.put("csoqEndTimeNot", "");this.put("csoqEndTime", null);return this;};
 		/** not .... */
 		public M csoqEndTimeNot(){this.put("csoqEndTimeNot", "not");return this;};
 		/** and csoq_end_time >= ? */
 		public M csoqEndTimeStart(Object start){this.put("csoqEndTimeStart", start);return this;};			
 		/** and csoq_end_time <= ? */
 		public M csoqEndTimeEnd(Object end){this.put("csoqEndTimeEnd", end);return this;};
		/** 作业时长        **/
		public M csoqTimeLong(Object csoqTimeLong){this.put("csoqTimeLong", csoqTimeLong);return this;};
	 	/** and csoq_time_long is null */
 		public M csoqTimeLongNull(){if(this.get("csoqTimeLongNot")==null)this.put("csoqTimeLongNot", "");this.put("csoqTimeLong", null);return this;};
 		/** not .... */
 		public M csoqTimeLongNot(){this.put("csoqTimeLongNot", "not");return this;};
		/** and csoq_time_long >= ? */
		public M csoqTimeLongMin(Object min){this.put("csoqTimeLongMin", min);return this;};
		/** and csoq_time_long <= ? */
		public M csoqTimeLongMax(Object max){this.put("csoqTimeLongMax", max);return this;};
		/** 备注信息        **/
		public M csoqRemark(Object csoqRemark){this.put("csoqRemark", csoqRemark);return this;};
	 	/** and csoq_remark is null */
 		public M csoqRemarkNull(){if(this.get("csoqRemarkNot")==null)this.put("csoqRemarkNot", "");this.put("csoqRemark", null);return this;};
 		/** not .... */
 		public M csoqRemarkNot(){this.put("csoqRemarkNot", "not");return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csoqStatus(Object csoqStatus){this.put("csoqStatus", csoqStatus);return this;};
	 	/** and csoq_status is null */
 		public M csoqStatusNull(){if(this.get("csoqStatusNot")==null)this.put("csoqStatusNot", "");this.put("csoqStatus", null);return this;};
 		/** not .... */
 		public M csoqStatusNot(){this.put("csoqStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有巡检作业 **/
		public @api List<CsOutsideQr> list(Integer size){
			return getCsOutsideQrList(this,size);
		}
		/** 获取巡检作业分页 **/
		public @api Page<CsOutsideQr> page(int page,int size){
			return getCsOutsideQrPage(page, size , this);
		}
		/** 根据查询条件取巡检作业 **/
		public @api CsOutsideQr get(){
			return getCsOutsideQr(this);
		}
		/** 获取巡检作业数 **/
		public @api Long count(){
			return getCsOutsideQrCount(this);
		}
		/** 获取巡检作业表达式 **/
		public @api <T> T eval(String strEval){
			return getCsOutsideQrEval(strEval,this);
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
			updateCsOutsideQr(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csoqId="csoqId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csoqHost="csoqHost";
		/** 人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csoqAdder="csoqAdder";
		/** 车辆 [非空] [CsCar]      **/
		public final static @type(Long.class)  String csoqCar="csoqCar";
		/** 作业类型 [非空] [SrvProperty]  outsideqr_type:巡检作业类型     **/
		public final static @type(Long.class)  String csoqType="csoqType";
		/** 人员位置 [非空]       **/
		public final static @type(String.class) @like String csoqUserPosStart="csoqUserPosStart";
		/** 车辆位置 [非空]       **/
		public final static @type(String.class) @like String csoqCarPosStart="csoqCarPosStart";
		/** 人车距离 [非空]       **/
		public final static @type(Integer.class)  String csoqDistanceStart="csoqDistanceStart";
		/** and csoq_distance_start >= ? */
		public final static @type(Integer.class) String csoqDistanceStartMin="csoqDistanceStartMin";
		/** and csoq_distance_start <= ? */
		public final static @type(Integer.class) String csoqDistanceStartMax="csoqDistanceStartMax";
		/** 人员位置        **/
		public final static @type(String.class) @like String csoqUsePosEnd="csoqUsePosEnd";
		/** 车辆位置        **/
		public final static @type(String.class) @like String csoqCarPosEnd="csoqCarPosEnd";
		/** 人车距离        **/
		public final static @type(Integer.class)  String csoqDistanceEnd="csoqDistanceEnd";
		/** and csoq_distance_end >= ? */
		public final static @type(Integer.class) String csoqDistanceEndMin="csoqDistanceEndMin";
		/** and csoq_distance_end <= ? */
		public final static @type(Integer.class) String csoqDistanceEndMax="csoqDistanceEndMax";
		/** 图片列表        **/
		public final static @type(String.class)  String csoqImages="csoqImages";
		/** 开始时间 [非空]       **/
		public final static @type(Date.class)  String csoqAddTime="csoqAddTime";
	 	/** and csoq_add_time >= ? */
 		public final static @type(Date.class) String csoqAddTimeStart="csoqAddTimeStart";
 		/** and csoq_add_time <= ? */
 		public final static @type(Date.class) String csoqAddTimeEnd="csoqAddTimeEnd";
		/** 结束时间        **/
		public final static @type(Date.class)  String csoqEndTime="csoqEndTime";
	 	/** and csoq_end_time >= ? */
 		public final static @type(Date.class) String csoqEndTimeStart="csoqEndTimeStart";
 		/** and csoq_end_time <= ? */
 		public final static @type(Date.class) String csoqEndTimeEnd="csoqEndTimeEnd";
		/** 作业时长        **/
		public final static @type(Integer.class)  String csoqTimeLong="csoqTimeLong";
		/** and csoq_time_long >= ? */
		public final static @type(Integer.class) String csoqTimeLongMin="csoqTimeLongMin";
		/** and csoq_time_long <= ? */
		public final static @type(Integer.class) String csoqTimeLongMax="csoqTimeLongMax";
		/** 备注信息        **/
		public final static @type(String.class) @like String csoqRemark="csoqRemark";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csoqStatus="csoqStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csoqId="csoq_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csoqHost="csoq_host";
		/** 人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csoqAdder="csoq_adder";
		/** 车辆 [非空] [CsCar]      **/
		public final static String csoqCar="csoq_car";
		/** 作业类型 [非空] [SrvProperty]  outsideqr_type:巡检作业类型     **/
		public final static String csoqType="csoq_type";
		/** 人员位置 [非空]       **/
		public final static String csoqUserPosStart="csoq_user_pos_start";
		/** 车辆位置 [非空]       **/
		public final static String csoqCarPosStart="csoq_car_pos_start";
		/** 人车距离 [非空]       **/
		public final static String csoqDistanceStart="csoq_distance_start";
		/** 人员位置        **/
		public final static String csoqUsePosEnd="csoq_use_pos_end";
		/** 车辆位置        **/
		public final static String csoqCarPosEnd="csoq_car_pos_end";
		/** 人车距离        **/
		public final static String csoqDistanceEnd="csoq_distance_end";
		/** 图片列表        **/
		public final static String csoqImages="csoq_images";
		/** 开始时间 [非空]       **/
		public final static String csoqAddTime="csoq_add_time";
		/** 结束时间        **/
		public final static String csoqEndTime="csoq_end_time";
		/** 作业时长        **/
		public final static String csoqTimeLong="csoq_time_long";
		/** 备注信息        **/
		public final static String csoqRemark="csoq_remark";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csoqStatus="csoq_status";
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
				$.Set(name,CsOutsideQr.getCsOutsideQr(params));
			else
				$.Set(name,CsOutsideQr.getCsOutsideQrList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取巡检作业数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsOutsideQr) $.GetRequest("CsOutsideQr$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsOutsideQr.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsOutsideQr.getCsOutsideQr(params);
			else
				value = CsOutsideQr.getCsOutsideQrList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsOutsideQr.Get($.add(CsOutsideQr.F.csoqId,param));
		else if(!$.empty(param.toString()))
			value = CsOutsideQr.get(Long.valueOf(param.toString()));
		$.SetRequest("CsOutsideQr$"+param.hashCode(), value);
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
	public void mergeSet(CsOutsideQr old){
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