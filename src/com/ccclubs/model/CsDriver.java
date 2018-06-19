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

@namespace("unit/driver")
public @caption("专车司机") @table("cs_driver") class CsDriver implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csd_id")   @primary   @note("  ") Long csdId;
	private @caption("城市") @column("csd_host")    @relate("$csdHost") @RelateClass(SrvHost.class)  @note("  ") Long csdHost;
	private SrvHost $csdHost;//关联对象[城市]
	private @caption("业务网点") @column("csd_outlets")      @note("  ") String csdOutlets;
	private @caption("姓名") @column("csd_name")      @note("  ") String csdName;
	private @caption("性别") @column("csd_sex")      @note(" 0:女 1:男  ") Short csdSex;
	private @caption("手机号码") @column("csd_mobile")      @note("  ") String csdMobile;
	private @caption("密码") @column("csd_password")      @note("  ") String csdPassword;
	private @caption("司机照片") @column("csd_photo")      @note("  ") String csdPhoto;
	private @caption("身份证号码") @column("csd_certify_num")      @note("  ") String csdCertifyNum;
	private @caption("驾驶证号码") @column("csd_driver_num")      @note("  ") String csdDriverNum;
	private @caption("实名认证状态") @column("csd_v_real")      @note(" 0:未认证 1:已认证  ") Short csdVReal;
	private @caption("数据状态") @column("csd_status")      @note(" 1:正常 0:禁用  ") Short csdStatus;
	private @caption("创建时间") @column("csd_add_time")      @note("  ") Date csdAddTime;
	private @caption("修改时间") @column("csd_update_time")      @note("  ") Date csdUpdateTime;
	
	//默认构造函数
	public CsDriver(){
	
	}
	
	//主键构造函数
	public CsDriver(Long id){
		this.csdId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsDriver(Long csdHost,String csdOutlets,String csdName,Short csdSex,String csdMobile,String csdPassword,String csdPhoto,String csdCertifyNum,String csdDriverNum,Short csdVReal,Short csdStatus,Date csdAddTime,Date csdUpdateTime){
		this.csdHost=csdHost;
		this.csdOutlets=csdOutlets;
		this.csdName=csdName;
		this.csdSex=csdSex;
		this.csdMobile=csdMobile;
		this.csdPassword=csdPassword;
		this.csdPhoto=csdPhoto;
		this.csdCertifyNum=csdCertifyNum;
		this.csdDriverNum=csdDriverNum;
		this.csdVReal=csdVReal;
		this.csdStatus=csdStatus;
		this.csdAddTime=csdAddTime;
		this.csdUpdateTime=csdUpdateTime;
	}
	
	//设置非空字段
	public CsDriver setNotNull(String csdOutlets,String csdName,Short csdSex,String csdMobile,String csdPassword,Short csdStatus){
		this.csdOutlets=csdOutlets;
		this.csdName=csdName;
		this.csdSex=csdSex;
		this.csdMobile=csdMobile;
		this.csdPassword=csdPassword;
		this.csdStatus=csdStatus;
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsDriver csdId(Long csdId){
		this.csdId = csdId;
		this.setSeted(F.csdId);
		return this;
	}
	/** 城市 [非空]       **/
	public CsDriver csdHost(Long csdHost){
		this.csdHost = csdHost;
		this.setSeted(F.csdHost);
		return this;
	}
	/** 业务网点 [可空]       **/
	public CsDriver csdOutlets(String csdOutlets){
		this.csdOutlets = csdOutlets;
		this.setSeted(F.csdOutlets);
		return this;
	}
	/** 姓名 [可空]       **/
	public CsDriver csdName(String csdName){
		this.csdName = csdName;
		this.setSeted(F.csdName);
		return this;
	}
	/** 性别 [可空]       **/
	public CsDriver csdSex(Short csdSex){
		this.csdSex = csdSex;
		this.setSeted(F.csdSex);
		return this;
	}
	/** 手机号码 [可空]       **/
	public CsDriver csdMobile(String csdMobile){
		this.csdMobile = csdMobile;
		this.setSeted(F.csdMobile);
		return this;
	}
	/** 密码 [可空]       **/
	public CsDriver csdPassword(String csdPassword){
		this.csdPassword = csdPassword;
		this.setSeted(F.csdPassword);
		return this;
	}
	/** 司机照片 [非空]       **/
	public CsDriver csdPhoto(String csdPhoto){
		this.csdPhoto = csdPhoto;
		this.setSeted(F.csdPhoto);
		return this;
	}
	/** 身份证号码 [非空]       **/
	public CsDriver csdCertifyNum(String csdCertifyNum){
		this.csdCertifyNum = csdCertifyNum;
		this.setSeted(F.csdCertifyNum);
		return this;
	}
	/** 驾驶证号码 [非空]       **/
	public CsDriver csdDriverNum(String csdDriverNum){
		this.csdDriverNum = csdDriverNum;
		this.setSeted(F.csdDriverNum);
		return this;
	}
	/** 实名认证状态 [非空]       **/
	public CsDriver csdVReal(Short csdVReal){
		this.csdVReal = csdVReal;
		this.setSeted(F.csdVReal);
		return this;
	}
	/** 数据状态 [可空]       **/
	public CsDriver csdStatus(Short csdStatus){
		this.csdStatus = csdStatus;
		this.setSeted(F.csdStatus);
		return this;
	}
	/** 创建时间 [非空]       **/
	public CsDriver csdAddTime(Date csdAddTime){
		this.csdAddTime = csdAddTime;
		this.setSeted(F.csdAddTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsDriver csdUpdateTime(Date csdUpdateTime){
		this.csdUpdateTime = csdUpdateTime;
		this.setSeted(F.csdUpdateTime);
		return this;
	}
	
	//克隆对象
	public CsDriver clone(){
		CsDriver clone = new CsDriver();
		clone.csdId=this.csdId;
		clone.csdHost=this.csdHost;
		clone.csdOutlets=this.csdOutlets;
		clone.csdName=this.csdName;
		clone.csdSex=this.csdSex;
		clone.csdMobile=this.csdMobile;
		clone.csdPassword=this.csdPassword;
		clone.csdPhoto=this.csdPhoto;
		clone.csdCertifyNum=this.csdCertifyNum;
		clone.csdDriverNum=this.csdDriverNum;
		clone.csdVReal=this.csdVReal;
		clone.csdStatus=this.csdStatus;
		clone.csdAddTime=this.csdAddTime;
		clone.csdUpdateTime=this.csdUpdateTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取专车司机
	 * @param id
	 * @return
	 */
	public static @api CsDriver get(Long id){		
		return getCsDriverById(id);
	}
	/**
	 * 获取所有专车司机
	 * @return
	 */
	public static @api List<CsDriver> list(Map params,Integer size){
		return getCsDriverList(params,size);
	}
	/**
	 * 获取专车司机分页
	 * @return
	 */
	public static @api Page<CsDriver> page(int page,int size,Map params){
		return getCsDriverPage(page, size , params);
	}
	/**
	 * 根据查询条件取专车司机
	 * @param params
	 * @return
	 */
	public static @api CsDriver Get(Map params){
		return getCsDriver(params);
	}
	/**
	 * 获取专车司机数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsDriverCount(params);
	}
	/**
	 * 获取专车司机数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsDriverEval(eval,params);
	}
	
	/**
	 * 根据ID取专车司机
	 * @param id
	 * @return
	 */
	public static @api CsDriver getCsDriverById(Long id){		
		CsDriver csDriver = (CsDriver) $.GetRequest("CsDriver$"+id);
		if(csDriver!=null)
			return csDriver;
		com.ccclubs.service.admin.ICsDriverService csDriverService = $.GetSpringBean("csDriverService");		
		return csDriverService.getCsDriverById(id);
	}
	
	
	/**
	 * 根据ID取专车司机的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsDriver.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsDriver csDriver = get(id);
		if(csDriver!=null){
			String strValue = csDriver.getCsdId$();
			if(!"CsdId".equals("CsdId"))
				strValue+="("+csDriver.getCsdId$()+")";
			MemCache.setValue(CsDriver.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsdId$();
		if(!"CsdId".equals("CsdId"))
			keyValue+="("+this.getCsdId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有专车司机
	 * @return
	 */
	public static @api List<CsDriver> getCsDriverList(Map params,Integer size){
		com.ccclubs.service.admin.ICsDriverService csDriverService = $.GetSpringBean("csDriverService");
		return csDriverService.getCsDriverList(params, size);
	}
	
	/**
	 * 获取专车司机分页
	 * @return
	 */
	public static @api Page<CsDriver> getCsDriverPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsDriverService csDriverService = $.GetSpringBean("csDriverService");
		return csDriverService.getCsDriverPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取专车司机
	 * @param params
	 * @return
	 */
	public static @api CsDriver getCsDriver(Map params){
		com.ccclubs.service.admin.ICsDriverService csDriverService = $.GetSpringBean("csDriverService");
		return csDriverService.getCsDriver(params);
	}
	
	/**
	 * 获取专车司机数
	 * @return
	 */
	public static @api Long getCsDriverCount(Map params){
		com.ccclubs.service.admin.ICsDriverService csDriverService = $.GetSpringBean("csDriverService");
		return csDriverService.getCsDriverCount(params);
	}
		
		
	/**
	 * 获取专车司机自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsDriverEval(String eval,Map params){
		com.ccclubs.service.admin.ICsDriverService csDriverService = $.GetSpringBean("csDriverService");
		return csDriverService.getCsDriverEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsDriver(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsDriverService csDriverService = $.GetSpringBean("csDriverService");
		csDriverService.updateCsDriverByConfirm(set, where);
	}
	
	
	/**
	 * 保存专车司机对象
	 * @param params
	 * @return
	 */
	public CsDriver save(){
		com.ccclubs.service.admin.ICsDriverService csDriverService = $.GetSpringBean("csDriverService");
		if(this.getCsdId()!=null)
			csDriverService.updateCsDriver(this);
		else
			return csDriverService.saveCsDriver(this);
		return this;
	}
	
	
	/**
	 * 更新专车司机对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsDriverService csDriverService = $.GetSpringBean("csDriverService");
		csDriverService.updateCsDriver$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsDriverService csDriverService = $.GetSpringBean("csDriverService");
		if($.equals($.config("logic_delete"),"true"))
			csDriverService.removeCsDriverById(this.getCsdId());
		else
			csDriverService.deleteCsDriverById(this.getCsdId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsDriverService csDriverService = $.GetSpringBean("csDriverService");
		return csDriverService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsdId(){
		return this.csdId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsdId$(){
		String strValue="";
		 strValue=$.str(this.getCsdId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCsdId(Long csdId){
		this.csdId = csdId;
		this.setSeted(F.csdId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空]      
	**/
	public Long getCsdHost(){
		return this.csdHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsdHost$(){
		String strValue="";
		if(this.getCsdHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsdHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空]     
	**/
	public void setCsdHost(Long csdHost){
		this.csdHost = csdHost;
		this.setSeted(F.csdHost);
	}
	/**
	* 获取关联对象[城市]
	**/	 			
 	public SrvHost get$csdHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsdHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsdHost()!=null){
			srvHost = SrvHost.get(this.getCsdHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsdHost(), srvHost);
	 	return srvHost;
	}
 	
 	/**
	* 获取关联对象[网点]
	**/	 			
 	public List<com.ccclubs.model.CsOutlets> get$csdOutlets(){
		if(!$.empty(this.getCsdOutlets())){
			return com.ccclubs.model.CsOutlets.getCsOutletsList($.add("definex","cso_id in ("+$.str(this.getCsdOutlets()).replace("#","")+")"),-1);
		}else
			return new ArrayList();
	}
 	
	/*******************************业务网点**********************************/	
	/**
	* 业务网点 [可空]      
	**/
	public String getCsdOutlets(){
		return this.csdOutlets;
	}
	/**
	* 获取业务网点格式化(toString)
	**/
	public String getCsdOutlets$(){
		String strValue="";
		if(!$.empty(this.getCsdOutlets())){
			List<com.ccclubs.model.CsOutlets> items = com.ccclubs.model.CsOutlets.getCsOutletsList($.add("definex","cso_id in ("+$.str(this.getCsdOutlets()).replace("#","")+")"),-1);
			for(com.ccclubs.model.CsOutlets item1:items)
				strValue+="["+$.str(item1.getCsoName())+"]";
		}
	 	return strValue;
	}
	/**
	* 业务网点 [可空]     
	**/
	public void setCsdOutlets(String csdOutlets){
		this.csdOutlets = csdOutlets;
		this.setSeted(F.csdOutlets);
	}
	/*******************************姓名**********************************/	
	/**
	* 姓名 [可空]      
	**/
	public String getCsdName(){
		return this.csdName;
	}
	/**
	* 获取姓名格式化(toString)
	**/
	public String getCsdName$(){
		String strValue="";
		 strValue=$.str(this.getCsdName());
	 	 return strValue;
	}
	/**
	* 姓名 [可空]     
	**/
	public void setCsdName(String csdName){
		this.csdName = csdName;
		this.setSeted(F.csdName);
	}
	/*******************************性别**********************************/	
	/**
	* 性别 [可空]      
	**/
	public Short getCsdSex(){
		return this.csdSex;
	}
	/**
	* 获取性别格式化(toString)
	**/
	public String getCsdSex$(){
		String strValue="";
		 if($.equals($.str(this.getCsdSex()),"1"))
			 strValue=$.str("男");
		 if($.equals($.str(this.getCsdSex()),"0"))
			 strValue=$.str("女");
	 	 return strValue;
	}
	/**
	* 性别 [可空]     
	**/
	public void setCsdSex(Short csdSex){
		this.csdSex = csdSex;
		this.setSeted(F.csdSex);
	}
	/*******************************手机号码**********************************/	
	/**
	* 手机号码 [可空]      
	**/
	public String getCsdMobile(){
		return this.csdMobile;
	}
	/**
	* 获取手机号码格式化(toString)
	**/
	public String getCsdMobile$(){
		String strValue="";
		 strValue=$.str(this.getCsdMobile());
	 	 return strValue;
	}
	/**
	* 手机号码 [可空]     
	**/
	public void setCsdMobile(String csdMobile){
		this.csdMobile = csdMobile;
		this.setSeted(F.csdMobile);
	}
	/*******************************密码**********************************/	
	/**
	* 密码 [可空]      
	**/
	public String getCsdPassword(){
		return this.csdPassword;
	}
	/**
	* 获取密码格式化(toString)
	**/
	public String getCsdPassword$(){
		String strValue="";
		 strValue=$.str(this.getCsdPassword());
	 	 return strValue;
	}
	/**
	* 密码 [可空]     
	**/
	public void setCsdPassword(String csdPassword){
		this.csdPassword = csdPassword;
		this.setSeted(F.csdPassword);
	}
	/*******************************司机照片**********************************/	
	/**
	* 司机照片 [非空]      
	**/
	public String getCsdPhoto(){
		return this.csdPhoto;
	}
	/**
	* 获取司机照片格式化(toString)
	**/
	public String getCsdPhoto$(){
		String strValue="";
		 strValue=$.str(this.getCsdPhoto());
	 	 return strValue;
	}
	/**
	* 司机照片 [非空]     
	**/
	public void setCsdPhoto(String csdPhoto){
		this.csdPhoto = csdPhoto;
		this.setSeted(F.csdPhoto);
	}
	/*******************************身份证号码**********************************/	
	/**
	* 身份证号码 [非空]      
	**/
	public String getCsdCertifyNum(){
		return this.csdCertifyNum;
	}
	/**
	* 获取身份证号码格式化(toString)
	**/
	public String getCsdCertifyNum$(){
		String strValue="";
		 strValue=$.str(this.getCsdCertifyNum());
	 	 return strValue;
	}
	/**
	* 身份证号码 [非空]     
	**/
	public void setCsdCertifyNum(String csdCertifyNum){
		this.csdCertifyNum = csdCertifyNum;
		this.setSeted(F.csdCertifyNum);
	}
	/*******************************驾驶证号码**********************************/	
	/**
	* 驾驶证号码 [非空]      
	**/
	public String getCsdDriverNum(){
		return this.csdDriverNum;
	}
	/**
	* 获取驾驶证号码格式化(toString)
	**/
	public String getCsdDriverNum$(){
		String strValue="";
		 strValue=$.str(this.getCsdDriverNum());
	 	 return strValue;
	}
	/**
	* 驾驶证号码 [非空]     
	**/
	public void setCsdDriverNum(String csdDriverNum){
		this.csdDriverNum = csdDriverNum;
		this.setSeted(F.csdDriverNum);
	}
	/*******************************实名认证状态**********************************/	
	/**
	* 实名认证状态 [非空]      
	**/
	public Short getCsdVReal(){
		return this.csdVReal;
	}
	/**
	* 获取实名认证状态格式化(toString)
	**/
	public String getCsdVReal$(){
		String strValue="";
		 if($.equals($.str(this.getCsdVReal()),"1"))
			 strValue=$.str("已认证");
		 if($.equals($.str(this.getCsdVReal()),"0"))
			 strValue=$.str("未认证");
	 	 return strValue;
	}
	/**
	* 实名认证状态 [非空]     
	**/
	public void setCsdVReal(Short csdVReal){
		this.csdVReal = csdVReal;
		this.setSeted(F.csdVReal);
	}
	/*******************************数据状态**********************************/	
	/**
	* 数据状态 [可空]      
	**/
	public Short getCsdStatus(){
		return this.csdStatus;
	}
	/**
	* 获取数据状态格式化(toString)
	**/
	public String getCsdStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsdStatus()),"1"))
			 strValue=$.str("正常");
		 if($.equals($.str(this.getCsdStatus()),"0"))
			 strValue=$.str("禁用");
	 	 return strValue;
	}
	/**
	* 数据状态 [可空]     
	**/
	public void setCsdStatus(Short csdStatus){
		this.csdStatus = csdStatus;
		this.setSeted(F.csdStatus);
	}
	/*******************************创建时间**********************************/	
	/**
	* 创建时间 [非空]      
	**/
	public Date getCsdAddTime(){
		return this.csdAddTime;
	}
	/**
	* 获取创建时间格式化(toString)
	**/
	public String getCsdAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsdAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 创建时间 [非空]     
	**/
	public void setCsdAddTime(Date csdAddTime){
		this.csdAddTime = csdAddTime;
		this.setSeted(F.csdAddTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsdUpdateTime(){
		return this.csdUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsdUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsdUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCsdUpdateTime(Date csdUpdateTime){
		this.csdUpdateTime = csdUpdateTime;
		this.setSeted(F.csdUpdateTime);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsDriver.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsDriver.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsDriver.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsDriver.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsDriver.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsDriver.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsDriver.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsDriver.this);
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
		public M csdId(Object csdId){this.put("csdId", csdId);return this;};
		/** and csd_id is null */
		public M csdIdNull(){if(this.get("csdIdNot")==null)this.put("csdIdNot", "");this.put("csdId", null);return this;};
		/** not .... */
		public M csdIdNot(){this.put("csdIdNot", "not");return this;};
		/** 城市 [非空]       **/
		public M csdHost(Object csdHost){this.put("csdHost", csdHost);return this;};
		/** and csd_host is null */
		public M csdHostNull(){if(this.get("csdHostNot")==null)this.put("csdHostNot", "");this.put("csdHost", null);return this;};
		/** not .... */
		public M csdHostNot(){this.put("csdHostNot", "not");return this;};
		/** 业务网点 [可空]       **/
		public M csdOutlets(Object csdOutlets){this.put("csdOutlets", csdOutlets);return this;};
		/** and csd_outlets is null */
		public M csdOutletsNull(){if(this.get("csdOutletsNot")==null)this.put("csdOutletsNot", "");this.put("csdOutlets", null);return this;};
		/** not .... */
		public M csdOutletsNot(){this.put("csdOutletsNot", "not");return this;};
		/** 姓名 [可空]       **/
		public M csdName(Object csdName){this.put("csdName", csdName);return this;};
		/** and csd_name is null */
		public M csdNameNull(){if(this.get("csdNameNot")==null)this.put("csdNameNot", "");this.put("csdName", null);return this;};
		/** not .... */
		public M csdNameNot(){this.put("csdNameNot", "not");return this;};
		/** 性别 [可空]       **/
		public M csdSex(Object csdSex){this.put("csdSex", csdSex);return this;};
		/** and csd_sex is null */
		public M csdSexNull(){if(this.get("csdSexNot")==null)this.put("csdSexNot", "");this.put("csdSex", null);return this;};
		/** not .... */
		public M csdSexNot(){this.put("csdSexNot", "not");return this;};
		/** 手机号码 [可空]       **/
		public M csdMobile(Object csdMobile){this.put("csdMobile", csdMobile);return this;};
		/** and csd_mobile is null */
		public M csdMobileNull(){if(this.get("csdMobileNot")==null)this.put("csdMobileNot", "");this.put("csdMobile", null);return this;};
		/** not .... */
		public M csdMobileNot(){this.put("csdMobileNot", "not");return this;};
		/** 密码 [可空]       **/
		public M csdPassword(Object csdPassword){this.put("csdPassword", csdPassword);return this;};
		/** and csd_password is null */
		public M csdPasswordNull(){if(this.get("csdPasswordNot")==null)this.put("csdPasswordNot", "");this.put("csdPassword", null);return this;};
		/** not .... */
		public M csdPasswordNot(){this.put("csdPasswordNot", "not");return this;};
		/** 司机照片 [非空]       **/
		public M csdPhoto(Object csdPhoto){this.put("csdPhoto", csdPhoto);return this;};
		/** and csd_photo is null */
		public M csdPhotoNull(){if(this.get("csdPhotoNot")==null)this.put("csdPhotoNot", "");this.put("csdPhoto", null);return this;};
		/** not .... */
		public M csdPhotoNot(){this.put("csdPhotoNot", "not");return this;};
		/** 身份证号码 [非空]       **/
		public M csdCertifyNum(Object csdCertifyNum){this.put("csdCertifyNum", csdCertifyNum);return this;};
		/** and csd_certify_num is null */
		public M csdCertifyNumNull(){if(this.get("csdCertifyNumNot")==null)this.put("csdCertifyNumNot", "");this.put("csdCertifyNum", null);return this;};
		/** not .... */
		public M csdCertifyNumNot(){this.put("csdCertifyNumNot", "not");return this;};
		/** 驾驶证号码 [非空]       **/
		public M csdDriverNum(Object csdDriverNum){this.put("csdDriverNum", csdDriverNum);return this;};
		/** and csd_driver_num is null */
		public M csdDriverNumNull(){if(this.get("csdDriverNumNot")==null)this.put("csdDriverNumNot", "");this.put("csdDriverNum", null);return this;};
		/** not .... */
		public M csdDriverNumNot(){this.put("csdDriverNumNot", "not");return this;};
		/** 实名认证状态 [非空]       **/
		public M csdVReal(Object csdVReal){this.put("csdVReal", csdVReal);return this;};
		/** and csd_v_real is null */
		public M csdVRealNull(){if(this.get("csdVRealNot")==null)this.put("csdVRealNot", "");this.put("csdVReal", null);return this;};
		/** not .... */
		public M csdVRealNot(){this.put("csdVRealNot", "not");return this;};
		/** 数据状态 [可空]       **/
		public M csdStatus(Object csdStatus){this.put("csdStatus", csdStatus);return this;};
		/** and csd_status is null */
		public M csdStatusNull(){if(this.get("csdStatusNot")==null)this.put("csdStatusNot", "");this.put("csdStatus", null);return this;};
		/** not .... */
		public M csdStatusNot(){this.put("csdStatusNot", "not");return this;};
		/** 创建时间 [非空]       **/
		public M csdAddTime(Object csdAddTime){this.put("csdAddTime", csdAddTime);return this;};
		/** and csd_add_time is null */
		public M csdAddTimeNull(){if(this.get("csdAddTimeNot")==null)this.put("csdAddTimeNot", "");this.put("csdAddTime", null);return this;};
		/** not .... */
		public M csdAddTimeNot(){this.put("csdAddTimeNot", "not");return this;};
		/** and csd_add_time >= ? */
 		public M csdAddTimeStart(Object start){this.put("csdAddTimeStart", start);return this;};			
 		/** and csd_add_time <= ? */
 		public M csdAddTimeEnd(Object end){this.put("csdAddTimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M csdUpdateTime(Object csdUpdateTime){this.put("csdUpdateTime", csdUpdateTime);return this;};
		/** and csd_update_time is null */
		public M csdUpdateTimeNull(){if(this.get("csdUpdateTimeNot")==null)this.put("csdUpdateTimeNot", "");this.put("csdUpdateTime", null);return this;};
		/** not .... */
		public M csdUpdateTimeNot(){this.put("csdUpdateTimeNot", "not");return this;};
		/** and csd_update_time >= ? */
 		public M csdUpdateTimeStart(Object start){this.put("csdUpdateTimeStart", start);return this;};			
 		/** and csd_update_time <= ? */
 		public M csdUpdateTimeEnd(Object end){this.put("csdUpdateTimeEnd", end);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有专车司机 **/
		public @api List<CsDriver> list(Integer size){
			return getCsDriverList(this,size);
		}
		/** 获取专车司机分页 **/
		public @api Page<CsDriver> page(int page,int size){
			return getCsDriverPage(page, size , this);
		}
		/** 根据查询条件取专车司机 **/
		public @api CsDriver get(){
			return getCsDriver(this);
		}
		/** 获取专车司机数 **/
		public @api Long count(){
			return getCsDriverCount(this);
		}
		/** 获取专车司机表达式 **/
		public @api <T> T eval(String strEval){
			return getCsDriverEval(strEval,this);
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
			updateCsDriver(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csdId="csdId";
		/** 城市 [非空]       **/
		public final static @type(Long.class)  String csdHost="csdHost";
		/** 业务网点 [可空]       **/
		public final static @type(String.class)  String csdOutlets="csdOutlets";
		/** 姓名 [可空]       **/
		public final static @type(String.class)  String csdName="csdName";
		/** 性别 [可空]       **/
		public final static @type(Short.class)  String csdSex="csdSex";
		/** 手机号码 [可空]       **/
		public final static @type(String.class)  String csdMobile="csdMobile";
		/** 密码 [可空]       **/
		public final static @type(String.class)  String csdPassword="csdPassword";
		/** 司机照片 [非空]       **/
		public final static @type(String.class)  String csdPhoto="csdPhoto";
		/** 身份证号码 [非空]       **/
		public final static @type(String.class)  String csdCertifyNum="csdCertifyNum";
		/** 驾驶证号码 [非空]       **/
		public final static @type(String.class)  String csdDriverNum="csdDriverNum";
		/** 实名认证状态 [非空]       **/
		public final static @type(Short.class)  String csdVReal="csdVReal";
		/** 数据状态 [可空]       **/
		public final static @type(Short.class)  String csdStatus="csdStatus";
		/** 创建时间 [非空]       **/
		public final static @type(Date.class)  String csdAddTime="csdAddTime";
		/** and csd_add_time >= ? */
 		public final static @type(Date.class) String csdAddTimeStart="csdAddTimeStart";
 		/** and csd_add_time <= ? */
 		public final static @type(Date.class) String csdAddTimeEnd="csdAddTimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csdUpdateTime="csdUpdateTime";
		/** and csd_update_time >= ? */
 		public final static @type(Date.class) String csdUpdateTimeStart="csdUpdateTimeStart";
 		/** and csd_update_time <= ? */
 		public final static @type(Date.class) String csdUpdateTimeEnd="csdUpdateTimeEnd";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csdId="csd_id";
		/** 城市 [非空]       **/
		public final static String csdHost="csd_host";
		/** 业务网点 [可空]       **/
		public final static String csdOutlets="csd_outlets";
		/** 姓名 [可空]       **/
		public final static String csdName="csd_name";
		/** 性别 [可空]       **/
		public final static String csdSex="csd_sex";
		/** 手机号码 [可空]       **/
		public final static String csdMobile="csd_mobile";
		/** 密码 [可空]       **/
		public final static String csdPassword="csd_password";
		/** 司机照片 [非空]       **/
		public final static String csdPhoto="csd_photo";
		/** 身份证号码 [非空]       **/
		public final static String csdCertifyNum="csd_certify_num";
		/** 驾驶证号码 [非空]       **/
		public final static String csdDriverNum="csd_driver_num";
		/** 实名认证状态 [非空]       **/
		public final static String csdVReal="csd_v_real";
		/** 数据状态 [可空]       **/
		public final static String csdStatus="csd_status";
		/** 创建时间 [非空]       **/
		public final static String csdAddTime="csd_add_time";
		/** 修改时间 [非空]       **/
		public final static String csdUpdateTime="csd_update_time";
		
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
				$.Set(name,CsDriver.getCsDriver(params));
			else
				$.Set(name,CsDriver.getCsDriverList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取专车司机数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsDriver) $.GetRequest("CsDriver$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsDriver.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsDriver.getCsDriver(params);
			else
				value = CsDriver.getCsDriverList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsDriver.Get($.add(CsDriver.F.csdId,param));
		else if(!$.empty(param.toString()))
			value = CsDriver.get(Long.valueOf(param.toString()));
		$.SetRequest("CsDriver$"+param.hashCode(), value);
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
	public void mergeSet(CsDriver old){
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