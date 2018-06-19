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

@namespace("configurator/provid")
public @caption("供应商") @table("cs_provid") class CsProvid implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csp_id")   @primary  @note("  ") Long cspId;// 主键 非空     
	private @caption("城市") @column("csp_host")    @relate("$cspHost") @RelateClass(SrvHost.class)  @note("  ") Long cspHost;// 非空     
 	private SrvHost $cspHost;//关联对象[运营城市]
	private @caption("名称") @column("csp_name")    @note("  ") String cspName;// 非空     
	private @caption("负责人") @column("csp_person")    @note("  ") String cspPerson;//     
	private @caption("联系方式") @column("csp_concat")    @note("  ") String cspConcat;//     
	private @caption("程序标识") @column("csp_flag")    @note("  ") String cspFlag;//     
	private @caption("备注信息") @column("csp_remark")    @note("  ") String cspRemark;//     
	private @caption("修改时间") @column("csp_update_time")    @note("  ") Date cspUpdateTime;// 非空     
	private @caption("添加时间") @column("csp_add_time")    @note("  ") Date cspAddTime;// 非空     
	private @caption("状态") @column("csp_status")    @note(" 1:正常 0:无效  ") Short cspStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsProvid(){
	
	}
	
	//主键构造函数
	public CsProvid(Long id){
		this.cspId = id;
	}
	
	/**所有字段构造函数 CsProvid(cspHost,cspName,cspPerson,cspConcat,cspFlag,cspRemark,cspUpdateTime,cspAddTime,cspStatus)
	 CsProvid(
	 	$.getLong("cspHost")//城市 [非空]
	 	,$.getString("cspName")//名称 [非空]
	 	,$.getString("cspPerson")//负责人
	 	,$.getString("cspConcat")//联系方式
	 	,$.getString("cspFlag")//程序标识
	 	,$.getString("cspRemark")//备注信息
	 	,$.getDate("cspUpdateTime")//修改时间 [非空]
	 	,$.getDate("cspAddTime")//添加时间 [非空]
	 	,$.getShort("cspStatus")//状态 [非空]
	 )
	**/
	public CsProvid(Long cspHost,String cspName,String cspPerson,String cspConcat,String cspFlag,String cspRemark,Date cspUpdateTime,Date cspAddTime,Short cspStatus){
		this.cspHost=cspHost;
		this.cspName=cspName;
		this.cspPerson=cspPerson;
		this.cspConcat=cspConcat;
		this.cspFlag=cspFlag;
		this.cspRemark=cspRemark;
		this.cspUpdateTime=cspUpdateTime;
		this.cspAddTime=cspAddTime;
		this.cspStatus=cspStatus;
	}
	
	//设置非空字段
	public CsProvid setNotNull(Long cspHost,String cspName,Date cspUpdateTime,Date cspAddTime,Short cspStatus){
		this.cspHost=cspHost;
		this.cspName=cspName;
		this.cspUpdateTime=cspUpdateTime;
		this.cspAddTime=cspAddTime;
		this.cspStatus=cspStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsProvid cspId(Long cspId){
		this.cspId = cspId;
		this.setSeted(F.cspId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsProvid cspHost(Long cspHost){
		this.cspHost = cspHost;
		this.setSeted(F.cspHost);
		return this;
	}
	/** 名称 [非空]       **/
	public CsProvid cspName(String cspName){
		this.cspName = cspName;
		this.setSeted(F.cspName);
		return this;
	}
	/** 负责人        **/
	public CsProvid cspPerson(String cspPerson){
		this.cspPerson = cspPerson;
		this.setSeted(F.cspPerson);
		return this;
	}
	/** 联系方式        **/
	public CsProvid cspConcat(String cspConcat){
		this.cspConcat = cspConcat;
		this.setSeted(F.cspConcat);
		return this;
	}
	/** 程序标识        **/
	public CsProvid cspFlag(String cspFlag){
		this.cspFlag = cspFlag;
		this.setSeted(F.cspFlag);
		return this;
	}
	/** 备注信息        **/
	public CsProvid cspRemark(String cspRemark){
		this.cspRemark = cspRemark;
		this.setSeted(F.cspRemark);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsProvid cspUpdateTime(Date cspUpdateTime){
		this.cspUpdateTime = cspUpdateTime;
		this.setSeted(F.cspUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsProvid cspAddTime(Date cspAddTime){
		this.cspAddTime = cspAddTime;
		this.setSeted(F.cspAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsProvid cspStatus(Short cspStatus){
		this.cspStatus = cspStatus;
		this.setSeted(F.cspStatus);
		return this;
	}
	
	
	//克隆对象
	public CsProvid clone(){
		CsProvid clone = new CsProvid();
		clone.cspHost=this.cspHost;
		clone.cspName=this.cspName;
		clone.cspUpdateTime=this.cspUpdateTime;
		clone.cspAddTime=this.cspAddTime;
		clone.cspStatus=this.cspStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取供应商
	 * @param id
	 * @return
	 */
	public static @api CsProvid get(Long id){		
		return getCsProvidById(id);
	}
	/**
	 * 获取所有供应商
	 * @return
	 */
	public static @api List<CsProvid> list(Map params,Integer size){
		return getCsProvidList(params,size);
	}
	/**
	 * 获取供应商分页
	 * @return
	 */
	public static @api Page<CsProvid> page(int page,int size,Map params){
		return getCsProvidPage(page, size , params);
	}
	/**
	 * 根据查询条件取供应商
	 * @param params
	 * @return
	 */
	public static @api CsProvid Get(Map params){
		return getCsProvid(params);
	}
	/**
	 * 获取供应商数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsProvidCount(params);
	}
	/**
	 * 获取供应商数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsProvidEval(eval,params);
	}
	
	/**
	 * 根据ID取供应商
	 * @param id
	 * @return
	 */
	public static @api CsProvid getCsProvidById(Long id){		
		CsProvid csProvid = (CsProvid) $.GetRequest("CsProvid$"+id);
		if(csProvid!=null)
			return csProvid;
		com.ccclubs.service.admin.ICsProvidService csProvidService = $.GetSpringBean("csProvidService");		
		return csProvidService.getCsProvidById(id);
	}
	
	
	/**
	 * 根据ID取供应商的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsProvid.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsProvid csProvid = get(id);
		if(csProvid!=null){
			String strValue = csProvid.getCspName$();
			if(!"CspName".equals("CspName"))
				strValue+="("+csProvid.getCspName$()+")";
			MemCache.setValue(CsProvid.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCspName$();
		if(!"CspName".equals("CspName"))
			keyValue+="("+this.getCspName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有供应商
	 * @return
	 */
	public static @api List<CsProvid> getCsProvidList(Map params,Integer size){
		com.ccclubs.service.admin.ICsProvidService csProvidService = $.GetSpringBean("csProvidService");
		return csProvidService.getCsProvidList(params, size);
	}
	
	/**
	 * 获取供应商分页
	 * @return
	 */
	public static @api Page<CsProvid> getCsProvidPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsProvidService csProvidService = $.GetSpringBean("csProvidService");
		return csProvidService.getCsProvidPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取供应商
	 * @param params
	 * @return
	 */
	public static @api CsProvid getCsProvid(Map params){
		com.ccclubs.service.admin.ICsProvidService csProvidService = $.GetSpringBean("csProvidService");
		return csProvidService.getCsProvid(params);
	}
	
	/**
	 * 获取供应商数
	 * @return
	 */
	public static @api Long getCsProvidCount(Map params){
		com.ccclubs.service.admin.ICsProvidService csProvidService = $.GetSpringBean("csProvidService");
		return csProvidService.getCsProvidCount(params);
	}
		
		
	/**
	 * 获取供应商自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsProvidEval(String eval,Map params){
		com.ccclubs.service.admin.ICsProvidService csProvidService = $.GetSpringBean("csProvidService");
		return csProvidService.getCsProvidEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsProvid(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsProvidService csProvidService = $.GetSpringBean("csProvidService");
		csProvidService.updateCsProvidByConfirm(set, where);
	}
	
	
	/**
	 * 保存供应商对象
	 * @param params
	 * @return
	 */
	public CsProvid save(){
		com.ccclubs.service.admin.ICsProvidService csProvidService = $.GetSpringBean("csProvidService");
		if(this.getCspId()!=null)
			csProvidService.updateCsProvid(this);
		else
			return csProvidService.saveCsProvid(this);
		return this;
	}
	
	
	/**
	 * 更新供应商对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsProvidService csProvidService = $.GetSpringBean("csProvidService");
		csProvidService.updateCsProvid$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsProvidService csProvidService = $.GetSpringBean("csProvidService");
		if($.equals($.config("logic_delete"),"true"))
			csProvidService.removeCsProvidById(this.getCspId());
		else
			csProvidService.deleteCsProvidById(this.getCspId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsProvidService csProvidService = $.GetSpringBean("csProvidService");
		return csProvidService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCspId(){
		return this.cspId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCspId$(){
		String strValue="";
		 strValue=$.str(this.getCspId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCspId(Long cspId){
		this.cspId = cspId;
		this.setSeted(F.cspId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCspHost(){
		return this.cspHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCspHost$(){
		String strValue="";
		if(this.getCspHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCspHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCspHost(Long cspHost){
		this.cspHost = cspHost;
		this.setSeted(F.cspHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cspHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCspHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCspHost()!=null){
 			srvHost = SrvHost.get(this.getCspHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCspHost(), srvHost);
	 	return srvHost;
	}
	/*******************************名称**********************************/	
	/**
	* 名称 [非空]      
	**/
	public String getCspName(){
		return this.cspName;
	}
	/**
	* 获取名称格式化(toString)
	**/
	public String getCspName$(){
		String strValue="";
		 strValue=$.str(this.getCspName());
	 	 return strValue;
	}
	/**
	* 名称 [非空]      
	**/
	public void setCspName(String cspName){
		this.cspName = cspName;
		this.setSeted(F.cspName);
	}
	/*******************************负责人**********************************/	
	/**
	* 负责人       
	**/
	public String getCspPerson(){
		return this.cspPerson;
	}
	/**
	* 获取负责人格式化(toString)
	**/
	public String getCspPerson$(){
		String strValue="";
		 strValue=$.str(this.getCspPerson());
	 	 return strValue;
	}
	/**
	* 负责人       
	**/
	public void setCspPerson(String cspPerson){
		this.cspPerson = cspPerson;
		this.setSeted(F.cspPerson);
	}
	/*******************************联系方式**********************************/	
	/**
	* 联系方式       
	**/
	public String getCspConcat(){
		return this.cspConcat;
	}
	/**
	* 获取联系方式格式化(toString)
	**/
	public String getCspConcat$(){
		String strValue="";
		 strValue=$.str(this.getCspConcat());
	 	 return strValue;
	}
	/**
	* 联系方式       
	**/
	public void setCspConcat(String cspConcat){
		this.cspConcat = cspConcat;
		this.setSeted(F.cspConcat);
	}
	/*******************************程序标识**********************************/	
	/**
	* 程序标识       
	**/
	public String getCspFlag(){
		return this.cspFlag;
	}
	/**
	* 获取程序标识格式化(toString)
	**/
	public String getCspFlag$(){
		String strValue="";
		 strValue=$.str(this.getCspFlag());
	 	 return strValue;
	}
	/**
	* 程序标识       
	**/
	public void setCspFlag(String cspFlag){
		this.cspFlag = cspFlag;
		this.setSeted(F.cspFlag);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息       
	**/
	public String getCspRemark(){
		return this.cspRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCspRemark$(){
		String strValue="";
		 strValue=$.str(this.getCspRemark());
	 	 return strValue;
	}
	/**
	* 备注信息       
	**/
	public void setCspRemark(String cspRemark){
		this.cspRemark = cspRemark;
		this.setSeted(F.cspRemark);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCspUpdateTime(){
		return this.cspUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCspUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCspUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCspUpdateTime(Date cspUpdateTime){
		this.cspUpdateTime = cspUpdateTime;
		this.setSeted(F.cspUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCspAddTime(){
		return this.cspAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCspAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCspAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCspAddTime(Date cspAddTime){
		this.cspAddTime = cspAddTime;
		this.setSeted(F.cspAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCspStatus(){
		return this.cspStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCspStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCspStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCspStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCspStatus(Short cspStatus){
		this.cspStatus = cspStatus;
		this.setSeted(F.cspStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsProvid.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsProvid.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsProvid.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsProvid.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsProvid.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsProvid.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsProvid.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsProvid.this);
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
		public M cspId(Object cspId){this.put("cspId", cspId);return this;};
	 	/** and csp_id is null */
 		public M cspIdNull(){if(this.get("cspIdNot")==null)this.put("cspIdNot", "");this.put("cspId", null);return this;};
 		/** not .... */
 		public M cspIdNot(){this.put("cspIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M cspHost(Object cspHost){this.put("cspHost", cspHost);return this;};
	 	/** and csp_host is null */
 		public M cspHostNull(){if(this.get("cspHostNot")==null)this.put("cspHostNot", "");this.put("cspHost", null);return this;};
 		/** not .... */
 		public M cspHostNot(){this.put("cspHostNot", "not");return this;};
		/** 名称 [非空]       **/
		public M cspName(Object cspName){this.put("cspName", cspName);return this;};
	 	/** and csp_name is null */
 		public M cspNameNull(){if(this.get("cspNameNot")==null)this.put("cspNameNot", "");this.put("cspName", null);return this;};
 		/** not .... */
 		public M cspNameNot(){this.put("cspNameNot", "not");return this;};
		/** 负责人        **/
		public M cspPerson(Object cspPerson){this.put("cspPerson", cspPerson);return this;};
	 	/** and csp_person is null */
 		public M cspPersonNull(){if(this.get("cspPersonNot")==null)this.put("cspPersonNot", "");this.put("cspPerson", null);return this;};
 		/** not .... */
 		public M cspPersonNot(){this.put("cspPersonNot", "not");return this;};
		/** 联系方式        **/
		public M cspConcat(Object cspConcat){this.put("cspConcat", cspConcat);return this;};
	 	/** and csp_concat is null */
 		public M cspConcatNull(){if(this.get("cspConcatNot")==null)this.put("cspConcatNot", "");this.put("cspConcat", null);return this;};
 		/** not .... */
 		public M cspConcatNot(){this.put("cspConcatNot", "not");return this;};
		/** 程序标识        **/
		public M cspFlag(Object cspFlag){this.put("cspFlag", cspFlag);return this;};
	 	/** and csp_flag is null */
 		public M cspFlagNull(){if(this.get("cspFlagNot")==null)this.put("cspFlagNot", "");this.put("cspFlag", null);return this;};
 		/** not .... */
 		public M cspFlagNot(){this.put("cspFlagNot", "not");return this;};
		/** 备注信息        **/
		public M cspRemark(Object cspRemark){this.put("cspRemark", cspRemark);return this;};
	 	/** and csp_remark is null */
 		public M cspRemarkNull(){if(this.get("cspRemarkNot")==null)this.put("cspRemarkNot", "");this.put("cspRemark", null);return this;};
 		/** not .... */
 		public M cspRemarkNot(){this.put("cspRemarkNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M cspUpdateTime(Object cspUpdateTime){this.put("cspUpdateTime", cspUpdateTime);return this;};
	 	/** and csp_update_time is null */
 		public M cspUpdateTimeNull(){if(this.get("cspUpdateTimeNot")==null)this.put("cspUpdateTimeNot", "");this.put("cspUpdateTime", null);return this;};
 		/** not .... */
 		public M cspUpdateTimeNot(){this.put("cspUpdateTimeNot", "not");return this;};
 		/** and csp_update_time >= ? */
 		public M cspUpdateTimeStart(Object start){this.put("cspUpdateTimeStart", start);return this;};			
 		/** and csp_update_time <= ? */
 		public M cspUpdateTimeEnd(Object end){this.put("cspUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cspAddTime(Object cspAddTime){this.put("cspAddTime", cspAddTime);return this;};
	 	/** and csp_add_time is null */
 		public M cspAddTimeNull(){if(this.get("cspAddTimeNot")==null)this.put("cspAddTimeNot", "");this.put("cspAddTime", null);return this;};
 		/** not .... */
 		public M cspAddTimeNot(){this.put("cspAddTimeNot", "not");return this;};
 		/** and csp_add_time >= ? */
 		public M cspAddTimeStart(Object start){this.put("cspAddTimeStart", start);return this;};			
 		/** and csp_add_time <= ? */
 		public M cspAddTimeEnd(Object end){this.put("cspAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M cspStatus(Object cspStatus){this.put("cspStatus", cspStatus);return this;};
	 	/** and csp_status is null */
 		public M cspStatusNull(){if(this.get("cspStatusNot")==null)this.put("cspStatusNot", "");this.put("cspStatus", null);return this;};
 		/** not .... */
 		public M cspStatusNot(){this.put("cspStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有供应商 **/
		public @api List<CsProvid> list(Integer size){
			return getCsProvidList(this,size);
		}
		/** 获取供应商分页 **/
		public @api Page<CsProvid> page(int page,int size){
			return getCsProvidPage(page, size , this);
		}
		/** 根据查询条件取供应商 **/
		public @api CsProvid get(){
			return getCsProvid(this);
		}
		/** 获取供应商数 **/
		public @api Long count(){
			return getCsProvidCount(this);
		}
		/** 获取供应商表达式 **/
		public @api <T> T eval(String strEval){
			return getCsProvidEval(strEval,this);
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
			updateCsProvid(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cspId="cspId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cspHost="cspHost";
		/** 名称 [非空]       **/
		public final static @type(String.class) @like String cspName="cspName";
		/** 负责人        **/
		public final static @type(String.class) @like String cspPerson="cspPerson";
		/** 联系方式        **/
		public final static @type(String.class) @like String cspConcat="cspConcat";
		/** 程序标识        **/
		public final static @type(String.class) @like String cspFlag="cspFlag";
		/** 备注信息        **/
		public final static @type(String.class) @like String cspRemark="cspRemark";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cspUpdateTime="cspUpdateTime";
	 	/** and csp_update_time >= ? */
 		public final static @type(Date.class) String cspUpdateTimeStart="cspUpdateTimeStart";
 		/** and csp_update_time <= ? */
 		public final static @type(Date.class) String cspUpdateTimeEnd="cspUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cspAddTime="cspAddTime";
	 	/** and csp_add_time >= ? */
 		public final static @type(Date.class) String cspAddTimeStart="cspAddTimeStart";
 		/** and csp_add_time <= ? */
 		public final static @type(Date.class) String cspAddTimeEnd="cspAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String cspStatus="cspStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cspId="csp_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cspHost="csp_host";
		/** 名称 [非空]       **/
		public final static String cspName="csp_name";
		/** 负责人        **/
		public final static String cspPerson="csp_person";
		/** 联系方式        **/
		public final static String cspConcat="csp_concat";
		/** 程序标识        **/
		public final static String cspFlag="csp_flag";
		/** 备注信息        **/
		public final static String cspRemark="csp_remark";
		/** 修改时间 [非空]       **/
		public final static String cspUpdateTime="csp_update_time";
		/** 添加时间 [非空]       **/
		public final static String cspAddTime="csp_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String cspStatus="csp_status";
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
				$.Set(name,CsProvid.getCsProvid(params));
			else
				$.Set(name,CsProvid.getCsProvidList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取供应商数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsProvid) $.GetRequest("CsProvid$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsProvid.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsProvid.getCsProvid(params);
			else
				value = CsProvid.getCsProvidList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsProvid.Get($.add(CsProvid.F.cspId,param));
		else if(!$.empty(param.toString()))
			value = CsProvid.get(Long.valueOf(param.toString()));
		$.SetRequest("CsProvid$"+param.hashCode(), value);
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
	public void mergeSet(CsProvid old){
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