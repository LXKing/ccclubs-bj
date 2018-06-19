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

@namespace("configurator/area")
public @caption("网点区域") @table("cs_area") class CsArea implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csa_id")   @primary  @note("  ") Long csaId;// 主键 非空     
	private @caption("所在城市") @column("csa_host")    @relate("$csaHost") @RelateClass(SrvHost.class)  @note("  ") Long csaHost;// 非空     
 	private SrvHost $csaHost;//关联对象[运营城市]
	private @caption("区域名称") @column("csa_name")    @note("  ") String csaName;// 非空     
	private @caption("优先位") @column("csa_level")    @note("  数字大的排在前面") Integer csaLevel;//  数字大的排在前面   
	private @caption("状态") @column("csa_status")    @note(" 1:正常 0:无效  ") Short csaStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsArea(){
	
	}
	
	//主键构造函数
	public CsArea(Long id){
		this.csaId = id;
	}
	
	/**所有字段构造函数 CsArea(csaHost,csaName,csaLevel,csaStatus)
	 CsArea(
	 	$.getLong("csaHost")//所在城市 [非空]
	 	,$.getString("csaName")//区域名称 [非空]
	 	,$.getInteger("csaLevel")//优先位
	 	,$.getShort("csaStatus")//状态 [非空]
	 )
	**/
	public CsArea(Long csaHost,String csaName,Integer csaLevel,Short csaStatus){
		this.csaHost=csaHost;
		this.csaName=csaName;
		this.csaLevel=csaLevel;
		this.csaStatus=csaStatus;
	}
	
	//设置非空字段
	public CsArea setNotNull(Long csaHost,String csaName,Short csaStatus){
		this.csaHost=csaHost;
		this.csaName=csaName;
		this.csaStatus=csaStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsArea csaId(Long csaId){
		this.csaId = csaId;
		this.setSeted(F.csaId);
		return this;
	}
	/** 所在城市 [非空] [SrvHost]      **/
	public CsArea csaHost(Long csaHost){
		this.csaHost = csaHost;
		this.setSeted(F.csaHost);
		return this;
	}
	/** 区域名称 [非空]       **/
	public CsArea csaName(String csaName){
		this.csaName = csaName;
		this.setSeted(F.csaName);
		return this;
	}
	/** 优先位     数字大的排在前面   **/
	public CsArea csaLevel(Integer csaLevel){
		this.csaLevel = csaLevel;
		this.setSeted(F.csaLevel);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsArea csaStatus(Short csaStatus){
		this.csaStatus = csaStatus;
		this.setSeted(F.csaStatus);
		return this;
	}
	
	
	//克隆对象
	public CsArea clone(){
		CsArea clone = new CsArea();
		clone.csaHost=this.csaHost;
		clone.csaName=this.csaName;
		clone.csaStatus=this.csaStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取网点区域
	 * @param id
	 * @return
	 */
	public static @api CsArea get(Long id){		
		return getCsAreaById(id);
	}
	/**
	 * 获取所有网点区域
	 * @return
	 */
	public static @api List<CsArea> list(Map params,Integer size){
		return getCsAreaList(params,size);
	}
	/**
	 * 获取网点区域分页
	 * @return
	 */
	public static @api Page<CsArea> page(int page,int size,Map params){
		return getCsAreaPage(page, size , params);
	}
	/**
	 * 根据查询条件取网点区域
	 * @param params
	 * @return
	 */
	public static @api CsArea Get(Map params){
		return getCsArea(params);
	}
	/**
	 * 获取网点区域数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsAreaCount(params);
	}
	/**
	 * 获取网点区域数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsAreaEval(eval,params);
	}
	
	/**
	 * 根据ID取网点区域
	 * @param id
	 * @return
	 */
	public static @api CsArea getCsAreaById(Long id){		
		CsArea csArea = (CsArea) $.GetRequest("CsArea$"+id);
		if(csArea!=null)
			return csArea;
		com.ccclubs.service.admin.ICsAreaService csAreaService = $.GetSpringBean("csAreaService");		
		return csAreaService.getCsAreaById(id);
	}
	
	
	/**
	 * 根据ID取网点区域的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsArea.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsArea csArea = get(id);
		if(csArea!=null){
			String strValue = csArea.getCsaName$();
			if(!"CsaName".equals("CsaName"))
				strValue+="("+csArea.getCsaName$()+")";
			MemCache.setValue(CsArea.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsaName$();
		if(!"CsaName".equals("CsaName"))
			keyValue+="("+this.getCsaName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有网点区域
	 * @return
	 */
	public static @api List<CsArea> getCsAreaList(Map params,Integer size){
		com.ccclubs.service.admin.ICsAreaService csAreaService = $.GetSpringBean("csAreaService");
		return csAreaService.getCsAreaList(params, size);
	}
	
	/**
	 * 获取网点区域分页
	 * @return
	 */
	public static @api Page<CsArea> getCsAreaPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsAreaService csAreaService = $.GetSpringBean("csAreaService");
		return csAreaService.getCsAreaPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取网点区域
	 * @param params
	 * @return
	 */
	public static @api CsArea getCsArea(Map params){
		com.ccclubs.service.admin.ICsAreaService csAreaService = $.GetSpringBean("csAreaService");
		return csAreaService.getCsArea(params);
	}
	
	/**
	 * 获取网点区域数
	 * @return
	 */
	public static @api Long getCsAreaCount(Map params){
		com.ccclubs.service.admin.ICsAreaService csAreaService = $.GetSpringBean("csAreaService");
		return csAreaService.getCsAreaCount(params);
	}
		
		
	/**
	 * 获取网点区域自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsAreaEval(String eval,Map params){
		com.ccclubs.service.admin.ICsAreaService csAreaService = $.GetSpringBean("csAreaService");
		return csAreaService.getCsAreaEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsArea(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsAreaService csAreaService = $.GetSpringBean("csAreaService");
		csAreaService.updateCsAreaByConfirm(set, where);
	}
	
	
	/**
	 * 保存网点区域对象
	 * @param params
	 * @return
	 */
	public CsArea save(){
		com.ccclubs.service.admin.ICsAreaService csAreaService = $.GetSpringBean("csAreaService");
		if(this.getCsaId()!=null)
			csAreaService.updateCsArea(this);
		else
			return csAreaService.saveCsArea(this);
		return this;
	}
	
	
	/**
	 * 更新网点区域对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsAreaService csAreaService = $.GetSpringBean("csAreaService");
		csAreaService.updateCsArea$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsAreaService csAreaService = $.GetSpringBean("csAreaService");
		if($.equals($.config("logic_delete"),"true"))
			csAreaService.removeCsAreaById(this.getCsaId());
		else
			csAreaService.deleteCsAreaById(this.getCsaId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsAreaService csAreaService = $.GetSpringBean("csAreaService");
		return csAreaService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsaId(){
		return this.csaId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsaId$(){
		String strValue="";
		 strValue=$.str(this.getCsaId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsaId(Long csaId){
		this.csaId = csaId;
		this.setSeted(F.csaId);
	}
	/*******************************所在城市**********************************/	
	/**
	* 所在城市 [非空] [SrvHost]     
	**/
	public Long getCsaHost(){
		return this.csaHost;
	}
	/**
	* 获取所在城市格式化(toString)
	**/
	public String getCsaHost$(){
		String strValue="";
		if(this.getCsaHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsaHost()));
		}			
	 	 return strValue;
	}
	/**
	* 所在城市 [非空] [SrvHost]     
	**/
	public void setCsaHost(Long csaHost){
		this.csaHost = csaHost;
		this.setSeted(F.csaHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csaHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsaHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsaHost()!=null){
 			srvHost = SrvHost.get(this.getCsaHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsaHost(), srvHost);
	 	return srvHost;
	}
	/*******************************区域名称**********************************/	
	/**
	* 区域名称 [非空]      
	**/
	public String getCsaName(){
		return this.csaName;
	}
	/**
	* 获取区域名称格式化(toString)
	**/
	public String getCsaName$(){
		String strValue="";
		 strValue=$.str(this.getCsaName());
	 	 return strValue;
	}
	/**
	* 区域名称 [非空]      
	**/
	public void setCsaName(String csaName){
		this.csaName = csaName;
		this.setSeted(F.csaName);
	}
	/*******************************优先位**********************************/	
	/**
	* 优先位     数字大的排在前面  
	**/
	public Integer getCsaLevel(){
		return this.csaLevel;
	}
	/**
	* 获取优先位格式化(toString)
	**/
	public String getCsaLevel$(){
		String strValue="";
		 strValue=$.str(this.getCsaLevel());
	 	 return strValue;
	}
	/**
	* 优先位     数字大的排在前面  
	**/
	public void setCsaLevel(Integer csaLevel){
		this.csaLevel = csaLevel;
		this.setSeted(F.csaLevel);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsaStatus(){
		return this.csaStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsaStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsaStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsaStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsaStatus(Short csaStatus){
		this.csaStatus = csaStatus;
		this.setSeted(F.csaStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsArea.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsArea.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsArea.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsArea.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsArea.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsArea.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsArea.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsArea.this);
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
		public M csaId(Object csaId){this.put("csaId", csaId);return this;};
	 	/** and csa_id is null */
 		public M csaIdNull(){if(this.get("csaIdNot")==null)this.put("csaIdNot", "");this.put("csaId", null);return this;};
 		/** not .... */
 		public M csaIdNot(){this.put("csaIdNot", "not");return this;};
		/** 所在城市 [非空] [SrvHost]      **/
		public M csaHost(Object csaHost){this.put("csaHost", csaHost);return this;};
	 	/** and csa_host is null */
 		public M csaHostNull(){if(this.get("csaHostNot")==null)this.put("csaHostNot", "");this.put("csaHost", null);return this;};
 		/** not .... */
 		public M csaHostNot(){this.put("csaHostNot", "not");return this;};
		/** 区域名称 [非空]       **/
		public M csaName(Object csaName){this.put("csaName", csaName);return this;};
	 	/** and csa_name is null */
 		public M csaNameNull(){if(this.get("csaNameNot")==null)this.put("csaNameNot", "");this.put("csaName", null);return this;};
 		/** not .... */
 		public M csaNameNot(){this.put("csaNameNot", "not");return this;};
		/** 优先位     数字大的排在前面   **/
		public M csaLevel(Object csaLevel){this.put("csaLevel", csaLevel);return this;};
	 	/** and csa_level is null */
 		public M csaLevelNull(){if(this.get("csaLevelNot")==null)this.put("csaLevelNot", "");this.put("csaLevel", null);return this;};
 		/** not .... */
 		public M csaLevelNot(){this.put("csaLevelNot", "not");return this;};
		/** and csa_level >= ? */
		public M csaLevelMin(Object min){this.put("csaLevelMin", min);return this;};
		/** and csa_level <= ? */
		public M csaLevelMax(Object max){this.put("csaLevelMax", max);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csaStatus(Object csaStatus){this.put("csaStatus", csaStatus);return this;};
	 	/** and csa_status is null */
 		public M csaStatusNull(){if(this.get("csaStatusNot")==null)this.put("csaStatusNot", "");this.put("csaStatus", null);return this;};
 		/** not .... */
 		public M csaStatusNot(){this.put("csaStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有网点区域 **/
		public @api List<CsArea> list(Integer size){
			return getCsAreaList(this,size);
		}
		/** 获取网点区域分页 **/
		public @api Page<CsArea> page(int page,int size){
			return getCsAreaPage(page, size , this);
		}
		/** 根据查询条件取网点区域 **/
		public @api CsArea get(){
			return getCsArea(this);
		}
		/** 获取网点区域数 **/
		public @api Long count(){
			return getCsAreaCount(this);
		}
		/** 获取网点区域表达式 **/
		public @api <T> T eval(String strEval){
			return getCsAreaEval(strEval,this);
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
			updateCsArea(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csaId="csaId";
		/** 所在城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csaHost="csaHost";
		/** 区域名称 [非空]       **/
		public final static @type(String.class) @like String csaName="csaName";
		/** 优先位     数字大的排在前面   **/
		public final static @type(Integer.class)  String csaLevel="csaLevel";
		/** and csa_level >= ? */
		public final static @type(Integer.class) String csaLevelMin="csaLevelMin";
		/** and csa_level <= ? */
		public final static @type(Integer.class) String csaLevelMax="csaLevelMax";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csaStatus="csaStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csaId="csa_id";
		/** 所在城市 [非空] [SrvHost]      **/
		public final static String csaHost="csa_host";
		/** 区域名称 [非空]       **/
		public final static String csaName="csa_name";
		/** 优先位     数字大的排在前面   **/
		public final static String csaLevel="csa_level";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csaStatus="csa_status";
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
				$.Set(name,CsArea.getCsArea(params));
			else
				$.Set(name,CsArea.getCsAreaList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取网点区域数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsArea) $.GetRequest("CsArea$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsArea.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsArea.getCsArea(params);
			else
				value = CsArea.getCsAreaList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsArea.Get($.add(CsArea.F.csaId,param));
		else if(!$.empty(param.toString()))
			value = CsArea.get(Long.valueOf(param.toString()));
		$.SetRequest("CsArea$"+param.hashCode(), value);
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
	public void mergeSet(CsArea old){
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