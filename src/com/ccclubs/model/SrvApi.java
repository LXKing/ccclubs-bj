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

@namespace("configurator/api")
public @caption("API接口") @table("srv_api") class SrvApi implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("sa_id")   @primary  @note("  ") Long saId;// 主键 非空     
	private @caption("API名称") @column("sa_name")    @note("  中文描述") String saName;// 非空  中文描述   
	private @caption("名称空间") @column("sa_namespace")    @note("  格式如:com.xxxx.xxx") String saNamespace;// 非空  格式如:com.xxxx.xxx   
	private @caption("接口分类") @column("sa_type")    @relate("$saType") @RelateClass(SrvProperty.class)  @note(" api_type:API接口分类  ") Long saType;// 非空 api_type:API接口分类     
 	private SrvProperty $saType;//关联对象[系统属性]
	private @caption("是否开放") @column("sa_open")    @note(" 1:开放 0:非开放  ") Short saOpen;// 非空 1:开放 0:非开放     
	private @caption("接口说明") @column("sa_descript")    @note("  ") String saDescript;//     
	private @caption("参数结构") @column("sa_params")  @hidden   @note("  ") String saParams;//     
	private @caption("需要用户授权") @column("sa_oauth")    @note(" 0:无需授权 1:需要授权  ") Short saOauth;// 非空 0:无需授权 1:需要授权     
	private @caption("返回结构设计") @column("sa_return_struct")  @hidden   @note("  ") String saReturnStruct;// 非空     
	private @caption("脚本代码") @column("sa_java_code")  @hidden   @note("  脚本代码为java代码,参数结构中定义的变量名可直接使用,有错误返回错误代码，没错误无需返回") String saJavaCode;// 非空  脚本代码为java代码,参数结构中定义的变量名可直接使用,有错误返回错误代码，没错误无需返回   
	private @caption("返回格式化") @column("sa_return_format")  @hidden   @note("  模板代码为velocity模板,可用变量为上面脚本代码中定义的局部变量") String saReturnFormat;// 非空  模板代码为velocity模板,可用变量为上面脚本代码中定义的局部变量   
	private @caption("错误代码") @column("sa_error")  @hidden   @note("  ") String saError;//     
	private @caption("添加人") @column("sa_editor")    @relate("$saEditor") @RelateClass(SrvUser.class)  @note("  ") Long saEditor;// 非空    ${basePath}${proname}/permissions/user_tree.do 
 	private SrvUser $saEditor;//关联对象[用户]
	private @caption("修改时间") @column("sa_update_time")    @note("  ") Date saUpdateTime;// 非空     
	private @caption("添加时间") @column("sa_add_time")    @note("  ") Date saAddTime;// 非空     
	private @caption("状态") @column("sa_status")    @note(" 0:测试中 1:正常 2:禁用  ") Short saStatus;// 非空 0:测试中 1:正常 2:禁用     
	
	//默认构造函数
	public SrvApi(){
	
	}
	
	//主键构造函数
	public SrvApi(Long id){
		this.saId = id;
	}
	
	/**所有字段构造函数 SrvApi(saName,saNamespace,saType,saOpen,saDescript,saParams,saOauth,saReturnStruct,saJavaCode,saReturnFormat,saError,saEditor,saUpdateTime,saAddTime,saStatus)
	 SrvApi(
	 	$.getString("saName")//API名称 [非空]
	 	,$.getString("saNamespace")//名称空间 [非空]
	 	,$.getLong("saType")//接口分类 [非空]
	 	,$.getShort("saOpen")//是否开放 [非空]
	 	,$.getString("saDescript")//接口说明
	 	,$.getString("saParams")//参数结构
	 	,$.getShort("saOauth")//需要用户授权 [非空]
	 	,$.getString("saReturnStruct")//返回结构设计 [非空]
	 	,$.getString("saJavaCode")//脚本代码 [非空]
	 	,$.getString("saReturnFormat")//返回格式化 [非空]
	 	,$.getString("saError")//错误代码
	 	,$.getLong("saEditor")//添加人 [非空]
	 	,$.getDate("saUpdateTime")//修改时间 [非空]
	 	,$.getDate("saAddTime")//添加时间 [非空]
	 	,$.getShort("saStatus")//状态 [非空]
	 )
	**/
	public SrvApi(String saName,String saNamespace,Long saType,Short saOpen,String saDescript,String saParams,Short saOauth,String saReturnStruct,String saJavaCode,String saReturnFormat,String saError,Long saEditor,Date saUpdateTime,Date saAddTime,Short saStatus){
		this.saName=saName;
		this.saNamespace=saNamespace;
		this.saType=saType;
		this.saOpen=saOpen;
		this.saDescript=saDescript;
		this.saParams=saParams;
		this.saOauth=saOauth;
		this.saReturnStruct=saReturnStruct;
		this.saJavaCode=saJavaCode;
		this.saReturnFormat=saReturnFormat;
		this.saError=saError;
		this.saEditor=saEditor;
		this.saUpdateTime=saUpdateTime;
		this.saAddTime=saAddTime;
		this.saStatus=saStatus;
	}
	
	//设置非空字段
	public SrvApi setNotNull(String saName,String saNamespace,Long saType,Short saOpen,Short saOauth,String saReturnStruct,String saJavaCode,String saReturnFormat,Long saEditor,Date saUpdateTime,Date saAddTime,Short saStatus){
		this.saName=saName;
		this.saNamespace=saNamespace;
		this.saType=saType;
		this.saOpen=saOpen;
		this.saOauth=saOauth;
		this.saReturnStruct=saReturnStruct;
		this.saJavaCode=saJavaCode;
		this.saReturnFormat=saReturnFormat;
		this.saEditor=saEditor;
		this.saUpdateTime=saUpdateTime;
		this.saAddTime=saAddTime;
		this.saStatus=saStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public SrvApi saId(Long saId){
		this.saId = saId;
		this.setSeted(F.saId);
		return this;
	}
	/** API名称 [非空]    中文描述   **/
	public SrvApi saName(String saName){
		this.saName = saName;
		this.setSeted(F.saName);
		return this;
	}
	/** 名称空间 [非空]    格式如:com.xxxx.xxx   **/
	public SrvApi saNamespace(String saNamespace){
		this.saNamespace = saNamespace;
		this.setSeted(F.saNamespace);
		return this;
	}
	/** 接口分类 [非空] [SrvProperty]  api_type:API接口分类     **/
	public SrvApi saType(Long saType){
		this.saType = saType;
		this.setSeted(F.saType);
		return this;
	}
	/** 是否开放 [非空]   1:开放 0:非开放     **/
	public SrvApi saOpen(Short saOpen){
		this.saOpen = saOpen;
		this.setSeted(F.saOpen);
		return this;
	}
	/** 接口说明        **/
	public SrvApi saDescript(String saDescript){
		this.saDescript = saDescript;
		this.setSeted(F.saDescript);
		return this;
	}
	/** 参数结构        **/
	public SrvApi saParams(String saParams){
		this.saParams = saParams;
		this.setSeted(F.saParams);
		return this;
	}
	/** 需要用户授权 [非空]   0:无需授权 1:需要授权     **/
	public SrvApi saOauth(Short saOauth){
		this.saOauth = saOauth;
		this.setSeted(F.saOauth);
		return this;
	}
	/** 返回结构设计 [非空]       **/
	public SrvApi saReturnStruct(String saReturnStruct){
		this.saReturnStruct = saReturnStruct;
		this.setSeted(F.saReturnStruct);
		return this;
	}
	/** 脚本代码 [非空]    脚本代码为java代码,参数结构中定义的变量名可直接使用,有错误返回错误代码，没错误无需返回   **/
	public SrvApi saJavaCode(String saJavaCode){
		this.saJavaCode = saJavaCode;
		this.setSeted(F.saJavaCode);
		return this;
	}
	/** 返回格式化 [非空]    模板代码为velocity模板,可用变量为上面脚本代码中定义的局部变量   **/
	public SrvApi saReturnFormat(String saReturnFormat){
		this.saReturnFormat = saReturnFormat;
		this.setSeted(F.saReturnFormat);
		return this;
	}
	/** 错误代码        **/
	public SrvApi saError(String saError){
		this.saError = saError;
		this.setSeted(F.saError);
		return this;
	}
	/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
	public SrvApi saEditor(Long saEditor){
		this.saEditor = saEditor;
		this.setSeted(F.saEditor);
		return this;
	}
	/** 修改时间 [非空]       **/
	public SrvApi saUpdateTime(Date saUpdateTime){
		this.saUpdateTime = saUpdateTime;
		this.setSeted(F.saUpdateTime);
		return this;
	}
	/** 添加时间 [非空]       **/
	public SrvApi saAddTime(Date saAddTime){
		this.saAddTime = saAddTime;
		this.setSeted(F.saAddTime);
		return this;
	}
	/** 状态 [非空]   0:测试中 1:正常 2:禁用     **/
	public SrvApi saStatus(Short saStatus){
		this.saStatus = saStatus;
		this.setSeted(F.saStatus);
		return this;
	}
	
	
	//克隆对象
	public SrvApi clone(){
		SrvApi clone = new SrvApi();
		clone.saName=this.saName;
		clone.saNamespace=this.saNamespace;
		clone.saType=this.saType;
		clone.saOpen=this.saOpen;
		clone.saOauth=this.saOauth;
		clone.saReturnStruct=this.saReturnStruct;
		clone.saJavaCode=this.saJavaCode;
		clone.saReturnFormat=this.saReturnFormat;
		clone.saEditor=this.saEditor;
		clone.saUpdateTime=this.saUpdateTime;
		clone.saAddTime=this.saAddTime;
		clone.saStatus=this.saStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取API接口
	 * @param id
	 * @return
	 */
	public static @api SrvApi get(Long id){		
		return getSrvApiById(id);
	}
	/**
	 * 获取所有API接口
	 * @return
	 */
	public static @api List<SrvApi> list(Map params,Integer size){
		return getSrvApiList(params,size);
	}
	/**
	 * 获取API接口分页
	 * @return
	 */
	public static @api Page<SrvApi> page(int page,int size,Map params){
		return getSrvApiPage(page, size , params);
	}
	/**
	 * 根据查询条件取API接口
	 * @param params
	 * @return
	 */
	public static @api SrvApi Get(Map params){
		return getSrvApi(params);
	}
	/**
	 * 获取API接口数
	 * @return
	 */
	public static @api Long count(Map params){
		return getSrvApiCount(params);
	}
	/**
	 * 获取API接口数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getSrvApiEval(eval,params);
	}
	
	/**
	 * 根据ID取API接口
	 * @param id
	 * @return
	 */
	public static @api SrvApi getSrvApiById(Long id){		
		SrvApi srvApi = (SrvApi) $.GetRequest("SrvApi$"+id);
		if(srvApi!=null)
			return srvApi;
		com.ccclubs.service.admin.ISrvApiService srvApiService = $.GetSpringBean("srvApiService");		
		return srvApiService.getSrvApiById(id);
	}
	
	
	/**
	 * 根据ID取API接口的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(SrvApi.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		SrvApi srvApi = get(id);
		if(srvApi!=null){
			String strValue = srvApi.getSaName$();
			if(!"SaName".equals("SaName"))
				strValue+="("+srvApi.getSaName$()+")";
			if(!"SaName".equals("SaNamespace"))
				strValue+="("+srvApi.getSaNamespace$()+")";
			MemCache.setValue(SrvApi.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getSaName$();
		if(!"SaName".equals("SaName"))
			keyValue+="("+this.getSaName$()+")";
		if(!"SaName".equals("SaNamespace"))
			keyValue+="("+this.getSaNamespace$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有API接口
	 * @return
	 */
	public static @api List<SrvApi> getSrvApiList(Map params,Integer size){
		com.ccclubs.service.admin.ISrvApiService srvApiService = $.GetSpringBean("srvApiService");
		return srvApiService.getSrvApiList(params, size);
	}
	
	/**
	 * 获取API接口分页
	 * @return
	 */
	public static @api Page<SrvApi> getSrvApiPage(int page,int size,Map params){
		com.ccclubs.service.admin.ISrvApiService srvApiService = $.GetSpringBean("srvApiService");
		return srvApiService.getSrvApiPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取API接口
	 * @param params
	 * @return
	 */
	public static @api SrvApi getSrvApi(Map params){
		com.ccclubs.service.admin.ISrvApiService srvApiService = $.GetSpringBean("srvApiService");
		return srvApiService.getSrvApi(params);
	}
	
	/**
	 * 获取API接口数
	 * @return
	 */
	public static @api Long getSrvApiCount(Map params){
		com.ccclubs.service.admin.ISrvApiService srvApiService = $.GetSpringBean("srvApiService");
		return srvApiService.getSrvApiCount(params);
	}
		
		
	/**
	 * 获取API接口自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getSrvApiEval(String eval,Map params){
		com.ccclubs.service.admin.ISrvApiService srvApiService = $.GetSpringBean("srvApiService");
		return srvApiService.getSrvApiEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateSrvApi(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ISrvApiService srvApiService = $.GetSpringBean("srvApiService");
		srvApiService.updateSrvApiByConfirm(set, where);
	}
	
	
	/**
	 * 保存API接口对象
	 * @param params
	 * @return
	 */
	public SrvApi save(){
		com.ccclubs.service.admin.ISrvApiService srvApiService = $.GetSpringBean("srvApiService");
		if(this.getSaId()!=null)
			srvApiService.updateSrvApi(this);
		else
			return srvApiService.saveSrvApi(this);
		return this;
	}
	
	
	/**
	 * 更新API接口对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ISrvApiService srvApiService = $.GetSpringBean("srvApiService");
		srvApiService.updateSrvApi$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ISrvApiService srvApiService = $.GetSpringBean("srvApiService");
		if($.equals($.config("logic_delete"),"true"))
			srvApiService.removeSrvApiById(this.getSaId());
		else
			srvApiService.deleteSrvApiById(this.getSaId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ISrvApiService srvApiService = $.GetSpringBean("srvApiService");
		return srvApiService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getSaId(){
		return this.saId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getSaId$(){
		String strValue="";
		 strValue=$.str(this.getSaId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setSaId(Long saId){
		this.saId = saId;
		this.setSeted(F.saId);
	}
	/*******************************API名称**********************************/	
	/**
	* API名称 [非空]    中文描述  
	**/
	public String getSaName(){
		return this.saName;
	}
	/**
	* 获取API名称格式化(toString)
	**/
	public String getSaName$(){
		String strValue="";
		 strValue=$.str(this.getSaName());
	 	 return strValue;
	}
	/**
	* API名称 [非空]    中文描述  
	**/
	public void setSaName(String saName){
		this.saName = saName;
		this.setSeted(F.saName);
	}
	/*******************************名称空间**********************************/	
	/**
	* 名称空间 [非空]    格式如:com.xxxx.xxx  
	**/
	public String getSaNamespace(){
		return this.saNamespace;
	}
	/**
	* 获取名称空间格式化(toString)
	**/
	public String getSaNamespace$(){
		String strValue="";
		 strValue=$.str(this.getSaNamespace());
	 	 return strValue;
	}
	/**
	* 名称空间 [非空]    格式如:com.xxxx.xxx  
	**/
	public void setSaNamespace(String saNamespace){
		this.saNamespace = saNamespace;
		this.setSeted(F.saNamespace);
	}
	/*******************************接口分类**********************************/	
	/**
	* 接口分类 [非空] [SrvProperty]  api_type:API接口分类    
	**/
	public Long getSaType(){
		return this.saType;
	}
	/**
	* 获取接口分类格式化(toString)
	**/
	public String getSaType$(){
		String strValue="";
		if(this.getSaType()!=null){
				strValue+=$.str(SrvProperty.getKeyValue(this.getSaType()));
		}			
	 	 return strValue;
	}
	/**
	* 接口分类 [非空] [SrvProperty]  api_type:API接口分类    
	**/
	public void setSaType(Long saType){
		this.saType = saType;
		this.setSeted(F.saType);
	}
	/**
	* 获取关联对象[系统属性]
	**/	 			
 	public SrvProperty get$saType(){
 		com.ccclubs.model.SrvProperty srvProperty = (com.ccclubs.model.SrvProperty) $.GetRequest("SrvProperty$"+this.getSaType());
 		if(srvProperty!=null)
			return srvProperty;
		if(this.getSaType()!=null){
 			srvProperty = SrvProperty.get(this.getSaType());
 		}
 		$.SetRequest("SrvProperty$"+this.getSaType(), srvProperty);
	 	return srvProperty;
	}
	/*******************************是否开放**********************************/	
	/**
	* 是否开放 [非空]   1:开放 0:非开放    
	**/
	public Short getSaOpen(){
		return this.saOpen;
	}
	/**
	* 获取是否开放格式化(toString)
	**/
	public String getSaOpen$(){
		String strValue="";
		 if($.equals($.str(this.getSaOpen()),"1"))
			strValue=$.str("开放");		 
		 if($.equals($.str(this.getSaOpen()),"0"))
			strValue=$.str("非开放");		 
	 	 return strValue;
	}
	/**
	* 是否开放 [非空]   1:开放 0:非开放    
	**/
	public void setSaOpen(Short saOpen){
		this.saOpen = saOpen;
		this.setSeted(F.saOpen);
	}
	/*******************************接口说明**********************************/	
	/**
	* 接口说明       
	**/
	public String getSaDescript(){
		return this.saDescript;
	}
	/**
	* 获取接口说明格式化(toString)
	**/
	public String getSaDescript$(){
		String strValue="";
		 strValue=$.str(this.getSaDescript());
	 	 return strValue;
	}
	/**
	* 接口说明       
	**/
	public void setSaDescript(String saDescript){
		this.saDescript = saDescript;
		this.setSeted(F.saDescript);
	}
	/*******************************参数结构**********************************/	
	/**
	* 参数结构       
	**/
	public String getSaParams(){
		return this.saParams;
	}
	/**
	* 获取参数结构格式化(toString)
	**/
	public String getSaParams$(){
		String strValue="";
		 strValue=$.str(this.getSaParams());
	 	 return strValue;
	}
	/**
	* 参数结构       
	**/
	public void setSaParams(String saParams){
		this.saParams = saParams;
		this.setSeted(F.saParams);
	}
	/*******************************需要用户授权**********************************/	
	/**
	* 需要用户授权 [非空]   0:无需授权 1:需要授权    
	**/
	public Short getSaOauth(){
		return this.saOauth;
	}
	/**
	* 获取需要用户授权格式化(toString)
	**/
	public String getSaOauth$(){
		String strValue="";
		 if($.equals($.str(this.getSaOauth()),"0"))
			strValue=$.str("无需授权");		 
		 if($.equals($.str(this.getSaOauth()),"1"))
			strValue=$.str("需要授权");		 
	 	 return strValue;
	}
	/**
	* 需要用户授权 [非空]   0:无需授权 1:需要授权    
	**/
	public void setSaOauth(Short saOauth){
		this.saOauth = saOauth;
		this.setSeted(F.saOauth);
	}
	/*******************************返回结构设计**********************************/	
	/**
	* 返回结构设计 [非空]      
	**/
	public String getSaReturnStruct(){
		return this.saReturnStruct;
	}
	/**
	* 获取返回结构设计格式化(toString)
	**/
	public String getSaReturnStruct$(){
		String strValue="";
		 strValue=$.str(this.getSaReturnStruct());
	 	 return strValue;
	}
	/**
	* 返回结构设计 [非空]      
	**/
	public void setSaReturnStruct(String saReturnStruct){
		this.saReturnStruct = saReturnStruct;
		this.setSeted(F.saReturnStruct);
	}
	/*******************************脚本代码**********************************/	
	/**
	* 脚本代码 [非空]    脚本代码为java代码,参数结构中定义的变量名可直接使用,有错误返回错误代码，没错误无需返回  
	**/
	public String getSaJavaCode(){
		return this.saJavaCode;
	}
	/**
	* 获取脚本代码格式化(toString)
	**/
	public String getSaJavaCode$(){
		String strValue="";
		 strValue=$.str(this.getSaJavaCode());
	 	 return strValue;
	}
	/**
	* 脚本代码 [非空]    脚本代码为java代码,参数结构中定义的变量名可直接使用,有错误返回错误代码，没错误无需返回  
	**/
	public void setSaJavaCode(String saJavaCode){
		this.saJavaCode = saJavaCode;
		this.setSeted(F.saJavaCode);
	}
	/*******************************返回格式化**********************************/	
	/**
	* 返回格式化 [非空]    模板代码为velocity模板,可用变量为上面脚本代码中定义的局部变量  
	**/
	public String getSaReturnFormat(){
		return this.saReturnFormat;
	}
	/**
	* 获取返回格式化格式化(toString)
	**/
	public String getSaReturnFormat$(){
		String strValue="";
		 strValue=$.str(this.getSaReturnFormat());
	 	 return strValue;
	}
	/**
	* 返回格式化 [非空]    模板代码为velocity模板,可用变量为上面脚本代码中定义的局部变量  
	**/
	public void setSaReturnFormat(String saReturnFormat){
		this.saReturnFormat = saReturnFormat;
		this.setSeted(F.saReturnFormat);
	}
	/*******************************错误代码**********************************/	
	/**
	* 错误代码       
	**/
	public String getSaError(){
		return this.saError;
	}
	/**
	* 获取错误代码格式化(toString)
	**/
	public String getSaError$(){
		String strValue="";
		 strValue=$.str(this.getSaError());
	 	 return strValue;
	}
	/**
	* 错误代码       
	**/
	public void setSaError(String saError){
		this.saError = saError;
		this.setSeted(F.saError);
	}
	/*******************************添加人**********************************/	
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public Long getSaEditor(){
		return this.saEditor;
	}
	/**
	* 获取添加人格式化(toString)
	**/
	public String getSaEditor$(){
		String strValue="";
		if(this.getSaEditor()!=null){
				strValue+=$.str(SrvUser.getKeyValue(this.getSaEditor()));
		}			
	 	 return strValue;
	}
	/**
	* 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do
	**/
	public void setSaEditor(Long saEditor){
		this.saEditor = saEditor;
		this.setSeted(F.saEditor);
	}
	/**
	* 获取关联对象[用户]
	**/	 			
 	public SrvUser get$saEditor(){
 		com.ccclubs.model.SrvUser srvUser = (com.ccclubs.model.SrvUser) $.GetRequest("SrvUser$"+this.getSaEditor());
 		if(srvUser!=null)
			return srvUser;
		if(this.getSaEditor()!=null){
 			srvUser = SrvUser.get(this.getSaEditor());
 		}
 		$.SetRequest("SrvUser$"+this.getSaEditor(), srvUser);
	 	return srvUser;
	}
	/*******************************修改时间**********************************/	
	/**
	* 修改时间 [非空]      
	**/
	public Date getSaUpdateTime(){
		return this.saUpdateTime;
	}
	/**
	* 获取修改时间格式化(toString)
	**/
	public String getSaUpdateTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getSaUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 修改时间 [非空]      
	**/
	public void setSaUpdateTime(Date saUpdateTime){
		this.saUpdateTime = saUpdateTime;
		this.setSeted(F.saUpdateTime);
	}
	/*******************************添加时间**********************************/	
	/**
	* 添加时间 [非空]      
	**/
	public Date getSaAddTime(){
		return this.saAddTime;
	}
	/**
	* 获取添加时间格式化(toString)
	**/
	public String getSaAddTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getSaAddTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 添加时间 [非空]      
	**/
	public void setSaAddTime(Date saAddTime){
		this.saAddTime = saAddTime;
		this.setSeted(F.saAddTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   0:测试中 1:正常 2:禁用    
	**/
	public Short getSaStatus(){
		return this.saStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getSaStatus$(){
		String strValue="";
		 if($.equals($.str(this.getSaStatus()),"0"))
			strValue=$.str("测试中");		 
		 if($.equals($.str(this.getSaStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getSaStatus()),"2"))
			strValue=$.str("禁用");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   0:测试中 1:正常 2:禁用    
	**/
	public void setSaStatus(Short saStatus){
		this.saStatus = saStatus;
		this.setSeted(F.saStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${SrvApi.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvApi.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvApi.class.getMethod("get$"+$.forMat(key.toString())).invoke(SrvApi.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${SrvApi.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${SrvApi.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return SrvApi.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(SrvApi.this);
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
		public M saId(Object saId){this.put("saId", saId);return this;};
	 	/** and sa_id is null */
 		public M saIdNull(){if(this.get("saIdNot")==null)this.put("saIdNot", "");this.put("saId", null);return this;};
 		/** not .... */
 		public M saIdNot(){this.put("saIdNot", "not");return this;};
		/** API名称 [非空]    中文描述   **/
		public M saName(Object saName){this.put("saName", saName);return this;};
	 	/** and sa_name is null */
 		public M saNameNull(){if(this.get("saNameNot")==null)this.put("saNameNot", "");this.put("saName", null);return this;};
 		/** not .... */
 		public M saNameNot(){this.put("saNameNot", "not");return this;};
		/** 名称空间 [非空]    格式如:com.xxxx.xxx   **/
		public M saNamespace(Object saNamespace){this.put("saNamespace", saNamespace);return this;};
	 	/** and sa_namespace is null */
 		public M saNamespaceNull(){if(this.get("saNamespaceNot")==null)this.put("saNamespaceNot", "");this.put("saNamespace", null);return this;};
 		/** not .... */
 		public M saNamespaceNot(){this.put("saNamespaceNot", "not");return this;};
		/** 接口分类 [非空] [SrvProperty]  api_type:API接口分类     **/
		public M saType(Object saType){this.put("saType", saType);return this;};
	 	/** and sa_type is null */
 		public M saTypeNull(){if(this.get("saTypeNot")==null)this.put("saTypeNot", "");this.put("saType", null);return this;};
 		/** not .... */
 		public M saTypeNot(){this.put("saTypeNot", "not");return this;};
		public M saType$on(SrvProperty.M value){
			this.put("SrvProperty", value);
			this.put("saType$on", value);
			return this;
		};	
		/** 是否开放 [非空]   1:开放 0:非开放     **/
		public M saOpen(Object saOpen){this.put("saOpen", saOpen);return this;};
	 	/** and sa_open is null */
 		public M saOpenNull(){if(this.get("saOpenNot")==null)this.put("saOpenNot", "");this.put("saOpen", null);return this;};
 		/** not .... */
 		public M saOpenNot(){this.put("saOpenNot", "not");return this;};
		/** 接口说明        **/
		public M saDescript(Object saDescript){this.put("saDescript", saDescript);return this;};
	 	/** and sa_descript is null */
 		public M saDescriptNull(){if(this.get("saDescriptNot")==null)this.put("saDescriptNot", "");this.put("saDescript", null);return this;};
 		/** not .... */
 		public M saDescriptNot(){this.put("saDescriptNot", "not");return this;};
		/** 参数结构        **/
		public M saParams(Object saParams){this.put("saParams", saParams);return this;};
	 	/** and sa_params is null */
 		public M saParamsNull(){if(this.get("saParamsNot")==null)this.put("saParamsNot", "");this.put("saParams", null);return this;};
 		/** not .... */
 		public M saParamsNot(){this.put("saParamsNot", "not");return this;};
		/** 需要用户授权 [非空]   0:无需授权 1:需要授权     **/
		public M saOauth(Object saOauth){this.put("saOauth", saOauth);return this;};
	 	/** and sa_oauth is null */
 		public M saOauthNull(){if(this.get("saOauthNot")==null)this.put("saOauthNot", "");this.put("saOauth", null);return this;};
 		/** not .... */
 		public M saOauthNot(){this.put("saOauthNot", "not");return this;};
		/** 返回结构设计 [非空]       **/
		public M saReturnStruct(Object saReturnStruct){this.put("saReturnStruct", saReturnStruct);return this;};
	 	/** and sa_return_struct is null */
 		public M saReturnStructNull(){if(this.get("saReturnStructNot")==null)this.put("saReturnStructNot", "");this.put("saReturnStruct", null);return this;};
 		/** not .... */
 		public M saReturnStructNot(){this.put("saReturnStructNot", "not");return this;};
		/** 脚本代码 [非空]    脚本代码为java代码,参数结构中定义的变量名可直接使用,有错误返回错误代码，没错误无需返回   **/
		public M saJavaCode(Object saJavaCode){this.put("saJavaCode", saJavaCode);return this;};
	 	/** and sa_java_code is null */
 		public M saJavaCodeNull(){if(this.get("saJavaCodeNot")==null)this.put("saJavaCodeNot", "");this.put("saJavaCode", null);return this;};
 		/** not .... */
 		public M saJavaCodeNot(){this.put("saJavaCodeNot", "not");return this;};
		/** 返回格式化 [非空]    模板代码为velocity模板,可用变量为上面脚本代码中定义的局部变量   **/
		public M saReturnFormat(Object saReturnFormat){this.put("saReturnFormat", saReturnFormat);return this;};
	 	/** and sa_return_format is null */
 		public M saReturnFormatNull(){if(this.get("saReturnFormatNot")==null)this.put("saReturnFormatNot", "");this.put("saReturnFormat", null);return this;};
 		/** not .... */
 		public M saReturnFormatNot(){this.put("saReturnFormatNot", "not");return this;};
		/** 错误代码        **/
		public M saError(Object saError){this.put("saError", saError);return this;};
	 	/** and sa_error is null */
 		public M saErrorNull(){if(this.get("saErrorNot")==null)this.put("saErrorNot", "");this.put("saError", null);return this;};
 		/** not .... */
 		public M saErrorNot(){this.put("saErrorNot", "not");return this;};
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public M saEditor(Object saEditor){this.put("saEditor", saEditor);return this;};
	 	/** and sa_editor is null */
 		public M saEditorNull(){if(this.get("saEditorNot")==null)this.put("saEditorNot", "");this.put("saEditor", null);return this;};
 		/** not .... */
 		public M saEditorNot(){this.put("saEditorNot", "not");return this;};
		/** 修改时间 [非空]       **/
		public M saUpdateTime(Object saUpdateTime){this.put("saUpdateTime", saUpdateTime);return this;};
	 	/** and sa_update_time is null */
 		public M saUpdateTimeNull(){if(this.get("saUpdateTimeNot")==null)this.put("saUpdateTimeNot", "");this.put("saUpdateTime", null);return this;};
 		/** not .... */
 		public M saUpdateTimeNot(){this.put("saUpdateTimeNot", "not");return this;};
 		/** and sa_update_time >= ? */
 		public M saUpdateTimeStart(Object start){this.put("saUpdateTimeStart", start);return this;};			
 		/** and sa_update_time <= ? */
 		public M saUpdateTimeEnd(Object end){this.put("saUpdateTimeEnd", end);return this;};
		/** 添加时间 [非空]       **/
		public M saAddTime(Object saAddTime){this.put("saAddTime", saAddTime);return this;};
	 	/** and sa_add_time is null */
 		public M saAddTimeNull(){if(this.get("saAddTimeNot")==null)this.put("saAddTimeNot", "");this.put("saAddTime", null);return this;};
 		/** not .... */
 		public M saAddTimeNot(){this.put("saAddTimeNot", "not");return this;};
 		/** and sa_add_time >= ? */
 		public M saAddTimeStart(Object start){this.put("saAddTimeStart", start);return this;};			
 		/** and sa_add_time <= ? */
 		public M saAddTimeEnd(Object end){this.put("saAddTimeEnd", end);return this;};
		/** 状态 [非空]   0:测试中 1:正常 2:禁用     **/
		public M saStatus(Object saStatus){this.put("saStatus", saStatus);return this;};
	 	/** and sa_status is null */
 		public M saStatusNull(){if(this.get("saStatusNot")==null)this.put("saStatusNot", "");this.put("saStatus", null);return this;};
 		/** not .... */
 		public M saStatusNot(){this.put("saStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有API接口 **/
		public @api List<SrvApi> list(Integer size){
			return getSrvApiList(this,size);
		}
		/** 获取API接口分页 **/
		public @api Page<SrvApi> page(int page,int size){
			return getSrvApiPage(page, size , this);
		}
		/** 根据查询条件取API接口 **/
		public @api SrvApi get(){
			return getSrvApi(this);
		}
		/** 获取API接口数 **/
		public @api Long count(){
			return getSrvApiCount(this);
		}
		/** 获取API接口表达式 **/
		public @api <T> T eval(String strEval){
			return getSrvApiEval(strEval,this);
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
			updateSrvApi(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String saId="saId";
		/** API名称 [非空]    中文描述   **/
		public final static @type(String.class) @like String saName="saName";
		/** 名称空间 [非空]    格式如:com.xxxx.xxx   **/
		public final static @type(String.class) @like String saNamespace="saNamespace";
		/** 接口分类 [非空] [SrvProperty]  api_type:API接口分类     **/
		public final static @type(Long.class)  String saType="saType";
		/** 是否开放 [非空]   1:开放 0:非开放     **/
		public final static @type(Short.class)  String saOpen="saOpen";
		/** 接口说明        **/
		public final static @type(String.class) @like String saDescript="saDescript";
		/** 参数结构        **/
		public final static @type(String.class) @like String saParams="saParams";
		/** 需要用户授权 [非空]   0:无需授权 1:需要授权     **/
		public final static @type(Short.class)  String saOauth="saOauth";
		/** 返回结构设计 [非空]       **/
		public final static @type(String.class) @like String saReturnStruct="saReturnStruct";
		/** 脚本代码 [非空]    脚本代码为java代码,参数结构中定义的变量名可直接使用,有错误返回错误代码，没错误无需返回   **/
		public final static @type(String.class) @like String saJavaCode="saJavaCode";
		/** 返回格式化 [非空]    模板代码为velocity模板,可用变量为上面脚本代码中定义的局部变量   **/
		public final static @type(String.class) @like String saReturnFormat="saReturnFormat";
		/** 错误代码        **/
		public final static @type(String.class) @like String saError="saError";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static @type(Long.class)  String saEditor="saEditor";
		/** 修改时间 [非空]       **/
		public final static @type(Date.class)  String saUpdateTime="saUpdateTime";
	 	/** and sa_update_time >= ? */
 		public final static @type(Date.class) String saUpdateTimeStart="saUpdateTimeStart";
 		/** and sa_update_time <= ? */
 		public final static @type(Date.class) String saUpdateTimeEnd="saUpdateTimeEnd";
		/** 添加时间 [非空]       **/
		public final static @type(Date.class)  String saAddTime="saAddTime";
	 	/** and sa_add_time >= ? */
 		public final static @type(Date.class) String saAddTimeStart="saAddTimeStart";
 		/** and sa_add_time <= ? */
 		public final static @type(Date.class) String saAddTimeEnd="saAddTimeEnd";
		/** 状态 [非空]   0:测试中 1:正常 2:禁用     **/
		public final static @type(Short.class)  String saStatus="saStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String saId="sa_id";
		/** API名称 [非空]    中文描述   **/
		public final static String saName="sa_name";
		/** 名称空间 [非空]    格式如:com.xxxx.xxx   **/
		public final static String saNamespace="sa_namespace";
		/** 接口分类 [非空] [SrvProperty]  api_type:API接口分类     **/
		public final static String saType="sa_type";
		/** 是否开放 [非空]   1:开放 0:非开放     **/
		public final static String saOpen="sa_open";
		/** 接口说明        **/
		public final static String saDescript="sa_descript";
		/** 参数结构        **/
		public final static String saParams="sa_params";
		/** 需要用户授权 [非空]   0:无需授权 1:需要授权     **/
		public final static String saOauth="sa_oauth";
		/** 返回结构设计 [非空]       **/
		public final static String saReturnStruct="sa_return_struct";
		/** 脚本代码 [非空]    脚本代码为java代码,参数结构中定义的变量名可直接使用,有错误返回错误代码，没错误无需返回   **/
		public final static String saJavaCode="sa_java_code";
		/** 返回格式化 [非空]    模板代码为velocity模板,可用变量为上面脚本代码中定义的局部变量   **/
		public final static String saReturnFormat="sa_return_format";
		/** 错误代码        **/
		public final static String saError="sa_error";
		/** 添加人 [非空] [SrvUser]     ${basePath}${proname}/permissions/user_tree.do **/
		public final static String saEditor="sa_editor";
		/** 修改时间 [非空]       **/
		public final static String saUpdateTime="sa_update_time";
		/** 添加时间 [非空]       **/
		public final static String saAddTime="sa_add_time";
		/** 状态 [非空]   0:测试中 1:正常 2:禁用     **/
		public final static String saStatus="sa_status";
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
				$.Set(name,SrvApi.getSrvApi(params));
			else
				$.Set(name,SrvApi.getSrvApiList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取API接口数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.SrvApi) $.GetRequest("SrvApi$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = SrvApi.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = SrvApi.getSrvApi(params);
			else
				value = SrvApi.getSrvApiList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = SrvApi.Get($.add(SrvApi.F.saId,param));
		else if(!$.empty(param.toString()))
			value = SrvApi.get(Long.valueOf(param.toString()));
		$.SetRequest("SrvApi$"+param.hashCode(), value);
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
	public void mergeSet(SrvApi old){
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