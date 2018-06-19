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

@namespace("user/address")
public @caption("会员地址") @table("cs_address") class CsAddress implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csa_id")   @primary  @note("  ") Long csaId;// 主键 非空     
	private @caption("详细地址") @column("csa_address")    @note("  ") String csaAddress;// 非空     
	private @caption("收件人姓名") @column("csa_name")    @note("  ") String csaName;// 非空     
	private @caption("是否默认") @column("csa_default")    @note("  ") Boolean csaDefault;// 非空     
	private @caption("所属会员") @column("csa_member")    @relate("$csaMember") @RelateClass(CsMember.class)  @note("  ") Long csaMember;// 非空     
 	private CsMember $csaMember;//关联对象[会员帐号]
	private @caption("邮编") @column("csa_post_code")    @note("  ") String csaPostCode;//     
	private @caption("电话") @column("csa_contact")    @note("  ") String csaContact;//     
	private @caption("备注信息") @column("csa_remark")    @note("  ") String csaRemark;//     
	private @caption("修改时间") @column("csa_update_time")    @note("  ") Date csaUpdateTime;// 非空     
	private @caption("添加时间") @column("csa_add_time")    @note("  ") Date csaAddTime;// 非空     
	private @caption("状态") @column("csa_status")    @note(" 1:正常 0:无效  ") Short csaStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsAddress(){
	
	}
	
	//主键构造函数
	public CsAddress(Long id){
		this.csaId = id;
	}
	
	/**所有字段构造函数 CsAddress(csaAddress,csaName,csaDefault,csaMember,csaPostCode,csaContact,csaRemark,csaUpdateTime,csaAddTime,csaStatus)
	 CsAddress(
	 	$.getString("csaAddress")//详细地址 [非空]
	 	,$.getString("csaName")//收件人姓名 [非空]
	 	,$.getBoolean("csaDefault")//是否默认 [非空]
	 	,$.getLong("csaMember")//所属会员 [非空]
	 	,$.getString("csaPostCode")//邮编
	 	,$.getString("csaContact")//电话
	 	,$.getString("csaRemark")//备注信息
	 	,$.getDate("csaUpdateTime")//修改时间 [非空]
	 	,$.getDate("csaAddTime")//添加时间 [非空]
	 	,$.getShort("csaStatus")//状态 [非空]
	 )
	**/
	public CsAddress(String csaAddress,String csaName,Boolean csaDefault,Long csaMember,String csaPostCode,String csaContact,String csaRemark,Date csaUpdateTime,Date csaAddTime,Short csaStatus){
		this.csaAddress=csaAddress;
		this.csaName=csaName;
		this.csaDefault=csaDefault;
		this.csaMember=csaMember;
		this.csaPostCode=csaPostCode;
		this.csaContact=csaContact;
		this.csaRemark=csaRemark;
		this.csaUpdateTime=csaUpdateTime;
		this.csaAddTime=csaAddTime;
		this.csaStatus=csaStatus;
	}
	
	//设置非空字段
	public CsAddress setNotNull(String csaAddress,String csaName,Boolean csaDefault,Long csaMember,Date csaUpdateTime,Date csaAddTime,Short csaStatus){
		this.csaAddress=csaAddress;
		this.csaName=csaName;
		this.csaDefault=csaDefault;
		this.csaMember=csaMember;
		this.csaUpdateTime=csaUpdateTime;
		this.csaAddTime=csaAddTime;
		this.csaStatus=csaStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsAddress csaId(Long csaId){
		this.csaId = csaId;
		this.setSeted(F.csaId);
		return this;
	}
	/** 详细地址 [非空]       **/
	public CsAddress csaAddress(String csaAddress){
		this.csaAddress = csaAddress;
		this.setSeted(F.csaAddress);
		return this;
	}
	/** 收件人姓名 [非空]       **/
	public CsAddress csaName(String csaName){
		this.csaName = csaName;
		this.setSeted(F.csaName);
		return this;
	}
	/** 是否默认 [非空]       **/
	public CsAddress csaDefault(Boolean csaDefault){
		this.csaDefault = csaDefault;
		this.setSeted(F.csaDefault);
		return this;
	}
	/** 所属会员 [非空] [CsMember]      **/
	public CsAddress csaMember(Long csaMember){
		this.csaMember = csaMember;
		this.setSeted(F.csaMember);
		return this;
	}
	/** 邮编        **/
	public CsAddress csaPostCode(String csaPostCode){
		this.csaPostCode = csaPostCode;
		this.setSeted(F.csaPostCode);
		return this;
	}
	/** 电话        **/
	public CsAddress csaContact(String csaContact){
		this.csaContact = csaContact;
		this.setSeted(F.csaContact);
		return this;
	}
	/** 备注信息        **/
	public CsAddress csaRemark(String csaRemark){
		this.csaRemark = csaRemark;
		this.setSeted(F.csaRemark);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsAddress csaUpdateTime(Date csaUpdateTime){
		this.csaUpdateTime = csaUpdateTime;
		this.setSeted(F.csaUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsAddress csaAddTime(Date csaAddTime){
		this.csaAddTime = csaAddTime;
		this.setSeted(F.csaAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsAddress csaStatus(Short csaStatus){
		this.csaStatus = csaStatus;
		this.setSeted(F.csaStatus);
		return this;
	}
	
	
	//克隆对象
	public CsAddress clone(){
		CsAddress clone = new CsAddress();
		clone.csaAddress=this.csaAddress;
		clone.csaName=this.csaName;
		clone.csaDefault=this.csaDefault;
		clone.csaMember=this.csaMember;
		clone.csaUpdateTime=this.csaUpdateTime;
		clone.csaAddTime=this.csaAddTime;
		clone.csaStatus=this.csaStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取会员地址
	 * @param id
	 * @return
	 */
	public static @api CsAddress get(Long id){		
		return getCsAddressById(id);
	}
	/**
	 * 获取所有会员地址
	 * @return
	 */
	public static @api List<CsAddress> list(Map params,Integer size){
		return getCsAddressList(params,size);
	}
	/**
	 * 获取会员地址分页
	 * @return
	 */
	public static @api Page<CsAddress> page(int page,int size,Map params){
		return getCsAddressPage(page, size , params);
	}
	/**
	 * 根据查询条件取会员地址
	 * @param params
	 * @return
	 */
	public static @api CsAddress Get(Map params){
		return getCsAddress(params);
	}
	/**
	 * 获取会员地址数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsAddressCount(params);
	}
	/**
	 * 获取会员地址数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsAddressEval(eval,params);
	}
	
	/**
	 * 根据ID取会员地址
	 * @param id
	 * @return
	 */
	public static @api CsAddress getCsAddressById(Long id){		
		CsAddress csAddress = (CsAddress) $.GetRequest("CsAddress$"+id);
		if(csAddress!=null)
			return csAddress;
		com.ccclubs.service.admin.ICsAddressService csAddressService = $.GetSpringBean("csAddressService");		
		return csAddressService.getCsAddressById(id);
	}
	
	
	/**
	 * 根据ID取会员地址的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsAddress.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsAddress csAddress = get(id);
		if(csAddress!=null){
			String strValue = csAddress.getCsaAddress$();
			if(!"CsaAddress".equals("CsaAddress"))
				strValue+="("+csAddress.getCsaAddress$()+")";
			MemCache.setValue(CsAddress.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsaAddress$();
		if(!"CsaAddress".equals("CsaAddress"))
			keyValue+="("+this.getCsaAddress$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有会员地址
	 * @return
	 */
	public static @api List<CsAddress> getCsAddressList(Map params,Integer size){
		com.ccclubs.service.admin.ICsAddressService csAddressService = $.GetSpringBean("csAddressService");
		return csAddressService.getCsAddressList(params, size);
	}
	
	/**
	 * 获取会员地址分页
	 * @return
	 */
	public static @api Page<CsAddress> getCsAddressPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsAddressService csAddressService = $.GetSpringBean("csAddressService");
		return csAddressService.getCsAddressPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取会员地址
	 * @param params
	 * @return
	 */
	public static @api CsAddress getCsAddress(Map params){
		com.ccclubs.service.admin.ICsAddressService csAddressService = $.GetSpringBean("csAddressService");
		return csAddressService.getCsAddress(params);
	}
	
	/**
	 * 获取会员地址数
	 * @return
	 */
	public static @api Long getCsAddressCount(Map params){
		com.ccclubs.service.admin.ICsAddressService csAddressService = $.GetSpringBean("csAddressService");
		return csAddressService.getCsAddressCount(params);
	}
		
		
	/**
	 * 获取会员地址自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsAddressEval(String eval,Map params){
		com.ccclubs.service.admin.ICsAddressService csAddressService = $.GetSpringBean("csAddressService");
		return csAddressService.getCsAddressEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsAddress(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsAddressService csAddressService = $.GetSpringBean("csAddressService");
		csAddressService.updateCsAddressByConfirm(set, where);
	}
	
	
	/**
	 * 保存会员地址对象
	 * @param params
	 * @return
	 */
	public CsAddress save(){
		com.ccclubs.service.admin.ICsAddressService csAddressService = $.GetSpringBean("csAddressService");
		if(this.getCsaId()!=null)
			csAddressService.updateCsAddress(this);
		else
			return csAddressService.saveCsAddress(this);
		return this;
	}
	
	
	/**
	 * 更新会员地址对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsAddressService csAddressService = $.GetSpringBean("csAddressService");
		csAddressService.updateCsAddress$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsAddressService csAddressService = $.GetSpringBean("csAddressService");
		if($.equals($.config("logic_delete"),"true"))
			csAddressService.removeCsAddressById(this.getCsaId());
		else
			csAddressService.deleteCsAddressById(this.getCsaId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsAddressService csAddressService = $.GetSpringBean("csAddressService");
		return csAddressService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsaId(){
		return this.csaId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsaId$(){
		String strValue="";
		 strValue=$.str(this.getCsaId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsaId(Long csaId){
		this.csaId = csaId;
		this.setSeted(F.csaId);
	}
	/*******************************详细地址**********************************/	
	/**
	* 详细地址 [非空]      
	**/
	public String getCsaAddress(){
		return this.csaAddress;
	}
	/**
	* 获取详细地址格式化(toString)
	**/
	public String getCsaAddress$(){
		String strValue="";
		 strValue=$.str(this.getCsaAddress());
	 	 return strValue;
	}
	/**
	* 详细地址 [非空]      
	**/
	public void setCsaAddress(String csaAddress){
		this.csaAddress = csaAddress;
		this.setSeted(F.csaAddress);
	}
	/*******************************收件人姓名**********************************/	
	/**
	* 收件人姓名 [非空]      
	**/
	public String getCsaName(){
		return this.csaName;
	}
	/**
	* 获取收件人姓名格式化(toString)
	**/
	public String getCsaName$(){
		String strValue="";
		 strValue=$.str(this.getCsaName());
	 	 return strValue;
	}
	/**
	* 收件人姓名 [非空]      
	**/
	public void setCsaName(String csaName){
		this.csaName = csaName;
		this.setSeted(F.csaName);
	}
	/*******************************是否默认**********************************/	
	/**
	* 是否默认 [非空]      
	**/
	public Boolean getCsaDefault(){
		return this.csaDefault;
	}
	/**
	* 获取是否默认格式化(toString)
	**/
	public String getCsaDefault$(){
		String strValue="";
		 strValue=$.str((this.getCsaDefault()!=null && this.getCsaDefault())?"是":"否");					 	
	 	 return strValue;
	}
	/**
	* 是否默认 [非空]      
	**/
	public void setCsaDefault(Boolean csaDefault){
		this.csaDefault = csaDefault;
		this.setSeted(F.csaDefault);
	}
	/*******************************所属会员**********************************/	
	/**
	* 所属会员 [非空] [CsMember]     
	**/
	public Long getCsaMember(){
		return this.csaMember;
	}
	/**
	* 获取所属会员格式化(toString)
	**/
	public String getCsaMember$(){
		String strValue="";
		if(this.getCsaMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsaMember()));
		}			
	 	 return strValue;
	}
	/**
	* 所属会员 [非空] [CsMember]     
	**/
	public void setCsaMember(Long csaMember){
		this.csaMember = csaMember;
		this.setSeted(F.csaMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csaMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsaMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsaMember()!=null){
 			csMember = CsMember.get(this.getCsaMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsaMember(), csMember);
	 	return csMember;
	}
	/*******************************邮编**********************************/	
	/**
	* 邮编       
	**/
	public String getCsaPostCode(){
		return this.csaPostCode;
	}
	/**
	* 获取邮编格式化(toString)
	**/
	public String getCsaPostCode$(){
		String strValue="";
		 strValue=$.str(this.getCsaPostCode());
	 	 return strValue;
	}
	/**
	* 邮编       
	**/
	public void setCsaPostCode(String csaPostCode){
		this.csaPostCode = csaPostCode;
		this.setSeted(F.csaPostCode);
	}
	/*******************************电话**********************************/	
	/**
	* 电话       
	**/
	public String getCsaContact(){
		return this.csaContact;
	}
	/**
	* 获取电话格式化(toString)
	**/
	public String getCsaContact$(){
		String strValue="";
		 strValue=$.str(this.getCsaContact());
	 	 return strValue;
	}
	/**
	* 电话       
	**/
	public void setCsaContact(String csaContact){
		this.csaContact = csaContact;
		this.setSeted(F.csaContact);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息       
	**/
	public String getCsaRemark(){
		return this.csaRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCsaRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsaRemark());
	 	 return strValue;
	}
	/**
	* 备注信息       
	**/
	public void setCsaRemark(String csaRemark){
		this.csaRemark = csaRemark;
		this.setSeted(F.csaRemark);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsaUpdateTime(){
		return this.csaUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsaUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsaUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsaUpdateTime(Date csaUpdateTime){
		this.csaUpdateTime = csaUpdateTime;
		this.setSeted(F.csaUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsaAddTime(){
		return this.csaAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsaAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsaAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsaAddTime(Date csaAddTime){
		this.csaAddTime = csaAddTime;
		this.setSeted(F.csaAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsaStatus(){
		return this.csaStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsaStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsaStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsaStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsaStatus(Short csaStatus){
		this.csaStatus = csaStatus;
		this.setSeted(F.csaStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsAddress.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsAddress.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsAddress.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsAddress.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsAddress.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsAddress.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsAddress.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsAddress.this);
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
		public M csaId(Object csaId){this.put("csaId", csaId);return this;};
	 	/** and csa_id is null */
 		public M csaIdNull(){if(this.get("csaIdNot")==null)this.put("csaIdNot", "");this.put("csaId", null);return this;};
 		/** not .... */
 		public M csaIdNot(){this.put("csaIdNot", "not");return this;};
		/** 详细地址 [非空]       **/
		public M csaAddress(Object csaAddress){this.put("csaAddress", csaAddress);return this;};
	 	/** and csa_address is null */
 		public M csaAddressNull(){if(this.get("csaAddressNot")==null)this.put("csaAddressNot", "");this.put("csaAddress", null);return this;};
 		/** not .... */
 		public M csaAddressNot(){this.put("csaAddressNot", "not");return this;};
		/** 收件人姓名 [非空]       **/
		public M csaName(Object csaName){this.put("csaName", csaName);return this;};
	 	/** and csa_name is null */
 		public M csaNameNull(){if(this.get("csaNameNot")==null)this.put("csaNameNot", "");this.put("csaName", null);return this;};
 		/** not .... */
 		public M csaNameNot(){this.put("csaNameNot", "not");return this;};
		/** 是否默认 [非空]       **/
		public M csaDefault(Object csaDefault){this.put("csaDefault", csaDefault);return this;};
	 	/** and csa_default is null */
 		public M csaDefaultNull(){if(this.get("csaDefaultNot")==null)this.put("csaDefaultNot", "");this.put("csaDefault", null);return this;};
 		/** not .... */
 		public M csaDefaultNot(){this.put("csaDefaultNot", "not");return this;};
		/** 所属会员 [非空] [CsMember]      **/
		public M csaMember(Object csaMember){this.put("csaMember", csaMember);return this;};
	 	/** and csa_member is null */
 		public M csaMemberNull(){if(this.get("csaMemberNot")==null)this.put("csaMemberNot", "");this.put("csaMember", null);return this;};
 		/** not .... */
 		public M csaMemberNot(){this.put("csaMemberNot", "not");return this;};
		public M csaMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csaMember$on", value);
			return this;
		};	
		/** 邮编        **/
		public M csaPostCode(Object csaPostCode){this.put("csaPostCode", csaPostCode);return this;};
	 	/** and csa_post_code is null */
 		public M csaPostCodeNull(){if(this.get("csaPostCodeNot")==null)this.put("csaPostCodeNot", "");this.put("csaPostCode", null);return this;};
 		/** not .... */
 		public M csaPostCodeNot(){this.put("csaPostCodeNot", "not");return this;};
		/** 电话        **/
		public M csaContact(Object csaContact){this.put("csaContact", csaContact);return this;};
	 	/** and csa_contact is null */
 		public M csaContactNull(){if(this.get("csaContactNot")==null)this.put("csaContactNot", "");this.put("csaContact", null);return this;};
 		/** not .... */
 		public M csaContactNot(){this.put("csaContactNot", "not");return this;};
		/** 备注信息        **/
		public M csaRemark(Object csaRemark){this.put("csaRemark", csaRemark);return this;};
	 	/** and csa_remark is null */
 		public M csaRemarkNull(){if(this.get("csaRemarkNot")==null)this.put("csaRemarkNot", "");this.put("csaRemark", null);return this;};
 		/** not .... */
 		public M csaRemarkNot(){this.put("csaRemarkNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csaUpdateTime(Object csaUpdateTime){this.put("csaUpdateTime", csaUpdateTime);return this;};
	 	/** and csa_update_time is null */
 		public M csaUpdateTimeNull(){if(this.get("csaUpdateTimeNot")==null)this.put("csaUpdateTimeNot", "");this.put("csaUpdateTime", null);return this;};
 		/** not .... */
 		public M csaUpdateTimeNot(){this.put("csaUpdateTimeNot", "not");return this;};
 		/** and csa_update_time >= ? */
 		public M csaUpdateTimeStart(Object start){this.put("csaUpdateTimeStart", start);return this;};			
 		/** and csa_update_time <= ? */
 		public M csaUpdateTimeEnd(Object end){this.put("csaUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csaAddTime(Object csaAddTime){this.put("csaAddTime", csaAddTime);return this;};
	 	/** and csa_add_time is null */
 		public M csaAddTimeNull(){if(this.get("csaAddTimeNot")==null)this.put("csaAddTimeNot", "");this.put("csaAddTime", null);return this;};
 		/** not .... */
 		public M csaAddTimeNot(){this.put("csaAddTimeNot", "not");return this;};
 		/** and csa_add_time >= ? */
 		public M csaAddTimeStart(Object start){this.put("csaAddTimeStart", start);return this;};			
 		/** and csa_add_time <= ? */
 		public M csaAddTimeEnd(Object end){this.put("csaAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csaStatus(Object csaStatus){this.put("csaStatus", csaStatus);return this;};
	 	/** and csa_status is null */
 		public M csaStatusNull(){if(this.get("csaStatusNot")==null)this.put("csaStatusNot", "");this.put("csaStatus", null);return this;};
 		/** not .... */
 		public M csaStatusNot(){this.put("csaStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有会员地址 **/
		public @api List<CsAddress> list(Integer size){
			return getCsAddressList(this,size);
		}
		/** 获取会员地址分页 **/
		public @api Page<CsAddress> page(int page,int size){
			return getCsAddressPage(page, size , this);
		}
		/** 根据查询条件取会员地址 **/
		public @api CsAddress get(){
			return getCsAddress(this);
		}
		/** 获取会员地址数 **/
		public @api Long count(){
			return getCsAddressCount(this);
		}
		/** 获取会员地址表达式 **/
		public @api <T> T eval(String strEval){
			return getCsAddressEval(strEval,this);
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
			updateCsAddress(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csaId="csaId";
		/** 详细地址 [非空]       **/
		public final static @type(String.class) @like String csaAddress="csaAddress";
		/** 收件人姓名 [非空]       **/
		public final static @type(String.class) @like String csaName="csaName";
		/** 是否默认 [非空]       **/
		public final static @type(Boolean.class)  String csaDefault="csaDefault";
		/** 所属会员 [非空] [CsMember]      **/
		public final static @type(Long.class)  String csaMember="csaMember";
		/** 邮编        **/
		public final static @type(String.class) @like String csaPostCode="csaPostCode";
		/** 电话        **/
		public final static @type(String.class) @like String csaContact="csaContact";
		/** 备注信息        **/
		public final static @type(String.class) @like String csaRemark="csaRemark";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csaUpdateTime="csaUpdateTime";
	 	/** and csa_update_time >= ? */
 		public final static @type(Date.class) String csaUpdateTimeStart="csaUpdateTimeStart";
 		/** and csa_update_time <= ? */
 		public final static @type(Date.class) String csaUpdateTimeEnd="csaUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csaAddTime="csaAddTime";
	 	/** and csa_add_time >= ? */
 		public final static @type(Date.class) String csaAddTimeStart="csaAddTimeStart";
 		/** and csa_add_time <= ? */
 		public final static @type(Date.class) String csaAddTimeEnd="csaAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csaStatus="csaStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csaId="csa_id";
		/** 详细地址 [非空]       **/
		public final static String csaAddress="csa_address";
		/** 收件人姓名 [非空]       **/
		public final static String csaName="csa_name";
		/** 是否默认 [非空]       **/
		public final static String csaDefault="csa_default";
		/** 所属会员 [非空] [CsMember]      **/
		public final static String csaMember="csa_member";
		/** 邮编        **/
		public final static String csaPostCode="csa_post_code";
		/** 电话        **/
		public final static String csaContact="csa_contact";
		/** 备注信息        **/
		public final static String csaRemark="csa_remark";
		/** 修改时间 [非空]       **/
		public final static String csaUpdateTime="csa_update_time";
		/** 添加时间 [非空]       **/
		public final static String csaAddTime="csa_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csaStatus="csa_status";
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
				$.Set(name,CsAddress.getCsAddress(params));
			else
				$.Set(name,CsAddress.getCsAddressList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取会员地址数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsAddress) $.GetRequest("CsAddress$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsAddress.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsAddress.getCsAddress(params);
			else
				value = CsAddress.getCsAddressList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsAddress.Get($.add(CsAddress.F.csaId,param));
		else if(!$.empty(param.toString()))
			value = CsAddress.get(Long.valueOf(param.toString()));
		$.SetRequest("CsAddress$"+param.hashCode(), value);
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
	public void mergeSet(CsAddress old){
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