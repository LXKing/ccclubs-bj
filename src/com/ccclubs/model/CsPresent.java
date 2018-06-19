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

@namespace("operate/present")
public @caption("礼品赠品") @table("cs_present") class CsPresent implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("csp_id")   @primary  @note("  ") Long cspId;// 主键 非空     
	private @caption("城市") @column("csp_host")    @relate("$cspHost") @RelateClass(SrvHost.class)  @note("  ") Long cspHost;// 非空     
 	private SrvHost $cspHost;//关联对象[运营城市]
	private @caption("礼品名称") @column("csp_name")    @note("  ") String cspName;// 非空     
	private @caption("礼品分类") @column("csp_type")    @relate("$cspType") @RelateClass(SrvProperty.class)  @note(" present_type:礼品赠品分类  ") Long cspType;// 非空 present_type:礼品赠品分类     
 	private SrvProperty $cspType;//关联对象[系统属性]
	private @caption("主图") @column("csp_image")  @hidden   @note("  ") String cspImage;// 非空     
	private @caption("图列表") @column("csp_images")  @hidden   @note("  ") String cspImages;//     
	private @caption("详细信息") @column("csp_profile")  @hidden   @note("  ") String cspProfile;//     
	private @caption("库存数") @column("csp_count")    @note("  ") Integer cspCount;//     
	private @caption("修改时间") @column("csp_update_time")    @note("  ") Date cspUpdateTime;// 非空     
	private @caption("添加时间") @column("csp_add_time")    @note("  ") Date cspAddTime;// 非空     
	private @caption("状态") @column("csp_status")    @note(" 1:正常 0:无效  ") Short cspStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsPresent(){
	
	}
	
	//主键构造函数
	public CsPresent(Long id){
		this.cspId = id;
	}
	
	/**所有字段构造函数 CsPresent(cspHost,cspName,cspType,cspImage,cspImages,cspProfile,cspCount,cspUpdateTime,cspAddTime,cspStatus)
	 CsPresent(
	 	$.getLong("cspHost")//城市 [非空]
	 	,$.getString("cspName")//礼品名称 [非空]
	 	,$.getLong("cspType")//礼品分类 [非空]
	 	,$.getString("cspImage")//主图 [非空]
	 	,$.getString("cspImages")//图列表
	 	,$.getString("cspProfile")//详细信息
	 	,$.getInteger("cspCount")//库存数
	 	,$.getDate("cspUpdateTime")//修改时间 [非空]
	 	,$.getDate("cspAddTime")//添加时间 [非空]
	 	,$.getShort("cspStatus")//状态 [非空]
	 )
	**/
	public CsPresent(Long cspHost,String cspName,Long cspType,String cspImage,String cspImages,String cspProfile,Integer cspCount,Date cspUpdateTime,Date cspAddTime,Short cspStatus){
		this.cspHost=cspHost;
		this.cspName=cspName;
		this.cspType=cspType;
		this.cspImage=cspImage;
		this.cspImages=cspImages;
		this.cspProfile=cspProfile;
		this.cspCount=cspCount;
		this.cspUpdateTime=cspUpdateTime;
		this.cspAddTime=cspAddTime;
		this.cspStatus=cspStatus;
	}
	
	//设置非空字段
	public CsPresent setNotNull(Long cspHost,String cspName,Long cspType,String cspImage,Date cspUpdateTime,Date cspAddTime,Short cspStatus){
		this.cspHost=cspHost;
		this.cspName=cspName;
		this.cspType=cspType;
		this.cspImage=cspImage;
		this.cspUpdateTime=cspUpdateTime;
		this.cspAddTime=cspAddTime;
		this.cspStatus=cspStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsPresent cspId(Long cspId){
		this.cspId = cspId;
		this.setSeted(F.cspId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsPresent cspHost(Long cspHost){
		this.cspHost = cspHost;
		this.setSeted(F.cspHost);
		return this;
	}
	/** 礼品名称 [非空]       **/
	public CsPresent cspName(String cspName){
		this.cspName = cspName;
		this.setSeted(F.cspName);
		return this;
	}
	/** 礼品分类 [非空] [SrvProperty]  present_type:礼品赠品分类     **/
	public CsPresent cspType(Long cspType){
		this.cspType = cspType;
		this.setSeted(F.cspType);
		return this;
	}
	/** 主图 [非空]       **/
	public CsPresent cspImage(String cspImage){
		this.cspImage = cspImage;
		this.setSeted(F.cspImage);
		return this;
	}
	/** 图列表        **/
	public CsPresent cspImages(String cspImages){
		this.cspImages = cspImages;
		this.setSeted(F.cspImages);
		return this;
	}
	/** 详细信息        **/
	public CsPresent cspProfile(String cspProfile){
		this.cspProfile = cspProfile;
		this.setSeted(F.cspProfile);
		return this;
	}
	/** 库存数        **/
	public CsPresent cspCount(Integer cspCount){
		this.cspCount = cspCount;
		this.setSeted(F.cspCount);
		return this;
	}
	/** 修改时间 [非空]       **/
	public CsPresent cspUpdateTime(Date cspUpdateTime){
		this.cspUpdateTime = cspUpdateTime;
		this.setSeted(F.cspUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public CsPresent cspAddTime(Date cspAddTime){
		this.cspAddTime = cspAddTime;
		this.setSeted(F.cspAddTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsPresent cspStatus(Short cspStatus){
		this.cspStatus = cspStatus;
		this.setSeted(F.cspStatus);
		return this;
	}
	
	
	//克隆对象
	public CsPresent clone(){
		CsPresent clone = new CsPresent();
		clone.cspHost=this.cspHost;
		clone.cspName=this.cspName;
		clone.cspType=this.cspType;
		clone.cspImage=this.cspImage;
		clone.cspUpdateTime=this.cspUpdateTime;
		clone.cspAddTime=this.cspAddTime;
		clone.cspStatus=this.cspStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取礼品赠品
	 * @param id
	 * @return
	 */
	public static @api CsPresent get(Long id){		
		return getCsPresentById(id);
	}
	/**
	 * 获取所有礼品赠品
	 * @return
	 */
	public static @api List<CsPresent> list(Map params,Integer size){
		return getCsPresentList(params,size);
	}
	/**
	 * 获取礼品赠品分页
	 * @return
	 */
	public static @api Page<CsPresent> page(int page,int size,Map params){
		return getCsPresentPage(page, size , params);
	}
	/**
	 * 根据查询条件取礼品赠品
	 * @param params
	 * @return
	 */
	public static @api CsPresent Get(Map params){
		return getCsPresent(params);
	}
	/**
	 * 获取礼品赠品数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsPresentCount(params);
	}
	/**
	 * 获取礼品赠品数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsPresentEval(eval,params);
	}
	
	/**
	 * 根据ID取礼品赠品
	 * @param id
	 * @return
	 */
	public static @api CsPresent getCsPresentById(Long id){		
		CsPresent csPresent = (CsPresent) $.GetRequest("CsPresent$"+id);
		if(csPresent!=null)
			return csPresent;
		com.ccclubs.service.admin.ICsPresentService csPresentService = $.GetSpringBean("csPresentService");		
		return csPresentService.getCsPresentById(id);
	}
	
	
	/**
	 * 根据ID取礼品赠品的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsPresent.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsPresent csPresent = get(id);
		if(csPresent!=null){
			String strValue = csPresent.getCspName$();
			if(!"CspName".equals("CspName"))
				strValue+="("+csPresent.getCspName$()+")";
			MemCache.setValue(CsPresent.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCspName$();
		if(!"CspName".equals("CspName"))
			keyValue+="("+this.getCspName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有礼品赠品
	 * @return
	 */
	public static @api List<CsPresent> getCsPresentList(Map params,Integer size){
		com.ccclubs.service.admin.ICsPresentService csPresentService = $.GetSpringBean("csPresentService");
		return csPresentService.getCsPresentList(params, size);
	}
	
	/**
	 * 获取礼品赠品分页
	 * @return
	 */
	public static @api Page<CsPresent> getCsPresentPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsPresentService csPresentService = $.GetSpringBean("csPresentService");
		return csPresentService.getCsPresentPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取礼品赠品
	 * @param params
	 * @return
	 */
	public static @api CsPresent getCsPresent(Map params){
		com.ccclubs.service.admin.ICsPresentService csPresentService = $.GetSpringBean("csPresentService");
		return csPresentService.getCsPresent(params);
	}
	
	/**
	 * 获取礼品赠品数
	 * @return
	 */
	public static @api Long getCsPresentCount(Map params){
		com.ccclubs.service.admin.ICsPresentService csPresentService = $.GetSpringBean("csPresentService");
		return csPresentService.getCsPresentCount(params);
	}
		
		
	/**
	 * 获取礼品赠品自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsPresentEval(String eval,Map params){
		com.ccclubs.service.admin.ICsPresentService csPresentService = $.GetSpringBean("csPresentService");
		return csPresentService.getCsPresentEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsPresent(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsPresentService csPresentService = $.GetSpringBean("csPresentService");
		csPresentService.updateCsPresentByConfirm(set, where);
	}
	
	
	/**
	 * 保存礼品赠品对象
	 * @param params
	 * @return
	 */
	public CsPresent save(){
		com.ccclubs.service.admin.ICsPresentService csPresentService = $.GetSpringBean("csPresentService");
		if(this.getCspId()!=null)
			csPresentService.updateCsPresent(this);
		else
			return csPresentService.saveCsPresent(this);
		return this;
	}
	
	
	/**
	 * 更新礼品赠品对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsPresentService csPresentService = $.GetSpringBean("csPresentService");
		csPresentService.updateCsPresent$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsPresentService csPresentService = $.GetSpringBean("csPresentService");
		if($.equals($.config("logic_delete"),"true"))
			csPresentService.removeCsPresentById(this.getCspId());
		else
			csPresentService.deleteCsPresentById(this.getCspId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsPresentService csPresentService = $.GetSpringBean("csPresentService");
		return csPresentService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCspId(){
		return this.cspId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCspId$(){
		String strValue="";
		 strValue=$.str(this.getCspId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCspId(Long cspId){
		this.cspId = cspId;
		this.setSeted(F.cspId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCspHost(){
		return this.cspHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCspHost$(){
		String strValue="";
		if(this.getCspHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCspHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCspHost(Long cspHost){
		this.cspHost = cspHost;
		this.setSeted(F.cspHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cspHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCspHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCspHost()!=null){
 			srvHost = SrvHost.get(this.getCspHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCspHost(), srvHost);
	 	return srvHost;
	}
	/*******************************礼品名称**********************************/	
	/**
	* 礼品名称 [非空]      
	**/
	public String getCspName(){
		return this.cspName;
	}
	/**
	* 获取礼品名称格式化(toString)
	**/
	public String getCspName$(){
		String strValue="";
		 strValue=$.str(this.getCspName());
	 	 return strValue;
	}
	/**
	* 礼品名称 [非空]      
	**/
	public void setCspName(String cspName){
		this.cspName = cspName;
		this.setSeted(F.cspName);
	}
	/*******************************礼品分类**********************************/	
	/**
	* 礼品分类 [非空] [SrvProperty]  present_type:礼品赠品分类    
	**/
	public Long getCspType(){
		return this.cspType;
	}
	/**
	* 获取礼品分类格式化(toString)
	**/
	public String getCspType$(){
		String strValue="";
		if(this.getCspType()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getCspType()));
		}			
	 	 return strValue;
	}
	/**
	* 礼品分类 [非空] [SrvProperty]  present_type:礼品赠品分类    
	**/
	public void setCspType(Long cspType){
		this.cspType = cspType;
		this.setSeted(F.cspType);
	}
	/**
	* 获取关联对象[系统属性]
	**/	 			
 	public SrvProperty get$cspType(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getCspType());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getCspType()!=null){
 			srvProperty = SrvProperty.get(this.getCspType());
 		}
 		$.SetRequest("SrvProperty$"+this.getCspType(), srvProperty);
	 	return srvProperty;
	}
	/*******************************主图**********************************/	
	/**
	* 主图 [非空]      
	**/
	public String getCspImage(){
		return this.cspImage;
	}
	/**
	* 获取主图格式化(toString)
	**/
	public String getCspImage$(){
		String strValue="";
		 strValue=$.str(this.getCspImage());
	 	 return strValue;
	}
	/**
	* 主图 [非空]      
	**/
	public void setCspImage(String cspImage){
		this.cspImage = cspImage;
		this.setSeted(F.cspImage);
	}
	/*******************************图列表**********************************/	
	/**
	* 图列表       
	**/
	public String getCspImages(){
		return this.cspImages;
	}
	/**
	* 获取图列表格式化(toString)
	**/
	public String getCspImages$(){
		String strValue="";
		 strValue=$.str(this.getCspImages());
	 	 return strValue;
	}
	/**
	* 图列表       
	**/
	public void setCspImages(String cspImages){
		this.cspImages = cspImages;
		this.setSeted(F.cspImages);
	}
	/*******************************详细信息**********************************/	
	/**
	* 详细信息       
	**/
	public String getCspProfile(){
		return this.cspProfile;
	}
	/**
	* 获取详细信息格式化(toString)
	**/
	public String getCspProfile$(){
		String strValue="";
		 strValue=$.str(this.getCspProfile());
	 	 return strValue;
	}
	/**
	* 详细信息       
	**/
	public void setCspProfile(String cspProfile){
		this.cspProfile = cspProfile;
		this.setSeted(F.cspProfile);
	}
	/*******************************库存数**********************************/	
	/**
	* 库存数       
	**/
	public Integer getCspCount(){
		return this.cspCount;
	}
	/**
	* 获取库存数格式化(toString)
	**/
	public String getCspCount$(){
		String strValue="";
		 strValue=$.str(this.getCspCount());
	 	 return strValue;
	}
	/**
	* 库存数       
	**/
	public void setCspCount(Integer cspCount){
		this.cspCount = cspCount;
		this.setSeted(F.cspCount);
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getCspUpdateTime(){
		return this.cspUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getCspUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCspUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setCspUpdateTime(Date cspUpdateTime){
		this.cspUpdateTime = cspUpdateTime;
		this.setSeted(F.cspUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getCspAddTime(){
		return this.cspAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getCspAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCspAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setCspAddTime(Date cspAddTime){
		this.cspAddTime = cspAddTime;
		this.setSeted(F.cspAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCspStatus(){
		return this.cspStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCspStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCspStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCspStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCspStatus(Short cspStatus){
		this.cspStatus = cspStatus;
		this.setSeted(F.cspStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsPresent.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsPresent.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsPresent.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsPresent.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsPresent.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsPresent.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsPresent.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsPresent.this);
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
		public M cspId(Object cspId){this.put("cspId", cspId);return this;};
	 	/** and csp_id is null */
 		public M cspIdNull(){if(this.get("cspIdNot")==null)this.put("cspIdNot", "");this.put("cspId", null);return this;};
 		/** not .... */
 		public M cspIdNot(){this.put("cspIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M cspHost(Object cspHost){this.put("cspHost", cspHost);return this;};
	 	/** and csp_host is null */
 		public M cspHostNull(){if(this.get("cspHostNot")==null)this.put("cspHostNot", "");this.put("cspHost", null);return this;};
 		/** not .... */
 		public M cspHostNot(){this.put("cspHostNot", "not");return this;};
		/** 礼品名称 [非空]       **/
		public M cspName(Object cspName){this.put("cspName", cspName);return this;};
	 	/** and csp_name is null */
 		public M cspNameNull(){if(this.get("cspNameNot")==null)this.put("cspNameNot", "");this.put("cspName", null);return this;};
 		/** not .... */
 		public M cspNameNot(){this.put("cspNameNot", "not");return this;};
		/** 礼品分类 [非空] [SrvProperty]  present_type:礼品赠品分类     **/
		public M cspType(Object cspType){this.put("cspType", cspType);return this;};
	 	/** and csp_type is null */
 		public M cspTypeNull(){if(this.get("cspTypeNot")==null)this.put("cspTypeNot", "");this.put("cspType", null);return this;};
 		/** not .... */
 		public M cspTypeNot(){this.put("cspTypeNot", "not");return this;};
		public M cspType$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("cspType$on", value);
			return this;
		};	
		/** 主图 [非空]       **/
		public M cspImage(Object cspImage){this.put("cspImage", cspImage);return this;};
	 	/** and csp_image is null */
 		public M cspImageNull(){if(this.get("cspImageNot")==null)this.put("cspImageNot", "");this.put("cspImage", null);return this;};
 		/** not .... */
 		public M cspImageNot(){this.put("cspImageNot", "not");return this;};
		/** 图列表        **/
		public M cspImages(Object cspImages){this.put("cspImages", cspImages);return this;};
	 	/** and csp_images is null */
 		public M cspImagesNull(){if(this.get("cspImagesNot")==null)this.put("cspImagesNot", "");this.put("cspImages", null);return this;};
 		/** not .... */
 		public M cspImagesNot(){this.put("cspImagesNot", "not");return this;};
		/** 详细信息        **/
		public M cspProfile(Object cspProfile){this.put("cspProfile", cspProfile);return this;};
	 	/** and csp_profile is null */
 		public M cspProfileNull(){if(this.get("cspProfileNot")==null)this.put("cspProfileNot", "");this.put("cspProfile", null);return this;};
 		/** not .... */
 		public M cspProfileNot(){this.put("cspProfileNot", "not");return this;};
		/** 库存数        **/
		public M cspCount(Object cspCount){this.put("cspCount", cspCount);return this;};
	 	/** and csp_count is null */
 		public M cspCountNull(){if(this.get("cspCountNot")==null)this.put("cspCountNot", "");this.put("cspCount", null);return this;};
 		/** not .... */
 		public M cspCountNot(){this.put("cspCountNot", "not");return this;};
		/** and csp_count >= ? */
		public M cspCountMin(Object min){this.put("cspCountMin", min);return this;};
		/** and csp_count <= ? */
		public M cspCountMax(Object max){this.put("cspCountMax", max);return this;};
		/** 修改时间 [非空]       **/
		public M cspUpdateTime(Object cspUpdateTime){this.put("cspUpdateTime", cspUpdateTime);return this;};
	 	/** and csp_update_time is null */
 		public M cspUpdateTimeNull(){if(this.get("cspUpdateTimeNot")==null)this.put("cspUpdateTimeNot", "");this.put("cspUpdateTime", null);return this;};
 		/** not .... */
 		public M cspUpdateTimeNot(){this.put("cspUpdateTimeNot", "not");return this;};
 		/** and csp_update_time >= ? */
 		public M cspUpdateTimeStart(Object start){this.put("cspUpdateTimeStart", start);return this;};			
 		/** and csp_update_time <= ? */
 		public M cspUpdateTimeEnd(Object end){this.put("cspUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M cspAddTime(Object cspAddTime){this.put("cspAddTime", cspAddTime);return this;};
	 	/** and csp_add_time is null */
 		public M cspAddTimeNull(){if(this.get("cspAddTimeNot")==null)this.put("cspAddTimeNot", "");this.put("cspAddTime", null);return this;};
 		/** not .... */
 		public M cspAddTimeNot(){this.put("cspAddTimeNot", "not");return this;};
 		/** and csp_add_time >= ? */
 		public M cspAddTimeStart(Object start){this.put("cspAddTimeStart", start);return this;};			
 		/** and csp_add_time <= ? */
 		public M cspAddTimeEnd(Object end){this.put("cspAddTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M cspStatus(Object cspStatus){this.put("cspStatus", cspStatus);return this;};
	 	/** and csp_status is null */
 		public M cspStatusNull(){if(this.get("cspStatusNot")==null)this.put("cspStatusNot", "");this.put("cspStatus", null);return this;};
 		/** not .... */
 		public M cspStatusNot(){this.put("cspStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有礼品赠品 **/
		public @api List<CsPresent> list(Integer size){
			return getCsPresentList(this,size);
		}
		/** 获取礼品赠品分页 **/
		public @api Page<CsPresent> page(int page,int size){
			return getCsPresentPage(page, size , this);
		}
		/** 根据查询条件取礼品赠品 **/
		public @api CsPresent get(){
			return getCsPresent(this);
		}
		/** 获取礼品赠品数 **/
		public @api Long count(){
			return getCsPresentCount(this);
		}
		/** 获取礼品赠品表达式 **/
		public @api <T> T eval(String strEval){
			return getCsPresentEval(strEval,this);
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
			updateCsPresent(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cspId="cspId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cspHost="cspHost";
		/** 礼品名称 [非空]       **/
		public final static @type(String.class) @like String cspName="cspName";
		/** 礼品分类 [非空] [SrvProperty]  present_type:礼品赠品分类     **/
		public final static @type(Long.class)  String cspType="cspType";
		/** 主图 [非空]       **/
		public final static @type(String.class)  String cspImage="cspImage";
		/** 图列表        **/
		public final static @type(String.class)  String cspImages="cspImages";
		/** 详细信息        **/
		public final static @type(String.class) @like String cspProfile="cspProfile";
		/** 库存数        **/
		public final static @type(Integer.class)  String cspCount="cspCount";
		/** and csp_count >= ? */
		public final static @type(Integer.class) String cspCountMin="cspCountMin";
		/** and csp_count <= ? */
		public final static @type(Integer.class) String cspCountMax="cspCountMax";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String cspUpdateTime="cspUpdateTime";
	 	/** and csp_update_time >= ? */
 		public final static @type(Date.class) String cspUpdateTimeStart="cspUpdateTimeStart";
 		/** and csp_update_time <= ? */
 		public final static @type(Date.class) String cspUpdateTimeEnd="cspUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String cspAddTime="cspAddTime";
	 	/** and csp_add_time >= ? */
 		public final static @type(Date.class) String cspAddTimeStart="cspAddTimeStart";
 		/** and csp_add_time <= ? */
 		public final static @type(Date.class) String cspAddTimeEnd="cspAddTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String cspStatus="cspStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cspId="csp_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cspHost="csp_host";
		/** 礼品名称 [非空]       **/
		public final static String cspName="csp_name";
		/** 礼品分类 [非空] [SrvProperty]  present_type:礼品赠品分类     **/
		public final static String cspType="csp_type";
		/** 主图 [非空]       **/
		public final static String cspImage="csp_image";
		/** 图列表        **/
		public final static String cspImages="csp_images";
		/** 详细信息        **/
		public final static String cspProfile="csp_profile";
		/** 库存数        **/
		public final static String cspCount="csp_count";
		/** 修改时间 [非空]       **/
		public final static String cspUpdateTime="csp_update_time";
		/** 添加时间 [非空]       **/
		public final static String cspAddTime="csp_add_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String cspStatus="csp_status";
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
				$.Set(name,CsPresent.getCsPresent(params));
			else
				$.Set(name,CsPresent.getCsPresentList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取礼品赠品数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsPresent) $.GetRequest("CsPresent$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsPresent.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsPresent.getCsPresent(params);
			else
				value = CsPresent.getCsPresentList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsPresent.Get($.add(CsPresent.F.cspId,param));
		else if(!$.empty(param.toString()))
			value = CsPresent.get(Long.valueOf(param.toString()));
		$.SetRequest("CsPresent$"+param.hashCode(), value);
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
	public void mergeSet(CsPresent old){
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