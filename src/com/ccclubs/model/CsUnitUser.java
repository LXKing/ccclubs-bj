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

@namespace("unit/user")
public @caption("企业车管") @table("cs_unit_user") class CsUnitUser implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csuu_id")   @primary  @note("  ") Long csuuId;// 主键 非空     
	private @caption("城市") @column("csuu_host")    @relate("$csuuHost") @RelateClass(SrvHost.class)  @note("  ") Long csuuHost;// 非空     
 	private SrvHost $csuuHost;//关联对象[运营城市]
	private @caption("登录帐号") @column("csuu_username")    @note("  ") String csuuUsername;// 非空     
	private @caption("登录密码") @column("csuu_password")  @hidden   @note("  默认密码为用户名") String csuuPassword;// 非空  默认密码为用户名   
	private @caption("所属单位") @column("csuu_unit_info")    @relate("$csuuUnitInfo") @RelateClass(CsUnitInfo.class)  @note("  ") Long csuuUnitInfo;// 非空     
 	private CsUnitInfo $csuuUnitInfo;//关联对象[企业信息]
	private @caption("系统管理员") @column("csuu_is_root")    @note("  ") Boolean csuuIsRoot;// 非空     
	private @caption("权限信息") @column("csuu_limit")    @note(" 0:订单管理 1:车辆管理 2:车辆监控 3:权限管理 4:价格管理 5:部门分组 6:用车人员 7:对账单  ") String csuuLimit;// 0:订单管理 1:车辆管理 2:车辆监控 3:权限管理 4:价格管理 5:部门分组 6:用车人员 7:对账单     
	private @caption("真实姓名") @column("csuu_realname")    @note("  ") String csuuRealname;//     
	private @caption("联系电话") @column("csuu_mobile")    @note("  ") String csuuMobile;//     
	private @caption("添加时间") @column("csuu_add_time")    @note("  ") Date csuuAddTime;// 非空     
	private @caption("最后登录IP") @column("csuu_last_ip")    @note("  ") String csuuLastIp;//     
	private @caption("最后登录时间") @column("csuu_last_time")    @note("  ") String csuuLastTime;//     
	private @caption("状态") @column("csuu_status")    @note(" 1:正常 0:无效  ") Short csuuStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsUnitUser(){
	
	}
	
	//主键构造函数
	public CsUnitUser(Long id){
		this.csuuId = id;
	}
	
	/**所有字段构造函数 CsUnitUser(csuuHost,csuuUsername,csuuPassword,csuuUnitInfo,csuuIsRoot,csuuLimit,csuuRealname,csuuMobile,csuuAddTime,csuuLastIp,csuuLastTime,csuuStatus)
	 CsUnitUser(
	 	$.getLong("csuuHost")//城市 [非空]
	 	,$.getString("csuuUsername")//登录帐号 [非空]
	 	,$.getString("csuuPassword")//登录密码 [非空]
	 	,$.getLong("csuuUnitInfo")//所属单位 [非空]
	 	,$.getBoolean("csuuIsRoot")//系统管理员 [非空]
	 	,$.getString("csuuLimit")//权限信息
	 	,$.getString("csuuRealname")//真实姓名
	 	,$.getString("csuuMobile")//联系电话
	 	,$.getDate("csuuAddTime")//添加时间 [非空]
	 	,$.getString("csuuLastIp")//最后登录IP
	 	,$.getString("csuuLastTime")//最后登录时间
	 	,$.getShort("csuuStatus")//状态 [非空]
	 )
	**/
	public CsUnitUser(Long csuuHost,String csuuUsername,String csuuPassword,Long csuuUnitInfo,Boolean csuuIsRoot,String csuuLimit,String csuuRealname,String csuuMobile,Date csuuAddTime,String csuuLastIp,String csuuLastTime,Short csuuStatus){
		this.csuuHost=csuuHost;
		this.csuuUsername=csuuUsername;
		this.csuuPassword=csuuPassword;
		this.csuuUnitInfo=csuuUnitInfo;
		this.csuuIsRoot=csuuIsRoot;
		this.csuuLimit=csuuLimit;
		this.csuuRealname=csuuRealname;
		this.csuuMobile=csuuMobile;
		this.csuuAddTime=csuuAddTime;
		this.csuuLastIp=csuuLastIp;
		this.csuuLastTime=csuuLastTime;
		this.csuuStatus=csuuStatus;
	}
	
	//设置非空字段
	public CsUnitUser setNotNull(Long csuuHost,String csuuUsername,String csuuPassword,Long csuuUnitInfo,Boolean csuuIsRoot,Date csuuAddTime,Short csuuStatus){
		this.csuuHost=csuuHost;
		this.csuuUsername=csuuUsername;
		this.csuuPassword=csuuPassword;
		this.csuuUnitInfo=csuuUnitInfo;
		this.csuuIsRoot=csuuIsRoot;
		this.csuuAddTime=csuuAddTime;
		this.csuuStatus=csuuStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsUnitUser csuuId(Long csuuId){
		this.csuuId = csuuId;
		this.setSeted(F.csuuId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsUnitUser csuuHost(Long csuuHost){
		this.csuuHost = csuuHost;
		this.setSeted(F.csuuHost);
		return this;
	}
	/** 登录帐号 [非空]       **/
	public CsUnitUser csuuUsername(String csuuUsername){
		this.csuuUsername = csuuUsername;
		this.setSeted(F.csuuUsername);
		return this;
	}
	/** 登录密码 [非空]    默认密码为用户名   **/
	public CsUnitUser csuuPassword(String csuuPassword){
		this.csuuPassword = csuuPassword;
		this.setSeted(F.csuuPassword);
		return this;
	}
	/** 所属单位 [非空] [CsUnitInfo]      **/
	public CsUnitUser csuuUnitInfo(Long csuuUnitInfo){
		this.csuuUnitInfo = csuuUnitInfo;
		this.setSeted(F.csuuUnitInfo);
		return this;
	}
	/** 系统管理员 [非空]       **/
	public CsUnitUser csuuIsRoot(Boolean csuuIsRoot){
		this.csuuIsRoot = csuuIsRoot;
		this.setSeted(F.csuuIsRoot);
		return this;
	}
	/** 权限信息    0:订单管理 1:车辆管理 2:车辆监控 3:权限管理 4:价格管理 5:部门分组 6:用车人员 7:对账单     **/
	public CsUnitUser csuuLimit(String csuuLimit){
		this.csuuLimit = csuuLimit;
		this.setSeted(F.csuuLimit);
		return this;
	}
	/** 真实姓名        **/
	public CsUnitUser csuuRealname(String csuuRealname){
		this.csuuRealname = csuuRealname;
		this.setSeted(F.csuuRealname);
		return this;
	}
	/** 联系电话        **/
	public CsUnitUser csuuMobile(String csuuMobile){
		this.csuuMobile = csuuMobile;
		this.setSeted(F.csuuMobile);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsUnitUser csuuAddTime(Date csuuAddTime){
		this.csuuAddTime = csuuAddTime;
		this.setSeted(F.csuuAddTime);
		return this;
	}
	/** 最后登录IP        **/
	public CsUnitUser csuuLastIp(String csuuLastIp){
		this.csuuLastIp = csuuLastIp;
		this.setSeted(F.csuuLastIp);
		return this;
	}
	/** 最后登录时间        **/
	public CsUnitUser csuuLastTime(String csuuLastTime){
		this.csuuLastTime = csuuLastTime;
		this.setSeted(F.csuuLastTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsUnitUser csuuStatus(Short csuuStatus){
		this.csuuStatus = csuuStatus;
		this.setSeted(F.csuuStatus);
		return this;
	}
	
	
	//克隆对象
	public CsUnitUser clone(){
		CsUnitUser clone = new CsUnitUser();
		clone.csuuHost=this.csuuHost;
		clone.csuuUsername=this.csuuUsername;
		clone.csuuPassword=this.csuuPassword;
		clone.csuuUnitInfo=this.csuuUnitInfo;
		clone.csuuIsRoot=this.csuuIsRoot;
		clone.csuuAddTime=this.csuuAddTime;
		clone.csuuStatus=this.csuuStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取企业车管
	 * @param id
	 * @return
	 */
	public static @api CsUnitUser get(Long id){		
		return getCsUnitUserById(id);
	}
	/**
	 * 获取所有企业车管
	 * @return
	 */
	public static @api List<CsUnitUser> list(Map params,Integer size){
		return getCsUnitUserList(params,size);
	}
	/**
	 * 获取企业车管分页
	 * @return
	 */
	public static @api Page<CsUnitUser> page(int page,int size,Map params){
		return getCsUnitUserPage(page, size , params);
	}
	/**
	 * 根据查询条件取企业车管
	 * @param params
	 * @return
	 */
	public static @api CsUnitUser Get(Map params){
		return getCsUnitUser(params);
	}
	/**
	 * 获取企业车管数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsUnitUserCount(params);
	}
	/**
	 * 获取企业车管数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsUnitUserEval(eval,params);
	}
	
	/**
	 * 根据ID取企业车管
	 * @param id
	 * @return
	 */
	public static @api CsUnitUser getCsUnitUserById(Long id){		
		CsUnitUser csUnitUser = (CsUnitUser) $.GetRequest("CsUnitUser$"+id);
		if(csUnitUser!=null)
			return csUnitUser;
		com.ccclubs.service.admin.ICsUnitUserService csUnitUserService = $.GetSpringBean("csUnitUserService");		
		return csUnitUserService.getCsUnitUserById(id);
	}
	
	
	/**
	 * 根据ID取企业车管的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsUnitUser.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsUnitUser csUnitUser = get(id);
		if(csUnitUser!=null){
			String strValue = csUnitUser.getCsuuUsername$();
			if(!"CsuuUsername".equals("CsuuUsername"))
				strValue+="("+csUnitUser.getCsuuUsername$()+")";
			MemCache.setValue(CsUnitUser.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsuuUsername$();
		if(!"CsuuUsername".equals("CsuuUsername"))
			keyValue+="("+this.getCsuuUsername$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有企业车管
	 * @return
	 */
	public static @api List<CsUnitUser> getCsUnitUserList(Map params,Integer size){
		com.ccclubs.service.admin.ICsUnitUserService csUnitUserService = $.GetSpringBean("csUnitUserService");
		return csUnitUserService.getCsUnitUserList(params, size);
	}
	
	/**
	 * 获取企业车管分页
	 * @return
	 */
	public static @api Page<CsUnitUser> getCsUnitUserPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsUnitUserService csUnitUserService = $.GetSpringBean("csUnitUserService");
		return csUnitUserService.getCsUnitUserPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取企业车管
	 * @param params
	 * @return
	 */
	public static @api CsUnitUser getCsUnitUser(Map params){
		com.ccclubs.service.admin.ICsUnitUserService csUnitUserService = $.GetSpringBean("csUnitUserService");
		return csUnitUserService.getCsUnitUser(params);
	}
	
	/**
	 * 获取企业车管数
	 * @return
	 */
	public static @api Long getCsUnitUserCount(Map params){
		com.ccclubs.service.admin.ICsUnitUserService csUnitUserService = $.GetSpringBean("csUnitUserService");
		return csUnitUserService.getCsUnitUserCount(params);
	}
		
		
	/**
	 * 获取企业车管自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsUnitUserEval(String eval,Map params){
		com.ccclubs.service.admin.ICsUnitUserService csUnitUserService = $.GetSpringBean("csUnitUserService");
		return csUnitUserService.getCsUnitUserEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsUnitUser(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsUnitUserService csUnitUserService = $.GetSpringBean("csUnitUserService");
		csUnitUserService.updateCsUnitUserByConfirm(set, where);
	}
	
	
	/**
	 * 保存企业车管对象
	 * @param params
	 * @return
	 */
	public CsUnitUser save(){
		com.ccclubs.service.admin.ICsUnitUserService csUnitUserService = $.GetSpringBean("csUnitUserService");
		if(this.getCsuuId()!=null)
			csUnitUserService.updateCsUnitUser(this);
		else
			return csUnitUserService.saveCsUnitUser(this);
		return this;
	}
	
	
	/**
	 * 更新企业车管对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsUnitUserService csUnitUserService = $.GetSpringBean("csUnitUserService");
		csUnitUserService.updateCsUnitUser$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsUnitUserService csUnitUserService = $.GetSpringBean("csUnitUserService");
		if($.equals($.config("logic_delete"),"true"))
			csUnitUserService.removeCsUnitUserById(this.getCsuuId());
		else
			csUnitUserService.deleteCsUnitUserById(this.getCsuuId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsUnitUserService csUnitUserService = $.GetSpringBean("csUnitUserService");
		return csUnitUserService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsuuId(){
		return this.csuuId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsuuId$(){
		String strValue="";
		 strValue=$.str(this.getCsuuId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsuuId(Long csuuId){
		this.csuuId = csuuId;
		this.setSeted(F.csuuId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCsuuHost(){
		return this.csuuHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCsuuHost$(){
		String strValue="";
		if(this.getCsuuHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsuuHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCsuuHost(Long csuuHost){
		this.csuuHost = csuuHost;
		this.setSeted(F.csuuHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csuuHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsuuHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsuuHost()!=null){
 			srvHost = SrvHost.get(this.getCsuuHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsuuHost(), srvHost);
	 	return srvHost;
	}
	/*******************************登录帐号**********************************/	
	/**
	* 登录帐号 [非空]      
	**/
	public String getCsuuUsername(){
		return this.csuuUsername;
	}
	/**
	* 获取登录帐号格式化(toString)
	**/
	public String getCsuuUsername$(){
		String strValue="";
		 strValue=$.str(this.getCsuuUsername());
	 	 return strValue;
	}
	/**
	* 登录帐号 [非空]      
	**/
	public void setCsuuUsername(String csuuUsername){
		this.csuuUsername = csuuUsername;
		this.setSeted(F.csuuUsername);
	}
	/*******************************登录密码**********************************/	
	/**
	* 登录密码 [非空]    默认密码为用户名  
	**/
	public String getCsuuPassword(){
		return this.csuuPassword;
	}
	/**
	* 获取登录密码格式化(toString)
	**/
	public String getCsuuPassword$(){
		String strValue="";
		 strValue=$.str(this.getCsuuPassword());
	 	 return strValue;
	}
	/**
	* 登录密码 [非空]    默认密码为用户名  
	**/
	public void setCsuuPassword(String csuuPassword){
		this.csuuPassword = csuuPassword;
		this.setSeted(F.csuuPassword);
	}
	/*******************************所属单位**********************************/	
	/**
	* 所属单位 [非空] [CsUnitInfo]     
	**/
	public Long getCsuuUnitInfo(){
		return this.csuuUnitInfo;
	}
	/**
	* 获取所属单位格式化(toString)
	**/
	public String getCsuuUnitInfo$(){
		String strValue="";
		if(this.getCsuuUnitInfo()!=null){
				strValue+=$.str(CsUnitInfo.getKeyValue(this.getCsuuUnitInfo()));
		}			
	 	 return strValue;
	}
	/**
	* 所属单位 [非空] [CsUnitInfo]     
	**/
	public void setCsuuUnitInfo(Long csuuUnitInfo){
		this.csuuUnitInfo = csuuUnitInfo;
		this.setSeted(F.csuuUnitInfo);
	}
	/**
	* 获取关联对象[企业信息]
	**/	 			
 	public CsUnitInfo get$csuuUnitInfo(){
 		com.ccclubs.model.CsUnitInfo csUnitInfo = (com.ccclubs.model.CsUnitInfo) $.GetRequest("CsUnitInfo$"+this.getCsuuUnitInfo());
 		if(csUnitInfo!=null)
			return csUnitInfo;
		if(this.getCsuuUnitInfo()!=null){
 			csUnitInfo = CsUnitInfo.get(this.getCsuuUnitInfo());
 		}
 		$.SetRequest("CsUnitInfo$"+this.getCsuuUnitInfo(), csUnitInfo);
	 	return csUnitInfo;
	}
	/*******************************系统管理员**********************************/	
	/**
	* 系统管理员 [非空]      
	**/
	public Boolean getCsuuIsRoot(){
		return this.csuuIsRoot;
	}
	/**
	* 获取系统管理员格式化(toString)
	**/
	public String getCsuuIsRoot$(){
		String strValue="";
		  strValue=$.str((this.getCsuuIsRoot()!=null && this.getCsuuIsRoot())?"是":"否");			
	 	 return strValue;
	}
	/**
	* 系统管理员 [非空]      
	**/
	public void setCsuuIsRoot(Boolean csuuIsRoot){
		this.csuuIsRoot = csuuIsRoot;
		this.setSeted(F.csuuIsRoot);
	}
	/*******************************权限信息**********************************/	
	/**
	* 权限信息    0:订单管理 1:车辆管理 2:车辆监控 3:权限管理 4:价格管理 5:部门分组 6:用车人员 7:对账单    
	**/
	public String getCsuuLimit(){
		return this.csuuLimit;
	}
	/**
	* 获取权限信息格式化(toString)
	**/
	public String getCsuuLimit$(){
		String strValue="";
		if($.idin(this.getCsuuLimit(),0))
			strValue+=$.str("订单管理,");								
		if($.idin(this.getCsuuLimit(),1))
			strValue+=$.str("车辆管理,");								
		if($.idin(this.getCsuuLimit(),2))
			strValue+=$.str("车辆监控,");								
		if($.idin(this.getCsuuLimit(),3))
			strValue+=$.str("权限管理,");								
		if($.idin(this.getCsuuLimit(),4))
			strValue+=$.str("价格管理,");								
		if($.idin(this.getCsuuLimit(),5))
			strValue+=$.str("部门分组,");								
		if($.idin(this.getCsuuLimit(),6))
			strValue+=$.str("用车人员,");								
		if($.idin(this.getCsuuLimit(),7))
			strValue+=$.str("对账单,");								
	 	 return strValue;
	}
	/**
	* 权限信息    0:订单管理 1:车辆管理 2:车辆监控 3:权限管理 4:价格管理 5:部门分组 6:用车人员 7:对账单    
	**/
	public void setCsuuLimit(String csuuLimit){
		this.csuuLimit = csuuLimit;
		this.setSeted(F.csuuLimit);
	}
	/*******************************真实姓名**********************************/	
	/**
	* 真实姓名       
	**/
	public String getCsuuRealname(){
		return this.csuuRealname;
	}
	/**
	* 获取真实姓名格式化(toString)
	**/
	public String getCsuuRealname$(){
		String strValue="";
		 strValue=$.str(this.getCsuuRealname());
	 	 return strValue;
	}
	/**
	* 真实姓名       
	**/
	public void setCsuuRealname(String csuuRealname){
		this.csuuRealname = csuuRealname;
		this.setSeted(F.csuuRealname);
	}
	/*******************************联系电话**********************************/	
	/**
	* 联系电话       
	**/
	public String getCsuuMobile(){
		return this.csuuMobile;
	}
	/**
	* 获取联系电话格式化(toString)
	**/
	public String getCsuuMobile$(){
		String strValue="";
		 strValue=$.str(this.getCsuuMobile());
	 	 return strValue;
	}
	/**
	* 联系电话       
	**/
	public void setCsuuMobile(String csuuMobile){
		this.csuuMobile = csuuMobile;
		this.setSeted(F.csuuMobile);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCsuuAddTime(){
		return this.csuuAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCsuuAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCsuuAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCsuuAddTime(Date csuuAddTime){
		this.csuuAddTime = csuuAddTime;
		this.setSeted(F.csuuAddTime);
	}
	/*******************************最后登录IP**********************************/	
	/**
	* 最后登录IP       
	**/
	public String getCsuuLastIp(){
		return this.csuuLastIp;
	}
	/**
	* 获取最后登录IP格式化(toString)
	**/
	public String getCsuuLastIp$(){
		String strValue="";
		 strValue=$.str(this.getCsuuLastIp());
	 	 return strValue;
	}
	/**
	* 最后登录IP       
	**/
	public void setCsuuLastIp(String csuuLastIp){
		this.csuuLastIp = csuuLastIp;
		this.setSeted(F.csuuLastIp);
	}
	/*******************************最后登录时间**********************************/	
	/**
	* 最后登录时间       
	**/
	public String getCsuuLastTime(){
		return this.csuuLastTime;
	}
	/**
	* 获取最后登录时间格式化(toString)
	**/
	public String getCsuuLastTime$(){
		String strValue="";
		 strValue=$.str(this.getCsuuLastTime());
	 	 return strValue;
	}
	/**
	* 最后登录时间       
	**/
	public void setCsuuLastTime(String csuuLastTime){
		this.csuuLastTime = csuuLastTime;
		this.setSeted(F.csuuLastTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsuuStatus(){
		return this.csuuStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsuuStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsuuStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsuuStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsuuStatus(Short csuuStatus){
		this.csuuStatus = csuuStatus;
		this.setSeted(F.csuuStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsUnitUser.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUnitUser.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUnitUser.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsUnitUser.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsUnitUser.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsUnitUser.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsUnitUser.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsUnitUser.this);
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
		public M csuuId(Object csuuId){this.put("csuuId", csuuId);return this;};
	 	/** and csuu_id is null */
 		public M csuuIdNull(){if(this.get("csuuIdNot")==null)this.put("csuuIdNot", "");this.put("csuuId", null);return this;};
 		/** not .... */
 		public M csuuIdNot(){this.put("csuuIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M csuuHost(Object csuuHost){this.put("csuuHost", csuuHost);return this;};
	 	/** and csuu_host is null */
 		public M csuuHostNull(){if(this.get("csuuHostNot")==null)this.put("csuuHostNot", "");this.put("csuuHost", null);return this;};
 		/** not .... */
 		public M csuuHostNot(){this.put("csuuHostNot", "not");return this;};
		/** 登录帐号 [非空]       **/
		public M csuuUsername(Object csuuUsername){this.put("csuuUsername", csuuUsername);return this;};
	 	/** and csuu_username is null */
 		public M csuuUsernameNull(){if(this.get("csuuUsernameNot")==null)this.put("csuuUsernameNot", "");this.put("csuuUsername", null);return this;};
 		/** not .... */
 		public M csuuUsernameNot(){this.put("csuuUsernameNot", "not");return this;};
		/** 登录密码 [非空]    默认密码为用户名   **/
		public M csuuPassword(Object csuuPassword){this.put("csuuPassword", csuuPassword);return this;};
	 	/** and csuu_password is null */
 		public M csuuPasswordNull(){if(this.get("csuuPasswordNot")==null)this.put("csuuPasswordNot", "");this.put("csuuPassword", null);return this;};
 		/** not .... */
 		public M csuuPasswordNot(){this.put("csuuPasswordNot", "not");return this;};
		/** 所属单位 [非空] [CsUnitInfo]      **/
		public M csuuUnitInfo(Object csuuUnitInfo){this.put("csuuUnitInfo", csuuUnitInfo);return this;};
	 	/** and csuu_unit_info is null */
 		public M csuuUnitInfoNull(){if(this.get("csuuUnitInfoNot")==null)this.put("csuuUnitInfoNot", "");this.put("csuuUnitInfo", null);return this;};
 		/** not .... */
 		public M csuuUnitInfoNot(){this.put("csuuUnitInfoNot", "not");return this;};
		public M csuuUnitInfo$on(CsUnitInfo.M value){
			this.put("CsUnitInfo", value);
			this.put("csuuUnitInfo$on", value);
			return this;
		};	
		/** 系统管理员 [非空]       **/
		public M csuuIsRoot(Object csuuIsRoot){this.put("csuuIsRoot", csuuIsRoot);return this;};
	 	/** and csuu_is_root is null */
 		public M csuuIsRootNull(){if(this.get("csuuIsRootNot")==null)this.put("csuuIsRootNot", "");this.put("csuuIsRoot", null);return this;};
 		/** not .... */
 		public M csuuIsRootNot(){this.put("csuuIsRootNot", "not");return this;};
		/** 权限信息    0:订单管理 1:车辆管理 2:车辆监控 3:权限管理 4:价格管理 5:部门分组 6:用车人员 7:对账单     **/
		public M csuuLimit(Object csuuLimit){this.put("csuuLimit", csuuLimit);return this;};
	 	/** and csuu_limit is null */
 		public M csuuLimitNull(){if(this.get("csuuLimitNot")==null)this.put("csuuLimitNot", "");this.put("csuuLimit", null);return this;};
 		/** not .... */
 		public M csuuLimitNot(){this.put("csuuLimitNot", "not");return this;};
		/** 真实姓名        **/
		public M csuuRealname(Object csuuRealname){this.put("csuuRealname", csuuRealname);return this;};
	 	/** and csuu_realname is null */
 		public M csuuRealnameNull(){if(this.get("csuuRealnameNot")==null)this.put("csuuRealnameNot", "");this.put("csuuRealname", null);return this;};
 		/** not .... */
 		public M csuuRealnameNot(){this.put("csuuRealnameNot", "not");return this;};
		/** 联系电话        **/
		public M csuuMobile(Object csuuMobile){this.put("csuuMobile", csuuMobile);return this;};
	 	/** and csuu_mobile is null */
 		public M csuuMobileNull(){if(this.get("csuuMobileNot")==null)this.put("csuuMobileNot", "");this.put("csuuMobile", null);return this;};
 		/** not .... */
 		public M csuuMobileNot(){this.put("csuuMobileNot", "not");return this;};
		/** 添加时间 [非空]       **/
		public M csuuAddTime(Object csuuAddTime){this.put("csuuAddTime", csuuAddTime);return this;};
	 	/** and csuu_add_time is null */
 		public M csuuAddTimeNull(){if(this.get("csuuAddTimeNot")==null)this.put("csuuAddTimeNot", "");this.put("csuuAddTime", null);return this;};
 		/** not .... */
 		public M csuuAddTimeNot(){this.put("csuuAddTimeNot", "not");return this;};
 		/** and csuu_add_time >= ? */
 		public M csuuAddTimeStart(Object start){this.put("csuuAddTimeStart", start);return this;};			
 		/** and csuu_add_time <= ? */
 		public M csuuAddTimeEnd(Object end){this.put("csuuAddTimeEnd", end);return this;};
		/** 最后登录IP        **/
		public M csuuLastIp(Object csuuLastIp){this.put("csuuLastIp", csuuLastIp);return this;};
	 	/** and csuu_last_ip is null */
 		public M csuuLastIpNull(){if(this.get("csuuLastIpNot")==null)this.put("csuuLastIpNot", "");this.put("csuuLastIp", null);return this;};
 		/** not .... */
 		public M csuuLastIpNot(){this.put("csuuLastIpNot", "not");return this;};
		/** 最后登录时间        **/
		public M csuuLastTime(Object csuuLastTime){this.put("csuuLastTime", csuuLastTime);return this;};
	 	/** and csuu_last_time is null */
 		public M csuuLastTimeNull(){if(this.get("csuuLastTimeNot")==null)this.put("csuuLastTimeNot", "");this.put("csuuLastTime", null);return this;};
 		/** not .... */
 		public M csuuLastTimeNot(){this.put("csuuLastTimeNot", "not");return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csuuStatus(Object csuuStatus){this.put("csuuStatus", csuuStatus);return this;};
	 	/** and csuu_status is null */
 		public M csuuStatusNull(){if(this.get("csuuStatusNot")==null)this.put("csuuStatusNot", "");this.put("csuuStatus", null);return this;};
 		/** not .... */
 		public M csuuStatusNot(){this.put("csuuStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有企业车管 **/
		public @api List<CsUnitUser> list(Integer size){
			return getCsUnitUserList(this,size);
		}
		/** 获取企业车管分页 **/
		public @api Page<CsUnitUser> page(int page,int size){
			return getCsUnitUserPage(page, size , this);
		}
		/** 根据查询条件取企业车管 **/
		public @api CsUnitUser get(){
			return getCsUnitUser(this);
		}
		/** 获取企业车管数 **/
		public @api Long count(){
			return getCsUnitUserCount(this);
		}
		/** 获取企业车管表达式 **/
		public @api <T> T eval(String strEval){
			return getCsUnitUserEval(strEval,this);
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
			updateCsUnitUser(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csuuId="csuuId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csuuHost="csuuHost";
		/** 登录帐号 [非空]       **/
		public final static @type(String.class) @like String csuuUsername="csuuUsername";
		/** 登录密码 [非空]    默认密码为用户名   **/
		public final static @type(String.class) @like String csuuPassword="csuuPassword";
		/** 所属单位 [非空] [CsUnitInfo]      **/
		public final static @type(Long.class)  String csuuUnitInfo="csuuUnitInfo";
		/** 系统管理员 [非空]       **/
		public final static @type(Boolean.class)  String csuuIsRoot="csuuIsRoot";
		/** 权限信息    0:订单管理 1:车辆管理 2:车辆监控 3:权限管理 4:价格管理 5:部门分组 6:用车人员 7:对账单     **/
		public final static @type(String.class) @like String csuuLimit="csuuLimit";
		/** 真实姓名        **/
		public final static @type(String.class) @like String csuuRealname="csuuRealname";
		/** 联系电话        **/
		public final static @type(String.class) @like String csuuMobile="csuuMobile";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String csuuAddTime="csuuAddTime";
	 	/** and csuu_add_time >= ? */
 		public final static @type(Date.class) String csuuAddTimeStart="csuuAddTimeStart";
 		/** and csuu_add_time <= ? */
 		public final static @type(Date.class) String csuuAddTimeEnd="csuuAddTimeEnd";
		/** 最后登录IP        **/
		public final static @type(String.class) @like String csuuLastIp="csuuLastIp";
		/** 最后登录时间        **/
		public final static @type(String.class) @like String csuuLastTime="csuuLastTime";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csuuStatus="csuuStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csuuId="csuu_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String csuuHost="csuu_host";
		/** 登录帐号 [非空]       **/
		public final static String csuuUsername="csuu_username";
		/** 登录密码 [非空]    默认密码为用户名   **/
		public final static String csuuPassword="csuu_password";
		/** 所属单位 [非空] [CsUnitInfo]      **/
		public final static String csuuUnitInfo="csuu_unit_info";
		/** 系统管理员 [非空]       **/
		public final static String csuuIsRoot="csuu_is_root";
		/** 权限信息    0:订单管理 1:车辆管理 2:车辆监控 3:权限管理 4:价格管理 5:部门分组 6:用车人员 7:对账单     **/
		public final static String csuuLimit="csuu_limit";
		/** 真实姓名        **/
		public final static String csuuRealname="csuu_realname";
		/** 联系电话        **/
		public final static String csuuMobile="csuu_mobile";
		/** 添加时间 [非空]       **/
		public final static String csuuAddTime="csuu_add_time";
		/** 最后登录IP        **/
		public final static String csuuLastIp="csuu_last_ip";
		/** 最后登录时间        **/
		public final static String csuuLastTime="csuu_last_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csuuStatus="csuu_status";
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
				$.Set(name,CsUnitUser.getCsUnitUser(params));
			else
				$.Set(name,CsUnitUser.getCsUnitUserList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取企业车管数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsUnitUser) $.GetRequest("CsUnitUser$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsUnitUser.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsUnitUser.getCsUnitUser(params);
			else
				value = CsUnitUser.getCsUnitUserList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsUnitUser.Get($.add(CsUnitUser.F.csuuId,param));
		else if(!$.empty(param.toString()))
			value = CsUnitUser.get(Long.valueOf(param.toString()));
		$.SetRequest("CsUnitUser$"+param.hashCode(), value);
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
	public void mergeSet(CsUnitUser old){
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