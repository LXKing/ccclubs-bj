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

@namespace("configurator/config")
public @caption("系统配置(城市)") @table("cs_config") class CsConfig implements java.io.Serializable
{
	private static final long serialVersionUID =          1l;
	private @caption("编号") @column("csc_id")   @primary  @note("  ") Long cscId;// 主键 非空     
	private @caption("城市") @column("csc_host")    @relate("$cscHost") @RelateClass(SrvHost.class)  @note("  ") Long cscHost;// 非空     
 	private SrvHost $cscHost;//关联对象[运营城市]
	private @caption("电量阀值") @column("csc_limit_power")    @note("  当车辆电量超过此值时发短信给维护人员") Integer cscLimitPower;//  当车辆电量超过此值时发短信给维护人员   
	private @caption("可选标记") @column("csc_flags")    @note(" 0:默认 1:叫车  ") String cscFlags;// 0:默认 1:叫车     
	private @caption("联系地址") @column("csc_address")    @note("  ") String cscAddress;//     
	private @caption("客服电话") @column("csc_svr_number")    @note("  ") String cscSvrNumber;//     
	private @caption("QQ群号") @column("csc_srv_qq_group")    @note("  ") String cscSrvQqGroup;//     
	private @caption("首页海报") @column("csc_banners")  @hidden   @note(" image:image text:link  ") String cscBanners;// image:image text:link     
	
	//默认构造函数
	public CsConfig(){
	
	}
	
	//主键构造函数
	public CsConfig(Long id){
		this.cscId = id;
	}
	
	/**所有字段构造函数 CsConfig(cscHost,cscLimitPower,cscFlags,cscAddress,cscSvrNumber,cscSrvQqGroup,cscBanners)
	 CsConfig(
	 	$.getLong("cscHost")//城市 [非空]
	 	,$.getInteger("cscLimitPower")//电量阀值
	 	,$.getString("cscFlags")//可选标记
	 	,$.getString("cscAddress")//联系地址
	 	,$.getString("cscSvrNumber")//客服电话
	 	,$.getString("cscSrvQqGroup")//QQ群号
	 	,$.getString("cscBanners")//首页海报
	 )
	**/
	public CsConfig(Long cscHost,Integer cscLimitPower,String cscFlags,String cscAddress,String cscSvrNumber,String cscSrvQqGroup,String cscBanners){
		this.cscHost=cscHost;
		this.cscLimitPower=cscLimitPower;
		this.cscFlags=cscFlags;
		this.cscAddress=cscAddress;
		this.cscSvrNumber=cscSvrNumber;
		this.cscSrvQqGroup=cscSrvQqGroup;
		this.cscBanners=cscBanners;
	}
	
	//设置非空字段
	public CsConfig setNotNull(Long cscHost){
		this.cscHost=cscHost;
		return this;
	}
	/** 编号 [非空]       **/
	public CsConfig cscId(Long cscId){
		this.cscId = cscId;
		this.setSeted(F.cscId);
		return this;
	}
	/** 城市 [非空] [SrvHost]      **/
	public CsConfig cscHost(Long cscHost){
		this.cscHost = cscHost;
		this.setSeted(F.cscHost);
		return this;
	}
	/** 电量阀值     当车辆电量超过此值时发短信给维护人员   **/
	public CsConfig cscLimitPower(Integer cscLimitPower){
		this.cscLimitPower = cscLimitPower;
		this.setSeted(F.cscLimitPower);
		return this;
	}
	/** 可选标记    0:默认 1:叫车     **/
	public CsConfig cscFlags(String cscFlags){
		this.cscFlags = cscFlags;
		this.setSeted(F.cscFlags);
		return this;
	}
	/** 联系地址        **/
	public CsConfig cscAddress(String cscAddress){
		this.cscAddress = cscAddress;
		this.setSeted(F.cscAddress);
		return this;
	}
	/** 客服电话        **/
	public CsConfig cscSvrNumber(String cscSvrNumber){
		this.cscSvrNumber = cscSvrNumber;
		this.setSeted(F.cscSvrNumber);
		return this;
	}
	/** QQ群号        **/
	public CsConfig cscSrvQqGroup(String cscSrvQqGroup){
		this.cscSrvQqGroup = cscSrvQqGroup;
		this.setSeted(F.cscSrvQqGroup);
		return this;
	}
	/** 首页海报    image:image text:link     **/
	public CsConfig cscBanners(String cscBanners){
		this.cscBanners = cscBanners;
		this.setSeted(F.cscBanners);
		return this;
	}
	
	
	//克隆对象
	public CsConfig clone(){
		CsConfig clone = new CsConfig();
		clone.cscHost=this.cscHost;
		return clone;
	}
	
	
	/**
	 * 根据ID取系统配置(城市)
	 * @param id
	 * @return
	 */
	public static @api CsConfig get(Long id){		
		return getCsConfigById(id);
	}
	/**
	 * 获取所有系统配置(城市)
	 * @return
	 */
	public static @api List<CsConfig> list(Map params,Integer size){
		return getCsConfigList(params,size);
	}
	/**
	 * 获取系统配置(城市)分页
	 * @return
	 */
	public static @api Page<CsConfig> page(int page,int size,Map params){
		return getCsConfigPage(page, size , params);
	}
	/**
	 * 根据查询条件取系统配置(城市)
	 * @param params
	 * @return
	 */
	public static @api CsConfig Get(Map params){
		return getCsConfig(params);
	}
	/**
	 * 获取系统配置(城市)数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsConfigCount(params);
	}
	/**
	 * 获取系统配置(城市)数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsConfigEval(eval,params);
	}
	
	/**
	 * 根据ID取系统配置(城市)
	 * @param id
	 * @return
	 */
	public static @api CsConfig getCsConfigById(Long id){		
		CsConfig csConfig = (CsConfig) $.GetRequest("CsConfig$"+id);
		if(csConfig!=null)
			return csConfig;
		com.ccclubs.service.admin.ICsConfigService csConfigService = $.GetSpringBean("csConfigService");		
		return csConfigService.getCsConfigById(id);
	}
	
	
	/**
	 * 根据ID取系统配置(城市)的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsConfig.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsConfig csConfig = get(id);
		if(csConfig!=null){
			String strValue = csConfig.getCscId$();
			if(!"CscId".equals("CscId"))
				strValue+="("+csConfig.getCscId$()+")";
			MemCache.setValue(CsConfig.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCscId$();
		if(!"CscId".equals("CscId"))
			keyValue+="("+this.getCscId$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有系统配置(城市)
	 * @return
	 */
	public static @api List<CsConfig> getCsConfigList(Map params,Integer size){
		com.ccclubs.service.admin.ICsConfigService csConfigService = $.GetSpringBean("csConfigService");
		return csConfigService.getCsConfigList(params, size);
	}
	
	/**
	 * 获取系统配置(城市)分页
	 * @return
	 */
	public static @api Page<CsConfig> getCsConfigPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsConfigService csConfigService = $.GetSpringBean("csConfigService");
		return csConfigService.getCsConfigPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取系统配置(城市)
	 * @param params
	 * @return
	 */
	public static @api CsConfig getCsConfig(Map params){
		com.ccclubs.service.admin.ICsConfigService csConfigService = $.GetSpringBean("csConfigService");
		return csConfigService.getCsConfig(params);
	}
	
	/**
	 * 获取系统配置(城市)数
	 * @return
	 */
	public static @api Long getCsConfigCount(Map params){
		com.ccclubs.service.admin.ICsConfigService csConfigService = $.GetSpringBean("csConfigService");
		return csConfigService.getCsConfigCount(params);
	}
		
		
	/**
	 * 获取系统配置(城市)自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsConfigEval(String eval,Map params){
		com.ccclubs.service.admin.ICsConfigService csConfigService = $.GetSpringBean("csConfigService");
		return csConfigService.getCsConfigEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsConfig(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsConfigService csConfigService = $.GetSpringBean("csConfigService");
		csConfigService.updateCsConfigByConfirm(set, where);
	}
	
	
	/**
	 * 保存系统配置(城市)对象
	 * @param params
	 * @return
	 */
	public CsConfig save(){
		com.ccclubs.service.admin.ICsConfigService csConfigService = $.GetSpringBean("csConfigService");
		if(this.getCscId()!=null)
			csConfigService.updateCsConfig(this);
		else
			return csConfigService.saveCsConfig(this);
		return this;
	}
	
	
	/**
	 * 更新系统配置(城市)对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsConfigService csConfigService = $.GetSpringBean("csConfigService");
		csConfigService.updateCsConfig$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsConfigService csConfigService = $.GetSpringBean("csConfigService");
			csConfigService.deleteCsConfigById(this.getCscId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsConfigService csConfigService = $.GetSpringBean("csConfigService");
		return csConfigService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCscId(){
		return this.cscId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCscId$(){
		String strValue="";
		 strValue=$.str(this.getCscId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCscId(Long cscId){
		this.cscId = cscId;
		this.setSeted(F.cscId);
	}
	/*******************************城市**********************************/	
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public Long getCscHost(){
		return this.cscHost;
	}
	/**
	* 获取城市格式化(toString)
	**/
	public String getCscHost$(){
		String strValue="";
		if(this.getCscHost()!=null){
				strValue+=$.str(SrvHost.getKeyValue(this.getCscHost()));
		}			
	 	 return strValue;
	}
	/**
	* 城市 [非空] [SrvHost]     
	**/
	public void setCscHost(Long cscHost){
		this.cscHost = cscHost;
		this.setSeted(F.cscHost);
	}
	/**
	* 获取关联对象[运营城市]
	**/	 			
 	public SrvHost get$cscHost(){
 		com.ccclubs.model.SrvHost srvHost = (com.ccclubs.model.SrvHost) $.GetRequest("SrvHost$"+this.getCscHost());
 		if(srvHost!=null)
			return srvHost;
		if(this.getCscHost()!=null){
 			srvHost = SrvHost.get(this.getCscHost());
 		}
 		$.SetRequest("SrvHost$"+this.getCscHost(), srvHost);
	 	return srvHost;
	}
	/*******************************电量阀值**********************************/	
	/**
	* 电量阀值     当车辆电量超过此值时发短信给维护人员  
	**/
	public Integer getCscLimitPower(){
		return this.cscLimitPower;
	}
	/**
	* 获取电量阀值格式化(toString)
	**/
	public String getCscLimitPower$(){
		String strValue="";
		 strValue=$.str(this.getCscLimitPower());
	 	 return strValue;
	}
	/**
	* 电量阀值     当车辆电量超过此值时发短信给维护人员  
	**/
	public void setCscLimitPower(Integer cscLimitPower){
		this.cscLimitPower = cscLimitPower;
		this.setSeted(F.cscLimitPower);
	}
	/*******************************可选标记**********************************/	
	/**
	* 可选标记    0:默认 1:叫车    
	**/
	public String getCscFlags(){
		return this.cscFlags;
	}
	/**
	* 获取可选标记格式化(toString)
	**/
	public String getCscFlags$(){
		String strValue="";
		if($.idin(this.getCscFlags(),0))
			strValue+=$.str("默认,");								
		if($.idin(this.getCscFlags(),1))
			strValue+=$.str("叫车,");								
	 	 return strValue;
	}
	/**
	* 可选标记    0:默认 1:叫车    
	**/
	public void setCscFlags(String cscFlags){
		this.cscFlags = cscFlags;
		this.setSeted(F.cscFlags);
	}
	/*******************************联系地址**********************************/	
	/**
	* 联系地址       
	**/
	public String getCscAddress(){
		return this.cscAddress;
	}
	/**
	* 获取联系地址格式化(toString)
	**/
	public String getCscAddress$(){
		String strValue="";
		 strValue=$.str(this.getCscAddress());
	 	 return strValue;
	}
	/**
	* 联系地址       
	**/
	public void setCscAddress(String cscAddress){
		this.cscAddress = cscAddress;
		this.setSeted(F.cscAddress);
	}
	/*******************************客服电话**********************************/	
	/**
	* 客服电话       
	**/
	public String getCscSvrNumber(){
		return this.cscSvrNumber;
	}
	/**
	* 获取客服电话格式化(toString)
	**/
	public String getCscSvrNumber$(){
		String strValue="";
		 strValue=$.str(this.getCscSvrNumber());
	 	 return strValue;
	}
	/**
	* 客服电话       
	**/
	public void setCscSvrNumber(String cscSvrNumber){
		this.cscSvrNumber = cscSvrNumber;
		this.setSeted(F.cscSvrNumber);
	}
	/*******************************QQ群号**********************************/	
	/**
	* QQ群号       
	**/
	public String getCscSrvQqGroup(){
		return this.cscSrvQqGroup;
	}
	/**
	* 获取QQ群号格式化(toString)
	**/
	public String getCscSrvQqGroup$(){
		String strValue="";
		 strValue=$.str(this.getCscSrvQqGroup());
	 	 return strValue;
	}
	/**
	* QQ群号       
	**/
	public void setCscSrvQqGroup(String cscSrvQqGroup){
		this.cscSrvQqGroup = cscSrvQqGroup;
		this.setSeted(F.cscSrvQqGroup);
	}
	/*******************************首页海报**********************************/	
	/**
	* 首页海报    image:image text:link    
	**/
	public String getCscBanners(){
		return this.cscBanners;
	}
	/**
	* 获取首页海报格式化(toString)
	**/
	public String getCscBanners$(){
		String strValue="";
		 strValue=$.str(this.getCscBanners());
	 	 return strValue;
	}
	/**
	* 首页海报    image:image text:link    
	**/
	public void setCscBanners(String cscBanners){
		this.cscBanners = cscBanners;
		this.setSeted(F.cscBanners);
	}
	/**
	* 首页海报对应内部类
	**/
	class CscBanners{
		String image;//海报图
		String link;//链接地址
		public void setImage(String image){
			this.image=image;
		}
		public String getImage(){
			return this.image;
		}
		public void setLink(String link){
			this.link=link;
		}
		public String getLink(){
			return this.link;
		}
	}
	/**
	* 获取首页海报格式化列表
	**/ 			
 	public List<CscBanners> get$cscBanners(){
 		List<CscBanners> array = new ArrayList();
 		List<Map> list = $.eval(this.cscBanners);
 		for(Map map:list){
 			CscBanners cscBanners = new CscBanners();
 			cscBanners.setImage($.parseString(map.get("image")));
 			cscBanners.setLink($.parseString(map.get("link")));
 			array.add(cscBanners);
 		}
 		return array;
 	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsConfig.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsConfig.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsConfig.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsConfig.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsConfig.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsConfig.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsConfig.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsConfig.this);
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
		public M cscId(Object cscId){this.put("cscId", cscId);return this;};
	 	/** and csc_id is null */
 		public M cscIdNull(){if(this.get("cscIdNot")==null)this.put("cscIdNot", "");this.put("cscId", null);return this;};
 		/** not .... */
 		public M cscIdNot(){this.put("cscIdNot", "not");return this;};
		/** 城市 [非空] [SrvHost]      **/
		public M cscHost(Object cscHost){this.put("cscHost", cscHost);return this;};
	 	/** and csc_host is null */
 		public M cscHostNull(){if(this.get("cscHostNot")==null)this.put("cscHostNot", "");this.put("cscHost", null);return this;};
 		/** not .... */
 		public M cscHostNot(){this.put("cscHostNot", "not");return this;};
		/** 电量阀值     当车辆电量超过此值时发短信给维护人员   **/
		public M cscLimitPower(Object cscLimitPower){this.put("cscLimitPower", cscLimitPower);return this;};
	 	/** and csc_limit_power is null */
 		public M cscLimitPowerNull(){if(this.get("cscLimitPowerNot")==null)this.put("cscLimitPowerNot", "");this.put("cscLimitPower", null);return this;};
 		/** not .... */
 		public M cscLimitPowerNot(){this.put("cscLimitPowerNot", "not");return this;};
		/** and csc_limit_power >= ? */
		public M cscLimitPowerMin(Object min){this.put("cscLimitPowerMin", min);return this;};
		/** and csc_limit_power <= ? */
		public M cscLimitPowerMax(Object max){this.put("cscLimitPowerMax", max);return this;};
		/** 可选标记    0:默认 1:叫车     **/
		public M cscFlags(Object cscFlags){this.put("cscFlags", cscFlags);return this;};
	 	/** and csc_flags is null */
 		public M cscFlagsNull(){if(this.get("cscFlagsNot")==null)this.put("cscFlagsNot", "");this.put("cscFlags", null);return this;};
 		/** not .... */
 		public M cscFlagsNot(){this.put("cscFlagsNot", "not");return this;};
		/** 联系地址        **/
		public M cscAddress(Object cscAddress){this.put("cscAddress", cscAddress);return this;};
	 	/** and csc_address is null */
 		public M cscAddressNull(){if(this.get("cscAddressNot")==null)this.put("cscAddressNot", "");this.put("cscAddress", null);return this;};
 		/** not .... */
 		public M cscAddressNot(){this.put("cscAddressNot", "not");return this;};
		/** 客服电话        **/
		public M cscSvrNumber(Object cscSvrNumber){this.put("cscSvrNumber", cscSvrNumber);return this;};
	 	/** and csc_svr_number is null */
 		public M cscSvrNumberNull(){if(this.get("cscSvrNumberNot")==null)this.put("cscSvrNumberNot", "");this.put("cscSvrNumber", null);return this;};
 		/** not .... */
 		public M cscSvrNumberNot(){this.put("cscSvrNumberNot", "not");return this;};
		/** QQ群号        **/
		public M cscSrvQqGroup(Object cscSrvQqGroup){this.put("cscSrvQqGroup", cscSrvQqGroup);return this;};
	 	/** and csc_srv_qq_group is null */
 		public M cscSrvQqGroupNull(){if(this.get("cscSrvQqGroupNot")==null)this.put("cscSrvQqGroupNot", "");this.put("cscSrvQqGroup", null);return this;};
 		/** not .... */
 		public M cscSrvQqGroupNot(){this.put("cscSrvQqGroupNot", "not");return this;};
		/** 首页海报    image:image text:link     **/
		public M cscBanners(Object cscBanners){this.put("cscBanners", cscBanners);return this;};
	 	/** and csc_banners is null */
 		public M cscBannersNull(){if(this.get("cscBannersNot")==null)this.put("cscBannersNot", "");this.put("cscBanners", null);return this;};
 		/** not .... */
 		public M cscBannersNot(){this.put("cscBannersNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有系统配置(城市) **/
		public @api List<CsConfig> list(Integer size){
			return getCsConfigList(this,size);
		}
		/** 获取系统配置(城市)分页 **/
		public @api Page<CsConfig> page(int page,int size){
			return getCsConfigPage(page, size , this);
		}
		/** 根据查询条件取系统配置(城市) **/
		public @api CsConfig get(){
			return getCsConfig(this);
		}
		/** 获取系统配置(城市)数 **/
		public @api Long count(){
			return getCsConfigCount(this);
		}
		/** 获取系统配置(城市)表达式 **/
		public @api <T> T eval(String strEval){
			return getCsConfigEval(strEval,this);
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
			updateCsConfig(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cscId="cscId";
		/** 城市 [非空] [SrvHost]      **/
		public final static @type(Long.class)  String cscHost="cscHost";
		/** 电量阀值     当车辆电量超过此值时发短信给维护人员   **/
		public final static @type(Integer.class)  String cscLimitPower="cscLimitPower";
		/** and csc_limit_power >= ? */
		public final static @type(Integer.class) String cscLimitPowerMin="cscLimitPowerMin";
		/** and csc_limit_power <= ? */
		public final static @type(Integer.class) String cscLimitPowerMax="cscLimitPowerMax";
		/** 可选标记    0:默认 1:叫车     **/
		public final static @type(String.class) @like String cscFlags="cscFlags";
		/** 联系地址        **/
		public final static @type(String.class) @like String cscAddress="cscAddress";
		/** 客服电话        **/
		public final static @type(String.class) @like String cscSvrNumber="cscSvrNumber";
		/** QQ群号        **/
		public final static @type(String.class) @like String cscSrvQqGroup="cscSrvQqGroup";
		/** 首页海报    image:image text:link     **/
		public final static @type(String.class)  String cscBanners="cscBanners";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cscId="csc_id";
		/** 城市 [非空] [SrvHost]      **/
		public final static String cscHost="csc_host";
		/** 电量阀值     当车辆电量超过此值时发短信给维护人员   **/
		public final static String cscLimitPower="csc_limit_power";
		/** 可选标记    0:默认 1:叫车     **/
		public final static String cscFlags="csc_flags";
		/** 联系地址        **/
		public final static String cscAddress="csc_address";
		/** 客服电话        **/
		public final static String cscSvrNumber="csc_svr_number";
		/** QQ群号        **/
		public final static String cscSrvQqGroup="csc_srv_qq_group";
		/** 首页海报    image:image text:link     **/
		public final static String cscBanners="csc_banners";
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
				$.Set(name,CsConfig.getCsConfig(params));
			else
				$.Set(name,CsConfig.getCsConfigList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取系统配置(城市)数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsConfig) $.GetRequest("CsConfig$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsConfig.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsConfig.getCsConfig(params);
			else
				value = CsConfig.getCsConfigList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsConfig.Get($.add(CsConfig.F.cscId,param));
		else if(!$.empty(param.toString()))
			value = CsConfig.get(Long.valueOf(param.toString()));
		$.SetRequest("CsConfig$"+param.hashCode(), value);
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
	public void mergeSet(CsConfig old){
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