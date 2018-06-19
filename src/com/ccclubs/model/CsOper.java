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

@namespace("configurator/oper")
public @caption("运营商") @table("cs_oper") class CsOper implements java.io.Serializable
{
	private static final long serialVersionUID =          1l;
	private @caption("编号") @column("cso_id")   @primary  @note("  ") Long csoId;// 主键 非空     
	private @caption("运营商名称") @column("cso_name")    @note("  ") String csoName;// 非空     
	private @caption("公司全称") @column("cso_full_name")    @note("  ") String csoFullName;//     
	private @caption("企业标识") @column("cso_icon")    @note("  ") String csoIcon;//     
	private @caption("短信签名") @column("cso_sign")    @note("  ") String csoSign;//     
	private @caption("负责人") @column("cso_person")    @note("  ") String csoPerson;// 非空     
	private @caption("帐户资金") @column("cso_money")    @note("  ") Double csoMoney;// 非空     
	private @caption("详细地址") @column("cso_address")    @note("  ") String csoAddress;//     
	private @caption("联系信息") @column("cso_contacts")    @note(" text:name text:mobile text:email text:other  ") String csoContacts;// text:name text:mobile text:email text:other     
	private @caption("添加人") @column("cso_adder")    @relate("$csoAdder") @RelateClass(SrvUser.class)  @note("  ") Long csoAdder;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csoAdder;//关联对象[用户]
	private @caption("备注") @column("cso_remark")    @note("  ") String csoRemark;//     
	private @caption("备忘信息") @column("cso_memo")    @note(" textarea:content text:sign  ") String csoMemo;// textarea:content text:sign     
	private @caption("程序数据") @column("cso_data")    @note("  ") String csoData;//     
	private @caption("修改时间") @column("cso_update_time")    @note("  ") Date csoUpdateTime;// 非空     
	private @caption("添加时间") @column("cso_add_time")    @note("  ") Date csoAddTime;// 非空     
	private @caption("状态") @column("cso_status")    @note(" 1:正常 0:无效  ") Short csoStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsOper(){
	
	}
	
	//主键构造函数
	public CsOper(Long id){
		this.csoId = id;
	}
	
	/**所有字段构造函数 CsOper(csoName,csoFullName,csoIcon,csoSign,csoPerson,csoMoney,csoAddress,csoContacts,csoAdder,csoRemark,csoMemo,csoData,csoUpdateTime,csoAddTime,csoStatus)
	 CsOper(
	 	$.getString("csoName")//运营商名称 [非空]
	 	,$.getString("csoFullName")//公司全称
	 	,$.getString("csoIcon")//企业标识
	 	,$.getString("csoSign")//短信签名
	 	,$.getString("csoPerson")//负责人 [非空]
	 	,$.getDouble("csoMoney")//帐户资金 [非空]
	 	,$.getString("csoAddress")//详细地址
	 	,$.getString("csoContacts")//联系信息
	 	,$.getLong("csoAdder")//添加人 [非空]
	 	,$.getString("csoRemark")//备注
	 	,$.getString("csoMemo")//备忘信息
	 	,$.getString("csoData")//程序数据
	 	,$.getDate("csoUpdateTime")//修改时间 [非空]
	 	,$.getDate("csoAddTime")//添加时间 [非空]
	 	,$.getShort("csoStatus")//状态 [非空]
	 )
	**/
	public CsOper(String csoName,String csoFullName,String csoIcon,String csoSign,String csoPerson,Double csoMoney,String csoAddress,String csoContacts,Long csoAdder,String csoRemark,String csoMemo,String csoData,Date csoUpdateTime,Date csoAddTime,Short csoStatus){
		this.csoName=csoName;
		this.csoFullName=csoFullName;
		this.csoIcon=csoIcon;
		this.csoSign=csoSign;
		this.csoPerson=csoPerson;
		this.csoMoney=csoMoney;
		this.csoAddress=csoAddress;
		this.csoContacts=csoContacts;
		this.csoAdder=csoAdder;
		this.csoRemark=csoRemark;
		this.csoMemo=csoMemo;
		this.csoData=csoData;
		this.csoUpdateTime=csoUpdateTime;
		this.csoAddTime=csoAddTime;
		this.csoStatus=csoStatus;
	}
	
	//设置非空字段
	public CsOper setNotNull(String csoName,String csoPerson,Double csoMoney,Long csoAdder,Date csoUpdateTime,Date csoAddTime,Short csoStatus){
		this.csoName=csoName;
		this.csoPerson=csoPerson;
		this.csoMoney=csoMoney;
		this.csoAdder=csoAdder;
		this.csoUpdateTime=csoUpdateTime;
		this.csoAddTime=csoAddTime;
		this.csoStatus=csoStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsOper csoId(Long csoId){
		this.csoId = csoId;
		this.setSeted(F.csoId);
		return this;
	}
	/** 运营商名称 [非空]       **/
	public CsOper csoName(String csoName){
		this.csoName = csoName;
		this.setSeted(F.csoName);
		return this;
	}
	/** 公司全称        **/
	public CsOper csoFullName(String csoFullName){
		this.csoFullName = csoFullName;
		this.setSeted(F.csoFullName);
		return this;
	}
	/** 企业标识        **/
	public CsOper csoIcon(String csoIcon){
		this.csoIcon = csoIcon;
		this.setSeted(F.csoIcon);
		return this;
	}
	/** 短信签名        **/
	public CsOper csoSign(String csoSign){
		this.csoSign = csoSign;
		this.setSeted(F.csoSign);
		return this;
	}
	/** 负责人 [非空]       **/
	public CsOper csoPerson(String csoPerson){
		this.csoPerson = csoPerson;
		this.setSeted(F.csoPerson);
		return this;
	}
	/** 帐户资金 [非空]       **/
	public CsOper csoMoney(Double csoMoney){
		this.csoMoney = csoMoney;
		this.setSeted(F.csoMoney);
		return this;
	}
	/** 详细地址        **/
	public CsOper csoAddress(String csoAddress){
		this.csoAddress = csoAddress;
		this.setSeted(F.csoAddress);
		return this;
	}
	/** 联系信息    text:name text:mobile text:email text:other     **/
	public CsOper csoContacts(String csoContacts){
		this.csoContacts = csoContacts;
		this.setSeted(F.csoContacts);
		return this;
	}
	/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsOper csoAdder(Long csoAdder){
		this.csoAdder = csoAdder;
		this.setSeted(F.csoAdder);
		return this;
	}
	/** 备注        **/
	public CsOper csoRemark(String csoRemark){
		this.csoRemark = csoRemark;
		this.setSeted(F.csoRemark);
		return this;
	}
	/** 备忘信息    textarea:content text:sign     **/
	public CsOper csoMemo(String csoMemo){
		this.csoMemo = csoMemo;
		this.setSeted(F.csoMemo);
		return this;
	}
	/** 程序数据        **/
	public CsOper csoData(String csoData){
		this.csoData = csoData;
		this.setSeted(F.csoData);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsOper csoUpdateTime(Date csoUpdateTime){
		this.csoUpdateTime = csoUpdateTime;
		this.setSeted(F.csoUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsOper csoAddTime(Date csoAddTime){
		this.csoAddTime = csoAddTime;
		this.setSeted(F.csoAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsOper csoStatus(Short csoStatus){
		this.csoStatus = csoStatus;
		this.setSeted(F.csoStatus);
		return this;
	}
	
	
	//克隆对象
	public CsOper clone(){
		CsOper clone = new CsOper();
		clone.csoName=this.csoName;
		clone.csoPerson=this.csoPerson;
		clone.csoMoney=this.csoMoney;
		clone.csoAdder=this.csoAdder;
		clone.csoUpdateTime=this.csoUpdateTime;
		clone.csoAddTime=this.csoAddTime;
		clone.csoStatus=this.csoStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取运营商
	 * @param id
	 * @return
	 */
	public static @api CsOper get(Long id){		
		return getCsOperById(id);
	}
	/**
	 * 获取所有运营商
	 * @return
	 */
	public static @api List<CsOper> list(Map params,Integer size){
		return getCsOperList(params,size);
	}
	/**
	 * 获取运营商分页
	 * @return
	 */
	public static @api Page<CsOper> page(int page,int size,Map params){
		return getCsOperPage(page, size , params);
	}
	/**
	 * 根据查询条件取运营商
	 * @param params
	 * @return
	 */
	public static @api CsOper Get(Map params){
		return getCsOper(params);
	}
	/**
	 * 获取运营商数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsOperCount(params);
	}
	/**
	 * 获取运营商数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsOperEval(eval,params);
	}
	
	/**
	 * 根据ID取运营商
	 * @param id
	 * @return
	 */
	public static @api CsOper getCsOperById(Long id){		
		CsOper csOper = (CsOper) $.GetRequest("CsOper$"+id);
		if(csOper!=null)
			return csOper;
		com.ccclubs.service.admin.ICsOperService csOperService = $.GetSpringBean("csOperService");		
		return csOperService.getCsOperById(id);
	}
	
	
	/**
	 * 根据ID取运营商的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsOper.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsOper csOper = get(id);
		if(csOper!=null){
			String strValue = csOper.getCsoName$();
			if(!"CsoName".equals("CsoName"))
				strValue+="("+csOper.getCsoName$()+")";
			MemCache.setValue(CsOper.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsoName$();
		if(!"CsoName".equals("CsoName"))
			keyValue+="("+this.getCsoName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有运营商
	 * @return
	 */
	public static @api List<CsOper> getCsOperList(Map params,Integer size){
		com.ccclubs.service.admin.ICsOperService csOperService = $.GetSpringBean("csOperService");
		return csOperService.getCsOperList(params, size);
	}
	
	/**
	 * 获取运营商分页
	 * @return
	 */
	public static @api Page<CsOper> getCsOperPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsOperService csOperService = $.GetSpringBean("csOperService");
		return csOperService.getCsOperPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取运营商
	 * @param params
	 * @return
	 */
	public static @api CsOper getCsOper(Map params){
		com.ccclubs.service.admin.ICsOperService csOperService = $.GetSpringBean("csOperService");
		return csOperService.getCsOper(params);
	}
	
	/**
	 * 获取运营商数
	 * @return
	 */
	public static @api Long getCsOperCount(Map params){
		com.ccclubs.service.admin.ICsOperService csOperService = $.GetSpringBean("csOperService");
		return csOperService.getCsOperCount(params);
	}
		
		
	/**
	 * 获取运营商自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsOperEval(String eval,Map params){
		com.ccclubs.service.admin.ICsOperService csOperService = $.GetSpringBean("csOperService");
		return csOperService.getCsOperEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsOper(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsOperService csOperService = $.GetSpringBean("csOperService");
		csOperService.updateCsOperByConfirm(set, where);
	}
	
	
	/**
	 * 保存运营商对象
	 * @param params
	 * @return
	 */
	public CsOper save(){
		com.ccclubs.service.admin.ICsOperService csOperService = $.GetSpringBean("csOperService");
		if(this.getCsoId()!=null)
			csOperService.updateCsOper(this);
		else
			return csOperService.saveCsOper(this);
		return this;
	}
	
	
	/**
	 * 更新运营商对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsOperService csOperService = $.GetSpringBean("csOperService");
		csOperService.updateCsOper$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsOperService csOperService = $.GetSpringBean("csOperService");
		if($.equals($.config("logic_delete"),"true"))
			csOperService.removeCsOperById(this.getCsoId());
		else
			csOperService.deleteCsOperById(this.getCsoId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsOperService csOperService = $.GetSpringBean("csOperService");
		return csOperService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsoId(){
		return this.csoId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsoId$(){
		String strValue="";
		 strValue=$.str(this.getCsoId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsoId(Long csoId){
		this.csoId = csoId;
		this.setSeted(F.csoId);
	}
	/*******************************运营商名称**********************************/	
	/**
	* 运营商名称 [非空]      
	**/
	public String getCsoName(){
		return this.csoName;
	}
	/**
	* 获取运营商名称格式化(toString)
	**/
	public String getCsoName$(){
		String strValue="";
		 strValue=$.str(this.getCsoName());
	 	 return strValue;
	}
	/**
	* 运营商名称 [非空]      
	**/
	public void setCsoName(String csoName){
		this.csoName = csoName;
		this.setSeted(F.csoName);
	}
	/*******************************公司全称**********************************/	
	/**
	* 公司全称       
	**/
	public String getCsoFullName(){
		return this.csoFullName;
	}
	/**
	* 获取公司全称格式化(toString)
	**/
	public String getCsoFullName$(){
		String strValue="";
		 strValue=$.str(this.getCsoFullName());
	 	 return strValue;
	}
	/**
	* 公司全称       
	**/
	public void setCsoFullName(String csoFullName){
		this.csoFullName = csoFullName;
		this.setSeted(F.csoFullName);
	}
	/*******************************企业标识**********************************/	
	/**
	* 企业标识       
	**/
	public String getCsoIcon(){
		return this.csoIcon;
	}
	/**
	* 获取企业标识格式化(toString)
	**/
	public String getCsoIcon$(){
		String strValue="";
		 strValue=$.str(this.getCsoIcon());
	 	 return strValue;
	}
	/**
	* 企业标识       
	**/
	public void setCsoIcon(String csoIcon){
		this.csoIcon = csoIcon;
		this.setSeted(F.csoIcon);
	}
	/*******************************短信签名**********************************/	
	/**
	* 短信签名       
	**/
	public String getCsoSign(){
		return this.csoSign;
	}
	/**
	* 获取短信签名格式化(toString)
	**/
	public String getCsoSign$(){
		String strValue="";
		 strValue=$.str(this.getCsoSign());
	 	 return strValue;
	}
	/**
	* 短信签名       
	**/
	public void setCsoSign(String csoSign){
		this.csoSign = csoSign;
		this.setSeted(F.csoSign);
	}
	/*******************************负责人**********************************/	
	/**
	* 负责人 [非空]      
	**/
	public String getCsoPerson(){
		return this.csoPerson;
	}
	/**
	* 获取负责人格式化(toString)
	**/
	public String getCsoPerson$(){
		String strValue="";
		 strValue=$.str(this.getCsoPerson());
	 	 return strValue;
	}
	/**
	* 负责人 [非空]      
	**/
	public void setCsoPerson(String csoPerson){
		this.csoPerson = csoPerson;
		this.setSeted(F.csoPerson);
	}
	/*******************************帐户资金**********************************/	
	/**
	* 帐户资金 [非空]      
	**/
	public Double getCsoMoney(){
		return this.csoMoney;
	}
	/**
	* 获取帐户资金格式化(toString)
	**/
	public String getCsoMoney$(){
		String strValue="";
		 strValue=$.str(this.getCsoMoney());	
	 	 return strValue;
	}
	/**
	* 帐户资金 [非空]      
	**/
	public void setCsoMoney(Double csoMoney){
		this.csoMoney = csoMoney;
		this.setSeted(F.csoMoney);
	}
	/*******************************详细地址**********************************/	
	/**
	* 详细地址       
	**/
	public String getCsoAddress(){
		return this.csoAddress;
	}
	/**
	* 获取详细地址格式化(toString)
	**/
	public String getCsoAddress$(){
		String strValue="";
		 strValue=$.str(this.getCsoAddress());
	 	 return strValue;
	}
	/**
	* 详细地址       
	**/
	public void setCsoAddress(String csoAddress){
		this.csoAddress = csoAddress;
		this.setSeted(F.csoAddress);
	}
	/*******************************联系信息**********************************/	
	/**
	* 联系信息    text:name text:mobile text:email text:other    
	**/
	public String getCsoContacts(){
		return this.csoContacts;
	}
	/**
	* 获取联系信息格式化(toString)
	**/
	public String getCsoContacts$(){
		String strValue="";
		 strValue=$.str(this.getCsoContacts());
	 	 return strValue;
	}
	/**
	* 联系信息    text:name text:mobile text:email text:other    
	**/
	public void setCsoContacts(String csoContacts){
		this.csoContacts = csoContacts;
		this.setSeted(F.csoContacts);
	}
	/**
	* 联系信息对应内部类
	**/
	class CsoContacts{
		String name;//姓名
		String mobile;//手机
		String email;//邮箱
		String other;//其它信息
		public void setName(String name){
			this.name=name;
		}
		public String getName(){
			return this.name;
		}
		public void setMobile(String mobile){
			this.mobile=mobile;
		}
		public String getMobile(){
			return this.mobile;
		}
		public void setEmail(String email){
			this.email=email;
		}
		public String getEmail(){
			return this.email;
		}
		public void setOther(String other){
			this.other=other;
		}
		public String getOther(){
			return this.other;
		}
	}
	/**
	* 获取联系信息格式化列表
	**/ 			
 	public List<CsoContacts> get$csoContacts(){
 		List<CsoContacts> array = new ArrayList();
 		List<Map> list = $.eval(this.csoContacts);
 		for(Map map:list){
 			CsoContacts csoContacts = new CsoContacts();
 			csoContacts.setName($.parseString(map.get("name")));
 			csoContacts.setMobile($.parseString(map.get("mobile")));
 			csoContacts.setEmail($.parseString(map.get("email")));
 			csoContacts.setOther($.parseString(map.get("other")));
 			array.add(csoContacts);
 		}
 		return array;
 	}
	/*******************************添加人**********************************/	
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsoAdder(){
		return this.csoAdder;
	}
	/**
	* 获取添加人格式化(toString)
	**/
	public String getCsoAdder$(){
		String strValue="";
		if(this.getCsoAdder()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsoAdder()));
		}			
	 	 return strValue;
	}
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsoAdder(Long csoAdder){
		this.csoAdder = csoAdder;
		this.setSeted(F.csoAdder);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csoAdder(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsoAdder());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsoAdder()!=null){
 			srvUser = SrvUser.get(this.getCsoAdder());
 		}
 		$.SetRequest("SrvUser$"+this.getCsoAdder(), srvUser);
	 	return srvUser;
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getCsoRemark(){
		return this.csoRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCsoRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsoRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setCsoRemark(String csoRemark){
		this.csoRemark = csoRemark;
		this.setSeted(F.csoRemark);
	}
	/*******************************备忘信息**********************************/	
	/**
	* 备忘信息    textarea:content text:sign    
	**/
	public String getCsoMemo(){
		return this.csoMemo;
	}
	/**
	* 获取备忘信息格式化(toString)
	**/
	public String getCsoMemo$(){
		String strValue="";
		 strValue=$.str(this.getCsoMemo());
	 	 return strValue;
	}
	/**
	* 备忘信息    textarea:content text:sign    
	**/
	public void setCsoMemo(String csoMemo){
		this.csoMemo = csoMemo;
		this.setSeted(F.csoMemo);
	}
	/*******************************程序数据**********************************/	
	/**
	* 程序数据       
	**/
	public String getCsoData(){
		return this.csoData;
	}
	/**
	* 获取程序数据格式化(toString)
	**/
	public String getCsoData$(){
		String strValue="";
		 strValue=$.str(this.getCsoData());
	 	 return strValue;
	}
	/**
	* 程序数据       
	**/
	public void setCsoData(String csoData){
		this.csoData = csoData;
		this.setSeted(F.csoData);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsoUpdateTime(){
		return this.csoUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsoUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsoUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsoUpdateTime(Date csoUpdateTime){
		this.csoUpdateTime = csoUpdateTime;
		this.setSeted(F.csoUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsoAddTime(){
		return this.csoAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsoAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsoAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsoAddTime(Date csoAddTime){
		this.csoAddTime = csoAddTime;
		this.setSeted(F.csoAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsoStatus(){
		return this.csoStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsoStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsoStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsoStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsoStatus(Short csoStatus){
		this.csoStatus = csoStatus;
		this.setSeted(F.csoStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsOper.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsOper.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsOper.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsOper.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsOper.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsOper.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsOper.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsOper.this);
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
		public M csoId(Object csoId){this.put("csoId", csoId);return this;};
	 	/** and cso_id is null */
 		public M csoIdNull(){if(this.get("csoIdNot")==null)this.put("csoIdNot", "");this.put("csoId", null);return this;};
 		/** not .... */
 		public M csoIdNot(){this.put("csoIdNot", "not");return this;};
		/** 运营商名称 [非空]       **/
		public M csoName(Object csoName){this.put("csoName", csoName);return this;};
	 	/** and cso_name is null */
 		public M csoNameNull(){if(this.get("csoNameNot")==null)this.put("csoNameNot", "");this.put("csoName", null);return this;};
 		/** not .... */
 		public M csoNameNot(){this.put("csoNameNot", "not");return this;};
		/** 公司全称        **/
		public M csoFullName(Object csoFullName){this.put("csoFullName", csoFullName);return this;};
	 	/** and cso_full_name is null */
 		public M csoFullNameNull(){if(this.get("csoFullNameNot")==null)this.put("csoFullNameNot", "");this.put("csoFullName", null);return this;};
 		/** not .... */
 		public M csoFullNameNot(){this.put("csoFullNameNot", "not");return this;};
		/** 企业标识        **/
		public M csoIcon(Object csoIcon){this.put("csoIcon", csoIcon);return this;};
	 	/** and cso_icon is null */
 		public M csoIconNull(){if(this.get("csoIconNot")==null)this.put("csoIconNot", "");this.put("csoIcon", null);return this;};
 		/** not .... */
 		public M csoIconNot(){this.put("csoIconNot", "not");return this;};
		/** 短信签名        **/
		public M csoSign(Object csoSign){this.put("csoSign", csoSign);return this;};
	 	/** and cso_sign is null */
 		public M csoSignNull(){if(this.get("csoSignNot")==null)this.put("csoSignNot", "");this.put("csoSign", null);return this;};
 		/** not .... */
 		public M csoSignNot(){this.put("csoSignNot", "not");return this;};
		/** 负责人 [非空]       **/
		public M csoPerson(Object csoPerson){this.put("csoPerson", csoPerson);return this;};
	 	/** and cso_person is null */
 		public M csoPersonNull(){if(this.get("csoPersonNot")==null)this.put("csoPersonNot", "");this.put("csoPerson", null);return this;};
 		/** not .... */
 		public M csoPersonNot(){this.put("csoPersonNot", "not");return this;};
		/** 帐户资金 [非空]       **/
		public M csoMoney(Object csoMoney){this.put("csoMoney", csoMoney);return this;};
	 	/** and cso_money is null */
 		public M csoMoneyNull(){if(this.get("csoMoneyNot")==null)this.put("csoMoneyNot", "");this.put("csoMoney", null);return this;};
 		/** not .... */
 		public M csoMoneyNot(){this.put("csoMoneyNot", "not");return this;};
		/** and cso_money >= ? */
		public M csoMoneyMin(Object min){this.put("csoMoneyMin", min);return this;};
		/** and cso_money <= ? */
		public M csoMoneyMax(Object max){this.put("csoMoneyMax", max);return this;};
		/** 详细地址        **/
		public M csoAddress(Object csoAddress){this.put("csoAddress", csoAddress);return this;};
	 	/** and cso_address is null */
 		public M csoAddressNull(){if(this.get("csoAddressNot")==null)this.put("csoAddressNot", "");this.put("csoAddress", null);return this;};
 		/** not .... */
 		public M csoAddressNot(){this.put("csoAddressNot", "not");return this;};
		/** 联系信息    text:name text:mobile text:email text:other     **/
		public M csoContacts(Object csoContacts){this.put("csoContacts", csoContacts);return this;};
	 	/** and cso_contacts is null */
 		public M csoContactsNull(){if(this.get("csoContactsNot")==null)this.put("csoContactsNot", "");this.put("csoContacts", null);return this;};
 		/** not .... */
 		public M csoContactsNot(){this.put("csoContactsNot", "not");return this;};
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csoAdder(Object csoAdder){this.put("csoAdder", csoAdder);return this;};
	 	/** and cso_adder is null */
 		public M csoAdderNull(){if(this.get("csoAdderNot")==null)this.put("csoAdderNot", "");this.put("csoAdder", null);return this;};
 		/** not .... */
 		public M csoAdderNot(){this.put("csoAdderNot", "not");return this;};
		/** 备注        **/
		public M csoRemark(Object csoRemark){this.put("csoRemark", csoRemark);return this;};
	 	/** and cso_remark is null */
 		public M csoRemarkNull(){if(this.get("csoRemarkNot")==null)this.put("csoRemarkNot", "");this.put("csoRemark", null);return this;};
 		/** not .... */
 		public M csoRemarkNot(){this.put("csoRemarkNot", "not");return this;};
		/** 备忘信息    textarea:content text:sign     **/
		public M csoMemo(Object csoMemo){this.put("csoMemo", csoMemo);return this;};
	 	/** and cso_memo is null */
 		public M csoMemoNull(){if(this.get("csoMemoNot")==null)this.put("csoMemoNot", "");this.put("csoMemo", null);return this;};
 		/** not .... */
 		public M csoMemoNot(){this.put("csoMemoNot", "not");return this;};
		/** 程序数据        **/
		public M csoData(Object csoData){this.put("csoData", csoData);return this;};
	 	/** and cso_data is null */
 		public M csoDataNull(){if(this.get("csoDataNot")==null)this.put("csoDataNot", "");this.put("csoData", null);return this;};
 		/** not .... */
 		public M csoDataNot(){this.put("csoDataNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csoUpdateTime(Object csoUpdateTime){this.put("csoUpdateTime", csoUpdateTime);return this;};
	 	/** and cso_update_time is null */
 		public M csoUpdateTimeNull(){if(this.get("csoUpdateTimeNot")==null)this.put("csoUpdateTimeNot", "");this.put("csoUpdateTime", null);return this;};
 		/** not .... */
 		public M csoUpdateTimeNot(){this.put("csoUpdateTimeNot", "not");return this;};
 		/** and cso_update_time >= ? */
 		public M csoUpdateTimeStart(Object start){this.put("csoUpdateTimeStart", start);return this;};			
 		/** and cso_update_time <= ? */
 		public M csoUpdateTimeEnd(Object end){this.put("csoUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csoAddTime(Object csoAddTime){this.put("csoAddTime", csoAddTime);return this;};
	 	/** and cso_add_time is null */
 		public M csoAddTimeNull(){if(this.get("csoAddTimeNot")==null)this.put("csoAddTimeNot", "");this.put("csoAddTime", null);return this;};
 		/** not .... */
 		public M csoAddTimeNot(){this.put("csoAddTimeNot", "not");return this;};
 		/** and cso_add_time >= ? */
 		public M csoAddTimeStart(Object start){this.put("csoAddTimeStart", start);return this;};			
 		/** and cso_add_time <= ? */
 		public M csoAddTimeEnd(Object end){this.put("csoAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csoStatus(Object csoStatus){this.put("csoStatus", csoStatus);return this;};
	 	/** and cso_status is null */
 		public M csoStatusNull(){if(this.get("csoStatusNot")==null)this.put("csoStatusNot", "");this.put("csoStatus", null);return this;};
 		/** not .... */
 		public M csoStatusNot(){this.put("csoStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有运营商 **/
		public @api List<CsOper> list(Integer size){
			return getCsOperList(this,size);
		}
		/** 获取运营商分页 **/
		public @api Page<CsOper> page(int page,int size){
			return getCsOperPage(page, size , this);
		}
		/** 根据查询条件取运营商 **/
		public @api CsOper get(){
			return getCsOper(this);
		}
		/** 获取运营商数 **/
		public @api Long count(){
			return getCsOperCount(this);
		}
		/** 获取运营商表达式 **/
		public @api <T> T eval(String strEval){
			return getCsOperEval(strEval,this);
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
			updateCsOper(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csoId="csoId";
		/** 运营商名称 [非空]       **/
		public final static @type(String.class) @like String csoName="csoName";
		/** 公司全称        **/
		public final static @type(String.class) @like String csoFullName="csoFullName";
		/** 企业标识        **/
		public final static @type(String.class)  String csoIcon="csoIcon";
		/** 短信签名        **/
		public final static @type(String.class) @like String csoSign="csoSign";
		/** 负责人 [非空]       **/
		public final static @type(String.class) @like String csoPerson="csoPerson";
		/** 帐户资金 [非空]       **/
		public final static @type(Double.class)  String csoMoney="csoMoney";
		/** and cso_money >= ? */
		public final static @type(Double.class) String csoMoneyMin="csoMoneyMin";
		/** and cso_money <= ? */
		public final static @type(Double.class) String csoMoneyMax="csoMoneyMax";
		/** 详细地址        **/
		public final static @type(String.class) @like String csoAddress="csoAddress";
		/** 联系信息    text:name text:mobile text:email text:other     **/
		public final static @type(String.class)  String csoContacts="csoContacts";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csoAdder="csoAdder";
		/** 备注        **/
		public final static @type(String.class) @like String csoRemark="csoRemark";
		/** 备忘信息    textarea:content text:sign     **/
		public final static @type(String.class) @like String csoMemo="csoMemo";
		/** 程序数据        **/
		public final static @type(String.class) @like String csoData="csoData";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csoUpdateTime="csoUpdateTime";
	 	/** and cso_update_time >= ? */
 		public final static @type(Date.class) String csoUpdateTimeStart="csoUpdateTimeStart";
 		/** and cso_update_time <= ? */
 		public final static @type(Date.class) String csoUpdateTimeEnd="csoUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csoAddTime="csoAddTime";
	 	/** and cso_add_time >= ? */
 		public final static @type(Date.class) String csoAddTimeStart="csoAddTimeStart";
 		/** and cso_add_time <= ? */
 		public final static @type(Date.class) String csoAddTimeEnd="csoAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csoStatus="csoStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csoId="cso_id";
		/** 运营商名称 [非空]       **/
		public final static String csoName="cso_name";
		/** 公司全称        **/
		public final static String csoFullName="cso_full_name";
		/** 企业标识        **/
		public final static String csoIcon="cso_icon";
		/** 短信签名        **/
		public final static String csoSign="cso_sign";
		/** 负责人 [非空]       **/
		public final static String csoPerson="cso_person";
		/** 帐户资金 [非空]       **/
		public final static String csoMoney="cso_money";
		/** 详细地址        **/
		public final static String csoAddress="cso_address";
		/** 联系信息    text:name text:mobile text:email text:other     **/
		public final static String csoContacts="cso_contacts";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csoAdder="cso_adder";
		/** 备注        **/
		public final static String csoRemark="cso_remark";
		/** 备忘信息    textarea:content text:sign     **/
		public final static String csoMemo="cso_memo";
		/** 程序数据        **/
		public final static String csoData="cso_data";
		/** 修改时间 [非空]       **/
		public final static String csoUpdateTime="cso_update_time";
		/** 添加时间 [非空]       **/
		public final static String csoAddTime="cso_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csoStatus="cso_status";
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
				$.Set(name,CsOper.getCsOper(params));
			else
				$.Set(name,CsOper.getCsOperList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取运营商数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsOper) $.GetRequest("CsOper$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsOper.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsOper.getCsOper(params);
			else
				value = CsOper.getCsOperList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsOper.Get($.add(CsOper.F.csoId,param));
		else if(!$.empty(param.toString()))
			value = CsOper.get(Long.valueOf(param.toString()));
		$.SetRequest("CsOper$"+param.hashCode(), value);
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
	public void mergeSet(CsOper old){
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