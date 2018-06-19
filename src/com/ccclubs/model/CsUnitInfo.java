package com.ccclubs.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccclubs.dao.impl.MemCache;
import com.lazy3q.lang.RelateClass;
import com.lazy3q.lang.api;
import com.lazy3q.lang.caption;
import com.lazy3q.lang.column;
import com.lazy3q.lang.namespace;
import com.lazy3q.lang.note;
import com.lazy3q.lang.primary;
import com.lazy3q.lang.relate;
import com.lazy3q.lang.table;
import com.lazy3q.lang.type;
import com.lazy3q.util.Function;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.tags.BodyTag;
import com.lazy3q.web.util.Page;

@namespace("unit/info")
public @caption("企业信息") @table("cs_unit_info") class CsUnitInfo implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csui_id")   @primary   @note("  ") Long csuiId;
	private @caption("城市") @column("csui_host")    @relate("$csuiHost") @RelateClass(SrvHost.class)  @note("  ") Long csuiHost;
	private SrvHost $csuiHost;//关联对象[城市]
	private @caption("名称") @column("csui_name")      @note("  ") String csuiName;
	private @caption("联系人") @column("csui_person")      @note("  ") String csuiPerson;
	private @caption("企业类型") @column("csui_type")      @note(" 0:企业用车 1:企业代理 2:公务用车  ") Short csuiType;
	private @caption("详细地址") @column("csui_address")      @note("  ") String csuiAddress;
	private @caption("联系电话") @column("csui_contact")      @note("  ") String csuiContact;
	private @caption("支付帐号") @column("csui_member")    @relate("$csuiMember") @RelateClass(CsMember.class)  @note("  ") String csuiMember;
	private CsMember $csuiMember;//关联对象[支付帐号]
	private @caption("指定网点") @column("csui_outlets")    @relate("$csuiOutlets") @RelateClass(CsOutlets.class)  @note(" {csoHost}:host  请选择可用车的网点，不选表示所有网点可用") String csuiOutlets;
	private CsOutlets $csuiOutlets;//关联对象[指定网点]
	private @caption("计费策略") @column("csui_fee_type")    @relate("$csuiFeeType") @RelateClass(CsUserType.class)  @note("  ") Long csuiFeeType;
	private CsUserType $csuiFeeType;//关联对象[计费策略]
	private @caption("结算方式") @column("csui_settle_type")      @note(" 0:后结算模式 1:预付款模式  ") Short csuiSettleType;
	private @caption("修改时间") @column("csui_update_time")      @note("  ") Date csuiUpdateTime;
	private @caption("添加时间") @column("csui_add_time")      @note("  ") Date csuiAddTime;
	private @caption("备注信息") @column("csui_remark")      @note("  ") String csuiRemark;
	private @caption("状态") @column("csui_status")      @note(" 1:正常 0:无效  ") Short csuiStatus;
	private @caption("所在区域") @column("csui_area")    @relate("$csuiArea") @RelateClass(CsArea.class)  @note("") Long csuiArea;
	private CsArea $csuiArea;//关联对象[所在区域]
	
	//默认构造函数
	public CsUnitInfo(){
	
	}
	
	//主键构造函数
	public CsUnitInfo(Long id){
		this.csuiId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsUnitInfo(Long csuiHost,String csuiName,String csuiPerson,Short csuiType,String csuiAddress,String csuiContact,String csuiMember,String csuiOutlets,Long csuiFeeType,Short csuiSettleType,Date csuiUpdateTime,Date csuiAddTime,String csuiRemark,Short csuiStatus,Long csuiArea){
		this.csuiHost=csuiHost;
		this.csuiName=csuiName;
		this.csuiPerson=csuiPerson;
		this.csuiType=csuiType;
		this.csuiAddress=csuiAddress;
		this.csuiContact=csuiContact;
		this.csuiMember=csuiMember;
		this.csuiOutlets=csuiOutlets;
		this.csuiFeeType=csuiFeeType;
		this.csuiSettleType=csuiSettleType;
		this.csuiUpdateTime=csuiUpdateTime;
		this.csuiAddTime=csuiAddTime;
		this.csuiRemark=csuiRemark;
		this.csuiStatus=csuiStatus;
		this.csuiArea=csuiArea;
	}
	
	//设置非空字段
	public CsUnitInfo setNotNull(Long csuiHost,String csuiName,String csuiOutlets,Long csuiFeeType,Short csuiSettleType,Date csuiUpdateTime,Date csuiAddTime,Short csuiStatus){
		this.csuiHost=csuiHost;
		this.csuiName=csuiName;
		this.csuiOutlets=csuiOutlets;
		this.csuiFeeType=csuiFeeType;
		this.csuiSettleType=csuiSettleType;
		this.csuiUpdateTime=csuiUpdateTime;
		this.csuiAddTime=csuiAddTime;
		this.csuiStatus=csuiStatus;
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsUnitInfo csuiId(Long csuiId){
		this.csuiId = csuiId;
		this.setSeted(F.csuiId);
		return this;
	}
	/** 城市 [非空]       **/
	public CsUnitInfo csuiHost(Long csuiHost){
		this.csuiHost = csuiHost;
		this.setSeted(F.csuiHost);
		return this;
	}
	/** 名称 [非空]       **/
	public CsUnitInfo csuiName(String csuiName){
		this.csuiName = csuiName;
		this.setSeted(F.csuiName);
		return this;
	}
	/** 联系人 [非空]       **/
	public CsUnitInfo csuiPerson(String csuiPerson){
		this.csuiPerson = csuiPerson;
		this.setSeted(F.csuiPerson);
		return this;
	}
	/** 企业类型 [非空]       **/
	public CsUnitInfo csuiType(Short csuiType){
		this.csuiType = csuiType;
		this.setSeted(F.csuiType);
		return this;
	}
	/** 详细地址 [非空]       **/
	public CsUnitInfo csuiAddress(String csuiAddress){
		this.csuiAddress = csuiAddress;
		this.setSeted(F.csuiAddress);
		return this;
	}
	/** 联系电话 [非空]       **/
	public CsUnitInfo csuiContact(String csuiContact){
		this.csuiContact = csuiContact;
		this.setSeted(F.csuiContact);
		return this;
	}
	/** 支付帐号 [非空]       **/
	public CsUnitInfo csuiMember(String csuiMember){
		this.csuiMember = csuiMember;
		this.setSeted(F.csuiMember);
		return this;
	}
	/** 指定网点 [非空]       **/
	public CsUnitInfo csuiOutlets(String csuiOutlets){
		this.csuiOutlets = csuiOutlets;
		this.setSeted(F.csuiOutlets);
		return this;
	}
	/** 计费策略 [非空]       **/
	public CsUnitInfo csuiFeeType(Long csuiFeeType){
		this.csuiFeeType = csuiFeeType;
		this.setSeted(F.csuiFeeType);
		return this;
	}
	/** 结算方式 [非空]       **/
	public CsUnitInfo csuiSettleType(Short csuiSettleType){
		this.csuiSettleType = csuiSettleType;
		this.setSeted(F.csuiSettleType);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsUnitInfo csuiUpdateTime(Date csuiUpdateTime){
		this.csuiUpdateTime = csuiUpdateTime;
		this.setSeted(F.csuiUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsUnitInfo csuiAddTime(Date csuiAddTime){
		this.csuiAddTime = csuiAddTime;
		this.setSeted(F.csuiAddTime);
		return this;
	}
	/** 备注信息 [非空]       **/
	public CsUnitInfo csuiRemark(String csuiRemark){
		this.csuiRemark = csuiRemark;
		this.setSeted(F.csuiRemark);
		return this;
	}
	/** 状态 [非空]       **/
	public CsUnitInfo csuiStatus(Short csuiStatus){
		this.csuiStatus = csuiStatus;
		this.setSeted(F.csuiStatus);
		return this;
	}
	/** 所在区域 [非空]       **/
	public CsUnitInfo csuiArea(Long csuiArea){
		this.csuiArea = csuiArea;
		this.setSeted(F.csuiArea);
		return this;
	}
	
	//克隆对象
	public CsUnitInfo clone(){
		CsUnitInfo clone = new CsUnitInfo();
		clone.csuiHost=this.csuiHost;
		clone.csuiName=this.csuiName;
		clone.csuiOutlets=this.csuiOutlets;
		clone.csuiFeeType=this.csuiFeeType;
		clone.csuiSettleType=this.csuiSettleType;
		clone.csuiUpdateTime=this.csuiUpdateTime;
		clone.csuiAddTime=this.csuiAddTime;
		clone.csuiStatus=this.csuiStatus;
		clone.csuiArea=this.csuiArea;
		return clone;
	}
	
	
	/**
	 * 根据ID取企业信息
	 * @param id
	 * @return
	 */
	public static @api CsUnitInfo get(Long id){		
		return getCsUnitInfoById(id);
	}
	/**
	 * 获取所有企业信息
	 * @return
	 */
	public static @api List<CsUnitInfo> list(Map params,Integer size){
		return getCsUnitInfoList(params,size);
	}
	/**
	 * 获取企业信息分页
	 * @return
	 */
	public static @api Page<CsUnitInfo> page(int page,int size,Map params){
		return getCsUnitInfoPage(page, size , params);
	}
	/**
	 * 根据查询条件取企业信息
	 * @param params
	 * @return
	 */
	public static @api CsUnitInfo Get(Map params){
		return getCsUnitInfo(params);
	}
	/**
	 * 获取企业信息数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsUnitInfoCount(params);
	}
	/**
	 * 获取企业信息数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsUnitInfoEval(eval,params);
	}
	
	/**
	 * 根据ID取企业信息
	 * @param id
	 * @return
	 */
	public static @api CsUnitInfo getCsUnitInfoById(Long id){		
		CsUnitInfo csUnitInfo = (CsUnitInfo) $.GetRequest("CsUnitInfo$"+id);
		if(csUnitInfo!=null)
			return csUnitInfo;
		com.ccclubs.service.admin.ICsUnitInfoService csUnitInfoService = $.GetSpringBean("csUnitInfoService");		
		return csUnitInfoService.getCsUnitInfoById(id);
	}
	
	
	/**
	 * 根据ID取企业信息的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsUnitInfo.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsUnitInfo csUnitInfo = get(id);
		if(csUnitInfo!=null){
			String strValue = csUnitInfo.getCsuiName$();
			if(!"CsuiName".equals("CsuiName"))
				strValue+="("+csUnitInfo.getCsuiName$()+")";
			MemCache.setValue(CsUnitInfo.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsuiName$();
		if(!"CsuiName".equals("CsuiName"))
			keyValue+="("+this.getCsuiName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有企业信息
	 * @return
	 */
	public static @api List<CsUnitInfo> getCsUnitInfoList(Map params,Integer size){
		com.ccclubs.service.admin.ICsUnitInfoService csUnitInfoService = $.GetSpringBean("csUnitInfoService");
		return csUnitInfoService.getCsUnitInfoList(params, size);
	}
	
	/**
	 * 获取企业信息分页
	 * @return
	 */
	public static @api Page<CsUnitInfo> getCsUnitInfoPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsUnitInfoService csUnitInfoService = $.GetSpringBean("csUnitInfoService");
		return csUnitInfoService.getCsUnitInfoPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取企业信息
	 * @param params
	 * @return
	 */
	public static @api CsUnitInfo getCsUnitInfo(Map params){
		com.ccclubs.service.admin.ICsUnitInfoService csUnitInfoService = $.GetSpringBean("csUnitInfoService");
		return csUnitInfoService.getCsUnitInfo(params);
	}
	
	/**
	 * 获取企业信息数
	 * @return
	 */
	public static @api Long getCsUnitInfoCount(Map params){
		com.ccclubs.service.admin.ICsUnitInfoService csUnitInfoService = $.GetSpringBean("csUnitInfoService");
		return csUnitInfoService.getCsUnitInfoCount(params);
	}
		
		
	/**
	 * 获取企业信息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsUnitInfoEval(String eval,Map params){
		com.ccclubs.service.admin.ICsUnitInfoService csUnitInfoService = $.GetSpringBean("csUnitInfoService");
		return csUnitInfoService.getCsUnitInfoEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsUnitInfo(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsUnitInfoService csUnitInfoService = $.GetSpringBean("csUnitInfoService");
		csUnitInfoService.updateCsUnitInfoByConfirm(set, where);
	}
	
	
	/**
	 * 保存企业信息对象
	 * @param params
	 * @return
	 */
	public CsUnitInfo save(){
		com.ccclubs.service.admin.ICsUnitInfoService csUnitInfoService = $.GetSpringBean("csUnitInfoService");
		if(this.getCsuiId()!=null)
			csUnitInfoService.updateCsUnitInfo(this);
		else
			return csUnitInfoService.saveCsUnitInfo(this);
		return this;
	}
	
	
	/**
	 * 更新企业信息对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsUnitInfoService csUnitInfoService = $.GetSpringBean("csUnitInfoService");
		csUnitInfoService.updateCsUnitInfo$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsUnitInfoService csUnitInfoService = $.GetSpringBean("csUnitInfoService");
		if($.equals($.config("logic_delete"),"true"))
			csUnitInfoService.removeCsUnitInfoById(this.getCsuiId());
		else
			csUnitInfoService.deleteCsUnitInfoById(this.getCsuiId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsUnitInfoService csUnitInfoService = $.GetSpringBean("csUnitInfoService");
		return csUnitInfoService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsuiId(){
		return this.csuiId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsuiId$(){
		String strValue="";
		 strValue=$.str(this.getCsuiId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCsuiId(Long csuiId){
		this.csuiId = csuiId;
		this.setSeted(F.csuiId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空]      
	**/
	public Long getCsuiHost(){
		return this.csuiHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsuiHost$(){
		String strValue="";
		if(this.getCsuiHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsuiHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空]     
	**/
	public void setCsuiHost(Long csuiHost){
		this.csuiHost = csuiHost;
		this.setSeted(F.csuiHost);
	}
	/**
	* 获取关联对象[城市]
	**/	 			
 	public SrvHost get$csuiHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsuiHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsuiHost()!=null){
			srvHost = SrvHost.get(this.getCsuiHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsuiHost(), srvHost);
	 	return srvHost;
	}
	/*******************************名称**********************************/	
	/**
	* 名称 [非空]      
	**/
	public String getCsuiName(){
		return this.csuiName;
	}
	/**
	* 获取名称格式化(toString)
	**/
	public String getCsuiName$(){
		String strValue="";
		 strValue=$.str(this.getCsuiName());
	 	 return strValue;
	}
	/**
	* 名称 [非空]     
	**/
	public void setCsuiName(String csuiName){
		this.csuiName = csuiName;
		this.setSeted(F.csuiName);
	}
	/*******************************联系人**********************************/	
	/**
	* 联系人 [非空]      
	**/
	public String getCsuiPerson(){
		return this.csuiPerson;
	}
	/**
	* 获取联系人格式化(toString)
	**/
	public String getCsuiPerson$(){
		String strValue="";
		 strValue=$.str(this.getCsuiPerson());
	 	 return strValue;
	}
	/**
	* 联系人 [非空]     
	**/
	public void setCsuiPerson(String csuiPerson){
		this.csuiPerson = csuiPerson;
		this.setSeted(F.csuiPerson);
	}
	/*******************************企业类型**********************************/	
	/**
	* 企业类型 [非空]      
	**/
	public Short getCsuiType(){
		return this.csuiType;
	}
	/**
	* 获取企业类型格式化(toString)
	**/
	public String getCsuiType$(){
		String strValue="";
		 if($.equals($.str(this.getCsuiType()),"2"))
			 strValue=$.str("公务用车");
		 if($.equals($.str(this.getCsuiType()),"1"))
			 strValue=$.str("企业代理");
		 if($.equals($.str(this.getCsuiType()),"0"))
			 strValue=$.str("企业用车");
	 	 return strValue;
	}
	/**
	* 企业类型 [非空]     
	**/
	public void setCsuiType(Short csuiType){
		this.csuiType = csuiType;
		this.setSeted(F.csuiType);
	}
	/*******************************详细地址**********************************/	
	/**
	* 详细地址 [非空]      
	**/
	public String getCsuiAddress(){
		return this.csuiAddress;
	}
	/**
	* 获取详细地址格式化(toString)
	**/
	public String getCsuiAddress$(){
		String strValue="";
		 strValue=$.str(this.getCsuiAddress());
	 	 return strValue;
	}
	/**
	* 详细地址 [非空]     
	**/
	public void setCsuiAddress(String csuiAddress){
		this.csuiAddress = csuiAddress;
		this.setSeted(F.csuiAddress);
	}
	/*******************************联系电话**********************************/	
	/**
	* 联系电话 [非空]      
	**/
	public String getCsuiContact(){
		return this.csuiContact;
	}
	/**
	* 获取联系电话格式化(toString)
	**/
	public String getCsuiContact$(){
		String strValue="";
		 strValue=$.str(this.getCsuiContact());
	 	 return strValue;
	}
	/**
	* 联系电话 [非空]     
	**/
	public void setCsuiContact(String csuiContact){
		this.csuiContact = csuiContact;
		this.setSeted(F.csuiContact);
	}
	/*******************************支付帐号**********************************/	
	/**
	* 支付帐号 [非空]      
	**/
	public String getCsuiMember(){
		return this.csuiMember;
	}
	/**
	* 获取支付帐号格式化(toString)
	**/
	public String getCsuiMember$(){
		String strValue="";
		if(!$.empty(this.getCsuiMember())){
			List<com.ccclubs.model.CsMember> items = com.ccclubs.model.CsMember.getCsMemberList($.add("definex","csm_id in ("+$.str(this.getCsuiMember()).replace("#","")+")"),-1);
			for(com.ccclubs.model.CsMember item1:items)
				strValue+="["+$.str(item1.getCsmName())+"]";
		}
	 	 return strValue;
	}
	/**
	* 支付帐号 [非空]     
	**/
	public void setCsuiMember(String csuiMember){
		this.csuiMember = csuiMember;
		this.setSeted(F.csuiMember);
	}
	/**
	* 获取支付帐号列表
	**/ 			
 	public List<com.ccclubs.model.CsMember> get$csuiMember(){
		if(!$.empty(this.getCsuiMember())){
			return com.ccclubs.model.CsMember.getCsMemberList($.add("definex","csm_id in ("+$.str(this.getCsuiMember()).replace("#","")+")"),-1);
		}else
			return new ArrayList();
	}
	/*******************************指定网点**********************************/	
	/**
	* 指定网点 [非空]      
	**/
	public String getCsuiOutlets(){
		return this.csuiOutlets;
	}
	/**
	* 获取指定网点格式化(toString)
	**/
	public String getCsuiOutlets$(){
		String strValue="";
		if(!$.empty(this.getCsuiOutlets())){
			List<com.ccclubs.model.CsOutlets> items = com.ccclubs.model.CsOutlets.getCsOutletsList($.add("definex","cso_id in ("+$.str(this.getCsuiOutlets()).replace("#","")+")"),-1);
			for(com.ccclubs.model.CsOutlets item1:items)
				strValue+="["+$.str(item1.getCsoName())+"]";
		}			
	 	return strValue;
	}
	/**
	* 指定网点 [非空]     
	**/
	public void setCsuiOutlets(String csuiOutlets){
		this.csuiOutlets = csuiOutlets;
		this.setSeted(F.csuiOutlets);
	}
	/**
	* 获取关联对象[网点]
	**/	 			
 	public List<com.ccclubs.model.CsOutlets> get$csuiOutlets(){
		if(!$.empty(this.getCsuiOutlets())){
			return com.ccclubs.model.CsOutlets.getCsOutletsList($.add("definex","cso_id in ("+$.str(this.getCsuiOutlets()).replace("#","")+")"),-1);
		}else
			return new ArrayList();
	}
	/*******************************计费策略**********************************/	
	/**
	* 计费策略 [非空]      
	**/
	public Long getCsuiFeeType(){
		return this.csuiFeeType;
	}
	/**
	* 获取计费策略格式化(toString)
	**/
	public String getCsuiFeeType$(){
		String strValue="";
		if(this.getCsuiFeeType()!=null){
				strValue+=$.str(CsUserType.getKeyValue(this.getCsuiFeeType()));
		}			
	 	 return strValue;
	}
	/**
	* 计费策略 [非空]     
	**/
	public void setCsuiFeeType(Long csuiFeeType){
		this.csuiFeeType = csuiFeeType;
		this.setSeted(F.csuiFeeType);
	}
	/**
	* 获取关联对象[计费策略]
	**/	 			
 	public CsUserType get$csuiFeeType(){
 		com.ccclubs.model.CsUserType csUserType = (com.ccclubs.model.CsUserType) $.GetRequest("CsUserType$"+this.getCsuiFeeType());
 		if(csUserType!=null)
			return csUserType;
		if(this.getCsuiFeeType()!=null){
			csUserType = CsUserType.get(this.getCsuiFeeType());
 		}
 		$.SetRequest("CsUserType$"+this.getCsuiFeeType(), csUserType);
	 	return csUserType;
	}
	/*******************************结算方式**********************************/	
	/**
	* 结算方式 [非空]      
	**/
	public Short getCsuiSettleType(){
		return this.csuiSettleType;
	}
	/**
	* 获取结算方式格式化(toString)
	**/
	public String getCsuiSettleType$(){
		String strValue="";
		 if($.equals($.str(this.getCsuiSettleType()),"1"))
			 strValue=$.str("预付款模式");
		 if($.equals($.str(this.getCsuiSettleType()),"0"))
			 strValue=$.str("后结算模式");
	 	 return strValue;
	}
	/**
	* 结算方式 [非空]     
	**/
	public void setCsuiSettleType(Short csuiSettleType){
		this.csuiSettleType = csuiSettleType;
		this.setSeted(F.csuiSettleType);
	}
	/*******************************反向关联的价格配置列表**********************************/	
	/**
	* 获取与当前对象反向关联的价格配置列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsUnitFee> getFeeset(){
		com.ccclubs.service.admin.ICsUnitFeeService csUnitFeeService = $.GetSpringBean("csUnitFeeService");
		Map params = new HashMap();
		params.put("csufUnitInfo",this.getCsuiId());
		return csUnitFeeService.getCsUnitFeeList(params,-1);
	}
	/**
	* 获取价格配置格式化(toString)
	**/
	public String getFeeset$(){
		return "[...]";
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsuiUpdateTime(){
		return this.csuiUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsuiUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsuiUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCsuiUpdateTime(Date csuiUpdateTime){
		this.csuiUpdateTime = csuiUpdateTime;
		this.setSeted(F.csuiUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsuiAddTime(){
		return this.csuiAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsuiAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsuiAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]     
	**/
	public void setCsuiAddTime(Date csuiAddTime){
		this.csuiAddTime = csuiAddTime;
		this.setSeted(F.csuiAddTime);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息 [非空]      
	**/
	public String getCsuiRemark(){
		return this.csuiRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCsuiRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsuiRemark());
	 	 return strValue;
	}
	/**
	* 备注信息 [非空]     
	**/
	public void setCsuiRemark(String csuiRemark){
		this.csuiRemark = csuiRemark;
		this.setSeted(F.csuiRemark);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]      
	**/
	public Short getCsuiStatus(){
		return this.csuiStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsuiStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsuiStatus()),"1"))
			 strValue=$.str("正常");
		 if($.equals($.str(this.getCsuiStatus()),"0"))
			 strValue=$.str("无效");
	 	 return strValue;
	}
	/**
	* 状态 [非空]     
	**/
	public void setCsuiStatus(Short csuiStatus){
		this.csuiStatus = csuiStatus;
		this.setSeted(F.csuiStatus);
	}
	/*******************************所在区域**********************************/	
	/**
	* 所在区域 [非空]      
	**/
	public Long getCsuiArea(){
		return this.csuiArea;
	}
	/**
	* 获取所在区域格式化(toString)
	**/
	public String getCsuiArea$(){
		String strValue="";
		if(this.getCsuiArea()!=null){
				strValue+=$.str(CsArea.getKeyValue(this.getCsuiArea()));
		}			
	 	 return strValue;
	}
	/**
	* 所在区域 [非空]     
	**/
	public void setCsuiArea(Long csuiArea){
		this.csuiArea = csuiArea;
		this.setSeted(F.csuiArea);
	}
	/**
	* 获取关联对象[所在区域]
	**/	 			
 	public CsArea get$csuiArea(){
 		com.ccclubs.model.CsArea csArea = (com.ccclubs.model.CsArea) $.GetRequest("CsArea$"+this.getCsuiArea());
 		if(csArea!=null)
			return csArea;
		if(this.getCsuiArea()!=null){
			csArea = CsArea.get(this.getCsuiArea());
 		}
 		$.SetRequest("CsArea$"+this.getCsuiArea(), csArea);
	 	return csArea;
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsUnitInfo.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUnitInfo.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUnitInfo.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsUnitInfo.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsUnitInfo.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUnitInfo.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUnitInfo.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsUnitInfo.this);
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
		public M csuiId(Object csuiId){this.put("csuiId", csuiId);return this;};
		/** and csui_id is null */
		public M csuiIdNull(){if(this.get("csuiIdNot")==null)this.put("csuiIdNot", "");this.put("csuiId", null);return this;};
		/** not .... */
		public M csuiIdNot(){this.put("csuiIdNot", "not");return this;};
		/** and csui_id >= ? */
		public M csuiIdMin(Object min){this.put("csuiIdMin", min);return this;};
		/** and csui_id <= ? */
		public M csuiIdMax(Object max){this.put("csuiIdMax", max);return this;};
		/** 城市 [非空]       **/
		public M csuiHost(Object csuiHost){this.put("csuiHost", csuiHost);return this;};
		/** and csui_host is null */
		public M csuiHostNull(){if(this.get("csuiHostNot")==null)this.put("csuiHostNot", "");this.put("csuiHost", null);return this;};
		/** not .... */
		public M csuiHostNot(){this.put("csuiHostNot", "not");return this;};
		/** and csui_host >= ? */
		public M csuiHostMin(Object min){this.put("csuiHostMin", min);return this;};
		/** and csui_host <= ? */
		public M csuiHostMax(Object max){this.put("csuiHostMax", max);return this;};
		/** 名称 [非空]       **/
		public M csuiName(Object csuiName){this.put("csuiName", csuiName);return this;};
		/** and csui_name is null */
		public M csuiNameNull(){if(this.get("csuiNameNot")==null)this.put("csuiNameNot", "");this.put("csuiName", null);return this;};
		/** not .... */
		public M csuiNameNot(){this.put("csuiNameNot", "not");return this;};
		/** 联系人 [非空]       **/
		public M csuiPerson(Object csuiPerson){this.put("csuiPerson", csuiPerson);return this;};
		/** and csui_person is null */
		public M csuiPersonNull(){if(this.get("csuiPersonNot")==null)this.put("csuiPersonNot", "");this.put("csuiPerson", null);return this;};
		/** not .... */
		public M csuiPersonNot(){this.put("csuiPersonNot", "not");return this;};
		/** 企业类型 [非空]       **/
		public M csuiType(Object csuiType){this.put("csuiType", csuiType);return this;};
		/** and csui_type is null */
		public M csuiTypeNull(){if(this.get("csuiTypeNot")==null)this.put("csuiTypeNot", "");this.put("csuiType", null);return this;};
		/** not .... */
		public M csuiTypeNot(){this.put("csuiTypeNot", "not");return this;};
		/** and csui_type >= ? */
		public M csuiTypeMin(Object min){this.put("csuiTypeMin", min);return this;};
		/** and csui_type <= ? */
		public M csuiTypeMax(Object max){this.put("csuiTypeMax", max);return this;};
		/** 详细地址 [非空]       **/
		public M csuiAddress(Object csuiAddress){this.put("csuiAddress", csuiAddress);return this;};
		/** and csui_address is null */
		public M csuiAddressNull(){if(this.get("csuiAddressNot")==null)this.put("csuiAddressNot", "");this.put("csuiAddress", null);return this;};
		/** not .... */
		public M csuiAddressNot(){this.put("csuiAddressNot", "not");return this;};
		/** 联系电话 [非空]       **/
		public M csuiContact(Object csuiContact){this.put("csuiContact", csuiContact);return this;};
		/** and csui_contact is null */
		public M csuiContactNull(){if(this.get("csuiContactNot")==null)this.put("csuiContactNot", "");this.put("csuiContact", null);return this;};
		/** not .... */
		public M csuiContactNot(){this.put("csuiContactNot", "not");return this;};
		/** 支付帐号 [非空]       **/
		public M csuiMember(Object csuiMember){this.put("csuiMember", csuiMember);return this;};
		/** and csui_member is null */
		public M csuiMemberNull(){if(this.get("csuiMemberNot")==null)this.put("csuiMemberNot", "");this.put("csuiMember", null);return this;};
		/** not .... */
		public M csuiMemberNot(){this.put("csuiMemberNot", "not");return this;};
		public M csuiMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csuiMember$on", value);
			return this;
		};	
		/** 指定网点 [非空]       **/
		public M csuiOutlets(Object csuiOutlets){this.put("csuiOutlets", csuiOutlets);return this;};
		/** and csui_outlets is null */
		public M csuiOutletsNull(){if(this.get("csuiOutletsNot")==null)this.put("csuiOutletsNot", "");this.put("csuiOutlets", null);return this;};
		/** not .... */
		public M csuiOutletsNot(){this.put("csuiOutletsNot", "not");return this;};
		public M csuiOutlets$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("csuiOutlets$on", value);
			return this;
		};	
		/** 计费策略 [非空]       **/
		public M csuiFeeType(Object csuiFeeType){this.put("csuiFeeType", csuiFeeType);return this;};
		/** and csui_fee_type is null */
		public M csuiFeeTypeNull(){if(this.get("csuiFeeTypeNot")==null)this.put("csuiFeeTypeNot", "");this.put("csuiFeeType", null);return this;};
		/** not .... */
		public M csuiFeeTypeNot(){this.put("csuiFeeTypeNot", "not");return this;};
		public M csuiFeeType$on(CsUserType.M value){
			this.put("CsUserType", value);
			this.put("csuiFeeType$on", value);
			return this;
		};	
		/** and csui_fee_type >= ? */
		public M csuiFeeTypeMin(Object min){this.put("csuiFeeTypeMin", min);return this;};
		/** and csui_fee_type <= ? */
		public M csuiFeeTypeMax(Object max){this.put("csuiFeeTypeMax", max);return this;};
		/** 结算方式 [非空]       **/
		public M csuiSettleType(Object csuiSettleType){this.put("csuiSettleType", csuiSettleType);return this;};
		/** and csui_settle_type is null */
		public M csuiSettleTypeNull(){if(this.get("csuiSettleTypeNot")==null)this.put("csuiSettleTypeNot", "");this.put("csuiSettleType", null);return this;};
		/** not .... */
		public M csuiSettleTypeNot(){this.put("csuiSettleTypeNot", "not");return this;};
		/** and csui_settle_type >= ? */
		public M csuiSettleTypeMin(Object min){this.put("csuiSettleTypeMin", min);return this;};
		/** and csui_settle_type <= ? */
		public M csuiSettleTypeMax(Object max){this.put("csuiSettleTypeMax", max);return this;};
		/** 修改时间 [非空]       **/
		public M csuiUpdateTime(Object csuiUpdateTime){this.put("csuiUpdateTime", csuiUpdateTime);return this;};
		/** and csui_update_time is null */
		public M csuiUpdateTimeNull(){if(this.get("csuiUpdateTimeNot")==null)this.put("csuiUpdateTimeNot", "");this.put("csuiUpdateTime", null);return this;};
		/** not .... */
		public M csuiUpdateTimeNot(){this.put("csuiUpdateTimeNot", "not");return this;};
		/** and csui_update_time >= ? */
 		public M csuiUpdateTimeStart(Object start){this.put("csuiUpdateTimeStart", start);return this;};			
 		/** and csui_update_time <= ? */
 		public M csuiUpdateTimeEnd(Object end){this.put("csuiUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csuiAddTime(Object csuiAddTime){this.put("csuiAddTime", csuiAddTime);return this;};
		/** and csui_add_time is null */
		public M csuiAddTimeNull(){if(this.get("csuiAddTimeNot")==null)this.put("csuiAddTimeNot", "");this.put("csuiAddTime", null);return this;};
		/** not .... */
		public M csuiAddTimeNot(){this.put("csuiAddTimeNot", "not");return this;};
		/** and csui_add_time >= ? */
 		public M csuiAddTimeStart(Object start){this.put("csuiAddTimeStart", start);return this;};			
 		/** and csui_add_time <= ? */
 		public M csuiAddTimeEnd(Object end){this.put("csuiAddTimeEnd", end);return this;};
		/** 备注信息 [非空]       **/
		public M csuiRemark(Object csuiRemark){this.put("csuiRemark", csuiRemark);return this;};
		/** and csui_remark is null */
		public M csuiRemarkNull(){if(this.get("csuiRemarkNot")==null)this.put("csuiRemarkNot", "");this.put("csuiRemark", null);return this;};
		/** not .... */
		public M csuiRemarkNot(){this.put("csuiRemarkNot", "not");return this;};
		/** 状态 [非空]       **/
		public M csuiStatus(Object csuiStatus){this.put("csuiStatus", csuiStatus);return this;};
		/** and csui_status is null */
		public M csuiStatusNull(){if(this.get("csuiStatusNot")==null)this.put("csuiStatusNot", "");this.put("csuiStatus", null);return this;};
		/** not .... */
		public M csuiStatusNot(){this.put("csuiStatusNot", "not");return this;};
		/** and csui_status >= ? */
		public M csuiStatusMin(Object min){this.put("csuiStatusMin", min);return this;};
		/** and csui_status <= ? */
		public M csuiStatusMax(Object max){this.put("csuiStatusMax", max);return this;};
		/** 所在区域 [非空]       **/
		public M csuiArea(Object csuiArea){this.put("csuiArea", csuiArea);return this;};
		/** and csui_area is null */
		public M csuiAreaNull(){if(this.get("csuiAreaNot")==null)this.put("csuiAreaNot", "");this.put("csuiArea", null);return this;};
		/** not .... */
		public M csuiAreaNot(){this.put("csuiAreaNot", "not");return this;};
		public M csuiArea$on(CsArea.M value){
			this.put("CsArea", value);
			this.put("csuiArea$on", value);
			return this;
		};	
		/** and csui_area >= ? */
		public M csuiAreaMin(Object min){this.put("csuiAreaMin", min);return this;};
		/** and csui_area <= ? */
		public M csuiAreaMax(Object max){this.put("csuiAreaMax", max);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有企业信息 **/
		public @api List<CsUnitInfo> list(Integer size){
			return getCsUnitInfoList(this,size);
		}
		/** 获取企业信息分页 **/
		public @api Page<CsUnitInfo> page(int page,int size){
			return getCsUnitInfoPage(page, size , this);
		}
		/** 根据查询条件取企业信息 **/
		public @api CsUnitInfo get(){
			return getCsUnitInfo(this);
		}
		/** 获取企业信息数 **/
		public @api Long count(){
			return getCsUnitInfoCount(this);
		}
		/** 获取企业信息表达式 **/
		public @api <T> T eval(String strEval){
			return getCsUnitInfoEval(strEval,this);
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
			updateCsUnitInfo(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csuiId="csuiId";
		/** 城市 [非空]       **/
		public final static @type(Long.class)  String csuiHost="csuiHost";
		/** 名称 [非空]       **/
		public final static @type(String.class)  String csuiName="csuiName";
		/** 联系人 [非空]       **/
		public final static @type(String.class)  String csuiPerson="csuiPerson";
		/** 企业类型 [非空]       **/
		public final static @type(Short.class)  String csuiType="csuiType";
		/** 详细地址 [非空]       **/
		public final static @type(String.class)  String csuiAddress="csuiAddress";
		/** 联系电话 [非空]       **/
		public final static @type(String.class)  String csuiContact="csuiContact";
		/** 支付帐号 [非空]       **/
		public final static @type(String.class)  String csuiMember="csuiMember";
		/** 指定网点 [非空]       **/
		public final static @type(String.class)  String csuiOutlets="csuiOutlets";
		/** 计费策略 [非空]       **/
		public final static @type(Long.class)  String csuiFeeType="csuiFeeType";
		/** 结算方式 [非空]       **/
		public final static @type(Short.class)  String csuiSettleType="csuiSettleType";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csuiUpdateTime="csuiUpdateTime";
		/** and csui_update_time >= ? */
 		public final static @type(Date.class) String csuiUpdateTimeStart="csuiUpdateTimeStart";
 		/** and csui_update_time <= ? */
 		public final static @type(Date.class) String csuiUpdateTimeEnd="csuiUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csuiAddTime="csuiAddTime";
		/** and csui_add_time >= ? */
 		public final static @type(Date.class) String csuiAddTimeStart="csuiAddTimeStart";
 		/** and csui_add_time <= ? */
 		public final static @type(Date.class) String csuiAddTimeEnd="csuiAddTimeEnd";
		/** 备注信息 [非空]       **/
		public final static @type(String.class)  String csuiRemark="csuiRemark";
		/** 状态 [非空]       **/
		public final static @type(Short.class)  String csuiStatus="csuiStatus";
		/** 所在区域 [非空]       **/
		public final static @type(Long.class)  String csuiArea="csuiArea";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csuiId="csui_id";
		/** 城市 [非空]       **/
		public final static String csuiHost="csui_host";
		/** 名称 [非空]       **/
		public final static String csuiName="csui_name";
		/** 联系人 [非空]       **/
		public final static String csuiPerson="csui_person";
		/** 企业类型 [非空]       **/
		public final static String csuiType="csui_type";
		/** 详细地址 [非空]       **/
		public final static String csuiAddress="csui_address";
		/** 联系电话 [非空]       **/
		public final static String csuiContact="csui_contact";
		/** 支付帐号 [非空]       **/
		public final static String csuiMember="csui_member";
		/** 指定网点 [非空]       **/
		public final static String csuiOutlets="csui_outlets";
		/** 计费策略 [非空]       **/
		public final static String csuiFeeType="csui_fee_type";
		/** 结算方式 [非空]       **/
		public final static String csuiSettleType="csui_settle_type";
		/** 修改时间 [非空]       **/
		public final static String csuiUpdateTime="csui_update_time";
		/** 添加时间 [非空]       **/
		public final static String csuiAddTime="csui_add_time";
		/** 备注信息 [非空]       **/
		public final static String csuiRemark="csui_remark";
		/** 状态 [非空]       **/
		public final static String csuiStatus="csui_status";
		/** 所在区域 [非空]       **/
		public final static String csuiArea="csui_area";
		
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
				$.Set(name,CsUnitInfo.getCsUnitInfo(params));
			else
				$.Set(name,CsUnitInfo.getCsUnitInfoList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取企业信息数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsUnitInfo) $.GetRequest("CsUnitInfo$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsUnitInfo.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsUnitInfo.getCsUnitInfo(params);
			else
				value = CsUnitInfo.getCsUnitInfoList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsUnitInfo.Get($.add(CsUnitInfo.F.csuiId,param));
		else if(!$.empty(param.toString()))
			value = CsUnitInfo.get(Long.valueOf(param.toString()));
		$.SetRequest("CsUnitInfo$"+param.hashCode(), value);
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
	public void mergeSet(CsUnitInfo old){
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