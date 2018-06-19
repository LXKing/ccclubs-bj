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

@namespace("unit/specialmodel")
public @caption("专车车型") @table("cs_special_model") class CsSpecialModel implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cssm_id")   @primary  @note("  ") Long cssmId;// 主键 非空     
	private @caption("车型名称") @column("cssm_name")    @note("  ") String cssmName;// 非空     
	private @caption("三方名称") @column("cssm_third")    @note(" 1:滴滴专车 2:易道专车  ") Long cssmThird;// 非空 1:滴滴专车 2:易道专车     
	private @caption("三方代码") @column("cssm_third_code")    @note("  ") String cssmThirdCode;//     
	private @caption("修改时间") @column("cssm_update_time")    @note("  ") Date cssmUpdateTime;// 非空     
	private @caption("添加时间") @column("cssm_add_time")    @note("  ") Date cssmAddTime;// 非空     
	private @caption("状态") @column("cssm_status")    @note(" 1:正常 0:无效  ") Short cssmStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsSpecialModel(){
	
	}
	
	//主键构造函数
	public CsSpecialModel(Long id){
		this.cssmId = id;
	}
	
	/**所有字段构造函数 CsSpecialModel(cssmName,cssmThird,cssmThirdCode,cssmUpdateTime,cssmAddTime,cssmStatus)
	 CsSpecialModel(
	 	$.getString("cssmName")//车型名称 [非空]
	 	,$.getLong("cssmThird")//三方名称 [非空]
	 	,$.getString("cssmThirdCode")//三方代码
	 	,$.getDate("cssmUpdateTime")//修改时间 [非空]
	 	,$.getDate("cssmAddTime")//添加时间 [非空]
	 	,$.getShort("cssmStatus")//状态 [非空]
	 )
	**/
	public CsSpecialModel(String cssmName,Long cssmThird,String cssmThirdCode,Date cssmUpdateTime,Date cssmAddTime,Short cssmStatus){
		this.cssmName=cssmName;
		this.cssmThird=cssmThird;
		this.cssmThirdCode=cssmThirdCode;
		this.cssmUpdateTime=cssmUpdateTime;
		this.cssmAddTime=cssmAddTime;
		this.cssmStatus=cssmStatus;
	}
	
	//设置非空字段
	public CsSpecialModel setNotNull(String cssmName,Long cssmThird,Date cssmUpdateTime,Date cssmAddTime,Short cssmStatus){
		this.cssmName=cssmName;
		this.cssmThird=cssmThird;
		this.cssmUpdateTime=cssmUpdateTime;
		this.cssmAddTime=cssmAddTime;
		this.cssmStatus=cssmStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsSpecialModel cssmId(Long cssmId){
		this.cssmId = cssmId;
		this.setSeted(F.cssmId);
		return this;
	}
	/** 车型名称 [非空]       **/
	public CsSpecialModel cssmName(String cssmName){
		this.cssmName = cssmName;
		this.setSeted(F.cssmName);
		return this;
	}
	/** 三方名称 [非空]   1:滴滴专车 2:易道专车     **/
	public CsSpecialModel cssmThird(Long cssmThird){
		this.cssmThird = cssmThird;
		this.setSeted(F.cssmThird);
		return this;
	}
	/** 三方代码        **/
	public CsSpecialModel cssmThirdCode(String cssmThirdCode){
		this.cssmThirdCode = cssmThirdCode;
		this.setSeted(F.cssmThirdCode);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsSpecialModel cssmUpdateTime(Date cssmUpdateTime){
		this.cssmUpdateTime = cssmUpdateTime;
		this.setSeted(F.cssmUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsSpecialModel cssmAddTime(Date cssmAddTime){
		this.cssmAddTime = cssmAddTime;
		this.setSeted(F.cssmAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsSpecialModel cssmStatus(Short cssmStatus){
		this.cssmStatus = cssmStatus;
		this.setSeted(F.cssmStatus);
		return this;
	}
	
	
	//克隆对象
	public CsSpecialModel clone(){
		CsSpecialModel clone = new CsSpecialModel();
		clone.cssmName=this.cssmName;
		clone.cssmThird=this.cssmThird;
		clone.cssmUpdateTime=this.cssmUpdateTime;
		clone.cssmAddTime=this.cssmAddTime;
		clone.cssmStatus=this.cssmStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取专车车型
	 * @param id
	 * @return
	 */
	public static @api CsSpecialModel get(Long id){		
		return getCsSpecialModelById(id);
	}
	/**
	 * 获取所有专车车型
	 * @return
	 */
	public static @api List<CsSpecialModel> list(Map params,Integer size){
		return getCsSpecialModelList(params,size);
	}
	/**
	 * 获取专车车型分页
	 * @return
	 */
	public static @api Page<CsSpecialModel> page(int page,int size,Map params){
		return getCsSpecialModelPage(page, size , params);
	}
	/**
	 * 根据查询条件取专车车型
	 * @param params
	 * @return
	 */
	public static @api CsSpecialModel Get(Map params){
		return getCsSpecialModel(params);
	}
	/**
	 * 获取专车车型数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsSpecialModelCount(params);
	}
	/**
	 * 获取专车车型数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsSpecialModelEval(eval,params);
	}
	
	/**
	 * 根据ID取专车车型
	 * @param id
	 * @return
	 */
	public static @api CsSpecialModel getCsSpecialModelById(Long id){		
		CsSpecialModel csSpecialModel = (CsSpecialModel) $.GetRequest("CsSpecialModel$"+id);
		if(csSpecialModel!=null)
			return csSpecialModel;
		com.ccclubs.service.admin.ICsSpecialModelService csSpecialModelService = $.GetSpringBean("csSpecialModelService");		
		return csSpecialModelService.getCsSpecialModelById(id);
	}
	
	
	/**
	 * 根据ID取专车车型的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsSpecialModel.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsSpecialModel csSpecialModel = get(id);
		if(csSpecialModel!=null){
			String strValue = csSpecialModel.getCssmName$();
			if(!"CssmName".equals("CssmName"))
				strValue+="("+csSpecialModel.getCssmName$()+")";
			MemCache.setValue(CsSpecialModel.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCssmName$();
		if(!"CssmName".equals("CssmName"))
			keyValue+="("+this.getCssmName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有专车车型
	 * @return
	 */
	public static @api List<CsSpecialModel> getCsSpecialModelList(Map params,Integer size){
		com.ccclubs.service.admin.ICsSpecialModelService csSpecialModelService = $.GetSpringBean("csSpecialModelService");
		return csSpecialModelService.getCsSpecialModelList(params, size);
	}
	
	/**
	 * 获取专车车型分页
	 * @return
	 */
	public static @api Page<CsSpecialModel> getCsSpecialModelPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsSpecialModelService csSpecialModelService = $.GetSpringBean("csSpecialModelService");
		return csSpecialModelService.getCsSpecialModelPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取专车车型
	 * @param params
	 * @return
	 */
	public static @api CsSpecialModel getCsSpecialModel(Map params){
		com.ccclubs.service.admin.ICsSpecialModelService csSpecialModelService = $.GetSpringBean("csSpecialModelService");
		return csSpecialModelService.getCsSpecialModel(params);
	}
	
	/**
	 * 获取专车车型数
	 * @return
	 */
	public static @api Long getCsSpecialModelCount(Map params){
		com.ccclubs.service.admin.ICsSpecialModelService csSpecialModelService = $.GetSpringBean("csSpecialModelService");
		return csSpecialModelService.getCsSpecialModelCount(params);
	}
		
		
	/**
	 * 获取专车车型自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsSpecialModelEval(String eval,Map params){
		com.ccclubs.service.admin.ICsSpecialModelService csSpecialModelService = $.GetSpringBean("csSpecialModelService");
		return csSpecialModelService.getCsSpecialModelEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsSpecialModel(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsSpecialModelService csSpecialModelService = $.GetSpringBean("csSpecialModelService");
		csSpecialModelService.updateCsSpecialModelByConfirm(set, where);
	}
	
	
	/**
	 * 保存专车车型对象
	 * @param params
	 * @return
	 */
	public CsSpecialModel save(){
		com.ccclubs.service.admin.ICsSpecialModelService csSpecialModelService = $.GetSpringBean("csSpecialModelService");
		if(this.getCssmId()!=null)
			csSpecialModelService.updateCsSpecialModel(this);
		else
			return csSpecialModelService.saveCsSpecialModel(this);
		return this;
	}
	
	
	/**
	 * 更新专车车型对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsSpecialModelService csSpecialModelService = $.GetSpringBean("csSpecialModelService");
		csSpecialModelService.updateCsSpecialModel$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsSpecialModelService csSpecialModelService = $.GetSpringBean("csSpecialModelService");
		if($.equals($.config("logic_delete"),"true"))
			csSpecialModelService.removeCsSpecialModelById(this.getCssmId());
		else
			csSpecialModelService.deleteCsSpecialModelById(this.getCssmId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsSpecialModelService csSpecialModelService = $.GetSpringBean("csSpecialModelService");
		return csSpecialModelService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCssmId(){
		return this.cssmId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCssmId$(){
		String strValue="";
		 strValue=$.str(this.getCssmId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCssmId(Long cssmId){
		this.cssmId = cssmId;
		this.setSeted(F.cssmId);
	}
	/*******************************车型名称**********************************/	
	/**
	* 车型名称 [非空]      
	**/
	public String getCssmName(){
		return this.cssmName;
	}
	/**
	* 获取车型名称格式化(toString)
	**/
	public String getCssmName$(){
		String strValue="";
		 strValue=$.str(this.getCssmName());
	 	 return strValue;
	}
	/**
	* 车型名称 [非空]      
	**/
	public void setCssmName(String cssmName){
		this.cssmName = cssmName;
		this.setSeted(F.cssmName);
	}
	/*******************************三方名称**********************************/	
	/**
	* 三方名称 [非空]   1:滴滴专车 2:易道专车    
	**/
	public Long getCssmThird(){
		return this.cssmThird;
	}
	/**
	* 获取三方名称格式化(toString)
	**/
	public String getCssmThird$(){
		String strValue="";
		 if($.equals($.str(this.getCssmThird()),"1"))
			strValue=$.str("滴滴专车");		 
		 if($.equals($.str(this.getCssmThird()),"2"))
			strValue=$.str("易道专车");		 
	 	 return strValue;
	}
	/**
	* 三方名称 [非空]   1:滴滴专车 2:易道专车    
	**/
	public void setCssmThird(Long cssmThird){
		this.cssmThird = cssmThird;
		this.setSeted(F.cssmThird);
	}
	/*******************************三方代码**********************************/	
	/**
	* 三方代码       
	**/
	public String getCssmThirdCode(){
		return this.cssmThirdCode;
	}
	/**
	* 获取三方代码格式化(toString)
	**/
	public String getCssmThirdCode$(){
		String strValue="";
		 strValue=$.str(this.getCssmThirdCode());
	 	 return strValue;
	}
	/**
	* 三方代码       
	**/
	public void setCssmThirdCode(String cssmThirdCode){
		this.cssmThirdCode = cssmThirdCode;
		this.setSeted(F.cssmThirdCode);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCssmUpdateTime(){
		return this.cssmUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCssmUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssmUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCssmUpdateTime(Date cssmUpdateTime){
		this.cssmUpdateTime = cssmUpdateTime;
		this.setSeted(F.cssmUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCssmAddTime(){
		return this.cssmAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCssmAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssmAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCssmAddTime(Date cssmAddTime){
		this.cssmAddTime = cssmAddTime;
		this.setSeted(F.cssmAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCssmStatus(){
		return this.cssmStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCssmStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCssmStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCssmStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCssmStatus(Short cssmStatus){
		this.cssmStatus = cssmStatus;
		this.setSeted(F.cssmStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsSpecialModel.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsSpecialModel.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsSpecialModel.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsSpecialModel.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsSpecialModel.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsSpecialModel.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsSpecialModel.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsSpecialModel.this);
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
		public M cssmId(Object cssmId){this.put("cssmId", cssmId);return this;};
	 	/** and cssm_id is null */
 		public M cssmIdNull(){if(this.get("cssmIdNot")==null)this.put("cssmIdNot", "");this.put("cssmId", null);return this;};
 		/** not .... */
 		public M cssmIdNot(){this.put("cssmIdNot", "not");return this;};
		/** 车型名称 [非空]       **/
		public M cssmName(Object cssmName){this.put("cssmName", cssmName);return this;};
	 	/** and cssm_name is null */
 		public M cssmNameNull(){if(this.get("cssmNameNot")==null)this.put("cssmNameNot", "");this.put("cssmName", null);return this;};
 		/** not .... */
 		public M cssmNameNot(){this.put("cssmNameNot", "not");return this;};
		/** 三方名称 [非空]   1:滴滴专车 2:易道专车     **/
		public M cssmThird(Object cssmThird){this.put("cssmThird", cssmThird);return this;};
	 	/** and cssm_third is null */
 		public M cssmThirdNull(){if(this.get("cssmThirdNot")==null)this.put("cssmThirdNot", "");this.put("cssmThird", null);return this;};
 		/** not .... */
 		public M cssmThirdNot(){this.put("cssmThirdNot", "not");return this;};
		/** 三方代码        **/
		public M cssmThirdCode(Object cssmThirdCode){this.put("cssmThirdCode", cssmThirdCode);return this;};
	 	/** and cssm_third_code is null */
 		public M cssmThirdCodeNull(){if(this.get("cssmThirdCodeNot")==null)this.put("cssmThirdCodeNot", "");this.put("cssmThirdCode", null);return this;};
 		/** not .... */
 		public M cssmThirdCodeNot(){this.put("cssmThirdCodeNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M cssmUpdateTime(Object cssmUpdateTime){this.put("cssmUpdateTime", cssmUpdateTime);return this;};
	 	/** and cssm_update_time is null */
 		public M cssmUpdateTimeNull(){if(this.get("cssmUpdateTimeNot")==null)this.put("cssmUpdateTimeNot", "");this.put("cssmUpdateTime", null);return this;};
 		/** not .... */
 		public M cssmUpdateTimeNot(){this.put("cssmUpdateTimeNot", "not");return this;};
 		/** and cssm_update_time >= ? */
 		public M cssmUpdateTimeStart(Object start){this.put("cssmUpdateTimeStart", start);return this;};			
 		/** and cssm_update_time <= ? */
 		public M cssmUpdateTimeEnd(Object end){this.put("cssmUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cssmAddTime(Object cssmAddTime){this.put("cssmAddTime", cssmAddTime);return this;};
	 	/** and cssm_add_time is null */
 		public M cssmAddTimeNull(){if(this.get("cssmAddTimeNot")==null)this.put("cssmAddTimeNot", "");this.put("cssmAddTime", null);return this;};
 		/** not .... */
 		public M cssmAddTimeNot(){this.put("cssmAddTimeNot", "not");return this;};
 		/** and cssm_add_time >= ? */
 		public M cssmAddTimeStart(Object start){this.put("cssmAddTimeStart", start);return this;};			
 		/** and cssm_add_time <= ? */
 		public M cssmAddTimeEnd(Object end){this.put("cssmAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M cssmStatus(Object cssmStatus){this.put("cssmStatus", cssmStatus);return this;};
	 	/** and cssm_status is null */
 		public M cssmStatusNull(){if(this.get("cssmStatusNot")==null)this.put("cssmStatusNot", "");this.put("cssmStatus", null);return this;};
 		/** not .... */
 		public M cssmStatusNot(){this.put("cssmStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有专车车型 **/
		public @api List<CsSpecialModel> list(Integer size){
			return getCsSpecialModelList(this,size);
		}
		/** 获取专车车型分页 **/
		public @api Page<CsSpecialModel> page(int page,int size){
			return getCsSpecialModelPage(page, size , this);
		}
		/** 根据查询条件取专车车型 **/
		public @api CsSpecialModel get(){
			return getCsSpecialModel(this);
		}
		/** 获取专车车型数 **/
		public @api Long count(){
			return getCsSpecialModelCount(this);
		}
		/** 获取专车车型表达式 **/
		public @api <T> T eval(String strEval){
			return getCsSpecialModelEval(strEval,this);
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
			updateCsSpecialModel(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cssmId="cssmId";
		/** 车型名称 [非空]       **/
		public final static @type(String.class) @like String cssmName="cssmName";
		/** 三方名称 [非空]   1:滴滴专车 2:易道专车     **/
		public final static @type(Long.class)  String cssmThird="cssmThird";
		/** 三方代码        **/
		public final static @type(String.class) @like String cssmThirdCode="cssmThirdCode";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cssmUpdateTime="cssmUpdateTime";
	 	/** and cssm_update_time >= ? */
 		public final static @type(Date.class) String cssmUpdateTimeStart="cssmUpdateTimeStart";
 		/** and cssm_update_time <= ? */
 		public final static @type(Date.class) String cssmUpdateTimeEnd="cssmUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cssmAddTime="cssmAddTime";
	 	/** and cssm_add_time >= ? */
 		public final static @type(Date.class) String cssmAddTimeStart="cssmAddTimeStart";
 		/** and cssm_add_time <= ? */
 		public final static @type(Date.class) String cssmAddTimeEnd="cssmAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String cssmStatus="cssmStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cssmId="cssm_id";
		/** 车型名称 [非空]       **/
		public final static String cssmName="cssm_name";
		/** 三方名称 [非空]   1:滴滴专车 2:易道专车     **/
		public final static String cssmThird="cssm_third";
		/** 三方代码        **/
		public final static String cssmThirdCode="cssm_third_code";
		/** 修改时间 [非空]       **/
		public final static String cssmUpdateTime="cssm_update_time";
		/** 添加时间 [非空]       **/
		public final static String cssmAddTime="cssm_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String cssmStatus="cssm_status";
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
				$.Set(name,CsSpecialModel.getCsSpecialModel(params));
			else
				$.Set(name,CsSpecialModel.getCsSpecialModelList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取专车车型数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsSpecialModel) $.GetRequest("CsSpecialModel$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsSpecialModel.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsSpecialModel.getCsSpecialModel(params);
			else
				value = CsSpecialModel.getCsSpecialModelList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsSpecialModel.Get($.add(CsSpecialModel.F.cssmId,param));
		else if(!$.empty(param.toString()))
			value = CsSpecialModel.get(Long.valueOf(param.toString()));
		$.SetRequest("CsSpecialModel$"+param.hashCode(), value);
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
	public void mergeSet(CsSpecialModel old){
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