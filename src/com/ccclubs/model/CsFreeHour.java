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

@namespace("user/freehour")
public @caption("免费小时") @table("cs_free_hour") class CsFreeHour implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csfh_id")   @primary  @note("  ") Long csfhId;// 主键 非空     
	private @caption("城市") @column("csfh_host")    @relate("$csfhHost") @RelateClass(SrvHost.class)  @note("  ") Long csfhHost;// 非空     
 	private SrvHost $csfhHost;//关联对象[运营城市]
	private @caption("所属会员") @column("csfh_member")    @relate("$csfhMember") @RelateClass(CsMember.class)  @note("  ") Long csfhMember;// 非空    ${basePath}${proname}/user/member_inquire.do 
 	private CsMember $csfhMember;//关联对象[会员帐号]
	private @caption("充值小时") @column("csfh_count")    @note("  ") Double csfhCount;// 非空     
	private @caption("可用小时") @column("csfh_remain")    @note("  ") Double csfhRemain;// 非空     
	private @caption("有效期") @column("csfh_validity")    @note(" 1:1个月 2:2个月 3:3个月 6:半年 12:1年  ") Short csfhValidity;// 非空 1:1个月 2:2个月 3:3个月 6:半年 12:1年     
	//private @caption("使用记录") @RelateClass(CsUseRecord.class) List<CsUseRecord> records;//反向关联的惠免记录列表
	private @caption("结束时间") @column("csfh_end")    @note("  ") Date csfhEnd;// 非空     
	private @caption("修改人") @column("csfh_editor")    @relate("$csfhEditor") @RelateClass(SrvUser.class)  @note("  ") Long csfhEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $csfhEditor;//关联对象[用户]
	private @caption("修改时间") @column("csfh_update_time")    @note("  ") Date csfhUpdateTime;// 非空     
	private @caption("添加时间") @column("csfh_add_time")    @note("  ") Date csfhAddTime;// 非空     
	private @caption("备注") @column("csfh_remark")    @note("  ") String csfhRemark;//     
	private @caption("状态") @column("csfh_status")    @note(" 1:正常 2:已过期 3:用完  ") Short csfhStatus;// 非空 1:正常 2:已过期 3:用完     
	
	//默认构造函数
	public CsFreeHour(){
	
	}
	
	//主键构造函数
	public CsFreeHour(Long id){
		this.csfhId = id;
	}
	
	/**所有字段构造函数 CsFreeHour(csfhHost,csfhMember,csfhCount,csfhRemain,csfhValidity,csfhEnd,csfhEditor,csfhUpdateTime,csfhAddTime,csfhRemark,csfhStatus)
	 CsFreeHour(
	 	$.getLong("csfhHost")//城市 [非空]
	 	,$.getLong("csfhMember")//所属会员 [非空]
	 	,$.getDouble("csfhCount")//充值小时 [非空]
	 	,$.getDouble("csfhRemain")//可用小时 [非空]
	 	,$.getShort("csfhValidity")//有效期 [非空]
	 	,$.getDate("csfhEnd")//结束时间 [非空]
	 	,$.getLong("csfhEditor")//修改人 [非空]
	 	,$.getDate("csfhUpdateTime")//修改时间 [非空]
	 	,$.getDate("csfhAddTime")//添加时间 [非空]
	 	,$.getString("csfhRemark")//备注
	 	,$.getShort("csfhStatus")//状态 [非空]
	 )
	**/
	public CsFreeHour(Long csfhHost,Long csfhMember,Double csfhCount,Double csfhRemain,Short csfhValidity,Date csfhEnd,Long csfhEditor,Date csfhUpdateTime,Date csfhAddTime,String csfhRemark,Short csfhStatus){
		this.csfhHost=csfhHost;
		this.csfhMember=csfhMember;
		this.csfhCount=csfhCount;
		this.csfhRemain=csfhRemain;
		this.csfhValidity=csfhValidity;
		this.csfhEnd=csfhEnd;
		this.csfhEditor=csfhEditor;
		this.csfhUpdateTime=csfhUpdateTime;
		this.csfhAddTime=csfhAddTime;
		this.csfhRemark=csfhRemark;
		this.csfhStatus=csfhStatus;
	}
	
	//设置非空字段
	public CsFreeHour setNotNull(Long csfhHost,Long csfhMember,Double csfhCount,Double csfhRemain,Short csfhValidity,Date csfhEnd,Long csfhEditor,Date csfhUpdateTime,Date csfhAddTime,Short csfhStatus){
		this.csfhHost=csfhHost;
		this.csfhMember=csfhMember;
		this.csfhCount=csfhCount;
		this.csfhRemain=csfhRemain;
		this.csfhValidity=csfhValidity;
		this.csfhEnd=csfhEnd;
		this.csfhEditor=csfhEditor;
		this.csfhUpdateTime=csfhUpdateTime;
		this.csfhAddTime=csfhAddTime;
		this.csfhStatus=csfhStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsFreeHour csfhId(Long csfhId){
		this.csfhId = csfhId;
		this.setSeted(F.csfhId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsFreeHour csfhHost(Long csfhHost){
		this.csfhHost = csfhHost;
		this.setSeted(F.csfhHost);
		return this;
	}
	/** 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
	public CsFreeHour csfhMember(Long csfhMember){
		this.csfhMember = csfhMember;
		this.setSeted(F.csfhMember);
		return this;
	}
	/** 充值小时 [非空]       **/
	public CsFreeHour csfhCount(Double csfhCount){
		this.csfhCount = csfhCount;
		this.setSeted(F.csfhCount);
		return this;
	}
	/** 可用小时 [非空]       **/
	public CsFreeHour csfhRemain(Double csfhRemain){
		this.csfhRemain = csfhRemain;
		this.setSeted(F.csfhRemain);
		return this;
	}
	/** 有效期 [非空]   1:1个月 2:2个月 3:3个月 6:半年 12:1年     **/
	public CsFreeHour csfhValidity(Short csfhValidity){
		this.csfhValidity = csfhValidity;
		this.setSeted(F.csfhValidity);
		return this;
	}
	/** 结束时间 [非空]       **/
	public CsFreeHour csfhEnd(Date csfhEnd){
		this.csfhEnd = csfhEnd;
		this.setSeted(F.csfhEnd);
		return this;
	}
	/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public CsFreeHour csfhEditor(Long csfhEditor){
		this.csfhEditor = csfhEditor;
		this.setSeted(F.csfhEditor);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsFreeHour csfhUpdateTime(Date csfhUpdateTime){
		this.csfhUpdateTime = csfhUpdateTime;
		this.setSeted(F.csfhUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsFreeHour csfhAddTime(Date csfhAddTime){
		this.csfhAddTime = csfhAddTime;
		this.setSeted(F.csfhAddTime);
		return this;
	}
	/** 备注        **/
	public CsFreeHour csfhRemark(String csfhRemark){
		this.csfhRemark = csfhRemark;
		this.setSeted(F.csfhRemark);
		return this;
	}
	/** 状态 [非空]   1:正常 2:已过期 3:用完     **/
	public CsFreeHour csfhStatus(Short csfhStatus){
		this.csfhStatus = csfhStatus;
		this.setSeted(F.csfhStatus);
		return this;
	}
	
	
	//克隆对象
	public CsFreeHour clone(){
		CsFreeHour clone = new CsFreeHour();
		clone.csfhHost=this.csfhHost;
		clone.csfhMember=this.csfhMember;
		clone.csfhCount=this.csfhCount;
		clone.csfhRemain=this.csfhRemain;
		clone.csfhValidity=this.csfhValidity;
		clone.csfhEnd=this.csfhEnd;
		clone.csfhEditor=this.csfhEditor;
		clone.csfhUpdateTime=this.csfhUpdateTime;
		clone.csfhAddTime=this.csfhAddTime;
		clone.csfhStatus=this.csfhStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取免费小时
	 * @param id
	 * @return
	 */
	public static @api CsFreeHour get(Long id){		
		return getCsFreeHourById(id);
	}
	/**
	 * 获取所有免费小时
	 * @return
	 */
	public static @api List<CsFreeHour> list(Map params,Integer size){
		return getCsFreeHourList(params,size);
	}
	/**
	 * 获取免费小时分页
	 * @return
	 */
	public static @api Page<CsFreeHour> page(int page,int size,Map params){
		return getCsFreeHourPage(page, size , params);
	}
	/**
	 * 根据查询条件取免费小时
	 * @param params
	 * @return
	 */
	public static @api CsFreeHour Get(Map params){
		return getCsFreeHour(params);
	}
	/**
	 * 获取免费小时数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsFreeHourCount(params);
	}
	/**
	 * 获取免费小时数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsFreeHourEval(eval,params);
	}
	
	/**
	 * 根据ID取免费小时
	 * @param id
	 * @return
	 */
	public static @api CsFreeHour getCsFreeHourById(Long id){		
		CsFreeHour csFreeHour = (CsFreeHour) $.GetRequest("CsFreeHour$"+id);
		if(csFreeHour!=null)
			return csFreeHour;
		com.ccclubs.service.admin.ICsFreeHourService csFreeHourService = $.GetSpringBean("csFreeHourService");		
		return csFreeHourService.getCsFreeHourById(id);
	}
	
	
	/**
	 * 根据ID取免费小时的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsFreeHour.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsFreeHour csFreeHour = get(id);
		if(csFreeHour!=null){
			String strValue = csFreeHour.getCsfhId$();
			if(!"CsfhId".equals("CsfhId"))
				strValue+="("+csFreeHour.getCsfhId$()+")";
			MemCache.setValue(CsFreeHour.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsfhId$();
		if(!"CsfhId".equals("CsfhId"))
			keyValue+="("+this.getCsfhId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有免费小时
	 * @return
	 */
	public static @api List<CsFreeHour> getCsFreeHourList(Map params,Integer size){
		com.ccclubs.service.admin.ICsFreeHourService csFreeHourService = $.GetSpringBean("csFreeHourService");
		return csFreeHourService.getCsFreeHourList(params, size);
	}
	
	/**
	 * 获取免费小时分页
	 * @return
	 */
	public static @api Page<CsFreeHour> getCsFreeHourPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsFreeHourService csFreeHourService = $.GetSpringBean("csFreeHourService");
		return csFreeHourService.getCsFreeHourPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取免费小时
	 * @param params
	 * @return
	 */
	public static @api CsFreeHour getCsFreeHour(Map params){
		com.ccclubs.service.admin.ICsFreeHourService csFreeHourService = $.GetSpringBean("csFreeHourService");
		return csFreeHourService.getCsFreeHour(params);
	}
	
	/**
	 * 获取免费小时数
	 * @return
	 */
	public static @api Long getCsFreeHourCount(Map params){
		com.ccclubs.service.admin.ICsFreeHourService csFreeHourService = $.GetSpringBean("csFreeHourService");
		return csFreeHourService.getCsFreeHourCount(params);
	}
		
		
	/**
	 * 获取免费小时自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsFreeHourEval(String eval,Map params){
		com.ccclubs.service.admin.ICsFreeHourService csFreeHourService = $.GetSpringBean("csFreeHourService");
		return csFreeHourService.getCsFreeHourEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsFreeHour(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsFreeHourService csFreeHourService = $.GetSpringBean("csFreeHourService");
		csFreeHourService.updateCsFreeHourByConfirm(set, where);
	}
	
	
	/**
	 * 保存免费小时对象
	 * @param params
	 * @return
	 */
	public CsFreeHour save(){
		com.ccclubs.service.admin.ICsFreeHourService csFreeHourService = $.GetSpringBean("csFreeHourService");
		if(this.getCsfhId()!=null)
			csFreeHourService.updateCsFreeHour(this);
		else
			return csFreeHourService.saveCsFreeHour(this);
		return this;
	}
	
	
	/**
	 * 更新免费小时对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsFreeHourService csFreeHourService = $.GetSpringBean("csFreeHourService");
		csFreeHourService.updateCsFreeHour$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsFreeHourService csFreeHourService = $.GetSpringBean("csFreeHourService");
		if($.equals($.config("logic_delete"),"true"))
			csFreeHourService.removeCsFreeHourById(this.getCsfhId());
		else
			csFreeHourService.deleteCsFreeHourById(this.getCsfhId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsFreeHourService csFreeHourService = $.GetSpringBean("csFreeHourService");
		return csFreeHourService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsfhId(){
		return this.csfhId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsfhId$(){
		String strValue="";
		 strValue=$.str(this.getCsfhId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsfhId(Long csfhId){
		this.csfhId = csfhId;
		this.setSeted(F.csfhId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsfhHost(){
		return this.csfhHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsfhHost$(){
		String strValue="";
		if(this.getCsfhHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsfhHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsfhHost(Long csfhHost){
		this.csfhHost = csfhHost;
		this.setSeted(F.csfhHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csfhHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsfhHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsfhHost()!=null){
 			srvHost = SrvHost.get(this.getCsfhHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsfhHost(), srvHost);
	 	return srvHost;
	}
	/*******************************所属会员**********************************/	
	/**
	* 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public Long getCsfhMember(){
		return this.csfhMember;
	}
	/**
	* 获取所属会员格式化(toString)
	**/
	public String getCsfhMember$(){
		String strValue="";
		if(this.getCsfhMember()!=null){
				strValue+=$.str(CsMember.getKeyValue(this.getCsfhMember()));
		}			
	 	 return strValue;
	}
	/**
	* 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do
	**/
	public void setCsfhMember(Long csfhMember){
		this.csfhMember = csfhMember;
		this.setSeted(F.csfhMember);
	}
	/**
	* 获取关联对象[会员帐号]
	**/	 			
 	public CsMember get$csfhMember(){
 		com.ccclubs.model.CsMember csMember = (com.ccclubs.model.CsMember) $.GetRequest("CsMember$"+this.getCsfhMember());
 		if(csMember!=null)
			return csMember;
		if(this.getCsfhMember()!=null){
 			csMember = CsMember.get(this.getCsfhMember());
 		}
 		$.SetRequest("CsMember$"+this.getCsfhMember(), csMember);
	 	return csMember;
	}
	/*******************************充值小时**********************************/	
	/**
	* 充值小时 [非空]      
	**/
	public Double getCsfhCount(){
		return this.csfhCount;
	}
	/**
	* 获取充值小时格式化(toString)
	**/
	public String getCsfhCount$(){
		String strValue="";
		 strValue=$.str(this.getCsfhCount());	
	 	 return strValue;
	}
	/**
	* 充值小时 [非空]      
	**/
	public void setCsfhCount(Double csfhCount){
		this.csfhCount = csfhCount;
		this.setSeted(F.csfhCount);
	}
	/*******************************可用小时**********************************/	
	/**
	* 可用小时 [非空]      
	**/
	public Double getCsfhRemain(){
		return this.csfhRemain;
	}
	/**
	* 获取可用小时格式化(toString)
	**/
	public String getCsfhRemain$(){
		String strValue="";
		 strValue=$.str(this.getCsfhRemain());	
	 	 return strValue;
	}
	/**
	* 可用小时 [非空]      
	**/
	public void setCsfhRemain(Double csfhRemain){
		this.csfhRemain = csfhRemain;
		this.setSeted(F.csfhRemain);
	}
	/*******************************有效期**********************************/	
	/**
	* 有效期 [非空]   1:1个月 2:2个月 3:3个月 6:半年 12:1年    
	**/
	public Short getCsfhValidity(){
		return this.csfhValidity;
	}
	/**
	* 获取有效期格式化(toString)
	**/
	public String getCsfhValidity$(){
		String strValue="";
		 if($.equals($.str(this.getCsfhValidity()),"1"))
			strValue=$.str("1个月");	
		 if($.equals($.str(this.getCsfhValidity()),"2"))
			strValue=$.str("2个月");	
		 if($.equals($.str(this.getCsfhValidity()),"3"))
			strValue=$.str("3个月");	
		 if($.equals($.str(this.getCsfhValidity()),"6"))
			strValue=$.str("半年");	
		 if($.equals($.str(this.getCsfhValidity()),"12"))
			strValue=$.str("1年");	
	 	 return strValue;
	}
	/**
	* 有效期 [非空]   1:1个月 2:2个月 3:3个月 6:半年 12:1年    
	**/
	public void setCsfhValidity(Short csfhValidity){
		this.csfhValidity = csfhValidity;
		this.setSeted(F.csfhValidity);
	}
	/*******************************反向关联的惠免记录列表**********************************/	
	/**
	* 获取与当前对象反向关联的惠免记录列表
	* 参数size：要取的个数，-1表示取全部
	**/
	public List<CsUseRecord> getRecords(){
		com.ccclubs.service.admin.ICsUseRecordService csUseRecordService = $.GetSpringBean("csUseRecordService");
		Map params = new HashMap();
		params.put("csurFreeHour",this.getCsfhId());
		return csUseRecordService.getCsUseRecordList(params,-1);
	}
	/**
	* 获取使用记录格式化(toString)
	**/
	public String getRecords$(){
		return "[...]";
	}
	/*******************************结束时间**********************************/	
	/**
	* 结束时间 [非空]      
	**/
	public Date getCsfhEnd(){
		return this.csfhEnd;
	}
	/**
	* 获取结束时间格式化(toString)
	**/
	public String getCsfhEnd$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsfhEnd(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 结束时间 [非空]      
	**/
	public void setCsfhEnd(Date csfhEnd){
		this.csfhEnd = csfhEnd;
		this.setSeted(F.csfhEnd);
	}
	/*******************************修改人**********************************/	
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getCsfhEditor(){
		return this.csfhEditor;
	}
	/**
	* 获取修改人格式化(toString)
	**/
	public String getCsfhEditor$(){
		String strValue="";
		if(this.getCsfhEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getCsfhEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setCsfhEditor(Long csfhEditor){
		this.csfhEditor = csfhEditor;
		this.setSeted(F.csfhEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$csfhEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getCsfhEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getCsfhEditor()!=null){
 			srvUser = SrvUser.get(this.getCsfhEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getCsfhEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCsfhUpdateTime(){
		return this.csfhUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCsfhUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsfhUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCsfhUpdateTime(Date csfhUpdateTime){
		this.csfhUpdateTime = csfhUpdateTime;
		this.setSeted(F.csfhUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsfhAddTime(){
		return this.csfhAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsfhAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsfhAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsfhAddTime(Date csfhAddTime){
		this.csfhAddTime = csfhAddTime;
		this.setSeted(F.csfhAddTime);
	}
	/*******************************备注**********************************/	
	/**
	* 备注       
	**/
	public String getCsfhRemark(){
		return this.csfhRemark;
	}
	/**
	* 获取备注格式化(toString)
	**/
	public String getCsfhRemark$(){
		String strValue="";
		 strValue=$.str(this.getCsfhRemark());
	 	 return strValue;
	}
	/**
	* 备注       
	**/
	public void setCsfhRemark(String csfhRemark){
		this.csfhRemark = csfhRemark;
		this.setSeted(F.csfhRemark);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 2:已过期 3:用完    
	**/
	public Short getCsfhStatus(){
		return this.csfhStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsfhStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsfhStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsfhStatus()),"2"))
			strValue=$.str("已过期");		 
		 if($.equals($.str(this.getCsfhStatus()),"3"))
			strValue=$.str("用完");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 2:已过期 3:用完    
	**/
	public void setCsfhStatus(Short csfhStatus){
		this.csfhStatus = csfhStatus;
		this.setSeted(F.csfhStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsFreeHour.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsFreeHour.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsFreeHour.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsFreeHour.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsFreeHour.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsFreeHour.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsFreeHour.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsFreeHour.this);
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
		public M csfhId(Object csfhId){this.put("csfhId", csfhId);return this;};
	 	/** and csfh_id is null */
 		public M csfhIdNull(){if(this.get("csfhIdNot")==null)this.put("csfhIdNot", "");this.put("csfhId", null);return this;};
 		/** not .... */
 		public M csfhIdNot(){this.put("csfhIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csfhHost(Object csfhHost){this.put("csfhHost", csfhHost);return this;};
	 	/** and csfh_host is null */
 		public M csfhHostNull(){if(this.get("csfhHostNot")==null)this.put("csfhHostNot", "");this.put("csfhHost", null);return this;};
 		/** not .... */
 		public M csfhHostNot(){this.put("csfhHostNot", "not");return this;};
		/** 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public M csfhMember(Object csfhMember){this.put("csfhMember", csfhMember);return this;};
	 	/** and csfh_member is null */
 		public M csfhMemberNull(){if(this.get("csfhMemberNot")==null)this.put("csfhMemberNot", "");this.put("csfhMember", null);return this;};
 		/** not .... */
 		public M csfhMemberNot(){this.put("csfhMemberNot", "not");return this;};
		public M csfhMember$on(CsMember.M value){
			this.put("CsMember", value);
			this.put("csfhMember$on", value);
			return this;
		};	
		/** 充值小时 [非空]       **/
		public M csfhCount(Object csfhCount){this.put("csfhCount", csfhCount);return this;};
	 	/** and csfh_count is null */
 		public M csfhCountNull(){if(this.get("csfhCountNot")==null)this.put("csfhCountNot", "");this.put("csfhCount", null);return this;};
 		/** not .... */
 		public M csfhCountNot(){this.put("csfhCountNot", "not");return this;};
		/** and csfh_count >= ? */
		public M csfhCountMin(Object min){this.put("csfhCountMin", min);return this;};
		/** and csfh_count <= ? */
		public M csfhCountMax(Object max){this.put("csfhCountMax", max);return this;};
		/** 可用小时 [非空]       **/
		public M csfhRemain(Object csfhRemain){this.put("csfhRemain", csfhRemain);return this;};
	 	/** and csfh_remain is null */
 		public M csfhRemainNull(){if(this.get("csfhRemainNot")==null)this.put("csfhRemainNot", "");this.put("csfhRemain", null);return this;};
 		/** not .... */
 		public M csfhRemainNot(){this.put("csfhRemainNot", "not");return this;};
		/** and csfh_remain >= ? */
		public M csfhRemainMin(Object min){this.put("csfhRemainMin", min);return this;};
		/** and csfh_remain <= ? */
		public M csfhRemainMax(Object max){this.put("csfhRemainMax", max);return this;};
		/** 有效期 [非空]   1:1个月 2:2个月 3:3个月 6:半年 12:1年     **/
		public M csfhValidity(Object csfhValidity){this.put("csfhValidity", csfhValidity);return this;};
	 	/** and csfh_validity is null */
 		public M csfhValidityNull(){if(this.get("csfhValidityNot")==null)this.put("csfhValidityNot", "");this.put("csfhValidity", null);return this;};
 		/** not .... */
 		public M csfhValidityNot(){this.put("csfhValidityNot", "not");return this;};
		/** 结束时间 [非空]       **/
		public M csfhEnd(Object csfhEnd){this.put("csfhEnd", csfhEnd);return this;};
	 	/** and csfh_end is null */
 		public M csfhEndNull(){if(this.get("csfhEndNot")==null)this.put("csfhEndNot", "");this.put("csfhEnd", null);return this;};
 		/** not .... */
 		public M csfhEndNot(){this.put("csfhEndNot", "not");return this;};
 		/** and csfh_end >= ? */
 		public M csfhEndStart(Object start){this.put("csfhEndStart", start);return this;};			
 		/** and csfh_end <= ? */
 		public M csfhEndEnd(Object end){this.put("csfhEndEnd", end);return this;};
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M csfhEditor(Object csfhEditor){this.put("csfhEditor", csfhEditor);return this;};
	 	/** and csfh_editor is null */
 		public M csfhEditorNull(){if(this.get("csfhEditorNot")==null)this.put("csfhEditorNot", "");this.put("csfhEditor", null);return this;};
 		/** not .... */
 		public M csfhEditorNot(){this.put("csfhEditorNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M csfhUpdateTime(Object csfhUpdateTime){this.put("csfhUpdateTime", csfhUpdateTime);return this;};
	 	/** and csfh_update_time is null */
 		public M csfhUpdateTimeNull(){if(this.get("csfhUpdateTimeNot")==null)this.put("csfhUpdateTimeNot", "");this.put("csfhUpdateTime", null);return this;};
 		/** not .... */
 		public M csfhUpdateTimeNot(){this.put("csfhUpdateTimeNot", "not");return this;};
 		/** and csfh_update_time >= ? */
 		public M csfhUpdateTimeStart(Object start){this.put("csfhUpdateTimeStart", start);return this;};			
 		/** and csfh_update_time <= ? */
 		public M csfhUpdateTimeEnd(Object end){this.put("csfhUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M csfhAddTime(Object csfhAddTime){this.put("csfhAddTime", csfhAddTime);return this;};
	 	/** and csfh_add_time is null */
 		public M csfhAddTimeNull(){if(this.get("csfhAddTimeNot")==null)this.put("csfhAddTimeNot", "");this.put("csfhAddTime", null);return this;};
 		/** not .... */
 		public M csfhAddTimeNot(){this.put("csfhAddTimeNot", "not");return this;};
 		/** and csfh_add_time >= ? */
 		public M csfhAddTimeStart(Object start){this.put("csfhAddTimeStart", start);return this;};			
 		/** and csfh_add_time <= ? */
 		public M csfhAddTimeEnd(Object end){this.put("csfhAddTimeEnd", end);return this;};
		/** 备注        **/
		public M csfhRemark(Object csfhRemark){this.put("csfhRemark", csfhRemark);return this;};
	 	/** and csfh_remark is null */
 		public M csfhRemarkNull(){if(this.get("csfhRemarkNot")==null)this.put("csfhRemarkNot", "");this.put("csfhRemark", null);return this;};
 		/** not .... */
 		public M csfhRemarkNot(){this.put("csfhRemarkNot", "not");return this;};
		/** 状态 [非空]   1:正常 2:已过期 3:用完     **/
		public M csfhStatus(Object csfhStatus){this.put("csfhStatus", csfhStatus);return this;};
	 	/** and csfh_status is null */
 		public M csfhStatusNull(){if(this.get("csfhStatusNot")==null)this.put("csfhStatusNot", "");this.put("csfhStatus", null);return this;};
 		/** not .... */
 		public M csfhStatusNot(){this.put("csfhStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有免费小时 **/
		public @api List<CsFreeHour> list(Integer size){
			return getCsFreeHourList(this,size);
		}
		/** 获取免费小时分页 **/
		public @api Page<CsFreeHour> page(int page,int size){
			return getCsFreeHourPage(page, size , this);
		}
		/** 根据查询条件取免费小时 **/
		public @api CsFreeHour get(){
			return getCsFreeHour(this);
		}
		/** 获取免费小时数 **/
		public @api Long count(){
			return getCsFreeHourCount(this);
		}
		/** 获取免费小时表达式 **/
		public @api <T> T eval(String strEval){
			return getCsFreeHourEval(strEval,this);
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
			updateCsFreeHour(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csfhId="csfhId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csfhHost="csfhHost";
		/** 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static @type(Long.class)  String csfhMember="csfhMember";
		/** 充值小时 [非空]       **/
		public final static @type(Double.class)  String csfhCount="csfhCount";
		/** and csfh_count >= ? */
		public final static @type(Double.class) String csfhCountMin="csfhCountMin";
		/** and csfh_count <= ? */
		public final static @type(Double.class) String csfhCountMax="csfhCountMax";
		/** 可用小时 [非空]       **/
		public final static @type(Double.class)  String csfhRemain="csfhRemain";
		/** and csfh_remain >= ? */
		public final static @type(Double.class) String csfhRemainMin="csfhRemainMin";
		/** and csfh_remain <= ? */
		public final static @type(Double.class) String csfhRemainMax="csfhRemainMax";
		/** 有效期 [非空]   1:1个月 2:2个月 3:3个月 6:半年 12:1年     **/
		public final static @type(Short.class)  String csfhValidity="csfhValidity";
		/** 结束时间 [非空]       **/
		public final static @type(Date.class)  String csfhEnd="csfhEnd";
	 	/** and csfh_end >= ? */
 		public final static @type(Date.class) String csfhEndStart="csfhEndStart";
 		/** and csfh_end <= ? */
 		public final static @type(Date.class) String csfhEndEnd="csfhEndEnd";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String csfhEditor="csfhEditor";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String csfhUpdateTime="csfhUpdateTime";
	 	/** and csfh_update_time >= ? */
 		public final static @type(Date.class) String csfhUpdateTimeStart="csfhUpdateTimeStart";
 		/** and csfh_update_time <= ? */
 		public final static @type(Date.class) String csfhUpdateTimeEnd="csfhUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csfhAddTime="csfhAddTime";
	 	/** and csfh_add_time >= ? */
 		public final static @type(Date.class) String csfhAddTimeStart="csfhAddTimeStart";
 		/** and csfh_add_time <= ? */
 		public final static @type(Date.class) String csfhAddTimeEnd="csfhAddTimeEnd";
		/** 备注        **/
		public final static @type(String.class) @like String csfhRemark="csfhRemark";
		/** 状态 [非空]   1:正常 2:已过期 3:用完     **/
		public final static @type(Short.class)  String csfhStatus="csfhStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csfhId="csfh_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csfhHost="csfh_host";
		/** 所属会员 [非空] [CsMember]     ${basePath}${proname}/user/member_inquire.do **/
		public final static String csfhMember="csfh_member";
		/** 充值小时 [非空]       **/
		public final static String csfhCount="csfh_count";
		/** 可用小时 [非空]       **/
		public final static String csfhRemain="csfh_remain";
		/** 有效期 [非空]   1:1个月 2:2个月 3:3个月 6:半年 12:1年     **/
		public final static String csfhValidity="csfh_validity";
		/** 结束时间 [非空]       **/
		public final static String csfhEnd="csfh_end";
		/** 修改人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String csfhEditor="csfh_editor";
		/** 修改时间 [非空]       **/
		public final static String csfhUpdateTime="csfh_update_time";
		/** 添加时间 [非空]       **/
		public final static String csfhAddTime="csfh_add_time";
		/** 备注        **/
		public final static String csfhRemark="csfh_remark";
		/** 状态 [非空]   1:正常 2:已过期 3:用完     **/
		public final static String csfhStatus="csfh_status";
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
				$.Set(name,CsFreeHour.getCsFreeHour(params));
			else
				$.Set(name,CsFreeHour.getCsFreeHourList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取免费小时数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsFreeHour) $.GetRequest("CsFreeHour$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsFreeHour.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsFreeHour.getCsFreeHour(params);
			else
				value = CsFreeHour.getCsFreeHourList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsFreeHour.Get($.add(CsFreeHour.F.csfhId,param));
		else if(!$.empty(param.toString()))
			value = CsFreeHour.get(Long.valueOf(param.toString()));
		$.SetRequest("CsFreeHour$"+param.hashCode(), value);
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
	public void mergeSet(CsFreeHour old){
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