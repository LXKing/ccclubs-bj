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

@namespace("service/maintain")
public @caption("车辆保养") @table("cs_maintain") class CsMaintain implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csm_id")   @primary  @note("  ") Long csmId;// 主键 非空     
	private @caption("城市") @column("csm_host")    @relate("$csmHost") @RelateClass(SrvHost.class)  @note("  ") Long csmHost;// 非空     
 	private SrvHost $csmHost;//关联对象[运营城市]
	private @caption("车牌号") @column("csm_number")    @relate("$csmNumber") @RelateClass(CsCar.class)  @note("  ") Long csmNumber;// 非空     
 	private CsCar $csmNumber;//关联对象[车辆]
	private @caption("车型") @column("csm_model")    @relate("$csmModel") @RelateClass(CsCarModel.class)  @note("  ") Long csmModel;// 非空     
 	private CsCarModel $csmModel;//关联对象[车型]
	private @caption("保期") @column("csm_type")    @note(" 1:首保 2:二保 3:三保 4:四保 5:五保 6:六保 7:七保 8:其它  ") Short csmType;// 非空 1:首保 2:二保 3:三保 4:四保 5:五保 6:六保 7:七保 8:其它     
	private @caption("保养按") @column("csm_expect_by")    @note(" 0:里程 1:时间  ") Short csmExpectBy;// 非空 0:里程 1:时间     
	private @caption("预计保养日期") @column("csm_expect_date")    @note("  ") Date csmExpectDate;//     
	private @caption("实际保养日期") @column("csm_date")    @note("  ") Date csmDate;//     
	private @caption("预计保养公里") @column("csm_expect_km")    @note("  ") Integer csmExpectKm;//     
	private @caption("实际保养公里") @column("csm_real_km")    @note("  ") Integer csmRealKm;//     
	private @caption("当前公里数") @column("csm_curr_km")    @note("  ") Integer csmCurrKm;//     
	private @caption("公里数历史") @column("csm_km_history")  @hidden   @note("  ") String csmKmHistory;//     
	private @caption("费别") @column("csm_fee_type")    @note(" 0:免费 1:自费  ") Short csmFeeType;// 非空 0:免费 1:自费     
	private @caption("保养费用") @column("csm_fee")    @note("  ") Double csmFee;//     
	private @caption("备注信息") @column("csm_remark")    @note("  ") String csmRemark;//     
	private @caption("添加时间") @column("csm_add_time")    @note("  ") Date csmAddTime;// 非空     
	private @caption("状态") @column("csm_status")    @note(" 0:保养预备 1:保养报警 2:正在保养 3:保养完成  ") Short csmStatus;// 非空 0:保养预备 1:保养报警 2:正在保养 3:保养完成     
	
	//默认构造函数
	public CsMaintain(){
	
	}
	
	//主键构造函数
	public CsMaintain(Long id){
		this.csmId = id;
	}
	
	/**所有字段构造函数 CsMaintain(csmHost,csmNumber,csmModel,csmType,csmExpectBy,csmExpectDate,csmDate,csmExpectKm,csmRealKm,csmCurrKm,csmKmHistory,csmFeeType,csmFee,csmRemark,csmAddTime,csmStatus)
	 CsMaintain(
	 	$.getLong("csmHost")//城市 [非空]
	 	,$.getLong("csmNumber")//车牌号 [非空]
	 	,$.getLong("csmModel")//车型 [非空]
	 	,$.getShort("csmType")//保期 [非空]
	 	,$.getShort("csmExpectBy")//保养按 [非空]
	 	,$.getDate("csmExpectDate")//预计保养日期
	 	,$.getDate("csmDate")//实际保养日期
	 	,$.getInteger("csmExpectKm")//预计保养公里
	 	,$.getInteger("csmRealKm")//实际保养公里
	 	,$.getInteger("csmCurrKm")//当前公里数
	 	,$.getString("csmKmHistory")//公里数历史
	 	,$.getShort("csmFeeType")//费别 [非空]
	 	,$.getDouble("csmFee")//保养费用
	 	,$.getString("csmRemark")//备注信息
	 	,$.getDate("csmAddTime")//添加时间 [非空]
	 	,$.getShort("csmStatus")//状态 [非空]
	 )
	**/
	public CsMaintain(Long csmHost,Long csmNumber,Long csmModel,Short csmType,Short csmExpectBy,Date csmExpectDate,Date csmDate,Integer csmExpectKm,Integer csmRealKm,Integer csmCurrKm,String csmKmHistory,Short csmFeeType,Double csmFee,String csmRemark,Date csmAddTime,Short csmStatus){
		this.csmHost=csmHost;
		this.csmNumber=csmNumber;
		this.csmModel=csmModel;
		this.csmType=csmType;
		this.csmExpectBy=csmExpectBy;
		this.csmExpectDate=csmExpectDate;
		this.csmDate=csmDate;
		this.csmExpectKm=csmExpectKm;
		this.csmRealKm=csmRealKm;
		this.csmCurrKm=csmCurrKm;
		this.csmKmHistory=csmKmHistory;
		this.csmFeeType=csmFeeType;
		this.csmFee=csmFee;
		this.csmRemark=csmRemark;
		this.csmAddTime=csmAddTime;
		this.csmStatus=csmStatus;
	}
	
	//设置非空字段
	public CsMaintain setNotNull(Long csmHost,Long csmNumber,Long csmModel,Short csmType,Short csmExpectBy,Short csmFeeType,Date csmAddTime,Short csmStatus){
		this.csmHost=csmHost;
		this.csmNumber=csmNumber;
		this.csmModel=csmModel;
		this.csmType=csmType;
		this.csmExpectBy=csmExpectBy;
		this.csmFeeType=csmFeeType;
		this.csmAddTime=csmAddTime;
		this.csmStatus=csmStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsMaintain csmId(Long csmId){
		this.csmId = csmId;
		this.setSeted(F.csmId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsMaintain csmHost(Long csmHost){
		this.csmHost = csmHost;
		this.setSeted(F.csmHost);
		return this;
	}
	/** 车牌号 [非空] [CsCar]      **/
	public CsMaintain csmNumber(Long csmNumber){
		this.csmNumber = csmNumber;
		this.setSeted(F.csmNumber);
		return this;
	}
	/** 车型 [非空] [CsCarModel]      **/
	public CsMaintain csmModel(Long csmModel){
		this.csmModel = csmModel;
		this.setSeted(F.csmModel);
		return this;
	}
	/** 保期 [非空]   1:首保 2:二保 3:三保 4:四保 5:五保 6:六保 7:七保 8:其它     **/
	public CsMaintain csmType(Short csmType){
		this.csmType = csmType;
		this.setSeted(F.csmType);
		return this;
	}
	/** 保养按 [非空]   0:里程 1:时间     **/
	public CsMaintain csmExpectBy(Short csmExpectBy){
		this.csmExpectBy = csmExpectBy;
		this.setSeted(F.csmExpectBy);
		return this;
	}
	/** 预计保养日期        **/
	public CsMaintain csmExpectDate(Date csmExpectDate){
		this.csmExpectDate = csmExpectDate;
		this.setSeted(F.csmExpectDate);
		return this;
	}
	/** 实际保养日期        **/
	public CsMaintain csmDate(Date csmDate){
		this.csmDate = csmDate;
		this.setSeted(F.csmDate);
		return this;
	}
	/** 预计保养公里        **/
	public CsMaintain csmExpectKm(Integer csmExpectKm){
		this.csmExpectKm = csmExpectKm;
		this.setSeted(F.csmExpectKm);
		return this;
	}
	/** 实际保养公里        **/
	public CsMaintain csmRealKm(Integer csmRealKm){
		this.csmRealKm = csmRealKm;
		this.setSeted(F.csmRealKm);
		return this;
	}
	/** 当前公里数        **/
	public CsMaintain csmCurrKm(Integer csmCurrKm){
		this.csmCurrKm = csmCurrKm;
		this.setSeted(F.csmCurrKm);
		return this;
	}
	/** 公里数历史        **/
	public CsMaintain csmKmHistory(String csmKmHistory){
		this.csmKmHistory = csmKmHistory;
		this.setSeted(F.csmKmHistory);
		return this;
	}
	/** 费别 [非空]   0:免费 1:自费     **/
	public CsMaintain csmFeeType(Short csmFeeType){
		this.csmFeeType = csmFeeType;
		this.setSeted(F.csmFeeType);
		return this;
	}
	/** 保养费用        **/
	public CsMaintain csmFee(Double csmFee){
		this.csmFee = csmFee;
		this.setSeted(F.csmFee);
		return this;
	}
	/** 备注信息        **/
	public CsMaintain csmRemark(String csmRemark){
		this.csmRemark = csmRemark;
		this.setSeted(F.csmRemark);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsMaintain csmAddTime(Date csmAddTime){
		this.csmAddTime = csmAddTime;
		this.setSeted(F.csmAddTime);
		return this;
	}
	/** 状态 [非空]   0:保养预备 1:保养报警 2:正在保养 3:保养完成     **/
	public CsMaintain csmStatus(Short csmStatus){
		this.csmStatus = csmStatus;
		this.setSeted(F.csmStatus);
		return this;
	}
	
	
	//克隆对象
	public CsMaintain clone(){
		CsMaintain clone = new CsMaintain();
		clone.csmHost=this.csmHost;
		clone.csmNumber=this.csmNumber;
		clone.csmModel=this.csmModel;
		clone.csmType=this.csmType;
		clone.csmExpectBy=this.csmExpectBy;
		clone.csmFeeType=this.csmFeeType;
		clone.csmAddTime=this.csmAddTime;
		clone.csmStatus=this.csmStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取车辆保养
	 * @param id
	 * @return
	 */
	public static @api CsMaintain get(Long id){		
		return getCsMaintainById(id);
	}
	/**
	 * 获取所有车辆保养
	 * @return
	 */
	public static @api List<CsMaintain> list(Map params,Integer size){
		return getCsMaintainList(params,size);
	}
	/**
	 * 获取车辆保养分页
	 * @return
	 */
	public static @api Page<CsMaintain> page(int page,int size,Map params){
		return getCsMaintainPage(page, size , params);
	}
	/**
	 * 根据查询条件取车辆保养
	 * @param params
	 * @return
	 */
	public static @api CsMaintain Get(Map params){
		return getCsMaintain(params);
	}
	/**
	 * 获取车辆保养数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsMaintainCount(params);
	}
	/**
	 * 获取车辆保养数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsMaintainEval(eval,params);
	}
	
	/**
	 * 根据ID取车辆保养
	 * @param id
	 * @return
	 */
	public static @api CsMaintain getCsMaintainById(Long id){		
		CsMaintain csMaintain = (CsMaintain) $.GetRequest("CsMaintain$"+id);
		if(csMaintain!=null)
			return csMaintain;
		com.ccclubs.service.admin.ICsMaintainService csMaintainService = $.GetSpringBean("csMaintainService");		
		return csMaintainService.getCsMaintainById(id);
	}
	
	
	/**
	 * 根据ID取车辆保养的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsMaintain.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsMaintain csMaintain = get(id);
		if(csMaintain!=null){
			String strValue = csMaintain.getCsmId$();
			if(!"CsmId".equals("CsmId"))
				strValue+="("+csMaintain.getCsmId$()+")";
			MemCache.setValue(CsMaintain.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsmId$();
		if(!"CsmId".equals("CsmId"))
			keyValue+="("+this.getCsmId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有车辆保养
	 * @return
	 */
	public static @api List<CsMaintain> getCsMaintainList(Map params,Integer size){
		com.ccclubs.service.admin.ICsMaintainService csMaintainService = $.GetSpringBean("csMaintainService");
		return csMaintainService.getCsMaintainList(params, size);
	}
	
	/**
	 * 获取车辆保养分页
	 * @return
	 */
	public static @api Page<CsMaintain> getCsMaintainPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsMaintainService csMaintainService = $.GetSpringBean("csMaintainService");
		return csMaintainService.getCsMaintainPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取车辆保养
	 * @param params
	 * @return
	 */
	public static @api CsMaintain getCsMaintain(Map params){
		com.ccclubs.service.admin.ICsMaintainService csMaintainService = $.GetSpringBean("csMaintainService");
		return csMaintainService.getCsMaintain(params);
	}
	
	/**
	 * 获取车辆保养数
	 * @return
	 */
	public static @api Long getCsMaintainCount(Map params){
		com.ccclubs.service.admin.ICsMaintainService csMaintainService = $.GetSpringBean("csMaintainService");
		return csMaintainService.getCsMaintainCount(params);
	}
		
		
	/**
	 * 获取车辆保养自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsMaintainEval(String eval,Map params){
		com.ccclubs.service.admin.ICsMaintainService csMaintainService = $.GetSpringBean("csMaintainService");
		return csMaintainService.getCsMaintainEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsMaintain(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsMaintainService csMaintainService = $.GetSpringBean("csMaintainService");
		csMaintainService.updateCsMaintainByConfirm(set, where);
	}
	
	
	/**
	 * 保存车辆保养对象
	 * @param params
	 * @return
	 */
	public CsMaintain save(){
		com.ccclubs.service.admin.ICsMaintainService csMaintainService = $.GetSpringBean("csMaintainService");
		if(this.getCsmId()!=null)
			csMaintainService.updateCsMaintain(this);
		else
			return csMaintainService.saveCsMaintain(this);
		return this;
	}
	
	
	/**
	 * 更新车辆保养对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsMaintainService csMaintainService = $.GetSpringBean("csMaintainService");
		csMaintainService.updateCsMaintain$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsMaintainService csMaintainService = $.GetSpringBean("csMaintainService");
		if($.equals($.config("logic_delete"),"true"))
			csMaintainService.removeCsMaintainById(this.getCsmId());
		else
			csMaintainService.deleteCsMaintainById(this.getCsmId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsMaintainService csMaintainService = $.GetSpringBean("csMaintainService");
		return csMaintainService.executeTransaction(function);
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
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsmHost(){
		return this.csmHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsmHost$(){
		String strValue="";
		if(this.getCsmHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsmHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsmHost(Long csmHost){
		this.csmHost = csmHost;
		this.setSeted(F.csmHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csmHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsmHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsmHost()!=null){
 			srvHost = SrvHost.get(this.getCsmHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsmHost(), srvHost);
	 	return srvHost;
	}
	/*******************************车牌号**********************************/	
	/**
	* 车牌号 [非空] [CsCar]     
	**/
	public Long getCsmNumber(){
		return this.csmNumber;
	}
	/**
	* 获取车牌号格式化(toString)
	**/
	public String getCsmNumber$(){
		String strValue="";
		if(this.getCsmNumber()!=null){
				strValue+=$.str(CsCar.getKeyValue(this.getCsmNumber()));
		}			
	 	 return strValue;
	}
	/**
	* 车牌号 [非空] [CsCar]     
	**/
	public void setCsmNumber(Long csmNumber){
		this.csmNumber = csmNumber;
		this.setSeted(F.csmNumber);
	}
	/**
	* 获取关联对象[车辆]
	**/	 			
 	public CsCar get$csmNumber(){
 		com.ccclubs.model.CsCar csCar = (com.ccclubs.model.CsCar) $.GetRequest("CsCar$"+this.getCsmNumber());
 		if(csCar!=null)
			return csCar;
		if(this.getCsmNumber()!=null){
 			csCar = CsCar.get(this.getCsmNumber());
 		}
 		$.SetRequest("CsCar$"+this.getCsmNumber(), csCar);
	 	return csCar;
	}
	/*******************************车型**********************************/	
	/**
	* 车型 [非空] [CsCarModel]     
	**/
	public Long getCsmModel(){
		return this.csmModel;
	}
	/**
	* 获取车型格式化(toString)
	**/
	public String getCsmModel$(){
		String strValue="";
		if(this.getCsmModel()!=null){
				strValue+=$.str(CsCarModel.getKeyValue(this.getCsmModel()));
		}			
	 	 return strValue;
	}
	/**
	* 车型 [非空] [CsCarModel]     
	**/
	public void setCsmModel(Long csmModel){
		this.csmModel = csmModel;
		this.setSeted(F.csmModel);
	}
	/**
	* 获取关联对象[车型]
	**/	 			
 	public CsCarModel get$csmModel(){
 		com.ccclubs.model.CsCarModel csCarModel = (com.ccclubs.model.CsCarModel) $.GetRequest("CsCarModel$"+this.getCsmModel());
 		if(csCarModel!=null)
			return csCarModel;
		if(this.getCsmModel()!=null){
 			csCarModel = CsCarModel.get(this.getCsmModel());
 		}
 		$.SetRequest("CsCarModel$"+this.getCsmModel(), csCarModel);
	 	return csCarModel;
	}
	/*******************************保期**********************************/	
	/**
	* 保期 [非空]   1:首保 2:二保 3:三保 4:四保 5:五保 6:六保 7:七保 8:其它    
	**/
	public Short getCsmType(){
		return this.csmType;
	}
	/**
	* 获取保期格式化(toString)
	**/
	public String getCsmType$(){
		String strValue="";
		 if($.equals($.str(this.getCsmType()),"1"))
			strValue=$.str("首保");		 
		 if($.equals($.str(this.getCsmType()),"2"))
			strValue=$.str("二保");		 
		 if($.equals($.str(this.getCsmType()),"3"))
			strValue=$.str("三保");		 
		 if($.equals($.str(this.getCsmType()),"4"))
			strValue=$.str("四保");		 
		 if($.equals($.str(this.getCsmType()),"5"))
			strValue=$.str("五保");		 
		 if($.equals($.str(this.getCsmType()),"6"))
			strValue=$.str("六保");		 
		 if($.equals($.str(this.getCsmType()),"7"))
			strValue=$.str("七保");		 
		 if($.equals($.str(this.getCsmType()),"8"))
			strValue=$.str("其它");		 
	 	 return strValue;
	}
	/**
	* 保期 [非空]   1:首保 2:二保 3:三保 4:四保 5:五保 6:六保 7:七保 8:其它    
	**/
	public void setCsmType(Short csmType){
		this.csmType = csmType;
		this.setSeted(F.csmType);
	}
	/*******************************保养按**********************************/	
	/**
	* 保养按 [非空]   0:里程 1:时间    
	**/
	public Short getCsmExpectBy(){
		return this.csmExpectBy;
	}
	/**
	* 获取保养按格式化(toString)
	**/
	public String getCsmExpectBy$(){
		String strValue="";
		 if($.equals($.str(this.getCsmExpectBy()),"0"))
			strValue=$.str("里程");		 
		 if($.equals($.str(this.getCsmExpectBy()),"1"))
			strValue=$.str("时间");		 
	 	 return strValue;
	}
	/**
	* 保养按 [非空]   0:里程 1:时间    
	**/
	public void setCsmExpectBy(Short csmExpectBy){
		this.csmExpectBy = csmExpectBy;
		this.setSeted(F.csmExpectBy);
	}
	/*******************************预计保养日期**********************************/	
	/**
	* 预计保养日期       
	**/
	public Date getCsmExpectDate(){
		return this.csmExpectDate;
	}
	/**
	* 获取预计保养日期格式化(toString)
	**/
	public String getCsmExpectDate$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmExpectDate(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 预计保养日期       
	**/
	public void setCsmExpectDate(Date csmExpectDate){
		this.csmExpectDate = csmExpectDate;
		this.setSeted(F.csmExpectDate);
	}
	/*******************************实际保养日期**********************************/	
	/**
	* 实际保养日期       
	**/
	public Date getCsmDate(){
		return this.csmDate;
	}
	/**
	* 获取实际保养日期格式化(toString)
	**/
	public String getCsmDate$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmDate(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 实际保养日期       
	**/
	public void setCsmDate(Date csmDate){
		this.csmDate = csmDate;
		this.setSeted(F.csmDate);
	}
	/*******************************预计保养公里**********************************/	
	/**
	* 预计保养公里       
	**/
	public Integer getCsmExpectKm(){
		return this.csmExpectKm;
	}
	/**
	* 获取预计保养公里格式化(toString)
	**/
	public String getCsmExpectKm$(){
		String strValue="";
		 strValue=$.str(this.getCsmExpectKm());
	 	 return strValue;
	}
	/**
	* 预计保养公里       
	**/
	public void setCsmExpectKm(Integer csmExpectKm){
		this.csmExpectKm = csmExpectKm;
		this.setSeted(F.csmExpectKm);
	}
	/*******************************实际保养公里**********************************/	
	/**
	* 实际保养公里       
	**/
	public Integer getCsmRealKm(){
		return this.csmRealKm;
	}
	/**
	* 获取实际保养公里格式化(toString)
	**/
	public String getCsmRealKm$(){
		String strValue="";
		 strValue=$.str(this.getCsmRealKm());
	 	 return strValue;
	}
	/**
	* 实际保养公里       
	**/
	public void setCsmRealKm(Integer csmRealKm){
		this.csmRealKm = csmRealKm;
		this.setSeted(F.csmRealKm);
	}
	/*******************************当前公里数**********************************/	
	/**
	* 当前公里数       
	**/
	public Integer getCsmCurrKm(){
		return this.csmCurrKm;
	}
	/**
	* 获取当前公里数格式化(toString)
	**/
	public String getCsmCurrKm$(){
		String strValue="";
		 strValue=$.str(this.getCsmCurrKm());
	 	 return strValue;
	}
	/**
	* 当前公里数       
	**/
	public void setCsmCurrKm(Integer csmCurrKm){
		this.csmCurrKm = csmCurrKm;
		this.setSeted(F.csmCurrKm);
	}
	/*******************************公里数历史**********************************/	
	/**
	* 公里数历史       
	**/
	public String getCsmKmHistory(){
		return this.csmKmHistory;
	}
	/**
	* 获取公里数历史格式化(toString)
	**/
	public String getCsmKmHistory$(){
		String strValue="";
		 strValue=$.str(this.getCsmKmHistory());
	 	 return strValue;
	}
	/**
	* 公里数历史       
	**/
	public void setCsmKmHistory(String csmKmHistory){
		this.csmKmHistory = csmKmHistory;
		this.setSeted(F.csmKmHistory);
	}
	/*******************************费别**********************************/	
	/**
	* 费别 [非空]   0:免费 1:自费    
	**/
	public Short getCsmFeeType(){
		return this.csmFeeType;
	}
	/**
	* 获取费别格式化(toString)
	**/
	public String getCsmFeeType$(){
		String strValue="";
		 if($.equals($.str(this.getCsmFeeType()),"0"))
			strValue=$.str("免费");		 
		 if($.equals($.str(this.getCsmFeeType()),"1"))
			strValue=$.str("自费");		 
	 	 return strValue;
	}
	/**
	* 费别 [非空]   0:免费 1:自费    
	**/
	public void setCsmFeeType(Short csmFeeType){
		this.csmFeeType = csmFeeType;
		this.setSeted(F.csmFeeType);
	}
	/*******************************保养费用**********************************/	
	/**
	* 保养费用       
	**/
	public Double getCsmFee(){
		return this.csmFee;
	}
	/**
	* 获取保养费用格式化(toString)
	**/
	public String getCsmFee$(){
		String strValue="";
		 strValue=$.str(this.getCsmFee());	
	 	 return strValue;
	}
	/**
	* 保养费用       
	**/
	public void setCsmFee(Double csmFee){
		this.csmFee = csmFee;
		this.setSeted(F.csmFee);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息       
	**/
	public String getCsmRemark(){
		return this.csmRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCsmRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsmRemark());
	 	 return strValue;
	}
	/**
	* 备注信息       
	**/
	public void setCsmRemark(String csmRemark){
		this.csmRemark = csmRemark;
		this.setSeted(F.csmRemark);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsmAddTime(){
		return this.csmAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsmAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsmAddTime(Date csmAddTime){
		this.csmAddTime = csmAddTime;
		this.setSeted(F.csmAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:保养预备 1:保养报警 2:正在保养 3:保养完成    
	**/
	public Short getCsmStatus(){
		return this.csmStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsmStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsmStatus()),"0"))
			strValue=$.str("保养预备");		 
		 if($.equals($.str(this.getCsmStatus()),"1"))
			strValue=$.str("保养报警");		 
		 if($.equals($.str(this.getCsmStatus()),"2"))
			strValue=$.str("正在保养");		 
		 if($.equals($.str(this.getCsmStatus()),"3"))
			strValue=$.str("保养完成");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:保养预备 1:保养报警 2:正在保养 3:保养完成    
	**/
	public void setCsmStatus(Short csmStatus){
		this.csmStatus = csmStatus;
		this.setSeted(F.csmStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsMaintain.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMaintain.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMaintain.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsMaintain.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsMaintain.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMaintain.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMaintain.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsMaintain.this);
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
		/** 城市 [非空] [SrvHost]      **/
		public M csmHost(Object csmHost){this.put("csmHost", csmHost);return this;};
	 	/** and csm_host is null */
 		public M csmHostNull(){if(this.get("csmHostNot")==null)this.put("csmHostNot", "");this.put("csmHost", null);return this;};
 		/** not .... */
 		public M csmHostNot(){this.put("csmHostNot", "not");return this;};
		/** 车牌号 [非空] [CsCar]      **/
		public M csmNumber(Object csmNumber){this.put("csmNumber", csmNumber);return this;};
	 	/** and csm_number is null */
 		public M csmNumberNull(){if(this.get("csmNumberNot")==null)this.put("csmNumberNot", "");this.put("csmNumber", null);return this;};
 		/** not .... */
 		public M csmNumberNot(){this.put("csmNumberNot", "not");return this;};
		public M csmNumber$on(CsCar.M value){
			this.put("CsCar", value);
			this.put("csmNumber$on", value);
			return this;
		};	
		/** 车型 [非空] [CsCarModel]      **/
		public M csmModel(Object csmModel){this.put("csmModel", csmModel);return this;};
	 	/** and csm_model is null */
 		public M csmModelNull(){if(this.get("csmModelNot")==null)this.put("csmModelNot", "");this.put("csmModel", null);return this;};
 		/** not .... */
 		public M csmModelNot(){this.put("csmModelNot", "not");return this;};
		public M csmModel$on(CsCarModel.M value){
			this.put("CsCarModel", value);
			this.put("csmModel$on", value);
			return this;
		};	
		/** 保期 [非空]   1:首保 2:二保 3:三保 4:四保 5:五保 6:六保 7:七保 8:其它     **/
		public M csmType(Object csmType){this.put("csmType", csmType);return this;};
	 	/** and csm_type is null */
 		public M csmTypeNull(){if(this.get("csmTypeNot")==null)this.put("csmTypeNot", "");this.put("csmType", null);return this;};
 		/** not .... */
 		public M csmTypeNot(){this.put("csmTypeNot", "not");return this;};
		/** 保养按 [非空]   0:里程 1:时间     **/
		public M csmExpectBy(Object csmExpectBy){this.put("csmExpectBy", csmExpectBy);return this;};
	 	/** and csm_expect_by is null */
 		public M csmExpectByNull(){if(this.get("csmExpectByNot")==null)this.put("csmExpectByNot", "");this.put("csmExpectBy", null);return this;};
 		/** not .... */
 		public M csmExpectByNot(){this.put("csmExpectByNot", "not");return this;};
		/** 预计保养日期        **/
		public M csmExpectDate(Object csmExpectDate){this.put("csmExpectDate", csmExpectDate);return this;};
	 	/** and csm_expect_date is null */
 		public M csmExpectDateNull(){if(this.get("csmExpectDateNot")==null)this.put("csmExpectDateNot", "");this.put("csmExpectDate", null);return this;};
 		/** not .... */
 		public M csmExpectDateNot(){this.put("csmExpectDateNot", "not");return this;};
 		/** and csm_expect_date >= ? */
 		public M csmExpectDateStart(Object start){this.put("csmExpectDateStart", start);return this;};			
 		/** and csm_expect_date <= ? */
 		public M csmExpectDateEnd(Object end){this.put("csmExpectDateEnd", end);return this;};
		/** 实际保养日期        **/
		public M csmDate(Object csmDate){this.put("csmDate", csmDate);return this;};
	 	/** and csm_date is null */
 		public M csmDateNull(){if(this.get("csmDateNot")==null)this.put("csmDateNot", "");this.put("csmDate", null);return this;};
 		/** not .... */
 		public M csmDateNot(){this.put("csmDateNot", "not");return this;};
 		/** and csm_date >= ? */
 		public M csmDateStart(Object start){this.put("csmDateStart", start);return this;};			
 		/** and csm_date <= ? */
 		public M csmDateEnd(Object end){this.put("csmDateEnd", end);return this;};
		/** 预计保养公里        **/
		public M csmExpectKm(Object csmExpectKm){this.put("csmExpectKm", csmExpectKm);return this;};
	 	/** and csm_expect_km is null */
 		public M csmExpectKmNull(){if(this.get("csmExpectKmNot")==null)this.put("csmExpectKmNot", "");this.put("csmExpectKm", null);return this;};
 		/** not .... */
 		public M csmExpectKmNot(){this.put("csmExpectKmNot", "not");return this;};
		/** and csm_expect_km >= ? */
		public M csmExpectKmMin(Object min){this.put("csmExpectKmMin", min);return this;};
		/** and csm_expect_km <= ? */
		public M csmExpectKmMax(Object max){this.put("csmExpectKmMax", max);return this;};
		/** 实际保养公里        **/
		public M csmRealKm(Object csmRealKm){this.put("csmRealKm", csmRealKm);return this;};
	 	/** and csm_real_km is null */
 		public M csmRealKmNull(){if(this.get("csmRealKmNot")==null)this.put("csmRealKmNot", "");this.put("csmRealKm", null);return this;};
 		/** not .... */
 		public M csmRealKmNot(){this.put("csmRealKmNot", "not");return this;};
		/** and csm_real_km >= ? */
		public M csmRealKmMin(Object min){this.put("csmRealKmMin", min);return this;};
		/** and csm_real_km <= ? */
		public M csmRealKmMax(Object max){this.put("csmRealKmMax", max);return this;};
		/** 当前公里数        **/
		public M csmCurrKm(Object csmCurrKm){this.put("csmCurrKm", csmCurrKm);return this;};
	 	/** and csm_curr_km is null */
 		public M csmCurrKmNull(){if(this.get("csmCurrKmNot")==null)this.put("csmCurrKmNot", "");this.put("csmCurrKm", null);return this;};
 		/** not .... */
 		public M csmCurrKmNot(){this.put("csmCurrKmNot", "not");return this;};
		/** and csm_curr_km >= ? */
		public M csmCurrKmMin(Object min){this.put("csmCurrKmMin", min);return this;};
		/** and csm_curr_km <= ? */
		public M csmCurrKmMax(Object max){this.put("csmCurrKmMax", max);return this;};
		/** 公里数历史        **/
		public M csmKmHistory(Object csmKmHistory){this.put("csmKmHistory", csmKmHistory);return this;};
	 	/** and csm_km_history is null */
 		public M csmKmHistoryNull(){if(this.get("csmKmHistoryNot")==null)this.put("csmKmHistoryNot", "");this.put("csmKmHistory", null);return this;};
 		/** not .... */
 		public M csmKmHistoryNot(){this.put("csmKmHistoryNot", "not");return this;};
		/** 费别 [非空]   0:免费 1:自费     **/
		public M csmFeeType(Object csmFeeType){this.put("csmFeeType", csmFeeType);return this;};
	 	/** and csm_fee_type is null */
 		public M csmFeeTypeNull(){if(this.get("csmFeeTypeNot")==null)this.put("csmFeeTypeNot", "");this.put("csmFeeType", null);return this;};
 		/** not .... */
 		public M csmFeeTypeNot(){this.put("csmFeeTypeNot", "not");return this;};
		/** 保养费用        **/
		public M csmFee(Object csmFee){this.put("csmFee", csmFee);return this;};
	 	/** and csm_fee is null */
 		public M csmFeeNull(){if(this.get("csmFeeNot")==null)this.put("csmFeeNot", "");this.put("csmFee", null);return this;};
 		/** not .... */
 		public M csmFeeNot(){this.put("csmFeeNot", "not");return this;};
		/** and csm_fee >= ? */
		public M csmFeeMin(Object min){this.put("csmFeeMin", min);return this;};
		/** and csm_fee <= ? */
		public M csmFeeMax(Object max){this.put("csmFeeMax", max);return this;};
		/** 备注信息        **/
		public M csmRemark(Object csmRemark){this.put("csmRemark", csmRemark);return this;};
	 	/** and csm_remark is null */
 		public M csmRemarkNull(){if(this.get("csmRemarkNot")==null)this.put("csmRemarkNot", "");this.put("csmRemark", null);return this;};
 		/** not .... */
 		public M csmRemarkNot(){this.put("csmRemarkNot", "not");return this;};
		/** 添加时间 [非空]       **/
		public M csmAddTime(Object csmAddTime){this.put("csmAddTime", csmAddTime);return this;};
	 	/** and csm_add_time is null */
 		public M csmAddTimeNull(){if(this.get("csmAddTimeNot")==null)this.put("csmAddTimeNot", "");this.put("csmAddTime", null);return this;};
 		/** not .... */
 		public M csmAddTimeNot(){this.put("csmAddTimeNot", "not");return this;};
 		/** and csm_add_time >= ? */
 		public M csmAddTimeStart(Object start){this.put("csmAddTimeStart", start);return this;};			
 		/** and csm_add_time <= ? */
 		public M csmAddTimeEnd(Object end){this.put("csmAddTimeEnd", end);return this;};
		/** 状态 [非空]   0:保养预备 1:保养报警 2:正在保养 3:保养完成     **/
		public M csmStatus(Object csmStatus){this.put("csmStatus", csmStatus);return this;};
	 	/** and csm_status is null */
 		public M csmStatusNull(){if(this.get("csmStatusNot")==null)this.put("csmStatusNot", "");this.put("csmStatus", null);return this;};
 		/** not .... */
 		public M csmStatusNot(){this.put("csmStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有车辆保养 **/
		public @api List<CsMaintain> list(Integer size){
			return getCsMaintainList(this,size);
		}
		/** 获取车辆保养分页 **/
		public @api Page<CsMaintain> page(int page,int size){
			return getCsMaintainPage(page, size , this);
		}
		/** 根据查询条件取车辆保养 **/
		public @api CsMaintain get(){
			return getCsMaintain(this);
		}
		/** 获取车辆保养数 **/
		public @api Long count(){
			return getCsMaintainCount(this);
		}
		/** 获取车辆保养表达式 **/
		public @api <T> T eval(String strEval){
			return getCsMaintainEval(strEval,this);
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
			updateCsMaintain(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csmId="csmId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csmHost="csmHost";
		/** 车牌号 [非空] [CsCar]      **/
		public final static @type(Long.class)  String csmNumber="csmNumber";
		/** 车型 [非空] [CsCarModel]      **/
		public final static @type(Long.class)  String csmModel="csmModel";
		/** 保期 [非空]   1:首保 2:二保 3:三保 4:四保 5:五保 6:六保 7:七保 8:其它     **/
		public final static @type(Short.class)  String csmType="csmType";
		/** 保养按 [非空]   0:里程 1:时间     **/
		public final static @type(Short.class)  String csmExpectBy="csmExpectBy";
		/** 预计保养日期        **/
		public final static @type(Date.class)  String csmExpectDate="csmExpectDate";
	 	/** and csm_expect_date >= ? */
 		public final static @type(Date.class) String csmExpectDateStart="csmExpectDateStart";
 		/** and csm_expect_date <= ? */
 		public final static @type(Date.class) String csmExpectDateEnd="csmExpectDateEnd";
		/** 实际保养日期        **/
		public final static @type(Date.class)  String csmDate="csmDate";
	 	/** and csm_date >= ? */
 		public final static @type(Date.class) String csmDateStart="csmDateStart";
 		/** and csm_date <= ? */
 		public final static @type(Date.class) String csmDateEnd="csmDateEnd";
		/** 预计保养公里        **/
		public final static @type(Integer.class)  String csmExpectKm="csmExpectKm";
		/** and csm_expect_km >= ? */
		public final static @type(Integer.class) String csmExpectKmMin="csmExpectKmMin";
		/** and csm_expect_km <= ? */
		public final static @type(Integer.class) String csmExpectKmMax="csmExpectKmMax";
		/** 实际保养公里        **/
		public final static @type(Integer.class)  String csmRealKm="csmRealKm";
		/** and csm_real_km >= ? */
		public final static @type(Integer.class) String csmRealKmMin="csmRealKmMin";
		/** and csm_real_km <= ? */
		public final static @type(Integer.class) String csmRealKmMax="csmRealKmMax";
		/** 当前公里数        **/
		public final static @type(Integer.class)  String csmCurrKm="csmCurrKm";
		/** and csm_curr_km >= ? */
		public final static @type(Integer.class) String csmCurrKmMin="csmCurrKmMin";
		/** and csm_curr_km <= ? */
		public final static @type(Integer.class) String csmCurrKmMax="csmCurrKmMax";
		/** 公里数历史        **/
		public final static @type(String.class) @like String csmKmHistory="csmKmHistory";
		/** 费别 [非空]   0:免费 1:自费     **/
		public final static @type(Short.class)  String csmFeeType="csmFeeType";
		/** 保养费用        **/
		public final static @type(Double.class)  String csmFee="csmFee";
		/** and csm_fee >= ? */
		public final static @type(Double.class) String csmFeeMin="csmFeeMin";
		/** and csm_fee <= ? */
		public final static @type(Double.class) String csmFeeMax="csmFeeMax";
		/** 备注信息        **/
		public final static @type(String.class) @like String csmRemark="csmRemark";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csmAddTime="csmAddTime";
	 	/** and csm_add_time >= ? */
 		public final static @type(Date.class) String csmAddTimeStart="csmAddTimeStart";
 		/** and csm_add_time <= ? */
 		public final static @type(Date.class) String csmAddTimeEnd="csmAddTimeEnd";
		/** 状态 [非空]   0:保养预备 1:保养报警 2:正在保养 3:保养完成     **/
		public final static @type(Short.class)  String csmStatus="csmStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csmId="csm_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csmHost="csm_host";
		/** 车牌号 [非空] [CsCar]      **/
		public final static String csmNumber="csm_number";
		/** 车型 [非空] [CsCarModel]      **/
		public final static String csmModel="csm_model";
		/** 保期 [非空]   1:首保 2:二保 3:三保 4:四保 5:五保 6:六保 7:七保 8:其它     **/
		public final static String csmType="csm_type";
		/** 保养按 [非空]   0:里程 1:时间     **/
		public final static String csmExpectBy="csm_expect_by";
		/** 预计保养日期        **/
		public final static String csmExpectDate="csm_expect_date";
		/** 实际保养日期        **/
		public final static String csmDate="csm_date";
		/** 预计保养公里        **/
		public final static String csmExpectKm="csm_expect_km";
		/** 实际保养公里        **/
		public final static String csmRealKm="csm_real_km";
		/** 当前公里数        **/
		public final static String csmCurrKm="csm_curr_km";
		/** 公里数历史        **/
		public final static String csmKmHistory="csm_km_history";
		/** 费别 [非空]   0:免费 1:自费     **/
		public final static String csmFeeType="csm_fee_type";
		/** 保养费用        **/
		public final static String csmFee="csm_fee";
		/** 备注信息        **/
		public final static String csmRemark="csm_remark";
		/** 添加时间 [非空]       **/
		public final static String csmAddTime="csm_add_time";
		/** 状态 [非空]   0:保养预备 1:保养报警 2:正在保养 3:保养完成     **/
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
				$.Set(name,CsMaintain.getCsMaintain(params));
			else
				$.Set(name,CsMaintain.getCsMaintainList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取车辆保养数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsMaintain) $.GetRequest("CsMaintain$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsMaintain.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsMaintain.getCsMaintain(params);
			else
				value = CsMaintain.getCsMaintainList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsMaintain.Get($.add(CsMaintain.F.csmId,param));
		else if(!$.empty(param.toString()))
			value = CsMaintain.get(Long.valueOf(param.toString()));
		$.SetRequest("CsMaintain$"+param.hashCode(), value);
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
	public void mergeSet(CsMaintain old){
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