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

@namespace("object/app/update")
public @caption("App更新") @table("cs_update") class CsUpdate implements java.io.Serializable
{
	private static final long serialVersionUID =          1l;
	private @caption("编号") @column("csu_id")   @primary  @note("  ") Long csuId;// 主键 非空     
	private @caption("名称") @column("csu_name")    @note("  ") String csuName;// 非空     
	private @caption("版本号") @column("csu_version")    @note("  ") String csuVersion;// 非空     
	private @caption("类型") @column("csu_type")    @note(" 0:android程序 1:ios程序  ") Short csuType;// 非空 0:android程序 1:ios程序     
	private @caption("更新文件") @column("csu_file")    @note("  ") String csuFile;//     
	private @caption("描述") @column("csu_descript")    @note("  ") String csuDescript;//     
	private @caption("修改时间") @column("csu_update_time")    @note("  ") Date csuUpdateTime;// 非空     
	private @caption("添加时间") @column("csu_add_time")    @note("  ") Date csuAddTime;// 非空     
	private @caption("状态") @column("csu_status")    @note(" 1:正常 0:无效  ") Short csuStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsUpdate(){
	
	}
	
	//主键构造函数
	public CsUpdate(Long id){
		this.csuId = id;
	}
	
	/**所有字段构造函数 CsUpdate(csuName,csuVersion,csuType,csuFile,csuDescript,csuUpdateTime,csuAddTime,csuStatus)
	 CsUpdate(
	 	$.getString("csuName")//名称 [非空]
	 	,$.getString("csuVersion")//版本号 [非空]
	 	,$.getShort("csuType")//类型 [非空]
	 	,$.getString("csuFile")//更新文件
	 	,$.getString("csuDescript")//描述
	 	,$.getDate("csuUpdateTime")//修改时间 [非空]
	 	,$.getDate("csuAddTime")//添加时间 [非空]
	 	,$.getShort("csuStatus")//状态 [非空]
	 )
	**/
	public CsUpdate(String csuName,String csuVersion,Short csuType,String csuFile,String csuDescript,Date csuUpdateTime,Date csuAddTime,Short csuStatus){
		this.csuName=csuName;
		this.csuVersion=csuVersion;
		this.csuType=csuType;
		this.csuFile=csuFile;
		this.csuDescript=csuDescript;
		this.csuUpdateTime=csuUpdateTime;
		this.csuAddTime=csuAddTime;
		this.csuStatus=csuStatus;
	}
	
	//设置非空字段
	public CsUpdate setNotNull(String csuName,String csuVersion,Short csuType,Date csuUpdateTime,Date csuAddTime,Short csuStatus){
		this.csuName=csuName;
		this.csuVersion=csuVersion;
		this.csuType=csuType;
		this.csuUpdateTime=csuUpdateTime;
		this.csuAddTime=csuAddTime;
		this.csuStatus=csuStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsUpdate csuId(Long csuId){
		this.csuId = csuId;
		this.setSeted(F.csuId);
		return this;
	}
	/** 名称 [非空]       **/
	public CsUpdate csuName(String csuName){
		this.csuName = csuName;
		this.setSeted(F.csuName);
		return this;
	}
	/** 版本号 [非空]       **/
	public CsUpdate csuVersion(String csuVersion){
		this.csuVersion = csuVersion;
		this.setSeted(F.csuVersion);
		return this;
	}
	/** 类型 [非空]   0:android程序 1:ios程序     **/
	public CsUpdate csuType(Short csuType){
		this.csuType = csuType;
		this.setSeted(F.csuType);
		return this;
	}
	/** 更新文件        **/
	public CsUpdate csuFile(String csuFile){
		this.csuFile = csuFile;
		this.setSeted(F.csuFile);
		return this;
	}
	/** 描述        **/
	public CsUpdate csuDescript(String csuDescript){
		this.csuDescript = csuDescript;
		this.setSeted(F.csuDescript);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsUpdate csuUpdateTime(Date csuUpdateTime){
		this.csuUpdateTime = csuUpdateTime;
		this.setSeted(F.csuUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsUpdate csuAddTime(Date csuAddTime){
		this.csuAddTime = csuAddTime;
		this.setSeted(F.csuAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsUpdate csuStatus(Short csuStatus){
		this.csuStatus = csuStatus;
		this.setSeted(F.csuStatus);
		return this;
	}
	
	
	//克隆对象
	public CsUpdate clone(){
		CsUpdate clone = new CsUpdate();
		clone.csuName=this.csuName;
		clone.csuVersion=this.csuVersion;
		clone.csuType=this.csuType;
		clone.csuUpdateTime=this.csuUpdateTime;
		clone.csuAddTime=this.csuAddTime;
		clone.csuStatus=this.csuStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取App更新
	 * @param id
	 * @return
	 */
	public static @api CsUpdate get(Long id){		
		return getCsUpdateById(id);
	}
	/**
	 * 获取所有App更新
	 * @return
	 */
	public static @api List<CsUpdate> list(Map params,Integer size){
		return getCsUpdateList(params,size);
	}
	/**
	 * 获取App更新分页
	 * @return
	 */
	public static @api Page<CsUpdate> page(int page,int size,Map params){
		return getCsUpdatePage(page, size , params);
	}
	/**
	 * 根据查询条件取App更新
	 * @param params
	 * @return
	 */
	public static @api CsUpdate Get(Map params){
		return getCsUpdate(params);
	}
	/**
	 * 获取App更新数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsUpdateCount(params);
	}
	/**
	 * 获取App更新数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsUpdateEval(eval,params);
	}
	
	/**
	 * 根据ID取App更新
	 * @param id
	 * @return
	 */
	public static @api CsUpdate getCsUpdateById(Long id){		
		CsUpdate csUpdate = (CsUpdate) $.GetRequest("CsUpdate$"+id);
		if(csUpdate!=null)
			return csUpdate;
		com.ccclubs.service.admin.ICsUpdateService csUpdateService = $.GetSpringBean("csUpdateService");		
		return csUpdateService.getCsUpdateById(id);
	}
	
	
	/**
	 * 根据ID取App更新的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsUpdate.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsUpdate csUpdate = get(id);
		if(csUpdate!=null){
			String strValue = csUpdate.getCsuName$();
			if(!"CsuName".equals("CsuName"))
				strValue+="("+csUpdate.getCsuName$()+")";
			MemCache.setValue(CsUpdate.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsuName$();
		if(!"CsuName".equals("CsuName"))
			keyValue+="("+this.getCsuName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有App更新
	 * @return
	 */
	public static @api List<CsUpdate> getCsUpdateList(Map params,Integer size){
		com.ccclubs.service.admin.ICsUpdateService csUpdateService = $.GetSpringBean("csUpdateService");
		return csUpdateService.getCsUpdateList(params, size);
	}
	
	/**
	 * 获取App更新分页
	 * @return
	 */
	public static @api Page<CsUpdate> getCsUpdatePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsUpdateService csUpdateService = $.GetSpringBean("csUpdateService");
		return csUpdateService.getCsUpdatePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取App更新
	 * @param params
	 * @return
	 */
	public static @api CsUpdate getCsUpdate(Map params){
		com.ccclubs.service.admin.ICsUpdateService csUpdateService = $.GetSpringBean("csUpdateService");
		return csUpdateService.getCsUpdate(params);
	}
	
	/**
	 * 获取App更新数
	 * @return
	 */
	public static @api Long getCsUpdateCount(Map params){
		com.ccclubs.service.admin.ICsUpdateService csUpdateService = $.GetSpringBean("csUpdateService");
		return csUpdateService.getCsUpdateCount(params);
	}
		
		
	/**
	 * 获取App更新自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsUpdateEval(String eval,Map params){
		com.ccclubs.service.admin.ICsUpdateService csUpdateService = $.GetSpringBean("csUpdateService");
		return csUpdateService.getCsUpdateEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsUpdate(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsUpdateService csUpdateService = $.GetSpringBean("csUpdateService");
		csUpdateService.updateCsUpdateByConfirm(set, where);
	}
	
	
	/**
	 * 保存App更新对象
	 * @param params
	 * @return
	 */
	public CsUpdate save(){
		com.ccclubs.service.admin.ICsUpdateService csUpdateService = $.GetSpringBean("csUpdateService");
		if(this.getCsuId()!=null)
			csUpdateService.updateCsUpdate(this);
		else
			return csUpdateService.saveCsUpdate(this);
		return this;
	}
	
	
	/**
	 * 更新App更新对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsUpdateService csUpdateService = $.GetSpringBean("csUpdateService");
		csUpdateService.updateCsUpdate$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsUpdateService csUpdateService = $.GetSpringBean("csUpdateService");
		if($.equals($.config("logic_delete"),"true"))
			csUpdateService.removeCsUpdateById(this.getCsuId());
		else
			csUpdateService.deleteCsUpdateById(this.getCsuId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsUpdateService csUpdateService = $.GetSpringBean("csUpdateService");
		return csUpdateService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsuId(){
		return this.csuId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsuId$(){
		String strValue="";
		 strValue=$.str(this.getCsuId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsuId(Long csuId){
		this.csuId = csuId;
		this.setSeted(F.csuId);
	}
	/*******************************名称**********************************/	
	/**
	* 名称 [非空]      
	**/
	public String getCsuName(){
		return this.csuName;
	}
	/**
	* 获取名称格式化(toString)
	**/
	public String getCsuName$(){
		String strValue="";
		 strValue=$.str(this.getCsuName());
	 	 return strValue;
	}
	/**
	* 名称 [非空]      
	**/
	public void setCsuName(String csuName){
		this.csuName = csuName;
		this.setSeted(F.csuName);
	}
	/*******************************版本号**********************************/	
	/**
	* 版本号 [非空]      
	**/
	public String getCsuVersion(){
		return this.csuVersion;
	}
	/**
	* 获取版本号格式化(toString)
	**/
	public String getCsuVersion$(){
		String strValue="";
		 strValue=$.str(this.getCsuVersion());
	 	 return strValue;
	}
	/**
	* 版本号 [非空]      
	**/
	public void setCsuVersion(String csuVersion){
		this.csuVersion = csuVersion;
		this.setSeted(F.csuVersion);
	}
	/*******************************类型**********************************/	
	/**
	* 类型 [非空]   0:android程序 1:ios程序    
	**/
	public Short getCsuType(){
		return this.csuType;
	}
	/**
	* 获取类型格式化(toString)
	**/
	public String getCsuType$(){
		String strValue="";
		 if($.equals($.str(this.getCsuType()),"0"))
			strValue=$.str("android程序");		 
		 if($.equals($.str(this.getCsuType()),"1"))
			strValue=$.str("ios程序");		 
	 	 return strValue;
	}
	/**
	* 类型 [非空]   0:android程序 1:ios程序    
	**/
	public void setCsuType(Short csuType){
		this.csuType = csuType;
		this.setSeted(F.csuType);
	}
	/*******************************更新文件**********************************/	
	/**
	* 更新文件       
	**/
	public String getCsuFile(){
		return this.csuFile;
	}
	/**
	* 获取更新文件格式化(toString)
	**/
	public String getCsuFile$(){
		String strValue="";
		 strValue=$.str(this.getCsuFile());
	 	 return strValue;
	}
	/**
	* 更新文件       
	**/
	public void setCsuFile(String csuFile){
		this.csuFile = csuFile;
		this.setSeted(F.csuFile);
	}
	/*******************************描述**********************************/	
	/**
	* 描述       
	**/
	public String getCsuDescript(){
		return this.csuDescript;
	}
	/**
	* 获取描述格式化(toString)
	**/
	public String getCsuDescript$(){
		String strValue="";
		 strValue=$.str(this.getCsuDescript());
	 	 return strValue;
	}
	/**
	* 描述       
	**/
	public void setCsuDescript(String csuDescript){
		this.csuDescript = csuDescript;
		this.setSeted(F.csuDescript);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsuUpdateTime(){
		return this.csuUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsuUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsuUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsuUpdateTime(Date csuUpdateTime){
		this.csuUpdateTime = csuUpdateTime;
		this.setSeted(F.csuUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsuAddTime(){
		return this.csuAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsuAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsuAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsuAddTime(Date csuAddTime){
		this.csuAddTime = csuAddTime;
		this.setSeted(F.csuAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsuStatus(){
		return this.csuStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsuStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsuStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsuStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsuStatus(Short csuStatus){
		this.csuStatus = csuStatus;
		this.setSeted(F.csuStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsUpdate.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUpdate.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUpdate.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsUpdate.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsUpdate.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUpdate.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUpdate.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsUpdate.this);
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
		public M csuId(Object csuId){this.put("csuId", csuId);return this;};
	 	/** and csu_id is null */
 		public M csuIdNull(){if(this.get("csuIdNot")==null)this.put("csuIdNot", "");this.put("csuId", null);return this;};
 		/** not .... */
 		public M csuIdNot(){this.put("csuIdNot", "not");return this;};
		/** 名称 [非空]       **/
		public M csuName(Object csuName){this.put("csuName", csuName);return this;};
	 	/** and csu_name is null */
 		public M csuNameNull(){if(this.get("csuNameNot")==null)this.put("csuNameNot", "");this.put("csuName", null);return this;};
 		/** not .... */
 		public M csuNameNot(){this.put("csuNameNot", "not");return this;};
		/** 版本号 [非空]       **/
		public M csuVersion(Object csuVersion){this.put("csuVersion", csuVersion);return this;};
	 	/** and csu_version is null */
 		public M csuVersionNull(){if(this.get("csuVersionNot")==null)this.put("csuVersionNot", "");this.put("csuVersion", null);return this;};
 		/** not .... */
 		public M csuVersionNot(){this.put("csuVersionNot", "not");return this;};
		/** 类型 [非空]   0:android程序 1:ios程序     **/
		public M csuType(Object csuType){this.put("csuType", csuType);return this;};
	 	/** and csu_type is null */
 		public M csuTypeNull(){if(this.get("csuTypeNot")==null)this.put("csuTypeNot", "");this.put("csuType", null);return this;};
 		/** not .... */
 		public M csuTypeNot(){this.put("csuTypeNot", "not");return this;};
		/** 更新文件        **/
		public M csuFile(Object csuFile){this.put("csuFile", csuFile);return this;};
	 	/** and csu_file is null */
 		public M csuFileNull(){if(this.get("csuFileNot")==null)this.put("csuFileNot", "");this.put("csuFile", null);return this;};
 		/** not .... */
 		public M csuFileNot(){this.put("csuFileNot", "not");return this;};
		/** 描述        **/
		public M csuDescript(Object csuDescript){this.put("csuDescript", csuDescript);return this;};
	 	/** and csu_descript is null */
 		public M csuDescriptNull(){if(this.get("csuDescriptNot")==null)this.put("csuDescriptNot", "");this.put("csuDescript", null);return this;};
 		/** not .... */
 		public M csuDescriptNot(){this.put("csuDescriptNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csuUpdateTime(Object csuUpdateTime){this.put("csuUpdateTime", csuUpdateTime);return this;};
	 	/** and csu_update_time is null */
 		public M csuUpdateTimeNull(){if(this.get("csuUpdateTimeNot")==null)this.put("csuUpdateTimeNot", "");this.put("csuUpdateTime", null);return this;};
 		/** not .... */
 		public M csuUpdateTimeNot(){this.put("csuUpdateTimeNot", "not");return this;};
 		/** and csu_update_time >= ? */
 		public M csuUpdateTimeStart(Object start){this.put("csuUpdateTimeStart", start);return this;};			
 		/** and csu_update_time <= ? */
 		public M csuUpdateTimeEnd(Object end){this.put("csuUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csuAddTime(Object csuAddTime){this.put("csuAddTime", csuAddTime);return this;};
	 	/** and csu_add_time is null */
 		public M csuAddTimeNull(){if(this.get("csuAddTimeNot")==null)this.put("csuAddTimeNot", "");this.put("csuAddTime", null);return this;};
 		/** not .... */
 		public M csuAddTimeNot(){this.put("csuAddTimeNot", "not");return this;};
 		/** and csu_add_time >= ? */
 		public M csuAddTimeStart(Object start){this.put("csuAddTimeStart", start);return this;};			
 		/** and csu_add_time <= ? */
 		public M csuAddTimeEnd(Object end){this.put("csuAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csuStatus(Object csuStatus){this.put("csuStatus", csuStatus);return this;};
	 	/** and csu_status is null */
 		public M csuStatusNull(){if(this.get("csuStatusNot")==null)this.put("csuStatusNot", "");this.put("csuStatus", null);return this;};
 		/** not .... */
 		public M csuStatusNot(){this.put("csuStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有App更新 **/
		public @api List<CsUpdate> list(Integer size){
			return getCsUpdateList(this,size);
		}
		/** 获取App更新分页 **/
		public @api Page<CsUpdate> page(int page,int size){
			return getCsUpdatePage(page, size , this);
		}
		/** 根据查询条件取App更新 **/
		public @api CsUpdate get(){
			return getCsUpdate(this);
		}
		/** 获取App更新数 **/
		public @api Long count(){
			return getCsUpdateCount(this);
		}
		/** 获取App更新表达式 **/
		public @api <T> T eval(String strEval){
			return getCsUpdateEval(strEval,this);
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
			updateCsUpdate(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csuId="csuId";
		/** 名称 [非空]       **/
		public final static @type(String.class) @like String csuName="csuName";
		/** 版本号 [非空]       **/
		public final static @type(String.class) @like String csuVersion="csuVersion";
		/** 类型 [非空]   0:android程序 1:ios程序     **/
		public final static @type(Short.class)  String csuType="csuType";
		/** 更新文件        **/
		public final static @type(String.class)  String csuFile="csuFile";
		/** 描述        **/
		public final static @type(String.class) @like String csuDescript="csuDescript";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csuUpdateTime="csuUpdateTime";
	 	/** and csu_update_time >= ? */
 		public final static @type(Date.class) String csuUpdateTimeStart="csuUpdateTimeStart";
 		/** and csu_update_time <= ? */
 		public final static @type(Date.class) String csuUpdateTimeEnd="csuUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csuAddTime="csuAddTime";
	 	/** and csu_add_time >= ? */
 		public final static @type(Date.class) String csuAddTimeStart="csuAddTimeStart";
 		/** and csu_add_time <= ? */
 		public final static @type(Date.class) String csuAddTimeEnd="csuAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csuStatus="csuStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csuId="csu_id";
		/** 名称 [非空]       **/
		public final static String csuName="csu_name";
		/** 版本号 [非空]       **/
		public final static String csuVersion="csu_version";
		/** 类型 [非空]   0:android程序 1:ios程序     **/
		public final static String csuType="csu_type";
		/** 更新文件        **/
		public final static String csuFile="csu_file";
		/** 描述        **/
		public final static String csuDescript="csu_descript";
		/** 修改时间 [非空]       **/
		public final static String csuUpdateTime="csu_update_time";
		/** 添加时间 [非空]       **/
		public final static String csuAddTime="csu_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csuStatus="csu_status";
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
				$.Set(name,CsUpdate.getCsUpdate(params));
			else
				$.Set(name,CsUpdate.getCsUpdateList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取App更新数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsUpdate) $.GetRequest("CsUpdate$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsUpdate.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsUpdate.getCsUpdate(params);
			else
				value = CsUpdate.getCsUpdateList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsUpdate.Get($.add(CsUpdate.F.csuId,param));
		else if(!$.empty(param.toString()))
			value = CsUpdate.get(Long.valueOf(param.toString()));
		$.SetRequest("CsUpdate$"+param.hashCode(), value);
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
	public void mergeSet(CsUpdate old){
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