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

@namespace("unit/group")
public @caption("分组信息") @table("cs_unit_group") class CsUnitGroup implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csug_id")   @primary  @note("  ") Long csugId;// 主键 非空     
	private @caption("城市") @column("csug_host")    @relate("$csugHost") @RelateClass(SrvHost.class)  @note("  ") Long csugHost;// 非空     
 	private SrvHost $csugHost;//关联对象[运营城市]
	private @caption("所属单位") @column("csug_info")    @relate("$csugInfo") @RelateClass(CsUnitInfo.class)  @note("  ") Long csugInfo;// 非空     
 	private CsUnitInfo $csugInfo;//关联对象[企业信息]
	private @caption("组名称") @column("csug_name")    @note("  ") String csugName;// 非空     
	private @caption("审核人员") @column("csug_person")    @relate("$csugPerson") @RelateClass(CsUnitPerson.class)  @note(" {csugId}:csupGroup  ") Long csugPerson;// {csugId}:csupGroup     
 	private CsUnitPerson $csugPerson;//关联对象[用车人员]
	private @caption("备注") @column("csug_remark")    @note("  ") String csugRemark;//     
	private @caption("备忘信息") @column("csug_memo")    @note(" textarea:content text:sign  ") String csugMemo;// textarea:content text:sign     
	private @caption("程序数据") @column("csug_data")    @note("  ") String csugData;//     
	private @caption("修改时间") @column("csug_update_time")    @note("  ") Date csugUpdateTime;// 非空     
	private @caption("添加时间") @column("csug_add_time")    @note("  ") Date csugAddTime;// 非空     
	private @caption("状态") @column("csug_status")    @note(" 1:正常 0:无效  ") Short csugStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsUnitGroup(){
	
	}
	
	//主键构造函数
	public CsUnitGroup(Long id){
		this.csugId = id;
	}
	
	/**所有字段构造函数 CsUnitGroup(csugHost,csugInfo,csugName,csugPerson,csugRemark,csugMemo,csugData,csugUpdateTime,csugAddTime,csugStatus)
	 CsUnitGroup(
	 	$.getLong("csugHost")//城市 [非空]
	 	,$.getLong("csugInfo")//所属单位 [非空]
	 	,$.getString("csugName")//组名称 [非空]
	 	,$.getLong("csugPerson")//审核人员
	 	,$.getString("csugRemark")//备注
	 	,$.getString("csugMemo")//备忘信息
	 	,$.getString("csugData")//程序数据
	 	,$.getDate("csugUpdateTime")//修改时间 [非空]
	 	,$.getDate("csugAddTime")//添加时间 [非空]
	 	,$.getShort("csugStatus")//状态 [非空]
	 )
	**/
	public CsUnitGroup(Long csugHost,Long csugInfo,String csugName,Long csugPerson,String csugRemark,String csugMemo,String csugData,Date csugUpdateTime,Date csugAddTime,Short csugStatus){
		this.csugHost=csugHost;
		this.csugInfo=csugInfo;
		this.csugName=csugName;
		this.csugPerson=csugPerson;
		this.csugRemark=csugRemark;
		this.csugMemo=csugMemo;
		this.csugData=csugData;
		this.csugUpdateTime=csugUpdateTime;
		this.csugAddTime=csugAddTime;
		this.csugStatus=csugStatus;
	}
	
	//设置非空字段
	public CsUnitGroup setNotNull(Long csugHost,Long csugInfo,String csugName,Date csugUpdateTime,Date csugAddTime,Short csugStatus){
		this.csugHost=csugHost;
		this.csugInfo=csugInfo;
		this.csugName=csugName;
		this.csugUpdateTime=csugUpdateTime;
		this.csugAddTime=csugAddTime;
		this.csugStatus=csugStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsUnitGroup csugId(Long csugId){
		this.csugId = csugId;
		this.setSeted(F.csugId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsUnitGroup csugHost(Long csugHost){
		this.csugHost = csugHost;
		this.setSeted(F.csugHost);
		return this;
	}
	/** 所属单位 [非空] [CsUnitInfo]      **/
	public CsUnitGroup csugInfo(Long csugInfo){
		this.csugInfo = csugInfo;
		this.setSeted(F.csugInfo);
		return this;
	}
	/** 组名称 [非空]       **/
	public CsUnitGroup csugName(String csugName){
		this.csugName = csugName;
		this.setSeted(F.csugName);
		return this;
	}
	/** 审核人员  [CsUnitPerson]  {csugId}:csupGroup     **/
	public CsUnitGroup csugPerson(Long csugPerson){
		this.csugPerson = csugPerson;
		this.setSeted(F.csugPerson);
		return this;
	}
	/** 备注        **/
	public CsUnitGroup csugRemark(String csugRemark){
		this.csugRemark = csugRemark;
		this.setSeted(F.csugRemark);
		return this;
	}
	/** 备忘信息    textarea:content text:sign     **/
	public CsUnitGroup csugMemo(String csugMemo){
		this.csugMemo = csugMemo;
		this.setSeted(F.csugMemo);
		return this;
	}
	/** 程序数据        **/
	public CsUnitGroup csugData(String csugData){
		this.csugData = csugData;
		this.setSeted(F.csugData);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsUnitGroup csugUpdateTime(Date csugUpdateTime){
		this.csugUpdateTime = csugUpdateTime;
		this.setSeted(F.csugUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsUnitGroup csugAddTime(Date csugAddTime){
		this.csugAddTime = csugAddTime;
		this.setSeted(F.csugAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsUnitGroup csugStatus(Short csugStatus){
		this.csugStatus = csugStatus;
		this.setSeted(F.csugStatus);
		return this;
	}
	
	
	//克隆对象
	public CsUnitGroup clone(){
		CsUnitGroup clone = new CsUnitGroup();
		clone.csugHost=this.csugHost;
		clone.csugInfo=this.csugInfo;
		clone.csugName=this.csugName;
		clone.csugUpdateTime=this.csugUpdateTime;
		clone.csugAddTime=this.csugAddTime;
		clone.csugStatus=this.csugStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取分组信息
	 * @param id
	 * @return
	 */
	public static @api CsUnitGroup get(Long id){		
		return getCsUnitGroupById(id);
	}
	/**
	 * 获取所有分组信息
	 * @return
	 */
	public static @api List<CsUnitGroup> list(Map params,Integer size){
		return getCsUnitGroupList(params,size);
	}
	/**
	 * 获取分组信息分页
	 * @return
	 */
	public static @api Page<CsUnitGroup> page(int page,int size,Map params){
		return getCsUnitGroupPage(page, size , params);
	}
	/**
	 * 根据查询条件取分组信息
	 * @param params
	 * @return
	 */
	public static @api CsUnitGroup Get(Map params){
		return getCsUnitGroup(params);
	}
	/**
	 * 获取分组信息数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsUnitGroupCount(params);
	}
	/**
	 * 获取分组信息数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsUnitGroupEval(eval,params);
	}
	
	/**
	 * 根据ID取分组信息
	 * @param id
	 * @return
	 */
	public static @api CsUnitGroup getCsUnitGroupById(Long id){		
		CsUnitGroup csUnitGroup = (CsUnitGroup) $.GetRequest("CsUnitGroup$"+id);
		if(csUnitGroup!=null)
			return csUnitGroup;
		com.ccclubs.service.admin.ICsUnitGroupService csUnitGroupService = $.GetSpringBean("csUnitGroupService");		
		return csUnitGroupService.getCsUnitGroupById(id);
	}
	
	
	/**
	 * 根据ID取分组信息的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsUnitGroup.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsUnitGroup csUnitGroup = get(id);
		if(csUnitGroup!=null){
			String strValue = csUnitGroup.getCsugName$();
			if(!"CsugName".equals("CsugName"))
				strValue+="("+csUnitGroup.getCsugName$()+")";
			MemCache.setValue(CsUnitGroup.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsugName$();
		if(!"CsugName".equals("CsugName"))
			keyValue+="("+this.getCsugName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有分组信息
	 * @return
	 */
	public static @api List<CsUnitGroup> getCsUnitGroupList(Map params,Integer size){
		com.ccclubs.service.admin.ICsUnitGroupService csUnitGroupService = $.GetSpringBean("csUnitGroupService");
		return csUnitGroupService.getCsUnitGroupList(params, size);
	}
	
	/**
	 * 获取分组信息分页
	 * @return
	 */
	public static @api Page<CsUnitGroup> getCsUnitGroupPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsUnitGroupService csUnitGroupService = $.GetSpringBean("csUnitGroupService");
		return csUnitGroupService.getCsUnitGroupPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取分组信息
	 * @param params
	 * @return
	 */
	public static @api CsUnitGroup getCsUnitGroup(Map params){
		com.ccclubs.service.admin.ICsUnitGroupService csUnitGroupService = $.GetSpringBean("csUnitGroupService");
		return csUnitGroupService.getCsUnitGroup(params);
	}
	
	/**
	 * 获取分组信息数
	 * @return
	 */
	public static @api Long getCsUnitGroupCount(Map params){
		com.ccclubs.service.admin.ICsUnitGroupService csUnitGroupService = $.GetSpringBean("csUnitGroupService");
		return csUnitGroupService.getCsUnitGroupCount(params);
	}
		
		
	/**
	 * 获取分组信息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsUnitGroupEval(String eval,Map params){
		com.ccclubs.service.admin.ICsUnitGroupService csUnitGroupService = $.GetSpringBean("csUnitGroupService");
		return csUnitGroupService.getCsUnitGroupEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsUnitGroup(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsUnitGroupService csUnitGroupService = $.GetSpringBean("csUnitGroupService");
		csUnitGroupService.updateCsUnitGroupByConfirm(set, where);
	}
	
	
	/**
	 * 保存分组信息对象
	 * @param params
	 * @return
	 */
	public CsUnitGroup save(){
		com.ccclubs.service.admin.ICsUnitGroupService csUnitGroupService = $.GetSpringBean("csUnitGroupService");
		if(this.getCsugId()!=null)
			csUnitGroupService.updateCsUnitGroup(this);
		else
			return csUnitGroupService.saveCsUnitGroup(this);
		return this;
	}
	
	
	/**
	 * 更新分组信息对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsUnitGroupService csUnitGroupService = $.GetSpringBean("csUnitGroupService");
		csUnitGroupService.updateCsUnitGroup$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsUnitGroupService csUnitGroupService = $.GetSpringBean("csUnitGroupService");
		if($.equals($.config("logic_delete"),"true"))
			csUnitGroupService.removeCsUnitGroupById(this.getCsugId());
		else
			csUnitGroupService.deleteCsUnitGroupById(this.getCsugId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsUnitGroupService csUnitGroupService = $.GetSpringBean("csUnitGroupService");
		return csUnitGroupService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsugId(){
		return this.csugId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsugId$(){
		String strValue="";
		 strValue=$.str(this.getCsugId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsugId(Long csugId){
		this.csugId = csugId;
		this.setSeted(F.csugId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsugHost(){
		return this.csugHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsugHost$(){
		String strValue="";
		if(this.getCsugHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsugHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsugHost(Long csugHost){
		this.csugHost = csugHost;
		this.setSeted(F.csugHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csugHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsugHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsugHost()!=null){
 			srvHost = SrvHost.get(this.getCsugHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsugHost(), srvHost);
	 	return srvHost;
	}
	/*******************************所属单位**********************************/	
	/**
	* 所属单位 [非空] [CsUnitInfo]     
	**/
	public Long getCsugInfo(){
		return this.csugInfo;
	}
	/**
	* 获取所属单位格式化(toString)
	**/
	public String getCsugInfo$(){
		String strValue="";
		if(this.getCsugInfo()!=null){
				strValue+=$.str(CsUnitInfo.getKeyValue(this.getCsugInfo()));
		}			
	 	 return strValue;
	}
	/**
	* 所属单位 [非空] [CsUnitInfo]     
	**/
	public void setCsugInfo(Long csugInfo){
		this.csugInfo = csugInfo;
		this.setSeted(F.csugInfo);
	}
	/**
	* 获取关联对象[企业信息]
	**/	 			
 	public CsUnitInfo get$csugInfo(){
 		com.ccclubs.model.CsUnitInfo csUnitInfo = (com.ccclubs.model.CsUnitInfo) $.GetRequest("CsUnitInfo$"+this.getCsugInfo());
 		if(csUnitInfo!=null)
			return csUnitInfo;
		if(this.getCsugInfo()!=null){
 			csUnitInfo = CsUnitInfo.get(this.getCsugInfo());
 		}
 		$.SetRequest("CsUnitInfo$"+this.getCsugInfo(), csUnitInfo);
	 	return csUnitInfo;
	}
	/*******************************组名称**********************************/	
	/**
	* 组名称 [非空]      
	**/
	public String getCsugName(){
		return this.csugName;
	}
	/**
	* 获取组名称格式化(toString)
	**/
	public String getCsugName$(){
		String strValue="";
		 strValue=$.str(this.getCsugName());
	 	 return strValue;
	}
	/**
	* 组名称 [非空]      
	**/
	public void setCsugName(String csugName){
		this.csugName = csugName;
		this.setSeted(F.csugName);
	}
	/*******************************审核人员**********************************/	
	/**
	* 审核人员  [CsUnitPerson]  {csugId}:csupGroup    
	**/
	public Long getCsugPerson(){
		return this.csugPerson;
	}
	/**
	* 获取审核人员格式化(toString)
	**/
	public String getCsugPerson$(){
		String strValue="";
		if(this.getCsugPerson()!=null){
				strValue+=$.str(CsUnitPerson.getKeyValue(this.getCsugPerson()));
		}			
	 	 return strValue;
	}
	/**
	* 审核人员  [CsUnitPerson]  {csugId}:csupGroup    
	**/
	public void setCsugPerson(Long csugPerson){
		this.csugPerson = csugPerson;
		this.setSeted(F.csugPerson);
	}
	/**
	* 获取关联对象[用车人员]
	**/	 			
 	public CsUnitPerson get$csugPerson(){
 		com.ccclubs.model.CsUnitPerson csUnitPerson = (com.ccclubs.model.CsUnitPerson) $.GetRequest("CsUnitPerson$"+this.getCsugPerson());
 		if(csUnitPerson!=null)
			return csUnitPerson;
		if(this.getCsugPerson()!=null){
 			csUnitPerson = CsUnitPerson.get(this.getCsugPerson());
 		}
 		$.SetRequest("CsUnitPerson$"+this.getCsugPerson(), csUnitPerson);
	 	return csUnitPerson;
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getCsugRemark(){
		return this.csugRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCsugRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsugRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setCsugRemark(String csugRemark){
		this.csugRemark = csugRemark;
		this.setSeted(F.csugRemark);
	}
	/*******************************备忘信息**********************************/	
	/**
	* 备忘信息    textarea:content text:sign    
	**/
	public String getCsugMemo(){
		return this.csugMemo;
	}
	/**
	* 获取备忘信息格式化(toString)
	**/
	public String getCsugMemo$(){
		String strValue="";
		 strValue=$.str(this.getCsugMemo());
	 	 return strValue;
	}
	/**
	* 备忘信息    textarea:content text:sign    
	**/
	public void setCsugMemo(String csugMemo){
		this.csugMemo = csugMemo;
		this.setSeted(F.csugMemo);
	}
	/*******************************程序数据**********************************/	
	/**
	* 程序数据       
	**/
	public String getCsugData(){
		return this.csugData;
	}
	/**
	* 获取程序数据格式化(toString)
	**/
	public String getCsugData$(){
		String strValue="";
		 strValue=$.str(this.getCsugData());
	 	 return strValue;
	}
	/**
	* 程序数据       
	**/
	public void setCsugData(String csugData){
		this.csugData = csugData;
		this.setSeted(F.csugData);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsugUpdateTime(){
		return this.csugUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsugUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsugUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsugUpdateTime(Date csugUpdateTime){
		this.csugUpdateTime = csugUpdateTime;
		this.setSeted(F.csugUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsugAddTime(){
		return this.csugAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsugAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsugAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsugAddTime(Date csugAddTime){
		this.csugAddTime = csugAddTime;
		this.setSeted(F.csugAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsugStatus(){
		return this.csugStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsugStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsugStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsugStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsugStatus(Short csugStatus){
		this.csugStatus = csugStatus;
		this.setSeted(F.csugStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsUnitGroup.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUnitGroup.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUnitGroup.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsUnitGroup.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsUnitGroup.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUnitGroup.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUnitGroup.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsUnitGroup.this);
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
		public M csugId(Object csugId){this.put("csugId", csugId);return this;};
	 	/** and csug_id is null */
 		public M csugIdNull(){if(this.get("csugIdNot")==null)this.put("csugIdNot", "");this.put("csugId", null);return this;};
 		/** not .... */
 		public M csugIdNot(){this.put("csugIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csugHost(Object csugHost){this.put("csugHost", csugHost);return this;};
	 	/** and csug_host is null */
 		public M csugHostNull(){if(this.get("csugHostNot")==null)this.put("csugHostNot", "");this.put("csugHost", null);return this;};
 		/** not .... */
 		public M csugHostNot(){this.put("csugHostNot", "not");return this;};
		/** 所属单位 [非空] [CsUnitInfo]      **/
		public M csugInfo(Object csugInfo){this.put("csugInfo", csugInfo);return this;};
	 	/** and csug_info is null */
 		public M csugInfoNull(){if(this.get("csugInfoNot")==null)this.put("csugInfoNot", "");this.put("csugInfo", null);return this;};
 		/** not .... */
 		public M csugInfoNot(){this.put("csugInfoNot", "not");return this;};
		public M csugInfo$on(CsUnitInfo.M value){
			this.put("CsUnitInfo", value);
			this.put("csugInfo$on", value);
			return this;
		};	
		/** 组名称 [非空]       **/
		public M csugName(Object csugName){this.put("csugName", csugName);return this;};
	 	/** and csug_name is null */
 		public M csugNameNull(){if(this.get("csugNameNot")==null)this.put("csugNameNot", "");this.put("csugName", null);return this;};
 		/** not .... */
 		public M csugNameNot(){this.put("csugNameNot", "not");return this;};
		/** 审核人员  [CsUnitPerson]  {csugId}:csupGroup     **/
		public M csugPerson(Object csugPerson){this.put("csugPerson", csugPerson);return this;};
	 	/** and csug_person is null */
 		public M csugPersonNull(){if(this.get("csugPersonNot")==null)this.put("csugPersonNot", "");this.put("csugPerson", null);return this;};
 		/** not .... */
 		public M csugPersonNot(){this.put("csugPersonNot", "not");return this;};
		/** 备注        **/
		public M csugRemark(Object csugRemark){this.put("csugRemark", csugRemark);return this;};
	 	/** and csug_remark is null */
 		public M csugRemarkNull(){if(this.get("csugRemarkNot")==null)this.put("csugRemarkNot", "");this.put("csugRemark", null);return this;};
 		/** not .... */
 		public M csugRemarkNot(){this.put("csugRemarkNot", "not");return this;};
		/** 备忘信息    textarea:content text:sign     **/
		public M csugMemo(Object csugMemo){this.put("csugMemo", csugMemo);return this;};
	 	/** and csug_memo is null */
 		public M csugMemoNull(){if(this.get("csugMemoNot")==null)this.put("csugMemoNot", "");this.put("csugMemo", null);return this;};
 		/** not .... */
 		public M csugMemoNot(){this.put("csugMemoNot", "not");return this;};
		/** 程序数据        **/
		public M csugData(Object csugData){this.put("csugData", csugData);return this;};
	 	/** and csug_data is null */
 		public M csugDataNull(){if(this.get("csugDataNot")==null)this.put("csugDataNot", "");this.put("csugData", null);return this;};
 		/** not .... */
 		public M csugDataNot(){this.put("csugDataNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csugUpdateTime(Object csugUpdateTime){this.put("csugUpdateTime", csugUpdateTime);return this;};
	 	/** and csug_update_time is null */
 		public M csugUpdateTimeNull(){if(this.get("csugUpdateTimeNot")==null)this.put("csugUpdateTimeNot", "");this.put("csugUpdateTime", null);return this;};
 		/** not .... */
 		public M csugUpdateTimeNot(){this.put("csugUpdateTimeNot", "not");return this;};
 		/** and csug_update_time >= ? */
 		public M csugUpdateTimeStart(Object start){this.put("csugUpdateTimeStart", start);return this;};			
 		/** and csug_update_time <= ? */
 		public M csugUpdateTimeEnd(Object end){this.put("csugUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csugAddTime(Object csugAddTime){this.put("csugAddTime", csugAddTime);return this;};
	 	/** and csug_add_time is null */
 		public M csugAddTimeNull(){if(this.get("csugAddTimeNot")==null)this.put("csugAddTimeNot", "");this.put("csugAddTime", null);return this;};
 		/** not .... */
 		public M csugAddTimeNot(){this.put("csugAddTimeNot", "not");return this;};
 		/** and csug_add_time >= ? */
 		public M csugAddTimeStart(Object start){this.put("csugAddTimeStart", start);return this;};			
 		/** and csug_add_time <= ? */
 		public M csugAddTimeEnd(Object end){this.put("csugAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csugStatus(Object csugStatus){this.put("csugStatus", csugStatus);return this;};
	 	/** and csug_status is null */
 		public M csugStatusNull(){if(this.get("csugStatusNot")==null)this.put("csugStatusNot", "");this.put("csugStatus", null);return this;};
 		/** not .... */
 		public M csugStatusNot(){this.put("csugStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有分组信息 **/
		public @api List<CsUnitGroup> list(Integer size){
			return getCsUnitGroupList(this,size);
		}
		/** 获取分组信息分页 **/
		public @api Page<CsUnitGroup> page(int page,int size){
			return getCsUnitGroupPage(page, size , this);
		}
		/** 根据查询条件取分组信息 **/
		public @api CsUnitGroup get(){
			return getCsUnitGroup(this);
		}
		/** 获取分组信息数 **/
		public @api Long count(){
			return getCsUnitGroupCount(this);
		}
		/** 获取分组信息表达式 **/
		public @api <T> T eval(String strEval){
			return getCsUnitGroupEval(strEval,this);
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
			updateCsUnitGroup(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csugId="csugId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csugHost="csugHost";
		/** 所属单位 [非空] [CsUnitInfo]      **/
		public final static @type(Long.class)  String csugInfo="csugInfo";
		/** 组名称 [非空]       **/
		public final static @type(String.class) @like String csugName="csugName";
		/** 审核人员  [CsUnitPerson]  {csugId}:csupGroup     **/
		public final static @type(Long.class)  String csugPerson="csugPerson";
		/** 备注        **/
		public final static @type(String.class) @like String csugRemark="csugRemark";
		/** 备忘信息    textarea:content text:sign     **/
		public final static @type(String.class) @like String csugMemo="csugMemo";
		/** 程序数据        **/
		public final static @type(String.class) @like String csugData="csugData";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csugUpdateTime="csugUpdateTime";
	 	/** and csug_update_time >= ? */
 		public final static @type(Date.class) String csugUpdateTimeStart="csugUpdateTimeStart";
 		/** and csug_update_time <= ? */
 		public final static @type(Date.class) String csugUpdateTimeEnd="csugUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csugAddTime="csugAddTime";
	 	/** and csug_add_time >= ? */
 		public final static @type(Date.class) String csugAddTimeStart="csugAddTimeStart";
 		/** and csug_add_time <= ? */
 		public final static @type(Date.class) String csugAddTimeEnd="csugAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csugStatus="csugStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csugId="csug_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csugHost="csug_host";
		/** 所属单位 [非空] [CsUnitInfo]      **/
		public final static String csugInfo="csug_info";
		/** 组名称 [非空]       **/
		public final static String csugName="csug_name";
		/** 审核人员  [CsUnitPerson]  {csugId}:csupGroup     **/
		public final static String csugPerson="csug_person";
		/** 备注        **/
		public final static String csugRemark="csug_remark";
		/** 备忘信息    textarea:content text:sign     **/
		public final static String csugMemo="csug_memo";
		/** 程序数据        **/
		public final static String csugData="csug_data";
		/** 修改时间 [非空]       **/
		public final static String csugUpdateTime="csug_update_time";
		/** 添加时间 [非空]       **/
		public final static String csugAddTime="csug_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csugStatus="csug_status";
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
				$.Set(name,CsUnitGroup.getCsUnitGroup(params));
			else
				$.Set(name,CsUnitGroup.getCsUnitGroupList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取分组信息数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsUnitGroup) $.GetRequest("CsUnitGroup$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsUnitGroup.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsUnitGroup.getCsUnitGroup(params);
			else
				value = CsUnitGroup.getCsUnitGroupList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsUnitGroup.Get($.add(CsUnitGroup.F.csugId,param));
		else if(!$.empty(param.toString()))
			value = CsUnitGroup.get(Long.valueOf(param.toString()));
		$.SetRequest("CsUnitGroup$"+param.hashCode(), value);
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
	public void mergeSet(CsUnitGroup old){
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