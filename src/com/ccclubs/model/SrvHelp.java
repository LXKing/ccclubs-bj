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

@namespace("systematic/help")
public @caption("系统帮助") @table("srv_help") class SrvHelp implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("sh_id")   @primary  @note("  ") Long shId;// 主键 非空     
	private @caption("主题名称") @column("sh_name")    @note("  建议8个字以内的名称，建议无标点符号") String shName;// 非空  建议8个字以内的名称，建议无标点符号   
	private @caption("上级目录") @column("sh_parent")    @relate("$shParent") @RelateClass(SrvHelp.class)  @note("  ") Long shParent;// 非空     
 	private SrvHelp $shParent;//关联对象[系统帮助]
	private @caption("帮助简述") @column("sh_profile")    @note("  一句话描述一下该帮助功能帮助") String shProfile;// 非空  一句话描述一下该帮助功能帮助   
	private @caption("详细说明") @column("sh_text")  @hidden   @note("  ") String shText;//     
	private @caption("流程导向") @column("sh_mind")  @hidden   @note("  ") String shMind;//     
	private @caption("操作截图") @column("sh_images")  @hidden   @note("  ") String shImages;//     
	private @caption("修改人员") @column("sh_editor")    @relate("$shEditor") @RelateClass(SrvUser.class)  @note("  ") Long shEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $shEditor;//关联对象[用户]
	private @caption("添加时间") @column("sh_add_time")    @note("  ") Date shAddTime;// 非空     
	private @caption("修改时间") @column("sh_update_time")    @note("  ") Date shUpdateTime;// 非空     
	private @caption("优先级") @column("sh_level")    @note("  请输入一个100以内的数描述其先后顺序") Integer shLevel;//  请输入一个100以内的数描述其先后顺序   
	private @caption("状态") @column("sh_status")    @note(" 1:有效 0:内容  ") Short shStatus;// 非空 1:有效 0:内容     
	
	//默认构造函数
	public SrvHelp(){
	
	}
	
	//主键构造函数
	public SrvHelp(Long id){
		this.shId = id;
	}
	
	/**所有字段构造函数 SrvHelp(shName,shParent,shProfile,shText,shMind,shImages,shEditor,shAddTime,shUpdateTime,shLevel,shStatus)
	 SrvHelp(
	 	$.getString("shName")//主题名称 [非空]
	 	,$.getLong("shParent")//上级目录 [非空]
	 	,$.getString("shProfile")//帮助简述 [非空]
	 	,$.getString("shText")//详细说明
	 	,$.getString("shMind")//流程导向
	 	,$.getString("shImages")//操作截图
	 	,$.getLong("shEditor")//修改人员 [非空]
	 	,$.getDate("shAddTime")//添加时间 [非空]
	 	,$.getDate("shUpdateTime")//修改时间 [非空]
	 	,$.getInteger("shLevel")//优先级
	 	,$.getShort("shStatus")//状态 [非空]
	 )
	**/
	public SrvHelp(String shName,Long shParent,String shProfile,String shText,String shMind,String shImages,Long shEditor,Date shAddTime,Date shUpdateTime,Integer shLevel,Short shStatus){
		this.shName=shName;
		this.shParent=shParent;
		this.shProfile=shProfile;
		this.shText=shText;
		this.shMind=shMind;
		this.shImages=shImages;
		this.shEditor=shEditor;
		this.shAddTime=shAddTime;
		this.shUpdateTime=shUpdateTime;
		this.shLevel=shLevel;
		this.shStatus=shStatus;
	}
	
	//设置非空字段
	public SrvHelp setNotNull(String shName,Long shParent,String shProfile,Long shEditor,Date shAddTime,Date shUpdateTime,Short shStatus){
		this.shName=shName;
		this.shParent=shParent;
		this.shProfile=shProfile;
		this.shEditor=shEditor;
		this.shAddTime=shAddTime;
		this.shUpdateTime=shUpdateTime;
		this.shStatus=shStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public SrvHelp shId(Long shId){
		this.shId = shId;
		this.setSeted(F.shId);
		return this;
	}
	/** 主题名称 [非空]    建议8个字以内的名称，建议无标点符号   **/
	public SrvHelp shName(String shName){
		this.shName = shName;
		this.setSeted(F.shName);
		return this;
	}
	/** 上级目录 [非空] [SrvHelp]      **/
	public SrvHelp shParent(Long shParent){
		this.shParent = shParent;
		this.setSeted(F.shParent);
		return this;
	}
	/** 帮助简述 [非空]    一句话描述一下该帮助功能帮助   **/
	public SrvHelp shProfile(String shProfile){
		this.shProfile = shProfile;
		this.setSeted(F.shProfile);
		return this;
	}
	/** 详细说明        **/
	public SrvHelp shText(String shText){
		this.shText = shText;
		this.setSeted(F.shText);
		return this;
	}
	/** 流程导向        **/
	public SrvHelp shMind(String shMind){
		this.shMind = shMind;
		this.setSeted(F.shMind);
		return this;
	}
	/** 操作截图        **/
	public SrvHelp shImages(String shImages){
		this.shImages = shImages;
		this.setSeted(F.shImages);
		return this;
	}
	/** 修改人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public SrvHelp shEditor(Long shEditor){
		this.shEditor = shEditor;
		this.setSeted(F.shEditor);
		return this;
	}
	/** 添加时间 [非空]       **/
	public SrvHelp shAddTime(Date shAddTime){
		this.shAddTime = shAddTime;
		this.setSeted(F.shAddTime);
		return this;
	}
	/** 修改时间 [非空]       **/
	public SrvHelp shUpdateTime(Date shUpdateTime){
		this.shUpdateTime = shUpdateTime;
		this.setSeted(F.shUpdateTime);
		return this;
	}
	/** 优先级     请输入一个100以内的数描述其先后顺序   **/
	public SrvHelp shLevel(Integer shLevel){
		this.shLevel = shLevel;
		this.setSeted(F.shLevel);
		return this;
	}
	/** 状态 [非空]   1:有效 0:内容     **/
	public SrvHelp shStatus(Short shStatus){
		this.shStatus = shStatus;
		this.setSeted(F.shStatus);
		return this;
	}
	
	
	//克隆对象
	public SrvHelp clone(){
		SrvHelp clone = new SrvHelp();
		clone.shName=this.shName;
		clone.shParent=this.shParent;
		clone.shProfile=this.shProfile;
		clone.shEditor=this.shEditor;
		clone.shAddTime=this.shAddTime;
		clone.shUpdateTime=this.shUpdateTime;
		clone.shStatus=this.shStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取系统帮助
	 * @param id
	 * @return
	 */
	public static @api SrvHelp get(Long id){		
		return getSrvHelpById(id);
	}
	/**
	 * 获取所有系统帮助
	 * @return
	 */
	public static @api List<SrvHelp> list(Map params,Integer size){
		return getSrvHelpList(params,size);
	}
	/**
	 * 获取系统帮助分页
	 * @return
	 */
	public static @api Page<SrvHelp> page(int page,int size,Map params){
		return getSrvHelpPage(page, size , params);
	}
	/**
	 * 根据查询条件取系统帮助
	 * @param params
	 * @return
	 */
	public static @api SrvHelp Get(Map params){
		return getSrvHelp(params);
	}
	/**
	 * 获取系统帮助数
	 * @return
	 */
	public static @api Long count(Map params){
		return getSrvHelpCount(params);
	}
	/**
	 * 获取系统帮助数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getSrvHelpEval(eval,params);
	}
	
	/**
	 * 根据ID取系统帮助
	 * @param id
	 * @return
	 */
	public static @api SrvHelp getSrvHelpById(Long id){		
		SrvHelp srvHelp = (SrvHelp) $.GetRequest("SrvHelp$"+id);
		if(srvHelp!=null)
			return srvHelp;
		com.ccclubs.service.admin.ISrvHelpService srvHelpService = $.GetSpringBean("srvHelpService");		
		return srvHelpService.getSrvHelpById(id);
	}
	
	
	/**
	 * 根据ID取系统帮助的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(SrvHelp.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		SrvHelp srvHelp = get(id);
		if(srvHelp!=null){
			String strValue = srvHelp.getShName$();
			if(!"ShName".equals("ShName"))
				strValue+="("+srvHelp.getShName$()+")";
			MemCache.setValue(SrvHelp.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getShName$();
		if(!"ShName".equals("ShName"))
			keyValue+="("+this.getShName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有系统帮助
	 * @return
	 */
	public static @api List<SrvHelp> getSrvHelpList(Map params,Integer size){
		com.ccclubs.service.admin.ISrvHelpService srvHelpService = $.GetSpringBean("srvHelpService");
		return srvHelpService.getSrvHelpList(params, size);
	}
	
	/**
	 * 获取系统帮助分页
	 * @return
	 */
	public static @api Page<SrvHelp> getSrvHelpPage(int page,int size,Map params){
		com.ccclubs.service.admin.ISrvHelpService srvHelpService = $.GetSpringBean("srvHelpService");
		return srvHelpService.getSrvHelpPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取系统帮助
	 * @param params
	 * @return
	 */
	public static @api SrvHelp getSrvHelp(Map params){
		com.ccclubs.service.admin.ISrvHelpService srvHelpService = $.GetSpringBean("srvHelpService");
		return srvHelpService.getSrvHelp(params);
	}
	
	/**
	 * 获取系统帮助数
	 * @return
	 */
	public static @api Long getSrvHelpCount(Map params){
		com.ccclubs.service.admin.ISrvHelpService srvHelpService = $.GetSpringBean("srvHelpService");
		return srvHelpService.getSrvHelpCount(params);
	}
		
		
	/**
	 * 获取系统帮助自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getSrvHelpEval(String eval,Map params){
		com.ccclubs.service.admin.ISrvHelpService srvHelpService = $.GetSpringBean("srvHelpService");
		return srvHelpService.getSrvHelpEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateSrvHelp(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ISrvHelpService srvHelpService = $.GetSpringBean("srvHelpService");
		srvHelpService.updateSrvHelpByConfirm(set, where);
	}
	
	
	/**
	 * 保存系统帮助对象
	 * @param params
	 * @return
	 */
	public SrvHelp save(){
		com.ccclubs.service.admin.ISrvHelpService srvHelpService = $.GetSpringBean("srvHelpService");
		if(this.getShId()!=null)
			srvHelpService.updateSrvHelp(this);
		else
			return srvHelpService.saveSrvHelp(this);
		return this;
	}
	
	
	/**
	 * 更新系统帮助对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ISrvHelpService srvHelpService = $.GetSpringBean("srvHelpService");
		srvHelpService.updateSrvHelp$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ISrvHelpService srvHelpService = $.GetSpringBean("srvHelpService");
			srvHelpService.deleteSrvHelpById(this.getShId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ISrvHelpService srvHelpService = $.GetSpringBean("srvHelpService");
		return srvHelpService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getShId(){
		return this.shId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getShId$(){
		String strValue="";
		 strValue=$.str(this.getShId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setShId(Long shId){
		this.shId = shId;
		this.setSeted(F.shId);
	}
	/*******************************主题名称**********************************/	
	/**
	* 主题名称 [非空]    建议8个字以内的名称，建议无标点符号  
	**/
	public String getShName(){
		return this.shName;
	}
	/**
	* 获取主题名称格式化(toString)
	**/
	public String getShName$(){
		String strValue="";
		 strValue=$.str(this.getShName());
	 	 return strValue;
	}
	/**
	* 主题名称 [非空]    建议8个字以内的名称，建议无标点符号  
	**/
	public void setShName(String shName){
		this.shName = shName;
		this.setSeted(F.shName);
	}
	/*******************************上级目录**********************************/	
	/**
	* 上级目录 [非空] [SrvHelp]     
	**/
	public Long getShParent(){
		return this.shParent;
	}
	/**
	* 获取上级目录格式化(toString)
	**/
	public String getShParent$(){
		String strValue="";
		if(this.getShParent()!=null){
				strValue+=$.str(SrvHelp.getKeyValue(this.getShParent()));
		}			
	 	 return strValue;
	}
	/**
	* 上级目录 [非空] [SrvHelp]     
	**/
	public void setShParent(Long shParent){
		this.shParent = shParent;
		this.setSeted(F.shParent);
	}
	/**
	* 获取关联对象[系统帮助]
	**/	 			
 	public SrvHelp get$shParent(){
 		com.ccclubs.model.SrvHelp srvHelp = (com.ccclubs.model.SrvHelp) $.GetRequest("SrvHelp$"+this.getShParent());
 		if(srvHelp!=null)
			return srvHelp;
		if(this.getShParent()!=null){
 			srvHelp = SrvHelp.get(this.getShParent());
 		}
 		$.SetRequest("SrvHelp$"+this.getShParent(), srvHelp);
	 	return srvHelp;
	}
	/*******************************帮助简述**********************************/	
	/**
	* 帮助简述 [非空]    一句话描述一下该帮助功能帮助  
	**/
	public String getShProfile(){
		return this.shProfile;
	}
	/**
	* 获取帮助简述格式化(toString)
	**/
	public String getShProfile$(){
		String strValue="";
		 strValue=$.str(this.getShProfile());
	 	 return strValue;
	}
	/**
	* 帮助简述 [非空]    一句话描述一下该帮助功能帮助  
	**/
	public void setShProfile(String shProfile){
		this.shProfile = shProfile;
		this.setSeted(F.shProfile);
	}
	/*******************************详细说明**********************************/	
	/**
	* 详细说明       
	**/
	public String getShText(){
		return this.shText;
	}
	/**
	* 获取详细说明格式化(toString)
	**/
	public String getShText$(){
		String strValue="";
		 strValue=$.str(this.getShText());
	 	 return strValue;
	}
	/**
	* 详细说明       
	**/
	public void setShText(String shText){
		this.shText = shText;
		this.setSeted(F.shText);
	}
	/*******************************流程导向**********************************/	
	/**
	* 流程导向       
	**/
	public String getShMind(){
		return this.shMind;
	}
	/**
	* 获取流程导向格式化(toString)
	**/
	public String getShMind$(){
		String strValue="";
		 strValue=$.str(this.getShMind());
	 	 return strValue;
	}
	/**
	* 流程导向       
	**/
	public void setShMind(String shMind){
		this.shMind = shMind;
		this.setSeted(F.shMind);
	}
	/*******************************操作截图**********************************/	
	/**
	* 操作截图       
	**/
	public String getShImages(){
		return this.shImages;
	}
	/**
	* 获取操作截图格式化(toString)
	**/
	public String getShImages$(){
		String strValue="";
		 strValue=$.str(this.getShImages());
	 	 return strValue;
	}
	/**
	* 操作截图       
	**/
	public void setShImages(String shImages){
		this.shImages = shImages;
		this.setSeted(F.shImages);
	}
	/*******************************修改人员**********************************/	
	/**
	* 修改人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getShEditor(){
		return this.shEditor;
	}
	/**
	* 获取修改人员格式化(toString)
	**/
	public String getShEditor$(){
		String strValue="";
		if(this.getShEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getShEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 修改人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setShEditor(Long shEditor){
		this.shEditor = shEditor;
		this.setSeted(F.shEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$shEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getShEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getShEditor()!=null){
 			srvUser = SrvUser.get(this.getShEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getShEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getShAddTime(){
		return this.shAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getShAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getShAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setShAddTime(Date shAddTime){
		this.shAddTime = shAddTime;
		this.setSeted(F.shAddTime);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getShUpdateTime(){
		return this.shUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getShUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getShUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setShUpdateTime(Date shUpdateTime){
		this.shUpdateTime = shUpdateTime;
		this.setSeted(F.shUpdateTime);
	}
	/*******************************优先级**********************************/	
	/**
	* 优先级     请输入一个100以内的数描述其先后顺序  
	**/
	public Integer getShLevel(){
		return this.shLevel;
	}
	/**
	* 获取优先级格式化(toString)
	**/
	public String getShLevel$(){
		String strValue="";
		 strValue=$.str(this.getShLevel());
	 	 return strValue;
	}
	/**
	* 优先级     请输入一个100以内的数描述其先后顺序  
	**/
	public void setShLevel(Integer shLevel){
		this.shLevel = shLevel;
		this.setSeted(F.shLevel);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:有效 0:内容    
	**/
	public Short getShStatus(){
		return this.shStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getShStatus$(){
		String strValue="";
		 if($.equals($.str(this.getShStatus()),"1"))
			strValue=$.str("有效");		 
		 if($.equals($.str(this.getShStatus()),"0"))
			strValue=$.str("内容");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:有效 0:内容    
	**/
	public void setShStatus(Short shStatus){
		this.shStatus = shStatus;
		this.setSeted(F.shStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${SrvHelp.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvHelp.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvHelp.class.getMethod("get$"+$.forMat(key.toString())).invoke(SrvHelp.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${SrvHelp.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvHelp.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvHelp.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(SrvHelp.this);
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
		public M shId(Object shId){this.put("shId", shId);return this;};
	 	/** and sh_id is null */
 		public M shIdNull(){if(this.get("shIdNot")==null)this.put("shIdNot", "");this.put("shId", null);return this;};
 		/** not .... */
 		public M shIdNot(){this.put("shIdNot", "not");return this;};
		/** 主题名称 [非空]    建议8个字以内的名称，建议无标点符号   **/
		public M shName(Object shName){this.put("shName", shName);return this;};
	 	/** and sh_name is null */
 		public M shNameNull(){if(this.get("shNameNot")==null)this.put("shNameNot", "");this.put("shName", null);return this;};
 		/** not .... */
 		public M shNameNot(){this.put("shNameNot", "not");return this;};
		/** 上级目录 [非空] [SrvHelp]      **/
		public M shParent(Object shParent){this.put("shParent", shParent);return this;};
	 	/** and sh_parent is null */
 		public M shParentNull(){if(this.get("shParentNot")==null)this.put("shParentNot", "");this.put("shParent", null);return this;};
 		/** not .... */
 		public M shParentNot(){this.put("shParentNot", "not");return this;};
		public M shParent$on(SrvHelp.M value){
			this.put("SrvHelp", value);
			this.put("shParent$on", value);
			return this;
		};	
		/** 帮助简述 [非空]    一句话描述一下该帮助功能帮助   **/
		public M shProfile(Object shProfile){this.put("shProfile", shProfile);return this;};
	 	/** and sh_profile is null */
 		public M shProfileNull(){if(this.get("shProfileNot")==null)this.put("shProfileNot", "");this.put("shProfile", null);return this;};
 		/** not .... */
 		public M shProfileNot(){this.put("shProfileNot", "not");return this;};
		/** 详细说明        **/
		public M shText(Object shText){this.put("shText", shText);return this;};
	 	/** and sh_text is null */
 		public M shTextNull(){if(this.get("shTextNot")==null)this.put("shTextNot", "");this.put("shText", null);return this;};
 		/** not .... */
 		public M shTextNot(){this.put("shTextNot", "not");return this;};
		/** 流程导向        **/
		public M shMind(Object shMind){this.put("shMind", shMind);return this;};
	 	/** and sh_mind is null */
 		public M shMindNull(){if(this.get("shMindNot")==null)this.put("shMindNot", "");this.put("shMind", null);return this;};
 		/** not .... */
 		public M shMindNot(){this.put("shMindNot", "not");return this;};
		/** 操作截图        **/
		public M shImages(Object shImages){this.put("shImages", shImages);return this;};
	 	/** and sh_images is null */
 		public M shImagesNull(){if(this.get("shImagesNot")==null)this.put("shImagesNot", "");this.put("shImages", null);return this;};
 		/** not .... */
 		public M shImagesNot(){this.put("shImagesNot", "not");return this;};
		/** 修改人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M shEditor(Object shEditor){this.put("shEditor", shEditor);return this;};
	 	/** and sh_editor is null */
 		public M shEditorNull(){if(this.get("shEditorNot")==null)this.put("shEditorNot", "");this.put("shEditor", null);return this;};
 		/** not .... */
 		public M shEditorNot(){this.put("shEditorNot", "not");return this;};
		/** 添加时间 [非空]       **/
		public M shAddTime(Object shAddTime){this.put("shAddTime", shAddTime);return this;};
	 	/** and sh_add_time is null */
 		public M shAddTimeNull(){if(this.get("shAddTimeNot")==null)this.put("shAddTimeNot", "");this.put("shAddTime", null);return this;};
 		/** not .... */
 		public M shAddTimeNot(){this.put("shAddTimeNot", "not");return this;};
 		/** and sh_add_time >= ? */
 		public M shAddTimeStart(Object start){this.put("shAddTimeStart", start);return this;};			
 		/** and sh_add_time <= ? */
 		public M shAddTimeEnd(Object end){this.put("shAddTimeEnd", end);return this;};
		/** 修改时间 [非空]       **/
		public M shUpdateTime(Object shUpdateTime){this.put("shUpdateTime", shUpdateTime);return this;};
	 	/** and sh_update_time is null */
 		public M shUpdateTimeNull(){if(this.get("shUpdateTimeNot")==null)this.put("shUpdateTimeNot", "");this.put("shUpdateTime", null);return this;};
 		/** not .... */
 		public M shUpdateTimeNot(){this.put("shUpdateTimeNot", "not");return this;};
 		/** and sh_update_time >= ? */
 		public M shUpdateTimeStart(Object start){this.put("shUpdateTimeStart", start);return this;};			
 		/** and sh_update_time <= ? */
 		public M shUpdateTimeEnd(Object end){this.put("shUpdateTimeEnd", end);return this;};
		/** 优先级     请输入一个100以内的数描述其先后顺序   **/
		public M shLevel(Object shLevel){this.put("shLevel", shLevel);return this;};
	 	/** and sh_level is null */
 		public M shLevelNull(){if(this.get("shLevelNot")==null)this.put("shLevelNot", "");this.put("shLevel", null);return this;};
 		/** not .... */
 		public M shLevelNot(){this.put("shLevelNot", "not");return this;};
		/** and sh_level >= ? */
		public M shLevelMin(Object min){this.put("shLevelMin", min);return this;};
		/** and sh_level <= ? */
		public M shLevelMax(Object max){this.put("shLevelMax", max);return this;};
		/** 状态 [非空]   1:有效 0:内容     **/
		public M shStatus(Object shStatus){this.put("shStatus", shStatus);return this;};
	 	/** and sh_status is null */
 		public M shStatusNull(){if(this.get("shStatusNot")==null)this.put("shStatusNot", "");this.put("shStatus", null);return this;};
 		/** not .... */
 		public M shStatusNot(){this.put("shStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有系统帮助 **/
		public @api List<SrvHelp> list(Integer size){
			return getSrvHelpList(this,size);
		}
		/** 获取系统帮助分页 **/
		public @api Page<SrvHelp> page(int page,int size){
			return getSrvHelpPage(page, size , this);
		}
		/** 根据查询条件取系统帮助 **/
		public @api SrvHelp get(){
			return getSrvHelp(this);
		}
		/** 获取系统帮助数 **/
		public @api Long count(){
			return getSrvHelpCount(this);
		}
		/** 获取系统帮助表达式 **/
		public @api <T> T eval(String strEval){
			return getSrvHelpEval(strEval,this);
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
			updateSrvHelp(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String shId="shId";
		/** 主题名称 [非空]    建议8个字以内的名称，建议无标点符号   **/
		public final static @type(String.class) @like String shName="shName";
		/** 上级目录 [非空] [SrvHelp]      **/
		public final static @type(Long.class)  String shParent="shParent";
		/** 帮助简述 [非空]    一句话描述一下该帮助功能帮助   **/
		public final static @type(String.class) @like String shProfile="shProfile";
		/** 详细说明        **/
		public final static @type(String.class) @like String shText="shText";
		/** 流程导向        **/
		public final static @type(String.class)  String shMind="shMind";
		/** 操作截图        **/
		public final static @type(String.class)  String shImages="shImages";
		/** 修改人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String shEditor="shEditor";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String shAddTime="shAddTime";
	 	/** and sh_add_time >= ? */
 		public final static @type(Date.class) String shAddTimeStart="shAddTimeStart";
 		/** and sh_add_time <= ? */
 		public final static @type(Date.class) String shAddTimeEnd="shAddTimeEnd";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String shUpdateTime="shUpdateTime";
	 	/** and sh_update_time >= ? */
 		public final static @type(Date.class) String shUpdateTimeStart="shUpdateTimeStart";
 		/** and sh_update_time <= ? */
 		public final static @type(Date.class) String shUpdateTimeEnd="shUpdateTimeEnd";
		/** 优先级     请输入一个100以内的数描述其先后顺序   **/
		public final static @type(Integer.class)  String shLevel="shLevel";
		/** and sh_level >= ? */
		public final static @type(Integer.class) String shLevelMin="shLevelMin";
		/** and sh_level <= ? */
		public final static @type(Integer.class) String shLevelMax="shLevelMax";
		/** 状态 [非空]   1:有效 0:内容     **/
		public final static @type(Short.class)  String shStatus="shStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String shId="sh_id";
		/** 主题名称 [非空]    建议8个字以内的名称，建议无标点符号   **/
		public final static String shName="sh_name";
		/** 上级目录 [非空] [SrvHelp]      **/
		public final static String shParent="sh_parent";
		/** 帮助简述 [非空]    一句话描述一下该帮助功能帮助   **/
		public final static String shProfile="sh_profile";
		/** 详细说明        **/
		public final static String shText="sh_text";
		/** 流程导向        **/
		public final static String shMind="sh_mind";
		/** 操作截图        **/
		public final static String shImages="sh_images";
		/** 修改人员 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String shEditor="sh_editor";
		/** 添加时间 [非空]       **/
		public final static String shAddTime="sh_add_time";
		/** 修改时间 [非空]       **/
		public final static String shUpdateTime="sh_update_time";
		/** 优先级     请输入一个100以内的数描述其先后顺序   **/
		public final static String shLevel="sh_level";
		/** 状态 [非空]   1:有效 0:内容     **/
		public final static String shStatus="sh_status";
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
				$.Set(name,SrvHelp.getSrvHelp(params));
			else
				$.Set(name,SrvHelp.getSrvHelpList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取系统帮助数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.SrvHelp) $.GetRequest("SrvHelp$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = SrvHelp.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = SrvHelp.getSrvHelp(params);
			else
				value = SrvHelp.getSrvHelpList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = SrvHelp.Get($.add(SrvHelp.F.shId,param));
		else if(!$.empty(param.toString()))
			value = SrvHelp.get(Long.valueOf(param.toString()));
		$.SetRequest("SrvHelp$"+param.hashCode(), value);
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
	public void mergeSet(SrvHelp old){
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