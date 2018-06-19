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

@namespace("permissions/project")
public @caption("项目") @table("srv_project") class SrvProject implements java.io.Serializable
{
	private @caption("编号") @column("sp_id")   @primary  @note("  ") Long spId;// 主键 非空     
	private @caption("项目名称") @column("sp_name")    @note("  ") String spName;// 非空     
	private @caption("父节点") @column("sp_parent_id")  @hidden   @relate("$spParentId") @RelateClass(SrvProject.class)  @note("  ") Long spParentId;// 非空     
 	private SrvProject $spParentId;//关联对象[项目]
	private @caption("项目地址") @column("sp_url")    @note("  ") String spUrl;// 非空     
	private @caption("优先级") @column("sp_level")    @note("  ") Integer spLevel;//     
	private @caption("家族") @column("sp_family")    @note("  ") String spFamily;//     
	private @caption("Logo") @column("sp_logo")    @note("  ") String spLogo;//     
	private @caption("项目描述") @column("sp_descript")    @note("  ") String spDescript;//     
	private @caption("扩展权限") @column("sp_limit_exp")    @note("  ") String spLimitExp;//     
	private @caption("项目分组") @column("sp_group")    @note("  ") String spGroup;//     
	private @caption("流程信息") @column("sp_mind")  @hidden   @note("  ") String spMind;//     
	private @caption("状态") @column("sp_status")    @note(" 1:有效 0:无效  ") Short spStatus;// 非空 1:有效 0:无效     
	
	//默认构造函数
	public SrvProject(){
	
	}
	
	//主键构造函数
	public SrvProject(Long id){
		this.spId = id;
	}
	
	/**所有字段构造函数 SrvProject(spName,spParentId,spUrl,spLevel,spFamily,spLogo,spDescript,spLimitExp,spGroup,spMind,spStatus)
	 SrvProject(
	 	$.getString("spName")//项目名称 [非空]
	 	,$.getLong("spParentId")//父节点 [非空]
	 	,$.getString("spUrl")//项目地址 [非空]
	 	,$.getInteger("spLevel")//优先级
	 	,$.getString("spFamily")//家族
	 	,$.getString("spLogo")//Logo
	 	,$.getString("spDescript")//项目描述
	 	,$.getString("spLimitExp")//扩展权限
	 	,$.getString("spGroup")//项目分组
	 	,$.getString("spMind")//流程信息
	 	,$.getShort("spStatus")//状态 [非空]
	 )
	**/
	public SrvProject(String spName,Long spParentId,String spUrl,Integer spLevel,String spFamily,String spLogo,String spDescript,String spLimitExp,String spGroup,String spMind,Short spStatus){
		this.spName=spName;
		this.spParentId=spParentId;
		this.spUrl=spUrl;
		this.spLevel=spLevel;
		this.spFamily=spFamily;
		this.spLogo=spLogo;
		this.spDescript=spDescript;
		this.spLimitExp=spLimitExp;
		this.spGroup=spGroup;
		this.spMind=spMind;
		this.spStatus=spStatus;
	}
	
	//设置非空字段
	public SrvProject setNotNull(String spName,Long spParentId,String spUrl,Short spStatus){
		this.spName=spName;
		this.spParentId=spParentId;
		this.spUrl=spUrl;
		this.spStatus=spStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public SrvProject spId(Long spId){
		this.spId = spId;
		this.setSeted(F.spId);
		return this;
	}
	/** 项目名称 [非空]       **/
	public SrvProject spName(String spName){
		this.spName = spName;
		this.setSeted(F.spName);
		return this;
	}
	/** 父节点 [非空] [SrvProject]      **/
	public SrvProject spParentId(Long spParentId){
		this.spParentId = spParentId;
		this.setSeted(F.spParentId);
		return this;
	}
	/** 项目地址 [非空]       **/
	public SrvProject spUrl(String spUrl){
		this.spUrl = spUrl;
		this.setSeted(F.spUrl);
		return this;
	}
	/** 优先级        **/
	public SrvProject spLevel(Integer spLevel){
		this.spLevel = spLevel;
		this.setSeted(F.spLevel);
		return this;
	}
	/** 家族        **/
	public SrvProject spFamily(String spFamily){
		this.spFamily = spFamily;
		this.setSeted(F.spFamily);
		return this;
	}
	/** Logo        **/
	public SrvProject spLogo(String spLogo){
		this.spLogo = spLogo;
		this.setSeted(F.spLogo);
		return this;
	}
	/** 项目描述        **/
	public SrvProject spDescript(String spDescript){
		this.spDescript = spDescript;
		this.setSeted(F.spDescript);
		return this;
	}
	/** 扩展权限        **/
	public SrvProject spLimitExp(String spLimitExp){
		this.spLimitExp = spLimitExp;
		this.setSeted(F.spLimitExp);
		return this;
	}
	/** 项目分组        **/
	public SrvProject spGroup(String spGroup){
		this.spGroup = spGroup;
		this.setSeted(F.spGroup);
		return this;
	}
	/** 流程信息        **/
	public SrvProject spMind(String spMind){
		this.spMind = spMind;
		this.setSeted(F.spMind);
		return this;
	}
	/** 状态 [非空]   1:有效 0:无效     **/
	public SrvProject spStatus(Short spStatus){
		this.spStatus = spStatus;
		this.setSeted(F.spStatus);
		return this;
	}
	
	
	//克隆对象
	public SrvProject clone(){
		SrvProject clone = new SrvProject();
		clone.spName=this.spName;
		clone.spParentId=this.spParentId;
		clone.spUrl=this.spUrl;
		clone.spStatus=this.spStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取项目
	 * @param id
	 * @return
	 */
	public static @api SrvProject get(Long id){		
		return getSrvProjectById(id);
	}
	/**
	 * 获取所有项目
	 * @return
	 */
	public static @api List<SrvProject> list(Map params,Integer size){
		return getSrvProjectList(params,size);
	}
	
	/**
	 * 根据ID取项目
	 * @param id
	 * @return
	 */
	public static @api SrvProject getSrvProjectById(Long id){		
		SrvProject srvProject = (SrvProject) $.GetRequest("SrvProject$"+id);
		if(srvProject!=null)
			return srvProject;
		com.ccclubs.service.admin.ISrvProjectService srvProjectService = $.GetSpringBean("srvProjectService");		
		return srvProjectService.getSrvProjectById(id);
	}
	
	
	/**
	 * 根据ID取项目的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.kv(SrvProject.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		SrvProject srvProject = get(id);
		if(srvProject!=null){
			String strValue = srvProject.getSpName$();
			if(!"SpName".equals("SpName"))
				strValue+="("+srvProject.getSpName$()+")";
			return MemCache.kv(SrvProject.class, id , srvProject.getKeyValue());
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getSpName$();
		if(!"SpName".equals("SpName"))
			keyValue+="("+this.getSpName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有项目
	 * @return
	 */
	public static @api List<SrvProject> getSrvProjectList(Map params,Integer size){
		com.ccclubs.service.admin.ISrvProjectService srvProjectService = $.GetSpringBean("srvProjectService");
		return srvProjectService.getSrvProjectList(params, size);
	}
	
	/**
	 * 保存项目对象
	 * @param params
	 * @return
	 */
	public SrvProject save(){
		com.ccclubs.service.admin.ISrvProjectService srvProjectService = $.GetSpringBean("srvProjectService");
		if(this.getSpId()!=null)
			srvProjectService.updateSrvProject(this);
		else
			return srvProjectService.saveSrvProject(this);
		return this;
	}
	
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ISrvProjectService srvProjectService = $.GetSpringBean("srvProjectService");
			srvProjectService.deleteSrvProjectById(this.getSpId());
	}
	
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getSpId(){
		return this.spId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getSpId$(){
		String strValue="";
		 strValue=$.str(this.getSpId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setSpId(Long spId){
		this.spId = spId;
		this.setSeted(F.spId);
	}
	/*******************************项目名称**********************************/	
	/**
	* 项目名称 [非空]      
	**/
	public String getSpName(){
		return this.spName;
	}
	/**
	* 获取项目名称格式化(toString)
	**/
	public String getSpName$(){
		String strValue="";
		 strValue=$.str(this.getSpName());
	 	 return strValue;
	}
	/**
	* 项目名称 [非空]      
	**/
	public void setSpName(String spName){
		this.spName = spName;
		this.setSeted(F.spName);
	}
	/*******************************父节点**********************************/	
	/**
	* 父节点 [非空] [SrvProject]     
	**/
	public Long getSpParentId(){
		return this.spParentId;
	}
	/**
	* 获取父节点格式化(toString)
	**/
	public String getSpParentId$(){
		String strValue="";
		if(this.getSpParentId()!=null){
				strValue+=$.str(SrvProject.getKeyValue(this.getSpParentId()));
		}			
	 	 return strValue;
	}
	/**
	* 父节点 [非空] [SrvProject]     
	**/
	public void setSpParentId(Long spParentId){
		this.spParentId = spParentId;
		this.setSeted(F.spParentId);
	}
	/**
	* 获取关联对象[项目]
	**/	 			
 	public SrvProject get$spParentId(){
 		com.ccclubs.model.SrvProject srvProject = (com.ccclubs.model.SrvProject) $.GetRequest("SrvProject$"+this.getSpParentId());
 		if(srvProject!=null)
			return srvProject;
		if(this.getSpParentId()!=null){
 			srvProject = SrvProject.get(this.getSpParentId());
 		}
 		$.SetRequest("SrvProject$"+this.getSpParentId(), srvProject);
	 	return srvProject;
	}
	/*******************************项目地址**********************************/	
	/**
	* 项目地址 [非空]      
	**/
	public String getSpUrl(){
		return this.spUrl;
	}
	/**
	* 获取项目地址格式化(toString)
	**/
	public String getSpUrl$(){
		String strValue="";
		 strValue=$.str(this.getSpUrl());
	 	 return strValue;
	}
	/**
	* 项目地址 [非空]      
	**/
	public void setSpUrl(String spUrl){
		this.spUrl = spUrl;
		this.setSeted(F.spUrl);
	}
	/*******************************优先级**********************************/	
	/**
	* 优先级       
	**/
	public Integer getSpLevel(){
		return this.spLevel;
	}
	/**
	* 获取优先级格式化(toString)
	**/
	public String getSpLevel$(){
		String strValue="";
		 strValue=$.str(this.getSpLevel());
	 	 return strValue;
	}
	/**
	* 优先级       
	**/
	public void setSpLevel(Integer spLevel){
		this.spLevel = spLevel;
		this.setSeted(F.spLevel);
	}
	/*******************************家族**********************************/	
	/**
	* 家族       
	**/
	public String getSpFamily(){
		return this.spFamily;
	}
	/**
	* 获取家族格式化(toString)
	**/
	public String getSpFamily$(){
		String strValue="";
		 strValue=$.str(this.getSpFamily());
	 	 return strValue;
	}
	/**
	* 家族       
	**/
	public void setSpFamily(String spFamily){
		this.spFamily = spFamily;
		this.setSeted(F.spFamily);
	}
	/*******************************Logo**********************************/	
	/**
	* Logo       
	**/
	public String getSpLogo(){
		return this.spLogo;
	}
	/**
	* 获取Logo格式化(toString)
	**/
	public String getSpLogo$(){
		String strValue="";
		 strValue=$.str(this.getSpLogo());
	 	 return strValue;
	}
	/**
	* Logo       
	**/
	public void setSpLogo(String spLogo){
		this.spLogo = spLogo;
		this.setSeted(F.spLogo);
	}
	/*******************************项目描述**********************************/	
	/**
	* 项目描述       
	**/
	public String getSpDescript(){
		return this.spDescript;
	}
	/**
	* 获取项目描述格式化(toString)
	**/
	public String getSpDescript$(){
		String strValue="";
		 strValue=$.str(this.getSpDescript());
	 	 return strValue;
	}
	/**
	* 项目描述       
	**/
	public void setSpDescript(String spDescript){
		this.spDescript = spDescript;
		this.setSeted(F.spDescript);
	}
	/*******************************扩展权限**********************************/	
	/**
	* 扩展权限       
	**/
	public String getSpLimitExp(){
		return this.spLimitExp;
	}
	/**
	* 获取扩展权限格式化(toString)
	**/
	public String getSpLimitExp$(){
		String strValue="";
		 strValue=$.str(this.getSpLimitExp());
	 	 return strValue;
	}
	/**
	* 扩展权限       
	**/
	public void setSpLimitExp(String spLimitExp){
		this.spLimitExp = spLimitExp;
		this.setSeted(F.spLimitExp);
	}
	/*******************************项目分组**********************************/	
	/**
	* 项目分组       
	**/
	public String getSpGroup(){
		return this.spGroup;
	}
	/**
	* 获取项目分组格式化(toString)
	**/
	public String getSpGroup$(){
		String strValue="";
		 strValue=$.str(this.getSpGroup());
	 	 return strValue;
	}
	/**
	* 项目分组       
	**/
	public void setSpGroup(String spGroup){
		this.spGroup = spGroup;
		this.setSeted(F.spGroup);
	}
	/*******************************流程信息**********************************/	
	/**
	* 流程信息       
	**/
	public String getSpMind(){
		return this.spMind;
	}
	/**
	* 获取流程信息格式化(toString)
	**/
	public String getSpMind$(){
		String strValue="";
		 strValue=$.str(this.getSpMind());
	 	 return strValue;
	}
	/**
	* 流程信息       
	**/
	public void setSpMind(String spMind){
		this.spMind = spMind;
		this.setSeted(F.spMind);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:有效 0:无效    
	**/
	public Short getSpStatus(){
		return this.spStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getSpStatus$(){
		String strValue="";
		 if($.equals($.str(this.getSpStatus()),"1"))
			strValue=$.str("有效");		 
		 if($.equals($.str(this.getSpStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:有效 0:无效    
	**/
	public void setSpStatus(Short spStatus){
		this.spStatus = spStatus;
		this.setSeted(F.spStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${SrvProject.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvProject.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvProject.class.getMethod("get$"+$.forMat(key.toString())).invoke(SrvProject.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${SrvProject.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvProject.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvProject.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(SrvProject.this);
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
	public static M m(){ return new M(); }
	public static M where(){ return new M(); }
	public static class M extends HashMap{
		/** 编号 [非空]       **/
		public M spId(Long spId){this.put("spId", spId);return this;};
		/** 项目名称 [非空]       **/
		public M spName(String spName){this.put("spName", spName);return this;};
		/** 父节点 [非空] [SrvProject]      **/
		public M spParentId(Long spParentId){this.put("spParentId", spParentId);return this;};
		/** 项目地址 [非空]       **/
		public M spUrl(String spUrl){this.put("spUrl", spUrl);return this;};
		/** 优先级        **/
		public M spLevel(Integer spLevel){this.put("spLevel", spLevel);return this;};
		/** and sp_level >= ? */
		public M spLevelMin(Integer min){this.put("spLevelMin", min);return this;};
		/** and sp_level <= ? */
		public M spLevelMax(Integer max){this.put("spLevelMax", max);return this;};
		/** 家族        **/
		public M spFamily(String spFamily){this.put("spFamily", spFamily);return this;};
		/** Logo        **/
		public M spLogo(String spLogo){this.put("spLogo", spLogo);return this;};
		/** 项目描述        **/
		public M spDescript(String spDescript){this.put("spDescript", spDescript);return this;};
		/** 扩展权限        **/
		public M spLimitExp(String spLimitExp){this.put("spLimitExp", spLimitExp);return this;};
		/** 项目分组        **/
		public M spGroup(String spGroup){this.put("spGroup", spGroup);return this;};
		/** 流程信息        **/
		public M spMind(String spMind){this.put("spMind", spMind);return this;};
		/** 状态 [非空]   1:有效 0:无效     **/
		public M spStatus(Short spStatus){this.put("spStatus", spStatus);return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有项目 **/
		public @api List<SrvProject> list(Integer size){
			return getSrvProjectList(this,size);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String spId="spId";
		/** 项目名称 [非空]       **/
		public final static @type(String.class) @like String spName="spName";
		/** 父节点 [非空] [SrvProject]      **/
		public final static @type(Long.class)  String spParentId="spParentId";
		/** 项目地址 [非空]       **/
		public final static @type(String.class) @like String spUrl="spUrl";
		/** 优先级        **/
		public final static @type(Integer.class)  String spLevel="spLevel";
		/** and sp_level >= ? */
		public final static @type(Integer.class) String spLevelMin="spLevelMin";
		/** and sp_level <= ? */
		public final static @type(Integer.class) String spLevelMax="spLevelMax";
		/** 家族        **/
		public final static @type(String.class) @like String spFamily="spFamily";
		/** Logo        **/
		public final static @type(String.class)  String spLogo="spLogo";
		/** 项目描述        **/
		public final static @type(String.class) @like String spDescript="spDescript";
		/** 扩展权限        **/
		public final static @type(String.class) @like String spLimitExp="spLimitExp";
		/** 项目分组        **/
		public final static @type(String.class) @like String spGroup="spGroup";
		/** 流程信息        **/
		public final static @type(String.class)  String spMind="spMind";
		/** 状态 [非空]   1:有效 0:无效     **/
		public final static @type(Short.class)  String spStatus="spStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String spId="sp_id";
		/** 项目名称 [非空]       **/
		public final static String spName="sp_name";
		/** 父节点 [非空] [SrvProject]      **/
		public final static String spParentId="sp_parent_id";
		/** 项目地址 [非空]       **/
		public final static String spUrl="sp_url";
		/** 优先级        **/
		public final static String spLevel="sp_level";
		/** 家族        **/
		public final static String spFamily="sp_family";
		/** Logo        **/
		public final static String spLogo="sp_logo";
		/** 项目描述        **/
		public final static String spDescript="sp_descript";
		/** 扩展权限        **/
		public final static String spLimitExp="sp_limit_exp";
		/** 项目分组        **/
		public final static String spGroup="sp_group";
		/** 流程信息        **/
		public final static String spMind="sp_mind";
		/** 状态 [非空]   1:有效 0:无效     **/
		public final static String spStatus="sp_status";
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
	
	
	/**字段设置标记队列**/
	private Map<String,Boolean> seted=new HashMap();
	public Boolean getSeted(String key){
		return seted.get(key);
	}
	public void setSeted(String key){
		seted.put(key,true);
	}
	/**同步未set的字段**/
	public void mergeSet(SrvProject old){
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