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

@namespace("user/membergroup")
public @caption("会员组织") @table("cs_member_group") class CsMemberGroup implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csmg_id")   @primary  @note("  ") Long csmgId;// 主键 非空     
	private @caption("城市") @column("csmg_host")    @relate("$csmgHost") @RelateClass(SrvHost.class)  @note("  ") Long csmgHost;// 非空     
 	private SrvHost $csmgHost;//关联对象[运营城市]
	private @caption("组织名称") @column("csmg_name")    @note("  ") String csmgName;// 非空     
	private @caption("负责人") @column("csmg_person")    @note("  ") String csmgPerson;//     
	private @caption("联系电话") @column("csmg_number")    @note("  ") String csmgNumber;//     
	private @caption("组织标识") @column("csmg_flag")    @note("  ") String csmgFlag;//     
	private @caption("组织折扣") @column("csmg_rebate")    @note("  0到1之间的小数，其它无效") Double csmgRebate;//  0到1之间的小数，其它无效   
	private @caption("备注信息") @column("csmg_remark")    @note("  ") String csmgRemark;//     
	private @caption("添加时间") @column("csmg_add_time")    @note("  ") Date csmgAddTime;// 非空     
	private @caption("状态") @column("csmg_status")    @note(" 1:正常  ") Short csmgStatus;// 非空 1:正常     
	
	//默认构造函数
	public CsMemberGroup(){
	
	}
	
	//主键构造函数
	public CsMemberGroup(Long id){
		this.csmgId = id;
	}
	
	/**所有字段构造函数 CsMemberGroup(csmgHost,csmgName,csmgPerson,csmgNumber,csmgFlag,csmgRebate,csmgRemark,csmgAddTime,csmgStatus)
	 CsMemberGroup(
	 	$.getLong("csmgHost")//城市 [非空]
	 	,$.getString("csmgName")//组织名称 [非空]
	 	,$.getString("csmgPerson")//负责人
	 	,$.getString("csmgNumber")//联系电话
	 	,$.getString("csmgFlag")//组织标识
	 	,$.getDouble("csmgRebate")//组织折扣
	 	,$.getString("csmgRemark")//备注信息
	 	,$.getDate("csmgAddTime")//添加时间 [非空]
	 	,$.getShort("csmgStatus")//状态 [非空]
	 )
	**/
	public CsMemberGroup(Long csmgHost,String csmgName,String csmgPerson,String csmgNumber,String csmgFlag,Double csmgRebate,String csmgRemark,Date csmgAddTime,Short csmgStatus){
		this.csmgHost=csmgHost;
		this.csmgName=csmgName;
		this.csmgPerson=csmgPerson;
		this.csmgNumber=csmgNumber;
		this.csmgFlag=csmgFlag;
		this.csmgRebate=csmgRebate;
		this.csmgRemark=csmgRemark;
		this.csmgAddTime=csmgAddTime;
		this.csmgStatus=csmgStatus;
	}
	
	//设置非空字段
	public CsMemberGroup setNotNull(Long csmgHost,String csmgName,Date csmgAddTime,Short csmgStatus){
		this.csmgHost=csmgHost;
		this.csmgName=csmgName;
		this.csmgAddTime=csmgAddTime;
		this.csmgStatus=csmgStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsMemberGroup csmgId(Long csmgId){
		this.csmgId = csmgId;
		this.setSeted(F.csmgId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsMemberGroup csmgHost(Long csmgHost){
		this.csmgHost = csmgHost;
		this.setSeted(F.csmgHost);
		return this;
	}
	/** 组织名称 [非空]       **/
	public CsMemberGroup csmgName(String csmgName){
		this.csmgName = csmgName;
		this.setSeted(F.csmgName);
		return this;
	}
	/** 负责人        **/
	public CsMemberGroup csmgPerson(String csmgPerson){
		this.csmgPerson = csmgPerson;
		this.setSeted(F.csmgPerson);
		return this;
	}
	/** 联系电话        **/
	public CsMemberGroup csmgNumber(String csmgNumber){
		this.csmgNumber = csmgNumber;
		this.setSeted(F.csmgNumber);
		return this;
	}
	/** 组织标识        **/
	public CsMemberGroup csmgFlag(String csmgFlag){
		this.csmgFlag = csmgFlag;
		this.setSeted(F.csmgFlag);
		return this;
	}
	/** 组织折扣     0到1之间的小数，其它无效   **/
	public CsMemberGroup csmgRebate(Double csmgRebate){
		this.csmgRebate = csmgRebate;
		this.setSeted(F.csmgRebate);
		return this;
	}
	/** 备注信息        **/
	public CsMemberGroup csmgRemark(String csmgRemark){
		this.csmgRemark = csmgRemark;
		this.setSeted(F.csmgRemark);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsMemberGroup csmgAddTime(Date csmgAddTime){
		this.csmgAddTime = csmgAddTime;
		this.setSeted(F.csmgAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常     **/
	public CsMemberGroup csmgStatus(Short csmgStatus){
		this.csmgStatus = csmgStatus;
		this.setSeted(F.csmgStatus);
		return this;
	}
	
	
	//克隆对象
	public CsMemberGroup clone(){
		CsMemberGroup clone = new CsMemberGroup();
		clone.csmgHost=this.csmgHost;
		clone.csmgName=this.csmgName;
		clone.csmgAddTime=this.csmgAddTime;
		clone.csmgStatus=this.csmgStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取会员组织
	 * @param id
	 * @return
	 */
	public static @api CsMemberGroup get(Long id){		
		return getCsMemberGroupById(id);
	}
	/**
	 * 获取所有会员组织
	 * @return
	 */
	public static @api List<CsMemberGroup> list(Map params,Integer size){
		return getCsMemberGroupList(params,size);
	}
	/**
	 * 获取会员组织分页
	 * @return
	 */
	public static @api Page<CsMemberGroup> page(int page,int size,Map params){
		return getCsMemberGroupPage(page, size , params);
	}
	/**
	 * 根据查询条件取会员组织
	 * @param params
	 * @return
	 */
	public static @api CsMemberGroup Get(Map params){
		return getCsMemberGroup(params);
	}
	/**
	 * 获取会员组织数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsMemberGroupCount(params);
	}
	/**
	 * 获取会员组织数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsMemberGroupEval(eval,params);
	}
	
	/**
	 * 根据ID取会员组织
	 * @param id
	 * @return
	 */
	public static @api CsMemberGroup getCsMemberGroupById(Long id){		
		CsMemberGroup csMemberGroup = (CsMemberGroup) $.GetRequest("CsMemberGroup$"+id);
		if(csMemberGroup!=null)
			return csMemberGroup;
		com.ccclubs.service.admin.ICsMemberGroupService csMemberGroupService = $.GetSpringBean("csMemberGroupService");		
		return csMemberGroupService.getCsMemberGroupById(id);
	}
	
	
	/**
	 * 根据ID取会员组织的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsMemberGroup.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsMemberGroup csMemberGroup = get(id);
		if(csMemberGroup!=null){
			String strValue = csMemberGroup.getCsmgName$();
			if(!"CsmgName".equals("CsmgName"))
				strValue+="("+csMemberGroup.getCsmgName$()+")";
			MemCache.setValue(CsMemberGroup.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsmgName$();
		if(!"CsmgName".equals("CsmgName"))
			keyValue+="("+this.getCsmgName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有会员组织
	 * @return
	 */
	public static @api List<CsMemberGroup> getCsMemberGroupList(Map params,Integer size){
		com.ccclubs.service.admin.ICsMemberGroupService csMemberGroupService = $.GetSpringBean("csMemberGroupService");
		return csMemberGroupService.getCsMemberGroupList(params, size);
	}
	
	/**
	 * 获取会员组织分页
	 * @return
	 */
	public static @api Page<CsMemberGroup> getCsMemberGroupPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsMemberGroupService csMemberGroupService = $.GetSpringBean("csMemberGroupService");
		return csMemberGroupService.getCsMemberGroupPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取会员组织
	 * @param params
	 * @return
	 */
	public static @api CsMemberGroup getCsMemberGroup(Map params){
		com.ccclubs.service.admin.ICsMemberGroupService csMemberGroupService = $.GetSpringBean("csMemberGroupService");
		return csMemberGroupService.getCsMemberGroup(params);
	}
	
	/**
	 * 获取会员组织数
	 * @return
	 */
	public static @api Long getCsMemberGroupCount(Map params){
		com.ccclubs.service.admin.ICsMemberGroupService csMemberGroupService = $.GetSpringBean("csMemberGroupService");
		return csMemberGroupService.getCsMemberGroupCount(params);
	}
		
		
	/**
	 * 获取会员组织自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsMemberGroupEval(String eval,Map params){
		com.ccclubs.service.admin.ICsMemberGroupService csMemberGroupService = $.GetSpringBean("csMemberGroupService");
		return csMemberGroupService.getCsMemberGroupEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsMemberGroup(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsMemberGroupService csMemberGroupService = $.GetSpringBean("csMemberGroupService");
		csMemberGroupService.updateCsMemberGroupByConfirm(set, where);
	}
	
	
	/**
	 * 保存会员组织对象
	 * @param params
	 * @return
	 */
	public CsMemberGroup save(){
		com.ccclubs.service.admin.ICsMemberGroupService csMemberGroupService = $.GetSpringBean("csMemberGroupService");
		if(this.getCsmgId()!=null)
			csMemberGroupService.updateCsMemberGroup(this);
		else
			return csMemberGroupService.saveCsMemberGroup(this);
		return this;
	}
	
	
	/**
	 * 更新会员组织对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsMemberGroupService csMemberGroupService = $.GetSpringBean("csMemberGroupService");
		csMemberGroupService.updateCsMemberGroup$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsMemberGroupService csMemberGroupService = $.GetSpringBean("csMemberGroupService");
		if($.equals($.config("logic_delete"),"true"))
			csMemberGroupService.removeCsMemberGroupById(this.getCsmgId());
		else
			csMemberGroupService.deleteCsMemberGroupById(this.getCsmgId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsMemberGroupService csMemberGroupService = $.GetSpringBean("csMemberGroupService");
		return csMemberGroupService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsmgId(){
		return this.csmgId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsmgId$(){
		String strValue="";
		 strValue=$.str(this.getCsmgId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsmgId(Long csmgId){
		this.csmgId = csmgId;
		this.setSeted(F.csmgId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsmgHost(){
		return this.csmgHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsmgHost$(){
		String strValue="";
		if(this.getCsmgHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsmgHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsmgHost(Long csmgHost){
		this.csmgHost = csmgHost;
		this.setSeted(F.csmgHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csmgHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsmgHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsmgHost()!=null){
 			srvHost = SrvHost.get(this.getCsmgHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsmgHost(), srvHost);
	 	return srvHost;
	}
	/*******************************组织名称**********************************/	
	/**
	* 组织名称 [非空]      
	**/
	public String getCsmgName(){
		return this.csmgName;
	}
	/**
	* 获取组织名称格式化(toString)
	**/
	public String getCsmgName$(){
		String strValue="";
		 strValue=$.str(this.getCsmgName());
	 	 return strValue;
	}
	/**
	* 组织名称 [非空]      
	**/
	public void setCsmgName(String csmgName){
		this.csmgName = csmgName;
		this.setSeted(F.csmgName);
	}
	/*******************************负责人**********************************/	
	/**
	* 负责人       
	**/
	public String getCsmgPerson(){
		return this.csmgPerson;
	}
	/**
	* 获取负责人格式化(toString)
	**/
	public String getCsmgPerson$(){
		String strValue="";
		 strValue=$.str(this.getCsmgPerson());
	 	 return strValue;
	}
	/**
	* 负责人       
	**/
	public void setCsmgPerson(String csmgPerson){
		this.csmgPerson = csmgPerson;
		this.setSeted(F.csmgPerson);
	}
	/*******************************联系电话**********************************/	
	/**
	* 联系电话       
	**/
	public String getCsmgNumber(){
		return this.csmgNumber;
	}
	/**
	* 获取联系电话格式化(toString)
	**/
	public String getCsmgNumber$(){
		String strValue="";
		 strValue=$.str(this.getCsmgNumber());
	 	 return strValue;
	}
	/**
	* 联系电话       
	**/
	public void setCsmgNumber(String csmgNumber){
		this.csmgNumber = csmgNumber;
		this.setSeted(F.csmgNumber);
	}
	/*******************************组织标识**********************************/	
	/**
	* 组织标识       
	**/
	public String getCsmgFlag(){
		return this.csmgFlag;
	}
	/**
	* 获取组织标识格式化(toString)
	**/
	public String getCsmgFlag$(){
		String strValue="";
		 strValue=$.str(this.getCsmgFlag());
	 	 return strValue;
	}
	/**
	* 组织标识       
	**/
	public void setCsmgFlag(String csmgFlag){
		this.csmgFlag = csmgFlag;
		this.setSeted(F.csmgFlag);
	}
	/*******************************组织折扣**********************************/	
	/**
	* 组织折扣     0到1之间的小数，其它无效  
	**/
	public Double getCsmgRebate(){
		return this.csmgRebate;
	}
	/**
	* 获取组织折扣格式化(toString)
	**/
	public String getCsmgRebate$(){
		String strValue="";
		 strValue=$.str(this.getCsmgRebate());	
	 	 return strValue;
	}
	/**
	* 组织折扣     0到1之间的小数，其它无效  
	**/
	public void setCsmgRebate(Double csmgRebate){
		this.csmgRebate = csmgRebate;
		this.setSeted(F.csmgRebate);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息       
	**/
	public String getCsmgRemark(){
		return this.csmgRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCsmgRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsmgRemark());
	 	 return strValue;
	}
	/**
	* 备注信息       
	**/
	public void setCsmgRemark(String csmgRemark){
		this.csmgRemark = csmgRemark;
		this.setSeted(F.csmgRemark);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsmgAddTime(){
		return this.csmgAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsmgAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmgAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsmgAddTime(Date csmgAddTime){
		this.csmgAddTime = csmgAddTime;
		this.setSeted(F.csmgAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常    
	**/
	public Short getCsmgStatus(){
		return this.csmgStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsmgStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsmgStatus()),"1"))
			strValue=$.str("正常");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常    
	**/
	public void setCsmgStatus(Short csmgStatus){
		this.csmgStatus = csmgStatus;
		this.setSeted(F.csmgStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsMemberGroup.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMemberGroup.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMemberGroup.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsMemberGroup.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsMemberGroup.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMemberGroup.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMemberGroup.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsMemberGroup.this);
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
		public M csmgId(Object csmgId){this.put("csmgId", csmgId);return this;};
	 	/** and csmg_id is null */
 		public M csmgIdNull(){if(this.get("csmgIdNot")==null)this.put("csmgIdNot", "");this.put("csmgId", null);return this;};
 		/** not .... */
 		public M csmgIdNot(){this.put("csmgIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csmgHost(Object csmgHost){this.put("csmgHost", csmgHost);return this;};
	 	/** and csmg_host is null */
 		public M csmgHostNull(){if(this.get("csmgHostNot")==null)this.put("csmgHostNot", "");this.put("csmgHost", null);return this;};
 		/** not .... */
 		public M csmgHostNot(){this.put("csmgHostNot", "not");return this;};
		/** 组织名称 [非空]       **/
		public M csmgName(Object csmgName){this.put("csmgName", csmgName);return this;};
	 	/** and csmg_name is null */
 		public M csmgNameNull(){if(this.get("csmgNameNot")==null)this.put("csmgNameNot", "");this.put("csmgName", null);return this;};
 		/** not .... */
 		public M csmgNameNot(){this.put("csmgNameNot", "not");return this;};
		/** 负责人        **/
		public M csmgPerson(Object csmgPerson){this.put("csmgPerson", csmgPerson);return this;};
	 	/** and csmg_person is null */
 		public M csmgPersonNull(){if(this.get("csmgPersonNot")==null)this.put("csmgPersonNot", "");this.put("csmgPerson", null);return this;};
 		/** not .... */
 		public M csmgPersonNot(){this.put("csmgPersonNot", "not");return this;};
		/** 联系电话        **/
		public M csmgNumber(Object csmgNumber){this.put("csmgNumber", csmgNumber);return this;};
	 	/** and csmg_number is null */
 		public M csmgNumberNull(){if(this.get("csmgNumberNot")==null)this.put("csmgNumberNot", "");this.put("csmgNumber", null);return this;};
 		/** not .... */
 		public M csmgNumberNot(){this.put("csmgNumberNot", "not");return this;};
		/** 组织标识        **/
		public M csmgFlag(Object csmgFlag){this.put("csmgFlag", csmgFlag);return this;};
	 	/** and csmg_flag is null */
 		public M csmgFlagNull(){if(this.get("csmgFlagNot")==null)this.put("csmgFlagNot", "");this.put("csmgFlag", null);return this;};
 		/** not .... */
 		public M csmgFlagNot(){this.put("csmgFlagNot", "not");return this;};
		/** 组织折扣     0到1之间的小数，其它无效   **/
		public M csmgRebate(Object csmgRebate){this.put("csmgRebate", csmgRebate);return this;};
	 	/** and csmg_rebate is null */
 		public M csmgRebateNull(){if(this.get("csmgRebateNot")==null)this.put("csmgRebateNot", "");this.put("csmgRebate", null);return this;};
 		/** not .... */
 		public M csmgRebateNot(){this.put("csmgRebateNot", "not");return this;};
		/** and csmg_rebate >= ? */
		public M csmgRebateMin(Object min){this.put("csmgRebateMin", min);return this;};
		/** and csmg_rebate <= ? */
		public M csmgRebateMax(Object max){this.put("csmgRebateMax", max);return this;};
		/** 备注信息        **/
		public M csmgRemark(Object csmgRemark){this.put("csmgRemark", csmgRemark);return this;};
	 	/** and csmg_remark is null */
 		public M csmgRemarkNull(){if(this.get("csmgRemarkNot")==null)this.put("csmgRemarkNot", "");this.put("csmgRemark", null);return this;};
 		/** not .... */
 		public M csmgRemarkNot(){this.put("csmgRemarkNot", "not");return this;};
		/** 添加时间 [非空]       **/
		public M csmgAddTime(Object csmgAddTime){this.put("csmgAddTime", csmgAddTime);return this;};
	 	/** and csmg_add_time is null */
 		public M csmgAddTimeNull(){if(this.get("csmgAddTimeNot")==null)this.put("csmgAddTimeNot", "");this.put("csmgAddTime", null);return this;};
 		/** not .... */
 		public M csmgAddTimeNot(){this.put("csmgAddTimeNot", "not");return this;};
 		/** and csmg_add_time >= ? */
 		public M csmgAddTimeStart(Object start){this.put("csmgAddTimeStart", start);return this;};			
 		/** and csmg_add_time <= ? */
 		public M csmgAddTimeEnd(Object end){this.put("csmgAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常     **/
		public M csmgStatus(Object csmgStatus){this.put("csmgStatus", csmgStatus);return this;};
	 	/** and csmg_status is null */
 		public M csmgStatusNull(){if(this.get("csmgStatusNot")==null)this.put("csmgStatusNot", "");this.put("csmgStatus", null);return this;};
 		/** not .... */
 		public M csmgStatusNot(){this.put("csmgStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有会员组织 **/
		public @api List<CsMemberGroup> list(Integer size){
			return getCsMemberGroupList(this,size);
		}
		/** 获取会员组织分页 **/
		public @api Page<CsMemberGroup> page(int page,int size){
			return getCsMemberGroupPage(page, size , this);
		}
		/** 根据查询条件取会员组织 **/
		public @api CsMemberGroup get(){
			return getCsMemberGroup(this);
		}
		/** 获取会员组织数 **/
		public @api Long count(){
			return getCsMemberGroupCount(this);
		}
		/** 获取会员组织表达式 **/
		public @api <T> T eval(String strEval){
			return getCsMemberGroupEval(strEval,this);
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
			updateCsMemberGroup(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csmgId="csmgId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csmgHost="csmgHost";
		/** 组织名称 [非空]       **/
		public final static @type(String.class) @like String csmgName="csmgName";
		/** 负责人        **/
		public final static @type(String.class) @like String csmgPerson="csmgPerson";
		/** 联系电话        **/
		public final static @type(String.class) @like String csmgNumber="csmgNumber";
		/** 组织标识        **/
		public final static @type(String.class) @like String csmgFlag="csmgFlag";
		/** 组织折扣     0到1之间的小数，其它无效   **/
		public final static @type(Double.class)  String csmgRebate="csmgRebate";
		/** and csmg_rebate >= ? */
		public final static @type(Double.class) String csmgRebateMin="csmgRebateMin";
		/** and csmg_rebate <= ? */
		public final static @type(Double.class) String csmgRebateMax="csmgRebateMax";
		/** 备注信息        **/
		public final static @type(String.class) @like String csmgRemark="csmgRemark";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csmgAddTime="csmgAddTime";
	 	/** and csmg_add_time >= ? */
 		public final static @type(Date.class) String csmgAddTimeStart="csmgAddTimeStart";
 		/** and csmg_add_time <= ? */
 		public final static @type(Date.class) String csmgAddTimeEnd="csmgAddTimeEnd";
		/** 状态 [非空]   1:正常     **/
		public final static @type(Short.class)  String csmgStatus="csmgStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csmgId="csmg_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csmgHost="csmg_host";
		/** 组织名称 [非空]       **/
		public final static String csmgName="csmg_name";
		/** 负责人        **/
		public final static String csmgPerson="csmg_person";
		/** 联系电话        **/
		public final static String csmgNumber="csmg_number";
		/** 组织标识        **/
		public final static String csmgFlag="csmg_flag";
		/** 组织折扣     0到1之间的小数，其它无效   **/
		public final static String csmgRebate="csmg_rebate";
		/** 备注信息        **/
		public final static String csmgRemark="csmg_remark";
		/** 添加时间 [非空]       **/
		public final static String csmgAddTime="csmg_add_time";
		/** 状态 [非空]   1:正常     **/
		public final static String csmgStatus="csmg_status";
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
				$.Set(name,CsMemberGroup.getCsMemberGroup(params));
			else
				$.Set(name,CsMemberGroup.getCsMemberGroupList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取会员组织数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsMemberGroup) $.GetRequest("CsMemberGroup$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsMemberGroup.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsMemberGroup.getCsMemberGroup(params);
			else
				value = CsMemberGroup.getCsMemberGroupList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsMemberGroup.Get($.add(CsMemberGroup.F.csmgId,param));
		else if(!$.empty(param.toString()))
			value = CsMemberGroup.get(Long.valueOf(param.toString()));
		$.SetRequest("CsMemberGroup$"+param.hashCode(), value);
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
	public void mergeSet(CsMemberGroup old){
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