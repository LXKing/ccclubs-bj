package com.ccclubs.model;

import java.lang.reflect.Field;
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

@namespace("user/memberinfo")
public @caption("会员信息") @table("cs_member_info") class CsMemberInfo implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csmi_id")   @primary   @note("  ") Long csmiId;
	private @caption("城市") @column("csmi_host")    @relate("$csmiHost") @RelateClass(SrvHost.class)  @note("  ") Long csmiHost;
	private SrvHost $csmiHost;//关联对象[城市]
	private @caption("真实姓名") @column("csmi_name")      @note("  ") String csmiName;
	private @caption("会员帐号") @column("csmi_member_id")    @relate("$csmiMemberId") @RelateClass(CsMember.class)  @note("  ") Long csmiMemberId;
	private CsMember $csmiMemberId;//关联对象[会员帐号]
	private @caption("证件类型") @column("csmi_certify_type")      @note(" 1:身份证 2:护照 3:军官证 4:台胞证 5:港澳通行证 6:营业执照 7:公务员证 9:其它  ") Short csmiCertifyType;
	private @caption("证件号码") @column("csmi_certify_num")      @note("  ") String csmiCertifyNum;
	private @caption("身份证反面") @column("csmi_certify_image")      @note(" 人像面 ") String csmiCertifyImage;
	private @caption("驾证号码") @column("csmi_driver_num")      @note("  ") String csmiDriverNum;
	private @caption("驾证图片") @column("csmi_driver_image")      @note("  ") String csmiDriverImage;
	private @caption("性别") @column("csmi_sex")      @note(" 0:女 1:男  ") Short csmiSex;
	private @caption("生日") @column("csmi_birthday")      @note("  ") Date csmiBirthday;
	private @caption("QQ") @column("csmi_qq")      @note("  ") String csmiQq;
	private @caption("民族") @column("csmi_nation")      @note("  ") String csmiNation;
	private @caption("邮寄地址") @column("csmi_address")      @note("  ") String csmiAddress;
	private @caption("就职公司") @column("csmi_company")      @note("  ") String csmiCompany;
	private @caption("所在地") @column("csmi_place")      @note("  ") Long csmiPlace;
	private @caption("紧急联系人") @column("csmi_person")      @note("  ") String csmiPerson;
	private @caption("紧急联系号码") @column("csmi_contact")      @note("  ") String csmiContact;
	private @caption("联系人关系") @column("csmi_relation")      @note("  ") String csmiRelation;
	private @caption("修改时间") @column("csmi_update_time")      @note("  ") Date csmiUpdateTime;
	private @caption("添加时间") @column("csmi_add_time")      @note("  ") Date csmiAddTime;
	private @caption("状态") @column("csmi_status")      @note(" 1:默认 2:保留  ") Short csmiStatus;
	private @caption("身份证正面") @column("csmi_on_certify_image")      @note("国徽面") String csmiOnCertifyImage;
	
	private @caption("工作证明") @column("csmi_proof_of_employment")      @note("") String csmiProofOfEmployment;
	private @caption("部门") @column("csmi_department")      @note("") String csmiDepartment;
	private @caption("机审照片") @column("csmi_check_photo")      @note("") String csmiCheckPhoto;

	//默认构造函数
	public CsMemberInfo(){
	
	}
	
	//主键构造函数
	public CsMemberInfo(Long id){
		this.csmiId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsMemberInfo(Long csmiHost,String csmiName,Long csmiMemberId,Short csmiCertifyType,
	        String csmiCertifyNum,String csmiCertifyImage,String csmiDriverNum,
	        String csmiDriverImage,Short csmiSex,Date csmiBirthday,String csmiQq,
	        String csmiNation,String csmiAddress,String csmiCompany,Long csmiPlace,
	        String csmiPerson,String csmiContact,String csmiRelation,Date csmiUpdateTime,
	        Date csmiAddTime,Short csmiStatus,String csmiOnCertifyImage,
	        String csmiProofOfEmployment,String csmiDepartment){
		this.csmiHost=csmiHost;
		this.csmiName=csmiName;
		this.csmiMemberId=csmiMemberId;
		this.csmiCertifyType=csmiCertifyType;
		this.csmiCertifyNum=csmiCertifyNum;
		this.csmiCertifyImage=csmiCertifyImage;
		this.csmiDriverNum=csmiDriverNum;
		this.csmiDriverImage=csmiDriverImage;
		this.csmiSex=csmiSex;
		this.csmiBirthday=csmiBirthday;
		this.csmiQq=csmiQq;
		this.csmiNation=csmiNation;
		this.csmiAddress=csmiAddress;
		this.csmiCompany=csmiCompany;
		this.csmiPlace=csmiPlace;
		this.csmiPerson=csmiPerson;
		this.csmiContact=csmiContact;
		this.csmiRelation=csmiRelation;
		this.csmiUpdateTime=csmiUpdateTime;
		this.csmiAddTime=csmiAddTime;
		this.csmiStatus=csmiStatus;
		this.csmiOnCertifyImage=csmiOnCertifyImage;
		this.csmiProofOfEmployment=csmiProofOfEmployment;
		this.csmiDepartment=csmiDepartment;
		
	}
	
	//设置非空字段
	public CsMemberInfo setNotNull(Long csmiHost,Long csmiMemberId,Date csmiUpdateTime,Date csmiAddTime,Short csmiStatus){
		this.csmiHost=csmiHost;
		this.csmiMemberId=csmiMemberId;
		this.csmiUpdateTime=csmiUpdateTime;
		this.csmiAddTime=csmiAddTime;
		this.csmiStatus=csmiStatus; 
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsMemberInfo csmiId(Long csmiId){
		this.csmiId = csmiId;
		this.setSeted(F.csmiId);
		return this;
	}
	/** 城市 [非空]       **/
	public CsMemberInfo csmiHost(Long csmiHost){
		this.csmiHost = csmiHost;
		this.setSeted(F.csmiHost);
		return this;
	}
	/** 真实姓名 [非空]       **/
	public CsMemberInfo csmiName(String csmiName){
		this.csmiName = csmiName;
		this.setSeted(F.csmiName);
		return this;
	}
	/** 会员帐号 [非空]       **/
	public CsMemberInfo csmiMemberId(Long csmiMemberId){
		this.csmiMemberId = csmiMemberId;
		this.setSeted(F.csmiMemberId);
		return this;
	}
	/** 证件类型 [非空]    1:身份证 2:护照 3:军官证 4:台胞证 5:港澳通行证 6:营业执照 7:公务员证 9:其它        **/
	public CsMemberInfo csmiCertifyType(Short csmiCertifyType){
		this.csmiCertifyType = csmiCertifyType;
		this.setSeted(F.csmiCertifyType);
		return this;
	}
	/** 证件号码 [非空]       **/
	public CsMemberInfo csmiCertifyNum(String csmiCertifyNum){
		this.csmiCertifyNum = csmiCertifyNum;
		this.setSeted(F.csmiCertifyNum);
		return this;
	}
	/** 证件图片 [非空]       **/
	public CsMemberInfo csmiCertifyImage(String csmiCertifyImage){
		this.csmiCertifyImage = csmiCertifyImage;
		this.setSeted(F.csmiCertifyImage);
		return this;
	}
	/** 驾证号码 [非空]       **/
	public CsMemberInfo csmiDriverNum(String csmiDriverNum){
		this.csmiDriverNum = csmiDriverNum;
		this.setSeted(F.csmiDriverNum);
		return this;
	}
	/** 驾证图片 [非空]       **/
	public CsMemberInfo csmiDriverImage(String csmiDriverImage){
		this.csmiDriverImage = csmiDriverImage;
		this.setSeted(F.csmiDriverImage);
		return this;
	}
	/** 性别 [非空]    0:女 1:男       **/
	public CsMemberInfo csmiSex(Short csmiSex){
		this.csmiSex = csmiSex;
		this.setSeted(F.csmiSex);
		return this;
	}
	/** 生日 [非空]       **/
	public CsMemberInfo csmiBirthday(Date csmiBirthday){
		this.csmiBirthday = csmiBirthday;
		this.setSeted(F.csmiBirthday);
		return this;
	}
	/** QQ [非空]       **/
	public CsMemberInfo csmiQq(String csmiQq){
		this.csmiQq = csmiQq;
		this.setSeted(F.csmiQq);
		return this;
	}
	/** 民族 [非空]       **/
	public CsMemberInfo csmiNation(String csmiNation){
		this.csmiNation = csmiNation;
		this.setSeted(F.csmiNation);
		return this;
	}
	/** 邮寄地址 [非空]       **/
	public CsMemberInfo csmiAddress(String csmiAddress){
		this.csmiAddress = csmiAddress;
		this.setSeted(F.csmiAddress);
		return this;
	}
	/** 就职公司 [非空]       **/
	public CsMemberInfo csmiCompany(String csmiCompany){
		this.csmiCompany = csmiCompany;
		this.setSeted(F.csmiCompany);
		return this;
	}
	/** 所在地 [非空]       **/
	public CsMemberInfo csmiPlace(Long csmiPlace){
		this.csmiPlace = csmiPlace;
		this.setSeted(F.csmiPlace);
		return this;
	}
	/** 紧急联系人 [非空]       **/
	public CsMemberInfo csmiPerson(String csmiPerson){
		this.csmiPerson = csmiPerson;
		this.setSeted(F.csmiPerson);
		return this;
	}
	/** 紧急联系号码 [非空]       **/
	public CsMemberInfo csmiContact(String csmiContact){
		this.csmiContact = csmiContact;
		this.setSeted(F.csmiContact);
		return this;
	}
	/** 联系人关系 [非空]       **/
	public CsMemberInfo csmiRelation(String csmiRelation){
		this.csmiRelation = csmiRelation;
		this.setSeted(F.csmiRelation);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsMemberInfo csmiUpdateTime(Date csmiUpdateTime){
		this.csmiUpdateTime = csmiUpdateTime;
		this.setSeted(F.csmiUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsMemberInfo csmiAddTime(Date csmiAddTime){
		this.csmiAddTime = csmiAddTime;
		this.setSeted(F.csmiAddTime);
		return this;
	}
	/** 状态 [非空]  1:默认 2:保留        **/
	public CsMemberInfo csmiStatus(Short csmiStatus){
		this.csmiStatus = csmiStatus;
		this.setSeted(F.csmiStatus);
		return this;
	}
	/** 身份证正面 [非空]       **/
	public CsMemberInfo csmiOnCertifyImage(String csmiOnCertifyImage){
		this.csmiOnCertifyImage = csmiOnCertifyImage;
		this.setSeted(F.csmiOnCertifyImage);
		return this;
	}
	
	/** 工作证明 [可空]       **/
    public CsMemberInfo csmiProofOfEmployment(String csmiProofOfEmployment){
        this.csmiProofOfEmployment = csmiProofOfEmployment;
        this.setSeted(F.csmiProofOfEmployment);
        return this;
    }
    
    /** 部门 [非空]       **/
    public CsMemberInfo csmiDepartment(String csmiDepartment){
        this.csmiDepartment = csmiDepartment;
        this.setSeted(F.csmiDepartment);
        return this;
    }
	
		/** 机审照片 [可空]       **/
	public CsMemberInfo csmiCheckPhoto(String csmiCheckPhoto){
		this.csmiCheckPhoto = csmiCheckPhoto;
		this.setSeted(F.csmiCheckPhoto);
		return this;
	}
	
	//克隆对象
	public CsMemberInfo clone(){
		CsMemberInfo clone = new CsMemberInfo();
		clone.csmiId=this.csmiId;
		clone.csmiHost=this.csmiHost;
		clone.csmiName=this.csmiName;
		clone.csmiMemberId=this.csmiMemberId;
		clone.csmiCertifyType=this.csmiCertifyType;
		clone.csmiCertifyNum=this.csmiCertifyNum;
		clone.csmiCertifyImage=this.csmiCertifyImage;
		clone.csmiDriverNum=this.csmiDriverNum;
		clone.csmiDriverImage=this.csmiDriverImage;
		clone.csmiSex=this.csmiSex;
		clone.csmiBirthday=this.csmiBirthday;
		clone.csmiQq=this.csmiQq;
		clone.csmiNation=this.csmiNation;
		clone.csmiAddress=this.csmiAddress;
		clone.csmiCompany=this.csmiCompany;
		clone.csmiPlace=this.csmiPlace;
		clone.csmiPerson=this.csmiPerson;
		clone.csmiContact=this.csmiContact;
		clone.csmiRelation=this.csmiRelation;
		clone.csmiUpdateTime=this.csmiUpdateTime;
		clone.csmiAddTime=this.csmiAddTime;
		clone.csmiStatus=this.csmiStatus;
		clone.csmiNation=this.csmiNation;
		clone.csmiOnCertifyImage=this.csmiOnCertifyImage;
	    clone.csmiProofOfEmployment=this.csmiProofOfEmployment;
	    clone.csmiDepartment=this.csmiDepartment;

		
		return clone;
	}
	
	
	/**
	 * 根据ID取会员信息
	 * @param id
	 * @return
	 */
	public static @api CsMemberInfo get(Long id){		
		return getCsMemberInfoById(id);
	}
	/**
	 * 获取所有会员信息
	 * @return
	 */
	public static @api List<CsMemberInfo> list(Map params,Integer size){
		return getCsMemberInfoList(params,size);
	}
	/**
	 * 获取会员信息分页
	 * @return
	 */
	public static @api Page<CsMemberInfo> page(int page,int size,Map params){
		return getCsMemberInfoPage(page, size , params);
	}
	/**
	 * 根据查询条件取会员信息
	 * @param params
	 * @return
	 */
	public static @api CsMemberInfo Get(Map params){
		return getCsMemberInfo(params);
	}
	/**
	 * 获取会员信息数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsMemberInfoCount(params);
	}
	/**
	 * 获取会员信息数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsMemberInfoEval(eval,params);
	}
	
	/**
	 * 根据ID取会员信息
	 * @param id
	 * @return
	 */
	public static @api CsMemberInfo getCsMemberInfoById(Long id){		
		CsMemberInfo csMemberInfo = (CsMemberInfo) $.GetRequest("CsMemberInfo$"+id);
		if(csMemberInfo!=null)
			return csMemberInfo;
		com.ccclubs.service.admin.ICsMemberInfoService csMemberInfoService = $.GetSpringBean("csMemberInfoService");		
		return csMemberInfoService.getCsMemberInfoById(id);
	}
	
	
	/**
	 * 根据ID取会员信息的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsMemberInfo.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsMemberInfo csMemberInfo = get(id);
		if(csMemberInfo!=null){
			String strValue = csMemberInfo.getCsmiName$();
			if(!"CsmiName".equals("CsmiName"))
				strValue+="("+csMemberInfo.getCsmiName$()+")";
			MemCache.setValue(CsMemberInfo.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsmiName$();
		if(!"CsmiName".equals("CsmiName"))
			keyValue+="("+this.getCsmiName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有会员信息
	 * @return
	 */
	public static @api List<CsMemberInfo> getCsMemberInfoList(Map params,Integer size){
		com.ccclubs.service.admin.ICsMemberInfoService csMemberInfoService = $.GetSpringBean("csMemberInfoService");
		return csMemberInfoService.getCsMemberInfoList(params, size);
	}
	
	/**
	 * 获取会员信息分页
	 * @return
	 */
	public static @api Page<CsMemberInfo> getCsMemberInfoPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsMemberInfoService csMemberInfoService = $.GetSpringBean("csMemberInfoService");
		return csMemberInfoService.getCsMemberInfoPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取会员信息
	 * @param params
	 * @return
	 */
	public static @api CsMemberInfo getCsMemberInfo(Map params){
		com.ccclubs.service.admin.ICsMemberInfoService csMemberInfoService = $.GetSpringBean("csMemberInfoService");
		return csMemberInfoService.getCsMemberInfo(params);
	}
	
	/**
	 * 获取会员信息数
	 * @return
	 */
	public static @api Long getCsMemberInfoCount(Map params){
		com.ccclubs.service.admin.ICsMemberInfoService csMemberInfoService = $.GetSpringBean("csMemberInfoService");
		return csMemberInfoService.getCsMemberInfoCount(params);
	}
		
		
	/**
	 * 获取会员信息自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsMemberInfoEval(String eval,Map params){
		com.ccclubs.service.admin.ICsMemberInfoService csMemberInfoService = $.GetSpringBean("csMemberInfoService");
		return csMemberInfoService.getCsMemberInfoEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsMemberInfo(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsMemberInfoService csMemberInfoService = $.GetSpringBean("csMemberInfoService");
		csMemberInfoService.updateCsMemberInfoByConfirm(set, where);
	}
	
	
	/**
	 * 保存会员信息对象
	 * @param params
	 * @return
	 */
	public CsMemberInfo save(){
		com.ccclubs.service.admin.ICsMemberInfoService csMemberInfoService = $.GetSpringBean("csMemberInfoService");
		if(this.getCsmiId()!=null)
			csMemberInfoService.updateCsMemberInfo(this);
		else
			return csMemberInfoService.saveCsMemberInfo(this);
		return this;
	}
	
	
	/**
	 * 更新会员信息对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsMemberInfoService csMemberInfoService = $.GetSpringBean("csMemberInfoService");
		csMemberInfoService.updateCsMemberInfo$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsMemberInfoService csMemberInfoService = $.GetSpringBean("csMemberInfoService");
		if($.equals($.config("logic_delete"),"true"))
			csMemberInfoService.removeCsMemberInfoById(this.getCsmiId());
		else
			csMemberInfoService.deleteCsMemberInfoById(this.getCsmiId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsMemberInfoService csMemberInfoService = $.GetSpringBean("csMemberInfoService");
		return csMemberInfoService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsmiId(){
		return this.csmiId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsmiId$(){
		String strValue="";
		 strValue=$.str(this.getCsmiId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCsmiId(Long csmiId){
		this.csmiId = csmiId;
		this.setSeted(F.csmiId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空]      
	**/
	public Long getCsmiHost(){
		return this.csmiHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsmiHost$(){
		String strValue="";
		if(this.getCsmiHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsmiHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空]     
	**/
	public void setCsmiHost(Long csmiHost){
		this.csmiHost = csmiHost;
		this.setSeted(F.csmiHost);
	}
	/**
	* 获取关联对象[城市]
	**/	 			
 	public SrvHost get$csmiHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsmiHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsmiHost()!=null){
			srvHost = SrvHost.get(this.getCsmiHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsmiHost(), srvHost);
	 	return srvHost;
	}
	/*******************************真实姓名**********************************/	
	/**
	* 真实姓名 [非空]      
	**/
	public String getCsmiName(){
		return this.csmiName;
	}
	/**
	* 获取真实姓名格式化(toString)
	**/
	public String getCsmiName$(){
		String strValue="";
		 strValue=$.str(this.getCsmiName());
	 	 return strValue;
	}
	/**
	* 真实姓名 [非空]     
	**/
	public void setCsmiName(String csmiName){
		this.csmiName = csmiName;
		this.setSeted(F.csmiName);
	}
	/*******************************会员帐号**********************************/	
	/**
	* 会员帐号 [非空]      
	**/
	public Long getCsmiMemberId(){
		return this.csmiMemberId;
	}
	/**
	* 获取会员帐号格式化(toString)
	**/
	public String getCsmiMemberId$(){
		String strValue="";
		if(this.getCsmiMemberId()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsmiMemberId()));
		}			
	 	 return strValue;
	}
	/**
	* 会员帐号 [非空]     
	**/
	public void setCsmiMemberId(Long csmiMemberId){
		this.csmiMemberId = csmiMemberId;
		this.setSeted(F.csmiMemberId);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csmiMemberId(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsmiMemberId());
 		if(csMember!=null)
			return csMember;
		if(this.getCsmiMemberId()!=null){
			csMember = CsMember.get(this.getCsmiMemberId());
 		}
 		$.SetRequest("CsMember$"+this.getCsmiMemberId(), csMember);
	 	return csMember;
	}
	/*******************************证件类型**********************************/	
	/**
	* 证件类型 [非空]      
	**/
	public Short getCsmiCertifyType(){
		return this.csmiCertifyType;
	}
	/**
	* 获取证件类型格式化(toString)
	**/
	public String getCsmiCertifyType$(){
		String strValue="";
		 if($.equals($.str(this.getCsmiCertifyType()),"1"))
			strValue=$.str("身份证");		 
		 if($.equals($.str(this.getCsmiCertifyType()),"2"))
			strValue=$.str("护照");		 
		 if($.equals($.str(this.getCsmiCertifyType()),"3"))
			strValue=$.str("军官证");		 
		 if($.equals($.str(this.getCsmiCertifyType()),"4"))
			strValue=$.str("台胞证");		 
		 if($.equals($.str(this.getCsmiCertifyType()),"5"))
			strValue=$.str("港澳通行证");		 
		 if($.equals($.str(this.getCsmiCertifyType()),"6"))
			strValue=$.str("营业执照");		 
		 if($.equals($.str(this.getCsmiCertifyType()),"7"))
			strValue=$.str("公务员证");		 
		 if($.equals($.str(this.getCsmiCertifyType()),"9"))
			strValue=$.str("其它");		 
	 	 return strValue;
	}
	/**
	* 证件类型 [非空]     
	**/
	public void setCsmiCertifyType(Short csmiCertifyType){
		this.csmiCertifyType = csmiCertifyType;
		this.setSeted(F.csmiCertifyType);
	}
	/*******************************证件号码**********************************/	
	/**
	* 证件号码 [非空]      
	**/
	public String getCsmiCertifyNum(){
		return this.csmiCertifyNum;
	}
	/**
	* 获取证件号码格式化(toString)
	**/
	public String getCsmiCertifyNum$(){
		String strValue="";
		 strValue=$.str(this.getCsmiCertifyNum());
	 	 return strValue;
	}
	/**
	* 证件号码 [非空]     
	**/
	public void setCsmiCertifyNum(String csmiCertifyNum){
		this.csmiCertifyNum = csmiCertifyNum;
		this.setSeted(F.csmiCertifyNum);
	}
	/*******************************证件图片**********************************/	
	/**
	* 证件图片 [非空]      
	**/
	public String getCsmiCertifyImage(){
		return this.csmiCertifyImage;
	}
	/**
	* 获取证件图片格式化(toString)
	**/
	public String getCsmiCertifyImage$(){
		String strValue="";
		 strValue=$.str(this.getCsmiCertifyImage());
	 	 return strValue;
	}
	/**
	* 证件图片 [非空]     
	**/
	public void setCsmiCertifyImage(String csmiCertifyImage){
		this.csmiCertifyImage = csmiCertifyImage;
		this.setSeted(F.csmiCertifyImage);
	}
	/*******************************驾证号码**********************************/	
	/**
	* 驾证号码 [非空]      
	**/
	public String getCsmiDriverNum(){
		return this.csmiDriverNum;
	}
	/**
	* 获取驾证号码格式化(toString)
	**/
	public String getCsmiDriverNum$(){
		String strValue="";
		 strValue=$.str(this.getCsmiDriverNum());
	 	 return strValue;
	}
	/**
	* 驾证号码 [非空]     
	**/
	public void setCsmiDriverNum(String csmiDriverNum){
		this.csmiDriverNum = csmiDriverNum;
		this.setSeted(F.csmiDriverNum);
	}
	/*******************************驾证图片**********************************/	
	/**
	* 驾证图片 [非空]      
	**/
	public String getCsmiDriverImage(){
		return this.csmiDriverImage;
	}
	/**
	* 获取驾证图片格式化(toString)
	**/
	public String getCsmiDriverImage$(){
		String strValue="";
		 strValue=$.str(this.getCsmiDriverImage());
	 	 return strValue;
	}
	/**
	* 驾证图片 [非空]     
	**/
	public void setCsmiDriverImage(String csmiDriverImage){
		this.csmiDriverImage = csmiDriverImage;
		this.setSeted(F.csmiDriverImage);
	}
	/*******************************性别**********************************/	
	/**
	* 性别 [非空]      
	**/
	public Short getCsmiSex(){
		return this.csmiSex;
	}
	/**
	* 获取性别格式化(toString)
	**/
	public String getCsmiSex$(){
		String strValue="";
		 if($.equals($.str(this.getCsmiSex()),"0"))
			strValue=$.str("女");		 
		 if($.equals($.str(this.getCsmiSex()),"1"))
			strValue=$.str("男");		 
	 	 return strValue;
	}
	/**
	* 性别 [非空]     
	**/
	public void setCsmiSex(Short csmiSex){
		this.csmiSex = csmiSex;
		this.setSeted(F.csmiSex);
	}
	/*******************************生日**********************************/	
	/**
	* 生日 [非空]      
	**/
	public Date getCsmiBirthday(){
		return this.csmiBirthday;
	}
	/**
	* 获取生日格式化(toString)
	**/
	public String getCsmiBirthday$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmiBirthday(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 生日 [非空]     
	**/
	public void setCsmiBirthday(Date csmiBirthday){
		this.csmiBirthday = csmiBirthday;
		this.setSeted(F.csmiBirthday);
	}
	/*******************************QQ**********************************/	
	/**
	* QQ [非空]      
	**/
	public String getCsmiQq(){
		return this.csmiQq;
	}
	/**
	* 获取QQ格式化(toString)
	**/
	public String getCsmiQq$(){
		String strValue="";
		 strValue=$.str(this.getCsmiQq());
	 	 return strValue;
	}
	/**
	* QQ [非空]     
	**/
	public void setCsmiQq(String csmiQq){
		this.csmiQq = csmiQq;
		this.setSeted(F.csmiQq);
	}
	/*******************************民族**********************************/	
	/**
	* 民族 [非空]      
	**/
	public String getCsmiNation(){
		return this.csmiNation;
	}
	/**
	* 获取民族格式化(toString)
	**/
	public String getCsmiNation$(){
		String strValue="";
		 strValue=$.str(this.getCsmiNation());
	 	 return strValue;
	}
	/**
	* 民族 [非空]     
	**/
	public void setCsmiNation(String csmiNation){
		this.csmiNation = csmiNation;
		this.setSeted(F.csmiNation);
	}
	/*******************************邮寄地址**********************************/	
	/**
	* 邮寄地址 [非空]      
	**/
	public String getCsmiAddress(){
		return this.csmiAddress;
	}
	/**
	* 获取邮寄地址格式化(toString)
	**/
	public String getCsmiAddress$(){
		String strValue="";
		 strValue=$.str(this.getCsmiAddress());
	 	 return strValue;
	}
	/**
	* 邮寄地址 [非空]     
	**/
	public void setCsmiAddress(String csmiAddress){
		this.csmiAddress = csmiAddress;
		this.setSeted(F.csmiAddress);
	}
	/*******************************就职公司**********************************/	
	/**
	* 就职公司 [非空]      
	**/
	public String getCsmiCompany(){
		return this.csmiCompany;
	}
	/**
	* 获取就职公司格式化(toString)
	**/
	public String getCsmiCompany$(){
		String strValue="";
		 strValue=$.str(this.getCsmiCompany());
	 	 return strValue;
	}
	/**
	* 就职公司 [非空]     
	**/
	public void setCsmiCompany(String csmiCompany){
		this.csmiCompany = csmiCompany;
		this.setSeted(F.csmiCompany);
	}
	/*******************************所在地**********************************/	
	/**
	* 所在地 [非空]      
	**/
	public Long getCsmiPlace(){
		return this.csmiPlace;
	}
	/**
	* 获取所在地格式化(toString)
	**/
	public String getCsmiPlace$(){
		String strValue="";
		 strValue=$.str(this.getCsmiPlace());
	 	 return strValue;
	}
	/**
	* 所在地 [非空]     
	**/
	public void setCsmiPlace(Long csmiPlace){
		this.csmiPlace = csmiPlace;
		this.setSeted(F.csmiPlace);
	}
	/*******************************紧急联系人**********************************/	
	/**
	* 紧急联系人 [非空]      
	**/
	public String getCsmiPerson(){
		return this.csmiPerson;
	}
	/**
	* 获取紧急联系人格式化(toString)
	**/
	public String getCsmiPerson$(){
		String strValue="";
		 strValue=$.str(this.getCsmiPerson());
	 	 return strValue;
	}
	/**
	* 紧急联系人 [非空]     
	**/
	public void setCsmiPerson(String csmiPerson){
		this.csmiPerson = csmiPerson;
		this.setSeted(F.csmiPerson);
	}
	/*******************************紧急联系号码**********************************/	
	/**
	* 紧急联系号码 [非空]      
	**/
	public String getCsmiContact(){
		return this.csmiContact;
	}
	/**
	* 获取紧急联系号码格式化(toString)
	**/
	public String getCsmiContact$(){
		String strValue="";
		 strValue=$.str(this.getCsmiContact());
	 	 return strValue;
	}
	/**
	* 紧急联系号码 [非空]     
	**/
	public void setCsmiContact(String csmiContact){
		this.csmiContact = csmiContact;
		this.setSeted(F.csmiContact);
	}
	/*******************************联系人关系**********************************/	
	/**
	* 联系人关系 [非空]      
	**/
	public String getCsmiRelation(){
		return this.csmiRelation;
	}
	/**
	* 获取联系人关系格式化(toString)
	**/
	public String getCsmiRelation$(){
		String strValue="";
		 strValue=$.str(this.getCsmiRelation());
	 	 return strValue;
	}
	/**
	* 联系人关系 [非空]     
	**/
	public void setCsmiRelation(String csmiRelation){
		this.csmiRelation = csmiRelation;
		this.setSeted(F.csmiRelation);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsmiUpdateTime(){
		return this.csmiUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsmiUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmiUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCsmiUpdateTime(Date csmiUpdateTime){
		this.csmiUpdateTime = csmiUpdateTime;
		this.setSeted(F.csmiUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsmiAddTime(){
		return this.csmiAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsmiAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmiAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]     
	**/
	public void setCsmiAddTime(Date csmiAddTime){
		this.csmiAddTime = csmiAddTime;
		this.setSeted(F.csmiAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]      
	**/
	public Short getCsmiStatus(){
		return this.csmiStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsmiStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsmiStatus()),"1"))
			strValue=$.str("默认");		 
		 if($.equals($.str(this.getCsmiStatus()),"2"))
			strValue=$.str("保留");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]     
	**/
	public void setCsmiStatus(Short csmiStatus){
		this.csmiStatus = csmiStatus;
		this.setSeted(F.csmiStatus);
	}
	/*******************************身份证正面**********************************/	
	/**
	* 身份证正面 [非空]      
	**/
	public String getCsmiOnCertifyImage(){
		return this.csmiOnCertifyImage;
	}
	/**
	* 获取身份证正面格式化(toString)
	**/
	public String getCsmiOnCertifyImage$(){
		String strValue="";
		 strValue=$.str(this.getCsmiOnCertifyImage());
	 	 return strValue;
	}
	/**
	* 身份证正面 [非空]     
	**/
	public void setCsmiOnCertifyImage(String csmiOnCertifyImage){
		this.csmiOnCertifyImage = csmiOnCertifyImage;
		this.setSeted(F.csmiOnCertifyImage);
	}
	
	
	/*******************************工作证明**********************************/   
    /**
    * 工作证明 [非空]      
    **/
    public String getCsmiProofOfEmployment(){
        return this.csmiProofOfEmployment;
    }
    /**
    * 获取工作证明格式化(toString)
    **/
    public String getCsmiProofOfEmployment$(){
        String strValue="";
         strValue=$.str(this.getCsmiProofOfEmployment());
         return strValue;
    }
    /**
    * 工作证明 [非空]     
    **/
    public void setCsmiProofOfEmployment(String csmiProofOfEmployment){
        this.csmiProofOfEmployment = csmiProofOfEmployment;
        this.setSeted(F.csmiProofOfEmployment);
    }
    
    /*******************************部门**********************************/    
    /**
    * 部门 [非空]      
    **/
    public String getCsmiDepartment(){
        return this.csmiDepartment;
    }
    /**
    * 获取部门格式化(toString)
    **/
    public String getCsmiDepartment$(){
        String strValue="";
         strValue=$.str(this.getCsmiDepartment());
         return strValue;
    }
    /**
    * 部门 [非空]     
    **/
    public void setCsmiDepartment(String csmiDepartment){
        this.csmiDepartment = csmiDepartment;
        this.setSeted(F.csmiDepartment);
    }
	
		/*******************************机审照片**********************************/	
	/**
	* 机审照片 [可空]      
	**/
	public String getCsmiCheckPhoto(){
		return this.csmiCheckPhoto;
	}
	/**
	* 获取机审照片格式化(toString)
	**/
	public String getCsmiCheckPhoto$(){
		String strValue="";
		 strValue=$.str(this.getCsmiCheckPhoto());
	 	 return strValue;
	}
	/**
	* 机审照片 [可空]     
	**/
	public void setCsmiCheckPhoto(String csmiCheckPhoto){
		this.csmiCheckPhoto = csmiCheckPhoto;
		this.setSeted(F.csmiCheckPhoto);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsMemberInfo.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMemberInfo.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMemberInfo.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsMemberInfo.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsMemberInfo.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMemberInfo.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMemberInfo.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsMemberInfo.this);
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
		public M csmiId(Object csmiId){this.put("csmiId", csmiId);return this;};
		/** and csmi_id is null */
		public M csmiIdNull(){if(this.get("csmiIdNot")==null)this.put("csmiIdNot", "");this.put("csmiId", null);return this;};
		/** not .... */
		public M csmiIdNot(){this.put("csmiIdNot", "not");return this;};
		/** and csmi_id >= ? */
		public M csmiIdMin(Object min){this.put("csmiIdMin", min);return this;};
		/** and csmi_id <= ? */
		public M csmiIdMax(Object max){this.put("csmiIdMax", max);return this;};
		/** 城市 [非空]       **/
		public M csmiHost(Object csmiHost){this.put("csmiHost", csmiHost);return this;};
		/** and csmi_host is null */
		public M csmiHostNull(){if(this.get("csmiHostNot")==null)this.put("csmiHostNot", "");this.put("csmiHost", null);return this;};
		/** not .... */
		public M csmiHostNot(){this.put("csmiHostNot", "not");return this;};
		/** and csmi_host >= ? */
		public M csmiHostMin(Object min){this.put("csmiHostMin", min);return this;};
		/** and csmi_host <= ? */
		public M csmiHostMax(Object max){this.put("csmiHostMax", max);return this;};
		/** 真实姓名 [非空]       **/
		public M csmiName(Object csmiName){this.put("csmiName", csmiName);return this;};
		/** and csmi_name is null */
		public M csmiNameNull(){if(this.get("csmiNameNot")==null)this.put("csmiNameNot", "");this.put("csmiName", null);return this;};
		/** not .... */
		public M csmiNameNot(){this.put("csmiNameNot", "not");return this;};
		/** 会员帐号 [非空]       **/
		public M csmiMemberId(Object csmiMemberId){this.put("csmiMemberId", csmiMemberId);return this;};
		/** and csmi_member_id is null */
		public M csmiMemberIdNull(){if(this.get("csmiMemberIdNot")==null)this.put("csmiMemberIdNot", "");this.put("csmiMemberId", null);return this;};
		/** not .... */
		public M csmiMemberIdNot(){this.put("csmiMemberIdNot", "not");return this;};
		public M csmiMemberId$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csmiMemberId$on", value);
			return this;
		};	
		/** and csmi_member_id >= ? */
		public M csmiMemberIdMin(Object min){this.put("csmiMemberIdMin", min);return this;};
		/** and csmi_member_id <= ? */
		public M csmiMemberIdMax(Object max){this.put("csmiMemberIdMax", max);return this;};
		/** 证件类型 [非空]       **/
		public M csmiCertifyType(Object csmiCertifyType){this.put("csmiCertifyType", csmiCertifyType);return this;};
		/** and csmi_certify_type is null */
		public M csmiCertifyTypeNull(){if(this.get("csmiCertifyTypeNot")==null)this.put("csmiCertifyTypeNot", "");this.put("csmiCertifyType", null);return this;};
		/** not .... */
		public M csmiCertifyTypeNot(){this.put("csmiCertifyTypeNot", "not");return this;};
		/** and csmi_certify_type >= ? */
		public M csmiCertifyTypeMin(Object min){this.put("csmiCertifyTypeMin", min);return this;};
		/** and csmi_certify_type <= ? */
		public M csmiCertifyTypeMax(Object max){this.put("csmiCertifyTypeMax", max);return this;};
		/** 证件号码 [非空]       **/
		public M csmiCertifyNum(Object csmiCertifyNum){this.put("csmiCertifyNum", csmiCertifyNum);return this;};
		/** and csmi_certify_num is null */
		public M csmiCertifyNumNull(){if(this.get("csmiCertifyNumNot")==null)this.put("csmiCertifyNumNot", "");this.put("csmiCertifyNum", null);return this;};
		/** not .... */
		public M csmiCertifyNumNot(){this.put("csmiCertifyNumNot", "not");return this;};
		/** 证件图片 [非空]       **/
		public M csmiCertifyImage(Object csmiCertifyImage){this.put("csmiCertifyImage", csmiCertifyImage);return this;};
		/** and csmi_certify_image is null */
		public M csmiCertifyImageNull(){if(this.get("csmiCertifyImageNot")==null)this.put("csmiCertifyImageNot", "");this.put("csmiCertifyImage", null);return this;};
		/** not .... */
		public M csmiCertifyImageNot(){this.put("csmiCertifyImageNot", "not");return this;};
		/** 驾证号码 [非空]       **/
		public M csmiDriverNum(Object csmiDriverNum){this.put("csmiDriverNum", csmiDriverNum);return this;};
		/** and csmi_driver_num is null */
		public M csmiDriverNumNull(){if(this.get("csmiDriverNumNot")==null)this.put("csmiDriverNumNot", "");this.put("csmiDriverNum", null);return this;};
		/** not .... */
		public M csmiDriverNumNot(){this.put("csmiDriverNumNot", "not");return this;};
		/** 驾证图片 [非空]       **/
		public M csmiDriverImage(Object csmiDriverImage){this.put("csmiDriverImage", csmiDriverImage);return this;};
		/** and csmi_driver_image is null */
		public M csmiDriverImageNull(){if(this.get("csmiDriverImageNot")==null)this.put("csmiDriverImageNot", "");this.put("csmiDriverImage", null);return this;};
		/** not .... */
		public M csmiDriverImageNot(){this.put("csmiDriverImageNot", "not");return this;};
		/** 性别 [非空]       **/
		public M csmiSex(Object csmiSex){this.put("csmiSex", csmiSex);return this;};
		/** and csmi_sex is null */
		public M csmiSexNull(){if(this.get("csmiSexNot")==null)this.put("csmiSexNot", "");this.put("csmiSex", null);return this;};
		/** not .... */
		public M csmiSexNot(){this.put("csmiSexNot", "not");return this;};
		/** and csmi_sex >= ? */
		public M csmiSexMin(Object min){this.put("csmiSexMin", min);return this;};
		/** and csmi_sex <= ? */
		public M csmiSexMax(Object max){this.put("csmiSexMax", max);return this;};
		/** 生日 [非空]       **/
		public M csmiBirthday(Object csmiBirthday){this.put("csmiBirthday", csmiBirthday);return this;};
		/** and csmi_birthday is null */
		public M csmiBirthdayNull(){if(this.get("csmiBirthdayNot")==null)this.put("csmiBirthdayNot", "");this.put("csmiBirthday", null);return this;};
		/** not .... */
		public M csmiBirthdayNot(){this.put("csmiBirthdayNot", "not");return this;};
		/** and csmi_birthday >= ? */
 		public M csmiBirthdayStart(Object start){this.put("csmiBirthdayStart", start);return this;};			
 		/** and csmi_birthday <= ? */
 		public M csmiBirthdayEnd(Object end){this.put("csmiBirthdayEnd", end);return this;};
		/** QQ [非空]       **/
		public M csmiQq(Object csmiQq){this.put("csmiQq", csmiQq);return this;};
		/** and csmi_qq is null */
		public M csmiQqNull(){if(this.get("csmiQqNot")==null)this.put("csmiQqNot", "");this.put("csmiQq", null);return this;};
		/** not .... */
		public M csmiQqNot(){this.put("csmiQqNot", "not");return this;};
		/** 民族 [非空]       **/
		public M csmiNation(Object csmiNation){this.put("csmiNation", csmiNation);return this;};
		/** and csmi_nation is null */
		public M csmiNationNull(){if(this.get("csmiNationNot")==null)this.put("csmiNationNot", "");this.put("csmiNation", null);return this;};
		/** not .... */
		public M csmiNationNot(){this.put("csmiNationNot", "not");return this;};
		/** 邮寄地址 [非空]       **/
		public M csmiAddress(Object csmiAddress){this.put("csmiAddress", csmiAddress);return this;};
		/** and csmi_address is null */
		public M csmiAddressNull(){if(this.get("csmiAddressNot")==null)this.put("csmiAddressNot", "");this.put("csmiAddress", null);return this;};
		/** not .... */
		public M csmiAddressNot(){this.put("csmiAddressNot", "not");return this;};
		/** 就职公司 [非空]       **/
		public M csmiCompany(Object csmiCompany){this.put("csmiCompany", csmiCompany);return this;};
		/** and csmi_company is null */
		public M csmiCompanyNull(){if(this.get("csmiCompanyNot")==null)this.put("csmiCompanyNot", "");this.put("csmiCompany", null);return this;};
		/** not .... */
		public M csmiCompanyNot(){this.put("csmiCompanyNot", "not");return this;};
		/** 所在地 [非空]       **/
		public M csmiPlace(Object csmiPlace){this.put("csmiPlace", csmiPlace);return this;};
		/** and csmi_place is null */
		public M csmiPlaceNull(){if(this.get("csmiPlaceNot")==null)this.put("csmiPlaceNot", "");this.put("csmiPlace", null);return this;};
		/** not .... */
		public M csmiPlaceNot(){this.put("csmiPlaceNot", "not");return this;};
		/** and csmi_place >= ? */
		public M csmiPlaceMin(Object min){this.put("csmiPlaceMin", min);return this;};
		/** and csmi_place <= ? */
		public M csmiPlaceMax(Object max){this.put("csmiPlaceMax", max);return this;};
		/** 紧急联系人 [非空]       **/
		public M csmiPerson(Object csmiPerson){this.put("csmiPerson", csmiPerson);return this;};
		/** and csmi_person is null */
		public M csmiPersonNull(){if(this.get("csmiPersonNot")==null)this.put("csmiPersonNot", "");this.put("csmiPerson", null);return this;};
		/** not .... */
		public M csmiPersonNot(){this.put("csmiPersonNot", "not");return this;};
		/** 紧急联系号码 [非空]       **/
		public M csmiContact(Object csmiContact){this.put("csmiContact", csmiContact);return this;};
		/** and csmi_contact is null */
		public M csmiContactNull(){if(this.get("csmiContactNot")==null)this.put("csmiContactNot", "");this.put("csmiContact", null);return this;};
		/** not .... */
		public M csmiContactNot(){this.put("csmiContactNot", "not");return this;};
		/** 联系人关系 [非空]       **/
		public M csmiRelation(Object csmiRelation){this.put("csmiRelation", csmiRelation);return this;};
		/** and csmi_relation is null */
		public M csmiRelationNull(){if(this.get("csmiRelationNot")==null)this.put("csmiRelationNot", "");this.put("csmiRelation", null);return this;};
		/** not .... */
		public M csmiRelationNot(){this.put("csmiRelationNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csmiUpdateTime(Object csmiUpdateTime){this.put("csmiUpdateTime", csmiUpdateTime);return this;};
		/** and csmi_update_time is null */
		public M csmiUpdateTimeNull(){if(this.get("csmiUpdateTimeNot")==null)this.put("csmiUpdateTimeNot", "");this.put("csmiUpdateTime", null);return this;};
		/** not .... */
		public M csmiUpdateTimeNot(){this.put("csmiUpdateTimeNot", "not");return this;};
		/** and csmi_update_time >= ? */
 		public M csmiUpdateTimeStart(Object start){this.put("csmiUpdateTimeStart", start);return this;};			
 		/** and csmi_update_time <= ? */
 		public M csmiUpdateTimeEnd(Object end){this.put("csmiUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csmiAddTime(Object csmiAddTime){this.put("csmiAddTime", csmiAddTime);return this;};
		/** and csmi_add_time is null */
		public M csmiAddTimeNull(){if(this.get("csmiAddTimeNot")==null)this.put("csmiAddTimeNot", "");this.put("csmiAddTime", null);return this;};
		/** not .... */
		public M csmiAddTimeNot(){this.put("csmiAddTimeNot", "not");return this;};
		/** and csmi_add_time >= ? */
 		public M csmiAddTimeStart(Object start){this.put("csmiAddTimeStart", start);return this;};			
 		/** and csmi_add_time <= ? */
 		public M csmiAddTimeEnd(Object end){this.put("csmiAddTimeEnd", end);return this;};
		/** 状态 [非空]       **/
		public M csmiStatus(Object csmiStatus){this.put("csmiStatus", csmiStatus);return this;};
		/** and csmi_status is null */
		public M csmiStatusNull(){if(this.get("csmiStatusNot")==null)this.put("csmiStatusNot", "");this.put("csmiStatus", null);return this;};
		/** not .... */
		public M csmiStatusNot(){this.put("csmiStatusNot", "not");return this;};
		/** and csmi_status >= ? */
		public M csmiStatusMin(Object min){this.put("csmiStatusMin", min);return this;};
		/** and csmi_status <= ? */
		public M csmiStatusMax(Object max){this.put("csmiStatusMax", max);return this;};
		/** 身份证正面 [非空]       **/
		public M csmiOnCertifyImage(Object csmiOnCertifyImage){this.put("csmiOnCertifyImage", csmiOnCertifyImage);return this;};
		/** and csmi_on_certify_image is null */
		public M csmiOnCertifyImageNull(){if(this.get("csmiOnCertifyImageNot")==null)this.put("csmiOnCertifyImageNot", "");this.put("csmiOnCertifyImage", null);return this;};
		/** not .... */
		public M csmiOnCertifyImageNot(){this.put("csmiOnCertifyImageNot", "not");return this;};
	 	
		
		
        public M csmiProofOfEmployment(Object csmiProofOfEmployment){this.put("csmiProofOfEmployment", csmiProofOfEmployment);return this;};
        /** and  is null */
        public M csmiProofOfEmploymentNull(){if(this.get("csmiProofOfEmploymentNot")==null)this.put("csmiProofOfEmploymentNot", "");this.put("csmiProofOfEmployment", null);return this;};
        /** not .... */
        public M csmiProofOfEmploymentNot(){this.put("csmiProofOfEmploymentNot", "not");return this;};
        
        
        
        public M csmiDepartment(Object csmiDepartment){this.put("csmiDepartment", csmiDepartment);return this;};
        /** and  is null */
        public M csmiDepartmentNull(){if(this.get("csmiDepartmentNot")==null)this.put("csmiDepartmentNot", "");this.put("csmiDepartment", null);return this;};
        /** not .... */
        public M csmiDepartmentNot(){this.put("csmiDepartmentNot", "not");return this;};
		
			/** 机审照片 [可空]       **/
		public M csmiCheckPhoto(Object csmiCheckPhoto){this.put("csmiCheckPhoto", csmiCheckPhoto);return this;};
		/** and csmi_check_photo is null */
		public M csmiCheckPhotoNull(){if(this.get("csmiCheckPhotoNot")==null)this.put("csmiCheckPhotoNot", "");this.put("csmiCheckPhoto", null);return this;};
		/** not .... */
		public M csmiCheckPhotoNot(){this.put("csmiCheckPhotoNot", "not");return this;};
		public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有会员信息 **/
		public @api List<CsMemberInfo> list(Integer size){
			return getCsMemberInfoList(this,size);
		}
		/** 获取会员信息分页 **/
		public @api Page<CsMemberInfo> page(int page,int size){
			return getCsMemberInfoPage(page, size , this);
		}
		/** 根据查询条件取会员信息 **/
		public @api CsMemberInfo get(){
			return getCsMemberInfo(this);
		}
		/** 获取会员信息数 **/
		public @api Long count(){
			return getCsMemberInfoCount(this);
		}
		/** 获取会员信息表达式 **/
		public @api <T> T eval(String strEval){
			return getCsMemberInfoEval(strEval,this);
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
			updateCsMemberInfo(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csmiId="csmiId";
		/** 城市 [非空]       **/
		public final static @type(Long.class)  String csmiHost="csmiHost";
		/** 真实姓名 [非空]       **/
		public final static @type(String.class)  String csmiName="csmiName";
		/** 会员帐号 [非空]       **/
		public final static @type(Long.class)  String csmiMemberId="csmiMemberId";
		/** 证件类型 [非空]       **/
		public final static @type(Short.class)  String csmiCertifyType="csmiCertifyType";
		/** 证件号码 [非空]       **/
		public final static @type(String.class)  String csmiCertifyNum="csmiCertifyNum";
		/** 证件图片 [非空]       **/
		public final static @type(String.class)  String csmiCertifyImage="csmiCertifyImage";
		/** 驾证号码 [非空]       **/
		public final static @type(String.class)  String csmiDriverNum="csmiDriverNum";
		/** 驾证图片 [非空]       **/
		public final static @type(String.class)  String csmiDriverImage="csmiDriverImage";
		/** 性别 [非空]       **/
		public final static @type(Short.class)  String csmiSex="csmiSex";
		/** 生日 [非空]       **/
		public final static @type(Date.class)  String csmiBirthday="csmiBirthday";
		/** and csmi_birthday >= ? */
 		public final static @type(Date.class) String csmiBirthdayStart="csmiBirthdayStart";
 		/** and csmi_birthday <= ? */
 		public final static @type(Date.class) String csmiBirthdayEnd="csmiBirthdayEnd";
		/** QQ [非空]       **/
		public final static @type(String.class)  String csmiQq="csmiQq";
		/** 民族 [非空]       **/
		public final static @type(String.class)  String csmiNation="csmiNation";
		/** 邮寄地址 [非空]       **/
		public final static @type(String.class)  String csmiAddress="csmiAddress";
		/** 就职公司 [非空]       **/
		public final static @type(String.class)  String csmiCompany="csmiCompany";
		/** 所在地 [非空]       **/
		public final static @type(Long.class)  String csmiPlace="csmiPlace";
		/** 紧急联系人 [非空]       **/
		public final static @type(String.class)  String csmiPerson="csmiPerson";
		/** 紧急联系号码 [非空]       **/
		public final static @type(String.class)  String csmiContact="csmiContact";
		/** 联系人关系 [非空]       **/
		public final static @type(String.class)  String csmiRelation="csmiRelation";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csmiUpdateTime="csmiUpdateTime";
		/** and csmi_update_time >= ? */
 		public final static @type(Date.class) String csmiUpdateTimeStart="csmiUpdateTimeStart";
 		/** and csmi_update_time <= ? */
 		public final static @type(Date.class) String csmiUpdateTimeEnd="csmiUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csmiAddTime="csmiAddTime";
		/** and csmi_add_time >= ? */
 		public final static @type(Date.class) String csmiAddTimeStart="csmiAddTimeStart";
 		/** and csmi_add_time <= ? */
 		public final static @type(Date.class) String csmiAddTimeEnd="csmiAddTimeEnd";
		/** 状态 [非空]       **/
		public final static @type(Short.class)  String csmiStatus="csmiStatus";
		/** 身份证正面 [非空]       **/
		public final static @type(String.class)  String csmiOnCertifyImage="csmiOnCertifyImage";
		
		/** 工作证明 [非空]       **/
        public final static @type(String.class)  String csmiProofOfEmployment="csmiProofOfEmployment";
        
        /** 部门 [非空]       **/
        public final static @type(String.class)  String csmiDepartment="csmiDepartment";
		/** 机审照片 [可空]       **/
		public final static @type(String.class)  String csmiCheckPhoto="csmiCheckPhoto";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csmiId="csmi_id";
		/** 城市 [非空]       **/
		public final static String csmiHost="csmi_host";
		/** 真实姓名 [非空]       **/
		public final static String csmiName="csmi_name";
		/** 会员帐号 [非空]       **/
		public final static String csmiMemberId="csmi_member_id";
		/** 证件类型 [非空]       **/
		public final static String csmiCertifyType="csmi_certify_type";
		/** 证件号码 [非空]       **/
		public final static String csmiCertifyNum="csmi_certify_num";
		/** 证件图片 [非空]       **/
		public final static String csmiCertifyImage="csmi_certify_image";
		/** 驾证号码 [非空]       **/
		public final static String csmiDriverNum="csmi_driver_num";
		/** 驾证图片 [非空]       **/
		public final static String csmiDriverImage="csmi_driver_image";
		/** 性别 [非空]       **/
		public final static String csmiSex="csmi_sex";
		/** 生日 [非空]       **/
		public final static String csmiBirthday="csmi_birthday";
		/** QQ [非空]       **/
		public final static String csmiQq="csmi_qq";
		/** 民族 [非空]       **/
		public final static String csmiNation="csmi_nation";
		/** 邮寄地址 [非空]       **/
		public final static String csmiAddress="csmi_address";
		/** 就职公司 [非空]       **/
		public final static String csmiCompany="csmi_company";
		/** 所在地 [非空]       **/
		public final static String csmiPlace="csmi_place";
		/** 紧急联系人 [非空]       **/
		public final static String csmiPerson="csmi_person";
		/** 紧急联系号码 [非空]       **/
		public final static String csmiContact="csmi_contact";
		/** 联系人关系 [非空]       **/
		public final static String csmiRelation="csmi_relation";
		/** 修改时间 [非空]       **/
		public final static String csmiUpdateTime="csmi_update_time";
		/** 添加时间 [非空]       **/
		public final static String csmiAddTime="csmi_add_time";
		/** 状态 [非空]       **/
		public final static String csmiStatus="csmi_status";
		/** 身份证正面 [非空]       **/
		public final static String csmiOnCertifyImage="csmi_on_certify_image";
		
		/** 工作证明 [非空]       **/
        public final static String csmiProofOfEmployment="csmi_proof_of_employment";
        /** 部门 [非空]       **/
        public final static String csmiDepartment="csmi_department";
		/** 机审照片 [可空]       **/
		public final static String csmiCheckPhoto="csmi_check_photo";
		
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
				$.Set(name,CsMemberInfo.getCsMemberInfo(params));
			else
				$.Set(name,CsMemberInfo.getCsMemberInfoList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取会员信息数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsMemberInfo) $.GetRequest("CsMemberInfo$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsMemberInfo.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsMemberInfo.getCsMemberInfo(params);
			else
				value = CsMemberInfo.getCsMemberInfoList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsMemberInfo.Get($.add(CsMemberInfo.F.csmiId,param));
		else if(!$.empty(param.toString()))
			value = CsMemberInfo.get(Long.valueOf(param.toString()));
		$.SetRequest("CsMemberInfo$"+param.hashCode(), value);
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
	public void mergeSet(CsMemberInfo old){
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