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

@namespace("object/favaddr")
public @caption("会员常用地址") @table("cs_favorite_address") class CsFavoriteAddress implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csfa_id")   @primary  @note("  ") Long csfaId;
	private @caption("会员") @column("csfa_member")     @note("  ") Long csfaMember;
	private CsMember $csfaMember;//关联对象[会员]
	private @caption("类型") @column("csfa_type")     @note("0:一般 1:企业 2:单位") Short csfaType;
	private @caption("地址") @column("csfa_address")     @note("  ") String csfaAddress;
	private @caption("描述") @column("csfa_description")     @note("  ") String csfaDescription;
	private @caption("纬度") @column("csfa_latitude")     @note("  ") Double csfaLatitude;
	private @caption("经度") @column("csfa_longitude")     @note("  ") Double csfaLongitude;
	private @caption("状态") @column("csfa_status")     @note("0:无效 1:有效") Short csfaStatus;
	private @caption("创建时间") @column("csfa_add_time")     @note("  ") Date csfaAddTime;
	private @caption("修改时间") @column("csfa_update_time")     @note("  ") Date csfaUpdateTime;
	
	//默认构造函数
	public CsFavoriteAddress(){
	
	}
	
	//主键构造函数
	public CsFavoriteAddress(Long id){
		this.csfaId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsFavoriteAddress(Long csfaMember,Short csfaType,String csfaAddress,String csfaDescription,Double csfaLatitude,Double csfaLongitude,Short csfaStatus,Date csfaAddTime,Date csfaUpdateTime){
		this.csfaMember=csfaMember;
		this.csfaType=csfaType;
		this.csfaAddress=csfaAddress;
		this.csfaDescription=csfaDescription;
		this.csfaLatitude=csfaLatitude;
		this.csfaLongitude=csfaLongitude;
		this.csfaStatus=csfaStatus;
		this.csfaAddTime=csfaAddTime;
		this.csfaUpdateTime=csfaUpdateTime;
	}
	
	//设置非空字段
	public CsFavoriteAddress setNotNull(){
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsFavoriteAddress csfaId(Long csfaId){
		this.csfaId = csfaId;
		this.setSeted(F.csfaId);
		return this;
	}
	/** 会员 [非空]       **/
	public CsFavoriteAddress csfaMember(Long csfaMember){
		this.csfaMember = csfaMember;
		this.setSeted(F.csfaMember);
		return this;
	}
	/** 类型 [非空]       **/
	public CsFavoriteAddress csfaType(Short csfaType){
		this.csfaType = csfaType;
		this.setSeted(F.csfaType);
		return this;
	}
	/** 地址 [非空]       **/
	public CsFavoriteAddress csfaAddress(String csfaAddress){
		this.csfaAddress = csfaAddress;
		this.setSeted(F.csfaAddress);
		return this;
	}
	/** 描述 [非空]       **/
	public CsFavoriteAddress csfaDescription(String csfaDescription){
		this.csfaDescription = csfaDescription;
		this.setSeted(F.csfaDescription);
		return this;
	}
	/** 纬度 [非空]       **/
	public CsFavoriteAddress csfaLatitude(Double csfaLatitude){
		this.csfaLatitude = csfaLatitude;
		this.setSeted(F.csfaLatitude);
		return this;
	}
	/** 经度 [非空]       **/
	public CsFavoriteAddress csfaLongitude(Double csfaLongitude){
		this.csfaLongitude = csfaLongitude;
		this.setSeted(F.csfaLongitude);
		return this;
	}
	/** 状态 [非空]       **/
	public CsFavoriteAddress csfaStatus(Short csfaStatus){
		this.csfaStatus = csfaStatus;
		this.setSeted(F.csfaStatus);
		return this;
	}
	/** 创建时间 [非空]       **/
	public CsFavoriteAddress csfaAddTime(Date csfaAddTime){
		this.csfaAddTime = csfaAddTime;
		this.setSeted(F.csfaAddTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsFavoriteAddress csfaUpdateTime(Date csfaUpdateTime){
		this.csfaUpdateTime = csfaUpdateTime;
		this.setSeted(F.csfaUpdateTime);
		return this;
	}
	
	//克隆对象
	public CsFavoriteAddress clone(){
		CsFavoriteAddress clone = new CsFavoriteAddress();
		clone.csfaId=this.csfaId;
		clone.csfaMember=this.csfaMember;
		clone.csfaType=this.csfaType;
		clone.csfaAddress=this.csfaAddress;
		clone.csfaDescription=this.csfaDescription;
		clone.csfaLatitude=this.csfaLatitude;
		clone.csfaLongitude=this.csfaLongitude;
		clone.csfaStatus=this.csfaStatus;
		clone.csfaAddTime=this.csfaAddTime;
		clone.csfaUpdateTime=this.csfaUpdateTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取会员常用地址
	 * @param id
	 * @return
	 */
	public static @api CsFavoriteAddress get(Long id){		
		return getCsFavoriteAddressById(id);
	}
	/**
	 * 获取所有会员常用地址
	 * @return
	 */
	public static @api List<CsFavoriteAddress> list(Map params,Integer size){
		return getCsFavoriteAddressList(params,size);
	}
	/**
	 * 获取会员常用地址分页
	 * @return
	 */
	public static @api Page<CsFavoriteAddress> page(int page,int size,Map params){
		return getCsFavoriteAddressPage(page, size , params);
	}
	/**
	 * 根据查询条件取会员常用地址
	 * @param params
	 * @return
	 */
	public static @api CsFavoriteAddress Get(Map params){
		return getCsFavoriteAddress(params);
	}
	/**
	 * 获取会员常用地址数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsFavoriteAddressCount(params);
	}
	/**
	 * 获取会员常用地址数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsFavoriteAddressEval(eval,params);
	}
	
	/**
	 * 根据ID取会员常用地址
	 * @param id
	 * @return
	 */
	public static @api CsFavoriteAddress getCsFavoriteAddressById(Long id){		
		CsFavoriteAddress csFavoriteAddress = (CsFavoriteAddress) $.GetRequest("CsFavoriteAddress$"+id);
		if(csFavoriteAddress!=null)
			return csFavoriteAddress;
		com.ccclubs.service.admin.ICsFavoriteAddressService csFavoriteAddressService = $.GetSpringBean("csFavoriteAddressService");		
		return csFavoriteAddressService.getCsFavoriteAddressById(id);
	}
	/**
	 * 根据ID取会员常用地址的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsFavoriteAddress.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsFavoriteAddress csFavoriteAddress = get(id);
		if(csFavoriteAddress!=null){
			String strValue = csFavoriteAddress.getCsfaId$();
			if(!"CsfaId".equals("CsfaId"))
				strValue+="("+csFavoriteAddress.getCsfaId$()+")";
			MemCache.setValue(CsFavoriteAddress.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String strValue = this.getCsfaId$();
		if(!"CsfaId".equals("CsfaId"))
			strValue+="("+this.getCsfaId$()+")";
		return strValue;
	}
	
	/**
	 * 获取所有会员常用地址
	 * @return
	 */
	public static @api List<CsFavoriteAddress> getCsFavoriteAddressList(Map params,Integer size){
		com.ccclubs.service.admin.ICsFavoriteAddressService csFavoriteAddressService = $.GetSpringBean("csFavoriteAddressService");
		return csFavoriteAddressService.getCsFavoriteAddressList(params, size);
	}
	
	/**
	 * 获取会员常用地址分页
	 * @return
	 */
	public static @api Page<CsFavoriteAddress> getCsFavoriteAddressPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsFavoriteAddressService csFavoriteAddressService = $.GetSpringBean("csFavoriteAddressService");
		return csFavoriteAddressService.getCsFavoriteAddressPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取会员常用地址
	 * @param params
	 * @return
	 */
	public static @api CsFavoriteAddress getCsFavoriteAddress(Map params){
		com.ccclubs.service.admin.ICsFavoriteAddressService csFavoriteAddressService = $.GetSpringBean("csFavoriteAddressService");
		return csFavoriteAddressService.getCsFavoriteAddress(params);
	}
	
	/**
	 * 获取会员常用地址数
	 * @return
	 */
	public static @api Long getCsFavoriteAddressCount(Map params){
		com.ccclubs.service.admin.ICsFavoriteAddressService csFavoriteAddressService = $.GetSpringBean("csFavoriteAddressService");
		return csFavoriteAddressService.getCsFavoriteAddressCount(params);
	}
		
		
	/**
	 * 获取会员常用地址自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsFavoriteAddressEval(String eval,Map params){
		com.ccclubs.service.admin.ICsFavoriteAddressService csFavoriteAddressService = $.GetSpringBean("csFavoriteAddressService");
		return csFavoriteAddressService.getCsFavoriteAddressEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsFavoriteAddress(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsFavoriteAddressService csFavoriteAddressService = $.GetSpringBean("csFavoriteAddressService");
		csFavoriteAddressService.updateCsFavoriteAddressByConfirm(set, where);
	}
	
	
	/**
	 * 保存会员常用地址对象
	 * @param params
	 * @return
	 */
	public CsFavoriteAddress save(){
		com.ccclubs.service.admin.ICsFavoriteAddressService csFavoriteAddressService = $.GetSpringBean("csFavoriteAddressService");
		if(this.getCsfaId()!=null)
			csFavoriteAddressService.updateCsFavoriteAddress(this);
		else
			return csFavoriteAddressService.saveCsFavoriteAddress(this);
		return this;
	}
	
	
	/**
	 * 更新会员常用地址对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsFavoriteAddressService csFavoriteAddressService = $.GetSpringBean("csFavoriteAddressService");
		csFavoriteAddressService.updateCsFavoriteAddress$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsFavoriteAddressService csFavoriteAddressService = $.GetSpringBean("csFavoriteAddressService");
			csFavoriteAddressService.deleteCsFavoriteAddressById(this.getCsfaId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsFavoriteAddressService csFavoriteAddressService = $.GetSpringBean("csFavoriteAddressService");
		return csFavoriteAddressService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsfaId(){
		return this.csfaId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsfaId$(){
		String strValue="";
		 strValue=$.str(this.getCsfaId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCsfaId(Long csfaId){
		this.csfaId = csfaId;
		this.setSeted(F.csfaId);
	}
	/*******************************会员**********************************/	
	/**
	* 会员 [非空]      
	**/
	public Long getCsfaMember(){
		return this.csfaMember;
	}
	/**
	* 获取会员格式化(toString)
	**/
	public String getCsfaMember$(){
		String strValue="";
		 strValue=$.str(this.getCsfaMember());
	 	 return strValue;
	}
	/**
	* 会员 [非空]     
	**/
	public void setCsfaMember(Long csfaMember){
		this.csfaMember = csfaMember;
		this.setSeted(F.csfaMember);
	}
	/**
	* 获取关联对象[会员]
	**/	 			
 	public CsMember get$csfaMember(){
 		com.ccclubs.model.CsMember csfaMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsfaMember());
 		if(csfaMember!=null)
			return csfaMember;
		if(this.getCsfaMember()!=null){
			csfaMember = CsMember.get(this.getCsfaMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsfaMember(), csfaMember);
	 	return csfaMember;
	}
	/*******************************类型**********************************/	
	/**
	* 类型 [非空]      
	**/
	public Short getCsfaType(){
		return this.csfaType;
	}
	/**
	* 获取类型格式化(toString)
	**/
	public String getCsfaType$(){
		String strValue="";
		 if($.equals($.str(this.getCsfaType()),"2"))
			strValue=$.str("单位");
		 if($.equals($.str(this.getCsfaType()),"1"))
			strValue=$.str("企业");
		 if($.equals($.str(this.getCsfaType()),"0"))
			strValue=$.str("一般");
	 	 return strValue;
	}
	/**
	* 类型 [非空]     
	**/
	public void setCsfaType(Short csfaType){
		this.csfaType = csfaType;
		this.setSeted(F.csfaType);
	}
	/*******************************地址**********************************/	
	/**
	* 地址 [非空]      
	**/
	public String getCsfaAddress(){
		return this.csfaAddress;
	}
	/**
	* 获取地址格式化(toString)
	**/
	public String getCsfaAddress$(){
		String strValue="";
		 strValue=$.str(this.getCsfaAddress());
	 	 return strValue;
	}
	/**
	* 地址 [非空]     
	**/
	public void setCsfaAddress(String csfaAddress){
		this.csfaAddress = csfaAddress;
		this.setSeted(F.csfaAddress);
	}
	/*******************************描述**********************************/	
	/**
	* 描述 [非空]      
	**/
	public String getCsfaDescription(){
		return this.csfaDescription;
	}
	/**
	* 获取描述格式化(toString)
	**/
	public String getCsfaDescription$(){
		String strValue="";
		 strValue=$.str(this.getCsfaDescription());
	 	 return strValue;
	}
	/**
	* 描述 [非空]     
	**/
	public void setCsfaDescription(String csfaDescription){
		this.csfaDescription = csfaDescription;
		this.setSeted(F.csfaDescription);
	}
	/*******************************纬度**********************************/	
	/**
	* 纬度 [非空]      
	**/
	public Double getCsfaLatitude(){
		return this.csfaLatitude;
	}
	/**
	* 获取纬度格式化(toString)
	**/
	public String getCsfaLatitude$(){
		String strValue="";
		 strValue=$.str(this.getCsfaLatitude());
	 	 return strValue;
	}
	/**
	* 纬度 [非空]     
	**/
	public void setCsfaLatitude(Double csfaLatitude){
		this.csfaLatitude = csfaLatitude;
		this.setSeted(F.csfaLatitude);
	}
	/*******************************经度**********************************/	
	/**
	* 经度 [非空]      
	**/
	public Double getCsfaLongitude(){
		return this.csfaLongitude;
	}
	/**
	* 获取经度格式化(toString)
	**/
	public String getCsfaLongitude$(){
		String strValue="";
		 strValue=$.str(this.getCsfaLongitude());
	 	 return strValue;
	}
	/**
	* 经度 [非空]     
	**/
	public void setCsfaLongitude(Double csfaLongitude){
		this.csfaLongitude = csfaLongitude;
		this.setSeted(F.csfaLongitude);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]      
	**/
	public Short getCsfaStatus(){
		return this.csfaStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsfaStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsfaStatus()),"1"))
			strValue=$.str("有效");
		 if($.equals($.str(this.getCsfaStatus()),"0"))
			strValue=$.str("无效");
	 	 return strValue;
	}
	/**
	* 状态 [非空]     
	**/
	public void setCsfaStatus(Short csfaStatus){
		this.csfaStatus = csfaStatus;
		this.setSeted(F.csfaStatus);
	}
	/*******************************创建时间**********************************/	
	/**
	* 创建时间 [非空]      
	**/
	public Date getCsfaAddTime(){
		return this.csfaAddTime;
	}
	/**
	* 获取创建时间格式化(toString)
	**/
	public String getCsfaAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsfaAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 创建时间 [非空]     
	**/
	public void setCsfaAddTime(Date csfaAddTime){
		this.csfaAddTime = csfaAddTime;
		this.setSeted(F.csfaAddTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsfaUpdateTime(){
		return this.csfaUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsfaUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsfaUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCsfaUpdateTime(Date csfaUpdateTime){
		this.csfaUpdateTime = csfaUpdateTime;
		this.setSeted(F.csfaUpdateTime);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsFavoriteAddress.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsFavoriteAddress.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsFavoriteAddress.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsFavoriteAddress.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsFavoriteAddress.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsFavoriteAddress.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsFavoriteAddress.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsFavoriteAddress.this);
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
		public M csfaId(Object csfaId){this.put("csfaId", csfaId);return this;};
		/** and csfa_id is null */
		public M csfaIdNull(){if(this.get("csfaIdNot")==null)this.put("csfaIdNot", "");this.put("csfaId", null);return this;};
		/** not .... */
		public M csfaIdNot(){this.put("csfaIdNot", "not");return this;};
		/** 会员 [非空]       **/
		public M csfaMember(Object csfaMember){this.put("csfaMember", csfaMember);return this;};
		/** and csfa_member is null */
		public M csfaMemberNull(){if(this.get("csfaMemberNot")==null)this.put("csfaMemberNot", "");this.put("csfaMember", null);return this;};
		/** not .... */
		public M csfaMemberNot(){this.put("csfaMemberNot", "not");return this;};
		/** 类型 [非空]       **/
		public M csfaType(Object csfaType){this.put("csfaType", csfaType);return this;};
		/** and csfa_type is null */
		public M csfaTypeNull(){if(this.get("csfaTypeNot")==null)this.put("csfaTypeNot", "");this.put("csfaType", null);return this;};
		/** not .... */
		public M csfaTypeNot(){this.put("csfaTypeNot", "not");return this;};
		/** 地址 [非空]       **/
		public M csfaAddress(Object csfaAddress){this.put("csfaAddress", csfaAddress);return this;};
		/** and csfa_address is null */
		public M csfaAddressNull(){if(this.get("csfaAddressNot")==null)this.put("csfaAddressNot", "");this.put("csfaAddress", null);return this;};
		/** not .... */
		public M csfaAddressNot(){this.put("csfaAddressNot", "not");return this;};
		/** 描述 [非空]       **/
		public M csfaDescription(Object csfaDescription){this.put("csfaDescription", csfaDescription);return this;};
		/** and csfa_description is null */
		public M csfaDescriptionNull(){if(this.get("csfaDescriptionNot")==null)this.put("csfaDescriptionNot", "");this.put("csfaDescription", null);return this;};
		/** not .... */
		public M csfaDescriptionNot(){this.put("csfaDescriptionNot", "not");return this;};
		/** 纬度 [非空]       **/
		public M csfaLatitude(Object csfaLatitude){this.put("csfaLatitude", csfaLatitude);return this;};
		/** and csfa_latitude is null */
		public M csfaLatitudeNull(){if(this.get("csfaLatitudeNot")==null)this.put("csfaLatitudeNot", "");this.put("csfaLatitude", null);return this;};
		/** not .... */
		public M csfaLatitudeNot(){this.put("csfaLatitudeNot", "not");return this;};
		/** 经度 [非空]       **/
		public M csfaLongitude(Object csfaLongitude){this.put("csfaLongitude", csfaLongitude);return this;};
		/** and csfa_longitude is null */
		public M csfaLongitudeNull(){if(this.get("csfaLongitudeNot")==null)this.put("csfaLongitudeNot", "");this.put("csfaLongitude", null);return this;};
		/** not .... */
		public M csfaLongitudeNot(){this.put("csfaLongitudeNot", "not");return this;};
		/** 状态 [非空]       **/
		public M csfaStatus(Object csfaStatus){this.put("csfaStatus", csfaStatus);return this;};
		/** and csfa_status is null */
		public M csfaStatusNull(){if(this.get("csfaStatusNot")==null)this.put("csfaStatusNot", "");this.put("csfaStatus", null);return this;};
		/** not .... */
		public M csfaStatusNot(){this.put("csfaStatusNot", "not");return this;};
		/** 创建时间 [非空]       **/
		public M csfaAddTime(Object csfaAddTime){this.put("csfaAddTime", csfaAddTime);return this;};
		/** and csfa_add_time is null */
		public M csfaAddTimeNull(){if(this.get("csfaAddTimeNot")==null)this.put("csfaAddTimeNot", "");this.put("csfaAddTime", null);return this;};
		/** not .... */
		public M csfaAddTimeNot(){this.put("csfaAddTimeNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csfaUpdateTime(Object csfaUpdateTime){this.put("csfaUpdateTime", csfaUpdateTime);return this;};
		/** and csfa_update_time is null */
		public M csfaUpdateTimeNull(){if(this.get("csfaUpdateTimeNot")==null)this.put("csfaUpdateTimeNot", "");this.put("csfaUpdateTime", null);return this;};
		/** not .... */
		public M csfaUpdateTimeNot(){this.put("csfaUpdateTimeNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有会员常用地址 **/
		public @api List<CsFavoriteAddress> list(Integer size){
			return getCsFavoriteAddressList(this,size);
		}
		/** 获取会员常用地址分页 **/
		public @api Page<CsFavoriteAddress> page(int page,int size){
			return getCsFavoriteAddressPage(page, size , this);
		}
		/** 根据查询条件取会员常用地址 **/
		public @api CsFavoriteAddress get(){
			return getCsFavoriteAddress(this);
		}
		/** 获取会员常用地址数 **/
		public @api Long count(){
			return getCsFavoriteAddressCount(this);
		}
		/** 获取会员常用地址表达式 **/
		public @api <T> T eval(String strEval){
			return getCsFavoriteAddressEval(strEval,this);
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
			updateCsFavoriteAddress(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csfaId="csfaId";
		/** 会员 [非空]       **/
		public final static @type(Long.class)  String csfaMember="csfaMember";
		/** 类型 [非空]       **/
		public final static @type(Short.class)  String csfaType="csfaType";
		/** 地址 [非空]       **/
		public final static @type(String.class)  String csfaAddress="csfaAddress";
		/** 描述 [非空]       **/
		public final static @type(String.class)  String csfaDescription="csfaDescription";
		/** 纬度 [非空]       **/
		public final static @type(Double.class)  String csfaLatitude="csfaLatitude";
		/** 经度 [非空]       **/
		public final static @type(Double.class)  String csfaLongitude="csfaLongitude";
		/** 状态 [非空]       **/
		public final static @type(Short.class)  String csfaStatus="csfaStatus";
		/** 创建时间 [非空]       **/
		public final static @type(Date.class)  String csfaAddTime="csfaAddTime";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csfaUpdateTime="csfaUpdateTime";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csfaId="csfa_id";
		/** 会员 [非空]       **/
		public final static String csfaMember="csfa_member";
		/** 类型 [非空]       **/
		public final static String csfaType="csfa_type";
		/** 地址 [非空]       **/
		public final static String csfaAddress="csfa_address";
		/** 描述 [非空]       **/
		public final static String csfaDescription="csfa_description";
		/** 纬度 [非空]       **/
		public final static String csfaLatitude="csfa_latitude";
		/** 经度 [非空]       **/
		public final static String csfaLongitude="csfa_longitude";
		/** 状态 [非空]       **/
		public final static String csfaStatus="csfa_status";
		/** 创建时间 [非空]       **/
		public final static String csfaAddTime="csfa_add_time";
		/** 修改时间 [非空]       **/
		public final static String csfaUpdateTime="csfa_update_time";
		
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
				$.Set(name,CsFavoriteAddress.getCsFavoriteAddress(params));
			else
				$.Set(name,CsFavoriteAddress.getCsFavoriteAddressList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取会员常用地址数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsFavoriteAddress) $.GetRequest("CsFavoriteAddress$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsFavoriteAddress.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsFavoriteAddress.getCsFavoriteAddress(params);
			else
				value = CsFavoriteAddress.getCsFavoriteAddressList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsFavoriteAddress.Get($.add(CsFavoriteAddress.F.csfaId,param));
		else if(!$.empty(param.toString()))
			value = CsFavoriteAddress.get(Long.valueOf(param.toString()));
		$.SetRequest("CsFavoriteAddress$"+param.hashCode(), value);
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
	public void mergeSet(CsFavoriteAddress old){
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