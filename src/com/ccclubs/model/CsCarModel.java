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

@namespace("object/carmodel")
public @caption("车型") @table("cs_car_model") class CsCarModel implements java.io.Serializable
{
	private static final long serialVersionUID =           1l;
	private @caption("编号") @column("cscm_id")   @primary  @note("  ") Long cscmId;// 主键 非空     
	private @caption("车型名称") @column("cscm_name")    @note("  ") String cscmName;// 非空     
	private @caption("动力类型") @column("cscm_type")    @note(" 0:汽油车 1:电动车  ") Short cscmType;// 非空 0:汽油车 1:电动车     
	private @caption("档类型") @column("cscm_file")    @note(" 0:自动档 1:手动档  ") Short cscmFile;// 非空 0:自动档 1:手动档     
	private @caption("座位数") @column("cscm_seat_s")    @note("  ") Integer cscmSeatS;// 非空     
	private @caption("后备箱容量") @column("cscm_trunk")    @note("  ") String cscmTrunk;//     
	private @caption("排量/续航") @column("cscm_volume")    @note("  ") String cscmVolume;//     
	private @caption("能量容量") @column("cscm_capacity")    @note("  油箱容量/电池电量") String cscmCapacity;//  油箱容量/电池电量   
	private @caption("缩略图") @column("cscm_thumb")    @note("  图片建议尺寸大于200*200,一定要正放形哦") String cscmThumb;//  图片建议尺寸大于200*200,一定要正放形哦   
	private @caption("车型图片") @column("cscm_image")    @note("  图片建议尺寸500*300像素（比例）") String cscmImage;//  图片建议尺寸500*300像素（比例）   
	private @caption("车型介绍") @column("cscm_details")    @note("  html文件地址相对路径") String cscmDetails;//  html文件地址相对路径   
	//private @caption("车辆列表") @RelateClass(CsCar.class) List<CsCar> cscmCars;//反向关联的车辆列表
	private @caption("修改时间") @column("cscm_update_time")    @note("  ") Date cscmUpdateTime;// 非空     
	private @caption("添加时间") @column("cscm_add_time")    @note("  ") Date cscmAddTime;// 非空     
	private @caption("备注") @column("cscm_remark")    @note("  ") String cscmRemark;//     
	private @caption("状态") @column("cscm_status")    @note(" 1:正常 0:无效  ") Short cscmStatus;// 非空 1:正常 0:无效    
	
	/**
	 * 【车机中心对接】新增字段车型标志(公告车型备案型号)
	 */
	private @caption("车型标志") @column("cscm_flag")    @note("   ") String cscmFlag;// 
	
	//默认构造函数
	public CsCarModel() {
	
	}
	
	//主键构造函数
	public CsCarModel(Long id){
		this.cscmId = id;
	}
	
	/**所有字段构造函数 CsCarModel(cscmName,cscmType,cscmFile,cscmSeatS,cscmTrunk,cscmVolume,cscmCapacity,cscmThumb,cscmImage,cscmDetails,cscmUpdateTime,cscmAddTime,cscmRemark,cscmStatus)
	 CsCarModel(
	 	$.getString("cscmName")//车型名称 [非空]
	 	,$.getShort("cscmType")//动力类型 [非空]
	 	,$.getShort("cscmFile")//档类型 [非空]
	 	,$.getInteger("cscmSeatS")//座位数 [非空]
	 	,$.getString("cscmTrunk")//后备箱容量
	 	,$.getString("cscmVolume")//排量/续航
	 	,$.getString("cscmCapacity")//能量容量
	 	,$.getString("cscmThumb")//缩略图
	 	,$.getString("cscmImage")//车型图片
	 	,$.getString("cscmDetails")//车型介绍
	 	,$.getDate("cscmUpdateTime")//修改时间 [非空]
	 	,$.getDate("cscmAddTime")//添加时间 [非空]
	 	,$.getString("cscmRemark")//备注
	 	,$.getShort("cscmStatus")//状态 [非空]
	 )
	**/
	public CsCarModel(String cscmName,Short cscmType,Short cscmFile,Integer cscmSeatS,String cscmTrunk,String cscmVolume,String cscmCapacity,String cscmThumb,String cscmImage,String cscmDetails,Date cscmUpdateTime,Date cscmAddTime,String cscmRemark,Short cscmStatus, String cscmFlag){
		this.cscmName=cscmName;
		this.cscmType=cscmType;
		this.cscmFile=cscmFile;
		this.cscmSeatS=cscmSeatS;
		this.cscmTrunk=cscmTrunk;
		this.cscmVolume=cscmVolume;
		this.cscmCapacity=cscmCapacity;
		this.cscmThumb=cscmThumb;
		this.cscmImage=cscmImage;
		this.cscmDetails=cscmDetails;
		this.cscmUpdateTime=cscmUpdateTime;
		this.cscmAddTime=cscmAddTime;
		this.cscmRemark=cscmRemark;
		this.cscmStatus=cscmStatus;
		
		this.cscmFlag= cscmFlag;
	}
	
	//设置非空字段
	public CsCarModel setNotNull(String cscmName,Short cscmType,Short cscmFile,Integer cscmSeatS,Date cscmUpdateTime,Date cscmAddTime,Short cscmStatus){
		this.cscmName=cscmName;
		this.cscmType=cscmType;
		this.cscmFile=cscmFile;
		this.cscmSeatS=cscmSeatS;
		this.cscmUpdateTime=cscmUpdateTime;
		this.cscmAddTime=cscmAddTime;
		this.cscmStatus=cscmStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsCarModel cscmId(Long cscmId){
		this.cscmId = cscmId;
		this.setSeted(F.cscmId);
		return this;
	}
	/** 车型名称 [非空]       **/
	public CsCarModel cscmName(String cscmName){
		this.cscmName = cscmName;
		this.setSeted(F.cscmName);
		return this;
	}
	/** 动力类型 [非空]   0:汽油车 1:电动车     **/
	public CsCarModel cscmType(Short cscmType){
		this.cscmType = cscmType;
		this.setSeted(F.cscmType);
		return this;
	}
	/** 档类型 [非空]   0:自动档 1:手动档     **/
	public CsCarModel cscmFile(Short cscmFile){
		this.cscmFile = cscmFile;
		this.setSeted(F.cscmFile);
		return this;
	}
	/** 座位数 [非空]       **/
	public CsCarModel cscmSeatS(Integer cscmSeatS){
		this.cscmSeatS = cscmSeatS;
		this.setSeted(F.cscmSeatS);
		return this;
	}
	/** 后备箱容量        **/
	public CsCarModel cscmTrunk(String cscmTrunk){
		this.cscmTrunk = cscmTrunk;
		this.setSeted(F.cscmTrunk);
		return this;
	}
	/** 排量/续航        **/
	public CsCarModel cscmVolume(String cscmVolume){
		this.cscmVolume = cscmVolume;
		this.setSeted(F.cscmVolume);
		return this;
	}
	/** 能量容量     油箱容量/电池电量   **/
	public CsCarModel cscmCapacity(String cscmCapacity){
		this.cscmCapacity = cscmCapacity;
		this.setSeted(F.cscmCapacity);
		return this;
	}
	/** 缩略图     图片建议尺寸大于200*200,一定要正放形哦   **/
	public CsCarModel cscmThumb(String cscmThumb){
		this.cscmThumb = cscmThumb;
		this.setSeted(F.cscmThumb);
		return this;
	}
	/** 车型图片     图片建议尺寸500*300像素（比例）   **/
	public CsCarModel cscmImage(String cscmImage){
		this.cscmImage = cscmImage;
		this.setSeted(F.cscmImage);
		return this;
	}
	/** 车型介绍     html文件地址相对路径   **/
	public CsCarModel cscmDetails(String cscmDetails){
		this.cscmDetails = cscmDetails;
		this.setSeted(F.cscmDetails);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsCarModel cscmUpdateTime(Date cscmUpdateTime){
		this.cscmUpdateTime = cscmUpdateTime;
		this.setSeted(F.cscmUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsCarModel cscmAddTime(Date cscmAddTime){
		this.cscmAddTime = cscmAddTime;
		this.setSeted(F.cscmAddTime);
		return this;
	}
	/** 备注        **/
	public CsCarModel cscmRemark(String cscmRemark){
		this.cscmRemark = cscmRemark;
		this.setSeted(F.cscmRemark);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsCarModel cscmStatus(Short cscmStatus){
		this.cscmStatus = cscmStatus;
		this.setSeted(F.cscmStatus);
		return this;
	}
	/** 车型标志     **/
    public CsCarModel cscmFlag(String cscmFlag){
        this.cscmFlag = cscmFlag;
        this.setSeted(F.cscmFlag);
        return this;
    }
	
	
	//克隆对象
	public CsCarModel clone(){
		CsCarModel clone = new CsCarModel();
		clone.cscmName=this.cscmName;
		clone.cscmType=this.cscmType;
		clone.cscmFile=this.cscmFile;
		clone.cscmSeatS=this.cscmSeatS;
		clone.cscmUpdateTime=this.cscmUpdateTime;
		clone.cscmAddTime=this.cscmAddTime;
		clone.cscmStatus=this.cscmStatus;
		clone.cscmFlag=this.cscmFlag;
		return clone;
	}
	
	
	/**
	 * 根据ID取车型
	 * @param id
	 * @return
	 */
	public static @api CsCarModel get(Long id){		
		return getCsCarModelById(id);
	}
	/**
	 * 获取所有车型
	 * @return
	 */
	public static @api List<CsCarModel> list(Map params,Integer size){
		return getCsCarModelList(params,size);
	}
	/**
	 * 获取车型分页
	 * @return
	 */
	public static @api Page<CsCarModel> page(int page,int size,Map params){
		return getCsCarModelPage(page, size , params);
	}
	/**
	 * 根据查询条件取车型
	 * @param params
	 * @return
	 */
	public static @api CsCarModel Get(Map params){
		return getCsCarModel(params);
	}
	/**
	 * 获取车型数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsCarModelCount(params);
	}
	/**
	 * 获取车型数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsCarModelEval(eval,params);
	}
	
	/**
	 * 根据ID取车型
	 * @param id
	 * @return
	 */
	public static @api CsCarModel getCsCarModelById(Long id){		
		CsCarModel csCarModel = (CsCarModel) $.GetRequest("CsCarModel$"+id);
		if(csCarModel!=null)
			return csCarModel;
		com.ccclubs.service.admin.ICsCarModelService csCarModelService = $.GetSpringBean("csCarModelService");		
		return csCarModelService.getCsCarModelById(id);
	}
	
	
	/**
	 * 根据ID取车型的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsCarModel.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsCarModel csCarModel = get(id);
		if(csCarModel!=null){
			String strValue = csCarModel.getCscmName$();
			if(!"CscmName".equals("CscmName"))
				strValue+="("+csCarModel.getCscmName$()+")";
			MemCache.setValue(CsCarModel.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCscmName$();
		if(!"CscmName".equals("CscmName"))
			keyValue+="("+this.getCscmName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有车型
	 * @return
	 */
	public static @api List<CsCarModel> getCsCarModelList(Map params,Integer size){
		com.ccclubs.service.admin.ICsCarModelService csCarModelService = $.GetSpringBean("csCarModelService");
		return csCarModelService.getCsCarModelList(params, size);
	}
	
	/**
	 * 获取车型分页
	 * @return
	 */
	public static @api Page<CsCarModel> getCsCarModelPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsCarModelService csCarModelService = $.GetSpringBean("csCarModelService");
		return csCarModelService.getCsCarModelPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取车型
	 * @param params
	 * @return
	 */
	public static @api CsCarModel getCsCarModel(Map params){
		com.ccclubs.service.admin.ICsCarModelService csCarModelService = $.GetSpringBean("csCarModelService");
		return csCarModelService.getCsCarModel(params);
	}
	
	/**
	 * 获取车型数
	 * @return
	 */
	public static @api Long getCsCarModelCount(Map params){
		com.ccclubs.service.admin.ICsCarModelService csCarModelService = $.GetSpringBean("csCarModelService");
		return csCarModelService.getCsCarModelCount(params);
	}
		
		
	/**
	 * 获取车型自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsCarModelEval(String eval,Map params){
		com.ccclubs.service.admin.ICsCarModelService csCarModelService = $.GetSpringBean("csCarModelService");
		return csCarModelService.getCsCarModelEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsCarModel(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsCarModelService csCarModelService = $.GetSpringBean("csCarModelService");
		csCarModelService.updateCsCarModelByConfirm(set, where);
	}
	
	
	/**
	 * 保存车型对象
	 * @param params
	 * @return
	 */
	public CsCarModel save(){
		com.ccclubs.service.admin.ICsCarModelService csCarModelService = $.GetSpringBean("csCarModelService");
		if(this.getCscmId()!=null)
			csCarModelService.updateCsCarModel(this);
		else
			return csCarModelService.saveCsCarModel(this);
		return this;
	}
	
	
	/**
	 * 更新车型对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsCarModelService csCarModelService = $.GetSpringBean("csCarModelService");
		csCarModelService.updateCsCarModel$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsCarModelService csCarModelService = $.GetSpringBean("csCarModelService");
		if($.equals($.config("logic_delete"),"true"))
			csCarModelService.removeCsCarModelById(this.getCscmId());
		else
			csCarModelService.deleteCsCarModelById(this.getCscmId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsCarModelService csCarModelService = $.GetSpringBean("csCarModelService");
		return csCarModelService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCscmId(){
		return this.cscmId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCscmId$(){
		String strValue="";
		 strValue=$.str(this.getCscmId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCscmId(Long cscmId){
		this.cscmId = cscmId;
		this.setSeted(F.cscmId);
	}
	/*******************************车型名称**********************************/	
	/**
	* 车型名称 [非空]      
	**/
	public String getCscmName(){
		return this.cscmName;
	}
	/**
	* 获取车型名称格式化(toString)
	**/
	public String getCscmName$(){
		String strValue="";
		 strValue=$.str(this.getCscmName());
	 	 return strValue;
	}
	/**
	* 车型名称 [非空]      
	**/
	public void setCscmName(String cscmName){
		this.cscmName = cscmName;
		this.setSeted(F.cscmName);
	}
	/*******************************动力类型**********************************/	
	/**
	* 动力类型 [非空]   0:汽油车 1:电动车    
	**/
	public Short getCscmType(){
		return this.cscmType;
	}
	/**
	* 获取动力类型格式化(toString)
	**/
	public String getCscmType$(){
		String strValue="";
		 if($.equals($.str(this.getCscmType()),"0"))
			strValue=$.str("汽油车");		 
		 if($.equals($.str(this.getCscmType()),"1"))
			strValue=$.str("电动车");		 
	 	 return strValue;
	}
	/**
	* 动力类型 [非空]   0:汽油车 1:电动车    
	**/
	public void setCscmType(Short cscmType){
		this.cscmType = cscmType;
		this.setSeted(F.cscmType);
	}
	/*******************************档类型**********************************/	
	/**
	* 档类型 [非空]   0:自动档 1:手动档    
	**/
	public Short getCscmFile(){
		return this.cscmFile;
	}
	/**
	* 获取档类型格式化(toString)
	**/
	public String getCscmFile$(){
		String strValue="";
		 if($.equals($.str(this.getCscmFile()),"0"))
			strValue=$.str("自动档");		 
		 if($.equals($.str(this.getCscmFile()),"1"))
			strValue=$.str("手动档");		 
	 	 return strValue;
	}
	/**
	* 档类型 [非空]   0:自动档 1:手动档    
	**/
	public void setCscmFile(Short cscmFile){
		this.cscmFile = cscmFile;
		this.setSeted(F.cscmFile);
	}
	/*******************************座位数**********************************/	
	/**
	* 座位数 [非空]      
	**/
	public Integer getCscmSeatS(){
		return this.cscmSeatS;
	}
	/**
	* 获取座位数格式化(toString)
	**/
	public String getCscmSeatS$(){
		String strValue="";
		 strValue=$.str(this.getCscmSeatS());
	 	 return strValue;
	}
	/**
	* 座位数 [非空]      
	**/
	public void setCscmSeatS(Integer cscmSeatS){
		this.cscmSeatS = cscmSeatS;
		this.setSeted(F.cscmSeatS);
	}
	/*******************************后备箱容量**********************************/	
	/**
	* 后备箱容量       
	**/
	public String getCscmTrunk(){
		return this.cscmTrunk;
	}
	/**
	* 获取后备箱容量格式化(toString)
	**/
	public String getCscmTrunk$(){
		String strValue="";
		 strValue=$.str(this.getCscmTrunk());
	 	 return strValue;
	}
	/**
	* 后备箱容量       
	**/
	public void setCscmTrunk(String cscmTrunk){
		this.cscmTrunk = cscmTrunk;
		this.setSeted(F.cscmTrunk);
	}
	/*******************************排量/续航**********************************/	
	/**
	* 排量/续航       
	**/
	public String getCscmVolume(){
		return this.cscmVolume;
	}
	/**
	* 获取排量/续航格式化(toString)
	**/
	public String getCscmVolume$(){
		String strValue="";
		 strValue=$.str(this.getCscmVolume());
	 	 return strValue;
	}
	/**
	* 排量/续航       
	**/
	public void setCscmVolume(String cscmVolume){
		this.cscmVolume = cscmVolume;
		this.setSeted(F.cscmVolume);
	}
	/*******************************能量容量**********************************/	
	/**
	* 能量容量     油箱容量/电池电量  
	**/
	public String getCscmCapacity(){
		return this.cscmCapacity;
	}
	/**
	* 获取能量容量格式化(toString)
	**/
	public String getCscmCapacity$(){
		String strValue="";
		 strValue=$.str(this.getCscmCapacity());
	 	 return strValue;
	}
	/**
	* 能量容量     油箱容量/电池电量  
	**/
	public void setCscmCapacity(String cscmCapacity){
		this.cscmCapacity = cscmCapacity;
		this.setSeted(F.cscmCapacity);
	}
	/*******************************缩略图**********************************/	
	/**
	* 缩略图     图片建议尺寸大于200*200,一定要正放形哦  
	**/
	public String getCscmThumb(){
		return this.cscmThumb;
	}
	/**
	* 获取缩略图格式化(toString)
	**/
	public String getCscmThumb$(){
		String strValue="";
		 strValue=$.str(this.getCscmThumb());
	 	 return strValue;
	}
	/**
	* 缩略图     图片建议尺寸大于200*200,一定要正放形哦  
	**/
	public void setCscmThumb(String cscmThumb){
		this.cscmThumb = cscmThumb;
		this.setSeted(F.cscmThumb);
	}
	/*******************************车型图片**********************************/	
	/**
	* 车型图片     图片建议尺寸500*300像素（比例）  
	**/
	public String getCscmImage(){
		return this.cscmImage;
	}
	/**
	* 获取车型图片格式化(toString)
	**/
	public String getCscmImage$(){
		String strValue="";
		 strValue=$.str(this.getCscmImage());
	 	 return strValue;
	}
	/**
	* 车型图片     图片建议尺寸500*300像素（比例）  
	**/
	public void setCscmImage(String cscmImage){
		this.cscmImage = cscmImage;
		this.setSeted(F.cscmImage);
	}
	/*******************************车型介绍**********************************/	
	/**
	* 车型介绍     html文件地址相对路径  
	**/
	public String getCscmDetails(){
		return this.cscmDetails;
	}
	/**
	* 获取车型介绍格式化(toString)
	**/
	public String getCscmDetails$(){
		String strValue="";
		 strValue=$.str(this.getCscmDetails());
	 	 return strValue;
	}
	/**
	* 车型介绍     html文件地址相对路径  
	**/
	public void setCscmDetails(String cscmDetails){
		this.cscmDetails = cscmDetails;
		this.setSeted(F.cscmDetails);
	}
	/*******************************反向关联的车辆列表**********************************/	
	/**
	* 获取与当前对象反向关联的车辆列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsCar> getCscmCars(){
		com.ccclubs.service.admin.ICsCarService csCarService = $.GetSpringBean("csCarService");
		Map params = new HashMap();
		params.put("cscModel",this.getCscmId());
		return csCarService.getCsCarList(params,-1);
	}
	/**
	* 获取车辆列表格式化(toString)
	**/
	public String getCscmCars$(){
		return "[...]";
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCscmUpdateTime(){
		return this.cscmUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCscmUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscmUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCscmUpdateTime(Date cscmUpdateTime){
		this.cscmUpdateTime = cscmUpdateTime;
		this.setSeted(F.cscmUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCscmAddTime(){
		return this.cscmAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCscmAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscmAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCscmAddTime(Date cscmAddTime){
		this.cscmAddTime = cscmAddTime;
		this.setSeted(F.cscmAddTime);
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getCscmRemark(){
		return this.cscmRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCscmRemark$(){
		String strValue="";
		 strValue=$.str(this.getCscmRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setCscmRemark(String cscmRemark){
		this.cscmRemark = cscmRemark;
		this.setSeted(F.cscmRemark);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCscmStatus(){
		return this.cscmStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCscmStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCscmStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCscmStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCscmStatus(Short cscmStatus){
		this.cscmStatus = cscmStatus;
		this.setSeted(F.cscmStatus);
	}
	/*******************************车型标志**********************************/  
    /**
    * 车型标志       
    **/
    public String getCscmFlag(){
        return this.cscmFlag;
    }
    /**
    * 获取车型标志格式化(toString)
    **/
    public String getCscmFlag$(){
        String strValue="";
         strValue=$.str(this.getCscmFlag());
         return strValue;
    }
    /**
    * 车型标志       
    **/
    public void setCscmFlag(String cscmFlag){
        this.cscmFlag = cscmFlag;
        this.setSeted(F.cscmFlag);
    }
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsCarModel.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCarModel.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCarModel.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsCarModel.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsCarModel.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCarModel.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCarModel.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsCarModel.this);
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
		public M cscmId(Object cscmId){this.put("cscmId", cscmId);return this;};
	 	/** and cscm_id is null */
 		public M cscmIdNull(){if(this.get("cscmIdNot")==null)this.put("cscmIdNot", "");this.put("cscmId", null);return this;};
 		/** not .... */
 		public M cscmIdNot(){this.put("cscmIdNot", "not");return this;};
		/** 车型名称 [非空]       **/
		public M cscmName(Object cscmName){this.put("cscmName", cscmName);return this;};
	 	/** and cscm_name is null */
 		public M cscmNameNull(){if(this.get("cscmNameNot")==null)this.put("cscmNameNot", "");this.put("cscmName", null);return this;};
 		/** not .... */
 		public M cscmNameNot(){this.put("cscmNameNot", "not");return this;};
		/** 动力类型 [非空]   0:汽油车 1:电动车     **/
		public M cscmType(Object cscmType){this.put("cscmType", cscmType);return this;};
	 	/** and cscm_type is null */
 		public M cscmTypeNull(){if(this.get("cscmTypeNot")==null)this.put("cscmTypeNot", "");this.put("cscmType", null);return this;};
 		/** not .... */
 		public M cscmTypeNot(){this.put("cscmTypeNot", "not");return this;};
		/** 档类型 [非空]   0:自动档 1:手动档     **/
		public M cscmFile(Object cscmFile){this.put("cscmFile", cscmFile);return this;};
	 	/** and cscm_file is null */
 		public M cscmFileNull(){if(this.get("cscmFileNot")==null)this.put("cscmFileNot", "");this.put("cscmFile", null);return this;};
 		/** not .... */
 		public M cscmFileNot(){this.put("cscmFileNot", "not");return this;};
		/** 座位数 [非空]       **/
		public M cscmSeatS(Object cscmSeatS){this.put("cscmSeatS", cscmSeatS);return this;};
	 	/** and cscm_seat_s is null */
 		public M cscmSeatSNull(){if(this.get("cscmSeatSNot")==null)this.put("cscmSeatSNot", "");this.put("cscmSeatS", null);return this;};
 		/** not .... */
 		public M cscmSeatSNot(){this.put("cscmSeatSNot", "not");return this;};
		/** and cscm_seat_s >= ? */
		public M cscmSeatSMin(Object min){this.put("cscmSeatSMin", min);return this;};
		/** and cscm_seat_s <= ? */
		public M cscmSeatSMax(Object max){this.put("cscmSeatSMax", max);return this;};
		/** 后备箱容量        **/
		public M cscmTrunk(Object cscmTrunk){this.put("cscmTrunk", cscmTrunk);return this;};
	 	/** and cscm_trunk is null */
 		public M cscmTrunkNull(){if(this.get("cscmTrunkNot")==null)this.put("cscmTrunkNot", "");this.put("cscmTrunk", null);return this;};
 		/** not .... */
 		public M cscmTrunkNot(){this.put("cscmTrunkNot", "not");return this;};
		/** 排量/续航        **/
		public M cscmVolume(Object cscmVolume){this.put("cscmVolume", cscmVolume);return this;};
	 	/** and cscm_volume is null */
 		public M cscmVolumeNull(){if(this.get("cscmVolumeNot")==null)this.put("cscmVolumeNot", "");this.put("cscmVolume", null);return this;};
 		/** not .... */
 		public M cscmVolumeNot(){this.put("cscmVolumeNot", "not");return this;};
		/** 能量容量     油箱容量/电池电量   **/
		public M cscmCapacity(Object cscmCapacity){this.put("cscmCapacity", cscmCapacity);return this;};
	 	/** and cscm_capacity is null */
 		public M cscmCapacityNull(){if(this.get("cscmCapacityNot")==null)this.put("cscmCapacityNot", "");this.put("cscmCapacity", null);return this;};
 		/** not .... */
 		public M cscmCapacityNot(){this.put("cscmCapacityNot", "not");return this;};
		/** 缩略图     图片建议尺寸大于200*200,一定要正放形哦   **/
		public M cscmThumb(Object cscmThumb){this.put("cscmThumb", cscmThumb);return this;};
	 	/** and cscm_thumb is null */
 		public M cscmThumbNull(){if(this.get("cscmThumbNot")==null)this.put("cscmThumbNot", "");this.put("cscmThumb", null);return this;};
 		/** not .... */
 		public M cscmThumbNot(){this.put("cscmThumbNot", "not");return this;};
		/** 车型图片     图片建议尺寸500*300像素（比例）   **/
		public M cscmImage(Object cscmImage){this.put("cscmImage", cscmImage);return this;};
	 	/** and cscm_image is null */
 		public M cscmImageNull(){if(this.get("cscmImageNot")==null)this.put("cscmImageNot", "");this.put("cscmImage", null);return this;};
 		/** not .... */
 		public M cscmImageNot(){this.put("cscmImageNot", "not");return this;};
		/** 车型介绍     html文件地址相对路径   **/
		public M cscmDetails(Object cscmDetails){this.put("cscmDetails", cscmDetails);return this;};
	 	/** and cscm_details is null */
 		public M cscmDetailsNull(){if(this.get("cscmDetailsNot")==null)this.put("cscmDetailsNot", "");this.put("cscmDetails", null);return this;};
 		/** not .... */
 		public M cscmDetailsNot(){this.put("cscmDetailsNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M cscmUpdateTime(Object cscmUpdateTime){this.put("cscmUpdateTime", cscmUpdateTime);return this;};
	 	/** and cscm_update_time is null */
 		public M cscmUpdateTimeNull(){if(this.get("cscmUpdateTimeNot")==null)this.put("cscmUpdateTimeNot", "");this.put("cscmUpdateTime", null);return this;};
 		/** not .... */
 		public M cscmUpdateTimeNot(){this.put("cscmUpdateTimeNot", "not");return this;};
 		/** and cscm_update_time >= ? */
 		public M cscmUpdateTimeStart(Object start){this.put("cscmUpdateTimeStart", start);return this;};			
 		/** and cscm_update_time <= ? */
 		public M cscmUpdateTimeEnd(Object end){this.put("cscmUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cscmAddTime(Object cscmAddTime){this.put("cscmAddTime", cscmAddTime);return this;};
	 	/** and cscm_add_time is null */
 		public M cscmAddTimeNull(){if(this.get("cscmAddTimeNot")==null)this.put("cscmAddTimeNot", "");this.put("cscmAddTime", null);return this;};
 		/** not .... */
 		public M cscmAddTimeNot(){this.put("cscmAddTimeNot", "not");return this;};
 		/** and cscm_add_time >= ? */
 		public M cscmAddTimeStart(Object start){this.put("cscmAddTimeStart", start);return this;};			
 		/** and cscm_add_time <= ? */
 		public M cscmAddTimeEnd(Object end){this.put("cscmAddTimeEnd", end);return this;};
		/** 备注        **/
		public M cscmRemark(Object cscmRemark){this.put("cscmRemark", cscmRemark);return this;};
	 	/** and cscm_remark is null */
 		public M cscmRemarkNull(){if(this.get("cscmRemarkNot")==null)this.put("cscmRemarkNot", "");this.put("cscmRemark", null);return this;};
 		/** not .... */
 		public M cscmRemarkNot(){this.put("cscmRemarkNot", "not");return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M cscmStatus(Object cscmStatus){this.put("cscmStatus", cscmStatus);return this;};
	 	/** and cscm_status is null */
 		public M cscmStatusNull(){if(this.get("cscmStatusNot")==null)this.put("cscmStatusNot", "");this.put("cscmStatus", null);return this;};
 		/** not .... */
 		public M cscmStatusNot(){this.put("cscmStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	
	 	/**
	 	 * 【车机中心对接】新增字段
	 	 */
	 	/** 车型标志       **/
        public M cscmFlag(Object cscmFlag){this.put("cscmFlag", cscmFlag);return this;};
        /** and cscm_flag is null */
        public M cscmFlagNull(){if(this.get("cscmFlagNot")==null)this.put("cscmFlagNot", "");this.put("cscmFlag", null);return this;};
        /** not .... */
        public M cscmFlagNot(){this.put("cscmFlagNot", "not");return this;};
        
	 	/** 获取所有车型 **/
		public @api List<CsCarModel> list(Integer size){
			return getCsCarModelList(this,size);
		}
		/** 获取车型分页 **/
		public @api Page<CsCarModel> page(int page,int size){
			return getCsCarModelPage(page, size , this);
		}
		/** 根据查询条件取车型 **/
		public @api CsCarModel get(){
			return getCsCarModel(this);
		}
		/** 获取车型数 **/
		public @api Long count(){
			return getCsCarModelCount(this);
		}
		/** 获取车型表达式 **/
		public @api <T> T eval(String strEval){
			return getCsCarModelEval(strEval,this);
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
			updateCsCarModel(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscmId="cscmId";
		/** 车型名称 [非空]       **/
		public final static @type(String.class) @like String cscmName="cscmName";
		/** 动力类型 [非空]   0:汽油车 1:电动车     **/
		public final static @type(Short.class)  String cscmType="cscmType";
		/** 档类型 [非空]   0:自动档 1:手动档     **/
		public final static @type(Short.class)  String cscmFile="cscmFile";
		/** 座位数 [非空]       **/
		public final static @type(Integer.class)  String cscmSeatS="cscmSeatS";
		/** and cscm_seat_s >= ? */
		public final static @type(Integer.class) String cscmSeatSMin="cscmSeatSMin";
		/** and cscm_seat_s <= ? */
		public final static @type(Integer.class) String cscmSeatSMax="cscmSeatSMax";
		/** 后备箱容量        **/
		public final static @type(String.class) @like String cscmTrunk="cscmTrunk";
		/** 排量/续航        **/
		public final static @type(String.class) @like String cscmVolume="cscmVolume";
		/** 能量容量     油箱容量/电池电量   **/
		public final static @type(String.class) @like String cscmCapacity="cscmCapacity";
		/** 缩略图     图片建议尺寸大于200*200,一定要正放形哦   **/
		public final static @type(String.class)  String cscmThumb="cscmThumb";
		/** 车型图片     图片建议尺寸500*300像素（比例）   **/
		public final static @type(String.class)  String cscmImage="cscmImage";
		/** 车型介绍     html文件地址相对路径   **/
		public final static @type(String.class) @like String cscmDetails="cscmDetails";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cscmUpdateTime="cscmUpdateTime";
	 	/** and cscm_update_time >= ? */
 		public final static @type(Date.class) String cscmUpdateTimeStart="cscmUpdateTimeStart";
 		/** and cscm_update_time <= ? */
 		public final static @type(Date.class) String cscmUpdateTimeEnd="cscmUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cscmAddTime="cscmAddTime";
	 	/** and cscm_add_time >= ? */
 		public final static @type(Date.class) String cscmAddTimeStart="cscmAddTimeStart";
 		/** and cscm_add_time <= ? */
 		public final static @type(Date.class) String cscmAddTimeEnd="cscmAddTimeEnd";
		/** 备注        **/
		public final static @type(String.class) @like String cscmRemark="cscmRemark";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String cscmStatus="cscmStatus";
		/** 车型标志     **/
        public final static @type(String.class)  String cscmFlag="cscmFlag";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscmId="cscm_id";
		/** 车型名称 [非空]       **/
		public final static String cscmName="cscm_name";
		/** 动力类型 [非空]   0:汽油车 1:电动车     **/
		public final static String cscmType="cscm_type";
		/** 档类型 [非空]   0:自动档 1:手动档     **/
		public final static String cscmFile="cscm_file";
		/** 座位数 [非空]       **/
		public final static String cscmSeatS="cscm_seat_s";
		/** 后备箱容量        **/
		public final static String cscmTrunk="cscm_trunk";
		/** 排量/续航        **/
		public final static String cscmVolume="cscm_volume";
		/** 能量容量     油箱容量/电池电量   **/
		public final static String cscmCapacity="cscm_capacity";
		/** 缩略图     图片建议尺寸大于200*200,一定要正放形哦   **/
		public final static String cscmThumb="cscm_thumb";
		/** 车型图片     图片建议尺寸500*300像素（比例）   **/
		public final static String cscmImage="cscm_image";
		/** 车型介绍     html文件地址相对路径   **/
		public final static String cscmDetails="cscm_details";
		/** 修改时间 [非空]       **/
		public final static String cscmUpdateTime="cscm_update_time";
		/** 添加时间 [非空]       **/
		public final static String cscmAddTime="cscm_add_time";
		/** 备注        **/
		public final static String cscmRemark="cscm_remark";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String cscmStatus="cscm_status";
		/** 车型标志        **/
        public final static String cscmFlag="cscmFlag";
        
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
				$.Set(name,CsCarModel.getCsCarModel(params));
			else
				$.Set(name,CsCarModel.getCsCarModelList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取车型数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsCarModel) $.GetRequest("CsCarModel$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsCarModel.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsCarModel.getCsCarModel(params);
			else
				value = CsCarModel.getCsCarModelList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsCarModel.Get($.add(CsCarModel.F.cscmId,param));
		else if(!$.empty(param.toString()))
			value = CsCarModel.get(Long.valueOf(param.toString()));
		$.SetRequest("CsCarModel$"+param.hashCode(), value);
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
	public void mergeSet(CsCarModel old){
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