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

@namespace("user/credit/card")
public @caption("信用卡") @table("cs_credit_card") class CsCreditCard implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cscc_id")   @primary   @note("  ") Long csccId;
	private @caption("信用卡号") @column("cscc_no")      @note("  ") String csccNo;
	private @caption("所属会员") @column("cscc_member")    @relate("$csccMember") @RelateClass(CsMember.class)  @note("  ") Long csccMember;
	private CsMember $csccMember;//关联对象[所属会员]
	private @caption("信用额度") @column("cscc_limit")      @note("  ") Double csccLimit;
	private @caption("信用分值") @column("cscc_scope")      @note("  ") Integer csccScope;
	private @caption("信用卡类型") @column("cscc_type")      @note(" 1:信用卡 3:支付宝信用卡  ") Short csccType;
	private @caption("备注信息") @column("cscc_remark")      @note("  ") String csccRemark;
	private @caption("特殊数据") @column("cscc_data")      @note("  ") String csccData;
	private @caption("掩码标识") @column("cscc_mask")      @note(" 1:已回流支付宝 2:支付宝风险名单用户 3:保留  ") Long csccMask;
	private @caption("修改时间") @column("cscc_update_time")      @note("  ") Date csccUpdateTime;
	private @caption("添加时间") @column("cscc_add_time")      @note("  ") Date csccAddTime;
	private @caption("状态") @column("cscc_status")      @note(" 0:未认证 1:已认证 2:等待认证 3:认证失败  ") Short csccStatus;
	private @caption("信用卡照片") @column("cscc_image")      @note("") String csccImage;
	
	//默认构造函数
	public CsCreditCard(){
	
	}
	
	//主键构造函数
	public CsCreditCard(Long id){
		this.csccId = id;
	}
	
	/**
	 * 所有字段构造函数 
	 */
	public CsCreditCard(String csccNo,Long csccMember,Double csccLimit,Integer csccScope,Short csccType,String csccRemark,String csccData,Long csccMask,Date csccUpdateTime,Date csccAddTime,Short csccStatus,String csccImage){
		this.csccNo=csccNo;
		this.csccMember=csccMember;
		this.csccLimit=csccLimit;
		this.csccScope=csccScope;
		this.csccType=csccType;
		this.csccRemark=csccRemark;
		this.csccData=csccData;
		this.csccMask=csccMask;
		this.csccUpdateTime=csccUpdateTime;
		this.csccAddTime=csccAddTime;
		this.csccStatus=csccStatus;
		this.csccImage=csccImage;
	}
	
	//设置非空字段
	public CsCreditCard setNotNull(String csccNo,Long csccMember,Double csccLimit,Integer csccScope,Short csccType,Date csccUpdateTime,Date csccAddTime,Short csccStatus){
		this.csccNo=csccNo;
		this.csccMember=csccMember;
		this.csccLimit=csccLimit;
		this.csccScope=csccScope;
		this.csccType=csccType;
		this.csccUpdateTime=csccUpdateTime;
		this.csccAddTime=csccAddTime;
		this.csccStatus=csccStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsCreditCard csccId(Long csccId){
		this.csccId = csccId;
		this.setSeted(F.csccId);
		return this;
	}
	/** 信用卡号 [非空]       **/
	public CsCreditCard csccNo(String csccNo){
		this.csccNo = csccNo;
		this.setSeted(F.csccNo);
		return this;
	}
	/** 所属会员 [非空]       **/
	public CsCreditCard csccMember(Long csccMember){
		this.csccMember = csccMember;
		this.setSeted(F.csccMember);
		return this;
	}
	/** 信用额度 [非空]       **/
	public CsCreditCard csccLimit(Double csccLimit){
		this.csccLimit = csccLimit;
		this.setSeted(F.csccLimit);
		return this;
	}
	/** 信用分值 [非空]       **/
	public CsCreditCard csccScope(Integer csccScope){
		this.csccScope = csccScope;
		this.setSeted(F.csccScope);
		return this;
	}
	/** 信用卡类型 [非空]       **/
	public CsCreditCard csccType(Short csccType){
		this.csccType = csccType;
		this.setSeted(F.csccType);
		return this;
	}
	/** 备注信息 [非空]       **/
	public CsCreditCard csccRemark(String csccRemark){
		this.csccRemark = csccRemark;
		this.setSeted(F.csccRemark);
		return this;
	}
	/** 特殊数据 [非空]       **/
	public CsCreditCard csccData(String csccData){
		this.csccData = csccData;
		this.setSeted(F.csccData);
		return this;
	}
	/** 掩码标识 [非空]       **/
	public CsCreditCard csccMask(Long csccMask){
		this.csccMask = csccMask;
		this.setSeted(F.csccMask);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsCreditCard csccUpdateTime(Date csccUpdateTime){
		this.csccUpdateTime = csccUpdateTime;
		this.setSeted(F.csccUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsCreditCard csccAddTime(Date csccAddTime){
		this.csccAddTime = csccAddTime;
		this.setSeted(F.csccAddTime);
		return this;
	}
	/** 状态 [非空]       **/
	public CsCreditCard csccStatus(Short csccStatus){
		this.csccStatus = csccStatus;
		this.setSeted(F.csccStatus);
		return this;
	}
	/** 信用卡照片 [非空]       **/
	public CsCreditCard csccImage(String csccImage){
		this.csccImage = csccImage;
		this.setSeted(F.csccImage);
		return this;
	}
	
	//克隆对象
	public CsCreditCard clone(){
		CsCreditCard clone = new CsCreditCard();
		clone.csccId=this.csccId;
		clone.csccNo=this.csccNo;
		clone.csccMember=this.csccMember;
		clone.csccLimit=this.csccLimit;
		clone.csccScope=this.csccScope;
		clone.csccType=this.csccType;
		clone.csccRemark=this.csccRemark;
		clone.csccData=this.csccData;
		clone.csccMask=this.csccMask;
		clone.csccUpdateTime=this.csccUpdateTime;
		clone.csccAddTime=this.csccAddTime;
		clone.csccStatus=this.csccStatus;
		clone.csccImage=this.csccImage;
		return clone;
	}
	
	
	/**
	 * 根据ID取信用卡
	 * @param id
	 * @return
	 */
	public static @api CsCreditCard get(Long id){		
		return getCsCreditCardById(id);
	}
	/**
	 * 获取所有信用卡
	 * @return
	 */
	public static @api List<CsCreditCard> list(Map params,Integer size){
		return getCsCreditCardList(params,size);
	}
	/**
	 * 获取信用卡分页
	 * @return
	 */
	public static @api Page<CsCreditCard> page(int page,int size,Map params){
		return getCsCreditCardPage(page, size , params);
	}
	/**
	 * 根据查询条件取信用卡
	 * @param params
	 * @return
	 */
	public static @api CsCreditCard Get(Map params){
		return getCsCreditCard(params);
	}
	/**
	 * 获取信用卡数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsCreditCardCount(params);
	}
	/**
	 * 获取信用卡数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsCreditCardEval(eval,params);
	}
	
	/**
	 * 根据ID取信用卡
	 * @param id
	 * @return
	 */
	public static @api CsCreditCard getCsCreditCardById(Long id){		
		CsCreditCard csCreditCard = (CsCreditCard) $.GetRequest("CsCreditCard$"+id);
		if(csCreditCard!=null)
			return csCreditCard;
		com.ccclubs.service.admin.ICsCreditCardService csCreditCardService = $.GetSpringBean("csCreditCardService");		
		return csCreditCardService.getCsCreditCardById(id);
	}
	
	
	/**
	 * 根据ID取信用卡的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsCreditCard.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsCreditCard csCreditCard = get(id);
		if(csCreditCard!=null){
			String strValue = csCreditCard.getCsccId$();
			if(!"CsccNo".equals("CsccNo"))
				strValue+="("+csCreditCard.getCsccNo$()+")";
			MemCache.setValue(CsCreditCard.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsccId$();
		if(!"CsccId".equals("CsccId"))
			keyValue+="("+this.getCsccId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有信用卡
	 * @return
	 */
	public static @api List<CsCreditCard> getCsCreditCardList(Map params,Integer size){
		com.ccclubs.service.admin.ICsCreditCardService csCreditCardService = $.GetSpringBean("csCreditCardService");
		return csCreditCardService.getCsCreditCardList(params, size);
	}
	
	/**
	 * 获取信用卡分页
	 * @return
	 */
	public static @api Page<CsCreditCard> getCsCreditCardPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsCreditCardService csCreditCardService = $.GetSpringBean("csCreditCardService");
		return csCreditCardService.getCsCreditCardPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取信用卡
	 * @param params
	 * @return
	 */
	public static @api CsCreditCard getCsCreditCard(Map params){
		com.ccclubs.service.admin.ICsCreditCardService csCreditCardService = $.GetSpringBean("csCreditCardService");
		return csCreditCardService.getCsCreditCard(params);
	}
	
	/**
	 * 获取信用卡数
	 * @return
	 */
	public static @api Long getCsCreditCardCount(Map params){
		com.ccclubs.service.admin.ICsCreditCardService csCreditCardService = $.GetSpringBean("csCreditCardService");
		return csCreditCardService.getCsCreditCardCount(params);
	}
		
		
	/**
	 * 获取信用卡自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsCreditCardEval(String eval,Map params){
		com.ccclubs.service.admin.ICsCreditCardService csCreditCardService = $.GetSpringBean("csCreditCardService");
		return csCreditCardService.getCsCreditCardEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsCreditCard(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsCreditCardService csCreditCardService = $.GetSpringBean("csCreditCardService");
		csCreditCardService.updateCsCreditCardByConfirm(set, where);
	}
	
	
	/**
	 * 保存信用卡对象
	 * @param params
	 * @return
	 */
	public CsCreditCard save(){
		com.ccclubs.service.admin.ICsCreditCardService csCreditCardService = $.GetSpringBean("csCreditCardService");
		if(this.getCsccId()!=null)
			csCreditCardService.updateCsCreditCard(this);
		else
			return csCreditCardService.saveCsCreditCard(this);
		return this;
	}
	
	
	/**
	 * 更新信用卡对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsCreditCardService csCreditCardService = $.GetSpringBean("csCreditCardService");
		csCreditCardService.updateCsCreditCard$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsCreditCardService csCreditCardService = $.GetSpringBean("csCreditCardService");
		if($.equals($.config("logic_delete"),"true"))
			csCreditCardService.removeCsCreditCardById(this.getCsccId());
		else
			csCreditCardService.deleteCsCreditCardById(this.getCsccId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsCreditCardService csCreditCardService = $.GetSpringBean("csCreditCardService");
		return csCreditCardService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsccId(){
		return this.csccId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsccId$(){
		String strValue="";
		 strValue=$.str(this.getCsccId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]     
	**/
	public void setCsccId(Long csccId){
		this.csccId = csccId;
		this.setSeted(F.csccId);
	}
	/*******************************信用卡号**********************************/	
	/**
	* 信用卡号 [非空]      
	**/
	public String getCsccNo(){
		return this.csccNo;
	}
	/**
	* 获取信用卡号格式化(toString)
	**/
	public String getCsccNo$(){
		String strValue="";
		 strValue=$.str(this.getCsccNo());
	 	 return strValue;
	}
	/**
	* 信用卡号 [非空]     
	**/
	public void setCsccNo(String csccNo){
		this.csccNo = csccNo;
		this.setSeted(F.csccNo);
	}
	/*******************************所属会员**********************************/	
	/**
	* 所属会员 [非空]      
	**/
	public Long getCsccMember(){
		return this.csccMember;
	}
	/**
	* 获取所属会员格式化(toString)
	**/
	public String getCsccMember$(){
		String strValue="";
		if(this.getCsccMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsccMember()));
		}			
	 	 return strValue;
	}
	/**
	* 所属会员 [非空]     
	**/
	public void setCsccMember(Long csccMember){
		this.csccMember = csccMember;
		this.setSeted(F.csccMember);
	}
	/**
	* 获取关联对象[所属会员]
	**/	 			
 	public CsMember get$csccMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsccMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsccMember()!=null){
			csMember = CsMember.get(this.getCsccMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsccMember(), csMember);
	 	return csMember;
	}
	/*******************************信用额度**********************************/	
	/**
	* 信用额度 [非空]      
	**/
	public Double getCsccLimit(){
		return this.csccLimit;
	}
	/**
	* 获取信用额度格式化(toString)
	**/
	public String getCsccLimit$(){
		String strValue="";
		 strValue=$.str(this.getCsccLimit());
	 	 return strValue;
	}
	/**
	* 信用额度 [非空]     
	**/
	public void setCsccLimit(Double csccLimit){
		this.csccLimit = csccLimit;
		this.setSeted(F.csccLimit);
	}
	/*******************************信用分值**********************************/	
	/**
	* 信用分值 [非空]      
	**/
	public Integer getCsccScope(){
		return this.csccScope;
	}
	/**
	* 获取信用分值格式化(toString)
	**/
	public String getCsccScope$(){
		String strValue="";
		 strValue=$.str(this.getCsccScope());
	 	 return strValue;
	}
	/**
	* 信用分值 [非空]     
	**/
	public void setCsccScope(Integer csccScope){
		this.csccScope = csccScope;
		this.setSeted(F.csccScope);
	}
	/*******************************信用卡类型**********************************/	
	/**
	* 信用卡类型 [非空]      
	**/
	public Short getCsccType(){
		return this.csccType;
	}
	/**
	* 获取信用卡类型格式化(toString)
	**/
	public String getCsccType$(){
		String strValue="";
		 if($.equals($.str(this.getCsccType()),"1"))
			strValue=$.str("信用卡");		 
		 if($.equals($.str(this.getCsccType()),"3"))
			strValue=$.str("支付宝信用卡");		 
	 	 return strValue;
	}
	/**
	* 信用卡类型 [非空]     
	**/
	public void setCsccType(Short csccType){
		this.csccType = csccType;
		this.setSeted(F.csccType);
	}
	/*******************************备注信息**********************************/	
	/**
	* 备注信息 [非空]      
	**/
	public String getCsccRemark(){
		return this.csccRemark;
	}
	/**
	* 获取备注信息格式化(toString)
	**/
	public String getCsccRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsccRemark());
	 	 return strValue;
	}
	/**
	* 备注信息 [非空]     
	**/
	public void setCsccRemark(String csccRemark){
		this.csccRemark = csccRemark;
		this.setSeted(F.csccRemark);
	}
	/*******************************特殊数据**********************************/	
	/**
	* 特殊数据 [非空]      
	**/
	public String getCsccData(){
		return this.csccData;
	}
	/**
	* 获取特殊数据格式化(toString)
	**/
	public String getCsccData$(){
		String strValue="";
		 strValue=$.str(this.getCsccData());
	 	 return strValue;
	}
	/**
	* 特殊数据 [非空]     
	**/
	public void setCsccData(String csccData){
		this.csccData = csccData;
		this.setSeted(F.csccData);
	}
	/*******************************掩码标识**********************************/	
	/**
	* 掩码标识 [非空]      
	**/
	public Long getCsccMask(){
		return this.csccMask;
	}
	/**
	* 获取掩码标识格式化(toString)
	**/
	public String getCsccMask$(){
		String strValue="";
		if(this.getCsccMask()!=null){
			if((this.getCsccMask() & 1 ) == 1)
				strValue+="["+$.str("已回流支付宝")+"]";		 
			if((this.getCsccMask() & 2 ) == 2)
				strValue+="["+$.str("支付宝风险名单用户")+"]";		 
			if((this.getCsccMask() & 3 ) == 3)
				strValue+="["+$.str("保留")+"]";		 
		}
	 	 return strValue;
	}
	/**
	* 掩码标识 [非空]     
	**/
	public void setCsccMask(Long csccMask){
		this.csccMask = csccMask;
		this.setSeted(F.csccMask);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsccUpdateTime(){
		return this.csccUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsccUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsccUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]     
	**/
	public void setCsccUpdateTime(Date csccUpdateTime){
		this.csccUpdateTime = csccUpdateTime;
		this.setSeted(F.csccUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsccAddTime(){
		return this.csccAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsccAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsccAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]     
	**/
	public void setCsccAddTime(Date csccAddTime){
		this.csccAddTime = csccAddTime;
		this.setSeted(F.csccAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]      
	**/
	public Short getCsccStatus(){
		return this.csccStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsccStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsccStatus()),"2"))
			 strValue=$.str("等待认证 ");
		 if($.equals($.str(this.getCsccStatus()),"1"))
			 strValue=$.str("已认证");
		 if($.equals($.str(this.getCsccStatus()),"0"))
			 strValue=$.str("未认证");
		 if($.equals($.str(this.getCsccStatus()),"3"))
			 strValue=$.str("认证失败");
	 	 return strValue;
	}
	/**
	* 状态 [非空]     
	**/
	public void setCsccStatus(Short csccStatus){
		this.csccStatus = csccStatus;
		this.setSeted(F.csccStatus);
	}
	/*******************************信用卡照片**********************************/	
	/**
	* 信用卡照片 [非空]      
	**/
	public String getCsccImage(){
		return this.csccImage;
	}
	/**
	* 获取信用卡照片格式化(toString)
	**/
	public String getCsccImage$(){
		String strValue="";
		 strValue=$.str(this.getCsccImage());
	 	 return strValue;
	}
	/**
	* 信用卡照片 [非空]     
	**/
	public void setCsccImage(String csccImage){
		this.csccImage = csccImage;
		this.setSeted(F.csccImage);
	}
	
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsCreditCard.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCreditCard.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCreditCard.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsCreditCard.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsCreditCard.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsCreditCard.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsCreditCard.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsCreditCard.this);
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
		public M csccId(Object csccId){this.put("csccId", csccId);return this;};
		/** and cscc_id is null */
		public M csccIdNull(){if(this.get("csccIdNot")==null)this.put("csccIdNot", "");this.put("csccId", null);return this;};
		/** not .... */
		public M csccIdNot(){this.put("csccIdNot", "not");return this;};
		/** and cscc_id >= ? */
		public M csccIdMin(Object min){this.put("csccIdMin", min);return this;};
		/** and cscc_id <= ? */
		public M csccIdMax(Object max){this.put("csccIdMax", max);return this;};
		/** 信用卡号 [非空]       **/
		public M csccNo(Object csccNo){this.put("csccNo", csccNo);return this;};
		/** and cscc_no is null */
		public M csccNoNull(){if(this.get("csccNoNot")==null)this.put("csccNoNot", "");this.put("csccNo", null);return this;};
		/** not .... */
		public M csccNoNot(){this.put("csccNoNot", "not");return this;};
		/** 所属会员 [非空]       **/
		public M csccMember(Object csccMember){this.put("csccMember", csccMember);return this;};
		/** and cscc_member is null */
		public M csccMemberNull(){if(this.get("csccMemberNot")==null)this.put("csccMemberNot", "");this.put("csccMember", null);return this;};
		/** not .... */
		public M csccMemberNot(){this.put("csccMemberNot", "not");return this;};
		public M csccMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csccMember$on", value);
			return this;
		};	
		/** and cscc_member >= ? */
		public M csccMemberMin(Object min){this.put("csccMemberMin", min);return this;};
		/** and cscc_member <= ? */
		public M csccMemberMax(Object max){this.put("csccMemberMax", max);return this;};
		/** 信用额度 [非空]       **/
		public M csccLimit(Object csccLimit){this.put("csccLimit", csccLimit);return this;};
		/** and cscc_limit is null */
		public M csccLimitNull(){if(this.get("csccLimitNot")==null)this.put("csccLimitNot", "");this.put("csccLimit", null);return this;};
		/** not .... */
		public M csccLimitNot(){this.put("csccLimitNot", "not");return this;};
		/** and cscc_limit >= ? */
		public M csccLimitMin(Object min){this.put("csccLimitMin", min);return this;};
		/** and cscc_limit <= ? */
		public M csccLimitMax(Object max){this.put("csccLimitMax", max);return this;};
		/** 信用分值 [非空]       **/
		public M csccScope(Object csccScope){this.put("csccScope", csccScope);return this;};
		/** and cscc_scope is null */
		public M csccScopeNull(){if(this.get("csccScopeNot")==null)this.put("csccScopeNot", "");this.put("csccScope", null);return this;};
		/** not .... */
		public M csccScopeNot(){this.put("csccScopeNot", "not");return this;};
		/** and cscc_scope >= ? */
		public M csccScopeMin(Object min){this.put("csccScopeMin", min);return this;};
		/** and cscc_scope <= ? */
		public M csccScopeMax(Object max){this.put("csccScopeMax", max);return this;};
		/** 信用卡类型 [非空]       **/
		public M csccType(Object csccType){this.put("csccType", csccType);return this;};
		/** and cscc_type is null */
		public M csccTypeNull(){if(this.get("csccTypeNot")==null)this.put("csccTypeNot", "");this.put("csccType", null);return this;};
		/** not .... */
		public M csccTypeNot(){this.put("csccTypeNot", "not");return this;};
		/** and cscc_type >= ? */
		public M csccTypeMin(Object min){this.put("csccTypeMin", min);return this;};
		/** and cscc_type <= ? */
		public M csccTypeMax(Object max){this.put("csccTypeMax", max);return this;};
		/** 备注信息 [非空]       **/
		public M csccRemark(Object csccRemark){this.put("csccRemark", csccRemark);return this;};
		/** and cscc_remark is null */
		public M csccRemarkNull(){if(this.get("csccRemarkNot")==null)this.put("csccRemarkNot", "");this.put("csccRemark", null);return this;};
		/** not .... */
		public M csccRemarkNot(){this.put("csccRemarkNot", "not");return this;};
		/** 特殊数据 [非空]       **/
		public M csccData(Object csccData){this.put("csccData", csccData);return this;};
		/** and cscc_data is null */
		public M csccDataNull(){if(this.get("csccDataNot")==null)this.put("csccDataNot", "");this.put("csccData", null);return this;};
		/** not .... */
		public M csccDataNot(){this.put("csccDataNot", "not");return this;};
		/** 掩码标识 [非空]       **/
		public M csccMask(Object csccMask){this.put("csccMask", csccMask);return this;};
		/** and cscc_mask is null */
		public M csccMaskNull(){if(this.get("csccMaskNot")==null)this.put("csccMaskNot", "");this.put("csccMask", null);return this;};
		/** not .... */
		public M csccMaskNot(){this.put("csccMaskNot", "not");return this;};
		/** and cscc_mask >= ? */
		public M csccMaskMin(Object min){this.put("csccMaskMin", min);return this;};
		/** and cscc_mask <= ? */
		public M csccMaskMax(Object max){this.put("csccMaskMax", max);return this;};
		/** 修改时间 [非空]       **/
		public M csccUpdateTime(Object csccUpdateTime){this.put("csccUpdateTime", csccUpdateTime);return this;};
		/** and cscc_update_time is null */
		public M csccUpdateTimeNull(){if(this.get("csccUpdateTimeNot")==null)this.put("csccUpdateTimeNot", "");this.put("csccUpdateTime", null);return this;};
		/** not .... */
		public M csccUpdateTimeNot(){this.put("csccUpdateTimeNot", "not");return this;};
		/** and cscc_update_time >= ? */
 		public M csccUpdateTimeStart(Object start){this.put("csccUpdateTimeStart", start);return this;};			
 		/** and cscc_update_time <= ? */
 		public M csccUpdateTimeEnd(Object end){this.put("csccUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csccAddTime(Object csccAddTime){this.put("csccAddTime", csccAddTime);return this;};
		/** and cscc_add_time is null */
		public M csccAddTimeNull(){if(this.get("csccAddTimeNot")==null)this.put("csccAddTimeNot", "");this.put("csccAddTime", null);return this;};
		/** not .... */
		public M csccAddTimeNot(){this.put("csccAddTimeNot", "not");return this;};
		/** and cscc_add_time >= ? */
 		public M csccAddTimeStart(Object start){this.put("csccAddTimeStart", start);return this;};			
 		/** and cscc_add_time <= ? */
 		public M csccAddTimeEnd(Object end){this.put("csccAddTimeEnd", end);return this;};
		/** 状态 [非空]       **/
		public M csccStatus(Object csccStatus){this.put("csccStatus", csccStatus);return this;};
		/** and cscc_status is null */
		public M csccStatusNull(){if(this.get("csccStatusNot")==null)this.put("csccStatusNot", "");this.put("csccStatus", null);return this;};
		/** not .... */
		public M csccStatusNot(){this.put("csccStatusNot", "not");return this;};
		/** and cscc_status >= ? */
		public M csccStatusMin(Object min){this.put("csccStatusMin", min);return this;};
		/** and cscc_status <= ? */
		public M csccStatusMax(Object max){this.put("csccStatusMax", max);return this;};
		/** 信用卡照片 [非空]       **/
		public M csccImage(Object csccImage){this.put("csccImage", csccImage);return this;};
		/** and cscc_image is null */
		public M csccImageNull(){if(this.get("csccImageNot")==null)this.put("csccImageNot", "");this.put("csccImage", null);return this;};
		/** not .... */
		public M csccImageNot(){this.put("csccImageNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有信用卡 **/
		public @api List<CsCreditCard> list(Integer size){
			return getCsCreditCardList(this,size);
		}
		/** 获取信用卡分页 **/
		public @api Page<CsCreditCard> page(int page,int size){
			return getCsCreditCardPage(page, size , this);
		}
		/** 根据查询条件取信用卡 **/
		public @api CsCreditCard get(){
			return getCsCreditCard(this);
		}
		/** 获取信用卡数 **/
		public @api Long count(){
			return getCsCreditCardCount(this);
		}
		/** 获取信用卡表达式 **/
		public @api <T> T eval(String strEval){
			return getCsCreditCardEval(strEval,this);
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
			updateCsCreditCard(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csccId="csccId";
		/** 信用卡号 [非空]       **/
		public final static @type(String.class)  String csccNo="csccNo";
		/** 所属会员 [非空]       **/
		public final static @type(Long.class)  String csccMember="csccMember";
		/** 信用额度 [非空]       **/
		public final static @type(Double.class)  String csccLimit="csccLimit";
		/** 信用分值 [非空]       **/
		public final static @type(Integer.class)  String csccScope="csccScope";
		/** 信用卡类型 [非空]       **/
		public final static @type(Short.class)  String csccType="csccType";
		/** 备注信息 [非空]       **/
		public final static @type(String.class)  String csccRemark="csccRemark";
		/** 特殊数据 [非空]       **/
		public final static @type(String.class)  String csccData="csccData";
		/** 掩码标识 [非空]       **/
		public final static @type(Long.class)  String csccMask="csccMask";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csccUpdateTime="csccUpdateTime";
		/** and cscc_update_time >= ? */
 		public final static @type(Date.class) String csccUpdateTimeStart="csccUpdateTimeStart";
 		/** and cscc_update_time <= ? */
 		public final static @type(Date.class) String csccUpdateTimeEnd="csccUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csccAddTime="csccAddTime";
		/** and cscc_add_time >= ? */
 		public final static @type(Date.class) String csccAddTimeStart="csccAddTimeStart";
 		/** and cscc_add_time <= ? */
 		public final static @type(Date.class) String csccAddTimeEnd="csccAddTimeEnd";
		/** 状态 [非空]       **/
		public final static @type(Short.class)  String csccStatus="csccStatus";
		/** 信用卡照片 [非空]       **/
		public final static @type(String.class)  String csccImage="csccImage";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csccId="cscc_id";
		/** 信用卡号 [非空]       **/
		public final static String csccNo="cscc_no";
		/** 所属会员 [非空]       **/
		public final static String csccMember="cscc_member";
		/** 信用额度 [非空]       **/
		public final static String csccLimit="cscc_limit";
		/** 信用分值 [非空]       **/
		public final static String csccScope="cscc_scope";
		/** 信用卡类型 [非空]       **/
		public final static String csccType="cscc_type";
		/** 备注信息 [非空]       **/
		public final static String csccRemark="cscc_remark";
		/** 特殊数据 [非空]       **/
		public final static String csccData="cscc_data";
		/** 掩码标识 [非空]       **/
		public final static String csccMask="cscc_mask";
		/** 修改时间 [非空]       **/
		public final static String csccUpdateTime="cscc_update_time";
		/** 添加时间 [非空]       **/
		public final static String csccAddTime="cscc_add_time";
		/** 状态 [非空]       **/
		public final static String csccStatus="cscc_status";
		/** 信用卡照片 [非空]       **/
		public final static String csccImage="cscc_image";
		
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
				$.Set(name,CsCreditCard.getCsCreditCard(params));
			else
				$.Set(name,CsCreditCard.getCsCreditCardList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取信用卡数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsCreditCard) $.GetRequest("CsCreditCard$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsCreditCard.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsCreditCard.getCsCreditCard(params);
			else
				value = CsCreditCard.getCsCreditCardList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsCreditCard.Get($.add(CsCreditCard.F.csccId,param));
		else if(!$.empty(param.toString()))
			value = CsCreditCard.get(Long.valueOf(param.toString()));
		$.SetRequest("CsCreditCard$"+param.hashCode(), value);
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
	public void mergeSet(CsCreditCard old){
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