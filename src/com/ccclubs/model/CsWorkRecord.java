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

@namespace("object/serv")
public @caption("后勤工作记录") @table("cs_work_record") class CsWorkRecord implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cswr_id")   @primary   @note("  ") Long cswrId;
	private @caption("所在城市") @column("cswr_host")    @relate("$cswrHost") @RelateClass(SrvHost.class)  @note("  ") Long cswrHost;
	private SrvHost $cswrHost;//关联对象[所在城市]
	private @caption("网点") @column("cswr_outlets")    @relate("$cswrOutlets") @RelateClass(CsOutlets.class)  @note("  ") Long cswrOutlets;
	private CsOutlets $cswrOutlets;//关联对象[网点]
	private @caption("作业车辆") @column("cswr_car")    @relate("$cswrCar") @RelateClass(CsCar.class)  @note("  ") Long cswrCar;
	private CsCar $cswrCar;//关联对象[作业车辆]
	private @caption("人员") @column("cswr_user")    @relate("$cswrUser") @RelateClass(SrvUser.class)  @note("  ") Long cswrUser;
	private SrvUser $cswrUser;//关联对象[人员]
	private @caption("作业项目") @column("cswr_item")    @relate("$cswrItem") @RelateClass(SrvProperty.class)  @note("  ") Long cswrItem;
	private SrvProperty $cswrItem;//关联对象[作业项目]
	private @caption("作业项目名称") @column("cswr_itemname")      @note("  ") String cswrItemname;
	private @caption("远程控制") @column("cswr_operation")      @note("  ") String cswrOperation;
	private @caption("其它数据") @column("cswr_data")      @note("  ") String cswrData;
	private @caption("状态") @column("cswr_status")      @note(" 0:无效 1:未确认 2:已确认  ") Short cswrStatus;
	private @caption("创建时间") @column("cswr_add_time")      @note("  ") Date cswrAddTime;
	private @caption("修改时间") @column("cswr_update_time")      @note("  ") Date cswrUpdateTime;
	
	//默认构造函数
	public CsWorkRecord(){
	
	}
	
	//主键构造函数
	public CsWorkRecord(Long id){
		this.cswrId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsWorkRecord(Long cswrHost,Long cswrOutlets,Long cswrCar,Long cswrUser,Long cswrItem,String cswrItemname,String cswrOperation,String cswrData,Short cswrStatus,Date cswrAddTime,Date cswrUpdateTime){
		this.cswrHost=cswrHost;
		this.cswrOutlets=cswrOutlets;
		this.cswrCar=cswrCar;
		this.cswrUser=cswrUser;
		this.cswrItem=cswrItem;
		this.cswrItemname=cswrItemname;
		this.cswrOperation=cswrOperation;
		this.cswrData=cswrData;
		this.cswrStatus=cswrStatus;
		this.cswrAddTime=cswrAddTime;
		this.cswrUpdateTime=cswrUpdateTime;
	}
	
	//设置非空字段
	public CsWorkRecord setNotNull(Long cswrItem,String cswrItemname){
		this.cswrItem=cswrItem;
		this.cswrItemname=cswrItemname;
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsWorkRecord cswrId(Long cswrId){
		this.cswrId = cswrId;
		this.setSeted(F.cswrId);
		return this;
	}
	/** 所在城市 [非空]       **/
	public CsWorkRecord cswrHost(Long cswrHost){
		this.cswrHost = cswrHost;
		this.setSeted(F.cswrHost);
		return this;
	}
	/** 网点 [非空]       **/
	public CsWorkRecord cswrOutlets(Long cswrOutlets){
		this.cswrOutlets = cswrOutlets;
		this.setSeted(F.cswrOutlets);
		return this;
	}
	/** 作业车辆 [非空]       **/
	public CsWorkRecord cswrCar(Long cswrCar){
		this.cswrCar = cswrCar;
		this.setSeted(F.cswrCar);
		return this;
	}
	/** 人员 [非空]       **/
	public CsWorkRecord cswrUser(Long cswrUser){
		this.cswrUser = cswrUser;
		this.setSeted(F.cswrUser);
		return this;
	}
	/** 作业项目 [可空]       **/
	public CsWorkRecord cswrItem(Long cswrItem){
		this.cswrItem = cswrItem;
		this.setSeted(F.cswrItem);
		return this;
	}
	/** 作业项目名称 [可空]       **/
	public CsWorkRecord cswrItemname(String cswrItemname){
		this.cswrItemname = cswrItemname;
		this.setSeted(F.cswrItemname);
		return this;
	}
	/** 远程控制 [非空]       **/
	public CsWorkRecord cswrOperation(String cswrOperation){
		this.cswrOperation = cswrOperation;
		this.setSeted(F.cswrOperation);
		return this;
	}
	/** 其它数据 [非空]       **/
	public CsWorkRecord cswrData(String cswrData){
		this.cswrData = cswrData;
		this.setSeted(F.cswrData);
		return this;
	}
	/** 状态 [非空]       **/
	public CsWorkRecord cswrStatus(Short cswrStatus){
		this.cswrStatus = cswrStatus;
		this.setSeted(F.cswrStatus);
		return this;
	}
	/** 创建时间 [非空]       **/
	public CsWorkRecord cswrAddTime(Date cswrAddTime){
		this.cswrAddTime = cswrAddTime;
		this.setSeted(F.cswrAddTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsWorkRecord cswrUpdateTime(Date cswrUpdateTime){
		this.cswrUpdateTime = cswrUpdateTime;
		this.setSeted(F.cswrUpdateTime);
		return this;
	}
	
	//克隆对象
	public CsWorkRecord clone(){
		CsWorkRecord clone = new CsWorkRecord();
		clone.cswrId=this.cswrId;
		clone.cswrHost=this.cswrHost;
		clone.cswrOutlets=this.cswrOutlets;
		clone.cswrCar=this.cswrCar;
		clone.cswrUser=this.cswrUser;
		clone.cswrItem=this.cswrItem;
		clone.cswrItemname=this.cswrItemname;
		clone.cswrOperation=this.cswrOperation;
		clone.cswrData=this.cswrData;
		clone.cswrStatus=this.cswrStatus;
		clone.cswrAddTime=this.cswrAddTime;
		clone.cswrUpdateTime=this.cswrUpdateTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取后勤工作记录
	 * @param id
	 * @return
	 */
	public static @api CsWorkRecord get(Long id){		
		return getCsWorkRecordById(id);
	}
	/**
	 * 获取所有后勤工作记录
	 * @return
	 */
	public static @api List<CsWorkRecord> list(Map params,Integer size){
		return getCsWorkRecordList(params,size);
	}
	/**
	 * 获取后勤工作记录分页
	 * @return
	 */
	public static @api Page<CsWorkRecord> page(int page,int size,Map params){
		return getCsWorkRecordPage(page, size , params);
	}
	/**
	 * 根据查询条件取后勤工作记录
	 * @param params
	 * @return
	 */
	public static @api CsWorkRecord Get(Map params){
		return getCsWorkRecord(params);
	}
	/**
	 * 获取后勤工作记录数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsWorkRecordCount(params);
	}
	/**
	 * 获取后勤工作记录数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsWorkRecordEval(eval,params);
	}
	
	/**
	 * 根据ID取后勤工作记录
	 * @param id
	 * @return
	 */
	public static @api CsWorkRecord getCsWorkRecordById(Long id){		
		CsWorkRecord csWorkRecord = (CsWorkRecord) $.GetRequest("CsWorkRecord$"+id);
		if(csWorkRecord!=null)
			return csWorkRecord;
		com.ccclubs.service.admin.ICsWorkRecordService csWorkRecordService = $.GetSpringBean("csWorkRecordService");		
		return csWorkRecordService.getCsWorkRecordById(id);
	}
	
	
	/**
	 * 根据ID取后勤工作记录的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsWorkRecord.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsWorkRecord csWorkRecord = get(id);
		if(csWorkRecord!=null){
			String strValue = csWorkRecord.getCswrId$();
			if(!"CswrId".equals("CswrId"))
				strValue+="("+csWorkRecord.getCswrId$()+")";
			MemCache.setValue(CsWorkRecord.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCswrId$();
		if(!"CswrId".equals("CswrId"))
			keyValue+="("+this.getCswrId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有后勤工作记录
	 * @return
	 */
	public static @api List<CsWorkRecord> getCsWorkRecordList(Map params,Integer size){
		com.ccclubs.service.admin.ICsWorkRecordService csWorkRecordService = $.GetSpringBean("csWorkRecordService");
		return csWorkRecordService.getCsWorkRecordList(params, size);
	}
	
	/**
	 * 获取后勤工作记录分页
	 * @return
	 */
	public static @api Page<CsWorkRecord> getCsWorkRecordPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsWorkRecordService csWorkRecordService = $.GetSpringBean("csWorkRecordService");
		return csWorkRecordService.getCsWorkRecordPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取后勤工作记录
	 * @param params
	 * @return
	 */
	public static @api CsWorkRecord getCsWorkRecord(Map params){
		com.ccclubs.service.admin.ICsWorkRecordService csWorkRecordService = $.GetSpringBean("csWorkRecordService");
		return csWorkRecordService.getCsWorkRecord(params);
	}
	
	/**
	 * 获取后勤工作记录数
	 * @return
	 */
	public static @api Long getCsWorkRecordCount(Map params){
		com.ccclubs.service.admin.ICsWorkRecordService csWorkRecordService = $.GetSpringBean("csWorkRecordService");
		return csWorkRecordService.getCsWorkRecordCount(params);
	}
		
		
	/**
	 * 获取后勤工作记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsWorkRecordEval(String eval,Map params){
		com.ccclubs.service.admin.ICsWorkRecordService csWorkRecordService = $.GetSpringBean("csWorkRecordService");
		return csWorkRecordService.getCsWorkRecordEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsWorkRecord(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsWorkRecordService csWorkRecordService = $.GetSpringBean("csWorkRecordService");
		csWorkRecordService.updateCsWorkRecordByConfirm(set, where);
	}
	
	
	/**
	 * 保存后勤工作记录对象
	 * @param params
	 * @return
	 */
	public CsWorkRecord save(){
		com.ccclubs.service.admin.ICsWorkRecordService csWorkRecordService = $.GetSpringBean("csWorkRecordService");
		if(this.getCswrId()!=null)
			csWorkRecordService.updateCsWorkRecord(this);
		else
			return csWorkRecordService.saveCsWorkRecord(this);
		return this;
	}
	
	
	/**
	 * 更新后勤工作记录对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsWorkRecordService csWorkRecordService = $.GetSpringBean("csWorkRecordService");
		csWorkRecordService.updateCsWorkRecord$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsWorkRecordService csWorkRecordService = $.GetSpringBean("csWorkRecordService");
		if($.equals($.config("logic_delete"),"true"))
			csWorkRecordService.removeCsWorkRecordById(this.getCswrId());
		else
			csWorkRecordService.deleteCsWorkRecordById(this.getCswrId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsWorkRecordService csWorkRecordService = $.GetSpringBean("csWorkRecordService");
		return csWorkRecordService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCswrId(){
		return this.cswrId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCswrId$(){
		String strValue="";
		 strValue=$.str(this.getCswrId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCswrId(Long cswrId){
		this.cswrId = cswrId;
		this.setSeted(F.cswrId);
	}
	/*******************************所在城市**********************************/	
	/**
	* 所在城市 [非空]      
	**/
	public Long getCswrHost(){
		return this.cswrHost;
	}
	/**
	* 获取所在城市格式化(toString)
	**/
	public String getCswrHost$(){
		String strValue="";
		if(this.getCswrHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCswrHost()));
		}			
	 	 return strValue;
	}
	/**
	* 所在城市 [非空]     
	**/
	public void setCswrHost(Long cswrHost){
		this.cswrHost = cswrHost;
		this.setSeted(F.cswrHost);
	}
	/**
	* 获取关联对象[所在城市]
	**/	 			
 	public SrvHost get$cswrHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCswrHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCswrHost()!=null){
			srvHost = SrvHost.get(this.getCswrHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCswrHost(), srvHost);
	 	return srvHost;
	}
	/*******************************网点**********************************/	
	/**
	* 网点 [非空]      
	**/
	public Long getCswrOutlets(){
		return this.cswrOutlets;
	}
	/**
	* 获取网点格式化(toString)
	**/
	public String getCswrOutlets$(){
		String strValue="";
		if(this.getCswrOutlets()!=null){
				strValue+=$.str(CsOutlets.getKeyValue(this.getCswrOutlets()));
		}			
	 	 return strValue;
	}
	/**
	* 网点 [非空]     
	**/
	public void setCswrOutlets(Long cswrOutlets){
		this.cswrOutlets = cswrOutlets;
		this.setSeted(F.cswrOutlets);
	}
	/**
	* 获取关联对象[网点]
	**/	 			
 	public CsOutlets get$cswrOutlets(){
 		com.ccclubs.model.CsOutlets csOutlets = (com.ccclubs.model.CsOutlets) $.GetRequest("CsOutlets$"+this.getCswrOutlets());
 		if(csOutlets!=null)
			return csOutlets;
		if(this.getCswrOutlets()!=null){
			csOutlets = CsOutlets.get(this.getCswrOutlets());
 		}
 		$.SetRequest("CsOutlets$"+this.getCswrOutlets(), csOutlets);
	 	return csOutlets;
	}
	/*******************************作业车辆**********************************/	
	/**
	* 作业车辆 [非空]      
	**/
	public Long getCswrCar(){
		return this.cswrCar;
	}
	/**
	* 获取作业车辆格式化(toString)
	**/
	public String getCswrCar$(){
		String strValue="";
		if(this.getCswrCar()!=null){
				strValue+=$.str(CsCar.getKeyValue(this.getCswrCar()));
		}			
	 	 return strValue;
	}
	/**
	* 作业车辆 [非空]     
	**/
	public void setCswrCar(Long cswrCar){
		this.cswrCar = cswrCar;
		this.setSeted(F.cswrCar);
	}
	/**
	* 获取关联对象[作业车辆]
	**/	 			
 	public CsCar get$cswrCar(){
 		com.ccclubs.model.CsCar csCar = (com.ccclubs.model.CsCar) $.GetRequest("CsCar$"+this.getCswrCar());
 		if(csCar!=null)
			return csCar;
		if(this.getCswrCar()!=null){
			csCar = CsCar.get(this.getCswrCar());
 		}
 		$.SetRequest("CsCar$"+this.getCswrCar(), csCar);
	 	return csCar;
	}
	/*******************************人员**********************************/	
	/**
	* 人员 [非空]      
	**/
	public Long getCswrUser(){
		return this.cswrUser;
	}
	/**
	* 获取人员格式化(toString)
	**/
	public String getCswrUser$(){
		String strValue="";
		if(this.getCswrUser()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCswrUser()));
		}			
	 	 return strValue;
	}
	/**
	* 人员 [非空]     
	**/
	public void setCswrUser(Long cswrUser){
		this.cswrUser = cswrUser;
		this.setSeted(F.cswrUser);
	}
	/**
	* 获取关联对象[人员]
	**/	 			
 	public SrvUser get$cswrUser(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCswrUser());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCswrUser()!=null){
			srvUser = SrvUser.get(this.getCswrUser());
 		}
 		$.SetRequest("SrvUser$"+this.getCswrUser(), srvUser);
	 	return srvUser;
	}
	/*******************************作业项目**********************************/	
	/**
	* 作业项目 [可空]      
	**/
	public Long getCswrItem(){
		return this.cswrItem;
	}
	/**
	* 获取作业项目格式化(toString)
	**/
	public String getCswrItem$(){
		String strValue="";
		if(this.getCswrItem()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCswrItem()));
		}			
	 	 return strValue;
	}
	/**
	* 作业项目 [可空]     
	**/
	public void setCswrItem(Long cswrItem){
		this.cswrItem = cswrItem;
		this.setSeted(F.cswrItem);
	}
	/**
	* 获取关联对象[作业项目]
	**/	 			
 	public SrvProperty get$cswrItem(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCswrItem());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCswrItem()!=null){
			srvProperty = SrvProperty.get(this.getCswrItem());
 		}
 		$.SetRequest("SrvProperty$"+this.getCswrItem(), srvProperty);
	 	return srvProperty;
	}
	/*******************************作业项目名称**********************************/	
	/**
	* 作业项目名称 [可空]      
	**/
	public String getCswrItemname(){
		return this.cswrItemname;
	}
	/**
	* 获取作业项目名称格式化(toString)
	**/
	public String getCswrItemname$(){
		String strValue="";
		 strValue=$.str(this.getCswrItemname());
	 	 return strValue;
	}
	/**
	* 作业项目名称 [可空]     
	**/
	public void setCswrItemname(String cswrItemname){
		this.cswrItemname = cswrItemname;
		this.setSeted(F.cswrItemname);
	}
	/*******************************远程控制**********************************/	
	/**
	* 远程控制 [非空]      
	**/
	public String getCswrOperation(){
		return this.cswrOperation;
	}
	/**
	* 获取远程控制格式化(toString)
	**/
	public String getCswrOperation$(){
		String strValue="";
		 strValue=$.str(this.getCswrOperation());
	 	 return strValue;
	}
	/**
	* 远程控制 [非空]     
	**/
	public void setCswrOperation(String cswrOperation){
		this.cswrOperation = cswrOperation;
		this.setSeted(F.cswrOperation);
	}
	/*******************************其它数据**********************************/	
	/**
	* 其它数据 [非空]      
	**/
	public String getCswrData(){
		return this.cswrData;
	}
	/**
	* 获取其它数据格式化(toString)
	**/
	public String getCswrData$(){
		String strValue="";
		 strValue=$.str(this.getCswrData());
	 	 return strValue;
	}
	/**
	* 其它数据 [非空]     
	**/
	public void setCswrData(String cswrData){
		this.cswrData = cswrData;
		this.setSeted(F.cswrData);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]      
	**/
	public Short getCswrStatus(){
		return this.cswrStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCswrStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCswrStatus()),"2"))
			 strValue=$.str("已确认");
		 if($.equals($.str(this.getCswrStatus()),"1"))
			 strValue=$.str("未确认");
		 if($.equals($.str(this.getCswrStatus()),"0"))
			 strValue=$.str("无效");
	 	 return strValue;
	}
	/**
	* 状态 [非空]     
	**/
	public void setCswrStatus(Short cswrStatus){
		this.cswrStatus = cswrStatus;
		this.setSeted(F.cswrStatus);
	}
	/*******************************创建时间**********************************/	
	/**
	* 创建时间 [非空]      
	**/
	public Date getCswrAddTime(){
		return this.cswrAddTime;
	}
	/**
	* 获取创建时间格式化(toString)
	**/
	public String getCswrAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCswrAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 创建时间 [非空]     
	**/
	public void setCswrAddTime(Date cswrAddTime){
		this.cswrAddTime = cswrAddTime;
		this.setSeted(F.cswrAddTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCswrUpdateTime(){
		return this.cswrUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCswrUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCswrUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCswrUpdateTime(Date cswrUpdateTime){
		this.cswrUpdateTime = cswrUpdateTime;
		this.setSeted(F.cswrUpdateTime);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsWorkRecord.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsWorkRecord.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsWorkRecord.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsWorkRecord.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsWorkRecord.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsWorkRecord.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsWorkRecord.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsWorkRecord.this);
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
		public M cswrId(Object cswrId){this.put("cswrId", cswrId);return this;};
		/** and cswr_id is null */
		public M cswrIdNull(){if(this.get("cswrIdNot")==null)this.put("cswrIdNot", "");this.put("cswrId", null);return this;};
		/** not .... */
		public M cswrIdNot(){this.put("cswrIdNot", "not");return this;};
		/** and cswr_id >= ? */
		public M cswrIdMin(Object min){this.put("cswrIdMin", min);return this;};
		/** and cswr_id <= ? */
		public M cswrIdMax(Object max){this.put("cswrIdMax", max);return this;};
		/** 所在城市 [非空]       **/
		public M cswrHost(Object cswrHost){this.put("cswrHost", cswrHost);return this;};
		/** and cswr_host is null */
		public M cswrHostNull(){if(this.get("cswrHostNot")==null)this.put("cswrHostNot", "");this.put("cswrHost", null);return this;};
		/** not .... */
		public M cswrHostNot(){this.put("cswrHostNot", "not");return this;};
		/** and cswr_host >= ? */
		public M cswrHostMin(Object min){this.put("cswrHostMin", min);return this;};
		/** and cswr_host <= ? */
		public M cswrHostMax(Object max){this.put("cswrHostMax", max);return this;};
		/** 网点 [非空]       **/
		public M cswrOutlets(Object cswrOutlets){this.put("cswrOutlets", cswrOutlets);return this;};
		/** and cswr_outlets is null */
		public M cswrOutletsNull(){if(this.get("cswrOutletsNot")==null)this.put("cswrOutletsNot", "");this.put("cswrOutlets", null);return this;};
		/** not .... */
		public M cswrOutletsNot(){this.put("cswrOutletsNot", "not");return this;};
		public M cswrOutlets$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("cswrOutlets$on", value);
			return this;
		};	
		/** and cswr_outlets >= ? */
		public M cswrOutletsMin(Object min){this.put("cswrOutletsMin", min);return this;};
		/** and cswr_outlets <= ? */
		public M cswrOutletsMax(Object max){this.put("cswrOutletsMax", max);return this;};
		/** 作业车辆 [非空]       **/
		public M cswrCar(Object cswrCar){this.put("cswrCar", cswrCar);return this;};
		/** and cswr_car is null */
		public M cswrCarNull(){if(this.get("cswrCarNot")==null)this.put("cswrCarNot", "");this.put("cswrCar", null);return this;};
		/** not .... */
		public M cswrCarNot(){this.put("cswrCarNot", "not");return this;};
		public M cswrCar$on(CsCar.M value){
			this.put("CsCar", value);
			this.put("cswrCar$on", value);
			return this;
		};	
		/** and cswr_car >= ? */
		public M cswrCarMin(Object min){this.put("cswrCarMin", min);return this;};
		/** and cswr_car <= ? */
		public M cswrCarMax(Object max){this.put("cswrCarMax", max);return this;};
		/** 人员 [非空]       **/
		public M cswrUser(Object cswrUser){this.put("cswrUser", cswrUser);return this;};
		/** and cswr_user is null */
		public M cswrUserNull(){if(this.get("cswrUserNot")==null)this.put("cswrUserNot", "");this.put("cswrUser", null);return this;};
		/** not .... */
		public M cswrUserNot(){this.put("cswrUserNot", "not");return this;};
		public M cswrUser$on(SrvUser.M value){
			this.put("SrvUser", value);
			this.put("cswrUser$on", value);
			return this;
		};	
		/** and cswr_user >= ? */
		public M cswrUserMin(Object min){this.put("cswrUserMin", min);return this;};
		/** and cswr_user <= ? */
		public M cswrUserMax(Object max){this.put("cswrUserMax", max);return this;};
		/** 作业项目 [可空]       **/
		public M cswrItem(Object cswrItem){this.put("cswrItem", cswrItem);return this;};
		/** and cswr_item is null */
		public M cswrItemNull(){if(this.get("cswrItemNot")==null)this.put("cswrItemNot", "");this.put("cswrItem", null);return this;};
		/** not .... */
		public M cswrItemNot(){this.put("cswrItemNot", "not");return this;};
		public M cswrItem$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("cswrItem$on", value);
			return this;
		};	
		/** and cswr_item >= ? */
		public M cswrItemMin(Object min){this.put("cswrItemMin", min);return this;};
		/** and cswr_item <= ? */
		public M cswrItemMax(Object max){this.put("cswrItemMax", max);return this;};
		/** 作业项目名称 [可空]       **/
		public M cswrItemname(Object cswrItemname){this.put("cswrItemname", cswrItemname);return this;};
		/** and cswr_itemname is null */
		public M cswrItemnameNull(){if(this.get("cswrItemnameNot")==null)this.put("cswrItemnameNot", "");this.put("cswrItemname", null);return this;};
		/** not .... */
		public M cswrItemnameNot(){this.put("cswrItemnameNot", "not");return this;};
		/** 远程控制 [非空]       **/
		public M cswrOperation(Object cswrOperation){this.put("cswrOperation", cswrOperation);return this;};
		/** and cswr_operation is null */
		public M cswrOperationNull(){if(this.get("cswrOperationNot")==null)this.put("cswrOperationNot", "");this.put("cswrOperation", null);return this;};
		/** not .... */
		public M cswrOperationNot(){this.put("cswrOperationNot", "not");return this;};
		/** 其它数据 [非空]       **/
		public M cswrData(Object cswrData){this.put("cswrData", cswrData);return this;};
		/** and cswr_data is null */
		public M cswrDataNull(){if(this.get("cswrDataNot")==null)this.put("cswrDataNot", "");this.put("cswrData", null);return this;};
		/** not .... */
		public M cswrDataNot(){this.put("cswrDataNot", "not");return this;};
		/** 状态 [非空]       **/
		public M cswrStatus(Object cswrStatus){this.put("cswrStatus", cswrStatus);return this;};
		/** and cswr_status is null */
		public M cswrStatusNull(){if(this.get("cswrStatusNot")==null)this.put("cswrStatusNot", "");this.put("cswrStatus", null);return this;};
		/** not .... */
		public M cswrStatusNot(){this.put("cswrStatusNot", "not");return this;};
		/** and cswr_status >= ? */
		public M cswrStatusMin(Object min){this.put("cswrStatusMin", min);return this;};
		/** and cswr_status <= ? */
		public M cswrStatusMax(Object max){this.put("cswrStatusMax", max);return this;};
		/** 创建时间 [非空]       **/
		public M cswrAddTime(Object cswrAddTime){this.put("cswrAddTime", cswrAddTime);return this;};
		/** and cswr_add_time is null */
		public M cswrAddTimeNull(){if(this.get("cswrAddTimeNot")==null)this.put("cswrAddTimeNot", "");this.put("cswrAddTime", null);return this;};
		/** not .... */
		public M cswrAddTimeNot(){this.put("cswrAddTimeNot", "not");return this;};
		/** and cswr_add_time >= ? */
 		public M cswrAddTimeStart(Object start){this.put("cswrAddTimeStart", start);return this;};			
 		/** and cswr_add_time <= ? */
 		public M cswrAddTimeEnd(Object end){this.put("cswrAddTimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M cswrUpdateTime(Object cswrUpdateTime){this.put("cswrUpdateTime", cswrUpdateTime);return this;};
		/** and cswr_update_time is null */
		public M cswrUpdateTimeNull(){if(this.get("cswrUpdateTimeNot")==null)this.put("cswrUpdateTimeNot", "");this.put("cswrUpdateTime", null);return this;};
		/** not .... */
		public M cswrUpdateTimeNot(){this.put("cswrUpdateTimeNot", "not");return this;};
		/** and cswr_update_time >= ? */
 		public M cswrUpdateTimeStart(Object start){this.put("cswrUpdateTimeStart", start);return this;};			
 		/** and cswr_update_time <= ? */
 		public M cswrUpdateTimeEnd(Object end){this.put("cswrUpdateTimeEnd", end);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有后勤工作记录 **/
		public @api List<CsWorkRecord> list(Integer size){
			return getCsWorkRecordList(this,size);
		}
		/** 获取后勤工作记录分页 **/
		public @api Page<CsWorkRecord> page(int page,int size){
			return getCsWorkRecordPage(page, size , this);
		}
		/** 根据查询条件取后勤工作记录 **/
		public @api CsWorkRecord get(){
			return getCsWorkRecord(this);
		}
		/** 获取后勤工作记录数 **/
		public @api Long count(){
			return getCsWorkRecordCount(this);
		}
		/** 获取后勤工作记录表达式 **/
		public @api <T> T eval(String strEval){
			return getCsWorkRecordEval(strEval,this);
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
			updateCsWorkRecord(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cswrId="cswrId";
		/** 所在城市 [非空]       **/
		public final static @type(Long.class)  String cswrHost="cswrHost";
		/** 网点 [非空]       **/
		public final static @type(Long.class)  String cswrOutlets="cswrOutlets";
		/** 作业车辆 [非空]       **/
		public final static @type(Long.class)  String cswrCar="cswrCar";
		/** 人员 [非空]       **/
		public final static @type(Long.class)  String cswrUser="cswrUser";
		/** 作业项目 [可空]       **/
		public final static @type(Long.class)  String cswrItem="cswrItem";
		/** 作业项目名称 [可空]       **/
		public final static @type(String.class)  String cswrItemname="cswrItemname";
		/** 远程控制 [非空]       **/
		public final static @type(String.class)  String cswrOperation="cswrOperation";
		/** 其它数据 [非空]       **/
		public final static @type(String.class)  String cswrData="cswrData";
		/** 状态 [非空]       **/
		public final static @type(Short.class)  String cswrStatus="cswrStatus";
		/** 创建时间 [非空]       **/
		public final static @type(Date.class)  String cswrAddTime="cswrAddTime";
		/** and cswr_add_time >= ? */
 		public final static @type(Date.class) String cswrAddTimeStart="cswrAddTimeStart";
 		/** and cswr_add_time <= ? */
 		public final static @type(Date.class) String cswrAddTimeEnd="cswrAddTimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cswrUpdateTime="cswrUpdateTime";
		/** and cswr_update_time >= ? */
 		public final static @type(Date.class) String cswrUpdateTimeStart="cswrUpdateTimeStart";
 		/** and cswr_update_time <= ? */
 		public final static @type(Date.class) String cswrUpdateTimeEnd="cswrUpdateTimeEnd";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cswrId="cswr_id";
		/** 所在城市 [非空]       **/
		public final static String cswrHost="cswr_host";
		/** 网点 [非空]       **/
		public final static String cswrOutlets="cswr_outlets";
		/** 作业车辆 [非空]       **/
		public final static String cswrCar="cswr_car";
		/** 人员 [非空]       **/
		public final static String cswrUser="cswr_user";
		/** 作业项目 [可空]       **/
		public final static String cswrItem="cswr_item";
		/** 作业项目名称 [可空]       **/
		public final static String cswrItemname="cswr_itemname";
		/** 远程控制 [非空]       **/
		public final static String cswrOperation="cswr_operation";
		/** 其它数据 [非空]       **/
		public final static String cswrData="cswr_data";
		/** 状态 [非空]       **/
		public final static String cswrStatus="cswr_status";
		/** 创建时间 [非空]       **/
		public final static String cswrAddTime="cswr_add_time";
		/** 修改时间 [非空]       **/
		public final static String cswrUpdateTime="cswr_update_time";
		
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
				$.Set(name,CsWorkRecord.getCsWorkRecord(params));
			else
				$.Set(name,CsWorkRecord.getCsWorkRecordList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取后勤工作记录数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsWorkRecord) $.GetRequest("CsWorkRecord$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsWorkRecord.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsWorkRecord.getCsWorkRecord(params);
			else
				value = CsWorkRecord.getCsWorkRecordList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsWorkRecord.Get($.add(CsWorkRecord.F.cswrId,param));
		else if(!$.empty(param.toString()))
			value = CsWorkRecord.get(Long.valueOf(param.toString()));
		$.SetRequest("CsWorkRecord$"+param.hashCode(), value);
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
	public void mergeSet(CsWorkRecord old){
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