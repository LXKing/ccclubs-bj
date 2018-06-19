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

@namespace("configurator/wx/wxorder")
public @caption("微信指令") @table("cs_wx_order") class CsWxOrder implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cswo_id")   @primary  @note("  ") Long cswoId;// 主键 非空     
	private @caption("指令名称") @column("cswo_name")    @note("  模糊指令时，多个不同的词之前用|分隔") String cswoName;// 非空  模糊指令时，多个不同的词之前用|分隔   
	private @caption("匹配类型") @column("cswo_type")    @note(" 0:精确匹配 1:模糊匹配  ") Short cswoType;// 非空 0:精确匹配 1:模糊匹配     
	private @caption("回复方式") @column("cswo_rtype")    @note(" 0:文字内容 1:图文内容 2:调用过程  ") Short cswoRtype;// 非空 0:文字内容 1:图文内容 2:调用过程     
	private @caption("文字内容") @column("cswo_content")    @note("  ") String cswoContent;//     
	//private @caption("图文内容") @RelateClass(CsWxContent.class) List<CsWxContent> cswoNews;//反向关联的微信素材列表
	private @caption("调用过程") @column("cswo_class")    @relate("$cswoClass") @RelateClass(SrvProperty.class)  @note(" 微信处理脚本:'WX_ORDER_CLASS'  预先设置好的过程处理") Long cswoClass;// 微信处理脚本:'WX_ORDER_CLASS'  预先设置好的过程处理  select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) 
 	private SrvProperty $cswoClass;//关联对象[系统属性]
	private @caption("优先级") @column("cswo_priority")    @note("  ") Integer cswoPriority;//     
	private @caption("更改时间") @column("cswo_time")    @note("  ") Date cswoTime;// 非空     
	private @caption("状态") @column("cswo_status")    @note(" 1:正常 0:无效  ") Short cswoStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsWxOrder(){
	
	}
	
	//主键构造函数
	public CsWxOrder(Long id){
		this.cswoId = id;
	}
	
	/**所有字段构造函数 CsWxOrder(cswoName,cswoType,cswoRtype,cswoContent,cswoClass,cswoPriority,cswoTime,cswoStatus)
	 CsWxOrder(
	 	$.getString("cswoName")//指令名称 [非空]
	 	,$.getShort("cswoType")//匹配类型 [非空]
	 	,$.getShort("cswoRtype")//回复方式 [非空]
	 	,$.getString("cswoContent")//文字内容
	 	,$.getLong("cswoClass")//调用过程
	 	,$.getInteger("cswoPriority")//优先级
	 	,$.getDate("cswoTime")//更改时间 [非空]
	 	,$.getShort("cswoStatus")//状态 [非空]
	 )
	**/
	public CsWxOrder(String cswoName,Short cswoType,Short cswoRtype,String cswoContent,Long cswoClass,Integer cswoPriority,Date cswoTime,Short cswoStatus){
		this.cswoName=cswoName;
		this.cswoType=cswoType;
		this.cswoRtype=cswoRtype;
		this.cswoContent=cswoContent;
		this.cswoClass=cswoClass;
		this.cswoPriority=cswoPriority;
		this.cswoTime=cswoTime;
		this.cswoStatus=cswoStatus;
	}
	
	//设置非空字段
	public CsWxOrder setNotNull(String cswoName,Short cswoType,Short cswoRtype,Date cswoTime,Short cswoStatus){
		this.cswoName=cswoName;
		this.cswoType=cswoType;
		this.cswoRtype=cswoRtype;
		this.cswoTime=cswoTime;
		this.cswoStatus=cswoStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsWxOrder cswoId(Long cswoId){
		this.cswoId = cswoId;
		this.setSeted(F.cswoId);
		return this;
	}
	/** 指令名称 [非空]    模糊指令时，多个不同的词之前用|分隔   **/
	public CsWxOrder cswoName(String cswoName){
		this.cswoName = cswoName;
		this.setSeted(F.cswoName);
		return this;
	}
	/** 匹配类型 [非空]   0:精确匹配 1:模糊匹配     **/
	public CsWxOrder cswoType(Short cswoType){
		this.cswoType = cswoType;
		this.setSeted(F.cswoType);
		return this;
	}
	/** 回复方式 [非空]   0:文字内容 1:图文内容 2:调用过程     **/
	public CsWxOrder cswoRtype(Short cswoRtype){
		this.cswoRtype = cswoRtype;
		this.setSeted(F.cswoRtype);
		return this;
	}
	/** 文字内容        **/
	public CsWxOrder cswoContent(String cswoContent){
		this.cswoContent = cswoContent;
		this.setSeted(F.cswoContent);
		return this;
	}
	/** 调用过程  [SrvProperty]  微信处理脚本:'WX_ORDER_CLASS'  预先设置好的过程处理  select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
	public CsWxOrder cswoClass(Long cswoClass){
		this.cswoClass = cswoClass;
		this.setSeted(F.cswoClass);
		return this;
	}
	/** 优先级        **/
	public CsWxOrder cswoPriority(Integer cswoPriority){
		this.cswoPriority = cswoPriority;
		this.setSeted(F.cswoPriority);
		return this;
	}
	/** 更改时间 [非空]       **/
	public CsWxOrder cswoTime(Date cswoTime){
		this.cswoTime = cswoTime;
		this.setSeted(F.cswoTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsWxOrder cswoStatus(Short cswoStatus){
		this.cswoStatus = cswoStatus;
		this.setSeted(F.cswoStatus);
		return this;
	}
	
	
	//克隆对象
	public CsWxOrder clone(){
		CsWxOrder clone = new CsWxOrder();
		clone.cswoName=this.cswoName;
		clone.cswoType=this.cswoType;
		clone.cswoRtype=this.cswoRtype;
		clone.cswoTime=this.cswoTime;
		clone.cswoStatus=this.cswoStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取微信指令
	 * @param id
	 * @return
	 */
	public static @api CsWxOrder get(Long id){		
		return getCsWxOrderById(id);
	}
	/**
	 * 获取所有微信指令
	 * @return
	 */
	public static @api List<CsWxOrder> list(Map params,Integer size){
		return getCsWxOrderList(params,size);
	}
	/**
	 * 获取微信指令分页
	 * @return
	 */
	public static @api Page<CsWxOrder> page(int page,int size,Map params){
		return getCsWxOrderPage(page, size , params);
	}
	/**
	 * 根据查询条件取微信指令
	 * @param params
	 * @return
	 */
	public static @api CsWxOrder Get(Map params){
		return getCsWxOrder(params);
	}
	/**
	 * 获取微信指令数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsWxOrderCount(params);
	}
	/**
	 * 获取微信指令数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsWxOrderEval(eval,params);
	}
	
	/**
	 * 根据ID取微信指令
	 * @param id
	 * @return
	 */
	public static @api CsWxOrder getCsWxOrderById(Long id){		
		CsWxOrder csWxOrder = (CsWxOrder) $.GetRequest("CsWxOrder$"+id);
		if(csWxOrder!=null)
			return csWxOrder;
		com.ccclubs.service.admin.ICsWxOrderService csWxOrderService = $.GetSpringBean("csWxOrderService");		
		return csWxOrderService.getCsWxOrderById(id);
	}
	
	
	/**
	 * 根据ID取微信指令的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsWxOrder.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsWxOrder csWxOrder = get(id);
		if(csWxOrder!=null){
			String strValue = csWxOrder.getCswoName$();
			if(!"CswoName".equals("CswoName"))
				strValue+="("+csWxOrder.getCswoName$()+")";
			MemCache.setValue(CsWxOrder.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCswoName$();
		if(!"CswoName".equals("CswoName"))
			keyValue+="("+this.getCswoName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有微信指令
	 * @return
	 */
	public static @api List<CsWxOrder> getCsWxOrderList(Map params,Integer size){
		com.ccclubs.service.admin.ICsWxOrderService csWxOrderService = $.GetSpringBean("csWxOrderService");
		return csWxOrderService.getCsWxOrderList(params, size);
	}
	
	/**
	 * 获取微信指令分页
	 * @return
	 */
	public static @api Page<CsWxOrder> getCsWxOrderPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsWxOrderService csWxOrderService = $.GetSpringBean("csWxOrderService");
		return csWxOrderService.getCsWxOrderPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取微信指令
	 * @param params
	 * @return
	 */
	public static @api CsWxOrder getCsWxOrder(Map params){
		com.ccclubs.service.admin.ICsWxOrderService csWxOrderService = $.GetSpringBean("csWxOrderService");
		return csWxOrderService.getCsWxOrder(params);
	}
	
	/**
	 * 获取微信指令数
	 * @return
	 */
	public static @api Long getCsWxOrderCount(Map params){
		com.ccclubs.service.admin.ICsWxOrderService csWxOrderService = $.GetSpringBean("csWxOrderService");
		return csWxOrderService.getCsWxOrderCount(params);
	}
		
		
	/**
	 * 获取微信指令自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsWxOrderEval(String eval,Map params){
		com.ccclubs.service.admin.ICsWxOrderService csWxOrderService = $.GetSpringBean("csWxOrderService");
		return csWxOrderService.getCsWxOrderEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsWxOrder(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsWxOrderService csWxOrderService = $.GetSpringBean("csWxOrderService");
		csWxOrderService.updateCsWxOrderByConfirm(set, where);
	}
	
	
	/**
	 * 保存微信指令对象
	 * @param params
	 * @return
	 */
	public CsWxOrder save(){
		com.ccclubs.service.admin.ICsWxOrderService csWxOrderService = $.GetSpringBean("csWxOrderService");
		if(this.getCswoId()!=null)
			csWxOrderService.updateCsWxOrder(this);
		else
			return csWxOrderService.saveCsWxOrder(this);
		return this;
	}
	
	
	/**
	 * 更新微信指令对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsWxOrderService csWxOrderService = $.GetSpringBean("csWxOrderService");
		csWxOrderService.updateCsWxOrder$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsWxOrderService csWxOrderService = $.GetSpringBean("csWxOrderService");
		if($.equals($.config("logic_delete"),"true"))
			csWxOrderService.removeCsWxOrderById(this.getCswoId());
		else
			csWxOrderService.deleteCsWxOrderById(this.getCswoId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsWxOrderService csWxOrderService = $.GetSpringBean("csWxOrderService");
		return csWxOrderService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCswoId(){
		return this.cswoId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCswoId$(){
		String strValue="";
		 strValue=$.str(this.getCswoId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCswoId(Long cswoId){
		this.cswoId = cswoId;
		this.setSeted(F.cswoId);
	}
	/*******************************指令名称**********************************/	
	/**
	* 指令名称 [非空]    模糊指令时，多个不同的词之前用|分隔  
	**/
	public String getCswoName(){
		return this.cswoName;
	}
	/**
	* 获取指令名称格式化(toString)
	**/
	public String getCswoName$(){
		String strValue="";
		 strValue=$.str(this.getCswoName());
	 	 return strValue;
	}
	/**
	* 指令名称 [非空]    模糊指令时，多个不同的词之前用|分隔  
	**/
	public void setCswoName(String cswoName){
		this.cswoName = cswoName;
		this.setSeted(F.cswoName);
	}
	/*******************************匹配类型**********************************/	
	/**
	* 匹配类型 [非空]   0:精确匹配 1:模糊匹配    
	**/
	public Short getCswoType(){
		return this.cswoType;
	}
	/**
	* 获取匹配类型格式化(toString)
	**/
	public String getCswoType$(){
		String strValue="";
		 if($.equals($.str(this.getCswoType()),"0"))
			strValue=$.str("精确匹配");		 
		 if($.equals($.str(this.getCswoType()),"1"))
			strValue=$.str("模糊匹配");		 
	 	 return strValue;
	}
	/**
	* 匹配类型 [非空]   0:精确匹配 1:模糊匹配    
	**/
	public void setCswoType(Short cswoType){
		this.cswoType = cswoType;
		this.setSeted(F.cswoType);
	}
	/*******************************回复方式**********************************/	
	/**
	* 回复方式 [非空]   0:文字内容 1:图文内容 2:调用过程    
	**/
	public Short getCswoRtype(){
		return this.cswoRtype;
	}
	/**
	* 获取回复方式格式化(toString)
	**/
	public String getCswoRtype$(){
		String strValue="";
		 if($.equals($.str(this.getCswoRtype()),"0"))
			strValue=$.str("文字内容");		 
		 if($.equals($.str(this.getCswoRtype()),"1"))
			strValue=$.str("图文内容");		 
		 if($.equals($.str(this.getCswoRtype()),"2"))
			strValue=$.str("调用过程");		 
	 	 return strValue;
	}
	/**
	* 回复方式 [非空]   0:文字内容 1:图文内容 2:调用过程    
	**/
	public void setCswoRtype(Short cswoRtype){
		this.cswoRtype = cswoRtype;
		this.setSeted(F.cswoRtype);
	}
	/*******************************文字内容**********************************/	
	/**
	* 文字内容       
	**/
	public String getCswoContent(){
		return this.cswoContent;
	}
	/**
	* 获取文字内容格式化(toString)
	**/
	public String getCswoContent$(){
		String strValue="";
		 strValue=$.str(this.getCswoContent());
	 	 return strValue;
	}
	/**
	* 文字内容       
	**/
	public void setCswoContent(String cswoContent){
		this.cswoContent = cswoContent;
		this.setSeted(F.cswoContent);
	}
	/*******************************反向关联的微信素材列表**********************************/	
	/**
	* 获取与当前对象反向关联的微信素材列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsWxContent> getCswoNews(){
		com.ccclubs.service.admin.ICsWxContentService csWxContentService = $.GetSpringBean("csWxContentService");
		Map params = new HashMap();
		params.put("cswcOrder",this.getCswoId());
		return csWxContentService.getCsWxContentList(params,-1);
	}
	/**
	* 获取图文内容格式化(toString)
	**/
	public String getCswoNews$(){
		return "[...]";
	}
	/*******************************调用过程**********************************/	
	/**
	* 调用过程  [SrvProperty]  微信处理脚本:'WX_ORDER_CLASS'  预先设置好的过程处理  select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)
	**/
	public Long getCswoClass(){
		return this.cswoClass;
	}
	/**
	* 获取调用过程格式化(toString)
	**/
	public String getCswoClass$(){
		String strValue="";
		if(this.getCswoClass()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCswoClass()));
		}			
	 	 return strValue;
	}
	/**
	* 调用过程  [SrvProperty]  微信处理脚本:'WX_ORDER_CLASS'  预先设置好的过程处理  select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?)
	**/
	public void setCswoClass(Long cswoClass){
		this.cswoClass = cswoClass;
		this.setSeted(F.cswoClass);
	}
	/**
	* 获取关联对象[系统属性]
	**/	 			
 	public SrvProperty get$cswoClass(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCswoClass());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCswoClass()!=null){
 			srvProperty = SrvProperty.get(this.getCswoClass());
 		}
 		$.SetRequest("SrvProperty$"+this.getCswoClass(), srvProperty);
	 	return srvProperty;
	}
	/*******************************优先级**********************************/	
	/**
	* 优先级       
	**/
	public Integer getCswoPriority(){
		return this.cswoPriority;
	}
	/**
	* 获取优先级格式化(toString)
	**/
	public String getCswoPriority$(){
		String strValue="";
		 strValue=$.str(this.getCswoPriority());
	 	 return strValue;
	}
	/**
	* 优先级       
	**/
	public void setCswoPriority(Integer cswoPriority){
		this.cswoPriority = cswoPriority;
		this.setSeted(F.cswoPriority);
	}
	/*******************************更改时间**********************************/	
	/**
	* 更改时间 [非空]      
	**/
	public Date getCswoTime(){
		return this.cswoTime;
	}
	/**
	* 获取更改时间格式化(toString)
	**/
	public String getCswoTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCswoTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 更改时间 [非空]      
	**/
	public void setCswoTime(Date cswoTime){
		this.cswoTime = cswoTime;
		this.setSeted(F.cswoTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCswoStatus(){
		return this.cswoStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCswoStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCswoStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCswoStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCswoStatus(Short cswoStatus){
		this.cswoStatus = cswoStatus;
		this.setSeted(F.cswoStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsWxOrder.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsWxOrder.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsWxOrder.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsWxOrder.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsWxOrder.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsWxOrder.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsWxOrder.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsWxOrder.this);
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
		public M cswoId(Object cswoId){this.put("cswoId", cswoId);return this;};
	 	/** and cswo_id is null */
 		public M cswoIdNull(){if(this.get("cswoIdNot")==null)this.put("cswoIdNot", "");this.put("cswoId", null);return this;};
 		/** not .... */
 		public M cswoIdNot(){this.put("cswoIdNot", "not");return this;};
		/** 指令名称 [非空]    模糊指令时，多个不同的词之前用|分隔   **/
		public M cswoName(Object cswoName){this.put("cswoName", cswoName);return this;};
	 	/** and cswo_name is null */
 		public M cswoNameNull(){if(this.get("cswoNameNot")==null)this.put("cswoNameNot", "");this.put("cswoName", null);return this;};
 		/** not .... */
 		public M cswoNameNot(){this.put("cswoNameNot", "not");return this;};
		/** 匹配类型 [非空]   0:精确匹配 1:模糊匹配     **/
		public M cswoType(Object cswoType){this.put("cswoType", cswoType);return this;};
	 	/** and cswo_type is null */
 		public M cswoTypeNull(){if(this.get("cswoTypeNot")==null)this.put("cswoTypeNot", "");this.put("cswoType", null);return this;};
 		/** not .... */
 		public M cswoTypeNot(){this.put("cswoTypeNot", "not");return this;};
		/** 回复方式 [非空]   0:文字内容 1:图文内容 2:调用过程     **/
		public M cswoRtype(Object cswoRtype){this.put("cswoRtype", cswoRtype);return this;};
	 	/** and cswo_rtype is null */
 		public M cswoRtypeNull(){if(this.get("cswoRtypeNot")==null)this.put("cswoRtypeNot", "");this.put("cswoRtype", null);return this;};
 		/** not .... */
 		public M cswoRtypeNot(){this.put("cswoRtypeNot", "not");return this;};
		/** 文字内容        **/
		public M cswoContent(Object cswoContent){this.put("cswoContent", cswoContent);return this;};
	 	/** and cswo_content is null */
 		public M cswoContentNull(){if(this.get("cswoContentNot")==null)this.put("cswoContentNot", "");this.put("cswoContent", null);return this;};
 		/** not .... */
 		public M cswoContentNot(){this.put("cswoContentNot", "not");return this;};
		/** 调用过程  [SrvProperty]  微信处理脚本:'WX_ORDER_CLASS'  预先设置好的过程处理  select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
		public M cswoClass(Object cswoClass){this.put("cswoClass", cswoClass);return this;};
	 	/** and cswo_class is null */
 		public M cswoClassNull(){if(this.get("cswoClassNot")==null)this.put("cswoClassNot", "");this.put("cswoClass", null);return this;};
 		/** not .... */
 		public M cswoClassNot(){this.put("cswoClassNot", "not");return this;};
		public M cswoClass$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("cswoClass$on", value);
			return this;
		};	
		/** 优先级        **/
		public M cswoPriority(Object cswoPriority){this.put("cswoPriority", cswoPriority);return this;};
	 	/** and cswo_priority is null */
 		public M cswoPriorityNull(){if(this.get("cswoPriorityNot")==null)this.put("cswoPriorityNot", "");this.put("cswoPriority", null);return this;};
 		/** not .... */
 		public M cswoPriorityNot(){this.put("cswoPriorityNot", "not");return this;};
		/** and cswo_priority >= ? */
		public M cswoPriorityMin(Object min){this.put("cswoPriorityMin", min);return this;};
		/** and cswo_priority <= ? */
		public M cswoPriorityMax(Object max){this.put("cswoPriorityMax", max);return this;};
		/** 更改时间 [非空]       **/
		public M cswoTime(Object cswoTime){this.put("cswoTime", cswoTime);return this;};
	 	/** and cswo_time is null */
 		public M cswoTimeNull(){if(this.get("cswoTimeNot")==null)this.put("cswoTimeNot", "");this.put("cswoTime", null);return this;};
 		/** not .... */
 		public M cswoTimeNot(){this.put("cswoTimeNot", "not");return this;};
 		/** and cswo_time >= ? */
 		public M cswoTimeStart(Object start){this.put("cswoTimeStart", start);return this;};			
 		/** and cswo_time <= ? */
 		public M cswoTimeEnd(Object end){this.put("cswoTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M cswoStatus(Object cswoStatus){this.put("cswoStatus", cswoStatus);return this;};
	 	/** and cswo_status is null */
 		public M cswoStatusNull(){if(this.get("cswoStatusNot")==null)this.put("cswoStatusNot", "");this.put("cswoStatus", null);return this;};
 		/** not .... */
 		public M cswoStatusNot(){this.put("cswoStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有微信指令 **/
		public @api List<CsWxOrder> list(Integer size){
			return getCsWxOrderList(this,size);
		}
		/** 获取微信指令分页 **/
		public @api Page<CsWxOrder> page(int page,int size){
			return getCsWxOrderPage(page, size , this);
		}
		/** 根据查询条件取微信指令 **/
		public @api CsWxOrder get(){
			return getCsWxOrder(this);
		}
		/** 获取微信指令数 **/
		public @api Long count(){
			return getCsWxOrderCount(this);
		}
		/** 获取微信指令表达式 **/
		public @api <T> T eval(String strEval){
			return getCsWxOrderEval(strEval,this);
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
			updateCsWxOrder(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cswoId="cswoId";
		/** 指令名称 [非空]    模糊指令时，多个不同的词之前用|分隔   **/
		public final static @type(String.class) @like String cswoName="cswoName";
		/** 匹配类型 [非空]   0:精确匹配 1:模糊匹配     **/
		public final static @type(Short.class)  String cswoType="cswoType";
		/** 回复方式 [非空]   0:文字内容 1:图文内容 2:调用过程     **/
		public final static @type(Short.class)  String cswoRtype="cswoRtype";
		/** 文字内容        **/
		public final static @type(String.class) @like String cswoContent="cswoContent";
		/** 调用过程  [SrvProperty]  微信处理脚本:'WX_ORDER_CLASS'  预先设置好的过程处理  select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
		public final static @type(Long.class)  String cswoClass="cswoClass";
		/** 优先级        **/
		public final static @type(Integer.class)  String cswoPriority="cswoPriority";
		/** and cswo_priority >= ? */
		public final static @type(Integer.class) String cswoPriorityMin="cswoPriorityMin";
		/** and cswo_priority <= ? */
		public final static @type(Integer.class) String cswoPriorityMax="cswoPriorityMax";
		/** 更改时间 [非空]       **/
		public final static @type(Date.class)  String cswoTime="cswoTime";
	 	/** and cswo_time >= ? */
 		public final static @type(Date.class) String cswoTimeStart="cswoTimeStart";
 		/** and cswo_time <= ? */
 		public final static @type(Date.class) String cswoTimeEnd="cswoTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String cswoStatus="cswoStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cswoId="cswo_id";
		/** 指令名称 [非空]    模糊指令时，多个不同的词之前用|分隔   **/
		public final static String cswoName="cswo_name";
		/** 匹配类型 [非空]   0:精确匹配 1:模糊匹配     **/
		public final static String cswoType="cswo_type";
		/** 回复方式 [非空]   0:文字内容 1:图文内容 2:调用过程     **/
		public final static String cswoRtype="cswo_rtype";
		/** 文字内容        **/
		public final static String cswoContent="cswo_content";
		/** 调用过程  [SrvProperty]  微信处理脚本:'WX_ORDER_CLASS'  预先设置好的过程处理  select * from srv_property where sp_parent = (select sp_id from srv_property where sp_flag = ?) **/
		public final static String cswoClass="cswo_class";
		/** 优先级        **/
		public final static String cswoPriority="cswo_priority";
		/** 更改时间 [非空]       **/
		public final static String cswoTime="cswo_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String cswoStatus="cswo_status";
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
				$.Set(name,CsWxOrder.getCsWxOrder(params));
			else
				$.Set(name,CsWxOrder.getCsWxOrderList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取微信指令数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsWxOrder) $.GetRequest("CsWxOrder$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsWxOrder.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsWxOrder.getCsWxOrder(params);
			else
				value = CsWxOrder.getCsWxOrderList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsWxOrder.Get($.add(CsWxOrder.F.cswoId,param));
		else if(!$.empty(param.toString()))
			value = CsWxOrder.get(Long.valueOf(param.toString()));
		$.SetRequest("CsWxOrder$"+param.hashCode(), value);
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
	public void mergeSet(CsWxOrder old){
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