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

@namespace("fee/pack")
public @caption("系统套餐") @table("cs_pack") class CsPack implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csp_id")   @primary  @note("  ") Long cspId;// 主键 非空     
	private @caption("套餐名称") @column("csp_name")    @note("  ") String cspName;// 非空     
	private @caption("套餐价格") @column("csp_price")    @note("  ") Double cspPrice;// 非空     
	private @caption("套餐标识") @column("csp_flag")    @note("  ") String cspFlag;//     
	private @caption("有效期类型") @column("csp_validity")    @note(" 0:不限 1:一月 2:一季 3:半年 4:一年 5:天数  ") Short cspValidity;// 0:不限 1:一月 2:一季 3:半年 4:一年 5:天数     
	private @caption("有效天数") @column("csp_day")    @note("  当有效期类型为天数时有效") Integer cspDay;//  当有效期类型为天数时有效   
	private @caption("可自动续订") @column("csp_reorder")    @note(" 0:否 1:是  当有效期类型不为不限时有效") Short cspReorder;// 非空 0:否 1:是  当有效期类型不为不限时有效   
	//private @caption("套餐内容") @RelateClass(CsPackInfo.class) List<CsPackInfo> cspInfos;//反向关联的套餐内容列表
	private @caption("套餐说明") @column("csp_profile")    @note("  ") String cspProfile;//     
	private @caption("修改时间") @column("csp_update_time")    @note("  ") Date cspUpdateTime;// 非空     
	private @caption("添加时间") @column("csp_add_time")    @note("  ") Date cspAddTime;// 非空     
	private @caption("状态") @column("csp_status")    @note(" 1:正常 0:无效  ") Short cspStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsPack(){
	
	}
	
	//主键构造函数
	public CsPack(Long id){
		this.cspId = id;
	}
	
	/**所有字段构造函数 CsPack(cspName,cspPrice,cspFlag,cspValidity,cspDay,cspReorder,cspProfile,cspUpdateTime,cspAddTime,cspStatus)
	 CsPack(
	 	$.getString("cspName")//套餐名称 [非空]
	 	,$.getDouble("cspPrice")//套餐价格 [非空]
	 	,$.getString("cspFlag")//套餐标识
	 	,$.getShort("cspValidity")//有效期类型
	 	,$.getInteger("cspDay")//有效天数
	 	,$.getShort("cspReorder")//可自动续订 [非空]
	 	,$.getString("cspProfile")//套餐说明
	 	,$.getDate("cspUpdateTime")//修改时间 [非空]
	 	,$.getDate("cspAddTime")//添加时间 [非空]
	 	,$.getShort("cspStatus")//状态 [非空]
	 )
	**/
	public CsPack(String cspName,Double cspPrice,String cspFlag,Short cspValidity,Integer cspDay,Short cspReorder,String cspProfile,Date cspUpdateTime,Date cspAddTime,Short cspStatus){
		this.cspName=cspName;
		this.cspPrice=cspPrice;
		this.cspFlag=cspFlag;
		this.cspValidity=cspValidity;
		this.cspDay=cspDay;
		this.cspReorder=cspReorder;
		this.cspProfile=cspProfile;
		this.cspUpdateTime=cspUpdateTime;
		this.cspAddTime=cspAddTime;
		this.cspStatus=cspStatus;
	}
	
	//设置非空字段
	public CsPack setNotNull(String cspName,Double cspPrice,Short cspReorder,Date cspUpdateTime,Date cspAddTime,Short cspStatus){
		this.cspName=cspName;
		this.cspPrice=cspPrice;
		this.cspReorder=cspReorder;
		this.cspUpdateTime=cspUpdateTime;
		this.cspAddTime=cspAddTime;
		this.cspStatus=cspStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsPack cspId(Long cspId){
		this.cspId = cspId;
		this.setSeted(F.cspId);
		return this;
	}
	/** 套餐名称 [非空]       **/
	public CsPack cspName(String cspName){
		this.cspName = cspName;
		this.setSeted(F.cspName);
		return this;
	}
	/** 套餐价格 [非空]       **/
	public CsPack cspPrice(Double cspPrice){
		this.cspPrice = cspPrice;
		this.setSeted(F.cspPrice);
		return this;
	}
	/** 套餐标识        **/
	public CsPack cspFlag(String cspFlag){
		this.cspFlag = cspFlag;
		this.setSeted(F.cspFlag);
		return this;
	}
	/** 有效期类型    0:不限 1:一月 2:一季 3:半年 4:一年 5:天数     **/
	public CsPack cspValidity(Short cspValidity){
		this.cspValidity = cspValidity;
		this.setSeted(F.cspValidity);
		return this;
	}
	/** 有效天数     当有效期类型为天数时有效   **/
	public CsPack cspDay(Integer cspDay){
		this.cspDay = cspDay;
		this.setSeted(F.cspDay);
		return this;
	}
	/** 可自动续订 [非空]   0:否 1:是  当有效期类型不为不限时有效   **/
	public CsPack cspReorder(Short cspReorder){
		this.cspReorder = cspReorder;
		this.setSeted(F.cspReorder);
		return this;
	}
	/** 套餐说明        **/
	public CsPack cspProfile(String cspProfile){
		this.cspProfile = cspProfile;
		this.setSeted(F.cspProfile);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsPack cspUpdateTime(Date cspUpdateTime){
		this.cspUpdateTime = cspUpdateTime;
		this.setSeted(F.cspUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsPack cspAddTime(Date cspAddTime){
		this.cspAddTime = cspAddTime;
		this.setSeted(F.cspAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsPack cspStatus(Short cspStatus){
		this.cspStatus = cspStatus;
		this.setSeted(F.cspStatus);
		return this;
	}
	
	
	//克隆对象
	public CsPack clone(){
		CsPack clone = new CsPack();
		clone.cspName=this.cspName;
		clone.cspPrice=this.cspPrice;
		clone.cspReorder=this.cspReorder;
		clone.cspUpdateTime=this.cspUpdateTime;
		clone.cspAddTime=this.cspAddTime;
		clone.cspStatus=this.cspStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取系统套餐
	 * @param id
	 * @return
	 */
	public static @api CsPack get(Long id){		
		return getCsPackById(id);
	}
	/**
	 * 获取所有系统套餐
	 * @return
	 */
	public static @api List<CsPack> list(Map params,Integer size){
		return getCsPackList(params,size);
	}
	/**
	 * 获取系统套餐分页
	 * @return
	 */
	public static @api Page<CsPack> page(int page,int size,Map params){
		return getCsPackPage(page, size , params);
	}
	/**
	 * 根据查询条件取系统套餐
	 * @param params
	 * @return
	 */
	public static @api CsPack Get(Map params){
		return getCsPack(params);
	}
	/**
	 * 获取系统套餐数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsPackCount(params);
	}
	/**
	 * 获取系统套餐数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsPackEval(eval,params);
	}
	
	/**
	 * 根据ID取系统套餐
	 * @param id
	 * @return
	 */
	public static @api CsPack getCsPackById(Long id){		
		CsPack csPack = (CsPack) $.GetRequest("CsPack$"+id);
		if(csPack!=null)
			return csPack;
		com.ccclubs.service.admin.ICsPackService csPackService = $.GetSpringBean("csPackService");		
		return csPackService.getCsPackById(id);
	}
	
	
	/**
	 * 根据ID取系统套餐的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsPack.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsPack csPack = get(id);
		if(csPack!=null){
			String strValue = csPack.getCspName$();
			if(!"CspName".equals("CspName"))
				strValue+="("+csPack.getCspName$()+")";
			MemCache.setValue(CsPack.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCspName$();
		if(!"CspName".equals("CspName"))
			keyValue+="("+this.getCspName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有系统套餐
	 * @return
	 */
	public static @api List<CsPack> getCsPackList(Map params,Integer size){
		com.ccclubs.service.admin.ICsPackService csPackService = $.GetSpringBean("csPackService");
		return csPackService.getCsPackList(params, size);
	}
	
	/**
	 * 获取系统套餐分页
	 * @return
	 */
	public static @api Page<CsPack> getCsPackPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsPackService csPackService = $.GetSpringBean("csPackService");
		return csPackService.getCsPackPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取系统套餐
	 * @param params
	 * @return
	 */
	public static @api CsPack getCsPack(Map params){
		com.ccclubs.service.admin.ICsPackService csPackService = $.GetSpringBean("csPackService");
		return csPackService.getCsPack(params);
	}
	
	/**
	 * 获取系统套餐数
	 * @return
	 */
	public static @api Long getCsPackCount(Map params){
		com.ccclubs.service.admin.ICsPackService csPackService = $.GetSpringBean("csPackService");
		return csPackService.getCsPackCount(params);
	}
		
		
	/**
	 * 获取系统套餐自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsPackEval(String eval,Map params){
		com.ccclubs.service.admin.ICsPackService csPackService = $.GetSpringBean("csPackService");
		return csPackService.getCsPackEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsPack(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsPackService csPackService = $.GetSpringBean("csPackService");
		csPackService.updateCsPackByConfirm(set, where);
	}
	
	
	/**
	 * 保存系统套餐对象
	 * @param params
	 * @return
	 */
	public CsPack save(){
		com.ccclubs.service.admin.ICsPackService csPackService = $.GetSpringBean("csPackService");
		if(this.getCspId()!=null)
			csPackService.updateCsPack(this);
		else
			return csPackService.saveCsPack(this);
		return this;
	}
	
	
	/**
	 * 更新系统套餐对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsPackService csPackService = $.GetSpringBean("csPackService");
		csPackService.updateCsPack$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsPackService csPackService = $.GetSpringBean("csPackService");
		if($.equals($.config("logic_delete"),"true"))
			csPackService.removeCsPackById(this.getCspId());
		else
			csPackService.deleteCsPackById(this.getCspId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsPackService csPackService = $.GetSpringBean("csPackService");
		return csPackService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCspId(){
		return this.cspId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCspId$(){
		String strValue="";
		 strValue=$.str(this.getCspId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCspId(Long cspId){
		this.cspId = cspId;
		this.setSeted(F.cspId);
	}
	/*******************************套餐名称**********************************/	
	/**
	* 套餐名称 [非空]      
	**/
	public String getCspName(){
		return this.cspName;
	}
	/**
	* 获取套餐名称格式化(toString)
	**/
	public String getCspName$(){
		String strValue="";
		 strValue=$.str(this.getCspName());
	 	 return strValue;
	}
	/**
	* 套餐名称 [非空]      
	**/
	public void setCspName(String cspName){
		this.cspName = cspName;
		this.setSeted(F.cspName);
	}
	/*******************************套餐价格**********************************/	
	/**
	* 套餐价格 [非空]      
	**/
	public Double getCspPrice(){
		return this.cspPrice;
	}
	/**
	* 获取套餐价格格式化(toString)
	**/
	public String getCspPrice$(){
		String strValue="";
		 strValue=$.str(this.getCspPrice());	
	 	 return strValue;
	}
	/**
	* 套餐价格 [非空]      
	**/
	public void setCspPrice(Double cspPrice){
		this.cspPrice = cspPrice;
		this.setSeted(F.cspPrice);
	}
	/*******************************套餐标识**********************************/	
	/**
	* 套餐标识       
	**/
	public String getCspFlag(){
		return this.cspFlag;
	}
	/**
	* 获取套餐标识格式化(toString)
	**/
	public String getCspFlag$(){
		String strValue="";
		 strValue=$.str(this.getCspFlag());
	 	 return strValue;
	}
	/**
	* 套餐标识       
	**/
	public void setCspFlag(String cspFlag){
		this.cspFlag = cspFlag;
		this.setSeted(F.cspFlag);
	}
	/*******************************有效期类型**********************************/	
	/**
	* 有效期类型    0:不限 1:一月 2:一季 3:半年 4:一年 5:天数    
	**/
	public Short getCspValidity(){
		return this.cspValidity;
	}
	/**
	* 获取有效期类型格式化(toString)
	**/
	public String getCspValidity$(){
		String strValue="";
		 if($.equals($.str(this.getCspValidity()),"0"))
			strValue=$.str("不限");		 
		 if($.equals($.str(this.getCspValidity()),"1"))
			strValue=$.str("一月");		 
		 if($.equals($.str(this.getCspValidity()),"2"))
			strValue=$.str("一季");		 
		 if($.equals($.str(this.getCspValidity()),"3"))
			strValue=$.str("半年");		 
		 if($.equals($.str(this.getCspValidity()),"4"))
			strValue=$.str("一年");		 
		 if($.equals($.str(this.getCspValidity()),"5"))
			strValue=$.str("天数");		 
	 	 return strValue;
	}
	/**
	* 有效期类型    0:不限 1:一月 2:一季 3:半年 4:一年 5:天数    
	**/
	public void setCspValidity(Short cspValidity){
		this.cspValidity = cspValidity;
		this.setSeted(F.cspValidity);
	}
	/*******************************有效天数**********************************/	
	/**
	* 有效天数     当有效期类型为天数时有效  
	**/
	public Integer getCspDay(){
		return this.cspDay;
	}
	/**
	* 获取有效天数格式化(toString)
	**/
	public String getCspDay$(){
		String strValue="";
		 strValue=$.str(this.getCspDay());
	 	 return strValue;
	}
	/**
	* 有效天数     当有效期类型为天数时有效  
	**/
	public void setCspDay(Integer cspDay){
		this.cspDay = cspDay;
		this.setSeted(F.cspDay);
	}
	/*******************************可自动续订**********************************/	
	/**
	* 可自动续订 [非空]   0:否 1:是  当有效期类型不为不限时有效  
	**/
	public Short getCspReorder(){
		return this.cspReorder;
	}
	/**
	* 获取可自动续订格式化(toString)
	**/
	public String getCspReorder$(){
		String strValue="";
		 if($.equals($.str(this.getCspReorder()),"0"))
			strValue=$.str("否");		 
		 if($.equals($.str(this.getCspReorder()),"1"))
			strValue=$.str("是");		 
	 	 return strValue;
	}
	/**
	* 可自动续订 [非空]   0:否 1:是  当有效期类型不为不限时有效  
	**/
	public void setCspReorder(Short cspReorder){
		this.cspReorder = cspReorder;
		this.setSeted(F.cspReorder);
	}
	/*******************************反向关联的套餐内容列表**********************************/	
	/**
	* 获取与当前对象反向关联的套餐内容列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsPackInfo> getCspInfos(){
		com.ccclubs.service.admin.ICsPackInfoService csPackInfoService = $.GetSpringBean("csPackInfoService");
		Map params = new HashMap();
		params.put("cspiPack",this.getCspId());
		return csPackInfoService.getCsPackInfoList(params,-1);
	}
	/**
	* 获取套餐内容格式化(toString)
	**/
	public String getCspInfos$(){
		return "[...]";
	}
	/*******************************套餐说明**********************************/	
	/**
	* 套餐说明       
	**/
	public String getCspProfile(){
		return this.cspProfile;
	}
	/**
	* 获取套餐说明格式化(toString)
	**/
	public String getCspProfile$(){
		String strValue="";
		 strValue=$.str(this.getCspProfile());
	 	 return strValue;
	}
	/**
	* 套餐说明       
	**/
	public void setCspProfile(String cspProfile){
		this.cspProfile = cspProfile;
		this.setSeted(F.cspProfile);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCspUpdateTime(){
		return this.cspUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCspUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCspUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCspUpdateTime(Date cspUpdateTime){
		this.cspUpdateTime = cspUpdateTime;
		this.setSeted(F.cspUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCspAddTime(){
		return this.cspAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCspAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCspAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCspAddTime(Date cspAddTime){
		this.cspAddTime = cspAddTime;
		this.setSeted(F.cspAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCspStatus(){
		return this.cspStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCspStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCspStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCspStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCspStatus(Short cspStatus){
		this.cspStatus = cspStatus;
		this.setSeted(F.cspStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsPack.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsPack.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsPack.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsPack.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsPack.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsPack.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsPack.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsPack.this);
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
		public M cspId(Object cspId){this.put("cspId", cspId);return this;};
	 	/** and csp_id is null */
 		public M cspIdNull(){if(this.get("cspIdNot")==null)this.put("cspIdNot", "");this.put("cspId", null);return this;};
 		/** not .... */
 		public M cspIdNot(){this.put("cspIdNot", "not");return this;};
		/** 套餐名称 [非空]       **/
		public M cspName(Object cspName){this.put("cspName", cspName);return this;};
	 	/** and csp_name is null */
 		public M cspNameNull(){if(this.get("cspNameNot")==null)this.put("cspNameNot", "");this.put("cspName", null);return this;};
 		/** not .... */
 		public M cspNameNot(){this.put("cspNameNot", "not");return this;};
		/** 套餐价格 [非空]       **/
		public M cspPrice(Object cspPrice){this.put("cspPrice", cspPrice);return this;};
	 	/** and csp_price is null */
 		public M cspPriceNull(){if(this.get("cspPriceNot")==null)this.put("cspPriceNot", "");this.put("cspPrice", null);return this;};
 		/** not .... */
 		public M cspPriceNot(){this.put("cspPriceNot", "not");return this;};
		/** and csp_price >= ? */
		public M cspPriceMin(Object min){this.put("cspPriceMin", min);return this;};
		/** and csp_price <= ? */
		public M cspPriceMax(Object max){this.put("cspPriceMax", max);return this;};
		/** 套餐标识        **/
		public M cspFlag(Object cspFlag){this.put("cspFlag", cspFlag);return this;};
	 	/** and csp_flag is null */
 		public M cspFlagNull(){if(this.get("cspFlagNot")==null)this.put("cspFlagNot", "");this.put("cspFlag", null);return this;};
 		/** not .... */
 		public M cspFlagNot(){this.put("cspFlagNot", "not");return this;};
		/** 有效期类型    0:不限 1:一月 2:一季 3:半年 4:一年 5:天数     **/
		public M cspValidity(Object cspValidity){this.put("cspValidity", cspValidity);return this;};
	 	/** and csp_validity is null */
 		public M cspValidityNull(){if(this.get("cspValidityNot")==null)this.put("cspValidityNot", "");this.put("cspValidity", null);return this;};
 		/** not .... */
 		public M cspValidityNot(){this.put("cspValidityNot", "not");return this;};
		/** 有效天数     当有效期类型为天数时有效   **/
		public M cspDay(Object cspDay){this.put("cspDay", cspDay);return this;};
	 	/** and csp_day is null */
 		public M cspDayNull(){if(this.get("cspDayNot")==null)this.put("cspDayNot", "");this.put("cspDay", null);return this;};
 		/** not .... */
 		public M cspDayNot(){this.put("cspDayNot", "not");return this;};
		/** and csp_day >= ? */
		public M cspDayMin(Object min){this.put("cspDayMin", min);return this;};
		/** and csp_day <= ? */
		public M cspDayMax(Object max){this.put("cspDayMax", max);return this;};
		/** 可自动续订 [非空]   0:否 1:是  当有效期类型不为不限时有效   **/
		public M cspReorder(Object cspReorder){this.put("cspReorder", cspReorder);return this;};
	 	/** and csp_reorder is null */
 		public M cspReorderNull(){if(this.get("cspReorderNot")==null)this.put("cspReorderNot", "");this.put("cspReorder", null);return this;};
 		/** not .... */
 		public M cspReorderNot(){this.put("cspReorderNot", "not");return this;};
		/** 套餐说明        **/
		public M cspProfile(Object cspProfile){this.put("cspProfile", cspProfile);return this;};
	 	/** and csp_profile is null */
 		public M cspProfileNull(){if(this.get("cspProfileNot")==null)this.put("cspProfileNot", "");this.put("cspProfile", null);return this;};
 		/** not .... */
 		public M cspProfileNot(){this.put("cspProfileNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M cspUpdateTime(Object cspUpdateTime){this.put("cspUpdateTime", cspUpdateTime);return this;};
	 	/** and csp_update_time is null */
 		public M cspUpdateTimeNull(){if(this.get("cspUpdateTimeNot")==null)this.put("cspUpdateTimeNot", "");this.put("cspUpdateTime", null);return this;};
 		/** not .... */
 		public M cspUpdateTimeNot(){this.put("cspUpdateTimeNot", "not");return this;};
 		/** and csp_update_time >= ? */
 		public M cspUpdateTimeStart(Object start){this.put("cspUpdateTimeStart", start);return this;};			
 		/** and csp_update_time <= ? */
 		public M cspUpdateTimeEnd(Object end){this.put("cspUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cspAddTime(Object cspAddTime){this.put("cspAddTime", cspAddTime);return this;};
	 	/** and csp_add_time is null */
 		public M cspAddTimeNull(){if(this.get("cspAddTimeNot")==null)this.put("cspAddTimeNot", "");this.put("cspAddTime", null);return this;};
 		/** not .... */
 		public M cspAddTimeNot(){this.put("cspAddTimeNot", "not");return this;};
 		/** and csp_add_time >= ? */
 		public M cspAddTimeStart(Object start){this.put("cspAddTimeStart", start);return this;};			
 		/** and csp_add_time <= ? */
 		public M cspAddTimeEnd(Object end){this.put("cspAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M cspStatus(Object cspStatus){this.put("cspStatus", cspStatus);return this;};
	 	/** and csp_status is null */
 		public M cspStatusNull(){if(this.get("cspStatusNot")==null)this.put("cspStatusNot", "");this.put("cspStatus", null);return this;};
 		/** not .... */
 		public M cspStatusNot(){this.put("cspStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有系统套餐 **/
		public @api List<CsPack> list(Integer size){
			return getCsPackList(this,size);
		}
		/** 获取系统套餐分页 **/
		public @api Page<CsPack> page(int page,int size){
			return getCsPackPage(page, size , this);
		}
		/** 根据查询条件取系统套餐 **/
		public @api CsPack get(){
			return getCsPack(this);
		}
		/** 获取系统套餐数 **/
		public @api Long count(){
			return getCsPackCount(this);
		}
		/** 获取系统套餐表达式 **/
		public @api <T> T eval(String strEval){
			return getCsPackEval(strEval,this);
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
			updateCsPack(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cspId="cspId";
		/** 套餐名称 [非空]       **/
		public final static @type(String.class) @like String cspName="cspName";
		/** 套餐价格 [非空]       **/
		public final static @type(Double.class)  String cspPrice="cspPrice";
		/** and csp_price >= ? */
		public final static @type(Double.class) String cspPriceMin="cspPriceMin";
		/** and csp_price <= ? */
		public final static @type(Double.class) String cspPriceMax="cspPriceMax";
		/** 套餐标识        **/
		public final static @type(String.class) @like String cspFlag="cspFlag";
		/** 有效期类型    0:不限 1:一月 2:一季 3:半年 4:一年 5:天数     **/
		public final static @type(Short.class)  String cspValidity="cspValidity";
		/** 有效天数     当有效期类型为天数时有效   **/
		public final static @type(Integer.class)  String cspDay="cspDay";
		/** and csp_day >= ? */
		public final static @type(Integer.class) String cspDayMin="cspDayMin";
		/** and csp_day <= ? */
		public final static @type(Integer.class) String cspDayMax="cspDayMax";
		/** 可自动续订 [非空]   0:否 1:是  当有效期类型不为不限时有效   **/
		public final static @type(Short.class)  String cspReorder="cspReorder";
		/** 套餐说明        **/
		public final static @type(String.class) @like String cspProfile="cspProfile";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cspUpdateTime="cspUpdateTime";
	 	/** and csp_update_time >= ? */
 		public final static @type(Date.class) String cspUpdateTimeStart="cspUpdateTimeStart";
 		/** and csp_update_time <= ? */
 		public final static @type(Date.class) String cspUpdateTimeEnd="cspUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cspAddTime="cspAddTime";
	 	/** and csp_add_time >= ? */
 		public final static @type(Date.class) String cspAddTimeStart="cspAddTimeStart";
 		/** and csp_add_time <= ? */
 		public final static @type(Date.class) String cspAddTimeEnd="cspAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String cspStatus="cspStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cspId="csp_id";
		/** 套餐名称 [非空]       **/
		public final static String cspName="csp_name";
		/** 套餐价格 [非空]       **/
		public final static String cspPrice="csp_price";
		/** 套餐标识        **/
		public final static String cspFlag="csp_flag";
		/** 有效期类型    0:不限 1:一月 2:一季 3:半年 4:一年 5:天数     **/
		public final static String cspValidity="csp_validity";
		/** 有效天数     当有效期类型为天数时有效   **/
		public final static String cspDay="csp_day";
		/** 可自动续订 [非空]   0:否 1:是  当有效期类型不为不限时有效   **/
		public final static String cspReorder="csp_reorder";
		/** 套餐说明        **/
		public final static String cspProfile="csp_profile";
		/** 修改时间 [非空]       **/
		public final static String cspUpdateTime="csp_update_time";
		/** 添加时间 [非空]       **/
		public final static String cspAddTime="csp_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String cspStatus="csp_status";
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
				$.Set(name,CsPack.getCsPack(params));
			else
				$.Set(name,CsPack.getCsPackList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取系统套餐数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsPack) $.GetRequest("CsPack$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsPack.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsPack.getCsPack(params);
			else
				value = CsPack.getCsPackList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsPack.Get($.add(CsPack.F.cspId,param));
		else if(!$.empty(param.toString()))
			value = CsPack.get(Long.valueOf(param.toString()));
		$.SetRequest("CsPack$"+param.hashCode(), value);
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
	public void mergeSet(CsPack old){
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