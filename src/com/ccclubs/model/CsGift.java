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

@namespace("user/gift")
public @caption("优惠内容") @table("cs_gift") class CsGift implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csg_id")   @primary  @note("  ") Long csgId;// 主键 非空     
	private @caption("城市") @column("csg_host")    @relate("$csgHost") @RelateClass(SrvHost.class)  @note("  ") Long csgHost;// 非空     
 	private SrvHost $csgHost;//关联对象[运营城市]
	private @caption("优惠名称") @column("csg_name")    @note("  ") String csgName;//     
	private @caption("所属会员") @column("csg_member")    @relate("$csgMember") @RelateClass(CsMember.class)  @note("  ") Long csgMember;// 非空    ${basePath}${proname}/user/member_inquire.do 
 	private CsMember $csgMember;//关联对象[会员帐号]
	private @caption("系统套餐") @column("csg_sys_pack")    @relate("$csgSysPack") @RelateClass(CsPack.class)  @note("  所属于哪个系统套餐") Long csgSysPack;// 非空  所属于哪个系统套餐   
 	private CsPack $csgSysPack;//关联对象[系统套餐]
	private @caption("会员套餐") @column("csg_user_pack")    @relate("$csgUserPack") @RelateClass(CsUserPack.class)  @note("  ") Long csgUserPack;// 非空     
 	private CsUserPack $csgUserPack;//关联对象[用户套餐]
	private @caption("所属商品") @column("csg_goods")    @relate("$csgGoods") @RelateClass(CsGoods.class)  @note("  ") Long csgGoods;// 非空     
 	private CsGoods $csgGoods;//关联对象[商品]
	private @caption("所属网点") @column("csg_outlets")    @relate("$csgOutlets") @RelateClass(CsOutlets.class)  @note("  ") Long csgOutlets;//     
 	private CsOutlets $csgOutlets;//关联对象[网点]
	private @caption("所属车型") @column("csg_model")    @relate("$csgModel") @RelateClass(CsCarModel.class)  @note("  ") String csgModel;//     
 	private CsCarModel $csgModel;//关联对象[车型]
	private @caption("优惠类型") @column("csg_type")    @note(" 0:数量 1:价格 2:折扣  ") Short csgType;// 非空 0:数量 1:价格 2:折扣     
	private @caption("原始数量") @column("csg_count")    @note("  ") Double csgCount;//     
	private @caption("可用数量") @column("csg_remain")    @note("  ") Double csgRemain;//     
	private @caption("优惠价格") @column("csg_price")    @note("  ") Double csgPrice;//     
	private @caption("优惠折扣") @column("csg_rebate")    @note("  ") Double csgRebate;//     
	private @caption("函数脚本") @column("csg_script")    @note("  优惠项脚本，二次干预优惠项，返回false表示不能使用") String csgScript;//  优惠项脚本，二次干预优惠项，返回false表示不能使用   
	//private @caption("使用记录") @RelateClass(CsUseRecord.class) List<CsUseRecord> records;//反向关联的惠免记录列表
	private @caption("有效期至") @column("csg_until_time")    @note("  ") Date csgUntilTime;// 非空     
	private @caption("修改时间") @column("csg_update_time")    @note("  ") Date csgUpdateTime;// 非空     
	private @caption("添加时间") @column("csg_add_time")    @note("  ") Date csgAddTime;// 非空     
	private @caption("状态") @column("csg_status")    @note(" 1:有效 0:无效 2:过期 3:用完  ") Short csgStatus;// 非空 1:有效 0:无效 2:过期 3:用完     
	
	//默认构造函数
	public CsGift(){
	
	}
	
	//主键构造函数
	public CsGift(Long id){
		this.csgId = id;
	}
	
	/**所有字段构造函数 CsGift(csgHost,csgName,csgMember,csgSysPack,csgUserPack,csgGoods,csgOutlets,csgModel,csgType,csgCount,csgRemain,csgPrice,csgRebate,csgScript,csgUntilTime,csgUpdateTime,csgAddTime,csgStatus)
	 CsGift(
	 	$.getLong("csgHost")//城市 [非空]
	 	,$.getString("csgName")//优惠名称
	 	,$.getLong("csgMember")//所属会员 [非空]
	 	,$.getLong("csgSysPack")//系统套餐 [非空]
	 	,$.getLong("csgUserPack")//会员套餐 [非空]
	 	,$.getLong("csgGoods")//所属商品 [非空]
	 	,$.getLong("csgOutlets")//所属网点
	 	,$.getString("csgModel")//所属车型
	 	,$.getShort("csgType")//优惠类型 [非空]
	 	,$.getDouble("csgCount")//原始数量
	 	,$.getDouble("csgRemain")//可用数量
	 	,$.getDouble("csgPrice")//优惠价格
	 	,$.getDouble("csgRebate")//优惠折扣
	 	,$.getString("csgScript")//函数脚本
	 	,$.getDate("csgUntilTime")//有效期至 [非空]
	 	,$.getDate("csgUpdateTime")//修改时间 [非空]
	 	,$.getDate("csgAddTime")//添加时间 [非空]
	 	,$.getShort("csgStatus")//状态 [非空]
	 )
	**/
	public CsGift(Long csgHost,String csgName,Long csgMember,Long csgSysPack,Long csgUserPack,Long csgGoods,Long csgOutlets,String csgModel,Short csgType,Double csgCount,Double csgRemain,Double csgPrice,Double csgRebate,String csgScript,Date csgUntilTime,Date csgUpdateTime,Date csgAddTime,Short csgStatus){
		this.csgHost=csgHost;
		this.csgName=csgName;
		this.csgMember=csgMember;
		this.csgSysPack=csgSysPack;
		this.csgUserPack=csgUserPack;
		this.csgGoods=csgGoods;
		this.csgOutlets=csgOutlets;
		this.csgModel=csgModel;
		this.csgType=csgType;
		this.csgCount=csgCount;
		this.csgRemain=csgRemain;
		this.csgPrice=csgPrice;
		this.csgRebate=csgRebate;
		this.csgScript=csgScript;
		this.csgUntilTime=csgUntilTime;
		this.csgUpdateTime=csgUpdateTime;
		this.csgAddTime=csgAddTime;
		this.csgStatus=csgStatus;
	}
	
	//设置非空字段
	public CsGift setNotNull(Long csgHost,Long csgMember,Long csgSysPack,Long csgUserPack,Long csgGoods,Short csgType,Date csgUntilTime,Date csgUpdateTime,Date csgAddTime,Short csgStatus){
		this.csgHost=csgHost;
		this.csgMember=csgMember;
		this.csgSysPack=csgSysPack;
		this.csgUserPack=csgUserPack;
		this.csgGoods=csgGoods;
		this.csgType=csgType;
		this.csgUntilTime=csgUntilTime;
		this.csgUpdateTime=csgUpdateTime;
		this.csgAddTime=csgAddTime;
		this.csgStatus=csgStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsGift csgId(Long csgId){
		this.csgId = csgId;
		this.setSeted(F.csgId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsGift csgHost(Long csgHost){
		this.csgHost = csgHost;
		this.setSeted(F.csgHost);
		return this;
	}
	/** 优惠名称        **/
	public CsGift csgName(String csgName){
		this.csgName = csgName;
		this.setSeted(F.csgName);
		return this;
	}
	/** 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
	public CsGift csgMember(Long csgMember){
		this.csgMember = csgMember;
		this.setSeted(F.csgMember);
		return this;
	}
	/** 系统套餐 [非空] [CsPack]   所属于哪个系统套餐   **/
	public CsGift csgSysPack(Long csgSysPack){
		this.csgSysPack = csgSysPack;
		this.setSeted(F.csgSysPack);
		return this;
	}
	/** 会员套餐 [非空] [CsUserPack]      **/
	public CsGift csgUserPack(Long csgUserPack){
		this.csgUserPack = csgUserPack;
		this.setSeted(F.csgUserPack);
		return this;
	}
	/** 所属商品 [非空] [CsGoods]      **/
	public CsGift csgGoods(Long csgGoods){
		this.csgGoods = csgGoods;
		this.setSeted(F.csgGoods);
		return this;
	}
	/** 所属网点  [CsOutlets]      **/
	public CsGift csgOutlets(Long csgOutlets){
		this.csgOutlets = csgOutlets;
		this.setSeted(F.csgOutlets);
		return this;
	}
	/** 所属车型  [CsCarModel]      **/
	public CsGift csgModel(String csgModel){
		this.csgModel = csgModel;
		this.setSeted(F.csgModel);
		return this;
	}
	/** 优惠类型 [非空]   0:数量 1:价格 2:折扣     **/
	public CsGift csgType(Short csgType){
		this.csgType = csgType;
		this.setSeted(F.csgType);
		return this;
	}
	/** 原始数量        **/
	public CsGift csgCount(Double csgCount){
		this.csgCount = csgCount;
		this.setSeted(F.csgCount);
		return this;
	}
	/** 可用数量        **/
	public CsGift csgRemain(Double csgRemain){
		this.csgRemain = csgRemain;
		this.setSeted(F.csgRemain);
		return this;
	}
	/** 优惠价格        **/
	public CsGift csgPrice(Double csgPrice){
		this.csgPrice = csgPrice;
		this.setSeted(F.csgPrice);
		return this;
	}
	/** 优惠折扣        **/
	public CsGift csgRebate(Double csgRebate){
		this.csgRebate = csgRebate;
		this.setSeted(F.csgRebate);
		return this;
	}
	/** 函数脚本     优惠项脚本，二次干预优惠项，返回false表示不能使用   **/
	public CsGift csgScript(String csgScript){
		this.csgScript = csgScript;
		this.setSeted(F.csgScript);
		return this;
	}
	/** 有效期至 [非空]       **/
	public CsGift csgUntilTime(Date csgUntilTime){
		this.csgUntilTime = csgUntilTime;
		this.setSeted(F.csgUntilTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsGift csgUpdateTime(Date csgUpdateTime){
		this.csgUpdateTime = csgUpdateTime;
		this.setSeted(F.csgUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsGift csgAddTime(Date csgAddTime){
		this.csgAddTime = csgAddTime;
		this.setSeted(F.csgAddTime);
		return this;
	}
	/** 状态 [非空]   1:有效 0:无效 2:过期 3:用完     **/
	public CsGift csgStatus(Short csgStatus){
		this.csgStatus = csgStatus;
		this.setSeted(F.csgStatus);
		return this;
	}
	
	
	//克隆对象
	public CsGift clone(){
		CsGift clone = new CsGift();
		clone.csgHost=this.csgHost;
		clone.csgMember=this.csgMember;
		clone.csgSysPack=this.csgSysPack;
		clone.csgUserPack=this.csgUserPack;
		clone.csgGoods=this.csgGoods;
		clone.csgType=this.csgType;
		clone.csgUntilTime=this.csgUntilTime;
		clone.csgUpdateTime=this.csgUpdateTime;
		clone.csgAddTime=this.csgAddTime;
		clone.csgStatus=this.csgStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取优惠内容
	 * @param id
	 * @return
	 */
	public static @api CsGift get(Long id){		
		return getCsGiftById(id);
	}
	/**
	 * 获取所有优惠内容
	 * @return
	 */
	public static @api List<CsGift> list(Map params,Integer size){
		return getCsGiftList(params,size);
	}
	/**
	 * 获取优惠内容分页
	 * @return
	 */
	public static @api Page<CsGift> page(int page,int size,Map params){
		return getCsGiftPage(page, size , params);
	}
	/**
	 * 根据查询条件取优惠内容
	 * @param params
	 * @return
	 */
	public static @api CsGift Get(Map params){
		return getCsGift(params);
	}
	/**
	 * 获取优惠内容数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsGiftCount(params);
	}
	/**
	 * 获取优惠内容数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsGiftEval(eval,params);
	}
	
	/**
	 * 根据ID取优惠内容
	 * @param id
	 * @return
	 */
	public static @api CsGift getCsGiftById(Long id){		
		CsGift csGift = (CsGift) $.GetRequest("CsGift$"+id);
		if(csGift!=null)
			return csGift;
		com.ccclubs.service.admin.ICsGiftService csGiftService = $.GetSpringBean("csGiftService");		
		return csGiftService.getCsGiftById(id);
	}
	
	
	/**
	 * 根据ID取优惠内容的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsGift.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsGift csGift = get(id);
		if(csGift!=null){
			String strValue = csGift.getCsgName$();
			if(!"CsgName".equals("CsgName"))
				strValue+="("+csGift.getCsgName$()+")";
			MemCache.setValue(CsGift.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsgName$();
		if(!"CsgName".equals("CsgName"))
			keyValue+="("+this.getCsgName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有优惠内容
	 * @return
	 */
	public static @api List<CsGift> getCsGiftList(Map params,Integer size){
		com.ccclubs.service.admin.ICsGiftService csGiftService = $.GetSpringBean("csGiftService");
		return csGiftService.getCsGiftList(params, size);
	}
	
	/**
	 * 获取优惠内容分页
	 * @return
	 */
	public static @api Page<CsGift> getCsGiftPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsGiftService csGiftService = $.GetSpringBean("csGiftService");
		return csGiftService.getCsGiftPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取优惠内容
	 * @param params
	 * @return
	 */
	public static @api CsGift getCsGift(Map params){
		com.ccclubs.service.admin.ICsGiftService csGiftService = $.GetSpringBean("csGiftService");
		return csGiftService.getCsGift(params);
	}
	
	/**
	 * 获取优惠内容数
	 * @return
	 */
	public static @api Long getCsGiftCount(Map params){
		com.ccclubs.service.admin.ICsGiftService csGiftService = $.GetSpringBean("csGiftService");
		return csGiftService.getCsGiftCount(params);
	}
		
		
	/**
	 * 获取优惠内容自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsGiftEval(String eval,Map params){
		com.ccclubs.service.admin.ICsGiftService csGiftService = $.GetSpringBean("csGiftService");
		return csGiftService.getCsGiftEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsGift(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsGiftService csGiftService = $.GetSpringBean("csGiftService");
		csGiftService.updateCsGiftByConfirm(set, where);
	}
	
	
	/**
	 * 保存优惠内容对象
	 * @param params
	 * @return
	 */
	public CsGift save(){
		com.ccclubs.service.admin.ICsGiftService csGiftService = $.GetSpringBean("csGiftService");
		if(this.getCsgId()!=null)
			csGiftService.updateCsGift(this);
		else
			return csGiftService.saveCsGift(this);
		return this;
	}
	
	
	/**
	 * 更新优惠内容对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsGiftService csGiftService = $.GetSpringBean("csGiftService");
		csGiftService.updateCsGift$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsGiftService csGiftService = $.GetSpringBean("csGiftService");
		if($.equals($.config("logic_delete"),"true"))
			csGiftService.removeCsGiftById(this.getCsgId());
		else
			csGiftService.deleteCsGiftById(this.getCsgId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsGiftService csGiftService = $.GetSpringBean("csGiftService");
		return csGiftService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsgId(){
		return this.csgId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsgId$(){
		String strValue="";
		 strValue=$.str(this.getCsgId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsgId(Long csgId){
		this.csgId = csgId;
		this.setSeted(F.csgId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsgHost(){
		return this.csgHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsgHost$(){
		String strValue="";
		if(this.getCsgHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsgHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsgHost(Long csgHost){
		this.csgHost = csgHost;
		this.setSeted(F.csgHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csgHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsgHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsgHost()!=null){
 			srvHost = SrvHost.get(this.getCsgHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsgHost(), srvHost);
	 	return srvHost;
	}
	/*******************************优惠名称**********************************/	
	/**
	* 优惠名称       
	**/
	public String getCsgName(){
		return this.csgName;
	}
	/**
	* 获取优惠名称格式化(toString)
	**/
	public String getCsgName$(){
		String strValue="";
		 strValue=$.str(this.getCsgName());
	 	 return strValue;
	}
	/**
	* 优惠名称       
	**/
	public void setCsgName(String csgName){
		this.csgName = csgName;
		this.setSeted(F.csgName);
	}
	/*******************************所属会员**********************************/	
	/**
	* 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public Long getCsgMember(){
		return this.csgMember;
	}
	/**
	* 获取所属会员格式化(toString)
	**/
	public String getCsgMember$(){
		String strValue="";
		if(this.getCsgMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsgMember()));
		}			
	 	 return strValue;
	}
	/**
	* 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public void setCsgMember(Long csgMember){
		this.csgMember = csgMember;
		this.setSeted(F.csgMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csgMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsgMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsgMember()!=null){
 			csMember = CsMember.get(this.getCsgMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsgMember(), csMember);
	 	return csMember;
	}
	/*******************************系统套餐**********************************/	
	/**
	* 系统套餐 [非空] [CsPack]   所属于哪个系统套餐  
	**/
	public Long getCsgSysPack(){
		return this.csgSysPack;
	}
	/**
	* 获取系统套餐格式化(toString)
	**/
	public String getCsgSysPack$(){
		String strValue="";
		if(this.getCsgSysPack()!=null){
				strValue+=$.str(CsPack.getKeyValue(this.getCsgSysPack()));
		}			
	 	 return strValue;
	}
	/**
	* 系统套餐 [非空] [CsPack]   所属于哪个系统套餐  
	**/
	public void setCsgSysPack(Long csgSysPack){
		this.csgSysPack = csgSysPack;
		this.setSeted(F.csgSysPack);
	}
	/**
	* 获取关联对象[系统套餐]
	**/	 			
 	public CsPack get$csgSysPack(){
 		com.ccclubs.model.CsPack csPack = (com.ccclubs.model.CsPack) $.GetRequest("CsPack$"+this.getCsgSysPack());
 		if(csPack!=null)
			return csPack;
		if(this.getCsgSysPack()!=null){
 			csPack = CsPack.get(this.getCsgSysPack());
 		}
 		$.SetRequest("CsPack$"+this.getCsgSysPack(), csPack);
	 	return csPack;
	}
	/*******************************会员套餐**********************************/	
	/**
	* 会员套餐 [非空] [CsUserPack]     
	**/
	public Long getCsgUserPack(){
		return this.csgUserPack;
	}
	/**
	* 获取会员套餐格式化(toString)
	**/
	public String getCsgUserPack$(){
		String strValue="";
		if(this.getCsgUserPack()!=null){
				strValue+=$.str(CsUserPack.getKeyValue(this.getCsgUserPack()));
		}			
	 	 return strValue;
	}
	/**
	* 会员套餐 [非空] [CsUserPack]     
	**/
	public void setCsgUserPack(Long csgUserPack){
		this.csgUserPack = csgUserPack;
		this.setSeted(F.csgUserPack);
	}
	/**
	* 获取关联对象[用户套餐]
	**/	 			
 	public CsUserPack get$csgUserPack(){
 		com.ccclubs.model.CsUserPack csUserPack = (com.ccclubs.model.CsUserPack) $.GetRequest("CsUserPack$"+this.getCsgUserPack());
 		if(csUserPack!=null)
			return csUserPack;
		if(this.getCsgUserPack()!=null){
 			csUserPack = CsUserPack.get(this.getCsgUserPack());
 		}
 		$.SetRequest("CsUserPack$"+this.getCsgUserPack(), csUserPack);
	 	return csUserPack;
	}
	/*******************************所属商品**********************************/	
	/**
	* 所属商品 [非空] [CsGoods]     
	**/
	public Long getCsgGoods(){
		return this.csgGoods;
	}
	/**
	* 获取所属商品格式化(toString)
	**/
	public String getCsgGoods$(){
		String strValue="";
		if(this.getCsgGoods()!=null){
				strValue+=$.str(CsGoods.getKeyValue(this.getCsgGoods()));
		}			
	 	 return strValue;
	}
	/**
	* 所属商品 [非空] [CsGoods]     
	**/
	public void setCsgGoods(Long csgGoods){
		this.csgGoods = csgGoods;
		this.setSeted(F.csgGoods);
	}
	/**
	* 获取关联对象[商品]
	**/	 			
 	public CsGoods get$csgGoods(){
 		com.ccclubs.model.CsGoods csGoods = (com.ccclubs.model.CsGoods) $.GetRequest("CsGoods$"+this.getCsgGoods());
 		if(csGoods!=null)
			return csGoods;
		if(this.getCsgGoods()!=null){
 			csGoods = CsGoods.get(this.getCsgGoods());
 		}
 		$.SetRequest("CsGoods$"+this.getCsgGoods(), csGoods);
	 	return csGoods;
	}
	/*******************************所属网点**********************************/	
	/**
	* 所属网点  [CsOutlets]     
	**/
	public Long getCsgOutlets(){
		return this.csgOutlets;
	}
	/**
	* 获取所属网点格式化(toString)
	**/
	public String getCsgOutlets$(){
		String strValue="";
		if(this.getCsgOutlets()!=null){
				strValue+=$.str(CsOutlets.getKeyValue(this.getCsgOutlets()));
		}			
	 	 return strValue;
	}
	/**
	* 所属网点  [CsOutlets]     
	**/
	public void setCsgOutlets(Long csgOutlets){
		this.csgOutlets = csgOutlets;
		this.setSeted(F.csgOutlets);
	}
	/**
	* 获取关联对象[网点]
	**/	 			
 	public CsOutlets get$csgOutlets(){
 		com.ccclubs.model.CsOutlets csOutlets = (com.ccclubs.model.CsOutlets) $.GetRequest("CsOutlets$"+this.getCsgOutlets());
 		if(csOutlets!=null)
			return csOutlets;
		if(this.getCsgOutlets()!=null){
 			csOutlets = CsOutlets.get(this.getCsgOutlets());
 		}
 		$.SetRequest("CsOutlets$"+this.getCsgOutlets(), csOutlets);
	 	return csOutlets;
	}
	/*******************************所属车型**********************************/	
	/**
	* 所属车型  [CsCarModel]     
	**/
	public String getCsgModel(){
		return this.csgModel;
	}
	/**
	* 获取所属车型格式化(toString)
	**/
	public String getCsgModel$(){
		String strValue="";
			
		if(!$.empty(this.getCsgModel())){
			List<com.ccclubs.model.CsCarModel> items = com.ccclubs.model.CsCarModel.getCsCarModelList($.add("definex","cscm_id in ("+$.str(this.getCsgModel()).replace("#","")+")"),-1);
			for(com.ccclubs.model.CsCarModel item1:items)
				strValue+="["+$.str(item1.getCscmName())+"]";
		}
	 	 return strValue;
	}
	/**
	* 所属车型  [CsCarModel]     
	**/
	public void setCsgModel(String csgModel){
		this.csgModel = csgModel;
		this.setSeted(F.csgModel);
	}
			
	/**
	* 获取所属车型列表
	**/ 			
 	public List<com.ccclubs.model.CsCarModel> get$csgModel(){
		if(!$.empty(this.getCsgModel())){
			return com.ccclubs.model.CsCarModel.getCsCarModelList($.add("definex","cscm_id in ("+$.str(this.getCsgModel()).replace("#","")+")"),-1);
		}else
			return new ArrayList();
	}
	/*******************************优惠类型**********************************/	
	/**
	* 优惠类型 [非空]   0:数量 1:价格 2:折扣    
	**/
	public Short getCsgType(){
		return this.csgType;
	}
	/**
	* 获取优惠类型格式化(toString)
	**/
	public String getCsgType$(){
		String strValue="";
		 if($.equals($.str(this.getCsgType()),"0"))
			strValue=$.str("数量");		 
		 if($.equals($.str(this.getCsgType()),"1"))
			strValue=$.str("价格");		 
		 if($.equals($.str(this.getCsgType()),"2"))
			strValue=$.str("折扣");		 
	 	 return strValue;
	}
	/**
	* 优惠类型 [非空]   0:数量 1:价格 2:折扣    
	**/
	public void setCsgType(Short csgType){
		this.csgType = csgType;
		this.setSeted(F.csgType);
	}
	/*******************************原始数量**********************************/	
	/**
	* 原始数量       
	**/
	public Double getCsgCount(){
		return this.csgCount;
	}
	/**
	* 获取原始数量格式化(toString)
	**/
	public String getCsgCount$(){
		String strValue="";
		 strValue=$.str(this.getCsgCount());	
	 	 return strValue;
	}
	/**
	* 原始数量       
	**/
	public void setCsgCount(Double csgCount){
		this.csgCount = csgCount;
		this.setSeted(F.csgCount);
	}
	/*******************************可用数量**********************************/	
	/**
	* 可用数量       
	**/
	public Double getCsgRemain(){
		return this.csgRemain;
	}
	/**
	* 获取可用数量格式化(toString)
	**/
	public String getCsgRemain$(){
		String strValue="";
		 strValue=$.str(this.getCsgRemain());	
	 	 return strValue;
	}
	/**
	* 可用数量       
	**/
	public void setCsgRemain(Double csgRemain){
		this.csgRemain = csgRemain;
		this.setSeted(F.csgRemain);
	}
	/*******************************优惠价格**********************************/	
	/**
	* 优惠价格       
	**/
	public Double getCsgPrice(){
		return this.csgPrice;
	}
	/**
	* 获取优惠价格格式化(toString)
	**/
	public String getCsgPrice$(){
		String strValue="";
		 strValue=$.str(this.getCsgPrice());	
	 	 return strValue;
	}
	/**
	* 优惠价格       
	**/
	public void setCsgPrice(Double csgPrice){
		this.csgPrice = csgPrice;
		this.setSeted(F.csgPrice);
	}
	/*******************************优惠折扣**********************************/	
	/**
	* 优惠折扣       
	**/
	public Double getCsgRebate(){
		return this.csgRebate;
	}
	/**
	* 获取优惠折扣格式化(toString)
	**/
	public String getCsgRebate$(){
		String strValue="";
		 strValue=$.str(this.getCsgRebate());	
	 	 return strValue;
	}
	/**
	* 优惠折扣       
	**/
	public void setCsgRebate(Double csgRebate){
		this.csgRebate = csgRebate;
		this.setSeted(F.csgRebate);
	}
	/*******************************函数脚本**********************************/	
	/**
	* 函数脚本     优惠项脚本，二次干预优惠项，返回false表示不能使用  
	**/
	public String getCsgScript(){
		return this.csgScript;
	}
	/**
	* 获取函数脚本格式化(toString)
	**/
	public String getCsgScript$(){
		String strValue="";
		 strValue=$.str(this.getCsgScript());
	 	 return strValue;
	}
	/**
	* 函数脚本     优惠项脚本，二次干预优惠项，返回false表示不能使用  
	**/
	public void setCsgScript(String csgScript){
		this.csgScript = csgScript;
		this.setSeted(F.csgScript);
	}
	/*******************************反向关联的惠免记录列表**********************************/	
	/**
	* 获取与当前对象反向关联的惠免记录列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsUseRecord> getRecords(){
		com.ccclubs.service.admin.ICsUseRecordService csUseRecordService = $.GetSpringBean("csUseRecordService");
		Map params = new HashMap();
		params.put("csurGift",this.getCsgId());
		return csUseRecordService.getCsUseRecordList(params,-1);
	}
	/**
	* 获取使用记录格式化(toString)
	**/
	public String getRecords$(){
		return "[...]";
	}
	/*******************************有效期至**********************************/	
	/**
	* 有效期至 [非空]      
	**/
	public Date getCsgUntilTime(){
		return this.csgUntilTime;
	}
	/**
	* 获取有效期至格式化(toString)
	**/
	public String getCsgUntilTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsgUntilTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 有效期至 [非空]      
	**/
	public void setCsgUntilTime(Date csgUntilTime){
		this.csgUntilTime = csgUntilTime;
		this.setSeted(F.csgUntilTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsgUpdateTime(){
		return this.csgUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsgUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsgUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsgUpdateTime(Date csgUpdateTime){
		this.csgUpdateTime = csgUpdateTime;
		this.setSeted(F.csgUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsgAddTime(){
		return this.csgAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsgAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsgAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsgAddTime(Date csgAddTime){
		this.csgAddTime = csgAddTime;
		this.setSeted(F.csgAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:有效 0:无效 2:过期 3:用完    
	**/
	public Short getCsgStatus(){
		return this.csgStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsgStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsgStatus()),"1"))
			strValue=$.str("有效");		 
		 if($.equals($.str(this.getCsgStatus()),"0"))
			strValue=$.str("无效");		 
		 if($.equals($.str(this.getCsgStatus()),"2"))
			strValue=$.str("过期");		 
		 if($.equals($.str(this.getCsgStatus()),"3"))
			strValue=$.str("用完");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:有效 0:无效 2:过期 3:用完    
	**/
	public void setCsgStatus(Short csgStatus){
		this.csgStatus = csgStatus;
		this.setSeted(F.csgStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsGift.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsGift.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsGift.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsGift.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsGift.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsGift.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsGift.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsGift.this);
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
		public M csgId(Object csgId){this.put("csgId", csgId);return this;};
	 	/** and csg_id is null */
 		public M csgIdNull(){if(this.get("csgIdNot")==null)this.put("csgIdNot", "");this.put("csgId", null);return this;};
 		/** not .... */
 		public M csgIdNot(){this.put("csgIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csgHost(Object csgHost){this.put("csgHost", csgHost);return this;};
	 	/** and csg_host is null */
 		public M csgHostNull(){if(this.get("csgHostNot")==null)this.put("csgHostNot", "");this.put("csgHost", null);return this;};
 		/** not .... */
 		public M csgHostNot(){this.put("csgHostNot", "not");return this;};
		/** 优惠名称        **/
		public M csgName(Object csgName){this.put("csgName", csgName);return this;};
	 	/** and csg_name is null */
 		public M csgNameNull(){if(this.get("csgNameNot")==null)this.put("csgNameNot", "");this.put("csgName", null);return this;};
 		/** not .... */
 		public M csgNameNot(){this.put("csgNameNot", "not");return this;};
		/** 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public M csgMember(Object csgMember){this.put("csgMember", csgMember);return this;};
	 	/** and csg_member is null */
 		public M csgMemberNull(){if(this.get("csgMemberNot")==null)this.put("csgMemberNot", "");this.put("csgMember", null);return this;};
 		/** not .... */
 		public M csgMemberNot(){this.put("csgMemberNot", "not");return this;};
		public M csgMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csgMember$on", value);
			return this;
		};	
		/** 系统套餐 [非空] [CsPack]   所属于哪个系统套餐   **/
		public M csgSysPack(Object csgSysPack){this.put("csgSysPack", csgSysPack);return this;};
	 	/** and csg_sys_pack is null */
 		public M csgSysPackNull(){if(this.get("csgSysPackNot")==null)this.put("csgSysPackNot", "");this.put("csgSysPack", null);return this;};
 		/** not .... */
 		public M csgSysPackNot(){this.put("csgSysPackNot", "not");return this;};
		public M csgSysPack$on(CsPack.M value){
			this.put("CsPack", value);
			this.put("csgSysPack$on", value);
			return this;
		};	
		/** 会员套餐 [非空] [CsUserPack]      **/
		public M csgUserPack(Object csgUserPack){this.put("csgUserPack", csgUserPack);return this;};
	 	/** and csg_user_pack is null */
 		public M csgUserPackNull(){if(this.get("csgUserPackNot")==null)this.put("csgUserPackNot", "");this.put("csgUserPack", null);return this;};
 		/** not .... */
 		public M csgUserPackNot(){this.put("csgUserPackNot", "not");return this;};
		public M csgUserPack$on(CsUserPack.M value){
			this.put("CsUserPack", value);
			this.put("csgUserPack$on", value);
			return this;
		};	
		/** 所属商品 [非空] [CsGoods]      **/
		public M csgGoods(Object csgGoods){this.put("csgGoods", csgGoods);return this;};
	 	/** and csg_goods is null */
 		public M csgGoodsNull(){if(this.get("csgGoodsNot")==null)this.put("csgGoodsNot", "");this.put("csgGoods", null);return this;};
 		/** not .... */
 		public M csgGoodsNot(){this.put("csgGoodsNot", "not");return this;};
		public M csgGoods$on(CsGoods.M value){
			this.put("CsGoods", value);
			this.put("csgGoods$on", value);
			return this;
		};	
		/** 所属网点  [CsOutlets]      **/
		public M csgOutlets(Object csgOutlets){this.put("csgOutlets", csgOutlets);return this;};
	 	/** and csg_outlets is null */
 		public M csgOutletsNull(){if(this.get("csgOutletsNot")==null)this.put("csgOutletsNot", "");this.put("csgOutlets", null);return this;};
 		/** not .... */
 		public M csgOutletsNot(){this.put("csgOutletsNot", "not");return this;};
		public M csgOutlets$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("csgOutlets$on", value);
			return this;
		};	
		/** 所属车型  [CsCarModel]      **/
		public M csgModel(Object csgModel){this.put("csgModel", csgModel);return this;};
	 	/** and csg_model is null */
 		public M csgModelNull(){if(this.get("csgModelNot")==null)this.put("csgModelNot", "");this.put("csgModel", null);return this;};
 		/** not .... */
 		public M csgModelNot(){this.put("csgModelNot", "not");return this;};
		/** 优惠类型 [非空]   0:数量 1:价格 2:折扣     **/
		public M csgType(Object csgType){this.put("csgType", csgType);return this;};
	 	/** and csg_type is null */
 		public M csgTypeNull(){if(this.get("csgTypeNot")==null)this.put("csgTypeNot", "");this.put("csgType", null);return this;};
 		/** not .... */
 		public M csgTypeNot(){this.put("csgTypeNot", "not");return this;};
		/** 原始数量        **/
		public M csgCount(Object csgCount){this.put("csgCount", csgCount);return this;};
	 	/** and csg_count is null */
 		public M csgCountNull(){if(this.get("csgCountNot")==null)this.put("csgCountNot", "");this.put("csgCount", null);return this;};
 		/** not .... */
 		public M csgCountNot(){this.put("csgCountNot", "not");return this;};
		/** and csg_count >= ? */
		public M csgCountMin(Object min){this.put("csgCountMin", min);return this;};
		/** and csg_count <= ? */
		public M csgCountMax(Object max){this.put("csgCountMax", max);return this;};
		/** 可用数量        **/
		public M csgRemain(Object csgRemain){this.put("csgRemain", csgRemain);return this;};
	 	/** and csg_remain is null */
 		public M csgRemainNull(){if(this.get("csgRemainNot")==null)this.put("csgRemainNot", "");this.put("csgRemain", null);return this;};
 		/** not .... */
 		public M csgRemainNot(){this.put("csgRemainNot", "not");return this;};
		/** and csg_remain >= ? */
		public M csgRemainMin(Object min){this.put("csgRemainMin", min);return this;};
		/** and csg_remain <= ? */
		public M csgRemainMax(Object max){this.put("csgRemainMax", max);return this;};
		/** 优惠价格        **/
		public M csgPrice(Object csgPrice){this.put("csgPrice", csgPrice);return this;};
	 	/** and csg_price is null */
 		public M csgPriceNull(){if(this.get("csgPriceNot")==null)this.put("csgPriceNot", "");this.put("csgPrice", null);return this;};
 		/** not .... */
 		public M csgPriceNot(){this.put("csgPriceNot", "not");return this;};
		/** and csg_price >= ? */
		public M csgPriceMin(Object min){this.put("csgPriceMin", min);return this;};
		/** and csg_price <= ? */
		public M csgPriceMax(Object max){this.put("csgPriceMax", max);return this;};
		/** 优惠折扣        **/
		public M csgRebate(Object csgRebate){this.put("csgRebate", csgRebate);return this;};
	 	/** and csg_rebate is null */
 		public M csgRebateNull(){if(this.get("csgRebateNot")==null)this.put("csgRebateNot", "");this.put("csgRebate", null);return this;};
 		/** not .... */
 		public M csgRebateNot(){this.put("csgRebateNot", "not");return this;};
		/** and csg_rebate >= ? */
		public M csgRebateMin(Object min){this.put("csgRebateMin", min);return this;};
		/** and csg_rebate <= ? */
		public M csgRebateMax(Object max){this.put("csgRebateMax", max);return this;};
		/** 函数脚本     优惠项脚本，二次干预优惠项，返回false表示不能使用   **/
		public M csgScript(Object csgScript){this.put("csgScript", csgScript);return this;};
	 	/** and csg_script is null */
 		public M csgScriptNull(){if(this.get("csgScriptNot")==null)this.put("csgScriptNot", "");this.put("csgScript", null);return this;};
 		/** not .... */
 		public M csgScriptNot(){this.put("csgScriptNot", "not");return this;};
		/** 有效期至 [非空]       **/
		public M csgUntilTime(Object csgUntilTime){this.put("csgUntilTime", csgUntilTime);return this;};
	 	/** and csg_until_time is null */
 		public M csgUntilTimeNull(){if(this.get("csgUntilTimeNot")==null)this.put("csgUntilTimeNot", "");this.put("csgUntilTime", null);return this;};
 		/** not .... */
 		public M csgUntilTimeNot(){this.put("csgUntilTimeNot", "not");return this;};
 		/** and csg_until_time >= ? */
 		public M csgUntilTimeStart(Object start){this.put("csgUntilTimeStart", start);return this;};			
 		/** and csg_until_time <= ? */
 		public M csgUntilTimeEnd(Object end){this.put("csgUntilTimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M csgUpdateTime(Object csgUpdateTime){this.put("csgUpdateTime", csgUpdateTime);return this;};
	 	/** and csg_update_time is null */
 		public M csgUpdateTimeNull(){if(this.get("csgUpdateTimeNot")==null)this.put("csgUpdateTimeNot", "");this.put("csgUpdateTime", null);return this;};
 		/** not .... */
 		public M csgUpdateTimeNot(){this.put("csgUpdateTimeNot", "not");return this;};
 		/** and csg_update_time >= ? */
 		public M csgUpdateTimeStart(Object start){this.put("csgUpdateTimeStart", start);return this;};			
 		/** and csg_update_time <= ? */
 		public M csgUpdateTimeEnd(Object end){this.put("csgUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csgAddTime(Object csgAddTime){this.put("csgAddTime", csgAddTime);return this;};
	 	/** and csg_add_time is null */
 		public M csgAddTimeNull(){if(this.get("csgAddTimeNot")==null)this.put("csgAddTimeNot", "");this.put("csgAddTime", null);return this;};
 		/** not .... */
 		public M csgAddTimeNot(){this.put("csgAddTimeNot", "not");return this;};
 		/** and csg_add_time >= ? */
 		public M csgAddTimeStart(Object start){this.put("csgAddTimeStart", start);return this;};			
 		/** and csg_add_time <= ? */
 		public M csgAddTimeEnd(Object end){this.put("csgAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:有效 0:无效 2:过期 3:用完     **/
		public M csgStatus(Object csgStatus){this.put("csgStatus", csgStatus);return this;};
	 	/** and csg_status is null */
 		public M csgStatusNull(){if(this.get("csgStatusNot")==null)this.put("csgStatusNot", "");this.put("csgStatus", null);return this;};
 		/** not .... */
 		public M csgStatusNot(){this.put("csgStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有优惠内容 **/
		public @api List<CsGift> list(Integer size){
			return getCsGiftList(this,size);
		}
		/** 获取优惠内容分页 **/
		public @api Page<CsGift> page(int page,int size){
			return getCsGiftPage(page, size , this);
		}
		/** 根据查询条件取优惠内容 **/
		public @api CsGift get(){
			return getCsGift(this);
		}
		/** 获取优惠内容数 **/
		public @api Long count(){
			return getCsGiftCount(this);
		}
		/** 获取优惠内容表达式 **/
		public @api <T> T eval(String strEval){
			return getCsGiftEval(strEval,this);
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
			updateCsGift(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csgId="csgId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csgHost="csgHost";
		/** 优惠名称        **/
		public final static @type(String.class) @like String csgName="csgName";
		/** 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static @type(Long.class)  String csgMember="csgMember";
		/** 系统套餐 [非空] [CsPack]   所属于哪个系统套餐   **/
		public final static @type(Long.class)  String csgSysPack="csgSysPack";
		/** 会员套餐 [非空] [CsUserPack]      **/
		public final static @type(Long.class)  String csgUserPack="csgUserPack";
		/** 所属商品 [非空] [CsGoods]      **/
		public final static @type(Long.class)  String csgGoods="csgGoods";
		/** 所属网点  [CsOutlets]      **/
		public final static @type(Long.class)  String csgOutlets="csgOutlets";
		/** 所属车型  [CsCarModel]      **/
		public final static @type(String.class) @like String csgModel="csgModel";
		/** 优惠类型 [非空]   0:数量 1:价格 2:折扣     **/
		public final static @type(Short.class)  String csgType="csgType";
		/** 原始数量        **/
		public final static @type(Double.class)  String csgCount="csgCount";
		/** and csg_count >= ? */
		public final static @type(Double.class) String csgCountMin="csgCountMin";
		/** and csg_count <= ? */
		public final static @type(Double.class) String csgCountMax="csgCountMax";
		/** 可用数量        **/
		public final static @type(Double.class)  String csgRemain="csgRemain";
		/** and csg_remain >= ? */
		public final static @type(Double.class) String csgRemainMin="csgRemainMin";
		/** and csg_remain <= ? */
		public final static @type(Double.class) String csgRemainMax="csgRemainMax";
		/** 优惠价格        **/
		public final static @type(Double.class)  String csgPrice="csgPrice";
		/** and csg_price >= ? */
		public final static @type(Double.class) String csgPriceMin="csgPriceMin";
		/** and csg_price <= ? */
		public final static @type(Double.class) String csgPriceMax="csgPriceMax";
		/** 优惠折扣        **/
		public final static @type(Double.class)  String csgRebate="csgRebate";
		/** and csg_rebate >= ? */
		public final static @type(Double.class) String csgRebateMin="csgRebateMin";
		/** and csg_rebate <= ? */
		public final static @type(Double.class) String csgRebateMax="csgRebateMax";
		/** 函数脚本     优惠项脚本，二次干预优惠项，返回false表示不能使用   **/
		public final static @type(String.class) @like String csgScript="csgScript";
		/** 有效期至 [非空]       **/
		public final static @type(Date.class)  String csgUntilTime="csgUntilTime";
	 	/** and csg_until_time >= ? */
 		public final static @type(Date.class) String csgUntilTimeStart="csgUntilTimeStart";
 		/** and csg_until_time <= ? */
 		public final static @type(Date.class) String csgUntilTimeEnd="csgUntilTimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csgUpdateTime="csgUpdateTime";
	 	/** and csg_update_time >= ? */
 		public final static @type(Date.class) String csgUpdateTimeStart="csgUpdateTimeStart";
 		/** and csg_update_time <= ? */
 		public final static @type(Date.class) String csgUpdateTimeEnd="csgUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csgAddTime="csgAddTime";
	 	/** and csg_add_time >= ? */
 		public final static @type(Date.class) String csgAddTimeStart="csgAddTimeStart";
 		/** and csg_add_time <= ? */
 		public final static @type(Date.class) String csgAddTimeEnd="csgAddTimeEnd";
		/** 状态 [非空]   1:有效 0:无效 2:过期 3:用完     **/
		public final static @type(Short.class)  String csgStatus="csgStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csgId="csg_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csgHost="csg_host";
		/** 优惠名称        **/
		public final static String csgName="csg_name";
		/** 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static String csgMember="csg_member";
		/** 系统套餐 [非空] [CsPack]   所属于哪个系统套餐   **/
		public final static String csgSysPack="csg_sys_pack";
		/** 会员套餐 [非空] [CsUserPack]      **/
		public final static String csgUserPack="csg_user_pack";
		/** 所属商品 [非空] [CsGoods]      **/
		public final static String csgGoods="csg_goods";
		/** 所属网点  [CsOutlets]      **/
		public final static String csgOutlets="csg_outlets";
		/** 所属车型  [CsCarModel]      **/
		public final static String csgModel="csg_model";
		/** 优惠类型 [非空]   0:数量 1:价格 2:折扣     **/
		public final static String csgType="csg_type";
		/** 原始数量        **/
		public final static String csgCount="csg_count";
		/** 可用数量        **/
		public final static String csgRemain="csg_remain";
		/** 优惠价格        **/
		public final static String csgPrice="csg_price";
		/** 优惠折扣        **/
		public final static String csgRebate="csg_rebate";
		/** 函数脚本     优惠项脚本，二次干预优惠项，返回false表示不能使用   **/
		public final static String csgScript="csg_script";
		/** 有效期至 [非空]       **/
		public final static String csgUntilTime="csg_until_time";
		/** 修改时间 [非空]       **/
		public final static String csgUpdateTime="csg_update_time";
		/** 添加时间 [非空]       **/
		public final static String csgAddTime="csg_add_time";
		/** 状态 [非空]   1:有效 0:无效 2:过期 3:用完     **/
		public final static String csgStatus="csg_status";
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
				$.Set(name,CsGift.getCsGift(params));
			else
				$.Set(name,CsGift.getCsGiftList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取优惠内容数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsGift) $.GetRequest("CsGift$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsGift.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsGift.getCsGift(params);
			else
				value = CsGift.getCsGiftList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsGift.Get($.add(CsGift.F.csgId,param));
		else if(!$.empty(param.toString()))
			value = CsGift.get(Long.valueOf(param.toString()));
		$.SetRequest("CsGift$"+param.hashCode(), value);
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
	public void mergeSet(CsGift old){
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