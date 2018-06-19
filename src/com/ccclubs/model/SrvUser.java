package com.ccclubs.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.ccclubs.helper.GetTagHelper;
import com.lazy3q.web.helper.$;
import com.lazy3q.web.util.Page;
import com.lazy3q.lang.*;
import com.lazy3q.util.Function;
import com.ccclubs.dao.impl.MemCache;

@namespace("permissions/user")
public @caption("用户") @table("srv_user") class SrvUser implements java.io.Serializable
{
	private static final long serialVersionUID =          1l;
	
	private @caption("编号") @column("su_id")  @primary  Long suId;// 主键 非空    
	private @caption("用户名") @column("su_username")   String suUsername;// 非空    
	private @caption("密码") @column("su_password") @hidden  String suPassword;// 非空    
	private @caption("真实姓名") @column("su_real_name")   String suRealName;// 非空    
	private @caption("手机号码") @column("su_mobile")   String suMobile;//    
	private @caption("邮箱地址") @column("su_email")   String suEmail;//    
	private @caption("组") @column("su_group_id")   Long suGroupId;//    
	private @caption("父节点") @column("su_parent_id")   Long suParentId;// 非空    
	private @caption("所属城市") @column("su_host")   @relate("$suHost") String suHost;//    select * from srv_host where 1=1
 	private SrvHost $suHost;//关联对象[区城市]
	private @caption("家族") @column("su_family")   String suFamily;//    
	private @caption("快捷方式") @column("su_shortcut")   String suShortcut;//    
	private @caption("添加时间") @column("su_add_time")   Date suAddTime;//    
	private @caption("最后登录时间") @column("su_last_time")   Date suLastTime;//    
	private @caption("最后登录IP") @column("su_last_ip")   String suLastIp;//    
	private @caption("状态") @column("su_status")   Short suStatus;// 非空 1:有效 0:无效    
	
	//默认构造函数
	public SrvUser(){
	
	}
	
	/**所有字段构造函数 SrvUser(suUsername,suPassword,suRealName,suMobile,suEmail,suGroupId,suParentId,suHost,suFamily,suShortcut,suAddTime,suLastTime,suLastIp,suStatus)
	 SrvUser(
	 	$.getString("suUsername")//用户名 [非空]
	 	,$.getString("suPassword")//密码 [非空]
	 	,$.getString("suRealName")//真实姓名 [非空]
	 	,$.getString("suMobile")//手机号码
	 	,$.getString("suEmail")//邮箱地址
	 	,$.getLong("suGroupId")//组
	 	,$.getLong("suParentId")//父节点 [非空]
	 	,$.getString("suHost")//所属城市
	 	,$.getString("suFamily")//家族
	 	,$.getString("suShortcut")//快捷方式
	 	,$.getDate("suAddTime")//添加时间
	 	,$.getDate("suLastTime")//最后登录时间
	 	,$.getString("suLastIp")//最后登录IP
	 	,$.getShort("suStatus")//状态 [非空]
	 )
	**/
	public SrvUser(String suUsername,String suPassword,String suRealName,String suMobile,String suEmail,Long suGroupId,Long suParentId,String suHost,String suFamily,String suShortcut,Date suAddTime,Date suLastTime,String suLastIp,Short suStatus){
		this.suUsername=suUsername;
		this.suPassword=suPassword;
		this.suRealName=suRealName;
		this.suMobile=suMobile;
		this.suEmail=suEmail;
		this.suGroupId=suGroupId;
		this.suParentId=suParentId;
		this.suHost=suHost;
		this.suFamily=suFamily;
		this.suShortcut=suShortcut;
		this.suAddTime=suAddTime;
		this.suLastTime=suLastTime;
		this.suLastIp=suLastIp;
		this.suStatus=suStatus;
	}
	
	//设置非空字段
	public SrvUser setNotNull(String suUsername,String suPassword,String suRealName,Long suParentId,Short suStatus){
		this.suUsername=suUsername;
		this.suPassword=suPassword;
		this.suRealName=suRealName;
		this.suParentId=suParentId;
		this.suStatus=suStatus;
		return this;
	}
	
	
	//克隆对象
	public SrvUser clone(){
		SrvUser clone = new SrvUser();
		clone.suUsername=this.suUsername;
		clone.suPassword=this.suPassword;
		clone.suRealName=this.suRealName;
		clone.suParentId=this.suParentId;
		clone.suStatus=this.suStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取用户
	 * @param id
	 * @return
	 */
	public static @api SrvUser get(Long id){		
		return getSrvUserById(id);
	}
	/**
	 * 获取所有用户
	 * @return
	 */
	public static @api List<SrvUser> list(Map params,Integer size){
		return getSrvUserList(params,size);
	}
	
	/**
	 * 根据查询条件取用户
	 * @param params
	 * @return
	 */
	public static @api SrvUser Get(Map params){
		return getSrvUser(params);
	}
	
	/**
	 * 根据ID取用户
	 * @param id
	 * @return
	 */
	public static @api SrvUser getSrvUserById(Long id){		
		SrvUser srvUser = (SrvUser) $.GetRequest("SrvUser$"+id);
		if(srvUser!=null)
			return srvUser;
		com.ccclubs.service.admin.ISrvUserService srvUserService = $.GetSpringBean("srvUserService");		
		return srvUserService.getSrvUserById(id);
	}
	
	
	/**
	 * 根据ID取用户的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		if(id!=null && id.longValue()==0l)
			return "[系统]";
		String value = MemCache.kv(SrvUser.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null)
			return value;
		SrvUser srvUser = get(id);
		if(srvUser!=null){
			String strValue = srvUser.getSuRealName$();
			if(!"SuRealName".equals("SuRealName"))
				strValue+="("+srvUser.getSuRealName$()+")";
				MemCache.kv(SrvUser.class, id , srvUser.getKeyValue());
			return strValue; 
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getSuRealName$();
		if(!"SuRealName".equals("SuRealName"))
			keyValue+="("+this.getSuRealName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有用户
	 * @return
	 */
	public static @api List<SrvUser> getSrvUserList(Map params,Integer size){
		com.ccclubs.service.admin.ISrvUserService srvUserService = $.GetSpringBean("srvUserService");
		return srvUserService.getSrvUserList(params, size);
	}
	
	
	/**
	 * 根据查询条件取用户
	 * @param params
	 * @return
	 */
	public static @api SrvUser getSrvUser(Map params){
		com.ccclubs.service.admin.ISrvUserService srvUserService = $.GetSpringBean("srvUserService");
		List<SrvUser> list = srvUserService.getSrvUserList(params, 1);
		return list.isEmpty()?null:list.get(0);
	}
	
	/**
	 * 保存用户对象
	 * @param params
	 * @return
	 */
	public SrvUser save(){
		com.ccclubs.service.admin.ISrvUserService srvUserService = $.GetSpringBean("srvUserService");
		if(this.getSuId()!=null)
			srvUserService.updateSrvUser(this);
		else
			return srvUserService.saveSrvUser(this);
		return this;
	}
	
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getSuId(){
		return this.suId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getSuId$(){
		String strValue="";
		 strValue=$.str(this.getSuId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setSuId(Long suId){
		this.suId = suId;
		this.setSeted(F.suId);
	}
	/**
	* 编号 [非空]      
	**/
	public SrvUser suId(Long suId){
		this.suId = suId;
		this.setSeted(F.suId);
		return this;
	}
	/*******************************用户名**********************************/	
	/**
	* 用户名 [非空]      
	**/
	public String getSuUsername(){
		return this.suUsername;
	}
	/**
	* 获取用户名格式化(toString)
	**/
	public String getSuUsername$(){
		String strValue="";
		 strValue=$.str(this.getSuUsername());
	 	 return strValue;
	}
	/**
	* 用户名 [非空]      
	**/
	public void setSuUsername(String suUsername){
		this.suUsername = suUsername;
		this.setSeted(F.suUsername);
	}
	/**
	* 用户名 [非空]      
	**/
	public SrvUser suUsername(String suUsername){
		this.suUsername = suUsername;
		this.setSeted(F.suUsername);
		return this;
	}
	/*******************************密码**********************************/	
	/**
	* 密码 [非空]      
	**/
	public String getSuPassword(){
		return this.suPassword;
	}
	/**
	* 获取密码格式化(toString)
	**/
	public String getSuPassword$(){
		String strValue="";
		 strValue=$.str(this.getSuPassword());
	 	 return strValue;
	}
	/**
	* 密码 [非空]      
	**/
	public void setSuPassword(String suPassword){
		this.suPassword = suPassword;
		this.setSeted(F.suPassword);
	}
	/**
	* 密码 [非空]      
	**/
	public SrvUser suPassword(String suPassword){
		this.suPassword = suPassword;
		this.setSeted(F.suPassword);
		return this;
	}
	/*******************************真实姓名**********************************/	
	/**
	* 真实姓名 [非空]      
	**/
	public String getSuRealName(){
		return this.suRealName;
	}
	/**
	* 获取真实姓名格式化(toString)
	**/
	public String getSuRealName$(){
		String strValue="";
		 strValue=$.str(this.getSuRealName());
	 	 return strValue;
	}
	/**
	* 真实姓名 [非空]      
	**/
	public void setSuRealName(String suRealName){
		this.suRealName = suRealName;
		this.setSeted(F.suRealName);
	}
	/**
	* 真实姓名 [非空]      
	**/
	public SrvUser suRealName(String suRealName){
		this.suRealName = suRealName;
		this.setSeted(F.suRealName);
		return this;
	}
	/*******************************手机号码**********************************/	
	/**
	* 手机号码       
	**/
	public String getSuMobile(){
		return this.suMobile;
	}
	/**
	* 获取手机号码格式化(toString)
	**/
	public String getSuMobile$(){
		String strValue="";
		 strValue=$.str(this.getSuMobile());
	 	 return strValue;
	}
	/**
	* 手机号码       
	**/
	public void setSuMobile(String suMobile){
		this.suMobile = suMobile;
		this.setSeted(F.suMobile);
	}
	/**
	* 手机号码       
	**/
	public SrvUser suMobile(String suMobile){
		this.suMobile = suMobile;
		this.setSeted(F.suMobile);
		return this;
	}
	/*******************************邮箱地址**********************************/	
	/**
	* 邮箱地址       
	**/
	public String getSuEmail(){
		return this.suEmail;
	}
	/**
	* 获取邮箱地址格式化(toString)
	**/
	public String getSuEmail$(){
		String strValue="";
		 strValue=$.str(this.getSuEmail());
	 	 return strValue;
	}
	/**
	* 邮箱地址       
	**/
	public void setSuEmail(String suEmail){
		this.suEmail = suEmail;
		this.setSeted(F.suEmail);
	}
	/**
	* 邮箱地址       
	**/
	public SrvUser suEmail(String suEmail){
		this.suEmail = suEmail;
		this.setSeted(F.suEmail);
		return this;
	}
	/*******************************组**********************************/	
	/**
	* 组       
	**/
	public Long getSuGroupId(){
		return this.suGroupId;
	}
	/**
	* 获取组格式化(toString)
	**/
	public String getSuGroupId$(){
		String strValue="";
		 strValue=$.str(this.getSuGroupId());
	 	 return strValue;
	}
	/**
	* 组       
	**/
	public void setSuGroupId(Long suGroupId){
		this.suGroupId = suGroupId;
		this.setSeted(F.suGroupId);
	}
	/**
	* 组       
	**/
	public SrvUser suGroupId(Long suGroupId){
		this.suGroupId = suGroupId;
		this.setSeted(F.suGroupId);
		return this;
	}
	/*******************************父节点**********************************/	
	/**
	* 父节点 [非空]      
	**/
	public Long getSuParentId(){
		return this.suParentId;
	}
	/**
	* 获取父节点格式化(toString)
	**/
	public String getSuParentId$(){
		String strValue="";
		 strValue=$.str(this.getSuParentId());
	 	 return strValue;
	}
	/**
	* 父节点 [非空]      
	**/
	public void setSuParentId(Long suParentId){
		this.suParentId = suParentId;
		this.setSeted(F.suParentId);
	}
	/**
	* 父节点 [非空]      
	**/
	public SrvUser suParentId(Long suParentId){
		this.suParentId = suParentId;
		this.setSeted(F.suParentId);
		return this;
	}
	/*******************************所属城市**********************************/	
	/**
	* 所属城市  [SrvHost]     select * from srv_host where 1=1
	**/
	public String getSuHost(){
		return this.suHost;
	}
	/**
	* 获取所属城市格式化(toString)
	**/
	public String getSuHost$(){
		String strValue="";
			
		if(!$.empty(this.getSuHost())){
			List<com.ccclubs.model.SrvHost> items = com.ccclubs.model.SrvHost.getSrvHostList($.add("definex","sh_id in ("+$.str(this.getSuHost()).replace("#","")+")"),-1);
			for(com.ccclubs.model.SrvHost item1:items)
				strValue+="["+$.str(item1.getShName())+"]";
		}
	 	 return strValue;
	}
	/**
	* 所属城市  [SrvHost]     select * from srv_host where 1=1
	**/
	public void setSuHost(String suHost){
		this.suHost = suHost;
		this.setSeted(F.suHost);
	}
	/**
	* 所属城市  [SrvHost]     select * from srv_host where 1=1
	**/
	public SrvUser suHost(String suHost){
		this.suHost = suHost;
		this.setSeted(F.suHost);
		return this;
	}
	/*******************************家族**********************************/	
	/**
	* 家族       
	**/
	public String getSuFamily(){
		return this.suFamily;
	}
	/**
	* 获取家族格式化(toString)
	**/
	public String getSuFamily$(){
		String strValue="";
		 strValue=$.str(this.getSuFamily());
	 	 return strValue;
	}
	/**
	* 家族       
	**/
	public void setSuFamily(String suFamily){
		this.suFamily = suFamily;
		this.setSeted(F.suFamily);
	}
	/**
	* 家族       
	**/
	public SrvUser suFamily(String suFamily){
		this.suFamily = suFamily;
		this.setSeted(F.suFamily);
		return this;
	}
	/*******************************快捷方式**********************************/	
	/**
	* 快捷方式       
	**/
	public String getSuShortcut(){
		return this.suShortcut;
	}
	/**
	* 获取快捷方式格式化(toString)
	**/
	public String getSuShortcut$(){
		String strValue="";
		strValue=$.str(this.getSuShortcut());
	 	 return strValue;
	}
	/**
	* 快捷方式       
	**/
	public void setSuShortcut(String suShortcut){
		this.suShortcut = suShortcut;
		this.setSeted(F.suShortcut);
	}
	/**
	* 快捷方式       
	**/
	public SrvUser suShortcut(String suShortcut){
		this.suShortcut = suShortcut;
		this.setSeted(F.suShortcut);
		return this;
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间       
	**/
	public Date getSuAddTime(){
		return this.suAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getSuAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getSuAddTime(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 添加时间       
	**/
	public void setSuAddTime(Date suAddTime){
		this.suAddTime = suAddTime;
		this.setSeted(F.suAddTime);
	}
	/**
	* 添加时间       
	**/
	public SrvUser suAddTime(Date suAddTime){
		this.suAddTime = suAddTime;
		this.setSeted(F.suAddTime);
		return this;
	}
	/*******************************最后登录时间**********************************/	
	/**
	* 最后登录时间       
	**/
	public Date getSuLastTime(){
		return this.suLastTime;
	}
	/**
	* 获取最后登录时间格式化(toString)
	**/
	public String getSuLastTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getSuLastTime(),"yyyy-MM-dd"));
	 	 return strValue;
	}
	/**
	* 最后登录时间       
	**/
	public void setSuLastTime(Date suLastTime){
		this.suLastTime = suLastTime;
		this.setSeted(F.suLastTime);
	}
	/**
	* 最后登录时间       
	**/
	public SrvUser suLastTime(Date suLastTime){
		this.suLastTime = suLastTime;
		this.setSeted(F.suLastTime);
		return this;
	}
	/*******************************最后登录IP**********************************/	
	/**
	* 最后登录IP       
	**/
	public String getSuLastIp(){
		return this.suLastIp;
	}
	/**
	* 获取最后登录IP格式化(toString)
	**/
	public String getSuLastIp$(){
		String strValue="";
		 strValue=$.str(this.getSuLastIp());
	 	 return strValue;
	}
	/**
	* 最后登录IP       
	**/
	public void setSuLastIp(String suLastIp){
		this.suLastIp = suLastIp;
		this.setSeted(F.suLastIp);
	}
	/**
	* 最后登录IP       
	**/
	public SrvUser suLastIp(String suLastIp){
		this.suLastIp = suLastIp;
		this.setSeted(F.suLastIp);
		return this;
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:有效 0:无效    
	**/
	public Short getSuStatus(){
		return this.suStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getSuStatus$(){
		String strValue="";
		 if($.equals($.str(this.getSuStatus()),"1"))
			strValue=$.str("有效");		 
		 if($.equals($.str(this.getSuStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:有效 0:无效    
	**/
	public void setSuStatus(Short suStatus){
		this.suStatus = suStatus;
		this.setSeted(F.suStatus);
	}
	/**
	* 状态 [非空]   1:有效 0:无效    
	**/
	public SrvUser suStatus(Short suStatus){
		this.suStatus = suStatus;
		this.setSeted(F.suStatus);
		return this;
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	public static class M extends HashMap{}
	public static class F{
		/**
		* 编号 [非空]      
		**/
		public final static @type(Long.class) String suId="suId";		
		/**
		* 用户名 [非空]      
		**/
		public final static @type(String.class) String suUsername="suUsername";		
		/**
		* 密码 [非空]      
		**/
		public final static @type(String.class) String suPassword="suPassword";		
		/**
		* 真实姓名 [非空]      
		**/
		public final static @type(String.class) String suRealName="suRealName";		
		/**
		* 手机号码       
		**/
		public final static @type(String.class) String suMobile="suMobile";		
		/**
		* 邮箱地址       
		**/
		public final static @type(String.class) String suEmail="suEmail";		
		/**
		* 组       
		**/
		public final static @type(Long.class) String suGroupId="suGroupId";		
		/** and su_group_id >= ? */
		public final static @type(Long.class) String suGroupIdMin="suGroupIdMin";
		/** and su_group_id <= ? */
		public final static @type(Long.class) String suGroupIdMax="suGroupIdMax";
		/**
		* 父节点 [非空]      
		**/
		public final static @type(Long.class) String suParentId="suParentId";		
		/** and su_parent_id >= ? */
		public final static @type(Long.class) String suParentIdMin="suParentIdMin";
		/** and su_parent_id <= ? */
		public final static @type(Long.class) String suParentIdMax="suParentIdMax";
		/**
		* 所属城市  [SrvHost]     select * from srv_host where 1=1
		**/
		public final static @type(String.class) String suHost="suHost";		
		/**
		* 家族       
		**/
		public final static @type(String.class) String suFamily="suFamily";		
		/**
		* 快捷方式       
		**/
		public final static @type(String.class) String suShortcut="suShortcut";		
		/**
		* 添加时间       
		**/
		public final static @type(Date.class) String suAddTime="suAddTime";		
	 	/** and su_add_time >= ? */
 		public final static @type(Date.class) String suAddTimeStart="suAddTimeStart";
 		/** and su_add_time <= ? */
 		public final static @type(Date.class) String suAddTimeEnd="suAddTimeEnd";			
		/**
		* 最后登录时间       
		**/
		public final static @type(Date.class) String suLastTime="suLastTime";		
	 	/** and su_last_time >= ? */
 		public final static @type(Date.class) String suLastTimeStart="suLastTimeStart";
 		/** and su_last_time <= ? */
 		public final static @type(Date.class) String suLastTimeEnd="suLastTimeEnd";			
		/**
		* 最后登录IP       
		**/
		public final static @type(String.class) String suLastIp="suLastIp";		
		/**
		* 状态 [非空]   1:有效 0:无效    
		**/
		public final static @type(Short.class) String suStatus="suStatus";		
	}
	
	public static class C{
		/**
		* 编号 [非空]      
		**/
		public final static String suId="su_id";
		/**
		* 用户名 [非空]      
		**/
		public final static String suUsername="su_username";
		/**
		* 密码 [非空]      
		**/
		public final static String suPassword="su_password";
		/**
		* 真实姓名 [非空]      
		**/
		public final static String suRealName="su_real_name";
		/**
		* 手机号码       
		**/
		public final static String suMobile="su_mobile";
		/**
		* 邮箱地址       
		**/
		public final static String suEmail="su_email";
		/**
		* 组       
		**/
		public final static String suGroupId="su_group_id";
		/**
		* 父节点 [非空]      
		**/
		public final static String suParentId="su_parent_id";
		/**
		* 所属城市  [SrvHost]     select * from srv_host where 1=1
		**/
		public final static String suHost="su_host";
		/**
		* 家族       
		**/
		public final static String suFamily="su_family";
		/**
		* 快捷方式       
		**/
		public final static String suShortcut="su_shortcut";
		/**
		* 添加时间       
		**/
		public final static String suAddTime="su_add_time";
		/**
		* 最后登录时间       
		**/
		public final static String suLastTime="su_last_time";
		/**
		* 最后登录IP       
		**/
		public final static String suLastIp="su_last_ip";
		/**
		* 状态 [非空]   1:有效 0:无效    
		**/
		public final static String suStatus="su_status";
	 	public static String get(String name){
			try {
				return (String) C.class.getField(name).get(null);
			} catch (Exception e) {
				//e.printStackTrace();
			}
			return null;
		}
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
	public void mergeSet(SrvUser old){
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