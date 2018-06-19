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

@namespace("user/coinsource")
public @caption("红包来源") @table("cs_coin_source") class CsCoinSource implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cscs_id")   @primary   @note("  ") Long cscsId;
	private @caption("来源名称") @column("cscs_name")      @note("  ") String cscsName;
	private @caption("标识") @column("cscs_flag")      @note("  ") String cscsFlag;
	private @caption("修改时间") @column("cscs_update_time")      @note("  ") Date cscsUpdateTime;
	private @caption("添加时间") @column("cscs_add_time")      @note("  ") Date cscsAddTime;
	private @caption("备注信息") @column("cscs_remark")      @note("  ") String cscsRemark;
	private @caption("状态") @column("cscs_status")      @note(" 1:正常 0:无效 ") Short cscsStatus;
	
	//默认构造函数
	public CsCoinSource(){
	
	}
	
	//主键构造函数
	public CsCoinSource(Long id){
		this.cscsId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsCoinSource(String cscsName,String cscsFlag,Date cscsUpdateTime,Date cscsAddTime,String cscsRemark,Short cscsStatus){
		this.cscsName=cscsName;
		this.cscsFlag=cscsFlag;
		this.cscsUpdateTime=cscsUpdateTime;
		this.cscsAddTime=cscsAddTime;
		this.cscsRemark=cscsRemark;
		this.cscsStatus=cscsStatus;
	}
	
	//设置非空字段
	public CsCoinSource setNotNull(String cscsName){
		this.cscsName=cscsName;
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsCoinSource cscsId(Long cscsId){
		this.cscsId = cscsId;
		this.setSeted(F.cscsId);
		return this;
	}
	/** 来源名称 [可空]       **/
	public CsCoinSource cscsName(String cscsName){
		this.cscsName = cscsName;
		this.setSeted(F.cscsName);
		return this;
	}
	/** 标识 [非空]       **/
	public CsCoinSource cscsFlag(String cscsFlag){
		this.cscsFlag = cscsFlag;
		this.setSeted(F.cscsFlag);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsCoinSource cscsUpdateTime(Date cscsUpdateTime){
		this.cscsUpdateTime = cscsUpdateTime;
		this.setSeted(F.cscsUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsCoinSource cscsAddTime(Date cscsAddTime){
		this.cscsAddTime = cscsAddTime;
		this.setSeted(F.cscsAddTime);
		return this;
	}
	/** 备注信息 [非空]       **/
	public CsCoinSource cscsRemark(String cscsRemark){
		this.cscsRemark = cscsRemark;
		this.setSeted(F.cscsRemark);
		return this;
	}
	/** 状态 [非空]       **/
	public CsCoinSource cscsStatus(Short cscsStatus){
		this.cscsStatus = cscsStatus;
		this.setSeted(F.cscsStatus);
		return this;
	}
	
	//克隆对象
	public CsCoinSource clone(){
		CsCoinSource clone = new CsCoinSource();
		clone.cscsId=this.cscsId;
		clone.cscsName=this.cscsName;
		clone.cscsFlag=this.cscsFlag;
		clone.cscsUpdateTime=this.cscsUpdateTime;
		clone.cscsAddTime=this.cscsAddTime;
		clone.cscsRemark=this.cscsRemark;
		clone.cscsStatus=this.cscsStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取红包来源
	 * @param id
	 * @return
	 */
	public static @api CsCoinSource get(Long id){		
		return getCsCoinSourceById(id);
	}
	/**
	 * 获取所有红包来源
	 * @return
	 */
	public static @api List<CsCoinSource> list(Map params,Integer size){
		return getCsCoinSourceList(params,size);
	}
	/**
	 * 获取红包来源分页
	 * @return
	 */
	public static @api Page<CsCoinSource> page(int page,int size,Map params){
		return getCsCoinSourcePage(page, size , params);
	}
	/**
	 * 根据查询条件取红包来源
	 * @param params
	 * @return
	 */
	public static @api CsCoinSource Get(Map params){
		return getCsCoinSource(params);
	}
	/**
	 * 获取红包来源数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsCoinSourceCount(params);
	}
	/**
	 * 获取红包来源数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsCoinSourceEval(eval,params);
	}
	
	/**
	 * 根据ID取红包来源
	 * @param id
	 * @return
	 */
	public static @api CsCoinSource getCsCoinSourceById(Long id){		
		CsCoinSource csCoinSource = (CsCoinSource) $.GetRequest("CsCoinSource$"+id);
		if(csCoinSource!=null)
			return csCoinSource;
		com.ccclubs.service.admin.ICsCoinSourceService csCoinSourceService = $.GetSpringBean("csCoinSourceService");		
		return csCoinSourceService.getCsCoinSourceById(id);
	}
	
	
	/**
	 * 根据ID取红包来源的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsCoinSource.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsCoinSource csCoinSource = get(id);
		if(csCoinSource!=null){
			String strValue = csCoinSource.getCscsId$();
			if(!"CscsId".equals("CscsId"))
				strValue+="("+csCoinSource.getCscsId$()+")";
			MemCache.setValue(CsCoinSource.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCscsName$();
		if(!"CscsName".equals("CscsName"))
			keyValue+="("+this.getCscsName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有红包来源
	 * @return
	 */
	public static @api List<CsCoinSource> getCsCoinSourceList(Map params,Integer size){
		com.ccclubs.service.admin.ICsCoinSourceService csCoinSourceService = $.GetSpringBean("csCoinSourceService");
		return csCoinSourceService.getCsCoinSourceList(params, size);
	}
	
	/**
	 * 获取红包来源分页
	 * @return
	 */
	public static @api Page<CsCoinSource> getCsCoinSourcePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsCoinSourceService csCoinSourceService = $.GetSpringBean("csCoinSourceService");
		return csCoinSourceService.getCsCoinSourcePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取红包来源
	 * @param params
	 * @return
	 */
	public static @api CsCoinSource getCsCoinSource(Map params){
		com.ccclubs.service.admin.ICsCoinSourceService csCoinSourceService = $.GetSpringBean("csCoinSourceService");
		return csCoinSourceService.getCsCoinSource(params);
	}
	
	/**
	 * 获取红包来源数
	 * @return
	 */
	public static @api Long getCsCoinSourceCount(Map params){
		com.ccclubs.service.admin.ICsCoinSourceService csCoinSourceService = $.GetSpringBean("csCoinSourceService");
		return csCoinSourceService.getCsCoinSourceCount(params);
	}
		
		
	/**
	 * 获取红包来源自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsCoinSourceEval(String eval,Map params){
		com.ccclubs.service.admin.ICsCoinSourceService csCoinSourceService = $.GetSpringBean("csCoinSourceService");
		return csCoinSourceService.getCsCoinSourceEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsCoinSource(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsCoinSourceService csCoinSourceService = $.GetSpringBean("csCoinSourceService");
		csCoinSourceService.updateCsCoinSourceByConfirm(set, where);
	}
	
	
	/**
	 * 保存红包来源对象
	 * @param params
	 * @return
	 */
	public CsCoinSource save(){
		com.ccclubs.service.admin.ICsCoinSourceService csCoinSourceService = $.GetSpringBean("csCoinSourceService");
		if(this.getCscsId()!=null)
			csCoinSourceService.updateCsCoinSource(this);
		else
			return csCoinSourceService.saveCsCoinSource(this);
		return this;
	}
	
	
	/**
	 * 更新红包来源对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsCoinSourceService csCoinSourceService = $.GetSpringBean("csCoinSourceService");
		csCoinSourceService.updateCsCoinSource$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsCoinSourceService csCoinSourceService = $.GetSpringBean("csCoinSourceService");
		if($.equals($.config("logic_delete"),"true"))
			csCoinSourceService.removeCsCoinSourceById(this.getCscsId());
		else
			csCoinSourceService.deleteCsCoinSourceById(this.getCscsId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsCoinSourceService csCoinSourceService = $.GetSpringBean("csCoinSourceService");
		return csCoinSourceService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCscsId(){
		return this.cscsId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCscsId$(){
		String strValue="";
		 strValue=$.str(this.getCscsId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCscsId(Long cscsId){
		this.cscsId = cscsId;
		this.setSeted(F.cscsId);
	}
	/*******************************来源名称**********************************/	
	/**
	* 来源名称 [可空]      
	**/
	public String getCscsName(){
		return this.cscsName;
	}
	/**
	* 获取来源名称格式化(toString)
	**/
	public String getCscsName$(){
		String strValue="";
		 strValue=$.str(this.getCscsName());
	 	 return strValue;
	}
	/**
	* 来源名称 [可空]     
	**/
	public void setCscsName(String cscsName){
		this.cscsName = cscsName;
		this.setSeted(F.cscsName);
	}
	/*******************************标识**********************************/	
	/**
	* 标识 [非空]      
	**/
	public String getCscsFlag(){
		return this.cscsFlag;
	}
	/**
	* 获取标识格式化(toString)
	**/
	public String getCscsFlag$(){
		String strValue="";
		 strValue=$.str(this.getCscsFlag());
	 	 return strValue;
	}
	/**
	* 标识 [非空]     
	**/
	public void setCscsFlag(String cscsFlag){
		this.cscsFlag = cscsFlag;
		this.setSeted(F.cscsFlag);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCscsUpdateTime(){
		return this.cscsUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCscsUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscsUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCscsUpdateTime(Date cscsUpdateTime){
		this.cscsUpdateTime = cscsUpdateTime;
		this.setSeted(F.cscsUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCscsAddTime(){
		return this.cscsAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCscsAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscsAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]     
	**/
	public void setCscsAddTime(Date cscsAddTime){
		this.cscsAddTime = cscsAddTime;
		this.setSeted(F.cscsAddTime);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息 [非空]      
	**/
	public String getCscsRemark(){
		return this.cscsRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCscsRemark$(){
		String strValue="";
		 strValue=$.str(this.getCscsRemark());
	 	 return strValue;
	}
	/**
	* 备注信息 [非空]     
	**/
	public void setCscsRemark(String cscsRemark){
		this.cscsRemark = cscsRemark;
		this.setSeted(F.cscsRemark);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]      
	**/
	public Short getCscsStatus(){
		return this.cscsStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCscsStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCscsStatus()),"1"))
			 strValue=$.str("正常");
		 if($.equals($.str(this.getCscsStatus()),"0"))
			 strValue=$.str("无效");
	 	 return strValue;
	}
	/**
	* 状态 [非空]     
	**/
	public void setCscsStatus(Short cscsStatus){
		this.cscsStatus = cscsStatus;
		this.setSeted(F.cscsStatus);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsCoinSource.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCoinSource.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCoinSource.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsCoinSource.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsCoinSource.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCoinSource.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCoinSource.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsCoinSource.this);
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
		public M cscsId(Object cscsId){this.put("cscsId", cscsId);return this;};
		/** and cscs_id is null */
		public M cscsIdNull(){if(this.get("cscsIdNot")==null)this.put("cscsIdNot", "");this.put("cscsId", null);return this;};
		/** not .... */
		public M cscsIdNot(){this.put("cscsIdNot", "not");return this;};
		/** and cscs_id >= ? */
		public M cscsIdMin(Object min){this.put("cscsIdMin", min);return this;};
		/** and cscs_id <= ? */
		public M cscsIdMax(Object max){this.put("cscsIdMax", max);return this;};
		/** 来源名称 [可空]       **/
		public M cscsName(Object cscsName){this.put("cscsName", cscsName);return this;};
		/** and cscs_name is null */
		public M cscsNameNull(){if(this.get("cscsNameNot")==null)this.put("cscsNameNot", "");this.put("cscsName", null);return this;};
		/** not .... */
		public M cscsNameNot(){this.put("cscsNameNot", "not");return this;};
		/** 标识 [非空]       **/
		public M cscsFlag(Object cscsFlag){this.put("cscsFlag", cscsFlag);return this;};
		/** and cscs_flag is null */
		public M cscsFlagNull(){if(this.get("cscsFlagNot")==null)this.put("cscsFlagNot", "");this.put("cscsFlag", null);return this;};
		/** not .... */
		public M cscsFlagNot(){this.put("cscsFlagNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M cscsUpdateTime(Object cscsUpdateTime){this.put("cscsUpdateTime", cscsUpdateTime);return this;};
		/** and cscs_update_time is null */
		public M cscsUpdateTimeNull(){if(this.get("cscsUpdateTimeNot")==null)this.put("cscsUpdateTimeNot", "");this.put("cscsUpdateTime", null);return this;};
		/** not .... */
		public M cscsUpdateTimeNot(){this.put("cscsUpdateTimeNot", "not");return this;};
		/** and cscs_update_time >= ? */
 		public M cscsUpdateTimeStart(Object start){this.put("cscsUpdateTimeStart", start);return this;};			
 		/** and cscs_update_time <= ? */
 		public M cscsUpdateTimeEnd(Object end){this.put("cscsUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cscsAddTime(Object cscsAddTime){this.put("cscsAddTime", cscsAddTime);return this;};
		/** and cscs_add_time is null */
		public M cscsAddTimeNull(){if(this.get("cscsAddTimeNot")==null)this.put("cscsAddTimeNot", "");this.put("cscsAddTime", null);return this;};
		/** not .... */
		public M cscsAddTimeNot(){this.put("cscsAddTimeNot", "not");return this;};
		/** and cscs_add_time >= ? */
 		public M cscsAddTimeStart(Object start){this.put("cscsAddTimeStart", start);return this;};			
 		/** and cscs_add_time <= ? */
 		public M cscsAddTimeEnd(Object end){this.put("cscsAddTimeEnd", end);return this;};
		/** 备注信息 [非空]       **/
		public M cscsRemark(Object cscsRemark){this.put("cscsRemark", cscsRemark);return this;};
		/** and cscs_remark is null */
		public M cscsRemarkNull(){if(this.get("cscsRemarkNot")==null)this.put("cscsRemarkNot", "");this.put("cscsRemark", null);return this;};
		/** not .... */
		public M cscsRemarkNot(){this.put("cscsRemarkNot", "not");return this;};
		/** 状态 [非空]       **/
		public M cscsStatus(Object cscsStatus){this.put("cscsStatus", cscsStatus);return this;};
		/** and cscs_status is null */
		public M cscsStatusNull(){if(this.get("cscsStatusNot")==null)this.put("cscsStatusNot", "");this.put("cscsStatus", null);return this;};
		/** not .... */
		public M cscsStatusNot(){this.put("cscsStatusNot", "not");return this;};
		/** and cscs_status >= ? */
		public M cscsStatusMin(Object min){this.put("cscsStatusMin", min);return this;};
		/** and cscs_status <= ? */
		public M cscsStatusMax(Object max){this.put("cscsStatusMax", max);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有红包来源 **/
		public @api List<CsCoinSource> list(Integer size){
			return getCsCoinSourceList(this,size);
		}
		/** 获取红包来源分页 **/
		public @api Page<CsCoinSource> page(int page,int size){
			return getCsCoinSourcePage(page, size , this);
		}
		/** 根据查询条件取红包来源 **/
		public @api CsCoinSource get(){
			return getCsCoinSource(this);
		}
		/** 获取红包来源数 **/
		public @api Long count(){
			return getCsCoinSourceCount(this);
		}
		/** 获取红包来源表达式 **/
		public @api <T> T eval(String strEval){
			return getCsCoinSourceEval(strEval,this);
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
			updateCsCoinSource(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscsId="cscsId";
		/** 来源名称 [可空]       **/
		public final static @type(String.class)  String cscsName="cscsName";
		/** 标识 [非空]       **/
		public final static @type(String.class)  String cscsFlag="cscsFlag";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cscsUpdateTime="cscsUpdateTime";
		/** and cscs_update_time >= ? */
 		public final static @type(Date.class) String cscsUpdateTimeStart="cscsUpdateTimeStart";
 		/** and cscs_update_time <= ? */
 		public final static @type(Date.class) String cscsUpdateTimeEnd="cscsUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cscsAddTime="cscsAddTime";
		/** and cscs_add_time >= ? */
 		public final static @type(Date.class) String cscsAddTimeStart="cscsAddTimeStart";
 		/** and cscs_add_time <= ? */
 		public final static @type(Date.class) String cscsAddTimeEnd="cscsAddTimeEnd";
		/** 备注信息 [非空]       **/
		public final static @type(String.class)  String cscsRemark="cscsRemark";
		/** 状态 [非空]       **/
		public final static @type(Short.class)  String cscsStatus="cscsStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscsId="cscs_id";
		/** 来源名称 [可空]       **/
		public final static String cscsName="cscs_name";
		/** 标识 [非空]       **/
		public final static String cscsFlag="cscs_flag";
		/** 修改时间 [非空]       **/
		public final static String cscsUpdateTime="cscs_update_time";
		/** 添加时间 [非空]       **/
		public final static String cscsAddTime="cscs_add_time";
		/** 备注信息 [非空]       **/
		public final static String cscsRemark="cscs_remark";
		/** 状态 [非空]       **/
		public final static String cscsStatus="cscs_status";
		
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
				$.Set(name,CsCoinSource.getCsCoinSource(params));
			else
				$.Set(name,CsCoinSource.getCsCoinSourceList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取红包来源数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsCoinSource) $.GetRequest("CsCoinSource$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsCoinSource.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsCoinSource.getCsCoinSource(params);
			else
				value = CsCoinSource.getCsCoinSourceList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsCoinSource.Get($.add(CsCoinSource.F.cscsId,param));
		else if(!$.empty(param.toString()))
			value = CsCoinSource.get(Long.valueOf(param.toString()));
		$.SetRequest("CsCoinSource$"+param.hashCode(), value);
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
	public void mergeSet(CsCoinSource old){
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