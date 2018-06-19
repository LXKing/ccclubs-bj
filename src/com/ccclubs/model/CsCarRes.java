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

@namespace("unit/carres")
public @caption("专车资源") @table("cs_car_res") class CsCarRes implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cscr_id")   @primary   @note("  ") Long cscrId;
	private @caption("城市") @column("cscr_host")    @relate("$cscrHost") @RelateClass(SrvHost.class)  @note("  ") Long cscrHost;
	private SrvHost $cscrHost;//关联对象[城市]
	private @caption("网点") @column("cscr_outlets")    @relate("$cscrOutlets") @RelateClass(CsOutlets.class)  @note("  ") Long cscrOutlets;
	private CsOutlets $cscrOutlets;//关联对象[网点]
	private @caption("外部车辆ID") @column("cscr_car")      @note(" 如果没有外部id可空 ") String cscrCar;
	private @caption("外部车型ID") @column("cscr_carmodel")      @note("  ") String cscrCarmodel;
	private @caption("车牌名称") @column("cscr_car_no")      @note("  ") String cscrCarNo;
	private @caption("车辆颜色") @column("cscr_color")      @note("  ") String cscrColor;
	private @caption("停放位置") @column("cscr_location")      @note("  ") String cscrLocation;
	private @caption("数据来源") @column("cscr_source")      @note(" 1:公务出行 2:外部采集 ") Short cscrSource;
	private @caption("数据状态") @column("cscr_status")      @note(" 1:正常 0:禁用  ") Short cscrStatus;
	private @caption("创建时间") @column("cscr_add_time")      @note("  ") Date cscrAddTime;
	private @caption("修改时间") @column("cscr_update_time")      @note("  ") Date cscrUpdateTime;
	
	//默认构造函数
	public CsCarRes(){
	
	}
	
	//主键构造函数
	public CsCarRes(Long id){
		this.cscrId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsCarRes(Long cscrHost,Long cscrOutlets,String cscrCar,String cscrCarmodel,String cscrCarNo,String cscrColor,String cscrLocation,Short cscrSource,Short cscrStatus,Date cscrAddTime,Date cscrUpdateTime){
		this.cscrHost=cscrHost;
		this.cscrOutlets=cscrOutlets;
		this.cscrCar=cscrCar;
		this.cscrCarmodel=cscrCarmodel;
		this.cscrCarNo=cscrCarNo;
		this.cscrColor=cscrColor;
		this.cscrLocation=cscrLocation;
		this.cscrSource=cscrSource;
		this.cscrStatus=cscrStatus;
		this.cscrAddTime=cscrAddTime;
		this.cscrUpdateTime=cscrUpdateTime;
	}
	
	//设置非空字段
	public CsCarRes setNotNull(Long cscrHost,Long cscrOutlets,String cscrCarmodel,String cscrCarNo,String cscrColor,Short cscrStatus){
		this.cscrHost=cscrHost;
		this.cscrOutlets=cscrOutlets;
		this.cscrCarmodel=cscrCarmodel;
		this.cscrCarNo=cscrCarNo;
		this.cscrColor=cscrColor;
		this.cscrStatus=cscrStatus;
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsCarRes cscrId(Long cscrId){
		this.cscrId = cscrId;
		this.setSeted(F.cscrId);
		return this;
	}
	/** 城市 [可空]       **/
	public CsCarRes cscrHost(Long cscrHost){
		this.cscrHost = cscrHost;
		this.setSeted(F.cscrHost);
		return this;
	}
	/** 网点 [可空]       **/
	public CsCarRes cscrOutlets(Long cscrOutlets){
		this.cscrOutlets = cscrOutlets;
		this.setSeted(F.cscrOutlets);
		return this;
	}
	/** 外部车辆ID [非空]       **/
	public CsCarRes cscrCar(String cscrCar){
		this.cscrCar = cscrCar;
		this.setSeted(F.cscrCar);
		return this;
	}
	/** 外部车型ID [可空]       **/
	public CsCarRes cscrCarmodel(String cscrCarmodel){
		this.cscrCarmodel = cscrCarmodel;
		this.setSeted(F.cscrCarmodel);
		return this;
	}
	/** 车牌名称 [可空]       **/
	public CsCarRes cscrCarNo(String cscrCarNo){
		this.cscrCarNo = cscrCarNo;
		this.setSeted(F.cscrCarNo);
		return this;
	}
	/** 车辆颜色 [可空]       **/
	public CsCarRes cscrColor(String cscrColor){
		this.cscrColor = cscrColor;
		this.setSeted(F.cscrColor);
		return this;
	}
	/** 停放位置 [非空]       **/
	public CsCarRes cscrLocation(String cscrLocation){
		this.cscrLocation = cscrLocation;
		this.setSeted(F.cscrLocation);
		return this;
	}
	/** 数据来源 [非空]       **/
	public CsCarRes cscrSource(Short cscrSource){
		this.cscrSource = cscrSource;
		this.setSeted(F.cscrSource);
		return this;
	}
	/** 数据状态 [可空]       **/
	public CsCarRes cscrStatus(Short cscrStatus){
		this.cscrStatus = cscrStatus;
		this.setSeted(F.cscrStatus);
		return this;
	}
	/** 创建时间 [非空]       **/
	public CsCarRes cscrAddTime(Date cscrAddTime){
		this.cscrAddTime = cscrAddTime;
		this.setSeted(F.cscrAddTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsCarRes cscrUpdateTime(Date cscrUpdateTime){
		this.cscrUpdateTime = cscrUpdateTime;
		this.setSeted(F.cscrUpdateTime);
		return this;
	}
	
	//克隆对象
	public CsCarRes clone(){
		CsCarRes clone = new CsCarRes();
		clone.cscrId=this.cscrId;
		clone.cscrHost=this.cscrHost;
		clone.cscrOutlets=this.cscrOutlets;
		clone.cscrCar=this.cscrCar;
		clone.cscrCarmodel=this.cscrCarmodel;
		clone.cscrCarNo=this.cscrCarNo;
		clone.cscrColor=this.cscrColor;
		clone.cscrLocation=this.cscrLocation;
		clone.cscrSource=this.cscrSource;
		clone.cscrStatus=this.cscrStatus;
		clone.cscrAddTime=this.cscrAddTime;
		clone.cscrUpdateTime=this.cscrUpdateTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取专车资源
	 * @param id
	 * @return
	 */
	public static @api CsCarRes get(Long id){		
		return getCsCarResById(id);
	}
	/**
	 * 获取所有专车资源
	 * @return
	 */
	public static @api List<CsCarRes> list(Map params,Integer size){
		return getCsCarResList(params,size);
	}
	/**
	 * 获取专车资源分页
	 * @return
	 */
	public static @api Page<CsCarRes> page(int page,int size,Map params){
		return getCsCarResPage(page, size , params);
	}
	/**
	 * 根据查询条件取专车资源
	 * @param params
	 * @return
	 */
	public static @api CsCarRes Get(Map params){
		return getCsCarRes(params);
	}
	/**
	 * 获取专车资源数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsCarResCount(params);
	}
	/**
	 * 获取专车资源数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsCarResEval(eval,params);
	}
	
	/**
	 * 根据ID取专车资源
	 * @param id
	 * @return
	 */
	public static @api CsCarRes getCsCarResById(Long id){		
		CsCarRes csCarRes = (CsCarRes) $.GetRequest("CsCarRes$"+id);
		if(csCarRes!=null)
			return csCarRes;
		com.ccclubs.service.admin.ICsCarResService csCarResService = $.GetSpringBean("csCarResService");		
		return csCarResService.getCsCarResById(id);
	}
	
	
	/**
	 * 根据ID取专车资源的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsCarRes.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsCarRes csCarRes = get(id);
		if(csCarRes!=null){
			String strValue = csCarRes.getCscrId$();
			if(!"CscrId".equals("CscrId"))
				strValue+="("+csCarRes.getCscrId$()+")";
			MemCache.setValue(CsCarRes.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCscrId$();
		if(!"CscrId".equals("CscrId"))
			keyValue+="("+this.getCscrId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有专车资源
	 * @return
	 */
	public static @api List<CsCarRes> getCsCarResList(Map params,Integer size){
		com.ccclubs.service.admin.ICsCarResService csCarResService = $.GetSpringBean("csCarResService");
		return csCarResService.getCsCarResList(params, size);
	}
	
	/**
	 * 获取专车资源分页
	 * @return
	 */
	public static @api Page<CsCarRes> getCsCarResPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsCarResService csCarResService = $.GetSpringBean("csCarResService");
		return csCarResService.getCsCarResPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取专车资源
	 * @param params
	 * @return
	 */
	public static @api CsCarRes getCsCarRes(Map params){
		com.ccclubs.service.admin.ICsCarResService csCarResService = $.GetSpringBean("csCarResService");
		return csCarResService.getCsCarRes(params);
	}
	
	/**
	 * 获取专车资源数
	 * @return
	 */
	public static @api Long getCsCarResCount(Map params){
		com.ccclubs.service.admin.ICsCarResService csCarResService = $.GetSpringBean("csCarResService");
		return csCarResService.getCsCarResCount(params);
	}
		
		
	/**
	 * 获取专车资源自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsCarResEval(String eval,Map params){
		com.ccclubs.service.admin.ICsCarResService csCarResService = $.GetSpringBean("csCarResService");
		return csCarResService.getCsCarResEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsCarRes(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsCarResService csCarResService = $.GetSpringBean("csCarResService");
		csCarResService.updateCsCarResByConfirm(set, where);
	}
	
	
	/**
	 * 保存专车资源对象
	 * @param params
	 * @return
	 */
	public CsCarRes save(){
		com.ccclubs.service.admin.ICsCarResService csCarResService = $.GetSpringBean("csCarResService");
		if(this.getCscrId()!=null)
			csCarResService.updateCsCarRes(this);
		else
			return csCarResService.saveCsCarRes(this);
		return this;
	}
	
	
	/**
	 * 更新专车资源对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsCarResService csCarResService = $.GetSpringBean("csCarResService");
		csCarResService.updateCsCarRes$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsCarResService csCarResService = $.GetSpringBean("csCarResService");
		if($.equals($.config("logic_delete"),"true"))
			csCarResService.removeCsCarResById(this.getCscrId());
		else
			csCarResService.deleteCsCarResById(this.getCscrId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsCarResService csCarResService = $.GetSpringBean("csCarResService");
		return csCarResService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCscrId(){
		return this.cscrId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCscrId$(){
		String strValue="";
		 strValue=$.str(this.getCscrId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCscrId(Long cscrId){
		this.cscrId = cscrId;
		this.setSeted(F.cscrId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [可空]      
	**/
	public Long getCscrHost(){
		return this.cscrHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCscrHost$(){
		String strValue="";
		if(this.getCscrHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCscrHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [可空]     
	**/
	public void setCscrHost(Long cscrHost){
		this.cscrHost = cscrHost;
		this.setSeted(F.cscrHost);
	}
	/**
	* 获取关联对象[城市]
	**/	 			
 	public SrvHost get$cscrHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCscrHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCscrHost()!=null){
			srvHost = SrvHost.get(this.getCscrHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCscrHost(), srvHost);
	 	return srvHost;
	}
	/*******************************网点**********************************/	
	/**
	* 网点 [可空]      
	**/
	public Long getCscrOutlets(){
		return this.cscrOutlets;
	}
	/**
	* 获取网点格式化(toString)
	**/
	public String getCscrOutlets$(){
		String strValue="";
		if(this.getCscrOutlets()!=null){
				strValue+=$.str(CsOutlets.getKeyValue(this.getCscrOutlets()));
		}			
	 	 return strValue;
	}
	/**
	* 网点 [可空]     
	**/
	public void setCscrOutlets(Long cscrOutlets){
		this.cscrOutlets = cscrOutlets;
		this.setSeted(F.cscrOutlets);
	}
	/**
	* 获取关联对象[网点]
	**/	 			
 	public CsOutlets get$cscrOutlets(){
 		com.ccclubs.model.CsOutlets csOutlets = (com.ccclubs.model.CsOutlets) $.GetRequest("CsOutlets$"+this.getCscrOutlets());
 		if(csOutlets!=null)
			return csOutlets;
		if(this.getCscrOutlets()!=null){
			csOutlets = CsOutlets.get(this.getCscrOutlets());
 		}
 		$.SetRequest("CsOutlets$"+this.getCscrOutlets(), csOutlets);
	 	return csOutlets;
	}
	/*******************************外部车辆ID**********************************/	
	/**
	* 外部车辆ID [非空]      
	**/
	public String getCscrCar(){
		return this.cscrCar;
	}
	/**
	* 获取外部车辆ID格式化(toString)
	**/
	public String getCscrCar$(){
		String strValue="";
		 strValue=$.str(this.getCscrCar());
	 	 return strValue;
	}
	/**
	* 外部车辆ID [非空]     
	**/
	public void setCscrCar(String cscrCar){
		this.cscrCar = cscrCar;
		this.setSeted(F.cscrCar);
	}
	/*******************************外部车型ID**********************************/	
	/**
	* 外部车型ID [可空]      
	**/
	public String getCscrCarmodel(){
		return this.cscrCarmodel;
	}
	/**
	* 获取外部车型ID格式化(toString)
	**/
	public String getCscrCarmodel$(){
		String strValue="";
		 strValue=$.str(CsCarModel.getKeyValue(new Long(this.getCscrCarmodel())));
	 	 return strValue;
	}
	/**
	* 外部车型ID [可空]     
	**/
	public void setCscrCarmodel(String cscrCarmodel){
		this.cscrCarmodel = cscrCarmodel;
		this.setSeted(F.cscrCarmodel);
	}
	/*******************************车牌名称**********************************/	
	/**
	* 车牌名称 [可空]      
	**/
	public String getCscrCarNo(){
		return this.cscrCarNo;
	}
	/**
	* 获取车牌名称格式化(toString)
	**/
	public String getCscrCarNo$(){
		String strValue="";
		 strValue=$.str(this.getCscrCarNo());
	 	 return strValue;
	}
	/**
	* 车牌名称 [可空]     
	**/
	public void setCscrCarNo(String cscrCarNo){
		this.cscrCarNo = cscrCarNo;
		this.setSeted(F.cscrCarNo);
	}
	/*******************************车辆颜色**********************************/	
	/**
	* 车辆颜色 [可空]      
	**/
	public String getCscrColor(){
		return this.cscrColor;
	}
	/**
	* 获取车辆颜色格式化(toString)
	**/
	public String getCscrColor$(){
		String strValue="";
		 strValue=$.str(this.getCscrColor());
	 	 return strValue;
	}
	/**
	* 车辆颜色 [可空]     
	**/
	public void setCscrColor(String cscrColor){
		this.cscrColor = cscrColor;
		this.setSeted(F.cscrColor);
	}
	/*******************************停放位置**********************************/	
	/**
	* 停放位置 [非空]      
	**/
	public String getCscrLocation(){
		return this.cscrLocation;
	}
	/**
	* 获取停放位置格式化(toString)
	**/
	public String getCscrLocation$(){
		String strValue="";
		 strValue=$.str(this.getCscrLocation());
	 	 return strValue;
	}
	/**
	* 停放位置 [非空]     
	**/
	public void setCscrLocation(String cscrLocation){
		this.cscrLocation = cscrLocation;
		this.setSeted(F.cscrLocation);
	}
	/*******************************数据来源**********************************/	
	/**
	* 数据来源 [非空]      
	**/
	public Short getCscrSource(){
		return this.cscrSource;
	}
	/**
	* 获取数据来源格式化(toString)
	**/
	public String getCscrSource$(){
		String strValue="";
		 strValue=$.str(this.getCscrSource());
	 	 return strValue;
	}
	/**
	* 数据来源 [非空]     
	**/
	public void setCscrSource(Short cscrSource){
		this.cscrSource = cscrSource;
		this.setSeted(F.cscrSource);
	}
	/*******************************数据状态**********************************/	
	/**
	* 数据状态 [可空]      
	**/
	public Short getCscrStatus(){
		return this.cscrStatus;
	}
	/**
	* 获取数据状态格式化(toString)
	**/
	public String getCscrStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCscrStatus()),"1"))
			 strValue=$.str("正常");
		 if($.equals($.str(this.getCscrStatus()),"0"))
			 strValue=$.str("禁用");
	 	 return strValue;
	}
	/**
	* 数据状态 [可空]     
	**/
	public void setCscrStatus(Short cscrStatus){
		this.cscrStatus = cscrStatus;
		this.setSeted(F.cscrStatus);
	}
	/*******************************创建时间**********************************/	
	/**
	* 创建时间 [非空]      
	**/
	public Date getCscrAddTime(){
		return this.cscrAddTime;
	}
	/**
	* 获取创建时间格式化(toString)
	**/
	public String getCscrAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscrAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 创建时间 [非空]     
	**/
	public void setCscrAddTime(Date cscrAddTime){
		this.cscrAddTime = cscrAddTime;
		this.setSeted(F.cscrAddTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCscrUpdateTime(){
		return this.cscrUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCscrUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscrUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCscrUpdateTime(Date cscrUpdateTime){
		this.cscrUpdateTime = cscrUpdateTime;
		this.setSeted(F.cscrUpdateTime);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsCarRes.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCarRes.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCarRes.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsCarRes.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsCarRes.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCarRes.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCarRes.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsCarRes.this);
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
		public M cscrId(Object cscrId){this.put("cscrId", cscrId);return this;};
		/** and cscr_id is null */
		public M cscrIdNull(){if(this.get("cscrIdNot")==null)this.put("cscrIdNot", "");this.put("cscrId", null);return this;};
		/** not .... */
		public M cscrIdNot(){this.put("cscrIdNot", "not");return this;};
		/** 城市 [可空]       **/
		public M cscrHost(Object cscrHost){this.put("cscrHost", cscrHost);return this;};
		/** and cscr_host is null */
		public M cscrHostNull(){if(this.get("cscrHostNot")==null)this.put("cscrHostNot", "");this.put("cscrHost", null);return this;};
		/** not .... */
		public M cscrHostNot(){this.put("cscrHostNot", "not");return this;};
		/** 网点 [可空]       **/
		public M cscrOutlets(Object cscrOutlets){this.put("cscrOutlets", cscrOutlets);return this;};
		/** and cscr_outlets is null */
		public M cscrOutletsNull(){if(this.get("cscrOutletsNot")==null)this.put("cscrOutletsNot", "");this.put("cscrOutlets", null);return this;};
		/** not .... */
		public M cscrOutletsNot(){this.put("cscrOutletsNot", "not");return this;};
		public M cscrOutlets$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("cscrOutlets$on", value);
			return this;
		};	
		/** 外部车辆ID [非空]       **/
		public M cscrCar(Object cscrCar){this.put("cscrCar", cscrCar);return this;};
		/** and cscr_car is null */
		public M cscrCarNull(){if(this.get("cscrCarNot")==null)this.put("cscrCarNot", "");this.put("cscrCar", null);return this;};
		/** not .... */
		public M cscrCarNot(){this.put("cscrCarNot", "not");return this;};
		/** 外部车型ID [可空]       **/
		public M cscrCarmodel(Object cscrCarmodel){this.put("cscrCarmodel", cscrCarmodel);return this;};
		/** and cscr_carmodel is null */
		public M cscrCarmodelNull(){if(this.get("cscrCarmodelNot")==null)this.put("cscrCarmodelNot", "");this.put("cscrCarmodel", null);return this;};
		/** not .... */
		public M cscrCarmodelNot(){this.put("cscrCarmodelNot", "not");return this;};
		/** 车牌名称 [可空]       **/
		public M cscrCarNo(Object cscrCarNo){this.put("cscrCarNo", cscrCarNo);return this;};
		/** and cscr_car_no is null */
		public M cscrCarNoNull(){if(this.get("cscrCarNoNot")==null)this.put("cscrCarNoNot", "");this.put("cscrCarNo", null);return this;};
		/** not .... */
		public M cscrCarNoNot(){this.put("cscrCarNoNot", "not");return this;};
		/** 车辆颜色 [可空]       **/
		public M cscrColor(Object cscrColor){this.put("cscrColor", cscrColor);return this;};
		/** and cscr_color is null */
		public M cscrColorNull(){if(this.get("cscrColorNot")==null)this.put("cscrColorNot", "");this.put("cscrColor", null);return this;};
		/** not .... */
		public M cscrColorNot(){this.put("cscrColorNot", "not");return this;};
		/** 停放位置 [非空]       **/
		public M cscrLocation(Object cscrLocation){this.put("cscrLocation", cscrLocation);return this;};
		/** and cscr_location is null */
		public M cscrLocationNull(){if(this.get("cscrLocationNot")==null)this.put("cscrLocationNot", "");this.put("cscrLocation", null);return this;};
		/** not .... */
		public M cscrLocationNot(){this.put("cscrLocationNot", "not");return this;};
		/** 数据来源 [非空]       **/
		public M cscrSource(Object cscrSource){this.put("cscrSource", cscrSource);return this;};
		/** and cscr_source is null */
		public M cscrSourceNull(){if(this.get("cscrSourceNot")==null)this.put("cscrSourceNot", "");this.put("cscrSource", null);return this;};
		/** not .... */
		public M cscrSourceNot(){this.put("cscrSourceNot", "not");return this;};
		/** 数据状态 [可空]       **/
		public M cscrStatus(Object cscrStatus){this.put("cscrStatus", cscrStatus);return this;};
		/** and cscr_status is null */
		public M cscrStatusNull(){if(this.get("cscrStatusNot")==null)this.put("cscrStatusNot", "");this.put("cscrStatus", null);return this;};
		/** not .... */
		public M cscrStatusNot(){this.put("cscrStatusNot", "not");return this;};
		/** 创建时间 [非空]       **/
		public M cscrAddTime(Object cscrAddTime){this.put("cscrAddTime", cscrAddTime);return this;};
		/** and cscr_add_time is null */
		public M cscrAddTimeNull(){if(this.get("cscrAddTimeNot")==null)this.put("cscrAddTimeNot", "");this.put("cscrAddTime", null);return this;};
		/** not .... */
		public M cscrAddTimeNot(){this.put("cscrAddTimeNot", "not");return this;};
		/** and cscr_add_time >= ? */
 		public M cscrAddTimeStart(Object start){this.put("cscrAddTimeStart", start);return this;};			
 		/** and cscr_add_time <= ? */
 		public M cscrAddTimeEnd(Object end){this.put("cscrAddTimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M cscrUpdateTime(Object cscrUpdateTime){this.put("cscrUpdateTime", cscrUpdateTime);return this;};
		/** and cscr_update_time is null */
		public M cscrUpdateTimeNull(){if(this.get("cscrUpdateTimeNot")==null)this.put("cscrUpdateTimeNot", "");this.put("cscrUpdateTime", null);return this;};
		/** not .... */
		public M cscrUpdateTimeNot(){this.put("cscrUpdateTimeNot", "not");return this;};
		/** and cscr_update_time >= ? */
 		public M cscrUpdateTimeStart(Object start){this.put("cscrUpdateTimeStart", start);return this;};			
 		/** and cscr_update_time <= ? */
 		public M cscrUpdateTimeEnd(Object end){this.put("cscrUpdateTimeEnd", end);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有专车资源 **/
		public @api List<CsCarRes> list(Integer size){
			return getCsCarResList(this,size);
		}
		/** 获取专车资源分页 **/
		public @api Page<CsCarRes> page(int page,int size){
			return getCsCarResPage(page, size , this);
		}
		/** 根据查询条件取专车资源 **/
		public @api CsCarRes get(){
			return getCsCarRes(this);
		}
		/** 获取专车资源数 **/
		public @api Long count(){
			return getCsCarResCount(this);
		}
		/** 获取专车资源表达式 **/
		public @api <T> T eval(String strEval){
			return getCsCarResEval(strEval,this);
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
			updateCsCarRes(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscrId="cscrId";
		/** 城市 [可空]       **/
		public final static @type(Long.class)  String cscrHost="cscrHost";
		/** 网点 [可空]       **/
		public final static @type(Long.class)  String cscrOutlets="cscrOutlets";
		/** 外部车辆ID [非空]       **/
		public final static @type(String.class)  String cscrCar="cscrCar";
		/** 外部车型ID [可空]       **/
		public final static @type(String.class)  String cscrCarmodel="cscrCarmodel";
		/** 车牌名称 [可空]       **/
		public final static @type(String.class)  String cscrCarNo="cscrCarNo";
		/** 车辆颜色 [可空]       **/
		public final static @type(String.class)  String cscrColor="cscrColor";
		/** 停放位置 [非空]       **/
		public final static @type(String.class)  String cscrLocation="cscrLocation";
		/** 数据来源 [非空]       **/
		public final static @type(Short.class)  String cscrSource="cscrSource";
		/** 数据状态 [可空]       **/
		public final static @type(Short.class)  String cscrStatus="cscrStatus";
		/** 创建时间 [非空]       **/
		public final static @type(Date.class)  String cscrAddTime="cscrAddTime";
		/** and cscr_add_time >= ? */
 		public final static @type(Date.class) String cscrAddTimeStart="cscrAddTimeStart";
 		/** and cscr_add_time <= ? */
 		public final static @type(Date.class) String cscrAddTimeEnd="cscrAddTimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cscrUpdateTime="cscrUpdateTime";
		/** and cscr_update_time >= ? */
 		public final static @type(Date.class) String cscrUpdateTimeStart="cscrUpdateTimeStart";
 		/** and cscr_update_time <= ? */
 		public final static @type(Date.class) String cscrUpdateTimeEnd="cscrUpdateTimeEnd";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscrId="cscr_id";
		/** 城市 [可空]       **/
		public final static String cscrHost="cscr_host";
		/** 网点 [可空]       **/
		public final static String cscrOutlets="cscr_outlets";
		/** 外部车辆ID [非空]       **/
		public final static String cscrCar="cscr_car";
		/** 外部车型ID [可空]       **/
		public final static String cscrCarmodel="cscr_carmodel";
		/** 车牌名称 [可空]       **/
		public final static String cscrCarNo="cscr_car_no";
		/** 车辆颜色 [可空]       **/
		public final static String cscrColor="cscr_color";
		/** 停放位置 [非空]       **/
		public final static String cscrLocation="cscr_location";
		/** 数据来源 [非空]       **/
		public final static String cscrSource="cscr_source";
		/** 数据状态 [可空]       **/
		public final static String cscrStatus="cscr_status";
		/** 创建时间 [非空]       **/
		public final static String cscrAddTime="cscr_add_time";
		/** 修改时间 [非空]       **/
		public final static String cscrUpdateTime="cscr_update_time";
		
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
				$.Set(name,CsCarRes.getCsCarRes(params));
			else
				$.Set(name,CsCarRes.getCsCarResList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取专车资源数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsCarRes) $.GetRequest("CsCarRes$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsCarRes.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsCarRes.getCsCarRes(params);
			else
				value = CsCarRes.getCsCarResList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsCarRes.Get($.add(CsCarRes.F.cscrId,param));
		else if(!$.empty(param.toString()))
			value = CsCarRes.get(Long.valueOf(param.toString()));
		$.SetRequest("CsCarRes$"+param.hashCode(), value);
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
	public void mergeSet(CsCarRes old){
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