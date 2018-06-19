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

@namespace("service/orderlog")
public @caption("订单操作记录") @table("cs_order_log") class CsOrderLog implements java.io.Serializable
{
	private static final long serialVersionUID =          1l;
	private @caption("编号") @column("csol_id")   @primary  @note("  ") Long csolId;// 主键 非空     
	private @caption("操作项目") @column("csol_title")    @note("  ") String csolTitle;// 非空     
	private @caption("所属订单") @column("csol_order")    @relate("$csolOrder") @RelateClass(CsOrder.class)  @note("  ") Long csolOrder;// 非空     
 	private CsOrder $csolOrder;//关联对象[系统订单]
	private @caption("操作人") @column("csol_editor")    @relate("$csolEditor") @RelateClass(SrvUser.class)  @note("  ") Long csolEditor;//    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csolEditor;//关联对象[用户]
	private @caption("操作会员") @column("csol_member")    @relate("$csolMember") @RelateClass(CsMember.class)  @note("  ") Long csolMember;//     
 	private CsMember $csolMember;//关联对象[会员帐号]
	private @caption("操作平台") @column("csol_from")    @note(" 0:网站 1:后台 2:微信 3:APP 4:企业 5:支付宝  ") Short csolFrom;// 0:网站 1:后台 2:微信 3:APP 4:企业 5:支付宝     
	private @caption("操作源信息") @column("csol_src")    @note("  ") String csolSrc;//     存储格式:类名@ID,其中类名包括
	private Object $csolSrc;//泛关联对象，对象类名由csolSrc字段中标明
	private @caption("备注信息") @column("csol_remark")    @note("  ") String csolRemark;//     
	private @caption("添加时间") @column("csol_add_time")    @note("  ") Date csolAddTime;// 非空     
	
	//默认构造函数
	public CsOrderLog(){
	
	}
	
	//主键构造函数
	public CsOrderLog(Long id){
		this.csolId = id;
	}
	
	/**所有字段构造函数 CsOrderLog(csolTitle,csolOrder,csolEditor,csolMember,csolFrom,csolSrc,csolRemark,csolAddTime)
	 CsOrderLog(
	 	$.getString("csolTitle")//操作项目 [非空]
	 	,$.getLong("csolOrder")//所属订单 [非空]
	 	,$.getLong("csolEditor")//操作人
	 	,$.getLong("csolMember")//操作会员
	 	,$.getShort("csolFrom")//操作平台
	 	,$.getString("csolSrc")//操作源信息
	 	,$.getString("csolRemark")//备注信息
	 	,$.getDate("csolAddTime")//添加时间 [非空]
	 )
	**/
	public CsOrderLog(String csolTitle,Long csolOrder,Long csolEditor,Long csolMember,Short csolFrom,String csolSrc,String csolRemark,Date csolAddTime){
		this.csolTitle=csolTitle;
		this.csolOrder=csolOrder;
		this.csolEditor=csolEditor;
		this.csolMember=csolMember;
		this.csolFrom=csolFrom;
		this.csolSrc=csolSrc;
		this.csolRemark=csolRemark;
		this.csolAddTime=csolAddTime;
	}
	
	//设置非空字段
	public CsOrderLog setNotNull(String csolTitle,Long csolOrder,Date csolAddTime){
		this.csolTitle=csolTitle;
		this.csolOrder=csolOrder;
		this.csolAddTime=csolAddTime;
		return this;
	}
	/** 编号 [非空]       **/
	public CsOrderLog csolId(Long csolId){
		this.csolId = csolId;
		this.setSeted(F.csolId);
		return this;
	}
	/** 操作项目 [非空]       **/
	public CsOrderLog csolTitle(String csolTitle){
		this.csolTitle = csolTitle;
		this.setSeted(F.csolTitle);
		return this;
	}
	/** 所属订单 [非空] [CsOrder]      **/
	public CsOrderLog csolOrder(Long csolOrder){
		this.csolOrder = csolOrder;
		this.setSeted(F.csolOrder);
		return this;
	}
	/** 操作人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsOrderLog csolEditor(Long csolEditor){
		this.csolEditor = csolEditor;
		this.setSeted(F.csolEditor);
		return this;
	}
	/** 操作会员  [CsMember]      **/
	public CsOrderLog csolMember(Long csolMember){
		this.csolMember = csolMember;
		this.setSeted(F.csolMember);
		return this;
	}
	/** 操作平台    0:网站 1:后台 2:微信 3:APP 4:企业 5:支付宝     **/
	public CsOrderLog csolFrom(Short csolFrom){
		this.csolFrom = csolFrom;
		this.setSeted(F.csolFrom);
		return this;
	}
	/** 操作源信息        **/
	public CsOrderLog csolSrc(String csolSrc){
		this.csolSrc = csolSrc;
		this.setSeted(F.csolSrc);
		return this;
	}
	/** 备注信息        **/
	public CsOrderLog csolRemark(String csolRemark){
		this.csolRemark = csolRemark;
		this.setSeted(F.csolRemark);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsOrderLog csolAddTime(Date csolAddTime){
		this.csolAddTime = csolAddTime;
		this.setSeted(F.csolAddTime);
		return this;
	}
	
	
	//克隆对象
	public CsOrderLog clone(){
		CsOrderLog clone = new CsOrderLog();
		clone.csolTitle=this.csolTitle;
		clone.csolOrder=this.csolOrder;
		clone.csolAddTime=this.csolAddTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取订单操作记录
	 * @param id
	 * @return
	 */
	public static @api CsOrderLog get(Long id){		
		return getCsOrderLogById(id);
	}
	/**
	 * 获取所有订单操作记录
	 * @return
	 */
	public static @api List<CsOrderLog> list(Map params,Integer size){
		return getCsOrderLogList(params,size);
	}
	/**
	 * 获取订单操作记录分页
	 * @return
	 */
	public static @api Page<CsOrderLog> page(int page,int size,Map params){
		return getCsOrderLogPage(page, size , params);
	}
	/**
	 * 根据查询条件取订单操作记录
	 * @param params
	 * @return
	 */
	public static @api CsOrderLog Get(Map params){
		return getCsOrderLog(params);
	}
	/**
	 * 获取订单操作记录数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsOrderLogCount(params);
	}
	/**
	 * 获取订单操作记录数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsOrderLogEval(eval,params);
	}
	
	/**
	 * 根据ID取订单操作记录
	 * @param id
	 * @return
	 */
	public static @api CsOrderLog getCsOrderLogById(Long id){		
		CsOrderLog csOrderLog = (CsOrderLog) $.GetRequest("CsOrderLog$"+id);
		if(csOrderLog!=null)
			return csOrderLog;
		com.ccclubs.service.admin.ICsOrderLogService csOrderLogService = $.GetSpringBean("csOrderLogService");		
		return csOrderLogService.getCsOrderLogById(id);
	}
	
	
	/**
	 * 根据ID取订单操作记录的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		//当前对象不支持键值缓存
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsolTitle$();
		if(!"CsolTitle".equals("CsolTitle"))
			keyValue+="("+this.getCsolTitle$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有订单操作记录
	 * @return
	 */
	public static @api List<CsOrderLog> getCsOrderLogList(Map params,Integer size){
		com.ccclubs.service.admin.ICsOrderLogService csOrderLogService = $.GetSpringBean("csOrderLogService");
		return csOrderLogService.getCsOrderLogList(params, size);
	}
	
	/**
	 * 获取订单操作记录分页
	 * @return
	 */
	public static @api Page<CsOrderLog> getCsOrderLogPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsOrderLogService csOrderLogService = $.GetSpringBean("csOrderLogService");
		return csOrderLogService.getCsOrderLogPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取订单操作记录
	 * @param params
	 * @return
	 */
	public static @api CsOrderLog getCsOrderLog(Map params){
		com.ccclubs.service.admin.ICsOrderLogService csOrderLogService = $.GetSpringBean("csOrderLogService");
		return csOrderLogService.getCsOrderLog(params);
	}
	
	/**
	 * 获取订单操作记录数
	 * @return
	 */
	public static @api Long getCsOrderLogCount(Map params){
		com.ccclubs.service.admin.ICsOrderLogService csOrderLogService = $.GetSpringBean("csOrderLogService");
		return csOrderLogService.getCsOrderLogCount(params);
	}
		
		
	/**
	 * 获取订单操作记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsOrderLogEval(String eval,Map params){
		com.ccclubs.service.admin.ICsOrderLogService csOrderLogService = $.GetSpringBean("csOrderLogService");
		return csOrderLogService.getCsOrderLogEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsOrderLog(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsOrderLogService csOrderLogService = $.GetSpringBean("csOrderLogService");
		csOrderLogService.updateCsOrderLogByConfirm(set, where);
	}
	
	
	/**
	 * 保存订单操作记录对象
	 * @param params
	 * @return
	 */
	public CsOrderLog save(){
		com.ccclubs.service.admin.ICsOrderLogService csOrderLogService = $.GetSpringBean("csOrderLogService");
		if(this.getCsolId()!=null)
			csOrderLogService.updateCsOrderLog(this);
		else
			return csOrderLogService.saveCsOrderLog(this);
		return this;
	}
	
	
	/**
	 * 更新订单操作记录对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsOrderLogService csOrderLogService = $.GetSpringBean("csOrderLogService");
		csOrderLogService.updateCsOrderLog$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsOrderLogService csOrderLogService = $.GetSpringBean("csOrderLogService");
			csOrderLogService.deleteCsOrderLogById(this.getCsolId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsOrderLogService csOrderLogService = $.GetSpringBean("csOrderLogService");
		return csOrderLogService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsolId(){
		return this.csolId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsolId$(){
		String strValue="";
		 strValue=$.str(this.getCsolId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsolId(Long csolId){
		this.csolId = csolId;
		this.setSeted(F.csolId);
	}
	/*******************************操作项目**********************************/	
	/**
	* 操作项目 [非空]      
	**/
	public String getCsolTitle(){
		return this.csolTitle;
	}
	/**
	* 获取操作项目格式化(toString)
	**/
	public String getCsolTitle$(){
		String strValue="";
		 strValue=$.str(this.getCsolTitle());
	 	 return strValue;
	}
	/**
	* 操作项目 [非空]      
	**/
	public void setCsolTitle(String csolTitle){
		this.csolTitle = csolTitle;
		this.setSeted(F.csolTitle);
	}
	/*******************************所属订单**********************************/	
	/**
	* 所属订单 [非空] [CsOrder]     
	**/
	public Long getCsolOrder(){
		return this.csolOrder;
	}
	/**
	* 获取所属订单格式化(toString)
	**/
	public String getCsolOrder$(){
		String strValue="";
		if(this.getCsolOrder()!=null){
				strValue+=$.str(CsOrder.getKeyValue(this.getCsolOrder()));
		}			
	 	 return strValue;
	}
	/**
	* 所属订单 [非空] [CsOrder]     
	**/
	public void setCsolOrder(Long csolOrder){
		this.csolOrder = csolOrder;
		this.setSeted(F.csolOrder);
	}
	/**
	* 获取关联对象[系统订单]
	**/	 			
 	public CsOrder get$csolOrder(){
 		com.ccclubs.model.CsOrder csOrder = (com.ccclubs.model.CsOrder) $.GetRequest("CsOrder$"+this.getCsolOrder());
 		if(csOrder!=null)
			return csOrder;
		if(this.getCsolOrder()!=null){
 			csOrder = CsOrder.get(this.getCsolOrder());
 		}
 		$.SetRequest("CsOrder$"+this.getCsolOrder(), csOrder);
	 	return csOrder;
	}
	/*******************************操作人**********************************/	
	/**
	* 操作人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsolEditor(){
		return this.csolEditor;
	}
	/**
	* 获取操作人格式化(toString)
	**/
	public String getCsolEditor$(){
		String strValue="";
		if(this.getCsolEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsolEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 操作人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsolEditor(Long csolEditor){
		this.csolEditor = csolEditor;
		this.setSeted(F.csolEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csolEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsolEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsolEditor()!=null){
 			srvUser = SrvUser.get(this.getCsolEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCsolEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************操作会员**********************************/	
	/**
	* 操作会员  [CsMember]     
	**/
	public Long getCsolMember(){
		return this.csolMember;
	}
	/**
	* 获取操作会员格式化(toString)
	**/
	public String getCsolMember$(){
		String strValue="";
		if(this.getCsolMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsolMember()));
		}			
	 	 return strValue;
	}
	/**
	* 操作会员  [CsMember]     
	**/
	public void setCsolMember(Long csolMember){
		this.csolMember = csolMember;
		this.setSeted(F.csolMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csolMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsolMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsolMember()!=null){
 			csMember = CsMember.get(this.getCsolMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsolMember(), csMember);
	 	return csMember;
	}
	/*******************************操作平台**********************************/	
	/**
	* 操作平台    0:网站 1:后台 2:微信 3:APP 4:企业 5:支付宝    
	**/
	public Short getCsolFrom(){
		return this.csolFrom;
	}
	/**
	* 获取操作平台格式化(toString)
	**/
	public String getCsolFrom$(){
		String strValue="";
		 if($.equals($.str(this.getCsolFrom()),"0"))
			strValue=$.str("网站");		 
		 if($.equals($.str(this.getCsolFrom()),"1"))
			strValue=$.str("后台");		 
		 if($.equals($.str(this.getCsolFrom()),"2"))
			strValue=$.str("微信");		 
		 if($.equals($.str(this.getCsolFrom()),"3"))
			strValue=$.str("APP");		 
		 if($.equals($.str(this.getCsolFrom()),"4"))
			strValue=$.str("企业");		 
		 if($.equals($.str(this.getCsolFrom()),"5"))
			strValue=$.str("支付宝");		 
	 	 return strValue;
	}
	/**
	* 操作平台    0:网站 1:后台 2:微信 3:APP 4:企业 5:支付宝    
	**/
	public void setCsolFrom(Short csolFrom){
		this.csolFrom = csolFrom;
		this.setSeted(F.csolFrom);
	}
	/*******************************操作源信息**********************************/	
	/**
	* 操作源信息       
	**/
	public String getCsolSrc(){
		return this.csolSrc;
	}
	/**
	* 获取操作源信息格式化(toString)
	**/
	public String getCsolSrc$(){
		String strValue="";
	 	 Object object = this.get$csolSrc();
	 	 if(object!=null){
	 		caption caption = object.getClass().getAnnotation(caption.class);
	 		if(caption!=null)strValue+="["+caption.value()+"]";
	 		try {
				strValue+=object.getClass().getMethod("getKeyValue").invoke(object);
			} catch (Exception e) {
				e.printStackTrace();
			}
	 	 }
	 	 return strValue;
	}
	/**
	* 操作源信息       
	**/
	public void setCsolSrc(String csolSrc){
		this.csolSrc = csolSrc;
		this.setSeted(F.csolSrc);
	}
	/**
	* 泛关联对象，对象类名由csolSrc字段中标明
	**/	 			
 	public <T> T get$csolSrc(){
		if($.empty(this.csolSrc))
 			return null;
 		try{
	 		String ClassName = this.csolSrc.split("@")[0];
	 		Long id = Long.parseLong(this.csolSrc.split("@")[1]);
	 		Class<?> cls = Class.forName("com.ccclubs.model."+ClassName);
	 		return (T)cls.getMethod("get", Long.class).invoke(null, id);	
 		}catch(Exception ex){
 			ex.printStackTrace();
 			return null;
 		}
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息       
	**/
	public String getCsolRemark(){
		return this.csolRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCsolRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsolRemark());
	 	 return strValue;
	}
	/**
	* 备注信息       
	**/
	public void setCsolRemark(String csolRemark){
		this.csolRemark = csolRemark;
		this.setSeted(F.csolRemark);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsolAddTime(){
		return this.csolAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsolAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsolAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsolAddTime(Date csolAddTime){
		this.csolAddTime = csolAddTime;
		this.setSeted(F.csolAddTime);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsOrderLog.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsOrderLog.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsOrderLog.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsOrderLog.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsOrderLog.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsOrderLog.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsOrderLog.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsOrderLog.this);
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
		public M csolId(Object csolId){this.put("csolId", csolId);return this;};
	 	/** and csol_id is null */
 		public M csolIdNull(){if(this.get("csolIdNot")==null)this.put("csolIdNot", "");this.put("csolId", null);return this;};
 		/** not .... */
 		public M csolIdNot(){this.put("csolIdNot", "not");return this;};
		/** 操作项目 [非空]       **/
		public M csolTitle(Object csolTitle){this.put("csolTitle", csolTitle);return this;};
	 	/** and csol_title is null */
 		public M csolTitleNull(){if(this.get("csolTitleNot")==null)this.put("csolTitleNot", "");this.put("csolTitle", null);return this;};
 		/** not .... */
 		public M csolTitleNot(){this.put("csolTitleNot", "not");return this;};
		/** 所属订单 [非空] [CsOrder]      **/
		public M csolOrder(Object csolOrder){this.put("csolOrder", csolOrder);return this;};
	 	/** and csol_order is null */
 		public M csolOrderNull(){if(this.get("csolOrderNot")==null)this.put("csolOrderNot", "");this.put("csolOrder", null);return this;};
 		/** not .... */
 		public M csolOrderNot(){this.put("csolOrderNot", "not");return this;};
		public M csolOrder$on(CsOrder.M value){
			this.put("CsOrder", value);
			this.put("csolOrder$on", value);
			return this;
		};	
		/** 操作人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csolEditor(Object csolEditor){this.put("csolEditor", csolEditor);return this;};
	 	/** and csol_editor is null */
 		public M csolEditorNull(){if(this.get("csolEditorNot")==null)this.put("csolEditorNot", "");this.put("csolEditor", null);return this;};
 		/** not .... */
 		public M csolEditorNot(){this.put("csolEditorNot", "not");return this;};
		/** 操作会员  [CsMember]      **/
		public M csolMember(Object csolMember){this.put("csolMember", csolMember);return this;};
	 	/** and csol_member is null */
 		public M csolMemberNull(){if(this.get("csolMemberNot")==null)this.put("csolMemberNot", "");this.put("csolMember", null);return this;};
 		/** not .... */
 		public M csolMemberNot(){this.put("csolMemberNot", "not");return this;};
		public M csolMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csolMember$on", value);
			return this;
		};	
		/** 操作平台    0:网站 1:后台 2:微信 3:APP 4:企业 5:支付宝     **/
		public M csolFrom(Object csolFrom){this.put("csolFrom", csolFrom);return this;};
	 	/** and csol_from is null */
 		public M csolFromNull(){if(this.get("csolFromNot")==null)this.put("csolFromNot", "");this.put("csolFrom", null);return this;};
 		/** not .... */
 		public M csolFromNot(){this.put("csolFromNot", "not");return this;};
		/** 操作源信息        **/
		public M csolSrc(Object csolSrc){this.put("csolSrc", csolSrc);return this;};
	 	/** and csol_src is null */
 		public M csolSrcNull(){if(this.get("csolSrcNot")==null)this.put("csolSrcNot", "");this.put("csolSrc", null);return this;};
 		/** not .... */
 		public M csolSrcNot(){this.put("csolSrcNot", "not");return this;};
		/** 备注信息        **/
		public M csolRemark(Object csolRemark){this.put("csolRemark", csolRemark);return this;};
	 	/** and csol_remark is null */
 		public M csolRemarkNull(){if(this.get("csolRemarkNot")==null)this.put("csolRemarkNot", "");this.put("csolRemark", null);return this;};
 		/** not .... */
 		public M csolRemarkNot(){this.put("csolRemarkNot", "not");return this;};
		/** 添加时间 [非空]       **/
		public M csolAddTime(Object csolAddTime){this.put("csolAddTime", csolAddTime);return this;};
	 	/** and csol_add_time is null */
 		public M csolAddTimeNull(){if(this.get("csolAddTimeNot")==null)this.put("csolAddTimeNot", "");this.put("csolAddTime", null);return this;};
 		/** not .... */
 		public M csolAddTimeNot(){this.put("csolAddTimeNot", "not");return this;};
 		/** and csol_add_time >= ? */
 		public M csolAddTimeStart(Object start){this.put("csolAddTimeStart", start);return this;};			
 		/** and csol_add_time <= ? */
 		public M csolAddTimeEnd(Object end){this.put("csolAddTimeEnd", end);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有订单操作记录 **/
		public @api List<CsOrderLog> list(Integer size){
			return getCsOrderLogList(this,size);
		}
		/** 获取订单操作记录分页 **/
		public @api Page<CsOrderLog> page(int page,int size){
			return getCsOrderLogPage(page, size , this);
		}
		/** 根据查询条件取订单操作记录 **/
		public @api CsOrderLog get(){
			return getCsOrderLog(this);
		}
		/** 获取订单操作记录数 **/
		public @api Long count(){
			return getCsOrderLogCount(this);
		}
		/** 获取订单操作记录表达式 **/
		public @api <T> T eval(String strEval){
			return getCsOrderLogEval(strEval,this);
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
			updateCsOrderLog(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csolId="csolId";
		/** 操作项目 [非空]       **/
		public final static @type(String.class) @like String csolTitle="csolTitle";
		/** 所属订单 [非空] [CsOrder]      **/
		public final static @type(Long.class)  String csolOrder="csolOrder";
		/** 操作人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csolEditor="csolEditor";
		/** 操作会员  [CsMember]      **/
		public final static @type(Long.class)  String csolMember="csolMember";
		/** 操作平台    0:网站 1:后台 2:微信 3:APP 4:企业 5:支付宝     **/
		public final static @type(Short.class)  String csolFrom="csolFrom";
		/** 操作源信息        **/
		public final static @type(String.class)  String csolSrc="csolSrc";
		/** 备注信息        **/
		public final static @type(String.class) @like String csolRemark="csolRemark";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csolAddTime="csolAddTime";
	 	/** and csol_add_time >= ? */
 		public final static @type(Date.class) String csolAddTimeStart="csolAddTimeStart";
 		/** and csol_add_time <= ? */
 		public final static @type(Date.class) String csolAddTimeEnd="csolAddTimeEnd";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csolId="csol_id";
		/** 操作项目 [非空]       **/
		public final static String csolTitle="csol_title";
		/** 所属订单 [非空] [CsOrder]      **/
		public final static String csolOrder="csol_order";
		/** 操作人  [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csolEditor="csol_editor";
		/** 操作会员  [CsMember]      **/
		public final static String csolMember="csol_member";
		/** 操作平台    0:网站 1:后台 2:微信 3:APP 4:企业 5:支付宝     **/
		public final static String csolFrom="csol_from";
		/** 操作源信息        **/
		public final static String csolSrc="csol_src";
		/** 备注信息        **/
		public final static String csolRemark="csol_remark";
		/** 添加时间 [非空]       **/
		public final static String csolAddTime="csol_add_time";
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
				$.Set(name,CsOrderLog.getCsOrderLog(params));
			else
				$.Set(name,CsOrderLog.getCsOrderLogList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取订单操作记录数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsOrderLog) $.GetRequest("CsOrderLog$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsOrderLog.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsOrderLog.getCsOrderLog(params);
			else
				value = CsOrderLog.getCsOrderLogList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsOrderLog.Get($.add(CsOrderLog.F.csolId,param));
		else if(!$.empty(param.toString()))
			value = CsOrderLog.get(Long.valueOf(param.toString()));
		$.SetRequest("CsOrderLog$"+param.hashCode(), value);
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
	public void mergeSet(CsOrderLog old){
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