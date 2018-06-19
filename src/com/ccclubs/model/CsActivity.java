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

@namespace("operate/activitys/activity")
public @caption("营销活动") @table("cs_activity") class CsActivity implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csa_id")   @primary  @note("  ") Long csaId;// 主键 非空     
	private @caption("城市") @column("csa_host")    @relate("$csaHost") @RelateClass(SrvHost.class)  @note("  ") Long csaHost;// 非空     
 	private SrvHost $csaHost;//关联对象[运营城市]
	private @caption("活动主题") @column("csa_title")    @note("  ") String csaTitle;// 非空     
	private @caption("活动标识") @column("csa_flag")    @note("  只能是英文或者数字哦") String csaFlag;// 非空  只能是英文或者数字哦   
	private @caption("营销分类") @column("csa_type")    @relate("$csaType") @RelateClass(SrvProperty.class)  @note(" market_type:营销类型  产品(促销) 会员营销 渠道运营 品牌运营") Long csaType;// 非空 market_type:营销类型  产品(促销) 会员营销 渠道运营 品牌运营   
 	private SrvProperty $csaType;//关联对象[系统属性]
	private @caption("所属策划") @column("csa_plan")    @relate("$csaPlan") @RelateClass(CsMarketPlan.class)  @note("  注意，该说明仅对内说明，对外不可见") Long csaPlan;//  注意，该说明仅对内说明，对外不可见   
 	private CsMarketPlan $csaPlan;//关联对象[营销方案]
	private @caption("开始时间") @column("csa_start")    @note("  ") Date csaStart;//     
	private @caption("结束时间") @column("csa_finish")    @note("  ") Date csaFinish;//     
	private @caption("海报设计") @column("csa_poster")  @hidden   @note("  海报图片尺寸建议1920*500") String csaPoster;//  海报图片尺寸建议1920*500   
	private @caption("页面文件") @column("csa_zip")  @hidden   @note("  请上传开发好的html压缩文件，数据获取请通过开放平台获取") String csaZip;//  请上传开发好的html压缩文件，数据获取请通过开放平台获取   
	//private @caption("参与会员") @RelateClass(CsActivityIn.class) List<CsActivityIn> actors;//反向关联的活动参与列表
	private @caption("创建人") @column("csa_creater")    @relate("$csaCreater") @RelateClass(SrvUser.class)  @note("  ") Long csaCreater;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csaCreater;//关联对象[用户]
	private @caption("修改时间") @column("csa_update_time")    @note("  ") Date csaUpdateTime;// 非空     
	private @caption("添加时间") @column("csa_add_time")    @note("  ") Date csaAddTime;// 非空     
	private @caption("状态") @column("csa_status")    @note(" 0:策划中 1:进行中 2:已结束 3:已放弃  ") Short csaStatus;// 非空 0:策划中 1:进行中 2:已结束 3:已放弃     
	
	//默认构造函数
	public CsActivity(){
	
	}
	
	//主键构造函数
	public CsActivity(Long id){
		this.csaId = id;
	}
	
	/**所有字段构造函数 CsActivity(csaHost,csaTitle,csaFlag,csaType,csaPlan,csaStart,csaFinish,csaPoster,csaZip,csaCreater,csaUpdateTime,csaAddTime,csaStatus)
	 CsActivity(
	 	$.getLong("csaHost")//城市 [非空]
	 	,$.getString("csaTitle")//活动主题 [非空]
	 	,$.getString("csaFlag")//活动标识 [非空]
	 	,$.getLong("csaType")//营销分类 [非空]
	 	,$.getLong("csaPlan")//所属策划
	 	,$.getDate("csaStart")//开始时间
	 	,$.getDate("csaFinish")//结束时间
	 	,$.getString("csaPoster")//海报设计
	 	,$.getString("csaZip")//页面文件
	 	,$.getLong("csaCreater")//创建人 [非空]
	 	,$.getDate("csaUpdateTime")//修改时间 [非空]
	 	,$.getDate("csaAddTime")//添加时间 [非空]
	 	,$.getShort("csaStatus")//状态 [非空]
	 )
	**/
	public CsActivity(Long csaHost,String csaTitle,String csaFlag,Long csaType,Long csaPlan,Date csaStart,Date csaFinish,String csaPoster,String csaZip,Long csaCreater,Date csaUpdateTime,Date csaAddTime,Short csaStatus){
		this.csaHost=csaHost;
		this.csaTitle=csaTitle;
		this.csaFlag=csaFlag;
		this.csaType=csaType;
		this.csaPlan=csaPlan;
		this.csaStart=csaStart;
		this.csaFinish=csaFinish;
		this.csaPoster=csaPoster;
		this.csaZip=csaZip;
		this.csaCreater=csaCreater;
		this.csaUpdateTime=csaUpdateTime;
		this.csaAddTime=csaAddTime;
		this.csaStatus=csaStatus;
	}
	
	//设置非空字段
	public CsActivity setNotNull(Long csaHost,String csaTitle,String csaFlag,Long csaType,Long csaCreater,Date csaUpdateTime,Date csaAddTime,Short csaStatus){
		this.csaHost=csaHost;
		this.csaTitle=csaTitle;
		this.csaFlag=csaFlag;
		this.csaType=csaType;
		this.csaCreater=csaCreater;
		this.csaUpdateTime=csaUpdateTime;
		this.csaAddTime=csaAddTime;
		this.csaStatus=csaStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsActivity csaId(Long csaId){
		this.csaId = csaId;
		this.setSeted(F.csaId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsActivity csaHost(Long csaHost){
		this.csaHost = csaHost;
		this.setSeted(F.csaHost);
		return this;
	}
	/** 活动主题 [非空]       **/
	public CsActivity csaTitle(String csaTitle){
		this.csaTitle = csaTitle;
		this.setSeted(F.csaTitle);
		return this;
	}
	/** 活动标识 [非空]    只能是英文或者数字哦   **/
	public CsActivity csaFlag(String csaFlag){
		this.csaFlag = csaFlag;
		this.setSeted(F.csaFlag);
		return this;
	}
	/** 营销分类 [非空] [SrvProperty]  market_type:营销类型  产品(促销) 会员营销 渠道运营 品牌运营   **/
	public CsActivity csaType(Long csaType){
		this.csaType = csaType;
		this.setSeted(F.csaType);
		return this;
	}
	/** 所属策划  [CsMarketPlan]   注意，该说明仅对内说明，对外不可见   **/
	public CsActivity csaPlan(Long csaPlan){
		this.csaPlan = csaPlan;
		this.setSeted(F.csaPlan);
		return this;
	}
	/** 开始时间        **/
	public CsActivity csaStart(Date csaStart){
		this.csaStart = csaStart;
		this.setSeted(F.csaStart);
		return this;
	}
	/** 结束时间        **/
	public CsActivity csaFinish(Date csaFinish){
		this.csaFinish = csaFinish;
		this.setSeted(F.csaFinish);
		return this;
	}
	/** 海报设计     海报图片尺寸建议1920*500   **/
	public CsActivity csaPoster(String csaPoster){
		this.csaPoster = csaPoster;
		this.setSeted(F.csaPoster);
		return this;
	}
	/** 页面文件     请上传开发好的html压缩文件，数据获取请通过开放平台获取   **/
	public CsActivity csaZip(String csaZip){
		this.csaZip = csaZip;
		this.setSeted(F.csaZip);
		return this;
	}
	/** 创建人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsActivity csaCreater(Long csaCreater){
		this.csaCreater = csaCreater;
		this.setSeted(F.csaCreater);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsActivity csaUpdateTime(Date csaUpdateTime){
		this.csaUpdateTime = csaUpdateTime;
		this.setSeted(F.csaUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsActivity csaAddTime(Date csaAddTime){
		this.csaAddTime = csaAddTime;
		this.setSeted(F.csaAddTime);
		return this;
	}
	/** 状态 [非空]   0:策划中 1:进行中 2:已结束 3:已放弃     **/
	public CsActivity csaStatus(Short csaStatus){
		this.csaStatus = csaStatus;
		this.setSeted(F.csaStatus);
		return this;
	}
	
	
	//克隆对象
	public CsActivity clone(){
		CsActivity clone = new CsActivity();
		clone.csaHost=this.csaHost;
		clone.csaTitle=this.csaTitle;
		clone.csaFlag=this.csaFlag;
		clone.csaType=this.csaType;
		clone.csaCreater=this.csaCreater;
		clone.csaUpdateTime=this.csaUpdateTime;
		clone.csaAddTime=this.csaAddTime;
		clone.csaStatus=this.csaStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取营销活动
	 * @param id
	 * @return
	 */
	public static @api CsActivity get(Long id){		
		return getCsActivityById(id);
	}
	/**
	 * 获取所有营销活动
	 * @return
	 */
	public static @api List<CsActivity> list(Map params,Integer size){
		return getCsActivityList(params,size);
	}
	/**
	 * 获取营销活动分页
	 * @return
	 */
	public static @api Page<CsActivity> page(int page,int size,Map params){
		return getCsActivityPage(page, size , params);
	}
	/**
	 * 根据查询条件取营销活动
	 * @param params
	 * @return
	 */
	public static @api CsActivity Get(Map params){
		return getCsActivity(params);
	}
	/**
	 * 获取营销活动数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsActivityCount(params);
	}
	/**
	 * 获取营销活动数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsActivityEval(eval,params);
	}
	
	/**
	 * 根据ID取营销活动
	 * @param id
	 * @return
	 */
	public static @api CsActivity getCsActivityById(Long id){		
		CsActivity csActivity = (CsActivity) $.GetRequest("CsActivity$"+id);
		if(csActivity!=null)
			return csActivity;
		com.ccclubs.service.admin.ICsActivityService csActivityService = $.GetSpringBean("csActivityService");		
		return csActivityService.getCsActivityById(id);
	}
	
	
	/**
	 * 根据ID取营销活动的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsActivity.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsActivity csActivity = get(id);
		if(csActivity!=null){
			String strValue = csActivity.getCsaTitle$();
			if(!"CsaTitle".equals("CsaTitle"))
				strValue+="("+csActivity.getCsaTitle$()+")";
			MemCache.setValue(CsActivity.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsaTitle$();
		if(!"CsaTitle".equals("CsaTitle"))
			keyValue+="("+this.getCsaTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有营销活动
	 * @return
	 */
	public static @api List<CsActivity> getCsActivityList(Map params,Integer size){
		com.ccclubs.service.admin.ICsActivityService csActivityService = $.GetSpringBean("csActivityService");
		return csActivityService.getCsActivityList(params, size);
	}
	
	/**
	 * 获取营销活动分页
	 * @return
	 */
	public static @api Page<CsActivity> getCsActivityPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsActivityService csActivityService = $.GetSpringBean("csActivityService");
		return csActivityService.getCsActivityPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取营销活动
	 * @param params
	 * @return
	 */
	public static @api CsActivity getCsActivity(Map params){
		com.ccclubs.service.admin.ICsActivityService csActivityService = $.GetSpringBean("csActivityService");
		return csActivityService.getCsActivity(params);
	}
	
	/**
	 * 获取营销活动数
	 * @return
	 */
	public static @api Long getCsActivityCount(Map params){
		com.ccclubs.service.admin.ICsActivityService csActivityService = $.GetSpringBean("csActivityService");
		return csActivityService.getCsActivityCount(params);
	}
		
		
	/**
	 * 获取营销活动自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsActivityEval(String eval,Map params){
		com.ccclubs.service.admin.ICsActivityService csActivityService = $.GetSpringBean("csActivityService");
		return csActivityService.getCsActivityEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsActivity(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsActivityService csActivityService = $.GetSpringBean("csActivityService");
		csActivityService.updateCsActivityByConfirm(set, where);
	}
	
	
	/**
	 * 保存营销活动对象
	 * @param params
	 * @return
	 */
	public CsActivity save(){
		com.ccclubs.service.admin.ICsActivityService csActivityService = $.GetSpringBean("csActivityService");
		if(this.getCsaId()!=null)
			csActivityService.updateCsActivity(this);
		else
			return csActivityService.saveCsActivity(this);
		return this;
	}
	
	
	/**
	 * 更新营销活动对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsActivityService csActivityService = $.GetSpringBean("csActivityService");
		csActivityService.updateCsActivity$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsActivityService csActivityService = $.GetSpringBean("csActivityService");
		if($.equals($.config("logic_delete"),"true"))
			csActivityService.removeCsActivityById(this.getCsaId());
		else
			csActivityService.deleteCsActivityById(this.getCsaId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsActivityService csActivityService = $.GetSpringBean("csActivityService");
		return csActivityService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsaId(){
		return this.csaId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsaId$(){
		String strValue="";
		 strValue=$.str(this.getCsaId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsaId(Long csaId){
		this.csaId = csaId;
		this.setSeted(F.csaId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsaHost(){
		return this.csaHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsaHost$(){
		String strValue="";
		if(this.getCsaHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsaHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsaHost(Long csaHost){
		this.csaHost = csaHost;
		this.setSeted(F.csaHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csaHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsaHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsaHost()!=null){
 			srvHost = SrvHost.get(this.getCsaHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsaHost(), srvHost);
	 	return srvHost;
	}
	/*******************************活动主题**********************************/	
	/**
	* 活动主题 [非空]      
	**/
	public String getCsaTitle(){
		return this.csaTitle;
	}
	/**
	* 获取活动主题格式化(toString)
	**/
	public String getCsaTitle$(){
		String strValue="";
		 strValue=$.str(this.getCsaTitle());
	 	 return strValue;
	}
	/**
	* 活动主题 [非空]      
	**/
	public void setCsaTitle(String csaTitle){
		this.csaTitle = csaTitle;
		this.setSeted(F.csaTitle);
	}
	/*******************************活动标识**********************************/	
	/**
	* 活动标识 [非空]    只能是英文或者数字哦  
	**/
	public String getCsaFlag(){
		return this.csaFlag;
	}
	/**
	* 获取活动标识格式化(toString)
	**/
	public String getCsaFlag$(){
		String strValue="";
		 strValue=$.str(this.getCsaFlag());
	 	 return strValue;
	}
	/**
	* 活动标识 [非空]    只能是英文或者数字哦  
	**/
	public void setCsaFlag(String csaFlag){
		this.csaFlag = csaFlag;
		this.setSeted(F.csaFlag);
	}
	/*******************************营销分类**********************************/	
	/**
	* 营销分类 [非空] [SrvProperty]  market_type:营销类型  产品(促销) 会员营销 渠道运营 品牌运营  
	**/
	public Long getCsaType(){
		return this.csaType;
	}
	/**
	* 获取营销分类格式化(toString)
	**/
	public String getCsaType$(){
		String strValue="";
		if(this.getCsaType()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCsaType()));
		}			
	 	 return strValue;
	}
	/**
	* 营销分类 [非空] [SrvProperty]  market_type:营销类型  产品(促销) 会员营销 渠道运营 品牌运营  
	**/
	public void setCsaType(Long csaType){
		this.csaType = csaType;
		this.setSeted(F.csaType);
	}
	/**
	* 获取关联对象[系统属性]
	**/	 			
 	public SrvProperty get$csaType(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCsaType());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCsaType()!=null){
 			srvProperty = SrvProperty.get(this.getCsaType());
 		}
 		$.SetRequest("SrvProperty$"+this.getCsaType(), srvProperty);
	 	return srvProperty;
	}
	/*******************************所属策划**********************************/	
	/**
	* 所属策划  [CsMarketPlan]   注意，该说明仅对内说明，对外不可见  
	**/
	public Long getCsaPlan(){
		return this.csaPlan;
	}
	/**
	* 获取所属策划格式化(toString)
	**/
	public String getCsaPlan$(){
		String strValue="";
		if(this.getCsaPlan()!=null){
				strValue+=$.str(CsMarketPlan.getKeyValue(this.getCsaPlan()));
		}			
	 	 return strValue;
	}
	/**
	* 所属策划  [CsMarketPlan]   注意，该说明仅对内说明，对外不可见  
	**/
	public void setCsaPlan(Long csaPlan){
		this.csaPlan = csaPlan;
		this.setSeted(F.csaPlan);
	}
	/**
	* 获取关联对象[营销方案]
	**/	 			
 	public CsMarketPlan get$csaPlan(){
 		com.ccclubs.model.CsMarketPlan csMarketPlan = (com.ccclubs.model.CsMarketPlan) $.GetRequest("CsMarketPlan$"+this.getCsaPlan());
 		if(csMarketPlan!=null)
			return csMarketPlan;
		if(this.getCsaPlan()!=null){
 			csMarketPlan = CsMarketPlan.get(this.getCsaPlan());
 		}
 		$.SetRequest("CsMarketPlan$"+this.getCsaPlan(), csMarketPlan);
	 	return csMarketPlan;
	}
	/*******************************开始时间**********************************/	
	/**
	* 开始时间       
	**/
	public Date getCsaStart(){
		return this.csaStart;
	}
	/**
	* 获取开始时间格式化(toString)
	**/
	public String getCsaStart$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsaStart(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 开始时间       
	**/
	public void setCsaStart(Date csaStart){
		this.csaStart = csaStart;
		this.setSeted(F.csaStart);
	}
	/*******************************结束时间**********************************/	
	/**
	* 结束时间       
	**/
	public Date getCsaFinish(){
		return this.csaFinish;
	}
	/**
	* 获取结束时间格式化(toString)
	**/
	public String getCsaFinish$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsaFinish(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 结束时间       
	**/
	public void setCsaFinish(Date csaFinish){
		this.csaFinish = csaFinish;
		this.setSeted(F.csaFinish);
	}
	/*******************************海报设计**********************************/	
	/**
	* 海报设计     海报图片尺寸建议1920*500  
	**/
	public String getCsaPoster(){
		return this.csaPoster;
	}
	/**
	* 获取海报设计格式化(toString)
	**/
	public String getCsaPoster$(){
		String strValue="";
		 strValue=$.str(this.getCsaPoster());
	 	 return strValue;
	}
	/**
	* 海报设计     海报图片尺寸建议1920*500  
	**/
	public void setCsaPoster(String csaPoster){
		this.csaPoster = csaPoster;
		this.setSeted(F.csaPoster);
	}
	/*******************************页面文件**********************************/	
	/**
	* 页面文件     请上传开发好的html压缩文件，数据获取请通过开放平台获取  
	**/
	public String getCsaZip(){
		return this.csaZip;
	}
	/**
	* 获取页面文件格式化(toString)
	**/
	public String getCsaZip$(){
		String strValue="";
		 strValue=$.str(this.getCsaZip());
	 	 return strValue;
	}
	/**
	* 页面文件     请上传开发好的html压缩文件，数据获取请通过开放平台获取  
	**/
	public void setCsaZip(String csaZip){
		this.csaZip = csaZip;
		this.setSeted(F.csaZip);
	}
	/*******************************反向关联的活动参与列表**********************************/	
	/**
	* 获取与当前对象反向关联的活动参与列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsActivityIn> getActors(){
		com.ccclubs.service.admin.ICsActivityInService csActivityInService = $.GetSpringBean("csActivityInService");
		Map params = new HashMap();
		params.put("csaiActivity",this.getCsaId());
		return csActivityInService.getCsActivityInList(params,-1);
	}
	/**
	* 获取参与会员格式化(toString)
	**/
	public String getActors$(){
		return "[...]";
	}
	/*******************************创建人**********************************/	
	/**
	* 创建人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsaCreater(){
		return this.csaCreater;
	}
	/**
	* 获取创建人格式化(toString)
	**/
	public String getCsaCreater$(){
		String strValue="";
		if(this.getCsaCreater()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsaCreater()));
		}			
	 	 return strValue;
	}
	/**
	* 创建人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsaCreater(Long csaCreater){
		this.csaCreater = csaCreater;
		this.setSeted(F.csaCreater);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csaCreater(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsaCreater());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsaCreater()!=null){
 			srvUser = SrvUser.get(this.getCsaCreater());
 		}
 		$.SetRequest("SrvUser$"+this.getCsaCreater(), srvUser);
	 	return srvUser;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsaUpdateTime(){
		return this.csaUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsaUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsaUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsaUpdateTime(Date csaUpdateTime){
		this.csaUpdateTime = csaUpdateTime;
		this.setSeted(F.csaUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsaAddTime(){
		return this.csaAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsaAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsaAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsaAddTime(Date csaAddTime){
		this.csaAddTime = csaAddTime;
		this.setSeted(F.csaAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:策划中 1:进行中 2:已结束 3:已放弃    
	**/
	public Short getCsaStatus(){
		return this.csaStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsaStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsaStatus()),"0"))
			strValue=$.str("策划中");		 
		 if($.equals($.str(this.getCsaStatus()),"1"))
			strValue=$.str("进行中");		 
		 if($.equals($.str(this.getCsaStatus()),"2"))
			strValue=$.str("已结束");		 
		 if($.equals($.str(this.getCsaStatus()),"3"))
			strValue=$.str("已放弃");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:策划中 1:进行中 2:已结束 3:已放弃    
	**/
	public void setCsaStatus(Short csaStatus){
		this.csaStatus = csaStatus;
		this.setSeted(F.csaStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsActivity.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsActivity.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsActivity.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsActivity.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsActivity.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsActivity.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsActivity.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsActivity.this);
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
		public M csaId(Object csaId){this.put("csaId", csaId);return this;};
	 	/** and csa_id is null */
 		public M csaIdNull(){if(this.get("csaIdNot")==null)this.put("csaIdNot", "");this.put("csaId", null);return this;};
 		/** not .... */
 		public M csaIdNot(){this.put("csaIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csaHost(Object csaHost){this.put("csaHost", csaHost);return this;};
	 	/** and csa_host is null */
 		public M csaHostNull(){if(this.get("csaHostNot")==null)this.put("csaHostNot", "");this.put("csaHost", null);return this;};
 		/** not .... */
 		public M csaHostNot(){this.put("csaHostNot", "not");return this;};
		/** 活动主题 [非空]       **/
		public M csaTitle(Object csaTitle){this.put("csaTitle", csaTitle);return this;};
	 	/** and csa_title is null */
 		public M csaTitleNull(){if(this.get("csaTitleNot")==null)this.put("csaTitleNot", "");this.put("csaTitle", null);return this;};
 		/** not .... */
 		public M csaTitleNot(){this.put("csaTitleNot", "not");return this;};
		/** 活动标识 [非空]    只能是英文或者数字哦   **/
		public M csaFlag(Object csaFlag){this.put("csaFlag", csaFlag);return this;};
	 	/** and csa_flag is null */
 		public M csaFlagNull(){if(this.get("csaFlagNot")==null)this.put("csaFlagNot", "");this.put("csaFlag", null);return this;};
 		/** not .... */
 		public M csaFlagNot(){this.put("csaFlagNot", "not");return this;};
		/** 营销分类 [非空] [SrvProperty]  market_type:营销类型  产品(促销) 会员营销 渠道运营 品牌运营   **/
		public M csaType(Object csaType){this.put("csaType", csaType);return this;};
	 	/** and csa_type is null */
 		public M csaTypeNull(){if(this.get("csaTypeNot")==null)this.put("csaTypeNot", "");this.put("csaType", null);return this;};
 		/** not .... */
 		public M csaTypeNot(){this.put("csaTypeNot", "not");return this;};
		public M csaType$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("csaType$on", value);
			return this;
		};	
		/** 所属策划  [CsMarketPlan]   注意，该说明仅对内说明，对外不可见   **/
		public M csaPlan(Object csaPlan){this.put("csaPlan", csaPlan);return this;};
	 	/** and csa_plan is null */
 		public M csaPlanNull(){if(this.get("csaPlanNot")==null)this.put("csaPlanNot", "");this.put("csaPlan", null);return this;};
 		/** not .... */
 		public M csaPlanNot(){this.put("csaPlanNot", "not");return this;};
		public M csaPlan$on(CsMarketPlan.M value){
			this.put("CsMarketPlan", value);
			this.put("csaPlan$on", value);
			return this;
		};	
		/** 开始时间        **/
		public M csaStart(Object csaStart){this.put("csaStart", csaStart);return this;};
	 	/** and csa_start is null */
 		public M csaStartNull(){if(this.get("csaStartNot")==null)this.put("csaStartNot", "");this.put("csaStart", null);return this;};
 		/** not .... */
 		public M csaStartNot(){this.put("csaStartNot", "not");return this;};
 		/** and csa_start >= ? */
 		public M csaStartStart(Object start){this.put("csaStartStart", start);return this;};			
 		/** and csa_start <= ? */
 		public M csaStartEnd(Object end){this.put("csaStartEnd", end);return this;};
		/** 结束时间        **/
		public M csaFinish(Object csaFinish){this.put("csaFinish", csaFinish);return this;};
	 	/** and csa_finish is null */
 		public M csaFinishNull(){if(this.get("csaFinishNot")==null)this.put("csaFinishNot", "");this.put("csaFinish", null);return this;};
 		/** not .... */
 		public M csaFinishNot(){this.put("csaFinishNot", "not");return this;};
 		/** and csa_finish >= ? */
 		public M csaFinishStart(Object start){this.put("csaFinishStart", start);return this;};			
 		/** and csa_finish <= ? */
 		public M csaFinishEnd(Object end){this.put("csaFinishEnd", end);return this;};
		/** 海报设计     海报图片尺寸建议1920*500   **/
		public M csaPoster(Object csaPoster){this.put("csaPoster", csaPoster);return this;};
	 	/** and csa_poster is null */
 		public M csaPosterNull(){if(this.get("csaPosterNot")==null)this.put("csaPosterNot", "");this.put("csaPoster", null);return this;};
 		/** not .... */
 		public M csaPosterNot(){this.put("csaPosterNot", "not");return this;};
		/** 页面文件     请上传开发好的html压缩文件，数据获取请通过开放平台获取   **/
		public M csaZip(Object csaZip){this.put("csaZip", csaZip);return this;};
	 	/** and csa_zip is null */
 		public M csaZipNull(){if(this.get("csaZipNot")==null)this.put("csaZipNot", "");this.put("csaZip", null);return this;};
 		/** not .... */
 		public M csaZipNot(){this.put("csaZipNot", "not");return this;};
		/** 创建人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csaCreater(Object csaCreater){this.put("csaCreater", csaCreater);return this;};
	 	/** and csa_creater is null */
 		public M csaCreaterNull(){if(this.get("csaCreaterNot")==null)this.put("csaCreaterNot", "");this.put("csaCreater", null);return this;};
 		/** not .... */
 		public M csaCreaterNot(){this.put("csaCreaterNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csaUpdateTime(Object csaUpdateTime){this.put("csaUpdateTime", csaUpdateTime);return this;};
	 	/** and csa_update_time is null */
 		public M csaUpdateTimeNull(){if(this.get("csaUpdateTimeNot")==null)this.put("csaUpdateTimeNot", "");this.put("csaUpdateTime", null);return this;};
 		/** not .... */
 		public M csaUpdateTimeNot(){this.put("csaUpdateTimeNot", "not");return this;};
 		/** and csa_update_time >= ? */
 		public M csaUpdateTimeStart(Object start){this.put("csaUpdateTimeStart", start);return this;};			
 		/** and csa_update_time <= ? */
 		public M csaUpdateTimeEnd(Object end){this.put("csaUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csaAddTime(Object csaAddTime){this.put("csaAddTime", csaAddTime);return this;};
	 	/** and csa_add_time is null */
 		public M csaAddTimeNull(){if(this.get("csaAddTimeNot")==null)this.put("csaAddTimeNot", "");this.put("csaAddTime", null);return this;};
 		/** not .... */
 		public M csaAddTimeNot(){this.put("csaAddTimeNot", "not");return this;};
 		/** and csa_add_time >= ? */
 		public M csaAddTimeStart(Object start){this.put("csaAddTimeStart", start);return this;};			
 		/** and csa_add_time <= ? */
 		public M csaAddTimeEnd(Object end){this.put("csaAddTimeEnd", end);return this;};
		/** 状态 [非空]   0:策划中 1:进行中 2:已结束 3:已放弃     **/
		public M csaStatus(Object csaStatus){this.put("csaStatus", csaStatus);return this;};
	 	/** and csa_status is null */
 		public M csaStatusNull(){if(this.get("csaStatusNot")==null)this.put("csaStatusNot", "");this.put("csaStatus", null);return this;};
 		/** not .... */
 		public M csaStatusNot(){this.put("csaStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有营销活动 **/
		public @api List<CsActivity> list(Integer size){
			return getCsActivityList(this,size);
		}
		/** 获取营销活动分页 **/
		public @api Page<CsActivity> page(int page,int size){
			return getCsActivityPage(page, size , this);
		}
		/** 根据查询条件取营销活动 **/
		public @api CsActivity get(){
			return getCsActivity(this);
		}
		/** 获取营销活动数 **/
		public @api Long count(){
			return getCsActivityCount(this);
		}
		/** 获取营销活动表达式 **/
		public @api <T> T eval(String strEval){
			return getCsActivityEval(strEval,this);
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
			updateCsActivity(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csaId="csaId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csaHost="csaHost";
		/** 活动主题 [非空]       **/
		public final static @type(String.class) @like String csaTitle="csaTitle";
		/** 活动标识 [非空]    只能是英文或者数字哦   **/
		public final static @type(String.class) @like String csaFlag="csaFlag";
		/** 营销分类 [非空] [SrvProperty]  market_type:营销类型  产品(促销) 会员营销 渠道运营 品牌运营   **/
		public final static @type(Long.class)  String csaType="csaType";
		/** 所属策划  [CsMarketPlan]   注意，该说明仅对内说明，对外不可见   **/
		public final static @type(Long.class)  String csaPlan="csaPlan";
		/** 开始时间        **/
		public final static @type(Date.class)  String csaStart="csaStart";
	 	/** and csa_start >= ? */
 		public final static @type(Date.class) String csaStartStart="csaStartStart";
 		/** and csa_start <= ? */
 		public final static @type(Date.class) String csaStartEnd="csaStartEnd";
		/** 结束时间        **/
		public final static @type(Date.class)  String csaFinish="csaFinish";
	 	/** and csa_finish >= ? */
 		public final static @type(Date.class) String csaFinishStart="csaFinishStart";
 		/** and csa_finish <= ? */
 		public final static @type(Date.class) String csaFinishEnd="csaFinishEnd";
		/** 海报设计     海报图片尺寸建议1920*500   **/
		public final static @type(String.class)  String csaPoster="csaPoster";
		/** 页面文件     请上传开发好的html压缩文件，数据获取请通过开放平台获取   **/
		public final static @type(String.class)  String csaZip="csaZip";
		/** 创建人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csaCreater="csaCreater";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csaUpdateTime="csaUpdateTime";
	 	/** and csa_update_time >= ? */
 		public final static @type(Date.class) String csaUpdateTimeStart="csaUpdateTimeStart";
 		/** and csa_update_time <= ? */
 		public final static @type(Date.class) String csaUpdateTimeEnd="csaUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csaAddTime="csaAddTime";
	 	/** and csa_add_time >= ? */
 		public final static @type(Date.class) String csaAddTimeStart="csaAddTimeStart";
 		/** and csa_add_time <= ? */
 		public final static @type(Date.class) String csaAddTimeEnd="csaAddTimeEnd";
		/** 状态 [非空]   0:策划中 1:进行中 2:已结束 3:已放弃     **/
		public final static @type(Short.class)  String csaStatus="csaStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csaId="csa_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csaHost="csa_host";
		/** 活动主题 [非空]       **/
		public final static String csaTitle="csa_title";
		/** 活动标识 [非空]    只能是英文或者数字哦   **/
		public final static String csaFlag="csa_flag";
		/** 营销分类 [非空] [SrvProperty]  market_type:营销类型  产品(促销) 会员营销 渠道运营 品牌运营   **/
		public final static String csaType="csa_type";
		/** 所属策划  [CsMarketPlan]   注意，该说明仅对内说明，对外不可见   **/
		public final static String csaPlan="csa_plan";
		/** 开始时间        **/
		public final static String csaStart="csa_start";
		/** 结束时间        **/
		public final static String csaFinish="csa_finish";
		/** 海报设计     海报图片尺寸建议1920*500   **/
		public final static String csaPoster="csa_poster";
		/** 页面文件     请上传开发好的html压缩文件，数据获取请通过开放平台获取   **/
		public final static String csaZip="csa_zip";
		/** 创建人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csaCreater="csa_creater";
		/** 修改时间 [非空]       **/
		public final static String csaUpdateTime="csa_update_time";
		/** 添加时间 [非空]       **/
		public final static String csaAddTime="csa_add_time";
		/** 状态 [非空]   0:策划中 1:进行中 2:已结束 3:已放弃     **/
		public final static String csaStatus="csa_status";
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
				$.Set(name,CsActivity.getCsActivity(params));
			else
				$.Set(name,CsActivity.getCsActivityList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取营销活动数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsActivity) $.GetRequest("CsActivity$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsActivity.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsActivity.getCsActivity(params);
			else
				value = CsActivity.getCsActivityList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsActivity.Get($.add(CsActivity.F.csaId,param));
		else if(!$.empty(param.toString()))
			value = CsActivity.get(Long.valueOf(param.toString()));
		$.SetRequest("CsActivity$"+param.hashCode(), value);
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
	public void mergeSet(CsActivity old){
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