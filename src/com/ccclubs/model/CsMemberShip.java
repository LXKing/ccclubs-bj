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

@namespace("user/membership")
public @caption("会员关系") @table("cs_member_ship") class CsMemberShip implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csms_id")   @primary  @note("  ") Long csmsId;// 主键 非空     
	private @caption("城市") @column("csms_host")    @relate("$csmsHost") @RelateClass(SrvHost.class)  @note("  ") Long csmsHost;// 非空     
 	private SrvHost $csmsHost;//关联对象[运营城市]
	private @caption("付款帐号") @column("csms_payer")    @relate("$csmsPayer") @RelateClass(CsMember.class)  @note("  ") Long csmsPayer;// 非空    ${basePath}${proname}/user/member_inquire.do 
 	private CsMember $csmsPayer;//关联对象[会员帐号]
	private @caption("使用帐号") @column("csms_targeter")    @relate("$csmsTargeter") @RelateClass(CsMember.class)  @note("  ") Long csmsTargeter;// 非空    ${basePath}${proname}/user/member_inquire.do 
 	private CsMember $csmsTargeter;//关联对象[会员帐号]
	private @caption("添加时间") @column("csms_add_time")    @note("  ") Date csmsAddTime;// 非空     
	private @caption("备注") @column("csms_remark")    @note("  ") String csmsRemark;//     
	private @caption("状态") @column("csms_status")    @note(" 0:正在审核 1:审核通过 2:审核未通过 3:无效  ") Short csmsStatus;// 非空 0:正在审核 1:审核通过 2:审核未通过 3:无效     
	
	//默认构造函数
	public CsMemberShip(){
	
	}
	
	//主键构造函数
	public CsMemberShip(Long id){
		this.csmsId = id;
	}
	
	/**所有字段构造函数 CsMemberShip(csmsHost,csmsPayer,csmsTargeter,csmsAddTime,csmsRemark,csmsStatus)
	 CsMemberShip(
	 	$.getLong("csmsHost")//城市 [非空]
	 	,$.getLong("csmsPayer")//付款帐号 [非空]
	 	,$.getLong("csmsTargeter")//使用帐号 [非空]
	 	,$.getDate("csmsAddTime")//添加时间 [非空]
	 	,$.getString("csmsRemark")//备注
	 	,$.getShort("csmsStatus")//状态 [非空]
	 )
	**/
	public CsMemberShip(Long csmsHost,Long csmsPayer,Long csmsTargeter,Date csmsAddTime,String csmsRemark,Short csmsStatus){
		this.csmsHost=csmsHost;
		this.csmsPayer=csmsPayer;
		this.csmsTargeter=csmsTargeter;
		this.csmsAddTime=csmsAddTime;
		this.csmsRemark=csmsRemark;
		this.csmsStatus=csmsStatus;
	}
	
	//设置非空字段
	public CsMemberShip setNotNull(Long csmsHost,Long csmsPayer,Long csmsTargeter,Date csmsAddTime,Short csmsStatus){
		this.csmsHost=csmsHost;
		this.csmsPayer=csmsPayer;
		this.csmsTargeter=csmsTargeter;
		this.csmsAddTime=csmsAddTime;
		this.csmsStatus=csmsStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsMemberShip csmsId(Long csmsId){
		this.csmsId = csmsId;
		this.setSeted(F.csmsId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsMemberShip csmsHost(Long csmsHost){
		this.csmsHost = csmsHost;
		this.setSeted(F.csmsHost);
		return this;
	}
	/** 付款帐号 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
	public CsMemberShip csmsPayer(Long csmsPayer){
		this.csmsPayer = csmsPayer;
		this.setSeted(F.csmsPayer);
		return this;
	}
	/** 使用帐号 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
	public CsMemberShip csmsTargeter(Long csmsTargeter){
		this.csmsTargeter = csmsTargeter;
		this.setSeted(F.csmsTargeter);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsMemberShip csmsAddTime(Date csmsAddTime){
		this.csmsAddTime = csmsAddTime;
		this.setSeted(F.csmsAddTime);
		return this;
	}
	/** 备注        **/
	public CsMemberShip csmsRemark(String csmsRemark){
		this.csmsRemark = csmsRemark;
		this.setSeted(F.csmsRemark);
		return this;
	}
	/** 状态 [非空]   0:正在审核 1:审核通过 2:审核未通过 3:无效     **/
	public CsMemberShip csmsStatus(Short csmsStatus){
		this.csmsStatus = csmsStatus;
		this.setSeted(F.csmsStatus);
		return this;
	}
	
	
	//克隆对象
	public CsMemberShip clone(){
		CsMemberShip clone = new CsMemberShip();
		clone.csmsHost=this.csmsHost;
		clone.csmsPayer=this.csmsPayer;
		clone.csmsTargeter=this.csmsTargeter;
		clone.csmsAddTime=this.csmsAddTime;
		clone.csmsStatus=this.csmsStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取会员关系
	 * @param id
	 * @return
	 */
	public static @api CsMemberShip get(Long id){		
		return getCsMemberShipById(id);
	}
	/**
	 * 获取所有会员关系
	 * @return
	 */
	public static @api List<CsMemberShip> list(Map params,Integer size){
		return getCsMemberShipList(params,size);
	}
	/**
	 * 获取会员关系分页
	 * @return
	 */
	public static @api Page<CsMemberShip> page(int page,int size,Map params){
		return getCsMemberShipPage(page, size , params);
	}
	/**
	 * 根据查询条件取会员关系
	 * @param params
	 * @return
	 */
	public static @api CsMemberShip Get(Map params){
		return getCsMemberShip(params);
	}
	/**
	 * 获取会员关系数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsMemberShipCount(params);
	}
	/**
	 * 获取会员关系数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsMemberShipEval(eval,params);
	}
	
	/**
	 * 根据ID取会员关系
	 * @param id
	 * @return
	 */
	public static @api CsMemberShip getCsMemberShipById(Long id){		
		CsMemberShip csMemberShip = (CsMemberShip) $.GetRequest("CsMemberShip$"+id);
		if(csMemberShip!=null)
			return csMemberShip;
		com.ccclubs.service.admin.ICsMemberShipService csMemberShipService = $.GetSpringBean("csMemberShipService");		
		return csMemberShipService.getCsMemberShipById(id);
	}
	
	
	/**
	 * 根据ID取会员关系的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsMemberShip.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsMemberShip csMemberShip = get(id);
		if(csMemberShip!=null){
			String strValue = csMemberShip.getCsmsId$();
			if(!"CsmsId".equals("CsmsId"))
				strValue+="("+csMemberShip.getCsmsId$()+")";
			MemCache.setValue(CsMemberShip.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsmsId$();
		if(!"CsmsId".equals("CsmsId"))
			keyValue+="("+this.getCsmsId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有会员关系
	 * @return
	 */
	public static @api List<CsMemberShip> getCsMemberShipList(Map params,Integer size){
		com.ccclubs.service.admin.ICsMemberShipService csMemberShipService = $.GetSpringBean("csMemberShipService");
		return csMemberShipService.getCsMemberShipList(params, size);
	}
	
	/**
	 * 获取会员关系分页
	 * @return
	 */
	public static @api Page<CsMemberShip> getCsMemberShipPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsMemberShipService csMemberShipService = $.GetSpringBean("csMemberShipService");
		return csMemberShipService.getCsMemberShipPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取会员关系
	 * @param params
	 * @return
	 */
	public static @api CsMemberShip getCsMemberShip(Map params){
		com.ccclubs.service.admin.ICsMemberShipService csMemberShipService = $.GetSpringBean("csMemberShipService");
		return csMemberShipService.getCsMemberShip(params);
	}
	
	/**
	 * 获取会员关系数
	 * @return
	 */
	public static @api Long getCsMemberShipCount(Map params){
		com.ccclubs.service.admin.ICsMemberShipService csMemberShipService = $.GetSpringBean("csMemberShipService");
		return csMemberShipService.getCsMemberShipCount(params);
	}
		
		
	/**
	 * 获取会员关系自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsMemberShipEval(String eval,Map params){
		com.ccclubs.service.admin.ICsMemberShipService csMemberShipService = $.GetSpringBean("csMemberShipService");
		return csMemberShipService.getCsMemberShipEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsMemberShip(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsMemberShipService csMemberShipService = $.GetSpringBean("csMemberShipService");
		csMemberShipService.updateCsMemberShipByConfirm(set, where);
	}
	
	
	/**
	 * 保存会员关系对象
	 * @param params
	 * @return
	 */
	public CsMemberShip save(){
		com.ccclubs.service.admin.ICsMemberShipService csMemberShipService = $.GetSpringBean("csMemberShipService");
		if(this.getCsmsId()!=null)
			csMemberShipService.updateCsMemberShip(this);
		else
			return csMemberShipService.saveCsMemberShip(this);
		return this;
	}
	
	
	/**
	 * 更新会员关系对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsMemberShipService csMemberShipService = $.GetSpringBean("csMemberShipService");
		csMemberShipService.updateCsMemberShip$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsMemberShipService csMemberShipService = $.GetSpringBean("csMemberShipService");
		if($.equals($.config("logic_delete"),"true"))
			csMemberShipService.removeCsMemberShipById(this.getCsmsId());
		else
			csMemberShipService.deleteCsMemberShipById(this.getCsmsId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsMemberShipService csMemberShipService = $.GetSpringBean("csMemberShipService");
		return csMemberShipService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsmsId(){
		return this.csmsId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsmsId$(){
		String strValue="";
		 strValue=$.str(this.getCsmsId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsmsId(Long csmsId){
		this.csmsId = csmsId;
		this.setSeted(F.csmsId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsmsHost(){
		return this.csmsHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsmsHost$(){
		String strValue="";
		if(this.getCsmsHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsmsHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsmsHost(Long csmsHost){
		this.csmsHost = csmsHost;
		this.setSeted(F.csmsHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csmsHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsmsHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsmsHost()!=null){
 			srvHost = SrvHost.get(this.getCsmsHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsmsHost(), srvHost);
	 	return srvHost;
	}
	/*******************************付款帐号**********************************/	
	/**
	* 付款帐号 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public Long getCsmsPayer(){
		return this.csmsPayer;
	}
	/**
	* 获取付款帐号格式化(toString)
	**/
	public String getCsmsPayer$(){
		String strValue="";
		if(this.getCsmsPayer()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsmsPayer()));
		}			
	 	 return strValue;
	}
	/**
	* 付款帐号 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public void setCsmsPayer(Long csmsPayer){
		this.csmsPayer = csmsPayer;
		this.setSeted(F.csmsPayer);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csmsPayer(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsmsPayer());
 		if(csMember!=null)
			return csMember;
		if(this.getCsmsPayer()!=null){
 			csMember = CsMember.get(this.getCsmsPayer());
 		}
 		$.SetRequest("CsMember$"+this.getCsmsPayer(), csMember);
	 	return csMember;
	}
	/*******************************使用帐号**********************************/	
	/**
	* 使用帐号 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public Long getCsmsTargeter(){
		return this.csmsTargeter;
	}
	/**
	* 获取使用帐号格式化(toString)
	**/
	public String getCsmsTargeter$(){
		String strValue="";
		if(this.getCsmsTargeter()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsmsTargeter()));
		}			
	 	 return strValue;
	}
	/**
	* 使用帐号 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public void setCsmsTargeter(Long csmsTargeter){
		this.csmsTargeter = csmsTargeter;
		this.setSeted(F.csmsTargeter);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csmsTargeter(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsmsTargeter());
 		if(csMember!=null)
			return csMember;
		if(this.getCsmsTargeter()!=null){
 			csMember = CsMember.get(this.getCsmsTargeter());
 		}
 		$.SetRequest("CsMember$"+this.getCsmsTargeter(), csMember);
	 	return csMember;
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsmsAddTime(){
		return this.csmsAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsmsAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmsAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsmsAddTime(Date csmsAddTime){
		this.csmsAddTime = csmsAddTime;
		this.setSeted(F.csmsAddTime);
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getCsmsRemark(){
		return this.csmsRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCsmsRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsmsRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setCsmsRemark(String csmsRemark){
		this.csmsRemark = csmsRemark;
		this.setSeted(F.csmsRemark);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:正在审核 1:审核通过 2:审核未通过 3:无效    
	**/
	public Short getCsmsStatus(){
		return this.csmsStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsmsStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsmsStatus()),"0"))
			strValue=$.str("正在审核");		 
		 if($.equals($.str(this.getCsmsStatus()),"1"))
			strValue=$.str("审核通过");		 
		 if($.equals($.str(this.getCsmsStatus()),"2"))
			strValue=$.str("审核未通过");		 
		 if($.equals($.str(this.getCsmsStatus()),"3"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:正在审核 1:审核通过 2:审核未通过 3:无效    
	**/
	public void setCsmsStatus(Short csmsStatus){
		this.csmsStatus = csmsStatus;
		this.setSeted(F.csmsStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsMemberShip.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMemberShip.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMemberShip.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsMemberShip.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsMemberShip.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMemberShip.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMemberShip.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsMemberShip.this);
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
		public M csmsId(Object csmsId){this.put("csmsId", csmsId);return this;};
	 	/** and csms_id is null */
 		public M csmsIdNull(){if(this.get("csmsIdNot")==null)this.put("csmsIdNot", "");this.put("csmsId", null);return this;};
 		/** not .... */
 		public M csmsIdNot(){this.put("csmsIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csmsHost(Object csmsHost){this.put("csmsHost", csmsHost);return this;};
	 	/** and csms_host is null */
 		public M csmsHostNull(){if(this.get("csmsHostNot")==null)this.put("csmsHostNot", "");this.put("csmsHost", null);return this;};
 		/** not .... */
 		public M csmsHostNot(){this.put("csmsHostNot", "not");return this;};
		/** 付款帐号 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public M csmsPayer(Object csmsPayer){this.put("csmsPayer", csmsPayer);return this;};
	 	/** and csms_payer is null */
 		public M csmsPayerNull(){if(this.get("csmsPayerNot")==null)this.put("csmsPayerNot", "");this.put("csmsPayer", null);return this;};
 		/** not .... */
 		public M csmsPayerNot(){this.put("csmsPayerNot", "not");return this;};
		public M csmsPayer$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csmsPayer$on", value);
			return this;
		};	
		/** 使用帐号 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public M csmsTargeter(Object csmsTargeter){this.put("csmsTargeter", csmsTargeter);return this;};
	 	/** and csms_targeter is null */
 		public M csmsTargeterNull(){if(this.get("csmsTargeterNot")==null)this.put("csmsTargeterNot", "");this.put("csmsTargeter", null);return this;};
 		/** not .... */
 		public M csmsTargeterNot(){this.put("csmsTargeterNot", "not");return this;};
		public M csmsTargeter$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csmsTargeter$on", value);
			return this;
		};	
		/** 添加时间 [非空]       **/
		public M csmsAddTime(Object csmsAddTime){this.put("csmsAddTime", csmsAddTime);return this;};
	 	/** and csms_add_time is null */
 		public M csmsAddTimeNull(){if(this.get("csmsAddTimeNot")==null)this.put("csmsAddTimeNot", "");this.put("csmsAddTime", null);return this;};
 		/** not .... */
 		public M csmsAddTimeNot(){this.put("csmsAddTimeNot", "not");return this;};
 		/** and csms_add_time >= ? */
 		public M csmsAddTimeStart(Object start){this.put("csmsAddTimeStart", start);return this;};			
 		/** and csms_add_time <= ? */
 		public M csmsAddTimeEnd(Object end){this.put("csmsAddTimeEnd", end);return this;};
		/** 备注        **/
		public M csmsRemark(Object csmsRemark){this.put("csmsRemark", csmsRemark);return this;};
	 	/** and csms_remark is null */
 		public M csmsRemarkNull(){if(this.get("csmsRemarkNot")==null)this.put("csmsRemarkNot", "");this.put("csmsRemark", null);return this;};
 		/** not .... */
 		public M csmsRemarkNot(){this.put("csmsRemarkNot", "not");return this;};
		/** 状态 [非空]   0:正在审核 1:审核通过 2:审核未通过 3:无效     **/
		public M csmsStatus(Object csmsStatus){this.put("csmsStatus", csmsStatus);return this;};
	 	/** and csms_status is null */
 		public M csmsStatusNull(){if(this.get("csmsStatusNot")==null)this.put("csmsStatusNot", "");this.put("csmsStatus", null);return this;};
 		/** not .... */
 		public M csmsStatusNot(){this.put("csmsStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有会员关系 **/
		public @api List<CsMemberShip> list(Integer size){
			return getCsMemberShipList(this,size);
		}
		/** 获取会员关系分页 **/
		public @api Page<CsMemberShip> page(int page,int size){
			return getCsMemberShipPage(page, size , this);
		}
		/** 根据查询条件取会员关系 **/
		public @api CsMemberShip get(){
			return getCsMemberShip(this);
		}
		/** 获取会员关系数 **/
		public @api Long count(){
			return getCsMemberShipCount(this);
		}
		/** 获取会员关系表达式 **/
		public @api <T> T eval(String strEval){
			return getCsMemberShipEval(strEval,this);
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
			updateCsMemberShip(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csmsId="csmsId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csmsHost="csmsHost";
		/** 付款帐号 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static @type(Long.class)  String csmsPayer="csmsPayer";
		/** 使用帐号 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static @type(Long.class)  String csmsTargeter="csmsTargeter";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csmsAddTime="csmsAddTime";
	 	/** and csms_add_time >= ? */
 		public final static @type(Date.class) String csmsAddTimeStart="csmsAddTimeStart";
 		/** and csms_add_time <= ? */
 		public final static @type(Date.class) String csmsAddTimeEnd="csmsAddTimeEnd";
		/** 备注        **/
		public final static @type(String.class) @like String csmsRemark="csmsRemark";
		/** 状态 [非空]   0:正在审核 1:审核通过 2:审核未通过 3:无效     **/
		public final static @type(Short.class)  String csmsStatus="csmsStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csmsId="csms_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csmsHost="csms_host";
		/** 付款帐号 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static String csmsPayer="csms_payer";
		/** 使用帐号 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static String csmsTargeter="csms_targeter";
		/** 添加时间 [非空]       **/
		public final static String csmsAddTime="csms_add_time";
		/** 备注        **/
		public final static String csmsRemark="csms_remark";
		/** 状态 [非空]   0:正在审核 1:审核通过 2:审核未通过 3:无效     **/
		public final static String csmsStatus="csms_status";
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
				$.Set(name,CsMemberShip.getCsMemberShip(params));
			else
				$.Set(name,CsMemberShip.getCsMemberShipList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取会员关系数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsMemberShip) $.GetRequest("CsMemberShip$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsMemberShip.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsMemberShip.getCsMemberShip(params);
			else
				value = CsMemberShip.getCsMemberShipList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsMemberShip.Get($.add(CsMemberShip.F.csmsId,param));
		else if(!$.empty(param.toString()))
			value = CsMemberShip.get(Long.valueOf(param.toString()));
		$.SetRequest("CsMemberShip$"+param.hashCode(), value);
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
	public void mergeSet(CsMemberShip old){
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