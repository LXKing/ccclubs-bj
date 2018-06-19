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

@namespace("user/userecord")
public @caption("惠免记录") @table("cs_use_record") class CsUseRecord implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csur_id")   @primary  @note("  ") Long csurId;// 主键 非空     
	private @caption("城市") @column("csur_host")    @relate("$csurHost") @RelateClass(SrvHost.class)  @note("  ") Long csurHost;// 非空     
 	private SrvHost $csurHost;//关联对象[运营城市]
	private @caption("摘要") @column("csur_profile")    @note("  ") String csurProfile;// 非空     
	private @caption("所属会员") @column("csur_member")    @relate("$csurMember") @RelateClass(CsMember.class)  @note("  ") Long csurMember;// 非空    ${basePath}${proname}/user/member_inquire.do 
 	private CsMember $csurMember;//关联对象[会员帐号]
	private @caption("所属免费小时") @column("csur_free_hour") @hidden   @relate("$csurFreeHour") @RelateClass(CsFreeHour.class)  @note("  ") Long csurFreeHour;//     
 	private CsFreeHour $csurFreeHour;//关联对象[免费小时]
	private @caption("红包编号") @column("csur_coin")    @relate("$csurCoin") @RelateClass(CsCoin.class)  @note("  ") Long csurCoin;//     
 	private CsCoin $csurCoin;//关联对象[红包]
	private @caption("所属优惠项") @column("csur_gift")    @relate("$csurGift") @RelateClass(CsGift.class)  @note("  ") Long csurGift;//     
 	private CsGift $csurGift;//关联对象[优惠内容]
	private @caption("所属订单") @column("csur_order")    @relate("$csurOrder") @RelateClass(CsOrder.class)  @note("  ") Long csurOrder;// 非空     
 	private CsOrder $csurOrder;//关联对象[系统订单]
	private @caption("消费金额") @column("csur_count")  @note("  ") Double csurCount;//     
	private @caption("使用时间") @column("csur_add_time")    @note("  ") Date csurAddTime;// 非空     
	
	//默认构造函数
	public CsUseRecord(){
	
	}
	
	//主键构造函数
	public CsUseRecord(Long id){
		this.csurId = id;
	}
	
	/**所有字段构造函数 CsUseRecord(csurHost,csurProfile,csurMember,csurFreeHour,csurCoin,csurGift,csurOrder,csurCount,csurAddTime)
	 CsUseRecord(
	 	$.getLong("csurHost")//城市 [非空]
	 	,$.getString("csurProfile")//摘要 [非空]
	 	,$.getLong("csurMember")//所属会员 [非空]
	 	,$.getLong("csurFreeHour")//所属免费小时
	 	,$.getLong("csurCoin")//所属红包
	 	,$.getLong("csurGift")//所属优惠项
	 	,$.getLong("csurOrder")//所属订单 [非空]
	 	,$.getDouble("csurCount")//使用数量
	 	,$.getDate("csurAddTime")//使用时间 [非空]
	 )
	**/
	public CsUseRecord(Long csurHost,String csurProfile,Long csurMember,Long csurFreeHour,Long csurCoin,Long csurGift,Long csurOrder,Double csurCount,Date csurAddTime){
		this.csurHost=csurHost;
		this.csurProfile=csurProfile;
		this.csurMember=csurMember;
		this.csurFreeHour=csurFreeHour;
		this.csurCoin=csurCoin;
		this.csurGift=csurGift;
		this.csurOrder=csurOrder;
		this.csurCount=csurCount;
		this.csurAddTime=csurAddTime;
	}
	
	//设置非空字段
	public CsUseRecord setNotNull(Long csurHost,String csurProfile,Long csurMember,Long csurOrder,Date csurAddTime){
		this.csurHost=csurHost;
		this.csurProfile=csurProfile;
		this.csurMember=csurMember;
		this.csurOrder=csurOrder;
		this.csurAddTime=csurAddTime;
		return this;
	}
	/** 编号 [非空]       **/
	public CsUseRecord csurId(Long csurId){
		this.csurId = csurId;
		this.setSeted(F.csurId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsUseRecord csurHost(Long csurHost){
		this.csurHost = csurHost;
		this.setSeted(F.csurHost);
		return this;
	}
	/** 摘要 [非空]       **/
	public CsUseRecord csurProfile(String csurProfile){
		this.csurProfile = csurProfile;
		this.setSeted(F.csurProfile);
		return this;
	}
	/** 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
	public CsUseRecord csurMember(Long csurMember){
		this.csurMember = csurMember;
		this.setSeted(F.csurMember);
		return this;
	}
	/** 所属免费小时  [CsFreeHour]      **/
	public CsUseRecord csurFreeHour(Long csurFreeHour){
		this.csurFreeHour = csurFreeHour;
		this.setSeted(F.csurFreeHour);
		return this;
	}
	/** 所属红包  [CsCoin]      **/
	public CsUseRecord csurCoin(Long csurCoin){
		this.csurCoin = csurCoin;
		this.setSeted(F.csurCoin);
		return this;
	}
	/** 所属优惠项  [CsGift]      **/
	public CsUseRecord csurGift(Long csurGift){
		this.csurGift = csurGift;
		this.setSeted(F.csurGift);
		return this;
	}
	/** 所属订单 [非空] [CsOrder]      **/
	public CsUseRecord csurOrder(Long csurOrder){
		this.csurOrder = csurOrder;
		this.setSeted(F.csurOrder);
		return this;
	}
	/** 使用数量        **/
	public CsUseRecord csurCount(Double csurCount){
		this.csurCount = csurCount;
		this.setSeted(F.csurCount);
		return this;
	}
	/** 使用时间 [非空]       **/
	public CsUseRecord csurAddTime(Date csurAddTime){
		this.csurAddTime = csurAddTime;
		this.setSeted(F.csurAddTime);
		return this;
	}
	
	
	//克隆对象
	public CsUseRecord clone(){
		CsUseRecord clone = new CsUseRecord();
		clone.csurHost=this.csurHost;
		clone.csurProfile=this.csurProfile;
		clone.csurMember=this.csurMember;
		clone.csurOrder=this.csurOrder;
		clone.csurAddTime=this.csurAddTime;
		return clone;
	}
	
	
	/**
	 * 根据ID取惠免记录
	 * @param id
	 * @return
	 */
	public static @api CsUseRecord get(Long id){		
		return getCsUseRecordById(id);
	}
	/**
	 * 获取所有惠免记录
	 * @return
	 */
	public static @api List<CsUseRecord> list(Map params,Integer size){
		return getCsUseRecordList(params,size);
	}
	/**
	 * 获取惠免记录分页
	 * @return
	 */
	public static @api Page<CsUseRecord> page(int page,int size,Map params){
		return getCsUseRecordPage(page, size , params);
	}
	/**
	 * 根据查询条件取惠免记录
	 * @param params
	 * @return
	 */
	public static @api CsUseRecord Get(Map params){
		return getCsUseRecord(params);
	}
	/**
	 * 获取惠免记录数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsUseRecordCount(params);
	}
	/**
	 * 获取惠免记录数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsUseRecordEval(eval,params);
	}
	
	/**
	 * 根据ID取惠免记录
	 * @param id
	 * @return
	 */
	public static @api CsUseRecord getCsUseRecordById(Long id){		
		CsUseRecord csUseRecord = (CsUseRecord) $.GetRequest("CsUseRecord$"+id);
		if(csUseRecord!=null)
			return csUseRecord;
		com.ccclubs.service.admin.ICsUseRecordService csUseRecordService = $.GetSpringBean("csUseRecordService");		
		return csUseRecordService.getCsUseRecordById(id);
	}
	
	
	/**
	 * 根据ID取惠免记录的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsUseRecord.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsUseRecord csUseRecord = get(id);
		if(csUseRecord!=null){
			String strValue = csUseRecord.getCsurProfile$();
			if(!"CsurProfile".equals("CsurProfile"))
				strValue+="("+csUseRecord.getCsurProfile$()+")";
			MemCache.setValue(CsUseRecord.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsurProfile$();
		if(!"CsurProfile".equals("CsurProfile"))
			keyValue+="("+this.getCsurProfile$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有惠免记录
	 * @return
	 */
	public static @api List<CsUseRecord> getCsUseRecordList(Map params,Integer size){
		com.ccclubs.service.admin.ICsUseRecordService csUseRecordService = $.GetSpringBean("csUseRecordService");
		return csUseRecordService.getCsUseRecordList(params, size);
	}
	
	/**
	 * 获取惠免记录分页
	 * @return
	 */
	public static @api Page<CsUseRecord> getCsUseRecordPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsUseRecordService csUseRecordService = $.GetSpringBean("csUseRecordService");
		return csUseRecordService.getCsUseRecordPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取惠免记录
	 * @param params
	 * @return
	 */
	public static @api CsUseRecord getCsUseRecord(Map params){
		com.ccclubs.service.admin.ICsUseRecordService csUseRecordService = $.GetSpringBean("csUseRecordService");
		return csUseRecordService.getCsUseRecord(params);
	}
	
	/**
	 * 获取惠免记录数
	 * @return
	 */
	public static @api Long getCsUseRecordCount(Map params){
		com.ccclubs.service.admin.ICsUseRecordService csUseRecordService = $.GetSpringBean("csUseRecordService");
		return csUseRecordService.getCsUseRecordCount(params);
	}
		
		
	/**
	 * 获取惠免记录自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsUseRecordEval(String eval,Map params){
		com.ccclubs.service.admin.ICsUseRecordService csUseRecordService = $.GetSpringBean("csUseRecordService");
		return csUseRecordService.getCsUseRecordEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsUseRecord(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsUseRecordService csUseRecordService = $.GetSpringBean("csUseRecordService");
		csUseRecordService.updateCsUseRecordByConfirm(set, where);
	}
	
	
	/**
	 * 保存惠免记录对象
	 * @param params
	 * @return
	 */
	public CsUseRecord save(){
		com.ccclubs.service.admin.ICsUseRecordService csUseRecordService = $.GetSpringBean("csUseRecordService");
		if(this.getCsurId()!=null)
			csUseRecordService.updateCsUseRecord(this);
		else
			return csUseRecordService.saveCsUseRecord(this);
		return this;
	}
	
	
	/**
	 * 更新惠免记录对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsUseRecordService csUseRecordService = $.GetSpringBean("csUseRecordService");
		csUseRecordService.updateCsUseRecord$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsUseRecordService csUseRecordService = $.GetSpringBean("csUseRecordService");
			csUseRecordService.deleteCsUseRecordById(this.getCsurId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsUseRecordService csUseRecordService = $.GetSpringBean("csUseRecordService");
		return csUseRecordService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsurId(){
		return this.csurId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsurId$(){
		String strValue="";
		 strValue=$.str(this.getCsurId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsurId(Long csurId){
		this.csurId = csurId;
		this.setSeted(F.csurId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsurHost(){
		return this.csurHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsurHost$(){
		String strValue="";
		if(this.getCsurHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsurHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsurHost(Long csurHost){
		this.csurHost = csurHost;
		this.setSeted(F.csurHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csurHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsurHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsurHost()!=null){
 			srvHost = SrvHost.get(this.getCsurHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsurHost(), srvHost);
	 	return srvHost;
	}
	/*******************************摘要**********************************/	
	/**
	* 摘要 [非空]      
	**/
	public String getCsurProfile(){
		return this.csurProfile;
	}
	/**
	* 获取摘要格式化(toString)
	**/
	public String getCsurProfile$(){
		String strValue="";
		 strValue=$.str(this.getCsurProfile());
	 	 return strValue;
	}
	/**
	* 摘要 [非空]      
	**/
	public void setCsurProfile(String csurProfile){
		this.csurProfile = csurProfile;
		this.setSeted(F.csurProfile);
	}
	/*******************************所属会员**********************************/	
	/**
	* 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public Long getCsurMember(){
		return this.csurMember;
	}
	/**
	* 获取所属会员格式化(toString)
	**/
	public String getCsurMember$(){
		String strValue="";
		if(this.getCsurMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsurMember()));
		}			
	 	 return strValue;
	}
	/**
	* 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public void setCsurMember(Long csurMember){
		this.csurMember = csurMember;
		this.setSeted(F.csurMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csurMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsurMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsurMember()!=null){
 			csMember = CsMember.get(this.getCsurMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsurMember(), csMember);
	 	return csMember;
	}
	/*******************************所属免费小时**********************************/	
	/**
	* 所属免费小时  [CsFreeHour]     
	**/
	public Long getCsurFreeHour(){
		return this.csurFreeHour;
	}
	/**
	* 获取所属免费小时格式化(toString)
	**/
	public String getCsurFreeHour$(){
		String strValue="";
		if(this.getCsurFreeHour()!=null){
				strValue+=$.str(CsFreeHour.getKeyValue(this.getCsurFreeHour()));
		}			
	 	 return strValue;
	}
	/**
	* 所属免费小时  [CsFreeHour]     
	**/
	public void setCsurFreeHour(Long csurFreeHour){
		this.csurFreeHour = csurFreeHour;
		this.setSeted(F.csurFreeHour);
	}
	/**
	* 获取关联对象[免费小时]
	**/	 			
 	public CsFreeHour get$csurFreeHour(){
 		com.ccclubs.model.CsFreeHour csFreeHour = (com.ccclubs.model.CsFreeHour) $.GetRequest("CsFreeHour$"+this.getCsurFreeHour());
 		if(csFreeHour!=null)
			return csFreeHour;
		if(this.getCsurFreeHour()!=null){
 			csFreeHour = CsFreeHour.get(this.getCsurFreeHour());
 		}
 		$.SetRequest("CsFreeHour$"+this.getCsurFreeHour(), csFreeHour);
	 	return csFreeHour;
	}
	/*******************************所属红包**********************************/	
	/**
	* 所属红包  [CsCoin]     
	**/
	public Long getCsurCoin(){
		return this.csurCoin;
	}
	/**
	* 获取所属红包格式化(toString)
	**/
	public String getCsurCoin$(){
		String strValue="";
		if(this.getCsurCoin()!=null){
				strValue+=$.str(CsCoin.getKeyValue(this.getCsurCoin()));
		}			
	 	 return strValue;
	}
	/**
	* 所属红包  [CsCoin]     
	**/
	public void setCsurCoin(Long csurCoin){
		this.csurCoin = csurCoin;
		this.setSeted(F.csurCoin);
	}
	/**
	* 获取关联对象[红包]
	**/	 			
 	public CsCoin get$csurCoin(){
 		com.ccclubs.model.CsCoin csCoin = (com.ccclubs.model.CsCoin) $.GetRequest("CsCoin$"+this.getCsurCoin());
 		if(csCoin!=null)
			return csCoin;
		if(this.getCsurCoin()!=null){
 			csCoin = CsCoin.get(this.getCsurCoin());
 		}
 		$.SetRequest("CsCoin$"+this.getCsurCoin(), csCoin);
	 	return csCoin;
	}
	/*******************************所属优惠项**********************************/	
	/**
	* 所属优惠项  [CsGift]     
	**/
	public Long getCsurGift(){
		return this.csurGift;
	}
	/**
	* 获取所属优惠项格式化(toString)
	**/
	public String getCsurGift$(){
		String strValue="";
		if(this.getCsurGift()!=null){
				strValue+=$.str(CsGift.getKeyValue(this.getCsurGift()));
		}			
	 	 return strValue;
	}
	/**
	* 所属优惠项  [CsGift]     
	**/
	public void setCsurGift(Long csurGift){
		this.csurGift = csurGift;
		this.setSeted(F.csurGift);
	}
	/**
	* 获取关联对象[优惠内容]
	**/	 			
 	public CsGift get$csurGift(){
 		com.ccclubs.model.CsGift csGift = (com.ccclubs.model.CsGift) $.GetRequest("CsGift$"+this.getCsurGift());
 		if(csGift!=null)
			return csGift;
		if(this.getCsurGift()!=null){
 			csGift = CsGift.get(this.getCsurGift());
 		}
 		$.SetRequest("CsGift$"+this.getCsurGift(), csGift);
	 	return csGift;
	}
	/*******************************所属订单**********************************/	
	/**
	* 所属订单 [非空] [CsOrder]     
	**/
	public Long getCsurOrder(){
		return this.csurOrder;
	}
	/**
	* 获取所属订单格式化(toString)
	**/
	public String getCsurOrder$(){
		String strValue="";
		if(this.getCsurOrder()!=null){
				strValue+=$.str(CsOrder.getKeyValue(this.getCsurOrder()));
		}			
	 	 return strValue;
	}
	/**
	* 所属订单 [非空] [CsOrder]     
	**/
	public void setCsurOrder(Long csurOrder){
		this.csurOrder = csurOrder;
		this.setSeted(F.csurOrder);
	}
	/**
	* 获取关联对象[系统订单]
	**/	 			
 	public CsOrder get$csurOrder(){
 		com.ccclubs.model.CsOrder csOrder = (com.ccclubs.model.CsOrder) $.GetRequest("CsOrder$"+this.getCsurOrder());
 		if(csOrder!=null)
			return csOrder;
		if(this.getCsurOrder()!=null){
 			csOrder = CsOrder.get(this.getCsurOrder());
 		}
 		$.SetRequest("CsOrder$"+this.getCsurOrder(), csOrder);
	 	return csOrder;
	}
	/*******************************使用数量**********************************/	
	/**
	* 使用数量       
	**/
	public Double getCsurCount(){
		return this.csurCount;
	}
	/**
	* 获取使用数量格式化(toString)
	**/
	public String getCsurCount$(){
		String strValue="";
		 strValue=$.str(this.getCsurCount());	
	 	 return strValue;
	}
	/**
	* 使用数量       
	**/
	public void setCsurCount(Double csurCount){
		this.csurCount = csurCount;
		this.setSeted(F.csurCount);
	}
	/*******************************使用时间**********************************/	
	/**
	* 使用时间 [非空]      
	**/
	public Date getCsurAddTime(){
		return this.csurAddTime;
	}
	/**
	* 获取使用时间格式化(toString)
	**/
	public String getCsurAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsurAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 使用时间 [非空]      
	**/
	public void setCsurAddTime(Date csurAddTime){
		this.csurAddTime = csurAddTime;
		this.setSeted(F.csurAddTime);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsUseRecord.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUseRecord.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUseRecord.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsUseRecord.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsUseRecord.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUseRecord.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUseRecord.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsUseRecord.this);
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
		public M csurId(Object csurId){this.put("csurId", csurId);return this;};
	 	/** and csur_id is null */
 		public M csurIdNull(){if(this.get("csurIdNot")==null)this.put("csurIdNot", "");this.put("csurId", null);return this;};
 		/** not .... */
 		public M csurIdNot(){this.put("csurIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csurHost(Object csurHost){this.put("csurHost", csurHost);return this;};
	 	/** and csur_host is null */
 		public M csurHostNull(){if(this.get("csurHostNot")==null)this.put("csurHostNot", "");this.put("csurHost", null);return this;};
 		/** not .... */
 		public M csurHostNot(){this.put("csurHostNot", "not");return this;};
		/** 摘要 [非空]       **/
		public M csurProfile(Object csurProfile){this.put("csurProfile", csurProfile);return this;};
	 	/** and csur_profile is null */
 		public M csurProfileNull(){if(this.get("csurProfileNot")==null)this.put("csurProfileNot", "");this.put("csurProfile", null);return this;};
 		/** not .... */
 		public M csurProfileNot(){this.put("csurProfileNot", "not");return this;};
		/** 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public M csurMember(Object csurMember){this.put("csurMember", csurMember);return this;};
	 	/** and csur_member is null */
 		public M csurMemberNull(){if(this.get("csurMemberNot")==null)this.put("csurMemberNot", "");this.put("csurMember", null);return this;};
 		/** not .... */
 		public M csurMemberNot(){this.put("csurMemberNot", "not");return this;};
		public M csurMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csurMember$on", value);
			return this;
		};	
		/** 所属免费小时  [CsFreeHour]      **/
		public M csurFreeHour(Object csurFreeHour){this.put("csurFreeHour", csurFreeHour);return this;};
	 	/** and csur_free_hour is null */
 		public M csurFreeHourNull(){if(this.get("csurFreeHourNot")==null)this.put("csurFreeHourNot", "");this.put("csurFreeHour", null);return this;};
 		/** not .... */
 		public M csurFreeHourNot(){this.put("csurFreeHourNot", "not");return this;};
		public M csurFreeHour$on(CsFreeHour.M value){
			this.put("CsFreeHour", value);
			this.put("csurFreeHour$on", value);
			return this;
		};	
		/** 所属红包  [CsCoin]      **/
		public M csurCoin(Object csurCoin){this.put("csurCoin", csurCoin);return this;};
	 	/** and csur_coin is null */
 		public M csurCoinNull(){if(this.get("csurCoinNot")==null)this.put("csurCoinNot", "");this.put("csurCoin", null);return this;};
 		/** not .... */
 		public M csurCoinNot(){this.put("csurCoinNot", "not");return this;};
		public M csurCoin$on(CsCoin.M value){
			this.put("CsCoin", value);
			this.put("csurCoin$on", value);
			return this;
		};	
		/** 所属优惠项  [CsGift]      **/
		public M csurGift(Object csurGift){this.put("csurGift", csurGift);return this;};
	 	/** and csur_gift is null */
 		public M csurGiftNull(){if(this.get("csurGiftNot")==null)this.put("csurGiftNot", "");this.put("csurGift", null);return this;};
 		/** not .... */
 		public M csurGiftNot(){this.put("csurGiftNot", "not");return this;};
		public M csurGift$on(CsGift.M value){
			this.put("CsGift", value);
			this.put("csurGift$on", value);
			return this;
		};	
		/** 所属订单 [非空] [CsOrder]      **/
		public M csurOrder(Object csurOrder){this.put("csurOrder", csurOrder);return this;};
	 	/** and csur_order is null */
 		public M csurOrderNull(){if(this.get("csurOrderNot")==null)this.put("csurOrderNot", "");this.put("csurOrder", null);return this;};
 		/** not .... */
 		public M csurOrderNot(){this.put("csurOrderNot", "not");return this;};
		public M csurOrder$on(CsOrder.M value){
			this.put("CsOrder", value);
			this.put("csurOrder$on", value);
			return this;
		};	
		/** 使用数量        **/
		public M csurCount(Object csurCount){this.put("csurCount", csurCount);return this;};
	 	/** and csur_count is null */
 		public M csurCountNull(){if(this.get("csurCountNot")==null)this.put("csurCountNot", "");this.put("csurCount", null);return this;};
 		/** not .... */
 		public M csurCountNot(){this.put("csurCountNot", "not");return this;};
		/** and csur_count >= ? */
		public M csurCountMin(Object min){this.put("csurCountMin", min);return this;};
		/** and csur_count <= ? */
		public M csurCountMax(Object max){this.put("csurCountMax", max);return this;};
		/** 使用时间 [非空]       **/
		public M csurAddTime(Object csurAddTime){this.put("csurAddTime", csurAddTime);return this;};
	 	/** and csur_add_time is null */
 		public M csurAddTimeNull(){if(this.get("csurAddTimeNot")==null)this.put("csurAddTimeNot", "");this.put("csurAddTime", null);return this;};
 		/** not .... */
 		public M csurAddTimeNot(){this.put("csurAddTimeNot", "not");return this;};
 		/** and csur_add_time >= ? */
 		public M csurAddTimeStart(Object start){this.put("csurAddTimeStart", start);return this;};			
 		/** and csur_add_time <= ? */
 		public M csurAddTimeEnd(Object end){this.put("csurAddTimeEnd", end);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有惠免记录 **/
		public @api List<CsUseRecord> list(Integer size){
			return getCsUseRecordList(this,size);
		}
		/** 获取惠免记录分页 **/
		public @api Page<CsUseRecord> page(int page,int size){
			return getCsUseRecordPage(page, size , this);
		}
		/** 根据查询条件取惠免记录 **/
		public @api CsUseRecord get(){
			return getCsUseRecord(this);
		}
		/** 获取惠免记录数 **/
		public @api Long count(){
			return getCsUseRecordCount(this);
		}
		/** 获取惠免记录表达式 **/
		public @api <T> T eval(String strEval){
			return getCsUseRecordEval(strEval,this);
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
			updateCsUseRecord(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csurId="csurId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csurHost="csurHost";
		/** 摘要 [非空]       **/
		public final static @type(String.class) @like String csurProfile="csurProfile";
		/** 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static @type(Long.class)  String csurMember="csurMember";
		/** 所属免费小时  [CsFreeHour]      **/
		public final static @type(Long.class)  String csurFreeHour="csurFreeHour";
		/** 所属红包  [CsCoin]      **/
		public final static @type(Long.class)  String csurCoin="csurCoin";
		/** 所属优惠项  [CsGift]      **/
		public final static @type(Long.class)  String csurGift="csurGift";
		/** 所属订单 [非空] [CsOrder]      **/
		public final static @type(Long.class)  String csurOrder="csurOrder";
		/** 使用数量        **/
		public final static @type(Double.class)  String csurCount="csurCount";
		/** and csur_count >= ? */
		public final static @type(Double.class) String csurCountMin="csurCountMin";
		/** and csur_count <= ? */
		public final static @type(Double.class) String csurCountMax="csurCountMax";
		/** 使用时间 [非空]       **/
		public final static @type(Date.class)  String csurAddTime="csurAddTime";
	 	/** and csur_add_time >= ? */
 		public final static @type(Date.class) String csurAddTimeStart="csurAddTimeStart";
 		/** and csur_add_time <= ? */
 		public final static @type(Date.class) String csurAddTimeEnd="csurAddTimeEnd";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csurId="csur_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csurHost="csur_host";
		/** 摘要 [非空]       **/
		public final static String csurProfile="csur_profile";
		/** 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static String csurMember="csur_member";
		/** 所属免费小时  [CsFreeHour]      **/
		public final static String csurFreeHour="csur_free_hour";
		/** 所属红包  [CsCoin]      **/
		public final static String csurCoin="csur_coin";
		/** 所属优惠项  [CsGift]      **/
		public final static String csurGift="csur_gift";
		/** 所属订单 [非空] [CsOrder]      **/
		public final static String csurOrder="csur_order";
		/** 使用数量        **/
		public final static String csurCount="csur_count";
		/** 使用时间 [非空]       **/
		public final static String csurAddTime="csur_add_time";
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
				$.Set(name,CsUseRecord.getCsUseRecord(params));
			else
				$.Set(name,CsUseRecord.getCsUseRecordList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取惠免记录数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsUseRecord) $.GetRequest("CsUseRecord$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsUseRecord.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsUseRecord.getCsUseRecord(params);
			else
				value = CsUseRecord.getCsUseRecordList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsUseRecord.Get($.add(CsUseRecord.F.csurId,param));
		else if(!$.empty(param.toString()))
			value = CsUseRecord.get(Long.valueOf(param.toString()));
		$.SetRequest("CsUseRecord$"+param.hashCode(), value);
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
	public void mergeSet(CsUseRecord old){
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