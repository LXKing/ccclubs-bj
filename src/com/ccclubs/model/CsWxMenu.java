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

@namespace("configurator/wx/wxmenu")
public @caption("微信菜单") @table("cs_wx_menu") class CsWxMenu implements java.io.Serializable
{
	private static final long serialVersionUID =         1l;
	private @caption("编号") @column("cswm_id")   @primary  @note("  ") Long cswmId;// 主键 非空     
	private @caption("菜单名称") @column("cswm_name")    @note("  ") String cswmName;// 非空     
	private @caption("上级菜单") @column("cswm_parent")    @relate("$cswmParent") @RelateClass(CsWxMenu.class)  @note("  ") Long cswmParent;// 非空     
 	private CsWxMenu $cswmParent;//关联对象[微信菜单]
	private @caption("菜单类型") @column("cswm_type")    @note(" 0:链接 1:发消息  ") Short cswmType;// 非空 0:链接 1:发消息     
	private @caption("支持终端类型") @column("cswm_support")    @note(" 0:微信 1:支付宝  ") String cswmSupport;// 非空 0:微信 1:支付宝     
	private @caption("地址或消息") @column("cswm_url_key")    @note("  ") String cswmUrlKey;// 非空     
	private @caption("优先级") @column("cswm_level")    @note(" 0:默认 1:1 2:2 3:3 4:4 5:5  ") Short cswmLevel;// 非空 0:默认 1:1 2:2 3:3 4:4 5:5     
	private @caption("更改时间") @column("cswm_time")    @note("  ") Date cswmTime;// 非空     
	private @caption("状态") @column("cswm_status")    @note(" 1:正常 0:无效  ") Short cswmStatus;// 非空 1:正常 0:无效     
	
	//默认构造函数
	public CsWxMenu(){
	
	}
	
	//主键构造函数
	public CsWxMenu(Long id){
		this.cswmId = id;
	}
	
	/**所有字段构造函数 CsWxMenu(cswmName,cswmParent,cswmType,cswmSupport,cswmUrlKey,cswmLevel,cswmTime,cswmStatus)
	 CsWxMenu(
	 	$.getString("cswmName")//菜单名称 [非空]
	 	,$.getLong("cswmParent")//上级菜单 [非空]
	 	,$.getShort("cswmType")//菜单类型 [非空]
	 	,$.getString("cswmSupport")//支持终端类型 [非空]
	 	,$.getString("cswmUrlKey")//地址或消息 [非空]
	 	,$.getShort("cswmLevel")//优先级 [非空]
	 	,$.getDate("cswmTime")//更改时间 [非空]
	 	,$.getShort("cswmStatus")//状态 [非空]
	 )
	**/
	public CsWxMenu(String cswmName,Long cswmParent,Short cswmType,String cswmSupport,String cswmUrlKey,Short cswmLevel,Date cswmTime,Short cswmStatus){
		this.cswmName=cswmName;
		this.cswmParent=cswmParent;
		this.cswmType=cswmType;
		this.cswmSupport=cswmSupport;
		this.cswmUrlKey=cswmUrlKey;
		this.cswmLevel=cswmLevel;
		this.cswmTime=cswmTime;
		this.cswmStatus=cswmStatus;
	}
	
	//设置非空字段
	public CsWxMenu setNotNull(String cswmName,Long cswmParent,Short cswmType,String cswmSupport,String cswmUrlKey,Short cswmLevel,Date cswmTime,Short cswmStatus){
		this.cswmName=cswmName;
		this.cswmParent=cswmParent;
		this.cswmType=cswmType;
		this.cswmSupport=cswmSupport;
		this.cswmUrlKey=cswmUrlKey;
		this.cswmLevel=cswmLevel;
		this.cswmTime=cswmTime;
		this.cswmStatus=cswmStatus;
		return this;
	}
	/** 编号 [非空]       **/
	public CsWxMenu cswmId(Long cswmId){
		this.cswmId = cswmId;
		this.setSeted(F.cswmId);
		return this;
	}
	/** 菜单名称 [非空]       **/
	public CsWxMenu cswmName(String cswmName){
		this.cswmName = cswmName;
		this.setSeted(F.cswmName);
		return this;
	}
	/** 上级菜单 [非空] [CsWxMenu]      **/
	public CsWxMenu cswmParent(Long cswmParent){
		this.cswmParent = cswmParent;
		this.setSeted(F.cswmParent);
		return this;
	}
	/** 菜单类型 [非空]   0:链接 1:发消息     **/
	public CsWxMenu cswmType(Short cswmType){
		this.cswmType = cswmType;
		this.setSeted(F.cswmType);
		return this;
	}
	/** 支持终端类型 [非空]   0:微信 1:支付宝     **/
	public CsWxMenu cswmSupport(String cswmSupport){
		this.cswmSupport = cswmSupport;
		this.setSeted(F.cswmSupport);
		return this;
	}
	/** 地址或消息 [非空]       **/
	public CsWxMenu cswmUrlKey(String cswmUrlKey){
		this.cswmUrlKey = cswmUrlKey;
		this.setSeted(F.cswmUrlKey);
		return this;
	}
	/** 优先级 [非空]   0:默认 1:1 2:2 3:3 4:4 5:5     **/
	public CsWxMenu cswmLevel(Short cswmLevel){
		this.cswmLevel = cswmLevel;
		this.setSeted(F.cswmLevel);
		return this;
	}
	/** 更改时间 [非空]       **/
	public CsWxMenu cswmTime(Date cswmTime){
		this.cswmTime = cswmTime;
		this.setSeted(F.cswmTime);
		return this;
	}
	/** 状态 [非空]   1:正常 0:无效     **/
	public CsWxMenu cswmStatus(Short cswmStatus){
		this.cswmStatus = cswmStatus;
		this.setSeted(F.cswmStatus);
		return this;
	}
	
	
	//克隆对象
	public CsWxMenu clone(){
		CsWxMenu clone = new CsWxMenu();
		clone.cswmName=this.cswmName;
		clone.cswmParent=this.cswmParent;
		clone.cswmType=this.cswmType;
		clone.cswmSupport=this.cswmSupport;
		clone.cswmUrlKey=this.cswmUrlKey;
		clone.cswmLevel=this.cswmLevel;
		clone.cswmTime=this.cswmTime;
		clone.cswmStatus=this.cswmStatus;
		return clone;
	}
	
	
	/**
	 * 根据ID取微信菜单
	 * @param id
	 * @return
	 */
	public static @api CsWxMenu get(Long id){		
		return getCsWxMenuById(id);
	}
	/**
	 * 获取所有微信菜单
	 * @return
	 */
	public static @api List<CsWxMenu> list(Map params,Integer size){
		return getCsWxMenuList(params,size);
	}
	/**
	 * 获取微信菜单分页
	 * @return
	 */
	public static @api Page<CsWxMenu> page(int page,int size,Map params){
		return getCsWxMenuPage(page, size , params);
	}
	/**
	 * 根据查询条件取微信菜单
	 * @param params
	 * @return
	 */
	public static @api CsWxMenu Get(Map params){
		return getCsWxMenu(params);
	}
	/**
	 * 获取微信菜单数
	 * @return
	 */
	public static @api Long count(Map params){
		return getCsWxMenuCount(params);
	}
	/**
	 * 获取微信菜单数
	 * @return
	 */
	public static @api <T> T eval(String eval,Map params){
		return getCsWxMenuEval(eval,params);
	}
	
	/**
	 * 根据ID取微信菜单
	 * @param id
	 * @return
	 */
	public static @api CsWxMenu getCsWxMenuById(Long id){		
		CsWxMenu csWxMenu = (CsWxMenu) $.GetRequest("CsWxMenu$"+id);
		if(csWxMenu!=null)
			return csWxMenu;
		com.ccclubs.service.admin.ICsWxMenuService csWxMenuService = $.GetSpringBean("csWxMenuService");		
		return csWxMenuService.getCsWxMenuById(id);
	}
	
	
	/**
	 * 根据ID取微信菜单的标识键值
	 * @param id
	 * @return
	 */
	public static @api String getKeyValue(Long id){	
		String value = MemCache.getValue(CsWxMenu.class, id);
		if(!$.empty(value))
			return value;	
		if(id==null||id.longValue()==0)
			return value;			
		CsWxMenu csWxMenu = get(id);
		if(csWxMenu!=null){
			String strValue = csWxMenu.getCswmName$();
			if(!"CswmName".equals("CswmName"))
				strValue+="("+csWxMenu.getCswmName$()+")";
			MemCache.setValue(CsWxMenu.class, id ,strValue);
			return strValue;
		}
		return null;
	}	
	
	
	/**
	 * 获取当前对象的键值
	 * @return
	 */
	public String getKeyValue(){
		String keyValue = this.getCswmName$();
		if(!"CswmName".equals("CswmName"))
			keyValue+="("+this.getCswmName$()+")";
		return keyValue;
	}
	
	
	/**
	 * 获取所有微信菜单
	 * @return
	 */
	public static @api List<CsWxMenu> getCsWxMenuList(Map params,Integer size){
		com.ccclubs.service.admin.ICsWxMenuService csWxMenuService = $.GetSpringBean("csWxMenuService");
		return csWxMenuService.getCsWxMenuList(params, size);
	}
	
	/**
	 * 获取微信菜单分页
	 * @return
	 */
	public static @api Page<CsWxMenu> getCsWxMenuPage(int page,int size,Map params){
		com.ccclubs.service.admin.ICsWxMenuService csWxMenuService = $.GetSpringBean("csWxMenuService");
		return csWxMenuService.getCsWxMenuPage(page, size , params);
	}
	
	/**
	 * 根据查询条件取微信菜单
	 * @param params
	 * @return
	 */
	public static @api CsWxMenu getCsWxMenu(Map params){
		com.ccclubs.service.admin.ICsWxMenuService csWxMenuService = $.GetSpringBean("csWxMenuService");
		return csWxMenuService.getCsWxMenu(params);
	}
	
	/**
	 * 获取微信菜单数
	 * @return
	 */
	public static @api Long getCsWxMenuCount(Map params){
		com.ccclubs.service.admin.ICsWxMenuService csWxMenuService = $.GetSpringBean("csWxMenuService");
		return csWxMenuService.getCsWxMenuCount(params);
	}
		
		
	/**
	 * 获取微信菜单自定义求和表达式,比如求和:eval="sum(id)"
	 * @return
	 */	
	public static @api <T> T getCsWxMenuEval(String eval,Map params){
		com.ccclubs.service.admin.ICsWxMenuService csWxMenuService = $.GetSpringBean("csWxMenuService");
		return csWxMenuService.getCsWxMenuEval(eval,params);
	}
	
	/**
	 * 根据条件更新数据
	 * @param set
	 * @param where
	 */
	public static void updateCsWxMenu(Map set,Map where){
		if(set.isEmpty())throw new RuntimeException("set为空");
		if(where.isEmpty())throw new RuntimeException("where为空");
		where.put("confirm", "1");
		com.ccclubs.service.admin.ICsWxMenuService csWxMenuService = $.GetSpringBean("csWxMenuService");
		csWxMenuService.updateCsWxMenuByConfirm(set, where);
	}
	
	
	/**
	 * 保存微信菜单对象
	 * @param params
	 * @return
	 */
	public CsWxMenu save(){
		com.ccclubs.service.admin.ICsWxMenuService csWxMenuService = $.GetSpringBean("csWxMenuService");
		if(this.getCswmId()!=null)
			csWxMenuService.updateCsWxMenu(this);
		else
			return csWxMenuService.saveCsWxMenu(this);
		return this;
	}
	
	
	/**
	 * 更新微信菜单对象的非空字段,注意跟Service里面的update不一样哦
	 * @param params
	 * @return
	 */
	public void update(){
		com.ccclubs.service.admin.ICsWxMenuService csWxMenuService = $.GetSpringBean("csWxMenuService");
		csWxMenuService.updateCsWxMenu$NotNull(this);
	}
	
	
	/**
	 * 删除
	 * @param params
	 * @return
	 */
	public void delete(){
		com.ccclubs.service.admin.ICsWxMenuService csWxMenuService = $.GetSpringBean("csWxMenuService");
		if($.equals($.config("logic_delete"),"true"))
			csWxMenuService.removeCsWxMenuById(this.getCswmId());
		else
			csWxMenuService.deleteCsWxMenuById(this.getCswmId());
	}
	
	/**
	 * 执行事务
	 * @param function
	 */
	public static <T> T execute(Function function){
		com.ccclubs.service.admin.ICsWxMenuService csWxMenuService = $.GetSpringBean("csWxMenuService");
		return csWxMenuService.executeTransaction(function);
	}
	/*******************************编号**********************************/	
	/**
	* 编号 [非空]      
	**/
	public Long getCswmId(){
		return this.cswmId;
	}
	/**
	* 获取编号格式化(toString)
	**/
	public String getCswmId$(){
		String strValue="";
		 strValue=$.str(this.getCswmId());
	 	 return strValue;
	}
	/**
	* 编号 [非空]      
	**/
	public void setCswmId(Long cswmId){
		this.cswmId = cswmId;
		this.setSeted(F.cswmId);
	}
	/*******************************菜单名称**********************************/	
	/**
	* 菜单名称 [非空]      
	**/
	public String getCswmName(){
		return this.cswmName;
	}
	/**
	* 获取菜单名称格式化(toString)
	**/
	public String getCswmName$(){
		String strValue="";
		 strValue=$.str(this.getCswmName());
	 	 return strValue;
	}
	/**
	* 菜单名称 [非空]      
	**/
	public void setCswmName(String cswmName){
		this.cswmName = cswmName;
		this.setSeted(F.cswmName);
	}
	/*******************************上级菜单**********************************/	
	/**
	* 上级菜单 [非空] [CsWxMenu]     
	**/
	public Long getCswmParent(){
		return this.cswmParent;
	}
	/**
	* 获取上级菜单格式化(toString)
	**/
	public String getCswmParent$(){
		String strValue="";
		if(this.getCswmParent()!=null){
				strValue+=$.str(CsWxMenu.getKeyValue(this.getCswmParent()));
		}			
	 	 return strValue;
	}
	/**
	* 上级菜单 [非空] [CsWxMenu]     
	**/
	public void setCswmParent(Long cswmParent){
		this.cswmParent = cswmParent;
		this.setSeted(F.cswmParent);
	}
	/**
	* 获取关联对象[微信菜单]
	**/	 			
 	public CsWxMenu get$cswmParent(){
 		com.ccclubs.model.CsWxMenu csWxMenu = (com.ccclubs.model.CsWxMenu) $.GetRequest("CsWxMenu$"+this.getCswmParent());
 		if(csWxMenu!=null)
			return csWxMenu;
		if(this.getCswmParent()!=null){
 			csWxMenu = CsWxMenu.get(this.getCswmParent());
 		}
 		$.SetRequest("CsWxMenu$"+this.getCswmParent(), csWxMenu);
	 	return csWxMenu;
	}
	/*******************************菜单类型**********************************/	
	/**
	* 菜单类型 [非空]   0:链接 1:发消息    
	**/
	public Short getCswmType(){
		return this.cswmType;
	}
	/**
	* 获取菜单类型格式化(toString)
	**/
	public String getCswmType$(){
		String strValue="";
		 if($.equals($.str(this.getCswmType()),"0"))
			strValue=$.str("链接");		 
		 if($.equals($.str(this.getCswmType()),"1"))
			strValue=$.str("发消息");		 
	 	 return strValue;
	}
	/**
	* 菜单类型 [非空]   0:链接 1:发消息    
	**/
	public void setCswmType(Short cswmType){
		this.cswmType = cswmType;
		this.setSeted(F.cswmType);
	}
	/*******************************支持终端类型**********************************/	
	/**
	* 支持终端类型 [非空]   0:微信 1:支付宝    
	**/
	public String getCswmSupport(){
		return this.cswmSupport;
	}
	/**
	* 获取支持终端类型格式化(toString)
	**/
	public String getCswmSupport$(){
		String strValue="";
		if($.idin(this.getCswmSupport(),0))
			strValue+=$.str("微信,");								
		if($.idin(this.getCswmSupport(),1))
			strValue+=$.str("支付宝,");								
	 	 return strValue;
	}
	/**
	* 支持终端类型 [非空]   0:微信 1:支付宝    
	**/
	public void setCswmSupport(String cswmSupport){
		this.cswmSupport = cswmSupport;
		this.setSeted(F.cswmSupport);
	}
	/*******************************地址或消息**********************************/	
	/**
	* 地址或消息 [非空]      
	**/
	public String getCswmUrlKey(){
		return this.cswmUrlKey;
	}
	/**
	* 获取地址或消息格式化(toString)
	**/
	public String getCswmUrlKey$(){
		String strValue="";
		 strValue=$.str(this.getCswmUrlKey());
	 	 return strValue;
	}
	/**
	* 地址或消息 [非空]      
	**/
	public void setCswmUrlKey(String cswmUrlKey){
		this.cswmUrlKey = cswmUrlKey;
		this.setSeted(F.cswmUrlKey);
	}
	/*******************************优先级**********************************/	
	/**
	* 优先级 [非空]   0:默认 1:1 2:2 3:3 4:4 5:5    
	**/
	public Short getCswmLevel(){
		return this.cswmLevel;
	}
	/**
	* 获取优先级格式化(toString)
	**/
	public String getCswmLevel$(){
		String strValue="";
		 if($.equals($.str(this.getCswmLevel()),"0"))
			strValue=$.str("默认");		 
		 if($.equals($.str(this.getCswmLevel()),"1"))
			strValue=$.str("1");		 
		 if($.equals($.str(this.getCswmLevel()),"2"))
			strValue=$.str("2");		 
		 if($.equals($.str(this.getCswmLevel()),"3"))
			strValue=$.str("3");		 
		 if($.equals($.str(this.getCswmLevel()),"4"))
			strValue=$.str("4");		 
		 if($.equals($.str(this.getCswmLevel()),"5"))
			strValue=$.str("5");		 
	 	 return strValue;
	}
	/**
	* 优先级 [非空]   0:默认 1:1 2:2 3:3 4:4 5:5    
	**/
	public void setCswmLevel(Short cswmLevel){
		this.cswmLevel = cswmLevel;
		this.setSeted(F.cswmLevel);
	}
	/*******************************更改时间**********************************/	
	/**
	* 更改时间 [非空]      
	**/
	public Date getCswmTime(){
		return this.cswmTime;
	}
	/**
	* 获取更改时间格式化(toString)
	**/
	public String getCswmTime$(){
		String strValue="";
		 strValue=$.str($.date(this.getCswmTime(),"yyyy-MM-dd HH:mm:ss"));
	 	 return strValue;
	}
	/**
	* 更改时间 [非空]      
	**/
	public void setCswmTime(Date cswmTime){
		this.cswmTime = cswmTime;
		this.setSeted(F.cswmTime);
	}
	/*******************************状态**********************************/	
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public Short getCswmStatus(){
		return this.cswmStatus;
	}
	/**
	* 获取状态格式化(toString)
	**/
	public String getCswmStatus$(){
		String strValue="";
		 if($.equals($.str(this.getCswmStatus()),"1"))
			strValue=$.str("正常");		 
		 if($.equals($.str(this.getCswmStatus()),"0"))
			strValue=$.str("无效");		 
	 	 return strValue;
	}
	/**
	* 状态 [非空]   1:正常 0:无效    
	**/
	public void setCswmStatus(Short cswmStatus){
		this.cswmStatus = cswmStatus;
		this.setSeted(F.cswmStatus);
	}
	/************LAZY3Q_DEFINE_CODE************/
	/************LAZY3Q_DEFINE_CODE************/

	
	
	/**
	 * 用来获取当有字段关联对象时的获取方式,调用如${CsWxMenu.obj.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsWxMenu.$filedName}获取关联对象
	 */
	public Map getObj(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsWxMenu.class.getMethod("get$"+$.forMat(key.toString())).invoke(CsWxMenu.this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
	/**
	 * 用来获取某字段格式化的获取方式,调用如${CsWxMenu.fmt.filedName}
	 * 因为有些变态的模板语言不能识别$，所以不能以${CsWxMenu.filedName$}获取某字段格式化
	 */
	public Map getFmt(){
		return new HashMap(){
			@Override
			public Object get(Object key) {
				try {
					return CsWxMenu.class.getMethod("get"+$.ForMat(key.toString()+"$")).invoke(CsWxMenu.this);
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
		public M cswmId(Object cswmId){this.put("cswmId", cswmId);return this;};
	 	/** and cswm_id is null */
 		public M cswmIdNull(){if(this.get("cswmIdNot")==null)this.put("cswmIdNot", "");this.put("cswmId", null);return this;};
 		/** not .... */
 		public M cswmIdNot(){this.put("cswmIdNot", "not");return this;};
		/** 菜单名称 [非空]       **/
		public M cswmName(Object cswmName){this.put("cswmName", cswmName);return this;};
	 	/** and cswm_name is null */
 		public M cswmNameNull(){if(this.get("cswmNameNot")==null)this.put("cswmNameNot", "");this.put("cswmName", null);return this;};
 		/** not .... */
 		public M cswmNameNot(){this.put("cswmNameNot", "not");return this;};
		/** 上级菜单 [非空] [CsWxMenu]      **/
		public M cswmParent(Object cswmParent){this.put("cswmParent", cswmParent);return this;};
	 	/** and cswm_parent is null */
 		public M cswmParentNull(){if(this.get("cswmParentNot")==null)this.put("cswmParentNot", "");this.put("cswmParent", null);return this;};
 		/** not .... */
 		public M cswmParentNot(){this.put("cswmParentNot", "not");return this;};
		public M cswmParent$on(CsWxMenu.M value){
			this.put("CsWxMenu", value);
			this.put("cswmParent$on", value);
			return this;
		};	
		/** 菜单类型 [非空]   0:链接 1:发消息     **/
		public M cswmType(Object cswmType){this.put("cswmType", cswmType);return this;};
	 	/** and cswm_type is null */
 		public M cswmTypeNull(){if(this.get("cswmTypeNot")==null)this.put("cswmTypeNot", "");this.put("cswmType", null);return this;};
 		/** not .... */
 		public M cswmTypeNot(){this.put("cswmTypeNot", "not");return this;};
		/** 支持终端类型 [非空]   0:微信 1:支付宝     **/
		public M cswmSupport(Object cswmSupport){this.put("cswmSupport", cswmSupport);return this;};
	 	/** and cswm_support is null */
 		public M cswmSupportNull(){if(this.get("cswmSupportNot")==null)this.put("cswmSupportNot", "");this.put("cswmSupport", null);return this;};
 		/** not .... */
 		public M cswmSupportNot(){this.put("cswmSupportNot", "not");return this;};
		/** 地址或消息 [非空]       **/
		public M cswmUrlKey(Object cswmUrlKey){this.put("cswmUrlKey", cswmUrlKey);return this;};
	 	/** and cswm_url_key is null */
 		public M cswmUrlKeyNull(){if(this.get("cswmUrlKeyNot")==null)this.put("cswmUrlKeyNot", "");this.put("cswmUrlKey", null);return this;};
 		/** not .... */
 		public M cswmUrlKeyNot(){this.put("cswmUrlKeyNot", "not");return this;};
		/** 优先级 [非空]   0:默认 1:1 2:2 3:3 4:4 5:5     **/
		public M cswmLevel(Object cswmLevel){this.put("cswmLevel", cswmLevel);return this;};
	 	/** and cswm_level is null */
 		public M cswmLevelNull(){if(this.get("cswmLevelNot")==null)this.put("cswmLevelNot", "");this.put("cswmLevel", null);return this;};
 		/** not .... */
 		public M cswmLevelNot(){this.put("cswmLevelNot", "not");return this;};
		/** 更改时间 [非空]       **/
		public M cswmTime(Object cswmTime){this.put("cswmTime", cswmTime);return this;};
	 	/** and cswm_time is null */
 		public M cswmTimeNull(){if(this.get("cswmTimeNot")==null)this.put("cswmTimeNot", "");this.put("cswmTime", null);return this;};
 		/** not .... */
 		public M cswmTimeNot(){this.put("cswmTimeNot", "not");return this;};
 		/** and cswm_time >= ? */
 		public M cswmTimeStart(Object start){this.put("cswmTimeStart", start);return this;};			
 		/** and cswm_time <= ? */
 		public M cswmTimeEnd(Object end){this.put("cswmTimeEnd", end);return this;};
		/** 状态 [非空]   1:正常 0:无效     **/
		public M cswmStatus(Object cswmStatus){this.put("cswmStatus", cswmStatus);return this;};
	 	/** and cswm_status is null */
 		public M cswmStatusNull(){if(this.get("cswmStatusNot")==null)this.put("cswmStatusNot", "");this.put("cswmStatus", null);return this;};
 		/** not .... */
 		public M cswmStatusNot(){this.put("cswmStatusNot", "not");return this;};
	 	public M add(String key, Object value) {this.put(key, value);return this;}
	 	public M definex(String sql) {this.put("definex", sql);return this;}
	 	/** 获取所有微信菜单 **/
		public @api List<CsWxMenu> list(Integer size){
			return getCsWxMenuList(this,size);
		}
		/** 获取微信菜单分页 **/
		public @api Page<CsWxMenu> page(int page,int size){
			return getCsWxMenuPage(page, size , this);
		}
		/** 根据查询条件取微信菜单 **/
		public @api CsWxMenu get(){
			return getCsWxMenu(this);
		}
		/** 获取微信菜单数 **/
		public @api Long count(){
			return getCsWxMenuCount(this);
		}
		/** 获取微信菜单表达式 **/
		public @api <T> T eval(String strEval){
			return getCsWxMenuEval(strEval,this);
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
			updateCsWxMenu(set,where);
		}
	}
	
	
	
	/** 对象的字段描述 **/
	public static class F{
		/** 编号 [非空]       **/
		public final static @type(Long.class)  String cswmId="cswmId";
		/** 菜单名称 [非空]       **/
		public final static @type(String.class) @like String cswmName="cswmName";
		/** 上级菜单 [非空] [CsWxMenu]      **/
		public final static @type(Long.class)  String cswmParent="cswmParent";
		/** 菜单类型 [非空]   0:链接 1:发消息     **/
		public final static @type(Short.class)  String cswmType="cswmType";
		/** 支持终端类型 [非空]   0:微信 1:支付宝     **/
		public final static @type(String.class) @like String cswmSupport="cswmSupport";
		/** 地址或消息 [非空]       **/
		public final static @type(String.class) @like String cswmUrlKey="cswmUrlKey";
		/** 优先级 [非空]   0:默认 1:1 2:2 3:3 4:4 5:5     **/
		public final static @type(Short.class)  String cswmLevel="cswmLevel";
		/** 更改时间 [非空]       **/
		public final static @type(Date.class)  String cswmTime="cswmTime";
	 	/** and cswm_time >= ? */
 		public final static @type(Date.class) String cswmTimeStart="cswmTimeStart";
 		/** and cswm_time <= ? */
 		public final static @type(Date.class) String cswmTimeEnd="cswmTimeEnd";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static @type(Short.class)  String cswmStatus="cswmStatus";
	}
	
	/** 对象的数据库字段描述 **/
	public static class C{
		/** 编号 [非空]       **/
		public final static String cswmId="cswm_id";
		/** 菜单名称 [非空]       **/
		public final static String cswmName="cswm_name";
		/** 上级菜单 [非空] [CsWxMenu]      **/
		public final static String cswmParent="cswm_parent";
		/** 菜单类型 [非空]   0:链接 1:发消息     **/
		public final static String cswmType="cswm_type";
		/** 支持终端类型 [非空]   0:微信 1:支付宝     **/
		public final static String cswmSupport="cswm_support";
		/** 地址或消息 [非空]       **/
		public final static String cswmUrlKey="cswm_url_key";
		/** 优先级 [非空]   0:默认 1:1 2:2 3:3 4:4 5:5     **/
		public final static String cswmLevel="cswm_level";
		/** 更改时间 [非空]       **/
		public final static String cswmTime="cswm_time";
		/** 状态 [非空]   1:正常 0:无效     **/
		public final static String cswmStatus="cswm_status";
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
				$.Set(name,CsWxMenu.getCsWxMenu(params));
			else
				$.Set(name,CsWxMenu.getCsWxMenuList(params, size));
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	/**
	* 获取微信菜单数据
	**/
	public static Object getData(Object param){
		if(param==null)
			return null;
		Object value = (com.ccclubs.model.CsWxMenu) $.GetRequest("CsWxMenu$"+param.hashCode());
		if(value!=null)
			return value;
		if(param.getClass()==Long.class)
			value = CsWxMenu.get((Long)param);
		else if(param instanceof java.util.Map){
			java.util.Map params = (Map) param;
			if(params.get("size")==null)
				value = CsWxMenu.getCsWxMenu(params);
			else
				value = CsWxMenu.getCsWxMenuList(params, (Integer) params.get("size"));
		}else if(param.getClass()==Long.class )
			value = CsWxMenu.Get($.add(CsWxMenu.F.cswmId,param));
		else if(!$.empty(param.toString()))
			value = CsWxMenu.get(Long.valueOf(param.toString()));
		$.SetRequest("CsWxMenu$"+param.hashCode(), value);
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
	public void mergeSet(CsWxMenu old){
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