package com.ccclubs.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.dao.impl.MemCache;
import com.lazy3q.lang.RelateClass;
import com.lazy3q.lang.api;
import com.lazy3q.lang.caption;
import com.lazy3q.lang.column;
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

@namespace("oa/outside")
public @caption("还车检查") @table("cs_retcheck") class CsRetcheck implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csr_id")   @primary   @note("  ") Long csrId;
	private @caption("物品项目") @column("csr_item_id")    @relate("$csrItemId") @RelateClass(SrvProperty.class)  @note(" 车辆物品项目:'yw_attachment' ") Long csrItemId;
	private SrvProperty $csrItemId;//关联对象[物品项目]
	private @caption("车辆id") @column("csr_car")    @relate("$csrCar") @RelateClass(CsCar.class)  @note("  ") Long csrCar;
	private CsCar $csrCar;//关联对象[车辆id]
	private @caption("物品项名称") @column("csr_item_name")      @note("  ") String csrItemName;
	private @caption("后勤人员") @column("csr_user")    @relate("$csrUser") @RelateClass(SrvUser.class)  @note("  ") Long csrUser;
	private SrvUser $csrUser;//关联对象[后勤人员]
	private @caption("物品状态") @column("csr_item_state")      @note("1:正常 2:丢失") Short csrItemState;
	private @caption("记录状态") @column("csr_state")      @note("0:新增 1:过期") Short csrState;
	private @caption("备注") @column("csr_remark")      @note("  ") String csrRemark;
	private @caption("创建时间") @column("csr_addtime")      @note("  ") Date csrAddtime;
	private @caption("修改时间") @column("csr_updatetime")      @note("  ") Date csrUpdatetime;
	private @caption("数据状态") @column("csr_status")      @note("0:无效 1:有效") Short csrStatus;
	
	//默认构造函数
	public CsRetcheck(){
	
	}
	
	//主键构造函数
	public CsRetcheck(Long id){
		this.csrId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsRetcheck(Long csrItemId,Long csrCar,String csrItemName,Long csrUser,Short csrItemState,Short csrState,String csrRemark,Date csrAddtime,Date csrUpdatetime,Short csrStatus){
		this.csrItemId=csrItemId;
		this.csrCar=csrCar;
		this.csrItemName=csrItemName;
		this.csrUser=csrUser;
		this.csrItemState=csrItemState;
		this.csrState=csrState;
		this.csrRemark=csrRemark;
		this.csrAddtime=csrAddtime;
		this.csrUpdatetime=csrUpdatetime;
		this.csrStatus=csrStatus;
	}
	
	//设置非空字段
	public CsRetcheck setNotNull(Long csrItemId,Long csrCar){
		this.csrItemId=csrItemId;
		this.csrCar=csrCar;
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsRetcheck csrId(Long csrId){
		this.csrId = csrId;
		this.setSeted(F.csrId);
		return this;
	}
	/** 物品项目 [可空]       **/
	public CsRetcheck csrItemId(Long csrItemId){
		this.csrItemId = csrItemId;
		this.setSeted(F.csrItemId);
		return this;
	}
	/** 车辆id [可空]       **/
	public CsRetcheck csrCar(Long csrCar){
		this.csrCar = csrCar;
		this.setSeted(F.csrCar);
		return this;
	}
	/** 物品项名称 [非空]       **/
	public CsRetcheck csrItemName(String csrItemName){
		this.csrItemName = csrItemName;
		this.setSeted(F.csrItemName);
		return this;
	}
	/** 后勤人员 [非空]       **/
	public CsRetcheck csrUser(Long csrUser){
		this.csrUser = csrUser;
		this.setSeted(F.csrUser);
		return this;
	}
	/** 物品状态 [非空]       **/
	public CsRetcheck csrItemState(Short csrItemState){
		this.csrItemState = csrItemState;
		this.setSeted(F.csrItemState);
		return this;
	}
	/** 记录状态 [非空]       **/
	public CsRetcheck csrState(Short csrState){
		this.csrState = csrState;
		this.setSeted(F.csrState);
		return this;
	}
	/** 备注 [非空]       **/
	public CsRetcheck csrRemark(String csrRemark){
		this.csrRemark = csrRemark;
		this.setSeted(F.csrRemark);
		return this;
	}
	/** 创建时间 [非空]       **/
	public CsRetcheck csrAddtime(Date csrAddtime){
		this.csrAddtime = csrAddtime;
		this.setSeted(F.csrAddtime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsRetcheck csrUpdatetime(Date csrUpdatetime){
		this.csrUpdatetime = csrUpdatetime;
		this.setSeted(F.csrUpdatetime);
		return this;
	}
	/** 数据状态 [非空]       **/
	public CsRetcheck csrStatus(Short csrStatus){
		this.csrStatus = csrStatus;
		this.setSeted(F.csrStatus);
		return this;
	}
	
	//克隆对象
	public CsRetcheck clone(){
		CsRetcheck clone = new CsRetcheck();
		clone.csrId=this.csrId;
		clone.csrItemId=this.csrItemId;
		clone.csrCar=this.csrCar;
		clone.csrItemName=this.csrItemName;
		clone.csrUser=this.csrUser;
		clone.csrItemState=this.csrItemState;
		clone.csrState=this.csrState;
		clone.csrRemark=this.csrRemark;
		clone.csrAddtime=this.csrAddtime;
		clone.csrUpdatetime=this.csrUpdatetime;
		clone.csrStatus=this.csrStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取还车检查
	 * @param id
	 * @return
	 */
	public static @api CsRetcheck get(Long id){		
		return getCsRetcheckById(id);
	}
	/**
	 * 获取所有还车检查
	 * @return
	 */
	public static @api List<CsRetcheck> list(Map params,Integer size){
		return getCsRetcheckList(params,size);
	}
	/**
	 * 获取还车检查分页
	 * @return
	 */
	public static @api Page<CsRetcheck> page(int page,int size,Map params){
		return getCsRetcheckPage(page, size , params);
	}
	/**
	 * 根据查询条件取还车检查
	 * @param params
	 * @return
	 */
	public static @api CsRetcheck Get(Map params){
		return getCsRetcheck(params);
	}
	/**
	 * 获取还车检查数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsRetcheckCount(params);
	}
	/**
	 * 获取还车检查数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsRetcheckEval(eval,params);
	}
	
	/**
	 * 根据ID取还车检查
	 * @param id
	 * @return
	 */
	public static @api CsRetcheck getCsRetcheckById(Long id){		
		CsRetcheck csRetcheck = (CsRetcheck) $.GetRequest("CsRetcheck$"+id);
		if(csRetcheck!=null)
			return csRetcheck;
		com.ccclubs.service.admin.ICsRetcheckService csRetcheckService = $.GetSpringBean("csRetcheckService");		
		return csRetcheckService.getCsRetcheckById(id);
	}
	
	
	/**
	 * 根据ID取还车检查的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsRetcheck.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsRetcheck csRetcheck = get(id);
		if(csRetcheck!=null){
			String strValue = csRetcheck.getCsrId$();
			if(!"CsrId".equals("CsrId"))
				strValue+="("+csRetcheck.getCsrId$()+")";
			MemCache.setValue(CsRetcheck.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsrId$();
		if(!"CsrId".equals("CsrId"))
			keyValue+="("+this.getCsrId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有还车检查
	 * @return
	 */
	public static @api List<CsRetcheck> getCsRetcheckList(Map params,Integer size){
		com.ccclubs.service.admin.ICsRetcheckService csRetcheckService = $.GetSpringBean("csRetcheckService");
		return csRetcheckService.getCsRetcheckList(params, size);
	}
	
	/**
	 * 获取还车检查分页
	 * @return
	 */
	public static @api Page<CsRetcheck> getCsRetcheckPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsRetcheckService csRetcheckService = $.GetSpringBean("csRetcheckService");
		return csRetcheckService.getCsRetcheckPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取还车检查
	 * @param params
	 * @return
	 */
	public static @api CsRetcheck getCsRetcheck(Map params){
		com.ccclubs.service.admin.ICsRetcheckService csRetcheckService = $.GetSpringBean("csRetcheckService");
		return csRetcheckService.getCsRetcheck(params);
	}
	
	/**
	 * 获取还车检查数
	 * @return
	 */
	public static @api Long getCsRetcheckCount(Map params){
		com.ccclubs.service.admin.ICsRetcheckService csRetcheckService = $.GetSpringBean("csRetcheckService");
		return csRetcheckService.getCsRetcheckCount(params);
	}
		
		
	/**
	 * 获取还车检查自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsRetcheckEval(String eval,Map params){
		com.ccclubs.service.admin.ICsRetcheckService csRetcheckService = $.GetSpringBean("csRetcheckService");
		return csRetcheckService.getCsRetcheckEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsRetcheck(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsRetcheckService csRetcheckService = $.GetSpringBean("csRetcheckService");
		csRetcheckService.updateCsRetcheckByConfirm(set, where);
	}
	
	
	/**
	 * 保存还车检查对象
	 * @param params
	 * @return
	 */
	public CsRetcheck save(){
		com.ccclubs.service.admin.ICsRetcheckService csRetcheckService = $.GetSpringBean("csRetcheckService");
		if(this.getCsrId()!=null)
			csRetcheckService.updateCsRetcheck(this);
		else
			return csRetcheckService.saveCsRetcheck(this);
		return this;
	}
	
	
	/**
	 * 更新还车检查对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsRetcheckService csRetcheckService = $.GetSpringBean("csRetcheckService");
		csRetcheckService.updateCsRetcheck$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsRetcheckService csRetcheckService = $.GetSpringBean("csRetcheckService");
		if($.equals($.config("logic_delete"),"true"))
			csRetcheckService.removeCsRetcheckById(this.getCsrId());
		else
			csRetcheckService.deleteCsRetcheckById(this.getCsrId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsRetcheckService csRetcheckService = $.GetSpringBean("csRetcheckService");
		return csRetcheckService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsrId(){
		return this.csrId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsrId$(){
		String strValue="";
		 strValue=$.str(this.getCsrId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCsrId(Long csrId){
		this.csrId = csrId;
		this.setSeted(F.csrId);
	}
	/*******************************物品项目**********************************/	
	/**
	* 物品项目 [可空]      
	**/
	public Long getCsrItemId(){
		return this.csrItemId;
	}
	/**
	* 获取物品项目格式化(toString)
	**/
	public String getCsrItemId$(){
		String strValue="";
		if(this.getCsrItemId()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCsrItemId()));
		}			
	 	 return strValue;
	}
	/**
	* 物品项目 [可空]     
	**/
	public void setCsrItemId(Long csrItemId){
		this.csrItemId = csrItemId;
		this.setSeted(F.csrItemId);
	}
	/**
	* 获取关联对象[物品项目]
	**/	 			
 	public SrvProperty get$csrItemId(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCsrItemId());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCsrItemId()!=null){
			srvProperty = SrvProperty.get(this.getCsrItemId());
 		}
 		$.SetRequest("SrvProperty$"+this.getCsrItemId(), srvProperty);
	 	return srvProperty;
	}
	/*******************************车辆id**********************************/	
	/**
	* 车辆id [可空]      
	**/
	public Long getCsrCar(){
		return this.csrCar;
	}
	/**
	* 获取车辆id格式化(toString)
	**/
	public String getCsrCar$(){
		String strValue="";
		if(this.getCsrCar()!=null){
				strValue+=$.str(CsCar.getKeyValue(this.getCsrCar()));
		}			
	 	 return strValue;
	}
	/**
	* 车辆id [可空]     
	**/
	public void setCsrCar(Long csrCar){
		this.csrCar = csrCar;
		this.setSeted(F.csrCar);
	}
	/**
	* 获取关联对象[车辆id]
	**/	 			
 	public CsCar get$csrCar(){
 		com.ccclubs.model.CsCar csCar = (com.ccclubs.model.CsCar) $.GetRequest("CsCar$"+this.getCsrCar());
 		if(csCar!=null)
			return csCar;
		if(this.getCsrCar()!=null){
			csCar = CsCar.get(this.getCsrCar());
 		}
 		$.SetRequest("CsCar$"+this.getCsrCar(), csCar);
	 	return csCar;
	}
	/*******************************物品项名称**********************************/	
	/**
	* 物品项名称 [非空]      
	**/
	public String getCsrItemName(){
		return this.csrItemName;
	}
	/**
	* 获取物品项名称格式化(toString)
	**/
	public String getCsrItemName$(){
		String strValue="";
		 strValue=$.str(this.getCsrItemName());
	 	 return strValue;
	}
	/**
	* 物品项名称 [非空]     
	**/
	public void setCsrItemName(String csrItemName){
		this.csrItemName = csrItemName;
		this.setSeted(F.csrItemName);
	}
	/*******************************后勤人员**********************************/	
	/**
	* 后勤人员 [非空]      
	**/
	public Long getCsrUser(){
		return this.csrUser;
	}
	/**
	* 获取后勤人员格式化(toString)
	**/
	public String getCsrUser$(){
		String strValue="";
		if(this.getCsrUser()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsrUser()));
		}			
	 	 return strValue;
	}
	/**
	* 后勤人员 [非空]     
	**/
	public void setCsrUser(Long csrUser){
		this.csrUser = csrUser;
		this.setSeted(F.csrUser);
	}
	/**
	* 获取关联对象[后勤人员]
	**/	 			
 	public SrvUser get$csrUser(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsrUser());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsrUser()!=null){
			srvUser = SrvUser.get(this.getCsrUser());
 		}
 		$.SetRequest("SrvUser$"+this.getCsrUser(), srvUser);
	 	return srvUser;
	}
	/*******************************物品状态**********************************/	
	/**
	* 物品状态 [非空]      
	**/
	public Short getCsrItemState(){
		return this.csrItemState;
	}
	/**
	* 获取物品状态格式化(toString)
	**/
	public String getCsrItemState$(){
		String strValue="";
		 strValue=$.str(this.getCsrItemState());
	 	 return strValue;
	}
	/**
	* 物品状态 [非空]     
	**/
	public void setCsrItemState(Short csrItemState){
		this.csrItemState = csrItemState;
		this.setSeted(F.csrItemState);
	}
	/*******************************记录状态**********************************/	
	/**
	* 记录状态 [非空]      
	**/
	public Short getCsrState(){
		return this.csrState;
	}
	/**
	* 获取记录状态格式化(toString)
	**/
	public String getCsrState$(){
		String strValue="";
		 if($.equals($.str(this.getCsrState()),"1"))
			 strValue=$.str("过期");
		 if($.equals($.str(this.getCsrState()),"0"))
			 strValue=$.str("新增");
	 	 return strValue;
	}
	/**
	* 记录状态 [非空]     
	**/
	public void setCsrState(Short csrState){
		this.csrState = csrState;
		this.setSeted(F.csrState);
	}
	/*******************************备注**********************************/	
	/**
	* 备注 [非空]      
	**/
	public String getCsrRemark(){
		return this.csrRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCsrRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsrRemark());
	 	 return strValue;
	}
	/**
	* 备注 [非空]     
	**/
	public void setCsrRemark(String csrRemark){
		this.csrRemark = csrRemark;
		this.setSeted(F.csrRemark);
	}
	/*******************************创建时间**********************************/	
	/**
	* 创建时间 [非空]      
	**/
	public Date getCsrAddtime(){
		return this.csrAddtime;
	}
	/**
	* 获取创建时间格式化(toString)
	**/
	public String getCsrAddtime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsrAddtime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 创建时间 [非空]     
	**/
	public void setCsrAddtime(Date csrAddtime){
		this.csrAddtime = csrAddtime;
		this.setSeted(F.csrAddtime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsrUpdatetime(){
		return this.csrUpdatetime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsrUpdatetime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsrUpdatetime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCsrUpdatetime(Date csrUpdatetime){
		this.csrUpdatetime = csrUpdatetime;
		this.setSeted(F.csrUpdatetime);
	}
	/*******************************数据状态**********************************/	
	/**
	* 数据状态 [非空]      
	**/
	public Short getCsrStatus(){
		return this.csrStatus;
	}
	/**
	* 获取数据状态格式化(toString)
	**/
	public String getCsrStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsrStatus()),"1"))
			 strValue=$.str("有效");
		 if($.equals($.str(this.getCsrStatus()),"0"))
			 strValue=$.str("无效");
	 	 return strValue;
	}
	/**
	* 数据状态 [非空]     
	**/
	public void setCsrStatus(Short csrStatus){
		this.csrStatus = csrStatus;
		this.setSeted(F.csrStatus);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsRetcheck.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRetcheck.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRetcheck.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsRetcheck.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsRetcheck.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsRetcheck.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsRetcheck.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsRetcheck.this);
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
		public M csrId(Object csrId){this.put("csrId", csrId);return this;};
		/** and csr_id is null */
		public M csrIdNull(){if(this.get("csrIdNot")==null)this.put("csrIdNot", "");this.put("csrId", null);return this;};
		/** not .... */
		public M csrIdNot(){this.put("csrIdNot", "not");return this;};
		/** and csr_id >= ? */
		public M csrIdMin(Object min){this.put("csrIdMin", min);return this;};
		/** and csr_id <= ? */
		public M csrIdMax(Object max){this.put("csrIdMax", max);return this;};
		/** 物品项目 [可空]       **/
		public M csrItemId(Object csrItemId){this.put("csrItemId", csrItemId);return this;};
		/** and csr_item_id is null */
		public M csrItemIdNull(){if(this.get("csrItemIdNot")==null)this.put("csrItemIdNot", "");this.put("csrItemId", null);return this;};
		/** not .... */
		public M csrItemIdNot(){this.put("csrItemIdNot", "not");return this;};
		public M csrItemId$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("csrItemId$on", value);
			return this;
		};	
		/** and csr_item_id >= ? */
		public M csrItemIdMin(Object min){this.put("csrItemIdMin", min);return this;};
		/** and csr_item_id <= ? */
		public M csrItemIdMax(Object max){this.put("csrItemIdMax", max);return this;};
		/** 车辆id [可空]       **/
		public M csrCar(Object csrCar){this.put("csrCar", csrCar);return this;};
		/** and csr_car is null */
		public M csrCarNull(){if(this.get("csrCarNot")==null)this.put("csrCarNot", "");this.put("csrCar", null);return this;};
		/** not .... */
		public M csrCarNot(){this.put("csrCarNot", "not");return this;};
		public M csrCar$on(CsCar.M value){
			this.put("CsCar", value);
			this.put("csrCar$on", value);
			return this;
		};	
		/** and csr_car >= ? */
		public M csrCarMin(Object min){this.put("csrCarMin", min);return this;};
		/** and csr_car <= ? */
		public M csrCarMax(Object max){this.put("csrCarMax", max);return this;};
		/** 物品项名称 [非空]       **/
		public M csrItemName(Object csrItemName){this.put("csrItemName", csrItemName);return this;};
		/** and csr_item_name is null */
		public M csrItemNameNull(){if(this.get("csrItemNameNot")==null)this.put("csrItemNameNot", "");this.put("csrItemName", null);return this;};
		/** not .... */
		public M csrItemNameNot(){this.put("csrItemNameNot", "not");return this;};
		/** 后勤人员 [非空]       **/
		public M csrUser(Object csrUser){this.put("csrUser", csrUser);return this;};
		/** and csr_user is null */
		public M csrUserNull(){if(this.get("csrUserNot")==null)this.put("csrUserNot", "");this.put("csrUser", null);return this;};
		/** not .... */
		public M csrUserNot(){this.put("csrUserNot", "not");return this;};
		public M csrUser$on(SrvUser.M value){
			this.put("SrvUser", value);
			this.put("csrUser$on", value);
			return this;
		};	
		/** and csr_user >= ? */
		public M csrUserMin(Object min){this.put("csrUserMin", min);return this;};
		/** and csr_user <= ? */
		public M csrUserMax(Object max){this.put("csrUserMax", max);return this;};
		/** 物品状态 [非空]       **/
		public M csrItemState(Object csrItemState){this.put("csrItemState", csrItemState);return this;};
		/** and csr_item_state is null */
		public M csrItemStateNull(){if(this.get("csrItemStateNot")==null)this.put("csrItemStateNot", "");this.put("csrItemState", null);return this;};
		/** not .... */
		public M csrItemStateNot(){this.put("csrItemStateNot", "not");return this;};
		/** and csr_item_state >= ? */
		public M csrItemStateMin(Object min){this.put("csrItemStateMin", min);return this;};
		/** and csr_item_state <= ? */
		public M csrItemStateMax(Object max){this.put("csrItemStateMax", max);return this;};
		/** 记录状态 [非空]       **/
		public M csrState(Object csrState){this.put("csrState", csrState);return this;};
		/** and csr_state is null */
		public M csrStateNull(){if(this.get("csrStateNot")==null)this.put("csrStateNot", "");this.put("csrState", null);return this;};
		/** not .... */
		public M csrStateNot(){this.put("csrStateNot", "not");return this;};
		/** and csr_state >= ? */
		public M csrStateMin(Object min){this.put("csrStateMin", min);return this;};
		/** and csr_state <= ? */
		public M csrStateMax(Object max){this.put("csrStateMax", max);return this;};
		/** 备注 [非空]       **/
		public M csrRemark(Object csrRemark){this.put("csrRemark", csrRemark);return this;};
		/** and csr_remark is null */
		public M csrRemarkNull(){if(this.get("csrRemarkNot")==null)this.put("csrRemarkNot", "");this.put("csrRemark", null);return this;};
		/** not .... */
		public M csrRemarkNot(){this.put("csrRemarkNot", "not");return this;};
		/** 创建时间 [非空]       **/
		public M csrAddtime(Object csrAddtime){this.put("csrAddtime", csrAddtime);return this;};
		/** and csr_addtime is null */
		public M csrAddtimeNull(){if(this.get("csrAddtimeNot")==null)this.put("csrAddtimeNot", "");this.put("csrAddtime", null);return this;};
		/** not .... */
		public M csrAddtimeNot(){this.put("csrAddtimeNot", "not");return this;};
		/** and csr_addtime >= ? */
 		public M csrAddtimeStart(Object start){this.put("csrAddtimeStart", start);return this;};			
 		/** and csr_addtime <= ? */
 		public M csrAddtimeEnd(Object end){this.put("csrAddtimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M csrUpdatetime(Object csrUpdatetime){this.put("csrUpdatetime", csrUpdatetime);return this;};
		/** and csr_updatetime is null */
		public M csrUpdatetimeNull(){if(this.get("csrUpdatetimeNot")==null)this.put("csrUpdatetimeNot", "");this.put("csrUpdatetime", null);return this;};
		/** not .... */
		public M csrUpdatetimeNot(){this.put("csrUpdatetimeNot", "not");return this;};
		/** and csr_updatetime >= ? */
 		public M csrUpdatetimeStart(Object start){this.put("csrUpdatetimeStart", start);return this;};			
 		/** and csr_updatetime <= ? */
 		public M csrUpdatetimeEnd(Object end){this.put("csrUpdatetimeEnd", end);return this;};
		/** 数据状态 [非空]       **/
		public M csrStatus(Object csrStatus){this.put("csrStatus", csrStatus);return this;};
		/** and csr_status is null */
		public M csrStatusNull(){if(this.get("csrStatusNot")==null)this.put("csrStatusNot", "");this.put("csrStatus", null);return this;};
		/** not .... */
		public M csrStatusNot(){this.put("csrStatusNot", "not");return this;};
		/** and csr_status >= ? */
		public M csrStatusMin(Object min){this.put("csrStatusMin", min);return this;};
		/** and csr_status <= ? */
		public M csrStatusMax(Object max){this.put("csrStatusMax", max);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有还车检查 **/
		public @api List<CsRetcheck> list(Integer size){
			return getCsRetcheckList(this,size);
		}
		/** 获取还车检查分页 **/
		public @api Page<CsRetcheck> page(int page,int size){
			return getCsRetcheckPage(page, size , this);
		}
		/** 根据查询条件取还车检查 **/
		public @api CsRetcheck get(){
			return getCsRetcheck(this);
		}
		/** 获取还车检查数 **/
		public @api Long count(){
			return getCsRetcheckCount(this);
		}
		/** 获取还车检查表达式 **/
		public @api <T> T eval(String strEval){
			return getCsRetcheckEval(strEval,this);
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
			updateCsRetcheck(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csrId="csrId";
		/** 物品项目 [可空]       **/
		public final static @type(Long.class)  String csrItemId="csrItemId";
		/** 车辆id [可空]       **/
		public final static @type(Long.class)  String csrCar="csrCar";
		/** 物品项名称 [非空]       **/
		public final static @type(String.class)  String csrItemName="csrItemName";
		/** 后勤人员 [非空]       **/
		public final static @type(Long.class)  String csrUser="csrUser";
		/** 物品状态 [非空]       **/
		public final static @type(Short.class)  String csrItemState="csrItemState";
		/** 记录状态 [非空]       **/
		public final static @type(Short.class)  String csrState="csrState";
		/** 备注 [非空]       **/
		public final static @type(String.class)  String csrRemark="csrRemark";
		/** 创建时间 [非空]       **/
		public final static @type(Date.class)  String csrAddtime="csrAddtime";
		/** and csr_addtime >= ? */
 		public final static @type(Date.class) String csrAddtimeStart="csrAddtimeStart";
 		/** and csr_addtime <= ? */
 		public final static @type(Date.class) String csrAddtimeEnd="csrAddtimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csrUpdatetime="csrUpdatetime";
		/** and csr_updatetime >= ? */
 		public final static @type(Date.class) String csrUpdatetimeStart="csrUpdatetimeStart";
 		/** and csr_updatetime <= ? */
 		public final static @type(Date.class) String csrUpdatetimeEnd="csrUpdatetimeEnd";
		/** 数据状态 [非空]       **/
		public final static @type(Short.class)  String csrStatus="csrStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csrId="csr_id";
		/** 物品项目 [可空]       **/
		public final static String csrItemId="csr_item_id";
		/** 车辆id [可空]       **/
		public final static String csrCar="csr_car";
		/** 物品项名称 [非空]       **/
		public final static String csrItemName="csr_item_name";
		/** 后勤人员 [非空]       **/
		public final static String csrUser="csr_user";
		/** 物品状态 [非空]       **/
		public final static String csrItemState="csr_item_state";
		/** 记录状态 [非空]       **/
		public final static String csrState="csr_state";
		/** 备注 [非空]       **/
		public final static String csrRemark="csr_remark";
		/** 创建时间 [非空]       **/
		public final static String csrAddtime="csr_addtime";
		/** 修改时间 [非空]       **/
		public final static String csrUpdatetime="csr_updatetime";
		/** 数据状态 [非空]       **/
		public final static String csrStatus="csr_status";
		
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
				$.Set(name,CsRetcheck.getCsRetcheck(params));
			else
				$.Set(name,CsRetcheck.getCsRetcheckList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取还车检查数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsRetcheck) $.GetRequest("CsRetcheck$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsRetcheck.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsRetcheck.getCsRetcheck(params);
			else
				value = CsRetcheck.getCsRetcheckList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsRetcheck.Get($.add(CsRetcheck.F.csrId,param));
		else if(!$.empty(param.toString()))
			value = CsRetcheck.get(Long.valueOf(param.toString()));
		$.SetRequest("CsRetcheck$"+param.hashCode(), value);
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
	public void mergeSet(CsRetcheck old){
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