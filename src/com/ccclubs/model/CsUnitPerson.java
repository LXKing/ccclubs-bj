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

@namespace("unit/person")
public @caption("用车人员") @table("cs_unit_person") class CsUnitPerson implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csup_id")   @primary   @note("  ") Long csupId;
	private @caption("城市") @column("csup_host")    @relate("$csupHost") @RelateClass(SrvHost.class)  @note("  ") Long csupHost;
	private SrvHost $csupHost;//关联对象[城市]
	private @caption("所属单位") @column("csup_info")    @relate("$csupInfo") @RelateClass(CsUnitInfo.class)  @note("  ") Long csupInfo;
	private CsUnitInfo $csupInfo;//关联对象[所属单位]
	private @caption("姓名") @column("csup_name")      @note("  ") String csupName;
	private @caption("所属分组") @column("csup_group")    @relate("$csupGroup") @RelateClass(CsUnitGroup.class)  @note("  ") Long csupGroup;
	private CsUnitGroup $csupGroup;//关联对象[所属分组]
	private @caption("帐号信息") @column("csup_member")    @relate("$csupMember") @RelateClass(CsMember.class)  @note("  ") Long csupMember;
	private CsMember $csupMember;//关联对象[帐号信息]
	private @caption("备注") @column("csup_remark")     @hidden @note("  ") String csupRemark;
	private @caption("备忘信息") @column("csup_memo")      @note(" textarea:content text:sign  ") String csupMemo;
	private @caption("修改时间") @column("csup_update_time")      @note("  ") Date csupUpdateTime;
	private @caption("添加时间") @column("csup_add_time")      @note("  ") Date csupAddTime;
	private @caption("状态") @column("csup_status")      @note(" 1:正常 0:无效  ") Short csupStatus;
	private @caption("标识") @column("csup_flag")      @note("") String csupFlag;
	
	//默认构造函数
	public CsUnitPerson(){
	
	}
	
	//主键构造函数
	public CsUnitPerson(Long id){
		this.csupId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsUnitPerson(Long csupHost,Long csupInfo,String csupName,Long csupGroup,Long csupMember,String csupRemark,String csupMemo,Date csupUpdateTime,Date csupAddTime,Short csupStatus,String csupFlag){
		this.csupHost=csupHost;
		this.csupInfo=csupInfo;
		this.csupName=csupName;
		this.csupGroup=csupGroup;
		this.csupMember=csupMember;
		this.csupRemark=csupRemark;
		this.csupMemo=csupMemo;
		this.csupUpdateTime=csupUpdateTime;
		this.csupAddTime=csupAddTime;
		this.csupStatus=csupStatus;
		this.csupFlag=csupFlag;
	}
	
	//设置非空字段
	public CsUnitPerson setNotNull(Long csupHost,Long csupInfo,String csupName,Long csupGroup,Long csupMember,Date csupUpdateTime,Date csupAddTime,Short csupStatus){
		this.csupHost=csupHost;
		this.csupInfo=csupInfo;
		this.csupName=csupName;
		this.csupGroup=csupGroup;
		this.csupMember=csupMember;
		this.csupUpdateTime=csupUpdateTime;
		this.csupAddTime=csupAddTime;
		this.csupStatus=csupStatus;
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsUnitPerson csupId(Long csupId){
		this.csupId = csupId;
		this.setSeted(F.csupId);
		return this;
	}
	/** 城市 [非空]       **/
	public CsUnitPerson csupHost(Long csupHost){
		this.csupHost = csupHost;
		this.setSeted(F.csupHost);
		return this;
	}
	/** 所属单位 [非空]       **/
	public CsUnitPerson csupInfo(Long csupInfo){
		this.csupInfo = csupInfo;
		this.setSeted(F.csupInfo);
		return this;
	}
	/** 姓名 [非空]       **/
	public CsUnitPerson csupName(String csupName){
		this.csupName = csupName;
		this.setSeted(F.csupName);
		return this;
	}
	/** 所属分组 [非空]       **/
	public CsUnitPerson csupGroup(Long csupGroup){
		this.csupGroup = csupGroup;
		this.setSeted(F.csupGroup);
		return this;
	}
	/** 帐号信息 [非空]       **/
	public CsUnitPerson csupMember(Long csupMember){
		this.csupMember = csupMember;
		this.setSeted(F.csupMember);
		return this;
	}
	/** 备注 [可空]       **/
	public CsUnitPerson csupRemark(String csupRemark){
		this.csupRemark = csupRemark;
		this.setSeted(F.csupRemark);
		return this;
	}
	/** 备忘信息 [可空]       **/
	public CsUnitPerson csupMemo(String csupMemo){
		this.csupMemo = csupMemo;
		this.setSeted(F.csupMemo);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsUnitPerson csupUpdateTime(Date csupUpdateTime){
		this.csupUpdateTime = csupUpdateTime;
		this.setSeted(F.csupUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsUnitPerson csupAddTime(Date csupAddTime){
		this.csupAddTime = csupAddTime;
		this.setSeted(F.csupAddTime);
		return this;
	}
	/** 状态 [非空]       **/
	public CsUnitPerson csupStatus(Short csupStatus){
		this.csupStatus = csupStatus;
		this.setSeted(F.csupStatus);
		return this;
	}
	/** 标识 [非空]       **/
	public CsUnitPerson csupFlag(String csupFlag){
		this.csupFlag = csupFlag;
		this.setSeted(F.csupFlag);
		return this;
	}
	
	//克隆对象
	public CsUnitPerson clone(){
		CsUnitPerson clone = new CsUnitPerson();
		clone.csupId=this.csupId;
		clone.csupHost=this.csupHost;
		clone.csupInfo=this.csupInfo;
		clone.csupName=this.csupName;
		clone.csupGroup=this.csupGroup;
		clone.csupMember=this.csupMember;
		clone.csupRemark=this.csupRemark;
		clone.csupMemo=this.csupMemo;
		clone.csupUpdateTime=this.csupUpdateTime;
		clone.csupAddTime=this.csupAddTime;
		clone.csupStatus=this.csupStatus;
		clone.csupFlag=this.csupFlag;
		return clone;
	}
	
	
	/**
	 * 根据ID取用车人员
	 * @param id
	 * @return
	 */
	public static @api CsUnitPerson get(Long id){		
		return getCsUnitPersonById(id);
	}
	/**
	 * 获取所有用车人员
	 * @return
	 */
	public static @api List<CsUnitPerson> list(Map params,Integer size){
		return getCsUnitPersonList(params,size);
	}
	/**
	 * 获取用车人员分页
	 * @return
	 */
	public static @api Page<CsUnitPerson> page(int page,int size,Map params){
		return getCsUnitPersonPage(page, size , params);
	}
	/**
	 * 根据查询条件取用车人员
	 * @param params
	 * @return
	 */
	public static @api CsUnitPerson Get(Map params){
		return getCsUnitPerson(params);
	}
	/**
	 * 获取用车人员数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsUnitPersonCount(params);
	}
	/**
	 * 获取用车人员数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsUnitPersonEval(eval,params);
	}
	
	/**
	 * 根据ID取用车人员
	 * @param id
	 * @return
	 */
	public static @api CsUnitPerson getCsUnitPersonById(Long id){		
		CsUnitPerson csUnitPerson = (CsUnitPerson) $.GetRequest("CsUnitPerson$"+id);
		if(csUnitPerson!=null)
			return csUnitPerson;
		com.ccclubs.service.admin.ICsUnitPersonService csUnitPersonService = $.GetSpringBean("csUnitPersonService");		
		return csUnitPersonService.getCsUnitPersonById(id);
	}
	
	
	/**
	 * 根据ID取用车人员的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsUnitPerson.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsUnitPerson csUnitPerson = get(id);
		if(csUnitPerson!=null){
			String strValue = csUnitPerson.getCsupName$();
			if(!"CsupName".equals("CsupName"))
				strValue+="("+csUnitPerson.getCsupName$()+")";
			MemCache.setValue(CsUnitPerson.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsupName$();
		if(!"CsupName".equals("CsupName"))
			keyValue+="("+this.getCsupName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有用车人员
	 * @return
	 */
	public static @api List<CsUnitPerson> getCsUnitPersonList(Map params,Integer size){
		com.ccclubs.service.admin.ICsUnitPersonService csUnitPersonService = $.GetSpringBean("csUnitPersonService");
		return csUnitPersonService.getCsUnitPersonList(params, size);
	}
	
	/**
	 * 获取用车人员分页
	 * @return
	 */
	public static @api Page<CsUnitPerson> getCsUnitPersonPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsUnitPersonService csUnitPersonService = $.GetSpringBean("csUnitPersonService");
		return csUnitPersonService.getCsUnitPersonPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取用车人员
	 * @param params
	 * @return
	 */
	public static @api CsUnitPerson getCsUnitPerson(Map params){
		com.ccclubs.service.admin.ICsUnitPersonService csUnitPersonService = $.GetSpringBean("csUnitPersonService");
		return csUnitPersonService.getCsUnitPerson(params);
	}
	
	/**
	 * 获取用车人员数
	 * @return
	 */
	public static @api Long getCsUnitPersonCount(Map params){
		com.ccclubs.service.admin.ICsUnitPersonService csUnitPersonService = $.GetSpringBean("csUnitPersonService");
		return csUnitPersonService.getCsUnitPersonCount(params);
	}
		
		
	/**
	 * 获取用车人员自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsUnitPersonEval(String eval,Map params){
		com.ccclubs.service.admin.ICsUnitPersonService csUnitPersonService = $.GetSpringBean("csUnitPersonService");
		return csUnitPersonService.getCsUnitPersonEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsUnitPerson(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsUnitPersonService csUnitPersonService = $.GetSpringBean("csUnitPersonService");
		csUnitPersonService.updateCsUnitPersonByConfirm(set, where);
	}
	
	
	/**
	 * 保存用车人员对象
	 * @param params
	 * @return
	 */
	public CsUnitPerson save(){
		com.ccclubs.service.admin.ICsUnitPersonService csUnitPersonService = $.GetSpringBean("csUnitPersonService");
		if(this.getCsupId()!=null)
			csUnitPersonService.updateCsUnitPerson(this);
		else
			return csUnitPersonService.saveCsUnitPerson(this);
		return this;
	}
	
	
	/**
	 * 更新用车人员对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsUnitPersonService csUnitPersonService = $.GetSpringBean("csUnitPersonService");
		csUnitPersonService.updateCsUnitPerson$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsUnitPersonService csUnitPersonService = $.GetSpringBean("csUnitPersonService");
		if($.equals($.config("logic_delete"),"true"))
			csUnitPersonService.removeCsUnitPersonById(this.getCsupId());
		else
			csUnitPersonService.deleteCsUnitPersonById(this.getCsupId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsUnitPersonService csUnitPersonService = $.GetSpringBean("csUnitPersonService");
		return csUnitPersonService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsupId(){
		return this.csupId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsupId$(){
		String strValue="";
		 strValue=$.str(this.getCsupId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCsupId(Long csupId){
		this.csupId = csupId;
		this.setSeted(F.csupId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空]      
	**/
	public Long getCsupHost(){
		return this.csupHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsupHost$(){
		String strValue="";
		if(this.getCsupHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsupHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空]     
	**/
	public void setCsupHost(Long csupHost){
		this.csupHost = csupHost;
		this.setSeted(F.csupHost);
	}
	/**
	* 获取关联对象[城市]
	**/	 			
 	public SrvHost get$csupHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsupHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsupHost()!=null){
			srvHost = SrvHost.get(this.getCsupHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsupHost(), srvHost);
	 	return srvHost;
	}
	/*******************************所属单位**********************************/	
	/**
	* 所属单位 [非空]      
	**/
	public Long getCsupInfo(){
		return this.csupInfo;
	}
	/**
	* 获取所属单位格式化(toString)
	**/
	public String getCsupInfo$(){
		String strValue="";
		if(this.getCsupInfo()!=null){
				strValue+=$.str(CsUnitInfo.getKeyValue(this.getCsupInfo()));
		}			
	 	 return strValue;
	}
	/**
	* 所属单位 [非空]     
	**/
	public void setCsupInfo(Long csupInfo){
		this.csupInfo = csupInfo;
		this.setSeted(F.csupInfo);
	}
	/**
	* 获取关联对象[所属单位]
	**/	 			
 	public CsUnitInfo get$csupInfo(){
 		com.ccclubs.model.CsUnitInfo csUnitInfo = (com.ccclubs.model.CsUnitInfo) $.GetRequest("CsUnitInfo$"+this.getCsupInfo());
 		if(csUnitInfo!=null)
			return csUnitInfo;
		if(this.getCsupInfo()!=null){
			csUnitInfo = CsUnitInfo.get(this.getCsupInfo());
 		}
 		$.SetRequest("CsUnitInfo$"+this.getCsupInfo(), csUnitInfo);
	 	return csUnitInfo;
	}
	/*******************************姓名**********************************/	
	/**
	* 姓名 [非空]      
	**/
	public String getCsupName(){
		return this.csupName;
	}
	/**
	* 获取姓名格式化(toString)
	**/
	public String getCsupName$(){
		String strValue="";
		 strValue=$.str(this.getCsupName());
	 	 return strValue;
	}
	/**
	* 姓名 [非空]     
	**/
	public void setCsupName(String csupName){
		this.csupName = csupName;
		this.setSeted(F.csupName);
	}
	/*******************************所属分组**********************************/	
	/**
	* 所属分组 [非空]      
	**/
	public Long getCsupGroup(){
		return this.csupGroup;
	}
	/**
	* 获取所属分组格式化(toString)
	**/
	public String getCsupGroup$(){
		String strValue="";
		if(this.getCsupGroup()!=null){
				strValue+=$.str(CsUnitGroup.getKeyValue(this.getCsupGroup()));
		}			
	 	 return strValue;
	}
	/**
	* 所属分组 [非空]     
	**/
	public void setCsupGroup(Long csupGroup){
		this.csupGroup = csupGroup;
		this.setSeted(F.csupGroup);
	}
	/**
	* 获取关联对象[所属分组]
	**/	 			
 	public CsUnitGroup get$csupGroup(){
 		com.ccclubs.model.CsUnitGroup csUnitGroup = (com.ccclubs.model.CsUnitGroup) $.GetRequest("CsUnitGroup$"+this.getCsupGroup());
 		if(csUnitGroup!=null)
			return csUnitGroup;
		if(this.getCsupGroup()!=null){
			csUnitGroup = CsUnitGroup.get(this.getCsupGroup());
 		}
 		$.SetRequest("CsUnitGroup$"+this.getCsupGroup(), csUnitGroup);
	 	return csUnitGroup;
	}
	/*******************************帐号信息**********************************/	
	/**
	* 帐号信息 [非空]      
	**/
	public Long getCsupMember(){
		return this.csupMember;
	}
	/**
	* 获取帐号信息格式化(toString)
	**/
	public String getCsupMember$(){
		String strValue="";
		if(this.getCsupMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsupMember()));
		}			
	 	 return strValue;
	}
	/**
	* 帐号信息 [非空]     
	**/
	public void setCsupMember(Long csupMember){
		this.csupMember = csupMember;
		this.setSeted(F.csupMember);
	}
	/**
	* 获取关联对象[帐号信息]
	**/	 			
 	public CsMember get$csupMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsupMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsupMember()!=null){
			csMember = CsMember.get(this.getCsupMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsupMember(), csMember);
	 	return csMember;
	}
	/*******************************备注**********************************/	
	/**
	* 备注 [可空]      
	**/
	public String getCsupRemark(){
		return this.csupRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCsupRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsupRemark());
	 	 return strValue;
	}
	/**
	* 备注 [可空]     
	**/
	public void setCsupRemark(String csupRemark){
		this.csupRemark = csupRemark;
		this.setSeted(F.csupRemark);
	}
	/*******************************备忘信息**********************************/	
	/**
	* 备忘信息 [可空]      
	**/
	public String getCsupMemo(){
		return this.csupMemo;
	}
	/**
	* 获取备忘信息格式化(toString)
	**/
	public String getCsupMemo$(){
		String strValue="";
		 strValue=$.str(this.getCsupMemo());
	 	 return strValue;
	}
	/**
	* 备忘信息 [可空]     
	**/
	public void setCsupMemo(String csupMemo){
		this.csupMemo = csupMemo;
		this.setSeted(F.csupMemo);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsupUpdateTime(){
		return this.csupUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsupUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsupUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCsupUpdateTime(Date csupUpdateTime){
		this.csupUpdateTime = csupUpdateTime;
		this.setSeted(F.csupUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsupAddTime(){
		return this.csupAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsupAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsupAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]     
	**/
	public void setCsupAddTime(Date csupAddTime){
		this.csupAddTime = csupAddTime;
		this.setSeted(F.csupAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]      
	**/
	public Short getCsupStatus(){
		return this.csupStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsupStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsupStatus()),"1"))
			 strValue=$.str("正常");
		 if($.equals($.str(this.getCsupStatus()),"0"))
			 strValue=$.str("无效");
	 	 return strValue;
	}
	/**
	* 状态 [非空]     
	**/
	public void setCsupStatus(Short csupStatus){
		this.csupStatus = csupStatus;
		this.setSeted(F.csupStatus);
	}
	/*******************************标识**********************************/	
	/**
	* 标识 [非空]      
	**/
	public String getCsupFlag(){
		return this.csupFlag;
	}
	/**
	* 获取标识格式化(toString)
	**/
	public String getCsupFlag$(){
		String strValue="";
		 strValue=$.str(this.getCsupFlag());
	 	 return strValue;
	}
	/**
	* 标识 [非空]     
	**/
	public void setCsupFlag(String csupFlag){
		this.csupFlag = csupFlag;
		this.setSeted(F.csupFlag);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsUnitPerson.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUnitPerson.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUnitPerson.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsUnitPerson.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsUnitPerson.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUnitPerson.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUnitPerson.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsUnitPerson.this);
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
		public M csupId(Object csupId){this.put("csupId", csupId);return this;};
		/** and csup_id is null */
		public M csupIdNull(){if(this.get("csupIdNot")==null)this.put("csupIdNot", "");this.put("csupId", null);return this;};
		/** not .... */
		public M csupIdNot(){this.put("csupIdNot", "not");return this;};
		/** 城市 [非空]       **/
		public M csupHost(Object csupHost){this.put("csupHost", csupHost);return this;};
		/** and csup_host is null */
		public M csupHostNull(){if(this.get("csupHostNot")==null)this.put("csupHostNot", "");this.put("csupHost", null);return this;};
		/** not .... */
		public M csupHostNot(){this.put("csupHostNot", "not");return this;};
		/** 所属单位 [非空]       **/
		public M csupInfo(Object csupInfo){this.put("csupInfo", csupInfo);return this;};
		/** and csup_info is null */
		public M csupInfoNull(){if(this.get("csupInfoNot")==null)this.put("csupInfoNot", "");this.put("csupInfo", null);return this;};
		/** not .... */
		public M csupInfoNot(){this.put("csupInfoNot", "not");return this;};
		public M csupInfo$on(CsUnitInfo.M value){
			this.put("CsUnitInfo", value);
			this.put("csupInfo$on", value);
			return this;
		};	
		/** 姓名 [非空]       **/
		public M csupName(Object csupName){this.put("csupName", csupName);return this;};
		/** and csup_name is null */
		public M csupNameNull(){if(this.get("csupNameNot")==null)this.put("csupNameNot", "");this.put("csupName", null);return this;};
		/** not .... */
		public M csupNameNot(){this.put("csupNameNot", "not");return this;};
		/** 所属分组 [非空]       **/
		public M csupGroup(Object csupGroup){this.put("csupGroup", csupGroup);return this;};
		/** and csup_group is null */
		public M csupGroupNull(){if(this.get("csupGroupNot")==null)this.put("csupGroupNot", "");this.put("csupGroup", null);return this;};
		/** not .... */
		public M csupGroupNot(){this.put("csupGroupNot", "not");return this;};
		public M csupGroup$on(CsUnitGroup.M value){
			this.put("CsUnitGroup", value);
			this.put("csupGroup$on", value);
			return this;
		};	
		/** 帐号信息 [非空]       **/
		public M csupMember(Object csupMember){this.put("csupMember", csupMember);return this;};
		/** and csup_member is null */
		public M csupMemberNull(){if(this.get("csupMemberNot")==null)this.put("csupMemberNot", "");this.put("csupMember", null);return this;};
		/** not .... */
		public M csupMemberNot(){this.put("csupMemberNot", "not");return this;};
		public M csupMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csupMember$on", value);
			return this;
		};	
		/** 备注 [可空]       **/
		public M csupRemark(Object csupRemark){this.put("csupRemark", csupRemark);return this;};
		/** and csup_remark is null */
		public M csupRemarkNull(){if(this.get("csupRemarkNot")==null)this.put("csupRemarkNot", "");this.put("csupRemark", null);return this;};
		/** not .... */
		public M csupRemarkNot(){this.put("csupRemarkNot", "not");return this;};
		/** 备忘信息 [可空]       **/
		public M csupMemo(Object csupMemo){this.put("csupMemo", csupMemo);return this;};
		/** and csup_memo is null */
		public M csupMemoNull(){if(this.get("csupMemoNot")==null)this.put("csupMemoNot", "");this.put("csupMemo", null);return this;};
		/** not .... */
		public M csupMemoNot(){this.put("csupMemoNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csupUpdateTime(Object csupUpdateTime){this.put("csupUpdateTime", csupUpdateTime);return this;};
		/** and csup_update_time is null */
		public M csupUpdateTimeNull(){if(this.get("csupUpdateTimeNot")==null)this.put("csupUpdateTimeNot", "");this.put("csupUpdateTime", null);return this;};
		/** not .... */
		public M csupUpdateTimeNot(){this.put("csupUpdateTimeNot", "not");return this;};
		/** and csup_update_time >= ? */
 		public M csupUpdateTimeStart(Object start){this.put("csupUpdateTimeStart", start);return this;};			
 		/** and csup_update_time <= ? */
 		public M csupUpdateTimeEnd(Object end){this.put("csupUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csupAddTime(Object csupAddTime){this.put("csupAddTime", csupAddTime);return this;};
		/** and csup_add_time is null */
		public M csupAddTimeNull(){if(this.get("csupAddTimeNot")==null)this.put("csupAddTimeNot", "");this.put("csupAddTime", null);return this;};
		/** not .... */
		public M csupAddTimeNot(){this.put("csupAddTimeNot", "not");return this;};
		/** and csup_add_time >= ? */
 		public M csupAddTimeStart(Object start){this.put("csupAddTimeStart", start);return this;};			
 		/** and csup_add_time <= ? */
 		public M csupAddTimeEnd(Object end){this.put("csupAddTimeEnd", end);return this;};
		/** 状态 [非空]       **/
		public M csupStatus(Object csupStatus){this.put("csupStatus", csupStatus);return this;};
		/** and csup_status is null */
		public M csupStatusNull(){if(this.get("csupStatusNot")==null)this.put("csupStatusNot", "");this.put("csupStatus", null);return this;};
		/** not .... */
		public M csupStatusNot(){this.put("csupStatusNot", "not");return this;};
		/** 标识 [非空]       **/
		public M csupFlag(Object csupFlag){this.put("csupFlag", csupFlag);return this;};
		/** and csup_flag is null */
		public M csupFlagNull(){if(this.get("csupFlagNot")==null)this.put("csupFlagNot", "");this.put("csupFlag", null);return this;};
		/** not .... */
		public M csupFlagNot(){this.put("csupFlagNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有用车人员 **/
		public @api List<CsUnitPerson> list(Integer size){
			return getCsUnitPersonList(this,size);
		}
		/** 获取用车人员分页 **/
		public @api Page<CsUnitPerson> page(int page,int size){
			return getCsUnitPersonPage(page, size , this);
		}
		/** 根据查询条件取用车人员 **/
		public @api CsUnitPerson get(){
			return getCsUnitPerson(this);
		}
		/** 获取用车人员数 **/
		public @api Long count(){
			return getCsUnitPersonCount(this);
		}
		/** 获取用车人员表达式 **/
		public @api <T> T eval(String strEval){
			return getCsUnitPersonEval(strEval,this);
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
			updateCsUnitPerson(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csupId="csupId";
		/** 城市 [非空]       **/
		public final static @type(Long.class)  String csupHost="csupHost";
		/** 所属单位 [非空]       **/
		public final static @type(Long.class)  String csupInfo="csupInfo";
		/** 姓名 [非空]       **/
		public final static @type(String.class)@like  String csupName="csupName";
		/** 所属分组 [非空]       **/
		public final static @type(Long.class)  String csupGroup="csupGroup";
		/** 帐号信息 [非空]       **/
		public final static @type(Long.class)  String csupMember="csupMember";
		/** 备注 [可空]       **/
		public final static @type(String.class)@like  String csupRemark="csupRemark";
		/** 备忘信息 [可空]       **/
		public final static @type(String.class)@like  String csupMemo="csupMemo";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csupUpdateTime="csupUpdateTime";
		/** and csup_update_time >= ? */
 		public final static @type(Date.class) String csupUpdateTimeStart="csupUpdateTimeStart";
 		/** and csup_update_time <= ? */
 		public final static @type(Date.class) String csupUpdateTimeEnd="csupUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csupAddTime="csupAddTime";
		/** and csup_add_time >= ? */
 		public final static @type(Date.class) String csupAddTimeStart="csupAddTimeStart";
 		/** and csup_add_time <= ? */
 		public final static @type(Date.class) String csupAddTimeEnd="csupAddTimeEnd";
		/** 状态 [非空]       **/
		public final static @type(Short.class)  String csupStatus="csupStatus";
		/** 标识 [非空]       **/
		public final static @type(String.class)  String csupFlag="csupFlag";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csupId="csup_id";
		/** 城市 [非空]       **/
		public final static String csupHost="csup_host";
		/** 所属单位 [非空]       **/
		public final static String csupInfo="csup_info";
		/** 姓名 [非空]       **/
		public final static String csupName="csup_name";
		/** 所属分组 [非空]       **/
		public final static String csupGroup="csup_group";
		/** 帐号信息 [非空]       **/
		public final static String csupMember="csup_member";
		/** 备注 [可空]       **/
		public final static String csupRemark="csup_remark";
		/** 备忘信息 [可空]       **/
		public final static String csupMemo="csup_memo";
		/** 修改时间 [非空]       **/
		public final static String csupUpdateTime="csup_update_time";
		/** 添加时间 [非空]       **/
		public final static String csupAddTime="csup_add_time";
		/** 状态 [非空]       **/
		public final static String csupStatus="csup_status";
		/** 标识 [非空]       **/
		public final static String csupFlag="csup_flag";
		
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
				$.Set(name,CsUnitPerson.getCsUnitPerson(params));
			else
				$.Set(name,CsUnitPerson.getCsUnitPersonList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取用车人员数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsUnitPerson) $.GetRequest("CsUnitPerson$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsUnitPerson.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsUnitPerson.getCsUnitPerson(params);
			else
				value = CsUnitPerson.getCsUnitPersonList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsUnitPerson.Get($.add(CsUnitPerson.F.csupId,param));
		else if(!$.empty(param.toString()))
			value = CsUnitPerson.get(Long.valueOf(param.toString()));
		$.SetRequest("CsUnitPerson$"+param.hashCode(), value);
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
	public void mergeSet(CsUnitPerson old){
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