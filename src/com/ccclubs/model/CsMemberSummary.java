package com.ccclubs.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.ccclubs.model.CsSpecialCar.M;
import com.lazy3q.web.helper.$;

import com.lazy3q.web.tags.BodyTag;

import com.lazy3q.web.util.Page;
import com.lazy3q.lang.*;
import com.lazy3q.util.Function;
import com.ccclubs.dao.impl.MemCache;

@namespace("user/member")
public @caption("会员汇总项") @table("cs_member_summary") class CsMemberSummary implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csms_id")   @primary   @note("  ") Long csmsId;
	private @caption("会员") @column("csms_member")    @relate("$csmsMember") @RelateClass(CsMember.class)  @note("  ") Long csmsMember;
	private CsMember $csmsMember;//关联对象[会员]
	private @caption("个人订单总数") @column("csms_ord_total")      @note("  ") Integer csmsOrdTotal;
	private @caption("公务订单总数") @column("csms_unitord_total")      @note("  ") Integer csmsUnitordTotal;
	private @caption("个人订单总时长") @column("csms_ord_timelong")      @note("  ") Integer csmsOrdTimelong;
	private @caption("公务订单总时长") @column("csms_unitord_timelong")      @note("  ") Integer csmsUnitordTimelong;
	private @caption("个人订单总里程") @column("csms_ord_mileages")      @note("  ") Double csmsOrdMileages;
	private @caption("公务订单总里程") @column("csms_unitord_mileages")      @note("  ") Double csmsUnitordMileages;
	private @caption("个人订单总金额") @column("csms_ord_amount")      @note("  ") Double csmsOrdAmount;
	private @caption("公务订单总金额") @column("csms_unitord_amount")      @note("  ") Double csmsUnitordAmount;
	private @caption("创建时间") @column("csms_addtime")      @note("  ") Date csmsAddtime;
	private @caption("修改时间") @column("csms_updatetime")      @note("  ") Date csmsUpdatetime;
	
	//默认构造函数
	public CsMemberSummary(){
	
	}
	
	//主键构造函数
	public CsMemberSummary(Long id){
		this.csmsId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsMemberSummary(Long csmsMember,Integer csmsOrdTotal,Integer csmsUnitordTotal,Integer csmsOrdTimelong,Integer csmsUnitordTimelong,Double csmsOrdMileages,Double csmsUnitordMileages,Double csmsOrdAmount,Double csmsUnitordAmount,Date csmsAddtime,Date csmsUpdatetime){
		this.csmsMember=csmsMember;
		this.csmsOrdTotal=csmsOrdTotal;
		this.csmsUnitordTotal=csmsUnitordTotal;
		this.csmsOrdTimelong=csmsOrdTimelong;
		this.csmsUnitordTimelong=csmsUnitordTimelong;
		this.csmsOrdMileages=csmsOrdMileages;
		this.csmsUnitordMileages=csmsUnitordMileages;
		this.csmsOrdAmount=csmsOrdAmount;
		this.csmsUnitordAmount=csmsUnitordAmount;
		this.csmsAddtime=csmsAddtime;
		this.csmsUpdatetime=csmsUpdatetime;
	}
	
	//设置非空字段
	public CsMemberSummary setNotNull(Long csmsMember){
		this.csmsMember=csmsMember;
		return this;
	}
	
	/** 编号 [非空]       **/
	public CsMemberSummary csmsId(Long csmsId){
		this.csmsId = csmsId;
		this.setSeted(F.csmsId);
		return this;
	}
	/** 会员 [可空]       **/
	public CsMemberSummary csmsMember(Long csmsMember){
		this.csmsMember = csmsMember;
		this.setSeted(F.csmsMember);
		return this;
	}
	/** 个人订单总数 [非空]       **/
	public CsMemberSummary csmsOrdTotal(Integer csmsOrdTotal){
		this.csmsOrdTotal = csmsOrdTotal;
		this.setSeted(F.csmsOrdTotal);
		return this;
	}
	/** 公务订单总数 [非空]       **/
	public CsMemberSummary csmsUnitordTotal(Integer csmsUnitordTotal){
		this.csmsUnitordTotal = csmsUnitordTotal;
		this.setSeted(F.csmsUnitordTotal);
		return this;
	}
	/** 个人订单总时长 [非空]       **/
	public CsMemberSummary csmsOrdTimelong(Integer csmsOrdTimelong){
		this.csmsOrdTimelong = csmsOrdTimelong;
		this.setSeted(F.csmsOrdTimelong);
		return this;
	}
	/** 公务订单总时长 [非空]       **/
	public CsMemberSummary csmsUnitordTimelong(Integer csmsUnitordTimelong){
		this.csmsUnitordTimelong = csmsUnitordTimelong;
		this.setSeted(F.csmsUnitordTimelong);
		return this;
	}
	/** 个人订单总里程 [非空]       **/
	public CsMemberSummary csmsOrdMileages(Double csmsOrdMileages){
		this.csmsOrdMileages = csmsOrdMileages;
		this.setSeted(F.csmsOrdMileages);
		return this;
	}
	/** 公务订单总里程 [非空]       **/
	public CsMemberSummary csmsUnitordMileages(Double csmsUnitordMileages){
		this.csmsUnitordMileages = csmsUnitordMileages;
		this.setSeted(F.csmsUnitordMileages);
		return this;
	}
	/** 个人订单总金额 [非空]       **/
	public CsMemberSummary csmsOrdAmount(Double csmsOrdAmount){
		this.csmsOrdAmount = csmsOrdAmount;
		this.setSeted(F.csmsOrdAmount);
		return this;
	}
	/** 公务订单总金额 [非空]       **/
	public CsMemberSummary csmsUnitordAmount(Double csmsUnitordAmount){
		this.csmsUnitordAmount = csmsUnitordAmount;
		this.setSeted(F.csmsUnitordAmount);
		return this;
	}
	/** 创建时间 [非空]       **/
	public CsMemberSummary csmsAddtime(Date csmsAddtime){
		this.csmsAddtime = csmsAddtime;
		this.setSeted(F.csmsAddtime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsMemberSummary csmsUpdatetime(Date csmsUpdatetime){
		this.csmsUpdatetime = csmsUpdatetime;
		this.setSeted(F.csmsUpdatetime);
		return this;
	}
	
	//克隆对象
	public CsMemberSummary clone(){
		CsMemberSummary clone = new CsMemberSummary();
		clone.csmsId=this.csmsId;
		clone.csmsMember=this.csmsMember;
		clone.csmsOrdTotal=this.csmsOrdTotal;
		clone.csmsUnitordTotal=this.csmsUnitordTotal;
		clone.csmsOrdTimelong=this.csmsOrdTimelong;
		clone.csmsUnitordTimelong=this.csmsUnitordTimelong;
		clone.csmsOrdMileages=this.csmsOrdMileages;
		clone.csmsUnitordMileages=this.csmsUnitordMileages;
		clone.csmsOrdAmount=this.csmsOrdAmount;
		clone.csmsUnitordAmount=this.csmsUnitordAmount;
		clone.csmsAddtime=this.csmsAddtime;
		clone.csmsUpdatetime=this.csmsUpdatetime;
		return clone;
	}
	
	
	/**
	 * 根据ID取会员汇总项
	 * @param id
	 * @return
	 */
	public static @api CsMemberSummary get(Long id){		
		return getCsMemberSummaryById(id);
	}
	/**
	 * 获取所有会员汇总项
	 * @return
	 */
	public static @api List<CsMemberSummary> list(Map params,Integer size){
		return getCsMemberSummaryList(params,size);
	}
	/**
	 * 获取会员汇总项分页
	 * @return
	 */
	public static @api Page<CsMemberSummary> page(int page,int size,Map params){
		return getCsMemberSummaryPage(page, size , params);
	}
	/**
	 * 根据查询条件取会员汇总项
	 * @param params
	 * @return
	 */
	public static @api CsMemberSummary Get(Map params){
		return getCsMemberSummary(params);
	}
	/**
	 * 获取会员汇总项数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsMemberSummaryCount(params);
	}
	/**
	 * 获取会员汇总项数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsMemberSummaryEval(eval,params);
	}
	
	/**
	 * 根据ID取会员汇总项
	 * @param id
	 * @return
	 */
	public static @api CsMemberSummary getCsMemberSummaryById(Long id){		
		CsMemberSummary csMemberSummary = (CsMemberSummary) $.GetRequest("CsMemberSummary$"+id);
		if(csMemberSummary!=null)
			return csMemberSummary;
		com.ccclubs.service.admin.ICsMemberSummaryService csMemberSummaryService = $.GetSpringBean("csMemberSummaryService");		
		return csMemberSummaryService.getCsMemberSummaryById(id);
	}
	
	
	/**
	 * 根据ID取会员汇总项的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsMemberSummary.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsMemberSummary csMemberSummary = get(id);
		if(csMemberSummary!=null){
			String strValue = csMemberSummary.getCsmsId$();
			if(!"CsmsId".equals("CsmsId"))
				strValue+="("+csMemberSummary.getCsmsId$()+")";
			MemCache.setValue(CsMemberSummary.class, id ,strValue);
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
	 * 获取所有会员汇总项
	 * @return
	 */
	public static @api List<CsMemberSummary> getCsMemberSummaryList(Map params,Integer size){
		com.ccclubs.service.admin.ICsMemberSummaryService csMemberSummaryService = $.GetSpringBean("csMemberSummaryService");
		return csMemberSummaryService.getCsMemberSummaryList(params, size);
	}
	
	/**
	 * 获取会员汇总项分页
	 * @return
	 */
	public static @api Page<CsMemberSummary> getCsMemberSummaryPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsMemberSummaryService csMemberSummaryService = $.GetSpringBean("csMemberSummaryService");
		return csMemberSummaryService.getCsMemberSummaryPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取会员汇总项
	 * @param params
	 * @return
	 */
	public static @api CsMemberSummary getCsMemberSummary(Map params){
		com.ccclubs.service.admin.ICsMemberSummaryService csMemberSummaryService = $.GetSpringBean("csMemberSummaryService");
		return csMemberSummaryService.getCsMemberSummary(params);
	}
	
	/**
	 * 获取会员汇总项数
	 * @return
	 */
	public static @api Long getCsMemberSummaryCount(Map params){
		com.ccclubs.service.admin.ICsMemberSummaryService csMemberSummaryService = $.GetSpringBean("csMemberSummaryService");
		return csMemberSummaryService.getCsMemberSummaryCount(params);
	}
		
		
	/**
	 * 获取会员汇总项自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsMemberSummaryEval(String eval,Map params){
		com.ccclubs.service.admin.ICsMemberSummaryService csMemberSummaryService = $.GetSpringBean("csMemberSummaryService");
		return csMemberSummaryService.getCsMemberSummaryEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsMemberSummary(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsMemberSummaryService csMemberSummaryService = $.GetSpringBean("csMemberSummaryService");
		csMemberSummaryService.updateCsMemberSummaryByConfirm(set, where);
	}
	
	
	/**
	 * 保存会员汇总项对象
	 * @param params
	 * @return
	 */
	public CsMemberSummary save(){
		com.ccclubs.service.admin.ICsMemberSummaryService csMemberSummaryService = $.GetSpringBean("csMemberSummaryService");
		if(this.getCsmsId()!=null)
			csMemberSummaryService.updateCsMemberSummary(this);
		else
			return csMemberSummaryService.saveCsMemberSummary(this);
		return this;
	}
	
	
	/**
	 * 更新会员汇总项对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsMemberSummaryService csMemberSummaryService = $.GetSpringBean("csMemberSummaryService");
		csMemberSummaryService.updateCsMemberSummary$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsMemberSummaryService csMemberSummaryService = $.GetSpringBean("csMemberSummaryService");
		if($.equals($.config("logic_delete"),"true"))
			csMemberSummaryService.removeCsMemberSummaryById(this.getCsmsId());
		else
			csMemberSummaryService.deleteCsMemberSummaryById(this.getCsmsId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsMemberSummaryService csMemberSummaryService = $.GetSpringBean("csMemberSummaryService");
		return csMemberSummaryService.executeTransaction(function);
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
	/*******************************会员**********************************/	
	/**
	* 会员 [可空]      
	**/
	public Long getCsmsMember(){
		return this.csmsMember;
	}
	/**
	* 获取会员格式化(toString)
	**/
	public String getCsmsMember$(){
		String strValue="";
		if(this.getCsmsMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsmsMember()));
		}			
	 	 return strValue;
	}
	/**
	* 会员 [可空]     
	**/
	public void setCsmsMember(Long csmsMember){
		this.csmsMember = csmsMember;
		this.setSeted(F.csmsMember);
	}
	/**
	* 获取关联对象[会员]
	**/	 			
 	public CsMember get$csmsMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsmsMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsmsMember()!=null){
			csMember = CsMember.get(this.getCsmsMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsmsMember(), csMember);
	 	return csMember;
	}
	/*******************************个人订单总数**********************************/	
	/**
	* 个人订单总数 [非空]      
	**/
	public Integer getCsmsOrdTotal(){
		return this.csmsOrdTotal;
	}
	/**
	* 获取个人订单总数格式化(toString)
	**/
	public String getCsmsOrdTotal$(){
		String strValue="";
		 strValue=$.str(this.getCsmsOrdTotal());
	 	 return strValue;
	}
	/**
	* 个人订单总数 [非空]     
	**/
	public void setCsmsOrdTotal(Integer csmsOrdTotal){
		this.csmsOrdTotal = csmsOrdTotal;
		this.setSeted(F.csmsOrdTotal);
	}
	/*******************************公务订单总数**********************************/	
	/**
	* 公务订单总数 [非空]      
	**/
	public Integer getCsmsUnitordTotal(){
		return this.csmsUnitordTotal;
	}
	/**
	* 获取公务订单总数格式化(toString)
	**/
	public String getCsmsUnitordTotal$(){
		String strValue="";
		 strValue=$.str(this.getCsmsUnitordTotal());
	 	 return strValue;
	}
	/**
	* 公务订单总数 [非空]     
	**/
	public void setCsmsUnitordTotal(Integer csmsUnitordTotal){
		this.csmsUnitordTotal = csmsUnitordTotal;
		this.setSeted(F.csmsUnitordTotal);
	}
	/*******************************个人订单总时长**********************************/	
	/**
	* 个人订单总时长 [非空]      
	**/
	public Integer getCsmsOrdTimelong(){
		return this.csmsOrdTimelong;
	}
	/**
	* 获取个人订单总时长格式化(toString)
	**/
	public String getCsmsOrdTimelong$(){
		String strValue="";
		 strValue=$.str(this.getCsmsOrdTimelong());
	 	 return strValue;
	}
	/**
	* 个人订单总时长 [非空]     
	**/
	public void setCsmsOrdTimelong(Integer csmsOrdTimelong){
		this.csmsOrdTimelong = csmsOrdTimelong;
		this.setSeted(F.csmsOrdTimelong);
	}
	/*******************************公务订单总时长**********************************/	
	/**
	* 公务订单总时长 [非空]      
	**/
	public Integer getCsmsUnitordTimelong(){
		return this.csmsUnitordTimelong;
	}
	/**
	* 获取公务订单总时长格式化(toString)
	**/
	public String getCsmsUnitordTimelong$(){
		String strValue="";
		 strValue=$.str(this.getCsmsUnitordTimelong());
	 	 return strValue;
	}
	/**
	* 公务订单总时长 [非空]     
	**/
	public void setCsmsUnitordTimelong(Integer csmsUnitordTimelong){
		this.csmsUnitordTimelong = csmsUnitordTimelong;
		this.setSeted(F.csmsUnitordTimelong);
	}
	/*******************************个人订单总里程**********************************/	
	/**
	* 个人订单总里程 [非空]      
	**/
	public Double getCsmsOrdMileages(){
		return this.csmsOrdMileages;
	}
	/**
	* 获取个人订单总里程格式化(toString)
	**/
	public String getCsmsOrdMileages$(){
		String strValue="";
		 strValue=$.str(this.getCsmsOrdMileages());
	 	 return strValue;
	}
	/**
	* 个人订单总里程 [非空]     
	**/
	public void setCsmsOrdMileages(Double csmsOrdMileages){
		this.csmsOrdMileages = csmsOrdMileages;
		this.setSeted(F.csmsOrdMileages);
	}
	/*******************************公务订单总里程**********************************/	
	/**
	* 公务订单总里程 [非空]      
	**/
	public Double getCsmsUnitordMileages(){
		return this.csmsUnitordMileages;
	}
	/**
	* 获取公务订单总里程格式化(toString)
	**/
	public String getCsmsUnitordMileages$(){
		String strValue="";
		 strValue=$.str(this.getCsmsUnitordMileages());
	 	 return strValue;
	}
	/**
	* 公务订单总里程 [非空]     
	**/
	public void setCsmsUnitordMileages(Double csmsUnitordMileages){
		this.csmsUnitordMileages = csmsUnitordMileages;
		this.setSeted(F.csmsUnitordMileages);
	}
	/*******************************个人订单总金额**********************************/	
	/**
	* 个人订单总金额 [非空]      
	**/
	public Double getCsmsOrdAmount(){
		return this.csmsOrdAmount;
	}
	/**
	* 获取个人订单总金额格式化(toString)
	**/
	public String getCsmsOrdAmount$(){
		String strValue="";
		 strValue=$.str(this.getCsmsOrdAmount());
	 	 return strValue;
	}
	/**
	* 个人订单总金额 [非空]     
	**/
	public void setCsmsOrdAmount(Double csmsOrdAmount){
		this.csmsOrdAmount = csmsOrdAmount;
		this.setSeted(F.csmsOrdAmount);
	}
	/*******************************公务订单总金额**********************************/	
	/**
	* 公务订单总金额 [非空]      
	**/
	public Double getCsmsUnitordAmount(){
		return this.csmsUnitordAmount;
	}
	/**
	* 获取公务订单总金额格式化(toString)
	**/
	public String getCsmsUnitordAmount$(){
		String strValue="";
		 strValue=$.str(this.getCsmsUnitordAmount());
	 	 return strValue;
	}
	/**
	* 公务订单总金额 [非空]     
	**/
	public void setCsmsUnitordAmount(Double csmsUnitordAmount){
		this.csmsUnitordAmount = csmsUnitordAmount;
		this.setSeted(F.csmsUnitordAmount);
	}
	/*******************************创建时间**********************************/	
	/**
	* 创建时间 [非空]      
	**/
	public Date getCsmsAddtime(){
		return this.csmsAddtime;
	}
	/**
	* 获取创建时间格式化(toString)
	**/
	public String getCsmsAddtime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmsAddtime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 创建时间 [非空]     
	**/
	public void setCsmsAddtime(Date csmsAddtime){
		this.csmsAddtime = csmsAddtime;
		this.setSeted(F.csmsAddtime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsmsUpdatetime(){
		return this.csmsUpdatetime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsmsUpdatetime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsmsUpdatetime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCsmsUpdatetime(Date csmsUpdatetime){
		this.csmsUpdatetime = csmsUpdatetime;
		this.setSeted(F.csmsUpdatetime);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsMemberSummary.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMemberSummary.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMemberSummary.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsMemberSummary.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsMemberSummary.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsMemberSummary.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsMemberSummary.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsMemberSummary.this);
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
		/** and csms_id >= ? */
		public M csmsIdMin(Object min){this.put("csmsIdMin", min);return this;};
		/** and csms_id <= ? */
		public M csmsIdMax(Object max){this.put("csmsIdMax", max);return this;};
		/** 会员 [可空]       **/
		public M csmsMember(Object csmsMember){this.put("csmsMember", csmsMember);return this;};
		/** and csms_member is null */
		public M csmsMemberNull(){if(this.get("csmsMemberNot")==null)this.put("csmsMemberNot", "");this.put("csmsMember", null);return this;};
		/** not .... */
		public M csmsMemberNot(){this.put("csmsMemberNot", "not");return this;};
		public M csmsMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csmsMember$on", value);
			return this;
		};	
		/** and csms_member >= ? */
		public M csmsMemberMin(Object min){this.put("csmsMemberMin", min);return this;};
		/** and csms_member <= ? */
		public M csmsMemberMax(Object max){this.put("csmsMemberMax", max);return this;};
		/** 个人订单总数 [非空]       **/
		public M csmsOrdTotal(Object csmsOrdTotal){this.put("csmsOrdTotal", csmsOrdTotal);return this;};
		/** and csms_ord_total is null */
		public M csmsOrdTotalNull(){if(this.get("csmsOrdTotalNot")==null)this.put("csmsOrdTotalNot", "");this.put("csmsOrdTotal", null);return this;};
		/** not .... */
		public M csmsOrdTotalNot(){this.put("csmsOrdTotalNot", "not");return this;};
		/** and csms_ord_total >= ? */
		public M csmsOrdTotalMin(Object min){this.put("csmsOrdTotalMin", min);return this;};
		/** and csms_ord_total <= ? */
		public M csmsOrdTotalMax(Object max){this.put("csmsOrdTotalMax", max);return this;};
		/** 公务订单总数 [非空]       **/
		public M csmsUnitordTotal(Object csmsUnitordTotal){this.put("csmsUnitordTotal", csmsUnitordTotal);return this;};
		/** and csms_unitord_total is null */
		public M csmsUnitordTotalNull(){if(this.get("csmsUnitordTotalNot")==null)this.put("csmsUnitordTotalNot", "");this.put("csmsUnitordTotal", null);return this;};
		/** not .... */
		public M csmsUnitordTotalNot(){this.put("csmsUnitordTotalNot", "not");return this;};
		/** and csms_unitord_total >= ? */
		public M csmsUnitordTotalMin(Object min){this.put("csmsUnitordTotalMin", min);return this;};
		/** and csms_unitord_total <= ? */
		public M csmsUnitordTotalMax(Object max){this.put("csmsUnitordTotalMax", max);return this;};
		/** 个人订单总时长 [非空]       **/
		public M csmsOrdTimelong(Object csmsOrdTimelong){this.put("csmsOrdTimelong", csmsOrdTimelong);return this;};
		/** and csms_ord_timelong is null */
		public M csmsOrdTimelongNull(){if(this.get("csmsOrdTimelongNot")==null)this.put("csmsOrdTimelongNot", "");this.put("csmsOrdTimelong", null);return this;};
		/** not .... */
		public M csmsOrdTimelongNot(){this.put("csmsOrdTimelongNot", "not");return this;};
		/** and csms_ord_timelong >= ? */
		public M csmsOrdTimelongMin(Object min){this.put("csmsOrdTimelongMin", min);return this;};
		/** and csms_ord_timelong <= ? */
		public M csmsOrdTimelongMax(Object max){this.put("csmsOrdTimelongMax", max);return this;};
		/** 公务订单总时长 [非空]       **/
		public M csmsUnitordTimelong(Object csmsUnitordTimelong){this.put("csmsUnitordTimelong", csmsUnitordTimelong);return this;};
		/** and csms_unitord_timelong is null */
		public M csmsUnitordTimelongNull(){if(this.get("csmsUnitordTimelongNot")==null)this.put("csmsUnitordTimelongNot", "");this.put("csmsUnitordTimelong", null);return this;};
		/** not .... */
		public M csmsUnitordTimelongNot(){this.put("csmsUnitordTimelongNot", "not");return this;};
		/** and csms_unitord_timelong >= ? */
		public M csmsUnitordTimelongMin(Object min){this.put("csmsUnitordTimelongMin", min);return this;};
		/** and csms_unitord_timelong <= ? */
		public M csmsUnitordTimelongMax(Object max){this.put("csmsUnitordTimelongMax", max);return this;};
		/** 个人订单总里程 [非空]       **/
		public M csmsOrdMileages(Object csmsOrdMileages){this.put("csmsOrdMileages", csmsOrdMileages);return this;};
		/** and csms_ord_mileages is null */
		public M csmsOrdMileagesNull(){if(this.get("csmsOrdMileagesNot")==null)this.put("csmsOrdMileagesNot", "");this.put("csmsOrdMileages", null);return this;};
		/** not .... */
		public M csmsOrdMileagesNot(){this.put("csmsOrdMileagesNot", "not");return this;};
		/** and csms_ord_mileages >= ? */
		public M csmsOrdMileagesMin(Object min){this.put("csmsOrdMileagesMin", min);return this;};
		/** and csms_ord_mileages <= ? */
		public M csmsOrdMileagesMax(Object max){this.put("csmsOrdMileagesMax", max);return this;};
		/** 公务订单总里程 [非空]       **/
		public M csmsUnitordMileages(Object csmsUnitordMileages){this.put("csmsUnitordMileages", csmsUnitordMileages);return this;};
		/** and csms_unitord_mileages is null */
		public M csmsUnitordMileagesNull(){if(this.get("csmsUnitordMileagesNot")==null)this.put("csmsUnitordMileagesNot", "");this.put("csmsUnitordMileages", null);return this;};
		/** not .... */
		public M csmsUnitordMileagesNot(){this.put("csmsUnitordMileagesNot", "not");return this;};
		/** and csms_unitord_mileages >= ? */
		public M csmsUnitordMileagesMin(Object min){this.put("csmsUnitordMileagesMin", min);return this;};
		/** and csms_unitord_mileages <= ? */
		public M csmsUnitordMileagesMax(Object max){this.put("csmsUnitordMileagesMax", max);return this;};
		/** 个人订单总金额 [非空]       **/
		public M csmsOrdAmount(Object csmsOrdAmount){this.put("csmsOrdAmount", csmsOrdAmount);return this;};
		/** and csms_ord_amount is null */
		public M csmsOrdAmountNull(){if(this.get("csmsOrdAmountNot")==null)this.put("csmsOrdAmountNot", "");this.put("csmsOrdAmount", null);return this;};
		/** not .... */
		public M csmsOrdAmountNot(){this.put("csmsOrdAmountNot", "not");return this;};
		/** and csms_ord_amount >= ? */
		public M csmsOrdAmountMin(Object min){this.put("csmsOrdAmountMin", min);return this;};
		/** and csms_ord_amount <= ? */
		public M csmsOrdAmountMax(Object max){this.put("csmsOrdAmountMax", max);return this;};
		/** 公务订单总金额 [非空]       **/
		public M csmsUnitordAmount(Object csmsUnitordAmount){this.put("csmsUnitordAmount", csmsUnitordAmount);return this;};
		/** and csms_unitord_amount is null */
		public M csmsUnitordAmountNull(){if(this.get("csmsUnitordAmountNot")==null)this.put("csmsUnitordAmountNot", "");this.put("csmsUnitordAmount", null);return this;};
		/** not .... */
		public M csmsUnitordAmountNot(){this.put("csmsUnitordAmountNot", "not");return this;};
		/** and csms_unitord_amount >= ? */
		public M csmsUnitordAmountMin(Object min){this.put("csmsUnitordAmountMin", min);return this;};
		/** and csms_unitord_amount <= ? */
		public M csmsUnitordAmountMax(Object max){this.put("csmsUnitordAmountMax", max);return this;};
		/** 创建时间 [非空]       **/
		public M csmsAddtime(Object csmsAddtime){this.put("csmsAddtime", csmsAddtime);return this;};
		/** and csms_addtime is null */
		public M csmsAddtimeNull(){if(this.get("csmsAddtimeNot")==null)this.put("csmsAddtimeNot", "");this.put("csmsAddtime", null);return this;};
		/** not .... */
		public M csmsAddtimeNot(){this.put("csmsAddtimeNot", "not");return this;};
		/** and csms_addtime >= ? */
 		public M csmsAddtimeStart(Object start){this.put("csmsAddtimeStart", start);return this;};			
 		/** and csms_addtime <= ? */
 		public M csmsAddtimeEnd(Object end){this.put("csmsAddtimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M csmsUpdatetime(Object csmsUpdatetime){this.put("csmsUpdatetime", csmsUpdatetime);return this;};
		/** and csms_updatetime is null */
		public M csmsUpdatetimeNull(){if(this.get("csmsUpdatetimeNot")==null)this.put("csmsUpdatetimeNot", "");this.put("csmsUpdatetime", null);return this;};
		/** not .... */
		public M csmsUpdatetimeNot(){this.put("csmsUpdatetimeNot", "not");return this;};
		/** and csms_updatetime >= ? */
 		public M csmsUpdatetimeStart(Object start){this.put("csmsUpdatetimeStart", start);return this;};			
 		/** and csms_updatetime <= ? */
 		public M csmsUpdatetimeEnd(Object end){this.put("csmsUpdatetimeEnd", end);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有会员汇总项 **/
		public @api List<CsMemberSummary> list(Integer size){
			return getCsMemberSummaryList(this,size);
		}
		/** 获取会员汇总项分页 **/
		public @api Page<CsMemberSummary> page(int page,int size){
			return getCsMemberSummaryPage(page, size , this);
		}
		/** 根据查询条件取会员汇总项 **/
		public @api CsMemberSummary get(){
			return getCsMemberSummary(this);
		}
		/** 获取会员汇总项数 **/
		public @api Long count(){
			return getCsMemberSummaryCount(this);
		}
		/** 获取会员汇总项表达式 **/
		public @api <T> T eval(String strEval){
			return getCsMemberSummaryEval(strEval,this);
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
			updateCsMemberSummary(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csmsId="csmsId";
		/** 会员 [可空]       **/
		public final static @type(Long.class)  String csmsMember="csmsMember";
		/** 个人订单总数 [非空]       **/
		public final static @type(Integer.class)  String csmsOrdTotal="csmsOrdTotal";
		/** 公务订单总数 [非空]       **/
		public final static @type(Integer.class)  String csmsUnitordTotal="csmsUnitordTotal";
		/** 个人订单总时长 [非空]       **/
		public final static @type(Integer.class)  String csmsOrdTimelong="csmsOrdTimelong";
		/** 公务订单总时长 [非空]       **/
		public final static @type(Integer.class)  String csmsUnitordTimelong="csmsUnitordTimelong";
		/** 个人订单总里程 [非空]       **/
		public final static @type(Double.class)  String csmsOrdMileages="csmsOrdMileages";
		/** 公务订单总里程 [非空]       **/
		public final static @type(Double.class)  String csmsUnitordMileages="csmsUnitordMileages";
		/** 个人订单总金额 [非空]       **/
		public final static @type(Double.class)  String csmsOrdAmount="csmsOrdAmount";
		/** 公务订单总金额 [非空]       **/
		public final static @type(Double.class)  String csmsUnitordAmount="csmsUnitordAmount";
		/** 创建时间 [非空]       **/
		public final static @type(Date.class)  String csmsAddtime="csmsAddtime";
		/** and csms_addtime >= ? */
 		public final static @type(Date.class) String csmsAddtimeStart="csmsAddtimeStart";
 		/** and csms_addtime <= ? */
 		public final static @type(Date.class) String csmsAddtimeEnd="csmsAddtimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csmsUpdatetime="csmsUpdatetime";
		/** and csms_updatetime >= ? */
 		public final static @type(Date.class) String csmsUpdatetimeStart="csmsUpdatetimeStart";
 		/** and csms_updatetime <= ? */
 		public final static @type(Date.class) String csmsUpdatetimeEnd="csmsUpdatetimeEnd";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csmsId="csms_id";
		/** 会员 [可空]       **/
		public final static String csmsMember="csms_member";
		/** 个人订单总数 [非空]       **/
		public final static String csmsOrdTotal="csms_ord_total";
		/** 公务订单总数 [非空]       **/
		public final static String csmsUnitordTotal="csms_unitord_total";
		/** 个人订单总时长 [非空]       **/
		public final static String csmsOrdTimelong="csms_ord_timelong";
		/** 公务订单总时长 [非空]       **/
		public final static String csmsUnitordTimelong="csms_unitord_timelong";
		/** 个人订单总里程 [非空]       **/
		public final static String csmsOrdMileages="csms_ord_mileages";
		/** 公务订单总里程 [非空]       **/
		public final static String csmsUnitordMileages="csms_unitord_mileages";
		/** 个人订单总金额 [非空]       **/
		public final static String csmsOrdAmount="csms_ord_amount";
		/** 公务订单总金额 [非空]       **/
		public final static String csmsUnitordAmount="csms_unitord_amount";
		/** 创建时间 [非空]       **/
		public final static String csmsAddtime="csms_addtime";
		/** 修改时间 [非空]       **/
		public final static String csmsUpdatetime="csms_updatetime";
		
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
				$.Set(name,CsMemberSummary.getCsMemberSummary(params));
			else
				$.Set(name,CsMemberSummary.getCsMemberSummaryList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取会员汇总项数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsMemberSummary) $.GetRequest("CsMemberSummary$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsMemberSummary.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsMemberSummary.getCsMemberSummary(params);
			else
				value = CsMemberSummary.getCsMemberSummaryList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsMemberSummary.Get($.add(CsMemberSummary.F.csmsId,param));
		else if(!$.empty(param.toString()))
			value = CsMemberSummary.get(Long.valueOf(param.toString()));
		$.SetRequest("CsMemberSummary$"+param.hashCode(), value);
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
	public void mergeSet(CsMemberSummary old){
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