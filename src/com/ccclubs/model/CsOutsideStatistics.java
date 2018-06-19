package com.ccclubs.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.dao.impl.MemCache;
import com.ccclubs.model.CsGoods.C;
import com.ccclubs.model.CsGoods.F;
import com.ccclubs.model.CsGoods.M;
import com.lazy3q.lang.RelateClass;
import com.lazy3q.lang.api;
import com.lazy3q.lang.caption;
import com.lazy3q.lang.column;
import com.lazy3q.lang.like;
import com.lazy3q.lang.namespace;
import com.lazy3q.lang.note;
import com.lazy3q.lang.primary;
import com.lazy3q.lang.relate;
import com.lazy3q.lang.table;
import com.lazy3q.lang.type;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.tags.BodyTag;
import com.lazy3q.web.util.Page;
@namespace("oa/outside/outsideStatistics")
public @caption("外勤整备项统计") @table("cs_outside_statistics") class CsOutsideStatistics implements java.io.Serializable {

	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csos_id")   @primary  @note("  ") Long csosId;// 主键 非空     
	
	private @caption("员工") @column("csos_user")    @relate("$csosUser") @RelateClass(SrvUser.class)  @note("  ") Long csosUser;// 非空     
	private SrvUser $csosUser;//关联对象[产品]
	
	private @caption("车辆名称") @column("csos_car")    @relate("$csosCar") @RelateClass(CsCar.class)  @note("  ") Long csosCar;
	private CsCar $csosCar;//关联对象[产品]
	
	
	private @caption("所在区域") @column("csos_area")    @relate("$csosArea") @RelateClass(CsArea.class)  @note("  ") Long csosArea;
	private CsArea $csosArea;//关联对象[产品]
	
	
	private @caption("城市") @column("csos_host")    @relate("$csosHost") @RelateClass(SrvHost.class)  @note("  ") Long csosHost;// 非空     
	private SrvHost $csosHost;//关联对象[产品]
	
	
	private @caption("网点") @column("csos_outlets")    @relate("$csosOutlets") @RelateClass(CsOutlets.class)  @note("   ") Long csosOutlets;// 非空 {cscHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} 
	private CsOutlets $csosOutlets;//关联对象[产品]
	
	private @caption("整备项") @column("csos_property")    @relate("$csosProperty") @RelateClass(SrvProperty.class)  @note("   ") Long csosProperty;// 非空 {cscHost}:host    ${basePath}${proname}/object/outlets_tree.do?parent={param} 
	private SrvProperty $csosProperty;//关联对象[产品]
	
	
	private @caption("开门次数") @column("csos_open_door_count")    @note("  ") Integer  csosOpenDoorCount;// 非空   
	
	private @caption("关门次数") @column("csos_close_door_count")    @note("  ") Integer  csosCloseDoorCount;// 非空     
	
	private @caption("允许启动次数") @column("csos_start_count")    @note("  ") Integer  csosStartCount;// 非空     
	
	private @caption("鸣笛次数") @column("csos_whistle_count")    @note("  ") Integer  csosWhistleCount;// 非空   
	
	
	private @caption("开门时间") @column("csos_open_door_time")    @note("  ") Date  csosOpenDoorTime;// 非空     
	private @caption("关门时间") @column("csos_close_door_time")    @note("  ") Date  csosCloseDoorTime;// 非空     

	
	private @caption("添加时间") @column("csos_add_time")    @note("  ") Date  csosAddTime;// 非空     
	
	private @caption("更新新时间") @column("csos_update_time")    @note("  ") Date  csosUpdateTime;// 非空     

	private @caption("完成整备时间") @column("csos_finish_time")    @note("  ") Date  csosFinishTime;// 非空     

	 
	private @caption("状态") @column("csos_status")    @note("  ") Short  csosStatus;// 非空     

	
	private @caption("车辆位置") @column("csos_car_location")    @note("  ") String  csosCarLocation;// 非空     

	private @caption("人员位置") @column("csos_member_location")    @note("  ") String  csosMemberLocation;// 非空     

	private @caption("人车距离") @column("csos_distance")    @note("  ") Double  csosDistance;// 非空     

	private @caption("工作时长") @column("csos_work_time")    @note("  ") Long  csosWorkTime;// 非空     

	

	
	
	//默认构造函数
	public CsOutsideStatistics(){
	
	}
	
	//主键构造函数
	public CsOutsideStatistics(Long id){
		this.csosId = id;
	}
	
	
	/**所有字段构造函数
	**/
	public CsOutsideStatistics(Long csosUser,  Long csosCar,Long csosHost,Long csosOutlets, Long  csosProperty,Integer	csosOpenDoorCount,Integer  csosCloseDoorCount,
			Integer	csosStartCount,Integer	csosWhistleCount,Date csosOpenDoorTime,Date csosCloseDoorTime,Date csosAddTime,Date csosUpdateTime,
			Date	csosFinishTime,Short csosStatus ,String csosCarLocation,String csosMemberLocation,Long csosWorkTime,Long csosArea){
	
		this.csosUser=csosUser;
		this.csosCar=csosCar;
		this.csosHost=csosHost;
		this.csosOutlets=csosOutlets;
		this.csosProperty=csosProperty;
		this.csosOpenDoorCount=csosOpenDoorCount;
		this.csosCloseDoorCount=csosCloseDoorCount;
		this.csosStartCount=csosStartCount;
		this.csosWhistleCount=csosWhistleCount;
		this.csosOpenDoorTime=csosOpenDoorTime;
		this.csosCloseDoorTime=csosCloseDoorTime;
		this.csosAddTime=csosAddTime;
		this.csosUpdateTime=csosUpdateTime;
		this.csosFinishTime=csosFinishTime;
		this.csosStatus=csosStatus;
		this.csosCarLocation=csosCarLocation;
		this.csosMemberLocation=csosMemberLocation;
		this.csosWorkTime=csosWorkTime;
		this.csosArea=csosArea;
		
	}
	
	//设置非空字段
	public CsOutsideStatistics setNotNull(Long csosUser,Long csosCar,Long csosHost,Long csosProperty){
		this.csosUser=csosUser;
		this.csosCar=csosCar;
		this.csosHost=csosHost;
		this.csosProperty=csosProperty;
		return this;
	}
	/******************************************************************************/
	
	/** 编号 [非空]       **/
	public CsOutsideStatistics csosId(Long csosId){
		this.csosId = csosId;
		this.setSeted(F.csosId);
		return this;
	}
	public CsOutsideStatistics csosUser(Long csosUser){
		this.csosUser = csosUser;
		this.setSeted(F.csosUser);
		return this;
	}
	public CsOutsideStatistics csosCar(Long csosCar){
		this.csosCar = csosCar;
		this.setSeted(F.csosCar);
		return this;
	}
	//
	public CsOutsideStatistics csosHost(Long csosHost){
		this.csosHost = csosHost;
		this.setSeted(F.csosHost);
		return this;
	}
	public CsOutsideStatistics csosOutlets(Long csosOutlets){
		this.csosOutlets = csosOutlets;
		this.setSeted(F.csosOutlets);
		return this;
	}
	public CsOutsideStatistics csosProperty(Long csosProperty){
		this.csosProperty = csosProperty;
		this.setSeted(F.csosProperty);
		return this;
	}
	public CsOutsideStatistics csosOpenDoorCount(Integer csosOpenDoorCount){
		this.csosOpenDoorCount = csosOpenDoorCount;
		this.setSeted(F.csosOpenDoorCount);
		return this;
	}
	public CsOutsideStatistics csosCloseDoorCount(Integer csosCloseDoorCount){
		this.csosCloseDoorCount = csosCloseDoorCount;
		this.setSeted(F.csosCloseDoorCount);
		return this;
	}
	public CsOutsideStatistics csosStartCount(Integer csosStartCount){
		this.csosStartCount = csosStartCount;
		this.setSeted(F.csosStartCount);
		return this;
	}
	public CsOutsideStatistics csosId(Integer csosWhistleCount){
		this.csosWhistleCount = csosWhistleCount;
		this.setSeted(F.csosWhistleCount);
		return this;
	}
	public CsOutsideStatistics csosId(Date csosOpenDoorTime){
		this.csosOpenDoorTime = csosOpenDoorTime;
		this.setSeted(F.csosOpenDoorTime);
		return this;
	}
	
	
	public CsOutsideStatistics csosCloseDoorTime(Date csosCloseDoorTime){
		this.csosCloseDoorTime = csosCloseDoorTime;
		this.setSeted(F.csosCloseDoorTime);
		return this;
	}
	
	public CsOutsideStatistics csosAddTime(Date csosAddTime){
		this.csosAddTime = csosAddTime;
		this.setSeted(F.csosAddTime);
		return this;
	}
	
	public CsOutsideStatistics csosUpdateTime(Date csosUpdateTime){
		this.csosUpdateTime = csosUpdateTime;
		this.setSeted(F.csosUpdateTime);
		return this;
	}
	
	
	public CsOutsideStatistics csosFinishTime(Date csosFinishTime){
		this.csosFinishTime = csosFinishTime;
		this.setSeted(F.csosFinishTime);
		return this;
	}
	
	
	public CsOutsideStatistics csosStatus(Short csosStatus){
		this.csosStatus = csosStatus;
		this.setSeted(F.csosStatus);
		return this;
	}
	
	
	public CsOutsideStatistics csosCarLocation(String csosCarLocation){
		this.csosCarLocation = csosCarLocation;
		this.setSeted(F.csosCarLocation);
		return this;
	}
	public CsOutsideStatistics csosMemberLocation(String csosMemberLocation){
		this.csosMemberLocation = csosMemberLocation;
		this.setSeted(F.csosMemberLocation);
		return this;
	}
	
	public CsOutsideStatistics csosDistance(Double csosDistance){
		this.csosDistance = csosDistance;
		this.setSeted(F.csosDistance);
		return this;
	}
	
	
	
	
	public CsOutsideStatistics csosWorkTime(Long csosWorkTime){
		this.csosWorkTime = csosWorkTime;
		this.setSeted(F.csosWorkTime);
		return this;
	}
	
	
	public CsOutsideStatistics csosArea(Long csosArea){
		this.csosArea = csosArea;
		this.setSeted(F.csosArea);
		return this;
	}
	
	//克隆对象
		public CsOutsideStatistics clone(){
			CsOutsideStatistics clone = new CsOutsideStatistics();
			
			clone.csosUser=this.csosUser;
			clone.csosHost=this.csosHost;
			clone.csosCar=this.csosCar;
			clone.csosAddTime=this.csosAddTime;
			clone.csosStatus=this.csosStatus;
			return clone;
		}
	
	
	
	/**
	 * 
	 * @return
	 */
	public static @api Page<CsOutsideStatistics> page(int page,int size,Map params){
		return getCsOutsideStatisticsOnPage(page, size , params);
	}
	/**
	 * 获取分页
	 * @return
	 */
	public static @api Page<CsOutsideStatistics> getCsOutsideStatisticsOnPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsOutsideStatisticsService csOutsideStatisticsService = $.GetSpringBean("csOutsideStatisticsService");
		return csOutsideStatisticsService.getCsOutsideStatisticsOnPage(page, size , params);
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
				$.Set(name,CsOutsideStatistics.getCsOutsideStatistics(params));
			else
				$.Set(name,CsOutsideStatistics.getCsOutsideStatisticsList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	
	/**
	 * 获取所有数据
	 * @return
	 */
	public static @api List<CsOutsideStatistics> getCsOutsideStatisticsList(Map params,Integer size){
		com.ccclubs.service.admin.ICsOutsideStatisticsService csOutsideStatisticsService = $.GetSpringBean("csOutsideStatisticsService");
		return csOutsideStatisticsService.getCsOutsideStatisticsList(params, size);
	}
	
	/**
	 * 根据查询条件获取数据
	 * @param params
	 * @return
	 */
	public static @api CsOutsideStatistics getCsOutsideStatistics(Map params){
		com.ccclubs.service.admin.ICsOutsideStatisticsService csOutsideStatisticsService = $.GetSpringBean("csOutsideStatisticsService");
		return csOutsideStatisticsService.getOutsideStatistics(params);
	}
	
	
	/**
	 * 根据ID取数据
	 * @param id
	 * @return
	 */
	public static @api CsOutsideStatistics getCsOutsideStatisticsById(Long id){		
		CsOutsideStatistics csOutsideStatistics = (CsOutsideStatistics) $.GetRequest("CsOutsideStatistics$"+id);
		if(csOutsideStatistics!=null)
			return csOutsideStatistics;
		com.ccclubs.service.admin.ICsOutsideStatisticsService csOutsideStatisticsService = $.GetSpringBean("csOutsideStatisticsService");		
		return csOutsideStatisticsService.getCsOutsideStatisticsById(id);
	}
	
	/**
	 * 根据ID取数据
	 * @param id
	 * @return
	 */
	public static @api CsOutsideStatistics get(Long id){		
		return getCsOutsideStatisticsById(id);
	}
	
	
	/**
	 * 根据查询条件取数据
	 * @param params
	 * @return
	 */
	public static @api CsOutsideStatistics Get(Map params){
		return getCsOutsideStatistics(params);
	}
	
	/**
	* 获取静态内容数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsOutsideStatistics) $.GetRequest("CsOutsideStatistics$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsOutsideStatistics.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsOutsideStatistics.getCsOutsideStatistics(params);
			else
				value = CsOutsideStatistics.getCsOutsideStatisticsList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsOutsideStatistics.Get($.add(CsOutsideStatistics.F.csosId,param));
		else if(!$.empty(param.toString()))
			value = CsOutsideStatistics.get(Long.valueOf(param.toString()));
		$.SetRequest("CsOutsideStatistics$"+param.hashCode(), value);
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
	public void mergeSet(CsOutsideStatistics old){
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
	
	
	
	private String suffix;
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	/*********************************************************************************/
	/**
	 * 获取数据分页
	 * @return
	 */
	public static @api Page<CsOutsideStatistics> getCsOutsideStatisticsPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsOutsideStatisticsService csOutsideStatisticsService = $.GetSpringBean("csOutsideStatisticsService");
		return csOutsideStatisticsService.getCsOutsideStatisticsPage(page, size , params);
	}
	
	/**
	 * 获取数量
	 * @return
	 */
	public static @api Long getCsOutsideStatisticsCount(Map params){
		com.ccclubs.service.admin.ICsOutsideStatisticsService csOutsideStatisticsService = $.GetSpringBean("csOutsideStatisticsService");
		return csOutsideStatisticsService.getCsOutsideStatisticsCount(params);
	}
	
	/**
	 * 获取商品自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsOutsideStatisticsEval(String eval,Map params){
		com.ccclubs.service.admin.ICsOutsideStatisticsService csOutsideStatisticsService = $.GetSpringBean("csOutsideStatisticsService");
		return csOutsideStatisticsService.getCsOutsideStatisticsEval(eval,params);
	}
	
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsOutsideStatistics(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsOutsideStatisticsService csOutsideStatisticsService = $.GetSpringBean("csOutsideStatisticsService");
		csOutsideStatisticsService.updateCsOutsideStatisticsByConfirm(set, where);
	}
	
	/**
	 * 保存对象
	 * @param params
	 * @return
	 */
	public CsOutsideStatistics save(){
		com.ccclubs.service.admin.ICsOutsideStatisticsService csOutsideStatisticsService = $.GetSpringBean("csOutsideStatisticsService");
		if(this.getCsosId()!=null)
			csOutsideStatisticsService.updateCsOutsideStatistics(this);
		else
			return csOutsideStatisticsService.saveCsOutsideStatistics(this);
		return this;
	}
	
	
	
	
	/**
	 * 更新商品对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsOutsideStatisticsService csOutsideStatisticsService = $.GetSpringBean("csOutsideStatisticsService");
		csOutsideStatisticsService.updateCsOutsideStatistics$NotNull(this);
	}
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsOutsideStatisticsService csOutsideStatisticsService = $.GetSpringBean("csOutsideStatisticsService");
		if($.equals($.config("logic_delete"),"true"))
			csOutsideStatisticsService.removeCsOutsideStatisticsById(this.getCsosId());
		else
			csOutsideStatisticsService.deleteCsOutsideStatisticsById(this.getCsosId());
	}
	
	
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsOutsideStatisticsService csOutsideStatisticsService = $.GetSpringBean("csOutsideStatisticsService");
		return csOutsideStatisticsService.executeTransaction(function);
	}
	
	
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsGoods.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsGoods.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsOutsideStatistics.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsOutsideStatistics.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsGoods.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsGoods.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsOutsideStatistics.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsOutsideStatistics.this);
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
		public M csosId(Object csosId){this.put("csosId", csosId);return this;};
	 	/** and csg_id is null */
 		public M csosIdNull(){if(this.get("csosIdNot")==null)this.put("csosIdNot", "");this.put("csosId", null);return this;};
 	
 		public M csosIdNot(){this.put("csosIdNot", "not");return this;};
		
		public M csosUser(Object csosUser){this.put("csosUser", csosUser);return this;};
 		public M csosUserNull(){if(this.get("csosUserNot")==null)this.put("csosUserNot", "");this.put("csosUser", null);return this;};
 		public M csosUserNot(){this.put("csosUserNot", "not");return this;};
		public M csosUser$on(SrvUser.M value){
			this.put("SrvUser", value);
			this.put("csosUser$on", value);
			return this;
		};	
 		
		/** 所属产品 [非空] [CsProduct]      **/
		public M csosCar(Object csosCar){this.put("csosCar", csosCar);return this;};
 		public M csosCarNull(){if(this.get("csosCarNot")==null)this.put("csosCarNot", "");this.put("csosCar", null);return this;};
 		public M csosCarNot(){this.put("csosCarNot", "not");return this;};
 		public M csosCar$on(CsCar.M value){
			this.put("CsCar", value);
			this.put("csosCar$on", value);
			return this;
		};	
		
		//
 		public M csosArea(Object csosArea){this.put("csosArea", csosArea);return this;};
 		public M csosAreaNull(){if(this.get("csosArea")==null)this.put("csosAreaNot", "");this.put("csosArea", null);return this;};
 		public M csosAreaNot(){this.put("csosAreaNot", "not");return this;};
 		public M csosArea$on(CsArea.M value){
			this.put("CsArea", value);
			this.put("csosArea$on", value);
			return this;
		};	
		//
		public M csosHost(Object csosHost){this.put("csosHost", csosHost);return this;};
 		public M csosHostNull(){if(this.get("csosHostNot")==null)this.put("csosHostNot", "");this.put("csosHost", null);return this;};
 		public M csosHostNot(){this.put("csosHostNot", "not");return this;};
 		public M csosHost$on(SrvHost.M value){
			this.put("SrvHost", value);
			this.put("csosHost$on", value);
			return this;
		};	
 		
		/**   **/
		public M csosOutlets(Object csosOutlets){this.put("csosOutlets", csosOutlets);return this;};
 		public M csosOutletsNull(){if(this.get("csosOutletsNot")==null)this.put("csosOutletsNot", "");this.put("csosOutlets", null);return this;};
 		public M csosOutletsNot(){this.put("csosOutletsNot", "not");return this;};
 		public M csosOutlets$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("csosOutlets$on", value);
			return this;
		};	
		
 		/** **/
		public M csosProperty(Object csosProperty){this.put("csosProperty", csosProperty);return this;};
 		public M csosPropertyNull(){if(this.get("csosPropertyNot")==null)this.put("csosPropertyNot", "");this.put("csosProperty", null);return this;};
 		public M csosPropertyNot(){this.put("csosPropertyNot", "not");return this;};
 		public M csosProperty$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("csosProperty$on", value);
			return this;
		};	
		
		
 		/****/
		public M csosOpenDoorCount(Object csosOpenDoorCount){this.put("csosOpenDoorCount", csosOpenDoorCount);return this;};
 		public M csosOpenDoorCountNull(){if(this.get("csosOpenDoorCountNot")==null)this.put("csosOpenDoorCountNot", "");this.put("csosOpenDoorCount", null);return this;};
 		public M csosOpenDoorCountNot(){this.put("csosOpenDoorCountNot", "not");return this;};
 		
 		/** **/
		public M csosCloseDoorCount(Object csosCloseDoorCount){this.put("csosCloseDoorCount", csosCloseDoorCount);return this;};
 		public M csosCloseDoorCountNull(){if(this.get("csosCloseDoorCountNot")==null)this.put("csosCloseDoorCountNot", "");this.put("csosCloseDoorCount", null);return this;};
 		public M csosCloseDoorCountNot(){this.put("csosCloseDoorCountNot", "not");return this;};
 		//
 		public M csosStartCount(Object csosStartCount){this.put("csosStartCount", csosStartCount);return this;};
 		public M csosStartCountNull(){if(this.get("csosStartCountNot")==null)this.put("csosStartCountNot", "");this.put("csosStartCount", null);return this;};
 		public M csosStartCountNot(){this.put("csosStartCountNot", "not");return this;};
 		//
 		public M csosWhistleCount(Object csosWhistleCount){this.put("csosWhistleCount", csosWhistleCount);return this;};
 		public M csosWhistleCountNull(){if(this.get("csosWhistleCountNot")==null)this.put("csosWhistleCountNot", "");this.put("csosWhistleCount", null);return this;};
 		public M csosWhistleCountNot(){this.put("csosWhistleCountNot", "not");return this;};
 		//
 		public M csosOpenDoorTime(Object csosOpenDoorTime){this.put("csosOpenDoorTime", csosOpenDoorTime);return this;};
 		public M csosOpenDoorTimeNull(){if(this.get("csosOpenDoorTimeNot")==null)this.put("csosOpenDoorTimeNot", "");this.put("csosOpenDoorTime", null);return this;};
 		public M csosOpenDoorTimeNot(){this.put("csosOpenDoorTimeNot", "not");return this;};
		//
		public M csosCloseDoorTime(Object csosCloseDoorTime){this.put("csosCloseDoorTime", csosCloseDoorTime);return this;};
 		public M csosCloseDoorTimeNull(){if(this.get("csosCloseDoorTimeNot")==null)this.put("csosCloseDoorTimeNot", "");this.put("csosCloseDoorTime", null);return this;};
 		public M csosCloseDoorTimeNot(){this.put("csosCloseDoorTimeNot", "not");return this;};
		//
 		public M csosAddTime(Object csosAddTime){this.put("csosAddTime", csosAddTime);return this;};
 		public M csosAddTimeNull(){if(this.get("ccsosAddTimeNot")==null)this.put("csosAddTimeNot", "");this.put("csosAddTime", null);return this;};
 		public M csosAddTimeNot(){this.put("csosAddTimeNot", "not");return this;};
 		public M csosAddTimeStart(Object start){this.put("csosAddTimeStart", start);return this;};			
 		public M csosAddTimeEnd(Object end){this.put("csosAddTimeEnd", end);return this;};
 		//
 		public M csosUpdateTime(Object csosUpdateTime){this.put("csosUpdateTime", csosUpdateTime);return this;};
 		public M csosUpdateTimeNull(){if(this.get("csosCloseDoorTimeNot")==null)this.put("csosUpdateTimeNot", "");this.put("csosUpdateTime", null);return this;};
 		public M csosUpdateTimeNot(){this.put("csosUpdateTimeNot", "not");return this;};
 		public M csosUpdateTimeStart(Object start){this.put("csosUpdateTimeStart", start);return this;};			
 		public M csosUpdateTimeEnd(Object end){this.put("csosUpdateTimeEnd", end);return this;};
 		//
		public M csosFinishTime(Object csosFinishTime){this.put("csosFinishTime", csosFinishTime);return this;};
 		public M csosFinishTimeNull(){if(this.get("csosFinishTimeNot")==null)this.put("csosFinishTimeNot", "");this.put("csosFinishTime", null);return this;};
 		public M csosFinishTimeNot(){this.put("csosFinishTimeNot", "not");return this;};
 		public M csosFinishTimeStart(Object start){this.put("csosFinishTimeStart", start);return this;};			
 		public M csosFinishTimeEnd(Object end){this.put("csosFinishTimeEnd", end);return this;};
 		
 		//
 		public M csosStatus(Object csosStatus){this.put("csosStatus", csosStatus);return this;};
 		public M csosStatusNull(){if(this.get("csosStatusNot")==null)this.put("csosStatusNot", "");this.put("csosStatus", null);return this;};
 		public M csosStatusNot(){this.put("csosStatusNot", "not");return this;};
		//
 		public M csosCarLocation(Object csosCarLocation){this.put("csosCarLocation", csosCarLocation);return this;};
 		public M csosCarLocationNull(){if(this.get("csosCarLocationNot")==null)this.put("csosCarLocationNot", "");this.put("csosCarLocation", null);return this;};
 		public M csosCarLocationNot(){this.put("csosCarLocationNot", "not");return this;};
		//
		public M csosMemberLocation(Object csosMemberLocation){this.put("csosMemberLocation", csosMemberLocation);return this;};
 		public M csosMemberLocationNull(){if(this.get("csosMemberLocation")==null)this.put("csosMemberLocationNot", "");this.put("csosMemberLocation", null);return this;};
 		public M csosMemberLocationNot(){this.put("csosMemberLocationNot", "not");return this;};
		//
 		public M csosDistance(Object csosDistance){this.put("csosDistance", csosDistance);return this;};
 		public M csosDistanceNull(){if(this.get("csosDistance")==null)this.put("csosDistanceNot", "");this.put("csosDistance", null);return this;};
 		public M csosDistanceNot(){this.put("csosDistanceNot", "not");return this;};
		
 		//
 		public M csosWorkTime(Object csosWorkTime){this.put("csosWorkTime", csosWorkTime);return this;};
 		public M csosWorkTimeNull(){if(this.get("csosWorkTime")==null)this.put("csosWorkTimeNot", "");this.put("csosWorkTime", null);return this;};
 		public M csosWorkTimeNot(){this.put("csosWorkTimeNot", "not");return this;};
		
 		
 		
 		
 		public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	
	 	
	 	/** 获取所有数据 **/
		public @api List<CsOutsideStatistics> list(Integer size){
			return getCsOutsideStatisticsList(this,size);
		}
		/** 获取数据分页 **/
		public @api Page<CsOutsideStatistics> page(int page,int size){
			return getCsOutsideStatisticsPage(page, size , this);
		}
		/** 根据查询条件取数据 **/
		public @api CsOutsideStatistics get(){
			return getCsOutsideStatistics(this);
		}
		/** 获取数 **/
		public @api Long count(){
			return getCsOutsideStatisticsCount(this);
		}
		/** 获取数据表达式 **/
		public @api <T> T eval(String strEval){
			return getCsOutsideStatisticsEval(strEval,this);
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
			updateCsOutsideStatistics(set,where);
		}
	}
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csosId="csosId";
		/** 员工 [非空]       **/
		public final static @type(Long.class)  String csosUser="csosUser";
		/** 车辆 [非空] [CsProduct]      **/
		public final static @type(Long.class)   String csosCar="csosCar";
		/** 城市 [非空] [CsUserType]      **/
		public final static @type(Long.class)  String csosHost="csosHost";
		/** 网点 [CsRule]      **/
		public final static @type(Long.class)  String csosOutlets="csosOutlets";
		/** 整备项      **/
		public final static @type(Long.class)  String csosProperty="csosProperty";
		/******开门次数**/
		public final static @type(Integer.class)  String csosOpenDoorCount="csosOpenDoorCount";
		/******关门次数**/
		public final static @type(Integer.class)  String csosCloseDoorCount="csosCloseDoorCount";
		/******启动次数**/
		public final static @type(Integer.class)  String csosStartCount="csosStartCount";
		/******鸣笛次数**/
		public final static @type(Integer.class)  String csosWhistleCount="csosWhistleCount";
		/** 开门时间 [非空]       **/
		public final static @type(Date.class)  String csosOpenDoorTime="csosOpenDoorTime";
		public final static @type(Date.class) String csosOpenDoorTimeStart="csosOpenDoorTimeStart";
 		public final static @type(Date.class) String csosOpenDoorTimeEnd="csosOpenDoorTimeEnd";
		
	 	/** 关门时间 */
 		public final static @type(Date.class) String csosCloseDoorTime="csosCloseDoorTime";
 		public final static @type(Date.class) String csosCloseDoorTimeStart="csosCloseDoorTimeStart";
 		public final static @type(Date.class) String csosCloseDoorTimeEnd="csosCloseDoorTimeEnd";
 		
 		
 		/**添加时间 */
 		public final static @type(Date.class) String csosAddTime="csosAddTime";
 		public final static @type(Date.class) String csosAddTimeStart="csosAddTimeStart";
 		public final static @type(Date.class) String csosAddTimeEnd="csosAddTimeEnd";
 		
		/** 更新时间 [非空]       **/
		public final static @type(Date.class)  String csosUpdateTime="csosUpdateTime";
		public final static @type(Date.class) String csosUpdateTimeStart="csosUpdateTimeStart";
 		public final static @type(Date.class) String csosUpdateTimeEnd="csosUpdateTimeEnd";
 		
	 	/** 完成时间 */
 		public final static @type(Date.class) String csosFinishTime="csosFinishTime";
 		public final static @type(Date.class) String csosFinishTimeStart="csosFinishTimeStart";
 		public final static @type(Date.class) String csosFinishTimeEnd="csosFinishTimeEnd";
 		
 		/** 状态 */
 		public final static @type(Short.class) String csosStatus="csosStatus";
		/** 车辆位置    **/
		public final static @type(String.class)  String csosCarLocation="csosCarLocation";
		/***人员位置***/
		public final static @type(String.class)  String csosMemberLocation="csosMemberLocation";
		/****人车距离**/
		public final static @type(Double.class)  String csosDistance="csosDistance";
		
		/******/
		public final static @type(Long.class)  String csosWorkTime="csosWorkTime";
		
		/******/
		public final static @type(Long.class)  String csosArea="csosArea";
		
		
		
	}
	
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csosId="csos_id";
		/** 名称 [非空]       **/
		public final static String csosUser="csos_user";
		/** 所属产品 [非空] [CsProduct]      **/
		public final static String csosCar="csos_car";
		public final static String csosArea="csos_area";
		/** 计费类型 [非空] [CsUserType]      **/
		public final static String csosHost="csos_host";
		/** 单位规则  [CsRule]      **/
		public final static String csosOutlets="csos_outlets";
		/** 商品描述        **/
		public final static String csosProperty="csos_property";
		/** 修改时间 [非空]       **/
		public final static String csosOpenDoorCount="csos_open_door_count";
		/** 添加时间 [非空]       **/
		public final static String csosCloseDoorCount="csos_close_door_count";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csosStartCount="csos_start_count";
		public final static String csosWhistleCount="csos_whistle_count";
		public final static String csosOpenDoorTime="csos_open_door_time";
		public final static String csosCloseDoorTime="csos_close_door_time";
		public final static String csosAddTime="csos_add_time";
		public final static String csosUpdateTime="csos_update_time";
		public final static String csosFinishTime="csos_finish_time";
		public final static String csosStatus="csos_status";
		public final static String csosCarLocation="csos_car_location";
		public final static String csosMemberLocation="csos_member_location";
		public final static String csosDistance="csos_distance";
		public final static String csosWorkTime="csos_work_time";
		

		
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
	
	/*********************************************************/
	
	/**
	 * 根据ID取商品的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	//-----------------------------呆改
		String value = MemCache.getValue(CsOutsideStatistics.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsOutsideStatistics csOutsideStatistics = get(id);
//		if(csOutsideStatistics!=null){
//			String strValue = csOutsideStatistics.getCsgName$();
//			if(!"CsgName".equals("CsgName"))
//				strValue+="("+csOutsideStatistics.getCsgName$()+")";
//			MemCache.setValue(CsOutsideStatistics.class, id ,strValue);
//			return strValue;
//		}
		return null;
	}	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){//----------------呆改
		String keyValue =null;
//		if(!"CsgName".equals("CsgName"))
//			keyValue+="("+this.getCsgName$()+")";
		return keyValue;
	}

	/**********************setter/getter***函数***********************************************************/
	public Long getCsosId() {
		return this.csosId;
	}

	/**
	* 获取编号格式化(toString)
	**/
	public String getCsosId$(){
		String strValue="";
		 strValue=$.str(this.getCsosId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsosId(Long csosId){
		this.csosId = csosId;
		this.setSeted(F.csosId);
	}

	/**********************************/	
	public Long getCsosUser() {
		return this.csosUser;
	}

	public void setCsosUser(Long csosUser) {
		this.csosUser = csosUser;
		this.setSeted(F.csosUser);
	}
	
	public String getCsosUser$(){
		String strValue="";
		if(this.getCsosUser()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsosUser()));
		}		
	 	 return strValue;
	}
	
	/**********************************/	
	public Long getCsosCar() {
		return this.csosCar;
	}

	public void setCsosCar(Long csosCar) {
		this.csosCar = csosCar;
		this.setSeted(F.csosCar);
	}
	public String getCsosCar$(){
		String strValue="";
		if(this.getCsosCar()!=null){
				strValue+=$.str(CsCar.getKeyValue(this.getCsosCar()));
		}			
	 	 return strValue;
	}
	/**********************************/	
	public Long getCsosHost() {
		return this.csosHost;
	}

	public void setCsosHost(Long csosHost) {
		this.csosHost = csosHost;
		this.setSeted(F.csosHost);
	}
	public String getCsosHost$(){
		String strValue="";
		 if(this.getCsosHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsosHost()));
		}		
	 	 return strValue;
	}
	
	
	/**********************************/	
	public Long getCsosOutlets() {
		return this.csosOutlets;
	}

	public void setCsosOutlets(Long csosOutlets) {
		this.csosOutlets = csosOutlets;
		this.setSeted(F.csosOutlets);
	}
	
	public String getCsosOutlets$(){
		String strValue="";
		 if(this.getCsosOutlets()!=null){
				strValue+=$.str(CsOutlets.getKeyValue(this.getCsosOutlets()));
		}		
	 	 return strValue;
	}
	
	/**********************************/	
	public Long getCsosProperty() {
		return this.csosProperty;
	}

	public void setCsosProperty(Long csosProperty) {
		this.csosProperty = csosProperty;
		this.setSeted(F.csosProperty);
	}
	public String getCsosProperty$(){
		String strValue="";
		 if(this.getCsosProperty()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCsosProperty()));
		}		
	 	 return strValue;
	}
	
	/**********************************/	
	public Integer getCsosOpenDoorCount() {
		return this.csosOpenDoorCount;
	}

	public void setCsosOpenDoorCount(Integer csosOpenDoorCount) {
		this.csosOpenDoorCount = csosOpenDoorCount;
		this.setSeted(F.csosOpenDoorCount);
	}
	public String getCsosOpenDoorCount$(){
		String strValue="";
		 strValue=$.str(this.getCsosOpenDoorCount());
	 	 return strValue;
	}
	
	/**********************************/	
	public Integer getCsosCloseDoorCount() {
		return this.csosCloseDoorCount;
	}

	public void setCsosCloseDoorCount(Integer csosCloseDoorCount) {
		this.csosCloseDoorCount = csosCloseDoorCount;
		this.setSeted(F.csosCloseDoorCount);
	}
	public String  getCsosCloseDoorCount$(){
		String strValue="";
		 strValue=$.str(this.getCsosCloseDoorCount());
	 	 return strValue;
	}
	
	
	/**********************************/	
	public Integer getCsosStartCount() {
		return this.csosStartCount;
	}

	public void setCsosStartCount(Integer csosStartCount) {
		this.csosStartCount = csosStartCount;
		this.setSeted(F.csosStartCount);
		
	}
	public String  getCsosStartCount$(){
		 String strValue="";
		 strValue=$.str(this.getCsosStartCount());
	 	 return strValue;
	}
	
	
	/***************鸣笛次数*******************/	
	public Integer getCsosWhistleCount() {
		return this.csosWhistleCount;
	}

	public void setCsosWhistleCount(Integer csosWhistleCount) {
		this.csosWhistleCount = csosWhistleCount;
		this.setSeted(F.csosWhistleCount);
		
	}
	public String  getCsosWhistleCount$(){
		 String strValue="";
		 strValue=$.str(this.getCsosWhistleCount());
	 	 return strValue;
	}
	/****************开门时间******************/	
	public Date getCsosOpenDoorTime() {
		return this.csosOpenDoorTime;
	}

	public void setCsosOpenDoorTime(Date csosOpenDoorTime) {
		this.csosOpenDoorTime = csosOpenDoorTime;
		this.setSeted(F.csosOpenDoorTime);
	}
	public String getCsosOpenDoorTime$(){
		 String strValue="";
		 strValue=$.str($.date(this.getCsosOpenDoorTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	
	/****************关门时间******************/	
	public Date getCsosCloseDoorTime() {
		return this.csosCloseDoorTime;
	}

	public void setCsosCloseDoorTime(Date csosCloseDoorTime) {
		this.csosCloseDoorTime = csosCloseDoorTime;
		this.setSeted(F.csosCloseDoorTime);
		
	}
	public String getCsosCloseDoorTime$(){
		 String strValue="";
		 strValue=$.str($.date(this.getCsosCloseDoorTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	
	/***********添加时间***********************/	
	public Date getCsosAddTime() {
		return this.csosAddTime;
	}

	public void setCsosAddTime(Date csosAddTime) {
		this.csosAddTime = csosAddTime;
		this.setSeted(F.csosAddTime);
	}
	
	public String getCsosAddTime$(){
		 String strValue="";
		 strValue=$.str($.date(this.getCsosAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	
	
	/*************更新新时间*********************/	
	public Date getCsosUpdateTime() {
		return this.csosUpdateTime;
	}

	public void setCsosUpdateTime(Date csosUpdateTime) {
		this.csosUpdateTime = csosUpdateTime;
		this.setSeted(F.csosUpdateTime);
	}
	public String getCsosUpdateTime$(){
		 String strValue="";
		 strValue=$.str($.date(this.getCsosUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	
	/**************完成整备时间********************/	
	public Date getCsosFinishTime() {
		return this.csosFinishTime;
	}

	public void setCsosFinishTime(Date csosFinishTime) {
		this.csosFinishTime = csosFinishTime;
		this.setSeted(F.csosFinishTime);
	}
	public String getCsosFinishTime$(){
		 String strValue="";
		 strValue=$.str($.date(this.getCsosFinishTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	
	
	/************状态：1=未完成 2=已完成**********************/	
	public Short getCsosStatus() {
		return this.csosStatus;
	}

	public void setCsosStatus(Short csosStatus) {
		this.csosStatus = csosStatus;
		this.setSeted(F.csosStatus);
	}
	
	public String getCsosStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsosStatus()),"1"))
			strValue=$.str("未完成");		 
		 if($.equals($.str(this.getCsosStatus()),"2"))
			strValue=$.str("已完成");		 
	 	 return strValue;
	}
	/************车辆位置**********************/	
	public String getCsosCarLocation() {
		return this.csosCarLocation;
	}

	public void setCsosCarLocation(String csosCarLocation) {
		this.csosCarLocation = csosCarLocation;
		this.setSeted(F.csosCarLocation);
		
	}
	public String getCsosCarLocation$(){
		String strValue="";
		 strValue=$.str(this.getCsosCarLocation());
	 	 return strValue;
	}
	
	/************人员位置**********************/	
	public String getCsosMemberLocation() {
		return this.csosMemberLocation;
	}

	public void setCsosMemberLocation(String csosMemberLocation) {
		this.csosMemberLocation = csosMemberLocation;
		this.setSeted(F.csosMemberLocation);
	}
	public String getCsosMemberLocation$(){
		String strValue="";
		 strValue=$.str(this.getCsosMemberLocation());
	 	 return strValue;
	}
	
	/***********人车距离***********************/	
	public Double getCsosDistance() {
		return this.csosDistance;
	}

	public void setCsosDistance(Double csosDistance) {
		this.csosDistance = csosDistance;
		this.setSeted(F.csosDistance);
	}
	public String getCsosDistance$(){
		String strValue="";
		 strValue=$.str(this.getCsosDistance());
	 	 return strValue;
	}
	
	
	public Long getCsosWorkTime() {
		return this.csosWorkTime;
	}

	public void setCsosWorkTime(Long csosWorkTime) {
		this.csosWorkTime = csosWorkTime;
		this.setSeted(F.csosWorkTime);
	}
	public String getCsosWorkTime$(){
		String strValue="";
		 strValue=$.str(this.getCsosWorkTime());
	 	 return strValue;
	}
	
	
	
	
	public Long getCsosArea() {
		return this.csosArea;
	}

	public void setCsosArea(Long csosArea) {
		this.csosArea = csosArea;
		this.setSeted(F.csosArea);
	}
	public String getCsosArea$(){
		String strValue="";
		 if(this.getCsosArea()!=null){
				strValue+=$.str(CsArea.getKeyValue(this.getCsosArea()));
		}			
	 	 return strValue;
	}
}
