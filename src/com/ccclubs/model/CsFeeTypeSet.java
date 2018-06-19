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

@namespace("configurator/feetypeset")
public @caption("计费策略") @table("cs_fee_type_set") class CsFeeTypeSet implements java.io.Serializable
{
	private static final long serialVersionUID =          1l;
	private @caption("编号") @column("csfts_id")   @primary  @note("  ") Long csftsId;// 主键 非空     
	private @caption("运营城市") @column("csfts_host")    @relate("$csftsHost") @RelateClass(SrvHost.class)  @note("  ") Long csftsHost;// 非空     
 	private SrvHost $csftsHost;//关联对象[运营城市]
	private @caption("适用车型") @column("csfts_model")    @relate("$csftsModel") @RelateClass(CsCarModel.class)  @note("  ") Long csftsModel;// 非空     
 	private CsCarModel $csftsModel;//关联对象[车型]
	private @caption("适用计费类型") @column("csfts_fee_type")    @relate("$csftsFeeType") @RelateClass(CsUserType.class)  @note("  ") String csftsFeeType;// 非空    select * from cs_user_type where 1=1 
 	private CsUserType $csftsFeeType;//关联对象[计费方式]
	private @caption("默认计费类型") @column("csfts_default")    @relate("$csftsDefault") @RelateClass(CsUserType.class)  @note("  ") Long csftsDefault;// 非空     
 	private CsUserType $csftsDefault;//关联对象[计费方式]
	private @caption("默认网点") @column("csfts_outlets")    @relate("$csftsOutlets") @RelateClass(CsOutlets.class)  @note(" {csftsHost}:host  默认价格参考网点") Long csftsOutlets;// {csftsHost}:host  默认价格参考网点  ${basePath}${proname}/object/outlets_tree.do?parent={param} 
 	private CsOutlets $csftsOutlets;//关联对象[网点]
	private @caption("状态") @column("csfts_status")    @note(" 1:正常 0:无效  ") Short csftsStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsFeeTypeSet(){
	
	}
	
	//主键构造函数
	public CsFeeTypeSet(Long id){
		this.csftsId = id;
	}
	
	/**所有字段构造函数 CsFeeTypeSet(csftsHost,csftsModel,csftsFeeType,csftsDefault,csftsOutlets,csftsStatus)
	 CsFeeTypeSet(
	 	$.getLong("csftsHost")//运营城市 [非空]
	 	,$.getLong("csftsModel")//适用车型 [非空]
	 	,$.getString("csftsFeeType")//适用计费类型 [非空]
	 	,$.getLong("csftsDefault")//默认计费类型 [非空]
	 	,$.getLong("csftsOutlets")//默认网点
	 	,$.getShort("csftsStatus")//状态 [非空]
	 )
	**/
	public CsFeeTypeSet(Long csftsHost,Long csftsModel,String csftsFeeType,Long csftsDefault,Long csftsOutlets,Short csftsStatus){
		this.csftsHost=csftsHost;
		this.csftsModel=csftsModel;
		this.csftsFeeType=csftsFeeType;
		this.csftsDefault=csftsDefault;
		this.csftsOutlets=csftsOutlets;
		this.csftsStatus=csftsStatus;
	}
	
	//设置非空字段
	public CsFeeTypeSet setNotNull(Long csftsHost,Long csftsModel,String csftsFeeType,Long csftsDefault,Short csftsStatus){
		this.csftsHost=csftsHost;
		this.csftsModel=csftsModel;
		this.csftsFeeType=csftsFeeType;
		this.csftsDefault=csftsDefault;
		this.csftsStatus=csftsStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsFeeTypeSet csftsId(Long csftsId){
		this.csftsId = csftsId;
		this.setSeted(F.csftsId);
		return this;
	}
	/** 运营城市 [非空] [SrvHost]      **/
	public CsFeeTypeSet csftsHost(Long csftsHost){
		this.csftsHost = csftsHost;
		this.setSeted(F.csftsHost);
		return this;
	}
	/** 适用车型 [非空] [CsCarModel]      **/
	public CsFeeTypeSet csftsModel(Long csftsModel){
		this.csftsModel = csftsModel;
		this.setSeted(F.csftsModel);
		return this;
	}
	/** 适用计费类型 [非空] [CsUserType]     select * from cs_user_type where 1=1 **/
	public CsFeeTypeSet csftsFeeType(String csftsFeeType){
		this.csftsFeeType = csftsFeeType;
		this.setSeted(F.csftsFeeType);
		return this;
	}
	/** 默认计费类型 [非空] [CsUserType]      **/
	public CsFeeTypeSet csftsDefault(Long csftsDefault){
		this.csftsDefault = csftsDefault;
		this.setSeted(F.csftsDefault);
		return this;
	}
	/** 默认网点  [CsOutlets]  {csftsHost}:host  默认价格参考网点  ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
	public CsFeeTypeSet csftsOutlets(Long csftsOutlets){
		this.csftsOutlets = csftsOutlets;
		this.setSeted(F.csftsOutlets);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsFeeTypeSet csftsStatus(Short csftsStatus){
		this.csftsStatus = csftsStatus;
		this.setSeted(F.csftsStatus);
		return this;
	}
	
	
	//克隆对象
	public CsFeeTypeSet clone(){
		CsFeeTypeSet clone = new CsFeeTypeSet();
		clone.csftsHost=this.csftsHost;
		clone.csftsModel=this.csftsModel;
		clone.csftsFeeType=this.csftsFeeType;
		clone.csftsDefault=this.csftsDefault;
		clone.csftsStatus=this.csftsStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取计费策略
	 * @param id
	 * @return
	 */
	public static @api CsFeeTypeSet get(Long id){		
		return getCsFeeTypeSetById(id);
	}
	/**
	 * 获取所有计费策略
	 * @return
	 */
	public static @api List<CsFeeTypeSet> list(Map params,Integer size){
		return getCsFeeTypeSetList(params,size);
	}
	/**
	 * 获取计费策略分页
	 * @return
	 */
	public static @api Page<CsFeeTypeSet> page(int page,int size,Map params){
		return getCsFeeTypeSetPage(page, size , params);
	}
	/**
	 * 根据查询条件取计费策略
	 * @param params
	 * @return
	 */
	public static @api CsFeeTypeSet Get(Map params){
		return getCsFeeTypeSet(params);
	}
	/**
	 * 获取计费策略数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsFeeTypeSetCount(params);
	}
	/**
	 * 获取计费策略数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsFeeTypeSetEval(eval,params);
	}
	
	/**
	 * 根据ID取计费策略
	 * @param id
	 * @return
	 */
	public static @api CsFeeTypeSet getCsFeeTypeSetById(Long id){		
		CsFeeTypeSet csFeeTypeSet = (CsFeeTypeSet) $.GetRequest("CsFeeTypeSet$"+id);
		if(csFeeTypeSet!=null)
			return csFeeTypeSet;
		com.ccclubs.service.admin.ICsFeeTypeSetService csFeeTypeSetService = $.GetSpringBean("csFeeTypeSetService");		
		return csFeeTypeSetService.getCsFeeTypeSetById(id);
	}
	
	
	/**
	 * 根据ID取计费策略的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsFeeTypeSet.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsFeeTypeSet csFeeTypeSet = get(id);
		if(csFeeTypeSet!=null){
			String strValue = csFeeTypeSet.getCsftsHost$();
			if(!"CsftsHost".equals("CsftsHost"))
				strValue+="("+csFeeTypeSet.getCsftsHost$()+")";
			MemCache.setValue(CsFeeTypeSet.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCsftsHost$();
		if(!"CsftsHost".equals("CsftsHost"))
			keyValue+="("+this.getCsftsHost$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有计费策略
	 * @return
	 */
	public static @api List<CsFeeTypeSet> getCsFeeTypeSetList(Map params,Integer size){
		com.ccclubs.service.admin.ICsFeeTypeSetService csFeeTypeSetService = $.GetSpringBean("csFeeTypeSetService");
		return csFeeTypeSetService.getCsFeeTypeSetList(params, size);
	}
	
	/**
	 * 获取计费策略分页
	 * @return
	 */
	public static @api Page<CsFeeTypeSet> getCsFeeTypeSetPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsFeeTypeSetService csFeeTypeSetService = $.GetSpringBean("csFeeTypeSetService");
		return csFeeTypeSetService.getCsFeeTypeSetPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取计费策略
	 * @param params
	 * @return
	 */
	public static @api CsFeeTypeSet getCsFeeTypeSet(Map params){
		com.ccclubs.service.admin.ICsFeeTypeSetService csFeeTypeSetService = $.GetSpringBean("csFeeTypeSetService");
		return csFeeTypeSetService.getCsFeeTypeSet(params);
	}
	
	/**
	 * 获取计费策略数
	 * @return
	 */
	public static @api Long getCsFeeTypeSetCount(Map params){
		com.ccclubs.service.admin.ICsFeeTypeSetService csFeeTypeSetService = $.GetSpringBean("csFeeTypeSetService");
		return csFeeTypeSetService.getCsFeeTypeSetCount(params);
	}
		
		
	/**
	 * 获取计费策略自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsFeeTypeSetEval(String eval,Map params){
		com.ccclubs.service.admin.ICsFeeTypeSetService csFeeTypeSetService = $.GetSpringBean("csFeeTypeSetService");
		return csFeeTypeSetService.getCsFeeTypeSetEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsFeeTypeSet(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsFeeTypeSetService csFeeTypeSetService = $.GetSpringBean("csFeeTypeSetService");
		csFeeTypeSetService.updateCsFeeTypeSetByConfirm(set, where);
	}
	
	
	/**
	 * 保存计费策略对象
	 * @param params
	 * @return
	 */
	public CsFeeTypeSet save(){
		com.ccclubs.service.admin.ICsFeeTypeSetService csFeeTypeSetService = $.GetSpringBean("csFeeTypeSetService");
		if(this.getCsftsId()!=null)
			csFeeTypeSetService.updateCsFeeTypeSet(this);
		else
			return csFeeTypeSetService.saveCsFeeTypeSet(this);
		return this;
	}
	
	
	/**
	 * 更新计费策略对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsFeeTypeSetService csFeeTypeSetService = $.GetSpringBean("csFeeTypeSetService");
		csFeeTypeSetService.updateCsFeeTypeSet$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsFeeTypeSetService csFeeTypeSetService = $.GetSpringBean("csFeeTypeSetService");
		if($.equals($.config("logic_delete"),"true"))
			csFeeTypeSetService.removeCsFeeTypeSetById(this.getCsftsId());
		else
			csFeeTypeSetService.deleteCsFeeTypeSetById(this.getCsftsId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsFeeTypeSetService csFeeTypeSetService = $.GetSpringBean("csFeeTypeSetService");
		return csFeeTypeSetService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCsftsId(){
		return this.csftsId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCsftsId$(){
		String strValue="";
		 strValue=$.str(this.getCsftsId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCsftsId(Long csftsId){
		this.csftsId = csftsId;
		this.setSeted(F.csftsId);
	}
	/*******************************运营城市**********************************/	
	/**
	* 运营城市 [非空] [SrvHost]     
	**/
	public Long getCsftsHost(){
		return this.csftsHost;
	}
	/**
	* 获取运营城市格式化(toString)
	**/
	public String getCsftsHost$(){
		String strValue="";
		if(this.getCsftsHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCsftsHost()));
		}			
	 	 return strValue;
	}
	/**
	* 运营城市 [非空] [SrvHost]     
	**/
	public void setCsftsHost(Long csftsHost){
		this.csftsHost = csftsHost;
		this.setSeted(F.csftsHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$csftsHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCsftsHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCsftsHost()!=null){
 			srvHost = SrvHost.get(this.getCsftsHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCsftsHost(), srvHost);
	 	return srvHost;
	}
	/*******************************适用车型**********************************/	
	/**
	* 适用车型 [非空] [CsCarModel]     
	**/
	public Long getCsftsModel(){
		return this.csftsModel;
	}
	/**
	* 获取适用车型格式化(toString)
	**/
	public String getCsftsModel$(){
		String strValue="";
		if(this.getCsftsModel()!=null){
				strValue+=$.str(CsCarModel.getKeyValue(this.getCsftsModel()));
		}			
	 	 return strValue;
	}
	/**
	* 适用车型 [非空] [CsCarModel]     
	**/
	public void setCsftsModel(Long csftsModel){
		this.csftsModel = csftsModel;
		this.setSeted(F.csftsModel);
	}
	/**
	* 获取关联对象[车型]
	**/	 			
 	public CsCarModel get$csftsModel(){
 		com.ccclubs.model.CsCarModel csCarModel = (com.ccclubs.model.CsCarModel) $.GetRequest("CsCarModel$"+this.getCsftsModel());
 		if(csCarModel!=null)
			return csCarModel;
		if(this.getCsftsModel()!=null){
 			csCarModel = CsCarModel.get(this.getCsftsModel());
 		}
 		$.SetRequest("CsCarModel$"+this.getCsftsModel(), csCarModel);
	 	return csCarModel;
	}
	/*******************************适用计费类型**********************************/	
	/**
	* 适用计费类型 [非空] [CsUserType]     select * from cs_user_type where 1=1
	**/
	public String getCsftsFeeType(){
		return this.csftsFeeType;
	}
	/**
	* 获取适用计费类型格式化(toString)
	**/
	public String getCsftsFeeType$(){
		String strValue="";
			
		if(!$.empty(this.getCsftsFeeType())){
			List<com.ccclubs.model.CsUserType> items = com.ccclubs.model.CsUserType.getCsUserTypeList($.add("definex","csut_id in ("+$.str(this.getCsftsFeeType()).replace("#","")+")"),-1);
			for(com.ccclubs.model.CsUserType item1:items)
				strValue+="["+$.str(item1.getCsutName())+"]";
		}
	 	 return strValue;
	}
	/**
	* 适用计费类型 [非空] [CsUserType]     select * from cs_user_type where 1=1
	**/
	public void setCsftsFeeType(String csftsFeeType){
		this.csftsFeeType = csftsFeeType;
		this.setSeted(F.csftsFeeType);
	}
			
	/**
	* 获取适用计费类型列表
	**/ 			
 	public List<com.ccclubs.model.CsUserType> get$csftsFeeType(){
		if(!$.empty(this.getCsftsFeeType())){
			return com.ccclubs.model.CsUserType.getCsUserTypeList($.add("definex","csut_id in ("+$.str(this.getCsftsFeeType()).replace("#","")+")"),-1);
		}else
			return new ArrayList();
	}
	/*******************************默认计费类型**********************************/	
	/**
	* 默认计费类型 [非空] [CsUserType]     
	**/
	public Long getCsftsDefault(){
		return this.csftsDefault;
	}
	/**
	* 获取默认计费类型格式化(toString)
	**/
	public String getCsftsDefault$(){
		String strValue="";
		if(this.getCsftsDefault()!=null){
				strValue+=$.str(CsUserType.getKeyValue(this.getCsftsDefault()));
		}			
	 	 return strValue;
	}
	/**
	* 默认计费类型 [非空] [CsUserType]     
	**/
	public void setCsftsDefault(Long csftsDefault){
		this.csftsDefault = csftsDefault;
		this.setSeted(F.csftsDefault);
	}
	/**
	* 获取关联对象[计费方式]
	**/	 			
 	public CsUserType get$csftsDefault(){
 		com.ccclubs.model.CsUserType csUserType = (com.ccclubs.model.CsUserType) $.GetRequest("CsUserType$"+this.getCsftsDefault());
 		if(csUserType!=null)
			return csUserType;
		if(this.getCsftsDefault()!=null){
 			csUserType = CsUserType.get(this.getCsftsDefault());
 		}
 		$.SetRequest("CsUserType$"+this.getCsftsDefault(), csUserType);
	 	return csUserType;
	}
	/*******************************默认网点**********************************/	
	/**
	* 默认网点  [CsOutlets]  {csftsHost}:host  默认价格参考网点  ${basePath}${proname}/object/outlets_tree.do?parent={param}
	**/
	public Long getCsftsOutlets(){
		return this.csftsOutlets;
	}
	/**
	* 获取默认网点格式化(toString)
	**/
	public String getCsftsOutlets$(){
		String strValue="";
		if(this.getCsftsOutlets()!=null){
				strValue+=$.str(CsOutlets.getKeyValue(this.getCsftsOutlets()));
		}			
	 	 return strValue;
	}
	/**
	* 默认网点  [CsOutlets]  {csftsHost}:host  默认价格参考网点  ${basePath}${proname}/object/outlets_tree.do?parent={param}
	**/
	public void setCsftsOutlets(Long csftsOutlets){
		this.csftsOutlets = csftsOutlets;
		this.setSeted(F.csftsOutlets);
	}
	/**
	* 获取关联对象[网点]
	**/	 			
 	public CsOutlets get$csftsOutlets(){
 		com.ccclubs.model.CsOutlets csOutlets = (com.ccclubs.model.CsOutlets) $.GetRequest("CsOutlets$"+this.getCsftsOutlets());
 		if(csOutlets!=null)
			return csOutlets;
		if(this.getCsftsOutlets()!=null){
 			csOutlets = CsOutlets.get(this.getCsftsOutlets());
 		}
 		$.SetRequest("CsOutlets$"+this.getCsftsOutlets(), csOutlets);
	 	return csOutlets;
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCsftsStatus(){
		return this.csftsStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCsftsStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCsftsStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCsftsStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCsftsStatus(Short csftsStatus){
		this.csftsStatus = csftsStatus;
		this.setSeted(F.csftsStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsFeeTypeSet.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsFeeTypeSet.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsFeeTypeSet.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsFeeTypeSet.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsFeeTypeSet.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsFeeTypeSet.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsFeeTypeSet.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsFeeTypeSet.this);
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
		public M csftsId(Object csftsId){this.put("csftsId", csftsId);return this;};
	 	/** and csfts_id is null */
 		public M csftsIdNull(){if(this.get("csftsIdNot")==null)this.put("csftsIdNot", "");this.put("csftsId", null);return this;};
 		/** not .... */
 		public M csftsIdNot(){this.put("csftsIdNot", "not");return this;};
		/** 运营城市 [非空] [SrvHost]      **/
		public M csftsHost(Object csftsHost){this.put("csftsHost", csftsHost);return this;};
	 	/** and csfts_host is null */
 		public M csftsHostNull(){if(this.get("csftsHostNot")==null)this.put("csftsHostNot", "");this.put("csftsHost", null);return this;};
 		/** not .... */
 		public M csftsHostNot(){this.put("csftsHostNot", "not");return this;};
		/** 适用车型 [非空] [CsCarModel]      **/
		public M csftsModel(Object csftsModel){this.put("csftsModel", csftsModel);return this;};
	 	/** and csfts_model is null */
 		public M csftsModelNull(){if(this.get("csftsModelNot")==null)this.put("csftsModelNot", "");this.put("csftsModel", null);return this;};
 		/** not .... */
 		public M csftsModelNot(){this.put("csftsModelNot", "not");return this;};
		public M csftsModel$on(CsCarModel.M value){
			this.put("CsCarModel", value);
			this.put("csftsModel$on", value);
			return this;
		};	
		/** 适用计费类型 [非空] [CsUserType]     select * from cs_user_type where 1=1 **/
		public M csftsFeeType(Object csftsFeeType){this.put("csftsFeeType", csftsFeeType);return this;};
	 	/** and csfts_fee_type is null */
 		public M csftsFeeTypeNull(){if(this.get("csftsFeeTypeNot")==null)this.put("csftsFeeTypeNot", "");this.put("csftsFeeType", null);return this;};
 		/** not .... */
 		public M csftsFeeTypeNot(){this.put("csftsFeeTypeNot", "not");return this;};
		/** 默认计费类型 [非空] [CsUserType]      **/
		public M csftsDefault(Object csftsDefault){this.put("csftsDefault", csftsDefault);return this;};
	 	/** and csfts_default is null */
 		public M csftsDefaultNull(){if(this.get("csftsDefaultNot")==null)this.put("csftsDefaultNot", "");this.put("csftsDefault", null);return this;};
 		/** not .... */
 		public M csftsDefaultNot(){this.put("csftsDefaultNot", "not");return this;};
		public M csftsDefault$on(CsUserType.M value){
			this.put("CsUserType", value);
			this.put("csftsDefault$on", value);
			return this;
		};	
		/** 默认网点  [CsOutlets]  {csftsHost}:host  默认价格参考网点  ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
		public M csftsOutlets(Object csftsOutlets){this.put("csftsOutlets", csftsOutlets);return this;};
	 	/** and csfts_outlets is null */
 		public M csftsOutletsNull(){if(this.get("csftsOutletsNot")==null)this.put("csftsOutletsNot", "");this.put("csftsOutlets", null);return this;};
 		/** not .... */
 		public M csftsOutletsNot(){this.put("csftsOutletsNot", "not");return this;};
		public M csftsOutlets$on(CsOutlets.M value){
			this.put("CsOutlets", value);
			this.put("csftsOutlets$on", value);
			return this;
		};	
		/** 状态 [非空]   1:正常 0:无效     **/
		public M csftsStatus(Object csftsStatus){this.put("csftsStatus", csftsStatus);return this;};
	 	/** and csfts_status is null */
 		public M csftsStatusNull(){if(this.get("csftsStatusNot")==null)this.put("csftsStatusNot", "");this.put("csftsStatus", null);return this;};
 		/** not .... */
 		public M csftsStatusNot(){this.put("csftsStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有计费策略 **/
		public @api List<CsFeeTypeSet> list(Integer size){
			return getCsFeeTypeSetList(this,size);
		}
		/** 获取计费策略分页 **/
		public @api Page<CsFeeTypeSet> page(int page,int size){
			return getCsFeeTypeSetPage(page, size , this);
		}
		/** 根据查询条件取计费策略 **/
		public @api CsFeeTypeSet get(){
			return getCsFeeTypeSet(this);
		}
		/** 获取计费策略数 **/
		public @api Long count(){
			return getCsFeeTypeSetCount(this);
		}
		/** 获取计费策略表达式 **/
		public @api <T> T eval(String strEval){
			return getCsFeeTypeSetEval(strEval,this);
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
			updateCsFeeTypeSet(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String csftsId="csftsId";
		/** 运营城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String csftsHost="csftsHost";
		/** 适用车型 [非空] [CsCarModel]      **/
		public final static @type(Long.class)  String csftsModel="csftsModel";
		/** 适用计费类型 [非空] [CsUserType]     select * from cs_user_type where 1=1 **/
		public final static @type(String.class) @like String csftsFeeType="csftsFeeType";
		/** 默认计费类型 [非空] [CsUserType]      **/
		public final static @type(Long.class)  String csftsDefault="csftsDefault";
		/** 默认网点  [CsOutlets]  {csftsHost}:host  默认价格参考网点  ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
		public final static @type(Long.class)  String csftsOutlets="csftsOutlets";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String csftsStatus="csftsStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String csftsId="csfts_id";
		/** 运营城市 [非空] [SrvHost]      **/
		public final static String csftsHost="csfts_host";
		/** 适用车型 [非空] [CsCarModel]      **/
		public final static String csftsModel="csfts_model";
		/** 适用计费类型 [非空] [CsUserType]     select * from cs_user_type where 1=1 **/
		public final static String csftsFeeType="csfts_fee_type";
		/** 默认计费类型 [非空] [CsUserType]      **/
		public final static String csftsDefault="csfts_default";
		/** 默认网点  [CsOutlets]  {csftsHost}:host  默认价格参考网点  ${basePath}${proname}/object/outlets_tree.do?parent={param} **/
		public final static String csftsOutlets="csfts_outlets";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String csftsStatus="csfts_status";
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
				$.Set(name,CsFeeTypeSet.getCsFeeTypeSet(params));
			else
				$.Set(name,CsFeeTypeSet.getCsFeeTypeSetList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取计费策略数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsFeeTypeSet) $.GetRequest("CsFeeTypeSet$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsFeeTypeSet.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsFeeTypeSet.getCsFeeTypeSet(params);
			else
				value = CsFeeTypeSet.getCsFeeTypeSetList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsFeeTypeSet.Get($.add(CsFeeTypeSet.F.csftsId,param));
		else if(!$.empty(param.toString()))
			value = CsFeeTypeSet.get(Long.valueOf(param.toString()));
		$.SetRequest("CsFeeTypeSet$"+param.hashCode(), value);
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
	public void mergeSet(CsFeeTypeSet old){
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