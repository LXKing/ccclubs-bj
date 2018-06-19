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

@namespace("service/scheduling")
public @caption("车辆调度") @table("cs_scheduling") class CsScheduling implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("css_id")   @primary  @note("  ") Long cssId;// 主键 非空     
	private @caption("城市") @column("css_host")    @relate("$cssHost") @RelateClass(SrvHost.class)  @note("  ") Long cssHost;// 非空     
 	private SrvHost $cssHost;//关联对象[运营城市]
	private @caption("网点") @column("css_outlets")    @relate("$cssOutlets") @RelateClass(CsOutlets.class)  @note("  ") Long cssOutlets;// 非空     
 	private CsOutlets $cssOutlets;//关联对象[网点]
	private @caption("车辆") @column("css_car")    @relate("$cssCar") @RelateClass(CsCar.class)  @note("  ") Long cssCar;// 非空     
 	private CsCar $cssCar;//关联对象[车辆]
	private @caption("时间") @column("css_time")    @note("  ") Date cssTime;// 非空     
	private @caption("类型") @column("css_type")    @note(" 0:车辆投放 1:定单调度 2:干预调度  ") Short cssType;// 非空 0:车辆投放 1:定单调度 2:干预调度     
	private @caption("方向") @column("css_dir")    @note(" 0:调入 1:调出  ") Short cssDir;// 非空 0:调入 1:调出     
	private @caption("说明") @column("css_explain")    @note("  ") String cssExplain;// 非空     
	private @caption("订单") @column("css_order")    @relate("$cssOrder") @RelateClass(CsOrder.class)  @note("  ") Long cssOrder;//     
 	private CsOrder $cssOrder;//关联对象[系统订单]
	private @caption("操作人") @column("css_operator")    @note("  ") String cssOperator;//    SrvUser,CsMember 存储格式:类名@ID,其中类名包括SrvUser或CsMember
	private Object $cssOperator;//泛关联对象，对象类名由cssOperator字段中标明
	private @caption("状态") @column("css_status")    @note(" 0:待调 1:已调 2:取消  ") Short cssStatus;// 非空 0:待调 1:已调 2:取消     
	
	//默认构造函数
	public CsScheduling(){
	
	}
	
	//主键构造函数
	public CsScheduling(Long id){
		this.cssId = id;
	}
	
	/**所有字段构造函数 CsScheduling(cssHost,cssOutlets,cssCar,cssTime,cssType,cssDir,cssExplain,cssOrder,cssOperator,cssStatus)
	 CsScheduling(
	 	$.getLong("cssHost")//城市 [非空]
	 	,$.getLong("cssOutlets")//网点 [非空]
	 	,$.getLong("cssCar")//车辆 [非空]
	 	,$.getDate("cssTime")//时间 [非空]
	 	,$.getShort("cssType")//类型 [非空]
	 	,$.getShort("cssDir")//方向 [非空]
	 	,$.getString("cssExplain")//说明 [非空]
	 	,$.getLong("cssOrder")//订单
	 	,$.getString("cssOperator")//操作人
	 	,$.getShort("cssStatus")//状态 [非空]
	 )
	**/
	public CsScheduling(Long cssHost,Long cssOutlets,Long cssCar,Date cssTime,Short cssType,Short cssDir,String cssExplain,Long cssOrder,String cssOperator,Short cssStatus){
		this.cssHost=cssHost;
		this.cssOutlets=cssOutlets;
		this.cssCar=cssCar;
		this.cssTime=cssTime;
		this.cssType=cssType;
		this.cssDir=cssDir;
		this.cssExplain=cssExplain;
		this.cssOrder=cssOrder;
		this.cssOperator=cssOperator;
		this.cssStatus=cssStatus;
	}
	
	//设置非空字段
	public CsScheduling setNotNull(Long cssHost,Long cssOutlets,Long cssCar,Date cssTime,Short cssType,Short cssDir,String cssExplain,Short cssStatus){
		this.cssHost=cssHost;
		this.cssOutlets=cssOutlets;
		this.cssCar=cssCar;
		this.cssTime=cssTime;
		this.cssType=cssType;
		this.cssDir=cssDir;
		this.cssExplain=cssExplain;
		this.cssStatus=cssStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsScheduling cssId(Long cssId){
		this.cssId = cssId;
		this.setSeted(F.cssId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsScheduling cssHost(Long cssHost){
		this.cssHost = cssHost;
		this.setSeted(F.cssHost);
		return this;
	}
	/** 网点 [非空] [CsOutlets]      **/
	public CsScheduling cssOutlets(Long cssOutlets){
		this.cssOutlets = cssOutlets;
		this.setSeted(F.cssOutlets);
		return this;
	}
	/** 车辆 [非空] [CsCar]      **/
	public CsScheduling cssCar(Long cssCar){
		this.cssCar = cssCar;
		this.setSeted(F.cssCar);
		return this;
	}
	/** 时间 [非空]       **/
	public CsScheduling cssTime(Date cssTime){
		this.cssTime = cssTime;
		this.setSeted(F.cssTime);
		return this;
	}
	/** 类型 [非空]   0:车辆投放 1:定单调度 2:干预调度     **/
	public CsScheduling cssType(Short cssType){
		this.cssType = cssType;
		this.setSeted(F.cssType);
		return this;
	}
	/** 方向 [非空]   0:调入 1:调出     **/
	public CsScheduling cssDir(Short cssDir){
		this.cssDir = cssDir;
		this.setSeted(F.cssDir);
		return this;
	}
	/** 说明 [非空]       **/
	public CsScheduling cssExplain(String cssExplain){
		this.cssExplain = cssExplain;
		this.setSeted(F.cssExplain);
		return this;
	}
	/** 订单  [CsOrder]      **/
	public CsScheduling cssOrder(Long cssOrder){
		this.cssOrder = cssOrder;
		this.setSeted(F.cssOrder);
		return this;
	}
	/** 操作人       SrvUser,CsMember **/
	public CsScheduling cssOperator(String cssOperator){
		this.cssOperator = cssOperator;
		this.setSeted(F.cssOperator);
		return this;
	}
	/** 状态 [非空]   0:待调 1:已调 2:取消     **/
	public CsScheduling cssStatus(Short cssStatus){
		this.cssStatus = cssStatus;
		this.setSeted(F.cssStatus);
		return this;
	}
	
	
	//克隆对象
	public CsScheduling clone(){
		CsScheduling clone = new CsScheduling();
		clone.cssHost=this.cssHost;
		clone.cssOutlets=this.cssOutlets;
		clone.cssCar=this.cssCar;
		clone.cssTime=this.cssTime;
		clone.cssType=this.cssType;
		clone.cssDir=this.cssDir;
		clone.cssExplain=this.cssExplain;
		clone.cssStatus=this.cssStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取车辆调度
	 * @param id
	 * @return
	 */
	public static @api CsScheduling get(Long id){		
		return getCsSchedulingById(id);
	}
	/**
	 * 获取所有车辆调度
	 * @return
	 */
	public static @api List<CsScheduling> list(Map params,Integer size){
		return getCsSchedulingList(params,size);
	}
	/**
	 * 获取车辆调度分页
	 * @return
	 */
	public static @api Page<CsScheduling> page(int page,int size,Map params){
		return getCsSchedulingPage(page, size , params);
	}
	/**
	 * 根据查询条件取车辆调度
	 * @param params
	 * @return
	 */
	public static @api CsScheduling Get(Map params){
		return getCsScheduling(params);
	}
	/**
	 * 获取车辆调度数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsSchedulingCount(params);
	}
	/**
	 * 获取车辆调度数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsSchedulingEval(eval,params);
	}
	
	/**
	 * 根据ID取车辆调度
	 * @param id
	 * @return
	 */
	public static @api CsScheduling getCsSchedulingById(Long id){		
		CsScheduling csScheduling = (CsScheduling) $.GetRequest("CsScheduling$"+id);
		if(csScheduling!=null)
			return csScheduling;
		com.ccclubs.service.admin.ICsSchedulingService csSchedulingService = $.GetSpringBean("csSchedulingService");		
		return csSchedulingService.getCsSchedulingById(id);
	}
	
	
	/**
	 * 根据ID取车辆调度的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsScheduling.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsScheduling csScheduling = get(id);
		if(csScheduling!=null){
			String strValue = csScheduling.getCssId$();
			if(!"CssId".equals("CssId"))
				strValue+="("+csScheduling.getCssId$()+")";
			MemCache.setValue(CsScheduling.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCssId$();
		if(!"CssId".equals("CssId"))
			keyValue+="("+this.getCssId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有车辆调度
	 * @return
	 */
	public static @api List<CsScheduling> getCsSchedulingList(Map params,Integer size){
		com.ccclubs.service.admin.ICsSchedulingService csSchedulingService = $.GetSpringBean("csSchedulingService");
		return csSchedulingService.getCsSchedulingList(params, size);
	}
	
	/**
	 * 获取车辆调度分页
	 * @return
	 */
	public static @api Page<CsScheduling> getCsSchedulingPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsSchedulingService csSchedulingService = $.GetSpringBean("csSchedulingService");
		return csSchedulingService.getCsSchedulingPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取车辆调度
	 * @param params
	 * @return
	 */
	public static @api CsScheduling getCsScheduling(Map params){
		com.ccclubs.service.admin.ICsSchedulingService csSchedulingService = $.GetSpringBean("csSchedulingService");
		return csSchedulingService.getCsScheduling(params);
	}
	
	/**
	 * 获取车辆调度数
	 * @return
	 */
	public static @api Long getCsSchedulingCount(Map params){
		com.ccclubs.service.admin.ICsSchedulingService csSchedulingService = $.GetSpringBean("csSchedulingService");
		return csSchedulingService.getCsSchedulingCount(params);
	}
		
		
	/**
	 * 获取车辆调度自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsSchedulingEval(String eval,Map params){
		com.ccclubs.service.admin.ICsSchedulingService csSchedulingService = $.GetSpringBean("csSchedulingService");
		return csSchedulingService.getCsSchedulingEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsScheduling(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsSchedulingService csSchedulingService = $.GetSpringBean("csSchedulingService");
		csSchedulingService.updateCsSchedulingByConfirm(set, where);
	}
	
	
	/**
	 * 保存车辆调度对象
	 * @param params
	 * @return
	 */
	public CsScheduling save(){
		com.ccclubs.service.admin.ICsSchedulingService csSchedulingService = $.GetSpringBean("csSchedulingService");
		if(this.getCssId()!=null)
			csSchedulingService.updateCsScheduling(this);
		else
			return csSchedulingService.saveCsScheduling(this);
		return this;
	}
	
	
	/**
	 * 更新车辆调度对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsSchedulingService csSchedulingService = $.GetSpringBean("csSchedulingService");
		csSchedulingService.updateCsScheduling$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsSchedulingService csSchedulingService = $.GetSpringBean("csSchedulingService");
		if($.equals($.config("logic_delete"),"true"))
			csSchedulingService.removeCsSchedulingById(this.getCssId());
		else
			csSchedulingService.deleteCsSchedulingById(this.getCssId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsSchedulingService csSchedulingService = $.GetSpringBean("csSchedulingService");
		return csSchedulingService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCssId(){
		return this.cssId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCssId$(){
		String strValue="";
		 strValue=$.str(this.getCssId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCssId(Long cssId){
		this.cssId = cssId;
		this.setSeted(F.cssId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCssHost(){
		return this.cssHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCssHost$(){
		String strValue="";
		if(this.getCssHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCssHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCssHost(Long cssHost){
		this.cssHost = cssHost;
		this.setSeted(F.cssHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cssHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCssHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCssHost()!=null){
 			srvHost = SrvHost.get(this.getCssHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCssHost(), srvHost);
	 	return srvHost;
	}
	/*******************************网点**********************************/	
	/**
	* 网点 [非空] [CsOutlets]     
	**/
	public Long getCssOutlets(){
		return this.cssOutlets;
	}
	/**
	* 获取网点格式化(toString)
	**/
	public String getCssOutlets$(){
		String strValue="";
		if(this.getCssOutlets()!=null){
				strValue+=$.str(CsOutlets.getKeyValue(this.getCssOutlets()));
		}			
	 	 return strValue;
	}
	/**
	* 网点 [非空] [CsOutlets]     
	**/
	public void setCssOutlets(Long cssOutlets){
		this.cssOutlets = cssOutlets;
		this.setSeted(F.cssOutlets);
	}
	/**
	* 获取关联对象[网点]
	**/	 			
 	public CsOutlets get$cssOutlets(){
 		com.ccclubs.model.CsOutlets csOutlets = (com.ccclubs.model.CsOutlets) $.GetRequest("CsOutlets$"+this.getCssOutlets());
 		if(csOutlets!=null)
			return csOutlets;
		if(this.getCssOutlets()!=null){
 			csOutlets = CsOutlets.get(this.getCssOutlets());
 		}
 		$.SetRequest("CsOutlets$"+this.getCssOutlets(), csOutlets);
	 	return csOutlets;
	}
	/*******************************车辆**********************************/	
	/**
	* 车辆 [非空] [CsCar]     
	**/
	public Long getCssCar(){
		return this.cssCar;
	}
	/**
	* 获取车辆格式化(toString)
	**/
	public String getCssCar$(){
		String strValue="";
		if(this.getCssCar()!=null){
				strValue+=$.str(CsCar.getKeyValue(this.getCssCar()));
		}			
	 	 return strValue;
	}
	/**
	* 车辆 [非空] [CsCar]     
	**/
	public void setCssCar(Long cssCar){
		this.cssCar = cssCar;
		this.setSeted(F.cssCar);
	}
	/**
	* 获取关联对象[车辆]
	**/	 			
 	public CsCar get$cssCar(){
 		com.ccclubs.model.CsCar csCar = (com.ccclubs.model.CsCar) $.GetRequest("CsCar$"+this.getCssCar());
 		if(csCar!=null)
			return csCar;
		if(this.getCssCar()!=null){
 			csCar = CsCar.get(this.getCssCar());
 		}
 		$.SetRequest("CsCar$"+this.getCssCar(), csCar);
	 	return csCar;
	}
	/*******************************时间**********************************/	
	/**
	* 时间 [非空]      
	**/
	public Date getCssTime(){
		return this.cssTime;
	}
	/**
	* 获取时间格式化(toString)
	**/
	public String getCssTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 时间 [非空]      
	**/
	public void setCssTime(Date cssTime){
		this.cssTime = cssTime;
		this.setSeted(F.cssTime);
	}
	/*******************************类型**********************************/	
	/**
	* 类型 [非空]   0:车辆投放 1:定单调度 2:干预调度    
	**/
	public Short getCssType(){
		return this.cssType;
	}
	/**
	* 获取类型格式化(toString)
	**/
	public String getCssType$(){
		String strValue="";
		 if($.equals($.str(this.getCssType()),"0"))
			strValue=$.str("车辆投放");		 
		 if($.equals($.str(this.getCssType()),"1"))
			strValue=$.str("定单调度");		 
		 if($.equals($.str(this.getCssType()),"2"))
			strValue=$.str("干预调度");		 
	 	 return strValue;
	}
	/**
	* 类型 [非空]   0:车辆投放 1:定单调度 2:干预调度    
	**/
	public void setCssType(Short cssType){
		this.cssType = cssType;
		this.setSeted(F.cssType);
	}
	/*******************************方向**********************************/	
	/**
	* 方向 [非空]   0:调入 1:调出    
	**/
	public Short getCssDir(){
		return this.cssDir;
	}
	/**
	* 获取方向格式化(toString)
	**/
	public String getCssDir$(){
		String strValue="";
		 if($.equals($.str(this.getCssDir()),"0"))
			strValue=$.str("调入");		 
		 if($.equals($.str(this.getCssDir()),"1"))
			strValue=$.str("调出");		 
	 	 return strValue;
	}
	/**
	* 方向 [非空]   0:调入 1:调出    
	**/
	public void setCssDir(Short cssDir){
		this.cssDir = cssDir;
		this.setSeted(F.cssDir);
	}
	/*******************************说明**********************************/	
	/**
	* 说明 [非空]      
	**/
	public String getCssExplain(){
		return this.cssExplain;
	}
	/**
	* 获取说明格式化(toString)
	**/
	public String getCssExplain$(){
		String strValue="";
		 strValue=$.str(this.getCssExplain());
	 	 return strValue;
	}
	/**
	* 说明 [非空]      
	**/
	public void setCssExplain(String cssExplain){
		this.cssExplain = cssExplain;
		this.setSeted(F.cssExplain);
	}
	/*******************************订单**********************************/	
	/**
	* 订单  [CsOrder]     
	**/
	public Long getCssOrder(){
		return this.cssOrder;
	}
	/**
	* 获取订单格式化(toString)
	**/
	public String getCssOrder$(){
		String strValue="";
		if(this.getCssOrder()!=null){
				strValue+=$.str(CsOrder.getKeyValue(this.getCssOrder()));
		}			
	 	 return strValue;
	}
	/**
	* 订单  [CsOrder]     
	**/
	public void setCssOrder(Long cssOrder){
		this.cssOrder = cssOrder;
		this.setSeted(F.cssOrder);
	}
	/**
	* 获取关联对象[系统订单]
	**/	 			
 	public CsOrder get$cssOrder(){
 		com.ccclubs.model.CsOrder csOrder = (com.ccclubs.model.CsOrder) $.GetRequest("CsOrder$"+this.getCssOrder());
 		if(csOrder!=null)
			return csOrder;
		if(this.getCssOrder()!=null){
 			csOrder = CsOrder.get(this.getCssOrder());
 		}
 		$.SetRequest("CsOrder$"+this.getCssOrder(), csOrder);
	 	return csOrder;
	}
	/*******************************操作人**********************************/	
	/**
	* 操作人       SrvUser,CsMember
	**/
	public String getCssOperator(){
		return this.cssOperator;
	}
	/**
	* 获取操作人格式化(toString)
	**/
	public String getCssOperator$(){
		String strValue="";
	 	 Object object = this.get$cssOperator();
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
	* 操作人       SrvUser,CsMember
	**/
	public void setCssOperator(String cssOperator){
		this.cssOperator = cssOperator;
		this.setSeted(F.cssOperator);
	}
	/**
	* 泛关联对象，对象类名由cssOperator字段中标明
	**/	 			
 	public <T> T get$cssOperator(){
		if($.empty(this.cssOperator))
 			return null;
 		try{
	 		String ClassName = this.cssOperator.split("@")[0];
	 		Long id = Long.parseLong(this.cssOperator.split("@")[1]);
	 		Class<?> cls = Class.forName("com.ccclubs.model."+ClassName);
	 		return (T)cls.getMethod("get", Long.class).invoke(null, id);	
 		}catch(Exception ex){
 			ex.printStackTrace();
 			return null;
 		}
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:待调 1:已调 2:取消    
	**/
	public Short getCssStatus(){
		return this.cssStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCssStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCssStatus()),"0"))
			strValue=$.str("待调");		 
		 if($.equals($.str(this.getCssStatus()),"1"))
			strValue=$.str("已调");		 
		 if($.equals($.str(this.getCssStatus()),"2"))
			strValue=$.str("取消");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:待调 1:已调 2:取消    
	**/
	public void setCssStatus(Short cssStatus){
		this.cssStatus = cssStatus;
		this.setSeted(F.cssStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsScheduling.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsScheduling.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsScheduling.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsScheduling.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsScheduling.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsScheduling.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsScheduling.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsScheduling.this);
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
		public M cssId(Object cssId){this.put("cssId", cssId);return this;};
	 	/** and css_id is null */
 		public M cssIdNull(){if(this.get("cssIdNot")==null)this.put("cssIdNot", "");this.put("cssId", null);return this;};
 		/** not .... */
 		public M cssIdNot(){this.put("cssIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M cssHost(Object cssHost){this.put("cssHost", cssHost);return this;};
	 	/** and css_host is null */
 		public M cssHostNull(){if(this.get("cssHostNot")==null)this.put("cssHostNot", "");this.put("cssHost", null);return this;};
 		/** not .... */
 		public M cssHostNot(){this.put("cssHostNot", "not");return this;};
		/** 网点 [非空] [CsOutlets]      **/
		public M cssOutlets(Object cssOutlets){this.put("cssOutlets", cssOutlets);return this;};
	 	/** and css_outlets is null */
 		public M cssOutletsNull(){if(this.get("cssOutletsNot")==null)this.put("cssOutletsNot", "");this.put("cssOutlets", null);return this;};
 		/** not .... */
 		public M cssOutletsNot(){this.put("cssOutletsNot", "not");return this;};
		public M cssOutlets$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("cssOutlets$on", value);
			return this;
		};	
		/** 车辆 [非空] [CsCar]      **/
		public M cssCar(Object cssCar){this.put("cssCar", cssCar);return this;};
	 	/** and css_car is null */
 		public M cssCarNull(){if(this.get("cssCarNot")==null)this.put("cssCarNot", "");this.put("cssCar", null);return this;};
 		/** not .... */
 		public M cssCarNot(){this.put("cssCarNot", "not");return this;};
		public M cssCar$on(CsCar.M value){
			this.put("CsCar", value);
			this.put("cssCar$on", value);
			return this;
		};	
		/** 时间 [非空]       **/
		public M cssTime(Object cssTime){this.put("cssTime", cssTime);return this;};
	 	/** and css_time is null */
 		public M cssTimeNull(){if(this.get("cssTimeNot")==null)this.put("cssTimeNot", "");this.put("cssTime", null);return this;};
 		/** not .... */
 		public M cssTimeNot(){this.put("cssTimeNot", "not");return this;};
 		/** and css_time >= ? */
 		public M cssTimeStart(Object start){this.put("cssTimeStart", start);return this;};			
 		/** and css_time <= ? */
 		public M cssTimeEnd(Object end){this.put("cssTimeEnd", end);return this;};
		/** 类型 [非空]   0:车辆投放 1:定单调度 2:干预调度     **/
		public M cssType(Object cssType){this.put("cssType", cssType);return this;};
	 	/** and css_type is null */
 		public M cssTypeNull(){if(this.get("cssTypeNot")==null)this.put("cssTypeNot", "");this.put("cssType", null);return this;};
 		/** not .... */
 		public M cssTypeNot(){this.put("cssTypeNot", "not");return this;};
		/** 方向 [非空]   0:调入 1:调出     **/
		public M cssDir(Object cssDir){this.put("cssDir", cssDir);return this;};
	 	/** and css_dir is null */
 		public M cssDirNull(){if(this.get("cssDirNot")==null)this.put("cssDirNot", "");this.put("cssDir", null);return this;};
 		/** not .... */
 		public M cssDirNot(){this.put("cssDirNot", "not");return this;};
		/** 说明 [非空]       **/
		public M cssExplain(Object cssExplain){this.put("cssExplain", cssExplain);return this;};
	 	/** and css_explain is null */
 		public M cssExplainNull(){if(this.get("cssExplainNot")==null)this.put("cssExplainNot", "");this.put("cssExplain", null);return this;};
 		/** not .... */
 		public M cssExplainNot(){this.put("cssExplainNot", "not");return this;};
		/** 订单  [CsOrder]      **/
		public M cssOrder(Object cssOrder){this.put("cssOrder", cssOrder);return this;};
	 	/** and css_order is null */
 		public M cssOrderNull(){if(this.get("cssOrderNot")==null)this.put("cssOrderNot", "");this.put("cssOrder", null);return this;};
 		/** not .... */
 		public M cssOrderNot(){this.put("cssOrderNot", "not");return this;};
		public M cssOrder$on(CsOrder.M value){
			this.put("CsOrder", value);
			this.put("cssOrder$on", value);
			return this;
		};	
		/** 操作人       SrvUser,CsMember **/
		public M cssOperator(Object cssOperator){this.put("cssOperator", cssOperator);return this;};
	 	/** and css_operator is null */
 		public M cssOperatorNull(){if(this.get("cssOperatorNot")==null)this.put("cssOperatorNot", "");this.put("cssOperator", null);return this;};
 		/** not .... */
 		public M cssOperatorNot(){this.put("cssOperatorNot", "not");return this;};
		/** 状态 [非空]   0:待调 1:已调 2:取消     **/
		public M cssStatus(Object cssStatus){this.put("cssStatus", cssStatus);return this;};
	 	/** and css_status is null */
 		public M cssStatusNull(){if(this.get("cssStatusNot")==null)this.put("cssStatusNot", "");this.put("cssStatus", null);return this;};
 		/** not .... */
 		public M cssStatusNot(){this.put("cssStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有车辆调度 **/
		public @api List<CsScheduling> list(Integer size){
			return getCsSchedulingList(this,size);
		}
		/** 获取车辆调度分页 **/
		public @api Page<CsScheduling> page(int page,int size){
			return getCsSchedulingPage(page, size , this);
		}
		/** 根据查询条件取车辆调度 **/
		public @api CsScheduling get(){
			return getCsScheduling(this);
		}
		/** 获取车辆调度数 **/
		public @api Long count(){
			return getCsSchedulingCount(this);
		}
		/** 获取车辆调度表达式 **/
		public @api <T> T eval(String strEval){
			return getCsSchedulingEval(strEval,this);
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
			updateCsScheduling(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cssId="cssId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cssHost="cssHost";
		/** 网点 [非空] [CsOutlets]      **/
		public final static @type(Long.class)  String cssOutlets="cssOutlets";
		/** 车辆 [非空] [CsCar]      **/
		public final static @type(Long.class)  String cssCar="cssCar";
		/** 时间 [非空]       **/
		public final static @type(Date.class)  String cssTime="cssTime";
	 	/** and css_time >= ? */
 		public final static @type(Date.class) String cssTimeStart="cssTimeStart";
 		/** and css_time <= ? */
 		public final static @type(Date.class) String cssTimeEnd="cssTimeEnd";
		/** 类型 [非空]   0:车辆投放 1:定单调度 2:干预调度     **/
		public final static @type(Short.class)  String cssType="cssType";
		/** 方向 [非空]   0:调入 1:调出     **/
		public final static @type(Short.class)  String cssDir="cssDir";
		/** 说明 [非空]       **/
		public final static @type(String.class) @like String cssExplain="cssExplain";
		/** 订单  [CsOrder]      **/
		public final static @type(Long.class)  String cssOrder="cssOrder";
		/** 操作人       SrvUser,CsMember **/
		public final static @type(String.class)  String cssOperator="cssOperator";
		/** 状态 [非空]   0:待调 1:已调 2:取消     **/
		public final static @type(Short.class)  String cssStatus="cssStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cssId="css_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cssHost="css_host";
		/** 网点 [非空] [CsOutlets]      **/
		public final static String cssOutlets="css_outlets";
		/** 车辆 [非空] [CsCar]      **/
		public final static String cssCar="css_car";
		/** 时间 [非空]       **/
		public final static String cssTime="css_time";
		/** 类型 [非空]   0:车辆投放 1:定单调度 2:干预调度     **/
		public final static String cssType="css_type";
		/** 方向 [非空]   0:调入 1:调出     **/
		public final static String cssDir="css_dir";
		/** 说明 [非空]       **/
		public final static String cssExplain="css_explain";
		/** 订单  [CsOrder]      **/
		public final static String cssOrder="css_order";
		/** 操作人       SrvUser,CsMember **/
		public final static String cssOperator="css_operator";
		/** 状态 [非空]   0:待调 1:已调 2:取消     **/
		public final static String cssStatus="css_status";
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
				$.Set(name,CsScheduling.getCsScheduling(params));
			else
				$.Set(name,CsScheduling.getCsSchedulingList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取车辆调度数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsScheduling) $.GetRequest("CsScheduling$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsScheduling.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsScheduling.getCsScheduling(params);
			else
				value = CsScheduling.getCsSchedulingList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsScheduling.Get($.add(CsScheduling.F.cssId,param));
		else if(!$.empty(param.toString()))
			value = CsScheduling.get(Long.valueOf(param.toString()));
		$.SetRequest("CsScheduling$"+param.hashCode(), value);
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
	public void mergeSet(CsScheduling old){
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