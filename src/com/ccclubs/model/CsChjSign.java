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

@namespace("operate/chjsign")
public @caption("春节套餐报名") @table("cs_chj_sign") class CsChjSign implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cscs_id")   @primary  @note("  ") Long cscsId;// 主键 非空     
	private @caption("城市") @column("cscs_host")    @relate("$cscsHost") @RelateClass(SrvHost.class)  @note("  ") Long cscsHost;// 非空     
 	private SrvHost $cscsHost;//关联对象[运营城市]
	private @caption("姓名") @column("cscs_name")    @note("  ") String cscsName;// 非空     
	private @caption("手机号码") @column("cscs_mobile")    @note("  ") String cscsMobile;// 非空     
	private @caption("关联会员") @column("cscs_member")    @relate("$cscsMember") @RelateClass(CsMember.class)  @note("  ") Long cscsMember;// 非空     
 	private CsMember $cscsMember;//关联对象[会员帐号]
	private @caption("取车时间") @column("cscs_take_time")    @note("  ") Date cscsTakeTime;//     
	private @caption("还车时间") @column("cscs_ret_time")    @note("  ") Date cscsRetTime;//     
	private @caption("预定车型") @column("cscs_model")    @relate("$cscsModel") @RelateClass(CsCarModel.class)  @note("  ") Long cscsModel;//     
 	private CsCarModel $cscsModel;//关联对象[车型]
	private @caption("预定网点") @column("cscs_outlets")    @relate("$cscsOutlets") @RelateClass(CsOutlets.class)  @note("  ") Long cscsOutlets;//     
 	private CsOutlets $cscsOutlets;//关联对象[网点]
	private @caption("目的地") @column("cscs_target")    @note("  ") String cscsTarget;//     
	private @caption("预定套餐") @column("cscs_locked")    @relate("$cscsLocked") @RelateClass(CsPack.class)  @note("  ") Long cscsLocked;//     
 	private CsPack $cscsLocked;//关联对象[系统套餐]
	private @caption("预定时间") @column("cscs_lock_time")    @note("  ") Date cscsLockTime;//     
	private @caption("备注") @column("cscs_remark")    @note("  ") String cscsRemark;//     
	private @caption("年份") @column("cscs_year")    @note(" 2014:2014年 2015:2015年  ") Short cscsYear;// 2014:2014年 2015:2015年     
	private @caption("渠道来源") @column("cscs_channel")    @relate("$cscsVisitChannel") @RelateClass(CsChannel.class)  @note("  ") Long cscsVisitChannel;//     
 	private CsChannel $cscsVisitChannel;//关联对象[访问渠道]
	private @caption("修改时间") @column("cscs_update_time")    @note("  ") Date cscsUpdateTime;// 非空     
	private @caption("添加时间") @column("cscs_add_time")    @note("  ") Date cscsAddTime;// 非空     
	private @caption("状态") @column("cscs_status")    @note(" 0:已报名 1:已预定 2:已购买  ") Short cscsStatus;// 非空 0:已报名 1:已预定 2:已购买     
	
	//默认构造函数
	public CsChjSign(){
	
	}
	
	//主键构造函数
	public CsChjSign(Long id){
		this.cscsId = id;
	}
	
	/**所有字段构造函数 CsChjSign(cscsHost,cscsName,cscsMobile,cscsMember,cscsTakeTime,cscsRetTime,cscsModel,cscsOutlets,cscsTarget,cscsLocked,cscsLockTime,cscsRemark,cscsYear,cscsVisitChannel,cscsUpdateTime,cscsAddTime,cscsStatus)
	 CsChjSign(
	 	$.getLong("cscsHost")//城市 [非空]
	 	,$.getString("cscsName")//姓名 [非空]
	 	,$.getString("cscsMobile")//手机号码 [非空]
	 	,$.getLong("cscsMember")//关联会员 [非空]
	 	,$.getDate("cscsTakeTime")//取车时间
	 	,$.getDate("cscsRetTime")//还车时间
	 	,$.getLong("cscsModel")//预定车型
	 	,$.getLong("cscsOutlets")//预定网点
	 	,$.getString("cscsTarget")//目的地
	 	,$.getLong("cscsLocked")//预定套餐
	 	,$.getDate("cscsLockTime")//预定时间
	 	,$.getString("cscsRemark")//备注
	 	,$.getShort("cscsYear")//年份
	 	,$.getLong("cscsVisitChannel")//渠道来源
	 	,$.getDate("cscsUpdateTime")//修改时间 [非空]
	 	,$.getDate("cscsAddTime")//添加时间 [非空]
	 	,$.getShort("cscsStatus")//状态 [非空]
	 )
	**/
	public CsChjSign(Long cscsHost,String cscsName,String cscsMobile,Long cscsMember,Date cscsTakeTime,Date cscsRetTime,Long cscsModel,Long cscsOutlets,String cscsTarget,Long cscsLocked,Date cscsLockTime,String cscsRemark,Short cscsYear,Long cscsVisitChannel,Date cscsUpdateTime,Date cscsAddTime,Short cscsStatus){
		this.cscsHost=cscsHost;
		this.cscsName=cscsName;
		this.cscsMobile=cscsMobile;
		this.cscsMember=cscsMember;
		this.cscsTakeTime=cscsTakeTime;
		this.cscsRetTime=cscsRetTime;
		this.cscsModel=cscsModel;
		this.cscsOutlets=cscsOutlets;
		this.cscsTarget=cscsTarget;
		this.cscsLocked=cscsLocked;
		this.cscsLockTime=cscsLockTime;
		this.cscsRemark=cscsRemark;
		this.cscsYear=cscsYear;
		this.cscsVisitChannel=cscsVisitChannel;
		this.cscsUpdateTime=cscsUpdateTime;
		this.cscsAddTime=cscsAddTime;
		this.cscsStatus=cscsStatus;
	}
	
	//设置非空字段
	public CsChjSign setNotNull(Long cscsHost,String cscsName,String cscsMobile,Long cscsMember,Date cscsUpdateTime,Date cscsAddTime,Short cscsStatus){
		this.cscsHost=cscsHost;
		this.cscsName=cscsName;
		this.cscsMobile=cscsMobile;
		this.cscsMember=cscsMember;
		this.cscsUpdateTime=cscsUpdateTime;
		this.cscsAddTime=cscsAddTime;
		this.cscsStatus=cscsStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsChjSign cscsId(Long cscsId){
		this.cscsId = cscsId;
		this.setSeted(F.cscsId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsChjSign cscsHost(Long cscsHost){
		this.cscsHost = cscsHost;
		this.setSeted(F.cscsHost);
		return this;
	}
	/** 姓名 [非空]       **/
	public CsChjSign cscsName(String cscsName){
		this.cscsName = cscsName;
		this.setSeted(F.cscsName);
		return this;
	}
	/** 手机号码 [非空]       **/
	public CsChjSign cscsMobile(String cscsMobile){
		this.cscsMobile = cscsMobile;
		this.setSeted(F.cscsMobile);
		return this;
	}
	/** 关联会员 [非空] [CsMember]      **/
	public CsChjSign cscsMember(Long cscsMember){
		this.cscsMember = cscsMember;
		this.setSeted(F.cscsMember);
		return this;
	}
	/** 取车时间        **/
	public CsChjSign cscsTakeTime(Date cscsTakeTime){
		this.cscsTakeTime = cscsTakeTime;
		this.setSeted(F.cscsTakeTime);
		return this;
	}
	/** 还车时间        **/
	public CsChjSign cscsRetTime(Date cscsRetTime){
		this.cscsRetTime = cscsRetTime;
		this.setSeted(F.cscsRetTime);
		return this;
	}
	/** 预定车型  [CsCarModel]      **/
	public CsChjSign cscsModel(Long cscsModel){
		this.cscsModel = cscsModel;
		this.setSeted(F.cscsModel);
		return this;
	}
	/** 预定网点  [CsOutlets]      **/
	public CsChjSign cscsOutlets(Long cscsOutlets){
		this.cscsOutlets = cscsOutlets;
		this.setSeted(F.cscsOutlets);
		return this;
	}
	/** 目的地        **/
	public CsChjSign cscsTarget(String cscsTarget){
		this.cscsTarget = cscsTarget;
		this.setSeted(F.cscsTarget);
		return this;
	}
	/** 预定套餐  [CsPack]      **/
	public CsChjSign cscsLocked(Long cscsLocked){
		this.cscsLocked = cscsLocked;
		this.setSeted(F.cscsLocked);
		return this;
	}
	/** 预定时间        **/
	public CsChjSign cscsLockTime(Date cscsLockTime){
		this.cscsLockTime = cscsLockTime;
		this.setSeted(F.cscsLockTime);
		return this;
	}
	/** 备注        **/
	public CsChjSign cscsRemark(String cscsRemark){
		this.cscsRemark = cscsRemark;
		this.setSeted(F.cscsRemark);
		return this;
	}
	/** 年份    2014:2014年 2015:2015年     **/
	public CsChjSign cscsYear(Short cscsYear){
		this.cscsYear = cscsYear;
		this.setSeted(F.cscsYear);
		return this;
	}
	/** 渠道来源  [CsChannel]      **/
	public CsChjSign cscsVisitChannel(Long cscsVisitChannel){
		this.cscsVisitChannel = cscsVisitChannel;
		this.setSeted(F.cscsVisitChannel);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsChjSign cscsUpdateTime(Date cscsUpdateTime){
		this.cscsUpdateTime = cscsUpdateTime;
		this.setSeted(F.cscsUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsChjSign cscsAddTime(Date cscsAddTime){
		this.cscsAddTime = cscsAddTime;
		this.setSeted(F.cscsAddTime);
		return this;
	}
	/** 状态 [非空]   0:已报名 1:已预定 2:已购买     **/
	public CsChjSign cscsStatus(Short cscsStatus){
		this.cscsStatus = cscsStatus;
		this.setSeted(F.cscsStatus);
		return this;
	}
	
	
	//克隆对象
	public CsChjSign clone(){
		CsChjSign clone = new CsChjSign();
		clone.cscsHost=this.cscsHost;
		clone.cscsName=this.cscsName;
		clone.cscsMobile=this.cscsMobile;
		clone.cscsMember=this.cscsMember;
		clone.cscsUpdateTime=this.cscsUpdateTime;
		clone.cscsAddTime=this.cscsAddTime;
		clone.cscsStatus=this.cscsStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取春节套餐报名
	 * @param id
	 * @return
	 */
	public static @api CsChjSign get(Long id){		
		return getCsChjSignById(id);
	}
	/**
	 * 获取所有春节套餐报名
	 * @return
	 */
	public static @api List<CsChjSign> list(Map params,Integer size){
		return getCsChjSignList(params,size);
	}
	/**
	 * 获取春节套餐报名分页
	 * @return
	 */
	public static @api Page<CsChjSign> page(int page,int size,Map params){
		return getCsChjSignPage(page, size , params);
	}
	/**
	 * 根据查询条件取春节套餐报名
	 * @param params
	 * @return
	 */
	public static @api CsChjSign Get(Map params){
		return getCsChjSign(params);
	}
	/**
	 * 获取春节套餐报名数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsChjSignCount(params);
	}
	/**
	 * 获取春节套餐报名数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsChjSignEval(eval,params);
	}
	
	/**
	 * 根据ID取春节套餐报名
	 * @param id
	 * @return
	 */
	public static @api CsChjSign getCsChjSignById(Long id){		
		CsChjSign csChjSign = (CsChjSign) $.GetRequest("CsChjSign$"+id);
		if(csChjSign!=null)
			return csChjSign;
		com.ccclubs.service.admin.ICsChjSignService csChjSignService = $.GetSpringBean("csChjSignService");		
		return csChjSignService.getCsChjSignById(id);
	}
	
	
	/**
	 * 根据ID取春节套餐报名的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsChjSign.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsChjSign csChjSign = get(id);
		if(csChjSign!=null){
			String strValue = csChjSign.getCscsName$();
			if(!"CscsName".equals("CscsName"))
				strValue+="("+csChjSign.getCscsName$()+")";
			MemCache.setValue(CsChjSign.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCscsName$();
		if(!"CscsName".equals("CscsName"))
			keyValue+="("+this.getCscsName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有春节套餐报名
	 * @return
	 */
	public static @api List<CsChjSign> getCsChjSignList(Map params,Integer size){
		com.ccclubs.service.admin.ICsChjSignService csChjSignService = $.GetSpringBean("csChjSignService");
		return csChjSignService.getCsChjSignList(params, size);
	}
	
	/**
	 * 获取春节套餐报名分页
	 * @return
	 */
	public static @api Page<CsChjSign> getCsChjSignPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsChjSignService csChjSignService = $.GetSpringBean("csChjSignService");
		return csChjSignService.getCsChjSignPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取春节套餐报名
	 * @param params
	 * @return
	 */
	public static @api CsChjSign getCsChjSign(Map params){
		com.ccclubs.service.admin.ICsChjSignService csChjSignService = $.GetSpringBean("csChjSignService");
		return csChjSignService.getCsChjSign(params);
	}
	
	/**
	 * 获取春节套餐报名数
	 * @return
	 */
	public static @api Long getCsChjSignCount(Map params){
		com.ccclubs.service.admin.ICsChjSignService csChjSignService = $.GetSpringBean("csChjSignService");
		return csChjSignService.getCsChjSignCount(params);
	}
		
		
	/**
	 * 获取春节套餐报名自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsChjSignEval(String eval,Map params){
		com.ccclubs.service.admin.ICsChjSignService csChjSignService = $.GetSpringBean("csChjSignService");
		return csChjSignService.getCsChjSignEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsChjSign(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsChjSignService csChjSignService = $.GetSpringBean("csChjSignService");
		csChjSignService.updateCsChjSignByConfirm(set, where);
	}
	
	
	/**
	 * 保存春节套餐报名对象
	 * @param params
	 * @return
	 */
	public CsChjSign save(){
		com.ccclubs.service.admin.ICsChjSignService csChjSignService = $.GetSpringBean("csChjSignService");
		if(this.getCscsId()!=null)
			csChjSignService.updateCsChjSign(this);
		else
			return csChjSignService.saveCsChjSign(this);
		return this;
	}
	
	
	/**
	 * 更新春节套餐报名对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsChjSignService csChjSignService = $.GetSpringBean("csChjSignService");
		csChjSignService.updateCsChjSign$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsChjSignService csChjSignService = $.GetSpringBean("csChjSignService");
		if($.equals($.config("logic_delete"),"true"))
			csChjSignService.removeCsChjSignById(this.getCscsId());
		else
			csChjSignService.deleteCsChjSignById(this.getCscsId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsChjSignService csChjSignService = $.GetSpringBean("csChjSignService");
		return csChjSignService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCscsId(){
		return this.cscsId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCscsId$(){
		String strValue="";
		 strValue=$.str(this.getCscsId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCscsId(Long cscsId){
		this.cscsId = cscsId;
		this.setSeted(F.cscsId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCscsHost(){
		return this.cscsHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCscsHost$(){
		String strValue="";
		if(this.getCscsHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCscsHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCscsHost(Long cscsHost){
		this.cscsHost = cscsHost;
		this.setSeted(F.cscsHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cscsHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCscsHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCscsHost()!=null){
 			srvHost = SrvHost.get(this.getCscsHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCscsHost(), srvHost);
	 	return srvHost;
	}
	/*******************************姓名**********************************/	
	/**
	* 姓名 [非空]      
	**/
	public String getCscsName(){
		return this.cscsName;
	}
	/**
	* 获取姓名格式化(toString)
	**/
	public String getCscsName$(){
		String strValue="";
		 strValue=$.str(this.getCscsName());
	 	 return strValue;
	}
	/**
	* 姓名 [非空]      
	**/
	public void setCscsName(String cscsName){
		this.cscsName = cscsName;
		this.setSeted(F.cscsName);
	}
	/*******************************手机号码**********************************/	
	/**
	* 手机号码 [非空]      
	**/
	public String getCscsMobile(){
		return this.cscsMobile;
	}
	/**
	* 获取手机号码格式化(toString)
	**/
	public String getCscsMobile$(){
		String strValue="";
		 strValue=$.str(this.getCscsMobile());
	 	 return strValue;
	}
	/**
	* 手机号码 [非空]      
	**/
	public void setCscsMobile(String cscsMobile){
		this.cscsMobile = cscsMobile;
		this.setSeted(F.cscsMobile);
	}
	/*******************************关联会员**********************************/	
	/**
	* 关联会员 [非空] [CsMember]     
	**/
	public Long getCscsMember(){
		return this.cscsMember;
	}
	/**
	* 获取关联会员格式化(toString)
	**/
	public String getCscsMember$(){
		String strValue="";
		if(this.getCscsMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCscsMember()));
		}			
	 	 return strValue;
	}
	/**
	* 关联会员 [非空] [CsMember]     
	**/
	public void setCscsMember(Long cscsMember){
		this.cscsMember = cscsMember;
		this.setSeted(F.cscsMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$cscsMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCscsMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCscsMember()!=null){
 			csMember = CsMember.get(this.getCscsMember());
 		}
 		$.SetRequest("CsMember$"+this.getCscsMember(), csMember);
	 	return csMember;
	}
	/*******************************取车时间**********************************/	
	/**
	* 取车时间       
	**/
	public Date getCscsTakeTime(){
		return this.cscsTakeTime;
	}
	/**
	* 获取取车时间格式化(toString)
	**/
	public String getCscsTakeTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscsTakeTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 取车时间       
	**/
	public void setCscsTakeTime(Date cscsTakeTime){
		this.cscsTakeTime = cscsTakeTime;
		this.setSeted(F.cscsTakeTime);
	}
	/*******************************还车时间**********************************/	
	/**
	* 还车时间       
	**/
	public Date getCscsRetTime(){
		return this.cscsRetTime;
	}
	/**
	* 获取还车时间格式化(toString)
	**/
	public String getCscsRetTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscsRetTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 还车时间       
	**/
	public void setCscsRetTime(Date cscsRetTime){
		this.cscsRetTime = cscsRetTime;
		this.setSeted(F.cscsRetTime);
	}
	/*******************************预定车型**********************************/	
	/**
	* 预定车型  [CsCarModel]     
	**/
	public Long getCscsModel(){
		return this.cscsModel;
	}
	/**
	* 获取预定车型格式化(toString)
	**/
	public String getCscsModel$(){
		String strValue="";
		if(this.getCscsModel()!=null){
				strValue+=$.str(CsCarModel.getKeyValue(this.getCscsModel()));
		}			
	 	 return strValue;
	}
	/**
	* 预定车型  [CsCarModel]     
	**/
	public void setCscsModel(Long cscsModel){
		this.cscsModel = cscsModel;
		this.setSeted(F.cscsModel);
	}
	/**
	* 获取关联对象[车型]
	**/	 			
 	public CsCarModel get$cscsModel(){
 		com.ccclubs.model.CsCarModel csCarModel = (com.ccclubs.model.CsCarModel) $.GetRequest("CsCarModel$"+this.getCscsModel());
 		if(csCarModel!=null)
			return csCarModel;
		if(this.getCscsModel()!=null){
 			csCarModel = CsCarModel.get(this.getCscsModel());
 		}
 		$.SetRequest("CsCarModel$"+this.getCscsModel(), csCarModel);
	 	return csCarModel;
	}
	/*******************************预定网点**********************************/	
	/**
	* 预定网点  [CsOutlets]     
	**/
	public Long getCscsOutlets(){
		return this.cscsOutlets;
	}
	/**
	* 获取预定网点格式化(toString)
	**/
	public String getCscsOutlets$(){
		String strValue="";
		if(this.getCscsOutlets()!=null){
				strValue+=$.str(CsOutlets.getKeyValue(this.getCscsOutlets()));
		}			
	 	 return strValue;
	}
	/**
	* 预定网点  [CsOutlets]     
	**/
	public void setCscsOutlets(Long cscsOutlets){
		this.cscsOutlets = cscsOutlets;
		this.setSeted(F.cscsOutlets);
	}
	/**
	* 获取关联对象[网点]
	**/	 			
 	public CsOutlets get$cscsOutlets(){
 		com.ccclubs.model.CsOutlets csOutlets = (com.ccclubs.model.CsOutlets) $.GetRequest("CsOutlets$"+this.getCscsOutlets());
 		if(csOutlets!=null)
			return csOutlets;
		if(this.getCscsOutlets()!=null){
 			csOutlets = CsOutlets.get(this.getCscsOutlets());
 		}
 		$.SetRequest("CsOutlets$"+this.getCscsOutlets(), csOutlets);
	 	return csOutlets;
	}
	/*******************************目的地**********************************/	
	/**
	* 目的地       
	**/
	public String getCscsTarget(){
		return this.cscsTarget;
	}
	/**
	* 获取目的地格式化(toString)
	**/
	public String getCscsTarget$(){
		String strValue="";
		 strValue=$.str(this.getCscsTarget());
	 	 return strValue;
	}
	/**
	* 目的地       
	**/
	public void setCscsTarget(String cscsTarget){
		this.cscsTarget = cscsTarget;
		this.setSeted(F.cscsTarget);
	}
	/*******************************预定套餐**********************************/	
	/**
	* 预定套餐  [CsPack]     
	**/
	public Long getCscsLocked(){
		return this.cscsLocked;
	}
	/**
	* 获取预定套餐格式化(toString)
	**/
	public String getCscsLocked$(){
		String strValue="";
		if(this.getCscsLocked()!=null){
				strValue+=$.str(CsPack.getKeyValue(this.getCscsLocked()));
		}			
	 	 return strValue;
	}
	/**
	* 预定套餐  [CsPack]     
	**/
	public void setCscsLocked(Long cscsLocked){
		this.cscsLocked = cscsLocked;
		this.setSeted(F.cscsLocked);
	}
	/**
	* 获取关联对象[系统套餐]
	**/	 			
 	public CsPack get$cscsLocked(){
 		com.ccclubs.model.CsPack csPack = (com.ccclubs.model.CsPack) $.GetRequest("CsPack$"+this.getCscsLocked());
 		if(csPack!=null)
			return csPack;
		if(this.getCscsLocked()!=null){
 			csPack = CsPack.get(this.getCscsLocked());
 		}
 		$.SetRequest("CsPack$"+this.getCscsLocked(), csPack);
	 	return csPack;
	}
	/*******************************预定时间**********************************/	
	/**
	* 预定时间       
	**/
	public Date getCscsLockTime(){
		return this.cscsLockTime;
	}
	/**
	* 获取预定时间格式化(toString)
	**/
	public String getCscsLockTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscsLockTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 预定时间       
	**/
	public void setCscsLockTime(Date cscsLockTime){
		this.cscsLockTime = cscsLockTime;
		this.setSeted(F.cscsLockTime);
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getCscsRemark(){
		return this.cscsRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCscsRemark$(){
		String strValue="";
		 strValue=$.str(this.getCscsRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setCscsRemark(String cscsRemark){
		this.cscsRemark = cscsRemark;
		this.setSeted(F.cscsRemark);
	}
	/*******************************年份**********************************/	
	/**
	* 年份    2014:2014年 2015:2015年    
	**/
	public Short getCscsYear(){
		return this.cscsYear;
	}
	/**
	* 获取年份格式化(toString)
	**/
	public String getCscsYear$(){
		String strValue="";
		 if($.equals($.str(this.getCscsYear()),"2014"))
			strValue=$.str("2014年");		 
		 if($.equals($.str(this.getCscsYear()),"2015"))
			strValue=$.str("2015年");		 
	 	 return strValue;
	}
	/**
	* 年份    2014:2014年 2015:2015年    
	**/
	public void setCscsYear(Short cscsYear){
		this.cscsYear = cscsYear;
		this.setSeted(F.cscsYear);
	}
	/*******************************渠道来源**********************************/	
	/**
	* 渠道来源  [CsChannel]     
	**/
	public Long getCscsVisitChannel(){
		return this.cscsVisitChannel;
	}
	/**
	* 获取渠道来源格式化(toString)
	**/
	public String getCscsVisitChannel$(){
		String strValue="";
		if(this.getCscsVisitChannel()!=null){
				strValue+=$.str(CsChannel.getKeyValue(this.getCscsVisitChannel()));
		}			
	 	 return strValue;
	}
	/**
	* 渠道来源  [CsChannel]     
	**/
	public void setCscsVisitChannel(Long cscsVisitChannel){
		this.cscsVisitChannel = cscsVisitChannel;
		this.setSeted(F.cscsVisitChannel);
	}
	/**
	* 获取关联对象[访问渠道]
	**/	 			
 	public CsChannel get$cscsVisitChannel(){
 		com.ccclubs.model.CsChannel csChannel = (com.ccclubs.model.CsChannel) $.GetRequest("CsChannel$"+this.getCscsVisitChannel());
 		if(csChannel!=null)
			return csChannel;
		if(this.getCscsVisitChannel()!=null){
 			csChannel = CsChannel.get(this.getCscsVisitChannel());
 		}
 		$.SetRequest("CsChannel$"+this.getCscsVisitChannel(), csChannel);
	 	return csChannel;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCscsUpdateTime(){
		return this.cscsUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCscsUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscsUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCscsUpdateTime(Date cscsUpdateTime){
		this.cscsUpdateTime = cscsUpdateTime;
		this.setSeted(F.cscsUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCscsAddTime(){
		return this.cscsAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCscsAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCscsAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCscsAddTime(Date cscsAddTime){
		this.cscsAddTime = cscsAddTime;
		this.setSeted(F.cscsAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:已报名 1:已预定 2:已购买    
	**/
	public Short getCscsStatus(){
		return this.cscsStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCscsStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCscsStatus()),"0"))
			strValue=$.str("已报名");		 
		 if($.equals($.str(this.getCscsStatus()),"1"))
			strValue=$.str("已预定");		 
		 if($.equals($.str(this.getCscsStatus()),"2"))
			strValue=$.str("已购买");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:已报名 1:已预定 2:已购买    
	**/
	public void setCscsStatus(Short cscsStatus){
		this.cscsStatus = cscsStatus;
		this.setSeted(F.cscsStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsChjSign.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsChjSign.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsChjSign.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsChjSign.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsChjSign.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsChjSign.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsChjSign.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsChjSign.this);
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
		public M cscsId(Object cscsId){this.put("cscsId", cscsId);return this;};
	 	/** and cscs_id is null */
 		public M cscsIdNull(){if(this.get("cscsIdNot")==null)this.put("cscsIdNot", "");this.put("cscsId", null);return this;};
 		/** not .... */
 		public M cscsIdNot(){this.put("cscsIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M cscsHost(Object cscsHost){this.put("cscsHost", cscsHost);return this;};
	 	/** and cscs_host is null */
 		public M cscsHostNull(){if(this.get("cscsHostNot")==null)this.put("cscsHostNot", "");this.put("cscsHost", null);return this;};
 		/** not .... */
 		public M cscsHostNot(){this.put("cscsHostNot", "not");return this;};
		/** 姓名 [非空]       **/
		public M cscsName(Object cscsName){this.put("cscsName", cscsName);return this;};
	 	/** and cscs_name is null */
 		public M cscsNameNull(){if(this.get("cscsNameNot")==null)this.put("cscsNameNot", "");this.put("cscsName", null);return this;};
 		/** not .... */
 		public M cscsNameNot(){this.put("cscsNameNot", "not");return this;};
		/** 手机号码 [非空]       **/
		public M cscsMobile(Object cscsMobile){this.put("cscsMobile", cscsMobile);return this;};
	 	/** and cscs_mobile is null */
 		public M cscsMobileNull(){if(this.get("cscsMobileNot")==null)this.put("cscsMobileNot", "");this.put("cscsMobile", null);return this;};
 		/** not .... */
 		public M cscsMobileNot(){this.put("cscsMobileNot", "not");return this;};
		/** 关联会员 [非空] [CsMember]      **/
		public M cscsMember(Object cscsMember){this.put("cscsMember", cscsMember);return this;};
	 	/** and cscs_member is null */
 		public M cscsMemberNull(){if(this.get("cscsMemberNot")==null)this.put("cscsMemberNot", "");this.put("cscsMember", null);return this;};
 		/** not .... */
 		public M cscsMemberNot(){this.put("cscsMemberNot", "not");return this;};
		public M cscsMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("cscsMember$on", value);
			return this;
		};	
		/** 取车时间        **/
		public M cscsTakeTime(Object cscsTakeTime){this.put("cscsTakeTime", cscsTakeTime);return this;};
	 	/** and cscs_take_time is null */
 		public M cscsTakeTimeNull(){if(this.get("cscsTakeTimeNot")==null)this.put("cscsTakeTimeNot", "");this.put("cscsTakeTime", null);return this;};
 		/** not .... */
 		public M cscsTakeTimeNot(){this.put("cscsTakeTimeNot", "not");return this;};
 		/** and cscs_take_time >= ? */
 		public M cscsTakeTimeStart(Object start){this.put("cscsTakeTimeStart", start);return this;};			
 		/** and cscs_take_time <= ? */
 		public M cscsTakeTimeEnd(Object end){this.put("cscsTakeTimeEnd", end);return this;};
		/** 还车时间        **/
		public M cscsRetTime(Object cscsRetTime){this.put("cscsRetTime", cscsRetTime);return this;};
	 	/** and cscs_ret_time is null */
 		public M cscsRetTimeNull(){if(this.get("cscsRetTimeNot")==null)this.put("cscsRetTimeNot", "");this.put("cscsRetTime", null);return this;};
 		/** not .... */
 		public M cscsRetTimeNot(){this.put("cscsRetTimeNot", "not");return this;};
 		/** and cscs_ret_time >= ? */
 		public M cscsRetTimeStart(Object start){this.put("cscsRetTimeStart", start);return this;};			
 		/** and cscs_ret_time <= ? */
 		public M cscsRetTimeEnd(Object end){this.put("cscsRetTimeEnd", end);return this;};
		/** 预定车型  [CsCarModel]      **/
		public M cscsModel(Object cscsModel){this.put("cscsModel", cscsModel);return this;};
	 	/** and cscs_model is null */
 		public M cscsModelNull(){if(this.get("cscsModelNot")==null)this.put("cscsModelNot", "");this.put("cscsModel", null);return this;};
 		/** not .... */
 		public M cscsModelNot(){this.put("cscsModelNot", "not");return this;};
		public M cscsModel$on(CsCarModel.M value){
			this.put("CsCarModel", value);
			this.put("cscsModel$on", value);
			return this;
		};	
		/** 预定网点  [CsOutlets]      **/
		public M cscsOutlets(Object cscsOutlets){this.put("cscsOutlets", cscsOutlets);return this;};
	 	/** and cscs_outlets is null */
 		public M cscsOutletsNull(){if(this.get("cscsOutletsNot")==null)this.put("cscsOutletsNot", "");this.put("cscsOutlets", null);return this;};
 		/** not .... */
 		public M cscsOutletsNot(){this.put("cscsOutletsNot", "not");return this;};
		public M cscsOutlets$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("cscsOutlets$on", value);
			return this;
		};	
		/** 目的地        **/
		public M cscsTarget(Object cscsTarget){this.put("cscsTarget", cscsTarget);return this;};
	 	/** and cscs_target is null */
 		public M cscsTargetNull(){if(this.get("cscsTargetNot")==null)this.put("cscsTargetNot", "");this.put("cscsTarget", null);return this;};
 		/** not .... */
 		public M cscsTargetNot(){this.put("cscsTargetNot", "not");return this;};
		/** 预定套餐  [CsPack]      **/
		public M cscsLocked(Object cscsLocked){this.put("cscsLocked", cscsLocked);return this;};
	 	/** and cscs_locked is null */
 		public M cscsLockedNull(){if(this.get("cscsLockedNot")==null)this.put("cscsLockedNot", "");this.put("cscsLocked", null);return this;};
 		/** not .... */
 		public M cscsLockedNot(){this.put("cscsLockedNot", "not");return this;};
		public M cscsLocked$on(CsPack.M value){
			this.put("CsPack", value);
			this.put("cscsLocked$on", value);
			return this;
		};	
		/** 预定时间        **/
		public M cscsLockTime(Object cscsLockTime){this.put("cscsLockTime", cscsLockTime);return this;};
	 	/** and cscs_lock_time is null */
 		public M cscsLockTimeNull(){if(this.get("cscsLockTimeNot")==null)this.put("cscsLockTimeNot", "");this.put("cscsLockTime", null);return this;};
 		/** not .... */
 		public M cscsLockTimeNot(){this.put("cscsLockTimeNot", "not");return this;};
 		/** and cscs_lock_time >= ? */
 		public M cscsLockTimeStart(Object start){this.put("cscsLockTimeStart", start);return this;};			
 		/** and cscs_lock_time <= ? */
 		public M cscsLockTimeEnd(Object end){this.put("cscsLockTimeEnd", end);return this;};
		/** 备注        **/
		public M cscsRemark(Object cscsRemark){this.put("cscsRemark", cscsRemark);return this;};
	 	/** and cscs_remark is null */
 		public M cscsRemarkNull(){if(this.get("cscsRemarkNot")==null)this.put("cscsRemarkNot", "");this.put("cscsRemark", null);return this;};
 		/** not .... */
 		public M cscsRemarkNot(){this.put("cscsRemarkNot", "not");return this;};
		/** 年份    2014:2014年 2015:2015年     **/
		public M cscsYear(Object cscsYear){this.put("cscsYear", cscsYear);return this;};
	 	/** and cscs_year is null */
 		public M cscsYearNull(){if(this.get("cscsYearNot")==null)this.put("cscsYearNot", "");this.put("cscsYear", null);return this;};
 		/** not .... */
 		public M cscsYearNot(){this.put("cscsYearNot", "not");return this;};
		/** 渠道来源  [CsChannel]      **/
		public M cscsVisitChannel(Object cscsVisitChannel){this.put("cscsVisitChannel", cscsVisitChannel);return this;};
	 	/** and cscs_channel is null */
 		public M cscsVisitChannelNull(){if(this.get("cscsVisitChannelNot")==null)this.put("cscsVisitChannelNot", "");this.put("cscsVisitChannel", null);return this;};
 		/** not .... */
 		public M cscsVisitChannelNot(){this.put("cscsVisitChannelNot", "not");return this;};
		public M cscsVisitChannel$on(CsChannel.M value){
			this.put("CsChannel", value);
			this.put("cscsVisitChannel$on", value);
			return this;
		};	
		/** 修改时间 [非空]       **/
		public M cscsUpdateTime(Object cscsUpdateTime){this.put("cscsUpdateTime", cscsUpdateTime);return this;};
	 	/** and cscs_update_time is null */
 		public M cscsUpdateTimeNull(){if(this.get("cscsUpdateTimeNot")==null)this.put("cscsUpdateTimeNot", "");this.put("cscsUpdateTime", null);return this;};
 		/** not .... */
 		public M cscsUpdateTimeNot(){this.put("cscsUpdateTimeNot", "not");return this;};
 		/** and cscs_update_time >= ? */
 		public M cscsUpdateTimeStart(Object start){this.put("cscsUpdateTimeStart", start);return this;};			
 		/** and cscs_update_time <= ? */
 		public M cscsUpdateTimeEnd(Object end){this.put("cscsUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cscsAddTime(Object cscsAddTime){this.put("cscsAddTime", cscsAddTime);return this;};
	 	/** and cscs_add_time is null */
 		public M cscsAddTimeNull(){if(this.get("cscsAddTimeNot")==null)this.put("cscsAddTimeNot", "");this.put("cscsAddTime", null);return this;};
 		/** not .... */
 		public M cscsAddTimeNot(){this.put("cscsAddTimeNot", "not");return this;};
 		/** and cscs_add_time >= ? */
 		public M cscsAddTimeStart(Object start){this.put("cscsAddTimeStart", start);return this;};			
 		/** and cscs_add_time <= ? */
 		public M cscsAddTimeEnd(Object end){this.put("cscsAddTimeEnd", end);return this;};
		/** 状态 [非空]   0:已报名 1:已预定 2:已购买     **/
		public M cscsStatus(Object cscsStatus){this.put("cscsStatus", cscsStatus);return this;};
	 	/** and cscs_status is null */
 		public M cscsStatusNull(){if(this.get("cscsStatusNot")==null)this.put("cscsStatusNot", "");this.put("cscsStatus", null);return this;};
 		/** not .... */
 		public M cscsStatusNot(){this.put("cscsStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有春节套餐报名 **/
		public @api List<CsChjSign> list(Integer size){
			return getCsChjSignList(this,size);
		}
		/** 获取春节套餐报名分页 **/
		public @api Page<CsChjSign> page(int page,int size){
			return getCsChjSignPage(page, size , this);
		}
		/** 根据查询条件取春节套餐报名 **/
		public @api CsChjSign get(){
			return getCsChjSign(this);
		}
		/** 获取春节套餐报名数 **/
		public @api Long count(){
			return getCsChjSignCount(this);
		}
		/** 获取春节套餐报名表达式 **/
		public @api <T> T eval(String strEval){
			return getCsChjSignEval(strEval,this);
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
			updateCsChjSign(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscsId="cscsId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cscsHost="cscsHost";
		/** 姓名 [非空]       **/
		public final static @type(String.class) @like String cscsName="cscsName";
		/** 手机号码 [非空]       **/
		public final static @type(String.class) @like String cscsMobile="cscsMobile";
		/** 关联会员 [非空] [CsMember]      **/
		public final static @type(Long.class)  String cscsMember="cscsMember";
		/** 取车时间        **/
		public final static @type(Date.class)  String cscsTakeTime="cscsTakeTime";
	 	/** and cscs_take_time >= ? */
 		public final static @type(Date.class) String cscsTakeTimeStart="cscsTakeTimeStart";
 		/** and cscs_take_time <= ? */
 		public final static @type(Date.class) String cscsTakeTimeEnd="cscsTakeTimeEnd";
		/** 还车时间        **/
		public final static @type(Date.class)  String cscsRetTime="cscsRetTime";
	 	/** and cscs_ret_time >= ? */
 		public final static @type(Date.class) String cscsRetTimeStart="cscsRetTimeStart";
 		/** and cscs_ret_time <= ? */
 		public final static @type(Date.class) String cscsRetTimeEnd="cscsRetTimeEnd";
		/** 预定车型  [CsCarModel]      **/
		public final static @type(Long.class)  String cscsModel="cscsModel";
		/** 预定网点  [CsOutlets]      **/
		public final static @type(Long.class)  String cscsOutlets="cscsOutlets";
		/** 目的地        **/
		public final static @type(String.class) @like String cscsTarget="cscsTarget";
		/** 预定套餐  [CsPack]      **/
		public final static @type(Long.class)  String cscsLocked="cscsLocked";
		/** 预定时间        **/
		public final static @type(Date.class)  String cscsLockTime="cscsLockTime";
	 	/** and cscs_lock_time >= ? */
 		public final static @type(Date.class) String cscsLockTimeStart="cscsLockTimeStart";
 		/** and cscs_lock_time <= ? */
 		public final static @type(Date.class) String cscsLockTimeEnd="cscsLockTimeEnd";
		/** 备注        **/
		public final static @type(String.class) @like String cscsRemark="cscsRemark";
		/** 年份    2014:2014年 2015:2015年     **/
		public final static @type(Short.class)  String cscsYear="cscsYear";
		/** 渠道来源  [CsChannel]      **/
		public final static @type(Long.class)  String cscsVisitChannel="cscsVisitChannel";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cscsUpdateTime="cscsUpdateTime";
	 	/** and cscs_update_time >= ? */
 		public final static @type(Date.class) String cscsUpdateTimeStart="cscsUpdateTimeStart";
 		/** and cscs_update_time <= ? */
 		public final static @type(Date.class) String cscsUpdateTimeEnd="cscsUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cscsAddTime="cscsAddTime";
	 	/** and cscs_add_time >= ? */
 		public final static @type(Date.class) String cscsAddTimeStart="cscsAddTimeStart";
 		/** and cscs_add_time <= ? */
 		public final static @type(Date.class) String cscsAddTimeEnd="cscsAddTimeEnd";
		/** 状态 [非空]   0:已报名 1:已预定 2:已购买     **/
		public final static @type(Short.class)  String cscsStatus="cscsStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscsId="cscs_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cscsHost="cscs_host";
		/** 姓名 [非空]       **/
		public final static String cscsName="cscs_name";
		/** 手机号码 [非空]       **/
		public final static String cscsMobile="cscs_mobile";
		/** 关联会员 [非空] [CsMember]      **/
		public final static String cscsMember="cscs_member";
		/** 取车时间        **/
		public final static String cscsTakeTime="cscs_take_time";
		/** 还车时间        **/
		public final static String cscsRetTime="cscs_ret_time";
		/** 预定车型  [CsCarModel]      **/
		public final static String cscsModel="cscs_model";
		/** 预定网点  [CsOutlets]      **/
		public final static String cscsOutlets="cscs_outlets";
		/** 目的地        **/
		public final static String cscsTarget="cscs_target";
		/** 预定套餐  [CsPack]      **/
		public final static String cscsLocked="cscs_locked";
		/** 预定时间        **/
		public final static String cscsLockTime="cscs_lock_time";
		/** 备注        **/
		public final static String cscsRemark="cscs_remark";
		/** 年份    2014:2014年 2015:2015年     **/
		public final static String cscsYear="cscs_year";
		/** 渠道来源  [CsChannel]      **/
		public final static String cscsVisitChannel="cscs_channel";
		/** 修改时间 [非空]       **/
		public final static String cscsUpdateTime="cscs_update_time";
		/** 添加时间 [非空]       **/
		public final static String cscsAddTime="cscs_add_time";
		/** 状态 [非空]   0:已报名 1:已预定 2:已购买     **/
		public final static String cscsStatus="cscs_status";
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
				$.Set(name,CsChjSign.getCsChjSign(params));
			else
				$.Set(name,CsChjSign.getCsChjSignList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取春节套餐报名数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsChjSign) $.GetRequest("CsChjSign$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsChjSign.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsChjSign.getCsChjSign(params);
			else
				value = CsChjSign.getCsChjSignList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsChjSign.Get($.add(CsChjSign.F.cscsId,param));
		else if(!$.empty(param.toString()))
			value = CsChjSign.get(Long.valueOf(param.toString()));
		$.SetRequest("CsChjSign$"+param.hashCode(), value);
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
	public void mergeSet(CsChjSign old){
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