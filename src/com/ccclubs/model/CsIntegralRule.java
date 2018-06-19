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

@namespace("configurator/integralrule")
public @caption("积分规则") @table("cs_integral_rule") class CsIntegralRule implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csir_id")   @primary  @note("  ") Long csirId;// 主键 非空     
	private @caption("规则名称") @column("csir_name")    @note("  ") String csirName;// 非空     
	private @caption("标识") @column("csir_flag")    @note("  ") String csirFlag;// 非空     
	private @caption("规则描述") @column("csir_depict")    @note("  ") String csirDepict;//     
	private @caption("变动分数") @column("csir_integral")    @note("  ") Double csirIntegral;// 非空     
	private @caption("修改时间") @column("csir_update_time")    @note("  ") Date csirUpdateTime;// 非空     
	private @caption("添加时间") @column("csir_add_time")    @note("  ") Date csirAddTime;// 非空     
	private @caption("状态") @column("csir_status")    @note(" 1:正常 0:无效  ") Short csirStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsIntegralRule(){
	
	}
	
	//主键构造函数
	public CsIntegralRule(Long id){
		this.csirId = id;
	}
	
	/**所有字段构造函数 CsIntegralRule(csirName,csirFlag,csirDepict,csirIntegral,csirUpdateTime,csirAddTime,csirStatus)
	 CsIntegralRule(
	 	$.getString("csirName")//规则名称 [非空]
	 	,$.getString("csirFlag")//标识 [非空]
	 	,$.getString("csirDepict")//规则描述
	 	,$.getDouble("csirIntegral")//变动分数 [非空]
	 	,$.getDate("csirUpdateTime")//修改时间 [非空]
	 	,$.getDate("csirAddTime")//添加时间 [非空]
	 	,$.getShort("csirStatus")//状态 [非空]
	 )
	**/
	public CsIntegralRule(String csirName,String csirFlag,String csirDepict,Double csirIntegral,Date csirUpdateTime,Date csirAddTime,Short csirStatus){
		this.csirName=csirName;
		this.csirFlag=csirFlag;
		this.csirDepict=csirDepict;
		this.csirIntegral=csirIntegral;
		this.csirUpdateTime=csirUpdateTime;
		this.csirAddTime=csirAddTime;
		this.csirStatus=csirStatus;
	}
	
	//设置非空字段
	public CsIntegralRule setNotNull(String csirName,String csirFlag,Double csirIntegral,Date csirUpdateTime,Date csirAddTime,Short csirStatus){
		this.csirName=csirName;
		this.csirFlag=csirFlag;
		this.csirIntegral=csirIntegral;
		this.csirUpdateTime=csirUpdateTime;
		this.csirAddTime=csirAddTime;
		this.csirStatus=csirStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsIntegralRule csirId(Long csirId){
		this.csirId = csirId;
		this.setSeted(F.csirId);
		return this;
	}
	/** 规则名称 [非空]       **/
	public CsIntegralRule csirName(String csirName){
		this.csirName = csirName;
		this.setSeted(F.csirName);
		return this;
	}
	/** 标识 [非空]       **/
	public CsIntegralRule csirFlag(String csirFlag){
		this.csirFlag = csirFlag;
		this.setSeted(F.csirFlag);
		return this;
	}
	/** 规则描述        **/
	public CsIntegralRule csirDepict(String csirDepict){
		this.csirDepict = csirDepict;
		this.setSeted(F.csirDepict);
		return this;
	}
	/** 变动分数 [非空]       **/
	public CsIntegralRule csirIntegral(Double csirIntegral){
		this.csirIntegral = csirIntegral;
		this.setSeted(F.csirIntegral);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsIntegralRule csirUpdateTime(Date csirUpdateTime){
		this.csirUpdateTime = csirUpdateTime;
		this.setSeted(F.csirUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsIntegralRule csirAddTime(Date csirAddTime){
		this.csirAddTime = csirAddTime;
		this.setSeted(F.csirAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsIntegralRule csirStatus(Short csirStatus){
		this.csirStatus = csirStatus;
		this.setSeted(F.csirStatus);
		return this;
	}
	
	
	//克隆对象
	public CsIntegralRule clone(){
		CsIntegralRule clone = new CsIntegralRule();
		clone.csirName=this.csirName;
		clone.csirFlag=this.csirFlag;
		clone.csirIntegral=this.csirIntegral;
		clone.csirUpdateTime=this.csirUpdateTime;
		clone.csirAddTime=this.csirAddTime;
		clone.csirStatus=this.csirStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取积分规则
	 * @param id
	 * @return
	 */
	public static @api CsIntegralRule get(Long id){		
		return getCsIntegralRuleById(id);
	}
	/**
	 * 获取所有积分规则
	 * @return
	 */
	public static @api List<CsIntegralRule> list(Map params,Integer size){
		return getCsIntegralRuleList(params,size);
	}
	/**
	 * 获取积分规则分页
	 * @return
	 */
	public static @api Page<CsIntegralRule> page(int page,int size,Map params){
		return getCsIntegralRulePage(page, size , params);
	}
	/**
	 * 根据查询条件取积分规则
	 * @param params
	 * @return
	 */
	public static @api CsIntegralRule Get(Map params){
		return getCsIntegralRule(params);
	}
	/**
	 * 获取积分规则数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsIntegralRuleCount(params);
	}
	/**
	 * 获取积分规则数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsIntegralRuleEval(eval,params);
	}
	
	/**
	 * 根据ID取积分规则
	 * @param id
	 * @return
	 */
	public static @api CsIntegralRule getCsIntegralRuleById(Long id){		
		CsIntegralRule csIntegralRule = (CsIntegralRule) $.GetRequest("CsIntegralRule$"+id);
		if(csIntegralRule!=null)
			return csIntegralRule;
		com.ccclubs.service.admin.ICsIntegralRuleService csIntegralRuleService = $.GetSpringBean("csIntegralRuleService");		
		return csIntegralRuleService.getCsIntegralRuleById(id);
	}
	
	
	/**
	 * 根据ID取积分规则的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsIntegralRule.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsIntegralRule csIntegralRule = get(id);
		if(csIntegralRule!=null){
			String strValue = csIntegralRule.getCsirName$();
			if(!"CsirName".equals("CsirName"))
				strValue+="("+csIntegralRule.getCsirName$()+")";
			MemCache.setValue(CsIntegralRule.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsirName$();
		if(!"CsirName".equals("CsirName"))
			keyValue+="("+this.getCsirName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有积分规则
	 * @return
	 */
	public static @api List<CsIntegralRule> getCsIntegralRuleList(Map params,Integer size){
		com.ccclubs.service.admin.ICsIntegralRuleService csIntegralRuleService = $.GetSpringBean("csIntegralRuleService");
		return csIntegralRuleService.getCsIntegralRuleList(params, size);
	}
	
	/**
	 * 获取积分规则分页
	 * @return
	 */
	public static @api Page<CsIntegralRule> getCsIntegralRulePage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsIntegralRuleService csIntegralRuleService = $.GetSpringBean("csIntegralRuleService");
		return csIntegralRuleService.getCsIntegralRulePage(page, size , params);
	}
	
	/**
	 * 根据查询条件取积分规则
	 * @param params
	 * @return
	 */
	public static @api CsIntegralRule getCsIntegralRule(Map params){
		com.ccclubs.service.admin.ICsIntegralRuleService csIntegralRuleService = $.GetSpringBean("csIntegralRuleService");
		return csIntegralRuleService.getCsIntegralRule(params);
	}
	
	/**
	 * 获取积分规则数
	 * @return
	 */
	public static @api Long getCsIntegralRuleCount(Map params){
		com.ccclubs.service.admin.ICsIntegralRuleService csIntegralRuleService = $.GetSpringBean("csIntegralRuleService");
		return csIntegralRuleService.getCsIntegralRuleCount(params);
	}
		
		
	/**
	 * 获取积分规则自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsIntegralRuleEval(String eval,Map params){
		com.ccclubs.service.admin.ICsIntegralRuleService csIntegralRuleService = $.GetSpringBean("csIntegralRuleService");
		return csIntegralRuleService.getCsIntegralRuleEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsIntegralRule(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsIntegralRuleService csIntegralRuleService = $.GetSpringBean("csIntegralRuleService");
		csIntegralRuleService.updateCsIntegralRuleByConfirm(set, where);
	}
	
	
	/**
	 * 保存积分规则对象
	 * @param params
	 * @return
	 */
	public CsIntegralRule save(){
		com.ccclubs.service.admin.ICsIntegralRuleService csIntegralRuleService = $.GetSpringBean("csIntegralRuleService");
		if(this.getCsirId()!=null)
			csIntegralRuleService.updateCsIntegralRule(this);
		else
			return csIntegralRuleService.saveCsIntegralRule(this);
		return this;
	}
	
	
	/**
	 * 更新积分规则对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsIntegralRuleService csIntegralRuleService = $.GetSpringBean("csIntegralRuleService");
		csIntegralRuleService.updateCsIntegralRule$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsIntegralRuleService csIntegralRuleService = $.GetSpringBean("csIntegralRuleService");
		if($.equals($.config("logic_delete"),"true"))
			csIntegralRuleService.removeCsIntegralRuleById(this.getCsirId());
		else
			csIntegralRuleService.deleteCsIntegralRuleById(this.getCsirId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsIntegralRuleService csIntegralRuleService = $.GetSpringBean("csIntegralRuleService");
		return csIntegralRuleService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsirId(){
		return this.csirId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsirId$(){
		String strValue="";
		 strValue=$.str(this.getCsirId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsirId(Long csirId){
		this.csirId = csirId;
		this.setSeted(F.csirId);
	}
	/*******************************规则名称**********************************/	
	/**
	* 规则名称 [非空]      
	**/
	public String getCsirName(){
		return this.csirName;
	}
	/**
	* 获取规则名称格式化(toString)
	**/
	public String getCsirName$(){
		String strValue="";
		 strValue=$.str(this.getCsirName());
	 	 return strValue;
	}
	/**
	* 规则名称 [非空]      
	**/
	public void setCsirName(String csirName){
		this.csirName = csirName;
		this.setSeted(F.csirName);
	}
	/*******************************标识**********************************/	
	/**
	* 标识 [非空]      
	**/
	public String getCsirFlag(){
		return this.csirFlag;
	}
	/**
	* 获取标识格式化(toString)
	**/
	public String getCsirFlag$(){
		String strValue="";
		 strValue=$.str(this.getCsirFlag());
	 	 return strValue;
	}
	/**
	* 标识 [非空]      
	**/
	public void setCsirFlag(String csirFlag){
		this.csirFlag = csirFlag;
		this.setSeted(F.csirFlag);
	}
	/*******************************规则描述**********************************/	
	/**
	* 规则描述       
	**/
	public String getCsirDepict(){
		return this.csirDepict;
	}
	/**
	* 获取规则描述格式化(toString)
	**/
	public String getCsirDepict$(){
		String strValue="";
		 strValue=$.str(this.getCsirDepict());
	 	 return strValue;
	}
	/**
	* 规则描述       
	**/
	public void setCsirDepict(String csirDepict){
		this.csirDepict = csirDepict;
		this.setSeted(F.csirDepict);
	}
	/*******************************变动分数**********************************/	
	/**
	* 变动分数 [非空]      
	**/
	public Double getCsirIntegral(){
		return this.csirIntegral;
	}
	/**
	* 获取变动分数格式化(toString)
	**/
	public String getCsirIntegral$(){
		String strValue="";
		 strValue=$.str(this.getCsirIntegral());	
	 	 return strValue;
	}
	/**
	* 变动分数 [非空]      
	**/
	public void setCsirIntegral(Double csirIntegral){
		this.csirIntegral = csirIntegral;
		this.setSeted(F.csirIntegral);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsirUpdateTime(){
		return this.csirUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsirUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsirUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsirUpdateTime(Date csirUpdateTime){
		this.csirUpdateTime = csirUpdateTime;
		this.setSeted(F.csirUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsirAddTime(){
		return this.csirAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsirAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsirAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsirAddTime(Date csirAddTime){
		this.csirAddTime = csirAddTime;
		this.setSeted(F.csirAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsirStatus(){
		return this.csirStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsirStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsirStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsirStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsirStatus(Short csirStatus){
		this.csirStatus = csirStatus;
		this.setSeted(F.csirStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsIntegralRule.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsIntegralRule.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsIntegralRule.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsIntegralRule.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsIntegralRule.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsIntegralRule.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsIntegralRule.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsIntegralRule.this);
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
		public M csirId(Object csirId){this.put("csirId", csirId);return this;};
	 	/** and csir_id is null */
 		public M csirIdNull(){if(this.get("csirIdNot")==null)this.put("csirIdNot", "");this.put("csirId", null);return this;};
 		/** not .... */
 		public M csirIdNot(){this.put("csirIdNot", "not");return this;};
		/** 规则名称 [非空]       **/
		public M csirName(Object csirName){this.put("csirName", csirName);return this;};
	 	/** and csir_name is null */
 		public M csirNameNull(){if(this.get("csirNameNot")==null)this.put("csirNameNot", "");this.put("csirName", null);return this;};
 		/** not .... */
 		public M csirNameNot(){this.put("csirNameNot", "not");return this;};
		/** 标识 [非空]       **/
		public M csirFlag(Object csirFlag){this.put("csirFlag", csirFlag);return this;};
	 	/** and csir_flag is null */
 		public M csirFlagNull(){if(this.get("csirFlagNot")==null)this.put("csirFlagNot", "");this.put("csirFlag", null);return this;};
 		/** not .... */
 		public M csirFlagNot(){this.put("csirFlagNot", "not");return this;};
		/** 规则描述        **/
		public M csirDepict(Object csirDepict){this.put("csirDepict", csirDepict);return this;};
	 	/** and csir_depict is null */
 		public M csirDepictNull(){if(this.get("csirDepictNot")==null)this.put("csirDepictNot", "");this.put("csirDepict", null);return this;};
 		/** not .... */
 		public M csirDepictNot(){this.put("csirDepictNot", "not");return this;};
		/** 变动分数 [非空]       **/
		public M csirIntegral(Object csirIntegral){this.put("csirIntegral", csirIntegral);return this;};
	 	/** and csir_integral is null */
 		public M csirIntegralNull(){if(this.get("csirIntegralNot")==null)this.put("csirIntegralNot", "");this.put("csirIntegral", null);return this;};
 		/** not .... */
 		public M csirIntegralNot(){this.put("csirIntegralNot", "not");return this;};
		/** and csir_integral >= ? */
		public M csirIntegralMin(Object min){this.put("csirIntegralMin", min);return this;};
		/** and csir_integral <= ? */
		public M csirIntegralMax(Object max){this.put("csirIntegralMax", max);return this;};
		/** 修改时间 [非空]       **/
		public M csirUpdateTime(Object csirUpdateTime){this.put("csirUpdateTime", csirUpdateTime);return this;};
	 	/** and csir_update_time is null */
 		public M csirUpdateTimeNull(){if(this.get("csirUpdateTimeNot")==null)this.put("csirUpdateTimeNot", "");this.put("csirUpdateTime", null);return this;};
 		/** not .... */
 		public M csirUpdateTimeNot(){this.put("csirUpdateTimeNot", "not");return this;};
 		/** and csir_update_time >= ? */
 		public M csirUpdateTimeStart(Object start){this.put("csirUpdateTimeStart", start);return this;};			
 		/** and csir_update_time <= ? */
 		public M csirUpdateTimeEnd(Object end){this.put("csirUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csirAddTime(Object csirAddTime){this.put("csirAddTime", csirAddTime);return this;};
	 	/** and csir_add_time is null */
 		public M csirAddTimeNull(){if(this.get("csirAddTimeNot")==null)this.put("csirAddTimeNot", "");this.put("csirAddTime", null);return this;};
 		/** not .... */
 		public M csirAddTimeNot(){this.put("csirAddTimeNot", "not");return this;};
 		/** and csir_add_time >= ? */
 		public M csirAddTimeStart(Object start){this.put("csirAddTimeStart", start);return this;};			
 		/** and csir_add_time <= ? */
 		public M csirAddTimeEnd(Object end){this.put("csirAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csirStatus(Object csirStatus){this.put("csirStatus", csirStatus);return this;};
	 	/** and csir_status is null */
 		public M csirStatusNull(){if(this.get("csirStatusNot")==null)this.put("csirStatusNot", "");this.put("csirStatus", null);return this;};
 		/** not .... */
 		public M csirStatusNot(){this.put("csirStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有积分规则 **/
		public @api List<CsIntegralRule> list(Integer size){
			return getCsIntegralRuleList(this,size);
		}
		/** 获取积分规则分页 **/
		public @api Page<CsIntegralRule> page(int page,int size){
			return getCsIntegralRulePage(page, size , this);
		}
		/** 根据查询条件取积分规则 **/
		public @api CsIntegralRule get(){
			return getCsIntegralRule(this);
		}
		/** 获取积分规则数 **/
		public @api Long count(){
			return getCsIntegralRuleCount(this);
		}
		/** 获取积分规则表达式 **/
		public @api <T> T eval(String strEval){
			return getCsIntegralRuleEval(strEval,this);
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
			updateCsIntegralRule(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csirId="csirId";
		/** 规则名称 [非空]       **/
		public final static @type(String.class) @like String csirName="csirName";
		/** 标识 [非空]       **/
		public final static @type(String.class) @like String csirFlag="csirFlag";
		/** 规则描述        **/
		public final static @type(String.class) @like String csirDepict="csirDepict";
		/** 变动分数 [非空]       **/
		public final static @type(Double.class)  String csirIntegral="csirIntegral";
		/** and csir_integral >= ? */
		public final static @type(Double.class) String csirIntegralMin="csirIntegralMin";
		/** and csir_integral <= ? */
		public final static @type(Double.class) String csirIntegralMax="csirIntegralMax";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csirUpdateTime="csirUpdateTime";
	 	/** and csir_update_time >= ? */
 		public final static @type(Date.class) String csirUpdateTimeStart="csirUpdateTimeStart";
 		/** and csir_update_time <= ? */
 		public final static @type(Date.class) String csirUpdateTimeEnd="csirUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csirAddTime="csirAddTime";
	 	/** and csir_add_time >= ? */
 		public final static @type(Date.class) String csirAddTimeStart="csirAddTimeStart";
 		/** and csir_add_time <= ? */
 		public final static @type(Date.class) String csirAddTimeEnd="csirAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csirStatus="csirStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csirId="csir_id";
		/** 规则名称 [非空]       **/
		public final static String csirName="csir_name";
		/** 标识 [非空]       **/
		public final static String csirFlag="csir_flag";
		/** 规则描述        **/
		public final static String csirDepict="csir_depict";
		/** 变动分数 [非空]       **/
		public final static String csirIntegral="csir_integral";
		/** 修改时间 [非空]       **/
		public final static String csirUpdateTime="csir_update_time";
		/** 添加时间 [非空]       **/
		public final static String csirAddTime="csir_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csirStatus="csir_status";
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
				$.Set(name,CsIntegralRule.getCsIntegralRule(params));
			else
				$.Set(name,CsIntegralRule.getCsIntegralRuleList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取积分规则数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsIntegralRule) $.GetRequest("CsIntegralRule$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsIntegralRule.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsIntegralRule.getCsIntegralRule(params);
			else
				value = CsIntegralRule.getCsIntegralRuleList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsIntegralRule.Get($.add(CsIntegralRule.F.csirId,param));
		else if(!$.empty(param.toString()))
			value = CsIntegralRule.get(Long.valueOf(param.toString()));
		$.SetRequest("CsIntegralRule$"+param.hashCode(), value);
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
	public void mergeSet(CsIntegralRule old){
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