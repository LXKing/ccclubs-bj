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

@namespace("shop/order")
public @caption("商城订单") @table("cs_shop_order") class CsShopOrder implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csso_id")   @primary  @note("  ") Long cssoId;// 主键 非空     
	private @caption("所属会员") @column("csso_member")    @relate("$cssoMember") @RelateClass(CsMember.class)  @note("  ") Long cssoMember;// 非空     
 	private CsMember $cssoMember;//关联对象[会员帐号]
	private @caption("订单金额") @column("csso_money")    @note("  ") Double cssoMoney;// 非空     
	private @caption("实际金额") @column("csso_real_pay")    @note("  ") Double cssoRealPay;// 非空     
	private @caption("修改人") @column("csso_editor")    @relate("$cssoEditor") @RelateClass(SrvUser.class)  @note("  ") Long cssoEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $cssoEditor;//关联对象[用户]
	private @caption("备忘信息") @column("csso_memo")  @hidden   @note(" textarea:content text:sign  ") String cssoMemo;// textarea:content text:sign     
	private @caption("程序数据") @column("csso_data")    @note("  ") String cssoData;//     
	private @caption("修改时间") @column("csso_update_time")    @note("  ") Date cssoUpdateTime;// 非空     
	private @caption("添加时间") @column("csso_add_time")    @note("  ") Date cssoAddTime;// 非空     
	private @caption("状态") @column("csso_status")    @note(" 0:待付款 1:待发货 2:已完成 3:已撤销  ") Short cssoStatus;// 非空 0:待付款 1:待发货 2:已完成 3:已撤销     
	
	//默认构造函数
	public CsShopOrder(){
	
	}
	
	//主键构造函数
	public CsShopOrder(Long id){
		this.cssoId = id;
	}
	
	/**所有字段构造函数 CsShopOrder(cssoMember,cssoMoney,cssoRealPay,cssoEditor,cssoMemo,cssoData,cssoUpdateTime,cssoAddTime,cssoStatus)
	 CsShopOrder(
	 	$.getLong("cssoMember")//所属会员 [非空]
	 	,$.getDouble("cssoMoney")//订单金额 [非空]
	 	,$.getDouble("cssoRealPay")//实际金额 [非空]
	 	,$.getLong("cssoEditor")//修改人 [非空]
	 	,$.getString("cssoMemo")//备忘信息
	 	,$.getString("cssoData")//程序数据
	 	,$.getDate("cssoUpdateTime")//修改时间 [非空]
	 	,$.getDate("cssoAddTime")//添加时间 [非空]
	 	,$.getShort("cssoStatus")//状态 [非空]
	 )
	**/
	public CsShopOrder(Long cssoMember,Double cssoMoney,Double cssoRealPay,Long cssoEditor,String cssoMemo,String cssoData,Date cssoUpdateTime,Date cssoAddTime,Short cssoStatus){
		this.cssoMember=cssoMember;
		this.cssoMoney=cssoMoney;
		this.cssoRealPay=cssoRealPay;
		this.cssoEditor=cssoEditor;
		this.cssoMemo=cssoMemo;
		this.cssoData=cssoData;
		this.cssoUpdateTime=cssoUpdateTime;
		this.cssoAddTime=cssoAddTime;
		this.cssoStatus=cssoStatus;
	}
	
	//设置非空字段
	public CsShopOrder setNotNull(Long cssoMember,Double cssoMoney,Double cssoRealPay,Long cssoEditor,Date cssoUpdateTime,Date cssoAddTime,Short cssoStatus){
		this.cssoMember=cssoMember;
		this.cssoMoney=cssoMoney;
		this.cssoRealPay=cssoRealPay;
		this.cssoEditor=cssoEditor;
		this.cssoUpdateTime=cssoUpdateTime;
		this.cssoAddTime=cssoAddTime;
		this.cssoStatus=cssoStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsShopOrder cssoId(Long cssoId){
		this.cssoId = cssoId;
		this.setSeted(F.cssoId);
		return this;
	}
	/** 所属会员 [非空] [CsMember]      **/
	public CsShopOrder cssoMember(Long cssoMember){
		this.cssoMember = cssoMember;
		this.setSeted(F.cssoMember);
		return this;
	}
	/** 订单金额 [非空]       **/
	public CsShopOrder cssoMoney(Double cssoMoney){
		this.cssoMoney = cssoMoney;
		this.setSeted(F.cssoMoney);
		return this;
	}
	/** 实际金额 [非空]       **/
	public CsShopOrder cssoRealPay(Double cssoRealPay){
		this.cssoRealPay = cssoRealPay;
		this.setSeted(F.cssoRealPay);
		return this;
	}
	/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsShopOrder cssoEditor(Long cssoEditor){
		this.cssoEditor = cssoEditor;
		this.setSeted(F.cssoEditor);
		return this;
	}
	/** 备忘信息    textarea:content text:sign     **/
	public CsShopOrder cssoMemo(String cssoMemo){
		this.cssoMemo = cssoMemo;
		this.setSeted(F.cssoMemo);
		return this;
	}
	/** 程序数据        **/
	public CsShopOrder cssoData(String cssoData){
		this.cssoData = cssoData;
		this.setSeted(F.cssoData);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsShopOrder cssoUpdateTime(Date cssoUpdateTime){
		this.cssoUpdateTime = cssoUpdateTime;
		this.setSeted(F.cssoUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsShopOrder cssoAddTime(Date cssoAddTime){
		this.cssoAddTime = cssoAddTime;
		this.setSeted(F.cssoAddTime);
		return this;
	}
	/** 状态 [非空]   0:待付款 1:待发货 2:已完成 3:已撤销     **/
	public CsShopOrder cssoStatus(Short cssoStatus){
		this.cssoStatus = cssoStatus;
		this.setSeted(F.cssoStatus);
		return this;
	}
	
	
	//克隆对象
	public CsShopOrder clone(){
		CsShopOrder clone = new CsShopOrder();
		clone.cssoMember=this.cssoMember;
		clone.cssoMoney=this.cssoMoney;
		clone.cssoRealPay=this.cssoRealPay;
		clone.cssoEditor=this.cssoEditor;
		clone.cssoUpdateTime=this.cssoUpdateTime;
		clone.cssoAddTime=this.cssoAddTime;
		clone.cssoStatus=this.cssoStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取商城订单
	 * @param id
	 * @return
	 */
	public static @api CsShopOrder get(Long id){		
		return getCsShopOrderById(id);
	}
	/**
	 * 获取所有商城订单
	 * @return
	 */
	public static @api List<CsShopOrder> list(Map params,Integer size){
		return getCsShopOrderList(params,size);
	}
	/**
	 * 获取商城订单分页
	 * @return
	 */
	public static @api Page<CsShopOrder> page(int page,int size,Map params){
		return getCsShopOrderPage(page, size , params);
	}
	/**
	 * 根据查询条件取商城订单
	 * @param params
	 * @return
	 */
	public static @api CsShopOrder Get(Map params){
		return getCsShopOrder(params);
	}
	/**
	 * 获取商城订单数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsShopOrderCount(params);
	}
	/**
	 * 获取商城订单数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsShopOrderEval(eval,params);
	}
	
	/**
	 * 根据ID取商城订单
	 * @param id
	 * @return
	 */
	public static @api CsShopOrder getCsShopOrderById(Long id){		
		CsShopOrder csShopOrder = (CsShopOrder) $.GetRequest("CsShopOrder$"+id);
		if(csShopOrder!=null)
			return csShopOrder;
		com.ccclubs.service.admin.ICsShopOrderService csShopOrderService = $.GetSpringBean("csShopOrderService");		
		return csShopOrderService.getCsShopOrderById(id);
	}
	
	
	/**
	 * 根据ID取商城订单的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsShopOrder.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsShopOrder csShopOrder = get(id);
		if(csShopOrder!=null){
			String strValue = csShopOrder.getCssoId$();
			if(!"CssoId".equals("CssoId"))
				strValue+="("+csShopOrder.getCssoId$()+")";
			MemCache.setValue(CsShopOrder.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCssoId$();
		if(!"CssoId".equals("CssoId"))
			keyValue+="("+this.getCssoId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有商城订单
	 * @return
	 */
	public static @api List<CsShopOrder> getCsShopOrderList(Map params,Integer size){
		com.ccclubs.service.admin.ICsShopOrderService csShopOrderService = $.GetSpringBean("csShopOrderService");
		return csShopOrderService.getCsShopOrderList(params, size);
	}
	
	/**
	 * 获取商城订单分页
	 * @return
	 */
	public static @api Page<CsShopOrder> getCsShopOrderPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsShopOrderService csShopOrderService = $.GetSpringBean("csShopOrderService");
		return csShopOrderService.getCsShopOrderPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取商城订单
	 * @param params
	 * @return
	 */
	public static @api CsShopOrder getCsShopOrder(Map params){
		com.ccclubs.service.admin.ICsShopOrderService csShopOrderService = $.GetSpringBean("csShopOrderService");
		return csShopOrderService.getCsShopOrder(params);
	}
	
	/**
	 * 获取商城订单数
	 * @return
	 */
	public static @api Long getCsShopOrderCount(Map params){
		com.ccclubs.service.admin.ICsShopOrderService csShopOrderService = $.GetSpringBean("csShopOrderService");
		return csShopOrderService.getCsShopOrderCount(params);
	}
		
		
	/**
	 * 获取商城订单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsShopOrderEval(String eval,Map params){
		com.ccclubs.service.admin.ICsShopOrderService csShopOrderService = $.GetSpringBean("csShopOrderService");
		return csShopOrderService.getCsShopOrderEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsShopOrder(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsShopOrderService csShopOrderService = $.GetSpringBean("csShopOrderService");
		csShopOrderService.updateCsShopOrderByConfirm(set, where);
	}
	
	
	/**
	 * 保存商城订单对象
	 * @param params
	 * @return
	 */
	public CsShopOrder save(){
		com.ccclubs.service.admin.ICsShopOrderService csShopOrderService = $.GetSpringBean("csShopOrderService");
		if(this.getCssoId()!=null)
			csShopOrderService.updateCsShopOrder(this);
		else
			return csShopOrderService.saveCsShopOrder(this);
		return this;
	}
	
	
	/**
	 * 更新商城订单对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsShopOrderService csShopOrderService = $.GetSpringBean("csShopOrderService");
		csShopOrderService.updateCsShopOrder$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsShopOrderService csShopOrderService = $.GetSpringBean("csShopOrderService");
		if($.equals($.config("logic_delete"),"true"))
			csShopOrderService.removeCsShopOrderById(this.getCssoId());
		else
			csShopOrderService.deleteCsShopOrderById(this.getCssoId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsShopOrderService csShopOrderService = $.GetSpringBean("csShopOrderService");
		return csShopOrderService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCssoId(){
		return this.cssoId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCssoId$(){
		String strValue="";
		 strValue=$.str(this.getCssoId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCssoId(Long cssoId){
		this.cssoId = cssoId;
		this.setSeted(F.cssoId);
	}
	/*******************************所属会员**********************************/	
	/**
	* 所属会员 [非空] [CsMember]     
	**/
	public Long getCssoMember(){
		return this.cssoMember;
	}
	/**
	* 获取所属会员格式化(toString)
	**/
	public String getCssoMember$(){
		String strValue="";
		if(this.getCssoMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCssoMember()));
		}			
	 	 return strValue;
	}
	/**
	* 所属会员 [非空] [CsMember]     
	**/
	public void setCssoMember(Long cssoMember){
		this.cssoMember = cssoMember;
		this.setSeted(F.cssoMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$cssoMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCssoMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCssoMember()!=null){
 			csMember = CsMember.get(this.getCssoMember());
 		}
 		$.SetRequest("CsMember$"+this.getCssoMember(), csMember);
	 	return csMember;
	}
	/*******************************订单金额**********************************/	
	/**
	* 订单金额 [非空]      
	**/
	public Double getCssoMoney(){
		return this.cssoMoney;
	}
	/**
	* 获取订单金额格式化(toString)
	**/
	public String getCssoMoney$(){
		String strValue="";
		 strValue=$.str(this.getCssoMoney());	
	 	 return strValue;
	}
	/**
	* 订单金额 [非空]      
	**/
	public void setCssoMoney(Double cssoMoney){
		this.cssoMoney = cssoMoney;
		this.setSeted(F.cssoMoney);
	}
	/*******************************实际金额**********************************/	
	/**
	* 实际金额 [非空]      
	**/
	public Double getCssoRealPay(){
		return this.cssoRealPay;
	}
	/**
	* 获取实际金额格式化(toString)
	**/
	public String getCssoRealPay$(){
		String strValue="";
		 strValue=$.str(this.getCssoRealPay());	
	 	 return strValue;
	}
	/**
	* 实际金额 [非空]      
	**/
	public void setCssoRealPay(Double cssoRealPay){
		this.cssoRealPay = cssoRealPay;
		this.setSeted(F.cssoRealPay);
	}
	/*******************************修改人**********************************/	
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCssoEditor(){
		return this.cssoEditor;
	}
	/**
	* 获取修改人格式化(toString)
	**/
	public String getCssoEditor$(){
		String strValue="";
		if(this.getCssoEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCssoEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCssoEditor(Long cssoEditor){
		this.cssoEditor = cssoEditor;
		this.setSeted(F.cssoEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$cssoEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCssoEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCssoEditor()!=null){
 			srvUser = SrvUser.get(this.getCssoEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCssoEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************备忘信息**********************************/	
	/**
	* 备忘信息    textarea:content text:sign    
	**/
	public String getCssoMemo(){
		return this.cssoMemo;
	}
	/**
	* 获取备忘信息格式化(toString)
	**/
	public String getCssoMemo$(){
		String strValue="";
		 strValue=$.str(this.getCssoMemo());
	 	 return strValue;
	}
	/**
	* 备忘信息    textarea:content text:sign    
	**/
	public void setCssoMemo(String cssoMemo){
		this.cssoMemo = cssoMemo;
		this.setSeted(F.cssoMemo);
	}
	/*******************************程序数据**********************************/	
	/**
	* 程序数据       
	**/
	public String getCssoData(){
		return this.cssoData;
	}
	/**
	* 获取程序数据格式化(toString)
	**/
	public String getCssoData$(){
		String strValue="";
		 strValue=$.str(this.getCssoData());
	 	 return strValue;
	}
	/**
	* 程序数据       
	**/
	public void setCssoData(String cssoData){
		this.cssoData = cssoData;
		this.setSeted(F.cssoData);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCssoUpdateTime(){
		return this.cssoUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCssoUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssoUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCssoUpdateTime(Date cssoUpdateTime){
		this.cssoUpdateTime = cssoUpdateTime;
		this.setSeted(F.cssoUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCssoAddTime(){
		return this.cssoAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCssoAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCssoAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCssoAddTime(Date cssoAddTime){
		this.cssoAddTime = cssoAddTime;
		this.setSeted(F.cssoAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:待付款 1:待发货 2:已完成 3:已撤销    
	**/
	public Short getCssoStatus(){
		return this.cssoStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCssoStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCssoStatus()),"0"))
			strValue=$.str("待付款");		 
		 if($.equals($.str(this.getCssoStatus()),"1"))
			strValue=$.str("待发货");		 
		 if($.equals($.str(this.getCssoStatus()),"2"))
			strValue=$.str("已完成");		 
		 if($.equals($.str(this.getCssoStatus()),"3"))
			strValue=$.str("已撤销");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:待付款 1:待发货 2:已完成 3:已撤销    
	**/
	public void setCssoStatus(Short cssoStatus){
		this.cssoStatus = cssoStatus;
		this.setSeted(F.cssoStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsShopOrder.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsShopOrder.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsShopOrder.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsShopOrder.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsShopOrder.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsShopOrder.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsShopOrder.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsShopOrder.this);
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
		public M cssoId(Object cssoId){this.put("cssoId", cssoId);return this;};
	 	/** and csso_id is null */
 		public M cssoIdNull(){if(this.get("cssoIdNot")==null)this.put("cssoIdNot", "");this.put("cssoId", null);return this;};
 		/** not .... */
 		public M cssoIdNot(){this.put("cssoIdNot", "not");return this;};
		/** 所属会员 [非空] [CsMember]      **/
		public M cssoMember(Object cssoMember){this.put("cssoMember", cssoMember);return this;};
	 	/** and csso_member is null */
 		public M cssoMemberNull(){if(this.get("cssoMemberNot")==null)this.put("cssoMemberNot", "");this.put("cssoMember", null);return this;};
 		/** not .... */
 		public M cssoMemberNot(){this.put("cssoMemberNot", "not");return this;};
		public M cssoMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("cssoMember$on", value);
			return this;
		};	
		/** 订单金额 [非空]       **/
		public M cssoMoney(Object cssoMoney){this.put("cssoMoney", cssoMoney);return this;};
	 	/** and csso_money is null */
 		public M cssoMoneyNull(){if(this.get("cssoMoneyNot")==null)this.put("cssoMoneyNot", "");this.put("cssoMoney", null);return this;};
 		/** not .... */
 		public M cssoMoneyNot(){this.put("cssoMoneyNot", "not");return this;};
		/** and csso_money >= ? */
		public M cssoMoneyMin(Object min){this.put("cssoMoneyMin", min);return this;};
		/** and csso_money <= ? */
		public M cssoMoneyMax(Object max){this.put("cssoMoneyMax", max);return this;};
		/** 实际金额 [非空]       **/
		public M cssoRealPay(Object cssoRealPay){this.put("cssoRealPay", cssoRealPay);return this;};
	 	/** and csso_real_pay is null */
 		public M cssoRealPayNull(){if(this.get("cssoRealPayNot")==null)this.put("cssoRealPayNot", "");this.put("cssoRealPay", null);return this;};
 		/** not .... */
 		public M cssoRealPayNot(){this.put("cssoRealPayNot", "not");return this;};
		/** and csso_real_pay >= ? */
		public M cssoRealPayMin(Object min){this.put("cssoRealPayMin", min);return this;};
		/** and csso_real_pay <= ? */
		public M cssoRealPayMax(Object max){this.put("cssoRealPayMax", max);return this;};
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M cssoEditor(Object cssoEditor){this.put("cssoEditor", cssoEditor);return this;};
	 	/** and csso_editor is null */
 		public M cssoEditorNull(){if(this.get("cssoEditorNot")==null)this.put("cssoEditorNot", "");this.put("cssoEditor", null);return this;};
 		/** not .... */
 		public M cssoEditorNot(){this.put("cssoEditorNot", "not");return this;};
		/** 备忘信息    textarea:content text:sign     **/
		public M cssoMemo(Object cssoMemo){this.put("cssoMemo", cssoMemo);return this;};
	 	/** and csso_memo is null */
 		public M cssoMemoNull(){if(this.get("cssoMemoNot")==null)this.put("cssoMemoNot", "");this.put("cssoMemo", null);return this;};
 		/** not .... */
 		public M cssoMemoNot(){this.put("cssoMemoNot", "not");return this;};
		/** 程序数据        **/
		public M cssoData(Object cssoData){this.put("cssoData", cssoData);return this;};
	 	/** and csso_data is null */
 		public M cssoDataNull(){if(this.get("cssoDataNot")==null)this.put("cssoDataNot", "");this.put("cssoData", null);return this;};
 		/** not .... */
 		public M cssoDataNot(){this.put("cssoDataNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M cssoUpdateTime(Object cssoUpdateTime){this.put("cssoUpdateTime", cssoUpdateTime);return this;};
	 	/** and csso_update_time is null */
 		public M cssoUpdateTimeNull(){if(this.get("cssoUpdateTimeNot")==null)this.put("cssoUpdateTimeNot", "");this.put("cssoUpdateTime", null);return this;};
 		/** not .... */
 		public M cssoUpdateTimeNot(){this.put("cssoUpdateTimeNot", "not");return this;};
 		/** and csso_update_time >= ? */
 		public M cssoUpdateTimeStart(Object start){this.put("cssoUpdateTimeStart", start);return this;};			
 		/** and csso_update_time <= ? */
 		public M cssoUpdateTimeEnd(Object end){this.put("cssoUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cssoAddTime(Object cssoAddTime){this.put("cssoAddTime", cssoAddTime);return this;};
	 	/** and csso_add_time is null */
 		public M cssoAddTimeNull(){if(this.get("cssoAddTimeNot")==null)this.put("cssoAddTimeNot", "");this.put("cssoAddTime", null);return this;};
 		/** not .... */
 		public M cssoAddTimeNot(){this.put("cssoAddTimeNot", "not");return this;};
 		/** and csso_add_time >= ? */
 		public M cssoAddTimeStart(Object start){this.put("cssoAddTimeStart", start);return this;};			
 		/** and csso_add_time <= ? */
 		public M cssoAddTimeEnd(Object end){this.put("cssoAddTimeEnd", end);return this;};
		/** 状态 [非空]   0:待付款 1:待发货 2:已完成 3:已撤销     **/
		public M cssoStatus(Object cssoStatus){this.put("cssoStatus", cssoStatus);return this;};
	 	/** and csso_status is null */
 		public M cssoStatusNull(){if(this.get("cssoStatusNot")==null)this.put("cssoStatusNot", "");this.put("cssoStatus", null);return this;};
 		/** not .... */
 		public M cssoStatusNot(){this.put("cssoStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有商城订单 **/
		public @api List<CsShopOrder> list(Integer size){
			return getCsShopOrderList(this,size);
		}
		/** 获取商城订单分页 **/
		public @api Page<CsShopOrder> page(int page,int size){
			return getCsShopOrderPage(page, size , this);
		}
		/** 根据查询条件取商城订单 **/
		public @api CsShopOrder get(){
			return getCsShopOrder(this);
		}
		/** 获取商城订单数 **/
		public @api Long count(){
			return getCsShopOrderCount(this);
		}
		/** 获取商城订单表达式 **/
		public @api <T> T eval(String strEval){
			return getCsShopOrderEval(strEval,this);
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
			updateCsShopOrder(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cssoId="cssoId";
		/** 所属会员 [非空] [CsMember]      **/
		public final static @type(Long.class)  String cssoMember="cssoMember";
		/** 订单金额 [非空]       **/
		public final static @type(Double.class)  String cssoMoney="cssoMoney";
		/** and csso_money >= ? */
		public final static @type(Double.class) String cssoMoneyMin="cssoMoneyMin";
		/** and csso_money <= ? */
		public final static @type(Double.class) String cssoMoneyMax="cssoMoneyMax";
		/** 实际金额 [非空]       **/
		public final static @type(Double.class)  String cssoRealPay="cssoRealPay";
		/** and csso_real_pay >= ? */
		public final static @type(Double.class) String cssoRealPayMin="cssoRealPayMin";
		/** and csso_real_pay <= ? */
		public final static @type(Double.class) String cssoRealPayMax="cssoRealPayMax";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String cssoEditor="cssoEditor";
		/** 备忘信息    textarea:content text:sign     **/
		public final static @type(String.class) @like String cssoMemo="cssoMemo";
		/** 程序数据        **/
		public final static @type(String.class) @like String cssoData="cssoData";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cssoUpdateTime="cssoUpdateTime";
	 	/** and csso_update_time >= ? */
 		public final static @type(Date.class) String cssoUpdateTimeStart="cssoUpdateTimeStart";
 		/** and csso_update_time <= ? */
 		public final static @type(Date.class) String cssoUpdateTimeEnd="cssoUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cssoAddTime="cssoAddTime";
	 	/** and csso_add_time >= ? */
 		public final static @type(Date.class) String cssoAddTimeStart="cssoAddTimeStart";
 		/** and csso_add_time <= ? */
 		public final static @type(Date.class) String cssoAddTimeEnd="cssoAddTimeEnd";
		/** 状态 [非空]   0:待付款 1:待发货 2:已完成 3:已撤销     **/
		public final static @type(Short.class)  String cssoStatus="cssoStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cssoId="csso_id";
		/** 所属会员 [非空] [CsMember]      **/
		public final static String cssoMember="csso_member";
		/** 订单金额 [非空]       **/
		public final static String cssoMoney="csso_money";
		/** 实际金额 [非空]       **/
		public final static String cssoRealPay="csso_real_pay";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String cssoEditor="csso_editor";
		/** 备忘信息    textarea:content text:sign     **/
		public final static String cssoMemo="csso_memo";
		/** 程序数据        **/
		public final static String cssoData="csso_data";
		/** 修改时间 [非空]       **/
		public final static String cssoUpdateTime="csso_update_time";
		/** 添加时间 [非空]       **/
		public final static String cssoAddTime="csso_add_time";
		/** 状态 [非空]   0:待付款 1:待发货 2:已完成 3:已撤销     **/
		public final static String cssoStatus="csso_status";
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
				$.Set(name,CsShopOrder.getCsShopOrder(params));
			else
				$.Set(name,CsShopOrder.getCsShopOrderList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取商城订单数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsShopOrder) $.GetRequest("CsShopOrder$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsShopOrder.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsShopOrder.getCsShopOrder(params);
			else
				value = CsShopOrder.getCsShopOrderList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsShopOrder.Get($.add(CsShopOrder.F.cssoId,param));
		else if(!$.empty(param.toString()))
			value = CsShopOrder.get(Long.valueOf(param.toString()));
		$.SetRequest("CsShopOrder$"+param.hashCode(), value);
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
	public void mergeSet(CsShopOrder old){
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