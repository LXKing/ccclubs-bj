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
public @caption("保洁记录") @table("cs_clean_record") class CsCleanRecord implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cscr_id")   @primary   @note("  ") Long cscrId;
	private @caption("保洁项目") @column("cscr_item_id")    @relate("$cscrItemId") @RelateClass(SrvProperty.class)  @note(" 保洁项目:'yw_clean' ") Long cscrItemId;
	private SrvProperty $cscrItemId;//关联对象[保洁项目]
	private @caption("车辆id") @column("cscr_car")    @relate("$cscrCar") @RelateClass(CsCar.class)  @note("  ") Long cscrCar;
	private CsCar $cscrCar;//关联对象[车辆id]
	private @caption("保洁项名称") @column("cscr_item_name")      @note("  ") String cscrItemName;
	private @caption("后勤人员") @column("cscr_user")    @relate("$cscrUser") @RelateClass(SrvUser.class)  @note("  ") Long cscrUser;
	private SrvUser $cscrUser;//关联对象[后勤人员]
	private @caption("操作选择") @column("cscr_selection")      @note("0:未完成 1:完成") Short cscrSelection;
	private @caption("备注") @column("cscr_remark")      @note("  ") String cscrRemark;
	private @caption("创建时间") @column("cscr_addtime")      @note("  ") Date cscrAddtime;
	private @caption("修改时间") @column("cscr_updatetime")      @note("  ") Date cscrUpdatetime;
	private @caption("状态") @column("cscr_status")      @note("0:无效 1:有效") Short cscrStatus;
	
	//默认构造函数
	public CsCleanRecord(){
	
	}
	
	//主键构造函数
	public CsCleanRecord(Long id){
		this.cscrId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsCleanRecord(Long cscrItemId,Long cscrCar,String cscrItemName,Long cscrUser,Short cscrSelection,String cscrRemark,Date cscrAddtime,Date cscrUpdatetime,Short cscrStatus){
		this.cscrItemId=cscrItemId;
		this.cscrCar=cscrCar;
		this.cscrItemName=cscrItemName;
		this.cscrUser=cscrUser;
		this.cscrSelection=cscrSelection;
		this.cscrRemark=cscrRemark;
		this.cscrAddtime=cscrAddtime;
		this.cscrUpdatetime=cscrUpdatetime;
		this.cscrStatus=cscrStatus;
	}
	
	//设置非空字段
	public CsCleanRecord setNotNull(Long cscrItemId,Long cscrCar){
		this.cscrItemId=cscrItemId;
		this.cscrCar=cscrCar;
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsCleanRecord cscrId(Long cscrId){
		this.cscrId = cscrId;
		this.setSeted(F.cscrId);
		return this;
	}
	/** 保洁项目 [可空]       **/
	public CsCleanRecord cscrItemId(Long cscrItemId){
		this.cscrItemId = cscrItemId;
		this.setSeted(F.cscrItemId);
		return this;
	}
	/** 车辆id [可空]       **/
	public CsCleanRecord cscrCar(Long cscrCar){
		this.cscrCar = cscrCar;
		this.setSeted(F.cscrCar);
		return this;
	}
	/** 保洁项名称 [非空]       **/
	public CsCleanRecord cscrItemName(String cscrItemName){
		this.cscrItemName = cscrItemName;
		this.setSeted(F.cscrItemName);
		return this;
	}
	/** 后勤人员 [非空]       **/
	public CsCleanRecord cscrUser(Long cscrUser){
		this.cscrUser = cscrUser;
		this.setSeted(F.cscrUser);
		return this;
	}
	/** 操作选择 [非空]       **/
	public CsCleanRecord cscrSelection(Short cscrSelection){
		this.cscrSelection = cscrSelection;
		this.setSeted(F.cscrSelection);
		return this;
	}
	/** 备注 [非空]       **/
	public CsCleanRecord cscrRemark(String cscrRemark){
		this.cscrRemark = cscrRemark;
		this.setSeted(F.cscrRemark);
		return this;
	}
	/** 创建时间 [非空]       **/
	public CsCleanRecord cscrAddtime(Date cscrAddtime){
		this.cscrAddtime = cscrAddtime;
		this.setSeted(F.cscrAddtime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsCleanRecord cscrUpdatetime(Date cscrUpdatetime){
		this.cscrUpdatetime = cscrUpdatetime;
		this.setSeted(F.cscrUpdatetime);
		return this;
	}
	/** 状态 [非空]       **/
	public CsCleanRecord cscrStatus(Short cscrStatus){
		this.cscrStatus = cscrStatus;
		this.setSeted(F.cscrStatus);
		return this;
	}
	
	//克隆对象
	public CsCleanRecord clone(){
		CsCleanRecord clone = new CsCleanRecord();
		clone.cscrId=this.cscrId;
		clone.cscrItemId=this.cscrItemId;
		clone.cscrCar=this.cscrCar;
		clone.cscrItemName=this.cscrItemName;
		clone.cscrUser=this.cscrUser;
		clone.cscrSelection=this.cscrSelection;
		clone.cscrRemark=this.cscrRemark;
		clone.cscrAddtime=this.cscrAddtime;
		clone.cscrUpdatetime=this.cscrUpdatetime;
		clone.cscrStatus=this.cscrStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取保洁记录
	 * @param id
	 * @return
	 */
	public static @api CsCleanRecord get(Long id){		
		return getCsCleanRecordById(id);
	}
	/**
	 * 获取所有保洁记录
	 * @return
	 */
	public static @api List<CsCleanRecord> list(Map params,Integer size){
		return getCsCleanRecordList(params,size);
	}
	/**
	 * 获取保洁记录分页
	 * @return
	 */
	public static @api Page<CsCleanRecord> page(int page,int size,Map params){
		return getCsCleanRecordPage(page, size , params);
	}
	/**
	 * 根据查询条件取保洁记录
	 * @param params
	 * @return
	 */
	public static @api CsCleanRecord Get(Map params){
		return getCsCleanRecord(params);
	}
	/**
	 * 获取保洁记录数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsCleanRecordCount(params);
	}
	/**
	 * 获取保洁记录数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsCleanRecordEval(eval,params);
	}
	
	/**
	 * 根据ID取保洁记录
	 * @param id
	 * @return
	 */
	public static @api CsCleanRecord getCsCleanRecordById(Long id){		
		CsCleanRecord csCleanRecord = (CsCleanRecord) $.GetRequest("CsCleanRecord$"+id);
		if(csCleanRecord!=null)
			return csCleanRecord;
		com.ccclubs.service.admin.ICsCleanRecordService csCleanRecordService = $.GetSpringBean("csCleanRecordService");		
		return csCleanRecordService.getCsCleanRecordById(id);
	}
	
	
	/**
	 * 根据ID取保洁记录的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsCleanRecord.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsCleanRecord csCleanRecord = get(id);
		if(csCleanRecord!=null){
			String strValue = csCleanRecord.getCscrId$();
			if(!"CscrId".equals("CscrId"))
				strValue+="("+csCleanRecord.getCscrId$()+")";
			MemCache.setValue(CsCleanRecord.class, id ,strValue);
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
	 * 获取所有保洁记录
	 * @return
	 */
	public static @api List<CsCleanRecord> getCsCleanRecordList(Map params,Integer size){
		com.ccclubs.service.admin.ICsCleanRecordService csCleanRecordService = $.GetSpringBean("csCleanRecordService");
		return csCleanRecordService.getCsCleanRecordList(params, size);
	}
	
	/**
	 * 获取保洁记录分页
	 * @return
	 */
	public static @api Page<CsCleanRecord> getCsCleanRecordPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsCleanRecordService csCleanRecordService = $.GetSpringBean("csCleanRecordService");
		return csCleanRecordService.getCsCleanRecordPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取保洁记录
	 * @param params
	 * @return
	 */
	public static @api CsCleanRecord getCsCleanRecord(Map params){
		com.ccclubs.service.admin.ICsCleanRecordService csCleanRecordService = $.GetSpringBean("csCleanRecordService");
		return csCleanRecordService.getCsCleanRecord(params);
	}
	
	/**
	 * 获取保洁记录数
	 * @return
	 */
	public static @api Long getCsCleanRecordCount(Map params){
		com.ccclubs.service.admin.ICsCleanRecordService csCleanRecordService = $.GetSpringBean("csCleanRecordService");
		return csCleanRecordService.getCsCleanRecordCount(params);
	}
		
		
	/**
	 * 获取保洁记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsCleanRecordEval(String eval,Map params){
		com.ccclubs.service.admin.ICsCleanRecordService csCleanRecordService = $.GetSpringBean("csCleanRecordService");
		return csCleanRecordService.getCsCleanRecordEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsCleanRecord(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsCleanRecordService csCleanRecordService = $.GetSpringBean("csCleanRecordService");
		csCleanRecordService.updateCsCleanRecordByConfirm(set, where);
	}
	
	
	/**
	 * 保存保洁记录对象
	 * @param params
	 * @return
	 */
	public CsCleanRecord save(){
		com.ccclubs.service.admin.ICsCleanRecordService csCleanRecordService = $.GetSpringBean("csCleanRecordService");
		if(this.getCscrId()!=null)
			csCleanRecordService.updateCsCleanRecord(this);
		else
			return csCleanRecordService.saveCsCleanRecord(this);
		return this;
	}
	
	
	/**
	 * 更新保洁记录对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsCleanRecordService csCleanRecordService = $.GetSpringBean("csCleanRecordService");
		csCleanRecordService.updateCsCleanRecord$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsCleanRecordService csCleanRecordService = $.GetSpringBean("csCleanRecordService");
		if($.equals($.config("logic_delete"),"true"))
			csCleanRecordService.removeCsCleanRecordById(this.getCscrId());
		else
			csCleanRecordService.deleteCsCleanRecordById(this.getCscrId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsCleanRecordService csCleanRecordService = $.GetSpringBean("csCleanRecordService");
		return csCleanRecordService.executeTransaction(function);
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
	/*******************************保洁项目**********************************/	
	/**
	* 保洁项目 [可空]      
	**/
	public Long getCscrItemId(){
		return this.cscrItemId;
	}
	/**
	* 获取保洁项目格式化(toString)
	**/
	public String getCscrItemId$(){
		String strValue="";
		if(this.getCscrItemId()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCscrItemId()));
		}			
	 	 return strValue;
	}
	/**
	* 保洁项目 [可空]     
	**/
	public void setCscrItemId(Long cscrItemId){
		this.cscrItemId = cscrItemId;
		this.setSeted(F.cscrItemId);
	}
	/**
	* 获取关联对象[保洁项目]
	**/	 			
 	public SrvProperty get$cscrItemId(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCscrItemId());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCscrItemId()!=null){
			srvProperty = SrvProperty.get(this.getCscrItemId());
 		}
 		$.SetRequest("SrvProperty$"+this.getCscrItemId(), srvProperty);
	 	return srvProperty;
	}
	/*******************************车辆id**********************************/	
	/**
	* 车辆id [可空]      
	**/
	public Long getCscrCar(){
		return this.cscrCar;
	}
	/**
	* 获取车辆id格式化(toString)
	**/
	public String getCscrCar$(){
		String strValue="";
		if(this.getCscrCar()!=null){
				strValue+=$.str(CsCar.getKeyValue(this.getCscrCar()));
		}			
	 	 return strValue;
	}
	/**
	* 车辆id [可空]     
	**/
	public void setCscrCar(Long cscrCar){
		this.cscrCar = cscrCar;
		this.setSeted(F.cscrCar);
	}
	/**
	* 获取关联对象[车辆id]
	**/	 			
 	public CsCar get$cscrCar(){
 		com.ccclubs.model.CsCar csCar = (com.ccclubs.model.CsCar) $.GetRequest("CsCar$"+this.getCscrCar());
 		if(csCar!=null)
			return csCar;
		if(this.getCscrCar()!=null){
			csCar = CsCar.get(this.getCscrCar());
 		}
 		$.SetRequest("CsCar$"+this.getCscrCar(), csCar);
	 	return csCar;
	}
	/*******************************保洁项名称**********************************/	
	/**
	* 保洁项名称 [非空]      
	**/
	public String getCscrItemName(){
		return this.cscrItemName;
	}
	/**
	* 获取保洁项名称格式化(toString)
	**/
	public String getCscrItemName$(){
		String strValue="";
		 strValue=$.str(this.getCscrItemName());
	 	 return strValue;
	}
	/**
	* 保洁项名称 [非空]     
	**/
	public void setCscrItemName(String cscrItemName){
		this.cscrItemName = cscrItemName;
		this.setSeted(F.cscrItemName);
	}
	/*******************************后勤人员**********************************/	
	/**
	* 后勤人员 [非空]      
	**/
	public Long getCscrUser(){
		return this.cscrUser;
	}
	/**
	* 获取后勤人员格式化(toString)
	**/
	public String getCscrUser$(){
		String strValue="";
		if(this.getCscrUser()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCscrUser()));
		}			
	 	 return strValue;
	}
	/**
	* 后勤人员 [非空]     
	**/
	public void setCscrUser(Long cscrUser){
		this.cscrUser = cscrUser;
		this.setSeted(F.cscrUser);
	}
	/**
	* 获取关联对象[后勤人员]
	**/	 			
 	public SrvUser get$cscrUser(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCscrUser());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCscrUser()!=null){
			srvUser = SrvUser.get(this.getCscrUser());
 		}
 		$.SetRequest("SrvUser$"+this.getCscrUser(), srvUser);
	 	return srvUser;
	}
	/*******************************操作选择**********************************/	
	/**
	* 操作选择 [非空]      
	**/
	public Short getCscrSelection(){
		return this.cscrSelection;
	}
	/**
	* 获取操作选择格式化(toString)
	**/
	public String getCscrSelection$(){
		String strValue="";
		 if($.equals($.str(this.getCscrSelection()),"1"))
			 strValue=$.str("完成");
		 if($.equals($.str(this.getCscrSelection()),"0"))
			 strValue=$.str("未完成");
	 	 return strValue;
	}
	/**
	* 操作选择 [非空]     
	**/
	public void setCscrSelection(Short cscrSelection){
		this.cscrSelection = cscrSelection;
		this.setSeted(F.cscrSelection);
	}
	/*******************************备注**********************************/	
	/**
	* 备注 [非空]      
	**/
	public String getCscrRemark(){
		return this.cscrRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCscrRemark$(){
		String strValue="";
		 strValue=$.str(this.getCscrRemark());
	 	 return strValue;
	}
	/**
	* 备注 [非空]     
	**/
	public void setCscrRemark(String cscrRemark){
		this.cscrRemark = cscrRemark;
		this.setSeted(F.cscrRemark);
	}
	/*******************************创建时间**********************************/	
	/**
	* 创建时间 [非空]      
	**/
	public Date getCscrAddtime(){
		return this.cscrAddtime;
	}
	/**
	* 获取创建时间格式化(toString)
	**/
	public String getCscrAddtime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscrAddtime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 创建时间 [非空]     
	**/
	public void setCscrAddtime(Date cscrAddtime){
		this.cscrAddtime = cscrAddtime;
		this.setSeted(F.cscrAddtime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCscrUpdatetime(){
		return this.cscrUpdatetime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCscrUpdatetime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscrUpdatetime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCscrUpdatetime(Date cscrUpdatetime){
		this.cscrUpdatetime = cscrUpdatetime;
		this.setSeted(F.cscrUpdatetime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]      
	**/
	public Short getCscrStatus(){
		return this.cscrStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCscrStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCscrStatus()),"1"))
			 strValue=$.str("有效");
		 if($.equals($.str(this.getCscrStatus()),"0"))
			 strValue=$.str("无效");
	 	 return strValue;
	}
	/**
	* 状态 [非空]     
	**/
	public void setCscrStatus(Short cscrStatus){
		this.cscrStatus = cscrStatus;
		this.setSeted(F.cscrStatus);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsCleanRecord.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCleanRecord.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCleanRecord.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsCleanRecord.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsCleanRecord.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCleanRecord.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCleanRecord.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsCleanRecord.this);
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
		/** and cscr_id >= ? */
		public M cscrIdMin(Object min){this.put("cscrIdMin", min);return this;};
		/** and cscr_id <= ? */
		public M cscrIdMax(Object max){this.put("cscrIdMax", max);return this;};
		/** 保洁项目 [可空]       **/
		public M cscrItemId(Object cscrItemId){this.put("cscrItemId", cscrItemId);return this;};
		/** and cscr_item_id is null */
		public M cscrItemIdNull(){if(this.get("cscrItemIdNot")==null)this.put("cscrItemIdNot", "");this.put("cscrItemId", null);return this;};
		/** not .... */
		public M cscrItemIdNot(){this.put("cscrItemIdNot", "not");return this;};
		public M cscrItemId$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("cscrItemId$on", value);
			return this;
		};	
		/** and cscr_item_id >= ? */
		public M cscrItemIdMin(Object min){this.put("cscrItemIdMin", min);return this;};
		/** and cscr_item_id <= ? */
		public M cscrItemIdMax(Object max){this.put("cscrItemIdMax", max);return this;};
		/** 车辆id [可空]       **/
		public M cscrCar(Object cscrCar){this.put("cscrCar", cscrCar);return this;};
		/** and cscr_car is null */
		public M cscrCarNull(){if(this.get("cscrCarNot")==null)this.put("cscrCarNot", "");this.put("cscrCar", null);return this;};
		/** not .... */
		public M cscrCarNot(){this.put("cscrCarNot", "not");return this;};
		public M cscrCar$on(CsCar.M value){
			this.put("CsCar", value);
			this.put("cscrCar$on", value);
			return this;
		};	
		/** and cscr_car >= ? */
		public M cscrCarMin(Object min){this.put("cscrCarMin", min);return this;};
		/** and cscr_car <= ? */
		public M cscrCarMax(Object max){this.put("cscrCarMax", max);return this;};
		/** 保洁项名称 [非空]       **/
		public M cscrItemName(Object cscrItemName){this.put("cscrItemName", cscrItemName);return this;};
		/** and cscr_item_name is null */
		public M cscrItemNameNull(){if(this.get("cscrItemNameNot")==null)this.put("cscrItemNameNot", "");this.put("cscrItemName", null);return this;};
		/** not .... */
		public M cscrItemNameNot(){this.put("cscrItemNameNot", "not");return this;};
		/** 后勤人员 [非空]       **/
		public M cscrUser(Object cscrUser){this.put("cscrUser", cscrUser);return this;};
		/** and cscr_user is null */
		public M cscrUserNull(){if(this.get("cscrUserNot")==null)this.put("cscrUserNot", "");this.put("cscrUser", null);return this;};
		/** not .... */
		public M cscrUserNot(){this.put("cscrUserNot", "not");return this;};
		public M cscrUser$on(SrvUser.M value){
			this.put("SrvUser", value);
			this.put("cscrUser$on", value);
			return this;
		};	
		/** and cscr_user >= ? */
		public M cscrUserMin(Object min){this.put("cscrUserMin", min);return this;};
		/** and cscr_user <= ? */
		public M cscrUserMax(Object max){this.put("cscrUserMax", max);return this;};
		/** 操作选择 [非空]       **/
		public M cscrSelection(Object cscrSelection){this.put("cscrSelection", cscrSelection);return this;};
		/** and cscr_selection is null */
		public M cscrSelectionNull(){if(this.get("cscrSelectionNot")==null)this.put("cscrSelectionNot", "");this.put("cscrSelection", null);return this;};
		/** not .... */
		public M cscrSelectionNot(){this.put("cscrSelectionNot", "not");return this;};
		/** and cscr_selection >= ? */
		public M cscrSelectionMin(Object min){this.put("cscrSelectionMin", min);return this;};
		/** and cscr_selection <= ? */
		public M cscrSelectionMax(Object max){this.put("cscrSelectionMax", max);return this;};
		/** 备注 [非空]       **/
		public M cscrRemark(Object cscrRemark){this.put("cscrRemark", cscrRemark);return this;};
		/** and cscr_remark is null */
		public M cscrRemarkNull(){if(this.get("cscrRemarkNot")==null)this.put("cscrRemarkNot", "");this.put("cscrRemark", null);return this;};
		/** not .... */
		public M cscrRemarkNot(){this.put("cscrRemarkNot", "not");return this;};
		/** 创建时间 [非空]       **/
		public M cscrAddtime(Object cscrAddtime){this.put("cscrAddtime", cscrAddtime);return this;};
		/** and cscr_addtime is null */
		public M cscrAddtimeNull(){if(this.get("cscrAddtimeNot")==null)this.put("cscrAddtimeNot", "");this.put("cscrAddtime", null);return this;};
		/** not .... */
		public M cscrAddtimeNot(){this.put("cscrAddtimeNot", "not");return this;};
		/** and cscr_addtime >= ? */
 		public M cscrAddtimeStart(Object start){this.put("cscrAddtimeStart", start);return this;};			
 		/** and cscr_addtime <= ? */
 		public M cscrAddtimeEnd(Object end){this.put("cscrAddtimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M cscrUpdatetime(Object cscrUpdatetime){this.put("cscrUpdatetime", cscrUpdatetime);return this;};
		/** and cscr_updatetime is null */
		public M cscrUpdatetimeNull(){if(this.get("cscrUpdatetimeNot")==null)this.put("cscrUpdatetimeNot", "");this.put("cscrUpdatetime", null);return this;};
		/** not .... */
		public M cscrUpdatetimeNot(){this.put("cscrUpdatetimeNot", "not");return this;};
		/** and cscr_updatetime >= ? */
 		public M cscrUpdatetimeStart(Object start){this.put("cscrUpdatetimeStart", start);return this;};			
 		/** and cscr_updatetime <= ? */
 		public M cscrUpdatetimeEnd(Object end){this.put("cscrUpdatetimeEnd", end);return this;};
		/** 状态 [非空]       **/
		public M cscrStatus(Object cscrStatus){this.put("cscrStatus", cscrStatus);return this;};
		/** and cscr_status is null */
		public M cscrStatusNull(){if(this.get("cscrStatusNot")==null)this.put("cscrStatusNot", "");this.put("cscrStatus", null);return this;};
		/** not .... */
		public M cscrStatusNot(){this.put("cscrStatusNot", "not");return this;};
		/** and cscr_status >= ? */
		public M cscrStatusMin(Object min){this.put("cscrStatusMin", min);return this;};
		/** and cscr_status <= ? */
		public M cscrStatusMax(Object max){this.put("cscrStatusMax", max);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有保洁记录 **/
		public @api List<CsCleanRecord> list(Integer size){
			return getCsCleanRecordList(this,size);
		}
		/** 获取保洁记录分页 **/
		public @api Page<CsCleanRecord> page(int page,int size){
			return getCsCleanRecordPage(page, size , this);
		}
		/** 根据查询条件取保洁记录 **/
		public @api CsCleanRecord get(){
			return getCsCleanRecord(this);
		}
		/** 获取保洁记录数 **/
		public @api Long count(){
			return getCsCleanRecordCount(this);
		}
		/** 获取保洁记录表达式 **/
		public @api <T> T eval(String strEval){
			return getCsCleanRecordEval(strEval,this);
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
			updateCsCleanRecord(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscrId="cscrId";
		/** 保洁项目 [可空]       **/
		public final static @type(Long.class)  String cscrItemId="cscrItemId";
		/** 车辆id [可空]       **/
		public final static @type(Long.class)  String cscrCar="cscrCar";
		/** 保洁项名称 [非空]       **/
		public final static @type(String.class)  String cscrItemName="cscrItemName";
		/** 后勤人员 [非空]       **/
		public final static @type(Long.class)  String cscrUser="cscrUser";
		/** 操作选择 [非空]       **/
		public final static @type(Short.class)  String cscrSelection="cscrSelection";
		/** 备注 [非空]       **/
		public final static @type(String.class)  String cscrRemark="cscrRemark";
		/** 创建时间 [非空]       **/
		public final static @type(Date.class)  String cscrAddtime="cscrAddtime";
		/** and cscr_addtime >= ? */
 		public final static @type(Date.class) String cscrAddtimeStart="cscrAddtimeStart";
 		/** and cscr_addtime <= ? */
 		public final static @type(Date.class) String cscrAddtimeEnd="cscrAddtimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cscrUpdatetime="cscrUpdatetime";
		/** and cscr_updatetime >= ? */
 		public final static @type(Date.class) String cscrUpdatetimeStart="cscrUpdatetimeStart";
 		/** and cscr_updatetime <= ? */
 		public final static @type(Date.class) String cscrUpdatetimeEnd="cscrUpdatetimeEnd";
		/** 状态 [非空]       **/
		public final static @type(Short.class)  String cscrStatus="cscrStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscrId="cscr_id";
		/** 保洁项目 [可空]       **/
		public final static String cscrItemId="cscr_item_id";
		/** 车辆id [可空]       **/
		public final static String cscrCar="cscr_car";
		/** 保洁项名称 [非空]       **/
		public final static String cscrItemName="cscr_item_name";
		/** 后勤人员 [非空]       **/
		public final static String cscrUser="cscr_user";
		/** 操作选择 [非空]       **/
		public final static String cscrSelection="cscr_selection";
		/** 备注 [非空]       **/
		public final static String cscrRemark="cscr_remark";
		/** 创建时间 [非空]       **/
		public final static String cscrAddtime="cscr_addtime";
		/** 修改时间 [非空]       **/
		public final static String cscrUpdatetime="cscr_updatetime";
		/** 状态 [非空]       **/
		public final static String cscrStatus="cscr_status";
		
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
				$.Set(name,CsCleanRecord.getCsCleanRecord(params));
			else
				$.Set(name,CsCleanRecord.getCsCleanRecordList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取保洁记录数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsCleanRecord) $.GetRequest("CsCleanRecord$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsCleanRecord.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsCleanRecord.getCsCleanRecord(params);
			else
				value = CsCleanRecord.getCsCleanRecordList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsCleanRecord.Get($.add(CsCleanRecord.F.cscrId,param));
		else if(!$.empty(param.toString()))
			value = CsCleanRecord.get(Long.valueOf(param.toString()));
		$.SetRequest("CsCleanRecord$"+param.hashCode(), value);
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
	public void mergeSet(CsCleanRecord old){
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