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

@namespace("object/outlets")
public @caption("网点") @table("cs_outlets") class CsOutlets implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cso_id")   @primary  @note("  ") Long csoId;// 主键 非空     
	private @caption("城市") @column("cso_host")    @relate("$csoHost") @RelateClass(SrvHost.class)  @note("  ") Long csoHost;// 非空     
 	private SrvHost $csoHost;//关联对象[运营城市]
	private @caption("名称") @column("cso_name")    @note("  ") String csoName;// 非空     
	private @caption("所在区域") @column("cso_area")    @relate("$csoArea") @RelateClass(CsArea.class)  @note("  ") Long csoArea;// 非空     
 	private CsArea $csoArea;//关联对象[网点区域]
	private @caption("网点类型") @column("cso_type")    @note(" 0:汽油车网点 1:电动车网点  ") Short csoType;// 非空 0:汽油车网点 1:电动车网点     
	private @caption("经度") @column("cso_longitude")    @note("  ") Double csoLongitude;// 非空     
	private @caption("纬度") @column("cso_latitude")    @note("  ") Double csoLatitude;// 非空     
	//private @caption("车辆列表") @RelateClass(CsCar.class) List<CsCar> csoCars;//反向关联的车辆列表
	private @caption("地址") @column("cso_address")    @note("  ") String csoAddress;// 非空     
	private @caption("停车地点") @column("cso_space")    @note("  ") String csoSpace;//     
	private @caption("网点介绍") @column("cso_details")  @hidden   @note("  ") String csoDetails;//     
	private @caption("门禁时间") @column("cso_door_time")    @note("  ") String csoDoorTime;//     
	private @caption("停车费") @column("cso_cost")    @note("  ") Double csoCost;//(单位：元每月)     
	private @caption("联系人") @column("cso_person")    @note("  ") String csoPerson;//     
	private @caption("联系电话") @column("cso_contact")    @note("  ") String csoContact;//     
	private @caption("缴费到期") @column("cso_until")    @note("  ") Date csoUntil;//     
	private @caption("合约到期") @column("cso_expire")    @note("  ") Date csoExpire;//     
	private @caption("电桩建立时间") @column("cso_pile_time")    @note("  ") Date csoPileTime;//     
	private @caption("已有车辆数") @column("cso_car_s")    @note("  ") Short csoCarS;// 非空     
	private @caption("车位数") @column("cso_position_s")    @note("  ") Short csoPositionS;// 非空     
	private @caption("网点图片") @column("cso_image")    @note(" 400:width 300:height  ") String csoImage;// 400:width 300:height     
	private @caption("AB借出") @column("cso_can_a")    @note("  是否支持A借B还中的借出") Boolean csoCanA;// 非空  是否支持A借B还中的借出   
	private @caption("AB还入") @column("cso_can_b")    @note("  是否支持A借B还中的还入") Boolean csoCanB;// 非空  是否支持A借B还中的还入   
	private @caption("能注册") @column("cso_can_reg")    @note("  可作为会员默认网点的选取项") Boolean csoCanReg;// 非空  可作为会员默认网点的选取项   
	private @caption("能预订") @column("cso_can_order")    @note("  描述该网点是否可以下单") Boolean csoCanOrder;// 非空  描述该网点是否可以下单   
	private @caption("优先级") @column("cso_priority")    @note("  ") Integer csoPriority;//     
	private @caption("人气") @column("cso_vote_s")    @note("  ") Integer csoVoteS;//     
	private @caption("描述") @column("cso_profile")    @note("  ") String csoProfile;//     
	private @caption("上线时间") @column("cso_online_time")    @note("  ") Date csoOnlineTime;//     
	private @caption("修改时间") @column("cso_update_time")    @note("  ") Date csoUpdateTime;//     
	private @caption("添加时间") @column("cso_add_time")    @note("  ") Date csoAddTime;// 非空     
	private @caption("状态") @column("cso_status")    @note(" 1:正常 0:无效  描述该网点是否让会员看得到") Short csoStatus;// 非空 1:正常 0:无效  描述该网点是否让会员看得到   
	
	//默认构造函数
	public CsOutlets(){
	
	}
	
	//主键构造函数
	public CsOutlets(Long id){
		this.csoId = id;
	}
	
	/**所有字段构造函数 CsOutlets(csoHost,csoName,csoArea,csoType,csoLongitude,csoLatitude,csoAddress,csoSpace,csoDetails,csoDoorTime,csoCost,csoPerson,csoContact,csoUntil,csoExpire,csoPileTime,csoCarS,csoPositionS,csoImage,csoCanA,csoCanB,csoCanReg,csoCanOrder,csoPriority,csoVoteS,csoProfile,csoOnlineTime,csoUpdateTime,csoAddTime,csoStatus)
	 CsOutlets(
	 	$.getLong("csoHost")//城市 [非空]
	 	,$.getString("csoName")//名称 [非空]
	 	,$.getLong("csoArea")//所在区域 [非空]
	 	,$.getShort("csoType")//网点类型 [非空]
	 	,$.getDouble("csoLongitude")//经度 [非空]
	 	,$.getDouble("csoLatitude")//纬度 [非空]
	 	,$.getString("csoAddress")//地址 [非空]
	 	,$.getString("csoSpace")//停车地点
	 	,$.getString("csoDetails")//网点介绍
	 	,$.getString("csoDoorTime")//门禁时间
	 	,$.getDouble("csoCost")//停车费
	 	,$.getString("csoPerson")//联系人
	 	,$.getString("csoContact")//联系电话
	 	,$.getDate("csoUntil")//缴费到期
	 	,$.getDate("csoExpire")//合约到期
	 	,$.getDate("csoPileTime")//电桩建立时间
	 	,$.getShort("csoCarS")//已有车辆数 [非空]
	 	,$.getShort("csoPositionS")//车位数 [非空]
	 	,$.getString("csoImage")//网点图片
	 	,$.getBoolean("csoCanA")//AB借出 [非空]
	 	,$.getBoolean("csoCanB")//AB还入 [非空]
	 	,$.getBoolean("csoCanReg")//能注册 [非空]
	 	,$.getBoolean("csoCanOrder")//能预订 [非空]
	 	,$.getInteger("csoPriority")//优先级
	 	,$.getInteger("csoVoteS")//人气
	 	,$.getString("csoProfile")//描述
	 	,$.getDate("csoOnlineTime")//上线时间
	 	,$.getDate("csoUpdateTime")//修改时间
	 	,$.getDate("csoAddTime")//添加时间 [非空]
	 	,$.getShort("csoStatus")//状态 [非空]
	 )
	**/
	public CsOutlets(Long csoHost,String csoName,Long csoArea,Short csoType,Double csoLongitude,Double csoLatitude,String csoAddress,String csoSpace,String csoDetails,String csoDoorTime,Double csoCost,String csoPerson,String csoContact,Date csoUntil,Date csoExpire,Date csoPileTime,Short csoCarS,Short csoPositionS,String csoImage,Boolean csoCanA,Boolean csoCanB,Boolean csoCanReg,Boolean csoCanOrder,Integer csoPriority,Integer csoVoteS,String csoProfile,Date csoOnlineTime,Date csoUpdateTime,Date csoAddTime,Short csoStatus){
		this.csoHost=csoHost;
		this.csoName=csoName;
		this.csoArea=csoArea;
		this.csoType=csoType;
		this.csoLongitude=csoLongitude;
		this.csoLatitude=csoLatitude;
		this.csoAddress=csoAddress;
		this.csoSpace=csoSpace;
		this.csoDetails=csoDetails;
		this.csoDoorTime=csoDoorTime;
		this.csoCost=csoCost;
		this.csoPerson=csoPerson;
		this.csoContact=csoContact;
		this.csoUntil=csoUntil;
		this.csoExpire=csoExpire;
		this.csoPileTime=csoPileTime;
		this.csoCarS=csoCarS;
		this.csoPositionS=csoPositionS;
		this.csoImage=csoImage;
		this.csoCanA=csoCanA;
		this.csoCanB=csoCanB;
		this.csoCanReg=csoCanReg;
		this.csoCanOrder=csoCanOrder;
		this.csoPriority=csoPriority;
		this.csoVoteS=csoVoteS;
		this.csoProfile=csoProfile;
		this.csoOnlineTime=csoOnlineTime;
		this.csoUpdateTime=csoUpdateTime;
		this.csoAddTime=csoAddTime;
		this.csoStatus=csoStatus;
	}
	
	//设置非空字段
	public CsOutlets setNotNull(Long csoHost,String csoName,Long csoArea,Short csoType,Double csoLongitude,Double csoLatitude,String csoAddress,Short csoCarS,Short csoPositionS,Boolean csoCanA,Boolean csoCanB,Boolean csoCanReg,Boolean csoCanOrder,Date csoAddTime,Short csoStatus){
		this.csoHost=csoHost;
		this.csoName=csoName;
		this.csoArea=csoArea;
		this.csoType=csoType;
		this.csoLongitude=csoLongitude;
		this.csoLatitude=csoLatitude;
		this.csoAddress=csoAddress;
		this.csoCarS=csoCarS;
		this.csoPositionS=csoPositionS;
		this.csoCanA=csoCanA;
		this.csoCanB=csoCanB;
		this.csoCanReg=csoCanReg;
		this.csoCanOrder=csoCanOrder;
		this.csoAddTime=csoAddTime;
		this.csoStatus=csoStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsOutlets csoId(Long csoId){
		this.csoId = csoId;
		this.setSeted(F.csoId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsOutlets csoHost(Long csoHost){
		this.csoHost = csoHost;
		this.setSeted(F.csoHost);
		return this;
	}
	/** 名称 [非空]       **/
	public CsOutlets csoName(String csoName){
		this.csoName = csoName;
		this.setSeted(F.csoName);
		return this;
	}
	/** 所在区域 [非空] [CsArea]      **/
	public CsOutlets csoArea(Long csoArea){
		this.csoArea = csoArea;
		this.setSeted(F.csoArea);
		return this;
	}
	/** 网点类型 [非空]   0:汽油车网点 1:电动车网点     **/
	public CsOutlets csoType(Short csoType){
		this.csoType = csoType;
		this.setSeted(F.csoType);
		return this;
	}
	/** 经度 [非空]       **/
	public CsOutlets csoLongitude(Double csoLongitude){
		this.csoLongitude = csoLongitude;
		this.setSeted(F.csoLongitude);
		return this;
	}
	/** 纬度 [非空]       **/
	public CsOutlets csoLatitude(Double csoLatitude){
		this.csoLatitude = csoLatitude;
		this.setSeted(F.csoLatitude);
		return this;
	}
	/** 地址 [非空]       **/
	public CsOutlets csoAddress(String csoAddress){
		this.csoAddress = csoAddress;
		this.setSeted(F.csoAddress);
		return this;
	}
	/** 停车地点        **/
	public CsOutlets csoSpace(String csoSpace){
		this.csoSpace = csoSpace;
		this.setSeted(F.csoSpace);
		return this;
	}
	/** 网点介绍        **/
	public CsOutlets csoDetails(String csoDetails){
		this.csoDetails = csoDetails;
		this.setSeted(F.csoDetails);
		return this;
	}
	/** 门禁时间        **/
	public CsOutlets csoDoorTime(String csoDoorTime){
		this.csoDoorTime = csoDoorTime;
		this.setSeted(F.csoDoorTime);
		return this;
	}
	/** 停车费        **/
	public CsOutlets csoCost(Double csoCost){
		this.csoCost = csoCost;
		this.setSeted(F.csoCost);
		return this;
	}
	/** 联系人        **/
	public CsOutlets csoPerson(String csoPerson){
		this.csoPerson = csoPerson;
		this.setSeted(F.csoPerson);
		return this;
	}
	/** 联系电话        **/
	public CsOutlets csoContact(String csoContact){
		this.csoContact = csoContact;
		this.setSeted(F.csoContact);
		return this;
	}
	/** 缴费到期        **/
	public CsOutlets csoUntil(Date csoUntil){
		this.csoUntil = csoUntil;
		this.setSeted(F.csoUntil);
		return this;
	}
	/** 合约到期        **/
	public CsOutlets csoExpire(Date csoExpire){
		this.csoExpire = csoExpire;
		this.setSeted(F.csoExpire);
		return this;
	}
	/** 电桩建立时间        **/
	public CsOutlets csoPileTime(Date csoPileTime){
		this.csoPileTime = csoPileTime;
		this.setSeted(F.csoPileTime);
		return this;
	}
	/** 已有车辆数 [非空]       **/
	public CsOutlets csoCarS(Short csoCarS){
		this.csoCarS = csoCarS;
		this.setSeted(F.csoCarS);
		return this;
	}
	/** 车位数 [非空]       **/
	public CsOutlets csoPositionS(Short csoPositionS){
		this.csoPositionS = csoPositionS;
		this.setSeted(F.csoPositionS);
		return this;
	}
	/** 网点图片    400:width 300:height     **/
	public CsOutlets csoImage(String csoImage){
		this.csoImage = csoImage;
		this.setSeted(F.csoImage);
		return this;
	}
	/** AB借出 [非空]    是否支持A借B还中的借出   **/
	public CsOutlets csoCanA(Boolean csoCanA){
		this.csoCanA = csoCanA;
		this.setSeted(F.csoCanA);
		return this;
	}
	/** AB还入 [非空]    是否支持A借B还中的还入   **/
	public CsOutlets csoCanB(Boolean csoCanB){
		this.csoCanB = csoCanB;
		this.setSeted(F.csoCanB);
		return this;
	}
	/** 能注册 [非空]    可作为会员默认网点的选取项   **/
	public CsOutlets csoCanReg(Boolean csoCanReg){
		this.csoCanReg = csoCanReg;
		this.setSeted(F.csoCanReg);
		return this;
	}
	/** 能预订 [非空]    描述该网点是否可以下单   **/
	public CsOutlets csoCanOrder(Boolean csoCanOrder){
		this.csoCanOrder = csoCanOrder;
		this.setSeted(F.csoCanOrder);
		return this;
	}
	/** 优先级        **/
	public CsOutlets csoPriority(Integer csoPriority){
		this.csoPriority = csoPriority;
		this.setSeted(F.csoPriority);
		return this;
	}
	/** 人气        **/
	public CsOutlets csoVoteS(Integer csoVoteS){
		this.csoVoteS = csoVoteS;
		this.setSeted(F.csoVoteS);
		return this;
	}
	/** 描述        **/
	public CsOutlets csoProfile(String csoProfile){
		this.csoProfile = csoProfile;
		this.setSeted(F.csoProfile);
		return this;
	}
	/** 上线时间        **/
	public CsOutlets csoOnlineTime(Date csoOnlineTime){
		this.csoOnlineTime = csoOnlineTime;
		this.setSeted(F.csoOnlineTime);
		return this;
	}
	/** 修改时间        **/
	public CsOutlets csoUpdateTime(Date csoUpdateTime){
		this.csoUpdateTime = csoUpdateTime;
		this.setSeted(F.csoUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsOutlets csoAddTime(Date csoAddTime){
		this.csoAddTime = csoAddTime;
		this.setSeted(F.csoAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效  描述该网点是否让会员看得到   **/
	public CsOutlets csoStatus(Short csoStatus){
		this.csoStatus = csoStatus;
		this.setSeted(F.csoStatus);
		return this;
	}
	
	
	//克隆对象
	public CsOutlets clone(){
		CsOutlets clone = new CsOutlets();
		clone.csoHost=this.csoHost;
		clone.csoName=this.csoName;
		clone.csoArea=this.csoArea;
		clone.csoType=this.csoType;
		clone.csoLongitude=this.csoLongitude;
		clone.csoLatitude=this.csoLatitude;
		clone.csoAddress=this.csoAddress;
		clone.csoCarS=this.csoCarS;
		clone.csoPositionS=this.csoPositionS;
		clone.csoCanA=this.csoCanA;
		clone.csoCanB=this.csoCanB;
		clone.csoCanReg=this.csoCanReg;
		clone.csoCanOrder=this.csoCanOrder;
		clone.csoAddTime=this.csoAddTime;
		clone.csoStatus=this.csoStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取网点
	 * @param id
	 * @return
	 */
	public static @api CsOutlets get(Long id){		
		return getCsOutletsById(id);
	}
	/**
	 * 获取所有网点
	 * @return
	 */
	public static @api List<CsOutlets> list(Map params,Integer size){
		return getCsOutletsList(params,size);
	}
	/**
	 * 获取网点分页
	 * @return
	 */
	public static @api Page<CsOutlets> page(int page,int size,Map params){
		return getCsOutletsPage(page, size , params);
	}
	/**
	 * 根据查询条件取网点
	 * @param params
	 * @return
	 */
	public static @api CsOutlets Get(Map params){
		return getCsOutlets(params);
	}
	/**
	 * 获取网点数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsOutletsCount(params);
	}
	/**
	 * 获取网点数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsOutletsEval(eval,params);
	}
	
	/**
	 * 根据ID取网点
	 * @param id
	 * @return
	 */
	public static @api CsOutlets getCsOutletsById(Long id){		
		CsOutlets csOutlets = (CsOutlets) $.GetRequest("CsOutlets$"+id);
		if(csOutlets!=null)
			return csOutlets;
		com.ccclubs.service.admin.ICsOutletsService csOutletsService = $.GetSpringBean("csOutletsService");		
		return csOutletsService.getCsOutletsById(id);
	}
	
	
	/**
	 * 根据ID取网点的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsOutlets.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsOutlets csOutlets = get(id);
		if(csOutlets!=null){
			String strValue = csOutlets.getCsoName$();
			if(!"CsoName".equals("CsoName"))
				strValue+="("+csOutlets.getCsoName$()+")";
			if(!"CsoName".equals("CsoArea"))
				strValue+="("+csOutlets.getCsoArea$()+")";
			MemCache.setValue(CsOutlets.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsoName$();
		if(!"CsoName".equals("CsoName"))
			keyValue+="("+this.getCsoName$()+")";
		if(!"CsoName".equals("CsoArea"))
			keyValue+="("+this.getCsoArea$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有网点
	 * @return
	 */
	public static @api List<CsOutlets> getCsOutletsList(Map params,Integer size){
		com.ccclubs.service.admin.ICsOutletsService csOutletsService = $.GetSpringBean("csOutletsService");
		return csOutletsService.getCsOutletsList(params, size);
	}
	
	/**
	 * 获取网点分页
	 * @return
	 */
	public static @api Page<CsOutlets> getCsOutletsPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsOutletsService csOutletsService = $.GetSpringBean("csOutletsService");
		return csOutletsService.getCsOutletsPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取网点
	 * @param params
	 * @return
	 */
	public static @api CsOutlets getCsOutlets(Map params){
		com.ccclubs.service.admin.ICsOutletsService csOutletsService = $.GetSpringBean("csOutletsService");
		return csOutletsService.getCsOutlets(params);
	}
	
	/**
	 * 获取网点数
	 * @return
	 */
	public static @api Long getCsOutletsCount(Map params){
		com.ccclubs.service.admin.ICsOutletsService csOutletsService = $.GetSpringBean("csOutletsService");
		return csOutletsService.getCsOutletsCount(params);
	}
		
		
	/**
	 * 获取网点自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsOutletsEval(String eval,Map params){
		com.ccclubs.service.admin.ICsOutletsService csOutletsService = $.GetSpringBean("csOutletsService");
		return csOutletsService.getCsOutletsEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsOutlets(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsOutletsService csOutletsService = $.GetSpringBean("csOutletsService");
		csOutletsService.updateCsOutletsByConfirm(set, where);
	}
	
	
	/**
	 * 保存网点对象
	 * @param params
	 * @return
	 */
	public CsOutlets save(){
		com.ccclubs.service.admin.ICsOutletsService csOutletsService = $.GetSpringBean("csOutletsService");
		if(this.getCsoId()!=null)
			csOutletsService.updateCsOutlets(this);
		else
			return csOutletsService.saveCsOutlets(this);
		return this;
	}
	
	
	/**
	 * 更新网点对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsOutletsService csOutletsService = $.GetSpringBean("csOutletsService");
		csOutletsService.updateCsOutlets$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsOutletsService csOutletsService = $.GetSpringBean("csOutletsService");
		if($.equals($.config("logic_delete"),"true"))
			csOutletsService.removeCsOutletsById(this.getCsoId());
		else
			csOutletsService.deleteCsOutletsById(this.getCsoId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsOutletsService csOutletsService = $.GetSpringBean("csOutletsService");
		return csOutletsService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsoId(){
		return this.csoId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsoId$(){
		String strValue="";
		 strValue=$.str(this.getCsoId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsoId(Long csoId){
		this.csoId = csoId;
		this.setSeted(F.csoId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsoHost(){
		return this.csoHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsoHost$(){
		String strValue="";
		if(this.getCsoHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsoHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsoHost(Long csoHost){
		this.csoHost = csoHost;
		this.setSeted(F.csoHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csoHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsoHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsoHost()!=null){
 			srvHost = SrvHost.get(this.getCsoHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsoHost(), srvHost);
	 	return srvHost;
	}
	/*******************************名称**********************************/	
	/**
	* 名称 [非空]      
	**/
	public String getCsoName(){
		return this.csoName;
	}
	/**
	* 获取名称格式化(toString)
	**/
	public String getCsoName$(){
		String strValue="";
		 strValue=$.str(this.getCsoName());
	 	 return strValue;
	}
	/**
	* 名称 [非空]      
	**/
	public void setCsoName(String csoName){
		this.csoName = csoName;
		this.setSeted(F.csoName);
	}
	/*******************************所在区域**********************************/	
	/**
	* 所在区域 [非空] [CsArea]     
	**/
	public Long getCsoArea(){
		return this.csoArea;
	}
	/**
	* 获取所在区域格式化(toString)
	**/
	public String getCsoArea$(){
		String strValue="";
		if(this.getCsoArea()!=null){
				strValue+=$.str(CsArea.getKeyValue(this.getCsoArea()));
		}			
	 	 return strValue;
	}
	/**
	* 所在区域 [非空] [CsArea]     
	**/
	public void setCsoArea(Long csoArea){
		this.csoArea = csoArea;
		this.setSeted(F.csoArea);
	}
	/**
	* 获取关联对象[网点区域]
	**/	 			
 	public CsArea get$csoArea(){
 		com.ccclubs.model.CsArea csArea = (com.ccclubs.model.CsArea) $.GetRequest("CsArea$"+this.getCsoArea());
 		if(csArea!=null)
			return csArea;
		if(this.getCsoArea()!=null){
 			csArea = CsArea.get(this.getCsoArea());
 		}
 		$.SetRequest("CsArea$"+this.getCsoArea(), csArea);
	 	return csArea;
	}
	/*******************************网点类型**********************************/	
	/**
	* 网点类型 [非空]   0:汽油车网点 1:电动车网点    
	**/
	public Short getCsoType(){
		return this.csoType;
	}
	/**
	* 获取网点类型格式化(toString)
	**/
	public String getCsoType$(){
		String strValue="";
		 if($.equals($.str(this.getCsoType()),"0"))
			strValue=$.str("汽油车网点");		 
		 if($.equals($.str(this.getCsoType()),"1"))
			strValue=$.str("电动车网点");		 
	 	 return strValue;
	}
	/**
	* 网点类型 [非空]   0:汽油车网点 1:电动车网点    
	**/
	public void setCsoType(Short csoType){
		this.csoType = csoType;
		this.setSeted(F.csoType);
	}
	/*******************************经度**********************************/	
	/**
	* 经度 [非空]      
	**/
	public Double getCsoLongitude(){
		return this.csoLongitude;
	}
	/**
	* 获取经度格式化(toString)
	**/
	public String getCsoLongitude$(){
		String strValue="";
		 strValue=$.str(this.getCsoLongitude());	
	 	 return strValue;
	}
	/**
	* 经度 [非空]      
	**/
	public void setCsoLongitude(Double csoLongitude){
		this.csoLongitude = csoLongitude;
		this.setSeted(F.csoLongitude);
	}
	/*******************************纬度**********************************/	
	/**
	* 纬度 [非空]      
	**/
	public Double getCsoLatitude(){
		return this.csoLatitude;
	}
	/**
	* 获取纬度格式化(toString)
	**/
	public String getCsoLatitude$(){
		String strValue="";
		 strValue=$.str(this.getCsoLatitude());	
	 	 return strValue;
	}
	/**
	* 纬度 [非空]      
	**/
	public void setCsoLatitude(Double csoLatitude){
		this.csoLatitude = csoLatitude;
		this.setSeted(F.csoLatitude);
	}
	/*******************************反向关联的车辆列表**********************************/	
	/**
	* 获取与当前对象反向关联的车辆列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsCar> getCsoCars(){
		com.ccclubs.service.admin.ICsCarService csCarService = $.GetSpringBean("csCarService");
		Map params = new HashMap();
		params.put("cscOutlets",this.getCsoId());
		return csCarService.getCsCarList(params,-1);
	}
	/**
	* 获取车辆列表格式化(toString)
	**/
	public String getCsoCars$(){
		return "[...]";
	}
	/*******************************地址**********************************/	
	/**
	* 地址 [非空]      
	**/
	public String getCsoAddress(){
		return this.csoAddress;
	}
	/**
	* 获取地址格式化(toString)
	**/
	public String getCsoAddress$(){
		String strValue="";
		 strValue=$.str(this.getCsoAddress());
	 	 return strValue;
	}
	/**
	* 地址 [非空]      
	**/
	public void setCsoAddress(String csoAddress){
		this.csoAddress = csoAddress;
		this.setSeted(F.csoAddress);
	}
	/*******************************停车地点**********************************/	
	/**
	* 停车地点       
	**/
	public String getCsoSpace(){
		return this.csoSpace;
	}
	/**
	* 获取停车地点格式化(toString)
	**/
	public String getCsoSpace$(){
		String strValue="";
		 strValue=$.str(this.getCsoSpace());
	 	 return strValue;
	}
	/**
	* 停车地点       
	**/
	public void setCsoSpace(String csoSpace){
		this.csoSpace = csoSpace;
		this.setSeted(F.csoSpace);
	}
	/*******************************网点介绍**********************************/	
	/**
	* 网点介绍       
	**/
	public String getCsoDetails(){
		return this.csoDetails;
	}
	/**
	* 获取网点介绍格式化(toString)
	**/
	public String getCsoDetails$(){
		String strValue="";
		 strValue=$.str(this.getCsoDetails());
	 	 return strValue;
	}
	/**
	* 网点介绍       
	**/
	public void setCsoDetails(String csoDetails){
		this.csoDetails = csoDetails;
		this.setSeted(F.csoDetails);
	}
	/*******************************门禁时间**********************************/	
	/**
	* 门禁时间       
	**/
	public String getCsoDoorTime(){
		return this.csoDoorTime;
	}
	/**
	* 获取门禁时间格式化(toString)
	**/
	public String getCsoDoorTime$(){
		String strValue="";
		 strValue=$.str(this.getCsoDoorTime());
	 	 return strValue;
	}
	/**
	* 门禁时间       
	**/
	public void setCsoDoorTime(String csoDoorTime){
		this.csoDoorTime = csoDoorTime;
		this.setSeted(F.csoDoorTime);
	}
	/*******************************停车费**********************************/	
	/**
	* 停车费       
	**/
	public Double getCsoCost(){
		return this.csoCost;
	}
	/**
	* 获取停车费格式化(toString)
	**/
	public String getCsoCost$(){
		String strValue="";
		 strValue=$.str(this.getCsoCost());	
	 	 return strValue;
	}
	/**
	* 停车费       
	**/
	public void setCsoCost(Double csoCost){
		this.csoCost = csoCost;
		this.setSeted(F.csoCost);
	}
	/*******************************联系人**********************************/	
	/**
	* 联系人       
	**/
	public String getCsoPerson(){
		return this.csoPerson;
	}
	/**
	* 获取联系人格式化(toString)
	**/
	public String getCsoPerson$(){
		String strValue="";
		 strValue=$.str(this.getCsoPerson());
	 	 return strValue;
	}
	/**
	* 联系人       
	**/
	public void setCsoPerson(String csoPerson){
		this.csoPerson = csoPerson;
		this.setSeted(F.csoPerson);
	}
	/*******************************联系电话**********************************/	
	/**
	* 联系电话       
	**/
	public String getCsoContact(){
		return this.csoContact;
	}
	/**
	* 获取联系电话格式化(toString)
	**/
	public String getCsoContact$(){
		String strValue="";
		 strValue=$.str(this.getCsoContact());
	 	 return strValue;
	}
	/**
	* 联系电话       
	**/
	public void setCsoContact(String csoContact){
		this.csoContact = csoContact;
		this.setSeted(F.csoContact);
	}
	/*******************************缴费到期**********************************/	
	/**
	* 缴费到期       
	**/
	public Date getCsoUntil(){
		return this.csoUntil;
	}
	/**
	* 获取缴费到期格式化(toString)
	**/
	public String getCsoUntil$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsoUntil(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 缴费到期       
	**/
	public void setCsoUntil(Date csoUntil){
		this.csoUntil = csoUntil;
		this.setSeted(F.csoUntil);
	}
	/*******************************合约到期**********************************/	
	/**
	* 合约到期       
	**/
	public Date getCsoExpire(){
		return this.csoExpire;
	}
	/**
	* 获取合约到期格式化(toString)
	**/
	public String getCsoExpire$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsoExpire(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 合约到期       
	**/
	public void setCsoExpire(Date csoExpire){
		this.csoExpire = csoExpire;
		this.setSeted(F.csoExpire);
	}
	/*******************************电桩建立时间**********************************/	
	/**
	* 电桩建立时间       
	**/
	public Date getCsoPileTime(){
		return this.csoPileTime;
	}
	/**
	* 获取电桩建立时间格式化(toString)
	**/
	public String getCsoPileTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsoPileTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 电桩建立时间       
	**/
	public void setCsoPileTime(Date csoPileTime){
		this.csoPileTime = csoPileTime;
		this.setSeted(F.csoPileTime);
	}
	/*******************************已有车辆数**********************************/	
	/**
	* 已有车辆数 [非空]      
	**/
	public Short getCsoCarS(){
		return this.csoCarS;
	}
	/**
	* 获取已有车辆数格式化(toString)
	**/
	public String getCsoCarS$(){
		String strValue="";
		 strValue=$.str(this.getCsoCarS());
	 	 return strValue;
	}
	/**
	* 已有车辆数 [非空]      
	**/
	public void setCsoCarS(Short csoCarS){
		this.csoCarS = csoCarS;
		this.setSeted(F.csoCarS);
	}
	/*******************************车位数**********************************/	
	/**
	* 车位数 [非空]      
	**/
	public Short getCsoPositionS(){
		return this.csoPositionS;
	}
	/**
	* 获取车位数格式化(toString)
	**/
	public String getCsoPositionS$(){
		String strValue="";
		 strValue=$.str(this.getCsoPositionS());
	 	 return strValue;
	}
	/**
	* 车位数 [非空]      
	**/
	public void setCsoPositionS(Short csoPositionS){
		this.csoPositionS = csoPositionS;
		this.setSeted(F.csoPositionS);
	}
	/*******************************网点图片**********************************/	
	/**
	* 网点图片    400:width 300:height    
	**/
	public String getCsoImage(){
		return this.csoImage;
	}
	/**
	* 获取网点图片格式化(toString)
	**/
	public String getCsoImage$(){
		String strValue="";
		 strValue=$.str(this.getCsoImage());
	 	 return strValue;
	}
	/**
	* 网点图片    400:width 300:height    
	**/
	public void setCsoImage(String csoImage){
		this.csoImage = csoImage;
		this.setSeted(F.csoImage);
	}
	/*******************************AB借出**********************************/	
	/**
	* AB借出 [非空]    是否支持A借B还中的借出  
	**/
	public Boolean getCsoCanA(){
		return this.csoCanA;
	}
	/**
	* 获取AB借出格式化(toString)
	**/
	public String getCsoCanA$(){
		String strValue="";
		 strValue=$.str((this.getCsoCanA()!=null && this.getCsoCanA())?"是":"否");					 	
	 	 return strValue;
	}
	/**
	* AB借出 [非空]    是否支持A借B还中的借出  
	**/
	public void setCsoCanA(Boolean csoCanA){
		this.csoCanA = csoCanA;
		this.setSeted(F.csoCanA);
	}
	/*******************************AB还入**********************************/	
	/**
	* AB还入 [非空]    是否支持A借B还中的还入  
	**/
	public Boolean getCsoCanB(){
		return this.csoCanB;
	}
	/**
	* 获取AB还入格式化(toString)
	**/
	public String getCsoCanB$(){
		String strValue="";
		 strValue=$.str((this.getCsoCanB()!=null && this.getCsoCanB())?"是":"否");					 	
	 	 return strValue;
	}
	/**
	* AB还入 [非空]    是否支持A借B还中的还入  
	**/
	public void setCsoCanB(Boolean csoCanB){
		this.csoCanB = csoCanB;
		this.setSeted(F.csoCanB);
	}
	/*******************************能注册**********************************/	
	/**
	* 能注册 [非空]    可作为会员默认网点的选取项  
	**/
	public Boolean getCsoCanReg(){
		return this.csoCanReg;
	}
	/**
	* 获取能注册格式化(toString)
	**/
	public String getCsoCanReg$(){
		String strValue="";
		 strValue=$.str((this.getCsoCanReg()!=null && this.getCsoCanReg())?"是":"否");					 	
	 	 return strValue;
	}
	/**
	* 能注册 [非空]    可作为会员默认网点的选取项  
	**/
	public void setCsoCanReg(Boolean csoCanReg){
		this.csoCanReg = csoCanReg;
		this.setSeted(F.csoCanReg);
	}
	/*******************************能预订**********************************/	
	/**
	* 能预订 [非空]    描述该网点是否可以下单  
	**/
	public Boolean getCsoCanOrder(){
		return this.csoCanOrder;
	}
	/**
	* 获取能预订格式化(toString)
	**/
	public String getCsoCanOrder$(){
		String strValue="";
		 strValue=$.str((this.getCsoCanOrder()!=null && this.getCsoCanOrder())?"是":"否");					 	
	 	 return strValue;
	}
	/**
	* 能预订 [非空]    描述该网点是否可以下单  
	**/
	public void setCsoCanOrder(Boolean csoCanOrder){
		this.csoCanOrder = csoCanOrder;
		this.setSeted(F.csoCanOrder);
	}
	/*******************************优先级**********************************/	
	/**
	* 优先级       
	**/
	public Integer getCsoPriority(){
		return this.csoPriority;
	}
	/**
	* 获取优先级格式化(toString)
	**/
	public String getCsoPriority$(){
		String strValue="";
		 strValue=$.str(this.getCsoPriority());
	 	 return strValue;
	}
	/**
	* 优先级       
	**/
	public void setCsoPriority(Integer csoPriority){
		this.csoPriority = csoPriority;
		this.setSeted(F.csoPriority);
	}
	/*******************************人气**********************************/	
	/**
	* 人气       
	**/
	public Integer getCsoVoteS(){
		return this.csoVoteS;
	}
	/**
	* 获取人气格式化(toString)
	**/
	public String getCsoVoteS$(){
		String strValue="";
		 strValue=$.str(this.getCsoVoteS());
	 	 return strValue;
	}
	/**
	* 人气       
	**/
	public void setCsoVoteS(Integer csoVoteS){
		this.csoVoteS = csoVoteS;
		this.setSeted(F.csoVoteS);
	}
	/*******************************描述**********************************/	
	/**
	* 描述       
	**/
	public String getCsoProfile(){
		return this.csoProfile;
	}
	/**
	* 获取描述格式化(toString)
	**/
	public String getCsoProfile$(){
		String strValue="";
		 strValue=$.str(this.getCsoProfile());
	 	 return strValue;
	}
	/**
	* 描述       
	**/
	public void setCsoProfile(String csoProfile){
		this.csoProfile = csoProfile;
		this.setSeted(F.csoProfile);
	}
	/*******************************上线时间**********************************/	
	/**
	* 上线时间       
	**/
	public Date getCsoOnlineTime(){
		return this.csoOnlineTime;
	}
	/**
	* 获取上线时间格式化(toString)
	**/
	public String getCsoOnlineTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsoOnlineTime(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 上线时间       
	**/
	public void setCsoOnlineTime(Date csoOnlineTime){
		this.csoOnlineTime = csoOnlineTime;
		this.setSeted(F.csoOnlineTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间       
	**/
	public Date getCsoUpdateTime(){
		return this.csoUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsoUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsoUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间       
	**/
	public void setCsoUpdateTime(Date csoUpdateTime){
		this.csoUpdateTime = csoUpdateTime;
		this.setSeted(F.csoUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsoAddTime(){
		return this.csoAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsoAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsoAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsoAddTime(Date csoAddTime){
		this.csoAddTime = csoAddTime;
		this.setSeted(F.csoAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效  描述该网点是否让会员看得到  
	**/
	public Short getCsoStatus(){
		return this.csoStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsoStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsoStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsoStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效  描述该网点是否让会员看得到  
	**/
	public void setCsoStatus(Short csoStatus){
		this.csoStatus = csoStatus;
		this.setSeted(F.csoStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsOutlets.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsOutlets.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsOutlets.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsOutlets.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsOutlets.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsOutlets.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsOutlets.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsOutlets.this);
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
		public M csoId(Object csoId){this.put("csoId", csoId);return this;};
	 	/** and cso_id is null */
 		public M csoIdNull(){if(this.get("csoIdNot")==null)this.put("csoIdNot", "");this.put("csoId", null);return this;};
 		/** not .... */
 		public M csoIdNot(){this.put("csoIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csoHost(Object csoHost){this.put("csoHost", csoHost);return this;};
	 	/** and cso_host is null */
 		public M csoHostNull(){if(this.get("csoHostNot")==null)this.put("csoHostNot", "");this.put("csoHost", null);return this;};
 		/** not .... */
 		public M csoHostNot(){this.put("csoHostNot", "not");return this;};
		/** 名称 [非空]       **/
		public M csoName(Object csoName){this.put("csoName", csoName);return this;};
	 	/** and cso_name is null */
 		public M csoNameNull(){if(this.get("csoNameNot")==null)this.put("csoNameNot", "");this.put("csoName", null);return this;};
 		/** not .... */
 		public M csoNameNot(){this.put("csoNameNot", "not");return this;};
		/** 所在区域 [非空] [CsArea]      **/
		public M csoArea(Object csoArea){this.put("csoArea", csoArea);return this;};
	 	/** and cso_area is null */
 		public M csoAreaNull(){if(this.get("csoAreaNot")==null)this.put("csoAreaNot", "");this.put("csoArea", null);return this;};
 		/** not .... */
 		public M csoAreaNot(){this.put("csoAreaNot", "not");return this;};
		public M csoArea$on(CsArea.M value){
			this.put("CsArea", value);
			this.put("csoArea$on", value);
			return this;
		};	
		/** 网点类型 [非空]   0:汽油车网点 1:电动车网点     **/
		public M csoType(Object csoType){this.put("csoType", csoType);return this;};
	 	/** and cso_type is null */
 		public M csoTypeNull(){if(this.get("csoTypeNot")==null)this.put("csoTypeNot", "");this.put("csoType", null);return this;};
 		/** not .... */
 		public M csoTypeNot(){this.put("csoTypeNot", "not");return this;};
		/** 经度 [非空]       **/
		public M csoLongitude(Object csoLongitude){this.put("csoLongitude", csoLongitude);return this;};
	 	/** and cso_longitude is null */
 		public M csoLongitudeNull(){if(this.get("csoLongitudeNot")==null)this.put("csoLongitudeNot", "");this.put("csoLongitude", null);return this;};
 		/** not .... */
 		public M csoLongitudeNot(){this.put("csoLongitudeNot", "not");return this;};
		/** and cso_longitude >= ? */
		public M csoLongitudeMin(Object min){this.put("csoLongitudeMin", min);return this;};
		/** and cso_longitude <= ? */
		public M csoLongitudeMax(Object max){this.put("csoLongitudeMax", max);return this;};
		/** 纬度 [非空]       **/
		public M csoLatitude(Object csoLatitude){this.put("csoLatitude", csoLatitude);return this;};
	 	/** and cso_latitude is null */
 		public M csoLatitudeNull(){if(this.get("csoLatitudeNot")==null)this.put("csoLatitudeNot", "");this.put("csoLatitude", null);return this;};
 		/** not .... */
 		public M csoLatitudeNot(){this.put("csoLatitudeNot", "not");return this;};
		/** and cso_latitude >= ? */
		public M csoLatitudeMin(Object min){this.put("csoLatitudeMin", min);return this;};
		/** and cso_latitude <= ? */
		public M csoLatitudeMax(Object max){this.put("csoLatitudeMax", max);return this;};
		/** 地址 [非空]       **/
		public M csoAddress(Object csoAddress){this.put("csoAddress", csoAddress);return this;};
	 	/** and cso_address is null */
 		public M csoAddressNull(){if(this.get("csoAddressNot")==null)this.put("csoAddressNot", "");this.put("csoAddress", null);return this;};
 		/** not .... */
 		public M csoAddressNot(){this.put("csoAddressNot", "not");return this;};
		/** 停车地点        **/
		public M csoSpace(Object csoSpace){this.put("csoSpace", csoSpace);return this;};
	 	/** and cso_space is null */
 		public M csoSpaceNull(){if(this.get("csoSpaceNot")==null)this.put("csoSpaceNot", "");this.put("csoSpace", null);return this;};
 		/** not .... */
 		public M csoSpaceNot(){this.put("csoSpaceNot", "not");return this;};
		/** 网点介绍        **/
		public M csoDetails(Object csoDetails){this.put("csoDetails", csoDetails);return this;};
	 	/** and cso_details is null */
 		public M csoDetailsNull(){if(this.get("csoDetailsNot")==null)this.put("csoDetailsNot", "");this.put("csoDetails", null);return this;};
 		/** not .... */
 		public M csoDetailsNot(){this.put("csoDetailsNot", "not");return this;};
		/** 门禁时间        **/
		public M csoDoorTime(Object csoDoorTime){this.put("csoDoorTime", csoDoorTime);return this;};
	 	/** and cso_door_time is null */
 		public M csoDoorTimeNull(){if(this.get("csoDoorTimeNot")==null)this.put("csoDoorTimeNot", "");this.put("csoDoorTime", null);return this;};
 		/** not .... */
 		public M csoDoorTimeNot(){this.put("csoDoorTimeNot", "not");return this;};
		/** 停车费        **/
		public M csoCost(Object csoCost){this.put("csoCost", csoCost);return this;};
	 	/** and cso_cost is null */
 		public M csoCostNull(){if(this.get("csoCostNot")==null)this.put("csoCostNot", "");this.put("csoCost", null);return this;};
 		/** not .... */
 		public M csoCostNot(){this.put("csoCostNot", "not");return this;};
		/** and cso_cost >= ? */
		public M csoCostMin(Object min){this.put("csoCostMin", min);return this;};
		/** and cso_cost <= ? */
		public M csoCostMax(Object max){this.put("csoCostMax", max);return this;};
		/** 联系人        **/
		public M csoPerson(Object csoPerson){this.put("csoPerson", csoPerson);return this;};
	 	/** and cso_person is null */
 		public M csoPersonNull(){if(this.get("csoPersonNot")==null)this.put("csoPersonNot", "");this.put("csoPerson", null);return this;};
 		/** not .... */
 		public M csoPersonNot(){this.put("csoPersonNot", "not");return this;};
		/** 联系电话        **/
		public M csoContact(Object csoContact){this.put("csoContact", csoContact);return this;};
	 	/** and cso_contact is null */
 		public M csoContactNull(){if(this.get("csoContactNot")==null)this.put("csoContactNot", "");this.put("csoContact", null);return this;};
 		/** not .... */
 		public M csoContactNot(){this.put("csoContactNot", "not");return this;};
		/** 缴费到期        **/
		public M csoUntil(Object csoUntil){this.put("csoUntil", csoUntil);return this;};
	 	/** and cso_until is null */
 		public M csoUntilNull(){if(this.get("csoUntilNot")==null)this.put("csoUntilNot", "");this.put("csoUntil", null);return this;};
 		/** not .... */
 		public M csoUntilNot(){this.put("csoUntilNot", "not");return this;};
 		/** and cso_until >= ? */
 		public M csoUntilStart(Object start){this.put("csoUntilStart", start);return this;};			
 		/** and cso_until <= ? */
 		public M csoUntilEnd(Object end){this.put("csoUntilEnd", end);return this;};
		/** 合约到期        **/
		public M csoExpire(Object csoExpire){this.put("csoExpire", csoExpire);return this;};
	 	/** and cso_expire is null */
 		public M csoExpireNull(){if(this.get("csoExpireNot")==null)this.put("csoExpireNot", "");this.put("csoExpire", null);return this;};
 		/** not .... */
 		public M csoExpireNot(){this.put("csoExpireNot", "not");return this;};
 		/** and cso_expire >= ? */
 		public M csoExpireStart(Object start){this.put("csoExpireStart", start);return this;};			
 		/** and cso_expire <= ? */
 		public M csoExpireEnd(Object end){this.put("csoExpireEnd", end);return this;};
		/** 电桩建立时间        **/
		public M csoPileTime(Object csoPileTime){this.put("csoPileTime", csoPileTime);return this;};
	 	/** and cso_pile_time is null */
 		public M csoPileTimeNull(){if(this.get("csoPileTimeNot")==null)this.put("csoPileTimeNot", "");this.put("csoPileTime", null);return this;};
 		/** not .... */
 		public M csoPileTimeNot(){this.put("csoPileTimeNot", "not");return this;};
 		/** and cso_pile_time >= ? */
 		public M csoPileTimeStart(Object start){this.put("csoPileTimeStart", start);return this;};			
 		/** and cso_pile_time <= ? */
 		public M csoPileTimeEnd(Object end){this.put("csoPileTimeEnd", end);return this;};
		/** 已有车辆数 [非空]       **/
		public M csoCarS(Object csoCarS){this.put("csoCarS", csoCarS);return this;};
	 	/** and cso_car_s is null */
 		public M csoCarSNull(){if(this.get("csoCarSNot")==null)this.put("csoCarSNot", "");this.put("csoCarS", null);return this;};
 		/** not .... */
 		public M csoCarSNot(){this.put("csoCarSNot", "not");return this;};
		/** and cso_car_s >= ? */
		public M csoCarSMin(Object min){this.put("csoCarSMin", min);return this;};
		/** and cso_car_s <= ? */
		public M csoCarSMax(Object max){this.put("csoCarSMax", max);return this;};
		/** 车位数 [非空]       **/
		public M csoPositionS(Object csoPositionS){this.put("csoPositionS", csoPositionS);return this;};
	 	/** and cso_position_s is null */
 		public M csoPositionSNull(){if(this.get("csoPositionSNot")==null)this.put("csoPositionSNot", "");this.put("csoPositionS", null);return this;};
 		/** not .... */
 		public M csoPositionSNot(){this.put("csoPositionSNot", "not");return this;};
		/** and cso_position_s >= ? */
		public M csoPositionSMin(Object min){this.put("csoPositionSMin", min);return this;};
		/** and cso_position_s <= ? */
		public M csoPositionSMax(Object max){this.put("csoPositionSMax", max);return this;};
		/** 网点图片    400:width 300:height     **/
		public M csoImage(Object csoImage){this.put("csoImage", csoImage);return this;};
	 	/** and cso_image is null */
 		public M csoImageNull(){if(this.get("csoImageNot")==null)this.put("csoImageNot", "");this.put("csoImage", null);return this;};
 		/** not .... */
 		public M csoImageNot(){this.put("csoImageNot", "not");return this;};
		/** AB借出 [非空]    是否支持A借B还中的借出   **/
		public M csoCanA(Object csoCanA){this.put("csoCanA", csoCanA);return this;};
	 	/** and cso_can_a is null */
 		public M csoCanANull(){if(this.get("csoCanANot")==null)this.put("csoCanANot", "");this.put("csoCanA", null);return this;};
 		/** not .... */
 		public M csoCanANot(){this.put("csoCanANot", "not");return this;};
		/** AB还入 [非空]    是否支持A借B还中的还入   **/
		public M csoCanB(Object csoCanB){this.put("csoCanB", csoCanB);return this;};
	 	/** and cso_can_b is null */
 		public M csoCanBNull(){if(this.get("csoCanBNot")==null)this.put("csoCanBNot", "");this.put("csoCanB", null);return this;};
 		/** not .... */
 		public M csoCanBNot(){this.put("csoCanBNot", "not");return this;};
		/** 能注册 [非空]    可作为会员默认网点的选取项   **/
		public M csoCanReg(Object csoCanReg){this.put("csoCanReg", csoCanReg);return this;};
	 	/** and cso_can_reg is null */
 		public M csoCanRegNull(){if(this.get("csoCanRegNot")==null)this.put("csoCanRegNot", "");this.put("csoCanReg", null);return this;};
 		/** not .... */
 		public M csoCanRegNot(){this.put("csoCanRegNot", "not");return this;};
		/** 能预订 [非空]    描述该网点是否可以下单   **/
		public M csoCanOrder(Object csoCanOrder){this.put("csoCanOrder", csoCanOrder);return this;};
	 	/** and cso_can_order is null */
 		public M csoCanOrderNull(){if(this.get("csoCanOrderNot")==null)this.put("csoCanOrderNot", "");this.put("csoCanOrder", null);return this;};
 		/** not .... */
 		public M csoCanOrderNot(){this.put("csoCanOrderNot", "not");return this;};
		/** 优先级        **/
		public M csoPriority(Object csoPriority){this.put("csoPriority", csoPriority);return this;};
	 	/** and cso_priority is null */
 		public M csoPriorityNull(){if(this.get("csoPriorityNot")==null)this.put("csoPriorityNot", "");this.put("csoPriority", null);return this;};
 		/** not .... */
 		public M csoPriorityNot(){this.put("csoPriorityNot", "not");return this;};
		/** and cso_priority >= ? */
		public M csoPriorityMin(Object min){this.put("csoPriorityMin", min);return this;};
		/** and cso_priority <= ? */
		public M csoPriorityMax(Object max){this.put("csoPriorityMax", max);return this;};
		/** 人气        **/
		public M csoVoteS(Object csoVoteS){this.put("csoVoteS", csoVoteS);return this;};
	 	/** and cso_vote_s is null */
 		public M csoVoteSNull(){if(this.get("csoVoteSNot")==null)this.put("csoVoteSNot", "");this.put("csoVoteS", null);return this;};
 		/** not .... */
 		public M csoVoteSNot(){this.put("csoVoteSNot", "not");return this;};
		/** and cso_vote_s >= ? */
		public M csoVoteSMin(Object min){this.put("csoVoteSMin", min);return this;};
		/** and cso_vote_s <= ? */
		public M csoVoteSMax(Object max){this.put("csoVoteSMax", max);return this;};
		/** 描述        **/
		public M csoProfile(Object csoProfile){this.put("csoProfile", csoProfile);return this;};
	 	/** and cso_profile is null */
 		public M csoProfileNull(){if(this.get("csoProfileNot")==null)this.put("csoProfileNot", "");this.put("csoProfile", null);return this;};
 		/** not .... */
 		public M csoProfileNot(){this.put("csoProfileNot", "not");return this;};
		/** 上线时间        **/
		public M csoOnlineTime(Object csoOnlineTime){this.put("csoOnlineTime", csoOnlineTime);return this;};
	 	/** and cso_online_time is null */
 		public M csoOnlineTimeNull(){if(this.get("csoOnlineTimeNot")==null)this.put("csoOnlineTimeNot", "");this.put("csoOnlineTime", null);return this;};
 		/** not .... */
 		public M csoOnlineTimeNot(){this.put("csoOnlineTimeNot", "not");return this;};
 		/** and cso_online_time >= ? */
 		public M csoOnlineTimeStart(Object start){this.put("csoOnlineTimeStart", start);return this;};			
 		/** and cso_online_time <= ? */
 		public M csoOnlineTimeEnd(Object end){this.put("csoOnlineTimeEnd", end);return this;};
		/** 修改时间        **/
		public M csoUpdateTime(Object csoUpdateTime){this.put("csoUpdateTime", csoUpdateTime);return this;};
	 	/** and cso_update_time is null */
 		public M csoUpdateTimeNull(){if(this.get("csoUpdateTimeNot")==null)this.put("csoUpdateTimeNot", "");this.put("csoUpdateTime", null);return this;};
 		/** not .... */
 		public M csoUpdateTimeNot(){this.put("csoUpdateTimeNot", "not");return this;};
 		/** and cso_update_time >= ? */
 		public M csoUpdateTimeStart(Object start){this.put("csoUpdateTimeStart", start);return this;};			
 		/** and cso_update_time <= ? */
 		public M csoUpdateTimeEnd(Object end){this.put("csoUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csoAddTime(Object csoAddTime){this.put("csoAddTime", csoAddTime);return this;};
	 	/** and cso_add_time is null */
 		public M csoAddTimeNull(){if(this.get("csoAddTimeNot")==null)this.put("csoAddTimeNot", "");this.put("csoAddTime", null);return this;};
 		/** not .... */
 		public M csoAddTimeNot(){this.put("csoAddTimeNot", "not");return this;};
 		/** and cso_add_time >= ? */
 		public M csoAddTimeStart(Object start){this.put("csoAddTimeStart", start);return this;};			
 		/** and cso_add_time <= ? */
 		public M csoAddTimeEnd(Object end){this.put("csoAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效  描述该网点是否让会员看得到   **/
		public M csoStatus(Object csoStatus){this.put("csoStatus", csoStatus);return this;};
	 	/** and cso_status is null */
 		public M csoStatusNull(){if(this.get("csoStatusNot")==null)this.put("csoStatusNot", "");this.put("csoStatus", null);return this;};
 		/** not .... */
 		public M csoStatusNot(){this.put("csoStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有网点 **/
		public @api List<CsOutlets> list(Integer size){
			return getCsOutletsList(this,size);
		}
		/** 获取网点分页 **/
		public @api Page<CsOutlets> page(int page,int size){
			return getCsOutletsPage(page, size , this);
		}
		/** 根据查询条件取网点 **/
		public @api CsOutlets get(){
			return getCsOutlets(this);
		}
		/** 获取网点数 **/
		public @api Long count(){
			return getCsOutletsCount(this);
		}
		/** 获取网点表达式 **/
		public @api <T> T eval(String strEval){
			return getCsOutletsEval(strEval,this);
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
			updateCsOutlets(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csoId="csoId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csoHost="csoHost";
		/** 名称 [非空]       **/
		public final static @type(String.class) @like String csoName="csoName";
		/** 所在区域 [非空] [CsArea]      **/
		public final static @type(Long.class)  String csoArea="csoArea";
		/** 网点类型 [非空]   0:汽油车网点 1:电动车网点     **/
		public final static @type(Short.class)  String csoType="csoType";
		/** 经度 [非空]       **/
		public final static @type(Double.class)  String csoLongitude="csoLongitude";
		/** and cso_longitude >= ? */
		public final static @type(Double.class) String csoLongitudeMin="csoLongitudeMin";
		/** and cso_longitude <= ? */
		public final static @type(Double.class) String csoLongitudeMax="csoLongitudeMax";
		/** 纬度 [非空]       **/
		public final static @type(Double.class)  String csoLatitude="csoLatitude";
		/** and cso_latitude >= ? */
		public final static @type(Double.class) String csoLatitudeMin="csoLatitudeMin";
		/** and cso_latitude <= ? */
		public final static @type(Double.class) String csoLatitudeMax="csoLatitudeMax";
		/** 地址 [非空]       **/
		public final static @type(String.class) @like String csoAddress="csoAddress";
		/** 停车地点        **/
		public final static @type(String.class) @like String csoSpace="csoSpace";
		/** 网点介绍        **/
		public final static @type(String.class) @like String csoDetails="csoDetails";
		/** 门禁时间        **/
		public final static @type(String.class) @like String csoDoorTime="csoDoorTime";
		/** 停车费        **/
		public final static @type(Double.class)  String csoCost="csoCost";
		/** and cso_cost >= ? */
		public final static @type(Double.class) String csoCostMin="csoCostMin";
		/** and cso_cost <= ? */
		public final static @type(Double.class) String csoCostMax="csoCostMax";
		/** 联系人        **/
		public final static @type(String.class) @like String csoPerson="csoPerson";
		/** 联系电话        **/
		public final static @type(String.class) @like String csoContact="csoContact";
		/** 缴费到期        **/
		public final static @type(Date.class)  String csoUntil="csoUntil";
	 	/** and cso_until >= ? */
 		public final static @type(Date.class) String csoUntilStart="csoUntilStart";
 		/** and cso_until <= ? */
 		public final static @type(Date.class) String csoUntilEnd="csoUntilEnd";
		/** 合约到期        **/
		public final static @type(Date.class)  String csoExpire="csoExpire";
	 	/** and cso_expire >= ? */
 		public final static @type(Date.class) String csoExpireStart="csoExpireStart";
 		/** and cso_expire <= ? */
 		public final static @type(Date.class) String csoExpireEnd="csoExpireEnd";
		/** 电桩建立时间        **/
		public final static @type(Date.class)  String csoPileTime="csoPileTime";
	 	/** and cso_pile_time >= ? */
 		public final static @type(Date.class) String csoPileTimeStart="csoPileTimeStart";
 		/** and cso_pile_time <= ? */
 		public final static @type(Date.class) String csoPileTimeEnd="csoPileTimeEnd";
		/** 已有车辆数 [非空]       **/
		public final static @type(Short.class)  String csoCarS="csoCarS";
		/** and cso_car_s >= ? */
		public final static @type(Short.class) String csoCarSMin="csoCarSMin";
		/** and cso_car_s <= ? */
		public final static @type(Short.class) String csoCarSMax="csoCarSMax";
		/** 车位数 [非空]       **/
		public final static @type(Short.class)  String csoPositionS="csoPositionS";
		/** and cso_position_s >= ? */
		public final static @type(Short.class) String csoPositionSMin="csoPositionSMin";
		/** and cso_position_s <= ? */
		public final static @type(Short.class) String csoPositionSMax="csoPositionSMax";
		/** 网点图片    400:width 300:height     **/
		public final static @type(String.class)  String csoImage="csoImage";
		/** AB借出 [非空]    是否支持A借B还中的借出   **/
		public final static @type(Boolean.class)  String csoCanA="csoCanA";
		/** AB还入 [非空]    是否支持A借B还中的还入   **/
		public final static @type(Boolean.class)  String csoCanB="csoCanB";
		/** 能注册 [非空]    可作为会员默认网点的选取项   **/
		public final static @type(Boolean.class)  String csoCanReg="csoCanReg";
		/** 能预订 [非空]    描述该网点是否可以下单   **/
		public final static @type(Boolean.class)  String csoCanOrder="csoCanOrder";
		/** 优先级        **/
		public final static @type(Integer.class)  String csoPriority="csoPriority";
		/** and cso_priority >= ? */
		public final static @type(Integer.class) String csoPriorityMin="csoPriorityMin";
		/** and cso_priority <= ? */
		public final static @type(Integer.class) String csoPriorityMax="csoPriorityMax";
		/** 人气        **/
		public final static @type(Integer.class)  String csoVoteS="csoVoteS";
		/** and cso_vote_s >= ? */
		public final static @type(Integer.class) String csoVoteSMin="csoVoteSMin";
		/** and cso_vote_s <= ? */
		public final static @type(Integer.class) String csoVoteSMax="csoVoteSMax";
		/** 描述        **/
		public final static @type(String.class) @like String csoProfile="csoProfile";
		/** 上线时间        **/
		public final static @type(Date.class)  String csoOnlineTime="csoOnlineTime";
	 	/** and cso_online_time >= ? */
 		public final static @type(Date.class) String csoOnlineTimeStart="csoOnlineTimeStart";
 		/** and cso_online_time <= ? */
 		public final static @type(Date.class) String csoOnlineTimeEnd="csoOnlineTimeEnd";
		/** 修改时间        **/
		public final static @type(Date.class)  String csoUpdateTime="csoUpdateTime";
	 	/** and cso_update_time >= ? */
 		public final static @type(Date.class) String csoUpdateTimeStart="csoUpdateTimeStart";
 		/** and cso_update_time <= ? */
 		public final static @type(Date.class) String csoUpdateTimeEnd="csoUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csoAddTime="csoAddTime";
	 	/** and cso_add_time >= ? */
 		public final static @type(Date.class) String csoAddTimeStart="csoAddTimeStart";
 		/** and cso_add_time <= ? */
 		public final static @type(Date.class) String csoAddTimeEnd="csoAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效  描述该网点是否让会员看得到   **/
		public final static @type(Short.class)  String csoStatus="csoStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csoId="cso_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csoHost="cso_host";
		/** 名称 [非空]       **/
		public final static String csoName="cso_name";
		/** 所在区域 [非空] [CsArea]      **/
		public final static String csoArea="cso_area";
		/** 网点类型 [非空]   0:汽油车网点 1:电动车网点     **/
		public final static String csoType="cso_type";
		/** 经度 [非空]       **/
		public final static String csoLongitude="cso_longitude";
		/** 纬度 [非空]       **/
		public final static String csoLatitude="cso_latitude";
		/** 地址 [非空]       **/
		public final static String csoAddress="cso_address";
		/** 停车地点        **/
		public final static String csoSpace="cso_space";
		/** 网点介绍        **/
		public final static String csoDetails="cso_details";
		/** 门禁时间        **/
		public final static String csoDoorTime="cso_door_time";
		/** 停车费        **/
		public final static String csoCost="cso_cost";
		/** 联系人        **/
		public final static String csoPerson="cso_person";
		/** 联系电话        **/
		public final static String csoContact="cso_contact";
		/** 缴费到期        **/
		public final static String csoUntil="cso_until";
		/** 合约到期        **/
		public final static String csoExpire="cso_expire";
		/** 电桩建立时间        **/
		public final static String csoPileTime="cso_pile_time";
		/** 已有车辆数 [非空]       **/
		public final static String csoCarS="cso_car_s";
		/** 车位数 [非空]       **/
		public final static String csoPositionS="cso_position_s";
		/** 网点图片    400:width 300:height     **/
		public final static String csoImage="cso_image";
		/** AB借出 [非空]    是否支持A借B还中的借出   **/
		public final static String csoCanA="cso_can_a";
		/** AB还入 [非空]    是否支持A借B还中的还入   **/
		public final static String csoCanB="cso_can_b";
		/** 能注册 [非空]    可作为会员默认网点的选取项   **/
		public final static String csoCanReg="cso_can_reg";
		/** 能预订 [非空]    描述该网点是否可以下单   **/
		public final static String csoCanOrder="cso_can_order";
		/** 优先级        **/
		public final static String csoPriority="cso_priority";
		/** 人气        **/
		public final static String csoVoteS="cso_vote_s";
		/** 描述        **/
		public final static String csoProfile="cso_profile";
		/** 上线时间        **/
		public final static String csoOnlineTime="cso_online_time";
		/** 修改时间        **/
		public final static String csoUpdateTime="cso_update_time";
		/** 添加时间 [非空]       **/
		public final static String csoAddTime="cso_add_time";
		/** 状态 [非空]   1:正常 0:无效  描述该网点是否让会员看得到   **/
		public final static String csoStatus="cso_status";
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
				$.Set(name,CsOutlets.getCsOutlets(params));
			else
				$.Set(name,CsOutlets.getCsOutletsList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取网点数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsOutlets) $.GetRequest("CsOutlets$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsOutlets.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsOutlets.getCsOutlets(params);
			else
				value = CsOutlets.getCsOutletsList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsOutlets.Get($.add(CsOutlets.F.csoId,param));
		else if(!$.empty(param.toString()))
			value = CsOutlets.get(Long.valueOf(param.toString()));
		$.SetRequest("CsOutlets$"+param.hashCode(), value);
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
	public void mergeSet(CsOutlets old){
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