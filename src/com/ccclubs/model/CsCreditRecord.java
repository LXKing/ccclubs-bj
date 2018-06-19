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

@namespace("user/credit/record")
public @caption("信用记录") @table("cs_credit_record") class CsCreditRecord implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cscr_id")   @primary  @note("  ") Long cscrId;// 主键 非空     
	private @caption("信用描述") @column("cscr_title")    @note("  ") String cscrTitle;// 非空     
	private @caption("所属会员") @column("cscr_member")    @relate("$cscrMember") @RelateClass(CsMember.class)  @note("  ") Long cscrMember;// 非空     
 	private CsMember $cscrMember;//关联对象[会员帐号]
	private @caption("信用值") @column("cscr_value")    @note("  ") Double cscrValue;// 非空     
	private @caption("修改时间") @column("cscr_update_time")    @note("  ") Date cscrUpdateTime;// 非空     
	private @caption("添加时间") @column("cscr_add_time")    @note("  ") Date cscrAddTime;// 非空     
	private @caption("状态") @column("cscr_status")    @note(" 1:正常 0:无效  ") Short cscrStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsCreditRecord(){
	
	}
	
	//主键构造函数
	public CsCreditRecord(Long id){
		this.cscrId = id;
	}
	
	/**所有字段构造函数 CsCreditRecord(cscrTitle,cscrMember,cscrValue,cscrUpdateTime,cscrAddTime,cscrStatus)
	 CsCreditRecord(
	 	$.getString("cscrTitle")//信用描述 [非空]
	 	,$.getLong("cscrMember")//所属会员 [非空]
	 	,$.getDouble("cscrValue")//信用值 [非空]
	 	,$.getDate("cscrUpdateTime")//修改时间 [非空]
	 	,$.getDate("cscrAddTime")//添加时间 [非空]
	 	,$.getShort("cscrStatus")//状态 [非空]
	 )
	**/
	public CsCreditRecord(String cscrTitle,Long cscrMember,Double cscrValue,Date cscrUpdateTime,Date cscrAddTime,Short cscrStatus){
		this.cscrTitle=cscrTitle;
		this.cscrMember=cscrMember;
		this.cscrValue=cscrValue;
		this.cscrUpdateTime=cscrUpdateTime;
		this.cscrAddTime=cscrAddTime;
		this.cscrStatus=cscrStatus;
	}
	
	//设置非空字段
	public CsCreditRecord setNotNull(String cscrTitle,Long cscrMember,Double cscrValue,Date cscrUpdateTime,Date cscrAddTime,Short cscrStatus){
		this.cscrTitle=cscrTitle;
		this.cscrMember=cscrMember;
		this.cscrValue=cscrValue;
		this.cscrUpdateTime=cscrUpdateTime;
		this.cscrAddTime=cscrAddTime;
		this.cscrStatus=cscrStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsCreditRecord cscrId(Long cscrId){
		this.cscrId = cscrId;
		this.setSeted(F.cscrId);
		return this;
	}
	/** 信用描述 [非空]       **/
	public CsCreditRecord cscrTitle(String cscrTitle){
		this.cscrTitle = cscrTitle;
		this.setSeted(F.cscrTitle);
		return this;
	}
	/** 所属会员 [非空] [CsMember]      **/
	public CsCreditRecord cscrMember(Long cscrMember){
		this.cscrMember = cscrMember;
		this.setSeted(F.cscrMember);
		return this;
	}
	/** 信用值 [非空]       **/
	public CsCreditRecord cscrValue(Double cscrValue){
		this.cscrValue = cscrValue;
		this.setSeted(F.cscrValue);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsCreditRecord cscrUpdateTime(Date cscrUpdateTime){
		this.cscrUpdateTime = cscrUpdateTime;
		this.setSeted(F.cscrUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsCreditRecord cscrAddTime(Date cscrAddTime){
		this.cscrAddTime = cscrAddTime;
		this.setSeted(F.cscrAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsCreditRecord cscrStatus(Short cscrStatus){
		this.cscrStatus = cscrStatus;
		this.setSeted(F.cscrStatus);
		return this;
	}
	
	
	//克隆对象
	public CsCreditRecord clone(){
		CsCreditRecord clone = new CsCreditRecord();
		clone.cscrTitle=this.cscrTitle;
		clone.cscrMember=this.cscrMember;
		clone.cscrValue=this.cscrValue;
		clone.cscrUpdateTime=this.cscrUpdateTime;
		clone.cscrAddTime=this.cscrAddTime;
		clone.cscrStatus=this.cscrStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取信用记录
	 * @param id
	 * @return
	 */
	public static @api CsCreditRecord get(Long id){		
		return getCsCreditRecordById(id);
	}
	/**
	 * 获取所有信用记录
	 * @return
	 */
	public static @api List<CsCreditRecord> list(Map params,Integer size){
		return getCsCreditRecordList(params,size);
	}
	/**
	 * 获取信用记录分页
	 * @return
	 */
	public static @api Page<CsCreditRecord> page(int page,int size,Map params){
		return getCsCreditRecordPage(page, size , params);
	}
	/**
	 * 根据查询条件取信用记录
	 * @param params
	 * @return
	 */
	public static @api CsCreditRecord Get(Map params){
		return getCsCreditRecord(params);
	}
	/**
	 * 获取信用记录数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsCreditRecordCount(params);
	}
	/**
	 * 获取信用记录数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsCreditRecordEval(eval,params);
	}
	
	/**
	 * 根据ID取信用记录
	 * @param id
	 * @return
	 */
	public static @api CsCreditRecord getCsCreditRecordById(Long id){		
		CsCreditRecord csCreditRecord = (CsCreditRecord) $.GetRequest("CsCreditRecord$"+id);
		if(csCreditRecord!=null)
			return csCreditRecord;
		com.ccclubs.service.admin.ICsCreditRecordService csCreditRecordService = $.GetSpringBean("csCreditRecordService");		
		return csCreditRecordService.getCsCreditRecordById(id);
	}
	
	
	/**
	 * 根据ID取信用记录的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsCreditRecord.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsCreditRecord csCreditRecord = get(id);
		if(csCreditRecord!=null){
			String strValue = csCreditRecord.getCscrTitle$();
			if(!"CscrTitle".equals("CscrTitle"))
				strValue+="("+csCreditRecord.getCscrTitle$()+")";
			MemCache.setValue(CsCreditRecord.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCscrTitle$();
		if(!"CscrTitle".equals("CscrTitle"))
			keyValue+="("+this.getCscrTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有信用记录
	 * @return
	 */
	public static @api List<CsCreditRecord> getCsCreditRecordList(Map params,Integer size){
		com.ccclubs.service.admin.ICsCreditRecordService csCreditRecordService = $.GetSpringBean("csCreditRecordService");
		return csCreditRecordService.getCsCreditRecordList(params, size);
	}
	
	/**
	 * 获取信用记录分页
	 * @return
	 */
	public static @api Page<CsCreditRecord> getCsCreditRecordPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsCreditRecordService csCreditRecordService = $.GetSpringBean("csCreditRecordService");
		return csCreditRecordService.getCsCreditRecordPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取信用记录
	 * @param params
	 * @return
	 */
	public static @api CsCreditRecord getCsCreditRecord(Map params){
		com.ccclubs.service.admin.ICsCreditRecordService csCreditRecordService = $.GetSpringBean("csCreditRecordService");
		return csCreditRecordService.getCsCreditRecord(params);
	}
	
	/**
	 * 获取信用记录数
	 * @return
	 */
	public static @api Long getCsCreditRecordCount(Map params){
		com.ccclubs.service.admin.ICsCreditRecordService csCreditRecordService = $.GetSpringBean("csCreditRecordService");
		return csCreditRecordService.getCsCreditRecordCount(params);
	}
		
		
	/**
	 * 获取信用记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsCreditRecordEval(String eval,Map params){
		com.ccclubs.service.admin.ICsCreditRecordService csCreditRecordService = $.GetSpringBean("csCreditRecordService");
		return csCreditRecordService.getCsCreditRecordEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsCreditRecord(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsCreditRecordService csCreditRecordService = $.GetSpringBean("csCreditRecordService");
		csCreditRecordService.updateCsCreditRecordByConfirm(set, where);
	}
	
	
	/**
	 * 保存信用记录对象
	 * @param params
	 * @return
	 */
	public CsCreditRecord save(){
		com.ccclubs.service.admin.ICsCreditRecordService csCreditRecordService = $.GetSpringBean("csCreditRecordService");
		if(this.getCscrId()!=null)
			csCreditRecordService.updateCsCreditRecord(this);
		else
			return csCreditRecordService.saveCsCreditRecord(this);
		return this;
	}
	
	
	/**
	 * 更新信用记录对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsCreditRecordService csCreditRecordService = $.GetSpringBean("csCreditRecordService");
		csCreditRecordService.updateCsCreditRecord$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsCreditRecordService csCreditRecordService = $.GetSpringBean("csCreditRecordService");
		if($.equals($.config("logic_delete"),"true"))
			csCreditRecordService.removeCsCreditRecordById(this.getCscrId());
		else
			csCreditRecordService.deleteCsCreditRecordById(this.getCscrId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsCreditRecordService csCreditRecordService = $.GetSpringBean("csCreditRecordService");
		return csCreditRecordService.executeTransaction(function);
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
	/*******************************信用描述**********************************/	
	/**
	* 信用描述 [非空]      
	**/
	public String getCscrTitle(){
		return this.cscrTitle;
	}
	/**
	* 获取信用描述格式化(toString)
	**/
	public String getCscrTitle$(){
		String strValue="";
		 strValue=$.str(this.getCscrTitle());
	 	 return strValue;
	}
	/**
	* 信用描述 [非空]      
	**/
	public void setCscrTitle(String cscrTitle){
		this.cscrTitle = cscrTitle;
		this.setSeted(F.cscrTitle);
	}
	/*******************************所属会员**********************************/	
	/**
	* 所属会员 [非空] [CsMember]     
	**/
	public Long getCscrMember(){
		return this.cscrMember;
	}
	/**
	* 获取所属会员格式化(toString)
	**/
	public String getCscrMember$(){
		String strValue="";
		if(this.getCscrMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCscrMember()));
		}			
	 	 return strValue;
	}
	/**
	* 所属会员 [非空] [CsMember]     
	**/
	public void setCscrMember(Long cscrMember){
		this.cscrMember = cscrMember;
		this.setSeted(F.cscrMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$cscrMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCscrMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCscrMember()!=null){
 			csMember = CsMember.get(this.getCscrMember());
 		}
 		$.SetRequest("CsMember$"+this.getCscrMember(), csMember);
	 	return csMember;
	}
	/*******************************信用值**********************************/	
	/**
	* 信用值 [非空]      
	**/
	public Double getCscrValue(){
		return this.cscrValue;
	}
	/**
	* 获取信用值格式化(toString)
	**/
	public String getCscrValue$(){
		String strValue="";
		 strValue=$.str(this.getCscrValue());	
	 	 return strValue;
	}
	/**
	* 信用值 [非空]      
	**/
	public void setCscrValue(Double cscrValue){
		this.cscrValue = cscrValue;
		this.setSeted(F.cscrValue);
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
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCscrAddTime(){
		return this.cscrAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCscrAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscrAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCscrAddTime(Date cscrAddTime){
		this.cscrAddTime = cscrAddTime;
		this.setSeted(F.cscrAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
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
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCscrStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCscrStatus(Short cscrStatus){
		this.cscrStatus = cscrStatus;
		this.setSeted(F.cscrStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsCreditRecord.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCreditRecord.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCreditRecord.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsCreditRecord.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsCreditRecord.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCreditRecord.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCreditRecord.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsCreditRecord.this);
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
		/** 信用描述 [非空]       **/
		public M cscrTitle(Object cscrTitle){this.put("cscrTitle", cscrTitle);return this;};
	 	/** and cscr_title is null */
 		public M cscrTitleNull(){if(this.get("cscrTitleNot")==null)this.put("cscrTitleNot", "");this.put("cscrTitle", null);return this;};
 		/** not .... */
 		public M cscrTitleNot(){this.put("cscrTitleNot", "not");return this;};
		/** 所属会员 [非空] [CsMember]      **/
		public M cscrMember(Object cscrMember){this.put("cscrMember", cscrMember);return this;};
	 	/** and cscr_member is null */
 		public M cscrMemberNull(){if(this.get("cscrMemberNot")==null)this.put("cscrMemberNot", "");this.put("cscrMember", null);return this;};
 		/** not .... */
 		public M cscrMemberNot(){this.put("cscrMemberNot", "not");return this;};
		public M cscrMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("cscrMember$on", value);
			return this;
		};	
		/** 信用值 [非空]       **/
		public M cscrValue(Object cscrValue){this.put("cscrValue", cscrValue);return this;};
	 	/** and cscr_value is null */
 		public M cscrValueNull(){if(this.get("cscrValueNot")==null)this.put("cscrValueNot", "");this.put("cscrValue", null);return this;};
 		/** not .... */
 		public M cscrValueNot(){this.put("cscrValueNot", "not");return this;};
		/** and cscr_value >= ? */
		public M cscrValueMin(Object min){this.put("cscrValueMin", min);return this;};
		/** and cscr_value <= ? */
		public M cscrValueMax(Object max){this.put("cscrValueMax", max);return this;};
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
		/** 添加时间 [非空]       **/
		public M cscrAddTime(Object cscrAddTime){this.put("cscrAddTime", cscrAddTime);return this;};
	 	/** and cscr_add_time is null */
 		public M cscrAddTimeNull(){if(this.get("cscrAddTimeNot")==null)this.put("cscrAddTimeNot", "");this.put("cscrAddTime", null);return this;};
 		/** not .... */
 		public M cscrAddTimeNot(){this.put("cscrAddTimeNot", "not");return this;};
 		/** and cscr_add_time >= ? */
 		public M cscrAddTimeStart(Object start){this.put("cscrAddTimeStart", start);return this;};			
 		/** and cscr_add_time <= ? */
 		public M cscrAddTimeEnd(Object end){this.put("cscrAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M cscrStatus(Object cscrStatus){this.put("cscrStatus", cscrStatus);return this;};
	 	/** and cscr_status is null */
 		public M cscrStatusNull(){if(this.get("cscrStatusNot")==null)this.put("cscrStatusNot", "");this.put("cscrStatus", null);return this;};
 		/** not .... */
 		public M cscrStatusNot(){this.put("cscrStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有信用记录 **/
		public @api List<CsCreditRecord> list(Integer size){
			return getCsCreditRecordList(this,size);
		}
		/** 获取信用记录分页 **/
		public @api Page<CsCreditRecord> page(int page,int size){
			return getCsCreditRecordPage(page, size , this);
		}
		/** 根据查询条件取信用记录 **/
		public @api CsCreditRecord get(){
			return getCsCreditRecord(this);
		}
		/** 获取信用记录数 **/
		public @api Long count(){
			return getCsCreditRecordCount(this);
		}
		/** 获取信用记录表达式 **/
		public @api <T> T eval(String strEval){
			return getCsCreditRecordEval(strEval,this);
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
			updateCsCreditRecord(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscrId="cscrId";
		/** 信用描述 [非空]       **/
		public final static @type(String.class) @like String cscrTitle="cscrTitle";
		/** 所属会员 [非空] [CsMember]      **/
		public final static @type(Long.class)  String cscrMember="cscrMember";
		/** 信用值 [非空]       **/
		public final static @type(Double.class)  String cscrValue="cscrValue";
		/** and cscr_value >= ? */
		public final static @type(Double.class) String cscrValueMin="cscrValueMin";
		/** and cscr_value <= ? */
		public final static @type(Double.class) String cscrValueMax="cscrValueMax";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cscrUpdateTime="cscrUpdateTime";
	 	/** and cscr_update_time >= ? */
 		public final static @type(Date.class) String cscrUpdateTimeStart="cscrUpdateTimeStart";
 		/** and cscr_update_time <= ? */
 		public final static @type(Date.class) String cscrUpdateTimeEnd="cscrUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cscrAddTime="cscrAddTime";
	 	/** and cscr_add_time >= ? */
 		public final static @type(Date.class) String cscrAddTimeStart="cscrAddTimeStart";
 		/** and cscr_add_time <= ? */
 		public final static @type(Date.class) String cscrAddTimeEnd="cscrAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String cscrStatus="cscrStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscrId="cscr_id";
		/** 信用描述 [非空]       **/
		public final static String cscrTitle="cscr_title";
		/** 所属会员 [非空] [CsMember]      **/
		public final static String cscrMember="cscr_member";
		/** 信用值 [非空]       **/
		public final static String cscrValue="cscr_value";
		/** 修改时间 [非空]       **/
		public final static String cscrUpdateTime="cscr_update_time";
		/** 添加时间 [非空]       **/
		public final static String cscrAddTime="cscr_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
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
				$.Set(name,CsCreditRecord.getCsCreditRecord(params));
			else
				$.Set(name,CsCreditRecord.getCsCreditRecordList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取信用记录数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsCreditRecord) $.GetRequest("CsCreditRecord$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsCreditRecord.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsCreditRecord.getCsCreditRecord(params);
			else
				value = CsCreditRecord.getCsCreditRecordList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsCreditRecord.Get($.add(CsCreditRecord.F.cscrId,param));
		else if(!$.empty(param.toString()))
			value = CsCreditRecord.get(Long.valueOf(param.toString()));
		$.SetRequest("CsCreditRecord$"+param.hashCode(), value);
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
	public void mergeSet(CsCreditRecord old){
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